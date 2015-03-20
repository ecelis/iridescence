package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

public class CalloutDescPrecioFijo  extends CalloutEngine {

	public String precioFijo (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		BigDecimal precioFijo = (BigDecimal)value;
		if (precioFijo == null || precioFijo.intValue() == 0)
			return "";
		setCalloutActive(true);
		
		if ((BigDecimal)mTab.getValue("TopeMaxDesc") !=null )
		{
			
			BigDecimal descuentoFijo = (((BigDecimal)mTab.getValue("TopeMaxDesc")).subtract(precioFijo));
			mTab.setValue("DescFijo", descuentoFijo);
		}
			
		setCalloutActive(false);
		return "";
	}	//	product
	
	public String topeMaxDesc (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		BigDecimal topeMaxDesc = (BigDecimal)value;
		if (topeMaxDesc == null || topeMaxDesc.intValue() == 0)
			return "";
		setCalloutActive(true);
		
		if ((BigDecimal)mTab.getValue("PrecioFijo") !=null )
		{
			
			BigDecimal descuentoFijo = topeMaxDesc.subtract((BigDecimal)mTab.getValue("PrecioFijo"));
			mTab.setValue("DescFijo", descuentoFijo);
		}

		setCalloutActive(false);
		return "";
	}	//	product
	
	
	public String extensionNo (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		Integer extensionNo= null;
		if(value instanceof BigDecimal)
			extensionNo = Integer.valueOf(value.toString());
		else if(value instanceof Integer)
			extensionNo = (Integer)value;
		if (extensionNo == null || extensionNo.intValue() == 0)
		{ 
			mTab.setValue("ExtensionNo", 1);
			
		}
		else
		{
			setCalloutActive(true);

			if ((Integer)mTab.getValue("ExtensionNo") !=null )
			{
				if(extensionNo==99)
				{
					mTab.setValue("ExtensionNo", 1);
				}

				if(String.valueOf(extensionNo).equalsIgnoreCase("0"))
				{
					mTab.setValue("ExtensionNo", 1);
				}

			}
		}
		setCalloutActive(false);
		return "";
	}	//	product
}
