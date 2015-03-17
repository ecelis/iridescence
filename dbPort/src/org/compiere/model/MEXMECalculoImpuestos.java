package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.util.Env;

public class MEXMECalculoImpuestos {
//
//	/**
//	 * 
//	 * @param ctx
//	 * @param EXME_CtaPacExt_ID
//	 * @param trxName
//	 * @return
//	 * @throws Exception 
//	 */
//	public static String extension(Properties ctx, int EXME_CtaPacExt_ID,
//			String trxName) throws Exception{
//		String error = null;
//		
//		MEXMECtaPacExt extension = new MEXMECtaPacExt(ctx, EXME_CtaPacExt_ID, trxName);
//		
//		//Las lineas de la extension
//		MCtaPacDet[] lstExtension = extension.getCargosDeLaExtension(
//				" AND EXME_CtaPacDet.QtyPendiente > 0  ");
//		
//		//Barremos las lineas
//		for (int i = 0; i < lstExtension.length; i++) {
//			MCtaPacDet cargo = (MCtaPacDet) lstExtension[i];
//
//			MEXMETax m_tax = null;
//			//A las lineas de coaseguro y deducible no se les calcula el impuesto relacionado
//			if(!( (cargo.getTipoLinea().equalsIgnoreCase(MCtaPacDet.TIPOLINEA_Coaseguro) 
//					|| cargo.getTipoLinea().equalsIgnoreCase(MCtaPacDet.TIPOLINEA_Deducible) 
//					|| cargo.getTipoLinea().equalsIgnoreCase(MCtaPacDet.TIPOLINEA_CoaseguroMedico)
//					|| cargo.getTipoLinea().equalsIgnoreCase(MCtaPacDet.TIPOLINEA_Copago))
//					&& cargo.getLineNetAmt().compareTo(Env.ZERO)<= 0 ) 		){
//				
//				if(cargo.getM_Product_ID()>0 )
//					m_tax = MEXMECalculoImpuestos.getTaxAmtProduct(ctx, cargo.getM_Product_ID(), cargo.getLineNetAmt(), trxName);
//				else
//					m_tax = MEXMECalculoImpuestos.getTaxAmtCharge(ctx, cargo.getC_Charge_ID(), cargo.getLineNetAmt(), trxName);
//
//				BigDecimal taxAmt = Env.ZERO;
//				if(m_tax!=null){
//					cargo.setC_Tax_ID(m_tax.getC_Tax_ID());
//					taxAmt = m_tax.getAmount();
//				}
//
//				cargo.setTaxAmt(taxAmt);
//
//				if(!cargo.save(trxName)){
//					error="No se logro recalcular el impuesto";
//					break;
//				}
//			}
//		}
//		
//		
//		extension.cargarLineasDeExtension(true);
//		if (!extension.calcularTotales(trxName)){
//			error="No se logro recalcular el impuesto";
//		}
//		
//		if(!extension.save(trxName)){
//			error="No se logro recalcular el impuesto";
//		}
//		return error;
//	}
//	
//	
//	/**
//	 * 
//	 * @param ctx
//	 * @param EXME_CtaPacExt_ID
//	 * @param trxName
//	 * @return
//	 * @throws Exception 
//	 *
//	public ActionErrors impuestosExtension(ActionErrors errors,
//			MEXMECtaPacExt extension, MCtaPacDet[] lstExtension , 
//			MEXMEMejoras mejoras) 
//	throws Exception{
//
//		MEXMETax m_tax = null;
//		int TaxExempt_ID = 0;
//
//		MEXMEBPartner socio = new MEXMEBPartner(extension.getCtx(), 
//				extension.getC_BPartner_ID(), extension.get_TrxName());
//		if (socio.isTaxExempt())
//			TaxExempt_ID = MEXMETax.getExemptTaxID(extension.getCtx(), 
//					extension.get_TrxName());
//
//		//Barremos las l�neas
//		for (int i = 0; i < lstExtension.length; i++) {
//
//			m_tax = null;
//			//A las l�neas de coaseguro y deducible no se les calcula el impuesto relacionado
//			if(!( (lstExtension[i].getTipoLinea().equalsIgnoreCase(MCtaPacDet.TIPOLINEA_Coaseguro) 
//					|| lstExtension[i].getTipoLinea().equalsIgnoreCase(MCtaPacDet.TIPOLINEA_Deducible) )
//					&& lstExtension[i].getLineNetAmt().compareTo(Env.ZERO)<= 0 ) 		){
//
//				// Sync Tax si es TaxExempt Si esta excento y hay un
//				// impuesto de excento
//				if (TaxExempt_ID>0)
//					// cambiamos el id el impuesto
//					lstExtension[i].setC_Tax_ID(TaxExempt_ID);
//
//				//Buscamos otro impuesto segun configuracion y vigencia
//				if(mejoras!=null && mejoras.isCalcImpFact()){
//					if(lstExtension[i].getM_Product_ID()>0 )
//						m_tax = MEXMECalculoImpuestos.getTaxAmtProduct(extension.getCtx(), 
//								lstExtension[i].getM_Product_ID(), 
//								lstExtension[i].getLineNetAmt(), 
//								extension.get_TrxName());
//					else
//						m_tax = MEXMECalculoImpuestos.getTaxAmtCharge(extension.getCtx(), 
//								lstExtension[i].getC_Charge_ID(), 
//								lstExtension[i].getLineNetAmt(), 
//								extension.get_TrxName());
//				}else{
//					m_tax = new MEXMETax(extension.getCtx(), 
//							lstExtension[i].getC_Tax_ID(), 
//							extension.get_TrxName());
//
//					m_tax.setAmount(m_tax.calculateTax(lstExtension[i].getLineNetAmt(), false, 6));
//				}
//
//
//				BigDecimal taxAmt = Env.ZERO;
//				if(m_tax!=null){
//					lstExtension[i].setC_Tax_ID(m_tax.getC_Tax_ID());
//					taxAmt = m_tax.getAmount();
//				}
//
//				lstExtension[i].setTaxAmt(taxAmt);
//
//				if(!lstExtension[i].save(extension.get_TrxName())){
//					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
//					"error.getImpuesto"));
//					break;
//				}
//			}
//		}//Fin for
//		return errors;
//	}
//	
//	/**
//	 * 
//	 * @param ctx
//	 * @param EXME_CtaPacExt_ID
//	 * @param trxName
//	 * @return
//	 * @throws Exception 
//	 */
//	public static String factura(MEXMEInvoice factura, BigDecimal grandTotalOriginal) 
//	throws Exception{
//		String error = null;
//		
//		//Las lineas de la extension
//		MInvoiceLine[] lines = factura.getLines(true, false);
//		
//		BigDecimal totalAmt = Env.ZERO; //lhernandez. para obtener el monto total.
//		BigDecimal totalAmtLine = Env.ZERO; //lhernandez. para obtener el monto por linea.
//		BigDecimal totalAmtTax = Env.ZERO;
//		
//		MConfigPre configPre = MConfigPre.get(factura.getCtx(), null);
//		boolean requierePagos = false;
//		
//		//Barremos las lineas
//		for (int i = 0; i < lines.length; i++) {
//			MInvoiceLine cargo = (MInvoiceLine) lines[i];
//
//			MEXMETax m_tax = null;
//
//
//			if(cargo.getM_Product_ID()==0 
//					|| ( cargo.getM_Product_ID()>0 
//							&& cargo.getLineNetAmt().compareTo(Env.ZERO)>0 
//							&& configPre.getDeducible_ID() !=cargo.getM_Product_ID()
//							&& configPre.getCoaseguro_ID() != cargo.getM_Product_ID()
//							&& configPre.getCoaseguroMed_ID() != cargo.getM_Product_ID()
//							&& configPre.getCoPago_ID() != cargo.getM_Product_ID())){
//
//				if(cargo.getM_Product_ID()>0){
//					m_tax = MEXMECalculoImpuestos.getTaxAmtProduct(factura.getCtx(), cargo.getM_Product_ID(), 
//							cargo.getLineNetAmt(), 	factura.get_TrxName() );
//				} else{//Los impuestos de los cargos deber�n ser exentos
//					m_tax = MEXMECalculoImpuestos.getTaxAmtCharge(factura.getCtx(), cargo.getC_Charge_ID(), cargo.getLineNetAmt(), factura.get_TrxName());
//				}
//
//				if(cargo.getC_Tax_ID()!=m_tax.getC_Tax_ID()){
//
//					BigDecimal taxAmt = Env.ZERO;
//					if(m_tax!=null){
//						cargo.setC_Tax_ID(m_tax.getC_Tax_ID());
//						taxAmt = m_tax.getAmount();
//					}
//
//					cargo.setTaxAmt(taxAmt);
//
//					totalAmtLine = totalAmtLine.add(cargo.getLineNetAmt());//lhernandez. sumar los totales sin impuesto.
//					totalAmtTax = totalAmtTax.add(taxAmt);
//					if(cargo.getM_Product_ID()>0)
//						cargo.setLineTotalAmt(cargo.getLineNetAmt().add(cargo.getTaxAmt()));
//
//					if(!cargo.save(factura.get_TrxName())){
//						error="No se logro recalcular el impuesto";
//						return error;
//					}
//
//					requierePagos = true;
//				}
//			}
//		}
//		
//		//Importe mas impuesto
//		totalAmt = totalAmtLine.add(totalAmtTax); //lhernandez. sumar el nuevo impuesto. 
//		 
//		//Comparamos el total original por el total de la factura con el nuevo impuesto
//		if(requierePagos){
//			if(totalAmt.compareTo(grandTotalOriginal)!=0){
//				factura.setAfecta_Caja(false);
//				factura.setProcessed(false);
//			}
//
//			if(!factura.save(factura.get_TrxName())){
//				error="No se logro recalcular el impuesto";
//			}
//		}
//		return error;
//	}
//	
//	/**
//	 * 
//	 * @param product
//	 * @return
//	 */
//	public static MEXMETax getTaxAmtCharge(Properties ctx, 
//			int C_Charge_ID, BigDecimal monto, String trxName){
//		MEXMETax tax  = null; 
//
//		try {
//			tax = MEXMECalculoImpuestos.getCalculoImp(ctx, 0, monto, C_Charge_ID,false,trxName);
//		} catch (Exception e) {
//			return null;
//		}
//		return tax;
//	}
//
//	/**
//	 * 
//	 * @param ctx
//	 * @param product
//	 * @param monto
//	 * @param fecha
//	 * @param trxName
//	 * @return
//	 * @throws Exception
//	 */
//	public static MEXMETax getTaxAmtProduct(Properties ctx,
//			int product, BigDecimal monto,  boolean valida,
//			String trxName){
//		MEXMETax tax = null;
//		try{
//			tax = MEXMECalculoImpuestos.getCalculoImp(ctx, 
//					product, monto, 0, valida,trxName);
//		}catch (Exception e) {
//			return null;
//		}
//
//		return tax;
//	}
//
//	public static MEXMETax getTaxAmtProduct(Properties ctx,
//			int product, BigDecimal monto,
//			String trxName){
//		MEXMETax tax = null;
//		try{
//			tax = MEXMECalculoImpuestos.getCalculoImp(ctx, 
//					product, monto, 0, false,trxName);
//		}catch (Exception e) {
//			return null;
//		}
//
//		return tax;
//	}
//
//
//	/**
//	 * 
//	 * @param ctx
//	 * @param taxCategory
//	 * @param product
//	 * @param monto
//	 * @param fecha
//	 * @param trxName
//	 * @return
//	 * @throws Exception 
//	 */
//	public static MEXMETax getCalculoImp(Properties ctx,
//			int product, BigDecimal monto, int charge, boolean valida,
//			String trxName) 
//	throws Exception{
//	
//		//String fecha = null;
//		MEXMETax tax= null;
//		
//		if(ctx==null)
//			return null;
//		
//		if(valida){
//			MConfigPre configPre = null;
//			if(product>0 && monto.compareTo(Env.ZERO)<=0){
//				configPre = MConfigPre.get(ctx, trxName);
//				if(configPre.getCoaseguro_ID()==product || configPre.getDeducible_ID()==product || configPre.getCoaseguroMed_ID() == product || configPre.getCoPago_ID() == product){
//					tax = MEXMETax.impuesto(ctx, " AND IsTaxExempt='Y' " , trxName);
//				}
//			}
//		}
//		
//		
//		//Cargo
//		if(charge>0)
//			tax =  MEXMETax.getImpuestoCargo(ctx, charge, trxName);
//		
//		//Producto
//		if(product>0 && tax == null){
//			tax = MEXMETax.getImpuestoProducto(ctx, product, trxName);    
//
//			//Categor�a de producto
//			if( tax == null){
//				MProduct producto = new MProduct(ctx, product, trxName);
//				tax = MEXMETax.getTasaCategoriaProd(ctx, producto.getC_TaxCategory_ID(), trxName);
//			}
//		}
//		
//		//Por defecto
//		if(tax == null)
//			tax = MEXMETax.getDefaultTaxID(ctx, trxName);
//
//		if(tax != null && monto!=null && monto.compareTo(Env.ZERO)!=0){
//			BigDecimal impuesto = tax.calculateTax (monto, false, 6);
//			
//			tax.setAmount(impuesto);
//		}
//		return tax;
//	}

}
