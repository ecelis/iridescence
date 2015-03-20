package org.compiere.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.logging.Level;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import org.compiere.model.DatosFacturaCompiereFE;
import org.compiere.model.MEXMEImpresora;
import org.compiere.util.CLogger;

import com.sun.net.ssl.KeyManagerFactory;
import com.sun.net.ssl.SSLContext;
import com.sun.net.ssl.TrustManagerFactory;

/**
 * Impresion de Facturacion Electronica desde Compiere Creado: 19/Mar/2009
 * 
 * @author otorres
 */
public class ImpresionSRVCompiereFE extends SvrProcess implements Runnable{

	private static CLogger		s_log = CLogger.getCLogger (ImpresionSRVCompiereFE.class);
	static KeyStore clientKeyStore;
	
	public static SSLContext sslContext;
	static KeyStore serverKeyStore;
	static String passphrase;
	static String passphraseSrv;
	static SecureRandom secureRandom;
	static String dirHomeCompiere;
	public ImpresionSRVCompiereFE() {
		super();		
		
	}

	/**
	 * Preparar: obtener par&aacute;metros
	 */
	protected void prepare() 
	{
		
	}

	/**
	 * Corre el proceso.
	 * 
	 * @return Un mensaje de estado
	 * @throws Exception
	 */	
	protected  String doIt()  {
       
		if(MEXMEImpresora.servicioArribaSSL==false)
		{
          Thread t1 = new Thread(this);
          t1.start();
          return "Se Inicio Correctamente el Servicio de Impresi\u00F3n para FE desde Compiere";	   	
		}
		else
			return "Servicio de Impresi\u00F3n Iniciado Anteriormente";
	}

	
	public void run() {
		// TODO Auto-generated method stub
		
	try {
		   iniciaSetups(true);//llama desde el servidor
			
		   SSLServerSocketFactory sf = sslContext.getServerSocketFactory();
		   SSLServerSocket ss = (SSLServerSocket)sf.createServerSocket( MEXMEImpresora.getPortServicioCompiere() );

		   // Requerir autenticacion del cliente
		   ss.setNeedClientAuth( true );
			
			while(true)
	        {
			try {
				
				Socket s = ss.accept();

				InputStream flujo = s.getInputStream();
				ObjectInputStream in = new ObjectInputStream(flujo);
				DatosFacturaCompiereFE objD= (DatosFacturaCompiereFE)in.readObject();

				in.close();
				flujo.close();
				s.close();

				flujo = null;
				in = null;
				s = null;
				
	            if(objD!=null)
	            {
				MEXMEImpresora
				.facturacionElectronica(
						objD.getCtx(),
						get_TrxName(),
						objD.getC_Invoice_ID(),
						0,
						log,
						objD.getFuncionFactura(),
						objD.getSeImprimeFactura(), objD.getCopiasAdicionales(),
						objD.getSeMandaXEmail(), objD.getEmail());

	            }			
				
		    	   
			} catch (Exception e) {
				s_log.log(Level.SEVERE, e.getMessage(), e);
			}
			
	        }
		} catch (IOException e1) {
			s_log.log(Level.SEVERE, e1.getMessage(), e1);
		}
		
	}

	
	 /************************Llamados por el servidor y clientre*********************************/
	public static void setupClientKeyStore() throws GeneralSecurityException, IOException {
	    clientKeyStore = KeyStore.getInstance( "JKS" );
	    clientKeyStore.load( new FileInputStream(dirHomeCompiere + "client.public" ),
	    		passphrase.toCharArray() );
	  }

	  public static void setupServerKeystore() throws GeneralSecurityException, IOException {
	    serverKeyStore = KeyStore.getInstance( "JKS" );
	    serverKeyStore.load( new FileInputStream( dirHomeCompiere + "server.private" ),
	    		passphraseSrv.toCharArray() );
	  }
	
	 /************************Llamados por el servidor*********************************/
	  
	public static void setupSrvSSLContext() throws GeneralSecurityException, IOException {
		    TrustManagerFactory tmf = TrustManagerFactory.getInstance( "SunX509" );
		    tmf.init( clientKeyStore );

		    KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
		    kmf.init( serverKeyStore, passphraseSrv.toCharArray() );

		    sslContext = SSLContext.getInstance( "TLS" );		    
			sslContext.init( kmf.getKeyManagers(),
		                     tmf.getTrustManagers(),
		                     secureRandom );		  		   
		  }	  
	  
	
	  /************************Llamados por el cliente*********************************/
		  
		public static void setupSSLContext() throws GeneralSecurityException, IOException {
			    TrustManagerFactory tmf = TrustManagerFactory.getInstance( "SunX509" );
			    tmf.init( serverKeyStore );

			    KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
			    kmf.init( clientKeyStore, passphrase.toCharArray() );

			    sslContext = SSLContext.getInstance( "TLS" );		    
				sslContext.init( kmf.getKeyManagers(),
			                     tmf.getTrustManagers(),
			                     secureRandom );		  		   
			  }	  
		  	  
		
	  public static void iniciaSetups(boolean llamaDesdeServidor)
	  {
		  secureRandom = new SecureRandom();
	      byte bytes[] = new byte[20];
	      secureRandom.nextBytes(bytes);
          passphrase="pwExpertFE";
          passphraseSrv="srvPwExpertFE";
	      
          dirHomeCompiere= System.getenv(org.compiere.util.Ini.COMPIERE_HOME) + File.separator + "utils" + File.separator;
          
		   try {
			   
			     setupServerKeystore();
		    	 setupClientKeyStore();
		    	 
			     if(llamaDesdeServidor)
			    	 setupSrvSSLContext();			    
			     else
			    	 setupSSLContext();			    	
			    
			     
			     MEXMEImpresora.servicioArribaSSL=true;
			     
		  } catch (GeneralSecurityException e) {
			  s_log.log(Level.SEVERE, e.getMessage(), e);
		  } catch (IOException e) {
			  s_log.log(Level.SEVERE, e.getMessage(), e);
		}
		   
	  }
	  
	  
	  

}