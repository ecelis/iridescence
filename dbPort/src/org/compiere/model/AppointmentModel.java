package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.bpm.MedicationSave;
import org.compiere.model.bpm.ProceduresSave;
import org.compiere.model.bpm.ServicesSave;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;
import org.compiere.util.cuestionarios.Pregunta_VO;
import org.compiere.util.cuestionarios.RespuestaList_VO;

/**
 * Appointment Model
 * @author Lizeth de la Garza
 *
 */
public class AppointmentModel {
	/** log */
	private static CLogger log = CLogger.getCLogger (AppointmentModel.class);
	/** contexto */
	private Properties ctx = null;
	/** cita medica */
	private MEXMECitaMedica appointment = null;
	/** estacion de servicio */
//	private MEXMEEstServ estServ = null;
	/** paciente */
	private MEXMEPaciente patient = null;
	/** medico */
	private MEXMEMedico physician = null;
	/** almacen */
//	private MWarehouse warehouse = null;
	/** configuracion de precios */
//	private MConfigPre configPre = null;
	/** configuracion ce expdiente clinico */
	private MConfigEC configEC = null;
	/** Clase para guardar los servicios */
	private ServicesSave servicesSave;
	/** Clase para guardar los medicamentos */
	private MedicationSave medicationSave;
	/** Clase para guardar los procedimientos */
	private ProceduresSave proceduresSave;

	private Integer questionnaireFolio = new Integer(0);

	/**
	 * Consctructor
	 * @param ctx
	 * @param appointment
	 */
	public AppointmentModel(Properties ctx, MEXMECitaMedica appointment) {
		loadAppoitnment(ctx, appointment, Env.getContextAsInt(ctx, "#EXME_EstServ_ID"));

	}
	
	public AppointmentModel(Properties ctx, int citaMedicaId ,int estServId) {		 
		loadAppoitnment(ctx, new MEXMECitaMedica(ctx, citaMedicaId, null), estServId);
	}
	
