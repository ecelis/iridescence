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
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MEXMEI18N;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.X_C_DocType;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Post Invoice Documents.
 *  <pre>
 *  Table:              C_BankStatement (392)
 *  Document Types:     CMB
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_Bank.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_Bank extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	protected Doc_Bank (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, MBankStatement.class, rs, DOCTYPE_BankStatement, trxName);
	}	//	Doc_Bank
	
	/** Bank Account			*/
	private int			m_C_BankAccount_ID = 0;

	/**
	 *  Load Specific Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails ()
	{
		MBankStatement bs = (MBankStatement)getPO();
		setDateDoc(bs.getStatementDate());
		setDateAcct(bs.getStatementDate());	//	Overwritten on Line Level
		
		m_C_BankAccount_ID = bs.getC_BankAccount_ID();
		//	Amounts
		setAmount(AMTTYPE_Gross, bs.getStatementDifference());

		//  Set Bank Account Info (Currency)
		MBankAccount ba = MBankAccount.get (getCtx(), m_C_BankAccount_ID);
		setC_Currency_ID (ba.getC_Currency_ID());
		//setNational(ba.getC_Bank().isNational());
		
		//	Contained Objects
		p_lines = loadLines(bs);
		log.fine("Lines=" + p_lines.length);
		return null;
	}   //  loadDocumentDetails

	/**
	 *	Load Invoice Line.
	 *	@param bs bank statement
	 *  4 amounts
	 *  AMTTYPE_Payment
	 *  AMTTYPE_Statement2
	 *  AMTTYPE_Charge
	 *  AMTTYPE_Interest
	 *  @return DocLine Array
	 */
	private DocLine[] loadLines(MBankStatement bs)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		MBankStatementLine[] lines = bs.getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			final MBankStatementLine line = lines[i];
			final DocLine_Bank docLine = new DocLine_Bank(line, this);
			//	Set Date Acct
			if (i == 0) {
//				setDateAcct(line.getDateAcct()); // TODO En la misma fecha del encabezado GC
				setDateAcct(getDateAcct());	//	Overwritten on Line Level
			}
			
			final MPeriod period = MPeriod.get(getCtx(), line.getDateAcct(), line.getAD_Org_ID());
			if (period != null && period.isOpen(DOCTYPE_BankStatement, line.getCreated()))
				docLine.setC_Period_ID(period.getC_Period_ID());
			//
			list.add(docLine);
		}

		//	Return Array
		DocLine[] dls = new DocLine[list.size()];
		list.toArray(dls);
		return dls;
	}	//	loadLines

	
	/**************************************************************************
	 *  Get Source Currency Balance - subtracts line amounts from total - no rounding
	 *  @return positive amount, if total invoice is bigger than lines
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		StringBuffer sb = new StringBuffer (" [");
		//  Total
		retValue = retValue.add(getAmount(Doc.AMTTYPE_Gross));
		sb.append(getAmount(Doc.AMTTYPE_Gross));
		//  - Lines
		for (int i = 0; i < p_lines.length; i++)
		{
			BigDecimal lineBalance = ((DocLine_Bank)p_lines[i]).getStmtAmt();
			retValue = retValue.subtract(lineBalance);
			sb.append("-").append(lineBalance);
		}
		sb.append("]");
		//
		log.fine(toString() + " Balance=" + retValue + sb.toString());
		return retValue;
	}   //  getBalance

	/**
	 *  Create Facts (the accounting logic) for
	 *  CMB.
	 *  <pre>
	 *      BankAsset       DR      CR  (Statement)
	 *      BankInTransit   DR      CR              (Payment)
	 *      Charge          DR          (Charge)
	 *      Interest        DR      CR  (Interest)
	 *  </pre>
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (final MAcctSchema as)
	{
		//  create Fact Header
		final Fact fact = new Fact(this, as, Fact.POST_Actual);

		//  Header -- there may be different currency amounts

		FactLine fl = null;
		final int AD_Org_ID = getBank_Org_ID();	//	Bank Account Org
		//  Lines
		for (int i = 0; i < p_lines.length; i++)
		{
			final DocLine_Bank line = (DocLine_Bank)p_lines[i];
			final int C_BPartner_ID = line.getC_BPartner_ID();

			final int accType;
			if (MPayment.PAYMENTTYPE_ACH.equals(new MPayment(getCtx(), line.getC_Payment_ID(), null).getPaymentType())) {
				accType = Doc.ACCTTYPE_BankAsset;// Pagos con transferencia Card 806
			} else {
				accType = Doc.ACCTTYPE_BankInTransit;// Pagos en cheque
			}

			//  BankAsset       DR      CR  (Statement)
			if(accType == Doc.ACCTTYPE_BankInTransit){// mientras no sea un pago transferencia Card 806
				fl = fact.createLine(line,
						getAccount(Doc.ACCTTYPE_BankAsset, as),
						line.getC_Currency_ID(), line.getStmtAmt());//ok
				if (fl != null && AD_Org_ID != 0){
					fl.setAD_Org_ID(AD_Org_ID);
				}
				if (fl != null && C_BPartner_ID != 0){
					fl.setC_BPartner_ID(C_BPartner_ID);
				}
			}

			//  BankInTransit   DR      CR              (Payment)
			//			int accType = 0;
			//			if (MPayment.PAYMENTTYPE_ACH.equals(new MPayment(Env.getCtx(), line.getC_Payment_ID(), null).getPaymentType())) {
			//				accType = Doc.ACCTTYPE_BankAsset;
			//			} else {
			//				accType = Doc.ACCTTYPE_BankInTransit;
			//			}
			if(accType == Doc.ACCTTYPE_BankInTransit){// mientras no sea un pago transferencia Card 806
				fl = fact.createLine(line,
						getAccount(accType, as),
						line.getC_Currency_ID(), line.getTrxAmt().negate());//ok
				if (fl != null)
				{
					if (C_BPartner_ID != 0)
						fl.setC_BPartner_ID(C_BPartner_ID);
					if (AD_Org_ID != 0)
						fl.setAD_Org_ID(AD_Org_ID);
					else
						fl.setAD_Org_ID(line.getAD_Org_ID(true)); // from payment
				}
			}

			//  Charge          DR          (Charge)
			fl = fact.createLine(line,//no
					line.getChargeAccount(as, line.getChargeAmt().negate()),
					line.getC_Currency_ID(), line.getChargeAmt().negate(), null);

			if (fl != null && C_BPartner_ID != 0) {
				fl.setC_BPartner_ID(C_BPartner_ID);
			}

			//  Interest        DR      CR  (Interest)
			if (line.getInterestAmt().signum() < 0) {
				fl = fact.createLine(line,
						getAccount(Doc.ACCTTYPE_InterestExp, as), getAccount(Doc.ACCTTYPE_InterestExp, as),
						line.getC_Currency_ID(), line.getInterestAmt().negate());
			} else {
				fl = fact.createLine(line,
						getAccount(Doc.ACCTTYPE_InterestRev, as), getAccount(Doc.ACCTTYPE_InterestRev, as),
						line.getC_Currency_ID(), line.getInterestAmt().negate());
			}

			if (fl != null && C_BPartner_ID != 0){
				fl.setC_BPartner_ID(C_BPartner_ID);
			}
			
			//	fact.createTaxCorrection();
			createTaxCorrection(as, line, fact);
			
		}// fin for

		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact

	/**
	 * Lineas de impuesto de factura(s) relacionada(s) al pago
	 * Tax Due Acct        ACCTTYPE_TaxDue = 0;// Impuesto por pagar VTA
	 * Tax Liability       ACCTTYPE_TaxLiability = 1;// Impuesto a cargo VTA
	 * Tax Credit          ACCTTYPE_TaxCredit = 2;// Impuesto por recuperar CMPRA
	 * Tax Receivables     ACCTTYPE_TaxReceivables = 3;// Impuesto a favor CMPRA
	 * @param as
	 * @param line
	 * @param fact
	 */
	private void createTaxCorrection(final MAcctSchema as, final DocLine_Bank line, final Fact fact){

		final MEXMEI18N valMex = MEXMEI18N.getFromCountry(getCtx(), Env.getC_Country_ID(getCtx()), null);
		if (valMex == null || !valMex.ismoveTaxes()
				|| line.getM_BankStatementLine()==null || line.getM_BankStatementLine().getC_Payment_ID()<=0){
			return;
		}

		if(line.getM_BankStatementLine().getC_Payment_ID()> 0) {
			final DocTax[] m_taxes = 
					X_C_DocType.DOCBASETYPE_APPayment.equals(line.getM_BankStatementLine().getPayment(). getDocType().getDocBaseType())
					
					? Doc_Bank.loadTaxesVendor(getCtx(),  
							line.getC_Payment_ID(), line.getM_BankStatementLine().getPayment().getPayAmt(), getTrxName())

							: Doc_Bank.loadTaxesCustomer(getCtx(), 
									line.getC_Payment_ID(), line.getM_BankStatementLine().getPayment().getPayAmt(), getTrxName());

					if(m_taxes!=null){
						for (final DocTax mtax: m_taxes)	{
							final BigDecimal amt = mtax.getAmount();
							if (amt != null && amt.signum() != 0) {
								
								boolean isSalesTax = !X_C_DocType.DOCBASETYPE_APPayment.equals(line.getM_BankStatementLine().getPayment(). getDocType().getDocBaseType());
								
								
								if(isSalesTax /*mtax.isSalesTax()*/){
									//  TaxDue           CR -VENTAS
									FactLine tl = fact.createLine(null, mtax.getAccount(mtax.getARTaxTypeBank(true), as), getC_Currency_ID(), amt, null);
									if (tl != null){
										tl.setC_Tax_ID(mtax.getC_Tax_ID());
									}

									tl = fact.createLine(null, mtax.getAccount(mtax.getARTaxTypeBank(false), as),
											getC_Currency_ID(), null, amt);
									if (tl != null){
										tl.setC_Tax_ID(mtax.getC_Tax_ID());
									}
									
								} else {
									//  TaxCredit       DR -COMPRAS
									FactLine tl = fact.createLine(null, mtax.getAccount(mtax.getAPTaxTypeBank(false), as),
											getC_Currency_ID(), null, amt);
									if (tl != null) {
										tl.setC_Tax_ID(mtax.getC_Tax_ID());
									}
									tl = fact.createLine(null, mtax.getAccount(mtax.getAPTaxTypeBank(true), as),
											getC_Currency_ID(), amt, null);
									if (tl != null) {
										tl.setC_Tax_ID(mtax.getC_Tax_ID());
									}
								}// fin else (Compra/venta)
							}
						}// fin for
					}
		}
	}

	/**
	 * 	Get AD_Org_ID from Bank Account
	 * 	@return AD_Org_ID or 0
	 */
	private int getBank_Org_ID ()
	{
		if (m_C_BankAccount_ID == 0)
			return 0;
		//
		MBankAccount ba = MBankAccount.get(getCtx(), m_C_BankAccount_ID);
		return ba.getAD_Org_ID();
	}	//	getBank_Org_ID

	/**
	 *	Load Invoice Taxes
	 *					
	 *	COMO LA FACTURA INCLUYE PRODUCTOS GRAVADOS EN TASA 0% Y 16%					
	 *	EN EL EJEMPLO LA PARTE GRAVADA CORRESPONDEN 1000.00					
	 *	EN ESTE CASO EL SISTEMA DEBE VALIDAR EN PRIMER LUGAR SI LA FACTURA					
	 *	QUE SE PAGO Y QUE SE ESTA CONCILIANDO CONTIENE PRODUCTOS GRAVADOS.					
	 *	DESPUES DE IDENTIFICAR EL IMPORTE DE LA PARTE GRAVADA, SE DEBE COMPARAR					
	 *	EL VALOR DEL PAGO, 					
	 *  @return DocTax Array
	 */
	public static DocTax[] loadTaxesCustomer(final Properties ctx, /*final int pCInvoiceId,*/ final int mCPaymentId,
			final BigDecimal amountPayment, final String trxName){
		// Lineas a devolver
		final ArrayList<DocTax> list = new ArrayList<DocTax>();

		// Buscar las asignaciones del pago
		final StringBuilder sql = new StringBuilder()
		.append(" WITH Asignacion AS ( ")
		.append(" 		SELECT al.C_Invoice_ID ")
		.append(" 		, COALESCE(inv.GrandTotal,0) as GrandTotal ")
		.append(" 		, COALESCE(inv.TotalLines,0) as TotalLines ")
		.append(" 		, COALESCE(inv.TaxAmt,0) as TaxAmt ")
		.append(" 		, SUM( COALESCE(al.Amount,0)) as Amount ")
		.append(" 		, inv.isSOTrx ")
		.append(" 		FROM  C_AllocationLine    al  ")
		.append(" 		INNER JOIN C_Invoice_Customer_V inv ON  al.C_Invoice_ID = inv.C_Invoice_ID ")
		.append(" 		where al.IsActive = 'Y' ")
		.append(" 		AND   al.C_Payment_ID = ? ")//# 1
		.append("       AND   al.AD_Client_ID = ? ")
		.append(" 		and   inv.TaxAmt > 0 ")
		.append(" 		GROUP BY al.C_Invoice_ID, inv.GrandTotal, inv.TotalLines, inv.TaxAmt, inv.isSOTrx ")
		.append(" 	) ")
		.append(" 	SELECT Asignacion.C_Invoice_ID, Asignacion.Amount, Asignacion.GrandTotal ")
		.append(" 	, lin.C_Tax_ID, SUM( COALESCE(lin.TaxAmt,0)) as TaxAmt ")
		.append(" 	, tax.Name, tax.Rate, Asignacion.isSOTrx ")
		.append(" 	FROM Asignacion  ")
		.append(" 	INNER JOIN C_InvoiceLine lin ON lin.C_Invoice_ID = Asignacion.C_Invoice_ID ")
		.append(" 	INNER JOIN C_Tax         tax ON tax.C_Tax_ID = lin.C_Tax_ID ")
		.append(" 	GROUP BY Asignacion.C_Invoice_ID, Asignacion.Amount, lin.C_Tax_ID ")
		.append(" 	, Asignacion.GrandTotal, Asignacion.TotalLines, Asignacion.TaxAmt ")
		.append(" 	, tax.Name, tax.Rate, Asignacion.isSOTrx ");

//		.append(" SELECT distinct on (al.AmtAcct) inv.isSOTrx ")
////		.append(" , case when row_number() over () = 1 then  SUM(COALESCE(al.AmtAcct   ,0)) else 0 END AS ALAmtAcct ")
//		.append(" ,  SUM(COALESCE(al.AmtAcct   ,0)) AS ALAmtAcct ")
//		.append(" , SUM(COALESCE(itax.AmtAcct ,0)) AS ITAmtAcct ")
//		.append(" , itax.C_Tax_ID ")
//		.append(" , tax.Name ")
//		.append(" , tax.Rate ")
//		.append(" FROM  C_AllocationLine    al ")
//		.append(" INNER JOIN C_Invoice     inv ON  inv.C_Invoice_ID = al.C_Invoice_ID  ")
//		.append(" INNER JOIN C_InvoiceTax itax ON itax.C_Invoice_ID = inv.C_Invoice_ID ")
//		.append(" INNER JOIN C_Tax         tax ON  tax.C_Tax_ID     = itax.C_Tax_ID ")
//		.append(" WHERE al.IsActive = 'Y' ")
//		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", X_C_AllocationLine.Table_Name, "al"))
//		.append(" AND   al.C_Payment_ID = ? ")
//		.append(" GROUP BY al.AmtAcct, inv.isSOTrx, itax.C_Tax_ID, tax.Name, tax.Rate  ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mCPaymentId);
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery();
			//
			while (rs.next()){
				final BigDecimal grandTotal = rs.getBigDecimal("GrandTotal")==null?Env.ZERO:rs.getBigDecimal("GrandTotal"); 
				final BigDecimal taxAmt = rs.getBigDecimal("TaxAmt")==null?Env.ZERO:rs.getBigDecimal("TaxAmt"); 
				final BigDecimal amount = rs.getBigDecimal("Amount")==null?Env.ZERO:rs.getBigDecimal("Amount"); // Monto Asignado
				
				// rs.getBigDecimal("ITAmtAcct")==null?Env.ZERO:rs.getBigDecimal("ITAmtAcct")
				// rs.getBigDecimal("ALAmtAcct")==null?Env.ZERO:rs.getBigDecimal("ALAmtAcct")
				BigDecimal ALAmtAcct = Env.ZERO;
				if(grandTotal.compareTo(Env.ZERO)>0){// Aplicar la regla de tres
					ALAmtAcct = (amount.multiply(taxAmt)).divide(grandTotal, 6, BigDecimal.ROUND_HALF_UP);
				}
				
				// El pago puede ser parcial de la factura 
				final DocTax taxLine = new DocTax(
						rs.getInt("C_Tax_ID")<=0?0:rs.getInt("C_Tax_ID") 
								, rs.getString("Name")==null?StringUtils.EMPTY:rs.getString("Name") 
										, rs.getBigDecimal("Rate")==null?Env.ZERO:rs.getBigDecimal("Rate")
												, Env.ZERO
														, ALAmtAcct
																, "Y".equals(rs.getString("isSOTrx")));
				s_log.fine(taxLine.toString());
				list.add(taxLine);
			}
		} catch (SQLException e){
			s_log.log(Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);rs = null;pstmt = null;
		}

		//	Return Array
		DocTax[] tl = new DocTax[list.size()];
		list.toArray(tl);
		return tl;
	}	//	loadTaxes
	
	
	/**
	 *	Load Invoice Taxes
	 *					
	 *	COMO LA FACTURA INCLUYE PRODUCTOS GRAVADOS EN TASA 0% Y 16%					
	 *	EN EL EJEMPLO LA PARTE GRAVADA CORRESPONDEN 1000.00					
	 *	EN ESTE CASO EL SISTEMA DEBE VALIDAR EN PRIMER LUGAR SI LA FACTURA					
	 *	QUE SE PAGO Y QUE SE ESTA CONCILIANDO CONTIENE PRODUCTOS GRAVADOS.					
	 *	DESPUES DE IDENTIFICAR EL IMPORTE DE LA PARTE GRAVADA, SE DEBE COMPARAR					
	 *	EL VALOR DEL PAGO, 					
	 *  @return DocTax Array
	 */
	public static DocTax[] loadTaxesVendor(final Properties ctx,/* final int pCInvoiceId,*/ final int mCPaymentId, 
			final BigDecimal amountPayment, final String trxName){
		// Lineas a devolver
		final ArrayList<DocTax> list = new ArrayList<DocTax>();

		// Buscar las asignaciones del pago
		final StringBuilder sql = new StringBuilder()
		.append(" WITH Asignacion AS ( ")
		.append(" 		SELECT al.C_Invoice_ID ")
		.append(" 		, COALESCE(inv.GrandTotal,0) as GrandTotal ")
		.append(" 		, COALESCE(inv.TotalLines,0) as TotalLines ")
		.append(" 	    , COALESCE( (SELECT  SUM( COALESCE(TAXAMT,0)) FROM C_INVOICETAX WHERE C_INVOICE_ID = al.C_Invoice_ID),0) as TaxAmt  ")
		.append(" 		, SUM( COALESCE(al.Amount,0)) as Amount ")
		.append(" 		, inv.isSOTrx ")
		.append(" 		FROM  C_AllocationLine    al  ")
		.append(" 		INNER JOIN C_Invoice inv ON  al.C_Invoice_ID = inv.C_Invoice_ID ")
		.append(" 		INNER JOIN C_DocType dty ON inv.C_DocType_ID = dty.C_DocType_ID ")
		.append("                               AND dty.DOCBASETYPE  = 'API' ")
		.append(" 		where al.IsActive = 'Y' ")
		.append(" 		AND   al.C_Payment_ID = ? ")//# 1
		.append("       AND   al.AD_Client_ID = ? ")
		//.append(" 		and   inv.TaxAmt <> 0 ")
		.append(" 		GROUP BY al.C_Invoice_ID, inv.GrandTotal, inv.TotalLines, inv.TaxAmt, inv.isSOTrx ")
		.append(" 	) ")
		.append(" 	SELECT Asignacion.C_Invoice_ID, Asignacion.Amount, Asignacion.GrandTotal ")
		.append(" 	, lin.C_Tax_ID, SUM( COALESCE(lin.TaxAmt,0)) as TaxAmt ")
		.append(" 	, tax.Name, tax.Rate, Asignacion.isSOTrx ")
		.append(" 	FROM Asignacion  ")
		.append(" 	INNER JOIN C_InvoiceLine lin ON lin.C_Invoice_ID = Asignacion.C_Invoice_ID ")
		.append(" 	INNER JOIN C_Tax         tax ON tax.C_Tax_ID = lin.C_Tax_ID ")
		.append(" 	WHERE Asignacion.TaxAmt <> 0  ")
		.append(" 	GROUP BY Asignacion.C_Invoice_ID, Asignacion.Amount, lin.C_Tax_ID ")
		.append(" 	, Asignacion.GrandTotal, Asignacion.TotalLines, Asignacion.TaxAmt ")
		.append(" 	, tax.Name, tax.Rate, Asignacion.isSOTrx ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mCPaymentId);
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery();
			//
			while (rs.next()){
				final BigDecimal grandTotal = rs.getBigDecimal("GrandTotal")==null?Env.ZERO:rs.getBigDecimal("GrandTotal"); 
				final BigDecimal taxAmt = rs.getBigDecimal("TaxAmt")==null?Env.ZERO:rs.getBigDecimal("TaxAmt"); 
				final BigDecimal amount = rs.getBigDecimal("Amount")==null?Env.ZERO:rs.getBigDecimal("Amount"); // Monto Asignado
				
				// rs.getBigDecimal("ITAmtAcct")==null?Env.ZERO:rs.getBigDecimal("ITAmtAcct")
				// rs.getBigDecimal("ALAmtAcct")==null?Env.ZERO:rs.getBigDecimal("ALAmtAcct")
				BigDecimal ALAmtAcct = Env.ZERO;
				if(grandTotal.compareTo(Env.ZERO)>0){// Aplicar la regla de tres
					ALAmtAcct = (amount.multiply(taxAmt)).divide(grandTotal, 6, BigDecimal.ROUND_HALF_UP);
				}
				
				// El pago puede ser parcial de la factura 
				final DocTax taxLine = new DocTax(
						rs.getInt("C_Tax_ID")<=0?0:rs.getInt("C_Tax_ID") 
								, rs.getString("Name")==null?StringUtils.EMPTY:rs.getString("Name") 
										, rs.getBigDecimal("Rate")==null?Env.ZERO:rs.getBigDecimal("Rate")
												, Env.ZERO
														, ALAmtAcct
																, "Y".equals(rs.getString("isSOTrx")));
				s_log.fine(taxLine.toString());
				list.add(taxLine);
			}
		} catch (SQLException e){
			s_log.log(Level.SEVERE, sql.toString(), e);
			
		} finally {
			DB.close(rs, pstmt);rs = null;pstmt = null;
		}

		//	Return Array
		DocTax[] tl = new DocTax[list.size()];
		list.toArray(tl);
		return tl;
	}	//	loadTaxes
}   //  Doc_Bank

