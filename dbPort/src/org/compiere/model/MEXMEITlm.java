package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEITlm extends X_EXME_I_Tlm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEXMEITlm(Properties ctx, int EXME_I_Tlm_ID, String trxName) {
		super(ctx, EXME_I_Tlm_ID, trxName);
	}

	public MEXMEITlm(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}


}
