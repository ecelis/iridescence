package org.compiere.model.billing;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MCharge;
import org.compiere.model.MConfigPre;
import org.compiere.model.MDocType;
import org.compiere.model.MEXMEClaimPayment;
import org.compiere.model.MEXMEClaimPaymentH;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEDocType;
import org.compiere.model.MEXMEInvoice;
import org.compiere.model.MEXMEPacienteAseg;
import org.compiere.model.MEXMEPayment;
import org.compiere.model.MEXMETax;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.ModelError;
import org.compiere.model.PO;
import org.compiere.model.X_C_Charge;
import org.compiere.model.X_C_Invoice;
import org.compiere.model.X_EXME_ClaimPaymentH;
import org.compiere.model.X_EXME_CtaPac;
import org.compiere.model.billing.MNextStep.IEncounterStep;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 * Save ClaimPayment
 * 
 * @author twry
 * 
 */
public class ClaimPayment {
	/** Header */
	private MEXMEClaimPaymentH header;
	/** lst Claim Payment */
	private transient List<MEXMEClaimPayment> lstClaimPayment = new ArrayList<MEXMEClaimPayment>();
	/** trxName */
	private transient String trxName;
	/** Manejo de errores */
	protected transient List<ModelError> viewErrors = new ArrayList<ModelError>();
	/** configuracion de precios */
	private transient MConfigPre configPre;
	/** id de tipo de documento */
	private int docType = 0;
	/** mapa de cuentas pacientes con lista de pagos */
	private HashMap<Integer, List<MPayment>> mapaCta = new HashMap<Integer, List<MPayment>>();
	private HashMap<Integer, MInvoice> mapaInv = new HashMap<Integer, MInvoice>();
	private HashMap<Integer, MAllocationHdr> mapaAlloc = new HashMap<Integer, MAllocationHdr>();
	/** */
	private BigDecimal total;
	/** log */
	private CLogger s_log = CLogger.getCLogger(ClaimPayment.class);
	
	
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * Save <MEXMEClaimPaymentH> and list <MEXMEClaimPayment>
	 * 
	 * @param pheader
	 *            Header <MEXMEClaimPaymentH>
	 * @param trxName
	 *            Transaction name <String>
	 * @return <true> if header insert/update
	 */
	public boolean save(final MEXMEClaimPaymentH pheader, final String trxName) {
		if (pheader == null) {
			viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
					"msj.error"));
			return false;
		}
		this.trxName = trxName;
		this.header = pheader;

		return paymentH();
	}

	/**
	 * save header <MEXMEClaimPaymentH> and lines <MEXMEClaimPayment>
	 * 
	 * @return <true> if the record is inserted or updated and status is
	 *         "Drafted" or "In Progress"
	 */
	private boolean paymentH() {
		if (!header.save(trxName)) {
			viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
					"We were unable to save the batch"));
			return false;
		}

		return saveLine();
	}

	/**
	 * save lines <MEXMEClaimPayment>
	 * 
	 * @return <String> header status
	 */
	private boolean saveLine() {
		String status = X_C_Invoice.DOCSTATUS_Drafted;
		if (lstClaimPayment != null && !lstClaimPayment.isEmpty()) {
			total = Env.ZERO;

			for (int i = 0; i < lstClaimPayment.size(); i++) {

				lstClaimPayment.get(i).setEXME_ClaimPaymentH_ID(
						header.getEXME_ClaimPaymentH_ID());

				MCharge charge = new MCharge(Env.getCtx(), lstClaimPayment.get(i).getC_Charge_ID(), null);
				if(charge.getType().equals(X_C_Charge.TYPE_Payment)
						|| charge.getType().equals(X_C_Charge.TYPE_InsurancePayments)
								|| charge.getType().equals(X_C_Charge.TYPE_CoinsurancePayment)
										|| charge.getType().equals(X_C_Charge.TYPE_CopayPayment)
												|| charge.getType().equals(X_C_Charge.TYPE_DeductiblePayment)
													|| charge.getType().equals(X_C_Charge.TYPE_OthersPayment))
				{
					total = sumLines(total, lstClaimPayment.get(i));
				}
				
				if (!lstClaimPayment.get(i).save(trxName)) {
					viewErrors
							.add(new ModelError(true,
									ModelError.TIPOERROR_Error,
									"error.detalleconsentimiento", charge.getName()));
					return false;
				}
			}

//			if (header.getPaidAmt().compareTo(total) == 0) {
//				status = X_C_Invoice.DOCSTATUS_InProgress;
//			} else if (header.getPaidAmt().compareTo(total) < 0) {
//				viewErrors
//						.add(new ModelError(false, ModelError.TIPOERROR_Error,
//								"The sum of the lines is greater than the payment amount "));
//				status = X_C_Invoice.DOCSTATUS_Invalid;
//			}

		} else {
			viewErrors.add(new ModelError(false, ModelError.TIPOERROR_Error,
					"No lines related to the batch"));
			status = X_C_Invoice.DOCSTATUS_Invalid;
		}

		header.setStatus(status);
		if (!header.save(trxName)) {
			viewErrors.add(new ModelError(false, ModelError.TIPOERROR_Error,
					"I do not pay the line"));
			return false;
		}

		return true;
	}

	/**
	 * sum Lines
	 * 
	 * @param total
	 * @param line
	 * @return
	 */
	private BigDecimal sumLines(final BigDecimal total,
			final MEXMEClaimPayment line) {
		return total.add(line.getAmt_Paid());
	}

	/**
	 * Lst Delete
	 * 
	 * @param deletelst
	 * @param trxName
	 * @return
	 */
	public boolean setLstDelete(final List<MEXMEClaimPayment> deletelst) {
		for (int i = 0; i < deletelst.size(); i++) {
			if (deletelst.get(i) != null && deletelst.get(i).getEXME_ClaimPayment_ID()>0
					&& !deletelst.get(i).delete(true, trxName)) {
				viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
						"esqDesc.error.borrar"));
				return false;
			}
		}
		return true;
	}

	/****************************************************************************/
	/**
	 * init process
	 * 
	 * @param pheader
	 * @param trxName
	 * @return error
	 */
	public boolean process(MEXMEClaimPaymentH pheader,
			List<IEncounterStep> lstNextStep, String trxName) {
		boolean ok = false;
		this.header = pheader;
		this.trxName = trxName;
		this.configPre = MConfigPre.get(Env.getCtx(), trxName);
		this.docType = MEXMEDocType.getOfName(Env.getCtx(), "AR Receipt",
				trxName);

		// documento nota de debito
		MDocType[] docs = MEXMEDocType.getOfDocBaseType(header.getCtx(),
				MDocType.DOCBASETYPE_ARDebitMemo);

		// Exista un tipo de documento
		if (docs != null && docs.length > 0)
			debitMemoId = docs[0].getC_DocType_ID();

		// Crear los pagos (C_Payment) como primer paso
		String success = payment();
		if (success.equals(X_C_Invoice.DOCSTATUS_Completed)) {
			
			// Relacionar los pagos a la factura (Allocation)
			success = iteraAllocation();
			
			if (success.equals(X_C_Invoice.DOCSTATUS_Closed)
					&& lstNextStep != null && !lstNextStep.isEmpty()) {
				// Crear la nota de cargo de la sig aseguradora/paciente (next invoice)	
				success = createInvoice(lstNextStep);
			}
		}

		header.setStatus(success);
		if(header.save(trxName) && X_C_Invoice.DOCSTATUS_Closed.equals(header.getStatus())) {
			header.setPostedDate(new Timestamp(System.currentTimeMillis()));
			ok = header.save(trxName);
		}
			
		return ok;
	}

	/**
	 * save Payment
	 * 
	 * @return error
	 */
	private String payment() {
		String success = X_C_Invoice.DOCSTATUS_Invalid;
		if (configPre == null) {
			viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
					"error.caja.configPre"));
		} else if (header.getC_BPartner_ID() <= 0) {
			viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
					"msj.error"));
		} else {
			success = groupPayment();
		}
		s_log.log( Level.INFO, "payment OK >"+success);
		return success;
	}

	/**
	 * Insert payments
	 * 
	 * @return
	 */
	private String groupPayment() {
		String success = X_C_Invoice.DOCSTATUS_Invalid;

		// Obtiene el listado de pagos/ajustes (llenar lstPaymentHdr)
		List<MPayment> lstPayment = claimPaymentGroup(header.getCtx(),
				header.getEXME_ClaimPaymentH_ID(), header.getC_BPartner_ID(), 
				header.getPaymentDate(), trxName);
		
		// Se agrupan por cuenta paciente, todos son del mismo bpartner
		for (int i = 0; i < lstPayment.size(); i++) {
			
			//
			if(lstPayment.get(i).save()){
				
				List<MPayment> lst = new ArrayList<MPayment>();
				if (mapaCta.containsKey(lstPayment.get(i).getEXME_CtaPac_ID())) {
					lst = mapaCta.get(lstPayment.get(i).getEXME_CtaPac_ID());
					mapaCta.remove(lstPayment.get(i).getEXME_CtaPac_ID());
				}
				
				lst.add(lstPayment.get(i));
				mapaCta.put(lstPayment.get(i).getEXME_CtaPac_ID(), lst);
				
			} else {
				break;
			}
		}
		
		//
		success = viewErrors.isEmpty() ? X_C_Invoice.DOCSTATUS_Completed
				: X_C_Invoice.DOCSTATUS_Invalid;
		s_log.log( Level.INFO, "groupPayment OK >"+success);
		return success;
	}

