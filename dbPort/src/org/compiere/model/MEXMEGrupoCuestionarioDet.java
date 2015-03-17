package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author odelarosa
 * 
 */
public class MEXMEGrupoCuestionarioDet extends X_EXME_GrupoCuestionarioDet {
	private static final long serialVersionUID = -3809100924372216458L;

	/**
	 * @param ctx
	 * @param EXME_GrupoCuestionarioDet_ID
	 * @param trxName
	 */
	public MEXMEGrupoCuestionarioDet(Properties ctx, int EXME_GrupoCuestionarioDet_ID, String trxName) {
		super(ctx, EXME_GrupoCuestionarioDet_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEGrupoCuestionarioDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
