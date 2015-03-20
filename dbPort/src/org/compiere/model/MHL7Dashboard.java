package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MHL7Dashboard extends X_HL7_Dashboard {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9077928206102988586L;
	static protected CLogger s_log = CLogger.getCLogger(MHL7Dashboard.class);

	public MHL7Dashboard(Properties ctx, int HL7_Dashboard_ID, String trxName) {
		super(ctx, HL7_Dashboard_ID, trxName);
	}
	
	public MHL7Dashboard(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
