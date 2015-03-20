package org.compiere.model.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MPayment;
import org.compiere.model.X_C_PaySelectionCheck;
import org.compiere.model.X_C_Payment;
import org.compiere.util.Env;
import org.compiere.util.PaymentUtils;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Card #1313
 * Bean para creacion de pagos
 * 
 * @author Lorena Lama
 */
public class PaymentBean implements Cloneable {

	/**
	 * Llena los datos de Payment con la informacion de MPayment
	 * 
	 * @param bean PaymentBean
	 * @param payment MPayment
	 */
	public static void fillFrom(final PaymentBean bean, final MPayment payment) {
		bean.setId(payment.getC_Payment_ID());
		bean.setDocTypeId(payment.getC_DocType_ID());
		bean.setBankAccountId(payment.getC_BankAccount_ID());
		bean.setbPartnerId(payment.getC_BPartner_ID());
		if (X_C_Payment.TENDERTYPE_Check.equals(payment.getTenderType())) {
			bean.setPaymentRule(X_C_PaySelectionCheck.PAYMENTRULE_Check);
		} else if (X_C_Payment.TENDERTYPE_DirectDeposit.equals(payment.getTenderType())) {
			bean.setPaymentRule(X_C_PaySelectionCheck.PAYMENTRULE_DirectDeposit);
		}
		bean.setDateTrx(payment.getDateTrx());
		bean.setConcept(payment.getDescription());
		bean.setPayAmt(payment.getPayAmt());
		bean.setCheckNo(payment.getCheckNo());
	}

	/**
	 * Llena los datos de MPayment con la informacion de Payment
	 * 
	 * @param bean PaymentBean
	 * @param payment MPayment
	 */
	public static void fillPayment(final PaymentBean bean, final MPayment payment) {
		payment.setC_DocType_ID(bean.getDocTypeId());
		payment.setC_BankAccount_ID(bean.getBankAccountId());
		payment.setC_BPartner_ID(bean.getbPartnerId());
		payment.setTenderType(bean.getTenderType());
		payment.setDateTrx(bean.getDateTrx());
		payment.setDateAcct(bean.getDateAcct()==null?bean.getDateTrx():bean.getDateAcct());
		payment.setDescription(bean.getConcept());
		payment.setPayAmt(bean.getPayAmt());// Dolar o PEsos
		payment.setCheckNo(bean.getCheckNo());
		payment.setReciboNo(" ");
		payment.setC_Currency_ID(bean.getCurrencyId());
		payment.setAllocationDate(bean.getAllocationDate());
		payment.setRate(bean.getRate());
		payment.setC_Charge_ID(bean.getCheckId());
	}
	
	private int					bankAccountId;
	private int					bPartnerId;
	private String				checkNo;
	private String				concept;
	private int					currencyId;
	private Timestamp			dateTrx;
	private Timestamp			dateAcct;
	private int					docTypeId;
	private int					id;
	private BigDecimal			payAmt;
	private BigDecimal 			conversionAmt;
	private BigDecimal			rate;
	private int					checkId;

	private MPaySelectionCheck	paymentCheck;
	private String				paymentRule;
	private Timestamp			allocationDate;
	
	private BigDecimal			available;
	private BigDecimal			amtPayment;
	private BigDecimal			amtMultiplier;
	private String			currencyName;
	private String			bPartnerName;
	
	private boolean isTrxSO;
	

	public PaymentBean() {
		isTrxSO = false;
	}

