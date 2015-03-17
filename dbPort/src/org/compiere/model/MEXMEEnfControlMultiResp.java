package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEEnfControlMultiResp extends X_EXME_EnfControlMultiResp{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -4194563012636223295L;
	/**	Static Logger				*/
	@SuppressWarnings("unused")
	private static CLogger		s_log = CLogger.getCLogger (MEXMEEnfControlMultiResp.class);

	public MEXMEEnfControlMultiResp(Properties ctx,
			int EXME_EnfControlMultiResp_ID, String trxName) {
		super(ctx, EXME_EnfControlMultiResp_ID, trxName);
	}


	public MEXMEEnfControlMultiResp(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
	}

}
