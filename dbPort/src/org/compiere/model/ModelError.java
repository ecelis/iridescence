package org.compiere.model;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.ExpertException;
import org.compiere.util.Utilerias;


public class ModelError  {
	/** Log								*/
	private static CLogger		slog = CLogger.getCLogger (ModelError.class);
	/**
	 * Ya no permite continuar con el proceso
	 */
	public static final int TIPOERROR_Error = 1;
	
	/**
	 * Entra dentro del bloque catch  ya no sigue con la tarea
	 */
	public static final int TIPOERROR_Excepcion = 2;
	
	/**
	 * Algun dato esta incorrecto o falta permite continuar con la tarea
	 * Validacion
	 */
	public static final int TIPOERROR_Pregunta = 3;
	
	/**
	 * Falta alguna configuracion permite continuar con la tarea
	 * Configuraciones
	 */
	public static final int TIPOERROR_Informativo = 4;
	
	/**
	 * Algun dato esta incorrecto o falta permite continuar con la tarea
	 * Validacion
	 */
	public static final int TIPOERROR_Exclamacion = 5;
	
	/**
	 * Constructor
	 * @param tipo
	 * @param msj
	 * @param valor
	 */
	public ModelError(String msj){
		super();
		this.tipoError = TIPOERROR_Error;
		this.mensaje = msj;
	}
	
	/**
	 * Constructor
	 * @param tipo
	 * @param msj
	 * @param valor
	 */
	public ModelError(int tipo, String msj, Object... valor){
		super();
		this.tipoError = tipo;
		this.mensaje = msj;
		
		if(valor!=null && valor.length>0){
			this.param = valor;
		}
		
		slog.saveError(Utilerias.getMsg(Env.getCtx(), msj),msj);
	}
	
	/**
	 * Constructor
	 * @param traduccion Requiere que el mensaje tome el idioma de login
	 * @param tipo Error, informacion, pregunta ..
	 * @param msj  mensaje
	 * @param valor valores adicionales que contempla el mensaje
	 */
	public ModelError(boolean traduccion, int tipo, String msj, Object... valor){
		super();
		this.tipoError = tipo;
		this.mensaje = msj;
		this.traduccion = traduccion;
		if(valor!=null && valor.length>0)
		this.param = valor;
	}
	
	private String  AD_Lenguaje = null;
	private int       tipoError = 0;
	private String      mensaje = null;
	private Object[]      param = null;
	private boolean  traduccion = true;
	
	public boolean isRequiereTraduccion() {
		return traduccion;
	}

	public void setTraduccion(boolean traduccion) {
		this.traduccion = traduccion;
	}

	public String getAD_Lenguaje() {
		return AD_Lenguaje;
	}
	public void setAD_Lenguaje(String aDLenguaje) {
		AD_Lenguaje = aDLenguaje;
	}
	public int getTipoError() {
		return tipoError;
	}
	public void setTipoError(int tipoError) {
		this.tipoError = tipoError;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Object[] getParam() {
		return param;
	}
	public void setParam(Object[] param) {
		this.param = param;
	}
	
	public static String getMsgError(final Properties ctx, final List<ModelError> lst){
		final List<ModelError> errors = lst;
		final StringBuilder str = new StringBuilder();
		for (ModelError error : errors) {
			if (error.getParam() != null
					&& error.getParam().length > 0) {
				str.append(
						Utilerias.getAppMsg(ctx,
								error.getMensaje(),
								error.getParam())).append("\n");
			} else {
				str.append(
						Utilerias.getMessage(ctx, null,
								error.getMensaje())).append(
										"\n");
			}
		}
		return str.toString();
	}
}
