package org.compiere.model;

/**
 * @author Noelia
 * Vista creada para Opciones de Enfermeria TouchScreen
 */
public class CuentaPacTSView {

	public int ctaPacID = 0;
	public String habitacion = null;
	public String cama = null;
	public String historia= null;
	public String dateOrdered = null;
	public String nombrePac = null;
	public String nombreMed = null;
	public String diagnosticoIngreso = null;
	public String dieta = null;
	public String statusAlta = null;
	public String expediente = null;
	public String documentNo = null;
	
	public String getCama() {
		return cama;
	}
	public void setCama(String cama) {
		this.cama = cama;
	}
	public int getCtaPacID() {
		return ctaPacID;
	}
	public void setCtaPacID(int ctaPacID) {
		this.ctaPacID = ctaPacID;
	}
	public String getDateOrdered() {
		return dateOrdered;
	}
	public void setDateOrdered(String dateOrdered) {
		this.dateOrdered = dateOrdered;
	}
	public String getDiagnosticoIngreso() {
		return diagnosticoIngreso;
	}
	public void setDiagnosticoIngreso(String diagnosticoIngreso) {
		this.diagnosticoIngreso = diagnosticoIngreso;
	}
	public String getDieta() {
		return dieta;
	}
	public void setDieta(String dieta) {
		this.dieta = dieta;
	}
	public String getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	public String getNombreMed() {
		return nombreMed;
	}
	public void setNombreMed(String nombreMed) {
		this.nombreMed = nombreMed;
	}
	public String getNombrePac() {
		return nombrePac;
	}
	public void setNombrePac(String nombrePac) {
		this.nombrePac = nombrePac;
	}
	public String getStatusAlta() {
		return statusAlta;
	}
	public void setStatusAlta(String statusAlta) {
		this.statusAlta = statusAlta;
	}
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
}
