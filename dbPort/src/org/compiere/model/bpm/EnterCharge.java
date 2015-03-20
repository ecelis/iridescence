package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.MConfigEC;
import org.compiere.model.MCountry;
import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MEXMEActPacienteIndCgo;
import org.compiere.model.MEXMEActPacienteIndH;
import org.compiere.model.MEXMEConfigPre;
import org.compiere.model.MEXMECtaPac;
import org.compiere.model.MEXMECtaPacExt;
import org.compiere.model.MEXMEEstServ;
import org.compiere.model.MEXMEMejoras;
import org.compiere.model.MEXMETax;
import org.compiere.model.MInOut;
import org.compiere.model.MInOut.ProcessesCharges;
import org.compiere.model.MLocator;
import org.compiere.model.MPrecios;
import org.compiere.model.MProduct;
import org.compiere.model.MRequest;
import org.compiere.model.MStorage;
import org.compiere.model.MTax;
import org.compiere.model.MWarehouse;
import org.compiere.model.ModelError;
import org.compiere.model.X_EXME_ActPacienteInd;
import org.compiere.model.X_EXME_CtaPacDet;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.ErrorList;

/**
 * Patient Account's (Encounter) charges <br>
 * based on {@link CaptCargoAction}
 * 
 * @author Lorena Lama<br>
 *         Modified by @author Twry Perez
 * Modificado por Lorena Lama (Marzo 2014)
 * FIXME: requiere refactorizacion: exceso de parametros y metodos
 */
public class EnterCharge {
	/** log */
	protected final CLogger				log					= CLogger.getCLogger(getClass());
	/** contexto */
	protected final Properties			ctx;
	/** almacen que surte el producto/servicio */
	private MWarehouse					warehouse;
	/** unidad de serivio que surte el producto/servicio */
	private MEXMEEstServ				estServ;
	/** encuentro */
	protected MEXMECtaPac				ctaPac;
	/** configuracion precios */
	private final MEXMEConfigPre		configPre;
	/** configuracion de mejoras */
	private final MEXMEMejoras			configMej;
	/** producto */
	private MProduct					prod;
	/** listado de mensajes errores o avisos */
	protected final List<ModelError>	errores				= new ArrayList<ModelError>();
	/** se considera si el cargo es parte del paquete */
	private MTax					impuesto;
	/** listado de modificadores */
	private List<Integer>				lstModificadores	= new ArrayList<Integer>();
	/** Listado de cargos */
	protected List<MCtaPacDet>			lstCharges			= new ArrayList<MCtaPacDet>();
	/** Obj de log de errores */
	private MEXMEActPacienteIndCgo		logCgo;
	/** */
	private MEXMEActPacienteInd			mActPacienteInd;
	/** */
	protected boolean					isNimboMx;
	/** Actualiza el inventario si el proceso asi lo requiere */
	protected boolean					updateInventory		= true;
	protected final int locatorCtx;//localizadro del almacen de ctx

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param warehouseID
	 * @param estservID
	 * @param ctapacID
	 */
	public EnterCharge(final Properties ctx, final int warehouseID, final int estservID, final int ctapacID) {
		this(ctx);
		if (warehouseID <= 0 || estservID <= 0 || ctapacID <= 0) {
			log.log(Level.SEVERE, "warehouseID <= 0 : " + warehouseID + " estservID <= 0 : " + estservID + " ctapacID <= 0 : " + ctapacID);
			throw new MedsysException(Utilerias.getAppMsg(ctx, "error.encCtaPac.errGuardaCgo", ctapacID));
		}
		setWarehouseID(warehouseID);
		setEstservID(estservID);
		if(ctapacID > 0) {
			ctaPac = new MEXMECtaPac(ctx, ctapacID, null);
		}
	}

