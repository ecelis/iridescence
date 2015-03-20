package org.compiere.util;

import java.io.Serializable;

import org.compiere.minigrid.IDColumn;

/**
 * Elemento de un Grid
 * 
 * @author odelarosa
 * 
 */
public interface GridItem extends Serializable {

	/**
	 * Columns a mostrar
	 * 
	 * @return Arreglo de string con las columnas a mistrar
	 */
	public String[] getColumns();

	/**
	 * Identificador de columna
	 * 
	 * @return Identificador de columna
	 */
	public IDColumn getIdColumn();
}
