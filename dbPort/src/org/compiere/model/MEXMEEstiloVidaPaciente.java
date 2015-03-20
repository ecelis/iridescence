package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Utilerias;

/**
 * Clase para obtener los estatus de fumador registrados a los pacientes
 * 
 * @author Lizeth de la Garza <br>
 *         Modified by @author Lorena Lama on March, 2011<br>
 *         Modified by @author Lorena Lama on August, 2012
 */
public class MEXMEEstiloVidaPaciente extends X_EXME_EstiloVidaPaciente {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8774607010692171756L;

	private static CLogger log = CLogger.getCLogger(MEXMEEstiloVidaPaciente.class);

	private MEXMEEstiloVida estiloVida = null;
	private MActPacienteDiag actPacDiag = null;
	private MDiagnostico diagnostico = null;
	private String diagnosisText = null;

	public MEXMEEstiloVidaPaciente(Properties ctx, int EXME_EstiloVidaPaciente_ID, String trxName) {
		super(ctx, EXME_EstiloVidaPaciente_ID, trxName);
	}

	public MEXMEEstiloVidaPaciente(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 * @return
	 */
	public MDiagnostico getDiagnostico() {
		if (diagnostico == null && getActPacienteDiag() != null) {
			diagnostico = getActPacienteDiag().getDiagnostico();
			diagnosisText = getActPacienteDiag().getDiagnosis();
		}
		return diagnostico;
	}

	/**
	 * 
	 * @return
	 */
	public MActPacienteDiag getActPacienteDiag() {
		if (actPacDiag == null && getEXME_ActPacienteDiag_ID() > 0) {
			actPacDiag = new MActPacienteDiag(getCtx(), getEXME_ActPacienteDiag_ID(), get_TrxName());
		}
		return actPacDiag;
	}

	/**
	 * 
	 * @return
	 */
	public MEXMEEstiloVida getEstiloVida() {
		if (estiloVida == null && getEXME_EstiloVida_ID() > 0) {
			estiloVida = new MEXMEEstiloVida(getCtx(), getEXME_EstiloVida_ID(), get_TrxName());
		}
		return estiloVida;
	}

	public void setEstiloVida(MEXMEEstiloVida estiloVida) {
		this.estiloVida = estiloVida;
	}

	/**
	 * Obtiene Nombre del Diagnostico
	 * 
	 * @return String
	 */
	public String getNombreDiag() {
		return getDiagnostico() == null ? getActPacienteDiag() == null ? " " : getDiagnosticText() : getDiagnostico()
				.getName();
	}

	/**
	 * Obtiene el nombre del estatus
	 * 
	 * @return String
	 */
	public String getNombreEstilo() {
		return getEstiloVida() == null ? " " : getEstiloVida().getName();
	}

//	public String[] getColumns() {
//		return new String[] { "idColumn", "nombreEstilo", "nombreDiag", "observaciones", "fechaIni", "fechaFin" };
//	}
//
//	private IDColumn idColumn = null;
//
//	public IDColumn getIdColumn() {
//		if (idColumn == null) {
//			idColumn = new IDColumn(getEXME_EstiloVidaPaciente_ID());
//		}
//		return idColumn;
//	}
//
//	public void setIdColumn(IDColumn idColumn) {
//		this.idColumn = idColumn;
//	}

	/**
	 * Obtener el historial de estatus de fumador del paciente
	 * 
	 * @param ctx
	 *            Contexto de la aplicacion
	 * @param patientID
	 *            ID del paciente
	 * @param trxName
	 *            Nombre de la transaccion
	 * @return List<MEXMEPacienteFumador> lista del historial
	 * @author Lizeth de la Garza
	 */
	public static List<MEXMEEstiloVidaPaciente> getHistoria(Properties ctx, int patientID, String trxName) {
		return getHistoria(ctx, patientID, false, false, null);
	}

	/**
	 * Obtener el historial de estatus de fumador del paciente
	 * 
	 * @param ctx
	 *            Contexto de la aplicacion
	 * @param patientID
	 *            ID del paciente
	 * @param onlyActive Muestra solo los registros activos
	 * @param trxName
	 *            Nombre de la transaccion
	 * @return List<MEXMEPacienteFumador> lista del historial
	 */
	public static List<MEXMEEstiloVidaPaciente> getHistoria(Properties ctx, int patientID, boolean onlyActive, boolean proMujer,String trxName) {
//			int AD_Org_ID, StringBuilder join, StringBuilder where) {

		List<MEXMEEstiloVidaPaciente> lst = new ArrayList<MEXMEEstiloVidaPaciente>();
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

//		ResultSet rs = null;
//		PreparedStatement pstmt = null;
		try {

//			sql.append("SELECT EXME_EstiloVidaPaciente.* FROM EXME_EstiloVidaPaciente ");
//			if(join != null && join.length() > 0){
//				sql.append(join);
//			}
//			if(where != null && where.toString().toUpperCase().contains(" WHERE ")){
//				sql.append(where);
//				sql.append(" AND EXME_EstiloVidaPaciente.EXME_Paciente_ID=? ");
//			} else {
//				sql.append(" WHERE EXME_EstiloVidaPaciente.EXME_Paciente_ID=? ");
//				if(where != null){
//					sql.append(where);
//				}
//			}
//			if(Env.getUserPatientID(ctx) < 1){
//				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", POInfo.getPOInfo(ctx, Table_ID), null, AD_Org_ID));
//			}
//			sql.append(" ORDER BY EXME_EstiloVidaPaciente.CREATED DESC ");
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, patientID);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				final MEXMEEstiloVidaPaciente estiloVida = new MEXMEEstiloVidaPaciente(ctx, rs, trxName);
//				lst.add(estiloVida);
//			}
			//Card #1545 ProMujer 
			sql.append(" EXME_EstiloVidaPaciente.EXME_Paciente_ID=? ");
			if(proMujer){
				sql.append(MClientInfo.getClientSQL(ctx, Table_Name));
			}
			lst = new Query(ctx, Table_Name, sql.toString(), trxName)//
			.setParameters(patientID)//
			.setOnlyActiveRecords(onlyActive)//
			.addAccessLevelSQL(Env.getUserPatientID(ctx) < 1 && !proMujer)//
			.setOrderBy(" EXME_EstiloVidaPaciente.CREATED DESC ")//
			.list();

		} catch (Exception e) {
			log.log(Level.SEVERE, e.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
		}

		return lst;
	}

	public String getDiagnosticText() {
		return diagnosisText;
	}

	public void setDiagnosticText(String diagnosisText) {
		this.diagnosisText = diagnosisText;
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		// save the diagnosis selected
		boolean deactivateDiag = false;
		final int originalActDiag = get_ValueOldAsInt(COLUMNNAME_EXME_ActPacienteDiag_ID);
		if (isActive()) {
			if (getDiagnosisKN() != null
					&& (getDiagnosisKN().getKey() > 0 || StringUtils.isNotEmpty(getDiagnosisKN().getName()))) {
				final MActPacienteDiag actPacDiag = MActPacienteDiag.saveDiag(this, getActPacienteId(true),
						"EXME_Diagnostico_ID", getDiagnosisKN().getKey(), getDiagnosisKN().getName(), null);
				if (actPacDiag == null || !actPacDiag.save()) {
					return false;
				}
				setEXME_ActPacienteDiag_ID(actPacDiag.get_ID());
				deactivateDiag = originalActDiag > 0 && originalActDiag != getEXME_ActPacienteDiag_ID();
			} else if(originalActDiag > 0){ // si se removio
				deactivateDiag = true;
				setEXME_ActPacienteDiag_ID(0);
			}
		} else {
			deactivateDiag = getEXME_ActPacienteDiag_ID() > 0;
		}
		// cancelar diag
		if (deactivateDiag) {
			final MActPacienteDiag actPacDiag = new MActPacienteDiag(getCtx(), originalActDiag, get_TrxName());
			if(!actPacDiag.is_new()){
				actPacDiag.setIsActive(false);
				if (!actPacDiag.save()) {
					log.log(Level.SEVERE, Utilerias.getMsg(getCtx(), "diarioEnf.error.noSave"));
					return false;
				}
			}
		}
		return super.beforeSave(newRecord);
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success) {
			if (isActive()) {
				// updates record_id only for new lifestyle record
				if (newRecord && getEXME_ActPacienteDiag_ID() > 0) {
					final MActPacienteDiag actPacDiag = new MActPacienteDiag(getCtx(), getEXME_ActPacienteDiag_ID(),
							get_TrxName());
					actPacDiag.setRecord_ID(get_ID());
					if (!actPacDiag.save()) {
						log.log(Level.SEVERE, Utilerias.getMsg(getCtx(), "diarioEnf.error.noSave"));
						return false;
					}
				}
				// sin o es un nuevo registro guarda el log de edicion
				if (!newRecord) {
					if (!saveLog()) {
						log.log(Level.SEVERE, Utilerias.getMsg(getCtx(), "diarioEnf.error.noSave"));
						return false;
					}
				}
			}
			if (!saveSmokingMetrics(newRecord)) {
				log.log(Level.SEVERE, Utilerias.getMsg(getCtx(), "diarioEnf.error.noSave"));
				return false;
			}
		}

		return super.afterSave(newRecord, success);
	}

	

