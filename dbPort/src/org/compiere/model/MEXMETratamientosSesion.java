package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;

public class MEXMETratamientosSesion extends X_EXME_Tratamientos_Sesion {

	/** serialVersionUID */
	private static final long serialVersionUID = 5855099220669064773L;
	/** */
	private static CLogger s_log = CLogger
			.getCLogger(MEXMETratamientosSesion.class);

	/**
	 * 
	 * @param ctx
	 * @param EXME_Tratamientos_Sesion_ID
	 * @param trxName
	 */
	public MEXMETratamientosSesion(Properties ctx,
			int EXME_Tratamientos_Sesion_ID, String trxName) {
		super(ctx, EXME_Tratamientos_Sesion_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETratamientosSesion(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Crea el objeto de la sesion
	 * 
	 * @param EXME_Tratamientos_Detalle_ID
	 *            detalle del tratamiento (por cada detalle una sesion)
	 * @param EXME_TratamientosPaciente_ID
	 *            relacion tratamiento paciente
	 * @param description
	 *            descripcion del detalle del tratamiento
	 * @return true si todo ha sido correcto, false si faltan parametros
	 */
	public boolean createSesion(int EXME_Tratamientos_Detalle_ID,
			int EXME_TratamientosPaciente_ID, String description,
			Timestamp DateStart, int secuencia) {

		if (EXME_Tratamientos_Detalle_ID <= 0
				|| EXME_TratamientosPaciente_ID <= 0)
			return false;

		this.setDescription(description);
		this.setEXME_Tratamientos_Detalle_ID(EXME_Tratamientos_Detalle_ID);
		this.setProcessed(false);
		this.setProcessing(false);
		this.setEXME_TratamientosPaciente_ID(EXME_TratamientosPaciente_ID);
		this.setDocStatus(X_EXME_CtaPac.DOCSTATUS_Drafted); // Draft
		this.setDocAction(X_EXME_CtaPac.DOCACTION_Complete);
		this.setDateStart(DateStart != null ? DateStart : DB.getTimestampForOrg(getCtx()));
		this.setDatePromised(DateStart != null ? DateStart : DB.getTimestampForOrg(getCtx()));
		this.setSecuencia(new BigDecimal(secuencia));

		return true;
	}

	/**
	 * Obtenemos las sesiones de un tratamiento asignado a un paciente solo
	 * activos
	 * 
	 * @param ctx
	 *            contexto
	 * @param EXME_TratamientosPaciente_ID
	 *            identificador de la relacion tratamiento - paciente
	 * @param EXME_Tratamientos_Detalle_ID
	 *            identificador de la relacion tratamiento - detalle (cada
	 *            detalle del tratamiento es una sesion)
	 * @param trxName
	 *            nombre de transaccion
	 * @return Listado de objetos MEXMETratamientosSesion
	 */
	public static List<MEXMETratamientosSesion> getTratamientosDetalle(
			Properties ctx, int EXME_TratamientosPaciente_ID,
			int EXME_Tratamientos_Detalle_ID, String trxName) {

		List<Integer> params = new ArrayList<Integer>();
		params.add(EXME_TratamientosPaciente_ID);
		params.add(EXME_Tratamientos_Detalle_ID);

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_Tratamientos_Sesion ").append(
				" WHERE IsActive = 'Y' AND EXME_TratamientosPaciente_ID = ? ")
				.append(" AND  EXME_Tratamientos_Detalle_ID = ? ").append(
						MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
								X_EXME_Tratamientos_Sesion.Table_Name)).append(
						" ORDER BY ").append(
						X_EXME_Tratamientos_Sesion.COLUMNNAME_Secuencia);

		return MEXMETratamientosSesion
				.get(ctx, sql.toString(), params, trxName);

	}

	public static List<MEXMETratamientosSesion> getTratamientosDetalle(
			Properties ctx, int EXME_TratamientosPaciente_ID, String trxName) {

		List<Integer> params = new ArrayList<Integer>();
		params.add(EXME_TratamientosPaciente_ID);

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_Tratamientos_Sesion ").append(
				" WHERE IsActive = 'Y' AND EXME_TratamientosPaciente_ID = ? ")
				.append(
						MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
								X_EXME_Tratamientos_Sesion.Table_Name)).append(
						" ORDER BY ").append(
						X_EXME_Tratamientos_Sesion.COLUMNNAME_Secuencia);

