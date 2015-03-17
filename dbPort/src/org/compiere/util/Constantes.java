/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * Clase de Constantes del Sistema Hospital
 * <p>
 * Modificado por: $Author: taniap $ <p>
 * Fecha: $Date: 2006/09/27 14:33:51 $ <p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.43 $
*/
 
public class Constantes {
	
	/**
	 * Formato decimal ###,##0.00
	 */
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,##0.00");
	
	/**
	 * Formato decimal ###,##.0000
	 */
	public static final DecimalFormat DECIMAL_FORMAT_4 = new DecimalFormat("###,##0.0000");

	/**
	 * Da formato decimal al numero enviado
	 * 
	 * @param value
	 *            Valor a dar formato
	 * @return
	 */
	public static String toDecimalFormat(BigDecimal value) {
		return value != null ? DECIMAL_FORMAT.format(value) : "0.00";
	}

	/**
	 * Da formato decimal al numero enviado
	 * 
	 * @param value
	 *            Valor a dar formato
	 * @return
	 */
	public static String toDecimalFormat(double value) {
		return DECIMAL_FORMAT.format(value);
	}
	
	/**
	* Constante para Reportes
	*/
	public static final String REPORTE = "REPORTE";
	
	public static final String INST = "T";
	public static final String PROF = "P";
	public static final String BOTH = "B";
	
	public static final String OTHERPHYSICIAN = "OTHERS";
	
	/**@deprecated Constante para Resumen Clinico 	*/
	public static final String RESUMEN = "RESUMEN";
	
	public static final String SPACE = " ";
	
	/**
	* Constante para la Receta
	*/
	public static final String RECETA = "RECETA";
	
	/** @deprecated Constante para Nota de Entrega de Medicamentos */
	public static final String NOTA = "NOTA";
	
	/** @deprecated  Constante para Vale de Entrega de Medicamentos */
	public static final String VALE = "VALE";
	
	/**
	* Constantes para las busquedas de tipos de documentos
	* p.e. name LIKE %RECETA%
	*/
	public static final String SERVICIO = "SERVICIO";
	
	/**
	 * if Language locale is "es_MX" 
	 * @param ctx
	 * @return
	 */
	public static boolean isSpanishMX(Properties ctx){
		return StringUtils.equals(Env.getLanguage(ctx).getLocale().toString(), "es_MX");
	}
	
	/**
	 * Formatea a fecha larga (DiaSemana, dia mesCompleto a�o) EEEE, d MMMM yyyy
	 */
	public static SimpledateFormat getSdfFechaLarga(Properties ctx) {
		SimpledateFormat sdf = null;
		if (isSpanishMX(ctx)) {
			sdf = new SimpledateFormat(ctx, "EEEE, d MMMM yyyy", Env.getLanguage(ctx).getLocale());
		} else {
			sdf = new SimpledateFormat(ctx, "EEEE, MMMM d", Env.getLanguage(ctx).getLocale());
		}
		return sdf;
	}
	
