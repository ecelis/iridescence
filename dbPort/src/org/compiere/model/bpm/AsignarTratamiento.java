package org.compiere.model.bpm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMETratamientos;
import org.compiere.model.MEXMETratamientosDetalle;
import org.compiere.model.MEXMETratamientosPaciente;
import org.compiere.model.MEXMETratamientosSesion;
import org.compiere.model.ModelError;
import org.compiere.model.X_EXME_Tratamientos_Detalle;

/**
 * 
 * @author Expert
 * 
 */
public class AsignarTratamiento {

	/** Listado de errores producidos por la asignacion de tratamientos */
	private List<ModelError> lstErrores = new ArrayList<ModelError>();
	/** contexto */
	private Properties ctx = null;
	/** objeto de la cuenta paciete */
	private MEXMECtaPac ctaPac = null;

	/**
	 * Constructor
	 * 
	 * @param contex
	 *            contecto
	 * @param generarDetalle
	 *            true, genera el detalle de la sesion
	 */
	public AsignarTratamiento(Properties contex) {
		super();
		this.ctx = contex;
	}

	/**
	 * Crea la relacion tratamiento paciente, la cuenta paciente, las sesiones
	 * el detalle de las sesiones
	 * 
	 * @param pacienteID
	 *            paciente en cuestion
	 * @param ctaPacID
	 *            si existe cuenta paciente enviarla
	 * @param lstTrat
	 *            tratamientos aun no asignados
	 * @param medicoID
	 *            medico que prescribe el tratamiento o responsable
	 * @param trxname
	 *            nombre de transaccion para el metodo PO.save(trxName)
	 * @return listado de errores (ModelError)
	 */
	public List<ModelError> treatPacAll(int pacienteID, int ctaPacID,
			List<MEXMETratamientos> lstTrat, int medicoID, Timestamp fecha,
			String trxname) {

		if (pacienteID <= 0 || medicoID <= 0 || lstTrat == null
				|| trxname == null) {
			lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
					"parametros incompletos"));
			return lstErrores;
		}

		// iteramos todos los tratamientos aun no asignados
		for (int i = 0; i < lstTrat.size(); i++) {
			if (!generar(pacienteID, ctaPacID, lstTrat.get(i), medicoID, fecha,
					trxname))
				break;
		}

		return lstErrores;
	}

	/**
	 * Crea la relacion paciente tratamiento
	 * 
	 * @param pacienteID
	 *            paciente en cuestion
	 * @param ctaPacID
	 *            cuenta paciente si es que existe si no cero
	 * @param tratamientos
	 *            objeto del tratamiento
	 * @param medicoID
	 *            medico responsable
	 * @param trxname
	 *            nombre de trransaccion obligatoria
	 * @return retorna true si no hubo errores
	 */
	public boolean generar(int pacienteID, int ctaPacID,
			MEXMETratamientos tratamientos, int medicoID, Timestamp fecha,
			String trxname) {

		if (pacienteID <= 0 || medicoID <= 0 || tratamientos == null
				|| trxname == null) {
			lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
					"parametros incompletos"));
			return false;
		}

		if (tratamientos != null) {

			// Crea la cuenta paciente
			if (!crearCtaPac(ctaPacID, pacienteID, tratamientos
					.getEXME_Especialidad_ID(), medicoID, trxname))
				return false;

			// Generamos sesiones
			if (!crearSesiones(crearTratPac(tratamientos, ctaPac, trxname),
					fecha, trxname))
				return false;

		}

		return true;
	}

	/**
	 * Creacion de la cuenta paciente
	 * 
	 * @param pacienteID
	 *            id del paciente
	 * @param especialidadID
	 *            id de la especialidad del tratamiento
	 * @param medicoID
	 *            id del medico responsable
	 * @param trxname
	 *            nombre dela transaccion retorna true si no hubo errores
	 */
	public boolean crearCtaPac(int EXME_CtaPac_ID, int pacienteID,
			int especialidadID, int medicoID, String trxName) {

		MEXMECtaPac ctaPac = new MEXMECtaPac(ctx, EXME_CtaPac_ID, pacienteID, especialidadID,  medicoID, trxName);
		/*if (EXME_CtaPac_ID <= 0) {

			if (pacienteID <= 0 || medicoID <= 0 || especialidadID <= 0
					|| trxName == null) {
				lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
						"parametros incompletos"));
				return false;
			}
			
			ctaPac.setEXME_Paciente_ID(pacienteID);
			ctaPac.setEXME_Especialidad_ID(especialidadID);
			ctaPac.setEXME_Medico_ID(medicoID);
			
			// Lista de precios de configuracion
			ctaPac.setM_PriceList_ID(MEXMEConfigPre.getPriceList(ctx, null)
					.getM_PriceList_ID());
			
			// motivo de cita - dato requerido
			MMotivoCita[] motivos = MMotivoCita.get(ctx, null, trxName);
			if (motivos.length > 0) {
				ctaPac
						.setEXME_MotivoCita_ID(motivos[0]
								.getEXME_MotivoCita_ID());
				
				// Cuenta paciente creada para tratamientos
				//ctaPac.setEstatus(X_EXME_CtaPac.ESTATUS_Reserved);
				ctaPac.setEstatus(X_EXME_CtaPac.ENCOUNTERSTATUS_PreAdmission);

				// Requerimos el area (Est Serv de login)
				MEXMEEstServ estServ = new MEXMEEstServ(ctx, Env.getEXME_EstServ_ID(ctx), null);
				if(estServ!=null){
					ctaPac.setEXME_Area_ID(estServ.getEXME_Area_ID());
				}
				
				// obtenemos un id
				if (!ctaPac.save(trxName))
					lstErrores.add(new ModelError(
							ModelError.TIPOERROR_Informativo,
							"no se guardo el tratamiento"));
				return false;
			}
		}*/

		// obtenemos un id
		if (!ctaPac.save(trxName)){
			lstErrores.add(new ModelError(
					ModelError.TIPOERROR_Informativo,
			"no se guardo el tratamiento"));
			return false;
		}
		
		return true;
	}

	/**
	 * crea la relacion tratamiento paciente
	 * 
	 * @param tratamientos
	 *            objeto del tratamiento
	 * @param ctaPac
	 *            objeto de la cuenta paciente
	 * @param trxName
	 *            nombre de transaccion
	 * @return objeto de la relacion tratamiento paciente
	 */
	public MEXMETratamientosPaciente crearTratPac(
			MEXMETratamientos tratamientos, MEXMECtaPac ctaPac, String trxName) {

		// Generamos la relacion tratamiento paciente
		MEXMETratamientosPaciente tratpac = new MEXMETratamientosPaciente(ctx,
				0, trxName);

		//
		if (!tratpac.crear(tratamientos, ctaPac.getEXME_Paciente_ID(), ctaPac
				.getEXME_CtaPac_ID())) {
			lstErrores.add(new ModelError(ModelError.TIPOERROR_Informativo,
					"no se guardo el tratamiento"));
			return null;
		}

		//
		if (!tratpac.save(trxName)) {
			lstErrores.add(new ModelError(ModelError.TIPOERROR_Informativo,
					"no se guardo el tratamiento"));
			return null;
		}

		return tratpac;
	}

	/**
	 * Crea las sesiones
	 * 
	 * @param tratpac
	 *            Objeto de relacion tratamiento paciente
	 * @param trxName
	 *            nombre de transaccion
	 * @return retorna true si no hubo errores
	 */
	public boolean crearSesiones(MEXMETratamientosPaciente tratpac,
			Timestamp fecha, String trxName) {

		if (tratpac == null)
			return false;

		// lstTratDet =
		// MEXMETratamientosDetalle.get(tratpac.getEXME_Tratamientos_ID());
		// Traemos las sesiones dle tratamiento
		// TODO: cONSUME MUCHO TIEMPO
		// List<MEXMETratamientosDetalle> lstTratDet =
		List<MEXMETratamientosDetalle> lstTratDet = MEXMETratamientosDetalle
				.getTratamientosDetalle(ctx, tratpac.getEXME_Tratamientos_ID());

		if (!generarSesiones(lstTratDet, tratpac
				.getEXME_TratamientosPaciente_ID(), fecha, trxName))
			return false;

		return true;
	}

	/**
	 * Genera las sesiones y el detalle de las mismas si asi se solicita por
	 * cada detalle del tratamiento
	 * 
	 * @param lstTratDet
	 *            listado de objetos MEXMETratamientosDetalle
	 * @param pTratPacienteID
	 *            id relacion tratamiento paciente
	 * @param trxName
	 *            nombre de transaccion
	 * @return retorna true si no hubo errores
	 */
	public boolean generarSesiones(List<MEXMETratamientosDetalle> lstTratDet,
			int pTratPacienteID, Timestamp pFecha, String trxName) {

		if (lstTratDet == null || trxName == null) {
			lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
					"parametros incompletos"));
			return false;
		}

		Timestamp fecha = pFecha;
		//
		for (int i = 0; i < lstTratDet.size(); i++) {
			fecha = nuevaFecha(fecha, lstTratDet.get(i));

			if (!generarSesiones(lstTratDet.get(i), pTratPacienteID, fecha, i,
					trxName)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Crea la sesion (MEXMETratamientosSesion)
	 * 
	 * @param tratDet
	 *            objeto de detalle del tratamiento
	 * @param EXME_TratamientosPaciente_ID
	 *            id de la relacion tratamiento paciente
	 * @param trxName
	 *            nombre de transaccion
	 * @return retorna true si no hubo errores
	 */
	public boolean generarSesiones(MEXMETratamientosDetalle tratDet,
			int EXME_TratamientosPaciente_ID, Timestamp date, int secuencia,
			String trxName) {

		if (EXME_TratamientosPaciente_ID <= 0 || tratDet == null
				|| trxName == null) {
			lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
					"parametros incompletos"));
			return false;
		}

		// Si la sesion es tipo Consulta Externa generamos las citas
		MEXMETratamientosSesion sesion = new MEXMETratamientosSesion(ctx, 0,
				trxName);
		if (!sesion.createSesion(tratDet.getEXME_Tratamientos_Detalle_ID(),
				EXME_TratamientosPaciente_ID, tratDet.getDescription(), date,
				tratDet.getSessionNo()))
			return false;
		if (!sesion.save(trxName)) {
			lstErrores.add(new ModelError(ModelError.TIPOERROR_Error,
					"no guarda"));
			return false;
		}

		return true;
	}

	/*********************************************************************************************/

	/**
	 * Ultima cuenta paciente con tratamiento
	 * 
	 * @param ctx
	 * @param EXME_Paciente_ID
	 * @return
	 */
	public static MEXMECtaPac getCtaPacTratamientos(Properties ctx,
			int EXME_Paciente_ID, String trxName) {
		MEXMECtaPac ctapac = null;
		// TODO: Falta definir si la cuenta se habre o cierra para cada
		// tratamiento
		List<Integer> params = new ArrayList<Integer>();
		params.add(EXME_Paciente_ID);

		String where = " AND EXME_Paciente_ID = ? ORDER BY Created DESC ";

		List<MEXMETratamientosPaciente> lst = MEXMETratamientosPaciente
				.getTratamientos(ctx, where, params, null);

		if (lst != null && lst.size() > 0)
			ctapac = new MEXMECtaPac(ctx, lst.get(0).getEXME_CtaPac_ID(), null);

		return ctapac;
	}

	/**
	 * Fecha nueva
	 * 
	 * @param fecha1
	 * @param detalle
	 * @return
	 */
	private Timestamp nuevaFecha(Timestamp fecha1,
			MEXMETratamientosDetalle detalle) {
		Timestamp fechaNueva = fecha1;

		if (detalle.getSessionNo() > 1) {
			// fechaNueva + detalle.getIntervalo()
			int periodo = 0;
			if (detalle.getIntervalo().equals(
					X_EXME_Tratamientos_Detalle.INTERVALO_Years))
				periodo = Calendar.YEAR;
			if (detalle.getIntervalo().equals(
					X_EXME_Tratamientos_Detalle.INTERVALO_Days))
				periodo = Calendar.DATE;
			if (detalle.getIntervalo().equals(
					X_EXME_Tratamientos_Detalle.INTERVALO_Hours))
				periodo = Calendar.HOUR;
			if (detalle.getIntervalo().equals(
					X_EXME_Tratamientos_Detalle.INTERVALO_Months))
				periodo = Calendar.MONTH;
			if (detalle.getIntervalo().equals(
					X_EXME_Tratamientos_Detalle.INTERVALO_NotSpecified))
				periodo = Calendar.DATE;
			if (detalle.getIntervalo().equals(
					X_EXME_Tratamientos_Detalle.INTERVALO_Weeks))
				periodo = Calendar.WEDNESDAY;

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(fecha1.getTime());

			cal.add(periodo, detalle.getQtyIntervalo());
			fechaNueva = new Timestamp(cal.getTimeInMillis());
		}

		return fechaNueva;
	}

	public MEXMECtaPac getCtaPac() {
		if (ctaPac == null)
			ctaPac = new MEXMECtaPac(ctx, 0, null);
		return ctaPac;
	}

	public void setCtaPac(MEXMECtaPac ctaPac) {
		this.ctaPac = ctaPac;
	}

	public List<ModelError> getLstErrores() {
		return lstErrores;
	}

	public void setLstErrores(List<ModelError> lstErrores) {
		this.lstErrores = lstErrores;
	}
}
