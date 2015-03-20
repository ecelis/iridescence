package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.apps.form.BeanPaySelect;
import org.compiere.model.bean.PaymentBean;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessCall;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.PaymentUtils;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Payment Model. - retrieve and create payments for invoice
 *
 * <pre>
 *  Event chain
 *  - Payment inserted
 *      C_Payment_Trg fires
 *          update DocumentNo with payment summary
 *  - Payment posted (C_Payment_Post)
 *      create allocation line
 *          C_Allocation_Trg fires
 *              Update C_BPartner Open Item Amount
 *      update invoice (IsPaid)
 *      link invoice-payment if batch
 *
 *  Lifeline:
 *  -   Created by VPayment or directly
 *  -   When changed in VPayment
 *      - old payment is reversed
 *      - new payment created
 *
 *  When Payment is posed, the Allocation is made
 * </pre>
 *
 * @author Jorg Janke
 * @version $Id: MPayment.java,v 1.3 2006/08/11 02:26:22 mrojas Exp $
 */
// Expert: Gisela Lee : se quito el final para poder extender
// public final class MPayment extends X_C_Payment
public class MPayment extends X_C_Payment implements DocAction, ProcessCall {
	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Get Payments Of BPartner
	 *
	 * @param ctx
	 *            context
	 * @param C_BPartner_ID
	 *            id
	 * @return array
	 */
	public static MPayment[] getOfBPartner(Properties ctx, int C_BPartner_ID, String trxName) {
		final List<MPayment> list = new Query(ctx, Table_Name, "C_BPartner_ID=?", trxName)//
				.setParameters(C_BPartner_ID).list();

		//
		MPayment[] retValue = new MPayment[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfBPartner

	/**************************************************************************
	 * Default Constructor
	 *
	 * @param ctx
	 *            context
	 * @param C_Payment_ID
	 *            payment to load, (0 create new payment)
	 * @param trxName
	 *            trx name
	 */
	public MPayment(Properties ctx, int C_Payment_ID, String trxName) {
		super(ctx, C_Payment_ID, trxName);
		// New
		if (C_Payment_ID == 0) {
			// payment.setOProcessing("N");
			setBackoffice("Y");//Lama
			setDocAction(DOCACTION_Complete);
			setDocStatus(DOCSTATUS_Drafted);
			setTrxType(TRXTYPE_Sales);
			//
			setR_AvsAddr(R_AVSZIP_Unavailable);
			setR_AvsZip(R_AVSZIP_Unavailable);
			//
			setIsReceipt(true);
			setIsApproved(false);
			setIsReconciled(false);
			setIsAllocated(false);
			setIsOnline(false);
			setIsSelfService(false);
			setIsDelayedCapture(false);
			setIsPrepayment(false);
			setProcessed(false);
			setProcessing(false);
			setPosted(false);
			//
			setPayAmt(Env.ZERO);
			setDiscountAmt(Env.ZERO);
			setTaxAmt(Env.ZERO);
			setWriteOffAmt(Env.ZERO);
			setIsOverUnderPayment(false);
			setOverUnderAmt(Env.ZERO);
			//
			setDateTrx(Env.getCurrentDate());
			setDateAcct(getDateTrx());
			setTenderType(TENDERTYPE_Check);

			setC_Currency_ID(Env.getC_Currency_ID(getCtx()));
			setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(getCtx()));
		}
	} // MPayment

	/**
	 * Load Constructor
	 *
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set record
	 */
	public MPayment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MPayment

	/** Temporary Payment Processors */
	private MPaymentProcessor[]	m_mPaymentProcessors	= null;
	/** Temporary Payment Processor */
	private MPaymentProcessor	m_mPaymentProcessor		= null;
	/** Logger */
	protected static CLogger	s_log					= CLogger.getCLogger(MPayment.class);
	/** Error Message */
	private String				m_errorMessage			= null;
	/** Reversal Indicator */
	public static String		REVERSE_INDICATOR		= "^";
	/**
	 * permite almacenar el monto disponible si es que el pago tiene mas de una
	 * factura asignada
	 */
	private BigDecimal			available				= Env.ZERO;
	/** Socio de negocio */
	public MBPartner			mbPartner				= null;
	/** Cuenta paciente */
	private MEXMECtaPac			mCtaPac					= null;
	/** linea de caja */
	private MCashLine			mCashLine				= null;
	/** extensiones a las que pertenece */
	private String				extensiones				= null;
	/** asignacion de pago */
	private MCtaPacPag			mCtaPacPag				= null;
	private String formaPago = null;
	private MUser user = null;
	private PaymentAmount paymentAmtConv;
	private Timestamp	allocationDate;
	/** Termino o condicion de pago */
	private MDocType mDocType = null;
//	private KeyNamePair columnDocAction;
	private boolean isFullPayment = true; // OverUnderAmt.
	private BigDecimal balanceAmt;
	private BigDecimal payAmtAllocation= Env.ZERO;
	
	/**
	 * Reset Payment to new status
	 */
	public void resetNew() {
		setC_Payment_ID(0); // forces new Record FIXME: throws
							// IllegalArgumentException:
							// "C_Payment_ID is mandatory."
		set_ValueNoCheck("DocumentNo", null);
		setDocAction(DOCACTION_Prepare);
		setDocStatus(DOCSTATUS_Drafted);
		setProcessed(false);
		setPosted(false);
		setIsReconciled(false);
		setIsAllocated(false);
		setIsOnline(false);
		setIsDelayedCapture(false);
		// setC_BPartner_ID(0);
		setC_Invoice_ID(0);
		setC_Order_ID(0);
		setC_Charge_ID(0);
		setC_Project_ID(0);
		setIsPrepayment(false);
	} // resetNew

	/**
	 * Is Cashbook Transfer Trx
	 *
	 * @return true if Cash Trx
	 */
	public boolean isCashTrx() {
		return TENDERTYPE_Cash.equals(getTenderType());
	} // isCashTrx

	/**************************************************************************
	 * Set Credit Card. Need to set PatmentProcessor after Amount/Currency Set
	 *
	 * @param TrxType
	 *            Transaction Type see TRX_
	 * @param creditCardType
	 *            CC type
	 * @param creditCardNumber
	 *            CC number
	 * @param creditCardVV
	 *            CC verification
	 * @param creditCardExpMM
	 *            CC Exp MM
	 * @param creditCardExpYY
	 *            CC Exp YY
	 * @return true if valid
	 */
	public boolean setCreditCard(String TrxType, String creditCardType, String creditCardNumber, String creditCardVV, int creditCardExpMM,
			int creditCardExpYY) {
		setTenderType(TENDERTYPE_CreditCard);
		setTrxType(TrxType);
		//
		setCreditCardType(creditCardType);
		setCreditCardNumber(creditCardNumber);
		setCreditCardVV(creditCardVV);
		setCreditCardExpMM(creditCardExpMM);
		setCreditCardExpYY(creditCardExpYY);
		//
		int check = MPaymentValidate.validateCreditCardNumber(creditCardNumber, creditCardType).length()
				+ MPaymentValidate.validateCreditCardExp(creditCardExpMM, creditCardExpYY).length();
		if (creditCardVV.length() > 0) {
			check += MPaymentValidate.validateCreditCardVV(creditCardVV, creditCardType).length();
		}
		return check == 0;
	} // setCreditCard

	/**
	 * Set Credit Card - Exp. Need to set PatmentProcessor after Amount/Currency
	 * Set
	 *
	 * @param TrxType
	 *            Transaction Type see TRX_
	 * @param creditCardType
	 *            CC type
	 * @param creditCardNumber
	 *            CC number
	 * @param creditCardVV
	 *            CC verification
	 * @param creditCardExp
	 *            CC Exp
	 * @return true if valid
	 */
	public boolean setCreditCard(String TrxType, String creditCardType, String creditCardNumber, String creditCardVV, String creditCardExp) {
		return setCreditCard(
				TrxType, creditCardType, creditCardNumber, creditCardVV, MPaymentValidate.getCreditCardExpMM(creditCardExp),
				MPaymentValidate.getCreditCardExpYY(creditCardExp));
	} // setCreditCard

	/**
	 * Set ACH BankAccount Info
	 *
	 * @param C_BankAccount_ID
	 *            bank account
	 * @param isReceipt
	 *            true if receipt
	 * @return true if valid
	 */
	public boolean setBankACH(MPaySelectionCheck preparedPayment) {
		// Our Bank
		setC_BankAccount_ID(preparedPayment.getParent().getC_BankAccount_ID());
		// Target Bank
		int C_BP_BankAccount_ID = preparedPayment.getC_BP_BankAccount_ID();
		MBPBankAccount ba = new MBPBankAccount(preparedPayment.getCtx(), C_BP_BankAccount_ID, null);
		setRoutingNo(ba.getRoutingNo());
		setAccountNo(ba.getAccountNo());
		setIsReceipt(X_C_Order.PAYMENTRULE_DirectDebit.equals // AR only
				(preparedPayment.getPaymentRule()));
		setPaymentType(PAYMENTTYPE_ACH);
		int check = MPaymentValidate.validateRoutingNo(getRoutingNo()).length() + MPaymentValidate.validateAccountNo(getAccountNo()).length();
		return check == 0;
	} // setBankACH

	/**
	 * Set ACH BankAccount Info
	 *
	 * @param C_BankAccount_ID
	 *            bank account
	 * @param isReceipt
	 *            true if receipt
	 * @return true if valid
	 */
	public boolean setBankACH(int C_BankAccount_ID, boolean isReceipt) {
		setBankAccountDetails(C_BankAccount_ID);
		setIsReceipt(isReceipt);
		//
		int check = MPaymentValidate.validateRoutingNo(getRoutingNo()).length() + MPaymentValidate.validateAccountNo(getAccountNo()).length();
		return check == 0;
	} // setBankACH

	/**
	 * Set ACH BankAccount Info
	 *
	 * @param C_BankAccount_ID
	 *            bank account
	 * @param isReceipt
	 *            true if receipt
	 * @param tenderType
	 *            - Direct Debit or Direct Deposit
	 * @param routingNo
	 *            routing
	 * @param accountNo
	 *            account
	 * @return true if valid
	 */
	public boolean setBankACH(int C_BankAccount_ID, boolean isReceipt, String tenderType, String routingNo, String accountNo) {
		setTenderType(tenderType);
		setIsReceipt(isReceipt);
		//
		if (C_BankAccount_ID > 0 && (routingNo == null || routingNo.length() == 0 || accountNo == null || accountNo.length() == 0))
			setBankAccountDetails(C_BankAccount_ID);
		else {
			setC_BankAccount_ID(C_BankAccount_ID);
			setRoutingNo(routingNo);
			setAccountNo(accountNo);
		}
		setCheckNo("");
		//
		int check = MPaymentValidate.validateRoutingNo(routingNo).length() + MPaymentValidate.validateAccountNo(accountNo).length();
		return check == 0;
	} // setBankACH

	/**
	 * Set Check BankAccount Info
	 *
	 * @param C_BankAccount_ID
	 *            bank account
	 * @param isReceipt
	 *            true if receipt
	 * @param checkNo
	 *            chack no
	 * @return true if valid
	 */
	public boolean setBankCheck(int C_BankAccount_ID, boolean isReceipt, String checkNo) {
		return setBankCheck(C_BankAccount_ID, isReceipt, null, null, checkNo);
	} // setBankCheck

	/**
	 * Set Check BankAccount Info
	 *
	 * @param C_BankAccount_ID
	 *            bank account
	 * @param isReceipt
	 *            true if receipt
	 * @param routingNo
	 *            routing no
	 * @param accountNo
	 *            account no
	 * @param checkNo
	 *            chack no
	 * @return true if valid
	 */
	public boolean setBankCheck(int C_BankAccount_ID, boolean isReceipt, String routingNo, String accountNo, String checkNo) {
		setTenderType(TENDERTYPE_Check);
		setIsReceipt(isReceipt);
		//
		if (C_BankAccount_ID > 0 && (routingNo == null || routingNo.length() == 0 || accountNo == null || accountNo.length() == 0))
			setBankAccountDetails(C_BankAccount_ID);
		else {
			setC_BankAccount_ID(C_BankAccount_ID);
			setRoutingNo(routingNo);
			setAccountNo(accountNo);
		}
		setCheckNo(checkNo);
		//
		int check = MPaymentValidate.validateRoutingNo(routingNo).length()
				+ MPaymentValidate.validateAccountNo(accountNo).length()
				+ MPaymentValidate.validateCheckNo(checkNo).length();
		return check == 0; // no error message
	} // setBankCheck

	/**
	 * Set Bank Account Details. Look up Routing No & Bank Acct No
	 *
	 * @param C_BankAccount_ID
	 *            bank account
	 */
	public void setBankAccountDetails(int C_BankAccount_ID) {
		if (C_BankAccount_ID == 0)
			return;
		setC_BankAccount_ID(C_BankAccount_ID);
		//
		String sql = "SELECT b.RoutingNo, ba.AccountNo "
				+ "FROM C_BankAccount ba"
				+ " INNER JOIN C_Bank b ON (ba.C_Bank_ID=b.C_Bank_ID) "
				+ "WHERE C_BankAccount_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, C_BankAccount_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				setRoutingNo(rs.getString(1));
				setAccountNo(rs.getString(2));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	} // setBankAccountDetails

	/**
	 * Set Account Address
	 *
	 * @param name
	 *            name
	 * @param street
	 *            street
	 * @param city
	 *            city
	 * @param state
	 *            state
	 * @param zip
	 *            zip
	 * @param country
	 *            country
	 */
	public void setAccountAddress(String name, String street, String city, String state, String zip, String country) {
		setA_Name(name);
		setA_Street(street);
		setA_City(city);
		setA_State(state);
		setA_Zip(zip);
		setA_Country(country);
	} // setAccountAddress

	/**************************************************************************
	 * Process Payment
	 *
	 * @return true if approved
	 */
	public boolean processOnline() {
		log.info("Amt=" + getPayAmt());
		//
		setIsOnline(true);
		setErrorMessage(null);
		// prevent charging twice
		if (isApproved()) {
			log.info("Already processed - " + getR_Result() + " - " + getR_RespMsg());
			setErrorMessage("Payment already Processed");
			return true;
		}

		if (m_mPaymentProcessor == null)
			setPaymentProcessor();
		if (m_mPaymentProcessor == null) {
			log.log(Level.WARNING, "No Payment Processor Model");
			setErrorMessage("No Payment Processor Model");
			return false;
		}

		boolean approved = false;
		/** Process Payment on Server */
		/*
		 * if (DB.isRemoteObjects()) { Server server =
		 * CConnection.get().getServer(); try { if (server != null) { // See
		 * ServerBean String trxName = null; // unconditionally save
		 * save(trxName); // server reads from disk approved =
		 * server.paymentOnline (getCtx(), getC_Payment_ID(),
		 * m_mPaymentProcessor.getC_PaymentProcessor_ID(), trxName); if
		 * (CLogMgt.isLevelFinest()) s_log.fine("server => " + approved);
		 * load(trxName); // server saves to disk setIsApproved(approved);
		 * return approved; } log.log(Level.WARNING, "AppsServer not found"); }
		 * catch (RemoteException ex) { log.log(Level.SEVERE,
		 * "AppsServer error", ex); } }
		 */
		/** **/

		// Try locally
		try {
			PaymentProcessor pp = PaymentProcessor.create(m_mPaymentProcessor, this);
			if (pp == null)
				setErrorMessage("No Payment Processor");
			else {
				approved = pp.processCC();
				if (approved)
					setErrorMessage(null);
				else
					setErrorMessage("From " + getCreditCardName() + ": " + getR_RespMsg());
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "processOnline", e);
			setErrorMessage("Payment Processor Error");
		}
		setIsApproved(approved);
		return approved;
	} // processOnline

	/**
	 * Process Online Payment. implements ProcessCall after standard constructor
	 * Called when pressing the Process_Online button in C_Payment
	 *
	 * @param ctx
	 *            Context
	 * @param pi
	 *            Process Info
	 * @return true if the next process should be performed
	 */
	public boolean startProcess(Properties ctx, ProcessInfo pi, Trx trx) {
		log.info("startProcess - " + pi.getRecord_ID());
		boolean retValue = false;
		//
		if (pi.getRecord_ID() != get_ID()) {
			log.log(Level.SEVERE, "startProcess - Not same Payment - " + pi.getRecord_ID());
			return false;
		}
		// Process it
		retValue = processOnline();
		save();
		return retValue; // Payment processed
	} // startProcess

	/**
	 * Before Save
	 *
	 * @param newRecord
	 *            new
	 * @return save
	 */
	protected boolean beforeSave(boolean newRecord) {
		// We have a charge
		if (getC_Charge_ID() != 0) {
			if (newRecord || is_ValueChanged("C_Charge_ID")) {
				setC_Order_ID(0);
				setC_Invoice_ID(0);
				setWriteOffAmt(Env.ZERO);
				setDiscountAmt(Env.ZERO);
				setIsOverUnderPayment(false);
				setOverUnderAmt(Env.ZERO);
				setIsPrepayment(false);
			}
		}
		// We need a BPartner
		else if (getC_BPartner_ID() == 0 && !isCashTrx()) {
			if (getC_Invoice_ID() != 0)
				;
			else if (getC_Order_ID() != 0)
				;
			else {
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@: @C_BPartner_ID@"));
				return false;
			}
		}
		// Prepayment: No charge and order or project (not as acct dimension)
		if (newRecord
				|| is_ValueChanged("C_Charge_ID")
				|| is_ValueChanged("C_Invoice_ID")
				|| is_ValueChanged("C_Order_ID")
				|| is_ValueChanged("C_Project_ID")) {
			setIsPrepayment(getC_Charge_ID() == 0
					&& getC_BPartner_ID() != 0
					&& (getC_Order_ID() != 0 || (getC_Project_ID() != 0 && getC_Invoice_ID() == 0)));
		}

		//
		if (isPrepayment()) {
			if (newRecord || is_ValueChanged("C_Order_ID") || is_ValueChanged("C_Project_ID")) {
				setWriteOffAmt(Env.ZERO);
				setDiscountAmt(Env.ZERO);
				setIsOverUnderPayment(false);
				setOverUnderAmt(Env.ZERO);
			}
		}

		// Document Type/Receipt
		if (getC_DocType_ID() == 0)
			setC_DocType_ID();
		else {
			MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
			setIsReceipt(dt.isSOTrx());
		}
		setDocumentNo();
		//
		if (getDateAcct() == null)
			setDateAcct(getDateTrx());
		//
		if (!isOverUnderPayment())
			setOverUnderAmt(Env.ZERO);

		// Organization
		if ((newRecord || is_ValueChanged("C_BankAccount_ID")) && getC_Charge_ID() == 0) // allow
																							// different
																							// org
																							// for
																							// charge
		{
			MBankAccount ba = MBankAccount.get(getCtx(), getC_BankAccount_ID());
			if (ba.getAD_Org_ID() != 0) {
				setAD_Org_ID(ba.getAD_Org_ID());
			}
		}

		// expert : gisela lee : asignar por default la org trx logueada (est
		// serv)
		if (getAD_OrgTrx_ID() == 0) {
			setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(getCtx()));
		}
		// expert : gisela lee : asignar por default la org trx logueada (est
		// serv)

		// Tener el paciente cuando exista una cuenta paciente
		if (getEXME_CtaPac_ID() > 0 && getEXME_Paciente_ID() <= 0) {
			MEXMECtaPac ctapac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
			if (ctapac != null) {
				setEXME_Paciente_ID(ctapac.getEXME_Paciente_ID());
			}
		}

		// Los descuentos por pronto pagos en las facturas de venta generan nota de credito
		if (isReceipt() && getC_Invoice_ID()>0 && getDiscountAmt().compareTo(Env.ZERO)>0) {
			setCreditMemo(getDiscountAmt());
//			setDiscountAmt(Env.ZERO);
		}

		if(StringUtils.isEmpty(getType())) {
			setType(TYPE_Payment);
		}
		

		if(newRecord && getRate().compareTo(Env.ZERO)<=0 && getC_Currency_ID()!=Env.getC_Currency_ID(getCtx()) ) {
			setRate (MConversionRate.getRate(getCtx(), -1, getC_Currency_ID(), new java.sql.Timestamp(TimeUtil.getToday().getTimeInMillis()).getTime()));
		}

		return true;
	} // beforeSave

	/**
	 * After Save
	 *
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return saved
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (success && newRecord) {
			MEXMEPolicyInfo policyInfo = new MEXMEPolicyInfo(getCtx(), 0, null);
			policyInfo.setAD_Table_ID(Table_ID);
			policyInfo.setRecord_ID(getC_Payment_ID());
			policyInfo.setDateTrx(getDateAcct());
			policyInfo.setC_BankAccount_ID(getC_BankAccount_ID());
			policyInfo.setDocNo(getCheckNo());
			policyInfo.setC_BPartner_ID(getC_BPartner_ID());
			policyInfo.setPolicyNo(getC_Payment_ID());
			policyInfo.setAmount(getPayAmt());
			policyInfo.setName(getC_BPartner().getName());
			policyInfo.setC_Currency_ID(getC_Currency_ID());
			policyInfo.setCurrencyRate(getRate());

			I_C_DocType docType = new MDocType(getCtx(), getC_DocType_ID(), null);

			I_GL_Category glCategory = docType.getGL_Category();

			if (X_GL_Category.POLICYTYPE_Journal.equals(glCategory.getPolicyType())) {
				policyInfo.setAuxType(MEXMEPolicyInfo.AUXTYPE_Invoice);
			} else {
				if (PAYMENTTYPE_Check.equals(getPaymentType())) {
					policyInfo.setAuxType(MEXMEPolicyInfo.AUXTYPE_Check);
				} else {
					policyInfo.setAuxType(MEXMEPolicyInfo.AUXTYPE_Transfer);
				}
			}

			policyInfo.setGL_Category_ID(glCategory.getGL_Category_ID());
			policyInfo.setPolicyType(glCategory.getPolicyType());

			if (!policyInfo.save(get_TrxName())) {
				return false;
			}
		}
		return success;
	} // afterSave

	/**
	 * Get Allocated Amt in Payment Currency
	 *
	 * @return amount or ZERO
	 */
	public BigDecimal getAmtAllocated() {
		BigDecimal aplicado = getAllocatedAmt();
		if (aplicado == null) {
			aplicado = BigDecimal.ZERO;
		}
		return aplicado;
	}

	public BigDecimal getBalanceAmt() {
		if (balanceAmt == null && getPayAmt() != null) {
			balanceAmt = getPayAmt().add(getAmtAllocated());
		} else if (balanceAmt == null) {
			return BigDecimal.ZERO;
		}
		return balanceAmt;
	}


	/**
	 * Get Allocated Amt in Payment Currency
	 * Convertir a pesos
	 * @return amount or null
	 */
	public BigDecimal getAllocatedAmt() {
		if (getC_Charge_ID() != 0) {
			return getPayAmt();
		}
		//
		final StringBuilder sql = new StringBuilder();
//		if( getC_Currency_ID() == Env.getC_Currency_ID(getCtx()) ){
			sql.append("SELECT SUM(currencyConvert( ");
//		} else {
//			sql.append("SELECT SUM(currencyConvertInvoice( ");
//		}
		sql.append("   al.Amount ");
		sql.append(" , ah.C_Currency_ID      ");
		sql.append(" , p.C_Currency_ID       ");
		sql.append(" , ah.DateTrx            ");
		sql.append(" , p.C_ConversionType_ID ");
		sql.append(" , al.AD_Client_ID ");
		sql.append(" , al.AD_Org_ID ");
		sql.append( /*getC_Currency_ID() == Env.getC_Currency_ID(getCtx())*/ " )) ");//:" , al.Rate )) ");
		sql.append(" FROM C_AllocationLine al ");
		sql.append(" INNER JOIN C_AllocationHdr ah ON al.C_AllocationHdr_ID=ah.C_AllocationHdr_ID ");
		sql.append(" INNER JOIN C_Payment        p ON al.C_Payment_ID      =p.C_Payment_ID ");
		sql.append(" WHERE al.C_Payment_ID = ? ");
		sql.append(" AND   ah.IsActive='Y' ");
		sql.append(" AND   al.IsActive='Y' ");
		final BigDecimal retValue = DB.getSQLValueBD(get_TrxName(), sql.toString(), getC_Payment_ID());
		return retValue;
	} // getAllocatedAmt

