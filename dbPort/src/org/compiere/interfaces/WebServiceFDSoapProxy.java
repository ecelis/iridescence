package org.compiere.interfaces;

public class WebServiceFDSoapProxy /*implements org.compiere.interfaces.WebServiceFDSoap*/ {
	private String _endpoint = null;
/*	private org.compiere.interfaces.WebServiceFDSoap wS_FD_TFDSoap = null;

	public WebServiceFDSoapProxy() {
		_initWS_FD_TFDSoapProxy();
	}

	public WebServiceFDSoapProxy(String endpoint) {
		_endpoint = endpoint;
		_initWS_FD_TFDSoapProxy();
	}

	private void _initWS_FD_TFDSoapProxy() {
		try {
			wS_FD_TFDSoap = (new org.compiere.interfaces.WebServiceFDLocator()).getWS_FD_TFDSoap();
			if (wS_FD_TFDSoap != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) wS_FD_TFDSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) wS_FD_TFDSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (wS_FD_TFDSoap != null)
			((javax.xml.rpc.Stub) wS_FD_TFDSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public org.compiere.interfaces.WebServiceFDSoap getWS_FD_TFDSoap() {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap;
	}

	public java.lang.String[] timbrarPruebaCFDI(java.lang.String usuario, java.lang.String password, java.lang.String cadenaXML)
			throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.timbrarPruebaCFDI(usuario, password, cadenaXML);
	}

	public java.lang.String[] timbrarCFD(java.lang.String usuario, java.lang.String password, java.lang.String cadenaXML, java.lang.String referencia)
			throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.timbrarCFD(usuario, password, cadenaXML, referencia);
	}

	public java.lang.String[] cancelarCFDI(java.lang.String usuario, java.lang.String password, java.lang.String RFCEmisor,
			java.lang.String[] listaCFDI, java.lang.String certificadoPKCS12_Base64, java.lang.String passwordPKCS12) throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.cancelarCFDI(usuario, password, RFCEmisor, listaCFDI, certificadoPKCS12_Base64, passwordPKCS12);
	}

	public java.lang.String[] obtenerXML(java.lang.String usuario, java.lang.String password, java.lang.String UUID, java.lang.String RFCEmisor)
			throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.obtenerXML(usuario, password, UUID, RFCEmisor);
	}

	public java.lang.String[] obtenerAcuseEnvio(java.lang.String usuario, java.lang.String password, java.lang.String UUID, java.lang.String RFCEmisor)
			throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.obtenerAcuseEnvio(usuario, password, UUID, RFCEmisor);
	}

	public java.lang.String[] obtenerAcuseCancelacion(java.lang.String usuario, java.lang.String password, java.lang.String UUID,
			java.lang.String RFCEmisor) throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.obtenerAcuseCancelacion(usuario, password, UUID, RFCEmisor);
	}

	public java.lang.String[] cambiarPassword(java.lang.String usuario, java.lang.String passwordActual, java.lang.String passwordNuevo)
			throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.cambiarPassword(usuario, passwordActual, passwordNuevo);
	}

	public java.lang.String[] consultarComplementoTimbre(java.lang.String usuario, java.lang.String password, java.lang.String UUID)
			throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.consultarComplementoTimbre(usuario, password, UUID);
	}

	public java.lang.String[] consultarTimbrePorReferencia(java.lang.String usuario, java.lang.String password, java.lang.String RFCEmisor,
			java.lang.String referencia) throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.consultarTimbrePorReferencia(usuario, password, RFCEmisor, referencia);
	}

	public java.lang.String[] consultarEstadoComprobante(java.lang.String usuario, java.lang.String password, java.lang.String UUID)
			throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.consultarEstadoComprobante(usuario, password, UUID);
	}

	public java.lang.String[] consultarCreditos(java.lang.String usuario, java.lang.String password) throws java.rmi.RemoteException {
		if (wS_FD_TFDSoap == null)
			_initWS_FD_TFDSoapProxy();
		return wS_FD_TFDSoap.consultarCreditos(usuario, password);
	}*/

}