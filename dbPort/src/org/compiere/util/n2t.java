package org.compiere.util;

/**
 *  */
public class n2t {

	private int						flag;

	public final static int			DIEZ_MIL		= 10000;
	public final static int			CIEN_MIL		= 100000;
	public final static int			MILLON			= 1000000;
	public final static int			DIEZ_MILLONES	= 10000000;

	public static final String[]	numNames		= { "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez ",
		"once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ", "diecisiete ", "dieciocho ", "diecinueve " };

	private static final String[]	tensNames		= { "", "", "veinte ", "treinta ", "cuarenta ", "cincuenta ", "sesenta ", "setenta ", "ochenta ",
		"noventa "									};

	private static final String[]	numName100		= { "", "cien ", "doscientos ", "trescientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
		"setecientos ", "ochocientos ", "novecientos " };

	public n2t() {
		// numero = 0;
		flag = 0;
	}

	/** numeros del 1 - 9 */
	private String unidad(final int numero) {
		String num;
		if (numero == 1 && flag != 0) {
			num = "un";
		} else if (numero < 20) {
			num = numNames[numero];
		} else {
			num = "";
		}
		return num;
	}

	/** numeros del 10 - 99 */
	private String decena(final int numero) {
		final StringBuilder num_letra = new StringBuilder();
		if (numero < 20) {
			flag = flag == 0 && numero == 1 ? 1 : flag;
			num_letra.append(unidad(numero));
			flag = flag == 1 && numero == 1 ? 0 : flag;
		} else if (numero > 99) {
			num_letra.append(centena(numero));
		} else {
			final int res = numero % 10;
			if (numero > 20 && numero < 30) {
				num_letra.append("veinti").append(unidad(res));
			} else {
				num_letra.append(tensNames[numero / 10]);
				if (res > 0) {
					num_letra.append("y ").append(unidad(res));
				}
			}
		}
		return num_letra.toString();
	}

	/** numeros del 100 - 999 */
	private String centena(final int numero) {
		final StringBuilder num_letra = new StringBuilder();
		if (numero < 100) {
			num_letra.append(decena(numero));
		} else if (numero > 999) {
			num_letra.append(miles(numero));
		} else {
			final int res = numero % 100;
			if (numero > 100 && numero < 200) {
				num_letra.append("ciento ").append(decena(res));
			} else {
				num_letra.append(numName100[numero / 100]);
				if (res > 0) {
					num_letra.append(decena(res));
				}
			}
		}
		return num_letra.toString();
	}

	/** numeros del 1000 - 9999 */
	private String miles(final int numero) {
		final StringBuilder num_letra = new StringBuilder();
		if (numero < 1000) {
			num_letra.append(centena(numero));
		} else if (numero < DIEZ_MIL) {
			if (numero >= 2000) {
				flag = 1;
				num_letra.append(unidad(numero / 1000)).append(" ");
			}
			num_letra.append("mil ");
			num_letra.append(centena(numero % 1000));
		} else {
			num_letra.append(decmiles(numero));
		}
		return num_letra.toString();
	}

	/** numeros del 10000 - 99999 */
	private String decmiles(final int numero) {
		final StringBuilder num_letra = new StringBuilder();
		if (numero < DIEZ_MIL) {
			num_letra.append(miles(numero));
		} else if (numero == DIEZ_MIL) {
			num_letra.append("diez mil");
		} else if (numero < CIEN_MIL) {
			flag = 1;
			num_letra.append(decena(numero / 1000));
			num_letra.append(" mil ");
			flag = 0;
			if (numero < CIEN_MIL * 2) {
				num_letra.append(centena(numero % 1000));
			} else {
				num_letra.append(miles(numero % 1000));
			}
		} else {
			num_letra.append(cienmiles(numero));
		}
		return num_letra.toString();
	}

	/** numeros del 100000 - 999999 */
	private String cienmiles(final int numero) {
		final StringBuilder num_letra = new StringBuilder();
		if (numero < CIEN_MIL) {
			num_letra.append(decmiles(numero));
		} else if (numero == CIEN_MIL) {
			num_letra.append("cien mil");
		} else if (numero < MILLON) {
			flag = 1;
			num_letra.append(centena(numero / 1000)).append(" mil ");
			flag = 0;
			num_letra.append(centena(numero % 1000));
		} else {
			num_letra.append(millon(numero));
		}
		return num_letra.toString();
	}

	/** numeros del 1000 000 - 9 999 999 */
	private String millon(final int numero) {
		final StringBuilder num_letra = new StringBuilder();
		if (numero < MILLON) {
			num_letra.append(cienmiles(numero));
		} else if (numero < (MILLON * 2)) {
			flag = 1;
			num_letra.append("Un millon ").append(cienmiles(numero % MILLON));
		} else if (numero < DIEZ_MILLONES) {
			flag = 1;
			num_letra.append(unidad(numero / MILLON)).append(" millones ");
			flag = 0;
			num_letra.append(cienmiles(numero % MILLON));
		} else {
			num_letra.append(decmillon(numero));
		}
		return num_letra.toString();
	}

	private String decmillon(long numero) {
		final StringBuilder num_letra = new StringBuilder();
		if (numero < DIEZ_MILLONES) {
			num_letra.append(millon((int) numero));
		} else if (numero == DIEZ_MILLONES) {
			num_letra.append("diez millones");
		} else if (numero < (DIEZ_MILLONES * 2)) {
			flag = 1;
			num_letra.append(decena((int) (numero / MILLON))).append("millones ");
			flag = 0;
			num_letra.append(cienmiles((int) (numero % MILLON)));
		} else {
			long digitos = 1 + (long) Math.log10(numero);
			if (digitos <= 12) {
				flag = 1;
				num_letra.append(centena((int) (numero / MILLON))).append(" millones ");
				flag = 0;
				num_letra.append(decmillon((numero % MILLON)));
			} else if (digitos <= 18) {
				// longitud
				// 11 123 615 900 070 502 (17 digitos) billones
				// once mil ciento veintitres billones
				// seiscientos quince mil novecientos millones
				// setenta mil quinientos dos.
				flag = 1;
				int value = (int) (numero / 1000000000000L);
				num_letra.append(millon(value)).append(value == 1 ? " billon " : " billones ");
				flag = 0;
				num_letra.append(decmillon((numero % 1000000000000L)));
			}
		}
		return num_letra.toString();
	}

	public String convert(long numero) {
		final StringBuilder num_letra = new StringBuilder();
		if (numero < 0) {
			num_letra.append("MENOS");
			numero *= -1;
		}else if (numero == 0){
			num_letra.append(Utilerias.getLabel("msj.Cero", null));
			numero *= -1;
		}
		return num_letra.append(decmillon(numero)).toString();
	}

	public static String convertirLetras(final long numero) {
		return new n2t().convert(numero);
	}

}
