package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

public class CalloutProductOrg extends CalloutEngine
{

	public String attribute (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_AttributeSet_ID = null;
		if(value instanceof BigDecimal)
			M_AttributeSet_ID = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			M_AttributeSet_ID = (Integer)value;
		if (M_AttributeSet_ID == null || M_AttributeSet_ID.intValue() == 0)
			return "";
		
		if (isCalloutActive())	//	
			return "";
		
		// Si va a quitar el valor o a cambiar el valor Validar que no tenga existencias
		
		
		// si se agrega un nuevo valor por primera vez se cambia a que maneja lotes
		mTab.setValue("isLot", "Y");
		return "";
		
	}
	
	public String lot (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())	//	
			return "";
		return "";
	}
}
