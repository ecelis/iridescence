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
public class MEXMESpanCodes extends X_EXME_SpanCodes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2254807983018321394L;

	/**
	 * @param ctx
	 * @param EXME_SpanCodes_ID
	 * @param trxName
	 */
	public MEXMESpanCodes(Properties ctx, int EXME_SpanCodes_ID, String trxName) {
		super(ctx, EXME_SpanCodes_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMESpanCodes(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
