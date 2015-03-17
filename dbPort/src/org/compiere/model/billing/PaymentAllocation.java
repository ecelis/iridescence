package org.compiere.model.billing;


/**
 * Revisa que existan anticipos para la factura recien generada
 * 
 * @author twry
 * 
 */
public class PaymentAllocation {

//	/** Manejo de errores */
//	protected transient List<ModelError> viewErrors = new ArrayList<ModelError>();
//	/** Allocation line */
//	private List<MAllocationLine> lstAllocationLine = new ArrayList<MAllocationLine>();
//	/** Allocation */
//	private List<MAllocationHdr> lstAllocation = new ArrayList<MAllocationHdr>();
//	
//	/**
//	 * Relaciona los anticipos con las facturas
//	 * 
//	 * @param lstInvoice
//	 *            Facturas a relacionar
//	 * @return <true> ok
//	 */
//	
//	public static boolean allocationPayment(List<MInvoice> lstInvoice){
//		return new PaymentAllocation().complete(lstInvoice);
//	}
//	
//	/**
//	 * Relaciona los anticipos con las facturas
//	 * @param lstInvoice
//	 * @return
//	 */
//	public static List<MAllocationHdr> allocationPayment(List<MInvoice> lstInvoice, String trxName) {
//		PaymentAllocation anticipos = new PaymentAllocation();
//		if(anticipos.complete(lstInvoice)){
//			return anticipos.getLstAllocation();
//		}
//		return null;
//	}
//
//	/**
//	 * complete payment
//	 * 
//	 * @param lstInvoice
//	 * @return
//	 */
//	private boolean complete(List<MInvoice> lstInvoice) {
//		// Iteramos todas las facturas creadas
//		for (int i = 0; i < lstInvoice.size(); i++) {
//
//			// Pagos anticipos de la cuenta paciente para el socio
//			List<MPayment> lstPays = MEXMEPayment.getPaymentCtaPac(lstInvoice
//					.get(i).getCtx(), lstInvoice.get(i).getEXME_CtaPac_ID(),
//					lstInvoice.get(i).getC_BPartner_ID(), lstInvoice.get(i)
//							.get_TrxName());
//			
//			// Creamos la distribucion de los pagos de la nota de cargo
//			if (!X_C_Invoice.DOCSTATUS_Closed.equals(createAllocation(lstPays,
//					lstInvoice.get(i))))
//				return false;
//		}
//		return viewErrors.isEmpty();
//	}
//
//	/**
//	 * createAllocation
//	 * 
//	 * @param lstPaymentHdr
//	 *            Pagos anticipos desde cach
//	 * @param invoice
//	 * @return
//	 */
//	private String createAllocation(List<MPayment> lstPaymentHdr,
//			MInvoice invoice) {
////		int docType = MEXMEDocType.getOfName(Env.getCtx(), "AR Receipt",
////				null);
////		MConfigPre configPre = MConfigPre.get(Env.getCtx(), null);
////		
//		// cuenta
//		int ctapac = invoice.getEXME_CtaPac_ID();
//		String success = X_C_Invoice.DOCSTATUS_Invalid;
//		lstAllocationLine = new ArrayList<MAllocationLine>();
//		
//		// si existen anticipos
//		if (lstPaymentHdr != null && !lstPaymentHdr.isEmpty()) {
//
//			final MAllocationHdr alloc = new MAllocationHdr(invoice.getCtx(),
//					false, Env.getCurrentDate(), //invoice.getDateInvoiced()
//					Env.getC_Currency_ID(invoice.getCtx()), Msg.translate(
//							invoice.getCtx(), "Payment ID")
//							+ ": "
//							+ invoice.getDocumentNo() + " [n]",
//					invoice.get_TrxName());
//			alloc.setAD_Org_ID(invoice.getAD_Org_ID());
//			alloc.setEXME_CtaPac_ID(ctapac);
//			if (!alloc.save(invoice.get_TrxName())) {
//				viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
//						"msj.error"));
//
//			} else {
//
//				// Total de pagos anticipados
////				BigDecimal totalPayment = Env.ZERO;
//				for (int i = 0; i < lstPaymentHdr.size(); i++) {
//
//					// crea las lineas de la asignación de pagos /factura <lstAllocationLine>
//					createAllocationLine(alloc,lstPaymentHdr.get(i), invoice.getC_Invoice_ID());
//				}
//				
////				// Relacion arbitraria de anticipos/concepto
////				MInvoiceLine[] lstLinesInv = invoice.getLines();
////						
////				
////				if(lstLinesInv==null || lstLinesInv.length<=0){
////					return success;
////				}
////				
////				int sizeLst = lstLinesInv.length-1;
////				for (int i = 0; i < lstAllocationLine.size(); i++) {
////					if (sizeLst >= i && totalPayment.compareTo(Env.ZERO) > 0) {
////						lstAllocationLine.get(i).setC_Charge_ID(
////								lstLinesInv[i].getC_Charge_ID());//lstLinesInv[i].setLineTotalAmt(lstLinesInv[i].getLineNetAmt().add(lstLinesInv[i].getTaxAmt ()));
////						totalPayment = totalPayment.subtract(lstLinesInv[i].getLineNetAmt());
////								
////						lstAllocationLine.get(i).save(invoice.get_TrxName());
////					} else {
////						break;
////					}
////				}
//				
//				//
//				alloc.processIt(DocAction.ACTION_Complete);
//				if (!alloc.save(invoice.get_TrxName())) {
//					viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
//							"msj.error"));
//				} else {
//					lstAllocation.add(alloc);
//				}
//			}
//		}
//
//		if (viewErrors == null || viewErrors.isEmpty())
//			success = X_C_Invoice.DOCSTATUS_Closed;
//
//		return success;
//	}
//
//	/**
//	 * create relation Payment - allocation
//	 * 
//	 * @param alloc
//	 */
//	private BigDecimal createAllocationLine(MAllocationHdr alloc,
//			org.compiere.model.MPayment payment, int C_Invoice_ID) {
//		BigDecimal totalLine = Env.ZERO;
//		if (payment != null && payment.getC_Payment_ID() > 0) {
//			MAllocationLine aLine = null;
//
//			if (payment.isReceipt()) {
//				aLine = new MAllocationLine(alloc, payment.getPayAmt()
//						.compareTo(Env.ZERO) == 0 ? payment.getPayAmt()
//						: payment.getChargeAmt(), payment.getDiscountAmt(),
//						payment.getWriteOffAmt(), payment.getOverUnderAmt());
//				aLine.setAmount(payment.getPayAmt());
//			} else {
//				aLine = new MAllocationLine(alloc, payment.getPayAmt()
//						.compareTo(Env.ZERO) == 0 ? payment.getPayAmt()
//						.negate() : payment.getChargeAmt().negate(), payment
//						.getDiscountAmt().negate(), payment.getWriteOffAmt()
//						.negate(), payment.getOverUnderAmt().negate());
//				aLine.setAmount(payment.getPayAmt().negate());
//			}
//			aLine.setDocInfo(payment.getC_BPartner_ID(), 0,
//					payment.getC_Invoice_ID());
//			aLine.setPaymentInfo(payment.getC_Payment_ID(), 0);
//			aLine.setC_Invoice_ID(C_Invoice_ID);
//			// aLine.setC_Charge_ID(C_Charge_ID);
//			if (!aLine.save(alloc.get_TrxName())) {
//				viewErrors.add(new ModelError(ModelError.TIPOERROR_Error,
//						"msj.error"));
//			}
//
//			totalLine = aLine.getAmount();
//			lstAllocationLine.add(aLine);
//		}
//		return totalLine;
//	}
//	
//	public List<MAllocationHdr> getLstAllocation() {
//		return lstAllocation;
//	}
//
//	public void setLstAllocation(List<MAllocationHdr> lstAllocation) {
//		this.lstAllocation = lstAllocation;
//	}

}
