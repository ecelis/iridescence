package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MCountry;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMELookupInfo;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MPlanMed;
import org.compiere.model.MPlanMedLine;
import org.compiere.model.MProduct;
import org.compiere.model.MWarehouse;
import org.compiere.model.ModelError;
import org.compiere.model.PO;
import org.compiere.model.X_EXME_PlanMedLine;
import org.compiere.model.bean.MovementLine;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ExpertException;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.ErrorList;

/**
 * Invoca la creacion del cargo a la cuenta paciente
 * 
 * @author twry
 * Modificado por Lorena Lama (Marzo 2014)
 * FIXME: requiere refactorizacion: exceso de parametros y metodos
 */
public class CreateCharge extends EnterCharge {

	/** log */
	private static CLogger logger = CLogger.getCLogger(CreateCharge.class);
	/** encabezado a aplicar */
	private transient MEXMEActPacienteIndH actPacIndH = null;
	/** Plan medico */
	private transient MPlanMed mPlanMed = null;
	/** Linea del Plan medico */
	private transient MPlanMedLine mPlanMedLine = null;
	/** Listado del detalle del plan medico */
	private List<MPlanMedLine> listPlanMedLinePendientes = new ArrayList<MPlanMedLine>();

	/**
	 * Constructor
	 * 
	 * @param pCtx
	 *            contexto
	 */
	public CreateCharge(final Properties pCtx) {
		super(pCtx);
	}

	/**
	 * Constructor
	 * 
	 * @param pCtx Contexto
	 * @param warehouseID Almacen al que se le hara el cargo
	 * @param estservID Estacion de servicio procedencia
	 * @param ctapacID Cuenta paciente del cargo
	 */
	public CreateCharge(final Properties pCtx, final int warehouseID, final int estservID, final int ctapacID) {
		super(pCtx, warehouseID, estservID, ctapacID);
	}

	/**
	 * Constructor
	 * 
	 * @param pCtx Contexto
	 * @param pActPacIndH Expediente
	 */
	public CreateCharge(final Properties pCtx, final MEXMEActPacienteIndH pActPacIndH) {
		super(pCtx, //
			pActPacIndH == null || pActPacIndH.getM_Warehouse_ID() <= 0 //
			? Env.getM_Warehouse_ID(pCtx) : pActPacIndH.getM_Warehouse_ID(), //
			Env.getEXME_EstServ_ID(pCtx),//
			pActPacIndH == null ? -1 : pActPacIndH.getEXME_CtaPac_ID());
		actPacIndH = pActPacIndH;
	}

	/**
	 * A partir de la actividad paciente (MEXMEActPacienteIndH), se crean los
	 * cargos a la cuenta paciente .Es necesario el objeto MEXMEActPacienteIndH
	 * del constructor y el Contexto que son parte del constructor
	 * 
	 * 
	 * Clases: MEXMEActPacienteIndH (Servicios), MedicationSave (Medicamentos)
	 * 
	 * @param trxName
	 *            Nombre de transaccion (REQUERIDO)
	 * @return regresa _DocStatus de la cuenta paciente en caso de que el String
	 *         sea null es por que No cumple las validaciones o no se genero el
	 *         cargo
	 */
	public String insertActPacIndHCharges(final String pTrxName) {
		lstCharges.clear();

		String success = null;
		if (actPacIndH == null || pTrxName == null || ctx == null) {
			log.log(Level.INFO, "No existe actPacIndH o bien pTrxName o ctx");
			return success;
		}
		// Nombre de transaccion
		actPacIndH.set_TrxName(pTrxName);
		// Lineas de la actividad paciente
		final List<MEXMEActPacienteInd> lstActPacienteInd = actPacIndH.getLineas(null, null, false);
		// Validar si existen lineas
		if (lstActPacienteInd == null || lstActPacienteInd.isEmpty()) {
			log.log(Level.INFO, "no hay lineas del expediente");

		} else {

			// Carga cada linea a la cuenta paciente
			for (MEXMEActPacienteInd mInd : lstActPacienteInd) {
				// Valida si no esta cancelada RQM #3966 .- Lama 
				// gvaldez Se cambio 24122013 ahora valida que la linea este completada y no tenga cargo generado
				if ((MEXMEActPacienteInd.ESTATUS_CompletedService.equals(mInd.getEstatus()) && mInd.getCargo() == null)
				    // si se aplica internamente o es facturable
						&& (mInd.isSurtir() || mInd.isBillable())) {
					// aplica=false, isNimboMx=false
					// Crear objeto cargo
					
					// Obligar los parametros de acuerdo a la ejecución de citas
					if(MCountry.isMexico(ctx)
							&& actPacIndH.getActPaciente().getEXME_CitaMedica_ID()>0 
							&& actPacIndH.isPhysicianServices()){
						
//						if (ctaPac == null && actPacIndH != null) {
//							ctaPac = actPacIndH.getCtaPac();
//						} 
						
						if(mInd.getQtyOrdered_Vol().compareTo(Env.ZERO)<=0){
							mInd.setQtyOrdered_Vol(mInd.getQtyOrdered()); 
						}
						
						insertActPacIndCharges(mInd, true, true, pTrxName);
						
					} else {
						insertActPacIndCharges(mInd, false, false, pTrxName);
					}
				}
			}

			// Completa la transaccion de los cargos
			if (insertAllCharges(lstCharges, /*false, false, false, updateInventory,*/ pTrxName)) {
				success = actPacIndH.getCtaPac().getDocStatus();
			}
		}

		log.log(Level.INFO, "status=" + success);
		return success;
	}

//	/**
//	 * A partir de la actividad paciente (MEXMEActPacienteIndH), se crean los
//	 * cargos a la cuenta paciente Es necesario el objeto MEXMEActPacienteIndH
//	 * del constructor El nombre de la transaccion y el Contexto del
//	 * constructor.
//	 * 
//	 * Clases: MEXMEActPacienteIndH, MedicationSave, ServicesDelivered
//	 * 
//	 * @param trxName
//	 *            Nombre de transaccion
//	 * @return null: No cumple las validaciones o no se genero el cargo
//	 */
//	public static String createNimboCharges(final Properties pCtx, final MEXMEActPacienteIndH pActPacIndH, final String pTrxName) {
//		if (pActPacIndH == null || pTrxName == null) {
//			logger.log(Level.INFO, "if (actPacIndH == null || trxName == null)");
//			return null;
//		} else {
//			MEXMEConfigOV conf = MEXMEConfigOV.findActPac(pCtx, pActPacIndH.getEXME_ActPaciente_ID(), null);
//			if (conf != null && conf.getM_Warehouse_ID() > 0) {
//				pActPacIndH.setM_Warehouse_ID(conf.getM_Warehouse_ID());
//				pActPacIndH.save(pTrxName);
//			}
//			String success = null;
//			final StringBuilder where = new StringBuilder(" EXME_ACTPACIENTEIND_ID NOT IN ");
//			where.append(" (SELECT EXME_ACTPACIENTEIND_ID FROM EXME_CTAPACDET ");
//			where.append(" WHERE EXME_ActPacienteInd_ID IS NOT NULL AND IsActive = 'Y') ");
//			where.append(" AND SURTIR = 'Y' ");
//			// Lineas de la actividad paciente
//			final List<MEXMEActPacienteInd> lstActPacienteInd = pActPacIndH.getLineas(where.toString(), null, false);
//
//			final CreateCharge createCharge = new CreateCharge(pCtx, pActPacIndH);
//			// Validar si existen lineas
//			if (lstActPacienteInd == null || lstActPacienteInd.isEmpty()) {
//				logger.log(Level.INFO, "if(lstActPacienteInd==null){");
//			} else {
//				// Carga cada linea a la cuenta paciente
//				for (MEXMEActPacienteInd mInd : lstActPacienteInd) {
//					if (!createCharge.createFromActPacInd(mInd, true, true, pTrxName)) {
//						ValueNamePair pair = CLogger.retrieveError();
//						if (pair != null) {
//							success = pair.getName();
//						}
//					}
//				}
//			}
//			logger.log(Level.INFO, "status=" + success);
//			return success;
//		}
//	}

//	/**
//	 * Crear el cargo a partir de MEXMEActPacienteInd
//	 * 
//	 * @see #charges(MEXMEActPacienteInd, boolean, boolean, String) donde (aplica==>true
//	 *      : despues de crear el cargo se procesa)
//	 * 
//	 * @param pActPacInd
//	 *            : Requerido MEXMEActPacienteInd
//	 * @param pTrxName
//	 *            : Requerido Nombre de la transaccion
//	 * @return [false> error, [true> exito
//	 * @deprecated use {@link #charges(MEXMEActPacienteInd, boolean <==(true), String)}
//	 */
//	public boolean process(final MEXMEActPacienteInd pActPacInd, final String pTrxName) {
//		return charges(pActPacInd, true, false, pTrxName);
//	}

//	/**
//	 * Crear el cargo a partir de MEXMEActPacienteInd
//	 * 
//	 * @see #charges(MEXMEActPacienteInd, boolean, boolean, String) donde
//	 *      (isNimboMx==>false)
//	 * @param pActPacInd
//	 *            : Requerido MEXMEActPacienteInd
//	 * @param aplica
//	 *            : [true> si despues de crear el cargo se procesa, solo cuando
//	 *            existe una linea a cargar
//	 * @param pTrxName
//	 *            : Requerido Nombre de la transaccion
//	 * @return [false> error, [true> exito
//	 * @deprecated use {@link #charges(MEXMEActPacienteInd, boolean <==(true), String)}
//	 */
//	public boolean charges(final MEXMEActPacienteInd pActPacInd, final boolean aplica, final String pTrxName) {
//		return charges(pActPacInd, aplica, false, pTrxName);
//	}

