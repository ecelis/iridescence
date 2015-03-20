package org.compiere.model;

import java.util.Properties;

/**
 * @author arangel
 *
 */
public class CalloutPrice extends CalloutEngine {

	public String salesPriceList(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value){
	
		int listVersionId = (Integer) mTab.getValue("M_PriceList_Version_ID");
		String indicador = MEXMEPriceList.getIsSOPriceList(listVersionId);
		mTab.setValue("IsSOPriceList", indicador);
		return "";
	}
}