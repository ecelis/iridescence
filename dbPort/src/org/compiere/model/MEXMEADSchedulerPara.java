/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Clase Modelo para la tabla X_AD_Scheduler_Para.
 * Creada el 18 de Junio del 2012.
 * 
 * @author jcantu 
 *@deprecated no sigue con standard de nombrado de modelos
 */
public class MEXMEADSchedulerPara extends X_AD_Scheduler_Para {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2621200686306535771L;

	/**
	 * @param ctx
	 * @param AD_Scheduler_Para_ID
	 * @param trxName
	 */
	public MEXMEADSchedulerPara(Properties ctx, int AD_Scheduler_Para_ID,
			String trxName) {
		super(ctx, AD_Scheduler_Para_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEADSchedulerPara(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
