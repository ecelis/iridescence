package org.compiere.model.billing;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.compiere.model.MCtaPacDet;
import org.compiere.model.BeanConcepts.BeanAmts;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.util.Env;

/**
 * Prorrate del descuento global
 * @author twry
 *
 */
public class Overalldiscount {
	
	/** Calculo */
	private Prorrateo mProrrateo = new Prorrateo();

	/**
	 * Prorratear el descuento
	 * @param beanDescuento Datos del descuento
	 * @return true el calculo se realizo
	 */
	public BigDecimal prorate(final BeanAmts beanDescuento
			, final BigDecimal totalVta
			, final List<? extends MCtaPacDet> lineas
			, final int numExt
			, final String trxName) {
		
		BigDecimal desctoGlobalAmt = Env.ZERO;
		if(lineas.isEmpty()){
			return desctoGlobalAmt;
			
		} else {
			BigDecimal totalLines = Env.ZERO;
			
			BigDecimal factorPerc = beanDescuento.isEsPorcentaje() ? beanDescuento.getMontoCaptura()
				:(beanDescuento.getMontoCaptura().multiply(Env.ONEHUNDRED)).divide(totalVta, 10, BigDecimal.ROUND_HALF_UP);
			factorPerc = factorPerc.divide(Env.ONEHUNDRED, 10, BigDecimal.ROUND_HALF_UP);
			// 0.8733098443
			final BigDecimal montoAProrratear = beanDescuento.isEsPorcentaje() ? Env.ZERO: beanDescuento.getMontoCaptura();
			final BigDecimal porcentAProrratear = beanDescuento.isEsPorcentaje() ? beanDescuento.getMontoCaptura() : Env.ZERO;
			final BigDecimal total = totalVta;//284,503.45 //getCoaseDeducDesc().getBean().getTotalVta();

			// Linea de ajuste
			MCtaPacDet ctaPacDetAdj = null;
			BigDecimal maxAmt = Env.ZERO;

			// Importe de descuento
			mProrrateo.setTtDescuento(
					porcentAProrratear.compareTo(Env.ZERO)>0
					? total.multiply(porcentAProrratear.divide(Env.ONEHUNDRED, MCtaPacDet.REDONDEO2_Usuario, BigDecimal.ROUND_HALF_UP))
							: montoAProrratear);

			// Importes sobre totales
			BigDecimal descCalculado = mProrrateo.getTtDescuento();//870
			BigDecimal subtotal = total.subtract(mProrrateo.getTtDescuento());//126.21
			boolean esParticular = lineas.get(0).getExtension().getBpartner().isParticular();

			// Prorrateo por linea
			for (final MCtaPacDet bean : lineas) {
				if (bean.getProduct().isProduct() || esParticular) {
					final int exts = Integer.parseInt(bean.getExtLVB().getValue());
					if(numExt<0 || numExt==exts) {
						BigDecimal newPrices = mProrrateo.getPriceUnit(
								true, 
								bean.getLineGrossAmt(),//
								total, bean.getQtyDelivered(), 
								bean.getMTax(),
								bean.getEXME_CtaPacDet_ID(), 
								true);//57.644021


						// Dos decimales
						if(newPrices.scale() > MCtaPacDet.REDONDEO2_Usuario){
							newPrices = newPrices.setScale(MCtaPacDet.REDONDEO2_Usuario,BigDecimal.ROUND_HALF_UP);
						}

						// Precio con Convenio y descuento global
						bean.setLineNetAmt(newPrices);
						BigDecimal precioDespuesDesc = bean.getLineNetAmt().divide(bean.getQtyDelivered(), MCtaPacDet.REDONDEO2_Usuario, BigDecimal.ROUND_HALF_UP);
						bean.setPriceActualInv(precioDespuesDesc);// por unidad y sin impuesto
						// Descuento 
						bean.setDiscountFam(bean.getOverallDiscount());
						
						if(esParticular && !bean.getProduct().isProduct()){
							// Facturación directa 
							if(numExt==exts){
//								// Impuesto original
//								final BigDecimal impOld = bean.getTaxAmt();//.add(bean.getDiscountTaxAmt());
//								// Aplicar el factor el viejo impuesto
//								//final BigDecimal imp = factorPerc.multiply(impOld);
//								// Impuesto correspondiente al descuento global
//								//bean.setDiscountTaxAmt(imp);
//								// Nuevo impuesto
//								bean.setTaxAmt(impOld.subtract(bean.getDiscountTaxAmt(true)));	
								
								// Impuesto original
								final BigDecimal impOld = bean.getTaxAmt().add(bean.getDiscountTaxAmt());
								// Aplicar el factor el viejo impuesto
								final BigDecimal imp = factorPerc.multiply(impOld);
								// Impuesto correspondiente al descuento global
								bean.setDiscountTaxAmt(imp);
								// Nuevo impuesto
								bean.setTaxAmt(impOld.subtract(imp));	
								
							} else {
								final BigDecimal imp = factorPerc.multiply(bean.getTaxAmt());
								bean.setDiscountTaxAmt(imp);
								bean.setTaxAmt(bean.getTaxAmt().subtract(imp));		
								
							}
						} else {
							bean.setDiscountTaxAmt(bean.setDiscTaxAmt());//bean.setTaxAmt();
							bean.setTaxAmt(false, bean.getLineNetAmt());
						}
						
						if(bean.getEXME_CtaPacDet_ID()>0 ){
							if(!bean.save(trxName)){
								desctoGlobalAmt = Env.ZERO;
								break;
							}
						} 

						if((maxAmt.compareTo(Env.ZERO)==0 || maxAmt.compareTo(bean.getLineNetAmt())<0)
							&& bean.getProduct().isProduct()){
								maxAmt = bean.getLineNetAmt();
								ctaPacDetAdj = bean;
						}

						totalLines = totalLines.add(bean.getLineNetAmt());
						desctoGlobalAmt = desctoGlobalAmt.add(bean.getDiscountFam());
					}
				}
			}// fin for

			return ajustarLinea(ctaPacDetAdj,descCalculado,desctoGlobalAmt, subtotal, totalLines, numExt, trxName);
		}
	}


