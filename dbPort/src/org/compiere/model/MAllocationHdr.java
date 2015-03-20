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
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.ErrorList;

/**
 * Payment Allocation Model. Allocation Trigger update C_BPartner
 * 
 * @author Jorg Janke
 * @version $Id: MAllocationHdr.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
// public final class MAllocationHdr extends X_C_AllocationHdr implements
// DocAction
public class MAllocationHdr extends X_C_AllocationHdr implements DocAction {
	/** serialVersionUID */
	private static final long	serialVersionUID	= 4245775175811746661L;

	/**
	 * Get Allocations of Payment
	 * 
	 * @param ctx
	 *            context
	 * @param C_Payment_ID
	 *            payment
	 * @return allocations of payment
	 * @param trxName
	 *            transaction
	 */
	public static MAllocationHdr[] getOfPayment(Properties ctx, int C_Payment_ID, String trxName) {
		final StringBuilder sql = new StringBuilder(" SELECT * FROM C_AllocationHdr h ")
		.append(" WHERE IsActive='Y' ")
		.append(" AND EXISTS (SELECT * FROM C_AllocationLine l ")
		.append("             WHERE l.IsActive='Y' ")
		.append("             AND h.C_AllocationHdr_ID = l.C_AllocationHdr_ID ") 
		.append("             AND l.C_Payment_ID = ? ) ")
		.append(" ORDER BY h.DateTrx DESC ");
		
		final ArrayList<MAllocationHdr> list = new ArrayList<MAllocationHdr>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_Payment_ID);
			rs = pstmt.executeQuery();
			while (rs.next()){
				list.add(new MAllocationHdr(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		final MAllocationHdr[] retValue = new MAllocationHdr[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfPayment

//	/**
//	 * Get Allocations of Invoice
//	 * 
//	 * @param ctx
//	 *            context
//	 * @param C_Invoice_ID
//	 *            payment
//	 * @return allocations of payment
//	 * @param trxName
//	 *            transaction
//	 *   @deprecated         NOT USED
//	 */
//	public static MAllocationHdr[] getOfInvoice(final Properties ctx, final int C_Invoice_ID, final String trxName) {
//		final StringBuilder sql = new StringBuilder(" SELECT * FROM C_AllocationHdr h ")
//		.append(" WHERE IsActive='Y' ")
//		.append(" AND EXISTS (SELECT * FROM C_AllocationLine l ")
//		.append("             WHERE l.IsActive='Y' ")
//		.append("             AND h.C_AllocationHdr_ID = l.C_AllocationHdr_ID ")
//		.append("             AND l.C_Invoice_ID = ? ) ")
//		.append(" ORDER BY h.DateTrx DESC ");
//		
//		final ArrayList<MAllocationHdr> list = new ArrayList<MAllocationHdr>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, C_Invoice_ID);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				list.add(new MAllocationHdr(ctx, rs, trxName));
//			}
//		} catch (Exception e) {
//			s_log.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		final MAllocationHdr[] retValue = new MAllocationHdr[list.size()];
//		list.toArray(retValue);
//		return retValue;
//	} // getOfInvoice

	/** Logger */
	private static CLogger	s_log	= CLogger.getCLogger(MAllocationHdr.class);

	/**************************************************************************
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param C_AllocationHdr_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MAllocationHdr(Properties ctx, int C_AllocationHdr_ID, String trxName) {
		super(ctx, C_AllocationHdr_ID, trxName);// ok
		if (C_AllocationHdr_ID == 0) {
			// setDocumentNo (null);
			setDateTrx(Env.getCurrentDate());
			setDateAcct(getDateTrx());
			setDocAction(DOCACTION_Complete); // CO
			setDocStatus(DOCSTATUS_Drafted); // DR
			// setC_Currency_ID (0);
			setApprovalAmt(Env.ZERO);
			setIsApproved(false);
			setIsManual(false);
			//
			setPosted(false);
			setProcessed(false);
			setProcessing(false);
		}
	} // MAllocation

	/**
	 * Mandatory New Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param IsManual
	 *            manual trx
	 * @param DateTrx
	 *            date (if null today)
	 * @param C_Currency_ID
	 *            currency
	 * @param description
	 *            description
	 * @param trxName
	 *            transaction
	 */
	public MAllocationHdr(Properties ctx, boolean IsManual, Timestamp DateTrx, int C_Currency_ID, String description, String trxName) {
		this(ctx, 0, trxName);//ok
		setIsManual(IsManual);
		if (DateTrx != null) {
			setDateTrx (DateTrx);
			setDateAcct (DateTrx);
		}
		setC_Currency_ID(C_Currency_ID);
		if (description != null){
			setDescription(description);
		}
	}	//  create Allocation

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MAllocationHdr(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MAllocation

	/** Lines */
	private MAllocationLine[]	m_lines	= null;

	/**
	 * Get Lines
	 * 
	 * @param requery
	 *            if true requery
	 * @return lines
	 */// ADVERTENCIA UN ALLOCATIONHDR PUEDE TENER VARIAS LINEAS DE DIFERENTES FACTURAS DE UN MISMO PAGO
	// O VARIOS PAGOS DE UNA MISMA FACTURA
	// O Todo MEZCLADO
	public MAllocationLine[] getLines(boolean requery) {
		if (m_lines != null && m_lines.length != 0 && !requery)
			return m_lines;
		//
		String sql = "SELECT * FROM C_AllocationLine WHERE IsActive = 'Y' AND C_AllocationHdr_ID=?";
		ArrayList<MAllocationLine> list = new ArrayList<MAllocationLine>();
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getC_AllocationHdr_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MAllocationLine line = new MAllocationLine(getCtx(), rs, get_TrxName());
				line.setParent(this);
				list.add(line);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		//
		m_lines = new MAllocationLine[list.size()];
		list.toArray(m_lines);
		return m_lines;
	} // getLines
	

	/**
	 * Set Processed
	 * 
	 * @param processed
	 *            Processed
	 */
	public void setProcessed(boolean processed) {
		super.setProcessed(processed);
		if (get_ID() == 0)
			return;
		String sql = " UPDATE C_AllocationHdr SET Processed='" + (processed ? "Y" : "N") + "' WHERE C_AllocationHdr_ID=" + getC_AllocationHdr_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		m_lines = null;
		log.fine(processed + " - #" + no);
	} // setProcessed

	/**************************************************************************
	 * Before Save
	 * 
	 * @param newRecord
	 * @return save
	 */
	protected boolean beforeSave(boolean newRecord) {
		// Changed from Not to Active
		if (!newRecord && is_ValueChanged("IsActive") && isActive()) {
			log.severe("Cannot Re-Activate deactivated Allocations");
			return false;
		}

		// expert : gisela lee : asignar la org trx
		if (getAD_OrgTrx_ID() == 0) {
			MAllocationLine[] al = getLines(false);

			// si no trae lineas o es nulo
			if (al.length == 0 || al[0] == null) {
				// asignar la de logueo
				setAD_OrgTrx_ID(Env.getContextAsInt(getCtx(), "#AD_OrgTrx_ID"));
			}
			// si es una distribucion por caja
			else if (al[0].getC_CashLine_ID() != 0) {
				// guardar la org trx de la caja
				MCashLine cash = new MCashLine(getCtx(), al[0].getC_CashLine_ID(), get_TrxName());
				setAD_OrgTrx_ID(cash.getParent().getAD_OrgTrx_ID());
			}
			// si es un anticipo relacionado a factura
			else if (al[0].getC_Invoice_ID() != 0 && al[0].getC_Payment_ID() != 0) {
				// guardar la org trx de la caja
				MInvoice invoice = new MInvoice(getCtx(), al[0].getC_Invoice_ID(), get_TrxName());
				setAD_OrgTrx_ID(invoice.getAD_OrgTrx_ID());
			} else {
				// si no es ningun caso guardar el de logueo
				setAD_OrgTrx_ID(Env.getContextAsInt(getCtx(), "#AD_OrgTrx_ID"));
			}
		}
		// expert : gisela lee : asignar la org trx

		return true;
	} // beforeSave

	/**
	 * Before Delete.
	 * 
	 * @return true if acct was deleted
	 */
	protected boolean beforeDelete() {
		String trxName = get_TrxName();
		if (trxName == null || trxName.length() == 0)
			log.warning("No transaction");
		if (isPosted()) {
			if (!MPeriod.isOpen(getCtx(), getDateTrx(), MDocType.DOCBASETYPE_PaymentAllocation, getAD_Org_ID()))
			// if (!MPeriod.isOpen(getCtx(), getDateTrx(),
			// MDocType.DOCBASETYPE_PaymentAllocation))
			{
				log.warning("Period Closed");
				return false;
			}
			setPosted(false);
			if (MFactAcct.delete(Table_ID, get_ID(), trxName) < 0)
				return false;
		}
		// Mark as Inactive
		setIsActive(false); // updated DB for line delete/process
		String sql = "UPDATE C_AllocationHdr SET IsActive='N' WHERE C_AllocationHdr_ID=?";
		DB.executeUpdate(sql, getC_AllocationHdr_ID(), trxName);

		// Unlink
		getLines(true);
		HashSet<Integer> bps = new HashSet<Integer>();
		for (int i = 0; i < m_lines.length; i++) {
			MAllocationLine line = m_lines[i];
			bps.add(new Integer(line.getC_BPartner_ID()));
			if (!line.delete(true, trxName))
				return false;
		}
		
		//Se comenta por bloqueo generado en la BD el 25 Abril 2013.
		//Jesus Cantu con asesoria y apoyo de Twry perez y Mrojas a Solicitud de GC.
		//updateBP(bps);
		return true;
	} // beforeDelete

	/**
	 * After Save
	 * 
	 * @param newRecord
	 * @param success
	 * @return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		return success;
	} // afterSave

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
	} // processIt

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
		log.info(toString());
		setProcessing(false);
		return true;
	} // unlockIt

	/**
	 * Invalidate Document
	 * 
	 * @return true if success
	 */
	public boolean invalidateIt() {
		log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	} // invalidateIt

	/**
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
		// MDocType.DOCBASETYPE_PaymentAllocation))
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), MDocType.DOCBASETYPE_PaymentAllocation, getAD_Org_ID())) {
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		getLines(false);
		if (m_lines.length == 0) {
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		
		// Notas de credito
		lstCrMemo.clear();
		
		// Add up Amounts & validate
		BigDecimal approval = Env.ZERO;
		for (int i = 0; i < m_lines.length; i++) {
			MAllocationLine line = m_lines[i];
			approval = approval.add(line.getWriteOffAmt()).add(line.getDiscountAmt());
			// Make sure there is BP
			if (line.getC_BPartner_ID() == 0) {
				m_processMsg = "No Business Partner";
				return DocAction.STATUS_Invalid;
			}

			// Nota de credito por pronto pago (SOLO DEBERIA ENTRAR UNA VEZ)
			if(line.getC_Payment_ID()>0 && line.getC_Invoice_ID()>0 
					&& line.getCreditMemo().compareTo(Env.ZERO)>0 
					&& line.getPayment().isReceipt()) {
				
				final MInvoice mInvoice = new MInvoice(getCtx(), line.getC_Invoice_ID(), get_TrxName());
				if(mInvoice.isSOTrx()){
					final MInvoice notaCredito = MInvoice.createNotaCredCxC(
							mInvoice,
							getDateTrx(),
							line.getCreditMemo(),
							get_TrxName());
					m_processMsg = m_processMsg+" "+notaCredito.getDocumentNo();
					lstCrMemo.add(notaCredito);
					
					mInvoice.setIsPaid(notaCredito.getGrandTotal().compareTo( line.getCreditMemo().add(line.getAmount()) ) <= 0);
					mInvoice.save( get_TrxName());
				}
			
			}
		}
		setApprovalAmt(approval);
		//
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	} // prepareIt

	/** Listado de notas de credito */
	final List<MInvoice> lstCrMemo = new ArrayList<MInvoice>();
	
	public List<MInvoice> getLstCrMemo() {
		return lstCrMemo;
	}

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

	/**
	 * Complete Document
	 * 
	 * @return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt() {
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

		// Link
		getLines(false);
		HashSet<Integer> bps = new HashSet<Integer>();
		for (int i = 0; i < m_lines.length; i++) {
			MAllocationLine line = m_lines[i];
			bps.add(new Integer(line.processIt(false))); // not reverse
		}
		//Se comenta por bloqueo generado en la BD el 25 Abril 2013.
		//Jesus Cantu con asesoria y apoyo de Twry perez y Mrojas a Solicitud de GC.
		//updateBP(bps);

		// User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	} // completeIt

	/**
	 * Void Document. Same as Close.
	 * 
	 * @return true if success
	 */
	public boolean voidIt() {
		log.info(toString());
		boolean retValue = reverseIt();
		setDocAction(DOCACTION_None);
		return retValue;
	} // voidIt

	/**
	 * Close Document. Cancel not delivered Qunatities
	 * 
	 * @return true if success
	 */
	public boolean closeIt() {
		log.info(toString());

		setDocAction(DOCACTION_None);
		return true;
	} // closeIt

	/**
	 * Reverse Correction
	 * 
	 * @return true if success
	 */
	public boolean reverseCorrectIt() {
		log.info(toString());
		boolean retValue = reverseIt();
		setDocAction(DOCACTION_None);
		return retValue;
	} // reverseCorrectionIt

	/**
	 * Reverse Accrual - none
	 * 
	 * @return false
	 */
	public boolean reverseAccrualIt() {
		log.info(toString());
		boolean retValue = reverseIt();
		setDocAction(DOCACTION_None);
		return retValue;
	} // reverseAccrualIt

	/**
	 * Re-activate
	 * 
	 * @return false
	 */
	public boolean reActivateIt() {
		log.info(toString());
		return false;
	} // reActivateIt

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("MAllocationHdr[");
		sb.append(get_ID()).append("-").append(getSummary()).append("]");
		return sb.toString();
	} // toString

	/**
	 * Get Document Info
	 * 
	 * @return document info (untranslated)
	 */
	public String getDocumentInfo() {
		return Msg.getElement(getCtx(), "C_AllocationHdr_ID") + " " + getDocumentNo();
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
			log.severe("Could not create PDF - " + e.getMessage());
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
		// ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE,
		// getC_Invoice_ID());
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
		sb.append(": ").append(Msg.translate(getCtx(), "ApprovalAmt")).append("=").append(getApprovalAmt()).append(" (#")
				.append(getLines(false).length).append(")");
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

	/**************************************************************************
	 * Reverse Allocation. Period needs to be open
	 * 
	 * @return true if reversed
	 */
	private boolean reverseIt() {
		if (!isActive())
			throw new IllegalStateException("Allocation already reversed (not active)");

		// Can we delete posting
		// if (!MPeriod.isOpen(getCtx(), getDateTrx(),
		// MPeriodControl.DOCBASETYPE_PaymentAllocation))
		if (!MPeriod.isOpen(getCtx(), getDateTrx(), MPeriodControl.DOCBASETYPE_PaymentAllocation, getAD_Org_ID()))
			throw new IllegalStateException("@PeriodClosed@");

		// Set Inactive
		setIsActive(false);
		setDocumentNo(getDocumentNo() + "^");
		setDocStatus(DOCSTATUS_Reversed); // for direct calls
		if (!save() || isActive())
			throw new IllegalStateException("Cannot de-activate allocation");

		// Delete Posting
		String sql = "DELETE FROM Fact_Acct WHERE AD_Table_ID=" + MAllocationHdr.Table_ID + " AND Record_ID=" + getC_AllocationHdr_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		log.fine("Fact_Acct deleted #" + no);

		// Unlink Invoices
		getLines(true);
		HashSet<Integer> bps = new HashSet<Integer>();
		for (int i = 0; i < m_lines.length; i++) {
			MAllocationLine line = m_lines[i];
			line.setIsActive(false);
			line.save();
			bps.add(new Integer(line.processIt(true))); // reverse
		}
		
		//Se comenta por bloqueo generado en la BD el 25 Abril 2013.
		//Jesus Cantu con asesoria y apoyo de Twry perez y Mrojas a Solicitud de GC.
		//updateBP(bps);
		return true;
	} // reverse

	/**
	 * Crea el registro en C_AllocationLine relacionando el C_Payment_ID y C_Invoice_ID
	 * @param cashline
	 * @param invoice
	 * @return
	 */
	public MAllocationLine createAllocationLine(MPayment payment, final MInvoice invoice) {
		final MAllocationLine line = new MAllocationLine(this);
		line.setC_BPartner_ID(invoice.getC_BPartner_ID());
		line.setC_Payment_ID(payment.getC_Payment_ID());
		line.setC_Invoice_ID(invoice.getC_Invoice_ID());
		line.setAmount(payment.getPayAmt());
		if (!line.save()) {
			throw new MedsysException();
		}
		return line;
	}
	
	/**
	 * Crea el registro en C_AllocationLine relacionando el C_Cashline_ID y C_Invoice_ID
	 * @param cashline
	 * @param invoice
	 * @return
	 */
	public MAllocationLine createAllocationLine(MCashLine cashline, final MInvoice invoice) {
		final MAllocationLine line = new MAllocationLine(this);
		if (cashline.getC_Payment_ID() > 0) {//RQM #5252
			line.setC_Payment_ID(cashline.getC_Payment_ID());
		} else {	
			line.setC_CashLine_ID(cashline.getC_CashLine_ID());
		}
		line.setC_BPartner_ID(invoice.getC_BPartner_ID());
		line.setC_Invoice_ID(invoice.getC_Invoice_ID());
		line.setAmount(cashline.getAmount());
		if (!line.save()) {
			throw new MedsysException();
		}
		return line;
	}
	
	public MAllocationLine createAllocationLine(Properties ctx, MPayment payment, final MInvoice mInvoice, String trxName) {
		MAllocationLine aLine;
		if (payment.isReceipt()) {
			aLine = new MAllocationLine(this, Env.ZERO.compareTo(payment.getPayAmt()) == 0 ? payment.getPayAmt() : payment.getChargeAmt(),
					payment.getDiscountAmt(), payment.getWriteOffAmt(), payment.getOverUnderAmt());
			aLine.setAmount(payment.getPayAmt());
		} else {
			aLine = new MAllocationLine(this, payment.getPayAmt().compareTo(Env.ZERO) == 0 ? payment.getPayAmt().negate() : payment.getChargeAmt()
					.negate(), payment.getDiscountAmt().negate(), payment.getWriteOffAmt().negate(), payment.getOverUnderAmt().negate());
			aLine.setAmount(payment.getPayAmt().negate());
		}

		aLine.setDocInfo(payment.getC_BPartner_ID(), 0, mInvoice.getC_Invoice_ID());
		aLine.setPaymentInfo(payment.getC_Payment_ID(), 0);
		aLine.setC_Invoice_ID(mInvoice.getC_Invoice_ID());
		//
		aLine.setC_Charge_ID(payment.getC_Charge_ID());
		if (!aLine.save(trxName)) {
			throw new MedsysException();
		}

		aLine.processIt(false);
		return aLine;
	}
	
	
	/**
	 * Generamos los registros de C_AllocationHdr y C_AllocationLine
	 * 
	 * @param ctaPac
	 *            Encounter
	 * @param payment
	 *            Payment
	 * @return error
	 * 			  True or False depending if an error ocurred
	 * @throws Exception
	 */
	public static boolean generarAllocation(Properties ctx, MEXMECtaPac ctaPac, MInvoice invoice,
			MPayment payment, List<Double> entryDist, int adjType, boolean changes, String trxName) {
		boolean error = Boolean.FALSE;
		
		MAllocationHdr allocH = new MAllocationHdr(ctx
				, false
				, Env.getCurrentDate()
				, ctaPac.getC_Currency_ID() 
				, Msg.translate(ctx,	"Payment ID") + ": " + payment.getDocumentNo() + " [e]"
				, trxName);
		allocH.setAD_Org_ID(payment.getAD_Org_ID());
		allocH.setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
		if (allocH.save(trxName)){
			s_log.info("Saved AllocationHdr C_AllocationHdr = "+allocH.getC_AllocationHdr_ID());
		}else{
			s_log.warning("Not Saved AllocationHdr DocumentNO = "+allocH.getDocumentNo());
			error = Boolean.TRUE;
		}
		
		

		// AllocationLine for the amount that was not in the distribution
		if (!error) {
			for (int d = Constantes.PAYMENT; d <= Constantes.OTHER && !error; d++) {
				if (changes || d == Constantes.PAYMENT) {
					if (entryDist.get(d) > 0) {
						BigDecimal amount = new BigDecimal(entryDist.get(d));
						if (adjType > 0) {
							amount = payment.getPayAmt();
						}
						
						// AllocationLine for the amount showed in the distribution
						MAllocationLine aLine = new MAllocationLine(allocH, amount
						, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
		
						aLine.setDocInfo(ctaPac.getC_BPartner_ID(), 0, invoice == null 
								? 0 : invoice.getC_Invoice_ID());
						aLine.setPaymentInfo(payment.getC_Payment_ID(), 0);
						String typeAdj = null;
						if (d == Constantes.PAYMENT) {
							typeAdj = MCharge.TYPE_Payment;
						} else if (d == Constantes.COPAY) {
							typeAdj = MCharge.TYPE_CopayPayment;
						} else if (d == Constantes.DEDUCTIBLE) {
							typeAdj = MCharge.TYPE_DeductiblePayment;
						} else if (d == Constantes.COINSURANCE) {
							typeAdj = MCharge.TYPE_CoinsurancePayment;
						} else if (d == Constantes.OTHER) {
							typeAdj = MCharge.TYPE_OthersPayment;
						}
						if (adjType > 0) {
							aLine.setC_Charge_ID(adjType);
						} else {
							MCharge adjPayment = MEXMECharge.getAdjustmentByType(ctx, typeAdj, null);
							if (adjPayment != null) {
								aLine.setC_Charge_ID(adjPayment.getC_Charge_ID());
							}
						}
						
						aLine.setC_Invoice_ID(invoice.getC_Invoice_ID());
						if (aLine.save(trxName)) {
							s_log.info("Saved AllocationLine C_AllocationLine = "+aLine.getC_AllocationLine_ID());
						} else {
							s_log.warning("Not Saved AllocationLine Type = "+ 
									MRefList.getListName(ctx, MCharge.TYPE_AD_Reference_ID, typeAdj));
							error = Boolean.TRUE;
						}
					}
				}
			}
		}
		return !error;
	}
	
	public static void allocateInvoicePayment(final MInvoice invoice, final MPayment payment) {
		final MAllocationHdr hdr =
			new MAllocationHdr(payment.getCtx()
					, false
					, Env.getCurrentDate() //payment.getDateTrx() (Tomar la fecha actual - Lama)
					, payment.getC_Currency_ID()
					, payment.getDescription()==null?Utilerias.getLabel("msj.checkRefund")+" [d]":payment.getDescription()+" [d]"
					, payment.get_TrxName());
		if (hdr.save()) {
			final MAllocationLine line = hdr.createAllocationLine(payment, invoice);
//			final MAllocationLine line = new MAllocationLine(hdr);
//			line.setC_Payment_ID(payment.getC_Payment_ID());
//			line.setC_BPartner_ID(payment.getC_BPartner_ID());
//			line.setC_Invoice_ID(invoice.getC_Invoice_ID());
			line.setAmount(payment.getAmount());
			if (!line.save()) {
				throw new MedsysException();
			}
			hdr.completeInvoice(true);
//			hdr.setDocStatus(hdr.completeIt());// Completar
//			if (!hdr.save()) {
//				throw new MedsysException();
//			}
		} else {
			throw new MedsysException();
		}
	}
	
	public static List<MAllocationHdr> getHeaders(final Properties ctx, final MInvoice invoice, final String trxName){
		List<MAllocationHdr> ret = new ArrayList<MAllocationHdr>();
		 PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        StringBuilder sql = new StringBuilder();
	        try {	        		        	
	        	sql.append(" SELECT * FROM C_ALLOCATIONHDR WHERE C_ALLOCATIONHDR_ID IN ( ");
	        	sql.append(" SELECT C_ALLOCATIONHDR_ID  ");
	        	sql.append(" FROM C_ALLOCATIONLINE  ");
	        	sql.append(" WHERE C_INVOICE_ID = ? ");
	        	sql.append(" GROUP BY C_ALLOCATIONHDR_ID ");
	        	sql.append(" ) ");
	        	
	            pstmt = DB.prepareStatement(sql.toString(), trxName);	            
	            pstmt.setInt(1, invoice.getC_Invoice_ID());
	            
				rs = pstmt.executeQuery();
				MAllocationHdr aux = null;
				while (rs.next()) {
				    aux = new MAllocationHdr(ctx, rs, trxName);
				    ret.add(aux);
				}				
	        } catch (Exception e) {
	        	s_log.log(Level.SEVERE, "getHeaders()", e);	        	
	        } finally {
	        	DB.close(rs, pstmt);
	        }
		return ret;
	}
	
	
	/**
	 * Reverse pre-payment
	 * @return true if success
	 */
	public MAllocationHdr reversePrePayment() {
		log.info(toString());
		MAllocationHdr allocHdrNota = null;

		getLines(true);
		for (int i = 0; i < m_lines.length; i++) {

			// Crear la asignacion de la devolucion a la nota de credito
			if(allocHdrNota==null){
				allocHdrNota = new MAllocationHdr(getCtx(), false
						, Env.getCurrentDate()
						, getC_Currency_ID()
						, Msg.translate(getCtx(), "Reverse")+" "+toString()+" [g]"
						, get_TrxName());

				if(allocHdrNota==null || !allocHdrNota.save(get_TrxName())) {
					allocHdrNota=null;
					break;
				}	
			}

			final MAllocationLine cAllocLine = new MAllocationLine (
					allocHdrNota 
					, m_lines[i].getAmount().negate()
					, m_lines[i].getDiscountAmt().negate()
					, m_lines[i].getWriteOffAmt().negate()
					, m_lines[i].getOverUnderAmt().negate());
			cAllocLine.set_TrxName(get_TrxName());
			cAllocLine.setDocInfo(m_lines[i].getC_BPartner_ID(), 0, m_lines[i].getC_Invoice_ID());//remision o factura por que es cancelacion de anticipo
			cAllocLine.setPaymentInfo(m_lines[i].getC_Payment_ID(), m_lines[i].getC_CashLine_ID());// Cashline original por que es cancelacion de anticipo
			cAllocLine.setAmtAcct(m_lines[i].getAmtAcct());
			cAllocLine.setOverUnderAmt(Env.ZERO);
			if(!cAllocLine.save(get_TrxName())){
				allocHdrNota=null;
				break;
			}
		}
		return allocHdrNota;
	} // reverseCorrectionIt
	
	/**
	 * Obtenemos la distribuciones del pago para la factura especificada
	 * @param ctx El contexto de la aplicacion
	 * @param cInvoiceID El identificador de la factura
	 * @param trxName El nombre de la transaccion
	 * @return Un objeto de tipo MEXMEAllocationLine
	 */
	public static List<MAllocationHdr> getAllocationHrdInv(final Properties ctx, final int cInvoiceID, final boolean isPrePayment, final String trxName) {
		final List<MAllocationHdr> lista = new ArrayList<MAllocationHdr>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final StringBuilder sql = new StringBuilder();
		try {
			
			final String nivelAccess = MEXMELookupInfo.addAccessLevelSQL(ctx, "", "C_AllocationLine", "aLine");
	        
			sql.append(" SELECT DISTINCT ( aLine.C_AllocationHdr_ID ) AS C_AllocationHdr_ID ")
			.append(" FROM C_AllocationLine aLine ") 
			.append(isPrePayment?" INNER JOIN C_Payment cPay ON aLine.C_Payment_ID = cPay.C_Payment_ID ":"")
			.append(" WHERE aLine.IsActive = 'Y' ")
			.append(nivelAccess==null?"":nivelAccess)
			.append(" AND   aLine.C_Invoice_ID  = ? ")
			.append(" AND   aLine.C_Payment_ID IS NOT NULL ");
			if(isPrePayment){
				sql.append(" AND cPay.IsActive = 'Y' ")
				.append("    AND cPay.Type='A'       ")
				.append("    AND cPay.EXME_CtaPac_ID IS NOT NULL ");
			}
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			
			pstmt.setInt(1,cInvoiceID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(new MAllocationHdr(ctx, rs.getInt("C_AllocationHdr_ID"), trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return lista;
	}

	//CARD #1299
	/** Constructor a partir de una factura */
	public MAllocationHdr(Properties ctx, MInvoice mInvoice, String trxName) {
		this(ctx, false, Env.getCurrentDate()
				, mInvoice.getC_Currency_ID()
				, Utilerias.getLabel("msg.Cancelacion") + " - [" + mInvoice.getDocumentNo() + "] [f]"
				, trxName);
	}	//  create Allocation
	
	/** Crear asignacion negativa a partir de otra positiva */
	public MAllocationLine copyLineReverse(final MAllocationLine cALine){
		final MAllocationLine cAllocLine = new MAllocationLine (
				this 
				, cALine.getAmount().negate()
				, cALine.getDiscountAmt().negate()
				, cALine.getWriteOffAmt().negate()
				, cALine.getOverUnderAmt().negate());
		cAllocLine.set_TrxName(get_TrxName());
		cAllocLine.setDocInfo(cALine.getC_BPartner_ID(), 0, cALine.getC_Invoice_ID());//remision o factura por que es cancelacion de anticipo
		cAllocLine.setPaymentInfo(cALine.getC_Payment_ID(), cALine.getC_CashLine_ID());// Cashline original por que es cancelacion de anticipo
		cAllocLine.setAmtAcct(cALine.getAmtAcct());
		cAllocLine.setOverUnderAmt(Env.ZERO);
		if(!cAllocLine.save(get_TrxName())){
			throw new MedsysException();
		}
		return cAllocLine;
	}
	
	/** Crear asignacion de la linea de caja a la factura */
	public MAllocationLine addAllocationLine(final MCashLine mCashLine,final MPayment mPayment,final MInvoice mInvoice, final String trxName){
		final MAllocationLine allocLine = new MAllocationLine (
				this 
				, mPayment==null?mCashLine.getAmount():mPayment.getPayAmt() 
				, Env.ZERO
				, Env.ZERO
				, Env.ZERO);
		allocLine.set_TrxName(trxName);
		allocLine.setDocInfo(mInvoice.getC_BPartner_ID(), 0, mInvoice.getC_Invoice_ID());
		allocLine.setPaymentInfo(
				  mPayment ==null?0:mPayment.getC_Payment_ID()
				, mCashLine==null?0:mCashLine.getC_CashLine_ID());
		allocLine.setOverUnderAmt(Env.ZERO);
		
		if(!allocLine.save(trxName)){
			throw new MedsysException();
		}
		return allocLine;
	}
	
	/** Completa el documento de la asignación */
	public ErrorList completeInvoice(boolean isSendException){
		final ErrorList errorList = new ErrorList();
		startWorkflow(errorList, DocAction.ACTION_Complete, DOCSTATUS_Completed, get_TrxName());
		if(errorList.isEmpty()){
			log.severe("C_AllocationHdr : " + getDocumentInfo() + " : Completed ");
			
		} else {
			log.severe("C_AllocationHdr : " + getDocumentInfo() + " Not Completed : " + errorList.toString());
			if(isSendException){
				throw new MedsysException(errorList.toString());
			}
		}
		return errorList;
	}
	
	public void resetDataAfterCopyValues(){
		setDateTrx(Env.getCurrentDate());
		setDateAcct(getDateTrx());
		setDocAction(DOCACTION_Complete); // CO
		setDocStatus(DOCSTATUS_Drafted); // DR
		// setC_Currency_ID (0);
		setApprovalAmt(Env.ZERO);
		setIsApproved(false);
		setIsManual(false);
		//
		setPosted(false);
		setProcessed(false);
		setProcessing(false);
	}
	
	
	/**
	 * Get Lines
	 * 
	 * @param requery
	 *            if true requery
	 * @return lines
	 */
	public List<MAllocationLine> getLinesByInvoice(boolean requery, final int invoiceId) {
		//
		final StringBuilder sql = new StringBuilder()
			.append(" SELECT * ")
			.append(" FROM C_AllocationLine ")
			.append(" WHERE IsActive = 'Y' ")
			.append(" AND C_AllocationHdr_ID = ? " )
			.append(" AND C_Invoice_ID = ? ");
		
		List<MAllocationLine> list = new ArrayList<MAllocationLine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getC_AllocationHdr_ID());
			pstmt.setInt(2, invoiceId);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MAllocationLine line = new MAllocationLine(getCtx(), rs, get_TrxName());
				line.setParent(this);
				list.add(line);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		//
		return list;
	} // getLines
	
	/**
	 * Crear la linea de la asignación
	 * @param errorList
	 * @param bean
	 * @param mPayment
	 * @param allocationDate
	 * @param apply
	 * @param bad
	 * @param writeOff
	 * @param rate
	 * @param trxName
	 * @return
	 */
	public static MAllocationHdr createAllocationLine(ErrorList errorList, final MInvoice mInvoice,
			final MPayment mPayment, final Timestamp allocationDate, final BigDecimal apply, final BigDecimal bad,
			final boolean saldada, final BigDecimal writeOff, final BigDecimal rate, final String trxName) {

		final MAllocationHdr allow = new MAllocationHdr(mPayment.getCtx()
				, true
				, allocationDate
				, mPayment.getC_Currency_ID()
				, mPayment.getDescription()==null?Utilerias.getLabel("msj.ingresos")+" [acf]":mPayment.getDescription()+" [acf]"
						, trxName);
		if (!allow.save()) {
			throw new MedsysException();
		}
		// Allocation Line
		final MAllocationLine allocLine = new MAllocationLine (
				allow 
				, apply
				, Env.ZERO
				, bad
				, writeOff);
		
		allocLine.set_TrxName(trxName);
		allocLine.setDocInfo(mInvoice.getC_BPartner_ID(), 0, mInvoice.getC_Invoice_ID());
		allocLine.setPaymentInfo(mPayment.getC_Payment_ID(),0);
		allocLine.setRate(rate);
		if (!allocLine.save()) {
			throw new MedsysException();
		}
		allow.completeInvoice(true);
		
		// No hay descuentos
		allocLine.setDiscountAmt(Env.ZERO);
		// Montos en cero
		if(saldada){
			allocLine.setOverUnderAmt (Env.ZERO);
		}
		allocLine.save();
		return allow;
	}
	
} // MAllocation
