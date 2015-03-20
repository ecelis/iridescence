/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author Lorena Lama
 *
 */
public class MEXMEPreReqCitaRel extends X_EXME_PreReqCitaRel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param ctx
	 * @param EXME_PreReqCitaRel_ID
	 * @param trxName
	 */
	public MEXMEPreReqCitaRel(Properties ctx, int EXME_PreReqCitaRel_ID, String trxName) {
		super(ctx, EXME_PreReqCitaRel_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEPreReqCitaRel(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
