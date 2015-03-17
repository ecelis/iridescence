package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.compiere.model.I_EXME_ActPacienteInd;
import org.compiere.model.I_M_InOutLine;
import org.compiere.model.I_M_InventoryLine;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMEBPartner;
import org.compiere.model.MEXMEConfigPre;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMEDocType;
import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MEXMEInOutStop;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMEProductClassWhs;
import org.compiere.model.MEXMEUOMConversion;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MUOM;
import org.compiere.model.MUOMConversion;
import org.compiere.model.MWarehouse;
import org.compiere.model.ModelError;
import org.compiere.model.PO;
import org.compiere.model.X_EXME_CtaPac;
import org.compiere.model.X_M_InOut;
import org.compiere.model.X_M_Inventory;
import org.compiere.model.X_M_Product;
import org.compiere.model.MInOut.ProcessesCharges;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ExpertException;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.ErrorList;

/**
 * Clase para el manejo de inventarios relacionados a los cargos de la cuenta
 * paciente y facturación
 *
 * @author twry
 */
public class Inventory {

	/** contexto */
	private transient final Properties ctx;
	/** listado de mensajes */
	private transient final List<ModelError> errores = new ArrayList<ModelError>();
	/** cuenta paciente */
	private transient int mWarehouseID = 0;
	/** */
	private transient MProduct mProduct = null;
	/** cuenta paciente */
	private transient MEXMECtaPac mCtaPac;
	/** expediente */
	public transient int EXME_ActPacienteIndH_ID = 0;
	/** Logger */
	protected transient CLogger log = CLogger.getCLogger(Inventory.class);
	/** Table que invoca */
	private transient int adTableID = 0;
	/** id Registro */
	private transient int recordID = 0;
	/** true : define si se hace una descarga de inventario */
	private transient boolean controlaExistencias = true;
	/** true : permitira inventario negativo (Mexico) false : USA */
	private transient boolean stopPending = true;
	/**
	 * true : cuando el lote no existe y no se hace la descarga de inventario se
	 * va a pending
	 */
	private transient boolean necessaryLot = false;
	/** almacen que surte */
	private transient int mWarehouseIDSurt = 0;
	/** unidad de medida con la que se hace el surtido */
	private transient int cUOMIDSurt = 0;
	/** Tal cual se solicito */
	private transient BigDecimal qtyPedido = Env.ZERO;
	/** En unidad de venta. */
	private transient BigDecimal qtyEntred = Env.ZERO;
	/** Identificador de la conversion en caso de que las udm sean distintas */
	private transient int cUOMConvID = 0;
	/** Convertimos a UOM de almacenamiento. */
	private transient BigDecimal targetQty = Env.ZERO;
	/** codigo del lote */
//	private transient String lote = null;RQM#5629
	/** Identificador del lote */
	private transient int mAttribSetInstID = 0;
	/** Process Message Estatico */
	@SuppressWarnings("unused")
	private static String s_processMsg = null;

	/**
	 * Salida de inventario
	 *
	 * @param pctx
	 *            Contexto
	 * @param mCtaPacID
	 *            Cuenta paciente
	 * @param mTableID
	 *            identificador de la tabla
	 */
	public Inventory(final Properties pctx, final int mCtaPacID, final int mTableID) {
		super();
		ctx = pctx;
		mCtaPac = new MEXMECtaPac(ctx, mCtaPacID, null);
		adTableID = mTableID;
		controlaExistencias = MEXMEMejoras.get(ctx).isControlExistencias();
	}

//	/**
//	 * Hace las validaciones necesarias para poder hacer el embarque del
//	 * producto y la afectación de inventarios
//	 *
//	 * @param mRecordID
//	 *            Numero de registro de la tabla del constructor
//	 * @param pQtyPedido
//	 *            Cantidad que solicito el usuario
//	 * @param pmProductID
//	 *            Producto
//	 * @param pcUOMID
//	 *            Unidad de medida que selecciono el usuario
//	 * @param pLote
//	 *            Codigo del lote
//	 * @param pTrxName
//	 *            Nombre de transaccion
//	 * @return true si es valida la informacion del producto para crear la
//	 *         salida de inventario
//	 */
//	public boolean valid(final int mRecordID, final BigDecimal pQtyPedido,
//			final int pmProductID, final int pcUOMID, final String pLote,
//			final boolean validarLaValidacion, final String pTrxName) {
//		return valid(mRecordID, pQtyPedido, pmProductID, pcUOMID, pLote,
//				Env.getM_Warehouse_ID(ctx), validarLaValidacion, pTrxName);
//	}

	/**
	 * Valida que el registro tenga toda la información necesaria para hacer la
	 * salida de inventario
	 *
	 * @param mRecordID
	 *            Numero de registro de la tabla del constructor
	 * @param pQtyPedido
	 *            Cantidad que solicito el usuario
	 * @param pmProductID
	 *            Producto
	 * @param pcUOMID
	 *            Unidad de medida que selecciono el usuario
	 * @param pLote
	 *            Codigo del lote
	 * @param mWarehouseID
	 * @param pTrxName
	 *            Nombre de transaccion
	 * @return true si es valida la informacion del producto para crear la
	 *         salida de inventario
	 */
	public boolean valid(final int mRecordID, final BigDecimal pQtyPedido,
			final int pmProductID, final int pcUOMID, final int mAttribSetInstID,//RQM#5629
			final int mWarehouseID, final boolean validarLaValidacion,
			final String pTrxName) {
		log.info(getClass().getName() + "@" + Integer.toHexString(hashCode()));
		errores.clear();

		boolean success = true;
		this.recordID = mRecordID;
		this.mWarehouseID = mWarehouseID;// 10001044
		this.mProduct = new MProduct(ctx, pmProductID, null);
//		this.lote = pLote;
		this.mAttribSetInstID = mAttribSetInstID;//RQM#5629
		final int cUOMID = pcUOMID <= 0 ? this.mProduct.getC_UOM_ID() : pcUOMID;
		this.qtyPedido = pQtyPedido;

		if ((validarLaValidacion)
			&& (!controlaExistencias)) {
				log.log(Level.SEVERE,
						"EXME_Mejoras.isControlaExistencias = [N] ");// no
																		// continua
				success = false;

		}

		// debe existir un almacen
		if (mWarehouseID <= 0) {
			errores.add(new ModelError(1, "error.requerido.warehouseID"));
			log.log(Level.SEVERE, "mWarehouseID <= 0");
		}

		// la cantidad no puede ser cero
		if (qtyPedido == null || qtyPedido.compareTo(Env.ZERO) == 0) {
			errores.add(new ModelError(1, "error.requerido.warehouseID"));
			log.log(Level.SEVERE,
					"qtyPedido == null || qtyPedido.compareTo(Env.ZERO) == 0");
		}

		// debe existir una unidad de medida
		if (cUOMID <= 0) {
			errores.add(new ModelError(1, "error.requerido.warehouseID"));
			log.log(Level.SEVERE, "cUOMID <= 0 ");
		}

		// el producto se debe almacenar
		if (!this.mProduct.isStocked()) {
			log.log(Level.SEVERE, "M_Product.isStocked = [N] ");
			success = false;
		}

		// se evalua si el producto maneja lotes
		if (this.mProduct.getProdOrg() != null && this.mProduct.getProdOrg().isLot()  && this.mAttribSetInstID < 0) {
//					&& (this.lote == null || this.lote.isEmpty()))){
				errores.add(new ModelError(1, "error.unLoteMinimo"));//error.requerido.warehouseID
				log.log(Level.SEVERE,
						"this.mProduct.getProductOrg().isLot()&& (this.lote == null || this.lote.isEmpty())");
		}

		// lotes RQM #5629
//		if (lote != null) {
//			try {
//
//				final MLot mLot = MLot.getProductLot(ctx,
//						mProduct.getM_Product_ID(), this.lote.trim(), null);
//
//				String sqlLote = null;
//				if (mLot == null) {
//					sqlLote = " upper(Lot) = upper(" + DB.TO_STRING(lote)
//					+ ") ";
//				} else {
//					sqlLote = " M_Lot_ID = " + mLot.getM_Lot_ID();
//				}
//				mAttribSetInstID = PO.getAllIDs(
//						I_M_AttributeSetInstance.Table_Name, sqlLote, null)[0];
//
//				log.log(Level.SEVERE, "mAttribSetInstID" + mAttribSetInstID);
//			} catch (final Exception e) {
//				if (this.lote != null && !this.lote.trim().isEmpty()) {
//					necessaryLot = true;
//				}
//				log.log(Level.SEVERE, "M_AttributeSetInstance_ID = 0 Lote:"
//						+ lote);// continua
//			}
//		}

		/*
		 * if (mProduct.getAD_Org_ID() <= 0) {
		 * log.info("M_Product.AD_Org_ID <=0 "); success = false; }
		 */

		// la clase de producto es medicamento o descartables, 14/Nov/13 ETS 05587 es indiferente la clase de producto
//		if (!mProduct.getProductClass().equals(X_M_Product.PRODUCTCLASS_Drug)
//				&& !mProduct.getProductClass().equals(
//						X_M_Product.PRODUCTCLASS_MedicalSupplies)) {
//			log.log(Level.SEVERE,
//					"!mProduct.getProductClass().equals(X_M_Product.PRODUCTCLASS_Drug)&& !mProduct.getProductClass().equals(X_M_Product.PRODUCTCLASS_MedicalSupplies");
//			success = false;
//		}

		// debe ser un item
		if (!X_M_Product.PRODUCTTYPE_Item.equalsIgnoreCase(mProduct
				.getProductType())) {
			log.log(Level.SEVERE,
					"!MProduct.PRODUCTTYPE_Item.equalsIgnoreCase(mProduct.getProductType() ");
			success = false;
		}

		// debe haber un almacen que surte
		mWarehouseIDSurt = getWarehouseSurt();
		if (mWarehouseIDSurt <= 0) {
			errores.add(new ModelError(1, "error.requerido.warehouseID"));
			log.log(Level.SEVERE, "mWarehouseIDSurt <= 0");
		}

		cUOMIDSurt = cUOMID;
		cUOMConvID = 0;
		// validacion de conversiones de UDM. Si las udm son diferentes
		if (mProduct.getC_UOM_ID() != cUOMIDSurt) {
			// conversion entre unidades
			final MUOMConversion rates = MEXMEUOMConversion.validateConversions(
					Env.getCtx(), mProduct.getM_Product_ID(), cUOMIDSurt, null);
			if (rates == null) {
				errores.add(new ModelError(1, "error.udm.noExisteConversion"));
				log.saveError(Utilerias.getMsg(Env.getCtx(), "error.udm.noExisteConversion"), mProduct.getName());
			} else {
				// Si existe conversion
				cUOMConvID = rates.getC_UOM_Conversion_ID();

				// cantidad en udm de minima
				final BigDecimal[] qtys = mProduct.qtyConversion(qtyPedido,
						cUOMIDSurt);

				// minima
				targetQty = qtys[0];// Min
			}
		} else {
			targetQty = qtyPedido;
		}

		// unidad de medida de surtido
		if (cUOMIDSurt <= 0) {
			errores.add(new ModelError(1, "error.requerido.warehouseID"));
			log.log(Level.INFO, "cUOMIDSurt <= 0 && cUOMConvID > 0");
		}

		// cantidad de que ha tecleado el usuario
		qtyEntred = qtyPedido;
		if (qtyEntred == null || qtyEntred.compareTo(Env.ZERO) == 0) {
			errores.add(new ModelError(1, "error.requerido.warehouseID"));
			log.log(Level.INFO,
					"cUOMConvID > 0 && (qtyEntred == null || qtyEntred.compareTo(Env.ZERO) ");
		}

		// cantidad de: TODO
		if (targetQty == null || targetQty.compareTo(Env.ZERO) == 0) {
			errores.add(new ModelError(1, "error.requerido.warehouseID"));
			log.log(Level.INFO,
					"cUOMConvID > 0 && targetQty == null || targetQty.compareTo(Env.ZERO) <= 0 ");
		}

		//
		if (I_EXME_ActPacienteInd.Table_ID == adTableID && this.recordID > 0) {// Record...
			final MEXMEActPacienteInd actpac = new MEXMEActPacienteInd(ctx,
					this.recordID, pTrxName);
			if (actpac != null) {
				EXME_ActPacienteIndH_ID = actpac.getEXME_ActPacienteIndH_ID();
				log.log(Level.INFO, "X_EXME_ActPacienteInd.Table_ID == adTableID && this.recordID > 0 [actpac != null]");
			}
		}

		//
		if (!errores.isEmpty()) {
			success = false;
		}
		return success;
	}

