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

public class MEXMELabeler extends X_EXME_Labeler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Logger **/
	private static CLogger log = CLogger.getCLogger(MEXMELabeler.class);
	
	public MEXMELabeler(Properties ctx, int EXME_Labeler_ID, String trxName) {
		super(ctx, EXME_Labeler_ID, trxName);
	}

	public MEXMELabeler(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Obtiene una lista de fabricantes de un producto.
	 * @param ctx
	 * @param M_Product_ID
	 * @return
	 */
	public static List<LabelValueBean> getManufacturer(Properties ctx, int M_Product_ID){
		List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT L.EXME_Labeler_ID, L.LabelerID||' '||L.Mfgname as Name ")
		   .append("FROM EXME_Labeler L ")
		   .append("INNER JOIN M_Product prod ON (L.EXME_Labeler_ID = prod.EXME_Labeler_ID) ")
		   .append("WHERE prod.M_Product_ID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, M_Product_ID);
			 result = pstmt.executeQuery();
			 
			 while(result.next()){
				 LabelValueBean lvb = new LabelValueBean(result.getString(2) ,String.valueOf(result.getInt(1)));
				 list.add(lvb);
			 }
		} catch (Exception e) {
    		log.log(Level.SEVERE, 
    				"getManufacturer(Properties ctx, int M_Product_ID)" + e.getMessage(),
    				e);
    	} finally {
			DB.close(result, pstmt);
			pstmt = null;
			result = null;
		}
		return list;
	}
	
	public static MEXMELabeler getLabeler(Properties ctx, String code, int id){
		MEXMELabeler ret = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		 sql.append(" Select  ")
			.append(" EXME_LABELER_ID, MFGNAME, MVXCODE ") 
			.append(" from EXME_LABELER  ")
			.append(" where ISACTIVE = 'Y' ")
			.append(" and ISVACUNA = 'Y' ");
		 if(code != null && id == 0){
			sql.append(" and MVXCODE = ?  ");
		 }else if(id > 0){
			 sql.append(" and EXME_LABELER_ID = ?  ");
		 }
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 if(code != null && id == 0){
				 pstmt.setString(1, code);
			 }else if(id > 0){
				 pstmt.setInt(1, id);
			}
			
			 result = pstmt.executeQuery();
			 
			 while(result.next()){
				 ret = new MEXMELabeler(ctx, result, null);
//				 ret.setLabelerID(result.getInt(1));
//				 ret.setLabelerName(result.getString(2));
//				 ret.setLabelerCode(result.getString(3));
			 }
		} catch (Exception e) {
    		log.log(Level.SEVERE, 
    				"getManufacturer(Properties ctx, int M_Product_ID)" + e.getMessage(),
    				e);
    	} finally {
			DB.close(result, pstmt);
			pstmt = null;
			result = null;
		}
		return ret;
	}

	/**
	 * Obtiene una lista de fabricantes de un producto.
	 * @param ctx
	 * @param M_Product_ID
	 * @return
	 */
	public static boolean validateLabeler(Properties ctx, String LABELERID){
		boolean valido = true;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT L.* ")
		   .append("FROM EXME_LABELER L ")
		   .append("WHERE L.LABELERID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setString(1, LABELERID);
			 result = pstmt.executeQuery();
			 
			 if(!result.next()){
				 valido = createLabeler(ctx, LABELERID);		 
			 }
			 
		} catch (Exception e) {
    		log.log(Level.SEVERE, 
    				"getManufacturer(Properties ctx, String LABELERID)" + e.getMessage(),
    				e);
    	} finally {
    		DB.close(result,pstmt);
    	}
		return valido;
	}
	
	public static boolean createLabeler(Properties ctx, String LABELERID){
		MEXMELabeler ret = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT L.LABELERID, L.MFGNAME, L.NDCMFGCODE ")
		   .append("FROM FDB_LABELER L ")
		   .append("WHERE L.LABELERID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setString(1, LABELERID);
			 result = pstmt.executeQuery();
			 
			 if(result.next()){
				ret = new MEXMELabeler(ctx, 0, null);
				ret.setLabelerID(result.getString(1));
				ret.setMfgname(result.getString(2));
				ret.setNdcmfgcode(result.getString(3));
			 }
		} catch (Exception e) {
    		log.log(Level.SEVERE, 
    				"getManufacturer(Properties ctx, String LABELERID)" + e.getMessage(),
    				e);
    	} finally {
    		DB.close(result,pstmt);
    	}
		return ret.save();
	}
	
	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static List<LabelValueBean> getVB(Properties ctx, int exmeVacunaID) {
		final List<LabelValueBean> list = new ArrayList<LabelValueBean>();
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		sql.append("select DISTINCT lab.* from exme_labeler lab ");//, M_Product.M_Product_ID as vaccineProd
		sql.append("inner join m_product prod on(lab.exme_labeler_id = prod.exme_labeler_id) ");
		sql.append("INNER JOIN EXME_vacunaproduct vp ON (prod.m_product_id = vp.m_product_id AND vp.isActive       ='Y') ");
		sql.append(" INNER JOIN EXME_VACUNA vac ON(vp.exme_vacuna_id = vac.exme_vacuna_id) ");
		sql.append(" where vac.exme_vacuna_id = ? ");
		sql.append(" and lab.isvacuna ='Y' order by lab.mfgname ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, exmeVacunaID);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				MEXMELabeler lab = new MEXMELabeler(ctx, rs, null);
//				vac.getProduct(true, labelerID, warehouseId);
				list.add(new LabelValueBean(lab.getMfgname() ,String.valueOf(lab.getEXME_Labeler_ID())));
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
}
