package org.compiere.util;

import java.util.Properties;

import org.compiere.model.MEXMEPaciente;
import org.compiere.util.dwr.IngresoPacienteAjax;


public class CalculoCURPyRFC {
	
	
	public CalculoCURPyRFC() {
		super();
		
	}
	
	private String apellidoPaterno = "";
    private String apellidoMaterno = "";
    private String nombre = "";
    private String nombre2 = "";
    private String segundoNombre = "";
    private String curp =  "";
    private int pacienteID = 0;
    private String fechaNac = "";
    private String[] nombreNoValido = {"maria ", "mar\u00ECa ", "mar\u00EDa ", "mar\u00EEa ", "mar\u00EFa ", "ma. ", "ma ", "jose ", "jos\u00E8 ", "jos\u00E9 ", "jos\u00EA ", "jos\u00EB ", "j. ", "j "};
    private String sexo = "";
    private String estadoNac="";
    private String mensajeHistorias = "";
    private String rfcResp = "";
    private String nombreFam = "";
    private String apellidoMaternoFam = "";
    private String apellidoPaternoFam = "";
    private String rfc = "";
    Properties ctx = null;
    
    
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public int getPacienteID() {
		return pacienteID;
	}
	public void setPacienteID(int pacienteID) {
		this.pacienteID = pacienteID;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public String getEstadoNac() {
		return estadoNac;
	}
	public void setEstadoNac(String estadoNac) {
		this.estadoNac = estadoNac;
	}
	/*
	 * Fecha de Nacimiento en el orden 
	 * a�o, mes y dia.
	 */
	public void setFechaNac(String fechaNac) {
		
		String subcadena ="";
		
		if(fechaNac!=null && fechaNac!="" && fechaNac.length()==10){
			subcadena  = fechaNac.substring(8,10);
			subcadena += fechaNac.substring(3,5);
			subcadena += fechaNac.substring(0,2);
		}
		
		this.fechaNac = subcadena;

	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}	
	public String getMensajeHistorias() {
		return mensajeHistorias;
	}
	public void setMensajeHistorias(String mensajeHistorias) {
		this.mensajeHistorias = mensajeHistorias;
	}	
	public String getEstado() {
		return estadoNac;
	}
	public void setEstado(String estado) {
		this.estadoNac = estado;
	}
	public String getRfcResp() {
		return rfcResp;
	}
	public void setRfcResp(String rfcResp) {
		this.rfcResp = rfcResp;
	}	
	public String getNombreFam() {
		return nombreFam;
	}
	public void setNombreFam(String nombreFam) {
		this.nombreFam = nombreFam;
	}
	public String getApellidoMaternoFam() {
		return apellidoMaternoFam;
	}
	public void setApellidoMaternoFam(String apellidoMaternoFam) {
		this.apellidoMaternoFam = apellidoMaternoFam;
	}
	public String getApellidoPaternoFam() {
		return apellidoPaternoFam;
	}
	public void setApellidoPaternoFam(String apellidoPaternoFam) {
		this.apellidoPaternoFam = apellidoPaternoFam;
	}	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}	
	public Properties getCtx() {
		return ctx;
	}
	public void setCtx(Properties ctx) {
		this.ctx = ctx;
	}
	
	public void createRfcAndCurp(){
		String subRfcAndCurp = genericoRfcAndCurp();
		createRFC(subRfcAndCurp);
		createCURP(subRfcAndCurp);
		
		if(apellidoMaterno != "" && apellidoPaterno != "" && nombre != "" && fechaNac != ""){
			IngresoPacienteAjax validacionHistorias = new IngresoPacienteAjax();
			validacionHistorias.setCtx(ctx);
			mensajeHistorias= validacionHistorias.getPacientes(ctx, nombre, nombre2, apellidoPaterno, apellidoMaterno, curp, pacienteID * 1, fechaNac);
		}
		
	}
	
	
	 /* Noelia: Metodo que asigna las coincidencias del 
     * curp y rfc. 'Primeras 10 posiciones'
     */
    public String genericoRfcAndCurp(){
    
	    String rfcAndCurp = "";
	   
	    deleteComps();
	      
	    //1 Letra inicial del primer apellido
	    rfcAndCurp += letraIni(apellidoPaterno);
	    
	    //2 La primer vocal del apellido paterno .-Lama
	    rfcAndCurp += firstVocal(apellidoPaterno);  
	    
	    //3 Letra incial del segundo apellido
	    if(apellidoMaterno.length()==0){
	    	rfcAndCurp += "X";	    	
	    }else{
	    	rfcAndCurp += letraIni(apellidoMaterno);
	    }
	    
	 	//4 La primera letra del nombre       
	 	rfcAndCurp += letraIniName();	
	 	
	 	if(rfcAndCurp.length()==4){
	 		rfcAndCurp = deleteAltisonante(remplazar(rfcAndCurp));    		
	 	}
	 	
	 	rfcAndCurp += fechaNac;
	 	
	 	return rfcAndCurp;	 	
    }
    
    
    
