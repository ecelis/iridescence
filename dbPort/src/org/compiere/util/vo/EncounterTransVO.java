package org.compiere.util.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MAllocationLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MCharge;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MPayment;
import org.compiere.model.MEXMECtaPac;
import org.compiere.util.Constantes;

public class EncounterTransVO {
	private MEXMECtaPac ctapac = null;
	private MAllocationLine allocationLine = null;
	private MPayment payment = null;
	private MCtaPacDet ctapacDet = null;
	private MBPartner bpartner = null;
	private Timestamp dos = null;
	private Timestamp date_posted = null;
	private String code = "";
	private String description = "";
	private String revCode = "";
	private String cpt_hcpcs = "";
	private BigDecimal qty = BigDecimal.ZERO;
	private BigDecimal unit_charge = null;
	private BigDecimal total_charge = null;
	private BigDecimal payments = null;
	private BigDecimal adjustments = null;
	private final String PROFESSIONAL = "Professional";
	private final String INSTITUTIONAL = "Institutional";
	private String billingType = null;
	private BigDecimal information = null;
		
	public EncounterTransVO(Properties ctx, MEXMECtaPac ctapac) {
		setCtapac(ctapac);
	}
	
	public EncounterTransVO(Properties ctx, MAllocationLine allocationLine) {
		setAllocationLine(allocationLine);
		if (allocationLine.getCtaPacID() > 0) {
			setCtapac(new MEXMECtaPac(ctx, allocationLine.getCtaPacID(), null));
		}
		setDate_posted(allocationLine.getCreated());
		setCode(allocationLine.getC_Charge().getValue());
		setDescription(allocationLine.getC_Charge().getName());
		if (allocationLine.getC_Charge().getType().equals(MCharge.TYPE_InsurancePayments)) {
			setPayments(allocationLine.getAmount().negate());
			setDescription(allocationLine.getC_Invoice().getC_BPartner().getName().concat(" ").concat(getDescription()));
		} else if (allocationLine.getC_Charge().getType().equals(MCharge.TYPE_CopayPayment)
					|| allocationLine.getC_Charge().getType().equals(MCharge.TYPE_DeductiblePayment)
					|| allocationLine.getC_Charge().getType().equals(MCharge.TYPE_CoinsurancePayment)
					|| allocationLine.getC_Charge().getType().equals(MCharge.TYPE_OthersPayment)
					|| allocationLine.getC_Charge().getType().equals(MCharge.TYPE_Payment)) {
			setPayments(allocationLine.getAmount().negate());
		} else if (allocationLine.getC_Charge().getType().equals(MCharge.TYPE_Adjustment)
				|| allocationLine.getC_Charge().getType().equals(MCharge.TYPE_PatientAdjustments)
				|| allocationLine.getC_Charge().getType().equals(MCharge.TYPE_BadDebtAdjustment)
				|| allocationLine.getC_Charge().getType().equals(MCharge.TYPE_Others)) {
			setAdjustments(allocationLine.getAmount().negate());
		}
	}
	
	
	public EncounterTransVO(Properties ctx, MPayment payment) {
		setPayment(payment);
		setDos(payment.getDateTrx());
		setDate_posted(payment.getCreated());
		setCode(payment.getC_Charge().getValue());
		setDescription(payment.getC_Charge().getName());
		setBillingType(MPayment.CONFTYPE_Professional.equals(payment.getConfType()) ? PROFESSIONAL : INSTITUTIONAL );
		
		// Si el ajuste es un pago de aseguradora
		if (MCharge.TYPE_InsurancePayments.equals(payment.getC_Charge().getType())) {
			setPayments(payment.getPayAmt().negate());
			setDescription(payment.getC_BPartner_ID()>0?payment.getC_BPartner().getName().concat(" ").concat(getDescription()):getDescription());
			
		} else  if (MCharge.TYPE_CopayPayment.equals(payment.getC_Charge().getType()) 
				|| MCharge.TYPE_DeductiblePayment.equals(payment.getC_Charge().getType())
				|| MCharge.TYPE_CoinsurancePayment.equals(payment.getC_Charge().getType()) 
				|| MCharge.TYPE_OthersPayment.equals(payment.getC_Charge().getType())
				|| MCharge.TYPE_Payment.equals(payment.getC_Charge().getType()) ) {
			setPayments(payment.getPayAmt().negate());
//			setDescription(MRefList.getListName(ctx, X_C_Charge.TYPE_AD_Reference_ID, X_C_Charge.TYPE_Payment));
			
		} else if (MCharge.TYPE_Adjustment.equals(payment.getC_Charge().getType()) 
				|| MCharge.TYPE_PatientAdjustments.equals(payment.getC_Charge().getType())
				|| MCharge.TYPE_BadDebtAdjustment.equals(payment.getC_Charge().getType()) 
				|| MCharge.TYPE_Others.equals(payment.getC_Charge().getType())) {
			setAdjustments(payment.getPayAmt().negate());
//			setDescription(MRefList.getListName(ctx, X_C_Charge.TYPE_AD_Reference_ID, X_C_Charge.TYPE_Adjustment));
			
		} else if (MCharge.TYPE_Deductible.equals(payment.getC_Charge().getType()) || MCharge.TYPE_CoPay.equals(payment.getC_Charge().getType())
					|| MCharge.TYPE_Coinsurance.equals(payment.getC_Charge().getType())) {
			setInformation(payment.getPayAmt());
		}
		
		if (payment.getEXME_CtaPac_ID() > 0) {
			setCtapac(new MEXMECtaPac(ctx, payment.getEXME_CtaPac_ID(), null));
		}
		
	}
	
	
	public EncounterTransVO(Properties ctx, MCtaPacDet ctapacDet) {
		setCtapacDet(ctapacDet);
		if (ctapacDet.getEXME_CtaPac_ID() > 0) {
			setCtapac(new MEXMECtaPac(ctx, ctapacDet.getEXME_CtaPac_ID(), null));
		}
		StringBuilder strDescription = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		strDescription.append(ctapacDet.getProduct().getName());
		setBillingType(ctapacDet.isProfessionalClaim() ? PROFESSIONAL : INSTITUTIONAL);
		
		setDos(ctapacDet.getDateDelivered());
		setDate_posted(ctapacDet.getCreated());
		setCode(ctapacDet.getMProductValue());
		setDescription(strDescription.toString());
		setRevCode(ctapacDet.getEXME_RevenueCode().getValue());
		setCpt_hcpcs(ctapacDet.getProduct().getIntevencion() != null 
				? ctapacDet.getProduct().getIntevencion().getValue()
						: "");
		setQty(ctapacDet.getQtyDelivered());
		
		if (ctapacDet.isCalcularPrecio()){
			setUnit_charge(ctapacDet.getPriceList());
			setTotal_charge(ctapacDet.getLineNetAmt());
		}else{
			setUnit_charge(null);
			setTotal_charge(null);
		}
//		this.ctx = ctx;
	}
	
