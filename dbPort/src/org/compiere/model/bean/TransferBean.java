/**
 * 
 */
package org.compiere.model.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.Env;

/**
 * Datos necesarios para la Recepcion de Material
 * y orden de compra
 * @author twry
 *
 */
public class TransferBean {
	private int productID = 0;
	private int attributeSetInsID = 0;
	private int uomID = 0;
	private BigDecimal qtyEntered = Env.ZERO;
	private BigDecimal qtyOrdered = Env.ZERO;
	private String description = StringUtils.EMPTY;
	private Timestamp datePromised = new Timestamp(System.currentTimeMillis());
	
	
	private BigDecimal qtyEnteredVol = Env.ZERO;
	private BigDecimal qtyOrderedVol = Env.ZERO;
	private int uomVolumeID = 0;
	
	/** Los precios son dato obligatorio */
	private BigDecimal priceActualVol = Env.ZERO;
	private BigDecimal priceListVol = Env.ZERO;
	private BigDecimal priceEnteredVol = Env.ZERO;
	private BigDecimal priceActual = Env.ZERO;
	private BigDecimal priceList = Env.ZERO;
	private BigDecimal priceEntered = Env.ZERO;
	private BigDecimal priceLimit = Env.ZERO;
	
	private BigDecimal discount = Env.ZERO;
	private int cTaxId = 0;
	private int inOutLineId =0;
	
	public int getInOutLine() {
		return inOutLineId;
	}
	public void setInOutLine(int mInOutLine) {
		this.inOutLineId = mInOutLine;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public int getcTaxId() {
		return cTaxId;
	}
	public void setcTaxId(int cTaxId) {
		this.cTaxId = cTaxId;
	}
	public BigDecimal getPriceLimit() {
		return priceLimit;
	}
	public void setPriceLimit(BigDecimal priceLimit) {
		this.priceLimit = priceLimit;
	}
	public BigDecimal getPriceActual() {
		return priceActual;
	}
	public void setPriceActual(BigDecimal priceActual) {
		this.priceActual = priceActual;
	}
	public BigDecimal getPriceList() {
		return priceList;
	}
	public void setPriceList(BigDecimal priceList) {
		this.priceList = priceList;
	}
	public BigDecimal getPriceEntered() {
		return priceEntered;
	}
	public void setPriceEntered(BigDecimal priceEntered) {
		this.priceEntered = priceEntered;
	}
	public BigDecimal getPriceActual_Vol() {
		return priceActualVol;
	}
	public void setPriceActual_Vol(BigDecimal priceActual_Vol) {
		priceActualVol = priceActual_Vol;
	}
	public BigDecimal getPriceList_Vol() {
		return priceListVol;
	}
	public void setPriceList_Vol(BigDecimal priceList_Vol) {
		priceListVol = priceList_Vol;
	}
	public BigDecimal getPriceEntered_Vol() {
		return priceEnteredVol;
	}
	public void setPriceEntered_Vol(BigDecimal priceEntered_Vol) {
		priceEnteredVol = priceEntered_Vol;
	}
	public BigDecimal getQtyEntered_Vol() {
		return qtyEnteredVol;
	}
	public void setQtyEntered_Vol(BigDecimal qtyEntered_Vol) {
		qtyEnteredVol = qtyEntered_Vol;
	}
	public BigDecimal getQtyOrdered_Vol() {
		return qtyOrderedVol;
	}
	public void setQtyOrdered_Vol(BigDecimal qtyOrdered_Vol) {
		qtyOrderedVol = qtyOrdered_Vol;
	}
	public int getC_UOMVolume_ID() {
		return uomVolumeID;
	}
	public void setC_UOMVolume_ID(int c_UOMVolume_ID) {
		uomVolumeID = c_UOMVolume_ID;
	}
	public int getM_Product_ID() {
		return productID;
	}
	public void setM_Product_ID(int m_Product_ID) {
		productID = m_Product_ID;
	}
	public int getM_AttributeSetInstance_ID() {
		return attributeSetInsID;
	}
	public void setM_AttributeSetInstance_ID(int m_AttributeSetInstance_ID) {
		attributeSetInsID = m_AttributeSetInstance_ID;
	}
	public int getC_UOM_ID() {
		return uomID;
	}
	public void setC_UOM_ID(int c_UOM_ID) {
		uomID = c_UOM_ID;
	}
	public BigDecimal getQtyEntered() {
		return qtyEntered;
	}
	public void setQtyEntered(BigDecimal qty_Entered) {
		qtyEntered = qty_Entered;
	}
	public BigDecimal getQtyOrdered() {
		return qtyOrdered;
	}
	public void setQtyOrdered(BigDecimal qty_Ordered) {
		qtyOrdered = qty_Ordered;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String descrip) {
		description = descrip;
	}
	public Timestamp getDatePromised() {
		return datePromised;
	}
	public void setDatePromised(Timestamp date_Promised) {
		datePromised = date_Promised;
	}
}
