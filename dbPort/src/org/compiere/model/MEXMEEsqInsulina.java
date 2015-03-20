package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Utilerias;

public class MEXMEEsqInsulina extends X_EXME_EsqInsulina {

	private static final long	serialVersionUID	= 1L;
	private static CLogger		s_log				= CLogger.getCLogger(MEXMEEsqInsulina.class);

	public MEXMEEsqInsulina(Properties ctx, int EXME_EsqInsulina_ID, String trxName) {
		super(ctx, EXME_EsqInsulina_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEEsqInsulina(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Obtiene los esquemas de insulina de un paciente
	 * 
	 * @param ctx
	 * @param patientId
	 *            id de Paciente
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEsqInsulina> get(Properties ctx, int patientId, String trxName) {
		List<MEXMEEsqInsulina> lista = new ArrayList<MEXMEEsqInsulina>();
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();
		try {

			sql.append(" EXME_EsqInsulina.EXME_Paciente_ID=? ");
			params.add(patientId);
			// sql.append(" AND Folio=(");
			// sql.append(" SELECT COALESCE(MAX(folio),0)  ");
			// sql.append(" FROM EXME_EsqInsulina ");
			// sql.append(" WHERE EXME_CtaPac_ID=? ");
			// sql.append(")  ");
			// params.add(ctaPacID);

			lista = new Query(ctx, Table_Name, sql.toString(), trxName)//
					.setParameters(params)//
					.addAccessLevelSQL(true)//
					.setOnlyActiveRecords(true)//
					.setOrderBy(" Created DESC ")//
					.list();
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString() + " " + params.toString(), e);
		}

		return lista;
	}

	/**
	 * Obtiene los esquemas de insulina disponibles, segun el nivel de acceso, filtra que sean de un
	 * medico y/o paciente, o visibles para todos
	 * 
	 * @param ctx
	 * @param patientId
	 *            Id del paciente
	 * @param medicoID
	 *            Id del medico
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEsqInsulina> get(Properties ctx, int patientId, int medicoID, String trxName) {
		return new Query(ctx, Table_Name, //
				"COALESCE(EXME_Paciente_ID,0) IN (0,?) OR COALESCE(EXME_Medico_ID,0) IN (0,?)", trxName)//
				.setOnlyActiveRecords(true)//
				.setParameters(patientId, medicoID)//
				.addAccessLevelSQL(true)//
				.setOrderBy("AD_Client_ID, AD_Org_ID DESC, EXME_Medico_ID, EXME_Paciente_ID, Tipo ")//
				.list();
	}

	/**
	 * Obtiene todos los esquemas de insulina disponibles, segun el nivel de acceso
	 * 
	 * @param ctx
	 * @param medicoID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEEsqInsulina> getAll(Properties ctx, String trxName) {
		final StringBuilder sql = new StringBuilder();
		return new Query(ctx, Table_Name, //
				sql.toString(), trxName)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy("AD_Client_ID, AD_Org_ID DESC, EXME_Medico_ID, EXME_Paciente_ID, Tipo ")//
				.list();
	}

	/**
	 * Recupera todos los registros de EXME_EsqInsulinaLine relacionados
	 * 
	 * @return
	 */
	public List<MEXMEEsqInsulinaLine> getDetail() {
		return MEXMEEsqInsulinaLine.getDetail(getCtx(), getEXME_EsqInsulina_ID(), get_TrxName());
	}

	/**
	 * Crea un nuevo objeto de tipo {@link MEXMEEsqInsulinaLine}
	 * 
	 * @return
	 */
	public MEXMEEsqInsulinaLine newMEXMEEsqInsulinaLine() {
		return new MEXMEEsqInsulinaLine(this);
	}

	/**
	 * Recupera el nombre del valor de referencia seleccionado
	 * 
	 * @return
	 */
	public String getTipoStr() {
		return MRefList.getListName(getCtx(), TIPO_AD_Reference_ID, StringUtils.trimToEmpty(super.getTipo()));
	}

	/**
	 * Regresa el valor String del tipo de esquema (System/Cliente/Organizacion/Medico/Paciente)
	 * 
	 * @return
	 */
	public String getLevelStr() {
		final StringBuilder str = new StringBuilder();
		// Checkbox para indicar que tipo de SlidingScale: System/Organizacion/Medico/Paciente
		if (getEXME_Paciente_ID() > 0) {
			str.append(Utilerias.getMsg(getCtx(), "aplicacion.paciente"));
			str.append(": ");
			str.append(MEXMEPaciente.getNombre_Pac(getEXME_Paciente_ID(), null));
		} else if (getEXME_Medico_ID() > 0) {
			str.append(Utilerias.getMsg(getCtx(), "abstracting.physician"));
			str.append(": ");
			str.append(MEXMEMedico.nombreMedico(getCtx(), getEXME_Medico_ID()));
		} else if (getAD_Client_ID() == 0) {
			str.append(Utilerias.getMsg(getCtx(), "msj.system"));
		} else if (getAD_Org_ID() == 0) {
			str.append(Utilerias.getMsg(getCtx(), "login.client"));
		} else {
			str.append(Utilerias.getMsg(getCtx(), "citas.organizacion"));
		}
		return str.toString();
	}

	/**
	 * Regresa el nombre del registro y el tipo de insulina
	 * 
	 * @return
	 */
	public String getSummary() {
		final StringBuilder str = new StringBuilder();
		str.append(getName()).append(" ");
		str.append(getTipoStr());// .append(" (");
		// str.append(getLevelStr()).append(") ");
		return str.toString();
	}

	/**
	 * Busca un registro de EXME_EsqInsulinaLine que este dentro del rango de glucosa definido
	 * 
	 * @param resultValue
	 *            Valor del resultado de glucosa
	 * @return
	 */
	public MEXMEEsqInsulinaLine getMatchResult(final BigDecimal resultValue) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" EXME_EsqInsulina_ID=? ");
		sql.append(" AND ? BETWEEN Lim_Inferior AND Lim_Superior ");
		return new Query(getCtx(), MEXMEEsqInsulinaLine.Table_Name, //
				sql.toString(), get_TrxName())//
				.setOnlyActiveRecords(true)//
				.setParameters(getEXME_EsqInsulina_ID(), resultValue)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" Lim_Inferior, Lim_Superior, Unidad ")//
				.first();
	}
}