	private void loadAppoitnment(Properties ctx, MEXMECitaMedica appointment, int estServId){
		this.ctx = ctx;
		this.appointment = appointment;		
//		estServ = new MEXMEEstServ(ctx, estServId, null);
		patient = new MEXMEPaciente(ctx, appointment.getEXME_Paciente_ID(), null);
		physician = new MEXMEMedico(ctx, appointment.getEXME_Medico_ID(), null);
//		warehouse = new MWarehouse(ctx, Env.getM_Warehouse_ID(ctx), null);
//		configPre = MConfigPre.get(ctx, null);
		configEC = MConfigEC.get(ctx, null);
		proceduresSave = new ProceduresSave(ctx);
		try {
			questionnaireFolio = MEXMETCuest.getExistFolioCita(appointment.getEXME_CitaMedica_ID(), new Integer(Env.getAD_User_ID(ctx)));
			if(questionnaireFolio == null)
				questionnaireFolio = new Integer(0);
		} catch (SQLException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	/**
	 * Crea la actividad paciente  MEXMEActPaciente
	 * @param EXME_CtaPac_ID
	 * @param EXME_MotivoCita_ID
	 * @param comment
	 * @param trxName Nombre de transaccion
	 * @return return ap.getEXME_ActPaciente_ID();
	 * @throws SQLException
	 */
	public int createPatientActivity(int EXME_CtaPac_ID, int EXME_MotivoCita_ID, String comment, String trxName) throws SQLException {
		log.log(Level.INFO, "***** Insertando en Act. Paciente ***** ");

		MEXMEActPaciente ap = new MEXMEActPaciente(ctx, 0, trxName);
		ap.setDescription(comment);
		ap.setEXME_Paciente_ID(appointment.getEXME_Paciente_ID());
		ap.setEXME_Medico_ID(appointment.getEXME_Medico_ID());
		ap.setEXME_Especialidad_ID(appointment.getEXME_Especialidad_ID());
		ap.setEXME_CtaPac_ID(EXME_CtaPac_ID);
		ap.setEXME_MotivoCita_ID(EXME_MotivoCita_ID);
		ap.setFecha(DB.getTimestampForOrg(ctx));
		ap.setEXME_CitaMedica_ID(appointment.getEXME_CitaMedica_ID());
		ap.setTipoArea(MEXMEActPaciente.TIPOAREA_MedicalConsultation);
		ap.setName(Msg.translate(ctx, "EXME_ActPaciente_ID") + ": " );
		if(!ap.save(trxName)) {  
			throw new SQLException("error.citas.noInsertEjec");
		}
		ap.setName(ap.getName() + " - " + Msg.translate(ctx, "Cita") + ": " + appointment.getEXME_CitaMedica_ID());
		if (!ap.save(trxName)) {
			throw new SQLException("error.citas.noInsertEjec");
		}
		return ap.getEXME_ActPaciente_ID();
	}

	/**
	 * Actualiza MEXMECitaMedica appointment
	 * @param initHr
	 * @param EXME_Intervencion_ID
	 * @param EXME_CtaPac_ID
	 * @param EXME_MotivoCita_ID
	 * @param susbtituteID
	 * @param estatus
	 * @param description
	 * @param trxName Nombre de transaccion
	 * @throws SQLException
	 */
	public void updateMedicalAppointment(Timestamp initHr, int EXME_Level_Of_Service_ID, int EXME_CtaPac_ID, int EXME_MotivoCita_ID,
			 String estatus, String description, int EXME_EstServ_ID, int asistente, String trxName ) throws SQLException {
		log.log(Level.FINER, " Actualizando  cita medica, FechaHrFin y FechaHrIni");

		appointment.setConfirmada(true);
		appointment.setEstatus(estatus);

		if (appointment.getFechaHrIni() == null) {
			appointment.setFechaHrIni(initHr);
		}
		if (estatus.equals(MEXMECitaMedica.ESTATUS_Executed)) {
			appointment.setFechaHrFin(new Timestamp(System.currentTimeMillis()));
		}
		
		if(appointment.getSubstitute_ID() == 0 && asistente == 0){
			appointment.setSubstitute_ID(appointment.getEXME_Medico_ID());
		}
		//si proviene de una cita de promocion, guardar si es optimo o no
		if( appointment.getN_Promo() != null) {
//			System.out.println(appointment.isUtilidad());
		}
		if (EXME_Level_Of_Service_ID > 0) {
			appointment.setEXME_Level_Of_Service_ID(EXME_Level_Of_Service_ID);
		}
		if (EXME_MotivoCita_ID > 0) {
			appointment.setEXME_MotivoCita_ID(EXME_MotivoCita_ID);
		}
		if(EXME_EstServ_ID > 0 && appointment.getEXME_EstServ_ID() != EXME_EstServ_ID){
			appointment.setEXME_EstServ_ID(EXME_EstServ_ID);
		}

		if(appointment.getEXME_CtaPac_ID() != EXME_CtaPac_ID)
			throw new SQLException("error.citas.noInsertEjec");
		
		appointment.setDescription(description);

		if (!appointment.save(trxName)) {
			throw new SQLException("error.citas.noInsertEjec");
		}
	}
	
	public void updateMedicalAppointment(Timestamp initHr, int EXME_Level_Of_Service_ID, int EXME_CtaPac_ID, int EXME_MotivoCita_ID,
			int susbtituteID, String estatus, String description, int EXME_EstServ_ID,int EXME_EstiloVida_ID, int EXME_EstiloVidaPaciente_ID, String trxName ) throws SQLException {
		log.log(Level.FINER, " Actualizando  cita medica, FechaHrFin y FechaHrIni");

		appointment.setConfirmada(true);
		appointment.setEstatus(estatus);

		if (appointment.getFechaHrIni() == null) {
			appointment.setFechaHrIni(initHr);
		}
		if (estatus.equals(MEXMECitaMedica.ESTATUS_Executed)) {
			appointment.setFechaHrFin(new Timestamp(System.currentTimeMillis()));
		}

		if(appointment.getSubstitute_ID() == 0){
			appointment.setSubstitute_ID(appointment.getEXME_Medico_ID());
		}
		//si proviene de una cita de promocion, guardar si es optimo o no
		if( appointment.getN_Promo() != null) {
//			System.out.println(appointment.isUtilidad());
		}
		if (EXME_Level_Of_Service_ID > 0) {
			appointment.setEXME_Level_Of_Service_ID(EXME_Level_Of_Service_ID);
		}
		if (EXME_MotivoCita_ID > 0) {
			appointment.setEXME_MotivoCita_ID(EXME_MotivoCita_ID);
		}
		if(EXME_EstServ_ID > 0 && appointment.getEXME_EstServ_ID() != EXME_EstServ_ID){
			appointment.setEXME_EstServ_ID(EXME_EstServ_ID);
		}

		appointment.setEXME_CtaPac_ID(EXME_CtaPac_ID);
		appointment.setDescription(description);

		if (!appointment.save(trxName)) {
			throw new SQLException("error.citas.noInsertEjec");
		}else{
			if(EXME_EstiloVidaPaciente_ID > 0){
				MEXMEEstiloVidaPaciente pacStyle = new MEXMEEstiloVidaPaciente(Env.getCtx(), EXME_EstiloVidaPaciente_ID, trxName);
				pacStyle.setEXME_EstiloVida_ID(EXME_EstiloVida_ID);
				if (!pacStyle.save(trxName)) {
					throw new SQLException("error.citas.noInsertEjec");
				}				
			}else if(EXME_EstiloVida_ID > 0){				
				MEXMEEstiloVidaPaciente pacStyle = new MEXMEEstiloVidaPaciente(Env.getCtx(), 0, trxName);
				pacStyle.setEXME_Paciente_ID(appointment.getEXME_Paciente_ID());
				pacStyle.setEXME_EstiloVida_ID(EXME_EstiloVida_ID);
				if (!pacStyle.save(trxName)) {
					throw new SQLException("error.citas.noInsertEjec");
				}				
			}
		}
	}

	/**
	 * Actualiza el asistente  setEXME_Asistente_ID
	 * @param EXME_Asistente_ID
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 */
	public void updateMedApptAssistant(int EXME_Asistente_ID, String trxName) throws Exception {

		appointment.setEXME_Asistente_ID(EXME_Asistente_ID);
		if (!appointment.save(trxName)) {
			throw new Exception("error.citas.noInsertEjec");
		}
	}

	/**
	 * Guarda los signos vitales
	 * @param forma
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 */
//	public void saveVitalSign(List<List<MSignoVitalDet>> list, int EXME_ActPaciente_ID , String trxName)  throws  SQLException{
//
//		for (int i = 0; i < list.size(); i++) {
//			for (int j = 0; j < list.get(i).size(); j++) {
//				if (list.get(i).get(j) != null) {
//					MSignoVitalDet signo = list.get(i).get(j);
//					signo.set_TrxName(trxName);
//					if (signo.getValor().compareTo(Env.ZERO) > 0) {
//						createVitalSigns(EXME_ActPaciente_ID, signo.getEXME_SignoVital_ID(), signo.getValor(),
//								signo.getFecha(), signo.getFolio(), signo.getDescription(), signo.getEdad(), trxName);
//					}
//				}
//			}
//		}
//	}

	/**
	 * Guarda los signos vitales en tabla temporal
	 * @param listVS
	 * @param EXME_ActPaciente_ID
	 * @param trxName Nombre de transaccion
	 * @param lstAddedSignsTemp
	 * @throws SQLException
	 *
	public void saveVitalSignFromTemp(List<List<MSignoVitalDet>> listVS, int EXME_ActPaciente_ID , 
			String trxName, List<List<MSignoVitalDet>>lstAddedSignsTemp)  throws SQLException {

		for (List<MSignoVitalDet> list : listVS) {
			for (int i = 0; i < list.size(); i++) {
				MSignoVitalDet vitalSign = null;
				if (list.get(i) instanceof MSignoVitalDet) {
					vitalSign = list.get(i);
				}
				boolean exist = false;
				if (vitalSign != null) {
					for (List<MSignoVitalDet> listAdded : lstAddedSignsTemp) {
						if (!exist) {
							for (MSignoVitalDet addedVitalSign : listAdded) {
								if (addedVitalSign != null && vitalSign != null) {
									if (addedVitalSign.getFolio() == vitalSign.getFolio() && 
											addedVitalSign.getEXME_SignoVitalDet_ID() == vitalSign.getEXME_SignoVitalDet_ID()) {
										addedVitalSign.setValor(vitalSign.getValor());
										if (!addedVitalSign.save(trxName)) {
											throw new SQLException(Utilerias.getMessage(ctx, null, "error.citas.noInsertEjec"));
										}
										listAdded.remove(addedVitalSign);
										exist = true;
										break;
									}
								}
							}
						} else {
							break;
						}
					}
					if (!exist && vitalSign != null) {
						createVitalSigns(EXME_ActPaciente_ID, vitalSign.getEXME_SignoVital_ID(), vitalSign.getValor(), vitalSign.getFecha(), vitalSign.getFolio(), vitalSign.getDescription(), vitalSign.getEdad(), trxName);
					}
				}
			}
		}

		for (List<MSignoVitalDet> listAdded : lstAddedSignsTemp) {
			if (listAdded.size() > 0) {
				for (MSignoVitalDet addedVitalSign : listAdded) {
					if (addedVitalSign != null && !addedVitalSign.delete(false, trxName)) {
						throw new SQLException(Utilerias.getMessage(ctx, null, "error.citas.noInsertEjec"));
					}
				}
			}
		}
	}*/

	/**
	 * 
	 * @param listPrevVS
	 * @param EXME_ActPaciente_ID
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 *
	public void savePrevVitalSigns(List<Object> listPrevVS,  int EXME_ActPaciente_ID , String trxName) throws Exception {
		// Liz de la Garza -Se guarda la actividad paciente a los signos vitales previos a
		// ejecuci�n de cita
		for (int i = 0; i < listPrevVS.size(); i++) {
			MSignoVitalDet signoPrevio = (MSignoVitalDet) listPrevVS.get(i);
			signoPrevio.set_TrxName(trxName);
			signoPrevio.setEXME_ActPaciente_ID(EXME_ActPaciente_ID);
			if (!signoPrevio.save(trxName)) {
				throw new SQLException("error.notasMed.signoVitalSave");
			}
		}
	}
*/
	/**
	 * 
	 * @param EXME_ActPaciente_ID
	 * @param signoVital
	 * @param valor
	 * @param fecha
	 * @param folio
	 * @param description
	 * @param edad
	 * @param trxName Nombre de transaccion
	 * @throws SQLException
	 *
	public void createVitalSigns(int EXME_ActPaciente_ID ,  int signoVital, BigDecimal valor, 
			Timestamp fecha, int folio, String description, BigDecimal edad, String trxName ) throws SQLException {
		log.log(Level.INFO, "***** Insertando en Act. Paciente***** ");              

		MSignoVitalDet svd = new MSignoVitalDet(ctx, 0, trxName);

		if (MUser.convertirUnidades(ctx)) {
			valor = VitalSignsUtils.getToDB(ctx, svd.getUnidadMedida(), valor, true);
		}

		svd.setEXME_EstServ_ID(Env.getEXME_EstServ_ID(ctx));
		svd.setAD_User_ID(Env.getAD_User_ID(ctx));
		svd.setFecha(fecha);
		svd.setEXME_SignoVital_ID(signoVital);
		svd.setValor(valor == null ? new BigDecimal(0) : valor);
		svd.setEXME_ActPaciente_ID(EXME_ActPaciente_ID);
		svd.setFolio(folio);
		svd.setEXME_CitaMedica_ID(appointment.getEXME_CitaMedica_ID());
		svd.setEXME_Paciente_ID(appointment.getEXME_Paciente_ID());
		svd.setDescription(description);
		svd.setEdad(edad);
		if(!svd.save(trxName)) {
			throw new SQLException("error.notasMed.signoVitalSave");
		}
	}*/

	/**
	 * 
	 * @param studies
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 */
	public void saveTempStudies(List<ServicioView> studies, String trxName) throws Exception {

		for (ServicioView studie: studies) {
			MEXMECitaMedicaDet appointmentTemp = new MEXMECitaMedicaDet(ctx, appointment, null);

			appointmentTemp.setIsService(true);
			appointmentTemp.setEstatus(MEXMECitaMedicaDet.ESTATUS_Drafted);
			appointmentTemp.setAplicar(false);
			appointmentTemp.setTomadoCasa(false);
			appointmentTemp.setOtherInstructions(studie.getOtherInstructions());
			appointmentTemp.setConsultingProvider(studie.getConsultingProvider());
			appointmentTemp.setDescription(studie.getComments());

			appointmentTemp.setProductType(MEXMECitaMedicaDet.PRODUCTTYPE_Studie);
			appointmentTemp.setM_Product_ID(studie.getProdID());
			appointmentTemp.setSurtir(studie.getSurtir());
			appointmentTemp.setEXME_Diagnostico_ID(studie.getDiagnosis1ID());
			appointmentTemp.setEXME_Diagnostico2_ID(studie.getDiagnosis2ID());
			appointmentTemp.setEXME_Diagnostico3_ID(studie.getDiagnosis3ID());
			if(studie.getEXME_Medico_ID() >0){
				appointmentTemp.setEXME_Medico_ID(studie.getEXME_Medico_ID());
			}
			appointmentTemp.setEXME_Institucion_ID(studie.getEXME_Institucion_ID());
			appointmentTemp.setProveedor(studie.getProvider());
			appointmentTemp.setDatePromised(studie.getFecha());
			appointmentTemp.setPriorityRule(studie.getPrioridadID());
			appointmentTemp.setM_Warehouse_ID(studie.getAlmaServ());
			appointmentTemp.setAD_Org_Dest_ID(studie.getAD_Org_Dest_ID());
			appointmentTemp.setEXME_Modifiers_ID(studie.getEXME_Modifier());
			appointmentTemp.setBillable(studie.isBillable());
			appointmentTemp.setEXME_OrderSet_ID(studie.getExmeOrderSetId());
			
			if (!appointmentTemp.save(trxName)) {
				throw new Exception("error.citas.noGuardarSolicitud");
			}
		}
	}

	/**
	 * 
	 * @param procedures
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 */
	public void saveTempProcedures(List<MProduct> procedures, String trxName) throws Exception {

		for (MProduct procedure: procedures) {
			MEXMECitaMedicaDet appointmentTemp = new MEXMECitaMedicaDet(ctx, appointment, null);

			appointmentTemp.setIsService(true);
			appointmentTemp.setEstatus(MEXMECitaMedicaDet.ESTATUS_Drafted);
			appointmentTemp.setSurtir(false);
			appointmentTemp.setAplicar(false);
			appointmentTemp.setTomadoCasa(false);
			appointmentTemp.setDescription(procedure.getComment());

			appointmentTemp.setProductType(MEXMECitaMedicaDet.PRODUCTTYPE_Procedure);
			appointmentTemp.setM_Product_ID(procedure.getM_Product_ID());
			if (!appointmentTemp.save(trxName)) {
				throw new Exception("error.citas.noGuardarSolicitud");
			}
		}
	}

	/**
	 * 
	 * @param Immunizations
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 */
	public void saveTempImmunizations(List<MProduct> immunization, String trxName) throws Exception {

		for (MProduct immunizationProd: immunization) {
			MEXMECitaMedicaDet appointmentTemp = new MEXMECitaMedicaDet(ctx, appointment, null);

			appointmentTemp.setIsService(true);
			appointmentTemp.setEstatus(MEXMECitaMedicaDet.ESTATUS_Drafted);
			appointmentTemp.setSurtir(false);
			appointmentTemp.setAplicar(false);
			appointmentTemp.setTomadoCasa(false);
			appointmentTemp.setDescription(immunizationProd.getComment());

			appointmentTemp.setProductType(MEXMECitaMedicaDet.PRODUCTTYPE_Other);
			appointmentTemp.setM_Product_ID(immunizationProd.getM_Product_ID());
			if (!appointmentTemp.save(trxName)) {
				throw new Exception("error.citas.noGuardarSolicitud");
			}
		}
	}
	
	/**
	 * 
	 * @param items
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 */
	public void saveTempItem(List<MProduct> items, String trxName) throws Exception {

		for (MProduct item: items) {
			MEXMECitaMedicaDet appointmentTemp = new MEXMECitaMedicaDet(ctx, appointment, null);

			appointmentTemp.setIsService(false);
			appointmentTemp.setEstatus(MEXMECitaMedicaDet.ESTATUS_Drafted);
			appointmentTemp.setSurtir(false);
			appointmentTemp.setAplicar(false);
			appointmentTemp.setTomadoCasa(false);

			appointmentTemp.setProductType(MEXMECitaMedicaDet.PRODUCTTYPE_Item);
			appointmentTemp.setM_Product_ID(item.getM_Product_ID());
			if (!appointmentTemp.save(trxName)) {
				throw new Exception("error.citas.noGuardarSolicitud");
			}
		}
	}

	/**
	 * 
	 * @param diagnostics
	 * @param trxName Nombre de transaccion
	 * @throws Exception
	 */
	public void saveTempDiagnostic(List<MDiagnostico> diagnostics, String trxName) throws Exception {

		for (MDiagnostico diagnostic: diagnostics) {
			MEXMECitaMedicaDet appointmentTemp = new MEXMECitaMedicaDet(ctx, appointment, null);

			appointmentTemp.setIsService(false);
			appointmentTemp.setEstatus(MEXMECitaMedicaDet.ESTATUS_Drafted);
			appointmentTemp.setSurtir(false);
			appointmentTemp.setAplicar(false);
			appointmentTemp.setTomadoCasa(false);
			appointmentTemp.setDescription(diagnostic.getComment());
			appointmentTemp.setISCC(diagnostic.isCC());
			appointmentTemp.setEXME_OrderSet_ID(diagnostic.getExme_orderset_id());
			
			appointmentTemp.setEXME_Diagnostico_ID(diagnostic.getEXME_Diagnostico_ID());
			if (!appointmentTemp.save(trxName)) {
				throw new Exception("error.citas.noGuardarSolicitud");
			}
		}
	}

	/**
	 * 
	 * @param EXME_ActPaciente_ID
	 * @param EXME_CtaPac_ID
	 * @param EXME_Intervencion_ID
	 * @param trxName Nombre de transaccion
	 * @throws Exception 
	 */
	public void createAppointmentCharge(int EXME_ActPaciente_ID, int EXME_CtaPac_ID, int EXME_Intervencion_ID, String trxName) throws Exception{   
		servicesSave.createAppointmentCharge(EXME_ActPaciente_ID, EXME_CtaPac_ID, EXME_Intervencion_ID,  configEC,  appointment,  trxName);
	}
	
	/**
	 * Guarda las solcitudes de servicio
	 * @param studies : Listado de estudios (MProduct)
	 * @param EXME_ActPaciente_ID : id Actividad paciente header
	 * @param EXME_CtaPac_ID : id cuenta paciente
	 * @param trxName : nombre de transaccion
	 * @throws Exception
	 */
	public void saveStudies(List<ServicioView> studies, int EXME_ActPaciente_ID, int EXME_CtaPac_ID, String trxName) throws Exception {

		if (studies != null && !studies.isEmpty()) {
			//MActPacienteIndH header = createHeader(EXME_ActPaciente_ID, EXME_CtaPac_ID, false, trxName);

//			List<ServicioView> productsHelper = new ArrayList<ServicioView>();
//			List<ServicioView> productsHelperRef = new ArrayList<ServicioView>();
//			for (ServicioView studie: studies) {
//				List<MProduct> products = getPackageLine(ctx, studie.prodID, null);
//				if (products != null && !products.isEmpty()) {
//					for (MProduct product: products) {
//						if (MProduct.PRODUCTCLASS_Laboratory.equals(product.getProductClass()) 
//								|| MProduct.PRODUCTCLASS_XRay.equals(product.getProductClass())) {
//							if (studie.getAD_Org_Dest_ID() > 0 || !studie.getSurtir()) {
//								productsHelperRef.add(studie);						
//							}else {
//								productsHelper.add(studie);
//							}							
//						}
//					}
//					productsHelper.add(studie);
//				} else {
//					if (studie.getAD_Org_Dest_ID() > 0 || !studie.getSurtir()) {
//						productsHelperRef.add(studie);						
//					}else {
//						productsHelper.add(studie);
//					}
//				}
//			}
			if (servicesSave != null) {
//				final MEXMECtaPac ctpac = new MEXMECtaPac(ctx, EXME_CtaPac_ID, trxName);
////				 createLines(productsHelper, header, false, trxName);
//				servicesSave.save(productsHelper, EXME_ActPaciente_ID, ctpac, false, trxName);
//				if (!productsHelperRef.isEmpty() && productsHelperRef != null) {
//					servicesSave.save(productsHelperRef, EXME_ActPaciente_ID, ctpac, false, trxName);
//				}
				servicesSave.save(studies, EXME_ActPaciente_ID, new MEXMECtaPac(ctx, EXME_CtaPac_ID, trxName), false, trxName);
			}
		}
	}

	/**
	 * Guarda los procedimientos
	 * los procedimientos pueden tener servicios
	 * @param procedures : Listado de procedimientos (MProduct)
	 * @param EXME_ActPaciente_ID : id Actividad paciente header
	 * @param EXME_CtaPac_ID : id cuenta paciente
	 * @param trxName : nombre de transaccion
	 * @throws Exception
	 */
	public void saveProcedures(List<MProduct> procedures, int EXME_ActPaciente_ID, int EXME_CtaPac_ID, String trxName) throws Exception {
	/*
		if (procedures != null && !procedures.isEmpty()) {
			MActPacienteIndH header = createHeader(EXME_ActPaciente_ID, EXME_CtaPac_ID, true, trxName);
			createLines(procedures, header, true, trxName);
			for (MProduct procedure: procedures) {
				List<MProduct> products = getPackageLine(ctx, procedure.getM_Product_ID(), null);
				if (products != null && !products.isEmpty()) {
					for (MProduct product: products) {
						if (!product.isEstudio() || !MProduct.PRODUCTCLASS_Laboratory.equals(product.getProductClass()) 
								|| !MProduct.PRODUCTCLASS_XRay.equals(product.getProductClass())) {
							createCharge(product, EXME_CtaPac_ID, product.getQuantity(), product.getEXME_PaqBase_Version_ID(), trxName);
						}
					}
				} else {
					createCharge(procedure, EXME_CtaPac_ID, Env.ONE, trxName);
				}
			}
		}
	*/
		proceduresSave.saveProcedures(procedures, EXME_ActPaciente_ID, EXME_CtaPac_ID, medicationSave, servicesSave, trxName);
	}
	
	/**
	 * Guarda los procedimientos de la cita medica
	 * @param procedures : Listado de procedimientos (MProduct)
	 * @param EXME_ActPaciente_ID : id Actividad paciente header
	 * @param EXME_CtaPac_ID : id cuenta paciente
	 * @param trxName : nombre de transaccion
	 * @throws Exception
	 */
	public void saveProcedure(List<ServicioView> procedures, int EXME_ActPaciente_ID, int EXME_CtaPac_ID, String trxName) throws Exception {
		proceduresSave.saveProcedure(procedures, EXME_ActPaciente_ID, EXME_CtaPac_ID, medicationSave, servicesSave, trxName);
	}

	/**
	 * Genera los cargos de medicamentos de la citamedica
	 * @param items
	 * @param EXME_CtaPac_ID
	 * @param trxName nombre de transaccion
	 * @throws Exception
	 */
	public void saveItems(List<MProduct> items, int EXME_CtaPac_ID, String trxName) throws Exception {
/*
		for (MProduct item: items) {
			createCharge(item, EXME_CtaPac_ID, item.getQuantity(), trxName);
		}
*/
		
		// Crea la indicacion medica el cargo y la salida de inventario (Items)
		if(medicationSave!=null && items.size()>0){
			// Crea el encabezado y lineas de la indicacion y los cargos
			medicationSave.insertCharge(medicationSave.createActPacIndH(items, true, trxName, false));
		}
	}

	/*
	public void createCharge(MProduct product, int EXME_CtaPac_ID, BigDecimal quantity, String trxName) throws Exception {
		createCharge(product, EXME_CtaPac_ID, quantity, 0, trxName);
	}

	/**
	 * Creamos el cargo
	 * @param product
	 * @param EXME_CtaPac_ID
	 * @param quantity
	 * @param EXME_PaqBase_Version_ID
	 * @param trxName
	 * @throws Exception
	 *
	public void createCharge(MProduct product, int EXME_CtaPac_ID, BigDecimal quantity, int EXME_PaqBase_Version_ID, String trxName) throws Exception {

		PreciosVenta.m_configPre =  MEXMEConfigPre.get(ctx, trxName);
		PreciosVenta.m_Paciente = patient;   
		PreciosVenta.m_ConfigEC =  MConfigEC.get(ctx, trxName);

		MPrecios precios = PreciosVenta.precioProdVta(ctx, estServ.getTipoArea(), product.getM_Product_ID(), 
				quantity, product.getC_UOM_ID(), PreciosVenta.PVCE, 0,  0,  warehouse.getM_Warehouse_ID(), 
				warehouse.getM_Warehouse_ID(), estServ.getEXME_Area_ID(),
				new Timestamp(System.currentTimeMillis()), false, trxName); 
		// Expert. Precios en cero
		if (precios == null || Env.ZERO.compareTo(precios.getPriceList()) >= 0) {
			//throw new Exception(Utilerias.getMessage(ctx, null, "error.factDirecta.findPrice", product.getName()));
			;
		} else {

			MCtaPacDet ctaPacDet = new MCtaPacDet(ctx, 0, null);
			ctaPacDet.setEXME_CtaPac_ID(EXME_CtaPac_ID);
			ctaPacDet.setLine();

			ctaPacDet.setQtyDelivered(quantity);
			ctaPacDet.setDescription(Utilerias.getMessage(ctx, null, "msg.citaMedica"));

			ctaPacDet.setDateOrdered(new Timestamp(System.currentTimeMillis()));
			ctaPacDet.setQtyOrdered(quantity);
			ctaPacDet.setQtyDelivered(quantity);
			ctaPacDet.setQtyEntered(quantity);
			ctaPacDet.setM_Product_ID(product.getM_Product_ID());
			ctaPacDet.setC_UOM_ID(product.getC_UOM_ID());

			ctaPacDet.setM_Warehouse_ID(Env.getContextAsInt(ctx,"$M_Warehouse_ID"));

			ctaPacDet.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID());// Organizacion

			ctaPacDet.setCosto(Env.ZERO);
			ctaPacDet.setC_Currency_ID(Env.getContextAsInt(ctx,	"$C_Currency_ID"));
			ctaPacDet.setDateDelivered(new Timestamp(System.currentTimeMillis()));

			ctaPacDet.setEXME_Area_ID(estServ.getEXME_Area_ID());
			ctaPacDet = precios.preciosActual(ctaPacDet);
			ctaPacDet.setTipoArea(estServ.getTipoArea());	
			ctaPacDet.setQtyPaquete(ctaPacDet.getQtyOrdered());
			ctaPacDet.setQtyPendiente(ctaPacDet.getQtyOrdered());
			ctaPacDet.setEXME_CtaPacExt_ID(ctaPacDet.getEXME_CtaPac_ID());			
			ctaPacDet.setEXME_PaqBase_Version_ID(EXME_PaqBase_Version_ID);
			if(!ctaPacDet.save(trxName)){
				throw new Exception(Utilerias.getMessage(ctx, null, "error.factDirecta.findPrice", product.getName()));
			}
		}
	}

	public void createLines(List<MProduct> products, MActPacienteIndH header, boolean applied, String trxName) throws Exception {

		BigDecimal totalLines = Env.ZERO;
		BigDecimal tax = Env.ZERO;
		int line = 1;

		PreciosVenta.m_configPre =  MEXMEConfigPre.get(ctx, trxName);
		PreciosVenta.m_Paciente = patient;   
		PreciosVenta.m_ConfigEC =  MConfigEC.get(ctx, trxName);

		for (MProduct product : products) {

			MEXMEActPacienteInd serv = new MEXMEActPacienteInd(ctx, 0, trxName);

			serv.setM_Product_ID(product.getM_Product_ID());
			serv.setDescripcion(product.getComment());
			serv.setC_UOM_ID(product.getC_UOM_ID());
			serv.setQtyOrdered(Env.ONE);
			serv.setLine(line);
			line++;
			serv.setC_Currency_ID(header.getC_Currency_ID());
			serv.setDateOrdered(new Timestamp(System.currentTimeMillis()));
			serv.setEXME_Area_ID(estServ.getEXME_Area_ID()); 
			serv.setTipoArea(estServ.getTipoArea()); 
			serv.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID());
			serv.setSurtir(false);
			serv.setIsExternal(product.isExternal());

			if (applied) {
				serv.setEstatus(MEXMEActPacienteInd.ESTATUS_CompletedService);
				serv.setQtyDelivered(Env.ONE);
				serv.setDateDelivered(new Timestamp(System.currentTimeMillis()));
			} else {
				serv.setEstatus(MEXMEActPacienteInd.ESTATUS_RequestedService);
			}

			MPrecios precios = PreciosVenta.precioProdVta(ctx, estServ.getTipoArea(),
					serv.getM_Product_ID(), 
					serv.getQtyOrdered(), 
					serv.getC_UOM_ID(),
					PreciosVenta.PVCE, 
					header.getM_Warehouse_Sol_ID(), 
					header.getM_Warehouse_ID(), 
					serv.getEXME_Area_ID(),
					serv.getDateOrdered(),
					true, trxName);
			
			// Expert. Precios en cero
/*			if (precios == null || (precios != null && Env.ZERO.compareTo(precios.getPriceList()) >= 0)) {
				MEXMEConfigPre cp = MEXMEConfigPre.get(Env.getCtx(), null);
				if (cp != null && cp.isAplicaServSinPrec()) {
					MPrecios.enCeros(serv);
				} //else {
					//throw new Exception("error.preciosVenta.precios");
				//}
			} else {
			serv = precios.preciosActual(serv);
//			}
/*			serv.setEXME_ActPacienteIndH_ID(header.getEXME_ActPacienteIndH_ID());
			serv.setDatePromised(header.getDatePromised());
			serv.setEstatus(header.getEstatus());
			serv.setActPacienteID(header.getEXME_ActPaciente_ID());

			if(!serv.save(trxName)){
				throw new Exception("error.notasMed.servicios.detalle");
			}				
			tax = tax.add(serv.getTotalImp());	

		} // se termina de guardar el detalle (MEXMEActPacienteInd)

		totalLines = new BigDecimal(products.size());
		header.setTotalLines(totalLines);            
		header.setGrandTotal(totalLines.add(tax));
	}

	
	public MActPacienteIndH createHeader(int EXME_ActPaciente_ID, int EXME_CtaPac_ID, boolean applied, boolean isService, String tipoServicio, String trxName) throws Exception {

		MActPacienteIndH header = new MActPacienteIndH(ctx, 0, trxName);		


		header.setC_BPartner_ID(patient.getC_BPartner_ID());
		MPriceList pricelist = getPriceList();
		header.setM_PriceList_ID(pricelist.getM_PriceList_ID());
		header.setC_Currency_ID(pricelist.getC_Currency_ID());
		header.setIsTaxIncluded(pricelist.isTaxIncluded());		

		header.setEXME_ActPaciente_ID(EXME_ActPaciente_ID);
		header.setEXME_CtaPac_ID(EXME_CtaPac_ID);		

		int location = (int)Datos.getBPartnerLocation(ctx, patient.getC_BPartner_ID());
		if (location <= 0) {
			header.setC_Location_ID(patient.getC_Location_ID());
		} else {
			header.setC_Location_ID(location);	
		}

		header.setDocAction(DocAction.ACTION_None);

		header.setDatePromised(new Timestamp(System.currentTimeMillis()));
		header.setDateOrdered(header.getDatePromised());

		header.setM_Warehouse_Sol_ID(warehouse.getM_Warehouse_ID());

		header.setC_DocType_ID(Datos.getTipoDoc(ctx, tipoServicio));		
		header.setIsService(isService);

		header.setC_DocTypeTarget_ID(header.getC_DocType_ID());
		header.setDocumentNo(DB.getDocumentNo((int)header.getC_DocType_ID(), trxName, false));
		if (applied) {
			header.setDateDelivered(header.getDatePromised());
			header.setIsDelivered(true);	
			header.setProcessed(true);
			header.setDocStatus(DocAction.STATUS_Completed);
			header.setEstatus(MEXMEActPacienteInd.ESTATUS_CompletedService);
		} else if(tipoServicio.equals(Constantes.RECETA)) {
			//que estatus se usara?? :/
			header.setEstatus(MEXMEActPacienteInd.ESTATUS_RequestedService);
			header.setDocStatus(DocAction.STATUS_Drafted);
		}else{
			header.setEstatus(MEXMEActPacienteInd.ESTATUS_RequestedService);
			header.setDocStatus(DocAction.STATUS_Drafted);
		}

		header.setEXME_Medico_ID(physician.getEXME_Medico_ID());
		header.setMedicoNombre(physician.getNombre_Med());

		header.setAD_OrgTrx_ID(estServ.getAD_OrgTrx_ID());
		header.setEXME_ActPaciente_ID(EXME_ActPaciente_ID);
		header.setEXME_CtaPac_ID(EXME_CtaPac_ID);

		header.setEXME_MedicoSol_ID(physician.getEXME_Medico_ID());
		header.setEXME_EspecialidadSol_ID(appointment.getEXME_Especialidad_ID());		

		if (!header.save(trxName)){
			throw new Exception("error.citas.noGuardarSolicitud");
		}
		return header;

	}

	public MActPacienteIndH createHeader(int EXME_ActPaciente_ID, int EXME_CtaPac_ID, boolean applied, String trxName) throws Exception {
		return createHeader( EXME_ActPaciente_ID,  EXME_CtaPac_ID,  applied, true,  Constantes.SERVICIO,  trxName);
	}
*/
	public void saveDiagnostics(List<MDiagnostico> diagnostics, int EXME_ActPaciente_ID, String trxName) throws Exception {
		for (MDiagnostico diagnostic: diagnostics) {

			MActPacienteDiag apd = new MActPacienteDiag(ctx, 0 , trxName);
			apd.setEXME_ActPaciente_ID(EXME_ActPaciente_ID);
			apd.setEXME_Diagnostico_ID(diagnostic.getEXME_Diagnostico_ID());
			apd.setFecha_Diagnostico(DB.getTSForOrg(ctx));
			apd.setDescription(diagnostic.getComment());
			apd.setISCC(diagnostic.isCC());
			
			apd.setRecord_ID(appointment.getEXME_CitaMedica_ID());
			apd.setAD_Table_ID(MEXMECitaMedica.Table_ID);
			apd.setType(X_EXME_ActPacienteDiag.TYPE_MedicalFinal);
			apd.setEXME_OrderSet_ID(diagnostic.getExme_orderset_id());
			if(!apd.save(trxName)) {
				throw new SQLException("error.citas.noInsertEjec");
			}
		}
	}

	/**
	 * Actualiza el estatus de las solicitudes pendientes
	 * @param services
	 * @param trxName nombre de transaccion
	 * @throws Exception
	 */
	public void updateStudies(List<ServicioView> services, String trxName)  throws Exception {
		for (ServicioView service: services) {
			
			MEXMEActPacienteInd studie = new MEXMEActPacienteInd(ctx, service.getEXME_ActPacienteInd_ID(), null);
			studie.setEXME_Medico_ID(service.getEXME_Medico_ID());
			studie.setPriorityRule(String.valueOf(service.getPrioridadID() == null ? 0 : service.getPrioridadID()));
			if (service.getDiagnosis1ID() > 0) {
				studie.setEXME_Diagnostico_ID(service.getDiagnosis1ID());
			}
			if (service.getDiagnosis2ID() > 0) {
				studie.setEXME_Diagnostico2_ID(service.getDiagnosis2ID());
			}
			if (service.getDiagnosis3ID() > 0) {
				studie.setEXME_Diagnostico3_ID(service.getDiagnosis3ID());
			}
			if(service.getFecha() == null){
				studie.setDateOrdered(Env.getCurrentDate());
			}else{
				studie.setDateOrdered(service.getFecha());
			}
			studie.setOtherInstructions(service.getOtherInstructions());
			studie.setConsultingProvider(service.getConsultingProvider());
			studie.setComments(service.getComments());
			studie.setProveedor(service.getProvider());
			studie.setEXME_Institucion_ID(service.getEXME_Institucion_ID());
			studie.setSurtir(service.getSurtir());
			studie.setEXME_OrderSet_ID(service.getExmeOrderSetId());
			studie.setEXME_Modifiers_ID(service.getEXME_Modifier());
			studie.setBillable(service.isBillable());
			if (service.getSurtir()) {
				studie.setM_Warehouse_ID(service.getAlmaServ());
//				studie.setEstatus(X_EXME_ActPacienteInd.ESTATUS_RequestedService);
//				studie.setSurtir(true);
//				studie.getActPacienteIndH().statusComplete();
				studie.getActPacienteIndH().setM_Warehouse_ID(service.getAlmaServ());
			} else {
				studie.setM_Warehouse_ID(0);
				studie.getActPacienteIndH().setM_Warehouse_ID(0);
//				studie.setEstatus(X_EXME_ActPacienteInd.ESTATUS_CompletedService);
//				studie.getActPacienteIndH().statusDraft();
			}
			if(!studie.save(trxName)) {
				throw new SQLException("error.citas.noInsertEjec");
			}
		}
	}

//	/**
//	 * Lista de precios
//	 * @return
//	 */
//	public MPriceList getPriceList(){
//
//		int precioLista = 0;
//		MPriceList listaPrecios = null;
//
//		//buscamos la lista de precios para el BPartner del paciente
//		if(patient.getC_BPartner_Seg_ID()>0){
//			precioLista = new MBPartner(ctx, patient.getC_BPartner_Seg_ID(), null).getM_PriceList_ID();
//		}else{ 
//			precioLista = new MBPartner(ctx ,patient.getC_BPartner_ID(), null).getM_PriceList_ID();
//		}
//
//		//si no tiene configurada una lista, busca la configuracion de precios
//		if(precioLista<=0){
//			listaPrecios = MEXMEConfigPre.getPriceList(ctx , null);
//			if(listaPrecios==null || (listaPrecios!=null && listaPrecios.getM_PriceList_ID()<=0)){//si no esta configurada, aplica la lista que tiene por default
//				precioLista = Env.getM_PriceList_ID(ctx);
//				if(precioLista>0){
//					listaPrecios = MPriceList.get(ctx, precioLista, null);
//				}else{
//					listaPrecios = null;
//				}
//
//			}
//		}else{
//			listaPrecios = MPriceList.get(ctx, precioLista, null);
//		}
//
//		return listaPrecios;
//
//	}

	/**
	 * 
	 * @param productType
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public List<MProduct> getFromTemporal(String productType, String trxName) throws Exception {

		List<MProduct> list = new ArrayList<MProduct>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT M_Product_ID, Description, IsExternal, EXME_Diagnostico_ID, EXME_Diagnostico2_ID, EXME_Diagnostico3_ID, EXME_Medico_ID FROM EXME_CitaMedicaDet WHERE EXME_CitaMedica_ID = ?");
		sql.append(" AND ProductType = ? AND IsActive = 'Y'");


		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, appointment.getEXME_CitaMedica_ID());
			pstmt.setString(2, productType);

			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MProduct product = new MProduct(ctx, rs.getInt(MProduct.COLUMNNAME_M_Product_ID), trxName);
				product.setComment(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_Description));
				if ("Y".equals(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_IsExternal))) {
					product.setExternal(true);
				} else {
					product.setExternal(false);
				}
				list.add(product);

			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * Lista Temporal de Inmunizaciones
	 * @param productClass
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public List<MProduct> getImmunizationFromTemporal(String productClass, String trxName) throws Exception {

		List<MProduct> list = new ArrayList<MProduct>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT cmdet.* FROM EXME_CitaMedicaDet cmdet ");
		sql.append("INNER JOIN M_Product prod ON prod.M_Product_ID = cmdet.M_Product_ID ");
		sql.append("WHERE cmdet.EXME_CitaMedica_ID = ? AND prod.ProductClass = ? AND cmdet.IsActive = 'Y'");

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, appointment.getEXME_CitaMedica_ID());
			pstmt.setString(2, productClass);

			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MProduct immunization = new MProduct(ctx, rs.getInt(MProduct.COLUMNNAME_M_Product_ID), trxName);
				immunization.setComment(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_Description));
				if ("Y".equals(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_IsExternal))) {
					immunization.setExternal(true);
				} else {
					immunization.setExternal(false);
				}
				list.add(immunization);

			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * 
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public List<MDiagnostico> getDiagnosticFromTemporal(String trxName) throws Exception {

		List<MDiagnostico> list = new ArrayList<MDiagnostico>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Diagnostico_ID, Description, isCC, EXME_OrderSet_ID ")	
		   .append(" FROM EXME_CitaMedicaDet ")
		   .append(" WHERE EXME_CitaMedica_ID = ? AND EXME_Diagnostico_ID IS NOT NULL AND IsActive = 'Y' and ISPROBLEM = 'N'");
				
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, appointment.getEXME_CitaMedica_ID());

			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MDiagnostico diagnostic = new MDiagnostico(ctx, rs.getInt(MDiagnostico.COLUMNNAME_EXME_Diagnostico_ID), trxName);
				diagnostic.setComment(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_Description));
				diagnostic.setCC(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_ISCC).equalsIgnoreCase("Y") ? true : false);
				diagnostic.setExme_orderset_id(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_EXME_OrderSet_ID));
				list.add(diagnostic);

			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	
	public static List<MDiagnostico> getProblemsFromTemporal(final Properties ctx, final int EXME_CitaMedica_ID, String trxName) throws Exception {

		List<MDiagnostico> list = new ArrayList<MDiagnostico>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_Diagnostico_ID, Description, isCC, DatePromised, EstatusDiag ")	
		   .append(" FROM EXME_CitaMedicaDet ")
		   .append(" WHERE EXME_CitaMedica_ID = ? AND EXME_Diagnostico_ID IS NOT NULL AND IsActive = 'Y' and ISPROBLEM = 'Y'");
				
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MDiagnostico diagnostic = new MDiagnostico(ctx, rs.getInt(MDiagnostico.COLUMNNAME_EXME_Diagnostico_ID), trxName);
				diagnostic.setComment(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_Description));
				diagnostic.setCC(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_ISCC).equalsIgnoreCase("Y") ? true : false);
				diagnostic.setFechaDiagnosed(rs.getTimestamp(MEXMECitaMedicaDet.COLUMNNAME_DatePromised));
				diagnostic.setEstatus(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_estatusDiag));
				list.add(diagnostic);

			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void deleteOldValues() throws Exception {

		Trx trx = null;
		try {
			trx = Trx.get(Trx.createTrxName("NM"), true);
			MEXMECitaMedicaDet.deleteOldValues(ctx, appointment.getEXME_CitaMedica_ID(), false, trx.getTrxName());
			Trx.commit(trx);
		} catch (Exception e) {
			Trx.rollback(trx);
			throw e;
		} finally {
			Trx.close(trx, true);
		}
	}
	
	/**
	 * 
	 */
	public void inactivateTempStudies(List<ServicioView> list) {
		if (appointment.getEXME_CitaMedica_ID() > 0 && !list.isEmpty()) {
			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
			final List<Object> params = new ArrayList<Object>();
			sql.append("SELECT * FROM EXME_CitaMedicaDet WHERE EXME_CitaMedica_ID=? AND ProductType=? AND IsActive='Y' ");
			params.add(appointment.getEXME_CitaMedica_ID());
			params.add(MEXMECitaMedicaDet.PRODUCTTYPE_Studie);
			sql.append(" AND M_Product_id IN ( ");
			for (ServicioView servicioView : list) {
				if(params.size() > 2){
					sql.append(",");
				}
				sql.append("?");
				params.add(servicioView.getProdID());
			}
			sql.append(" ) ");
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			Trx trx = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				DB.setParameters(pstmt, params);
				rs = pstmt.executeQuery();
				trx = Trx.get(Trx.createTrxName("NM"), true);
				while (rs.next()) {
					MEXMECitaMedicaDet cita = new MEXMECitaMedicaDet(ctx, rs,trx.getTrxName());
					
					cita.setIsActive(false);
					if (!cita.save()) {
						throw new MedsysException();
					}
				}
				Trx.commit(trx, true);
			} catch (Exception e) {
				Trx.rollback(trx, true);
				log.log(Level.SEVERE, sql.toString(), e);
				throw new MedsysException(Utilerias.getMsg(ctx,
						"error.insulinas.registro.eliminar") + e.getMessage());
			} finally {
				Trx.close(trx, true);
				DB.close(rs, pstmt);
			}
		}
	}

	/********************/
	public List<ServicioView> getStudiesFromTemp() throws Exception {
		return AppointmentModel.getStudiesFromTemp(ctx, appointment.getEXME_CitaMedica_ID());
	}
	
	public static List<ServicioView> getStudiesFromTemp(Properties ctx, int citaMedicaID) throws Exception {
		return getStudiesFromTemp(ctx, citaMedicaID, true);
	}
	
	public static List<ServicioView> getStudiesFromTemp(Properties ctx, int citaMedicaID, boolean onlyActives)  throws Exception {
		List<ServicioView> list = new ArrayList<ServicioView>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		 sql.append(" SELECT * ")
			.append(" FROM EXME_CitaMedicaDet WHERE EXME_CitaMedica_ID = ?")
		 	.append(" AND ProductType = ? ");
		 sql.append(" AND Estatus <> 'VO' ");
		 if(onlyActives){
			 sql.append(" AND IsActive = 'Y' ");
		 }

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, citaMedicaID);
			pstmt.setString(2, MEXMECitaMedicaDet.PRODUCTTYPE_Studie);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MProduct prod = new MProduct(ctx, rs.getInt("M_Product_ID"), null);
				
				ServicioView servicio = new ServicioView(ctx, prod);
				
				servicio.setCitaMedicaDetId(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_EXME_CitaMedicaDet_ID));
				servicio.setComments(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_Description));
				servicio.setSurtir("Y".equals(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_Surtir)));
				servicio.setOtherInstructions(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_OtherInstructions));
				servicio.setConsultingProvider(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_ConsultingProvider));
				
				servicio.setDiagnosis1ID(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_EXME_Diagnostico_ID));
				servicio.setDiagnosis2ID(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_EXME_Diagnostico2_ID));
				servicio.setDiagnosis3ID(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_EXME_Diagnostico3_ID));
				servicio.setFecha(rs.getTimestamp(MEXMECitaMedicaDet.COLUMNNAME_DatePromised));
				servicio.setEXME_Medico_ID(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_EXME_Medico_ID));
				servicio.setEXME_Institucion_ID(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_EXME_Institucion_ID));
				servicio.setProvider(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_Proveedor));
				servicio.setPrioridadID(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_PriorityRule));
				servicio.setAlmaServ(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_M_Warehouse_ID));
				servicio.setAD_Org_Dest_ID(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_AD_Org_Dest_ID));
				servicio.setExmeOrderSetId(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_EXME_OrderSet_ID));
				servicio.setTodayService(false);
				servicio.setBillable("Y".equals(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_Billable)));
				servicio.setEXME_Modifier(rs.getInt(MEXMECitaMedicaDet.COLUMNNAME_EXME_Modifiers_ID));
				
				list.add(servicio);
			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}


	public List<MProduct> getProceduresFromTemp() throws Exception {
		List<MProduct> lstTempProcedures = getFromTemporal(MEXMECitaMedicaDet.PRODUCTTYPE_Procedure, null);
		return lstTempProcedures;
	}
	
	public List<MProduct> getImmunizationsFromTemp() throws Exception {
		List<MProduct> lstTempImmunizations = getImmunizationFromTemporal(MProduct.PRODUCTCLASS_Immunization, null);
		return lstTempImmunizations;
	}

	public List<MProduct> getItemsFromTemp() throws Exception {
		List<MProduct> lstTempItems = getFromTemporal(MEXMECitaMedicaDet.PRODUCTTYPE_Item, null);
		return lstTempItems;
	}

	public List<MDiagnostico> getDiagnosticsFromTemp() throws Exception {
		List<MDiagnostico> lstTempDiagnostics = getDiagnosticFromTemporal(null);
		return lstTempDiagnostics;
	}

	public List<ServicioView> getSavedStudies(boolean isTodayService) throws Exception {
		List<MEXMEActPacienteInd> total = getSavedStudies(ctx, appointment.getEXME_CitaMedica_ID(),	isTodayService, null, MProduct.PRODUCTCLASS_Procedures, MProduct.PRODUCTCLASS_Cultures, MProduct.PRODUCTCLASS_Blood, MProduct.PRODUCTCLASS_Surgeries, MProduct.PRODUCTCLASS_Anesthesic,
				MProduct.PRODUCTCLASS_OtherService, MProduct.PRODUCTCLASS_Ambulance, MProduct.PRODUCTCLASS_HomeHealt, MProduct.PRODUCTCLASS_PhysicianServices,
				MProduct.PRODUCTCLASS_Others, MProduct.PRODUCTCLASS_Laboratory, MProduct.PRODUCTCLASS_XRay);
		List<ServicioView> studies = new ArrayList<ServicioView>();
		for(MEXMEActPacienteInd ind : total){
			ServicioView serv = new ServicioView(ctx, ind);
			studies.add(serv);
		}
		return studies;
	}

	public List<MProduct> getSavedProcedures() throws Exception {
		List<MProduct> lstProcedures = getSavedProducts(MEXMEActPacienteInd.ESTATUS_CompletedService, null);
		return lstProcedures;
	}
	
	public List<MProduct> getSavedImmunizations() throws Exception {
		return AppointmentModel.getSavedImmunizations(ctx, appointment.getEXME_CitaMedica_ID(), null);
	}

	public List<MDiagnostico> getSavedDiagnostics() throws Exception {
		List<MDiagnostico> lstDiagnostics = getSavedDiagnostics(null);
		return lstDiagnostics;
	}	

	public List<MEXMEActPacienteInd> getLaboratoryStudies(boolean isTodayService) throws Exception {
		List<MEXMEActPacienteInd> lstStudies = getSavedStudies(ctx, appointment.getEXME_CitaMedica_ID(), 
				MEXMEActPacienteInd.ESTATUS_RequestedService, MProduct.PRODUCTCLASS_Laboratory, isTodayService, null);
		return lstStudies;
	}

	public List<MEXMEActPacienteInd> getImagingStudies(boolean isTodayService) throws Exception {
		return AppointmentModel.getImagingStudies(ctx, appointment.getEXME_CitaMedica_ID(), isTodayService);
	}
	
	public static List<MEXMEActPacienteInd> getImagingStudies(Properties ctx, int citaMedicaId, boolean isTodayService) throws Exception {
		List<MEXMEActPacienteInd> lstStudies = getSavedStudies(ctx, citaMedicaId, 
				MEXMEActPacienteInd.ESTATUS_RequestedService, MProduct.PRODUCTCLASS_XRay, isTodayService, null);
		return lstStudies;
	}
	
	public List<MEXMEActPacienteInd> getStudies(boolean isTodayService) throws Exception {
		List<MEXMEActPacienteInd> lstStudies = getSavedStudies(ctx, appointment.getEXME_CitaMedica_ID(), 
				MEXMEActPacienteInd.ESTATUS_RequestedService, MProduct.PRODUCTCLASS_XRay, isTodayService, null);
		return lstStudies;
	}

	public  List<MProduct> getSavedProducts( String status, String trxName) throws Exception {
		return getSavedProducts(ctx, appointment.getEXME_CitaMedica_ID(), status, null, trxName);
	}
	
	public  List<MProduct> getSavedImmunizations( String status, String trxName) throws Exception {
		return AppointmentModel.getSavedProducts(ctx, appointment.getEXME_CitaMedica_ID(), status, null, trxName);
	}
	public  static List<MProduct> getSavedImmunizations(Properties ctx, int citaMedicaId, String trxName) throws Exception {
		return AppointmentModel.getSavedProducts(ctx, citaMedicaId, MEXMEActPacienteInd.ESTATUS_CompletedService, null, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param status
	 * @param medicalService
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public static List<MProduct> getSavedProducts(Properties ctx, int EXME_CitaMedica_ID, String status, String medicalService, String trxName) throws Exception {

		List<MProduct> list = new ArrayList<MProduct>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT ind.M_Product_ID, ind.Description FROM EXME_ActPacienteInd ind")
		.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.exme_actpacienteindh_id = ind.exme_actpacienteindh_id) ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = indh.exme_actpaciente_id)")
		.append(" INNER JOIN M_Product prod ON prod.M_Product_ID = ind.M_Product_ID")
		.append(" WHERE act.EXME_CitaMedica_ID = ? ");
		//AND ind.Estatus = ?");

		if (medicalService != null) {
			sql.append(" AND prod.ProductClass = ?");
		}


		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			//pstmt.setString(2, status);
			if (medicalService != null) {
				pstmt.setString(2, medicalService);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MProduct product = new MProduct(ctx, rs.getInt(MProduct.COLUMNNAME_M_Product_ID), trxName);
				product.setComment(rs.getString(MEXMEActPacienteInd.COLUMNNAME_Description));
				list.add(product);

			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * 
	 * @param trxName nombre de transaccion
	 * @param isAssistant
	 * @return
	 * @throws Exception
	 */
	public List<MEXMEActPacienteInd> getSavedMeds(String trxName, boolean isAssistant) throws Exception {
		/*List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT ind.* FROM EXME_ActPacienteInd ind")
		.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.exme_actpacienteindh_id = ind.exme_actpacienteindh_id) ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = indh.exme_actpaciente_id)")
		.append(" LEFT JOIN M_Product prod ON (ind.m_product_id = prod.m_product_id) ")
		.append(" WHERE act.EXME_CitaMedica_ID = ? AND indh.isService= 'N' AND ind.isActive = 'Y'")
		.append(" AND (ind.exme_genproduct_id is not null OR (ind.m_product_id is not null and prod.productclass = ?)) ")
		.append(" AND ind.estatus in ('S','P','C') ");
		/*if(isAssistant){
			sql.append(" AND ind.estatus=?");
		}
		/*ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, appointment.getEXME_CitaMedica_ID());
			pstmt.setString(2, MProduct.PRODUCTCLASS_Drug);
			/*if(isAssistant){
				pstmt.setString(2, MEXMEActPacienteInd.ESTATUS_ScheduleService);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				list.add(new MEXMEActPacienteInd(ctx, rs, trxName));
			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;*/
		return MEXMEActPacienteInd.getSavedMeds(ctx, appointment.getEXME_CitaMedica_ID(), trxName);// Lama
	}

	/**
	 * 
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public List<MDiagnostico> getSavedDiagnostics(String trxName) throws Exception {
		return getSavedDiagnostics(ctx, appointment.getEXME_CitaMedica_ID(), trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public static List<MDiagnostico> getSavedDiagnostics(Properties ctx, int EXME_CitaMedica_ID, String trxName) throws Exception {

		List<MDiagnostico> list = new ArrayList<MDiagnostico>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT diag.EXME_Diagnostico_ID, diag.Description, diag.isCC, diag.EXME_ActPacienteDiag_ID, diag.EXME_OrderSet_ID FROM EXME_ActPacienteDiag diag")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = diag.exme_actpaciente_id) ")
		.append(" WHERE act.EXME_CitaMedica_ID = ? AND diag.ad_table_id = ? and diag.isActive = 'Y' ");


		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			pstmt.setInt(2, X_EXME_CitaMedica.Table_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MDiagnostico diagnostic = new MDiagnostico(ctx, rs.getInt(MDiagnostico.COLUMNNAME_EXME_Diagnostico_ID), trxName);
				diagnostic.setComment(rs.getString(MEXMEActPacienteInd.COLUMNNAME_Description));
				diagnostic.setCC(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_ISCC).equalsIgnoreCase("Y") ? true : false);
				diagnostic.setEXME_ActPacienteDiag_ID(rs.getInt(MActPacienteDiag.COLUMNNAME_EXME_ActPacienteDiag_ID));
				diagnostic.setExme_orderset_id(rs.getInt(MActPacienteDiag.COLUMNNAME_EXME_OrderSet_ID));
				list.add(diagnostic);

			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * TODO corregir query trae exme_diagnostico_id repetidos para luego en el proceso de ApptProblemHistory.addDiagnostic solo tome el primero de los repetidos
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public static List<MDiagnostico> getSavedProblems(Properties ctx, int EXME_Paciente_ID, int EXME_CitaMedica_ID, String trxName) throws Exception {

		
		List<MDiagnostico> list = new ArrayList<MDiagnostico>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT diag.EXME_Diagnostico_ID, ")
		.append(" diag.Description, ")
		.append(" diag.isCC, diag.estatus, diag.Fecha_Diagnostico, diag.EXME_ActPacienteDiag_ID ")
		.append(" FROM EXME_ActPacienteDiag diag ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id  = diag.exme_actpaciente_id) ")
		.append(" inner join exme_ctapac cta on act.exme_ctapac_id = cta.exme_ctapac_id and cta.exme_paciente_id = ? ")
		.append(" where trim(cta.encounterstatus) =  ? ")
		.append(" and diag.type = ? ")
		.append(" and diag.exme_actpacientediag_id not in ( ")
		.append(" SELECT diag.EXME_ActPacienteDiag_ID FROM EXME_ActPacienteDiag diag ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = diag.exme_actpaciente_id) ")
		.append(" WHERE act.EXME_CitaMedica_ID = ? AND diag.ad_table_id = ?) ");
  
 


		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_Paciente_ID);
			pstmt.setString(2, MEXMECtaPac.ENCOUNTERSTATUS_Discharge);
			pstmt.setString(3, MActPacienteDiag.TYPE_MedicalFinal);
			pstmt.setInt(4, EXME_CitaMedica_ID);
			pstmt.setInt(5, X_EXME_CitaMedica.Table_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MDiagnostico diagnostic = new MDiagnostico(ctx, rs.getInt(MDiagnostico.COLUMNNAME_EXME_Diagnostico_ID), trxName);
				diagnostic.setComment(rs.getString(MEXMEActPacienteInd.COLUMNNAME_Description));
				diagnostic.setCC(rs.getString(MEXMECitaMedicaDet.COLUMNNAME_ISCC).equalsIgnoreCase("Y") ? true : false);
				diagnostic.setEstatus(rs.getString(MActPacienteDiag.COLUMNNAME_Estatus));
				diagnostic.setFechaDiagnosed(rs.getTimestamp(MActPacienteDiag.COLUMNNAME_Fecha_Diagnostico));
				diagnostic.setEXME_ActPacienteDiag_ID(rs.getInt(MActPacienteDiag.COLUMNNAME_EXME_ActPacienteDiag_ID));
				list.add(diagnostic);
			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 * 
	 * @param ctx
	 * @param M_Product_ID
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public static List<MProduct> getPackageLine(Properties ctx, int M_Product_ID, String trxName) throws Exception {

		List<MProduct> list = new ArrayList<MProduct>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT line.M_Product_ID, line.Cantidad, line.exme_paqbase_version_id FROM EXME_PaqBaseDet line")
		.append(" INNER JOIN EXME_PaqBase_Version version ON (line.exme_paqbase_version_id = version.exme_paqbase_version_id) ")
		.append(" WHERE version.M_Product_ID = ? AND version.IsActive = 'Y'");


		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Product_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) { 
				MProduct product = new MProduct(ctx, rs.getInt(MProduct.COLUMNNAME_M_Product_ID), trxName);
				product.setQuantity(rs.getBigDecimal(MEXMEPaqBaseDet.COLUMNNAME_Cantidad));
				product.setEXME_PaqBase_Version_ID(rs.getInt(MEXMEPaqBaseDet.COLUMNNAME_EXME_PaqBase_Version_ID));
				list.add(product);

			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param status
	 * @param medicalService
	 * @param isTodayService TODO
	 * @param trxName nombre de transaccion
	 * @return
	 * @throws Exception
	 */
	public static List<MEXMEActPacienteInd> getSavedStudies(Properties ctx, int EXME_CitaMedica_ID, String status, String medicalService, boolean isTodayService, String trxName) throws Exception {

		List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT ind.* FROM EXME_ActPacienteInd ind")
		.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.exme_actpacienteindh_id = ind.exme_actpacienteindh_id) ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = indh.exme_actpaciente_id)")
		.append(" INNER JOIN M_Product prod ON prod.M_Product_ID = ind.M_Product_ID")
		.append(" WHERE act.EXME_CitaMedica_ID = ? " )
		.append(" AND ind.isTodayService = ? ");
		if (medicalService != null) {
			sql.append(" AND prod.ProductClass = ?");
		}

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			pstmt.setString(2, DB.TO_STRING(isTodayService));

			if (medicalService != null) {
				pstmt.setString(3, medicalService);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) { 
				list.add(new MEXMEActPacienteInd(ctx, rs, trxName));
			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
			throw new Exception("Error al eliminar registros");
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param EXME_CitaMedica_ID
	 * @param isTodayService TODO
	 * @param trxName nombre de transaccion
	 * @param ProductClass
	 * @return
	 * @throws Exception
	 */
	public static List<MEXMEActPacienteInd> getSavedStudies(Properties ctx, int EXME_CitaMedica_ID, boolean isTodayService, String trxName, Object... parameters) throws Exception {

		List<MEXMEActPacienteInd> list = new ArrayList<MEXMEActPacienteInd>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT ind.* FROM EXME_ActPacienteInd ind")
		.append(" INNER JOIN EXME_ActPacienteIndH indh ON (indh.exme_actpacienteindh_id = ind.exme_actpacienteindh_id) ")
		.append(" INNER JOIN EXME_ActPaciente act ON (act.exme_actpaciente_id = indh.exme_actpaciente_id)")
		.append(" INNER JOIN M_Product prod ON prod.M_Product_ID = ind.M_Product_ID")
		.append(" WHERE act.EXME_CitaMedica_ID = ? " )
		.append(" and ind.M_Product_ID not in ( SELECT " ) 
		.append(" inte.m_product_id FROM EXME_Level_Of_Service  ls  " )
		.append(" inner join EXME_Intervencion inte on inte.EXME_Intervencion_ID = ls.EXME_Intervencion_ID " ) 
		.append(" WHERE (ls.isActive = 'Y' AND (ls.Patient_Type = 'E' or ls.Patient_Type = 'N')) and inte.m_product_id is not null ) ")
		.append(" AND ind.isTodayService = ? ");
		
		if (parameters.length >0) {
			sql.append(" AND (ProductClass in ( ?");
			for (int i = 1; i < parameters.length; i++) {
				sql.append(", ? ");
			}
			sql.append(" ))");
		}

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CitaMedica_ID);
			pstmt.setString(2, DB.TO_STRING(isTodayService));
			for (int i = 0; i < parameters.length; i++) {
				pstmt.setString(i + 3, (String)parameters[i]);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				list.add(new MEXMEActPacienteInd(ctx, rs, trxName));
			}			
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	/************************/

	public MEXMEMedico getPhysician() {
		return physician;
	}

	public void setPhysician(MEXMEMedico physician) {
		this.physician = physician;
	}

	public MEXMEPaciente getPatient() {
		return patient;
	}

	public void setPatient(MEXMEPaciente patient) {
		this.patient = patient;
	}

	public MEXMECitaMedica getAppointment() {
		return appointment;
	}

	public void setAppointment(MEXMECitaMedica appointment) {
		this.appointment = appointment;
	}

	/**
	 * 
	 * @param questionLst
	 * @return
	 */
	public List<MProduct> getProceduresByQuestionnaire(List<Pregunta_VO> questionLst) {
		List<MProduct> procedures = new ArrayList<MProduct>();


		for (Pregunta_VO question: questionLst) {
			if (!MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_Logial.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getTipoDato())) {
				if (question.getRespuesta() != null && question.getRespuesta().length() > 0) {
					MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
					if(mPregunta.getEXME_OrderSet_ID() > 0)
						procedures.addAll(MEXMEOrderSetProd.getProceduresProd(ctx, mPregunta.getEXME_OrderSet_ID(),patient));				
				} 
			}else if (MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())) {
				if (question.getRespuestaCombo() != null) {
					MPregunta_Lista mPreguntaLista = new MPregunta_Lista(ctx, Integer.parseInt(question.getRespuestaCombo()), null);
					
					if(mPreguntaLista.getEXME_OrderSet_ID() > 0){
						List<MProduct> products = MEXMEOrderSetProd.getProceduresProd(ctx, mPreguntaLista.getEXME_OrderSet_ID(), patient);					
						if (products.isEmpty()) {
							if (question.getId() != null) {
								MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
								if(mPregunta.getEXME_OrderSet_ID() > 0)
									procedures.addAll(MEXMEOrderSetProd.getProceduresProd(ctx, mPregunta.getEXME_OrderSet_ID(),patient));
							}
						} else {
							procedures.addAll(products);
						}					
					}
				} 
			} else if (MEXMEPregunta.TIPODATO_Logial.equals(question.getTipoDato())) {
				if (question.getRespuesta() != null && question.getId() != null && question.getRespuesta().equalsIgnoreCase("Y")) {
					MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
					if(mPregunta.getEXME_OrderSet_ID() > 0)
						procedures.addAll(MEXMEOrderSetProd.getProceduresProd(ctx, mPregunta.getEXME_OrderSet_ID(),patient));
				}
			} else if (MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getTipoDato())) {
				for (RespuestaList_VO answer: question.getRespuestasMulti()) {
					MPregunta_Lista mPreguntaLista = new MPregunta_Lista(ctx, answer.getListaID(), null);
					if(mPreguntaLista.getEXME_OrderSet_ID() > 0){
						List<MProduct> products = MEXMEOrderSetProd.getProceduresProd(ctx, mPreguntaLista.getEXME_OrderSet_ID(), patient);
						if (products.isEmpty()) {
							if (question.getId() != null) {
								MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
								if(mPregunta.getEXME_OrderSet_ID() > 0)
									procedures.addAll(MEXMEOrderSetProd.getProceduresProd(ctx, mPregunta.getEXME_OrderSet_ID(), patient));
							}
						} else {					
							procedures.addAll(products);
						}					
					}
				}
			}
		}		 
		return procedures;
	}

	/**
	 * 
	 * @param questionLst
	 * @return
	 */
	public List<MProduct> getStudiesByQuestionnaire(List<Pregunta_VO> questionLst) {
		List<MProduct> studies = new ArrayList<MProduct>();
		
		for (Pregunta_VO question: questionLst) {
			if (!MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_Logial.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getTipoDato())) {
				if (question.getRespuesta() != null && question.getRespuesta().length() > 0) {
						MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
						if(mPregunta.getEXME_OrderSet_ID() > 0)
							studies.addAll(MEXMEOrderSetProd.getProductsProd(ctx, mPregunta.getEXME_OrderSet_ID(), patient));
				}
			} else if (MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())) {
				if (question.getRespuestaCombo() != null) {
					MPregunta_Lista mPreguntaLista = new MPregunta_Lista(ctx, Integer.parseInt(question.getRespuestaCombo()), null);
					if(mPreguntaLista.getEXME_OrderSet_ID() > 0){
						List<MProduct> products = MEXMEOrderSetProd.getProductsProd(ctx, mPreguntaLista.getEXME_OrderSet_ID(),patient);
						if (products.isEmpty()) {
							if (question.getId() != null) {
								MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
								if(mPregunta.getEXME_OrderSet_ID() > 0)
									studies.addAll(MEXMEOrderSetProd.getProductsProd(ctx, mPregunta.getEXME_OrderSet_ID(), patient));
							}
						} else {
							studies.addAll(products);
						}					
					}
				}
			} else if (MEXMEPregunta.TIPODATO_Logial.equals(question.getTipoDato())) {
				if (question.getRespuesta() != null && question.getId() != null && question.getRespuesta().equalsIgnoreCase("Y")) {
					MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
					if(mPregunta.getEXME_OrderSet_ID() > 0)
						studies.addAll(MEXMEOrderSetProd.getProductsProd(ctx, mPregunta.getEXME_OrderSet_ID(), patient));
				}

			} else if (MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getTipoDato())) {
				for (RespuestaList_VO answer: question.getRespuestasMulti()) {
					MPregunta_Lista mPreguntaLista = new MPregunta_Lista(ctx, answer.getListaID(), null);
					if(mPreguntaLista.getEXME_OrderSet_ID() > 0){
						List<MProduct> products = MEXMEOrderSetProd.getProductsProd(ctx, mPreguntaLista.getEXME_OrderSet_ID(), patient);					
						if (products.isEmpty()) {
							if (question.getId() != null) {
								MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
								if(mPregunta.getEXME_OrderSet_ID() > 0)
									studies.addAll(MEXMEOrderSetProd.getProductsProd(ctx, mPregunta.getEXME_OrderSet_ID(), patient));
							}
						} else {
							studies.addAll(products);
						}
					}
				}
			}
		}		 
		return studies;
	}

	/**
	 * 
	 * @param questionLst
	 * @return
	 */
	public List<MEXMEOrderSetProd> getMedsByQuestionnaire(List<Pregunta_VO> questionLst) {
		List<MEXMEOrderSetProd> medications = new ArrayList<MEXMEOrderSetProd>();
		for (Pregunta_VO question: questionLst) {
			if (!MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_Logial.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getTipoDato())) {				
				if (question.getRespuesta() != null && question.getRespuesta().length() > 0) {
					MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
					if(mPregunta.getEXME_OrderSet_ID() > 0)
						medications.addAll(MEXMEOrderSetProd.getRXNorm(ctx, mPregunta.getEXME_OrderSet_ID(), patient));
				} 
			}else if (MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())) {				
				if (question.getRespuestaCombo() != null) {
					MPregunta_Lista mPreguntaLista = new MPregunta_Lista(ctx, Integer.parseInt(question.getRespuestaCombo()), null);
					if(mPreguntaLista.getEXME_OrderSet_ID() > 0){
						List<MEXMEOrderSetProd> products = MEXMEOrderSetProd.getRXNorm(ctx, mPreguntaLista.getEXME_OrderSet_ID(), patient);
						if (products.isEmpty()) {
							if (question.getId() != null) { 
								MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
								if(mPregunta.getEXME_OrderSet_ID() > 0)
									medications.addAll(MEXMEOrderSetProd.getRXNorm(ctx, mPregunta.getEXME_OrderSet_ID(), patient));
							}
						} else {
							medications.addAll(products);
						}
					}
				} 
			} else if (MEXMEPregunta.TIPODATO_Logial.equals(question.getTipoDato())) {
				if (question.getRespuesta() != null && question.getId() != null && question.getRespuesta().equalsIgnoreCase("Y")) {
					MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
					if(mPregunta.getEXME_OrderSet_ID() > 0)
						medications.addAll(MEXMEOrderSetProd.getRXNorm(ctx, mPregunta.getEXME_OrderSet_ID(), patient));
				}
			} else if (MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getTipoDato())) {
				for (RespuestaList_VO answer: question.getRespuestasMulti()) {
					MPregunta_Lista mPreguntaLista = new MPregunta_Lista(ctx, answer.getListaID(), null);
					if(mPreguntaLista.getEXME_OrderSet_ID() > 0){
						List<MEXMEOrderSetProd> products = MEXMEOrderSetProd.getRXNorm(ctx, mPreguntaLista.getEXME_OrderSet_ID(),patient);					
						if (products.isEmpty()) {
							if (question.getId() != null) {
								MPregunta mPregunta = new MPregunta(ctx, question.getId(), null);
								if(mPregunta.getEXME_OrderSet_ID() > 0)
									medications.addAll(MEXMEOrderSetProd.getRXNorm(ctx, mPregunta.getEXME_OrderSet_ID(), patient));
							}
						} else {
							medications.addAll(products);
						}
					}
				}
			}
		}
		return medications;
	}

	/**
	 * 
	 * @param questionLst
	 * @return
	 */
	public List<MDiagnostico> getDiagsByQuestionnaire(List<Pregunta_VO> questionLst) {
		List<MDiagnostico> diagnostics = new ArrayList<MDiagnostico>();

		for (Pregunta_VO question: questionLst) {
			if (!MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato()) 
					&& !MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_Logial.equals(question.getTipoDato())
					&& !MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getTipoDato())) {
				if (question.getRespuesta() != null && question.getRespuesta().length() > 0) {
					if (question.getRespuesta() != null && question.getId() != null && question.getRespuesta().length() > 0) {
						diagnostics.addAll(MEXMEPreguntaDiag.getDiagnosis(ctx, question.getId(), null));
					}
				}
			}else if (MEXMEPregunta.TIPODATO_OptionList.equals(question.getTipoDato())) {
				if (question.getRespuestaCombo() != null) {
					List<MDiagnostico> diagnosis = MEXMEPreguntaListaDiag.getDiagnosis(ctx, Integer.parseInt(question.getRespuestaCombo()), null);
					if (diagnosis.isEmpty()) {
						if (question.getId() != null) {
							diagnostics.addAll(MEXMEPreguntaDiag.getDiagnosis(ctx, question.getId(), null));
						}  
					} else {		
						diagnostics.addAll(diagnosis);
					}					
				}
			} else if (MEXMEPregunta.TIPODATO_Logial.equals(question.getTipoDato())) {
				if (question.getRespuesta() != null && question.getId() != null && question.getRespuesta().equalsIgnoreCase("Y")) {
					diagnostics.addAll(MEXMEPreguntaDiag.getDiagnosis(ctx, question.getId(), null));
				}
			} else if (MEXMEPregunta.TIPODATO_MultiOptions.equals(question.getTipoDato())) {
				for (RespuestaList_VO answer: question.getRespuestasMulti()) {
					List<MDiagnostico> diagnosis = MEXMEPreguntaListaDiag.getDiagnosis(ctx, answer.getListaID(), null);
					if (diagnosis.isEmpty()) {
						if (question.getId() != null) {
							diagnostics.addAll(MEXMEPreguntaDiag.getDiagnosis(ctx, question.getId(), null));
						}  
					} else {
						diagnostics.addAll(diagnosis);
					}			
				}
			}
		}
		return diagnostics;
	}
	
	public ServicesSave getServicesSave() {
		return this.servicesSave;
	}

	public void setServicesSave(ServicesSave servicesSave) {
		this.servicesSave = servicesSave;
	}

	public MedicationSave getMedicationSave() {
		return this.medicationSave;
	}

	public void setMedicationSave(MedicationSave medicationSave) {
		this.medicationSave = medicationSave;
	}

	public Integer getQuestionnaireFolio() {		
		return questionnaireFolio;
	}

	public void setQuestionnaireFolio(Integer questionnaireFolio) {
		this.questionnaireFolio = questionnaireFolio;
	}
	
	/**
	 * Obtiene dieta del paciente
	 * 
	 * @param ctx
	 * @param cita
	 *            Cita Médica
	 * @return Lista de productos
	 */
	public static MProduct getProductCmDet(Properties ctx, int cita) {
//		MProduct product = null;
//		StringBuilder sql = new StringBuilder();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append("Select prod.M_Product_ID, cmdet.description From M_Product prod ");
//		sql.append("Inner Join Exme_Citamedicadet Cmdet On Prod.M_Product_Id = Cmdet.M_Product_Id ");
//		sql.append("Where prod.ProcedureType =  ? ");//'DI'
//		sql.append(" And cmdet.EXME_CitaMedica_ID = ? And cmdet.isActive = 'Y'");
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setString(1, MProduct.PROCEDURETYPE_Diet);
//			pstmt.setInt(2, cita);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				product = new MProduct(ctx, rs.getInt(1), null);
//				product.setDescription(rs.getString(2));
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
		return getProductCmDet(ctx, cita, false);
	}
	
