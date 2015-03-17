/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.MClient;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEMejoras;

/**
 * Clase de Utilerias del Sistema Hospital
 * <p>
 * Modificado por: $Author: taniap $ <p>
 * Fecha: $Date: 2006/09/05 17:55:49 $ <p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.1 $
*/

public class Utilerias {

	/**
	* Elimina una cadena de una cadena original
	* @param original La cadena original
	* @param borrar La cadena a eliminar de la original
	* @return La cadena original menos la eliminada
	* use StringUtils
	public static String removeString(String original, String borrar) {
		if(original == null || original.equals("")) {
			return(original);
		}
		if(borrar == null || borrar.equals("")) {
			return(original);
		}
		int place;
		while(original.indexOf(borrar) > -1) {
			place = original.indexOf(borrar);
			if(place == 0) {
				original = original.substring(borrar.length());
			} else if(place == (original.length() - 1)) {
				original = original.substring(0, original.length() - borrar.length());
			} else {
				original = original.substring(0, place) + original.substring(place + borrar.length());
			}
		}
		return(original);
	}*/


//	/**
//	 * Devuelve la diferencia en horas y minutos entre dos
//	 * fechas determinadas.
//	 *
//	 * @param fechaIni La fecha inicial del intervalo
//	 * @param fechaFin La fecha final del intervalo
//	 *
//	 * @return Un valor String con las horas y minutos en
//	 * formato "hh:mm".
//	 *
//	 */
//	public static String calcHoraMin(Calendar fechaIni, Calendar fechaFin) {
//		//obtenemos la diferencia en segundos entre las dos fechas
//		long d1 = fechaIni.getTime().getTime(); //fecha inicial en milisegundos
//		long d2 = fechaFin.getTime().getTime(); //fecha final en milisegundos
//		long tiempoSegundos = Math.abs(d2 - d1) / 1000;    //diferencia en milisegundos
//		long horas, minutos, segundos;
//		horas = tiempoSegundos / 3600;
//		segundos = tiempoSegundos - (horas * 3600);
//		minutos =  segundos / 60;
//
//		return Constantes.getDosDigitos().format(horas) + ":" + Constantes.getDosDigitos().format(minutos);
//	}

	/**
	* Adecua una cadena para que sea utilizada dentro de una sentencia SQL IN('cadena').
	* Si la cadena NO tiene un patron de A,B,C... etc (p.e: A) esta se adecuara para que
	* regrese un valor valido (p.e: 'A').
	* <p/>
	* A una cadena "ABC" ----> "'ABC'"
	* A una cadena "A,B,C" -----> "'A','B','C'"
	*
	* @param cadena Cadena a adecuar. Si ya tiene las comitas sensillas y/o las comas, la cadena
	* se retornara sin sufrir cambios.
	* use StringUtils
	*
	public static String inPattern(String cadena){
		String resultado = cadena;
		int p = 0;
		try{
			p = cadena.indexOf(",");
			if(p > 0){
				StringTokenizer st = new StringTokenizer(cadena,",");
				resultado = "'";
				while(st.hasMoreElements()){
					resultado += (String)st.nextElement() + "','";
				}
				resultado = resultado.substring(0,resultado.length()-2);
			}else{
				if(cadena.lastIndexOf("'") == (cadena.length()-1) && cadena.indexOf("'") == 0)
					resultado = cadena;
				else
					resultado = "'" + cadena + "'";
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}*/

	/**
	* Valida si la cadena tiene un formato de fecha valido (dd/MM/yyyy)
	* @param cadena Cadena a comparar
	* @return true - si la cadena tiene un formato valido, false - si la cadena no se puede combertir a una fecha valida
	*/
	public static boolean isDate(String cadena){
		try{
			if(Constantes.getSdfFecha().parse(cadena) != null);
				return true;
		} catch(Exception e) {
			return false;
		}
	}


//	/**
//	 * Cargamos los campos de un ResultSet a un bean.
//	 *
//	 * @param bean El objeto en el que colocaremos los datos
//	 * @param rs El ResultSet fuente
//	 * @throws SQLException En caso de ocurrir un error
//	 * al recuperar los datos.
//	 */
//	public static void populate(Object bean, ResultSet rs) throws SQLException {
//		ResultSetMetaData metaData = rs.getMetaData();
//		int ncolumns = metaData.getColumnCount();
//
//		HashMap<String, String> properties = new HashMap<String, String>();
//		// Scroll to next record and pump into hashmap
//		for (int i=1; i<=ncolumns ; i++) {
//			properties.put(sql2javaName(metaData.getColumnName(i)), rs.getString(i));
//		}
//		// Set the corresponding properties of our bean
//		try {
//			BeanUtils.populate(bean, properties);
//		} catch (InvocationTargetException ite) {
//			throw new SQLException("BeanUtils.populate threw " + ite.toString());
//		} catch (IllegalAccessException iae) {
//			throw new SQLException("BeanUtils.populate threw " + iae.toString());
//		}
//	}

//	/**
//	 * Convertimos un nombre sql a un nombre de propiedad.
//	 *
//	 * @param name El nombre del campo SQL
//	 *
//	 * @return El nombre Java
//	 */
//	protected static String sql2javaName(String name) {
//	    String column = "";
//	    for (int i = 0; i < name.length(); i++) {
//	      if (name.charAt(i) == '_') {
//	        column += ++i<name.length()?String.valueOf(name.charAt(i)).toUpperCase():"";
//	      } else {
//	        column += String.valueOf(name.charAt(i)).toLowerCase();
//	      }
//	    }
//	    return column;
//	  }

//	/**
//	 * Obtenemos el Locale de la aplicacion a partir del idioma y pais del contexto
//	 * @param ctx El contexto de la aplicacion
//	 * @return el Locale de la aplicacion
//	 * @deprecated Utilizar {@link org.compiere.util.Env.getLanguage(ctx).getLocale()}
//	 */
//	public  static Locale getLocale(Properties ctx) {
////		Locale locale = Env.getLanguage(ctx).getLocale();
////
////        if(locale == null) {
////        	String idioma = "en";
////            String pais = "US";
////        	locale = new Locale(idioma, pais);
////        }
//
//		return Env.getLanguage(ctx).getLocale();
//	}

