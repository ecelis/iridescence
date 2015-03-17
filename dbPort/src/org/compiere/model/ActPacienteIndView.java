/*

 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.

 * Sistema MedSys

 */

package org.compiere.model;

import java.util.Properties;

import org.apache.struts.upload.FormFile;

/**
 * Modelo de la Vista para mostrar los datos del detalle de la Indicaci�n
 * <p>
 * Modificado por: $Author: taniap $ <p>
 * Fecha: $Date: 2006/09/27 14:42:16 $ <p>
 *
 * @author Hassan Reyes
 * @version $Revision: 1.2 $
 */
public class ActPacienteIndView extends ActPacienteInd{

    /**
     * xXActPacienteIndID property.
     */
    private long xXActPacienteIndID = 0;

    /**
     * Get xXActPacienteIndID property.
     * 
     *@return xXActPacienteIndID property.
     */
    public long getXXActPacienteIndID() {
        return this.xXActPacienteIndID;
    }

    /**
     * Set xXActPacienteIndID property.
     * 
     *@param xXActPacienteIndID New xXActPacienteIndID property.
     */
    public void setXXActPacienteIndID(long xXActPacienteIndID) {
        this.xXActPacienteIndID = xXActPacienteIndID;
    }
    
    /**
     * Producto property.
     * @deprecated
     */
    private String producto = null;

    /**
     * Get producto property.
     * 
     *@return Producto property.
     *@deprecated
     */
    public String getProducto() {
        return this.producto;
    }

    /**
     * Set producto property.
     * 
     *@param producto New producto property.
     *@deprecated
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * Existencia property.
     */
//    private int existencia = 0;

    /**
     * Get existencia property.
     * 
     *@return Existencia property.
     */
//    public int getExistencia() {
//        return this.existencia;
//    }
    
    /**
     * Set existencia property.
     * 
     *@param existencia New existencia property.
     */
//    public void setExistencia(int existencia) {
//        this.existencia = existencia;
//    }

//    public void initCantidades(){
//        /** 
//         * Si la cantidad pedida es mayor a las existencias solo se podra surtir
//         * el total de existencias.
//         */
//        if(cantPedida >= existencia)
//            cantSurtir = existencia;
//        else
//            cantSurtir = cantPedida;
//        if (cantSurtir < 0)
//            cantSurtir = 0;
//    }
    
    /**
     * Linea property.
     */
    private long linea = 0;

    /**
     * Get linea property.
     * 
     *@return Linea property.
     */
    public long getLinea() {
        return this.linea;
    }

    /**
     * Set linea property.
     * 
     *@param linea New linea property.
     */
    public void setLinea(long linea) {
        this.linea = linea;
    }

    /**
     * Uom property.
     */
    private String uom = null;

    /**
     * Get uom property.
     * 
     *@return Uom property.
     */
    public String getUom() {
        return this.uom;
    }

    /**
     * Set uom property.
     * 
     *@param uom New uom property.
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * CantPedida property.
     */
    private int cantPedida = 0;

    /**
     * Get cantPedida property.
     * 
     *@return CantPedida property.
     */
    public int getCantPedida() {
        return this.cantPedida;
    }

    /**
     * Set cantPedida property.
     * 
     *@param cantPedida New cantPedida property.
     */
    public void setCantPedida(int cantPedida) {
        this.cantPedida = cantPedida;
    }

    /**
     * CantSurtir property.
     */
    private int cantSurtir = 0;

    /**
     * Get cantSurtir property.
     * 
     *@return CantSurtir property.
     */
    public int getCantSurtir() {
        return this.cantSurtir;

    }

    /**
     * Set cantSurtir property.
     * 
     *@param cantSurtir New cantSurtir property.
     */
    public void setCantSurtir(int cantSurtir) {
        this.cantSurtir = cantSurtir;
    }

    /**
     * Precio property.
     */
    private double precio = 0.0;

    /**
     * Get precio property.
     * 
     *@return Precio property.
     */
    public double getPrecio() {
        return this.precio;
    }

