/**
 * Clase para timbrar el xml
 */
package org.compiere.interfaces;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MEXMEConfigFE;
import org.compiere.util.CLogger;


import UtileriasSFProd.ParametrosBuscar;
import UtileriasSFProd.ResponseBuscar;
import UtileriasSFProd.ResponseTimbres;
import com.solucionfactible.cfdi.ws.timbrado.xsd.CFDICancelacion;
import com.solucionfactible.cfdi.ws.timbrado.xsd.CFDICertificacion;
import com.solucionfactible.cfdi.ws.timbrado.xsd.CFDIResultadoCancelacion;
import com.solucionfactible.cfdi.ws.timbrado.xsd.CFDIResultadoCertificacion;
import com.solucionfactible.cfdi.ws.timbrado.client.Timbrado;


/**
 * @author jgarza
 * 
 */
public class WebServiceTimbrarXML {
	
	private static CLogger log = CLogger.getCLogger (WebServiceTimbrarXML.class);
	
	/**
	 * Realiza la conexion con el Web Service (WS) y recibe la respuesta (Metodo de prueba)
	 * @param usuario
	 * @param password
	 * @param cadenaXML
	 */
	public static CFDIResultadoCertificacion[] timbrarPruebaXML(String rutaXML, MEXMEConfigFE fe/*String usuario, String password, String cadenaXML*/) {
		CFDIResultadoCertificacion[] respuesta = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		//String[] respuesta = null;
		try {
			//URL url = new URL("https://www.foliosdigitalespac.com/ws-folios/WS-TFD.asmx");
			// Crear instancia al WS para timbrar
			//WebServiceFDSoapStub servicioFD = new WebServiceFDSoapStub(url, new org.apache.axis.client.Service());
			// Se recibe la respuesta
			//respuesta = servicioFD.timbrarPruebaCFDI(usuario, password, cadenaXML);
			Timbrado timbrado = new Timbrado();
			CFDICertificacion certifica = timbrado.timbrar(fe.getUserName(), fe.getPassword(), rutaXML, fe.isInProduction());
			// Se recibe la respuesta
			respuesta = certifica.getResultados();
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "timbrarPruebaXML", e);
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, "timbrarPruebaXML", e);
		} catch (Exception e) {
			log.log(Level.SEVERE, "timbrarPruebaXML", e);
		} finally {
			if (respuesta != null) {
				for (int i = 0; i < respuesta.length; i++) {
					log.log(Level.INFO, "Respuesta WSFE:[ " + i + " ]" + respuesta[i]);
				}
			}
		}
		return respuesta;
	}
	
	/**
	 * Realiza la conexion con el Web Service (WS) y recibe la respuesta
	 * @param usuario
	 * @param password
	 * @param cadenaXML
	 */
	public static CFDIResultadoCertificacion[] timbrarXML(String rutaXML, MEXMEConfigFE fe/*String usuario, String password, String cadenaXML, String referencia*/) {
		//String[] respuesta = null;
		CFDIResultadoCertificacion[] respuesta = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		try {
			//URL url = new URL("https://www.foliosdigitalespac.com/ws-folios/WS-TFD.asmx");
			// Crear instancia al WS para timbrar
			//WebServiceFDSoapStub servicioFD = new WebServiceFDSoapStub(url, new org.apache.axis.client.Service());
			// Se recibe la respuesta
			//respuesta = servicioFD.timbrarCFD(usuario, password, cadenaXML, referencia);
			
			Timbrado timbrado = new Timbrado();
			CFDICertificacion certifica = timbrado.timbrar(fe.getUserName(), fe.getPassword(), rutaXML, fe.isInProduction());
			// Se recibe la respuesta
			respuesta = certifica.getResultados();
			
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "timbrarXML", e);
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, "timbrarXML", e);
		} catch (Exception e) {
			log.log(Level.SEVERE, "timbrarXML", e);
		} finally {
			if (respuesta != null) {
				for (int i = 0; i < respuesta.length; i++) {
					log.log(Level.WARNING, "Respuesta WSFE:[ " + i + " ]" + respuesta[i]);
				}
			}else {
				log.log(Level.WARNING, "@timbrarXML.WebServiceTimbrarXML Sin respuesta de servicioFD.timbrarCFD @");
			}			
		}
		return respuesta;
	}
	/**
	 * Cancelacion de un CFDI
	 * @return
	 */
	public static CFDIResultadoCancelacion[] cancelarCFDI (String rfc, String[] cfdi, String cadena, MEXMEConfigFE fe/*String usuario, String password, String rfc, String[] cfdi, String cadena, String passwordPfx*/){
		//String[] respuesta = null;
		CFDIResultadoCancelacion[] respuesta = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		try {
			//URL url = new URL("https://www.foliosdigitalespac.com/ws-folios/WS-TFD.asmx");
			//Crear instancia al WS para cancelar
			//WebServiceFDSoapStub servicioFD = new WebServiceFDSoapStub(url, new org.apache.axis.client.Service());
			//Se recibe la respuesta
			//respuesta = servicioFD.cancelarCFDI(usuario, password, rfc, cfdi, cadena, passwordPfx);
			File certificado = obtenerArchivo(fe, "cer");
			File llave = obtenerArchivo(fe, "key");			
			
			Timbrado timbrado = new Timbrado();
			CFDICancelacion cancelacion = timbrado.cancelar(fe.getUserName(), fe.getPassword(), cfdi, certificado.toString(), llave.toString(), fe.getPasswordPK(), fe.isInProduction());
			respuesta = cancelacion.getResultados();
		} catch (Exception e){
			log.log(Level.SEVERE, "cancelarCFDI", e);
		}finally {
			if (respuesta != null) {
				for (int i = 0; i < respuesta.length; i++) {
					log.log(Level.INFO, "Respuesta WSFE:[ " + i + " ]" + respuesta[i]);
				}
			}else {
				log.log(Level.WARNING, "@timbrarXML.cancelarCFDI Sin respuesta de servicioSF.timbrarCFD @");
			}			
		}
		return respuesta;
	}
	
	/**
	 * Metodo para la consulta de los timbres disponibles
	 * @param usuario
	 * @param password
	 * @param isProduccion
	 **/
	public static ResponseTimbres consultarCreditos(String usuario, String password){
		ResponseTimbres respuesta = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		try {
												
		// Crear instancia al WS para timbrar
		//WebServiceFDSoapStub servicioFD = new WebServiceFDSoapStub(url, new org.apache.axis.client.Service());
		// Se recibe la respuesta
		//respuesta = servicioFD.consultarCreditos(usuario, password);
			//WS para las utilerias de Solucion Factible
		WebServiceSFSoapStub servicioSF = new WebServiceSFSoapStub();	
		
		respuesta = servicioSF.getTimbresImplementacion(usuario, password);
		
		}/* catch (RemoteException e) {
			log.log(Level.SEVERE, "obtenerXML", e);
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, "obtenerXML", e);
		} */catch (Exception e) {
			log.log(Level.SEVERE, "obtenerXML", e);
		}finally {
			if (respuesta != null) {
				/*for (int i = 0; i < respuesta.length; i++) {
					log.log(Level.INFO, "Respuesta WSFE:[ " + i + " ]" + respuesta[i]);
				}*/
				log.log(Level.INFO, "Respuesta WSFE: " + respuesta.getStatus() + ", mensaje: " + respuesta.getMensaje());
			}			
		}
		return respuesta;
	}
	
	
	
	/**
	 * Metodo para la consulta de los timbres disponibles (Prueba)
	 * @param usuario
	 * @param password
	 **/
	public static UtileriasSFTesting.ResponseTimbres consultarCreditosPrueba(String usuario, String password){
		UtileriasSFTesting.ResponseTimbres respuesta = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		try {
			//WS para las utilerias de Solucion Factible
			WebServiceSFSoapStubTesting servicioSF = new WebServiceSFSoapStubTesting();	

			respuesta = servicioSF.getTimbresImplementacionTesting(usuario, password);

		}catch (Exception e) {
			log.log(Level.SEVERE, "obtenerXML", e);
		}finally {
			if (respuesta != null) {				
				log.log(Level.INFO, "Respuesta WSFE: " + respuesta.getStatus() + ", mensaje: " + respuesta.getMensaje());
			}			
		}
		return respuesta;
	}
	
	/**Metodo para solicitar un XML timbrado
	 * @param usuario
	 * @param password
	 * @param UUID
	 * @param RFCEmisor
	 **/
	public static ResponseBuscar obtenerXML(MEXMEConfigFE fe /*String usuario, String password*/, String UUID, String RFCCliente) {
		ResponseBuscar respuesta = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		try {
			//URL url = new URL("https://www.foliosdigitalespac.com/ws-folios/WS-TFD.asmx");
			
			// Crear instancia al WS para timbrar
			//WebServiceFDSoapStub servicioFD = new WebServiceFDSoapStub(url, new org.apache.axis.client.Service());
			// Se recibe la respuesta
			//respuesta = servicioFD.obtenerXML(usuario, password, UUID, RFCEmisor);
			//WS para las utilerias de Solucion Factible			
			
			//Se recibe la respuesta
			if(fe.isInProduction()){
				WebServiceSFSoapStub servicioSF = new WebServiceSFSoapStub();
				UtileriasSFProd.ObjectFactory ob = new UtileriasSFProd.ObjectFactory();
				UtileriasSFProd.ParametrosBuscar parametros = new UtileriasSFProd.ParametrosBuscar();
				parametros.setOffset(0);
				parametros.setUuid(ob.createParametrosBuscarUuid(UUID.trim()));
				parametros.setLimit(10);
				respuesta = servicioSF.buscar(fe.getUserName(), fe.getPassword(), parametros);	
			}
			
		}/* catch (RemoteException e) {
			log.log(Level.SEVERE, "obtenerXML", e);
		} /*catch (MalformedURLException e) {
			log.log(Level.SEVERE, "obtenerXML", e);
		}*/ catch (Exception e) {
			log.log(Level.SEVERE, "obtenerXML", e);
		} finally {
			if (respuesta != null) {
				/*for (int i = 0; i < respuesta.length; i++) {
					log.log(Level.INFO, "Respuesta WSFE:[ " + i + " ]" + respuesta[i]);
				}*/
				log.log(Level.INFO, "Respuesta WSFE: " + respuesta.getStatus() + ", mensaje: " + respuesta.getMensaje());
			}			
		}
		return respuesta;
	}	
	
	
	/**Metodo para solicitar un XML timbrado (prueba)	
	 * @param MEXMEConfigFE
	 * @param UUID
	 **/
	public static UtileriasSFTesting.ResponseBuscar obtenerXMLPrueba(MEXMEConfigFE fe, String UUID) {
		UtileriasSFTesting.ResponseBuscar respuesta = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		try {
			//WS para las utilerias de Solucion Factible			
			if(!fe.isInProduction()){				
				WebServiceSFSoapStubTesting servicioSFTesting = new WebServiceSFSoapStubTesting();
				UtileriasSFTesting.ObjectFactory ob = new UtileriasSFTesting.ObjectFactory();
				UtileriasSFTesting.ParametrosBuscar parametros = new UtileriasSFTesting.ParametrosBuscar();
				parametros.setOffset(0);				
				parametros.setUuid(ob.createParametrosBuscarUuid(UUID.trim()));
				parametros.setLimit(10);
				respuesta = servicioSFTesting.buscarTesting(fe.getUserName(), fe.getPassword(), parametros);	
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, "obtenerXMLPrueba", e);
		} finally {
			if (respuesta != null) {				
				log.log(Level.INFO, "Respuesta WSFE: " + respuesta.getStatus() + ", mensaje: " + respuesta.getMensaje());
			}			
		}
		return respuesta;
	}	
	
	
	/**
	 * Otener el Acuse digital de Cancelacion de un CFDI
	 * @param usuario
	 * @param password
	 * @param UUID
	 * @param RFCEmisor
	 */
	/*public static String[] acuseCancelacion(String usuario, String password,  String UUID, String RFCEmisor){
		String[] respuesta = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		try {
			URL url = new URL("https://www.foliosdigitalespac.com/ws-folios/WS-TFD.asmx");
			//Crear instancia al WS para obtener acuse
			WebServiceFDSoapStub servicioFD = new WebServiceFDSoapStub(url, new org.apache.axis.client.Service());
			//Se recibe la respuesta
			respuesta = servicioFD.obtenerAcuseCancelacion(usuario, password, UUID, RFCEmisor);
			
		} catch (Exception e){
			log.log(Level.SEVERE, "acuseCancelacion", e);
		}finally{
			if (respuesta != null) {
				for (int i = 0; i < respuesta.length; i++) {
					log.log(Level.INFO, "Respuesta WSFE:[ " + i + " ]" + respuesta[i]);
				}
			}
		}
		return respuesta;
	}*/
	
	/**
	 * Metodo para verificar timbrado y obtener el XML
	 * @param usuario
	 * @param password
	 * @param RFCEmisor
	 * @param uuid
	 * @return respuesta
	 */
	public static ResponseBuscar consultarTimbrePorReferencia(String usuario, String password, String RFCEmisor, String uuid){
		ResponseBuscar respuesta = null;
		System.setProperty("jsse.enableSNIExtension", "false");
		
		try {			
			//Crear instancia al WS para obtener acuse
			//WebServiceFDSoapStub servicioFD = new WebServiceFDSoapStub(url, new org.apache.axis.client.Service());
			//Se recibe la respuesta
			//respuesta = servicioFD.consultarTimbrePorReferencia(usuario, password, RFCEmisor, referencia);
			
			//WS para las utilerias de Solucion Factible			
			WebServiceSFSoapStub servicioSF = new WebServiceSFSoapStub();
			UtileriasSFProd.ObjectFactory ob = new UtileriasSFProd.ObjectFactory();
			ParametrosBuscar parametros = new ParametrosBuscar();
			parametros.setOffset(0);
			parametros.setUuid(ob.createParametrosBuscarUuid(uuid.trim()));
			parametros.setLimit(10);
			//Se recibe la respuesta
			respuesta = servicioSF.buscar(usuario, password, parametros);			
			
		} catch (Exception e){
			log.log(Level.SEVERE, "consultarTimbrePorReferencia", e);
		}finally{
			if (respuesta != null) {
				/*for (int i = 0; i < respuesta.length; i++) {
					log.log(Level.INFO, "Respuesta WSFE:[ " + i + " ]" + respuesta[i]);
				}*/
				log.log(Level.INFO, "Respuesta WSFE: " + respuesta.getStatus() + ", mensaje: " + respuesta.getMensaje());
			}
		}
		
		return respuesta;
	}
	
	
	public static File obtenerArchivo(MEXMEConfigFE fe, String tipo){
		String path = MAttachmentEntry.getTmpDirectory();
		File certificado = new File(path.concat(File.separator).concat("certificado.cer"));
		File llave = new File(path.concat(File.separator).concat("key.key"));
		
		if(tipo.equalsIgnoreCase("cer")){
			if (fe != null) {
				// Obtener el archivo de certificado
				byte[] cer = fe.getAttachmentData(".cer");
				if (cer == null){
					return null;
				}
				
				try {
					FileUtils.writeByteArrayToFile(certificado, cer);
				} catch (IOException e) {					
					log.log(Level.INFO, "WebServiceTimbrarXML.obtenerArchivo(): .cer", e);
				}
			}
			
			
		}else if(tipo.equalsIgnoreCase("key")){
			if (fe != null) {								
				// Obtener el archivo de la llave
				byte[] key = fe.getAttachmentData(".key");
				if (key == null) {
					return null;
				}		
				
				try {
					FileUtils.writeByteArrayToFile(llave, key);
				} catch (IOException e) {				
					log.log(Level.INFO, "WebServiceTimbrarXML.obtenerArchivo(): .key", e);
				}
			}
		}
		
		if(tipo.equalsIgnoreCase("cer")){
			return certificado;
		}else{
			return llave; 
		}		
	}
}
