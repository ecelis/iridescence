package org.compiere.model;

/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

/**
 * Modelo para la pantalla que muestra las citas por medico
 * <p>
 * Modificado por: $Author: gisela $ <p>
 * Fecha: $Date: 2006/08/25 16:05:41 $ <p>
 *
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.1 $
*/
public class CuestionarioView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Propiedad Cuestionario.
     */
    private long cuestionario = 0;

    /**
     * Obtener la propiedad cuestionario.
     * 
     *@return La propiedad Cuestionario.
     */
    public long getCuestionario() {
        return this.cuestionario;
    }

    /**
     * Establecer la propiedad cuestionario.
     * 
     *@param cuestionario Nueva propiedad cuestionario.
     */
    public void setCuestionario(long cuestionario) {
        this.cuestionario = cuestionario;
    }

    /**
     * Propiedad Pregunta.
     */
    private int pregunta = 0;

    /**
     * Obtener la propiedad pregunta.
     * 
     *@return La propiedad Pregunta.
     */
    public int getPregunta() {
        return this.pregunta;
    }

    /**
     * Establecer la propiedad pregunta.
     * 
     *@param pregunta Nueva propiedad pregunta.
     */
    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Propiedad TipoPreg.
     */
    private long tipoPreg = 0;

    /**
     * Obtener la propiedad tipoPreg.
     * 
     *@return La propiedad TipoPreg.
     */
    public long getTipoPreg() {
        return this.tipoPreg;
    }

    /**
     * Establecer la propiedad tipoPreg.
     * 
     *@param tipoPreg Nueva propiedad tipoPreg.
     */
    public void setTipoPreg(long tipoPreg) {
        this.tipoPreg = tipoPreg;
    }

    /**
     * Propiedad NombrePreg.
     */
    private String nombrePreg = "";

    /**
     * Obtener la propiedad nombrePreg.
     * 
     *@return La propiedad NombrePreg.
     */
    public String getNombrePreg() {
        return this.nombrePreg;
    }

    /**
     * Establecer la propiedad nombrePreg.
     * 
     *@param nombrePreg Nueva propiedad nombrePreg.
     */
    public void setNombrePreg(String nombrePreg) {
        this.nombrePreg = nombrePreg;
    }

    /**
     * Propiedad NombreTipoPreg.
     */
    private String nombreTipoPreg = null;

    /**
     * Obtener la propiedad nombreTipoPreg.
     * 
     *@return La propiedad NombreTipoPreg.
     */
    public String getNombreTipoPreg() {
        return this.nombreTipoPreg;
    }

   /**
     * Establecer la propiedad nombreTipoPreg.
     * 
     *@param nombreTipoPreg Nueva propiedad nombreTipoPreg.
     */
    public void setNombreTipoPreg(String nombreTipoPreg) {
        this.nombreTipoPreg = nombreTipoPreg;
    }

    /**
    * Propiedad Respuesta.
    */
    private String respuesta = null;

    /**
     * Obtener la propiedad respuesta.
     * 
     *@return La propiedad Respuesta.
     */
    public String getRespuesta() {
        return this.respuesta;
    }

    /**
     * Establecer la propiedad respuesta.
     * 
     *@param respuesta Nueva propiedad respuesta.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Propiedad RespuestaL. (para tipo Logico)
     */
    private boolean respuestaL = false;

    /**
     * Obtener la propiedad respuestaL.
     * 
     *@return La propiedad RespuestaL.
     */
    public boolean getRespuestaL() {
        return this.respuestaL;
   }

   /**
     * Establecer la propiedad respuestaL.
     * 
     *@param respuestaL Nueva propiedad respuestaL.
     */
    public void setRespuestaL(boolean respuestaL) {
        this.respuestaL = respuestaL;
    }

    /**
     * Propiedad RespuestaI (para tipo imagen)
     */
    private FormFile respuestaI = null;

    /**
     * Obtener la propiedad respuestaI.
     * 
     *@return La propiedad RespuestaI.
     */
    public FormFile getRespuestaI() {
        return this.respuestaI;
    }

    /**
     * Establecer la propiedad respuestaI.
     * 
     *@param respuestaI Nueva propiedad respuestaI.
     */
    public void setRespuestaI(FormFile respuestaI) {
        this.respuestaI = respuestaI;
    }

    /**
    * Propiedad Comentario.
    */
    private String comentario = null;

    /**
     * Obtener la propiedad comentario.
     * 
     *@return La propiedad Comentario.
     */
    public String getComentario() {
        return this.comentario;
    }

    /**
     * Establecer la propiedad comentario.
     * 
     *@param comentario Nueva propiedad comentario.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * Propiedad Compartir.
     */
    private String compartir = "T";

    /**
     * Obtener la propiedad compartir.
     * 
     *@return La propiedad Compartir.
     */
    public String getCompartir() {
        return this.compartir;
    }

    /**
     * Establecer la propiedad compartir.
     * 
     *@param compartir Nueva propiedad compartir.
     */
    public void setCompartir(String compartir) {
        this.compartir = compartir;
    }

    /**
     * Propiedad Tipo.
     */
    private String tipo = "T"; //texto

    /**
     * Obtener la propiedad tipo.
     * 
     *@return La propiedad Tipo.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Establecer la propiedad tipo.
     * 
     *@param tipo Nueva propiedad tipo.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Propiedad Obligatoria.
     */
    private String obligatoria = "Y";

    /**
     * Obtener la propiedad obligatoria.
     * 
     *@return La propiedad Obligatoria.
     */
    public String getObligatoria() {
        return this.obligatoria;
    }

    /**
    * Establecer la propiedad obligatoria.
    * 
    *@param obligatoria Nueva propiedad obligatoria.
    */
    public void setObligatoria(String obligatoria) {
        this.obligatoria = obligatoria;
    }


   /**
     * Propiedad Secuencia.
     */
    private int secuencia = 0;

    /**
     * Obtener la propiedad secuencia.
     * 
     *@return La propiedad Secuencia.
     */
    public int getSecuencia() {
        return this.secuencia;
    }

    /**
     * Establecer la propiedad secuencia.
     * 
     *@param secuencia Nueva propiedad secuencia.
     */
    public void setSecuencia(int secuencia) {
       this.secuencia = secuencia;
    }

    /**
     * Lista de Valores para preguntas tipo combo
     */
    private List<LabelValueBean> lstCombo = new ArrayList<LabelValueBean>();
    
   
    /**
     * @return Returns the lstCombo.
     */
    public List<LabelValueBean> getLstCombo() {
        return lstCombo;
    }
    
    /**
     * @param lstCombo The lstCombo to set.
     */
    public void setLstCombo(List<LabelValueBean> lstCombo) {
        this.lstCombo = lstCombo;
    }
    /**
    * Nombre del cuestionario .. Twry
    */
   private String nombreCuest = null;
    
    /**
     * 
     * @return
     */
	public String getNombreCuest() {
		return nombreCuest;
	}

	/**
	 * 
	 * @param nombreCuest
	 */
	public void setNombreCuest(String nombreCuest) {
		this.nombreCuest = nombreCuest;
	}

	/**
	 * Define si la pregunta esta activa o no 
	 */
	private boolean activo = true;

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
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
        lstCombo = new ArrayList<LabelValueBean>();
        this.nombreCuest = null; //Nombre del cuestionario
        this.activo = true;
        this.respCombo=null;
    }
    
    /**
     *  Propiedad RutaImagen. (LAMA)
     */
    private String rutaImagen = null;

    /**
     *  Obtener la propiedad rutaImagen. (LAMA)
     *
     *@return    La propiedad RutaImagen.
     */
    public String getRutaImagen() {
        return this.rutaImagen;
    }
  
    /**
     *  Establecer la propiedad rutaImagen. (LAMA)
     *
     *@param  rutaImagen  Nueva propiedad rutaImagen.
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    private int visible=1;

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}
	
	private String verDiv="block";

	public String getVerDiv() {
		return verDiv;
	}

	public void setVerDiv(String verDiv) {
		this.verDiv = verDiv;
	}
	
	private String respCombo="";


	public String getRespCombo() {
		return respCombo;
	}


	public void setRespCombo(String respCombo) {
		this.respCombo = respCombo;
	}
}

