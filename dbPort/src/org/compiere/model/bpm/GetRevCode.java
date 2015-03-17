package org.compiere.model.bpm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEProductoOrg;
import org.compiere.model.X_EXME_ProductoOrg;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 
 * @author twry
 * 
 */
public class GetRevCode {
	/** Log */
	private static CLogger s_log = CLogger.getCLogger(GetRevCode.class);

//	/**
//	 * 
//	 * @param ctx
//	 * @param M_Product_ID
//	 * @param valid
//	 * @return
//	 */
//	public static MEXMEProductoOrg calcularRevCode(Properties ctx,
//			int M_Product_ID) {
//		if (M_Product_ID == 0)
//			return null;
//
//		MEXMEProductoOrg prodOrg = null;
//		//
//		StringBuilder sql = new StringBuilder(
//				" SELECT * FROM EXME_ProductoOrg ");
//		sql.append(" WHERE IsActive='Y' AND M_Product_ID = ? ");
//		sql.append(" AND AD_Org_ID = ? ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
//				X_EXME_ProductoOrg.Table_Name));
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, M_Product_ID);pstmt.setInt(2, Env.getAD_Org_ID(ctx));
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				prodOrg = new MEXMEProductoOrg(ctx, rs, null);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//			prodOrg = null;
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return prodOrg;
//	}
//	
	
//	/**
//	 * 
//	 * @param ctx
//	 * @param M_Product_ID
//	 * @param valid
//	 * @return
//	 */
//	public static MEXMEProductoOrg getProductoOrg(Properties ctx,
//			int M_Product_ID, boolean isActive) {
//		if (M_Product_ID == 0)
//			return null;
//
//		MEXMEProductoOrg precio = null;
//		//
//		StringBuilder sql = new StringBuilder(
//				" SELECT * FROM EXME_ProductoOrg ");
//		sql.append(" WHERE M_Product_ID = ? ");
//		sql.append(" AND AD_Org_ID = ? ");
//		if(isActive){
//			sql.append(" AND isActive='Y' ");
//		}
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
//				X_EXME_ProductoOrg.Table_Name));
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setInt(1, M_Product_ID);pstmt.setInt(2, Env.getAD_Org_ID(ctx));
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				precio = new MEXMEProductoOrg(ctx, rs, null);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//			precio = null;
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return precio;
//	}
}