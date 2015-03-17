package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MTaxAcct extends X_C_Tax_Acct {

	/** serialVersionUID */
	private static final long serialVersionUID = 4258741629290553688L;

	public MTaxAcct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public MTaxAcct(Properties ctx, int C_Tax_Acct_ID, String trxName) {
		super(ctx, C_Tax_Acct_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MAccount getTCreditAcct(){
		return MAccount.get(getCtx(), getT_Credit_Acct());
	}
	
	public MAccount getTDueAcct(){
		return MAccount.get(getCtx(), getT_Due_Acct());
	}
	
	public MAccount getTExpenseAcct(){
		return MAccount.get(getCtx(), getT_Expense_Acct());
	}
	
	public MAccount getTHoldLiabAcct(){
		return MAccount.get(getCtx(), getT_HoldLiab_Acct());
	}
	
	public MAccount getTHoldRecAcct(){
		return MAccount.get(getCtx(), getT_HoldRec_Acct());
	}
	
	public MAccount getTLiabilityAcct(){
		return MAccount.get(getCtx(), getT_Liability_Acct());
	}
	
	public MAccount getTReceivablesAcct(){
		return MAccount.get(getCtx(), getT_Receivables_Acct());
	}
}

