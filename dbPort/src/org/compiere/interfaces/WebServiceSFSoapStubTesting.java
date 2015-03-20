package org.compiere.interfaces;



public class WebServiceSFSoapStubTesting {	
	

	public WebServiceSFSoapStubTesting() {
		super();
	}
	
	
	public UtileriasSFTesting.ResponseBuscar buscarTesting(
			String userName,
			String password,
			UtileriasSFTesting.ParametrosBuscar parametros) {
		UtileriasSFTesting.Utilerias service = new UtileriasSFTesting.Utilerias();
		UtileriasSFTesting.UtileriasPortType port = service.getUtileriasHttpsSoap11Endpoint();
		
		return port.buscar(userName, password, parametros);
	}
	

	public UtileriasSFTesting.AcuseRecepcion consultaAcuseRecepcionTesting(String usuario, String password,String uuid) {

		UtileriasSFTesting.Utilerias service = new UtileriasSFTesting.Utilerias();
		UtileriasSFTesting.UtileriasPortType port = service.getUtileriasHttpSoap11Endpoint();
		return port.consultaAcuseRecepcion(usuario, password, uuid);
	}


	public UtileriasSFTesting.ResponseTimbres getTimbresTesting(String usuario, String password, String RFCEmisor) {

		UtileriasSFTesting.Utilerias service = new UtileriasSFTesting.Utilerias();
		UtileriasSFTesting.UtileriasPortType port = service.getUtileriasHttpSoap11Endpoint();
		return port.getTimbres(usuario, password, RFCEmisor);
	}


	public UtileriasSFTesting.ResponseTimbres getTimbresImplementacionTesting(String usuario, String password) {

		UtileriasSFTesting.Utilerias service = new UtileriasSFTesting.Utilerias();
		UtileriasSFTesting.UtileriasPortType port = service.getUtileriasHttpsSoap11Endpoint();
		return port.getTimbresImplementacion(usuario, password);
	}
	
}
