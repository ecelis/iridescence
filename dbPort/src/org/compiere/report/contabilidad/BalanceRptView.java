package org.compiere.report.contabilidad;

import java.math.BigDecimal;

/**
 * 
 * @author rsolorzano
 *
 */
public class BalanceRptView {
	
	private int elementValueID;
	private String value;
	private String name;
	private BigDecimal initAmt;
	private BigDecimal debitAmt;
	private BigDecimal creditAmt;
	private BigDecimal periodAmt;
	private BigDecimal finalAmt;
	private boolean isSummary;
	private int parentID;
	private boolean isProcessed;
	
	public BalanceRptView() {
		super();
	}

	public BalanceRptView(int elementValueID, String value, String name, BigDecimal initAmt, BigDecimal debitAmt, BigDecimal creditAmt,
			BigDecimal periodAmt, BigDecimal finalAmt, boolean isSummary, int parentID, boolean isProcessed) {
		super();
		this.elementValueID = elementValueID;
		this.value = value;
		this.name = name;
		this.initAmt = initAmt;
		this.debitAmt = debitAmt;
		this.creditAmt = creditAmt;
		this.periodAmt = periodAmt;
		this.finalAmt = finalAmt;
		this.isSummary = isSummary;
		this.parentID = parentID;
		this.isProcessed = isProcessed;
	}

	public int getElementValueID() {
		return elementValueID;
	}

	public void setElementValueID(int elementValueID) {
		this.elementValueID = elementValueID;
	}

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

	public BigDecimal getInitAmt() {
		return initAmt;
	}

	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	public BigDecimal getDebitAmt() {
		return debitAmt;
	}

	public void setDebitAmt(BigDecimal debitAmt) {
		this.debitAmt = debitAmt;
	}

	public BigDecimal getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(BigDecimal creditAmt) {
		this.creditAmt = creditAmt;
	}

	public BigDecimal getPeriodAmt() {
		return periodAmt;
	}

	public void setPeriodAmt(BigDecimal periodAmt) {
		this.periodAmt = periodAmt;
	}

	public BigDecimal getFinalAmt() {
		return finalAmt;
	}

	public void setFinalAmt(BigDecimal finalAmt) {
		this.finalAmt = finalAmt;
	}

	public boolean isSummary() {
		return isSummary;
	}

	public void setSummary(boolean isSummary) {
		this.isSummary = isSummary;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	
	
	
	
	
	
	

}
