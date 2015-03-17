package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEPrescReview extends X_EXME_PrescReview {

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	public MEXMEPrescReview(Properties ctx, int EXME_PrescReview_ID,
			String trxName) {
		super(ctx, EXME_PrescReview_ID, trxName);
	}

	public MEXMEPrescReview(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
