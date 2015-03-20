/**
 * WS_FD_TFDLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.compiere.interfaces;

import javax.xml.namespace.QName;

public class WebServiceFDLocator /*extends org.apache.axis.client.Service implements org.compiere.interfaces.WebServiceFD*/ {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 2660523865485675007L;

	/*public WebServiceFDLocator() {
	}

	public WebServiceFDLocator(org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public WebServiceFDLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for WS_FD_TFDSoap
	private java.lang.String WS_FD_TFDSoap_address = "https://www.foliosdigitalespac.com/ws-folios/WS-TFD.asmx";

	public java.lang.String getWS_FD_TFDSoapAddress() {
		return WS_FD_TFDSoap_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String WS_FD_TFDSoapWSDDServiceName = "WS_FD_TFDSoap";

	public java.lang.String getWS_FD_TFDSoapWSDDServiceName() {
		return WS_FD_TFDSoapWSDDServiceName;
	}

	public void setWS_FD_TFDSoapWSDDServiceName(java.lang.String name) {
		WS_FD_TFDSoapWSDDServiceName = name;
	}

	public org.compiere.interfaces.WebServiceFDSoap getWS_FD_TFDSoap() throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(WS_FD_TFDSoap_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getWS_FD_TFDSoap(endpoint);
	}

	public org.compiere.interfaces.WebServiceFDSoap getWS_FD_TFDSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			org.compiere.interfaces.WebServiceFDSoapStub _stub = new org.compiere.interfaces.WebServiceFDSoapStub(portAddress, this);
			_stub.setPortName(getWS_FD_TFDSoapWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setWS_FD_TFDSoapEndpointAddress(java.lang.String address) {
		WS_FD_TFDSoap_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	/*@SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		try {
			if (org.compiere.interfaces.WebServiceFDSoap.class.isAssignableFrom(serviceEndpointInterface)) {
				org.compiere.interfaces.WebServiceFDSoapStub _stub = new org.compiere.interfaces.WebServiceFDSoapStub(new java.net.URL(WS_FD_TFDSoap_address),
						this);
				_stub.setPortName(getWS_FD_TFDSoapWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
				+ (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	/*@SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("WS_FD_TFDSoap".equals(inputPortName)) {
			return getWS_FD_TFDSoap();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "WS_FD_TFD");
	}

	private java.util.HashSet<QName> ports = null;

	public java.util.Iterator<QName> getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet<QName>();
			ports.add(new javax.xml.namespace.QName("https://www.foliosdigitalespac.com/WS-Folios", "WS_FD_TFDSoap"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	/*public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("WS_FD_TFDSoap".equals(portName)) {
			setWS_FD_TFDSoapEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	/*public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}*/

}
