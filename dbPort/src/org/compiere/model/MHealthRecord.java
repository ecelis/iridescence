/**
 * 
 */
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.util.ProductClassList;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Lorena Lama
 * modificado por lcontreras
 * Card #1545 ProMujer 
 */
public class MHealthRecord {

	private static CLogger s_log = CLogger.getCLogger(MHealthRecord.class);

	public MHealthRecord(){
		
	}
	
	public MHealthRecord(Properties ctx, int EXME_Paciente_ID) {
		this(ctx,EXME_Paciente_ID,0);
	}
	
	/**
	 * Constructor para filtrar por medico
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @param EXME_Medico_ID
	 */
	public MHealthRecord(Properties ctx, int EXME_Paciente_ID, int EXME_Medico_ID) {
		this.ctx = ctx;
		this.EXME_Patient_ID = EXME_Paciente_ID;
		this.EXME_Medico_ID = EXME_Medico_ID;
		this.derechoHabiente = MClientInfo.get(ctx).isDerechohabiente();
	}

	private Properties ctx = null;

	private int EXME_Patient_ID = 0;
	
	private int icCtaPacId = 0;
	
	private int EXME_Medico_ID;
	
	private boolean derechoHabiente = false;

	/** Patient BIO */
	private MEXMEPaciente patient = null;
	
    public void setIcCtaPacId(int icCtaPacId) {
        this.icCtaPacId = icCtaPacId;
    }

    public int getIcCtaPacId() {
        return icCtaPacId;
    }