	/**
	 * Almacen que surte
	 *
	 * @return mWarehouseIDSurt
	 */
	private int getWarehouseSurt() {
		// Primera coincidencia
		final List<MEXMEProductClassWhs> lst = MEXMEProductClassWhs
				.getAllByClass(ctx, mProduct.getProductClass(), null);
		if (lst != null && !lst.isEmpty()) {
			for (int i = 0; i < lst.size(); i++) {
				mWarehouseIDSurt = lst.get(i).getM_Warehouse_ID();
			}
		}

		return mWarehouseIDSurt;
	}

	/*****************************************************************************************/
	/**
	 * Movimiento 3 posibles casos salida del almacen de login solicitar a otro
	 * almacen dejar pendiente el movimiento hasta que surta el almacen
	 *
	 * @return
	 */
	public boolean movement(final String pTrxName,final MCtaPacDet charge, boolean forceStorageUpdate) {
		boolean success = false;

		// salida del almacen de surtido
		if ((inStock(mWarehouseID, pTrxName) && !necessaryLot) || forceStorageUpdate) {
			success = localStore(mWarehouseID,  false, charge, pTrxName);
		}
	
		// Se comenta porque solo aplica para USA - Farmacia Intrahospitalaria
		// no funcional por cambios Mexico en inventario y productos - Lama
//		// normalmente en México no se permitira que el surtido quede pendiente
//		// -stopPending
//		if (!isStopPending(pTrxName) && !success) {
//
//			if (inStock(mWarehouseIDSurt) && !necessaryLot) {
//				// Se solicita al almacen de surtido
//				transfers(pTrxName);
//				log.log(Level.INFO, "transfers ...");
//				success = true;
//			} else {
//				// Queda pendiente la salida de inventario
//				pending(pTrxName);
//				log.log(Level.INFO, "pending ...");
//				success = true;
//			}
//		}

		return success;
	}

//	/**
//	 *
//	 * @param pTrxName
//	 * @return
//	 */
//	public boolean movementLocalStore(final String pTrxName) {
//		log.log(Level.INFO, "movementLocalStore ...");
//		return localStore(mWarehouseID,  false, pTrxName);
//	}

	/**
	 * Valida si hay existencias
	 *
	 * @param warehouse
	 * @return
	 */
	public boolean inStock(final int warehouse, final String pTrxName) {
		boolean inStock = true;
		BigDecimal QtyAvailable = MStorage.getQtyAvailable(warehouse, mProduct.getM_Product_ID(), mAttribSetInstID, pTrxName);
		if (QtyAvailable == null) {
			QtyAvailable = Env.ZERO;
		}
		if (targetQty.compareTo(QtyAvailable) > 0) {
			errores.add(new ModelError(false, 1,  "La cantidad de uso interno es mayor que la cantidad disponible para el almacen (" + QtyAvailable + ")"));
			log.saveError(
					"SaveError",
					"La cantidad de uso interno es mayor que la cantidad disponible para el almacen ("
							+ QtyAvailable + ")");
			inStock = false;
		}

		return inStock;
	}

//	/**
//	 * Obtiene las existencias del almacen local
//	 *
//	 * @param warehouse
//	 *            almacen de salida
//	 * @return
//	 */
//	public boolean localStore(final int warehouse, final String pTrxName) {
//		return localStore(warehouse, false, pTrxName);
//	}

