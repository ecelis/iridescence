/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * Informacion del pago de una cotizacion en banco (pro mujer)
 * 
 * @author Lorena Lama
 *         Card http://control.ecaresoft.com/card/1548/
 */
public class MDHVoucher extends X_DH_Voucher {

	private static final long	serialVersionUID	= 1L;

	/**
	 * @param ctx
	 * @param DH_Voucher_ID
	 * @param trxName
	 */
	public MDHVoucher(Properties ctx, int DH_Voucher_ID, String trxName) {
		super(ctx, DH_Voucher_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MDHVoucher(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	@Override
	protected boolean beforeSave(final boolean newRecord) {
		if (newRecord) {
			if (getDateTrx() == null) {
				setDateTrx(Env.getCurrentDate());
			} else if (getTaxAmt() == null) {
				setTaxAmt(BigDecimal.ZERO);
			}
		}
		return super.beforeSave(newRecord);
	}

}
