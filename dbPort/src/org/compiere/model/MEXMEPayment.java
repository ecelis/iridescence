/*
 * Created on 18-mar-2005
 *
 * Basada en MPayment
 * Se heredo ya que en el metodo beforeSave siempre guardaba false en el 
 * campo isPrepayment y en caja se necesita grabar true para los anticipos
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @deprecated use {@link MPayment}
 */
public class MEXMEPayment extends MPayment {

	/** serialVersionUID */
	private static final long serialVersionUID = 1441101197313424092L;

	/**************************************************************************
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_Payment_ID    payment to load, (0 create new payment)
	 *  @param trxName trx name
	 */
	public MEXMEPayment (Properties ctx, int C_Payment_ID, String trxName)
	{
		super (ctx, C_Payment_ID, trxName);
		// New
		if (C_Payment_ID == 0) {
//			setBackoffice(false);
		}
	}   //  MEXMEPayment
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MEXMEPayment (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MEXMEPayment
	
//	/**
//	 * 	Before Save 
//	 *  Basado en beforeSave de MPayment. Se sobreescribio porque siempre
//	 *  le asignaba false en el campo isPrepayment y en caja se necesita
//	 *  grabar true para los anticipos
//	 *	@param newRecord new
//	 *	@return save
//	 */
//	protected boolean beforeSave (boolean newRecord)
//	{
//		//	We have a charge
//		if (getC_Charge_ID() != 0) 
//		{
//			if (newRecord || is_ValueChanged("C_Charge_ID"))
//			{
//				setC_Order_ID(0);
//				setC_Invoice_ID(0);
//				setWriteOffAmt(Env.ZERO);
//				setDiscountAmt(Env.ZERO);
//				setIsOverUnderPayment(false);
//				setOverUnderAmt(Env.ZERO);
//				//no tiene que ser false para caja
//				//se asigna dependiendo de las necesidades
//				//setIsPrepayment(false);
//			}
//		}
//		//	We need a BPartner
//		else if (getC_BPartner_ID() == 0 && !isCashTrx())
//		{
//			if (getC_Invoice_ID() != 0)
//				;
//			else if (getC_Order_ID() != 0)
//				;
//			else
//			{
//				log.saveError("Error", Msg.parseTranslation(getCtx(), "@NotFound@: @C_BPartner_ID@"));
//				return false;
//			}
//		}
//		//	Prepayment: No charge and order or project (not as acct dimension)
//		//Gisela Lee : asignar isPrepayment desde la captura de pagos
//		/*if (newRecord 
//			|| is_ValueChanged("C_Charge_ID") || is_ValueChanged("C_Invoice_ID")
//			|| is_ValueChanged("C_Order_ID") || is_ValueChanged("C_Project_ID"))
//			setIsPrepayment (getC_Charge_ID() == 0 
//				&& getC_BPartner_ID() != 0
//				&& (getC_Order_ID() != 0 
//					|| (getC_Project_ID() != 0 && getC_Invoice_ID() == 0)));*/
//		if (isPrepayment())
//		{
//			if (newRecord 
//				|| is_ValueChanged("C_Order_ID") || is_ValueChanged("C_Project_ID"))
//			{
//				setWriteOffAmt(Env.ZERO);
//				setDiscountAmt(Env.ZERO);
//				setIsOverUnderPayment(false);
//				setOverUnderAmt(Env.ZERO);
//			}
//		}
//		
//		//	Document Type
//		if (getC_DocType_ID() == 0)
//			setC_DocType_ID();
//		setDocumentNo();
//		//
//		if (getDateAcct() == null)
//			setDateAcct(getDateTrx());
//		//
//		if (!isOverUnderPayment())
//			setOverUnderAmt(Env.ZERO);
//		return true;
//	}	//	beforeSave
	
//	/**
//	 * 	Set Doc Type bases on IsReceipt
//	 *  Basado en setC_DocType_ID de MPayment. Se definio porque lo
//	 *  se manda llamar en beforeSave y es private en MPayment
//	 */
//	public void setC_DocType_ID ()
//	{
//		setC_DocType_ID(isReceipt());
//	}	//	setC_DocType_ID

	/**
	 *  Set DocumentNo to Payment info.
	 * 	If there is a R_PnRef that is set automatically 
	 *  Basado en setDocumentNo de MPayment. Se definio porque lo
	 *  se manda llamar en beforeSave y es private en MPayment
	 */
//	public void setDocumentNo()
//	{
//		//	Cash Transfer
//		if ("X".equals(getTenderType()))
//			return;
//		//	Current Document No
//		String documentNo = getDocumentNo();
//		//	Existing reversal
//		if (documentNo != null 
//			&& documentNo.indexOf(REVERSE_INDICATOR) >= 0)
//			return;
//		
//		//	If external number exists - enforce it 
//		if (getR_PnRef() != null && getR_PnRef().length() > 0)
//		{
//			if (!getR_PnRef().equals(documentNo))
//				setDocumentNo(getR_PnRef());
//			return;
//		}
//		
//		documentNo = "";
//		//	Credit Card
//		if (TENDERTYPE_CreditCard.equals(getTenderType()))
//		{
//			documentNo = (getCreditCardType() == null ? "" : getCreditCardType()) 
//				+ " " + Obscure.obscure(getCreditCardNumber() == null ? "" : getCreditCardNumber())
//				+ " " + getCreditCardExpMM() 
//				+ "/" + getCreditCardExpYY();
//		}
//		//	Own Check No
//		else if (TENDERTYPE_Check.equals(getTenderType())
//			&& !isReceipt()
//			&& getCheckNo() != null && getCheckNo().length() > 0)
//		{
//			documentNo = getCheckNo();
//		}
//		//	Customer Check: Routing: Account #Check 
//		else if (TENDERTYPE_Check.equals(getTenderType())
//			&& isReceipt())
//		{
//			if (getRoutingNo() != null)
//				documentNo = getRoutingNo() + ": ";
//			if (getAccountNo() != null)
//				documentNo += getAccountNo();
//			if (getCheckNo() != null)
//			{
//				if (documentNo.length() > 0)
//					documentNo += " ";
//				documentNo += "#" + getCheckNo();
//			}
//		}
//
//		//	Set Document No
//		documentNo = documentNo.trim();
//		if (documentNo.length() > 0)
//			setDocumentNo(documentNo);
//	}	//	setDocumentNo

   
   
    
 
    /**
     * Permite cambiar el estatus de processed y generated
     * @param ctx
     * @param encabezado
     * @param trxName
     * @return
     */
    public static boolean cambiarEstatus(Properties ctx, MCash encabezado, String trxName){
    	MPayment[] lineas = MEXMEPayment.pagosEnCash(ctx, encabezado.getC_Cash_ID(), trxName);
        
        for (int i=0; i<lineas.length; i++){
            lineas[i].setProcessed(true);
            if (!lineas[i].save(trxName))
               return false;
        }
    
        return true;
    }
    
    
	/**
	 * Devuelve un arreglo con las facturas destinadas a la apertura/cierre de la caja
	 * @param ctx
	 * @param C_CashBook_ID Caja
	 * @param C_Cash_ID         Linea de Apertura de caja
	 * @param trxName
	 * @return
	 */
	public static MPayment[] pagosEnCash(final Properties ctx, final int C_Cash_ID,
	        	final String trxName){

		if (ctx == null) {
			return null;
		}
		List<MPayment> lista = new ArrayList<MPayment>();

		final StringBuilder sql = new StringBuilder(100);  
		sql.append(" SELECT * FROM C_CashLine cLine INNER JOIN ")
		.append(" C_Payment p ON ( p.C_Payment_ID = cLine.C_Payment_ID ) WHERE cLine.IsActive = 'Y' ")
	    .append(" AND cLine.C_Cash_ID = ? AND p.Posted = 'N' ")
        .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "C_CashLine", "cLine"));
        
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_Cash_ID);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				MPayment m_pago = new MPayment(ctx, rs, trxName);
			    lista.add(m_pago);
			} 
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, "SQL : " + sql.toString(), e);
        } finally {
        	DB.close(rs, pstmt);
        }
	    MPayment[] listPag = new MPayment[lista.size()];
		lista.toArray(listPag);
		return listPag;
	}
	
	
