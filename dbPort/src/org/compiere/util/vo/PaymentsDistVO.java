package org.compiere.util.vo;


public class PaymentsDistVO {
	
	private String documentNo = null;
	private String encounterStatus = null;
	private String admitDate = null;
	private String dischargeDate = null;
	private int ctaPacID = 0;
	private int id = 0;
//	private Double payment = new Double(0);
//	private Double coPay = new Double(0);
//	private Double coInsurance = new Double(0);
//	private Double deductible = new Double(0);
//	private Double others = new Double(0);
//	
//	
//	private HashMap<Integer, ArrayList<Double>> payments = new HashMap<Integer, ArrayList<Double>>();
	
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getEncounterStatus() {
		return encounterStatus;
	}
	public void setEncounterStatus(String encounterStatus) {
		this.encounterStatus = encounterStatus;
	}
	public String getAdmitDate() {
		return admitDate;
	}
	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public int getCtaPacID() {
		return ctaPacID;
	}
	public void setCtaPacID(int ctaPacID) {
		this.ctaPacID = ctaPacID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	
	
	

}
