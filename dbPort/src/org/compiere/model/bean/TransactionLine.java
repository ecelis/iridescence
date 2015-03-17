/**
 * 
 */
package org.compiere.model.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author uriel
 * Clase que se utiliza para sacar el reporte de existencias
 * card #1407
 */
public class TransactionLine {

	private String locatorName;
	private String prodName;
	private String prodValue;
	private BigDecimal qty;
	private BigDecimal qtyVol;
	private String udm;
	private String udmVol;
	private String wareHouse;
	private String lotName;
	private Timestamp guaranteeDate;

	public TransactionLine() {
	}

	public String getLocatorName() {
		return locatorName;
	}

	public String getProdName() {
		return prodName;
	}

	public String getProdValue() {
		return prodValue;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public BigDecimal getQtyVol() {
		return qtyVol;
	}

	public String getUdm() {
		return udm;
	}

	public String getUdmVol() {
		return udmVol;
	}

	public String getWareHouse() {
		return wareHouse;
	}

	public String getLotName() {
		return lotName;
	}

	public Timestamp getGuaranteeDate() {
		return guaranteeDate;
	}

	public void setLocatorName(String locatorName) {
		this.locatorName = locatorName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public void setProdValue(String prodValue) {
		this.prodValue = prodValue;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public void setQtyVol(BigDecimal qtyVol) {
		this.qtyVol = qtyVol;
	}

	public void setUdm(String udm) {
		this.udm = udm;
	}

	public void setUdmVol(String udmVol) {
		this.udmVol = udmVol;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public void setLotName(String lotName) {
		this.lotName = lotName;
	}

	public void setGuaranteeDate(Timestamp guaranteeDate) {
		this.guaranteeDate = guaranteeDate;
	}

}
