package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEDieta extends X_EXME_Dieta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MEXMEDieta.class);

	public MEXMEDieta(Properties ctx, int EXME_Dieta_ID, String trxName) {
		super(ctx, EXME_Dieta_ID, trxName);
	}

	public MEXMEDieta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

//	public static MEXMEDieta[] get(Properties ctx, String trxName) {
//
//		if (ctx == null) {
//			return null;
//		}
//
//		MEXMEDieta[] retValue = null;
//		ArrayList<MEXMEDieta> list = new ArrayList<MEXMEDieta>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//	    sql.append (" SELECT EXME_Dieta.* ")
//	       .append (" FROM EXME_Dieta WHERE EXME_Dieta.IsActive='Y' ");
//
//		sql = new StringBuilder (MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Dieta"));
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MEXMEDieta dieta = new MEXMEDieta(ctx, rs, trxName);
//				list.add(dieta);
//			}
//			
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//
//		//
//		retValue = new MEXMEDieta[list.size()];
//		list.toArray(retValue);
//
//		return retValue;
//	}

//	public static MEXMEDieta get(Properties ctx, String value, String trxName) {
//
//		if (ctx == null) {
//			return null;
//		}
//
//		MEXMEDieta retValue = null;
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append (" SELECT EXME_Dieta.* ")
//		   .append (" FROM EXME_Dieta WHERE EXME_Dieta.IsActive='Y' ")
//		   .append (" AND TRIM(EXME_Dieta.Value) = '").append(value).append("' ");
//
//		sql = new StringBuilder (MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Dieta"));
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//			if (rs.next())
//				retValue = new MEXMEDieta(ctx, rs, trxName);
//			
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return retValue;
//	}

//	public static MEXMEDieta getDieta(Properties ctx, int ctapac, String trxName) {
//
//		if (ctx == null) {
//			return null;
//		}
//
//		MEXMEDieta retValue = null;
//		String sql = " select EXME_dieta.* "
//				+ " FROM EXME_CtaPacDieta cd "
//				+ " INNER JOIN EXME_dieta  ON (EXME_dieta.exme_dieta_id = cd.exme_dieta_id)  "
//				+ " Where Exme_Dieta.IsActive = 'Y' "
//				+ " and cd.IsActive = 'Y' " + " and cd.exme_ctapac_id = "
//				+ ctapac;
//
//		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_Dieta");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql, trxName);
//			rs = pstmt.executeQuery();
//			if (rs.next())
//				retValue = new MEXMEDieta(ctx, rs, trxName);
//			
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "getDieta", e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return retValue;
//	}

//	public static List<MEXMEDieta> getDietas(Properties ctx, String saWhere, String trxName) {
//		StringBuilder sql = new StringBuilder(25);
//		if (ctx == null) {
//			return null;
//		}
//
//		ArrayList<MEXMEDieta> list = new ArrayList<MEXMEDieta>();
//
//		sql.append(" SELECT EXME_Dieta.* ");
//		sql.append(" FROM EXME_Dieta WHERE EXME_Dieta.IsActive='Y' ");
//
//		if (saWhere != null && !saWhere.trim().equals("")) {
//			sql.append(saWhere);
//		}
//
//		String sqlFinal = MEXMELookupInfo.addAccessLevelSQL(ctx,
//				sql.toString(), "EXME_Dieta");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sqlFinal, trxName);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				MEXMEDieta dieta = new MEXMEDieta(ctx, rs, trxName);
//				list.add(dieta);
//			}
//			
//
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		} finally {
//			DB.close(rs, pstmt);
//			rs = null;
//			pstmt = null;
//		}
//		return list;
//	}
	public static List<LabelValueBean> getDietasList(Properties ctx, String trxName) {
		return getDietasList(ctx, false, trxName);
	}
	
	public static List<LabelValueBean> getDietasList(Properties ctx, boolean subItem, String trxName) {

		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    sql.append (" SELECT EXME_Dieta_ID, SUBSTR(Name,1,30) AS Name ");
	    sql.append (" FROM EXME_Dieta WHERE EXME_Dieta.IsActive='Y' ");
	    sql.append (MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		sql.append(" AND COALESCE(IsSubDiet,'N')=? ");
		sql.append(" ORDER BY NAME ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, DB.TO_STRING(subItem));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LabelValueBean valor = new LabelValueBean(rs.getString("Name"),rs.getString("EXME_Dieta_ID") );
				lista.add(valor);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return lista;
	}

}
