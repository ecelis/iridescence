package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;

/**
 * Modelo de la tabla interconsulta - encame
 * 
 * @author Lorena Lama
 * Se comentan codigo agregado para manejo de modelo PO desde un jsp de struts
 */
public class MEXMEInterconsulta extends X_EXME_Interconsulta {

	/** serialVersionUID */
	private static final long serialVersionUID = 9016682848171014222L;
	/** log */
	private static CLogger s_log = CLogger.getCLogger(MEXMEInterconsulta.class);

	/**
	 * @param ctx
	 * @param EXME_Interconsulta_ID
	 * @param trxName
	 */
	public MEXMEInterconsulta(Properties ctx, int EXME_Interconsulta_ID, String trxName) {
		super(ctx, EXME_Interconsulta_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEInterconsulta(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	// medico solitante
//	private String medicoName = null;
//	private String medicoValue = null;

	// medico tratante
//	private String medico2Name = null;
//	private String medico2Value = null;

	// especialidad
//	private String especialidadName = null;
//	private String especialidadSolName = null;

	// paciente
//	private String pacienteValue = null;
//	private String pacienteName = null;

	// interconsultas
//	private String citaName = null;

	// promocion
//	private String promocion = null;

	// externa
//	private String institucionName = null;

	// hospitalizacion
//	private String ctaPacDoc = null;

	// encame: estacion servicio
//	private String estServSolVal = null;
//	private String estServSolName = null;

//	private String estServValue = null;
//	private String estServName = null;

//	private String diagnosticoValue = null;
	private String diagnosticoName = null;

//	private String camaName = null;

//	private String motivoTrasladoName = null;

	private boolean hide = false;

	private MEXMECitaMedicaView m_cita = null;

	private MEXMEPaciente m_paciente = null;
	private MEXMECtaPac m_ctaPac = null;

	private MEXMEMedico m_medico = null; // med. solicitante

	private MEXMEMedico m_medico2 = null; // med. tratante (encame)

	/**
	 * Llena la informacion de una interconsulta existente
	 * 
	 * @param ctx
	 * @param EXME_Interconsulta_ID
	 * @throws Exception
	 */
	public MEXMEInterconsulta(Properties ctx, int EXME_Interconsulta_ID) throws Exception {

		super(ctx, EXME_Interconsulta_ID, null);

		if (EXME_Interconsulta_ID > 0) {

			this.m_cita = null;
			this.m_ctaPac = null;
			this.m_paciente = null;
			this.m_medico = null;
			this.m_medico2 = null;

			if (getEXME_CitaMedica_ID() > 0 && getM_cita() == null)// cita medica
				this.setCitaMed(getEXME_CitaMedica_ID(), null);

			if (getEXME_CtaPac_ID() > 0 && m_ctaPac == null) // cta paciente
				this.setCtaPac(getEXME_CtaPac_ID(), null);

			if (getEXME_Medico_ID() > 0 && m_medico == null) // medico solicitante
				this.setMedico(getEXME_Medico_ID(), null);

			if (getEXME_Medico2_ID() > 0 && m_medico2 == null) // medico tratante (encame)
				this.setMedico2(getEXME_Medico2_ID(), null);

			if (getEXME_Paciente_ID() > 0 && m_paciente == null) // paciente
				this.setPaciente(getEXME_Paciente_ID(), null);

			if (getEXME_Especialidad_Sol_ID() > 0) // especialidad solicitante
				this.setEspecilidadSol(getEXME_Especialidad_Sol_ID(), null);

			if (getEXME_Especialidad_ID() > 0) // especialidad solicitada
				this.setEspecilidad(getEXME_Especialidad_ID(), null);

			if (getEXME_Diagnostico_ID() > 0) // especialidad solicitada
				this.setDiagnostico(getEXME_Diagnostico_ID(), null);
		}
	}

	/**
	 * Copia la interconsulta a partir de un objeto origen
	 * 
	 * @param object
	 * @return
	 */
	public static MEXMEInterconsulta copyFrom(MEXMEInterconsulta object) {
		MEXMEInterconsulta newObject = new MEXMEInterconsulta(object.getCtx(), 0, object.get_TrxName());
		copyValues(object, newObject);
		return newObject;
	}

	/** TIPO DE SOLICITUD **/
	public static final String DOCTYPE_Unknown = "?";

	public static final String DOCTYPE_MilitaryPromotion = "P";

	public static final String DOCTYPE_Consultation = "I";

	public static final String DOCTYPE_ConsultationHosp = "H";

	public static final String DOCTYPE_BedAccommodation = "E";

	public void setDocType(String docType) {
		if (docType == null)
			throw new IllegalArgumentException("DocType is mandatory");
		if (docType.equals(DOCTYPE_MilitaryPromotion) || docType.equals(DOCTYPE_ConsultationHosp) || docType.equals(DOCTYPE_BedAccommodation)
				|| docType.equals(DOCTYPE_Consultation))
			;
		else
			throw new IllegalArgumentException("DocStatus Invalid value - " + docType);
		set_Value("docType", docType);

		super.setDocType(docType);
	}

	/**
	 * Completa el documento
	 * 
	 * @throws SQLException
	 */
	public void completeIt(boolean throwException) throws SQLException {
		setDateDelivered(Env.getCurrentDate());
		setDocStatus(DOCSTATUS_Completed);
		if (throwException) {
			if (!save(get_TrxName()))
				throw new SQLException("error.interconsulta.save");
		} else
			save(get_TrxName());
	}

	/**
	 * Completa el documento al crear la ctapac
	 * 
	 * @throws SQLException
	 */
	public void completeIt(int EXME_CtaPac_ID, String trxName) throws SQLException {
		set_TrxName(trxName);
		setEXME_CtaPac_ID(EXME_CtaPac_ID);

		// si tiene diagnostico
		if (getEXME_Diagnostico_ID() > 0) {

			// obtenemos la actpaciete de ctapac
			MEXMEActPaciente actpac = MEXMEActPaciente.getActPaciente(getCtx(), EXME_CtaPac_ID, null, trxName);
			if (actpac.getEXME_ActPaciente_ID() > 0) {
				// insertamos el diagnostico en act
				MActPacienteDiag diag = new MActPacienteDiag(getCtx(), 0, trxName);

				diag.setEXME_ActPaciente_ID(actpac.getEXME_ActPaciente_ID());
				diag.setEXME_Diagnostico_ID(getEXME_Diagnostico_ID());
				diag.setDescription(getDiagnostico());
				
				 // lama - reg origen
				diag.setRecord_ID(getEXME_Interconsulta_ID());
				diag.setAD_Table_ID(Table_ID);
				
				if(!diag.save(trxName))
					s_log.log(Level.SEVERE, "Interconsulta not saved");
			}
		}
		completeIt(false);
	}

	/**
	 * Completa el documento con las notas medicas
	 * 
	 * @throws SQLException
	 */
	public void completeIt(int actPacID, Integer folio, String trxName) throws SQLException {
		set_TrxName(trxName);
		setEXME_ActPaciente_ID(actPacID);
		setFolio(folio);
		completeIt(true);
	}

	/**
	 * Completa el documento con las notas medicas
	 * 
	 * @throws SQLException
	 */
	public void completeIt(String trxName, int citaID) throws SQLException {
		set_TrxName(trxName);
		setRef_CitaMedica_ID(citaID);
		completeIt(true);
	}

	/**
	 * Obtiene una lista de solicitudes de interconsultas de acuerdo a los parametros
	 * 
	 * @param ctx
	 * @param join Otras tablas requeridas
	 * @param whereClause Condicion de la consulta
	 * @param recordType Tipo de registro: DOCTYPE
	 * @param orderBy
	 * @param trxName
	 * @return
	 *
	public static List<MEXMEInterconsulta> getLst(Properties ctx, StringBuilder join, StringBuilder whereClause, String recordType, StringBuilder orderBy,
			String trxName) {
		List<MEXMEInterconsulta> retValue = new ArrayList<MEXMEInterconsulta>();

		StringBuilder sql = getListaSolicitudes(recordType);

		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			if (join != null)
				sql.append(join);

			sql.append(" WHERE EXME_Interconsulta.isActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEInterconsulta.Table_Name))
				.append(" AND EXME_Interconsulta.DocType = ? ");

			if (whereClause != null)
				sql.append(whereClause);

			sql.append(" ORDER BY ").append(orderBy != null ? orderBy : " EXME_Interconsulta.dateDoc DESC ");

			psmt = DB.prepareStatement(sql.toString(), trxName);
			psmt.setString(1, recordType);
			rs = psmt.executeQuery();

			while (rs.next()) {
				MEXMEInterconsulta model = new MEXMEInterconsulta(ctx, rs, trxName);
				model.setData(rs, recordType);
				retValue.add(model);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, psmt);
		}

		return retValue;
	}*/

	/**
	 * SQL para obtener las solicitudes deacuerdo al tipo de registro
	 * 
	 * @param recordType
	 * @return
	 *
	private static StringBuilder getListaSolicitudes(String recordType) {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT DISTINCT EXME_Interconsulta.*, med.value as medicoValue, med.nombre_med as medicoName ")
			.append(", pac.value as pacienteValue, pac.nombre_pac as pacienteName ")
			.append(", esp.value as especialidadVal, esp.name as especialidadName ")
			.append(", espSol.value as especialidadSolVal, espSol.name as especialidadSolName ");

		// promociones o interconsulta
		if (!recordType.equals(DOCTYPE_ConsultationHosp))
			sql.append(", cita.name as citaName, cita.n_promo as promocion ");
		// interconsulta externa
		if (recordType.equals(DOCTYPE_Consultation))
			sql.append(", inst.name as institucionName ");
		// encame
		else if (recordType.equals(DOCTYPE_BedAccommodation))
			sql.append(", estSerSol.value as estServSolVal, estSerSol.name as estServSolName ")
				.append(", nvl(motTras.name,motTras.value) as motivoTrasladoName ")
				.append(", diag.value as diagValue, diag.name as diagName ");
		
		// interconsulta - hospitalizacion
		if (recordType.equals(DOCTYPE_ConsultationHosp) || recordType.equals(DOCTYPE_BedAccommodation)) 
			sql.append(", ctapac.documentNo as ctaPacDoc ")
				.append(", estServ.value as estServValue, estServ.name as estServName ")
				.append(", cama.name as camaName ");

		
		// ** FROM ** //
		sql.append("FROM EXME_Interconsulta ")
			.append("INNER JOIN EXME_Medico med on (EXME_Interconsulta.EXME_Medico_ID = med.EXME_Medico_ID) ")
			.append("INNER JOIN EXME_Paciente pac on (EXME_Interconsulta.EXME_Paciente_ID = pac.EXME_Paciente_ID) ")
			.append("INNER JOIN EXME_Especialidad esp on (EXME_Interconsulta.EXME_Especialidad_ID = esp.EXME_Especialidad_ID) ")
			.append("LEFT JOIN EXME_Especialidad espSol on (EXME_Interconsulta.EXME_Especialidad_Sol_ID = espSol.EXME_Especialidad_ID) ");

		// interconsulta  - hospitalizacion
		if (!recordType.equals(DOCTYPE_ConsultationHosp)) {
			// promociones, interconsulta, encame
			if (recordType.equals(DOCTYPE_MilitaryPromotion))
				sql.append("INNER");
			else
				sql.append("LEFT");
			sql.append(" JOIN EXME_CitaMedica cita on (EXME_Interconsulta.EXME_citamedica_ID = cita.EXME_citamedica_ID) ");
		}		
		
		// interconsulta externa
		if (recordType.equals(DOCTYPE_Consultation)) {
			sql.append("LEFT JOIN EXME_Institucion inst on (EXME_Interconsulta.EXME_institucion_ID = inst.EXME_institucion_ID) ");
		}

		// Ticket # 1411 Agregar la cama y la estacion de servicio Interconsulta - Hospitalizacion
		// encame - hospitalizacion
		if (recordType.equals(DOCTYPE_ConsultationHosp) || recordType.equals(DOCTYPE_BedAccommodation)) {
			
			if (recordType.equals(DOCTYPE_ConsultationHosp))
				sql.append("INNER");
			else
				sql.append("LEFT");
			
			sql.append(" JOIN EXME_Ctapac ctapac on (EXME_Interconsulta.EXME_ctapac_ID = ctapac.EXME_ctapac_ID) ")
				.append(" LEFT JOIN EXME_Cama cama on (ctapac.EXME_CtaPac_ID = cama.EXME_CtaPac_ID) ");
			
			// encame
			if (recordType.equals(DOCTYPE_BedAccommodation)) 
				sql.append("INNER JOIN EXME_EstServ estServ on (EXME_Interconsulta.EXME_estServ_ID = estServ.EXME_estServ_ID) ")
					.append("INNER JOIN EXME_estServ estSerSol on (EXME_Interconsulta.EXME_estServ_sol_ID = estSerSol.EXME_estServ_ID) ")
					.append("LEFT JOIN EXME_motivotraslado motTras on (EXME_Interconsulta.EXME_motivotraslado_ID = motTras.EXME_motivotraslado_ID) ")
					.append("LEFT JOIN EXME_diagnostico diag on (EXME_Interconsulta.EXME_Diagnostico_ID = diag.EXME_Diagnostico_ID) ");
			else
				sql.append("LEFT JOIN EXME_Habitacion hab on (hab.EXME_habitacion_ID = cama.EXME_habitacion_ID) ")
					.append("LEFT JOIN EXME_Estserv estServ on (hab.EXME_estServ_ID = estServ.EXME_estServ_ID) ");
		}
		return sql;
	}

	/**
	 * Mapeo de las propiedades del modelo y result set
	 * 
	 * @param rs
	 * @throws SQLException
	 *
	private void setData(ResultSet rs, String recordType) throws SQLException {

		// medico solitante
//		setMedicoName(rs.getString("medicoName"));
//		setMedicoValue(rs.getString("medicoValue"));
//		setEspecialidadName(rs.getString("especialidadName"));
//		setEspecialidadSolName(rs.getString("especialidadSolName"));
//		setPacienteValue(rs.getString("pacienteValue"));
//		setPacienteName(rs.getString("pacienteName"));

		if (!recordType.equals(DOCTYPE_ConsultationHosp)) {
//			setCitaName(rs.getString("citaName"));
//			setPromocion(rs.getString("promocion"));
		}
		
		// Ticket # 1411 Agregar la cama y la estacion de servicio Interconsulta - Hospitalizacion
		if (recordType.equals(DOCTYPE_ConsultationHosp) || recordType.equals(DOCTYPE_BedAccommodation)) {
//			setCtaPacDoc(rs.getString("ctaPacDoc"));
//			setCamaName(rs.getString("camaName"));
//			setEstServName(rs.getString("estServName"));
//			setEstServValue(rs.getString("estServValue"));
		}
		if (recordType.equals(DOCTYPE_BedAccommodation)) {
//			setEstServSolVal(rs.getString("estServSolVal"));
//			setEstServSolName(rs.getString("estServSolName"));
			//setEstServValue(rs.getString("estServValue"));
			//setEstServName(rs.getString("estServName"));
//			setMotivoTrasladoName(rs.getString("motivoTrasladoName"));
			setDiagnosticoName(rs.getString("diagName"));
//			setDiagnosticoValue(rs.getString("diagValue"));
		}
	}
*/
	/**
	 * Cuenta Paciente
	 * 
	 * @param ctapacID
	 * @param ctapac
	 * @throws Exception
	 */
	public void setCtaPac(int ctapacID, MEXMECtaPac ctapac) throws Exception {
		if (ctapac == null) {
			if (ctapacID > 0)
				ctapac = new MEXMECtaPac(getCtx(), ctapacID, null);
			else
				throw new Exception("error.find.ctapac");
		}
		this.m_ctaPac = ctapac;
		setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
//		setCtaPacDoc(ctapac.getDocumentNo());
		this.setMedico(0, ctapac.getMedico());
		this.setPaciente(0, ctapac.getPaciente());
	}

	/**
	 * Paciente
	 * 
	 * @param pacienteID
	 * @param paciente
	 * @throws Exception
	 */
	public void setPaciente(int pacienteID, MEXMEPaciente paciente) throws Exception {
		if (paciente == null) {
			if (pacienteID > 0)
				paciente = new MEXMEPaciente(getCtx(), pacienteID, null);
			else
				throw new Exception("error.find.paciente");
		}
		this.m_paciente = paciente;
		setEXME_Paciente_ID(paciente.getEXME_Paciente_ID());
//		setPacienteName(paciente.getNombre_Pac());
//		setPacienteValue(paciente.getValue());
	}

	/**
	 * Medico Solicitante
	 * 
	 * @param medicoID
	 * @param medico
	 * @throws Exception
	 */
	public void setMedico(int medicoID, MEXMEMedico medico) throws Exception {
		if (medico == null) {
			if (medicoID > 0)
				medico = new MEXMEMedico(getCtx(), medicoID, null);
			else
				throw new Exception("error.find.medico");
		}
		this.m_medico = medico;
		setEXME_Medico_ID(medico.getEXME_Medico_ID());
//		setMedicoValue(medico.getValue());
//		setMedicoName(medico.getNombre_Med());
	}

	/**
	 * Medico Tratante
	 * 
	 * @param medicoID
	 * @param medico
	 * @throws Exception
	 */
	public void setMedico2(int medico2ID, MEXMEMedico medico2) throws Exception {
		if (medico2 == null) {
			if (medico2ID > 0)
				medico2 = new MEXMEMedico(getCtx(), medico2ID, null);
			else
				throw new Exception("error.find.medico");
		}
		this.m_medico2 = medico2;
		setEXME_Medico2_ID(medico2.getEXME_Medico_ID());
//		setMedico2Value(medico2.getValue());
//		setMedico2Name(medico2.getNombre_Med());
	}

	/**
	 * Cita Medica
	 * 
	 * @param citaID
	 * @param cita
	 * @throws Exception
	 */
	public void setCitaMed(int citaID, MEXMECitaMedica cita) throws Exception {
		MEXMECitaMedicaView view = null;
		if (cita == null) {
			if (citaID > 0) {
				view = new MEXMECitaMedicaView(getCtx(), citaID, null);
				cita = (MEXMECitaMedica) view;
			} else
				throw new Exception("error.find.citamedica");
		}
		this.m_cita = view;
		setEXME_CitaMedica_ID(cita.getEXME_CitaMedica_ID());
//		setPromocion(cita.getN_Promo());
//		setCitaName(cita.getName());
		this.setEspecilidadSol(cita.getEXME_Especialidad_ID(), null);
		this.setMedico(0, cita.getMedico());
		this.setPaciente(0, cita.getPaciente());
	}

	/**
	 * Especialidad Solicitante
	 * 
	 * @param especialidadID
	 * @param especialidad
	 * @throws Exception
	 */
	public void setEspecilidadSol(int especialidadID, MEXMEEspecialidad especialidad) throws Exception {
		if (especialidad == null) {
			if (especialidadID > 0)
				especialidad = new MEXMEEspecialidad(getCtx(), especialidadID, null);
			else
				throw new Exception("error.find.especialidad");
		}

		setEXME_Especialidad_Sol_ID(especialidad.getEXME_Especialidad_ID());
//		setEspecialidadSolName(especialidad.getName());
	}

	/**
	 * Especialidad Solicitada
	 * 
	 * @param especialidadID
	 * @param especialidad
	 * @throws Exception
	 */
	private void setEspecilidad(int especialidadID, MEXMEEspecialidad especialidad) throws Exception {
		if (especialidad == null) {
			if (especialidadID > 0)
				especialidad = new MEXMEEspecialidad(getCtx(), especialidadID, null);
			else
				throw new Exception("error.find.especialidad");
		}

		setEXME_Especialidad_ID(especialidad.getEXME_Especialidad_ID());
//		setEspecialidadName(especialidad.getName());
	}

	/**
	 * Diagnostico Final
	 * 
	 * @param diagnosticoID
	 * @param diagnostico
	 * @throws Exception
	 */
	public void setDiagnostico(int diagnosticoID, MDiagnostico diagnostico) throws Exception {
		if (diagnostico == null) {
			if (diagnosticoID > 0)
				diagnostico = new MDiagnostico(getCtx(), diagnosticoID, null);
			else
				throw new Exception("error.find.diagnostico");
		}
		setEXME_Diagnostico_ID(diagnostico.getEXME_Diagnostico_ID());
//		setDiagnosticoValue(diagnostico.getValue());
//		setDiagnosticoName(diagnostico.getName());
	}

	/**
	 * Obtiene todas las citas de promocion relacionadas a un medico
	 * 
	 * @param ctx
	 * @param medID
	 * @param trxName
	 * @return
	 *
	public static List<LabelValueBean> getCitas(Properties ctx, int medicoID, boolean emptyRow, String trxName) {
		List<LabelValueBean> retValue = new ArrayList<LabelValueBean>();
		retValue = MEXMECitaMedica.getCitasPromo(ctx, medicoID, emptyRow, true, trxName);
		return retValue;
	}*/

	/****************** Getters/Setters *************************/

	public boolean isCompleted() {
		boolean retValue = false;
		if (getDocStatus().equalsIgnoreCase(DOCSTATUS_Completed))
			retValue = true;
		return retValue;
	}

//	public String getMedicoName() {
//		return medicoName;
//	}
//
//	public void setMedicoName(String medicoName) {
//		this.medicoName = medicoName;
//	}
//
//	public String getMedicoValue() {
//		return medicoValue;
//	}
//
//	public void setMedicoValue(String medicoValue) {
//		this.medicoValue = medicoValue;
//	}

//	public String getEspecialidadName() {
//		return especialidadName;
//	}
//
//	public void setEspecialidadName(String especialidadName) {
//		this.especialidadName = especialidadName;
//	}
//
//	public String getEspecialidadSolName() {
//		return especialidadSolName;
//	}
//
//	public void setEspecialidadSolName(String especialidadSolName) {
//		this.especialidadSolName = especialidadSolName;
//	}

//	public String getPacienteValue() {
//		return pacienteValue;
//	}
//
//	public void setPacienteValue(String pacienteValue) {
//		this.pacienteValue = pacienteValue;
//	}
//
//	public String getPacienteName() {
//		return pacienteName;
//	}
//
//	public void setPacienteName(String pacienteName) {
//		this.pacienteName = pacienteName;
//	}

//	public String getCitaName() {
//		return citaName;
//	}
//
//	public void setCitaName(String citaName) {
//		this.citaName = citaName;
//	}
//
//	public String getPromocion() {
//		return promocion;
//	}
//
//	public void setPromocion(String promocion) {
//		this.promocion = promocion;
//	}
//
//	public String getInstitucionName() {
//		return institucionName;
//	}
//
//	public void setInstitucionName(String institucionName) {
//		this.institucionName = institucionName;
//	}
//
//	public String getCtaPacDoc() {
//		return ctaPacDoc;
//	}
//
//	public void setCtaPacDoc(String ctaPacDoc) {
//		this.ctaPacDoc = ctaPacDoc;
//	}
//
//	public String getEstServSolVal() {
//		return estServSolVal;
//	}
//
//	public void setEstServSolVal(String estServSolVal) {
//		this.estServSolVal = estServSolVal;
//	}
//
//	public String getEstServSolName() {
//		return estServSolName;
//	}
//
//	public void setEstServSolName(String estServSolName) {
//		this.estServSolName = estServSolName;
//	}
//
//	public String getEstServValue() {
//		return estServValue;
//	}
//
//	public void setEstServValue(String estServValue) {
//		this.estServValue = estServValue;
//	}
//
//	public String getEstServName() {
//		return estServName;
//	}
//
//	public void setEstServName(String estServName) {
//		this.estServName = estServName;
//	}
//
//	public String getDiagnosticoValue() {
//		return diagnosticoValue;
//	}

//	public void setDiagnosticoValue(String diagnosticoValue) {
//		this.diagnosticoValue = diagnosticoValue;
//	}
//
	public String getDiagnosticoName() {
		return diagnosticoName;
	}

	public void setDiagnosticoName(String diagnosticoName) {
		this.diagnosticoName = diagnosticoName;
	}
//
//	public String getCamaName() {
//		return camaName;
//	}
//
//	public void setCamaName(String camaName) {
//		this.camaName = camaName;
//	}

	public boolean isHide() {
		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public MEXMECitaMedicaView getM_cita() {
		return m_cita;
	}

	public void setM_cita(MEXMECitaMedicaView mCita) {
		m_cita = mCita;
	}

	public MEXMEPaciente getM_paciente() {
		return m_paciente;
	}

	public void setM_paciente(MEXMEPaciente mPaciente) {
		m_paciente = mPaciente;
	}

	public MEXMECtaPac getM_ctaPac() {
		return m_ctaPac;
	}

	public void setM_ctaPac(MEXMECtaPac mCtaPac) {
		m_ctaPac = mCtaPac;
	}

	public MEXMEMedico getM_medico() {
		return m_medico;
	}

	public void setM_medico(MEXMEMedico mMedico) {
		m_medico = mMedico;
	}

//	public String getMotivoTrasladoName() {
//		return motivoTrasladoName;
//	}
//
//	public void setMotivoTrasladoName(String motivoTrasladoName) {
//		this.motivoTrasladoName = motivoTrasladoName;
//	}
//
//	public String getMedico2Name() {
//		return medico2Name;
//	}
//
//	public void setMedico2Name(String medico2Name) {
//		this.medico2Name = medico2Name;
//	}
//
//	public String getMedico2Value() {
//		return medico2Value;
//	}
//
//	public void setMedico2Value(String medico2Value) {
//		this.medico2Value = medico2Value;
//	}

	public MEXMEMedico getM_medico2() {
		return m_medico2;
	}

	public void setM_medico2(MEXMEMedico mMedico2) {
		m_medico2 = mMedico2;
	}
	
	/**
	 * Obtener Solicitudes pendientes (para recordatorios)
	 * @author Lizeth de la Garza
	 * @param ctx
	 * @param pacienteID
	 * @param trxName
	 * @return
	 */
	public static List<ValueNamePair> getPacSolPending(Properties ctx, int pacienteID, String trxName) {

		List<ValueNamePair> list = new ArrayList<ValueNamePair>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT inter.*  ");
		sql.append(" FROM EXME_Interconsulta inter WHERE inter.EXME_Paciente_ID = ? ");
		sql.append(" AND inter.DocType IN (?,?)  ");
		if (DB.isOracle()) {
			sql.append(" AND trunc(inter.DateDoc,'DD')>= ?");
		} else if (DB.isPostgreSQL()) {
			sql.append(" AND DATE_TRUNC('day', inter.DateDoc)>= ?");
		}

		sql.append(" ORDER BY inter.DateDoc DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null; //

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, pacienteID);
			pstmt.setString(2, DOCTYPE_BedAccommodation);
			pstmt.setString(3, DOCSTATUS_Drafted);
			pstmt.setTimestamp(4, TimeUtil.getDay(Env.getCurrentDate()));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				StringBuilder name = new StringBuilder();
				name.append(Utilerias.getMsg(ctx, "msg.histRecord.solicitudEncame"));
				name.append(rs.getString("DocumentNo")).append("-");
				name.append(Constantes.getSDFDateTime(ctx).formatConvert(rs.getTimestamp("DateDoc")));

				StringBuilder value = new StringBuilder();
				value.append(MEXMEProgRecordatorio.TIPORECORDATORIO_EXME_Interconsulta);
				value.append("-");
				value.append(rs.getInt("EXME_Interconsulta_ID"));

				list.add(new ValueNamePair(value.toString(), name.toString()));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
	
	public String getMsjEmail() {
		StringBuilder msj = new StringBuilder();
		msj.append(Utilerias.getMsg(getCtx(), "msg.histRecord.solicitudEncame"));
		msj.append(getDocumentNo());
		msj.append(" ");
		msj.append(Utilerias.getMsg(getCtx(), "msj.fechaProgramada"));
		msj.append(":");
		msj.append(Constantes.getSdfFechaHora().format(getDateDoc()));
		return msj.toString();
	}
	
	
	public String getMsjSMS() {
		StringBuilder msj = new StringBuilder();
		msj.append(Utilerias.getMsg(getCtx(), "msg.histRecord.solicitudEncameSMS"));
		msj.append(getDocumentNo());
		msj.append("+");
		msj.append(Utilerias.getMsg(getCtx(), "msg.histRecord.FechaProg"));
		msj.append(":");
		msj.append(StringUtils.replaceChars(Constantes.getSdfFechaHora().format(getDateDoc()), " ", "+"));
		return msj.toString();
	}


}