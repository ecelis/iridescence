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
public class MEXMERDUserAccess extends X_EXME_RD_User_Access {

	private static final long serialVersionUID = -3151616585156759886L;
	private static CLogger s_log = CLogger.getCLogger(MEXMERDUserAccess.class);

	public static List<MEXMERDUserAccess> get(Properties ctx, int userId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * from EXME_RD_User_Access ");
		sql.append(" where AD_User_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMERDUserAccess.Table_Name));
		List<MEXMERDUserAccess> list = new ArrayList<MEXMERDUserAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMERDUserAccess(ctx, rs, trxName));
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
	 * @param EXME_RD_User_Access_ID
	 * @param trxName
	 */
	public MEXMERDUserAccess(Properties ctx, int EXME_RD_User_Access_ID, String trxName) {
		super(ctx, EXME_RD_User_Access_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERDUserAccess(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
