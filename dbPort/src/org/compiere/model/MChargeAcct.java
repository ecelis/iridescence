/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class MChargeAcct extends X_C_Charge_Acct {

	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param C_Charge_Acct_ID
	 * @param trxName
	 */
	public MChargeAcct(Properties ctx, int C_Charge_Acct_ID, String trxName) {
		super(ctx, C_Charge_Acct_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MChargeAcct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static X_C_Charge_Acct chargeAcct(Properties ctx, int C_Charge_Acct_ID, int C_AcctSchema_ID, String trxName){
		
		ArrayList<Object> lst = new ArrayList<Object>();
		lst.add(C_Charge_Acct_ID);
		lst.add(C_AcctSchema_ID);
		
		return new Query(ctx, Table_Name, " C_Charge_ID= ? and C_AcctSchema_ID= ? ", trxName).addAccessLevelSQL(true).setParameters(lst).first();
	}
}
