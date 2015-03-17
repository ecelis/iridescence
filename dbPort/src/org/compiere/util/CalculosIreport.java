/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.util;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.MCurrency;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEEnfermeria;
import org.compiere.model.MEXMEPaciente;
import org.compiere.model.MEXMESignoVital;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MUser;

/**
 * Metodos utilizados en reportes
 * @author Omar Torres Atonal
 * 
 * Nota. Cualquier cambio en el metodo de calcular edad en AMD, 
 * se debe considerar la clase CalcularEdadAMD
 */
public class CalculosIreport {
    
   
    private static String almacenaValor=null;
    private static Properties _ctx = null;


   public static String getAlmacenaValor() {
        return almacenaValor;
    }

    public static boolean  setAlmacenaValor(String almacenaValor) {
        CalculosIreport .almacenaValor = almacenaValor;
        return false;
    }

    /**
     * INICIO DEL PROCEDIMIENTO  ObtenEdad
     * Recibe como par�metro la fecha de nacimiento del paciente.
     * Devuelve la edad en formato String
     */
    
    public synchronized static String obtenEdad(Date fecha)    {
        int edad=0;
        
        int restaFecha=0;
      
        SimpleDateFormat formato= new SimpleDateFormat("dd");
        int dia=Integer.parseInt(formato.format(fecha));
        formato= new SimpleDateFormat("MM");
        int mes=Integer.parseInt(formato.format(fecha));
        formato= new SimpleDateFormat("yyyy");
        int ano=Integer.parseInt(formato.format(fecha));
        
        fecha= Calendar.getInstance().getTime();
        
        formato=new SimpleDateFormat("dd/MM/yyyy");
        
        formato=new SimpleDateFormat("dd");
        int DIAS=Integer.parseInt(formato.format(fecha));        
        formato= new SimpleDateFormat("MM");
        int mesS=Integer.parseInt(formato.format(fecha));
        formato= new SimpleDateFormat("yyyy");
        int anoS=Integer.parseInt(formato.format(fecha));
        
        /*********************************************************/
        
        if(mes>mesS)
           restaFecha=1;    
        else if(mes==mesS && dia>DIAS)
             restaFecha=1;
        
        edad=(anoS-ano)-restaFecha;
           
       return String.valueOf(edad);
    }
   
    /**
     * Obtiene la edad en formato decimal AA.MM.DD
     * Calcula la edad a partir de la fecha de nacimiento hasta una fecha deterninada (Lama)
     * @param fechaNac, fechaCita
     * @return
     * @throws Exception
     */
    public synchronized static String getEdadCita(String fechaNac, String fechaCita) {
        
       String edad = null;
        
        if (fechaCita == null || fechaCita.length() < 7)
        	return "";
        
        try{

        	CalcularEdadAMD c_edad = CalcularEdadAMD.getEdadCita(get_ctx(), fechaNac, fechaCita);
        	
			//edad = c_edad.getAnios() + "." + c_edad.getMeses() + "." + c_edad.getDias();
        	edad = c_edad.getAgeSimple();
        		//c_edad.getAnios() + " " + Utilerias.getMessage(CalculosIreport.get_ctx(), null, "msj.edad.aa") + " " + c_edad.getMeses() + " " + Utilerias.getMessage(CalculosIreport.get_ctx(), null, "msj.edad.mm") + " " + c_edad.getDias() + " " + Utilerias.getMessage(CalculosIreport.get_ctx(), null, "msj.edad.dd");
       }
       catch (Exception e) {
    	   edad = "";
    }
       return edad;
    }
    
  
    /**
     * Obtiene la edad actual en formato decimal AA.MM.DD (Lama)
     * Calcula la edad a partir de la fecha de nacimiento hasta la fecha actual
     * @param fechaNac
     * @return
     * @throws Exception
     */
    public synchronized static String getEdad(String fechaNac){
    
    	String edad = null;
    	try {
    		String fechaActual = null;
        	SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy");
        	fechaActual = spd.format(new Date());
        	edad = getEdadCita(fechaNac,fechaActual);
			
		} catch (Exception e) {
			edad = "";
		}		
		return edad;
    } 
    /**
     * Convierte un double a cantidad con letras:
     * Compatibilidad con versiones anteriores a medsys 5.5
     * @param cant
     * @return
     */
    public synchronized static String getPrecioMexicoLetra(Double cant){
    	return getPrecioMexicoLetra(cant, null);
    }
    /**
     * Convierte un double a cantidad con letras
     * @param cant
     * @param loc Se agrego el parametro locale por error en el contexto para los reportes
     * @return
     */
    public synchronized static String getPrecioMexicoLetra(Double cant, Locale loc) {


    	String precioLetra = new BigDecimal(cant).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    	if(loc == null)
    		loc = Env.getLanguage(get_ctx()).getLocale();
    	//Language lag = new Language(loc.getDisplayLanguage(), Env.getContext( Env.getCtx(), "#AD_Language"), loc);
    	precioLetra = Msg.getAmtInWords(new Language(loc.getDisplayLanguage(), loc.toString(), loc), precioLetra);

    	//String idioma = loc.getLanguage();//Env.getContext(Env.getCtx(), "#AD_Language");           
    	if (loc.toString().equalsIgnoreCase("es_MX")) {
    		//Reutilizacion de codigo - Si la cantidad es igual a uno escribe PESO. raul
    		return Utilerias.precioMexico(precioLetra, cant.intValue() == 1);

    	} else
    		return precioLetra;
    }
    