	/**
	 * Realiza la salida de inventario o devolución
	 *
	 * @param warehouse
	 * @param isDevol
	 * @return
	 */
	public boolean localStore(final int warehouse, final boolean isDevol, final MCtaPacDet charge, final String pTrxName) {
		boolean success = true;
		try {

			final MInOut mInOut = saveInOut(warehouse, pTrxName); // relativo a create  shipment

			if (isDevol) {
				mInOut.setMovementType(mInOut.isSOTrx() ? X_M_InOut.MOVEMENTTYPE_CustomerReturns
						: X_M_InOut.MOVEMENTTYPE_VendorReturns);
			}

			if (!mInOut.save(pTrxName)) {
				throw new MedsysException(Utilerias.getLabel("error.save.inOut"));
			}
			final int locatorId = charge == null ? -1 :charge.getM_Locator_ID();
			final MInOutLine line = saveInOutLine(warehouse, locatorId, mInOut, pTrxName);
			
			if (!line.save(pTrxName)) {
				throw new MedsysException(Utilerias.getLabel("error.save.inOutLine"));
			}
			
			final String completed = mInOut.completeIt();
			if (!completed.equals(X_M_InOut.DOCSTATUS_Completed)) {
				throw new MedsysException(Utilerias.getLabel("error.complete.inOut"));
			}
			mInOut.setDocStatus(X_M_InOut.DOCSTATUS_Completed);

			if (mInOut.save(pTrxName)) {
				if (charge != null) {// RQM #5937 guardar la relacion .- Lama
					charge.setM_InOut_ID(line.getM_InOut_ID());// guardar la relacion .- Lama
					charge.setM_InOutLine_ID(line.getM_InOutLine_ID());
					if (!charge.save(pTrxName)) {
						log.log(Level.SEVERE, "if(!charge.save(pTrxName)){", MedsysException.getMessageFromLogger());
					}
				}
			} else {
				log.log(Level.SEVERE, "if(!m_InOut.save(pTrxName)){", MedsysException.getMessageFromLogger());
			}
			
		} catch (final Exception e) {
			success = false;
			errores.add(new ModelError(false, 1,  e.getLocalizedMessage() + MedsysException.getMessageFromLogger()));
			log.log(Level.SEVERE, e.getLocalizedMessage() + MedsysException.getMessageFromLogger(), e);
		}
		return success;
	}

//	/**
//	 * Trasfiere las existencias, desde un almacen que tenga existencia
//	 *
//	 * @return
//	 */
	// Se comenta porque solo aplica para USA - Farmacia Intrahospitalaria
	// no funcional por cambios Mexico en inventario y productos - Lama
//	public boolean transfers(final String pTrxName) {
//		boolean success = false;
//		final TransferProduct transferProduct = new TransferProduct(ctx,
//				pTrxName);
//		try {
//			// NOTA: Su propia transaccion
//			// solicitud
//			if (mCtaPac != null) {
//				transferProduct.internalTransfer(mWarehouseIDSurt,
//						mWarehouseID, mCtaPac.getEXME_CtaPac_ID(),
//						mProduct.getM_Product_ID(), targetQty.longValue(),
//						qtyPedido.longValue(), lote, Env.getAD_Org_ID(ctx),
//						mAttribSetInstID, lote);
//
//				// Salida de almacen
//				// create = true;
//				localStore(mWarehouseID, false, pTrxName);
//				success = true;
//			}
//		} catch (final Exception e) {
//			success = false;
//			log.info("transfers" + e.getMessage());// continua
//		}
//		return success;
//	}

//	/**
//	 * No hay existencias por lo que quedara pendiente el movimiento hasta que
//	 * se surta el inventario
//	 *
//	 * @param trxName
//	 * @return
//	 */
	// Se comenta porque solo aplica para USA - Farmacia Intrahospitalaria
	// no funcional por cambios Mexico en inventario y productos - Lama
//	public boolean pending(final String trxName) {
//
//		if (adTableID > 0) {
//			final MEXMEInOutStop mInOutStop = new MEXMEInOutStop(ctx, 0,
//					trxName);
//			mInOutStop.setAD_User_ID(Env.getAD_User_ID(ctx));
//			mInOutStop.setM_Product_ID(mProduct.getM_Product_ID());
//			mInOutStop.setM_Warehouse_Sol_ID(mWarehouseID);
//			mInOutStop.setM_Warehouse_ID(mWarehouseIDSurt);
//			mInOutStop.setMovementDate(new Timestamp(System.currentTimeMillis()));
//			mInOutStop.setStatus(X_M_InOut.DOCSTATUS_InProgress);
//			mInOutStop.setQtyDose(qtyEntred);// tal cual la ha tecleado el
//												// usuario
//			mInOutStop.setQtyOrdered(qtyPedido);// tal cual la ha tecleado el
//												// usuario
//			mInOutStop.setC_UOM_Conversion_ID(cUOMConvID);
//			mInOutStop.setTargetQty(targetQty);// unidad de medida minima
//			mInOutStop.setAD_Table_ID(adTableID);
//			mInOutStop.setRecord_ID(recordID);
//			mInOutStop.setLot(lote);
//			if (mCtaPac != null) {
//				mInOutStop.setEXME_CtaPac_ID(mCtaPac.getEXME_CtaPac_ID());
//				mInOutStop.setEXME_Paciente_ID(mCtaPac.getEXME_Paciente_ID());
//			}
//
//			return mInOutStop.save(trxName);
//		} else {
//			return true;
//		}
//	}

	/**
	 * Creacion del la salida de inventario
	 *
	 * @param warehouse
	 * @return
	 */
	private MInOut saveInOut(final int warehouse, final String pTrxName) {
		// Configuración de precios
		final MEXMEConfigPre config = MEXMEConfigPre.get(ctx, pTrxName);
		// Fecha de movimiento
		final Timestamp dateMovement = Env.getCurrentDate(); //new Timestamp(System.currentTimeMillis());
		// Documento
		final int C_DocType_ID = MEXMEDocType.getOfName(ctx, "MM SHIPMENT", pTrxName);
		// Salida de inventario
		MInOut mInOut = null;
		// Evento de exito
		boolean success = true;

		// Cuenta obligatorio o configuración de precios
		if (mCtaPac == null || mCtaPac.getEXME_CtaPac_ID() <= 0) {
			log.log(Level.SEVERE,
					"No hay cuenta paciente, se tomaran los datos de configuración de precios");
			if (config == null) {
				log.log(Level.SEVERE,
						"No hay cuenta paciente, ni configuración de precios");
				success = false;
			} else if (config.getC_BPartner_ID() <= 0) {
				log.log(Level.SEVERE,
						"No hay cuenta paciente, ni socio en configuración de precios");
				success = false;
			}
		}

		//
		if (success) {
			mInOut = new MInOut(ctx, 0, pTrxName);
			mInOut.setEXME_CtaPac_ID(mCtaPac == null || mCtaPac.getEXME_CtaPac_ID() <= 0 ? 0 : mCtaPac.getEXME_CtaPac_ID());
			mInOut.setEXME_ActPacienteIndH_ID(EXME_ActPacienteIndH_ID);
			mInOut.setC_BPartner_ID(mCtaPac == null || mCtaPac.getEXME_CtaPac_ID() <= 0 ? config.getC_BPartner_ID() : mCtaPac.getPaciente() == null
				|| mCtaPac.getEXME_Paciente_ID() <= 0 ? config.getC_BPartner_ID() : mCtaPac.getC_BPartner_ID());

			//
			if (mCtaPac != null && mCtaPac.getEXME_CtaPac_ID() > 0 && mCtaPac.getPaciente() != null && mCtaPac.getPaciente().getLocationPac() != null) {
				mInOut.setC_BPartner_Location_ID(mCtaPac.getPaciente().getLocationPac().getC_BPartner_Location_ID());
			} else {
				final MEXMEBPartner socio = new MEXMEBPartner(ctx, config.getC_BPartner_ID(), pTrxName);
				MBPartnerLocation location = null;
//				try {
					location = socio.getLocationPac();
//				} catch (final SQLException e) {
//					log.log(Level.SEVERE, "No hay direccion de socio" + e.getMessage());
//				}

				if (location == null) {
					log.log(Level.SEVERE, "No hay direccion de socio por lo que no se crea la salida de inventario");
				} else {
					mInOut.setC_BPartner_Location_ID(location.getC_BPartner_Location_ID());

				}
			}

			mInOut.setC_DocType_ID(C_DocType_ID);
			mInOut.setDateOrdered(dateMovement);
			mInOut.setDateAcct(dateMovement);
			mInOut.setDateReceived(dateMovement);
			mInOut.setM_Warehouse_ID(warehouse);
			mInOut.setMovementDate(dateMovement);
			mInOut.completeObject();
		}

		return mInOut;
	}

