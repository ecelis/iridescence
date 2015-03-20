/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;



import java.io.Serializable;



/**

 *  Modelo para el listado de citas <p>

 *

 *  Modificado por: $Author: taniap $ Fecha: $Date: 2006/09/02 00:22:06 $

 *

 *@author     $Author: taniap $

 *@created    2 de noviembre de 2004

 *@version    $Revision: 1.1 $
@deprecated
 */

public class DetalleView implements Serializable {

    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**

     *  Propiedad Respuesta.

     */

    private String respuesta = null;

    

    

    /**

     *  Obtener la propiedad respuesta.

     *

     *@return    La propiedad Respuesta.

     */

    public String getRespuesta() {

        return this.respuesta;

    }

    

    

    /**

     *  Establecer la propiedad respuesta.

     *

     *@param  respuesta  Nueva propiedad respuesta.

     */

    public void setRespuesta(String respuesta) {

        this.respuesta = respuesta;

    }

    

    

    /**

     *  Propiedad RutaImagen.

     */

    private String rutaImagen = null;

    

    

    /**

     *  Obtener la propiedad rutaImagen.

     *

     *@return    La propiedad RutaImagen.

     */

    public String getRutaImagen() {

        return this.rutaImagen;

    }

    

    

    /**

     *  Establecer la propiedad rutaImagen.

     *

     *@param  rutaImagen  Nueva propiedad rutaImagen.

     */

    public void setRutaImagen(String rutaImagen) {

        this.rutaImagen = rutaImagen;

    }

    

    

    /**

     *  Propiedad Confidencial.

     */

    private String confidencial = null;

    

    

    /**

     *  Obtener la propiedad confidencial.

     *

     *@return    La propiedad Confidencial.

     */

    public String getConfidencial() {

        return this.confidencial;

    }

    

    

    /**

     *  Establecer la propiedad confidencial.

     *

     *@param  confidencial  Nueva propiedad confidencial.

     */

    public void setConfidencial(String confidencial) {

        this.confidencial = confidencial;

    }

    

    

    /**

     *  Propiedad Nombre.

     */

    private String nombre = null;

    

    

    /**

     *  Obtener la propiedad nombre.

     *

     *@return    La propiedad Nombre.

     */

    public String getNombre() {

        return this.nombre;

    }

    

    

    /**

     *  Establecer la propiedad nombre.

     *

     *@param  nombre  Nueva propiedad nombre.

     */

    public void setNombre(String nombre) {

        this.nombre = nombre;

    }

    

    

    /**

     *  Propiedad PregName.

     */

    private String pregName = null;

    

    

    /**

     *  Obtener la propiedad pregName.

     *

     *@return    La propiedad PregName.

     */

    public String getPregName() {

        return this.pregName;

    }

    

    

    /**

     *  Establecer la propiedad pregName.

     *

     *@param  pregName  Nueva propiedad pregName.

     */

    public void setPregName(String pregName) {

        this.pregName = pregName;

    }

    

    

    /**

     *  Propiedad TipoPregName.

     */

    private String tipoPregName = null;

    

    

    /**

     *  Obtener la propiedad tipoPregName.

     *

     *@return    La propiedad TipoPregName.

     */

    public String getTipoPregName() {

        return this.tipoPregName;

    }

    

    

    /**

     *  Establecer la propiedad tipoPregName.

     *

     *@param  tipoPregName  Nueva propiedad tipoPregName.

     */

    public void setTipoPregName(String tipoPregName) {

        this.tipoPregName = tipoPregName;

    }

    

    

    /**

     *  Propiedad DiagDescAdic.

     */

    private String diagDescAdic = null;

    

    

    /**

     *  Obtener la propiedad diagDescAdic.

     *

     *@return    La propiedad DiagDescAdic.

     */

    public String getDiagDescAdic() {

        return this.diagDescAdic;

    }

    

    

    /**

     *  Establecer la propiedad diagDescAdic.

     *

     *@param  diagDescAdic  Nueva propiedad diagDescAdic.

     */

    public void setDiagDescAdic(String diagDescAdic) {

        this.diagDescAdic = diagDescAdic;

    }

    

    

    /**

     *  Propiedad UdmName.

     */

    private String udmName = null;

    

    

    /**

     *  Obtener la propiedad udmName.

     *

     *@return    La propiedad UdmName.

     */

    public String getUdmName() {

        return this.udmName;

    }

    

    

    /**

     *  Establecer la propiedad udmName.

     *

     *@param  udmName  Nueva propiedad udmName.

     */

    public void setUdmName(String udmName) {

        this.udmName = udmName;

    }

    

    

    /**

     *  Propiedad Cantidad.

     */

    private long cantidad = 0;

    

    

    /**

     *  Obtener la propiedad cantidad.

     *

     *@return    La propiedad Cantidad.

     */

    public long getCantidad() {

        return this.cantidad;

    }

    

    

    /**

     *  Establecer la propiedad cantidad.

     *

     *@param  cantidad  Nueva propiedad cantidad.

     */

    public void setCantidad(long cantidad) {

        this.cantidad = cantidad;

    }

    

    

    /**

     *  Propiedad Descripcion.

     */

    private String descripcion = null;

    

    

    /**

     *  Obtener la propiedad descripcion.

     *

     *@return    La propiedad Descripcion.

     */

    public String getDescripcion() {

        return this.descripcion;

    }

    

    

