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
 * @author odelarosa
 * 
 */
public class MEXMEAlertaPac extends X_EXME_AlertaPac {
	private static CLogger s_log = CLogger.getCLogger(MEXMEAlertaPac.class);
	private static final long serialVersionUID = 8576476789308457312L;

	/**
	 * Obtiene las alertas del paciente
	 * 
	 * @param ctx
	 * @param pacId
	 *            Paciente
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEAlertaPac> getLst(Properties ctx, int pacId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_alertapac ");
		sql.append("WHERE ");
		sql.append("  exme_paciente_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEAlertaPac.Table_Name));
		List<MEXMEAlertaPac> list = new ArrayList<MEXMEAlertaPac>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEAlertaPac(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * @param ctx
	 * @param EXME_AlertaPac_ID
	 * @param trxName
	 */
	public MEXMEAlertaPac(Properties ctx, int EXME_AlertaPac_ID, String trxName) {
		super(ctx, EXME_AlertaPac_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEAlertaPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
