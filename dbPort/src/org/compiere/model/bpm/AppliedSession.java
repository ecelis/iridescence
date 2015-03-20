package org.compiere.model.bpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMECitaMedica;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECuidadosPac;
import org.compiere.model.MEXMETratamientosDetalle;
import org.compiere.model.MEXMETratamientosServ;
import org.compiere.model.MProduct;
import org.compiere.model.ModelError;
import org.compiere.util.DB;
import org.compiere.util.Programador;
import org.compiere.util.Trx;

/**
 * Clase para aplicar una sesion de un tratamiento.
 * 
 * @author Expert
 * 
 */
public class AppliedSession {

	/** Contexto. */
	private Properties ctx = null;
	/** Identificador de la sesi�n por paciente y tratamiento. */
	private int tratSesionID = 0;
	/** Cuenta Paciente. */
	private MEXMECtaPac objCtaPac = null;
	/** Identificador del tratamiento. */
	private int tratamientoID = 0;
	/** Numero de sesi�n del tratamiento. */
	private int sesionNo = 0;
	/** Descripci�n configurada de la sesi�n. */
	private String description = null;

	/**
	 * Constructor.
	 * 
	 * @param ctx
	 * @param pTratSesionID
	 * @param pObjTratDetalle
	 * @param pObjCtaPac
	 */
	public AppliedSession(Properties ctx, int pTratSesionID,
			MEXMETratamientosDetalle pObjTratDetalle, MEXMECtaPac pObjCtaPac) {
		this.ctx = ctx;
		this.tratSesionID = pTratSesionID;
		this.objCtaPac = pObjCtaPac;

		if (pObjTratDetalle != null) {
			this.tratamientoID = pObjTratDetalle.getEXME_Tratamientos_ID();
			this.sesionNo = pObjTratDetalle.getSessionNo();
			this.description = pObjTratDetalle.getDescription();
		}

		lstErrors = new ArrayList<ModelError>();
	}

	/**
	 * Constructor.
	 * 
	 * @param ctx
	 * @param pTratSesionID
	 * @param pSesion
	 * @param pTratamientoID
	 * @param pDescription
	 * @param pObjCtaPac
	 */
	public AppliedSession(Properties ctx, int pTratSesionID, int pSesion,
			int pTratamientoID, String pDescription, MEXMECtaPac pObjCtaPac) {
		this.ctx = ctx;
		this.tratSesionID = pTratSesionID;
		this.objCtaPac = pObjCtaPac;
		this.tratamientoID = pTratamientoID;
		this.sesionNo = pSesion;
		this.description = pDescription;

		lstErrors = new ArrayList<ModelError>();
	}

	/**
	 * Generar las citas medicas
	 */
	public boolean generarCitas() {
		Trx m_trx = null;
		m_trx = Trx.get(Trx.createTrxName("CitaTrat"), true);

		if (m_trx == null) {
			return false;
		}

		try {

			List<MEXMECitaMedica> lstCitas = Programador.agendarTratamiento(
					ctx, tratamientoID, objCtaPac.getEXME_Medico_ID(),
					DB.getTimestampForOrg(ctx), sesionNo,
					objCtaPac.getEXME_Paciente_ID(), m_trx.getTrxName(),
					tratSesionID);

			if (lstCitas == null) {
				lstErrors.add(new ModelError(ModelError.TIPOERROR_Informativo,
						"msj.sinCitas"));
				return false;

			} else {
				m_trx.commit();

				for (int i = 0; i < lstCitas.size(); i++)
					lstErrors.add(new ModelError(
							ModelError.TIPOERROR_Informativo,
							"msg.odontologia.Historial.Id", lstCitas.get(i)
									.getFechaHrCita()));

			}

		} catch (Exception e) {
			lstErrors.add(new ModelError(ModelError.TIPOERROR_Error,
					"error.servicios"));
			return false;
		} finally {
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
			}
		}

