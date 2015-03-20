package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.ActPacienteIndView;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMEConfigPre;
import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MPrecios;
import org.compiere.model.ModelError;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * Clase que define el proceso de aplicacion de servicios
 * 
 * @author Expert
 * 
 */
public class ServicesDelivered {

	/** log */
	private static CLogger log;
	/** contexto */
	private Properties ctx;
	/** listado de mensajes errores o avisos */
	private List<ModelError> errores;
	/** constante de mensaje de error */
	public static final String ERROR_SURTIR = "surtPedidoAction.error.surtir";
	/** constante de mensaje de error */
	public static final String ERROR_SURTIR2 = "error.servicios.surtir";
	/** listado de indicacion medicas */
	private List<ActPacienteIndView> detail;
	/** edicion */
	private boolean editable;
	/** */
	private boolean pending = false;
	/** encabezado a aplicar */
	private MEXMEActPacienteIndH actPacIndH;
	/** create Charge */
//	private CreateCharge createCharge;

	/**
	 * Constructor
	 * 
	 * @param pCtx
	 */
	public ServicesDelivered(Properties pCtx) {
		log = CLogger.getCLogger(ServicesDelivered.class);
		ctx = pCtx;
		errores = new ArrayList<ModelError>();
	}

//	/**
//	 * Complete the order
//	 * @deprecated no usado
//	 */
//	public static boolean surtir(Properties pCtx, boolean showTestRes, MEXMEActPacienteIndH actPacIndH,
//			List<ActPacienteIndView> detalle) {
//		return new ServicesDelivered(pCtx).surtir(actPacIndH, detalle, null);
//	}

	/**
	 * Complete the order
	 */
	public static boolean surtir(Properties pCtx, boolean showTestRes, MEXMEActPacienteIndH actPacIndH,
			List<ActPacienteIndView> detalle, String pTrxName) {
		return new ServicesDelivered(pCtx).surtir(actPacIndH, detalle, pTrxName);
	}
	/** @deprecated use {@link #surtir(MEXMEActPacienteIndH, List, String)}*/
	public boolean surtir(boolean showTestRes, MEXMEActPacienteIndH actPacIndH, List<ActPacienteIndView> detalle, String pTrxName) {
		return surtir(actPacIndH, detalle, pTrxName);
	}
	
