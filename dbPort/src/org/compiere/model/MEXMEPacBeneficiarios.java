package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMEPacBeneficiarios extends X_EXME_PacBeneficiarios {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEXMEPacBeneficiarios(Properties ctx, int EXME_PacBeneficiarios_ID, String trxName) {
		super(ctx, EXME_PacBeneficiarios_ID, trxName);
	}

	public MEXMEPacBeneficiarios(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
