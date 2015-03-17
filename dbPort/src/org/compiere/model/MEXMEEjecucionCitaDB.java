package org.compiere.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * 
 * @deprecated will be removed
 *
 */
public class MEXMEEjecucionCitaDB { 

	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MEXMEEjecucionCitaDB.class);


//	private static final String NO_INSERT_EJEC = "error.citas.noInsertEjec";



	/**
	 * Crea un registro en paciente alergias por cada alergia a una
	 * sustancia activa
	 *
	 * @param Hashtable con objetos tipo BasicoTresProps que representan a una alergia
	 * @throws SQLException si ocurre un error
	 * @deprecated utilizado solo por MostrarExpedienteMOActin}on
	 *
	public static void insertaAlergias(Properties ctx, int pacienteID, 
			List<BasicoTresProps> lstAlergias, String trxName) 
	throws Exception {
		s_log.log(Level.INFO, "***** Insertando en Paciente Aler ***** ");

		// ahora guardamos el detalle de la ejecucion de la consulta
		for (int i = 0; i < lstAlergias.size(); i++) {
			// recuperamos los valores de la forma
			BasicoTresProps alergias = lstAlergias.get(i);

			int id = (
					alergias.getValue() == null ? 
							0 : 
								Integer.parseInt(alergias.getValue())
			);

			MEXMEPacienteAler pacAler = new MEXMEPacienteAler(ctx, id, trxName);//Lama
			if (pacAler.getEXME_PacienteAler_ID() <= 0) {
				pacAler.setEXME_Paciente_ID(pacienteID);
				pacAler.setDescription(alergias.getDescripcion());
				pacAler.setEXME_SActiva_ID((int) alergias.getId());
				if (!pacAler.save(trxName)) {
					throw new SQLException(NO_INSERT_EJEC);
				} 
			} else {//Lama: actualiza la descripcion de la alergia		
				pacAler.setDescription(alergias.getDescripcion());
				if (pacAler.is_ValueChanged("Description") && !pacAler.save(trxName)) {
					s_log.log(Level.SEVERE, NO_INSERT_EJEC);
				}
			}
		}
	}*/



	/**
	 * Regresa la cita medica nueva de acuerdo los datos basicos obligatorios
	 * asigna la hora de acuerdo al horario disponible del medico
	 * @param ctx					Contexto de la aplicacion
	 * @param mapa				valores minimos requerido para creacion de cita
	 * @param useMinimum 	TRUE: asignar duracion minima a la cita FALSE: busca el intervalo del medico
	 * @param useFirstAvailable TRUE: toma la primer hora disponible FALSE: asigna la fecha seleccionada
	 * @param addhours 
	 * @param trxName 			Nombre de la transaccion
	 * @return						la cita creada para la fecha y hora actual
	 * @throws Exception
	 *
	public static MEXMECitaMedica incorporarCita(Properties ctx, HashMap<String, Object> mapa, boolean useMinimum,
			boolean useFirstAvailable, boolean addhours, String trxName) throws Exception {


		int pacienteId = (Integer) mapa.get("PACIENTE");
		int medicoId = (Integer) mapa.get("MEDICO");
		int especialidadId = (Integer) mapa.get("ESPECIALIDAD");
		int asistenteID = 0;
		Date fecha = null;
		int cita_ref = 0;
		String observaciones = null;
		
		if(mapa.get("ASISTENTE") != null) {
			asistenteID = (Integer) mapa.get("ASISTENTE"); 
		}

		if(mapa.get("FECHA") == null) {
			fecha = DB.getDateForOrg(ctx);
		} else {
			fecha = (Date) mapa.get("FECHA");
		}

		if(mapa.get("CITAREF") != null) {
			cita_ref = (Integer) mapa.get("CITAREF"); 
		}

		if(mapa.get("OBSERVA") != null) {
			observaciones = (String) mapa.get("OBSERVA"); 
		}

		MEXMECitaMedica datos = new MEXMECitaMedica(ctx, 0, trxName);

		int duracion = 0;
		MConfigEC config = MConfigEC.get(ctx);
		if (useMinimum) {
			duracion = (config != null ? config.getDurationMin() : 0);
		} else {
			duracion = MEXMEMedico.getIntervaloConsulta(ctx, medicoId, especialidadId, null);
		}
		//lhernandez. CPT por defecto
		if(config != null && config.getEXME_Intervencion_ID()>0){
			datos.setEXME_Intervencion_ID(config.getEXME_Intervencion_ID());
		}//lhernandez
		datos.setEXME_Medico_ID(medicoId);
		datos.setEXME_Paciente_ID(pacienteId);
		datos.setEXME_Especialidad_ID(especialidadId);

		if (asistenteID > 0) {
			datos.setEXME_Asistente_ID(asistenteID);
		}

		datos.setName(Msg.translate(ctx, "Cita"));
		datos.setEstatus(MEXMECitaMedica.ESTATUS_ToBeConfirmed);
		datos.setDuracion(duracion <= 0 ? 1 : duracion);
		datos.setHoraLlegada(DB.getTimestampForOrg(ctx));// se agrego hora de llegada del paciente: aocampo

		// desde otra cita
		if (cita_ref > 0) {
			datos.setRef_CitaMedica_ID(cita_ref);
			datos.setCitaDe(MEXMECitaMedica.CITADE_Subsequent);
		} else {
			datos.setCitaDe(MEXMECitaMedica.CITADE_FirstTime);
		}
		if (observaciones != null) {
			datos.setObservaciones(observaciones);
		}

		// Variables para utilizar en validacion de comparacion de fechas.
		String fecString = null;
		fecString = Constantes.getSdfFecha().format(fecha);

		// Obtenemos el horario del medico
		HashMap<String, String> horarioMedico = MEXMEMedico.getHorarioMap(ctx, medicoId);

		ResultSet rs = null;
		PreparedStatement psmt = null;
		try{
			if (!horarioMedico.isEmpty()) {
				//obtenemos las horas disponibles del medico
				HashMap<String, List<String>> horasDesocupadas = null;
				StringBuffer sqlHorario = new StringBuffer();

				long fech = fecha.getTime();
				//inicio dnuncio
				//booleano para saber si nos encontramos entre semana
				boolean EntreSemana=false;

				Calendar cal = Calendar.getInstance();
				cal.setTime(fecha);
				
				//obtenemos dia de la semana
				int dia = cal.get(Calendar.DAY_OF_WEEK);
				//comprobamos si es fin de semana
				if(dia == 1 || dia == 7){
					sqlHorario.append("ET.HORAENT1FS, ET.HORASAL1FS ");

				}else {
					sqlHorario.append("ET.HORAENT1ES, ET.HORASAL1ES, ")
					.append("ET.HORAENT2ES, ET.HORASAL2ES ");
					EntreSemana=true;
				}
				StringBuilder sql = new StringBuilder();
				


				sql.append("SELECT ")
				.append(sqlHorario.toString())
				.append("FROM EXME_TURNOS ET ")
				.append("INNER JOIN EXME_MEDICO_ORG EMO ON EMO.EXME_TURNOS_ID = ET.EXME_TURNOS_ID ")
				.append("INNER JOIN EXME_MEDICO EM ON EM.EXME_MEDICO_ID = EMO.EXME_MEDICO_ID ")
				.append("WHERE  EM.EXME_MEDICO_ID = ? ");
				//
				sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", MEXMEMedicoOrg.Table_Name, "EMO"));

				psmt = DB.prepareStatement(sql.toString(), null);
				psmt.setInt(1, medicoId);
				rs = psmt.executeQuery();

				String horaI = null;
				String horaF = null;
				String horaI2 = null;
				String horaF2 = null;

				//string con la fecha actual
				String hoy = Constantes.getSdfFecha().format(fecha);

				Date fechaI = null;
				Date fechaF = null;
				Date fechaI2 = null;
				Date fechaF2 = null;

				if (rs.next()){
					//Obtenemos las horas de entrada y salida de su turno
					horaI = rs.getString(1);
					horaF = rs.getString(2);
					//Establecemos las fechas para las horas en el día actual
					fechaI = Constantes.getSdfFechaHora().parse(hoy + ' ' + horaI);
					fechaF = Constantes.getSdfFechaHora().parse(hoy + ' ' + horaF);
					horaI = null;
					horaF = null;

					//Solo si es un dia entre semana se necesitará verificar el segundo horario
					if (EntreSemana){
						horaI2 = rs.getString("HORAENT2ES");
						horaF2 = rs.getString("HORASAL2ES");

						//Si el médico tiene un turno con 2 entradas y 2 salidas:
						if (horaI2 != null || horaF2 != null){

							//Establecemos las fechas para las horas en el día actual
							fechaI2 = Constantes.getSdfFechaHora().parse(hoy + ' ' + horaI2);
							fechaF2 = Constantes.getSdfFechaHora().parse(hoy + ' ' + horaF2);
							horaF2 = null;

							//Validamos que las horas esten en el orden correcto para procesarlas 
							if (fechaI.getTime() < fechaF.getTime() && fechaF.getTime() < fechaI2.getTime() &&
									fechaI2.getTime() < fechaF2.getTime()){
								//Si es la hora del intermedio para el doctor
								if(fech >= fechaF.getTime() && fech <= fechaI2.getTime()){
									//el medico regresa a las (horaI2)
									throw new Exception(Utilerias.getMessage(ctx, null, "error.citasdetalle.entreHorarios", horaI2));
								}
							}else{
								throw new Exception(Utilerias.getMessage(ctx, null,"error.citasdetalle.RevisarHorarios"));
							}
							
							if(fech >= fechaI.getTime() && fech <= fechaF.getTime() || fech >= fechaI2.getTime() && fech <= fechaF2.getTime()){
								try{
									horasDesocupadas = Programador.getHorasdisponibles(ctx, horarioMedico, fecha, asistenteID);			
									List<String> horasIni = horasDesocupadas.get("Horaini");
									List<String> horasFin = horasDesocupadas.get("Horafin");
									goAhead(ctx, horasIni, fecString, horasFin, addhours, fech, useFirstAvailable, datos, fecha);
								}catch (Exception e){
									throw e;
								}
							}else {
								throw new Exception(
										Utilerias.getMessage(ctx, null, "error.citasdetalle.medicoFueraTurno"));
								//esta hora no entra en el horario del medico
							}
						} else {
							if (fech >= fechaI.getTime() && fech <= fechaF.getTime()){
								try{
									horasDesocupadas = Programador.getHorasdisponibles(ctx, horarioMedico, fecha, asistenteID);			
									List<String> horasIni = horasDesocupadas.get("Horaini");
									List<String> horasFin = horasDesocupadas.get("Horafin");
									goAhead(ctx, horasIni, fecString, horasFin, addhours, fech, useFirstAvailable, datos, fecha);
								}catch (Exception e){
									throw e;
								}
							}else {
								throw new Exception(
										Utilerias.getMessage(ctx, null, "error.citasdetalle.medicoFueraTurno"));
								//esta hora no entra en el horario del medico
							}
						}
					}
				}	
			}else {
				throw new Exception(Utilerias.getMessage(ctx, null,"error.citasdetalle.medicoTurnoNoDef"));//el medico no tiene turno definido
			}
		}
		catch(Exception e){
			throw e;
		}finally {
			DB.close(rs, psmt);
		}
		return datos;
	} */

	/**
	 * Copia registros de la tabla temporal (EXME_T_Cuest) a EXME_ActPacienteDet
	 *
	 * @param Lista con objetos tipo CuestionarioView
	 * @throws Exception si ocurre un error
	 * 
	 */
	public static void copyCuestFromTemp(Properties ctx, List<MEXMETCuest> cuestionario, 
			int EXME_ActPaciente_ID, boolean valideteMandatory,  String trxName) 
	throws Exception {
		s_log.log(Level.INFO, "***** Copiando de EXME_T_Cuest a Act. Paciente Det ***** ");

		for (int i=0; i < cuestionario.size(); i++) {
			MEXMETCuest temp = (MEXMETCuest)cuestionario.get(i);
			MEXMEActPacienteDet det = new MEXMEActPacienteDet(ctx, 0, trxName);
			det.setDescription(temp.getDescription());
			det.setEXME_Cuestionario_ID(temp.getEXME_Cuestionario_ID());
			det.setEXME_Pregunta_ID(temp.getEXME_Pregunta_ID());
			det.setEXME_Medico_ID(temp.getEXME_Medico_ID());
			det.setRespuesta(temp.getRespuesta());
			det.setTextBinary(temp.getTextBinary());
			det.setRutaImagen(temp.getRutaImagen());

			if(temp.getConfidencial() == null) {
				det.setConfidencial("T");   
			} else {
				det.setConfidencial(temp.getConfidencial());
			}

			det.setPregunta_Lista_Value(temp.getPregunta_Lista_Value());
			det.setREF_EXME_PREGUNTA_ID(temp.getREF_EXME_PREGUNTA_ID());
			det.setSecuencia(temp.getSecuencia().intValue());
			det.setEXME_ActPaciente_ID(EXME_ActPaciente_ID);
			det.setFecha(temp.getFecha());
			det.setFolio(temp.getFolio());
			//Noelia: Guardamos la estacion de servicio donde se ejecuto la cita
			det.setEXME_EstServ_ID(Env.getContextAsInt(ctx,"#EXME_EstServ_ID"));

			if(valideteMandatory) {
				// Buscamos el detalle del cuestionatio, y validamos si la pregunta es obligatoria .- Lama
				MEXMECuestionarioDt cuestDet = MEXMECuestionarioDt.get(ctx, " AND EXME_CuestionarioDt.EXME_Cuestionario_ID = " 
						+ temp.getEXME_Cuestionario_ID() + " AND EXME_CuestionarioDt.EXME_Pregunta_id = " 
						+ temp.getEXME_Pregunta_ID(), trxName);

				if(cuestDet != null && cuestDet.isObligatoria()){
					if(!((temp.getRespuesta()!= null && !temp.getRespuesta().equalsIgnoreCase("")) || 
							(temp.getRutaImagen() != null && !temp.getRutaImagen().equalsIgnoreCase(""))))
						throw new Exception("error.notasCuest.obligatorio");
				}
			}
			det.setEXME_EncounterForms_ID(temp.getEXME_EncounterForms_ID());
			if (!det.save(trxName)) {
				throw new Exception("error.citas.getCuestionarios");
			}
			if (temp.getImagenBinary()!=null && temp.getImagenBinary().length > 0) {
				//Liz delaGarza - Copiar imagenes de la temporal a ActPacienteDet
				ByteArrayInputStream byteInput = new ByteArrayInputStream(temp.getImagenBinary());
				InputStream inputStream = (InputStream)byteInput ;				   
				MEXMETCuest.updateImagen(
						ctx, 
						MEXMEActPacienteDet.Table_Name, 
						"ImagenBinary", 
						det.getEXME_ActPacienteDet_ID(),
						0,
						0,
						0,
						0,
						0, 
						inputStream, 
						trxName, 
						0
				);
			}

		}

	}


