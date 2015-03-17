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

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;

import org.compiere.util.*;

/**
 *	Payment Selection Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MPaySelectionLine.java,v 1.5 2006/08/11 02:26:22 mrojas Exp $
 */
public class MPaySelectionLine extends X_C_PaySelectionLine
{
	/** Logger								*/
	static private CLogger	s_log = CLogger.getCLogger (MPaySelectionLine.class);
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_PaySelectionLine_ID id
	 */
	public MPaySelectionLine (Properties ctx, int C_PaySelectionLine_ID, String trxName)
	{
		super(ctx, C_PaySelectionLine_ID, trxName);
		if (C_PaySelectionLine_ID == 0)
		{
		//	setC_PaySelection_ID (0);
		//	setPaymentRule (null);	// S
		//	setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_PaySelectionLine WHERE C_PaySelection_ID=@C_PaySelection_ID@
		//	setC_Invoice_ID (0);
			setIsSOTrx (false);
			setOpenAmt(Env.ZERO);
			setPayAmt (Env.ZERO);
			setDiscountAmt(Env.ZERO);
			setDifferenceAmt (Env.ZERO);
			setIsManual (false);
		}
	}	//	MPaySelectionLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MPaySelectionLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPaySelectionLine

	/**
	 * 	Parent Constructor
	 *	@param ps parent
	 *	@param Line line
	 *	@param PaymentRule payment rule
	 */
	public MPaySelectionLine (MPaySelection ps, int Line, String PaymentRule)
	{
		this (ps.getCtx(), 0, ps.get_TrxName());
		setClientOrg(ps);
		setC_PaySelection_ID(ps.getC_PaySelection_ID());
		setLine(Line);
		setPaymentRule(PaymentRule);
	}	//	MPaySelectionLine

	/**	Invoice					*/
	private MInvoice 	m_invoice = null;
	private MPayment	m_payment = null; //Expert:Juan

	/**
	 * 	Set Invoice Info
	 *	@param C_Invoice_ID invoice
	 *	@param isSOTrx sales trx
	 *	@param PayAmt payment
	 *	@param OpenAmt open
	 *	@param DiscountAmt discount
	 */
	public void setInvoice (int C_Invoice_ID, boolean isSOTrx, BigDecimal OpenAmt, 
		BigDecimal PayAmt, BigDecimal DiscountAmt)
	{
		setC_Invoice_ID (C_Invoice_ID);
		setIsSOTrx(isSOTrx);
		setOpenAmt(OpenAmt);
		setPayAmt (PayAmt);
		setDiscountAmt(DiscountAmt);
		setDifferenceAmt(OpenAmt.subtract(PayAmt).subtract(DiscountAmt));
	}	//	setInvoive

	/**
	 * 	Get Invoice
	 *	@return invoice
	 */
	public MInvoice getInvoice()
	{
		if (m_invoice == null)
			m_invoice = new MInvoice (getCtx(), getC_Invoice_ID(), get_TrxName());
		return m_invoice;
	}	//	getInvoice
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		setDifferenceAmt(getOpenAmt().subtract(getPayAmt()).subtract(getDiscountAmt()));
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		setHeader();
		return success;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return sucess
	 */
	protected boolean afterDelete (boolean success)
	{
		setHeader();
		return success;
	}	//	afterDelete
	
	/**
	 * 	Recalculate Header Sum
	 */
	private void setHeader()
	{
		//	Update Header
		String sql = "UPDATE C_PaySelection ps "
			+ "SET TotalAmt = (SELECT COALESCE(SUM(psl.PayAmt),0) "
				+ "FROM C_PaySelectionLine psl "
				+ "WHERE ps.C_PaySelection_ID=psl.C_PaySelection_ID AND psl.IsActive='Y') "
			+ "WHERE C_PaySelection_ID=" + getC_PaySelection_ID();
		DB.executeUpdate(sql, get_TrxName());
	}	//	setHeader
	
	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MPaySelectionLine[");
		sb.append(get_ID()).append(",C_Invoice_ID=").append(getC_Invoice_ID())
			.append(",PayAmt=").append(getPayAmt())
			.append(",DifferenceAmt=").append(getDifferenceAmt())
			.append("]");
		return sb.toString();
	}	//	toString

	//Expert:Juan - Inicia Bloque
	public void setPayment (int C_Payment_ID, boolean isSOTrx, BigDecimal OpenAmt, 
		BigDecimal PayAmt, BigDecimal DiscountAmt)
	{
		setC_Payment_ID (C_Payment_ID);
		setIsSOTrx(isSOTrx);
		setOpenAmt(OpenAmt);
		setPayAmt (PayAmt);
		setDiscountAmt(DiscountAmt);
		setDifferenceAmt(OpenAmt.subtract(PayAmt).subtract(DiscountAmt));
	}	//	
	
	public MPayment getPayment()
	{
		if (m_payment == null)
			m_payment = new MPayment (getCtx(), getC_Payment_ID(), null);
		return m_payment;
	}	//	getPayment

	
	/**************************************************************************
	 *  Get Payment Selection Line from Payment Selection Check
	 *
	 *  @param C_PaySelectionCheck_ID Payment Selection Check
	 *  @return array of Payment Selection Lines
	 */
	public static MPaySelectionLine[] get (int C_PaySelectionCheck_ID, String trxName)
	{
		
		ArrayList<MPaySelectionLine> list = new ArrayList<MPaySelectionLine>();

		String sql = "SELECT * FROM C_PaySelectionLine "
			+ "WHERE C_PaySelectionCheck_ID=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_PaySelectionCheck_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				MPaySelectionLine selLines = new MPaySelectionLine (Env.getCtx(), rs, trxName);
				list.add(selLines);
			}
			rs.close();
			pstmt.close();
			//expert:
			pstmt=null;
			rs=null;
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}

		//  convert to Array
		MPaySelectionLine[] retValue = new MPaySelectionLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}   
	
	//Expert:Juan - Fin del Bloque

}	//	MPaySelectionLine
