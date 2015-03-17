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
public class MEXMERDRoleAccess extends X_EXME_RD_Role_Access {

	private static final long serialVersionUID = 3794178672962337556L;
	private static CLogger s_log = CLogger.getCLogger(MEXMERDRoleAccess.class);

	public static List<MEXMERDRoleAccess> get(Properties ctx, int roleId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * from EXME_RD_Role_Access ");
		sql.append(" where AD_Role_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMERDRoleAccess.Table_Name));
		List<MEXMERDRoleAccess> list = new ArrayList<MEXMERDRoleAccess>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, roleId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMERDRoleAccess(ctx, rs, trxName));
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
	 * @param EXME_RD_Role_Access_ID
	 * @param trxName
	 */
	public MEXMERDRoleAccess(Properties ctx, int EXME_RD_Role_Access_ID, String trxName) {
		super(ctx, EXME_RD_Role_Access_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMERDRoleAccess(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