	private static Hashtable<Locale, ResourceBundle> appResources_Cache =
		new Hashtable<Locale, ResourceBundle>();

	private static String fileName = "org.compiere.hospital.ApplicationResources";

	/**
	 * Obtenemos el mensage del Archivo de Propiedades.
	 * Nota: No formatea.
	 * @param locale Opcional, por defecto del Logeo a la Aplicacion
	 * @param key Llave del mensaje.
	 * @return
	 */
	public static String getMessage(Properties ctx, Locale locale, String key) {
		if (ctx == null) {
			throw new IllegalArgumentException();
		}
		String message = key;
		ResourceBundle res = getApplicationResources(ctx, locale);
		if (res != null) {
			try {
				if(WebEnv.DEBUG || res.containsKey(key)) {
					message = res.getString(key);
				} else {
					slog.log(Level.CONFIG, "While getting key " + key);
				}
			} catch (Exception e) {
				slog.log(Level.SEVERE, "While getting key " + key, e);
			}
		}
		return message;
	}

	public static String getAppMsg(Properties ctx, String key, Object... args) {
		return getMessage(ctx, null, key, args);
	}

//	public static String getLabel(String key, Object... args){
//		return getAppMsg(Env.getCtx(), key, args);
//	}

	public static String getLabel(String key, String... args) {
		Properties ctx = Env.getCtx();
		try {
			if(args == null || args.length == 0){
				return getMessage(ctx, Env.getLanguage(ctx).getLocale(), key);
			}else{
				MessageFormat formatter = new MessageFormat(getMessage(ctx, Env.getLanguage(ctx).getLocale(), key));
				formatter.setLocale(Env.getLanguage(ctx).getLocale());
				return formatter.format(args);
			}
		} catch (Exception ex) {
			slog.config("Key not found in the ApplicationResources: " + key);
		}
		return key;
	}

	public static String getMessage(Properties ctx, Locale locale, String key, Object... args) {
		try {
			MessageFormat formatter = new MessageFormat(getMessage(ctx, locale, key));
			formatter.setLocale(locale);
			key = formatter.format(args);
		} catch (Exception ex) {
			slog.log(Level.WARNING, null, ex.getMessage());
		}
		return key;
	}

