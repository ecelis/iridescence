package org.compiere.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.compiere.util.Env;


public class MEXMEDescImpView implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Impuesto
	 */
	private int tax_ID = 0;
	
	/**
	 * Base Gravable
	 */
	private BigDecimal base = Env.ZERO;
	
	/**
	 * Descuento correspondiente a la base gravable
	 */
	private BigDecimal prorrateo = Env.ZERO;
	
	/**
	 * Base gravable menos descuento
	 */
	private BigDecimal newBase = Env.ZERO;
	
	/**
	 * Impuesto correspondiente a la nueva base
	 */
	private BigDecimal iva = Env.ZERO;
	
	/**
	 * Nueva base mas impuesto
	 */
	private BigDecimal newTotal = Env.ZERO;
	
	/**
	 * Impuesto correspondiente al descuento
	 */
	private BigDecimal ivaProrrateo = Env.ZERO;
	
	/**
	 * Cargo de captura 
	 */
	private int charge_ID = 0; 
	
	/**
	 * Esquema de descuento (convenio)
	 */
	private int esqDesLine_ID = 0;
	
	/**
	 * Tasa de impuesto 
	 */
	private BigDecimal rate = Env.ZERO;
	
	/**
	 * Nueva base mas impuesto
	 */
	private BigDecimal newTotalProrrateo = Env.ZERO;
	
	public BigDecimal getBase() {
		return base;
	}
	public void setBase(BigDecimal base) {
		this.base = base;
	}
	
	public BigDecimal getProrrateo() {
		return prorrateo;
	}
	public void setProrrateo(BigDecimal prorrateo) {
		this.prorrateo = prorrateo;
	}
	public BigDecimal getIva() {
		return iva;
	}
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	public BigDecimal getNewBase() {
		return newBase;
	}
	public void setNewBase(BigDecimal newBase) {
		this.newBase = newBase;
	}
	public BigDecimal getNewTotal() {
		return newTotal;
	}
	public void setNewTotal(BigDecimal newTotal) {
		this.newTotal = newTotal;
	}
	public int getTax_ID() {
		return tax_ID;
	}
	public void setTax_ID(int tax_ID) {
		this.tax_ID = tax_ID;
	}
	public BigDecimal getIvaProrrateo() {
		return ivaProrrateo;
	}
	public void setIvaProrrateo(BigDecimal ivaProrrateo) {
		this.ivaProrrateo = ivaProrrateo;
	}
	public int getCharge_ID() {
		return charge_ID;
	}
	public void setCharge_ID(int charge_ID) {
		this.charge_ID = charge_ID;
	}
	public int getEsqDesLine_ID() {
		return esqDesLine_ID;
	}
	public void setEsqDesLine_ID(int esqDesLine_ID) {
		this.esqDesLine_ID = esqDesLine_ID;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getNewTotalProrrateo() {
		return newTotalProrrateo;
	}
	public void setNewTotalProrrateo(BigDecimal newTotalProrrateo) {
		this.newTotalProrrateo = newTotalProrrateo;
	}
	
	
}