//	/**
//	 * Group by EXME_CtaPac_ID , EXME_AdjustmentType_ID, C_Invoice_ID
//	 * 
//	 * @param objPayment
//	 *            Payment (sum of group)
//	 * @param i
//	 *            index for ticket name
//	 */
//	private MPayment claimPaymentAdj(MPayment objPayment, int i) {
//		MPayment payment = new MPayment(header.getCtx(), 0, trxName);
//		try {
//			payment.setC_DocType_ID(docType);
//			payment.setC_Currency_ID(Env.getContextAsInt(Env.getCtx(),
//					"$C_Currency_ID"));
//			payment.setReciboNo("ticket" + i + " "
//					+ objPayment.getEXME_CtaPac_ID());
//			// TODO:MEXMEAreaCaja.createCounterRecibo(Env.getCtx(),
//			// MEXMECashBook.getAreaCajaID(operador.getC_CashBook_ID()),
//			// trxName));//el numero
//			// de recibo
//			payment.setEXME_CtaPac_ID(objPayment.getEXME_CtaPac_ID());
//			payment.setC_BPartner_ID(header.getC_BPartner_ID());
//			payment.setC_Invoice_ID(objPayment.getC_Invoice_ID());
//			payment.setC_BankAccount_ID(configPre.getC_BankAccount_ID());
//			payment.setPayAmt(objPayment.getPayAmt());
//
//			if (!payment.save(trxName)) {
//				viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
//						"msj.error"));
//				payment = null;
//			}
//
//		} catch (Exception e) {
//			viewErrors.add(new ModelError(false, ModelError.TIPOERROR_Error, e
//					.getMessage()));
//			payment = null;
//		}
//
//		return payment;
//	}

	/*********************************************************************************************/
	/**
	 * Crea la asignacion de pagos
	 * por cuenta paciente
	 * @return status doc
	 */
	private String iteraAllocation() {
		String success = X_C_Invoice.DOCSTATUS_Invalid;
		Iterator<Entry<Integer, List<MPayment>>> itEntries = mapaCta.entrySet()
				.iterator();
		
		// Insert C_AllocationHdr and C_AllocationLine
		while (itEntries.hasNext()) {
			Entry<Integer, List<MPayment>> element = itEntries.next();
			success = createAllocation(element.getKey(), element.getValue());
		}
		
		s_log.log( Level.INFO, "iteraAllocation OK >"+success);
		return success;
	}

	
	/**
	 * Insert C_AllocationHdr and C_AllocationLine
	 */
	private String createAllocation(int ctapac, List<MPayment> lstPaymentHdr) {
		MEXMECtaPac mCtaPac = new MEXMECtaPac(header.getCtx(), ctapac, trxName);
		String success = X_C_Invoice.DOCSTATUS_Invalid;
		
		// que existan pagos por definir
		if (lstPaymentHdr != null && !lstPaymentHdr.isEmpty()) {

			// Crear el encabezado de la asignacion de pagos
			final MAllocationHdr alloc = new MAllocationHdr(header.getCtx(),
					false, header.getDateTrx(), Env.getC_Currency_ID(header
							.getCtx()), Msg.translate(header.getCtx(),
									"Claim payment ID")
									+ ": "
									+ header.getDocumentNo()+" [s]"
								, trxName);
			alloc.setAD_Org_ID(header.getAD_Org_ID());
			alloc.setEXME_ClaimPaymentH_ID(header.getEXME_ClaimPaymentH_ID());
			alloc.setEXME_CtaPac_ID(ctapac);

			
			if (!alloc.save(trxName)) {
				viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
						"msj.error"));

			} else {
				
				// Buscar la Factura/nota de debito
				int invId = findInvoice(mCtaPac, lstPaymentHdr.get(0), 0);
				s_log.log( Level.INFO, "createAllocation invId"+invId);

				// Relacionar los pagos con la factura
				for (int i = 0; i < lstPaymentHdr.size(); i++) {
					createPaymentAllocation(alloc, lstPaymentHdr.get(i), invId);
				}

				// Relacionar los anticipos de la cuenta paciente para el socio con la factura
				List<MPayment> lstPays = MEXMEPayment.getPaymentCtaPac(
						header.getCtx(), ctapac, header.getC_BPartner_ID(), trxName);
				for (int i = 0; i < lstPays.size(); i++) {
					createPaymentAllocation(alloc, lstPays.get(i), invId);
				}

				// Procesar
				alloc.processIt(DocAction.ACTION_Complete);
				if (!alloc.save(trxName)) {
					viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,"msj.error"));
				}

				s_log.log( Level.INFO, "createAllocation alloc.getDocStatus"+alloc.getDocStatus());

				if(!mapaAlloc.containsKey(ctapac)){
					mapaAlloc.put(ctapac, alloc);
				}
				
				if(!mapaInv.containsKey(ctapac)){
					mapaInv.put(ctapac, new MInvoice(header.getCtx(), invId, trxName));
				}

			}
		} else {
			viewErrors.add(new ModelError(ModelError.TIPOERROR_Error, "msj.error"));
		}

		if (viewErrors == null || viewErrors.isEmpty())
			success = X_C_Invoice.DOCSTATUS_Closed;

		return success;
	}

	/**
	 * Busca la factura/nota de debito correspondiente
	 * @param pac
	 * @param payment
	 * @return
	 */
	private int findInvoice(final MEXMECtaPac pac, final MPayment pPayment, final int C_Invoice_ID){
		// Factura relacionada al pago
		int invId = C_Invoice_ID;
		if(pPayment!=null){
			invId = pPayment.getC_Invoice_ID();
		}
		
		// Factura/Nota de debito previa para la cuenta
		if(invId<=0){

			// id de la factura SELECT C_Invoice_ID FROM C_Invoice WHERE C_BPartner_ID=? AND EXME_CtaPac_ID=? AND ConfType = ?
			invId = MInvoice.getByBPartner(header.getCtx(), header.getBillingType(), header.getC_BPartner_ID(), pac.getEXME_CtaPac_ID(),null);
		}

		// Crear la nota de debito en caso de que esta no exista
		// para relacionar los pagos
		if (invId <= 0) {

			// Pagos capturados en esta transaccion
			List<MEXMEClaimPayment> lstPayments = paymentCtaPac(header.getCtx(),
					pac.getEXME_CtaPac_ID(), //billingType,
					header.getEXME_ClaimPaymentH_ID(), true, trxName);

			invId = getInvoice(
					new MInvoice(header.getCtx(), 0, trxName), 
					lstPayments, 
					header.getC_BPartner_ID(), 
					pac,
					header.getBillingType());

			if (invId > 0){
				// Siguiente estatus para el documento factura hasta cerrar
				completeInvoice(header.getCtx(), invId);// catch
			}
		}
		return invId;
	}
	
	
	/**
	 * create relation Payment - allocation
	 * 
	 * @param alloc
	 */
	public BigDecimal createPaymentAllocation(MAllocationHdr alloc,
			MPayment payment, final int invoiceId) {
		BigDecimal totalLine = Env.ZERO;
		
		if (payment != null && payment.getC_Payment_ID() > 0) {
			MAllocationLine aLine = null;
			s_log.log( Level.INFO, "createPaymentAllocation add payment");
			if (payment.isReceipt()) {
				aLine = new MAllocationLine(alloc, 
						Env.ZERO.compareTo(payment.getPayAmt()) == 0 ? payment.getPayAmt(): payment.getChargeAmt(), 
						payment.getDiscountAmt(),
						payment.getWriteOffAmt(), 
						payment.getOverUnderAmt());
				aLine.setAmount(payment.getPayAmt());
			} else {
				aLine = new MAllocationLine(alloc, payment.getPayAmt().compareTo(Env.ZERO) == 0 ? payment.getPayAmt().negate() : payment.getChargeAmt().negate(), 
						payment.getDiscountAmt().negate(), 
						payment.getWriteOffAmt().negate(), 
						payment.getOverUnderAmt().negate());
				aLine.setAmount(payment.getPayAmt().negate());
			}
			
			aLine.setDocInfo(payment.getC_BPartner_ID(), 0,
					invoiceId);
			aLine.setPaymentInfo(payment.getC_Payment_ID(), 0);
			aLine.setC_Invoice_ID(invoiceId);
			//
			aLine.setC_Charge_ID(payment.getC_Charge_ID());
			if (!aLine.save(trxName)) {
				viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
						"msj.error"));
			}

			totalLine = aLine.getAmount();
		}	
		s_log.log( Level.INFO, "createPaymentAllocation totalLine"+totalLine);
		return totalLine;
	}

	/*******************************************************************************/

	/**
	 * id debit memo
	 */
	private int debitMemoId = 0;

	/**
	 * create invoice
	 * 
	 * @param lstNextStep
	 * @return
	 */
	private String createInvoice(final List<IEncounterStep> lstNextStep) {
		String success = X_C_Invoice.DOCSTATUS_Closed;
		
		// Accion/cuenta
		for (int i = 0; i < lstNextStep.size(); i++) {
			
			MEXMECtaPac pac = new MEXMECtaPac(header.getCtx(), lstNextStep.get(i).getEXME_CtaPac_ID(), trxName);
			
			// Siguiente accion
			String action = lstNextStep.get(i).getNextStep();
			// siguiente estatus
			String status = lstNextStep.get(i).getNextStatus();
			// Factura anteriormente saldada 
			int factOrig = mapaInv.get(pac.getEXME_CtaPac_ID()).getC_Invoice_ID();
			
			// Cambia los estatus de la cuenta
			if (header.getBillingType().equals(
					X_EXME_ClaimPaymentH.BILLINGTYPE_Technical)) {
				pac.setInstitutionalStep(action);
				pac.setInstitutionalStatus(status);
				
			} else {
				pac.setProfessionalStep(action);
				pac.setProfessionalStatus(status);
			}
			
			// Graba 
			success = !pac.save(trxName) ? X_C_Invoice.DOCSTATUS_Invalid
					: X_C_Invoice.DOCSTATUS_Closed;
			
			
			// si no se ejecuta la next accion no se cambia el estatus
			if(nextAction(pac, action, factOrig)){
				success = !pac.save(trxName) ? X_C_Invoice.DOCSTATUS_Invalid
						: X_C_Invoice.DOCSTATUS_Closed;
			}

		}// fin cta
		return success;
	}
	
	/**
	 * Se ejecuta la sig accion
	 * @param pac
	 * @param action
	 * @param factOrig
	 * @param prio
	 * @return true: se cumple la accion, false no se cumple la accion
	 */
	private boolean nextAction(final MEXMECtaPac pac, final  String action, final int factOrig ){
	
		// Siguiente accion, regresa el numero de factura/nota de debito creada
		return nextAction(
				header.getCtx(),
				pac,
				socioNextAction(action, header.getCtx(),pac.getEXME_Paciente_ID(), pac.getEXME_CtaPac_ID()),
				new MInvoice(header.getCtx(), factOrig, trxName),
				//T,P
				header.getBillingType(), header.getEXME_ClaimPaymentH_ID(), false) >= 0 ;
	}

	/**
	 * PROFESSIONALSTEP_AD_Reference_ID=1200595;
	 * INSTITUTIONALSTEP_AD_Reference_ID=1200595;
	 * 
	 * @param action
	 * @return
	 */
	public int priority(String action) {
		/** Default = D */

		if (header.getBillingType().equals(
				X_EXME_ClaimPaymentH.BILLINGTYPE_Technical)) {

			if (X_EXME_CtaPac.INSTITUTIONALSTEP_Default.equals(action)) {
				return -1;
			} else if (X_EXME_CtaPac.INSTITUTIONALSTEP_FirstInsurance
					.equals(action)) {
				/** First Insurance = F */
				return 1;
			} else if (X_EXME_CtaPac.INSTITUTIONALSTEP_SecondInsurance
					.equals(action)) {
				/** Second Insurance = S */
				return 2;
			} else if (X_EXME_CtaPac.INSTITUTIONALSTEP_ThirdInsurance
					.equals(action)) {
				/** Third Insurance = T */
				return 3;
			} else if (X_EXME_CtaPac.INSTITUTIONALSTEP_OtherInsurance
					.equals(action)) {
				/** Other Insurance = O */
				return 4;
			} else if (X_EXME_CtaPac.INSTITUTIONALSTEP_SelftPay.equals(action)) {// si
				// paga
				/** Selft Pay = P */
				return 0;
			}

		} else {

			if (X_EXME_CtaPac.PROFESSIONALSTEP_Default.equals(action)) {
				return -1;
			} else if (X_EXME_CtaPac.PROFESSIONALSTEP_FirstInsurance
					.equals(action)) {
				/** First Insurance = F */
				return 1;
			} else if (X_EXME_CtaPac.PROFESSIONALSTEP_SecondInsurance
					.equals(action)) {
				/** Second Insurance = S */
				return 2;
			} else if (X_EXME_CtaPac.PROFESSIONALSTEP_ThirdInsurance
					.equals(action)) {
				/** Third Insurance = T */
				return 3;
			} else if (X_EXME_CtaPac.PROFESSIONALSTEP_OtherInsurance
					.equals(action)) {
				/** Other Insurance = O */
				return 4;
			} else if (X_EXME_CtaPac.PROFESSIONALSTEP_SelftPay.equals(action)) {// si
				// paga
				/** Selft Pay = P */
				return 0;
			}
		}
		return 99;
	}

	/**
	 * Crea la factura
	 * @param invoicehdr
	 * @param lstPayments
	 * @param C_BPartner_ID
	 * @param ctapac
	 * @return
	 */
	private int getInvoice(MInvoice invoicehdr, List<MEXMEClaimPayment> lstPayments, 
			int C_BPartner_ID, MEXMECtaPac ctapac, String confType){//T,P
		int invoiceNextID = -1;

		// Itera cada pago para que sea la linea de la nota de debito (C_Invoice)
		for (int i = 0; i < lstPayments.size(); i++) {
			if (i == 0) {
				MInvoice nextInvoice = new MInvoice(ctapac.getCtx(), 0,
						trxName);
				PO.copyValues(invoicehdr, nextInvoice);
				nextInvoice.setBackoffice(false);
				nextInvoice.setC_BPartner_ID(C_BPartner_ID);
				nextInvoice.setC_DocType_ID(debitMemoId);
				nextInvoice.setC_DocTypeTarget_ID(debitMemoId);
				nextInvoice.setEXME_CtaPac_ID(ctapac.getEXME_CtaPac_ID());
				nextInvoice.setConfType(confType);
				
				invoiceNextID = nextInvoice.save(trxName) ? nextInvoice
						.getC_Invoice_ID() : -1;
			}

			// Que exista la factura
			if (invoiceNextID <= 0) {
				break;
			}

			MInvoiceLine line = new MInvoiceLine(ctapac.getCtx(),
					0, trxName);
			line.setLine(i);
			line.setC_Invoice_ID(invoiceNextID);
			line.setDescription(""
					+ lstPayments.get(i).getEXME_ClaimPayment_ID());
			line.setC_Charge_ID(lstPayments.get(i).getC_Charge_ID());
			if(lstPayments.get(i).getCharge()!=null && lstPayments.get(i).getCharge().getTaxID()>0){
				line.setC_Tax_ID(lstPayments.get(i).getCharge().getTaxID());
			} else {
				line.setC_Tax_ID(MEXMETax.getDefaultTaxID(ctapac.getCtx(), trxName));
			}
			line.setQtyInvoiced(Env.ONE);
			line.setPrice(lstPayments.get(i).getAmt_Paid());
			line.setPriceLimit(lstPayments.get(i).getAmt_Paid());
			line.setPriceList(lstPayments.get(i).getAmt_Paid());
			line.setLineNetAmt(); 
			line.setTaxAmt (); 
			line.setLineTotalAmt(line.getLineNetAmt().add(line.getTaxAmt ()));
			line.setProcessed(false);
			line.setQtyEntered(Env.ONE);
			line.setPriceEntered(lstPayments.get(i).getAmt_Paid());
			
			// Guardar la linea
			if (!line.save(trxName)) {
				invoiceNextID = -1;
				break;
			}
		} 

		return invoiceNextID;
	}
	
	/**
	 * 
	 * @param ctx
	 * @param ctapac
	 * @param C_BPartner_ID
	 * @param invoicehdr
	 * @param EXME_ClaimPaymentH_ID
	 * @param priority
	 * @param trxName
	 * @return
	 */
	private int nextAction(Properties ctx, MEXMECtaPac ctapac,
			int C_BPartner_ID, MInvoice invoicehdr, String billingType,
			int EXME_ClaimPaymentH_ID, final boolean all
			) {
		
		int invoiceNextID = 0;
		int EXME_CtaPac_ID = ctapac.getEXME_CtaPac_ID();
		
		// Factuta del nuevo bpartner (nextstep)
		// id de la factura SELECT C_Invoice_ID FROM C_Invoice WHERE C_BPartner_ID=? AND EXME_CtaPac_ID=? AND ConfType = ?
		int id = getOfBPartner(ctx, billingType, C_BPartner_ID, EXME_CtaPac_ID);
		if (id <= 0) {

			// Ajustes capturados en esta transaccion que pasan al sig. pagador
			List<MEXMEClaimPayment> lstPayments = paymentCtaPac(ctx,
					ctapac.getEXME_CtaPac_ID(), //billingType,
					EXME_ClaimPaymentH_ID, all, trxName);

			// Nueva nota de cargo
			invoiceNextID = getInvoice(invoicehdr, lstPayments, C_BPartner_ID, ctapac, billingType);//T,P
			
			// Si no hay mas que saldar(ajustes) aun cuando se haya elegido un sig. paso
			if(lstPayments==null || lstPayments.size()<=0){
				invoiceNextID = 0;
			}

			// Invoice
			if (invoiceNextID > 0){
				
				MInvoice notaCargo = new MInvoice(ctx, invoiceNextID, trxName);
				// Siguiente estatus para el documento factura hasta cerrar
				final String docStatus = notaCargo.completeIt();
				
				if (!MInvoice.DOCSTATUS_Invalid.equals(docStatus)) {
					notaCargo.setDocStatus(X_C_Invoice.DOCSTATUS_Completed);
					notaCargo.setDocAction(X_C_Invoice.DOCSTATUS_Closed);
				
//				if (MInvoice.DOCSTATUS_Completed.equals(docStatus)) {
//					notaCargo.setDocStatus(docStatus);
					// closeIt
					if (!notaCargo.closeIt()) {
						throw new MedsysException("Not closeit");
					}
					if (!notaCargo.save()) {
						throw new MedsysException("Not completed");
					}
				} else {
					MDocType dt = MDocType.get(Env.getCtx(), notaCargo.getC_DocTypeTarget_ID());
					if (!MPeriod.isOpen(Env.getCtx(), notaCargo.getDateAcct(), dt.getDocBaseType(), Env.getAD_Org_ID(Env.getCtx()))) {
						throw new MedsysException(Utilerias.getAppMsg(Env.getCtx(), "msj.error.postPeriod"));
					} else {
						throw new MedsysException("Not completed");
					}
				}
				
				// Se marca que no hay anticipos para la factura generada
				// y saldar aquellos que no vaya a pagar el paciente
				if(!completeAllocationNextStep(notaCargo)){
					s_log.log( Level.WARNING, "Not exist pre-payments for invoice : "+invoiceNextID);
				}
				// 

			}
		} else {
			invoiceNextID = id;
		}
		return invoiceNextID;
	}

	/**
	 * completeInvoice
	 */
	private void completeInvoice(Properties ctx, final int invId) {
		MInvoice invoice = new MInvoice(ctx, invId, trxName);
		final String docStatus = invoice.completeIt();
		if (!MInvoice.DOCSTATUS_Invalid.equals(docStatus)) {
			invoice.setDocStatus(X_C_Invoice.DOCSTATUS_Completed);
			invoice.setDocAction(X_C_Invoice.DOCSTATUS_Closed);
//			
//		if (MInvoice.DOCSTATUS_Completed.equals(docStatus)) {
//			invoice.setDocStatus(docStatus);
			// closeIt
			if (!invoice.closeIt()) {
				throw new MedsysException("Not closeit");
			}
			if (!invoice.save()) {
				throw new MedsysException("Not completed");
			}
		} else {
			throw new MedsysException("Not completed");
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean completeAllocationNextStep(MInvoice pMInvoice) {
		// Pagos anticipos de la cuenta paciente para el socio
		List<MPayment> lstPays = MEXMEPayment.getPaymentCtaPac(pMInvoice
				.getCtx(), pMInvoice.getEXME_CtaPac_ID(),
				pMInvoice.getC_BPartner_ID(), pMInvoice.get_TrxName());

		// Creamos la distribucion de los pagos de la nota de cargo
		if (!X_C_Invoice.DOCSTATUS_Closed.equals(createAllocationNextStep(lstPays,pMInvoice))
				){
			return false;
		}
		return true;
	}
	
	/**
	 * createAllocation
	 * 
	 * @param lstPaymentHdr
	 *            Pagos anticipos desde cach
	 * @param invoice
	 * @return
	 */
	private String createAllocationNextStep(List<MPayment> lstPaymentHdr,
			MInvoice invoice) {
		int docType = MEXMEDocType.getOfName(Env.getCtx(), "AR Receipt",
				null);
		MConfigPre configPre = MConfigPre.get(Env.getCtx(), null);
		
		// cuenta
		int ctapac = invoice.getEXME_CtaPac_ID();
		String success = X_C_Invoice.DOCSTATUS_Invalid;
		
		// si existen anticipos
		if (lstPaymentHdr != null && !lstPaymentHdr.isEmpty()) {

			final MAllocationHdr alloc = new MAllocationHdr(invoice.getCtx(),
					false, invoice.getDateInvoiced(),
					Env.getC_Currency_ID(invoice.getCtx()), 
					Msg.translate(invoice.getCtx(), "Next")
					+" "
					+ Msg.translate(invoice.getCtx(), "Payment ID")
							+ ": "
							+ invoice.getDocumentNo() + " [t]",
					invoice.get_TrxName());
			alloc.setAD_Org_ID(invoice.getAD_Org_ID());
			alloc.setEXME_CtaPac_ID(ctapac);
			if (!alloc.save(invoice.get_TrxName())) {
				viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
						"msj.error"));

			} else {

				// Total de pagos anticipados
//				BigDecimal totalPayment = Env.ZERO;
				for (int i = 0; i < lstPaymentHdr.size(); i++) {

					// crea las lineas de la asignación de pagos /factura <lstAllocationLine>
					createAllocationLineNext(alloc,lstPaymentHdr.get(i), invoice.getC_Invoice_ID());
				}

				// Existen pagos que se deben saldar por que ya no los paga el paciente ? //#Card 144
				// En la extension cero el socio debe ser el paciente BPartner->Standard
				if(MEXMECtaPacExt.getExtCero(
						invoice.getCtx(), ctapac, invoice.get_TrxName())
							.getC_BPartner_ID() == invoice.getC_BPartner_ID()){
//					
					List<MCharge> mCharges = getChargesInv(invoice.get_TrxName(), invoice.getC_Invoice_ID(), invoice.getCtx());
					
					for (int i = 0; i < mCharges.size(); i++) {
						if(mCharges.get(i).getExcepcion().contains("P")){
							
							//cambiar a MPAYment/

							// Crear el allocation con los Payment
							MPayment mPayment = new MPayment(invoice.getCtx(), 0, null);
//							mPayment.setBackoffice(false);
							mPayment.setPayAmt(mCharges.get(i).getPayAmt());
							mPayment.setC_Charge_ID(mCharges.get(i).getC_Charge_ID());
							mPayment.setEXME_CtaPac_ID(mCharges.get(i).getEXME_CtaPac_ID());
							mPayment.setC_Invoice_ID(mCharges.get(i).getC_Invoice_ID());
							mPayment.setConfType(invoice.getConfType());//T,P
							mPayment.setAmount(mCharges.get(i).getPayAmt());
							mPayment.setC_BPartner_ID(invoice.getC_BPartner_ID());
							mPayment.setDateAcct(invoice.getDateOrdered());
							
							mPayment.setC_DocType_ID(docType);
							mPayment.setC_Currency_ID(Env.getContextAsInt(Env.getCtx(),
									"$C_Currency_ID"));
							mPayment.setReciboNo("ticket" + i + " "+ ctapac);
							mPayment.setC_BankAccount_ID(configPre.getC_BankAccount_ID());
							mPayment.setDocStatus(MPayment.DOCSTATUS_Drafted);
							mPayment.setDocAction(MPayment.DOCACTION_Complete);
							
							if(mPayment.save(invoice.get_TrxName())){
								createAllocationLineNext(alloc, mPayment, invoice.getC_Invoice_ID());
							}
						}
					}	
				}
				
				//
				alloc.processIt(DocAction.ACTION_Complete);
				if (!alloc.save(invoice.get_TrxName())) {
					viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
							"msj.error"));
				}
			}
		}

		if (viewErrors == null || viewErrors.isEmpty())
			success = X_C_Invoice.DOCSTATUS_Closed;

		return success;
	}
	
	/**
	 * create relation Payment - allocation
	 * 
	 * @param alloc
	 */
	private BigDecimal createAllocationLineNext(MAllocationHdr alloc,
			org.compiere.model.MPayment payment, int C_Invoice_ID) {
		BigDecimal totalLine = Env.ZERO;
		if (payment != null && payment.getC_Payment_ID() > 0) {
			MAllocationLine aLine = null;

			if (payment.isReceipt()) {
				aLine = new MAllocationLine(alloc, payment.getPayAmt()
						.compareTo(Env.ZERO) == 0 ? payment.getPayAmt()
						: payment.getChargeAmt(), payment.getDiscountAmt(),
						payment.getWriteOffAmt(), payment.getOverUnderAmt());
				aLine.setAmount(payment.getPayAmt());
			} else {
				aLine = new MAllocationLine(alloc, payment.getPayAmt()
						.compareTo(Env.ZERO) == 0 ? payment.getPayAmt()
						.negate() : payment.getChargeAmt().negate(), payment
						.getDiscountAmt().negate(), payment.getWriteOffAmt()
						.negate(), payment.getOverUnderAmt().negate());
				aLine.setAmount(payment.getPayAmt().negate());
			}
			aLine.setDocInfo(payment.getC_BPartner_ID(), 0,
					payment.getC_Invoice_ID());
			aLine.setPaymentInfo(payment.getC_Payment_ID(), 0);
			aLine.setC_Invoice_ID(C_Invoice_ID);
			aLine.setC_Charge_ID(payment.getC_Charge_ID());
			if (!aLine.save(alloc.get_TrxName())) {
				viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
						"msj.error"));
			}

			totalLine = aLine.getAmount();
		}
		return totalLine;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private List<MAllocationHdr> lstAllocation;
