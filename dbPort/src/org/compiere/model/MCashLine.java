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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.ecaresoft.util.ErrorList;

/**
 *	Cash Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MCashLine.java,v 1.5 2006/08/28 23:37:27 vgarcia Exp $
 * 
 * <b>Modificado: </b> $Author: vgarcia $<p>
 * <b>En :</b> $Date: 2006/08/28 23:37:27 $<p>
 * <b>Version:</b> $Revision: 1.5 $
 * 
 */
public class MCashLine extends X_C_CashLine {

	private static final long	serialVersionUID	= 1L;

	/** Static Logger */
	private static CLogger		log				= CLogger.getCLogger(MCashLine.class);

	/**
	 * Standard Constructor
	 * @param ctx context
	 * @param C_CashLine_ID id
	 */
	public MCashLine(final Properties ctx, final int C_CashLine_ID, final String trxName) {
		super(ctx, C_CashLine_ID, trxName);
		formaPago = null;
		payment = null;
		creditCardTypeName = null;
		if (C_CashLine_ID == 0) {
			// setLine (0);
			// setCashType (CASHTYPE_GeneralExpense);
			setAmount(Env.ZERO);
			setDiscountAmt(Env.ZERO);
			setWriteOffAmt(Env.ZERO);
			setIsGenerated(false);
		}
	} // MCashLine

	/**
	 * Load Constructor
	 * @param ctx context
	 * @param rs result set
	 */
	public MCashLine(final Properties ctx, final ResultSet rs, final String trxName) {
		super(ctx, rs, trxName);
	} // MCashLine

	/**
	 * Parent Cosntructor
	 * @param cash parent
	 */
	public MCashLine(final MCash cash) {
		this(cash.getCtx(), 0, cash.get_TrxName());
		setClientOrg(cash);
		setC_Cash_ID(cash.getC_Cash_ID());
		m_parent = cash;
		m_cashBook = m_parent.getCashBook();
	}	//	MCashLine
	
	/** Parent					*/
	private MCash			m_parent = null;
	/** Cash Book				*/
	private MCashBook 		m_cashBook = null;
	/** Bank Account			*/
	private MBankAccount 	m_bankAccount = null;
	/** Invoice					*/
	private MInvoice		m_invoice = null;
	/** */
	private MFormaPago formaPago = null;

	/**
	 * Add to Description
	 * @param description text
	 */
	public void addDescription(final String description) {
		final String desc = getDescription();
		if (desc == null) {
			setDescription(description);
		} else {
			setDescription(desc + " | " + description);
		}
	} // addDescription

	/**
	 * Set Invoice - no discount
	 * @param invoice invoice
	 */
	public void setInvoice(final MInvoice invoice) {
		setC_Invoice_ID(invoice.getC_Invoice_ID());
		setCashType(CASHTYPE_Invoice);
		setC_Currency_ID(invoice.getC_Currency_ID());
		// Amount
		final MDocType dt = MDocType.get(getCtx(), invoice.getC_DocType_ID());
		BigDecimal amt = invoice.getGrandTotal();
		if (X_C_DocType.DOCBASETYPE_APInvoice.equals(dt.getDocBaseType()) || X_C_DocType.DOCBASETYPE_ARCreditMemo.equals(dt.getDocBaseType())) {
			amt = amt.negate();
		}
		setAmount(amt);
		//
		setDiscountAmt(Env.ZERO);
		setWriteOffAmt(Env.ZERO);
		setIsGenerated(true);
		m_invoice = invoice;
	} //	setInvoiceLine

	/**
	 * Set Order - no discount
	 * @param order order
	 */
	public void setOrder(final MOrder order, final String trxName) {
		setCashType(CASHTYPE_Invoice);
		setC_Currency_ID(order.getC_Currency_ID());
		// Amount
		final BigDecimal amt = order.getGrandTotal();
		setAmount(amt);
		setDiscountAmt(Env.ZERO);
		setWriteOffAmt(Env.ZERO);
		setIsGenerated(true);
		//
		if (X_C_Order.DOCSTATUS_WaitingPayment.equals(order.getDocStatus())) {
			save(trxName);
			order.setC_CashLine_ID(getC_CashLine_ID());
			order.processIt(DocAction.ACTION_WaitComplete);
			order.save(trxName);
			// Set Invoice
			final MInvoice[] invoices = order.getInvoices();
			final int length = invoices.length;
			if (length > 0) // get last invoice
			{
				m_invoice = invoices[length - 1];
				setC_Invoice_ID(m_invoice.getC_Invoice_ID());
			}
		}
	} // setOrder

	/**
	 * Get Statement Date from header
	 * @return date
	 */
	public Timestamp getStatementDate() {
		return getParent().getStatementDate();
	} //	getStatementDate