//	public static MProduct getProdCmDetActPaciente(Properties ctx, int cita) {
//		MProduct product = null;
//		StringBuilder sql = new StringBuilder();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		sql.append("Select Act.M_Product_Id, Cmdet.Description From Exme_Actpacienteind Act  ");
//		sql.append("Inner Join Exme_Citamedicadet Cmdet On Act.M_Product_Id = Cmdet.M_Product_Id  ");
//		sql.append("Inner Join M_Product prod On Act.M_Product_Id = prod.M_Product_Id ");
//		sql.append("Where prod.ProcedureType =  ? ");//'DI'
//		sql.append(" And cmdet.EXME_CitaMedica_ID = ? And cmdet.isActive = 'Y'");
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			pstmt.setString(1, MProduct.PROCEDURETYPE_Diet);
//			pstmt.setInt(2, cita);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				product = new MProduct(ctx, rs.getInt(1), null);
//				product.setDescription(rs.getString(2));
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return product;
//	}
	
	public static MProduct getProductCmDet(Properties ctx, int cita, boolean indication) {
		MProduct product = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		sql.append("SELECT prod.*, Cmdet.Description as cmdetDesc ");
		sql.append(" FROM Exme_Citamedicadet Cmdet ");
		if (indication) {
			sql.append(" INNER JOIN Exme_Actpacienteind Act ON Act.M_Product_Id = Cmdet.M_Product_Id) ");
		}
		sql.append(" INNER JOIN M_Product prod ON prod.M_Product_Id=")//
			.append(indication ? "Act" : "Cmdet").append(".M_Product_Id ");
		sql.append(" WHERE cmdet.isActive='Y' ");
		sql.append(" AND prod.ProcedureType=? ");//'DI'
		sql.append(" AND cmdet.EXME_CitaMedica_ID=? ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setString(1, MProduct.PROCEDURETYPE_Diet);
			pstmt.setInt(2, cita);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = new MProduct(ctx, rs, null);
				product.setComment(rs.getString("cmdetDesc"));
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return product;
	}
	
	public MConfigEC getConfigEC() {
		return configEC;
	}

	/**
	 * 
	 */
	public void inactivateTemp(List<ServicioView> list, String trxName) {
		if (appointment.getEXME_CitaMedica_ID() > 0 && !list.isEmpty()) {
			MEXMECitaMedicaDet.inactivateTemp(list, appointment.getEXME_CitaMedica_ID(), trxName);
		}
	}

}
