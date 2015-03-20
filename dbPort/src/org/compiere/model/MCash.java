/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
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

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MCorteCaja.MCashLineDiff;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.SecureEngine;
import org.compiere.util.TimeUtil;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.ErrorList;

/**
 *	Cash Journal Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MCash.java,v 1.8 2006/09/05 23:18:54 taniap Exp $
 */
public class MCash extends X_C_Cash implements DocAction
{
	/** serialVersionUID */
	private static final long serialVersionUID = -2661587959086635883L;
	private List<MCashLineDiff> lstConciliacion = new ArrayList<MCashLineDiff>();
	private List<MFormaPago> lstDiferent = new ArrayList<MFormaPago>();
	private BigDecimal diferencias = Env.ZERO;
	
	/**
	 * 	Get Cash Journal for currency, org and date
	 *	@param ctx context
	 *	@param C_Currency_ID currency
	 *	@param AD_Org_ID org
	 *	@param dateAcct date
	 *	@return cash
	 */
	public static MCash get (Properties ctx, int AD_Org_ID, 
		Timestamp dateAcct, int C_Currency_ID, String trxName)
	{
		MCash retValue = null;
		//	Existing Journal
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM C_Cash c ");
		sql.append(" WHERE c.AD_Org_ID=?");//	#1
		if (DB.isOracle()) {
			sql.append(" AND TRUNC(c.StatementDate)=?");//	#2
		} else if (DB.isPostgreSQL()) {
			sql.append(" AND DATE_TRUNC('day', c.StatementDate)=?");//	#2
		}
		sql.append(" AND c.Processed='N'");
		sql.append(" AND EXISTS (SELECT * FROM C_CashBook cb ");
		sql.append(" WHERE c.C_CashBook_ID=cb.C_CashBook_ID AND cb.AD_Org_ID=c.AD_Org_ID");
		sql.append(" AND cb.C_Currency_ID=?)");//	#3

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, AD_Org_ID);
			pstmt.setTimestamp(2, TimeUtil.getDay(dateAcct));
			pstmt.setInt(3, C_Currency_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MCash(ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		if (retValue != null)
			return retValue;
		
		//	Get CashBook
		MCashBook cb = MCashBook.get(ctx, AD_Org_ID, C_Currency_ID, trxName);
		if (cb == null) {
			s_log.warning("No CashBook for AD_Org_ID=" + AD_Org_ID + ", C_Currency_ID=" + C_Currency_ID);
			return null;
		}

		//	Create New Journal
		retValue = new MCash (cb, dateAcct);
		retValue.save(trxName);
		return retValue;
	}	//	get

	/**
	 * 	Get Cash Journal for CashBook and date
	 *	@param ctx context
	 *	@param C_CashBook_ID cashbook
	 *	@param dateAcct date
	 *	@return cash
	 */
	public static MCash get (Properties ctx, int C_CashBook_ID, 
		Timestamp dateAcct, String trxName)
	{
		MCash retValue = null;
		//	Existing Journal
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM C_Cash c ");
		sql.append(" WHERE c.C_CashBook_ID=?");//	#1
		if (DB.isOracle()) {
			sql.append(" AND TRUNC(c.StatementDate)=?");//	#2
		} else if (DB.isPostgreSQL()) {
			sql.append(" AND DATE_TRUNC('day', c.StatementDate)=?");//	#2
		}
		sql.append(" AND c.Processed='N'");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_CashBook_ID);
			pstmt.setTimestamp(2, TimeUtil.getDay(dateAcct));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = new MCash (ctx, rs, trxName);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		if (retValue != null) {
			return retValue;
		}
		//	Get CashBook
		MCashBook cb = new MCashBook(ctx, C_CashBook_ID, trxName);
		if (cb.get_ID() == 0) {
			s_log.warning("Not found C_CashBook_ID=" + C_CashBook_ID);
			return null;
		}
		
		//	Create New Journal
		retValue = new MCash (cb, dateAcct);
		retValue.save(trxName);
		return retValue;
	}	//	get

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MCash.class);

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Cash_ID id
	 */
	public MCash (Properties ctx, int C_Cash_ID, String trxName)
	{
		super (ctx, C_Cash_ID, trxName);
		if (C_Cash_ID == 0)
		{
		//	setC_CashBook_ID (0);		//	FK
			setBeginningBalance (Env.ZERO);
			setEndingBalance (Env.ZERO);
			setStatementDifference(Env.ZERO);
			setDocAction(DOCACTION_Complete);
			setDocStatus(DOCSTATUS_Drafted);
			//
			Timestamp today = TimeUtil.getDay(System.currentTimeMillis());
			setStatementDate (today);	// @#Date@
			setDateAcct (today);	// @#Date@
			String name = DisplayType.getDateFormat(DisplayType.Date).format(today)
				+ " " + MOrg.get(ctx, getAD_Org_ID()).getValue();
			setName (name);	
			setIsApproved(false);
			setPosted (false);	// N
			setProcessed (false);
		}
	}	//	MCash

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MCash (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCash
	
	/**
	 * 	Parent Constructor
	 *	@param cb cash book
	 *	@param today date - if null today
	 */
	public MCash (MCashBook cb, Timestamp today)
	{
		this (cb.getCtx(), 0, cb.get_TrxName());
		setClientOrg(cb);
		setC_CashBook_ID(cb.getC_CashBook_ID());
		if (today != null)
		{
			setStatementDate (today);	
			setDateAcct (today);
			String name = DisplayType.getDateFormat(DisplayType.Date).format(today)
				+ " " + cb.getName();
			setName (name);	
		}
		m_book = cb;
	}	//	MCash
	
	/**	Lines					*/
	private MCashLine[]		m_lines = null;
	/** CashBook				*/
	private MCashBook		m_book = null;
	
	/**
	 * 	Get Lines, no debe de tomar en cuenta los ajustes
	 *	@param requery requery
	 *	@return lines
	 */
	public MCashLine[] getLines (boolean requery)
	{
		if (m_lines != null && !requery) {
			return m_lines;
		}
		ArrayList<MCashLine> list = new ArrayList<MCashLine>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT CLINE.* ")
		.append(" FROM C_CASH CASH ")
		.append(" INNER JOIN C_CASHLINE CLINE ON CLINE.C_CASH_ID = CASH.C_CASH_ID ")
		.append(" INNER JOIN C_PAYMENT PAY ON PAY.C_PAYMENT_ID = CLINE.C_PAYMENT_ID ")
		.append(" LEFT JOIN  C_CHARGE CHARGE ON CHARGE.C_CHARGE_ID = PAY.C_CHARGE_ID ")
		.append(" WHERE CLINE.ISACTIVE = 'Y' ")
		.append(" AND CHARGE.TYPE NOT IN ('A','B','G', 'C', 'D', 'I') ")
		.append(" AND CASH.C_CASH_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(getCtx(), sql.toString(), "C_CASH", "CASH"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Cash_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MCashLine(getCtx(), rs, get_TrxName()));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		m_lines = new MCashLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines

	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return lines
	 */
	public MCashLine[] getLines (boolean requery, boolean post, String where)
	{
		if (m_lines != null && !requery){
			return m_lines;
		}
		
		ArrayList<MCashLine> list = new ArrayList<MCashLine>();
		String sql = "SELECT * FROM C_CashLine WHERE C_Cash_ID=? AND C_Currency_ID=?  ORDER BY Line";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_Cash_ID());
			pstmt.setInt(2, Env.getC_Currency_ID(getCtx()));// Card #1170 - Lama
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MCashLine(getCtx(), rs, get_TrxName()));
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
		}

		m_lines = new MCashLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines


	/**
	 * 	Get Cash Book
	 *	@return cash book
	 */
	public MCashBook getCashBook()
	{
		if (m_book == null)
			m_book = MCashBook.get(getCtx(), getC_CashBook_ID());
		return m_book;
	}	//	getCashBook

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
		return Msg.getElement(getCtx(), "C_Cash_ID") + " " + getDocumentNo();
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
	 *	@param newRecord
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		setAD_Org_ID(getCashBook().getAD_Org_ID());
		if (getAD_Org_ID() == 0)
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@AD_Org_ID@"));
			return false;
		}
		//	Calculate End Balance
		setEndingBalance(getBeginningBalance().add(getStatementDifference()));

		//EXPERT: twry
		if(newRecord) {
		    String sql = "SELECT COALESCE(MAX(secuencia),0)+1 FROM C_Cash WHERE AD_Client_ID=?";
		    int ii = DB.getSQLValue (get_TrxName(), sql, getAD_Client_ID());
		    setSecuencia(ii);
		} else {
			if(getEndDate()!=null && getEndingBalance()!=null && !getDocStatus().equals(X_C_Cash.DOCSTATUS_Drafted))
				setDateAcct(getEndDate());
		}
		//FIN twry
		return true;
	}	//	beforeSave
	
	
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
	}	//	process
	
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
		log.info(toString());
		setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info(toString());
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
		if (m_processMsg != null) {
			return DocAction.STATUS_Invalid;
		}

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), MDocType.DOCBASETYPE_CashJournal, getAD_Org_ID()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		MCashLine[] lines = getLines(false, false, null);
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		//	Add up Amounts
		BigDecimal difference = Env.ZERO;
		int C_Currency_ID = getC_Currency_ID();
		for (int i = 0; i < lines.length; i++)
		{
			MCashLine line = lines[i];
			if (!line.isActive())
				continue;
			if (C_Currency_ID == line.getC_Currency_ID()) {
				difference = difference.add(line.getAmount());
			} else if (MCashLine.CASHTYPE_CuentasXCobrar.equals(line.getCashType())) { //Card #1170 - Lama
				continue;
			} else {
				BigDecimal amt = MConversionRate.convert(getCtx(), line.getAmount(), 
					line.getC_Currency_ID(), C_Currency_ID, getDateAcct(), 0, 
					getAD_Client_ID(), getAD_Org_ID());
				if (amt == null)
				{
					m_processMsg = "No Conversion Rate found - @C_CashLine_ID@= " + line.getLine();
					return DocAction.STATUS_Invalid;
				}
				difference = difference.add(amt);
			}
		}
		setStatementDifference(difference);
	//	setEndingBalance(getBeginningBalance().add(getStatementDifference()));
		//
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
		log.info(toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info(toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt() {
		// Re-Check
		if (!m_justPrepared) {
			final String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status)) {
				return status;
			}
		}
		// Implicit Approval
		if (!isApproved()) {
			approveIt();
		}
		//
		log.info(toString());

//		// Allocation Header
//		final MAllocationHdr alloc = new MAllocationHdr(getCtx(), false, getDateAcct(), //
//			getC_Currency_ID(), Msg.translate(getCtx(), "C_Cash_ID") + ": " + getName(), get_TrxName());
//		alloc.setAD_Org_ID(getAD_Org_ID());
//		if (!alloc.save()) {
//			m_processMsg = "Could not create Allocation Hdr";
//			return DocAction.STATUS_Invalid;
//		}
		
		// Traemos las lineas de caja ordenadas por factura y moneda
		final List<MCashLine> lines = new Query(getCtx(), MCashLine.Table_Name, " C_Cash_ID=? ", null)//
			.setOnlyActiveRecords(true)//
			.setParameters(getC_Cash_ID())//
			.addAccessLevelSQL(true)//
//			.setOrderBy(" C_Invoice_ID, C_Currency_ID, Created ")//
			.setOrderBy(" CashType ")//
			.list();
		
//		MAllocationHdr header = null;
//		int invoiceId = 0;
		for (MCashLine line : lines) {
			line.setProcessed(true);// Processed = 'Y'
			line.save(get_TrxName());
//			if (MCashLine.CASHTYPE_Invoice.equals(line.getCashType())) {
//				// cambio de moneda (header vs detalle)
//				boolean differentCurrency = getC_Currency_ID() != line.getC_Currency_ID();
//				// boolean allocationInv = true;
//				// lineas de otra factura
//				boolean differentInvoice = invoiceId != line.getC_Invoice_ID();
//				// reseteamos si cambio factura o moneda
//				if (differentInvoice || differentCurrency) {
//					// si ya viene header, se completa
//					if (header != null) {
//						// Should start WF
//						header.processIt(DocAction.ACTION_Complete);
//						header.save();
//					}
//					header = null;
//					if (differentInvoice) {
//						invoiceId = line.getC_Invoice_ID();
//					}
//				}
//				// creamos allocation si invoiceallocation <= 0 (lama)
//				if (header == null) {
//					header = new MAllocationHdr(getCtx(),  false, //
//						getDateAcct(),  line.getC_Currency_ID(), //
//						Msg.translate(getCtx(), "C_Cash_ID") + ": " + getName(), //
//						get_TrxName());
//					header.setAD_Org_ID(getAD_Org_ID());
//					if (!header.save()) {
//						m_processMsg = "Could not create Allocation Hdr";
//						return DocAction.STATUS_Invalid;
//					}
//				}
//				// Allocation Line
//				final MAllocationLine aLine = new MAllocationLine(header, //
//					line.getAmount(),//
//					line.getDiscountAmt(),//
//					line.getWriteOffAmt(), //
//					BigDecimal.ZERO);
//				aLine.setC_Invoice_ID(line.getC_Invoice_ID());
//				aLine.setC_CashLine_ID(line.getC_CashLine_ID());
//				if (!aLine.save()) {
//					m_processMsg = "Could not create Allocation Line";
//					return DocAction.STATUS_Invalid;
//				}
//			} else 
			if (MCashLine.CASHTYPE_BankAccountTransfer.equals(line.getCashType())) {
				// Payment just as intermediate info // FIXME ???
				final MPayment pay = new MPayment(getCtx(), 0, get_TrxName());
				pay.setAD_Org_ID(getAD_Org_ID());
				String documentNo = getName();
				pay.setDocumentNo(documentNo);
				pay.setR_PnRef(documentNo);
				pay.set_Value("TrxType", "X"); // Transfer
				pay.set_Value("TenderType", "X");
				//
				pay.setC_BankAccount_ID(line.getC_BankAccount_ID());
				pay.setC_DocType_ID(true); // Receipt
				pay.setDateTrx(getStatementDate());
				pay.setDateAcct(getDateAcct());
				pay.setAmount(line.getC_Currency_ID(), line.getAmount().negate()); // Transfer
				pay.setDescription(line.getDescription());
				pay.setDocStatus(MPayment.DOCSTATUS_Closed);
				pay.setDocAction(MPayment.DOCACTION_None);
				pay.setPosted(true);
				pay.setIsAllocated(true); // Has No Allocation!
				pay.setProcessed(true);
				if (!pay.save()) {
					m_processMsg = "Could not create Payment";
					return DocAction.STATUS_Invalid;
				}
			}
		}
		completeAllocation();//RQM #5303
		
//		// si quedo header, se completa
//		if (header != null) {
//			// Should start WF
//			header.processIt(DocAction.ACTION_Complete);
//			header.save();
//			header = null;
//		}

//		// Should start WF
//		alloc.processIt(DocAction.ACTION_Complete);
//		alloc.save();

		// User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		if(getEndDate() == null) {
			setEndDate(Env.getCurrentDate());
		}
		return DocAction.STATUS_Completed;
	} // completeIt

	/** Busca y completa todos los registros de asignacion relacionados al diario */
	public boolean completeAllocation() {
		// buscamos los allocation headers generados por el cash id para completar Card #1126
		final StringBuilder joins = new StringBuilder();
		joins.append(" INNER JOIN C_AllocationLine al ON (C_AllocationHdr.C_AllocationHdr_ID=al.C_AllocationHdr_ID) ");
		joins.append(" LEFT JOIN C_CashLine cl ON (al.C_Payment_ID=cl.C_Payment_ID OR al.C_CashLine_ID=cl.C_CashLine_ID) ");
		joins.append(" LEFT JOIN C_Invoice i ON (al.C_Invoice_ID=i.C_Invoice_ID) ");
		final List<MAllocationHdr> listAlloc = new Query(getCtx(), 
			MAllocationHdr.Table_Name,
			// filtrar solo los encabezados NO completados/Cerrados
			" ( cl.C_Cash_ID=? OR i.C_Cash_ID=? ) AND C_AllocationHdr.DocStatus NOT IN (?,?) ", null)//
			.setJoins(joins).setOnlyActiveRecords(true)//
			.setParameters(getC_Cash_ID(), getC_Cash_ID(), DocAction.STATUS_Completed, DocAction.STATUS_Closed)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" C_AllocationHdr_ID ")//
			.list();

		int completed = 0;
		for (MAllocationHdr header : listAlloc) {
			header.set_TrxName(get_TrxName());// Should start WF
			header.processIt(DocAction.ACTION_Complete);
			if (header.save() && DocAction.STATUS_Completed.equals(header.getDocStatus())) {
				completed++;
				s_log.fine("Allocation Completed >> " + header.getC_AllocationHdr_ID());
			} else {
				s_log.severe("Allocation NOT Completed >> " + header.getC_AllocationHdr_ID());
			}
		}
		return listAlloc.size() == completed;
	}
	
	
	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		// Lama: completa las asignaciones, para el caso que no tenga lineas en caja
		// y solo facturas
		completeAllocation();
		log.info(toString());
		setDocAction(DOCACTION_None);
		return false;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info(toString());
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info(toString());
		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info(toString());
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info(toString());
		setProcessed(false);
		if (reverseCorrectIt())
			return true;
		return false;
	}	//	reActivateIt
	
	/**
	 * 	Set Processed
	 *	@param processed processed
	 */
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		String sql = "UPDATE C_CashLine SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE C_Cash_ID=" + getC_Cash_ID();
		int noLine = DB.executeUpdate (sql, get_TrxName());
		m_lines = null;
		log.fine(processed + " - Lines=" + noLine);
	}	//	setProcessed
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MCash[");
		sb.append (get_ID ())
			.append ("-").append (getName())
			.append(", Balance=").append(getBeginningBalance())
			.append("->").append(getEndingBalance())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
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
			.append(Msg.translate(getCtx(),"BeginningBalance")).append("=").append(getBeginningBalance())
			.append(",")
			.append(Msg.translate(getCtx(),"EndingBalance")).append("=").append(getEndingBalance())
			.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0) {
			sb.append(" - ").append(getDescription());
		}
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
		return getCreatedBy();
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return amount difference
	 */
	public BigDecimal getApprovalAmt()
	{
		return getStatementDifference();
	}	//	getApprovalAmt

	/**
	 * 	Get Currency
	 *	@return Currency
	 */
	public int getC_Currency_ID ()
	{
		return getCashBook().getC_Currency_ID();
	}	//	getC_Currency_ID

	//Expert..Twry
