/**
 * Created on 19-june-2007
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modelo de Diario de Enfermeria
 * 
 * @author M Garcia <br>
 *         Modificado por Lorena Lama, Oct 2012
 */
public class MEXMEDiarioEnf extends X_EXME_DiarioEnf {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 1115307595325206676L;
	/** Static Logger */
	private static CLogger		s_log				= CLogger.getCLogger(MEXMEDiarioEnf.class);

	/**
	 * Regresa los Diarios de Enfermeria de una Cuenta Paciente
	 * 
	 * @param ctx Propiedades
	 * @param ctaPacId ID de una Cuenta Paciente
	 * @param sinEgresar
	 *            true: solo aquellos diarios que no tienen fecha de egreso (activos)
	 *            false: solo los diarios que cuentan con fecha de egreso (cerrados),
	 *            null: no agrega condicion
	 * @param trxName Nombre de la transaccion
	 */
	public static MEXMEDiarioEnf[] get(final Properties ctx, final int ctaPacId, final Boolean sinEgresar, final String trxName) {
		final List<MEXMEDiarioEnf> resultados = getList(ctx, ctaPacId, sinEgresar, trxName);
		final MEXMEDiarioEnf[] diariosEnf = new MEXMEDiarioEnf[resultados.size()];
		resultados.toArray(diariosEnf);
		return diariosEnf;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param nurseID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEDiarioEnf> getByNurse(final Properties ctx, final int nurseID, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" exme_enfermeria_id=? ");
		sql.append(" AND Fecha_Egreso IS NULL ");

		return new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(nurseID)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" exme_ctapac_id, exme_diarioenf_id DESC ")//
			.list();
	}

	/**
	 * Regresa el diario de una cuenta paciente
	 * 
	 * @param ctx Propiedades
	 * @param trxName Nombre de la transaccion
	 * @param ctaPacId ID de una Cuenta Paciente
	 * @deprecated una cuenta paciente puede tener múltiples diarios.
	 */
	@Deprecated
	public static MEXMEDiarioEnf getDiario(final Properties ctx, final int ctaPacId, final String trxName) {
		MEXMEDiarioEnf diarioEnf = null;
		try {
			diarioEnf = new Query(ctx, Table_Name, " EXME_DiarioEnf.EXME_CTAPAC_ID=? ", trxName)//
				.setParameters(ctaPacId)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.first();
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, e.toString(), e);
		}
		return diarioEnf;
	}
	
	/**
	 * Regresa el diario de una cuenta paciente
	 * 
	 * @param ctx Propiedades
	 * @param trxName Nombre de la transaccion
	 * @param ctaPacId ID de una Cuenta Paciente
	 * @param nurseId ID de la enfermera
	 */
	public static int getDiarioId(final Properties ctx, final int ctaPacId, final int nurseId, final String trxName) {
		int diarioEnf = 0;
		try {
			diarioEnf = new Query(ctx, Table_Name, " EXME_DiarioEnf.EXME_CTAPAC_ID=? AND EXME_DiarioEnf.EXME_Enfermeria_ID=? AND EXME_DiarioEnf.Fecha_Egreso IS NULL ", trxName)//
				.setParameters(ctaPacId, nurseId)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.firstId();
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, e.toString(), e);
		}
		return diarioEnf;
	}

	/**
	 * Regresa el diario actual de una cuenta paciente si corresponde con la estaci�n de servicio
	 * 
	 * @param ctx Propiedades
	 * @param trxName Nombre de la transaccion
	 * @param ctaPacId ID de una Cuenta Paciente
	 * 
	 */
	public static int getDiarioActual(final Properties ctx, final int ctaPacID, final boolean validaEgreso, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		int diarioEnf = -1;
		try {
			diarioEnf = getQuery(ctx, ctaPacID, validaEgreso, trxName).firstId();
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		return diarioEnf;
	}

	/**
	 * Regresa el diario actual de una cuenta paciente si corresponde con la estaci�n de servicio
	 * 
	 * @param ctx Propiedades
	 * @param trxName Nombre de la transaccion
	 * @param ctaPacId ID de una Cuenta Paciente
	 * 
	 */
	public static MEXMEDiarioEnf getDiarioActual(final Properties ctx, final int ctaPacID, final String trxName,
		final boolean validaEgreso) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		MEXMEDiarioEnf diarioEnf = null;
		try {
			diarioEnf = getQuery(ctx, ctaPacID, validaEgreso, trxName).first();
		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		return diarioEnf;
	}

	/**
	 * Regresa el diario relacionado a una Seccion de forma
	 * Nota: un diario puede tener multiples formas
	 * @param ctx Propiedades
	 * @param trxName Nombre de la transaccion
	 * @param encounterFormId ID de la seccion de forma
	 * 
	 */
	public static int getFromEncounterForm(final Properties ctx, final int encounterFormId, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EXME_DiarioEnf_ID FROM EXME_DiarioEnfForm WHERE EXME_EncounterForms_ID=? AND isActive='Y' ");
		return DB.getSQLValue(trxName, sql.toString(), encounterFormId);
	}

	/**
	 * Regresa los Diarios de Enfermeria de una Cuenta Paciente
	 * 
	 * @param ctx Propiedades
	 * @param ctaPacId ID de una Cuenta Paciente
	 * @param sinEgresar
	 *            true: solo aquellos diarios que no tienen fecha de egreso (activos)
	 *            false: solo los diarios que cuentan con fecha de egreso (cerrados),
	 *            null: no agrega condicion
	 * @param trxName Nombre de la transaccion
	 */
	public static List<MEXMEDiarioEnf> getList(final Properties ctx, final int ctaPacId, final Boolean sinEgresar, final String trxName) {

		List<MEXMEDiarioEnf> resultados = new ArrayList<MEXMEDiarioEnf>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		try {
			sql.append(" EXME_DiarioEnf.EXME_CTAPAC_ID=? ");
			if (sinEgresar != null) {
				sql.append(" AND EXME_DiarioEnf.Fecha_Egreso IS ");
				sql.append(sinEgresar ? " NULL " : " NOT NULL ");
			}
			resultados = new Query(ctx, Table_Name, sql.toString(), trxName)//
				.setParameters(ctaPacId)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setOrderBy(" EXME_DiarioEnf.EXME_CtaPac_ID DESC, EXME_DiarioEnf.Fecha_Ingreso DESC, EXME_DiarioEnf.Fecha_Egreso DESC ")//
				.list();

		} catch (final Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			resultados = new ArrayList<MEXMEDiarioEnf>();
		}
		return resultados;
	}
	
	/**
	 * Regresa el diario actual de una cuenta paciente si corresponde con la estaci�n de servicio
	 * 
	 * @param ctx Propiedades
	 * @param trxName Nombre de la transaccion
	 * @param ctaPacId ID de una Cuenta Paciente
	 * 
	 */
	public static Query getQuery(final Properties ctx, final int ctaPacID, final boolean validaEgreso, final String trxName) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// obtiene todos los diarios de una cuenta paciente
		sql.append("     EXME_DiarioEnf.EXME_CtaPac_ID=? ");
//		if(estServID > 0){// valida que sea de la misma estacion de servicio
//			sql.append(" AND EXME_DiarioEnf.EXME_EstServ_ID=? ");
//		}
		if (validaEgreso) {//valida si esta abierto
			sql.append(" AND EXME_DiarioEnf.Fecha_Egreso IS NULL");
		}
		final Query query = new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(ctaPacID)// parametros
			.setOnlyActiveRecords(true)// solo los activos
			.addAccessLevelSQL(true)// para el cliente/org de logueo
			.setOrderBy(" EXME_DiarioEnf_ID DESC ");// el mas reciente
//		if (estServID > 0) {
//			query.setParameters(ctaPacID, estServID);// parametros
//		} else {
//			query.setParameters(ctaPacID);// parametros
//		}
		return query;
	}