	/**
	 * Test Allocation (and set allocated flag)
	 *
	 * @return true if updated
	 */
	public boolean testAllocation() {
		// Cash Trx always allocated
		if (isCashTrx()) {
			if (!isAllocated()) {
				setIsAllocated(true);
				return true;
			}
			return false;
		}
		//
		BigDecimal alloc = getAllocatedAmt();//-480
		if (alloc == null)
			alloc = Env.ZERO;

		BigDecimal total = getPayAmt();//40
		if(getRate().compareTo(Env.ZERO)>0 && getC_Currency_ID() != Env.getC_Currency_ID(getCtx()))
			total = getPayAmt().multiply(getRate());

		if (!isReceipt())
			total = total.negate();
		boolean test = total.compareTo(alloc) == 0;//false
		boolean change = test != isAllocated();// false
		if (change)
			setIsAllocated(test);
		log.fine("Allocated=" + test + " (" + alloc + "=" + total + ")");
		return change;
	} // testAllocation

	/**
	 * Set Allocated Flag for payments
	 *
	 * @param ctx
	 *            context
	 * @param C_BPartner_ID
	 *            if 0 all
	 * @param trxName
	 *            trx
	 */
	public static void setIsAllocated(Properties ctx, int C_BPartner_ID, String trxName) {
		int counter = 0;
		String sql = "SELECT * FROM C_Payment " + "WHERE IsAllocated='N' AND DocStatus IN ('CO','CL')";
		if (C_BPartner_ID > 1)
			sql += " AND C_BPartner_ID=?";
		else
			sql += " AND AD_Client_ID=" + Env.getAD_Client_ID(ctx);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, trxName);
			if (C_BPartner_ID > 1)
				pstmt.setInt(1, C_BPartner_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MPayment pay = new MPayment(ctx, rs, trxName);
				if (pay.testAllocation())
					if (pay.save())
						counter++;
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		s_log.config("#" + counter);
	} // setIsAllocated

	/**************************************************************************
	 * Set Error Message
	 *
	 * @param errorMessage
	 *            error message
	 */
	public void setErrorMessage(String errorMessage) {
		m_errorMessage = errorMessage;
	} // setErrorMessage

	/**
	 * Get Error Message
	 *
	 * @return error message
	 */
	public String getErrorMessage() {
		return m_errorMessage;
	} // getErrorMessage

	/**
	 * Set Bank Account for Payment.
	 *
	 * @param C_BankAccount_ID
	 *            C_BankAccount_ID
	 */
	public void setC_BankAccount_ID(int C_BankAccount_ID) {
		if (C_BankAccount_ID == 0) {
			setPaymentProcessor();
			if (getC_BankAccount_ID() == 0)
				throw new IllegalArgumentException("Can't find Bank Account");
		} else
			super.setC_BankAccount_ID(C_BankAccount_ID);
	} // setC_BankAccount_ID

	/**
	 * Set BankAccount and PaymentProcessor
	 *
	 * @return true if found
	 */
	public boolean setPaymentProcessor() {
		return setPaymentProcessor(getTenderType(), getCreditCardType());
	} // setPaymentProcessor

	/**
	 * Set BankAccount and PaymentProcessor
	 *
	 * @param tender
	 *            TenderType see TENDER_
	 * @param CCType
	 *            CC Type see CC_
	 * @return true if found
	 */
	public boolean setPaymentProcessor(String tender, String CCType) {
		m_mPaymentProcessor = null;
		// Get Processor List
		if (m_mPaymentProcessors == null || m_mPaymentProcessors.length == 0)
			m_mPaymentProcessors = MPaymentProcessor
					.find(getCtx(), tender, CCType, getAD_Client_ID(), getC_Currency_ID(), getPayAmt(), get_TrxName());
		// Relax Amount
		if (m_mPaymentProcessors == null || m_mPaymentProcessors.length == 0)
			m_mPaymentProcessors = MPaymentProcessor.find(getCtx(), tender, CCType, getAD_Client_ID(), getC_Currency_ID(), Env.ZERO, get_TrxName());
		if (m_mPaymentProcessors == null || m_mPaymentProcessors.length == 0)
			return false;

		// Find the first right one
		for (int i = 0; i < m_mPaymentProcessors.length; i++) {
			if (m_mPaymentProcessors[i].accepts(tender, CCType)) {
				m_mPaymentProcessor = m_mPaymentProcessors[i];
			}
		}
		if (m_mPaymentProcessor != null)
			setC_BankAccount_ID(m_mPaymentProcessor.getC_BankAccount_ID());
		//
		return m_mPaymentProcessor != null;
	} // setPaymentProcessor

	/**
	 * Get Accepted Credit Cards for PayAmt (default 0)
	 *
	 * @return credit cards
	 */
	public ValueNamePair[] getCreditCards() {
		return getCreditCards(getPayAmt());
	} // getCreditCards

	/**
	 * Get Accepted Credit Cards for amount
	 *
	 * @param amt
	 *            trx amount
	 * @return credit cards
	 */
	public ValueNamePair[] getCreditCards(BigDecimal amt) {
		try {
			if (m_mPaymentProcessors == null || m_mPaymentProcessors.length == 0)
				m_mPaymentProcessors = MPaymentProcessor.find(getCtx(), null, null, getAD_Client_ID(), getC_Currency_ID(), amt, get_TrxName());
			//
			HashMap<String, ValueNamePair> map = new HashMap<String, ValueNamePair>(); // to
																						// eliminate
																						// duplicates
			for (int i = 0; i < m_mPaymentProcessors.length; i++) {
				if (m_mPaymentProcessors[i].isAcceptAMEX())
					map.put(CREDITCARDTYPE_Amex, getCreditCardPair(CREDITCARDTYPE_Amex));
				if (m_mPaymentProcessors[i].isAcceptDiners())
					map.put(CREDITCARDTYPE_Diners, getCreditCardPair(CREDITCARDTYPE_Diners));
				if (m_mPaymentProcessors[i].isAcceptDiscover())
					map.put(CREDITCARDTYPE_Discover, getCreditCardPair(CREDITCARDTYPE_Discover));
				if (m_mPaymentProcessors[i].isAcceptMC())
					map.put(CREDITCARDTYPE_MasterCard, getCreditCardPair(CREDITCARDTYPE_MasterCard));
				if (m_mPaymentProcessors[i].isAcceptCorporate())
					map.put(CREDITCARDTYPE_PurchaseCard, getCreditCardPair(CREDITCARDTYPE_PurchaseCard));
				if (m_mPaymentProcessors[i].isAcceptVisa())
					map.put(CREDITCARDTYPE_Visa, getCreditCardPair(CREDITCARDTYPE_Visa));
			} // for all payment processors
				//
			ValueNamePair[] retValue = new ValueNamePair[map.size()];
			map.values().toArray(retValue);
			log.fine("getCreditCards - #" + retValue.length + " - Processors=" + m_mPaymentProcessors.length);
			return retValue;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	} // getCreditCards

	/**
	 * Get Type and name pair
	 *
	 * @param CreditCardType
	 *            credit card Type
	 * @return pair
	 */
	private ValueNamePair getCreditCardPair(String CreditCardType) {
		return new ValueNamePair(CreditCardType, getCreditCardName(CreditCardType));
	} // getCreditCardPair

	/**************************************************************************
	 * Credit Card Number
	 *
	 * @param CreditCardNumber
	 *            CreditCard Number
	 */
	public void setCreditCardNumber(String CreditCardNumber) {
		super.setCreditCardNumber(MPaymentValidate.checkNumeric(CreditCardNumber));
	} // setCreditCardNumber

	/**
	 * Verification Code
	 *
	 * @param newCreditCardVV
	 *            CC verification
	 */
	public void setCreditCardVV(String newCreditCardVV) {
		super.setCreditCardVV(MPaymentValidate.checkNumeric(newCreditCardVV));
	} // setCreditCardVV

	/**
	 * Two Digit CreditCard MM
	 *
	 * @param CreditCardExpMM
	 *            Exp month
	 */
	public void setCreditCardExpMM(int CreditCardExpMM) {
		if (CreditCardExpMM < 1 || CreditCardExpMM > 12)
			;
		else
			super.setCreditCardExpMM(CreditCardExpMM);
	} // setCreditCardExpMM

	/**
	 * Two digit CreditCard YY (til 2020)
	 *
	 * @param newCreditCardExpYY
	 *            2 or 4 digit year
	 */
	public void setCreditCardExpYY(int newCreditCardExpYY) {
		int CreditCardExpYY = newCreditCardExpYY;
		if (newCreditCardExpYY > 1999)
			CreditCardExpYY = newCreditCardExpYY - 2000;
		super.setCreditCardExpYY(CreditCardExpYY);
	} // setCreditCardExpYY

	/**
	 * CreditCard Exp MMYY
	 *
	 * @param mmyy
	 *            Exp in form of mmyy
	 * @return true if valid
	 */
	public boolean setCreditCardExp(String mmyy) {
		if (MPaymentValidate.validateCreditCardExp(mmyy).length() != 0)
			return false;
		//
		String exp = MPaymentValidate.checkNumeric(mmyy);
		String mmStr = exp.substring(0, 2);
		String yyStr = exp.substring(2, 4);
		setCreditCardExpMM(Integer.parseInt(mmStr));
		setCreditCardExpYY(Integer.parseInt(yyStr));
		return true;
	} // setCreditCardExp

	/**
	 * CreditCard Exp MMYY
	 *
	 * @param delimiter
	 *            / - or null
	 * @return Exp
	 */
	public String getCreditCardExp(String delimiter) {
		String mm = String.valueOf(getCreditCardExpMM());
		String yy = String.valueOf(getCreditCardExpYY());

		StringBuffer retValue = new StringBuffer();
		if (mm.length() == 1)
			retValue.append("0");
		retValue.append(mm);
		//
		if (delimiter != null)
			retValue.append(delimiter);
		//
		if (yy.length() == 1)
			retValue.append("0");
		retValue.append(yy);
		//
		return (retValue.toString());
	} // getCreditCardExp

	/**
	 * MICR
	 *
	 * @param MICR
	 *            MICR
	 */
	public void setMicr(String MICR) {
		super.setMicr(MPaymentValidate.checkNumeric(MICR));
	} // setBankMICR

	/**
	 * Routing No
	 *
	 * @param RoutingNo
	 *            Routing No
	 */
	public void setRoutingNo(String RoutingNo) {
		super.setRoutingNo(MPaymentValidate.checkNumeric(RoutingNo));
	} // setBankRoutingNo

	/**
	 * Bank Account No
	 *
	 * @param AccountNo
	 *            AccountNo
	 */
	public void setAccountNo(String AccountNo) {
		super.setAccountNo(MPaymentValidate.checkNumeric(AccountNo));
	} // setBankAccountNo

	/**
	 * Check No
	 *
	 * @param CheckNo
	 *            Check No
	 */
	public void setCheckNo(String CheckNo) {
		super.setCheckNo(MPaymentValidate.checkNumeric(CheckNo));
	} // setBankCheckNo

	/**
	 * Set DocumentNo to Payment info. If there is a R_PnRef that is set
	 * automatically
	 */
	private void setDocumentNo() {
		// Cash Transfer
		if (TENDERTYPE_Cash.equals(getTenderType()))
			return;
		// Current Document No
		String documentNo = getDocumentNo();
		// Existing reversal
		if (documentNo != null && documentNo.indexOf(REVERSE_INDICATOR) >= 0)
			return;

		// If external number exists - enforce it
		if (getR_PnRef() != null && getR_PnRef().length() > 0) {
			if (!getR_PnRef().equals(documentNo))
				setDocumentNo(getR_PnRef());
			return;
		}

		documentNo = "";
		// Credit Card
		if (TENDERTYPE_CreditCard.equals(getTenderType())) {
			documentNo = getCreditCardType() + " " + Obscure.obscure(getCreditCardNumber()) + " " + getCreditCardExpMM() + "/" + getCreditCardExpYY();
		}
		// Own Check No
		else if (TENDERTYPE_Check.equals(getTenderType()) && !isReceipt() && getCheckNo() != null && getCheckNo().length() > 0) {
			documentNo = getCheckNo();
		}
		// Customer Check: Routing: Account #Check
		else if (TENDERTYPE_Check.equals(getTenderType()) && isReceipt()) {
			if (getRoutingNo() != null)
				documentNo = getRoutingNo() + ": ";
			if (getAccountNo() != null)
				documentNo += getAccountNo();
			if (getCheckNo() != null) {
				if (documentNo.length() > 0)
					documentNo += " ";
				documentNo += "#" + getCheckNo();
			}
		}

		// Set Document No
		documentNo = documentNo.trim();
		if (documentNo.length() > 0)
			setDocumentNo(documentNo);
	} // setDocumentNo

	/**
	 * Set Refernce No (and Document No)
	 *
	 * @param R_PnRef
	 *            reference
	 */
	public void setR_PnRef(String R_PnRef) {
		super.setR_PnRef(R_PnRef);
		if (R_PnRef != null)
			setDocumentNo(R_PnRef);
	} // setR_PnRef

	// ---------------

	/**
	 * Set Payment Amount
	 *
	 * @param PayAmt
	 *            Pay Amt
	 */
	public void setPayAmt(BigDecimal PayAmt) {
		super.setPayAmt(PayAmt == null ? Env.ZERO : PayAmt);
	} // setPayAmt

	/**
	 * Set Payment Amount
	 *
	 * @param C_Currency_ID
	 *            currency
	 * @param payAmt
	 *            amount
	 */
	public void setAmount(int C_Currency_ID, BigDecimal payAmt) {
		if (C_Currency_ID == 0)
			C_Currency_ID = MClient.get(getCtx()).getC_Currency_ID();
		setC_Currency_ID(C_Currency_ID);
		setPayAmt(payAmt);
	} // setAmount

	/**
	 * Discount Amt
	 *
	 * @param DiscountAmt
	 *            Discount
	 */
	public void setDiscountAmt(BigDecimal DiscountAmt) {
		super.setDiscountAmt(DiscountAmt == null ? Env.ZERO : DiscountAmt);
	} // setDiscountAmt

	/**
	 * WriteOff Amt
	 *
	 * @param WriteOffAmt
	 *            WriteOff
	 */
	public void setWriteOffAmt(BigDecimal WriteOffAmt) {
		super.setWriteOffAmt(WriteOffAmt == null ? Env.ZERO : WriteOffAmt);
	} // setWriteOffAmt

	/**
	 * OverUnder Amt
	 *
	 * @param OverUnderAmt
	 *            OverUnder
	 */
	public void setOverUnderAmt(BigDecimal OverUnderAmt) {
		super.setOverUnderAmt(OverUnderAmt == null ? Env.ZERO : OverUnderAmt);
		setIsOverUnderPayment(getOverUnderAmt().compareTo(Env.ZERO) != 0);
	} // setOverUnderAmt

	/**
	 * Tax Amt
	 *
	 * @param TaxAmt
	 *            Tax
	 */
	public void setTaxAmt(BigDecimal TaxAmt) {
		super.setTaxAmt(TaxAmt == null ? Env.ZERO : TaxAmt);
	} // setTaxAmt

	/**
	 * Set Info from BP Bank Account
	 *
	 * @param ba
	 *            BP bank account
	 */
	public void setBP_BankAccount(MBPBankAccount ba) {
		log.fine("" + ba);
		if (ba == null)
			return;
		setC_BPartner_ID(ba.getC_BPartner_ID());
		setAccountAddress(ba.getA_Name(), ba.getA_Street(), ba.getA_City(), ba.getA_State(), ba.getA_Zip(), ba.getA_Country());
		setA_EMail(ba.getA_EMail());
		setA_Ident_DL(ba.getA_Ident_DL());
		setA_Ident_SSN(ba.getA_Ident_SSN());
		// CC
		if (ba.getCreditCardType() != null)
			setCreditCardType(ba.getCreditCardType());
		if (ba.getCreditCardNumber() != null)
			setCreditCardNumber(ba.getCreditCardNumber());
		if (ba.getCreditCardExpMM() != 0)
			setCreditCardExpMM(ba.getCreditCardExpMM());
		if (ba.getCreditCardExpYY() != 0)
			setCreditCardExpYY(ba.getCreditCardExpYY());
		if (ba.getCreditCardVV() != null)
			setCreditCardVV(ba.getCreditCardVV());
		// Bank
		if (ba.getAccountNo() != null)
			setAccountNo(ba.getAccountNo());
		if (ba.getRoutingNo() != null)
			setRoutingNo(ba.getRoutingNo());
	} // setBP_BankAccount

	/**
	 * Save Info from BP Bank Account
	 *
	 * @param ba
	 *            BP bank account
	 * @return true if saved
	 */
	public boolean saveToBP_BankAccount(MBPBankAccount ba) {
		if (ba == null)
			return false;
		ba.setA_Name(getA_Name());
		ba.setA_Street(getA_Street());
		ba.setA_City(getA_City());
		ba.setA_State(getA_State());
		ba.setA_Zip(getA_Zip());
		ba.setA_Country(getA_Country());
		ba.setA_EMail(getA_EMail());
		ba.setA_Ident_DL(getA_Ident_DL());
		ba.setA_Ident_SSN(getA_Ident_SSN());
		// CC
		ba.setCreditCardType(getCreditCardType());
		ba.setCreditCardNumber(getCreditCardNumber());
		ba.setCreditCardExpMM(getCreditCardExpMM());
		ba.setCreditCardExpYY(getCreditCardExpYY());
		ba.setCreditCardVV(getCreditCardVV());
		// Bank
		if (getAccountNo() != null)
			ba.setAccountNo(getAccountNo());
		if (getRoutingNo() != null)
			ba.setRoutingNo(getRoutingNo());
		// Trx
		ba.setR_AvsAddr(getR_AvsAddr());
		ba.setR_AvsZip(getR_AvsZip());
		//
		boolean ok = ba.save(get_TrxName());
		log.fine("saveToBP_BankAccount - " + ba);
		return ok;
	} // setBP_BankAccount

	/**
	 * Set Doc Type bases on IsReceipt
	 */
	private void setC_DocType_ID() {
		setC_DocType_ID(isReceipt());
	} // setC_DocType_ID

	/**
	 * Set Doc Type
	 *
	 * @param isReceipt
	 *            is receipt
	 */
	public void setC_DocType_ID(boolean isReceipt) {
		setIsReceipt(isReceipt);
		String sql = "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=? AND DocBaseType=? ORDER BY IsDefault DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Client_ID());
			if (isReceipt)
				pstmt.setString(2, MDocType.DOCBASETYPE_ARReceipt);
			else
				pstmt.setString(2, MDocType.DOCBASETYPE_APPayment);
			rs = pstmt.executeQuery();
			if (rs.next())
				setC_DocType_ID(rs.getInt(1));
			else
				log.warning("setDocType - NOT found - isReceipt=" + isReceipt);

		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	} // setC_DocType_ID

	/**
	 * Set Document Type
	 *
	 * @param C_DocType_ID
	 *            doc type
	 */
	public void setC_DocType_ID(int C_DocType_ID) {
		// if (getDocumentNo() != null && getC_DocType_ID() != C_DocType_ID)
		// setDocumentNo(null);
		super.setC_DocType_ID(C_DocType_ID);
	} // setC_DocType_ID

	/**
	 * Verify Document Type with Invoice
	 *
	 * @return true if ok
	 */
	private boolean verifyDocType() {
		if (getC_DocType_ID() == 0)
			return false;
		//
		Boolean invoiceSO = null;
		// Check Invoice First
		if (getC_Invoice_ID() > 0) {
			String sql = "SELECT idt.IsSOTrx "
					+ "FROM C_Invoice i"
					+ " INNER JOIN C_DocType idt ON (i.C_DocType_ID=idt.C_DocType_ID) "
					+ "WHERE i.C_Invoice_ID=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, getC_Invoice_ID());
				rs = pstmt.executeQuery();
				if (rs.next())
					invoiceSO = new Boolean("Y".equals(rs.getString(1)));

			} catch (Exception e) {
				log.log(Level.SEVERE, sql, e);
			} finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
		} // Invoice

		// DocumentType
		Boolean paymentSO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT IsSOTrx " + "FROM C_DocType " + "WHERE C_DocType_ID=?";
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_DocType_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
				paymentSO = new Boolean("Y".equals(rs.getString(1)));
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		// No Payment info
		if (paymentSO == null)
			return false;
		setIsReceipt(paymentSO.booleanValue());

		// We have an Invoice .. and it does not match
		if (invoiceSO != null && invoiceSO.booleanValue() != paymentSO.booleanValue())
			return false;
		// OK
		return true;
	} // verifyDocType

	/**
	 * Verify Payment Allocate is ignored (must not exists) if the payment
	 * header has charge/invoice/order
	 *
	 * @param pAllocs
	 * @return true if ok
	 */
	@SuppressWarnings("unused")
	private boolean verifyPaymentAllocateVsHeader(MPaymentAllocate[] pAllocs) {
		if (pAllocs.length > 0) {
			if (getC_Charge_ID() > 0 || getC_Invoice_ID() > 0 || getC_Order_ID() > 0)
				return false;
		}
		return true;
	}

	/**
	 * Verify Payment Allocate Sum must be equal to the Payment Amount
	 *
	 * @param pAllocs
	 * @return true if ok
	 */
	@SuppressWarnings("unused")
	private boolean verifyPaymentAllocateSum(MPaymentAllocate[] pAllocs) {
		BigDecimal sumPaymentAllocates = Env.ZERO;
		if (pAllocs.length > 0) {
			for (MPaymentAllocate pAlloc : pAllocs)
				sumPaymentAllocates = sumPaymentAllocates.add(pAlloc.getAmount());
			if (getPayAmt().compareTo(sumPaymentAllocates) != 0)
				return false;
		}
		return true;
	}

	/**
	 * Get ISO Code of Currency
	 *
	 * @return Currency ISO
	 */
	public String getCurrencyISO() {
		return MCurrency.getISO_Code(getCtx(), getC_Currency_ID());
	} // getCurrencyISO

	/**
	 * Get Document Status
	 *
	 * @return Document Status Clear Text
	 */
	public String getDocStatusName() {
		return MRefList.getListName(getCtx(), 131, getDocStatus());
	} // getDocStatusName

	/**
	 * Get Name of Credit Card
	 *
	 * @return Name
	 */
	public String getCreditCardName() {
		return getCreditCardName(getCreditCardType());
	} // getCreditCardName

	/**
	 * Get Name of Credit Card
	 *
	 * @param CreditCardType
	 *            credit card type
	 * @return Name
	 */
	public String getCreditCardName(String CreditCardType) {
		if (CreditCardType == null)
			return "--";
		else if (CREDITCARDTYPE_MasterCard.equals(CreditCardType))
			return "MasterCard";
		else if (CREDITCARDTYPE_Visa.equals(CreditCardType))
			return "Visa";
		else if (CREDITCARDTYPE_Amex.equals(CreditCardType))
			return "Amex";
		else if (CREDITCARDTYPE_ATM.equals(CreditCardType))
			return "ATM";
		else if (CREDITCARDTYPE_Diners.equals(CreditCardType))
			return "Diners";
		else if (CREDITCARDTYPE_Discover.equals(CreditCardType))
			return "Discover";
		else if (CREDITCARDTYPE_PurchaseCard.equals(CreditCardType))
			return "PurchaseCard";
		return "?" + CreditCardType + "?";
	} // getCreditCardName

	/**
	 * Add to Description
	 *
	 * @param description
	 *            text
	 */
	public void addDescription(String description) {
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	} // addDescription

	/**
	 * Get Pay Amt
	 *
	 * @param absolute
	 *            if true the absolute amount (i.e. negative if payment)
	 * @return amount
	 */
	public BigDecimal getPayAmt(boolean absolute) {
		if (isReceipt())
			return super.getPayAmt();
		return super.getPayAmt().negate();
	} // getPayAmt

	/**
	 * Get Pay Amt in cents
	 *
	 * @return amount in cents
	 */
	public int getPayAmtInCents() {
		BigDecimal bd = super.getPayAmt().multiply(Env.ONEHUNDRED);
		return bd.intValue();
	} // getPayAmtInCents