	public static String getMessageArgs(Properties ctx, Locale locale, String key, Object... args) {
		if (ctx == null) {
			throw new IllegalArgumentException();
		}
		String message = key;
		ResourceBundle res = getApplicationResources(ctx, locale);
		if (res == null) {
			message = key;
		} else if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				message = StringUtils.replace(res.getString(key), "{" + i + "}", args[i].toString());
			}
		} else {
			message = res.getString(key);
		}
		return message;


	}

	/**
	 * Obtenemos el Archivo de Propiedades.
	 * org.compiere.hospital.ApplicationResources
	 * @return
	 */
	public static ResourceBundle getApplicationResources(Properties ctx, Locale locale) {
		if (ctx == null) {
			throw new IllegalArgumentException();
		}
		if (locale == null) {
			locale = Env.getLanguage(ctx).getLocale();
		}
		if (!appResources_Cache.containsKey(locale)) {
			appResources_Cache.put(locale, ResourceBundle.getBundle(fileName, locale));
		}
		return (ResourceBundle) appResources_Cache.get(locale);
	}

	/**
	 * Convertir RGB a Hexadecimal
	 * Expert: Lama
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	public static String convertHex(int red, int green, int blue){

		StringBuilder hexValue = new StringBuilder(6);

		hexValue.append(Integer.toHexString((int)Math.floor(red / 16)))
					.append(Integer.toHexString(red % 16))
					.append(Integer.toHexString((int)Math.floor(green / 16)))
					.append(Integer.toHexString(green % 16))
					.append(Integer.toHexString((int)Math.floor(blue / 16)))
					.append(Integer.toHexString(blue % 16));

		return hexValue.toString();
	}


	/**
	 * Returns the days for the specified year and month.
	 * @param year The year for the month.
	 * @param month The month to obtain the days.
	 * @return The days for the year and month.
	 *
	public static int getDaysOfMonth(int year, int month) {

		Calendar cal = new GregorianCalendar(year, (month - 1),1);

		return cal.getMaximum(Calendar.DAY_OF_MONTH);
	}*/

	protected static CLogger slog = CLogger.getCLogger (Utilerias.class);

	/**
	 * Regresa el entero Hora o Minutos de un string "HH:MM"
	 * @param horaMin - cadena de hora/minutos
	 * @param minutos - si regresa la hora o los minutos
	 * @return
	 */
	public static int getNumber(String horaMin, boolean hora){
		int valor = 0;
		try {
			valor = horaMin == null ? 0 :
					hora ? Integer.parseInt(horaMin.substring(0, horaMin.indexOf(":")))
							: Integer.parseInt(horaMin.substring(horaMin.indexOf(":")));

		}catch (Exception e) {
			slog.log(Level.WARNING, "-- parseInt : ", e.getMessage());
		}
		return valor;
	}


	/**
	 *  Devolvemos un ArrayList con objetos org.apache.struts.util.LabelValueBean
	 *  para indicar un rango de horas.
	 *
	 *@param  inicio  La hora de inicio del rango
	 *@param  fin     La hora de fin del rango
	 *@return         The lstHoras value
	 */
	public static ArrayList<LabelValueBean> getLstHoras(int inicio, int fin) {

		ArrayList<LabelValueBean> horas = new ArrayList<LabelValueBean>();

			for (int i = inicio; i <= fin; i++) {
			horas.add(
					new LabelValueBean(
							Constantes.getDosDigitos().format(i),
							Constantes.getDosDigitos().format(i)
					)
			);
			}

		return horas;
	}

	public static ArrayList<LabelValueBean> getLstHorasHH(int inicio, int fin) {

		ArrayList<LabelValueBean> horas = new ArrayList<LabelValueBean>();

			for (int i = inicio; i <= fin; i++) {
			horas.add(
					new LabelValueBean(
							i >= 12? new StringBuilder().append(Constantes.getDosDigitos().format(i-(i==12?0:12))).append("PM").toString() :
								new StringBuilder().append(Constantes.getDosDigitos().format(i)).append("AM").toString(),
							Constantes.getDosDigitos().format(i)
					)
			);
			}

		return horas;
	}

	/**
	 *  Devolvemos un ArrayList con objetos org.apache.struts.util.LabelValueBean
	 *  para indicar un rango de minutos con un lapso de tiempo.
	 *
	 *@param  inicio  El minuto de inicio del rango
	 *@param  fin     El minuto de fin del rango
	 *@param  lapso   El lapso entre minutos (5, 10, 15...)
	 *@return         The lstMinutos value
	 */
	public static ArrayList<LabelValueBean> getLstMinutos(int inicio, int fin, int lapso) {

		ArrayList<LabelValueBean> minutos = new ArrayList<LabelValueBean>();

			for (int i = inicio; i <= fin; i += lapso) {
			minutos.add(
					new LabelValueBean(
							Constantes.getDosDigitos().format(i),
							Constantes.getDosDigitos().format(i)
					)
			);
			}


		return minutos;
	}

	/**
	 *  Devolvemos un ArrayList con objetos org.apache.struts.util.LabelValueBean
	 *  para indicar un rango de minutos con un lapso de tiempo.
	 *
	 *@param  inicio  El minuto de inicio del rango
	 *@param  fin     El minuto de fin del rango
	 *@param  lapso   El lapso entre minutos (5, 10, 15...)
	 *@return         The lstMinutos value
	 */
	public static ArrayList<LabelValueBean> getLstMinutos() {

		ArrayList<LabelValueBean> minutos = new ArrayList<LabelValueBean>();

			for (int i = 0; i <= 59; i += 5) {
			minutos.add(
					new LabelValueBean(
							Constantes.getDosDigitos().format(i),
							Constantes.getDosDigitos().format(i)
					)
			);
			}


		return minutos;
	}


	public static long getCorectTime(long time) {
		Calendar begDate = Calendar.getInstance();
		begDate.setTimeInMillis(time);

		int minutes = begDate.get(Calendar.MINUTE);
		int min = minutes / 5;
		int seg = minutes % 5;

		if(seg == 0){
			return time;
		}else if(seg < 3){
			begDate.set(Calendar.MINUTE, (min * 5));
		}else{
			begDate.set(Calendar.MINUTE, ((min+1) * 5));
		}
		return begDate.getTimeInMillis();
	}




	/**
	 * Regresa una lista de a#os, ordenado desc.
	 * @param inicio
	 * @param fin
	 * @param lapso
	 * @return
	 *
	public static ArrayList<LabelValueBean> getLstYears(int inicio, int fin, int lapso) {

		ArrayList<LabelValueBean> years = new ArrayList<LabelValueBean>();

		for (int i = fin; i >= inicio; i -= lapso) {
			years.add(new LabelValueBean(String.valueOf(i), String.valueOf(i)));
		}

		return years;
	}*/

	/**
	 * Regresa una lista de meses
	 * @param valIni initial value for the first row.
	 * @param blank returns the list with an empty row
	 * @return
	 *
	public static ArrayList<LabelValueBean> getLstMonths(Properties ctx, boolean blank, int valIni) {

		//llenamos los meses
		Locale locale = Env.getLanguage(ctx).getLocale();
		DateFormatSymbols date = new DateFormatSymbols(locale);

		ArrayList<LabelValueBean> months = new ArrayList<LabelValueBean>();

		String meses[] = date.getMonths();
		if(blank)
			months.add(new LabelValueBean("", "0"));

		for (int i = 0; i < meses.length; i++) {
			if(meses[i] != null && !meses[i].equals("")) {
				LabelValueBean obj = new LabelValueBean(meses[i].toUpperCase(), String.valueOf(i+valIni));
				months.add(obj);
			}
		}

		return months;
	}*/


	/**
	 * Noelia:Regresa un lista de los a#os de acuerdo a un rango anterior y un rango posterior al a#o actual
	 * @param rangoIni, int que representa la cantidad de a#os anteriores al a#o actual a agregar a la lista
	 * @param rangoFin, int que representa la cantidad de a#os posteriores al a#o actual a agregar a la lista
	 * @return List<LabelValueBean> lista de los a#os de acuerdo a un rango anterior y un rango posterior al a#o actual
	 */
	public static List<LabelValueBean> getYearLst(int rangoIni, int rangoFin){

		int year = Calendar.getInstance().get(Calendar.YEAR);
		List<LabelValueBean> yearLst = new ArrayList<LabelValueBean>();
		LabelValueBean lvb = null;

		//Asignamos los a#os anteriores
		for(int i=rangoIni; i>0; i-- ){
			lvb = new LabelValueBean(String.valueOf(year - i), String.valueOf(year - i));
			yearLst.add(lvb);
		}

		//Asignamos el a#o actual
		lvb = new LabelValueBean(String.valueOf(year), String.valueOf(year));
		yearLst.add(lvb);

		//Asignamos los a#os posteriores
		for(int i=1; i<=rangoFin; i++ ){
			lvb = new LabelValueBean(String.valueOf(year + i), String.valueOf(year + i));
			yearLst.add(lvb);
		}

		return yearLst;
	}

	/**
	 * Obtienen un listado de Key-Name
	 * @param ctx
	 * @param tableName   Nombre de la tabla
	 * @param whereClause   (opcional)
	 * @param blankLine
	 * @param NameColumnName Columna para mostrar en el Name
	 * @param KeyColumnName Columna que contendra el Key
	 * @param trxName
	 * @return
	 *
	public static ArrayList<KeyNamePair> getListKeyName(Properties ctx, String tableName,
			String name_ColumnName, String key_ColumnName, String whereClause, Object[] params, boolean blankLine) {
		ArrayList<KeyNamePair> retValue = new ArrayList<KeyNamePair>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = prepare(ctx, psmt, tableName, name_ColumnName, key_ColumnName, whereClause, params);
			rs = psmt.executeQuery();

			if(blankLine) {
				retValue.add(new KeyNamePair(0, ""));
			}

			while (rs.next()) {
				retValue.add(new KeyNamePair(rs.getInt(2), rs.getString(1)));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage());
		} finally {
			DB.close(rs, psmt);
		}
		return retValue;
	}	*/