	/***
	 * Lineas de la salida de inventario
	 *
	 * @param warehouse
	 * @return
	 * @throws Exception
	 */
	private MInOutLine saveInOutLine(final int warehouse, int locatorId,
			final MInOut mInOut, final String pTrxName) throws Exception {
		final MInOutLine line = new MInOutLine(mInOut);
		line.setLine(10);
		// line.setC_UOM_ID(cUOMIDSurt);

		if (cUOMIDSurt == 0 || mProduct.getC_UOM_ID() == cUOMIDSurt) {// el
																		// cargo
																		// se
																		// encuentra
																		// en
																		// unidad
																		// minima

			line.setC_UOM_ID(cUOMIDSurt);
			line.setC_UOMVolume_ID(mProduct.getC_UOMVolume_ID());

			// la cantidad se encuentra en unidad minima, se establecen las
			// cantidades y se calculan las de volumen
			line.setQty(targetQty.abs()); // Qty - En unidad de almacenamiento
			if (qtyEntred == null || qtyEntred.compareTo(Env.ZERO) == 0) {
				qtyEntred = line.getMovementQty();
			}

			line.setQtyEntered(qtyEntred.abs());

			if (mProduct.getC_UOM_ID() == mProduct.getC_UOMVolume_ID()) { // la
																			// unidad
																			// minima
																			// y
																			// la
																			// de
																			// volumen
																			// son
																			// iguales

				line.setQtyEntered_Vol(line.getQtyEntered());
				line.setMovementQty_Vol(line.getMovementQty());
				line.setQtyEntered_Vol(line.getQtyEntered());

			} else {

				BigDecimal targetVol = MEXMEUOMConversion.convertProductTo(ctx,
						mProduct.getM_Product_ID(),
						mProduct.getC_UOMVolume_ID(), targetQty.abs(), null);
				targetVol = targetVol.setScale(
						MUOM.getPrecision(ctx, mProduct.getC_UOMVolume_ID()),
						BigDecimal.ROUND_HALF_UP);

				BigDecimal enteredVol = MEXMEUOMConversion.convertProductTo(
						ctx, mProduct.getM_Product_ID(),
						mProduct.getC_UOMVolume_ID(), line.getMovementQty(),
						null);
				enteredVol = enteredVol.setScale(
						MUOM.getPrecision(ctx, mProduct.getC_UOMVolume_ID()),
						BigDecimal.ROUND_HALF_UP);

				line.setQtyEntered_Vol(targetVol);
				line.setMovementQty_Vol(targetVol);
				line.setQtyEntered_Vol(enteredVol);

			}

		} else {

			line.setC_UOM_ID(mProduct.getC_UOM_ID());
			line.setC_UOMVolume_ID(cUOMIDSurt);

			// la cantidad se encuentra en unidad de volumen, se establecen las
			// cantidades y se calculan las de unidad minima
			line.setQtyEntered_Vol(qtyPedido.abs());
			line.setMovementQty_Vol(qtyPedido.abs());
			if (qtyEntred == null || qtyEntred.compareTo(Env.ZERO) == 0) {
				qtyEntred = line.getMovementQty_Vol();
			}

			line.setQtyEntered_Vol(qtyEntred.abs());

			// calculamos la cantidad minima
			BigDecimal targetMin = MEXMEUOMConversion.convertProductFrom(ctx,
					mProduct.getM_Product_ID(), mProduct.getC_UOMVolume_ID(),
					qtyPedido.abs(), null);
			targetMin = targetMin.setScale(
					MUOM.getPrecision(ctx, mProduct.getC_UOM_ID()),
					BigDecimal.ROUND_HALF_UP);

			BigDecimal enteredMin = MEXMEUOMConversion.convertProductFrom(ctx,
					mProduct.getM_Product_ID(), mProduct.getC_UOMVolume_ID(),
					line.getMovementQty_Vol(), null);
			enteredMin = enteredMin.setScale(
					MUOM.getPrecision(ctx, mProduct.getC_UOMVolume_ID()),
					BigDecimal.ROUND_HALF_UP);

			line.setQtyEntered(targetMin);
			line.setMovementQty(targetMin);
			line.setQtyEntered(enteredMin);
		}

		line.setM_Product_ID(mProduct.getM_Product_ID());

		if (locatorId <= 0) {
			locatorId = locatorToId(warehouse, pTrxName);
		}
		if (locatorId > 0) {
			line.setM_Locator_ID(locatorId);
		}

		/*
		 * line.setQty(targetQty); // Qty - En unidad de almacenamiento if
		 * (qtyEntred == null || qtyEntred.compareTo(Env.ZERO) == 0) { qtyEntred
		 * = line.getMovementQty(); }
		 *
		 * line.setQtyEntered(qtyEntred);
		 */

		line.setAD_OrgTrx_ID(mInOut.getAD_OrgTrx_ID());
		line.setM_AttributeSetInstance_ID(mAttribSetInstID);
		line.setDescription("Detail");

		return line;
	}

	/**
	 * Id localizador
	 *
	 * @param almacen
	 * @return
	 */
	public int locatorToId(final int almacen, final String pTrxName) {
		return getMLocatorID(almacen, mProduct.getM_Product_ID(),
				mAttribSetInstID, targetQty, pTrxName);
	}

	/**************************************************************************
	 * Get Location with highest Locator Priority and a sufficient OnHand Qty
	 *
	 * @param M_Warehouse_ID
	 *            warehouse
	 * @param M_Product_ID
	 *            product
	 * @param M_AttributeSetInstance_ID
	 *            asi
	 * @param Qty
	 *            qty
	 * @param trxName
	 *            transaction
	 * @return id
	 */
	public int getMLocatorID(final int M_Warehouse_ID, final int M_Product_ID,
			final int M_AttributeSetInstance_ID, final BigDecimal Qty,
			final String trxName) {
		int M_Locator_ID = 0;
		int firstM_Locator_ID = 0;
		final StringBuilder sql = new StringBuilder(
				"SELECT s.M_Locator_ID, s.QtyOnHand ")
				.append(" FROM M_Storage s ")
				.append(" INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID) ")
				.append(" INNER JOIN M_Product p ON (s.M_Product_ID=p.M_Product_ID) ")
				.append(" INNER JOIN EXME_ProductoOrg po ON (p.M_Product_ID=po.M_Product_ID AND po.IsActive='Y' AND po.AD_Org_ID = ")
				.append(Env.getAD_Org_ID(Env.getCtx()))
				.append(")  ")
				.append(" LEFT OUTER JOIN M_AttributeSet mas ON (po.M_AttributeSet_ID=mas.M_AttributeSet_ID)  ")
				.append(" WHERE l.M_Warehouse_ID=? ")
				.append(" AND s.M_Product_ID=? ")
				.append(" AND (mas.IsInstanceAttribute IS NULL OR mas.IsInstanceAttribute='N' OR s.M_AttributeSetInstance_ID=?) ")
				.append(" AND l.IsActive='Y'  ")
				.append("ORDER BY l.PriorityNo DESC, s.QtyOnHand DESC ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Warehouse_ID);
			pstmt.setInt(2, M_Product_ID);
			pstmt.setInt(3, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final BigDecimal QtyOnHand = rs.getBigDecimal(2);
				if (QtyOnHand != null && Qty.compareTo(QtyOnHand) <= 0) {
					M_Locator_ID = rs.getInt(1);
					break;
				}
				if (firstM_Locator_ID == 0) {
					firstM_Locator_ID = rs.getInt(1);
				}
			}

			if (M_Locator_ID <= 0 && firstM_Locator_ID <= 0) {
				M_Locator_ID = MLocator.getLocatorID(ctx, M_Warehouse_ID,
						trxName);
			}
		} catch (final SQLException ex) {
			log.log(Level.SEVERE, sql.toString(), ex);
		} catch (final Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			log.log(Level.INFO, "M_Locator_ID" + M_Locator_ID
					+ "firstM_Locator_ID" + firstM_Locator_ID);
		}

		if (M_Locator_ID != 0) {
			return M_Locator_ID;
		}
		return firstM_Locator_ID;
	} // getM_Locator_ID

	/**
	 * Actualiza el inventario Complete MInventory
	 *
	 * @param ctx
	 * @param lines
	 * @param warehouse
	 * @param trxName
	 * @deprecated
	 */
	@Deprecated
	public static void updateInventory(final Properties ctx,
			final MInventoryLine[] lines, final int warehouse,
			final String trxName) {
		for (final MInventoryLine line : lines) {
			// Producto
			final int prod = line.getM_Product_ID();
			// Cantidad de entrada
			final BigDecimal qty = line.getQtyCount();
			// Lote
			final MAttributeSetInstance atri = new MAttributeSetInstance(ctx,
					line.getM_AttributeSetInstance_ID(), trxName);

			// Cantidad a cubrir
			BigDecimal sum = Env.ZERO;
			final List<MEXMEInOutStop> lst = MEXMEInOutStop.find(ctx, prod,
					atri.getLot(), trxName);
			final List<MEXMEInOutStop> lstPending = new ArrayList<MEXMEInOutStop>();
			for (int j = 0; j < lst.size(); j++) {
				sum = sum.add(lst.get(j).getTargetQty());
				lstPending.add(lst.get(j));
				if (sum.compareTo(qty) >= 0) {
					break;
				}
			}

			// Salida por cada salida pendiente que cubre la cantidad
			for (MEXMEInOutStop inOutStop : lstPending) {

				if (inOutStop.getAD_Table_ID() == I_EXME_ActPacienteInd.Table_ID) {
					final MEXMEActPacienteInd actpac = new MEXMEActPacienteInd(ctx,
							inOutStop.getRecord_ID(), trxName);

					final Inventory inventory = new Inventory(ctx, actpac
							.getActPacienteIndH().getEXME_CtaPac_ID(),
							I_M_InventoryLine.Table_ID);

					inventory.EXME_ActPacienteIndH_ID = actpac
							.getEXME_ActPacienteIndH_ID();

					if (inventory.valid(line.getM_InventoryLine_ID(),
							inOutStop.getTargetQty(), inOutStop
									.getM_Product_ID(), actpac.getC_UOM_ID(),
							0,//inOutStop.getLot(), RQM#5629
							Env.getM_Warehouse_ID(ctx),
							true, trxName)) {
						// inventory.create = true;
						inventory.localStore(warehouse,  false,  null, trxName);
					}
				}
			}// fin for
		}
	}

