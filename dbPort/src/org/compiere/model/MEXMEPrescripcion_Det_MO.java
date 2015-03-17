package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEPrescripcion_Det_MO extends X_EXME_MO_Prescripcion_Det{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger log = CLogger.getCLogger (MEXMEPrescripcion_Det_MO.class);
	private String productName = null;
	
	
	public MEXMEPrescripcion_Det_MO(Properties ctx, int prescripcionDetID, String trxName) {
		super(ctx, prescripcionDetID, trxName);
	}
	 
	
	public MEXMEPrescripcion_Det_MO(Properties ctx, ResultSet rs, String trx) {
		 super(ctx, rs, trx);
	}
	
	
	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public static ArrayList<MEXMEPrescripcion_Det_MO> getDetalle(Properties ctx, int prescripcionID, String trxName){
		 
		ArrayList<MEXMEPrescripcion_Det_MO> list = new ArrayList<MEXMEPrescripcion_Det_MO>();
		 
		String sql = " SELECT EXME_MO_Prescripcion_Det.*, M_Product.Name as productname  " +
				"FROM EXME_MO_Prescripcion_Det " +
				"LEFT OUTER JOIN M_Product ON EXME_MO_Prescripcion_Det.M_Product_ID = M_Product.M_Product_ID " +
		        "WHERE EXME_MO_Prescripcion_Det.EXME_MO_Prescripcion_Id = " +  prescripcionID;
		
		 
		sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "EXME_MO_Prescripcion_Det");
		 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		    
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MEXMEPrescripcion_Det_MO obj = new MEXMEPrescripcion_Det_MO(ctx, rs, trxName);
				obj.setProductName(rs.getString("productname"));
				list.add(obj);
				
			}
			
		 } catch (Exception e) {
			 log.log(Level.SEVERE, sql.toString(), e.getMessage());	
		 }finally {
			 DB.close(rs, pstmt);
			 pstmt = null;
			 rs = null;
			
		}
		 
		 return list;
		   
	}
	
	

}
