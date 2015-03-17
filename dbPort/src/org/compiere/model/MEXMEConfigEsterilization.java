package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.DB;

public class MEXMEConfigEsterilization extends X_EXME_ConfigEsterilizacion{

	/** serialVersionUID */
	private static final long serialVersionUID = -3966267824363437256L;

	/**
	 * Constructor
	 * @param ctx
	 * @param EXME_ConfigEsterilizacion_ID
	 * @param trxName
	 */
	public MEXMEConfigEsterilization(Properties ctx,
			int EXME_ConfigEsterilizacion_ID, String trxName) {
		super(ctx, EXME_ConfigEsterilizacion_ID, trxName);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConfigEsterilization(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * List Config Esterilizacion
	 * @param ctx
	 * @return
	 */
	public static List<LabelValueBean> getAll(Properties ctx){
		
		List<X_EXME_ConfigEsterilizacion>  list = new org.compiere.model.Query(ctx, X_EXME_ConfigEsterilizacion.Table_Name, null, null)
		.setOnlyActiveRecords(true)
		.setOrderBy(" Created Asc, Updated desc ").list();
		
		List<LabelValueBean> list2 = new ArrayList<LabelValueBean>();

		for(int i = 0; i < list.size(); i++){
			list2.add(new LabelValueBean(list.get(i).getName(), String.valueOf(list.get(i).getEXME_ConfigEsterilizacion_ID())));
		}
		
		return list2;
		
	}
	
	/**
	 * Create m_product
	 * @param ctx
	 * @param trxName
	 * @param EXME_ConfigEsterilizacion_ID
	 * @return
	 */
	public static MProduct createMProduct(Properties ctx,String trxName, int EXME_ConfigEsterilizacion_ID){
		MProduct product = new MProduct(ctx, 0, trxName);
		MEXMEConfigEsterilization config = new MEXMEConfigEsterilization(ctx, EXME_ConfigEsterilizacion_ID, trxName );
//		product.setProcedureType(config.getProcedureType());
		product.setProductClass(config.getProductClass());
		product.setC_UOM_ID(config.getC_UOM_ID());
		product.setM_Product_Category_ID(config.getM_Product_Category_ID());
		product.setEXME_TipoProd_ID(config.getEXME_TipoProd_ID());
		return product;
	}
	
	
	/**
	 * List Config Esterilizacion
	 * @param ctx
	 * @return
	 */
	public static List<LabelValueBean> getProductClass(Properties ctx, String productClass){
		
		List<X_EXME_ConfigEsterilizacion>  list = new org.compiere.model.Query(ctx, X_EXME_ConfigEsterilizacion.Table_Name, 
				" AND productClass = "+productClass, null)
		.setOnlyActiveRecords(true).addAccessLevelSQL(true)
		.setOrderBy(" Created Asc, Updated desc ").list();
		
		List<LabelValueBean> list2 = new ArrayList<LabelValueBean>();

		for(int i = 0; i < list.size(); i++){
			list2.add(new LabelValueBean(list.get(i).getName(), String.valueOf(list.get(i).getEXME_ConfigEsterilizacion_ID())));
		}
		
		return list2;
		
	}
	
	/**
	 * List Config Esterilizacion
	 * @param ctx
	 * @return
	 */
	 public static List<LabelValueBean> getLVB(Properties ctx, String productClass) {
         List<LabelValueBean> list2 = new ArrayList<LabelValueBean>();
         StringBuilder sql = new StringBuilder(); 
                 
         sql.append("SELECT * FROM EXME_ConfigEsterilizacion ")
         .append(" WHERE isActive = 'Y' AND productClass = ? ")
         .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " " , "EXME_ConfigEsterilizacion"));
         
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try {
                 
                 pstmt = DB.prepareStatement(sql.toString(), null);
                 pstmt.setString(1, productClass);
                 rs = pstmt.executeQuery();
                 
                 List<X_EXME_ConfigEsterilizacion>  list = new ArrayList<X_EXME_ConfigEsterilizacion>();
                 while (rs.next()){
                         list.add(new MEXMEConfigEsterilization(ctx, rs, null));
                 }

                 for(int i = 0; i < list.size(); i++){
                         list2.add(new LabelValueBean(list.get(i).getName(), String.valueOf(list.get(i).getEXME_ConfigEsterilizacion_ID())));
                 }
                 
                 
         } catch (Exception e) {
                 //s_log.log(Level.SEVERE, sql, e);
         } finally {
                 DB.close(rs, pstmt);
                 pstmt = null;
                 rs = null;
         }

         return list2;
 } // getSalesRepAdmin
}