    public void deleteComps(){
    	
    	String[] comp = {" DE LOS ", " DE LA ", " DE LAS ", " DE EL "};
    	apellidoPaterno = deleteComp(apellidoPaterno);
	    apellidoMaterno = deleteComp(apellidoMaterno);
	 	nombre = deleteComp(nombre);
		
	 	
	 	if(nombre!=null){
	 		
	 		//Eliminanos los componentes internos
	 		for(int i=0; i<comp.length; i++){
				if(nombre.toUpperCase().indexOf(comp[i])>0){
					nombre = nombre.substring(0,nombre.indexOf(comp[i])) + nombre.substring(nombre.indexOf(comp[i])+comp[i].length()-1,nombre.length());
					break;
				}
			}
	 		
	 		//Si el primer nombre es compuesto, eliminamos del primer nombre, el nombre no valido.
	 		nombre = nombre.trim();
	 		nombre = nombre.toLowerCase();
			for(int i=0; i<nombreNoValido.length; i++){
				if(nombre.startsWith(nombreNoValido[i]) && !(nombre.equals(nombreNoValido[i]))){
					nombre = nombre.substring(nombreNoValido[i].length(), nombre.length());
					nombre = nombre.trim();
					break;
				}
			}
			
			if(nombre2!=null){
		 		if(nombre2!=null){
			    	segundoNombre = deleteComp(nombre2);
			    	for(int i=0; i<comp.length; i++){
						if(segundoNombre.toUpperCase().indexOf(comp[i])>0){
							segundoNombre = segundoNombre.substring(0,segundoNombre.indexOf(comp[i])) + segundoNombre.substring(segundoNombre.indexOf(comp[i])+comp[i].length()-1,segundoNombre.length());
							break;
						}
					}
			    }
		 	}
	 		
	 	}
		
	    	
    }
    
