package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Vacunas externas del paciente
 * 
 * @author odelarosa
 * 
 */
public class MEXMEExternalVaccine extends X_EXME_ExternalVaccine {

	private static CLogger s_log = CLogger.getCLogger(MEXMEExternalVaccine.class);
	private static final long serialVersionUID = 2253812914076601401L;

	/**
	 * Obtenemos las vacunas externas de la cita
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param citaId
	 *            Cita del paciente
	 * @param trxName
	 *            Trx Name
	 * @return
	 */
	public static List<MEXMEExternalVaccine> get(Properties ctx, int citaId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_externalvaccine ");
		sql.append("WHERE ");
		sql.append("  exme_citamedica_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEExternalVaccine.Table_Name));
		List<MEXMEExternalVaccine> list = new ArrayList<MEXMEExternalVaccine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, citaId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEExternalVaccine(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Actualizar vacunas externas de la cita
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param citaId
	 *            Cita del paciente
	 * @param closed
	 *            Si la cita está o no cerrada
	 * @param trxName
	 *            Trx name
	 */
	public static boolean update(Properties ctx, int citaId, boolean closed, String trxName) {
		boolean retValue = true;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append("  exme_externalvaccine ");
		sql.append("SET ");
		sql.append("  isclosed = ");
		sql.append("? WHERE ");
		sql.append("  exme_citamedica_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEExternalVaccine.Table_Name));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, DB.TO_STRING(closed));
			pstmt.setInt(2, citaId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			retValue = false;
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}

	/**
	 * @param ctx
	 * @param EXME_ExternalVaccine_ID
	 * @param trxName
	 */
	public MEXMEExternalVaccine(Properties ctx, int EXME_ExternalVaccine_ID, String trxName) {
		super(ctx, EXME_ExternalVaccine_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEExternalVaccine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
