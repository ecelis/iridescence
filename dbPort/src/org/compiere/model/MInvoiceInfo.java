/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Permitir ir almacenando la información de las facturas timbradas y no timbradas
 * @author tperez
 *
 */
public class MInvoiceInfo extends X_C_InvoiceInfo {

	/** Constructor
	 * @param ctx
	 * @param C_InvoiceInfo_ID
	 * @param trxName
	 */
	public MInvoiceInfo(Properties ctx, int C_InvoiceInfo_ID, String trxName) {
		super(ctx, C_InvoiceInfo_ID, trxName);
	}

	/** Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MInvoiceInfo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
