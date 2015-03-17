/*
 * Created on 22-mar-2005
 *
 */

package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;
import org.compiere.model.bpm.BeanView;
import org.compiere.model.bpm.GetCost;
import org.compiere.model.bpm.GetPrice;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;
import org.compiere.util.ValueNamePair;
import org.compiere.util.vo.ServicesVO;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;
import com.ecaresoft.util.PackageBalance;

/**
 * Detalle de cuenta paciente. Cualquier cambio en la tabla EXME_CtaPacDet en
 * cuestion de estructura debera tambien realizarse en cambio en la estructura
 * de la tabla EXME_T_CtaPacCargos, para no afectar el calculo del estado de
 * cuenta y saldos de reportes.
 * 
 * @author Hassan reyes
 * 
 */
public class MCtaPacDet extends X_EXME_CtaPacDet /* implements Comparable */{

	/** serialVersionUID */
	private static final long serialVersionUID = 1499310887949245420L;
	/** Static Logger */
	private static CLogger slog = CLogger.getCLogger(MCtaPacDet.class);
	/** Validaables miembro */
	private MEXMECtaPacExt mCtaPacExt = null;
	/** objeto de la cuenta paciente */
	private MEXMECtaPac mCtaPac = null;
	/** Objeto del producto */
	private MProduct mProduct = null;
	/** */
	private MProduct productSinTrx = null;
	/** */
	private MProduct product = null;
	/** UOM en la cual se va a facturar. */
	private MUOM m_uom = null;
//	/** */
//	private MUOM uomInvoiceSinTrx = null;
//	/** */
//	private MUOM uomInvoice = null;
	/** Objeto con los precios del cargo */
	private MPrecios precios;
	/** Actividad paciente */
	private MEXMEActPacienteInd actPacienteInd = null;
	/** */
	private MPlanMedLine planMedLine;
	/** */
	private MWarehouse mWarehouse = null;
	/** */
	private MCharge charge = null;
//	/** */
//	private MEXMEPaqBaseVersion paqBaseVersion = null;
	/** */
	private BeanView bean4String = null;
//	/** Indica si la linea esta selecionada en la pantalla. */
//	private boolean selected = false;
	/** */
	public static boolean checkReponse = false;
	/** considerado como factura especial */
	public boolean factEsp = false;
//	/** Cantidad (UOM de venta) a facturar. */
//	private BigDecimal qtyEntred = Env.ZERO;
	/** */
	public BigDecimal importeActualInv = Env.ZERO;
//	/** cantidad de cargo - cantidad de devolucion */
//	private BigDecimal qtyDevolucion = Env.ZERO;
//	/** numero de serie */
//	private String numSerie = null;
	/** */
	private String nombreProd = null;
	/** */
	private String productName = null;
	/** */
	private String productValue = null;
	/** */
	private String nameEstServ = null;
//	/** Lista de datalles de Lotes por cargo */
//	private List<MInOutLine> lstSurtidoDet = null;
//	/** */
//	private int excepcion = 0;
	/** categoria de producto */
	public int productCategoryID = 0;
	/** categoria de producto */
	public int prodCatId = 0;
	/** redondeo para el usuario */
	public static final int REDONDEO2_Usuario = 2;
	/** redondeo para los calculos */
	public static final int REDONDEO6_Calculos = 6;
//	/**
//	 * Cuenta las LP que hacen match para esta l�nea de extensi�n, con el fin de
//	 * que al momento de calcular los descuentos s�lo tome en cuenta las l�neas
//	 * que tienen un valor mayor a 0
//	 ***/
//	private int esqDescLineCount;
	/** Modificadores del cargo */
	private List<Integer> lstModificadores = new ArrayList<Integer>();
//	/** log cargo */
//	private MEXMEActPacienteIndCgo logCgo;
	/** tasa de impuesto */
	private MTax mTax = null;
	/** tipo de linea como en cargos a cuenta paciente */
	private String subTipoLinea = null;
	/** Cantidad (UOM de venta) a facturar. */
	private BigDecimal qtyPack = Env.ZERO;
//	/** factura */
//	private int invoiceId = 0;
	/** */
	private BigDecimal precioCambio = Env.ZERO;
	/** Nombre de extension y numero de extension */
	private LabelValueBean extLVB = new LabelValueBean(Utilerias.getLabel("factDirecta.tab.particular"), "0");
	/** Localizador seleccionado por el usuario */
	private int mLocatorUserID = 0;
	/** Objeto del esquema de descuento o convenio */
	private MEXMEEsqDesLine mEsqDesLine = null;
	/** Bean en apoyo a obtener datos */
	private BeanView beanID = null;
	/** */
	private boolean packed = false;
	/** Evaluar existencias */
	private boolean reloadStorage = true;
	
	public boolean isReloadStorage() {
		return reloadStorage;
	}

	public void setReloadStorage(boolean reloadStorage) {
		this.reloadStorage = reloadStorage;
	}

	/**
	 * Crea una copia a partir de un origen, estableciendole nuevamente el ID de
	 * la extension.
	 * 
	 * @param origin
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 */
	public void copyFrom(final MCtaPacDet origin) {
		PO.copyValues(origin, this);
		setLine();
		setC_UOM_ID(origin.getC_UOM_ID());
		//
		setDateDelivered(origin.getDateOrdered());
		setQtyOrdered(origin.getQtyDelivered());
		setQtyPendiente(origin.getQtyDelivered());//
		setQtyPaquete(origin.getQtyDelivered());//
		//
		setPriceActual(origin.getPriceActual());
		setPriceLimit(origin.getPriceLimit());
		setPriceList(origin.getPriceList());
		setPricesInv();
		setLineNetAmt(origin.getLineNetAmt());
		//
		setActPacienteInd(origin.getActPacienteInd());
		setLstModificadores(origin.getLstModificadores());
		setPrecios(origin.getPrecios());
	}

	/**
	 * Crea una copia a partir de un origen, estableciendole nuevamente el ID de
	 * la extension.
	 * 
	 * @param origin
	 * @param EXME_CtaPacExt_ID
	 * @param trxName
	 * @return
	 */
	public static MCtaPacDet copyFrom(MCtaPacDet origin, int EXME_CtaPacExt_ID,
			String trxName) {

		MCtaPacDet charge = new MCtaPacDet(origin.getCtaPac(),
				EXME_CtaPacExt_ID, origin.getEXME_Area_ID(), trxName);
		charge.copyFrom(origin);
		//CopyFrom invoca desde BD por lo que no ve el cambio de extensión asignado 
		//en caso de haberse dado el flujo .
		//Ticket # 0102478
		charge.setEXME_CtaPacExt_ID(EXME_CtaPacExt_ID);
		return charge;
	}

	/**
	 * @param ctx
	 * @param EXME_CtaPacDet_ID
	 * @param trxName
	 */

	public MCtaPacDet(Properties ctx, int EXME_CtaPacDet_ID, String trxName) {
		super(ctx, EXME_CtaPacDet_ID, trxName);

		if (EXME_CtaPacDet_ID == 0) {

			setDateOrdered(DB.convert(getCtx(), new Date()));
			setQtyOrdered(Env.ZERO);
			setQtyDelivered(Env.ZERO);
			setQtyInvoiced(Env.ZERO);
			setQtyReserved(Env.ZERO);
			setQtyEntered(Env.ZERO);
			setQtyPaquete(Env.ZERO);
			setQtyPendiente(Env.ZERO);

			setPriceActual(Env.ZERO);
//			setPriceActualInv(Env.ZERO);
			setPriceLimit(Env.ZERO);
//			setPriceLimitInv(Env.ZERO);
			setPriceList(Env.ZERO);
//			setPriceListInv(Env.ZERO);
			setPricesInv();
			setLineNetAmt(Env.ZERO);
			setFreightAmt(Env.ZERO);
			setChargeAmt(Env.ZERO);

			setIsDescription(false);
			setUsarFactor(false);

			// Tipo de linea Cargo por default Expert:aaranda 05082010
			setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
			setVisible(true); // Default Tipo de Linea visible.
			setCgoProcesado(false);
			setPrecioPublico(Env.ZERO);
			setSeDevolvio(false);
			setCalcularPrecio(false);// true: Cuando el precio es cero y ese
										// precio es correcto
		}
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MCtaPacDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Creamos un cargo a la cuenta paciente.
	 * 
	 * @param ctaPac
	 */

	public MCtaPacDet(MEXMECtaPac ctaPac, int EXME_CtaPacExt_ID,
			int EXME_Area_ID, String trxName) {

		this(ctaPac.getCtx(), 0, trxName);
		setClientOrg(ctaPac);
		setCtaPac(ctaPac);

		// Tipo de linea Cargo por default Expert:aaranda 05082010
		setTipoLinea(X_EXME_CtaPacDet.TIPOLINEA_Charge);
		setVisible(true); // Alejandro.- Tipo de Linea visible.

		if (EXME_Area_ID > 0) {
			MEXMEArea area = new MEXMEArea(getCtx(), EXME_Area_ID,
					get_TrxName());
			setTipoArea(area.getTipoArea());
			setEXME_Area_ID(EXME_Area_ID);
		}

		setDateOrdered(DB.convert(getCtx(), new Date()));
		if (EXME_CtaPacExt_ID == 0) {
			// Determinamos la extension cero.
			setEXME_CtaPacExt_ID(MEXMECtaPacExt.getExtCero(getCtx(),
					getEXME_CtaPac_ID(), get_TrxName()).getEXME_CtaPacExt_ID());

		} else {
			setEXME_CtaPacExt_ID(EXME_CtaPacExt_ID);
		}
	}

//	/**
//	 * Creamos un cargo a la cuenta paciente a partir de un embarque.
//	 * 
//	 * @return
//	 */
//	public static MCtaPacDet createFrom(MEXMECtaPac ctaPac,
//			int EXME_CtapacExt_ID, int EXME_Area_ID, MInOutLine inOutLine) {
//
//		MCtaPacDet ctaPacDet = new MCtaPacDet(ctaPac, EXME_CtapacExt_ID,
//				EXME_Area_ID, ctaPac.get_TrxName());
//
//		// Verificamos si el embarque proviene de un movimiento entre almacenes.
//		if (inOutLine.getMovementLine() == null) {
//			ctaPacDet.setM_InOutLine_ID(inOutLine.getM_InOutLine_ID());
//		} else {
//			ctaPacDet.setM_MovementLine_ID(inOutLine.getMovementLine()
//					.getM_MovementLine_ID());
//		}
//
//		MProduct product = new MProduct(ctaPac.getCtx(),
//				inOutLine.getM_Product_ID(), ctaPac.get_TrxName());
//
//		ctaPacDet.setM_Product_ID(product.getM_Product_ID());
//		ctaPacDet.setM_Warehouse_ID(inOutLine.getM_Warehouse_ID());
//		// ctaPacDet.setM_Warehouse_Sol_ID(getM_Warehouse_Sol_ID());
//		/*
//		 * Si la unidad en que salio el producto del almacen es diferente a la
//		 * unidad de almacenamiento (product.getC_UOM_ID), se hace la conversi�n
//		 * a unidad de venta.
//		 */
//		BigDecimal qty = inOutLine.getMovementQty();
//		if (product.getC_UOM_ID() != inOutLine.getC_UOM_ID()) {
//
//			qty = MEXMEUOMConversion.convertProductTo(ctaPac.getCtx(),
//					product.getM_Product_ID(), inOutLine.getC_UOM_ID(),
//					inOutLine.getMovementQty(), null);
//
//		}
//
//		ctaPacDet.setC_UOM_ID(inOutLine.getC_UOM_ID());
//		ctaPacDet.setQty(qty);
//		ctaPacDet.setQtyPendiente(qty);//
//		ctaPacDet.setQtyPaquete(qty);//
//
//		try {
//
//			final MTax tax = MEXMETax.getImpuestoProducto(
//					ctaPacDet.getCtx(), ctaPacDet.getM_Product_ID(),
//					ctaPac.get_TrxName());
//			if (tax != null) {
//				ctaPacDet.setC_Tax_ID(tax.getC_Tax_ID());
//			}
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, e.getLocalizedMessage(), e);
//
//		}
//
//		return ctaPacDet;
//
//	}

	/**
	 * Establece el siguiente numero de linea por Extension.
	 */
	public void setLine() {
		int lineNo = 0;
		setLine(lineNo);
	}

	/**
	 * Extension del cargo se instancia cuando m_CtaPacExt esta vacio
	 * 
	 * @return
	 */
	public MEXMECtaPacExt getExtension() {
		if (mCtaPacExt == null) {
			mCtaPacExt = new MEXMECtaPacExt(getCtx(), getEXME_CtaPacExt_ID(),
					get_TrxName());
		}
		return mCtaPacExt;

	}

//	/**
//	 * Indica si la linea esta selecionada en la pantalla.
//	 */
//	public boolean getSelected() {
//		return selected;
//	}
//
//	public void setSelected(boolean selected) {
//		this.selected = selected;
//	}

	public String getSubTipoLinea() {
		return subTipoLinea;
	}

	public void setSubTipoLinea(String subTipoLinea) {
		this.subTipoLinea = subTipoLinea;
	}

	/**
	 * Obtenemos le producto.
	 * 
	 * @return
	 */
	public MProduct getProduct() {

		if (mProduct == null || mProduct.getM_Product_ID() == 0) {
			mProduct = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		}
		return mProduct;
	}

//	/**
//	 * Upc
//	 * 
//	 * @return
//	 */
//	public String getUpc() {
//		String upc = "";
//		if (mProduct != null)
//			upc = mProduct.getUPC();
//
//		return upc;
//	}

	/**
	 * Unidad de medida en la cual se va a facturar.
	 * 
	 * @return
	 */
	public MUOM getUom() {

		if (m_uom == null || m_uom.getC_UOM_ID() != getC_UOM_ID()) {
			m_uom = new MUOM(getCtx(), getC_UOM_ID(), get_TrxName());
		}
		return m_uom;
	}

	/**
	 * CtaPac
	 * 
	 * @return
	 */
	public MEXMECtaPac getCtaPac() {
		if (mCtaPac == null || mCtaPac.getEXME_CtaPac_ID() == 0) {
			mCtaPac = new MEXMECtaPac(getCtx(), getEXME_CtaPac_ID(),
					get_TrxName());
		}
		return mCtaPac;
	}

	/**
	 * Le asignamos la cuenta paciente.
	 * 
	 * @param ctaPac
	 */
	public void setCtaPac(MEXMECtaPac ctaPac) {
		if (ctaPac != null) {
			setEXME_CtaPac_ID(ctaPac.getEXME_CtaPac_ID());
			setC_Currency_ID(ctaPac.getC_Currency_ID());
			mCtaPac = ctaPac;
		}

	}

//	public List<MInOutLine> getLstSurtidoDet() {
//		return lstSurtidoDet;
//	}

//	public void setLstSurtidoDet(List<MInOutLine> lstSurtidoDet) {
//		this.lstSurtidoDet = lstSurtidoDet;
//	}

	public MEXMEActPacienteInd getActPacienteInd() {
		return actPacienteInd;
	}

	public void setActPacienteInd(final MEXMEActPacienteInd actPacienteInd) {
		this.actPacienteInd = actPacienteInd;
	}

//	public void actPacienteInd(MCtaPacDet cargo) {
//		MEXMEActPacienteInd actPaciente = new MEXMEActPacienteInd(getCtx(), 0,
//				get_TrxName());
//		actPaciente.createLine(cargo, null, null);
//		setActPacienteInd(actPaciente);
//	}

	/**
	 * String
	 */
	public String toString() {
		return "Producto: " + getProduct().getName() + ", UOM: "
				+ getUom().getName() + ", QtyOrdered: " + getQtyOrdered()
				+ ", QtyDelivered: " + getQtyDelivered() + ", QtyInvoiced: "
				+ getQtyInvoiced();// + ", Cant despues de Devolucion: "
				//+ getQtyDevolucion();
	}

//	/**
//	 * La cantidad que me queda despues de las devoluciones valor maximo
//	 * qtyDelivered valor minimo 0
//	 * 
//	 * @return
//	 */
//	public BigDecimal getQtyDevolucion() {
//		return qtyDevolucion;
//	}
//
//	/**
//	 * Permite almacenar temporalmente la cantidad cargada menos devoluciones
//	 * 
//	 * @param qtyDevolucion
//	 */
//	public void setQtyDevolucion(BigDecimal qtyDevolucion) {
//		if (qtyDevolucion.compareTo(Env.ZERO) <= 0)
//			this.qtyDevolucion = qtyDevolucion;
//	}