	/**
	 * Actualiza el inventario completit MInOut
	 *
	 * @param ctx
	 * @param lines
	 * @param trxName
	 * @deprecated Farmacia Intrahospitalaria - Revisar (FIXME)
	 */
	@Deprecated
	public static void updateInventory(final Properties ctx,
			final MInOutLine[] lines, final String trxName) {

		try {

			// Itera por linea de la recepcion
			for (final MInOutLine line : lines) {

				// producto
				final int prod = line.getM_Product_ID();
				// Cantidad recepcion
				final BigDecimal qty = line.getQtyEntered();
				// lote
				final MAttributeSetInstance atri = new MAttributeSetInstance(ctx,
						line.getM_AttributeSetInstance_ID(), trxName);
				// Lineas pendientes
				final List<MEXMEInOutStop> lst = MEXMEInOutStop.find(ctx, prod,
						atri.getLot(), trxName);
				// Cantidad pendiente
				BigDecimal sum = Env.ZERO;
				final List<MEXMEInOutStop> lstPending = new ArrayList<MEXMEInOutStop>();
				for (int j = 0; j < lst.size(); j++) {
					sum = sum.add(lst.get(j).getTargetQty());
					lstPending.add(lst.get(j));
					if (sum.compareTo(qty) >= 0) {
						break;
					}
				}

				// Itera por linea pendiente
				for (MEXMEInOutStop inOutStop : lstPending) {

					if (inOutStop.getAD_Table_ID() == I_EXME_ActPacienteInd.Table_ID) {

						// Orden del paciente
						final MEXMEActPacienteInd actpac = new MEXMEActPacienteInd(
								ctx, inOutStop.getRecord_ID(), trxName);

						// Creamos el objeto con la cuenta paciente
						final Inventory inventory = new Inventory(ctx, actpac
								.getActPacienteIndH().getEXME_CtaPac_ID(),
								I_M_InOutLine.Table_ID);

						// Id de la orden
						inventory.EXME_ActPacienteIndH_ID = actpac
								.getEXME_ActPacienteIndH_ID();

						// Validamos la salida
						if (inventory.valid(line.getM_InOutLine_ID(),
								inOutStop.getTargetQty(), 
								inOutStop.getM_Product_ID(), 
								actpac.getC_UOM_ID(), 
								0,//inOutStop.getLot(), RQM#5629
								Env.getM_Warehouse_ID(ctx),
								true, trxName)) {
							// inventory.create = true;

							// traspaso
							final TransferProduct transferProduct = new TransferProduct(
									ctx, trxName);
							// Se crea movement y movement line
							transferProduct.internalTransfer(
									line.getM_Warehouse_ID(), // Almacen de
									// la
									// recepcion
									actpac.getActPacIndH().getM_Warehouse_ID(), // Almacen
																				// de
																				// la
									// enfermera
									actpac.getActPacIndH().getEXME_CtaPac_ID(),
									inOutStop.getM_Product_ID(),
									inOutStop.getTargetQty()
											.longValue(), inOutStop
											.getTargetQty().longValue(),
									inOutStop.getLot(),
									actpac.getAD_OrgTrx_ID(),// Org trx de la
									// enfermera
									atri.getM_AttributeSetInstance_ID(), null);

							// Salida
							inventory.localStore(line.getM_Warehouse_ID(), false, null, trxName);
							inOutStop.setStatus(
									X_EXME_CtaPac.DOCACTION_Close);
							inOutStop.save(trxName);
						}
					}
				}
			}

		} catch (final Exception e) {
			s_processMsg = e.getMessage();// continua
		}
	}// Act

//	/*******************************************************************************/
//
//	/**
//	 *
//	 * Desde aplicacion de servicios
//	 *
//	 * @param indH
//	 * @param movementDate
//	 * @param linesToDeliver
//	 * @param allAttributeInstances
//	 * @param minGuaranteeDate
//	 * @param complete
//	 * @param estServ
//	 * @param trxName (toma la transaccion de indh)
//	 * @return
//	 * @deprecated
//	 */
//	public static MInOut createFromApServ(final MEXMEActPacienteIndH indH,
////			final Timestamp movementDate,
//			final Map<Integer, BigDecimal> linesToDeliver,
//			final boolean allAttributeInstances,
//			final Timestamp minGuaranteeDate,
////			final boolean complete,
//			final MEXMEEstServ estServ) {
//
//		s_processMsg = null;
//
//		if (indH == null) {
//			throw new IllegalArgumentException("No IndicacionH");
//		}
//
//		// Create Meader
////		final MInOut mInOutObj = new MInOut(indH);//se pasa aqui la transaccion
////		if(mInOutObj.get_TrxName() == null) {
////			mInOutObj.set_TrxName(indH.get_TrxName());
////		}
////		mInOutObj.setDocAction(complete ? X_M_InOut.DOCACTION_Complete : X_M_InOut.DOCACTION_Prepare);
//		// Organizacion de quien solicita
////		mInOutObj.setAD_OrgTrx_ID(indH.getAD_OrgTrx_ID());
////		mInOutObj.setEXME_CtaPac_ID(indH.getEXME_CtaPac_ID());
//
////		// direccion socio
////		if (mInOutObj.getC_BPartner_Location_ID() <= 0) {
////			// Crear una direccion
////			final MBPartner company = new MBPartner(indH.getCtx(), mInOutObj.getC_BPartner_ID(), indH.get_TrxName());
////			// direcciones
////			final MBPartnerLocation[] direcciones = company.getLocations(true);
////			if (direcciones != null && direcciones.length > 0) {
////				mInOutObj.setC_BPartner_Location_ID(direcciones[0].getC_BPartner_Location_ID());//
////			} else {
////				// unida direccion
////				final MLocation direccion = new MLocation(indH.getCtx(), 0, indH.get_TrxName());
////				direccion.setC_Country_ID(Env.getC_Country_ID(indH.getCtx()));
////				if (direccion.save() ) {//ya tiene trxname
////
////					final MBPartnerLocation socioDirect = new MBPartnerLocation(indH.getCtx(), 0, indH.get_TrxName());
////					socioDirect.setName("Address" + company.getName());
////					socioDirect.setC_Location_ID(direccion.getC_Location_ID());
////					socioDirect.setC_BPartner_ID(company.get_ID());
////					if (socioDirect.save()) {//ya tiene trxname
////						// Relacionarla al socio
////						mInOutObj.setC_BPartner_Location_ID(socioDirect.getC_BPartner_Location_ID());//
////					}
////				}
////			}
////		}
//
////		if (mInOutObj.getC_BPartner_Location_ID() <= 0) {
////			s_processMsg = "No BPartner Location.";
////			return mInOutObj;
////		}
////
////		// Create Line
////		if (mInOutObj.get_ID() == 0 && !mInOutObj.save()) {//ya tiene trxname
////			s_processMsg = "No se genero la salida del Almacen.";
////			return mInOutObj;
////
////		}
//		final List<Inventariado> lstStock = new ArrayList<Inventariado>();
//		// Check if we can create the lines
//		final MEXMEActPacienteInd[] apIndLines = indH.getLineas(true);
//		for (final MEXMEActPacienteInd apIndLine : apIndLines) {
//
//			// Relacionar con el expediente
////			mInOutObj.setEXME_ActPacienteIndH_ID(apIndLine.getEXME_ActPacienteIndH_ID());
//			final int actPacienteInd_ID = apIndLine.getEXME_ActPacienteInd_ID();
//			// Cantidad a partir del mapa (Id Actpacienteind/ cantidad)
//			final BigDecimal totalQty = linesToDeliver.get(actPacienteInd_ID) == null ? BigDecimal.ZERO : linesToDeliver.get(actPacienteInd_ID);
//
//			// Cantidad despues de conversion
//			BigDecimal qtyDelivered =
//				MEXMEUOMConversion.convertProductTo(indH.getCtx(), apIndLine.getM_Product_ID(), apIndLine.getC_UOM_ID(), totalQty, null);
//			if (qtyDelivered == null || Env.ZERO.compareTo(qtyDelivered) == 0) {
//				qtyDelivered = totalQty;
//			}
//
//			// QUE NO ACTUALIZE EXME_ACTPACIENTEIND NI EXME_ACTPACIENTEINDH
//			// // Actualiza la cantidad de aplicacion del servicio
//			// apIndLine.setQtyDelivered(qtyDelivered);
//			// // Actualiza fecha de aplicacion de servicio
//			// apIndLine.setDateDelivered(DB.getTimestampForOrg(Env.getCtx()));
//			// if (!apIndLine.save(trxName)) {
//			// s_processMsg =
//			// "No se actualizo la linea del cargo @EXME_ActPacienteInd@ = "
//			// + apIndLine.toString();
//			// return null;
//			// }
//
//			//
//			if (X_M_Product.PRODUCTTYPE_Service.equals(apIndLine.getProduct().getProductType())) {
//				// Los servicios no crean movimiento de inventario.
//				// totalQty = apIndLine.getQtyDelivered();
//				final Inventariado line = new Inventariado(indH.getCtx(), apIndLine);
//				
////				final MInOutLine line = new MInOutLine(mInOutObj); //aqui se pasa el trxname
////				if(line.get_TrxName() == null) {
////					line.set_TrxName(mInOutObj.get_TrxName());
////				}
////				line.setOrderLine(apIndLine, 0);
//
//				// Siempre debería traer una cantidad, si es cero hay un error
//				if (Env.ZERO.compareTo(qtyDelivered) == 0) {
//					line.setQty(Env.ONE); // Qty - En unidad de almacenamiento
//				} else {
//					line.setQty(qtyDelivered); // Qty - En unidad de almacenamiento
//				}
//				// line.setQtyEntered(qtyDelivered);
////				line.setEXME_ActPacienteInd_ID(actPacienteInd_ID);
////				line.setAD_OrgTrx_ID(apIndLine.getAD_OrgTrx_ID());
////				//  Noelia: guarda en  salida de inventario  la orgtrx de quien  surte
////				if (!line.save()) { //ya tiene trxname
////					// if (apIndLine.getM_InOutLine_ID() == 0) {
////					// apIndLine.setM_InOutLine_ID(line.getM_InOutLine_ID());
////					// }//FIXME: Es necesaria esta referencia ?
////					// } else {
////					s_processMsg = "No se genero la salida del Almacen.";
////					return null;
////				}
//				
//				lstStock.add(line);
//			}
//
//		} // for all order lines
//
//		final ErrorList errors = new ErrorList();
//		final MInOut mInOutObj = MInOut.updateStock(lstStock, errors, indH, ProcessesCharges.APLICARSERVICIOS, false);
//		
//		if(!errors.isEmpty()){
//			s_processMsg = errors.toString();
//			return mInOutObj;
//		}
//		
//		// Organizacion de quien solicita
//		mInOutObj.setAD_OrgTrx_ID(indH.getAD_OrgTrx_ID());
//		
//		// direccion socio
//		if (mInOutObj.getC_BPartner_Location_ID() <= 0) {
//			// Crear una direccion
//			final MBPartner company = new MBPartner(indH.getCtx(), mInOutObj.getC_BPartner_ID(), indH.get_TrxName());
//			// direcciones
//			final MBPartnerLocation[] direcciones = company.getLocations(true);
//			if (direcciones != null && direcciones.length > 0) {
//				mInOutObj.setC_BPartner_Location_ID(direcciones[0].getC_BPartner_Location_ID());//
//			} else {
//				// unida direccion
//				final MLocation direccion = new MLocation(indH.getCtx(), 0, indH.get_TrxName());
//				direccion.setC_Country_ID(Env.getC_Country_ID(indH.getCtx()));
//				if (direccion.save() ) {//ya tiene trxname
//
//					final MBPartnerLocation socioDirect = new MBPartnerLocation(indH.getCtx(), 0, indH.get_TrxName());
//					socioDirect.setName("Address" + company.getName());
//					socioDirect.setC_Location_ID(direccion.getC_Location_ID());
//					socioDirect.setC_BPartner_ID(company.get_ID());
//					if (socioDirect.save()) {//ya tiene trxname
//						// Relacionarla al socio
//						mInOutObj.setC_BPartner_Location_ID(socioDirect.getC_BPartner_Location_ID());//
//					}
//				}
//			}
//		}
//
//		if (mInOutObj.getC_BPartner_Location_ID() <= 0) {
//			s_processMsg = "No BPartner Location.";
//			return mInOutObj;
//		}
//		// No Lines saved
//		if (!mInOutObj.save() || mInOutObj.get_ID() <= 0) {//ya tiene trxname
//			return mInOutObj;
//		}
//		mInOutObj.setActPacienteIndH(indH);
//		return mInOutObj;
//	} // createFrom

//	/**
//	 * Devolucion de cargo
//	 *
//	 * @return
//	 */
//	public boolean devolucion(final String pTrxName) {
//		log.log(Level.INFO, "devolucion ...");
//		boolean success = true;
//		try {
//
//			success = localStore(mWarehouseID, true, pTrxName);
//
//		} catch (final Exception e) {
//			success = false;
//			log.info("localStore" + e.getMessage());// continua
//		}
//		return success;
//	}

//	/**
//	 * Permitir existencias negativas
//	 *
//	 * @return TRUE : MEX FALSE : USA
//	 */
//	public boolean isStopPending(final String pTrxName) {
//		final MEXMEConfigPre config = MEXMEConfigPre.get(ctx, pTrxName);// TODO: porque se requiere trxName para config ??
//		stopPending = config == null ? false : config.isCreateInventory();
//		return stopPending;
	// Se comenta porque solo aplica para USA - Farmacia Intrahospitalaria
	// no funcional por cambios Mexico en inventario y productos - Lama
//	}

