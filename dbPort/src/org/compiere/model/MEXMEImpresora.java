package org.compiere.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.LabelValueBean;
import org.compiere.Compiere;
import org.compiere.db.CConnection;
import org.compiere.process.ImpresionSRVCompiereFE;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;


public class MEXMEImpresora extends X_EXME_Impresora{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEImpresora.class);
	//Funciones o pantallas web en las que se realiza facturacion
	//Electronica
	public final static String FUNCION_FACTURACION_DIRECTA="FD";
	public final static String FUNCION_FACTURACION_POR_EXTENSION="FE";
	public final static String FUNCION_FACTURACION_REFACTURACION="RF";
	public final static String FUNCION_FACTURACION_CANCELACION_FACTURA_DIRECTA="CFD";
	public final static String FUNCION_FACTURACION_CANCELACION_FACTURA_EXTENSION="CFE";
	
    public final static int NUMERO_LINEAS_DETALLE=30;
    public final static int NUMERO_LINEAS_IVA_BASE=3;

    //variable que abrega el BOM a la codificacion UTF-8
    public final static char BOM = ( char ) 0xfeff;

    //variable que indica si el directorio temporal existe
    private static boolean existeDirTMP=false;
    //variable que almacena # de facturas impresas.
    private static long consecutivoFactElec=0;
    
    //variable que guarda # de puerto del servicio de impresion de FE en Compiere
    private static int portServicioCompiere=0;
    private static String srvAppMedsys=null;
    
    //variable que guarda si el servcio SSL esta arriba
    public static boolean servicioArribaSSL=false;
    
	 /**
     * @param ctx
     * @param ID
     */
    public MEXMEImpresora(Properties ctx, int ID, String trxName) {
        super(ctx, ID, trxName);

    }

    /**
     * @param ctx
     * @param rs
     */
    public MEXMEImpresora(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);

    }


    public static  String[] getImpresoraFuncion(Properties ctx,String trxName, String clave_funcion){

    	String impresoraFunc[]= new String[2];

		StringBuilder sql =
			new StringBuilder("select EXME_Impresora.name as impresora, EXME_Impresora.ip as ip \n"+
							   "from EXME_Impresora EXME_Impresora \n"+
							   "inner join EXME_Impresora_Func impf on(impf.EXME_Impresora_ID=EXME_Impresora.EXME_Impresora_ID) \n"+
							   "where EXME_Impresora.isActive='Y' and EXME_Impresora.isActive='Y' and impf.value='" + clave_funcion.trim().substring(0, 2) + "'" );

		   sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "EXME_Impresora"));

		PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				impresoraFunc[0] = rs.getString("impresora");
				impresoraFunc[1] = rs.getString("ip");
			}
			
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
        }

		//
		return impresoraFunc;
    }

	/*
	 * Metodo que envia informacion de la factura electronica
	 * El parametro infoFuncion[0] contiene el nombre de la impresora
	 * El parametro infoFuncion[1] contiene la ip del servidor de impresion
	 * El parametro funcionFactura se refiere que opcion de facturacion es la que llama a la impresion
	 * de facturacion electronica
	 */

	public static ActionErrors ImpFacturaElectronica(String[]infoFuncion, int C_Invoice_ID, Properties ctx, String funcionFactura,
			String seImprimeFactura,  String copiasAdicionales,	String seMandaXEmail, String email)
	{
		ActionErrors error= new ActionErrors();
		boolean RFC_esValido=false;

		int noRegDetalle=0;
		int noRegBases=0;
		final int largoLinea=159;
	    final int tamanoLineasProd=29;

	    //boolean error=true;
	    //Crea el objeto que almacenara temporalmente el pie de la factura
	    PieFacturaElectronica infoPie = new PieFacturaElectronica();
	    //Se determina si el RFC es valido o generico
	    
	    
	    EncabezadoFacturaElectronica infoFE= obtenInfoEncabezadoYPieFacturaElect(C_Invoice_ID, ctx, error, infoPie);
	    
	    RFC_esValido=validaRFC(infoFE.getRFCSocioNeg(),ctx);
	    //se agregan los parametros de impresion de la factura
	    infoFE.setImprimeFactura(seImprimeFactura.trim());
	    
	    if(seImprimeFactura.trim().equals("N"))
	          infoFE.setCopias("0");
	    else
	    	infoFE.setCopias(String.valueOf(Integer.parseInt(copiasAdicionales!=null && copiasAdicionales.length()>0?copiasAdicionales:"0")));	    	    	    	    
	    
	    infoFE.setEnviafacturaEmail(seMandaXEmail);
	    infoFE.setEmail(email);
	    
		//convierte los parametros de impresion para efactuara para
	    //que sean comprendidos por efactura.
		obtenParamsImprimeFacturaYenviaEmail(infoFE);

	    
		DetalleFacturaElectronica lstDetFE[]= new DetalleFacturaElectronica[NUMERO_LINEAS_DETALLE];

		noRegDetalle=obtenInfoDetalleFacturaElect(ctx, C_Invoice_ID, lstDetFE, error, infoFE.getTipoDoc(), RFC_esValido, funcionFactura,infoFE);

		BaseIVA lstbiva[]= new BaseIVA[NUMERO_LINEAS_IVA_BASE];
		
		noRegBases=obtenInfoBaseIVA(C_Invoice_ID, ctx, error,lstbiva);

		StringBuilder linea10 = creaLinea(largoLinea);
		StringBuilder linea11 = creaLinea(largoLinea);
		StringBuilder linea12 = creaLinea(largoLinea);
		StringBuilder linea13 = creaLinea(largoLinea);
		StringBuilder linea15 = creaLinea(largoLinea);
		StringBuilder linea16 = creaLinea(largoLinea);
		StringBuilder linea17 = creaLinea(largoLinea);
		StringBuilder linea23 = creaLinea(largoLinea);

		StringBuilder linea53 = creaLinea(largoLinea);
		StringBuilder linea56 = creaLinea(largoLinea);
		StringBuilder linea57 = creaLinea(largoLinea);
		StringBuilder linea58 = creaLinea(largoLinea);
		StringBuilder linea59 = creaLinea(largoLinea);
		StringBuilder linea60 = creaLinea(largoLinea);
		StringBuilder linea61 = creaLinea(largoLinea);
		StringBuilder linea64 = creaLinea(largoLinea);
		StringBuilder linea66 = creaLinea(largoLinea);
		StringBuilder linea73 = creaLinea(largoLinea);
		StringBuilder linea75 = creaLinea(largoLinea);
		StringBuilder linea77 = creaLinea(largoLinea);
		StringBuilder linea79 = creaLinea(largoLinea);
		StringBuilder linea81 = creaLinea(largoLinea);
		StringBuilder linea83 = creaLinea(largoLinea);
		//Se obtiene el nombre de la impresora y la IP del host de la impresora
		String nombreImpresora=infoFuncion[0];
		String ipImpresora=infoFuncion[1];

		//URL formados por la IP y el nombre de la impresora

		StringBuilder cadenaFinal=
		new StringBuilder(BOM + "\n\n\n\n\n\n             " + infoFE.getNombreSocioNeg())
		.append("\n\n\n");

		linea10.replace(0,("*" + infoFE.getDireccionSocioNeg() + "* *" + infoFE.getNoExterior() + "* *" + infoFE.getNoInterior() + "*").length()-1 , "*" + infoFE.getDireccionSocioNeg() + "* *" + infoFE.getNoExterior() + "* *" + infoFE.getNoInterior() + "*");
		linea10.replace(122-infoFE.getTipoSocioNeg().length(), 122, infoFE.getTipoSocioNeg());
		cadenaFinal.append(linea10);
		cadenaFinal.append("\n");

		linea11.replace(0,("*" + infoFE.getColonia() + "* *" + infoFE.getDelegacion() + "*").length()-1, ("*" + infoFE.getColonia() + "* *" + infoFE.getDelegacion() + "*"));
		linea11.replace(126, 126 + ("Mexico D.F. a:      " + infoFE.getEtiquetaTipoDoc()).length(), "M\u00E9xico D.F. a:      " + infoFE.getEtiquetaTipoDoc());
		cadenaFinal.append(linea11);
		cadenaFinal.append("\n");

		linea12.replace(0, ("*" + infoFE.getCodPostalSocioNeg() + "* *" + infoFE.getCiudadSocioNeg() + "* *" + infoFE.getEstadoSocioNeg() + "* *" + infoFE.getPaisSocioNeg() + "*").length(),
				"*" + infoFE.getCodPostalSocioNeg() + "* *" + infoFE.getCiudadSocioNeg() + "* *" + infoFE.getEstadoSocioNeg() + "* *" + infoFE.getPaisSocioNeg() + "*");

		linea12.replace(96-"Cia.: ".length(), 96 + infoFE.getClaveSocioNeg().length() , "Cia.: " + infoFE.getClaveSocioNeg() + ""); 
		//Vamos en el AREA
		linea12.replace(104,104 + (" AREA: " + infoFE.getCentroCostos()).length(), " AREA: " + infoFE.getCentroCostos());
		linea12.replace(140-infoFE.getFechaFactura().length(),140, infoFE.getFechaFactura());
		linea12.replace(159 - (infoFE.getNoFactura().length()+ " ".length() + infoFE.getFolioFiscal().length()),159,infoFE.getNoFactura()+ " " + infoFE.getFolioFiscal());	
		
		cadenaFinal.append(linea12);
		cadenaFinal.append("\n");

		linea13.replace(0, infoFE.getRFCSocioNeg().length(), "R.F.C.   : " + infoFE.getRFCSocioNeg());
		cadenaFinal.append(linea13);
		cadenaFinal.append("\n");
		cadenaFinal.append("\n"); //Linea14

		//Facturacion Directa		
		if(
			((MEXMEImpresora.FUNCION_FACTURACION_DIRECTA.equals(funcionFactura)
		     || MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_DIRECTA.equals(funcionFactura)) &&
		
		     //sino existe cuenta paciente, nombre del paciente y nombre del medico.
				//son servicios hospitalarios y no llevan datos del medico, cuenta paciente, fecha, sexo y telefono en la factura electronica
				((infoFE.getNoCtaPac().length()>0 && infoFE.getNombrePaciente().length()>0 && infoFE.getNombreMedico().length()>0)
				  //como minimo para que aparezcan los datos del paciente en FD debe llevar el nombre del paciente
			      || infoFE.getNombrePaciente().length()>0))
		      
			||
		   //En facturacion por extension no se facturan servicios hospirarios, 		   
		   ((MEXMEImpresora.FUNCION_FACTURACION_POR_EXTENSION.equals(funcionFactura) 
		    || MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_EXTENSION.equals(funcionFactura))))
		    		
		{	
			linea15.replace(0,("Medico   : " + infoFE.getNombreMedico()).length(), "M\u00E9dico   : " + infoFE.getNombreMedico());
			linea15.replace(52,52+infoFE.getNombreEntidad().length(), infoFE.getNombreEntidad());
			
			linea16.replace(0,18, "Cuenta Paciente :");
			linea16.replace(26-infoFE.getNoCtaPac().length(),26, infoFE.getNoCtaPac());
			linea16.replace(27, 27 + infoFE.getNombrePaciente().length(), infoFE.getNombrePaciente());
			
			linea17.replace(0, ("Fec.Nac. : " + infoFE.getFechaNacPac()).length(), "Fec.Nac. : " + infoFE.getFechaNacPac() + "    Edad:  ");
			linea17.replace(34-infoFE.getEdadPac().length(),34 ,infoFE.getEdadPac());
			linea17.replace(38, 38+ ("Sexo: ".length() + infoFE.getSexoPac().length()), "Sexo: " + infoFE.getSexoPac());
			linea17.replace(72,72 + ("Telefono: " + infoFE.getTelefonoPac()).length(),"Tel\u00E9fono: " + infoFE.getTelefonoPac());
			
		}//Si son notas de Credito o Debito se coloca lo que esta en la descripcion de la factura
		else if(
				(infoFE.getTipoDoc().equals(MDocType.DOCBASETYPE_ARDebitMemo)
			     || infoFE.getTipoDoc().equals(MDocType.DOCBASETYPE_ARCreditMemo))
			     && infoFE.getFacturaWEB_Compiere().equals("COMPIERE"))		
		{
			linea15.replace(0,infoFE.getLeyendaCompiere().length(), infoFE.getLeyendaCompiere());						
		}
		
		
		cadenaFinal.append(linea15);
		cadenaFinal.append("\n");
		cadenaFinal.append(linea16);
		cadenaFinal.append("\n");
		cadenaFinal.append(linea17);
				
		cadenaFinal.append("\n"); //linea18
		cadenaFinal.append("\n"); //linea19
		cadenaFinal.append("\n"); //linea20
		cadenaFinal.append("\n"); //linea21
		cadenaFinal.append("\n"); //Linea extra

		//Agrega el detalle para ser mandado a la impresora
		for(int i=0; i<noRegDetalle; i++)
		{
		   DetalleFacturaElectronica detFE = lstDetFE[i];

		   cadenaFinal.append("\n"); //linea22
		   linea23.replace(6-detFE.getCantidadProd().length(),6, detFE.getCantidadProd());
		   linea23.replace(17,17 + detFE.getNombreProducto().length(), detFE.getNombreProducto());
		   linea23.replace(110-detFE.getImporteTotal().length(),110, detFE.getImporteTotal());
		   cadenaFinal.append(linea23);
		   linea23 = creaLinea(largoLinea);
		    //linea24
		}

		
		//Agreaga Descuento Global
		if(infoFE.getFacturaWEB_Compiere().equals("WEB") && infoFE.getDescuentoGlobalFact()!=null && infoFE.getDescuentoGlobalFact().length()>0
				&& Double.parseDouble(infoFE.getDescuentoGlobalFact().replace(",", ""))>0)
		 {
		   cadenaFinal.append("\n\n");
		   
		 //0011-008 TTPR Desc. Fijo
		   if(infoFE.getC_Charge_ID()>0)
		   {   String signo="";
		      
		   //Se agrega el signo dependiendo del descuentoFijo
		       if(Double.parseDouble(infoFE.getDescuentoFijoSigno().replace(",", ""))>0)
		       signo="-";
		       
			   linea53.replace(17,17 + infoFE.getCargoNombre().length(), infoFE.getCargoNombre());
			   linea53.replace(110-(signo + infoFE.getDescuentoGlobalFact()).length(),110 , signo + infoFE.getDescuentoGlobalFact());
		   }
		   else
		   {
			   linea53.replace(17,17 + "DESCUENTO GLOBAL".length(), "DESCUENTO GLOBAL");
			   linea53.replace(110-("-" + infoFE.getDescuentoGlobalFact()).length(),110 , "-" + infoFE.getDescuentoGlobalFact());
		   }
		   


		   cadenaFinal.append(linea53);		   		   
		   noRegDetalle=+noRegDetalle+2;
		}
		
		//Dinamica las lineas de base IVA
		int rellenaLineas= tamanoLineasProd-noRegDetalle;

		for(int i=0; i<rellenaLineas+1; i++){
		   cadenaFinal.append("\n");
		}
	

		for(int i=0; i<noRegBases; i++)
		{
			BaseIVA b= lstbiva[i];
			StringBuilder linea54 = creaLinea(largoLinea);

					
			linea54.replace(0, 3, "Base");
			linea54.replace(25-b.getImporteTotalBase().length(), 25, b.getImporteTotalBase() + "   IVA Tasa  ");
			linea54.replace(40-b.getBase().length(), 40, b.getBase());
			linea54.replace(41, 41, "%");
			linea54.replace(60-b.getIVABase().length(), 60, b.getIVABase());
			cadenaFinal.append(linea54);
			cadenaFinal.append("\n");

		}

		cadenaFinal.append("\n"); //linea55


		linea56.replace(15, 15 +("EMITIDO POR: " + infoFE.getNombreUsuarioFact()).length(), "EMITIDO POR: " + infoFE.getNombreUsuarioFact());

		cadenaFinal.append(linea56);
		cadenaFinal.append("\n"); //linea57

		linea57.replace(15, 15 +("**" + infoPie.getLeyenda1() +"**").length(), "**" + infoPie.getLeyenda1() +"**");
		cadenaFinal.append(linea57);
		cadenaFinal.append("\n"); //linea57
		
		//Si es nota de credito o debito se debe agregar a que factura afecta
		 if(infoFE.getTipoDoc().equals(MDocType.DOCBASETYPE_ARCreditMemo) || infoFE.getTipoDoc().equals(MDocType.DOCBASETYPE_ARDebitMemo)) 
		   linea58.replace(15, 15 + (infoPie.getLeyenda3() + " " + infoFE.getRefenciaDoc()).length(), infoPie.getLeyenda3() + " " + infoFE.getRefenciaDoc());
		 else
		   linea58.replace(15, 15 + ("**" + infoPie.getLeyenda2() + "**").length(), "**" + infoPie.getLeyenda2() + "**");//si es otro tipo de documento

		cadenaFinal.append(linea58);
		cadenaFinal.append("\n"); //linea59

		linea59.replace(15, 15 +("Num. Siniestro: " + infoFE.getNoSiniestro()).length(), "Num. Siniestro: " + infoFE.getNoSiniestro());
		cadenaFinal.append(linea59);
		cadenaFinal.append("\n"); //linea60

		linea60.replace(15, 15 +("IMPRESO POR : " + infoFE.getNombreUsuario() + "      " + infoFE.getHoraMin()).length(), "IMPRESO POR : " + infoFE.getNombreUsuario() + "      " + infoFE.getHoraMin());
		cadenaFinal.append(linea60);
		cadenaFinal.append("\n"); //linea61
		//Se envian los parametros: se imprime factura y/o se envia por email
		linea61.replace(15, 15 + infoFE.getParamsFE_ImprimeFactYEnviaMail().length(), infoFE.getParamsFE_ImprimeFactYEnviaMail());
		cadenaFinal.append(linea61);
		cadenaFinal.append("\n"); //linea62
		cadenaFinal.append("\n");
		cadenaFinal.append("\n");

		linea64.replace(15, 15 +(infoPie.getCantidadLetra() + "   **").length(), infoPie.getCantidadLetra() + "   **");
		cadenaFinal.append(linea64);
		cadenaFinal.append("\n"); //linea65
		cadenaFinal.append("\n");

		linea66.replace(15, 15 +infoPie.getLeyenda4().length(), infoPie.getLeyenda4());
		cadenaFinal.append(linea66);
		cadenaFinal.append("\n");
		cadenaFinal.append("\n");
		cadenaFinal.append("\n");
		cadenaFinal.append("\n");
		cadenaFinal.append("\n");
		cadenaFinal.append("\n");
		cadenaFinal.append("\n");
	
			
		linea73.replace(110-infoPie.getSubTotalGralFactura().length(), 110, infoPie.getSubTotalGralFactura());
		cadenaFinal.append(linea73);
		cadenaFinal.append("\n"); //linea74
		cadenaFinal.append("\n");

		linea75.replace(110-infoPie.getIVAGralFactura().length(), 110, infoPie.getIVAGralFactura());
		cadenaFinal.append(linea75);
		cadenaFinal.append("\n"); //linea76
		cadenaFinal.append("\n");


		linea77.replace(110-infoPie.getTotalGralFactura().length(), 110, infoPie.getTotalGralFactura());
		cadenaFinal.append(linea77);
		cadenaFinal.append("\n"); //linea78
		cadenaFinal.append("\n");

		//tipo de cambio
		linea79.replace(110-infoPie.getTipo_cambio().length(), 110, infoPie.getTipo_cambio());
		cadenaFinal.append(linea79);
		cadenaFinal.append("\n"); //linea80
		cadenaFinal.append("\n");

		//tipo de moneda
		linea81.replace(110-infoPie.getTotalFacturaUSD().length(), 110, infoPie.getTotalFacturaUSD());
		cadenaFinal.append(linea81);
		cadenaFinal.append("\n"); //linea82
		cadenaFinal.append("\n");

		//Se agrega el IVA en moneda nacional MXN
		String IVA_MXN="";

		try {
			if(infoPie.getIVAConversionUSD_MXN()!=null && infoPie.getIVAConversionUSD_MXN().length()>0 && Double.parseDouble(infoPie.getIVAConversionUSD_MXN().replace(",", ""))>0)
			    IVA_MXN=infoPie.getIVAConversionUSD_MXN();
			else
				IVA_MXN=infoPie.getIVANeto();
	
			linea83.replace(110-IVA_MXN.length(), 110, IVA_MXN);
			cadenaFinal.append(linea83);
			cadenaFinal.append("\n"); //linea82
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}

	try {

		     String nombreArchivo="";
		     FileOutputStream os=null;
		     PrintStream ps=null;
			// enviar a imprimir a la impresora en red, debemos conocer el
			// nombre de la

			// Formato UNIX
			// FileOutputStream os = new
			// FileOutputStream("\\\\Au-4022\\hp1100");


	        /*
		     *        IMPRESION DE FACTURACION ELECTRONICA
		     *   PARA UN SERVIDOR DE APLICACIONES INSTALADO EN LINUX
		     */
		    //Crea archivos temporales que seran enviados a efactura
		    if(System.getProperty("os.name").toUpperCase().trim().equals("LINUX"))
		    {

		       String ubicaDirTemp=Compiere.getCompiereHome().replace("/", "//") + "//utils//tmp";

		       //Se realiza esta comparacion con la variable estatica
		       //para que no leea del disco duro en cada impresion de la factura
		       if(MEXMEImpresora.existeDirTMP==false)
		       {

		           File f= new File(ubicaDirTemp);
		           if(!f.exists())
		               f.mkdir();

		       }

		       //aumenta el # facturas impresas
		       consecutivoFactElec++;

		       SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy_hhmmss");
		       nombreArchivo=ubicaDirTemp + "//" + infoFE.getClaveUsuario() + formato.format(new java.util.Date()) + "_Cs"+ consecutivoFactElec +".txt";
		       //**********************************************************


		       try
		       {
		    	   os = new FileOutputStream(nombreArchivo);
		    	   ps = new PrintStream(os);
		    	   ps.println(new String(cadenaFinal.toString().getBytes("UTF-8"),"UTF-8"));
		    	   os.close();
		    	   ps.close();
		       }
	    	  catch(FileNotFoundException e)
	    	   {
	    		   error.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.facturaElectronica.impresoraNoValida"));

	    	   }
              finally
              {

            	if(os!=null)
            		os.close();

            	if(ps!=null)
            		ps.close();

            	os=null;
            	ps=null;
              }
			  //Descomentar cuando terminen las pruebas
			   imprimirLinuxLPR(nombreArchivo, nombreImpresora, error);

		    }
		    else
		    {
		    	/*
				 *     IMPRESION DE FACTURACION ELECTRONICA
				 *     PARA UN SERVIDOR DE APLICACIONES INSTALADO EN WINDOWS
				 */


				try
		    	{
					String urlImpresora= "//"+ipImpresora+"//" +nombreImpresora;

				    //FileOutputStream os = new FileOutputStream("//192.168.2.81//HPLaserJ3050");
					os = new FileOutputStream(urlImpresora);
					ps = new PrintStream(os);
					ps.println(cadenaFinal.toString());
					os.close();
					ps.close();
		    	}
		    	catch(FileNotFoundException e)
		    	{
		    		error.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.facturaElectronica.impresoraNoValida"));

		    	}
	            finally
	            {

	            	if(os!=null)
	            		os.close();

	            	if(ps!=null)
	            		ps.close();

	            	os=null;
	            	ps=null;
	            }
		    }
			  //**************************************************************
		      //descomentar cuando terminen las pruebas
		        //File f1 = new File(nombreArchivo+"TEMP");
				//f1.delete();
				//f1 = new File(nombreArchivo+".txt");
				//f1.delete();
		     //*************************************************************



		} catch (Exception e) {
			//System.out.println("Error: " + e.getMessage());
			s_log.log(Level.SEVERE, e.getMessage(), e);
		}

		return error;

	}

	/*
	 * Metodo que envia informacion de la factura electronica
	 * El parametro infoFuncion[0] contiene el nombre de la impresora
	 * El parametro infoFuncion[1] contiene la ip del servidor de impresion
	 */

	private static StringBuilder creaLinea(int tamano)
	{
		StringBuilder linea= new StringBuilder("");

		for(int i=0;i<tamano;i++)
		 linea.append(" ");

		return linea;
	}

	//Impresion que se manda via LPR a una impresora, de Linux a "Windows o Linux"
	public static synchronized void imprimirLinuxLPR(String nombreArchivo, String nombreImpresora, ActionErrors error) throws InterruptedException{

	      try{
	    	 //  Runtime.getRuntime().exec("unix2dos " + nombreArchivo).waitFor();
               //Runtime.getRuntime().exec("lpr -r -P" + nombreImpresora + " " + nombreArchivo);
               Runtime.getRuntime().exec("lpr -r -P" + nombreImpresora + " " + nombreArchivo);

	         } catch (IOException e) {
	           error.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.facturaElectronica.LPR_envioLinux"));
	           s_log.log(Level.SEVERE, e.getMessage(), e);
	           //System.out.println("*******Error al ejecutar LPR en la linea de comandos Linux******");
	      } catch (NumberFormatException e) {
	    	  s_log.log(Level.SEVERE, e.getMessage(), e);
			}

	   }

	//procedimiento que cambia las vocales por su codigo unicode utf8
	
	/*
	public static String cambiaVocalesConAcento(StringBuilder cadena )
	{
		return cadena.toString().replace("�","\u00E1").replace("�","\u00E9").replace("�","\u00ED").replace("�","\u00F3").replace("�","\u00FA")
								.replace("�","\u00C1").replace("�","\u00C9").replace("�","\u00CD").replace("�","\u00D3").replace("�","\u00DA");	
	}
   */
	
    public static EncabezadoFacturaElectronica obtenInfoEncabezadoYPieFacturaElect(int C_Invoice_ID, Properties ctx,
    		ActionErrors error, PieFacturaElectronica infoPie)
    {
    	EncabezadoFacturaElectronica infoFE= new EncabezadoFacturaElectronica();

    	//Se busca el encabezado de la factura si se realizo por web o en compiere
    	//Si se obtiene el encabezado a la primera entonces se hizo en web, de lo contrario se hizo en compiere
     int numEjecucion=0;
     boolean encontroRegistro=false;
     
     do
     {

        StringBuilder sql= new StringBuilder("select * from ( \n" +
        		"select bp.value as claveSocio, " +
 
        		(numEjecucion==0?
    			"C_Invoice.description as socioNegocios, C_Invoice.poreference as RFC_Socio, C_Invoice.address1  as direccionSocio, C_Invoice.address2 as colonia,C_Invoice.postal as cpSocio, \n" +
        		"C_Invoice.city as ciudadSocio, C_Invoice.numext as noExterior, C_Invoice.numin as noInterior,trim(substr(edad(C_Invoice.fechanac,C_Invoice.created,1),1,2)) as edad, \n":       
        			
        		"bp.name as socioNegocios, bp.taxID as RFC_Socio, l.address1  as direccionSocio, l.address2 as colonia,l.postal as cpSocio,\n"  +
        		"l.city as ciudadSocio, l.numext as noExterior, l.numin as noInterior, '0' as edad, \n" )
        		+
        		
        		"r.name as estadoSocio,c.name as paisSocio, mun.name as deleg_mpio, C_Invoice.documentno as factura, orgtrx.value as ccosto, \n" +
        		"bpg.name as tipoSocio, to_char(C_Invoice.created,'dd/mm/yy') as fechaFactura, to_char(C_Invoice.created,'hh:mi:ss') as horaMinSeg, " +
        		"C_Invoice.nombre_medico, org.name as entidad, ctp.documentno as noCtaPac, C_Invoice.nombre_paciente, to_char(C_Invoice.fechanac,'dd/mm/yyyy') as fechanac \n" +
        		", C_Invoice.sexo, C_Invoice.invoicephone as telparticular, \n" +
        		"usr.name as claveUsuario, usr.description as usuario, usrFact.name as claveUsuarioFact, usrFact.description as usuarioFact, pac.nosiniestro, dt.docbasetype as tipoDoc, nvl(C_Invoice.discountamt,0) as descuentoGlobalFact, \n" +
        		//0011-008 TTPR Desc. Fijo
        		" C_Invoice.C_Charge_ID, cg.name as cargoNombre, \n" +
        		"C_Invoice.totallines as subtotalFactura, (select nvl(sum(it.taxamt),0) as iva from c_invoicetax it where it.c_invoice_id=C_Invoice.c_invoice_id) as ivaFactura, \n" +
        		"C_Invoice.grandtotal as totalFactura, nvl(cr.multiplyrate,0) as tipoCambio, (C_Invoice.grandtotal * nvl(cr.multiplyrate,0)) as totalFacturaUSD, \n"+
        		"cfe.leyenda1, cfe.leyenda2, cfe.leyenda3, cfe.leyenda4, cfe.serieDocFac, cfe.serieDocNC, cfe.serieDocND, cfe.ad_org_id, \n" +
        		"rf.documentno as refenciaDoc, C_Invoice.description as leyendaCompiere \n" +
        		"from c_bpartner bp \n" +
        		"inner join exme_configFE cfe on(cfe.ad_client_id=bp.ad_client_id and cfe.ad_org_id in(0,?)) \n" +//#2
        		"inner join c_invoice C_Invoice on(C_Invoice.c_bpartner_id=bp.c_bpartner_id) \n" +
        		"left  join c_invoice rf on(rf.c_invoice_id=C_Invoice.ref_invoice_id) \n" +
        		"inner join c_doctype dt on(dt.c_doctype_id=C_Invoice.c_doctypetarget_id) \n" +
        		"inner join exme_estserv est on(est.exme_estserv_id=C_Invoice.exme_estserv_id) \n" +
        		"left join ad_org orgtrx on(orgtrx.ad_org_id=est.ad_orgtrx_id) \n" +
        		"left join c_bp_group bpg on(bpg.c_bp_group_id=bp.c_bp_group_id) \n" +
        		"left join c_charge cg on(cg.c_charge_id=C_Invoice.c_charge_id) \n" +
        		
        		(numEjecucion==0?
        		"inner join c_region r on(r.c_region_id=C_Invoice.c_region_id) \n" +
        		"inner join c_country c on(c.c_country_id=C_Invoice.c_country_id) \n" +
        		"left join exme_towncouncil mun on(mun.exme_towncouncil_id=C_Invoice.exme_towncouncil_id) \n":
        		        		
        		"left join c_bpartner_location bpl on(bpl.c_bpartner_id=bp.c_bpartner_id) \n" +
        		"left join c_location l on(l.c_location_id=bpl.c_location_id and bpl.isbillto='Y') \n" +
        		"left join c_region r on(r.c_region_id=l.c_region_id) \n" + 
        		"left join c_country c on(c.c_country_id=l.c_country_id) \n" + 
        		"left join exme_towncouncil mun on(mun.exme_towncouncil_id=l.exme_towncouncil_id) \n") 

        		+
        		"inner join ad_org org on(org.ad_org_id=C_Invoice.ad_org_id) \n" +
        		"left join exme_ctapacext ctpe on(ctpe.exme_ctapacext_id=C_Invoice.exme_ctapacext_id) \n" +
        		"left join exme_ctapac ctp on(ctp.exme_ctapac_id=ctpe.exme_ctapac_id)  \n" +
        		"left join exme_paciente pac on(pac.exme_paciente_id=C_Invoice.exme_paciente_id) \n" +
        		"inner join c_currency cmxn on (cmxn.iso_code='MXN') \n" +
        		"inner join c_currency cur on(cur.c_currency_id=C_Invoice.c_currency_id) \n" +
        		"left join c_conversion_rate cr on(cr.c_currency_id=cur.c_currency_id and cr.c_currency_id_to=cmxn.c_currency_id \n" +
        		"and ");
        if (DB.isOracle()) {
        	sql.append("trunc(C_Invoice.created,'dd') between trunc(cr.validfrom,'dd') and trunc(cr.validto,'dd')");	
        } else if (DB.isPostgreSQL()) {
        	sql.append("date_trunc('day', C_Invoice.created) between date_trunc('day', cr.validfrom) and date_trunc('day', cr.validto)");
        }
        
        
        sql.append(") \n");
        sql.append("inner join ad_user usrFact on(usrFact.ad_user_id=C_Invoice.createdby) \n" +
        		"inner join ad_user usr on(usr.ad_user_id=?) \n" +        //#2		
        		"where C_Invoice.c_invoice_id=?");//#3
           
            sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_Invoice"));
            sql.append("\n order by cfe.ad_org_id desc)");
            if (DB.isOracle()) {
            	sql.append("\n where rownum=1");
            } else if (DB.isPostgreSQL()) {
            	sql.append(" AS consulta").append(StringUtils.EMPTY);
            	sql = new StringBuilder(DB.getDatabase().addPagingSQL(sql.toString(), 1, 1));
            }

            numEjecucion++;
            
          //System.out.println(sql.toString());
            PreparedStatement pstmt = null;
            ResultSet rs = null;
    		try {

    			pstmt = DB.prepareStatement(sql.toString(),null);
    			pstmt.setInt(1, Env.getAD_Org_ID(ctx));
    			pstmt.setInt(2, Env.getAD_User_ID(ctx));//quien Imprime
    			pstmt.setInt(3, C_Invoice_ID);
    			rs = pstmt.executeQuery();

    			if (rs.next()) {
    				
    				if (numEjecucion == 1) {
    					infoFE.setFacturaWEB_Compiere("WEB"); 
    				} else {
    					infoFE.setFacturaWEB_Compiere("COMPIERE");
    				}
    				
    				encontroRegistro = true;
    				infoFE.setClaveSocioNeg(rs.getString("claveSocio"));
    				infoFE.setNombreSocioNeg(rs.getString("socioNegocios"));
    				infoFE.setRFCSocioNeg(rs.getString("RFC_Socio")!=null?rs.getString("RFC_Socio"):"");    				
    				infoFE.setDireccionSocioNeg(rs.getString("direccionSocio")!=null?rs.getString("direccionSocio"):"");
    				infoFE.setColonia(rs.getString("colonia")!=null?rs.getString("colonia"):"");
    				infoFE.setCodPostalSocioNeg(rs.getString("cpSocio")!=null?rs.getString("cpSocio"):"");
    				infoFE.setCiudadSocioNeg(rs.getString("ciudadSocio")!=null?rs.getString("ciudadSocio"):"");
    				infoFE.setNoExterior(rs.getString("noExterior")!=null?rs.getString("noExterior"):"");
    				infoFE.setNoInterior(rs.getString("noInterior")!=null?rs.getString("noInterior"):"");
    				infoFE.setEdadPac(rs.getString("edad")!=null?rs.getString("edad"):"");
    				
    				infoFE.setEstadoSocioNeg(rs.getString("estadoSocio")!=null?rs.getString("estadoSocio"):"");
    				infoFE.setPaisSocioNeg(rs.getString("paisSocio")!=null?rs.getString("paisSocio"):"");
    				infoFE.setDelegacion(rs.getString("deleg_mpio")!=null?rs.getString("deleg_mpio"):"");
    				infoFE.setNoFactura(rs.getString("factura")!=null?rs.getString("factura"):"");
    				//Aqui va la clave centro de costos rs.getString("")
    				infoFE.setCentroCostos(rs.getString("ccosto")!=null?rs.getString("ccosto"):"");
    				infoFE.setTipoSocioNeg(rs.getString("tipoSocio")!=null?rs.getString("tipoSocio"):"");
    				infoFE.setFechaFactura(rs.getString("fechaFactura"));
    				infoFE.setHoraMin(rs.getString("horaMinSeg"));
    				infoFE.setNombreMedico(rs.getString("nombre_medico")!=null?rs.getString("nombre_medico"):"");
    				infoFE.setNombreEntidad(rs.getString("entidad")!=null?rs.getString("entidad"):"");
    				infoFE.setNoCtaPac(rs.getString("noCtaPac")!=null?rs.getString("noCtaPac"):"");
    				infoFE.setNombrePaciente(rs.getString("nombre_paciente")!=null?rs.getString("nombre_paciente"):"");
    				infoFE.setFechaNacPac(rs.getString("fechanac")!=null?rs.getString("fechanac"):"");
    				
    				infoFE.setSexoPac(rs.getString("sexo")!=null?rs.getString("sexo").equals("M")?"Masculino":rs.getString("sexo").equals("F")?"Femenino":"Sin Asignacion":"");
    				infoFE.setTelefonoPac(rs.getString("telparticular")!=null?rs.getString("telparticular"):"");
    				infoFE.setClaveUsuario(rs.getString("claveUsuario")!=null?rs.getString("claveUsuario"):"");
    				infoFE.setNombreUsuario(rs.getString("usuario")!=null?rs.getString("usuario"):"");
    				infoFE.setClaveUsuarioFact(rs.getString("claveUsuarioFact")!=null?rs.getString("claveUsuarioFact"):"");
    				infoFE.setNombreUsuarioFact(rs.getString("usuarioFact")!=null?rs.getString("usuarioFact"):"");    				
    				infoFE.setNoSiniestro(rs.getString("nosiniestro")!=null?rs.getString("nosiniestro"):"");
    				infoFE.setTipoDoc(rs.getString("tipoDoc")!=null?rs.getString("tipoDoc"):"");

    				infoFE.setFolioFiscal(infoFE.getNoFactura()+infoFE.getTipoDoc());
    				
    				//Este descuento Global se vuelve a calcular en el detalle de la factura ya que este campo no es confiable 
    				infoFE.setDescuentoGlobalFact(formatearNumero(rs.getBigDecimal("descuentoGlobalFact").setScale(2, BigDecimal.ROUND_HALF_UP),2));
    				infoFE.setDescuentoFijoSigno(formatearNumero(rs.getBigDecimal("descuentoGlobalFact").setScale(2, BigDecimal.ROUND_HALF_UP),2));
    				
    				
    				//0011-008 TTPR Desc. Fijo
    				infoFE.setC_Charge_ID(rs.getInt("C_Charge_ID"));
    				
    				infoFE.setCargoNombre(rs.getString("cargoNombre")!=null?rs.getString("cargoNombre"):"");
    				
    				//INFORMACION PARA EL PIE DE LA FACTUA
    				BigDecimal  ivaFactura;
    				BigDecimal ivaNeto;
    				//Si el RFC es generico el subtotal incluye iva y el iva=0
    				//Si el RFC es valido el subtotal no incluye iva   			
    				if(validaRFC(infoFE.getRFCSocioNeg(),ctx) == false) {
    					infoPie.setSubTotalGralFactura(formatearNumero(rs.getBigDecimal("subtotalFactura").add( rs.getBigDecimal("ivaFactura")).setScale(2, BigDecimal.ROUND_HALF_UP),2));    					 
    					ivaFactura = new BigDecimal(0);
    				} else {
    				    infoPie.setSubTotalGralFactura(formatearNumero(rs.getBigDecimal("subtotalFactura").setScale(2, BigDecimal.ROUND_HALF_UP),2));
    				    ivaFactura = rs.getBigDecimal("ivaFactura");
    				}
    				//El iva neto es el total de iva causado. 
    				//El iva Factura es el valor de iva de acuerdo si el RFC es valido o generico. 
    				//Ya que este puede variar de acuerdo a esto.
    				ivaNeto= rs.getBigDecimal("ivaFactura");

    				//Se obtiene el iva para que en caso que sea el tipo de moneda USD,
    				//Se convierta a pesos MXN
    				
    				//Se obtiene la cantidad del monto para obneter la cantidad en letra
    				BigDecimal  cantidad = rs.getBigDecimal("totalFactura").setScale(2, BigDecimal.ROUND_HALF_UP);
    				//Se obtiene el tipo de cambio para hacer la conversion del iva
    				BigDecimal  tipoCambio = rs.getBigDecimal("tipoCambio").setScale(2, BigDecimal.ROUND_HALF_UP);

    				infoPie.setTotalFacturaUSD(formatearNumero(rs.getBigDecimal("totalFacturaUSD").setScale(2, BigDecimal.ROUND_HALF_UP),2));
    				infoPie.setIVAGralFactura(formatearNumero(ivaFactura.setScale(2, BigDecimal.ROUND_HALF_UP),2));
    				infoPie.setIVANeto(formatearNumero(ivaNeto.setScale(2, BigDecimal.ROUND_HALF_UP),2));
    				infoPie.setTotalGralFactura(formatearNumero(cantidad,2));
    				infoPie.setTipo_cambio(formatearNumero(tipoCambio,2));
    				//Se realiza la conversion del IVA de USD a MXN
    				infoPie.setIVAConversionUSD_MXN(formatearNumero(ivaNeto.multiply(tipoCambio).setScale(2, BigDecimal.ROUND_HALF_UP),2));
                    //Obtiene la cantidad con letra
             	    infoPie.setCantidadLetra(precioMexico(cantidad.intValue()==1?true:false, ctx, infoPie.getTotalGralFactura().toString().replace(",", "")));

            	    //Leyendas para la factura electronica
            	    infoPie.setLeyenda1(rs.getString("leyenda1")!=null?rs.getString("leyenda1"):"");
            	    infoPie.setLeyenda2(rs.getString("leyenda2")!=null?rs.getString("leyenda2"):"");
            	    infoPie.setLeyenda3(rs.getString("leyenda3")!=null?rs.getString("leyenda3"):"");
            	    infoPie.setLeyenda4(rs.getString("leyenda4")!=null?rs.getString("leyenda4"):"");
            	    //Valor por tipo de documento que forma parte del folio fiscal
            	    infoFE.setSerieDocFac(rs.getString("serieDocFac")!=null?rs.getString("serieDocFac").trim():"");
            	    infoFE.setSerieDocNC(rs.getString("serieDocNC")!=null?rs.getString("serieDocNC").trim():"");
            	    infoFE.setSerieDocND(rs.getString("serieDocND")!=null?rs.getString("serieDocND").trim():"");

            	    //Se obtiene el folio fiscal Y se determina el valor de la etiqueta de tipo de documento
    				obtenerFolioFiscal(infoFE);

    				infoFE.setRefenciaDoc(rs.getString("refenciaDoc")!=null?rs.getString("refenciaDoc").trim():"");
    				infoFE.setLeyendaCompiere(rs.getString("leyendaCompiere")!=null?rs.getString("leyendaCompiere"):"");
    				
    				//infoFE.setCentroCostos("17852");
    				/*
    				infoFE.setFolioFiscal("10353000");
    				infoFE.setNoFactura("353000");
    				*/
    			}
    	//		System.out.println(sql.toString());
    		} catch (Exception e) {
    			s_log.log(Level.SEVERE, "sql: " + sql, e);
    		} finally {
    			DB.close(rs,pstmt);
            }

    		
     }while(numEjecucion<2  && encontroRegistro==false);
         	 
    return infoFE;
    
    }

    public static int  obtenInfoDetalleFacturaElect(Properties ctx, int C_Invoice_ID, DetalleFacturaElectronica detFE[],
    		ActionErrors error, String tipoDoc, boolean RFC_esValido, String funcionFactura, EncabezadoFacturaElectronica infoFE)
    {
    	int noReg=0;
       BigDecimal descuentoGlobal= new BigDecimal(0.0);
        StringBuilder sql= null;

    	//Si el RFC es valido el monto de la linea es sin iva (priceList)
    	//Si el RFC es generico el monto de la linea es con iva
    	String tasaIVA=RFC_esValido?"1":
    		" ((t.rate/100)+1)";
/*
    	String calculoDescuento=RFC_esValido?"(C_InvoiceLine.pricelist-C_InvoiceLine.priceactual)":
    		"((" + nombreCampoMontoLine +
    		      ") * trunc((c_invoiceline.pricelist-c_invoiceline.priceactual)/
    		      c_invoiceline.pricelist,3))";

*/
    	
    	
        //Si es factura directa
        if(MEXMEImpresora.FUNCION_FACTURACION_DIRECTA.equals(funcionFactura)
        		|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_DIRECTA.equals(funcionFactura))
        {

              sql=new StringBuilder(
        		"select C_InvoiceLine.qtyinvoiced as cantidad, " +
 
        		//Si es documentno hecho en compiere, se le agrega la descripcion de la linea del producto
        		(infoFE.getFacturaWEB_Compiere().equals("COMPIERE")?        		       				
        		"trim(upper(p.name || ' ' || nvl(C_InvoiceLine.description,' '))) as producto, \n" :
        	    //si no es factura hecha en compiere
        		"upper(nvl(C_InvoiceLine.descriptionserv,p.name)) as producto, \n") +
        		
        		"(C_InvoiceLine.qtyinvoiced * C_InvoiceLine.priceList) * "+ tasaIVA + " as total_linea, \n" +
        		
        		
        		"(abs((C_InvoiceLine.pricelimit + abs(C_InvoiceLine.pricelist * (edl.list_discount/100))-C_InvoiceLine.pricelist)  * "+ tasaIVA + " ) +  \n" +  //as descuentofamciva,
        		"abs((C_InvoiceLine.pricelist * (edl.list_discount/100)* "+ tasaIVA + "))) \n" + //as descuentoconvciva,        		        		
        		 " * C_InvoiceLine.QTYInvoiced  as descuento, 'DESCUENTO A '|| upper(nvl(C_InvoiceLine.descriptionserv,p.name)) as conceptoFacDesc, \n"+
        		 
        		        		 
        		 
        		 
        		 "abs((C_InvoiceLine.priceActual - C_InvoiceLine.priceLimit) * "+ tasaIVA + ") * C_InvoiceLine.QTYInvoiced as descGlobal \n" +
        		"from c_invoiceline C_InvoiceLine \n" +
        		"inner join m_product p on(p.m_product_id=C_InvoiceLine.m_product_id) \n" +
        		"inner join exme_configpre cp on(cp.ad_org_id=C_InvoiceLine.ad_org_id and cp.coaseguro_id <> C_InvoiceLine.m_product_id and cp.deducible_id <> C_InvoiceLine.m_product_id) \n" +
        		"left  join exme_esqdesline edl on (edl.exme_esqdesline_id=C_InvoiceLine.exme_esqdesline_id) \n" +
        		"inner join c_tax t on (t.c_tax_id=C_InvoiceLine.c_tax_id) \n" +
        		"where C_InvoiceLine.c_invoice_id=? AND C_InvoiceLine.AD_Client_ID=? ");

                 sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_InvoiceLine"));
        }  //Si es facturacion por extension
        else  if(MEXMEImpresora.FUNCION_FACTURACION_POR_EXTENSION.equals(funcionFactura)
        		|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_EXTENSION.equals(funcionFactura))

        {       String whereCompletar="where C_InvoiceLine.c_invoice_id=?";
        	    whereCompletar=MEXMELookupInfo.addAccessLevelSQL(ctx, whereCompletar, "C_InvoiceLine");

        	    sql=new StringBuilder(
        	    		"select sum(C_InvoiceLine.qtyinvoiced) as cantidad, " +
        	    		"case when upper(uom.name) like '%PAQUETE%' then upper(p.name) \n" +
        	    		"else nvl(upper(cf.name),'SIN CONCEPTO DE FACTURACION') end as producto, \n" +

        	    		
        	    		"sum((C_InvoiceLine.qtyinvoiced * C_InvoiceLine.priceList)*  "+ tasaIVA + ") as total_linea, \n" +        	    		 
                		 
                		 "sum((abs((C_InvoiceLine.pricelimit + abs(C_InvoiceLine.pricelist * (edl.list_discount/100))-C_InvoiceLine.pricelist)  * "+ tasaIVA + " ) +  \n" +  //as descuentofamciva,
                 		"abs((C_InvoiceLine.pricelist * (edl.list_discount/100)* "+ tasaIVA + "))) \n" + //as descuentoconvciva,        		        		
                 		 " * C_InvoiceLine.QTYInvoiced)  as descuento,  \n"+                 		
                		 
        	    		"('DESCUENTO A CARGOS, '  || upper(cf.name)) as conceptoFacDesc, \n" +
        	    	        	    	      	    		
        	    		//Se cambiara por el nuevo campo de la tabla exme_conceptofac
        	    		"sum(abs((C_InvoiceLine.priceActual - C_InvoiceLine.priceLimit) * "+ tasaIVA + ") * C_InvoiceLine.QTYInvoiced) as descGlobal \n" +
        	    		"from c_invoiceline C_InvoiceLine \n" +        	    		
        	    		"inner join m_product p on(p.m_product_id=C_InvoiceLine.m_product_id)  \n" +
        	    		// Lama .- Product_Cte
        				" LEFT JOIN  M_Product_Cte cte ON (p.M_Product_ID = cte.M_Product_ID AND cte.AD_Client_ID = " + Env.getAD_Client_ID(ctx)+" ) " + // 1
        	    		"inner join c_uom uom on(uom.c_uom_id=p.c_uom_id)\n" +
        	    		"inner join exme_configpre cp on(cp.ad_org_id=C_InvoiceLine.ad_org_id and cp.coaseguro_id <> C_InvoiceLine.m_product_id and cp.deducible_id <> C_InvoiceLine.m_product_id) \n" +
        	    		"left join exme_conceptofac cf on(cf.exme_conceptofac_id= NVL(cte.exme_conceptofac_id,p.exme_conceptofac_id)) \n" +
        	    		"left join exme_esqdesline edl on (edl.exme_esqdesline_id=C_InvoiceLine.exme_esqdesline_id) \n" +
        	    		"inner join c_tax t on (t.c_tax_id=C_InvoiceLine.c_tax_id) \n" +
        	    		whereCompletar + " \n" +
        	    		"group by cf.name,\n" +
        	    		"case when upper(uom.name) like '%PAQUETE%' then upper(p.name) \n" +
        	    		"else nvl(upper(cf.name),'SIN CONCEPTO DE FACTURACION') end");
        	    
        	   // System.out.println(sql.toString());
         }


        PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {
			//System.out.println(sql.toString());
			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setInt(1, C_Invoice_ID);
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
	        rs = pstmt.executeQuery();

			while (rs.next())
			{
				if(noReg<=NUMERO_LINEAS_DETALLE)
				{
					DetalleFacturaElectronica detReg= new DetalleFacturaElectronica();

					//Si es facturacion por extension
			        if(MEXMEImpresora.FUNCION_FACTURACION_POR_EXTENSION.equals(funcionFactura)
			        		|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_EXTENSION.equals(funcionFactura))
			        	detReg.setCantidadProd("1");
			        //Si es facturacion directa
			        else if(MEXMEImpresora.FUNCION_FACTURACION_DIRECTA.equals(funcionFactura)
			              		|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_DIRECTA.equals(funcionFactura))
			        	detReg.setCantidadProd(rs.getObject("cantidad").toString());

					//La cantidad se define a uno por el manejo del servidor de facturacion
					detReg.setNombreProducto(rs.getObject("producto").toString());
					detReg.setImporteTotal(formatearNumero(rs.getBigDecimal("total_linea").setScale(2,BigDecimal.ROUND_HALF_UP),2));
					descuentoGlobal=descuentoGlobal.add(rs.getBigDecimal("descGlobal"));
					detFE[noReg]=detReg;

					noReg++;

					//Si tiene descuento el concepto de facturacion
					BigDecimal descuento=rs.getBigDecimal("descuento")!=null?
							rs.getBigDecimal("descuento").setScale(2,BigDecimal.ROUND_HALF_UP):Env.ZERO;

					if(descuento.floatValue()>0)
					 {
						detReg= new DetalleFacturaElectronica();

						//Si es facturacion por extension
				        if(MEXMEImpresora.FUNCION_FACTURACION_POR_EXTENSION.equals(funcionFactura)
				        		|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_EXTENSION.equals(funcionFactura))
				        	detReg.setCantidadProd("1");
				        //Si es facturacion directa
				        else if(MEXMEImpresora.FUNCION_FACTURACION_DIRECTA.equals(funcionFactura)
				              		|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_DIRECTA.equals(funcionFactura))
				        	detReg.setCantidadProd(rs.getObject("cantidad").toString());

						//La cantidad se define a uno por el manejo del servidor de facturacion
						detReg.setNombreProducto(rs.getObject("conceptoFacDesc").toString());
						detReg.setImporteTotal("-"+ formatearNumero(descuento,2));
						
						detFE[noReg]=detReg;

						noReg++;
					 }


				}


			}

			
			infoFE.setDescuentoGlobalFact(formatearNumero(descuentoGlobal.setScale(2,BigDecimal.ROUND_HALF_UP),2));


			if(noReg>NUMERO_LINEAS_DETALLE)
				error.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.facturaElectronica.maximoLineasDetalle"));
			else
			{

				//se obtiene el coaseguro, deducible y descuento por convenio
				DeducibleYCoaseguroFE dc= obtenInfoDescuentosYCoaseguroFE(C_Invoice_ID, ctx, error, tipoDoc, RFC_esValido);

				//indica que existe deducible o/y coaseguro o/y descuento
				if(dc.isExisteInfo())
				{
					DetalleFacturaElectronica detReg=null;

					if(Float.parseFloat(dc.getCoaseguro().replace(",", ""))!=0)
					{
					//se agrega el coaseguro
						detReg= new DetalleFacturaElectronica();
						if(MEXMEImpresora.FUNCION_FACTURACION_DIRECTA.equals(funcionFactura)
								|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_DIRECTA.equals(funcionFactura))
							detReg.setCantidadProd("1");
						else if(MEXMEImpresora.FUNCION_FACTURACION_POR_EXTENSION.equals(funcionFactura)
							|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_EXTENSION.equals(funcionFactura))
							detReg.setCantidadProd("");

						detReg.setNombreProducto("COASEGURO");
						detReg.setImporteTotal(dc.getCoaseguro());
						detFE[noReg]=detReg;
						noReg++;
					}

				   if(Float.parseFloat(dc.getDeducible().replace(",", ""))!=0)
					{
					    //se agrega el deducible
						detReg= new DetalleFacturaElectronica();

						if(MEXMEImpresora.FUNCION_FACTURACION_DIRECTA.equals(funcionFactura)
								|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_DIRECTA.equals(funcionFactura))
							detReg.setCantidadProd("1");
						else if(MEXMEImpresora.FUNCION_FACTURACION_POR_EXTENSION.equals(funcionFactura)
								|| MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_EXTENSION.equals(funcionFactura))
							detReg.setCantidadProd("");

						detReg.setNombreProducto("DEDUCIBLE");
						detReg.setImporteTotal(dc.getDeducible());
						detFE[noReg]=detReg;
						noReg++;
					}

				}
			}

	} catch (Exception e) {
		s_log.log(Level.SEVERE, e.getMessage(),e);
	} finally {
		DB.close(rs,pstmt);
		pstmt = null;
		rs =null;
    }

    	return noReg;
    }


    public static DeducibleYCoaseguroFE  obtenInfoDescuentosYCoaseguroFE(int C_Invoice_ID, Properties ctx,
    		ActionErrors error, String tipoDoc, boolean RFC_esValido)
    {
    	DeducibleYCoaseguroFE dc= new DeducibleYCoaseguroFE();
    	StringBuilder sql=null;

    	//Si no es factura  no obtiene coaseguro, descuento y deducible(CHECAR ESTA INFO)
    //	if(!tipoDoc.equals(MDocType.DOCBASETYPE_ARInvoice))
    //		return dc;

    	//Si el RFC es valido el monto de la linea es sin iva (PriceList)
    	//Si el RFC es generico el monto de la linea es con iva (PriceList * (t.rate/100)+1))
    

    	
    	String nombreCampoMontoLine=RFC_esValido?"C_InvoiceLine.priceList":
    		"(C_InvoiceLine.priceList * ((t.rate/100)+1))";
    	
    	
                sql= new StringBuilder(
        		"select sum(deducible) as deducible, \n" +
        		"sum(coaseguro) as coaseguro \n" +
        		"from( \n" +
        		"		select distinct \n" +
        		"		(case when \n" +
        		"				cp.deducible_id=C_InvoiceLine.m_product_id then \n" +
        		"				C_InvoiceLine.qtyinvoiced *  ("+ nombreCampoMontoLine + ") \n" +
        		"				else 0 end) \n" +
        		"		as deducible, \n" +
        		"		( case when  \n" +
        		"				C_InvoiceLine.m_product_id=cp.coaseguro_id then \n" +
        		"				C_InvoiceLine.qtyinvoiced *  (" + nombreCampoMontoLine + ") \n" +
        		"				else 0 end) \n" +
        		"				as coaseguro \n" +
        		"				from exme_configpre cp \n" +
        		"				inner join c_invoiceline C_InvoiceLine on(C_InvoiceLine.c_invoice_id = ?) \n" +
        		"               inner join c_tax t on (t.c_tax_id=C_InvoiceLine.c_tax_id) \n" +
        		" 				WHERE C_InvoiceLine.AD_Client_ID = ?   \n "+
        		") ");




        PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setInt(1, C_Invoice_ID);
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery();

			if (rs.next())
			{

					dc.setDeducible(formatearNumero(rs.getBigDecimal("deducible").setScale(2,BigDecimal.ROUND_HALF_UP),2));
					dc.setCoaseguro(formatearNumero(rs.getBigDecimal("coaseguro").setScale(2,BigDecimal.ROUND_HALF_UP),2));

					//indica que existe deducible o/y coaseguro o/y descuento
				    dc.setExisteInfo(true);
			}


	} catch (Exception e) {
		s_log.log(Level.SEVERE, e.getMessage(), e);
	} finally {
		DB.close(rs,pstmt);
		pstmt = null;
		rs =null;
    }

    	return dc;
    }



    public static int obtenInfoBaseIVA(int C_Invoice_ID, Properties ctx,
    		ActionErrors error, BaseIVA biva[])
    {
    	StringBuilder sql=null;
    	int noReg=0;


        sql= new StringBuilder(
        "select sum(it.taxbaseamt) as base_iva, sum(it.taxamt) as iva, t.rate as porcentaje \n" +
        "from c_invoicetax it \n" +
        "inner join c_tax t on(t.c_tax_id=it.c_tax_id) \n" +
        "where it.c_invoice_id=? and it.isActive='Y' AND it.AD_Client_ID=? \n" +
        "group by t.rate \n" +
       // "having t.rate>0 \n" +   //Para que visualice los de iva 0
        "order by t.rate");

        PreparedStatement pstmt = null;
        ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(),null);
			pstmt.setInt(1, C_Invoice_ID);
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				    BaseIVA b= new BaseIVA();
				    b.setImporteTotalBase(formatearNumero(rs.getBigDecimal("base_iva").setScale(2,BigDecimal.ROUND_HALF_UP),2));
				    b.setIVABase(formatearNumero(rs.getBigDecimal("iva").setScale(2,BigDecimal.ROUND_HALF_UP),2));
					b.setBase(rs.getObject("porcentaje").toString());

					biva[noReg]=b;
					noReg++;

			}


	} catch (Exception e) {
		s_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
	} finally {
		DB.close(rs,pstmt);
		pstmt = null;
		rs =null;
    }

    	return noReg;
    }



	/***************************************************************/
	/**
    *  Proceso que agrega la palabra "PESOS" y "M.N."
    *  en el precio con letra de la factura (Solo Mexico)
	*/
	public static String precioMexico(boolean esUnPeso, Properties ctx, String totalFactura){

		 Locale loc = Env.getLanguage(ctx).getLocale();
    	 Language lag = new Language(loc.getDisplayLanguage(), Env.getContext(ctx, "#AD_Language"), loc);
    	
    	 String precioLetra = Msg.getAmtInWords(lag,totalFactura);
    	 
 	    
	    String idioma = Env.getContext(ctx, "#AD_Language");
	    if (idioma.equalsIgnoreCase("es_MX")){

		StringTokenizer t= new StringTokenizer(precioLetra," ");
		String precioCompleto="";

        boolean bandera=true;

		while(t.hasMoreTokens() && bandera){
             String token= t.nextToken();

             StringTokenizer pesosMN= new StringTokenizer(token,"/");

             while(pesosMN.hasMoreTokens() && bandera){
            	      String mn=pesosMN.nextToken();
            	      if(!mn.equals(token)){
            	    	  if (esUnPeso) {
                  	        precioCompleto+="UN PESO " + token + " M.N.";
            	    	  } else {
                  	        precioCompleto+="PESOS " + token + " M.N.";
            	    	  }
            	        bandera=false;
            	      }
            	      else
                        precioCompleto+=token + " ";
             }
          }
		return precioCompleto;
  	  } else
  	      return precioLetra;
	}
	/******************************************************************/

    //Metodo para validar el RFC y determinar si es valido o generico
	//Si es true entonces es un RFC valido
	//Si es False entonces en un RFC generico
	private static boolean validaRFC(String rfc, Properties ctx)
	{
		
		MEXMEConfigFE cfe = MEXMEConfigFE.get(ctx, null);
	 

		//Si el rfc es igual a el RFCNacional o Exntranjero entonces es rfc generico
	      if(cfe.getRFC_Nacional().equals(rfc) || cfe.getRFC_Extranjero().equals(rfc))
	    	  return false;
	      else return rfc.length()>=12 && rfc.length()<=13 ?true:false;


	}

	private static void obtenerFolioFiscal(EncabezadoFacturaElectronica infoFE)
	{
		//El folio fiscal debe de conformarse por 8 caracteres
         StringBuilder folioFiscal=new StringBuilder("0000000");

         //1=Factura
         if(infoFE.getTipoDoc().equals(MDocType.DOCBASETYPE_ARInvoice))
         {
            folioFiscal.replace(0, 0, infoFE.getSerieDocFac());
            infoFE.setEtiquetaTipoDoc("FACTURA");
         }
         //2=Nota de credito
         else  if(infoFE.getTipoDoc().equals(MDocType.DOCBASETYPE_ARCreditMemo))
         {
            folioFiscal.replace(0, 0, infoFE.getSerieDocNC());
            infoFE.setEtiquetaTipoDoc("NOTA DE CREDITO");
         }
         //3=Nota de debito
         else  if(infoFE.getTipoDoc().equals(MDocType.DOCBASETYPE_ARDebitMemo))
         {
            folioFiscal.replace(0, 0, infoFE.getSerieDocND());
            infoFE.setEtiquetaTipoDoc("NOTA DE DEBITO");
         }

        folioFiscal.replace(folioFiscal.length()-infoFE.getNoFactura().trim().length(), folioFiscal.length(), infoFE.getNoFactura().trim());
        infoFE.setFolioFiscal(folioFiscal.toString());

	}

	/***
	 * metodo que obtiene los parametros de impresion de factura y de envio de la
	 * factura por email
	 *
	 * @param infoFE
	 */
	private static void obtenParamsImprimeFacturaYenviaEmail(EncabezadoFacturaElectronica infoFE)
	{
		//campo que almacena los datos que determinan si se imprime la factura o/y se envia por email
         String campoImprimeFacturaYenviaEmail="";

         //Si se imprime y no se manda por email. se envian dos valores
         //ejm 12. si se imprime y son dos copias.
         if(infoFE.getImprimeFactura().equals("Y") && infoFE.getEnviafacturaEmail().equals("N"))
        	 campoImprimeFacturaYenviaEmail="1"+ infoFE.getCopias();

         //si no se imprime y se envia por email se mandan 4 valores.
         //ejem 001coreo@algo.com no se imprime y se envia por email a correo@algo.com
         else if(infoFE.getImprimeFactura().equals("N") && infoFE.getEnviafacturaEmail().equals("Y"))
        	 campoImprimeFacturaYenviaEmail="001"+ infoFE.getEmail();

         //Si no se imprime y no se va a envi�a por email no se pone ningun valor.         
         else if(infoFE.getImprimeFactura().equals("N") && infoFE.getEnviafacturaEmail().equals("N"))
        	 campoImprimeFacturaYenviaEmail="00";
         
         
         //Si se imprime y se envia por email
         else if(infoFE.getImprimeFactura().equals("Y") && infoFE.getEnviafacturaEmail().equals("Y"))
        	 campoImprimeFacturaYenviaEmail="1"+ infoFE.getCopias()+"1"+infoFE.getEmail(); 

         infoFE.setParamsFE_ImprimeFactYEnviaMail(campoImprimeFacturaYenviaEmail);


	}


	/**Proceso que inicia la facturacion electronica
	* El parametro C_Invoice2_ID puede ser el ID de una factura de aseguradora,
	* o puede ser una nota de credito
	*/

	public static ActionErrors facturacionElectronica(Properties ctx, String trxName, int C_Invoice_ID,
			int C_Invoice2_ID, CLogger s_log, String funcionFacturacion, String seImprimeFactura,  String copiasAdicionales, 
			String seMandaXEmail, String email){

		/******************************INICIO DE FACTURACION ELECTRONICA***************************************/

		MEXMEConfigFE cfe = MEXMEConfigFE.get(ctx, trxName);
		ActionErrors erroresFE = new ActionErrors();

		if(cfe!=null ){
			if(cfe.isPrintFacturaElect()==true){
				s_log.log(Level.INFO,"****** Imprime Facturacion Electronica Directa ****** ");


				//Metodo que obtiene la impresora donde sera impresa la factura electronica
				String[] infoImpresora=MEXMEImpresora.getImpresoraFuncion(ctx, trxName,funcionFacturacion);

				//Valida que exista la IP y el nombre de la impresora para la funcion que se ejecuta
				if(infoImpresora!=null && infoImpresora[0]!=null && infoImpresora[1]!=null){
					//metodo que imprime la factura electronica Factura del Socio de Negocios
					if(C_Invoice_ID>0)
						erroresFE=MEXMEImpresora.ImpFacturaElectronica(infoImpresora, C_Invoice_ID, ctx,funcionFacturacion,seImprimeFactura, copiasAdicionales, seMandaXEmail, email);

					//metodo que imprime la factura electronica Factura del Particular
					if(C_Invoice2_ID>0)
						erroresFE=MEXMEImpresora.ImpFacturaElectronica(infoImpresora, C_Invoice2_ID, ctx,funcionFacturacion,seImprimeFactura, copiasAdicionales, seMandaXEmail, email);
				}else{ 
					erroresFE= new ActionErrors();
					erroresFE.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.facturaElectronica.impresoraNoValida"));
				}
			}

			/*
			if(erroresFE.isEmpty()){
				//if(true){
				MEXMEInvoice invoice1 = null;
				MEXMEInvoice invoice2 = null;
				if(C_Invoice_ID != 0){
					invoice1 = new MEXMEInvoice(ctx, C_Invoice_ID, null);
					invoice1.setIsPrinted(true);
					if(invoice1.getDocumentNoExt()==null || invoice1.getDocumentNoExt().trim().length() == 0)
						invoice1.setDocumentNoExt(invoice1.getDocumentNo());

					if(!invoice1.save()){
						erroresFE.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.facturaElectronica.ErrorNoExterno"));
					}
				}
				if(C_Invoice2_ID != 0){
					invoice2 = new MEXMEInvoice(ctx, C_Invoice2_ID, null);
					invoice2.setIsPrinted(true);
					if(invoice2.getDocumentNoExt()==null || invoice2.getDocumentNoExt().trim().length() == 0)
						invoice2.setDocumentNoExt(invoice2.getDocumentNo());

					if(!invoice2.save()){
						erroresFE.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.facturaElectronica.ErrorNoExterno"));
					}
				}
			}
		
		*/
		}else{
			erroresFE.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.facturaElectronica.impresoraNoValida"));
		}
		return erroresFE;

		/******************************FIN DE FACTURACION ELECTRONICA***************************************/
	}


    /***
     * Metodo que obtiene el c_invoice_id y el tipo de funcion
     *
     */
    public static ActionErrors obtenTipoFacturacionDocumento(Object C_Invoice_ID[], Object funcionFactura[], String noDocumento,
    		String tipoDoc, Properties ctx)
    {
    	  StringBuilder sql= new StringBuilder(
          		"select C_Invoice.C_Invoice_ID, dt.DocBaseType as tipoDocumento ,\n" +
    	  		"case when C_Invoice.exme_ctapacext_id is not null then 'FE' else 'FD' end as tipoFacturacion \n" +
    	  		"from c_invoice C_Invoice \n" +
    	  		"inner join c_doctype dt on(dt.c_doctype_id=C_Invoice.c_doctype_id)\n" +    	  		
    	  		"where C_Invoice.documentno=? and dt.docbasetype=?");


          sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx, sql.toString(), "C_Invoice"));
          ActionErrors error= new ActionErrors();

          PreparedStatement pstmt = null;
          ResultSet rs = null;
  		try {

  			pstmt = DB.prepareStatement(sql.toString(),null);
  			pstmt.setString(1,noDocumento);
  			pstmt.setString(2,tipoDoc);

  			rs = pstmt.executeQuery();

  			if(rs.next())
  			{

  				    C_Invoice_ID[0]=rs.getObject("C_Invoice_ID");
  				                                          
  				    if(rs.getString("tipoDocumento")!=null && (rs.getString("tipoDocumento").equals(MDocType.DOCBASETYPE_ARInvoice) || rs.getString("tipoDocumento").equals(MDocType.DOCBASETYPE_ARDebitMemo)))  				    
  				    	funcionFactura[0]= rs.getObject("tipoFacturacion");  				    
  				    else if(rs.getString("tipoDocumento")!=null && rs.getString("tipoDocumento").equals(MDocType.DOCBASETYPE_ARCreditMemo))
  				    {    if(rs.getString("tipoFacturacion").equals("FD"))
  				    	     funcionFactura[0]= FUNCION_FACTURACION_CANCELACION_FACTURA_DIRECTA;
  				    	else funcionFactura[0]= FUNCION_FACTURACION_CANCELACION_FACTURA_EXTENSION;
  				    }
  				    
  			}
  			else
  			{
  				error.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.facturaImp.noExisDoc"));
  			}

  		} catch (Exception e) {
  			s_log.log(Level.SEVERE, e.getMessage(), e);
  		} finally {
  			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
  		}

  	return error;

   }
    
    
    private static String formatearNumero(BigDecimal importe, int nDecimales)
    {
            // Preparo un objeto para realizar el formateo
            NumberFormat formateo = NumberFormat.getNumberInstance();
            formateo.setMaximumFractionDigits(nDecimales);
            formateo.setMinimumFractionDigits(nDecimales);
            return formateo.format(importe);
    }

    
    /***
     * 
     * @return el puerto que se ocupa en el servicio de compiere
     */
    
	public static int getPortServicioCompiere() {
		
		if(portServicioCompiere==0)
			portServicioCompiere=CConnection.get().getAppsPort()+1;
		
		return portServicioCompiere;
	}

	public static String getSrvAppMedsys() {
		
		if(srvAppMedsys==null)
			srvAppMedsys=CConnection.get().getAppsHost();
		
		return srvAppMedsys;
	}

	
	/***
	 * Envia el Spool de la facturacion electronica desde compiere al servidor de aplicaciones
	 * 
	 * @param C_Invoice_ID
	 * @param ctx
	 * @param seImprimeFactura
	 * @param copiasAdicionales
	 * @param seMandaXEmail
	 * @param email
	 * @param AD_Client_ID
	 * @param AD_Org_ID
	 * @param docBaseType
	 */
	public static boolean enviaCompiereSpoolFE(int C_Invoice_ID, Properties ctx,
			String seImprimeFactura,  String copiasAdicionales,	String seMandaXEmail, String email, int AD_Client_ID,
			int AD_Org_ID, String docBaseType )
	     {
			//Variable que guarda si se produce un error en la impresion de la factura
			boolean error=false;
			
			String infoFuncion[]={"",""};
			String funcionFactura="";
			
			if(docBaseType.equals("ARC") || docBaseType.equals("ARD"))
				funcionFactura=MEXMEImpresora.FUNCION_FACTURACION_CANCELACION_FACTURA_DIRECTA;
			else
				funcionFactura=MEXMEImpresora.FUNCION_FACTURACION_DIRECTA;
			
			DatosFacturaCompiereFE obj= new DatosFacturaCompiereFE();
			obj.setAD_Client_ID(AD_Client_ID);
			obj.setAD_Org_ID(AD_Org_ID);
			obj.setInfoFuncion(infoFuncion);
			obj.setC_Invoice_ID(C_Invoice_ID);
			obj.setCtx(ctx);
			obj.setFuncionFactura(funcionFactura);
			obj.setSeImprimeFactura(seImprimeFactura);
			obj.setCopiasAdicionales(copiasAdicionales);
			obj.setSeMandaXEmail(seMandaXEmail);
			obj.setEmail(email);
			
			SSLSocket s = null;		
			ObjectOutputStream out=null;
			OutputStream flujo=null;
			
			//Solo se inicia  una sola vez el servicioSSL
			if(servicioArribaSSL==false)
			   ImpresionSRVCompiereFE.iniciaSetups(false);//
						
			try 
			{
		   	   SSLSocketFactory sf = ImpresionSRVCompiereFE.sslContext.getSocketFactory();
		       s = (SSLSocket)sf.createSocket(MEXMEImpresora.getSrvAppMedsys(), MEXMEImpresora.getPortServicioCompiere());
		
			   flujo = s.getOutputStream();
			
			    	
			   out = new ObjectOutputStream(flujo);						
		       out.writeObject(obj);
						        
			    
			
			} catch (IOException e) {
				error=true;
				s_log.log(Level.SEVERE, e.getMessage(), e);
			}
			finally
			{
				
				if(out!=null)
				{
				  try {
					out.flush();
					out.close();
				} catch (IOException e) {
					s_log.log(Level.SEVERE, e.getMessage(), e);
					error=true;
				}
			      
				}
				
				if(flujo!=null)
					try {
						flujo.close();
					} catch (IOException e) {
						s_log.log(Level.SEVERE, e.getMessage(), e);
						error=true;
					}
				
				if(s!=null)
					try {
						s.close();
					} catch (IOException e) {
						s_log.log(Level.SEVERE, e.getMessage(), e);
						error=true;
					}
			    
			    out=null;
			    flujo=null;
			    s=null;
				
			}
	
			return error;
		}
	
	
	
	//	-----Armando--------------------------
    /**
	 *  Devolvemos una lista de objetos LabelValueBean con las impresoras
	 *  relacionadas una Organizaci&oacute;
	 *
	 */ 
	public static List<LabelValueBean> getImpresoras(Properties ctx, String trxName) throws Exception {
		
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
	    List<LabelValueBean> resultados = new ArrayList<LabelValueBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{ 
		    if(ctx == null)
	            return null;

				sql.append("SELECT  Exme_Impresora.Value,Exme_Impresora.Exme_Impresora_Id  ")
						.append(" FROM Exme_Impresora")
						.append(" WHERE  EXME_Impresora.IsActive = 'Y' AND  EXME_Impresora.Ad_Client_Id= ")	
						.append(Env.getAD_Client_ID(ctx))
						.append("AND EXME_Impresora.Ad_Org_Id=")
						.append(Env.getAD_Org_ID(ctx))
					    .append(" ORDER BY Exme_Impresora.Value  "); 

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				LabelValueBean resultado = new LabelValueBean(rs.getString("Value"),rs.getString("Exme_Impresora_Id"));
				resultados.add(resultado);
			
			}
		} catch (Exception e) { 
			s_log.log(Level.SEVERE, e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs,pstmt);
			pstmt = null;
			rs =null;
		}
		return resultados; 
	}
  
	/**
	 * Nos regresa las impresoras asociadas a la organizacion. La lista esta formada por un objeto KeyNamePair
	 * con los valores de "Exme_Impresora_Id" y "Name"
	 * @return List<KeyNamePair>
	 * @author mvrodriguez 
	 */
	public static List<KeyNamePair> getImpresorasIdName(Properties ctx, String trxName) throws Exception {

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<KeyNamePair> resultados = new ArrayList<KeyNamePair>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append("SELECT Name, Exme_Impresora_Id ");
			sql.append(" FROM Exme_Impresora");
			sql.append(" WHERE  IsActive = 'Y' ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));
			sql.append(" ORDER BY Value  ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultados.add(new KeyNamePair(rs.getInt("Exme_Impresora_Id"), rs.getString("Name")));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		return resultados;
	}
	

	/**
	 * Devolvemos una lista de objetos MEXMEImpresora
	 * @param ctx
	 * @param warehouseID
	 * @param trxName
	 * @return
	 */
	public static List<MEXMEImpresora> getFromWarehouse(Properties ctx, int warehouseID, String trxName) {
		List<MEXMEImpresora> retvalue = new ArrayList<MEXMEImpresora>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("SELECT  *  ").append(" FROM Exme_Impresora")
				.append(" INNER JOIN  Exme_impresoraalm ON (exme_impresora.exme_impresora_id = exme_impresoraalm.exme_impresora_id)")
				.append(" WHERE  EXME_Impresora.IsActive = 'Y'").append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
				.append(" AND exme_impresoraalm.m_warehouse_id = ?").append(" ORDER BY Exme_Impresora.Name  ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, warehouseID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retvalue.add(new MEXMEImpresora(ctx, rs, trxName));
			}

		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql.toString(), e);

		} finally {
			DB.close(rs, pstmt);
		}

		return retvalue;
	}

}