//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.01.22 a las 08:17:21 AM CST 
//


package com.ecaresoft.acct.xml.polizas;

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
 *         &lt;element name="Poliza" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Transaccion" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="CompNal" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;attribute name="UUID_CFDI" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;whiteSpace value="collapse"/&gt;
 *                                           &lt;length value="36"/&gt;
 *                                           &lt;pattern value="[a-f0-9A-F]{8}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{4}-[a-f0-9A-F]{12}"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="RFC" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="12"/&gt;
 *                                           &lt;maxLength value="13"/&gt;
 *                                           &lt;whiteSpace value="collapse"/&gt;
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
 *                                     &lt;attribute name="TipCamb"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                                           &lt;minInclusive value="0"/&gt;
 *                                           &lt;totalDigits value="19"/&gt;
 *                                           &lt;fractionDigits value="5"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="CompNalOtr" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;attribute name="CFD_CBB_Serie"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                           &lt;maxLength value="10"/&gt;
 *                                           &lt;pattern value="[A-Z]+"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="CFD_CBB_NumFol" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                                           &lt;minInclusive value="1"/&gt;
 *                                           &lt;totalDigits value="20"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="RFC" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="12"/&gt;
 *                                           &lt;maxLength value="13"/&gt;
 *                                           &lt;whiteSpace value="collapse"/&gt;
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
 *                                     &lt;attribute name="TipCamb"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                                           &lt;minInclusive value="0"/&gt;
 *                                           &lt;totalDigits value="19"/&gt;
 *                                           &lt;fractionDigits value="5"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="CompExt" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;attribute name="NumFactExt" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;whiteSpace value="collapse"/&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                           &lt;maxLength value="36"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="TaxID"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                           &lt;maxLength value="30"/&gt;
 *                                           &lt;whiteSpace value="collapse"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
 *                                     &lt;attribute name="TipCamb"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                                           &lt;minInclusive value="0"/&gt;
 *                                           &lt;totalDigits value="19"/&gt;
 *                                           &lt;fractionDigits value="5"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="Cheque" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;attribute name="Num" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;maxLength value="20"/&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="BanEmisNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
 *                                     &lt;attribute name="BanEmisExt"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;maxLength value="150"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="CtaOri" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;maxLength value="50"/&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *                                     &lt;attribute name="Benef" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                           &lt;maxLength value="300"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="RFC" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="12"/&gt;
 *                                           &lt;maxLength value="13"/&gt;
 *                                           &lt;whiteSpace value="collapse"/&gt;
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
 *                                     &lt;attribute name="TipCamb"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                                           &lt;minInclusive value="0"/&gt;
 *                                           &lt;totalDigits value="19"/&gt;
 *                                           &lt;fractionDigits value="5"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="Transferencia" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;attribute name="CtaOri"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                           &lt;maxLength value="50"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="BancoOriNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
 *                                     &lt;attribute name="BancoOriExt"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;maxLength value="150"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="CtaDest" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                           &lt;maxLength value="50"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="BancoDestNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
 *                                     &lt;attribute name="BancoDestExt"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;maxLength value="150"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *                                     &lt;attribute name="Benef" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                           &lt;maxLength value="300"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="RFC" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="12"/&gt;
 *                                           &lt;maxLength value="13"/&gt;
 *                                           &lt;whiteSpace value="collapse"/&gt;
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
 *                                     &lt;attribute name="TipCamb"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                                           &lt;minInclusive value="0"/&gt;
 *                                           &lt;totalDigits value="19"/&gt;
 *                                           &lt;fractionDigits value="5"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="OtrMetodoPago" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;attribute name="MetPagoPol" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
 *                                     &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *                                     &lt;attribute name="Benef" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="1"/&gt;
 *                                           &lt;maxLength value="300"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="RFC" use="required"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                           &lt;minLength value="12"/&gt;
 *                                           &lt;maxLength value="13"/&gt;
 *                                           &lt;whiteSpace value="collapse"/&gt;
 *                                           &lt;pattern value="[A-ZÑ&amp;]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z0-9]?[A-Z0-9]?[0-9A-Z]?"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                     &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
 *                                     &lt;attribute name="Moneda" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Moneda" /&gt;
 *                                     &lt;attribute name="TipCamb"&gt;
 *                                       &lt;simpleType&gt;
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                                           &lt;minInclusive value="0"/&gt;
 *                                           &lt;totalDigits value="19"/&gt;
 *                                           &lt;fractionDigits value="5"/&gt;
 *                                         &lt;/restriction&gt;
 *                                       &lt;/simpleType&gt;
 *                                     &lt;/attribute&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="NumCta" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;minLength value="1"/&gt;
 *                                 &lt;maxLength value="100"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                           &lt;attribute name="DesCta" use="required"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;minLength value="1"/&gt;
 *                                 &lt;maxLength value="100"/&gt;
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
 *                           &lt;attribute name="Debe" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
 *                           &lt;attribute name="Haber" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="NumUnIdenPol" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;maxLength value="50"/&gt;
 *                       &lt;minLength value="1"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *                 &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *                 &lt;attribute name="Concepto" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;minLength value="1"/&gt;
 *                       &lt;maxLength value="300"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
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
 *             &lt;whiteSpace value="collapse"/&gt;
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
    "poliza"
})
@XmlRootElement(name = "Polizas")
public class Polizas {

