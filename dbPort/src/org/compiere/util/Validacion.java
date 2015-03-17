/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.compiere.model.MEXMEConfigFE;

/**
 * Clase de Validacion de caracteres 
 * @author Alejandro Garza
*/
 
public class Validacion {
	
	/** Static logger */
	private static CLogger s_log = CLogger.getCLogger(Validacion.class);
	
	/**
	 * 
	 * @param rfc
	 * @param ctx
	 * @param maxlenght
	 * @return
	 */
	public static boolean validacionRfc(String rfc, Properties ctx, boolean maxlenght) {
		MEXMEConfigFE cfe = MEXMEConfigFE.get(ctx, null);
		if (cfe != null && cfe.getCaracPermitidos() != null) {
			String caracPermitidos = cfe.getCaracPermitidos().toUpperCase();

			int y = rfc.length();
			boolean valido = false;

			if (rfc == null || rfc.trim().equals("")) {
				return false;
			}

			if (maxlenght && (rfc.length() < 12 || rfc.length() > 13)) {
				return false;
			}

			for (int j = 0; j < y; j++) {
				Character c = rfc.charAt(j);
				if (caracPermitidos.contains(c.toString().toUpperCase())) {
					valido = true;
				} else {
					valido = false;
					break;
				}
			}
			if (valido == false) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * Valida que la fecha no sea mayor a la actual
	 * 
	 * @param fecha
	 * @return true si la fecha es valida
	 */
	public boolean fecha(String fecha) {
		try {
	            /*la fecha no debe tener ni mas ni menos de 10 caracteres
	            if(fecha.trim().length() != 10){
	                return false;
	            }
	            
	            if(fecha.charAt(2)!= '/' || fecha.charAt(5)!= '/'){
	        	return false;
	            }
	            
	            int dia=Integer.parseInt(fecha.substring(0,2));
	            int mes=Integer.parseInt(fecha.substring(3,5));
	            int anio=Integer.parseInt(fecha.substring(6,10)); 
	            
	            //debe ser un dia, mes y a�o v�lido
	            if(dia <= 0 || mes <= 0 || anio <= 0){
	                return false;
	            }
	            //el mes debe estar entre 1 y 12
	            if(mes > 12){
	                return false;
	            }
    
	            //los dias deben ser v�lidos segun el mes
	            int totalDias=0;
	            
	            switch (mes) {
	            case 1:
	            case 3:
	            case 5:
	            case 7:
	            case 8:
	            case 10:
	            case 12: {totalDias=31;break;}
	            
	            case 4:
	            case 6:
	            case 9:
	            case 11: {totalDias=30;break;}
	            
	            case 2: {//dias segun si es a�o bisiesto
	                GregorianCalendar gregCal = new GregorianCalendar();
	                if(gregCal.isLeapYear(anio))
	                    totalDias=29;
	                else
	                    totalDias=28;
	                break;}
	            }
	            //si el dia ingresado es mayor al Numero de dias de c/mes
	            if(dia > totalDias){
	                return false;
	            }*/
			fechaValida(fecha, null);

			int dia = Integer.parseInt(fecha.substring(0, 2));
			int mes = Integer.parseInt(fecha.substring(3, 5));
			int anio = Integer.parseInt(fecha.substring(6, 10));

			// Validamos que la fecha no sea mayor a la actual
			String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(DB.getDateForOrg(Env.getCtx()));
			int diaActual = Integer.parseInt(fechaActual.substring(0, 2));
			int mesActual = Integer.parseInt(fechaActual.substring(3, 5));
			int anioActual = Integer.parseInt(fechaActual.substring(6, 10));

			if (new GregorianCalendar(anio, mes, dia).after(new GregorianCalendar(anioActual, mesActual, diaActual)))
				return false;

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Valida que la fecha y formato sean correcto
	 * @param fecha
	 * @param errors
	 * @return
	 */
	public static boolean fechaValida(String fecha, ActionErrors errors) {
		try {
			// la fecha no debe tener ni mas ni menos de 10 caracteres
			if (fecha.trim().length() != 10) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.formato.fecha"));
				return false;
			}

			if (fecha.charAt(2) != '/' || fecha.charAt(5) != '/') {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.formato.fecha"));
				return false;
			}

			int dia = Integer.parseInt(fecha.substring(0, 2));
			int mes = Integer.parseInt(fecha.substring(3, 5));
			int anio = Integer.parseInt(fecha.substring(6, 10));

			// debe ser un dia, mes y a�o v�lido
			if (dia <= 0 || mes <= 0 || anio <= 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("msj.error.fecha"));
				return false;
			}
			// el mes debe estar entre 1 y 12
			if (mes > 12) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caja.mes"));
				return false;
			}

			// los dias deben ser v�lidos segun el mes
			int totalDias = 0;

			switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {totalDias=31;break;}
            
            case 4:
            case 6:
            case 9:
            case 11: {totalDias=30;break;}
            
            case 2: {//dias segun si es a�o bisiesto
				GregorianCalendar gregCal = new GregorianCalendar();
				if (gregCal.isLeapYear(anio))
					totalDias = 29;
				else
					totalDias = 28;
				break;}
            }
			// si el dia ingresado es mayor al Numero de dias de c/mes
			if (dia > totalDias) {
				if(errors != null)
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("msj.error.fecha.dia", totalDias));
				return false;
			}

		} catch (Exception e) {
			if(errors != null)
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.formato.fecha"));
			return false;
		}

