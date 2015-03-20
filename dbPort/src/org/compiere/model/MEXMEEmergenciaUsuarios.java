package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEEmergenciaUsuarios extends X_EXME_Emergencia_Usuarios {

	private static final long	serialVersionUID	= 1389296859569878578L;
	@SuppressWarnings("unused")
	private static CLogger		slog				= CLogger.getCLogger(MEXMEEmergenciaUsuarios.class);

	public MEXMEEmergenciaUsuarios(Properties ctx, int EXME_Emergencia_Usuarios_ID, String trxName) {
		super(ctx, EXME_Emergencia_Usuarios_ID, trxName);
	}

	public MEXMEEmergenciaUsuarios(Properties ctx, int EXME_Emergencia_ID, int AD_User_ID, String trxName) {
		super(ctx, 0, trxName);
		setEXME_Emergencia_ID(EXME_Emergencia_ID);
		setAD_User_ID(AD_User_ID);
	}

	public MEXMEEmergenciaUsuarios(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMEEmergenciaUsuarios> get(Properties ctx, int EXME_Emergencia_ID, String trxName) {
		return new Query(ctx, Table_Name, " EXME_Emergencia_ID=?", null)//
			.setParameters(EXME_Emergencia_ID)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.list();
	}

}
