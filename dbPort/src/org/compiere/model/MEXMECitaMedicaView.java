package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.apache.struts.util.LabelValueBean;
import org.compiere.util.CLogger;


public class MEXMECitaMedicaView extends MEXMECitaMedica {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	@SuppressWarnings("unused")
	private static CLogger s_log = CLogger.getCLogger(MEXMECitaMedicaView.class);

	public MEXMECitaMedicaView(Properties ctx, int EXME_CitaMedica_ID, String trxName) {
		super(ctx, EXME_CitaMedica_ID, trxName);
	}

	public MEXMECitaMedicaView(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
    private Integer invoice = null;
	private String editable = null;
	private String historia = null;
	private String hora = null;
	private String minutos = null;
	private String nombre = null;
	private String sexo = null;
	private String telefono = null;
	private String usuario = null;
	private String stringEdadAMD = null;
	private String expediente = null;
	private String citaDeName = null;
	private String consultorio = null;
	private String organizacion = null;
	private String medicoValue = null;
	private String medicoName = null;
	private String pacName = null;
	private String pacName2 = null;
	private String pacApellido1 = null;
	private String pacApellido2 = null;
	private String pacApellido3 = null;
	private Timestamp fechaNac = null;
	private String horaConfirm = null;
	private String minConfirm = null;
	private String ctaPac = null;
		
	
	// Referencia  .- Lama
	private String referencia;
	//Liz de la Garza- La hora actual sea menor que la hora agendada
	private boolean isBeforeCita = true;
	
	// para ejecucion de citas
	private List<LabelValueBean> lstDiagnostico = null;
	private String especialidadName = null;
	
	public String getHoraConfirm() {
		return horaConfirm;
	}

	public void setHoraConfirm(String horaConfirm) {
		this.horaConfirm = horaConfirm;
	}

	public String getMinConfirm() {
		return minConfirm;
	}

	public void setMinConfirm(String minConfirm) {
		this.minConfirm = minConfirm;
	}

	public String getCitaDeName() {
		return citaDeName;
	}

	public void setCitaDeName(String citaDeName) {
		this.citaDeName = citaDeName;
	}

	public String getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(String consultorio) {
		this.consultorio = consultorio;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public boolean isBeforeCita() {
		return isBeforeCita;
	}

	public void setBeforeCita(boolean isBeforeCita) {
		this.isBeforeCita = isBeforeCita;
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

	public String getMinutos() {
		return minutos;
	}

	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getPacApellido1() {
		return pacApellido1;
	}

	public void setPacApellido1(String pacApellido1) {
		this.pacApellido1 = pacApellido1;
	}

	public String getPacApellido2() {
		return pacApellido2;
	}

	public void setPacApellido2(String pacApellido2) {
		this.pacApellido2 = pacApellido2;
	}
	
	public String getPacApellido3() {
		return pacApellido3;
	}

	public void setPacApellido3(String pacApellido3) {
		this.pacApellido3 = pacApellido3;
	}

	public String getPacName() {
		return pacName;
	}

	public void setPacName(String pacName) {
		this.pacName = pacName;
	}

	public String getPacName2() {
		return pacName2;
	}

	public void setPacName2(String pacName2) {
		this.pacName2 = pacName2;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getStringEdadAMD() {
		return stringEdadAMD;
	}

	public void setStringEdadAMD(String stringEdadAMD) {
		this.stringEdadAMD = stringEdadAMD;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Timestamp getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Timestamp fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Integer getInvoice() {
		return invoice;
	}

	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}

	public List<LabelValueBean> getLstDiagnostico() {
		return lstDiagnostico;
	}

	public void setLstDiagnostico(List<LabelValueBean> lstDiagnostico) {
		this.lstDiagnostico = lstDiagnostico;
	}

	public String getEspecialidadName() {
		return especialidadName;
	}

	public void setEspecialidadName(String especialidadName) {
		this.especialidadName = especialidadName;
	}

	public void setCtaPac(String ctaPac) {
		this.ctaPac = ctaPac;
	}

	public String getCtaPac() {
		return ctaPac;
	}

	
	
}
