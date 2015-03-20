package org.compiere.model;

import java.util.Date;

/**
 * Modelo para el cierre de cuenta paciente
 * <p>
 * Modificado por: $Author: gisela $ <p>
 * Fecha: $Date: 2006/08/30 00:52:59 $ <p>
 *
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.1 $
 */
public class CierreView extends EncCgosCPView {

    /**
    * Propiedad DocumentNo.
    */
    private String documentNo = null;

    /**
    * Obtener la propiedad documentNo.
    * 
    *@return La propiedad DocumentNo.
    */
    public String getDocumentNo() {
        return this.documentNo;
    }

    /**
    * Establecer la propiedad documentNo.
    * 
    *@param documentNo Nueva propiedad documentNo.
    */
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    /**
     * Propiedad Historia.
     */
    private String historia = null;

    /**
     * Obtener la propiedad historia.
     * 
     *@return La propiedad Historia.
     */
    public String getHistoria() {
        return this.historia;
    }

    /**
     * Establecer la propiedad historia.
     * 
     *@param historia Nueva propiedad historia.
     */
    public void setHistoria(String historia) {
        this.historia = historia;
    }

    /**
    * Propiedad DateOrdered.
    */
    private Date dateOrdered = null;

    /**
    * Obtener la propiedad dateOrdered.
    * 
    *@return La propiedad DateOrdered.
    */
    public Date getDateOrdered() {
        return this.dateOrdered;
    }

    /**
    * Establecer la propiedad dateOrdered.
    * 
    *@param dateOrdered Nueva propiedad dateOrdered.
    */
    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

}