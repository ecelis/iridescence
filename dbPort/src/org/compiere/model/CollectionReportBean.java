/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author jonathan
 * 
 */
public class CollectionReportBean {
	private BigDecimal amount;
	private Timestamp documentDate;
	private String documentNo;
	private String documentType;
	private String name;

	public BigDecimal getAmount() {
		return amount;
	}

	public Timestamp getDocumentDate() {
		return documentDate;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public String getDocumentType() {
		return documentType;
	}

	public String getName() {
		return name;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setDocumentDate(Timestamp documentDate) {
		this.documentDate = documentDate;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public void setName(String name) {
		this.name = name;
	}

}
