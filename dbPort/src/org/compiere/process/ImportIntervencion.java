package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.X_EXME_Intervencion;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Proceso de importaci&oacute;n de la tabla de Intervencion.
 * <p>
 * Creado: 23/Mar/2005
 * <p>
 * Modificado: $Date: 2006/02/09 23:35:07 $
 * <p>
 * Por: $Author: gisela $
 * <p>
 *
 * @author Gisela Lee
 *         Modified by Lorena Lama (PMD, Postgresql, FindBugs)
 * @version $Revision: 1.2 $
 */
public class ImportIntervencion extends SvrProcess {

	/** Client to be imported to */
	private int		adClientId;
	/** Delete old Imported */
	private boolean	isDeleteOldImp;

	/** Preparar: obtener par&aacute;metros */
	protected void prepare() {
		final ProcessInfoParameter[] para = getParameter();
		for (final ProcessInfoParameter pinfo : para) {
			final String name = pinfo.getParameterName();
			if ("AD_Client_ID".equals(name)) {
				adClientId = ((BigDecimal) pinfo.getParameter()).intValue();
			} else if ("DeleteOldImported".equals(name)) {
				isDeleteOldImp = "Y".equals(pinfo.getParameter());
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}
	}

	/** Corre el proceso */
	protected String doIt() {
		// Delete Old Imported
		deleteOldImported();
		// Set Client, Org, IsActive, Created/Updated, NotImported
		updateToImport();
		// relate procedures to header
		updateInterventionHdr();
		// update existing interventions
		updatePrevious();
		// Insert/Update EXME_Intervention
		runProcess();
		// Set Error to not imported records
		updateNoImported();
		return "";
	}

	/** @return Sysdate or now() for postgresql */
	private static String sqlDate() {
		return DB.isPostgreSQL() ? "now()" : "Sysdate";
	}

	/** @return next ID (AD_Sequency.CurrentNext) for table EXME_Intervencion */
	private int getNextId() {
		final int nextId = DB.getNextID(adClientId, X_EXME_Intervencion.Table_Name, null);
		if (nextId <= 0) {
			throw new MedsysException("No Next ID EXME_Intervencion (" + nextId + ")");
		}
		return nextId;
	}

	/** @return SQL insert into EXME_Intervencion */
	private String getSQLInsert() {
		final StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO EXME_Intervencion (EXME_Intervencion_ID,");
		sql.append("AD_Client_ID,AD_Org_ID,IsActive,CreatedBy,UpdatedBy,Created,Updated,");
		sql.append("Value,Name,Description,CodOms,CodInegi,CodGrd,CodSnomed,EXME_IntervencionHdr_ID,ProcedureGroup) ");
		sql.append("SELECT ?,AD_Client_ID,AD_Org_ID,'Y',CreatedBy,UpdatedBy,?,?,");
		// sql.append(sqlDate()).append(",").append(sqlDate());
		sql.append("Value,Name,Description,CodOms,CodInegi,CodGrd,CodSnomed,EXME_IntervencionHdr_ID,ProcedureGroup ");
		sql.append("FROM I_EXME_Intervencion ");
		sql.append("WHERE I_EXME_Intervencion_ID=?");
		log.config(sql.toString());
		return sql.toString();
	}

	/** @return SQL update EXME_Intervencion */
	private String getSQLUpdate() {
		final StringBuilder sql = new StringBuilder();
		if (DB.isPostgreSQL()) {
			sql.append("UPDATE EXME_Intervencion SET ");
//			sql.append("Value=I_EXME_Intervencion.Value,");
			sql.append("Name=I_EXME_Intervencion.Name,");
			sql.append("Description=I_EXME_Intervencion.Description,");
			sql.append("Updated=?,");// now()
			sql.append("UpdatedBy=I_EXME_Intervencion.UpdatedBy,");
			sql.append("CodOms=I_EXME_Intervencion.CodOms,");
			sql.append("CodInegi=I_EXME_Intervencion.CodInegi,");
			sql.append("CodGrd=I_EXME_Intervencion.CodGrd,");
			sql.append("CodSnomed=I_EXME_Intervencion.CodSnomed,");
			sql.append("ProcedureGroup=I_EXME_Intervencion.ProcedureGroup ");
			sql.append("FROM I_EXME_Intervencion WHERE I_EXME_Intervencion_ID=? ");
			sql.append("AND I_EXME_Intervencion.EXME_Intervencion_ID=?");
		} else {
			sql.append("UPDATE EXME_Intervencion ");
			sql.append("SET (Name,Description,Updated,UpdatedBy,CodOms,CodInegi,CodGrd,CodSnomed,ProcedureGroup) = ");
			sql.append("(SELECT Name,Description,?,UpdatedBy,CodOms,CodInegi,CodGrd,CodSnomed,ProcedureGroup");
			sql.append(" FROM I_EXME_Intervencion WHERE I_EXME_Intervencion_ID=?) ");
			sql.append("WHERE EXME_Intervencion_ID=?");
		}
		log.config(sql.toString());
		return sql.toString();
	}

	/** @return SQL update I_EXME_Intervencion: Imported=Y */
	private String getSQLSetImported() {
		final StringBuilder sql = new StringBuilder();
		sql.append("UPDATE I_EXME_Intervencion SET Updated=").append(sqlDate());
		sql.append(",I_IsImported='Y',Processed='Y',EXME_Intervencion_ID=? ");
		sql.append("WHERE I_EXME_Intervencion_ID=?");
		log.config(sql.toString());
		return sql.toString();
	}

	/** Delete Old Imported */
	private void deleteOldImported() {
		if (isDeleteOldImp) {
			final int retvalue = DB.executeUpdate(//
				"DELETE I_EXME_Intervencion WHERE I_IsImported='Y' AND AD_Client_ID=?",//
				new Object[] { adClientId }, null);
			log.config("Delete Old Imported =" + retvalue);
		}
	}

	/** Set Client, Org, IsActive, Created/Updated, */
	private void updateToImport() {
		final StringBuilder sql = new StringBuilder();
		sql.append("UPDATE I_EXME_Intervencion ");
		sql.append("SET AD_Client_ID=COALESCE(AD_Client_ID,?),");
		sql.append("AD_Org_ID=COALESCE(AD_Org_ID,0),");
		sql.append("IsActive=COALESCE(IsActive,'Y'),");
		sql.append("Created=COALESCE(Created,").append(sqlDate()).append("),");
		sql.append("CreatedBy=COALESCE(CreatedBy,0),");
		sql.append("Updated=COALESCE(Updated,").append(sqlDate()).append("),");
		sql.append("UpdatedBy=COALESCE(UpdatedBy,0),");
		sql.append("I_ErrorMsg=NULL,");
		sql.append("I_IsImported='N' ");
		sql.append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		final int retvalue = DB.executeUpdate(sql.toString(), new Object[] { adClientId }, null);
		log.config("Reset=" + retvalue);
	}

	/** relate to the procedures to the header */
	private void updateInterventionHdr() {
		final StringBuilder sql = new StringBuilder();
		sql.append("UPDATE I_EXME_Intervencion i SET EXME_IntervencionHdr_ID=");
		sql.append("(SELECT EXME_IntervencionHdr_ID FROM EXME_IntervencionHdr WHERE Value=i.ValueHdr) ");
		sql.append("WHERE EXISTS (SELECT 1 FROM EXME_IntervencionHdr WHERE Value=i.ValueHdr) ");
		final int retvalue = DB.executeUpdate(sql.toString(), null);
		log.config("** Updated to Header : " + retvalue);
	}

	/** to update the already existing interventions */
	private void updatePrevious() {
		final StringBuilder sql = new StringBuilder();
		sql.append("UPDATE I_EXME_Intervencion ");
		sql.append("SET EXME_Intervencion_ID=( ");
		sql.append("	SELECT distinct d.EXME_Intervencion_ID ");
		sql.append("	FROM EXME_Intervencion d ");
		sql.append("	WHERE d.Value=I_EXME_Intervencion.Value ");
		sql.append("	  AND d.EXME_IntervencionHdr_ID=I_EXME_Intervencion.EXME_IntervencionHdr_ID ");
		sql.append("	  AND d.AD_Client_ID=I_EXME_Intervencion.AD_Client_ID) ");
		sql.append("WHERE ");
		sql.append("EXISTS ( ");
		sql.append("	SELECT 1 FROM EXME_Intervencion d ");
		sql.append("	WHERE d.Value=I_EXME_Intervencion.Value ");
		sql.append("      AND d.EXME_IntervencionHdr_ID=I_EXME_Intervencion.EXME_IntervencionHdr_ID ");
		sql.append("	  AND d.AD_Client_ID=I_EXME_Intervencion.AD_Client_ID)");

		final int retvalue = DB.executeUpdate(sql.toString(), null);
		log.config("** Already existing interventions : " + retvalue);
	}

	/**
	 * Process I_EXME_Intervention and Insert/Update EXME_Intervention
	 *
	 * @return map: Inserted (total), Updated (total)
	 */
	private void runProcess() {
		int noInsert = 0;
		int noUpdate = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resSet = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtUpdate = null;
		PreparedStatement pstmtSetImported = null;

		try {
			pstmt =
				DB.prepareStatement(
					"SELECT I_EXME_Intervencion_ID, EXME_Intervencion_ID FROM I_EXME_Intervencion WHERE I_IsImported='N' AND AD_Client_ID=?", null);
			pstmt.setInt(1, adClientId);
			resSet = pstmt.executeQuery();

			conn = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);

			log.fine("start inserting/updating... ");
			final Timestamp dateValue = Env.getCurrentDate();
			// Go through Records
			while (resSet.next()) {
				final int i_IntervencionID = resSet.getInt("I_EXME_Intervencion_ID");
				int intervencionID = resSet.getInt("EXME_Intervencion_ID");
				final boolean newIntervencion = intervencionID == 0;

				log.config("I_EXME_Intervencion_ID=" + i_IntervencionID + ", EXME_Intervencion_ID=" + intervencionID);

				if (newIntervencion) { // Insert Intervention from import
					log.fine(" Insert ... ");
					try {
						intervencionID = getNextId();
						if (pstmtInsert == null) {
							pstmtInsert = conn.prepareStatement(getSQLInsert());
						}
						pstmtInsert.setInt(1, intervencionID);
						pstmtInsert.setTimestamp(2, dateValue);
						pstmtInsert.setTimestamp(3, dateValue);
						pstmtInsert.setInt(4, i_IntervencionID);
						//
						log.config("Insert Intervention = " + pstmtInsert.executeUpdate());
						noInsert++;
					} catch (final Exception ex) {
						updateError(i_IntervencionID, "Insert Intervention  -  " + ex.toString());
						continue;
					}
				} else { // Update Intervention from Import
					log.fine(" Update ... ");
					try {
						if (pstmtUpdate == null) {
							pstmtUpdate = conn.prepareStatement(getSQLUpdate());
						}
						pstmtUpdate.setTimestamp(1, dateValue);
						pstmtUpdate.setInt(2, i_IntervencionID);
						pstmtUpdate.setInt(3, intervencionID);
						//
						log.config("Update Intervention = " + pstmtUpdate.executeUpdate());
						noUpdate++;
					} catch (final SQLException ex) {
						updateError(i_IntervencionID, "Update Intervention  -  " + ex.toString());
						continue;
					}
				}
				log.fine(" Set is_imported (yes) ... ");
				// Update I_EXME_Intervencion Imported = Y
				if (pstmtSetImported == null) {
					pstmtSetImported = conn.prepareStatement(getSQLSetImported());
				}
				pstmtSetImported.setInt(1, intervencionID);
				pstmtSetImported.setInt(2, i_IntervencionID);
				log.config("Update Set Imported = " + pstmtSetImported.executeUpdate());
				//
				conn.commit();
			}
			//
		} catch (final SQLException e) {
			log.log(Level.SEVERE, "doIt", e);
			throw new MedsysException("doIt", e);
		} finally {
			DB.close(conn);
			DB.close(resSet, pstmt);
			DB.close(pstmtInsert);
			DB.close(pstmtUpdate);
			DB.close(pstmtSetImported);
		}

		addLog(0, null, new BigDecimal(noInsert), "@EXME_Intervencion_ID@: @Inserted@");
		addLog(0, null, new BigDecimal(noUpdate), "@EXME_Intervencion_ID@: @Updated@");
	}

	/** Set Error to indicator to not imported */
	private void updateNoImported() {
		final StringBuilder sql = new StringBuilder();
		sql.append("UPDATE I_EXME_Intervencion ");
		sql.append("SET I_IsImported='N',Updated=").append(sqlDate()).append(" ");
		sql.append("WHERE I_IsImported<>'Y' AND AD_Client_ID=? ");
		final int retvalue = DB.executeUpdate(sql.toString(), new Object[] { adClientId }, null);
		addLog(0, null, new BigDecimal(retvalue), "@Errors@");
	}

	/** Set the exception as the error */
	private void updateError(final int iIntervencionId, final String error) {
		log.warning(error);
		final StringBuilder sql = new StringBuilder();
		sql.append("UPDATE I_EXME_Intervencion i ");
		sql.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||");
		sql.append(DB.TO_STRING(error));
		sql.append("WHERE I_EXME_Intervencion_ID=? ");

		final int retvalue = DB.executeUpdate(sql.toString(), new Object[] { iIntervencionId }, null);
		log.severe("updateError: " + retvalue);
	}
}