//	/**
//	 *
//	 * @param ctx
//	 * @param tableName
//	 * @param label_columnName
//	 * @param value_columnName
//	 * @param whereClause
//	 * @param params
//	 * @param blankLine
//	 * @return
//	 * @deprecated
//	 */
//	public static ArrayList<LabelValueBean> getListLabelValue(Properties ctx, String tableName,
//			String label_columnName, String value_columnName, String whereClause, Object[] params, boolean blankLine) {
//		return getListLabelValue(ctx, tableName, label_columnName, value_columnName, whereClause, blankLine, params);
//	}
	/**
	 * Obtienen un listado de Label-Value
	 * @param ctx
	 * @param tableName   Nombre de la tabla
	 * @param label_columnName Columna para mostrar en el Label
	 * @param value_columnName Columna que contendra el Value
	 * @param whereClause   (opcional)
	 * @param trxName
	 * @return
	 *  @deprecated
	 */
	public static ArrayList<LabelValueBean> getListLabelValue(Properties ctx, String tableName, String label_columnName, String value_columnName,
		String whereClause, boolean blankLine, Object... params) {
		ArrayList<LabelValueBean> retValue = new ArrayList<LabelValueBean>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

			sql.append("SELECT ").append(label_columnName).append(", ").append(value_columnName);
			sql.append(" FROM ").append(tableName);
			sql.append(" WHERE isActive = 'Y' ");
			if (whereClause != null) {
				sql.append(whereClause);
			}
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", tableName));
			sql.append(" ORDER BY 1 ");

			psmt = DB.prepareStatement(sql.toString(), null);

			DB.setParameters(psmt, params); // Set Parameter

			rs = psmt.executeQuery();
			if (blankLine)
				retValue.add(new LabelValueBean("", "0"));
			while (rs.next()) {
				retValue.add(new LabelValueBean(rs.getString(1), rs.getString(2)));
			}
		}
		catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage());
		}
		finally {
			DB.close(rs, psmt);
		}
		return retValue;
	}

	/**
	 * Método para enviar correos
	 * @param ctx Contexto
	 * @param message Mensaje
	 * @param isHtml Es HTML
	 * @param subject Asunto
	 * @param requestMail Destinatario
	 * @return Exitoso o no
	 */
	public static boolean sendMail(Properties ctx, String message,
			boolean isHtml, String subject, String requestMail) {
		return sendMail(ctx, message, isHtml, subject, requestMail, null);
	}

	/**
	 * Método para enviar correos
	 * @param ctx Contexto de la aplicacion
	 * @param message Mensaje a enviar - Not null
	 * @param isHtml Mandar true si el mensaje esta en formato HTML, false si es texto plano
	 * @param subject Tema del mensaje - Not null
	 * @param requestMail el e-mail al que se enviará el mensaje - Not null
	 * @param attachFiles Listado de archivos a adjuntar - Nullable
	 * @return Exitoso o no
	 */
	public static boolean sendMail(Properties ctx, String message,
			boolean isHtml, String subject, String requestMail,
			List<File> attachFiles) {
		boolean retValue = true;
		try {
			//Validar los parametros
			if (message == null || subject == null || requestMail == null) {
				retValue = false;
			}
			if (retValue) {
				// el cliente, para los datos del servidor y de envio
				MClient client = MClient.get(ctx, Env.getAD_Client_ID(ctx));

				// Crear el email con los datos proporcionados
				EMail email = new EMail(client, client.getRequestUser(), requestMail, subject, message);
				email.setSMTPPort(client.getSMTPPort());
				email.setSSLPort(client.getSSLPort());
				email.setSSLConnection(client.isSSLConnection());

				// Establece si es formato HTML
				if (isHtml) {
					email.setMessageHTML(message);
				}
				// Agregar los archivos adjuntos
				if (attachFiles != null) {
					for (File file : attachFiles) {
						email.addAttachment(file);
					}
				}
				// Si el servidor requiere autentificacion crearla
				if (client.isSmtpAuthorization()) {
					email.createAuthenticator(client.getRequestUser(), client
							.getRequestUserPW());
				}
				//Mandar el mensaje
				String msg = email.send();
				//Verificar que el menseje se envió
				if (!EMail.SENT_OK.equals(msg)) {
					retValue = false;
				}
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "Error en sendMail: " + e.getMessage());
			retValue = false;
		}

		return retValue;
	}

	/**
	 *
	 * @param ctx
	 * @param tableName
	 * @param column1
	 * @param column2
	 * @param whereClause
	 * @return
	 * @throws SQLException
	 *
	private static PreparedStatement prepare(Properties ctx, PreparedStatement pstmt, String tableName, String column1, String column2,
			String whereClause, Object[] params) throws SQLException {
		if (ctx == null || tableName == null)
			return null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		sql.append("SELECT ").append(column1).append(", ").append(column2).append(" FROM ").append(tableName).append(
				" WHERE isActive = 'Y' ");
		if (whereClause != null)
			sql.append(whereClause);
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", tableName));
		sql.append(" ORDER BY 1 ");

		pstmt = DB.prepareStatement(sql.toString(), null);
		// Set Parameter
		DB.setParameters(pstmt, params);
		return pstmt;
	}*/


	/**
	 * Elimina el archivo
	 * @author Lorena Lama
	 * @param file
	 * @return
	 */
	public static boolean forceDelete(File file) {
		boolean deleted = true;

		if (file.exists()) {
			try {
				FileUtils.forceDelete(file);
			} catch (IOException ex) {
				deleted = false;
				slog.log(Level.SEVERE, "DeleteDirectory: ", ex.getMessage());
			}
		}
		return deleted;
	}

	/**
	 * Elimina las carpetas dada una ruta
	 * @author Lorena Lama
	 * @param path
	 * @return
	 */
	public static boolean forceDelete(String path) {
		File directory = new File(path);
		return forceDelete(directory);
	}

	/**
	 *  Regresa el nombre de la imagen
	 * @author Lorena Lama
	 * @param path
	 * @param imgName
	 * @return
	 * @throws Exception
	 */
	public static String getFileName(String path, String imgName) throws Exception {
		String fileName = null;

		File file = getFile(path, imgName);

		if (file != null)
			fileName = file.getName();

		return fileName;
	}


	/**
	 * Regresa la imagen de acuerdo a un nombre dado
	 * @author Lorena Lama
	 * @param path
	 * @param imgName
	 * @return
	 * @throws Exception
	 */
	public static File getFile(String path, String imgName) throws Exception {
		File file = null;
		imgName = StringUtils.substringBeforeLast(imgName, ".");//eliminamos la extension

		File directory = new File(path);
		if (directory.exists()) {
			File[] files = directory.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().startsWith(imgName)) {
					file = files[i];
					break;
				}
			}
		}

		return file;
	}

	/**
	 * Regresa la imagen de acuerdo a un nombre dado
	 * @author Lorena Lama
	 * @param path
	 * @param imgName
	 * @return
	 * @throws Exception
	 */
	public static FileInputStream getFileInputStream(String path, String imgName) throws Exception {
		FileInputStream inputStr = null;
		File file = getFile(path, imgName);

		if (file != null)
			inputStr = new FileInputStream(file);

		return inputStr;
	}



	/**
	 * Proceso que agraga la palabra "PESOS" y "M.N." en el precio con letra de la factura (Solo M#xico)
	 * @param precioLetra
	 * @return
	 */
	public static String precioMexico(String precioLetra, boolean unPeso) {

		StringTokenizer t = new StringTokenizer(precioLetra, " ");
		String precioCompleto = "";

		boolean bandera = false;

		while (t.hasMoreTokens()) {
			String token = t.nextToken();
			StringTokenizer pesosMN = new StringTokenizer(token, "/");
			bandera = false;
			/*if(!bandera) {
				precioCompleto += token;
				break;
			}*/
			while (pesosMN.hasMoreTokens() && !bandera) {

				String mn = pesosMN.nextToken();
				if (!mn.equals(token)) {
					bandera = true;
					break;
				}
			}
			if(bandera)
				precioCompleto += (!unPeso ? "PESOS " : "PESO ") + token + " M.N. ";
			else
				precioCompleto += token + " ";
		}
		return precioCompleto.replace("UNO", "UN");// correccion de precio mexico

	}

