package com.ecaresoft.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MEXMEPaciente;

/**
 * Clase que permite generar los valores del CURP y el RFC <b>SIN</b> homoclaves.
 * @author Pedro Mendoza
 *
 */
public class CURP_RFC {
	private static final String U = "U";
	private static final String O = "O";
	private static final String I = "I";
	private static final String E = "E";
	private static final String A = "A";
	private static final String X = "X";
	private static final String ASTERISCO = "*";
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombre;
	private String segundoNombre;
	private String sexo;
	private Date fechaNac = null;
	private String estadoNac;
	private String estadoNacValue = "**";
	private String letra1AP = ASTERISCO; // Primer letra apellido paterno
	private String vocal1AP = ASTERISCO; // Primer vocal apellido paterno
	private String consonante1AP = ASTERISCO; // Primera consontante interna del
												// apellido paterno
	private String letra1AM = ASTERISCO; // Primer letra apellido materno
	private String consonante1AM = ASTERISCO; // Primera consontante interna del
												// apellido materno
	private String letra1N = ASTERISCO; // Primer letra nombre
	private String consonante1N = ASTERISCO; // Primera consontante interna del
												// nombre
	private String consonante1SN = ASTERISCO; // Primera consontante interna del
												// segundo nombre
	private String fechaValue = "******";
	private String inicialGenerico = "*********";
	private String sexoValue = ASTERISCO;
	private String currentCurp;
	private boolean curpChange;

	private static final List<String> ALTISONANTES = getAltisonantes();

	private static final String[] nombreNoValido = { "maria ", "mar\u00ECa ",
			"mar\u00EDa ", "mar\u00EEa ", "mar\u00EFa ", "ma. ", "ma ",
			"jose ", "jos\u00E8 ", "jos\u00E9 ", "jos\u00EA ", "jos\u00EB ",
			"j. ", "j " };

	public static HashMap<String, String> ESTADOS = getEstados();

	/**
	 * Obtiene una arreglo con las palabras altisonantes que podrian dar creando
	 * los primero 4 digitos del CURP o RFC
	 *
	 * @return
	 */
	private static List<String> getAltisonantes() {
		final String[] altisonantes = { "BACA", "BAKA", "BUEI", "BUEY", "CACA",
				"CACO", "CAGA", "CAGO", "CAKA", "CAKO", "COGE", "COGI", "COJA",
				"COJE", "COJI", "COJO", "COLA", "CULO", "FALO", "FETO", "GETA",
				"GUEI", "GUEY", "JETA", "JOTO", "KACA", "KACO", "KAGA", "KAGO",
				"KAKA", "KAKO", "KOGE", "KOGI", "KOJA", "KOJE", "KOJI", "KOJO",
				"KOLA", "KULO", "LILO", "LOCA", "LOCO", "LOKA", "LOKO", "MAME",
				"MAMO", "MEAR", "MEAS", "MEON", "MIAR", "MION", "MOCO", "MOKO",
				"MULA", "MULO", "NACA", "NACO", "PEDA", "PEDO", "PENE", "PIPI",
				"PITO", "POPO", "PUTA", "PUTO", "QULO", "RATA", "ROBA", "ROBE",
				"ROBO", "RUIN", "SENO", "TETA", "VACA", "VAGA", "VAGO", "VAKA",
				"VUEI", "VUEY", "WUEI", "WUEY" };

		List<String> alt = new ArrayList<String>();
		for (String altisonante : altisonantes) {
			alt.add(altisonante);
		}
		return alt;
	}

	/**
	 * Crea una lista con las claves a dos digitos utilizados para el curp
	 *
	 * @return
	 */
	private static HashMap<String, String> getEstados() {
		HashMap<String, String> estados = new HashMap<String, String>();
		estados.put("AGUASCALIENTES", "AS");
		estados.put("MORELOS", "MS");
		estados.put("BAJA CALIFORNIA", "BC");
		estados.put("NAYARIT", "NT");
		estados.put("BAJA CALIFORNIA SUR", "BS");
		estados.put("NUEVO LEON", "NL");
		estados.put("CAMPECHE", "CC");
		estados.put("OAXACA", "OC");
		estados.put("CHIAPAS", "CS");
		estados.put("PUEBLA", "PL");
		estados.put("CHIHUAHUA", "CH");
		estados.put("QUERETARO", "QT");
		estados.put("COAHUILA", "CL");
		estados.put("QUINTANA ROO", "QR");
		estados.put("COLIMA", "CM");
		estados.put("SAN LUIS POTOSI", "SP");
		estados.put("DISTRITO FEDERAL", "DF");
		estados.put("SINALOA", "SL");
		estados.put("DURANGO", "DG");
		estados.put("SONORA", "SR");
		estados.put("GUANAJUATO", "GT");
		estados.put("TABASCO", "TC");
		estados.put("GUERRERO", "GR");
		estados.put("TAMAULIPAS", "TS");
		estados.put("HIDALGO", "HG");
		estados.put("TLAXCALA", "TL");
		estados.put("JALISCO", "JC");
		estados.put("VERACRUZ", "VZ");
		estados.put("ESTADO DE MEXICO", "MC");
		estados.put("MEXICO", "MC");
		estados.put("YUCATAN", "YN");
		estados.put("MICHOACAN", "MN");
		estados.put("ZACATECAS", "ZS");
		return estados;
	}

