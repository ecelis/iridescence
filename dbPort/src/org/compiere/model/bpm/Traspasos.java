package org.compiere.model.bpm;

import java.util.Properties;

import org.compiere.model.MMovement;
import org.compiere.util.CLogger;

/**
 * Proceso de transpaso entre almacenes movimiento de productos
 * 
 * @author
 * @deprecated
 */
public class Traspasos {
	/** Log */
	private static CLogger log = CLogger.getCLogger(Traspasos.class);
	/** Propiedades de contexto */
	private transient Properties ctx;
	/** movimiento */
	private MMovement mov = null;
	
	private static String errorUDM = "error.udm.noExisteConversion";

	public MMovement getMov() {
		return mov;
	}

	public void setMov(final MMovement mov) {
		this.mov = mov;
	}

	public Traspasos(final Properties pCtx, final int movementID) {
		super();
		ctx = pCtx;
//		mov = MMovement.getMEXMEMov(ctx, movementID, null);
		mov = new MMovement(ctx, movementID, null);
	}

//	/**
//	 * Confirmar la solicitud que ya se ha surtido o la devolucion que ya se ha
//	 * devuelta
//	 * 
//	 * @param confirmaList
//	 *            : Lineas con las cantidades a confirmar
//	 * @param movementConfirmID
//	 *            : Id de la confirmacion
//	 * @param isSterilization
//	 *            : Si es una confirmacion de esterilizacion
//	 * @param isDevol
//	 *            : Si es una devolución
//	 * @return
//	 * @throws Exception
//	 * @deprecated revision de procesos {@link MMovementConfirm#confirm(Properties, MMovement, List, String)}
//	 */
//	public List<String> confirmComplete(final List<ConfirmaDetView> confirmaList,
//			final int movementConfirmID){
//		log.fine("***** confirmComplete ***** ");
//		// Validar los paramertos de la solicitud especificados por el usuario
//		List<String> errores = new ArrayList<String>();
//		final List<ConfirmaDetView> lines = confirmaList;
//
//		try {
//			errores = validateConfirm(mov, lines, movementConfirmID);
//
//			// Validamos que no halla sido confirmado.
//			if (mov.getDocStatus().equalsIgnoreCase(MMovement.DOCSTATUS_Completed)) {
//				errores.add(Utilerias.getLabel("error.traspasos.isConfirmado", mov.getDocumentNo()));
//			}
//
//			MConfigEC configEC = null;
//			if (errores.isEmpty()) {
//				// obtenemos el almacen al que se le carga la diferencia de
//				// inventario fisico:Noelia, no necesita trxName
//				configEC = MConfigEC.get(ctx, null);
//				if (configEC == null) {
//					errores.add(Utilerias.getLabel("error.citas.noConfigEC"));
//				}
//
//				/* Se instancia la clase que llevara a cabo el proceso de */
//				final ConfirmaDet confirmaDet = new ConfirmaDet(ctx, //
//					mov.getDocumentNo(), lines, //
//					null, // aplicar "Y" No se usa
//					null,// String.valueOf(mov.getEXME_CtaPac_ID()), No se usa
//					configEC, 0, // estserv
//					false,// ceye
//					movementConfirmID, null // patient name
//					);
//
//				// Se hace commit
//				final ArrayList<String[]> confirmaMM = confirmaDet.complete();
//
//				if (!confirmaMM.isEmpty()) {
//					// validar que el producto no se encuentre en la lista
//					for (String[] string : confirmaMM) {
//						if (string[0].equalsIgnoreCase("THROW_EXCEPTION")) {
//							throw new Exception(Utilerias.getLabel(string[1], string[2]));
//						} else {
//							errores.add(Utilerias.getLabel(string[1], string[2]));
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "-- complete : ", e);
//			errores.add(Utilerias.getLabel("error.confirma.complete", e.getMessage()));
//		}
//
//		return errores;
//	}

