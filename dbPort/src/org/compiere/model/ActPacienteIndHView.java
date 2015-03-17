/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.process.OnCompare;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.SecureEngine;

/**
 * Modelo de la Vista para mostrar los datos de la Indicaci�n
 * <p>
 * Modificado por: $Author: taniap $
 * <p>
 * Fecha: $Date: 2006/09/27 14:42:16 $
 * <p>
 * 
 * @author Hassan Reyes
 * @version $Revision: 1.3 $
 */

public class ActPacienteIndHView extends ActPacienteIndH implements OnCompare {
	/** Static Logger					*/
	private static CLogger		s_log = CLogger.getCLogger (ActPacienteIndHView.class);
	
	public ActPacienteIndHView(){
		super();
	}
	
	/**
	 * indHID property.
	 */
	private String indHID = null;

	/**
	 * Get indHID property.
	 * 
	 *@return indHID property.
	 */
	public String getIndHID() {
		return this.indHID;
	}

	/**
	 * Set indHID property.
	 * 
	 *@param indHID
	 *            New indHID property.
	 */
	public void setIndHID(String indHID) {
		this.indHID = indHID;
	}

	/**
	 * Medico property.
	 */
	private String medico = null;

	/**
	 * Get medico property.
	 * 
	 *@return Medico property.
	 */
	public String getMedico() {
		return this.medico;
	}

	/**
	 * Set medico property.
	 * 
	 *@param medico
	 *            New medico property.
	 */
	public void setMedico(String medico) {
		this.medico = medico;
	}

	/**
	 * Paciente property. @deprecated
	 */
	private String paciente = null;

	/**
	 * Get paciente property.
	 *@deprecated
	 *@return Paciente property.
	 */
	public String getPaciente() {
		return this.paciente;
	}

	/**
	 * Set paciente property.
	 * @deprecated
	 *@param paciente
	 *            New paciente property.
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	/**
	 * Especialidad property.
	 */
//	private String especialidad = null;

	/**
	 * Get especialidad property.
	 * 
	 *@return Especialidad property.
	 *
	public String getEspecialidad() {
		return this.especialidad;
	}

	/**
	 * Set especialidad property.
	 * 
	 *@param especialidad
	 *            New especialidad property.
	 *
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	/**
	 * TipoDoc property.
	 */
//	private String tipoDoc = null;

	/**
	 * Get tipoDoc property.
	 * 
	 *@return TipoDoc property.
	 *
	public String getTipoDoc() {
		return this.tipoDoc;
	}

	/**
	 * Set tipoDoc property.
	 * 
	 *@param tipoDoc
	 *            New tipoDoc property.
	 *
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	/**
	 * NoHistoria property. @deprecated
	 */
//	private String noHistoria = null;

//	/**
//	 * Get noHistoria property.
//	 * @deprecated
//	 *@return NoHistoria property.
//	 */
//	public String getNoHistoria() {
//		return this.noHistoria;
//	}

//	/**
//	 * Set noHistoria property.
//	 * @deprecated
//	 *@param noHistoria
//	 *            New noHistoria property.
//	 */
//	public void setNoHistoria(String noHistoria) {
//		this.noHistoria = noHistoria;
//	}

	/**
	 * EsAsegurado property.
	 *
	private String esAsegurado = null;

	/**
	 * Get esAsegurado property.
	 * 
	 *@return EsAsegurado property.
	 *
	public String getEsAsegurado() {
		return this.esAsegurado;
	}

	/**
	 * Set esAsegurado property.
	 * 
	 *@param esAsegurado
	 *            New esAsegurado property.
	 *
	public void setEsAsegurado(String esAsegurado) {
		this.esAsegurado = esAsegurado;
	}

	/**
	 * NoConsultorio property.
	 *
	private String noConsultorio = null;

	/**
	 * Get noConsultorio property.
	 * 
	 *@return NoConsultorio property.
	 *
	public String getNoConsultorio() {
		return this.noConsultorio;
	}

	/**
	 * Set noConsultorio property.
	 * 
	 *@param noConsultorio
	 *            New noConsultorio property.
	 *
	public void setNoConsultorio(String noConsultorio) {
		this.noConsultorio = noConsultorio;
	}

	/**
	 * FechaOrd property.
	 */
	private java.util.Date fechaOrd = null;

	/**
	 * Get fechaOrd property.
	 * 
	 *@return FechaOrd property.
	 */
	public java.util.Date getFechaOrd() {
		return this.fechaOrd;
	}

	/**
	 * Set fechaOrd property.
	 * 
	 *@param fechaOrd
	 *            New fechaOrd property.
	 */
	public void setFechaOrd(java.util.Date fechaOrd) {
		this.fechaOrd = fechaOrd;
	}