	@Override
	public PaymentBean clone() {
		PaymentBean obj = null;
		try {
			obj = (PaymentBean) super.clone();
		} catch (final CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}

	@Override
	public boolean equals(final Object o) {
		if (o != null && o instanceof PaymentBean) {
			return ((PaymentBean) o).toString().equals(this.toString());
		} else {
			return false;
		}
	}

	public Timestamp getAllocationDate() {
		return allocationDate;
	}

	public int getBankAccountId() {
		return bankAccountId;
	}

	public int getbPartnerId() {
		return bPartnerId;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public String getConcept() {
		return concept;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public Timestamp getDateTrx() {
		return dateTrx;
	}
	
	public Timestamp getDateAcct() {
		return dateAcct;
	}

	public int getDocTypeId() {
		return docTypeId;
	}

	public int getId() {
		return id;
	}

	public BigDecimal getPayAmt() {
		return payAmt;
	}

	public MPayment getPayment() {
		return new MPayment(Env.getCtx(), id, null);
	}

	public MPaySelectionCheck getPaymentCheck() {
		if (id > 0 && paymentCheck == null) {
			paymentCheck = MPaySelectionCheck.getOfPayment(Env.getCtx(), id, null);
		}
		return paymentCheck;
	}
	public String getTenderType(){
		return getTenderType(paymentRule);
	}
	public static String getTenderType(String paymentRule){
		if (X_C_PaySelectionCheck.PAYMENTRULE_Check.equals(paymentRule)) {
			return X_C_Payment.TENDERTYPE_Check;
		} else if (X_C_PaySelectionCheck.PAYMENTRULE_DirectDeposit.equals(paymentRule)) {
			return X_C_Payment.TENDERTYPE_DirectDeposit;
		} else {
			return null;
		}
	}
	
	public void setTrxSO(boolean isTrxSO) {
		this.isTrxSO = isTrxSO;
	}
	
	public void setAllocationDate(Timestamp allocationDate) {
		this.allocationDate = allocationDate;
	}

	public void setBankAccountId(final int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public void setbPartnerId(final int bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	public void setCheckNo(final String checkNo) {
		if (!StringUtils.equals(PaymentUtils.getBankAccountDoc(bankAccountId, paymentRule),checkNo)) {
			this.checkNo = checkNo;
		}
	}

	public void setConcept(final String concept) {
		this.concept = concept;
	}

	public void setCurrencyId(final int currencyId) {
		this.currencyId = currencyId;
	}

	public void setDateTrx(final Timestamp dateTrx) {
		this.dateTrx = dateTrx;
	}

	public void setDateAcct(Timestamp dateAcct) {
		this.dateAcct = dateAcct;
	}
	
	public void setDocTypeId(final int docTypeId) {
		this.docTypeId = docTypeId;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setPayAmt(final BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	public void setPaymentCheck(final MPaySelectionCheck paymentCheck) {
		this.paymentCheck = paymentCheck;
	}

	/**
	 * @return Lista de errores
	 */
	public ErrorList validate() {
		final ErrorList errorList = new ErrorList();
		if (dateTrx == null) {
			dateTrx = Env.getCurrentDate();
		}
		// amt
		if (payAmt == null || BigDecimal.ZERO.compareTo(payAmt) == 0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "error.contraRecibos.montoNoValido"));
		} else if (bankAccountId <= 0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "error.nobanco"));
		} else if (X_C_PaySelectionCheck.PAYMENTRULE_DirectDeposit.equals(paymentRule) && dateTrx.after(Env.getCurrentDate())) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "patient.error.futureDate"));
		}
		// validar que el numero de cheque no se haya asignado a otro pago
		if (id <= 0) {
			if (StringUtils.isNotBlank(checkNo)) {
				if (PaymentUtils.isSequenceUsed(checkNo, bankAccountId, getTenderType())) {
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "error.unidadadmin.docexistente"));// FIXME
				}
				// validar uqe haya una secuencia para la regla de pago y cuenta bancaria
			} else if (StringUtils.isNotEmpty(paymentRule) && PaymentUtils.getBankAccountDoc(bankAccountId, paymentRule) == null) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "error.paymentselection.nocuenta"));
			}
		}
		
		return errorList;
	}
	
	
	/**
	 * @return Lista de errores
	 */
	public ErrorList validateReceipt() {
		final ErrorList errorList = new ErrorList();
		if (dateTrx == null) {
			dateTrx = Env.getCurrentDate();
		}
		if (dateAcct == null) {
			dateAcct = Env.getCurrentDate();
		}
		// amt
		if (payAmt == null || BigDecimal.ZERO.compareTo(payAmt) == 0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "error.contraRecibos.montoNoValido"));
		} else if (bankAccountId <= 0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(Env.getCtx(), "error.nobanco"));
		}
		return errorList;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public void setPaymentRule(String paymentRule) {
		this.paymentRule = paymentRule;
	}

	public String getPaymentRule() {
		return paymentRule;
	}
	
	public BigDecimal getConversionAmt() {
		return conversionAmt;
	}

	public void setConversionAmt(BigDecimal conversionAmt) {
		this.conversionAmt = conversionAmt;
	}
	
	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	public BigDecimal getAmtPayment() {
		return amtPayment;
	}

	public void setAmtPayment(BigDecimal amtPayment) {
		this.amtPayment = amtPayment;
	}

	public BigDecimal getAmtMultiplier() {
		return amtMultiplier;
	}

	public void setAmtMultiplier(BigDecimal amtMultiplier) {
		this.amtMultiplier = amtMultiplier;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	
	public String getbPartnerName() {
		return bPartnerName;
	}

	public void setbPartnerName(String bPartnerName) {
		this.bPartnerName = bPartnerName;
	}
	
	private String  routingNo ;
	private String  accountNo ;
	private String  micr ;
	private String  a_Name ;
	private String  creditCardType ;
	private String  creditCardNumber ;
	private String  creditCardVV ;
	private int     creditCardExpMM ;
	private int     creditCardExpYY ;
	private String  documentNo ;
	private int     ctaPacId ;
	private boolean isPrepayment;
	private int     invoiceId ;

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public boolean isPrepayment() {
		return isPrepayment;
	}

	public void setPrepayment(boolean isPrepayment) {
		this.isPrepayment = isPrepayment;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getRoutingNo() {
		return routingNo;
	}

	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getMicr() {
		return micr;
	}

	public void setMicr(String micr) {
		this.micr = micr;
	}

	public String getA_Name() {
		return a_Name;
	}

	public void setA_Name(String a_Name) {
		this.a_Name = a_Name;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardVV() {
		return creditCardVV;
	}

	public void setCreditCardVV(String creditCardVV) {
		this.creditCardVV = creditCardVV;
	}

	public int getCreditCardExpMM() {
		return creditCardExpMM;
	}

	public void setCreditCardExpMM(int creditCardExpMM) {
		this.creditCardExpMM = creditCardExpMM;
	}

	public int getCreditCardExpYY() {
		return creditCardExpYY;
	}

	public void setCreditCardExpYY(int creditCardExpYY) {
		this.creditCardExpYY = creditCardExpYY;
	}

	public boolean isTrxSO() {
		return isTrxSO;
	}

	public void setCtaPacId(int selectedId) {
		ctaPacId = selectedId;
	}
	public int getCtaPacId() {
		return ctaPacId;
	}
	
	public void setCheckId(int checkId){
		this.checkId = checkId;
	}
	
	public int getCheckId(){
		return checkId;
	}

	
}