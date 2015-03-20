package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEMorgue extends X_EXME_Morgue {

	private static final long serialVersionUID = 1L;

	public MEXMEMorgue(Properties ctx, int EXME_Morgue_ID, String trxName) {
		super(ctx, EXME_Morgue_ID, trxName);
	}

	public MEXMEMorgue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
