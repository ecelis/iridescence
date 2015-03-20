/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.compiere.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author Administrador
 */
public class MEXMEDosificacion {
	
	private final static Long segundoMS    =1000L;
	private final static Long minutoMS     =60*segundoMS;
	private final static Long horaMS       =60*minutoMS;
	private final static Long diaMS        =24*horaMS;
	
	
	/**
	 * @param args the command line arguments
	 *//*
	 public static void main(String[] args) {
	 MEXMEDosificacion main = new MEXMEDosificacion();
	 DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
	 //Establecer parametros para calcular tiempos y tiepos de aplicaciones
	  int intervalo = 12;
	  String intervaloUOM = "H";
	  int duracion = 1;
	  String duracionUOM = "D";
	  int cantidad = 2;
	  
	  try{            
	  Date dateIni = (Date)formatter.parse("200907101200");
	  Date dateFin = (Date)formatter.parse("200907101200");
	  dateFin = main.getCalcularFechaFinMedicamentos(dateFin,duracion,duracionUOM);
	  
	  Calendar fechaDate1 = Calendar.getInstance();
	  fechaDate1.setTime(dateIni);
	  Calendar fechaDate2 = Calendar.getInstance();
	  fechaDate2.setTime(dateFin);
	  
	  
	  String dosificacion = main.getDosificacion(intervalo, intervaloUOM, duracion,duracionUOM, fechaDate1, fechaDate2, cantidad);
	  
	  System.out.println("dosificacion"+dosificacion);
	  
	  }catch(Exception e){
	  System.out.println("error = "+e);
	  }
	  }*/
	
	/**
	 * Main metod to get the string of dosification
	 * Metodo principal de acceso para la obtencion de las dosis
	 */
	public static String getDosificacion(int intervalo, String intervaloUOM,
			int duracion, String duracionUOM,
			Calendar fechaIni, Calendar fechaFin, int cantidad) {
		
		StringBuilder segmentHL7 = new StringBuilder(200).append("^@VECES&@HORAS^^@F1^@F2^^^");
		
		Format formatter = new SimpleDateFormat("yyyyMMddHHmm");
		
		
		//intervalo
		Long cadaCuanto = getTimeinMillis(intervalo, intervaloUOM);
		Long duranteCuanto = getTimeinMillis(duracion, duracionUOM);
		
		//cuatas veces se debe dar el medicamento	
		int tantasVeces = (int)(duranteCuanto/cadaCuanto);
		
		/* cuantos dias tomara la medicacion
		 * si los dias son uno mismo entonces el resultado de la resta seria cero
		 * por eso se pone uno, para que la division entre cero no sea etre cero
		 */		
		int duranteTantosDias = (int)((fechaFin.getTimeInMillis()-fechaIni.getTimeInMillis())/diaMS);
		if (duranteTantosDias==0)
			duranteTantosDias=1;
		
		//recuperamos las horas a las que se aplicara la medicacion
		ArrayList<String> tiempoAplicar = cuandoAplicar(fechaIni,cadaCuanto,tantasVeces);
		//nuevo sb para 
		StringBuilder sb = new StringBuilder(100);
		
		/*
		 * Just one time of medication application
		 * Una sola aplicacion del medicamento
		 */
		if (tantasVeces  == 1){
			segmentHL7 =segmentHL7.replace(2,7, "ONCE");
			iteracionFechas(tiempoAplicar, segmentHL7, sb , formatter,fechaIni,fechaFin );
			
		}else
			/*
			 * Twice application per day of medication
			 * Dos veces de aplicacion del medicamento por dia
			 */
			if (tantasVeces/duranteTantosDias == 2){
				segmentHL7 =segmentHL7.replace(2,7, "BID");
				iteracionFechas(tiempoAplicar, segmentHL7, sb , formatter,fechaIni,fechaFin );
				
			}else
				/*
				 * Thre times per day for at least one day for the medication
				 * Tres veces de aplciacion por dia
				 */
				if (tantasVeces/duranteTantosDias  == 3){
					segmentHL7 =segmentHL7.replace(2,7, "TID");
					iteracionFechas(tiempoAplicar, segmentHL7, sb , formatter,fechaIni,fechaFin  ); 
				}else                
					/*
					 * Four times of application of medicament per day
					 * Cuatro veces de aplicacion del medicamento.
					 */
					if (tantasVeces/duranteTantosDias  == 4){
						iteracionFechas(tiempoAplicar, segmentHL7, sb , formatter,fechaIni,fechaFin  );
					}else          
						/*
						 * More than 5 time of application of medication per day
						 * Mas de 5 veces de aplicacion del mediamento por dia.
						 */
						if (tantasVeces/duranteTantosDias  > 4){
							segmentHL7 =segmentHL7.replace(2,7, +tantasVeces/duranteTantosDias+"ID");
							iteracionFechas(tiempoAplicar, segmentHL7, sb , formatter,fechaIni,fechaFin  );
						}
		return segmentHL7.toString();
	}    
	
