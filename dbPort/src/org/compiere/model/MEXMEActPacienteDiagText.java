package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEActPacienteDiagText extends X_EXME_ActPacienteDiagText {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEXMEActPacienteDiagText(Properties ctx, int EXME_ActPacienteDiagText_ID, String trxName) {
		super(ctx, EXME_ActPacienteDiagText_ID, trxName);
	}

	public MEXMEActPacienteDiagText(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
