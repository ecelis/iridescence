package org.compiere.util.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MFormaPago;
import org.compiere.model.MInvoice;
import org.compiere.util.Env;

public class CreditNoteDet {
	
	private Timestamp fecha;
	private BigDecimal monto = BigDecimal.ZERO;
	private MFormaPago pago;
	private int ncID;
	private MInvoice nc;
	private int indice;
	private BigDecimal pendiente = BigDecimal.ZERO;
	private String notaNo;
	private String facturaNo;
	private String cliente;
	private String rfc;
	private String pacName;
	private int notaCargoID;

	public int getNotaCargoID() {
		return notaCargoID;
	}

	public void setNotaCargoID(int notaCargoID) {
		this.notaCargoID = notaCargoID;
	}

	public String getPacName() {
		return pacName;
	}

	public void setPacName(String pacName) {
		this.pacName = pacName;
	}

	public String getNotaNo() {
		return notaNo;
	}

	public void setNotaNo(String notaNo) {
		this.notaNo = notaNo;
	}

	public String getFacturaNo() {
		return facturaNo;
	}

	public void setFacturaNo(String facturaNo) {
		this.facturaNo = facturaNo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public CreditNoteDet(){}
	
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public MFormaPago getPago() {
		return pago;
	}
	public void setPago(MFormaPago pago) {
		this.pago = pago;
	}

	public int getNcID() {
		return ncID;
	}
	
	public MInvoice getNc() {
		if(nc == null && ncID > 0){
			nc = new MInvoice(Env.getCtx(), ncID, null);
		}
		return nc;
	}

	public void setNcID(int ncID) {
		this.ncID = ncID;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public BigDecimal getPendiente() {
		return pendiente;
	}

	public void setPendiente(BigDecimal pendiente) {
		this.pendiente = pendiente;
	}

}
