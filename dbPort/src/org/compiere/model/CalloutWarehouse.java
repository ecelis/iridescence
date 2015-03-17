package org.compiere.model;

import java.math.BigDecimal;
import java.util.Properties;

public class CalloutWarehouse extends CalloutEngine {
	
	
	/**
	 *	M_InOutLine - Default UOM/Locator for Product.
	 *	@param ctx context
	 *	@param WindowNo window no
	 *	@param mTab tab model
	 *	@param mField field model
	 *	@param value new value
	 *	@return error message or ""
	 */
	public String intervalo (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		//Gvaldez Mantis 3817, la clase que controla los cambios via web, crea un objeto de tipo BigDecimal 
		//para todas las cajas de texto que almacenan Numeros, sin importar el tipo de numero
		//Es necesario evaluar si el objeto recibido es instancia de BigDecimal para poder "convertirlo" a Integer.
		//De cualquier otro caso se castea directo.
		//Se presume que si no recibe BigDecimal el objeto sera un String, int o Integer.
		
		Integer Intervalo = null;
		if(value instanceof BigDecimal) {
			Intervalo = Integer.valueOf(value.toString());
		}else{
			Intervalo = (Integer)value;
		}
		
		if (Intervalo == null || Intervalo.intValue() == 0)
			return "";
		setCalloutActive(true);
		
		if (Intervalo.intValue()!=15 && Intervalo.intValue()!=30
				&& Intervalo.intValue()!=45 && Intervalo.intValue()!=60	
				&& Intervalo.intValue()!=120 )
			mTab.setValue("Intervalo", 60);
			
		setCalloutActive(false);
		return "";
	}	//	product

}