	/**
	 * Cambiar todas las variables a su valor por default
	 */
	public void clear() {
		apellidoPaterno = null;
		apellidoMaterno = null;
		nombre = null;
		segundoNombre = null;
		sexo = null;
		fechaNac = null;
		estadoNac = null;
		estadoNacValue = "**";
		letra1AP = ASTERISCO;
		vocal1AP = ASTERISCO;
		consonante1AP = ASTERISCO;
		letra1AM = ASTERISCO;
		consonante1AM = ASTERISCO;
		letra1N = ASTERISCO;
		consonante1N = ASTERISCO;
		consonante1SN = ASTERISCO;
		fechaValue = "******";
		inicialGenerico = "*********";
		sexoValue = ASTERISCO;
		curpChange = true;
	}

	/**
	 * Censura las palabras altisonantes si se encuentra dentro de la lista de
	 * palabras altisontes {@link CURP_RFC#getAltisonantes()}
	 *
	 * @param subCurpAndRfc
	 * @return
	 */
	public static String deleteAltisonante(String subCurpAndRfc) {
		if (ALTISONANTES.contains(subCurpAndRfc)) {
			subCurpAndRfc = subCurpAndRfc.substring(0, 1) + ASTERISCO
					+ subCurpAndRfc.substring(2, subCurpAndRfc.length());
		}
		return subCurpAndRfc;
	}

	/**
	 * Genera el CURP apartir de los datos que se han asignado
	 *
	 * @return
	 */
	public String getCURP() {
		if (curpChange) {
			StringBuilder curp = new StringBuilder(inicialGenerico);
			curp.append(sexoValue);
			curp.append(estadoNacValue);
			curp.append(consonante1AP);
			curp.append(consonante1AM);
			curp.append(consonante1N.equals(ASTERISCO) ? consonante1SN
					: consonante1N);
			currentCurp = curp.toString();
			curpChange = false;
		}
		return currentCurp;
	}

	/**
	 * Obtiene la letra inicial de la palabra, si tiene acento se le quita
	 *
	 * @param temp
	 * @return
	 */
	public static String getLetraInicial(String temp) {
		String subcadena = "";
		if (StringUtils.isNotBlank(temp)) {
			subcadena = temp.substring(0, 1);
			if (subcadena.equals("/") || subcadena.equals("-")
					|| subcadena.equals(".")) {
				subcadena = ASTERISCO;
			}
		}
		//return remplazarAcentos(subcadena);
		return subcadena;
	}

	/**
	 * Metodo que obtiene la primera consontante interna de la palabra. Ejemplo
	 * de Pedro obtiene D
	 *
	 * @param String
	 *            palabra.
	 * @return String primer consonate interna.
	 */
	public static String getPrimeraConsonanteInterna(String palabra) {
		if (StringUtils.isBlank(palabra)) {
			return ASTERISCO;
		} else {
			palabra = palabra.substring(1).toUpperCase();
			String sinVocales = palabra.replaceAll("[AEIOUaeiou]", "");
//			sinVocales = latinToUnicode(sinVocales);

			//sustituir la Ñ por X
//			String patron = "u00D1";
//			sinVocales = sinVocales.replace(patron, X);

			return StringUtils.isBlank(sinVocales) ? ASTERISCO : sinVocales.substring(0, 1);
		}
	}

	/**
	 * Metodo para obtener la primera vocal interna de la palabra.Ejemplo de
	 * Pedro obitene E
	 *
	 * @param palabra
	 * @return Primera vocal interna
	 */
	public static String getPrimeraVocalInterna(String palabra) {
		if (StringUtils.isBlank(palabra)) {
			return ASTERISCO;
		} else {
			palabra = palabra.substring(1).toUpperCase();
			String sinConsonantes = palabra.replaceAll("[^AEIOUaeiou]", "");
			return StringUtils.isBlank(sinConsonantes) ? ASTERISCO : sinConsonantes.substring(0, 1);
		}
	}

	/**
	 * Genera los primeros 8 caracteres del RFC
	 *
	 * @return
	 */
	public String getRFC() {
		return inicialGenerico;
	}

