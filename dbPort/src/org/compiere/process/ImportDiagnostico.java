package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Proceso de importaci&oacute;n de la tabla de Diagnostico.<p>
 * Creado: 11/Mar/2005<p>
 * Modificado: $Date: 2006/02/09 23:27:27 $<p>
 * Por: $Author: gisela $<p>
 * 
 * @author Gisela Lee
 * @version $Revision: 1.3 $
 */
public class ImportDiagnostico extends SvrProcess {
    

    /**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;

	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;

	/** Organization to be imported to	*/
	private int				m_AD_Org_ID = 0;

	/** Effective						*/
	private Timestamp		m_DateValue = null;

    /**
     * Constructor por defecto.
     */
    public ImportDiagnostico() {
        super();
    }

    /**
     * Preparar: obtener par&aacute;metros
     */
    protected void prepare() {
        ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {

			String name = para[i].getParameterName();

			if ("AD_Client_ID".equals(name)) {
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			} else if ("DeleteOldImported".equals(name)) {
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			} else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}

		if (m_DateValue == null) {
			m_DateValue = DB.getTimestampForOrg(Env.getCtx());
		}
    }


    /**
     * Corre el proceso.
     * @return Un mensaje de estado
     * @throws Exception
     */
    protected String doIt() throws Exception {

        StringBuffer sql = null;

		int no = 0;

		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported

		if (m_deleteOldImported) {

			sql = 
				new StringBuffer ("DELETE I_EXME_Diagnostico ")
				.append("WHERE I_IsImported='Y'")
				.append(clientCheck);

			no = DB.executeUpdate(sql.toString(), null);
			log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IaActive, Created/Updated,
		sql = 
			new StringBuffer ("UPDATE I_EXME_Diagnostico ")
			.append("SET AD_Client_ID = COALESCE (AD_Client_ID, ")
			.append(m_AD_Client_ID)
			.append("),")
			.append(" AD_Org_ID = COALESCE (AD_Org_ID, 0),")
			.append(" IsActive = COALESCE (IsActive, 'Y'),")
			.append(" Created = COALESCE (Created, SysDate),")
			.append(" CreatedBy = COALESCE (CreatedBy, 0),")
			.append(" Updated = COALESCE (Updated, SysDate),")
			.append(" UpdatedBy = COALESCE (UpdatedBy, 0),")
			.append(" I_ErrorMsg = NULL,")
			.append(" I_IsImported = 'N' ")
			.append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");

		no = DB.executeUpdate(sql.toString(), null);

		log.info("Reset=" + no);

//		-------------------------------------------------------------------

		int noInsert = 0;
		int noUpdate = 0;


		//	Go through Records
		log.fine("start inserting/updating ...");

		sql = 
			new StringBuffer ("SELECT I_EXME_Diagnostico_ID, EXME_Diagnostico_ID ")
			.append("FROM I_EXME_Diagnostico WHERE I_IsImported='N'")
			.append(clientCheck);

		Connection conn = 
			DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED);
		
		ResultSet rs = null;
		PreparedStatement pstmt_insertDiagnostico = null;
		PreparedStatement pstmt_updateDiagnostico = null;
		PreparedStatement pstmt_setImported = null;
		PreparedStatement pstmt = null;

		try {
			
			//relate to the diagnostics header
			StringBuffer diagHeader = 
				new StringBuffer("UPDATE I_EXME_Diagnostico i SET EXME_DiagnosticoHdr_ID = ")
				.append("(SELECT EXME_DiagnosticoHdr_ID FROM EXME_DiagnosticoHdr WHERE Value = i.ValueHdr) ")
				.append("WHERE EXISTS (SELECT 1 FROM EXME_DiagnosticoHdr WHERE Value = i.ValueHdr)");
			
			int upd = DB.executeUpdate(diagHeader.toString(), null);
			log.log(Level.CONFIG, "** Updated to Header : " + upd);
			
			// to update the already existing diagnostics
			StringBuffer updatePrevious = 
				new StringBuffer("UPDATE I_EXME_Diagnostico i ")
				.append("SET i.EXME_Diagnostico_ID = ( ")
				.append("	SELECT distinct d.EXME_Diagnostico_ID ")
				.append("	FROM EXME_Diagnostico d ")
				.append("	WHERE d.Value = i.Value ")
				.append("   	AND d.EXME_DiagnosticoHdr_ID = i.EXME_DiagnosticoHdr_ID ")
				.append("		AND d.AD_Client_ID = i.AD_Client_ID) ")
				.append("WHERE ") 
				.append("EXISTS ( ")
				.append("	SELECT 1 FROM EXME_Diagnostico d ")
				.append("	WHERE d.Value = i.Value ")
				.append("   	AND d.EXME_DiagnosticoHdr_ID = i.EXME_DiagnosticoHdr_ID ")
				.append("		AND d.AD_Client_ID = i.AD_Client_ID)");
			
			upd = DB.executeUpdate(updatePrevious.toString(), null);
			log.log(Level.CONFIG, "** Already existing diagnostics : " + upd);
			
			//	Insertar diagnostico a partir de la importacion
			StringBuffer insertDiag = 
				new StringBuffer("INSERT INTO EXME_Diagnostico (EXME_Diagnostico_ID,")
				.append("AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,")
				.append("Value,Name,CodOms,CodInegi,CodGrd,CodSnomed, EXME_DiagnosticoHdr_ID) ")
				.append("SELECT ?,")
				.append("AD_Client_ID,AD_Org_ID,'Y',SysDate,CreatedBy,SysDate,UpdatedBy,")
				.append("Value,Name,NVL (CodOms, TO_CHAR(0)), NVL (CodInegi, TO_CHAR(0)), ")
				.append("NVL (CodGrd, TO_CHAR(0)), NVL (CodSnomed,TO_CHAR(0)), ")
				.append("EXME_DiagnosticoHdr_ID ")
				.append("FROM I_EXME_Diagnostico ")
				.append("WHERE I_EXME_Diagnostico_ID=?");
			
			pstmt_insertDiagnostico = conn.prepareStatement(insertDiag.toString());

			//	Update Diagnostico from Import
			StringBuffer updateDiag = 
				new StringBuffer("UPDATE EXME_Diagnostico ")
				.append("SET (Value,Name,Updated,UpdatedBy,CodOms,CodInegi,CodGrd,CodSnomed)= ")
				.append("(SELECT Value,Name,SysDate,UpdatedBy,CodOms,CodInegi,CodGrd,CodSnomed")
				.append(" FROM I_EXME_Diagnostico WHERE I_EXME_Diagnostico_ID=?) ")
				.append("WHERE EXME_Diagnostico_ID=?");
			
			pstmt_updateDiagnostico = conn.prepareStatement(updateDiag.toString());


			//	Set Imported = Y
			StringBuffer updImport = 
				new StringBuffer("UPDATE I_EXME_Diagnostico SET I_IsImported='Y', EXME_Diagnostico_ID=?, ")
				.append("Updated=SysDate, Processed='Y' WHERE I_EXME_Diagnostico_ID=?");

			pstmt_setImported = conn.prepareStatement(updImport.toString());

			//
			pstmt = DB.prepareStatement(sql.toString(), null);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int I_EXME_Diagnostico_ID = rs.getInt(1);

				int EXME_Diagnostico_ID = rs.getInt(2);

				boolean newDiagnostico = EXME_Diagnostico_ID == 0;

				log.fine("I_EXME_Diagnostico_ID=" + I_EXME_Diagnostico_ID + ", EXME_Diagnostico_ID=" + EXME_Diagnostico_ID);

				//	Diagnostico
				if (newDiagnostico)	{		//	Insert new Diagnostico
					try {

						EXME_Diagnostico_ID = DB.getNextID(m_AD_Client_ID, "EXME_Diagnostico", null);

						if (EXME_Diagnostico_ID <= 0) {
							throw new DBException("No Next ID (" + EXME_Diagnostico_ID + ")");
						}

						pstmt_insertDiagnostico.setInt(1, EXME_Diagnostico_ID);
						pstmt_insertDiagnostico.setInt(2, I_EXME_Diagnostico_ID);

						//
						no = pstmt_insertDiagnostico.executeUpdate();
						log.finer("Insert Diagnostico = " + no);
						noInsert++;
					} catch (Exception ex) {

						log.warning("Insert diagnostico  - " + ex.toString());

						sql = 
							new StringBuffer ("UPDATE I_EXME_Diagnostico i ")
							.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Insert diagnostico: " + ex.toString()))
							.append("WHERE I_EXME_Diagnostico_ID=")
							.append(I_EXME_Diagnostico_ID);

						DB.executeUpdate(sql.toString(), null);

						continue;
					}
				} else	{				//	Update Diagnostico

					pstmt_updateDiagnostico.setInt(1, I_EXME_Diagnostico_ID);
					pstmt_updateDiagnostico.setInt(2, EXME_Diagnostico_ID);

					try {
						no = pstmt_updateDiagnostico.executeUpdate();
						log.finer("Update Diagnostico = " + no);
						noUpdate++;
					} catch (SQLException ex) {

						log.warning("Update Diagnostico - " + ex.toString());

						sql = 
							new StringBuffer ("UPDATE I_EXME_Diagnostico i ")
							.append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||")
							.append(DB.TO_STRING("Update Diagnostico: " + ex.toString()))
							.append("WHERE I_EXME_Diagnostico_ID=")
							.append(I_EXME_Diagnostico_ID);

						DB.executeUpdate(sql.toString(), null);

						continue;
					}
				}

				

				//	Update I_EXME_Diagnostico

				pstmt_setImported.setInt(1, EXME_Diagnostico_ID);
				pstmt_setImported.setInt(2, I_EXME_Diagnostico_ID);

				no = pstmt_setImported.executeUpdate();

				//
				conn.commit();
			}

		} catch (SQLException e) {
			
			log.log(Level.SEVERE, "doIt", e);

			throw new Exception ("doIt", e);

		} finally {

			try {
				if(rs != null) {
					rs.close();
				}
				
				if (conn != null) {
					conn.close();
				}
				
				if(pstmt_updateDiagnostico != null) {
						pstmt_insertDiagnostico.close();
				}
				
				if(pstmt_updateDiagnostico != null) {
					pstmt_updateDiagnostico.close();
				}
				
				if(pstmt_setImported != null) {
					pstmt_setImported.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
			} catch (SQLException e) {
				log.log(Level.SEVERE, "While clossing connection ...", e);
			}

		}



		//	Set Error to indicator to not imported

		sql = 
			new StringBuffer ("UPDATE I_EXME_Diagnostico ")
			.append("SET I_IsImported='N', Updated=SysDate ")
			.append("WHERE I_IsImported<>'Y'")
			.append(clientCheck);

		no = DB.executeUpdate(sql.toString(), null);

		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noInsert), "@EXME_Diagnostico_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noUpdate), "@EXME_Diagnostico_ID@: @Updated@");

		return "";
    }

}

