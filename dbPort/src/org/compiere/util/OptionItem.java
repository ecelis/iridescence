package org.compiere.util;

/**
 * Clase que almacena dos valores, mantiene el patron 
 * de diseño MVC, utilizada para regresar valores de 
 * los proyectos medsys y dbPort, manteniendo la flexibilidad
 * de dichas clases.
 * <p>
 *
 * @author ExpertVictoria
 * @version $Revision: 1.00 $
 * @deprecated Duplicated of {@link KeyNamePair} use that class instead
 */
public class OptionItem {
	
	private String id;
    private String label;

    /**
     * Empty Constructor
     */
    public OptionItem(){
    	
    }
    
    /**
     * Constructor con parametros de inicio
     * 
     * @param id el valor oculto para <code>optionsCollection</code>
     * @param label el valor que se muestra para <code>optionsCollection</code>
     */
    public OptionItem( String id, String label ){
        this.id = id;
        this.label = label;
    }
  
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Informacion de la instancia del objeto
	 * 
	 * @return String devuelve una cadena con informaciond de dicho objeto
	 */    
    @Override
    public String toString (){
    	return (this.id + "+" + this.label);    	
    }
}