		return true;
	}

	/**
	 * Comprueba si las fechas son correctas
	 * y la inicial es menor que la final
	 * @param fechaIni Fecha Inicial
	 * @param fechaFin Fecha Final
	 * @param errores Errores encontrados
	 */
	public static ActionErrors validaFechas(String fechaIni, String fechaFin, ActionErrors errores) {
		if (fechaIni != null && fechaFin != null) {
			if (Validacion.fechaValida(fechaIni, errores) && Validacion.fechaValida(fechaFin, errores)) {
				Date ini = null;
				Date fin = null;
				try {
					ini = Constantes.getSdfFecha().parse(fechaIni);
					fin = Constantes.getSdfFecha().parse(fechaFin);
					if (ini.after(fin)) {
						errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.fechaFinMenor"));
					}
				} catch (Exception ex) {
					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("msj.error.fecha"));
				}
			}
		} else {
			errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("msj.error.fecha"));
		}

		return errores;
	}

  	/**
	 * Validacion de entrada
	 * Solo acepta letras
	 * @param input Cadena de entrada
	 * @param espacio 
	 * @return true si la entrada es solo letras
	 */
	public static boolean isLetter(String input, boolean espacio) {

		for (int i = 0; i < input.length(); i++) {
			if (!Character.isLetter(input.charAt(i))) {
				if (espacio ? (input.charAt(i) == ' ' ? false : true) : true)
					return false;
				else
					continue;
			}
		}
		return true;
	}

  	/**
      * Validacion del RFC y CURP   
      * @param rfc
      * @return boolean
      */    
	public static void isValido(String aValidar, MEXMEConfigFE cfe, String errorMsg)throws ExpertException {
		if (StringUtils.isNotBlank(aValidar)) {
			String caracPermitidos = cfe.getCaracPermitidos().toUpperCase();
			for (int j = 0; j < aValidar.length(); j++) {
				Character c = aValidar.charAt(j);
				if (!caracPermitidos.contains(c.toString().toUpperCase())) {
					throw new ExpertException((StringUtils.isBlank(errorMsg) ? "" : errorMsg) +Utilerias.getLabel("cfe.invalid.character", c.toString(), caracPermitidos));
				}
			}
		}
	}

    /**
     * Valida el formato de la fecha y si es mayor a la actual
     * @param consulta
     * @param hora
     * @param min
     * @return
     */
	public static ActionErrors validarFecha(String consulta, int hora, int min, ActionErrors errores) {
		String fechaActual = null;
		String hrActual = null;

		try {
			Calendar cal = Calendar.getInstance();
			fechaActual = Constantes.getSdfFecha().format(cal.getTime());
			// LRHU. Hora actual para mostrar en mensaje de error.
			hrActual = Constantes.getSdfHora().format(cal.getTime());

			int dia = Integer.parseInt(consulta.substring(0, 2));
			int mes = Integer.parseInt(consulta.substring(3, 5));
			int anio = Integer.parseInt(consulta.substring(6, 10));

			int diaA = cal.get(Calendar.DAY_OF_MONTH);
			int mesA = cal.get(Calendar.MONTH) + 1;
			int anioA = cal.get(Calendar.YEAR);
			int horaA = cal.get(Calendar.HOUR_OF_DAY); // RaulM Se toma la hora en formato 24hr.
			int minA = cal.get(Calendar.MINUTE);

			if (consulta.equalsIgnoreCase(fechaActual)) {
				if ((hora > horaA) || (hora == horaA && min >= minA)) {
					return errores;
				} else {
					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("citas.error.fechaCita", hrActual,
							fechaActual));
					return errores;
				}

			} else if (anio > anioA || (anio == anioA && mes > mesA) || (anio == anioA && mes == mesA && dia > diaA))
				return errores;
			else {
				errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("citas.error.fechaCita", hrActual, fechaActual));
				return errores;
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, "-- validarFecha : ", e.getMessage());
			if(WebEnv.DEBUG)
			    e.printStackTrace();
			errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("citas.error.fechaCita", hrActual, fechaActual));
			return errores;
		}
	}
	
	/**
	 * Comprueba si las fechas con formato dd/MM/yyyy hh:mm aa son correctas
	 * y la inicial es menor que la final
	 * @param fechaIni Fecha Inicial
	 * @param fechaFin Fecha Final
	 * @param errores Errores encontrados
	 */
	public static ActionErrors validaFechasyHoras(String fechaIni, String fechaFin, ActionErrors errores) {
		if (fechaIni != null && fechaFin != null) {
			Date ini = null;
			Date fin = null;
			try {
				ini = Constantes.getSdfFechaHoraAmPm().parse(fechaIni);
				fin = Constantes.getSdfFechaHoraAmPm().parse(fechaFin);
				if (ini.after(fin)) {
					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.fechaFinMenor"));
				}
			} catch (ParseException e) {
				errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.formatoFechaHoraInv"));
			}

			catch (Exception ex) {
				errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("msj.error.fecha"));
			}
		} else {
			errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("msj.error.fecha"));
		}

		return errores;
	}
	
	/**
	 * Noelia: Se valida una fecha en el formato dd/mm/yyyy, ademas se puede contemplar si se desea que sea mayor a la actual
	 * @param fecha: Cadena con longitud de 10 caracteres
	 * @param mayorActual
	 * @param errores
	 * @return ActionErrors
	 * @deprecated use com.ecaresoft.web.util.Validate instead (will be removed by Feb 2013)
	 */
	public static ActionErrors validaFecha(String fecha, boolean mayorActual,ActionErrors errores){
		if (fecha != null) {
			
			Date dFecha = null;
			try {
				SimpleDateFormat sdfFecha = Constantes.getSdfFecha();
				sdfFecha.setLenient(false);
				dFecha = sdfFecha.parse(fecha);	
				
				if (!mayorActual && dFecha.after(new Date())) {
					errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("msj.error.fecha.mayor", fecha));
				}
				
			} catch (ParseException e) {				
				errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.formato.fecha")); 
			} 
			
		}else {
			errores.add(ActionErrors.GLOBAL_ERROR, new ActionError("msj.error.fecha"));
		}
		return errores;
	}
}