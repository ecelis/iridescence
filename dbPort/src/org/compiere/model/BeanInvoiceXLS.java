package org.compiere.model;

import java.math.BigDecimal;

public class BeanInvoiceXLS {
	
	private String ctaPac;
	private String valueCte;
	private String nameCte;
	private java.sql.Timestamp ingreso;
	private java.sql.Timestamp egreso;
	private String pacienteName;
	private String medicoName;
	private String detalle;
	private String facturaNo;
	private java.sql.Timestamp fechaFac;
	private BigDecimal importe;
	private BigDecimal iva;
	private BigDecimal total;
	private String status;
	private java.sql.Timestamp ingresoQuir;
	private java.sql.Timestamp egresoQuir;
	private String estIngreso;
	private BigDecimal duracion;
	private BigDecimal xRay;
	private BigDecimal lab;
	
	
	public BigDecimal getxRay() {
		return xRay;
	}
	public void setxRay(BigDecimal xRay) {
		this.xRay = xRay;
	}
	public BigDecimal getLab() {
		return lab;
	}
	public void setLab(BigDecimal lab) {
		this.lab = lab;
	}
	public BigDecimal getDuracion() {
		return duracion;
	}
	public void setDuracion(BigDecimal duracion) {
		this.duracion = duracion;
	}
	public String getEstIngreso() {
		return estIngreso;
	}
	public void setEstIngreso(String estIngreso) {
		this.estIngreso = estIngreso;
	}
	public String getCtaPac() {
		return ctaPac;
	}
	public void setCtaPac(String ctaPac) {
		this.ctaPac = ctaPac;
	}
	public String getValueCte() {
		return valueCte;
	}
	public void setValueCte(String valueCte) {
		this.valueCte = valueCte;
	}
	public String getNameCte() {
		return nameCte;
	}
	public void setNameCte(String nameCte) {
		this.nameCte = nameCte;
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
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getFacturaNo() {
		return facturaNo;
	}
	public void setFacturaNo(String facturaNo) {
		this.facturaNo = facturaNo;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getIva() {
		return iva;
	}
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.sql.Timestamp getIngreso() {
		return ingreso;
	}
	public void setIngreso(java.sql.Timestamp ingreso) {
		this.ingreso = ingreso;
	}
	public java.sql.Timestamp getEgreso() {
		return egreso;
	}
	public void setEgreso(java.sql.Timestamp egreso) {
		this.egreso = egreso;
	}
	public java.sql.Timestamp getFechaFac() {
		return fechaFac;
	}
	public void setFechaFac(java.sql.Timestamp fechaFac) {
		this.fechaFac = fechaFac;
	}
	public java.sql.Timestamp getIngresoQuir() {
		return ingresoQuir;
	}
	public void setIngresoQuir(java.sql.Timestamp ingresoQuir) {
		this.ingresoQuir = ingresoQuir;
	}
	public java.sql.Timestamp getEgresoQuir() {
		return egresoQuir;
	}
	public void setEgresoQuir(java.sql.Timestamp egresoQuir) {
		this.egresoQuir = egresoQuir;
	}
			
}
