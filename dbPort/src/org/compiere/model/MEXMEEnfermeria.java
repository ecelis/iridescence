package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMEEnfermeria extends X_EXME_Enfermeria {

	private static final long	serialVersionUID	= 1L;

	private static CLogger		log					= CLogger.getCLogger(MEXMEEnfermeria.class);

	public MEXMEEnfermeria(Properties ctx, int EXME_Enfermeria_ID, String trxName) {
		super(ctx, EXME_Enfermeria_ID, trxName);
	}

	public MEXMEEnfermeria(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	private String getFullName() {
		log.info("getFullName");
		final StringBuilder name = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		name.append(getName());
		if (StringUtils.isNotBlank(getNombre2())) {
			name.append(" ").append(getNombre2());
		}
		name.append(" ").append(getApellido1());
		if (StringUtils.isNotBlank(getApellido2())) {
			name.append(" ").append(getApellido2());
		}
		return name.toString().toUpperCase();
	}

	/**
	 * Regresa el nombre de la enfermera
	 * 
	 * @param ctx Properties
	 * @param trxName String
	 * @return List<LabelValueBean>
	 * @throws
	 */
	public static String getFullName(Properties ctx, int enfermeriaID, String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT Nombre_Enf FROM EXME_Enfermeria ");
		sql.append(" WHERE IsActive='Y' AND EXME_Enfermeria_ID=? ");
		return DB.getSQLValueString(trxName, sql.toString(), enfermeriaID);
	}

	protected boolean beforeSave(boolean newRecord) {
		format();// quitar espacios, cambiar a mayusculas
		return true;
	} // beforeSave

	public void format() {
		log.info("format");
		// Borrar los espacios al final del nombre y apellidos (RMontemayorJulio 2007)
		if (is_ValueChanged(COLUMNNAME_Name) && StringUtils.isNotBlank(getName())) {
			setName(getName().trim().toUpperCase());
		}
		if (is_ValueChanged(COLUMNNAME_Nombre2) && StringUtils.isNotBlank(getNombre2())) {
			setNombre2(getNombre2().trim().toUpperCase());
		}
		if (is_ValueChanged(COLUMNNAME_Apellido1) && StringUtils.isNotBlank(getApellido1())) {
			setApellido1(getApellido1().trim().toUpperCase());
		}
		if (is_ValueChanged(COLUMNNAME_Apellido2) && StringUtils.isNotBlank(getApellido2())) {
			setApellido2(getApellido2().trim().toUpperCase());
		}// vperez: se setea el nombre completo
		if (is_new() || StringUtils.isBlank(getNombre_Enf()) || //
			(is_ValueChanged(COLUMNNAME_Name) //
				|| is_ValueChanged(COLUMNNAME_Nombre2) //
				|| is_ValueChanged(COLUMNNAME_Apellido1)//
			|| is_ValueChanged(COLUMNNAME_Apellido2))) {
			setNombre_Enf(getFullName());
		}
	}
	
	/**
	 * 
	 * @param ctx
	 * @param userId
	 * @return
	 */
	public static String getNameByUserId(Properties ctx, int userId) {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT Nombre_Enf FROM EXME_Enfermeria ");
		sql.append(" WHERE IsActive='Y' AND AD_User_ID=? ");
		return DB.getSQLValueString(null, sql.toString(), userId);
	}
}
