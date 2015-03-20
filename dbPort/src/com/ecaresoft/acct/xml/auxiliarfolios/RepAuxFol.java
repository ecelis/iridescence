//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.01.22 a las 08:16:39 AM CST 
//


package com.ecaresoft.acct.xml.auxiliarfolios;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ecaresoft.acct.xml.CMoneda;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DetAuxFol" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ComprNal" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="UUID_CFDI" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;whiteSpace value="collapse"/&gt;
 *                                 &lt;length value="36"/&gt;
 *                                 &lt;pattern value="[a-f0-9A-F]{8}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{12}"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarFolios}t_importe" /&gt;
 *                           &lt;attribute name="RFC" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;minLength value="12"/&gt;
 *                                 &lt;maxLength value="13"/&gt;
 *                                 &lt;whiteSpace value="collapse"/&gt;
 *                                 &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="MetPagoAux" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
 *                           &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
 *                           &lt;attribute name="TipCamb"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                                 &lt;totalDigits value="19"/&gt;
 *                                 &lt;fractionDigits value="5"/&gt;
 *                                 &lt;minInclusive value="0"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ComprNalOtr" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="CFD_CBB_Serie"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;minLength value="1"/&gt;
 *                                 &lt;maxLength value="10"/&gt;
 *                                 &lt;pattern value="[A-Z]+"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="CFD_CBB_NumFol" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                                 &lt;totalDigits value="20"/&gt;
 *                                 &lt;minInclusive value="1"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarFolios}t_importe" /&gt;
 *                           &lt;attribute name="RFC" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;minLength value="12"/&gt;
 *                                 &lt;maxLength value="13"/&gt;
 *                                 &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="MetPagoAux" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
 *                           &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
 *                           &lt;attribute name="TipCamb"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                                 &lt;minInclusive value="0"/&gt;
 *                                 &lt;totalDigits value="19"/&gt;
 *                                 &lt;fractionDigits value="5"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ComprExt" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="NumFactExt" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;whiteSpace value="collapse"/&gt;
 *                                 &lt;minLength value="1"/&gt;
 *                                 &lt;maxLength value="36"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="TaxID"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;minLength value="1"/&gt;
 *                                 &lt;maxLength value="30"/&gt;
 *                                 &lt;whiteSpace value="collapse"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarFolios}t_importe" /&gt;
 *                           &lt;attribute name="MetPagoAux" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
 *                           &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
 *                           &lt;attribute name="TipCamb"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                                 &lt;minInclusive value="0"/&gt;
 *                                 &lt;totalDigits value="19"/&gt;
 *                                 &lt;fractionDigits value="5"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="NumUnIdenPol" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;minLength value="1"/&gt;
 *                       &lt;maxLength value="50"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *                 &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.2" /&gt;
 *       &lt;attribute name="RFC" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;minLength value="12"/&gt;
 *             &lt;maxLength value="13"/&gt;
 *             &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Mes" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="01"/&gt;
 *             &lt;enumeration value="02"/&gt;
 *             &lt;enumeration value="03"/&gt;
 *             &lt;enumeration value="04"/&gt;
 *             &lt;enumeration value="05"/&gt;
 *             &lt;enumeration value="06"/&gt;
 *             &lt;enumeration value="07"/&gt;
 *             &lt;enumeration value="08"/&gt;
 *             &lt;enumeration value="09"/&gt;
 *             &lt;enumeration value="10"/&gt;
 *             &lt;enumeration value="11"/&gt;
 *             &lt;enumeration value="12"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Anio" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *             &lt;minInclusive value="2015"/&gt;
 *             &lt;maxInclusive value="2099"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="TipoSolicitud" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="AF|DE|CO|FC"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="NumOrden"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;length value="13"/&gt;
 *             &lt;pattern value="[A-Z]{3}[0-6][0-9][0-9]{5}(/)[0-9]{2}"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="NumTramite"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;length value="10"/&gt;
 *             &lt;pattern value="[0-9]{10}"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Sello"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;whiteSpace value="collapse"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="noCertificado"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;length value="20"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="Certificado"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;whiteSpace value="collapse"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "detAuxFol"
})
@XmlRootElement(name = "RepAuxFol")
public class RepAuxFol {

