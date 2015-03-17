package org.compiere.util.directives;

import java.sql.Timestamp;

public class MConsentimientoPac_VO {

	private int consentimientoId;
	private Timestamp fecha;
	private Timestamp fechaDoc;
	private String comentarios;
	private boolean estatus;
	
	public int getConsentimientoId() {
		
		return consentimientoId;
		
	}

	public void setConsentimientoId(int consentimientoId) {
		
		this.consentimientoId = consentimientoId;
		
	}

	public Timestamp getFecha() {
		
		return fecha;
		
	}
	
	public void setFecha(Timestamp fecha) {
		
		this.fecha = fecha;
		
	}
	
	
	public Timestamp getFechaDoc() {
		
		return fechaDoc;
		
	}

	public void setFechaDoc(Timestamp fechaDoc) {
		
		this.fechaDoc = fechaDoc;
		
	}

	public String getComentarios() {
		
		return comentarios;
		
	}

	public void setComentarios(String comentarios) {
		
		this.comentarios = comentarios;
		
	}

	public boolean getEstatus() {
		
		return estatus;
		
	}
	
	public void setEstatus(boolean estatus) {
		
		this.estatus = estatus;
		
	}
	
}