	/**
	 * gets the patient activity using the table exme_actpacientediag
	 * 
	 * @param createNew
	 *            if true, creates a new exme_actapaciente record
	 * @return
	 */
	public int getActPacienteId(final boolean createNew) {
		if (get_ID() > 0 && getEXME_ActPacienteDiag_ID() > 0) {
			return getActPacienteDiag().getEXME_ActPaciente_ID();
		} else if (createNew) {
			MEXMEActPaciente actPac = new MEXMEActPaciente(Env.getCtx(), 0, get_TrxName());
			actPac.setName(Utilerias.getMsg(getCtx(), "msg.estiloVida") + getEXME_Paciente_ID());
			actPac.setEXME_Paciente_ID(getEXME_Paciente_ID());
			actPac.setFecha(Env.getCurrentDate());
			actPac.setVacunaPendiente(false);
			// actPac.setPosted(false);
			actPac.setProcessed(false);
			actPac.setTipoArea(MEXMEActPaciente.TIPOAREA_Admission);

			if (!actPac.save()) {
				throw new MedsysException(Utilerias.getMsg(getCtx(), "diarioEnf.error.noSave"));
			}
			return actPac.get_ID();
		}
		return -1;
	}

	/**
	 * Saves lifestyle changes
	 * 
	 * @return
	 */
	public boolean saveLog() {
		final MEXMEEstiloVidaPacDet det = new MEXMEEstiloVidaPacDet(getCtx(), 0, get_TrxName());
		det.setEXME_EstiloVidaPaciente_ID(get_ValueOldAsInt(COLUMNNAME_EXME_EstiloVidaPaciente_ID));
		det.setEXME_ActPacienteDiag_ID(get_ValueOldAsInt(COLUMNNAME_EXME_ActPacienteDiag_ID));
		det.setObservaciones((String) get_ValueOld(COLUMNNAME_Observaciones));
		det.setSeguimiento((String) get_ValueOld(COLUMNNAME_Seguimiento));
		det.setFechaIni((Timestamp) get_ValueOld(COLUMNNAME_FechaIni));
		det.setFechaFin((Timestamp) get_ValueOld(COLUMNNAME_FechaFin));
		return det.save();
	}

