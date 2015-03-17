package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Utilerias;

public class MEXMEEmergenciaUsuario extends X_EXME_Emergencia_Usuario {

	private static final long	serialVersionUID	= -2327785163630647792L;
	@SuppressWarnings("unused")
	private static CLogger		slog				= CLogger.getCLogger(MEXMEEmergenciaUsuario.class);

	public MEXMEEmergenciaUsuario(Properties ctx, int EXME_Emergencia_Usuario_ID, String trxName) {
		super(ctx, EXME_Emergencia_Usuario_ID, trxName);
	}

	public MEXMEEmergenciaUsuario(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static List<MEXMEEmergenciaUsuario> getEmergencyUsers(Properties ctx, String trxName) {
		return new Query(ctx, Table_Name, "", null)//
			// .setOnlyActiveRecords(true)// ??
			.addAccessLevelSQL(true)//
			.list();
	}

	public static boolean isEmergencyUser(Properties ctx, int AD_User_ID, String trxName) {
		return new Query(ctx, Table_Name, " AD_User_ID=?", null)//
			.setParameters(AD_User_ID)//
			// .setOnlyActiveRecords(true)// ?
			.addAccessLevelSQL(true)//
			.firstId() > 0;
	}

	private MUser	user	= null;

	public MUser getUser() {
		if (user == null) {
			user = new MUser(getCtx(), getAD_User_ID(), get_TrxName());
		}
		return user;
	}

	@Override
	protected boolean beforeDelete() {
		if (MEXMEEmergencia.isEmergencyState(getCtx())) {
			log.saveError(Utilerias.getMsg(getCtx(),"msj.errordeletereg"), Utilerias.getMsg(getCtx(),"msj.emrstdoactivo"));
			return false;
		} else {
			return super.beforeDelete();
		}
	}

}
