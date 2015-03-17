package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Utilerias;

public class MEXMEEmergenciaRol extends X_EXME_Emergencia_Rol {

	private static final long	serialVersionUID	= -307164156603710440L;
	@SuppressWarnings("unused")
	private static CLogger		slog				= CLogger.getCLogger(MEXMEEmergenciaRol.class);

	public MEXMEEmergenciaRol(Properties ctx, int EXME_Emergencia_Rol_ID, String trxName) {
		super(ctx, EXME_Emergencia_Rol_ID, trxName);
	}

	public MEXMEEmergenciaRol(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private MRole	role	= null;

	public MRole getRole() {
		if (role == null) {
			role = new MRole(getCtx(), getAD_Role_ID(), get_TrxName());
		}
		return role;
	}

	public static List<MEXMEEmergenciaRol> getEmergencyRoles(Properties ctx, String trxName) {
		return new Query(ctx, Table_Name, "", null)//
			// .setOnlyActiveRecords(true)// ??
			.addAccessLevelSQL(true)//
			.list();
	}

	public static boolean isEmergencyRol(Properties ctx, int AD_Role_ID, String trxName) {
		return new Query(ctx, Table_Name, " AD_Role_ID=?", null)//
			.setParameters(AD_Role_ID)//
			// .setOnlyActiveRecords(true)// ??
			.addAccessLevelSQL(true)//
			.firstId() > 0;
	}

	@Override
	protected boolean beforeDelete() {
		if (MEXMEEmergencia.isEmergencyState(getCtx())) {
			log.saveError(Utilerias.getMsg(getCtx(), "msj.errordeletereg"), Utilerias.getMsg(getCtx(), "msj.emrstdoactivo"));
			return false;
		} else {
			return super.beforeDelete();
		}
	}
}
