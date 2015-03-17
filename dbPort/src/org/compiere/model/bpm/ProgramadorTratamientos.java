package org.compiere.model.bpm;

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
import org.compiere.model.MEXMEMedicoEsp;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Programador;

public class ProgramadorTratamientos extends Programador {

	public ProgramadorTratamientos() {
		// TODO Auto-generated constructor stub
	}

	/*******************************************************/
	/**
	 * Disponibilidad de horarios de medicos por especialidad
	 * 
	 * @return
	 */
	public List<MEXMEMedico> getDisponibilidad(Properties ctx,
			int especialidadId, Timestamp fechaTentativa, int duracion,
			String trxName) {
		// listado de medicos por espepcialidad
		List<MEXMEMedicoEsp> lstMedicosEsp = MEXMEMedicoEsp.getForSpec(ctx,
				especialidadId, trxName);

		List<MEXMEMedico> lstmedico = new ArrayList<MEXMEMedico>();

		// Revision por medico (medicos filtrados por especialidad)
		for (int i = 0; i < lstMedicosEsp.size(); i++) {
			HashMap<String, String> horariomedico = MEXMEMedico.getHorarioMap(
					ctx, lstMedicosEsp.get(i).getEXME_Medico_ID());

			if (horariomedico != null && horariomedico.size() > 1) {
				MEXMEMedico med = new MEXMEMedico(ctx, lstMedicosEsp.get(i)
						.getEXME_Medico_ID(), trxName);
				med.setFechaTentativa(fechaDisponible(ctx, horariomedico,
						fechaTentativa, especialidadId, duracion, trxName));
				lstmedico.add(med);
			}
		}

		return lstmedico;
	}

	/**
	 * Disponibilidad por medico
	 * 
	 * @param ctx
	 * @param horariomedico
	 * @param fechaProspecto
	 * @param especialidadId
	 * @param duracion
	 * @param trxName
	 * @return
	 */
	private Timestamp fechaDisponible(Properties ctx,
			final HashMap<String, String> horariomedico,
			Timestamp fechaProspecto, int especialidadId, int pDuracion,
			String trxName) {

		// Valida que tenga una fecha tentativa, especialidad
		if (fechaProspecto == null || especialidadId == 0) {
			return null;
		}

		int duracion = pDuracion;

		// configuracion
		MConfigEC configEC = MConfigEC.get(ctx, null); // LRHU. Para obtener la
		// duracion minima
		// configurada

		// fecha disponible
		Timestamp FechaHrCita = null;
		// si tuvo exito o no
		boolean guardada = false;

		// Saca las fechas de inicio y fin de cita tentativas.
		Date fechaini = new Date(fechaProspecto.getTime());
		Date fechaActual = DB.getDateForOrg(ctx);

		// Validamos que la fecha de la cita no sea menos a la actual
		// en caso de que asi fuera se cambia el valor poniendo la fecha actual
		if (fechaini.before(fechaActual))
			fechaini = fechaActual;

		// Duracion de la cita
		int duracionMin = configEC.getDurationMin();// LRHU. La duraci�n
		// m�nima configurada.
		if (duracion == 0 || duracion < duracionMin) { // LRHU. Comparamos
			// tambien contra la
			// duracion minima
			// configurada
			duracion = duracionMin; // LRHU. asignamos la duracion minima
		}

		// Fecha final
		Calendar calini = Calendar.getInstance();
		Calendar calfin = Calendar.getInstance();
		calini.setTime(fechaini);
		calfin.setTime(fechaini);
		calfin.add(Calendar.MINUTE, duracion);
		String horaini = getHoraString(calini);
		String fechaSeleccionada = Constantes.getSdfFecha().format(
				calini.getTime());

		do {

			// Obtenemos los bloques de horas disponibles.
			HashMap<String, List<String>> horariodisponible = getHorasdisponibles(
					ctx, horariomedico, calfin.getTime(), 0);

			// iteramos entre los bloques buscando en la hora tentativa
			for (int i = 0; i < horariodisponible.get("Horaini").size(); i++) {

				Date fechaDispIni = null;

				try {
					fechaDispIni = Constantes.getSdfFechaHora().parse(
							fechaSeleccionada + " "
									+ horariodisponible.get("Horaini").get(i));

				} catch (Exception e) {
					e.getStackTrace();
					s_log.log(Level.SEVERE, "Error de formato de fechas: "
							+ e.getMessage());
				}

				// LRHU.Validamos que la cita quepa dentro del hueco encontrado,
				// cuando la hora seleccionada esta dentro del hueco.
				// Comparamos la duracion de la cita contra la duracion del
				// hueco
				// a usando como hora inicial la hora seleccionada para agendar
				// la cita
				if ((calini.getTime().equals(fechaDispIni) || calini.getTime()
						.after(fechaDispIni))
						&& getDuracion(horaini, horariodisponible
								.get("Horafin").get(i)) >= duracion) {
					FechaHrCita = new Timestamp(calini.getTime().getTime());
					guardada = true;
					break;
				}

				// LRHU.Cuando la hora seleccionada no esta disponible
				// validamos que el hueco encontrado este despues de
				// la hora seleccionada.
				// Validamos que la cita quepa en el hueco
				if (calini.getTime().equals(fechaDispIni)
						|| calini.getTime().before(fechaDispIni)
						&& getDuracion(horariodisponible.get("Horaini").get(i),
								horariodisponible.get("Horafin").get(i)) >= duracion) {
					// Si es hora disponible, por lo tanto la guarda
					FechaHrCita = new Timestamp(fechaDispIni.getTime());
					guardada = true;
					break;
				}

			}

			if (!guardada) {
				// Si no gaurdo pasa al dia siguiente.
				calini.add(Calendar.DATE, 1);
				calfin.add(Calendar.DATE, 1);
				calini.set(Calendar.HOUR_OF_DAY, 0);
				calini.set(Calendar.MINUTE, 0);
				calfin.set(Calendar.HOUR_OF_DAY, 0);
				calfin.set(Calendar.MINUTE, 0);
				calfin.add(Calendar.MINUTE, duracion); // agregamos la

				FechaHrCita = null;
			}

		} while (!guardada); // Sale del ciclo hasta que guarde la cita

		return FechaHrCita; // Regresa la cita agendada con ID y la fecha en que
		// se agendo
	}

	public static List<MEXMECitaMedica> getNewSession() {
		List<MEXMECitaMedica> lstSessionNew = new ArrayList<MEXMECitaMedica>();
		return lstSessionNew;
	}

}
