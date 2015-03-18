/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.GenericModel;
import org.compiere.util.Msg;
 
/**
*	Bank Statement Model
*
*	@author Eldir Tomassen/Jorg Janke
*	@version $Id: MBankStatement.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
*/
public class MBankStatement extends X_C_BankStatement implements DocAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5162372404339962863L;
	private static CLogger s_log = CLogger.getCLogger(MBankStatement.class);
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_BankStatement_ID id
	 *	@param trxName transaction
	 */	
	public MBankStatement (Properties ctx, int C_BankStatement_ID, String trxName)
	{
		super (ctx, C_BankStatement_ID, trxName);
		if (C_BankStatement_ID == 0)
		{ 
		//	setC_BankAccount_ID (0);	//	parent
			setStatementDate (DB.getTimestampForOrg(ctx));	// @Date@
			setDocAction (DOCACTION_Complete);	// CO
			setDocStatus (DOCSTATUS_Drafted);	// DR
			setBeginningBalance(Env.ZERO);
			setStatementDifference(Env.ZERO);
			setEndingBalance (Env.ZERO);
			setIsApproved (false);	// N
			setIsManual (true);	// Y
			setPosted (false);	// N
			super.setProcessed (false);
		}
	}	//	MBankStatement

	/**
	 * 	Load Constructor
	 * 	@param ctx Current context
	 * 	@param rs result set
	 *	@param trxName transaction
	 */
	public MBankStatement(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MBankStatement

 	/**
 	 * 	Parent Constructor
	 *	@param account Bank Account
 	 * 	@param isManual Manual statement
 	 **/
	public MBankStatement (MBankAccount account, boolean isManual)
	{
		this (account.getCtx(), 0, account.get_TrxName());
		setClientOrg(account);
		setC_BankAccount_ID(account.getC_BankAccount_ID());
		setStatementDate(DB.getTimestampForOrg(account.getCtx()));
		setBeginningBalance(account.getCurrentBalance());
		setName(getStatementDate().toString());
		setIsManual(isManual);
	}	//	MBankStatement
	
	/**
	 * 	Create a new Bank Statement
	 *	@param account Bank Account
	 */
	public MBankStatement(MBankAccount account)
	{
		this(account, false);
	}	//	MBankStatement
 
	/**	Lines							*/
	private MBankStatementLine[] 	m_lines = null;
	
	
	
	/**
	 * 
	 * */
	public static List<MBankStatementView> getAll(Properties ctx) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		List<MBankStatementView> cuentas = new ArrayList<MBankStatementView>();

		StringBuffer sql = new StringBuffer("select bs.c_bankstatement_id C_BankStatement_ID, bs.name statement, ")
		.append("banco.name||'-'||ca.accountno cuenta, bs.beginningbalance balanceInicio, ")
		.append("bs.endingbalance balanceFin, bs.statementdifference statementDif, ")
		.append("bs.statementdate statementFecha from c_bankstatement bs, ")
		.append("c_bankaccount ca, c_bank banco where bs.c_bankaccount_id = ca.c_bankaccount_id ")
		.append("and banco.c_bank_id = ca.c_bank_id and bs.isActive = 'Y'");
		
		/*
		 * select bs.c_bankstatement_id, bs.name statement, banco.name||'-'||ca.accountno cuenta, bs.beginningbalance balanceInicio, bs.endingbalance balanceFin,
bs.statementdifference statementDiff, bs.statementdate statementFecha
from c_bankstatement bs, c_bankaccount ca, c_bank banco
where bs.c_bankaccount_id = ca.c_bankaccount_id
and banco.c_bank_id = ca.c_bank_id*/
		
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				MBankStatementView cuenta = new MBankStatementView();
				
				cuenta.setBalanceFin(rs.getString("balanceFin"));
				cuenta.setBalanceInicio(rs.getString("balanceInicio"));
				cuenta.setBankStatementId(rs.getInt("C_BankStatement_ID"));
				cuenta.setCuenta(rs.getString("cuenta"));
				cuenta.setStatement(rs.getString("statement"));
				cuenta.setStatementDif(rs.getString("statementDif"));
				cuenta.setStatementFecha(sdf.format(rs.getDate("statementFecha")));
				cuentas.add(cuenta);			
				
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}

		return cuentas;
	}
	
 	/**
 	 * 	Get Bank Statement Lines
 	 * 	@param requery requery
 	 *	@return line array
 	 */
 	public MBankStatementLine[] getLines (boolean requery)
 	{
		if (m_lines != null && !requery)
			return m_lines;
		//
 		ArrayList<MBankStatementLine> list = new ArrayList<MBankStatementLine>();
 		String sql = "SELECT * FROM C_BankStatementLine "
 			+ "WHERE C_BankStatement_ID=?"
 			+ "ORDER BY Line";
 		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_BankStatement_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MBankStatementLine(getCtx(), rs, get_TrxName()));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getLines", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
 		
		MBankStatementLine[] retValue = new MBankStatementLine[list.size()];
		list.toArray(retValue);
		return retValue;
 	}	//	getLines

 	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription

	/**
	 * 	Set Processed.
	 * 	Propergate to Lines/Taxes
	 *	@param processed processed
	 */
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		String sql = "UPDATE C_BankStatementLine SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE C_BankStatement_ID=" + getC_BankStatement_ID();
		int noLine = DB.executeUpdate(sql, get_TrxName());
		m_lines = null;
		log.fine("setProcessed - " + processed + " - Lines=" + noLine);
	}	//	setProcessed

	/**
	 * 	Get Bank Account
	 *	@return bank Account
	 */
	public MBankAccount getBankAccount()
	{
		return MBankAccount.get(getCtx(), getC_BankAccount_ID());
	}	//	getBankAccount
	
	/**
	 * 	Get Document No 
	 *	@return name
	 */
	public String getDocumentNo()
	{
		return getName();
	}	//	getDocumentNo
	
	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		return getBankAccount().getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
	//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
	//	if (re == null)
			return null;
	//	return re.getPDF(file);
	}	//	createPDF

	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getBeginningBalance().compareTo(Env.ZERO) == 0)
		{
			MBankAccount ba = MBankAccount.get(getCtx(), getC_BankAccount_ID());
			setBeginningBalance(ba.getCurrentBalance());
		}
		setEndingBalance(getBeginningBalance().add(getStatementDifference()));
		return true;
	}	//	beforeSave
	
	protected boolean afterSave(final boolean newRecord, boolean success) {
		if (success && !newRecord && getStatementDate() != null) {
			MBankStatementLine[] lines = getLines(true);
			for (int i = 0; i < lines.length; i++) {
				MBankStatementLine line = lines[i];
				// Fecha contable
				line.setDateAcct(getStatementDate());
				if (!line.save(get_TrxName())) {
					success = false;
					break;
				}
			}

		}
		return success;
	}
	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt
	
	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getStatementDate(), MDocType.DOCBASETYPE_BankStatement))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		MBankStatementLine[] lines = getLines(true);
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		//	Lines
		BigDecimal total = Env.ZERO;
		Timestamp minDate = getStatementDate();
		Timestamp maxDate = minDate;
		for (int i = 0; i < lines.length; i++)
		{
			MBankStatementLine line = lines[i];
			total = total.add(line.getStmtAmt());
			if (line.getDateAcct().before(minDate))
				minDate = line.getDateAcct(); 
			if (line.getDateAcct().after(maxDate))
				maxDate = line.getDateAcct(); 
		}
		setStatementDifference(total);
		setEndingBalance(getBeginningBalance().add(total));
		if (!MPeriod.isOpen(getCtx(), minDate, MDocType.DOCBASETYPE_BankStatement)
			|| !MPeriod.isOpen(getCtx(), maxDate, MDocType.DOCBASETYPE_BankStatement))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info("completeIt - " + toString());
		
		//	Set Payment reconciled
		MBankStatementLine[] lines = getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MBankStatementLine line = lines[i];
			if (line.getC_Payment_ID() != 0)
			{
				MPayment payment = new MPayment (getCtx(), line.getC_Payment_ID(), get_TrxName());
				payment.setIsReconciled(true);
				payment.save(get_TrxName());
			}
		}
		//	Update Bank Account
		MBankAccount ba = MBankAccount.get(getCtx(), getC_BankAccount_ID());
		ba.setCurrentBalance(getEndingBalance());
		ba.save(get_TrxName());
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Void Document.
	 * 	@return false 
	 */
	public boolean voidIt()
	{
		log.info(toString());
		if (DOCSTATUS_Closed.equals(getDocStatus())
			|| DOCSTATUS_Reversed.equals(getDocStatus())
			|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			m_processMsg = "Document Closed: " + getDocStatus();
			setDocAction(DOCACTION_None);
			return false;
		}

		//	Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
			|| DOCSTATUS_Invalid.equals(getDocStatus())
			|| DOCSTATUS_InProgress.equals(getDocStatus())
			|| DOCSTATUS_Approved.equals(getDocStatus())
			|| DOCSTATUS_NotApproved.equals(getDocStatus()) )
			;
		//	Std Period open?
		else
		{
			if (!MPeriod.isOpen(getCtx(), getStatementDate(), MDocType.DOCBASETYPE_BankStatement))
			{
				m_processMsg = "@PeriodClosed@";
				return false;
			}
			if (MFactAcct.delete(Table_ID, getC_BankStatement_ID(), get_TrxName()) < 0)
				return false;	//	could not delete
		}
			
		//	Set lines to 0
		MBankStatementLine[] lines = getLines(true);
		for (int i = 0; i < lines.length; i++)
		{
			MBankStatementLine line = lines[i];
			@SuppressWarnings("unused")
			BigDecimal old = line.getStmtAmt();
			if (line.getStmtAmt().compareTo(Env.ZERO) != 0)
			{
				String description = Msg.getMsg(getCtx(), "Voided") + " ("
					+ Msg.translate(getCtx(), "StmtAmt") + "=" + line.getStmtAmt();
				if (line.getTrxAmt().compareTo(Env.ZERO) != 0)
					description += ", " + Msg.translate(getCtx(), "TrxAmt") + "=" + line.getTrxAmt();
				if (line.getChargeAmt().compareTo(Env.ZERO) != 0)
					description += ", " + Msg.translate(getCtx(), "ChargeAmt") + "=" + line.getChargeAmt();
				if (line.getInterestAmt().compareTo(Env.ZERO) != 0)
					description += ", " + Msg.translate(getCtx(), "InterestAmt") + "=" + line.getInterestAmt();
				description += ")";
				line.addDescription(description);
				//
				line.setStmtAmt(Env.ZERO);
				line.setTrxAmt(Env.ZERO);
				line.setChargeAmt(Env.ZERO);
				line.setInterestAmt(Env.ZERO);
				line.save(get_TrxName());
				//
				if (line.getC_Payment_ID() != 0)
				{
					MPayment payment = new MPayment (getCtx(), line.getC_Payment_ID(), get_TrxName());
					payment.setIsReconciled(false);
					payment.save(get_TrxName());
				}
			}
		}
		addDescription(Msg.getMsg(getCtx(), "Voided"));
		setStatementDifference(Env.ZERO);
		
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());

		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return false 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual
	 * 	@return false 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return false 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		return false;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getName());
		//	: Total Lines = 123.00 (#1)
		sb.append(": ")
			.append(Msg.translate(getCtx(),"StatementDifference")).append("=").append(getStatementDifference())
			.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary
	
	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
		return getUpdatedBy();
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount.
	 * 	Statement Difference
	 *	@return amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return getStatementDifference();
	}	//	getApprovalAmt

	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
	//	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
	//	return pl.getC_Currency_ID();
		return 0;
	}	//	getC_Currency_ID
	
	/**
	 * 
	 */
	public static List<GenericModel> getPaymentByAccount(int C_BankAccount_ID, Timestamp DateStatement){
		
		List<GenericModel> pagos = new ArrayList<GenericModel>();

		StringBuffer sql = new StringBuffer("SELECT p.DateTrx,p.C_Payment_ID,p.DocumentNo, p.C_Currency_ID,c.ISO_Code, p.PayAmt, ")
				  .append("currencyConvert(p.PayAmt,p.C_Currency_ID,ba.C_Currency_ID, ?,null,p.AD_Client_ID,p.AD_Org_ID),")   
				  .append("bp.Name ")
				  .append ("FROM C_BankAccount ba ")
				  .append("INNER JOIN C_Payment_v p ON (p.C_BankAccount_ID=ba.C_BankAccount_ID)" )
				  .append("INNER JOIN C_Currency c ON (p.C_Currency_ID=c.C_Currency_ID) ")
				  .append("INNER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID) ") 
				  .append("WHERE p.Processed='Y' AND p.IsReconciled='N' ")
				  .append("AND p.DocStatus IN ('CO','CL','RE') AND p.PayAmt<>0 ")
				  .append("AND p.C_BankAccount_ID=? ")                              	
				  .append("AND NOT EXISTS (SELECT * FROM C_BankStatementLine l ")  
				  .append("WHERE p.C_Payment_ID=l.C_Payment_ID AND l.StmtAmt <> 0)");
		

		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(2, C_BankAccount_ID);
			pstmt.setTimestamp(1, DateStatement);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				GenericModel pago = new GenericModel();
				
				pago.setFecha(rs.getDate(1));
				pago.setValue(rs.getString(2));
				pago.setDocumentNo(rs.getString(3));
				pago.setReferenciaID(rs.getString(4));
				pago.setName(rs.getString(5));
				pago.setCredito(rs.getString(6));
				pago.setPeriodo(rs.getString(7));
				pago.setSocio(rs.getString(8));
				pagos.add(pago);			
				
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}

		return pagos;
		
	}
	
	/**
	 * Obtiene una lista de MBankStatement
	 * @param where filtro para query inicia con "AND"
	 * @param ctx Contexto
	 * @return list
	 * @author rosy velazquez
	 * */
	public static List<MBankStatement> getBankStatements(String where, Properties ctx){
	
		ArrayList<MBankStatement> list = new ArrayList<MBankStatement>();
		String sql = "SELECT * FROM C_BankStatement "
			       + "WHERE isActive= 'Y' ";
		
		if(where != null){
			sql = sql + where;
		}
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MBankStatement(ctx, rs, null));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			    pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		return list;
	}//getBankStatement
	
 }	//	MBankStatement