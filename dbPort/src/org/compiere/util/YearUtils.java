package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;


public class YearUtils {

	/**
	 * Divide en un arrgelo en Años, Meses<br/>
	 * una cantidad de años representada por un decimal
	 * 
	 * @param years
	 *            Años representados decimalmente
	 * @return Arreglo que contiene la cantidad de años, meses
	 */
	public static int[] split(BigDecimal years) {
		int[] a = new int[2];

		BigDecimal[] arr = years.divideAndRemainder(BigDecimal.ONE);

		a[0] = (arr[0].intValue());

		if (arr[1].doubleValue() > 0) {
			BigDecimal tmp = arr[1].multiply(BigDecimal.valueOf(12));
			tmp = tmp.setScale(1, RoundingMode.UP);
			a[1] = tmp.intValue();
		} else {
			a[1] = 0;
		}

		return a;
	}

	/**
	 * Imprime en formato de años, meses</br> una cantidad de años representada por un número decimal
	 * 
	 * @param years
	 *            Años representados decimalmente
	 * @return Texto en formato de años, meses
	 */
	public static String toText(Properties ctx, BigDecimal years) {
		int[] a = YearUtils.split(years);

		Period period = new Period(a[0], a[1], 0, 0, 0, 0, 0, 0);

		PeriodFormatterBuilder builder = new PeriodFormatterBuilder();
		builder.appendYears();
		builder.appendSuffix(Constantes.SPACE + Utilerias.getAppMsg(ctx, "msj.anio"), Constantes.SPACE + Utilerias.getAppMsg(ctx, "msj.anios"));
		builder.appendSeparator(", ");
		builder.appendMonths();
		builder.appendSuffix(Constantes.SPACE + Utilerias.getAppMsg(ctx, "msj.mes"), Constantes.SPACE + Utilerias.getAppMsg(ctx, "msj.meses"));

		PeriodFormatter dhm = builder.toFormatter();

		return dhm.print(period);
	}

}
