package org.compiere.model.bpm;


/**
 * DOCSTATUS_Drafted Cuando se crea la prescripcion DOCSTATUS_Voided Cancelado
 * DOCSTATUS_InProgress Cuando se empieza a aplicar el medicamento
 * DOCSTATUS_Invalid Cuando se suspende una prescripcion que ya se estaba dando
 * DOCSTATUS_NotApproved Cuando se hace una modificacion un registro se guarda
 * con los valores anteriores y se le asigna NA
 * 
 * @author Expert
 * 
 */
public class MDrugProgramDet {

//	private static CLogger slog = CLogger.getCLogger(MDrugProgramDet.class);
//	private List<MPlanMedLine> lstCalculo = new ArrayList<MPlanMedLine>();
//	private java.util.Date startDateValue = null;
//	private java.util.Date endDateDate = null;
//	private String endDate = null;
//	private double qtyTotPlanned2 = 0.0;
//	private boolean calculado = false;
//	private static int liDecimales = 2;
//	private MEXMEPrescription prescriptionNew = null;
//
//	public MDrugProgramDet() {
//		super();
//
//	}
//
//	public List<ModelError> saveNew(List<MPlanMed> planes,
//			BeanMedicalDecision pBeanMedDecision, MPlanMed plan) {
//		return save(planes, pBeanMedDecision, null, plan);
//	}
//
//	/**
//	 * 
//	 * @param lstMapPlan
//	 * @param plan
//	 * @param bmd
//	 * @return
//	 */
//	public List<ModelError> save(List<MPlanMed> planes,
//			BeanMedicalDecision pBeanMedDecision,
//			MEXMEPrescription prescription, MPlanMed plan) {
//
//		slog.log(Level.INFO, "****** guardar ****** ");
//
//		if (pBeanMedDecision == null)
//			slog.log(Level.INFO, "pBeanMedDecision is null ");
//
//		List<ModelError> errores = new ArrayList<ModelError>();
//		setPrescriptionNew(null);
//
//		try {
//
//			// creamos el encabezado del documento: Prescripci�n M�dica
//			if (prescription == null) {
//				prescription = new MEXMEPrescription(plan.getCtx(), 0, plan
//						.get_TrxName());
//
//				// llenamos los datos de la prescripcion
//				prescription.setDescription(plan.getDescription());
//				prescription.setEXME_CtaPac_ID(plan.getEXME_CtaPac_ID());
//			}
//			prescription
//					.setEXME_Especialidad_ID(plan.getEXME_Especialidad_ID());
//			prescription.setEXME_Medico_ID(plan.getEXME_Medico_ID());
//			prescription.setM_Warehouse_ID(plan.getM_Warehouse_ID());
//
//			// Guardamos la prescripcion
//			if (!prescription.save(plan.get_TrxName())) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"prescription.error.save"));
//				return errores;
//			}
//
//			// creamos los planes medicos para la lista de planes.
//			for (int i = 0; i < planes.size(); i++) {
//
//				planes.get(i).setEXME_Prescription_ID(
//						prescription.getEXME_Prescription_ID());
//				// if (Datos.esAlergico(plan.getCtx(),
//				// planes.get(i).getM_Product_ID(),
//				// plan.getEXME_Paciente_ID())) {
//				// errores.add(new ModelError(ModelError.TIPOERROR_Error,
//				// "error.progMed.alergico"));
//				// return errores;
//				// }
//				StringBuilder cadena = new StringBuilder("Plan ");
//				if (prescription != null)
//					cadena.append(prescription.getDocumentNo());
//
//				cadena.append(i);
//
//				MEXMEProduct product = new MEXMEProduct(plan.getCtx(), planes
//						.get(i).getM_Product_ID(), null);
//				if (planes.get(i) != null && product != null) {
//					cadena.append(product.getValue()).append(" ");
//					cadena.append(product.getName());
//				}
//
//				// Guardamos la prog. de medicamentos header
//				if (!planes.get(i).save(plan.get_TrxName())) {
//					errores.add(new ModelError(ModelError.TIPOERROR_Error,
//							"error.internacion"));
//					return errores;
//				}
//
//				// Vemos que se tengan lineas
//				if (planes.get(i).getLines().isEmpty()
//						|| planes.get(i).getLines().size() <= 0) {
//					errores.add(new ModelError(ModelError.TIPOERROR_Error,
//							"error.internacion"));
//					return errores;
//				}
//
//				// Iteramos el detalle
//				for (int j = 0; j < planes.get(i).getLines().size(); j++) {
//
//					planes.get(i).getLines().get(j).setEXME_PlanMed_ID(
//							planes.get(i).getEXME_PlanMed_ID());
//
//					if (!planes.get(i).getLines().get(j).save(
//							plan.get_TrxName())) {
//						errores.add(new ModelError(ModelError.TIPOERROR_Error,
//								"error.internacion"));
//						return errores;
//					}
//				}
//
//			}
//
//			// Actualizamos el estatus del plan de medicamento y la prescripcion
//			MBPPrescription pres = new MBPPrescription(plan.getCtx(),
//					plan.get_TrxName());
//			int val = pres.actualizaPlan(null, null, prescription);
//			if (val < 0)
//				errores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"diarioEnf.error.noSave"));
//
//			// Actualizamos el log para establecer que hay una prescripcion
//			MEXMEPrescriptionLog lst = MEXMEPrescriptionLog.get(plan.getCtx(),
//					plan.getEXME_CtaPac_ID(), X_EXME_Prescription.Table_ID,
//					null);
//
//			if (lst != null) {
//				lst.setEstatus(false);
//			} else {
//				lst = new MEXMEPrescriptionLog(plan.getCtx(), 0, plan
//						.get_TrxName());
//				lst.setAD_Table_ID(X_EXME_Prescription.Table_ID);
//				lst.setEXME_CtaPac_ID(plan.getEXME_CtaPac_ID());
//				lst.setEstatus(false);
//			}
//
//			if (lst != null) {
//				if (!lst.save(plan.get_TrxName())) {
//					errores.add(new ModelError(ModelError.TIPOERROR_Error,
//							"diarioEnf.error.noSave"));
//				}
//			}
//
//			prescriptionNew = prescription;
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getMessage());
//			if (!e.getMessage().equals("validate")) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Excepcion, e
//						.getMessage()));
//			}
//		}
//		return errores;
//	}
//
//	/**
//	 * Noelia: Copiamos la prescripcion seleccionada para crear una nueva.
//	 * Cancelamos la prescripcion de la cual se obtuvo la copia. Se recalculan
//	 * las lineas del plan de medicamento a partir de la fecha y hora actual
//	 * 
//	 * @return
//	 */
//	public List<ModelError> copy(MEXMEPrescription presOld, String startDate,
//			String startHr) {
//
//		slog.log(Level.INFO, "****** copy ****** ");
//
//		List<ModelError> errores = new ArrayList<ModelError>();
//
//		try {
//			// Prescripcion nueva, sera una copia exacta de la prescripcionOld
//			prescriptionNew = new MEXMEPrescription(presOld.getCtx(), 0, null);
//			prescriptionNew = MEXMEPrescription.copyFromMEXMEPrescription(
//					presOld, prescriptionNew);
//
//			// Obtenemos los planes de medicamento de la prescripcionOld
//			List<MPlanMed> plansMedOld = MEXMEPrescription.getPlanMed(presOld
//					.getCtx(), presOld.getEXME_Prescription_ID(), null, null);
//			presOld.setLineas(plansMedOld);
//
//			// Inicializamos los planes de medicamento y el detalle de la
//			// prescripcionNew
//			List<MPlanMed> lstProgMedNew = new ArrayList<MPlanMed>();
//			ArrayList<List<MPlanMedLine>> lstProgMedLineNew = new ArrayList<List<MPlanMedLine>>();
//
//			for (int i = 0; i < plansMedOld.size(); i++) {// ciclamos los planes
//				// viejos
//
//				// Creamos un plan de Medicamento por cada plan de Medicamento
//				// de la prescripcionOld
//				MPlanMed planMedNew = new MPlanMed(presOld.getCtx(), 0, null);
//				planMedNew = MPlanMed.copyFromPlanMed(plansMedOld.get(i),
//						planMedNew);
//				planMedNew.setName("Plan: " + (i + 1));
//				planMedNew.setIsActive(true);
//				planMedNew.setQtyTotAplied(new BigDecimal(0.0));
//				planMedNew.setDocStatus(MPlanMed.DOCSTATUS_Drafted);
//
//				// Inicio del calculo, de la lista de lineas de plan de
//				// medicamento
//				errores = calcular(presOld.getCtx(), planMedNew
//						.getM_Product_ID(), planMedNew.getIntervalo(),
//						planMedNew.getDuracion(), planMedNew.getQtyPlanned()
//								.doubleValue(), startDate, startHr, planMedNew
//								.getUOMDuracion(), planMedNew.getUOMIntervalo());
//
//				// Asignamos la relacion del Plan de Medicamento con su lista de
//				// Lineas de Plan de Medicamento
//				lstProgMedLineNew.add(i, lstCalculo);
//				planMedNew.setPlanMedLineLst(lstCalculo);// Lama
//
//				// Actualizamos el plan de medicamento con la fecha inicial y
//				// final, y agregamos a la lista.
//				planMedNew
//						.setStartDate(new Timestamp(startDateValue.getTime()));
//				planMedNew.setEndDate(new Timestamp((Constantes
//						.getSdfFechaHoraAmPm().parse(endDate)).getTime()));
//				planMedNew.setQtyTotPlanned(new BigDecimal(qtyTotPlanned2)
//						.setScale(liDecimales, BigDecimal.ROUND_HALF_UP));
//				lstProgMedNew.add(i, planMedNew);
//
//				// Reseteamos variables necesarias para el calculo.
//				endDate = null;
//				endDateDate = null;
//				qtyTotPlanned2 = 0.0;
//				calculado = false;
//				lstCalculo = new ArrayList<MPlanMedLine>();
//			}
//			prescriptionNew.setLineas(lstProgMedNew);
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getMessage());
//			if (!e.getMessage().equals("validate")) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Excepcion, e
//						.getMessage()));
//			}
//		}
//		return errores;
//
//	}
//
//	/**
//	 * Realiza los calculos de tiempo para el plan de medicamentos
//	 * 
//	 * @param mapping
//	 *            El ActionMapping empleado para seleccionar esta instancia
//	 * @param actionForm
//	 *            El bean ActionForm opcional para esta solicitud
//	 * @param request
//	 *            La solicitud HTTP que estamos procesando
//	 * @param response
//	 *            La respuesta HTTP que estamos creando
//	 */
//	public List<ModelError> calcular(Properties ctx, int M_Product_ID,
//			int interval, int period, double qtyPlanned, String startDate,
//			String startHr, String periodsUOM, String intervalUOM) {
//
//		slog.log(Level.INFO, "****** calcular ****** ");
//
//		List<ModelError> errores = new ArrayList<ModelError>();
//		int liDecimales = 2;
//		// nos idica si genero o no programacion
//		boolean genProg = false;
//
//		try {
//
//			List<MPlanMedLine> resultados = new ArrayList<MPlanMedLine>();
//
//			// Validamos que se haya proporcionado un producto.
//			if (M_Product_ID == 0)
//				errores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.traspasos.noExisteProd", String
//								.valueOf(M_Product_ID)));
//
//			// Validamos que se hayan proporcionado Intervalo, Periodo y
//			// Cantidad.
//			if (interval <= 0 || period <= 0 || qtyPlanned <= 0)
//				errores
//						.add(new ModelError(ModelError.TIPOERROR_Error,
//								"error.progmed.calcular", String
//										.valueOf(M_Product_ID)));
//
//			// Validamos que no venga en blanco la hora y fecha de inicio Lama
//			if (StringUtils.isBlank(startDate) || StringUtils.isBlank(startHr))
//				errores
//						.add(new ModelError(ModelError.TIPOERROR_Error,
//								"error.progmed.calcular", String
//										.valueOf(M_Product_ID)));
//
//			if (!errores.isEmpty())
//				return errores;
//
//			// Fecha y hora de programaci�n
//			Calendar cal = Calendar.getInstance();
//			Calendar cal2 = Calendar.getInstance();
//
//			// Intervalo sobre la programacion, tecleado por el usuario
//			int intervalo = interval;
//
//			// Periodo a cumplir, tecleado por el usuario
//			int periodo = period;
//
//			// Siempre truncamos la cantidad a enteros.
//			BigDecimal cantidad = new BigDecimal(qtyPlanned);
//
//			String lcQtyPlanned = String.valueOf(qtyPlanned);
//			String lcDecimales = lcQtyPlanned.substring(lcQtyPlanned
//					.indexOf("."), lcQtyPlanned.length());
//			liDecimales = lcDecimales.length() - 1;
//
//			cal.setTime(Constantes.getSdfFechaHoraAmPm().parse(
//					startDate + " " + startHr));// 22/04/2009 Wed Apr 22
//			// 00:00:00 GMT-05:00 2009
//			java.util.Date fechaIni = cal.getTime();// cal.get(Calendar.AM_PM);
//			startDateValue = fechaIni;// Wed Apr 22 12:45:00 GMT-05:00 2009
//			BigDecimal qtyTotPlanned = Env.ZERO;
//
//			int _periodo = 0;
//			if (periodsUOM.equalsIgnoreCase(X_EXME_PlanMed.UOMDURACION_Hours))
//				_periodo = periodo * Constantes._HR;
//			else if (periodsUOM
//					.equalsIgnoreCase(X_EXME_PlanMed.UOMDURACION_Days))
//				_periodo = periodo * Constantes._DIA;
//			else if (periodsUOM
//					.equalsIgnoreCase(X_EXME_PlanMed.UOMDURACION_Weeks))
//				_periodo = periodo * Constantes._SEM;
//			else
//				errores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.periodoIncorrecto"));
//
//			int _intervalo = 0;
//			if (intervalUOM
//					.equalsIgnoreCase(X_EXME_PlanMed.UOMINTERVALO_Minutes))
//				_intervalo = intervalo * Constantes._MIN;
//			else if (intervalUOM
//					.equalsIgnoreCase(X_EXME_PlanMed.UOMINTERVALO_Hours))
//				_intervalo = intervalo * Constantes._HR;
//			else if (intervalUOM
//					.equalsIgnoreCase(X_EXME_PlanMed.UOMINTERVALO_Days))
//				_intervalo = intervalo * Constantes._DIA;
//			else
//				errores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.intervaloIncorrecto"));
//
//			cal2.add(Calendar.DATE, _periodo / Constantes._DIA);
//
//			try {
//				if (WebEnv.DEBUG)
//					slog.log(Level.FINEST, "Division " + _periodo / _intervalo);
//			} catch (java.lang.ArithmeticException ae) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.intervaloPeriodoIncorrecto"));
//				return errores;
//			}
//
//			java.util.Date fecha = null;
//			// Alex.- Ticket 678 Correcion en el for, realizaba una iteracion de
//			// mas se quito el =
//			for (int i = 0; i < _periodo / _intervalo; i++) {
//				MPlanMedLine planMedLine = new MPlanMedLine(ctx, 0, null); // no
//				// es
//				// necesaria
//				// la
//				// transacci�n
//				// --Miguel
//				// Loera--
//				// Alex.- Ticket 1069 Asignar la fecha inicial si es la primer
//				// linea del plan.
//				planMedLine.indexStr = String.valueOf(i + 1);
//				if (i == 0) {
//					fecha = fechaIni;
//				} else {
//					cal.add(Calendar.SECOND, _intervalo);
//					fecha = Constantes.getSdfFechaHoraAmPm().parse(
//							Constantes.getSdfFechaHoraAmPm().format(
//									cal.getTime()));
//				}
//				planMedLine.setQtyPlanned(cantidad.setScale(liDecimales,
//						BigDecimal.ROUND_HALF_UP));
//				planMedLine.setProgDate(new Timestamp(fecha.getTime()));// 2009-04-23
//				// 00:30:00.0
//				qtyTotPlanned = qtyTotPlanned.add(planMedLine.getQtyPlanned());
//				planMedLine.getColumnas();
//				planMedLine.idColumn = i;
//				resultados.add(planMedLine);
//
//				genProg = true;
//			}
//
//			// Valida si genero programacion
//			if (!genProg) {
//
//				errores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.intervaloPeriodoIncorrecto"));
//
//				return errores;
//
//			}
//
//			endDate = Constantes.getSdfFechaHoraAmPm().format(cal.getTime());
//			endDateDate = cal.getTime();
//			qtyTotPlanned2 = qtyTotPlanned.doubleValue();
//			lstCalculo = resultados;
//
//			if (resultados.size() > 0)
//				calculado = true;
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getMessage());
//			if (!e.getMessage().equals("validate")) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Excepcion, e
//						.getMessage()));
//			}
//		}
//		return errores;
//
//	}
//
//	/**
//	 * Cancela una prescripcion medica
//	 * 
//	 * @param mapping
//	 *            El ActionMapping empleado para seleccionar esta instancia
//	 * @param actionForm
//	 *            El bean ActionForm opcional para esta solicitud
//	 * @param request
//	 *            La solicitud HTTP que estamos procesando
//	 * @param response
//	 *            La respuesta HTTP que estamos creando
//	 * 
//	 *            public List<ModelError> cancel(MEXMEPrescription prescription,
//	 *            List<? extends MPlanMed> lstProgMed) { slog.log(Level.INFO,
//	 *            "****** cancelar ****** ");
//	 * 
//	 *            List<ModelError> errores = new ArrayList<ModelError>(); try {
//	 * 
//	 *            prescription.setDocStatus(MEXMEPrescription.DOCSTATUS_Voided);
//	 *            prescription.setDocAction(MEXMEPrescription.DOCACTION_Void);
//	 * 
//	 *            // hacemos un save, para guardar la fecha de actualizacion if
//	 *            (!prescription.save()) { errores.add(new
//	 *            ModelError(ModelError.TIPOERROR_Error,
//	 *            "prescription.error.save")); return errores; }
//	 * 
//	 *            // Inactivamos los planes m�dicos para la lista de planes.
//	 *            for (int i = 0; i < lstProgMed.size(); i++) {
//	 * 
//	 *            lstProgMed.get(i).set_TrxName(prescription.get_TrxName());
//	 *            lstProgMed.get(i).setIsActive(false);
//	 *            lstProgMed.get(i).setDocStatus(MPlanMed.DOCSTATUS_Voided);
//	 * 
//	 *            if (!lstProgMed.get(i).save()) { errores.add(new
//	 *            ModelError(ModelError.TIPOERROR_Error, "error.internacion"));
//	 *            return errores; }
//	 * 
//	 *            // actualizacion la programacion de cada uno for (int k = 0; k
//	 *            < lstProgMed.get(i).getLines().size(); k++) {
//	 * 
//	 *            // creamos la programacion de cada uno
//	 *            lstProgMed.get(i).getLines().get(k).setIsActive(false);
//	 *            lstProgMed.get(i).getLines().get(k).set_TrxName(
//	 *            prescription.get_TrxName());
//	 * 
//	 *            if (!lstProgMed.get(i).getLines().get(k).save(
//	 *            prescription.get_TrxName())) { errores.add(new
//	 *            ModelError(ModelError.TIPOERROR_Error, "error.internacion"));
//	 *            return errores; } } }// fin for(lstProgMed)
//	 * 
//	 *            } catch (Exception e) { slog.log(Level.SEVERE,
//	 *            e.getMessage()); if (!e.getMessage().equals("validate")) {
//	 *            errores.add(new ModelError(ModelError.TIPOERROR_Excepcion, e
//	 *            .getMessage(), null)); } } return errores; }
//	 * 
//	 * 
//	 *            /** Cancela una prescripcion medica
//	 * 
//	 */
//	public List<ModelError> cancel(MPlanMed exmeProgMed) {
//		slog.log(Level.INFO, "****** cancelar ****** ");
//
//		List<ModelError> errores = new ArrayList<ModelError>();
//		try {
//			exmeProgMed.set_TrxName(exmeProgMed.get_TrxName());
//
//			// Si ya se ha empezado a aplicar el medicamento se suspende
//			if (exmeProgMed.getDocStatus().equals(
//					X_EXME_PlanMed.DOCSTATUS_InProgress))
//				exmeProgMed.setDocStatus(MPlanMed.DOCSTATUS_Invalid);
//
//			// Si aun no se aplica se cancela
//			if (exmeProgMed.getDocStatus().equals(
//					X_EXME_PlanMed.DOCSTATUS_Drafted))
//				exmeProgMed.setDocStatus(MPlanMed.DOCSTATUS_Voided);
//
//			// Guardamos el nuevo estatus del header
//			if (!exmeProgMed.save()) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.internacion"));
//				return errores;
//			}
//
//			// actualizacion la programacion de cada uno
//			for (int k = 0; k < exmeProgMed.getLines().size(); k++) {
//
//				// Inactivamos los que aun no se han aplicado
//				if (exmeProgMed.getLines().get(k).getQtyAplied().compareTo(
//						Env.ZERO) != 0) {
//
//					exmeProgMed.getLines().get(k).setIsActive(false);
//					exmeProgMed.getLines().get(k).set_TrxName(
//							exmeProgMed.get_TrxName());
//
//					if (!exmeProgMed.getLines().get(k).save(
//							exmeProgMed.get_TrxName())) {
//						errores.add(new ModelError(ModelError.TIPOERROR_Error,
//								"error.internacion"));
//						return errores;
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getMessage());
//			if (!e.getMessage().equals("validate")) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Excepcion, e
//						.getMessage()));
//			}
//		}
//		return errores;
//	}
//
//	/**
//	 * edit una prescripcion medica
//	 * 
//	 */
//	public List<ModelError> edit(MPlanMed exmeProgMed) {
//		slog.log(Level.INFO, "****** edit ****** ");
//
//		List<ModelError> errores = new ArrayList<ModelError>();
//		try {
//
//			// Inactivamos los planes m�dicos para la lista de planes.
//			exmeProgMed.set_TrxName(exmeProgMed.get_TrxName());
//			exmeProgMed.setIsActive(false);
//			exmeProgMed.setDocStatus(MPlanMed.DOCSTATUS_NotApproved);
//
//			if (!exmeProgMed.save()) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Error,
//						"error.internacion"));
//				return errores;
//			}
//
//			// actualizacion la programacion de cada uno
//			for (int k = 0; k < exmeProgMed.getLines().size(); k++) {
//
//				// creamos la programacion de cada uno
//				exmeProgMed.getLines().get(k).setIsActive(false);
//				exmeProgMed.getLines().get(k).set_TrxName(
//						exmeProgMed.get_TrxName());
//
//				if (!exmeProgMed.getLines().get(k).save(
//						exmeProgMed.get_TrxName())) {
//					errores.add(new ModelError(ModelError.TIPOERROR_Error,
//							"error.internacion"));
//					return errores;
//				}
//			}
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getMessage());
//			if (!e.getMessage().equals("validate")) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Excepcion, e
//						.getMessage()));
//			}
//		}
//		return errores;
//	}
//
//	public List<MPlanMedLine> getLstCalculo() {
//		return lstCalculo;
//	}
//
//	public void setLstCalculo(List<MPlanMedLine> lstCalculo) {
//		this.lstCalculo = lstCalculo;
//	}
//
//	public java.util.Date getStartDateValue() {
//		return startDateValue;
//	}
//
//	public void setStartDateValue(java.util.Date startDateValue) {
//		this.startDateValue = startDateValue;
//	}
//
//	public String getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(String endDate) {
//		this.endDate = endDate;
//	}
//
//	public double getQtyTotPlanned2() {
//		return qtyTotPlanned2;
//	}
//
//	public void setQtyTotPlanned2(double qtyTotPlanned2) {
//		this.qtyTotPlanned2 = qtyTotPlanned2;
//	}
//
//	public boolean isCalculado() {
//		return calculado;
//	}
//
//	public void setCalculado(boolean calculado) {
//		this.calculado = calculado;
//	}
//
//	public MEXMEPrescription getPrescriptionNew() {
//		return prescriptionNew;
//	}
//
//	public void setPrescriptionNew(MEXMEPrescription prescriptionNew) {
//		this.prescriptionNew = prescriptionNew;
//	}
//
//	public java.util.Date getEndDateDate() {
//		return endDateDate;
//	}
//
//	public void setEndDateDate(java.util.Date endDateDate) {
//		this.endDateDate = endDateDate;
//	}
//
//	/**
//	 * 
//	 * @param ctx
//	 * @param resourseKey
//	 * @param almacen
//	 * @param especialidad
//	 * @return
//	 */
//	public static List<MEXMEProduct> getMedicationList(Properties ctx,
//			StringBuilder cadena) {
//		List<MEXMEProduct> list = new ArrayList<MEXMEProduct>();
//		PreparedStatement pstmt = null;
//		ResultSet res = null;
//
//		slog.log(Level.FINEST, "MEXMEPedidosRectas.getMedicationList.sql > "
//				+ cadena.toString());
//
//		try {
//			pstmt = DB.prepareStatement(cadena.toString(), null);
//			res = pstmt.executeQuery();
//
//			while (res.next()) {
//				list.add(new MEXMEProduct(ctx, res, null));
//			}
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getLocalizedMessage(), e);
//		} finally {
//			DB.close(res, pstmt);
//		}
//
//		return list;
//
//	}
//
//	public List<ModelError> eliminar(MEXMEPrescription prescription,
//			List<? extends MPlanMed> lstProgMed) {
//		slog.log(Level.INFO, "****** eliminar ****** ");
//
//		List<ModelError> errores = new ArrayList<ModelError>();
//		try {
//
//			MProduct noSePuedeEliminar = null;
//
//			// Vemos que todos los planes esten como DR
//			for (int i = 0; i < lstProgMed.size(); i++) {
//
//				if (!lstProgMed.get(i).getDocStatus().equals(
//						X_EXME_PlanMed.DOCSTATUS_Drafted)) {
//					noSePuedeEliminar = lstProgMed.get(i).getProduct();
//				} else {
//
//					// Empezamos a eliminar las lineas del plan
//					for (int k = 0; k < lstProgMed.get(i).getLines().size(); k++) {
//						if (!lstProgMed.get(i).getLines().get(k).delete(true)) {
//							errores.add(new ModelError(
//									ModelError.TIPOERROR_Error,
//									"prescription.error.save"));
//							break;
//						}
//					}
//
//					if (!lstProgMed.get(i).delete(true)) {
//						errores.add(new ModelError(ModelError.TIPOERROR_Error,
//								"prescription.error.save"));
//						break;
//					}
//				}
//			}// fin for(lstProgMed)
//
//			// eliminamos la prescripcion
//			if (noSePuedeEliminar == null) {
//				int EXME_CtaPac_ID = prescription.getEXME_CtaPac_ID();
//				String trxName = prescription.get_TrxName();
//				Properties ctx = prescription.getCtx();
//
//				// Borrado de prescripcion
//				if (!prescription.delete(true)) {
//					errores.add(new ModelError(ModelError.TIPOERROR_Error,
//							"prescription.error.save"));
//					return errores;
//				}
//
//				// si es el unico registro en MEXMEPrescripcion
//				// borrar del Log la existencia de la prescripcion
//				MEXMEPrescription[] arreglo = MEXMEPrescription
//						.getPrescription(ctx,
//								" AND EXME_Prescription.EXME_CtaPac_ID = "
//										+ EXME_CtaPac_ID, trxName);
//
//				MEXMEPrescriptionLog log = MEXMEPrescriptionLog.get(ctx,
//						EXME_CtaPac_ID, X_EXME_Prescription.Table_ID, trxName);
//				if (log != null) {
//					if (arreglo.length > 0)
//						log.setEstatus(false);
//					else
//						log.setEstatus(true);
//
//					if (!log.save(trxName)) {
//						errores.add(new ModelError(ModelError.TIPOERROR_Error,
//								"prescription.error.save"));
//					}
//
//				}
//
//			} else {
//				errores.add(new ModelError(ModelError.TIPOERROR_Exclamacion,
//						"error.medicamentoAplicado", noSePuedeEliminar
//								.getName()));
//			}
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getMessage());
//			if (!e.getMessage().equals("validate")) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Excepcion, e
//						.getMessage()));
//			}
//		}
//		return errores;
//	}
}
