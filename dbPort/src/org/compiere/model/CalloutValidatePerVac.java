package org.compiere.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

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
public class CalloutValidatePerVac extends CalloutEngine  {

	
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
		// Set new timstamp to grid
		mTab.setValue("Fecha_Regreso",new Timestamp(cal.getTimeInMillis()));
	
		setCalloutActive(false);
		return "";
	}
	
	
}
