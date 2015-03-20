package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;

/**
 * @author hassan reyes
 *
 */
public class MEXMELookupInfo extends MLookupInfo {

    
    /** serialVersionUID */
	private static final long serialVersionUID = -1199579236095273478L;
	/**	Logging								*/
	private static CLogger		s_log = CLogger.getCLogger(MEXMELookupInfo.class);
	
    /**
     * @param sqlQuery
     * @param tableName
     * @param keyColumn
     * @param zoomWindow
     * @param zoomWindowPO
     * @param zoomQuery
     */
	public MEXMELookupInfo(String sqlQuery, String tableName, String keyColumn, int zoomWindow, int zoomWindowPO, MQuery zoomQuery) {
		super(sqlQuery, tableName, keyColumn, zoomWindow, zoomWindowPO, zoomQuery);
	}
    
    /**
     * Obtenemos un VN Pair del tipo de referencia de Table con la columna Llave y la columna a Desplegar.
     * @param ctx
     * @param AD_Reference_ID Este debe ser de tipo VALIDATIONTYPE = "T"
     * @return
     */
	public static ValueNamePair getLookup_Table(Properties ctx, int AD_Reference_ID, String trxName) {
		ValueNamePair pair = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement("SELECT AD_Key, AD_Display FROM AD_Ref_Table WHERE AD_Reference_ID=?", trxName);
			pstmt.setInt(1, AD_Reference_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// key , Display
				pair = new ValueNamePair(MColumn.getColumnName(rs.getInt(1)), MColumn.getColumnName(rs.getInt(2)));
			}
		}
		catch (SQLException e) {
			s_log.log(Level.SEVERE, "getLookup_Table", e);
		}
		finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return pair;
	}
    
    /**
     * 
     * @param AD_Column_ID
     * @return
     *
    public static String getColumnName(int AD_Column_ID, String trxName){
//        String retVal = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        String sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID = ? ";
//        
//        try
//		{
//			pstmt = DB.prepareStatement(sql, trxName);
//			pstmt.setInt(1, AD_ColumnName_ID);
//			rs = pstmt.executeQuery();
//			if (rs.next())
//			{
//				retVal = rs.getString(1);
//				
//			}
//		}
//		catch (SQLException e){
//			s_log.log(Level.SEVERE, "getColumnName", e);
//        }finally {
//        	DB.close(rs,pstmt);
//            pstmt=null;
//            rs=null;
//		}
//		return retVal;
    }*/
    
    /**
     * Obtenemos la clausula Where de una referencia de Table o Search.
     * @return
     */
    public static String getWhereClause(Properties ctx, int AD_Reference_ID, int WindowNo){
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
        // //	1,2 ,rt.OrderByClause
		String sql0 = "SELECT rt.WhereClause FROM AD_Ref_Table rt WHERE rt.AD_Reference_ID=? AND rt.IsActive='Y' ";
		//
		String	whereClause = DB.getSQLValueString(null, sql0, AD_Reference_ID);
		boolean loaded = StringUtils.isNotBlank(whereClause);
//		try
//		{
//			pstmt = DB.prepareStatement(sql0, null);
//			pstmt.setInt(1, AD_Reference_ID);
//			rs = pstmt.executeQuery();
//			if (rs.next())
//			{
//				WhereClause = rs.getString(1);
//				//OrderByClause = rs.getString(2);
//				loaded = true;
//			}
//		}
//		catch (SQLException e)
//		{
//			s_log.log(Level.SEVERE, "getWhereClause", e);
//			return null;
//        }finally {
//        	DB.close(rs,pstmt);
//            pstmt=null;
//            rs=null;
//		}
		if (!loaded)
		{
			s_log.log(Level.SEVERE, "No Table Reference Table ID=" + AD_Reference_ID);
			return null;
		}
		String where = "";
		if (whereClause != null)
		{
			where = whereClause;
			if (where.indexOf("@") != -1){
				where = Env.parseContext(ctx, WindowNo, where, false);
			}
			if (where.length() == 0 && whereClause.length() != 0){
				s_log.severe ("Could not resolve: " + whereClause);
			}
		}
		return where;
    }

    /**
     * Obtenemos la clausula OrderBy de una referencia de Table o Search.
     * @return
     */
    public static String getOrderByClause(Properties ctx, int AD_Reference_ID, int WindowNo){
//        String orderBy = "";
        ////	1,2 ,rt.WhereClause 
        String sql0 = "SELECT rt.OrderByClause FROM AD_Ref_Table rt WHERE rt.AD_Reference_ID=? AND rt.IsActive='Y' ";
		//
		String	orderByClause = DB.getSQLValueString(null, sql0, AD_Reference_ID);
		boolean loaded = StringUtils.isNotBlank(orderByClause);
        
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql0, null);
//			pstmt.setInt(1, AD_Reference_ID);
//			rs = pstmt.executeQuery();
//			if (rs.next())
//			{
//				//WhereClause = rs.getString(1);
//				orderByClause = rs.getString(2);
//				loaded = true;
//			}
//		}
//		catch (SQLException e){
//			s_log.log(Level.SEVERE, "getOrderByClause", e);
//			return null;
//        }finally {
//            DB.close(rs, pstmt);
//		}
		if (!loaded) {
			s_log.log(Level.SEVERE, "No Table Reference Table ID=" + AD_Reference_ID);
			return null;
		}
		if(orderByClause != null && orderByClause.indexOf(".") == -1) {
			s_log.log(Level.SEVERE, "ORDER BY must fully qualified: " + orderByClause);
		}
