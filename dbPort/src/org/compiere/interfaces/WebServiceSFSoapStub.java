package org.compiere.interfaces;


import UtileriasSFProd.ParametrosBuscar;
import UtileriasSFProd.ResponseTimbres;



public class WebServiceSFSoapStub {



	public WebServiceSFSoapStub(){
		super();
	}
	
	
	public UtileriasSFProd.ResponseBuscar buscar(String usuario, String password, ParametrosBuscar parametrosBusqueda) {

		UtileriasSFProd.Utilerias service = new UtileriasSFProd.Utilerias();
		UtileriasSFProd.UtileriasPortType port = service.getUtileriasHttpsSoap11Endpoint();
		return port.buscar(usuario, password, parametrosBusqueda);
	}

	public UtileriasSFProd.AcuseRecepcion consultaAcuseRecepcion(String usuario, String password,String uuid) {

		UtileriasSFProd.Utilerias service = new UtileriasSFProd.Utilerias();
		UtileriasSFProd.UtileriasPortType port = service.getUtileriasHttpsSoap11Endpoint();
		return port.consultaAcuseRecepcion(usuario, password, uuid);
	}


	public ResponseTimbres getTimbres(String usuario, String password, String RFCEmisor) {

		UtileriasSFProd.Utilerias service = new UtileriasSFProd.Utilerias();
		UtileriasSFProd.UtileriasPortType port = service.getUtileriasHttpsSoap11Endpoint();
		return port.getTimbres(usuario, password, RFCEmisor);
	}


	public ResponseTimbres getTimbresImplementacion(String usuario, String password) {

		UtileriasSFProd.Utilerias service = new UtileriasSFProd.Utilerias();
		UtileriasSFProd.UtileriasPortType port = service.getUtileriasHttpsSoap11Endpoint();		
		return port.getTimbresImplementacion(usuario, password);
	}

}