		return true;
	}

	/**
	 * Generar las solicitudes de servicios
	 */
	public boolean solicitarServicio() {
		Trx m_trx = null;
		m_trx = Trx.get(Trx.createTrxName("ActDiag"), true);

		if (m_trx == null) {
			return false;
		}
		List<MEXMEActPacienteIndH> lista = new ArrayList<MEXMEActPacienteIndH>();

		try {
			MProduct prod = null;
//			Programador prog = new Programador();
			List<MEXMETratamientosServ> lstTratdet = MEXMETratamientosServ
					.getForTratamiento(ctx, tratamientoID, null);

			//
			if (!lstTratdet.isEmpty()) {

				for (MEXMETratamientosServ obj : lstTratdet) {
					prod = new MProduct(ctx, obj.getM_Product_ID(), m_trx
							.getTrxName());

					if (obj.getM_Product_ID() > 0 && prod.isService()) {

//						Timestamp fechaInicio = new Timestamp(System
//								.currentTimeMillis()
//								+ 24 * 60 * 60 * 1000);
//						int M_Warehouse_ID = obj.getAlmacen_Surt_ID();
/*
						lista = prog.servicePrograming(ctx, objCtaPac, obj,
								objCtaPac.getEXME_Paciente_ID(), objCtaPac
										.getEXME_Medico_ID(), fechaInicio,
								M_Warehouse_ID, m_trx.getTrxName(),
								tratSesionID);
*/
						//
						if (lista.isEmpty()) {
							lstErrors.add(new ModelError(
									ModelError.TIPOERROR_Informativo,
									"error.servicios"));

							return false;
						}
					}
				}

				m_trx.commit();

				if (lista != null && lista.size() > 0) {
					for (int i = 0; i < lista.size(); i++) {
						lstErrors.add(new ModelError(
								ModelError.TIPOERROR_Informativo,
								"exito.solicitudServicios", lista.get(i)
										.getDocumentNo(), lista.get(i)
										.getDatePromised()));
					}
				} else {
					lstErrors.add(new ModelError(ModelError.TIPOERROR_Error,
							"error.servicios"));

					return false;
				}

			} // fin if

		} catch (Exception e) {
			lstErrors.add(new ModelError(ModelError.TIPOERROR_Error,
					"error.servicios"));
			return false;
		} finally {
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
			}
		}
		return true;
	}

	/**
	 * Hacer las indicaciones medicas
	 */
	public boolean solicitarCuidadosPac() {

		Trx m_trx = null;
		m_trx = Trx.get(Trx.createTrxName("ActDiag"), true);

		if (m_trx == null) {
			return false;
		}

		try {

			// cuidados del paciente
			MEXMECuidadosPac cuidados = new MEXMECuidadosPac(ctx, 0, m_trx
					.getTrxName());
			cuidados.setEXME_Medico_ID(objCtaPac.getEXME_Medico_ID());
			cuidados.setEXME_CtaPac_ID(objCtaPac.getEXME_CtaPac_ID());
			cuidados.setEXME_Tratamientos_Sesion_ID(tratSesionID);
			cuidados.setDescription(description != null ? description : String
					.valueOf(sesionNo));

			// guardamos el registro
			if (!cuidados.save(m_trx.getTrxName())) {
				lstErrors.add(new ModelError(ModelError.TIPOERROR_Error,
						"error.servicios"));
				return false;
			} else {
				m_trx.commit();

				if (cuidados != null && cuidados.getEXME_CuidadosPac_ID() > 0) {
					lstErrors.add(new ModelError(
							ModelError.TIPOERROR_Informativo,
							"enfermeria.cuidados.guardar"));
				} else {
					lstErrors
							.add(new ModelError(
									ModelError.TIPOERROR_Informativo,
									"error.servicios"));
				}
			}
		} catch (Exception e) {
			lstErrors.add(new ModelError(ModelError.TIPOERROR_Error,
					"error.servicios"));
			return false;
		} finally {
			if (m_trx != null) {
				m_trx.rollback();
				m_trx.close();
			}
		}

		return true;
	}

	/** Listado de errores */
	private List<ModelError> lstErrors = null;

	public List<ModelError> getLstErrors() {
		if (lstErrors == null)
			lstErrors = new ArrayList<ModelError>();

		return lstErrors;
	}

	public void setLstErrors(List<ModelError> lstErrors) {
		this.lstErrors = lstErrors;
	}
}