//	private boolean visible = true;
//	private int indice = 0;
	/** unidad de servicio */
	private MEXMEEstServ estServ = null;
	/** corte de caja relacionado */
	private MCorteCaja corteCaja = null;
	
	/**
	* Buscamos el ID de cash que corresponda con operador caja y estatus del doc = Dr 
	*
	* @param ctx
	* @param c_Cashbook_ID
	* @param eXME_Operador_ID
	*/
	public static int getCash(Properties ctx, int c_CashBook_ID, int exme_Operador_ID, String trxName ){ 
		int resultado = 0;
		if(ctx == null){
			return resultado;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT C_Cash_ID FROM C_Cash  ")
		.append(" WHERE IsActive = 'Y' ") 
		.append(" AND EXME_Operador_ID = ? ") 
		.append(" AND DocStatus = 'DR' ")
		.append(" AND C_CashBook_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,sql.toString(),"C_Cash"));

		sql.append(" ORDER BY C_Cash_ID DESC"); 

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt(1,exme_Operador_ID);
			pstmt.setInt(2,c_CashBook_ID);

			rs = pstmt.executeQuery();
			if (rs.next()){
				resultado = rs.getInt("C_Cash_ID");
			}
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "MCash.getCash", e);
		} finally {
			DB.close(rs,pstmt);
		}

		return resultado;
	}	

	/**
	 * Metodo para obtener el ID de cash corresponda con operador caja y estatus
	 * del doc = CO Se utiliza en el cierre de caja, para obtener los registros
	 * a postear
	 * 
	 * @param ctx Contexto de la aplicacion
	 * @param c_CashBook_ID El ID del libro de caja
	 * @param exme_Operador_ID EL ID del operador
	 * @param trxName El nombre de la transaccion
	 * @return int ID de Cash
	 * 
	 */
	public static int getCashCO(Properties ctx, int c_CashBook_ID, int exme_Operador_ID, String trxName) {
		int resultado = 0;
		if (ctx == null) {
			return resultado;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT C_Cash_ID FROM C_Cash  WHERE ").append(" EXME_Operador_ID = ? ")
				.append(" AND DocStatus = 'CO' AND C_CashBook_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_Cash"));

		sql.append("ORDER BY C_Cash_ID DESC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exme_Operador_ID);
			pstmt.setInt(2, c_CashBook_ID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				resultado = rs.getInt("C_Cash_ID");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MCash.getCashCO", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return resultado;
	}
	  
	/**
	 * Get Lines
	 * 
	 * @param requery
	 *            requery
	 * @return lines
	 */
	public static MCashLine[] getLinesType(Properties ctx, int c_Cash_ID, String trxName) {

		ArrayList<MCashLine> list = new ArrayList<MCashLine>();
		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT CLINE.* ")
		.append(" FROM C_CASH CASH ")
		.append(" INNER JOIN C_CASHLINE CLINE ON CLINE.C_CASH_ID = CASH.C_CASH_ID ")
		.append(" INNER JOIN C_PAYMENT PAY ON PAY.C_PAYMENT_ID = CLINE.C_PAYMENT_ID ")
		.append(" LEFT JOIN  C_CHARGE CHARGE ON CHARGE.C_CHARGE_ID = PAY.C_CHARGE_ID ")
		.append(" WHERE CLINE.ISACTIVE = 'Y' ")
		.append(" AND CLINE.CASHTYPE IN ('I', 'P', 'X') ")
		.append(" AND CHARGE.TYPE NOT IN ('A','B','G', 'C', 'D', 'I') ") // No tome en cuenta ajustes
		.append(" AND CASH.C_CASH_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_CASH", "CASH"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, c_Cash_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MCashLine(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MCash.getLinesType", e);
		} finally {
			DB.close(rs, pstmt);
		}

		MCashLine[] lineas = new MCashLine[list.size()];
		list.toArray(lineas);
		return lineas;
	} // getLines

	/**
	 * Get Lines
	 * 
	 * @param requery
	 *            requery
	 * @return lines
	 */
	public static MCashLine[] getLinesCash(Properties ctx, int c_Cash_ID, String trxName) {

		ArrayList<MCashLine> list = new ArrayList<MCashLine>();
		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT CLINE.* ");
		sql.append(" FROM C_CASHLINE CLINE "); //C_CASH CASH 
//		sql.append(" INNER JOIN C_CASHLINE CLINE ON  CLINE.C_CASH_ID = CASH.C_CASH_ID ");
//		sql.append(" LEFT  JOIN C_PAYMENT    PAY ON  PAY.C_PAYMENT_ID = CLINE.C_PAYMENT_ID ");
//		sql.append(" LEFT  JOIN C_CHARGE  CHARGE ON  CHARGE.C_CHARGE_ID = PAY.C_CHARGE_ID ");
//		sql.append("                             AND CHARGE.TYPE NOT IN ('A','B','G', 'C', 'D', 'I') "); // No tome en cuenta ajustes
		sql.append(" WHERE CLINE.ISACTIVE = 'Y' ");
		sql.append(" AND   CLINE.C_CASH_ID = ? ");
		sql.append(" AND   CLINE.C_Currency_ID = ? ");// Card #1170 - Lama
		sql.append(" AND (CLINE.isCancelled='N' OR CLINE.Ref_Cash_id!=?)");//Card #1201 - ASnchez
		sql.append(" AND   CLINE.CASHTYPE  IN ('I', 'P', 'X') ");//I:Invoice, P:Anticipo, X:CuentasXCobrar
//		sql.append(" AND   CASH.ISACTIVE = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MCashLine.Table_Name, "CLINE"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, c_Cash_ID);
			pstmt.setInt(2, Env.getC_Currency_ID(ctx));
			pstmt.setInt(3, c_Cash_ID );

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MCashLine(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MCash.getLinesType", e);
		} finally {
			DB.close(rs, pstmt);
		}

		MCashLine[] lineas = new MCashLine[list.size()];
		list.toArray(lineas);
		return lineas;
	} // getLines
	
	// Expert..Fin
	// EXPERT
	/**
	 * ID
	 */
	public int getId(){
    	return getC_Cash_ID();    
    }


	/**
	 * Operador
	 * @return
	 */
	public MEXMEOperador getOperador() {

		if(getEXME_Operador_ID() <= 0) {
			return null;
		}
		return new MEXMEOperador(getCtx(), getEXME_Operador_ID(), get_TrxName());

	}
	
    //expert
	/**
	 * 
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static List<MCash> get(Properties ctx, String trxName) {
		List<MCash> list = new ArrayList<MCash>();
		if (ctx == null) {
			return null;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);		
		
		sql.append(" SELECT * FROM C_Cash  WHERE NVL(exme_operador_id,0) > 0  AND ")
			.append(" IsActive='Y' AND Secuencia IS NOT NULL ")
			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",Table_Name))
			.append(" ORDER BY Secuencia ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MCash(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}

		return list;
	} // getLines
	
	/**
	 * Operador
	 * @return
	 */
	public MCashBook getCaja() {

		if(getC_CashBook_ID() <= 0)
			return null;
		return new MCashBook(getCtx(), getC_CashBook_ID(), get_TrxName());

	}

	public int getFolio(){
	    if (getSecuencia()!=0) {
	        return getSecuencia();
	    } else {
	        return 0;
	    }
    }

	/**
	 * Verifica si estan posteadas las lineas de la caja
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static boolean cashPosted(Properties ctx, int C_Cash_ID, String trxName) {
		boolean isPosted = false;

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT pay.C_Payment_ID AS ids, ")
		.append("factPay.amtSourceDr AS debit, factPay.amtSourceCr AS credit, factPay.dateAcct, pay.posted ")
		.append("FROM C_Payment pay ")
		.append("INNER JOIN C_CashLine cLine ON cLine.C_Payment_ID = pay.C_Payment_ID ")
		.append("INNER JOIN C_Cash cash ON cash.C_Cash_ID = cLine.C_Cash_ID ")
		.append("LEFT JOIN Fact_Acct factPay ON factPay.AD_Table_ID = 335 AND factPay.Record_ID = pay.C_Payment_ID ")
		.append("WHERE cash.C_Cash_ID =  ").append(C_Cash_ID)
		.append(" UNION ")
		.append("SELECT hdr.C_AllocationHdr_ID AS ids, ")
		.append("factPay.amtSourceDr AS debit, factPay.amtSourceCr AS credit, factPay.dateAcct, hdr.posted ")
		.append("FROM ")
		.append("(SELECT DISTINCT calloLine.C_AllocationHdr_ID AS C_AllocationHdr_ID, callo.posted  ")
		.append("FROM C_Payment pay  ")
		.append("INNER JOIN C_CashLine cLine ON cLine.C_Payment_ID = pay.C_Payment_ID ")
		.append("INNER JOIN C_Cash cash ON Cash.C_Cash_ID = cLine.C_Cash_ID ")
		.append("INNER JOIN C_AllocationLine calloLine ON Pay.C_Payment_Id = Calloline.C_Payment_Id ")
		.append("INNER JOIN C_AllocationHdr callo ON Calloline.C_Allocationhdr_Id = Callo.C_Allocationhdr_Id ")
		.append("WHERE pay.C_Payment_ID IN (SELECT cLine.C_Payment_ID FROM C_CashLine cLine WHERE cLine.C_Cash_ID = ").append(C_Cash_ID).append(")) hdr ")
		.append("INNER JOIN Fact_Acct factPay ON Factpay.Ad_Table_Id = 735 And Factpay.Record_Id = Hdr.C_Allocationhdr_Id ")
		.append("UNION ")
		.append("SELECT cash.C_Cash_ID AS ids, ")
		.append("factCash.amtSourceDr AS debit, factCash.amtSourceCr AS credit, factCash.dateAcct, cash.posted ")
		.append("FROM C_Cash cash ")
		.append("INNER JOIN Fact_Acct factCash ON  factCash.Record_ID = Cash.C_Cash_ID AND factCash.AD_Table_ID = 407 ")
		.append("LEFT JOIN C_CashLine cl ON factCash.Line_ID = cl.C_CashLine_ID ")
		.append("WHERE cash.c_Cash_ID = ").append(C_Cash_ID);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				isPosted = "Y".equalsIgnoreCase(rs.getString("posted"));
//				if (rs.getString("posted").equalsIgnoreCase("Y")) {
//					isPosted = true;
//				} else {
				if(!isPosted) {
//					isPosted = false;
					break;
				}

			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return isPosted;
	}
	
	/**
	 * Busca el corte de caja correspondiente
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public MCorteCaja getCorteCaja() {
		
		if(corteCaja==null){

			StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)	
			.append(" SELECT * FROM EXME_CorteCaja  ")
			.append(" WHERE IsActive  = 'Y'          ")
			.append(MEXMELookupInfo.addAccessLevelSQL(getCtx()," ","EXME_CorteCaja"))
			.append(" AND   C_Cash_ID = ?  ");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setInt(1, getC_Cash_ID());
				rs = pstmt.executeQuery();
				if (rs.next()){
					corteCaja = new MCorteCaja(getCtx(), rs, get_TrxName());
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs,pstmt);
			}
		}
		return corteCaja;
	}
	
	/**********************************************************/
	public MEXMEEstServ getEstServ() {
		if(estServ==null){
			estServ = new MEXMEEstServ(getCtx(), getEXME_EstServ_ID(), null);
		}
		return estServ;
	}

	public void setEstServ(MEXMEEstServ estServ) {
		this.estServ = estServ;
	}
	
	/**
	 * cashier amount
	 * @return EXME_CorteCaja.CashierAmount
	 */
	public String getCashierAmount() {
		String montoStr = " ";
		if(getCorteCaja()!=null 
				&& getCorteCaja().getCashierAmount()!=null 
				&& getCorteCaja().getCashierAmount().compareTo(Env.ZERO)!=0){
			montoStr = String.valueOf(getCorteCaja().getCashierAmount());
		}
		return montoStr;
	}
	
	/**
	 * system amount - cashier amount
	 * @return
	 */
	public String getBalance() {
		// system amount - cashier amount
		String montoStr = " ";
		
		//
		if(getCorteCaja()!=null 
				&& getCorteCaja().getCashierAmount()!=null 
				&& getCorteCaja().getCashierAmount().compareTo(Env.ZERO)!=0
				&& getEndingBalance()!=null
				&& getEndingBalance().compareTo(Env.ZERO)!=0){
			
			BigDecimal monto = Env.ZERO;
			monto = getEndingBalance().subtract(getCorteCaja().getCashierAmount());
			montoStr = String.valueOf(monto);
		}
		
		return montoStr;
	}

	/**
	 * Open amount - system amount
	 * @return
	 */
	public String getCollections() {

		// Open amount - system amount
		String montoStr = " ";
		
		if(getStatementDifference()!=null && getStatementDifference().compareTo(Env.ZERO)!=0){
			montoStr = String.valueOf(getStatementDifference());
//		} else {
//          BigDecimal monto = Env.ZERO;
//			BigDecimal openAmount = Env.ZERO;
//			if(getBeginningBalance()!=null && getBeginningBalance().compareTo(Env.ZERO)!=0){
//				openAmount = getBeginningBalance();
//			}
//			//
//			BigDecimal systemAmount = Env.ZERO;
//			if(getEndingBalance()!=null && getEndingBalance().compareTo(Env.ZERO)!=0){
//				systemAmount = getEndingBalance();// se actualiza al cierre
//			} else {
//				systemAmount = Env.ZERO;//TODO: Hacer el aprox de la caja
//			}
//
//			monto = openAmount.subtract(systemAmount);
//			montoStr = String.valueOf(monto);
		}
		
		return montoStr;
	}

	/**
	 */
	public static List<MCash> get(Properties ctx) {
		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		where.append(" BEGINNINGBALANCE is null ");
		return new Query(ctx, Table_Name, where.toString(), null)//
			.setParameters()
			.setOnlyActiveRecords(true)
			.addAccessLevelSQL(true)
			.list();
	}

	/**
	 * Regresa la suma de los ajustes realizados
	 * 
	 * @param ctx
	 * @param C_Cash_ID
	 */
	public BigDecimal getAdjustments(Properties ctx, int C_Cash_ID, String trxName) {
		BigDecimal resultado = BigDecimal.ZERO;

		if (ctx == null) {
			return resultado;
		}

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append(" SELECT COALESCE(SUM(CLINE.AMOUNT),0) AS AMOUNT ")
		.append(" FROM C_CASH CASH ")
		.append(" INNER JOIN C_CASHLINE CLINE ON CLINE.C_CASH_ID = CASH.C_CASH_ID ")
		.append(" INNER JOIN C_PAYMENT PAY ON PAY.C_PAYMENT_ID = CLINE.C_PAYMENT_ID ")
		.append(" LEFT JOIN  C_CHARGE CHARGE ON CHARGE.C_CHARGE_ID = PAY.C_CHARGE_ID ")
		.append(" WHERE CLINE.ISACTIVE = 'Y' ")
		.append(" AND CHARGE.TYPE IN ('A','B','G') ")
		.append(" AND CASH.C_CASH_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_CASH", "CASH"));

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_Cash_ID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				resultado = rs.getBigDecimal("AMOUNT");
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MCash.getAdjustments", e);
		} finally {
			DB.close(rs, pstmt);
		}

		return resultado;
	}

	
	/**
	 * Movimientos de caja
	 * @return
	 */
	public List<PagoLine> getMovCaja() {
		List<PagoLine> msl = new ArrayList<PagoLine>();
		
//		select cash.statementdate as dateC, op.name as cashierName, cash.secuencia as numCash,
//		cline.amount as amountL, fpago.paymentrule, ccaja.cashieramount as amountC, fpago.esdevol as devol,
//		pac.nombre_pac as nomPac, 
//		ctapac.documentno doc, 
//		us.name as userN , 
//		cline.checkno
//		, (SELECT COALESCE(MAX('Y'), 'N')
//		   FROM   C_PAYMENT L INNER JOIN C_CHARGE C ON C.C_CHARGE_ID = L.C_CHARGE_ID
//		   WHERE  C.TYPE IN ('A','B','G')
//		   AND    L.C_PAYMENT_ID = cline.c_payment_id) AS isAdj
//		FROM c_cash cash
//		left join exme_cortecaja ccaja on ccaja.c_cash_id = cash.c_cash_id
//		inner join exme_operador op on op.exme_operador_id = cash.exme_operador_id
//		inner join ad_user us on us.ad_user_id = op.ad_user_id
//		inner join c_cashline cLine on cline.c_cash_id = cash.c_cash_id
//		inner join exme_formapago fpago on fpago.exme_formapago_id = cline.exme_formapago_id
//		inner join exme_ctapac ctapac on ctapac.exme_ctapac_id = cline.exme_ctapac_id
//		inner join exme_paciente pac on pac.exme_paciente_id= ctapac.exme_paciente_id
//		where cline.isactive = 'Y' AND cash.c_cash_id = $P{C_CASH_ID}
		
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT cl.*, COALESCE(pa.Nombre_Pac, pai.Nombre_Pac) AS Nombre_Pac, ")
		.append("              COALESCE(ct.DocumentNo, cti.DocumentNo) AS Encounter,   ")
		.append("        Us.Name  AS Username, ")
		.append(" fp.Name AS TransactionType1, fp.paymentrule, coalesce (fp.esdevol, 'N') as devol, ")
		.append(" ch.Name AS TransactionType2,          ")
				
		.append(" (SELECT COALESCE(MAX('Y'), 'N')       ")
		.append("    FROM   C_Payment l                 ")
		.append("    INNER JOIN C_Charge c ON C.C_Charge_ID = L.C_Charge_ID  ")
		.append("    WHERE  c.Type IN ('A','B','G')     ")//Adjustment, BadDebtAdjustment, PatientAdjustments
		.append("    AND    l.C_Payment_ID = cl.C_Payment_ID) AS isAdj       ")
		   
		.append(" FROM  C_CashLine cl ")
		.append(" INNER JOIN C_Cash       cash ON       cash.C_Cash_ID = cl.C_Cash_ID          ")
		.append(" INNER JOIN Exme_Operador  Op ON  Op.Exme_Operador_Id = Cash.Exme_Operador_Id ")
		.append(" INNER JOIN AD_User        Us ON        Us.AD_User_Id = Op.AD_User_Id         ")
		
		.append(" INNER  JOIN Exme_Formapago fp ON cl.Exme_Formapago_Id = fp.Exme_Formapago_Id  ")
		.append(" LEFT  JOIN Exme_Ctapac    ct ON    cl.Exme_CtaPac_ID = ct.Exme_Ctapac_ID     ")
		.append(" LEFT  JOIN Exme_Paciente  pa ON  ct.Exme_Paciente_ID = pa.Exme_Paciente_ID   ")
		.append(" LEFT  JOIN C_Payment     Pay ON     Pay.C_Payment_ID = cl.C_Payment_ID       ")
		.append(" LEFT  JOIN C_Charge       ch ON       ch.C_Charge_ID = pay.C_Charge_ID       ")
		.append(" LEFT  JOIN exme_cortecaja cc ON         cc.c_cash_id = cash.c_cash_id        ")
		
		.append(" LEFT  JOIN C_Invoice     inv ON      cl.C_Invoice_Id = inv.C_Invoice_Id        ")
		.append(" LEFT  JOIN Exme_Ctapac   cti ON   inv.Exme_CtaPac_ID = cti.Exme_Ctapac_ID     ")
		.append(" LEFT  JOIN Exme_Paciente pai ON inv.Exme_Paciente_ID = pai.Exme_Paciente_ID   ")
		
		
		.append(" WHERE cl.isActive = 'Y' AND cl.c_Cash_id = ? ") //1: C_Cash_ID
		.append(" AND cl.cashtype != 'D' ")
		.append(" AND (cl.isCancelled='N' OR cl.Ref_Cash_id!=?)")//Card #1201 - ASnchez
		
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ",X_C_CashLine.Table_Name, "cl"))
		.append(" ORDER BY cl.Created Desc ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Cash_ID());
			pstmt.setInt(2, getC_Cash_ID());
			rs = pstmt.executeQuery();
			while (rs.next()){
				MCashLine cashLine = new MCashLine (getCtx(), rs, null);
				
				
				String ISADJ = rs.getString("isAdj");
				String DEVOL = rs.getString("devol");
				BigDecimal AMOUNTL = cashLine.getAmount();
				String PAYMENTRULE = rs.getString("paymentrule");
				String CHECKNO = cashLine.getCheckNo();
				
				// importe
				BigDecimal AMOUNTLPAGO = ISADJ.equals("N") ? DEVOL.equals("Y") ? AMOUNTL.abs().negate() : AMOUNTL : Env.ZERO;

				// ajuste
				BigDecimal AMOUNTLAJUSTE = ISADJ.equals("Y") ? DEVOL.equals("Y") ? AMOUNTL.abs().negate() : AMOUNTL : Env.ZERO;
						
						// transacction type
				String transacction_Type =	StringUtils.EMPTY;
				if(ISADJ.equals("Y")){ 
					transacction_Type =	Utilerias.getAppMsg(Env.getCtx(), "reportes.RepFacturasIVA_AP.ajustes");//"Adjustments"
				} else if(PAYMENTRULE!=null){ 
					
					if(X_EXME_FormaPago.PAYMENTRULE_Check.equals(PAYMENTRULE)){ 
						transacction_Type =	MRefList.get(getCtx(), X_EXME_FormaPago.PAYMENTRULE_AD_Reference_ID, PAYMENTRULE, null).getNameStr() + " " + CHECKNO;
					} else {
						transacction_Type =	MRefList.get(getCtx(), X_EXME_FormaPago.PAYMENTRULE_AD_Reference_ID, PAYMENTRULE, null).getNameStr();
					}
 				}
							
				PagoLine linea = new PagoLine();
				linea.setValues(getCtx(), cashLine);
				linea.setPacienteName(SecureEngine.decrypt(rs.getString("Nombre_Pac")));
				linea.setEncounter(rs.getString("Encounter"));
				linea.setMontoRecibido(AMOUNTLPAGO);
				linea.setMontoAplicado(AMOUNTLAJUSTE);
				linea.setTransactionType(transacction_Type);
//						rs.getString("TransactionType1")!=null
//						?rs.getString("TransactionType1")
//								:rs.getString("TransactionType2"));
				linea.setUsername(rs.getString("Username"));

								
				msl.add(linea);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs,pstmt);
		}
		return msl;
	}
	
	/**
	 * Buscar el diario de caja del operador que aun este abierto
	 * @param ctx: Contexto
	 * @param operadorId: Id de operador
	 * @return El primer objeto con el diario de daja "solo debe existir uno abierto a la vez por operador cajero"
	 */
	public static MCash getCashOpen(final Properties ctx,
			int operadorId) {
		return new Query(ctx, X_C_Cash.Table_Name, 
				" EXME_Operador_ID = ? AND C_Cash.DocStatus = "+DB.TO_STRING(DOCSTATUS_Drafted), null)//
				.setParameters(operadorId)
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.first();
	}
	
	/**
	 * Caja abierta por usuario
	 * @param ctx
	 * @param trxName
	 * @return
	 */
	public static MCash getCash (Properties ctx, int cashbookid, String trxName) {

		if(ctx == null){
			return null;
		}

		StringBuilder sql = new  StringBuilder()
		.append(" SELECT C_Cash.* ")
		.append(" FROM   C_Cash  ")// Diario de caja
		.append(" INNER JOIN EXME_Operador o ON  o.EXME_Operador_ID = C_Cash.EXME_Operador_ID  ")// Diario de caja
		.append(" WHERE  C_Cash.IsActive  = 'Y'  ")// Que este activo
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_Cash"))// Para el cliente y organizacion
		.append(" AND    C_Cash.DocStatus = ").append(DB.TO_STRING(DOCSTATUS_Drafted))// En estatus de borrador
		.append(" AND    o.IsActive       = 'Y' ")// Que este activo
		.append(" AND    o.AD_User_ID     = ?   ")// Para el usuario de loguin
		.append(cashbookid==0?" ":" AND C_Cash.C_CashBook_ID = ? ")// y que la caja sea la del param
		.append(" ORDER BY C_Cash.Created desc  ");// Ordenado por fecha de creación


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MCash m_cash = null;
		try {
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt(1,Env.getAD_User_ID(ctx));
			if(cashbookid>0){
				pstmt.setInt(2,cashbookid);
			}
			
			rs = pstmt.executeQuery();
			if (rs.next ()){
				m_cash = new MCash (ctx, rs, trxName);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MCash.getCash", e);
		} finally {
			DB.close(rs,pstmt);
		}

		return m_cash;
	}

	/**
	 * Facturas pendientes de procesar en caja
	 * @return
	 */
	public List<MInvoice> facturasPendientes() {
		final List<MInvoice> list = new ArrayList<MInvoice>();
		
		StringBuilder sql = new  StringBuilder()
		.append(" SELECT * FROM C_Invoice WHERE IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(getCtx(), " ", X_C_Invoice.Table_Name))
		.append(" AND C_Cash_ID = ?  ")// de la caja
		.append(" AND DocStatus = ").append(DB.TO_STRING(X_C_Invoice.DOCSTATUS_InProgress))
		.append(" AND SELLO IS NOT NULL ")
		.append(" AND Afecta_Caja = 'N' ");
		
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_Cash_ID());
			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				list.add(new MInvoice(getCtx(), rSet, get_TrxName()));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString());
		} finally {
			DB.close(rSet, pstmt);
		}

		//
		return list;
	}
	
	/**
	 * Crea la linea de caja a partir de un PagoLine
     * @param pagoLine PagoLine
	 * @param recibo
	 * @return MCashLine
	 */
	public MCashLine newCashLine(PagoLine pagoLine, String recibo) {
		final MCashLine cashLine = new MCashLine(getCtx(), 0, get_TrxName());
		cashLine.setC_Cash_ID(getC_Cash_ID());
		cashLine.setLine(10);
		cashLine.setReciboNo(recibo);
		cashLine.setProcessed(false);
		cashLine.setC_Currency_ID(pagoLine.getMonedaID());
		cashLine.setAmount(pagoLine.getMontoAplicado());
		// la forma de pago
		cashLine.setEXME_FormaPago_ID(pagoLine.getFormaPagoID());
		if (pagoLine.getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_CreditCard)) {
			cashLine.setCreditCardType(pagoLine.getCreditCardType());
			cashLine.setCreditCardNumber(pagoLine.getCreditCardNumber());
			cashLine.setCreditCardVV(pagoLine.getCreditCardVV());
			cashLine.setCreditCardExpMM(pagoLine.getCreditCardExpMM());
			cashLine.setCreditCardExpYY(pagoLine.getCreditCardExpYY());
			cashLine.setA_Name(pagoLine.getA_Name());
		} else if (pagoLine.getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_Check)) {
			cashLine.setRoutingNo(pagoLine.getRoutingNo());
			cashLine.setAccountNo(pagoLine.getAccountNo());
			cashLine.setCheckNo(pagoLine.getCheckNo());
			cashLine.setMicr(pagoLine.getMicr());
			cashLine.setA_Name(pagoLine.getA_Name());
		}
		if (MFormaPago.PAYMENTRULE_OnCredit.equalsIgnoreCase(pagoLine.getPaymentRule())) {// ACREDITO
			cashLine.setCashType(MCashLine.CASHTYPE_CuentasXCobrar);
		}
		return cashLine;
	}
	
	/**
	 * Crear lineas de pagos en cashline para na factura
	 * desde captura de pagos
	 * @param lstPagoLine
	 * @param recibo
	 * @param decCambio
	 * @param invoice
	 * @return BigDecimal monto total de pagos
	 */
	public BigDecimal createInvoicePayments(final List<PagoLine> lstPagoLine, 
		                                    final String recibo, 
		                                    final BigDecimal decCambio, 
		                                    final MInvoice invoice) {
		
		BigDecimal pagoRecLocal = BigDecimal.ZERO;

		if (lstPagoLine != null && !lstPagoLine.isEmpty()) {
			MFormaPago payTerm = null;
			boolean cxc = false;
			final List<Integer> chashlinesIds = new ArrayList<Integer>();
			final StringBuilder sql = new StringBuilder();
			sql.append(" C_CashLine_ID IN (");
			
			for (PagoLine pagoLine : lstPagoLine) {
				final MCashLine cashLine = createCashLine(pagoLine, invoice, recibo, decCambio);
				if (MCashLine.CASHTYPE_CuentasXCobrar.equals(cashLine.getCashType())) {
					cxc = true;
					payTerm = cashLine.getFormaPago();
				} else {
					if (payTerm == null) {
						payTerm = cashLine.getFormaPago();// TODO ? porque la ultima o la primera forma de pago?
					}
					if (!chashlinesIds.isEmpty()) {
						sql.append(",");
					}
					sql.append("?");
					chashlinesIds.add(cashLine.getC_CashLine_ID());
				}
				pagoRecLocal = pagoRecLocal.add(cashLine.getAmountRounded2());
			}
			//Actualiza la factura
			updateInvoice(invoice, cxc, payTerm);
			
			invoice.setAfecta_Caja(true);// solo cuando se cobra en caja o es pagada totalmente con anticipos
			if (!invoice.save(get_TrxName())) {
				return null;
			}

			if (!chashlinesIds.isEmpty()) {
				sql.append(") ");
				sql.append(" AND C_Invoice_ID=? ");

				chashlinesIds.add(invoice.getC_Invoice_ID());
				// obtenemos los cashline ordenados por moneda
				final List<MCashLine> lines = new Query(getCtx(), MCashLine.Table_Name, sql.toString(), get_TrxName())//
					.setParameters(chashlinesIds)//
					.setOrderBy(" C_Currency_ID ")//
					.list();
				createAllocations(invoice, lines);
			}
		}
		return pagoRecLocal;
	}
	
	/**
	 * Crea la linea de caja a partir de una factura
	 * 
	 * @param pagoLine PagoLine
	 * @param invoice MInvoice
	 * @param recibo
	 * @return MCashLine
	 */
	public MCashLine createCashLine(final PagoLine pagoLine,
									final MInvoice invoice,
									final String recibo,
									final BigDecimal decCambio) {
		// Lineas para Factura
		final MCashLine cashLine = newCashLine(pagoLine, recibo);
		cashLine.setC_Invoice_ID(invoice.getC_Invoice_ID());
		cashLine.setIsPrepayment(false);
		// si no es CxC
		if (!MCashLine.CASHTYPE_CuentasXCobrar.equals(cashLine.getCashType())) {
			// Si es efectivo y hay cambio se agrega la linea del cambio
			if (MFormaPago.PAYMENTRULE_Cash.equals(pagoLine.getPaymentRule())) {
				if (decCambio.compareTo(BigDecimal.ZERO) > 0) {
					cashLine.setCambio(pagoLine.getCambioAmt());
				}
				cashLine.setCashType(MCashLine.CASHTYPE_Invoice);
			} else { // si no es efectivo
				// generamos el pago en C_Payment
				cashLine.createPayment(invoice, getAD_OrgTrx_ID());
				cashLine.setCashType(MCashLine.CASHTYPE_Anticipo);
			}
		}
		if (!cashLine.save()) {
			throw new MedsysException();
		}
		return cashLine;
	}
	
	/**
	 * Actualiza la forma de pago de la factura
	 * 
	 * @param invoice
	 * @param cxc
	 * @param payTerm
	 */
	private void updateInvoice(MInvoice invoice, boolean cxc, MFormaPago payTerm) {
		if (invoice != null) {
			if (cxc) {
				final MBPartner socio = new MBPartner(getCtx(), invoice.getC_BPartner_ID(), null);
				if (socio.getC_PaymentTerm_ID() > 0 && socio.getPaymentRule() != null) {
					invoice.setC_PaymentTerm_ID(socio.getC_PaymentTerm_ID());
					invoice.setPaymentRule(socio.getPaymentRule());
				} else if (payTerm != null) {
					invoice.setC_PaymentTerm_ID(payTerm.getC_PaymentTerm_ID());
					invoice.setPaymentRule(MFormaPago.PAYMENTRULE_OnCredit);
				}
				invoice.setIsPaid(false);
			} else {
				invoice.setIsPaid(true);
				if (payTerm != null) {
					invoice.setC_PaymentTerm_ID(payTerm.getC_PaymentTerm_ID());
					invoice.setPaymentRule(payTerm.getPaymentRule());
				}
			}
		}
	}
	
	/**
	 * Crea los registros M_AllocationHdr y M_AllocationLine por cada cashline hacia la factura,
	 * si cashline contiene un Payment, en AllocationLine relacionará el payment y la factura
	 * Un header por factura y por moneda
	 * 
	 * @param invoice
	 * @param cashlinesIds
	 */
	private void createAllocations(MInvoice invoice, List<MCashLine> cashlines) {
		if (invoice != null && cashlines != null && !cashlines.isEmpty()) {
			MAllocationHdr hdr = null;
			Timestamp dateAcct = Env.getCurrentDate();
			for (MCashLine line : cashlines) {
				if(MCashLine.CASHTYPE_CuentasXCobrar.equals(line)) {
					continue;
				}
				if (hdr == null || line.getC_Currency_ID() != hdr.getC_Currency_ID()) {
					// se completa la linea anterior Ticket #08738
					if(hdr != null && DOCSTATUS_Drafted.equals(hdr.getDocStatus())){
						hdr.completeInvoice(true); // ok
					}
					
					hdr = new MAllocationHdr(getCtx(), false
							, dateAcct
							, line.getC_Currency_ID()
							, Msg.translate(getCtx(), "C_Cash_ID") + ": " + getName()+" [i]"
							, get_TrxName());
					if (!hdr.save()) {
						throw new MedsysException();
					}
				}
				hdr.createAllocationLine(line, invoice);
//				hdr.completeInvoice(true); // ok
//				hdr.setDocStatus(hdr.completeIt());// Completar
//				if (!hdr.save()) {
//					throw new MedsysException();
//				}
			}
			// Ticket #08738
			if(hdr != null && DOCSTATUS_Drafted.equals(hdr.getDocStatus())){
				hdr.completeInvoice(true); // ok
			}
		}
	}
	
	/**
	 * Crear lineas de pagos en cashline de anticipos
	 * desde captura de pagos
	 * @param lstPagoLine
	 * @param recibo
	 * @param clientID
	 * @param anticipo
	 * @return BigDecimal monto total de pagos
	 */
	public BigDecimal createAdvancePayments(final List<PagoLine> lstPagoLine 
			, final String recibo
			, final int clientID
			, final MEXMEAnticipo anticipo){
		
		BigDecimal pagoRecLocal = BigDecimal.ZERO;
		
		if (lstPagoLine != null && !lstPagoLine.isEmpty()) {
			for (PagoLine pagoLine : lstPagoLine) {
				final MCashLine cashLine = createCashLine(pagoLine, clientID, anticipo, recibo);
				if (cashLine != null) {
					pagoRecLocal = pagoRecLocal.add(cashLine.getAmountRounded2());
				}
			}
		}
		// Act
		return pagoRecLocal;
	}
	/**
	 * Crear lineas de pagos en cashline de anticipos
	 * desde captura de pagos
	 * @param lstPagoLine
	 * @param recibo
	 * @param clientID
	 * @param EXME_Operador_ID
	 * @param anticipo
	 * @return BigDecimal monto total de pagos
	 * @deprecated Ya no se usa parametro EXME_Operador_ID
	 */
	public BigDecimal createAdvancePayments(final List<PagoLine> lstPagoLine 
			, final String recibo
			, final int clientID
			, final int EXME_Operador_ID // no se usa, sera removido
			, final MEXMEAnticipo anticipo){
		
		return createAdvancePayments(lstPagoLine,recibo,clientID,anticipo);
	}

	/**
	 * Crea la linea de caja a partir de un anticipo
     * @param pagoLine PagoLine
	 * @param clientID C_BPartner_ID
	 * @param EXME_Operador_ID
	 * @param anticipo MEXMEAnticipo
	 * @param recibo
	 * @return MCashLine
	 *///TODO Validar Card 1229// ok
	public MCashLine createCashLine(PagoLine pagoLine,
									int clientID,
									MEXMEAnticipo anticipo,
									String recibo) {
		//if (anticipo != null) {
			final MCashLine cashLine = newCashLine(pagoLine, recibo);

			// Lineas para Anticipos
			//BigDecimal amount;
//			if (cashLine.getFormaPago().isEsDevol()) {
//				//amount = pagoLine.getMontoAplicado().negate();// TODO ???
//				anticipo.setTotal(anticipo.getTotal().subtract(pagoLine.getMontoAplicado().negate()));
//				anticipo.setSaldo(anticipo.getSaldo().subtract(pagoLine.getMontoAplicado().negate()));
//			} else {
//				//amount = pagoLine.getMontoAplicado();//
//				anticipo.setTotal(anticipo.getTotal().add(pagoLine.getMontoAplicado()));
//				anticipo.setSaldo(anticipo.getSaldo().add(pagoLine.getMontoAplicado()));
//			}
			//anticipo.setTotal(anticipo.getTotal().add(amount));
			//anticipo.setSaldo(anticipo.getSaldo().add(amount));
			
			cashLine.setCashType(MCashLine.CASHTYPE_Anticipo);
			cashLine.setIsPrepayment(true);
			if(anticipo!=null) {
				cashLine.setEXME_CtaPac_ID(anticipo.getEXME_CtaPac_ID());
			}

			// generamos el pago en C_Payment
//			MPayment payment = cashLine.createPayment();
			// procesamos el payment
			final MPayment payment = cashLine.processPayment(clientID, getAD_OrgTrx_ID(), MPayment.TYPE_AdvancePayment);

			// generamos la relacion de pago - cta pag
//			MCtaPacPag.createFromPayment(payment, anticipo.getEXME_CtaPacExt_ID());
//          MCtaPacPag.getFromPayment(payment, anticipo.getEXME_CtaPacExt_ID(), true);
			if(anticipo!=null){
				anticipo.createAdvancePaymentCustomer(payment, cashLine.getEXME_FormaPago_ID());
			}
			
			if (cashLine.save()) {
				return cashLine;
			}
		//}
		return null;
	}

	/**
	 * Al cancelar el pago hecho en Caja
	 * NO DEBERIA VOLVER AL CANCELAR LAS LINEAS DE CASHLINE QUE SE HICIERON CON EL ANTICIPO
	 * @param lstCashLine
	 * @param notaCredID
	 * @param cNewInvoiceID
	 * @param reAsignacion
	 * @param trxName
	 * @return
	 */
	public String cancelCashLines(final Properties ctx
			, final List<MCashLine> lstCashLine
			, final MInvoice oldInvoice
			, final String trxName){
		if (oldInvoice == null || lstCashLine == null) {
			return "error.caja.allocHdr.noSave";
		}

		if (lstCashLine.isEmpty()) {
			return null;
		}

		// Crear la asignacion de la devolucion de la factura
		MAllocationHdr allocHdr = null; // no crear si la factura es CxC

		// Genera movimiento de caja con devolucion de dinero y/o y a su vez de cargo a la nueva preFactura
		for (final MCashLine mCashLine : lstCashLine) {
			if (MCashLine.CASHTYPE_CuentasXCobrar.equals(mCashLine.getCashType())) {
				// Card #1201 ASanchéz
				// Asigna a la columna Ref_Cash_id el id de la persona que cancelo la factura
				if (!mCashLine.isCancelled()) {
					mCashLine.setIsCancelled(true);
					mCashLine.setRef_Cash_ID(getC_Cash_ID());
					if (!mCashLine.save(get_TrxName())) {
						s_log.log(Level.SEVERE, "No se cancelaron las líneas cxc");
						return "error.caja.cashLine.noSave";
					}
				}
				continue;
			} else {
				// Generar una copia de la linea del pago de la factura (-)
				final MCashLine devolLine = createLineCashLine(mCashLine, oldInvoice, trxName);
				if (devolLine == null) {
					s_log.log(Level.SEVERE, "No se crearon los cobro negativos");
					return "error.caja.cashLine.noSave";
				}
				//
				final int inverso = mCashLine.getFormaPago().getInverso();
				final MFormaPago mFormaPago = new MFormaPago(getCtx(), inverso, trxName);

				// Se crea el header solo si la factura tiene al menos 1 pago
				if (allocHdr == null) { // Ticket #07894 Lama
					allocHdr = new MAllocationHdr(ctx, false //
						, Env.getCurrentDate()//
						, oldInvoice.getC_Currency_ID()//
						, Utilerias.getLabel("msg.Cancelacion") + " [" + oldInvoice.getDocumentNo() + "] [h]"//
						, trxName);
					if (!allocHdr.save()) {
						s_log.log(Level.SEVERE, "No se creo la asignación para las lineas de cobros");
						return "error.caja.allocHdr.noSave";
					}
				}

				// Crear la linea se asignacion efectivo - factura
				if (mCashLine.getC_Payment_ID() == 0 || mFormaPago.isCash()) {
					if (!createAllocation(devolLine, allocHdr, oldInvoice, trxName)) {
						s_log.log(Level.SEVERE, "No se crearon las asignaciones del cobro negativos");
						return "error.caja.cashLine.noSave";
					}
				}
				// Devolucion de cobros hechos a la factura o remision que no son anticipos
				else if (devolLine.getEXME_CtaPac_ID() <= 0) {
					// Cobros hechos a la factura no anticipos
					final MPayment devolPayment = createPaymentNegate(mCashLine, trxName);
					if (devolPayment == null) {
						s_log.log(Level.SEVERE, "No se crearon los pagos del pago/cobro negativos");
						return "error.caja.cashLine.noSave";
					}
					devolLine.setC_Payment_ID(devolPayment.getC_Payment_ID());
					devolLine.save();

					// Asignacion del pago
					if (!createAllocationPay(devolPayment, allocHdr, oldInvoice, trxName)) {
						s_log.log(Level.SEVERE, "No se crearon las asignaciones del pago/cobro negativos");
						return "error.caja.cashLine.noSave";
					}
				}
			}

		}// devolucion y cargo de cash_line
		
		if (allocHdr != null) {
			final ErrorList err = allocHdr.completeInvoice(false);
//			allocHdr.setDocStatus(allocHdr.completeIt()); // Completar
//			if (!allocHdr.save()) {
//				s_log.log(Level.SEVERE, "No se completo la asignación de reversa de los cobros");
			if(!err.isEmpty()) {
				return Utilerias.getLabel("error.caja.cashLine.noSave")+" "+err.toString();
			}
		}
		return null;
	}
	
	/** Crear copia de la linea de caja */
	private MCashLine createLineCashLine(final MCashLine mCashLine, final MInvoice invoice, final String trxName){
		// Generar una copia de la linea del pago de la factura
		final MCashLine devolLine = MCashLine.copyFrom(mCashLine, getC_Cash_ID(),trxName);
		// Asociar la devolución a la nota de remision si es cancelación de ventas
		// o a la factura si es cancelación de facturas
		// NOTA: Considerar que no todas las facturas tienen remisiones
		if (invoice.getRef_Invoice_Sales_ID()>0) {
			devolLine.setC_Invoice_ID(invoice.getRef_Invoice_Sales_ID());// Es una factura y apunta a la nota de remision
		}else {
			devolLine.setC_Invoice_ID(invoice.getC_Invoice_ID());// Es una nota de remision
		}		
		// Invertir la forma de pago para que sea devolución 
		devolLine.setEXME_FormaPago_ID(mCashLine.getFormaPago().getInverso());
		// Invertir el monto del pago para que sea negativo
		devolLine.setAmount(mCashLine.getAmount().negate());
		// Si no existe tipo de linea de caja poner como anticipo
		devolLine.setCashType(mCashLine.getCashType()==null?MCashLine.CASHTYPE_Anticipo:mCashLine.getCashType());
		return devolLine.save()?devolLine:null;
	}
	
	/** Crear asignacion de la linea de caja a la factura */
	private boolean createAllocation(final MCashLine mCashLine, final MAllocationHdr mAllocHdr, final MInvoice mInvoice, final String trxName){
		// Allocation Line
		final MAllocationLine allocLine = new MAllocationLine (
				mAllocHdr 
				, mCashLine.getAmount() 
				, Env.ZERO
				, Env.ZERO
				, Env.ZERO);
		allocLine.set_TrxName(trxName);
		allocLine.setDocInfo(mInvoice.getC_BPartner_ID(), 0, mInvoice.getC_Invoice_ID());
		allocLine.setPaymentInfo(0, mCashLine.getC_CashLine_ID());
		allocLine.setOverUnderAmt(Env.ZERO);
		return allocLine.save();
	}
	
	/** crear el pago negativo por devolución */
	private MPayment createPaymentNegate(final MCashLine mCashLine, final String trxName){
		final MPayment devolPayment = MPayment.copyFrom(mCashLine.getPayment(), 0, trxName);
		// Fecha en que se cancela
		devolPayment.setDateTrx(Env.getCurrentDate());
		devolPayment.setDateAcct(Env.getCurrentDate());
		// invertimos el monto del pago
		devolPayment.setAmount(mCashLine.getAmount().negate());
		devolPayment.setPayAmt(mCashLine.getAmount().negate());
		// insertar
		return devolPayment.save()?devolPayment:null;
	}
	
	/** crear asignacion de un pago a una factura */
	private boolean createAllocationPay(final MPayment mPayment, final MAllocationHdr mAllocHdr, final MInvoice mInvoice, final String trxName){
		final MAllocationLine payAllocLine = new MAllocationLine (
				mAllocHdr 
				, mPayment.getPayAmt()
				, Env.ZERO
				, Env.ZERO
				, Env.ZERO);
		payAllocLine.set_TrxName(trxName);
		payAllocLine.setDocInfo(mInvoice.getC_BPartner_ID(), 0, mInvoice.getC_Invoice_ID());
		payAllocLine.setPaymentInfo(mPayment.getC_Payment_ID(), 0);
		payAllocLine.setOverUnderAmt(Env.ZERO);
		return payAllocLine.save();
	}

	/**
	 * Regresa la cantidad total de pagos y devoluciones
	 * @return BigDecimal La cantidad total de pagos
	 * @throws MedsysException una excepcion para manejar el error si ocurriese.
	 */
	public static BigDecimal getAmountCash(Properties ctx, int cashID, int formaPagoID) {
		List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new  StringBuilder();
		sql.append(" SELECT SUM(COALESCE(cl.AMOUNT,0)) as sumAmt \n");
//		sql.append(" SELECT SUM(CASE WHEN fp.EsDevol='Y' \n");
//		sql.append("                 THEN ABS(COALESCE(cl.AMOUNT,0)) * -1 \n");
//		sql.append("                 ELSE COALESCE(cl.AMOUNT,0) END) as sumAmt \n");
		sql.append(" FROM C_CashLine cl ");
		sql.append(" INNER JOIN EXME_FormaPago fp ON (cl.EXME_FormaPago_ID=fp.EXME_FormaPago_ID) \n");
		sql.append(" WHERE cl.isActive = 'Y' \n");
		sql.append(" AND cl.C_Cash_ID = ? \n");
		params.add(cashID);
		sql.append(" AND cl.C_Currency_ID=? \n");//Card #1170 - Lama
		params.add(Env.getC_Currency_ID(ctx));
		sql.append(" AND (cl.isCancelled='N' OR cl.Ref_Cash_id!=?) \n");//Card #1201 - ASnchez
        params.add(cashID);
		if(formaPagoID > 0) {
			sql.append(" AND COALESCE(fp.Ref_FormaPago_ID,fp.EXME_FormaPago_ID)=? \n");
			params.add(formaPagoID);
		}
		// Condicion añadida para evitar que los registros tipo ajuste sean tomados en cuenta en el total para Count Cash (WCorteCaja)
		// RQM 4429 - Count Cash. Manda mensaje de diferencias cuando no las hay, ya que esta tomando montos de ajustes realizados desde LATE CHARGES
		// Mayo 21 de 2013
		sql.append(" AND NOT EXISTS(SELECT C_PAYMENT_ID FROM C_PAYMENT P \n");
		sql.append("                INNER JOIN C_CHARGE CH ON CH.C_CHARGE_ID = P.C_CHARGE_ID \n");
		sql.append("                WHERE C_PAYMENT_ID = CL.C_PAYMENT_ID \n");
		sql.append("                AND CH.TYPE IN ('A','B','G','C','D','I')) \n");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_CashLine.Table_Name, "cl"));
		BigDecimal total = DB.getSQLValueBD(null, sql.toString(), params);
		return total == null ? BigDecimal.ZERO : total;
	}
	
	/**
	 * Actualizacion de estatus
	 * al cerrar la caja
	 * @return null sin errores
	 */
	public void closeCash() {
		final MCashLine[] cashDet = MCash.getLinesCash(getCtx(), getC_Cash_ID(), get_TrxName());
		if (cashDet.length > 0) {
			setDocStatus(completeIt());
//			setDocAction(DOCACTION_Close);
//			setProcessed(true);
//			setEndDate(Env.getCurrentDate());
		} else {
			completeAllocation();//RQM #5303
			setDocStatus(DOCSTATUS_Voided);
			setDocAction(DOCACTION_None);
			setProcessed(false);
			setIsApproved(false);
		}
		if ((!DOCSTATUS_Voided.equals(getDocStatus()) && !DOCSTATUS_Completed.equals(getDocStatus())) || !save()) {
			if (StringUtils.isNotBlank(getProcessMsg())) {
				throw new MedsysException(Msg.getMsg(getCtx(), StringUtils.removeEnd(StringUtils.removeStart(getProcessMsg(), "@"), "@")));
			} else {
				throw new MedsysException(Utilerias.getMsg(getCtx(), "error.cierre.cash"));
			}
		}
	}
	
	
	/**
	 * Actualizacion de estatus
	 * al cerrar la caja
	 * @return null sin errores
	 * @deprecated
	 */
	public String closedStatusUpdate(){

		final MCashLine[] cashDet = MCash.getLinesCash(
				getCtx(), getC_Cash_ID(),  get_TrxName());
		if (cashDet.length > 0) {
			setDocStatus(completeIt());
			setDocAction(DOCACTION_Close);
			setProcessed(true);
			setEndDate(new Timestamp(System.currentTimeMillis()));
			if (!getDocStatus().equals(DOCSTATUS_Completed)
					|| !save(get_TrxName())) {
				getProcessMsg();
//				if (m_trx != null) {
//					m_trx.rollback();
//					commit = false;
//				}
				if (StringUtils.isNotBlank(getProcessMsg())) {
//					Utils.error(
							return Msg.getMsg(Env.getCtx(),
									StringUtils.removeEnd(
											StringUtils.removeStart(
													getProcessMsg(),
													"@"
											), 
											"@"
									)
							);
//							, null
//					);
				} else {
//					Utils.error(
							return Utilerias.getMessage(Env.getCtx(), null,
							"error.cierre.cash");
//							, null);
				}
			}
		} else {
			setDocStatus(DOCSTATUS_Voided);
			setDocAction(DOCACTION_None);
			setProcessed(false);
			setIsApproved(false);

			if (!getDocStatus().equals(DOCSTATUS_Voided)
					|| !save(get_TrxName())) {
//				if (m_trx != null) {
//					m_trx.rollback();
//					commit = false;
//				}
				if (StringUtils.isNotBlank(getProcessMsg())) {
//					Utils.error(
					return		Msg.getMsg(Env.getCtx(),
									StringUtils.removeEnd(StringUtils
											.removeStart(
													getProcessMsg(),
													"@"), "@"));
//					, null);
				} else {
//					Utils.error(
							return Utilerias.getMessage(Env.getCtx(), null,
							"error.cierre.cash");
//							, null);
				}
			}
		}
		return "";
	}
	
	/**
	 * setUpdateStatusInvoice
	 * @return
	 */
	public int setUpdateStatusInvoice(){
		final StringBuilder sql = new StringBuilder()
		.append(" UPDATE C_Invoice i ")
		.append(" SET Processed = 'Y' ")
		.append(" , DocStatus = 'CO' ")
		.append(" , DocAction = 'CL' ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND DocStatus <> 'VO' ")
		.append(" AND C_Invoice_ID In ( ")
		.append(" 		SELECT C_Invoice_ID FROM C_CashLine ")
		.append(" 		WHERE IsActive = 'Y' AND C_Cash_ID = ? ")
		.append(" 		AND CashType   <> ?   ")
		.append(" 		) ");
		final int no = DB.executeUpdate(sql.toString(), new Object[]{getC_Cash_ID(), MCashLine.CASHTYPE_CuentasXCobrar}, get_TrxName());
		log.info("Reset=" + no);
		return no;
	}
	
	
	/**
	 * Actualiza los estatus de inprogress a complete para las facturas que no tienen pagos con cuentas por cobrar
	 * Tambien incluye las facturas pagadas con anticipos, metodo exclusivo para México
	 * @return
	 */
	public int setUpdateStatusInvoiceMX(){
		final StringBuilder sql = new StringBuilder()
		.append(" UPDATE C_Invoice     ")
		.append(" SET Processed = 'Y'  ")
		.append(" ,   DocStatus = 'CO' ")
		.append(" ,   DocAction = 'CL' ")
		.append(" WHERE IsActive = 'Y' ")
		.append(" AND   isSoTrx = 'Y' ")
		.append(" AND C_INVOICE_ID IN (")
		.append("       SELECT DISTINCT i.c_invoice_id ")
		.append("       FROM c_invoice i ")// Facturas de las remisiones cobradas
		.append(" 		LEFT join c_cashline cl on (cl.c_invoice_id=i.C_invoice_id ")
		.append("                               AND cl.CashType is null OR cl.CashType<>? ) ")
		.append(" 		WHERE i.IsActive='Y' ")
		.append("       AND i.DocStatus='IP' ")
		.append(" 		AND (i.C_cash_id=? OR cl.c_cash_id=?) ")
		.append(" 		)");
		final int no = DB.executeUpdate(sql.toString(), 
			new Object[]{
			MCashLine.CASHTYPE_CuentasXCobrar, getC_Cash_ID(),getC_Cash_ID(),
			}, get_TrxName());
		log.info("Reset=" + no);
		return no;
	}
	
	public int setUpdateStatusInvoiceMultipleMX(){
		final StringBuilder sql = new StringBuilder()
		.append(" UPDATE C_Invoice     ")
		.append(" SET Processed = 'Y'  ")
		.append(" ,   DocStatus = 'CO' ")
		.append(" ,   DocAction = 'CL' ")
		.append(" WHERE IsActive   = 'Y' ")
		.append(" AND   isSoTrx    = 'Y' ")
		.append(" AND   isMultiple = 'Y' ")
		.append(" AND   DocStatus  = 'IP' ")
		.append(" AND   C_cash_id  = ? ");
		final int no = DB.executeUpdate(sql.toString(), getC_Cash_ID(), get_TrxName());
		log.info("Reset=" + no);
		return no;
	}
	
	/**
	 * setUpdateStatusLine for México
	 * @return
	 */
	public int setUpdateStatusCxCMX(){
		final StringBuilder sql = new StringBuilder()
		.append(" UPDATE C_Invoice i         ")
		.append(" SET Processed = 'Y'        ")
		.append(" , DocStatus = 'CO'         ")
		.append(" , DocAction = 'CL'         ")
		.append(" , IsPaid='N'               ")
		.append(" WHERE IsActive = 'Y'       ")
		.append(" AND   isSoTrx = 'Y'        ")
		.append(" AND DocStatus = 'IP'      ")
		.append(" AND ref_invoice_sales_id In (      ")
		.append(" 		SELECT DISTINCT C_Invoice_ID ")
		.append(" 		FROM C_CashLine      ")
		.append(" 		WHERE IsActive = 'Y' ")
		.append(" 		AND C_Cash_ID  = ?   ")
		.append(" 		AND CashType   = ?   ")
		.append(" 		) ");
		final int no = DB.executeUpdate(sql.toString(), new Object[]{getC_Cash_ID(), MCashLine.CASHTYPE_CuentasXCobrar}, get_TrxName());
		log.info("Reset=" + no);
		return no;
	}
	
	/**
	 * setUpdateStatusLine
	 * @return
	 */
	public int setUpdateStatusPayment(){
		final StringBuilder sql = new StringBuilder()
		.append(" UPDATE C_Payment         ")
		.append(" SET    Processed = 'Y'   ")
		.append(" WHERE  IsActive = 'Y'    ")
		.append(" AND    Posted = 'N'      ")
		.append(" AND    C_Payment_ID In ( ")
		.append(" 		SELECT C_Payment_ID FROM C_CashLine ")
		.append(" 		WHERE IsActive = 'Y' AND C_Cash_ID = ? ")
		.append(" )                        ");
		final int no = DB.executeUpdate(sql.toString(), getC_Cash_ID(), get_TrxName());
		log.info("Reset=" + no);
		return no;
	}
	
	
	/**
	 * setUpdateStatusLine
	 * @return
	 */
	public int setUpdateStatusCxC(){
		final StringBuilder sql = new StringBuilder()
		.append(" UPDATE C_Invoice i         ")
		.append(" SET Processed = 'Y'        ")
		.append(" , DocStatus = 'CO'         ")
		.append(" , DocAction = 'CL'         ")
		.append(" , IsPaid='N'               ")
		.append(" WHERE IsActive = 'Y'       ")
		.append(" AND DocStatus <> 'VO'      ")
		.append(" AND C_Invoice_ID In (      ")
		.append(" 		SELECT DISTINCT C_Invoice_ID ")
		.append(" 		FROM C_CashLine      ")
		.append(" 		WHERE IsActive = 'Y' ")
		.append(" 		AND C_Cash_ID  = ?   ")
		.append(" 		AND CashType   = ?   ")
		.append(" 		) ");
		final int no = DB.executeUpdate(sql.toString(), new Object[]{getC_Cash_ID(), MCashLine.CASHTYPE_CuentasXCobrar}, get_TrxName());
		log.info("Reset=" + no);
		return no;
	}
	
	public List<MCashLineDiff> getLstConciliacion() {
		return lstConciliacion;
	}

	public void setLstConciliacion(List<MCashLineDiff> lstConciliacion) {
		this.lstConciliacion = lstConciliacion;
	}

	public List<MFormaPago> getLstDiferent() {
		return lstDiferent;
	}

	public void setLstDiferent(List<MFormaPago> lstDiferent) {
		this.lstDiferent = lstDiferent;
	}

	public BigDecimal getDiferencias() {
		return diferencias;
	}

	public void setDiferencias(BigDecimal diferencias) {
		this.diferencias = diferencias;
	}
	
	/**
	 * Balance de diferencias en caja (debido al corte)
	 * 
	 * @param trxName
	 * @return
	 */
	public boolean balance(String trxName) {
		// Validamos si hay diferencias en caja (debido al corte)
		if (lstConciliacion != null && !lstConciliacion.isEmpty()) {
			int linea = 0;
			for (MCashLine cashLine : lstConciliacion) {
				cashLine.forceIsNew();
				linea = linea + 10;

//				final MCashLine cashLine = new MCashLine(getCtx(), cashConcil.getC_CashLine_ID(), trxName);
//				cashLine.setAmount(cashConcil.getAmount());
//				cashLine.setC_Cash_ID(getC_Cash_ID());
				cashLine.setCashType(MCashLine.CASHTYPE_Difference);
				cashLine.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
				cashLine.setLine(linea);
				// cashLine.setC_CashLine_ID(cashConcil.getC_CashLine_ID());
//				cashLine.setEXME_FormaPago_ID(cashConcil.getEXME_FormaPago_ID());
				if (cashLine.getAmount() != null) {
					diferencias = diferencias.add(cashLine.getAmount());
				}

				if (!cashLine.save(trxName)) {
					throw new MedsysException();
				}
			}
		}// fin Si existen diferencias entre los cortes y los movimientos de
			// caja

		return true;
	}

	//CARD #1299
	/**
	 * Crear lineas de pagos en cashline de anticipos
	 * desde captura de pagos
	 * @param lstPagoLine
	 * @param recibo
	 * @param clientID
	 * @param anticipo
	 * @return BigDecimal monto total de pagos
	 */
	public BigDecimal createAdvancePayments(final List<PagoLine> lstPagoLine 
			, final String recibo
			, final int clientID
			, final int extensionId){
		BigDecimal pagoRecLocal = BigDecimal.ZERO;
		if (lstPagoLine != null && !lstPagoLine.isEmpty()) {
			final MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipoToExtension(getCtx()
					, new MEXMECtaPacExt(getCtx(), extensionId, get_TrxName()));
			for (PagoLine pagoLine : lstPagoLine) {
				final MCashLine cashLine = createCashLine(pagoLine, clientID, anticipo, recibo);
				if (cashLine != null) {
					pagoRecLocal = pagoRecLocal.add(cashLine.getAmountRounded2());
				}
			}
		}
		// Act
		return pagoRecLocal;
	}
	
	/**
	 * Crea la linea de caja a partir de un anticipo
     * @param pagoLine PagoLine
	 * @param clientID C_BPartner_ID
	 * @param EXME_Operador_ID
	 * @param anticipo MEXMEAnticipo
	 * @param recibo
	 * @return MCashLine
	 */
	public MCashLine createLine(PagoLine pagoLine,
									int clientID,
									MEXMEAnticipo anticipo,
									String recibo) {
			final MCashLine cashLine = newCashLine(pagoLine, recibo);
			cashLine.setCashType(MCashLine.CASHTYPE_Anticipo);
			cashLine.setIsPrepayment(true);
			if(anticipo!=null) {
				cashLine.setEXME_CtaPac_ID(anticipo.getEXME_CtaPac_ID());
			}
			final MPayment payment = cashLine.processPayment(clientID, getAD_OrgTrx_ID(), MPayment.TYPE_AdvancePayment);
			if(anticipo!=null){
				anticipo.createAdvancePaymentCustomer(payment, cashLine.getEXME_FormaPago_ID());
			}
			
			if (cashLine.save()) {
				return cashLine;
			}
		//}
		return null;
	}
	
	
	/** Crear copia de la linea de caja */
	public MCashLine addLineNegative(final MCashLine mCashLine, final MInvoice invoice, final String trxName){
		// Generar una copia de la linea del pago de la factura
		final MCashLine devolLine = MCashLine.copyFrom(mCashLine, getC_Cash_ID(),trxName);
		// Asociar la devolución a la nota de remision si es cancelación de ventas
		// o a la factura si es cancelación de facturas
		// NOTA: Considerar que no todas las facturas tienen remisiones
		if (invoice.getRef_Invoice_Sales_ID()>0) {
			devolLine.setC_Invoice_ID(invoice.getRef_Invoice_Sales_ID());// Es una factura y apunta a la nota de remision
		}else {
			devolLine.setC_Invoice_ID(invoice.getC_Invoice_ID());// Es una nota de remision
		}		
		// Invertir la forma de pago para que sea devolución 
		devolLine.setEXME_FormaPago_ID(mCashLine.getFormaPago().getInverso());
		// Invertir el monto del pago para que sea negativo
		devolLine.setAmount(mCashLine.getAmount().negate());
		// Si no existe tipo de linea de caja poner como anticipo
		devolLine.setCashType(mCashLine.getCashType()==null?MCashLine.CASHTYPE_Anticipo:mCashLine.getCashType());
		if(devolLine==null || !devolLine.save(trxName)){
			throw new MedsysException();
		}
		return devolLine;
	}
	
	/** Facturas o remisión de la caja */
	public List<MInvoice> getFacturas(final Properties ctx, final String trxName){
		final ArrayList<MInvoice> lista = new ArrayList<MInvoice>();

		StringBuilder sql = new StringBuilder();

		// Debe considerar tambien las facturas pagadas con anticipos
		sql.append(" SELECT COALESCE(f.C_Invoice_ID, r.C_Invoice_ID)  ")
		.append(" FROM C_CashLine cl ")
		.append(" INNER JOIN C_Invoice  r ON r.C_Invoice_ID         = cl.C_Invoice_ID ")
		.append(" LEFT  JOIN C_Invoice  f ON f.Ref_Invoice_Sales_ID =  r.C_Invoice_ID ")
		.append("                        AND f.C_Invoice_ID NOT IN (SELECT Ref_Invoice_ID FROM C_Invoice WHERE Ref_Invoice_ID IS NOT NULL) ")
		.append(" WHERE cl.IsActive = 'Y' ")
		.append(" AND  cl.C_Cash_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, getC_Cash_ID());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				final MInvoice fact = new MInvoice(ctx,rset.getInt(1),trxName);
				lista.add(fact);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "MEXMEInvoice.getFromCash", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return lista;
	}
}	//	MCash