//	public static void copyCuestFromTemp(Properties ctx, List<MEXMETCuest> cuestionario, 
//			int EXME_ActPaciente_ID, String trxName) throws Exception {
//		copyCuestFromTemp(ctx, cuestionario, EXME_ActPaciente_ID, false, trxName);
//	}

//	private static void goAhead(Properties ctx, List<String> horasIni, String fecString, List<String> horasFin, boolean addhours,
//			long fech, boolean useFirstAvailable, MEXMECitaMedica datos, Date fecha)throws Exception{
//		for (int i = 0; i < horasIni.size(); i++) {
//			// Concatenamos la fecha con la hora y los convertimos a Date para efectos de validacion.
//			Date fecTempIni = Constantes.getSdfFechaHora().parse(fecString + " " + horasIni.get(i));
//			Date fecTemFin = Constantes.getSdfFechaHora().parse(fecString + " " + horasFin.get(i));
//			// Validamos que no sea una hora antes de la actual.
//			if (!addhours) {
//				if (fech >= fecTempIni.getTime() && fech <= fecTemFin.getTime()) {
//					if (useFirstAvailable) {
//						datos.setFechaHrCita(new Timestamp(fecTempIni.getTime()));
//					} else {
//						datos.setFechaHrCita(new Timestamp(fecha.getTime()));
//					}
//					break;
//				} 
//			}else {
//				if (useFirstAvailable) {
//					datos.setFechaHrCita(new Timestamp(fecTempIni.getTime()));
//				} else {
//					datos.setFechaHrCita(new Timestamp(fecha.getTime()));
//				}
//				break;
//			}
//		}
//		if (datos.getFechaHrCita() == null) {
//			throw new Exception(Utilerias.getMessage(ctx, null,"error.citasdetalle.horaOcupada"));//se empalman las horas
//		}
//	}
}