	/**
	 * Obtiene las citas filtradas por medico o por actividad paciente
	 * si se le mandan mayores que 0
	 * @param ctx
	 * @param EXME_Patient_ID
	 * @param EXME_ActPaciente_ID
	 * @param EXME_Medico_ID
	 * @param null
	 * @return
	 */
	public List<MEXMEActPaciente> getVisits() {

		List<Object> lstParam = new ArrayList<Object>();
		StringBuilder where = new StringBuilder();
		where.append(" EXME_ActPaciente.EXME_Paciente_ID=? ");
		where.append(" AND EXME_ActPaciente.EXME_CitaMedica_ID IS NOT NULL ");

		lstParam.add(EXME_Patient_ID);

		if (icCtaPacId > 0) {
			lstParam.add(icCtaPacId);
			where.append(" AND EXME_ActPaciente.EXME_CtaPac_ID=? ");
		}

		if (EXME_Medico_ID > 0) {
			lstParam.add(EXME_Medico_ID);
			where.append(" AND EXME_ActPaciente.EXME_Medico_ID=? ");
		}
		if(derechoHabiente){
			where.append(MClientInfo.getClientSQL(ctx, "EXME_ActPaciente"));
		}
		
		return new Query(ctx, MEXMEActPaciente.Table_Name, where.toString(), null)//
				.setParameters(lstParam)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente)//
				.list();

	}

	public MEXMEActPaciente getLastVisit() {

		if (EXME_Patient_ID <= 0)
			return null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" AND cita.Estatus = ? ");
		if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, "cita"));
		}
		
		final MEXMECitaMedica lastVisit = MEXMECitaMedica.getUltimaCita(ctx, //
				EXME_Patient_ID, // patient
				0, // especialidad - no requerido
				false, // enEjecucion - no requerido
				Env.getUserPatientID(ctx) <= 0 && !derechoHabiente, // accessLevel solo si no es paciente
				sql.toString(), // whereClausse - ejecutadas
				new Object[] {
					MEXMECitaMedica.ESTATUS_Executed
				}, // params - ejecutadas
				null);

		final MEXMEActPaciente actPaciente = new Query(ctx, MEXMEActPaciente.Table_Name, " EXME_CitaMedica_ID=? ", null)//
				.setParameters(lastVisit.getEXME_CitaMedica_ID())//
				.first();
		actPaciente.setCitaMedica(lastVisit);
		return actPaciente;
	}

	public List<MActPacienteDiag> getActiveDiagnosis(){
		return getDiagnosis(MActPacienteDiag.ESTATUS_Active);
	}
	public List<MActPacienteDiag> getDiagnosis() {
		return getDiagnosis(null);
	}
	
	public List<MActPacienteDiag> getDiagnosis(String estatus) {

		final List<Object> params = new ArrayList<Object>();
		StringBuilder join = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		join.append(" INNER JOIN EXME_ActPaciente act ON (");
		join.append(" EXME_ActPacienteDiag.EXME_ActPaciente_ID=act.EXME_ActPaciente_ID ");
		join.append(" AND act.EXME_Paciente_ID=? ");
		params.add(EXME_Patient_ID);
		if (EXME_Medico_ID > 0) {
			join.append(" AND act.EXME_Medico_ID=? ");
			params.add(EXME_Medico_ID);
		}
		if (icCtaPacId > 0) {
			join.append(" AND act.EXME_CtaPac_ID=? ");
			params.add(icCtaPacId);
		}
		join.append(" )");

		if(MCountry.isUSA(ctx)) {
			where.append(" EXME_ActPacienteDiag.Type=? ");
			params.add(MActPacienteDiag.TYPE_CoderFinal);
		} else {// abstracting no aplica para Mexico
			where.append(" 1=1 ");
		}
		if (estatus != null) {
			where.append(" AND EXME_ActPacienteDiag.Estatus=? ");
			params.add(estatus);
		}
		
		List<MActPacienteDiag> list = new Query(ctx, MActPacienteDiag.Table_Name, where.toString(), null)//
		.setJoins(join)//		
		.setParameters(params)//
		.setOnlyActiveRecords(true) //
		.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0)//
		.setOrderBy(" EXME_ActPacienteDiag.EXME_ActPaciente_ID,EXME_ActPacienteDiag.Created,EXME_ActPacienteDiag.EXME_Diagnostico_ID ")
		.list();
		// FIXME: parche, quitar repetidos
		List<String> ids = new ArrayList<>();
		List<MActPacienteDiag> diags = new ArrayList<>();
		for (MActPacienteDiag actDiag : list) {
			if (actDiag.getEXME_Diagnostico_ID() <= 0) {
				if (StringUtils.isNotBlank(actDiag.getDiagnosis())) {
					diags.add(actDiag);
				}
			} else {
				String id = actDiag.getEXME_ActPaciente_ID() + "" + actDiag.getEXME_Diagnostico_ID();
				if (!ids.contains(id)) {
					ids.add(id);
					diags.add(actDiag);
				}
			}
		}
		return diags;
	}

	public MEXMEPaciente getPatient() {
		if (patient == null && EXME_Patient_ID > 0)
			patient = new MEXMEPaciente(ctx, null, EXME_Patient_ID);
		return patient;
	}

	public List<MEXMEPacienteAler> getAllergies(String status) {
		if (status == null) {
			return MEXMEPacienteAler.getHistoria(ctx, EXME_Patient_ID, null);
		} else {
			return MEXMEPacienteAler.getHistoria(ctx, EXME_Patient_ID, " AND trim(PA.ESTATUS)=? ", status);
		}
	}

	public MEXMECitaMedica getNextVisit() {
		StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			where.append(" AND cita.estatus in ( ").append(DB.TO_STRING(MEXMECitaMedica.ESTATUS_Confirmed)).append(", ");
			where.append(DB.TO_STRING(MEXMECitaMedica.ESTATUS_ToBeConfirmed)).append(", ");
			where.append(DB.TO_STRING(MEXMECitaMedica.ESTATUS_InProcess));
			where.append(") AND ");
			
			if (DB.isOracle()) {
				where.append("trunc(cita.fechahrcita,'HH24') ");
			} else if (DB.isPostgreSQL()) {
				where.append("date_trunc('hour', cita.fechahrcita) ");
			}
			
			where.append(">= ");
			
			if (DB.isOracle()) {
				where.append("trunc(sysdate,'HH24')");
			} else if (DB.isPostgreSQL()) {
				where.append("date_trunc('hour', current_date)");
			}
			if(derechoHabiente){
				where.append(MClientInfo.getClientSQL(ctx, "cita"));
			}
		
		return MEXMECitaMedica.getUltimaCita(ctx, EXME_Patient_ID, 0, false, Env.getUserPatientID(ctx) <= 0 && !derechoHabiente, where.toString() , null, null);
		
	}

	public List<MEXMEActPacienteInd> getExams() {
		return getExams(" AND EXME_ActPacienteIndH.DocStatus=? ", MEXMEActPacienteIndH.DOCSTATUS_Completed);
	}

	public List<MEXMEActPacienteInd> getDraftExams() {
		return getExams(" AND EXME_ActPacienteIndH.DocStatus NOT IN (?,?,?) ", MEXMEActPacienteIndH.DOCSTATUS_Voided,
			MEXMEActPacienteIndH.DOCSTATUS_Completed, MEXMEActPacienteIndH.DOCSTATUS_WaitingConfirmation);
	}

	public List<MEXMEActPacienteInd> getCancelledExams() {
		return getExams(" AND EXME_ActPacienteIndH.DocStatus=? ", MEXMEActPacienteIndH.DOCSTATUS_Voided);
	}

	/**
	 * Regresa los servicios de estudio (imagen / lab)
	 * @param draftOrders - true: solicitudes con estatus = DR
	 * @return
	 */
	public List<MEXMEActPacienteInd> getExams(final String whereClause, final Object... arrayParams) {
		final StringBuilder join = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();

		join.append(" INNER JOIN EXME_ActPacienteIndH ON (EXME_ActPacienteIndH.EXME_ActPacienteIndH_ID = EXME_ActPacienteInd.EXME_ActPacienteIndH_ID) ");
		join.append(" INNER JOIN EXME_ActPaciente ON (EXME_ActPacienteIndH.EXME_ActPaciente_ID = EXME_ActPaciente.EXME_ActPaciente_ID ");
		join.append(" AND EXME_ActPaciente.EXME_Paciente_ID=? ");
		params.add(EXME_Patient_ID);
		if (icCtaPacId > 0) {
			join.append(" AND EXME_ActPaciente.EXME_CtaPac_ID=? ");
			params.add(icCtaPacId);
		}
		if (EXME_Medico_ID > 0) {
			join.append(" AND EXME_ActPaciente.EXME_Medico_ID=? ");
			params.add(EXME_Medico_ID);
		}
		join.append(") ");
		join.append(" INNER JOIN M_Product ON (EXME_ActPacienteInd.M_Product_ID = M_Product.M_Product_ID ");
		join.append("AND M_Product.ProductClass IN ('");
		join.append(StringUtils.join(ProductClassList.SERVICE.getProdClassList(), "','"));
		join.append("'))");
		
		where.append(" EXME_ActPacienteIndH.IsService = 'Y' AND EXME_ActPacienteIndH.EXME_Tratamientos_ID IS NULL ");
		if(StringUtils.isNotBlank(whereClause)){
			where.append(whereClause);
			if(arrayParams.length > 0){
				params.addAll(Arrays.asList(arrayParams));
			}
		}
		
		if(derechoHabiente){
			where.append(MClientInfo.getClientSQL(ctx, MEXMEActPacienteInd.Table_Name));
		}
		
		return new Query(ctx, MEXMEActPacienteInd.Table_Name, where.toString(), null)//
				.setJoins(join) //
				.setParameters(params)//
				.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente)//
				.setOnlyActiveRecords(true)//
				.setOrderBy(" EXME_ActPacienteInd.DateOrdered DESC ")//
				.list();
	}

	public List<MProgQuiro> getInterventions(boolean completed) {
		
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if(icCtaPacId > 0) {
			params.add(icCtaPacId);
			where.append(" EXME_ProgQuiro.EXME_CtaPac_ID=? ");
		} else {
			params.add(EXME_Patient_ID);
			where.append(" EXME_ProgQuiro.EXME_Paciente_ID=? ");
		}
		where.append(" AND TRIM(EXME_ProgQuiro.DocStatus)").append(completed ? "=?" : "<>?");
		params.add(MProgQuiro.DOCSTATUS_Closed);

		if(derechoHabiente){
			where.append(MClientInfo.getClientSQL(ctx, MProgQuiro.Table_Name));
		}
		
		return new Query(ctx, MProgQuiro.Table_Name, where.toString(), null)//
		.setParameters(params)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente)//
		.list();
	}

	public List<MEXMEActPacienteIndH> getTreatments() {
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder join = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		// aplicaciones de tratamientos
		join.append(" INNER JOIN EXME_Tratamientos ON (EXME_ActPacienteIndH.EXME_Tratamientos_ID = EXME_Tratamientos.EXME_Tratamientos_ID) ");
		join.append(" INNER JOIN EXME_ActPaciente ON (EXME_ActPacienteIndH.EXME_ActPaciente_ID = EXME_ActPaciente.EXME_ActPaciente_ID ");
		join.append(" AND EXME_ActPaciente.EXME_Paciente_ID=? ");
		params.add(EXME_Patient_ID);
		if (icCtaPacId > 0) {
			join.append(" AND EXME_ActPaciente.EXME_CtaPac_ID=? ");
			params.add(icCtaPacId);
		}
		if (EXME_Medico_ID > 0) {
			join.append(" AND EXME_ActPaciente.EXME_Medico_ID=? ");
			params.add(EXME_Medico_ID);
		}
		join.append(") ");
		return new Query(ctx, MEXMEActPacienteIndH.Table_Name, null, null)//
				.setJoins(join)//
				.setParameters(params)//
				.setOrderBy(" EXME_ActPacienteIndH.DateOrdered DESC ")//
				.list();
	}
	
	/**
	 * Lista de dietas capturadas por el paciente
	 * @return
	 */
	public List<MEXMEPacDieta> getLstPacDiet(){
		return MEXMEPacDieta.getPacientDiets(ctx, EXME_Patient_ID, derechoHabiente,null);
	}
	
	/**
	 * Vacunas aplicadas y no rechazadas del paciente
	 * @return
	 */
	public List<MEXMEHistVacuna> getVaccines(){
		return MEXMEHistVacuna.getVaccines(ctx, EXME_Patient_ID, -1, null);
	}
	
	public List<MEXMEProgRecordatorio> getReminder() {
		return MEXMEProgRecordatorio.getPendingProg(ctx, EXME_Patient_ID, null); //FIXME
	}
	
	public List<MEXMEEstiloVidaPaciente> getLifeStyle(){
		return MEXMEEstiloVidaPaciente.getHistoria(ctx, EXME_Patient_ID, true, derechoHabiente, null);
	}
	/**
	 * Regresa una lista de los medicos que tengan alguna actividad con 
	 * el paciente seleccionado
	 * @return
	 */
	public static List<MEXMEMedico> getAllDoctors(Properties ctx,int EXME_Patient_ID){
		StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MEXMEMedico> lstMedicos = new ArrayList<MEXMEMedico>();

		try{
			sql.append(" SELECT DISTINCT(EXME_MEDICO_ID) FROM ( " )
			.append(" SELECT DISTINCT(EXME_MEDICO_ID) FROM EXME_ACTPACIENTE ")
			.append(" WHERE EXME_PACIENTE_ID = ? AND EXME_MEDICO_ID IS NOT NULL AND EXME_CTAPAC_ID IS NULL ")
			.append(" UNION ")
			.append(" SELECT DISTINCT(EXME_MEDICO_ID) FROM EXME_HIST_VACUNA ")
			.append(" WHERE EXME_PACIENTE_ID = ? AND EXME_MEDICO_ID IS NOT NULL ")
			.append(" UNION ")
			.append(" SELECT DISTINCT(EXME_MEDICO_ID) FROM EXME_PROGQUIRO ")
			.append(" WHERE EXME_PACIENTE_ID = ? AND EXME_MEDICO_ID IS NOT NULL ")
			.append(" UNION ")
			.append(" SELECT DISTINCT(DET.EXME_MEDICO_ID) FROM EXME_ACTPACIENTEDET DET ")
			.append(" INNER JOIN EXME_ACTPACIENTE ACT ON (DET.EXME_ACTPACIENTE_ID = ACT.EXME_ACTPACIENTE_ID) ")
			.append(" WHERE ACT.EXME_PACIENTE_ID = ? AND DET.EXME_MEDICO_ID IS NOT NULL ")
			.append(" UNION ")
			.append(" SELECT DISTINCT(H.EXME_MEDICO_ID) FROM EXME_ACTPACIENTEINDH H ")
			.append(" INNER JOIN EXME_ACTPACIENTE ACT ON (H.EXME_ACTPACIENTE_ID = ACT.EXME_ACTPACIENTE_ID) ")
			.append(" WHERE ACT.EXME_PACIENTE_ID = ? AND H.EXME_MEDICO_ID IS NOT NULL ")
			.append(" )  ").append(DB.isPostgreSQL() ? " AS doctors " : "");
		
			pstmt = DB.prepareStatement(sql.toString(), null);
			for(int i = 1; i<=5; i++){
				pstmt.setInt(i, EXME_Patient_ID);
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				lstMedicos.add(new MEXMEMedico(ctx, rs.getInt(1), null));
			}
		}catch(Exception e){
			s_log.log(Level.SEVERE, sql.toString(), e);
		}finally{
			DB.close(rs, pstmt);
		}
		return lstMedicos;
	}

	/**
	 * Regresa una lista con los documentos de consentimiento asignados al paciente 
	 * @Author GDerreza
	 * @return
	 */
	public List<MEXMEConsentimiento> getlstConsentimiento(){
		return MEXMEConsentimiento.getHistoria(ctx, EXME_Patient_ID, icCtaPacId, derechoHabiente,null);
	}
	
	public MEXMECtaPac getDischarge(){
		if(icCtaPacId > 0) {
			final MEXMECtaPac discharge = new MEXMECtaPac(ctx, icCtaPacId, null);
			if(discharge.getFechaAlta() != null) {
				return discharge;
			}
		}
		return null;
	}
	
	public List<MEXMEPrescRXDet> getActiveMedications() {
		List<MEXMEPrescRXDet>  list = new ArrayList<MEXMEPrescRXDet>();
		try {
			list = MEXMEPrescRXDet.getSavedMeds(ctx, true, null, null, EXME_Patient_ID, null);
		}
		catch (Exception e) {
			s_log.log(Level.SEVERE, "getActiveMedications" , e);
		}
		return list;
	}
	
	public List<MEXMEPrescRXDet> getPrescriptions(Boolean active) {
		
		final int paramId = icCtaPacId > 0 ? icCtaPacId : EXME_Patient_ID;
		
		final StringBuilder joins = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		joins.append(" INNER JOIN EXME_PrescRX ON ( ")//
			.append("     EXME_PrescRX.EXME_PrescRX_ID=EXME_PrescRXDet.EXME_PrescRX_ID ")//
			.append(" AND TRIM(EXME_PrescRX.Tipo)=? ") //
			.append(") ") //
			.append(" INNER JOIN EXME_CtaPac cp ON ( ")//
			.append("     EXME_PrescRX.EXME_CtaPac_ID=cp.EXME_CtaPac_ID ")//
			.append(" AND cp.").append(icCtaPacId > 0 ? "EXME_CtaPac_ID" : "EXME_Paciente_ID").append("=? ") //
			.append(") ");
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		if(active == null){
			sql.append(" 1=? ");
		}else{
			sql.append(" EXME_PrescRXDet.isActive=? ");
		}
		if(derechoHabiente){
			sql.append(MClientInfo.getClientSQL(ctx, MEXMEPrescRXDet.Table_Name));
		}
		return new Query(ctx, MEXMEPrescRXDet.Table_Name, sql.toString() , null)//
			.setJoins(joins)//
			.setParameters(MEXMEPrescRX.TIPO_MedicalPrescription, paramId, active == null ? 1 : DB.TO_STRING(active))//
//			.setOnlyActiveRecords(true)
			.addAccessLevelSQL(Env.getUserPatientID(ctx) <= 0 && !derechoHabiente)//
			.list();
	}
	
	public List<MEXMEPrescRXDet> getActivePrescriptions() {
		return getPrescriptions(true);
	}
	
	public List<MEXMEPrescRXDet> getPastPrescriptions() {
		return getPrescriptions(false);
	}
	
	/**
	 * Metodo para obtener las cuentas del paciente relacionadas a un cuestionario
	 * 
	 * @author gderreza
	 * @param isMedico
	 *            Si el cuestionario fue contestado por medico
	 *            @deprecated
	 **/
	public List<BasicoTresProps> getCtasCues(boolean isMedico) {
		return MEXMECtaCuest.getCtasCues(ctx, isMedico, EXME_Patient_ID, null);
	}
	
	public void setEXME_Medico_ID(int eXME_Medico_ID) {
		EXME_Medico_ID = eXME_Medico_ID;
	}
	
//	public int getEXME_ActPaciente_ID() {
//		if(icCtaPacId > 0) {
//			return MEXMEActPaciente.getActPaciente(ctx, icCtaPacId, null, null).get_ID();
//		}
//		return 0;
//	}

	public boolean isDerechoHabiente() {
		return derechoHabiente;
	}
}