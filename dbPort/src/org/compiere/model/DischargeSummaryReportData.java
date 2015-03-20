/**
 * 
 */
package org.compiere.model;

/**
 * @author arangel
 *
 */
public class DischargeSummaryReportData {
	
	private int value;
	private String pacType;
	private String pacTypeValue;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getPacType() {
		return pacType;
	}
	public void setPacType(String pacType) {
		this.pacType = pacType;
	}
	public String getPacTypeValue() {
		return pacTypeValue;
	}
	public void setPacTypeValue(String pacTypeValue) {
		this.pacTypeValue = pacTypeValue;
	}
}