//	/**
//	 * 
//	 * @param mapping
//	 * @param request
//	 * @return
//	 * @deprecated revision de procesos {@link MMovementConfirm#confirm(Properties, MMovement, List, String)}
//	 * y {@link MovementLine#validateConfirm(BigDecimal)}
//	 */
//	private List<String> validateConfirm(final MMovement movement,
//			final List<ConfirmaDetView> lines, final int movementConfirmID) {
//
//		log.fine("*******WAlmacenSave- validate ****************");
//
//		final List<String> lstErrores = new ArrayList<String>();
//
//		try {
//			if (movement.getEXME_CtaPac_ID() > 0) {
//				MEXMECtaPac mCtaPac = new MEXMECtaPac(ctx, movement.getEXME_CtaPac_ID(), null);
//
//				// validamos que la cuenta paciente este activa en caso de que exista
//				// if(mCtaPac != null && mCtaPac.get_ID() != 0 &&
//				// (!MEXMECtaPac.ENCOUNTERSTATUS_Admission.equalsIgnoreCase(mCtaPac.getEncounterStatus())))
//				if (mCtaPac.getFechaCierre() != null) {
//					lstErrores.add(Utilerias.getLabel("error.traspasos.ctaPac"));
//				}
//			}
//
//			// double totalQty = 0.0;
//			// validar que exista al menos una linea
//			if (lines.isEmpty()) {
//				lstErrores.add(Utilerias.getLabel("error.traspasos.lines"));
//			} else {
//				for (ConfirmaDetView linea : lines) {
//					// final ConfirmaDetView linea = (ConfirmaDetView) lines.get(i);
//					// Validamos ctx
//					if (linea.ctx == null) {
//						linea.ctx = ctx;
//					}
//					// La unidad es de volumen o minima
//					if (linea.getProd().getC_UOMVolume_ID() == linea.getUomSel()) {
//						// Volumen
//						if (linea.getConfirmedQty_Vol().compareTo(linea.getTargetQty_Vol()) > 0) {
//							// validamos q la cant. confirmada sea menor o igual
//							// a la surtida(target)
//							lstErrores.add(Utilerias.getLabel("error.traspasos.confSurt", String.valueOf(linea.getLine())));
//						}
//					} else if (linea.getConfirmedQty().compareTo(linea.getTargetQty()) > 0) {
//						// Minima
//						// validamos q la cant. confirmada sea menor o igual
//						// a la surtida(target)
//						lstErrores.add(Utilerias.getLabel("error.traspasos.confSurt", String.valueOf(linea.getLine())));
//					}
//					// validamos q la cant. confirmada no sea negativa
//					if (linea.getConfirmedQty().compareTo(BigDecimal.ZERO) < 0) { // linea.getConfirmedQty()<0
//						lstErrores.add(Utilerias.getLabel("error.traspasos.confCero", String.valueOf(linea.getLine())));
//					}
//					final MProduct product = MProduct.get(ctx, (int) linea.getProductID());
//					// validacion de conversiones de UDM
//					if (product.getC_UOM_ID() != product.getC_UOMVolume_ID()) {
//						final MUOMConversion rates =
//							MEXMEUOMConversion.validateConversions(ctx, product.getM_Product_ID(), product.getC_UOMVolume_ID(), null);
//						if (rates == null) {
//							lstErrores.add(Utilerias.getLabel(errorUDM, product.getName()));
//							log.saveError(Utilerias.getMsg(ctx, errorUDM), product.getName());
//						}
//					}
//				}
//			}
//
//			// obtenemos los tipos de documento de tipo Mov
//			List<LabelValueBean> tipoDoc = MEXMEDocType.getTipoDocMov(ctx, null);
//
//			MDocType docType = null;
//
//			if (!tipoDoc.isEmpty()) {
//				// asignamos el id del almacen (el primero de la lista)
//				docType = new MDocType(ctx, Integer.parseInt(((LabelValueBean) tipoDoc.get(0)).getValue()), null);
//			}
//
//			// validar que el tipo de documento este en transito
//			if (docType == null) {
//				lstErrores.add(Utilerias.getLabel("error.traspasos.noTipoDoc"));
//			} else if (!docType.isInTransit()) {
//					lstErrores.add(Utilerias.getLabel("error.traspasos.tipoDoc"));
//			}
//			// /////
//			docType = null;
//			tipoDoc = null;
//
//			// informacion de la confirmacion
//			MMovementConfirm movConfirm = new MMovementConfirm(ctx, movementConfirmID, null);
//			MMovement mov = new MMovement(ctx, movConfirm.getM_Movement_ID(), null);
//
//			if (movConfirm != null && movConfirm.get_ID() != 0) {
//				// validar que el periodo para la fecha del movimiento se
//				// encuentre abierto
//				boolean isOpen = MPeriod.isOpen(ctx, mov.getMovementDate(), MDocType.DOCBASETYPE_MaterialMovement, Env.getAD_Org_ID(ctx));
//
//				if (!isOpen) {
//					lstErrores.add(Utilerias.getLabel("error.traspasos.perNoAbierto"));
//				}
//				// validar que el periodo para la fecha del confirmacion
//				// encuentre abierto
//				isOpen = MPeriod.isOpen(ctx, movConfirm.getCreated(), MDocType.DOCBASETYPE_MaterialMovement, Env.getAD_Org_ID(ctx));
//				if (!isOpen) {
//					lstErrores.add(Utilerias.getLabel("error.traspasos.perNoAbierto"));
//				}
//			}
//			movConfirm = null;
//			mov = null;
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "-- validando: ", e);
//			lstErrores.add(Utilerias.getLabel(e.getMessage()));
//		}
//
//		return lstErrores;
//
//	}