	/**
	 * Verifica si el nombre es valido para ser tomado como parte de los datos
	 * del CURP tomando en cuenta los datos del arreglo
	 * {@link CURP_RFC#nombreNoValido}
	 *
	 * @param nombre1
	 * @return
	 */
	public static boolean isNameValid(String nombre1) {
		boolean valido = true;
		nombre1 = StringUtils.defaultString(nombre1, StringUtils.EMPTY);
		nombre1 = nombre1.toLowerCase();
		for (int i = 0; i < nombreNoValido.length; i++) {
			if ((nombre1.trim()).equals(nombreNoValido[i].trim())) {
				valido = false;
				break;
			}
		}
		return valido;
	}

	/**
	 * Remueve acentos y tildes de las vocales en la palabra
	 *
	 * @param palabra
	 * @return
	 */
	public static String remplazarAcentos(String palabra) {

		palabra = latinToUnicode(palabra);

		String patron = "\\u00E0";
		palabra = palabra.replace(patron, A);
		patron = "\\u00E1";
		palabra = palabra.replace(patron, A);
		patron = "\\u00E2";
		palabra = palabra.replace(patron, A);
		patron = "\\u00E3";
		palabra = palabra.replace(patron, A);
		patron = "\\u00E4";
		palabra = palabra.replace(patron, A);
		patron = "\\u00E5";
		palabra = palabra.replace(patron, A);
		patron = "\\u00C1";
		palabra = palabra.replace(patron, A);

		patron = "\\u00E8";
		palabra = palabra.replace(patron, E);
		patron = "\\u00E9";
		palabra = palabra.replace(patron, E);
		patron = "\\u00EA";
		palabra = palabra.replace(patron, E);
		patron = "\\u00EB";
		palabra = palabra.replace(patron, E);
		patron = "\\u00C8";
		palabra = palabra.replace(patron, E);
		patron = "\\u00C9";
		palabra = palabra.replace(patron, E);

		patron = "\\u00EC";
		palabra = palabra.replace(patron, I);
		patron = "\\u00ED";
		palabra = palabra.replace(patron, I);
		patron = "\\u00EE";
		palabra = palabra.replace(patron, I);
		patron = "\\u00EF";
		palabra = palabra.replace(patron, I);
		patron = "\\u00CD";
		palabra = palabra.replace(patron, I);

		patron = "\\u00F2";
		palabra = palabra.replace(patron, O);
		patron = "\\u00F3";
		palabra = palabra.replace(patron, O);
		patron = "\\u00F4";
		palabra = palabra.replace(patron, O);
		patron = "\\u00F5";
		palabra = palabra.replace(patron, O);
		patron = "\\u00F6";
		palabra = palabra.replace(patron, O);
		patron = "\\u00D3";
		palabra = palabra.replace(patron, O);

		patron = "\\u00F9";
		palabra = palabra.replace(patron, U);
		patron = "\\u00FA";
		palabra = palabra.replace(patron, U);
		patron = "\\u00FB";
		palabra = palabra.replace(patron, U);
		patron = "\\u00FC";
		palabra = palabra.replace(patron, U);
		patron = "\\u00DA";
		palabra = palabra.replace(patron, U);
		patron = "\\u00FA";
		palabra = palabra.replace(patron, U);

		patron = "\\u00D1";
		palabra = palabra.replace(patron, X);

		return palabra;
	}

	/**
	 * @param nuevoApellidoMaterno
	 */
	public void setApellidoMaterno(String nuevoApellidoMaterno) {
		if (StringUtils.isBlank(nuevoApellidoMaterno)) {
			letra1AM = ASTERISCO;
			consonante1AM = ASTERISCO;
			apellidoMaterno = null;
			updateInicial();
		} else if (!nuevoApellidoMaterno.equalsIgnoreCase(this.apellidoMaterno)) {
			apellidoMaterno = remplazarAcentos(nuevoApellidoMaterno.toUpperCase());
			letra1AM = getLetraInicial(this.apellidoMaterno);
			consonante1AM = getPrimeraConsonanteInterna(apellidoMaterno);
			updateInicial();
		}
	}

	/**
	 *
	 * @param nuevoApellidoPaterno
	 */
	public void setApellidoPaterno(String nuevoApellidoPaterno) {
		if (StringUtils.isBlank(nuevoApellidoPaterno)) {
			letra1AP = ASTERISCO;
			vocal1AP = ASTERISCO;
			consonante1AP = ASTERISCO;
			apellidoPaterno = null;
			updateInicial();
		} else if (!nuevoApellidoPaterno.equalsIgnoreCase(this.apellidoPaterno)) {
			apellidoPaterno = remplazarAcentos(nuevoApellidoPaterno.toUpperCase());
			letra1AP = getLetraInicial(apellidoPaterno);
			vocal1AP = getPrimeraVocalInterna(apellidoPaterno);
			consonante1AP = getPrimeraConsonanteInterna(apellidoPaterno);
			updateInicial();
		}
	}