	/**
	 * 	Create Line Reversal
	 *	@return new reversed CashLine
	 */
	public MCashLine createReversal() {
		MCash parent = getParent();
		if (parent.isProcessed()) { // saved
			parent = MCash.get(getCtx(), parent.getAD_Org_ID(), parent.getStatementDate(), parent.getC_Currency_ID(), get_TrxName());
		}
		//
		final MCashLine reversal = new MCashLine(parent);
		reversal.setClientOrg(this);
		reversal.setC_BankAccount_ID(getC_BankAccount_ID());
		reversal.setC_Charge_ID(getC_Charge_ID());
		reversal.setC_Currency_ID(getC_Currency_ID());
		reversal.setC_Invoice_ID(getC_Invoice_ID());
		reversal.setCashType(getCashType());
		reversal.setDescription(getDescription());
		reversal.setIsGenerated(true);
		//
		reversal.setAmount(getAmount().negate());
		if (getDiscountAmt() == null) {
			setDiscountAmt(Env.ZERO);
		} else {
			reversal.setDiscountAmt(getDiscountAmt().negate());
		}
		if (getWriteOffAmt() == null) {
			setWriteOffAmt(Env.ZERO);
		} else {
			reversal.setWriteOffAmt(getWriteOffAmt().negate());
		}
		reversal.addDescription("(" + getLine() + ")");
		return reversal;
	} // reverse

	/**
	 * Get Cash (parent)
	 * @return cash
	 */
	public MCash getParent() {
		if (m_parent == null) {
			m_parent = new MCash(getCtx(), getC_Cash_ID(), get_TrxName());
		}
		return m_parent;
	} // getCash

	/**
	 * Get CashBook
	 * @return cash book
	 */
	public MCashBook getCashBook() {
		if (m_cashBook == null) {
			m_cashBook = MCashBook.get(getCtx(), getParent().getC_CashBook_ID());
		}
		return m_cashBook;
	} // getCashBook

	/**
	 * Get Bank Account
	 * @return bank account
	 */
	public MBankAccount getBankAccount() {
		if (m_bankAccount == null && getC_BankAccount_ID() != 0) {
			m_bankAccount = MBankAccount.get(getCtx(), getC_BankAccount_ID());
		}
		return m_bankAccount;
	} // getBankAccount

	/**
	 * Get Invoice
	 * @return invoice
	 */
	public MInvoice getInvoice() {
		if (m_invoice == null && getC_Invoice_ID() != 0) {
			m_invoice = MInvoice.get(getCtx(), getC_Invoice_ID(), get_TrxName()); // expert : gisela lee : enviar nombre de transaccion
		}
		return m_invoice;
	} // getInvoice

	/**************************************************************************
	 * Before Delete
	 * @return true/false
	 */
	@Override
	protected boolean beforeDelete() {
		// Cannot Delete generated Invoices
		final Boolean generated = (Boolean) get_ValueOld("IsGenerated");
		if (generated != null && generated.booleanValue()) {
			if (get_ValueOld("C_Invoice_ID") != null) {
				log.warning("Cannot delete line with generated Invoice");
				return false;
			}
		}
		return true;
	} // beforeDelete

	/**
	 * After Delete
	 * @param success
	 * @return true/false
	 */
	@Override
	protected boolean afterDelete(final boolean success) {
		if (!success) {
			return success;
		}
		return updateHeader();
	} // afterDelete

	/**
	 * 	Before Save
	 *	@param newRecord
	 *	@return true/false
	 */
	@Override
	protected boolean beforeSave (final boolean newRecord)
	{
		// Cannot change generated Invoices
		if (is_ValueChanged("C_Invoice_ID")) {
			final Object generated = get_ValueOld("IsGenerated");
			if (generated != null && ((Boolean) generated).booleanValue()) {
				log.warning("Cannot change line with generated Invoice");
				return false;
			}
		}

		// Verify CashType
		if (CASHTYPE_Invoice.equals(getCashType()) && getC_Invoice_ID() == 0) {
			setCashType(CASHTYPE_GeneralExpense);
		}
		if (CASHTYPE_BankAccountTransfer.equals(getCashType()) && getC_BankAccount_ID() == 0) {
			setCashType(CASHTYPE_GeneralExpense);
		}
		if (CASHTYPE_Charge.equals(getCashType()) && getC_Charge_ID() == 0) {
			setCashType(CASHTYPE_GeneralExpense);
		}

		final boolean verify = newRecord
			|| is_ValueChanged("CashType")
			|| is_ValueChanged("C_Invoice_ID")
			|| is_ValueChanged("C_BankAccount_ID");
		if (verify) {
			// Verify Currency
			if (CASHTYPE_BankAccountTransfer.equals(getCashType())) {
				setC_Currency_ID(getBankAccount().getC_Currency_ID());
			} else if (CASHTYPE_Invoice.equals(getCashType())) {
				setC_Currency_ID(getInvoice().getC_Currency_ID());
			} else if (!CASHTYPE_CuentasXCobrar.equals(getCashType())) {//Card #1170 - Lama
				setC_Currency_ID(getCashBook().getC_Currency_ID());
			}

			// Set Organization
			if (CASHTYPE_BankAccountTransfer.equals(getCashType())) {
				setAD_Org_ID(getBankAccount().getAD_Org_ID());
			} else if (CASHTYPE_Invoice.equals(getCashType())) {
				setAD_Org_ID(getCashBook().getAD_Org_ID());
			}
			// otherwise (charge) - leave it
			// Enforce Org
			if (getAD_Org_ID() == 0) {
				setAD_Org_ID(getParent().getAD_Org_ID());
			}
		}

		/**	General fix of Currency
		UPDATE C_CashLine cl SET C_Currency_ID = (SELECT C_Currency_ID FROM C_Invoice i WHERE i.C_Invoice_ID=cl.C_Invoice_ID) WHERE C_Currency_ID IS NULL AND C_Invoice_ID IS NOT NULL;
		UPDATE C_CashLine cl SET C_Currency_ID = (SELECT C_Currency_ID FROM C_BankAccount b WHERE b.C_BankAccount_ID=cl.C_BankAccount_ID) WHERE C_Currency_ID IS NULL AND C_BankAccount_ID IS NOT NULL;
		UPDATE C_CashLine cl SET C_Currency_ID = (SELECT b.C_Currency_ID FROM C_Cash c, C_CashBook b WHERE c.C_Cash_ID=cl.C_Cash_ID AND c.C_CashBook_ID=b.C_CashBook_ID) WHERE C_Currency_ID IS NULL;
		 **/

		// Get Line No
		if (getLine() == 0) {
			final String sql = "SELECT COALESCE(MAX(Line),0)+10 FROM C_CashLine WHERE C_Cash_ID=?";
			final int ii = DB.getSQLValue(get_TrxName(), sql, getC_Cash_ID());
			setLine(ii);
		}

		return true;
	}	//	beforeSave