		return MEXMETratamientosSesion
				.get(ctx, sql.toString(), params, trxName);

	}

	public static List<MEXMETratamientosSesion> getSesionesPendientes(
			Properties ctx, int EXME_TratamientosPaciente_ID, String trxName) {

		List<Integer> params = new ArrayList<Integer>();
		params.add(EXME_TratamientosPaciente_ID);

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_Tratamientos_Sesion ").append(
				" WHERE IsActive = 'Y' AND EXME_TratamientosPaciente_ID = ? ")
				.append(
						MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
								X_EXME_Tratamientos_Sesion.Table_Name)).append(
						" AND ").append(
						X_EXME_Tratamientos_Sesion.COLUMNNAME_DocStatus)
				.append(" = '").append(X_EXME_CtaPac.DOCSTATUS_Drafted).append(
						"'").append(" ORDER BY ").append(
						X_EXME_Tratamientos_Sesion.COLUMNNAME_Secuencia);

		return MEXMETratamientosSesion
				.get(ctx, sql.toString(), params, trxName);
	}

	/**
	 * Generico .- Obtenemos las sesiones de un tratamiento asignado a un
	 * paciente
	 * 
	 * @param ctx
	 *            contexto
	 * @param sql
	 *            consulta para la tabla EXME_Tratamientos_Sesion
	 * @param params
	 *            parametros para realizar la consulta ( reemplazando "?" )
	 * @param trxName
	 *            nombre de transaccion
	 * @return regresa la lista con objetos MEXMETratamientosSesion
	 */
	public static List<MEXMETratamientosSesion> get(Properties ctx, String sql,
			List<?> params, String trxName) {

		List<MEXMETratamientosSesion> retorno = new ArrayList<MEXMETratamientosSesion>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (!sql.contains("ORDER BY") && !sql.contains("GROUP BY"))
				sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql,
						X_EXME_Tratamientos_Sesion.Table_Name);

			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MEXMETratamientosSesion mtd = new MEXMETratamientosSesion(ctx,
						rs, null);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getCitasMedico", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retorno;
	}

	private MEXMETratamientosDetalle _tratamientosDetalle = null;

	/**
	 * 
	 * @return
	 */
	public MEXMETratamientosDetalle getTratamientosDetalle() {
		if (_tratamientosDetalle == null) {
			_tratamientosDetalle = new MEXMETratamientosDetalle(getCtx(),
					getEXME_Tratamientos_Detalle_ID(), get_TrxName());
		}
		return _tratamientosDetalle;
	}

	private MEXMETratamientosPaciente _tratamientosPaciente = null;

	/**
	 * 
	 * @return
	 */
	public MEXMETratamientosPaciente getTratamientosPaciente() {
		if (_tratamientosPaciente == null) {
			_tratamientosPaciente = new MEXMETratamientosPaciente(getCtx(),
					getEXME_TratamientosPaciente_ID(), get_TrxName());
		}
		return _tratamientosPaciente;
	}

	/**
	 * Referencia
	 */
	private MReference reference = null;

	public MReference getReference() {
		return reference;
	}

	public void setReference(MReference reference) {
		this.reference = reference;
	}

	/**
	 * Nombre del tipo de sesion
	 * 
	 * @return
	 */
	public String getEstatus() {

		if (reference == null
				|| reference.getAD_Reference_ID() != X_EXME_CtaPac.DOCSTATUS_AD_Reference_ID)

			reference = new MReference(getCtx(), X_EXME_CtaPac.DOCSTATUS_AD_Reference_ID,
					get_TrxName());

		return reference.getValueList(getDocStatus());
	}
}
