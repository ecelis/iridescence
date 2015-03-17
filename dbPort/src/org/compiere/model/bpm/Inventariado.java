package org.compiere.model.bpm;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.MCtaPacDet;
import org.compiere.model.MEXMEActPacienteInd;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Lineas de inventario
 * para facturación en venta de mostrador
 * @author twry
 *
 */
public class Inventariado {
	private Properties iCtx = null;
	/** Cargo (no siempre se crea el registro en la bd) */
	private MCtaPacDet iCargo = null;
//	/** Cuenta paciente (puede no existir) */
//	private MEXMECtaPac iCtapac = null;
//	/** Nombre de transaccion */
//	private String iTrxName = null;
	/** unidad de medida minima */
	private int cUOMIDSurt = 0;
	/** cantidad en mov */
	private BigDecimal targetQty = Env.ZERO;
	/** cantidad del usuario */
	private BigDecimal qtyEntred = Env.ZERO;
	/** cantidad de pedido */
	private BigDecimal qtyPedido = Env.ZERO;
	/** Producto */
	private MProduct mProduct = null;
	/** atributo (normalmente cero) */
	private int mAttribSetInstID = 0;
	/** Cantidad de inventario */
	private BigDecimal scrappedQty = Env.ZERO;
	/** comentario */
	private String description = null;
	/** Cantidad del sistema */
	private BigDecimal qtyAvailable = Env.ZERO;
	/** Con existencias */
	private boolean noStock = false;
	/** Linea de factura */
	private MInvoiceLine mInvoiceLine = null;
	/** Linea del expediente*/
	private MEXMEActPacienteInd apIndLine = null;
	/** Cantidad del servicio */
	private BigDecimal qty = Env.ZERO;

	/**
	 * Constructor
	 * @param pCtx
	 * @param cargo
	 * @param ctapac
	 * @param trxName
	 */
	public Inventariado (final Properties pCtx, final MEXMEActPacienteInd cargo){
		iCtx = pCtx;
		apIndLine = cargo;
	}
	
	/**
	 * Constructor
	 * @param pCtx
	 * @param cargo
	 * @param ctapac
	 * @param trxName
	 */
	public Inventariado (final Properties pCtx, final MCtaPacDet cargo /*, final MEXMECtaPac ctapac, final String trxName*/){
		iCtx = pCtx;
		iCargo = cargo;
//		iCtapac = ctapac;
//		iTrxName = trxName;
	}
	
	/**
	 * Constructor
	 * @param pCtx
	 * @param cargo
	 * @param ctapac
	 * @param trxName
	 */
	public Inventariado (final Properties pCtx, final MCtaPacDet cargo, final String trxName){
		iCtx = pCtx;
		iCargo = cargo;
//		iCtapac = cargo.getCtaPac();
//		iTrxName = trxName;

		cUOMIDSurt=cargo.getC_UOM_ID();
		qtyEntred=cargo.getQtyDelivered();
		qtyPedido=cargo.getQtyDelivered();
		mProduct=cargo.getProduct();
		mAttribSetInstID=cargo.getM_AttributeSetInstance_ID();//10001018

	}
	
	public void fill (){
		setmProduct(iCargo.getProduct());
		setmAttribSetInstID(iCargo.getM_AttributeSetInstance_ID());
		setQtyAvailable(iCargo.getQtyDelivered());
		setScrappedQty(iCargo.getQtyDelivered());
		setDescription(iCargo.getDescription());
	}
	
	public int getmAttribSetInstID() {
		return mAttribSetInstID;
	}

	public void setmAttribSetInstID(int mAttribSetInstID) {
		this.mAttribSetInstID = mAttribSetInstID;
	}

	public MProduct getmProduct() {
		return mProduct;
	}

	public void setmProduct(MProduct mProduct) {
		this.mProduct = mProduct;
	}
	
	public int getcUOMIDSurt() {
		return cUOMIDSurt;
	}

	public void setcUOMIDSurt(int cUOMIDSurt) {
		this.cUOMIDSurt = cUOMIDSurt;
	}

	public BigDecimal getTargetQty() {
		return targetQty;
	}

	public void setTargetQty(BigDecimal targetQty) {
		this.targetQty = targetQty;
	}

