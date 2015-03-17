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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Bank Statement Line Model
 * 
 * @author Eldir Tomassen/Jorg Janke
 * @version $Id: MBankStatementLine.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MBankStatementLine extends X_C_BankStatementLine {

	/** Factura relacionada */
	private MInvoice mInvoice = null;
	/** Pago relacionada */
	private MPayment mPayment = null;

	/**
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param C_BankStatementLine_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MBankStatementLine (Properties ctx, int C_BankStatementLine_ID, String trxName)
	{
		super (ctx, C_BankStatementLine_ID, trxName);
		if (C_BankStatementLine_ID == 0)
		{
			//	setC_BankStatement_ID (0);		//	Parent
			//	setC_Charge_ID (0);
			//	setC_Currency_ID (0);	//	Bank Acct Currency
			//	setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_BankStatementLine WHERE C_BankStatement_ID=@C_BankStatement_ID@
			setStmtAmt(Env.ZERO);
			setTrxAmt(Env.ZERO);
			setInterestAmt(Env.ZERO);
			setChargeAmt(Env.ZERO);
			setIsReversal (false);
			//	setValutaDate (new Timestamp(System.currentTimeMillis()));	// @StatementDate@
			//	setDateAcct (new Timestamp(System.currentTimeMillis()));	// @StatementDate@
		}
	}	//	MBankStatementLine

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
	public MBankStatementLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	} // MBankStatementLine

	/**
	 * Parent Constructor
	 * 
	 * @param statement
	 *            Bank Statement that the line is part of
	 */
	public MBankStatementLine(MBankStatement statement) {
		this(statement.getCtx(), 0, statement.get_TrxName());
		setClientOrg(statement);
		setC_BankStatement_ID(statement.getC_BankStatement_ID());
		setStatementLineDate(statement.getStatementDate());
	} // MBankStatementLine

	/**
	 * Parent Constructor
	 * 
	 * @param statement
	 *            Bank Statement that the line is part of
	 * @param lineNo
	 *            position of the line within the statement
	 */
	public MBankStatementLine(MBankStatement statement, int lineNo) {
		this(statement);
		setLine(lineNo);
	} // MBankStatementLine

	/**
	 * Set Statement Line Date and all other dates (Valuta, Acct)
	 * 
	 * @param StatementLineDate
	 *            date
	 */
	public void setStatementLineDate(Timestamp StatementLineDate) {
		super.setStatementLineDate(StatementLineDate);
		setValutaDate(StatementLineDate);
		setDateAcct(StatementLineDate);
	} // setStatementLineDate

	/**
	 * Set Payment
	 * 
	 * @param payment
	 *            payment
	 */
	public void setPayment(MPayment payment) {
		setC_Payment_ID(payment.getC_Payment_ID());
		setC_Currency_ID(payment.getC_Currency_ID());
		//
		BigDecimal amt = payment.getPayAmt(true);
		setTrxAmt(amt);
		setStmtAmt(amt);
		//
		setDescription(payment.getDescription());
	} // setPayment

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
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave(boolean newRecord) {
		if (getChargeAmt().signum() != 0 && getC_Charge_ID() == 0) {
			log.saveError("FillMandatory",
					Msg.getElement(getCtx(), "C_Charge_ID"));
			return false;
		}
		// Set Line No
		if (getLine() == 0) {
			String sql = "SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM C_BankStatementLine WHERE C_BankStatement_ID=?";
			int ii = DB
					.getSQLValue(get_TrxName(), sql, getC_BankStatement_ID());
			setLine(ii);
		}

		// Set References
		if (getC_Payment_ID() != 0) {
			final MPayment payment = new MPayment(getCtx(), getC_Payment_ID(), get_TrxName());
			
			if(getC_BPartner_ID() == 0){
				setC_BPartner_ID(payment.getC_BPartner_ID());
				if (payment.getC_Invoice_ID() != 0) {
					setC_Invoice_ID(payment.getC_Invoice_ID());
				}
			}
			// Guadar en la descripción de la linea la descripción del pago
			setDescription(getDescription()+" "+ payment.getDescriptiveData());
		}
		
		if (getC_Invoice_ID() != 0 && getC_BPartner_ID() == 0) {
			MInvoice invoice = new MInvoice(getCtx(), getC_Invoice_ID(),
					get_TrxName());
			setC_BPartner_ID(invoice.getC_BPartner_ID());
		}

		// Calculate Charge = Statement - trx - Interest
		BigDecimal amt = getStmtAmt();
		amt = amt.subtract(getTrxAmt());
		amt = amt.subtract(getInterestAmt());
		if (amt.compareTo(getChargeAmt()) != 0)
			setChargeAmt(amt);
		//
		if(newRecord && getDateAcct().equals(getStatementLineDate()) && getC_BankStatement().getStatementDate()!=null){
			setDateAcct(getC_BankStatement().getStatementDate());
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
	 * @return success
	 */
	protected boolean afterSave(boolean newRecord, boolean success) {
		updateHeader();
		return success;
	} // afterSave

	/**
	 * After Delete
	 * 
	 * @param success
	 *            success
	 * @return success
	 */
	protected boolean afterDelete(boolean success) {
		updateHeader();
		return success;
	} // afterSave

	/**
	 * Update Header
	 */
	private void updateHeader() {
		String sql = "UPDATE C_BankStatement bs"
				+ " SET StatementDifference=(SELECT SUM(StmtAmt) FROM C_BankStatementLine bsl "
				+ "WHERE bsl.C_BankStatement_ID=bs.C_BankStatement_ID AND bsl.IsActive='Y') "
				+ "WHERE C_BankStatement_ID=" + getC_BankStatement_ID();
		DB.executeUpdate(sql, get_TrxName());
		sql = "UPDATE C_BankStatement bs"
				+ " SET EndingBalance=BeginningBalance+StatementDifference "
				+ "WHERE C_BankStatement_ID=" + getC_BankStatement_ID();
		DB.executeUpdate(sql, get_TrxName());
	} // updateHeader

	/**
	 * Obtenemos la factura relacionada
	 * 
	 * @return MInvoice de C_BankStatement.C_Invoice_ID
	 */ 
	public MInvoice getInvoice() {
		if (mInvoice == null && getC_Invoice_ID() > 0) {
			mInvoice = new MInvoice(getCtx(), getC_Invoice_ID(), get_TrxName());
		}
		return mInvoice;
	}

	/**
	 * Obtenemos el pago facturado
	 * 
	 * @return MPayment de C_BankStatement.C_Payment_ID
	 */
	public MPayment getPayment() {
		if (mPayment == null && getC_Payment_ID() > 0) {
			mPayment = new MPayment(getCtx(), getC_Payment_ID(), get_TrxName());
		}
		return mPayment;
	}

} // MBankStatementLine
