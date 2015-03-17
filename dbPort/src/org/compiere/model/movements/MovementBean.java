package org.compiere.model.movements;

import org.apache.commons.lang.StringUtils;

/**
 * Bean para mostrar la información de la confirmación de un movimiento
 * @author twry
 *
 */
public class MovementBean {
	/** Numero de documento de la cuenta paciente */
	private String ctaPacDocumentNo = StringUtils.EMPTY;
	/** Nombre del quirofano */
	private String quirofanoName = StringUtils.EMPTY;
	/** nombre del medico */
	private String medicoNombreMed = StringUtils.EMPTY;
	/** Nombre del usuario que solicita */
	private String soliUserName = StringUtils.EMPTY;
	/** Nombre del usuario que confirma */
	private String surtUserName = StringUtils.EMPTY;
	/** Fecha del movimiento o de la programación si existe */
	private String dateMovProg = StringUtils.EMPTY;
	/** Nombre del almacen */
	private String soliWarehouseName = StringUtils.EMPTY;
	/** Nombre del almacen */
	private String surtWarehouseName = StringUtils.EMPTY;
	
	public String getSoliWarehouseName() {
		return soliWarehouseName;
	}
	public void setSoliWarehouseName(String soliWarehouseName) {
		this.soliWarehouseName = soliWarehouseName;
	}
	public String getSurtWarehouseName() {
		return surtWarehouseName;
	}
	public void setSurtWarehouseName(String surtWarehouseName) {
		this.surtWarehouseName = surtWarehouseName;
	}
	public String getDateMovProg () {
		return dateMovProg;
	}
	public void setDateMovProg (String fecha) {
		this.dateMovProg = fecha;
	}
	public String getCtaPacDocumentNo() {
		return ctaPacDocumentNo;
	}
	public void setCtaPacDocumentNo(final String ctapac) {
		this.ctaPacDocumentNo = ctapac;
	}

	public String getQuirofanoName() {
		return quirofanoName;
	}
	public void setQuirofanoName(final String value) {
		this.quirofanoName = value;
	}
	
	public String getMedicoNombreMed() {
		return medicoNombreMed;
	}
	public void setMedicoNombreMed(final String value) {
		this.medicoNombreMed = value;
	}
	
	public String getSoliUserName() {
		return soliUserName;
	}
	public void setSoliUserName(final String value) {
		this.soliUserName = value;
	}
	
	public String getSurtUserName() {
		return surtUserName;
	}
	public void setSurtUserName(final String value) {
		this.surtUserName = value;
	}
}
