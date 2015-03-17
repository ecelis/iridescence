/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.Utilerias;

/**
 * @author Lorena Lama
 * Se comentan codigo agregado para manejo de modelo PO desde un jsp de struts
 */
public class MEXMEIncapacidadPac extends X_EXME_Incapacidad_Pac {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static CLogger s_log = CLogger.getCLogger(MEXMEIncapacidadPac.class);

	/**
	 * @param ctx
	 * @param EXME_Incapacidad_Pac_ID
	 * @param trxName
	 */
	public MEXMEIncapacidadPac(Properties ctx, int EXME_Incapacidad_Pac_ID, String trxName) {
		super(ctx, EXME_Incapacidad_Pac_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEIncapacidadPac(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	
	/**
	 * 
	 * @param ctx
	 * @param EXME_Incapacidad_Pac_ID
	 * @throws Exception
	 */
	public MEXMEIncapacidadPac(Properties ctx, int EXME_Incapacidad_Pac_ID) throws Exception {
		
		super(ctx, EXME_Incapacidad_Pac_ID, null);
		
//		if(EXME_Incapacidad_Pac_ID > 0){
//			
//			this.m_paciente = null;
//			this.m_incapacidad = null;
//			
//			if(getEXME_CtaPac_ID() > 0) {//cta paciente
//				this.setCtaPac(getEXME_CtaPac_ID(), null);
//			}
//			if(getEXME_Paciente_ID() > 0 && m_paciente == null) {//paciente
//				this.setPaciente(getEXME_Paciente_ID(), null);
//			}
//			if(getEXME_Medico_ID() > 0 && m_medico == null) {//medico solicitante
//				this.setMedico(getEXME_Medico_ID(), null);
//			}
//			if(getEXME_Incapacidad_ID() > 0 && m_incapacidad == null) {//Incapacidad
//				this.setIncapacidad(getEXME_Incapacidad_ID(), null);
//			}
//		}
	}
	
	/**
	 * Copia la interconsulta a partir de un objeto origen
	 * @param object
	 * @return
	 *
	public static MEXMEIncapacidadPac copyFrom(MEXMEIncapacidadPac object) throws Exception{        
		MEXMEIncapacidadPac newObject = new MEXMEIncapacidadPac(object.getCtx(), 0, object.get_TrxName());       
		copyValues(object, newObject);
		
		if(object.m_paciente != null){//paciente
			newObject.setPaciente(0, object.m_paciente);
		}
		if(object.m_medico != null) {//medico
			newObject.setMedico(0, object.m_medico);
		}
		if(object.m_medico2 != null) {//medico2
			newObject.setMedico2(0, object.m_medico2);	
		}
		if(object.m_ctaPac != null) {//cta paciente
			newObject.setCtaPac(0, object.m_ctaPac);
		}
		if(object.m_incapacidad != null){ //incapacidad
			newObject.setIncapacidad(0, object.m_incapacidad);
		}
        return newObject;
    }
	
	
	/**
	 * Valida los obligatorios
	 * @return
	 */
	public  void validate() throws Exception{        
		
		if(getEXME_Paciente_ID() <= 0){
			throw new Exception(Utilerias.getMessage(getCtx(), null, "odontologia.msj.Paciente"));
		}
		if(getEXME_Medico_ID() <= 0){
			throw new Exception(Utilerias.getMessage(getCtx(), null, "error.citas.medico"));
		}
		if(getEXME_Incapacidad_ID() <= 0){
			throw new Exception(Utilerias.getMessage(getCtx(), null, "error.msj"));//FIXME
		}
		if(getFecha_Ini() == null || getFecha_Ini().equals("")){
			throw new Exception(Utilerias.getMessage(getCtx(), null, "error.caja.fechaIni"));
		}
		if(getFecha_Fin() == null || getFecha_Fin().equals("")){
			throw new Exception(Utilerias.getMessage(getCtx(), null, "error.caja.fechaFin"));
		}
		if(getFecha_Registro() == null || getFecha_Registro().equals("")){
			throw new Exception(Utilerias.getMessage(getCtx(), null, "ingresoPaciente.error.fechaRegistro"));
		}
    }
	
	//ctapac
//	private String ctaPacDoc = null;
//	private MEXMECtaPac m_ctaPac = null;
//	//paciente
//	private String pacienteValue = null;
//	private String pacienteName = null;
//	private MEXMEPaciente m_paciente = null;
//	//medico
//	private String medicoName = null;
//	private String medicoValue = null;
//	private MEXMEMedico m_medico = null;
//	private MEXMEMedico m_medico2 = null;
//	//medico 2
//	private String medico2Name = null;
//	private String medico2Value = null;
//
//	//incapacidad
//	private String incapacidadValue = null;
//	private String incapacidadName = null;
//	private X_EXME_Incapacidad m_incapacidad = null;
//	//especialidad
//	private String especialidadValue = null;
//	private String especialidadName = null;
////	private MEXMEEspecialidad m_especialidad = null;
	
	
	/**
	 * Paciente
	 * @param pacienteID
	 * @param paciente
	 * @throws Exception
	 *
	public void setPaciente(int pacienteID, MEXMEPaciente paciente) throws Exception {
		if (paciente == null) {
			if (pacienteID > 0) {
				paciente = new MEXMEPaciente(getCtx(), pacienteID, null);
			} else {
				throw new Exception(Utilerias.getMessage(getCtx(), null, "error.find.paciente"));
			}
		}
		this.m_paciente = paciente;
		setEXME_Paciente_ID(paciente.getEXME_Paciente_ID());
		setPacienteName(paciente.getNombre_Pac());
		setPacienteValue(paciente.getValue());
	}
	
	/**
	 * Cuenta Paciente
	 * @param ctapacID
	 * @param ctapac
	 * @throws Exception
	 *
	public void setCtaPac(int ctapacID, MEXMECtaPac ctapac) throws Exception {
		if (ctapac == null) {
			if (ctapacID > 0){
				ctapac = new MEXMECtaPac(getCtx(), ctapacID, null);
			}else{
				throw new Exception(Utilerias.getMessage(getCtx(), null, "error.find.ctapac"));
			}
		}
		this.m_ctaPac = ctapac;
		setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
		setCtaPacDoc(ctapac.getDocumentNo());
		if (m_medico == null)
			this.setMedico(0, ctapac.getMedico());
		if (m_paciente == null)
			this.setPaciente(0, ctapac.getPaciente());
	}
	
	/**
	 * Medico Solicitante
	 * @param medicoID
	 * @param medico
	 * @throws Exception
	 *
	public void setMedico(int medicoID, MEXMEMedico medico) throws Exception {
		if (medico == null ) {
			if( medicoID > 0){
				medico = new MEXMEMedico(getCtx(), medicoID, null);
			}else{
				throw new Exception(Utilerias.getMessage(getCtx(), null, "error.find.medico"));
			}
		}
		this.m_medico = medico;
		setEXME_Medico_ID(medico.getEXME_Medico_ID());
		setMedicoValue(medico.getValue());
		setMedicoName(medico.getNombre_Med());
	}
	
	/**
	 * Medico Solicitante
	 * @param medicoID
	 * @param medico
	 * @throws Exception
	 *
	public void setMedico2(int medico2ID, MEXMEMedico medico2) throws Exception {
		if (medico2 == null ) {
			if( medico2ID > 0){
				medico2 = new MEXMEMedico(getCtx(), medico2ID, null);
			}else{
				throw new Exception(Utilerias.getMessage(getCtx(), null, "error.find.medico"));
			}
		}
		this.m_medico2 = medico2;
		setEXME_Medico2_ID(medico2.getEXME_Medico_ID());
		setMedico2Value(medico2.getValue());
		setMedico2Name(medico2.getNombre_Med());
	}
	
	/**
	 * Incapacidad
	 * @param incapacidadID
	 * @param incapacidad
	 * @throws Exception
	 *
	public void setIncapacidad(int incapacidadID, X_EXME_Incapacidad incapacidad) throws Exception {
		if (incapacidad == null) {
			if (incapacidadID > 0){
				incapacidad = new X_EXME_Incapacidad(getCtx(), incapacidadID, null);
			}else{
				throw new Exception(Utilerias.getMessage(getCtx(), null, "error.find.incapacidad"));
			}
		}
		this.m_incapacidad = incapacidad;
		setEXME_Incapacidad_ID(incapacidad.getEXME_Incapacidad_ID());
		setIncapacidadName(incapacidad.getName());
		setIncapacidadValue(incapacidad.getValue());
	}
		
	/**
	 * Paciente
	 * @param especialidadID
	 * @param paciente
	 * @throws Exception
	 *
	public void setEspecialidad(int especialidadID, MEXMEEspecialidad especialidad) throws Exception {
		if (especialidad == null) {
			if (especialidadID > 0){
				especialidad = new MEXMEEspecialidad(getCtx(), especialidadID, null);
			}else{
				throw new Exception(Utilerias.getMessage(getCtx(), null, "error.find.especialidad"));
			}
		}
//		this.m_especialidad = especialidad;
		setEXME_Especialidad_ID(especialidad.getEXME_Especialidad_ID());
		setEspecialidadName(especialidad.getName());
		setEspecialidadValue(especialidad.getValue());
	}
	
	/**
	 * Obtenemos una lista de registros de Incapacidades para el expediente.  
	 * @author rvelazquez
	 * @param ctx         properties 
	 * @param pacienteID  ID del Paciente
	 * @param trxName     Transaccion
	 * 
	 * @return List<ExpedienteView>
	 * 
	 *
	public static List<ExpedienteView> getIncapacidades(Properties ctx, int pacienteID,String trxName) throws Exception {
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		List<ExpedienteView> lista = new ArrayList<ExpedienteView>();

		sql.append("SELECT  inc.exme_incapacidad_pac_id as incapacidadPacID, pac.value, pac.expediente, ")//
				.append(" pac.nombre_pac, med.nombre_med as medico, esp.name as especialidad, ")//
				.append(" inc.fecha_registro, inc.fecha_ini, inc.fecha_fin, inc.description, ")//
				.append(" ei.name as incapacidad_name, ei.motivo as incapacidad_motivo ")//
				.append(" FROM exme_incapacidad_pac inc ")//
				.append(" INNER JOIN exme_paciente pac ON pac.exme_paciente_id= inc.exme_paciente_id ")//
				.append(" INNER JOIN exme_medico med ON med.exme_medico_id = inc.exme_medico_id ")//
				.append(" LEFT JOIN exme_especialidad esp ON esp.exme_especialidad_id = inc.exme_especialidad_id ")//
				.append(" LEFT JOIN exme_incapacidad ei ON ei.exme_incapacidad_id = inc.exme_incapacidad_id ")//
				.append(" WHERE inc.exme_paciente_id=? ")//
				// nivel de acceso
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEIncapacidadPac.Table_Name, "inc"))//
				.append(" ORDER BY inc.fecha_registro ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, pacienteID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ExpedienteView exp = new ExpedienteView();
				exp.setFecha(rs.getString("fecha_registro"));
				exp.setEspecialidad(rs.getString("especialidad"));
				exp.setMedicoName(rs.getString("medico"));
				exp.setFechaIni(rs.getString("fecha_ini"));
				exp.setFechaFin(rs.getString("fecha_fin"));
				exp.setIncapacidadMotivo(rs.getString("incapacidad_motivo"));
				exp.setIncapacidadName(rs.getString("incapacidad_name"));
				exp.setIncapacidadPacID(rs.getInt("incapacidadPacID"));

				lista.add(exp);
			}

		} catch (SQLException e) {
			s_log.log(Level.SEVERE, "getIncapacidades", e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return lista;
	}*/
	
	
	/******** Getters / Setters ***************


	public String getCtaPacDoc() {
		return ctaPacDoc;
	}

	public void setCtaPacDoc(String ctaPacDoc) {
		this.ctaPacDoc = ctaPacDoc;
	}

	public String getPacienteValue() {
		return pacienteValue;
	}

	public void setPacienteValue(String pacienteValue) {
		this.pacienteValue = pacienteValue;
	}

	public String getPacienteName() {
		return pacienteName;
	}

	public void setPacienteName(String pacienteName) {
		this.pacienteName = pacienteName;
	}


	public String getMedicoName() {
		return medicoName;
	}

	public void setMedicoName(String medicoName) {
		this.medicoName = medicoName;
	}

	public String getMedicoValue() {
		return medicoValue;
	}

	public void setMedicoValue(String medicoValue) {
		this.medicoValue = medicoValue;
	}

	public String getIncapacidadValue() {
		return incapacidadValue;
	}

	public void setIncapacidadValue(String incapacidadValue) {
		this.incapacidadValue = incapacidadValue;
	}

	public String getIncapacidadName() {
		return incapacidadName;
	}

	public void setIncapacidadName(String incapacidadName) {
		this.incapacidadName = incapacidadName;
	}

	public String getEspecialidadValue() {
		return especialidadValue;
	}

	public void setEspecialidadValue(String especialidadValue) {
		this.especialidadValue = especialidadValue;
	}

	public String getEspecialidadName() {
		return especialidadName;
	}

	public void setEspecialidadName(String especialidadName) {
		this.especialidadName = especialidadName;
	}

	public String getMedico2Name() {
		return medico2Name;
	}

	public void setMedico2Name(String medico2Name) {
		this.medico2Name = medico2Name;
	}

	public String getMedico2Value() {
		return medico2Value;
	}

	public void setMedico2Value(String medico2Value) {
		this.medico2Value = medico2Value;
	} */
}
