package com.ecaresoft.util;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MMessage;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * 
 * @author odelarosa
 * 
 */
public class Error {
	/**
	 * Error de configuraci&oacuten, por ejemplo <b>Falta la Configuraci&oacuten de Precios</b>
	 */
	public static final int CONFIGURATION_ERROR = 0;
	/**
	 * Error manejado, un NumberFormatException, NullPointer, etc.
	 */
	public static final int EXCEPTION_ERROR = 1;
	/**
	 * Error no esperado
	 */
	public static final int UNKNOWN_ERROR = 2;
	/**
	 * Error de validaci&oacuten, por ejemplo <b>El precio no debe ser menor o igual a cero</b>
	 */
	public static final int VALIDATION_ERROR = 3;

	/**
	 * Obtiene el mensaje de base de datos
	 * 
	 * @param msg
	 *            Mensaje de base de datos (Rodeado por @)
	 * @return Mensaje de base de datos si existe, si no, regresa el mismo mensaje
	 */
	public static String getDbMsg(String msg) {
		if (StringUtils.startsWith(msg, "@") && StringUtils.endsWith(msg, "@")) {
			int msgId = MMessage.getAD_Message_ID(Env.getCtx(), StringUtils.replace(msg, "@", StringUtils.EMPTY));
			if (msgId > 0) {
				return MMessage.getMessage(Env.getCtx(), msgId, Env.getContext(Env.getCtx(), Env.LANGUAGE));
			} else {
				return msg;
			}
		} else {
			return msg;
		}
	}

	private int code;

	private String info;

	/**
	 * Representación de un error del sistema
	 * 
	 * @param code
	 *            C&oacutedigo de error, puede ser {@link #CONFIGURATION_ERROR}, {@link #EXCEPTION_ERROR}, {@link #UNKNOWN_ERROR}, {@link #VALIDATION_ERROR}
	 * @param info
	 *            Mensaje de informaci&oacuten detallando el error
	 */
	public Error(int code, String info) {
		this.code = code;
		this.info = info;
	}

	public int getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (CONFIGURATION_ERROR == getCode()) {
			return Utilerias.getAppMsg(Env.getCtx(), "msj.error.configuracion", getDbMsg(info)) + "</br>";
		} else if (EXCEPTION_ERROR == getCode()) {
			return Utilerias.getAppMsg(Env.getCtx(), "msj.error.sistema", getDbMsg(info)) + "</br>";
		} else if (VALIDATION_ERROR == getCode()) {
			return Utilerias.getAppMsg(Env.getCtx(), "msj.error.validacion", getDbMsg(info)) + "</br>";
		} else if (UNKNOWN_ERROR == getCode()) {
			return Utilerias.getAppMsg(Env.getCtx(), "msj.error.desconocido", getDbMsg(info)) + "</br>";
		} else {
			return getDbMsg(info) + "</br>";
		}
	}

}
