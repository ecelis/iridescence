package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Constantes;

public class MProductAcct extends X_M_Product_Acct{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3567272138907801445L;

	public MProductAcct(Properties ctx, int M_Product_Acct_ID, String trxName) {
		super(ctx, M_Product_Acct_ID, trxName);
	}

	public MProductAcct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public static List<MProductAcct> get(Properties ctx, int productID, int schemaID, String trxName){

		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		where.append(" M_Product_ID = ? AND C_AcctSchema_ID = ?");
		return new Query(ctx, Table_Name, where.toString(), trxName)//
			.setParameters(productID, schemaID)//
			.setOnlyActiveRecords(true)//
			.setOrderBy(" Updated desc")
			.addAccessLevelSQL(true)//
			.list();
		
	}
	
	
	
	

}
