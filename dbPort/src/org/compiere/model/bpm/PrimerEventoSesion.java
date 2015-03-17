package org.compiere.model.bpm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MEXMEPrescription;
import org.compiere.model.MEXMETratamientosPaciente;
import org.compiere.model.X_EXME_ActPacienteIndH;
import org.compiere.model.X_EXME_CitaMedica;
import org.compiere.model.X_EXME_Prescription;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.WebEnv;

/**
 * Buscamos el primer evento de la sesion para saber cuando inicia o inicio esta
 * 
 * @author Expert
 * 
 */
public class PrimerEventoSesion {

	/** Logger */
	protected CLogger log = CLogger.getCLogger(PrimerEventoSesion.class);

	private Timestamp fechaInicio = null;
	private String estatus = null;
	private String nombreTabla = null;
	private int tratSesionID = 0;
	private MEXMETratamientosPaciente tratPacienteObj = null;

	/**
	 * Constructor
	 * 
	 * @param tratPac
	 * @param EXME_Tratamientos_Sesion_ID
	 */
	public PrimerEventoSesion(final MEXMETratamientosPaciente pTratPac,
			final int pTratSesionID) {

		super();
		this.tratSesionID = pTratSesionID;
		this.tratPacienteObj = pTratPac;

		if (pTratSesionID <= 0)
			return;
	}

	/**
	 * Tratamiento
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 *            (Requerido)
	 * @param EXME_Tratamiento_ID
	 * @param EXME_Tratamientos_Detalle_ID
	 */
	public void primerEvento() {

		if (this.tratSesionID <= 0)
			return;

		Properties ctx = tratPacienteObj.getCtx();

		// Criterios de busqueda
		List<Object> params = new ArrayList<Object>();
		String where = where(params, this.tratSesionID);

		if (WebEnv.DEBUG)
			log.log(Level.SEVERE, "Parametro>" + params.get(0).toString());

		if ((params == null || params.size() <= 0) && this.tratSesionID > 0) {
			params.add(this.tratSesionID);
			where = " AND EXME_Tratamientos_Sesion_ID = ? ";
		}

		// Buscamos la primera cita
		StringBuilder consulta = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		consulta.append(" WHERE IsActive = 'Y' ");
		consulta.append(where);
		consulta.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				MEXMECitaMedica.Table_Name));
		consulta.append(" ORDER BY NVL(fechaHrIni, fechaHrCita) ASC");

		List<MEXMECitaMedica> lstCitas = MEXMECitaMedica.get(ctx, null,
				consulta.toString(), params, null);

		// Buscamos el primer servicio
		StringBuilder whereClause = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		whereClause.append(" IsActive = 'Y' AND ").append(
				MEXMEActPacienteIndH.COLUMNNAME_DatePromised).append(
				" IS NOT NULL ");
		whereClause.append(where);

		StringBuilder orderBy = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		orderBy.append(MEXMEActPacienteIndH.COLUMNNAME_DatePromised)
				.append(" ASC ");

		List<MEXMEActPacienteIndH> lstServicios = MEXMEActPacienteIndH.getList(ctx,
				null, whereClause.toString(), orderBy.toString(), params);

		// Buscamos la primera prescripcion del tratamiento
		StringBuilder query = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		query.append(" SELECT * ").append(" FROM EXME_Prescription ").append(
				" WHERE IsActive = 'Y' ").append(
				MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						MEXMEPrescription.Table_Name)).append(where).append(
				" ORDER BY ").append(MEXMEPrescription.COLUMNNAME_DateOrdered)
				.append(" ASC ");

		MEXMEPrescription[] lstPrescripciones = MEXMEPrescription
				.getPrescription(ctx, query, params, null);

		// Primeros eventos de la sesion
		MEXMEActPacienteIndH actPac = null;
		MEXMECitaMedica cita = null;
		MEXMEPrescription prescripcion = null;

		Timestamp fechaCita = null;
		Timestamp fechaServicio = null;
		Timestamp fechaPrescription = null;

		// int datos = 0;

		// Listado de servicios
		if (lstServicios.size() > 0) {
			actPac = lstServicios.get(0);
			if (actPac != null) {
				fechaServicio = actPac.getDatePromised();
				if (fechaServicio != null) {
					// Ponemos por defecto la fecha de cita del servicio en caso
					// de existir
					setEstatus(actPac.getDocStatus());// TODO: Verificar el
					// estatus ya que este
					// podria no ser el
					// unico servicio que se
					// dara para la sesion
					setFechaInicio(actPac.getDatePromised());
					setNombreTabla(X_EXME_ActPacienteIndH.Table_Name);
				}
			}
		}

		// Listado de citas
		if (lstCitas.size() > 0) {
			cita = lstCitas.get(0);
			if (cita != null) {
				fechaCita = cita.getFechaHrIni() != null ? cita.getFechaHrIni()
						: cita.getFechaHrCita();
				if (fechaCita != null) {
					if (fechaServicio == null
							|| fechaCita.before(fechaServicio)) {
						// Si fecha cita es primero que la fecha de servicio
						setEstatus(cita.getDocStatus());
						setFechaInicio(cita.getFechaHrCita());
						setNombreTabla(X_EXME_CitaMedica.Table_Name);
					}
				}
			}
		}

		// Listado de prescripciones
		if (lstPrescripciones != null && lstPrescripciones.length > 0) {
			prescripcion = lstPrescripciones[0];
			if (prescripcion != null) {
				fechaPrescription = prescripcion.getDateOrdered();
				if (fechaPrescription != null) {
					if (getFechaInicio() == null
							|| prescripcion.getDateOrdered().before(
									getFechaInicio())) {
						// Si la fecha de la prescripcion es primero de la fecha
						// de la desicion anterior
						setEstatus(prescripcion.getDocStatus());
						setFechaInicio(prescripcion.getDateOrdered());
						setNombreTabla(X_EXME_Prescription.Table_Name);
					}
				}
			}
		}

	}

	/**
	 * 
	 * @param params
	 * @param EXME_TratamientoPaciente_ID
	 * @param EXME_CtaPac_ID
	 * @param EXME_Tratamiento_ID
	 * @param EXME_Tratamientos_Detalle_ID
	 * @return
	 */
	public String where(List<Object> params, int pTratSesionID) {
		// if(params==null)params = new ArrayList<Object>();
		// Buscamos la primera cita
		StringBuilder consulta = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		if (pTratSesionID > 0) {
			params.add(pTratSesionID);
			consulta.append(" AND EXME_Tratamientos_Sesion_ID = ? ");
		}
		// Siempre debe existir una cuenta por tratamiento
		/*
		 * if(EXME_CtaPac_ID>0){ consulta.append(" AND EXME_CtaPac_ID = ? ");
		 * params.add(EXME_CtaPac_ID); }
		 */
		// if(EXME_Tratamiento_ID>0){
		// consulta.append(" AND EXME_Tratamiento_ID = ? ");
		// params.add(EXME_Tratamiento_ID);
		// }

		// if(EXME_Tratamientos_Sesion_ID>0){
		// consulta.append(" AND EXME_Tratamientos_Sesion_ID = ? ");
		// //params.add(EXME_Tratamientos_Sesion_ID);
		// }

		/*
		 * if(EXME_TratamientoPaciente_ID >0){
		 * consulta.append(" AND EXME_TratamientoPaciente_ID = ? ");
		 * params.add(EXME_TratamientoPaciente_ID); }
		 */

		return consulta.toString();
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}
}