	/**
	 * Formatea a fecha corta (dd/MM/yy hh:mm am/pm  o MM/dd/yy hh:mm am/pm 
	 */
	public static DateFormat getDFFechaCortaHora(Properties ctx) {
		return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Env.getLanguage(ctx).getLocale());
	}
	
	/**
	 * Formatea a fecha corta (dd/MM/yy  o MM/dd/yy)
	 */
	public static SimpledateFormat getShortDate(Properties ctx) {
		SimpledateFormat format = null;
		if (isSpanishMX(ctx)) {
			format = new SimpledateFormat(ctx, "dd/MM/yy", Env.getLanguage(ctx).getLocale());
		} else {
			format = new SimpledateFormat(ctx, "MM/dd/yy", Locale.US);
		}
		return format;
	}
	
	/**
	 * Formatea a fecha corta (dd/MM/yyyy hh:mm am/pm  o MM/dd/yyyy hh:mm am/pm 
	 */
	public static SimpledateFormat getSDFFechaCortaHora(Properties ctx) {
		SimpledateFormat format = null;
		if (isSpanishMX(ctx)) {
			format = new SimpledateFormat(ctx, "dd/MM/yyyy hh:mm aaa", Env.getLanguage(ctx).getLocale());
		} else {
			format = new SimpledateFormat(ctx, "MM/dd/yyyy hh:mm aaa", Locale.US);
		}
		return format;
	}
	
	public static SimpledateFormat getSDFFechaHora24(Properties ctx) {
		SimpledateFormat format = null;
		if (isSpanishMX(ctx)) {
			format = new SimpledateFormat(ctx, "dd/MM/yy HH:mm", Env.getLanguage(ctx).getLocale());
		} else {
			format = new SimpledateFormat(ctx, "MM/dd/yy HH:mm", Locale.US);
		}
		return format;
	}
	
	/**
	 * Formatea a fecha corta dd/MM/yyyy o MM/dd/yyyy 
	 */
	public static SimpledateFormat getSDFFechaCortaDMA(Properties ctx) {
		SimpledateFormat format = null;
		if (isSpanishMX(ctx)) {
			format = new SimpledateFormat(ctx, "dd/MM/yyyy", Env.getLanguage(ctx).getLocale());
		} else {
			format = new SimpledateFormat(ctx, "MM/dd/yyyy", Locale.US);
		}
		return format;
	}
	
	/**
	 * Formato de fecha y hora (MM/dd/yyyy HH:mm)
	 * @param ctx
	 * @return
	 */
	public static SimpledateFormat getSDFDateTime(Properties ctx) {
		SimpledateFormat format = null;
		if (isSpanishMX(ctx)) {
			format = new SimpledateFormat(ctx, "dd/MM/yyyy HH:mm", Env.getLanguage(ctx).getLocale());
		} else {
			format = new SimpledateFormat(ctx, "MM/dd/yyyy HH:mm", Locale.US);
		}
		return format;
	}
	
	/**
	 * Formato de fecha y hora (MM/dd HH:mm)
	 * @param ctx
	 * @return
	 */
	public static SimpledateFormat getDateTimeNoYear(Properties ctx) {
		SimpledateFormat format = null;
		
		if (isSpanishMX(ctx)) {
			format = new SimpledateFormat(ctx, "dd/MM HH:mm", Env.getLanguage(ctx).getLocale());
		} else {
			format = new SimpledateFormat(ctx, "MM/dd HH:mm", Locale.US);
		}
		
		return format;
	}

	/**
	 * Devuelve o conviere una fecha con formato dd/MMM/yyyy
	 *
	public static SimpledateFormat sdfFechaM = new SimpledateFormat("dd/MMM/yyyy");
	
	/**
	 * Devuelve una fecha con formato dd/MMM/yyyy
	 */
	public static SimpledateFormat getSdfFechaM(Properties ctx) {
		return new SimpledateFormat(ctx, "dd/MMM/yyyy", Env.getLanguage(ctx).getLocale());
	}
	
	/**
	 * Devuelve una fecha con formato MMM dd, yyyy
	 */
	public static SimpledateFormat getSdfFechaM_CDA(Properties ctx) {
		return new SimpledateFormat(ctx, "MMM dd, yyyy", Env.getLanguage(ctx).getLocale());
	}
	
	/**
	 * Devuelve una fecha con formato MMM dd, yyyy
	 */
	public static SimpledateFormat getSdfFechaMH(Properties ctx) {
		SimpledateFormat format = null;
		if (isSpanishMX(ctx)) {
			format = new SimpledateFormat(ctx, "dd MMM yyyy HH:mm", Env.getLanguage(ctx).getLocale());
		} else {
			format = new SimpledateFormat(ctx, "MMM dd, yyyy HH:mm",Env.getLanguage(ctx).getLocale());
		}
		return format;
	}

	/**
	 * Devuelve una fecha con formato MMM, yyyy
	 */
	public static SimpledateFormat getSdfFechaMY_CDA(Properties ctx) {
		return new SimpledateFormat(ctx, "MMM, yyyy", Env.getLanguage(ctx).getLocale());
	}
	
	/**
	 * Devuelve una fecha con formato mmyy para validar tarjetas de credito
	 */
	public static SimpleDateFormat sdfMonthYear = new SimpleDateFormat("MMyy");
	
	/**
	 * Devuelve una fecha con formato yyyyMMdd
	 */
	public static SimpledateFormat getSdfFecha_CDA(Properties ctx) {
		return new SimpledateFormat(ctx, "yyyyMMdd", Env.getLanguage(ctx).getLocale());
	}
	
	/**
	 * Devuelve una fecha con formato yyyyddMM
	 */
	public static SimpledateFormat getSdfFecha_CDA2(Properties ctx) {
		return new SimpledateFormat(ctx, "yyyyddMM", Env.getLanguage(ctx).getLocale());
	}
	
	/**
	 * Devuelve una fecha con formato yyyyMMddhhmmaa
	 */
	public static SimpledateFormat getSdfFecha_CCD(Properties ctx) {
		return new SimpledateFormat(ctx, "yyyyMMddHHmmss", Env.getLanguage(ctx).getLocale());
	}
	
	/**
	 * Devuelve una fecha con formato "EEEEE, dd 'de' MMMMM yyy 'a las' hh:mm aaa"
	 */
	public static SimpledateFormat getSdfLargeDateTime(Properties ctx) {
		SimpledateFormat sdf = null;
		if (isSpanishMX(ctx)) {
			sdf = new SimpledateFormat(ctx, "EEEEE, dd 'de' MMMMM yyy 'a las' hh:mm aaa", Env.getLanguage(ctx)
					.getLocale());
		} else {
			sdf = new SimpledateFormat(ctx, "EEEEE, MMMMM dd, yyyy 'at' hh:mm aaa", Env.getLanguage(ctx).getLocale());
		}
		return sdf;
	}	
	
	/**
	 * Devuelve una fecha con formato "dd/MMMMM/yyyy"
	 */
	public static SimpledateFormat getSdfNormalDate(Properties ctx) {
		SimpledateFormat sdf = null;
		
		if(isSpanishMX(ctx)) {
			sdf =  new SimpledateFormat(ctx, "dd/MMMMM/yyyy", Env.getLanguage(ctx).getLocale());
		} else {
			sdf = new  SimpledateFormat(ctx, "MMMMM/dd/yyyy", Env.getLanguage(ctx).getLocale());
		}
		
		return sdf;
	}
	
	/**
	 * Devuelve una fecha con formato "dd/MMMMM/yyyy HH:mm"
	 */
	public static SimpledateFormat getSdfNormalDateTime(Properties ctx) {
		return new SimpledateFormat(ctx, "dd/MMMMM/yyyy HH:mm", Env.getLanguage(ctx).getLocale());
	}	
	
	public static DecimalFormat	decFormat					= new DecimalFormat("$#,##0.00");
	
	public static String getDecimalFormat(BigDecimal number) {
		return decFormat.format(number);
	}
	
	/**
	* Constante para el tipo de documento en el pedido automatico
	*/
	public static final String MOVEMENT = "MOVEMENT";
	
	/**
	 * Nombre del Tipo de Documento AR Receipt (Cobranza CxC)
	 */
	public static final String AR_RECEIPT = "AR Receipt";
	
	/**@deprecated
	 * Nombre del Tipo de Documento CASH JOURNAL para caja
	 */
	public static final String CASH_JOURNAL = "CASH JOURNAL";

	/**@deprecated
	* Constante muestra de pantalla resultado de proceso
	*/
	public static final String CORTE = "CORTE";
	
    /** @deprecated
    * Constante para Reportes
    */
    public static final String FACTURAS_EXT = "FACTURAS_EXT";
    
    /** @deprecated
    * Constante para la Receta
    */
    public static final String FACTURAS_CE = "FACTURAS_CE";
    
	/** @deprecated
	* Constantes para las busquedas de tipos de documentos
	* p.e. name LIKE %RECETA%
	*/
	public static final String CUESTIONARIO = "CUESTIONARIO";
	
	/**
	* Constante para la capacidad inicial para objetos StringBuilder/StringBuffer
	*/
	public static final int			INIT_CAPACITY_ARRAY			= 100;
	
	/**
	 * Segundos por minuto
	 */
	public static final int			_MIN						= 60;

	/**
	 * Segundos por hora
	 */
	public static final int			_HR							= 3600;

	/**
	 * Segundos por dia
	 */
	public static final int			_DIA						= 86400;

	/**
	 * Segundos por semana
	 */
	public static final int			_SEM						= 604800;
	
	public static final String		MINUTOS						= "MI";
	public static final String		HORAS						= "HR";
	public static final String		DIAS						= "DA";
	public static final String		SEMANAS						= "WK";
	public static final String		YEAR						= "YR";
	public static final String		MONTH						= "MO";
	
	public static final String REPPEDIDOS = "REPPEDIDOS";
	/**
	* Constante para value del registro exme_i_order.value cuandoes de Solicitud de Servicios (SS Solicitud Servicios)
	*/
	public static final String SOLICITUD_SERVICIOS  = "SS";
	
	/**
	* Constante para value del registro exme_i_order.value cuando es una confirmacion de servicios desde cita medica (CC Confirmacion desde Cita)
	* @deprecated It's not being referenced, should go by February's end
	*/
	public static final String CONFIRMACION_SERVICIOS = "CC";
	
	/** @deprecated
	* Constante reporte comprobante de cita medica
	*/
	public static final String CITAMEDICA = "CITAMEDICA";
	
	/**
	 * Constante para valor limite Superior de String
	 */
	public static final String LIM_SUP_STRING = "zzzzzzzzzz";
	
	/**
	 * Constante para valor limite Inferior de String
	 */
	public static final String LIM_INF_STRING = "0";
	
	/**
	 * Constante para validar si la tabla es de alto volumen.
	 */
	public static final int MAX_QUERY_RECORDS = 10000; // Expert: Lama
	
	/**
	 * Constante para verificar que una direccion email sea valida.
	 */
	public static final String EMAIL_PATTERN = "[A-Za-z0-9]+([-_]*[.]{0,1}[A-Za-z0-9]+)+[-_]*@{1}[A-Za-z0-9]+([-_]*[A-Za-z0-9]+)+([.]{1}[A-Za-z]+)+";
	/**
	 * Constante para verificar que una direccion email sea valida para textbox zk no le importa que el texto este vacio.
	 */
	public static final String EMAILWEB_PATTERN = "/([A-Za-z0-9]+([-_]*[.]{0,1}[A-Za-z0-9]+)+[-_]*@{1}[A-Za-z0-9]+([-_]*[A-Za-z0-9]+)+([.]{1}[A-Za-z]+)+)*/:"; 
	/**
	 * Constantes para el estilo de campos obligatorios
	 */
	public static final String LABELMANDATORY_STYLE = "white-space: nowrap;text-decoration:underline;";
	public static final String TEXTMANDATORY_STYLE = "background-color: #D2E7EE;";
	public static final String INACTIVE_STYLE = "font-weight:bold;color: #405D80;";//#003399

	/**
	 * Constante para estilo de PopUp de creación de paciente
	 */
	public static final String PATIENTPOPUP_STYLE ="show: slideDown({duration: 1000});hide: slideUp({duration: 1000})";
	/**
	 * Constante para determinar el numero de registros en una pagina
	 */
	public static final int RECORDS_ON_PAGE = 10; //ldelagarza
	
	/**
	 * Constante que nos indica los esquemas de intalacion
	 */
	public static final String INSTALLATION_SCHEMA_RENT = "rta";
	
	//PHR INIT
	/**
	 * Constantes que nos indican si los regsitros son validos o no (columna de Tabla isActive)
	 */
	public static final String		REG_ACTIVE					= "Y";
	public static final String		REG_NOT_ACTIVE				= "N";

	public static final String		MASA						= "MASS";
	public static final String		NOTSMOKER					= "NS";
	public static final String		PESO						= "PESO";
	public static final String		TALLA						= "TALLA";
	public static final String		HG_LG						= "HEIGHT/LENGTH";	
	
	//PHR END
	public static final String		STYLE_FONT_9px				= "font-size:9px";
	public static final String		STYLE_FONT_8px				= "font-size:8px";
	public static final String		STYLE_FONT_10px				= "font-size:10px;";
	public static final String		STYLE_FONT_11px				= "font-size:11px";
	public static final String		STYLE_FONT_12px				= "font-size:12px;";
	public static final String		STYLE_FONT_14px				= "font-size:14px;";
	public static final String		STYLE_FONT_20px				= "font-size:20px;";
	
	
	public static SimpledateFormat sdfFecha(Properties ctx) {
		SimpledateFormat format = new SimpledateFormat(ctx, "MM/dd/yyyy");
		if(isSpanishMX(ctx)) {
			format = new SimpledateFormat(ctx, "dd/MM/yyyy");
		}
		return format;
	}
	/** @deprecated use {@link Constantes#getSDFDateTime(Properties)}*/
	public static SimpledateFormat sdfFechaHora(Properties ctx) {
//		SimpledateFormat format = new SimpledateFormat(ctx, "MM/dd/yyyy HH:mm");
//		if(isSpanishMX(ctx)) {
//			format = new SimpledateFormat(ctx, "dd/MM/yyyy HH:mm");
//		}
		return getSDFDateTime(ctx);
	}

	/**
	 * Formatea a fechas dd/MM/yyyy
	 * Se elimino etiqueta deprecated pues en algunas consultas 
	 * se parsea en directo a este formato de fecha
	 * @return
	 * 
	 */
	public static SimpleDateFormat getSdfFecha() {
		return new SimpleDateFormat("dd/MM/yyyy");
	}

	public static SimpleDateFormat getSdfFechaInvertida() {
		return getCustom("yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * Formatea a horas (am/pm) hh:mm aa
	 * 
	 * @return
	 */
	public static SimpledateFormat getSdfHora(Properties ctx) {
		return new SimpledateFormat(ctx, "hh:mm aa");
	}

	/**
	 * Formatea a horas (am/pm) hh:mm aa
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSdfHora() {
		return getCustom("hh:mm aa");
	}

	/**
	 * Formatea a horas HH:mm
	 * 
	 * @return
	 */
	public static SimpledateFormat getSdfHora24(Properties ctx) {
		return new SimpledateFormat(ctx, "HH:mm");
	}

	/**
	 * Formatea a horas HH:mm
	 * @deprecated
	 * @return
	 */
	public static SimpleDateFormat getSdfHora24() {
		return getCustom("HH:mm");
	}
	
	/**
	 * Formatea a horas HH:mm
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSdfMinutos() {
		return getCustom("mm");
	}
	
	public static SimpleDateFormat getCustom(String format) {
		return new SimpleDateFormat(format);
	}

	/**
	 * Formatea a fecha y hora (24) dd/MM/yyyy HH:mm
	 * 
	 * @return
	 * @deprecated Usar {@link Constantes#getSDFDateTime(Properties)}
	 */
	public static SimpleDateFormat getSdfFechaHora() {
		return getCustom("dd/MM/yyyy HH:mm");
	}

	/**
	 * Formatea a fecha y hora (24) yyyy-MM-dd HH:mm
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSdfFechaHoraBD24() {
		return getCustom("yyyy-MM-dd HH:mm");
	}

	/**
	 * Formatea a fecha y hora (12) dd/MM/yyyy hh:mm aa
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSdfFechaHoraAmPm() {
		return getCustom("dd/MM/yyyy hh:mm aa");
	}

	/**
	 * Formatea a fecha y hora (12) dd/MM/yyyy hh:mm:ss aa
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSdfFechaHoraAmpliaAmPm() {
		return getCustom("dd/MM/yyyy hh:mm:ss aa");
	}
	
	/**
	 * Formatea a fecha YYYY-MM-DDTHH:MM:SS.FZ 
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSDFSurescripts() {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.0Z'", new Locale("en"));
	}

	/**
	 * Formatea un numero entero a dos digitos
	 * 
	 * @return
	 */
	public static DecimalFormat getDosDigitos() {
		return new DecimalFormat("00");
	}

	/**
	 * Formatea un numero con decimales a dos enteros.dos decimales.
	 * Principalmente para formateo de horas.
	 * 
	 * @return
	 */
	public static DecimalFormat getDfHora() {
		return new DecimalFormat("00.00");
	}
	
	/**
	 * Formatea a fecha YYYY-MM-DDTHH:MM:SS
	 * 
	 * @return
	 */
	public static SimpleDateFormat getSDFEstandar() {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", new Locale("es"));
	}
	
	public static final String[] arr1 = new String[] { "\u00E1", "\u00E9", "\u00ED", "\u00F3", "\u00FA", "\u00C1", "\u00C9", "\u00CD", "\u00D3", "\u00DA" };
	public static final String[] arr2 = new String[] { "a", "e", "i", "o", "u", "A", "E", "I", "O", "U" };
	public static final String[] arr3 = new String[] { "+", "-", "*", "/"};
	
	/**
	 * Constante para utilizar en la funcion translate en la base de datos Ej: " translate('texto', " + Constantes.TRANSLATE_VOCALS + ")"
	 */
	public static final String TRANSLATE_VOCALS = "\u0027\u00E1\u00E9\u00ED\u00F3\u00FA\u00C1\u00C9\u00CD\u00D3\u00DA\u0027, \u0027aeiouAEIOU\u0027";
	public static SimpledateFormat sdfFechaHoraSeg(Properties ctx) {
		SimpledateFormat format = new SimpledateFormat(ctx, "MM/dd/yyyy HH:mm:ss");
		if(isSpanishMX(ctx)) {
			format = new SimpledateFormat(ctx, "dd/MM/yyyy HH:mm:ss");
		}
		return format;
	}

	/** Percent: 100% */
	public static final String	PERCENT_100			= "100%";
	/** Percent: 99% */
	public static final String	PERCENT_99			= "99%";
	/** Percent: 98% */
	public static final String	PERCENT_98			= "98%";
	/** Percent: 95% */
	public static final String	PERCENT_95			= "95%";
	/** Percent: 90% */
	public static final String	PERCENT_90			= "90%";
	/** Percent: 85% */
	public static final String	PERCENT_85			= "85%";
	/** Percent: 80% */
	public static final String	PERCENT_80			= "80%";
	/** Percent: 70% */
	public static final String	PERCENT_70			= "70%";
	/** Percent: 65% */
	public static final String	PERCENT_65			= "65%";
	/** Percent: 60% */
	public static final String	PERCENT_60			= "60%";
	/** Percent: 55% */
	public static final String	PERCENT_55			= "55%";
	/** Percent: 50% */
	public static final String	PERCENT_50			= "50%";
	/** Percent: 40% */
	public static final String	PERCENT_45			= "45%";
	/** Percent: 40% */
	public static final String	PERCENT_40			= "40%";
	/** Percent: 35% */
	public static final String	PERCENT_35			= "35%";
	/** Percent: 30% */
	public static final String	PERCENT_30			= "30%";
	/** Percent: 20% */
	public static final String	PERCENT_25			= "25%";
	/** Percent: 20% */
	public static final String	PERCENT_20			= "20%";
	/** Percent: 15% */
	public static final String	PERCENT_15			= "15%";
	/** Percent: 12% */
	public static final String	PERCENT_12			= "12%";
	/** Percent: 11% */
	public static final String	PERCENT_11			= "10%";
	/** Percent: 10% */
	public static final String	PERCENT_10			= "10%";
	/** Percent: 8% */
	public static final String	PERCENT_8			= "8%";
	/** Percent: 7% */
	public static final String	PERCENT_7			= "7%";
	/** Percent: 6% */
	public static final String	PERCENT_6			= "6%";
	/** Percent: 5% */
	public static final String	PERCENT_5			= "5%";
	/** Percent: 4% */
	public static final String	PERCENT_4			= "4%";
	/** Percent: 3% */
	public static final String	PERCENT_3			= "3%";
	
	public static final String	SQL_AND				= " AND ";
	
	/** 'top' */
	public static final String	TOP						= "top";
	/** 'left' */
	public static final String	LEFT					= "left";
	/** 'right' */
	public static final String	RIGHT					= "right";
	/** 'center' */
	public static final String	CENTER					= "center";
	/** 'MIDDLE' */
	public static final String	MIDDLE					= "middle";
	
	
	/** Width 300 pixels: '300px' */
	public static final String	WIDTH300			= "300px";
	/** 'none' */
	public static final String	NONE				= "none";
	/** 'normal' */
	public static final String	NORMAL				= "normal";
	
	public static final String	REFERPHYSICIAN		= "REFERPHYSICIAN";
	public static final String	REFERPHYSICIANMED	= "REFERPHYSICIANMED";
	public static final String	REFERINPATIENT		= "REFERINPATIENT";
	public static final String	EXTERNALSERVICE		= "EXTERNALSERVICE";
	public static final String	MEDICALAPPT			= "MEDICALAPPT";
	/** New Line: '\n' */
	public static final String	NEWLINE				= "\n";
	/** Mandatory: '*' */
	public static final String	MANDATORY			= "*";
	/** Wildcard: '%' */
	public static final String	WILDCARD			= "%";
	/** Ampersand: '&' */
	public static final String	AMPERSAND			= "&";
	/** Equal: '=' */
	public static final String	EQUAL				= "=";

	// Constantes Medical Prescription / Medication Plan 
	
	/** PDDNG_BTTM_1PRCNT = padding-bottom:1%; */
	public static final String	PDDNG_TOP_1PRCNT	= "padding-top:1%;";
	/** PDDNG_BTTM_1PRCNT = padding-bottom:1%; */
	public static final String	PDDNG_BTTM_1PRCNT	= "padding-bottom:1%;";
	/** PDDNG_LFT_2PRCNT = padding-left:2%; */
	public static final String	PDDNG_LFT_2PRCNT	= "padding-left:2%;";
	/** PDDNG_LFT_1PRCNT = padding-left:1%; */
	public static final String	PDDNG_LFT_1PRCNT	= "padding-left:1%;";
	/** ERROR_LABEL = font-weight:bold;color:red */
	public static final String	ERROR_LABEL			= "font-weight:bold;color:#FF0000";
	/** SUCCESS_LABEL = font-weight:bold;color:green */
	public static final String	SUCCESS_LABEL		= "font-size:12px;font-weight:bold;color:#2E8B57";
	/** BACKGROUND = "background-color:LightGoldenRodYellow */
	public static final String	BACKGROUND			= "background-color:#FAFAD2;";
	/** BORDER = "border:1px solid black; */
//	public static final String	BORDER				= "border:1px solid black;";
	/** BORDER_NONE = "border : none" */
	public static final String	BORDER_NONE			= "border: none;";
	/** (Autenticated Medication) AUTHENTICATED = background: none repeat scroll 0 0 #D1D798;*/
	//public static final String AUTHENTICATED = "background: none repeat scroll 0 0 #D1D798;";
	/**  (Verbal Order Medication) VERBAL_ORDER = background: none repeat scroll 0 0 #BAAFD7;*/
	//public static final String VERBAL_ORDER = "background: none repeat scroll 0 0 #BAAFD7;";
	/** REVIEWED = "background: none repeat scroll 0 0 #9CC8E8;*/
	public static final String REVIEWED = "font-style: italic;";
	/** (Dose) ADMINISTERED = color:grey;*/
	public static final String ADMINISTERED = "color:grey;";
	/** (Dose) ADMINISTERED = font-weight:bold;background: none repeat scroll 0 0 #F5804E;*/
//	public static final String PRESCRIBED = "font-weight:bold;background: none repeat scroll 0 0 #F5804E;";	
	/** Dose AUTO_CANCELLED = background: none repeat scroll 0 0 #D1D3D4; */
	public static final String	AUTO_CANCELLED		= "background: none repeat scroll 0 0 #D1D3D4;";
	public static final String	AUTO_STOPPOLICY		= "background-color: #F1F574;";
	public static final String	AUTO_STOPPED		= "font-family:Arial;background: url(images/bg_AutoStop.jpg) repeat-x top;";
//	/** NOTED = "font-weight:bold; */
//	public static final String	NOT_NOTED			= "font-weight:bold;";
	/** not NOT_AUTHENTICATED = color: #1760A9; */
	public static final String	NOT_AUTHENTICATED	= "color: #1760A9;";
	
	/** Style for LisTab **/
	public static final String	LISTTAB_STYLE_CELL	= "border-bottom: 1px solid #CFCFCF;";
	public static final String	LISTTAB_STYLE_LABEL	= "color: #363636;font-size: 12px;font-weight: bold;padding: 4px 2px;align:center;";
	/**Style for ListTab font size 10**/
	public static final String	LISTTAB_STYLE_LABEL_10	= "color: #002e3e;font-size: 10px;font-weight: bold;padding: 4px 2px;align:center;";
	/** OVERFLOW_Y = overflow-y:auto */
	public static final String	OVERFLOW_Y			= "overflow-y:auto;";
	/** CSS: 'overflow:auto' */
	public static final String	OVERFLOW			= "overflow:auto;";
	/** Align: 'overflow:autoalign:center' */
	public static final String	OVERFLOW_CENTER		= "overflow:auto;align:center";

	public static final String	ESCAPE_CHARS		= "[\\\\+\\-\\!\\(\\)\\:\\^\\]\\{\\}\\~\\?]";
	/** Tilde: '~' */
	public static final String	REPLACE_CHARS		= "~";
	/** Caret: '^' */
	public static final String	CARET				= "^";
	/** Align: 'top' */
	public static final String	ALIGN_TOP			= "top";
	/** vAlign: 'top' */
	public static final String	VALIGN_TOP			= "vertical-align:text-top";
	/** Align: 'align:right' */
	public static final String	ALIGN_RIGHT			= "align:right";
	/** Align: 'align:center' */
	public static final String	ALIGN_CENTER		= "align:center";
	/** Boolean value: 'true' */
	public static final String	STR_TRUE			= "true";
	/** Boolean value: 'false' */
	public static final String	STR_FALSE			= "false";

	/** Constantes para las busquedas de tipos de documentos. p.e. name LIKE %VACUNA% */
	public static final String	VACUNA				= "VACUNA";

	public static final String	STR_ADD				= "ADD";
	public static final String	STR_DEL				= "DEL";
	public static final String	STR_CLOSE			= "CLOSE";
	public static final String	STR_LIST			= "LIST";
	public static final String	STR_OK				= "OK";
	public static final String	OCURRENCE			= "Ocurrence";
	public static final String	SPAN				= "Span";
	public static final String	CONDITION			= "Condition";
	public static final String	VALUE				= "Value";
	public static final String	WINDOW				= "Window";

	/** Zk Mold: '3d' */
	public static final String	MOLD_3D				= "3d";
	/** Zk Mold: 'paging' */
	public static final String	MOLD_PAGING			= "paging";
	/** Zk Mold: 'select' */
	public static final String	MOLD_SELECT			= "select";
	/** Zk Mold: 'accordion' */
	public static final String	MOLD_ACCORDION		= "accordion";
	/** Zk Mold: button mold 'trendy' */
	public static final String	MOLD_TRENDY		= "trendy";
	/** 'onEvent' */
	public static final String	ON_EVENT			= "onEvent";
	/** 'actualizacion ABCList' */
	public static final String	ON_ABCLIST			= "onABCList";
	/** Format: '$#,##0.00' */
	public static final String	FORMAT_AMOUNT		= "$#,##0.00";
	/** Format: '#,##0.00' */
	public static final String	FORMAT				= "###,##0.00";
	/** Zk Type: 'password' */
	public static final String	TYPE_PASSWORD		= "password";
	
	// Product Search
	public static final String	Type_All			= "All";
	public static final String	Type_Med			= "MED";
	public static final String	Type_Study			= "SERV";
	public static final String	Type_Procedure		= "PROC";
	public static final String	Type_ProcedurePack	= "PROCPACK";
	public static final String	Type_StudyPack		= "SERVPACK";
	// Paquetes y Minipaquetes. Twry
	public static final String	Type_Pack			= "PACK";
	// Solicitud de servicios Twry
	public static final String	Type_Service		= "SERVALL";
	// Procedures en Abstracting
	public static final String	Type_HCPCS			= "HCPCS";
	public static final String	Type_NOTHCPCS		 = "HCPCSNOT";
	public static final String	SubType_PROF		= "PROF";
	public static final String	SubType_INS			= "INS";
	
	
	public static final String	FORMAT_RATE   		= "###,##0.0000";
	
	public static final String size(int pixels){
		return pixels+"px";
	}
	public static final String percent(int percent){
		return percent+"%";
	}
	
	/** CSS: 'cursor:pointer' */
	public static final String	CURSOR_POINTER	= "cursor:pointer;";
	/** CSS: 'font-weight:bold' */
	public static final String	FONT_BOLD		= "font-weight:bold;";
	/** CSS: 'text-align:left' */
	public static final String	ALIGN_LEFT		= "text-align:left;";
	/** CSS: 'text-align:right' */
	public static final String	TEXTALIGN_RIGHT		= "text-align:right;";
	public static final String	TEXTALIGN_CENTER		= "text-align:center;";
	
	/** Return translated value for boolean value : Yes/No, Si/No, etc */
	public static String yesNoStr(Properties ctx, boolean value){
		return  Msg.translate(ctx, value ? "Yes" : "No");
	}
	
	public static final String	ENTITY_TYPE_EXME	= "EXME";

	public static final String	IMG_ALERT_16		= "/images/16x16/Alert_16.png";
	public static final String	IMG_ALERT_24		= "/images/24x24/Alert_24.png";
	public static final String	IMG_ATTACH_16		= "/images/16x16/Attachment_16.png";
	public static final String	IMG_ATTACH_24		= "/images/24x24/Attachment_24.png";
	public static final String	IMG_BACK_16			= "/images/16x16/Back_16.png";
	public static final String	IMG_BACK_24			= "/images/24x24/Back_24.png";
	public static final String	IMG_CANCEL_16		= "/images/16x16/Cancel_16.png";
	public static final String	IMG_CANCEL_24		= "/images/24x24/Cancel_24.png";
	public static final String	IMG_CLEAN_16		= "/images/16x16/Clean_16.png";
	public static final String	IMG_CLEAN_24		= "/images/24x24/Clean_24.png";
	public static final String	IMG_COPY_16			= "/images/16x16/Copy_16.png";
	public static final String	IMG_COPY_24			= "/images/24x24/Copy_24.png";
	public static final String	IMG_DELETE_16		= "/images/16x16/Delete_16.png";
	public static final String	IMG_DELETE_24		= "/images/24x24/Delete_24.png";
	public static final String	IMG_DOWN_16			= "/images/16x16/Down_16.png";
	public static final String	IMG_EDIT_16			= "/images/16x16/Edit_16.png";
	public static final String	IMG_EDIT_24			= "/images/24x24/Edit_24.png";
	public static final String	IMG_ENDRECORD_16	= "/images/16x16/EndRecord_16.png";
	public static final String	IMG_FAVORITE_16		= "/images/16x16/Favorite_16.png";
	public static final String	IMG_FAVORITEGRAY_16	= "/images/16x16/FavoriteGray_16.png";
	public static final String	IMG_FIND_16			= "/images/16x16/Find_16.png";
	public static final String	IMG_FIND_24			= "/images/24x24/Find_24.png";
	public static final String	IMG_HELP_16			= "/images/16x16/Help_16.png";
	public static final String	IMG_HELP_24			= "/images/24x24/Help_24.png";
	public static final String	IMG_IGNORE_16		= "/images/16x16/Ignore_16.png";
	public static final String	IMG_IGNORE_24		= "/images/24x24/Ignore_24.png";
	public static final String	IMG_MULTI_16		= "/images/16x16/Multi_16.png";
	public static final String	IMG_MULTI_24		= "/images/24x24/Multi_24.png";
	public static final String	IMG_NEW_16			= "/images/16x16/New_16.png";
	public static final String	IMG_NEW_24			= "/images/24x24/New_24.png";
	public static final String	IMG_NEXT_16			= "/images/16x16/Next_16.png";
	public static final String	IMG_NEXT_24			= "/images/24x24/Next_24.png";
	public static final String	IMG_OK_16			= "/images/16x16/Ok_16.png";
	public static final String	IMG_OK_24			= "/images/24x24/Ok_24.png";
	public static final String	IMG_PRINT_16		= "/images/16x16/Print_16.png";
	public static final String	IMG_PRINT_24		= "/images/24x24/Print_24.png";
	public static final String	IMG_PROCESS_16		= "/images/16x16/Process_16.png";
	public static final String	IMG_PROCESS_24		= "/images/24x24/Process_24.png";
	public static final String	IMG_REFRESH_16		= "/images/16x16/Refresh_16.png";
	public static final String	IMG_REFRESH_24		= "/images/24x24/Refresh_24.png";
	public static final String	IMG_SAVE_16			= "/images/16x16/Save_16.png";
	public static final String	IMG_SAVE_24			= "/images/24x24/Save_24.png";
	public static final String	IMG_STARTRECORD_16	= "/images/16x16/StartRecord_16.png";
	public static final String	IMG_UP_16			= "/images/16x16/Up_16.png";
	public static final String	IMG_CALCULATOR_24	= "/images/24x24/Calculator_24.png";
	public static final String	IMG_POST_16			= "/images/postit-16.png";
	public static final String	IMG_REPORT_16		= "/images/16x16/Report_16.png";
	public static final String	IMG_FLECHAWF		= "/images/24x24/flecha_.png";
	public static final String	IMG_DOWN24			= "/images/24x24/Down_24.png";
	public static final String	IMG_UP24			= "/images/24x24/Up_24.png";
	public static final String	IMG_LOCK_16			= "/images/16x16/Lock_16.png";
	public static final String	IMG_LOCK_24			= "/images/24x24/Lock_24.png";
	public static final String	IMG_LOCKX_16		= "/images/16x16/LockX_16.png";
	public static final String	IMG_LOCKX_24		= "/images/24x24/LockX_24.png";
	public static final String  IMG_SENDMAIL_24     = "/images/24x24/Sendmail_24.png";
	public static final String  IMG_NEWMESSAGE_24   = "/images/24x24/newmessage.png";
//	public static final String  IMG_INFOBUTTON_24   = "/images/24x24/InfoButton.png";
//	public static final String  IMG_INFOBUTTON_16   = "/images/16x16/InfoButton.png";
//	public static final String  IMG_INFOBUTTON_32   = "/images/32x32/InfoButton.png";
	
	public static String getImageURL(String name, int size) {
		final StringBuilder str = new StringBuilder();
		str.append("/images/");
		if (size > 0) {
			str.append(size).append("x").append(size).append("/");
		}
		str.append(name);
		if (size > 0) {
			str.append("_").append(size);
		}
		str.append(".png");
		return str.toString();
	}
	
	public static final int CLAIM_MISSINGPRICES      = 1;
	public static final int CLAIM_MISSINGREVCODE     = 2;
	public static final int CLAIM_MISSINGCHARGES     = 3;
	

	/**
	 * CONSTANTE ERROR
	 */
	public static final String ERROR = "ERROR";/**
	
	 * CONSTANTE NULL
	 */
	public static final String NULL = "NULL";
	
	/**ID DE LA FORMA DE FORM SEARCH */
//	public static final String FORM_SECTION = "1000357";
	
//	public static final String FORM_SECTIONSEARCH = "1000346";
	
	/** MENSAJES PARA EXCEPTIONS CARD 93 **/
	// La opcion sera depreciada cuando se termine Billing Queue
	public static final String NOINS_1 = "NO INSURANCES - FIRST CHECK INSURANCE COVERAGE , SECOND CODING NOT INITIATED";

	public static final String NOINS_2 = "NO INSURANCES - FIRST CHECK INSURANCE COVERAGE , SECOND STATUS NOT APPLICABLE, BUT ENCOUNTER HAS CHARGES";
	
	public static final String NOINS_3 = "NO INSURANCES - FIRST CHECK INSURANCE COVERAGE , SECOND CODING INCOMPLETE";
	
	public static final String STAT_1 = "STATEMENTS - CODING NOT INITIATED";

	public static final String STAT_21 = "STATEMENTS - STATUS NOT APPLICABLE, BUT ENCOUNTER HAS CHARGES";
	
	public static final String STAT_22 = "STATEMENTS - STATUS NOT APPLICABLE, IF THIS IS OK  CHANGE THE STEP TO DEFAULT TO AVOID STATEMENT GENERATION";
	 
	public static final String STAT_3 = "STATEMENTS - CODING INCOMPLETE";
	
	public static final String CLAIM_1 = "CLAIMS - CODING NOT INITIATED";

	public static final String CLAIM_21 = "CLAIMS - STATUS NOT APPLICABLE, BUT ENCOUNTER HAS CHARGES";
	
	public static final String CLAIM_22 = "CLAIMS - NOT APPLICABLE, IF THIS IS OK, REMOVE THE INSURANCES IN BILLING DATA AND CHANGE THE STEP TO DEFAULT";
	
	public static final String CLAIM_3 = "CLAIMS - CODING INCOMPLETE";

	
	
	public static final int CREATE = 0;
	
	public static final int CLOSE = 1;
	
	public static final int CODING = 2;
	
	public static final int CASHIERING = 3;
	
	public static final int BILLING = 4;
	
	public static final int ERRORPROF = 9;
	
	public static final int REACTIVATE = 7;
	
	public static final int ABSTRACTING = 8;
	
	public static final int ERRORINST = 5;
	
	public static final int CHARGES = 10;
	
	public static final int BILLING_EXT = 11;
	
	public static final int EDOCTA = 12;
	
	public static final int LATECHARGES = 15;

	public static final String IN = "IN";
	
	public static final String NOTIN = "NOT IN";
	
	
	//Constante para el SMTP Host de AD_Client
	public static final String SMTP_HOST = "smtp.1and1.com";
	/** Cargos a cuenta paciente con la conciliación */
	public static final int CHARGESCONS = 13;
	/** Match de cargos / paquetes */
	public static final int MATCH = 14;
	/** RFC_NACIONAL para EXME_ConfigFE */
	public static final String RFC_NACIONAL="XAXX010101000";
	/** RFC_EXTRANJERO para EXME_ConfigFE */
	public static final String RFC_EXTRANJERO="XEXX010101000";
	/** CARACTERES PERMITIDOS para EXME_ConfigFE */
	public static final String CARAC_PERMITIDOS="1234567890abcdefghijklmnopqrstuvwxyz&";
	
	public static final String	isMX = "MX";
	/** tipos de procesos de calculo de precios. consulta externa*/
	public final static String PVCE = "PVCE";
	/** Hospitalizacion/Ambulatorio */
	public final static String PVH = "PVH";
	/** CODIGO DE PARENTESTO SELF **/
	public final static String SELF_ = "SEL";
	/**
	* Constante para Authorization
	*/
	public static final String ON_SAVEAUTH = "onSaveAuth";
	public static final String ICD9DX = "ICD9CM_DX";
	// public static final String ICD9CME = "ICD9CM_E";
	public static final String ICD9CMPR = "ICD9CM_PR";
	public static final String CPT4 = "CPT4_TABULAR";
	public static final String HCPCS = "HCPCS_TABULAR";
	public static final String COPYICD9 = "COPYICD9";
	

	public final static int INITIAL =0;
	public final static int PAYMENT = 1;
	public final static int COPAY = 2;
	public final static int DEDUCTIBLE = 3;
	public final static int COINSURANCE = 4;
	public final static int OTHER = 5;
	public final static int BALANCE = 6;
	public final static int PAYBALANCE = 7;
	
	/**
	 * Lista de Imagenes sacadas del Tema Holo de Android
	 * <br/>
	 * <b>Nota: No todos los tamaños estan soportados, al ser asi no se mostrara la imagen del src</b>
	 * @author pedro
	 *
	 */
	public enum HoloImage{
		CONTENT_TEMPLATE("5_content_template.png"),
		CONTENT_ORDERSET("5_content_ordersets.png"),
		NAVIGATION_ACCEPT_GRAY("1_navigation_accept_gray.png"),
		NAVIGATION_ACCEPT_GREEN("1_navigation_accept_green.png"),
		NAVIGATION_CANCEL("1_navigation_cancel.png");
		
		private String imageName;		
		private final String BASE_SOURCE = "/images/holo/";
		
		private HoloImage(String imageName){
			this.imageName = imageName;
		}
		public String getSrc(int size){
			return BASE_SOURCE + size + "/" + imageName;
		}
	}
	
	public static List<String>getLstMail(){
		List<String> lista = new ArrayList<String>();
		lista.add("gcamargo@ecaresoft.com.mx");
		lista.add("dmendoza@ecaresoft.com.mx");
		lista.add("mrojas@ecaresoft.com.mx");
		lista.add("jcantu@ecaresoft.com.mx");
		lista.add("beufracio@ecaresoft.com.mx");
		lista.add("mloera@ecaresoft.com.mx");
		return lista;
	}
	
	/** Constantes para la definicion de pasos en traspaso entre almacenes */
	public final static int SOLICITUD    = 1;
	public final static int SURTIDO      = 2;
	public final static int CONFIRMACION = 3;
	public final static int DEVOLUCION   = 4;
	
	public static final int EXTPART = 0;
	public static final int EXTASEG = 1;
	
	public static final String NEW = "newRecord";
	public static final String EDIT = "editRecord";
	public static final String OK = "ok";
	public static final String SAVE = "save";
	public static final String DELETE = "delete";
	public static final String REFRESH = "refresh";
	public static final String PRINT = "print";
	public static final String BACK = "back";
	public static final String CLEAN = "clean";
	public static final String COPY = "copy";
	public static final String PROCESS = "process";
	public static final String HELP = "help";
	public static final String FIND = "find";
}

