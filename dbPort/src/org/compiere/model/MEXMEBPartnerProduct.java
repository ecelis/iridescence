package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MEXMEBPartnerProduct extends X_C_BPartner_Product {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Static Logger               */
    private static CLogger      log = CLogger.getCLogger (MEXMEBPartnerProduct.class);
    
	public MEXMEBPartnerProduct(Properties ctx, int C_BPartner_Product_ID,
			String trxName) {
		super(ctx, C_BPartner_Product_ID, trxName);
	}

	public MEXMEBPartnerProduct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Log								*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEBPartnerProduct.class);
	
	/**
	 * 
	 * @param ctx
	 * @param C_BPartner_ID
	 * @param M_Product_ID
	 * @param trxName
	 * @return
	 */
	public static MEXMEBPartnerProduct get (Properties ctx, int C_BPartner_ID, int M_Product_ID, String trxName){

		MEXMEBPartnerProduct retValue = null;

		StringBuffer sql = new StringBuffer();

		sql.append("SELECT * FROM C_BPartner_Product WHERE");

		if (C_BPartner_ID == 0){
			sql.append(" (C_BPartner_ID = ").append(C_BPartner_ID)
			.append(" OR C_BPartner_ID IS NULL)");
		} else {
			sql.append(" C_BPartner_ID = ").append(C_BPartner_ID);
		}

		sql.append(" AND ");		

		if (M_Product_ID == 0){
			sql.append(" (M_Product_ID = ").append(M_Product_ID)
			.append(" OR M_Product_ID IS NULL)");
		} else {
			sql.append(" M_Product_ID = ").append(M_Product_ID);
		}

		PreparedStatement pstmt = null;
        ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			rs = pstmt.executeQuery ();
			if (rs.next ()) {
				retValue = new MEXMEBPartnerProduct(ctx, rs, trxName);
			} else {
				retValue = new MEXMEBPartnerProduct(ctx, 0, trxName);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}finally {
			DB.close(rs,pstmt);
		}
		
		if (retValue == null){
			s_log.fine("Not Found - C_BPartner_ID=" + C_BPartner_ID + ", M_Product_ID=" + M_Product_ID);
		}
		else{
			s_log.fine("C_BPartner_ID=" + C_BPartner_ID + ", M_Product_ID=" + M_Product_ID);
		}

		return retValue;
	}

}
