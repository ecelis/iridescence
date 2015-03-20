package org.compiere.model.bean;

import java.math.BigDecimal;

/**
 * @author jonathan
 * 
 */
public class StorageInfo {
	private String locator;
	private String lot;
	private String name;
	private BigDecimal onHand;
	private BigDecimal ordered;
	private int productId;
	private BigDecimal qtyAvailableMin;
	private BigDecimal qtyAvailableVol;
	private BigDecimal qtyOnHandMin;
	private BigDecimal qtyOnHandVol;
	private BigDecimal qtyRequestedMin;
	private BigDecimal qtyRequestedVol;
	private BigDecimal qtyReservedMin;
	private BigDecimal qtyReservedVol;
	private BigDecimal reserved;
	private String value;
	private String warehouse;

	public StorageInfo() {

	}

	public String getLocator() {
		return locator;
	}

	public String getLot() {
		return lot;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getOnHand() {
		return onHand;
	}

	public BigDecimal getOrdered() {
		return ordered;
	}

	public int getProductId() {
		return productId;
	}

	public BigDecimal getQtyAvailableMin() {
		return qtyAvailableMin;
	}

	public BigDecimal getQtyAvailableVol() {
		return qtyAvailableVol;
	}

	public BigDecimal getQtyOnHandMin() {
		return qtyOnHandMin;
	}

	public BigDecimal getQtyOnHandVol() {
		return qtyOnHandVol;
	}

	public BigDecimal getQtyRequestedMin() {
		return qtyRequestedMin;
	}

	public BigDecimal getQtyRequestedVol() {
		return qtyRequestedVol;
	}

	public BigDecimal getQtyReservedMin() {
		return qtyReservedMin;
	}

	public BigDecimal getQtyReservedVol() {
		return qtyReservedVol;
	}

	public BigDecimal getReserved() {
		return reserved;
	}

	public String getValue() {
		return value;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOnHand(BigDecimal onHand) {
		this.onHand = onHand;
	}

	public void setOrdered(BigDecimal ordered) {
		this.ordered = ordered;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setQtyAvailableMin(BigDecimal qtyAvailableMin) {
		this.qtyAvailableMin = qtyAvailableMin;
	}

	public void setQtyAvailableVol(BigDecimal qtyAvailableVol) {
		this.qtyAvailableVol = qtyAvailableVol;
	}

	public void setQtyOnHandMin(BigDecimal qtyOnHandMin) {
		this.qtyOnHandMin = qtyOnHandMin;
	}

	public void setQtyOnHandVol(BigDecimal qtyOnHandVol) {
		this.qtyOnHandVol = qtyOnHandVol;
	}

	public void setQtyRequestedMin(BigDecimal qtyRequestedMin) {
		this.qtyRequestedMin = qtyRequestedMin;
	}

	public void setQtyRequestedVol(BigDecimal qtyRequestedVol) {
		this.qtyRequestedVol = qtyRequestedVol;
	}

	public void setQtyReservedMin(BigDecimal qtyReservedMin) {
		this.qtyReservedMin = qtyReservedMin;
	}

	public void setQtyReservedVol(BigDecimal qtyReservedVol) {
		this.qtyReservedVol = qtyReservedVol;
	}

	public void setReserved(BigDecimal reserved) {
		this.reserved = reserved;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

}