	/**************************************************************************
	 * Process document
	 *
	 * @param processAction
	 *            document action
	 * @return true if performed
	 */
	public boolean processIt(String processAction) {
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());
	} // process

	/** Process Message */
	private String	m_processMsg	= null;
	/** Just Prepared Flag */
	private boolean	m_justPrepared	= false;

	/**
	 * Unlock Document.
	 *
	 * @return true if success
	 */
	public boolean unlockIt() {
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	} // unlockIt

	/**
	 * Invalidate Document
	 *
	 * @return true if success
	 */
	public boolean invalidateIt() {
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	} // invalidateIt

	/**************************************************************************
	 * Prepare Document
	 *
	 * @return new status (In Progress or Invalid)
	 */
	public String prepareIt() {
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		// Std Period open?
		// if (!MPeriod.isOpen(getCtx(), getDateAcct(),
		// isReceipt() ? MDocType.DOCBASETYPE_ARReceipt :
		// MDocType.DOCBASETYPE_APPayment))
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), isReceipt() ? MDocType.DOCBASETYPE_ARReceipt : MDocType.DOCBASETYPE_APPayment, getAD_Org_ID()))

		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		// Los descuentos por pronto pagos en las facturas de venta generan nota de credito
		if (isReceipt() && getC_Invoice_ID()>0 && getDiscountAmt().compareTo(Env.ZERO)>0) {
			setCreditMemo(getDiscountAmt());
			setDiscountAmt(Env.ZERO);// Se vuelve cero para que no siga el proceso normal de descuento
		}

		// Unsuccessful Online Payment
		if (isOnline() && !isApproved()) {
			if (getR_Result() != null)
				m_processMsg = "@OnlinePaymentFailed@";
			else
				m_processMsg = "@PaymentNotProcessed@";
			return DocAction.STATUS_Invalid;
		}

		// Waiting Payment - Need to create Invoice & Shipment
		if (getC_Order_ID() != 0 && getC_Invoice_ID() == 0) { // see
																// WebOrder.process
			MOrder order = new MOrder(getCtx(), getC_Order_ID(), get_TrxName());
			if (DOCSTATUS_WaitingPayment.equals(order.getDocStatus())) {
				order.setC_Payment_ID(getC_Payment_ID());
				order.setDocAction(MOrder.DOCACTION_WaitComplete);
				order.set_TrxName(get_TrxName());
				// boolean ok =
				order.processIt(MOrder.DOCACTION_WaitComplete);
				m_processMsg = order.getProcessMsg();
				order.save(get_TrxName());
				// Set Invoice
				MInvoice[] invoices = order.getInvoices();
				int length = invoices.length;
				if (length > 0) // get last invoice
					setC_Invoice_ID(invoices[length - 1].getC_Invoice_ID());
				//
				if (getC_Invoice_ID() == 0) {
					m_processMsg = "@NotFound@ @C_Invoice_ID@";
					return DocAction.STATUS_Invalid;
				}
			} // WaitingPayment
		}

		// Consistency of Invoice / Document Type and IsReceipt
		if (!verifyDocType()) {
			m_processMsg = "@PaymentDocTypeInvoiceInconsistent@";
			return DocAction.STATUS_Invalid;
		}

		// Do not pay when Credit Stop/Hold
		if (!isReceipt()) {
			MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			if (MBPartner.SOCREDITSTATUS_CreditStop.equals(bp.getSOCreditStatus())) {
				m_processMsg = "@BPartnerCreditStop@ - @TotalOpenBalance@="
						+ bp.getTotalOpenBalance()
						+ ", @SO_CreditLimit@="
						+ bp.getSO_CreditLimit();
				return DocAction.STATUS_Invalid;
			}
			if (MBPartner.SOCREDITSTATUS_CreditHold.equals(bp.getSOCreditStatus())) {
				m_processMsg = "@BPartnerCreditHold@ - @TotalOpenBalance@="
						+ bp.getTotalOpenBalance()
						+ ", @SO_CreditLimit@="
						+ bp.getSO_CreditLimit();
				return DocAction.STATUS_Invalid;
			}
		}

		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/**
	 * Approve Document
	 *
	 * @return true if success
	 */
	public boolean approveIt() {
		log.info(toString());
		setIsApproved(true);
		return true;
	} // approveIt

	/**
	 * Reject Approval
	 *
	 * @return true if success
	 */
	public boolean rejectIt() {
		log.info(toString());
		setIsApproved(false);
		return true;
	} // rejectIt

	/**************************************************************************
	 * Complete Document
	 *
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt() {

		// Los descuentos por pronto pagos en las facturas de venta generan nota de credito
		if (isReceipt() && getC_Invoice_ID()>0 && getDiscountAmt().compareTo(Env.ZERO)>0) {
			setCreditMemo(getDiscountAmt());
			setDiscountAmt(Env.ZERO);// Se vuelve cero para que no siga el proceso normal de descuento
		}

		/*
		 * Control Presupuestal
		 */
//		MAcctSchema as = MClient.get(getCtx(), getAD_Client_ID()).getAcctSchema();

		/*
		 * Si commitmentType es diferente de Nulo existe CP y en todos sus
		 * niveles este controla los pagos
		 */