	public BigDecimal ajustarLinea(final MCtaPacDet ctaPacDet, BigDecimal descCalculado
			, BigDecimal desctoGlobalAmt,BigDecimal subtotal, BigDecimal totalLines, final int numExt, final String trxName){

		if(ctaPacDet==null){
			return desctoGlobalAmt;
		} else {

			// Hacer ajuste por desbalanceo del redondeo
			if(descCalculado.compareTo(desctoGlobalAmt)!=0){
				BigDecimal dif    = descCalculado.subtract(desctoGlobalAmt); 
				BigDecimal difQty = dif.divide(ctaPacDet.getQtyDelivered(), MCtaPacDet.REDONDEO6_Calculos, BigDecimal.ROUND_HALF_UP);

				ctaPacDet.setPriceActualInv(ctaPacDet.getPriceActualInv().add(difQty.multiply(new BigDecimal(-1))));
				ctaPacDet.setDiscountFam(ctaPacDet.getOverallDiscount());
				ctaPacDet.setDiscountTaxAmt(ctaPacDet.setDiscTaxAmt());// getOverallDiscount();
				ctaPacDet.setTaxAmt(false, ctaPacDet.getLineNetAmt());
			}		

			// Ajustar subtotal en caso de ser necesario
			if(subtotal.compareTo(totalLines)!=0){
				BigDecimal difAMT = subtotal.subtract(totalLines); 
				ctaPacDet.setLineNetAmt(ctaPacDet.getLineNetAmt().add(difAMT));
				ctaPacDet.setTaxAmt(false, ctaPacDet.getLineNetAmt());
			}

			// Posiblemente facturación directa no tiene ID
			if(ctaPacDet.getEXME_CtaPacDet_ID()>0){
				if(!ctaPacDet.save(trxName)){
					desctoGlobalAmt = Env.ZERO;
				}
			}//35778.00 -> 1431.310392

			// Prorrateo por linea
			BigDecimal monto1 = Env.ZERO;
			BigDecimal monto2 = Env.ZERO; 
			BigDecimal sumdesctoGlobalAmt = Env.ZERO;
			BigDecimal otherDiscount = Env.ZERO;
			BigDecimal sumdesctoGlobalTaxAmt = Env.ZERO;
			BigDecimal sumtotalLines = Env.ZERO;
			BigDecimal impuesto = Env.ZERO;
			BigDecimal baseImpuesto16All = Env.ZERO;
			BigDecimal impuesto16All = Env.ZERO;

			MEXMECtaPacExt mCtaPacExt = new MEXMECtaPacExt(ctaPacDet.getCtx(),ctaPacDet.getEXME_CtaPacExt_ID(), trxName);
			List<MCtaPacDet> linesReload = null;
			try {
				linesReload = mCtaPacExt.getLstCargos(MEXMECtaPacExt.condicionCargos());
			} catch (SQLException e) {
				e.printStackTrace();
			}//desc

			if(linesReload!=null && !linesReload.isEmpty()){
				for (final MCtaPacDet mReloadCtaPacDet : linesReload) {
					if (mReloadCtaPacDet.getProduct().isProduct()) {
						final int exts = Integer.parseInt(mReloadCtaPacDet.getExtLVB().getValue());
						if(numExt<0 || numExt==exts) {

							sumtotalLines = sumtotalLines.add(mReloadCtaPacDet.getLineNetAmt());

							otherDiscount = otherDiscount.add(mReloadCtaPacDet.getOverallDiscount());
							sumdesctoGlobalAmt = sumdesctoGlobalAmt.add(mReloadCtaPacDet.getDiscountFam());

							sumdesctoGlobalTaxAmt = sumdesctoGlobalTaxAmt.add(mReloadCtaPacDet.getDiscountTaxAmt(true));
							impuesto = impuesto.add(mReloadCtaPacDet.getTaxAmt());

							if(mReloadCtaPacDet.getMTax().getRate().compareTo(new BigDecimal(15))>0){
								baseImpuesto16All=baseImpuesto16All.add(mReloadCtaPacDet.getLineNetAmt());
								impuesto16All=impuesto16All.add(mReloadCtaPacDet.getTaxAmt());
							}

							// La suma de todos los cargos con convenio, desc global, cccmd
							if(ctaPacDet.getEXME_CtaPacDet_ID() == mReloadCtaPacDet.getEXME_CtaPacDet_ID()){
								monto1 = monto1.add(mReloadCtaPacDet.getLineNetAmt());//
								monto2 = monto2.add(mReloadCtaPacDet.getTaxAmt());//
							}
						}
					}
				}
			}
			return desctoGlobalAmt.compareTo(Env.ZERO)==0?Env.ZERO:descCalculado;
		}
	}

	/**
	 * Localiza y Eliminar la linea de descuento
	 * @param mExt: Extension
	 * @return
	 * @throws SQLException
	 */
	public boolean rollbackDiscount(MEXMECtaPacExt mExt) throws SQLException {
		int count=0;
		mExt.cargarLineasDeExtension(true, true);
		if(mExt.getLineasDeExtension()!=null){
			count=mExt.deleteDiscount(Arrays.asList(mExt.getLineasDeExtension()));
		}
		return count>0;
	}
}
