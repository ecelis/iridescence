package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Tablas del paciente (Base de datos)
 * 
 * @author odelarosa <br>
 *         Modified by Lorena Lama
 */
public class MEXMETablasPaciente extends X_EXME_TablasPaciente {

	private static final long serialVersionUID = 386585134664583682L;

	/**
	 * 
	 * @param ctx
	 * @param EXME_TablasPaciente_ID
	 * @param trxName
	 */
	public MEXMETablasPaciente(Properties ctx, int EXME_TablasPaciente_ID, String trxName) {
		super(ctx, EXME_TablasPaciente_ID, trxName);
	}

	/**
	 * 
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETablasPaciente(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