	// public void setStopPending(boolean stopPending) {
	// this.stopPending = stopPending;
	// }

//	/**
//	 * se llena con MEXMEMejoras.get(ctx).isControlExistencias();
//	 *
//	 * @return true: si maneja existencias
//	 */
//	public boolean isValid() {
//		return valid;
//	}
	
	public boolean isControlaExistencias() {
		return controlaExistencias;
	}

	/*** SALIDA BATCH **********************************************************/

	/**
	 * Valida que el producto es factible hacer la salida de inventario Solo no
	 * evalua la existencia
	 *
	 * @param pCtx
	 *            : Contexto
	 * @param cargo
	 *            : Objeto <MCtaPacDet> No puede estar almacenado en la base de
	 *            datos
	 * @param ctapac
	 *            : Cuenta paciente. No es obligatoria
	 * @param trxName
	 *            : Nombre de transacción obligatorio
	 * @return Inventariado : Regresa las cantidades calculadas puede regresar
	 *         <NULL>
	 */
	public Inventariado valid(final Properties pCtx, final MCtaPacDet cargo,
			final MEXMECtaPac ctapac, final String trxName) {
		// Información necesaria, el cargo no debera tener un ID
		if (pCtx == null || cargo == null || trxName == null
				|| cargo.getEXME_CtaPacDet_ID() > 0) {
			return null;
		}

		// Objeto con la información Producto, Cantidades, Unidad
		final Inventariado mInventariado = new Inventariado(pCtx, cargo/*,
				ctapac, trxName*/);

		// true si es valido para una salida de inventario
		final boolean valido = valid(cargo.getEXME_ActPacienteInd_ID(), // Registros
				cargo.getQtyDelivered(), // cant
				cargo.getM_Product_ID(), // prod
				cargo.getC_UOM_ID(), // unidad
//				cargo.getProductDescription(), // lote
				cargo.getM_AttributeSetInstance_ID(),//lote RQM#5629
				Env.getM_Warehouse_ID(ctx),
				false, trxName);
		// Almacena en el objeto las cantidades calculadas en unidad de medida
		// minima
		if (valido) {
			mInventariado.setcUOMIDSurt(cUOMIDSurt);
			mInventariado.setTargetQty(targetQty);
			mInventariado.setQtyEntred(qtyEntred);
			mInventariado.setQtyPedido(qtyPedido);
			mInventariado.setmProduct(mProduct);
			mInventariado.setmAttribSetInstID(mAttribSetInstID);

			return mInventariado;
		} else {
			return null;
		}
	}

