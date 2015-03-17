package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEGenQualityMeasureDet extends X_EXME_GenQualityMeasureDet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3779531876116865580L;

	public MEXMEGenQualityMeasureDet(Properties ctx, int EXME_GenQualityMeasureDet_ID, String trxName) {
		super(ctx, EXME_GenQualityMeasureDet_ID, trxName);
	}
	
	public MEXMEGenQualityMeasureDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
}