	/**
	 * Permite almacenar temporalmente la cantidad que incluye el paquete
	 * 
	 * @return
	 */
	public BigDecimal getQtyPack() {
		return qtyPack;
	}

	public void setQtyPack(BigDecimal qtyPack) {
		this.qtyPack = qtyPack;
	}

	/**
	 * Obtenemos el detalle de la cuenta paciente.
	 * 
	 * @return
	 */
	public static MCtaPacDet[] getDetalle(Properties ctx, int EXME_CtaPac_ID,
			String whereClause, String trxName) {

		ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_CtaPacDet.* FROM EXME_CtaPacDet ")
				.append(" LEFT JOIN EXME_CtaPacExt ON (EXME_CtaPacExt.EXME_CtaPacExt_ID = EXME_CtaPacDet.EXME_CtaPacExt_ID) ")
				.append(" WHERE EXME_CtaPacDet.IsActive = 'Y' ")
				.append(" AND EXME_CtaPacDet.EXME_CtaPac_ID = ? ");

		if (whereClause != null)
			sql.append(whereClause);

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "EXME_CtaPacDet"));

		sql.append(" ORDER BY EXME_CtaPacDet.EXME_CtaPacDet_ID ASC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MCtaPacDet ctaPacDet = new MCtaPacDet(ctx, rs, trxName);
				list.add(ctaPacDet);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		MCtaPacDet[] ctasPacDet = new MCtaPacDet[list.size()];
		list.toArray(ctasPacDet);

		return ctasPacDet;

	}

//	public BigDecimal getQtyEntred() {
//		return qtyEntred;
//	}
//
//	public void setQtyEntred(BigDecimal qtyEntred) {
//		this.qtyEntred = qtyEntred;
//	}

	/**
	 * Set Header Info
	 * 
	 * @param order
	 *            order
	 */
	public void setHeaderInfo(MEXMECtaPac order) {
		mCtaPac = order;
	} // setHeaderInfo

//	/**
//	 * Parent Constructor. ol.setM_Product_ID(wbl.getM_Product_ID());
//	 * ol.setQtyOrdered(wbl.getQuantity()); ol.setPrice();
//	 * ol.setPriceActual(wbl.getPrice()); ol.setTax(); ol.save();
//	 * 
//	 * @param order
//	 *            parent order
//	 */
//	public MCtaPacDet(MEXMECtaPac order) {
//		this(order.getCtx(), 0, order.get_TrxName());
//		if (order.get_ID() == 0)
//			throw new IllegalArgumentException("Header not saved");
//		setEXME_CtaPac_ID(order.getEXME_CtaPac_ID()); // parent
//		setCuentaPac(order);
//	} // MCtaPacDet

//	/**
//	 * Set Defaults from Order. Does not set Parent !!
//	 * 
//	 * @param order
//	 *            order
//	 */
//	public void setCuentaPac(MEXMECtaPac order) {
//		setClientOrg(order);
//		setDateOrdered(order.getDateOrdered());
//		setDatePromised(order.getDateOrdered());
//		setC_Currency_ID(order.getC_Currency_ID());
//		//
//		setHeaderInfoma(order); // sets m_order
//	} // setOrder

//	/**
//	 * Set Header Info
//	 * 
//	 * @param order
//	 *            order
//	 */
//	public void setHeaderInfoma(MEXMECtaPac order) {
//		mCtaPac = order;
//	} // setHeaderInfo