	/**
	 * Method to iterate under the dates and the any other data that match with the current time format for an medical order
	 * Metodo para iterar las fechas (horas ) o cualquier otro dato que cumpla con los datos a remplazar en las ordenes medicas.
	 */
	private static void iteracionFechas(ArrayList<String> tiempoAplicar, StringBuilder segmentHL7, StringBuilder sb ,Format formatter,Calendar fechaIni,Calendar fechaFin ){
		for (int i=0;i<tiempoAplicar.size();i++){
			if (i<tiempoAplicar.size()-1)
				sb.append(tiempoAplicar.get(i)).append(",");
			else 
				sb.append(tiempoAplicar.get(i));
		}            
		segmentHL7.replace(9,14, sb.toString());
		segmentHL7.replace(17,19, formatter.format(fechaIni.getTime()));
		segmentHL7.replace(21,23, formatter.format(fechaFin.getTime()));
	}
	
	/**
	 * Methdo to return a list with gthe dates (times in HHmm format) for the application)
	 * Metodo que devuelve en una lista las horas en formato 1100 1120 1140 para iterar en iteracionFechas.
	 * @param fechaIni
	 * @param cadaCuanto
	 * @param tantasVeces
	 * @return
	 */
	private static ArrayList<String> cuandoAplicar(Calendar fechaIni, Long cadaCuanto,int tantasVeces){
		
		Long fecha = fechaIni.getTimeInMillis();
		ArrayList<String> veces = new ArrayList<String>();
		Format formatterHr = new SimpleDateFormat("HHmm");
		
		for (int i=0; i<tantasVeces;i++){
			veces.add(i,formatterHr.format(fecha+(i*cadaCuanto)));
		}
		return veces;
	}
	
	/**
	 * Get times depending on the match parameter
	 * M for minute
	 * H for Hour
	 * D for day
	 * W for week
	 * depending on this the time is calculated to return a time in seconds.
	 * @param time
	 * @param timeUOM
	 * @return
	 */
	private static Long getTimeinMillis(int cantidad, String timeUOM) {
		
		Long timeInSecs = 0L;
		
		if (timeUOM.equalsIgnoreCase("M")) {
			timeInSecs = cantidad * 60L*1000;
		} else if (timeUOM.equalsIgnoreCase("H")) {
			timeInSecs = cantidad * 60 * 60L*1000;
		} else if (timeUOM.equalsIgnoreCase("D")) {
			timeInSecs = cantidad * 24 * 60 * 60L*1000;
		} else if (timeUOM.equalsIgnoreCase("W")) {
			timeInSecs = cantidad * 7 * 24 * 60 * 60L*1000;
		}
		return timeInSecs;
	}
	
	/**
	 * Methdo to calculate the ending date from a few time and medical information depending on a recet.
	 * Metodo para calcular la fecha final de aplciacion dependiento de la informacion medica y de la receta.
	 * @param dateIni
	 * @param duracion
	 * @param duracionUOM
	 * @return
	 */
	public static Date getCalcularFechaFinMedicamentos(Date dateIni,int duracion,String duracionUOM){
		Date dateIniPlusDateFin = dateIni;
		Long plus = getTimeinMillis(duracion, duracionUOM);
		dateIniPlusDateFin.setTime(dateIniPlusDateFin.getTime()+plus);
		
		return dateIniPlusDateFin;
	}
	
}