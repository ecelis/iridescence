package org.compiere.model;

//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Properties;
//
//import org.apache.struts.action.ActionError;
//import org.apache.struts.action.ActionErrors;
//import org.compiere.util.DB;
//import org.compiere.util.Msg;

/**
 * @deprecated
 * @author mvrodriguez
 *
 */
public class MEXMECalculoPagosFact {

//	/**
//	 * Pagos de Facturaci�n por extensiones
//	 * @param ctx
//	 * @param lstCashLine
//	 * @param C_Cash_ID
//	 * @param trxName
//	 * @param notaCred
//	 * @param nuevaFact
//	 * @param invoice
//	 * @param reAsignacion
//	 * @return
//	 */
//	public static ActionErrors pagos (Properties ctx, MEXMECashLine[] lstCashLine, int C_Cash_ID, String trxName,
//			MEXMEInvoice notaCred, 	MEXMEInvoice nuevaFact, 
//			MEXMEInvoice invoice, boolean reAsignacion,
//			boolean porExtension
//	){
//
//
//		ActionErrors errores =  new ActionErrors();
//
//		/*
//		 * Para las cancelaciones y re facturaciones se crean los negativos de los cashLine.
//		 * relacionados a la factura y generados por los anticipos
//		 */
//		//genera movimiento de caja con devolucion de dinero y/o y a su vez de cargo a la nueva preFactura
//		for(int i=0; i<lstCashLine.length; i++) {
//
//			MEXMECashLine lineaCash = lstCashLine[i];
//
//			//generamos una copia de la l�nea del pago de la factura
//			MEXMECashLine nueva = MEXMECashLine.copyFrom(lineaCash, C_Cash_ID, trxName);
//
//			//asociamos el pago en caja a la nota de cr�dito
//			nueva.setC_Invoice_ID(notaCred.getC_Invoice_ID());
//
//			//invertimos la forma de pago y su cantidad
//			nueva.setEXME_FormaPago_ID(lineaCash.getFormaPago().getInverso());
//
//			nueva.setC_Currency_ID(lineaCash.getC_Currency_ID());
//			if(lineaCash.getCashType()==null)
//				nueva.setCashType(MCashLine.CASHTYPE_Anticipo);
//			else
//				nueva.setCashType(lineaCash.getCashType());
//
//			nueva.setAmount(lineaCash.getAmount().negate());
//			if(!nueva.save(trxName)) {
//				errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.cashLine.noSave"));
//				return errores;
//			}
//
//			// generamos una copia de la l�nea del pago de la factura para asociar a la nueva factura
//			if(reAsignacion){
//				MEXMECashLine pagoPreFact = MEXMECashLine.copyFrom(lineaCash, C_Cash_ID, trxName);
//
//				//asociamos el pago en caja a la nueva pre factura
//				pagoPreFact.setC_Invoice_ID(nuevaFact.getC_Invoice_ID());
//
//				pagoPreFact.setC_Currency_ID(lineaCash.getC_Currency_ID());
//				if(lineaCash.getCashType()==null)
//					pagoPreFact.setCashType(MCashLine.CASHTYPE_Anticipo);
//				else
//					pagoPreFact.setCashType(lineaCash.getCashType());
//
//				if(!pagoPreFact.save(trxName)) {
//					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.cashLine.noSave"));
//					return errores;
//				}
//			}
//
//		}//devolucion y cargo de cash_line
//
//		/*
//		 * Cuando en cuentas por cobrar se cobra una factura ( status: completa) poniendo el numero de factura
//		 * en la opci�n de pagos y se completa el pago, se crea el allocation header y el allocation line,
//		 * y payment tiene la relaci�n a invoice, pero cuando se relaciona el pago y la factura por medio
//		 * de la opci�n de Compiere asignaci�n de pagos, se crea el allocation header y el allocation line,
//		 * pero en payment no se guarda la relaci�n con la factura.
//		 */
//		@SuppressWarnings("unused")
//		HashMap<Integer, Integer> mapaPagos = new HashMap<Integer, Integer>();
//		try {
//
//			List<MEXMEPayment> pagosCxC = new ArrayList<MEXMEPayment>() ;
//
//			//Buscamos los pagos realizados ASOCIADOS con la factura
//			pagosCxC = MEXMEPagosFactura.pagosCxCAllocation(ctx, invoice.getC_Invoice_ID(), trxName);
//
//			for (int i = 0; i < pagosCxC.size(); i++) {
//				//Asociamos a al nueva factura el pago
//				if (reAsignacion && nuevaFact!=null){
//					pagosCxC.get(i).setC_Invoice_ID(nuevaFact.getC_Invoice_ID());
//				}else{
//					pagosCxC.get(i).setC_Invoice_ID(0);
//					//indicar que no esta asociado el pago a ninguna factura
//					pagosCxC.get(i).setIsAllocated(false);
//				}
//
//				if(!pagosCxC.get(i).save(trxName)){
//					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.payment.noSave"));
//					return errores;
//				}
//				
//			}
//
//			//Buscamos los pagos RELACIONADOS con la factura y se borran ya que tienen status de DR
//			pagosCxC = MEXMEPagosFactura.pagosCxCPayment(ctx, invoice.getC_Invoice_ID(), trxName);
//			for (int i = 0; i < pagosCxC.size(); i++) {
//				
//				//Asociamos a al nueva factura el pago
//				if (reAsignacion && nuevaFact!=null){
//					pagosCxC.get(i).setC_Invoice_ID(nuevaFact.getC_Invoice_ID());
//				}else{
//					pagosCxC.get(i).setC_Invoice_ID(0);
//					//indicar que no esta asociado el pago a ninguna factura
//					pagosCxC.get(i).setIsAllocated(false);
//				}
//				
//				if(!pagosCxC.get(i).save(trxName)){
//					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.payment.noSave"));
//					return errores;
//				}
//			}		
//
//		} catch (Exception e) {
//			errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.payment.noSave"));
//			return errores;
//		}
//
//		//Cuando existe la cuenta paciente
//		if(porExtension){
//			/*
//			 * Devoluci�n de Pagos ( Payment ) por motivo de anticipos siempre relacionados a la cuenta paciente
//			 * Re facturaci�n: Solo se quita la asociaci�n y asocia a la nota de cr�dito
//			 * Cancelaci�n: Se dejan activos los anticipos para relacionarse posteriormente
//			 */
//			MPayment[] pagoFact = MCtaPacPag.getPayments(ctx, invoice.getEXME_CtaPacExt_ID(), trxName);
//			
//			//BigDecimal montoAnticipos = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
//			MEXMECtaPacExt extension = new MEXMECtaPacExt(ctx,
//					invoice.getEXME_CtaPacExt_ID(),
//					trxName);//Lama .- Asignamos el nombre de transacciï¿½n
//			//barremos los pagos
//			for(int j=0; j<pagoFact.length; j++) {
//
//				if(reAsignacion){
//					//eliminar la relaciï¿½n del pago con la factura
//					pagoFact[j].setC_Invoice_ID(nuevaFact.getC_Invoice_ID());
//
//				}else{
//
//
//
//					
//					//asociar el socio de la extension
//					pagoFact[j].setC_BPartner_ID(extension.getC_BPartner_ID());
//					//eliminar la relaciï¿½n del pago con la factura
//					pagoFact[j].setC_Invoice_ID(0);
//					//indicar que no esta asociado el pago a ninguna factura
//					pagoFact[j].setIsAllocated(false);
//					
//					//Obtenemos los pagos y los in activamos
//					MCtaPacPag[] pagos = MCtaPacPag.getAnticipos(ctx,null,invoice.getEXME_CtaPacExt_ID(),trxName);
//					for(int i = 0; i < pagos.length; i++){
//						MCtaPacPag p = pagos[i];
//						p.setIsActive(true);
//						p.setIsPay(false);
//						if(!p.save(trxName)){
//							errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.payment.noSave"));
//							return errores;
//						}
//					}
//					
//				}
//				//montoAnticipos = montoAnticipos.add(pagoFact[j].getPayAmt().abs());
//
//
//				if(!pagoFact[j].save(trxName)) {
//					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.payment.noSave"));
//					return errores;
//				}
//
//
//			}//payment
////			MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipo(ctx, extension.getEXME_CtaPac_ID(), extension.getEXME_CtaPacExt_ID(), trxName);
////			if (anticipo != null){
////				anticipo.setTotal(montoAnticipos);
////				if(!anticipo.save(trxName)) {
////					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.payment.noSave"));
////					return errores;
////				}
////			}
////
////			anticipo = null;
//			extension.setAnticipo(BigDecimal.ZERO);
//			if(!extension.save(trxName)) {
//				errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.payment.noSave"));
//				return errores;
//			}
//			extension = null;
//
//			
//			//genera nota de crï¿½dito con los datos de la factura
//			Timestamp now = DB.getTimestampForOrg(ctx);
//
//			//Allocation Header
//			int C_AllocationHdr_ID = 0;
//
//
//
//
//
//			// Devoluci�n y Cargo de Anticipos y pagos CxC (AllocationLine)
//			MEXMEAllocationLine[] allocationLine = MEXMEAllocationLine.getOfInvoice(ctx, invoice.getC_Invoice_ID(), trxName); 
//
//			// Si el pago viene de un anticipo a la cuenta paciente
//			for(int i = 0; i < allocationLine.length; i++){
//
//				MAllocationLine aLine = allocationLine[i];
//
//				// Devoluci�n de la entrada de dinero (CashLine)
//				// por concepto de anticipos a la Cuenta Paciente o pagos CxC
//				// La l�nea de allocation por anticipo no tiene cash line ni los pagos cuentas por cobrar
//
//				//Nuevo l�nea de cobro de caja
//				MEXMECashLine nuevaCashLineDevol = null;
//
//				//Nuevo l�nea de cobro de caja
//				MEXMECashLine nuevaCashLinePago = null;
//
//				if(aLine.getC_CashLine_ID()>0 && aLine.getC_Payment_ID()>0 && reAsignacion){
//					MEXMECashLine cashLine = MEXMECashLine.getOfPayment(ctx, aLine.getC_Payment_ID(), trxName);
//
//					/*
//					 * DEVOLUCION DE PAGO ASOCIADO A LA NOTA DE CREDITO
//					 */
//					//generamos una copia de la l�nea del pago de la factura
//					nuevaCashLineDevol = MEXMECashLine.copyFrom(cashLine, C_Cash_ID, trxName);
//
//					//asociamos el pago en caja a la nota de cr�dito
//					nuevaCashLineDevol.setC_Invoice_ID(notaCred.getC_Invoice_ID());
//
//					//invertimos la forma de pago y su cantidad
//					nuevaCashLineDevol.setEXME_FormaPago_ID(cashLine.getFormaPago().getInverso());
//
//					nuevaCashLineDevol.setC_Currency_ID(cashLine.getC_Currency_ID());
//					if(cashLine.getCashType()==null)
//						nuevaCashLineDevol.setCashType(MCashLine.CASHTYPE_Anticipo);
//					else
//						nuevaCashLineDevol.setCashType(cashLine.getCashType());
//
//					nuevaCashLineDevol.setAmount(cashLine.getAmount().negate());
//					if(!nuevaCashLineDevol.save(trxName)) {
//						errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.cashLine.noSave"));
//						return errores;
//					}
//
//					/*
//					 * NUEVO PAGO ASOCIADO A NUEVA FACTURA
//					 */
//					// Generamos una copia de la l�nea del pago de la factura para asociar a la nueva factura
//					nuevaCashLinePago = MEXMECashLine.copyFrom(cashLine, C_Cash_ID, trxName);
//
//					//asociamos el pago en caja a la nueva pre factura
//					nuevaCashLinePago.setC_Invoice_ID(nuevaFact.getC_Invoice_ID());
//
//					nuevaCashLinePago.setC_Currency_ID(cashLine.getC_Currency_ID());
//					if(cashLine.getCashType()==null)
//						nuevaCashLinePago.setCashType(MCashLine.CASHTYPE_Anticipo);
//					else
//						nuevaCashLinePago.setCashType(cashLine.getCashType());
//
//					if(!nuevaCashLinePago.save(trxName)) {
//						errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.cashLine.noSave"));
//						return errores;
//					}
//				}else{
//					//Para el caso de la cancelaci�n se debe desasociar el C_cashLine de la factura
//				}
//
//
//
//
//
//
//
//				//insertamos el registro en AllocationHdr (para la nota de credito) - NEGATIVO
//				if(C_AllocationHdr_ID==0 || C_AllocationHdr_ID!=aLine.getC_AllocationHdr_ID()) {
//					MAllocationHdr alloc = new MAllocationHdr(ctx, false, now, aLine.getC_Currency_ID()
//							, Msg.translate(ctx, "DevRefacturacion") + " [" + invoice.getDocumentNo() + "]", 
//							trxName);
//
//					if(!alloc.save(trxName)) {
//						errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.allocHdr.noSave"));
//						return errores;
//					}
//
//					C_AllocationHdr_ID = alloc.getC_AllocationHdr_ID();
//				}
//
//				MEXMEAllocationLine neg = MEXMEAllocationLine.copyFrom(
//						allocationLine[i], C_AllocationHdr_ID, now, trxName);
//
//				//asignar las cantidades inversas
//				neg.setAmount(neg.getAmount().negate());
//
//				//asociar la distribuci�n a la nota de cr�dito
//				//El payment queda activo, positivo y no relacionado 
//				//El Cash line queda negativo y relacionado a la nota de cr�dito
//				//El allocation queda negativo y relacionado ala nota de cr�dito
//				neg.setC_Invoice_ID(notaCred.getC_Invoice_ID());
//
//				//y a la nueva l�nea del pago
//				if(nuevaCashLineDevol!=null && nuevaCashLineDevol.getC_CashLine_ID()>0 && reAsignacion)
//					neg.setC_CashLine_ID(nuevaCashLineDevol.getC_CashLine_ID());
//
//				if(!neg.save(trxName)) {
//					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.allocLine.noSave"));
//					return errores;
//				}
//
//				//Re facturaci�n
//				if(reAsignacion){
//					
//					// insertamos el registro en AllocationHdr para la nueva factura -  POSITIVO
//					MAllocationHdr alloc = new MAllocationHdr(ctx, false, now, aLine.getC_Currency_ID()
//							,  Msg.translate(ctx, "PagoRefacturacion") + "[" + invoice.getDocumentNo() + "]", 
//							trxName);
//
//					if(!alloc.save(trxName)) {
//						errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.allocHdr.noSave"));
//						return errores;
//					}
//
//					//Nuevo allocation Header
//					C_AllocationHdr_ID = alloc.getC_AllocationHdr_ID();
//					MEXMEAllocationLine pos = MEXMEAllocationLine.copyFrom(
//							allocationLine[i], C_AllocationHdr_ID, now, trxName);
//
//					//asociar la distribuci�n a la nueva factura
//					pos.setC_Invoice_ID(nuevaFact.getC_Invoice_ID());
//					//y al pago relacionado a la pre factura
//					if(nuevaCashLinePago!=null && nuevaCashLinePago.getC_CashLine_ID()>0  && reAsignacion)
//						pos.setC_CashLine_ID(nuevaCashLinePago.getC_CashLine_ID());
//
//					if(!pos.save(trxName)) {
//						errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.allocLine.noSave"));
//						return errores;
//					}
//				}
//
//			}// allocation_Line
//
//		}
//		return errores;
//	}

}
