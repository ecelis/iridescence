package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MCash;
import org.compiere.model.MCashLine;
import org.compiere.model.MCtaPacPag;
import org.compiere.model.MEXMEAnticipo;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMECtaPacPag;
import org.compiere.model.MInvoice;
import org.compiere.model.MPayment;
import org.compiere.model.bean.CtaPacPag;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.ErrorList;

public class BillPayments {

	/** Logger */
	protected transient static CLogger slog = CLogger.getCLogger(BillPayments.class);
	/** Cobros */
	private boolean isPaid = true;
	
	/**
	 * Actualizacion de pagos al cancelar o al refacturar
	 * @param ctx
	 * @param lstCashLine : Pagos hechos en caja
	 * @param cCashID : Caja de login
	 * @param trxName : Nombre de transacción
	 * @param invoiceOld : Siempre existe
	 * @return Mensaje de error
	 */
	public static String actualizarPagos(final Properties ctx, final List<MCashLine> lstCashLine,
			final int cCashID, final MInvoice invoiceOld, final String trxName) {
		final BillPayments mTraspasoPagos = new BillPayments();
		String errores = null;
		// 
		if (cCashID <= 0 || invoiceOld==null) {
			return Utilerias.getLabel("error.caja.cashLine.noSave");
		}
		mTraspasoPagos.setPaid(invoiceOld.isPaid());
		// Cancelacion de asignaciones de cobros
		errores = mTraspasoPagos.cancelCashLine(ctx, lstCashLine, cCashID, invoiceOld, trxName);
		// Cancelación de anticipos 
		if (errores == null) {
			errores = mTraspasoPagos.cancelarAnticipos(ctx, lstCashLine, cCashID,  invoiceOld, trxName);
		}
		
		if(errores == null && !mTraspasoPagos.updateInvoice(invoiceOld, trxName)){
			errores = Utilerias.getLabel("error.caja.cashLine.noSave");
		}
		return errores==null?"":errores;//error por la actualizaion
	}
	
	/** Actualizar factura */
	private boolean updateInvoice(final MInvoice invoiceOld, final String trxName){
		// la factura debe quedar con estatus de pagada (si es que asi estaba antes) aunque ya no tenga pagos
		invoiceOld.setIsPaid(isPaid);
		return invoiceOld.save(trxName);
	}
	
	/** Cancelar las lineas de caja */
	private String cancelCashLine(final Properties ctx, final List<MCashLine> lstCashLine,
			final int cCashID, final MInvoice invoiceOld, final String trxName){
		/* Las Facturas R13-6 y las facturas R13-7 
		 * tienen cashlline relacionados por pagos en caja (efectivo o cxc)
		 * 
		 * se crean las devoluciones de esas formas de pago en la caja de login
		 * si es refacturacion, se crean nuevos pagos pero apuntando a la nueva factura
		 * si es remision, se crean las devoluciones apuntando a la remision
		 */
		String errores = null;
		if (lstCashLine != null && !lstCashLine.isEmpty()) {
			final MCash caja = new MCash(ctx, cCashID, trxName);
			errores = caja.cancelCashLines(ctx, lstCashLine, invoiceOld, trxName);
		}
		return errores;
	}
	
	/** Cancelacion de anticipos 
	 * 
	 * @param ctx
	 * @param lstCashLine
	 * @param C_Cash_ID
	 * @param invoiceOld
	 * @param trxName
	 * @return
	 */
	public String cancelarAnticipos(final Properties ctx, final List<MCashLine> lstCashLine,
			final int C_Cash_ID, final MInvoice invoiceOld, final String trxName){	
		/*
		 * Los anticipos se regresan a la extension cero cuando la factura o la nota de remision se cancelan
		 * en el entendido que cuando cancelas una factura, tambien se cancela la nota de remision 
		 */
		String error = null;
		if (invoiceOld.getEXME_CtaPacExt_ID()>0) {
			error = revertAllocation(ctx, invoiceOld, trxName);

			if(error==null){

				// Cuando existe la cuenta paciente
				// La extension que se cancelo queda sin anticipos
				final MEXMECtaPacExt extensionCero = new MEXMECtaPacExt(ctx,invoiceOld.getCtaPac().getEXME_CtaPacExt_ID(), trxName);
//				if(!removeAllPrepayment(ctx, extensionCero, trxName)){
//					error = Utilerias.getLabel("error.caja.payment.noSave");			
//				}
				if(!removePrepaymentForExt(ctx, invoiceOld, trxName)){
					error = Utilerias.getLabel("error.caja.payment.noSave");			
				}
			}
		}
		return error;
	}
	