	public BigDecimal getQtyEntred() {
		return qtyEntred;
	}

	public void setQtyEntred(BigDecimal qtyEntred) {
		this.qtyEntred = qtyEntred;
	}

	public BigDecimal getQtyPedido() {
		return qtyPedido;
	}

	public void setQtyPedido(BigDecimal qtyPedido) {
		this.qtyPedido = qtyPedido;
	}

	public Properties getiCtx() {
		return iCtx;
	}

	public void setiCtx(Properties iCtx) {
		this.iCtx = iCtx;
	}

	public MCtaPacDet getiCargo() {
		return iCargo;
	}

	public void setiCargo(MCtaPacDet iCargo) {
		this.iCargo = iCargo;
	}

//	public MEXMECtaPac getiCtapac() {
//		return iCtapac;
//	}

//	public void setiCtapac(MEXMECtaPac iCtapac) {
//		this.iCtapac = iCtapac;
//	}

//	public String getiTrxName() {
//		return iTrxName;
//	}
//
//	public void setiTrxName(String iTrxName) {
//		this.iTrxName = iTrxName;
//	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getScrappedQty() {
		return scrappedQty;
	}

	public void setScrappedQty(BigDecimal scrappedQty) {
		this.scrappedQty = scrappedQty;
	}
	public BigDecimal getQtyAvailable() {
		return qtyAvailable;
	}

	public void setQtyAvailable(BigDecimal qtyAvailable) {
		this.qtyAvailable = qtyAvailable;
	}
	
	public boolean isNoStock() {
		return noStock;
	}
	
	public void setNoStock(boolean isOnStock) {
		noStock = isOnStock;
	}
	
	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	
	public MInvoiceLine getmInvoiceLine() {
		return mInvoiceLine;
	}

	public void setmInvoiceLine(MInvoiceLine mInvoiceLine) {
		this.mInvoiceLine = mInvoiceLine;
	}
	public MEXMEActPacienteInd getApIndLine() {
		return apIndLine;
	}

	public void setApIndLine(MEXMEActPacienteInd apIndLine) {
		this.apIndLine = apIndLine;
	}
	
	/**
	 * Consultar las existencias
	 * @param mCtaPacDet: Cargo, podría estar en la base o no
	 * @param consigna: TRUE Si la transacción es consigna (Basado en el supuesto que Consigna Siempre Tendrá Existencias)
	 */
	public void updateNoStocks(final MCtaPacDet mCtaPacDet, boolean consigna, boolean reloadQty){
		noStock = false;
		
		// cantidad seleccionada por el usuario en minima
		final BigDecimal[] qtys = mCtaPacDet.getProduct().qtyConversion(
				mCtaPacDet.getQtyDelivered(),mCtaPacDet.getC_UOM_ID());//TODO Revisar cambio y no se habia detectado el error
				//mCtaPacDet.getProduct().getC_UOM_ID());
		final BigDecimal qtyDeliveredMin = qtys[0];//Vol  = qtys[1];

		// cantidad en el almacen en minima
		BigDecimal QtyAvailable;
		if(reloadQty && mCtaPacDet.getProduct().isStocked() && mCtaPacDet.getProduct().isItem() && !mCtaPacDet.isSeDevolvio()){
			QtyAvailable = MStorage.getQtyAvailable(0,
					mCtaPacDet.getmLocatorSurID()//10001308
					, mCtaPacDet.getM_Product_ID() 
					, mCtaPacDet.getM_AttributeSetInstance_ID() 
					, mCtaPacDet.get_TrxName());
			if (QtyAvailable == null) {
				QtyAvailable = Env.ZERO;
			}
		} else {
			QtyAvailable = qtyDeliveredMin;
		}
		
		// Revisa si es suficiente el stock, consigna siempre tendrá suficiente stock, sino se llenan los datos sigs.
		if (!consigna && qtyDeliveredMin.compareTo(QtyAvailable) > 0) {
			final BigDecimal qtyFaltante = qtyDeliveredMin.subtract(QtyAvailable);
			description = Utilerias.getMessage(mCtaPacDet.getCtx(),null,"msj.ajustesInventario")+":"+qtyFaltante;
			scrappedQty = qtyDeliveredMin;
			qtyAvailable = QtyAvailable;
			noStock = true;
		}
	}
}
