package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
/**
 * Modelo de la tabla de accesos al Expediente Personal
 * @author raul 23/07/2010
 *
 */
public class MPHRAccesoDet extends X_PHR_AccesoDet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9182099814649875878L;
	/**
	 * Construye un objeto MPHRAccesoDet a partir del ID 
	 * @param ctx
	 * @param PHR_AccesoDet_ID
	 * @param trxName
	 */
	public MPHRAccesoDet(Properties ctx, int PHR_AccesoDet_ID, String trxName) {
		super(ctx, PHR_AccesoDet_ID, trxName);
	}
	/**
	 * Construye un objeto MPHRAccesoDet a partir del ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRAccesoDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
