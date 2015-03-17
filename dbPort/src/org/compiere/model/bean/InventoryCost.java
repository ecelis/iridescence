/**
 * 
 */
package org.compiere.model.bean;

import java.math.BigDecimal;

/**
 * @author jonathan
 *
 */
public class InventoryCost {
	
	public String value;
	public String name;
	public String category;
	public String wname;
	public BigDecimal qty;
	public BigDecimal standardCost;
	public BigDecimal averageCost;
	public BigDecimal lastPoCost;
	public BigDecimal stockValue;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getStandardCost() {
		return standardCost;
	}

	public void setStandardCost(BigDecimal standardCost) {
		this.standardCost = standardCost;
	}

	public BigDecimal getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(BigDecimal averageCost) {
		this.averageCost = averageCost;
	}

	public BigDecimal getLastPoCost() {
		return lastPoCost;
	}

	public void setLastPoCost(BigDecimal lastPoCost) {
		this.lastPoCost = lastPoCost;
	}

	public BigDecimal getStockValue() {
		return stockValue;
	}

	public void setStockValue(BigDecimal stockValue) {
		this.stockValue = stockValue;
	}

}