	/**
	 * Complete the order
	 */
	public boolean surtir(MEXMEActPacienteIndH actPacIndH, List<ActPacienteIndView> detalle, String pTrxName) {
		String lTrxName = null;
		Trx trx = null;
		boolean valid = false;
		setDetail(detalle);
		setActPacIndH(actPacIndH);
//		createCharge = new CreateCharge(ctx, actPacIndH);

		try {
//			if (validate(getDetail(), showTestRes) && getActPacIndH() != null) { // No es necesario volver a iterar la lista

				pending = false;
//				final MEXMEActPaciente actPac = getActPacIndH().getActPaciente();
				final MEXMEEstServ estServ = MEXMEEstServ.getFromCtx(ctx);
				final MEXMEConfigPre configPre = MEXMEConfigPre.get(ctx, null);
				final Map<Integer, BigDecimal> aSurtir = new Hashtable<Integer, BigDecimal>();

				if (pTrxName == null) {
					trx = Trx.get(Trx.createTrxName("COInd"), true);
					lTrxName = trx.getTrxName();
				} else {
					lTrxName = pTrxName;
				}

				getActPacIndH().set_TrxName(lTrxName);
				
				int countPending = getActPacIndH().getLineas().length; //getDetail().size();

				for (ActPacienteIndView view : getDetail()) {
					if (view == null || view.getModel() == null) {
						continue;
					}
					MEXMEActPacienteInd model = view.getModel();
					model.set_TrxName(lTrxName);
					// si es nuevo, se guarda y continua
					// <a href="http://control.ecaresoft.com/card/635/">Card <b>#635</b> <i>Ancillary orders: Editing and Scheduling</i></a>
					if (model.is_new()) {
						if (!model.save()) {
							throw new MedsysException();// guardar si es nuevo .- Lama
						}
						// Actualiza el encabezado con la nueva línea
						getActPacIndH().setTotalLines(getActPacIndH().getTotalLines().add(model.getLineNetAmt()));
						getActPacIndH().setGrandTotal(
							getActPacIndH().getGrandTotal().add(model.getLineNetAmt().add(model.getTotalImp())));
						continue;
					}
					// si esta como completado
					// valida uqe la cantidad a surtir sea mayor a 0
					if (MEXMEActPacienteInd.ESTATUS_CompletedService.equals(view.getAccion()) && view.getCantSurtir() > 0) {
						// surte la linea
						if (!surtirLine(view, estServ, configPre, aSurtir)) {
							throw new MedsysException();
						}
						// revisa que no se haya quedado pendiente
						if (// view.isSurtir() && //TODO: no se usa !!??
						view.isSurtido() && // si no esta surtido
							MEXMEActPacienteInd.ESTATUS_CompletedService.equals(model.getEstatus())) { // si no quedo completo
							countPending--;
						}
						continue;
					}
					// si esta como cancelado
					if (MEXMEActPacienteInd.ESTATUS_CancelledService.equals(view.getAccion())) {
						// cancela la linea
						if (!cancelLine(view)) {
							throw new MedsysException();
						}
						// revisa que no se haya quedado pendiente
						if (MEXMEActPacienteInd.ESTATUS_CancelledService.equals(model.getEstatus())) { // si no quedo cancelado
							countPending--;
						}
						continue;
					}
					// si esta como solicitado, guardamos los cambios, y la orden esta como pendiente
					if (MEXMEActPacienteInd.ESTATUS_RequestedService.equals(view.getAccion())) {
						model.setAnotaciones(view.getAnotaciones());
						if (!model.save()) {
							throw new MedsysException();
						}
						continue;
					}
//					if (view != null && view.getCantSurtir() > 0) {
//						if (view.getAccion().equals(MEXMEActPacienteInd.ESTATUS_CompletedService) && view.isActivo()) {
//							valid = surtirLine(view, estServ, configPre, aSurtir, lTrxName);
//							if (!valid) {
//								break;
//							}
//						} else if (view.getAccion().equals(MEXMEActPacienteInd.ESTATUS_CancelledService)) {
//							// Aqui va el cierre del registro por cancelacion
//							valid = cancelLine(view, lTrxName);
//							if (!valid) {
//								break;
//							}
//						}// Aqui va el cierre de la validacion para parcial.
//					}
				} // for
				
				pending = countPending > 0;

				// si se proceso mas de una linea CANCEL / COMPLETE
				// si quedaron pendientes TODO
				if (countPending <= 0 && aSurtir.isEmpty()) {
					// RQM #3972: se cambia mensaje en ingles, por no ser claro para el usuario
					throw new MedsysException("error.servicios.cancelaciondetalle");
				} else {
					// Aqui ya se generan cargos, y se revisa si esta o no completo el
					// documento
					final String doctAction = getActPacIndH().completeIt();
					valid = !DocAction.STATUS_Invalid.equals(doctAction);
				}
				// si no ocurrio ninguna excepcion
				if (valid) {
					Trx.commit(trx, true);
					if(trx == null) {
						getActPacIndH().afterCompleteIt();
					} else {
						new MEXMEActPacienteIndH(ctx, getActPacIndH().get_ID(), null).afterCompleteIt();
					}
				} else {
					Trx.rollback(trx, true);
				}
				
//				if (valid) {
//					// Aqui se valida si entra a salvar el encabezado producto
//					// de que se terminaron sus lineas
//					for (ActPacienteIndView view : getDetail()) {
//						// Que este activo y que este solicitado y que se surta
//						// aqui y que no este aun surtido
//						pending =
//							view.isActivo() && MEXMEActPacienteInd.ESTATUS_RequestedService.equals(view.getAccion()) && view.isSurtir()
//								&& !view.isSurtido();// False
//						if (pending) {
//							break;
//						}
//					}
//
//					if (pending) {
//						for (ActPacienteIndView view : getDetail()) {
//							// que no este surtido y que se surta aqui
//							if (!view.isSurtido()) {
//								// Se pone en proceso el documento
//								getActPacIndH().setDocStatus(DocAction.STATUS_InProgress);
//								break;
//							}
//						}
//						valid = getActPacIndH().save();
//						if (!valid) {
//							errores.add(new ModelError(ModelError.TIPOERROR_Error, ERROR_SURTIR2));
//						}
//					} else {
//						if (aSurtir.isEmpty()) { // Si se cancela el detalle
//							valid = false;// RQM #3972: se cambia mensaje en ingles, por no ser claro para el usuario
//							errores.add(new ModelError(ModelError.TIPOERROR_Error, "error.servicios.cancelaciondetalle"));
//						} else {
//							valid = process(actPac, estServ, aSurtir, lTrxName);
//						}
//					}
//				}
//				// Aqui termina la validacion del encabezado
//				if (valid) {
//					Trx.commit(trx, true);
//				} else {
//					Trx.rollback(trx, true);
//				}

//			} // validate

		} catch (Exception e) {
			Trx.rollback(trx, true);
			log.log(Level.SEVERE, "save", e);
			errores.add(new ModelError(ModelError.TIPOERROR_Error, e.getMessage()));
		} finally {
			Trx.close(trx, true);
			if(trx != null) {
				getActPacIndH().set_TrxName(null);
			}
			if (valid) {
				setEditable(false);
			}
		}
		return valid;
	}

//	/**
//	 * Validate before complete
//	 * 
//	 * @param lstDetail
//	 *            Order Detail
//	 * @param showTestRes
//	 *            : getParentForm().isShowTestRes()
//	 * @return
//	 */
//	private boolean validate(final List<ActPacienteIndView> lstDetail, boolean showTestRes) {
//		if(lstDetail == null || lstDetail.isEmpty()){
//			return false;
//		}
//
//		double cantTotal = 0.0;
//		pending = false;
//		boolean testResult = true;
//		int pendientes = lstDetail.size();
//
//		for (ActPacienteIndView view : lstDetail) {
//			cantTotal += view.getCantSurtir();
//			if (!view.isActivo() || view.isSurtido()) {
//				continue;
//			}
//			// cancelled
//			if (MEXMEActPacienteInd.ESTATUS_CancelledService.equals(view.getAccion())) {
//				continue;
//				// completed
//			} else if (MEXMEActPacienteInd.ESTATUS_CompletedService.equals(view.getAccion())) {
//				// if test results are not set
//				if (showTestRes && MEstudiosOBX.getListaOBX(ctx, (int) view.getXXActPacienteIndID()).isEmpty()) {
//					testResult = false;
//					errores.add(new ModelError(ModelError.TIPOERROR_Error, "error.save.testResults", view.getProductoValue()));
//					break;
//				} else {
//					continue;
//				}
//				// not completed
//			} else if (lstDetail.size() > 1 && MEXMEActPacienteInd.ESTATUS_RequestedService.equals(view.getAccion())) {
//				if (view.getXXActPacienteIndID() > 0) {// Card #635 Edicion de orden desde surtido.- Lama
//					pendientes--;
//				}
//			} else {
//				pending = true;
//				break;
//			}
//		}
//
//		// Si todos estan como solicitados
//		if (pendientes == 0) {
//			pending = true;
//		} else if (pendientes < lstDetail.size()) {
//			// Si solo algunos quedan en estatus de solicitado
//			errores.add(new ModelError(ModelError.TIPOERROR_Informativo, "error.servicios.seleccion"));
//		}
//
//		if (cantTotal <= 0.0) {
//			errores.add(new ModelError(ModelError.TIPOERROR_Error, "error.traspasos.lines.qty"));
//		} else if (pending) {
//			errores.add(new ModelError(ModelError.TIPOERROR_Error, "error.servicios.seleccion"));
//		}
//
//		return !pending && cantTotal > 0.0 && testResult;
//	}

