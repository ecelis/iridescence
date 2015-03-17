package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MBPCustomerAcct extends X_C_BP_Customer_Acct {

	public MBPCustomerAcct(Properties ctx, int C_BP_Customer_Acct_ID, String trxName) {
		super(ctx, C_BP_Customer_Acct_ID, trxName);
	}

	public MBPCustomerAcct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get Category Acct
	 * @param ctx
	 * @param M_Product_Category_ID
	 * @param C_AcctSchema_ID
	 * @param trxName
	 * @return 
	 */
	public static MBPCustomerAcct get(Properties ctx, int C_BPartner_ID, int C_AcctSchema_ID, String trxName) {
		MBPCustomerAcct retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM  C_BP_Customer_Acct " + "WHERE C_BPartner_ID =? AND C_AcctSchema_ID=?";
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_BPartner_ID);
			pstmt.setInt(2, C_AcctSchema_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MBPCustomerAcct(ctx, rs, trxName);

		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql, e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			pstmt = null;
			rs = null;
		} catch (Exception e) {
			pstmt = null;
			rs = null;
		}
		return retValue;
	} //	get

}
