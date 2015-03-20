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

public class MEXMEDiscountSchema extends MDiscountSchema{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEPriceList.class);
	
	public MEXMEDiscountSchema(Properties ctx, int M_DiscountSchema_ID, String trxName)
	{
		super (ctx, M_DiscountSchema_ID, trxName);
	}
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MEXMEDiscountSchema (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MDiscountSchema
	
	
	/**
	 * Obtenemos la informacion de todos los esquemas de descuento
	 * 
	 * @param ctx
	 * @param id cash
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	
	public static List<MEXMEDiscountSchema> get(Properties ctx, String trxName) 
	throws Exception{
		
		List<MEXMEDiscountSchema> lista = new ArrayList<MEXMEDiscountSchema>();
		if(ctx == null){
			return null;
		}
		
		MEXMEDiscountSchema retValue = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		 sql.append(" SELECT * FROM M_DiscountSchema WHERE IsActive = 'Y' ")
		     .append(" AND DiscountType='P' ");
		     
		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"M_DiscountSchema"));
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{	
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			rs = pstmt.executeQuery ();
			while(rs.next()){
				retValue = new MEXMEDiscountSchema(ctx, rs, trxName);
				lista.add(retValue);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs, pstmt);
		}
		
		
		return lista;
		
	}


	/**
	 * Obtenemos la informacion de todos los esquemas de descuento
	 * 
	 * @param ctx
	 * @param id cash
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	
	public static List<LabelValueBean> getLabelValue(Properties ctx, String trxName) 
	throws Exception{
		
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		if(ctx == null){
			return null;
		}
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		 sql.append(" SELECT M_DiscountSchema_ID, name FROM M_DiscountSchema WHERE IsActive = 'Y' ")
			.append(" AND DiscountType='P' ");
		 sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"M_DiscountSchema"));
		 
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{	
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			rs = pstmt.executeQuery ();
			while(rs.next()){
				LabelValueBean elemento = new LabelValueBean(rs.getString("Name"), String.valueOf(rs.getInt("M_DiscountSchema_ID")));
				lista.add(elemento);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs, pstmt);
		}
		
		
		return lista;
		
	}
	
//	/**
//	 * Realiza la busqueda segun el critero seleccionado
//	 * @param criterio
//	 * @param valor
//	 * @param ctx
//	 * @param whereclause
//	 * @param orderBlock
//	 * @return
//	 * @throws Exception
//	 */
//	public static MEXMEEsqDesLine[] busqueda(Properties ctx, String criterio, String valor)
//			throws Exception {
//
//		MEXMEEsqDesLine[] esqDescs = null;
//
//		try {
//
//			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//			StringBuilder sqlWhere = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//			List<String> parameters = new ArrayList<String>();
//			String operador = criterio.isEmpty()?" OR ":" AND ";
//			
//			
//			sql.append(" SELECT EXME_EsqDesLine.* FROM EXME_EsqDesLine ");
//
//			if(!valor.isEmpty() && !"%".equals(valor)){
//				
//				if (criterio.startsWith("C_BPartner.") || criterio.isEmpty()) {
//					sql.append(" LEFT JOIN C_BPartner ON C_BPartner.C_BPartner_ID = EXME_EsqDesLine.C_BPartner_ID AND C_BPartner.isActive = 'Y' ");
//					sqlWhere.append(operador +" ( UPPER(C_BPartner.value) LIKE UPPER(?) OR UPPER(C_BPartner.Name) LIKE UPPER(?)  )");
//					parameters.add(valor);
//					parameters.add(valor);
//				} 
//
//				if (criterio.startsWith("C_BP_Group.")|| criterio.isEmpty()) {
//					sql.append(" LEFT JOIN C_BP_Group ON C_BP_Group.C_BP_Group_ID = EXME_EsqDesLine.C_BP_Group_ID AND C_BP_Group.isActive = 'Y' ");
//					sqlWhere.append(operador +" ( UPPER(C_BP_Group.value) LIKE UPPER(?) OR UPPER(C_BP_Group.Name) LIKE UPPER(?)  )");
//					parameters.add(valor);
//					parameters.add(valor);
//				} 
//
//				if (criterio.startsWith("M_Product.")|| criterio.isEmpty()) {
//					sql.append(" LEFT JOIN M_Product ON M_Product.M_Product_ID = EXME_EsqDesLine.M_Product_ID AND M_Product.isActive = 'Y' ");
//					sqlWhere.append(operador +" ( UPPER(M_Product.value) LIKE UPPER(?) OR UPPER(M_Product.Name) LIKE UPPER(?)  )");
//					parameters.add(valor);
//					parameters.add(valor);
//				} 
//
//				if (criterio.startsWith("M_Product_Category.")|| criterio.isEmpty()) {
//					sql.append(" LEFT JOIN M_Product_Category ON (M_Product_Category.M_Product_Category_ID = EXME_EsqDesLine.M_Product_Category_ID ) AND M_Product_Category.isActive = 'Y' ");
//					sqlWhere.append(operador +" ( UPPER(M_Product_Category.value) LIKE UPPER(?) OR UPPER(M_Product_Category.Name) LIKE UPPER(?)  )");
//					parameters.add(valor);
//					parameters.add(valor);
//				} 
//
//				if (criterio.startsWith("EXME_Area.")|| criterio.isEmpty()) {
//					sql.append(" LEFT JOIN EXME_Area ON (EXME_Area.EXME_Area_ID = EXME_EsqDesLine.EXME_Area_ID) AND EXME_Area.isActive = 'Y' ");
//					sqlWhere.append(operador +" ( UPPER(EXME_Area.value) LIKE UPPER(?) OR UPPER(EXME_Area.Name) LIKE UPPER(?)  )");
//					parameters.add(valor);
//					parameters.add(valor);	
//				} 
//
//				if (criterio.startsWith("EXME_EsqDesLine.")|| criterio.isEmpty()){
//					sql.append(" LEFT JOIN AD_Ref_List ON EXME_EsqDesLine.TIPOAREA  = AD_Ref_List.Value AND AD_Ref_List.AD_Reference_ID = ").append(X_EXME_EsqDesLine.TIPOAREA_AD_Reference_ID).append(" ");
//					sqlWhere.append(operador +" UPPER(AD_Ref_List.Name) LIKE UPPER(?) ");
//					parameters.add(valor);
//				}
//			}
//			
//			sql.append(" WHERE EXME_EsqDesLine.isActive = 'Y' ")
//				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ","EXME_EsqDesLine"))
//				.append(sqlWhere != null ? sqlWhere : "")
//				.append(" ORDER BY  EXME_EsqDesLine_ID DESC  ");		
//
//			esqDescs = MEXMEEsqDesLine.get(ctx, sql.toString(), parameters, null);
//
//		} catch (Exception e) {
//			throw new Exception(e.getMessage());
//		}
//
//		return esqDescs;
//	}
}