//	/**
//	 * Regresa el monto en letras, segun el idioma
//	 * @param precioLetra
//	 * @return
//	 */
//	public static String getTotalLetras(Properties ctx, String amount) {
//
//		Locale loc = Env.getLanguage(ctx).getLocale();
//		Language lag = new Language(loc.getDisplayLanguage(), Env.getAD_Language(ctx), loc);
//
//		String precioLetra = Msg.getAmtInWords(lag, amount);
//
//		String idioma = Env.getAD_Language(ctx);
//
//		if (idioma.equalsIgnoreCase("es_MX")) {
//			return precioMexico(precioLetra, new Boolean(amount.equalsIgnoreCase("1")));
//		} else {
//			return precioLetra;
//		}
//	}

	/**
	 * Get list of valid document action into the options array parameter.
	 * Set default document action into the docAction array parameter.
	 * @param docStatus
	 * @param processing
	 * @param orderType
	 * @param isSOTrx
	 * @param AD_Table_ID
	 * @param docAction
	 * @param options
	 * @return Number of valid options
	 *
	public static int getValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID, String[] docAction, String[] options)
	{
		if (options == null)
			throw new IllegalArgumentException("Option array parameter is null");
		if (docAction == null)
			throw new IllegalArgumentException("Doc action array parameter is null");

		int index = 0;

//		Locked
		if (processing != null)
		{
			boolean locked = "Y".equals(processing);
			if (!locked && processing instanceof Boolean)
				locked = ((Boolean)processing).booleanValue();
			if (locked)
				options[index++] = DocumentEngine.ACTION_Unlock;
		}

		//	Approval required           ..  NA
		if (docStatus.equals(DocumentEngine.STATUS_NotApproved))
		{
			options[index++] = DocumentEngine.ACTION_Prepare;
			options[index++] = DocumentEngine.ACTION_Void;
		}
		//	Draft/Invalid				..  DR/IN
		else if (docStatus.equals(DocumentEngine.STATUS_Drafted)
			|| docStatus.equals(DocumentEngine.STATUS_Invalid))
		{
			options[index++] = DocumentEngine.ACTION_Complete;
		//	options[index++] = DocumentEngine.ACTION_Prepare;
			options[index++] = DocumentEngine.ACTION_Void;
		}
		//	In Process                  ..  IP
		else if (docStatus.equals(DocumentEngine.STATUS_InProgress)
			|| docStatus.equals(DocumentEngine.STATUS_Approved))
		{
			options[index++] = DocumentEngine.ACTION_Complete;
			options[index++] = DocumentEngine.ACTION_Void;
		}
		//	Complete                    ..  CO
		else if (docStatus.equals(DocumentEngine.STATUS_Completed))
		{
			options[index++] = DocumentEngine.ACTION_Close;
		}
		//	Waiting Payment
		else if (docStatus.equals(DocumentEngine.STATUS_WaitingPayment)
			|| docStatus.equals(DocumentEngine.STATUS_WaitingConfirmation))
		{
			options[index++] = DocumentEngine.ACTION_Void;
			options[index++] = DocumentEngine.ACTION_Prepare;
		}
		//	Closed, Voided, REversed    ..  CL/VO/RE
		else if (docStatus.equals(DocumentEngine.STATUS_Closed)
			|| docStatus.equals(DocumentEngine.STATUS_Voided)
			|| docStatus.equals(DocumentEngine.STATUS_Reversed))
			return 0;

		/********************
		 *  Order
		 *
		if (AD_Table_ID == MOrder.Table_ID)
		{
			//	Draft                       ..  DR/IP/IN
			if (docStatus.equals(DocumentEngine.STATUS_Drafted)
				|| docStatus.equals(DocumentEngine.STATUS_InProgress)
				|| docStatus.equals(DocumentEngine.STATUS_Invalid))
			{
				options[index++] = DocumentEngine.ACTION_Prepare;
				options[index++] = DocumentEngine.ACTION_Close;
				//	Draft Sales Order Quote/Proposal - Process
				if ("Y".equals(isSOTrx)
					&& ("OB".equals(orderType) || "ON".equals(orderType)))
					docAction[0] = DocumentEngine.ACTION_Prepare;
			}
			//	Complete                    ..  CO
			else if (docStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_ReActivate;
			}
			else if (docStatus.equals(DocumentEngine.STATUS_WaitingPayment))
			{
				options[index++] = DocumentEngine.ACTION_ReActivate;
				options[index++] = DocumentEngine.ACTION_Close;
			}
		}
		/********************
		 *  Shipment
		 *
		else if (AD_Table_ID == MInOut.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}
		/********************
		 *  Invoice
		 *
		else if (AD_Table_ID == MInvoice.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}
		/********************
		 *  Payment
		 *
		else if (AD_Table_ID == MPayment.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}
		/********************
		 *  GL Journal
		 *
		else if (AD_Table_ID == MJournal.Table_ID || AD_Table_ID == MJournalBatch.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
				options[index++] = DocumentEngine.ACTION_Reverse_Accrual;
			}
		}
		/********************
		 *  Allocation
		 *
		else if (AD_Table_ID == MAllocationHdr.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}
		//[ 1782412 ]
		/********************
		 *  Cash
		 *
		else if (AD_Table_ID == MCash.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
			}
		}
		/********************
		 *  Bank Statement
		 *
		else if (AD_Table_ID == MBankStatement.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
			}
		}
		/********************
		 *  Inventory Movement, Physical Inventory
		 *
		else if (AD_Table_ID == MMovement.Table_ID
			|| AD_Table_ID == MInventory.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(DocumentEngine.STATUS_Completed))
			{
				options[index++] = DocumentEngine.ACTION_Void;
				options[index++] = DocumentEngine.ACTION_Reverse_Correct;
			}
		}
		return index;
	}	*/


	/**
	 * Returns the value of an especific key in the env properties
	 * @author Lorena Lama
	 * @param key
	 * @return
	 */
	public static String getPropertiesFromEnv(String key) {
		Properties properties = WebEnv.getEnv_Properties();
		return getProperties(properties, key);
	}

	/**
	 * Returns the value of an especific key in the path properties
	 * @author Lorena Lama
	 * @param key
	 * @return
	 */
	public static String getPropertiesFromXPT(String key) {
		Properties properties = WebEnv.getXPT_Properties();
		return getProperties(properties, key);
	}

	/**
	 * Returns the value of an especific key in the properties
	 * @author Lorena Lama
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getProperties(Properties properties, String key) {
		if (properties == null || key == null || !properties.containsKey(key)) {
			return null;
		}
		return properties.getProperty(key);
	}

//	public static File createFromInputStream(InputStream inputStream, String directoryPath) {
//		if(inputStream == null || directoryPath == null)
//			return null;
//
//		File file_tmp = new File(directoryPath);
//		FileOutputStream fos = null;
//		try {
//			file_tmp.createNewFile();
//			file_tmp.deleteOnExit();
//
//			fos = new FileOutputStream(file_tmp);
//			IOUtils.copy(inputStream, fos);
//		} catch (FileNotFoundException e) {
//			slog.log(Level.SEVERE, "createFile.FileNotFoundException", e);
//		} catch (IOException e) {
//			slog.log(Level.SEVERE, "createFile.IOException", e);
//		} finally {
//			try {
//				if (fos != null)
//					fos.close();
//			} catch (IOException e) {
//				slog.log(Level.SEVERE, "createFile.IOException", e);
//			}
//		}
//		return file_tmp;
//	}
	public static Object getFieldFrom(Properties ctx, String tableName, String field, String column, Object param){
		return getFieldFrom(ctx, tableName, field, column, param, true, null);
	}
//	public static Object getFieldFrom(Properties ctx, String tableName, String field, String column, Object param, String trxName){
//		return getFieldFrom(ctx, tableName, field, column, param, true, trxName);
//	}
//	public static Object getFieldFrom(Properties ctx, String tableName, String field, String column, Object param, boolean onlyActives) {
//		return getFieldFrom(ctx, tableName, field, column, param, onlyActives, null);
//	}

	/**
	 * Devuelve el valor del campo de la tabla dada segun la columna y parametro.
	 * @param ctx
	 * @param tabla tabla a consultar
	 * @param field nombre de columna con valor de retorno
	 * @param column nombre de la colunmna de la clausula where
	 * @param param valor de parametro
	 * @return valor de "field"
	 * @author lorena lama
	 */
	public static Object getFieldFrom(Properties ctx, String tableName, String field, String column, Object param, boolean onlyActives, String trxName){
		Object retValue = null;
		if (StringUtils.isBlank(tableName) || StringUtils.isBlank(field) || StringUtils.isBlank(column))
			return retValue;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean blSetearParametro = true;

		sql.append("SELECT ").append(field);
		sql.append(" FROM ").append(tableName);
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " WHERE ", tableName));
		sql.append(onlyActives ? " AND IsActive = 'Y' " : StringUtils.EMPTY);

		if (DB.isPostgreSQL() && "rownum".equalsIgnoreCase(StringUtils.trim(column))) {
			//Si la columna es rownum y la BD es Postgresql, hacer el manejo para postgresql.
			if (param != null) {
				sql = new StringBuilder(DB.getDatabase().addPagingSQL
						(sql.toString(), 1, Integer.parseInt(param.toString())));
			}
			//No debemos setear el parametro de la consulta si la BD es postgresql y la columna
			//es rownum. Jesus Cantu.
			blSetearParametro = false;

		// Si la columna es cualquier otra diferente a rownum y la BD es Postgresql.
		// Se hace el manejo normal.
		// Si es Oracle la BD, Se hace el manejo normal de Oracle en este else.
		} else {
			sql.append(" AND ");
			sql.append(column);
			sql.append(param != null && param.toString().indexOf("%") != -1 ? " LIKE " : " = ");
			sql.append(" ? ");
		}

		slog.log(Level.INFO, "SQL : " + sql + "    param:" + param);

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);//lhernandez.12042011 : se agrega la transaccion

			// Cuando la columna es rownum y la BD es Postgresql no debe setearse el parametro.
			// Esto lo hace el manejo del Limit.
			if (blSetearParametro) {
				DB.setParameter(pstmt, 1, param);
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = rs.getObject(1);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getFieldFrom - sql: " + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

//	/**
//	 * Convierte el ActionErrors a un StringBuilder con los mensajes de error
//	 * @param ActionErrors errores (errores)
//	 * @return StringBuilder error (String con todos los errores)
//	 * @author Lizeth de la Garza
//	 */
//	@SuppressWarnings("unchecked")
//	public static StringBuilder getErrors(ActionErrors errores) {
//		StringBuilder error = new StringBuilder();
//		Iterator<ActionError> it = errores.get();
//		while (it.hasNext()) {
//			error.append(Utilerias.getMessage(Env.getCtx(), null, StringUtils.substringBeforeLast(it.next().getKey(), "\\")) + "\n");
//		}
//		return error;
//	}

	/**
	 * Obtenemos el mensage del Archivo de Propiedades.
	 * Nota: No formatea.
	 * @param ctx Obligatorio
	 * @param key Llave del mensaje.
	 * @return mensaje traducido
	 */
	public static String getMsg(Properties ctx, String key) {
		return getMessage(ctx, null, key);
	}

	/**
	 * Obtenemos el mensage del Archivo de Propiedades.
	 * Nota: No formatea.
	 * @param ctx Obligatorio
	 * @param key Llave del mensaje.
	 * @return mensaje traducido
	 */
	public static String getYesNoMsg(Properties ctx, boolean value) {
		return  Utilerias.getMsg(ctx, value ? "msg.si" : "msg.no");
	}
	/**
	 * Regresa true si la cadena se puede convertir a numero
	 * @param cadena
	 * @return
	 * @deprecated
	 */
	public static boolean isNumeric(String cadena){
		try{
			Double.parseDouble(cadena);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
//	public static boolean esPar(int x) {
//		if ((x % 2) == 0) {
//			return true;
//		}
//
//		return false;
//	}

	public static double getYearsByMonths(int months) {
		return ((months % 12) * 0.01) + (months/12);
	}

	public static String getAgeFromSubstrings(Properties ctx, String age) {
		if (age != null) {
			if (age.contains("@y@")) {
				age = StringUtils.replace(age, "@y@", ' ' + getMsg(ctx, "msj.anios") + ' ');
			}
			if (age.contains("@m@")) {
				age = StringUtils.replace(age, "@m@", ' ' + getMsg(ctx, "msj.meses") + ' ');
			}
			if (age.contains("@d@")) {
				age = StringUtils.replace(age, "@d@", ' ' + getMsg(ctx, "progMed.dias"));
			}
		}

		return age;
	}

	public static String validateTelephone(Properties ctx, String telephone) {
		String msg = null;
		if (!Utilerias.validateTel(ctx, telephone)) {
			if (MEXMEMejoras.get(ctx) != null) {
				msg = getMessage(ctx, null, "error.telephoneFormat", MEXMEMejoras.get(ctx).getDigitosArea(), MEXMEMejoras.get(ctx).getDigitosTel());
			}
		}
		return msg;
	}

	public static boolean validateTel(Properties ctx, String telephone) {
		if(MEXMEMejoras.get(ctx) == null){
			return false;
		}
		String patron = getTelephonePattern(ctx);

		if(patron != null){

			String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
			CharSequence inputStr = StringUtils.defaultIfEmpty(telephone, "");
			Pattern pattern = Pattern.compile(expression);
			Matcher matcher = pattern.matcher(inputStr);
			if (!matcher.matches()){
				return false;

			}
			/*Pattern pattern = Pattern.compile(patron);
			Matcher m = pattern.matcher(StringUtils.defaultIfEmpty(telephone, ""));
			if (!m.matches()) {
				return false;
			}*/
		}
		return true;
	}

	public static String getTelephonePattern(Properties ctx) {
		String telPattern = null;
		MEXMEMejoras obj = MEXMEMejoras.get(ctx);
		if (obj != null && obj.getDigitosArea() > 0 && obj.getDigitosTel() > 0) {
			int areaDigits = obj.getDigitosArea();
			int telephoneDigits = obj.getDigitosTel();
			telPattern = ("\\d{" + (areaDigits + telephoneDigits) + "}");
		}
		return telPattern;
	}

	public static boolean validateMail(String email) {
		boolean bandera = true;
		Pattern correo = Pattern.compile(Constantes.EMAIL_PATTERN);

		if (!(email.equals(""))) {
			if (!(correo.matcher(email).find() == true)) {
				bandera = false;
			}
		}
		return bandera;
	}

	//TODO: Quitar
	public static String cambioValor(Object value, int index){
		String valorStr = null;
		try{
			if(value!=null)
				valorStr = String.valueOf(value);
			else
				valorStr = " ";
		}catch (Exception e) {
			//System.out.println("no funciono con este valor: " + index);
		}
		return valorStr;
	}

	/**
	 * Convierte una lista de Integer a un int[]
	 * @param list
	 * @return
	 */
	public static int[] toIntArray(List<Integer> list) {
		int[] intArray = new int[list.size()];
		int i = 0;

		for (Integer integer : list)
			intArray[i++] = integer;

		return intArray;
	}

	/**
	 * Valida que el codigo postal sea numerico y que la longitud sea de 5, 7 y 9 caracteres.
	 * Para Chile los codigos postales son de 7 caracteres, por esta razon se adecuo este
	 * metodo. Para Mexico son 5.
	 *
	 * @param zip, el string que contiene el codigo postal a validar
	 * @return true si es valido el codigo postal de lo contrario devuelve false.
	 * @author jcantu modificado por Jesus Cantu el 11 de Junio del 2012
	 */
	public static boolean validateZipCode(String zip) {
		String postalCodeRegEx = "^\\d{5}$|^\\d{7}$|^\\d{9}$";
		return Pattern.matches(postalCodeRegEx, zip);

	}

	/**
	 * Valida que los caracteres enviados en el string sean validos de la a a la z, del 0 al 9
	 * incluyendo los acentos, dieresis y las ñ.
	 *
	 * @param car, el string que contiene el texto a validar.
	 * @return true si es valido el string de lo contrario devuelve false.
	 * @author freyes modificado por Jesus Cantu el 11 de Junio del 2012
	 */
	public static boolean validateSpecialChar(String car) {
//		String noSpace = car.replace(" ", "");
		//Lama: se invierte validacion, limitanto los caracteres NO validos para una direccion.
		//String postalCodeRegEx = "^[a-zA-Z0-9/.,;:()&#áéíóúÁÉÍÓÚÑñüÜ]*$";
		// |¬!$%=?¿¡~+[]{}^_
		char[] array =  {'~','|','@','?','¿','¡','!','[',']','{','}','+','=','^','$','%','¬','_'};
		return !StringUtils.containsAny(car, array);
			//!Pattern.matches(postalCodeRegEx, noSpace);
	}

	/**
	 * aumentar al siguiente dia
	 * @param start
	 */
	public static void addDay(final Calendar start) {
		start.add(Calendar.DATE, 1);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
	}

	public static Calendar getDayHour() {
		return getDayHour(null);
	}
	public static Calendar getDayHour(Date date) {
		final Calendar cal = Calendar.getInstance();
		if(date != null){
			cal.setTime(date);
		}
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		return cal;
	}

	public static int getAsInt(final Map<String, Object> map, final String key) {
		int retValue = -1;
		if (map != null && !map.isEmpty()) {
			final Object value = map.get(key);
			if (value instanceof Integer) {
				retValue = (Integer) value;
			} else if (value != null && StringUtils.isNumeric(value.toString())) {
				try {
					retValue = Integer.valueOf(value.toString());
				} catch (NumberFormatException e) {
					retValue = -1;
					slog.log(Level.SEVERE, "getParameterAsInt: " + key, e);
				}
			}
		}
		return retValue;
	}

	public static boolean getAsBoolean(final Map<String, Object> map, final String key) {
		boolean retValue = false;
		if (map != null && !map.isEmpty()) {
			final Object value = map.get(key);
			if (value instanceof Boolean) {
				retValue = (Boolean) value;
			} else {
				retValue = DB.toBoolean(value);
			}
		}
		return retValue;
	}

	public static Date getAsDate(final Map<String, Object> map, final String key) {
		Date retValue = null;
		if (map != null && !map.isEmpty()) {
			final Object value = map.get(key);
			if (value instanceof Date) {
				retValue = (Date) value;
			}
		}
		return retValue;
	}

	/**
	 * gvaldez regresa una lista de X tabla con las columnas solicitadas utilizando el nivel de acceso
	 *
	 * @param ctx
	 *            - contexto
	 * @param columnValue
	 *            - campo para llave
	 * @param columnName
	 *            - campo para mostrar
	 * @param tableName
	 *            - tabla a cargar
	 * @param sqlWhere
	 *            - condition de Busqueda
	 * @return
	 * @throws Exception
	 */
	public static List<ValueNamePair> getAll(Properties ctx, String tableName, String columnValue, String columnName,  String sqlWhere){

		final List<ValueNamePair> array = new ArrayList<ValueNamePair>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		sql.append(" SELECT * ")
		   .append(" FROM ")
		   .append(tableName)
		   .append(" where isactive = 'Y'");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",tableName));
		if (StringUtils.isNotEmpty(sqlWhere)){
			sql.append(sqlWhere);
		}


		try{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();

			while (rs.next()){
				array.add(new ValueNamePair(rs.getString(columnValue), rs.getString(columnName)));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE,sql.toString() +  " - " +e.getMessage());
		}finally {
			DB.close(rs, pstmt);
		}
		return array;
	}


	/**
	 * Devolvemos un List con objetos KeyNamePair
	 * para indicar un rango de minutos con un lapso de tiempo.
	 *
	 * @param inicio El minuto de inicio del rango
	 * @param fin El minuto de fin del rango
	 * @param lapso El lapso entre minutos (5, 10, 15...)
	 * @return The lstMinutos value
	 */
	public static List<KeyNamePair> getMinutos(int inicio, int fin, int lapso) {
		final List<KeyNamePair> minutos = new ArrayList<KeyNamePair>();
		for (int i = inicio; i <= fin; i += lapso) {
			minutos.add(new KeyNamePair(i, Constantes.getDosDigitos().format(i)));
		}
		return minutos;
	}

	/*
	 * Return a double with Currency Format
	 */
	public static String returnWithCurrFormatUS(double value){
		return NumberFormat.getCurrencyInstance(Locale.CANADA).format(value);
	}

	/*
	 * Return a double with percent Format
	 */
	public static String returnWithPercFormatUS(double value){
		return NumberFormat.getPercentInstance(Locale.CANADA).format(value);

	}

	/**
	 * Obtiene el  ultimo mensaje de error logueado para mostrarlo en pantalla.
	 *
	 * @return el mensaje de error o vacío si err es null.
	 */
	public static String getLastErrorMessage() {
		org.compiere.util.ValueNamePair err = CLogger.retrieveError();

		String slMsg = StringUtils.EMPTY;
		if (err != null) {
			slMsg = err.getName();
		}

		return slMsg;
	}
}
