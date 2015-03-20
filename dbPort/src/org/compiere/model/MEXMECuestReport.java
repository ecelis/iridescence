package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author odelarosa
 *
 */
public class MEXMECuestReport extends X_EXME_CuestReport {

	private static final long serialVersionUID = -2883510782441709249L;

	public MEXMECuestReport(Properties ctx, int EXME_Cuest_Report_ID, String trxName) {
		super(ctx, EXME_Cuest_Report_ID, trxName);
	}

	public MEXMECuestReport(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
