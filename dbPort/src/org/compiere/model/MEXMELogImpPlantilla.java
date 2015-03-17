package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEXMELogImpPlantilla extends X_EXME_LogImpPlantilla {
	private static final long serialVersionUID = 1L;

	public MEXMELogImpPlantilla(Properties ctx, int EXME_CampoPlantilla_ID, String trxName) {
		super(ctx, EXME_CampoPlantilla_ID, trxName);
	}

	public MEXMELogImpPlantilla(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
