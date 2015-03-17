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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;
import org.joda.time.DateTime;

import com.ecaresoft.util.EcsException;

/**
 *  GL Journal Model
 *
 *	@author Jorg Janke
 *	@version $Id: MJournal.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MJournal extends X_GL_Journal implements DocAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**	Logger			*/
	private static CLogger s_log = CLogger.getCLogger(MJournal.class);

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param GL_Journal_ID id
	 *	@param trxName transaction
	 */
	public MJournal (Properties ctx, int GL_Journal_ID, String trxName)
	{
		super (ctx, GL_Journal_ID, trxName);
		if (GL_Journal_ID == 0)
		{
		//	setGL_Journal_ID (0);		//	PK
		//	setC_AcctSchema_ID (0);
		//	setC_Currency_ID (0);
		//	setC_DocType_ID (0);
		//	setC_Period_ID (0);
			//
			setCurrencyRate (Env.ONE);
		//	setC_ConversionType_ID(0);
			setDateAcct (Env.getCurrentDate());
			setDateDoc (Env.getCurrentDate());
		//	setDescription (null);
			setDocAction (DOCACTION_Complete);
			setDocStatus (DOCSTATUS_Drafted);
		//	setDocumentNo (null);
		//	setGL_Category_ID (0);
			setPostingType (POSTINGTYPE_Actual);
			setTotalCr (Env.ZERO);
			setTotalDr (Env.ZERO);
			setIsApproved (false);
			setIsPrinted (false);
			setPosted (false);
			setProcessed(false);
		}
	}	//	MJournal

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MJournal (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MJournal

	/**
	 * 	Parent Constructor.
	 *	@param parent batch
	 */
	public MJournal (MJournalBatch parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setGL_JournalBatch_ID(parent.getGL_JournalBatch_ID());
		setC_DocType_ID(parent.getC_DocType_ID());
		setPostingType(parent.getPostingType());
		//
		setDateDoc(parent.getDateDoc());
		setC_Period_ID(parent.getC_Period_ID());
		setDateAcct(parent.getDateAcct());
		setC_Currency_ID(parent.getC_Currency_ID());
	}	//	MJournal
	
	/**
	 * 	Copy Constructor.
	 * 	Dos not copy: Dates/Period
	 *	@param original original
	 */
	public MJournal (MJournal original)
	{
		this (original.getCtx(), 0, original.get_TrxName());
		setClientOrg(original);
		setGL_JournalBatch_ID(original.getGL_JournalBatch_ID());
		//
		setC_AcctSchema_ID(original.getC_AcctSchema_ID());
		setGL_Budget_ID(original.getGL_Budget_ID());
		setGL_Category_ID(original.getGL_Category_ID());
		setPostingType(original.getPostingType());
		setDescription(original.getDescription());
		setC_DocType_ID(original.getC_DocType_ID());
		setControlAmt(original.getControlAmt());
		//
		setC_Currency_ID(original.getC_Currency_ID());
		setC_ConversionType_ID(original.getC_ConversionType_ID());
		setCurrencyRate(original.getCurrencyRate());
		
	//	setDateDoc(original.getDateDoc());
	//	setDateAcct(original.getDateAcct());
	//	setC_Period_ID(original.getC_Period_ID());
	}	//	MJournal
	
	
	/**
	 * 	Overwrite Client/Org if required
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID org
	 */
	public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
	{
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	}	//	setClientOrg

	/**
	 * 	Set Accounting Date.
	 * 	Set also Period if not set earlier
	 *	@param DateAcct date
	 */
	public void setDateAcct (Timestamp DateAcct)
	{
		super.setDateAcct(DateAcct);
		if (DateAcct == null)
			return;
		if (getC_Period_ID() != 0)
			return;
		int C_Period_ID = MPeriod.getC_Period_ID(getCtx(), DateAcct, getAD_Org_ID());
		if (C_Period_ID == 0)
			log.warning("setDateAcct - Period not found");
		else
			setC_Period_ID(C_Period_ID);
	}	//	setDateAcct

	/**
	 * 	Set Currency Info
	 *	@param C_Currency_ID currenct
	 *	@param C_ConversionType_ID type
	 *	@param CurrencyRate rate
	 */
	public void setCurrency (int C_Currency_ID, int C_ConversionType_ID, BigDecimal CurrencyRate)
	{
		if (C_Currency_ID != 0)
			setC_Currency_ID(C_Currency_ID);
		if (C_ConversionType_ID != 0)
			setC_ConversionType_ID(C_ConversionType_ID);
		if (CurrencyRate != null && CurrencyRate.compareTo(Env.ZERO) == 0)
			setCurrencyRate(CurrencyRate);
	}	//	setCurrency

	
	/**************************************************************************
	 * 	Get Journal Lines
	 * 	@param requery requery
	 *	@return Array of lines
	 */
	public MJournalLine[] getLines (boolean requery)
	{
		ArrayList<MJournalLine> list = new ArrayList<MJournalLine>();
		String sql = "SELECT * FROM GL_JournalLine WHERE GL_Journal_ID=? ORDER BY Line";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getGL_Journal_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MJournalLine (getCtx(), rs, get_TrxName()));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, "getLines", ex); 
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;
		//
		MJournalLine[] retValue = new MJournalLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getLines

	/**
	 * 	Copy Lines from other Journal
	 *	@param fromJournal Journal
	 *	@param dateAcct date used - if null original
	 *	@param typeCR type of copying (C)orrect=negate - (R)everse=flip dr/cr - otherwise just copy
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MJournal fromJournal, Timestamp dateAcct, char typeCR)
	{
		if (isProcessed() || fromJournal == null)
			return 0;
		int count = 0;
		MJournalLine[] fromLines = fromJournal.getLines(false);
		for (int i = 0; i < fromLines.length; i++)
		{
			MJournalLine toLine = new MJournalLine (getCtx(), 0, fromJournal.get_TrxName());
			PO.copyValues(fromLines[i], toLine, getAD_Client_ID(), getAD_Org_ID());
			toLine.setGL_Journal_ID(getGL_Journal_ID());
			//
			if (dateAcct != null)
				toLine.setDateAcct(dateAcct);
			//	Amounts
			if (typeCR == 'C')			//	correct
			{
				toLine.setAmtSourceDr(fromLines[i].getAmtSourceDr().negate());
				toLine.setAmtSourceCr(fromLines[i].getAmtSourceCr().negate());
				
				toLine.setAmtAcctDr(fromLines[i].getAmtAcctDr().negate());
				toLine.setAmtAcctCr(fromLines[i].getAmtSourceCr().negate());
			}
			else if (typeCR == 'R')		//	reverse
			{
				toLine.setAmtSourceDr(fromLines[i].getAmtSourceCr());
				toLine.setAmtSourceCr(fromLines[i].getAmtSourceDr());
				
				toLine.setAmtAcctDr(fromLines[i].getAmtAcctCr());
				toLine.setAmtAcctCr(fromLines[i].getAmtAcctDr());
			}
			toLine.setIsGenerated(true);
			toLine.setProcessed(false);
			if (toLine.save())
				count++;
		}
		if (fromLines.length != count)
			log.log(Level.SEVERE, "copyLinesFrom - Line difference - JournalLines=" + fromLines.length + " <> Saved=" + count);

		return count;
	}	//	copyLinesFrom

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
		String sql = "UPDATE GL_JournalLine SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE GL_Journal_ID=" + getGL_Journal_ID();
		int noLine = DB.executeUpdate(sql, get_TrxName());
		log.fine(processed + " - Lines=" + noLine);
	}	//	setProcessed

	/**
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true/false
	 */
	protected boolean beforeSave(boolean newRecord) {
		boolean retValue = true;

		// Imported Journals may not have date
		if (getDateDoc() == null) {
			if (getDateAcct() == null) {
				setDateDoc(Env.getCurrentDate());
			} else {
				setDateDoc(getDateAcct());
			}
		}

		if (getDateAcct() == null) {
			setDateAcct(getDateDoc());
		}
		
		if(getC_Period_ID() > 0){
			MPeriod period = new MPeriod(getCtx(), getC_Period_ID(), null);
			
			int year = Integer.valueOf(period.getC_Year().getYear());
			int month = period.getPeriodNo();
			
			DateTime dateTime = new DateTime(getDateAcct());
			
			if (!(dateTime.getYear() == year && dateTime.getMonthOfYear() == month)) {
				log.saveError(null, Utilerias.getAppMsg(getCtx(), "msj.fechaFueraPeriodo"));
				return false;
			}
		}

		if (!newRecord && is_ValueChanged(COLUMNNAME_GL_Category_ID)) {
			MEXMEPolicyInfo policyInfo = MEXMEPolicyInfo.get(getCtx(), Table_ID, getGL_Journal_ID(), 0, null);

			I_GL_Category glCategory = getGL_Category();

			String auxType = policyInfo.getAuxType();

			if (MEXMEPolicyInfo.AUXTYPE_Invoice.equals(policyInfo.getAuxType())) {
				policyInfo.setAuxType(MEXMEPolicyInfo.AUXTYPE_Check);
			} else if (X_GL_Category.POLICYTYPE_Journal.equals(glCategory.getPolicyType())) {
				policyInfo.setAuxType(MEXMEPolicyInfo.AUXTYPE_Invoice);
			}

			if (!StringUtils.equals(auxType, policyInfo.getAuxType())) {
				policyInfo.setName(null);
				policyInfo.setC_BPartner_ID(0);
				policyInfo.setUUID(null);
				policyInfo.setAmount(BigDecimal.ZERO);
				policyInfo.setC_BankAccount_ID(0);
				policyInfo.setC_BankAccountDest_ID(0);
				policyInfo.setDocNo(null);
			}

			policyInfo.setPolicyType(glCategory.getPolicyType());

			retValue = policyInfo.save(get_TrxName());
		}

		return retValue;
	} // beforeSave
	
	
	/**
	 * 	After Save.
	 * 	Update Batch Total
	 *	@param newRecord true if new record
	 *	@param success true if success
	 *	@return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		if (!success) {
			return success;
		}
		
		if (success && newRecord) {
			MEXMEPolicyInfo policyInfo = new MEXMEPolicyInfo(getCtx(), 0, null);
			policyInfo.setAD_Table_ID(Table_ID);
			policyInfo.setRecord_ID(getGL_Journal_ID());
			policyInfo.setDateTrx(getDateAcct());
			I_GL_Category glCategory = getGL_Category();

			if (X_GL_Category.POLICYTYPE_Journal.equals(glCategory.getPolicyType())) {
				policyInfo.setAuxType(MEXMEPolicyInfo.AUXTYPE_Invoice);
			} else {
				policyInfo.setAuxType(MEXMEPolicyInfo.AUXTYPE_Check);
			}

			policyInfo.setGL_Category_ID(glCategory.getGL_Category_ID());
			policyInfo.setPolicyType(glCategory.getPolicyType());

			if (!policyInfo.save(get_TrxName())) {
				return false;
			}
		}
		
		// Si tuvo exito, no es nuevo y cambio la columna currencyRate
		if (success && !newRecord && is_ValueChanged(COLUMNNAME_CurrencyRate)) {
			for (MJournalLine line : getLines(true)) {
				// Actualizar el rate en los detalles
				line.setCurrencyRate(getCurrencyRate());

				// Recalcular nuevamente
				line.setAmtAcctCr(getCurrencyRate().multiply(line.getAmtSourceCr()));
				line.setAmtAcctDr(getCurrencyRate().multiply(line.getAmtSourceDr()));

				if (!line.save(get_TrxName())) {
					return false;
				}
			}
		}
		
		if (success && !newRecord && is_ValueChanged(COLUMNNAME_DateAcct)) {
			for (MJournalLine journalLine : getLines(true)) {
				journalLine.setDateAcct(getDateAcct());
				if (!journalLine.save(get_TrxName())) {
					return false;
				}
			}
		}

		return updateBatch();
	} // afterSave
	
	/**
	 * 	After Delete
	 *	@param success true if deleted
	 *	@return true if success
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;
		return updateBatch();
	}	//	afterDelete
	
	/**
	 * 	Update Batch total
	 *	@return true if ok
	 */
	private boolean updateBatch()
	{
		String sql = "UPDATE GL_JournalBatch jb"
			+ " SET (TotalDr, TotalCr) = (SELECT COALESCE(SUM(TotalDr),0), COALESCE(SUM(TotalCr),0)"
				+ " FROM GL_Journal j WHERE j.IsActive='Y' AND jb.GL_JournalBatch_ID=j.GL_JournalBatch_ID) "
			+ "WHERE GL_JournalBatch_ID=" + getGL_JournalBatch_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1)
			log.warning("afterSave - Update Batch #" + no);
		return true;//no == 1;
	}	//	updateBatch
	
	
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
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		//	Get Period
		MPeriod period = MPeriod.get (getCtx(), getDateAcct(), getAD_Org_ID());
		if (period == null)
		{
			log.warning("No Period for " + getDateAcct());
			m_processMsg = "@PeriodNotFound@";
			return DocAction.STATUS_Invalid;
		}
		//	Standard Period
		if (period.getC_Period_ID() != getC_Period_ID()
			&& period.isStandardPeriod())
		{
			m_processMsg = "@PeriodNotValid@";
			return DocAction.STATUS_Invalid;
		}
		boolean open = period.isOpen(dt.getDocBaseType(), getDateAcct());
		if (!open)
		{
			log.warning(period.getName()
				+ ": Not open for " + dt.getDocBaseType() + " (" + getDateAcct() + ")");
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		//	Lines
		MJournalLine[] lines = getLines(true);
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Add up Amounts
		BigDecimal AmtSourceDr = Env.ZERO;
		BigDecimal AmtSourceCr = Env.ZERO;
		for (int i = 0; i < lines.length; i++)
		{
			MJournalLine line = lines[i];
			if (!isActive())
				continue;
			//
			if (line.isDocControlled())
			{
				m_processMsg = "@DocControlledError@ - @Line@=" + line.getLine()
					+ " - " + line.getAccountElementValue();
				return DocAction.STATUS_Invalid;
			}
			//
//			AmtSourceDr = AmtSourceDr.add(line.getAmtSourceDr());
//			AmtSourceCr = AmtSourceCr.add(line.getAmtSourceCr());
			AmtSourceDr = AmtSourceDr.add(line.getAmtAcctDr());//card 1195 Es el monto en moneda del cliente
			AmtSourceCr = AmtSourceCr.add(line.getAmtAcctCr());//card 1195 Es el monto en moneda del cliente
		}
		setTotalDr(AmtSourceDr);
		setTotalCr(AmtSourceCr);

		//	Control Amount
		if (Env.ZERO.compareTo(getControlAmt()) != 0
			&& getControlAmt().compareTo(getTotalDr()) != 0)
		{
			m_processMsg = "@ControlAmtError@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Unbalanced Jornal & Not Suspense
		if (AmtSourceDr.compareTo(AmtSourceCr) != 0)
		{
			MAcctSchemaGL gl = MAcctSchemaGL.get(getCtx(), getC_AcctSchema_ID());
			if (gl == null || !gl.isUseSuspenseBalancing())
			{
				m_processMsg = "@UnbalancedJornal@";
				return DocAction.STATUS_Invalid;
			}
		}
		
		if (!DOCACTION_Complete.equals(getDocAction())) 
			setDocAction(DOCACTION_Complete);
		m_justPrepared = true;
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
	public String completeIt()
	{
		
		if (getTotalCr().compareTo(getTotalDr()) != 0) {
			throw new EcsException(Utilerias.getAppMsg(getCtx(), "msg.acc.balance"));
		}
		
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
		log.info(toString());
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
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info(toString());
		if (DOCSTATUS_Drafted.equals(getDocStatus()) 
			|| DOCSTATUS_Invalid.equals(getDocStatus()))
		{
			setProcessed(true);
			setDocAction(DOCACTION_None);
			setDocStatus(DOCSTATUS_Voided);
			return true;
		}
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
		if (DOCSTATUS_Completed.equals(getDocStatus())) 
		{
			setProcessed(true);
			setDocAction(DOCACTION_None);
			return true;
		}
		return false;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction (in same batch).
	 * 	As if nothing happened - same date
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		// La reversa corrección debe hacer lo que hace la reversa acctual GC 04/03/15
		//return reverseCorrectIt(getGL_JournalBatch_ID()) != null;
		return reverseAccrualIt();
	}	//	reverseCorrectIt

	/**
	 * 	Reverse Correction.
	 * 	As if nothing happened - same date
	 * 	@param GL_JournalBatch_ID reversal batch
	 * 	@return reversed Journal or null
	 */
	public MJournal reverseCorrectIt (int GL_JournalBatch_ID)
	{
		log.info(toString());
		//	Journal
		MJournal reverse = new MJournal (this);
		reverse.setGL_JournalBatch_ID(GL_JournalBatch_ID);
		reverse.setDateDoc(getDateDoc());
		reverse.setC_Period_ID(getC_Period_ID());
		reverse.setDateAcct(getDateAcct());
		//	Reverse indicator
		String description = reverse.getDescription();
		if (description == null)
			description = "** " + getDocumentNo() + " **";
		else
			description += " ** " + getDocumentNo() + " **";
		reverse.setDescription(description);
		if (!reverse.save())
			return null;
		
		//	Lines
		reverse.copyLinesFrom(this, null, 'C');
		//
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return reverse;
	}	//	reverseCorrectiokindario
	
	/**
	 * 	Reverse Accrual (sane batch).
	 * 	Flip Dr/Cr - Use Today's date
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		return reverseAccrualIt (getGL_JournalBatch_ID()) != null;
	}	//	reverseAccrualIt
	
	/**
	 * 	Reverse Accrual.
	 * 	Flip Dr/Cr - Use Today's date
	 * 	@param GL_JournalBatch_ID reversal batch
	 * 	@return reversed journal or null 
	 */
	public MJournal reverseAccrualIt (int GL_JournalBatch_ID)
	{
		log.info(toString());
		//	Journal
		MJournal reverse = new MJournal (this);
		reverse.setGL_JournalBatch_ID(GL_JournalBatch_ID);
		MPeriod period = MPeriod.get(getCtx(), Env.getCurrentDate(), Env.getAD_Org_ID(getCtx()));
		
		if(period == null){
			throw new IllegalArgumentException (Utilerias.getAppMsg(getCtx(), "error.periodoCerrado"));
		}
		
		reverse.setC_Period_ID(period.getC_Period_ID());
		reverse.setDateDoc(Env.getCurrentDate());
		reverse.setDateAcct(reverse.getDateDoc());

		//	Reverse indicator
		String description = reverse.getDescription();
		if (description == null)
			description = "** " + getDocumentNo() + " **";
		else
			description += " ** " + getDocumentNo() + " **";
		reverse.setDescription(description);
		if (!reverse.save())
			return null;
		
		//	Lines
		reverse.copyLinesFrom(this, reverse.getDateAcct(), 'R');
		//
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return reverse;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info(toString());
		return false;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		//	: Total Lines = 123.00 (#1)
		sb.append(": ")
			.append(Msg.translate(getCtx(),"TotalDr")).append("=").append(getTotalDr())
			.append(" ")
			.append(Msg.translate(getCtx(),"TotalCR")).append("=").append(getTotalCr())
			.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MJournal[");
		sb.append(get_ID()).append(",").append(getDescription())
			.append(",DR=").append(getTotalDr())
			.append(",CR=").append(getTotalCr())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
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
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID (Created)
	 */
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return DR amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return getTotalDr();
	}	//	getApprovalAmt
	
	public static List<MJournal> getJournals(String where, Properties ctx){
		ArrayList<MJournal> list = new ArrayList<MJournal>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM GL_Journal ")
		.append(" WHERE isActive= 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
		
		if(where != null){
			sql.append(where);
		}
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MJournal(ctx, rs, null));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql.toString(), e);
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
	}//getJournals
	
	// Inician parámetros para renderer
	private String periodName;
	private String docStatusStr;
	private String policyType;
	private String currency;

	public String getCurrency() {
		if (StringUtils.isBlank(currency)) {
			currency = DB.getSQLValueString(null, "select iso_code from c_currency where c_currency_id = ?", getC_Currency_ID());
		}
		return currency;
	}

	public String getPolicyType() {
		if (StringUtils.isBlank(policyType)) {
			policyType = DB.getSQLValueString(null, "select name from gl_category where gl_category_id = ? ", getGL_Category_ID());
		}
		return policyType;
	}

	public String getDocStatusStr() {
		if (StringUtils.isBlank(docStatusStr)) {
			docStatusStr = MRefList.getListName(getCtx(), X_GL_Journal.DOCSTATUS_AD_Reference_ID, getDocStatus());
		}
		return docStatusStr;
	}

	public String getPeriodName() {
		if (StringUtils.isBlank(periodName)) {
			periodName = DB.getSQLValueString(null, "select name from c_period where c_period_id = ?", getC_Period_ID());
		}
		return periodName;
	}
	// Fin de parámetros de renderer
	
	/**
	 * Busca todas las pólizas activas que cumplan los parámetros
	 * 
	 * @param ctx
	 *            Contexto
	 * @param docNo
	 *            Número de Documento (Opcional)
	 * @param docStatus
	 *            Estatus (Opcional)
	 * @param periodId
	 *            Período (Opcional)
	 * @param categoryId
	 *            Categoría (Opcional)
	 * @param postingType
	 *            Tipo de Contabilidad (Opcional)
	 * @param date
	 *            Fecha Inicio (Opcional)
	 * @param date2
	 *            Fecha Final (Opcional)
	 * @param trxName
	 *            Nombre de trx
	 * @return Listado de pólizas
	 */
	public static List<MJournal> get(Properties ctx, String docNo, String docStatus, int periodId, int categoryId, String postingType, Date date, Date date2, String trxName) {
		List<Object> params = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		if (StringUtils.isNotBlank(docNo)) {
			sql.append("  documentno = ? ");
			params.add(docNo);
		} else {
			sql.append("  1 = 1 ");

			if (StringUtils.isNotBlank(docStatus)) {
				sql.append("  AND docstatus = ? ");
				params.add(docStatus);
			}

			if (periodId > 0) {
				sql.append("  AND c_period_id = ? ");
				params.add(periodId);
			}

			if (date != null && date2 != null) {
				sql.append("  AND dateacct BETWEEN ? AND ?");
				params.add(date);
				params.add(date2);
			}

			if (categoryId > 0) {
				sql.append("  AND gl_category_id = ? ");
				params.add(categoryId);
			}

			if (StringUtils.isNotBlank(postingType)) {
				sql.append("  AND postingtype = ? ");
				params.add(postingType);
			}
		}

		Query query = new Query(ctx, Table_Name, sql.toString(), trxName);
		query.setOnlyActiveRecords(true);
		query.setParameters(params);
		query.addAccessLevelSQL(true);
		query.setOrderBy("created desc");

		return query.list();
	}
	
	private MEXMEPolicyInfo policyInfo;
	private Integer policyNo;
	private String policyNoStr;

	public MEXMEPolicyInfo getPolicyInfo() {
		if (policyInfo == null) {
			policyInfo = MEXMEPolicyInfo.get(getCtx(), Table_ID, getGL_Journal_ID(), 0, null);
		}
		return policyInfo;
	}

	public int getPolicyNo() {
		if (policyNo == null) {
			MEXMEPolicyInfo policyInfo = getPolicyInfo();

			if (policyInfo == null) {
				policyNo = 0;
			} else {
				policyNo = policyInfo.getPolicyNo();
			}
		}
		return policyNo;
	}

	public String getPolicyNoStr() {
		if (policyNoStr == null) {
			int policyNo = getPolicyNo();

			if (policyNo <= 0) {
				policyNoStr = Utilerias.getAppMsg(getCtx(), "msj.sinAsignarAun");
			} else {
				policyNoStr = Integer.toString(policyNo);
			}
		}
		return policyNoStr;
	}
	
}	//	MJournal
