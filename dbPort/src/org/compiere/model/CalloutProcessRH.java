package org.compiere.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.joda.time.DateTimeZone;

/**
 * Callouts para validacion de periodos de vacaciones de RH
 *
 * <b>Fecha:</b> 29/Ago/2009<p>
 * <b>Modificado: </b> $Author: Arturo Aranda XPT $<p>
 * <b>En :</b> $Date: 2006/09/07 22:22:43 $<p>
 *
 * @author Arturo Aranda XPT $
 * @version $Revision: 1.5 $
 */
public class CalloutProcessRH extends CalloutEngine  {

	
	/*
	 * Increment in One day to date End to date back
	 */
	public String Fecha_Fin(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		
		setCalloutActive(false);
		
		if (isCalloutActive())		//	assuming it is resetting value
			return "";
		
		if (value == null || !(value instanceof Timestamp))
			return "";

		setCalloutActive(true);
		
		//Get Timpstamp from grid
		Calendar cal = Calendar.getInstance(DateTimeZone.forID(Env.getTimezone(ctx)).toTimeZone());
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
		// Set new timstamp to a<grid
		mTab.setValue("Fecha_Regreso",new Timestamp(cal.getTimeInMillis()));
	
		setCalloutActive(false);
		return "";
	}
	
	
	/*
	 * Calcular la edad apartir de la fecha de nacimiento del empleado
	 *
	 */
	public String CalcularEdad(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
		
		setCalloutActive(false);
		
		if (isCalloutActive())		//	assuming it is resetting value
			return "";
		
		if (value == null || !(value instanceof Timestamp))
			return "";

		setCalloutActive(true);
		
		//Set new month values
		int empId = 0;
		empId = mTab.getRecord_ID();
		
		UpdateAgeFromBirthDate(empId, ctx);
	
		setCalloutActive(false);
		return "";
	}
	
	/**
	 * Actualizar la edad del empleado apartir de su fecha de nacimiento.
	 * @param EmpId
	 * @param clientCheck
	 */
	private void UpdateAgeFromBirthDate(int EmpId, Properties ctx){
		StringBuilder strSql = new StringBuilder();
		int m_AD_Client_ID = Env.getAD_Client_ID(ctx);
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;
		
		strSql.append("		UPDATE EXME_Emp e ")
				.append(" SET e.Edad = (SELECT edadentero(to_char(e.fecha_nac,'dd/MM/yyyy')) FROM dual) ")
				.append(" WHERE e.EXME_Emp_ID = ").append(EmpId)
				.append(clientCheck);
		
		try{
        	DB.executeUpdate(strSql.toString(), null);
        }catch(Exception e){
        	System.out.println("fail: "+strSql.toString());
            log.config("Fail update haber value in  EXME_ResumenMesRH =" + EmpId); 
        }
	}

}