	/**
	 *
	 * @param nuevoEstadoNac
	 */
	public void setEstadoNac(String nuevoEstadoNac) {
		if (StringUtils.isBlank(nuevoEstadoNac)) {
			estadoNac = null;
			estadoNacValue = "**";
			curpChange = true;
		} else if (!nuevoEstadoNac.equalsIgnoreCase(estadoNac)) {
			estadoNac = nuevoEstadoNac.toUpperCase();
			estadoNacValue = ESTADOS.get(remplazarAcentos(estadoNac));
			if(StringUtils.isBlank(estadoNacValue)){
				estadoNacValue = "**";
			}
			curpChange = true;
		}
	}
	/**
	 *
	 * @param date
	 */
	public void setFechaNac(Date date) {
		if (date == null) {
			fechaNac = date;
			fechaValue = "******";
			updateInicial();
		} else if (!date.equals(fechaNac)) {
			fechaNac = date;
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			StringBuilder subcadena = new StringBuilder();
			if (date != null) {
				//YEAR
				String year = String.valueOf(cal.get(Calendar.YEAR));
				subcadena.append(year.substring(year.length()-2, year.length()));
				//MONTH
				int month = cal.get(Calendar.MONTH) + 1;
				subcadena.append(month > 9 ? month : "0" + month);
				//Day
				int day = cal.get(Calendar.DAY_OF_MONTH);
				subcadena.append(day > 9 ? day : "0" + day);

			}
			fechaValue = subcadena.toString();
			updateInicial();
		}
	}

	/**
	 *
	 * @param nuevoNombre
	 */
	public void setNombre(String nuevoNombre) {
		if (StringUtils.isBlank(nuevoNombre)) {
			letra1AM = ASTERISCO;
			nombre = null;
			consonante1N = ASTERISCO;
			updateInicial();
		} else if (!nuevoNombre.equalsIgnoreCase(this.nombre)) {
			nombre = remplazarAcentos(nuevoNombre.toUpperCase());
			letra1N = getLetraInicial(nombre);
			consonante1N = getPrimeraConsonanteInterna(nombre);
			if (isNameValid(nombre)) {
				consonante1N = getPrimeraConsonanteInterna(nombre);
			} else {
				consonante1N = ASTERISCO;
			}
			updateInicial();
		}

	}

	/**
	 *
	 * @param nuevoSegundoNombre
	 */
	public void setSegundoNombre(String nuevoSegundoNombre) {
		if (StringUtils.isBlank(nuevoSegundoNombre)) {
			segundoNombre = null;
			consonante1SN = ASTERISCO;
			curpChange = true;
		} else if (!nuevoSegundoNombre.equalsIgnoreCase(this.segundoNombre)) {
			segundoNombre = remplazarAcentos(nuevoSegundoNombre.toUpperCase());
			consonante1SN = getPrimeraConsonanteInterna(segundoNombre);
			curpChange = true;
		}
	}

	/**
	 *
	 * @param nuevoSexo
	 */
	public void setSexo(String nuevoSexo) {
		if (StringUtils.isBlank(nuevoSexo)) {
			sexo = null;
			sexoValue = ASTERISCO;
			curpChange = true;
		} else if (!nuevoSexo.equalsIgnoreCase(this.sexo)) {
			sexo = nuevoSexo;
			if (MEXMEPaciente.SEXO_Female.equalsIgnoreCase(sexo)) {
				sexoValue = "M";
			} else if (MEXMEPaciente.SEXO_Male.equalsIgnoreCase(sexo)) {
				sexoValue = "H";
			} else {
				sexoValue = ASTERISCO;
			}
			curpChange = true;
		}
	}

	/**
	 * Regenera los primeros 8 caracteres de la cadena que compone a RFC y CURP
	 */
	private void updateInicial() {
		inicialGenerico = letra1AP + vocal1AP + letra1AM + letra1N;
		if (!inicialGenerico.contains(ASTERISCO)) {
			inicialGenerico = deleteAltisonante(inicialGenerico);
		}
		inicialGenerico += fechaValue;
		curpChange = true;
	}


	/**
	 * Replaces the accented (like á, è, ñ, ü) character with the corresponding
	 * unicode sequence.
	 * @param word The original accented word.
	 * @return The unaccented word.
	 */
	private static String latinToUnicode(String word) {
		String retVal = StringUtils.EMPTY;

		retVal = StringEscapeUtils.escapeJava(word);
//		retVal = retVal.replaceAll("\\\\", "");

		return retVal;
	}

}
