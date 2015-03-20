/**
 * 
 */
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
import org.compiere.util.KeyNamePair;

import com.ecaresoft.api.Generic;

/**
 * @author Expert
 * 
 */
public class MEXMEProductClassWhs extends X_EXME_ProductClassWhs {

	/** serialVersionUID */
	private static final long serialVersionUID = 1578538181419495156L;
	private static CLogger log = CLogger.getCLogger(MEXMEProductClassWhs.class);

	/**
	 * @param ctx
	 * @param EXME_ProductClassWhs_ID
	 * @param trxName
	 */
	public MEXMEProductClassWhs(Properties ctx, int EXME_ProductClassWhs_ID,
			String trxName) {
		super(ctx, EXME_ProductClassWhs_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEProductClassWhs(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
    /**
     * Obtiene lista de almacenes ordenados por su nombre
     * @param ctx
     * @param trxName
     * @return
     *
    public static List<LabelValueBean> getAllProdClass(Properties ctx, String prodclass, String trxName){
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;
		
		sql.append(" SELECT w.Name, pcw.M_Warehouse_ID  ")
		.append(" FROM EXME_ProductClassWhs pcw ")
		.append(" INNER JOIN M_Warehouse w ON pcw.M_Warehouse_ID = w.M_Warehouse_ID ")
		.append(" WHERE pcw.IsActive = 'Y' ")	
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pcw"))
		.append(" AND TRIM(pcw.ProductClass) = ? ")
		.append(" ORDER BY w.Name ");
		
		PreparedStatement pstmt = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);   
        	pstmt.setString(1, prodclass);
        	rs = pstmt.executeQuery();        	
        	while(rs.next()){
        		LabelValueBean res = new LabelValueBean(rs.getString("Name"), rs.getString("M_Warehouse_ID"));
        		lista.add(res);
        	}
        } catch (Exception e) {
			lista = null;
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	 /**
     * Obtiene lista de almacenes ordenados por su nombre
     * @param ctx
     * @param trxName
     * @return
     *
    public static List<LabelValueBean> getAllProdClass(Properties ctx, String prodclass, String trxName){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" SELECT w.Name, pcw.M_Warehouse_ID  ")
		.append(" FROM EXME_ProductClassWhs pcw ")
		.append(" INNER JOIN M_Warehouse w ON pcw.M_Warehouse_ID = w.M_Warehouse_ID ")
		.append(" WHERE pcw.IsActive = 'Y' ")	
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pcw"))
		.append(" AND TRIM(pcw.ProductClass) = ? ")
		.append(" ORDER BY w.Name ");
		
		params.add(prodclass);
		return MEXMEProductClassWhs.getAllProdClass(ctx, sql.toString(), trxName, params);
	}
    
    public static List<LabelValueBean> getProdClassServ(Properties ctx, String trxName){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" SELECT w.Name, pcw.M_Warehouse_ID  ")
		.append(" FROM EXME_ProductClassWhs pcw ")
		.append(" INNER JOIN M_Warehouse w ON pcw.M_Warehouse_ID = w.M_Warehouse_ID ")
		.append(" WHERE pcw.IsActive = 'Y' ")	
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pcw"))
		.append(" AND TRIM(pcw.ProductClass) IN (? ");
		params.add(X_EXME_ProductClassWhs.PRODUCTCLASS_Laboratory);
		sql.append(",?");
		params.add(X_EXME_ProductClassWhs.PRODUCTCLASS_XRay);
		sql.append(",?");
		params.add(X_EXME_ProductClassWhs.PRODUCTCLASS_Procedures);
		sql.append(",?");
		params.add(X_EXME_ProductClassWhs.PRODUCTCLASS_MR);
		sql.append(",?");
		params.add(X_EXME_ProductClassWhs.PRODUCTCLASS_CT);
		sql.append(",?");
		params.add(X_EXME_ProductClassWhs.PRODUCTCLASS_NM);
		sql.append(",?");
		params.add(X_EXME_ProductClassWhs.PRODUCTCLASS_PT);
		sql.append(") ORDER BY w.Name ");

		return MEXMEProductClassWhs.getAllProdClass(ctx, sql.toString(), trxName, params);
	}
    
    /**
     * Obtiene lista de almacenes ordenados por su nombre
     * @param ctx
     * @param trxName
     * @return
     *
    public static List<LabelValueBean> getAllProdClass(Properties ctx, String sql, String trxName, List<?> params){
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);   
        	DB.setParameters(pstmt, params);
        	
        	rs = pstmt.executeQuery();        	
        	while(rs.next()){
        		LabelValueBean res = new LabelValueBean(rs.getString("Name"), rs.getString("M_Warehouse_ID"));
        		lista.add(res);
        	}
        } catch (Exception e) {
			lista = null;
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
*/
	/**
	 * Obtiene lista de almacenes ordenados por su nombre
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @deprecated use getAllProdClassList
	 */
	public static List<LabelValueBean> getAllProdClass(Properties ctx,
			String prodclass, Boolean access, String trxName) {
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;

		sql.append(" SELECT w.Name, pcw.M_Warehouse_ID  ");
		sql.append(" FROM EXME_ProductClassWhs pcw ");
		sql.append(" INNER JOIN M_Warehouse w ON pcw.M_Warehouse_ID = w.M_Warehouse_ID ");
		sql.append(" WHERE pcw.IsActive = 'Y' ");
		if (access == true) {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pcw"));
		}
		sql.append(" AND TRIM(pcw.ProductClass) = ? ");
		sql.append(" ORDER BY w.Name ");


		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, prodclass);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LabelValueBean res = new LabelValueBean(rs.getString("Name"),
						rs.getString("M_Warehouse_ID"));
				lista.add(res);
			}
		} catch (Exception e) {
			lista = null;
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}
	
	
	/**
	 * Obtiene lista de almacenes ordenados por su nombre
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<KeyNamePair> getAllProdClassList(Properties ctx, String prodclass, Boolean access, String trxName) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT pcw.M_Warehouse_ID, w.Name  ");
		sql.append(" FROM EXME_ProductClassWhs pcw ");
		sql.append(" INNER JOIN M_Warehouse w ON pcw.M_Warehouse_ID = w.M_Warehouse_ID ");
		sql.append(" WHERE pcw.IsActive = 'Y' ");
		if (access == true) {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pcw"));
		}
		sql.append(" AND TRIM(pcw.ProductClass) = ? ");
		sql.append(" ORDER BY w.Name ");

		return Query.getListKN(sql.toString(), trxName, prodclass);
	}
	
	/**
	 * Obtiene lista de almacenes ordenados por su nombre
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 * @deprecated use {@link #getAllProdClassList(Properties, String, Boolean, String)}
	 */
	public static List<Generic> getAllProdClassGeneric(Properties ctx,
			String prodclass, Boolean access, String trxName) {
		List<Generic> lista = new ArrayList<Generic>();
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;

		sql.append(" SELECT w.Name, pcw.M_Warehouse_ID  ");
		sql.append(" FROM EXME_ProductClassWhs pcw ");
		sql.append(" INNER JOIN M_Warehouse w ON pcw.M_Warehouse_ID = w.M_Warehouse_ID ");
		sql.append(" WHERE pcw.IsActive = 'Y' ");
		if (access == true) {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pcw"));
		}
		sql.append(" AND TRIM(pcw.ProductClass) = ? ");
		sql.append(" ORDER BY w.Name ");


		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, prodclass);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Generic res = new Generic(rs.getString("Name"),
						rs.getString("M_Warehouse_ID"));
				lista.add(res);
			}
		} catch (Exception e) {
			lista = null;
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Obtiene lista de almacenes ordenados por su nombre
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MWarehouse> getAllWarehouseProdClass(final Properties ctx,
			final String prodclass, final String trxName) {
		List<MWarehouse> lista = new ArrayList<MWarehouse>();
		final StringBuilder sql = new StringBuilder();
		ResultSet rs = null;

		sql
				.append(" SELECT w.*, pcw.EXME_ProductClassWhs_ID AS pcwID ")
				.append(" FROM M_Warehouse w  ")
				.append(
						" INNER JOIN EXME_ProductClassWhs pcw ON pcw.M_Warehouse_ID = w.M_Warehouse_ID AND pcw.IsActive = 'Y' AND pcw.ProductClass = ? ")
				.append(" WHERE w.IsActive = 'Y' ").append(
						MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
								MWarehouse.Table_Name, "w")).append(
						" ORDER BY w.Name ");

		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, prodclass);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MWarehouse res = new MWarehouse(ctx, rs, trxName);
				res.setProductClassWhsID(rs.getInt("pcwID"));
				res.setSelected(rs.getInt("pcwID") > 0);
				lista.add(res);
			}
		} catch (Exception e) {
			lista = null;
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * getAllByClass
	 * @param ctx
	 * @param prodclass
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEProductClassWhs> getAllByClass(Properties ctx,
			String prodclass, String trxName) {
		List<MEXMEProductClassWhs> lista = new ArrayList<MEXMEProductClassWhs>();
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;

		sql.append(" SELECT pcw.*  ");
		sql.append(" FROM EXME_ProductClassWhs pcw ");
		sql.append(" WHERE pcw.IsActive = 'Y' ");
		sql.append(
				MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name,
				"pcw"));
		
		if(prodclass!=null){
			sql.append(" AND TRIM(pcw.ProductClass) = ? ");
		}
		
		sql.append(" ORDER BY pcw.ProductClass ");

		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			if(prodclass!=null){
				pstmt.setString(1, prodclass);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMEProductClassWhs res = new MEXMEProductClassWhs(
						ctx, rs, null);
				lista.add(res);
			}
		} catch (Exception e) {
			lista = null;
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Valida si el producto pertenece al almacen
	 * 
	 * @param ctx
	 * @param warehouse
	 * @param product
	 * @param trxName
	 * @return
	 */
	public static boolean validProductWarehouse(Properties ctx, int warehouse,
			int product, String trxName) {
		boolean lista = false;
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;

		sql
				.append(" SELECT p.M_Product_ID  ")
				.append(" FROM M_Product p ")
				.append(
						" INNER JOIN EXME_ProductClassWhs pcw ON pcw.ProductClass = p.ProductClass AND pcw.IsActive = 'Y' ")
				.append(
						" INNER JOIN M_Warehouse            w ON pcw.M_Warehouse_ID = w.M_Warehouse_ID AND w.IsActive = 'Y' ")

				.append(" WHERE p.IsActive = 'Y' ").append(
						MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
								"EXME_ProductClassWhs", "pcw"))// nivel org
				.append(" AND w.M_Warehouse_ID = ? ").append(
						" AND p.M_Product_ID = ? ");

		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, warehouse);
			pstmt.setInt(1, product);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lista = true;
			}
		} catch (Exception e) {
			lista = false;
			log.log(Level.SEVERE, "validProductWarehouse (" + sql + ")", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return lista;
	}

	/**
	 * Obtenemos los almacenes a los que le puede pedir el almacen solicitante (almacen resurtido)
	 * y que sean almacenes con clase de producto Drug, Medical Supplies y/o Instrumental
	 * 
	 * @param ctx
	 * @param almacen
	 *            el almacen origen
	 * @return Una lista con los almacenes a los que les puede pedir
	 * 
	 */
 	public static List<LabelValueBean> getAlmacenResurtido(Properties ctx, boolean medicalSupplies, String trxName) {
 		final List<LabelValueBean> almaResurt = new ArrayList<LabelValueBean>();
 		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
 		
 		sql.append("SELECT pcw.M_Warehouse_ID, w.Name ")
 		.append("FROM EXME_ProductClassWhs pcw ")
 		.append("LEFT JOIN M_Warehouse w ON (w.M_Warehouse_ID = pcw.M_Warehouse_ID) ")
 		.append("WHERE pcw.IsActive = 'Y' AND w.IsActive  = 'Y' ");
 		if (medicalSupplies) {
			sql.append("AND pcw.ProductClass IN ('DG', 'MS', 'IN') ");
		}else {
			sql.append("AND pcw.ProductClass IN ('DG', 'MS') ");
		}
 		
 		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "pcw"))
 		.append(" ORDER BY w.Name ");
         
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try {
 			pstmt = DB.prepareStatement(sql.toString(), null);
 			rs = pstmt.executeQuery();

 			while (rs.next()) {
 				final LabelValueBean combo = new LabelValueBean(rs.getString("Name"), rs.getString("M_Warehouse_ID"));
 				almaResurt.add(combo);
 			}

 		} catch (Exception e) {
 			log.log(Level.SEVERE, "getAlmacenResurtido: ", e);
 		} finally {
 			DB.close(rs, pstmt);
 			rs = null;
 			pstmt = null;
 		}
         return almaResurt;
     } 	

}