//	private List<ModelError>	errores	= new ArrayList<ModelError>();
//
//	public List<ModelError> getErrores() {
//		return errores;
//	}
//
//	public List<ModelError> setErrores(final List<ModelError> errores) {
//		this.errores = errores;
//		return errores;
//	}
//
//	public List<ModelError> setError(final List<String> erroress) {
//		for (int i = 0; i < erroress.size(); i++) {
//			this.errores.add(new ModelError(erroress.get(i)));
//		}
//		return errores;
//	}

//	/**@deprecated */
//	public boolean crearCargos(final MMovement origen, final boolean isSterilization) {
//
//		try {
//			crearCargos(isSterilization, origen, origen.getLines());
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "-- crearCargos : ", e.getMessage());
//		}
//		if (!trans.getErrores().isEmpty()) {
//			setError(trans.getErrores());
//		}
//		return getErrores().isEmpty();
//	}

//	/**
//	 * Metodo utilizado para guardar la solicitud de charolas y/o productos
//	 * tomando en cuenta ambas unidades de medida y recibiendo como parametro la
//	 * clase de modelo
//	 * 
//	 * @author rsolorzano
//	 * @param ctx
//	 * @param reorderWHId
//	 * @param almacenLogId
//	 * @param documentNo
//	 * @param ctaPacId
//	 * @param description
//	 * @param movementLines
//	 * @param priorityRule
//	 * @param progQuiroId
//	 * @param isDevol
//	 * @param validateZeroDev
//	 * @param pLstError
//	 * @return
//	 * @throws ExpertException
//	 */
//	public static MMovement guardarSolicitud(final Properties ctx,final int reorderWHId,
//			final int almacenLogId, final String documentNo, final int ctaPacId,
//			final String description,final List<MMovementLine> movementLines,
//			final String priorityRule,final int progQuiroId,final boolean isDevol,
//			boolean validateZeroDev, final List<String> pLstError) throws ExpertException {
//
//		Trx trx = null;
//		MMovement mov = null;
//
//		try {
//
//			trx = Trx.get(Trx.createTrxName("Sol"), true);
//			final String trxName = trx.getTrxName();
//
//			final MEXMEEstServ est = MEstServAlm.getEstServ(ctx, (int) (isDevol ? almacenLogId : reorderWHId), trxName);
//
//			final String almacenV = new X_M_Warehouse(ctx, almacenLogId, null).getName();
//
//			// guarda el encabezado
//			mov = AlmacenesDB.insertMovement(ctx, documentNo, isDevol, //
//				0, 0, 0, est.getAD_OrgTrx_ID(),//
//				0, 0, ctaPacId, 0, description, trxName, //
//				almacenV);
//
//			// guarda el detalle
//			final boolean success = AlmacenesDB.insertMEXMEMovementLine(ctx, movementLines, //
//				isDevol, mov, reorderWHId, //
//				almacenLogId, ctaPacId, false, trxName);
//
//			if (success) {
//				if (mov != null) {
////					mov.setWarehouseIds();
//					mov.setPriorityRule(priorityRule);
//					if (progQuiroId > 0) {
//						mov.setEXME_ProgQuiro_ID(progQuiroId);
//					}
//					if (!mov.save(trxName)) {
//						mov = null;
//						throw new ExpertException("error.traspasos.noUpdateMovPriority");
//					} else if (isDevol) {
//						pLstError.addAll(complete(ctx, mov, mov.getLines(), trx.getTrxName(), validateZeroDev));
//					}
//				}
//
//				if (pLstError == null || pLstError.isEmpty()) {
//					Trx.commit(trx);
//				} else {
//					mov = null;
//					Trx.rollback(trx);
//				}
//			} else {
//				mov = null;
//				throw new ExpertException("error.solicitudCharolas");
//			}
//
//		} catch (Exception e) {
//			mov = null;
//			if (trx != null) {
//				trx.rollback();
//			}
//			if (e instanceof ExpertException) {
//				throw (ExpertException) e;
//			}// else al log exception
//		} finally {
//			if (trx != null) {
//				trx.close();
//			}
//		}
//
//		return mov;
//	}