	/**
	 * Crea la salida de inventario y el ajuste de inventario en caso de ser
	 * necesario Solo para las ventas consideradas punto de venta
	 * @param C_Invoice_ID Factura
	 * @param lst
	 *            : Listado de productos ha hacer su salida de inventario
	 * @param mWarehouse
	 *            : Almacén de Punto de venta
	 * @param pTrxName
	 *            : Nombre de transaccion
	 */
	public boolean inOut(final int C_Invoice_ID, final List<Inventariado> lst,
			final MWarehouse mWarehouse, final String pTrxName) {
		// Información necesaria
		if (lst == null || lst.isEmpty() || mWarehouse ==null || mWarehouse.getM_Warehouse_ID()<=0
				|| pTrxName == null) {
			return false;
		}

		// Productos que no tienen existencia y se creara un inventario de
		// salida
		final List<Inventariado> lstSinExistencia = new ArrayList<Inventariado>();
		boolean success = true;

		// Validar que todos los productos tengan existencia
		for (Inventariado inv : lst) {

			// cantidad seleccionada por el usuario en minima
			final BigDecimal[] qtys = inv.getiCargo().getProduct().qtyConversion(
					inv.getiCargo().getQtyDelivered(),
					inv.getiCargo().getC_UOM_ID());
			final BigDecimal qtyDeliveredMin = qtys[0];//Vol  = qtys[1];

			// cantidad en el almacen en minima
			BigDecimal QtyAvailable = MStorage.getQtyAvailable(mWarehouse.getM_Warehouse_ID(),
//					inv.getiCargo().getM_Locator_ID(),
					inv.getiCargo().getM_Product_ID(), 
					inv.getiCargo().getM_AttributeSetInstance_ID(), 
					pTrxName);
			if (QtyAvailable == null) {
				QtyAvailable = Env.ZERO;
			}

			// Revisa si es suficiente el stock
			if (qtyDeliveredMin.compareTo(QtyAvailable) > 0) {
				final BigDecimal qtyFaltante = qtyDeliveredMin.subtract(QtyAvailable);
				log.saveError("SaveError","La cantidad de uso interno es mayor que la cantidad disponible para el almacen ("+ QtyAvailable + ")"+"La faltante "+ qtyFaltante);
				inv.setDescription(Utilerias.getMessage(ctx,null,"msj.ajustesInventario")+":"+qtyFaltante);
				inv.setScrappedQty(qtyDeliveredMin);
				inv.setQtyAvailable(QtyAvailable);//udm min
				lstSinExistencia.add(inv);
			}
		}// fin for

		// Ajuste de inventario
		if (lstSinExistencia != null && !lstSinExistencia.isEmpty()) {
			success = ajusteInventario(lstSinExistencia, mWarehouse.getM_Warehouse_ID(),
					pTrxName);
		}// si hay ajuste de inventario

		// Crear salida de inventario
		if (success) {// movementLocalStore(pTrxName);
			success = localStoreBatch(C_Invoice_ID, lst, mWarehouse, false, pTrxName);
		}

		return success;
	}

	/**
	 * Crear un ajuste de inventario para lograr la venta al publico
	 *
	 * @param lstSinExistencia
	 *            : Listado de productos sin existencia suficiente
	 * @param m_Warehouse_ID
	 *            : Almacen de punto de venta
	 * @param pTrxName
	 *            : Nombre de transaccion
	 * @return
	 */
	public boolean ajusteInventario(final List<Inventariado> lstSinExistencia,
			final int m_Warehouse_ID, final String pTrxName) {
		boolean success = true;

		if (lstSinExistencia == null || lstSinExistencia.isEmpty()) {
			return success;
		}

		if (m_Warehouse_ID <= 0 || pTrxName == null) {
			return false;
		}

		// Crear ajuste de inventario lineas y encabezado
		MInventory m_inventory = null;
		final MWarehouse wh = MWarehouse.get(ctx, m_Warehouse_ID);
		for (int i = 0; i < lstSinExistencia.size(); i++) {
			// encabezado
			if (m_inventory == null) {
				m_inventory = new MInventory(wh);
				m_inventory.set_TrxName(pTrxName);
				m_inventory.setDescription(Utilerias.getMessage(ctx,null,
						"msj.ajustesInventario")
						+ " "
						+ Utilerias.getMessage(ctx,null,"cDiarios.nomAlmac")
						+ " "
						+ wh.getName());
				m_inventory.setProcess("Directa");
				if (!m_inventory.save(pTrxName)) {
					log.saveError("SaveError",
							"No se logro crear el ajuste de inventario");
					success = false;
					break;
				}
			}
			// Una linea por cada producto sin existencia suficiente
			// la cantidad sera la faltante
			BigDecimal qtyOnHand = MStorage.getQtyOnHand(wh.getLocators(true)[0].getM_Locator_ID()
					,lstSinExistencia.get(i).getmProduct().getM_Product_ID(),0,null);
			final MInventoryLine line = new MInventoryLine(m_inventory,
					wh.getLocators(true)[0].getM_Locator_ID(),
					lstSinExistencia.get(i).getmProduct().getM_Product_ID(),
					lstSinExistencia.get(i).getmAttribSetInstID(),
					qtyOnHand,
					Env.ZERO);
			line.setDescription(lstSinExistencia.get(i).getDescription());
			//Colocar las cantidades correctas en la linea
			line.setQtysBookAndCount(lstSinExistencia.get(i).getQtyPedido(), lstSinExistencia.get(i).getcUOMIDSurt(), lstSinExistencia.get(i).getQtyAvailable());//llena la cantidad contada
			line.set_TrxName(pTrxName);
			if (!line.save(pTrxName)) {
				log.saveError("SaveError",
						"No se logro crear el ajuste de inventario");
				success = false;
				break;
			}
		}// fin for

		// Completar ajuste de inventario
		if (m_inventory == null) {
			success = false;
		} else {
			if (m_inventory.completeIt().equals(DocAction.STATUS_Completed)) {
				log.saveInfo("InventarioEntrada","Se creo el ajuste de inventario");
				m_inventory.setDocStatus(X_M_Inventory.DOCSTATUS_Completed);
				m_inventory.setProcess("Directa");
				m_inventory.save(pTrxName);
			} else {
				success = false;
				log.saveError("SaveError",
						"No se logro crear el ajuste de inventario");
			}
		}
		return success;
	}

	/**
	 * Realiza la salida de inventario o devolución
	 *
	 * @param lLines
	 *            Listado de productos para crear su salida de inventarui
	 * @param warehouse
	 *            Almacén de punto de venta
	 * @param isDevol
	 *            debera ser false
	 * @param pTrxName
	 *            : Nombre de transaccion
	 * @return
	 */
	private boolean localStoreBatch(final int C_Invoice_ID, List<Inventariado> lLines,
			final MWarehouse warehouse, final boolean isDevol, final String pTrxName) {
		boolean success = true;
		try {

			final MInOut mInOut = saveInOut(warehouse.getM_Warehouse_ID(), pTrxName); // relativo a create
															// shipment
			if(mInOut==null){
				success = false;
				throw new ExpertException("error.save.inOut");
			}

			if (isDevol) {
				mInOut.setMovementType(mInOut.isSOTrx() ? X_M_InOut.MOVEMENTTYPE_CustomerReturns
						: X_M_InOut.MOVEMENTTYPE_VendorReturns);
			}
			mInOut.setC_Invoice_ID(C_Invoice_ID);
			if (!mInOut.save(pTrxName)) {
				success = false;
				throw new ExpertException("error.save.inOut");
			}

			for (int i = 0; i < lLines.size(); i++) {
				createLine(mInOut, lLines.get(i), warehouse, pTrxName);
			}

			final String completed = mInOut.completeIt();
			if (!completed.equals(X_M_InOut.DOCSTATUS_Completed)) {
				success = false;
				throw new ExpertException("error.complete.inOut");
			}
			mInOut.setDocStatus(X_M_InOut.DOCSTATUS_Completed);

			if (!mInOut.save(pTrxName)) {
				log.info("if(!m_InOut.save(pTrxName)){");
				success = false;
			}
		} catch (final Exception e) {
			success = false;
			log.info("localStore" + e.getMessage());// continua
		}
		return success;
	}

	/**
	 * Crear la linea
	 *
	 * @param mInOut
	 *            Encabezado de la salida de inventario
	 * @param lLine
	 *            Datos de la linea
	 * @param warehouse
	 *            Almacen de punto de venta
	 * @param isDevol
	 *            False
	 * @param pTrxName
	 *            Nombre de transaccion
	 * @throws Exception
	 */
	private void createLine(final MInOut mInOut, final Inventariado lLine,
			final MWarehouse warehouse, final String pTrxName)
			throws Exception {
		final MInOutLine line = saveInOutLine(lLine, warehouse, mInOut,
				pTrxName);

		if (!line.save(pTrxName)) {
			throw new ExpertException("error.save.inOutLine");
		}
	}

