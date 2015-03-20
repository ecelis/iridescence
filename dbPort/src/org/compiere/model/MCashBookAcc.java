package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class MCashBookAcc extends X_C_CashBook_Acct {

	private static final long serialVersionUID = 1L;
	
	public MCashBookAcc(Properties ctx, int C_CashBook_Acct_ID, String trxName) {
		super(ctx, C_CashBook_Acct_ID, trxName);
	}
	
    public MCashBookAcc (Properties ctx, ResultSet rs, String trxName) {
      super (ctx, rs, trxName);
    }

	public static X_C_CashBook_Acct cashBookAcc(Properties ctx, int C_CashBook_Acct_ID, int C_AcctSchema_ID, String trxName) {
		ArrayList<Object> lst = new ArrayList<Object>();
		lst.add(C_CashBook_Acct_ID);
		lst.add(C_AcctSchema_ID);
		return new Query(ctx, Table_Name, " C_CashBook_ID= ? and C_AcctSchema_ID= ? ", trxName).addAccessLevelSQL(true).setParameters(lst).first();
	}
}