//	
//	public List<MAllocationHdr> getLstAllocation() {
//		return lstAllocation;
//	}
//
//	public void setLstAllocation(List<MAllocationHdr> lstAllocation) {
//		this.lstAllocation = lstAllocation;
//	}

	/**
	 * 
	 * @param nextAction
	 * @param ctx
	 * @param pacienteID
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	private int socioNextAction(String nextAction, Properties ctx,
			int pacienteID, int EXME_CtaPac_ID) {
		MEXMEPacienteAseg mPacienteAseg = null;
		if (header.getBillingType().equals(
				X_EXME_ClaimPaymentH.BILLINGTYPE_Technical)) {
			if (nextAction
					.equals(X_EXME_CtaPac.INSTITUTIONALSTEP_FirstInsurance)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 1, Constantes.INST);
			} else if (nextAction
					.equals(X_EXME_CtaPac.INSTITUTIONALSTEP_SecondInsurance)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 2, Constantes.INST);
			} else if (nextAction
					.equals(X_EXME_CtaPac.INSTITUTIONALSTEP_ThirdInsurance)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 3, Constantes.INST);
			} else if (nextAction
					.equals(X_EXME_CtaPac.INSTITUTIONALSTEP_OtherInsurance)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 4, Constantes.INST);
			} else if (nextAction
					.equals(X_EXME_CtaPac.INSTITUTIONALSTEP_SelftPay)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 0, Constantes.INST);
			}
		} else {
			if (nextAction
					.equals(X_EXME_CtaPac.PROFESSIONALSTEP_FirstInsurance)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 1, Constantes.PROF);
			} else if (nextAction
					.equals(X_EXME_CtaPac.PROFESSIONALSTEP_SecondInsurance)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 2, Constantes.PROF);
			} else if (nextAction
					.equals(X_EXME_CtaPac.PROFESSIONALSTEP_ThirdInsurance)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 3, Constantes.PROF);
			} else if (nextAction
					.equals(X_EXME_CtaPac.PROFESSIONALSTEP_OtherInsurance)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 4, Constantes.PROF);
			} else if (nextAction
					.equals(X_EXME_CtaPac.PROFESSIONALSTEP_SelftPay)) {
				mPacienteAseg = MEXMEPacienteAseg.getAsegPrior(ctx, pacienteID,
						EXME_CtaPac_ID, 0, Constantes.PROF);
			}
		}
		return mPacienteAseg != null ? mPacienteAseg.getC_BPartner_ID()
				: new org.compiere.model.MEXMEPaciente(ctx, pacienteID, null)
						.getC_BPartner_ID();
	}

	/*************************************************/
	public void add(MEXMEClaimPayment copyValue) {
		lstClaimPayment.add(copyValue);
	}

	public MEXMEClaimPaymentH getHeader() {
		return header;
	}

	public void setHeader(MEXMEClaimPaymentH header) {
		this.header = header;
	}

	public List<MEXMEClaimPayment> getLstClaimPayment() {
		return lstClaimPayment;
	}

	public void setLstClaimPayment(List<MEXMEClaimPayment> lstClaimPayment) {
		this.lstClaimPayment = lstClaimPayment;
	}

