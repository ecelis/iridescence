package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.CtaPacPag;
import org.compiere.model.bpm.BeanView;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Modelo de anticipos para la cuenta paciente
 * @author 
 *
 */
public class MEXMEAnticipo extends X_EXME_Anticipo{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -4858334700171495094L;
	/** log */
	private static CLogger		slog = CLogger.getCLogger (MEXMEAnticipo.class);
	/** seleccionado */
	private boolean seleccionado = false;
//	/** extension */
//	private int extension = 0;
	/** indica la extension */
	private MEXMECtaPacExt mExtension = null;
	/** Mapa entre pagos y extension */
	private Map<Integer, List<MEXMECtaPacPag>> mapPayMethod;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param exmeAnticipoID : id
	 * @param trxName
	 */
	public MEXMEAnticipo(final Properties ctx, final int exmeAnticipoID, final String trxName) {
		super(ctx, exmeAnticipoID, trxName);
	}

	/**
	 * Constructor
	 * @param ctx
	 * @param exmeCtaPacID
	 * @param exmeCtaPacExtID
	 * @param trxName
	 */
	public MEXMEAnticipo(final Properties ctx, final int exmeCtaPacID, final int exmeCtaPacExtID, final String trxName) {
		super(ctx, 0, trxName);
		this.setEXME_CtaPac_ID(exmeCtaPacID);
		this.setEXME_CtaPacExt_ID(exmeCtaPacExtID);
	}
	
	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEAnticipo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Anticipos de la cuenta paciente, por extension 
	 * @param ctx : contexto
	 * @param exmeCtaPacID : Cuenta paciente (opcional)
	 * @param EXME_CtaPacExt_ID : Extension
	 * @param trxName : Nombre de transacción
	 * @return MEXMEAnticipo 
	 */
	public static MEXMEAnticipo getAnticipo(final Properties ctx, final int exmeCtaPacID, final int EXME_CtaPacExt_ID, final String trxName){
		MEXMEAnticipo anticipo = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("SELECT * FROM EXME_ANTICIPO WHERE ");
		if(exmeCtaPacID != 0) {
			sql.append("EXME_CTAPAC_ID = ? AND ");
		}
		sql.append("EXME_CTAPACEXT_ID = ? ")
		   .append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "EXME_Anticipo"));
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);
			int index = 1;
			if (exmeCtaPacID != 0) {
				pstmt.setInt(index++, exmeCtaPacID);
			}
			pstmt.setInt(index, EXME_CtaPacExt_ID);

        	rs = pstmt.executeQuery();
        	if(rs.next()){
        		anticipo = new MEXMEAnticipo(ctx,rs,trxName);
        	}
    	} catch (Exception e){
    		slog.log(Level.SEVERE, "getAnticipo ", e);
    	} finally{
    		DB.close(rs,pstmt);
    	}
    	return anticipo;
	}
	
	/**
	 * Anticipos de la cuenta paciente
	 * @param ctx
	 * @param exmeCtaPacID
	 * @param where
	 * @param trxName
	 * @return
	 */
	public static List <MEXMEAnticipo> getAnticipos(final Properties ctx, final int exmeCtaPacID, final String where, final String trxName){
		final  List <MEXMEAnticipo>  anticipo = new ArrayList <MEXMEAnticipo>();
		final  StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		
		sql.append("select * from EXME_ANTICIPO ")
		   .append("inner join EXME_CTAPACEXT ext on EXME_ANTICIPO.EXME_CTAPACEXT_ID = ext.EXME_CTAPACEXT_ID ")
		   .append("where EXME_ANTICIPO.EXME_CTAPAC_ID = ? ");
		if(where != null) {
			sql.append(where);
		}
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		   .append(" order by ext.EXTENSIONNO");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);
        	pstmt.setInt(1, exmeCtaPacID);
        	rs = pstmt.executeQuery();
        	while(rs.next()){
        		anticipo.add(new MEXMEAnticipo(ctx,rs,trxName));
        	}
    	} catch (Exception e){
    		slog.log(Level.SEVERE, "getAnticipo ", e);
    	} finally{
    		DB.close(rs,pstmt);
    	}
    	
    	return anticipo;
	}		
	
	
	/**
	 * Anticipos de cuentas de un mismo paciente
	 * @param ctx
	 * @param exmeCtaPacsIDs cuentas del mismo paciente
	 * @param trxName
	 * @return List <MEXMEAnticipo>
	 */
	public static List <MEXMEAnticipo> getAnticiposPorPaciente(final Properties ctx, 
			final String exmeCtaPacsIDs, final String trxName){
		final  List <MEXMEAnticipo>  anticipo = new ArrayList <MEXMEAnticipo>();
		final  StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT * FROM EXME_ANTICIPO              ")
		.append(" WHERE EXME_ANTICIPO.IsActive = 'Y'       ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		.append(" AND   EXME_ANTICIPO.EXME_CTAPAC_ID IN ( ").append(exmeCtaPacsIDs)
		.append(") ORDER BY EXME_ANTICIPO.EXME_ANTICIPO_ID  ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);
        	rs = pstmt.executeQuery();
        	while(rs.next()){
        		anticipo.add(new MEXMEAnticipo(ctx,rs,trxName));
        	}
    	} catch (Exception e){
    		slog.log(Level.SEVERE, "getAnticipo ", e);
    	} finally{
    		DB.close(rs,pstmt);
    	}
    	
    	return anticipo;
	}	
	
//	/**
//	 * @return
//	 */
//	public int getExtension() {
//		final MEXMECtaPacExt ext = new MEXMECtaPacExt(getCtx(),getEXME_CtaPacExt_ID(), null);
//		this.extension = ext.getExtensionNo();
//		return extension;
//	}
//
//	public void setExtension(int extension) {
//		this.extension = extension;
//	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	
	/************************************************************/
	
	/**
	 * Considerando que el anticipo ya esta relacionado a la extension
	 * y la factura ya esta generada. Este método relacionara 
	 * la factura con su anticipo
	 * @param mInvoice FACTURA RECIEN GENERADA
	 * @param trxName Nombre de transaccion
	 */
	public BigDecimal asignarAnticipo(final MInvoice mInvoice, 
			final String trxName){
		if(mInvoice==null || getEXME_Anticipo_ID()<=0 || trxName==null){
			log.log(Level.WARNING, "Not parameters");
			return Env.ZERO;
		}
		
		BigDecimal sumMontoPagosAplicados = Env.ZERO;
		try {

			// Pagos relacionados a la extension
			final List<MEXMECtaPacPag> pagosAnticipo = MCtaPacPag.getPayments(Env.getCtx(),mInvoice.getEXME_CtaPacExt_ID(), trxName);
			if(pagosAnticipo==null || pagosAnticipo.isEmpty()){
				log.log(Level.INFO, "Not payment");
				return Env.ZERO;
			}

			// Create Allocation
			final MAllocationHdr alloc = new MAllocationHdr (
					Env.getCtx(), 
					true,	//	manual
					Env.getCurrentDate(),  
					Env.getC_Currency_ID(getCtx()), 
					Env.getAD_User_Name(getCtx())+" [l]", trxName);
			alloc.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
			if (!alloc.save(trxName)){
				log.log(Level.SEVERE, "Allocation not created");
				return Env.ZERO;
			}

			// Obtenemos los anticipos
			MEXMEAnticipo mAnticipoExtCero = mInvoice.getCtaPac().getAnticipoExtCero();
			
			// 
			List<MPayment> lstPagosAsignados = new ArrayList<MPayment>();
			boolean facturaCubierta = false;
			
			BigDecimal totalInv = mInvoice.getGrandTotal();
			BigDecimal sumMontoPagos = Env.ZERO;
			
			// Allocation Line
			MAllocationLine aLine = null; 
					
			// Itera los pagos
			for (final MEXMECtaPacPag pag: pagosAnticipo) {
				// Sumatoria de todos los pagos relacionados
				sumMontoPagos = sumMontoPagos.add(pag.getAplicado());
				BigDecimal available = Env.ZERO;
				// Mientras no se haya cubrido el monto de la factura
				if(!facturaCubierta){
					// Saldo pendiente de la factura
					totalInv = totalInv.subtract(pag.getAplicado());
					// Allocation Line
					aLine = new MAllocationLine (
							alloc, 
							pag.getAplicado(), 
							Env.ZERO,
							Env.ZERO,
							Env.ZERO);
					aLine.set_TrxName(trxName);
					aLine.setDocInfo(mInvoice.getC_BPartner_ID(), mInvoice.getC_Order_ID(), mInvoice.getC_Invoice_ID());// Nota de remision
					aLine.setPaymentInfo(pag.getC_Payment_ID(), 0);
					aLine.setOverUnderAmt(Env.ZERO);

					// Es mas grande la factura que el pago
					if (totalInv.compareTo(Env.ZERO) > 0) {
						aLine.setAmount(pag.getAplicado());
						if (!aLine.save(trxName)) {
							log.log(Level.SEVERE, "Allocation Line not written - Invoice=" + mInvoice.getC_Invoice_ID());
							return Env.ZERO;
						} else { // no queda disponible nada de ese pago
							sumMontoPagosAplicados = sumMontoPagosAplicados.add(pag.getAplicado());
						} // continuar con el ciclo
					}
					// Es mas pequeña la factura que el pago
					else {
						BigDecimal porPagar = mInvoice.getGrandTotal().subtract(sumMontoPagosAplicados);
						aLine.setAmount(porPagar);
						if (!aLine.save(trxName)) {
							log.log(Level.SEVERE, "Allocation Line not written - Invoice=" + mInvoice.getC_Invoice_ID());
							return Env.ZERO;
						} else {
							sumMontoPagosAplicados = sumMontoPagosAplicados.add(porPagar);
							// pag.setAvailable(pag.getAplicado().subtract(porPagar));
							available = pag.getAplicado().subtract(porPagar);
							facturaCubierta = true;
						}
					}
				} else {
					available = pag.getAplicado();
				}
				
				MPayment payment = pag.getPayment();
				payment.setAvailable(available);
				lstPagosAsignados.add(payment);
				// guardar el pago
				payment.set_TrxName(trxName); 
				payment.save();// ?
				// Se evalua el monto asociado al pago
				if (payment.testAllocation() && !payment.save()){
					log.log(Level.SEVERE, "MEXMEAnticipo.reactivarAnticipo - testAllocation=" + pag.getC_Payment_ID());
					return Env.ZERO;
				}
				log.config("Payment #" + payment.getDocumentNo() + (payment.isAllocated() ? " not" : " is")  + " fully allocated");

				// si la factura ya se cubrio se asugna el resto del pago o de los pagos a la extension cero
				if(facturaCubierta){
					// extension cero
					asignacionPagoExtCero(pag, mInvoice, payment, mAnticipoExtCero, trxName);
				} else {
					// extension de la factura
//					final MCtaPacPag pagos = MCtaPacPag.getMCtaPacPag(getCtx(), pag.getC_Payment_ID(), 
//							mInvoice.getEXME_CtaPacExt_ID(), trxName);
//					if (pag != null) {
					//pag.setDisponible(Env.ZERO);
					pag.setIsPay(true);
					if (!pag.save(trxName)) {
						log.log(Level.SEVERE,
								"MEXMEAnticipo.reactivarAnticipo - MCtaPacPag=" + pag.getEXME_CtaPacPag_ID());
						return Env.ZERO;
					}
//					}
				}
			}// for de pagos

			// El ultimo
			if (aLine != null && totalInv.compareTo(Env.ZERO) > 0) {
				aLine.setOverUnderAmt(totalInv);
				if (!aLine.save(trxName)) {
					log.log(Level.SEVERE, "Allocation Line not written - Invoice=" + mInvoice.getC_Invoice_ID());
					return Env.ZERO;
				}
			}
			
			//	Should start WF
			if (alloc.get_ID() != 0)
			{
				final ErrorList err = alloc.completeInvoice(false);
//				alloc.processIt(DocAction.ACTION_Complete);// Completar
//				if(!alloc.save(trxName)){
				if(!err.isEmpty()){
//					log.log(Level.SEVERE, "Allocation - Invoice=" + mInvoice.getC_Invoice_ID());
					return Env.ZERO;
				}
			}

			// identificar el monto del anticipo
			setTotal(sumMontoPagos);
			setAplicado(sumMontoPagosAplicados);
			setSaldo(sumMontoPagos.subtract(sumMontoPagosAplicados));
			
			if (facturaCubierta) {
				mInvoice.setIsPaid(true);// solo cuando esta pagada sin cxc
				// solo cuando se cobra en caja o es pagada totalmente con anticipos
				mInvoice.setAfecta_Caja(true);
				if (!mInvoice.save(trxName)) {
					log.log(Level.SEVERE, "Allocation - Invoice=" + mInvoice.getC_Invoice_ID());
					return Env.ZERO;
				}
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMEAnticipo.allocation - Invoice=" + mInvoice.getC_Invoice_ID());
			return Env.ZERO;
		}

		this.save(trxName);
		return sumMontoPagosAplicados;
	}
	
	/**
	 * Asignar pagos a la extension cero
	 * @param ctx
	 * @param mInvoice
	 * @param mPayment
	 * @param mAnticipoExtCero
	 * @param trxName
	 * @return
	 */
	private boolean asignacionPagoExtCero(final MEXMECtaPacPag mCtaPacPag, final MInvoice mInvoice, 
			final MPayment mPayment, final MEXMEAnticipo mAnticipoExtCero, final String trxName){
		final boolean success = false;
		
		// Extension cero el exceso del anticipo
		if (mInvoice.getCtaPac() != null && mInvoice.getCtaPac().getEXME_CtaPacExt_ID() > 0) {
			// extension cero
			int extCeroID = mInvoice.getCtaPac().getEXME_CtaPacExt_ID();
			// Pago de la extension a facturar
			// final MCtaPacPag mCtaPacPag = MCtaPacPag.getMCtaPacPag(getCtx(),
			// mPayment.getC_Payment_ID(), mInvoice.getEXME_CtaPacExt_ID(), trxName);
			mCtaPacPag.setIsPay(true);// se marca como asignado
			//mCtaPacPag.setDisponible(mPayment.getAvailable());// la cantidad no relacionada
			mCtaPacPag.setAplicado(mCtaPacPag.getAplicado().subtract(mPayment.getAvailable()));
			if (!mCtaPacPag.save(trxName)) {
				log.log(Level.SEVERE,
						"MEXMEAnticipo.reactivarAnticipo - MCtaPacPag=" + mCtaPacPag.getEXME_CtaPacPag_ID());
				return success;
			}

			BigDecimal available = mPayment.getAvailable();
			if (available.compareTo(Env.ZERO) > 0) {
				boolean continuar = true;
				// Pago de la extension cero
				MCtaPacPag mOld = MCtaPacPag.getMCtaPacPag(Env.getCtx(), mPayment.getC_Payment_ID(), extCeroID, trxName);
				if (mOld != null && mOld.getEXME_CtaPacPag_ID() > 0) {
					available = available.add(mOld.getAplicado());
					if (!mOld.delete(true, trxName)) {
						mOld.setIsActive(false);
						continuar = mOld.save(trxName);
					}
				}

				if(continuar){// Cada pago se asigna a la extension cero
					final MCtaPacPag mCtaPacPagNew = new MCtaPacPag(getCtx(), 0, trxName);
					PO.copyValues(mCtaPacPag, mCtaPacPagNew);
					mCtaPacPagNew.setIsPay(false);// se marca como aun no asignado
					mCtaPacPagNew.setEXME_CtaPacExt_ID(extCeroID);// se pasa a la extension cero
					//mCtaPacPagNew.setDisponible(available);// la cantidad aun no se relaciona por lo que es cero
					mCtaPacPagNew.setAplicado(available);
					if(!mCtaPacPagNew.save(trxName)){
						log.log(Level.SEVERE, "MEXMEAnticipo.reactivarAnticipo - MCtaPacPag=" + mCtaPacPag.getEXME_CtaPacPag_ID());
						return success;
					}
				}

				// identificar el monto del anticipo
				mAnticipoExtCero.setTotal(mAnticipoExtCero.getTotal().add(mPayment.getAvailable()));
				mAnticipoExtCero.setAplicado(Env.ZERO);// siempre cero en la extension cero
				mAnticipoExtCero.setSaldo(Env.ZERO);// siempre cero en la extension cero
				if(!mAnticipoExtCero.save(trxName)){
					log.log(Level.SEVERE, "MEXMEAnticipo.reactivarAnticipo - MCtaPacPag=" + mCtaPacPag.getEXME_CtaPacPag_ID());
					return success;
				}
			}
		}// fin rel. ext Cero
		
		return true;
	}
	
//	/**@deprecated no utilizado*/
//	public String cancelarAnticipos(final Properties ctx, final List<MCashLine> lstCashLine,
//			final int C_Cash_ID,           
//			/*final MInvoice nuevaFact, */  final MInvoice invoiceOld, /*,/* boolean reAsignacion,*/
//			/*final boolean porExtension*/final MEXMECtaPacExt extensionCero, final String trxName){	
//		
////		if (invoiceOld.getEXME_CtaPacExt_ID()>0) {
//////			// En refacturacion las asignaciones de acticipos se hacen a la nueva factura
//////			if(reAsignacion) {
//////				if(!MEXMEAnticipo.reasignarAsignaciones(ctx, invoiceOld.getC_Invoice_ID(),nuevaFact.getC_Invoice_ID(),trxName)){
//////					return Utilerias.getLabel("error.caja.payment.noSave");
//////				}
//////				
//////				
//////			} else {
////
////				// Se eliminan las asignaciones de pagos para que queden disponibles otra vez
////				// solo tene lienas si es que tuvo anticipos
//////				if(MEXMEAnticipo.eliminarAsignaciones(ctx, invoiceOld.getC_Invoice_ID(),trxName)==0){
//////					return null;
//////				}
////			
////				// Asignaciones de la factura
////				final List<MAllocationHdr> allocationHdr = MEXMEAllocationHdr.getAllocationHrdInv(ctx
////						, invoiceOld.getC_Invoice_ID()
////						, true
////						, trxName);
////				if(!allocationHdr.isEmpty()){
////					
////					// Revertir asignacion
////					for (final MAllocationHdr mAllocationHdr: allocationHdr) {
////
////						// Crear la asignacion de la devolucion a la nota de credito
////						final MAllocationHdr allocHdrNota = new MAllocationHdr(ctx, false
////								, DB.convert(ctx, new Timestamp(System.currentTimeMillis()))
////								, mAllocationHdr.getC_Currency_ID()
////								, Msg.translate(ctx, "Rev")+" "+mAllocationHdr.toString()
////								, trxName);
////						if(allocHdrNota !=null && !allocHdrNota.save(trxName)) {
////							return "error.caja.allocHdr.noSave";
////						}	
////
////
////						if(!allocHdrNota.reversePrePayment()){
////							slog.log(Level.SEVERE, "Extension");
////							return Utilerias.getLabel("error.caja.payment.noSave");
////						} else {
////							allocHdrNota.setDocStatus(allocHdrNota.prepareIt());
////							if(!allocHdrNota.save(trxName)){
////								slog.log(Level.SEVERE, "Extension1");
////								return Utilerias.getLabel("error.caja.payment.noSave");
////							}
////						}
////					}
////					
////					// Cuando existe la cuenta paciente
////					// La extension que se cancelo queda sin anticipos
////					final MEXMECtaPacExt extensionCero = new MEXMECtaPacExt(ctx,invoiceOld.getCtaPac().getEXME_CtaPacExt_ID(), trxName);
////					final MEXMECtaPacExt extension = new MEXMECtaPacExt(ctx, invoiceOld.getEXME_CtaPacExt_ID(), trxName);
////					extension.setAnticipo(Env.ZERO);
////					if(!extension.save(trxName)){
////						slog.log(Level.SEVERE, "Extension");
////						return Utilerias.getLabel("error.caja.payment.noSave");
////					}
////
////					// Se busca los anticipo de la extension para actualizar el registro a la extension cero 
////					final MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipo(
////							ctx
////							, invoiceOld.getEXME_CtaPac_ID()
////							, invoiceOld.getEXME_CtaPacExt_ID()
////							, trxName);
//
//					// borrar y actualizar MCtaPacPag para la extension cero
//					if(!reactivarAnticipo(invoiceOld, extensionCero, trxName)) {
//						slog.log(Level.SEVERE, "pagos (No se actualizo la extension cancelada)");
//						return "error.caja.payment.noSave";
//					}
//
//					// Si existe anticipo de traspasara a la extension cero
//					if(!reactivarAnticipoExtCero(ctx, lstCashLine,C_Cash_ID, trxName,invoiceOld,extensionCero)){
//						slog.log(Level.SEVERE, " pagos (No se actualizará el total del anticipo de la extension cero a partir de la extension cancelada) ");
//						return "error.caja.payment.noSave";			
//					}
////				}
////		}
//		return null;
//	}
	

/*
	public static String cancelarAnticipos(final Properties ctx, final MEXMECashLine[] lstCashLine,
			final int C_Cash_ID,        final String trxName,   
			final MInvoice nuevaFact,   final MInvoice invoiceOld,  boolean reAsignacion,
			final boolean porExtension, final int opcion){	
		
		if (porExtension) {
			// En refacturacion las asignaciones de acticipos se hacen a la nueva factura
			if(reAsignacion) {
//				if(!MEXMEAnticipo.reasignarAsignaciones(ctx, invoiceOld.getC_Invoice_ID(),nuevaFact.getC_Invoice_ID(),trxName)){
//					return Utilerias.getLabel("error.caja.payment.noSave");
//				}
				// Asignaciones de facturas (PARCHE PARA SOLVENTAR EL ERROR DE PERDIDA DE ASIGNACIONES)
				rebilling(C_Cash_ID, invoiceOld, nuevaFact, trxName);
				
			} else {

				// Se eliminan las asignaciones de pagos para que queden disponibles otra vez
				// solo tene lienas si es que tuvo anticipos
//				if(MEXMEAnticipo.eliminarAsignaciones(ctx, invoiceOld.getC_Invoice_ID(),trxName)==0){
//					return null;
//				}
				// Asignaciones de facturas (PARCHE PARA SOLVENTAR EL ERROR DE PERDIDA DE ASIGNACIONES)
				final MAllocationHdr[] allocation = MEXMEAllocationHdr.getAllocationHrdInv(ctx, invoiceOld.getC_Invoice_ID(), null, trxName);
				if(allocation.length>0){
					// crear asignaciones negativas que maten el saldo a favor del cliente, aplica para cancelacion como para refacturacion
					copyAllocationLine(allocation, invoiceOld, true, trxName);


					// Cuando existe la cuenta paciente
					// La extension que se cancelo queda sin anticipos
					final MEXMECtaPacExt extensionCero = new MEXMECtaPacExt(ctx,invoiceOld.getCtaPac().getEXME_CtaPacExt_ID(), trxName);
					final MEXMECtaPacExt extension = new MEXMECtaPacExt(ctx, invoiceOld.getEXME_CtaPacExt_ID(), trxName);
					extension.setAnticipo(Env.ZERO);
					if(!extension.save(trxName)){
						slog.log(Level.SEVERE, "Extension");
						return Utilerias.getLabel("error.caja.payment.noSave");
					}


					// Se busca los anticipo de la extension para actualizar el registro a la extension cero 
					final MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipo(
							ctx
							, invoiceOld.getEXME_CtaPac_ID()
							, invoiceOld.getEXME_CtaPacExt_ID()
							, trxName);

					// borrar y actualizar MCtaPacPag para la extension cero
					if (anticipo==null || (anticipo != null && !anticipo.reactivarAnticipo(
							invoiceOld, extensionCero, trxName))) {
						slog.log(Level.SEVERE, "pagos (No se actualizo la extension cancelada)");
						return Utilerias.getLabel("error.caja.payment.noSave");
					}

					// Si existe anticipo de traspasara a la extension cero
					if(anticipo==null || (anticipo != null && !anticipo.reactivarAnticipoExtCero(
							ctx, lstCashLine,
							C_Cash_ID,    trxName,    
							nuevaFact,    invoiceOld, reAsignacion,
							porExtension, opcion,     extensionCero))){
						slog.log(Level.SEVERE, " pagos (No se actualizará el total del anticipo de la extension cero a partir de la extension cancelada) ");
						return Utilerias.getLabel("error.caja.payment.noSave");			
					}
				}
			}
		}
		return null;
	}
*/
//	/**
//	 * Crear asignaciones a partir de anticipos
//	 * @param allocationHdr: Asignaciones anteriores de los anticipos
//	 * @param creditMemo: Nuevo documento
//	 * @param reverse: true si es solo una devolción
//	 * @param trxName: Nombre de transaccción
//	 * @return
//	 */
//	private static String copyAllocationLine(final MAllocationHdr[] allocationHdr, final MInvoice mInvoice, final boolean reverse, final String trxName){
//		String error = null;
//		MAllocationHdr header = null;
//
//		for (final MAllocationHdr mAllocHdr:allocationHdr) {
//			
//			// Allocation Header
//			if(header==null && error==null){
//				header = new MAllocationHdr(mInvoice.getCtx()
//						, false
//						, new Timestamp(System.currentTimeMillis())
//						, mInvoice.getC_Currency_ID()
//						, reverse? Msg.translate(mInvoice.getCtx(), "Rev")+" "+mAllocHdr.toString():mAllocHdr.toString()
//						, trxName);
//				header.setAD_Org_ID(mInvoice.getAD_Org_ID());
//				if (!header.save()){
//					error = "error.caja.allocHdr.noSave";
//				}
//			}
//			
//			// Allocation line
//			final MAllocationLine[] allocationLine = mAllocHdr.getLines(true);
//			for (final MAllocationLine mAllocLine:allocationLine) {
//
//				if(error==null){
//					final MAllocationLine devolAllocLine = new MAllocationLine (
//							header 
//							, reverse?mAllocLine.getAmount().negate():mAllocLine.getAmount() 
//							, reverse?mAllocLine.getDiscountAmt().negate():mAllocLine.getDiscountAmt() 
//							, reverse?mAllocLine.getWriteOffAmt().negate():mAllocLine.getWriteOffAmt() 
//							, reverse?mAllocLine.getOverUnderAmt().negate():mAllocLine.getOverUnderAmt() );
//					devolAllocLine.set_TrxName(trxName);
////					devolAllocLine.setParent(header);
//					devolAllocLine.setDocInfo(mInvoice.getC_BPartner_ID(), 0, mInvoice.getC_Invoice_ID());
//					devolAllocLine.setPaymentInfo(mAllocLine.getC_Payment_ID(),mAllocLine.getC_CashLine_ID());
//					devolAllocLine.setAmtAcct(mAllocLine.getAmtAcct());
//					devolAllocLine.setC_AllocationHdr_ID(header.getC_AllocationHdr_ID());
//					devolAllocLine.setC_Invoice_ID(mInvoice.getC_Invoice_ID());
//					if(!devolAllocLine.save(trxName)) {
//						error = "error.caja.cashLine.noSave";
//					}
//				} else {
//					break;
//				}
//			}
//			
//			if(error==null){
//				header.setDocStatus(header.prepareIt());
//				if(!header.save(trxName)){
//					error = "error.caja.cashLine.noSave";
//				}
//			} else {
//				break;
//			}
//		}
//		return error;
//	}
	
//	/**@deprecated no utilizado (se usa desde {@link #cancelarAnticipos(Properties, List, int, MInvoice, MEXMECtaPacExt, String)}*/
//	public boolean reactivarAnticipoExtCero(final Properties ctx,      final List<MCashLine> lstCashLine,
//			final int C_Cash_ID,            final String trxName,      
//			/*final MInvoice nuevaFact,      */ final MInvoice invoiceOld,/* boolean reAsignacion,*/
//			/*final boolean porExtension,   */  final MEXMECtaPacExt extensionCero){
//		boolean continuar = false;
//		
//		// Reactivación de anticipos
//		final MEXMEAnticipo anticipoCero = MEXMEAnticipo.getAnticipo(
//				ctx
//				, extensionCero.getEXME_CtaPac_ID()
//				, extensionCero.getEXME_CtaPacExt_ID()
//				, trxName);
//		
//		// El anticipo de la extension cero será reemplazado
//		if (anticipoCero != null && anticipoCero.getEXME_Anticipo_ID() > 0) {
//			if (anticipoCero.delete(true, trxName)) {
//				continuar = true;
//				
//			} else {	
//				anticipoCero.setIsActive(false);
//				continuar = anticipoCero.save(trxName);
//			}
//		} else {
//			continuar = true;
//		}
//
//		
//		// Se borro el anticipo de la extension utilizaremos el que estaba en la extension N
//		// para la extension cero
//		if (continuar) {
//			
//			// Traer todos los ctapacpag de la extension cero
//			final MCtaPacPag[] arreglo = MCtaPacPag.getAnticipos(
//					Env.getCtx()
//					, null
//					, extensionCero.getEXME_CtaPacExt_ID()
//					, trxName);
//			
//			// Total del anticipo
//			BigDecimal sumTotal = Env.ZERO;
//			for (int i = 0; i < arreglo.length; i++) {
//				sumTotal = sumTotal.add(arreglo[i].getPayment().getPaymentAvailable());
//			}
//			
//			setEXME_CtaPacExt_ID(extensionCero.getEXME_CtaPacExt_ID());
//			setTotal(sumTotal);
//			setAplicado(Env.ZERO);
//			setSaldo(sumTotal);
//			if(!save(trxName)){
//				slog.log(Level.SEVERE, " pagos (No se actualizo el total del anticipo de la extension cero) ");
//				return false;
//			}
//		}
//		
//		return continuar;
//	}
	
//	public static int eliminarAsignaciones(final Properties ctx, final int cInvoiceId, final String trxName){
//		// Reset (delete) Allocations
//		// en un allocationHdr no debe haber mas de una factura
//		final MEXMEAllocationHdr[] allocationHdr = MEXMEAllocationHdr.getAllocationHrdInv(ctx, cInvoiceId, trxName);
//		int success = allocationHdr.length;
//		
////		if(success>0) {
//
//			for (int i = 0; i < allocationHdr.length; i++) {
//				if (!allocationHdr[i].delete(true, trxName)){
//					slog.log(Level.SEVERE, "MEXMEAnticipo.reactivarAnticipo - allocationHdr=" + allocationHdr[i].getC_AllocationHdr_ID());
//					success = 0;
//				}
//			}
////		}
//		return success;
//	}

//	/**
//	 * Al refacturar :
//	 * - Las asignaciones de cobros y anticipos se crean negativas a la nota de credito
//	 * - Las asignaciones de cobros y anticipos se crean positivas hacia la nueva factura
//	 * @param cashID: nuevo diario de caja
//	 * @param creditMemo: nota de credito
//	 * @param newInvoice: nueva factura
//	 * @param trxName: nombre de transacción
//	 * @return error ó null si no hubo ningún problema
//	 */
//	public static String rebilling(final int cashID
//			, final MInvoice oldInvoice
//			, final MInvoice newInvoice
//			, final String trxName){
//		String error = null;
//		// Buscamos si existen asignaciones de Anticipos y cobros para la factura.
//		final MAllocationHdr[] allocationLine = MEXMEAllocationHdr.getAllocationHrdInv(oldInvoice.getCtx(), oldInvoice.getC_Invoice_ID(), null, trxName);
//		// Copiamos las asignaciones pasadas hacia la nota de credito pero ahora negativas
//		error = copyAllocationLine(allocationLine, oldInvoice, true, trxName);
//		// Copiamos las asignaciones pasadas hacia la nueva factura pero positivas
//		if(error==null){
//			error = copyAllocationLine(allocationLine, newInvoice, false, trxName);
//		}
//		return error;
//	}

//	/**@deprecated no utilizado*/
//	public static boolean reasignarAsignaciones(final Properties ctx, final int cInvoiceOldId, final int cInvoiceNewId, final String trxName){
//		// Reset (delete) Allocations
//		// en un allocationHdr no debe haber mas de una factura
//		final MAllocationLine[] lines = MAllocationLine.getOfInvoice(ctx, cInvoiceOldId, trxName);
//		for (MAllocationLine allocation : lines) {
//			allocation.setC_Invoice_ID(cInvoiceNewId);
//			if (!allocation.save()) {
//				return false;
//			}
//		}
//		return true;
//	}

	/**
	 * Reactivar anticipo
	 * @param cInvoice FACTURA A CANCELAR
	 * @param trxName Nombre de transaccion
	 * @return true: Correcto
	 */
	public boolean reactivarAnticipo(final MInvoice cInvoice, final MEXMECtaPacExt extensionCero, final String trxName){
		boolean success = false;
		
		// Obtenemos los pagos de la extension
		final MCtaPacPag[] pagos = MCtaPacPag.getAnticipos(getCtx(), null, cInvoice.getEXME_CtaPacExt_ID(), trxName);
		for(int i = 0; i < pagos.length; i++){
			
			// Se mueve a la extension cero al cancelarse
			boolean continuar = false;
			
			// Si el pago esta partido y ALGUNA parte esta en la extension cero
			// se borra para no tener duplicidad del mismo pago
			final MCtaPacPag mOld = MCtaPacPag.getMCtaPacPag(Env.getCtx(), 
					pagos[i].getC_Payment_ID(),
					extensionCero.getEXME_CtaPacExt_ID(), trxName);
			if(mOld==null || (mOld!=null && mOld.getEXME_CtaPacPag_ID()<1)){
				continuar = true;
				
			} else if( mOld.getEXME_CtaPacPag_ID()>0){
				if(mOld.delete(true, trxName)){
					continuar = true;
				} else {
					mOld.setIsActive(false);
					continuar = mOld.save(trxName);
				}
			}
			
			// Pasar los pagos de la extension N a la extension CERO
			if(continuar){
				//pagos[i].getC_Payment_ID();
				BigDecimal amtAvailable = pagos[i].getPayment().getPaymentAvailable();
				
				pagos[i].setEXME_CtaPacExt_ID(extensionCero.getEXME_CtaPacExt_ID());
				pagos[i].setIsActive(true);
				pagos[i].setDisponible(amtAvailable);
				pagos[i].setIsPay(false);
				if (!pagos[i].save(trxName)) {
					log.log(Level.SEVERE, "MCtaPacPag =" + pagos[i].getEXME_CtaPacPag_ID());
					return success;
				}
			}

			// Los pagos directos se relacionan a la factura, CXC
			pagos[i].getPayment().setC_Invoice_ID(0);
			boolean sucess = pagos[i].getPayment().testAllocation();
			
			
			if(!sucess){
				log.log(Level.SEVERE, "MEXMEAnticipo.reactivarAnticipo - MCtaPacPag=" + pagos[i].getEXME_CtaPacPag_ID());

				BigDecimal alloc =  pagos[i].getPayment().getAllocatedAmt();
				if (alloc == null){
					alloc = Env.ZERO;
				}
				
				BigDecimal total =  pagos[i].getPayment().getPayAmt();
				if (!pagos[i].getPayment().isReceipt()){
					total = total.negate();
				}
				
				boolean test = total.compareTo(alloc) == 0;
				boolean change = test != pagos[i].getPayment().isAllocated();
				
				if (change){
					pagos[i].getPayment().setIsAllocated(test);
				}
				log.fine("Allocated=" + test + " (" + alloc + "=" + total + ")");
			}

			pagos[i].getPayment().setIsAllocated (true); // PERMITE NO SELECCIONARLO DESDE WAllocation
			if(!pagos[i].getPayment().save(trxName)){
				log.log(Level.SEVERE, "MEXMEAnticipo.reactivarAnticipo - MCtaPacPag=" + pagos[i].getEXME_CtaPacPag_ID());
				return success;
			}
			
		}//TODO si no hay debe tener Payment().setC_Invoice_ID(0);Payment().testAllocation()){Payment().save()){
		
		return true;
	}

	/**
	 * Anticipos de la cuenta paciente
	 * 
	 * @deprecated
	 *             para un caso particular de 1 anticipo mayor al total de la factura
	 *             anticipo se repite para 2 CtaPacPag, lo mismo para extension.
	 *             El metodo que lo utiliza requiere los objetos PO para poder actualizar o borrar
	 *             por lo que no presenta ventaja al traer los Ids en un solo query,
	 *             ya que al iterarlo se vuelve a consultar la BD para instanciarse.
	 * */
	public static List<BeanView> getAllPrepayment(final Properties ctx, final int ctaPacId, final String trxName){
		final List<BeanView> lst = new ArrayList<BeanView>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT DISTINCT cpp.EXME_CtaPacPag_ID, cpe.EXME_CtaPacExt_ID, anti.EXME_Anticipo_ID ")
		.append(" FROM   EXME_Anticipo anti       ")
		.append(" INNER  JOIN EXME_CtaPacPag cpp ON cpp.EXME_CtaPacExt_ID = anti.EXME_CtaPacExt_ID ")
		.append(" INNER  JOIN EXME_CtaPacExt cpe ON cpe.EXME_CtaPacExt_ID = cpp.EXME_CtaPacExt_ID  ")
		.append(" WHERE  anti.IsActive = 'Y'      ")
		.append(" AND    anti.EXME_CtaPac_ID = ?  ")
		.append(" AND    cpe.C_Invoice_ID IS NULL ")
		.append(" ORDER  BY cpe.EXME_CtaPacExt_ID, anti.EXME_Anticipo_ID, cpp.EXME_CtaPacPag_ID ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(),trxName);
			pstmt.setInt(1, ctaPacId);

			rs = pstmt.executeQuery();
			while(rs.next()){
				final BeanView mBeanView = new BeanView();
				mBeanView.setInteger1(rs.getInt("EXME_Anticipo_ID"));
				mBeanView.setInteger2(rs.getInt("EXME_CtaPacPag_ID"));
				mBeanView.setInteger3(rs.getInt("EXME_CtaPacExt_ID"));
				lst.add(mBeanView);
			}
		} catch (Exception e){
			slog.log(Level.SEVERE, "getAnticipo ", e);
			
		} finally{
			DB.close(rs,pstmt);
		}
		return lst;
	}

	//CARD #1299
	/**
	 * Anticipos de la cuenta paciente, por extensi&oacuten
	 * @param ctx : contexto
	 * @param EXME_CtaPacExt_ID : Extension
	 * @param trxName : Nombre de transacci&oacuten
	 * @return MEXMEAnticipo 
	 */
	public static MEXMEAnticipo getAnticipo(final Properties ctx, final int EXME_CtaPacExt_ID, final String trxName){
		return new Query(ctx, Table_Name, " EXME_CtaPacExt_ID = ? ", trxName)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setOrderBy(" ORDER BY Created ASC ")
		.setParameters(EXME_CtaPacExt_ID)
		.first();
	}
	
	/**
	 * Anticipos de la cuenta paciente
	 * @param ctx : contexto
	 * @param exmeCtaPacID : Cuenta Paciente
	 * @param trxName : Nombre de transacci&oacuten
	 * @return MEXMEAnticipo 
	 */
	public static List<MEXMEAnticipo> getAnticiposXCtaPac(final Properties ctx, String ctasPacIds, final String trxName){
		return new Query(ctx, Table_Name, " EXME_CtaPac_ID IN ( "+ctasPacIds, trxName)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setOrderBy(" ORDER BY Created ASC ")
		.list();
	}
	
	/**
	 * USA Anticipos de la cuenta paciente
	 * @param ctx
	 * @param exmeCtaPacID
	 * @param where
	 * @param trxName
	 * @return
	 */
	public static List <MEXMEAnticipo> getAnticiposXCtaPac(final Properties ctx, final int exmeCtaPacID, final String where, final String trxName){
		final  List <MEXMEAnticipo>  anticipo = new ArrayList <MEXMEAnticipo>();
		final  StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT  * ")
		.append(" FROM EXME_Anticipo ")
		.append(" INNER JOIN EXME_CtaPacExt ext on EXME_Anticipo.EXME_CtaPacExt_ID = ext.EXME_CtaPacExt_ID ")
		.append(" WHERE EXME_Anticipo.EXME_CtaPac_ID = ? ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
		.append(" AND (ext.C_Invoice_ID IS NULL OR EXME_Anticipo.Saldo > EXME_Anticipo.total) ")
		.append(" AND EXME_Anticipo.Total <> 0 ")
		.append(" AND EXME_Anticipo.isactive='Y' ")
		.append(where==null?StringUtils.EMPTY:where)
		.append(" ORDER BY ext.ExtensionNo ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
        	pstmt = DB.prepareStatement(sql.toString(), trxName);
        	pstmt.setInt(1, exmeCtaPacID);
        	rs = pstmt.executeQuery();
        	while(rs.next()){
        		anticipo.add(new MEXMEAnticipo(ctx,rs,trxName));
        	}
    	} catch (Exception e){
    		slog.log(Level.SEVERE, "getAnticipo ", e);
    	} finally{
    		DB.close(rs,pstmt);
    	}
    	
    	return anticipo;
	}	
	
	protected boolean beforeSave(boolean newRecord) {
		getExtension().updateTotalsExt(null);
		setSaldo(getTotal().subtract(getAplicado()));
		return true; 
	} //

	/** Obtener el encabezado de los anticipos por extensi&oacuten */
	public static BigDecimal getSumAplicado(Properties ctx, int extId, final String trxName) {
		final  StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT SUM(COALESCE(EXME_CtaPacPag.Aplicado,0)) AS amt  ")
		.append(" FROM  EXME_CtaPacPag ")
		.append(" WHERE EXME_CtaPacPag.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",X_EXME_CtaPacPag.Table_Name))
		.append(" AND   EXME_CtaPacPag.EXME_CtaPacExt_ID = ?  ")
		.append(" GROUP BY  EXME_CtaPacPag.EXME_CtaPacExt_ID  ");
		final BigDecimal amt = DB.getSQLValueBD(trxName, sql.toString(), extId);
        return amt==null ? Env.ZERO : amt;
	}

	/** Obtener el encabezado de los anticipos por extensi&oacuten */
	private static MEXMEAnticipo reloadAdvPaymentToExtension(Properties ctx, int extId, final String trxName) {
		final BigDecimal amt = MEXMEAnticipo.getSumAplicado(ctx, extId, trxName);
		
		final MEXMEAnticipo mAnticipo = MEXMEAnticipo.getAnticipoToExtension(ctx, new MEXMECtaPacExt(ctx, extId, trxName));
        //mAnticipo.setAplicado(amt==null?Env.ZERO:amt);//reload
        mAnticipo.setTotal(amt==null?Env.ZERO:amt);
        if(!mAnticipo.save(trxName)){
        	throw new MedsysException();
        }

        return mAnticipo;
	}


	/** Regresar el anticipo de la extensi&oacuten o crearlo en caso de no existir */
	public static MEXMEAnticipo getAnticipoToExtension(final Properties ctx, final MEXMECtaPacExt ctaPacExt){
		// 
		MEXMEAnticipo anticipo = MEXMEAnticipo.getAnticipo(ctx, ctaPacExt.getEXME_CtaPacExt_ID(), ctaPacExt.get_TrxName());
		if (anticipo == null) {
			anticipo = new MEXMEAnticipo(ctx, ctaPacExt.getEXME_CtaPac_ID(), ctaPacExt.getEXME_CtaPacExt_ID(), ctaPacExt.get_TrxName());
		}
		//
		if(!anticipo.save(ctaPacExt.get_TrxName())){
			throw new MedsysException();
		}
		return anticipo;
	}

	/** Instanciar la extensi&oacuten del anticipo  */
	public MEXMECtaPacExt getExtension(){
		if(mExtension == null && getEXME_CtaPacExt_ID()>0){
			mExtension = new MEXMECtaPacExt(getCtx(), getEXME_CtaPacExt_ID(), get_TrxName());
		}
		return mExtension;
	}

	/** Crear las l&iacuteneas del anticipo (relaci&oacuten pagos extensi&oacuten)
	 *  SI EL ANTICIPO ES NEGATIVO TAMBIEN DEBE INVOCAR devolAdvancePaymentOfCustomer */
	public void createAdvancePaymentCustomer(final MPayment mPayment, final int EXME_FormaPago_ID){
		final MEXMECtaPacPag mCtaPacPag = MEXMECtaPacPag.createFromPayment(mPayment, getEXME_CtaPacExt_ID());
		//setAplicado(getAplicado().add(mCtaPacPag.getAplicado()));// cero en devolución
		setTotal(getTotal().add(mCtaPacPag.getAplicado()));
		if(getEXME_CtaPacExt_ID() > 0){
			MEXMECtaPacExt extension = getExtension();
			extension.setAnticipo(getTotal().compareTo(extension.getGrandTotal()) > 0 ? extension.getGrandTotal() : getTotal());
			if(!extension.save(get_TrxName())){
				throw new MedsysException();
			}
		}
		if(!save(get_TrxName())){
			throw new MedsysException();
		}
		
		devolAdvancePaymentCustomer(mPayment, EXME_FormaPago_ID);
	}
	
	/** Cancelar los anticipos de de factura/remisi&oacuten por cancelaci&oacuten  (relaci&oacuten pagos extensi&oacuten) 
	 *  moviendo los anticipos a la extensi&oacuten cero de la cuenta opaciente */
	public void cancelAdvancePaymentCustomer(){
		moveAdvancePaymentCustomer(getExtension(), getExtension().getCtaPac().getExtCero(), null);
	}
	
	/** Mover las l&iacuteneas del anticipo de una extensi&oacuten a otra 
	 *  concidera que el pago ya puede existir en la nueva extensi&oacuten,
	 *  y que podr&iacutea estar devuelto o de ser de una cuenta diferente para el caso de merge de cuentas */
	public void moveAdvancePaymentCustomer(final MEXMECtaPacExt mCtaPacExt
			, final MEXMECtaPacExt mCtaPacExtTo
			, List<MEXMECtaPacPag> lPagos){
		
		if(lPagos==null){
			lPagos = MEXMECtaPacPag.getAnticipos(getCtx(), mCtaPacExt.getEXME_CtaPacExt_ID(), null, get_TrxName());
		}
		
		BigDecimal amt = Env.ZERO;
		for (final MEXMECtaPacPag mCtaPacPag: lPagos) {

			// Constraint C_Payment_ID - EXME_CtaPacExt_ID
			if(mCtaPacExt.getEXME_CtaPac_ID()==mCtaPacExtTo.getEXME_CtaPac_ID()){
				final MEXMECtaPacPag oCtaPacPag = MEXMECtaPacPag.getMCtaPacPag(getCtx()
						, mCtaPacPag.getC_Payment_ID()
						, mCtaPacExtTo.getEXME_CtaPacExt_ID()
						, get_TrxName());
				
				if(oCtaPacPag!=null){
					
					if(mCtaPacPag.isSeDevolvio()){//TODO Validar otros casos
						mCtaPacPag.setSeDevolvio(oCtaPacPag.getAplicado().compareTo(Env.ZERO)!=0);
					}
					
					mCtaPacPag.setAplicado(mCtaPacPag.getAplicado().add(oCtaPacPag.getAplicado()));
					if(!oCtaPacPag.delete(true)){
						throw new MedsysException();
					}//findel
				}
			}
			
			mCtaPacPag.setEXME_CtaPacExt_ID(mCtaPacExtTo.getEXME_CtaPacExt_ID());
			amt = amt.add(mCtaPacPag.getAplicado());
			if(!mCtaPacPag.save(get_TrxName())){
				throw new MedsysException();
			}
		}

		// Actializar los encabezados
		final MEXMEAnticipo anticipoNew = MEXMEAnticipo.getAnticipoToExtension(getCtx(), mCtaPacExtTo);
		anticipoNew.setTotal(anticipoNew.getTotal().add(amt));
		if(!anticipoNew.save(get_TrxName())){
			throw new MedsysException();
		}
		
		setTotal(getTotal().subtract(amt));
		if(!save(get_TrxName())){
			throw new MedsysException();
		}
	}
	
	/** Disminuye del saldo del anticipo la devoluci&oacuten afectando
	 * - Las l&iacuteneas de los pagos por extensi&oacuten 
	 * - Marca las l&iacuteneas como devueltas y mueve los anticipos a la extensi&oacuten cero
	 * - Actualiza el encabezado de cada extensi&oacuten modificada */
	private String devolAdvancePaymentCustomer(final MPayment mPaymentNeg, final int EXME_FormaPago_ID){
		if(mPaymentNeg.getPayAmt().signum()>=0){//-1 (negative), 0 (zero), or 1 (positive)
			return null;
		}
		
		final List<Integer> lExtAnticipo = new ArrayList<Integer>(); 
		final List<MEXMECtaPacPag> lMCtaPacPag = MEXMECtaPacPag.getAnticiposSinFacturar(
				getCtx(), get_TrxName(), getEXME_CtaPac_ID(), EXME_FormaPago_ID, true);
		BigDecimal devolAmt = mPaymentNeg.getPayAmt().abs();
		
		for (final MEXMECtaPacPag mCtaPacPag: lMCtaPacPag) {
			
			if(lExtAnticipo.indexOf(mCtaPacPag.getEXME_CtaPacExt_ID())<0){
				lExtAnticipo.add(mCtaPacPag.getEXME_CtaPacExt_ID());
			}
			//            -3 = 7 - 10
			BigDecimal saldo = devolAmt.subtract(mCtaPacPag.getAplicado());
			Boolean isNoDelete = true;
			if(saldo.compareTo(Env.ZERO)>=0){
				
				mCtaPacPag.setAplicado(Env.ZERO);// se devolvio todo
				mCtaPacPag.setSeDevolvio(true);
				int mCtaPacExtID = mCtaPacPag.getExtension().getCtaPac().getEXME_CtaPacExt_ID();
				if(mCtaPacPag.getEXME_CtaPacExt_ID() != mCtaPacExtID){
					if(mCtaPacPag.getExistPaymentFor(mCtaPacExtID) > 0){
						//se borra se devuelve todo y ya existe en la extension 0 
						if(mCtaPacPag.delete(true)){
							throw new MedsysException();
						}
						isNoDelete = false;
					} else {
						mCtaPacPag.setEXME_CtaPacExt_ID(mCtaPacExtID);
					}
				}
				if(lExtAnticipo.indexOf(mCtaPacPag.getExtension().getCtaPac().getEXME_CtaPacExt_ID())<0){
					lExtAnticipo.add(mCtaPacPag.getExtension().getCtaPac().getEXME_CtaPacExt_ID());	
				}
				devolAmt = saldo;
			} else if(saldo.compareTo(Env.ZERO)<0){
				
				mCtaPacPag.setAplicado(saldo.abs());// Solo una parte
				devolAmt = Env.ZERO;
			}
			
			if(isNoDelete){
				if(!mCtaPacPag.save(get_TrxName())){
					throw new MedsysException();
				}
			}
			
			if(devolAmt.compareTo(Env.ZERO)==0){
				break;
			}
		}
		
		if(devolAmt.compareTo(Env.ZERO)!=0){
			throw new MedsysException(Utilerias.getLabel("error.caja.ctapac"));
			
		} else {
			// Actualizar las extensiones que fueron modificadas
			for (int extId : lExtAnticipo) {
				final MEXMEAnticipo anticipoNew = MEXMEAnticipo.reloadAdvPaymentToExtension(getCtx(), extId, get_TrxName());
				if(!anticipoNew.save(get_TrxName())){
					throw new MedsysException();
				}
			}
		}
		return null;
	}
	
	/** Crear las asignaciones a partir de los anticipos que se tienen asignados a la extensi&oacuten */
	public BigDecimal allocateAdvancePaymentCustomer(final MInvoice mInvoice, final String trxName){
		BigDecimal anticipo = Env.ZERO;
		
		final List<MEXMECtaPacPag> lCtaPacPag = MEXMECtaPacPag.get(Env.getCtx()
				, null
				, mInvoice.getEXME_CtaPacExt_ID());
		if(!lCtaPacPag.isEmpty()){
			// Create Allocation
			final MAllocationHdr alloc = new MAllocationHdr (
					Env.getCtx(), 
					true,	//	manual
					Env.getCurrentDate(),  
					Env.getC_Currency_ID(getCtx()), 
					Env.getAD_User_Name(getCtx()) + " [j]", trxName);
			alloc.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
			if (!alloc.save(trxName)){
				throw new MedsysException("error.caja.ctaPacExt.noSave");
			}


			for (final MEXMECtaPacPag mCtaPacPag:lCtaPacPag) {
				final MAllocationLine aLine = new MAllocationLine (
						alloc 
						, mCtaPacPag.getAplicado() 
						, Env.ZERO //DiscountAmt
						, Env.ZERO //WriteOffAmt
						, Env.ZERO); //OverUnderAmt
				aLine.set_TrxName(trxName);
				aLine.setDocInfo(mInvoice.getC_BPartner_ID(), mInvoice.getC_Order_ID(), mInvoice.getC_Invoice_ID());// Nota de remision
				aLine.setPaymentInfo(mCtaPacPag.getC_Payment_ID(), 0);
				aLine.setOverUnderAmt(Env.ZERO);
				if (aLine.save(trxName)){
					anticipo = anticipo.add(mCtaPacPag.getAplicado()); 
				} else {
					throw new MedsysException("error.caja.ctaPacExt.noSave");
				}
			
			}
			
			alloc.completeInvoice(true);
//			alloc.setDocStatus(alloc.completeIt());// Completar
//			if (!alloc.save()) {
//				throw new MedsysException();
//			}
		}
		return anticipo;
	}

	/** Actualizar los totales */
	public void updateTotals() {
		//setAplicado(MEXMEAnticipo.getSumAplicado(getCtx(), getEXME_CtaPacExt_ID(), get_TrxName()));
		setTotal(MEXMEAnticipo.getSumAplicado(getCtx(), getEXME_CtaPacExt_ID(), get_TrxName()));
		if(!save(get_TrxName())){
			throw new MedsysException();
		}
	}
	
	/** Cobros por forma de pago.
	 *  Se deberá actualizar en cada distribuci&oacuten a otra extensi&oacuten */
	private void setPayments(final List<CtaPacPag> distribucion){
		if(mapPayMethod==null){
			mapPayMethod  = new HashMap<Integer, List<MEXMECtaPacPag>>();
		}
		for (final CtaPacPag mCtaPacPag: distribucion) {
			if(!mapPayMethod.containsKey(mCtaPacPag.getmFormaPago().getEXME_FormaPago_ID())){
				mapPayMethod.put(mCtaPacPag.getmFormaPago().getEXME_FormaPago_ID(), mCtaPacPag.getLstPayments());
			}
		}
	}
	
	/** La distribuci&oacuten es por forma de pago 
	 *  @throws Exception */
	public boolean reallocatePayments(final ErrorList error, final List<CtaPacPag> distribucion, final List<CtaPacPag> lines, final String trxName) throws Exception {
		final Map<Integer, MEXMEAnticipo> mapAnticipo  = new HashMap<Integer, MEXMEAnticipo>();
		if(lines == null || lines.isEmpty()) {
			return error.isEmpty();
		}
		
		set_TrxName(trxName);
		setPayments(distribucion);
		
		// Distribuir en lineas por extensi&oacuten
		for (final CtaPacPag mline : lines) {
			int extDesId = mline.getmExtension().getEXME_CtaPacExt_ID();
			if(!mapAnticipo.containsKey(extDesId)){
				mapAnticipo.put(extDesId, MEXMEAnticipo.getAnticipoToExtension(getCtx(), mline.getmExtension()));
			}
			
			if(!distribuirPago(error, mline)){
				break;
			}
		}
		
//		// movido a AllocarionOfCustomerAdv line 278 (save())
//		// Crear/Actualizar el encabezado por extensi&oacuten destino
//		Iterator<Entry<Integer, MEXMEAnticipo>> itEntries = mapAnticipo.entrySet().iterator();
//		while (itEntries.hasNext()) {
//			(itEntries.next()).getValue().updateTotals();
//		}
		
		// Actualizar
		updateTotals();
		return error.isEmpty(); 
	}
	
	/** La distribuci&oacuten es por forma de pago 
	 *  @throws Exception */
	private boolean distribuirPago(final ErrorList error, final CtaPacPag oUserFormaPago) throws Exception {
		
		// Usuario //390
		BigDecimal              amtPay = oUserFormaPago.getAmtAllocated();
		final List<MEXMECtaPacPag>    lcpp = mapPayMethod.get(oUserFormaPago.getmFormaPago().getEXME_FormaPago_ID());
		if(lcpp==null || lcpp.isEmpty()){
			error.add(Error.VALIDATION_ERROR,"error.noExisten.anticiposFormaPago.seleccionada");
			return error.isEmpty();
		}
		
		// Iterar los pagos que tiene registrado el sistema por forma de pago
		for (final MEXMECtaPacPag oLine: lcpp) {
			// El usuario ingreso el monto del anticipo a mover por forma de pago
			// sin embargo en el sistema, vario pagos pueden sumarse para dar el monto por forma de pago
			if(amtPay.compareTo(Env.ZERO)>0 && oLine.getEXME_CtaPacPag_ID()>0 && oLine.getAplicado().compareTo(Env.ZERO)>0){
				
				// No repertir la relaci&oacuten extensi&oacuten - pago
				MEXMECtaPacPag mLineNew = MEXMECtaPacPag.getMCtaPacPag(getCtx()
						, oLine.getC_Payment_ID()
						, oUserFormaPago.getmExtension().getEXME_CtaPacExt_ID()
						, get_TrxName());
				
				if(mLineNew==null){
					mLineNew = new MEXMECtaPacPag(getCtx(), 0, get_TrxName());
					mLineNew.setEXME_CtaPacExt_ID(oUserFormaPago.getmExtension().getEXME_CtaPacExt_ID());//Nueva extension
					mLineNew.setC_Payment_ID(oLine.getC_Payment_ID());
				}
				// 386 = 390 - 4 || 206 = 386 - 180 || 92 = 206 - 114 || -118.00 = 92 - 210
				// Pago x forma menos el monto De Pago  
				BigDecimal saldo = amtPay.subtract(oLine.getAplicado());
				if(saldo.compareTo(Env.ZERO)>=0){
					//(30<RestoDePreviaExtension> = 100<TecleadoPorElUsuario> - 70<PrevioEnOtraExtension>)
					mLineNew.setAplicado(mLineNew.getAplicado().add(oLine.getAplicado()));// 
					oLine.setAplicado(Env.ZERO);
					if(!oLine.delete(true,get_TrxName())){
						throw new MedsysException();	
					}
					
					amtPay = saldo;

				} else if(saldo.compareTo(Env.ZERO)<0){
					// -30<RestoDePreviaExtension> = 70<TecleadoPorElUsuario> - 100<PrevioEnOtraExtension>)
					mLineNew.setAplicado(mLineNew.getAplicado().add(amtPay));
					oLine.setAplicado(saldo.abs());
					if(!oLine.save(get_TrxName())){
						throw new MedsysException();
					}
					
					amtPay = Env.ZERO;
				}
				
				if(!mLineNew.save(get_TrxName())){
					throw new MedsysException();
				}
			} else if(amtPay.compareTo(Env.ZERO)<=0){
				// fin validar montos arriba de cero
				break;
			}
		}// fin for
		return error.isEmpty();
	}
	
	private BigDecimal getCobrosMenosDevolucionesMenosAsignados() {
		String sql = "SELECT COALESCE(SUM(paymentAvailable(C_Payment_ID)),0) FROM C_Payment WHERE EXME_CtaPac_ID = ? ";
		BigDecimal monto = DB.getSQLValueBD(null, sql, getEXME_CtaPac_ID());
		return monto==null?Env.ZERO:monto;
	}
	
	private List<AdvancePaymentBean> reloadPrePayment(final MInvoice mInvoice, final String trxName){
		final List<AdvancePaymentBean> bPayments = new ArrayList<AdvancePaymentBean>();
		
		// Monto total del anticipo (Tomando en cuenta las devoluciones y los pagos asignados previamente)
		BigDecimal payAmt = getCobrosMenosDevolucionesMenosAsignados();
		if(payAmt.compareTo(Env.ZERO)>0){

			// Pagos relacionados a la extension (Sin los pagos que son devoluciones pero no descuenta el monto de la devolución)
			final List<MPayment> lPayments = MCtaPacPag.getPaymentsExtension(getCtx(),mInvoice.getEXME_CtaPacExt_ID(), trxName);
			BigDecimal total = Env.ZERO;

			for (final MPayment mPayment: lPayments) {
				total = total.add(mPayment.getAvailable());// 80 // 70

				// 80 - 100 // 150 - 100
				if(total.compareTo(mInvoice.getGrandTotal())>0){//
					final BigDecimal x = total.subtract(mPayment.getAvailable());// 150 - 70 = 80
					final BigDecimal y = mInvoice.getGrandTotal().subtract(x);//100 - 80 = 20

					bPayments.add(new AdvancePaymentBean(mPayment, y));break;// 20
				} else {
					bPayments.add(new AdvancePaymentBean(mPayment, mPayment.getAvailable()));
				}


			}
		}
		return bPayments;
	}
	
	private class AdvancePaymentBean {
		MPayment mPayment = null;
		public MPayment getmPayment() {
			return mPayment;
		}
		BigDecimal saldo = null;
		public BigDecimal getSaldo() {
			return saldo;
		}
		public AdvancePaymentBean(final MPayment pPayment, final BigDecimal pSaldo){
			mPayment = pPayment;
			saldo = pSaldo;
		}
	}
	
	private List<MAllocationLine> allocationPrePayment(final MInvoice mInvoice, final String trxName){
		final List<MAllocationLine> lAllocLine = new ArrayList<MAllocationLine>();
		//
		final List<AdvancePaymentBean> bPayments = reloadPrePayment(mInvoice, trxName);
		if(!bPayments.isEmpty()){

			// Create Allocation
			final MAllocationHdr alloc = new MAllocationHdr (getCtx()
					,true,Env.getCurrentDate()
					,Env.getC_Currency_ID(getCtx())
					,Env.getAD_User_Name(getCtx())+" [k]", trxName);
			alloc.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
			if (!alloc.save(trxName)){
				throw new MedsysException();
			}

			for (final AdvancePaymentBean mPayment: bPayments) {
				// Allocation Line
				final MAllocationLine aLine = new MAllocationLine (
						alloc, 
						mPayment.getSaldo(), 
						Env.ZERO,
						Env.ZERO,
						Env.ZERO);
				aLine.set_TrxName(trxName);
				aLine.setDocInfo(mInvoice.getC_BPartner_ID(), mInvoice.getC_Order_ID(), mInvoice.getC_Invoice_ID());// Nota de remision
				aLine.setPaymentInfo(mPayment.getmPayment().getC_Payment_ID(), 0);
				aLine.setOverUnderAmt(Env.ZERO);

				if (aLine.save(trxName)){
					lAllocLine.add(aLine);
				} else {
					throw new MedsysException();
				}
			}

			alloc.completeInvoice(true);
		}
		return lAllocLine;
	}
	
	/** Test */
	public static BigDecimal reloadPrePaymentTest(final MInvoice mInvoice){
		final MEXMEAnticipo mAnticipo = new MEXMEAnticipo(mInvoice.getCtx(), 0, null);
		mAnticipo.setEXME_CtaPac_ID(mInvoice.getEXME_CtaPac_ID());
		mAnticipo.setEXME_CtaPacExt_ID(mInvoice.getEXME_CtaPacExt_ID());
		
		final List<AdvancePaymentBean> lPayments = mAnticipo.reloadPrePayment( mInvoice, null);
		BigDecimal anticipo = Env.ZERO;
		for (final AdvancePaymentBean mPayment: lPayments) {
			anticipo = anticipo.add(mPayment.getSaldo());
		}
		return anticipo;
	}
	
	/** Test */
	public static BigDecimal allocationPrePaymentTest(final MInvoice mInvoice){
		final MEXMEAnticipo mAnticipo = new MEXMEAnticipo(mInvoice.getCtx(), 0, null);
		mAnticipo.setEXME_CtaPac_ID(mInvoice.getEXME_CtaPac_ID());
		mAnticipo.setEXME_CtaPacExt_ID(mInvoice.getEXME_CtaPacExt_ID());
		
		final List<MAllocationLine> lPayments = mAnticipo.allocationPrePayment( mInvoice, null);
		BigDecimal anticipo = Env.ZERO;
		for (final MAllocationLine mPayment: lPayments) {
			anticipo = anticipo.add(mPayment.getAmount());
		}
		return anticipo;
	}
	
	/**  
	 * Devuelve el valor aplicado en anticipos a una extension
	 * @param getEXME_CtaPac_ID
	 * @param EXME_CtaPacExt_ID
	 * @return
	 */
	public static BigDecimal getAdvanceByExt(int getEXME_CtaPac_ID, int EXME_CtaPacExt_ID){
		String sql = "SELECT sum(EXME_CtaPacPag.aplicado) from EXME_CtaPacPag left join EXME_CtaPacExt on EXME_CtaPacExt.EXME_CtaPacExt_ID = EXME_CtaPacPag.EXME_CtaPacExt_ID  where EXME_CtaPacExt.exme_ctapac_id = ? and EXME_CtaPacExt.EXME_CtaPacExt_ID = ? ";
		BigDecimal anticipo = DB.getSQLValueBDEx(null, sql, getEXME_CtaPac_ID, EXME_CtaPacExt_ID);
		if(anticipo == null){
			anticipo = BigDecimal.ZERO;
		}
		return anticipo;
	}
	
	/**  
	 * Devuelve el saldo disponible del paciente de todas las extenciones
	 * @param getEXME_CtaPac_ID
	 * @return
	 */
	public static BigDecimal getAdvance(int getEXME_CtaPac_ID){
//		StringBuilder sql = new StringBuilder("select ( ");
//		sql.append("select coalesce(sum(coalesce(payamt,0))) from c_payment where exme_ctapac_id = ? ");
//		sql.append(") - ( ");
//		sql.append("select coalesce(sum(coalesce(amount,0)),0) from C_AllocationLine where C_Allocationhdr_ID = (select C_Allocationhdr_ID from C_Allocationhdr where exme_ctapac_id = ? ) ");
//		sql.append(") as disponible");
		StringBuilder sql = new StringBuilder("SELECT sum(coalesce(EXME_Anticipo.total,0)) ");
		sql.append("FROM EXME_CtaPacExt ");
		sql.append("INNER JOIN EXME_Anticipo ON EXME_CtaPacExt.EXME_CtaPacExt_ID = EXME_Anticipo.EXME_CtaPacExt_ID ");
		sql.append("WHERE EXME_CtaPacExt.exme_ctapac_id = ? and c_invoice_id is null; "); 
		BigDecimal anticipo = DB.getSQLValueBDEx(null, sql.toString(), getEXME_CtaPac_ID);
		if(anticipo == null){
			anticipo = BigDecimal.ZERO;
		}
		return anticipo;
	}
}