//	/**
//	 *	Load Invoice Taxes
//	 *					
//	 *			 	COMO LA FACTURA INCLUYE PRODUCTOS GRAVADOS EN TASA 0% Y 16%					
//	 *				EN EL EJEMPLO LA PARTE GRAVADA CORRESPONDEN 1000.00					
//	 *				EN ESTE CASO EL SISTEMA DEBE VALIDAR EN PRIMER LUGAR SI LA FACTURA					
//	 *				QUE SE PAGO Y QUE SE ESTA CONCILIANDO CONTIENE PRODUCTOS GRAVADOS.					
//	 *				DESPUES DE IDENTIFICAR EL IMPORTE DE LA PARTE GRAVADA, SE DEBE COMPARAR					
//	 *				EL VALOR DEL PAGO, 					
//	 *  @return DocTax Array
//	 */
//	public static DocTax[] loadTaxes(final int pCInvoiceId, final int mCPaymentId, 
//			final BigDecimal amountPayment, final boolean pRepost, final String trxName){
//
//		// Cuantas y cuales facturas se pagaron 
//		List<MAllocationLine> allocations = new ArrayList<MAllocationLine> ();
//		StringBuilder sql = new StringBuilder()
//		.append(" SELECT al.* ")
//		.append(" FROM  C_AllocationLine al ")
//		.append(" WHERE al.C_Payment_ID = ? ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try
//		{
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, mCPaymentId);
//			rs = pstmt.executeQuery();
//			//
//			while (rs.next())
//			{
//				allocations.add(new MAllocationLine(Env.getCtx(), rs, trxName));
//			}
//		} catch (SQLException e){
//			s_log.log(Level.SEVERE, sql.toString(), e);
//			return null;
//		} finally {
//			DB.close(rs, pstmt);rs = null;pstmt = null;
//		}
//
//
//		// Facturas pagadas completamente o pagadas parcialmente
//		if(allocations.isEmpty()){
//			return null;
//		}
//		
//		// Lineas a devolver
//		ArrayList<DocTax> list = new ArrayList<DocTax>();
//		
//		// Iteramos las facturas
//		for (int i = 0; i < allocations.size(); i++) {
//			
//			// Factura a cubrir
//			MInvoice minvoice = new MInvoice (Env.getCtx(), allocations.get(i).getC_Invoice_ID(), trxName);// MInvoice[1000630-1000080,GrandTotal=208.5]
//			// Pago asignado a la factura
//			BigDecimal pagoAplicado = allocations.get(i).getAmount();// 98.5
//			// Impuestos de la factura
//			MInvoiceTax[] mtaxes = minvoice.getTaxes(true);// [MInvoiceTax[C_Invoice_ID=1000630,C_Tax_ID=10001001, Base=100,Tax=8.5], MInvoiceTax[C_Invoice_ID=1000630,C_Tax_ID=1000055, Base=100,Tax=0]]
//			
//			if(mtaxes==null || mtaxes.length<=0){
//				continue;
//			}
//			
//			// Monto previamente trasladado
//			BigDecimal amtAcctInv = allocations.get(i).getAmtAcct();//0
//			
//			// Se iteran las lineas de impuesto
//			for (int x = 0; x < mtaxes.length; x++) {
//
//				// Debe existir una parte gravada de la factura y pendiente de pagar  								
//				if(mtaxes[x].getTaxAmt().compareTo(Env.ZERO)==0 || amtAcctInv.compareTo(mtaxes[x].getTaxBaseAmt().add(mtaxes[x].getTaxAmt()))==0){
//					continue;
//				}
//
//				// Base sobre la que se calcula el impuesto
//				BigDecimal base = Env.ZERO;
//				// Importe que se mostrara en la poliza
//				BigDecimal ivadelpago = Env.ZERO;
//				
//				// Impuesto aplicado
//				MTax mtax = new MTax(Env.getCtx(), mtaxes[x].getC_Tax_ID(), trxName);//MTax[10001001,Mahmood Medical Group Sales Tax, SO/PO=B,Rate=8.5,C_TaxCategory_ID=10001001,Summary=false,Parent=0,Country=100|100,Region=0|0]
//				// Desglosar iva
//				BigDecimal multiplier = mtax.getRate().divide(Env.ONEHUNDRED, 12, BigDecimal.ROUND_HALF_UP).add(Env.ONE);//1.085	
//
//
//				// BaseGravadaPendienteDePago: Base gravada + Impuesto - Impuesto conciliado o trasladado previamente //100 + 8.5 = 108.5
//				BigDecimal baseGravadaPendienteDePago = mtaxes[x].getTaxBaseAmt().add(mtaxes[x].getTaxAmt()).subtract(amtAcctInv);
//
//				// Se quita a la base gravada pendiente de pagar el pago que se aplica a la factura 
//				BigDecimal ivaPendienteDePagoMenosPago = baseGravadaPendienteDePago.subtract(pagoAplicado.abs());//(108.5 - 98.5)=10
//				
//						
//				// si es positivo (base gravada es mayor que  el pago)//(3700-2700)
//				if(ivaPendienteDePagoMenosPago.compareTo(Env.ZERO)>=0){
//					
//					base = pagoAplicado.abs().divide(multiplier, 12, BigDecimal.ROUND_HALF_UP);//(98.5/1.085)=90.783410138249
////					ivadelpago = base.multiply(mtax.getRate().divide(Env.ONEHUNDRED, 12, BigDecimal.ROUND_HALF_UP));//(90.783410138249*(85/100=0,085)=7.716589861751165000000000)
//					// Se actualiza el monto trasladado
//					amtAcctInv = amtAcctInv.add(pagoAplicado.abs());// +
//					
//				} else {
//					// si es negativo (base gravada menos que el pago)//(65 - 2700)
//					base = baseGravadaPendienteDePago.abs().divide(multiplier, 12, BigDecimal.ROUND_HALF_UP);//(65/1.16)
////					ivadelpago = base.multiply(mtax.getRate().divide(Env.ONEHUNDRED, 12, BigDecimal.ROUND_HALF_UP));//(58.62*.16)=9,376
//					// Se actualiza el monto trasladado
//					amtAcctInv = amtAcctInv.add(baseGravadaPendienteDePago.abs());// +65 
//				}
//
//				// Iva del pago con relacion a la parte gravada de la factura
//				ivadelpago = base.multiply(mtax.getRate().divide(Env.ONEHUNDRED, 12, BigDecimal.ROUND_HALF_UP));
//
//				// considerando que puede haber mas de una tasa
//				pagoAplicado = pagoAplicado.abs().subtract(base);// REVISAR CON SANDRA
//				
//				
//				// El pago puede ser parcial de la factura			
//				DocTax taxLine = new DocTax(mtax.getC_Tax_ID(), mtax.getName(), mtax.getRate(), 
//						base, ivadelpago, mtax.isSalesTax());
//				s_log.fine(taxLine.toString());
//				list.add(taxLine);
//			} // si hay im
//			
//		}// for de taxas
//
//		//	Return Array
//		DocTax[] tl = new DocTax[list.size()];
//		list.toArray(tl);
//		return tl;
//	}	//	loadTaxes