	public static String getLetra(int cant) {
		String letras = n2t.convertirLetras(cant);
		return letras;
	}


	
	public static String getFechaCompleta(String fechaTxt, Locale loc, String limitador) {
		String letras = "";
		//Locale loc = Utilerias.getLocale(Env.getCtx());
        //Language lag = new Language(loc.getDisplayLanguage(), Env.getContext( Env.getCtx(), "#AD_Language"), loc);
		//DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, loc);
		
		try {
			SimpleDateFormat formato = new SimpleDateFormat("MMMMMMMMM", loc);
			Date fecha = Constantes.getSdfFecha().parse(fechaTxt);
		    String mesS = formato.format(fecha);
		Calendar c = Calendar.getInstance(loc);
		c.setTime(fecha);
		
		int dia= c.get(Calendar.DAY_OF_MONTH);
		 
		int ano = c.get(Calendar.YEAR);
		
		String diaT= n2t.convertirLetras(dia);
		 
		String anoT= n2t.convertirLetras(ano);
		
		letras = diaT+" "+limitador+" "+mesS+" "+limitador+" "+anoT;
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return letras;
	}
	
	public static Properties get_ctx() {
		if (_ctx == null) {
			DefaultContextProvider defaultContextProvider = new DefaultContextProvider();
			_ctx = defaultContextProvider.getContext();
		}
		return _ctx;
	}

    public static void set_ctx(Properties ctx) {
        CalculosIreport._ctx = ctx;
    }
    
    /**
     * Regresa el valor string de una lista
     * @param adRefID
     * @param idioma
     * @param value
     * @return
     */
    public static String getEstatusTxt(int adRefID, String idioma, String value) {
		return getValorReferencia(adRefID, idioma, value);
    }
    
    /**
     * Regresa el valor string de una lista
     * @param adRefID
     * @param idioma
     * @param value
     * @return
     */
    public static String getValorReferencia(int adRefID, String idioma, String value) {
		
		if (idioma == null)
			idioma = Env.getContext(get_ctx(), "#AD_Language");

		String ret = "";
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);

		// si es lenguaje base
		boolean isBaseLanguage = Env.isBaseLanguage(idioma, "AD_Ref_List");
		if (isBaseLanguage)
			sql.append("SELECT Name FROM AD_Ref_List ")
				.append("WHERE AD_Reference_ID=? AND Value=?" );
			else    	
			sql.append("SELECT t.Name FROM AD_Ref_List_Trl t ")
			   .append("INNER JOIN AD_Ref_List r ON (r.AD_Ref_List_ID=t.AD_Ref_List_ID) ")
			   .append("WHERE r.AD_Reference_ID=? AND r.Value=? AND upper(t.AD_Language)=? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, adRefID);
			pstmt.setString(2, value);
			if (!isBaseLanguage)
				pstmt.setString(3, idioma.toUpperCase());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ret = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;

	}
    
    public static String getValorReferencia(int adRefID,String value) {
    	return getValorReferencia(adRefID, null, value)  ;
    }
 
	public static long getDiferenciaDias(Timestamp fechaInicio, Timestamp fechaFin) {
		
		 Calendar calendar1 = Calendar.getInstance();
		 Calendar calendar2 = Calendar.getInstance();
		 calendar1.setTime(fechaInicio);
		 if(fechaFin!=null){
			 calendar2.setTime(fechaFin);
		 }
		 long milliseconds1 = calendar1.getTimeInMillis();
		 long milliseconds2 = calendar2.getTimeInMillis();
		 long diff = milliseconds2 - milliseconds1;
		 long diffDays = diff / (24 * 60 * 60 * 1000);
		    
		 return diffDays;
	 	
	 }
	
	/**
     * Obtiene la edad en formato de String
     * Calcula la edad a partir de la fecha de nacimiento hasta una fecha determinada
     * @param fechaNac, fechaActual
     * @return
     * @throws Exception
     */
    public synchronized static String getEdadCitaString(String fechaNac, String fechaActual) {
        
        String edad = null;
        
        if (fechaActual == null || fechaActual.length() < 7)
        	return "";
        
        try{

        	CalcularEdadAMD c_edad = CalcularEdadAMD.getEdadCita(get_ctx(), fechaNac, fechaActual);
        	
			edad = c_edad.getEdadAMD();
       }
       catch (Exception e) {
    	   edad = "";
    }
       return edad;
    }
    
    public static String getMes(String mes){
    	
    	String retValue = null;
    	
    	SimpleDateFormat fml = new SimpleDateFormat("MMMMM");
		SimpleDateFormat fmn = new SimpleDateFormat("MM");
		try {
			if (mes.length() == 1) {
				mes = "0" + mes;
			}
			retValue = fml.format(fmn.parse(mes)).toUpperCase();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return retValue;
    }
    
    /**
     * Metodo para obtener grado de un paciente
     * @param EXME_Paciente_ID
     * @return
     *
	public static String getGradoPaciente(int EXME_Paciente_ID) {
		String grado = null;
		grado = MEXMEPaciente.getGrado(get_ctx(), false, true, EXME_Paciente_ID, null);
		return grado;
	}*/
	
	/**
     * Metodo para obtener grado de un paciente
     * @param EXME_Paciente_ID
     * @return
     *
	public static String getGradoPaciente(int EXME_Paciente_ID, boolean tipoPac) {
		String grado = null;
		grado = MEXMEPaciente.getGrado(get_ctx(), false, tipoPac, EXME_Paciente_ID, null);
		return grado;
	}*/
    /**
     * Metodo para Obtener el saldo de un paciente
     * @param EXME_CtaPac_ID
     * @return BigDecimal saldo
     */ 
	public static BigDecimal getSaldo(int EXME_CtaPac_ID){			
		MEXMECtaPac ctaPac = new MEXMECtaPac(get_ctx(), EXME_CtaPac_ID, null);
		Double saldo = MEXMEPaciente.getPatientBalance(get_ctx(), ctaPac.getEXME_Paciente_ID(), EXME_CtaPac_ID, null);
		return new BigDecimal(saldo);
	}
	
	 /**
     * Metodo para obtener la unidad de medida de su conversion (ID)
     * @param uomID
     * @return toUomID
     */ 
	public static int getToUomID(int uomID){			
		
		return  MUOMConversion.getCUOMToID(get_ctx(), uomID, MUser.getSistMedicionUsuario(get_ctx()));
	
	}
	
	 /**
     * Metodo para obtener la unidad de medida de su conversion (nombre)
     * @param uomID
     * @return toUomID
     */ 
	public static String getToUomName(int uomID){			
		
		String name="";
		int uomtoID = MUOMConversion.getCUOMToID(get_ctx(), uomID, MUser.getSistMedicionUsuario(get_ctx()));
		MUOM uom = new MUOM(get_ctx(), uomtoID, null);
		name = uom.getName();
		
		return name;
	}
	
	/**
     * Metodo para obtener la conversi�n de un valor
     * @param uomID
     * @return toUomID
     */ 
	public static BigDecimal getValorConv(BigDecimal valor, int uomID){			
		
		BigDecimal valorConv = new BigDecimal("0");
		
		if(MUser.convertirUnidades(get_ctx()) && MEXMESignoVital.convertirUnidades(get_ctx(), uomID)){
			valorConv = MUOM.convertirMedida(get_ctx(), uomID, valor, MUser.getSistMedicionUsuario(get_ctx()));
		}else{
			valorConv = valor;
		}
		
		return valorConv;
	}
	
	public static String getLetras(int cant, Locale language) {
		  String letras = null;
		  if ("es_MX".equalsIgnoreCase(language.toString())) {
		   letras = n2t.convertirLetras(cant);
		  } else {
		   letras = n2t_EN.convert(cant);
		  }
		  return letras;
		 }
	
	/**
	 * User's name.<br>
	 * If the user is a doctor then returns Doctor's name,<br>
	 * in case the user is a nurse then it will be the nurse's name
	 * @param ctx
	 * @param userId
	 * @return
	 */
	public static String getUserName(int userID){
		return MUser.getUserName(get_ctx(), userID);
	}
	/**
	 * Regresa el nombre de la enfermera 
	 */
	public static String getNurseName(int nurseID){
		return MEXMEEnfermeria.getFullName(get_ctx(), nurseID, null);
	}
	
	/**
	 * Obtenemos la traduccion de la unidad de medida, a partir de su ID y el idioma de contexto.
	 * En caso de no tener traduccion, retorna el valor de la columna Name en C_UOM
	 * @param uomID
	 * @return UOM Translated
	 */
	public static String getTranslatedUOM(int uomID){
		final String idioma = Env.getContext(get_ctx(), "#AD_Language");
		return MUOM.getTranslatedUOM(uomID, idioma);		
	}
	
	
	/**
	 * Metodo para obtener la cantidad en letra 
	 **/
	public synchronized static String getpriceletter(Double cant, Integer currencyID ) {
    	String precioLetra = new BigDecimal(cant).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    	Locale loc = Env.getLanguage(get_ctx()).getLocale();
    	precioLetra = Msg.getAmtInWords(new Language(loc.getDisplayLanguage(), loc.toString(), loc), precioLetra);
    	MCurrency currency = new MCurrency(Env.getCtx(), currencyID, null);
    	if (currency != null) {
    		precioLetra += " " + currency.getISO_Code();
		}
    	
    	return precioLetra;
    }
	
	/**
	 * Metodo para obtener la cantidad en letra evaluando la moneda
	 * 
	 * @param cant
	 *            la cantidad a desplegar
	 * @param currencyID
	 *            el Tipo de moneda del pago
	 * @author gderreza
	 **/
	public synchronized static String getPrecioLetra(Double cant,
			Integer currencyID) {
		if (currencyID == Env.getC_Currency_ID(Env.getCtx())) {
			return getPrecioMexicoLetra(cant);
		} else {
			String priceLetter = getpriceletter(cant, currencyID);
			if (100 == currencyID) {
				String str = "";
				
				if(cant.doubleValue() >=1d && cant.doubleValue() <2d){
					str = Utilerias.getAppMsg(Env.getCtx(),"msj.Dolar");
				}else{
					str = Utilerias.getAppMsg(Env.getCtx(),"msj.Dolares");
				}
				
				String firstPart = StringUtils.substringBefore(priceLetter, "/");
				
				StringBuilder strBuilder = new StringBuilder(StringUtils.substringBeforeLast(firstPart, Constantes.SPACE));
				strBuilder.append(Constantes.SPACE).append(str).append(Constantes.SPACE);
				strBuilder.append(StringUtils.substringAfterLast(firstPart, Constantes.SPACE));
				strBuilder.append("/");
				strBuilder.append(StringUtils.substringAfter(priceLetter, "/"));

				priceLetter = strBuilder.toString();
			}
			return priceLetter;
		}
	}
	
}