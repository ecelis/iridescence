package com.ecaresoft.acct.xml;

import com.ecaresoft.util.ErrorList;

/**
 * @author odelarosa
 *
 */
public interface XmlValidator {

	/**
	 * Valida si el xml es válido, si no lo es debe regresar la lista de errores
	 * indicando por qué no lo es
	 * 
	 * @return Listado de errores
	 */
	public ErrorList validate();
}