	/**
	 * Completes line
	 * 
	 * @param view
	 *            Indication View
	 * @param estServ
	 *            Service Station
	 * @param configPre
	 *            Prices configuration
	 * @param aSurtir
	 *
	 * @return
	 */
	private boolean surtirLine(final ActPacienteIndView view, //
			final MEXMEEstServ estServ, //
			final MEXMEConfigPre configPre,//
			final Map<Integer, BigDecimal> aSurtir //
			//final String trxName
			) {

		final MEXMEActPacienteInd line = view.getModel();
		 //TODO: mandar codigo a PO , completeIt

		boolean retValue = view.isSurtido();
		if (retValue) {
			aSurtir.put(line.getEXME_ActPacienteInd_ID(), line.getQtyDelivered());
		} else {
			line.setAnotaciones(view.getAnotaciones());

			if (line.getQtyOrdered().compareTo(Env.ZERO) <= 0) {
				if (view.getQtyOrdered() <= 0) {
					line.setQtyOrdered(getBigDecimal(view.getCantSurtir()));
				} else {
					line.setQtyOrdered(getBigDecimal(view.getQtyOrdered()));
				}
			}

			line.setQtyDelivered(getBigDecimal(view.getCantSurtir()));
			if (line.getDateDelivered() == null) {
				line.setDateDelivered(Env.getCurrentDate());
			}
			line.setEstatus(MEXMEActPacienteInd.ESTATUS_CompletedService);

			if(line.getTipoArea()==null || line.getEXME_Area_ID()==0){
				line.setTipoArea(line.getTipoArea()==null?estServ.getTipoArea():line.getTipoArea());
				line.setEXME_Area_ID(line.getEXME_Area_ID()==0?estServ.getEXME_Area_ID():line.getEXME_Area_ID());
			}
//			
//			String priceList;
//			if (getActPacIndH().getCtaPac() == null
//					|| getActPacIndH().getCtaPac().getEXME_CtaPac_ID() <= 0
//					|| !X_EXME_CtaPac.TIPOAREA_Ambulatory
//							.equals(getActPacIndH().getCtaPac().getTipoArea()
//									)) {// Se elije el tipo de area del tipo de paciente
//				priceList = Constantes.PVH;
//			} else {
//				priceList = Constantes.PVCE; // Proviene de Consulta Externa.
//			}
//			
			// Expert. Precios en cero
//			final MPrecios precios = PreciosVenta.precioProdVta(ctx, estServ
//					.getTipoArea(), line.getM_Product_ID(), line
//					.getQtyDelivered(), line.getC_UOM_ID(), priceList, 0, 0,
//					getActPacIndH().getM_Warehouse_Sol_ID(), getActPacIndH()
//							.getM_Warehouse_ID(), estServ.getEXME_Area_ID(),
//					getActPacIndH().getDateOrdered(), false, trxName);
			
			// si no tenia el precio calculado se busca nuevamente
			if(Env.ZERO.compareTo(line.getPriceList())==0){
				final MPrecios precios = GetPrice.getPriceActPac(line);
				retValue = precios.getPriceList().compareTo(Env.ZERO) > 0 ||
						// y no esta configurado que aplique sin precios
						(MEXMEConfigPre.aplicarCargoSinPrecio(precios.getPriceList()));
				
				 if (retValue) {
					 log.log(Level.INFO, "precio aplicación servicio: ", precios.getPriceList());
				 } else {
					 errores.add(new ModelError(ModelError.TIPOERROR_Error, "error.servicios.noPrice", line.getProduct().getName()));
					 return retValue;
				 }
			}
			
			retValue = line.save();//ya tiene transaccion
					
			// Agregar al listado
			aSurtir.put(Integer.valueOf(line.getEXME_ActPacienteInd_ID()), line
					.getQtyDelivered());
		}
		return retValue;
	}

