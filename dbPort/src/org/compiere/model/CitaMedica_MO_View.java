package org.compiere.model;

/**
 * Vista Para las citas medicas
 * Odontologia<p>
 *  
 * Creado:15/Junio/2009<p>
 * @author Lizeth de la Garza
 * 
 */

public class CitaMedica_MO_View {	
	
	private int citaMedicaID = 0;
	private String fechaProgramada = null;
	private String horaProgramada = null;
	private String fechaIniCita = null;
	private String horaIniCita = null;
	private String fechaFinCita = null;
	private String horaFinCita = null;
	private String medico = null;
	private String paciente = null;
	private String estatus = null;
	private int pacienteID = 0;
	
	public int getCitaMedicaID() {
		return citaMedicaID;
	}
	public void setCitaMedicaID(int citaMedicaID) {
		this.citaMedicaID = citaMedicaID;
	}
	public String getFechaProgramada() {
		return fechaProgramada;
	}
	public void setFechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}
	public String getHoraProgramada() {
		return horaProgramada;
	}
	public void setHoraProgramada(String horaProgramada) {
		this.horaProgramada = horaProgramada;
	}
	public String getFechaIniCita() {
		return fechaIniCita;
	}
	public void setFechaIniCita(String fechaIniCita) {
		this.fechaIniCita = fechaIniCita;
	}
	public String getHoraIniCita() {
		return horaIniCita;
	}
	public void setHoraIniCita(String horaIniCita) {
		this.horaIniCita = horaIniCita;
	}
	public String getFechaFinCita() {
		return fechaFinCita;
	}
	public void setFechaFinCita(String fechaFinCita) {
		this.fechaFinCita = fechaFinCita;
	}
	public String getHoraFinCita() {
		return horaFinCita;
	}
	public void setHoraFinCita(String horaFinCita) {
		this.horaFinCita = horaFinCita;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	public int getPacienteID() {
		return pacienteID;
	}
	public void setPacienteID(int pacienteID) {
		this.pacienteID = pacienteID;
	} 


}