    /* Cuando alguno de los apellidos o nombre es compuesto
	 *  y la primera palabra es una preposici�n, conjunci�n o contracci�n,
	 * se elimina.
	 */
	public String deleteComp(String propiedad){
	
		String temp = "";
		
		if(propiedad!=null){
			temp = propiedad.trim();
		}
				
		String[] comp = {"DA ", "DEL ", "DE LAS ", "DE LA ", "DE LOS ", "DE ", "DI ", "DD ", "EL ", "LA ", 
							 "LE ", "MC ", "DAS ", "DIE ", "LOS ", "LES ", "LAS ", "MAC ", "VAN ", "VON "};		
		 
		for(int i=0; i<comp.length; i++){
			if(temp.toUpperCase().startsWith(comp[i])){
				String variable = comp[i];
				temp = temp.substring(variable.length(), temp.length());									
				break;
			}
		}	
		
		return temp;
	}
	
	
	//Obtenemos La letra inicial
	public String letraIni(String temp){
		String subcadena = "";
	    if(temp!=null && temp!="" && temp.length()>1){
			subcadena = temp.substring(0,1);
			if(subcadena.equals("/") || subcadena.equals("-")  || subcadena.equals("."))
				subcadena="X";
		}	    
		return subcadena;
	}
	
	
	//La primera vocal interna 
	public String firstVocal(String temp){
		String subcadena = "X";
		temp = temp.toLowerCase();
		if(temp!=null && temp!="" && temp.length()>1){
			String[] vocales={"a", "e", "i", "o", "u",
					"\u00E0", "\u00E8", "\u00EC", "\u00F2", "\u00F9",
					"\u00E1", "\u00E9", "\u00ED", "\u00F3", "\u00FA",
					"\u00E2", "\u00EA", "\u00EE", "\u00F4", "\u00FB",
					"\u00E3", "\u00F5",
					"\u00E4", "\u00EB", "\u00EF", "\u00F6", "\u00FC"};
				
			String resto = temp.substring(1);
			boolean seguir=true;
			for(int j=0; j<resto.length()&& seguir; j++){	
				for(int i=0; i<vocales.length; i++){
					if(resto.substring(j,j+1).equals(vocales[i])){
						subcadena=vocales[i];
						seguir=false;
					}else if((resto.substring(j,j+1)).equals("-") || (resto.substring(j,j+1)).equals("/") || (resto.substring(j,j+1)).equals(".")){
						subcadena ="X";
						seguir=false;
					}
				}
			}	
		}
		return subcadena;
	}	
	
	
	/* Obtenemos la primera letra del nombre
	 * siempre y cuando no sea Mar�a o Jos�,
	 * en cuyo caso se utilizar�a el segundo nombre.
	 */
	public String letraIniName(){
		
		String subcadena = "";
		
	    if(nombre!=null && nombre!="" && nombre.length()>0){
	    	if(!isNameValid(nombre)){
	    		if(nombre2 !=null && nombre2 != ""){
					subcadena = letraIni(nombre2);
				} else {
					subcadena = letraIni(nombre);
				}
			} else {
				subcadena = letraIni(nombre);
			}
	    }
	    
	    return subcadena;
	    
	}
	
	
	public boolean isNameValid(String nombre1){
		
		boolean valido = true;
		nombre1 = nombre1.toLowerCase();
		
		for(int i=0; i< nombreNoValido.length; i++){
			if((nombre1.trim()).equals(nombreNoValido[i].trim())){
				valido = false;
				break;
			}			 
		}		
		return valido;	
	}
	
	
	 public String remplazar(String cadena){
	    	
	    	cadena = cadena.toLowerCase();
	    	
	    	String patron = "/\u00E0/gi";
	     	cadena = cadena.replace(patron, "a");
	     	patron = "/\u00E1/gi";
	     	cadena = cadena.replace(patron, "a");
	     	patron = "/\u00E2/gi";
	     	cadena = cadena.replace(patron, "a");
	     	patron = "/\u00E3/gi";
	     	cadena = cadena.replace(patron, "a");
	     	patron = "/\u00E4/gi";
	     	cadena = cadena.replace(patron, "a");
	     	patron = "/\u00E5/gi";
	     	cadena = cadena.replace(patron, "a");
	     	
	     	patron = "/\u00E8/gi";
	     	cadena = cadena.replace(patron, "e");
	     	patron = "/\u00E9/gi";
	     	cadena = cadena.replace(patron, "e");
	     	patron = "/\u00EA/gi";
	     	cadena = cadena.replace(patron, "e");
	     	patron = "/\u00EB/gi";
	     	cadena = cadena.replace(patron, "e");
	     	
	     	patron = "/\u00EC/gi";
	     	cadena = cadena.replace(patron, "i");
	     	patron = "/\u00ED/gi";
	     	cadena = cadena.replace(patron, "i");
	     	patron = "/\u00EE/gi";
	     	cadena = cadena.replace(patron, "i");
	     	patron = "/\u00EF/gi";
	     	cadena = cadena.replace(patron, "i");
	     	
	    	patron = "/\u00F2/gi";
	     	cadena = cadena.replace(patron, "o");
	     	patron = "/\u00F3/gi";
	     	cadena = cadena.replace(patron, "o");
	     	patron = "/\u00F4/gi";
	     	cadena = cadena.replace(patron, "o");
	     	patron = "/\u00F5/gi";
	     	cadena = cadena.replace(patron, "o");
	     	patron = "/\u00F6/gi";
	     	cadena = cadena.replace(patron, "o");
	     	     	
	     	patron = "/\u00F9/gi";
	     	cadena = cadena.replace(patron, "u");
	     	patron = "/\u00FA/gi";
	     	cadena = cadena.replace(patron, "u");
	     	patron = "/\u00FB/gi";
	     	cadena = cadena.replace(patron, "u");
	     	patron = "/\u00FC/gi";
	     	cadena = cadena.replace(patron, "u");
	     	   	
	    	patron = "/\u00F1/gi";
	    	cadena = cadena.replace(patron, "x");
	    	
	    	return cadena;
	    }
	 
	 
	 public String deleteAltisonante(String subCurpAndRfc){
			
		String[] notValid = {"BACA", "BAKA", "BUEI", "BUEY", "CACA", "CACO", "CAGA", "CAGO", "CAKA",
								  "CAKO", "COGE", "COGI", "COJA", "COJE", "COJI", "COJO", "COLA", "CULO", 
								  "FALO", "FETO", "GETA", "GUEI", "GUEY", "JETA", "JOTO", "KACA", "KACO", 
								  "KAGA", "KAGO", "KAKA", "KAKO", "KOGE", "KOGI", "KOJA", "KOJE", "KOJI", 
								  "KOJO", "KOLA", "KULO", "LILO", "LOCA", "LOCO", "LOKA", "LOKO", "MAME", 
								  "MAMO", "MEAR", "MEAS", "MEON", "MIAR", "MION", "MOCO", "MOKO", "MULA", 
								  "MULO", "NACA", "NACO", "PEDA", "PEDO", "PENE", "PIPI", "PITO", "POPO", 
								  "PUTA", "PUTO", "QULO", "RATA", "ROBA", "ROBE", "ROBO", "RUIN", "SENO", 
								  "TETA", "VACA", "VAGA", "VAGO", "VAKA", "VUEI", "VUEY", "WUEI", "WUEY"};
			
		for (int i=0; i<notValid.length; i++){
			if(subCurpAndRfc.toUpperCase().equals(notValid[i])){
				subCurpAndRfc =  subCurpAndRfc.substring(0,1) + "X" + subCurpAndRfc.substring(2,subCurpAndRfc.length());
				break;
			}
		}
			
		return subCurpAndRfc;
	}	
	 
