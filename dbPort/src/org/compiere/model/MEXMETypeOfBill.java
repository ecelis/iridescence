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
public class MEXMETypeOfBill extends X_EXME_TypeOfBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1160812359793592583L;

	/**
	 * @param ctx
	 * @param EXME_TypeOfBill_ID
	 * @param trxName
	 */
	public MEXMETypeOfBill(Properties ctx, int EXME_TypeOfBill_ID,
			String trxName) {
		super(ctx, EXME_TypeOfBill_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMETypeOfBill(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
