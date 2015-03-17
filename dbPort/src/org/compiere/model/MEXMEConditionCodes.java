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
public class MEXMEConditionCodes extends X_EXME_ConditionCodes {

	private static final long serialVersionUID = 7096221832735860072L;

	/**
	 * @param ctx
	 * @param EXME_ConditionCodes_ID
	 * @param trxName
	 */
	public MEXMEConditionCodes(Properties ctx, int EXME_ConditionCodes_ID,
			String trxName) {
		super(ctx, EXME_ConditionCodes_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEConditionCodes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
