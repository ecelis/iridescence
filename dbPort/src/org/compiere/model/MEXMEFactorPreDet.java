/**
 * 
 */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author Expert
 *
 */
public class MEXMEFactorPreDet extends X_EXME_FactorPreDet {

	/** serialVersionUID */
	private static final long serialVersionUID = -8959858644123079216L;

	/**
	 * @param ctx
	 * @param EXME_FactorPreDet_ID
	 * @param trxName
	 */
	public MEXMEFactorPreDet(Properties ctx, int EXME_FactorPreDet_ID,
			String trxName) {
		super(ctx, EXME_FactorPreDet_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MEXMEFactorPreDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		if(getLinea()<0){
			log.saveError("Line0", "");
			return false;
		}
		
		if(getPorcentaje().compareTo(BigDecimal.ZERO)<0){
			log.saveError("Percentage0", "");
			return false;
		}
		
		if(getNivelSuperior().compareTo(BigDecimal.ZERO)<0){
			log.saveError("SuperiorLevel0", "");
			return false;
		}
		
		return true;
		
	}
	
	

}
