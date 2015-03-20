package org.compiere.model;

import java.math.BigDecimal;

import org.compiere.util.Env;

/**
 * Modelo para documentos comunes (factura, cta paciente, etc)
 * <p>
 * Modificado por:  $Author: gisela $<p>
 * Fecha:  $Date: 2006/08/30 00:54:08 $<p>
 *
 * @author Gisela Lee
 * @version $Revision: 1.1 $
 */
public class DocumentView {
    int scale = 2; // decimales

    /**
     * Propiedad DocumentID.
     */
    private int documentID = 0;

    /**
     * Obtener la propiedad documentID.
     * 
     *@return La propiedad DocumentID.
     */
    public int getDocumentID() {
        return this.documentID;
    }

    /**
     * Establecer la propiedad documentID.
     * 
     *@param documentID Nueva propiedad documentID.
     */
    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

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
     * Propiedad ClienteID.
     */
    private int clienteID = 0;

    /**
     * Obtener la propiedad clienteID.
     * 
     *@return La propiedad ClienteID.
     */
    public int getClienteID() {
        return this.clienteID;
    }

    /**
     * Establecer la propiedad clienteID.
     * 
     *@param clienteID Nueva propiedad clienteID.
     */
    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    /**
     * Propiedad ClienteValue.
     */
    private String clienteValue = null;

    /**
     * Obtener la propiedad clienteValue.
     * 
     *@return La propiedad ClienteValue.
     */
    public String getClienteValue() {
        return this.clienteValue;
    }

    /**
     * Establecer la propiedad clienteValue.
     * 
     *@param clienteValue Nueva propiedad clienteValue.
     */
    public void setClienteValue(String clienteValue) {
        this.clienteValue = clienteValue;
    }

    /**
     * Propiedad ClienteName.
     */
    private String clienteName = null;

    /**
     * Obtener la propiedad clienteName.
     * 
     *@return La propiedad ClienteName.
     */
    public String getClienteName() {
        return this.clienteName;
    }

    /**
     * Establecer la propiedad clienteName.
     * 
     *@param clienteName Nueva propiedad clienteName.
     */
    public void setClienteName(String clienteName) {
        this.clienteName = clienteName;
    }

    /**
     * Propiedad PacienteName.
     */
    private String pacienteName = null;

    /**
     * Obtener la propiedad pacienteName.
     * 
     *@return La propiedad PacienteName.
     */
    public String getPacienteName() {
        return pacienteName;
    }

    /**
     * Establecer la propiedad pacienteName.
     * 
     *@param pacienteName Nueva propiedad pacienteName.
     */
    public void setPacienteName(String pacienteName) {
        this.pacienteName = pacienteName;
    }

    /**
     * Propiedad TotalDocto.
     */
    private BigDecimal totalDocto = Env.ZERO;

    /**
     * Obtener la propiedad totalDocto.
     * 
     *@return La propiedad TotalDocto.
     */
    public BigDecimal getTotalDocto() {
        return this.totalDocto;
    }

    /**
     * Establecer la propiedad totalDocto.
     * 
     *@param totalDocto Nueva propiedad totalDocto.
     */
    public void setTotalDocto(BigDecimal totalDocto) {
        this.totalDocto = totalDocto;
        this.totalDocto = this.totalDocto.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Propiedad Saldo.
     */
    private BigDecimal saldo = Env.ZERO;

    /**
     * Obtener la propiedad saldo.
     * 
     *@return La propiedad Saldo.
     */
    public BigDecimal getSaldo() {
        return this.saldo;
    }

    /**
     * Establecer la propiedad saldo.
     * 
     *@param saldo Nueva propiedad saldo.
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
        this.saldo = this.saldo.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Propiedad MonedaID.
     */
    private int monedaID = 0;

    /**
     * Obtener la propiedad monedaID.
     * 
     *@return La propiedad MonedaID.
     */
    public int getMonedaID() {
        return this.monedaID;
    }

    /**
     * Establecer la propiedad monedaID.
     * 
     *@param monedaID Nueva propiedad monedaID.
     */
    public void setMonedaID(int monedaID) {
        this.monedaID = monedaID;
    }

    /**
     * Propiedad ConversionTypeID.
     */
    private long conversionTypeID = 0;

    /**
     * Obtener la propiedad conversionTypeID.
     * 
     *@return La propiedad ConversionTypeID.
     */
    public long getConversionTypeID() {
        return this.conversionTypeID;
    }

    /**
     * Establecer la propiedad conversionTypeID.
     * 
     *@param conversionTypeID Nueva propiedad conversionTypeID.
     */
    public void setConversionTypeID(long conversionTypeID) {
        this.conversionTypeID = conversionTypeID;
    }

    /**
     * Propiedad DocTypeID.
     */
    private int docTypeID = 0;

    /**
     * Obtener la propiedad docTypeID.
     * 
     *@return La propiedad DocTypeID.
     */
    public int getDocTypeID() {
        return this.docTypeID;
    }

    /**
     * Establecer la propiedad docTypeID.
     * 
     *@param docTypeID Nueva propiedad docTypeID.
     */
    public void setDocTypeID(int docTypeID) {
        this.docTypeID = docTypeID;
    }

}
