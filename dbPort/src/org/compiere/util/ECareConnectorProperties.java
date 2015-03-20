package org.compiere.util;

import java.util.Properties;




/**interface usada para proveer los properties de un connector
 * y para proveer el transportname*/
public interface ECareConnectorProperties {
	//TODO refactoirizar, usar nombre mas descriptivo e.g medsysConnectorProepeties 
	
	
	/** regresa un objeto properties con la configuracion del conector
	 * se recibe un cliente para otener canal manejador de ACK ahora solo se accesa
	 * a el cliente desde MHL7BPartnerLLP depues podria usarse desde connector TCP y HTTP*/
	public Properties getProperties(/*Client mirthClient*/);
	
	
	/** regresa el atributo name de la calse shared en la que se base este conector*/
	public String getTransportName();
	

}
