package org.compiere.model;

/**
 * Representa un objeto seleccionable
 * 
 * @author odelarosa
 * 
 */
public interface Selectable {

	/**
	 * Id del objeto, {@link PO#get_ID()}
	 * 
	 * @return
	 */
	public int getId();

	/**
	 * Nombre
	 * 
	 * @return Nombre a mostrar
	 */
	public String getName();

	/**
	 * Si es por defecto
	 * 
	 * @return Es o no
	 */
	public boolean isDefault();

	/**
	 * Está seleccionado
	 * 
	 * @return Si o No
	 */
	public boolean isSelected();

	/**
	 * Asignar o no por defecto
	 * 
	 * @param selected
	 *            Es o No
	 */
	public void setDefault(boolean isDefault);

	/**
	 * Asignar como seleccionado o no
	 * 
	 * @param selected
	 *            Si o No
	 */
	public void setSelected(boolean selected);
}
