/*

 * Created on 02-may-2005

 *

 */

package org.compiere.model;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;



/**
 * @author hassan reyes
 *
 */
public class MEXMEParentesco extends X_EXME_Parentesco {
	private static final long serialVersionUID = 1L;
    /**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEParentesco.class);

    /**
     * @param ctx
     * @param EXME_Parentesco_ID
     * @param trxName
     */
    public MEXMEParentesco(Properties ctx, int EXME_Parentesco_ID, String trxName) {
        super(ctx, EXME_Parentesco_ID, trxName);
    }

    /**
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MEXMEParentesco(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
     * Obtenemos los parentescos para una cliente + organizacion.
     * @param ctx
     * @param trxName
     * @param whereClause opcional
     * @param orderBy opcional
     * @return
     */
    public static MEXMEParentesco[] get(Properties ctx, String trxName, String whereClause, String orderBy){
    	List<MEXMEParentesco> list = getList(ctx, trxName, whereClause, orderBy);
//        ArrayList<MEXMEParentesco> list = new ArrayList<MEXMEParentesco>();
//		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append("SELECT * FROM EXME_Parentesco WHERE isActive = 'Y' ");
//
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//
//		if (whereClause != null)
//			sql.append(whereClause);
//
//		if(orderBy != null && orderBy.length() > 0)
//			sql.append( " ORDER BY ").append(orderBy);
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//			    MEXMEParentesco parentesco = new MEXMEParentesco(ctx, rs, trxName);
//				list.add(parentesco);
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, "get", e);
//		} finally {
//			DB.close(rs,pstmt);
//		}
		//
		MEXMEParentesco[] parentescos = new MEXMEParentesco[list.size()];
		list.toArray(parentescos);
		return parentescos;
    }

    /**
     * Obtenemos los parentescos para una cliente + organizacion.
     * @param ctx
     * @param trxName
     * @param whereClause opcional
     * @param orderBy opcional
     * @return
     */
    public static List<MEXMEParentesco> getList(Properties ctx, String trxName, String whereClause, String orderBy){
        
        ArrayList<MEXMEParentesco> list = new ArrayList<MEXMEParentesco>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_Parentesco WHERE isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		if (whereClause != null) {
			sql.append(whereClause);
		}
		if(StringUtils.isNotBlank(orderBy)) {
			sql.append( " ORDER BY ").append(orderBy);
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MEXMEParentesco(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "get", e);
		} finally {
			DB.close(rs,pstmt);
		}
		return list;
    }
    /**
     * Busca toda los parantescos dispobibles
     * 
     * @param ctx
     * @param trxName
     * @param whereClause
     * @param orderBy
     * @return List de LabelValueBean<Name,ID>  con los parentescos
     */
    public static List<LabelValueBean> getAll(Properties ctx, String trxName, String whereClause, String orderBy){
    	final List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
    	final StringBuilder sql = new StringBuilder("SELECT NAME, EXME_Parentesco_ID FROM EXME_Parentesco WHERE isActive = 'Y' ");
    	sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

		if (whereClause != null)
			sql.append(" ").append(whereClause);

		if(orderBy != null && orderBy.length() > 0)
			sql.append(" ORDER BY ").append(orderBy);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				lista.add(new LabelValueBean(rs.getString("NAME"), rs.getString("EXME_Parentesco_ID")));
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
    
    /**
     * Obtenemos los parentescos para una cliente + organizacion.
     * @param ctx
     * @param trxName
     * @param whereClause opcional
     * @param orderBy opcional
     * @return
     */
    public static MEXMEParentesco get(Properties ctx, String value, String trxName){
        
        MEXMEParentesco parentesco = null;
        if (StringUtils.isNotEmpty(value)){
        	
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT * FROM EXME_Parentesco WHERE isActive = 'Y' AND VALUE = ?");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), trxName);
				pstmt.setString(1, value);
				rs = pstmt.executeQuery();
				while (rs.next()) {
				    parentesco = new MEXMEParentesco(ctx, rs, trxName);;
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "get MEXMEParentesco", e);
			} finally {
				DB.close(rs,pstmt);
			}
        }
		
		return parentesco;
    }

	/**
	 * Obtenemos los parentescos para una cliente + organizacion.
	 * 
	 * @param ctx
	 * @param trxName
	 * @param whereClause opcional
	 * @param orderBy opcional
	 * @return
	 */
	public static Integer[] getForNewborns(Properties ctx) {
		final List<MEXMEParentesco> kinship = MEXMEParentesco.getList(ctx, null, " and value in ('MTH','NCH','CHD')", null);
		final Integer[] ids = new Integer[kinship.size()];
		for (int i = 0; i < kinship.size(); i++) {
			ids[i] = kinship.get(i).getEXME_Parentesco_ID();
		}
		return ids;
	}
    
    
   
}