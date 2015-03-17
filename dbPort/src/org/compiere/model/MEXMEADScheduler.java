/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Clase Modelo para la tabla X_AD_Scheduler.
 * Creada el 18 de Junio del 2012.
 * 
 * @author jcantu 
 *@deprecated no sigue con standard de nombrado de modelos
 */
public class MEXMEADScheduler extends X_AD_Scheduler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3060361719974178300L;

	/**
	 * @param ctx
	 * @param AD_Scheduler_ID
	 * @param trxName
	 */
	public MEXMEADScheduler(Properties ctx, int AD_Scheduler_ID, String trxName) {
		super(ctx, AD_Scheduler_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEADScheduler(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
