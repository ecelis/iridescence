package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;
/**
 * Modelo de la tabla PHR_PacSignoVitalDet
 * @author raul
 *
 */
public class MPHRPacSignoVitalDet extends X_PHR_PacSignoVitalDet {
	
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8549895300126766982L;
	/**
	 * Log
	 */
	private static CLogger s_log = CLogger.getCLogger (MPHRPacSignoVitalDet.class);
	/**
	 * Construye un objeto MPHRPacSignoVitalDet a partir del ID 
	 * @param ctx
	 * @param PHR_AccesoDet_ID
	 * @param trxName
	 */
	public MPHRPacSignoVitalDet(Properties ctx, int PHR_PacSignoVitalDet_ID,
			String trxName) {
		super(ctx, PHR_PacSignoVitalDet_ID, trxName);
	}
	/**
	 * Construye un objeto MPHRPacSignoVitalDet a partir del ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRPacSignoVitalDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
