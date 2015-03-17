package org.compiere.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;

public class NotasMedicasView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Propiedad Cuestionario.
     */
    private long cuestionario = 0;

    /**
     * Propiedad Pregunta.
     */
    private int pregunta = 0;

    /**
     * Propiedad TipoPreg.
     */

    private long tipoPreg = 0;

    /**
     * Propiedad NombrePreg.
     */

    private String nombrePreg = "";

    /**
     * Propiedad NombreTipoPreg.
     */

    private String nombreTipoPreg = null;

    /**
    * Propiedad Respuesta.
    */

    private String respuesta = null;

    /**
     * Propiedad RespuestaL. (para tipo Logico)
     */

    private boolean respuestaL = false;

    /**
     * Propiedad RespuestaI (para tipo imagen)
     */

    private FormFile respuestaI = null;

    /**
    * Propiedad Comentario.
    */

    private String comentario = null;

    /**
     * Propiedad Compartir.
     */

    private String compartir = "";

    /**
     * Propiedad Tipo.
     */

    private String tipo = ""; //texto

    /**
     * Propiedad Obligatoria.
     */

    private String obligatoria = "";


    /**
     * Propiedad Secuencia.
     */

    private int secuencia = 0;

    /**
     * Lista de Valores para preguntas tipo combo
     */

    private List lstCombo = new ArrayList();

   
    private String confidencial = null;
    
    private int exme_medico_id = 0;
   
    private String nombre_med = null;
    
    private int exme_estserv_id = 0;
    
    private String estacion = null;   
    
    private String descripcion = null;
    
    private String rutaImagen = null;
    
    /**
     * Noelia Propiedad folio.
     */

    private int folio = 0;
    
    /**
     * Noelia Propiedad fechaHr.
     */

    private String fechaHr = null;
    
    /**
     * Noelia Propiedad cuestID.
     */
    private int cuestID = 0;
    
    /** GADC**
     * Propiedad Usuario.
     */
    private String usuario = null;
    
    
    /**
     * 
     *
     */
    public void reset(){

        this.cuestionario   = 0;

        this.pregunta       = 0;

        this.tipoPreg       = 0;

        this.nombrePreg     = "";

        this.nombreTipoPreg = null;

        this.respuesta      = null;

        this.respuestaL     = false;

        this.respuestaI     = null;

        this.comentario     = null;

        this.compartir      = "T"; //todos

        this.tipo       = "T"; //Texto

        this.obligatoria    = "Y"; //si

        this.secuencia      = 0;

        this.lstCombo = new ArrayList();

        this.confidencial = null;
        
        this.exme_medico_id = 0;
       
        this.nombre_med = null;
        
        this.exme_estserv_id = 0;
        
        this.estacion = null;  
        
        this.cuestID = 0;
        
        this.usuario = null;//GADC
    }






	public String getComentario() {
		return comentario;
	}






	public void setComentario(String comentario) {
		this.comentario = comentario;
	}






	public String getCompartir() {
		return compartir;
	}






	public void setCompartir(String compartir) {
		this.compartir = compartir;
	}






	public String getConfidencial() {
		return confidencial;
	}






	public void setConfidencial(String confidencial) {
		this.confidencial = confidencial;
	}






	public long getCuestionario() {
		return cuestionario;
	}






	public void setCuestionario(long cuestionario) {
		this.cuestionario = cuestionario;
	}






	public String getEstacion() {
		return estacion;
	}






	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}






	public int getExme_estserv_id() {
		return exme_estserv_id;
	}






	public void setExme_estserv_id(int exme_estserv_id) {
		this.exme_estserv_id = exme_estserv_id;
	}






	public int getExme_medico_id() {
		return exme_medico_id;
	}






	public void setExme_medico_id(int exme_medico_id) {
		this.exme_medico_id = exme_medico_id;
	}






	public List getLstCombo() {
		return lstCombo;
	}






	public void setLstCombo(List lstCombo) {
		this.lstCombo = lstCombo;
	}






	public String getNombre_med() {
		return nombre_med;
	}






	public void setNombre_med(String nombre_med) {
		this.nombre_med = nombre_med;
	}






	public String getNombrePreg() {
		return nombrePreg;
	}






	public void setNombrePreg(String nombrePreg) {
		this.nombrePreg = nombrePreg;
	}






	public String getNombreTipoPreg() {
		return nombreTipoPreg;
	}






	public void setNombreTipoPreg(String nombreTipoPreg) {
		this.nombreTipoPreg = nombreTipoPreg;
	}






	public String getObligatoria() {
		return obligatoria;
	}






	public void setObligatoria(String obligatoria) {
		this.obligatoria = obligatoria;
	}






	public int getPregunta() {
		return pregunta;
	}






	public void setPregunta(int pregunta) {
		this.pregunta = pregunta;
	}






	public String getRespuesta() {
		return respuesta;
	}






	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	public FormFile getRespuestaI() {
		return respuestaI;
	}






	public void setRespuestaI(FormFile respuestaI) {
		this.respuestaI = respuestaI;
	}






	public boolean isRespuestaL() {
		return respuestaL;
	}






	public void setRespuestaL(boolean respuestaL) {
		this.respuestaL = respuestaL;
	}






	public int getSecuencia() {
		return secuencia;
	}






	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}






	public String getTipo() {
		return tipo;
	}






	public void setTipo(String tipo) {
		this.tipo = tipo;
	}






	public long getTipoPreg() {
		return tipoPreg;
	}






	public void setTipoPreg(long tipoPreg) {
		this.tipoPreg = tipoPreg;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}






	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}






	public String getDescripcion() {
		return descripcion;
	}






	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}


	public String getFechaHr() {
		return fechaHr;
	}


	public void setFechaHr(String fechaHr) {
		this.fechaHr = fechaHr;
	}

	public int getCuestID() {
		return cuestID;
	}

	public void setCuestID(int cuestID) {
		this.cuestID = cuestID;
	}

	//**GADC**
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}//Fin cambios **GADC**
}


