package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

public class MTaxCategorySeq extends X_EXME_TaxCategorySeq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sequenceMsg = null;
	
	public MTaxCategorySeq(Properties ctx, int C_TaxCategorySeq_ID, String trxName) {
		super(ctx, C_TaxCategorySeq_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MTaxCategorySeq(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	

	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	@Override
	protected boolean beforeSave(final boolean newRecord) {
		boolean retValue = true;
		log.fine("");
		// Get Line No
		if (getSequence() == 0) {
			final String sql = "SELECT COALESCE(MAX(Sequence),0)+10 FROM EXME_TaxCategorySeq WHERE C_TaxCategory_ID=?";
			final int ii = DB.getSQLValue(get_TrxName(), sql, getC_TaxCategory_ID());
			setSequence(ii);
			if(ii >=100){
				retValue = false;
				sequenceMsg = new StringBuilder(Utilerias.getLabel("abstracting.msj.errorSequence")).append(" (").append(ii).append(") ").toString();
			}
		}
		return retValue;
	}
	
	@Override
	protected boolean afterDelete(boolean success) {
		boolean retValue = true;
		List<MTaxCategorySeq> lst = new Query(Env.getCtx(), Table_Name, " C_TaxCategory_ID = ? ", get_TrxName()).addAccessLevelSQL(true)
				.setParameters(getC_TaxCategory_ID()).setOrderBy(COLUMNNAME_Sequence).list();
		int seq = 10;
		for (MTaxCategorySeq catSeq : lst) {
			if (seq != catSeq.getSequence()) {
				catSeq.setSequence(seq);
				if (!catSeq.save(get_TrxName())) {
					retValue = false;
					break;
				}
			}
			seq += 10;
		}
		return retValue;
	}
	
	public static List<MTaxCategorySeq> getTaxCategorySeqLst(String whereClause, List<Object> params, String trxName) {
		return new Query(Env.getCtx(), Table_Name, whereClause, trxName).addAccessLevelSQL(true).setOnlyActiveRecords(true).setParameters(params)
				.setOrderBy(COLUMNNAME_Sequence).list();
	}

	public static boolean isBuySellType(int taxCatID, String trxName) {
		boolean retvalue;
		if (new Query(Env.getCtx(), Table_Name, "C_TaxCategory_ID = ? AND Buy_Sell IN(?,?)", trxName).addAccessLevelSQL(true)
				.setOnlyActiveRecords(true).setParameters(taxCatID, BUY_SELL_BEqBuy, BUY_SELL_SEqSell).getIDs().length > 0) {
			retvalue = true;
		} else {
			retvalue = false;
		}
		return retvalue;
	}

	public static List<MTaxCategorySeq> getCatTaxesSeq(String whereClause, List<Object> params, String trxName) {
		return new Query(Env.getCtx(), Table_Name, whereClause, trxName).addAccessLevelSQL(true).setParameters(params).list();
	}

	public String getSequenceMsg() {
		return sequenceMsg;
	}
}
