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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.apps.form.BeanPaySelect;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.WebEnv;

import com.ecaresoft.util.ErrorList;


/**
 *	Allocation Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MAllocationLine.java,v 1.7 2006/08/23 00:43:45 mrojas Exp $
 */
public class MAllocationLine extends X_C_AllocationLine
{

	/** serialVersionUID */
	private static final long serialVersionUID = 789298693765971647L;
	/** Variables miembro que indica el pago */
	private MPayment mPayment = null;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_AllocationLine_ID id
	 *	@param trxName name
	 */
	public MAllocationLine (Properties ctx, int C_AllocationLine_ID, String trxName)
	{
		super (ctx, C_AllocationLine_ID, trxName);
		if (C_AllocationLine_ID == 0)
		{
		//	setC_AllocationHdr_ID (0);
			setAmount (Env.ZERO);
			setDiscountAmt (Env.ZERO);
			setWriteOffAmt (Env.ZERO);
			setOverUnderAmt(Env.ZERO);
		}	
	}	//	MAllocationLine

	/**
	 * 	Load Constructor
	 *	@param ctx ctx
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MAllocationLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAllocationLine

	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 */
	public MAllocationLine (MAllocationHdr parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setC_AllocationHdr_ID(parent.getC_AllocationHdr_ID());
		m_parent = parent;
		set_TrxName(parent.get_TrxName());
	}	//	MAllocationLine

	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 *	@param Amount amount
	 *	@param DiscountAmt optional discount
	 *	@param WriteOffAmt optional write off
	 *	@param OverUnderAmt over/underpayment
	 */
	public MAllocationLine (MAllocationHdr parent, BigDecimal Amount, 
		BigDecimal DiscountAmt, BigDecimal WriteOffAmt, BigDecimal OverUnderAmt)
	{
		this (parent);
		setAmount (Amount);
		setDiscountAmt (DiscountAmt == null ? Env.ZERO : DiscountAmt);
		setWriteOffAmt (WriteOffAmt == null ? Env.ZERO : WriteOffAmt);
		setOverUnderAmt (OverUnderAmt == null ? Env.ZERO : OverUnderAmt);
	}	//	MAllocationLine
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MAllocationLine.class);
	/**	Invoice info			*/
	private MInvoice		m_invoice = null; 
	/** Allocation Header		*/
	private MAllocationHdr	m_parent = null;
	/** ctaPacID */
	private int ctaPacID = 0;
	
	
	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MAllocationHdr getParent()
	{
		if (m_parent == null)
			m_parent = new MAllocationHdr (getCtx(), getC_AllocationHdr_ID(), get_TrxName());
		return m_parent;
	}	//	getParent
	
	/**
	 * 	Set Parent
	 *	@param parent parent
	 */
	protected void setParent (MAllocationHdr parent)
	{
		m_parent = parent;
	}	//	setParent
	
	/**
	 * 	Get Parent Trx Date
	 *	@return date trx
	 */
	public Timestamp getDateTrx ()
	{
		return getParent().getDateTrx ();
	}	//	getDateTrx
	
	/**
	 * 	Set Document Info
	 *	@param C_BPartner_ID partner
	 *	@param C_Order_ID order
	 *	@param C_Invoice_ID invoice
	 */
	public void setDocInfo (int C_BPartner_ID, int C_Order_ID, int C_Invoice_ID)
	{
		setC_BPartner_ID(C_BPartner_ID);
		setC_Order_ID(C_Order_ID);
		setC_Invoice_ID(C_Invoice_ID);
	}	//	setDocInfo
	
	/**
	 * 	Set Payment Info
	 *	@param C_Payment_ID payment
	 *	@param C_CashLine_ID cash line
	 */
	public void setPaymentInfo (int C_Payment_ID, int C_CashLine_ID)
	{
		if (C_Payment_ID != 0)
			setC_Payment_ID(C_Payment_ID);
		else if (C_CashLine_ID != 0)
			setC_CashLine_ID(C_CashLine_ID);
	}	//	setPaymentInfo

	/**
	 * 	Get Invoice
	 *	@return invoice or null
	 */
	public MInvoice getInvoice()
	{
		if (m_invoice == null && getC_Invoice_ID() != 0)
			m_invoice = new MInvoice (getCtx(), getC_Invoice_ID(), get_TrxName());
		return m_invoice;
	}	//	getInvoice

	/**
	 * 	Get CtaPacID
	 *	@return int or 0
	 */
	public int getCtaPacID()
	{
		if (ctaPacID == 0)
			ctaPacID = new MPayment(getCtx(), getC_Payment_ID(), get_TrxName()).getEXME_CtaPac_ID();
		return ctaPacID;
	}	//	getCtaPacID

	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (!newRecord  
			&& (is_ValueChanged("C_BPartner_ID") /*|| is_ValueChanged("C_Invoice_ID")*/))
		{
			log.severe ("Cannot Change Business Partner or Invoice");
			return false;
		}
		
		//	Set BPartner/Order from Invoice
		if (getC_BPartner_ID() == 0 && getInvoice() != null)
			setC_BPartner_ID(getInvoice().getC_BPartner_ID()); 
		if (getC_Order_ID() == 0 && getInvoice() != null)
			setC_Order_ID(getInvoice().getC_Order_ID());
		
		if(newRecord && getC_Invoice_ID()>0 && getC_Payment_ID()>0 && getDiscountAmt().compareTo(Env.ZERO)>0){
			setCreditMemo(getDiscountAmt());
			setDiscountAmt(Env.ZERO);
		}
		
		// Impuestos
		if(newRecord && getC_Invoice_ID()>0 && getC_Payment_ID()>0){
    		loadTaxes();
		}
		
		//
		return true;
	}	//	beforeSave

	
	/**
	 * 	Before Delete
	 *	@return true if reversed
	 */
	protected boolean beforeDelete ()
	{
		setIsActive(false);
		processIt(true);
		return true;
	}	//	beforeDelete
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MAllocationLine[");
		sb.append(get_ID());
		if (getC_Payment_ID() != 0)
			sb.append(",C_Payment_ID=").append(getC_Payment_ID());
		if (getC_CashLine_ID() != 0)
			sb.append(",C_CashLine_ID=").append(getC_CashLine_ID());
		if (getC_Invoice_ID() != 0)
			sb.append(",C_Invoice_ID=").append(getC_Invoice_ID());
		if (getC_BPartner_ID() != 0)
			sb.append(",C_BPartner_ID=").append(getC_BPartner_ID());
		sb.append(", Amount=").append(getAmount())
			.append(",Discount=").append(getDiscountAmt())
			.append(",WriteOff=").append(getWriteOffAmt())
			.append(",OverUnder=").append(getOverUnderAmt());
		sb.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**************************************************************************
	 * 	Process Allocation (does not update line).
	 * 	- Update and Link Invoice/Payment/Cash
	 * 	@param reverse if true allocation is reversed
	 *	@return C_BPartner_ID
	 */
	protected int processIt (boolean reverse)
	{
		log.fine("Reverse=" + reverse + " - " + toString());
		int C_Invoice_ID = getC_Invoice_ID();
		MInvoice invoice = getInvoice();
		if (invoice != null 
			&& getC_BPartner_ID() != invoice.getC_BPartner_ID())
			setC_BPartner_ID(invoice.getC_BPartner_ID());
		//
		int C_Payment_ID = getC_Payment_ID();
		int C_CashLine_ID = getC_CashLine_ID();
		
		//	Update Payment
		if (C_Payment_ID != 0)
		{
			MPayment payment = new MPayment (getCtx(), C_Payment_ID, get_TrxName());
			if (getC_BPartner_ID() != payment.getC_BPartner_ID())
				log.warning("C_BPartner_ID different - Invoice=" + getC_BPartner_ID() + " - Payment=" + payment.getC_BPartner_ID());
			if (reverse)
			{
				if (!payment.isCashTrx())
				{
					payment.setIsAllocated(false);
					payment.save();
				}
			}
			else
			{
				if (payment.testAllocation())
					payment.save();
			}
		}
		
		//	Payment - Invoice
		if (C_Payment_ID != 0 && invoice != null)
		{
			//	Link to Invoice
			if (reverse)
			{
				invoice.setC_Payment_ID(0);
				log.fine("C_Payment_ID=" + C_Payment_ID
					+ " Unlinked from C_Invoice_ID=" + C_Invoice_ID);
			}
			else if (invoice.isPaid())
			{
				invoice.setC_Payment_ID(C_Payment_ID);
				log.fine("C_Payment_ID=" + C_Payment_ID
					+ " Linked to C_Invoice_ID=" + C_Invoice_ID);
			}
			
			//	Link to Order
			String update = "UPDATE C_Order o "
				+ "SET C_Payment_ID=" 
					+ (reverse ? "NULL " : "(SELECT C_Payment_ID FROM C_Invoice WHERE C_Invoice_ID=" + C_Invoice_ID + ") ")
				+ "WHERE EXISTS (SELECT * FROM C_Invoice i "
					+ "WHERE o.C_Order_ID=i.C_Order_ID AND i.C_Invoice_ID=" + C_Invoice_ID + ")";
			if (DB.executeUpdate(update, get_TrxName()) > 0)
				log.fine("C_Payment_ID=" + C_Payment_ID 
					+ (reverse ? " UnLinked from" : " Linked to")
					+ " order of C_Invoice_ID=" + C_Invoice_ID);
		}
		
		//	Cash - Invoice
		if (C_CashLine_ID != 0 && invoice != null)
		{
			//	Link to Invoice
			if (reverse)
			{
				invoice.setC_CashLine_ID(0);
				log.fine("C_CashLine_ID=" + C_CashLine_ID 
					+ " Unlinked from C_Invoice_ID=" + C_Invoice_ID);
			}
			else
			{
				invoice.setC_CashLine_ID(C_CashLine_ID);
				log.fine("C_CashLine_ID=" + C_CashLine_ID 
					+ " Linked to C_Invoice_ID=" + C_Invoice_ID);
			}
			
			//	Link to Order
			String update = "UPDATE C_Order o "
				+ "SET C_CashLine_ID="
					+ (reverse ? "NULL " : "(SELECT C_CashLine_ID FROM C_Invoice WHERE C_Invoice_ID=" + C_Invoice_ID + ") ")
				+ "WHERE EXISTS (SELECT * FROM C_Invoice i "
					+ "WHERE o.C_Order_ID=i.C_Order_ID AND i.C_Invoice_ID=" + C_Invoice_ID + ")";
			if (DB.executeUpdate(update, get_TrxName()) > 0)
				log.fine("C_CashLine_ID=" + C_CashLine_ID 
					+ (reverse ? " UnLinked from" : " Linked to")
					+ " order of C_Invoice_ID=" + C_Invoice_ID);
		}		
		
		//	Update Balance / Credit used - Counterpart of MInvoice.completeIt
		if (invoice != null)
		{
			invoice.testAllocation();
			if(!invoice.save())
				log.log(Level.SEVERE, "Invoice not updated - " + invoice);
		}
		
		return getC_BPartner_ID();
	}	//	processIt
	
	
	/**
     *      Get Parent Currency
     *      @return currency
     */
    public int getC_Currency_ID ()
    {
            return getParent().getC_Currency_ID ();
    }       //      getC_Currency_ID

    
    /**
     * Obtenemos la distribuciones del pago para la cuenta especificada
     * @param ctx El contexto de la aplicacion
     * @param C_Invoice_ID El identificador de la cuenta paciente
     * @param trxName El nombre de la transaccion
     * @return Una lista de objetos de tipo MAllocationLine
     */
	public static List<MAllocationLine> getOfCtaPac(Properties ctx, int ctaPacID, String trxName) {
        List<MAllocationLine> lista = new ArrayList<MAllocationLine>();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        try {
        	sql.append(" SELECT AL.* ")
        	   .append(" FROM C_ALLOCATIONLINE AL ")
        	   .append(" INNER JOIN C_PAYMENT P ON P.C_PAYMENT_ID = AL.C_PAYMENT_ID ")
        	   .append(" WHERE P.EXME_CTAPAC_ID = ? ")
        	   .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", Table_Name, "AL"))
        	   .append(" ORDER BY AL.Created ASC ");  
            pstmt = DB.prepareStatement(sql.toString(), trxName);
              
			if(WebEnv.DEBUG)
				s_log.log(Level.SEVERE, sql.toString() + " ctapacID = " + ctaPacID);
			
            pstmt.setInt(1,ctaPacID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			    MAllocationLine allLine = new MAllocationLine(ctx, rs, trxName);
			    lista.add(allLine);
			}
			
        } catch (Exception e) {
        	s_log.log(Level.SEVERE, sql.toString(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
        
        return lista;
    }
    
	/**
	 * Payments/Adjustments of Account by type of Claim.
	 * @param ctaPacID Account
	 * @param AdjustmentType Payment Concept
	 * @param ConfType Claim Configuration Type
	 * @param ctx Context
	 * @return Payment Amount group by AdjustmentType
	 * @author gvaldez/abautista
	 */
    public static HashMap<String, Double> getTotalPaymentByClaim(int ctaPacID, String adjustmentType, String confType, Properties ctx) {
    	HashMap<String, Double> retVal = new HashMap<String, Double>();
    	//Double retVal = new Double(0);
    	StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
    	sql.append("SELECT EAT.Type, SUM(P.PayAmt) ")
    	   .append("  FROM  C_Payment P ")
    	   .append(" INNER JOIN C_AllocationLine AL ON AL.C_Payment_ID = P.C_Payment_ID ")
    	   .append(" INNER JOIN C_Charge   EAT ON EAT.C_Charge_ID = P.C_Charge_ID ");

       	if (StringUtils.isNotEmpty(adjustmentType)){
       		sql.append("AND EAT.Type= ? ");
       	}
    	sql.append(" LEFT JOIN C_Invoice i ")
    	   .append("    ON i.C_Invoice_ID = P.C_Invoice_ID ");
    	
    	sql.append("  WHERE P.EXME_CtaPac_ID = ? ");
    	if (StringUtils.isNotEmpty(confType)) {
    		sql.append(" AND (i.ConfType = ? ");
    		if (confType.equalsIgnoreCase(X_HL7_MessageConf.CONFTYPE_InstitutionalClaim)){
    			sql.append(" OR i.C_Invoice_ID is null ");
    		}else{
    			sql.append(" AND i.C_Invoice_ID is NOT null ");
    		}
    		sql.append(" ) ");
    	}
    	
    	
    	sql.append("GROUP BY EAT.Type ");
    	
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			int cont = 1;
			if (StringUtils.isNotEmpty(adjustmentType)){
				pstmt.setString(cont++, adjustmentType);
	    	}
			pstmt.setInt(cont++, ctaPacID);
			if (StringUtils.isNotEmpty(confType)) {
				pstmt.setString(cont++, confType);
			}
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
    
    /**
	 * After Save
	 * 
	 * @param newRecord
	 *            new
	 * @param success
	 *            success
	 * @return success
	 */
//    protected boolean afterSave(boolean newRecord, boolean success) {
//    	if (success) {
////    		loadTaxes();
//    	}
//    	return success;
//    } // afterSave
    
    /**
     * Método para el caso de prueba
     * @return
     */
    public static boolean loadTaxesTest(){
    	MAllocationLine obj = new MAllocationLine(Env.getCtx(), 0, null);
    	obj.setC_Invoice_ID(0);
    	obj.setC_Payment_ID(0);
    	obj.setAmount(Env.ZERO);
    	obj.setAmtAcct(Env.ZERO);
    	return obj.loadTaxes();
    }
    
	/**
	 *	Load Invoice Taxes
	 *					
	 *			 	COMO LA FACTURA INCLUYE PRODUCTOS GRAVADOS EN TASA 0% Y 16%					
	 *				EN EL EJEMPLO LA PARTE GRAVADA CORRESPONDEN 1000.00					
	 *				EN ESTE CASO EL SISTEMA DEBE VALIDAR EN PRIMER LUGAR SI LA FACTURA					
	 *				QUE SE PAGO Y QUE SE ESTA CONCILIANDO CONTIENE PRODUCTOS GRAVADOS.					
	 *				DESPUES DE IDENTIFICAR EL IMPORTE DE LA PARTE GRAVADA, SE DEBE COMPARAR					
	 *				EL VALOR DEL PAGO, 					
	 */
    public boolean loadTaxes(){
    	boolean success = true;

    	try {

    		MEXMEI18N valMex = MEXMEI18N.getFromCountry(Env.getCtx(), Env.getC_Country_ID(Env.getCtx()), null);
    		if (valMex != null && valMex.ismoveTaxes()){

    			// Factura a cubrir
    			final MInvoice minvoice = getInvoice();
    			// Pago asignado a la factura
    			BigDecimal pagoAplicado = getAmount();//-10
    			// Impuestos de la factura
    			final MInvoiceTax[] mtaxes = minvoice.getTaxes(true);

    			// Si no hay impuesto no se calcula nada
    			if(mtaxes==null || mtaxes.length<=0){
    				return true;
    			}

    			// Monto previamente trasladado
    			BigDecimal amtAcctInv = mtaxes[0].getAmtAcct();//0

    			// Se iteran las lineas de impuesto
    			for (int x = 0; x < mtaxes.length; x++) {

    				// Debe existir una parte gravada de la factura y pendiente de pagar  								
    				if(mtaxes[x].getTaxAmt().compareTo(Env.ZERO)==0 || amtAcctInv.compareTo(mtaxes[x].getTaxBaseAmt().add(mtaxes[x].getTaxAmt()))==0){
    					continue;
    				}

    				// Base sobre la que se calcula el impuesto
    				BigDecimal base = Env.ZERO;
    				// Importe que se mostrara en la poliza
    				BigDecimal ivadelpago = Env.ZERO;

    				// Impuesto aplicado
    				final MTax mtax = new MTax(Env.getCtx(), mtaxes[x].getC_Tax_ID(), get_TrxName());//

    				// BaseGravadaPendienteDePago: Base gravada + Impuesto - Impuesto conciliado o trasladado previamente //
    				BigDecimal baseGravadaPendienteDePago = mtaxes[x].getTaxBaseAmt().add(mtaxes[x].getTaxAmt()).subtract(amtAcctInv);
//133.46//123.46
    				// Se quita a la base gravada pendiente de pagar el pago que se aplica a la factura 
    				BigDecimal ivaPendienteDePagoMenosPago = baseGravadaPendienteDePago.subtract(pagoAplicado.abs());//
//123.46//113.46

    				// si es positivo (base gravada es mayor que  el pago)//
    				if(ivaPendienteDePagoMenosPago.compareTo(Env.ZERO)>=0){
    					//9.216589861751//9.216589861751
    					base = pagoAplicado.abs().divide(mtax.getDesglosar(), 12, BigDecimal.ROUND_HALF_UP);//
    					// Se actualiza el monto trasladado
    					amtAcctInv = amtAcctInv.add(pagoAplicado.abs());//
    					//10//20
    				} else {
    					// si es negativo (base gravada menos que el pago)//
    					base = baseGravadaPendienteDePago.abs().divide(mtax.getDesglosar(), 12, BigDecimal.ROUND_HALF_UP);//
    					// Se actualiza el monto trasladado
    					amtAcctInv = amtAcctInv.add(baseGravadaPendienteDePago.abs());//
    				}

    				// Iva del pago con relacion a la parte gravada de la factura
    				ivadelpago = base.multiply(mtax.getMultiplier());
    				//0.783410138248835//0.783410138248835000000000
    				// considerando que puede haber mas de una tasa
    				pagoAplicado = pagoAplicado.abs().subtract(base);//
    				//0.783410138248835//0.783410138248835000000000
    				// Se asigna el monto del iva por trasladar
    				setAmtAcct(ivadelpago);//C_AllocationLine.AmtAcct = impuesto trasladado
    			} // si hay importe gravado

    			if(amtAcctInv.compareTo(Env.ZERO)!=0){
    				//Actualiza la ultima cifra de saldo pagado
    				for (int x = 0; x < mtaxes.length; x++) {
    					// Debe existir una parte gravada de la factura y pendiente de pagar  								
    					if(mtaxes[x].getTaxAmt().compareTo(Env.ZERO)==0){
    						continue;
    					}

    					mtaxes[x].setAmtAcct(amtAcctInv);//C_InvoiceTax.AmtAcct = monto cubierto del importe gravado
    					mtaxes[x].save(get_TrxName());
    				}
    			}
    		}
    	} catch (Exception e) {
    		success = false;
    	}
    	return success;
    }	//	loadTaxes
    
    
	/**
	 * Obrtenemos el pago (anticipo) relacionado a la extencion.
	 * @return
	 */
	public MPayment getPayment(){
		if(mPayment == null || mPayment.getC_Payment_ID() == 0){
			mPayment = new MPayment(getCtx(), getC_Payment_ID(), get_TrxName());
		}
		mPayment.getPayAmt();
		return mPayment;
	}
	
	/**
	 * Metodo que realiza la asignacion de pagos, 
	 * moviendolos de la remision a la facturas
	 * Captura de Pagos al facturar
	 * Refacturación
	 * Factura Global
	 * 
	 * @param ctx
	 * @param oldInvoice la factura a al que se quitan los pagos
	 * @param newInvoice la nueva factura a la que se pasaran los pagos
	 * @param trxName
	 * @return
	 */
	public static boolean createAllocationsLines(final Properties ctx, final MInvoice oldInvoice, final int newInvoiceID, final String trxName){
		final List<MAllocationHdr> oldHdr = MAllocationHdr.getHeaders(ctx, oldInvoice, trxName);
		s_log.log(Level.WARNING, " Factura Global Se general las asignaciones de la Remision "+oldInvoice.getC_Invoice_ID() + " a la factura global " + newInvoiceID);
		if(oldHdr.isEmpty() && oldInvoice.isPaid()){
			s_log.log(Level.WARNING, " La Remision es considerada como pagada y no tiene asignaciones: "+oldInvoice.getC_Invoice_ID());
			return true;
		}
		
		// Recorrer las aignaciones para crear nuevas
		for(final MAllocationHdr hdr : oldHdr){
			final MAllocationHdr auxN = new MAllocationHdr(ctx, 0, trxName);
			final MAllocationHdr auxP = new MAllocationHdr(ctx, 0, trxName);
			PO.copyValues(hdr, auxN);
			PO.copyValues(hdr, auxP);
			
			auxN.resetDataAfterCopyValues();
			if(!auxN.save(trxName)){
				s_log.log(Level.WARNING, "%MAllocationLine l-662 --Error al guardar MAllocationHdr negativas");
				throw new MedsysException();
			}
			
			auxP.resetDataAfterCopyValues();
			if(!auxP.save(trxName)){
				s_log.log(Level.WARNING, "%MAllocationLine l-667 --Error al guardar MAllocationHdr positivas");
				throw new MedsysException();
			}

			final List<MAllocationLine> arrLines = hdr.getLinesByInvoice(true, oldInvoice.getC_Invoice_ID());
			if(arrLines==null || arrLines.isEmpty()) {
				s_log.log(Level.WARNING, "Asignaciones de la Remision " + oldInvoice.getDocumentNo() +" no tiene lineas y el ID asignacion " + hdr.getC_AllocationHdr_ID() + "Is");
			} else {
			
				//
				for(final MAllocationLine line : arrLines){
					final MAllocationLine lineAuxN = new MAllocationLine(ctx, 0, trxName);
					final MAllocationLine lineAuxP = new MAllocationLine(ctx, 0, trxName);
	
					PO.copyValues(line, lineAuxN);
					PO.copyValues(line, lineAuxP);
	
					//Factura
					lineAuxP.setC_AllocationHdr_ID(auxP.getC_AllocationHdr_ID());
					lineAuxP.setC_Invoice_ID(newInvoiceID);			
	
					//Remision
					lineAuxN.setC_AllocationHdr_ID(auxN.getC_AllocationHdr_ID());
					lineAuxN.setAmount(lineAuxN.getAmount().negate());
	
					if(!lineAuxN.save(trxName)){
						s_log.log(Level.WARNING, "%MAllocationLine l-693 --Error al guardar MAllocationLine negativas");
						throw new MedsysException();
					}
					
					if(!lineAuxP.save(trxName)){
						s_log.log(Level.WARNING, "%MAllocationLine l-693 --Error al guardar MAllocationLine positivas");
						throw new MedsysException();
					}
	
				}

//			auxN.setDateAcct(Env.getCurrentDate());
//			auxN.setDateTrx(Env.getCurrentDate());
			auxN.completeInvoice(true);
//			auxN.setDocStatus(auxN.completeIt());// Completar
//			if(!auxN.save(trxName)){
//				s_log.log(Level.WARNING, "%MAllocationLine l-708 --Error al guardar MAllocationHdr negativas");
//				throw new MedsysException();
//			}

//			auxP.setDateAcct(Env.getCurrentDate());
//			auxP.setDateTrx(Env.getCurrentDate());
			auxP.completeInvoice(true);
//			auxP.setDocStatus(auxP.completeIt());// Completar
//			if(!auxP.save(trxName)){
//				s_log.log(Level.WARNING, "%MAllocationLine l-716 --Error al guardar MAllocationHdr positivas");
//				throw new MedsysException();
//			}
			}
		}
		return true;
	}
	
	
	/**
	 * Get Allocations of Invoice
	 * 
	 * @param ctx
	 *            context
	 * @param C_Invoice_ID
	 *            payment
	 * @return allocations of payment
	 * @param trxName
	 *            transaction
	 */
	public static BigDecimal getSumAmountOfInvoice(final Properties ctx, final int C_Invoice_ID, final String trxName) {
		final StringBuilder sql = new StringBuilder(" SELECT SUM(coalesce(l.Amount,0)) ")
		.append(" FROM C_AllocationHdr h ")
		.append(" INNER JOIN C_AllocationLine l ON h.C_AllocationHdr_ID = l.C_AllocationHdr_ID  AND l.IsActive='Y' ")
		.append(" WHERE h.IsActive='Y'     ")
		.append(" AND l.C_Invoice_ID = ?   ")
		.append(" AND h.DocAction NOT IN ( ")
		.append(DB.TO_STRING(DocAction.ACTION_Reverse_Correct))
		.append(") ")
		.append(" AND h.DocStatus     IN ( ")
		.append(DB.TO_STRING(X_C_AllocationHdr.DOCSTATUS_Approved)).append(",")
		.append(DB.TO_STRING(X_C_AllocationHdr.DOCSTATUS_Closed)).append(",")
		.append(DB.TO_STRING(X_C_AllocationHdr.DOCSTATUS_Completed)).append(",")
		.append(DB.TO_STRING(X_C_AllocationHdr.DOCSTATUS_Drafted)).append(",")
		.append(DB.TO_STRING(X_C_AllocationHdr.DOCSTATUS_InProgress))
		.append(" ) ");
		//.append(" ORDER BY h.DateTrx DESC  ");
		
		BigDecimal amount = Env.ZERO;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_Invoice_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				amount = rs.getBigDecimal(1);
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return amount;
	} // getOfInvoice
	
	
    private boolean isPaymentPaid = false;
    
    public boolean isPaymentPaid() {
		return isPaymentPaid;
	}

	public void setPaymentPaid(boolean isPaymentPaid) {
		this.isPaymentPaid = isPaymentPaid;
	}



	public static List<MAllocationLine> getForInvoice(Properties ctx, int cInvoiceId, String trxName) {

		List<MAllocationLine> lines = new ArrayList<MAllocationLine>();

		String sql = "SELECT * FROM C_AllocationLine WHERE C_Invoice_ID = ?";
		PreparedStatement pstmt = DB.prepareStatement(sql, trxName);
		ResultSet rs = null;

		try {
			pstmt.setInt(1, cInvoiceId);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				MAllocationLine line = new MAllocationLine(ctx, rs, trxName);
				lines.add(line);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return lines;
	}
	
	
	/**
     * Obtenemos la distribuciones del pago para la factura especificada
     * @param ctx El contexto de la aplicacion
     * @param C_Invoice_ID El identificador de la factura
     * @param trxName El nombre de la transaccion
     * @return Un objeto de tipo MEXMEAllocationLine
     */
	public static MAllocationLine[] getOfInvoice(final Properties ctx, final int C_Invoice_ID, final String trxName) {
		final List<MAllocationLine> lista = new ArrayList<MAllocationLine>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final StringBuilder sql = new StringBuilder();
		try {
			sql.append(" SELECT C_AllocationLine.*, nvl(EXME_CtaPacPag.isPay,'Y') as PAID ")
			.append(" FROM C_AllocationLine ")
			.append(" INNER JOIN C_CashLine c ON c.C_CashLine_id = C_AllocationLine.c_cashline_id AND cashtype = ?")
			.append(" LEFT JOIN EXME_CtaPacPag ON (C_AllocationLine.C_Payment_ID = EXME_CtaPacPag.C_Payment_ID AND EXME_CtaPacPag.IsActive = 'Y' ) ")
			.append(" WHERE C_AllocationLine.C_Invoice_ID = ? ")
			// excluimos pagos a facturas directos
			.append(" AND COALESCE(C_AllocationLine.C_CashLine_ID,0) NOT IN ( SELECT C_CashLine_ID FROM C_CashLine WHERE C_Invoice_ID = ? ) ")

			.append(MEXMELookupInfo.addAccessLevelSQL(ctx," ","C_AllocationLine"))
			.append(" ORDER BY C_AllocationLine.C_AllocationHdr_ID ASC ");  
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			if(WebEnv.DEBUG){
				s_log.log(Level.SEVERE,"SELECT * FROM C_AllocationLine WHERE C_Invoice_ID = " + C_Invoice_ID);
			}

			pstmt.setString(1, MPayment.TYPE_AdvancePayment);
			pstmt.setInt(2,C_Invoice_ID);
			pstmt.setInt(3,C_Invoice_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MAllocationLine allLine = new MAllocationLine(ctx, rs, trxName);
				allLine.setPaymentPaid(rs.getString("PAID").equals("Y")?true:false);
				lista.add(allLine);
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		final MAllocationLine[] retValue = new MAllocationLine[lista.size()];
		lista.toArray(retValue);
		return retValue;
	}

	//CARD #1299
    /** Asignaciones de anticipos por ext. */
	public static List<MAllocationLine> getAdvancePaymentAllocated(Properties ctx, int ctaPacExtID, int invoiceID, String trxName) {
        final List<MAllocationLine> lista = new ArrayList<MAllocationLine>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sql = new StringBuilder();
        try {
        	sql.append(" SELECT AL.* ")
        	   .append(" FROM C_AllocationLine      al ")
        	   .append(" INNER JOIN EXME_CtaPacPag cpp  ON cpp.C_Payment_ID = al.C_Payment_ID ")
        	   .append("                               AND cpp.EXME_CtaPacExt_ID = ? ")
        	   .append(" WHERE al.IsActive = 'Y' ")
        	   .append(MEXMELookupInfo.addAccessLevelSQL(ctx," ", X_C_AllocationLine.Table_Name, "al"))
        	   .append(" AND   al.C_Invoice_ID = ? ")
        	   .append(" ORDER BY al.Created ASC ");  
            pstmt = DB.prepareStatement(sql.toString(), trxName);
              
			if(WebEnv.DEBUG)
				s_log.log(Level.SEVERE, sql.toString() + " ctaPacExtID = " + ctaPacExtID);
			
            pstmt.setInt(1,ctaPacExtID);
            pstmt.setInt(2, invoiceID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			    lista.add(new MAllocationLine(ctx, rs, trxName));
			}
        } catch (Exception e) {
        	s_log.log(Level.SEVERE, sql.toString(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
        return lista;
    }
	
	
	/** Asignacion de la factura y el pago, en teoria no se repiten */
	public static MAllocationLine getForInvoicePayment(Properties ctx, int cInvoiceId, int cPaymentId, String trxName) {
		return new Query(ctx
				, X_C_AllocationLine.Table_Name
				, " C_Invoice_ID = ? AND C_Payment_ID = ? "
				, trxName)
		.addAccessLevelSQL(true)
		.setOnlyActiveRecords(true)
		.setParameters(cInvoiceId, cPaymentId)
		.first();
	}
	
	
	/** Asignacion de la factura y el pago, en teoria no se repiten */
	public static List<MAllocationLine> getForPayment(Properties ctx, int cPaymentId, String trxName) {
		return new Query(ctx
				, X_C_AllocationLine.Table_Name
				, " C_Payment_ID = ? "
				, trxName)
		.addAccessLevelSQL(true)
		.setOnlyActiveRecords(true)
		.setParameters(cPaymentId)
		.list();
	}
}	//	MAllocationLine