//	public static MEXMEPayment pagoDevol(Properties ctx, MEXMEPayment from,
//			String trxName){
//		//generamos el pago en C_Payment
//		MEXMEPayment to = new MEXMEPayment (ctx, 0, trxName);
//		copyValues(from, to);
//		to.setClientOrg(from);
//		//
//		to.setPayAmt(from.getPayAmt().negate());
//		to.setDiscountAmt(from.getDiscountAmt().negate());
//		to.setWriteOffAmt(from.getWriteOffAmt().negate());
//		to.setOverUnderAmt(from.getOverUnderAmt().negate());
//		//
//		to.prepareIt();
//		to.setDocStatus(to.completeIt());
//		
//		return to;
//	}

	public static List<MEXMEPayment> getPatientPayments(Properties ctx, int ctaPacId, String trxName) {
		return getPayments(ctx, ctaPacId, MBPartner.BP_CLASS_P, null, trxName);
	}

	public static List<MEXMEPayment> getAdjustments(Properties ctx, int ctaPacId, String trxName) {
		return getPayments(ctx, ctaPacId, MBPartner.BP_CLASS_I, true, trxName);
	}

	public static List<MEXMEPayment> getInstitutionsPayments(Properties ctx, int ctaPacId, String trxName) {
		return getPayments(ctx, ctaPacId, MBPartner.BP_CLASS_I, false, trxName);
	}

	/**
	 * Find bPartner's payments.
	 * 
	 * @param ctx
	 *            context
	 * @param ctaPacId
	 *            patient encounter
	 * @param bpClass
	 *            bPartner's class<br>
	 *            <ul>
	 *            <li>Insurance</li>
	 *            <li>Patient</li>
	 *            <li>Company</li>
	 *            <li>( ... )</li>
	 *            </ul>
	 * @param isAdjustment
	 *            if not null add "Adjustment Type" where clause:<br>
	 *            <b>true</b> C_Payment.EXME_AdjustmentType_ID is not null<br>
	 *            <b>false</b> C_Payment.EXME_AdjustmentType_ID not null
	 * @param trxName
	 *            transaction name
	 * @return list of payments
	 */
	public static List<MEXMEPayment> getPayments(Properties ctx, int ctaPacId, String bpClass, Boolean isAdjustment,
			String trxName) {

		final StringBuilder where = new StringBuilder();
		where.append(" c_payment.exme_ctapac_id=? AND ");
		where.append(" cb.bp_class=?");
		if (isAdjustment != null) {
			where.append(" AND c_payment.EXME_AdjustmentType_ID IS ");
			where.append(isAdjustment ? " NOT NULL " : " NULL ");
		}
		final StringBuilder join = new StringBuilder();
		join.append(" INNER JOIN c_bpartner cb ");
		join.append(" ON (c_payment.c_bpartner_id = cb.c_bpartner_id) ");

		final List<MEXMEPayment> lista = new Query(ctx, Table_Name, where.toString(), trxName)//
				.setJoins(join)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.setParameters(ctaPacId, bpClass)//
				.list();

		return lista;
	}

	/**
	 * Find insurance's (C_BPartner.BP_Class=I) <br>
	 * adjustement payments (C_Payment.EXME_AdjustmentType_ID not null)
	 * 
	 * @param ctx
	 *            context
	 * @param ctaPacId
	 *            patient encounter
	 * @param trxName
	 *            transaction name
	 * @return payment's sum (ZERO if no payments were found)
	 */
	public static BigDecimal getAdjustmentsTotal(Properties ctx, int ctaPacId, String trxName) {
		return getPaymentsTotal(ctx, ctaPacId, MBPartner.BP_CLASS_I, true, trxName);
	}

	/**
	 * Find insurance's (C_BPartner.BP_Class=I) <br>
	 * payments (C_Payment.EXME_AdjustmentType_ID null)
	 * 
	 * @param ctx
	 *            context
	 * @param ctaPacId
	 *            patient encounter
	 * @param trxName
	 *            transaction name
	 * @return payment's sum (ZERO if no payments were found)
	 */
	public static BigDecimal getInstitutionsPaymentsTotal(Properties ctx, int ctaPacId, String trxName) {
		return getPaymentsTotal(ctx, ctaPacId, MBPartner.BP_CLASS_I, false, trxName);
	}

	/**
	 * Find encounter's (C_BPartner.BP_Class=P) <br>
	 * payments or adjustments
	 * 
	 * @param ctx
	 *            context
	 * @param ctaPacId
	 *            patient encounter
	 * @param trxName
	 *            transaction name
	 * @return payment's sum (ZERO if no payments were found)
	 */
	public static BigDecimal getPatientPaymentsTotal(Properties ctx, int ctaPacId, String trxName) {
		return getPaymentsTotal(ctx, ctaPacId, MBPartner.BP_CLASS_P, null, trxName);
	}

	/**
	 * Find bPartner's payments.
	 * 
	 * @param ctx
	 *            context
	 * @param ctaPacId
	 *            patient encounter
	 * @param bpClass
	 *            bPartner's class<br>
	 *            <ul>
	 *            <li>Insurance</li>
	 *            <li>Patient</li>
	 *            <li>Company</li>
	 *            <li>( ... )</li>
	 *            </ul>
	 * @param isAdjustment
	 *            if not null adds "Adjustment Type" where clause<br>
	 *            <b>true</b> C_Payment.EXME_AdjustmentType_ID is not null<br>
	 *            <b>false</b> C_Payment.EXME_AdjustmentType_ID not null
	 * @param trxName
	 *            transaction name
	 * @return payment's sum (ZERO if no payments were found)
	 */
	public static BigDecimal getPaymentsTotal(Properties ctx, int ctaPacId, String bpClass, Boolean isAdjustment,
			String trxName) {
		final BigDecimal value;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT ");
		sql.append("  nvL(SUM(cp.payamt), ");
		sql.append("  0) ");
		sql.append("FROM ");
		sql.append("  c_payment cp ");
		sql.append("  INNER JOIN c_bpartner cb ");
		sql.append("  ON cp.c_bpartner_id = cb.c_bpartner_id ");
		sql.append("WHERE ");
		sql.append("  cp.exme_ctapac_id = ? AND ");
		sql.append("  TRIM(cb.bp_class) = ? ");
		if (isAdjustment != null) {
			sql.append(" AND cp.EXME_AdjustmentType_ID IS ");
			sql.append(isAdjustment ? " NOT NULL " : " NULL ");
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_ID, "cp"));

		value = DB.getSQLValueBD(trxName, sql.toString(), ctaPacId, bpClass);
		return value == null ? BigDecimal.ZERO : value;
	}
	
	/**
 	 * Obtiene todos los C_Payments de la cuenta paciente con el socio de negocios dado, que aun no tenga un C_AllocationLine

	 * @param ctx
	 * @param exmeCtaPacID
	 * @param cbPartnerID
	 * @param trxName
	 * @return
	 */
	public static List<MPayment> getPaymentCtaPac(Properties ctx, int exmeCtaPacID, int cbPartnerID, String trxName) {

		final StringBuilder where = new StringBuilder();
		where.append(" c_payment.exme_ctapac_id = ? ");
		where.append(" and c_payment.c_bpartner_id = ? ");
		where.append(" and not exists (select * from c_allocationLine Where c_payment.c_payment_id = c_allocationLine.c_payment_id) ");

		final List<MPayment> lista = new Query(ctx, Table_Name, where.toString(), trxName)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.setParameters(exmeCtaPacID, cbPartnerID)//
		.list();

		return lista;
	}
	
	
	/**
	 * Generamos el pago por concepto de anticipo a cuenta paciente
	 * 
	 * @param movto
	 *            La linea del movimiento del pago original
	 * @return Un objeto MPayment con la informacion insertada
	 * @throws Exception
	 * @deprecated
	 */
	public static MPayment generarPago(PagoLine movto
			, final Properties ctx
			, final String recibo
			, final int monedaID
			, final int clientID
			, final int EXME_Operador_ID
			, final int ctaPacID
			, String trxName) throws Exception {

		final Timestamp now = DB.convert(ctx, new Date());
		final MEXMEPayment payment = new MEXMEPayment(ctx, 0, trxName);
		payment.setDateTrx(now);
		payment.setIsReceipt(true);
		payment.setC_DocType_ID(MEXMEDocType.getOfName(ctx, Constantes.AR_RECEIPT, null));

		payment.setTrxType(MEXMEPayment.TRXTYPE_Sales);

		final MConfigPre configPre = MConfigPre.get(ctx, null);
		if (configPre == null) {
			throw new Exception("error.caja.configPre");
		}
		payment.setC_BankAccount_ID(configPre.getC_BankAccount_ID());

		payment.setC_BPartner_ID(clientID);

		if (movto.getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_Cash)) {
			payment.setTenderType(MPayment.TENDERTYPE_Cash);
		} else if (movto.getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_Check)) {
			payment.setTenderType(MPayment.TENDERTYPE_Check);
			payment.setRoutingNo(movto.getRoutingNo());
			payment.setAccountNo(movto.getAccountNo());
			payment.setCheckNo(movto.getCheckNo());
			payment.setMicr(movto.getMicr());
			payment.setA_Name(movto.getA_Name());
		} else if (movto.getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_CreditCard)) {
			payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
			payment.setCreditCardType(movto.getCreditCardType());
			payment.setCreditCardNumber(movto.getCreditCardNumber());
			payment.setCreditCardVV(movto.getCreditCardVV());
			payment.setCreditCardExpMM(movto.getCreditCardExpMM());
			payment.setCreditCardExpYY(movto.getCreditCardExpYY());
			payment.setA_Name(movto.getA_Name());
		} else if (movto.getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_Other)) {
			payment.setTenderType(MPayment.TENDERTYPE_Other);
		} else {
			payment.setTenderType(MPayment.TENDERTYPE_Other);
		}

		payment.setC_Currency_ID(monedaID);
		payment.setPayAmt(movto.getMontoAplicado());
		payment.setOProcessing("N");
		payment.setEXME_CtaPac_ID(Integer.valueOf(ctaPacID));
		payment.setReciboNo(recibo);

		// Organizacion transaccional del operador que hizo la venta
		final MEXMEOperador operador = new MEXMEOperador(ctx, Integer.valueOf(EXME_Operador_ID), null);
		payment.setAD_OrgTrx_ID(operador.getAD_OrgTrx_ID());
		if (!payment.save(trxName)) {
			throw new Exception("error.caja.noSaveLine");
		}

		return payment;
	}


	/**
	 * Generamos el pago por concepto de anticipo a cuenta paciente
	 * 
	 * @param cashline MCashLine - La linea del movimiento del pago original
	 * @param clientID
	 * @param EXME_Operador_ID 
	 * @return MPayment con la informacion insertada
	 * @deprecated use MCashline.createPayment(final int clientID, final int orgTrxId)
	 */
	public static MPayment generarPago(MCashLine cashline, final int clientID, final int EXME_Operador_ID) {
		final MEXMEPayment payment = new MEXMEPayment(cashline.getCtx(), 0, cashline.get_TrxName());
		payment.setDateTrx(Env.getCurrentDate());
		payment.setIsReceipt(true);
		payment.setC_DocType_ID(MEXMEDocType.getOfName(cashline.getCtx(), Constantes.AR_RECEIPT, null));
		payment.setTrxType(MEXMEPayment.TRXTYPE_Sales);

		final MConfigPre configPre = MConfigPre.get(cashline.getCtx(), null);
		if (configPre == null) {
			throw new MedsysException("error.caja.configPre");
		}
		payment.setC_BankAccount_ID(configPre.getC_BankAccount_ID());
		payment.setC_BPartner_ID(clientID);

		if (cashline.getFormaPago().getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_Cash)) {
			payment.setTenderType(MPayment.TENDERTYPE_Cash);
		} else if (cashline.getFormaPago().getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_Check)) {
			payment.setTenderType(MPayment.TENDERTYPE_Check);
			payment.setRoutingNo(cashline.getRoutingNo());
			payment.setAccountNo(cashline.getAccountNo());
			payment.setCheckNo(cashline.getCheckNo());
			payment.setMicr(cashline.getMicr());
			payment.setA_Name(cashline.getA_Name());
		} else if (cashline.getFormaPago().getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_CreditCard)) {
			payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
			payment.setCreditCardType(cashline.getCreditCardType());
			payment.setCreditCardNumber(cashline.getCreditCardNumber());
			payment.setCreditCardVV(cashline.getCreditCardVV());
			payment.setCreditCardExpMM(cashline.getCreditCardExpMM());
			payment.setCreditCardExpYY(cashline.getCreditCardExpYY());
			payment.setA_Name(cashline.getA_Name());
		} else if (cashline.getFormaPago().getPaymentRule().equalsIgnoreCase(X_C_Invoice.PAYMENTRULE_Other)) {
			payment.setTenderType(MPayment.TENDERTYPE_Other);
		} else {
			payment.setTenderType(MPayment.TENDERTYPE_Other);
		}

		payment.setC_Currency_ID(cashline.getC_Currency_ID());
		payment.setPayAmt(cashline.getAmount());
		payment.setOProcessing("N");
		payment.setEXME_CtaPac_ID(cashline.getEXME_CtaPac_ID());
		payment.setReciboNo(cashline.getReciboNo());

		// Organizacion transaccional del operador que hizo la venta
		final MEXMEOperador operador = new MEXMEOperador(cashline.getCtx(), EXME_Operador_ID, null);
		payment.setAD_OrgTrx_ID(operador.getAD_OrgTrx_ID());
		if (!payment.save()) {
			throw new MedsysException();
		}
		return payment;
	}
	
	/**
	 * Entonces seria activos, sin allocation, para la cuenta, socio
	 * @param ctx
	 * @param ctaPacId
	 * @param bpClass
	 * @param isAdjustment
	 * @param trxName
	 * @return
	 */
	public static List<MPayment> getPagosPorPaciente(final Properties ctx, 
			final String ctaPacsADesaparecer, final String trxName) {

		final StringBuilder where = new StringBuilder()
		.append(" c_payment.exme_ctapac_id IN ( ")
		.append(ctaPacsADesaparecer).append(") ");

		final List<MPayment> lista = new Query(ctx, Table_Name, where.toString(), trxName)//
		.setOnlyActiveRecords(true)//
		.addAccessLevelSQL(true)//
		.list();

		return lista;
	}

}