	/**
	 * Crear el cargo a partir de MEXMEActPacienteInd
	 * 
	 * @param pActPacInd
	 *            : Requerido (se da por un hecho que si existe)
	 *            MEXMEActPacienteInd
	 * @param aplica
	 *            : [true> si despues de crear el cargo se procesa, solo cuando
	 *            existe una linea a cargar
	 * @param isNimboMx
	 *            Inidca si crea los cargos dependiendo de la cantidad ordenada
	 *            y no de la cantidad entregada.
	 * @param pTrxName
	 *            : Requerido (se da por un hecho que si existe) Nombre de la
	 *            transaccion
	 * @return
	 */
	public boolean insertActPacIndCharges(final MEXMEActPacienteInd pActPacInd,
			final boolean aplica, final boolean pIsNimboMx,
			final String pTrxName) {
		if (actPacIndH == null) {
			actPacIndH = pActPacInd.getActPacienteIndH();
		}
		
		if (ctaPac == null && actPacIndH != null) {
			ctaPac = actPacIndH.getCtaPac();
		} 
		
		if(ctaPac!=null) {
			ctaPac.setActPacienteIndH(actPacIndH); 
		}
		
		// Define de donde es la llamada
		isNimboMx = pIsNimboMx;
		if (aplica) {
			lstCharges.clear();
		}
		boolean success = createFromActPacienteInd(pActPacInd, pTrxName);
		// Normalmente esta este metodo dentro de un ciclo, considera cuando no es asi
		if (success && aplica) {
			// success = instanceAndProcess(pActPacInd, pTrxName);
			// si despues de crear el cargo se procesa, solo cuando existe una linea a cargar
			// Crea objeto MCtaPacDet sin ID
			// Cuando existe solo una linea a cargar, se envia null como parametro
			// de ActPacienteIndH
			success = insertAllCharges(lstCharges, /* false, false, false, false,*/ pTrxName);
		}
		// else {
		// success = onlyInstance(pActPacInd, pTrxName);
		// }
		return success;
	}
	
//	/** 
//	 * si despues de crear el cargo se procesa, solo cuando existe una linea a cargar
//	 * @param pActPacInd
//	 * @param pTrxName
//	 * @return
//	 */
//	private boolean instaxnceAndProcess(final MEXMEActPacienteInd pActPacInd,
//			final String pTrxName) { //se comenta porque solo son 2 lineas diferente
//		lstCharges.clear();
//		// Crea objeto MCtaPacDet sin ID
//		boolean success = insertCharge(pActPacInd, pTrxName);
//		// Cuando existe solo una linea a cargar, se envia null como parametro
//		// de ActPacienteIndH
//		if (success) {
//			success = process(lstCharges, null, false, false, false, false, pTrxName);
//		} se comenta para simplicar codigo
//		return success;
//	}

//	/**
//	 * Solo crea el cargo como objeto
//	 * 
//	 * @param pActPacInd
//	 * @param pTrxName
//	 * @return
//	 */
//	private boolean onlyInstance(final MEXMEActPacienteInd pActPacInd,
//			final String pTrxName) {
//		// Crea objeto MCtaPacDet sin ID
//		return insertCharge(pActPacInd, pTrxName);se comenta porque solo es una linea
//	}