	/**
	 * Constructor
	 * 
	 * @param ctx
	 * @param warehouseID
	 * @param estservID
	 * @param ctapacID
	 */
	public EnterCharge(final Properties ctx) {
		super();
		this.ctx = ctx;
		if(ctx == null ) {
			log.log(Level.WARNING, "ctx == null");
			throw new IllegalArgumentException();
		}
		locatorCtx = MLocator.getLocatorID(ctx, Env.getM_Warehouse_ID(ctx), null);
		configPre = MEXMEConfigPre.get(ctx, null);
		configMej = MEXMEMejoras.get(ctx);
		if(configMej == null || configPre == null) {
			log.log(Level.WARNING, "if (configMej == null || configPre == null)");
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @param warehouseID Almacen
	 */
	public void setWarehouseID(final int warehouseID) {
		if(warehouseID > 0) {
			warehouse = MWarehouse.get(ctx, warehouseID);
		}
	}

	/**
	 * @param estservID Estacion de servicio
	 */
	public void setEstservID(final int estservID) {
		if(estservID > 0) {
			estServ = new MEXMEEstServ(ctx, estservID, null);
		}
	}

	/**
	 * @param actPacIndID EXME_ActPacienteInd_ID
	 */
	public void setEXMEActPacienteIndID(final int actPacIndID) {
		setEXMEActPacienteIndID(actPacIndID, null);
	}

	/**
	 * @param actPacIndID EXME_ActPacienteInd_ID
	 * @param trxName
	 */
	public void setEXMEActPacienteIndID(final int actPacIndID, final String trxName) {
		try {
			if (actPacIndID > 0) {
				if (mActPacienteInd == null //
					|| mActPacienteInd.getEXME_ActPacienteInd_ID() != actPacIndID) {
					mActPacienteInd = new MEXMEActPacienteInd(ctx, actPacIndID, trxName);
				}
				logCgo = MEXMEActPacienteIndCgo.get(ctx, mActPacienteInd.getEXME_ActPacienteInd_ID(), true, trxName);
				if (logCgo == null) {
					logCgo = new MEXMEActPacienteIndCgo(ctx, 0, trxName);
					logCgo.setEXME_ActPacienteIndH_ID(mActPacienteInd.getEXME_ActPacienteIndH_ID());
					logCgo.setEXME_ActPacienteInd_ID(actPacIndID);
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "MEXMEActPacienteIndCgo NO SE LOCALIZO EL REGISTRO EXME_ActPacienteInd_ID=", actPacIndID);
		}
	}

	public void setActPacienteInd(final MEXMEActPacienteInd mActPacienteInd) {
		this.mActPacienteInd = mActPacienteInd;
	}

	public void setCtaPac(final MEXMECtaPac ctaPac) {
		this.ctaPac = ctaPac;
	}

	/**
	 * Based on
	 * {@link CaptCargoAction#getBuscarID(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 * {@link CaptCargoAction#actUdM(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 * 
	 * @param productID
	 * @throws Exception
	 */
	public boolean findProduct(final int productID) {
		if (warehouse == null || estServ == null || ctaPac == null) {
			log.log(Level.WARNING, "if (warehouse == null || estServ == null || ctaPac == null)");
			throw new IllegalArgumentException(" warehouse is null OR estServ is null or ctaPac is null)");
		}
		final MProduct mProd = new MProduct(ctx, productID, null);
		if (validateProduct(mProd)) {
			prod = mProd;
			if (MCountry.isUSA(ctx) && prod.getProdOrg() != null) {
				lstModificadores = prod.getProdOrg().getModifiers();
			}
		}
		return errores.isEmpty();
	}

	/**
	 * validateProduct
	 * 
	 * @param mProd
	 * @return
	 * @throws Exception
	 */
	protected boolean validateProduct(final MProduct mProd) {
		StringBuilder error = new StringBuilder();
		String type = MEXMEActPacienteIndCgo.CONFIGURATION;
		try {
			error.append("Product: ");
			error.append(mProd.getValue()).append("-");
			error.append(mProd.getName()).append(Constantes.NEWLINE);
			if (mProd.is_new()) {
				error.append("Prod Id Zero").append(Constantes.NEWLINE);
				errores.add(new ModelError("error.cargo.noExisteElProducto"));
			}
//			// verificar que no sean deducible ni coaseguro 
//			// estos no se pueden devolver // Se valida en el constructor
//			if (configPre == null) {
//				error.append("Conf Price").append(Constantes.NEWLINE);
//				log.log(Level.WARNING, "if (configPre == null)" + error);
//				errores.add(new ModelError("error.configPre"));
//			}
			// coaseguro / deducible / etc
			if (!mProd.isProduct()) {
				error.append("Is not product").append(Constantes.NEWLINE);
				type = MEXMEActPacienteIndCgo.NDCNOTVALID;
				errores.add(new ModelError("error.captCargo.prodPrecio"));
			}
			// Define y valida el impuesto relacionado al producto.
			impuesto = MEXMETax.getImpuestoProducto(ctx, mProd.get_ID(), null);
			if (impuesto == null) {
				log.log(Level.WARNING, "if (impuesto == null)" + error + " producto ID - Value = " + mProd.get_ID() + " - " + mProd.getValue());
				error.append("Tax Zero").append(Constantes.NEWLINE);
				errores.add(new ModelError("error.configuracionTax"));
			}

		} catch (Exception e) {
			type = MEXMEActPacienteIndCgo.OTHER;
			log.log(Level.SEVERE, "EnterCharge.validateProduct", e);
			errores.add(new ModelError(ModelError.TIPOERROR_Excepcion, "error.msjError", e.getLocalizedMessage()));
			error.append(e.getLocalizedMessage());
		} finally {
			if(!errores.isEmpty()) {
				logError(error.toString(), type);// Actualiza el log de error
				log.log(Level.SEVERE, "EnterCharge.validateProduct", error);
			}
		}

		return errores.isEmpty();
	}
	
	/**
	 * FIXME Clase temporal con los parametros requeridos para creacion de cargos
	 * de forma que las firmas de los metodos queden simplificadas
	 * Llena los valores por defecto
	 * {@link #billingType} = SingleClaim
	 * {@link #warehouseId} = toma el del contexto
	 * {@link #locatorId} = toma el del contexto
	 * 
	 * @author lama
	 */
	public class CtaPacDet {
		private BigDecimal	qtyOrdered;
		private BigDecimal	qtyDelivered;
		private int			uoMSale;
		private String		lote;
		private boolean		process;
		private Timestamp	dateOrder;
		private Timestamp	dateDeliv;
		private BigDecimal	precio;
		private boolean		buscarPrecio;
		private String		billingType	= X_EXME_CtaPacDet.BILLINGTYPE_SingleClaim;
		/** si es producto o charola, es decir proviene de M_Movement, si valida o no existencias */
		private boolean		isProdOrTray;
		private int			attributeSetInstanceID;
		private int			warehouseId;
		private int			locatorId;
		private MEXMEActPacienteInd actPacienteInd;
		
		public CtaPacDet() {
			warehouseId	= Env.getM_Warehouse_ID(ctx);
			this.locatorId = locatorCtx;
			actPacienteInd = null;
		}
		
		public BigDecimal getQtyOrdered() {
			return qtyOrdered;
		}
		public void setQtyOrdered(BigDecimal qtyOrdered) {
			this.qtyOrdered = qtyOrdered;
		}
		public BigDecimal getQtyDelivered() {
			return qtyDelivered;
		}
		public void setQtyDelivered(BigDecimal qtyDelivered) {
			this.qtyDelivered = qtyDelivered;
		}
		public int getUoMSale() {
			return uoMSale;
		}
		public void setUoMSale(int uoMSale) {
			this.uoMSale = uoMSale;
		}
		public String getLote() {
			return lote;
		}
		public void setLote(String lote) {
			this.lote = lote;
		}
		public boolean isProcess() {
			return process;
		}
		public void setProcess(boolean process) {
			this.process = process;
		}
		public Timestamp getDateOrder() {
			return dateOrder;
		}
		public void setDateOrder(Timestamp dateOrder) {
			this.dateOrder = dateOrder;
		}
		public Timestamp getDateDeliv() {
			return dateDeliv;
		}
		public void setDateDeliv(Timestamp dateDeliv) {
			this.dateDeliv = dateDeliv;
		}
		public BigDecimal getPrecio() {
			return precio;
		}
		public void setPrecio(BigDecimal precio) {
			this.precio = precio;
		}
		public boolean isBuscarPrecio() {
			return buscarPrecio;
		}
		public void setBuscarPrecio(boolean buscarPrecio) {
			this.buscarPrecio = buscarPrecio;
		}
		public String getBillingType() {
			return billingType;
		}
		public void setBillingType(String billingType) {
			this.billingType = billingType;
		}

		/**
		 * @return TRUE: si es producto o charola,
		 *         es decir proviene de M_Movement
		 *         si valida o no existencias
		 */
		public boolean isProdOrTray() {
			return isProdOrTray;// from movement
		}

		/**
		 * @param isProdOrTray si es producto o charola,
		 *            es decir proviene de M_Movement
		 *            si valida o no existencias
		 */
		public void setProdOrTray(boolean isProdOrTray) {
			this.isProdOrTray = isProdOrTray;//from movement
		}
		public int getAttributeSetInstanceID() {
			return attributeSetInstanceID;
		}
		public void setAttributeSetInstanceID(int attributeSetInstanceID) {
			this.attributeSetInstanceID = attributeSetInstanceID;
		}
		public int getLocatorId() {
			return locatorId;
		}
		public void setLocatorId(int locatorId) {
			this.locatorId = locatorId;
		}
		public int getWarehouseId() {
			return warehouseId;
		}
		public void setWarehouseId(int warehouseId) {
			this.warehouseId = warehouseId;
		}
		public MEXMEActPacienteInd getActPacienteInd() {
			return actPacienteInd;
		}
		public void setActPacienteInd(MEXMEActPacienteInd actPacienteInd) {
			this.actPacienteInd = actPacienteInd;
		}
	}
	/**
	 * Crea el objeto de tipo EXME_CtaPacDet a parir de la informacion de CtaPacDet
	 * ( sin ejecutar {@link org.compiere.model.PO#save()} )
	 * 
	 * @param det Objeto con los parametros requeridos para generar el cargo
	 * @return EXME_CtaPacDet nuevo con la informacion de {@link #prod} y en parametro det
	 *         regresa null si {@link #validateCharge(CtaPacDet)} es falso
	 * 
	 *         LAMA: se utiliza un solo objeto en lugar de mandar tantos parametros
	 */
	public MCtaPacDet createCharge(CtaPacDet det) {
		MCtaPacDet charge = null;
		// final boolean isEnterChadrge = !process;
		try {
			//
			final BigDecimal qtyEntered = det.getQtyOrdered();
			/** TODO Fix qtySale Unidosis */
//			if (validateAdd(det.getQtyDelivered(), det.isProcess(), det.getUoMSale(), det.isProdOrTray())) {
			if(validateCharge(det)) {
				charge = new MCtaPacDet(ctaPac, 0, estServ.getEXME_Area_ID(), null);
				charge.setLine();
				charge.setM_Product_ID(prod.get_ID());
				charge.setC_UOM_ID(det.getUoMSale() <= 0 ? prod.getC_UOM_ID() : det.getUoMSale());
				charge.setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
				charge.setCgoProcesado(false);
				charge.setVisible(true);
				charge.setActPacienteInd(det.getActPacienteInd());
				
				charge.setQtyEntered(qtyEntered);
				charge.setQtyOrdered(det.getQtyOrdered());
				charge.setQtyDelivered(det.getQtyDelivered());

				charge.setM_Warehouse_ID(getWarehouseID());
				charge.setC_Tax_ID(impuesto.getC_Tax_ID());
				charge.setProductCategory(det.getLote());

				charge.setDateOrdered(det.getDateOrder() == null ? Env.getCurrentDate() : det.getDateOrder());
				charge.setDateDelivered(det.getDateDeliv() == null ? Env.getCurrentDate() : det.getDateDeliv());
				// datos de ActPacienteInd / ActPacienteIndH
				if (mActPacienteInd != null) {
					charge.setM_Warehouse_Sol_ID(mActPacienteInd.getActPacienteIndH().getM_Warehouse_Sol_ID());
					charge.setAD_OrgTrx_ID(mActPacienteInd.getActPacienteIndH().getAD_OrgTrx_ID());
					charge.setEXME_ActPacienteInd_ID(mActPacienteInd.getEXME_ActPacienteInd_ID());
				}

				// Faltaban datos antes de obtener los precios
				charge.completeCharge(getWarehouseID());

				// Obtener el precio
				if (det.isBuscarPrecio() && (det.getPrecio() == null || det.getPrecio().compareTo(BigDecimal.ZERO) <= 0)) {
					charge.prices();
				} else {
					final MPrecios precioLoc = new MPrecios();
					precioLoc.setM_Product_ID(prod.get_ID());
					charge.setPrecios(precioLoc);
					charge.getPrecios().preciosActual(charge);
				}

				charge.setProductCost();
				charge.setLstModificadores(lstModificadores);
				charge.setBillingType(det.getBillingType());
				// Recalcula totales
				charge.completeCharge(getWarehouseID());
				charge.setM_Locator_ID(det.getLocatorId());
				charge.setM_AttributeSetInstance_ID(det.getAttributeSetInstanceID());
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			errores.add(new ModelError(ModelError.TIPOERROR_Error, "error.existeProd", prod.getName()));
			charge = null;
			// Actualiza el log de error
			if (det.isProcess()) {
				logError(e.getLocalizedMessage(),//
					e instanceof IllegalArgumentException ? //
						MEXMEActPacienteIndCgo.MANDATORY : MEXMEActPacienteIndCgo.OTHER);
			}
		}

		return charge;
	}

	/**
	 * Validar 
	 * 
	 * @param det objeto con las propiedades del cargo
	 * @return
	 * LAMA: se utiliza un solo objeto en lugar de mandar tantos parametros
	 */
	private boolean validateCharge(final CtaPacDet det) {
		StringBuilder error = new StringBuilder();
		String type = MEXMEActPacienteIndCgo.CONFIGURATION;
		error.append("Product: ");
		error.append(prod.getValue()).append("-");
		error.append(prod.getName()).append(Constantes.NEWLINE);
		
		// Cantidad > 0 para cancelar debe ser la linea
		if (BigDecimal.ZERO.compareTo(det.getQtyDelivered()) >= 0) {
			error.append("Qty Zero").append(Constantes.NEWLINE);
			type = MEXMEActPacienteIndCgo.BUSSINESSRULE;
			errores.add(new ModelError(ModelError.TIPOERROR_Error, "msj.cargoCero", prod.getValue() + " - " + prod.getName()));
		}
		// Controla existencia y no registra existencias
		if (!validateStock(det)) {
			error.append("No Stock").append(Constantes.NEWLINE);
		}
		// Define y valida el impuesto relacionado al producto.
		impuesto = MEXMETax.getImpuestoProducto(ctx, prod.get_ID(), null);
		if (impuesto == null) {
			log.log(Level.WARNING, "if (impuesto == null)" + error + " producto ID - Value = " + prod.get_ID() + " - " + prod.getValue());
			error.append("Tax ZERO").append(Constantes.NEWLINE);
			errores.add(new ModelError(ModelError.TIPOERROR_Error, "error.configuracionTax"));
		}

		// Validamos si el producto esta programado para el paciente
		if (!det.isProcess()) {
			try {
				if (MEXMEActPacienteInd.getSolicitud(prod.getM_Product_ID(), ctaPac.getEXME_CtaPac_ID()) > 0) {
					log.log(Level.SEVERE, "Service request exists");
					errores.add(new ModelError(ModelError.TIPOERROR_Error, "msj.exiteSolServicio"));
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		if(errores.isEmpty()) {
			log.log(Level.SEVERE, "EnterCharge.validateAdd", error);
			// Actualiza el log de error
			if (det.isProcess()) {
				logError(error.toString(), type);
			}
		}
		
		return errores.isEmpty();
	}

	/**
	 * Valida que este disponbile la cantidad del cargo
	 * 
	 * @param det objeto con las propiedades del cargo
	 * @return
	 *         LAMA: se utiliza un solo objeto en lugar de mandar tantos parametros
	 */
	public boolean validateStock(final CtaPacDet det) {
		StringBuilder msg = new StringBuilder();
		
		if (prod.isStocked() //
			&& !det.isProdOrTray// no hay que validar en confirmacion
			&& configMej.isControlExistencias()
			// Si es que no se permiten existencias en negativo
			// se comenta pq es para estados unidos, validar si aun aplicara
//			&& configPre.isCreateInventory()
			) {
			msg .append( MStorage.validateStock(prod, det.getUoMSale(),//
						det.getQtyDelivered(), //
						det.getWarehouseId(), //
						det.getLocatorId(),//
						det.getAttributeSetInstanceID()));
		}
		
		if (msg.length() > 0) {
			errores.add(new ModelError(false, ModelError.TIPOERROR_Error, msg.toString()));
		}
		return msg.length() == 0;
	}

	/**
	 * Log de errores guarda los erroes en MEXMEActPacienteIndCgo
	 * @param error mensaje de error
	 * @param type tipo de error
	 */
	private void logError(final String error, final String type) {
		// Actualiza el log de error
		if (logCgo != null && logCgo.getType() == null && StringUtils.isNotBlank(error)) {
			logCgo.setErrorMsg(error);
			logCgo.setType(type);
			logCgo.setClassname(getClass().getName());
			logCgo.save();
		}
	}


	/**
	 * process
	 * 
	 * @param lines
	 *            : listado con objetos MCtaPacDet
	 * @param headerServ
	 *            : Modelo de EXME_ActPacienteIndH
	 * @param process NO SE USA, DEPENDE DE headerServ
	 *            : true si viene de una opcion diferente a Enter Charges
	 * @param service NO SE USA
	 *            : true si es un servicio M_Product.ProductType
	 * @param forcePrice NO SE USA
	 *            : true si requiere mensajes de error por falta de precio
	 * @param inventory
	 *            : true si hace la salida de inventario FIXME NO SE USA !! 
	 *            CONSIDERA UNICAMENTE updateInventory (por defecto TRUE)
	 * @param pTrxName
	 *            : Nombre de transaccion cuando process es true
	 * @return true : cuando la tabla MCtaPacDet se haya afectado
	 */
	public boolean insertAllCharges(final List<MCtaPacDet> lines,//
		//final MEXMEActPacienteIndH headerServ, //
		final String pTrxName) {//
		if (pTrxName == null || StringUtils.isEmpty(pTrxName) || Trx.get(pTrxName, false) == null) {
			return insertCharges(lines);
		} else {
			return insertCharges(lines, pTrxName);
		}
	}
	
	/**
	 * Inserta los cargos de la lista
	 * 
	 * @param lines
	 *            : listado con objetos MCtaPacDet (obligatoria)
	 * @param actPacienteIndH
	 *            : Modelo de EXME_ActPacienteIndH
	 * @param pTrxName
	 *            : Nombre de transaccion (obligatoria)
	 * @return true : cuando la tabla MCtaPacDet se haya afectado
	 */
	public boolean insertCharges(final List<MCtaPacDet> lines
//			, final MEXMEActPacienteIndH actPacienteIndH
			, final String trxName) {//
		final List<Inventariado>    lstStock   = new ArrayList<Inventariado>();
		final ErrorList errors = new ErrorList();
		boolean isDevol = false;
		try {
			// Deben de existir lineas
			if (lines == null || lines.isEmpty()) {
				log.log(Level.SEVERE, "EnterCharge#insertCharges >> CtaPacDetlist isEmpty ");
				errores.add(new ModelError(ModelError.TIPOERROR_Error, "captCargo.isEmpty"));
			
			} else {
				// Crear cargo
				try {
					// Nombre de transaccion
					ctaPac.set_TrxName(trxName);
//					if (actPacienteIndH != null) {// desde aplicacion de servicios es NULL
//						actPacienteIndH.set_TrxName(trxName);
//						ctaPac.setActPacienteIndH(actPacienteIndH);
//					}

//					final Timestamp actualTmp = Env.getCurrentDate();
//					//
//					boolean createActPac = false;

					// Charge
					for (final MCtaPacDet cargo : lines) {
						
						cargo.set_TrxName(trxName);
						cargo.completeCharge(cargo.getM_Warehouse_ID() > 0 ? cargo.getM_Warehouse_ID() : getWarehouseID());
						MRequest.createRequest(cargo, trxName);
						
//						//aplicacion de servicios
//						if (configPre.isCreaActividad() && actPacienteIndH != null) {
//							if (cargo.completeActPac(actualTmp, actPacienteIndH)) {
//								createActPac = true;
//							} else {
//								errores.add(new ModelError(ModelError.TIPOERROR_Error, "error.notasMed.servicios.header"));
//							}
//						}
						
						if (cargo.save()) {// no se requiere transaccion, ya que se  asigno en lineas anteriores
							
							
							// modificadores para el claim (USA)
							if(MCountry.isUSA(ctx) && cargo.getLstModificadores() != null) {
								cargo.insertModifiers();//modificadoresSave(cargo); 
							}
							
							
							// Salida de inventario a lineas que no sean cargos, ya que los cargos se surten en la opción surtido de farmacia
							if (cargo.getM_Product_ID() > 0 && cargo.getProduct().isProduct()) {
								final Inventariado lInv = MInOut.addLineInventory(errors, cargo, cargo.getActPacienteInd());
								if(lInv==null) {
									if(!errors.isEmpty()){
										break;
									}
								}  else {
									if(!isDevol){
										isDevol = cargo.getQtyDelivered().signum()<0;
									}
									lstStock.add(lInv);
								}
							}
							
							
						} else {
							errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir"));
							log.log(Level.SEVERE, "insertarMovimientosMed : Error en : if (!cargo.save(trxName))");

							if (cargo.getEXME_ActPacienteInd_ID() > 0) {
								logCgo = MEXMEActPacienteIndCgo.get(ctx, cargo.getEXME_ActPacienteInd_ID(), true, trxName);
								logError("save() false", MEXMEActPacienteIndCgo.SAVEFALSE);
							}
						}
						
					}//fin_for
					
					// close ctapac
					if (errores.isEmpty()) { // complete encounter
//						if (actPacienteIndH != null && createActPac) {
//							actPacienteIndH.updateActPacIndH();
//						}
						if (completeEncounter() == null) {
							errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir"));
						}
					}
					
					//setLinesCargos(lines);// inventory
					
				} catch (Exception e) {
					log.log(Level.SEVERE, "Error al surtir: ", e);
					errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir", e.getMessage()));
				}
				
				// Hacer la salida de inventario
				// con el objeto del cargo
				if (errores.isEmpty() && updateInventory && !isNimboMx) {
					// afterProcess(lines, trxNameEC)
					// Crear inventario bajo el mismo nombre de transaccion
					// Se procederá a realizar un movimiento de salida de inventario
					// negativo
					// (Incrementa Existencias). para el caso de la devolucion
					// cuando el cargo proviene de ninbo
					// se hace la salida de inventario
					// hasta elmomento del surtido en farmacia
					// FIXME: Cambiar Inventario --> inout x cada linea
//					if (!inventory(lstCharges, false, trxName)) {
//						log.log(Level.SEVERE, "Shipment=false EnterCharge.process.afterProcess");
//						// se comenta pq es para estados unidos, validar si aun aplicara
//						if (configMej.isControlExistencias() /*&& configPre.isCreateInventory()*/) {
//							// Si controla existencias y no pending envia mensaje de error al  usuario
//							errores.add(new ModelError(ModelError.TIPOERROR_Error, "error.complete.inOut"));
//						}
//					}
					
					// Salida de inventario
					MInOut.updateStock(lstStock, errors, ctaPac, ctaPac.getActPacienteIndH()==null?ProcessesCharges.CARGOSACUENTA:ProcessesCharges.APLICARSERVICIOS, isDevol);

					if(!errors.isEmpty()){
						errores.add(new ModelError(ModelError.TIPOERROR_Error, errors.toString()));
					}
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error al crear el cargo: ", e);
			errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir", e.getMessage()));
		} finally {
			if (ctaPac != null) {
				ctaPac.set_TrxName(null);
			}
		}
		return errores.isEmpty();
	}
	
	/**
	 * Inserta los cargos, crea su propia transaccion
	 * 
	 * @param lines
	 *            : listado con objetos MCtaPacDet
	 * @param headerServ
	 *            : Modelo de EXME_ActPacienteIndH
	 * @param process NO SE USA, DEPENDE DE headerServ
	 *            : true si viene de una opcion diferente a Enter Charges
	 * @param service NO SE USA
	 *            : true si es un servicio M_Product.ProductType
	 * @param forcePrice NO SE USA
	 *            : true si requiere mensajes de error por falta de precio
	 * @param inventory
	 *            : true si hace la salida de inventario FIXME NO SE USA !! 
	 *            CONSIDERA UNICAMENTE {@link #updateInventory} (por defecto TRUE)
	 * @param pTrxName NO SE USA SE CREA LA TRANSACCION
	 *            : Nombre de transaccion cuando process es true
	 * @return true : cuando la tabla MCtaPacDet se haya afectado
	 */
	public boolean insertCharges(final List<MCtaPacDet> lines//
		//final MEXMEActPacienteIndH headerServ //,
		) {//
		Trx trx = null;
		boolean success = false;
		try {
			// Deben de existir lineas
			if (lines == null || lines.isEmpty()) {
				log.log(Level.SEVERE, "EnterCharge#insertCharges >> CtaPacDetlist isEmpty ");
				errores.add(new ModelError(ModelError.TIPOERROR_Error, "captCargo.isEmpty"));
			} else {
				trx = Trx.get(Trx.createTrxName("Charges"), true);
				// Crear cargos
				if(insertCharges(lines, trx.getTrxName())) {
					success = true;
					Trx.commit(trx);
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error al crear el cargo: ", e);
			errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir", e.getMessage()));
		} finally {
			Trx.close(trx, true);
			if (trx != null) {
				for (MCtaPacDet mCtaPacDet : lines) {
					mCtaPacDet.set_TrxName(null);
				}
			}
			if (success) {
				success = errores.isEmpty();
			}
		}
		return success;
	}


	/**
	 * close
	 */
	public void close() {
	}

	/**
	 * reset
	 * 
	 * @param all
	 */
	public void reset(final boolean all) {
		prod = null;
	}

	/**
	 * Guardar el registro de la dosis administrada en exme_actpacienteindh y
	 * exme_actpacienteind
	 * 
	 * @param header
	 * @param headerServ
	 * @param trxNameEC
	 * @return null, el proceso no se ejecuto correctamente
	 */
	private String completeEncounter() {
		// sino se crea el cargo a la cuenta paciente,
		// no se crea la salida de inventario
		// Cuenta paciente
		String status = ctaPac.prepareIt();// status =
		// DocAction.STATUS_Completed;
		if (!DocAction.STATUS_InProgress.equals(status)) {
			errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir", ctaPac.getM_processMsg()));
			return null;
		}

		// Cambia estatus
		status = ctaPac.completeIt();

		ctaPac.setDocStatus(status);
		if (!DocAction.STATUS_Completed.equals(status)) {
			errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir", ctaPac.getM_processMsg()));
			return null;
		}

		if (!ctaPac.save()) {
			errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir", ctaPac.getM_processMsg()));
			return null;
		}
		return status;
	}

	
//	/**
//	 * inventory
//	 * @param lines cargos
//	 * @param forceStorageUpdate - En caso de no haber existencias, omite la validacion y continua al proceso (solo para movement)
//	 * @param trxName
//	 * @return
//	 * @deprecated Quitar la referencia {@link Inventory} 
//	 * y crear directamente el INOUT por cada cargo, sin iterar nuevamente
//	 */
//	protected boolean inventory(final List<MCtaPacDet> lines, boolean forceStorageUpdate, final String trxName)  {
//		final Inventory mInventory = new Inventory(ctx
//					, ctaPac.getEXME_CtaPac_ID() // cta
//					, X_EXME_ActPacienteInd.Table_ID // tabla
//			);
//		
//		if(!mInventory.createLinesInOut(lines, forceStorageUpdate, trxName)){
//			errores.addAll(mInventory.getErrores());
//		}
//		return errores.isEmpty();
//	}

	public boolean isManejaHL7() {
		return MConfigEC.get(ctx, null).isInterfaz_HL7() && warehouse.isGenera_HL7();
	}

	public int getWarehouseID() {
		return warehouse.get_ID();
	}

	public MProduct getProd() {
		return prod;
	}
	
	public int getProdId() {
		return prod == null ? -1 : prod.get_ID();
	}

	public MEXMECtaPac getCtaPac() {
		return ctaPac;
	}

	public MWarehouse getWarehouse() {
		return warehouse;
	}

	public List<ModelError> getErrores() {
		return this.errores;
	}

	public List<Integer> getLstModificadores() {
		return lstModificadores;
	}

	public void setLinesCargos(final List<MCtaPacDet> linesCargos) {
		this.lstCharges = linesCargos;
	}

	public List<MCtaPacDet> getLstCharges() {
		return lstCharges;
	}

	public void setProd(final MProduct prod) {
		this.prod = prod;
	}
	
	/**
	 * @return Configuracion mejoas
	 */
	public MEXMEMejoras getConfigMej() {
		return configMej;
	}
	
	public int getLocatorCtx() {
		return locatorCtx;
	}
	
	/**
	 * Create Charge (crear cargos sin expediente )
	 * Para los procesos de traspasos entre almacenes
	 * 
	 * @param plines listado de carogs
	 * @param isProdOrTray  si es producto o charola, 
	 * 						es decir proviene de M_Movement (sera removido: NO se usa)
	 *                      si valida o no existencias
	 * @param trxName
	 * @return mensaje de error, vacio ("") si fue existoso
	 */
	public String insertMovementCharges(final List<MCtaPacDet> plines, boolean isProdOrTray, String trxName) {
		if (!plines.isEmpty()) {
			// Creacion de cuenta paciente desde la confirmación de traspaso entre almacenes
			try {// Nombre de transaccion
				ctaPac.set_TrxName(trxName);
				// Charge
				for (final MCtaPacDet cargo : plines) {
					cargo.set_TrxName(trxName);
					cargo.completeCharge(getWarehouseID());
					if (!cargo.save()) {
						errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir"));
						break;
					}
				}
				if (errores.isEmpty()) {
					ctaPac.setDocStatus(ctaPac.completeIt());
					if (DocAction.STATUS_Completed.equals(ctaPac.getDocStatus()) && ctaPac.save()) {
						setLinesCargos(plines);
					} else {
						errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir", ctaPac.getM_processMsg()));
					}
				}
			} catch (Exception e) {
				errores.add(new ModelError(ModelError.TIPOERROR_Error, "surtPedidoAction.error.surtir", e.getMessage()));
			}
//			//  Cambiar Inventario --> inout x cada linea
//			if (errores.isEmpty()) {
//				inventory(plines, true, trxName);
//			}
		}
		return errores.isEmpty() ? "" : ModelError.getMsgError(ctx, errores);
	}
	
	/**
	 * Gets the price of the charge
	 * @param ext0 MANDARTORY ! Extension #0 record (to get Business Partner, Encounter and Exntension)
	 * @param cUOMid Unit of measure of the charge, uses product minumon uom when is zero
	 * @param chargeDate Date of the charge
	 * @return
	 * @throws NullPointerException when ext0 is null
	 * Para reutilizar codigo de AbstractCharges
	 */
	public MPrecios getPrice(MEXMECtaPacExt ext0, int cUOMid, Timestamp chargeDate) {
		// Precio del cargo de la cuenta paciente
		return GetPrice.getPriceCgo(ctx
				, getProd().getM_Product_ID() //
				, ext0.getC_BPartner_ID() //
				, cUOMid>0?cUOMid:getProd().getC_UOM_ID() // 
				, chargeDate //
				, ext0.getEXME_CtaPacExt_ID()//
				, ext0.getCtaPac().getTipoPaciente().getTipoArea()//
				, ext0.getCtaPac().getEXME_CtaPac_ID()//
				, null); //Ealvarez Se agrega el Id de la  extesion
	}
	
}