	 public void createRFC(String subRFC){
		 rfc = remplazar(subRFC);
		
		 //asignamos el rfc
		 rfc = rfc.toUpperCase();
	 }
	 
	 public void createCURP(String subCurp){
			
		curp = subCurp;
		
		//11 Sexo M para mujer y H para hombre
		if(MEXMEPaciente.SEXO_Female.equalsIgnoreCase(sexo)){
			curp += "M";
		}
		if(MEXMEPaciente.SEXO_Male.equalsIgnoreCase(sexo)){
			curp += "H";
		}

	    //12-13 Letra Inicial y última Consonante del Estado de Nacimiento 
	    curp += estadoNac();
	    
	    //14 Primera consonante interna del primer apellido
	    if(apellidoPaterno!=null && apellidoPaterno.trim()!=""){
	    	curp += firstConsonante(apellidoPaterno);
	    }
	    
	    //15 Primera consonante interna del segundo apellido
    	if(apellidoMaterno!=null && apellidoMaterno.trim()!=""){
    		curp += firstConsonante(apellidoMaterno);    		
    	}else{
    		curp += "X";
    	}
    	
    	//16 Primera consonante interna del nombre
    	if(isNameValid(nombre)&& nombre!=null && nombre.trim()!=""){
    		curp += firstConsonante(nombre);    		 
    	}else if(segundoNombre!=null && segundoNombre.trim()!=""){    	
    			curp += firstConsonante(segundoNombre);    		 
    	}
	    
	    //Remplazamos signos raros.	   
	    curp = remplazar(curp);	    
	    
	    //asignamos el curp
	    if(curp!=null){
	    	curp=curp.toUpperCase();
	    }
	   		
	}
	 
	 
	 /* Noelia: Obtenemos la letra inicial y 
	 * ultima consonante del Estado de Nacimiento
     */	
    public String estadoNac(){
    	String subcadena = "";
    	if(estadoNac == null || estadoNac.length()==0)//Lama
    		return subcadena;
    	//agregamos la letra inicial del estado
    	subcadena += estadoNac.substring(0,1);
    	
    	//eliminamos la letra inicial
		estadoNac = estadoNac.substring(1).toLowerCase();	
		
    	/* Si el nombre del estado es compuesto, tomamos la inicial del ultimo componente.
		 * En caso contrario tomamos la �ltima consonante interna.
		 */		
		if(estadoNac.lastIndexOf(" ")>0){			
			subcadena += estadoNac.substring(estadoNac.lastIndexOf(" ")+1,estadoNac.lastIndexOf(" ")+2);
		}else{
			if(estadoNac.equals("ampeche")){
				subcadena += "C";
			}else{ 
				for(int i = (estadoNac.length()-1); i>=0; i--){		
					if(isConsonante(String.valueOf(estadoNac.charAt(i)))){
						subcadena += estadoNac.charAt(i);
						break;
					}
				}				
			}
		}		
    	return subcadena;
    
    }
    
    
    /**
     * Metodo para obtener la primer consonante interna 
     * 
     * @param String palabra. 
     * @return String primer consonate interna.
     */
	public String firstConsonante(String palabra){
		
		/* Cuando no existe consonante interna,
		 * el sistema asignara una X
		 */ 
		String clave = "X";
				
		//eliminamos la letra inicial
		palabra = palabra.substring(1).toLowerCase();
	
		for(int i=0; i<palabra.length(); i++){
			if(isConsonante(String.valueOf(palabra.charAt(i)))){
				clave = String.valueOf(palabra.charAt(i));
				break;
			}
		}
		
		return clave;	
	}
	