	private BigDecimal getBigDecimal(final int number) {
		return new BigDecimal(number);
	}

	/**
	 * 
	 * @param view
	 * @return
	 */
	private boolean cancelLine(final ActPacienteIndView view) {
		final MEXMEActPacienteInd line = view.getModel();
		//line.set_TrxName(trxName);
		line.setAnotaciones(view.getAnotaciones());
//		line.setEstatus(MEXMEActPacienteInd.ESTATUS_CancelledService);
//		line.setIsActive(false);
//		final boolean valid = line.save();
		final boolean valid = line.voidIt();
		if (!valid) {
			throw new MedsysException();
//			errores.add(new ModelError(ModelError.TIPOERROR_Error,
//					"error.servicios.surtir"));
		}
		return valid;
	}

//	/**
//	 * Execute order related Workflows and create patient charges
//	 * 
//	 * @param actPac
//	 * @param estServ
//	 * @param aSurtir
//	 * @param trxName
//	 * @return
//	 * @deprecated estandarizar usando el metodo {@link MEXMEActPacienteIndH#completeIt()}
//	 */
//	private boolean process(final MEXMEActPaciente actPac,//
//			final MEXMEEstServ estServ,//
//			final Map<Integer, BigDecimal> aSurtir, final String trxName) {
//
//		boolean retValue = false;
//
//		//
//		ModelError error = getActPacIndH().statusEquipment(); 
//		if(error!=null){
//			errores.add(error);
//		}
//
//		// Verificamos que pueda aplicar el Servicio. Que no haya sido
//		// anteriormente aplicada
//		if (!MEXMEActPacienteIndH.canDeliver(ctx, getActPacIndH()
//				.getEXME_ActPacienteIndH_ID(), trxName, false)) {
//			errores.add(new ModelError(ModelError.TIPOERROR_Error,
//					"error.servicios.cantApply"));
//		}
//
//		if (!errores.isEmpty())
//			return retValue;
//
//		getActPacIndH().setData();
//		actPac.setEXME_Medico_ID(Env.getEXME_Medico_ID(ctx));
//
//		// ActPac
//		if (!actPac.save(trxName) || !getActPacIndH().save(trxName)) {
//			errores.add(new ModelError(ModelError.TIPOERROR_Error, ERROR_SURTIR2));
//		} else {
//			retValue = true;
//			try{
//				// Inventory
//				inventory(trxName, aSurtir, estServ); 
//			} catch (Exception e) {
//				log.log(Level.WARNING, "catch Inventory");
//			} 
//			// ya esta en actpacienteindh
////			MEXMEConfigOV confOV = MEXMEConfigOV.findActPac(ctx, actPac.getEXME_ActPaciente_ID(), null);
////			// Charges
////			if((getActPacIndH().getCtaPac().getCita() == null) ||
////					(getActPacIndH().getCtaPac().getCita() != null && getActPacIndH().getCtaPac().getCita().getEXME_CitaMedica_ID() > 0
////					&& confOV.isGenCharges())){
////			}
//			charges(trxName);
//		}
//		return retValue;
//	}

//	/**
//	 * Inventory
//	 * 
//	 * @param trxName
//	 * @param aSurtir
//	 * @param estServ
//	 * @deprecated use {@link MEXMEActPacienteIndH#inventory()} instead
//	 * NOTA> CUALQUIER CAMBIO REALIZADO AQUI, ACTUALIZAR EL METODO DE MEXMEACTPACIENTEINDH
//	 * @return
//	 */
//	private boolean inventory(String trxName,
//			final Map<Integer, BigDecimal> aSurtir, final MEXMEEstServ estServ) {
//		final MEXMEInOut inOut =
//			Inventory.createFromApServ(getActPacIndH(), DB.getTimestampForOrg(estServ.getCtx()), aSurtir, false, null, true, estServ, trxName);
//
//		if (inOut == null) {
//			errores.add(new ModelError(ModelError.TIPOERROR_Error, ERROR_SURTIR, MEXMEInOut.getS_ProcessMsg()));
//		} else if (DocAction.STATUS_InProgress.equals(inOut.prepareIt())) {
//			final String status = inOut.completeIt();
//			inOut.setDocStatus(status);
//			if (DocAction.STATUS_Completed.equals(status) && !inOut.save()) {
//				errores.add(new ModelError(ModelError.TIPOERROR_Error, ERROR_SURTIR, inOut.getM_processMsg()));
//			}
//		} else {
//			errores.add(new ModelError(ModelError.TIPOERROR_Error, ERROR_SURTIR, inOut.getM_processMsg()));
//		}
//		return errores.isEmpty();
//	}

//	/**
//	 * Charges
//	 * 
//	 * @param trxName
//	 * @return
//	 * @deprecated use {@link MEXMEActPacienteIndH#charges()} instead
//	 * NOTA> CUALQUIER CAMBIO REALIZADO AQUI, ACTUALIZAR EL METODO DE MEXMEACTPACIENTEINDH
//	 */
//	private void charges(String trxName) {
//		
//		// Hacemos el cargo a la cuenta paciente (si existe).
//		if (getActPacIndH().getCtaPac() == null
//				|| getActPacIndH().getCtaPac().getEXME_CtaPac_ID() <= 0 ){
//			log.log(Level.INFO, "EXME_CtaPac_ID is mandatory ");
//			return;
//		}
//		getActPacIndH().charges();//Lama
////
////		if (X_EXME_CtaPac.ENCOUNTERSTATUS_Admission.equals(getActPacIndH().getCtaPac().getEncounterStatus()) 
////				|| X_EXME_CtaPac.ENCOUNTERSTATUS_Predischarge.equals(getActPacIndH().getCtaPac().getEncounterStatus()) 
////				|| X_EXME_CtaPac.ENCOUNTERSTATUS_Discharge.equals(getActPacIndH().getCtaPac().getEncounterStatus())) {
////
////			// Crear el cargo
////			createCharge.process(trxName);
////		} else {
////			
////			// No se creo el cargo se guarda en el log
////			if(getActPacIndH().getCtaPac()!=null){
////				MEXMEActPacienteIndCgo logCgo = new MEXMEActPacienteIndCgo(ctx,0,null);
////				logCgo.setEXME_ActPacienteIndH_ID(getActPacIndH().getEXME_ActPacienteIndH_ID());
////				logCgo.setErrorMsg("Status encounter" + getActPacIndH().getCtaPac().getEncounterStatus());
////				logCgo.setType(MEXMEActPacienteIndCgo.BUSSINESSRULE);
////				logCgo.setClassname(getClass().getName());
////				logCgo.save(trxName);
////			}
////			log.log(Level.SEVERE, "Status encounter closed");
////		}
//	}

	
	public boolean isPending() {
		return this.pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public MEXMEActPacienteIndH getActPacIndH() {
		return actPacIndH;
	}

	public void setActPacIndH(final MEXMEActPacienteIndH actPacIndH) {
		this.actPacIndH = actPacIndH;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(final boolean editable) {
		this.editable = editable;
	}

	public void setDetail(final List<ActPacienteIndView> detail) {
		this.detail = detail;
	}

	public List<ActPacienteIndView> getDetail() {
		return detail;
	}

	public List<ModelError> getErrores() {
		return this.errores;
	}

	public void setErrores(List<ModelError> errores) {
		this.errores = errores;
	}
}
