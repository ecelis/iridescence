//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.01.22 a las 08:16:25 AM CST 
//


package com.ecaresoft.acct.xml.auxcuentas;

import java.math.BigDecimal;
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


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element name="Cuenta" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded"&gt;
 *                   &lt;element name="DetalleAux" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *                           &lt;attribute name="NumUnIdenPol" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;minLength value="1"/&gt;
 *                                 &lt;maxLength value="50"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="Concepto" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;minLength value="1"/&gt;
 *                                 &lt;maxLength value="200"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="Debe" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
 *                           &lt;attribute name="Haber" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="NumCta" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;minLength value="1"/&gt;
 *                       &lt;maxLength value="100"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *                 &lt;attribute name="DesCta" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;minLength value="1"/&gt;
 *                       &lt;maxLength value="100"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *                 &lt;attribute name="SaldoIni" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
 *                 &lt;attribute name="SaldoFin" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.1" /&gt;
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
 *             &lt;pattern value="AF|FC|DE|CO"/&gt;
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
    "cuenta"
})
@XmlRootElement(name = "AuxiliarCtas")
public class AuxiliarCtas {

    @XmlElement(name = "Cuenta", required = true)
    protected List<AuxiliarCtas.Cuenta> cuenta;
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
     * Gets the value of the cuenta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuenta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuenta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuxiliarCtas.Cuenta }
     * 
     * 
     */
    public List<AuxiliarCtas.Cuenta> getCuenta() {
        if (cuenta == null) {
            cuenta = new ArrayList<AuxiliarCtas.Cuenta>();
        }
        return this.cuenta;
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
            return "1.1";
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
     *       &lt;sequence maxOccurs="unbounded"&gt;
     *         &lt;element name="DetalleAux" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
     *                 &lt;attribute name="NumUnIdenPol" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;minLength value="1"/&gt;
     *                       &lt;maxLength value="50"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="Concepto" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;minLength value="1"/&gt;
     *                       &lt;maxLength value="200"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="Debe" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
     *                 &lt;attribute name="Haber" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="NumCta" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;minLength value="1"/&gt;
     *             &lt;maxLength value="100"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *       &lt;attribute name="DesCta" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;minLength value="1"/&gt;
     *             &lt;maxLength value="100"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *       &lt;attribute name="SaldoIni" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
     *       &lt;attribute name="SaldoFin" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "detalleAux"
    })
    public static class Cuenta {

        @XmlElement(name = "DetalleAux", required = true)
        protected List<AuxiliarCtas.Cuenta.DetalleAux> detalleAux;
        @XmlAttribute(name = "NumCta", required = true)
        protected String numCta;
        @XmlAttribute(name = "DesCta", required = true)
        protected String desCta;
        @XmlAttribute(name = "SaldoIni", required = true)
        protected BigDecimal saldoIni;
        @XmlAttribute(name = "SaldoFin", required = true)
        protected BigDecimal saldoFin;

        /**
         * Gets the value of the detalleAux property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the detalleAux property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDetalleAux().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AuxiliarCtas.Cuenta.DetalleAux }
         * 
         * 
         */
        public List<AuxiliarCtas.Cuenta.DetalleAux> getDetalleAux() {
            if (detalleAux == null) {
                detalleAux = new ArrayList<AuxiliarCtas.Cuenta.DetalleAux>();
            }
            return this.detalleAux;
        }

        /**
         * Obtiene el valor de la propiedad numCta.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumCta() {
            return numCta;
        }

        /**
         * Define el valor de la propiedad numCta.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumCta(String value) {
            this.numCta = value;
        }

        /**
         * Obtiene el valor de la propiedad desCta.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDesCta() {
            return desCta;
        }

        /**
         * Define el valor de la propiedad desCta.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDesCta(String value) {
            this.desCta = value;
        }

        /**
         * Obtiene el valor de la propiedad saldoIni.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getSaldoIni() {
            return saldoIni;
        }

        /**
         * Define el valor de la propiedad saldoIni.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setSaldoIni(BigDecimal value) {
            this.saldoIni = value;
        }

        /**
         * Obtiene el valor de la propiedad saldoFin.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getSaldoFin() {
            return saldoFin;
        }

        /**
         * Define el valor de la propiedad saldoFin.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setSaldoFin(BigDecimal value) {
            this.saldoFin = value;
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
         *       &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
         *       &lt;attribute name="NumUnIdenPol" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;minLength value="1"/&gt;
         *             &lt;maxLength value="50"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="Concepto" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;minLength value="1"/&gt;
         *             &lt;maxLength value="200"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="Debe" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
         *       &lt;attribute name="Haber" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/AuxiliarCtas}t_importe" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class DetalleAux {

            @XmlAttribute(name = "Fecha", required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar fecha;
            @XmlAttribute(name = "NumUnIdenPol", required = true)
            protected String numUnIdenPol;
            @XmlAttribute(name = "Concepto", required = true)
            protected String concepto;
            @XmlAttribute(name = "Debe", required = true)
            protected BigDecimal debe;
            @XmlAttribute(name = "Haber", required = true)
            protected BigDecimal haber;

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
             * Obtiene el valor de la propiedad concepto.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getConcepto() {
                return concepto;
            }

            /**
             * Define el valor de la propiedad concepto.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setConcepto(String value) {
                this.concepto = value;
            }

            /**
             * Obtiene el valor de la propiedad debe.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDebe() {
                return debe;
            }

            /**
             * Define el valor de la propiedad debe.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDebe(BigDecimal value) {
                this.debe = value;
            }

            /**
             * Obtiene el valor de la propiedad haber.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getHaber() {
                return haber;
            }

            /**
             * Define el valor de la propiedad haber.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setHaber(BigDecimal value) {
                this.haber = value;
            }

        }

    }

}