	/**
	 * Create/update the smoking metrics
	 * 
	 * @param newRecord
	 * @return
	 */
	public boolean saveSmokingMetrics(final boolean newRecord) {
		if (MEXMEEstiloVida.TIPOESTILO_SmokerStatus.equals(getEstiloVida().getTipoEstilo())
				&& !(Constantes.NOTSMOKER.equals(getEstiloVida().getValue()))) {
			MEXMEMetricasFumador metricas = null;
			if (!newRecord) {
				metricas = MEXMEMetricasFumador.getMetrica(getCtx(), getEXME_EstiloVidaPaciente_ID());
			}
			if (isActive()) {
				if (newRecord || metricas == null) {
					metricas = new MEXMEMetricasFumador(getCtx(), 0, get_TrxName());
					metricas.setEXME_EstiloVidaPaciente_ID(getEXME_EstiloVidaPaciente_ID());
					metricas.setEXME_Paciente_ID(getEXME_Paciente_ID());
				}
				metricas.setObservaciones(getSeguimiento());
				return metricas.save();
			} else if (metricas != null) {
				metricas.setIsActive(false);
				return metricas.save();
			}
		}
		return true;
	}
	
	private KeyNamePair diagnosisKN = null;
	
	public boolean isDiagnosisChanged() {
		boolean retValue;
		if (diagnosisKN == null || (diagnosisKN.getKey() <= 0 && StringUtils.isEmpty(diagnosisKN.getName()))) {
			// si se removio el diagnostico
			retValue = getEXME_ActPacienteDiag_ID() > 0;
		} else if (getEXME_ActPacienteDiag_ID() > 0) {
			// compara los registros
			I_EXME_ActPacienteDiag diag = getEXME_ActPacienteDiag();
			if (diag.getEXME_Diagnostico_ID() > 0 || diagnosisKN.getKey() > 0) {
				retValue = diag.getEXME_Diagnostico_ID() != diagnosisKN.getKey();
			} else {
				retValue = !StringUtils.equalsIgnoreCase(diag.getDiagnosis(), diagnosisKN.getName());
			}
		} else {
			// si se le asigno un diagnostico
			retValue = true;
		}
		return retValue;
	}
	
	public void setDiagnosisKN(int diagnosisId, String diagName) {
		this.diagnosisKN = new KeyNamePair(diagnosisId, diagName);
		if (isDiagnosisChanged()) { // force to update
			set_Value(COLUMNNAME_EXME_ActPacienteDiag_ID, -1);
		}
	}
	public KeyNamePair getDiagnosisKN() {
		return diagnosisKN;
	}
}