//	public static List<String> complete(final Properties ctx, final MMovement mov, final List<MMovementLine> lines, final String trxName,
//		boolean validateZeroDev) {
//		List<String> errores = new ArrayList<String>();
////		mov.setWarehouseIds();
//		// Validamos que no halla sido surtido.
//		// LEscamilla
//		if (mov.getDocStatus().equalsIgnoreCase(MMovement.DOCSTATUS_InProgress)) {
//			errores.add(Utilerias.getLabel("error.traspasos.isSurtido2", mov.getDocumentNo()));
//		} else {
//			errores = validate(ctx, mov, lines, validateZeroDev);
//		}
//
//		if (errores.isEmpty()) {
//
//			try {
//				SurtidoDet surtidoDet =
//					new SurtidoDet(ctx, mov.getDocumentNo(), lines, mov.getEXME_CtaPac_ID(), false, mov.get_ID(), mov.getMAlmSurt()
//						.getM_Warehouse_ID(), trxName);
//
//				List<String[]> complete = surtidoDet.complete();
//
//				if (!complete.isEmpty()) {
//					for (int i = 0; i < complete.size(); i++) {
//
//						if (complete.get(i)[0].equalsIgnoreCase("THROW_EXCEPTION")) {
//							throw new Exception(Utilerias.getLabel(complete.get(i)[1], complete.get(i)[2]));
//						} else {
//							if (!"GLOBAL_MESSAGE".equals(complete.get(i)[0])) {
//								errores.add(Utilerias.getLabel(complete.get(i)[1], complete.get(i)[2]));
//							}
//						}
//					}
//					complete = null;
//				}
//				surtidoDet = null;
//			} catch (Exception e) {
//				log.log(Level.SEVERE, "-- complete : ", e);
//				errores.add(Utilerias.getLabel("error.traspasos.completando", e.getMessage()));
//			}
//		}
//
//		return errores;
//	}

	/**
	 * Validamos la forma
	 */
