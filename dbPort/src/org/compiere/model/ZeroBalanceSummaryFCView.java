package org.compiere.model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 
 * @author rsolorzano
 * 
 */
public class ZeroBalanceSummaryFCView {

	private String primary_fc;
	private double total_charges;
	private double total_payment;
	private double total_pmt_charges;
	private double total_adjustment;
	private double total_adj_charges;
	private double total_bd_adjustment;
	private double total_db_charges;
	private double total_insurance_payments;
	private double total_insurance_adjustments;
	private double total_patient_payment;
	private double total_patient_adjustments;
	private double total;
	
	private String total_chargesStr;
	private String total_paymentStr;
	private String total_pmt_chargesStr;
	private String total_adjustmentStr;
	private String total_adj_chargesStr;
	private String total_bd_adjustmentStr;
	private String total_db_chargesStr;
	private String total_insurance_paymentsStr;
	private String total_insurance_adjustmentsStr;
	private String total_patient_paymentStr;
	private String total_patient_adjustmentsStr;
	private String totalStr;
	private NumberFormat percent = NumberFormat.getPercentInstance();

	public ZeroBalanceSummaryFCView() {
		super();
		percent.setMinimumFractionDigits(2);
	}

	public ZeroBalanceSummaryFCView(String primary_fc, double total_charges, double total_payment, double total_pmt_charges, double total_adjustment, 
			double total_adj_charges, double total_bd_adjustment, double total_db_charges, double total_insurance_payments, 
			double total_insurance_adjustments, double total_patient_payment, double total_patient_adjustments, double total) {
		super();
		this.primary_fc = primary_fc;
		this.total_charges = total_charges;
		this.total_payment = total_payment;
		this.total_pmt_charges = total_pmt_charges;
		this.total_adjustment = total_adjustment;
		this.total_adj_charges = total_adj_charges;
		this.total_bd_adjustment = total_bd_adjustment;
		this.total_db_charges = total_db_charges;
		this.total_insurance_payments = total_insurance_payments;
		this.total_insurance_adjustments = total_insurance_adjustments;
		this.total_patient_payment = total_patient_payment;
		this.total_patient_adjustments = total_patient_adjustments;
		this.total = total;
		
		this.total_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_charges);
		this.total_paymentStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_payment);
		this.total_pmt_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_pmt_charges);
		this.total_adjustmentStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_adjustment);
		this.total_adj_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_charges);
		this.total_bd_adjustmentStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_bd_adjustment);
		this.total_db_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_db_charges);
		this.total_insurance_paymentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_insurance_payments);
		this.total_insurance_adjustmentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_insurance_adjustments);
		this.total_patient_paymentStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_patient_payment);
		this.total_patient_adjustmentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_patient_adjustments);
		this.totalStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total);
	}

	public String getPrimary_fc() {
		return primary_fc;
	}

	public void setPrimary_fc(String primary_fc) {
		this.primary_fc = primary_fc;
	}

	public double getTotal_charges() {
		return total_charges;
	}

	public void setTotal_charges(double total_charges) {
		this.total_charges = total_charges;
		this.total_chargesStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_charges);
	}

	public double getTotal_payment() {
		return total_payment;
	}

	public void setTotal_payment(double total_payment) {
		this.total_payment = total_payment;
		this.total_paymentStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_payment);
	}

	public double getTotal_pmt_charges() {
		return total_pmt_charges;
	}

	public void setTotal_pmt_charges(double total_pmt_charges) {
		this.total_pmt_charges = total_pmt_charges;
		this.total_pmt_chargesStr = percent.format(total_pmt_charges);
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
		this.total_adj_chargesStr = percent.format(total_adj_charges);
	}

	public double getTotal_bd_adjustment() {
		return total_bd_adjustment;
	}

	public void setTotal_bd_adjustment(double total_bd_adjustment) {
		this.total_bd_adjustment = total_bd_adjustment;
		this.total_bd_adjustmentStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_bd_adjustment);
	}

	public double getTotal_db_charges() {
		return total_db_charges;
	}

	public void setTotal_db_charges(double total_db_charges) {
		this.total_db_charges = total_db_charges;
		this.total_db_chargesStr = percent.format(total_db_charges);
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

	public double getTotal_patient_payment() {
		return total_patient_payment;
	}

	public void setTotal_patient_payment(double total_patient_payment) {
		this.total_patient_payment = total_patient_payment;
		this.total_patient_paymentStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_patient_payment);
	}

	public double getTotal_patient_adjustments() {
		return total_patient_adjustments;
	}

	public void setTotal_patient_adjustments(double total_patient_adjustments) {
		this.total_patient_adjustments = total_patient_adjustments;
		this.total_patient_adjustmentsStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total_patient_adjustments);
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
		this.totalStr = NumberFormat.getCurrencyInstance(Locale.CANADA).format(total);
	}

	public String getTotal_chargesStr() {
		return total_chargesStr;
	}

	public String getTotal_paymentStr() {
		return total_paymentStr;
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

	public String getTotal_bd_adjustmentStr() {
		return total_bd_adjustmentStr;
	}

	public String getTotal_db_chargesStr() {
		return total_db_chargesStr;
	}

	public String getTotal_insurance_paymentsStr() {
		return total_insurance_paymentsStr;
	}

	public String getTotal_insurance_adjustmentsStr() {
		return total_insurance_adjustmentsStr;
	}

	public String getTotal_patient_paymentStr() {
		return total_patient_paymentStr;
	}

	public String getTotal_patient_adjustmentsStr() {
		return total_patient_adjustmentsStr;
	}

	public String getTotalStr() {
		return totalStr;
	}
	
	
	


	

}
