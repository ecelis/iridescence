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
public class MEXMEValueCodes extends X_EXME_ValueCodes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1620403118128662173L;

	/**
	 * @param ctx
	 * @param EXME_ValueCodes_ID
	 * @param trxName
	 */
	public MEXMEValueCodes(Properties ctx, int EXME_ValueCodes_ID,
			String trxName) {
		super(ctx, EXME_ValueCodes_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEValueCodes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
