package org.compiere.model;

public class BillingExceptionRptView {

	private String exmeCtaPacID;
	private String adClientID;
	private String adOrgID;
	private String encounter;
	private String mrn;
	private String patientType;
	private String billingException;
	private String daysAged;
	private String cBPartnerID;
	private String priCBPartnerID;
	private String primaryFC;
	private String primaryPC;
	private double totalCharges;
	private String attending;
	private String totalChargesStr;
	private String billingType;
	
	public BillingExceptionRptView() {
		super();
	}

	public BillingExceptionRptView(String exmeCtaPacID, String adClientID, String adOrgID, String encounter, 
			String mrn, String patientType, String billingException, String daysAged, String cBPartnerID, 
			String priCBPartnerID, String primaryFC, String primaryPC, double totalCharges, String attending, 
			String totalChargesStr, String billingType) {
		super();
		this.exmeCtaPacID = exmeCtaPacID;
		this.adClientID = adClientID;
		this.adOrgID = adOrgID;
		this.encounter = encounter;
		this.mrn = mrn;
		this.patientType = patientType;
		this.billingException = billingException;
		this.daysAged = daysAged;
		this.cBPartnerID = cBPartnerID;
		this.priCBPartnerID = priCBPartnerID;
		this.primaryFC = primaryFC;
		this.primaryPC = primaryPC;
		this.totalCharges = totalCharges;
		this.attending = attending;
		this.totalChargesStr = totalChargesStr;
		this.billingType = billingType;
	}

	public String getExmeCtaPacID() {
		return exmeCtaPacID;
	}

	public void setExmeCtaPacID(String exmeCtaPacID) {
		this.exmeCtaPacID = exmeCtaPacID;
	}

	public String getAdClientID() {
		return adClientID;
	}

	public void setAdClientID(String adClientID) {
		this.adClientID = adClientID;
	}

	public String getAdOrgID() {
		return adOrgID;
	}

	public void setAdOrgID(String adOrgID) {
		this.adOrgID = adOrgID;
	}

	public String getEncounter() {
		return encounter;
	}

	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getBillingException() {
		return billingException;
	}

	public void setBillingException(String billingException) {
		this.billingException = billingException;
	}

	public String getDaysAged() {
		return daysAged;
	}

	public void setDaysAged(String daysAged) {
		this.daysAged = daysAged;
	}

	public String getcBPartnerID() {
		return cBPartnerID;
	}

	public void setcBPartnerID(String cBPartnerID) {
		this.cBPartnerID = cBPartnerID;
	}

	public String getPriCBPartnerID() {
		return priCBPartnerID;
	}

	public void setPriCBPartnerID(String priCBPartnerID) {
		this.priCBPartnerID = priCBPartnerID;
	}

	public String getPrimaryFC() {
		return primaryFC;
	}

	public void setPrimaryFC(String primaryFC) {
		this.primaryFC = primaryFC;
	}

	public String getPrimaryPC() {
		return primaryPC;
	}

	public void setPrimaryPC(String primaryPC) {
		this.primaryPC = primaryPC;
	}

	public double getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
	}

	public String getAttending() {
		return attending;
	}

	public void setAttending(String attending) {
		this.attending = attending;
	}

	public String getTotalChargesStr() {
		return totalChargesStr;
	}

	public void setTotalChargesStr(String totalChargesStr) {
		this.totalChargesStr = totalChargesStr;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	
	
	
	
	
}