    @XmlElement(name = "Poliza", required = true)
    protected List<Polizas.Poliza> poliza;
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
     * Gets the value of the poliza property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the poliza property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPoliza().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Polizas.Poliza }
     * 
     * 
     */
    public List<Polizas.Poliza> getPoliza() {
        if (poliza == null) {
            poliza = new ArrayList<Polizas.Poliza>();
        }
        return this.poliza;
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
     *       &lt;sequence&gt;
     *         &lt;element name="Transaccion" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="CompNal" maxOccurs="unbounded" minOccurs="0"&gt;
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
     *                           &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
     *                   &lt;element name="CompNalOtr" maxOccurs="unbounded" minOccurs="0"&gt;
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
     *                                 &lt;minInclusive value="1"/&gt;
     *                                 &lt;totalDigits value="20"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
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
     *                           &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
     *                   &lt;element name="CompExt" maxOccurs="unbounded" minOccurs="0"&gt;
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
     *                           &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
     *                   &lt;element name="Cheque" maxOccurs="unbounded" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;attribute name="Num" use="required"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;maxLength value="20"/&gt;
     *                                 &lt;minLength value="1"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
     *                           &lt;attribute name="BanEmisNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
     *                           &lt;attribute name="BanEmisExt"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;maxLength value="150"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
     *                           &lt;attribute name="CtaOri" use="required"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;maxLength value="50"/&gt;
     *                                 &lt;minLength value="1"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
     *                           &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
     *                           &lt;attribute name="Benef" use="required"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;minLength value="1"/&gt;
     *                                 &lt;maxLength value="300"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
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
     *                           &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
     *                   &lt;element name="Transferencia" maxOccurs="unbounded" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;attribute name="CtaOri"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;minLength value="1"/&gt;
     *                                 &lt;maxLength value="50"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
     *                           &lt;attribute name="BancoOriNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
     *                           &lt;attribute name="BancoOriExt"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;maxLength value="150"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
     *                           &lt;attribute name="CtaDest" use="required"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;minLength value="1"/&gt;
     *                                 &lt;maxLength value="50"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
     *                           &lt;attribute name="BancoDestNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
     *                           &lt;attribute name="BancoDestExt"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;maxLength value="150"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
     *                           &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
     *                           &lt;attribute name="Benef" use="required"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;minLength value="1"/&gt;
     *                                 &lt;maxLength value="300"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
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
     *                           &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
     *                   &lt;element name="OtrMetodoPago" maxOccurs="unbounded" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;attribute name="MetPagoPol" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
     *                           &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
     *                           &lt;attribute name="Benef" use="required"&gt;
     *                             &lt;simpleType&gt;
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                                 &lt;minLength value="1"/&gt;
     *                                 &lt;maxLength value="300"/&gt;
     *                               &lt;/restriction&gt;
     *                             &lt;/simpleType&gt;
     *                           &lt;/attribute&gt;
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
     *                           &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
     *                 &lt;attribute name="Concepto" use="required"&gt;
     *                   &lt;simpleType&gt;
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;minLength value="1"/&gt;
     *                       &lt;maxLength value="200"/&gt;
     *                     &lt;/restriction&gt;
     *                   &lt;/simpleType&gt;
     *                 &lt;/attribute&gt;
     *                 &lt;attribute name="Debe" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
     *                 &lt;attribute name="Haber" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="NumUnIdenPol" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;maxLength value="50"/&gt;
     *             &lt;minLength value="1"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *       &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
     *       &lt;attribute name="Concepto" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;minLength value="1"/&gt;
     *             &lt;maxLength value="300"/&gt;
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
        "transaccion"
    })
    public static class Poliza {

        @XmlElement(name = "Transaccion", required = true)
        protected List<Polizas.Poliza.Transaccion> transaccion;
        @XmlAttribute(name = "NumUnIdenPol", required = true)
        protected String numUnIdenPol;
        @XmlAttribute(name = "Fecha", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fecha;
        @XmlAttribute(name = "Concepto", required = true)
        protected String concepto;

        /**
         * Gets the value of the transaccion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the transaccion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTransaccion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Polizas.Poliza.Transaccion }
         * 
         * 
         */
        public List<Polizas.Poliza.Transaccion> getTransaccion() {
            if (transaccion == null) {
                transaccion = new ArrayList<Polizas.Poliza.Transaccion>();
            }
            return this.transaccion;
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
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="CompNal" maxOccurs="unbounded" minOccurs="0"&gt;
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
         *                 &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
         *         &lt;element name="CompNalOtr" maxOccurs="unbounded" minOccurs="0"&gt;
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
         *                       &lt;minInclusive value="1"/&gt;
         *                       &lt;totalDigits value="20"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
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
         *                 &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
         *         &lt;element name="CompExt" maxOccurs="unbounded" minOccurs="0"&gt;
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
         *                 &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
         *         &lt;element name="Cheque" maxOccurs="unbounded" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;attribute name="Num" use="required"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;maxLength value="20"/&gt;
         *                       &lt;minLength value="1"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
         *                 &lt;attribute name="BanEmisNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
         *                 &lt;attribute name="BanEmisExt"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;maxLength value="150"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
         *                 &lt;attribute name="CtaOri" use="required"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;maxLength value="50"/&gt;
         *                       &lt;minLength value="1"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
         *                 &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
         *                 &lt;attribute name="Benef" use="required"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;minLength value="1"/&gt;
         *                       &lt;maxLength value="300"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
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
         *                 &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
         *         &lt;element name="Transferencia" maxOccurs="unbounded" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;attribute name="CtaOri"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;minLength value="1"/&gt;
         *                       &lt;maxLength value="50"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
         *                 &lt;attribute name="BancoOriNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
         *                 &lt;attribute name="BancoOriExt"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;maxLength value="150"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
         *                 &lt;attribute name="CtaDest" use="required"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;minLength value="1"/&gt;
         *                       &lt;maxLength value="50"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
         *                 &lt;attribute name="BancoDestNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
         *                 &lt;attribute name="BancoDestExt"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;maxLength value="150"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
         *                 &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
         *                 &lt;attribute name="Benef" use="required"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;minLength value="1"/&gt;
         *                       &lt;maxLength value="300"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
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
         *                 &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
         *         &lt;element name="OtrMetodoPago" maxOccurs="unbounded" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;attribute name="MetPagoPol" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
         *                 &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
         *                 &lt;attribute name="Benef" use="required"&gt;
         *                   &lt;simpleType&gt;
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *                       &lt;minLength value="1"/&gt;
         *                       &lt;maxLength value="300"/&gt;
         *                     &lt;/restriction&gt;
         *                   &lt;/simpleType&gt;
         *                 &lt;/attribute&gt;
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
         *                 &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
         *       &lt;attribute name="Concepto" use="required"&gt;
         *         &lt;simpleType&gt;
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;minLength value="1"/&gt;
         *             &lt;maxLength value="200"/&gt;
         *           &lt;/restriction&gt;
         *         &lt;/simpleType&gt;
         *       &lt;/attribute&gt;
         *       &lt;attribute name="Debe" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
         *       &lt;attribute name="Haber" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "compNal",
            "compNalOtr",
            "compExt",
            "cheque",
            "transferencia",
            "otrMetodoPago"
        })
        public static class Transaccion {

            @XmlElement(name = "CompNal")
            protected List<Polizas.Poliza.Transaccion.CompNal> compNal;
            @XmlElement(name = "CompNalOtr")
            protected List<Polizas.Poliza.Transaccion.CompNalOtr> compNalOtr;
            @XmlElement(name = "CompExt")
            protected List<Polizas.Poliza.Transaccion.CompExt> compExt;
            @XmlElement(name = "Cheque")
            protected List<Polizas.Poliza.Transaccion.Cheque> cheque;
            @XmlElement(name = "Transferencia")
            protected List<Polizas.Poliza.Transaccion.Transferencia> transferencia;
            @XmlElement(name = "OtrMetodoPago")
            protected List<Polizas.Poliza.Transaccion.OtrMetodoPago> otrMetodoPago;
            @XmlAttribute(name = "NumCta", required = true)
            protected String numCta;
            @XmlAttribute(name = "DesCta", required = true)
            protected String desCta;
            @XmlAttribute(name = "Concepto", required = true)
            protected String concepto;
            @XmlAttribute(name = "Debe", required = true)
            protected BigDecimal debe;
            @XmlAttribute(name = "Haber", required = true)
            protected BigDecimal haber;

            /**
             * Gets the value of the compNal property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the compNal property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCompNal().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Polizas.Poliza.Transaccion.CompNal }
             * 
             * 
             */
            public List<Polizas.Poliza.Transaccion.CompNal> getCompNal() {
                if (compNal == null) {
                    compNal = new ArrayList<Polizas.Poliza.Transaccion.CompNal>();
                }
                return this.compNal;
            }

            /**
             * Gets the value of the compNalOtr property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the compNalOtr property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCompNalOtr().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Polizas.Poliza.Transaccion.CompNalOtr }
             * 
             * 
             */
            public List<Polizas.Poliza.Transaccion.CompNalOtr> getCompNalOtr() {
                if (compNalOtr == null) {
                    compNalOtr = new ArrayList<Polizas.Poliza.Transaccion.CompNalOtr>();
                }
                return this.compNalOtr;
            }

            /**
             * Gets the value of the compExt property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the compExt property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCompExt().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Polizas.Poliza.Transaccion.CompExt }
             * 
             * 
             */
            public List<Polizas.Poliza.Transaccion.CompExt> getCompExt() {
                if (compExt == null) {
                    compExt = new ArrayList<Polizas.Poliza.Transaccion.CompExt>();
                }
                return this.compExt;
            }

            /**
             * Gets the value of the cheque property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the cheque property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getCheque().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Polizas.Poliza.Transaccion.Cheque }
             * 
             * 
             */
            public List<Polizas.Poliza.Transaccion.Cheque> getCheque() {
                if (cheque == null) {
                    cheque = new ArrayList<Polizas.Poliza.Transaccion.Cheque>();
                }
                return this.cheque;
            }

            /**
             * Gets the value of the transferencia property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the transferencia property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getTransferencia().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Polizas.Poliza.Transaccion.Transferencia }
             * 
             * 
             */
            public List<Polizas.Poliza.Transaccion.Transferencia> getTransferencia() {
                if (transferencia == null) {
                    transferencia = new ArrayList<Polizas.Poliza.Transaccion.Transferencia>();
                }
                return this.transferencia;
            }

            /**
             * Gets the value of the otrMetodoPago property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the otrMetodoPago property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getOtrMetodoPago().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Polizas.Poliza.Transaccion.OtrMetodoPago }
             * 
             * 
             */
            public List<Polizas.Poliza.Transaccion.OtrMetodoPago> getOtrMetodoPago() {
                if (otrMetodoPago == null) {
                    otrMetodoPago = new ArrayList<Polizas.Poliza.Transaccion.OtrMetodoPago>();
                }
                return this.otrMetodoPago;
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


            /**
             * <p>Clase Java para anonymous complex type.
             * 
             * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;attribute name="Num" use="required"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;maxLength value="20"/&gt;
             *             &lt;minLength value="1"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
             *       &lt;attribute name="BanEmisNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
             *       &lt;attribute name="BanEmisExt"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;maxLength value="150"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
             *       &lt;attribute name="CtaOri" use="required"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;maxLength value="50"/&gt;
             *             &lt;minLength value="1"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
             *       &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
             *       &lt;attribute name="Benef" use="required"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;minLength value="1"/&gt;
             *             &lt;maxLength value="300"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
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
             *       &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
            public static class Cheque {

                @XmlAttribute(name = "Num", required = true)
                protected String num;
                @XmlAttribute(name = "BanEmisNal", required = true)
                protected String banEmisNal;
                @XmlAttribute(name = "BanEmisExt")
                protected String banEmisExt;
                @XmlAttribute(name = "CtaOri", required = true)
                protected String ctaOri;
                @XmlAttribute(name = "Fecha", required = true)
                @XmlSchemaType(name = "date")
                protected XMLGregorianCalendar fecha;
                @XmlAttribute(name = "Benef", required = true)
                protected String benef;
                @XmlAttribute(name = "RFC", required = true)
                protected String rfc;
                @XmlAttribute(name = "Monto", required = true)
                protected BigDecimal monto;
                @XmlAttribute(name = "Moneda")
                protected CMoneda moneda;
                @XmlAttribute(name = "TipCamb")
                protected BigDecimal tipCamb;

                /**
                 * Obtiene el valor de la propiedad num.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getNum() {
                    return num;
                }

                /**
                 * Define el valor de la propiedad num.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setNum(String value) {
                    this.num = value;
                }

                /**
                 * Obtiene el valor de la propiedad banEmisNal.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBanEmisNal() {
                    return banEmisNal;
                }

                /**
                 * Define el valor de la propiedad banEmisNal.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBanEmisNal(String value) {
                    this.banEmisNal = value;
                }

                /**
                 * Obtiene el valor de la propiedad banEmisExt.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBanEmisExt() {
                    return banEmisExt;
                }

                /**
                 * Define el valor de la propiedad banEmisExt.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBanEmisExt(String value) {
                    this.banEmisExt = value;
                }

                /**
                 * Obtiene el valor de la propiedad ctaOri.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCtaOri() {
                    return ctaOri;
                }

                /**
                 * Define el valor de la propiedad ctaOri.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCtaOri(String value) {
                    this.ctaOri = value;
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
                 * Obtiene el valor de la propiedad benef.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBenef() {
                    return benef;
                }

                /**
                 * Define el valor de la propiedad benef.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBenef(String value) {
                    this.benef = value;
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
                 * Obtiene el valor de la propiedad monto.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getMonto() {
                    return monto;
                }

                /**
                 * Define el valor de la propiedad monto.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setMonto(BigDecimal value) {
                    this.monto = value;
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
             *       &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
            public static class CompExt {

                @XmlAttribute(name = "NumFactExt", required = true)
                protected String numFactExt;
                @XmlAttribute(name = "TaxID")
                protected String taxID;
                @XmlAttribute(name = "MontoTotal", required = true)
                protected BigDecimal montoTotal;
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
             *       &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
            public static class CompNal {

                @XmlAttribute(name = "UUID_CFDI", required = true)
                protected String uuidcfdi;
                @XmlAttribute(name = "RFC", required = true)
                protected String rfc;
                @XmlAttribute(name = "MontoTotal", required = true)
                protected BigDecimal montoTotal;
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
             *             &lt;minInclusive value="1"/&gt;
             *             &lt;totalDigits value="20"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
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
             *       &lt;attribute name="MontoTotal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
            public static class CompNalOtr {

                @XmlAttribute(name = "CFD_CBB_Serie")
                protected String cfdcbbSerie;
                @XmlAttribute(name = "CFD_CBB_NumFol", required = true)
                protected BigInteger cfdcbbNumFol;
                @XmlAttribute(name = "RFC", required = true)
                protected String rfc;
                @XmlAttribute(name = "MontoTotal", required = true)
                protected BigDecimal montoTotal;
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
             *       &lt;attribute name="MetPagoPol" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_MetPagos" /&gt;
             *       &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
             *       &lt;attribute name="Benef" use="required"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;minLength value="1"/&gt;
             *             &lt;maxLength value="300"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
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
             *       &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
            public static class OtrMetodoPago {

                @XmlAttribute(name = "MetPagoPol", required = true)
                protected String metPagoPol;
                @XmlAttribute(name = "Fecha", required = true)
                @XmlSchemaType(name = "date")
                protected XMLGregorianCalendar fecha;
                @XmlAttribute(name = "Benef", required = true)
                protected String benef;
                @XmlAttribute(name = "RFC", required = true)
                protected String rfc;
                @XmlAttribute(name = "Monto", required = true)
                protected BigDecimal monto;
                @XmlAttribute(name = "Moneda")
                protected CMoneda moneda;
                @XmlAttribute(name = "TipCamb")
                protected BigDecimal tipCamb;

                /**
                 * Obtiene el valor de la propiedad metPagoPol.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getMetPagoPol() {
                    return metPagoPol;
                }

                /**
                 * Define el valor de la propiedad metPagoPol.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setMetPagoPol(String value) {
                    this.metPagoPol = value;
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
                 * Obtiene el valor de la propiedad benef.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBenef() {
                    return benef;
                }

                /**
                 * Define el valor de la propiedad benef.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBenef(String value) {
                    this.benef = value;
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
                 * Obtiene el valor de la propiedad monto.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getMonto() {
                    return monto;
                }

                /**
                 * Define el valor de la propiedad monto.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setMonto(BigDecimal value) {
                    this.monto = value;
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
             *       &lt;attribute name="CtaOri"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;minLength value="1"/&gt;
             *             &lt;maxLength value="50"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
             *       &lt;attribute name="BancoOriNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
             *       &lt;attribute name="BancoOriExt"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;maxLength value="150"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
             *       &lt;attribute name="CtaDest" use="required"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;minLength value="1"/&gt;
             *             &lt;maxLength value="50"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
             *       &lt;attribute name="BancoDestNal" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/CatalogosParaEsqContE}c_Banco" /&gt;
             *       &lt;attribute name="BancoDestExt"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;maxLength value="150"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
             *       &lt;attribute name="Fecha" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
             *       &lt;attribute name="Benef" use="required"&gt;
             *         &lt;simpleType&gt;
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
             *             &lt;minLength value="1"/&gt;
             *             &lt;maxLength value="300"/&gt;
             *           &lt;/restriction&gt;
             *         &lt;/simpleType&gt;
             *       &lt;/attribute&gt;
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
             *       &lt;attribute name="Monto" use="required" type="{www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo}t_Importe" /&gt;
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
            public static class Transferencia {

                @XmlAttribute(name = "CtaOri")
                protected String ctaOri;
                @XmlAttribute(name = "BancoOriNal", required = true)
                protected String bancoOriNal;
                @XmlAttribute(name = "BancoOriExt")
                protected String bancoOriExt;
                @XmlAttribute(name = "CtaDest", required = true)
                protected String ctaDest;
                @XmlAttribute(name = "BancoDestNal", required = true)
                protected String bancoDestNal;
                @XmlAttribute(name = "BancoDestExt")
                protected String bancoDestExt;
                @XmlAttribute(name = "Fecha", required = true)
                @XmlSchemaType(name = "date")
                protected XMLGregorianCalendar fecha;
                @XmlAttribute(name = "Benef", required = true)
                protected String benef;
                @XmlAttribute(name = "RFC", required = true)
                protected String rfc;
                @XmlAttribute(name = "Monto", required = true)
                protected BigDecimal monto;
                @XmlAttribute(name = "Moneda")
                protected CMoneda moneda;
                @XmlAttribute(name = "TipCamb")
                protected BigDecimal tipCamb;

                /**
                 * Obtiene el valor de la propiedad ctaOri.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCtaOri() {
                    return ctaOri;
                }

                /**
                 * Define el valor de la propiedad ctaOri.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCtaOri(String value) {
                    this.ctaOri = value;
                }

                /**
                 * Obtiene el valor de la propiedad bancoOriNal.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBancoOriNal() {
                    return bancoOriNal;
                }

                /**
                 * Define el valor de la propiedad bancoOriNal.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBancoOriNal(String value) {
                    this.bancoOriNal = value;
                }

                /**
                 * Obtiene el valor de la propiedad bancoOriExt.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBancoOriExt() {
                    return bancoOriExt;
                }

                /**
                 * Define el valor de la propiedad bancoOriExt.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBancoOriExt(String value) {
                    this.bancoOriExt = value;
                }

                /**
                 * Obtiene el valor de la propiedad ctaDest.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCtaDest() {
                    return ctaDest;
                }

                /**
                 * Define el valor de la propiedad ctaDest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCtaDest(String value) {
                    this.ctaDest = value;
                }

                /**
                 * Obtiene el valor de la propiedad bancoDestNal.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBancoDestNal() {
                    return bancoDestNal;
                }

                /**
                 * Define el valor de la propiedad bancoDestNal.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBancoDestNal(String value) {
                    this.bancoDestNal = value;
                }

                /**
                 * Obtiene el valor de la propiedad bancoDestExt.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBancoDestExt() {
                    return bancoDestExt;
                }

                /**
                 * Define el valor de la propiedad bancoDestExt.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBancoDestExt(String value) {
                    this.bancoDestExt = value;
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
                 * Obtiene el valor de la propiedad benef.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBenef() {
                    return benef;
                }

                /**
                 * Define el valor de la propiedad benef.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBenef(String value) {
                    this.benef = value;
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
                 * Obtiene el valor de la propiedad monto.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getMonto() {
                    return monto;
                }

                /**
                 * Define el valor de la propiedad monto.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setMonto(BigDecimal value) {
                    this.monto = value;
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

}
