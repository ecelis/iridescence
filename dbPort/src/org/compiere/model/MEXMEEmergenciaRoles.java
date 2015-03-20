package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;

public class MEXMEEmergenciaRoles extends X_EXME_Emergencia_Roles {

	private static final long	serialVersionUID	= 2263902409995881818L;
	@SuppressWarnings("unused")
	private static CLogger		slog				= CLogger.getCLogger(MEXMEEmergenciaRoles.class);

	public MEXMEEmergenciaRoles(Properties ctx, int EXME_Emergencia_Roles_ID, String trxName) {
		super(ctx, EXME_Emergencia_Roles_ID, trxName);
	}

	public MEXMEEmergenciaRoles(Properties ctx, int EXME_Emergencia_ID, int AD_Role_ID, String trxName) {
		super(ctx, 0, trxName);
		setAD_Role_ID(AD_Role_ID);
		setEXME_Emergencia_ID(EXME_Emergencia_ID);
	}

	public MEXMEEmergenciaRoles(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMEEmergenciaRoles> get(Properties ctx, int EXME_Emergencia_ID, String trxName) {
		return new Query(ctx, Table_Name, " EXME_Emergencia_ID=?", null)//
			.setParameters(EXME_Emergencia_ID) //
			// .setOnlyActiveRecords(true)// ??
			.addAccessLevelSQL(true)//
			.list();
	}

}
