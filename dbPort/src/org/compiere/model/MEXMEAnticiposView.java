package org.compiere.model;

import java.math.BigDecimal;

import org.compiere.util.Env;

public class MEXMEAnticiposView {

	private String recibo = null;
	private int extension = 0;
	private int extensionId = 0;
	private String formaPago = null;
	private BigDecimal monto = Env.ZERO;
	private boolean seleccionado =  false;
	
	
	public String getRecibo() {
		return recibo;
	}
	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}
	public int getExtension() {
		return extension;
	}
	public void setExtension(int extension) {
		this.extension = extension;
	}
	public int getExtensionId() {
		return extensionId;
	}
	public void setExtensionId(int extensionId) {
		this.extensionId = extensionId;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public boolean getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	
}
