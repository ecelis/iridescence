/**
 * WS_FD_TFDSoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.compiere.interfaces;

import javax.xml.namespace.QName;

public class WebServiceFDSoapStub /*extends org.apache.axis.client.Stub implements org.compiere.interfaces.WebServiceFDSoap*/ {
	/*@SuppressWarnings("rawtypes")
	private java.util.Vector<Class> cachedSerClasses = new java.util.Vector<Class>();
	private java.util.Vector<QName> cachedSerQNames = new java.util.Vector<QName>();
	@SuppressWarnings("rawtypes")
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	@SuppressWarnings("rawtypes")
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	/*static org.apache.axis.description.OperationDesc[] operations;

	static {
		 operations = new org.apache.axis.description.OperationDesc[11];
		 initOperationDesc1();
		 initOperationDesc2();
	}

	private static void initOperationDesc1() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("TimbrarPruebaCFDI");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"cadenaXML"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "TimbrarPruebaCFDIResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("TimbrarCFD");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"cadenaXML"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"referencia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "TimbrarCFDResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("CancelarCFDI");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"RFCEmisor"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"listaCFDI"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"), java.lang.String[].class, false, false);
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"certificadoPKCS12_Base64"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"passwordPKCS12"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "CancelarCFDIResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ObtenerXML");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "Usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"Password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "UUID"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"RFCEmisor"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ObtenerXMLResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[3] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ObtenerAcuseEnvio");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "Usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"Password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "UUID"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"RFCEmisor"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ObtenerAcuseEnvioResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[4] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ObtenerAcuseCancelacion");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "Usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"Password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "UUID"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"RFCEmisor"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ObtenerAcuseCancelacionResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[5] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("CambiarPassword");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "Usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"passwordActual"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"passwordNuevo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "CambiarPasswordResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[6] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ConsultarComplementoTimbre");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "Usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"Password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "UUID"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ConsultarComplementoTimbreResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[7] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ConsultarTimbrePorReferencia");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "Usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"Password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"RFCEmisor"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"Referencia"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema",
				"string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ConsultarTimbrePorReferenciaResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[8] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ConsultarEstadoComprobante");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "Usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"Password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "UUID"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ConsultarEstadoComprobanteResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[9] = oper;

	}

	private static void initOperationDesc2() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("ConsultarCreditos");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "Usuario"),
				org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"),
				java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios",
				"Password"), org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString"));
		oper.setReturnClass(java.lang.String[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ConsultarCreditosResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		operations[10] = oper;

	}

	public WebServiceFDSoapStub() throws org.apache.axis.AxisFault {
		this(null);
	}

	public WebServiceFDSoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public WebServiceFDSoapStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service).setTypeMappingVersion("1.2");
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		qName = new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ArrayOfString");
		cachedSerQNames.add(qName);
		cls = java.lang.String[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
		qName2 = new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "string");
		cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
		cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

	}

	@SuppressWarnings("rawtypes")
	protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this) {
				if (firstCall()) {
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);
					for (int i = 0; i < cachedSerFactories.size(); ++i) {
						java.lang.Class<?> cls = (java.lang.Class<?>) cachedSerClasses.get(i);
						javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames.get(i);
						java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							java.lang.Class<?> sf = (java.lang.Class<?>) cachedSerFactories.get(i);
							java.lang.Class<?> df = (java.lang.Class<?>) cachedDeserFactories.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						} else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
									.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		} catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
		}
	}

	public java.lang.String[] timbrarPruebaCFDI(java.lang.String usuario, java.lang.String password, java.lang.String cadenaXML)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/TimbrarPruebaCFDI");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "TimbrarPruebaCFDI"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password, cadenaXML });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] timbrarCFD(java.lang.String usuario, java.lang.String password, java.lang.String cadenaXML, java.lang.String referencia)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/TimbrarCFD");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "TimbrarCFD"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password, cadenaXML, referencia });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] cancelarCFDI(java.lang.String usuario, java.lang.String password, java.lang.String RFCEmisor,
			java.lang.String[] listaCFDI, java.lang.String certificadoPKCS12_Base64, java.lang.String passwordPKCS12) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/CancelarCFDI");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "CancelarCFDI"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password, RFCEmisor, listaCFDI, certificadoPKCS12_Base64,
					passwordPKCS12 });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] obtenerXML(java.lang.String usuario, java.lang.String password, java.lang.String UUID, java.lang.String RFCEmisor)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/ObtenerXML");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ObtenerXML"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password, UUID, RFCEmisor });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] obtenerAcuseEnvio(java.lang.String usuario, java.lang.String password, java.lang.String UUID, java.lang.String RFCEmisor)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/ObtenerAcuseEnvio");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ObtenerAcuseEnvio"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password, UUID, RFCEmisor });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] obtenerAcuseCancelacion(java.lang.String usuario, java.lang.String password, java.lang.String UUID,
			java.lang.String RFCEmisor) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/ObtenerAcuseCancelacion");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ObtenerAcuseCancelacion"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password, UUID, RFCEmisor });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] cambiarPassword(java.lang.String usuario, java.lang.String passwordActual, java.lang.String passwordNuevo)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[6]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/CambiarPassword");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "CambiarPassword"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, passwordActual, passwordNuevo });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] consultarComplementoTimbre(java.lang.String usuario, java.lang.String password, java.lang.String UUID)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[7]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/ConsultarComplementoTimbre");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ConsultarComplementoTimbre"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password, UUID });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] consultarTimbrePorReferencia(java.lang.String usuario, java.lang.String password, java.lang.String RFCEmisor,
			java.lang.String referencia) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[8]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/ConsultarTimbrePorReferencia");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ConsultarTimbrePorReferencia"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password, RFCEmisor, referencia });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] consultarEstadoComprobante(java.lang.String usuario, java.lang.String password, java.lang.String UUID)
			throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[9]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/ConsultarEstadoComprobante");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ConsultarEstadoComprobante"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password, UUID });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}

	public java.lang.String[] consultarCreditos(java.lang.String usuario, java.lang.String password) throws java.rmi.RemoteException {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(operations[10]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("https://www.foliosdigitalespac.com/WS-Folios/ConsultarCreditos");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "ConsultarCreditos"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call.invoke(new java.lang.Object[] { usuario, password });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (java.lang.String[]) _resp;
				} catch (java.lang.Exception _exception) {
					return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			throw axisFaultException;
		}
	}
*/
}
