package org.compiere.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MConfigEC;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMEMedico;
import org.compiere.model.MEXMETratamientos;
import org.compiere.model.MEXMETratamientosDetalle;


/**
 * Programador que agenda una o mas citas segun los espacios disponibles Fecha: 2009/10/05 08:38:04.
 * 
 * @author Julio Gutierrez
 * @version Revision: 1.0
 */
public class Programador {

	/**
	 * Metodo que recibe una cita y la agenta en la fecha tentativa o en espacios vacios.
	 * 
	 * @author Julio Gutierrez
	 * @return Regresa la cita agendada (ID incluido)
	 */
	protected static CLogger s_log = CLogger.getCLogger(Programador.class);
	
	/**
	 * Proceso de agendar
	 * @param ctx
	 * @param cmoriginal
	 * @param horariomedico
	 * @param trxName
	 * @return
	 */
	public static MEXMECitaMedica agendar(Properties ctx, final MEXMECitaMedica cmoriginal, 
			final HashMap<String, String> horariomedico, String trxName) {
		
		MEXMECitaMedica citamedica = cmoriginal;
		final int asistente = citamedica.getEXME_Asistente_ID();
		boolean guardada = false;
		MConfigEC configEC = MConfigEC.get(ctx, null); //LRHU. Para obtener la duracion minima configurada
		
		// Valida que tenga una fecha tentativa, especialidad y asistente.
		if (citamedica.getFechaHrCita() == null 
				|| citamedica.getEXME_Especialidad_ID() == 0 
				|| citamedica.getEXME_Asistente_ID() == 0
				|| citamedica.getEXME_Medico_ID() == 0) 
			return null;
		
		// Saca las fechas de inicio y fin de cita tentativas.
		Date fechaini = null;
		Date fechaActual = DB.getDateForOrg(ctx);
		if(citamedica.getFechaHrIni() != null)
			fechaini = new Date(citamedica.getFechaHrIni().getTime());								
		else
			fechaini = new Date(citamedica.getFechaHrCita().getTime());
		
		// ubicamos la fecha 
		if(fechaini.before(fechaActual))
			fechaini = fechaActual;
		
		// Duracion
		Calendar calini = Calendar.getInstance();
		Calendar calfin = Calendar.getInstance();
		calini.setTime(fechaini);
		calfin.setTime(fechaini);
		
		// Duracion de la cita
		int duracion = citamedica.getDuracion();
		//LRHU. La duraci�n m�nima configurada.
		int duracionMin = configEC.getDurationMin();
		
		//LRHU. Comparamos tambien contra la duracion minima configurada
		if (duracion == 0 || duracion < duracionMin) { 
			duracion = duracionMin; //LRHU. asignamos la duracion minima
			citamedica.setDuracion(duracion);
		}
		calfin.add(Calendar.MINUTE, duracion);
		String horaini = getHoraString(calini);
		//String horafin = getHoraString(calfin);
		
		do {

			// Obtenemos los bloques de horas disponibles.
			HashMap<String, List<String>> horariodisponible = 
				getHorasdisponibles(ctx, horariomedico, calfin.getTime(), asistente);
			
			// Fecha preferida
			String fechaSeleccionada = Constantes.getSdfFecha().format(calini.getTime());
			
			// iteramos entre los bloques buscando en la hora tentativa
			for (int i = 0; i < horariodisponible.get("Horaini").size(); i++) {
				Date fechaDispIni = null; //LRHU.
				//Date fechaDispFin = null;
				try{
					fechaDispIni = Constantes.getSdfFechaHora().parse(fechaSeleccionada + " " + horariodisponible.get("Horaini").get(i));//LRHU.
					//fechaDispFin = Constantes.sdfFechaHora.parse(fechaSeleccionada + " " + horariodisponible.get("Horafin").get(i));
				}catch(Exception e){
					e.getStackTrace();
					s_log.log(Level.SEVERE, "Error de formato de fechas: " + e.getMessage());
				}
				//if (horariodisponible.get("Horaini").get(i).compareTo(horaini) >= 0 
				//&& horariodisponible.get("Horafin").get(i).compareTo(horafin) >= 0 ) {
				//LRHU.Validamos que la cita quepa dentro del hueco encontrado, cuando la hora seleccionada esta dentro del hueco. 
				//Comparamos la duracion de la cita contra la duracion del hueco a usando como hora inicial la hora seleccionada para agendar la cita 
				if((calini.getTime().equals(fechaDispIni) || calini.getTime().after(fechaDispIni)) && getDuracion(horaini, horariodisponible.get("Horafin").get(i)) >= citamedica.getDuracion()){ 
					citamedica.setFechaHrCita(new Timestamp(calini.getTime().getTime()));
					if (citamedica.save(trxName)) {
						guardada = true;
						break;
					}
				}
				//LRHU.Cuando la hora seleccionada no esta disponible validamos que el hueco encontrado este despues de la hora seleccionada. 
				//Validamos que la cita quepa en el hueco 
				if(calini.getTime().equals(fechaDispIni) || calini.getTime().before(fechaDispIni) && getDuracion(horariodisponible.get("Horaini").get(i), horariodisponible.get("Horafin").get(i)) >= citamedica.getDuracion()){
					// Si es hora disponible, por lo tanto la guarda
					citamedica.setFechaHrCita(new Timestamp(fechaDispIni.getTime()));
					if (citamedica.save(trxName)) {
						guardada = true;
						break;
					}
				}			

			}
			/*if (!guardada) {
				// Si no ha guardado la cita busca en los bloques espacios de duracion en donde
				// quepa
				for (int i = 0; i < horariodisponible.get("Horaini").size(); i++) {
					Date fechaDispIni = null;
					try{
						fechaDispIni = Constantes.sdfFechaHora.parse(fechaSeleccionada + " " + horariodisponible.get("Horaini").get(i));
					} catch(Exception e){
						e.getStackTrace();
						s_log.log(Level.SEVERE, "Error de formato de fechas: " + e.getMessage());
					}
					if(fechaDispIni.equals(fechaActual) || fechaDispIni.after(fechaActual)){
						if (getDuracion(horariodisponible.get("Horaini").get(i), horariodisponible.get("Horafin").get(i)) >= duracion) {
							// Si es hora disponible, por lo tanto la guarda
							horaini = horariodisponible.get("Horaini").get(i);
							String hora = horaini.substring(0, horaini.indexOf(':'));
							String min = horaini.substring(horaini.indexOf(':') + 1, horaini.length());
							int h = Integer.parseInt(hora);
							int m = Integer.parseInt(min);
							Timestamp newdate = new Timestamp(calini.getTime().getTime());
							newdate.setHours(h);
							newdate.setMinutes(m);
							citamedica.setFechaHrCita(newdate);
							if (citamedica.save(trxName)) {
								guardada = true;
								break;
							}
						}
					}
				}
			}*/
			if (!guardada) {
				// Si no gaurdo pasa al dia siguiente.
				calini.add(Calendar.DATE, 1);
				calfin.add(Calendar.DATE, 1);
				calini.set(Calendar.HOUR_OF_DAY, 0);
				calini.set(Calendar.MINUTE, 0);
				calfin.set(Calendar.HOUR_OF_DAY, 0);
				calfin.set(Calendar.MINUTE, 0);
				calfin.add(Calendar.MINUTE, duracion); //agregamos la duraci�n.
			}
			if (citamedica.get_ID() != 0 && !guardada) {
				// Si no ha guardado pero ya le asigno un id valida que regrese el ID a 0
				citamedica = cmoriginal;
			}
		} while (!guardada); // Sale del ciclo hasta que guarde la cita

		return citamedica; // Regresa la cita agendada con ID y la fecha en que se agendo
	}

