package org.compiere.model.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MDHPrePayment;
import org.compiere.model.MDHVoucher;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.X_M_Product;

/**
 * Encabezado del prepago (pro mujer)
 * 
 * @author Lorena Lama
 *         Card http://control.ecaresoft.com/card/1548/
 */
public class PrePaymentBean implements Cloneable {

	public static List<String> getProductClass(){
		final List<String> list = new ArrayList<>();
		list.add(X_M_Product.PRODUCTCLASS_Laboratory);
		list.add(X_M_Product.PRODUCTCLASS_XRay);
		list.add(X_M_Product.PRODUCTCLASS_Procedures);
		list.add(X_M_Product.PRODUCTCLASS_OtherService);
		list.add(X_M_Product.PRODUCTCLASS_Others);
		return list;
	}
	
	/**
	 * Llena los datos del modelo a partir del bean
	 * 
	 * @param beanFrom Objeto origen de los datos
	 * @param headerTo Objeto destino de los datos
	 */
	public static void fillFrom(final PrePaymentBean beanFrom, final MDHPrePayment headerTo) {
		if (beanFrom.getDateTrx() != null) {
			headerTo.setDateTrx(beanFrom.getDateTrx());
		}
		headerTo.setEXME_Paciente_ID(beanFrom.getPatientId());
		headerTo.setGrandTotal(beanFrom.getGrandTotal());
	}

	/**
	 * Llena los datos del modelo a partir del bean
	 * 
	 * @param headerFrom Objeto origen de los datos
	 * @param beanTo Objeto destino de los datos
	 */
	public static void fillFrom(final MDHPrePayment headerFrom, final PrePaymentBean beanTo) {
		beanTo.setDateTrx(headerFrom.getDateTrx());
		beanTo.setDocumentNo(headerFrom.getDocumentNo());
		beanTo.setGrandTotal(headerFrom.getGrandTotal());
		beanTo.setTaxAmt(headerFrom.getGrandTotal());
		beanTo.setId(headerFrom.getDH_PrePayment_ID());
		beanTo.setPatientId(headerFrom.getEXME_Paciente_ID());
		beanTo.setPatientName(MEXMEPaciente.getNombre_Pac(headerFrom.getEXME_Paciente_ID(), null));
		beanTo.setSubTotal(headerFrom.getGrandTotal().subtract(headerFrom.getTaxAmt()));
	}

	/**
	 * Llena los datos del modelo a partir del bean
	 * 
	 * @param beanFrom Objeto origen de los datos
	 * @param voucherTo Objeto destino de los datos
	 */
	public static void fillFrom(final PrePaymentBean beanFrom, final MDHVoucher voucherTo) {
		voucherTo.setDH_PrePayment_ID(beanFrom.getId());
		if (beanFrom.getDateTrx() != null) {
			voucherTo.setDateTrx(beanFrom.getDateTrx());
		}
		voucherTo.setGrandTotal(beanFrom.getGrandTotal());
		voucherTo.setC_BankAccount_ID(beanFrom.getBankAccountId());
		voucherTo.setEXME_FormaPago_ID(beanFrom.getPaymentFormId());
		voucherTo.setVoucher(beanFrom.getVoucher());
		if (beanFrom.getTaxAmt() != null) {
			voucherTo.setTaxAmt(beanFrom.getTaxAmt());
		}
	}

	private int			id;
	private int			patientId;
	private int			bankAccountId;
	private int			paymentFormId;
	private Timestamp	dateTrx;
	private BigDecimal	grandTotal;
	private BigDecimal	subTotal;
	private BigDecimal	taxAmt;
	private String		documentNo;
	private String		patientName;
	private String		voucher;

	/** 	 */
	public PrePaymentBean() {}

	@Override
	public PrePaymentBean clone() {
		PrePaymentBean obj = null;
		try {
			obj = (PrePaymentBean) super.clone();
		} catch (final CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	@Override
	public boolean equals(final Object o) {
		if (o != null && o instanceof PrePaymentBean) {
			return ((PrePaymentBean) o).toString().equals(this.toString());
		} else {
			return false;
		}
	}

	/** @return C_BankAccount_ID */
	public int getBankAccountId() {
		return bankAccountId;
	}

	public Timestamp getDateTrx() {
		return dateTrx;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	/** @return DH_PrePayment_ID */
	public int getId() {
		return id;
	}

	/** @return EXME_Paciente_ID */
	public int getPatientId() {
		return patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	/** @return EXME_FormaPago_ID */
	public int getPaymentFormId() {
		return paymentFormId;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public String getVoucher() {
		return voucher;
	}

	/** @param bankAccountId C_BankAccount_ID */
	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public void setDateTrx(Timestamp dateTrx) {
		this.dateTrx = dateTrx;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	/** @param id DH_PrePayment_ID */
	public void setId(int id) {
		this.id = id;
	}

	/** @param patientId EXME_Paciente_ID */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/** @param paymentFormId EXME_FormaPago_ID */
	public void setPaymentFormId(int paymentFormId) {
		this.paymentFormId = paymentFormId;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

}