	/**
	 * Crear el cargo a partir de MEXMEActPacienteInd ( sin ejecutar
	 * {@link org.compiere.model.PO#save()} )
	 *  
	 * @param actInd MEXMEActPacienteInd
	 * @param trxName transaccion
	 * @return EXME_CtaPacDet nuevo para el producto de EXME_ActPacienteInd 
	 *         regresa null si {@link #findProduct(productID)} es falso
	 *         y manda llamar {@link #createCharge(CtaPacDet)}
	 */
	private boolean createFromActPacienteInd(final MEXMEActPacienteInd actInd, final String trxName) {

		boolean success = false;
		try {
			if (actInd == null || actInd.getM_Product_ID() < 1) {
				log.log(Level.INFO, "No hay linea de expediente o bien un medicamento de cargar");
			} else {
				setWarehouseID(actPacIndH.getM_Warehouse_ID() == 0 ? Env.getM_Warehouse_ID(ctx) : actPacIndH.getM_Warehouse_ID());
				setEstservID(Env.getEXME_EstServ_ID(ctx));
				setCtaPac(ctaPac);
				setActPacienteInd(actInd);
				setEXMEActPacienteIndID(actInd.getEXME_ActPacienteInd_ID(), trxName);

				// Valida el producto
				if (findProduct(actInd.getM_Product_ID())) {
					final MProduct product = new MProduct(ctx, actInd.getM_Product_ID(), null);
					//LAMA: se utiliza un solo objeto en lugar de mandar tantos parametros
					final CtaPacDet det = new CtaPacDet();
					det.setQtyOrdered(actInd.getQtyOrdered());
					det.setQtyDelivered(isNimboMx ? actInd.getQtyOrdered_Vol() : actInd.getQtyDelivered());
					det.setUoMSale(product.getC_UOM_ID());// en eMar siempre la minima
					det.setLote(actInd.getLote());
					det.setProcess(true);
					det.setDateOrder(actInd.getDateOrdered());
					det.setDateDeliv(actInd.getDateDelivered());
//					det.setPrecio(null); valor por defecto
					det.setBuscarPrecio(true);
//					det.setBillingType(MCtaPacDet.BILLINGTYPE_SingleClaim); (valor por defecto)
//					det.setProdOrTray(false); valor por defecto
					det.setAttributeSetInstanceID(-1);
					det.setActPacienteInd(actInd);
					// se crea el cargo
					final MCtaPacDet charge = createCharge(det);
//						 addCharge(	//
//							actInd.getQtyOrdered(), //
//							isNimboMx ? actInd.getQtyOrdered_Vol() : actInd
//									.getQtyDelivered(), //
//							product.getC_UOMVolume_ID(), //
//							actInd.getLote(),//
//							true,//
//							actInd.getDateOrdered(),//
//							actInd.getDateDelivered(), false//
//					// , null, true // buscar precio
//					);

					if (charge == null) {
						log.log(Level.INFO, "if (charge == null)");
						errores.add(new ModelError("error.citas.setDatosIndicaciones"));
					} else {
						charge.setEXME_ActPacienteInd_ID(actInd.getEXME_ActPacienteInd_ID());
						charge.setProductCategory(actInd.getLote());
						charge.setEXME_PlanMedLine_ID(mPlanMedLine == null ? 0 : mPlanMedLine.getEXME_PlanMedLine_ID());
						lstCharges.add(charge);
						success = true;
					}
				}
			}// fin if
		} catch (Exception e) {
			log.log(Level.SEVERE, "Insertar cargo" + e.getLocalizedMessage());
		}

		return success;
	}

	/**
	 * create Charge (crear cargos sin expediente )
	 * 
	 * @param ctx
	 * @param ctaPacId
	 * @param warehouseId
	 * @param estServId
	 * @param map
	 * @return
	 * @throws Exception
	 *             Si hubo errores al generar el cargo.
	 * @deprecated 
	 * - No debe usarse un Map, ya que en caso de existir 2 objetos con el mismo producto habria error
	 * - El objeto BeanView no cuenta con documentacion
	 * Se puede usar directamente: {@link CtaPacDet} 
	 * para llenar los datos en lugar de nombres genericos como Cadena1,Dcimal etc
	 */
	public static List<MCtaPacDet> insertChargesFromMap(final Properties ctx,
			final int ctaPacId, final int warehouseId, final int estServId,
			final HashMap<Integer, BeanView> map, boolean isProdOrTray, final Timestamp dateOrder,
			final Timestamp dateDeliv) throws Exception {

		final List<MCtaPacDet> lst = new ArrayList<MCtaPacDet>();
		// Se crea el cargo y la salida de inventario
		final CreateCharge action = new CreateCharge(ctx, warehouseId, estServId, ctaPacId);
		try {

			for (int productId : map.keySet()) {

				// Valida el producto (s)// Validar
				action.setEXMEActPacienteIndID(0);
				if (action.findProduct(productId)) {

					final BeanView data = map.get(productId);
					//LAMA: se utiliza un solo objeto en lugar de mandar tantos parametros
					CtaPacDet det = action.new CtaPacDet();
					det.setQtyOrdered(data.getDcimal());
					det.setQtyDelivered(data.getDcimal());
					det.setUoMSale(data.getInteger1());
					det.setLote(data.getCadena1());
					det.setProcess(true);
					det.setDateOrder(dateOrder);
					det.setDateDeliv(dateDeliv);
//					det.setPrecio(null); valor por defecto
					det.setBuscarPrecio(true);
//					det.setBillingType(MCtaPacDet.BILLINGTYPE_SingleClaim); valor por defecto
					det.setProdOrTray(isProdOrTray);
					det.setAttributeSetInstanceID(-1);
					
					// se crea el cargo
					final MCtaPacDet charge = action.createCharge(det);
//						action.addCharge(
//							data.getDcimal(), data.getDcimal(),
//							data.getInteger1(), data.getCadena1(), true,
//							dateOrder, dateDeliv, isProdOrTray);
					if (charge != null) {
						lst.add(charge);
					} else {
						final List<ModelError> errors = action.getErrores();
						final StringBuilder str = new StringBuilder();
						for (ModelError error : errors) {
							if (error.getParam() != null && error.getParam().length > 0) {
								str.append(Utilerias.getAppMsg(ctx, error.getMensaje(), error.getParam())).append("\n");
							} else {
								str.append(Utilerias.getMessage(ctx, null, error.getMensaje())).append("\n");
							}
						}
						if (StringUtils.isNotBlank(str.toString())) {
							logger.log(Level.SEVERE,
									"if (StringUtils.isNotBlank(str.toString())) {");
							throw new ExpertException(str.toString());
						}
					}
				}
			}

			// crea la salida de inventario
			if (!action.insertCharges(lst, null/*, false, false, false, false, null*/)) {
				final List<ModelError> errors = action.getErrores();
				final StringBuilder str = new StringBuilder();
				for (ModelError error : errors) {
					if (error.getParam() != null && error.getParam().length > 0) {
						str.append(Utilerias.getAppMsg(ctx, null, error.getMensaje(), error.getParam())).append("\n");
					} else {
						str.append(Utilerias.getMessage(ctx, null, error.getMensaje())).append("\n");
					}
				}
				if (StringUtils.isNotBlank(str.toString())) {
					logger.log(Level.SEVERE, "if (StringUtils.isNotBlank(str.toString())) {");
					throw new ExpertException(str.toString());
				}
			}

		} catch (ExpertException ee) {
			logger.log(Level.SEVERE, "} catch (ExpertException ee) {", ee);
			throw ee;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "} catch (Exception e) {", e);
			throw new ExpertException(Utilerias.getMessage(ctx, null,
					"error.guardar"));
		}

