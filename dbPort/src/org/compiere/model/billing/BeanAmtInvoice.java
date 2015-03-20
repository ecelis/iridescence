package org.compiere.model.billing;

import java.math.BigDecimal;

import org.compiere.util.Env;

public class BeanAmtInvoice {

	/** Total de la suma de los cargos  */
	private BigDecimal totalVta = Env.ZERO;
	/** Total de la suma de los cargos con descuentos */
	private BigDecimal total = Env.ZERO;
	/** Despues de descuento */
	private BigDecimal subtotal = Env.ZERO;
	/** Impuestos */
	private BigDecimal impuesto = Env.ZERO;
	/** Total con impuestos */
	private BigDecimal grantotal = Env.ZERO;
	/** Subtotal con deducible */
	private BigDecimal subtotalD = Env.ZERO;
	/** Subtotal con coaseguro */
	private BigDecimal subtotalC = Env.ZERO;
	/** Subtotal con coaseguro medico */
	private BigDecimal subtotalDm = Env.ZERO;
	/** Subtotal con copago */
	private BigDecimal subtotalCo = Env.ZERO;
	/** Anticipos */
	private BigDecimal anticipos = Env.ZERO;
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getAnticipos() {
		return anticipos;
	}
	public void setAnticipos(BigDecimal anticipos) {
		this.anticipos = anticipos;
	}
	public BigDecimal getTotalVta() {
		return totalVta;
	}
	public void setTotalVta(BigDecimal totalVta) {
		this.totalVta = totalVta;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}
	public BigDecimal getGrantotal() {
		return grantotal;
	}
	public void setGrantotal(BigDecimal grantotal) {
		this.grantotal = grantotal;
	}
	public BigDecimal getSubtotalD() {
		return subtotalD;
	}
	public void setSubtotalD(BigDecimal subtotalD) {
		this.subtotalD = subtotalD;
	}
	public BigDecimal getSubtotalC() {
		return subtotalC;
	}
	public void setSubtotalC(BigDecimal subtotalC) {
		this.subtotalC = subtotalC;
	}
	public BigDecimal getSubtotalDm() {
		return subtotalDm;
	}
	public void setSubtotalDm(BigDecimal subtotalDm) {
		this.subtotalDm = subtotalDm;
	}
	public BigDecimal getSubtotalCo() {
		return subtotalCo;
	}
	public void setSubtotalCo(BigDecimal subtotalCo) {
		this.subtotalCo = subtotalCo;
	}
}
