package org.compiere.model.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MDashProMujer;
import org.compiere.util.CLogger;

/**
 * Proyecto Pro Mujer
 * http://control.ecaresoft.com/epic/82/
 * Transferencia de información entre NIMBO (eCareSoft) y FIN+
 * 
 * @author abautista
 *         Modificado por Lama, Feb 2015
 */
public class ResponseProMujer implements IDHPatientInfo {

	public static CLogger	log		= CLogger.getCLogger(ResponseProMujer.class);
	public static String	mask1	= "yyyyMMddhhmm";
	public static String	mask2	= "yyyyMMddhhmmss";
	public static String	mask3	= "yyyyMMdd";

	/**
	 * Fecha 20141110113942 con mascara yyyyMMddhhmm = 2014-11-13 04:42:00
	 * Se evalua la longitud del string antes de converir el string a fecha
	 */
	public static Timestamp getAsTimestamp(String date) {
		Date res = null;
		if (StringUtils.isNotBlank(date)) {
			try {
				if (date.length() == mask3.length()) {// hasta dia
					res = new SimpleDateFormat(mask3).parse(date);
				} else if (date.length() == mask1.length()) {// hasta horas/minutos
					res = new SimpleDateFormat(mask1).parse(date);
				} else if (date.length() == mask2.length()) {// incluye segundos
					res = new SimpleDateFormat(mask2).parse(date);
				} else if (date.length() > mask2.length()) {// si incluye milisegundos
					res = new SimpleDateFormat(mask2).parse(date.substring(0, mask2.length()));
				} else {// el formato es menor
					log.log(Level.SEVERE, "Formato no considerado: " + date);
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "Formato erroneo fecha: " + date);
			}
		}
		return res == null ? null : new Timestamp(res.getTime());
	}
	
	public static String getDataAsString(Properties innerData, String dataName) {
		String value = null;
		if (StringUtils.isNotEmpty(dataName)) {
			dataName = dataName.toUpperCase();
			if (innerData != null && innerData.containsKey(dataName)) {
				value = innerData.getProperty(dataName);
			} else {
				log.log(Level.CONFIG, "No existe key: " + dataName);
			}
		}
		return value;
	}

	private String		statusCode;
	private String		errorMsg;

	private String		data;
	private Properties	innerData;

	public boolean isSuccess() {
		log.log(Level.INFO, "ResponseProMujer", toString());
		boolean success = MDashProMujer.OK.equals(getStatusCode());
		if (success) {
			log.log(Level.FINE, "ResponseProMujer", getRespuesta());
		} else {
			log.log(Level.SEVERE, "ResponseProMujer", getErrorMsg());
		}
		return success;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMsg() {
		if (errorMsg == null) {
			errorMsg = getDataAsString("ERROR");
		}
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setData(String data) {
		innerData = new Properties();
		this.data = data;
		if (data != null) {
			data = data.replace(":=|", ":=0|");

			if (this.data.endsWith(":=")) {
				this.data = this.data + "0";
			}
			String[] segmentos = data.split("\\|");

			for (int i = 1; i < segmentos.length; i++) {
				String[] elementos = segmentos[i].split(":=");
				String key = elementos[0];
				StringBuilder value = new StringBuilder("");
				for (int j = 1; j < elementos.length; j++) {
					value.append(elementos[j]);
				}
				innerData.put(key.toUpperCase(), value.toString());
			}
		}
	}

	private String getDataAsString(String dataName) {
//		String value = null;
//		if (StringUtils.isNotEmpty(dataName)) {
//			dataName = dataName.toUpperCase();
//			if (innerData != null && innerData.containsKey(dataName)) {
//				value = innerData.getProperty(dataName);
//			} else {
//				log.log(Level.CONFIG, "No existe: " + dataName + " "+innerData==null?"":innerData.toString());
//			}
//		}
		return getDataAsString(innerData, dataName);
	}

	public Timestamp getDataAsTimestamp(String date) {
		return getAsTimestamp(getDataAsString(date));
	}

	public String getRespuesta() {
		return getDataAsString("RESPUESTA");
	}

	public String getCodUsuario() {
		return getDataAsString("CODUSUARIO");
	}

	public String getNombres() {
		return getDataAsString("NOMBRES");
	}

	public String getPaterno() {
		return getDataAsString("PATERNO");
	}

	public String getMaterno() {
		return getDataAsString("MATERNO");
	}

	public String getSexo() {
		return getDataAsString("SEXO");
	}

	public String getDI() {
		return getDataAsString("DI");
	}

	public String getCodDocIden() {
		return getDataAsString("CODDOCIDEN");
	}

	public Timestamp getFechaNacimiento() {
		return getDataAsTimestamp("FECHANACIMIENTO");
	}

	public String getEstadoCivil() {
		return getDataAsString("ESTADOCIVIL");
	}

	public String getDomicilio() {
		return getDataAsString("DOMICILIO");
	}

	public String getTelefono() {
		return getDataAsString("TELEFONO");
	}

	public String getCodCF() {
		return getDataAsString("CODCF");
	}

	public String getTipoCredito() {
		return getDataAsString("TIPOCREDITO");
	}

	public String getBancaComunal() {
		return getDataAsString("BANCACOMUNAL");
	}

	public String getNombreAsesor() {
		return getDataAsString("NOMBREASESOR");
	}

	public String getCiclo() {
		return getDataAsString("CICLO");
	}

	public Timestamp getFechaDesem() {
		return getDataAsTimestamp("FECHADESEM");
	}

	public Timestamp getFechaUltPago() {
		return getDataAsTimestamp("FECHAULTPAGO");
	}

	public String getApellidoConyugue() {
		return getDataAsString("APELLIDOCONYUGUE");
	}

	public String getNombreConyugue() {
		return getDataAsString("NOMBRECONYUGUE");
	}

	public String getDIConyugue() {
		return getDataAsString("DICONYUGUE");
	}

	public String getCodDocIdenConyugue() {
		return getDataAsString("CODDOCIDENCONYUGUE");
	}

	@Override
	public String getSOCreditStatus() {
		return getRespuesta();
	}
	public void setValue(Object key, Object value) {
		innerData.put(key, value);
	}
}
