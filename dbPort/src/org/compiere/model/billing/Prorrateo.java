package org.compiere.model.billing;

import java.math.BigDecimal;

import org.compiere.model.I_C_Tax;
import org.compiere.model.MCtaPacDet;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Prorratear una cantidad entre varias lineas
 * @author twry
 *
 */
public class Prorrateo {
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(Prorrateo.class);
	/*** Descuento total */
	public BigDecimal ttDescuento = Env.ZERO;

	/**
	 * Obtener el precio de la linea por unidad
	 * @param montoAProrratear
	 * @param porcentAProrratear
	 * @param antesImp
	 * @param baseAProrratear
	 * @param total
	 * @param qty
	 * @param cTax
	 * @param idLinea
	 * @return BigDecimal 6 decimales
	 */
	public BigDecimal getPriceUnit( 
			final boolean antesImp,            final BigDecimal baseAProrratear, 
			final BigDecimal total,            final BigDecimal qty, 
			final I_C_Tax cTax,                final int idLinea, final boolean isDiscount ){
				
		// Monto despues de prorrateo (nuevoMonto, descuento)
		final BigDecimal nuevoMonto = getNewAmount(baseAProrratear, total, ttDescuento, isDiscount);
		
		BigDecimal porUnidad = nuevoMonto;
		if(nuevoMonto.compareTo(Env.ZERO)>0){
			// Precio por unidad
			porUnidad = nuevoMonto.divide(qty, MCtaPacDet.REDONDEO6_Calculos, BigDecimal.ROUND_HALF_UP);
			// El impuesto 43.0376
			if(!antesImp){
				// Como incluye impuestos hay que quitar esa parte
				final BigDecimal multiplier = (cTax.getRate().divide(Env.ONEHUNDRED, MCtaPacDet.REDONDEO6_Calculos, BigDecimal.ROUND_HALF_UP)).add(Env.ONE);		
				porUnidad = porUnidad.divide(multiplier, MCtaPacDet.REDONDEO6_Calculos, BigDecimal.ROUND_HALF_UP); 
			}
		} else {
			slog.severe("El prorrateo de la linea: "+idLinea+" no ha sido correcto "+nuevoMonto);
		}
		return nuevoMonto;
	}
	
	/**
	 * Calculo del monto ya prorrateado
	 * @param baseAProrratear
	 * @param total
	 * @param montoAProrratear
	 * @param porcentAProrratear
	 * @return
	 */
	private BigDecimal getNewAmount(final BigDecimal baseAProrratear, final BigDecimal total,
			final BigDecimal montoAProrratear, final boolean isDiscount){
		// Monto despues de prorrateo
		BigDecimal nuevoMonto = Env.ZERO;
		BigDecimal descuento  = Env.ZERO;
		if(total.compareTo(Env.ZERO)!=0){//5107.20   56900.69000000 284503.45
			descuento = (baseAProrratear.multiply(montoAProrratear)).divide(total, MCtaPacDet.REDONDEO6_Calculos, BigDecimal.ROUND_HALF_UP);
			// Aplica para las facturas
			if(isDiscount){
				nuevoMonto = baseAProrratear.subtract(descuento);
			}
		}

		return nuevoMonto;
	}
	
	public BigDecimal getTtDescuento() {
		return ttDescuento;
	}
	public void setTtDescuento(final BigDecimal mBigDecimal) {
		ttDescuento = mBigDecimal;
	}
}