//		if (as != null) {
//			if (!as.getCommitmentType().equals(X_C_AcctSchema.COMMITMENTTYPE_None) && !isReceipt()) {
//				SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
//				SimpleDateFormat simpleMonth = new SimpleDateFormat("MM");
//				MGL_Budget budget = MGL_Budget.getBudgetByYear(Integer.valueOf(simpleDateformat.format(getDateAcct())));
//				if (budget == null) {
//					// FIXME: Revisar cuando el Tipo de Compromiso != Ninguno y
//					// no hay prespuesto activo para el a�o del documento
//					/*
//					 * Si no existe presupuesto y estaba establecido algun tipo
//					 * de compromiso deberia de marcar error y lanzar una
//					 * leyenda de que necesita darse de alta el presupuesto
//					 * realista Sin embargo algunos clientes necesitan postear
//					 * para llevar su contabilidad y en algunos casos el posteo
//					 * solo se llevo a cabo dependiendo del tipo de compromiso
//					 * Si no hay presupuesto en este caso se dejan las cosas
//					 * como estaban.
//					 */
//				} else {
//
//					MEXME_GL_BudgetPa budgetPa = MEXME_GL_BudgetPa.getBudgetPa(
//							budget.getGL_Budget_ID(), MPayment.Table_Name, this.getC_BankAccount_ID(), as.get_ID());
//					if (budgetPa == null) {
//						// FIXME: Si el presupuesto no cuenta con la partida
//						// ligada a los productos
//						/*
//						 * Si no existe presupuesto y estaba establecido algun
//						 * tipo de compromiso deberia de marcar error y lanzar
//						 * una leyenda de que necesita darse de alta el
//						 * presupuesto realista Sin embargo algunos clientes
//						 * necesitan postear para llevar su contabilidad y en
//						 * algunos casos el posteo solo se llevo a cabo
//						 * dependiendo del tipo de compromiso Si no hay
//						 * presupuesto en este caso se dejan las cosas como
//						 * estaban.
//						 */
//					} else {
//						MEXME_GL_BudgetPaPe budgetPaPe = MEXME_GL_BudgetPaPe.getBudgetPaPe(
//								budgetPa.getEXME_GL_BudgetPa_ID(), Integer.valueOf(simpleMonth.format(getDateAcct())));
//
//						boolean flag = false;
//						if (budgetPa.getLines(budgetPa.getEXME_GL_BudgetPa_ID()).length > 0)
//							flag = true;
//
//						boolean valido = false;
//
//						if (budgetPaPe == null)
//							valido = (budget.getPre_Ejercido().doubleValue() + getPayAmt().doubleValue()) <= budget.getPre_Autorizado().doubleValue()
//									&& (budgetPa.getPre_Ejercido().doubleValue() + getPayAmt().doubleValue()) <= budgetPa.getPre_Autorizado()
//											.doubleValue();
//
//						else
//							valido = (budget.getPre_Ejercido().doubleValue() + getPayAmt().doubleValue()) <= budget.getPre_Autorizado().doubleValue()
//									&& (budgetPa.getPre_Ejercido().doubleValue() + getPayAmt().doubleValue()) <= budgetPa.getPre_Autorizado()
//											.doubleValue()
//									&& (budgetPaPe.getPre_Ejercido().doubleValue() + getPayAmt().doubleValue() <= budgetPaPe.getPre_Autorizado()
//											.doubleValue());
//
//						if (!valido) {
//							String partida;
//							try {
//								partida = MEXMEPartida.getPartida(getCtx(), budgetPa.getEXME_GL_BudgetPa_ID());
//							} catch (Exception e) {
//								partida = "";
//							}
//
//							m_errorMessage = "control.presupuestal.validain";
//							return "ErrPresupuesto-" + partida;
//
//						} else {
//
//							BigDecimal acum = BigDecimal.ZERO;
//							MPaySelectionLine[] lines = MPaySelectionLine.get(
//									MPaySelectionCheck.getOfPayment(Env.getCtx(), getC_Payment_ID(), get_TrxName()).getC_PaySelectionCheck_ID(),
//									get_TrxName());
//							for (int i = 0; i < lines.length; i++) {
//								MInvoice invoice = new MInvoice(Env.getCtx(), lines[i].getC_Invoice_ID(), get_TrxName());
//								if (invoice.getC_Campaign_ID() > 0) {
//									acum = acum.add(invoice.getTotalLines());
//									valido = MCampaign.checkCampaings(
//											acum, invoice.getC_Campaign_ID(), MPayment.Table_Name, get_TrxName(), budgetPa.getEXME_Partida_ID());
//								} else
//									// FIXME: debo rechazar un pago porque no
//									// tiene campaña?
//									valido = false;
//								if (!valido) {
//									break;
//								}
//							}
//
//							if (valido) {
//
//								budget.setPre_Ejercido(new BigDecimal(budget.getPre_Ejercido().doubleValue() + getPayAmt().doubleValue()));
//								budgetPa.setPre_Ejercido(new BigDecimal(budgetPa.getPre_Ejercido().doubleValue() + getPayAmt().doubleValue()));
//								if (flag)
//									budgetPaPe
//											.setPre_Ejercido(new BigDecimal(budgetPaPe.getPre_Ejercido().doubleValue() + getPayAmt().doubleValue()));
//								// SAVE
//								budget.save();
//								budgetPa.save();
//								if (flag)
//									budgetPaPe.save();
//							} else {
//								String programa;
//								try {
//									MCampaign mc = new MCampaign(Env.getCtx(), getC_Campaign_ID(), null);
//									programa = mc.getName();
//								} catch (Exception e) {
//									programa = "";
//								}
//								m_errorMessage = "control.presupuestal.programa.validain";
//								return "ErrPrograma-" + programa;
//							}
//						}
//					}
//
//				}
//			}
//		}

		// Re-Check
		if (!m_justPrepared) {
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		// Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());

		// Notas de credito
		lstCrMemo.clear();

		// Nota de credito por pronto pago (SOLO DEBERIA ENTRAR UNA VEZ)
		if(getC_Invoice_ID()>0 && getCreditMemo().compareTo(Env.ZERO)>0 && isReceipt()) {

			final MInvoice mInvoice = new MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
			if(mInvoice.isSOTrx()){

				final MInvoice notaCredito = MInvoice.createNotaCredCxC(

						mInvoice,
						getDateTrx(),
						getCreditMemo(),
						get_TrxName()) ;
				m_processMsg = m_processMsg+" "+notaCredito.getDocumentNo();
				// Mostrar las notas de credito
				setBackoffice("N");//Lama
				lstCrMemo.add(notaCredito);
				mInvoice.setIsPaid(notaCredito.getGrandTotal().compareTo( getCreditMemo().add(getPayAmt()) ) <= 0);
				mInvoice.save( get_TrxName());
			}
		}

		// Charge Handling ó es un anticipo
		if (getC_Charge_ID() != 0 || (getEXME_CtaPac_ID() > 0 && X_C_Payment.TYPE_AdvancePayment.equals(getType()))) {
			setIsAllocated(true);
			
		} else {
			allocateIt(); // Create Allocation Records
			testAllocation();
		}

		// Project update
//		if (getC_Project_ID() != 0) {
			// MProject project = new MProject(getCtx(), getC_Project_ID());
//		}
		// Update BP for Prepayments

		//Se comenta por bloqueo generado en la BD MX el 25 Abril 2013.
		//Jesus Cantu con asesoria y apoyo de Twry perez y Mrojas a Solicitud de GC.

		/*if (getC_BPartner_ID() != 0 && getC_Invoice_ID() == 0) {
			MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
			bp.setTotalOpenBalance();
			bp.save();
		}*/

		// Counter Doc
		MPayment counter = createCounterDoc();
		if (counter != null){
			m_processMsg += " @CounterDoc@: @C_Payment_ID@=" + counter.getDocumentNo();
		}

		// User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//
		setProcessed(true);
		setDocAction(DOCACTION_Close);

		// complete allocation .- Lama
		if (TYPE_PaymentRefund.equals(getType()) && getRef_Invoice_ID() > 0) {
			MInvoice inv = new MInvoice(getCtx(), getRef_Invoice_ID(), null);//nota credito
			if (!inv.is_new()) {
				MAllocationHdr hdr = new Query(getCtx(), MAllocationHdr.Table_Name, //
					" line.C_Payment_ID=? AND line.C_Invoice_ID=? ", get_TrxName())//
					.setJoins(new StringBuilder(" INNER JOIN C_AllocationLine line ON C_AllocationHdr.C_AllocationHdr_ID=line.C_AllocationHdr_ID"))//
					.setParameters(getC_Payment_ID(), inv.getRef_Invoice_ID())//pago y factura
					.first();
				if (hdr != null && !hdr.is_new()) {
					hdr.processIt(DocAction.ACTION_Complete);
					if(hdr.save() && DocAction.STATUS_Completed.equals(hdr.getDocStatus())) {
						s_log.fine("Allocation Completed >> "+hdr.getC_AllocationHdr_ID());
					} else {
						s_log.severe("Allocation NOT Completed >> "+hdr.getC_AllocationHdr_ID());
					}
				}
			}
		}
		return DocAction.STATUS_Completed;
	} // completeIt

	/** Listado de notas de credito */
	final List<MInvoice> lstCrMemo = new ArrayList<MInvoice>();

	public List<MInvoice> getLstCrMemo() {
		return lstCrMemo;
	}

	/**
	 * Create Counter Document
	 */
	private MPayment createCounterDoc() {
		// Is this a counter doc ?
		if (getRef_Payment_ID() != 0)
			return null;

		// Org Must be linked to BPartner
		MOrg org = MOrg.get(getCtx(), getAD_Org_ID());
		int counterC_BPartner_ID = org.getLinkedC_BPartner_ID();
		if (counterC_BPartner_ID == 0)
			return null;
		// Business Partner needs to be linked to Org
		MBPartner bp = new MBPartner(getCtx(), getC_BPartner_ID(), null);
		int counterAD_Org_ID = bp.getAD_OrgBP_ID_Int();
		if (counterAD_Org_ID == 0)
			return null;

		MBPartner counterBP = new MBPartner(getCtx(), counterC_BPartner_ID, null);
		// MOrgInfo counterOrgInfo = MOrgInfo.get(getCtx(), counterAD_Org_ID);
		log.info("Counter BP=" + counterBP.getName());

		// Document Type
		int C_DocTypeTarget_ID = 0;
		MDocTypeCounter counterDT = MDocTypeCounter.getCounterDocType(getCtx(), getC_DocType_ID());
		if (counterDT != null) {
			log.fine(counterDT.toString());
			if (!counterDT.isCreateCounter() || !counterDT.isValid())
				return null;
			C_DocTypeTarget_ID = counterDT.getCounter_C_DocType_ID();
		} else // indirect
		{
			C_DocTypeTarget_ID = MDocTypeCounter.getCounterDocType_ID(getCtx(), getC_DocType_ID());
			log.fine("Indirect C_DocTypeTarget_ID=" + C_DocTypeTarget_ID);
			if (C_DocTypeTarget_ID <= 0)
				return null;
		}

		// Deep Copy
		MPayment counter = new MPayment(getCtx(), 0, get_TrxName());
		counter.setAD_Org_ID(counterAD_Org_ID);
		counter.setC_BPartner_ID(counterBP.getC_BPartner_ID());
		counter.setIsReceipt(!isReceipt());
		counter.setC_DocType_ID(C_DocTypeTarget_ID);
		counter.setTrxType(getTrxType());
		counter.setTenderType(getTenderType());
		//
		counter.setPayAmt(getPayAmt());
		counter.setDiscountAmt(getDiscountAmt());
		counter.setTaxAmt(getTaxAmt());
		counter.setWriteOffAmt(getWriteOffAmt());
		counter.setIsOverUnderPayment(isOverUnderPayment());
		counter.setOverUnderAmt(getOverUnderAmt());
		counter.setC_Currency_ID(getC_Currency_ID());
		counter.setC_ConversionType_ID(getC_ConversionType_ID());
		//
		counter.setDateTrx(getDateTrx());
		counter.setDateAcct(getDateAcct());
		counter.setRef_Payment_ID(getC_Payment_ID());
		//
		String sql = "SELECT C_BankAccount_ID FROM C_BankAccount "
				+ "WHERE C_Currency_ID=? AND AD_Org_ID IN (0,?) AND IsActive='Y' "
				+ "ORDER BY IsDefault DESC";
		int C_BankAccount_ID = DB.getSQLValue(get_TrxName(), sql, getC_Currency_ID(), counterAD_Org_ID);
		counter.setC_BankAccount_ID(C_BankAccount_ID);

		// Refernces
		counter.setC_Activity_ID(getC_Activity_ID());
		counter.setC_Campaign_ID(getC_Campaign_ID());
		counter.setC_Project_ID(getC_Project_ID());
		counter.setUser1_ID(getUser1_ID());
		counter.setUser2_ID(getUser2_ID());
		counter.save(get_TrxName());
		log.fine(counter.toString());
		setRef_Payment_ID(counter.getC_Payment_ID());

		// Document Action
		if (counterDT != null && counterDT.getDocAction() != null) {
			// if (counterDT.getDocAction() != null)
			// {
			counter.setDocAction(counterDT.getDocAction());
			counter.processIt(counterDT.getDocAction());
			counter.save(get_TrxName());
			// }
		}
		return counter;
	} // createCounterDoc

	/**
	 * Allocate It. Only call when there is NO allocation as it will create
	 * duplicates. If an invoice exists, it allocates that otherwise it
	 * allocates Payment Selection.
	 *
	 * @return true if allocated
	 */
	public boolean allocateIt() {
		// Create invoice Allocation - See also MCash.completeIt
		if (getC_Invoice_ID() != 0) {
			return allocateInvoice();
		}

		// Invoices of a AP Payment Selection
		if (allocatePaySelection()) {
			return true;
		}

		if (getC_Order_ID() != 0) {
			return false;
		}

		// Allocate to multiple Payments based on entry
		MPaymentAllocate[] pAllocs = MPaymentAllocate.get(this);
		if (pAllocs.length == 0) {
			return false;
		} else {
			final MAllocationHdr alloc = allocateInvoices(pAllocs); 
			return alloc==null?false:alloc.save(get_TrxName());
		}
	}
	
	private MAllocationHdr allocateInvoices(MPaymentAllocate[] pAllocs) {
		//getDateTrx() >> Lama, se pasa la fecha actual
		MAllocationHdr alloc =
				new MAllocationHdr(
						getCtx(),
						false,
						Env.getCurrentDate(),
						getC_Currency_ID(),
						Msg.translate(getCtx(), X_C_Payment.COLUMNNAME_C_Payment_ID)+ ": " + getDocumentNo()+" [n]",
						get_TrxName()
				);
		alloc.setAD_Org_ID(getAD_Org_ID());

		if (!alloc.save()) {
			log.severe("P.Allocations not created");
			return null;
		}

		// Lines
		for (int i = 0; i < pAllocs.length; i++) {
			MPaymentAllocate pa = pAllocs[i];
			MAllocationLine aLine = null;

			if (isReceipt()) {
				aLine =
						new MAllocationLine(
								alloc,
								pa.getAmount(),
								pa.getDiscountAmt(),
								pa.getWriteOffAmt(),
								pa.getOverUnderAmt()
						);
			} else {
				aLine =
						new MAllocationLine(
								alloc,
								pa.getAmount().negate(),
								pa.getDiscountAmt().negate(),
								pa.getWriteOffAmt().negate(),
								pa.getOverUnderAmt().negate()
						);
			}

			aLine.setDocInfo(pa.getC_BPartner_ID(), 0, pa.getC_Invoice_ID());
			aLine.setPaymentInfo(getC_Payment_ID(), 0);

			if (aLine.save(get_TrxName())) {
				pa.setC_AllocationLine_ID(aLine.getC_AllocationLine_ID());
				pa.save();
			} else {
				log.warning("P.Allocations - line not saved");
			}
		}
		// Should start WF
		alloc.processIt(DocAction.ACTION_Complete);
		m_processMsg = "@C_AllocationHdr_ID@: " + alloc.getDocumentNo();
		return alloc;
	} // allocateIt

	/** Crear las asignaciones de cobros */
	private MAllocationHdr allocateInvoices(List<BeanPaySelect> pAllocs) {
		final MAllocationHdr alloc;
		if( pAllocs.isEmpty()){
			alloc = null;
		} else {
			alloc = new MAllocationHdr(
					getCtx(),
					false,
					getAllocationDate(),//La fecha que seleccionaron de la asignacion
					getC_Currency_ID(),// En moneda del pago	
					Msg.translate(getCtx(), X_C_Payment.COLUMNNAME_C_Payment_ID)+ ": " + getDocumentNo()+" [ac]",
					get_TrxName()
					);
			alloc.setAD_Org_ID(getAD_Org_ID());

			if (alloc.save()) {
				// Lines
				for (final BeanPaySelect pa :pAllocs) {
					final MAllocationLine aLine;
					if (isReceipt()) {
						aLine = new MAllocationLine(
								alloc
								, pa.getApplied()// pa.getAmount(),
								, pa.getDiscount()// pa.getDiscountAmt(),
								, pa.getBad()// pa.getWriteOffAmt(),
								, pa.getNotApplied()// pa.getOverUnderAmt()
								);
					} else {
						aLine = new MAllocationLine(
								alloc
								, pa.getApplied().negate()
								, pa.getDiscount().negate()
								, pa.getBad().negate()
								, pa.getNotApplied().negate()
								);
					}
					aLine.setRate(pa.getRateAllocation());
					aLine.setDocInfo(pa.getPartnerId(), 0, pa.getInvoiceId());
					aLine.setPaymentInfo(getC_Payment_ID(), 0);
					if (!aLine.save(get_TrxName())) {
						log.warning("P.Allocations - line not saved");
					}
				}

				// Should start WF
				alloc.completeInvoice(true);//processIt(DocAction.ACTION_Complete);
				m_processMsg = "@C_AllocationHdr_ID@: " + alloc.getDocumentNo();
			} else {
				throw new MedsysException();
			}
		}
		return alloc;
	} // allocateIt
	/**
	 * Allocate single AP/AR Invoice
	 */
	private boolean allocateInvoice() {
		// calculate actual allocation
		BigDecimal allocationAmt = getPayAmt(); // underpayment
		if (getOverUnderAmt().signum() < 0 && getPayAmt().signum() > 0) {
			// overpayment (negative)
			allocationAmt = allocationAmt.add(getOverUnderAmt());
		}

		Timestamp dateTrx = getTransactionDate();

		MAllocationHdr alloc =
				new MAllocationHdr(
						getCtx(),
						false,
						dateTrx,
						getC_Currency_ID(),
						Msg.translate(getCtx(), "C_Payment_ID") + ": " + getDocumentNo() + " [m]",
						get_TrxName()
				);
		alloc.setAD_Org_ID(getAD_Org_ID());

		if (!alloc.save()) {
			log.log(Level.SEVERE, "Could not create Allocation Hdr");
			return false;
		}

		MAllocationLine aLine = null;

		if (isReceipt()) {
			aLine =
					new MAllocationLine(
							alloc,
							allocationAmt,
							getDiscountAmt(),
							getWriteOffAmt(),
							getOverUnderAmt()
					);
		} else {
			aLine =
					new MAllocationLine(
							alloc,
							allocationAmt.negate(),
							getDiscountAmt().negate(),
							getWriteOffAmt().negate(),
							getOverUnderAmt().negate()
					);
		}

		aLine.setDocInfo(getC_BPartner_ID(), 0, getC_Invoice_ID());
		aLine.setC_Payment_ID(getC_Payment_ID());
		if (!aLine.save(get_TrxName())) {
			log.log(Level.SEVERE, "Could not create Allocation Line");
			return false;
		}
		// Should start WF
		alloc.processIt(DocAction.ACTION_Complete);
		alloc.save(get_TrxName());
		m_processMsg = "@C_AllocationHdr_ID@: " + alloc.getDocumentNo();

		// Get Project from Invoice
		int C_Project_ID =
				DB.getSQLValue(
						get_TrxName(),
						"SELECT MAX(C_Project_ID) FROM C_Invoice WHERE C_Invoice_ID=?",
						getC_Invoice_ID()
				);

		if (C_Project_ID > 0 && getC_Project_ID() == 0) {
			setC_Project_ID(C_Project_ID);
		} else if (C_Project_ID > 0
				&& getC_Project_ID() > 0
				&& C_Project_ID != getC_Project_ID()) {
			log.warning("Invoice C_Project_ID=" + C_Project_ID + " <> Payment C_Project_ID=" + getC_Project_ID());
		}
		return true;
	} // allocateInvoice

	/**
	 * Allocate Payment Selection
	 */
	private boolean allocatePaySelection() {
		//getDateTrx() >> Lama, se pasa la fecha actual
		MAllocationHdr alloc = new MAllocationHdr(getCtx(), false,
			getAllocationDate() == null ? Env.getCurrentDate() : getAllocationDate(), //Lama
			Env.getC_Currency_ID(Env.getCtx()) // En pesos(esquema) siempre
			, Msg.translate(getCtx(), "C_Payment_ID")+ ": "+ getDocumentNo()+ " [o]"
			, get_TrxName());
		alloc.setAD_Org_ID(getAD_Org_ID());

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT psc.C_BPartner_ID, psl.C_Invoice_ID, psl.IsSOTrx, "); // 1..3
		sql.append(" psl.PayAmt, psl.DiscountAmt, psl.DifferenceAmt, psl.OpenAmt, psl.C_PaySelectionLine_ID ");
		sql.append("FROM C_PaySelectionLine psl");
		sql.append(" INNER JOIN C_PaySelectionCheck psc ON (psl.C_PaySelectionCheck_ID=psc.C_PaySelectionCheck_ID) ");
		sql.append(" LEFT JOIN C_AllocationLine al ON (psl.C_PaySelectionLine_ID=al.C_PaySelectionLine_ID) ");
		sql.append("WHERE psc.C_Payment_ID=?");
		sql.append(" AND psl.C_Invoice_ID IS NOT NULL ");
		sql.append(" AND psc.C_BPartner_ID IS NOT NULL ");
		sql.append(" AND al.C_PaySelectionLine_ID IS NULL ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Payment_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int C_BPartner_ID = rs.getInt(1);
				int C_Invoice_ID = rs.getInt(2);
				if (C_BPartner_ID == 0 && C_Invoice_ID == 0) {
					continue;
				}
				boolean isSOTrx = "Y".equals(rs.getString(3));
				BigDecimal PayAmt = rs.getBigDecimal(4);
				BigDecimal DiscountAmt  = rs.getBigDecimal(5).setScale(2,BigDecimal.ROUND_HALF_UP);
				BigDecimal WriteOffAmt  =  rs.getBigDecimal(6).setScale(2,BigDecimal.ROUND_HALF_UP);
				BigDecimal OpenAmt      = rs.getBigDecimal(7);
				BigDecimal OverUnderAmt = isFullPayment?OpenAmt.subtract(PayAmt).subtract(DiscountAmt).subtract(WriteOffAmt):Env.ZERO;
				//
				if (alloc.get_ID() == 0 && !alloc.save(get_TrxName())) {
					log.log(Level.SEVERE, "Could not create Allocation Hdr");
					return false;
				}
				MAllocationLine aLine = null;
				if (isSOTrx) {
					aLine = new MAllocationLine(alloc, PayAmt, DiscountAmt, WriteOffAmt, OverUnderAmt);
				} else {
					aLine = new MAllocationLine(alloc, PayAmt.negate(), DiscountAmt.negate(), WriteOffAmt.negate(), OverUnderAmt.negate());
				}
				
				final MInvoice mInvoice = new MInvoice(getCtx(), C_Invoice_ID, null);
				aLine.setDocInfo(C_BPartner_ID, 0, C_Invoice_ID);
				aLine.setC_Payment_ID(getC_Payment_ID());
				aLine.setC_PaySelectionLine_ID(rs.getInt("C_PaySelectionLine_ID"));
				// Si la moneda del pago es igual a la moneda del esquema utilizaremos la moneda de la factura
				// para obtener una conversion.
				aLine.setRate(MConversionRate.getRate(getCtx(), -1
						, getC_Currency_ID()==Env.getC_Currency_ID(getCtx())
						? mInvoice.getC_Currency_ID()>0?mInvoice.getC_Currency_ID():getC_Currency_ID()
								: getC_Currency_ID(), new java.sql.Timestamp(TimeUtil.getToday().getTimeInMillis()).getTime()
						));//dolar
				if (!aLine.save(get_TrxName())) {
					log.log(Level.SEVERE, "Could not create Allocation Line");
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "allocatePaySelection", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		// Should start WF
		boolean ok = true;
		if (alloc.get_ID() == 0) {
			log.fine("No Allocation created - C_Payment_ID=" + getC_Payment_ID());
			ok = false;
		} else {
			alloc.processIt(DocAction.ACTION_Complete);
			ok = alloc.save(get_TrxName());
			m_processMsg = "@C_AllocationHdr_ID@: " + alloc.getDocumentNo();
		}
		return ok;
	} // allocatePaySelection

	/**
	 * De-allocate Payment. Unkink Invoices and Orders and delete Allocations
	 */
	private void deAllocate() {
		if (getC_Order_ID() != 0)
			setC_Order_ID(0);

		// De-Allocate all
		MAllocationHdr[] allocations =
				MAllocationHdr.getOfPayment(getCtx(), getC_Payment_ID(), get_TrxName());
		log.fine("#" + allocations.length);

		for (int i = 0; i < allocations.length; i++) {
			allocations[i].set_TrxName(get_TrxName());
			allocations[i].setDocAction(DocAction.ACTION_Reverse_Correct);
			allocations[i].processIt(DocAction.ACTION_Reverse_Correct);
			allocations[i].save();
		}

		unlink();

		//
		setC_Invoice_ID(0);
		setIsAllocated(false);
	} // deallocate

	/**
	 * De-allocate Payment. Unkink Invoices and Orders and delete Allocations
	 */
	private void deAllocate(final boolean reverse, final Timestamp dateAcct) {

		// De-Allocate all
		final MAllocationHdr[] allocations =
				MAllocationHdr.getOfPayment(getCtx(), getC_Payment_ID(), get_TrxName());

		log.fine("#" + allocations.length);

		for (int i = 0; i < allocations.length; i++) {
			allocations[i].set_TrxName(get_TrxName());

			final MAllocationHdr header =
					new MAllocationHdr(
							getCtx()
							, false
							, dateAcct==null?getDateTrx():dateAcct
							, getC_Currency_ID()
							, reverse? Msg.translate(getCtx(), "Rev")+" "+allocations[i].toString()+" [i]":allocations[i].toString()+" [p]"
							, get_TrxName()
					);
			header.setAD_Org_ID(getAD_Org_ID());
			boolean continuar = header.save();


			// Allocation line
			final MAllocationLine[] allocationLine = allocations[i].getLines(true);
			for (final MAllocationLine mAllocLine:allocationLine) {

				if(mAllocLine.getC_Payment_ID() == getC_Payment_ID()){
					if(continuar){
						final MAllocationLine devolAllocLine =
								new MAllocationLine (
										header
										, reverse?mAllocLine.getAmount().negate():mAllocLine.getAmount()
												, reverse?mAllocLine.getDiscountAmt().negate():mAllocLine.getDiscountAmt()
														, reverse?mAllocLine.getWriteOffAmt().negate():mAllocLine.getWriteOffAmt()
																, reverse?mAllocLine.getOverUnderAmt().negate():mAllocLine.getOverUnderAmt()
										);
						devolAllocLine.set_TrxName(get_TrxName());
						//								devolAllocLine.setParent(header);
						devolAllocLine.setDocInfo(getC_BPartner_ID(), 0, mAllocLine.getC_Invoice_ID());
						devolAllocLine.setPaymentInfo(mAllocLine.getC_Payment_ID(),mAllocLine.getC_CashLine_ID());
						devolAllocLine.setAmtAcct(mAllocLine.getAmtAcct());
						devolAllocLine.setC_AllocationHdr_ID(header.getC_AllocationHdr_ID());
						devolAllocLine.setC_Invoice_ID(mAllocLine.getC_Invoice_ID());
						continuar = devolAllocLine.save(get_TrxName());
					} else {
						continuar = false;
						break;
					}
				}
			}

			// Lama - completar allocation
			if(continuar){
				header.processIt(DocAction.ACTION_Complete);
				if (header.save(get_TrxName())
						&& DocAction.STATUS_Completed.equals(header.getDocStatus())) {
					s_log.fine("Allocation Completed >> " + header.getC_AllocationHdr_ID());
				} else {
					s_log.severe("Allocation NOT Completed >> " + header.getC_AllocationHdr_ID());
				}
//				header.setDocStatus(header.prepareIt());
//				continuar = header.save(get_TrxName());
			} else {
				break;
			}
		}

		unlink();
		//
		setC_Order_ID(0);
		setC_Invoice_ID(0);
		setIsAllocated(false);
	} // deallocate



	private void unlink() {
		// Unlink (in case allocation did not get it)
		if (getC_Invoice_ID() != 0) {
			// Invoice
			String sql =
					"UPDATE C_Invoice SET C_Payment_ID = NULL, IsPaid = 'N' \n"
					+ "WHERE C_Invoice_ID = ? AND C_Payment_ID = ?";

			int no =
					DB.executeUpdate(
							sql,
							new Object[] {getC_Invoice_ID(), getC_Payment_ID()},
							get_TrxName()
							);

			if (no != 0) {
				log.fine("Unlink Invoice #" + no);
			}

			// Order
			sql =
					"UPDATE C_Order o  SET C_Payment_ID = NULL \n"
					+ " WHERE EXISTS (SELECT * FROM C_Invoice i \n"
					+ "   WHERE o.C_Order_ID=i.C_Order_ID AND i.C_Invoice_ID = ? \n"
					+ " )\n"
					+ " AND C_Payment_ID = ?";

			no =
					DB.executeUpdate(
							sql,
							new Object[] {getC_Invoice_ID(), getC_Payment_ID()},
							get_TrxName()
							);

			if (no != 0) {
				log.fine("Unlink Order #" + no);
			}
		}
	}


	/**
	 * Void Document.
	 *
	 * @return true if success
	 */
	public boolean voidIt() {
		log.info(toString());
		
		// Un anticipo a cuenta paciente no se puede anular
		if (getEXME_CtaPac_ID() > 0 
				&& X_C_Payment.TYPE_AdvancePayment.equals(getType()) ) {
			log.severe("Este pago es un anticipo : "+toString());
			throw new IllegalStateException(Utilerias.getLabel("error.caja.anticipo.noSave")+ ":"
					+ Utilerias.getLabel("exito.movAnticipos"));
		} 
		
		
		if (DOCSTATUS_Closed.equals(getDocStatus()) || DOCSTATUS_Reversed.equals(getDocStatus()) || DOCSTATUS_Voided.equals(getDocStatus())) {
			m_processMsg = "Document Closed: " + getDocStatus();
			setDocAction(DOCACTION_None);
			return false;
		}
		// If on Bank Statement, don't void it - reverse it
		if (getC_BankStatementLine_ID() > 0)
			return reverseCorrectIt();

		// Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
				|| DOCSTATUS_Invalid.equals(getDocStatus())
				|| DOCSTATUS_InProgress.equals(getDocStatus())
				|| DOCSTATUS_Approved.equals(getDocStatus())
				|| DOCSTATUS_NotApproved.equals(getDocStatus())) {
			addDescription(Msg.getMsg(getCtx(), "Voided") + " (" + getPayAmt() + ")");
			setPayAmt(Env.ZERO);
			setDiscountAmt(Env.ZERO);
			setWriteOffAmt(Env.ZERO);
			setOverUnderAmt(Env.ZERO);
			setIsAllocated(false);
			// Unlink & De-Allocate
			deAllocate();
		} else
			return reverseCorrectIt();

		//
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	} // voidIt

	/**
	 * Close Document.
	 *
	 * @return true if success
	 */
	public boolean closeIt() {
		log.info(toString());
		setDocAction(DOCACTION_None);
		return true;
	} // closeIt

	@Override
	public void setIsAllocated (boolean IsAllocated){
		if(getEXME_CtaPac_ID()>0 && X_C_Payment.TYPE_AdvancePayment.equals(getType())){
			super.setIsAllocated(true);
		} else {
			super.setIsAllocated(IsAllocated);
		}
	}
	
	/**
	 * Reverse Correction
	 *
	 * @return true if success
	 */
	public boolean reverseCorrectIt() {
		log.info(toString());

		if (getEXME_CtaPac_ID() > 0 && X_C_Payment.TYPE_AdvancePayment.equals(getType()) ) {
			log.severe("Este pago es un anticipo : "+toString());
			throw new IllegalStateException(Utilerias.getLabel("error.caja.anticipo.noSave")+ ":"
					+ Utilerias.getLabel("exito.movAnticipos"));

		} else {

			// Std Period open?
			Timestamp dateAcct = getDateAcct();
			// if (!MPeriod.isOpen(getCtx(), dateAcct,
			// isReceipt() ? MDocType.DOCBASETYPE_ARReceipt :
			// MDocType.DOCBASETYPE_APPayment))
			if (!MPeriod.isOpen(getCtx(), dateAcct, isReceipt() ? MDocType.DOCBASETYPE_ARReceipt : MDocType.DOCBASETYPE_APPayment, getAD_Org_ID()))
				dateAcct = DB.getTSForOrg(getCtx());

			// Auto Reconcile if not on Bank Statement
			boolean reconciled = false; // getC_BankStatementLine_ID() == 0;

			// Create Reversal
			MPayment reversal = new MPayment(getCtx(), 0, get_TrxName());
			copyValues(this, reversal);
			reversal.setClientOrg(this);
			reversal.setC_Order_ID(0);
			reversal.setC_Invoice_ID(0);
			reversal.setDateAcct(dateAcct);
			//
			reversal.setDocumentNo(getDocumentNo() + REVERSE_INDICATOR); // indicate
			// reversals
			reversal.setDocStatus(DOCSTATUS_Drafted);
			reversal.setDocAction(DOCACTION_Complete);
			//
			reversal.setPayAmt(getPayAmt().negate());
			reversal.setDiscountAmt(getDiscountAmt().negate());
			reversal.setWriteOffAmt(getWriteOffAmt().negate());
			reversal.setOverUnderAmt(getOverUnderAmt().negate());
			//
			reversal.setIsAllocated(true);
			reversal.setIsReconciled(reconciled); // to put on bank statement
			reversal.setIsOnline(false);
			reversal.setIsApproved(true);
			reversal.setR_PnRef(null);
			reversal.setR_Result(null);
			reversal.setR_RespMsg(null);
			reversal.setR_AuthCode(null);
			reversal.setR_Info(null);
			reversal.setProcessing(false);
			// reversal.setOProcessing("N");
			reversal.setProcessed(false);
			reversal.setPosted(false);
			reversal.setDescription(getDescription());
			reversal.addDescription("{->" + getDocumentNo() + ")");
			reversal.save(get_TrxName());
			// Post Reversal
			if (!reversal.processIt(DocAction.ACTION_Complete)) {
				m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
				return false;
			}
			reversal.closeIt();
			reversal.setDocStatus(DOCSTATUS_Reversed);
			reversal.setDocAction(DOCACTION_None);
			reversal.setReversal_ID(getC_Payment_ID());// Se hace referencia al pago que cancela #8814
			reversal.save(get_TrxName());

			// Unlink & De-Allocate
			deAllocate(true, dateAcct);
			setIsReconciled(reconciled);
			setIsAllocated(true); // the allocation below is overwritten
			// Set Status
			addDescription("(" + reversal.getDocumentNo() + "<-)");
			setDocStatus(DOCSTATUS_Reversed);
			setDocAction(DOCACTION_None);
			setProcessed(true);

			// Create automatic Allocation
			//getDateTrx() >> Lama, se pasa la fecha actual

			//		MAllocationHdr alloc =
			//				new MAllocationHdr(
			//						getCtx(),
			//						false,
			//						getTransactionDate(),
			//						getC_Currency_ID(),
			//						Msg.translate(getCtx(), "C_Payment_ID") + ": " + reversal.getDocumentNo() + " [k]",
			//						get_TrxName()
			//				);
			//		alloc.setAD_Org_ID(getAD_Org_ID());
			//
			//		if (!alloc.save()) {
			//			log.warning("Automatic allocation - hdr not saved - Payment : " + getDocumentInfo());
			//		} else {
			//			// Original Allocation
			//			MAllocationLine aLine =
			//					new MAllocationLine(
			//							alloc,
			//							getPayAmt(true),
			//							BigDecimal.ZERO,
			//							BigDecimal.ZERO,
			//							BigDecimal.ZERO
			//					);
			//
			//			aLine.setDocInfo(getC_BPartner_ID(), 0, 0);
			//			aLine.setPaymentInfo(getC_Payment_ID(), 0);
			//			if (!aLine.save(get_TrxName())) {
			//				log.warning("Automatic allocation - line not saved");
			//			}
			//			// Reversal Allocation
			//			aLine =
			//					new MAllocationLine(
			//							alloc,
			//							reversal.getPayAmt(true),
			//							BigDecimal.ZERO,
			//							BigDecimal.ZERO,
			//							BigDecimal.ZERO
			//					);
			//
			//			aLine.setDocInfo(reversal.getC_BPartner_ID(), 0, 0);
			//			aLine.setPaymentInfo(reversal.getC_Payment_ID(), 0);
			//			if (!aLine.save(get_TrxName())) {
			//				log.warning("Automatic allocation - reversal line not saved");
			//			}
			//		}//Se comenta por que ahora se hará referencia al pago que cancela #8814

			//		alloc.processIt(DocAction.ACTION_Complete);
			//		// Lama - registrar completado en log
			//		if (alloc.save(get_TrxName()) && DocAction.STATUS_Completed.equals(alloc.getDocStatus())) {
			//			s_log.fine("Allocation Completed >> " + alloc.getC_AllocationHdr_ID());
			//		} else {
			//			s_log.severe("Allocation NOT Completed >> " + alloc.getC_AllocationHdr_ID());
			//		}
			//
			final StringBuffer info = new StringBuffer(Utilerias.getLabel("nodocument.canceled")).append(", ")
			.append((Utilerias.getLabel(X_C_Payment.COLUMNNAME_Ref_Payment_ID))).append(": ")
			.append(reversal.getDocumentNo());
			//		info.append(" - @C_AllocationHdr_ID@: ").append(alloc.getDocumentNo());

			m_processMsg = info.toString();

			// Cancelacion de devolucion de pagos con cheque (Notas de Credito) Card #1111 .- Lama
			if (getRef_Invoice_ID() > 0) {
				final MInvoice inv = new MInvoice(getCtx(), getRef_Invoice_ID(), get_TrxName());
				inv.setIsPaid(false); // nota de credito
				if (inv.save()) {
					// Cancela el cheque
					cancelRefundCheck(reversal);
				}
				//				// hacemos reversa de la asignacion creada ....
				//				final MAllocationLine line =
				//						new Query(
				//								getCtx(),
				//								MAllocationLine.Table_Name, //
				//								" C_Payment_ID=? AND C_Invoice_ID=? ",
				//								get_TrxName()
				//						).setParameters(					// pago y factura
				//								getC_Payment_ID(),
				//								inv.getRef_Invoice_ID()
				//						).first();

				//				if (line != null && !line.is_new()) {
				//					// crear copia - se quita copyValues
				//					final I_C_AllocationHdr hdrFrom = line.getC_AllocationHdr();
				//					final MAllocationHdr hdr =
				//						new MAllocationHdr(
				//								getCtx(),
				//								hdrFrom.isManual(),
				//								alloc.getDateTrx(),
				//								hdrFrom.getC_Currency_ID(),
				//								hdrFrom.getDescription()+" [r]",
				//								get_TrxName()
				//						);
				//					if (hdr.save()) {
				//						final MAllocationLine line2 = new MAllocationLine(getCtx(), 0, get_TrxName());
				//						PO.copyValues(line, line2);
				//						line2.setPosted(false);
				//						line2.setC_AllocationHdr_ID(hdr.getC_AllocationHdr_ID());
				//						line2.setAmount(line.getAmount().negate()); // se ninvierte la cantidad
				//						if (line2.save()) {
				//							hdr.processIt(DocAction.ACTION_Complete);
				//							if (hdr.save() && DocAction.STATUS_Completed.equals(hdr.getDocStatus())) {
				//								s_log.fine("Allocation Completed >> " + hdr.getC_AllocationHdr_ID());
				//							} else {
				//								s_log.severe("Allocation NOT Completed >> " + hdr.getC_AllocationHdr_ID());
				//							}
				//						}
				//					}
				//				}
			} else
				// Si es una devolucion de anticipo con cheque Card #1345 #1049
				if (X_C_Payment.TYPE_PaymentRefund.equals(getType()) && getEXME_CtaPac_ID() > 0) {
					cancelRefundCheck(reversal);// cancela el cheque
					//			// actualiza el anticipo a la extension 0
					//			final int extension0Id = MEXMECtaPac.getExtZero(getEXME_CtaPac_ID());
					//			MEXMEAnticipo advanceExt0 = MEXMEAnticipo.getAnticipo(getCtx(), getEXME_CtaPac_ID(), extension0Id, null);
					//			// registra el pago nuevamente en CtaPacPagos y actualiza el saldo de anticipo
					//			if (advanceExt0 == null) {
					//				advanceExt0 = new MEXMEAnticipo(getCtx(), 0, get_TrxName());
					//				advanceExt0.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
					//				advanceExt0.setEXME_CtaPacExt_ID(extension0Id);
					//				advanceExt0.setTotal(BigDecimal.ZERO);
					//				advanceExt0.setAplicado(BigDecimal.ZERO);
					//				advanceExt0.setSaldo(BigDecimal.ZERO);
					//			}
					//			final BigDecimal total = advanceExt0.getTotal().add(reversal.getPayAmt());
					//			advanceExt0.setTotal(total);
					//			advanceExt0.setSaldo(total);
					//			if (!advanceExt0.save(get_TrxName())) {
					//				throw new MedsysException();
					//			}
					//			// generamos la relacion de pago - cta pag
					//			final MCtaPacPag ctaPacPag = new MCtaPacPag(getCtx(), 0, get_TrxName());
					//			ctaPacPag.setC_Payment_ID(reversal.getC_Payment_ID());
					//			ctaPacPag.setEXME_CtaPacExt_ID(extension0Id);
					//			ctaPacPag.setIsPay(true);
					//			if (!ctaPacPag.save()) {
					//				throw new MedsysException();
					//			}
					//			ctaPacPag.setDisponible(reversal.getAmount());
					//			if (!ctaPacPag.save()) {
					//				throw new MedsysException();
					//			}
				}
		}
		return true;
	} // reverseCorrectionIt

	private void cancelRefundCheck(MPayment reversal) {
		final MPaySelectionCheck check = MPaySelectionCheck.getOfPayment(getCtx(), getC_Payment_ID(), get_TrxName());

		if (check != null) {
			check.setIsActive(false);
			final StringBuilder str = new StringBuilder();
			str.append("[").append(Msg.translate(getCtx(), "CanceledBy"));
			str.append(">> ").append(Msg.translate(getCtx(), "M_Payment_ID"));
			str.append(": ").append(reversal.getDocumentNo());
			str.append("] ").append(getDocumentNo());
			check.setDocumentNo(str.toString());
			if (check.save()) {// registrar en el log el error
				s_log.fine("PaySelectionCheck Completed >> " + check.getC_PaySelectionCheck_ID());
			} else {
				s_log.severe("PaySelectionCheck NOT Completed >> " + check.getC_PaySelectionCheck_ID());
			}
		}
	}

	/**
	 * Get Bank Statement Line of payment or 0
	 *
	 * @return id or 0
	 */
	private int getC_BankStatementLine_ID() {
		String sql = "SELECT C_BankStatementLine_ID FROM C_BankStatementLine WHERE C_Payment_ID=?";
		int id = DB.getSQLValue(get_TrxName(), sql, getC_Payment_ID());
		if (id < 0)
			return 0;
		return id;
	} // getC_BankStatementLine_ID

	/**
	 * Reverse Accrual - none
	 *
	 * @return true if success
	 */
	public boolean reverseAccrualIt() {
		log.info(toString());
		return false;
	} // reverseAccrualIt

	/**
	 * Re-activate
	 *
	 * @return true if success
	 */
	public boolean reActivateIt() {
		log.info(toString());
		if (reverseCorrectIt())
			return true;
		return false;
	} // reActivateIt

	/**
	 * String Representation
	 *
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("MPayment[");
		sb.append(get_ID()).append("-").append(getDocumentNo()).append(",Receipt=").append(isReceipt()).append(",PayAmt=").append(getPayAmt())
				.append(",Discount=").append(getDiscountAmt()).append(",WriteOff=").append(getWriteOffAmt()).append(",OverUnder=")
				.append(getOverUnderAmt());
		return sb.toString();
	} // toString
	
	/**
	 * String Representation
	 *
	 * @return info
	 */
	public String getDescriptiveData() {
		return new StringBuffer(getDocumentNo()).append(", $").append(getPayAmt())
				.append(" ").append(Constantes.getSDFDateTime(getCtx()).format(getDateTrx()))
				.append(" ").append(getC_BPartner().getValue())
				.append(" ").append(getC_BPartner().getName()).toString()
				;
	} // toString

	/**
	 * Get Document Info
	 *
	 * @return document info (untranslated)
	 */
	public String getDocumentInfo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	} // getDocumentInfo

	/**
	 * Create PDF
	 *
	 * @return File or null
	 */
	public File createPDF() {
		try {
			File temp = File.createTempFile(get_TableName() + get_ID() + "_", ".pdf");
			return createPDF(temp);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Could not create PDF - " + e.getMessage(), e);
		}
		return null;
	} // getPDF

	/**
	 * Create PDF file
	 *
	 * @param file
	 *            output file
	 * @return file if success
	 */
	public File createPDF(File file) {
		// ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.PAYMENT,
		// getC_Payment_ID());
		// if (re == null)
		return null;
		// return re.getPDF(file);
	} // createPDF

	/*************************************************************************
	 * Get Summary
	 *
	 * @return Summary of Document
	 */
	public String getSummary() {
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		// : Total Lines = 123.00 (#1)
		sb.append(": ").append(Msg.translate(getCtx(), "PayAmt")).append("=").append(getPayAmt()).append(",")
				.append(Msg.translate(getCtx(), "WriteOffAmt")).append("=").append(getWriteOffAmt());
		// - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	} // getSummary

	/**
	 * Get Process Message
	 *
	 * @return clear text error message
	 */
	public String getProcessMsg() {
		return m_processMsg;
	} // getProcessMsg

	/**
	 * Get Document Owner (Responsible)
	 *
	 * @return AD_User_ID
	 */
	public int getDoc_User_ID() {
		return getCreatedBy();
	} // getDoc_User_ID

	/**
	 * Get Document Approval Amount
	 *
	 * @return amount payment(AP) or write-off(AR)
	 */
	public BigDecimal getApprovalAmt() {
		if (isReceipt())
			return getWriteOffAmt();
		return getPayAmt();
	} // getApprovalAmt

	/**
	 * Noelia: Metodo que regresa una lista con todos los No de recibo de
	 * Anticipos Para una determinada Cuenta Paciente.
	 *
	 * @param CtaPacID
	 *            : Identificador de la cuenta paciente
	 * @return ArrayList<String>: Lista con los no de recibo de Anticipos
	 */
	public static ArrayList<String> getLstReciboNo(int ctaPacID, String trxName) {

		ArrayList<String> lstReciboNo = new ArrayList<String>();

		StringBuilder sql = new StringBuilder("SELECT p.reciboNo FROM EXME_CtaPacPag cpp ")
				.append("INNER JOIN C_Payment p ON cpp.C_Payment_ID = p.C_Payment_ID AND p.IsActive = 'Y' ")
				.append("INNER JOIN EXME_CtaPacExt cpe ON cpp.EXME_CtaPacExt_ID = cpe.EXME_CtaPacExt_ID AND cpe.IsActive = 'Y' ")
				.append("WHERE cpp.IsActive = 'Y' AND cpe.EXME_CtaPac_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				lstReciboNo.add(rs.getString("reciboNo"));
			}
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return lstReciboNo;
	}

	/**
	 * Metodo que regresa el PayAmt de la vista C_Payment_v para el pago
	 * seleccionado.
	 *
	 * @param C_Payment_ID
	 *            : Identificador del pago
	 * @return BigDecimal: PayAmt
	 */
	public static BigDecimal getPayAmt(int C_Payment_ID) {
		StringBuilder sql = new StringBuilder("SELECT PayAmt FROM C_Payment_v WHERE C_Payment_ID = ?  AND IsActive = 'Y' ");
		BigDecimal payAmt = DB.getSQLValueBD(null, sql.toString(), C_Payment_ID);
		return payAmt == null ? BigDecimal.ZERO : payAmt;
	}

	/**
	 * Obtiene una lista de MPayment
	 *
	 * @param where
	 *            filtro para query inicia con "AND"
	 * @param ctx
	 *            Contexto
	 * @return list
	 * @author rosy velazquez
	 * */
	public static List<MPayment> getPayments(String where, Properties ctx, String trxName) {

		final List<MPayment> list = new Query(ctx, Table_Name, " 1=1 " + where, trxName)//
				.setOnlyActiveRecords(true)//
				.list();
		return list;
	}

	/**
	 * Pagos de cita médica.
	 *
	 * @param ctx
	 * @param exmeCitaMedicaId
	 * @return pays
	 * @author lhernandez
	 */
	public static MPayment[] getPaymentsMedApp(Properties ctx, int exmeCitaMedicaId) {

		final List<MPayment> list = new Query(ctx, Table_Name, "C_Payment.EXME_CitaMedica_ID=?", null)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setParameters(exmeCitaMedicaId).list();
		MPayment[] pays = new MPayment[list.size()];
		list.toArray(pays);

		return pays;
	}

	/**
	 * Payments of Account.
	 *
	 * @param EXME_CtaPac_ID
	 *            Account
	 * @param payConcept
	 *            Payment Concept
	 * @param ctx
	 *            Context
	 * @return List of Payments group by Concept
	 * @author gvaldez
	 */
	public static HashMap<String, Double> getPaymentsAmt(int EXME_CtaPac_ID, String payConcept, Properties ctx) {
		HashMap<String, Double> list = new HashMap<String, Double>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		StringBuilder sqlWhere = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT ");
		sqlWhere.append(" WHERE EXME_CtaPac_ID = ?  AND IsActive = 'Y' ");

		if (payConcept != null) {
			sql.append(" PayConcept, ");
			sqlWhere.append(" and PayConcept = ? ").append(" Group By PayConcept ");
		}

		sql.append(" SUM(PayAmt) FROM C_Payment ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString().concat(sqlWhere.toString()), null);
			pstmt.setInt(1, EXME_CtaPac_ID);
			if (payConcept != null) {
				pstmt.setString(2, payConcept);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (payConcept != null) {
					list.put(rs.getString(1), rs.getDouble(2));
				} else {
					list.put(new String("1"), rs.getDouble(1));
				}

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	/**
	 *
	 * @param EXME_CtaPac_ID
	 * @param payConcept
	 * @param ctx
	 * @return
	 */
	public static List<MPayment> getPayments(int EXME_CtaPac_ID, String payConcept, Properties ctx) {

		final List<MPayment> list = new Query(ctx, Table_Name, "EXME_CTAPAC_ID=? AND PAYCONCEPT=?", null)//
				.setParameters(EXME_CtaPac_ID, payConcept).list();

		return list;
	}

	@Override
	public void setPaymentType(String PaymentType) {
		super.setPaymentType(PaymentType);
		if (StringUtils.isNotEmpty(getPaymentType()) || is_ValueChanged(COLUMNNAME_PaymentType)) {
			if (PAYMENTTYPE_ACH.equalsIgnoreCase(getPaymentType())) {
				super.setTenderType(TENDERTYPE_DirectDeposit);// FIXME is
																// correct?
			} else if (PAYMENTTYPE_Cash.equalsIgnoreCase(getPaymentType())) {
				super.setTenderType(TENDERTYPE_Cash);
			} else if (PAYMENTTYPE_CreditCard.equalsIgnoreCase(getPaymentType())) {
				super.setTenderType(TENDERTYPE_CreditCard);
			} else if (PAYMENTTYPE_Check.equalsIgnoreCase(getPaymentType())) {
				super.setTenderType(TENDERTYPE_Check);
			} else if (StringUtils.isEmpty(getTenderType())) {
				super.setTenderType(TENDERTYPE_Other);
			}
		}
	}

	@Override
	public void setTenderType(String TenderType) {
		super.setTenderType(TenderType);
		if (StringUtils.isNotEmpty(getTenderType()) || is_ValueChanged(COLUMNNAME_TenderType)) {
			if (TENDERTYPE_DirectDeposit.equalsIgnoreCase(getTenderType())) {
				super.setPaymentType(PAYMENTTYPE_ACH);// FIXME is correct?
			} else if (TENDERTYPE_Cash.equalsIgnoreCase(getTenderType())) {
				super.setPaymentType(PAYMENTTYPE_Cash);
			} else if (TENDERTYPE_CreditCard.equalsIgnoreCase(getTenderType())) {
				super.setPaymentType(PAYMENTTYPE_CreditCard);
			} else if (TENDERTYPE_Check.equalsIgnoreCase(getTenderType())) {
				super.setPaymentType(PAYMENTTYPE_Check);
			}
		}
	}

	/**
	 * Payments/Adjustments of Account.
	 *
	 * @param EXME_CtaPac_ID
	 *            Account
	 * @param AdjustmentType
	 *            Payment Concept
	 * @param ctx
	 *            Context
	 * @return Payment Amount group by AdjustmentType
	 * @author gvaldez
	 */
	public static HashMap<String, Double> getTotalPayment(int EXME_CtaPac_ID, String adjustmentType, Properties ctx) {
		HashMap<String, Double> retVal = new HashMap<String, Double>();
		// Double retVal = new Double(0);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EAT.Type, SUM(Nvl(P.PayAmt, AL.Amount)) Amount ").append("  FROM  C_Payment P ")
				.append(" LEFT JOIN C_AllocationLine AL ON (AL.C_Payment_ID = P.C_Payment_ID) ")
				// .append(" INNER JOIN C_Charge EAT ON ((EAT.C_Charge_ID = AL.C_Charge_ID ) ")
				// .append(" OR (AL.C_Charge_ID IS NULL AND EAT.Type = '"+X_C_Charge.TYPE_Payment+"')) ")
				.append(" INNER JOIN C_Charge EAT ON p.C_Charge_id = eat.C_Charge_id ");
		/*
		 * StringBuilder sql = new
		 * StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		 * sql.append("SELECT EAT.Type, SUM(Nvl(AL.Amount, P.PayAmt)) Amount ")
		 * .append("  FROM  C_Payment P ") .append(
		 * " LEFT JOIN C_AllocationLine AL ON AL.C_Payment_ID = P.C_Payment_ID "
		 * ) .append(
		 * " INNER JOIN C_Charge EAT ON ((EAT.C_Charge_ID = AL.C_Charge_ID ) ")
		 * .append(" OR (AL.C_Charge_ID IS NULL AND EAT.Type = '"+X_C_Charge.
		 * TYPE_Payment+"')) ");
		 */

		if (StringUtils.isNotEmpty(adjustmentType)) {
			sql.append("AND EAT.Type= ? ");
		}
		sql.append("  WHERE P.EXME_CtaPac_ID = ? AND P.IsActive = 'Y' ").append("GROUP BY EAT.Type ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int cont = 1;
			if (StringUtils.isNotEmpty(adjustmentType)) {
				pstmt.setString(cont++, adjustmentType);
			}
			pstmt.setInt(cont++, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.put(rs.getString(1), rs.getDouble(2));

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * Payments/Adjustments of Account by type of Claim.
	 *
	 * @param EXME_CtaPac_ID
	 *            Account
	 * @param AdjustmentType
	 *            Payment Concept
	 * @param ConfType
	 *            Claim Configuration Type
	 * @param ctx
	 *            Context
	 * @return Payment Amount group by AdjustmentType
	 * @author gvaldez/abautista
	 */
	public static HashMap<String, Double> getPaymentsByClaimType(int EXME_CtaPac_ID, String adjustmentType, String confType, Properties ctx) {
		HashMap<String, Double> retVal = new HashMap<String, Double>();
		// Double retVal = new Double(0);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EAT.Type, SUM(P.PayAmt) Amount ").append("   FROM  C_Payment P ")
				.append("   INNER JOIN C_Charge EAT        ON  P.c_charge_id   = EAT.c_charge_id  ")
				.append(StringUtils.isNotEmpty(adjustmentType) ? " AND EAT.Type  = ? " : StringUtils.EMPTY);

		// Profesional akello explicitamente prof por medio de la factura
		if (StringUtils.isNotEmpty(confType) && X_HL7_MessageConf.CONFTYPE_ProfessionalClaim.equals(confType)) {
			sql
					.append("   WHERE P.IsActive = 'Y'                                ")
					.append("      AND  (P.CONFTYPE = ? OR                            ")
					.append("       P.C_Payment_ID     IN (                           ")
					.append("                                  select sal.C_Payment_id   ")
					.append("                                  from C_Payment sal ")
					.append("                                  inner join C_Invoice si on sal.C_Invoice_id = si.C_Invoice_id and si.IsActive = 'Y'  ")
					.append("                                  and si.ConfType = " + DB.TO_STRING(X_HL7_MessageConf.CONFTYPE_ProfessionalClaim))
					.append("                                  where sal.IsActive = 'Y' ").append("                             )) ");
		} else if (StringUtils.isNotEmpty(confType) && X_HL7_MessageConf.CONFTYPE_InstitutionalClaim.equals(confType)) {
			sql.append("   WHERE P.IsActive = 'Y'                                ")
					.append("      AND  (P.CONFTYPE = ? OR                            ")
					.append("       P.C_Payment_ID     IN (                           ")
					.append("                                  select sal.C_Payment_id   ")
					.append("                                  from C_Payment sal ")
					.append("                                  inner join C_Invoice si on sal.C_Invoice_id = si.C_Invoice_id and si.IsActive = 'Y' ")
					.append("                                  and si.ConfType = " + DB.TO_STRING(X_HL7_MessageConf.CONFTYPE_ProfessionalClaim))
					.append("                                  where sal.IsActive = 'Y'  ").append("                                 ))");
		}

		sql.append(" AND   P.EXME_CtaPac_ID = ? ").append("    GROUP BY EAT.Type ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int cont = 1;
			if (StringUtils.isNotEmpty(adjustmentType)) {
				pstmt.setString(cont++, adjustmentType);
			}
			if (StringUtils.isNotEmpty(confType)) {
				pstmt.setString(cont++, confType);
			}
			pstmt.setInt(cont++, EXME_CtaPac_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.put(rs.getString(1), rs.getDouble(2));

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPaymentsByClaim: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	public MBPartner getBPartner() {
		if (getC_BPartner_ID() > 0 && mbPartner == null)
			mbPartner = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		return mbPartner;
	}

	public MEXMECtaPac getCtaPac() {
		if (mCtaPac == null || getEXME_CtaPac_ID() > 0)
			mCtaPac = new MEXMECtaPac(Env.getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		return mCtaPac;
	}

	/**
	 * Payments/Adjustments of Account.
	 *
	 * @param EXME_CtaPac_ID
	 *            Account
	 * @param AdjustmentType
	 *            Payment Concept
	 * @param ctx
	 *            Context
	 * @return Payment Amount group by AdjustmentType
	 * @author gvaldez
	 */
	public static HashMap<String, Double> getTotalPaymentsByType(int EXME_CtaPac_ID, Properties ctx) {
		HashMap<String, Double> retVal = new HashMap<String, Double>();
		// Double retVal = new Double(0);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT EAT.Type, SUM(Nvl(P.PayAmt, AL.Amount)) Amount ").append("  FROM  C_Payment P ")
				.append(" LEFT JOIN C_AllocationLine AL ON AL.C_Payment_ID = P.C_Payment_ID ")
				// .append(" INNER JOIN C_Charge EAT ON ((EAT.C_Charge_ID = AL.C_Charge_ID ) ")
				.append(" INNER JOIN C_Charge EAT on (p.c_charge_id = EAT.c_charge_id) ");
		// .append(" OR (AL.C_Charge_ID IS NULL AND EAT.Type = '"+X_C_Charge.TYPE_Payment+"')) ");
		sql.append("  WHERE P.EXME_CtaPac_ID = ? AND P.IsActive = 'Y'  ").append("GROUP BY EAT.Type ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int cont = 1;
			pstmt.setInt(cont++, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			Double payments = new Double(0);
			Double adjustments = new Double(0);
			while (rs.next()) {
				if (rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_Payment)
						|| rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_CopayPayment)
						|| rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_DeductiblePayment)
						|| rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_CoinsurancePayment)
						|| rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_OthersPayment)
						|| rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_InsurancePayments)) {
					payments = payments + rs.getDouble("Amount");
				} else if (rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_Adjustment)
						|| rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_BadDebtAdjustment)
						|| rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_PatientAdjustments)
						|| rs.getString("Type").equalsIgnoreCase(MEXMEAdjustmentType.TYPE_Others)) {
					adjustments = adjustments + rs.getDouble("Amount");
				}
			}
			retVal.put(MEXMEClaimPayment.TRANSACTIONTYPE_ProfessionalPayment, payments);
			retVal.put(MEXMEClaimPayment.TRANSACTIONTYPE_ProfessionAdjustment, adjustments);
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * Payments/Adjustments of Account.
	 *
	 * @param EXME_CtaPac_ID
	 *            Account
	 * @param AdjustmentType
	 *            Payment Concept
	 * @param ctx
	 *            Context
	 * @return Payment Amount group by AdjustmentType
	 * @author gvaldez
	 */
	public static List<MPayment> getAdvancePayments(int EXME_CtaPac_ID, Properties ctx) {
		List<MPayment> retVal = new ArrayList<MPayment>();
		// Double retVal = new Double(0);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT p.*  FROM  C_Payment P ")
		// .append(" LEFT JOIN C_AllocationLine AL ON AL.C_Payment_ID = P.C_Payment_ID ")
				.append("  WHERE P.EXME_CtaPac_ID = ? AND P.IsActive = 'Y' ");
		// .append(" AND AL.C_AllocationLine_ID is null ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int cont = 1;
			pstmt.setInt(cont++, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new MPayment(ctx, rs, null));

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * Allocation to post
	 *
	 * @param cash_Id
	 *            Cashier
	 * @param AdjustmentType
	 *            Payment Concept
	 * @return Id of allocation to post
	 */
	public static List<String> getAllocations(int cash_Id, String trxName) {
		List<String> str = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT al.C_Allocationhdr_Id As C_Allocationhdr_Id, hdr.Documentno ")
				.append("FROM C_Payment p INNER JOIN C_Allocationline al On p.C_Payment_Id = al.C_Payment_Id ")
				.append("INNER JOIN c_Allocationhdr hdr on al.C_Allocationhdr_Id = hdr.C_Allocationhdr_Id ")
				.append("WHERE p.C_Payment_ID in (select cLine.C_Payment_ID from c_cashline cLine where cLine.C_Cash_Id = ?)  AND P.IsActive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, cash_Id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				str.add(String.valueOf(rs.getInt("C_Allocationhdr_Id")));

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return str;
	}

	/**
	 * Payments/Adjustments of Account.
	 *
	 * @param EXME_CtaPac_ID
	 *            Account
	 * @param AdjustmentType
	 *            Payment Concept
	 * @param ctx
	 *            Context
	 * @return Payment Amount group by AdjustmentType
	 * @author gvaldez
	 */
	public static List<MPayment> getPayments(final int EXME_CtaPac_ID, final int C_BPartner_ID, final Properties ctx) {
		List<MPayment> retVal = new ArrayList<MPayment>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT P.* ").append(" FROM  C_Payment P ").append(" WHERE P.IsActive = 'Y'     ").append(" AND   P.EXME_CtaPac_ID = ? ")
				.append(" AND   P.C_BPartner_ID = ?  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_CtaPac_ID);
			pstmt.setInt(2, C_BPartner_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new MPayment(ctx, rs, null));

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * getPaymentsInfo
	 *
	 * @param EXME_CtaPac_ID
	 * @param EXME_Paciente_ID
	 * @param ctx
	 * @return
	 */
	public static BigDecimal getPaymentsInfo(final int EXME_CtaPac_ID, final int EXME_Paciente_ID, final Properties ctx) {
		BigDecimal retVal = Env.ZERO;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT NVL (SUM(C_Payment.PayAmt),0) AS Total ").append(" FROM   C_Payment  ")
		.append(" INNER JOIN C_Charge      ON C_Payment.C_Charge_ID        = C_Charge.C_Charge_ID AND C_Charge.Type IN ('C','D','I') ")
		.append(" INNER JOIN EXME_CtaPac   ON C_Payment.EXME_CtaPac_ID     = EXME_CtaPac.EXME_CtaPac_ID  ")
		.append(" INNER JOIN EXME_Paciente ON EXME_CtaPac.EXME_Paciente_ID = EXME_Paciente.EXME_Paciente_id ")
		.append(" WHERE C_Payment.IsActive = 'Y' ")
		.append(EXME_CtaPac_ID > 0 ? " AND EXME_CtaPac.EXME_CtaPac_ID = ?  " : " AND EXME_Paciente.EXME_Paciente_ID = ?  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, EXME_CtaPac_ID > 0 ? EXME_CtaPac_ID : EXME_Paciente_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retVal = rs.getBigDecimal("Total");

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPaymentsInfo: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * Payments/Adjustments of Account for Inst/Prof.
	 *
	 * @param EXME_CtaPac_ID
	 *            Account
	 * @param AdjustmentType
	 *            Payment Concept
	 * @param ctx
	 *            Context
	 * @return Payment Amount group by AdjustmentType
	 * @author gvaldez
	 */
	public static HashMap<String, Double> getGroupPaymentsByClaim(int EXME_CtaPac_ID, String claimType, Properties ctx) {
		HashMap<String, Double> retVal = new HashMap<String, Double>();

		HashMap<String, Double> payLst = MPayment.getPaymentsByClaimType(EXME_CtaPac_ID, null, claimType, ctx);
		Iterator<String> it = payLst.keySet().iterator();
		double adjustments = 0;
		double payments = 0;
		double coPay = 0;
		double deductible = 0;
		double coInsurance = 0;
		while (it.hasNext()) {
			String key = it.next();
			if (key.equalsIgnoreCase(MCharge.TYPE_Adjustment)
					|| key.equalsIgnoreCase(MCharge.TYPE_BadDebtAdjustment)
					|| key.equalsIgnoreCase(MCharge.TYPE_PatientAdjustments)
					|| key.equalsIgnoreCase(MCharge.TYPE_Others)) {
				adjustments = adjustments + payLst.get(key);
			} else if (key.equalsIgnoreCase(MCharge.TYPE_CoPay)) {
				coPay = coPay + payLst.get(key);
			} else if (key.equalsIgnoreCase(MCharge.TYPE_Deductible)) {
				deductible = deductible + payLst.get(key);
			} else if (key.equalsIgnoreCase(MCharge.TYPE_Coinsurance)) {
				coInsurance = coInsurance + payLst.get(key);
			} else if (key.equalsIgnoreCase(MCharge.TYPE_OthersPayment)
					|| key.equalsIgnoreCase(MCharge.TYPE_CoinsurancePayment)
					|| key.equalsIgnoreCase(MCharge.TYPE_CopayPayment)
					|| key.equalsIgnoreCase(MCharge.TYPE_DeductiblePayment)
					|| key.equalsIgnoreCase(MCharge.TYPE_Payment)
					|| key.equalsIgnoreCase(MCharge.TYPE_InsurancePayments)) {
				payments = payments + payLst.get(key);
			}
		}

		retVal.put(MCharge.TYPE_Payment, payments);
		retVal.put(MCharge.TYPE_Adjustment, adjustments);
		retVal.put(MCharge.TYPE_CoPay, coPay);
		retVal.put(MCharge.TYPE_Deductible, deductible);
		retVal.put(MCharge.TYPE_Coinsurance, coInsurance);

		return retVal;
	}

	/**
	 * getFilteredPayments
	 *
	 * @param ctx
	 * @param doctypeID
	 * @param dateIni
	 * @param dateFin
	 * @param docNo
	 * @param trxName
	 * @return
	 */
	public static List<MPayment> getFilteredPayments(Properties ctx, int doctypeID, Date dateIni, Date dateFin, String docNo, String trxName) {
		// List<MPayment> retval = new ArrayList<MPayment>();
		StringBuilder stWhere = new StringBuilder();
		if (doctypeID > 0) {
			stWhere.append(" C_Payment.c_doctype_id = " + doctypeID + "  AND  ");
		}
		if (dateIni != null) {
			stWhere.append(" C_Payment.datetrx >=  to_date('" + Constantes.getSdfFecha().format(dateIni) + "','dd/mm/yy')  AND ");
		}
		if (dateFin != null) {
			stWhere.append(" C_Payment.datetrx <=  to_date('" + Constantes.getSdfFecha().format(dateFin) + "','dd/mm/yy') AND ");
		}
		if (docNo != null) {
			stWhere.append(" C_Payment.documentno ='" + docNo + "' AND ");
		}
		stWhere.append("  c_payment.c_payment_id in (select record_id from fact_acct where record_id = C_Payment.c_payment_ID)");
		// stWhere.append(" 1=1");
		return new Query(ctx, Table_Name, stWhere.toString(), trxName).list();
		// return retval;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal bigDecimal) {
		available = bigDecimal;
	}

//	/**
//	 * Cuando en cuentas por cobrar se cobra una factura ( status: completa)
//	 * poniendo el numero de factura en la opcion de pagos y se completa el
//	 * pago, se crea el allocation header y el allocation line, y payment tiene
//	 * la relacion a invoice, pero cuando se relaciona el pago y la factura por
//	 * medio de la opcion de Compiere asignacion de pagos, se crea el allocation
//	 * header y el allocation line, pero en payment no se guarda la relacion con
//	 * la factura.
//	 *
//	 * @param ctx
//	 * @param reAsignacion
//	 * @param invoice
//	 * @param nuevaFact
//	 * @param trxName
//	 * @return
//	 */
//	public static String cancelarPagosCXC(final Properties ctx, final boolean reAsignacion, final MInvoice invoice, final MInvoice nuevaFact,
//			final String trxName) {
//		try {
//
//			List<MPayment> pagosCxC = new ArrayList<MPayment>();
//
//			// Buscamos los pagos realizados ASOCIADOS con la factura
//			pagosCxC =
//					MEXMEPagosFactura.pagosCxCAllocation(ctx, invoice.getC_Invoice_ID(), trxName);
//
//			for (int i = 0; i < pagosCxC.size(); i++) {
//				// Asociamos a al nueva factura el pago
//				if (reAsignacion && nuevaFact != null) {
//					pagosCxC.get(i).setC_Invoice_ID(nuevaFact.getC_Invoice_ID());
//				} else {
//					pagosCxC.get(i).setC_Invoice_ID(0);
//					// indicar que no esta asociado el pago a ninguna factura
//					pagosCxC.get(i).setIsAllocated(false);
//				}
//
//				if (!pagosCxC.get(i).save(trxName)) {
//					return "error.caja.payment.noSave";
//				}
//
//			}
//
//			// Buscamos los pagos RELACIONADOS con la factura y se borran ya que
//			// tienen status de DR
//			pagosCxC =
//					MEXMEPagosFactura.pagosCxCPayment(ctx, invoice.getC_Invoice_ID(), trxName);
//			for (int i = 0; i < pagosCxC.size(); i++) {
//
//				// Asociamos a al nueva factura el pago
//				if (reAsignacion && nuevaFact != null) {
//					pagosCxC.get(i).setC_Invoice_ID(nuevaFact.getC_Invoice_ID());
//				} else {
//					pagosCxC.get(i).setC_Invoice_ID(0);
//					// indicar que no esta asociado el pago a ninguna factura
//					pagosCxC.get(i).setIsAllocated(false);
//				}
//
//				if (!pagosCxC.get(i).save(trxName)) {
//					return "error.caja.payment.noSave";
//				}
//			}
//
//		} catch (Exception e) {
//			s_log.log(Level.WARNING, "", e);
//
//			return "error.caja.payment.noSave";
//		}
//
//		return null;
//	}

	/**
	 * Allocation to post
	 *
	 * @param cash_Id
	 *            Cashier
	 * @param AdjustmentType
	 *            Payment Concept
	 * @return Id of allocation to post
	 */
	public List<MAllocationLine> getAllocations(String trxName) {
		List<MAllocationLine> str = new ArrayList<MAllocationLine>();
		StringBuilder sql = new StringBuilder().append(" SELECT al.*                      ").append(" FROM  C_Payment p                ")
				.append(" INNER JOIN C_Allocationline al ON p.C_Payment_Id = al.C_Payment_Id ")
				.append(" INNER JOIN c_Allocationhdr hdr ON al.C_Allocationhdr_Id = hdr.C_Allocationhdr_Id ")
				.append(" WHERE p.IsActive = 'Y'           ").append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", MPayment.Table_Name, "p"))
				.append(" AND   p.C_Payment_ID = ?         ").append(" AND  al.C_Invoice_ID IS NOT NULL ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, getC_Payment_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				str.add(new MAllocationLine(getCtx(), rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return str;
	}

	/**
	 * Linea de caja
	 *
	 * @return
	 */
	public MCashLine getCashLine() {
		if (mCashLine == null) {
			mCashLine = MCashLine.getOfPayment(getCtx(), getC_Payment_ID(), get_TrxName());
		}
		return mCashLine;
	}

	/**
	 * extension
	 *
	 * @return
	 */
	public String getExtensionFacturada() {
		if (extensiones == null || extensiones.isEmpty()) {
			extensiones = MCtaPacPag.getLineaDePago(getCtx(), getC_Payment_ID(), getEXME_CtaPac_ID(), get_TrxName());
		}
		if (extensiones == null) {
			extensiones = "";
		}
		return extensiones;
	}

	/**
	 * ctapac pag
	 */
	public MCtaPacPag getCtaPacPag() {
		if (mCtaPacPag == null || mCtaPacPag.getEXME_CtaPacPag_ID() <= 0) {
			mCtaPacPag = MCtaPacPag.getMCtaPacPag(getCtx(), getC_Payment_ID(), get_TrxName());
		}
		return mCtaPacPag;
	}

	/**
	 * Nombre de extension
	 *
	 * @return
	 */
	public String getExtensionName() {
		MCtaPacPag bean = getCtaPacPag();
		if (bean != null) {
			return String.valueOf(bean.getExtension().getExtensionNo());
		}
		return "";
	}

	/**
	 * Cantidad de pago disponible(no relacionado a una factura)
	 *
	 * @return
	 */
	public BigDecimal getPaymentAvailable() {

		BigDecimal payAv = Env.ZERO;

		StringBuilder sql = new StringBuilder().append(" SELECT paymentAvailable(C_Payment_ID) AS payAvailable ")
				.append(" FROM   C_Payment              ").append(" WHERE  C_Payment_ID = ?       ");

		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Payment_ID());
			rSet = pstmt.executeQuery();
			if (rSet.next()) {
				payAv = rSet.getBigDecimal("payAvailable");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rSet, pstmt);
		}

		return payAv == null ? Env.ZERO : payAv;
	}

	public static MPayment initFromEXMEClaimPayment(Properties ctx, X_EXME_ClaimPayment exmeClaimPayment, X_EXME_ClaimPaymentH exmeClaimPaymentH,
			int docTypeID) {
		MPayment payment = new MPayment(ctx, 0, null);
		payment.setEXME_CtaPac_ID(exmeClaimPayment.getEXME_CtaPac_ID());
		payment.setC_Invoice_ID(exmeClaimPayment.getC_Invoice_ID());
		payment.setAmount(exmeClaimPayment.getAmt_Paid());
		payment.setC_Charge_ID(exmeClaimPayment.getC_Charge_ID());
		payment.setConfType(exmeClaimPaymentH.getBillingType());// T,P

		if (exmeClaimPayment.getC_BPartner_ID() <= 0) {
			payment.setC_BPartner_ID(exmeClaimPaymentH.getC_BPartner_ID());
		} else {
			payment.setC_BPartner_ID(exmeClaimPayment.getC_BPartner_ID());
		}
		payment.setC_DocType_ID(docTypeID);
		payment.setC_Currency_ID(Env.getContextAsInt(Env.getCtx(),
				"$C_Currency_ID"));
		payment.setPayAmt(exmeClaimPayment.getAmt_Paid());
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setEXME_ClaimPayment_ID(exmeClaimPayment.getEXME_ClaimPayment_ID());
    	return payment;
    }

	public static MPayment initFromCharge(Properties ctx, MCharge mChage,MInvoice mInvoice, int docTypeID){
		MPayment mPayment = new MPayment(ctx, 0, null);
		mPayment.setPayAmt(mChage.getPayAmt());
		mPayment.setC_Charge_ID(mChage.getC_Charge_ID());
		mPayment.setEXME_CtaPac_ID(mChage.getEXME_CtaPac_ID());
		mPayment.setC_Invoice_ID(mChage.getC_Invoice_ID());
		mPayment.setConfType(mInvoice.getConfType());// T,P
		mPayment.setAmount(mChage.getPayAmt());
		mPayment.setC_BPartner_ID(mInvoice.getC_BPartner_ID());
		if(mInvoice.getDateOrdered() != null){
			mPayment.setDateAcct(mInvoice.getDateOrdered());
		}
		mPayment.setC_DocType_ID(docTypeID);
		mPayment.setC_Currency_ID(Env.getContextAsInt(Env.getCtx(), "$C_Currency_ID"));
//		mPayment.setReciboNo("ticket" + i + " " + exmeCtaPacID);
//		mPayment.setC_BankAccount_ID(mConfigPre.getC_BankAccount_ID());
		mPayment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		mPayment.setDocAction(MPayment.DOCACTION_Complete);
		return mPayment;
	}

	/**
	 * Payments/Adjustments of Account by Invoice
	 * @param invoiceID Invoice
	 * @param chargeType Payment Concept
	 * @param ctx Context
	 * @return Payment Amount group by chargeType
	 * @author gvaldez
	 */
    public static HashMap<String, Double> getPaymentsByExtension(int ctaPacID, int ctaPacExtID,
    		String chargeType, Properties ctx) {
    	HashMap<String, Double> retVal = new HashMap<String, Double>();
    	//Double retVal = new Double(0);
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append("SELECT EAT.Type, SUM(P.PayAmt) Amount ")
    	   .append("   FROM  C_Charge EAT ")
   		   .append("   LEFT JOIN (C_Payment P ")
   		   .append("   LEFT JOIN C_Invoice I ON I.C_Invoice_ID = P.C_Invoice_ID ")
   		   .append("   INNER JOIN EXME_CtaPacExt CPE ON CPE.EXME_CtaPacExt_ID = ? ")
   		   .append("   LEFT JOIN EXME_ClaimPayment CP ON CP.EXME_ClaimPayment_ID = P.EXME_ClaimPayment_ID) ")
   		   .append("   ON P.EXME_CtaPac_ID =? AND P.C_CHARGE_ID = EAT.C_CHARGE_ID ")
   		   .append("   AND ((I.C_Invoice_ID Is NOT NULL AND I.EXME_CtaPacExt_ID = ?) ")
   		   .append("        OR (CP.EXME_ClaimPayment_ID Is NOT NULL AND CP.EXME_CtaPacExt_ID = ?) ")
   		   .append("        OR (CPE.ExtensionNo = 0 AND CP.EXME_ClaimPayment_ID IS NULL)) ")
   		   .append(" WHERE EAT.IsActive = 'Y'")
    	   .append(StringUtils.isNotEmpty(chargeType)?" AND EAT.Type  = ? ":StringUtils.EMPTY)
    	   .append("    GROUP BY EAT.Type ");

    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	try {
    		pstmt = DB.prepareStatement(sql.toString(), null);
    		int cont = 1;

			pstmt.setInt(cont++, ctaPacExtID);
			pstmt.setInt(cont++, ctaPacID);
			pstmt.setInt(cont++, ctaPacExtID);
			pstmt.setInt(cont++, ctaPacExtID);
    		if (StringUtils.isNotEmpty(chargeType)){
    			pstmt.setString(cont++, chargeType);
    		}

    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			retVal.put(rs.getString(1), rs.getDouble(2));

    		}
    	} catch (Exception e) {
    		s_log.log(Level.SEVERE, "getPaymentsByExtension: " + sql, e);
    	} finally {
    		DB.close(rs, pstmt);
    	}
    	return retVal;
    }

    /**
	 * Generamos el pago por concepto de anticipo a cuenta paciente
	 *
	 * @param forma
	 *            El bean de forma
	 * @param movto
	 *            La linea del movimiento del pago original

	 * @return Un objeto MPayment con la informacion insertada
	 * @throws Exception
	 */
	public static MPayment generarPago(Properties ctx, PagoLine movto, String recibo, MEXMECtaPac ctaPac,
			final int cashBookID, String trxName) throws Exception {

		Timestamp now = new Timestamp(new Date().getTime());
		MPayment payment = new MPayment(ctx, 0, trxName); // envio
		// trx
		// **GADC**

		// la fecha del servidor de aplicaciones
		payment.setDateTrx(now);
		payment.setIsReceipt(true);
		payment.setC_DocType_ID(MEXMEDocType.getOfName(ctx,
				Constantes.AR_RECEIPT, null));

		payment.setTrxType(MPayment.TRXTYPE_Sales);
		payment.setC_Charge_ID(movto.getAdjType());

		MConfigPre configPre = MConfigPre.get(ctx, null);
		if (configPre == null) {
			throw new Exception("error.caja.configPre");
		}
		payment.setC_BankAccount_ID(configPre.getC_BankAccount_ID());

		payment.setC_BPartner_ID(ctaPac.getC_BPartner_ID());

		MFormaPago fp = new MFormaPago(Env.getCtx(), movto.getFormaPagoID(), null);
		if (MFormaPago.PAYMENTRULE_Cash.equals(fp.getPaymentRule())) {
			payment.setTenderType(MPayment.TENDERTYPE_Cash);
		} else if (MFormaPago.PAYMENTRULE_Check.equals(fp.getPaymentRule())) {
			payment.setTenderType(MPayment.TENDERTYPE_Check);
			payment.setRoutingNo(movto.getRoutingNo());
			payment.setAccountNo(movto.getAccountNo());
			payment.setCheckNo(movto.getCheckNo());
			payment.setMicr(movto.getMicr());
			payment.setA_Name(movto.getA_Name());
		} else if (MFormaPago.PAYMENTRULE_CreditCard.equals(fp.getPaymentRule())) {
			payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
			payment.setCreditCardType(movto.getCreditCardType());
			payment.setCreditCardNumber(movto.getCreditCardNumber());
			payment.setCreditCardVV(movto.getCreditCardVV());
			payment.setCreditCardExpMM(movto.getCreditCardExpMM());
			payment.setCreditCardExpYY(movto.getCreditCardExpYY());
			payment.setA_Name(movto.getA_Name());
		} else if (MFormaPago.OTROS.equals(movto.getFormaPago())) {
			payment.setTenderType(MPayment.TENDERTYPE_Other);
		} else {
			payment.setTenderType(MPayment.TENDERTYPE_Other);
		}
		payment.setC_Currency_ID(ctaPac.getC_Currency_ID());
		payment.setPayAmt(movto.getMontoAplicado());
		payment.setAmount(movto.getMontoAplicado());
		//payment.setOProcessing("N");

		payment.setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
		payment.setEXME_Paciente_ID(ctaPac.getEXME_Paciente_ID());
		payment.setReciboNo(recibo);

		// Organizacion transaccional del operador que hizo la venta
		MEXMEOperador operador = MEXMEOperador.get(ctx, cashBookID, null);

		payment.setAD_OrgTrx_ID(operador.getAD_OrgTrx_ID());
		if (!payment.save(trxName)) {
			throw new Exception("error.caja.noSaveLine");
		}

		return payment;
	}

	/**
	 * Payments/Adjustments/Info of Account.
	 *
	 * @param EXME_CtaPac_ID Account
	 * @param EXME_CtaPacExt_ID AccountExt
	 * @param ctx
	 *            Context
	 * @return All records, including Payments, Adjustments and Info (Copay, Deductible, CoInsurance)
	 * @author gvaldez
	 */
	public static List<MPayment> getPaymentsByExtUSA(int ctaPacID, int ctaPacExtID, Properties ctx) {
		List<MPayment> retVal = new ArrayList<MPayment>();
		// Double retVal = new Double(0);
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT P.* ")
		   .append(" FROM C_Payment P ")
		   .append(" INNER JOIN EXME_CtaPacExt CPE ON CPE.EXME_CtaPacExt_ID = ? ")
		   .append(" LEFT JOIN C_Invoice I ON I.C_Invoice_ID = P.C_Invoice_ID ")
		   .append(" LEFT JOIN EXME_ClaimPayment CP ON CP.EXME_ClaimPayment_ID = P.EXME_ClaimPayment_ID ")
		   .append(" WHERE P.EXME_CtaPac_ID =? ")
		   .append(" AND ((I.C_Invoice_ID Is NOT NULL AND I.EXME_CtaPacExt_ID = ?) ")
		   .append("      OR (CP.EXME_ClaimPayment_ID Is NOT NULL AND CP.EXME_CtaPacExt_ID = ?)")
		   .append("  OR (CPE.ExtensionNo = 0 AND CP.EXME_ClaimPayment_ID IS NULL AND I.C_Invoice_ID IS NULL) ) ")
		   .append(" AND P.IsActive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int cont = 1;
			pstmt.setInt(cont++, ctaPacExtID);
			pstmt.setInt(cont++, ctaPacID);
			pstmt.setInt(cont++, ctaPacExtID);
			pstmt.setInt(cont++, ctaPacExtID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new MPayment(ctx, rs, null));

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * Total Payments & Adjustments of Extension.
	 *
	 * @param EXME_CtaPac_ID
	 *            Account
	 * @param EXME_CtaPacExt_ID
	 *            Extension
	 * @param AdjustmentType
	 *            Payment Concept
	 * @param ctx
	 *            Context
	 * @return Payment & Adjustment Amount group by Extension
	 * @author gvaldez
	 */
	public static BigDecimal getTotPmtsAdjByExtUSA(int ctaPacID, int ctaPacExtID, Properties ctx) {
		BigDecimal retVal = BigDecimal.ZERO;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT COALESCE(SUM(P.PAYAMT), 0) AS TOTAL ")
		   .append(" FROM C_Payment P ")
		   .append(" INNER JOIN EXME_CtaPacExt CPE ON CPE.EXME_CtaPacExt_ID = ? ")
		   .append(" LEFT JOIN C_Invoice I ON I.C_Invoice_ID = P.C_Invoice_ID ")
		   .append(" INNER JOIN C_Charge C on C.C_Charge_ID = P.C_Charge_ID ")
		   .append(" LEFT JOIN EXME_ClaimPayment CP ON CP.EXME_ClaimPayment_ID = P.EXME_ClaimPayment_ID ")
		   .append(" WHERE P.EXME_CtaPac_ID =? ")
		   .append(" AND ((I.C_Invoice_ID Is NOT NULL AND I.EXME_CtaPacExt_ID = ?) ")
		   .append("      OR (CP.EXME_ClaimPayment_ID Is NOT NULL AND CP.EXME_CtaPacExt_ID = ?)")
		   .append("  OR (CPE.ExtensionNo = 0 AND CP.EXME_ClaimPayment_ID IS NULL AND I.C_Invoice_ID IS NULL) ) ")
		   .append(" AND P.IsActive = 'Y' ")
		   .append(" AND C.Type not in (?,?,?) ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int cont = 1;
			pstmt.setInt(cont++, ctaPacExtID);
			pstmt.setInt(cont++, ctaPacID);
			pstmt.setInt(cont++, ctaPacExtID);
			pstmt.setInt(cont++, ctaPacExtID);

			pstmt.setString(cont++, MCharge.TYPE_Coinsurance);
			pstmt.setString(cont++, MCharge.TYPE_Deductible);
			pstmt.setString(cont++, MCharge.TYPE_CoPay);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal = rs.getBigDecimal("TOTAL");

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 *  Devuelve el ID del pago dado un numero de documento
	 *
	 *@param  documentNo  Numero de documento
	 *@param  ctx         Description of the Parameter
	 *@return             long Id del pago
	 *@throws  Exception  en caso de ocurrir un error en la consulta o no existir
	 *      el producto.
	 */
	public static int getPaymentID(Properties ctx, String documentNo) throws Exception {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT C_Payment.C_Payment_id ");
		sql.append("FROM C_Payment ");
		sql.append("WHERE  C_Payment.documentNo = ? ");
		sql.append("AND C_Payment.isActive = 'Y' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		return DB.getSQLValue(null, sql.toString(), documentNo);
	}

	/** Regresa la regla de pago segun el Tender Type */
	public String getPaymentRule() {
		String paymentRule = MPaySelectionCheck.PAYMENTRULE_Check;
		if (TENDERTYPE_CreditCard.equals(getTenderType())) {
			paymentRule = MPaySelectionCheck.PAYMENTRULE_CreditCard;
		} else if (TENDERTYPE_DirectDebit.equals(getTenderType())) {
			paymentRule = MPaySelectionCheck.PAYMENTRULE_DirectDebit;
		} else if (TENDERTYPE_DirectDeposit.equals(getTenderType())) {
			paymentRule = MPaySelectionCheck.PAYMENTRULE_DirectDeposit;
			// } else if (payment.getTenderType().equals(MPayment.TENDERTYPE_Check)) {
			// PaymentRule = MPaySelectionCheck.PAYMENTRULE_Check;
		}
		return paymentRule;
	}

	/**
	 * Crea la devolucion de anticipo, registra el movimento en la seleccion de pagos.
	 * como no hay una factura involucrada, no se crea el payselectionline (DUDA?)
	 * registra la devolucion en CtaPacPagos y actualiza el saldo de anticipo a cero
	 * @param advance
	 */
	public MPaySelectionCheck refundAdvancePayment(final MEXMEAnticipo advance) {
		final MPaySelectionCheck check;
		if (is_new()) {
			// Si tiene num cheque lo toma tanto para M_Payment: CheckNo/DocumentNo, como para c_PaySelectionCheck
			if(StringUtils.isEmpty(super.getCheckNo())) {
//				final String paymentRule = this.getPaymentRule();
//				DB.executeUpdate("UPDATE C_BankAccountDoc SET CurrentNext=CurrentNext+1 WHERE C_BankAccount_ID=? AND PaymentRule=? AND IsActive='Y' ",
//					new Object[] { getC_BankAccount_ID(), paymentRule }, null);
//				// obtenemos el documentno de MPaySelectionCheck
//				final String docNo = DB.getSQLValueString(null, //
//					"SELECT CurrentNext FROM C_BankAccountDoc WHERE C_BankAccount_ID=? AND PaymentRule=? AND IsActive='Y' ",//
//					getC_BankAccount_ID(), paymentRule);
				// obtenemos el documentno de MPaySelectionCheck
				final String docNo = getNextBankAccountDoc();
				super.setDocumentNo(docNo);
				super.setCheckNo(docNo);
			} else {
				super.setDocumentNo(super.getCheckNo());
			}
			setAmount(getPayAmt().negate());
			// se crea el pago CXP
			if (!save()) {
				throw new MedsysException();
			}
			check = createPaySelectionCheck();
			// check.setC_BP_BankAccount_ID(C_BP_BankAccount_ID);
			check.getParent().setProcessed(true);
			if (!check.save() || !check.getParent().save()) {
				throw new MedsysException();
			}

			// registra la devolucion en CtaPacPagos y actualiza el saldo de anticipo a cero
			// Se actualiza el anticipo de la extension 0
			if (advance != null && advance.getTotal() != null && advance.getTotal().compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal total = advance.getTotal().subtract(getPayAmt());
				advance.setTotal(total);
				advance.setSaldo(total);
				if (!advance.save(get_TrxName())) {
					throw new MedsysException();
				}
				total = getPayAmt();
				List<MEXMECtaPacPag> mCtaPacPagList = MEXMECtaPacPag.getAnticipos(getCtx(), advance.getEXME_CtaPacExt_ID(), null, null);
				for (MEXMECtaPacPag mexmeCtaPacPag : mCtaPacPagList) {
					if(total.compareTo(Env.ZERO) > 0){
						total.subtract(mexmeCtaPacPag.getAplicado());
						mexmeCtaPacPag.setAplicado(total.compareTo(Env.ZERO) > 0 ? Env.ZERO : total.abs());
						mexmeCtaPacPag.setSeDevolvio(true);
					}
					if(!mexmeCtaPacPag.save()){
						throw new MedsysException();
					}
				}
				// generamos la relacion de pago - cta pag
				final MCtaPacPag ctaPacPag = new MCtaPacPag(getCtx(), 0, get_TrxName());
				ctaPacPag.setC_Payment_ID(getC_Payment_ID());
				ctaPacPag.setEXME_CtaPacExt_ID(advance.getEXME_CtaPacExt_ID());
				ctaPacPag.setIsPay(false);
				ctaPacPag.setSeDevolvio(true);
				if (!ctaPacPag.save()) {
					throw new MedsysException();
				}
				//ctaPacPag.setDisponible(getAmount());
				ctaPacPag.setAplicado(Env.ZERO);
				if (!ctaPacPag.save()) {
					throw new MedsysException();
				}
			}
		} else {
			check = null;
		}
		return check;
	}

	/** Create new PaySelectionCheck and a new PaySelection*/
	public MPaySelectionCheck createPaySelectionCheck() {
		// Create new PaySelection
		MPaySelection payselect = createPaySelection();

		MPaySelectionCheck psCheck = new MPaySelectionCheck(payselect, this.getPaymentRule());
		psCheck.setC_BPartner_ID(getC_BPartner_ID());
		psCheck.setC_Payment_ID(getC_Payment_ID());
		psCheck.setIsReceipt(isReceipt());
		psCheck.setPayAmt(getPayAmt());
		psCheck.setDiscountAmt(getDiscountAmt());
		psCheck.setQty(1);
		psCheck.setDocumentNo(getDocumentNo());
		psCheck.setProcessed(true);
		// afalcone - [ 1871567 ] Wrong value in Payment document
		psCheck.setIsGeneratedDraft(!isProcessed());
		//
		if (!psCheck.save()) {
			throw new MedsysException();
		}
		return psCheck;
	}

	/** Create new PaySelection */
	public MPaySelection createPaySelection() {
		MPaySelection payselect = new MPaySelection(getCtx(), 0, get_TrxName());
		payselect.setAD_Org_ID(getAD_Org_ID());
		payselect.setC_AcctSchema_ID(Env.getC_AcctSchema_ID(getCtx()));
		payselect.setC_BankAccount_ID(getC_BankAccount_ID());
		payselect.setDescription(getDescription());
		payselect.setPayDate(getDateTrx());
		//Si hay tasa de conversión mayor a 0 y la moneda de seleccionada es diferente a la del esquema
		//guarda la conversión del monto pago-anticipo
		if(getRate().compareTo( BigDecimal.ONE ) > 0 && (payselect.getC_Currency_ID() != Env.getC_Currency_ID(getCtx()))){
			payselect.setTotalAmt(getPayAmt().multiply(getRate()).setScale(6, BigDecimal.ROUND_HALF_DOWN));
		}else {
			payselect.setTotalAmt(getPayAmt());
		}
		payselect.setName(Msg.translate(getCtx(), "C_Payment_ID") + ": " + getDocumentNo());
		payselect.setIsApproved(true);
		if (!payselect.save()) {
			throw new MedsysException();
		}
		return payselect;
	}

	/**
	 * @param ctapac cuenta paciente
	 * @return listado de devoluciones de pagos de cuenta paciente no realizadas en caja
	 */
	public static List<PagoLine> getPaymentsNoCashLine(Properties ctx, int ctapac){
		List<PagoLine> payments = new ArrayList<PagoLine>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM c_payment ");
			sql.append(" WHERE c_payment_id NOT IN  ");
			sql.append("  (SELECT c_payment_id FROM c_cashline WHERE exme_ctapac_id=?) ");
			sql.append(" AND exme_ctapac_Id=? AND Isactive='Y' ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ctapac);
			pstmt.setInt(2, ctapac);
			rs = pstmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				final MPayment payment = new MPayment(ctx, rs, null);
				final PagoLine linea = new PagoLine();
				linea.setPaymentID(payment.getC_Payment_ID());
				linea.setPosition(++i);
				linea.setFormaPagoID(0);
				linea.setFormaPagoName(MRefList.getListName(ctx, MPayment.TENDERTYPE_AD_Reference_ID, payment.getTenderType()));
				linea.setMontoRecibido(payment.getAmount());
				linea.setMontoAplicado(payment.getAmount());
				linea.setMonedaID(payment.getC_Currency_ID());
				linea.setMonedaName(MEXMECurrency.getMonedaDescription(ctx, linea.getMonedaID()));
				linea.setCreditCardType(payment.getCreditCardType());
				linea.setCreditCardNumber(payment.getCreditCardNumber());
				linea.setCreditCardVV(payment.getCreditCardVV());
				linea.setCreditCardExpMM(payment.getCreditCardExpMM());
				linea.setCreditCardExpYY(payment.getCreditCardExpYY());
				linea.setA_Name(payment.getA_Name());
				linea.setRoutingNo(payment.getRoutingNo());
				linea.setAccountNo(payment.getAccountNo());
				linea.setCheckNo(payment.getCheckNo());
				linea.setMicr(payment.getMicr());
				linea.setFecha(payment.getCreated());
				if (linea.getCreditCardType() != null) {
					linea.setCreditCardTypeName(MRefList.getListName(ctx, MPayment.CREDITCARDTYPE_AD_Reference_ID, linea.getCreditCardType()));
				}
				payments.add(linea);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "WCaptPago.getPayments", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return payments;
	}

	/** Factura relacionada */
	public MInvoice getInvoice() {
		return new MInvoice (getCtx(), getC_Invoice_ID(), get_TrxName());
	}

	/**
	 * 	Crea una nueva linea de pago a partir de otra
	 * 	@param ctx contexto
	 *	@param C_Cash_ID El identificador de la caja abierta
	 * 	@param trxName El nombre de la transaccion
	 *	@return la linea del pago
	 */
	public static MPayment copyFrom(MPayment from, int C_Cash_ID, String trxName) {
		final MPayment to = new MPayment(from.getCtx(), 0, null);
		to.set_TrxName(trxName);
		PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		//to.setC_Cash_ID(C_Cash_ID);
		to.setProcessed(false);
		return to;
	} // copyFrom


	public static Timestamp getDateTrxForId(int cPaymentId) {
		Timestamp retVal =
				DB.getSQLValueTS(
						null,
						"SELECT DateTrx FROM C_Payment WHERE C_Payment_ID = ?",
						new Object[] {cPaymentId}
				);

		return retVal;
	}


	/**
	 * Returns the transaction date to be used for this payment based on the
	 * following rule :
	 * <blockquote>
	 * If the payment date (c_payment.datetrx) is before the creation date
	 * (c_payment.created) and greather than or equals to the invoice date
	 * (c_invoice.dateinvoiced), then the allocation will be created with the
	 * payment date, otherwise the current date will be used.
	 * </blockquote>
	 *
	 * @return The transaction date to be used.
	 */
	private Timestamp getTransactionDate() {

		Timestamp dateTrx = Env.getCurrentDate();

		if(getC_Invoice_ID() > 0) {
			Timestamp dateInvoiced = MInvoice.getDateInvoicedForId(getC_Invoice_ID());

			// as timestamp includes hour:minute:second.millisecond, we need to exclude that stuff
			Calendar originalDateTrx = Calendar.getInstance();
			originalDateTrx.setTimeInMillis(getDateTrx().getTime());
			originalDateTrx.set(Calendar.HOUR, 0);
			originalDateTrx.set(Calendar.MINUTE, 0);
			originalDateTrx.set(Calendar.SECOND, 0);
			originalDateTrx.set(Calendar.MILLISECOND, 0);


			Calendar dateCreated = Calendar.getInstance();
			dateCreated.setTimeInMillis(getCreated().getTime());
			dateCreated.set(Calendar.HOUR, 0);
			dateCreated.set(Calendar.MINUTE, 0);
			dateCreated.set(Calendar.SECOND, 0);
			dateCreated.set(Calendar.MILLISECOND, 0);

			if(originalDateTrx.before(dateCreated)
					&& getDateTrx().compareTo(dateInvoiced) >= 0) {
				dateTrx = getDateTrx();
			}
		} else {
			dateTrx = getDateTrx();
		}

		return dateTrx;
	}


	/**
	 *
	 * @param EXME_CtaPac_ID
	 * @param payConcept
	 * @param ctx
	 * @return
	 */
	public static List<MPayment> getPaymentsEncounter(int EXME_CtaPac_ID, String payConcept, Properties ctx) {

		final List<MPayment> list = new Query(ctx, Table_Name, "EXME_CTAPAC_ID=? AND PAYCONCEPT=?", null)//
				.setParameters(EXME_CtaPac_ID, payConcept).list();

		return list;
	}

	//CARD #1299
	/** Nombre de la forma en que se hizo el pago */
	public String getFormaPagoName(){
		if(formaPago==null || formaPago.isEmpty()){
			final MFormaPago mForma = getFormaPago();
			if(mForma==null){
				formaPago = StringUtils.EMPTY;
			} else {
				formaPago = mForma.getName();
			}
		}
		return formaPago;
	}

	/** Forma en que se realizo el pago */
	public MFormaPago getFormaPago() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MFormaPago mFormaPago = null;
		try {

			final StringBuilder sql = new StringBuilder()
			.append(" SELECT fp.*               ")
			.append(" FROM C_CashLine cl        ")
			.append(" INNER JOIN EXME_FormaPago fp ON fp.EXME_FormaPago_ID = cl.EXME_FormaPago_ID ")
			.append(" WHERE cl.IsActive = 'Y'   ")
			.append(" AND   cl.C_Payment_ID = ? ")
			.append(" Order by cl.C_CashLine_ID ASC ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, getC_Payment_ID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mFormaPago =  new MFormaPago(getCtx(), rs, null);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MPayment.getFormaPago catch", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return mFormaPago;
	}

	public MUser getUser(){
		if(user==null){
			user = new MUser(getCtx(), getCreatedBy(), null);
		}
		return user;
	}

	public String getUserName(){
		if(getUser()==null){
			return StringUtils.EMPTY;
		} else {
			return getUser().getName();
		}
	}
	
	
	/**
	 * Card #1313
	 * Crea un pago de tipo anticipo para proveedor, crea el documento de tipo cheque/transferencia
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param bean Parametros para crear el registro de C_Payment, C_PaySelection y C_PaySelectionCheck
	 * @param trxName Transaccion
	 * @return Listado de errores
	 */
	
	public static ErrorList createAdvancePayment(Properties ctx, PaymentBean bean, String trxName, boolean isPrepayment) {
		
		final ErrorList errorList = bean.validate();
		if (errorList.isEmpty()) {
			try {
				// crea el pago
				final MPayment payment = newPayment(ctx, bean, trxName);
				if (isPrepayment == true){
					payment.setIsPrepayment(isPrepayment);//true: si viene desde WGenerateAdvance false: WPaymentNoVoucher
					// crea el cheque
					final MPaySelectionCheck check = payment.newPaySelectionCheck();
				
					payment.setIsAllocated(false);
					payment.startWorkflow(errorList, DocAction.ACTION_Complete, DocAction.STATUS_Completed, trxName);
					if(errorList.isEmpty()){
						bean.setPaymentCheck(check);
					}
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "-- confirm : ", e);
				errorList.add(Error.EXCEPTION_ERROR, e.getMessage());
			}
		}
		return errorList;
	}

	/**
	 * Card #1313
	 * Crea un pago para multiples facturas, crea el documento de tipo cheque/transferencia y la relacion del pago y facturas.
	 * El total del pago puede cubrir el total de las facturas o solo parcialidades.
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param bean Parametros para crear el registro de C_Payment, C_PaySelection y C_PaySelectionCheck
	 * @param invoices Lista de facturas que se relacionaran al pago C_PaySelectionLine,
	 *            debe contener informacion en las columnas: <br>
	 *            <li>Monto de la factura a cubir por el pago {@link MInvoice#getPaySelectCheckAmount()}</li><br>
	 *            <li>Resultado de la funcion: currencyConvert(invoiceOpen(...)...) {@link MInvoice#getPaySelectAmountDue()}</li><br>
	 *            <li>Resultado de la funcion: currencyConvert(invoiceOpen(...)-paymentTermDiscount(...)...) {@link MInvoice#getPaySelectAmountPay()}</li>
	 * @param trxName Transaccion
	 * @return Listado de errores
	 */
	public static ErrorList createPayment(Properties ctx, PaymentBean bean, List<BeanPaySelect> invoices, String trxName) {
		final ErrorList errorList = bean.validate();
		if (errorList.isEmpty()) {
			try {
				//Si hay tasa de conversión, tomar el saldo convertido
				final BigDecimal totalPayamt; // En moneda de pesos
				if(bean.getRate().compareTo( BigDecimal.ONE ) > 0){
//					if (conversionAmt == null || BigDecimal.ZERO.compareTo(conversionAmt) == 0){
//						setConversionAmt(BigDecimal.ONE);
//					}
					totalPayamt = bean.getPayAmt().multiply(bean.getRate()).setScale(6, BigDecimal.ROUND_HALF_DOWN);
				} else {
					totalPayamt = bean.getPayAmt();
				}
				// Crear el pago en dolares 
				final MPayment payment = newPayment(ctx, bean, trxName);
				MPaySelectionCheck check = bean.getPaymentCheck();
				if (check == null) {
					check = payment.newPaySelectionCheck();//
				} else {
					check.set_TrxName(trxName);
				}
				
//				BigDecimal totalPayamt = bean.getPayAmt();
				if (!payment.is_new() && bean.getPayAmt()/*totalPayamt*/.compareTo(payment.getBalanceAmt()) > 0) {// FIXME
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "El monto asignado excede el total del pago."));
				} else {
					BigDecimal totalAmt = BigDecimal.ZERO;

					String errorMsg1 = new StringBuilder()//
						.append(Utilerias.getMsg(ctx, "error.caja.montoAplicado")).append(". ")//
						.append(Utilerias.getMsg(ctx, "msj.factura")).append(": ").toString();
					int line = 0;
					for (BeanPaySelect invoice : invoices) {
						// validar que la factura tenga un importe a pagar mayor a cero
						if (invoice.getImp() == null || invoice.getImp().compareTo(BigDecimal.ZERO) <= 0) {
							errorList.add(Error.VALIDATION_ERROR, errorMsg1 + invoice.getDocumentNo());
						} else if (invoice.getDueAmt() == null || invoice.getPayAmt() == null) {
							// open amt / pay amt
							errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("msg.error.select.cta"));
						} else if (invoice.getImp().compareTo(invoice.getPayAmt()) > 0) {
							errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("error.caja.montoTC"));
						}
						if (!errorList.isEmpty()) {
							break;
						}
						// se crea la linea relacionada a cada factura
						createPaySelectionLine(check, invoice, line += 10);
						//
						if(bean.getRate().compareTo( BigDecimal.ONE ) > 0){
							totalAmt = totalAmt.add(invoice.getImp().multiply(bean.getRate())).setScale(6, BigDecimal.ROUND_HALF_DOWN);
						} else {
							totalAmt = totalAmt.add(invoice.getImp());//
						}
					}

					// validar totales de pago, con totales sumados en las facturas
					if (totalPayamt.compareTo(totalAmt) != 0) {// 
						errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "error.caja.montoTC"));
					} else {
						// se completan los documentos
						if (DocAction.STATUS_Completed.equals(payment.getDocStatus())) {
							payment.setFullPayment(false);
							payment.allocateIt(); // Create Allocation Records
							payment.testAllocation();
							if (!payment.save()) {
								throw new MedsysException();
							}
						} else {
							payment.setFullPayment(false);
							payment.setIsAllocated(totalPayamt.compareTo(payment.getPayAmt()) == 0);
							payment.startWorkflow(errorList, DocAction.ACTION_Complete, DocAction.STATUS_Completed, trxName);
						}

						if (errorList.isEmpty()) {
							check.setProcessed(true);
							check.save();
							bean.setPaymentCheck(check);
						}
					}
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "-- confirm : ", e);
				errorList.add(Error.EXCEPTION_ERROR, e.getMessage());
			}
		}
		return errorList;
	}
	
	/**
	 * @return Registro de MPaySelectionCheck a partir del C_Payment
	 *         Le asigna como nombre la etiqueta "Seleccion de pago" + regla de pago y fecha de pago
	 *         guarda los cambio en C_PaySelection y C_PaySelectionCheck
	 */
	public MPaySelectionCheck newPaySelectionCheck() {
		final MPaySelectionCheck check = createPaySelectionCheck();
		final StringBuilder name = new StringBuilder();
		name.append(Msg.getMsg(getCtx(), "VPaySelect"));
		name.append(" - ");
		name.append(MRefList.getListName(getCtx(),//
			MPaySelectionCheck.PAYMENTRULE_AD_Reference_ID, check.getPaymentRule()));
		name.append(" - ").append(getDateTrx());
		check.getParent().setName(name.toString());
		check.getParent().setProcessed(true);
		if (!check.save() || !check.getParent().save()) {
			throw new MedsysException();
		}
		return check;
	}
	
	/**
	 * Crea el pago de tipo cheque/transferencia, 
	 * genera la secuencia del cheque (cuando no esta definido) segun la cuenta bancaria
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param bean Parametros para crear el registro de C_Payment, C_PaySelection y C_PaySelectionCheck
	 * @param trxName Transaccion
	 * @return Pago de tipo cheque o transferencia
	 */
	public static MPayment newPayment(Properties ctx, PaymentBean bean, String trxName) {
		final MPayment payment = new MPayment(ctx, bean.getId(), trxName);
		if (payment.is_new()) {
			PaymentBean.fillPayment(bean, payment);
			// Si tiene num cheque lo toma tanto para M_Payment: CheckNo/DocumentNo, como para c_PaySelectionCheck
			if (StringUtils.isEmpty(payment.getCheckNo())) {
				// obtenemos el documentno de MPaySelectionCheck
				final String docNo = payment.getNextBankAccountDoc();
				payment.setDocumentNo(docNo);
				payment.setCheckNo(docNo);
			} else {
				payment.setDocumentNo(payment.getCheckNo());
			}
			// se crea el pago
			if (payment.save()) {
				if(bean.getId() != payment.getC_Payment_ID()) {
					bean.setId(payment.getC_Payment_ID());
				}
			} else {
				throw new MedsysException();
			}
		} else {
			payment.setAllocationDate(bean.getAllocationDate());
		}
		return payment;
	}

	/**
	 * Crea la relacion Factura - Cheque (C_PaySelectionLine)
	 * guarda los cambios en C_PaySelectionCheck
	 * 
	 * @param check Cheque
	 * @param invoice Factura
	 * @param line numero de secuencia (10,20,30...90) de acuerdo a la cantidad y orden de las facturas
	 * @return
	 */
	public static MPaySelectionLine createPaySelectionLine(MPaySelectionCheck check, BeanPaySelect invoice, int line) {
		// se crea la linea relacionada a cada factura
		final MPaySelectionLine payLine = new MPaySelectionLine(check.getParent(),//
			line, check.getPaymentRule());
		payLine.setInvoice(invoice.getInvoiceId(), //
			false, // isSOTrx
			invoice.getImp().multiply(invoice.getRateInvoice()), //invoice.getDueAmt(), //
			invoice.getImp().multiply(invoice.getRateInvoice()), //invoice.getPayAmt(),//
			(invoice.getDueAmt().multiply(invoice.getRateInvoice())).subtract(invoice.getPayAmt()));
		payLine.setC_PaySelectionCheck_ID(check.getC_PaySelectionCheck_ID());
		payLine.setC_Payment_ID(check.getC_Payment_ID());
		payLine.setProcessed(true);
		if (!payLine.save()) {
			throw new MedsysException();
		}
		check.addLine(payLine);// ???
		if (!check.save()) {
			throw new MedsysException();
		}
		return payLine;
	}
	
	/**
	 * Crea el numero de cheque/transferencia de un pago, segun la regla de pago y la cuenta bancaria.
	 * @return
	 */
	protected String getNextBankAccountDoc() {
		final String paymentRule = getPaymentRule();
		final String docNo = PaymentUtils.getNextBankAccountDoc(getC_BankAccount_ID(), paymentRule, getTenderType());
		PaymentUtils.updateNextBankAccountDoc( getC_BankAccount_ID(), paymentRule);
		return docNo;
	}
	

	public Timestamp getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Timestamp allocationDate) {
		this.allocationDate = allocationDate;
	}
	
	
	public MDocType getDocType(){
		if (mDocType == null){
			mDocType = new MDocType(getCtx(), getC_DocType_ID(), get_TrxName());
		}
		return mDocType;
	}
	
	public static List<CollectionReportBean> getCollectionReport(Properties ctx, Timestamp startDate, Timestamp endDate, String trxName) {
		final List<CollectionReportBean> list = new ArrayList<CollectionReportBean>();
		final String AD_Language = Env.getAD_Language(ctx);
		final boolean isBaseLanguage = Env.isBaseLanguage(AD_Language, Table_Name);
		
		final List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  iv.DocumentNo ");
		sql.append("  AS Numero_de_documento, ");
		if (!isBaseLanguage) {
			sql.append(" doctrl.Name   AS Tipo_de_documento,");
		} else {
			sql.append("  dt.Name ");
			sql.append("  AS Tipo_de_documento, ");
		}
		sql.append("  iv.DateTrx ");
		sql.append("  AS Fecha_del_documento, ");
		sql.append("  so.Value ||' '|| so.Name ");
		sql.append("  AS Proveedor_Cliente, ");
		sql.append("  iv.PayAmt ");
		sql.append("  AS Monto ");
		sql.append("FROM ");
		sql.append("  C_Payment iv ");
		sql.append("  INNER JOIN c_doctype dt ");
		sql.append("  ON dt.c_doctype_id = iv.c_doctype_id ");
		if (!isBaseLanguage) {
			sql.append("INNER JOIN C_DocType_Trl doctrl ON doctrl.c_doctype_id = iv.c_doctype_id    AND doctrl.ad_Language = ?");
			params.add(AD_Language);
		}
		sql.append("  INNER JOIN c_Bpartner so ");
		sql.append("  ON so.c_Bpartner_id = iv.c_Bpartner_id ");
		sql.append("WHERE ");
		sql.append("  iv.IsActive = 'Y' ");
		sql.append("  and iv.DateTrx between ? and ? ");
		params.add(startDate);
		params.add(endDate);
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx,Constantes.SPACE, Table_Name, "iv"));
		sql.append("  Order by iv.DateTrx ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CollectionReportBean collectionReportBean = new CollectionReportBean();
				collectionReportBean.setDocumentNo(rs.getString("Numero_de_documento"));
				collectionReportBean.setDocumentType(rs.getString("Tipo_de_documento"));
				collectionReportBean.setDocumentDate(rs.getTimestamp("Fecha_del_documento"));
				collectionReportBean.setName(rs.getString("Proveedor_Cliente"));
				collectionReportBean.setAmount(rs.getBigDecimal("Monto"));

				list.add(collectionReportBean);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
			
		return list;
	}
	
	@Override
	protected boolean beforeDelete() {
		//Ticket#08773 Se deshabilita el borrado de pagos
		return false;
	}

	
	/**
	 * Payments/Adjustments of Account.
	 *
	 * @param EXME_CtaPac_ID
	 *            Account
	 * @param AdjustmentType
	 *            Payment Concept
	 * @param ctx
	 *            Context
	 * @return Payment Amount group by AdjustmentType
	 * @author gvaldez
	 */
	public static List<MPayment> getNetAdvancePayments(int EXME_CtaPac_ID, Properties ctx, final String trxName) {
		final List<MPayment> retVal = new ArrayList<MPayment>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT paymentAvailable(P.C_Payment_ID) as saldo, p.* ")
		.append(" FROM  C_Payment P ")
		.append(" WHERE P.IsActive = 'Y' ")
		.append(" AND P.EXME_CtaPac_ID = ? ")
		.append(" AND paymentAvailable(P.C_Payment_ID) > 0 ")
		.append(" ORDER BY  P.PayAmt DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			int cont = 1;
			pstmt.setInt(cont++, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MPayment   pay   = new MPayment(ctx, rs, trxName);
				pay.setAvailable(rs.getBigDecimal("saldo"));
				retVal.add(pay);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "getPayments: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}
	
	/**
	 * Obtiene el total de facturas pendientes por proveedor
	 * 
	 * @param ctx Contexto
	 * @param invoiceDate (obligatorio)
	 * @param payRule Regla de pago (no obligatorio)
	 * @param currencyId moneda (obligatorio)
	 * @param trxName Nombre de transaccion
	 * @return
	 */
	public static List<PaymentBean> getPaymentPartner(Properties ctx, Timestamp invoiceDate
			, String payRule, int currencyId, Boolean dueBill, int partnerID, String trxName){
		final List<PaymentBean> list = new ArrayList<PaymentBean>();
		final List<Object> params = new ArrayList<Object>();
		params.add(currencyId);
		params.add(currencyId);

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT p.DateTrx,p.DocumentNo,p.C_Payment_ID,"); // 1..3
		sql.append("c.ISO_Code,p.PayAmt,"); // 4..5
		sql.append("currencyConvert(p.PayAmt,p.C_Currency_ID,?,");
		sql.append("                p.DateTrx,p.C_ConversionType_ID,");
		sql.append("                p.AD_Client_ID,p.AD_Org_ID),");// 6 #1
		sql.append("currencyConvert(paymentAvailable(C_Payment_ID),");
		sql.append("                p.C_Currency_ID,?,p.DateTrx,");
		sql.append("                p.C_ConversionType_ID,p.AD_Client_ID,");
		sql.append("                p.AD_Org_ID),"); // 7
		sql.append(" p.MultiplierAP, pa.Name AS Client");
		sql.append(" FROM C_Payment_v p"); // Corrected for AP/AR
		sql.append(" INNER JOIN C_Currency c ON (p.C_Currency_ID=c.C_Currency_ID) ");
		sql.append(" INNER JOIN C_BPartner pa ON (pa.C_BPartner_ID=p.C_BPartner_ID) ");
		sql.append(" WHERE p.IsAllocated='N' AND p.Processed='Y'");
		sql.append(" AND p.C_Charge_ID IS NULL"); // Prepayments OK

		if(partnerID>0){
			params.add(partnerID);
			sql.append(" AND p.C_BPartner_ID=?"); // #3
		}



		sql.append(" AND COALESCE(p.EXME_CtaPac_ID,0) <=0 "); // Lama
		//		if (!multiCurrency.isSelected()) {
		//			sql.append(" AND p.C_Currency_ID=?");				//      #4
		//		}

		sql.append(" ORDER BY p.DateTrx,p.DocumentNo");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				PaymentBean paySelect = new PaymentBean();

				//				paySelect.setPartner(rs.getString("name"));
				//				paySelect.setPartnerId(rs.getInt("C_BPartner_ID"));
				//				paySelect.setCurrencyId(rs.getInt("C_Currency_ID"));
				//				paySelect.setPayAmt(rs.getBigDecimal("AmountPay"));
				//				paySelect.setDueAmt(rs.getBigDecimal("AmountDue"));
				//				paySelect.setCountInvoice(rs.getInt("countInvoice"));
				//				paySelect.setVencidas(rs.getInt("vencidas"));


				paySelect.setDateTrx(rs.getTimestamp(1));       //  1-TrxDate
				paySelect.setId(rs.getInt(3));
				paySelect.setCheckNo(rs.getString(2));
				paySelect.setCurrencyName(rs.getString(4));  //  3-Currency
				paySelect.setPayAmt(rs.getBigDecimal(5)); //  4-PayAmt
				paySelect.setConversionAmt(rs.getBigDecimal(6));//  3/5-ConvAmt
				BigDecimal available = rs.getBigDecimal(7); // 4/6-ConvOpen/Available
				if (available != null && available.signum() != 0) {	//	nothing available
					paySelect.setAvailable(available);		
					paySelect.setAmtPayment(BigDecimal.ZERO);					//  5/7-Payment
					paySelect.setAmtMultiplier(rs.getBigDecimal(8));		//  6/8-Multiplier
				}
				paySelect.setbPartnerName(rs.getString("Client"));
				list.add(paySelect);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	public PaymentAmount getPaymentAmtConv() {
		if(paymentAmtConv==null) {
			setPaymentAmount(getC_Currency_ID(),get_TrxName());
		}
		return paymentAmtConv;
	}
	
	/**
	 * Busca los datos de conversiones y montos disponibles del pago
	 * @param currencyId Moneda a conertir
	 * @param trxName Nombre de transaccion
	 * @return
	 */
	public PaymentAmount setPaymentAmount(final int currencyId, final String trxName){
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append("SELECT p.C_Payment_ID ")
		.append(" , p.PayAmt ") 
		.append(" , currencyConvert(p.PayAmt       ")
		.append("                , p.C_Currency_ID ")
		.append("                , ?               ") // #1 C_Currency_ID
		.append("                , p.DateTrx       ")
		.append("                , p.C_ConversionType_ID          ")
		.append("                , p.AD_Client_ID  ")
		.append("                , p.AD_Org_ID )   AS PayAmtConv   ")
		.append(" , paymentAvailable(C_Payment_ID) AS Open         ")
		.append(" , currencyConvert(paymentAvailable(C_Payment_ID) ")
		.append("                , p.C_Currency_ID ")
		.append("                , ?               ")// #2 C_Currency_ID
		.append("                , p.DateTrx       ")
		.append("                , p.C_ConversionType_ID           ")
		.append("                , p.AD_Client_ID  ")
		.append("                , p.AD_Org_ID   ) AS OpenConv     ") 
		//		.append(" p.MultiplierAP            ")
		.append(" FROM C_Payment_v p        ")
		.append(" WHERE p.IsActive = 'Y'    ")
		.append(" AND   p.C_Payment_ID = ? ");// #3 C_Payment_ID

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if(paymentAmtConv == null){
			paymentAmtConv = new PaymentAmount();
		}
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, currencyId);
			pstmt.setInt(2, currencyId);
			pstmt.setInt(3, getC_Payment_ID());

			rs = pstmt.executeQuery();
			MCurrency mCurrencyDefault = getmCurrencyDefault(getCtx());
			
			if (rs.next()) {
				
				paymentAmtConv.setAmount(rs.getBigDecimal("PayAmt"));
				paymentAmtConv.setConvertAmt(rs.getBigDecimal("PayAmtConv"));
				paymentAmtConv.setOpen(rs.getBigDecimal("Open"));
				paymentAmtConv.setOpenConvert(rs.getBigDecimal("OpenConv"));
				paymentAmtConv.setApply(Env.ZERO);
				paymentAmtConv.setApplyConvert(Env.ZERO);
				paymentAmtConv.setBad(Env.ZERO);
				paymentAmtConv.setBadConvert(Env.ZERO);
				paymentAmtConv.setBalance(Env.ZERO);
				paymentAmtConv.setBalanceConvert(Env.ZERO);
				paymentAmtConv.setWriteOff(Env.ZERO);
				paymentAmtConv.setWriteOffConvert(Env.ZERO);
				paymentAmtConv.setProfitLoss(Env.ZERO);
				
				// Moneda del cobro (PESOS) VS moneda del sistema (DOLAR)
				if(getC_Currency_ID() != mCurrencyDefault.getC_Currency_ID()){
					paymentAmtConv.setAmount(rs.getBigDecimal("PayAmt"));
					paymentAmtConv.setConvertAmt(rs.getBigDecimal("PayAmtConv"));
					paymentAmtConv.setOpen(rs.getBigDecimal("Open"));
					paymentAmtConv.setOpenConvert(rs.getBigDecimal("OpenConv"));
					paymentAmtConv.setApply(Env.ZERO);
					paymentAmtConv.setApplyConvert(Env.ZERO);
					paymentAmtConv.setBad(Env.ZERO);
					paymentAmtConv.setBadConvert(Env.ZERO);
					paymentAmtConv.setBalance(Env.ZERO);
					paymentAmtConv.setBalanceConvert(Env.ZERO);
					paymentAmtConv.setWriteOff(Env.ZERO);
					paymentAmtConv.setWriteOffConvert(Env.ZERO);
					paymentAmtConv.setProfitLoss(Env.ZERO);
				}

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return paymentAmtConv;
	}
		
		/** Moneda por defecto del esquema contable */
		public static MCurrency getmCurrencyDefault(final Properties ctx) {
			MCurrency mCurrencyDefault = null;
		
			// Moneda por defecto
			if(mCurrencyDefault==null){
				final MAcctSchema mAcctSchema = new MAcctSchema(ctx, Env.getC_AcctSchema_ID(ctx), null); 
				mCurrencyDefault = new MCurrency(Env.getCtx(), mAcctSchema.getC_Currency_ID(),null);
			}
			return mCurrencyDefault;
		}

	/** Montos del pago convertidos a moneda extranjera */ 
	public class PaymentAmount { 
		/** Importe del cobro */
		private BigDecimal					amount;
		private BigDecimal					convertAmt;
		/** Saldo de las facturas */
		private BigDecimal					open;
		private BigDecimal					openConvert;
		/** Saldo de las facturas */
		private BigDecimal					openInvoice;
		private BigDecimal					openInvoiceConvert;
		/** Importe a aplicar */
		private BigDecimal					apply;
		private BigDecimal					applyConvert;
		/** Monto  incobrable */
		private BigDecimal					bad;
		private BigDecimal					badConvert;
		/** Nuevo saldo de la factura */
		private BigDecimal					balance;
		private BigDecimal					balanceConvert;
		/** Cobro no aplicado */
		private BigDecimal					writeOff;
		private BigDecimal					writeOffConvert;
		/** Ganancia perdida bancaria (En moneda del cliente) */
		private BigDecimal					profitLoss;
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public BigDecimal getConvertAmt() {
			return convertAmt;
		}
		public void setConvertAmt(BigDecimal convertAmt) {
			this.convertAmt = convertAmt;
		}
		public BigDecimal getOpen() {
			return open;
		}
		public void setOpen(BigDecimal open) {
			this.open = open;
		}
		public BigDecimal getOpenConvert() {
			return openConvert;
		}
		public void setOpenConvert(BigDecimal openConvert) {
			this.openConvert = openConvert;
		}
		public BigDecimal getApply() {
			return apply;
		}
		public void setApply(BigDecimal apply) {
			this.apply = apply;
		}
		public BigDecimal getApplyConvert() {
			return applyConvert;
		}
		public void setApplyConvert(BigDecimal applyConvert) {
			this.applyConvert = applyConvert;
		}
		public BigDecimal getBad() {
			return bad;
		}
		public void setBad(BigDecimal bad) {
			this.bad = bad;
		}
		public BigDecimal getBadConvert() {
			return badConvert;
		}
		public void setBadConvert(BigDecimal badConvert) {
			this.badConvert = badConvert;
		}
		public BigDecimal getBalance() {
			return balance;
		}
		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}
		public BigDecimal getBalanceConvert() {
			return balanceConvert;
		}
		public void setBalanceConvert(BigDecimal balanceConvert) {
			this.balanceConvert = balanceConvert;
		}
		public BigDecimal getWriteOff() {
			return writeOff;
		}
		public void setWriteOff(BigDecimal writeOff) {
			this.writeOff = writeOff;
		}
		public BigDecimal getWriteOffConvert() {
			return writeOffConvert;
		}
		public void setWriteOffConvert(BigDecimal writeOffConvert) {
			this.writeOffConvert = writeOffConvert;
		}
		public BigDecimal getProfitLoss() {
			return profitLoss;
		}
		public void setProfitLoss(BigDecimal profitLoss) {
			this.profitLoss = profitLoss;
		}
		public BigDecimal getOpenInvoice() {
			return openInvoice;
		}
		public BigDecimal getOpenInvoiceConvert() {
			return openInvoiceConvert;
		}
	}
	
	/** A partir de una lista validar que la suma de sus montos no excedan el monto del pago */
	public static List<BeanPaySelect> getInvoiceSelected(Properties ctx, final Set<Object> lst, final BigDecimal totalPayamt, final ErrorList errorList){
		BigDecimal totalAmt = BigDecimal.ZERO;
//		final BigDecimal totalPayamt = beanUser.getPayAmt();

		final String errorMsg1 = new StringBuilder()//
		.append(Utilerias.getMsg(ctx, "error.caja.montoAplicado")).append(". ")//
		.append(Utilerias.getMsg(ctx, "msj.factura")).append(": ").toString();

		// Obtener las facturas seleccionadas
		final List<BeanPaySelect> lines = new ArrayList<BeanPaySelect>();
		for (final Object obj : lst) {
			if (obj instanceof BeanPaySelect) {
				final BeanPaySelect line = (BeanPaySelect) obj;

				if ((line.getApplied() == null || line.getApplied().compareTo(BigDecimal.ZERO) <= 0)
						&& (line.getBad() == null || line.getBad().compareTo(BigDecimal.ZERO) <= 0)
						&& (line.getNotApplied() == null || line.getNotApplied().compareTo(BigDecimal.ZERO) <= 0)) {
					continue;
					
				} else {
					line.setImp(line.getApplied().add(line.getBad()).add(line.getNotApplied()));

					if (line.getImp() == null || line.getImp().compareTo(BigDecimal.ZERO) <= 0) {
						errorList.add(Error.VALIDATION_ERROR, errorMsg1 + line.getDocumentNo());
//					} else if (line.getDueAmt() == null || line.getPayAmt() == null) {
//						// open amt / pay amt
//						errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("msg.error.select.cta"));
					} else if ( line.getImp().compareTo(totalPayamt) > 0) {//  || (line.getImp().compareTo(line.getPayAmt()) > 0)) {
						errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("error.caja.montoTC"));
					}
					if (!errorList.isEmpty()) {
						break;
					}

					totalAmt = totalAmt.add(line.getImp());
					lines.add(line);	
				}
			}
		}

		// validar totales de pago, con totales sumados en las facturas
		if (!lines.isEmpty() && totalPayamt.compareTo(totalAmt) < 0) {// FIXME
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "error.caja.montoTC"));
		}
		return lines;
	}
	
	/**
	 * Crea un pago para multiples facturas y la relacion del pago y facturas.
	 * El total del pago puede cubrir el total de las facturas o solo parcialidades.
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param beanUser Parametros para crear el registro de C_Payment, C_AllocationLine
	 * @param invoices Lista de facturas (C_Invoice) que se relacionaran (C_AllocationLine) al pago (C_Payment),
	 * @param trxName Transaccion
	 * @return Listado de errores
	 */
	public static ErrorList createPaymentReceipt(final Properties ctx, final PaymentBean beanUser, final List<BeanPaySelect> invoices, final String trxName) {
		final ErrorList errorList = beanUser.validateReceipt();
		if (errorList.isEmpty()) {
			try {
				final MPayment payment = new MPayment(ctx, beanUser.getId(), trxName);
				payment.newPaymentReceipt(ctx, beanUser, trxName);
				if(!(X_C_Payment.DOCSTATUS_Completed.equals(payment.getDocStatus())
						|| X_C_Payment.DOCSTATUS_Closed.equals(payment.getDocStatus())) ){
					payment.startWorkflow(errorList, DocAction.ACTION_Complete, DocAction.STATUS_Completed, trxName);
				} 

				// asignaciones
				BigDecimal totalPayamt = beanUser.getPayAmt();
				// Si no es un pago nuevo y el monto del pago excede el monto asignado
				if (!payment.is_new() && totalPayamt.compareTo(payment.getBalanceAmt()) > 0) {// FIXME
					errorList.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "El monto asignado excede el total del pago."));

				} else {
					// Asignaciones
					if(invoices!=null && !invoices.isEmpty()){
						if(!payment.allocateIt()) // Create Allocation Records
							payment.allocateInvoices(invoices);
					}
					payment.testAllocation();
					payment.setIsAllocated(totalPayamt.compareTo(payment.getPayAmt()) == 0);//TODO
					
					if (!payment.save()) {
						throw new MedsysException();
					}
				}
			} catch (Exception e) {
				s_log.log(Level.SEVERE, "-- confirm : ", e);
				errorList.add(Error.EXCEPTION_ERROR, e.getMessage());
			}
		}
		return errorList;
	}

	
	/**
	 * Crea el pago de tipo cheque/transferencia, 
	 * genera la secuencia del cheque (cuando no esta definido) segun la cuenta bancaria
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param bean Parametros para crear el registro de C_Payment, C_PaySelection y C_PaySelectionCheck
	 * @param trxName Transaccion
	 */
	private void newPaymentReceipt(Properties ctx, PaymentBean bean, String trxName) {
		if (is_new()) {
			fillPayment(bean);// payment.setMicr(bean.get);//TODO
			// se crea el pago
			if (save()) {
				if(bean.getId() != getC_Payment_ID()) {
					bean.setId(getC_Payment_ID());
				}
			} else {
				throw new MedsysException();
			}
		} else {
			setAllocationDate(bean.getAllocationDate());
		}
	}
	
	/**  A partir de un bean llenar los datos del pago */
	private void fillPayment(final PaymentBean bean) {
		setC_DocType_ID(bean.getDocTypeId());
		setC_BankAccount_ID(bean.getBankAccountId());
		setC_BPartner_ID(bean.getbPartnerId());
//		setTenderType(bean.getTenderType());
		setTenderType(bean.getPaymentRule());//TODO
		setDateTrx(bean.getDateTrx());
		setDateAcct(bean.getDateAcct()==null?bean.getDateTrx():bean.getDateAcct());
		setDescription(bean.getConcept());
		setPayAmt(bean.getPayAmt());
		setCheckNo(bean.getCheckNo());
		setReciboNo(" ");
		setC_Currency_ID(bean.getCurrencyId());
		setAllocationDate(bean.getAllocationDate());
		// 
		setDocumentNo(bean.getDocumentNo());
		setRoutingNo(bean.getRoutingNo () );
		setAccountNo(bean.getAccountNo() );
		setMicr(bean.getMicr() );
		setA_Name(bean.getA_Name() );
		if(bean.getCreditCardType()!=null && !bean.getCreditCardType().isEmpty())
			setCreditCardType (bean.getCreditCardType() );
		setCreditCardNumber (bean.getCreditCardNumber() );
		setCreditCardVV(bean.getCreditCardVV() );
		setCreditCardExpMM (bean.getCreditCardExpMM() );
		setCreditCardExpYY (bean.getCreditCardExpYY() );
		setEXME_CtaPac_ID(bean.getCtaPacId());
		setIsPrepayment(bean.isPrepayment());
	}
	
	/** Proceso y estatus de la columna docAction */
	public static KeyNamePair getColumnDocAction(){
		final MTable table = MTable.get(Env.getCtx(), I_C_Payment.Table_ID);
		final int actionProcessId = table.getColumn(I_C_Payment.COLUMNNAME_DocAction).getAD_Process_ID();
		final String name = table.getColumn(I_C_Payment.COLUMNNAME_DocAction).getTrlName();
		return new KeyNamePair(actionProcessId, name);
	}
	
	/** Proceso y estatus de la columna docAction */
	public static KeyNamePair getColumnVerPoliza(){
		final MTable table = MTable.get(Env.getCtx(), I_C_Payment.Table_ID);
		final int actionProcessId = table.getColumn(I_C_Payment.COLUMNNAME_VerPoliza).getAD_Process_ID();
		final String name = table.getColumn(I_C_Payment.COLUMNNAME_VerPoliza).getTrlName();
		return new KeyNamePair(actionProcessId, name);
	}
	
	public static List<CollectionsColumns> getPayments(Properties ctx, String docNo, String invoiceNum, String ctaPac, int partner, int account, int coin, String payway,
			boolean advance, boolean history, Timestamp date1, Timestamp date2, String trxName){
		
		final ArrayList<CollectionsColumns> list = new ArrayList<CollectionsColumns>();
		final StringBuilder sql = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		if(history){
			sql.append(" with monto as ( ");
		}
		
		sql.append(" select distinct allo.c_payment_id as payment, sum(allo.amount) as suma, paymentavailable(p.c_payment_id) as collectionamt , p.c_payment_id as paymentId, p.documentNo as documentNo, i.documentNo as invoice, ctaPac.documentno as ctapac, ");
		sql.append(" bank.name as bankName, p.dateTrx as dateTrx, c.iso_code as iso, p.paymenttype as paymentType, ");
		sql.append(" partner.name as partnerName, p.description as description, p.payamt as payamt ");
		sql.append(" from ");	
		sql.append(" c_payment p ");	
		sql.append(" inner join c_doctype doc on (p.c_doctype_id = doc.c_doctype_id) and doc.docbasetype = 'ARR' and doc.issotrx = 'Y' ");
		sql.append(" inner join c_bpartner partner on p.c_bpartner_id = partner.c_bpartner_id ");
		sql.append(" INNER JOIN c_bankAccount banka ON p.c_bankAccount_id = banka.c_bankAccount_id ");
		sql.append(" inner join c_currency c on banka.c_currency_id = c.c_currency_id " );
		sql.append(" inner join c_bank bank on banka.c_bank_id = bank.c_bank_id " );
		sql.append(" left join exme_ctapac ctaPac on p.exme_ctapac_id = ctaPac.exme_ctapac_id ");
		sql.append(" left JOIN c_invoice i ON p.c_invoice_id = i.c_invoice_id ");
		sql.append(" left join c_allocationline allo on allo.c_payment_id = p.c_payment_id " );
		
		
		sql.append(" where p.isactive = 'Y' ");	
		sql.append(" and p.TrxType<>'X' ");
		params.add(MPayment.DOCSTATUS_Completed);
		sql.append(" and p.docstatus = ? ");
		
		if (StringUtils.isNotBlank(docNo)){
			params.add(docNo.toLowerCase());
			sql.append(" and lower(p.documentno) = ? ");
			
		}else if(StringUtils.isNotBlank(invoiceNum)){
			params.add(invoiceNum.toLowerCase());
			sql.append(" and lower(i.documentno) = ? ");
			
		}else if(StringUtils.isNotBlank(ctaPac)){
			params.add(ctaPac.toLowerCase());
			sql.append(" and lower(ctaPac.documentno) = ? ");
			
		}else if(partner > 0){
			params.add(partner);
			sql.append(" and p.c_bpartner_id = ? ");
		}else{
			params.add(date1);
			params.add(date2);
			sql.append(" and p.datetrx BETWEEN ");
			sql.append("  ? AND ? ");			
		}
		
		
		if(account > 0){
			params.add(account);
			sql.append(" and p.c_bankaccount_id = ? ");
		}
		
		if(coin > 0){
			params.add(coin);
			sql.append(" and p.c_currency_id = ? ");
		}
		
		if(StringUtils.isNotBlank(payway)){
			params.add(payway);
			sql.append(" and p.paymenttype = ? ");
		}
		
		if(advance){
			sql.append(" and p.isprepayment = 'Y' ");
		}
		
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "p"));	
		sql.append(" group by allo.c_payment_id, p.c_payment_id, i.documentNo, ctaPac.documentno, bank.name, ");
		sql.append(" p.dateTrx, c.iso_code, p.paymenttype,  partner.name, p.description ");
		sql.append(" order by ");	
		sql.append(" p.datetrx desc ");	
		if(history){
			sql.append(" ) ");
			sql.append(" select * from monto ");
			sql.append(" where suma = payamt ");
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CollectionsColumns columns = new CollectionsColumns();
				columns.setDocumentNo(rs.getString("documentNo"));
				columns.setDocumentNoInvoice(rs.getString("invoice"));
				columns.setCtapac(rs.getString("ctapac"));
				columns.setBankName(rs.getString("bankName"));
				columns.setDateTrx(rs.getTimestamp("dateTrx"));
				
				columns.setPayAmt(rs.getBigDecimal("payamt"));
				columns.setCollectionAmt(rs.getBigDecimal("collectionamt"));
				
				columns.setIsoCode(rs.getString("iso"));
				columns.setPaymentType(rs.getString("paymentType"));
				columns.setPartner(rs.getString("partnerName"));
				columns.setDescription(rs.getString("description"));
				columns.setPaymentId(rs.getInt("paymentId"));
				list.add(columns);
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}finally {
			DB.close(rs, pstmt);
		}
		return list;
		
	}
	
	public static class CollectionsColumns{
		private String documentNo;
		private String documentNoInvoice;
		private String bankName;
		private Timestamp dateTrx;
		
		private BigDecimal payAmt;
		private BigDecimal collectionAmt;
		
		private String isoCode;
		private String paymentType;
		private String partner;
		private String description;
		private String ctapac;
		public String getCtapac() {
			return ctapac;
		}
		public void setCtapac(String ctapac) {
			this.ctapac = ctapac;
		}
		private int paymentId;
		
		public int getPaymentId() {
			return paymentId;
		}
		public void setPaymentId(int paymentId) {
			this.paymentId = paymentId;
		}
		public String getDocumentNo() {
			return documentNo;
		}
		public void setDocumentNo(String documentNo) {
			this.documentNo = documentNo;
		}
		public String getDocumentNoInvoice() {
			return documentNoInvoice;
		}
		public void setDocumentNoInvoice(String documentNoInvoice) {
			this.documentNoInvoice = documentNoInvoice;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
		public Timestamp getDateTrx() {
			return dateTrx;
		}
		public void setDateTrx(Timestamp dateTrx) {
			this.dateTrx = dateTrx;
		}
		public BigDecimal getCollectionAmt() {
			return collectionAmt;
		}
		public BigDecimal getPayAmt() {
			return payAmt;
		}
		public void setCollectionAmt(BigDecimal collectionAmt) {
			this.collectionAmt = collectionAmt;
		}
		public void setPayAmt(BigDecimal payAmt) {
			this.payAmt = payAmt;
		}
		public String getIsoCode() {
			return isoCode;
		}
		public void setIsoCode(String isoCode) {
			this.isoCode = isoCode;
		}
		public String getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}
		public String getPartner() {
			return partner;
		}
		public void setPartner(String partner) {
			this.partner = partner;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	}
	
	public void setFullPayment(boolean fullPayment) {
		this.isFullPayment = fullPayment;
	}
	
	public BigDecimal getPayAmtAllocation(){
		if(payAmtAllocation==null){
			return getPayAmt();
		} else {
			return payAmtAllocation;
		}
	}
	
	public void setPayAmtAllocation(final BigDecimal pPayAmtAllocation){
		payAmtAllocation = pPayAmtAllocation;
	}
} // MPayment
