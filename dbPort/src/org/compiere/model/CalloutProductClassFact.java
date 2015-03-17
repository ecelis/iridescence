/**
 * 
 */
package org.compiere.model;

import java.util.Properties;

/**
 * @author twry
 *
 */
public class CalloutProductClassFact extends CalloutEngine  {

	/**
	 *	@param ctx context
	 *	@param WindowNo window no
	 *	@param mTab tab
	 *	@param mField field
	 *	@param value value
	 *	@return null or error message
	 */
	public String setRefList (final Properties ctx, final int WindowNo, final GridTab mTab, final GridField mField, final Object value)
	{
		if (isCalloutActive())
			return "";
		
		if (value == null)
			return "";
		
		String key = null;
		if(value instanceof String) {
			key = value.toString();
		}else{
			key = "";
		}
		
		setCalloutActive(true);
		
		if (!key.isEmpty()){
			MRefList rlist = MRefList.get(ctx, X_M_Product.PRODUCTCLASS_AD_Reference_ID, key, null);
			if(rlist==null){
				return "";
			} else {
				mTab.setValue("AD_Ref_List_ID",rlist.getAD_Ref_List_ID());
				mTab.setValue("Name",rlist.getName());
			}
		}
			
		setCalloutActive(false);
		return "";
	}	//	assetGroupInActive
}