	/**
	 * Propiedad StrFechaOrd.
	 *
	private String strFechaOrd = null;

	/**
	 * Obtener la propiedad strFechaOrd.
	 * 
	 *@return La propiedad StrFechaOrd.
	 *
	public String getStrFechaOrd() {
		return this.strFechaOrd;
	}

	/**
	 * Establecer la propiedad strFechaOrd.
	 * 
	 *@param strFechaOrd
	 *            Nueva propiedad strFechaOrd.
	 *
	public void setStrFechaOrd(String strFechaOrd) {
		this.strFechaOrd = strFechaOrd;
	}

	/**
	 * Propiedad Hora.
	 *
	private String hora = null;

	/**
	 * Obtener la propiedad hora.
	 * 
	 *@return La propiedad Hora.
	 *
	public String getHora() {
		return this.hora;
	}

	/**
	 * Establecer la propiedad hora.
	 * 
	 *@param hora
	 *            Nueva propiedad hora.
	 *
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * CtaPac property.
	 */
	private String ctaPac = null;

	/**
	 * Get ctaPac property.
	 * 
	 *@return CtaPac property.
	 */
	public String getCtaPac() {
		return this.ctaPac;
	}

	/**
	 * Set ctaPac property.
	 * 
	 *@param ctaPac
	 *            New ctaPac property.
	 */
	public void setCtaPac(String ctaPac) {
		this.ctaPac = ctaPac;
	}

	/**
	 * AlmacenId from appoint medical property.
	 */
	private long almacenIdCita;

	/**
	 * Get AlmacenId from appoint medical property.
	 * 
	 * @return AlmacenId from appoint medical property.
	 */
	public long getAlmacenIdCita() {
		return almacenIdCita;
	}

	/**
	 * Set AlmacenId from appoint medical property.
	 * 
	 * @param AlmacenId
	 *            New AlmacenId property.
	 */
	public void setAlmacenIdCita(long almacenIdCita) {
		this.almacenIdCita = almacenIdCita;
	}

	/**
	 * Almacen property.
	 */
	private String almacen = null;

	/**
	 * Get almacen property.
	 * 
	 *@return Almacen property.
	 */
	public String getAlmacen() {
		return this.almacen;
	}

	/**
	 * Set almacen property.
	 * 
	 *@param almacen
	 *            New almacen property.
	 */
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}

	/**
	 * Usuario property.
	 */
	private String usuario = null;

	/**
	 * Get usuario property.
	 * 
	 *@return Usuario property.
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * Set usuario property.
	 * 
	 *@param usuario
	 *            New usuario property.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Indicaciones property.
	 *
	private List<IndicacionView> Indicaciones = new ArrayList<IndicacionView>();
	/**
	 * Get Indicaciones property.
	 * 
	 *@return Indicaciones property.
	 *
	public List<IndicacionView> getIndicaciones() {
		return this.Indicaciones;
	}

	/**
	 * Set Indicaciones property.
	 * 
	 *@param Indicaciones
	 *            New Indicaciones property.
	 *
	public void setIndicaciones(List<IndicacionView> Indicaciones) {
		this.Indicaciones = Indicaciones;
	}

	/**
	 * Add Indicaciones property.
	 * 
	 *@param Indicaciones
	 *            New Indicaciones property.
	 *
	public void addIndicacion(IndicacionView Indicacion) {
		this.Indicaciones.add(Indicacion);
	}*/

	// Lista para guardar los estatus de alta de la cuenta paciente - altas y prealtas -Dreyser
	private String releaseStatus;

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	private String almacenSol = null;

	public String getAlmacenSol() {
		return almacenSol;
	}

	public void setAlmacenSol(String almacenSol) {
		this.almacenSol = almacenSol;
	}

	// EXPERT: Inicio LRHU: Para obtener el numero de solicitud de servicio y la fecha de promesa
//	private String documentNo = null;
	private java.util.Date fechaProm = null;
	private String strFechaProm = null;

//	public String getDocumentNo() {
//		return documentNo;
//	}
//
//	public void setDocumentNo(String documentNo) {
//		this.documentNo = documentNo;
//	}

	public java.util.Date getFechaProm() {
		return fechaProm;
	}

	public void setFechaProm(java.util.Date fechaProm) {
		this.fechaProm = fechaProm;
	}

	public String getStrFechaProm() {
		return strFechaProm;
	}

	public void setStrFechaProm(String strFechaProm) {
		this.strFechaProm = strFechaProm;
	}

	// Fin LRHU;

	// Miguel Loera: se guarda expediente
//	private String expediente = null;

