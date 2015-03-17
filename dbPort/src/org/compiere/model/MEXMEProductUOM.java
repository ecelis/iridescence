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
import org.compiere.util.Env;

public class MEXMEProductUOM extends X_EXME_Product_UOM{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEProductUOM.class);
	
	public MEXMEProductUOM (Properties ctx, int EXME_Product_UOM_ID, String trxName)
	{
		super (ctx, EXME_Product_UOM_ID, trxName);
	}	//	MTaxCategory

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs resukt set
	 *	@param trxName trx
	 */
	public MEXMEProductUOM (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
	
	
	/**
	 *  Devuelve las unidades de medida asociadas al producto (C_UOM_Convertion)
	 *  seleccionado que este asociado al almaceen dado (M_Repenish).
	 *  (EXME_Product_UOM) <i>Nota: La udm del producto no sera agregada a la lista
	 *  si no esta definida en la tabla EXME_Product_UOM</i> .
	 *
	 *@param  producto    El producto.
	 *@param  almacen     Almacen del cual se toma el producto.
	 *@param  ctx         Description of the Parameter
	 *@return             Un valor List con las unidades de medida
	 *@throws  Exception  en 	caso de ocurrir un error al procesar la consulta.
	 */
	public static List<LabelValueBean> getProdUOM(Properties ctx, int M_Product_ID, 
			int M_Warehouse_ID, String trxName)
		throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(" SELECT uom.Name, EXME_Product_UOM.C_UOM_ID ")
		.append(" FROM EXME_Product_UOM  ")
		.append(" INNER JOIN C_UOM uom ON  uom.C_UOM_ID = EXME_Product_UOM.C_UOM_ID ")
		.append(" LEFT OUTER JOIN C_UOM_Trl uomTrl ON ( uom.C_UOM_ID = uomTrl.C_UOM_ID AND uomTrl.AD_Language='")
		.append(Env.getAD_Language(Env.getCtx())).append("') ")
		.append(" WHERE EXME_Product_UOM.IsActive = 'Y' ")
		.append(" AND EXME_Product_UOM.M_Product_ID = ? ");

		if(M_Warehouse_ID>0)
			   sql.append(" AND EXME_Product_UOM.M_Warehouse_ID = ?  ");
			   
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Product_UOM"))
		.append(" GROUP BY  EXME_Product_UOM.C_UOM_ID, uom.Name ");
		 
		List<LabelValueBean> lstUdm = new ArrayList<LabelValueBean>();

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			pstmt.setLong(1, M_Product_ID);
			if(M_Warehouse_ID>0)
				pstmt.setLong(2, M_Warehouse_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				LabelValueBean elemento = new LabelValueBean(rs.getString("Name"), String.valueOf(rs.getInt("C_UOM_ID")));
				lstUdm.add(elemento);
			}
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
    	}finally {
    		DB.close(rs, pstmt);
    	}


		return lstUdm;
	}
	
	/**
	 * Obtiene la unidad de medida de un producto en un almacen determinado
	 * @param ctx Contexto
	 * @param productId Prodcuto
	 * @param uomId Unidad de Medida
	 * @param warehouseId almacen
	 * @return MEXMEProductUOM
	 * @author mvrodriguez
	 */
	public static MEXMEProductUOM getProductUOM(Properties ctx, int productId, int warehouseId) {
    	
		MEXMEProductUOM productUom = null;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT * ");
		sql.append("   FROM EXME_PRODUCT_UOM ");
		sql.append("  WHERE EXME_PRODUCT_UOM.ISACTIVE = 'Y' ");
		sql.append("    AND EXME_PRODUCT_UOM.M_PRODUCT_ID = ? ");
		sql.append("    AND EXME_PRODUCT_UOM.M_WAREHOUSE_ID = ? ");
		
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"EXME_Product_UOM"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, warehouseId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				productUom = new MEXMEProductUOM(ctx, rs, null);
				
			}


		} catch (Exception e) {
			
			s_log.log(Level.SEVERE, sql.toString(), e.getMessage());
			
			
		} finally {
			DB.close(rs, pstmt);
		}

        return productUom;
    }
}
