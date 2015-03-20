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
package org.compiere.process;

import java.math.*;
import java.sql.*;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *	Write-off Open Invoices
 *	
 *  @author Jorg Janke
 *  @version $Id: InvoiceWriteOff.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class InvoiceWriteOff extends SvrProcess
{
	/**	BPartner				*/
	private int			p_C_BPartner_ID = 0;
	/** BPartner Group			*/
	private int			p_C_BP_Group_ID = 0;
	/**	Invoice					*/
	private int			p_C_Invoice_ID = 0;
	
	/** Max Amt					*/
	private BigDecimal	p_MaxInvWriteOffAmt = Env.ZERO;
	/** AP or AR				*/
	private String		p_APAR = "R";
	private static String	ONLY_AP = "P";
	private static String	ONLY_AR = "R";
	
	/** Invoice Date From		*/
	private Timestamp	p_DateInvoiced_From = null;
	/** Invoice Date To			*/
	private Timestamp	p_DateInvoiced_To = null;
	/** Accounting Date			*/
	private Timestamp	p_DateAcct = null;
	/** Create Payment			*/
	private boolean		p_CreatePayment = false;
	/** Bank Account			*/
	private int			p_C_BankAccount_ID = 0;
	/** Simulation				*/
	private boolean		p_IsSimulation = true;

	/**	Allocation Hdr			*/
	private MAllocationHdr	m_alloc = null;
	/**	Payment					*/
	private MPayment		m_payment = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BP_Group_ID"))
				p_C_BP_Group_ID = para[i].getParameterAsInt();
			else if (name.equals("C_Invoice_ID"))
				p_C_Invoice_ID = para[i].getParameterAsInt();
			//
			else if (name.equals("MaxInvWriteOffAmt"))
				p_MaxInvWriteOffAmt = (BigDecimal)para[i].getParameter();
			else if (name.equals("APAR"))
				p_APAR = (String)para[i].getParameter();
			//
			else if (name.equals("DateInvoiced"))
			{
				p_DateInvoiced_From = (Timestamp)para[i].getParameter();
				p_DateInvoiced_To = (Timestamp)para[i].getParameter_To();
			}
			else if (name.equals("DateAcct"))
				p_DateAcct = (Timestamp)para[i].getParameter();
			//
			else if (name.equals("CreatePayment"))
				p_CreatePayment = "Y".equals(para[i].getParameter());
			else if (name.equals("C_BankAccount_ID"))
				p_C_BankAccount_ID = para[i].getParameterAsInt();
			//
			else if (name.equals("IsSimulation"))
				p_IsSimulation = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 * 	Execute
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("C_BPartner_ID=" + p_C_BPartner_ID 
			+ ", C_BP_Group_ID=" + p_C_BP_Group_ID
			+ ", C_Invoice_ID=" + p_C_Invoice_ID
			+ "; APAR=" + p_APAR
			+ ", " + p_DateInvoiced_From + " - " + p_DateInvoiced_To
			+ "; CreatePayment=" + p_CreatePayment
			+ ", C_BankAccount_ID=" + p_C_BankAccount_ID);
		
		//Se debe seleccionar un socio, factura o grupo de socios
		if (p_C_BPartner_ID == 0 && p_C_Invoice_ID == 0 && p_C_BP_Group_ID == 0)
			throw new CompiereUserError ("@FillMandatory@ @C_Invoice_ID@ / @C_BPartner_ID@ / ");
		
		//Si se desea crear el pago, se debe seleccionar la cuenta bancaria
		if (p_CreatePayment && p_C_BankAccount_ID == 0)
			throw new CompiereUserError ("@FillMandatory@  @C_BankAccount_ID@");
		
		StringBuffer sql = new StringBuffer("SELECT C_Invoice_ID,DocumentNo,DateInvoiced,")
			.append(" C_Currency_ID,GrandTotal, invoiceOpen(C_Invoice_ID, 0) AS OpenAmt ")
			.append("FROM C_Invoice WHERE ");
		
		if (p_C_Invoice_ID != 0) {
			sql.append("C_Invoice_ID=").append(p_C_Invoice_ID); //Para una determinada factura
		} else {
			
			if (p_C_BPartner_ID != 0) {
				sql.append("C_BPartner_ID=").append(p_C_BPartner_ID); //Facturas relacionadas a determinado socio
			} else {
				sql.append("EXISTS (SELECT * FROM C_BPartner bp ")
						// Lama - Partner Cte
//					.append(" LEFT JOIN C_BPartner_Cte pcte ON (bp.C_BPartner_ID=pcte.C_BPartner_ID ")
//					.append(" AND pcte.AD_Client_ID = ")
//					.append(Env.getAD_Client_ID(getCtx())).append(")") //#1 FIXME
					.append("WHERE C_Invoice.C_BPartner_ID=bp.C_BPartner_ID ")
					.append(" AND bp.C_BP_Group_ID=")
					.append(p_C_BP_Group_ID).append(")"); //Facturas relacionadas as socio(s) de un determinado grupo
			}
				
			if (ONLY_AR.equals(p_APAR)) {
				sql.append(" AND IsSOTrx='Y'"); //Solo facturas de venta
			} else if (ONLY_AP.equals(p_APAR)) {
				sql.append(" AND IsSOTrx='N'"); //Solo facturas de proveedor
			}
				
			if (p_DateInvoiced_From != null && p_DateInvoiced_To != null) {
				//Si se seleccionan las dos fechas valida que la fecha de facturacion se encuentre entre estas.
				sql.append(" AND TRIM(DateInvoiced) BETWEEN ")
				.append(DB.TO_DATE(p_DateInvoiced_From, true))
				.append(" AND ")
				.append(DB.TO_DATE(p_DateInvoiced_To, true));
			} else if (p_DateInvoiced_From != null) {
				//Si solo se selecciona fecha desde, se la fecha de facturacion sea mayor igual que la fechaDesde
				sql.append(" AND TRIM(DateInvoiced) >= ")
				.append(DB.TO_DATE(p_DateInvoiced_From, true));
			} else if (p_DateInvoiced_To != null) {
				//Si solo se selecciona fecha hasta, se la fecha de facturacion sea menor igual que la fechaHasta
				sql.append(" AND TRIM(DateInvoiced) <= ")
				.append(DB.TO_DATE(p_DateInvoiced_To, true));
			}
		}
		
		//Las facturas no deben estar pagadas
		sql.append(" AND IsPaid='N' ORDER BY C_Currency_ID, C_BPartner_ID, DateInvoiced");
		
		log.finer(sql.toString());
		
		// Contamos el numero de facturas saldadas
		int counter = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery ();
			
			while (rs.next ()) {
				if (writeOff(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getInt(4), rs.getBigDecimal(6)));
					counter++;
			}
			
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} 
		catch (Exception e)	{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		try	{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		//	final
		processPayment();
		processAllocation();
		return "#" + counter;
	}	//	doIt

	/**
	 * 	Write Off
	 *	@param C_Invoice_ID invoice
	 *	@param DocumentNo doc no
	 *	@param DateInvoiced date
	 *	@param C_Currency_ID currency
	 *	@param OpenAmt open amt
	 *	@return true if written off
	 */
	private boolean writeOff (int C_Invoice_ID, String DocumentNo, Timestamp DateInvoiced, int C_Currency_ID, BigDecimal OpenAmt) {
		
		// Nothing to do: Si el  monto es cero o no hay monto
		if (OpenAmt == null || OpenAmt.signum() == 0) {
			return false;
		}
		
		// Nothing to do: Si el  monto es mayor al maximo posible
		if (OpenAmt.abs().compareTo(p_MaxInvWriteOffAmt) >= 0) {
			return false;
		}
		
		// Si es simulacion solo se muestran en pantalla las facturas que pueden ser afectadas
		if (p_IsSimulation) {
			addLog(C_Invoice_ID, DateInvoiced, OpenAmt, DocumentNo);
			return true;
		}
		
		// Invoice
		MInvoice invoice = new MInvoice(getCtx(), C_Invoice_ID, get_TrxName());
		
		// Si la factura no es de venta, es decir es de proveedor (backOffice) se cambia a negativo el monto 
		if (!invoice.isSOTrx()){
			OpenAmt = OpenAmt.negate();
		}
		
		// Si no se ha creado el Allocation o la moneda cambia se crea un nuevo
		if (m_alloc == null || C_Currency_ID != m_alloc.getC_Currency_ID()) {
			
			// Procesamos el AllocationHdr anterior
			processAllocation();
			
			// Creamos el nuevo AllocationHdr
			m_alloc = new MAllocationHdr (getCtx(), true, p_DateAcct, C_Currency_ID
					, getProcessInfo().getTitle() + " #" + getAD_PInstance_ID() + " [x]", get_TrxName());
			m_alloc.setAD_Org_ID(invoice.getAD_Org_ID());
			if (!m_alloc.save()) {
				log.log(Level.SEVERE, "Cannot create allocation header");
				return false;
			}
		}
		
		// Si se selecciono crear pago y (no se ha creado el pago o el socio de la factura es diferente al del pago o la moneda es diferente al del pago) se crea el payment		
		if (p_CreatePayment && (m_payment == null || invoice.getC_BPartner_ID() != m_payment.getC_BPartner_ID() || C_Currency_ID != m_payment.getC_Currency_ID())) {
			
			// Procesamos el pago anterior
			processPayment();
			
			// Creamos el nuevo pago
			m_payment = new MPayment(getCtx(), 0, get_TrxName());
			m_payment.setAD_Org_ID(invoice.getAD_Org_ID());
			m_payment.setC_BankAccount_ID(p_C_BankAccount_ID);
			m_payment.setTenderType(MPayment.TENDERTYPE_Check);
			m_payment.setDateTrx(p_DateAcct);
			m_payment.setDateAcct(p_DateAcct);
			m_payment.setDescription(getProcessInfo().getTitle() + " #" + getAD_PInstance_ID());
			m_payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
			m_payment.setIsReceipt(true);	//	payments are negative
			m_payment.setC_Currency_ID(C_Currency_ID);
			if (!m_payment.save()) {
				log.log(Level.SEVERE, "Cannot create payment");
				return false;
			}
		}

		// Creamos la linea con pago o sin pago relacionado
		MAllocationLine aLine = null;
		if (p_CreatePayment) {
			aLine = new MAllocationLine (m_alloc, OpenAmt, Env.ZERO, Env.ZERO, Env.ZERO);
			m_payment.setPayAmt(m_payment.getPayAmt().add(OpenAmt));
			aLine.setC_Payment_ID(m_payment.getC_Payment_ID());
		}else {
			aLine = new MAllocationLine (m_alloc, Env.ZERO,Env.ZERO, OpenAmt, Env.ZERO);
		}
		
		// Relacionamos con la factura
		aLine.setC_Invoice_ID(C_Invoice_ID);
		
		if (aLine.save()) {
			addLog(C_Invoice_ID, DateInvoiced, OpenAmt, DocumentNo);
			return true;
		}
		
		// Error
		log.log(Level.SEVERE, "Cannot create allocation line for C_Invoice_ID=" + C_Invoice_ID);
		return false;
	}	// writeOff
	
	/**
	 * 	Process Allocation
	 *	@return true if processed
	 */
	private boolean processAllocation()	{
		
		// Si no hay Allocation regresamos
		if (m_alloc == null) {
			return true;
		}
		
		// Procesamos el pago
		processPayment();
		
		// Process It
		if (m_alloc.processIt(DocAction.ACTION_Complete) &&  m_alloc.save()) {
			m_alloc = null;
			return true;
		}
		
		m_alloc = null;
		
		return false;
	}	// processAllocation

	/**
	 * 	Process Payment
	 *	@return true if processed
	 */
	private boolean processPayment() {
		
		// Si no hay Pago regresamos
		if (m_payment == null){
			return true;
		}
		
		// Process It
		if (m_payment.processIt(DocAction.ACTION_Complete) &&  m_payment.save()) {
			m_payment = null;
			return true;
		}
		
		m_payment = null;
		
		return false;
	}	// processPayment
	
}	// InvoiceWriteOff