package org.compiere.model;

import java.io.Serializable;

/**
 * Modelo b&aacute;sico de tres propiedades
 * (id, nombre, descripcion)
 * <p>
 * Modificado por: $Author: llama $ <p>
 * Fecha: $Date: 2006/10/09 01:27:32 $ <p>
 *
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.2 $
*/
public class BasicoTresProps implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9060099656678038103L;

	/**
    *  Constructor
    */
    public BasicoTresProps () {
        this.id          = 0;
        this.value       = null;
        this.nombre      = null;
        this.descripcion = null;
    }

    /**
     * Propiedad Id.
     */
    private long id = 0;

    /**
     * Obtener la propiedad id.
     * 
     *@return La propiedad Id.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Establecer la propiedad id.
     * 
     *@param id Nueva propiedad id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Propiedad Nombre.
     */
    private String nombre = null;

    /**
     * Obtener la propiedad nombre.
     * 
     *@return La propiedad Nombre.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establecer la propiedad nombre.
     * 
     *@param nombre Nueva propiedad nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Propiedad Descripcion.
     */
    private String descripcion = null;

    /**
     * Obtener la propiedad descripcion.
     * 
     *@return La propiedad Descripcion.
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Establecer la propiedad descripcion.
     * 
     *@param descripcion Nueva propiedad descripcion.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    private String value = null;

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     *  Constructor
     */
     public BasicoTresProps (int id, String value, String name, String desc) {
         this.id          = id;
         this.value       = value;
         this.nombre      = name;
         this.descripcion = desc;
     }
    
     /**
      *  Constructor
      */
      public BasicoTresProps (String value, String name, String desc) {
          this.value       = value;
          this.nombre      = name;
          this.descripcion = desc;
      }
}