    @XmlElement(name = "DetAuxFol")
    protected List<RepAuxFol.DetAuxFol> detAuxFol;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "RFC", required = true)
    protected String rfc;
    @XmlAttribute(name = "Mes", required = true)
    protected String mes;
    @XmlAttribute(name = "Anio", required = true)
    protected int anio;
    @XmlAttribute(name = "TipoSolicitud", required = true)
    protected String tipoSolicitud;
    @XmlAttribute(name = "NumOrden")
    protected String numOrden;
    @XmlAttribute(name = "NumTramite")
    protected String numTramite;
    @XmlAttribute(name = "Sello")
    protected String sello;
    @XmlAttribute(name = "noCertificado")
    protected String noCertificado;
    @XmlAttribute(name = "Certificado")
    protected String certificado;

    /**
     * Gets the value of the detAuxFol property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detAuxFol property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetAuxFol().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RepAuxFol.DetAuxFol }
     * 
     * 
     */
    public List<RepAuxFol.DetAuxFol> getDetAuxFol() {
        if (detAuxFol == null) {
            detAuxFol = new ArrayList<RepAuxFol.DetAuxFol>();
        }
        return this.detAuxFol;
    }

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        if (version == null) {
            return "1.2";
        } else {
            return version;
        }
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtiene el valor de la propiedad rfc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRFC() {
        return rfc;
    }

    /**
     * Define el valor de la propiedad rfc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRFC(String value) {
        this.rfc = value;
    }

    /**
     * Obtiene el valor de la propiedad mes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMes() {
        return mes;
    }

    /**
     * Define el valor de la propiedad mes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMes(String value) {
        this.mes = value;
    }

    /**
     * Obtiene el valor de la propiedad anio.
     * 
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Define el valor de la propiedad anio.
     * 
     */
    public void setAnio(int value) {
        this.anio = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoSolicitud.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    /**
     * Define el valor de la propiedad tipoSolicitud.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSolicitud(String value) {
        this.tipoSolicitud = value;
    }

    /**
     * Obtiene el valor de la propiedad numOrden.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumOrden() {
        return numOrden;
    }

    /**
     * Define el valor de la propiedad numOrden.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumOrden(String value) {
        this.numOrden = value;
    }

    /**
     * Obtiene el valor de la propiedad numTramite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumTramite() {
        return numTramite;
    }

    /**
     * Define el valor de la propiedad numTramite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumTramite(String value) {
        this.numTramite = value;
    }

    /**
     * Obtiene el valor de la propiedad sello.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSello() {
        return sello;
    }

    /**
     * Define el valor de la propiedad sello.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSello(String value) {
        this.sello = value;
    }

    /**
     * Obtiene el valor de la propiedad noCertificado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoCertificado() {
        return noCertificado;
    }

    /**
     * Define el valor de la propiedad noCertificado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoCertificado(String value) {
        this.noCertificado = value;
    }

    /**
     * Obtiene el valor de la propiedad certificado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificado() {
        return certificado;
    }

    /**
     * Define el valor de la propiedad certificado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificado(String value) {
        this.certificado = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="ComprNal" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="UUID_CFDI" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;whiteSpace value="collapse"/&gt;
     *                       &lt;length value="36"/&gt;
     *                       &lt;pattern value="[a-f0-9A-F]{8}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{12}"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarFolios}t_importe" /&gt;
     *                 &lt;attribute name="RFC" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;minLength value="12"/&gt;
     *                       &lt;maxLength value="13"/&gt;
     *                       &lt;whiteSpace value="collapse"/&gt;
     *                       &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="MetPagoAux" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
     *                 &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
     *                 &lt;attribute name="TipCamb"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *                       &lt;totalDigits value="19"/&gt;
     *                       &lt;fractionDigits value="5"/&gt;
     *                       &lt;minInclusive value="0"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ComprNalOtr" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="CFD_CBB_Serie"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;minLength value="1"/&gt;
     *                       &lt;maxLength value="10"/&gt;
     *                       &lt;pattern value="[A-Z]+"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="CFD_CBB_NumFol" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *                       &lt;totalDigits value="20"/&gt;
     *                       &lt;minInclusive value="1"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarFolios}t_importe" /&gt;
     *                 &lt;attribute name="RFC" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;minLength value="12"/&gt;
     *                       &lt;maxLength value="13"/&gt;
     *                       &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="MetPagoAux" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
     *                 &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
     *                 &lt;attribute name="TipCamb"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *                       &lt;minInclusive value="0"/&gt;
     *                       &lt;totalDigits value="19"/&gt;
     *                       &lt;fractionDigits value="5"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ComprExt" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="NumFactExt" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;whiteSpace value="collapse"/&gt;
     *                       &lt;minLength value="1"/&gt;
     *                       &lt;maxLength value="36"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="TaxID"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;minLength value="1"/&gt;
     *                       &lt;maxLength value="30"/&gt;
     *                       &lt;whiteSpace value="collapse"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarFolios}t_importe" /&gt;
     *                 &lt;attribute name="MetPagoAux" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
     *                 &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
     *                 &lt;attribute name="TipCamb"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *                       &lt;minInclusive value="0"/&gt;
     *                       &lt;totalDigits value="19"/&gt;
     *                       &lt;fractionDigits value="5"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="NumUnIdenPol" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;minLength value="1"/&gt;
     *             &lt;maxLength value="50"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *       &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "comprNal",
        "comprNalOtr",
        "comprExt"
    })
    public static class DetAuxFol {

        @XmlElement(name = "ComprNal")
        protected List<RepAuxFol.DetAuxFol.ComprNal> comprNal;
        @XmlElement(name = "ComprNalOtr")
        protected List<RepAuxFol.DetAuxFol.ComprNalOtr> comprNalOtr;
        @XmlElement(name = "ComprExt")
        protected List<RepAuxFol.DetAuxFol.ComprExt> comprExt;
        @XmlAttribute(name = "NumUnIdenPol", required = true)
        protected String numUnIdenPol;
        @XmlAttribute(name = "Fecha", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fecha;

        /**
         * Gets the value of the comprNal property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the comprNal property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComprNal().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RepAuxFol.DetAuxFol.ComprNal }
         * 
         * 
         */
        public List<RepAuxFol.DetAuxFol.ComprNal> getComprNal() {
            if (comprNal == null) {
                comprNal = new ArrayList<RepAuxFol.DetAuxFol.ComprNal>();
            }
            return this.comprNal;
        }

        /**
         * Gets the value of the comprNalOtr property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the comprNalOtr property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComprNalOtr().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RepAuxFol.DetAuxFol.ComprNalOtr }
         * 
         * 
         */
        public List<RepAuxFol.DetAuxFol.ComprNalOtr> getComprNalOtr() {
            if (comprNalOtr == null) {
                comprNalOtr = new ArrayList<RepAuxFol.DetAuxFol.ComprNalOtr>();
            }
            return this.comprNalOtr;
        }

        /**
         * Gets the value of the comprExt property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the comprExt property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComprExt().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RepAuxFol.DetAuxFol.ComprExt }
         * 
         * 
         */
        public List<RepAuxFol.DetAuxFol.ComprExt> getComprExt() {
            if (comprExt == null) {
                comprExt = new ArrayList<RepAuxFol.DetAuxFol.ComprExt>();
            }
            return this.comprExt;
        }

        /**
         * Obtiene el valor de la propiedad numUnIdenPol.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumUnIdenPol() {
            return numUnIdenPol;
        }

        /**
         * Define el valor de la propiedad numUnIdenPol.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumUnIdenPol(String value) {
            this.numUnIdenPol = value;
        }

        /**
         * Obtiene el valor de la propiedad fecha.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFecha() {
            return fecha;
        }

        /**
         * Define el valor de la propiedad fecha.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFecha(XMLGregorianCalendar value) {
            this.fecha = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;attribute name="NumFactExt" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;whiteSpace value="collapse"/&gt;
         *             &lt;minLength value="1"/&gt;
         *             &lt;maxLength value="36"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="TaxID"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;minLength value="1"/&gt;
         *             &lt;maxLength value="30"/&gt;
         *             &lt;whiteSpace value="collapse"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarFolios}t_importe" /&gt;
         *       &lt;attribute name="MetPagoAux" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
         *       &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
         *       &lt;attribute name="TipCamb"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
         *             &lt;minInclusive value="0"/&gt;
         *             &lt;totalDigits value="19"/&gt;
         *             &lt;fractionDigits value="5"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ComprExt {

            @XmlAttribute(name = "NumFactExt", required = true)
            protected String numFactExt;
            @XmlAttribute(name = "TaxID")
            protected String taxID;
            @XmlAttribute(name = "MontoTotal", required = true)
            protected BigDecimal montoTotal;
            @XmlAttribute(name = "MetPagoAux")
            protected String metPagoAux;
            @XmlAttribute(name = "Moneda")
            protected CMoneda moneda;
            @XmlAttribute(name = "TipCamb")
            protected BigDecimal tipCamb;

            /**
             * Obtiene el valor de la propiedad numFactExt.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNumFactExt() {
                return numFactExt;
            }

            /**
             * Define el valor de la propiedad numFactExt.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNumFactExt(String value) {
                this.numFactExt = value;
            }

            /**
             * Obtiene el valor de la propiedad taxID.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTaxID() {
                return taxID;
            }

            /**
             * Define el valor de la propiedad taxID.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTaxID(String value) {
                this.taxID = value;
            }

            /**
             * Obtiene el valor de la propiedad montoTotal.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getMontoTotal() {
                return montoTotal;
            }

            /**
             * Define el valor de la propiedad montoTotal.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setMontoTotal(BigDecimal value) {
                this.montoTotal = value;
            }

            /**
             * Obtiene el valor de la propiedad metPagoAux.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMetPagoAux() {
                return metPagoAux;
            }

            /**
             * Define el valor de la propiedad metPagoAux.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMetPagoAux(String value) {
                this.metPagoAux = value;
            }

            /**
             * Obtiene el valor de la propiedad moneda.
             * 
             * @return
             *     possible object is
             *     {@link CMoneda }
             *     
             */
            public CMoneda getMoneda() {
                return moneda;
            }

            /**
             * Define el valor de la propiedad moneda.
             * 
             * @param value
             *     allowed object is
             *     {@link CMoneda }
             *     
             */
            public void setMoneda(CMoneda value) {
                this.moneda = value;
            }

            /**
             * Obtiene el valor de la propiedad tipCamb.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTipCamb() {
                return tipCamb;
            }

            /**
             * Define el valor de la propiedad tipCamb.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTipCamb(BigDecimal value) {
                this.tipCamb = value;
            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;attribute name="UUID_CFDI" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;whiteSpace value="collapse"/&gt;
         *             &lt;length value="36"/&gt;
         *             &lt;pattern value="[a-f0-9A-F]{8}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{12}"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarFolios}t_importe" /&gt;
         *       &lt;attribute name="RFC" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;minLength value="12"/&gt;
         *             &lt;maxLength value="13"/&gt;
         *             &lt;whiteSpace value="collapse"/&gt;
         *             &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="MetPagoAux" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
         *       &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
         *       &lt;attribute name="TipCamb"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
         *             &lt;totalDigits value="19"/&gt;
         *             &lt;fractionDigits value="5"/&gt;
         *             &lt;minInclusive value="0"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ComprNal {

            @XmlAttribute(name = "UUID_CFDI", required = true)
            protected String uuidcfdi;
            @XmlAttribute(name = "MontoTotal", required = true)
            protected BigDecimal montoTotal;
            @XmlAttribute(name = "RFC", required = true)
            protected String rfc;
            @XmlAttribute(name = "MetPagoAux")
            protected String metPagoAux;
            @XmlAttribute(name = "Moneda")
            protected CMoneda moneda;
            @XmlAttribute(name = "TipCamb")
            protected BigDecimal tipCamb;

            /**
             * Obtiene el valor de la propiedad uuidcfdi.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUUIDCFDI() {
                return uuidcfdi;
            }

            /**
             * Define el valor de la propiedad uuidcfdi.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUUIDCFDI(String value) {
                this.uuidcfdi = value;
            }

            /**
             * Obtiene el valor de la propiedad montoTotal.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getMontoTotal() {
                return montoTotal;
            }

            /**
             * Define el valor de la propiedad montoTotal.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setMontoTotal(BigDecimal value) {
                this.montoTotal = value;
            }

            /**
             * Obtiene el valor de la propiedad rfc.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRFC() {
                return rfc;
            }

            /**
             * Define el valor de la propiedad rfc.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRFC(String value) {
                this.rfc = value;
            }

            /**
             * Obtiene el valor de la propiedad metPagoAux.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMetPagoAux() {
                return metPagoAux;
            }

            /**
             * Define el valor de la propiedad metPagoAux.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMetPagoAux(String value) {
                this.metPagoAux = value;
            }

            /**
             * Obtiene el valor de la propiedad moneda.
             * 
             * @return
             *     possible object is
             *     {@link CMoneda }
             *     
             */
            public CMoneda getMoneda() {
                return moneda;
            }

            /**
             * Define el valor de la propiedad moneda.
             * 
             * @param value
             *     allowed object is
             *     {@link CMoneda }
             *     
             */
            public void setMoneda(CMoneda value) {
                this.moneda = value;
            }

            /**
             * Obtiene el valor de la propiedad tipCamb.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTipCamb() {
                return tipCamb;
            }

            /**
             * Define el valor de la propiedad tipCamb.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTipCamb(BigDecimal value) {
                this.tipCamb = value;
            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;attribute name="CFD_CBB_Serie"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;minLength value="1"/&gt;
         *             &lt;maxLength value="10"/&gt;
         *             &lt;pattern value="[A-Z]+"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="CFD_CBB_NumFol" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
         *             &lt;totalDigits value="20"/&gt;
         *             &lt;minInclusive value="1"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarFolios}t_importe" /&gt;
         *       &lt;attribute name="RFC" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;minLength value="12"/&gt;
         *             &lt;maxLength value="13"/&gt;
         *             &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="MetPagoAux" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
         *       &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
         *       &lt;attribute name="TipCamb"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
         *             &lt;minInclusive value="0"/&gt;
         *             &lt;totalDigits value="19"/&gt;
         *             &lt;fractionDigits value="5"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class ComprNalOtr {

            @XmlAttribute(name = "CFD_CBB_Serie")
            protected String cfdcbbSerie;
            @XmlAttribute(name = "CFD_CBB_NumFol", required = true)
            protected BigInteger cfdcbbNumFol;
            @XmlAttribute(name = "MontoTotal", required = true)
            protected BigDecimal montoTotal;
            @XmlAttribute(name = "RFC", required = true)
            protected String rfc;
            @XmlAttribute(name = "MetPagoAux")
            protected String metPagoAux;
            @XmlAttribute(name = "Moneda")
            protected CMoneda moneda;
            @XmlAttribute(name = "TipCamb")
            protected BigDecimal tipCamb;

            /**
             * Obtiene el valor de la propiedad cfdcbbSerie.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCFDCBBSerie() {
                return cfdcbbSerie;
            }

            /**
             * Define el valor de la propiedad cfdcbbSerie.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCFDCBBSerie(String value) {
                this.cfdcbbSerie = value;
            }

            /**
             * Obtiene el valor de la propiedad cfdcbbNumFol.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getCFDCBBNumFol() {
                return cfdcbbNumFol;
            }

            /**
             * Define el valor de la propiedad cfdcbbNumFol.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setCFDCBBNumFol(BigInteger value) {
                this.cfdcbbNumFol = value;
            }

            /**
             * Obtiene el valor de la propiedad montoTotal.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getMontoTotal() {
                return montoTotal;
            }

            /**
             * Define el valor de la propiedad montoTotal.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setMontoTotal(BigDecimal value) {
                this.montoTotal = value;
            }

            /**
             * Obtiene el valor de la propiedad rfc.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRFC() {
                return rfc;
            }

            /**
             * Define el valor de la propiedad rfc.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRFC(String value) {
                this.rfc = value;
            }

            /**
             * Obtiene el valor de la propiedad metPagoAux.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMetPagoAux() {
                return metPagoAux;
            }

            /**
             * Define el valor de la propiedad metPagoAux.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMetPagoAux(String value) {
                this.metPagoAux = value;
            }

            /**
             * Obtiene el valor de la propiedad moneda.
             * 
             * @return
             *     possible object is
             *     {@link CMoneda }
             *     
             */
            public CMoneda getMoneda() {
                return moneda;
            }

            /**
             * Define el valor de la propiedad moneda.
             * 
             * @param value
             *     allowed object is
             *     {@link CMoneda }
             *     
             */
            public void setMoneda(CMoneda value) {
                this.moneda = value;
            }

            /**
             * Obtiene el valor de la propiedad tipCamb.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTipCamb() {
                return tipCamb;
            }

            /**
             * Define el valor de la propiedad tipCamb.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTipCamb(BigDecimal value) {
                this.tipCamb = value;
            }

        }

    }

}
