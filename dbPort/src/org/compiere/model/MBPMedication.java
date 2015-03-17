package org.compiere.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Constantes;

/**
 * Logica de negocios para insertar en las tablas del expediente con referencia
 * al medicamento
 * 
 * @author Expert
 * 
 */
public class MBPMedication extends InsertMedication {
	/** Log de mensajes */
	private static CLogger slog = CLogger.getCLogger(MBPMedication.class);
	/** */
	private transient MEXMEActPaciente act = null;

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param trxName
	 */
	public MBPMedication(final Properties ctx, final String trxName) {
		super(ctx, trxName);
	}

	/**
	 * Valida los parametros
	 * 
	 * @param ctapac
	 * @param lstDetalle
	 * @return
	 */
	private boolean validate(final MEXMECtaPac ctapac,
			final List<ServicioView> lstDetalle) {
		int error = 0;
		if (ctapac == null || lstDetalle == null || lstDetalle.isEmpty()) {
			slog.log(Level.FINE,
					"if(ctapac== null || lstDetalle==null || lstDetalle.size()<0)");
			error++;
		} else {

			act = MEXMEActPaciente.getActPaciente(ctx,
					ctapac.getEXME_CtaPac_ID(), null, trxName);
			if (act == null) {
				slog.log(Level.FINE, "if(act==null)");
				error++;
			}
		}

		return error == 0;
	}

	/**
	 * Inserta la actividad paciente
	 * 
	 * @param ctapac
	 * @param planMed
	 * 
	 * @return EXME_ActPacienteIndH_ID
	 */
	protected MEXMEActPacienteIndH insertActPaciente(final MEXMECtaPac ctapac,
			final List<ServicioView> lstDetalle) {
		MEXMEActPacienteIndH exmeActPacIndH = null;
		try {

			if (validate(ctapac, lstDetalle)) {
				
				int pWarehouseID = 0;
				
				// Definicion de almacen para farmaceutico
				if(lstDetalle.get(0).getPlanMed()!=null && lstDetalle.get(0).getPlanMed().getPrescRXDet()!=null){
					pWarehouseID = lstDetalle.get(0).getPlanMed().getPrescRXDet().getMWarehouseCtaPacID ();
				}
				
				// Crea las lineas del encabezado
				exmeActPacIndH = insertActPacientInd(ctx,
						ctapac.getPaciente(), act.getEXME_ActPaciente_ID(), pWarehouseID,
						"5", // En algun proceso se utiliza ? 
						ctapac.getMedico().getEXME_Medico_ID(), 
						ctapac.getMedico().getNombre_Med(),
						ctapac.getEXME_CtaPac_ID(), 0, // En algun proceso se utiliza ?
						null, // En algun proceso se utiliza ?
						Constantes.RECETA, lstDetalle, trxName);

				if (exmeActPacIndH == null) {
					sLog.log(Level.SEVERE, "if(actPacIndH==null) ");
				} else {
					exmeActPacIndH.setPreAlta(this.preAlta);
					exmeActPacIndH.setLstActPacInd(lstActPacInd);
					exmeActPacIndH.save();
				}
				
			}
			
		} catch (Exception e) {
			slog.log(Level.SEVERE, "insertActPaciente", e);
			lstErrors.add(new ModelError(ModelError.TIPOERROR_Error,
					"msj.error"));
		}
		return exmeActPacIndH;
	}

	/**
	 * Actualiza el estatus del plan y de la prescripcion cuando se esta
	 * aplicando una dosis
	 * 
	 * @param planMed
	 */
	protected boolean updatePlan(final MPlanMed planMed,
			final MPlanMedLine planMedLine, final int pActPacIndHID) {
		slog.log(Level.INFO, "MBPMedication.updatePlan");
		
		boolean value = false;
		final MEXMEPrescription prescription = planMed.getPrescription();
		try {
			if (prescription == null) {
				slog.log(Level.INFO, "if (prescription == null)");
			} else {

				// Lama: PRN no completar ni validar lineas, su detalle se crea
				// infinitamente hasta
				// que se alcance la fecha final del plan (AutoStopPolicy) o sea
				// descontinuada
				if (!MPlanMed.DOCSTATUS_WaitingConfirmation.equals(planMed
						.getDocStatus())) {

					// Verificamos las lineas del plan para saber su estatus
					planMed.setDocStatus(MPlanMed.DOCSTATUS_Completed);
					prescription.setDocStatus(MPlanMed.DOCSTATUS_Completed);

					// Busco que todas las lineas de esta planificacion esten
					// aplicadas
					// si es asi esta programacion ya esta completa si no siguen
					// en
					// proceso
					planMed.completeLines(planMedLine.getEXME_PlanMedLine_ID());
					value = planMed.save();
					planMedLine.setEXME_ActPacienteIndH_ID(pActPacIndHID);
					planMedLine.save();
				}

				// Pongo el estatus de completo por defecto almenos que cambie
				// por
				// que el plan en cuestion no se ha
				// aplicado en su totalidad
				prescription.setDocStatus(prescription.completeLines(planMed));
				value = prescription.save();
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, e.getMessage(), e);
			value = false;
		} finally {
			if (value) {
				slog.log(Level.INFO, "actualizaPlan :  OK ");
			} else {
				slog.log(Level.SEVERE, "actualizaPlan :  failure ");
			}
		}

		return value;
	}

	/**
	 * Crea el listado
	 * 
	 * @param pLstProduct
	 * @return
	 */
	protected static List<ServicioView> createServicioView(
			final Properties ctx, final List<MProduct> pLstProduct) {

		final List<ServicioView> lst = new ArrayList<ServicioView>();

		// Si solo existe una lista de productos
		if (pLstProduct != null) {

			// listado con los servicios
			for (int i = 0; i < pLstProduct.size(); i++) {
				ServicioView serv = null;
				if (pLstProduct.get(i).getServView() == null) {
					serv = new ServicioView(ctx, pLstProduct.get(i));
				} else {
					serv = pLstProduct.get(i).getServView();
				}
				serv.setSurtir(!pLstProduct.get(i).isExternal());
				serv.setTodayService(pLstProduct.get(i).isTodayService());
				serv.setExmeOrderSetId(pLstProduct.get(i).getEXME_OrderSet_ID());
				serv.setProcedure(true);
				lst.add(serv);
			}
		}

		return lst;
	}

	/**
	 * Lista de aplicacion de medicamento
	 * 
	 * @param ctx
	 * @param plan
	 * @param line
	 * @return
	 */
	protected static List<ServicioView> createServicioView(
			final Properties ctx, final MPlanMed plan, final MPlanMedLine line) {
		final List<ServicioView> lst = new ArrayList<ServicioView>();
		lst.add(new ServicioView(ctx, plan, line, 0));
		return lst;
	}

	/**
	 * Lista de aplicacion de vacuna
	 * 
	 * @param ctx
	 * @param plan
	 * @param line
	 * @return
	 */
	protected static List<ServicioView> createServicioView(
			final Properties ctx, final MEXMEVacuna vaccine) {
		final List<ServicioView> lst = new ArrayList<ServicioView>();
		lst.add(new ServicioView(ctx, vaccine));
		return lst;
	}

}