//	/**
//	 * Set Qty Entered/Ordered. Use this Method if the Line UOM is the Product
//	 * UOM
//	 * 
//	 * @param Qty
//	 *            QtyOrdered/Entered
//	 */
//	public void setQtyEntOrd(BigDecimal Qty) {
//		super.setQtyEntered(Qty);
//		super.setQtyOrdered(Qty);
//	} // setQty
//
//	public int getProductCategory_ID() {
//		return productCategoryID;
//	}
//
//	public void setProductCategory_ID(int productCategory_ID) {
//		this.productCategoryID = productCategory_ID;
//	}

	/**
	 * Permite traer todas los cargos
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static List<MCtaPacDet> getCargos(Properties ctx,
			int EXME_CtaPac_ID, String trxName) {
		return MCtaPacDet.getCargos(ctx, EXME_CtaPac_ID, "", null, trxName);
	}

	/**
	 * Obtiene todos los cargos de la cuenta que son candidatos para paquete
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta paciente
	 * @param trxName
	 *            Transacción
	 * @return Listado de cargos
	 */
	public static List<MCtaPacDet> getPackagesCandidates(Properties ctx, int ctaPacId, String trxName) {
		List<MCtaPacDet> list = new ArrayList<MCtaPacDet>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  exme_ctapacdet cpd ");
		sql.append("WHERE ");
		sql.append("  cpd.exme_ctapac_id = ? AND ");
		sql.append("  cpd.isactive = 'Y' AND ");
		sql.append("  cpd.tipoLinea = ? AND ");
		sql.append("  cpd.SeDevolvio = 'N' AND ");
		sql.append("  cpd.QtyDelivered > 0 AND ");
		sql.append("  cpd.isFacturado = 'N' ");
		sql.append("ORDER BY ");
		sql.append("  cpd.created asc ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacId);
			pstmt.setString(2, TIPOLINEA_Charge);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MCtaPacDet(ctx, rs, null));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

	/**
	 * Permite traer todas los cargos
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param where
	 * @param trxName
	 * @return
	 */
	public static List<MCtaPacDet> getCargos(Properties ctx,
			int EXME_CtaPac_ID, String where, List<Object> params,
			String trxName) {

		ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_CtaPacDet ")
				.append(" INNER JOIN EXME_CtaPacExt cpe ON (EXME_CtaPacDet.EXME_CtaPacExt_ID = cpe.EXME_CtaPacExt_ID AND cpe.IsActive='Y' ) ")
				.append(" LEFT JOIN m_product prod on  (EXME_CtaPacDet.M_Product_ID = prod.M_Product_ID ) ")
				.append(" WHERE EXME_CtaPacDet.IsActive = 'Y' ")
				.append(" AND EXME_CtaPacDet.EXME_CtaPac_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "EXME_CtaPacDet"));
		if (where != null)
			sql.append(where);
		sql.append(" ORDER BY EXME_CtaPacDet.EXME_CtaPacDet_ID ASC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			final List<Object> setParamlst = new ArrayList<Object>();
			setParamlst.add(EXME_CtaPac_ID);

			if (params != null && params.size() > 0) {
				setParamlst.addAll(params);
			}

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, setParamlst);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MCtaPacDet refValue = new MCtaPacDet(ctx, rs, trxName);
				list.add(refValue);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}

	/**
	 * Permite traer todas los cargos
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param where
	 * @param trxName
	 * @return
	 */
	public static List<MCtaPacDet> getCargosByExt(Properties ctx,
			int ctaPacExtID, String trxName) {

		ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT * FROM EXME_CtaPacDet ")
				.append(" INNER JOIN EXME_CtaPacExt cpe ON ")
				.append(" EXME_CtaPacDet.EXME_CtaPacExt_ID = cpe.EXME_CtaPacExt_ID AND cpe.IsActive='Y'  ")
				.append(" AND EXME_CtaPacDet.EXME_CtaPacExt_ID = ? ")
				// .append(" LEFT JOIN m_product prod on  (EXME_CtaPacDet.M_Product_ID = prod.M_Product_ID ) ")
				.append(" WHERE EXME_CtaPacDet.IsActive = 'Y' ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "EXME_CtaPacDet"));
		sql.append(" ORDER BY EXME_CtaPacDet.EXME_CtaPacDet_ID ASC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, ctaPacExtID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MCtaPacDet refValue = new MCtaPacDet(ctx, rs, trxName);
				list.add(refValue);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}

	/**
	 * Permite traer todas los cargos de una Extension No
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param where
	 * @param trxName
	 * @return
	 */
	public static List<MCtaPacDet> getCargosByTypeExtNo(Properties ctx,
			int ctaPacID, int extensionNo, String confType, String trxName) {

		ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_CtaPacDet.* FROM EXME_CtaPacDet ")
				.append(" INNER JOIN EXME_CtaPacExt cpe ON ")
				.append(" EXME_CtaPacDet.EXME_CtaPacExt_ID = cpe.EXME_CtaPacExt_ID AND cpe.IsActive='Y'  ")
				.append(" AND cpe.ExtensionNo = ? ")
				.append(" INNER JOIN EXME_ProductoOrg PO ")
				.append(" ON PO.exme_productoorg_id = fnc_getproductorg(EXME_CtaPacDet.M_Product_ID,EXME_CtaPacDet.AD_Org_ID) ")
				.append(" WHERE EXME_CtaPacDet.EXME_CtaPac_ID = ?  AND EXME_CtaPacDet.IsActive = 'Y' ");

		if (StringUtils.isNotEmpty(confType)) {
			sql.append(" AND fnc_IsProfessional(PO.EXME_ProductoOrg_ID, EXME_CtaPacDet.AD_Org_ID) = ?  ");
		}
		;

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "EXME_CtaPacDet"));
		sql.append(" ORDER BY EXME_CtaPacDet.EXME_CtaPacDet_ID ASC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, extensionNo);
			pstmt.setInt(2, ctaPacID);
			if (StringUtils.isNotEmpty(confType)) {
				pstmt.setString(
						3,
						confType.equals(Constantes.INST) ? Constantes.REG_NOT_ACTIVE
								: Constantes.REG_ACTIVE);
			}
			;

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MCtaPacDet refValue = new MCtaPacDet(ctx, rs, trxName);
				list.add(refValue);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return list;
	}


	/**
	 * Permite traer todas los cargos
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getTotalCargos(Properties ctx, int EXME_CtaPac_ID,
			String trxName) {

		BigDecimal retValue = BigDecimal.ZERO;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(
				" SELECT coalesce(sum(EXME_CtaPacDet.LineNetAmt),0) FROM EXME_CtaPacDet ")
				.append(" INNER JOIN EXME_CtaPacExt cpe ON (EXME_CtaPacDet.EXME_CtaPacExt_ID = cpe.EXME_CtaPacExt_ID AND cpe.IsActive='Y' ) ")
				.append(" WHERE EXME_CtaPacDet.IsActive = 'Y' ")
				.append(" AND EXME_CtaPacDet.EXME_CtaPac_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "EXME_CtaPacDet"));

		// sql.append(" ORDER BY EXME_CtaPacDet.EXME_CtaPacDet_ID ASC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retValue = rs.getBigDecimal(1);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return retValue;
	}

	/**
	 * Permite traer todas los cargos con problemas de la Extension 0
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param ConfType
	 *            Claim Configuration Type
	 * @param trxName
	 * @return
	 */
	public static List<MCtaPacDet> getCargosByClaim(Properties ctx,
			int EXME_CtaPacExt_ID, String confType, String trxName, int type) {

		ArrayList<MCtaPacDet> list = new ArrayList<MCtaPacDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT EXME_CtaPacDet.* FROM EXME_CtaPacDet ")
				.append(" INNER JOIN EXME_CtaPacExt cpe ON EXME_CtaPacDet.EXME_CtaPacExt_ID = cpe.EXME_CtaPacExt_ID ")
				.append(" INNER JOIN EXME_ProductoOrg po ")
				.append(" ON po.exme_productoorg_id = fnc_getproductorg(EXME_CtaPacDet.M_Product_ID,EXME_CtaPacDet.AD_Org_ID) ")
				.append(" WHERE EXME_CtaPacDet.IsActive = 'Y' ")
				.append(" AND EXME_CtaPacDet.EXME_CtaPacExt_ID = ? ");

		if (StringUtils.isNotEmpty(confType)) {
			sql.append(" AND fnc_IsProfessional(PO.EXME_ProductoOrg_ID, EXME_CtaPacDet.AD_Org_ID) = ?  ");
		}

		if (Constantes.CLAIM_MISSINGCHARGES == type) {
			sql.append(" AND 1= 1 ");
		} else if (Constantes.CLAIM_MISSINGPRICES == type) {
			sql.append(" AND EXME_CtaPacDet.calcularprecio='N' ");
		} else if (Constantes.CLAIM_MISSINGREVCODE == type) {
			sql.append(" AND po.exme_revenuecode_id is null ");
		}

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "EXME_CtaPacDet"));

		sql.append(" ORDER BY EXME_CtaPacDet.EXME_CtaPacDet_ID ASC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_CtaPacExt_ID);
			if (StringUtils.isNotEmpty(confType)) {
				pstmt.setString(
						2,
						confType.equals(MHL7MessageConf.CONFTYPE_InstitutionalClaim) ? Constantes.REG_NOT_ACTIVE
								: Constantes.REG_ACTIVE);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MCtaPacDet refValue = new MCtaPacDet(ctx, rs, trxName);
				list.add(refValue);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "getCargosByClaim: ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return list;
	}


	/**
	 * 
	 * @param ctx
	 * @param ctaPac
	 * @param trxName
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal generateProv(Properties ctx, MEXMECtaPac ctaPac,
			String trxName) throws Exception {

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		String caso = null;
		if (ctaPac.tienePaquete()) {// Si tiene paquete
			if (ctaPac.isPaqueteFacturado()) {// Si el paquete fue facturado
				caso = " case when ctaext.C_Invoice_id is not null then 0 else cpd.QtyPendiente end ";
			} else {// Si el paquete no fue facturado
				caso = " case when ctaext.C_Invoice_id is not null then cpd.qtypaquete else cpd.qtydelivered end ";
			}
		} else {// Si no tiene paquete
			caso = " case when ctaext.C_Invoice_id is not null then 0 else cpd.qtydelivered end ";
		}
		BigDecimal acumulado = new BigDecimal(0);
		sql.append(
				"SELECT cta.DOCUMENTNO AS ctapac,trx.VALUE AS ccosto, validc.C_VALIDCOMBINATION_ID as vc, pc.NAME AS pcategoria,cpd.C_CURRENCY_ID as moneda, ")
				.append("sum((")
				.append(caso)
				.append(") * cpd.PRICEACTUAL) AS credito, sum(0) AS debito ")
				.append("FROM EXME_CtaPacDet cpd ")
				.append("INNER JOIN EXME_CTAPAC cta ON cta.EXME_CTAPAC_ID = cpd.EXME_CTAPAC_ID ")
				.append("INNER JOIN EXME_CTAPACEXT ctaext ON ctaext.EXME_CTAPACEXT_ID = cpd.EXME_CTAPACEXT_ID ")
				.append("INNER JOIN EXME_PACIENTE P ON (P.EXME_Paciente_id = cta.EXME_paciente_id) ")
				.append("LEFT JOIN EXME_EstServAlm esa ON esa.M_Warehouse_id = cpd.M_warehouse_id ")
				.append("LEFT JOIN EXME_EstServ es ON es.EXME_EstServ_ID = esa.EXme_estserv_id ")
				.append("INNER JOIN ad_org trx ON (trx.AD_ORG_ID = es.AD_ORGTRX_ID) ")
				.append("INNER JOIN m_product pd ON (pd.m_product_id = cpd.m_product_id) ")
				.append("INNER JOIN m_product_category pc ON (pc.m_product_category_id = pd.m_product_category_id) ")
				.append("INNER JOIN m_product_category_acct PCA ON (PCA.m_product_category_id = pc.m_product_category_id) ")
				// mrojas : estaba como pca.P_ProvVta_Acct, pero de acuerdo al
				// estandar debiera ser EXME_Prov_Vta_Acct -->
				.append("INNER JOIN C_VALIDCOMBINATION validc ON ( pca.EXME_PROV_VTA_ACCT = validc.C_VALIDCOMBINATION_ID) ")
				// mrojas : estaba como pca.P_ProvVta_Acct, pero de acuerdo al
				// estandar debiera ser EXME_Prov_Vta_Acct <--
				.append("INNER JOIN C_ELEMENTVALUE cvalue ON( validc.ACCOUNT_ID = cvalue.C_ELEMENTVALUE_ID) ")
				.append("LEFT JOIN C_BPARTNER BP ON (BP.C_bpartner_id = P.C_bpartner_seg_id) ")
				.append("WHERE cpd.IsActive = 'Y' ")
				.append("AND cpd.Exme_ctapac_id = ? ")
				.append("AND ctaext.C_INVOICE_ID is null ")
				.append("AND cpd.ref_ctapacdet_id is null ")
				.append("AND cpd.QTYDELIVERED > 0 ")
				.append("AND cpd.SeDevolvio = 'N' ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "EXME_CtaPacDet", "cpd"));

		sql.append(" group by cta.DOCUMENTNO,pc.NAME, trx.VALUE, cpd.C_CURRENCY_ID, validc.C_VALIDCOMBINATION_ID");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, ctaPac.getEXME_CtaPac_ID());
			rs = pstmt.executeQuery();
			MEXMEPaciente paciente = new MEXMEPaciente(ctx,	ctaPac.getEXME_Paciente_ID(), null);
			MBPartner part = paciente.getPatientBPartner();
			MBPartner partner = null;
			if (part != null) {
				partner = new MBPartner(ctx, part.getC_BPartner_ID(), null);
			}
			String nombreCliente = null;
			if (partner != null) {
				nombreCliente = partner.getName();
			} else {
				nombreCliente = paciente.getNombre_Fam() + " "
						+ paciente.getApellido1_Fam() + " "
						+ paciente.getApellido2_Fam();
			}
			while (rs.next()) {
				if (rs.getBigDecimal("credito").compareTo(BigDecimal.ZERO) == 1) {
					MEXMETTProvision prov = new MEXMETTProvision(ctx, 0,
							trxName);
					prov.setDocumentNo(rs.getString("ctapac"));
					prov.setFecha(ctaPac.getDateOrdered());
					prov.setCentro_Costo(rs.getString("ccosto"));
					MAccount cuenta = new MAccount(ctx, rs.getInt("vc"), null);
					MElementValue value = new MElementValue(ctx,
							cuenta.getAccount_ID(), null);
					prov.setCta_Cont(value.getValue());
					prov.setCta_Cont_ID(cuenta.getC_ValidCombination_ID());
					if (cuenta.getC_ValidCombination_ID() <= 0) {
						throw new Exception(Msg.getMsg(ctx, "ErrInicializar"));
					}
					prov.setCategoria(rs.getString("pcategoria"));
					prov.setDebito(BigDecimal.ZERO);
					prov.setCredito(rs.getBigDecimal("credito"));
					acumulado = acumulado.add(rs.getBigDecimal("credito"));
					prov.setC_Currency_ID(rs.getInt("moneda") <= 0 ? Env
							.getContextAsInt(ctx, "$C_Currency_ID")
							: rs.getInt("moneda"));
					prov.setCliente(nombreCliente);
					prov.setAD_Session_ID(Env.getContextAsInt(ctx,
							"#AD_Session_ID"));
					if (!prov.save()) {
						throw new Exception(Msg.getMsg(ctx, "ErrInicializar"));
					}
				}
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getLineas ", e);
			throw new Exception(Msg.getMsg(ctx, "ErrInicializar"));
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return acumulado;
	}


//	public String getNumSerie() {
//		return numSerie;
//	}
//
//	public void setNumSerie(String numSerie) {
//		this.numSerie = numSerie;
//	}
//
//	public void setEsqDescLineCount(int esqDescLineCount) {
//		this.esqDescLineCount = esqDescLineCount;
//	}
//
//	public int getEsqDescLineCount() {
//		return esqDescLineCount;
//	}

	public boolean equals(Object obj) {
		boolean found = false;
		if (obj instanceof MCtaPacDet
				&& this.getEXME_CtaPacDet_ID() == ((MCtaPacDet) obj)
						.getEXME_CtaPacDet_ID()) {
			found = true;
		}
		return found;
	}

	/**
	 * Calculo de impuesto
	 */
	public BigDecimal getDiscountTaxAmt(final boolean recalcular) {
		if(recalcular){
			BigDecimal discountTaxAmt = Env.ZERO;
			try {
				if (getMTax() != null && getMTax().getC_Tax_ID() > 0) {
					discountTaxAmt = getMTax().calculateTax(
							(getOverallDiscountUnit()).multiply(getQtyDelivered())
							, false,REDONDEO6_Calculos);//
				} else {
					discountTaxAmt = Env.ZERO;
				}
			} catch (Exception e) {
				discountTaxAmt = Env.ZERO;
			}
			return discountTaxAmt;
		} else {
			return super.getDiscountTaxAmt();
		}
	}

//	/**
//	 * Obj Impuesto
//	 */
//	public void setMTax(MTax mTax) {
//		this.mTax = mTax;
//	}

	/**
	 * Obj Impuesto
	 * 
	 * @return MEXMETax
	 */
	public MTax getMTax() {
		if (mTax == null && getC_Tax_ID() > 0) {
			mTax = new MTax(getCtx(), getC_Tax_ID(), get_TrxName());
		}
		return mTax;
	}

	/**
	 * Calculo de impuesto
	 */
	public void setTaxAmt() {
		setTaxAmt(false);
	}

	/**
	 * Calculo de impuesto
	 */
	public BigDecimal setTaxAmt(final boolean taxIncluded) {
		return setTaxAmt(taxIncluded, getLineNetAmt());
	}
	
	/**
	 * Calculo de impuesto
	 */
	public BigDecimal setDiscTaxAmt() {
		return setTaxAmt(false, getOverallDiscount());
	}
	
	/**
	 * Calculo de impuesto
	 */
	public BigDecimal setTaxAmt(final boolean taxIncluded,
			final BigDecimal amount) {
		try {
			if (getMTax() != null && getMTax().getC_Tax_ID() > 0) {
				super.setTaxAmt(getMTax().calculateTax(amount, taxIncluded,
						REDONDEO6_Calculos));//
			} else {
				super.setTaxAmt(Env.ZERO);
			}
		} catch (Exception e) {
			super.setTaxAmt(Env.ZERO);
		}
		return super.getTaxAmt();
	}

	/**
	 * Asignar tasa de impuesto cuando aun no se tiene SOLO PARA PRODUCTOS
	 */
	public void asigTax() {

		// Que sea producto y que no sea CCCmD o que sea producto y CCCmD
		// siempre y cuando el precio sea positivo
		if (getM_Product_ID() > 0
				&& (getProduct().isProduct() || (!getProduct().isProduct() && getPriceActual()
						.compareTo(Env.ZERO) >= 0))) {

			// Si el cargo no tiene impuesto
			if (getMTax() == null || getMTax().getC_Tax_ID() <= 0) {

				// se obtiene la tasa de impuesto del producto
				mTax = MEXMETax.getImpuestoProducto(getCtx(),
						getM_Product_ID(), get_TrxName());

				// si se obtuvo una tasa de impuesto
				if (mTax != null && mTax.getC_Tax_ID() > 0) {
					// Se asigna al cargo
					setC_Tax_ID(mTax.getC_Tax_ID());
					// Se calcula el monto
					setTaxAmt();
				} else {
					slog.severe("C_Tax_ID = 0 Product:" + getM_Product_ID());
				}
			}
		} // si no es producto es un descuento,
	}

	/**
	 * Asignar tasa de impuesto cuando es venta al publico de un medicamento
	 */
	public void asigTaxDrugSalesPublic() {
		// se obtiene la tasa de impuesto del producto
		// mTax = MEXMETax.getExemptTax(getCtx(), get_TrxName());
		mTax = MEXMETax.getZeroTax(getCtx(), get_TrxName());// Los medicamentos
															// son gravado al
															// cero y no exentos
		if (mTax != null && mTax.getC_Tax_ID() > 0) {
			// Se asigna al cargo
			setC_Tax_ID(mTax.getC_Tax_ID());
			// Se calcula el monto
			setTaxAmt();
		} else {
			slog.severe("C_Tax_ID = 0 Product:" + getM_Product_ID());
		}
	}

	/**
	 * LineNetAmt
	 * Cantidad entregada por el precio de factura (todos los descuento)
	 */
	public void setLineNetAmt() {
		BigDecimal bDLineNetAmt = getQtyDelivered().multiply(getPriceActualInv());
		if(bDLineNetAmt.scale() > 2){
			bDLineNetAmt =  bDLineNetAmt.setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		
		super.setLineNetAmt(bDLineNetAmt);
	}
	
	/**
	 * LineNetAmt
	 * Cantidad entregada por el precio de factura (todos los descuento)
	 */
	public BigDecimal getLineNetAmt() {
		if(super.getPriceActualInv().compareTo(Env.ZERO)==0){
			setLineNetAmt();
		}
		return super.getLineNetAmt();
	}
	
	/**
	 * Precio con convenio
	 */
	public void setPriceActual(BigDecimal priceActual){
		if(super.getPriceActualInv().compareTo(Env.ZERO)==0){
			super.setPriceActualInv(priceActual);
		}
		super.setPriceActual(priceActual);
	}
	
	/**
	 * Devuelve el regristro donde EXME_CtaPacDet_ID conincide con el param
	 * 
	 * @param ctx
	 * @param EXME_CtaPacDet_ID
	 * @param EXME_CtaPac_ID
	 * @param trxName
	 * @return
	 * 
	 */
	public static List<MCtaPacDet> getCambiaExtPaq(Properties ctx,
			int EXME_PaqBase_Version_ID, int EXME_CtaPacExt_ID,
			int EXME_CtaPac_ID, int Ref_CtaPacDet_ID, int serie,
			int target_CtaPacExt_ID, String trxName) {

		List<MCtaPacDet> refValue = new ArrayList<MCtaPacDet>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT * FROM EXME_CtaPacDet  ")
				.append(" WHERE IsActive = 'Y' ")
				.append(" AND EXME_CtaPac_ID = ? AND EXME_CtaPacExt_ID = ? ")
				.append(" AND Ref_CtaPacDet_ID = ? ")
				.append(" AND EXME_PaqBase_Version_ID = ? ");

		sql = new StringBuilder(MEXMELookupInfo.addAccessLevelSQL(ctx,
				sql.toString(), "EXME_CtaPacDet"));
		sql.append(" ORDER BY EXME_CtaPacDet_ID ASC");
		slog.log(Level.FINEST, "SQL> " + sql + " ID > " + EXME_CtaPac_ID);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			pstmt.setInt(2, EXME_CtaPacExt_ID);
			pstmt.setInt(3, Ref_CtaPacDet_ID);
			pstmt.setInt(4, EXME_PaqBase_Version_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				refValue.add(new MCtaPacDet(ctx, rs, trxName));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getLineas ", e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return refValue;

	}

//	public boolean isFactEsp() {
//		return factEsp;
//	}
//
//	public void setFactEsp(boolean factEsp) {
//		this.factEsp = factEsp;
//	}
//
//	public int getM_ProductCategory_ID() {
//		return prodCatId;
//	}
//
//	public void setM_ProductCategory_ID(int productCategory_ID) {
//		prodCatId = productCategory_ID;
//	}

	public BigDecimal getImporteActualInv() {
		setImporteActualInv();
		return importeActualInv;
	}

	public void setImporteActualInv() {
		importeActualInv = getPriceActualInv().multiply(getQtyInvoiced())
				.setScale(REDONDEO2_Usuario, BigDecimal.ROUND_HALF_UP);
	}

//	public void setImporteActualInvRecalcular() {
//		importeActualInv = getPriceActualInv().multiply(getQtyInvoiced())
//				.setScale(REDONDEO2_Usuario, BigDecimal.ROUND_HALF_UP);
//	}

	public BigDecimal getTotal() {
		return getImporteActualInv().add(getTaxAmt()).setScale(
				REDONDEO2_Usuario, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 
	 * @param cantidad
	 */
	public void setQty(BigDecimal cantidad) {
		setQtyDelivered(cantidad);
		setQtyOrdered(cantidad);
		setQtyInvoiced(cantidad);
		setQtyPendiente(cantidad);
		setQtyPaquete(Env.ZERO);
		setQtyReserved(Env.ZERO);
		setLineNetAmt();
	}

	/**
	 * 
	 * @return
	 */
	public String getProductName() {
		productName = "";
		if (getProductSinTrx() != null) {
			productName = getProductSinTrx().getName();
		}
		return productName;
	}

//	public void setProductName(String productName) {
//		this.productName = productName;
//	}
	
	
	public String getMProductValue() {
		productValue = "";
		if (getProductSinTrx() != null){
			productValue = getProductSinTrx().getValue();
		}
		return productValue;
	}

	public MProduct getProductSinTrx() {
		if (productSinTrx == null || getM_Product_ID() <= 0)
			productSinTrx = new MProduct(getCtx(), getM_Product_ID(), null);
		return productSinTrx;
	}

//	public MUOM getUomInvoiceSinTrx() {
//		if (uomInvoiceSinTrx == null || getInvoice_UOM_ID() <= 0)
//			uomInvoiceSinTrx = new MUOM(getCtx(), getInvoice_UOM_ID(), null);
//		return uomInvoiceSinTrx;
//	}

	public String getUnidadMedida() {
		try {
			return MUOM.nombreUDM(getCtx(), getC_UOM_ID());
		} catch (Exception e) {
			log.log(Level.SEVERE, getUom().getName(), e);
		}
		return StringUtils.EMPTY;
	}
		
//	public BigDecimal getTotalLineaSinDesc() {
//		// Precio con descuento de convenio y de familia
//		return getPriceLimitInv().multiply(getQtyPendiente());
//	}

	public String getNameEstServ() {
		if (nameEstServ == null || getM_Warehouse_ID() <= 0) {
			MEXMEEstServ estacion = MEstServAlm.getEstServ(getCtx(),
					getM_Warehouse_ID(), null);
			if (estacion != null)
				nameEstServ = estacion.getName();
		}
		return nameEstServ;
	}
	
	public String getEstacion() {
		return getWarehouse().getName();
	}
	
	public String getConcepto(final boolean isFactEsp) {
		String concepto = getConcepto();
		if(isFactEsp){
			concepto = super.getProductValue();
		} 
		return concepto;
	}

	public String getConcepto() {
		String concepto  = StringUtils.EMPTY;
		if(getMProductValue()!=null){
			concepto = getMProductValue();
		} else {
			if(getCharge()!=null){
				concepto = getCharge().getValue();
			}
		}
		return concepto;
	}
	/** utilizar otra descripcion si el socio asi lo decide */
	public String getOtherDescrip(final boolean isFactDirect) {
		String descrip = getDescrip();// Del producto/c_charge
		if(isFactDirect && getProductDescription()!=null && !getProductDescription().isEmpty()){
			descrip = getProductDescription();
		} 
		return descrip;
	}
	/** utilizar otra descripcion si el socio asi lo decide */
	public String getDescrip(final boolean isFactEsp) {
		String descrip = getDescrip();
		if(isFactEsp){
			descrip = getProductDescription();
		} 
		return descrip;
	}
	
	public String getDescrip() {
		String descrip  = StringUtils.EMPTY;
		if(getProductName()!=null){
			descrip  = getProductName();
		}else if(getCharge()!=null){
			descrip  = getCharge().getName();
		}
		return descrip;
	}
	
	
	public MEXMEEsqDesLine getEsqDesLine(){
		if(mEsqDesLine == null && getEXME_EsqDesLine_ID()>0){
			mEsqDesLine = new MEXMEEsqDesLine(getCtx(), getEXME_EsqDesLine_ID(), get_TrxName()) ;
		}
		return mEsqDesLine;
	}
	
	/**
	 * Establecer los precios del parametro como precio del cargo,
	 * - El precio es el mismo para todas las columnas de precio
	 * - El impuesto queda en cero
	 * - Puede recalcular el descuento previamente establecido si asi se especifica sobre el precio del parametro
	 * - Calcula el total de la linea antes de impuesto
	 * @param price
	 * @param includeTax
	 * @param recalculateDiscount
	 */
	public void setPrices(final BigDecimal price, final boolean includeTax, final boolean recalculateDiscount) {
		// Precios
//		setPriceActual(price);
//		setPriceLimit(price);
//		setPriceList(price);
		// Precios de facturación
		setPrices(price); //setPricesInv();
		// Descuento sobre cambio de precios
		if(recalculateDiscount && getEXME_EsqDesLine_ID()>0){
			getEsqDesLine().calculateDiscount(price);
			setDiscountAmt(getEsqDesLine().getDiscount());
			setDiscount(getEsqDesLine().getList_Discount());
			
			setPriceActual(price.subtract(getDiscountAmt()));
			setPriceActualInv(getPriceActual());
		}
		// Total de la linea 
		setLineNetAmt();
		
		// Impuesto
		if (includeTax && getProduct().isProduct()) {
			setTaxAmt();
		} else if (getProduct().isProduct()){
			setTaxAmt(Env.ZERO);
		}
	}

	/**
	 * Obtenemos el producto asignada a la cuenta paciente
	 * 
	 * @return
	 */
	public MProduct getProducto() {
		if (product == null || getM_Product_ID() <= 0)
			product = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		return product;
	}

//	/**
//	 * Identificador del Producto
//	 * 
//	 * @return
//	 */
//	public int getProductID() {
//		return getM_Product_ID();
//	}

	/**
	 * Cargo de la linea
	 * 
	 * @return
	 */

	public MCharge getCharge() {
		if (charge == null || getC_Charge_ID() <= 0)
			charge = new MCharge(getCtx(), getC_Charge_ID(), get_TrxName());
		return charge;
	}

//	/**
//	 * Obtenemos la cama asignada a la cuenta paciente
//	 * 
//	 * @return
//	 */
//	public MUOM getUomInvoice() {
//		if (uomInvoice == null || getInvoice_UOM_ID() <= 0)
//			uomInvoice = new MUOM(getCtx(), getInvoice_UOM_ID(), get_TrxName());
//		return uomInvoice;
//	}

//	/**
//	 * Paquete
//	 * 
//	 * @return
//	 */
//	public MEXMEPaqBaseVersion getPaqBase_Version() {
//		if (paqBaseVersion == null || getEXME_PaqBase_Version_ID() <= 0)
//			paqBaseVersion = new MEXMEPaqBaseVersion(getCtx(),
//					getEXME_PaqBase_Version_ID(), get_TrxName());
//		return paqBaseVersion;
//	}

//	/**
//	 * Precio con iva
//	 * 
//	 * @return
//	 */
//	public BigDecimal getPrecioCIva() {
//
//		return getLineNetAmt().add(getTaxAmt()).setScale(2,
//				BigDecimal.ROUND_HALF_UP);
//
//	}

//	/**
//	 * Si el cargo en la extension tiene excepcion
//	 * 
//	 * @return
//	 */
//	public int getExcepcion() {
//		return excepcion;
//	}

//	public void setExcepcion(int excepcion) {
//		this.excepcion = excepcion;
//	}

	@Override
	public void setC_UOM_ID(int C_UOM_ID) {
		super.setC_UOM_ID(C_UOM_ID);
		super.setInvoice_UOM_ID(C_UOM_ID);
	}

	/**
	 * 
	 * @param ctx
	 * @param sql
	 * @param params
	 * @param trxName
	 * @return
	 */
	public static List<MCtaPacDet> get(final Properties ctx, final String sql,
			final List<?> params, final String trxName) {

		final List<MCtaPacDet> retorno = new ArrayList<MCtaPacDet>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				final MCtaPacDet mtd = new MCtaPacDet(ctx, rs, null);
				retorno.add(mtd);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getCitasMedico", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retorno;
	}

	/**
	 * 
	 * @param ctx
	 * @param id
	 * @param trxName
	 * @return
	 */
	public static HashMap<Integer, List<MCtaPacDet>> getDetalleCreado(
			Properties ctx, int id, String trxName) {

		List<Integer> params = new ArrayList<Integer>();
		params.add(id);

		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT cpd.*, cp.EXME_CtaPac_ID, cp.documentno as cuenta, pac.nombre_pac, pr.name, cpd.ad_pinstance_id ")
				.append(" FROM EXME_CtaPacDet cpd  ")
				.append(" INNER JOIN EXME_Ctapac    cp ON cpd.exme_ctapac_id = cp.exme_ctapac_id ")
				.append(" INNER JOIN EXME_Paciente pac ON cp.exme_paciente_id = pac.exme_paciente_id ")
				.append(" INNER JOIN M_Product      pr ON cpd.m_product_id = pr.m_product_id  ")
				.append(" WHERE cpd.AD_PInstance_ID = ? ");

		HashMap<Integer, List<MCtaPacDet>> mapa = new HashMap<Integer, List<MCtaPacDet>>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				MCtaPacDet mtd = new MCtaPacDet(ctx, rs, null);
				mtd.setNombreProd(rs.getString("name"));

				BeanView bean = new BeanView();
				bean.setCadena1(rs.getString("cuenta"));
				bean.setCadena2(rs.getString("nombre_pac"));
				bean.setInteger1(rs.getInt("EXME_CtaPac_ID"));
				mtd.setBean4String(bean);

				if (mapa.containsKey(rs.getInt("EXME_CtaPac_ID"))) {
					List<MCtaPacDet> lst = mapa
							.get(rs.getInt("EXME_CtaPac_ID"));
					lst.add(mtd);
				} else {
					List<MCtaPacDet> lst = new ArrayList<MCtaPacDet>();
					lst.add(mtd);
					mapa.put(rs.getInt("EXME_CtaPac_ID"), lst);
				}
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "getCitasMedico", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return mapa;
	}

	public String getNombreProd() {
		return nombreProd;
	}

	private void setNombreProd(String string) {
		nombreProd = string;
	}

	public MPlanMedLine getPlanMedLine() {
		if (planMedLine == null && getEXME_PlanMedLine_ID() > 0) {
			planMedLine = new MPlanMedLine(getCtx(), getEXME_PlanMedLine_ID(),
					get_TrxName());
		}
		return planMedLine;
	}

	public void setPlanMedLine(MPlanMedLine planMedLine) {
		this.planMedLine = planMedLine;
	}

	public BeanView getBean4String() {
		return bean4String;
	}

	public void setBean4String(BeanView bean4String) {
		this.bean4String = bean4String;
	}

	/**************************************************************************
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true
	 */

	protected boolean beforeSave(boolean newRecord) {
		// Debe existir siempre un revCode - SOLO para USA .- Lana
		if (newRecord) {
			if (getAD_User_ID() <= 0) {
				int user = Env.getAD_User_ID(getCtx());
				if (user < 1) {
					user = 100;// TODO Twry Validar
				}
				setAD_User_ID(user);
			}
			if (MCountry.isUSA(getCtx()) && getEXME_RevenueCode_ID() <= 0) {
				revCodeChargeMaster();
			}
		}
		// Se agrego validacion para DateDelivered y Qty Delivered al ser
		// requeridos en Billing
		if (getDateDelivered() == null) {
			setDateDelivered(Env.getCurrentDate());
		}
		if (getQtyDelivered().compareTo(Env.ZERO) == 0
				&& getEXME_ActPacienteInd_ID() > 0) {
			setQtyDelivered(new MEXMEActPacienteInd(getCtx(),getEXME_ActPacienteInd_ID(), get_TrxName()).getQtyOrdered());
			setLineNetAmt();
		}

		if (StringUtils.isBlank(getTipoLinea())) {
			setTipoLinea(TIPOLINEA_Charge);
		}

//		// Si existe POS a nivel cuenta y no se ha definido uno para el cargo,
//		// se asinga el de Cta
		// Se agregan las correctas validaciones .- Lama
		MEXMEPOS.setPOSId(this, MEXMECtaPac.Table_Name);

		return true;
	}

	/**
	 * Revenue Code - Charge master
	 * 
	 * @return
	 */
	private boolean revCodeChargeMaster() {
		if (getM_Product_ID() > 0 && getEXME_RevenueCode_ID() <= 0) {

			X_EXME_ProductoOrg prod = MEXMEProductoOrg.getProductOrg(getCtx()
					, getM_Product_ID()
					, Env.getAD_Org_ID(getCtx())
					, 1);//X_EXME_ProductoOrg prod = GetRevCode.calcularRevCode(getCtx(), getM_Product_ID());
			
			if (prod != null && prod.getEXME_RevenueCode_ID() > 0) {
				setEXME_RevenueCode_ID(prod.getEXME_RevenueCode_ID());
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtenemos el almacen
	 * 
	 * @return
	 */
	public MWarehouse getWarehouse() {
		if (mWarehouse == null || mWarehouse.getM_Warehouse_ID() == 0) {
			mWarehouse = new MWarehouse(getCtx(), getM_Warehouse_ID(),
					get_TrxName());
		}
		return mWarehouse;

	}

	/**
	 * getPriceList().multiply(getQtyDelivered());
	 * 
	 * @return
	 */
	public BigDecimal getTotalLine() {
		if (getPriceList().equals(BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		} else {
			return getPriceList().multiply(getQtyDelivered());
		}
	}

	/**
	 * @return Es professional si la Organizacion factura solo Professional
	 *         De otro modo se revisa su columna IsProfessional en EXME_ProductoOrg
	 */
	public boolean isProfessionalClaim() {
		// Es professional si la Organizacion factura solo Professional
		// Lama: Corrección de NullPointerException
		if (MOrgInfo.SUPPORTBILLING_Professional.equals(MOrgInfo.get(getCtx(), getAD_Org_ID()).getSupportBilling())) {
			return Boolean.TRUE;
		} else {
			// De otro modo se revisa su columna IsProfessional en
			// EXME_ProductoOrg
			final MEXMEProductoOrg porg = MEXMEProductoOrg.getProductoOrg(getCtx(), getM_Product_ID(), getAD_Client_ID(), getAD_Org_ID(), null);
			return porg != null && porg.isProfessional();
		}
	}

//	/**
//	 * # count
//	 * 
//	 * @param ctx
//	 * @param sql
//	 * @param params
//	 * @return
//	 */
//	public static BigDecimal get(Properties ctx, String sql, List<?> params) {
//
//		BigDecimal retorno = Env.ZERO;
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), null);
//			DB.setParameters(pstmt, params);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				retorno = rs.getBigDecimal(1);
//			}
//
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, "getCitasMedico", e);
//		} finally {
//			DB.close(rs, pstmt);
//		}
//		return retorno;
//	}

	/**
	 * Prices
	 */

	public void prices() {
		// Precio por primera vez
		if (!getTipoLinea().equals(X_EXME_CtaPacDet.TIPOLINEA_BasePack)) {
			precios = GetPrice.getPriceCargo(getCtx(), this);
			precios.preciosActual(this);
		} // las lineas de paquetes tienen precio cero
	}

	public MPrecios getPrecios() {
		return precios;
	}

	public void setPrecios(MPrecios precios) {
		this.precios = precios;
	}

	public boolean isChangePrices(){
		boolean isChangePrices = false;
		if(getProduct()!=null && getM_Product_ID()>0){
			if(getProduct().getProdOrg()!=null && getProduct().getProdOrg().getEXME_ProductoOrg_ID()>0){
				isChangePrices = getProduct().getProdOrg().isCambiaPrecio();// organizacion
			} else {
				isChangePrices = getProduct().isCambiaPrecio();// system
			}
		}
		return isChangePrices;
	}
	
	/**
	 * Cost
	 */
	public void setProductCost() {
		GetCost getCost = null;
		// Si no existe costo se crea
		if (getCosto() == null || getCosto().compareTo(Env.ZERO) == 0) {
			BigDecimal costo = getCosto();
			// buscamos el costo
			if (costo == null || costo.compareTo(Env.ZERO) <= 0) {
				getCost = new GetCost(getCtx(), getM_Product_ID());
				costo = getCost.costo(true);
			}
			// lo creamos
			if (costo == null || costo.compareTo(Env.ZERO) <= 0) {
				// crear
				// MCost.createCost(getProduct());
				// setCosto(Env.ONE);
				log.log(Level.FINEST, "No hay costo creado para el producto ");
			} else {
				setCosto(costo);
			}

		}
		// Card #1374 - Almacenar el costo en los cargos
		if(get_Value(COLUMNNAME_CostAverage) == null 
			|| get_Value(COLUMNNAME_CostStandard) == null 
			|| get_Value(COLUMNNAME_PriceLastPO) == null) {
			
			if(getCost == null) {
				getCost = new GetCost(getCtx(), getM_Product_ID());
			}
			getCost.setCosts();
			if(get_Value(COLUMNNAME_CostStandard) == null) {
				setCostStandard(getCost.getCostStandard());
			}
			// servicios solo guarda estandar
			if (!getProduct().isService()) {
				if (get_Value(COLUMNNAME_CostAverage) == null) {
					setCostAverage(getCost.getCostAverage());
				}
				if (get_Value(COLUMNNAME_PriceLastPO) == null) {
					setPriceLastPO(getCost.getPriceLastPO());
				}
			}
		}
	}// cost

	/**
	 * Qty's
	 * 
	 * @param actualTmp
	 * @param process
	 * @param WarehouseID
	 */
	public void completeCharge(final int warehouseID) {
		setQtyEntered(getQtyOrdered());

		if (getQtyDelivered() == null
				|| getQtyDelivered().compareTo(Env.ZERO) == 0) {
			setQtyDelivered(getQtyOrdered());
		}
		if (getDateDelivered() == null) {
			setDateDelivered(Env.getCurrentDate());
		}

		if (getM_Warehouse_ID() <= 0) {
			setM_Warehouse_ID(warehouseID);
		}

		if (getM_Warehouse_Sol_ID() <= 0) {
			setM_Warehouse_Sol_ID(Env.getM_Warehouse_ID(getCtx()));
		}

		if (getAD_OrgTrx_ID() <= 0) {
			setAD_OrgTrx_ID(Env.getAD_OrgTrx_ID(getCtx()));
		}

		if(getTipoArea()==null || getTipoArea().isEmpty()){
			setTipoArea(Env.getTipoArea(getCtx()));
		}
		
		if(getEXME_Area_ID()<=0){
			final MEXMEEstServ serv = new MEXMEEstServ(getCtx(), Env.getEXME_EstServ_ID(getCtx()), null);
			setEXME_Area_ID(serv.getEXME_Area_ID());
		}
		//Las devoluciones van a llevar el usuario del login
		if (getAD_User_ID() <= 0||getQtyDelivered().compareTo(Env.ZERO)<0){
			setAD_User_ID(Env.getAD_User_ID(getCtx()));
		}
		
		setCgoProcesado(false);// true ??
		setQtyPendiente(getQtyDelivered());
		setQtyPaquete(Env.ZERO);
		setLineNetAmt();
		setTaxAmt();

		if (getPriceActual() != null
				&& Env.ZERO.compareTo(getPriceActual()) < 0) {
			setEstaLineaTienePrecio(true);
		}
	}

	/**
	 * Act paciente ind
	 * 
	 * @param actualTmp
	 * @param headerServ
	 * @return
	 */
	public boolean completeActPac(Timestamp actualTmp, final MEXMEActPacienteIndH headerServ) {
		boolean success = false;
		if (getActPacienteInd() == null || !getProduct().isProductActPac() || !headerServ.save(get_TrxName())) {
			return success;
		}

		getActPacienteInd().set_TrxName(get_TrxName());
		getActPacienteInd().setEXME_ActPacienteIndH_ID(
				headerServ.getEXME_ActPacienteIndH_ID());
		getActPacienteInd().setC_Currency_ID(headerServ.getC_Currency_ID());

		// Estacion que aplica y que surte
		getActPacienteInd().setAD_OrgTrx_ID(getAD_OrgTrx_ID());
		getActPacienteInd().setDateDelivered(getDateDelivered());
		getActPacienteInd().setDateOrdered(getDateOrdered());
		getActPacienteInd().setDatePromised(getDatePromised());

		getActPacienteInd().setQtyEntered(getQtyOrdered());
		getActPacienteInd().setQtyDelivered(getQtyEntered());
		getActPacienteInd().setQtyInvoiced(Env.ZERO);
		getActPacienteInd().setQtyReserved(Env.ZERO);

		// Asignacion del Rev. code
		if (getProduct() != null && getProduct().getProdOrg() != null) {
			getActPacienteInd().setEXME_RevenueCode_ID(
					getProduct().getProdOrg().getEXME_RevenueCode_ID());
		}

		// Cargo directo
		if (getActPacienteInd().getEstatus() == null) {
			getActPacienteInd().setEstatus(
					MEXMEActPacienteInd.ESTATUS_CompletedService);
		}

		getActPacienteInd().setDescription(getDescription());
		getActPacienteInd().setIsDescription(false);
		getActPacienteInd().setM_InOutLine_ID(getM_InOutLine_ID());

		getActPacienteInd().setSurtir(true);
		getActPacienteInd().setCgoProcesado(true);
		getActPacienteInd().setLineNetAmt(getLineGrossAmt().setScale(2,
						BigDecimal.ROUND_HALF_UP));

		success = getActPacienteInd().save();
		if(success) { //Lama: cargos 2014 ???? copiado de EnterCharges
			setEXME_ActPacienteInd_ID(getActPacienteInd().getEXME_ActPacienteInd_ID());
		}
		return success;
	}

	/**
	 * Creacion de linea de descuento, deducible o coaseguro
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param EXME_CtaPacExt_ID
	 *            : Identificador de la extension
	 * @param C_Charge_ID
	 *            : Identificador del cargo (descuento, deducible, coaseguro)
	 * @param monto
	 *            : Monto de la linea
	 * @return MTTCtaPacDet: Cargo creado
	 * @return trxName: Transaccion
	 * @throws Exception
	 */
	public void completeLine(final int EXME_CtaPacExt_ID,
			/*final int C_Charge_ID,*/ final int M_Product_ID,
			final BigDecimal monto, BigDecimal taxAmt) throws Exception {

		BigDecimal desc = BigDecimal.ZERO;
		MTax taxExento = null;
		desc = monto.setScale(2, BigDecimal.ROUND_HALF_UP);
		//
		/*if (C_Charge_ID > 0) {
			setC_Charge_ID(C_Charge_ID);
			setDescription(getCharge().getName());
			taxExento = getCharge().getMTax();
			if (taxExento == null) {
				taxExento = MEXMETax.getExemptTax(getCtx(), get_TrxName());
			}
		}*/

		if (M_Product_ID > 0) {
			setM_Product_ID(M_Product_ID);
			taxExento = getProduct().getTax();// MEXMETax.getExemptTaxID(ctx,
												// trxName);//TTPR Tasa de
												// impuesto con vigencia
		}
		defaultValues();
		MEXMEEstServ m_estServ = new MEXMEEstServ(getCtx(),
				Env.getContextAsInt(getCtx(), "#EXME_EstServ_ID"), null);
		setEXME_Area_ID(m_estServ.getEXME_Area_ID());
		if (EXME_CtaPacExt_ID > 0) {
			setEXME_CtaPacExt_ID(EXME_CtaPacExt_ID);
		}
		if (getExtension() != null && getExtension().getEXME_CtaPac_ID() > 0) {
			setEXME_CtaPac_ID(getExtension().getEXME_CtaPac_ID());
		}
		// setLineNetAmt(desc);
		// setPriceList(desc);
		// setPriceActual(desc);
		// setPriceLimit(desc);
		// setPriceListInv(desc);
		// setPriceActualInv(desc);
		// setPriceLimitInv(desc);
		setPrices(desc, false, false);

		// taxAmt puede ser cero
		if (taxExento != null && taxAmt == null) {
			taxAmt = taxExento.calculateTax(desc, false, 2);
		}

		setC_Tax_ID(taxExento.getC_Tax_ID());
		setTaxAmt(taxAmt);
	}

	/**
	 * Valores por defecto
	 */

	public void defaultValues() {
		setCosto(Env.ZERO);
		setDateOrdered(DB.convert(getCtx(), new Date()));
		setAD_User_ID(Env.getContextAsInt(getCtx(), "#AD_User_ID"));
		setAD_Session_ID(Env.getContextAsInt(getCtx(), "#AD_Session_ID"));
		// setChargeAmt(desc);//Se comento por que se requiere que en esta
		// columna se guarde el precio original
		setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
		setIsDescription(false);
		setIsFacturado(false);
		setLine(0);
		setUsarFactor(false);
		setQtyOrdered(Env.ONE);
		setQtyDelivered(Env.ONE);
		setQtyInvoiced(Env.ONE);
		setQtyPendiente(Env.ONE);
		setQtyReserved(Env.ZERO);
		setQtyPaquete(Env.ZERO);
		setTipoArea(Env.getTipoArea(getCtx()) == null
				|| Env.getTipoArea(getCtx()).isEmpty() ? X_EXME_CtaPacDet.TIPOAREA_Other
				: Env.getTipoArea(getCtx()));
		setSecuencia(0);
		setLineNetAmt();
	}

//	/**
//	 * Tasa de impuesto
//	 * 
//	 * @throws Exception
//	 */
//	public void taxLine() {
//
//		if (getC_Tax_ID() <= 0) {
//
//			MTax taxExento = null;
//			if (getC_Charge_ID() > 0) {
//				taxExento = getCharge().getTax();
//				if (taxExento == null) {
//					taxExento = MEXMETax.getExemptTax(getCtx(), get_TrxName());
//				}
//			} else if (getM_Product_ID() > 0) {
//				taxExento = getProduct().getTax();// MEXMETax.getExemptTaxID(ctx,
//													// trxName);//TTPR Tasa de
//													// impuesto con vigencia
//			}
//
//			if (taxExento != null) {
//				setC_Tax_ID(taxExento.getC_Tax_ID());
//			}
//		}
//	}

//	/**
//	 * Area
//	 */
//	public void areaLine() {
//		if (getEXME_Area_ID() <= 0) {
//			MEXMEEstServ m_estServ = new MEXMEEstServ(getCtx(),
//					Env.getContextAsInt(getCtx(), "#EXME_EstServ_ID"), null);
//			if (m_estServ != null) {
//				setEXME_Area_ID(m_estServ.getEXME_Area_ID());
//			}
//		}
//	}

	/**
	 * Id del impuesto del producto que es un cargo en la cuenta paciente
	 */
	public void setPricesTaxPatient() {
		// Si es un producto y tiene una tasa de impuesto
		if (getProducto() != null && getProducto().getTax() != null) {
			// Obtenemos el impuesto configurado
			setC_Tax_ID(getProducto().getTax().getC_Tax_ID());
			// El precio es el mismo
			setPrices(getPriceList(), false, false);
		}
	}

//	/**
//	 * true: Cuando el precio es cero y ese precio es correcto o hay precio > 0
//	 * 
//	 * @return
//	 */
//	public boolean isEstaLineaTienePrecio() {
//		return isCalcularPrecio();
//	}

	/**
	 * true: Cuando el precio es cero y ese precio es correcto
	 * 
	 * @param crearCargo
	 */
	public void setEstaLineaTienePrecio(boolean PrecioCero) {
		setCalcularPrecio(PrecioCero);
	}

	/**
	 * Permite almacenar datos adicionales al modelo
	 */
	public List<Integer> getLstModificadores() {
		return lstModificadores;
	}

	public void setLstModificadores(List<Integer> lstModificadores) {
		this.lstModificadores = lstModificadores;
	}

	public void setLstModificadores(boolean reload,
			List<MEXMEModifiers> lstModif) {
		if (reload) {
			lstModificadores.clear();
		}
		for (int i = 0; i < lstModif.size(); i++) {
			if (lstModif.get(i).getEXME_Modifiers_ID() > 0) {
				lstModificadores.add(lstModif.get(i).getEXME_Modifiers_ID());
			}
		}
	}

	/**
	 * updateRevCode
	 * 
	 * @param ctx
	 * @param revCode
	 * @param trxName
	 */
	public static int updateRevCode(Properties ctx, int revCode,
			int M_Product_ID, String trxName) {
		// cambiar a todos los cargos sin rev. code
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT cpd.*          ")
				.append(" FROM EXME_CtaPacDet cpd  ")
				.append(" WHERE cpd.IsActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_CtaPacDet.Table_Name, "cpd"))
				.append(" AND cpd.EXME_RevenueCode_ID IS NULL ")
				.append(" AND cpd.M_Product_ID = ? ");

		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, M_Product_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MCtaPacDet mtd = new MCtaPacDet(ctx, rs, trxName);
				mtd.setEXME_RevenueCode_ID(revCode);
				if (mtd.save(trxName)) {
					count++;
				}
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, "MCtaPacDet.updateRevCode", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return count;
	}

	public BeanView getBeanID() {
		return beanID;
	}

	public void setBeanID(BeanView beanID) {
		this.beanID = beanID;
	}

//	public MEXMEActPacienteIndCgo getLogCgo() {
//		return logCgo;
//	}

//	public void setLogCgo(MEXMEActPacienteIndCgo logCgo) {
//		this.logCgo = logCgo;
//	}

	/**
	 * true: Si el tipo de linea es equivalente a un calculo de coaseguro,
	 * deducible, etc.
	 * 
	 * @return
	 */
	public boolean isCCCmD() {
		return MCtaPacDet.TIPOLINEA_Coaseguro.equals(getTipoLinea())
				|| MCtaPacDet.TIPOLINEA_Deducible.equals(getTipoLinea())
				|| MCtaPacDet.TIPOLINEA_CoaseguroMedico.equals(getTipoLinea())
				|| MCtaPacDet.TIPOLINEA_Copago.equals(getTipoLinea());
	}

	/**
	 * true: Si el tipo de linea es equivalente a un calculo de coaseguro,
	 * deducible, etc. pero la parte positiva
	 * 
	 * @return
	 */
	public boolean isCCCmDPositivo() {
		return getM_Product_ID() > 0 && getProduct() != null
				&& getProduct().isCCCmD();
	}

	/**
	 * 
	 * @param ctx
	 * @param patientID
	 * @param ctaPacID
	 * @param tableOrigID
	 * @param typeComponent
	 * @param onlyCPT_HCPCS
	 *            true: solo CPTs y HPCSs; false:todo tipo de cargo menos cpts
	 * @return
	 */
	public static List<ServicesVO> getCargosCtaPac(Properties ctx,
			int patientID, int ctaPacID, int tableOrigID, String typeComponent,
			boolean onlyCPT_HCPCS) {
		List<ServicesVO> lst = new ArrayList<ServicesVO>();

		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append(" SELECT ");

		if (DB.isOracle()) {
			sql.append("rownum as Sequence");
		} else if (DB.isPostgreSQL()) {
			sql.append("row_number() OVER (ORDER BY DateDelivered, Created) AS sequence");
		}

		sql.append(
				",  list.* FROM (SELECT   CPD.EXME_CtaPacDet_ID, I.EXME_ActPacienteInd_ID, CPD.M_Product_ID, CPD.Created, ")
				.append(" coalesce(EI.Value,mp.value) as value, MP.Name, MP.Description, EI.Value HCPCS, coalesce(EI.Name,MP.Description) HCPCSDesc, ERC.Value RevCode, CPD.DateDelivered, CPD.QtyDelivered, ")
				.append(" CPD.LineNetAmt charges, CC.Record_ID ctaPacMed_ID, CC.EXME_ClaimCodes_ID, ")
				// .append(" fnc_getStrClaimCodes(?,?,?,?,CPD.EXME_CtaPacDet_ID) modifiers, ")
				// .append(" fnc_getStrClaimCodes(?,?,?,?,CPD.EXME_CtaPacDet_ID) procdiag, ")
				.append(" CPD.EXME_POS_ID,EI.HCPCS_Level, CPD.Condition  ")
				.append(" FROM       EXME_CtaPacDet CPD ")
				.append(" LEFT JOIN EXME_ActPacienteInd I ON I.EXME_ActPacienteInd_ID = CPD.EXME_ActPacienteInd_ID ")
				.append(" INNER JOIN M_Product MP ON MP.M_Product_ID = CPD.M_Product_ID ")
				.append(" INNER  JOIN EXME_ProductoOrg PO ON PO.EXME_ProductoOrg_ID = fnc_getproductorg(CPD.M_Product_ID, CPD.AD_Org_ID) ")
				.append(" LEFT  JOIN EXME_RevenueCode ERC ON ERC.EXME_RevenueCode_ID = CPD.EXME_RevenueCode_ID ");
		if (onlyCPT_HCPCS) {
			sql.append(" INNER JOIN EXME_Intervencion EI ON EI.EXME_Intervencion_ID = MP.EXME_Intervencion_ID ");
		} else {
			sql.append(" LEFT JOIN EXME_Intervencion EI ON EI.EXME_Intervencion_ID = MP.EXME_Intervencion_ID ");
		}
		sql.append(
				" LEFT  JOIN EXME_ClaimCodes CC ON CC.IsActive = 'Y' AND CC.AD_TableOrig_ID = ? ")
				.append(" AND CC.RecordOrig_ID = CPD.EXME_CtaPacDet_ID AND CC.AD_Table_id = ? ")
				.append(" WHERE CPD.EXME_CtaPac_ID = ? ");
		if (!onlyCPT_HCPCS) {
			sql.append(" AND mp.exme_intervencion_id is null ");
		}
		sql.append(" AND CPD.QTYDELIVERED >0 ");

		if (typeComponent != null) {
			// Si pide Professional se regresan todos los marcados como
			// Isprofessional o todos sin importar la marca si es Solo
			// Professional
			sql.append(" AND fnc_IsProfessional(PO.EXME_ProductoOrg_ID,CPD.AD_Org_ID) = ?  ");

		}
		// Se eliminan los registros que fueron cancelados siempre y cuando
		// solo existan como cargos
		sql.append("AND  (")
				.append("(COALESCE(CPD.EXME_ActPacienteInd_ID,0) = 0  ")
				.append("AND  NOT EXISTS ")
				.append(" (SELECT REF_CTAPACDET_ID FROM EXME_CTAPACDET D WHERE D.EXME_CTAPAC_ID = CPD.EXME_CTAPAC_ID AND D.REF_CTAPACDET_ID = CPD.EXME_CTAPACDET_ID)) ")
				.append("OR  COALESCE(CPD.EXME_ActPacienteInd_ID,0) >1)  ")
				.append(" ORDER BY CPD.DateDelivered, CPD.Created) ");
		// Si es postgresql agregar el AS
		if (DB.isPostgreSQL()) {
			sql.append("AS ");
		}
		sql.append("list");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int set = 1;
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(set++, tableOrigID);
			pstmt.setInt(set++, MEXMECtaPacMed.Table_ID);

			pstmt.setInt(set++, ctaPacID);

			if (typeComponent != null) {
				pstmt.setString(
						set++,
						typeComponent.equals(Constantes.SubType_PROF) ? Constantes.REG_ACTIVE
								: Constantes.REG_NOT_ACTIVE);
			}

			rs = pstmt.executeQuery();
			ServicesVO obj = null;
			while (rs.next()) {
				obj = new ServicesVO();
				obj.setSequence(rs.getInt("Sequence"));
				obj.setCtaPacDetID(rs.getInt("EXME_CtaPacDet_ID"));
				obj.setActPacienteIndID(rs.getInt("EXME_ActPacienteInd_ID"));
				obj.setProductID(rs.getInt("M_Product_ID"));
				obj.setValue(rs.getString("Value"));
				obj.setName(rs.getString("Name"));
				obj.setDescription(rs.getString("HCPCSDesc"));
				obj.setHcpcs(rs.getString("HCPCS"));
				obj.setRevCode(rs.getString("RevCode"));
				obj.setQtyDelivered(rs.getInt("QtyDelivered"));
				obj.setDateDelivered(rs.getTimestamp("DateDelivered"));
				obj.setCharges(rs.getDouble("Charges"));
				obj.setClaimCodesID(rs.getInt("EXME_ClaimCodes_ID"));
				obj.setHcpcsLevel(rs.getString("HCPCS_Level"));
				obj.setCtaPacMed_ID(rs.getInt("CtaPacMed_ID"));
				if (Constantes.SubType_PROF.equalsIgnoreCase(typeComponent)) {
					obj.setPosID(rs.getInt("EXME_POS_ID"));
				}
				obj.addAllMod(MEXMEClaimCodes.get(ctx, ctaPacID,
						MCtaPacDet.Table_ID, rs.getInt("EXME_CtaPacDet_ID"),
						MEXMEModifiers.Table_ID, null));

				obj.addAllProcDiag(MEXMEClaimCodes.get(ctx, ctaPacID,
						MCtaPacDet.Table_ID, rs.getInt("EXME_CtaPacDet_ID"),
						MActPacienteDiag.Table_ID, null));
				obj.setPosID(rs.getInt("EXME_POS_ID"));
				obj.setCondition(rs.getString("Condition"));
				lst.add(obj);

			}
		} catch (SQLException ex) {
			slog.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		} finally {
			DB.close(rs, pstmt);
		}

		return lst;
	}

	/**
	 * Recupera el total de cargos por tipo de claim a generar, solo de
	 * Extension 0
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param ConfType
	 *            Claim Configuration Type
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getTotChargesByClaim(Properties ctx, int ctaPacID,
			String confType, String trxName) {
		// confType= "T";
		List<MCtaPacDet> lista = getCargosByTypeExtNo(ctx, ctaPacID, 0,
				confType, trxName);
		BigDecimal retVal = BigDecimal.ZERO;
		for (int i = 0; i < lista.size(); i++) {
			retVal = retVal.add(lista.get(i).getTotalLine());
		}
		return retVal;
	}
	
	/**
	 * Recupera el total de cargos por tipo de claim a generar por una cuenta en particular
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param ConfType
	 *            Claim Configuration Type
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getTotChargesByClaimExt(Properties ctx, int ctaPacExtID,
			String confType, String trxName) {
		// confType= "T";
		MEXMECtaPacExt ext = new MEXMECtaPacExt(ctx, ctaPacExtID, trxName);
		List<MCtaPacDet> lista = getCargosByTypeExtNo(ctx, ext.getEXME_CtaPac_ID(), ext.getExtensionNo(),
				confType, trxName);
		BigDecimal retVal = BigDecimal.ZERO;
		for (int i = 0; i < lista.size(); i++) {
			retVal = retVal.add(lista.get(i).getTotalLine());
		}
		return retVal;
	}

	/**
	 * Obtiene los detalles de la cuenta activos y que no tengan place of
	 * service
	 * 
	 * @param ctx
	 * @param sql
	 * @param params
	 * @return Regresa el listado de los objetos MEXMECtaPac
	 */
	public static List<MCtaPacDet> getCuentaPacDetNotPOS(Properties ctx,
			int EXME_CtaPac_ID, String trxName) {
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		final List<MCtaPacDet> cuentaPacDetLista = new ArrayList<MCtaPacDet>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			sql.append(" SELECT * ");
			sql.append(" FROM EXME_CTAPACDET ");
			sql.append(" WHERE EXME_CTAPACDET.EXME_CTAPAC_ID = ? AND EXME_CTAPACDET.IsActive = 'Y' AND EXME_CTAPACDET.EXME_POS_ID IS NULL ");
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name));

			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, EXME_CtaPac_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cuentaPacDetLista.add(new MCtaPacDet(ctx, rs, null));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "MCtaPacDet.getCuentaPacDetNotPOS", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return cuentaPacDetLista;
	}

	/**
	 * After Save
	 * CONSIDERAR que este metodo se ejecuta del lado del servidor y por lo tanto 
	 * no contiene todos los datos del contexto
	 * @param newRecord
	 * @param success
	 * @return success
	 */
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		// Copy the place of service from the encounter in case it doesn't have
		// it
		if (success) {
			// Solo se aplica si el lenguage es
			if (MCountry.isUSA(getCtx()) && getExtension().getExtensionNo() == 0
				&& !MEXMECtaPacExt.CHARGESTATUS_LateCharge.equals(getExtension().getChargeStatus())) {
				if (MEXMECtaPacExt.getExtBilledCtas(getCtx(), " AND  ext.ConfType =  ? ", null, getExtension().getEXME_CtaPac_ID(),
					isProfessionalClaim() ? MEXMECtaPacExt.CONFTYPE_ProfessionalClaim : MEXMECtaPacExt.CONFTYPE_InstitutionalClaim).size() > 0) {
					getExtension().setChargeStatus(MEXMECtaPacExt.CHARGESTATUS_LateCharge);
					success = getExtension().save(get_TrxName());
				}
			}
			if (newRecord && !packed && !TIPOLINEA_Exempt.equals(getTipoLinea()) && !isSeDevolvio()) {
				packed = true;
				new PackageBalance(getCtx(), getEXME_CtaPacDet_ID(), get_TrxName()).doIt();
			}
		}	

		return success;
	}
	
	
//
//	public boolean isPacked() {
//		return packed;
//	}
//
//	public void setPacked(boolean packed) {
//		this.packed = packed;
//	}

	/**
	 * charged
	 * 
	 * @param ctx
	 *            : contexto
	 * @param EXME_CtaPac_ID
	 *            : Cuenta paciente
	 * @param EXME_PlanMedLine_ID
	 *            : Plan medico
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return true
	 */
	public static List<MCtaPacDet> charged(final Properties ctx,
			final int EXME_CtaPac_ID, final int EXME_PlanMedLine_ID,
			final int M_Product_ID, final boolean actPac, final String trxName) {
		//
		final List<MCtaPacDet> retVal = new ArrayList<MCtaPacDet>();
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
				.append(" SELECT *                                            ")
				.append(" FROM  EXME_CtaPacDet                                 ")
				.append(" WHERE EXME_CtaPacDet.IsActive = 'Y'                 ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name))
				.append(" AND   EXME_CtaPacDet.EXME_CtaPac_ID = ?             ")
				.append(actPac?"":" AND   EXME_CtaPacDet.EXME_ActPacienteInd_ID IS NULL ")
				// Define si se aplico antes de cargarlo
				.append(EXME_PlanMedLine_ID > 0 ? " AND EXME_CtaPacDet.EXME_PlanMedLine_ID = ? "
						: " AND EXME_CtaPacDet.EXME_PlanMedLine_ID IS NULL AND EXME_CtaPacDet.M_Product_ID = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_CtaPac_ID);
			pstmt.setInt(2, EXME_PlanMedLine_ID > 0 ? EXME_PlanMedLine_ID
					: M_Product_ID);
			rs = pstmt.executeQuery();
			// correspondencia de uno a uno
			if (rs.next()) {
				retVal.add(new MCtaPacDet(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "MCtaPacDet.charged: ", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * charged: Buscara si el producto seleccionado ya esta cargado mas no
	 * aplicado
	 * 
	 * @param ctx
	 *            : contexto
	 * @param EXME_CtaPac_ID
	 *            : Cuenta paciente
	 * @param EXME_PlanMedLine_ID
	 *            : Plan medico
	 * @param trxName
	 *            : Nombre de transaccion
	 * @return true
	 */
	public static MCtaPacDet chargedForPlanMed(final Properties ctx,
			final int EXME_CtaPac_ID, final int EXME_PlanMed_ID,
			final String trxName) {
		//
		MCtaPacDet retVal = null;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
				.append(" SELECT *                                    ")
				.append(" FROM  EXME_CtaPacDet cpd                    ")
				.append(" INNER JOIN EXME_PlanMedLine pml             ")
				.append("       ON cpd.EXME_PlanMedLine_ID = pml.EXME_PlanMedLine_ID ")
				.append(" WHERE cpd.IsActive            = 'Y'         ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name,
						"cpd"))
				.append(" AND   cpd.EXME_CtaPac_ID      = ?           ")// De la
																		// cuenta
				.append(" AND   cpd.EXME_PlanMedLine_ID IS NOT NULL   ")
				// La dosis que no este aplicada
				.append(" AND   pml.EXME_PlanMed_ID     = ?           ")// De la
																		// prescripcion
				// .append(" AND   pml.ApliedDate IS NULL                ")//
				// Que no tenga fecha de aplicacion//POR ALGUNA EXTRAÑA RAZON SI
				// TRAE VALOR PERO MUY VIEJITO
				.append(" AND   pml.Estatus    IN ( ? )               ")// Que
																		// el
																		// estatus
																		// sea
																		// de
																		// prescripcion
				.append(" ORDER BY pml.ProgDate ASC                   ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);

			pstmt.setInt(1, EXME_CtaPac_ID);
			pstmt.setInt(2, EXME_PlanMed_ID);
			pstmt.setString(3, X_EXME_PlanMedLine.ESTATUS_Prescribed);

			rs = pstmt.executeQuery();
			// correspondencia de uno a uno
			if (rs.next()) {
				retVal = new MCtaPacDet(ctx, rs, trxName);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, "MCtaPacDet.charged: ", e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retVal;
	}

	/**
	 * Busqueda de lineas para la cuenta paciente
	 * 
	 * @param ctx
	 *            Contexto
	 * @param exmeCtaPacId
	 *            Identificador de la cuenta paciente
	 * @param trxName
	 *            Nombre de la transaccion
	 * @return Listado de objetos <MCtaPacDet>
	 */
	public static List<MCtaPacDet> get(final Properties ctx,
			final int exmeCtaPacId, final String trxName) {

		final List<MCtaPacDet> resultados = new ArrayList<MCtaPacDet>();
		if (ctx == null) {
			return resultados;
		}

		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM EXME_CtaPacDet ")
				.append(" WHERE EXME_CtaPacDet.IsActive = 'Y' ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						X_EXME_CtaPacDet.Table_Name))
				.append(" AND EXME_CtaPacDet.EXME_CtaPac_ID  = ? ")
				.append(" ORDER BY EXME_CtaPacDet.DATEORDERED DESC ");

		PreparedStatement pstmt = null;
		ResultSet rSet = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, exmeCtaPacId);
			rSet = pstmt.executeQuery();

			while (rSet.next()) {
				final MCtaPacDet bean = new MCtaPacDet(ctx, rSet, trxName);
				resultados.add(bean);
			}
		} catch (SQLException sqle) {
			slog.log(Level.SEVERE, "MCtaPacDet.charged: ", sqle);
		} finally {
			DB.close(rSet, pstmt);
		}

		return resultados;
	}

	/**
	 * Crear objeto de MPrecios
	 * 
	 * @param force
	 *            si el obj precio no esta vacio force = true para crear y
	 *            asignar
	 * @param precio
	 *            Precio del producto
	 */
	public void createMPrecios(final boolean force, final BigDecimal precio) {
		if (precios == null || force) {
			precios = new MPrecios();
			precios.setPriceList(precio);
			precios.setPriceStd(precio);
			precios.setPriceLimit(precio);
			precios.setM_Product_ID(getM_Product_ID());
			precios.setCtx(getCtx());
		}
	}
	
	public void createMPrecios(final BigDecimal precio) {
		if (precios == null) {
			precios = new MPrecios();
			precios.setM_Product_ID(getM_Product_ID());
			precios.setCtx(getCtx());
		} 
		
		precios.setValues(this);
	}

	/**
	 * Calculo de precio de venta para facturacion directa
	 * 
	 * @param socio
	 *            valor de la tabla C_BPartner_ID
	 * @param process
	 *            los valores posibles PVCE ó PVH
	 */
	public void preciosFactDirec(final int socio, final String process) {
		// Solo facturacion directa
		precios = GetPrice
				.getPriceVta(getCtx()
						, getM_Product_ID()
						, socio
						, getC_UOM_ID() <= 0 ? getProduct().getC_UOM_ID(): getC_UOM_ID()
						, new Timestamp(System.currentTimeMillis())
						, getEXME_CtaPac_ID() == 0 ? Env.getTipoArea(getCtx()) : getCtaPac().getTipoPaciente().getTipoArea());
		
		if (precios != null) {
			precios.preciosActual(this);
		}
	}

	
	public void setPricesInv(){
		setPriceActualInv(getPriceActual());
		setPriceListInv(getPriceList());
		setPriceLimitInv(getPriceLimit());
		setLineNetAmt();
	}
	
	/**
	 * Precio de venta (al facturar en FDirecta)
	 */
	public void setPrices(final boolean searchPrice) {
		if (precios != null) {
			setPriceActual(precios.getPriceStd());
			setPriceList(precios.getPriceList());
			setPriceLimit(precios.getPriceLimit());
			if(searchPrice){
				setPricesInv();
			}
			setLineNetAmt();
		}
	}

	/**
	 * Set precio de venta cuando el precio fue modificado por el usuario
	 * 
	 * @param precio
	 *            Monto que será el precio del cargo
	 */
	public void setPrices(final BigDecimal precio) {
		//
		if (precio != null) {
			setPriceActual(precio);
			setPriceList(precio);
			setPriceLimit(precio);
			setPricesInv();
			setLineNetAmt();
		}
	}

	/**
	 * Busqueda de precio por linea
	 * 
	 * @param product
	 *            <MProduct> Objeto de Producto
	 * @param socio
	 *            valor de la tabla C_BPartner_ID
	 * @param process
	 *            los valores posibles PVCE ó PVH
	 */
	public boolean searchPrices(final MProduct product, final int socio,
			final String process) {

		if (product == null || product.getM_Product_ID() <= 0){
			return false;
			
		} else if(X_EXME_CtaPacDet.TIPOLINEA_Charge.equals(getTipoLinea())) {
			//
			final BigDecimal qty = getQtyDelivered() == null || getQtyDelivered().compareTo(Env.ZERO) == 0 
					? getQtyOrdered()
					: getQtyDelivered();
			//
			if (product.isProduct()) {
				// 
				if (Constantes.PVCE.equals(process)) {
					// Solo facturacion directa
					preciosFactDirec(socio, process);
					setLineNetAmt(qty.multiply(getPriceActual()));

					// Si eres medicamento de PVCE y tiene configurado que no
					// genere impuesto
					if (X_M_Product.PRODUCTCLASS_Drug.equals(product.getProductClass())
							&& !MEXMEMejoras.isCalcImpFact(getCtx())) {
						asigTaxDrugSalesPublic();
					} else {
						asigTax();
					}

				} else {
					// Solo facturacion por extensiones
					setPrecios(GetPrice.getPriceCargo(getCtx(), this));
					if (getPrecios() != null) {
						setPriceActual(getPrecios().getPriceStd());
						setPriceList(getPrecios().getPriceList());
						setPricesInv();
					}

					setLineNetAmt(qty.multiply(getPriceActual()));
					asigTax();
				}

			} else {
				setLineNetAmt(qty.multiply(getPriceActual()));
				asigTax();
			}

			return true;
		}
		return false;
	}

	/**
	 * setValuesCCCMD
	 * 
	 * @param line
	 *            cargo
	 * @param price
	 *            precio
	 */
	public void setValuesCCCMD(final MCtaPacDet line, final BigDecimal price) {

		// Al objeto de precios se pasa el monto del calculo
		createMPrecios(true, price);
		// Monto del calculo
		setPriceActual(line.getPriceActual());
		setPriceList(line.getPriceList());
		setPriceLimit(line.getPriceLimit());
		setPricesInv();
		setLineNetAmt(getQtyOrdered().multiply(getPriceActual()));
		setTaxAmt(line.getTaxAmt());
	}

//	public int getInvoiceId() {
//		return invoiceId;
//	}
//
//	public void setInvoiceId(int invoiceId) {
//		this.invoiceId = invoiceId;
//	}

//	/**
//	 * Cargos de la cuenta paciente
//	 * 
//	 * @param ctx
//	 * @param ctaPacsADesaparecer
//	 * @param trxName
//	 * @return
//	 */
//	public static List<MCtaPacDet> getCargosPorPaciente(Properties ctx,
//			String ctaPacsADesaparecer, String trxName) {
//		final StringBuilder where = new StringBuilder()
//				.append(" EXME_CtaPacDet.exme_ctapac_id IN ( ")
//				.append(ctaPacsADesaparecer).append(") ");
//
//		final List<MCtaPacDet> lista = new Query(ctx,
//				X_EXME_CtaPacDet.Table_Name, where.toString(), trxName)//
//				.setOnlyActiveRecords(true)//
//				.addAccessLevelSQL(true)//
//				.list();
//
//		return lista;
//	}

	/**
	 * Permite mover los cargos de la extension cero a otra extension
	 * 
	 * @param ctx
	 * @param EXME_CtaPac_ID
	 * @param ConfType
	 *            Claim Configuration Type
	 * @param trxName
	 * @return
	 */
	public static boolean moveToExtension(Properties ctx,
			MEXMECtaPacExt ctaExt, String confType, String trxName) {

		// Ubicar cargos de extension 0
		BigDecimal totalLines = BigDecimal.ZERO;
		for (MCtaPacDet det : MCtaPacDet.getCargosByTypeExtNo(ctx,
				ctaExt.getEXME_CtaPac_ID(), 0, confType, trxName)) {
			det.setEXME_CtaPacExt_ID(ctaExt.getEXME_CtaPacExt_ID());
			if (!det.save(trxName)) {
				slog.severe("Fallo al mover de Extension ctapacdet_id = "
						+ det.getEXME_CtaPacDet_ID());
				return false;
			}
			totalLines = totalLines.add(det.getLineNetAmt());
		}

		ctaExt.setTotalLines(ctaExt.getTotalLines().add(totalLines));
		ctaExt.setGrandTotal(ctaExt.getTotalLines().add(totalLines));
		return ctaExt.save(trxName);
	}

	/**
	 * Lista los diferentes tipos de claims que tiene una cuenta a partir de sus
	 * cargos
	 * 
	 * @param ctx
	 * @param where
	 *            clause
	 * @param trxName
	 * @param params
	 * @return
	 */
	public static List<ValueNamePair> getClaimTypeByCharges(Properties ctx,
			String where, String trxName, Object... params) {
		final List<ValueNamePair> retValue = new ArrayList<ValueNamePair>();
		final StringBuilder sql = new StringBuilder();

		sql.append("SELECT CASE ")
				.append(" WHEN fnc_isprofessional(fnc_getproductorg(CPD.M_PRODUCT_ID, CPD.AD_ORG_ID), CPD.AD_ORG_ID) = 'Y'")
				.append(" THEN 'P' ELSE 'T' END AS CONFTYPE, SUM(LINENETAMT) TOTAL ")
				.append(" FROM	EXME_CTAPACDET CPD ")
				.append(" INNER JOIN EXME_CTAPACEXT CPE ON CPE.EXME_CTAPACEXT_ID = CPD.exme_ctapacext_id ")
				.append(" WHERE	CPD.ISACTIVE = 'Y' ")
				.append(" AND		CPE.ISACTIVE = 'Y' ");

		if (where != null) {
			sql.append(where);
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name,
				"cpd"));
		sql.append(" group by fnc_isprofessional(fnc_getproductorg(CPD.M_PRODUCT_ID, CPD.AD_ORG_ID),CPD.AD_ORG_ID) ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retValue.add(new ValueNamePair(String.valueOf(rs
						.getDouble("TOTAL")), rs
						.getString(MEXMECtaPacExt.COLUMNNAME_ConfType)));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE,
					"MCtaPacDet.getClaimTypeByCharges: " + sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		return retValue;
	}

	/**
	 * Cargo relacionado al CCCmD positivo
	 * 
	 * @param ctx
	 * @param exme_CtaPacDet_ID
	 * @param trxName
	 * @return
	 */
	public MCtaPacDet contraparte(Properties ctx, int exme_CtaPacDet_ID,
			String trxName) {
		final StringBuilder where = new StringBuilder().append(
				" EXME_CtaPacDet.Ref_CtaPacDet_ID = ")
				.append(exme_CtaPacDet_ID);

		final MCtaPacDet contraparte = new Query(ctx,
				X_EXME_CtaPacDet.Table_Name, where.toString(), trxName)//
				.setOnlyActiveRecords(true)//
				.addAccessLevelSQL(true)//
				.first();
		return contraparte;
	}

	/**
	 * Obtener el precio del primer cargo que se hizo para esa cuenta paciente
	 * para ese producto en esa unidad de medida
	 * 
	 * @param ctx
	 *            : Contexto
	 * @param ctaPacID
	 *            : Cuenta paciente
	 * @param productID
	 *            : Producto
	 * @param uomID
	 *            : Unidad de medida
	 * @return BigDecimal precio de lista
	 */
	public static BigDecimal setPriceListFirstCharge(final Properties ctx,
			final int ctaPacID, final int productID, final int uomID,
			final String trxName) {
		if (productID == 0 || uomID == 0 || ctaPacID == 0) {
			return BigDecimal.ZERO;
		}

		//
		final StringBuilder sql = new StringBuilder(" SELECT COALESCE(PriceList,0)  ")
		.append(" FROM EXME_CtaPacDet ")
		.append(" WHERE IsActive='Y'  ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
				X_EXME_CtaPacDet.Table_Name))
				.append(" AND EXME_CtaPac_ID = ?   ")
				.append(" AND M_Product_ID   = ?   ")
				.append(" AND C_UOM_ID       = ?   ")
				.append(" AND SeDevolvio     = 'N' ")
				.append(" ORDER BY EXME_CtaPacDet_ID DESC ");
		BigDecimal precio = DB.getSQLValueBD(null, sql.toString(), ctaPacID,productID,uomID);
		return precio == null ? BigDecimal.ZERO : precio;
	}
	
	/**
	 * Obtener el ID de producto para el codigo HPCPS, funcion creada para el procesamiento del mensaje 835 (Claim Payment)
	 * 
	 * @author Adrian Bautista
	 */
	public static int getProductIDbyHCPCS(Properties ctx, int EXME_CtaPacExt_ID, String confType, String code, BigDecimal totalCargo) {
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT L1.M_PRODUCT_ID, SUM(L1.LINENETAMT) AS TOTAL ")
		   .append(" FROM	( ")
		   .append("          SELECT cpd.M_PRODUCT_ID, COALESCE(GP.JCODE, I.VALUE) AS CODE, CPD.AD_ORG_ID, CPD.LINENETAMT ")
		   .append("           		, fnc_getproductorg(cpd.m_product_id, cpd.ad_org_id) AS EXME_PRODUCTOORG_ID ")
		   .append("          FROM	EXME_CTAPACDET CPD INNER JOIN M_PRODUCT P ON P.M_PRODUCT_ID = CPD.M_PRODUCT_ID ")
		   .append("                                   LEFT JOIN EXME_INTERVENCION I ON I.EXME_INTERVENCION_ID = P.EXME_INTERVENCION_ID ")
		   .append("                                   LEFT JOIN EXME_GENPRODUCT GP ON GP.EXME_GENPRODUCT_ID = P.EXME_GENPRODUCT_ID ")
		   .append("          WHERE	CPD.ISACTIVE = 'Y' ")
		   .append("          AND	CPD.EXME_CTAPACEXT_ID = ? ")
		   .append(" ) L1 ")
		   .append(" WHERE	fnc_isprofessional(L1.EXME_PRODUCTOORG_ID, L1.AD_ORG_ID) = ? ")
		   .append(" AND		L1.CODE = ? ")
		   .append(" GROUP BY L1.M_PRODUCT_ID ");
		
		params.add(EXME_CtaPacExt_ID);
		params.add(MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType) ? Constantes.REG_ACTIVE : Constantes.REG_NOT_ACTIVE);
		params.add(code);
		
		if (totalCargo.compareTo(BigDecimal.ZERO) > 0) {
			sql.append(" HAVING SUM(L1.LINENETAMT) = ? ");
			params.add(totalCargo);
		}
		return DB.getSQLValue(null, sql.toString(), params);
	}
	
	/**
	 * Obtener el ID de producto para el rev code, funcion creada para el procesamiento del mensaje 835 (Claim Payment)
	 * 
	 * @author Adrian Bautista
	 */
	public static int getProductIDbyRevCode(Properties ctx, int EXME_CtaPacExt_ID, String confType, String code, BigDecimal totalCargo) {
//		int idProduct = 0;
		StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT L1.M_PRODUCT_ID, SUM(L1.LINENETAMT) AS TOTAL ")
		   .append(" FROM	( ")
		   .append("          SELECT CPD.M_PRODUCT_ID, CPD.AD_ORG_ID, CPD.LINENETAMT ")
		   .append("           		, fnc_getproductorg(cpd.m_product_id, cpd.ad_org_id) AS EXME_PRODUCTOORG_ID ")
		   .append("          FROM	EXME_CTAPACDET CPD ")
		   .append("          WHERE	CPD.ISACTIVE = 'Y' ")
		   .append("          AND	CPD.EXME_CTAPACEXT_ID = ? ")
		   .append(" ) L1 INNER JOIN EXME_PRODUCTOORG PO ON PO.EXME_PRODUCTOORG_ID = L1.EXME_PRODUCTOORG_ID ")
		   .append("      INNER JOIN EXME_REVENUECODE RC ON RC.EXME_REVENUECODE_ID = PO.EXME_REVENUECODE_ID ")
		   .append(" WHERE	fnc_isprofessional(L1.EXME_PRODUCTOORG_ID, L1.AD_ORG_ID) = ? ")
		   .append(" AND	LPAD(RC.VALUE, 4, '0') = ? ")
		   .append(" GROUP BY L1.M_PRODUCT_ID ");
		
		
		params.add(EXME_CtaPacExt_ID);
		params.add(MEXMECtaPacExt.CONFTYPE_ProfessionalClaim.equals(confType) ? Constantes.REG_ACTIVE : Constantes.REG_NOT_ACTIVE);
		params.add(code);
		if (totalCargo.compareTo(BigDecimal.ZERO) > 0) {
			sql.append(" HAVING SUM(L1.LINENETAMT) = ? ");
			params.add(totalCargo);
		}
		return DB.getSQLValue(null, sql.toString(), params);
	}
	
	public BigDecimal getPrecioAnterior() {
		return precioCambio;
	}

	public void setPrecioCambio(final BigDecimal precioCambio) {
		this.precioCambio = precioCambio;
	}
	/** Total bruto */
	public BigDecimal getLineGrossAmt(){
		return getPriceActual().multiply(getQtyDelivered());
	}
	/** Overall Discount Per Unit */
	public BigDecimal getOverallDiscountUnit(){
		return getPriceActual().subtract(getPriceActualInv());
	}
	/** Overall Discount Per Unit */
	public BigDecimal getOverallDiscount(){
		return (getPriceActual().subtract(getPriceActualInv())).multiply(getQtyDelivered());
	}
	/** Overall Discount Per Unit */
	public BigDecimal getOverallDiscountAdj(){
		return getDiscountFam();
	}
	/** Descuento por convenio */
	public BigDecimal getConvDiscountUnit(){
		return getPriceList().subtract(getPriceActual());
	}
	/** Quita el calculo del descuento global */
	public void cleanOverallDisc(){
		setPriceActualInv(getPriceActual());
		setDiscountFam( Env.ZERO);// Porcion descuento global
		setDiscountTaxAmt(Env.ZERO);
		setLineNetAmt();
		setTaxAmt();
	}
	/** Quita el calculo del descuento global */
	public void cleanOverallDiscPatient(){
		
		BigDecimal impuestoOrig = getTaxAmt().add(getDiscountTaxAmt(true));//137.930000
		if(getProduct()!=null && !getProduct().isProduct()){
			if(getRef_CtaPacDet_ID()>0){
				final MCtaPacDet contraparte = new MCtaPacDet(getCtx(),getRef_CtaPacDet_ID(),null); 
				if(contraparte.getTaxAmt().compareTo(Env.ZERO)<=0){//Nagativo
					impuestoOrig = contraparte.getTaxAmt().abs();
				} else {
					impuestoOrig = contraparte.getTaxAmt().negate();
				}
			}
		}
		
		setPriceActualInv(getPriceActual());
		setDiscountFam( Env.ZERO);// Porcion descuento global
		setLineNetAmt();
		setTaxAmt(impuestoOrig);
		setDiscountTaxAmt(Env.ZERO);
	}
	
	/** Impuesto */
	public void setMTaxAmt(final MTax tax) {
		if (getC_Tax_ID() < 1 && tax != null) {
			setC_Tax_ID(tax.getC_Tax_ID());
			setTaxAmt( tax.getAmount());
		}
	}
	
	/**
	 * Obtiene el &uacuteltimo precio de un producto para una cuenta
	 * 
	 * @param ctx
	 *            Contexto de la app
	 * @param ctaPacId
	 *            Cuenta Paciente
	 * @param productId
	 *            Producto Id
	 * @param trxName
	 *            Trx Name
	 * @return El precio o nulo si nunca lo ha tenido
	 */
	public static BigDecimal getLastPrice(Properties ctx, int ctaPacId, int productId, String trxName) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  pricelist ");
		sql.append("FROM ");
		sql.append("  exme_ctapacdet ");
		sql.append("WHERE ");
		sql.append("  m_product_id = ? AND ");
		sql.append("  exme_ctapac_id = ? ");
		return DB.getSQLValueBD(trxName, sql.toString(), productId,ctaPacId);
	}
	
	public LabelValueBean getExtLVB() {
		return extLVB;
	}

	public void setExtLVB(LabelValueBean extLVB) {
		this.extLVB = extLVB;
	}
	/** Monto del descuento */
	public BigDecimal getDiscountAmt(){
		return super.getDiscountAmt();
	}
	/** Porcentaje de descuento (Solo cuando el convenio es en porcentaje) */
	public BigDecimal getDiscount(){
		return super.getDiscount();
	}
	/** Descuento adicional al convenio */
	public BigDecimal getDiscountFam(){
		return super.getDiscountFam();
	}
	
	/**
	 * Suma de totales de linea, descuento e impuestos
	 */
	public BigDecimal[] getSumTotals(final boolean afterDiscount, final String process) {
	
		// Cantidad de la linea
		final int cantidad = getQtyDelivered().intValue();
		// Total de la linea (Precio por cantidad)
		final BigDecimal totalLinea = getPrecios() == null ? Env.ZERO
				: getPrecios().getPriceStd()
						.multiply(new BigDecimal(cantidad));

		// Total de descuento (Descuento x cantidad)
		final BigDecimal totalDesc = afterDiscount ? getPriceActual()
				.subtract(getPriceActualInv())
				.multiply(new BigDecimal(cantidad)) : Env.ZERO;

		// Total de impuesto contanplando el descuento
		// Que no sea la linea de descuento
		BigDecimal totalTax = Env.ZERO;
		if (getCharge() == null || getCharge().getC_Charge_ID() < 1) {

			// Siempre que sea producto
			if (getM_Product_ID() > 0 && getProduct() != null
					&& getProduct().isProduct()) {
				MTax mtax = null;
				// Si es medicamento es tasa cero
				if (X_M_Product.PRODUCTCLASS_Drug.equals(getProduct()
						.getProductClass()) && Constantes.PVCE.equals(process)) {
					mtax = MEXMETax.getZeroTax(getCtx(), null);
				} else {
					mtax = getProduct().getTax();
				}

				if (mtax != null) {
					setC_Tax_ID(mtax.getC_Tax_ID());
					if (!afterDiscount) {
						setLineNetAmt(totalLinea);
					}
					setTaxAmt();
					totalTax = getTaxAmt();
				}
			} else {
				// CCCmD
				totalTax = getTaxAmt();
			}
		} else {
			// Es descuento
			totalTax = getTaxAmt();
		}
		// 1034.48 - 165.516800 - 0
		return new BigDecimal[] { totalLinea, totalTax, totalDesc };
	}
	
	/**
	 * Actualiza todos los cargos de la cuenta que sean EX, No devoluciones y
	 * activos a CG
	 * 
	 * @param ctx
	 * @param ctaPacId
	 * @param trxName
	 */
	public static void updateChargesEX(Properties ctx, int ctaPacId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append("  exme_ctapacdet ");
		sql.append("SET ");
		sql.append("  tipolinea = ? ");
		sql.append("WHERE ");
		sql.append("  exme_ctapac_id = ? AND ");
		sql.append("  sedevolvio = ? AND ");
		sql.append("  isactive = ? AND ");
		sql.append("  tipolinea = ? ");

		try {
			List<Object> params = new ArrayList<Object>();

			params.add(TIPOLINEA_Charge);
			params.add(ctaPacId);
			params.add("N");
			params.add("Y");
			params.add(TIPOLINEA_Exempt);

			DB.executeUpdate(sql.toString(), params.toArray(new Object[] {}), trxName);

		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void setCosto(BigDecimal costo){
		if(costo==null){
			costo = Env.ZERO;
		}
		super.setCosto(costo);
	}
	
	/**
	 * Obtiene las estaciones de servicio de los cargos de la cuenta paciente
	 * que sean de tipo Cargo/Exento
	 * 
	 * @param ctx Contexto
	 * @param ctapacId Cuenta Paciente
	 * @param trxName Transaccion
	 * @return
	 */
	public static List<MEXMEEstServ> getChargesServiceEst(Properties ctx, int ctapacId, String trxName) {
		final StringBuilder whereClause = new StringBuilder();
		final List<Object> parameters = new ArrayList<Object>();

		whereClause.append(" EXME_CtaPacDet.EXME_CtaPac_ID=? ");
		parameters.add(ctapacId);
		// charge type
		whereClause.append(" AND EXME_CtaPacDet.TIPOLINEA IN (?,?) ");
		parameters.add(X_EXME_CtaPacDet.TIPOLINEA_Charge);
		parameters.add(X_EXME_CtaPacDet.TIPOLINEA_Exempt);

		final StringBuilder joins = new StringBuilder();
		joins.append(" INNER JOIN EXME_EstServAlm ON EXME_EstServAlm.EXME_EstServ_ID=EXME_EstServ.EXME_EstServ_ID ");
		joins.append(" INNER JOIN EXME_CtaPacDet ON EXME_CtaPacDet.M_Warehouse_ID=EXME_EstServAlm.M_Warehouse_ID ");

		return new Query(ctx, MEXMEEstServ.Table_Name, whereClause.toString(), null)//
			.setJoins(joins)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(parameters)//
			.list();
	}

	/**
	 * Obtiene las unidades de medida utilizadas en los cargos de la cuenta paciente
	 * que sean de tipo Cargo/Exento
	 * 
	 * @param ctx Contexto
	 * @param ctapacId Cuenta Paciente (obligatorio)
	 * @param available true: que no esten devueltos o facturados
	 * @param trxName
	 * @return
	 */
	public static List<MUOM> getChargeUOMs(Properties ctx, int ctapacId, int productId, boolean available, String trxName) {
		final StringBuilder whereClause = new StringBuilder();
		final List<Object> parameters = new ArrayList<Object>();

		whereClause.append(" EXME_CtaPacDet.EXME_CtaPac_ID=? ");
		parameters.add(ctapacId);
		if (productId > 0) {
			whereClause.append(" AND EXME_CtaPacDet.M_Product_ID=? ");
			parameters.add(productId);
		}
		// warehouse (FIXME?)
		whereClause.append(" AND EXME_CtaPacDet.M_Warehouse_ID=? ");
		parameters.add(Env.getM_Warehouse_ID(ctx));
		// charge type
		whereClause.append(" AND EXME_CtaPacDet.TIPOLINEA IN (?,?) ");
		parameters.add(X_EXME_CtaPacDet.TIPOLINEA_Charge);
		parameters.add(X_EXME_CtaPacDet.TIPOLINEA_Exempt);

		if (available) {
			whereClause.append(" AND (EXME_CtaPacDet.IsFacturado='N' AND EXME_CtaPacDet.SeDevolvio='N') ");
		}

		final StringBuilder joins = new StringBuilder();
		joins.append(" INNER JOIN EXME_CtaPacDet ON EXME_CtaPacDet.C_UOM_ID=C_UOM.C_UOM_ID ");

		return new Query(ctx, MUOM.Table_Name, whereClause.toString(), null)//
			.setJoins(joins)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(parameters)//
			.list();
	}

	/**
	 * Obtiene los lotes utilizados en los cargos de la cuenta paciente
	 * que sean de tipo Cargo/Exento
	 * que no esten devueltos o facturados
	 * 
	 * @param ctx Contexto
	 * @param ctapacId Cuenta Paciente (obligatorio)
	 * @param productId Producto (obligatorio)
	 * @param trxName Transaccion
	 * @return
	 */
	public static List<MAttributeSetInstance> getChargeLots(Properties ctx, int ctapacId, int productId, int uomId, String trxName) {
		final StringBuilder whereClause = new StringBuilder();
		final List<Object> parameters = new ArrayList<Object>();

		whereClause.append(" EXME_CtaPacDet.EXME_CtaPac_ID=? ");
		parameters.add(ctapacId);
		whereClause.append(" AND EXME_CtaPacDet.M_Product_ID=? ");
		parameters.add(productId);
		whereClause.append(" AND EXME_CtaPacDet.C_UOM_ID=? ");
		parameters.add(uomId);
		// warehouse (FIXME?)
		whereClause.append(" AND EXME_CtaPacDet.M_Warehouse_ID=? ");
		parameters.add(Env.getM_Warehouse_ID(ctx));
		// charge type
		whereClause.append(" AND EXME_CtaPacDet.TIPOLINEA IN (?,?) ");
		parameters.add(X_EXME_CtaPacDet.TIPOLINEA_Charge);
		parameters.add(X_EXME_CtaPacDet.TIPOLINEA_Exempt);

		whereClause.append(" AND (EXME_CtaPacDet.IsFacturado='N' AND EXME_CtaPacDet.SeDevolvio='N') ");

		final StringBuilder joins = new StringBuilder();
		joins.append(" INNER JOIN EXME_CtaPacDet ON EXME_CtaPacDet.M_AttributeSetInstance_ID=M_AttributeSetInstance.M_AttributeSetInstance_ID ");

		return new Query(ctx, MAttributeSetInstance.Table_Name, whereClause.toString(), null)//
			.setJoins(joins)//
			.setOnlyActiveRecords(true)//
			.addAccessLevelSQL(true)//
			.setParameters(parameters)//
			.list();
	}

	/**
	 * Llena la informacion relativa al almacen de auditoria
	 * @param warehouseAuditId Almacen de auditoria
	 */
	public void setM_WarehouseAudit_ID(final int warehouseAuditId) {
		setM_Warehouse_ID(warehouseAuditId);
		setM_Locator_ID(MLocator.getLocatorID(getCtx(), warehouseAuditId, null));
		final MEXMEEstServ est = MEstServAlm.getEstServ(getCtx(), warehouseAuditId, null);
		if (est != null) {
			setTipoArea(est.getTipoArea());
			setEXME_Area_ID(est.getEXME_Area_ID());
		}
	}
	
	
	/**
	 * Crea la devolucion relacionada a un cargo por la cantidad indicada.
	 * 
	 * @param qty Cantidad a devolverS
	 * Se comenta codigo duplicado y se reutiliza codigo de AbstractCharges, WCatpCargo
	 * Lama: cargos 2014
	 */
	public MCtaPacDet createReturn(final BigDecimal qty) {
		// Se crea un cargo adicional con cantidad negativa en la extension cero
		final MCtaPacDet newDet = MCtaPacDet.copyFrom(this, getEXME_CtaPacExt_ID(), null);
		newDet.setDateOrdered(getDateOrdered());
		newDet.setDateDelivered(getDateDelivered());
		newDet.setQty(qty.negate());
		newDet.setRef_CtaPacDet_ID(getEXME_CtaPacDet_ID());
		newDet.setEXME_ActPacienteInd_ID(getEXME_ActPacienteInd_ID());
		newDet.setCondition(getCondition());
		newDet.setSeDevolvio(true);
		return newDet;
	}
	
	/**
	 * Agregar al cargo fecha y precio
	 * 
	 * @param priceCharges
	 * @param dateCharges
	 * Lama: cargos 2014
	 */
	public void setValuesToCharge( final BigDecimal priceCharges, final Timestamp dateCharges) {
		setCgoProcesado(false);
		setVisible(true);
		setPrices(priceCharges == null ? BigDecimal.ZERO : priceCharges, false, false);
		setEstaLineaTienePrecio(priceCharges != null);
		if (MCountry.isUSA(getCtx()) && getProducto() != null && getProducto().getProdOrg() != null) {
			setEXME_RevenueCode_ID(getProducto().getProdOrg().getEXME_RevenueCode_ID());
		}
		// charge.setEXME_Pos_ID(placeOfService.getSelectedId());
		setDateOrdered(dateCharges);
		setDatePromised(dateCharges);
		setDateDelivered(dateCharges);
	}
	
	
	/** Almacena los modificadores para el claim
	 * 
	 * @param cargo
	 */
	public boolean insertModifiers() {
		return MEXMEClaimCodes.insertModifiers(this);
	}

	/** Localizador propio siempre */
	public int getmLocatorSurID() {
		if(getM_Locator_ID()<=0){
			// Cuando el almacen que surte es consigna, debe buscarse el localizador
						// que recibe el movimiento. (Misma consulta ejecutada) 
			setM_Locator_ID(MLocator.getLocatorID(getCtx(), getM_Warehouse_ID(), null));
		}
		return getM_Locator_ID();
	}
	
	/** Localizador propio siempre */
	public void setmLocatorSurID() {
		if(getM_Locator_ID()<=0){//10001308
			// Cuando el almacen que surte es consigna, debe buscarse el localizador
			// que recibe el movimiento. (Misma consulta ejecutada) 
			setM_Locator_ID(MLocator.getLocatorID(getCtx(), getM_Warehouse_ID(), null));
		}
	}
	
	/** Buscar los costos en MCost */
	public void setCost(){
		final GetCost costos = new GetCost(getCtx(), getM_Product_ID());
		costos.setCosts();
		setCostAverage(costos.getCostAverage()==null?Env.ZERO:costos.getCostAverage());
		setCostStandard(costos.getCostStandard()==null?Env.ZERO:costos.getCostStandard());
		setPriceLastPO(costos.getPriceLastPO()==null?Env.ZERO:costos.getPriceLastPO());
	}
	
	/**
	 * Cuando el usuario selecciona un localizador puede ser de consigna
	 * sin embargo el localizador del proceso debe ser el propio
	 * @return
	 */
	public int getmLocatorUserID() {
		return mLocatorUserID;
	}

	public void setmLocatorUserID(final int mLocatorUserID) {
		this.mLocatorUserID = mLocatorUserID;
	}
	
	
	/**
	 * Crea el objeto del cargo
	 * 
	 * @param ctx
	 * @param productId
	 * @param negativo
	 * @param amt
	 * @param tipoLine
	 * @param trxName
	 * @return
	 */
	public static MCtaPacDet createChargeCCCmD(final Properties ctx, final int productId,
			final int C_Tax_ID, final boolean negativo, final BigDecimal amt, 
			final String tipoLine, final String trxName) {

		final MCtaPacDet linea = new MCtaPacDet(ctx, 0, trxName);
		try {
			linea.completeLine(0, /*C_Charge_ID,*/ productId, amt, null);
			linea.setVisible(true);
			linea.setC_Tax_ID(C_Tax_ID);
			linea.setTaxAmt(Env.ZERO);
			
			// El cargo de la aseguradora es negativo
			// es coaseguro el tipo de linea
			// y no lleva impuesto
			if (negativo) {
				linea.setTipoLinea(tipoLine);
//				linea.setC_Tax_ID(mTax.getC_Tax_ID());
//				linea.setTaxAmt(Env.ZERO);
			} else {
				// El cargo del particular es positivo
				// es cargo(normal) el tipo de linea
				// y lleva impuesto (deacuerdo a la configuracion del producto)
				linea.setTipoLinea(//C_Charge_ID > 0 ? tipoLine:
						MCtaPacDet.TIPOLINEA_Charge);
//				linea.setC_Tax_ID(mTax.getC_Tax_ID());
//				linea.setTaxAmt(Env.ZERO);
				linea.setSubTipoLinea(tipoLine);
				linea.setPricesTaxPatient();
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		}
		return linea;
	}
	
	
	
	
	
	
	
	
	
	/** Cantidad */
	private BigDecimal targetQty = Env.ZERO;
	
	public BigDecimal getTargetQty() {
		return targetQty;
	}
	
	
	/** Validar la linea antes del inventario o salida de inventario */
	public boolean isValidInventory(final ErrorList errorList){
		boolean success = true;
		if((!MEXMEMejoras.get(getCtx()).isControlExistencias()) 
			|| (X_M_Product.PRODUCTTYPE_Item.equalsIgnoreCase(getProduct().getProductType()) 
				&& !getProduct().isStocked())){
			return false;
		}

		// debe existir un almacen
		if (getmLocatorSurID()<=0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "error.requerido.warehouseID"));
		}
		// la cantidad no puede ser cero
		if (getQtyDelivered() == null || (!isSeDevolvio() && getQtyDelivered().compareTo(Env.ZERO) <= 0)) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "error.encCtaPac.cant0oMenor"));
		}
		// debe existir una unidad de medida
		if (getC_UOM_ID() <= 0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "error.citas.noTecleoUdm"));
		}
		if (getProduct().getProdOrg()== null){
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "msj.ligarProducto"));
		}
		// se evalua si el producto maneja lotes
		if (getProduct().getProdOrg() != null && getProduct().getProdOrg().isLot()  && getM_AttributeSetInstance_ID() < 0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "error.unLoteMinimo"));
		}

		targetQty = Env.ZERO;
		// validacion de conversiones de UDM. Si las udm son diferentes
		if (getProduct().getC_UOM_ID() == getC_UOM_ID()) {
			targetQty = getQtyDelivered();

		} else {
			// conversion entre unidades
			final MUOMConversion rates = MEXMEUOMConversion.validateConversions(getCtx(), getProduct().getM_Product_ID(), getC_UOM_ID(), null);
			if (rates == null) {
				errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "error.udm.noExisteConversion", getProduct().getName()));
			} else {
				final BigDecimal[] qtys = getProduct().qtyConversion(getQtyDelivered(), getC_UOM_ID());
				targetQty = qtys[0];// cantidad en udm de minima
			}
		}

		if (targetQty == null || targetQty.compareTo(Env.ZERO) == 0) {
			errorList.add(Error.VALIDATION_ERROR, Utilerias.getAppMsg(getCtx(), "error.encCtaPac.cant0oMenor"));
		}
		
		return success && errorList.isEmpty();
	}
	
	/**
	 * Cargos de la cuenta, con productos sustitutos
	 * 
	 * @param ctx
	 *            Ctx
	 * @param ctaPacId
	 *            Cuenta paciente
	 * @param productId
	 *            Producto
	 * @param uomId
	 *            Unidad
	 * @param trxName
	 *            Trx
	 * @return Listado de ids de cargos
	 */
	public static List<Integer> getSubstituteCharges(Properties ctx, int ctaPacId, int productId, int uomId, String trxName) {
		List<Integer> list = new ArrayList<Integer>();

		StringBuilder sql = new StringBuilder();
		sql.append("with sustitutos as (select substitute_id, uom_substitute_id from m_substitute where m_product_id = ? and c_uom_id = ?) ");
		sql.append("select det.exme_ctapacdet_id from exme_ctapacdet det  ");
		sql.append("inner join sustitutos sus on (det.m_product_id = sus.substitute_id and det.c_uom_id = sus.uom_substitute_id) ");
		sql.append("and det.exme_ctapac_id = ? and ");
		sql.append("  tipolinea = ? AND ");
		sql.append("  sedevolvio = ? AND ");
		sql.append("  isactive = ? AND ");
		sql.append("  isfacturado = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, uomId);
			pstmt.setInt(3, ctaPacId);
			pstmt.setString(4, X_EXME_CtaPacDet.TIPOLINEA_Charge);
			pstmt.setString(5, "N");
			pstmt.setString(6, "Y");
			pstmt.setString(7, "N");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * Obtiene los cargos disponibles del mismo producto y unidad de medida
	 * 
	 * @param ctx
	 *            Contexto
	 * @param ctaPacId
	 *            Cuenta
	 * @param productId
	 *            Producto
	 * @param uomId
	 *            Unidad de Medida
	 * @param trxName
	 *            Trx
	 * @return Listado de chargos (ids)
	 */
	public static List<Integer> getAvailableCharges(Properties ctx, int ctaPacId, int productId, int uomId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  exme_ctapacdet_id ");
		sql.append("FROM ");
		sql.append("  exme_ctapacdet ");
		sql.append("WHERE ");
		sql.append("  isactive = 'Y' ");
		sql.append("  AND exme_ctapac_id = ? ");
		sql.append("  AND m_product_id = ? ");
		sql.append("  AND c_uom_id = ? ");
		sql.append("  AND tipoLinea = ? ");
		sql.append("  AND SeDevolvio = 'N' ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));

		List<Integer> list = new ArrayList<Integer>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, ctaPacId);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, uomId);
			pstmt.setString(4, TIPOLINEA_Charge);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}
}
