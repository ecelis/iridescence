package org.compiere.model;

import java.util.List;

//import org.compiere.acct.Fact;

public interface FactsValidator {
	
	/**
	 * 	Get Client to be monitored
	 *	@return AD_Client_ID
	 */
	public int getAD_Client_ID();
	
	//expert : mrojas : modificado por reorganizacion, Fact esta en serverRoot y no tenemos visibilidad
	/**
	 * 
	 * @param facts
	 * @param po
	 * @return error message or null - 
     * if not null, the pocument will be marked as Invalid.
	 */
	public String factsValidate(MAcctSchema schema, List<?> facts, PO po);
}
