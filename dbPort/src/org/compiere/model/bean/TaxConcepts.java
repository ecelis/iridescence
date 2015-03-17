/**
 * 
 */
package org.compiere.model.bean;

import java.math.BigDecimal;

import org.compiere.util.Env;

import com.google.gson.Gson;

/**
 * @author tperez
 *
 */
public class TaxConcepts {

	/** Constructor */
	public TaxConcepts() {
		super();
	}
	
	private int tasa = 0;
	private BigDecimal base = Env.ZERO;
	private BigDecimal impuesto = Env.ZERO;
	
	public int getTasa() {
		return tasa;
	}
	public void setTasa(int tasa) {
		this.tasa = tasa;
	}
	public BigDecimal getBase() {
		return base;
	}
	public void setBase(BigDecimal base) {
		this.base = base;
	}
	public BigDecimal getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}
	
//	/**
//	 * Serializar objeto a Json para exportar
//	 * Gson gson = new Gson(); // Or use new GsonBuilder().create();
//	 * MyType target = new MyType();
//	 * String json = gson.toJson(target); // serializes target to Json
//	 * MyType target2 = gson.fromJson(json, MyType.class); // deserializes json into target2
//	 * @return Representación Json
//	 */
//	public String toJson() {
//		return new Gson().toJson(this);
//	}
}