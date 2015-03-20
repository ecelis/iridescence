package org.compiere.model;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Utilerias;

public class MEXMEEmergencia extends X_EXME_Emergencia {

	private static final long	serialVersionUID	= -7741904436562369686L;
	private static CLogger		slog				= CLogger.getCLogger(MEXMEEmergencia.class);

	public MEXMEEmergencia(Properties ctx, int EXME_Emergencia_ID, String trxName) {
		super(ctx, EXME_Emergencia_ID, trxName);
	}

	public MEXMEEmergencia(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		final String errorMsg = Utilerias.getMsg(getCtx(), "msj.errorsavereg");
		if (newRecord) {
			if (isEmergencyState(getCtx())) {
				slog.saveError(errorMsg, Utilerias.getMsg(getCtx(), "msj.emrstdoactivo"));
				return false;
			} else {
				this.setIniciadoPor(this.getCreatedBy());
				this.setFecha_Ini(this.getCreated());
				startEmergencyState();
			}
		} else {
			if (is_ValueChanged(COLUMNNAME_Description)) {
				slog.saveError(errorMsg, Utilerias.getMsg(getCtx(), "msj.regNoedit"));
				return false;
			}
			if (is_ValueChanged(COLUMN_IsActive)) {
				if (isActive()) {
					slog.saveError(errorMsg, Utilerias.getMsg(getCtx(), "msj.reactemstd"));
					return false;
				}
			}
		}
		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success) {
			return success;
		}
		if (!newRecord) {
			if (is_ValueChanged(COLUMN_IsActive) && this.getFecha_Fin() == null && this.getTerminadoPor() == 0) {
				if (!isActive()) {
					this.setFecha_Fin(this.getUpdated());
					this.setTerminadoPor(this.getUpdatedBy());
					if (this.save()) {
						finishEmergencyState();
					}
				}
			}
		} else {
			final List<MEXMEEmergenciaUsuario> usuarios = MEXMEEmergenciaUsuario.getEmergencyUsers(getCtx(), null);
			final List<MEXMEEmergenciaRol> roles = MEXMEEmergenciaRol.getEmergencyRoles(getCtx(), null);

			for (MEXMEEmergenciaUsuario usuario : usuarios) {
				final MEXMEEmergenciaUsuarios mEmergenciaUsuarios =
					new MEXMEEmergenciaUsuarios(getCtx(), getEXME_Emergencia_ID(), usuario.getAD_User_ID(), get_TrxName());
				mEmergenciaUsuarios.save();
			}

			for (MEXMEEmergenciaRol rol : roles) {
				final MEXMEEmergenciaRoles mEmergenciaRoles =
					new MEXMEEmergenciaRoles(getCtx(), getEXME_Emergencia_ID(), rol.getAD_Role_ID(), get_TrxName());
				mEmergenciaRoles.save();
			}
		}
		return super.afterSave(newRecord, success);
	}

	public static boolean isEmergencyState(final Properties ctx) {
		return new Query(ctx, Table_Name, "", null)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.firstId() > 0;// true if ID > 0
	}

	public static MEXMEEmergencia getEmergency(final Properties ctx) {
		return new Query(ctx, Table_Name, "", null)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.first();
	}

	public void startEmergencyState() {
		final List<MEXMEEmergenciaUsuario> usuarios = MEXMEEmergenciaUsuario.getEmergencyUsers(getCtx(), null);
		final List<MEXMEEmergenciaRol> roles = MEXMEEmergenciaRol.getEmergencyRoles(getCtx(), null);

		for (MEXMEEmergenciaUsuario usuario : usuarios) {
			final MUserRoles[] arr = MUserRoles.getOfUser(getCtx(), usuario.getAD_User_ID());
			final List<MUserRoles> lstRoles = Arrays.asList(arr);
			for (MEXMEEmergenciaRol rol : roles) {
				if (!lstRoles.contains(rol)) {
					final MUserRoles mUserRoles = new MUserRoles(getCtx(), usuario.getAD_User_ID(), rol.getAD_Role_ID(), get_TrxName());
					mUserRoles.setIsEmergency(true);
					mUserRoles.save();
				}
			}
		}

	}

	public void finishEmergencyState() {
		final List<MEXMEEmergenciaUsuario> usuarios = MEXMEEmergenciaUsuario.getEmergencyUsers(getCtx(), null);
		if (usuarios.isEmpty()) {
			return;
		}
		final List<MEXMEEmergenciaRol> roles = MEXMEEmergenciaRol.getEmergencyRoles(getCtx(), null);
		if (roles.isEmpty()) {
			return;
		}

		// final List<Integer> usuariosIDs = new ArrayList<Integer>();
		final StringBuilder idsUsuarios = new StringBuilder();
		for (MEXMEEmergenciaUsuario usuario : usuarios) {
			// usuariosIDs.add(usuario.getAD_User_ID());
			if (idsUsuarios.length() > 0) {
				idsUsuarios.append(",");
			}
			idsUsuarios.append(usuario.getAD_User_ID());
		}

		// final List<Integer> rolesIDs = new ArrayList<Integer>();
		final StringBuilder idsRoles = new StringBuilder();
		for (MEXMEEmergenciaRol rol : roles) {
			// rolesIDs.add(rol.getAD_Role_ID());
			if (idsRoles.length() > 0) {
				idsRoles.append(",");
			}
			idsRoles.append(rol.getAD_Role_ID());
		}

		if (idsUsuarios.length() > 0 && idsRoles.length() > 0) {
			// final String idsUsuarios = StringUtils.join(usuariosIDs, ",");
			// final String idsRoles = StringUtils.join(rolesIDs, ",");
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE AD_User_Roles WHERE AD_User_ID IN(");
			sql.append(idsUsuarios).append(") ");
			sql.append(" AND AD_Role_ID IN(").append(idsRoles).append(") ");
			sql.append(" AND isEmergency = 'Y'");
			DB.executeUpdate(sql.toString(), get_TrxName());
		}
	}

}
