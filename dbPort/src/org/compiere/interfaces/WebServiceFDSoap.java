/**
 * WS_FD_TFDSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.compiere.interfaces;

public interface WebServiceFDSoap extends java.rmi.Remote {

	/**
	 * Folios Digitales - Timbrado de Prueba
	 */
	public java.lang.String[] timbrarPruebaCFDI(java.lang.String usuario, java.lang.String password, java.lang.String cadenaXML)
			throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Timbrar un CFD
	 */
	public java.lang.String[] timbrarCFD(java.lang.String usuario, java.lang.String password, java.lang.String cadenaXML, java.lang.String referencia)
			throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Servicio para Cancelar CFDIs
	 */
	public java.lang.String[] cancelarCFDI(java.lang.String usuario, java.lang.String password, java.lang.String RFCEmisor,
			java.lang.String[] listaCFDI, java.lang.String certificadoPKCS12_Base64, java.lang.String passwordPKCS12) throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Obtener XML del CFDI
	 */
	public java.lang.String[] obtenerXML(java.lang.String usuario, java.lang.String password, java.lang.String UUID, java.lang.String RFCEmisor)
			throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Obtener acuse de envío al SAT
	 */
	public java.lang.String[] obtenerAcuseEnvio(java.lang.String usuario, java.lang.String password, java.lang.String UUID, java.lang.String RFCEmisor)
			throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Obtener acuse de cancelación ante el SAT
	 */
	public java.lang.String[] obtenerAcuseCancelacion(java.lang.String usuario, java.lang.String password, java.lang.String UUID,
			java.lang.String RFCEmisor) throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Cambio de Password
	 */
	public java.lang.String[] cambiarPassword(java.lang.String usuario, java.lang.String passwordActual, java.lang.String passwordNuevo)
			throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Consultar datos de timbrado
	 */
	public java.lang.String[] consultarComplementoTimbre(java.lang.String usuario, java.lang.String password, java.lang.String UUID)
			throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Consultar datos de complemento timbre
	 */
	public java.lang.String[] consultarTimbrePorReferencia(java.lang.String usuario, java.lang.String password, java.lang.String RFCEmisor,
			java.lang.String referencia) throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Consultar el estado de un comprobante
	 */
	public java.lang.String[] consultarEstadoComprobante(java.lang.String usuario, java.lang.String password, java.lang.String UUID)
			throws java.rmi.RemoteException;

	/**
	 * Folios Digitales - Consulta de creditos restantes
	 */
	public java.lang.String[] consultarCreditos(java.lang.String usuario, java.lang.String password) throws java.rmi.RemoteException;
}
