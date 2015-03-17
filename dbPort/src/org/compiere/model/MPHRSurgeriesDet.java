package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MPHRSurgeriesDet extends X_PHR_SurgeriesDet {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4155179848590282201L;
	/**
	 * Construye un objeto MPHRSurgeriesDet a partir del ID 
	 * @param ctx
	 * @param PHR_AccesoDet_ID
	 * @param trxName
	 */
	public MPHRSurgeriesDet(Properties ctx, int PHR_SurgeriesDet_ID, String trxName) {
		super(ctx, PHR_SurgeriesDet_ID, trxName);
	}
	/**
	 * Construye un objeto MPHRSurgeriesDet a partir del ResultSet
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPHRSurgeriesDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