//	public MEXMEAllocationHdr getAllocation() {
//		return allocation;
//	}
//
//	public void setAllocation(MEXMEAllocationHdr allocation) {
//		this.allocation = allocation;
//	}
//
//	public List<MEXMEAllocationLine> getLstAllocationLine() {
//		return lstAllocationLine;
//	}
//
//	public void setLstAllocationLine(List<MEXMEAllocationLine> lstAllocationLine) {
//		this.lstAllocationLine = lstAllocationLine;
//	}

	/**
	 * Solo debe haber una factura por aseguradora, asi que solo deberia
	 * resolverte una.
	 * 
	 * En el caso d que el pago sea menor o mayor que la factura, la factura
	 * original debe de cancelarse y crear una nueva con los montos recibidos(
	 * segun las instrucciones que en su momento dio Gerardo c relatvos al
	 * manejo que compiere hace d facturas y pagos, el t puede dar mas detallle)
	 * 
	 * En caso d haber saldo d la cuenta este generara una nueva factura cuando
	 * la accion seleccionada sea moverlo a la siguiente aseguradora
	 * 
	 * En caso d ajustes d tipo copago coaseguro o deducible todos estos junto
	 * con el saldo d la ultima aseguradora del encuentro debera generar una
	 * factura para el guarantor
	 * 
	 * En el caso d cashline, creo que eso solo se llena cuando los pagos se
	 * hacen desde caja(cashiering)
	 * 
	 * Si quedaron dudas o Gerardo C corrije algun comentario qu haya vertido
	 * porfa buscame por correo porque estare en reunion desde este momento y
	 * hasta las 2 d la tarde
	 */

	public class Payment {

		public String C_DocType_ID = null;
		public int C_Currency_ID = 0;
		public String ReciboNo = null;
		public int EXME_CtaPac_ID = 0;
		public int C_BPartner_ID = 0;
		public int C_Invoice_ID = 0;
		public int C_BankAccount_ID = 0;
		public int C_Charge_ID = 0;
		public BigDecimal ChargeAmt = Env.ZERO;
		public BigDecimal PayAmt = Env.ZERO;
		public boolean canBeSaved = true;
		public MPayment paymemt = null;

		/**
		 * TYPE_InsurancePayments,
		 * 
		 * @return
		 */
		public boolean isCanBeSaved() {

			if (!C_DocType_ID.equals(X_C_Charge.TYPE_InsurancePayments)) {
				canBeSaved = false;
			}

			return canBeSaved;
		}

		public void setCanBeSaved(boolean canBeSaved) {
			this.canBeSaved = canBeSaved;
		}

		public void setC_DocType_ID(String id) {
			C_DocType_ID = id;
		}

		public void setC_Currency_ID(int id) {
			C_Currency_ID = id;
		}

		public void setReciboNo(String data) {
			ReciboNo = data;
		}

		public void setEXME_CtaPac_ID(int id) {
			EXME_CtaPac_ID = id;
		}

		public void setC_BPartner_ID(int id) {
			C_BPartner_ID = id;
		}

		public void setC_Invoice_ID(int id) {
			C_Invoice_ID = id;
		}

		public void setC_BankAccount_ID(int id) {
			C_BankAccount_ID = id;
		}

		public void setC_Charge_ID(int id) {
			C_Charge_ID = id;
		}

		public void setChargeAmt(BigDecimal number) {
			ChargeAmt = number;
		}

		public void setPayAmt(BigDecimal number) {
			PayAmt = number;
		}

		public String getC_DocType_ID() {
			return C_DocType_ID;
		}

		public int getC_Currency_ID() {
			return C_Currency_ID;
		}

		public String getReciboNo() {
			return ReciboNo;
		}

		public int getEXME_CtaPac_ID() {
			return EXME_CtaPac_ID;
		}

		public int getC_BPartner_ID() {
			return C_BPartner_ID;
		}

		public int getC_Invoice_ID() {
			return C_Invoice_ID;
		}

		public int getC_BankAccount_ID() {
			return C_BankAccount_ID;
		}

		public int getC_Charge_ID() {
			return C_Charge_ID;
		}

		public BigDecimal getChargeAmt() {
			return ChargeAmt;
		}

		public BigDecimal getPayAmt() {
			return PayAmt;
		}

		public MPayment getPaymemt() {
			return paymemt;
		}

		public void setPaymemt(MPayment paymemt) {
			this.paymemt = paymemt;
		}
	}

	/**
	 * Agrupar por tipo de ajuste
	 * 
	 * @param ctx
	 * @param C_BPartner_ID
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	private List<MPayment> claimPaymentGroup(Properties ctx, int EXME_ClaimPaymentH_ID, int C_BPartner_ID, Timestamp dateOrdered,
			String trxName) {
		List<MPayment> claim = new ArrayList<MPayment>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cp.* ")
				//.append(" cp.C_Charge_ID, cp.EXME_CtaPac_ID, cp.C_Invoice_ID, c.Type ")
				.append(" FROM EXME_ClaimPayment cp  ")
				//.append(" INNER JOIN C_Charge c ON c.C_Charge_ID = cp.C_Charge_ID ")
				.append(" WHERE cp.IsActive = 'Y'  ")
				.append(" AND cp.EXME_ClaimPaymentH_ID = ?  ")
				//.append(" GROUP BY cp.C_Charge_ID, cp.EXME_CtaPac_ID, cp.C_Invoice_ID ,  c.Type ")
				.append(" ORDER BY cp.EXME_CtaPac_ID, cp.C_Invoice_ID, cp.C_Charge_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_ClaimPaymentH_ID);
			rs = pstmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				
				MPayment payment = new MPayment(ctx, 0, trxName);
//				payment.setBackoffice(false);				
				payment.setEXME_CtaPac_ID(rs.getInt("EXME_CtaPac_ID"));  
				payment.setC_Invoice_ID(rs.getInt("C_Invoice_ID"));     
				payment.setAmount(rs.getBigDecimal("AMT_PAID"));
				payment.setC_Charge_ID(rs.getInt("C_CHARGE_ID"));
				payment.setConfType(header.getBillingType());//T,P
				
				if (rs.getInt("C_BPartner_ID") == 0) {
					payment.setC_BPartner_ID(C_BPartner_ID);
				} else {
					payment.setC_BPartner_ID(rs.getInt("C_BPartner_ID"));
				}

				if (rs.getTimestamp("DATEORDERED") == null) {
					payment.setDateAcct(dateOrdered);
				} else {
					payment.setDateAcct(rs.getTimestamp("DATEORDERED"));
				}
				
				payment.setC_DocType_ID(docType);
				payment.setC_Currency_ID(Env.getContextAsInt(Env.getCtx(),
						"$C_Currency_ID"));
				payment.setReciboNo("ticket" + i + " "+ rs.getInt("EXME_CtaPac_ID"));
				payment.setC_BankAccount_ID(configPre.getC_BankAccount_ID());
				payment.setPayAmt(rs.getBigDecimal("AMT_PAID"));
				payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
				payment.setDocAction(MPayment.DOCACTION_Complete);
				payment.setEXME_ClaimPayment_ID(rs.getInt(MPayment.COLUMNNAME_EXME_ClaimPayment_ID));
				claim.add(payment);	
				i++;				
			}

		} catch (Exception e) {
			viewErrors.add(new ModelError(false, ModelError.TIPOERROR_Error, e
					.getMessage()));
		} finally {
			DB.close(rs, pstmt);
		}
		
		return claim;
	}

	/**
	 * Accion por cuenta paciente
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param C_BPartner_ID
	 * @param C_Invoice_ID
	 * @param EXME_ClaimPaymentH_ID
	 * @param priority
	 * @param trxName
	 * @return
	 */
	private List<MEXMEClaimPayment> paymentCtaPac(Properties ctx,
			final int EXME_CtaPac_ID, 
			//final String billingType, 
			final int EXME_ClaimPaymentH_ID,
			boolean all, 
			final String trxName) {

		List<MEXMEClaimPayment> claim = new ArrayList<MEXMEClaimPayment>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p.* ");
		sql.append(" FROM EXME_ClaimPayment p ");
		sql.append(" INNER JOIN C_Charge ad ON p.C_Charge_ID = ad.C_Charge_ID AND ad.IsActive = 'Y' ");
		sql.append(" WHERE p.IsActive = 'Y' ");
		sql.append(" AND p.EXME_ClaimPaymentH_ID = ? ");
		sql.append(" AND p.EXME_CtaPac_ID = ? ");
		if(!all){
			sql.append(" AND ad.NextInvoice = 'Y'");
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_ClaimPaymentH_ID);
			pstmt.setInt(2, EXME_CtaPac_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				claim.add(new MEXMEClaimPayment(ctx, rs, trxName));
			}

		} catch (Exception e) {
			viewErrors.add(new ModelError(false, ModelError.TIPOERROR_Error, e
					.getMessage()));
			claim = null;
		} finally {
			DB.close(rs, pstmt);
		}

		//
		return claim;
	}

	/**
	 * Factura
	 * 
	 * @param billingType
	 * @param C_BPartner_ID
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	public static int getOfBPartner(Properties ctx, String billingType, int C_BPartner_ID,
			int EXME_CtaPac_ID) {
		int id = 0;
		String sql = " SELECT C_Invoice_ID FROM C_Invoice WHERE IsActive = 'Y' AND C_BPartner_ID=? " +
				" AND EXME_CtaPac_ID=? AND ConfType = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_BPartner_ID);
			pstmt.setInt(2, EXME_CtaPac_ID);
			pstmt.setString(3, billingType);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
//			viewErrors.add(new ModelError(false, ModelError.TIPOERROR_Error, e
//					.getMessage()));
			id = 0;
		} finally {
			DB.close(rs, pstmt);
		}

		return id;
	} // getOfBPartner
	
	/**
	 * Saldar los cargos que no pagara el paciente
	 * @param ctx
	 * @param ctapacId
	 * @param C_Invoice_ID
	 * @param trxName
	 * @return
	 */
