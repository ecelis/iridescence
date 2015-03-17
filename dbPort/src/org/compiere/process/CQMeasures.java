package org.compiere.process;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Metodos generales de las medidas de calidad clinica 
 * 
 * @author Rosy Velazquez
 * 
 **/

public class CQMeasures {

	private static CLogger log = CLogger.getCLogger(CQMeasures.class);
	
	/**
	 * Executa un query
	 * @param sql
	 * @param parametros
	 * @return pacientes
	 */
	public static int executaSql(String sql, Object... params) {
		int pacientes = 0;
		try {
			pacientes = DB.getSQLValue(null, sql, params);
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
			for (Object object : params) {
				if (object == null) {
					log.severe(">> "+object);
				} else {
					log.severe(object.getClass().getCanonicalName() + ">> " + object.toString());
				}
			}
		}

		return pacientes;
	}
	
	/**
	 * Arma el pqri para la medida
	 *  numerador   = pacientes que cumplen con la medida
	 * 	denominador = pacientes en general
	 * 	exclusiones = 
	 * @return pqri
	 */
	public static String[] pqri(String pqriNumber, int pacientesMedida, int pacDenominador){
		String[] pqri = new String[5];
		pqri[0] = pqriNumber; //pqri_number
		
		int pacientes = pacientesMedida;
		pqri[1] = String.valueOf(pacientes); //numerador
			
		int denominador = pacDenominador; 
		pqri[2] =  String.valueOf(denominador);//denominador
		pqri[3] = "0"; // exclusions
		pqri[4] = "0"; //not met
		
		return pqri;		
	}
	
	/**
	 * Calcula otra fecha sumando la cantidad en meses a la fecha
	 * para restar meses cantidad debe ser negativa
	 * @return Date
	 */
	protected static Date addMonth(Date fecha, int cantidad){
		
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(fecha);
		calendar.add(Calendar.MONTH, cantidad);		
		
		return new Date(calendar.getTimeInMillis());
	}
	
}