	/**
	 * 	After Save
	 *	@param newRecord
	 *	@param success
	 */
	@Override
	protected boolean afterSave (final boolean newRecord, final boolean success) {
		if (!success) {
			return success;
		}
		return updateHeader();
	}	//	afterSave

	/**
	 * 	Update Cash Header.
	 * 	Statement Difference, Ending Balance
	 *	@return true if success
	 */
	private boolean updateHeader() {
		StringBuilder sql = new StringBuilder("UPDATE C_Cash c")
			.append(" SET StatementDifference=")
			.append("(SELECT COALESCE(SUM(currencyConvert(cl.Amount, cl.C_Currency_ID, cb.C_Currency_ID, c.DateAcct, null, c.AD_Client_ID, c.AD_Org_ID)),0) ")
			.append("FROM C_CashLine cl, C_CashBook cb ")
			.append("WHERE cb.C_CashBook_ID=c.C_CashBook_ID")
			.append(" AND cl.C_Cash_ID=c.C_Cash_ID) ")
			.append("WHERE C_Cash_ID=? ");
//			+ getC_Cash_ID());
		int no = DB.executeUpdate(sql.toString(), getC_Cash_ID(), get_TrxName());
		if (no != 1) {
			log.warning("Difference #" + no);
		}
		//	Ending Balance
		sql = new StringBuilder("UPDATE C_Cash")
			.append(" SET EndingBalance = BeginningBalance + StatementDifference ")
			.append("WHERE C_Cash_ID=? ");
			//+ getC_Cash_ID();
		no = DB.executeUpdate(sql.toString(), getC_Cash_ID(), get_TrxName());
		if (no != 1) {
			log.warning("Balance #" + no);
		}
		return no == 1;
	}	//	updateHeader

//	//expert
//	/**
//	 * Este metodo se utiliza en la Cancelacion de la cuenta Paciente
//	 * 
//	 * @param ctaPac_ID
//	 * @param client_ID
//	 * @param trxName
//	 * @return
//	 */
//	public static boolean havePayment(final long ctaPac_ID, final long client_ID, final String trxName){
//		boolean have=false;
//
//		final StringBuilder sql = new StringBuilder(); 
//		sql.append("SELECT CL.AMOUNT FROM C_CASHLINE CL WHERE CL.EXME_CTAPAC_ID = ? ");
//		sql.append("AND CL.AD_CLIENT_ID = ?");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//
//			pstmt.setLong(1,ctaPac_ID);
//			pstmt.setLong(2,client_ID);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				have=true;
//			}
//		} catch (final SQLException e) {
//			log.log(Level.SEVERE,sql.toString(),e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return have;
//	}

//	/**
//	 * Noelia: Metodo que regresa una lista con todos los No de recibo de Pago
//	 * Para una determinada Cuenta Paciente.
//	 * @param CtaPacID: Identificador de la cuenta paciente
//	 * @return ArrayList<String>: Lista con los no de recibo de Pago
//	 */
//	public static ArrayList<String> getLstReciboNo(final int ctaPacID, final String trxName){
//
//		final ArrayList<String> lstReciboNo = new ArrayList<String>();
//
//		final StringBuilder sql = new StringBuilder("SELECT cl.reciboNo FROM C_CashLine cl ")
//		.append("INNER JOIN C_Invoice i ON cl.C_Invoice_ID = i.C_Invoice_ID AND i.IsActive = 'Y' ")
//		.append("INNER JOIN EXME_CtaPacExt cpe ON i.EXME_CtaPacExt_ID = cpe.EXME_CtaPacExt_ID AND cpe.IsActive = 'Y' ")
//		.append("WHERE cl.IsActive = 'Y' AND cl.EXME_CtaPac_ID IS NULL AND cpe.EXME_CtaPac_ID = ? ");
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, ctaPacID);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				lstReciboNo.add(rs.getString("reciboNo"));
//			}
//		} catch (final SQLException e) {
//			log.log(Level.SEVERE,sql.toString(),e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return lstReciboNo;
//	}
	
	public static class LinesToPost {
		private int	recordId	= 0;
		private int	tableId		= 0;

		public LinesToPost(int tableId, int recordId) {
			this.recordId = recordId;
			this.tableId = tableId;
		}

		/**
		 * @param cashId Identificador del operador
		 * @return Lista de Ids y tableIds a postear
		 */
		public static List<LinesToPost> getToPost(final Properties ctx, final int cashId) {

			final List<LinesToPost> list = new ArrayList<LinesToPost>();

			final StringBuilder sql = new StringBuilder();
			sql.append(" SELECT DISTINCT * FROM ( ");
			// facturas
			sql.append(" SELECT i.C_INVOICE_ID as recordId ");
			sql.append(" , ? as tableId ");
			sql.append(" FROM C_Invoice i ");
			sql.append(" WHERE i.Posted='N' AND i.Processed='Y' AND i.IsActive='Y' ");
			sql.append(" AND i.DocStatus IN ('CO','CL') ");
			sql.append(" AND i.C_Cash_ID=? ");

			sql.append(" UNION  ");
			// pagos
			sql.append(" SELECT i.C_Payment_ID as recordId ");
			sql.append(" , ? as tableId ");
			sql.append(" FROM C_Payment i ");
			sql.append(" INNER JOIN C_CashLine cl ON (i.C_Payment_ID=cl.C_Payment_ID) ");
			sql.append(" WHERE i.Posted='N' AND i.IsActive='Y' ");
			sql.append(" AND i.DocStatus IN ('CO','CL') ");
			sql.append(" AND cl.C_Cash_ID=? ");

			sql.append(" UNION  ");
			// allocations
			sql.append(" SELECT i.C_AllocationHdr_ID as recordId ");
			sql.append(" , ? as tableId ");
			sql.append(" FROM C_AllocationHdr i ");
			sql.append(" INNER JOIN C_AllocationLine il ON (i.C_AllocationHdr_ID=il.C_AllocationHdr_ID) ");
			sql.append(" LEFT JOIN C_CashLine cl ON (il.C_Payment_ID=cl.C_Payment_ID OR il.C_CashLine_ID=cl.C_CashLine_ID) ");
			sql.append(" LEFT JOIN C_Invoice inv ON (il.C_Invoice_ID=inv.C_Invoice_ID) ");//RQM #5303
			sql.append(" WHERE i.Posted='N' AND i.IsActive='Y' ");
			sql.append(" AND i.DocStatus IN ('CO','CL') ");
			sql.append(" AND ( cl.C_Cash_ID=? OR inv.C_Cash_ID=? ) ");
			
			sql.append(") AS dual ");

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				int i = 0;
				pstmt.setInt(++i, MInvoice.Table_ID);
				pstmt.setInt(++i, cashId);
				pstmt.setInt(++i, MPayment.Table_ID);
				pstmt.setInt(++i, cashId);
				pstmt.setInt(++i, MAllocationHdr.Table_ID);
				pstmt.setInt(++i, cashId);
				pstmt.setInt(++i, cashId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					list.add(new LinesToPost(rs.getInt("tableId"), rs.getInt("recordId")));
				}
			} catch (final SQLException e) {
				log.log(Level.SEVERE, sql.toString(), e);
			} finally {
				DB.close(rs, pstmt);
			}

			return list;
		}

		public int getRecordId() {
			return recordId;
		}

		public int getTableId() {
			return tableId;
		}
	}

	/**
	 * Obtener los id a postear
	 * Dependiendo del operador
	 * @param cashId Identificador del operador
	 * @return str Lista de los identificadores
	 * FIXME : genera muchos strings, cashline solo tiene un invoice o payment, por lo que la lista siempre tendra 0,999 o 999,0
	 * FIXME : C_CashLine.C_Invoice_ID = Nota Remision, usar Allocation
	 * @deprecated use LinesToPost#getToPost
	 */
	public static List<String> getToPost(final int cashId,final String trxName) {

		final List<String> str = new ArrayList<String>();

		final StringBuilder sql = new StringBuilder();

		sql.append("SELECT DISTINCT C.C_INVOICE_ID,C.C_PAYMENT_ID FROM C_CASHLINE C ")
		.append("WHERE (c.C_PAYMENT_ID IN(SELECT P.C_PAYMENT_ID FROM C_PAYMENT P WHERE P.POSTED = 'N' AND P.ISACTIVE = 'Y') ")
		.append("OR c.C_INVOICE_ID IN (SELECT I.C_INVOICE_ID FROM C_INVOICE I WHERE I.POSTED = 'N' AND I.PROCESSED = 'Y' AND I.ISACTIVE = 'Y')) ")
		.append("AND c.C_Cash_Id = ? AND c.Isactive = 'Y' ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, cashId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				str.add(String.valueOf(rs.getInt("C_Invoice_Id")) + "," +
					String.valueOf(rs.getInt("C_Payment_Id")));
			}
		} catch (final SQLException e) {
			log.log(Level.SEVERE,sql.toString(),e);
		} finally {
			DB.close(rs, pstmt);
		}

		return str;
	}

	/** @return Obtenemos la forma de pago seleccionada  */
	public MFormaPago getFormaPago() {
		if (formaPago == null || formaPago.getEXME_FormaPago_ID() == 0) {
			formaPago = new MFormaPago(getCtx(), getEXME_FormaPago_ID(), get_TrxName());
		}
		return formaPago;
	}

	public void setFormaPago(final MFormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public MCash getM_parent() {
		if (m_parent == null && getC_Cash_ID() > 0) {
			m_parent = new MCash(getCtx(), getC_Cash_ID(), get_TrxName());
		}
		return m_parent;
	}

	public void setM_parent(final MCash m_parent) {
		this.m_parent = m_parent;
	}
	
	private MPayment payment = null;
	private String creditCardTypeName = null;
    
    private MEXMECtaPac m_CtaPac = null; 
	private boolean facturado;
	// Almacenar el AdjCode
	private int		adjCodeID;
	// Almacenar el AdjType para evitar cargar otros objetos
	private String	adjType;

	/**@return Obtenemos el pago seleccionado */
	public MPayment getPayment() {
		if (payment == null || payment.getC_Payment_ID() == 0) {
			payment = new MPayment(getCtx(), getC_Payment_ID(), get_TrxName());
		}
		return payment;
	}

	/** @return Obtenemos la forma de pago seleccionada */
	public String getCreditCardTypeName() {
		if (getCreditCardType() != null) {
			creditCardTypeName = MRefList.getListName(getCtx(), CREDITCARDTYPE_AD_Reference_ID, getCreditCardType());
		}
		return creditCardTypeName;
	}

	/** @return Obtenemos la cuenta paciente */
	public MEXMECtaPac getCtaPac() {
		if (m_CtaPac == null || m_CtaPac.getEXME_CtaPac_ID() == 0) {
			m_CtaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(), get_TrxName());
		}
		return m_CtaPac;
	}

	/**  @param ctaPac Le asignamos la cuenta paciente. */
	public void setCtaPac(MEXMECtaPac ctaPac) {
		if (ctaPac != null) {
			setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
			m_CtaPac = ctaPac;
		}
	}

	/** @return Define si el pago ya esta relacionado a una factura o no */
	public boolean isFacturado() {
		if(getC_Payment_ID()>0){
			// saber en que 
			if(!getPayment().isAllocated()){
				facturado = !getPayment().getAllocations(get_TrxName()).isEmpty();
			} else {
				facturado = getPayment().isAllocated();
			}
		}
		return facturado;
	}

	public void setFacturado(boolean facturado) {
		this.facturado = facturado;
	}
	
	public int getAdjCodeID() {
		return adjCodeID;
	}

	public void setAdjCodeID(int adjCodeID) {
		this.adjCodeID = adjCodeID;
	}

	public String getAdjType() {
		return adjType;
	}

	public void setAdjType(String adjType) {
		this.adjType = adjType;
	}
	
	/**
     * Devuelve un arreglo con las facturas destinadas a la apertura/cierre de la caja
     * @param ctx
     * @param C_CashBook_ID Caja
     * @param C_Cash_ID         Linea de Apertura de caja
     * @param trxName
     * @return
     */
    public static String recDevol(Properties ctx, int C_Payment_ID,String trxName){
        final StringBuilder  sql = new StringBuilder();
        sql.append("SELECT recibono FROM c_cashline WHERE c_payment_id=?");
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_CashLine"));
    	return DB.getSQLValueString(trxName, sql.toString(), C_Payment_ID);
    }
    
    /**
	 * Pagos de Facturas para caja
	 * @param ctx
	 * @param invoiceID
	 * @return
	 */
	public static List<PagoLine> getPaymentsInv(final Properties ctx, final int invoiceID) {
//		 MEXMECashLine.getOfInvoiceList(ctx, invoiceID, null);
		return getPayments(ctx, getOfInvoiceList(ctx, invoiceID, null));
	}
	
	/**
	 * Pagos de cuenta para caja
	 * @param ctx
	 * @param ctaPacID
	 * @return
	 */
	public static List<PagoLine> getPaymentsCta(final Properties ctx, final int ctaPacID) {
		return getPayments(ctx, getOfCtaPacCaptPago(ctx, ctaPacID, null));
	}
	
	/**
	 * Listado de Pagos
	 * @param ctx
	 * @param cashLines
	 * @return
	 */
	public static List<PagoLine> getPayments(final Properties ctx, final List<? extends MCashLine> cashLines) {
		List<PagoLine> payments = new ArrayList<PagoLine>();
		try {
			// buscar si existen pagos asociados a la factura en C_CashLine
			if (cashLines != null && !cashLines.isEmpty()) {
				for (int i = 0; i < cashLines.size(); i++) {
					final MCashLine cashline = cashLines.get(i);
					payments.add(cashline.createFrom(i));
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "WCaptPago.getPayments", e);
		}
		return payments;
	}
	
	public PagoLine createFrom(int position) throws Exception {
		// si hay un anticipo asociado
		final PagoLine linea = new PagoLine();
		linea.setPaymentID(this.getC_Payment_ID());
		linea.setPosition(position);
		MFormaPago fpago =this.getFormaPago();
		linea.setFormaPagoID(fpago.getEXME_FormaPago_ID());
		linea.setFormaPagoName(fpago.getName());
		linea.setFormaPago(fpago.getName());
		linea.setPaymentRule(fpago.getPaymentRule());
		linea.setMontoRecibido(this.getAmount());
		linea.setMontoAplicado(this.getAmount());
		linea.setMonedaID(this.getC_Currency_ID());
		linea.setMonedaName(MEXMECurrency.getMonedaDescription(getCtx(), linea.getMonedaID()));
		linea.setCreditCardType(this.getCreditCardType());
		linea.setCreditCardNumber(this.getCreditCardNumber());
		linea.setCreditCardVV(this.getCreditCardVV());
		linea.setCreditCardExpMM(this.getCreditCardExpMM());
		linea.setCreditCardExpYY(this.getCreditCardExpYY());
		linea.setA_Name(this.getA_Name());
		linea.setRoutingNo(this.getRoutingNo());
		linea.setAccountNo(this.getAccountNo());
		linea.setCheckNo(this.getCheckNo());
		linea.setMicr(this.getMicr());
		linea.setFacturado(this.isFacturado());
		linea.setFecha(this.getCreated());
		if (linea.getCreditCardType() != null){
			linea.setCreditCardTypeName(MRefList.getListName(getCtx(), MCashLine.CREDITCARDTYPE_AD_Reference_ID, linea.getCreditCardType()));
		}
		linea.setCashline(this);//card 1201 asanchez
		return linea;
	}
	
	
	public BigDecimal getAmountRounded2() {
		return super.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Crea la linea de caja a partir de una factura
	 * 
	 * @param cashline MCashLine
	 * @param orgTrxId 
	 * @return MPayment
	 */
	public MPayment createPayment(MInvoice invoice, int orgTrxId) {
		MPayment payment = null;
		if (invoice != null //
			&& StringUtils.indexOfAny(getFormaPago().getPaymentRule(), //
				new String[] { MFormaPago.PAYMENTRULE_OnCredit, MFormaPago.PAYMENTRULE_Cash }) == -1) {

			// generamos el pago en C_Payment
			payment = createPayment(invoice.getC_BPartner_ID(), orgTrxId);
			processPayment(payment, MPayment.TYPE_InvoicePayment);
		}
		return payment;
	}
	
	/**
	 * Generamos el pago por concepto de anticipo a cuenta paciente
	 * 
	 * @param cashline MCashLine - La linea del movimiento del pago original
	 * @param clientID
	 * @param orgTrxId 
	 * @return MPayment con la informacion insertada
	 */
	public MPayment createPayment(final int clientID, final int orgTrxId) {
		final MConfigPre configPre = MConfigPre.get(getCtx(), null);
		if (configPre == null) { //FIXME: 1 acceso de de BD por cada linea de pago
			throw new MedsysException("error.caja.configPre");
		}
		final MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
		payment.setDateTrx(Env.getCurrentDate());
		payment.setIsReceipt(true);
		payment.setC_DocType_ID(MEXMEDocType.getOfName(getCtx(), Constantes.AR_RECEIPT, null));
		payment.setTrxType(MPayment.TRXTYPE_Sales);
		payment.setC_BankAccount_ID(configPre.getC_BankAccount_ID());
		payment.setC_BPartner_ID(clientID);

		if (MFormaPago.PAYMENTRULE_Cash.equals(getFormaPago().getPaymentRule())) {
			payment.setTenderType(MPayment.TENDERTYPE_Cash);
		} else if (MFormaPago.PAYMENTRULE_Check.equals(getFormaPago().getPaymentRule())) {
			payment.setTenderType(MPayment.TENDERTYPE_Check);
			payment.setRoutingNo(getRoutingNo());
			payment.setAccountNo(getAccountNo());
			payment.setCheckNo(getCheckNo());
			payment.setMicr(getMicr());
			payment.setA_Name(getA_Name());
		} else if (MFormaPago.PAYMENTRULE_CreditCard.equals(getFormaPago().getPaymentRule())) { 
			payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
			payment.setCreditCardType(getCreditCardType());
			payment.setCreditCardNumber(getCreditCardNumber());
			payment.setCreditCardVV(getCreditCardVV());
			payment.setCreditCardExpMM(getCreditCardExpMM());
			payment.setCreditCardExpYY(getCreditCardExpYY());
			payment.setA_Name(getA_Name());
		} else if (MFormaPago.PAYMENTRULE_Other.equals(getFormaPago().getPaymentRule())) { 
			payment.setTenderType(MPayment.TENDERTYPE_Other);
		} else {
			payment.setTenderType(MPayment.TENDERTYPE_Other);
		}

		payment.setC_Currency_ID(getC_Currency_ID());
		payment.setPayAmt(getAmount());
		payment.setOProcessing("N");
		payment.setEXME_CtaPac_ID(getEXME_CtaPac_ID());
		payment.setReciboNo(getReciboNo());
		// Organizacion transaccional del operador que hizo la venta
		payment.setAD_OrgTrx_ID(orgTrxId);
		payment.setIsAllocated (true); // PERMITE NO SELECCIONARLO DESDE WAllocation por que Es un anticipo a cuenta paciente
		if (!payment.save()) {
			throw new MedsysException();
		}
		return payment;
	}
	
	/**
	 * Prepara, completa, guarda el pago, asigna el ID a cashline
	 * 
	 * @param cashline
	 * @param payment
	 * @param type
	 */
	public void processPayment(MPayment payment, String type) {
		payment.setType(type);
		payment.prepareIt();
		payment.setDocStatus(payment.completeIt());
		if (DocAction.STATUS_Completed.equals(payment.getDocStatus())) {
			if (!payment.save()) {
				throw new MedsysException();
			}
		} else {
			throw new MedsysException(payment.getProcessMsg());
		}
		setC_Payment_ID(payment.getC_Payment_ID());
	}
	
	/**
     * Obtenemos la distribuciones del pago para la factura especificada
     * @param ctx El contexto de la aplicacion
     * @param C_Invoice_ID El identificador de la factura
     * @param trxName El nombre de la transaccion
     * @return Un objeto de tipo MEXMECashLine
     */
	public static List<MCashLine> getOfInvoiceList(Properties ctx, int C_Invoice_ID, String trxName) {
		return getOfInvoiceList(ctx, C_Invoice_ID, false, trxName);
	}
	/**
     * Obtenemos la distribuciones del pago para la factura especificada
     * @param ctx El contexto de la aplicacion
     * @param C_Invoice_ID El identificador de la factura
     * @param trxName El nombre de la transaccion
     * @return Un objeto de tipo MEXMECashLine
     */
	public static List<MCashLine> getOfInvoiceList(Properties ctx, int C_Invoice_ID, boolean excludePrepayment, String trxName) {
		// sql.append("SELECT * FROM C_CashLine WHERE C_Invoice_ID = ?")
		// .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_CashLine"))
		// .append("ORDER BY C_CashLine_ID");
		final List<MCashLine> list = new ArrayList<MCashLine>();
		final StringBuilder sql = new StringBuilder();

		sql.append("SELECT DISTINCT * FROM ( \n");
		
		sql.append("SELECT DISTINCT C.* ");
		sql.append("FROM C_AllocationLine l  ");
		sql.append("INNER JOIN C_CashLine c ON c.C_CashLine_id=l.c_cashline_id ");
		sql.append("WHERE l.C_Invoice_ID=? ");
		sql.append("AND l.isActive='Y'  ");
		sql.append("\n UNION \n");
		
		sql.append("SELECT DISTINCT C.* ");
		sql.append("FROM C_CashLine c  ");
		sql.append("INNER JOIN C_Invoice i ON (i.C_Invoice_id=? AND ");
		sql.append("      (i.C_Invoice_id=c.c_Invoice_id OR i.Ref_Invoice_Sales_ID=c.c_Invoice_id)) ");
		sql.append("WHERE c.isActive='Y'  ");
		sql.append("\n UNION \n");
		
		sql.append("SELECT DISTINCT C.* ");
		sql.append("FROM C_AllocationLine l  ");
		sql.append("INNER JOIN C_Payment p ON l.c_payment_id=p.c_payment_id AND p.type in (?,?) ");
		sql.append("INNER JOIN C_CashLine c ON p.c_payment_id=c.c_payment_id ");
		sql.append("WHERE l.C_Invoice_ID=? ");
		sql.append("AND l.isActive='Y'  ");
		
		sql.append("\n ) as C_CashLine "); 
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", Table_Name, "C_CashLine"));
		// Ticket #07429 - Se quitan anticipos
		if(excludePrepayment) {
			sql.append("AND C_CashLine.EXME_CtaPac_ID IS NULL ");
		}
		sql.append("ORDER BY C_Cashline_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_Invoice_ID);
			pstmt.setInt(2, C_Invoice_ID);
			pstmt.setString(3, MPayment.TYPE_InvoicePayment);
			pstmt.setString(4, MPayment.TYPE_AdvancePayment);
			pstmt.setInt(5, C_Invoice_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MCashLine(ctx, rs, trxName));
			}
		} catch (final SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;

	}
	
	 /**
     * Obtenemos la distribuciones del pago para la cuenta paciente especificada
     * @param ctx El contexto de la aplicacion
     * @param EXME_CtaPac_ID El identificador de la cuenta paciente
     * @param trxName El nombre de la transaccion
     * @return Un objeto de tipo MEXMECashLine
     */
    public static List <MCashLine> getOfCtaPac(Properties ctx, int EXME_CtaPac_ID, String where, String trxName) {
    	List <MCashLine>lista = new ArrayList<MCashLine>();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
        
        sql.append("select c.*, coalesce(p.c_invoice_id,0) as facturado, cc.c_charge_id as adjtype_id, cc.type as adjType ")
           .append("from EXME_CTAPAC cp ")
           .append("inner join C_CASHLINE c on c.EXME_CTAPAC_ID = cp.EXME_CTAPAC_ID ")
           .append("inner join C_PAYMENT p on c.C_PAYMENT_ID = p.C_PAYMENT_ID ")
           .append("LEFT join EXME_CTAPACPAG cpp on cpp.C_PAYMENT_ID = p.C_PAYMENT_ID ")
           .append("LEFT join EXME_CTAPACEXT e on cpp.EXME_CTAPACEXT_ID = e.EXME_CTAPACEXT_ID ")
           .append(" left join c_allocationline al on al.c_payment_id = p.c_payment_id ")
           .append(" left join c_charge cc on cc.c_charge_id = p.c_charge_id ")
           .append("where cp.EXME_CTAPAC_ID = ? ");  
        if(where != null)
        	sql.append(where);
        sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","EXME_CtaPac", "cp"))
           .append(" ORDER BY p.c_payment_id ");
        
        try {
            pstmt = DB.prepareStatement(sql.toString(),trxName);
            
            pstmt.setInt(1,EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MCashLine cashLine = new MCashLine(ctx, rs, trxName);
			    cashLine.setFacturado(new Boolean (rs.getInt("facturado")>0 ? true : false));
			    cashLine.setAdjCodeID(rs.getInt("adjtype_id"));
			    cashLine.setAdjType(rs.getString("adjType"));
			    lista.add(cashLine);
			}
        } catch (Exception e) {

			log.log(Level.SEVERE, "", e);
			
		}finally {
			DB.close(rs, pstmt);
		}
    	return lista;
    }
    
    
    /**
     * Obtenemos la distribuciones del pago para la cuenta paciente especificada
     * @param ctx El contexto de la aplicacion
     * @param EXME_CtaPac_ID El identificador de la cuenta paciente
     * @param trxName El nombre de la transaccion
     * @return Un objeto de tipo MEXMECashLine
     */
    public static List<MCashLine> getOfCtaPacCaptPago(Properties ctx, int EXME_CtaPac_ID, String trxName) {
		return new Query(ctx, Table_Name, " EXME_CTAPAC_ID=? AND C_PAYMENT_ID IS NOT NULL ", trxName)//
			.setOnlyActiveRecords(true)// ?
			.setParameters(EXME_CtaPac_ID)//
			.addAccessLevelSQL(true)//
			.setOrderBy(" C_CashLine_ID DESC ")//
//			.setClass(MEXMECashLine.class)//
			.list();
        
//        .append(" SELECT * FROM C_CashLine WHERE IsActive = 'Y'  ")
//        .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ",X_C_CashLine.Table_Name))
//        .append(" AND EXME_CTAPAC_ID = ?  ")
//        .append(" AND C_PAYMENT_ID is not null ")
//        .append(" ORDER BY C_CashLine_ID DESC    ");
    }
    
    /**
     * Obtenemos la linea cashline a partir de un payment_id  
     * @param ctx
     * @param C_CashBook_ID Caja
     * @param C_Cash_ID         Linea de Apertura de caja
     * @param trxName
     * @return
     */
    public static MCashLine getOfPayment(Properties ctx, int C_Payment_ID, String trxName){
//    	String sql =  " SELECT * FROM c_cashline WHERE  C_Payment_ID= ? " ;
//    	sql = MEXMELookupInfo.addAccessLevelSQL(ctx, sql, "C_CashLine");
    	return new Query(ctx, Table_Name, " C_CashLine.C_Payment_ID=? ", trxName)//
//		.setOnlyActiveRecords(true)// ??
		.setParameters(C_Payment_ID)//
		.addAccessLevelSQL(true)//
//		.setClass(MEXMECashLine.class)//
		.first();
    }
    
    public static MCashLine copyFrom(final MCashLine from, final int C_Cash_ID, final String trxName) {
		final MCashLine to = new MCashLine(from.getCtx(), 0, null);
		to.set_TrxName(trxName);
		PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.setC_Cash_ID(C_Cash_ID);
		to.setProcessed(false);
		return to;
	}

	//CARD #1299
	/**
	 * Prepara, completa, guarda el pago, asigna el ID a cashline
	 * 
	 * @param cashline
	 * @param payment
	 * @param type
	 */
	public MPayment processPayment(final int clientID, final int orgTrxId, final String type) {
		final ErrorList errorList = new ErrorList();
		final MPayment payment = createPayment(clientID, orgTrxId);
		payment.setType(type);
		payment.startWorkflow(errorList, DocAction.ACTION_Complete, DocAction.STATUS_Completed, get_TrxName());
		if(!errorList.isEmpty()){
			throw new MedsysException(errorList.toString());
		}
		setC_Payment_ID(payment.getC_Payment_ID());
		return payment;
	}
	
	
    /** crear el pago negativo por devolución */
    public MPayment createPayment(final String trxName){
		final MPayment devolPayment = MPayment.copyFrom(getPayment(), 0, trxName);
		devolPayment.setAmount(getAmount());
		devolPayment.setPayAmt(getAmount());
		if(!devolPayment.save(trxName)){
			throw new MedsysException();
		}
		return devolPayment;
	}
    
	/** Cobros de facturas */
    public static List<MCashLine> getDirectPayments(final Properties ctx,
			final int CinvoiceID, final int CSalesID, final String trxName) {
		return new Query(ctx, X_C_CashLine.Table_Name, " C_Invoice_ID IN ( ?, ? ) ", trxName)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setParameters(CinvoiceID,CSalesID)
		.list();
	}
}	//	MCashLine
