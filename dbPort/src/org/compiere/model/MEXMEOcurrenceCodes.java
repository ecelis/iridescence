/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author luis
 *
 */
public class MEXMEOcurrenceCodes extends X_EXME_OcurrenceCodes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 144990767353243490L;

	/**
	 * @param ctx
	 * @param EXME_OcurrenceCodes_ID
	 * @param trxName
	 */
	public MEXMEOcurrenceCodes(Properties ctx, int EXME_OcurrenceCodes_ID,
			String trxName) {
		super(ctx, EXME_OcurrenceCodes_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEOcurrenceCodes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
