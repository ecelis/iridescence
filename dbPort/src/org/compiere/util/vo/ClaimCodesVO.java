package org.compiere.util.vo;


public class ClaimCodesVO {
	
	private int adTableID = 0;
	private int recordID = 0;
	private int adTableOrigID = 0;
	private int recordOrigID = 0;
	private String value = "";
	private String name = "";
	private String description ="";
	public int getAdTableID() {
		return adTableID;
	}
	public void setAdTableID(int adTableID) {
		this.adTableID = adTableID;
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public int getAdTableOrigID() {
		return adTableOrigID;
	}
	public void setAdTableOrigID(int adTableOrigID) {
		this.adTableOrigID = adTableOrigID;
	}
	public int getRecordOrigID() {
		return recordOrigID;
	}
	public void setRecordOrigID(int recordOrigID) {
		this.recordOrigID = recordOrigID;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