    /**
     * Set precio property.
     * 
     *@param precio New precio property.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * FechaOrd property.
     */
//    private java.sql.Timestamp fechaOrd = null;

    /**
     * Get fechaOrd property.
     * 
     *@return FechaOrd property.
     */
//    public java.sql.Timestamp getFechaOrd() {
//        return this.fechaOrd;
//    }

    /**
     * Set fechaOrd property.
     * 
     *@param fechaOrd New fechaOrd property.
     *@deprecated no se usa fechaOrd
     */
    public void setFechaOrd(java.sql.Timestamp fechaOrd) {
//        this.fechaOrd = fechaOrd;
    }

    /**
     * Descripcion property.
     */
    private String descripcion = null;
    
    /**
     * Get descripcion property.
     * 
     *@return Descripcion property.
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Set descripcion property.
     * 
     *@param descripcion New descripcion property.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /* ** Atributos para manejo de Lotes aaranda*/
	/**
	 *  Propiedad esLote. Indica si el Producto esta configurado como Lote para manejo de lotes
	 */
//    private boolean esLote;
    
    /**
	 *  Devuelve el valor de la propiedad esLote .
	 *
	 *@return esLote   El valor actual de esLote
	 */
//    public boolean isEsLote() {
//		return esLote;
//	}

    /**
	 *  Establecer la propiedad esLote.
	 *
	 *@param  esLote  Nueva propiedad esLote.
	 */
//	public void setEsLote(boolean esLote) {
//		this.esLote = esLote;
//	}

	/**
	 *  Propiedad lstSurtidoDet. Lista con los detalles de lotes que se le habran de dar salida
	 */
//	private List<MInOutLine> lstSurtidoDet = null;

	/**
	 *  Devuelve la lista de detalles de lotes lstSurtidoDet .
	 *
	 *@return lstSurtidoDet   El valor actual de lstSurtidoDet
	 */
//	public List<MInOutLine> getLstSurtidoDet() {
//		return lstSurtidoDet;
//	}
	
	/**
	 *  Establecer la propiedad lstSurtidoDet.
	 *
	 *@param  lstSurtidoDet  Nueva lista de detalles de lotes lstSurtidoDet.
	 */
//	public void setLstSurtidoDet(List<MInOutLine> lstSurtidoDet) {
//		this.lstSurtidoDet = lstSurtidoDet;
//	}
	
	/**
	 *  Atributo surtidoLoteOk. Indica si la lista de detalles de lotes ha sido correctamente seleccionada
	 */
//	private boolean surtidoLoteOk = false;

	/**
	 *  Devuelve la propiedad surtidoLoteOk .
	 *
	 *@return surtidoLoteOk   El valor actual de surtidoLoteOk
	 */
//	public boolean isSurtidoLoteOk() {
//		return surtidoLoteOk;
//	}
	
	/**
	 *  Establecer la propiedad surtidoLoteOk.
	 *
	 *@param  surtidoLoteOk  Nueva lista de detalles de lotes surtidoLoteOk.
	 */
//	public void setSurtidoLoteOk(boolean surtidoLoteOk) {
//		this.surtidoLoteOk = surtidoLoteOk;
//	}
	
	/**
	 *  Atributo M_AttributeSetInstance_ID. Atributo para el control de lotes
	 */
//	private int m_AttributeSetInstance_ID;
	
	/**
	 *  Devuelve la propiedad attributeSetInstance_ID .
	 *
	 *@return attributeSetInstance_ID
	 */
//    public int getM_AttributeSetInstance_ID() {
//		return m_AttributeSetInstance_ID;
//	}

    /**
	 *  Establecer la propiedad attributeSetInstance_ID.
	 *
	 *@param  attributeSetInstance_ID.
	 */
//	public void setM_AttributeSetInstance_ID(int attributeSetInstance_ID) {
//		m_AttributeSetInstance_ID = attributeSetInstance_ID;
//	}	

    /**
     * cantSurtirTot property.
     */
//    private double cantSurtirTot;
    
