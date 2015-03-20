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

public class MBPartnerProduct extends X_C_BPartner_Product {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MBPartnerProduct.class);

	public MBPartnerProduct(Properties ctx, int C_BPartner_Product_ID,
			String trxName) {
		super(ctx, C_BPartner_Product_ID, trxName);
	}

	public MBPartnerProduct(Properties ctx,ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Regresa un LabelValueBean con el id del socio de negocios y el fabricante relacionado con el producto
	 * @param ctx
	 * @param M_Product_ID
	 * @return
	 * lhernandez
	 */
	public static List<LabelValueBean> get(Properties ctx, int M_Product_ID){
		List<LabelValueBean> lista = new ArrayList<LabelValueBean>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("SELECT C_BPartner_ID, Manufacturer FROM C_BPartner_Product WHERE M_Product_ID = ? ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			 pstmt = DB.prepareStatement(sql.toString(), null);
			 pstmt.setInt(1, M_Product_ID);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()){
				 LabelValueBean bp = new LabelValueBean(rs.getString("Manufacturer") ,String.valueOf(rs.getInt("C_Bpartner_ID")));
				 lista.add(bp);
			 }
		} catch (Exception e) {
    		s_log.log(Level.SEVERE, "get(Properties ctx, int M_Product_ID)", e);
    	} finally {
    		DB.close(rs, pstmt);
    	}
		return lista;
	}
}