//	private String notaDelPaciente(final Properties ctx, final int ctapacId, final int C_Invoice_ID, final String trxName){
//		String success = null;
//		try {// Lista de cargos que se saldan en la nota del paciente
//			List<MCharge> invLine = getChargesInv(trxName, C_Invoice_ID, ctx);
//			for (int i = 0; i < invLine.size(); i++) {
//				if(invLine.get(i).getExcepcion().contains("P")){
//					
//					//cambiar a MPAYment/
//
//					// Crear el allocation con los Payment
//					MPayment claimPayment = new MPayment(ctx, 0, null);
//					claimPayment.setPayAmt(invLine.get(i).getPayAmt());
//					claimPayment.setC_Charge_ID(invLine.get(i).getC_Charge_ID());
//					claimPayment.setEXME_CtaPac_ID(invLine.get(i).getEXME_CtaPac_ID());
//					claimPayment.setC_Invoice_ID(invLine.get(i).getC_Invoice_ID());
//					//claimPayment.setC_DocType_ID(invLine.get(i).getType());
//
//					List<MPayment> claim = new ArrayList<MPayment>();
//					claim.add(claimPayment);
//					success = createAllocation(ctapacId, claim);
//				}
//			}	
//		} catch (Exception e) {
//			s_log.log( Level.WARNING, "charge corresponding to the lines of the note");
//		}
//		return success;
//	}
	
	/**
	 * Factura
	 * 
	 * @param billingType
	 * @param C_BPartner_ID
	 * @param EXME_CtaPac_ID
	 * @return
	 */
	private List<MCharge> getChargesInv(String trxName, int C_Invoice_ID,
			Properties ctx) {
		List<MCharge> lst = new ArrayList<MCharge>();
		String sql = " SELECT c.*, il.PRICELIST, i.EXME_CtaPac_ID, i.C_Invoice_ID " +
				" FROM C_InvoiceLine il      " +
				" inner join C_Charge  c on  il.C_Charge_ID = c.C_Charge_ID  " +
				" inner join C_Invoice i on il.C_Invoice_ID = i.C_Invoice_ID " +
				" WHERE il.C_Invoice_ID = ? and c.Excepcion like '%P%' and c.NEXTINVOICE ='Y' ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_Invoice_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCharge charge = new MCharge(ctx, rs, null);
				charge.setPayAmt(rs.getBigDecimal("PRICELIST"));
				charge.setEXME_CtaPac_ID(rs.getInt("EXME_CtaPac_ID"));
				charge.setC_Invoice_ID(rs.getInt("C_Invoice_ID"));
				lst.add(charge);
			}
		} catch (Exception e) {
			viewErrors.add(new ModelError(false, ModelError.TIPOERROR_Error, e
					.getMessage()));
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	} // getOfBPartner
	
	public List<ModelError> getViewErrors() {
		return viewErrors;
	}

	public void setViewErrors(List<ModelError> viewErrors) {
		this.viewErrors = viewErrors;
	}
	
	public HashMap<Integer, List<MPayment>> getMapaCta() {
		return mapaCta;
	}

	public void setMapaCta(HashMap<Integer, List<MPayment>> mapaCta) {
		this.mapaCta = mapaCta;
	}

	public HashMap<Integer, MInvoice> getMapaInv() {
		return mapaInv;
	}

	public void setMapaInv(HashMap<Integer, MInvoice> mapaInv) {
		this.mapaInv = mapaInv;
	}

	public HashMap<Integer, MAllocationHdr> getMapaAlloc() {
		return mapaAlloc;
	}

	public void setMapaAlloc(HashMap<Integer, MAllocationHdr> mapaAlloc) {
		this.mapaAlloc = mapaAlloc;
	}

	
}
	