//	public static String getReceiveEncountersSQL(final Properties ctx, final int nurseID) {
//		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
//		sql.append(" SELECT ");
//		if (DB.isOracle()) { // Note: LISTAGG para Oracle11 !!
//			sql.append(" wm_concat(distinct exme_ctapac_id) ");
//		} else if (DB.isPostgreSQL()) {
//			/* RQM 3651
//			 * Se agrega funcion array_to_string, para obtener los valores de la consulta separados por comas,
//			 * de lo contrario la consulta retorna los valores concatenados dentro de llaves {1,2,3,4,5} */
//			sql.append(" array_to_string(ARRAY_AGG(distinct exme_ctapac_id),',')  ");
//		} else {
//			sql.append(" null ");
//		}
//		sql.append(" FROM EXME_DiarioEnf ");
//		sql.append(" WHERE exme_enfermeria_id=").append(nurseID);
//		sql.append(" AND Fecha_Egreso IS NULL ");
//		sql.append(" AND IsActive='Y' ");
//		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
//		return sql.toString();
//	}

	private MEXMEMotivoEgreso				m_motivoegreso;
	private MDiagnostico					diagnostico;
	private MDiagnostico					diagnosticoEgreso;
	private MEXMEMotivoTraslado				motivoTraslado;
	private MEXMEProcedencia				procedencia;
	private MEXMEProcedencia				procedenciaEgr;
	private MEXMEEdoConciencia				edoConciencia;
	private MEXMEFnRespiratoria				fnRespiratoria;
	private MEXMEApoyoVentilatorio			apoyoVent;
	private MEXMEEstServ					estServicio;
	private MEXMECtaPac						ctapac;

	private int								actPacienteID;

	private String							motora;
	private String							pupilasName;
	private String							verbal;
	private String							ocular;
	private String							riesgoCaidaI;
	private String							riesgoCaidaE;

	private List<MEXMEManejoDolor>			lstManejoDolor;
	private List<MEXMEValCorporal>			lstValCorporal;
	private List<MEXMEEvalPiel>				lstEvalPiel;
	private List<MEXMEPrescProcedimiento>	lstProcEnfermeria;

	private List<MEXMEEgresoPresc>			lstEgresos;
	private List<MEXMEIngreso>				lstIngresos;
	private List<MEXMEIngreso>				lstMeals;
	private List<MEXMECateterEnf>			lstCateter;

	private List<MEXMEEsqInsulinaDet>		lstInsulinas;
	private List<MEXMEInsulinaEstudios>		lstHistoriaEstudio;

	private List<MEXMEMultistix>			lstMultistix;
	/** Descriptive diagnostic */
	private String							diagnosisText;
	/** Descriptive discharge diagnostic 2 */
	private String							dischargeDiagText;

	/**
	 * Constructor de MEXMEDiarioEnf
	 * 
	 * @param ctx Propiedades
	 * @param EXME_DiarioEnf_ID ID del Diario de Enfermeria
	 * @param trxName Nombre de la transaccion
	 * 
	 */
	public MEXMEDiarioEnf(final Properties ctx, final int EXME_DiarioEnf_ID, final String trxName) {
		super(ctx, EXME_DiarioEnf_ID, trxName);
		if (is_new()) { // valores por defecto del contexto
			setEXME_Enfermeria_Ing(Env.getEXME_Enfermeria_ID(ctx));
			setEXME_Enfermeria_ID(Env.getEXME_Enfermeria_ID(ctx));
			setEXME_EstServ_ID(Env.getEXME_EstServ_ID(ctx));
			setFecha_Ingreso(Env.getCurrentDate());
		}
	}

	/**
	 * Constructor de MEXMEDiarioEnf
	 * 
	 * @param ctx Propiedades
	 * @param rs Resultset con que se crea el objeto
	 * @param trxName Nombre de la transaccion
	 * 
	 */
	public MEXMEDiarioEnf(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean afterSave(final boolean newRecord, final boolean success) {
		return success && saveDiag(newRecord);
	}

	/**
	 * Before Save
	 * 
	 * @param newRecord
	 * @return true if it can be sabed
	 */
	@Override
	protected boolean beforeSave(final boolean newRecord) {
		if (getLine() == 0) {// Get Line No
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			sql.append("SELECT COALESCE(MAX(Line),0)+10 FROM EXME_DiarioEnf WHERE EXME_CtaPac_ID=?");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", Table_Name));
			setLine(DB.getSQLValue(get_TrxName(), sql.toString(), getEXME_CtaPac_ID()));
		}
		return true;
	}

	public int getActPacienteID() {
		if (actPacienteID <= 0 && getEXME_CtaPac_ID() > 0) {
			final MEXMEActPaciente actPac = MEXMEActPaciente.getActPaciente(getCtx(), getEXME_CtaPac_ID(), null, get_TrxName());
			actPacienteID = actPac == null ? 0 : actPac.getEXME_ActPaciente_ID();
		}
		return actPacienteID;
	}

	public MEXMEApoyoVentilatorio getApoyoVent() {
		if (apoyoVent == null) {
			apoyoVent = new MEXMEApoyoVentilatorio(getCtx(), getEXME_ApoyoVentilatorio_ID(), null);
		}
		return apoyoVent;
	}
	
	public String getApoyoVentName() {
		return getApoyoVent().getName();
	}

	public String getDiagnosisText() {
		if (diagnosisText == null && get_ID() > 0) {
			diagnosisText = getDiagnosisText(get_ColumnIndex(COLUMNNAME_EXME_Diagnostico_ID));
		}
		return diagnosisText;
	}

	private String getDiagnosisText(final int columnIdx) {
		// The diagnosis text is not saved in this table
		final MActPacienteDiag actdiag =
			MActPacienteDiag.get(getCtx(), get_ID(), get_Table_ID(), getActPacienteID(), get_ValueAsInt(columnIdx), null, get_ColumnName(columnIdx),
				get_TrxName());
		return actdiag == null ? "" : actdiag.getDiagnosis() == null ? "" : actdiag.getDiagnosis();
	}

	public MDiagnostico getDiagnostico() {
		if (diagnostico == null) {
			diagnostico = new MDiagnostico(getCtx(), getEXME_Diagnostico_ID(), null);
		}
		return diagnostico;
	}

	public MDiagnostico getDiagnosticoEgreso() {
		if (diagnosticoEgreso == null) {
			diagnosticoEgreso = new MDiagnostico(getCtx(), getEXME_DiagnosticoEgreso_ID(), null);
		}
		return diagnosticoEgreso;
	}

	public String getDischargeDiagText() {
		if (dischargeDiagText == null && get_ID() > 0) {
			dischargeDiagText = getDiagnosisText(get_ColumnIndex(COLUMNNAME_EXME_DiagnosticoEgreso_ID));
		}
		return dischargeDiagText;
	}

	public MEXMEEdoConciencia getEdoConciencia() {
		if (edoConciencia == null) {
			edoConciencia = new MEXMEEdoConciencia(getCtx(), getEXME_EdoConciencia_ID(), null);
		}
		return edoConciencia;
	}
	
	public String getEdoConcienciaName() {
		return getEdoConciencia().getName();
	}

	public MEXMEEstServ getEstServicio() {
		if (estServicio == null) {
			estServicio = new MEXMEEstServ(getCtx(), getEXME_EstServ_ID(), get_TrxName());
		}
		return estServicio;
	}

	@Override
	public MEXMECtaPac getEXME_CtaPac() throws RuntimeException {
		if (ctapac == null && getEXME_CtaPac_ID() > 0) {
			ctapac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return ctapac;
	}

	public MEXMEFnRespiratoria getFnRespiratoria() {
		if (fnRespiratoria == null) {
			fnRespiratoria = new MEXMEFnRespiratoria(getCtx(), getEXME_FnRespiratoria_ID(), null);
		}
		return fnRespiratoria;
	}
	
	public String getFnRespiratoriaName() {
		return getFnRespiratoria().getName();
	}

	/** Cateteres, Sondas y Drenajes */
	public List<MEXMECateterEnf> getLstCateter() {
		if (lstCateter == null) {
			lstCateter = MEXMECateterEnf.get(getCtx(), getEXME_CtaPac_ID(), getEXME_DiarioEnf_ID(), null);
		}
		return lstCateter;
	}

	/** Egresos */
	public List<MEXMEEgresoPresc> getLstEgresos() {
		if (lstEgresos == null) {
			lstEgresos = MEXMEEgresoPresc.getList(getCtx(), getEXME_CtaPac_ID(), null,  getEXME_DiarioEnf_ID(), -1, null);
		}
		return lstEgresos;
	}

	/** Protocolo de ulceras */
	public List<MEXMEEvalPiel> getLstEvalPiel() {
		if (lstEvalPiel == null) {
			lstEvalPiel = MEXMEEvalPiel.get(getCtx(), getEXME_CtaPac_ID(), getEXME_DiarioEnf_ID(), null);
		}
		return lstEvalPiel;
	}

	/** Historico de Reg. de estudios */
	public List<MEXMEInsulinaEstudios> getLstHistoriaEstudio() {
		if (lstHistoriaEstudio == null) {
			lstHistoriaEstudio = MEXMEInsulinaEstudios.get(getCtx(), getEXME_CtaPac_ID(), getEXME_DiarioEnf_ID(), null);
		}
		return lstHistoriaEstudio;
	}

	/** Ingresos  */
	public List<MEXMEIngreso> getLstIngresos() {
		if (lstIngresos == null) {
			lstIngresos = MEXMEIngreso.getIntake(getCtx(), getEXME_CtaPac_ID(),null, getEXME_DiarioEnf_ID(),  -1, null);
		}
		return lstIngresos;
	}
	
	/** Ingresos (intake & meal) */
	public List<MEXMEIngreso> getLstIngresosAll() {
		List<MEXMEIngreso> lst = new ArrayList<MEXMEIngreso>();
		if (getLstIngresos() != null) {
			lst.addAll(getLstIngresos());
		}
		if (getLstMeals() != null) {
			lst.addAll(getLstMeals());
		}
		return lstIngresos;
	}
	
	/** Control Diabetico */
	public List<MEXMEEsqInsulinaDet> getLstInsulinas() {
		if (lstInsulinas == null) {
			lstInsulinas = MEXMEEsqInsulinaDet.get(getCtx(), getEXME_CtaPac_ID(), getEXME_DiarioEnf_ID(), null);
		}
		return lstInsulinas;
	}

	/** Manejo de Dolor */
	public List<MEXMEManejoDolor> getLstManejoDolor() {
		if (lstManejoDolor == null) {
			lstManejoDolor = MEXMEManejoDolor.get(getCtx(), getEXME_CtaPac_ID(), getEXME_DiarioEnf_ID(), null);
		}
		return lstManejoDolor;
	}

	/** Ingresos (inteake & meal) */
	public List<MEXMEIngreso> getLstMeals() {
		if (lstMeals == null) {
			lstMeals = MEXMEIngreso.getMeals(getCtx(),  getEXME_CtaPac_ID(), null, getEXME_DiarioEnf_ID(), -1, null);
		}
		return lstMeals;
	}

	/** Tira Reactiva */
	public List<MEXMEMultistix> getLstMultistix() {
		if (lstMultistix == null) {
			lstMultistix = MEXMEMultistix.get(getCtx(), getEXME_CtaPac_ID(), getEXME_DiarioEnf_ID(), null);
		}
		return lstMultistix;
	}

	/** Procedimientos de Enf */
	public List<MEXMEPrescProcedimiento> getLstProcEnfermeria() {
		if (lstProcEnfermeria == null) {
			lstProcEnfermeria = MEXMEPrescProcedimiento.get(getCtx(), getEXME_CtaPac_ID(), getEXME_DiarioEnf_ID(), null);
		}
		return lstProcEnfermeria;
	}

	/** Valoracion Corporal */
	public List<MEXMEValCorporal> getLstValCorporal() {
		if (lstValCorporal == null) {
			lstValCorporal = MEXMEValCorporal.get(getCtx(), getEXME_CtaPac_ID(), getEXME_DiarioEnf_ID(), null);
		}
		return lstValCorporal;
	}

	public MEXMEMotivoEgreso getMotivoEgreso() {
		if (m_motivoegreso == null && getEXME_MotivoEgreso_ID() > 0) {
			m_motivoegreso = new MEXMEMotivoEgreso(getCtx(), getEXME_MotivoEgreso_ID(), get_TrxName());
		}
		return m_motivoegreso;
	}

	public MEXMEMotivoTraslado getMotivoTraslado() {
		if (motivoTraslado == null) {
			motivoTraslado = new MEXMEMotivoTraslado(getCtx(), getEXME_MotivoTraslado_ID(), null);
		}
		return motivoTraslado;
	}

	public String getMotora() {
		if (motora == null && getGlasgow_Motora() != null) {
			motora = MRefList.getListName(getCtx(), X_EXME_DiarioEnf.GLASGOW_MOTORA_AD_Reference_ID, getGlasgow_Motora().trim());
		}
		return motora;
	}

	public String getOcular() {
		if (ocular == null && getGlasgow_Ocular() != null) {
			ocular = MRefList.getListName(getCtx(), X_EXME_DiarioEnf.GLASGOW_OCULAR_AD_Reference_ID, getGlasgow_Ocular().trim());
		}
		return ocular;
	}

	public MEXMEProcedencia getProcedencia() {
		if (procedencia == null) {
			procedencia = new MEXMEProcedencia(getCtx(), getEXME_Procedencia_ID(), null);
		}
		return procedencia;
	}

	public MEXMEProcedencia getProcedenciaEgr() {
		if (procedenciaEgr == null) {
			procedenciaEgr = new MEXMEProcedencia(getCtx(), getEXME_Procedencia_Egreso_ID(), null);
		}
		return procedenciaEgr;
	}

	public String getPupilasName() {

		if (pupilasName == null && getPupilas() != null) {
			pupilasName = MRefList.getListName(getCtx(), X_EXME_DiarioEnf.PUPILAS_AD_Reference_ID, getPupilas().trim());
		}
		return pupilasName;
	}

	public String getRiesgoCaidaE() {
		if (riesgoCaidaE == null && getRiesgo_Caida_E() != null) {
			riesgoCaidaE = MRefList.getListName(getCtx(), X_EXME_DiarioEnf.RIESGO_CAIDA_E_AD_Reference_ID, getRiesgo_Caida_E().trim());
		}
		return riesgoCaidaE;
	}

	public String getRiesgoCaidaI() {
		if (riesgoCaidaI == null && getRiesgo_Caida_I() != null) {
			riesgoCaidaI = MRefList.getListName(getCtx(), X_EXME_DiarioEnf.RIESGO_CAIDA_I_AD_Reference_ID, getRiesgo_Caida_I().trim());
		}
		return riesgoCaidaI;
	}

	public String getVerbal() {
		if (verbal == null && getGlasgow_Verbal() != null) {
			verbal = MRefList.getListName(getCtx(), X_EXME_DiarioEnf.GLASGOW_VERBAL_AD_Reference_ID, getGlasgow_Verbal().trim());
		}
		return verbal;
	}

	/**
	 * Save/Update diagnostic of patient
	 * 
	 * @param newRecord
	 * @return true/false if the record was saved
	 * @author lorena lama
	 */
	public boolean saveDiag(final boolean newRecord) {
		boolean saved;
		if (getActPacienteID() <= 0) { // find actPaciente
			saved = false;
		} else {
			saved = saveDiagnosis(newRecord, get_ColumnIndex(COLUMNNAME_EXME_Diagnostico_ID), diagnosisText);
			if (saved) {
				saved = saveDiagnosis(newRecord, get_ColumnIndex(COLUMNNAME_EXME_DiagnosticoEgreso_ID), dischargeDiagText);
			}
		}
		return saved;
	}

	private boolean saveDiagnosis(final boolean newRecord, final int columnIdx, final String diagnosis) {
		boolean saveDiag;
		boolean saved;
		if (newRecord) { // if has a diagnostic
			saveDiag = get_ValueAsInt(columnIdx) > 0 || StringUtils.isNotBlank(diagnosis);
		} else { // if diagnosis code has changed or diagnosis descriptions has been assigned
			saveDiag = is_ValueChanged(columnIdx) || StringUtils.isNotBlank(diagnosis);
		}
		saved = !saveDiag;
		if (saveDiag) { // The diagnosis text is not saved in this table
			saved = MActPacienteDiag.saveDiagnostic(this, getActPacienteID(), get_ColumnName(columnIdx), diagnosis, null);
		}
		return saved;
	}

	/**
	 * Crea la relacion Diario - Encounter Forms
	 * con el objetivo de mantener consistencia en la informacion mostrada
	 * ya que un diario es similar a un documento.
	 * 
	 * @param encounterFormId
	 * @return
	 */
	public boolean saveEncountersForm(int encounterFormId) {
		if (encounterFormId > 0 && get_ID() > 0) {
			if (DB.getSQLValue(get_TrxName(),
				"SELECT COUNT(*) FROM EXME_DiarioEnfForm WHERE EXME_DiarioEnf_ID=? AND EXME_EncounterForms_ID=? AND isActive='Y'",
				getEXME_DiarioEnf_ID(), encounterFormId) <= 0) {
				X_EXME_DiarioEnfForm diarioForm = new X_EXME_DiarioEnfForm(getCtx(), 0, get_TrxName());
				diarioForm.setEXME_DiarioEnf_ID(getEXME_DiarioEnf_ID());
				diarioForm.setEXME_EncounterForms_ID(encounterFormId);
				return diarioForm.save();
			}

		}
		return true;
	}

	public void setDiagnosisText(final String diagnosisText) {
		this.diagnosisText = diagnosisText;
	}

	public void setDischargeDiagText(final String dischargeDiagText) {
		this.dischargeDiagText = dischargeDiagText;
	}
		
	public String getEnfermeriaIng() {
		return MEXMEEnfermeria.getFullName(getCtx(), getEXME_Enfermeria_Ing(), get_TrxName());
	}
	
	public String getEnfermeriaEgr() {
		return MEXMEEnfermeria.getFullName(getCtx(), getEXME_Enfermeria_Egr(), get_TrxName());
	}
	
	public String getEnfermeria() {
		return MEXMEEnfermeria.getFullName(getCtx(), getEXME_Enfermeria_ID(), get_TrxName());
	}
}