	public boolean isConsonante(String caracter) {
		
		caracter = remplazar(caracter);
    	String RegExPattern = "/[a-z]$/";
    	String noVocal = "/[aeiou]$/";
	  
	    if (caracter.equals(RegExPattern)&& !caracter.equals(noVocal)) {
	        return true;
	    } else {
	        return false;
	    } 
	}
	
	
	public void createOnlyCURP(){    	
    	String subRfcAndCurp = genericoRfcAndCurp();
    	createCURP(subRfcAndCurp);
    }
	
	public void createOnlyRFC(){    	
    	String subRfcAndCurp = genericoRfcAndCurp();
    	createRFC(subRfcAndCurp);
    }
	
	
	public void createRFCResp(){
		
		deleteCompsFam();
	   	
	    //1 Letra inicial del primer apellido
	    rfcResp += letraIni(apellidoPaternoFam);
	    
	  	//2 La primer vocal del apellido paterno .-Lama
	    rfcResp += firstVocal(apellidoPaternoFam);	   
	   
	    //3 Letra incial del segundo apellido
	    if(apellidoPaternoFam.length()==0){
	    	rfcResp += "X";
	    }else{
	    	rfcResp += letraIni(apellidoMaternoFam);	
	    } 
	    
	    //4 Letra incial del nombre
	    rfcResp += letraIni(nombreFam);
	 	
	 	if(rfcResp.length()==4){
	 		rfcResp = deleteAltisonante(remplazar(rfcResp));    		
	 	}		

		//Asinamos rfc
	 	rfcResp = remplazar(rfcResp);
	 	rfcResp = rfcResp.toUpperCase();
		
    }
	
	
	 public void deleteCompsFam(){
	    	
	    	String[] comp = {" DE LOS ", " DE LA ", " DE LAS ", " DE EL "};
	    	apellidoPaternoFam = deleteComp(apellidoPaternoFam);
	    	apellidoMaternoFam = deleteComp(apellidoMaternoFam);
	    	nombreFam = deleteComp(nombreFam);
			
		 	
		 	if(nombreFam!=null){
		 		
		 		//Eliminanos los componentes internos
		 		for(int i=0; i<comp.length; i++){
					if(nombreFam.toUpperCase().indexOf(comp[i])>0){
						nombreFam = nombreFam.substring(0,nombreFam.indexOf(comp[i])) + nombreFam.substring(nombreFam.indexOf(comp[i])+comp[i].length()-1,nombreFam.length());
						break;
					}
				}
		 		
		 		//Si el primer nombre es compuesto, eliminamos del primer nombre, el nombre no valido.
		 		nombreFam = nombreFam.trim();
		 		nombreFam = nombreFam.toLowerCase();
				for(int i=0; i<nombreNoValido.length; i++){
					if(nombreFam.startsWith(nombreNoValido[i]) && !(nombreFam.equals(nombreNoValido[i]))){
						nombreFam = nombreFam.substring(nombreNoValido[i].length(), nombreFam.length());
						nombreFam = nombreFam.trim();
						break;
					}
				}
		 		
		 	}
			
		    	
	    }

		
  }
    
	
    
    