		return lst;
	}

	/**
	 * Clase WSSearchCombo (crear cargos sin expediente )
	 * 
	 * @param ctapac
	 * @param prod
	 * @param qty
	 * @param date
	 * @param trxName
	 * @return
	 * FIXME Refactorizar muchos parametros
	 */
	public static MCtaPacDet insertHCPCSCharges(final MEXMECtaPac ctapac, final MProduct prod,
			final BigDecimal precio, final String billingType, final int qty,
			final Timestamp date, final int posID, String trxName) {
		if (ctapac == null || prod == null) {
			logger.log(Level.INFO, "if(ctapac==null || prod == null){");
			return null;
		}

		try {
			CreateCharge createCharge = new CreateCharge(ctapac.getCtx(),
				Env.getM_Warehouse_ID(ctapac.getCtx()),
				Env.getEXME_EstServ_ID(ctapac.getCtx()),
				0);
//			createCharge.setWarehouseID(Env.getM_Warehouse_ID(ctapac.getCtx()));
//			createCharge.setEstservID(Env.getEXME_EstServ_ID(ctapac.getCtx()));
			createCharge.ctaPac = ctapac;

			// Valida el producto
			createCharge.setEXMEActPacienteIndID(0);
			if (createCharge.findProduct(prod.getM_Product_ID())) {

				//LAMA: se utiliza un solo objeto en lugar de mandar tantos parametros
				final CtaPacDet det = createCharge.new CtaPacDet();
				det.setQtyOrdered(new BigDecimal(qty));
				det.setQtyDelivered(new BigDecimal(qty));
				det.setUoMSale(prod.getC_UOM_ID());
				det.setProcess(true);
				det.setDateOrder(date);
				det.setDateDeliv(date);
				det.setLote("CTP/HCPCS DBsearch");
				det.setAttributeSetInstanceID(-1);
				det.setProdOrTray(false);
				
				if (precio == null) {
//					charge = addCharge(new BigDecimal(qty),
//							new BigDecimal(qty), prod.getC_UOM_ID(),
//							"CTP/HCPCS DBsearch", true, date, date, false);
					det.setPrecio(null);
					det.setBuscarPrecio(true);
					det.setBillingType(MCtaPacDet.BILLINGTYPE_SingleClaim);
				} else {
//					charge = addCharge(new BigDecimal(qty),
//							new BigDecimal(qty), prod.getC_UOM_ID(),
//							"CTP/HCPCS DBsearch", true, date, date, null, true,
//							billingType, false, -1);
					
					det.setPrecio(precio);
					det.setBuscarPrecio(false);
					det.setBillingType(billingType);
				}
				final MCtaPacDet charge = createCharge.createCharge(det);
				if(precio != null) {
					charge.setValuesToCharge(precio, date);
				}
				if (charge == null) {
					logger.log(Level.INFO, "if (charge == null)");
					createCharge.getErrores().add(new ModelError("error.citas.setDatosIndicaciones"));
				} else {
					charge.setEXME_POS_ID(posID);
					createCharge.getLstCharges().add(charge);
					if (createCharge.insertAllCharges(createCharge.getLstCharges(), /* false, false, false, false,*/ trxName)) {
						return charge;
					}
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "CreateCharge.addCharge", e);
		} finally {

		}
		return null;
	}

//	/**
//	 * Agregar al cargo fecha y precio
//	 * 
//	 * @param charge
//	 * @param priceCharges
//	 * @param dateCharges
//	 */
//	public static void setValuesToCharge(final MCtaPacDet charge, final BigDecimal priceCharges, final Timestamp dateCharges) {
//		if (charge != null) {
//			charge.setCgoProcesado(false);
//			charge.setVisible(true);
//			charge.setPrices(priceCharges != null ? priceCharges : Env.ZERO, false, false);
//			charge.setEstaLineaTienePrecio(priceCharges != null);
//			if (charge.getProducto().getProdOrg() != null) {
//				charge.setEXME_RevenueCode_ID(charge.getProducto().getProdOrg().getEXME_RevenueCode_ID());
//			}
//			// charge.setEXME_Pos_ID(placeOfService.getSelectedId());
//			charge.setDateOrdered(dateCharges);
//			charge.setDatePromised(dateCharges);
//			charge.setDateDelivered(dateCharges);
//		}
//	}

	public MPlanMed getPlanMedView() {
		return mPlanMed;
	}

	/**
	 * Plan médico
	 * 
	 * @param pPlanMed
	 */
	public void setPlanMedView(final MPlanMed pPlanMed, final MPlanMedLine line) {
		mPlanMed = pPlanMed;
	}

	public List<MPlanMedLine> getListPlanMedLinePendientes() {
		return listPlanMedLinePendientes;
	}

	public void setListPlanMedLinePendientes(
			final List<MPlanMedLine> listPlanMedLinePendientes) {
		this.listPlanMedLinePendientes = listPlanMedLinePendientes;
	}

	/**
	 * Define si actualiza el inventario o no
	 * 
	 * @param pUpdateInventory
	 */
	public void setUpdateInventory(final boolean pUpdateInventory) {
		updateInventory = pUpdateInventory;
	}

	/**
	 * Unico o primer cargo generado
	 * 
	 * @return
	 */
	public List<MCtaPacDet> getCtaPacDet() {
		// MCtaPacDet ctaPacDet = null;
		// if(lstCharges!=null && !lstCharges.isEmpty()){
		// ctaPacDet = lstCharges.get(0);
		// }
		return lstCharges;
	}

	/**
	 * getPlanMedLinePendiente Que no este aplicada y que no este relacionada a
	 * otro cargo
	 * 
	 * @param ndcID
	 * @param ctaPacID
	 * @param qty
	 * @return
	 */
	public List<MPlanMedLine> getPlanMedLinePendiente(final int ndcID,
			final int ctaPacID, final int qty, final String where,
			final String trxName) {
		listPlanMedLinePendientes.clear();

		int count = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY)
				.append(" SELECT pml.*                     ")
				.append(" FROM EXME_PlanMed pm            ")
				.append(" INNER JOIN EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID AND pml.isActive='Y'")
				.append(" WHERE pm.isActive = 'Y'         ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						MPlanMed.Table_Name, "pm"))
				.append(" AND pm.EXME_CtaPac_id = ?   ")
				.append(" AND pm.M_Product_id   = ?   ")
				// .append(" AND pml.ApliedDate IS NULL  ")
				.append(" AND pml.Estatus    IN ( ? ) ")
				// Como se cuales aplicaciones están pendientes de aplicar.
				.append(" AND pml.EXME_PlanMedLine_ID NOT IN ( SELECT EXME_PlanMedLine_ID FROM EXME_CtaPacDet WHERE isActive = 'Y' AND EXME_CtaPac_id = ? AND EXME_PlanMedLine_ID IS NOT NULL ) ")
				.append(where == null ? " " : where)
				.append(" ORDER BY pml.ProgDate ASC ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			pstmt.setInt(2, ndcID);
			// Como se cuales aplicaciones están pendientes de aplicar.
			pstmt.setString(3, X_EXME_PlanMedLine.ESTATUS_Prescribed);
			pstmt.setInt(4, ctaPacID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (count <= qty) {
					listPlanMedLinePendientes.add(new MPlanMedLine(ctx, rs,
							null));
					count++;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getFromCtaPac: " + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return listPlanMedLinePendientes;
	}

//	/**
//	 * getPlanMedLinePendiente Que no este aplicada y que no este relacionada a
//	 * otro cargo
//	 * 
//	 * @param ndcID
//	 * @param ctaPacID
//	 * @param qty
//	 * @return
//	 */
//	public static List<MPlanMedLine> getPlanMedLinePendiente(
//			final Properties ctx, final int ndcID, final int ctaPacID,
//			final String where, final String trxName) {
//		List<MPlanMedLine> list = new ArrayList<MPlanMedLine>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		final StringBuilder sql = new StringBuilder(
//				Constantes.INIT_CAPACITY_ARRAY)
//				.append(" SELECT pml.*                     ")
//				.append(" FROM EXME_PlanMed pm            ")
//				.append(" INNER JOIN EXME_PlanMedLine pml ON pm.EXME_PlanMed_ID = pml.EXME_PlanMed_ID AND pml.isActive='Y'")
//				.append(" WHERE pm.isActive = 'Y'         ")
//				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
//						MPlanMed.Table_Name, "pm"))
//				.append(" AND pm.EXME_CtaPac_id = ?   ")
//				.append(" AND pm.M_Product_id   = ?   ")
//				// .append(" AND pml.ApliedDate IS NULL  ")
//				.append(" AND pml.Estatus    IN ( ? ) ")
//				// Como se cuales aplicaciones están pendientes de aplicar.
//				.append(" AND pml.EXME_PlanMedLine_ID NOT IN ( SELECT EXME_PlanMedLine_ID FROM EXME_CtaPacDet WHERE isActive = 'Y' AND EXME_CtaPac_id = ? AND EXME_PlanMedLine_ID IS NOT NULL ) ")
//				.append(where == null ? " " : where)
//				.append(" ORDER BY pml.ProgDate ASC ");
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, ctaPacID);
//			pstmt.setInt(2, ndcID);
//			// Como se cuales aplicaciones están pendientes de aplicar.
//			pstmt.setString(3, X_EXME_PlanMedLine.ESTATUS_Prescribed);
//			pstmt.setInt(4, ctaPacID);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				list.add(new MPlanMedLine(ctx, rs, null));
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "getFromCtaPac", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//
//		return list;
//	}

	/**
	 * getPlanMedLinePendiente Que no este aplicada y que no este relacionada a
	 * otro cargo
	 * 
	 * @param ndcID
	 * @param ctaPacID
	 * @param qty
	 * @return
	 */
	public MPlanMed getPlanMedPRNPendiente(final int ndcID, final int ctaPacID,
			final String trxName) {

		MPlanMed mPlanMed = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY)
				.append(" SELECT pm.*                     ")
				.append(" FROM       EXME_PlanMed    pm   ")
				.append(" INNER JOIN EXME_PrescRXDet pd ON pm.EXME_PrescRXDet_id = pd.EXME_PrescRXDet_id AND pd.ISPRN = 'Y' ")
				.append(" WHERE pm.isActive = 'Y'         ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						MPlanMed.Table_Name, "pm"))
				.append(" AND   pm.EXME_CtaPac_ID = ?     ")
				.append(" AND   pm.M_Product_ID   = ?     ")
				.append(" AND   pm.EXME_MOTIVOCANCEL_ID is null ")
				.append(" AND   pm.EndDate >= ?     ")
				// <-- (Esto por que las de PRN se descontinuan automaticamente
				// cuando se alcanza la fecha final de la prescripcion)
				.append(" AND   pd.IsActive='Y'           ")
				// -->.
				.append(" ORDER BY pm.Created ASC         ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacID);
			pstmt.setInt(2, ndcID);
			pstmt.setTimestamp(3, Env.getCurrentDate());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				mPlanMed = new MPlanMed(ctx, rs, null);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getFromCtaPac", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return mPlanMed;
	}

//	/**
//	 * Caso de prueba segun WSSearchCombo
//	 * 
//	 * @param pCtx
//	 * @param pCtapac
//	 * @param prod
//	 * @param qty
//	 * @param date
//	 * @return
//	 */
//	public static boolean crearCargoTest1(final Properties pCtx,
//			final MEXMECtaPac pCtapac, final MProduct prod, final int qty,
//			final Timestamp date, String trxName) {
//		CreateCharge mCreateCharge = new CreateCharge(pCtx);
//		return mCreateCharge.process(pCtapac, prod, null, null, qty, date, 0,
//				trxName) != null;
//	}

//	/**
//	 * No servicios
//	 * 
//	 * @param pCtx
//	 * @param warehouseID
//	 * @param estservID
//	 * @param ctapacID
//	 * @param prodID
//	 * @param price
//	 * @return
//	 */
//	public static boolean crearCargoTest2(final Properties pCtx,
//			final int warehouseID, final int estservID, final int ctapacID,
//			final int prodID, final BigDecimal price) {
//		CreateCharge mCreateCharge = new CreateCharge(pCtx, warehouseID,
//				estservID, ctapacID);
//		mCreateCharge.setEXMEActPacienteIndID(0);
//		mCreateCharge.findProduct(prodID);
//
//		final MCtaPacDet ctaPacDet = mCreateCharge.addCharge(Env.ONE, 0, null,
//				false, Env.getCurrentDate(), Env.getCurrentDate(), price,
//				false, MCtaPacDet.BILLINGTYPE_SingleClaim, false);
//
//		List<MCtaPacDet> cargo = new ArrayList<MCtaPacDet>();
//		cargo.add(ctaPacDet);
//		if (mCreateCharge.process(cargo, null, false, false, false)) {
//			return true;
//		}
//		return false;
//	}

//	/**
//	 * 
//	 * @param pCtx
//	 * @param pActPacIndH
//	 * @param trxName
//	 * @return
//	 */
//	public static boolean crearCargoTest3(final Properties pCtx,
//			final MEXMEActPacienteIndH pActPacIndH, final String trxName) {
//		CreateCharge mCreateCharge = new CreateCharge(pCtx, pActPacIndH);
//		final String info = mCreateCharge.process(trxName);
//		if (info != null) {
//			return false;
//		}
//		return true;
//	}

//	/**
//	 * 
//	 * @param ctx
//	 * @param ctaPacId
//	 * @param warehouseId
//	 * @param estServId
//	 * @param map
//	 * @param dateOrder
//	 * @param dateDeliv
//	 * @return
//	 */
//	public static boolean crearCargoTest4(final Properties ctx,
//			final int ctaPacId, final int warehouseId, final int estServId,
//			final HashMap<Integer, BeanView> map, final Timestamp dateOrder,
//			final Timestamp dateDeliv) {
//		List<MCtaPacDet> cargos;
//		try {
//			cargos = CreateCharge.process(ctx, ctaPacId, warehouseId,
//					estServId, map, false, dateOrder, dateDeliv);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return cargos != null;
//	}


	/**
	 * Create Charge (crear cargos sin expediente )
	 * Para los procesos de traspasos entre almacenes
	 * @param map
	 * @param isProdOrTray  si es producto o charola, 
	 * 						es decir proviene de M_Movement (sera removido)
	 *                      si valida o no existencias
	 * @return cantidad de cargos insertados
	 * @throws Exception
	 * @deprecated No debe usarse un Map, ya que en caso de existir 2 MovementLine con el mismo producto habria error
	 */
	public int insertMovementCharges(final HashMap<Integer, MMovementLine> map
			, boolean isProdOrTray) throws Exception {
		final List<MCtaPacDet> lst = addAllCharges(map, isProdOrTray);
		Trx trx = null;
		try {

			if(!lst.isEmpty()){
				trx = Trx.get(Trx.createTrxName("MvCtDt"), true);
				String str = insertMovementCharges(lst, isProdOrTray, trx.getTrxName());
				
//				if (createCharges(lst, trx.getTrxName())
//					&& inventory(lst, trx.getTrxName())) {
//					Trx.commit(trx);
//					
//				} else {
//					final String str = ModelError.getMsgError(ctx, getErrores());
					if (StringUtils.isNotBlank(str)) {
						throw new MedsysException(str);
					}
//				}
			}
		} catch (MedsysException ee) {
			throw ee;
			
		} catch (Exception e) {
			throw new MedsysException(Utilerias.getMessage(ctx, null,"error.guardar"));
			
		} finally {
			Trx.rollback(trx, true);
		}
		return lst.size();
	}
	
	/**
	 * Preparar las lineas del movimiento para poder generar cargos a cuentas paciente
	 * con ellos
	 * @param map: Mapa con los poductos y las lineas del movimiento
	 * @param isProdOrTray  si es producto o charola, 
	 * 						es decir proviene de M_Movement (sera removido)
	 *                      si valida o no existencias
	 * @return listado de cargos sin procesar
	 * @throws ExpertException si al generar el cargo existe algún error
	 * @deprecated No debe usarse un Map, ya que en caso de existir 2 MovementLine con el mismo producto habria error
	 */
	private List<MCtaPacDet> addAllCharges(final HashMap<Integer, MMovementLine> map, boolean isProdOrTray) throws ExpertException{

		final List<MCtaPacDet> lst = new ArrayList<MCtaPacDet>();
		for (int productId : map.keySet()) {
			final MMovementLine data = map.get(productId);
			// se crea el cargo
			CtaPacDet det = new CtaPacDet();
			det.setQtyOrdered(data.getBeanView().getDcimal());
			det.setQtyDelivered(data.getBeanView().getDcimal());
			det.setUoMSale(data.getOp_Uom());
			final MCtaPacDet charge = getMovementLineCharge(map.get(productId), det, isProdOrTray);
			if (charge != null) {
				lst.add(charge);
			}
//			if(data.getBeanView().getDcimal().compareTo(Env.ZERO)>0 && (data.getEXME_CtaPac_ID() > 0 || data.getMovement().getEXME_CtaPac_ID()>0)){
//				// Valida el producto (s)// Validar
//				setEXMEActPacienteIndID(0);
//				final MProduct mProd = new MProduct(ctx, productId, null);
//				if (data.getBeanView().getDcimal().compareTo(Env.ZERO)>0 && validateProduct(mProd)) {
//					
//					setProd(mProd);
//					setCtaPac(data.getEXME_CtaPac_ID() > 0?data.getCtaPac():data.getMovement().getCtaPac());
//
//					// se crea el cargo
//					final MCtaPacDet charge = addCharge(
//							data.getBeanView().getDcimal()
//							, data.getBeanView().getDcimal()
//							, data.getOp_Uom()
//							, null
//							, true
//							, data.getMovement().getDateReceived()
//							, data.getMovement().getDateReceived()
//							, null
//							, true
//							, MCtaPacDet.BILLINGTYPE_SingleClaim
//							, isProdOrTray
//							, data.getM_AttributeSetInstance_ID());
//
//					if (charge == null) {
//						final String str = ModelError.getMsgError(ctx, getErrores());
//						if (StringUtils.isNotBlank(str.toString())) {
//							log.log(Level.SEVERE,"CreateCharge[1]:"+str.toString());
//							throw new ExpertException(str.toString());
//						}
//
//					} else {
//						lst.add(charge);
//					}
//				}// Producto valido
//			}// fin si existe cuenta
		}// fin for
		return lst;
	}
	
	/**
	 * Preparar la linea del movimiento para poder generar el cargo a cuentas paciente
	 * con ellos
	 * @param movLine Linea del movimiento (obligatoria)
	 * @param det Parametros requeridos para crear el cargo
	 * @param isProdOrTray  si es producto o charola, 
	 * 						es decir proviene de M_Movement (sera removido)
	 *                      si valida o no existencias
	 * @return nuevo objeto de cargo
	 * @throws ExpertException
	 */
	public MCtaPacDet getMovementLineCharge(final MMovementLine movLine, final CtaPacDet det, boolean isProdOrTray) throws ExpertException{
		MCtaPacDet charge = null;
		if(det.getQtyDelivered().compareTo(BigDecimal.ZERO)>0 
			&& (movLine.getEXME_CtaPac_ID() > 0 || movLine.getMovement().getEXME_CtaPac_ID()>0)){
			// Valida el producto (s)// Validar
			setEXMEActPacienteIndID(0);
			final MProduct mProd = new MProduct(ctx, movLine.getM_Product_ID(), null);
			if (validateProduct(mProd)) {
				setProd(mProd);
				setCtaPac(movLine.getEXME_CtaPac_ID() > 0 ? movLine.getCtaPac() : movLine.getMovement().getCtaPac());
				
				det.setProcess(true);
				det.setDateOrder(movLine.getMovement().getDateReceived());
				det.setDateDeliv(movLine.getMovement().getDateReceived());
				det.setBuscarPrecio(true);
				det.setProdOrTray(isProdOrTray);
				det.setAttributeSetInstanceID(movLine.getM_AttributeSetInstance_ID());
				
				// se crea el cargo
				charge = createCharge(det);
				if (charge == null) {
					final String str = ModelError.getMsgError(ctx, getErrores());
					if (StringUtils.isNotBlank(str.toString())) {
						log.log(Level.SEVERE,"CreateCharge[1]:"+str.toString());
						throw new ExpertException(str.toString());
					}
				} else {
					charge.setM_Movement_ID(movLine.getM_Movement_ID());
					charge.setM_MovementLine_ID(movLine.getM_MovementLine_ID());
					charge.setAD_User_ID(movLine.getMovement().getCreatedBy());
					// si no es devolucion de charolas
					if(movLine.getMovement().getEXME_ProgQuiro_ID() <= 0
						// el almacen solicitante es diferente al del logueo
						&& Env.getM_Warehouse_ID(movLine.getCtx()) != movLine.getMovement().getM_WarehouseTo_ID()) {
						// toma la orgtrx del solicitante
						final int orgTrxRequester = MWarehouse.getOrgTrxId(movLine.getCtx(), movLine.getMovement().getM_WarehouseTo_ID());
						if(orgTrxRequester > 0) {
							charge.setAD_OrgTrx_ID(orgTrxRequester);//Ticket #07590
						}
					}
				}
			}// Producto valido
		}
		return charge;
	}
	
	/**
	 * Actualizamos totales si aplica
	 * Ticket 102478 solo USA
	 * @param extensionIDs
	 * @param trxName
	 * @throws SQLException
	 * Se mueve codigo de AbstratcCharges para poder ser reutilizado
	 */
	public void updateExtensions(List<Integer> extensionIDs, String trxName) throws SQLException {
		if (MCountry.isUSA(ctx)) {
			for (int ctaPacExtID : extensionIDs) {
				MEXMECtaPacExt ext = new MEXMECtaPacExt(ctx, ctaPacExtID, null);
				ext.updateTotals();
				if (!ext.save(trxName)) {
					getErrores().add(new ModelError(new MedsysException().getLocalizedMessage()));
				}
			}
		}
	}
	
	@Override
	public void reset(boolean all) {
		if(mapaDosis != null) {
			mapaDosis.clear();
		}
		super.reset(all);
	}
	
	/**
	 * @param prod Producto
	 * @return TRUE si el producto es un medicamento (CPOE)
	 */
	public boolean isMedication(MProduct prod) {
		return MProduct.PRODUCTCLASS_Drug.equals(prod.getProductClass())
			|| MProduct.PRODUCTCLASS_Immunization.equals(prod.getProductClass());
	}
	
	//LAMA: FIXME refactorizar codigo.... utilizado para la nueva pantalla de cargos
	private HashMap<Integer, List<MPlanMedLine>> mapaDosis = new HashMap<Integer, List<MPlanMedLine>>();
	
	/**
	 * Dosis que no este aplicada y que no este relacionada a
	 * otro cargo
	 * @param ctaPacDet Cargo a crear
	 * @param qtySelected cantidad seleccionada
	 * @return
	 * LAMA: FIXME refactorizar codigo.... utilizado para la nueva pantalla de cargos
	 */
	public List<MPlanMedLine> getPlanMedLinePendiente(MCtaPacDet ctaPacDet, int qtySelected) {
		final List<MPlanMedLine> lstDosisBD;
		final StringBuilder sql = new StringBuilder();
		if (!mapaDosis.isEmpty()) {// Dosis que estan en memoria
			sql.append(" AND pml.EXME_PlanMedLine_ID NOT IN (0 ");
			if (mapaDosis.containsKey(ctaPacDet.getM_Product_ID())) {
				for (MPlanMedLine line : mapaDosis.get(ctaPacDet.getM_Product_ID())) {
					sql.append(", ");
					sql.append(line.getEXME_PlanMedLine_ID());
				}
			}
			sql.append(") ");
		}
		lstDosisBD = getPlanMedLinePendiente(ctaPacDet.getM_Product_ID(), //
			ctaPacDet.getEXME_CtaPac_ID(), qtySelected, sql.toString(), null);

		return lstDosisBD;
	}

	/**
	 * Relaciona un cargo con una aplicacion de medicamentos
	 * @param ctaPacDet Cargo a crear
	 * @param qtySelected cantidad seleccionada
	 * @return lista de cargos de acuerdo a la cantidad de dosis
	 *  LAMA: FIXME refactorizar codigo.... utilizado para la nueva pantalla de cargos
	 */
	public List<MCtaPacDet> applyDosis(MCtaPacDet ctaPacDet, int qtySelected) {
		List<MCtaPacDet> returnValue = new ArrayList<MCtaPacDet>();
			
			final List<MPlanMedLine> lstDosisRelacionadas;
			// Dosis que estan en memoria
			if (!mapaDosis.isEmpty() && mapaDosis.containsKey(ctaPacDet.getM_Product_ID())) {
				lstDosisRelacionadas = mapaDosis.get(ctaPacDet.getM_Product_ID());
				mapaDosis.remove(ctaPacDet.getM_Product_ID());
			} else {
				lstDosisRelacionadas = new ArrayList<MPlanMedLine>();
			}
			boolean dosisPorAplicar = false;
			// Todas las dosis pendientes aun no relacionadas quitando las que estan en memoria
			List<MPlanMedLine> dosisSeleccionadas = getListPlanMedLinePendientes();
			// Agrega el cargo a la lista
			if (!dosisSeleccionadas.isEmpty()) {
				for (MPlanMedLine line : dosisSeleccionadas) {
					final MCtaPacDet cargoNew = new MCtaPacDet(ctx, 0, null);
					PO.copyValues(ctaPacDet, cargoNew);
					cargoNew.setQtyDelivered(Env.ONE);
					cargoNew.setQtyEntered(Env.ONE);
					cargoNew.setQtyOrdered(Env.ONE);
//					cargoNew.setQtyDelivered(Env.ONE);
					cargoNew.setEXME_PlanMedLine_ID(line.getEXME_PlanMedLine_ID());
					cargoNew.setPlanMedLine(line);
					cargoNew.setLineNetAmt();
					returnValue.add(cargoNew);	
					lstDosisRelacionadas.add(line);
				}
				// Si la cantidad a cargar no coincide con la cantidad de las dosis
				if (dosisSeleccionadas.size() < qtySelected) {
					final BigDecimal qty = new BigDecimal(qtySelected - dosisSeleccionadas.size());
					ctaPacDet.setQtyDelivered(qty);
					ctaPacDet.setQtyEntered(qty);
					ctaPacDet.setQtyOrdered(qty);
					// ctaPacDet.setQtyDelivered(qty);
					ctaPacDet.setEXME_PlanMedLine_ID(0);
					ctaPacDet.setPlanMedLine(null);
					ctaPacDet.setLineNetAmt();
					returnValue.add(ctaPacDet);
				}
				dosisPorAplicar = true;
			}

			if (!lstDosisRelacionadas.isEmpty()) {
				mapaDosis.put(ctaPacDet.getM_Product_ID(), lstDosisRelacionadas);
			}

			if (!dosisPorAplicar) {
				// Verificar si hay prescripciones PRN que no generan MPlanMedLine
				final MPlanMed mPlanMed = getPlanMedPRNPendiente(ctaPacDet.getM_Product_ID(), ctaPacDet.getEXME_CtaPac_ID(), null);
				if (mPlanMed != null) {
					errores.add(new ModelError(ModelError.TIPOERROR_Informativo, Utilerias.getLabel("info.cargoDosis.prn") + " "
						+ ctaPacDet.getProducto().getName(), ""));
				}
				returnValue.add(ctaPacDet);
			}
		return returnValue;
	}

	/**
	 * Para consigna - virtual, hace la solicitud/surtido y confirmacion para generar el cargo
	 * 
	 * @param movement Encabezado de la solicitud
	 * @param line Datos para creacion de M_MovementLine
	 * @param trxName Nombre de transaccion
	 * @return
	 */
	public ErrorList insertConsignCharges(final MovementLine line, final int warehouseConsign,  final String trxName) {
		final MMovement movement = new MMovement(ctx, warehouseConsign, getWarehouseID());
		// obligatorio la cuenta paciente
		movement.setEXME_CtaPac_ID(getCtaPac().getEXME_CtaPac_ID());
		movement.setTrxType(Constantes.CHARGES);
		line.setCtaPac(getCtaPac());
		line.setProduct(getProd());
		line.setUomId(getProd().getC_UOM_ID());
		line.setUomVolId(getProd().getC_UOMVolume_ID());
		
		final List<MovementLine> lines = new ArrayList<MovementLine>();
		lines.add(line);
		
		ErrorList error = MMovement.requestVirtualCharges(ctx, movement, lines, trxName);
		if (error.isEmpty() && !DocAction.STATUS_Completed.equals(movement.getDocStatus())) {
			// Pendiente esto, como mejora (pedir confirmacion en validate)
			errores.add(new ModelError(ModelError.TIPOERROR_Informativo, "msj.save.solicitud", movement.getDocumentNo()));
		}
		return error;
	}
	
	public int getConsignLocatorId(int movementId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT l.M_Locator_ID FROM M_Movement m ");
		sql.append("INNER JOIN M_Warehouse w ON (m.M_Warehouse_ID=w.M_Warehouse_ID AND w.Consigna='Y' AND w.Virtual='Y') ");
		sql.append("INNER JOIN M_MovementLine l ON (l.M_Movement_ID=m.M_Movement_ID AND m.M_Movement_ID=?) ");
		return DB.getSQLValue(null, sql.toString(), movementId);
	}
	
	
}