	/**
	 * Metodo que agenda tratamientos.
	 * 
	 * @author Julio Gutierrez
	 * @param ctx
	 * @param citaoriginal
	 *        Contiene el Medico, asistente, especialidad y fecha tentativa de la primer cita del
	 *        tratamiento
	 * @param tratamiento
	 * @param estaagendada
	 *        Si es true significa que todas van a hacer referencia a la cita mandada, si es false
	 *        todas hacen referencia al primer cita
	 * @return Lista de citas agendadas
	 */
	public static List<MEXMECitaMedica> agendar(Properties ctx, final MEXMECitaMedica citaoriginal, final int tratamiento, boolean estaagendada,
			String trxName) {
		int citaref = 0;
		// Valida tener los datos necesarios para el proceso
		if (!validarCitaMedica(citaoriginal)) {
			return null;
		}
		MEXMECitaMedica citamedica = citaoriginal;
		MEXMETratamientos mtratamiento = new MEXMETratamientos(ctx, tratamiento, null);
		int med = citamedica.getEXME_Medico_ID();
		
		Date fechainicial = new Date(citamedica.getFechaHrCita().getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechainicial);
		int hora = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE); 
		
		HashMap<String, String> horariomedico = MEXMEMedico.getHorarioMap(ctx, med);
		List<MEXMETratamientosDetalle> tratadet = MEXMETratamientosDetalle.getTratamientosDetalle(ctx, tratamiento);
		if (tratadet.size() == 0) {
			// no hay citas que agendar por k el tratamiento no tiene detalles.
			return null;
		}
		
		if(citaoriginal.getEXME_CitaMedica_ID() > 0 && !estaagendada){ //LRHU.
			citaref = citaoriginal.getEXME_CitaMedica_ID();
			citamedica = MEXMECitaMedica.copyFrom(ctx, citaoriginal, 0);
			//cal.add(Calendar.DATE, mtratamiento.getPeriodicity());
			citamedica.setFechaHrCita(new Timestamp(cal.getTime().getTime()));
		}
		
		if(citaoriginal.getEXME_CitaMedica_ID() > 0  && estaagendada){ 
			if (citaoriginal.getCitaNo() == 0){//Le agregamos que es la primera cita si el tratamiento esta iniciado y no tiene un numero de cita.
				citaoriginal.setCitaNo(1);
			}
			if(citaoriginal.getEXME_Tratamiento_ID() == 0){//Le agregamos el id del tratamiento si no tiene uno asignado.
				citaoriginal.setEXME_Tratamiento_ID(tratamiento);
			}
			if (!citamedica.save(trxName)) {
				return null;
			}
		}
		/*// LLeno la cita
		citamedica.setDuracion(tratadet.get(0).getDuracion());
		// citamedica.setCitaDe("I"); // revisar esto
		citamedica.setConfirmada(false);
		citamedica.setCitaNo(BigDecimal.valueOf(tratadet.get(0).getCitaNo()));
		citamedica.setEXME_Tratamiento_ID(tratamiento);
		citamedica.setEstatus("0");
		citamedica.setName("Cita " + tratadet.get(0).getCitaNo() + " " + mtratamiento.getName() + " - " + tratadet.get(0).getName());
		if(citaoriginal.getDescription()==null)
			citamedica.setDescription(mtratamiento.getDescription()==null?"":mtratamiento.getDescription());
		else 
			citamedica.setDescription(citaoriginal.getDescription());*/
		