	/**
	 * Lineas de la salida de inventario
	 *
	 * @param lLine
	 *            Datos para la linea
	 * @param warehouse
	 *            Almacen punto de venta
	 * @param mInOut
	 *            Encabezado
	 * @param pTrxName
	 *            Nombre de transaccion
	 * @return La linea del inout
	 * @throws Exception
	 */
	private MInOutLine saveInOutLine(final Inventariado lLine,
			final MWarehouse warehouse, final MInOut mInOut, final String pTrxName)
			throws Exception {
		final MInOutLine line = new MInOutLine(mInOut);
		line.setLine(10);
		// line.setC_UOM_ID(cUOMIDSurt);
		line.set_TrxName(pTrxName);
		if (lLine.getcUOMIDSurt() == 0
				|| lLine.getmProduct().getC_UOM_ID() == lLine.getcUOMIDSurt()) {// el
																				// cargo
																				// se
																				// encuentra
																				// en
																				// unidad
																				// minima

			line.setC_UOM_ID(lLine.getcUOMIDSurt());
			line.setC_UOMVolume_ID(lLine.getmProduct().getC_UOMVolume_ID());

			// la cantidad se encuentra en unidad minima, se establecen las
			// cantidades y se calculan las de volumen
			line.setQty(lLine.getTargetQty()); // Qty - En unidad de
												// almacenamiento
			if (lLine.getQtyEntred() == null
					|| lLine.getQtyEntred().compareTo(Env.ZERO) == 0) {
				lLine.setQtyEntred(line.getMovementQty());
			}

			line.setQtyEntered(lLine.getQtyEntred());

			if (lLine.getmProduct().getC_UOM_ID() == lLine.getmProduct()
					.getC_UOMVolume_ID()) { // la unidad minima y la de volumen
											// son iguales

				line.setQtyEntered_Vol(line.getQtyEntered());
				line.setMovementQty_Vol(line.getMovementQty());
				line.setQtyEntered_Vol(line.getQtyEntered());

			} else {

				BigDecimal targetVol = MEXMEUOMConversion.convertProductTo(ctx,
						lLine.getmProduct().getM_Product_ID(), lLine
								.getmProduct().getC_UOMVolume_ID(), lLine
								.getTargetQty(), pTrxName);
				targetVol = targetVol.setScale(MUOM.getPrecision(ctx, lLine
						.getmProduct().getC_UOMVolume_ID()),
						BigDecimal.ROUND_HALF_UP);

				BigDecimal enteredVol = MEXMEUOMConversion.convertProductTo(
						ctx, lLine.getmProduct().getM_Product_ID(), lLine
								.getmProduct().getC_UOMVolume_ID(), line
								.getMovementQty(), pTrxName);
				enteredVol = enteredVol.setScale(MUOM.getPrecision(ctx, lLine
						.getmProduct().getC_UOMVolume_ID()),
						BigDecimal.ROUND_HALF_UP);

				line.setQtyEntered_Vol(targetVol);
				line.setMovementQty_Vol(targetVol);
				line.setQtyEntered_Vol(enteredVol);

			}

		} else {

			line.setC_UOM_ID(lLine.getmProduct().getC_UOM_ID());
			line.setC_UOMVolume_ID(lLine.getcUOMIDSurt());

			// la cantidad se encuentra en unidad de volumen, se establecen las
			// cantidades y se calculan las de unidad minima
			line.setQtyEntered_Vol(lLine.getQtyPedido());
			line.setMovementQty_Vol(lLine.getQtyPedido());
			if (lLine.getQtyEntred() == null
					|| lLine.getQtyEntred().compareTo(Env.ZERO) == 0) {
				lLine.setQtyEntred(line.getMovementQty_Vol());
			}

			line.setQtyEntered_Vol(lLine.getQtyEntred());

			// calculamos la cantidad minima
			BigDecimal targetMin = MEXMEUOMConversion.convertProductFrom(ctx,
					lLine.getmProduct().getM_Product_ID(), lLine.getmProduct()
							.getC_UOMVolume_ID(), lLine.getQtyPedido(), pTrxName);
			targetMin = targetMin.setScale(
					MUOM.getPrecision(ctx, lLine.getmProduct().getC_UOM_ID()),
					BigDecimal.ROUND_HALF_UP);

			BigDecimal enteredMin = MEXMEUOMConversion.convertProductFrom(ctx,
					lLine.getmProduct().getM_Product_ID(), lLine.getmProduct()
							.getC_UOMVolume_ID(), line.getMovementQty_Vol(),
							pTrxName);
			enteredMin = enteredMin.setScale(MUOM.getPrecision(ctx, lLine
					.getmProduct().getC_UOMVolume_ID()),
					BigDecimal.ROUND_HALF_UP);

			line.setQtyEntered(targetMin);
			line.setMovementQty(targetMin);
			line.setQtyEntered(enteredMin);
		}

		line.setM_Product_ID(lLine.getmProduct().getM_Product_ID());
		line.setAD_OrgTrx_ID(mInOut.getAD_OrgTrx_ID());
		line.setDescription("Detail");
		line.setM_AttributeSetInstance_ID(lLine.getmAttribSetInstID());

		// Lotes/Localizadores Card #1225
		// Localizador del almacen de login Propio 
		if(lLine.getiCargo() != null && lLine.getiCargo().getM_Locator_ID() > 0) {
			line.setM_Locator_ID(lLine.getiCargo().getM_Locator_ID());
		} else {
			// Cuando el almacen que surte es consigna, debe buscarse el localizador
			// que recibe el movimiento. (Misma consulta ejecutada) 
			int locatorTo = MLocator.getLocatorID(ctx, warehouse.getM_Warehouse_ID(), null);
			line.setM_Locator_ID(locatorTo);
		}
		return line;
	}
	
	/**
	 * inventory
	 * @param lines
	 * @return
	 * @throws Exception 
	 */
	public boolean createLinesInOut(final List<MCtaPacDet> lines, boolean forceStorageUpdate, final String trxName) {
		boolean success = true;
		final ErrorList errorList =  new ErrorList();
		final List<Inventariado>    lstStock   = new ArrayList<Inventariado>();
		// inventory
		for (final Iterator<MCtaPacDet> i = lines.iterator(); i.hasNext();) {
			final MCtaPacDet cargo = i.next();
			
			if (cargo.getProduct() != null
					&& cargo.getProducto().isStocked()
					&& cargo.getProducto().isItem()) {
				
				// Valida que el producto pueda afectar el inventario
				if (valid(
						cargo.getEXME_ActPacienteInd_ID(), // Registros
						cargo.getQtyDelivered(), // cant
						cargo.getM_Product_ID(), // prod
						cargo.getC_UOM_ID(), // unidad
//						cargo.getProductDescription(), // lote
						cargo.getM_AttributeSetInstance_ID(),//RQM#5629
						cargo.getM_Warehouse_ID(),
						false, 
						trxName)) {

					// Se crea la salida de inventario M_InOut
					if (cargo.getQtyDelivered().compareTo(Env.ZERO) > 0
						// Si es que se maneja inventario
						&& isControlaExistencias() && MWarehouse.get(ctx, cargo.getM_Warehouse_ID()).isControlExistencias()) {
//							success = movement(trxName, cargo, forceStorageUpdate);// Si no hay
//							// existencia no se
//							// cre m_inout
//						} else {
//							success = localStore(mWarehouseID, false, cargo, trxName);// el
																				// valor
							// de la
							// existencia
							// no es
							// real
							cargo.setReloadStorage(forceStorageUpdate);
							final Inventariado lInv = MInOut.addLineInventory(errorList, cargo, null);
							if(lInv==null) {
								if(!errorList.isEmpty()){
									break;
								}
							}  else {
								lstStock.add(lInv);
							}
//						}
//					} else {
//						// Es una devolucion de inventario M_InOut
////						success = devolucion(trxName);
//						success = localStore(mWarehouseID, true, cargo, trxName);
					}
				} else {
					success = false;
				}
				
				if (!success) {
					break;
				}
			}
		}// fin_for
		
		if(success){
			success = errorList.isEmpty();
		}
		 
		if (success && !lstStock.isEmpty()) {
			mCtaPac.set_TrxName(trxName);
			MInOut.updateStock(lstStock, errorList, mCtaPac, ProcessesCharges.TRASPASOSENTREALMACENES, false);
		}
		return success;
	}
	
	public List<ModelError> getErrores() {
		return errores;
	}
	
	/**
	 * Itera la lista de errores y deacuerdo al tipo de error muestra la
	 * ventana, el mensaje correspondiente.
	 * 
	 * @param lstError
	 * @return
	 */
	public StringBuilder getErrors() {
		final StringBuilder err = new StringBuilder();
		if (errores != null && !errores.isEmpty()) {
			// Mostramos todos los errores//ok
			for (ModelError error : errores) {
				if (error.getTipoError() == ModelError.TIPOERROR_Exclamacion //
					|| error.getTipoError() == ModelError.TIPOERROR_Excepcion //
					|| error.getTipoError() == ModelError.TIPOERROR_Error) {
					MessageFormat formatter = new MessageFormat(//
						error.isRequiereTraduccion() ? Utilerias.getMsg(ctx, error.getMensaje()) : error.getMensaje(),//
						org.compiere.util.Env.getLanguage(ctx).getLocale()//
						);
					String msg = formatter.format(error.getParam());
					err.append(msg).append("<br>");
				}
			}
		}
		return err;
	}

}
