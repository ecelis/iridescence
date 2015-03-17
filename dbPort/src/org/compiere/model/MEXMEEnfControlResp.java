package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEEnfControlResp extends X_EXME_EnfControlResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6221074866825440176L;
	/**	Static Logger				*/
	@SuppressWarnings("unused")
	private static CLogger		s_log = CLogger.getCLogger (MEXMEEnfControlResp.class);

	public MEXMEEnfControlResp(Properties ctx,
			int EXME_EnfControlMultiResp_ID, String trxName) {
		super(ctx, EXME_EnfControlMultiResp_ID, trxName);
	}


	public MEXMEEnfControlResp(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}
}