//	public String getExpediente() {
//		return expediente;
//	}
//
//	public void setExpediente(String expediente) {
//		this.expediente = expediente;
//	}

	//Interconsulta: Estatus del documento (confirmacion de sol.) .- LAMA
	private String estatusDoc = null;

	public String getEstatusDoc() {
		return estatusDoc;
	}

	//HL7 Interfaz : Estudios de Laboratorio
	private boolean  containsOBX = false;
	
	public boolean isContainsOBX() {
		return containsOBX;
	}

	public void setContainsOBX(boolean containsOBX) {
		this.containsOBX = containsOBX;
	}	
	//FIN HL7 Interfaz
	
	public void setEstatusDoc(String estatusDoc) {
		this.estatusDoc = estatusDoc;
	}
	
	private int parentID = 0;

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
//	private int pacienteID = 0;
//
//	public int getPacienteID() {
//		return pacienteID;
//	}
//
//	public void setPacienteID(int pacienteID) {
//		this.pacienteID = pacienteID;
//	}
	
	// Lama: usar modelo
	private MEXMEActPacienteIndH model ;

	public MEXMEActPacienteIndH getModel(Properties ctx) {
		final long headerID = indHID == null ? xXActPacienteIndHID <= 0 ? 0 : xXActPacienteIndHID : Long.valueOf(indHID);
		if(model == null && headerID > 0){
			model = new MEXMEActPacienteIndH(ctx, (int)headerID, null);
		}
		return model;
	}
	
	public MEXMEActPacienteIndH getModel() {
		return model;
	}

	public void setModel(MEXMEActPacienteIndH model) {
		this.model = model;
	}
	
	private int referralID = 0;

	public int getReferralID() {
		return referralID;
	}

	public void setReferralID(int referralID) {
		this.referralID = referralID;
	}	
	
	private String mrn = null;
	
	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}
	
	private int productID = 0;
	

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	/**
	 * Valores del modelo padre
	 * @param model
	 */
	public void setValues(MEXMEActPacienteIndH model){

		setModel(model);
		setDocumentNo(model.getDocumentNo());
		setEstatusDoc(model.getDocStatus()); // LAMA
		setFechaOrd(model.getDateOrdered());
		setDescription(model.getDescription());
		setParentID(model.getParent_ID());
		setStrFechaProm(Constantes.getSDFDateTime(model.getCtx()).formatConvert(model.getDatePromised()));
//		setHora(Constantes.getSdfHora24(model.getCtx()).formatConvert(model.getDateOrdered()));

		setXXActPacienteID(model.getEXME_ActPaciente_ID());
		setXXActPacienteIndHID(model.getEXME_ActPacienteIndH_ID());
		setXXCtaPacID(model.getEXME_CtaPac_ID());
		setMWarehouseID(model.getM_Warehouse_ID());

		setIndHID(Long.toString(model.getEXME_ActPacienteIndH_ID()));
		
		String estado = "";
		// Encontrar el nombre del estatus del documento
		if (StringUtils.isNotBlank(model.getDocStatus())) {
			estado = MRefList.getListName(model.getCtx(), 
					X_EXME_ActPacienteIndH.DOCSTATUS_AD_Reference_ID, 
					model.getDocStatus().trim());
		}
		setDocStatus(estado);
	}
	
	/**
	 * Valores del result set
	 * @param rs
	 */
	public void setValues(ResultSet rs, int liga){
		try {
//			setEspecialidad(rs.getString("especialidad"));
			setPaciente(SecureEngine.decrypt(rs.getString("paciente")));
//			setNoHistoria(rs.getString("value"));
//			setPacienteID(rs.getInt("EXME_Paciente_ID"));// Lama
//			setEsAsegurado(rs.getString("esAsegurado"));
//			setTipoDoc(rs.getString("documentType"));

//			setStrFechaOrd(rs.getString("fechaOrd"));
			setCtaPac(rs.getString("ctapac"));
			setAlmacenSol(rs.getString("almacenSol"));
			
			if(liga<=1){
				setAlmacen(rs.getString("almacen"));//red
				setUsuario(rs.getString("user_name"));//red
				setContainsOBX(rs.getInt("obxCount") > 0);//red
			}
		} catch (SQLException e) {
			 s_log.log(Level.SEVERE,"setValues", e);
		}
	}

	@Override
	public Object onCompare(int type) {
		Object comparable;
		if (model != null) {
			if (type == MEXMEActPacienteInd.COLUMNNAME_M_Product_ID.hashCode()) {
				comparable = model.getDetailStr(false);
			} else if (type == MEXMEActPacienteIndH.COLUMNNAME_PriorityRule.hashCode()) {
				comparable = model.getPriorityRule();
			} else if (type == MEXMEActPacienteIndH.COLUMNNAME_M_Warehouse_Sol_ID.hashCode()) {
				comparable = getAlmacenSol();
			} else if (type == MEXMEHistExp.COLUMNNAME_DocumentNo.hashCode()) {
				comparable = model.getPaciente() == null ? "" : model.getPaciente().getHistExpediente().getDocumentNo();
			} else if (type == MEXMEActPacienteIndH.COLUMNNAME_DocumentNo.hashCode()) {
				comparable = model.getCtaPac() == null ? " " : model.getCtaPac().getDocumentNo();
			} else if (type == MEXMEPaciente.COLUMNNAME_Nombre_Pac.hashCode()) {
				comparable = model.getPaciente() == null ? "" : model.getPaciente().getNombre_Pac();
			} else if (type == MEXMEActPacienteIndH.COLUMNNAME_EXME_MedicoSol_ID.hashCode()) {
				comparable = MEXMEMedico.nombreMedico(model.getCtx(), model.getEXME_MedicoSol_ID());
			} else {
				comparable = model.onCompare(type);
			}
		} else {
			comparable = "";
		}
		return comparable;
	}
}