	public MBPartner getBpartner() {
		return bpartner;
	}
	
	public void setBpartner(MBPartner bpartner) {
		this.bpartner = bpartner;
	}
	
	public MEXMECtaPac getCtapac() {
		return ctapac;
	}
	
	public void setCtapac(MEXMECtaPac ctapac) {
		this.ctapac = ctapac;
	}

	public void setCtapacDet(MCtaPacDet ctapacDet) {
		this.ctapacDet = ctapacDet;
	}

	public MCtaPacDet getCtapacDet() {
		return ctapacDet;
	}

	public void setDos(Timestamp dos) {
		this.dos = dos;
	}

	public Timestamp getDos() {
		return dos;
	}

	public void setDate_posted(Timestamp date_posted) {
		this.date_posted = date_posted;
	}

	public Timestamp getDate_posted() {
		return date_posted;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setRevCode(String revCode) {
		this.revCode = revCode;
	}

	public String getRevCode() {
		return revCode;
	}

	public void setCpt_hcpcs(String cpt_hcpcs) {
		this.cpt_hcpcs = cpt_hcpcs;
	}

	public String getCpt_hcpcs() {
		return cpt_hcpcs;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setUnit_charge(BigDecimal unit_charge) {
		this.unit_charge = unit_charge;
	}

	public BigDecimal getUnit_charge() {
		return unit_charge;
	}

	public void setPayments(BigDecimal payments) {
		this.payments = payments;
	}

	public BigDecimal getPayments() {
		return payments;
	}

	public void setAdjustments(BigDecimal adjustments) {
		this.adjustments = adjustments;
	}

	public BigDecimal getAdjustments() {
		return adjustments;
	}

	public void setTotal_charge(BigDecimal total_charge) {
		this.total_charge = total_charge;
	}

	public BigDecimal getTotal_charge() {
		return total_charge;
	}
	

	public MAllocationLine getAllocationLine() {
		return allocationLine;
	}

	public void setAllocationLine(MAllocationLine allocationLine) {
		this.allocationLine = allocationLine;
	}

	public MPayment getPayment() {
		return payment;
	}

	public void setPayment(MPayment payment) {
		this.payment = payment;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public BigDecimal getInformation() {
		return information;
	}

	public void setInformation(BigDecimal information) {
		this.information = information;
	}		
	
}
