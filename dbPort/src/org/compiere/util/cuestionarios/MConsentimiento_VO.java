package org.compiere.util.cuestionarios;

import java.util.Properties;

import org.compiere.model.MEXMEPaciente;

/** @deprecated will be removed */
public class MConsentimiento_VO {
	
	private int consentimientoID = 0;
	private String usuario = null;
	private String fechaCreacion = null;
	private String name = null;
	private MEXMEPaciente paciente = null ;
	private int ctaPacID = 0;
	private Properties ctx = null;
	public int getConsentimientoID() {
		return consentimientoID;
	}
	public void setConsentimientoID(int consentimientoID) {
		this.consentimientoID = consentimientoID;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public MEXMEPaciente getPaciente() {
		return paciente;
	}
	public void setPaciente(MEXMEPaciente paciente) {
		this.paciente = paciente;
	}
	public int getCtaPacID() {
		return ctaPacID;
	}
	public void setCtaPacID(int ctaPacID) {
		this.ctaPacID = ctaPacID;
	}
	public Properties getCtx() {
		return ctx;
	}
	public void setCtx(Properties ctx) {
		this.ctx = ctx;
	}

}
