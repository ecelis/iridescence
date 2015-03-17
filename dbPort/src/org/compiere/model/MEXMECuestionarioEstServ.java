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
public class MEXMECuestionarioEstServ extends X_EXME_CuestionarioEstServ {
	private static CLogger s_log = CLogger.getCLogger(MEXMECuestionarioEstServ.class);
	private static final long serialVersionUID = 5844708864685132542L;

	public static List<MEXMECuestionarioEstServ> get(Properties ctx, int cuestId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_cuestionarioestserv ");
		sql.append("WHERE ");
		sql.append("  exme_cuestionario_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		List<MEXMECuestionarioEstServ> list = new ArrayList<MEXMECuestionarioEstServ>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cuestId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMECuestionarioEstServ(ctx, rs, trxName));
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
	 * @param EXME_CuestionarioEstServ_ID
	 * @param trxName
	 */
	public MEXMECuestionarioEstServ(Properties ctx, int EXME_CuestionarioEstServ_ID, String trxName) {
		super(ctx, EXME_CuestionarioEstServ_ID, trxName);
	}
	
	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMECuestionarioEstServ(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
