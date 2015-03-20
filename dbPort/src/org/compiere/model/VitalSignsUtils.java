package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * Utilerias de Signos Vitales
 * 
 * @author odelarosa
 * 
 */
public class VitalSignsUtils {

	/**
	 * Obtener valor de base de datos
	 * 
	 * @param ctx
	 *            Contexto
	 * @param cUomId
	 *            Unidad de medida del signo vital
	 * @param value
	 *            Valor del Signos Vital
	 * @param stdPrecision
	 * @return
	 */
	public static BigDecimal getFromDB(Properties ctx, int cUomId, BigDecimal value, boolean stdPrecision) {
		BigDecimal retValue = getValue(ctx, cUomId, value, stdPrecision, 1);
		return retValue;
	}

	/**
	 * Obtener valor a guardar en base de datos
	 * 
	 * @param ctx
	 *            Contexto
	 * @param cUomId
	 *            Unidad de medida del signo vital
	 * @param value
	 *            Valor del signo vital
	 * @param stdPrecision
	 * @return
	 */
	public static BigDecimal getToDB(Properties ctx, int cUomId, BigDecimal value, boolean stdPrecision) {
		BigDecimal retValue = getValue(ctx, cUomId, value, stdPrecision, 2);
		return retValue;
	}

	/**
	 * Obtener el valor segun el orden
	 * 
	 * @param ctx
	 *            Contexto
	 * @param cUomId
	 *            Unidad de Medida
	 * @param value
	 *            Valor
	 * @param stdPrecision
	 * @param order
	 *            Orden, 1 para obtener de base, 2 para guardar en base
	 * @return
	 */
	private static BigDecimal getValue(Properties ctx, int cUomId, BigDecimal value, boolean stdPrecision, int order) {
		BigDecimal retValue = null;
		String userSystem = MUser.getSistMedicionUsuario(ctx);
		if (MUOM.SISTEMAMEDICION_EnglishSystem.equals(userSystem)) {
			MUOM uom = new MUOM(ctx, cUomId, null);
			if (MUOM.SISTEMAMEDICION_None.equals(uom.getSistemaMedicion())) {
				retValue = value;
			} else {
				int id = MUOMConversion.getCUOMToID(ctx, cUomId, MUOM.SISTEMAMEDICION_MetricSystem);
				if (id > 0) {
					if (order == 1) {
						retValue = MUOMConversion.convert(id, cUomId, value, stdPrecision);
					} else {
						retValue = MUOMConversion.convert(cUomId, id, value, stdPrecision);
					}
				} else {
					retValue = value;
				}
			}
		} else {
			retValue = value;
		}
		return retValue;
	}

	/**
	 * Revisar si el signo vital esta configurado
	 * 
	 * @param ctx
	 *            Contexto
	 * @param cUomId
	 *            Unidad de Medida
	 * @return
	 */
	public static boolean isConfigured(Properties ctx, int cUomId) {
		boolean retValue = true;
		if (MUOM.SISTEMAMEDICION_EnglishSystem.equals(MUser.getSistMedicionUsuario(ctx))) {
			MUOM uom = new MUOM(ctx, cUomId, null);
			if (!MUOM.SISTEMAMEDICION_None.equals(uom.getSistemaMedicion())) {
				int id = 0;
				if (MUOM.SISTEMAMEDICION_MetricSystem.equals(uom.getSistemaMedicion())) {
					id = MUOMConversion.getCUOMToID(ctx, cUomId, MUOM.SISTEMAMEDICION_EnglishSystem);
				} else {
					id = MUOMConversion.getCUOMToID(ctx, cUomId, MUOM.SISTEMAMEDICION_MetricSystem);
				}
				if (id <= 0) {
					retValue = false;
				}
			}
		}

		return retValue;
	}

	public static int getUnit(Properties ctx, int cUomId) {
		int retValue = 0;
		if (MUOM.SISTEMAMEDICION_EnglishSystem.equals(MUser.getSistMedicionUsuario(ctx))) {
			MUOM uom = new MUOM(ctx, cUomId, null);
			if (!MUOM.SISTEMAMEDICION_None.equals(uom.getSistemaMedicion())) {
				if (MUOM.SISTEMAMEDICION_MetricSystem.equals(uom.getSistemaMedicion())) {
					retValue = MUOMConversion.getCUOMToID(ctx, cUomId, MUOM.SISTEMAMEDICION_EnglishSystem);
				} else {
					retValue = MUOMConversion.getCUOMToID(ctx, cUomId, MUOM.SISTEMAMEDICION_MetricSystem);
				}
			}
		}

		return retValue;
	}
	
	// Lama
	private Properties ctx;
	// unidad de medida para las conversiones
	private MUOM uomTo = null;

	public VitalSignsUtils(Properties ctx, int cUOMId) {
		this.ctx = ctx;
		// si el sist med del usuario es ingles
		if (MUser.convertirUnidades(ctx) && cUOMId > 0) {
			// convertimos el valor minimo y maximo correspondiente
			// unidad del valor
			final MUOM mUOM = new MUOM(ctx, cUOMId, null);
			// obtenemos la unidad de medida correspondiente al sistema de medicion del
			// usuario
			// si la unidad esta en metrico
			if (MUOM.SISTEMAMEDICION_MetricSystem.equals(mUOM.getSistemaMedicion())) {
				// uom a convertir
				uomTo = new MUOM(ctx, MUOMConversion.getCUOMToID(ctx, cUOMId,
					MUOM.SISTEMAMEDICION_EnglishSystem), null);
			} else if(MUOM.SISTEMAMEDICION_EnglishSystem.equals(mUOM.getSistemaMedicion())) {
				uomTo = mUOM;
			}
		}
	}

	public BigDecimal convertFromDB(BigDecimal value) {
		return uomTo == null ? null : getFromDB(ctx, uomTo.get_ID(), value, true);
	}

	public void setUomTo(MUOM uomTo) {
		this.uomTo = uomTo;
	}
	public MUOM getUomTo() {
		return uomTo;
	}
}
