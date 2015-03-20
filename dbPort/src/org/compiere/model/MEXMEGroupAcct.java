package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEGroupAcct extends X_EXME_GroupAcct {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEXMEGroupAcct(Properties ctx, int EXME_GroupAcct_ID, String trxName) {
		super(ctx, EXME_GroupAcct_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEXMEGroupAcct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	public static MEXMEGroupAcct getGroupValue(Properties ctx, String value, String trxName){
		return new Query(ctx, X_EXME_GroupAcct.Table_Name, " value = ? ", trxName)
		.setOnlyActiveRecords(true)// and isactive = 'Y'
		//.addAccessLevelSQL(true)// and ad_client_id = 0 and ad_org_id = 0 DEBE SER NIVEL SYSTEM
		.setParameters(value)
		.first();
	}
}
