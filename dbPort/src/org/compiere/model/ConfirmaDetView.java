/*
 * Derechos Reservados (c) a partir 2005 Expert Sistemas Computacionales, S. A. de C. V.
 * Sistema MedSys
 */
package org.compiere.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Modelo que representa una linea del detalle de un movimiento pendiente de
 * confirmar
 * <p>
 * Modificado por: $Author: mrojas $
 * <p>
 * Fecha: $Date: 2006/09/14 21:35:39 $
 * <p>
 * 
 * @author Gisela Lee Gonzalez
 * @version $Revision: 1.1 $
 */

public class ConfirmaDetView implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 5012664147155843722L;
	/** Logger */
	protected CLogger log = CLogger.getCLogger(getClass());
	/** @author rsolorzano campos necesarios para cambios de unidad de medida*/
	private int uomSel;
	private String uomSelName;
	private BigDecimal originalQty_Vol;
	private BigDecimal targetQty_Vol;
	private BigDecimal confirmedQty_Vol;
	private BigDecimal scrappedQty_Vol;
	private BigDecimal devueltaQty_Vol;
	private BigDecimal devolverQty_Vol;
	/** Contexto desde el render */
	public Properties ctx;
	/** Objeto Producto */
	protected MProduct prod = null;
	/** objeto udm */
	protected MUOM mUomSel = null;
	/** Propiedad UomName. */
	private String uomName = null;
	/** Propiedad Line. */
	private long line = 0;
	/** Propiedad MovementLineID. */
	private long movementLineID = 0;
	/** Fecha de garant�a para interfaz con Sistemas de Almacen */
	private String guaranteeDate = null;
	/** Cantidad que captura el usuario a devolver */
	private BigDecimal devolverQty;
	/** Propiedad ProductID. */
	private long productID = 0;
	/** Propiedad ProdName. */
	private String prodName = null;
	/** Propiedad OriginalQty.setProdName */
	private BigDecimal originalQty;
	/** Agregamos el precio del producto para almacenes en consigna */
	private double price = 0;
	/** Se agrega el numero de serie */
	private String numSerie = null;
	/** Propiedad TargetQty. */
	private BigDecimal targetQty;
	/** Propiedad Description. */
	private String description = null;
	/** */
	private String lote;
	/** */
	private String fechaLote;
	/** Propiedad ConfirmedQty. */
	private BigDecimal confirmedQty;
	/** Propiedad ScrappedQty. */
	private BigDecimal scrappedQty;
	/** Noelia: agrega cantidad desetProdNamevuelta para Ceye */
	private BigDecimal devueltaQty;
	/** */
	private String prodValue = null;

	
	
	public String getGuaranteeDate() {
		return guaranteeDate;
	}

	public void setGuaranteeDate(String guaranteeDate) {
		this.guaranteeDate = guaranteeDate;
	}

	public long getMovementLineID() {
		return this.movementLineID;
	}

	public void setMovementLineID(long movementLineID) {
		this.movementLineID = movementLineID;
	}

	public long getLine() {
		return this.line;
	}

	public void setLine(long line) {
		this.line = line;
	}

	public long getProductID() {
		return this.productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getProdName() {
		return this.prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getUomName() {
		return this.uomName;
	}
	
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
    /** {@link #setRSet(ResultSet, boolean, int)}: 
     * M_MovementLine.OriginalQty*/
	public BigDecimal getOriginalQty() {
		return this.originalQty;
	}

	public void setOriginalQty(BigDecimal originalQty) {
		this.originalQty = originalQty;
	}
	/** {@link #setRSet(ResultSet, boolean, int)}: 
	 * M_MovementLine.TargetQty*/
	public BigDecimal getTargetQty() {
		return this.targetQty;
	}

	public void setTargetQty(BigDecimal targetQty) {
		this.targetQty = targetQty;
	}
	/** {@link #setRSet(ResultSet, boolean, int)}: 
	 * Si es desde aplicacion (isWeb)
	 * 	Si EXME_ConfigEC.isCargaDiferAlm
	 * 		M_MovementLineConfirm.ConfirmedQty-M_MovementLineConfirm.ScrappedQty
	 * 	Si No
	 * 		M_MovementLineConfirm.ConfirmedQty (cero cuando es negativa)
	 * Si es interfaz
	 * 	SM_Confirma.Cantidad
	 * */
	public BigDecimal getConfirmedQty() {
		return this.confirmedQty;
	}

	public void setConfirmedQty(BigDecimal confirmedQty) {
		this.confirmedQty = confirmedQty;
	}
	/** {@link #setRSet(ResultSet, boolean, int)}: 
     * M_MovementLineConfirm.ScrappedQty*/
	public BigDecimal getScrappedQty() {
		return this.scrappedQty;
	}

	public void setScrappedQty(BigDecimal scrappedQty) {
		this.scrappedQty = scrappedQty;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProdValue() {
		return prodValue;
	}

	public void setProdValue(String prodValue) {
		this.prodValue = prodValue;
	}

	public BigDecimal getDevueltaQty() {
		return devueltaQty;
	}

	public void setDevueltaQty(BigDecimal devueltaQty) {
		this.devueltaQty = devueltaQty;
	}

	public String getFechaLote() {
		return fechaLote;
	}

	public void setFechaLote(String fechaLote) {
		this.fechaLote = fechaLote;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public BigDecimal getDevolverQty() {
		return devolverQty;
	}

	public void setDevolverQty(BigDecimal devolverQty) {
		this.devolverQty = devolverQty;
	}

	/** {@link #setRSet(ResultSet, boolean, int)}
	 * M_MovementLineConfirm.op_uom */
	public int getUomSel() {
		return uomSel;
	}

	public void setUomSel(int uomSel) {
		this.uomSel = uomSel;
	}

	/** {@link #setRSet(ResultSet, boolean, int)}
	 * M_MovementLineConfirm.op_uom.Name */
	public String getUomSelName() {
		return uomSelName;
	}

	public void setUomSelName(String uomSelName) {
		this.uomSelName = uomSelName;
	}

	/** @see #getOriginalQty() 
	 * columnas: M_MovementLine.OriginalQty_Vol*/
	public BigDecimal getOriginalQty_Vol() {
		return originalQty_Vol;
	}

	public void setOriginalQty_Vol(BigDecimal originalQty_Vol) {
		this.originalQty_Vol = originalQty_Vol;
	}

	/** @see #getTargetQty() 
	 * columnas: M_MovementLine.TargetQty_Vol*/
	public BigDecimal getTargetQty_Vol() {
		return targetQty_Vol;
	}

	public void setTargetQty_Vol(BigDecimal targetQty_Vol) {
		this.targetQty_Vol = targetQty_Vol;
	}

	/** @see #getConfirmedQty() 
	 * columnas: M_MovementLineConfirm.ConfirmedQty_Vol
	 *       y   M_MovementLineConfirm.ScrappedQty_Vol  */
	public BigDecimal getConfirmedQty_Vol() {
		return confirmedQty_Vol;
	}

	public void setConfirmedQty_Vol(BigDecimal confirmedQty_Vol) {
		this.confirmedQty_Vol = confirmedQty_Vol;
	}

	/** @see #getScrappedQty() 
	 * columnas: M_MovementLineConfirm.ScrappedQty_Vol*/
	public BigDecimal getScrappedQty_Vol() {
		return scrappedQty_Vol;
	}

	public void setScrappedQty_Vol(BigDecimal scrappedQty_Vol) {
		this.scrappedQty_Vol = scrappedQty_Vol;
	}

	public BigDecimal getDevueltaQty_Vol() {
		return devueltaQty_Vol;
	}

	public void setDevueltaQty_Vol(BigDecimal devueltaQty_Vol) {
		this.devueltaQty_Vol = devueltaQty_Vol;
	}

	public BigDecimal getDevolverQty_Vol() {
		return devolverQty_Vol;
	}

	public void setDevolverQty_Vol(BigDecimal devolverQty_Vol) {
		this.devolverQty_Vol = devolverQty_Vol;
	}

	public MUOM getmUomSel() {
		mUomSel = MUOM.get(ctx, uomSel);
		return mUomSel;
	}

	public void setmUomSel(MUOM mUomSel) {
		this.mUomSel = mUomSel;
	}

	public MProduct getProd() {
		prod = new MProduct(ctx, (int) productID, null);
		return prod;
	}

	public void setProd(MProduct prod) {
		this.prod = prod;
	}

	/**
	 * Nombre de la unidad de medida
	 * 
	 * @return
	 */
	public String nombreUom() {
		String nameUom = "";
		try {
			MUOM mUomSel = MUOM.get(ctx, uomSel);

			if (uomSel > 0) {
				nameUom = MEXMEUOM.nombreUDM(ctx, mUomSel.getC_UOM_ID());
			} else {
				nameUom = MEXMEUOM.nombreUDM(ctx, prod.getUom().getC_UOM_ID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nameUom;
	}

	/**
	 * confirmedQtyStr
	 * @return
	 */
	public String confirmedQtyStr() {
		if (uomSel == 0 || getmUomSel().getC_UOM_ID() == prod.getC_UOM_ID()) {
			return String.valueOf(getConfirmedQty().intValue());
		} else if (getProd().getC_UOMVolume_ID() == getmUomSel().getC_UOM_ID()) {
			return String.valueOf(getConfirmedQty_Vol().intValue());
		}
		return "";
	}
	
	/**
	 * Cantidad surtida
	 * @return
	 */
	public String targetQtyStr(){
		/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
		if (uomSel == 0 || getmUomSel().getC_UOM_ID() == prod.getC_UOM_ID()) {
			return String.valueOf(getTargetQty().intValue());
		} else if (getProd().getC_UOMVolume_ID() == getmUomSel().getC_UOM_ID()) {
			return String.valueOf(getTargetQty_Vol().intValue());
		}
		return "";
	}	
	
	/**
	 * setRSet
	 * @param rset
	 * @param isWeb
	 * @param index
	 * @throws SQLException
	 */
	public void setRSet(final ResultSet rset, final boolean isWeb,
			final int index) throws SQLException {
		setMovementLineID(rset.getLong("M_MovementLine_ID"));
		setLine(index * 10);
		setProductID(rset.getLong("M_Product_ID"));
		setProdValue(rset.getString("value"));
		setProdName(rset.getString("ProdName"));
		setUomName(rset.getString("UomName"));
		//M_MovementLineConfirm.op_uom
		setUomSel(rset.getInt("op_uom"));
		//M_MovementLineConfirm.op_uom.Name
		setUomSelName(rset.getString("UOMSel"));
		//M_MovementLine.OriginalQty
		setOriginalQty(rset.getBigDecimal("OriginalQty"));
		//M_MovementLine.OriginalQty_Vol
		setOriginalQty_Vol(rset.getBigDecimal("OriginalQty_Vol"));
		//M_MovementLine.TargetQty
		setTargetQty(rset.getBigDecimal("TargetQty"));
		//M_MovementLine.TargetQty_Vol
		setTargetQty_Vol(rset.getBigDecimal("TargetQty_Vol"));

		if (isWeb) {
			/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
			if(MEXMEConfigEC.get(Env.getCtx()).isCargaDiferAlm()){// Al que surte
				
				//M_MovementLineConfirm.ConfirmedQty-M_MovementLineConfirm.ScrappedQty
				setConfirmedQty(rset.getBigDecimal("ConfirmedQty").subtract(
						rset.getBigDecimal("ScrappedQty")));
				setConfirmedQty_Vol(rset.getBigDecimal("ConfirmedQty_Vol")
						.subtract(rset.getBigDecimal("ScrappedQty_Vol")));
				
			} else { // Al que confirma/solicita
				//M_MovementLineConfirm.ConfirmedQty > 0 . else 0
				setConfirmedQty(rset.getBigDecimal("ConfirmedQty").compareTo(
						BigDecimal.ZERO) > 0 ? rset.getBigDecimal("ConfirmedQty")
						: BigDecimal.ZERO);
				
				setConfirmedQty_Vol(rset.getBigDecimal("ConfirmedQty_Vol").compareTo(
						BigDecimal.ZERO) > 0 ? rset.getBigDecimal("ConfirmedQty_Vol")
						: BigDecimal.ZERO);
			}
			
		} else { // interfaz
			// SM_Confirma.GuaranteeDate
			setGuaranteeDate(rset.getString("GuaranteeDate"));
			// SM_Confirma.Cantidad
			setConfirmedQty(rset.getBigDecimal("Cantidad").compareTo(
					BigDecimal.ZERO) > 0 ? rset.getBigDecimal("Cantidad")
					: BigDecimal.ZERO);
			setConfirmedQty_Vol(BigDecimal.ZERO);
		}
		//M_MovementLineConfirm.ScrappedQty
		setScrappedQty(rset.getBigDecimal("ScrappedQty"));
		//M_MovementLineConfirm.ScrappedQty_Vol
		setScrappedQty_Vol(rset.getBigDecimal("ScrappedQty_Vol"));
		setDescription(rset.getString("Description"));
		// Noelia: se agrega cantidad devuelta para ceye
		// M_MovementLine.OriginalQty - Confirmed ...
		setDevueltaQty(getOriginalQty().subtract(getConfirmedQty()));
		setDevueltaQty_Vol(rset.getBigDecimal("OriginalQty_Vol").subtract(
				getConfirmedQty_Vol()));
		//M_MovementLine.OriginalQty
		setDevolverQty(rset.getBigDecimal("OriginalQty"));
		setDevolverQty_Vol(rset.getBigDecimal("OriginalQty_Vol"));
		
		setNumSerie(rset.getString("numSerie")); // numero de serie
		setPrice(rset.getDouble("priceList"));// precio del producto

		setLote(rset.getString("lote"));
		setFechaLote(rset.getString("fechaLote"));

		/**
		 * @rsolorzano campos para unidad de medida
		 */
//		if (isWeb) {
//			setConfirmedQty_Vol(rset.getBigDecimal("ConfirmedQty_Vol")
//					.subtract(rset.getBigDecimal("ScrappedQty_Vol")));
//		} else {
//			setConfirmedQty_Vol(BigDecimal.ZERO);
//		}
	}
	
	/**
	 * Actualizar cantidades de confirmarción cuando es ceye
	 * CENTRAL DE EQUIPOS DE ESTERILIZACIóN
	 * @param mMovementLine
	 * @return
	 */
	public List<String> setQtyCeye(final MMovementLine mMovementLine){
		final List<String> errores = new ArrayList<String>();
		setScrappedQty(getDevolverQty());
		setScrappedQty_Vol(getDevolverQty_Vol());

		// La cantidad a devolver no puede ser negativa
		BigDecimal scrappedQtyTmp;
		BigDecimal targetQtyTmp;
		if (mMovementLine.getOp_Uom() == mMovementLine.getProduct()
				.getC_UOMVolume_ID()) { // unidad volumen
			scrappedQtyTmp = getScrappedQty_Vol();
			targetQtyTmp = getTargetQty_Vol();
		} else { // unidad minima
			scrappedQtyTmp = getScrappedQty();
			targetQtyTmp = getTargetQty();
		}

		if (scrappedQtyTmp.compareTo(BigDecimal.ZERO) < 0) { // >=0
			log.log(Level.INFO, "confirmar.2");
			errores.add("msj.error.cantDevolverNeg");
		} else {
			// No se puede devolver mas de la cantidad solicitada
			if (scrappedQtyTmp.compareTo(targetQtyTmp) == 1) { // >
				log.log(Level.INFO, "confirmar.3");
				errores.add("error.encCtaPac.devolInsuficiente");
			} else {

				setConfirmedQty(getTargetQty().subtract(
						getScrappedQty()));
				setDevueltaQty(getOriginalQty().subtract(
						getConfirmedQty()));

				setConfirmedQty_Vol(getTargetQty_Vol().subtract(
						getScrappedQty_Vol()));
				setDevueltaQty_Vol(getOriginalQty_Vol().subtract(
						getConfirmedQty_Vol()));
			}
		}
		return errores;
	}
	
	/**
	 * Actualizar cantidad de confirmación
	 */
	public void setQtyConfirm(){

		setScrappedQty(getTargetQty().subtract(
						getConfirmedQty()));// 10-7
		setScrappedQty_Vol(getTargetQty_Vol().subtract(
						getConfirmedQty_Vol()));

	}
}
