package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author odelarosa
 * 
 */
public class MEXMEMedEstServ extends X_EXME_MedEstServ {

	private static final long serialVersionUID = 1363331645883901607L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEMedEstServ.class);

	/**
	 * Obtener horario del médico por especialidad
	 * 
	 * @param ctx
	 *            Contexto
	 * @param medicoID
	 *            Médico
	 * @param isActive
	 *            Activo o no
	 * @param trxName
	 *            Transacción
	 * @return
	 */
	public static MEXMEMedEstServ get(Properties ctx, int medicoID, boolean isActive, String trxName) {
		StringBuilder st = new StringBuilder("select * from EXME_MedEstServ where EXME_Medico_ID = ?  and isActive = ? and EXME_EstServ_ID = ? ");
		st = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, st.toString(), "EXME_MedEstServ"));
		MEXMEMedEstServ retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(st.toString(), null);
			pstmt.setInt(1, medicoID);
			if (isActive) {
				pstmt.setString(2, "Y");
			} else {
				pstmt.setString(2, "N");
			}
			pstmt.setInt(3, Env.getEXME_EstServ_ID(ctx));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MEXMEMedEstServ(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, st.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return retValue;
	}

	/**
	 * @param ctx
	 * @param EXME_MedEstServ_ID
	 * @param trxName
	 */
	public MEXMEMedEstServ(Properties ctx, int EXME_MedEstServ_ID, String trxName) {
		super(ctx, EXME_MedEstServ_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEMedEstServ(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