//		orderBy = orderByClause;
		return orderByClause;
		
    }
    
    /**
     * 
     * @param ctx
     * @param originalSQL Debe contener almenos una condicion en el WHERE. los campos de la tabla principal deben ser Tabla.Campo
     * @param AD_TAble_ID
     * @return
     */
    public static String addAccessLevelSQL(Properties ctx, String originalSQL, int AD_Table_ID){
		return addAccessLevelSQL(ctx, originalSQL, AD_Table_ID, null);
    }
    
    /**
     * 
     * @param ctx
     * @param originalSQL Debe contener almenos una condicion en el WHERE. los campos de la tabla principal deben ser Tabla.Campo
     * @param AD_TAble_ID
     * @param alias alias de la tabla p.e: M_product p
     * @return
     */
    public static String addAccessLevelSQL(Properties ctx, String originalSQL, int AD_Table_ID, String alias){
    	return addAccessLevelSQL(ctx, originalSQL, POInfo.getPOInfo(ctx, AD_Table_ID), alias);
    }
    
    /**
     * 
     * @param ctx
     * @param sql Debe contener almenos una condicion en el WHERE. los campos de la tabla principal deben ser Tabla.Campo
     * @param tableName Nombre completo de la tabla.
     * @return
     */
    public static String addAccessLevelSQL(Properties ctx, String originalSQL, String tableName){
        return addAccessLevelSQL(ctx, originalSQL, tableName, null);
    }
    
    /**
     * 
     * @param ctx
     * @param sql Debe contener almenos una condicion en el WHERE. los campos de la tabla principal deben ser Tabla.Campo
     * @param tableName Nombre completo de la tabla.
     * @return
     */
    public static String addAccessLevelSQL(Properties ctx, String originalSQL, String tableName, String alias){
        return addAccessLevelSQL(ctx, originalSQL, MTable.getTable_ID(tableName), alias);
    }
    
    /**
     * 
     * @param ctx
     * @param originalSQL Debe contener almenos una condicion en el WHERE. los campos de la tabla principal deben ser Tabla.Campo
     * @param AD_TAble_ID
     * @param alias de la tabla
     * @return
     */
    public static String addAccessLevelSQL(Properties ctx, String originalSQL, POInfo poInfo, String alias){
    	return addAccessLevelSQL(ctx, originalSQL, poInfo, alias, -1);
    }
    
    /**
     * Se realizo la sobrecarga del metodo agregando la organizacion
     * @param ctx
     * @param originalSQL Debe contener almenos una condicion en el WHERE. los campos de la tabla principal deben ser Tabla.Campo
     * @param poInfo
     * @param alias de la tabla
     * @param org Organizacion por la que se filtra
     * @author mvrodriguez 
     */
    public static String addAccessLevelSQL(Properties ctx, String originalSQL, POInfo poInfo, String alias, int org){
		if (originalSQL == null || originalSQL.length() <= 0) {
			return null;
		}

		final StringBuffer sql = new StringBuffer(originalSQL);
		if (Env.getUserPatientID(ctx) <= 0) {
			if (!originalSQL.trim().toUpperCase().endsWith("WHERE")) {
				sql.append(" AND ");
			}

			// Agregamos los filtros de nivel de acceso
			int AD_Client_ID = Env.getAD_Client_ID(ctx);
			int AD_Org_ID = Env.getAD_Org_ID(ctx);
			String tableName = poInfo.getTableName();
			if (StringUtils.isNotBlank(alias)) {
				tableName = alias;
			}

    		//Si org es -1 se sigue el proceso original
    		if(org == -1) {
    			
    			if (MTable.ACCESSLEVEL_ClientOnly.equals(poInfo.getAccessLevel())) {
        			sql.append(tableName).append(".AD_Client_ID = ").append(AD_Client_ID);
        		} else if (MTable.ACCESSLEVEL_ClientPlusOrganization.equals(poInfo.getAccessLevel())) {
        			sql.append(tableName).append(".AD_Client_ID = ").append(AD_Client_ID).append(" AND ").append(tableName).append(".AD_Org_ID IN (0,").append(AD_Org_ID).append(")");
        		} else if (MTable.ACCESSLEVEL_Organization.equals(poInfo.getAccessLevel())) {
        			sql.append(tableName).append(".AD_Client_ID = ").append(AD_Client_ID).append(" AND ").append(tableName).append(".AD_Org_ID = ").append(AD_Org_ID);
        		} else if (MTable.ACCESSLEVEL_SystemOnly.equals(poInfo.getAccessLevel()) || MTable.ACCESSLEVEL_ClientToSystem.equals(poInfo.getAccessLevel())) {
        			sql.append(tableName).append(".AD_Client_ID = 0");
        		} else if (MTable.ACCESSLEVEL_SystemPlusClient.equals(poInfo.getAccessLevel())) {
        			sql.append(tableName).append(".AD_Client_ID IN (0,").append(AD_Client_ID).append(")");
        		} else if (MTable.ACCESSLEVEL_All.equals(poInfo.getAccessLevel())) {
        			sql.append(tableName).append(".AD_Client_ID IN (0,").append(AD_Client_ID).append(")").append(" AND ").append(tableName).append(".AD_Org_ID IN (0,").append(AD_Org_ID).append(")");
        		} else {
        			sql.append(tableName).append(".AD_Client_ID = ").append(AD_Client_ID).append(" ");
        		}
    			
			} else {
				// En caso contrario si viene con una organizacion se filtra por ella
				if (org > 0) {
					sql.append(tableName).append(".AD_Org_ID = ").append(org).append(" ");
				} else {
					sql.append(" 1 = 1").append(" ");
				}
				// En caso contrario se trae toda la infromacion sin restriccion
			}

    	}
    	return sql.toString();

    }
    
    /**
	 * Obtenemos el ID de la tabla a partir del nombre de la misma en la BD.
	 *
	public static int getTableID(String dbTableName){
	    int AD_Table_ID = DB;
	    StringBuffer sql = new StringBuffer();
	    sql.append("SELECT AD_Table_ID FROM AD_Table WHERE TableName = ?");
	    /*.append(dbTableName)
	    .append("'");*
        PreparedStatement pstmt = null;
        ResultSet rs 			= null;
	    try {
	    	//aaranda@expert: Se elimino transaccion en consulta.
			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setString(1, dbTableName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
			    AD_Table_ID = rs.getInt(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "", e);
        }finally {
        	DB.close(rs,pstmt);
		}
		return AD_Table_ID;
	}*/
}