//	public static List<String> validate(final Properties ctx,final MMovement mov,
//			final List<MMovementLine> lines, boolean validateZeroDev) {
//		log.log(Level.INFO, "******* validate ****************");
//		final boolean controlaStock = MEXMEMejoras.get(ctx).isControlExistencias();
//		// el conjunto de errores
//		final List<String> errores = new ArrayList<String>();
//
//		try {
//			// validamos que la cuenta paciente este activa en caso de que
//			// exista
//			if (mov.getEXME_CtaPac_ID() > 0) {
//				final Timestamp dateCl = MEXMECtaPac.getFechaCierre(ctx, mov.getEXME_CtaPac_ID(), null);
//				if (dateCl != null) {
//					errores.add(Utilerias.getLabel("error.traspasos.ctaPac"));
//				}
//			}
//
//			BigDecimal totalQty = BigDecimal.ZERO;
//
//			// validar que exista al menos una linea
//			if (lines.isEmpty()) {
//				errores.add(Utilerias.getLabel("error.traspasos.lines"));
//			} else {
//
//				for (MMovementLine linea : lines) {
//					final BigDecimal existencia =
//						MStorage.puedeSurtir(linea.getProduct().getM_Product_ID(),
//							MWarehouse.getFromLocator(ctx, linea.getM_Locator_ID(), null).getM_Warehouse_ID(), linea.getTargetQty());
//
//					// validamos que la cantidad surtida no sea negativa
//					if (linea.getTargetQty().compareTo(Env.ZERO) < 0) {
//						errores.add(Utilerias.getLabel("error.traspasos.surtCero", String.valueOf(linea.getLine())));
//
//					} else if (linea.getTargetQty().compareTo(linea.getOriginalQty()) > 0) {
//
//						// validamos q la cant. surtida(target) sea menor o
//						// igual a la solicitada(original)
//						errores.add(Utilerias.getLabel("error.traspasos.surtSolic", String.valueOf(linea.getLine())));
//
//					} else if (linea.getTargetQty().compareTo(Env.ZERO) > 0 && controlaStock && existencia.compareTo(Env.ZERO) < 0) {
//						// LRHU. Si controla stock se valida que existan
//						// productos
//						// validar que tenga existencias
//						errores.add(Utilerias.getLabel("error.encCtaPac.noExistenProd", linea.getProduct().getName()));
//					}
//
//					// validacion de conversiones de UDM
//					if (linea.getProduct().getC_UOM_ID() != linea.getProduct().getC_UOMVolume_ID()) {
//						final MUOMConversion rates =
//							MEXMEUOMConversion.validateConversions(ctx, linea.getProduct().getM_Product_ID(), linea.getProduct()
//								.getC_UOMVolume_ID(), null);
//						if (rates == null) {
//							errores.add(Utilerias.getLabel(errorUDM, linea.getProduct().getName()));
//							log.saveError(Utilerias.getMsg(ctx, errorUDM), linea.getProduct().getName());
//						}
//					}
//					// Vamos sumando la cantidad a surtir.
//					totalQty = totalQty.add(linea.getTargetQty());
//				}
//			}
//
//			if (totalQty.compareTo(BigDecimal.ZERO) <= 0 && validateZeroDev) {
//				errores.add(Utilerias.getLabel("error.traspasos.linesSurtir0"));
//			}
//
//			final List<LabelValueBean> tipoDoc = MEXMEDocType.getTipoDocMov(
//					ctx, null);
//			/*
//			 * DatosTraspasos.getTipoDocMov( Env.getContextAsInt(ctx,
//			 * "#AD_Client_ID"), Env.getContextAsInt(ctx, "#AD_Org_ID"),
//			 * Env.getContext(ctx, "#AD_Language"));
//			 */
//
//			MDocType docType = null;
//
//			if (!tipoDoc.isEmpty()) {
//				// asignamos el id del almacen (el primero de la lista)
//				docType = new MDocType(ctx, Integer.valueOf(tipoDoc.get(0).getValue()), null);
//			}
//
//			// validar que el tipo de documento este en transito
//			if (docType == null) {
//				errores.add(Utilerias.getLabel("error.traspasos.noTipoDoc"));
//			} else if (!docType.isInTransit()) {
//				errores.add(Utilerias.getLabel("error.traspasos.tipoDoc"));
//			}
//
//			// validar que el periodo se encuentre abierto
//			final boolean isOpen = MPeriod.isOpen(ctx, mov.getMovementDate(), MDocType.DOCBASETYPE_MaterialMovement, Env.getAD_Org_ID(ctx));
//
//			if (!isOpen) {
//				errores.add(Utilerias.getLabel("error.traspasos.perNoAbierto"));
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "-- validate", e);
//			errores.add(Utilerias.getLabel("error.traspasos.validate",
//					e.getMessage()));
//		}
//
//		return errores;
//	}

//	/**************************/
//	private transient ConfirmTransferOrder trans;
//
//	public ConfirmTransferOrder getTrans() {
//		return trans;
//	}

//	/**
//	 * Creacion de cargos a cuenta paciente
//	 * 
//	 * @param isSterilization
//	 *            : Esterilizacion/Quirofanos
//	 * @param mMovement
//	 *            : Movimiento entre almacenes
//	 * @param mMovementLines
//	 *            : Lineas del movimiento
//	 * @return true: si los cargos fueron un exito
//	 * @throws Exception
//	 * @deprecated
//	 */
//	public boolean crearCargos(final boolean isSterilization,
//			final MMovement mMovement, final List<MMovementLine> mMovementLines){
//		try {
//			trans = new ConfirmTransferOrder();
//			trans.crearCargos(MConfigEC.get(ctx, null), isSterilization, mMovement,
//					mMovementLines);
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "-- crearCargos : ", e.getMessage());
//		}
//		
//		return trans.getErrores().isEmpty();
//	}
}