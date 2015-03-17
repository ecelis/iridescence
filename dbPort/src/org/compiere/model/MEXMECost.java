package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo para Costos (extiende de MCost)
 *
 * <b>Fecha:</b> 20/Marzo/2006<p>
 * <b>Modificado: </b> $Author: gisela $<p>
 * <b>En :</b> $Date: 2006/03/20 18:31:20 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.1 $
 */
public class MEXMECost {
//	extends MCost{
//    
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
	/** Logger  */
    private static CLogger  s_log = CLogger.getCLogger (MEXMECost.class);
    
//    /**************************************************************************
//     *  Standard Constructor
//     *  @param ctx context
//     *  @param ignored multi-key
//     *  @param trxName trx
//     */
//    public MEXMECost(Properties ctx, int ignored, String trxName) {
//		super(ctx, ignored, trxName);
//	} //  MCost

//    /**
//     *  Load Constructor
//     *  @param ctx context
//     *  @param rs result set
//     *  @param trxName trx
//     */
//    public MEXMECost(Properties ctx, ResultSet rs, String trxName) {
//		super(ctx, rs, trxName);
//	} //  MCost

        
//    /**
//     *  Get/Create Cost Record.
//     *  CostingLevel is not validated
//     *  @param product product
//     *  @param M_AttributeSetInstance_ID costing level asi
//     *  @param as accounting schema
//     *  @param AD_Org_ID costing level org
//     *  @param M_CostElement_ID element
//     *  @return cost price or null
//     */
//    public static MCost get(MProduct product, MAcctSchema as, int M_CostElement_ID) {
//    	MCost cost = null;
//		String sql = "SELECT * FROM M_Cost WHERE IsActive = 'Y' AND M_Product_ID=? " //AD_Client_ID=? "
//				+ " AND M_CostType_ID=? AND C_AcctSchema_ID=? AND M_CostElement_ID=?";
//
//		sql = MEXMELookupInfo.addAccessLevelSQL(product.getCtx(), sql.toString(), "M_Cost");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql, product.get_TrxName());
//			//pstmt.setInt (1, product.getAD_Client_ID());
//			pstmt.setInt(1, product.getM_Product_ID());
//			pstmt.setInt(2, as.getM_CostType_ID());
//			pstmt.setInt(3, as.getC_AcctSchema_ID());
//			pstmt.setInt(4, M_CostElement_ID);
//			rs = pstmt.executeQuery();
//			if (rs.next())
//				cost = new MCost(product.getCtx(), rs, product.get_TrxName());
//			
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//        return cost;
//    }   //  get    

    /**
     *  Get/Create Cost Record.
     *  CostingLevel is not validated
     *  @param product product
     *  @param M_AttributeSetInstance_ID costing level asi
     *  @param as accounting schema
     *  @param AD_Org_ID costing level org
     *  @param M_CostElement_ID element
     *  @return cost price or null
     */
    public static MCost get(Properties ctx, MProduct product, String costingMethod,
    		MAcctSchema as, String trxName) {
    	
    	if(as==null)
    		as = new MAcctSchema(ctx, Env.getContextAsInt(ctx, "$C_AcctSchema_ID"), trxName);
    	MCost cost = null;
        String sql = "SELECT M_Cost.* FROM M_Cost INNER JOIN M_CostElement ce "
            + " ON (M_Cost.M_CostElement_ID = ce.M_CostElement_ID) "
            + " WHERE M_Cost.IsActive = 'Y' AND M_Cost.M_Product_ID=? AND M_Cost.M_CostType_ID=? " //c.AD_Client_ID=? AND c.AD_Org_ID=? "
            + " AND M_Cost.C_AcctSchema_ID=? AND ce.CostingMethod=? AND M_Cost.M_AttributeSetInstance_ID=?";     
        
        sql = MEXMELookupInfo.addAccessLevelSQL(product.getCtx(), sql.toString(), "M_Cost");
        
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = DB.prepareStatement(sql, trxName);
			//pstmt.setInt (1, Env.getAD_Client_ID(ctx));
			//pstmt.setInt (2, Env.getAD_Org_ID(ctx));
			pstmt.setInt(1, product.getM_Product_ID());
			pstmt.setInt(2, as.getM_CostType_ID());
			pstmt.setInt(3, as.getC_AcctSchema_ID());
			pstmt.setString(4, costingMethod);
			pstmt.setInt(5, product.getM_AttributeSetInstance_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				cost = new MCost(product.getCtx(), rs, product.get_TrxName());
			

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return cost;
	} //  get    
    
    /**
     * Obtiene el costo de un producto
     * @param ctx contecto
     * @param productId Id del producto sobre el cual se requiere el Costo
     * @param costingMethods metodos de costeo para filtrar
     * @return MEXMECost
     * @author mvrodriguez
     */
    public static List<MCost> getCostProduct(Properties ctx, int productId, String trxName, String...costingMethods) {
    	
    	List<MCost> costProduct = new ArrayList<MCost>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT * ")
		.append(" FROM M_COST ")
		//sql.append("  WHERE M_COST.ISACTIVE = 'Y' ");
		.append(" LEFT JOIN M_CostElement ele on M_COST.m_costelement_id = ele.m_costelement_id ")
		.append(" WHERE M_COST.M_PRODUCT_ID = ? ");
		
		for (int i = 1; i <= costingMethods.length; i++) {
			if(i==1){
				sql.append(" AND ( costingmethod = ? ");
			}else{
				sql.append(" OR costingmethod = ? ");
				if(i==costingMethods.length){
					sql.append(" )");
				}
			}
		}

		//ETS 05053 Maestro de productos II muestra costos de otroa clientes.
		//Se descomenta linea que da el nivel de acceso por cliente
		//Jesus Cantu el 17 Mayo 2013
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"M_Cost"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int position=1;
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(position++, productId);
			for (int i = 0; i < costingMethods.length; i++) {
				pstmt.setString(position++, costingMethods[i]);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {				
				costProduct.add(new MCost(ctx, rs, null));
			}

		} catch (Exception e) {			
			s_log.log(Level.SEVERE, sql.toString(), e);			
		} finally {
			DB.close(rs, pstmt);
		}

        return costProduct;
    }    
}