    /**
     * Get cantSurtirTot property.
     *@return CantSurtir property.
     */
//    public double getCantSurtirTot() {
//		return cantSurtirTot;
//	}
//  
    /**
     * Set cantSurtirTot property.
     *@param cantSurtir New cantSurtir property.
     */
//	public void setCantSurtirTot(double cantSurtirTot) {
//		this.cantSurtirTot = cantSurtirTot;
//	}
	
    /* ** Fin Atributos para manejo de Lotes aaranda*/
    
    /**
     * Nombre de la imagen almacenada
     */
    private FormFile imagen = null;

    public FormFile getImagen() {
        return imagen;
    }

    public void setImagen(FormFile imagen) {
        this.imagen = imagen;
    }

    /**
     * Anotaciones relacionadas con la imagen
     */
    private String anotaciones;

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }

    private String productoValue = null;

	public String getProductoValue() {
		return productoValue;
	}

	public void setProductoValue(String productoValue) {
		this.productoValue = productoValue;
	}
	
	private boolean conFoto = false;
	private String ligaFoto;

	public String getLigaFoto() {
		return ligaFoto;
	}

	public void setLigaFoto(String ligaFoto) {
		this.ligaFoto = ligaFoto;
	}

	public boolean isConFoto() {
		return conFoto;
	}

	public void setConFoto(boolean conFoto) {
		this.conFoto = conFoto;
	}
	
	
	
	/**
     * Ruta para RIS
     */
    private int risID;

    public int getRisID() {
        return risID;
    }

    public void setRisID(int risID) {
        this.risID = risID;
    }
    
   
	private boolean surtido = false;

	public boolean isSurtido() {
		return surtido;
	}

	public void setSurtido(boolean surtido) {
		this.surtido = surtido;
	}
	
	/**
	 * Indica si la linea esta activa en la pantalla.
	 * @return
	 */
	
	private boolean activo = false;

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * Indica la accion a realizar para el servicio.
	 * @return
	 */
	
	private String accion = "N";

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	/**
	 * Contiene la lista de valores de el estado.
	 * @return
	 */
	
//	private List<ValueNamePair> estado = new ArrayList<ValueNamePair>();
//
//	public List<ValueNamePair> getEstado() {
//		return estado;
//	}
//
//	public void setEstado(List<ValueNamePair> estado) {
//		this.estado = estado;
//	}
	
	// Lama: usar modelo
	private MEXMEActPacienteInd model ;

	public MEXMEActPacienteInd getModel(Properties ctx) {
		
		if(model == null && xXActPacienteIndID > 0){
			model = new MEXMEActPacienteInd(ctx, (int)xXActPacienteIndID, null);
		}
		return model;
	}
	
	public MEXMEActPacienteInd getModel() {
		return model;
	}

	public void setModel(MEXMEActPacienteInd model) {
		this.model = model;
	}
	
	public void setFromModel(MEXMEActPacienteInd model) {
		this.model = model;
		if (model != null) {
			setXXActPacienteIndHID(model.getEXME_ActPacienteIndH_ID());
			setXXActPacienteIndID(model.getEXME_ActPacienteInd_ID());
			setLinea(model.getLine());
			setCantPedida(model.getQtyOrdered().intValue());
			setDescripcion(model.getDescription());
			setPrecio(model.getPriceActual().doubleValue());
			setCantSurtir(model.getQtyOrdered().intValue());
			setAnotaciones(model.getAnotaciones());
			final String imagen = model.getImagen();
			if (imagen != null) {
				setConFoto(true);
				setLigaFoto("/eCareSoftWeb/ece/imagenes/" + imagen);
			}
			// setFolio(model.getFolio());
			setSurtir(model.isSurtir());
			setSurtido(MEXMEActPacienteInd.ESTATUS_CompletedService.equals(model.getEstatus()));
			setActivo(model.isActive());
			if (model.getEstatus() == null) {
				setAccion(MEXMEActPacienteInd.ESTATUS_RequestedService);
			} else {
				setAccion(model.getEstatus());
			}
			setUom(model.getUomName());
		}
	}
}