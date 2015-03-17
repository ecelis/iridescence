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
public class MEXMECuestionarioUser extends X_EXME_CuestionarioUser {
	private static CLogger s_log = CLogger.getCLogger(MEXMECuestionarioUser.class);
	private static final long serialVersionUID = 3864885705514254203L;

	public static List<MEXMECuestionarioUser> get(Properties ctx, int cuestId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_cuestionariouser ");
		sql.append(" WHERE ");
		sql.append("  exme_cuestionario_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		List<MEXMECuestionarioUser> list = new ArrayList<MEXMECuestionarioUser>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMECuestionarioUser(ctx, rs, trxName));
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
	 * @param EXME_CuestionarioUser_ID
	 * @param trxName
	 */
	public MEXMECuestionarioUser(Properties ctx, int EXME_CuestionarioUser_ID, String trxName) {
		super(ctx, EXME_CuestionarioUser_ID, trxName);
	}
	
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECuestionarioUser(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