    /**

     *  Establecer la propiedad descripcion.

     *

     *@param  descripcion  Nueva propiedad descripcion.

     */

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;

    }

    

    

    /**

     *  Propiedad TipoArea.

     */

    private String tipoArea = null;

    

    

    /**

     *  Obtener la propiedad tipoArea.

     *

     *@return    La propiedad TipoArea.

     */

    public String getTipoArea() {

        return this.tipoArea;

    }

    

    

    /**

     *  Establecer la propiedad tipoArea.

     *

     *@param  tipoArea  Nueva propiedad tipoArea.

     */

    public void setTipoArea(String tipoArea) {

        this.tipoArea = tipoArea;

    }

    

    

    /**

     *  Propiedad FechaCierre.

     */

    private String fechaCierre = null;

    

    

    /**

     *  Obtener la propiedad fechaCierre.

     *

     *@return    La propiedad FechaCierre.

     */

    public String getFechaCierre() {

        return this.fechaCierre;

    }

    

    

    /**

     *  Establecer la propiedad fechaCierre.

     *

     *@param  fechaCierre  Nueva propiedad fechaCierre.

     */

    public void setFechaCierre(String fechaCierre) {

        this.fechaCierre = fechaCierre;

    }

    

    

    /**

     *  Propiedad Especialidad.

     */

    private String especialidad = null;

    

    

    /**

     *  Obtener la propiedad especialidad.

     *

     *@return    La propiedad Especialidad.

     */

    public String getEspecialidad() {

        return this.especialidad;

    }

    

    

    /**

     *  Establecer la propiedad especialidad.

     *

     *@param  especialidad  Nueva propiedad especialidad.

     */

    public void setEspecialidad(String especialidad) {

        this.especialidad = especialidad;

    }

    

    

    /**

     *  Propiedad MedicoName.

     */

    private String medicoName = null;

    

    

    /**

     *  Obtener la propiedad medicoName.

     *

     *@return    La propiedad MedicoName.

     */

    public String getMedicoName() {

        return this.medicoName;

    }

    

    

    /**

     *  Establecer la propiedad medicoName.

     *

     *@param  medicoName  Nueva propiedad medicoName.

     */

    public void setMedicoName(String medicoName) {

        this.medicoName = medicoName;

    }

    

    

    /**

     *  Propiedad MotivoName.

     */

    private String motivoName = null;

    

    

    /**

     *  Obtener la propiedad motivoName.

     *

     *@return    La propiedad MotivoName.

     */

    public String getMotivoName() {

        return this.motivoName;

    }

    

    

    /**

     *  Establecer la propiedad motivoName.

     *

     *@param  motivoName  Nueva propiedad motivoName.

     */

    public void setMotivoName(String motivoName) {

        this.motivoName = motivoName;

    }

    

    

    /**

     *  Propiedad CantidadD.

     */

    private long cantidadD = 0;

    

    

    /**

     *  Obtener la propiedad cantidadD.

     *

     *@return    La propiedad CantidadD.

     */

    public long getCantidadD() {

        return this.cantidadD;

    }

    

    

    /**

     *  Establecer la propiedad cantidadD.

     *

     *@param  cantidadD  Nueva propiedad cantidadD.

     */

    public void setCantidadD(long cantidadD) {

        this.cantidadD = cantidadD;

    }

    

    

    /**

     *  Propiedad CantidadM.

     */

    private long cantidadM = 0;

    

    

    /**

     *  Obtener la propiedad cantidadM.

     *

     *@return    La propiedad CantidadM.

     */

    public long getCantidadM() {

        return this.cantidadM;

    }

    

    

    /**

     *  Establecer la propiedad cantidadM.

     *

     *@param  cantidadM  Nueva propiedad cantidadM.

     */

    public void setCantidadM(long cantidadM) {

        this.cantidadM = cantidadM;

    }

    

    

    /**

     *  Propiedad Observacion.

     */

    private String observacion = null;

    

    

    /**

     *  Obtener la propiedad observacion.

     *

     *@return    La propiedad Observacion.

     */

    public String getObservacion() {

        return this.observacion;

    }

    

    

    /**

     *  Establecer la propiedad observacion.

     *

     *@param  observacion  Nueva propiedad observacion.

     */

    public void setObservacion(String observacion) {

        this.observacion = observacion;

    }

    

    

    /**

     *  Propiedad SignoID.

     */

    private long signoID = 0;

    

    

    /**

     *  Obtener la propiedad signoID.

     *

     *@return    La propiedad SignoID.

     */

    public long getSignoID() {

        return this.signoID;

    }

    

    

    /**

     *  Establecer la propiedad signoID.

     *

     *@param  signoID  Nueva propiedad signoID.

     */

    public void setSignoID(long signoID) {

        this.signoID = signoID;

    }

    

    //el tipo de dato que contiene la pregunta

    private String tipoDato;

    

    /**

     * Devuelve el tipo de dato

     * @return El tipo de dato

     */

    public String getTipoDato() {

        return tipoDato;

    }

    

    /**

     * Establece el tipo de dato

     * @param tipoDato El tipo de dato

     */

    public void setTipoDato(String tipoDato) {

        this.tipoDato = tipoDato;

    }

    

    /**

     * C&oacute;digo OMS del diagn&oacute;stico

     */

    private String codOMS;

    

    public String getCodOMS() {

        return codOMS;

    }

    public void setCodOMS(String codOMS) {

        this.codOMS = codOMS;

    }

    private long cuestionario = 0;
    private String nombreCuest = null;
    private Integer folio = null;





	public long getCuestionario() {
		return cuestionario;
	}





	public void setCuestionario(long cuestionario) {
		this.cuestionario = cuestionario;
	}





	public String getNombreCuest() {
		return nombreCuest;
	}





	public void setNombreCuest(String nombreCuest) {
		this.nombreCuest = nombreCuest;
	}





	public Integer getFolio() {
		return folio;
	}





	public void setFolio(Integer folio) {
		this.folio = folio;
	}
	
	private int diagnostico = 0;





	public int getDiagnostico() {
		return diagnostico;
	}





	public void setDiagnostico(int diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	
	    
}



