/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author Alejandro
 * 
 *@deprecated sera removida
 */
public class MEXMEConfigInt extends X_EXME_ConfigInt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger s_log = CLogger.getCLogger(MEXMEConfigInt.class);

	/**
	 * @param ctx
	 * @param EXME_Colonia_ID
	 * @param trxName
	 */

	public MEXMEConfigInt(Properties ctx, int EXME_ConfigInt_ID, String trxName) {
		super(ctx, EXME_ConfigInt_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConfigInt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static MEXMEConfigInt get(Properties ctx, String trxName,String whereClause) {

		if (ctx == null) {
			return null;
		}

		MEXMEConfigInt retValue = null;
		StringBuilder sql = new StringBuilder(" SELECT * FROM EXME_ConfigInt WHERE EXME_ConfigInt.IsActive='Y' ");

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_ConfigInt"));
		
		if (whereClause != null)
			sql.append(" AND "+whereClause);
			
		sql.append(" ORDER BY EXME_ConfigInt.AD_Org_ID DESC ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()){
				retValue = new MEXMEConfigInt(ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
			rs = null;
			pstmt = null;
		}
		return retValue;
	}
}