		List<MEXMECitaMedica> retorno = new ArrayList<MEXMECitaMedica>();
		//int citaref = 0;
		if (!estaagendada) {
			// LLeno la cita
			citamedica.setDuracion(tratadet.get(0).getDuracion());
			// citamedica.setCitaDe("I"); // revisar esto
			citamedica.setConfirmada(false);
			citamedica.setCitaNo(tratadet.get(0).getSessionNo());
			citamedica.setEXME_Tratamiento_ID(tratamiento);
			citamedica.setEstatus("0");
			//citamedica.setName("Cita " + tratadet.get(0).getCitaNo() + " " + mtratamiento.getName() + " - " + tratadet.get(0).getName());
			if(citaoriginal.getDescription()==null)
				citamedica.setDescription(mtratamiento.getDescription()==null?"":mtratamiento.getDescription());
			else 
				citamedica.setDescription(citaoriginal.getDescription());
			// si no esta agendada la agenda
			if(citaref>0) //Verificamos si hay una cita de referencia.
				citamedica.setRef_CitaMedica_ID(citaref);
			citamedica = agendar(ctx, citamedica, horariomedico, trxName);
			citamedica.setName(Msg.translate(ctx,"Cita")+" " + citamedica.getEXME_CitaMedica_ID() + "_" + tratadet.get(0).getSessionNo() + " " + mtratamiento.getValue() + " - " + tratadet.get(0).getValue());
			cal.setTime(new Date(citamedica.getFechaHrCita().getTime()));
//			if (citamedica == null) {
//				return null; //Dead code
//			} else {
				if(!citamedica.save(trxName)){
					return null;
				}
				retorno.add(citamedica);
//			}
		}
		
		//asignamos la referencia a las siguientes citas.
		citaref = citamedica.getEXME_CitaMedica_ID();
		for (int i = 1; i < tratadet.size(); i++) { // empieza desde 1 por k la primer ia c agendo
			MEXMECitaMedica citanext = MEXMECitaMedica.copyFrom(ctx, citamedica, 0);
			citanext.setRef_CitaMedica_ID(citaref);
			citanext.setDuracion(tratadet.get(i).getDuracion());
			citanext.setCitaNo(tratadet.get(i).getSessionNo());
			citanext.setEXME_Tratamiento_ID(tratamiento);
			//citanext.setName("Cita " + tratadet.get(i).getCitaNo() + " " + mtratamiento.getValue() + " - " + tratadet.get(i).getValue());
			citanext.setDescription(mtratamiento.getDescription() + " - " + tratadet.get(i).getDescription());
			//cal.add(Calendar.DATE, mtratamiento.getPeriodicity());
			cal.set(Calendar.HOUR_OF_DAY, hora);
			cal.set(Calendar.MINUTE, min);
			Timestamp newdate = new Timestamp(cal.getTime().getTime());
			/*newdate.setHours(hora);
			newdate.setMinutes(min);*/
			citanext.setFechaHrCita(newdate);
			citanext = agendar(ctx, citanext, horariomedico, trxName);
			citanext.setName(Msg.translate(ctx,"Cita")+" " + citanext.getEXME_CitaMedica_ID() + "_" + tratadet.get(i).getSessionNo() + " " + mtratamiento.getValue() + " - " + tratadet.get(i).getValue());
			cal.setTime(new Date(citanext.getFechaHrCita().getTime()));
			if (citanext != null) {
				if(!citanext.save(trxName)){
					return null;
				}
				retorno.add(citanext);
			}
		}

