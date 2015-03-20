package org.compiere.model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 
 * @author rsolorzano
 *
 */
public class ZeroBalanceDetailView {

	private String EXME_CtaPac_ID;
	private String AD_Client_ID;
	private String AD_Org_ID;
	private String encounter;
	private String mrn;
	private String billing_status;
	private String primary_fc;
	private String primary_pc;
	private String primary_payer;
	private String primary_pg;
	private String episode;
	private double total_charges;
	private double total_payments; 
	private double total_pmt_charges;
	private double total_adjustment;
	private double total_adj_charges;
	private double total_insurance_payments;
	private double total_insurance_adjustments;
	private double total_patient_payments;
	private double total_patient_adjustments;
	private double total_bad_debt_adjustments;
	
	private String total_chargesStr;
	private String total_paymentsStr; 
	private String total_pmt_chargesStr;
	private String total_adjustmentStr;
	private String total_adj_chargesStr;
	private String total_insurance_paymentsStr;
	private String total_insurance_adjustmentsStr;
	private String total_patient_paymentsStr;
	private String total_patient_adjustmentsStr;
	private String total_bad_debt_adjustmentsStr;
	
	
	
	public ZeroBalanceDetailView() {
		super();
	}

	public ZeroBalanceDetailView(String eXME_CtaPac_ID, String aD_Client_ID, String aD_Org_ID, String encounter, String mrn, String billing_status, String primary_fc, String primary_pc, String primary_payer, String primary_pg, double total_charges, double total_payments, double total_pmt_charges, double total_adjustment, double total_adj_charges, double total_insurance_payments,
			double total_insurance_adjustments, double total_patient_payments, double total_patient_adjustments, double total_bad_debt_adjustments) {
		super();
		
		EXME_CtaPac_ID = eXME_CtaPac_ID;
		AD_Client_ID = aD_Client_ID;
		AD_Org_ID = aD_Org_ID;
		this.encounter = encounter;
		this.mrn = mrn;
		this.billing_status = billing_status;
		this.primary_fc = primary_fc;
		this.primary_pc = primary_pc;
		this.primary_payer = primary_payer;
		this.primary_pg = primary_pg;
		this.total_charges = total_charges;
		this.total_payments = total_payments;
		this.total_pmt_charges = total_pmt_charges;
		this.total_adjustment = total_adjustment;
		this.total_adj_charges = total_adj_charges;
		this.total_insurance_payments = total_insurance_payments;
		this.total_insurance_adjustments = total_insurance_adjustments;
		this.total_patient_payments = total_patient_payments;
		this.total_patient_adjustments = total_patient_adjustments;
		this.total_bad_debt_adjustments = total_bad_debt_adjustments;
		
		this.total_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_charges);
		this.total_paymentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_payments);
		this.total_pmt_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_pmt_charges);
		this.total_adjustmentStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_adjustment);
		this.total_adj_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_adj_charges);
		this.total_insurance_paymentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_insurance_payments);
		this.total_insurance_adjustmentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_insurance_adjustments);
		this.total_patient_paymentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_patient_payments);
		this.total_patient_adjustmentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_patient_adjustments);
		this.total_bad_debt_adjustmentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_bad_debt_adjustments);
		
	}

	public String getEXME_CtaPac_ID() {
		return EXME_CtaPac_ID;
	}

	public void setEXME_CtaPac_ID(String eXME_CtaPac_ID) {
		EXME_CtaPac_ID = eXME_CtaPac_ID;
	}

	public String getAD_Client_ID() {
		return AD_Client_ID;
	}

	public void setAD_Client_ID(String aD_Client_ID) {
		AD_Client_ID = aD_Client_ID;
	}

	public String getAD_Org_ID() {
		return AD_Org_ID;
	}

	public void setAD_Org_ID(String aD_Org_ID) {
		AD_Org_ID = aD_Org_ID;
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

	public String getBilling_status() {
		return billing_status;
	}

	public void setBilling_status(String billing_status) {
		this.billing_status = billing_status;
	}

	public String getPrimary_fc() {
		return primary_fc;
	}

	public void setPrimary_fc(String primary_fc) {
		this.primary_fc = primary_fc;
	}

	public String getPrimary_pc() {
		return primary_pc;
	}

	public void setPrimary_pc(String primary_pc) {
		this.primary_pc = primary_pc;
	}

	public String getPrimary_payer() {
		return primary_payer;
	}

	public void setPrimary_payer(String primary_payer) {
		this.primary_payer = primary_payer;
	}

	public String getPrimary_pg() {
		return primary_pg;
	}

	public void setPrimary_pg(String primary_pg) {
		this.primary_pg = primary_pg;
	}

	public String getEpisode() {
		return episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public double getTotal_charges() {
		return total_charges;
	}

	public void setTotal_charges(double total_charges) {
		this.total_charges = total_charges;
		this.total_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_charges);
	}

	public double getTotal_payments() {
		return total_payments;
	}

	public void setTotal_payments(double total_payments) {
		this.total_payments = total_payments;
		this.total_paymentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_payments);
	}

	public double getTotal_pmt_charges() {
		return total_pmt_charges;
	}

	public void setTotal_pmt_charges(double total_pmt_charges) {
		this.total_pmt_charges = total_pmt_charges;
		this.total_pmt_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_pmt_charges);
	}

	public double getTotal_adjustment() {
		return total_adjustment;
	}

	public void setTotal_adjustment(double total_adjustment) {
		this.total_adjustment = total_adjustment;
		this.total_adjustmentStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_adjustment);
	}

	public double getTotal_adj_charges() {
		return total_adj_charges;
	}

	public void setTotal_adj_charges(double total_adj_charges) {
		this.total_adj_charges = total_adj_charges;
		this.total_adj_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_adj_charges);
	}

	public double getTotal_insurance_payments() {
		return total_insurance_payments;
	}

	public void setTotal_insurance_payments(double total_insurance_payments) {
		this.total_insurance_payments = total_insurance_payments;
		this.total_insurance_paymentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_insurance_payments);
	}

	public double getTotal_insurance_adjustments() {
		return total_insurance_adjustments;
	}

	public void setTotal_insurance_adjustments(double total_insurance_adjustments) {
		this.total_insurance_adjustments = total_insurance_adjustments;
		this.total_insurance_adjustmentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_insurance_adjustments);
	}

	public double getTotal_patient_payments() {
		return total_patient_payments;
	}

	public void setTotal_patient_payments(double total_patient_payments) {
		this.total_patient_payments = total_patient_payments;
		this.total_patient_paymentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_patient_payments);
	}

	public double getTotal_patient_adjustments() {
		return total_patient_adjustments;
	}

	public void setTotal_patient_adjustments(double total_patient_adjustments) {
		this.total_patient_adjustments = total_patient_adjustments;
		this.total_patient_adjustmentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_patient_adjustments);
	}

	public double getTotal_bad_debt_adjustments() {
		return total_bad_debt_adjustments;
	}

	public void setTotal_bad_debt_adjustments(double total_bad_debt_adjustments) {
		this.total_bad_debt_adjustments = total_bad_debt_adjustments;
		this.total_bad_debt_adjustmentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_bad_debt_adjustments);
	}

	public String getTotal_chargesStr() {
		return total_chargesStr;
	}

	public String getTotal_paymentsStr() {
		return total_paymentsStr;
	}

	public String getTotal_pmt_chargesStr() {
		return total_pmt_chargesStr;
	}

	public String getTotal_adjustmentStr() {
		return total_adjustmentStr;
	}

	public String getTotal_adj_chargesStr() {
		return total_adj_chargesStr;
	}

	public String getTotal_insurance_paymentsStr() {
		return total_insurance_paymentsStr;
	}

	public String getTotal_insurance_adjustmentsStr() {
		return total_insurance_adjustmentsStr;
	}

	public String getTotal_patient_paymentsStr() {
		return total_patient_paymentsStr;
	}

	public String getTotal_patient_adjustmentsStr() {
		return total_patient_adjustmentsStr;
	}

	public String getTotal_bad_debt_adjustmentsStr() {
		return total_bad_debt_adjustmentsStr;
	}
	
	
}