	/** revertir asignacion de anticipos */
	private String revertAllocation(final Properties ctx, final MInvoice invoiceOld, final String trxName){
		// Asignaciones de la factura
		final List<MAllocationHdr> allocationHdr = MAllocationHdr.getAllocationHrdInv(ctx
				, invoiceOld.getC_Invoice_ID()
				, true
				, trxName);
		if(allocationHdr!=null && !allocationHdr.isEmpty()){

			// Revertir asignacion
			for (final MAllocationHdr mAllocationHdr: allocationHdr) {

				final MAllocationHdr allocHdrNota = mAllocationHdr.reversePrePayment();
				if(allocHdrNota==null) {
					slog.log(Level.SEVERE, "No se genero la asignación de reversa del pago");
					return Utilerias.getLabel("error.caja.allocHdr.noSave");
				}
				
				final ErrorList err = allocHdrNota.completeInvoice(false);
//				allocHdrNota.setDocStatus(allocHdrNota.completeIt());// Completar
//				if(!allocHdrNota.save(trxName)){
				if(!err.isEmpty()){
					return Utilerias.getLabel("error.caja.payment.noSave")+" "+err.toString();
				}
			}
		}
		return null;
	}
	
	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(final boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	/** Remover los anticipos de otras extensiones
	 * @param ctx Contexto
	 * @param mExtCero Extension cero
	 * @param trxName Nombre de transacción
	 * @return
	 */
	public boolean removeAllPrepayment(final Properties ctx, final MEXMECtaPacExt mExtCero, final String trxName){
		boolean okey = true;
		// Listado de pagados
		final List<Integer>  lstPagos = new ArrayList<Integer>();
		// Listado de anticipos
		//FIXME, para un caso particular de 1 anticipo mayor al total de la factura
		// hace 7 idas a la base de datos, de las cuales solo ocupo 4
		// como anticipo se repite para 2 CtaPacPag,
		// en la segunda iteracion arroja una excepción al log cuando lo instancia porque ya no existe
		// es preferible 3 metodos que regresen el objeto PO completo
		final List<BeanView> lstAnticipos = MEXMEAnticipo.getAllPrepayment(ctx, mExtCero.getEXME_CtaPac_ID(), trxName);
		// Itera la relacion pago - extension para eliminarla
		for (final BeanView idCtaPacPag: lstAnticipos) {
			//
			final MCtaPacPag mCtaPacPag = new MCtaPacPag(ctx, idCtaPacPag.getInteger2(), trxName);
			if(!lstPagos.contains(mCtaPacPag.getC_Payment_ID())){
				lstPagos.add(mCtaPacPag.getC_Payment_ID());
			}
			if(!mCtaPacPag.delete(true)){
				mCtaPacPag.setIsActive(false);
				if(!mCtaPacPag.save()){
					okey = false;
					break;
				}
			}
			
			//
			final MEXMEAnticipo mAnticipo = new MEXMEAnticipo(ctx, idCtaPacPag.getInteger1(), trxName);
			if(!mAnticipo.delete(true)){
				mAnticipo.setIsActive(false);
				if(!mAnticipo.save()){
					okey = false;
					break;
				}
			}
			
			//
			final MEXMECtaPacExt mCtaPacExt = new MEXMECtaPacExt(ctx, idCtaPacPag.getInteger3(), trxName);
			mCtaPacExt.setAnticipo(Env.ZERO);
			if(!mCtaPacExt.save()){
				okey = false;
				break;
			}
		}
		
		if(okey){
			
			BigDecimal total = Env.ZERO;
			for (final Integer idPayment: lstPagos) {
				final MPayment mPaymentNew = new MPayment(ctx, idPayment, trxName);
				
				final MCtaPacPag mCtaPacPagNew = new MCtaPacPag(ctx, 0, trxName);
				mCtaPacPagNew.setEXME_CtaPacExt_ID(mExtCero.getEXME_CtaPacExt_ID());
				mCtaPacPagNew.setC_Payment_ID(mPaymentNew.getC_Payment_ID());
				//mCtaPacPagNew.setDisponible(mPaymentNew.getPaymentAvailable()); 
				mCtaPacPagNew.setAplicado(mPaymentNew.getPaymentAvailable());
				mCtaPacPagNew.setIsPay (false);
				if(mCtaPacPagNew.save()){
					//total = total.add(mCtaPacPagNew.getDisponible());
					total = total.add(mCtaPacPagNew.getAplicado());
					
					// Los pagos directos se relacionan a la factura, CXC
					mPaymentNew.setC_Invoice_ID(0);
					boolean success = mPaymentNew.testAllocation();
					
					
					if(!success){
						slog.log(Level.INFO, "TraspasoPagos.removeAllPrepayment - C_Payment_ID=" + mPaymentNew.getC_Payment_ID());

						BigDecimal alloc =  mPaymentNew.getAllocatedAmt();
						if (alloc == null){
							alloc = Env.ZERO;
						}
						
						BigDecimal totalPayment =  mPaymentNew.getPayAmt();
						if (!mPaymentNew.isReceipt()){
							totalPayment = totalPayment.negate();
						}
						
						boolean test = totalPayment.compareTo(alloc) == 0;
						boolean change = test != mPaymentNew.isAllocated();
						
						if (change){
							mPaymentNew.setIsAllocated(test);
						}
						slog.fine("Allocated=" + test + " (" + alloc + "=" + total + ")");
					}

					if(!mPaymentNew.save(trxName)){
						slog.log(Level.SEVERE, "TraspasoPagos.removeAllPrepayment - !C_Payment_ID=" + mPaymentNew.getC_Payment_ID());
						return success;
					}
					
				} else {
					okey = false;
					break;
				}
			}
			
			if(okey && total.compareTo(Env.ZERO)>0){
				final MEXMEAnticipo mAnticipoNew = new MEXMEAnticipo(ctx, 0, trxName);
				mAnticipoNew.setEXME_CtaPac_ID(mExtCero.getEXME_CtaPac_ID());
				mAnticipoNew.setEXME_CtaPacExt_ID(mExtCero.getEXME_CtaPacExt_ID());
				mAnticipoNew.setTotal(total);
				mAnticipoNew.setAplicado(Env.ZERO);
				mAnticipoNew.setSaldo(total);
				if(!mAnticipoNew.save()){
					okey = false;
				}
			}
		}
		return okey;
	}
	
	/**
	 * Remover los anticipos de una extension facturada y regresarlos a la cero
	 */
	public boolean removePrepaymentForExt(final Properties ctx, final MInvoice invoiceOld, final String trxName){
		boolean okey = true;
		
		MEXMECtaPacExt extTo = new MEXMECtaPacExt(ctx, invoiceOld.getCtaPac().getEXME_CtaPacExt_ID(), trxName);
		MEXMECtaPacExt extFrom = new MEXMECtaPacExt(ctx, invoiceOld.getEXME_CtaPacExt_ID(), trxName);
		
		MEXMEAnticipo antTo = extTo.getMAnticipo();
		MEXMEAnticipo antFrom = extFrom.getMAnticipo();
		
		List<MEXMECtaPacPag> mCtaPacPagFromList = MEXMECtaPacPag.get(ctx, trxName, extFrom.getEXME_CtaPacExt_ID());
		
		BigDecimal total = Env.ZERO;
		
		
		for (MEXMECtaPacPag mCtaPacPagFrom : mCtaPacPagFromList) {
			MEXMECtaPacPag mCtaPacPagTo = MEXMECtaPacPag.getMCtaPacPag(ctx, mCtaPacPagFrom.getC_Payment_ID(), extTo.getEXME_CtaPacExt_ID(), trxName);
			if(mCtaPacPagTo == null){
				mCtaPacPagTo = new MEXMECtaPacPag(ctx, 0, trxName);
				mCtaPacPagTo.setC_Payment_ID(mCtaPacPagFrom.getC_Payment_ID());
				mCtaPacPagTo.setEXME_CtaPacExt_ID(extTo.getEXME_CtaPacExt_ID());
			}
			
			mCtaPacPagTo.setAplicado(mCtaPacPagTo.getAplicado().add(mCtaPacPagFrom.getAplicado()));
			total = total.add(mCtaPacPagFrom.getAplicado());
			
			if(!mCtaPacPagTo.save()){
				okey = false;
				break;
			}
			if(!mCtaPacPagFrom.delete(true)){
				mCtaPacPagFrom.setIsActive(false);
				if(!mCtaPacPagFrom.save()){
					okey = false;
					break;
				}
			}
		}
		
		if(!okey){
			return false;
		}
		
		if(antFrom != null){
			antFrom.setTotal(Env.ZERO);
			if(!antFrom.delete(true)){
				okey = false;
			}
		}
		
		if(antTo == null){
			antTo = new MEXMEAnticipo(ctx, 0, trxName);
			antTo.setEXME_CtaPacExt_ID(extTo.getEXME_CtaPacExt_ID());
			antTo.setEXME_CtaPac_ID(invoiceOld.getCtaPac().getEXME_CtaPac_ID());
		}
		antTo.setTotal(antTo.getAplicado().add(total));
		if(!antTo.save()){
			okey = false;
		}
		
		extFrom.setAnticipo(Env.ZERO);
		if(!extFrom.save()){
			okey = false;
		}
		
		BigDecimal anticipo = extTo.getGrandTotal().compareTo(antTo.getTotal()) > 0 ? antTo.getTotal() : extTo.getGrandTotal();
		extTo.setAnticipo(anticipo);
		if(!extTo.save()){
			okey = false;
		}
		
		return okey;
	}
}