		return retorno;
	}

	/**
	 * Valida tener los datos necesario para agendar.
	 * 
	 * @author Julio Gutierrez
	 * @param citamedica
	 * @return
	 */
	public static boolean validarCitaMedica(final MEXMECitaMedica citamedica) {
		if (citamedica.getFechaHrCita() == null) {
			return false;
		}
		if (citamedica.getEXME_Asistente_ID() == 0) {
			return false;
		}
		if (citamedica.getEXME_Especialidad_ID() == 0) {
			return false;
		}
		if (citamedica.getEXME_Medico_ID() == 0) {
			return false;
		}
		if (citamedica.getEXME_Paciente_ID() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Agenda la cita.
	 * 
	 * @author Julio Gutierrez
	 * @param cm
	 * @return regresa la cita agendada
	 *
	public static MEXMECitaMedica agendar(Properties ctx, final MEXMECitaMedica cm, String trxName) {
		return agendar(ctx, cm, 1, 0, 0, true, trxName).get(0);
	}*/

	/**
	 * Metodo auxiliar para sacar las horas del calendario tomando en cuenta el formado hh:mm.
	 * 
	 * @author Julio Gutierrez
	 * @param cal
	 * @return
	 */
	protected
	static String getHoraString(Calendar cal) {
		String retorno = "";
		String hora = "" + cal.getTime().getHours();
		if (hora.length() < 2) {
			hora = "0" + hora;
		}
		String min = "" + cal.getTime().getMinutes();
		if (min.length() < 2) {
			min = "0" + min;
		}
		retorno = hora + ":" + min;
		return retorno;
	}

	/**
	 * Metodo que Agenda una cita o series de citas en base una primera y el intervalo entre citas.
	 * Como se decidio que no se iba a utilizar este metodo no esta 100% probado, ¡¡revisar antes de
	 * usar!!.
	 * 
	 * @author Julio Gutierrez
	 * @param cm
	 *        cita por agendar
	 * @param cantidad
	 *        cantidad de citas a agendar
	 * @param intervalo
	 *        Intervalo entre las citas ejemplo 3
	 * @param periodo
	 *        Periodo entre intervalo de citas ejemnplo Calendar.Month
	 * @param inserfirst
	 *        boolean k indidca si inserta la primer cita.
	 *
	public static List<MEXMECitaMedica> agendar(Properties ctx, final MEXMECitaMedica cm, final int cantidad, final int intervalo, final int periodo,
			final boolean insertfirst, String trxName) {
		List<MEXMECitaMedica> retorno = new ArrayList<MEXMECitaMedica>();
		int med = cm.getEXME_Medico_ID();
		HashMap<String, String> horariomedico = MEXMEMedico.getHorarioMap(ctx, med);
		int nocita = 1;
		cm.setCitaNo(nocita);
		MEXMECitaMedica newcita = cm;
		if (insertfirst) {
			newcita = agendar(ctx, cm, horariomedico, trxName);// Lama
		}
		if (newcita == null) {
			return null;
		}
		int ref = newcita.get_ID();
		while (cantidad != nocita && newcita != null) {
			retorno.add(newcita);
			newcita.setCitaNo(nocita++);
			newcita.setRef_CitaMedica_ID(ref);
			Date fecha = newcita.getFechaHrCita();
			Calendar cal = Calendar.getInstance();
//			cal.set(fecha.getYear(), fecha.getMonth(), fecha.getDate(), fecha.getHours(), fecha.getMinutes(), 0);
			cal.setTime(fecha);
			cal.set(Calendar.SECOND, 0);
			cal.add(periodo, intervalo);
			newcita.setFechaHrCita((Timestamp) cal.getTime());
			newcita = agendar(ctx, newcita, horariomedico, trxName);// Lama
			if (newcita == null) {
				return retorno;
			}
		}
		retorno.add(newcita);
		return retorno;
	}*/

	/**
	 * Agenda una lista de citas ya con fechas tentativas. Como se decidio que no se iba a utilizar
	 * este metodo no esta 100% probado, ¡¡revisar antes de usar!!.
	 * 
	 * @author Julio Gutierrez
	 *
	public static List<MEXMECitaMedica> agendar(Properties ctx, final List<MEXMECitaMedica> lstcm, String trxName) {

		List<MEXMECitaMedica> retorno = new ArrayList<MEXMECitaMedica>();
		// se supone que todas las citas van al mismo medico.
		int med = lstcm.get(0).getEXME_Medico_ID();
		HashMap<String, String> horariomedico = MEXMEMedico.getHorarioMap(ctx, med);

		// itera entre las citas
		for (int index = 0; index < lstcm.size(); index++) {
			MEXMECitaMedica cita = agendar(ctx, lstcm.get(index), horariomedico, trxName);// Lama
			if (cita != null) {
				retorno.add(cita);
			}
		}

		return retorno;
	}*/

	/**
	 * @author Julio Gutierrez
	 * @param horariomedico
	 *        es un mapa que contiene el horario del medico y su ID, fecha Fecha en que se requieren
	 *        espacios disponibles, asistente ID del asistente
	 * @return HashMap<String, List<String>> Regresa un Mapa con una lista de las Horas de inicio
	 *         (key = "Horaini"), una lista de las Horas de fin (key = "Horafin") de los espacios
	 *         vacios
	 */
	public static HashMap<String, List<String>> getHorasdisponibles(
			Properties ctx, final HashMap<String, String> horariomedico, 
			final Date fecha, final int asistente) {
		
		if(horariomedico==null || horariomedico.size()<=1)
			return null;
		
		// lostas que guardan las horas de entrada y salida de los bloques
		List<String> HorasIni = new ArrayList<String>();
		List<String> HorasFin = new ArrayList<String>();

		// Mapas con las citas programadas
		HashMap<String, List<String>> horarioocupado = MEXMECitaMedica.getCitasMedicoMap(ctx, Integer.parseInt(horariomedico.get("ID")), fecha);
		
		// Fecha en formato
		String fechaStr = Constantes.getSdfFecha().format(fecha);
		// Dia
//		int dia = fecha.getDay();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		// Para saber si es entresemana
		String sema = "Es"; 
		
		// por si es fin de semana se le resta uno por el TimeStamp
		if ((dia == Calendar.SUNDAY - 1 || dia == Calendar.SATURDAY - 1))
			sema = "Fs"; // Cambia el valor para los fines de semana
		
		
		// Revisa los 3 horarios del doctor
		for (int index = 1; index <= 3; index++) 
		{
			String horaentrada = horariomedico.get("HoraEnt" + index + sema);
			String horainter = "";
			String horasalida = horariomedico.get("HoraSal" + index + sema);
			
			// Solo si tiene horario
			if (horaentrada != null && horasalida != null) 
			{
				if (horaentrada.length() < 5)
					horaentrada = "0" + horaentrada;
				
				if (horasalida.length() < 5)
					horasalida = "0" + horasalida;
				
				// 
				if (horarioocupado.get("Horaini").size() == 0) 
				{
					// Si no hay citas programadas, toma todo el dia
					HorasIni.add(horaentrada);
					HorasFin.add(horasalida);
				} else 
				{
					horainter = horaentrada;
					Date horaEntradaMed = null;//LRHU.
					Date horaSalidaMed = null; //LRHU.
					try{
						//LRHU. La hora de entrada del m�dico
						horaEntradaMed = Constantes.getSdfFechaHora()
						.parse(fechaStr + " " + horaentrada);
						
						//LRHU. La hora de salida del m�dico
						horaSalidaMed = Constantes.getSdfFechaHora()
						.parse(fechaStr + " " + horasalida);
						
					}catch(Exception e){
						e.getStackTrace();
					}
					
					// iteramos el horario de inicio
					for (int i = 0; i < horarioocupado.get("Horaini").size(); i++) 
					{
						Date horaOcupadaInter = null; //LRHU.
						@SuppressWarnings("unused")
						Date horaOcupadaIni = null; //LRHU.
						Date horaOcupadaFin = null; //LRHU.
						
						try{
							horaOcupadaInter = Constantes.getSdfFechaHora()
							.parse(fechaStr + " " + horainter);
							//LRHU. Guardamos temporalmente las horas ocupadas
							horaOcupadaIni = Constantes.getSdfFechaHora()
							.parse(fechaStr + " " + horarioocupado.get("Horaini").get(i)); 
							horaOcupadaFin = Constantes.getSdfFechaHora()
							.parse(fechaStr + " " + horarioocupado.get("Horafin").get(i)); //LRHU.
						}catch(Exception e){
							e.getStackTrace();
						}
						
						// LRHU. Vamos a tomar las horas disponibles que 
						// estan dentro del horario del m�dico.
						if(horaOcupadaInter.getTime() >= horaEntradaMed.getTime() 
								&& horaOcupadaInter.getTime() < horaSalidaMed.getTime()){ 
							
							//
							while (horainter.compareTo(horarioocupado.get("Horaini").get(i)) < 0) 
							{
								int hora = Integer.parseInt(horainter.substring(0, 
										horainter.indexOf(':')));
								int min = Integer.parseInt(horainter.substring(
										horainter.indexOf(':') + 1, horainter.length()));
								// recorre minuto a minuto hasta toparse con
								// otra cita.
								min += 1;
								if (min >= 60) {
									hora++;
									min -= 60;
								}
								String Hora = "" + hora;
								String Min = "" + min;
								// ifs que validan el formato hh:mm
								if (Hora.length() < 2) {
									Hora = "0" + Hora;
								}
								if (Min.length() < 2) {
									Min = "0" + Min;
								}
								horainter = Hora + ":" + Min;
								
								// LRHU. Comparamos con la hora de la salida 
								// para ponerla como la hora fin disponible.
								if(horainter.equals(horasalida)) 
									break;
							}// fin while
							
							//
							if (!horaentrada.equals(horainter)) 
							{
								// Solo en caso que se aia generado un bloque de
								// espacio libre lo guarda en las listas
								HorasIni.add(horaentrada);
								HorasFin.add(horainter);
							}
						}
						
						// LRHU. Antes de cambiar las varaibles, 
						// verificamos que la hora ocupada este dentro del horario del m�dico.
						if((horaOcupadaFin.equals(horaEntradaMed) 
								|| horaOcupadaFin.after(horaEntradaMed)))
						{
							// Brinca las horas ocupadas.
							horaentrada = horarioocupado.get("Horafin").get(i);
							horainter = horaentrada;
						}
						
					}// fin for de horarioocupado.get("Horaini") 
					
					// En caso de que quede espacio entre la ultima cita y
					// la salida del medico
					if (horaentrada.compareTo(horasalida) < 0) 
					{
						HorasIni.add(horaentrada);
						HorasFin.add(horasalida);
					}
				}// Fin else del if (horarioocupado.get("Horaini").size() == 0) 
			}
			
			// para que solo revise un horario, si es fin de semana
			if (sema.equals("Fs"))
				index = 10;
			
		}// fin for
		
		// Crea y mete los datos al mapa que regresa el metodo
		HashMap<String, List<String>> retorno = new HashMap<String, List<String>>();
		retorno.put("Horaini", HorasIni);
		retorno.put("Horafin", HorasFin);
		
		return retorno;
	}

	/**
	 * Metodo auxiliar que saca la duracion entre dos horas
	 * 
	 * @author Julio Gutierrez
	 * @param fechaini
	 * @param fechafin
	 * @return valor en minutos.
	 */
	protected static int getDuracion(final String fechaini, final String fechafin) {
		int duracion = 0;
		int horaini = Integer.parseInt(fechaini.substring(0, fechaini.indexOf(':')));
		int minini = Integer.parseInt(fechaini.substring(fechaini.indexOf(':') + 1, fechaini.length()));
		int horafin = Integer.parseInt(fechafin.substring(0, fechafin.indexOf(':')));
		int minfin = Integer.parseInt(fechafin.substring(fechafin.indexOf(':') + 1, fechafin.length()));
		duracion = ((horafin * 60) + minfin) - ((horaini * 60) + minini);
		return duracion;
	}
	/*
	public List<MEXMEActPacienteIndH> servicePrograming(Properties ctx, MEXMECtaPac ctapac, MEXMETratamientosServ tratServ, int pacienteID, int medicoID,
			Timestamp startDate, int M_Warehouse_ID, String trxName, int EXME_Tratamientos_Sesion_ID) throws Exception {//tdo:static

		List<MEXMEActPacienteIndH> retValue = new ArrayList<MEXMEActPacienteIndH>();
		/*if(!tratProd.isScheduleStudy()){
			return retValue;
		}*/
		// validate mandatory (msg)
		/*if (ctapac == null || (tratProd == null || tratProd.getM_Product_ID() <= 0) || startDate == null || M_Warehouse_ID <= 0) {
			throw new Exception(Utilerias.getMsg(ctx,"error.exp.noSave"));//FIXME: msg
		}*/
/*		if (startDate.before(new Date())) {
			throw new Exception(Utilerias.getMsg(ctx,"error.servicios.datePromised"));
		}
		MActPaciente actPac = null;
		MProduct product = new MProduct(ctx, tratServ.getM_Product_ID(), trxName);
		// validate product as a service
		//if (!product.isEstudio() || !product.getProductType().equalsIgnoreCase(MProduct.PRODUCTTYPE_Service)) {
		if (!product.getProductType().equalsIgnoreCase(MProduct.PRODUCTTYPE_Service)) {
			return retValue;// error.serv.noServicio
		}
		// validate product / warehouse relation
		if (!MEXMEReplenish.validarRelProductAlm(ctx, product.getM_Product_ID(), M_Warehouse_ID)) {
			return retValue;
		}
		// price
		MEXMEPaciente paciente = null;
		if (ctapac == null) {
			paciente = new MEXMEPaciente(ctx, pacienteID, trxName);
		}
	/*	
		PreciosVenta.m_configPre = MEXMEConfigPre.get(ctx, trxName);
		PreciosVenta.m_Paciente = ctapac != null ? ctapac.getPaciente():paciente;//FIXME: no debe ser estatico
		PreciosVenta.m_ConfigEC = MConfigEC.get(ctx, trxName);
*/
/*		MPriceList listaPrecios = MPriceList.getPriceList(ctx, ctapac != null ? ctapac.getPaciente():paciente);
		if (listaPrecios == null) {
			throw new Exception(Utilerias.getMsg(ctx,"error.solServ.listaPrecios"));
		}
		// actpaciente
		if (ctapac != null) {
			actPac = MActPaciente.getForCtaPac(ctapac);
			actPac.setCtaPac(ctapac);
		}else {
			actPac = new MEXMEActPaciente(ctx, 0, trxName);
			
			actPac.setEXME_Paciente_ID(pacienteID);				
			actPac.setFecha(new Timestamp(System.currentTimeMillis()));				
			actPac.setName(Msg.translate(ctx, "EXME_ActPaciente_ID") + ": ");
			actPac.setTipoArea(MActPaciente.TIPOAREA_MedicalConsultation);
			actPac.setEXME_Medico_ID(medicoID);
			
			if (!actPac.save(trxName)) {
				return retValue;
			}
		}
		

		MWarehouse warehouse = new MWarehouse(ctx, M_Warehouse_ID, null);
		MEXMEEstServ estServ = new MEXMEEstServ(ctx, Env.getEXME_EstServ_ID(ctx), null);

		Calendar fechaNext = Calendar.getInstance();
		fechaNext.setTime(startDate);

		/*for (int i = 0; i < tratProd.getQty(); i++) {
			if (i > 0) {
				// add interval
				//fechaNext.add(Calendar.DAY_OF_MONTH, tratProd.getIntervalo());
			}*/

/*			MEXMEActPacienteIndH header = createHeader(actPac, warehouse, listaPrecios, trxName);
			header.setDescription(Utilerias.getMsg(ctx,"msj.tratamientos.servicios"));
			//header.setEXME_EspecialidadSol_ID(tratProd.getTreatment().getEXME_Especialidad_ID());
			header.setDatePromised(new Timestamp(fechaNext.getTimeInMillis()));
			//header.setEXME_Tratamientos_ID(tratProd.getEXME_Tratamientos_ID());
			if(EXME_Tratamientos_Sesion_ID>0)header.setEXME_Tratamientos_Sesion_ID(EXME_Tratamientos_Sesion_ID);
			if (!header.save(trxName)) {
				return retValue;
			}
			List<MActPacienteInd> list2 = new ArrayList<MActPacienteInd>();
			MActPacienteInd serv = createDet(header, estServ, product, warehouse, trxName);

			serv.setLine(10);
			serv.setDescripcion(Utilerias.getMsg(ctx,"msj.tratamientos.servicios"));
			list2.add(serv);

			// validate promise date
			getPromisedDate(header, header.getDatePromised(), warehouse, list2);
			serv.setDatePromised(header.getDatePromised());

			if (!header.save(trxName)) {
				throw new Exception("error.citas.noGuardarSolicitud");
			}
			if (!serv.save(trxName)) {
				throw new Exception("error.notasMed.servicios.detalle");
			}
			fechaNext.setTime(header.getDatePromised());
			retValue.add(header);
		

		return retValue;
	}

	private MEXMEActPacienteIndH createHeader(MActPaciente actPac, MWarehouse warehouse, MPriceList listaPrecios, String trxName) throws Exception {
		MEXMEActPacienteIndH header = new MEXMEActPacienteIndH(actPac.getCtx(), 0, trxName);

		header.setEXME_ActPaciente_ID(actPac.getEXME_ActPaciente_ID());

		header.setC_DocType_ID(MEXMEDocType.getOfName(actPac.getCtx(), Constantes.SERVICIO, null));
		header.setC_DocTypeTarget_ID(header.getC_DocType_ID());
		header.setEstatus(MActPacienteIndH.ESTATUS_OrdersOfServiceRequested);
		header.setDateOrdered(new Timestamp(System.currentTimeMillis()));
		header.setDatePromised(new Timestamp(System.currentTimeMillis()));
		header.setIsService(true);

		//XREF		header.getDetalleEquipos(Env.getEXME_EstServ_ID(actPac.getCtx()));
		header.setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(actPac.getCtx()));
		header.setM_Warehouse_ID(warehouse.getM_Warehouse_ID());
		header.setM_Warehouse_Sol_ID(Env.getM_Warehouse_ID(actPac.getCtx())); // almacen de logeo

		header.setPriorityRule(MEXMEActPacienteIndH.PRIORITYRULE_ROUTINE);

		header.setM_PriceList_ID(listaPrecios.getM_PriceList_ID());
		header.setC_Currency_ID(listaPrecios.getC_Currency_ID());
		header.setIsTaxIncluded(listaPrecios.isTaxIncluded());
		int location = 0;
		if (actPac.getCtaPac().getEXME_Medico_ID() > 0) {
			header.setEXME_Medico_ID(actPac.getCtaPac().getEXME_Medico_ID());
			header.setMedicoNombre(actPac.getCtaPac().getMedico().getNombre_Med());
			header.setC_BPartner_ID(actPac.getCtaPac().getPaciente().getC_BPartner_ID());
			location = (int) Datos.getBPartnerLocation(actPac.getCtx(), actPac.getCtaPac().getPaciente().getC_BPartner_ID());
			if (location <= 0) {
				header.setC_Location_ID(actPac.getCtaPac().getPaciente().getC_Location_ID());
			} else {
				header.setC_Location_ID(location);
			}
		}else {
			MEXMEMedico md = new MEXMEMedico(actPac.getCtx(), actPac.getEXME_Medico_ID(), trxName);
			header.setMedicoNombre(md.getNombre_Med());
			header.setEXME_Medico_ID(actPac.getEXME_Medico_ID());
			header.setC_BPartner_ID(actPac.getPaciente().getC_BPartner_ID());
			location = (int) Datos.getBPartnerLocation(actPac.getCtx(), actPac.getPaciente().getC_BPartner_ID());
			if (location <= 0) {
				header.setC_Location_ID(actPac.getPaciente().getC_Location_ID());
			} else {
				header.setC_Location_ID(location);
			}
		}
		
		header.setEXME_CtaPac_ID(actPac.getCtaPac().getEXME_CtaPac_ID());		
		header.setEXME_MedicoSol_ID(Env.getEXME_Medico_ID(actPac.getCtx()));

		return header;
	}

	private MActPacienteInd createDet(MEXMEActPacienteIndH header, MEXMEEstServ estServ, MProduct product, MWarehouse warehouse, String trxName)
			throws Exception {
		MActPacienteInd serv = new MActPacienteInd(header.getCtx(), 0, trxName);

		serv.setActPacienteID(header.getEXME_ActPaciente_ID());
		serv.setEXME_ActPacienteIndH_ID(header.getEXME_ActPacienteIndH_ID());
		serv.setEstatus(header.getEstatus());

		serv.setM_Warehouse_ID(warehouse.getM_Warehouse_ID());
		serv.setM_Product_ID(product.getM_Product_ID());

		serv.setC_UOM_ID(product.getC_UOM_ID());
		serv.setQtyOrdered(Env.ONE);

		serv.setC_Currency_ID(header.getC_Currency_ID());
		serv.setDateOrdered(new Timestamp(System.currentTimeMillis()));
		serv.setEXME_Area_ID(estServ.getEXME_Area_ID());
		serv.setTipoArea(estServ.getTipoArea());
		serv.setAD_OrgTrx_ID(MEXMEEstServ.getEstServAlmOrgTrx(header.getCtx(), warehouse.getM_Warehouse_ID()));
		serv.setEstatus(MActPacienteInd.ESTATUS_RequestedService);

		serv.setWarehouseName(warehouse.getName());
		serv.setWarehouseLocation(warehouse.getLocationStr(true));

		MPrecios precios = PreciosVenta.precioProdVta(header.getCtx(), estServ.getTipoArea(), serv.getM_Product_ID(), serv.getQtyOrdered(), serv
				.getC_UOM_ID(), PreciosVenta.PVCE, header.getM_Warehouse_Sol_ID(), header.getM_Warehouse_ID(), serv.getEXME_Area_ID(), serv
				.getDateOrdered(), true, null);
		
		// Expert. Precios en cero
	/*	if (precios == null || (precios != null && Env.ZERO.compareTo(precios.getPriceList()) >= 0)) {
			MEXMEConfigPre cp = MEXMEConfigPre.get(Env.getCtx(), null);
			if (cp != null && cp.isAplicaServSinPrec()) {
				MPrecios.enCeros(serv);
			} else {
				throw new Exception("error.preciosVenta.precios");
			}
		} else {
		*/	//serv = precios.preciosActual(serv);
		//}
		/*return serv;
	}*/
/*
	private void getPromisedDate(MEXMEActPacienteIndH header, Timestamp date, MWarehouse warehouse, List<MActPacienteInd> list2) {
		try {
			MEXMEAgenda agenda = new MEXMEAgenda();

			String datePromisedStr = agenda.disponibilidadFechaHora(header.getCtx(), Constantes.getSdfFechaHora().format(date),
					warehouse.getM_Warehouse_ID(), list2, header.get_TrxName()).trim();

			if (datePromisedStr != null && datePromisedStr.length() > 10) {
				Timestamp datePromised = new Timestamp(Constantes.getSdfFechaHora().parse(datePromisedStr).getTime());

				String initialDate = datePromisedStr;
				int minutes = list2.size() * warehouse.getIntervalo();
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(Constantes.getSdfFechaHora().parse(initialDate));
				calendar.add(Calendar.MINUTE, minutes);

				header.setDatePromised(datePromised);
				header.setFecha_Fin(new Timestamp(calendar.getTimeInMillis()));
			}
		} catch (ParseException e) {
			s_log.log(Level.SEVERE, "save", e);
		}
	}
	*/
	/**
	 * Metodo que agenda tratamientos.
	 * 
	 * @author Julio Gutierrez
	 * @param ctx
	 * @param citaoriginal
	 *        Contiene el Medico, asistente, especialidad y fecha tentativa de la primer cita del
	 *        tratamiento
	 * @param tratamiento
	 * @param estaagendada
	 *        Si es true significa que todas van a hacer referencia a la cita mandada, si es false
	 *        todas hacen referencia al primer cita
	 * @return Lista de citas agendadas
	 */
	
	public static List<MEXMECitaMedica> agendarTratamiento(Properties ctx, 
			final int tratamiento, int medicoID, Timestamp startDate, int sesion,
			int pacienteID, String trxName, int EXME_Tratamientos_Sesion_ID) {
		
		// Valida tener los datos necesarios para el proceso
		int citaref = 0;
		int med = medicoID;

		// tratamiento
		MEXMETratamientos mtratamiento = new MEXMETratamientos(ctx, tratamiento, null);
		
		// Fecha probable (propuesta) de la cita
		Date fechainicial = new Date(startDate.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechainicial);		
		
		// Validamos si existe un horario para el medico
		HashMap<String, String> horariomedico = MEXMEMedico.getHorarioMap(ctx, med);
		if(horariomedico==null || horariomedico.size()<=1)
			return null;
		
		
		// no hay citas que agendar por k el tratamiento no tiene detalles.
		List<MEXMETratamientosDetalle> tratadet = MEXMETratamientosDetalle.
		getTratamientosDetalle(ctx, tratamiento, "AND SessionNo = "+ sesion);
		if (tratadet.size() == 0)
			return null;

		// Si la sesion actual no es la primera
		if (sesion > 1) {
			// buscamos la sesion anterior para obtener el intervalo
			List<MEXMETratamientosDetalle> tratdet = MEXMETratamientosDetalle.
			getTratamientosDetalle(ctx, tratamiento, "AND SessionNo = " + (sesion - 1));
			if (tratdet.get(0).getIntervalo() == MEXMETratamientosDetalle.INTERVALO_Days) {
				cal.add(Calendar.DATE, tratdet.get(0).getQtyIntervalo());
			}else if (tratdet.get(0).getIntervalo().equals(MEXMETratamientosDetalle.INTERVALO_Hours)) {
				cal.add(Calendar.HOUR_OF_DAY, tratdet.get(0).getQtyIntervalo());
			}else if (tratdet.get(0).getIntervalo().equals(MEXMETratamientosDetalle.INTERVALO_Months)) {
				cal.add(Calendar.MONTH, tratdet.get(0).getQtyIntervalo());
			}else if (tratdet.get(0).getIntervalo().equals(MEXMETratamientosDetalle.INTERVALO_Weeks)) {
				cal.add(Calendar.WEEK_OF_YEAR, tratdet.get(0).getQtyIntervalo());
			}else if (tratdet.get(0).getIntervalo().equals(MEXMETratamientosDetalle.INTERVALO_Years)) {
				cal.add(Calendar.YEAR, tratdet.get(0).getQtyIntervalo());
			}
		}
		
		// 
		int hora = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE); 
		
		// listado de citas
		List<MEXMECitaMedica> retorno = new ArrayList<MEXMECitaMedica>();

		 // empieza desde 1 por k la primer ia c agendo
		MEXMECitaMedica citanext = new MEXMECitaMedica(ctx, 0, trxName);
		citanext.setRef_CitaMedica_ID(citaref);
		citanext.setDuracion(tratadet.get(0).getDuracion());
		citanext.setCitaNo(tratadet.get(0).getSessionNo());
		citanext.setEXME_Tratamiento_ID(tratamiento);
		citanext.setDescription(mtratamiento.getDescription() + " - " + tratadet.get(0).getDescription());
		
		cal.set(Calendar.HOUR_OF_DAY, hora);
		cal.set(Calendar.MINUTE, min);
		Timestamp newdate = new Timestamp(cal.getTime().getTime());
		
		citanext.setFechaHrCita(newdate);//medicoID = Env.getEXME_Medico_ID(ctx);
		citanext.setEXME_Medico_ID(medicoID==0?Env.getEXME_Medico_ID(ctx):medicoID);
		citanext.setEXME_Especialidad_ID(mtratamiento.getEXME_Especialidad_ID());
		citanext.setEXME_Asistente_ID(Env.getContextAsInt(Env.getCtx(), "#EXME_Asistente_ID"));
		citanext.setEXME_Paciente_ID(pacienteID);
		citanext.setUtilidad(false);
		citanext.setProcessing(false);
		citanext.setConfirmada(false);
		citanext.setEstatus(MEXMECitaMedica.ESTATUS_ToBeConfirmed);
		citanext.setName(Msg.translate(ctx,"Cita")+" ");
		
		// agendar
		citanext = agendar(ctx, citanext, horariomedico, trxName);
		
		citanext.setName(Msg.translate(ctx,"Cita")+" "+citanext.getEXME_CitaMedica_ID() + "_" + tratadet.get(0).getSessionNo() + " " + mtratamiento.getValue() + " - " + tratadet.get(0).getValue());
		cal.setTime(new Date(citanext.getFechaHrCita().getTime()));
			
		// si es una cita de tratamientos, asignamos el id
		if(EXME_Tratamientos_Sesion_ID>0)
			citanext.setEXME_Tratamientos_Sesion_ID(EXME_Tratamientos_Sesion_ID);
		
		if (citanext != null) {
			if(!citanext.save(trxName)){
				return null;
			}
			retorno.add(citanext);
		}		

		return retorno;
	}
	
	
}
