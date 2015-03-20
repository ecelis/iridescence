package org.compiere.util.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author odelarosa
 * 
 */
public class Transaction {
	private BigDecimal amount;
	private Date movementDate;
	private BigDecimal movementQty;
	private String movementType;
	private String productDescription;
	private String productValue;
	private String strMovementType;
	private String warehouseName;

	public BigDecimal getAmount() {
		return amount;
	}

	public Date getMovementDate() {
		return movementDate;
	}

	public BigDecimal getMovementQty() {
		return movementQty;
	}

	public String getMovementType() {
		return movementType;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductValue() {
		return productValue;
	}

	public String getStrMovementType() {
		return strMovementType;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	public void setMovementQty(BigDecimal movementQty) {
		this.movementQty = movementQty;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}

	public void setStrMovementType(String strMovementType) {
		this.strMovementType = strMovementType;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

}
