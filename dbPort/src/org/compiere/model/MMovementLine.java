/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.MedsysException;
import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.MovementLine;
import org.compiere.model.bean.TransferBean;
import org.compiere.model.bpm.BeanView;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Inventory Move Line Model
 * 
 * @author Jorg Janke
 * @version $Id: MMovementLine.java,v 1.4 2006/11/02 21:36:39 taniap Exp $
 * Modificado por Lorena Lama (Marzo 2014)
 */
public class MMovementLine extends X_M_MovementLine {

	/** serialVersionUID */
	private static final long serialVersionUID = -2162871269542235520L;
	/** Static logger */
	private static CLogger slog = CLogger.getCLogger(MMovementLine.class);
	/** Unidad de medida */
	private MUOM uom = null;
	/** Precio del producto */
	private double price = 0;
	/** Fecha de garant�a para interfaz con Sistemas de Almacen */
	private String guaranteeDate = null;
	/** movimiento */
	private MMovement mMovement = null;
	/** cuenta paciente */
	private MEXMECtaPac mCtaPac = null;
	/** unidades */
	protected MUOM uomSel = null;
	/** unidades */
	protected String uomSelName = null;
	/** lote */
	private String lot;
	/** fecha */
	private String fecha;
	/** lote */
	private boolean lote;
	/** cantidad */
	public BigDecimal quantityUser;
	/** nuevo */
	boolean newReg = false;
	/**@deprecated  No es conveniente usar este objeto para procesos como Cargos, no cuenta con documentacion*/
	private BeanView mBeanView = null;
	private MProduct product;
	
	/**
	 * Standard Cosntructor
	 * 
	 * @param ctx
	 *            context
	 * @param M_MovementLine_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MMovementLine(final Properties ctx, final int M_MovementLine_ID, final String trxName) {
		super(ctx, M_MovementLine_ID, trxName);
		if (is_new()) {
			setM_AttributeSetInstance_ID(0); // ID
			setMovementQty(Env.ZERO); // 1
			setTargetQty(Env.ZERO); // 0
			setScrappedQty(Env.ZERO);
			setConfirmedQty(Env.ZERO);
			setProcessed(false);
		}
	} // MMovementLine

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rset
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MMovementLine(final Properties ctx, final ResultSet rset, final String trxName) {
		super(ctx, rset, trxName);
	} // MMovementLine

	/**
	 * Parent constructor
	 * 
	 * @param parent
	 *            parent
	 */
	public MMovementLine(final MMovement parent) {
		this(parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setM_Movement_ID(parent.getM_Movement_ID());
	} // MMovementLine

	/**
	 * Get AttributeSetInstance To
	 * 
	 * @return ASI
	 */
	public int getM_AttributeSetInstanceTo_ID() {
		int attribSetInsToID = super.getM_AttributeSetInstanceTo_ID();
		if (attribSetInsToID == 0) {
			attribSetInsToID = super.getM_AttributeSetInstance_ID();
		}
		return attribSetInsToID;
	} // getM_AttributeSetInstanceTo_ID

	/**
	 * Add to Description
	 * 
	 * @param description
	 *            text
	 */
	public void addDescription(final String description) {
		final String desc = getDescription();
		if (desc == null){
			setDescription(description);
		}else{
			setDescription(desc + " | " + description);
		}
	} // addDescription
	
	

	/**
	 * Get Product
	 * 
	 * @return product or null if not defined
	 */
	public MProduct getProduct() {
		if (getM_Product_ID() != 0 && product == null) {
			product = MProduct.get(getCtx(), getM_Product_ID());
		}
		return product;
	} // getProduct

	/**
	 * Set Movement Qty - enforce UOM
	 * 
	 * @param movementQty
	 *            qty
	 */
	public void setMovementQty(final BigDecimal pMovementQty) {
		BigDecimal movementQty = pMovementQty;
		if (movementQty != null) {
			final MProduct product = getProduct();
			if (product != null) {
				final int precision = product.getUOMPrecision();
				movementQty = movementQty.setScale(precision, BigDecimal.ROUND_HALF_UP);
			}
		}
		super.setMovementQty(movementQty);
	} // setMovementQty

	/**
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true
	 */
	protected boolean beforeSave(final boolean newRecord) {
		// Set Line No
		if (getLine() == 0) {
			final String sql = "SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM M_MovementLine WHERE M_Movement_ID=?";
			final int iid = DB.getSQLValue(get_TrxName(), sql, getM_Movement_ID());
			setLine(iid);
		}

		if (newRecord) {
			setMovementQty(getMovementQty());
		}

		// expert : Lama : guardar la OrgTrx del almacen de salida (surtido)
		if (getM_Locator_ID() > 0) {
			final StringBuilder sql = new StringBuilder(
					"SELECT EXME_EstServ.AD_OrgTrx_ID ")
					.append(" FROM EXME_EstServ ")
					.append(" INNER JOIN EXME_EstServAlm estAlm on (EXME_EstServ.exme_estserv_id = estAlm.exme_estserv_id AND estAlm.isactive = 'Y')")
					.append(" INNER JOIN M_Warehouse ware on ( ware.m_warehouse_id = estAlm.m_warehouse_id AND ware.isactive = 'Y')")
					.append(" INNER JOIN m_locator LOC ON (loc.m_warehouse_id = ware.m_warehouse_id and loc.m_locator_ID = ? and loc.isactive = 'Y')")
					.append(" WHERE EXME_EstServ.isactive = 'Y'");
			final int orgtrx = DB.getSQLValue(get_TrxName(), sql.toString(),
					getM_Locator_ID());
			if (orgtrx > 0){
				setAD_OrgTrx_ID(orgtrx);
			}
		}
		// expert : Lama.

		// Clase de producto para el post
		if (getEXME_ProductClassFact_ID() <= 0 && getM_Product_ID() > 0) {
			setEXME_ProductClassFact_ID(MEXMEProductClassFact.get(getCtx(),
					getM_Product_ID(), get_TrxName()));
		}
		
		if (hasSimilarRecords()) {
			log.saveError(null, Utilerias.getAppMsg(getCtx(), "error.movSimilar"));
			return false;
		}

		return true;
	} // beforeSave
	
	/**
	 * Primer registro similar
	 * 
	 * @return Registro o -1 si no tiene
	 */
	public int getSimilarRecord(String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  m_movementline_id ");
		sql.append("FROM ");
		sql.append("  m_movementline ");
		sql.append("WHERE ");
		sql.append("  m_movement_id = ? AND ");
		sql.append("  m_movementline_id != ? AND ");
		sql.append("  m_locator_id = ? AND ");
		sql.append("  m_attributesetinstance_id = ? AND ");
		sql.append("  m_product_id = ? AND ");
		sql.append("  isactive = 'Y' ");

		int id = DB.getSQLValue(trxName, sql.toString(), getM_Movement_ID(), getM_MovementLine_ID(), getM_Locator_ID(), getM_AttributeSetInstance_ID(), getM_Product_ID());
		// NOTA estos criterios se utilizan tambien para buscar las refencias en el proceso de devolucion
		// en caso de haber un cambio, debera validarse la devolucion de productos tambien

		return id;
	}

	/**
	 * Validar que un encabezado MMovement no podra tener lineas similares
	 * 
	 * @return
	 */
	private boolean hasSimilarRecords() {
		return getSimilarRecord(get_TrxName()) > 0;
	}

	public MUOM getUom() {
		return uom;
	}

	public void setUom(final MUOM uom) {
		this.uom = uom;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public String getGuaranteeDate() {
		return guaranteeDate;
	}

	public void setGuaranteeDate(final String guaranteeDate) {
		this.guaranteeDate = guaranteeDate;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(final String lot) {
		this.lot = lot;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(final String fecha) {
		this.fecha = fecha;
	}

	public boolean isLote() {
		return lote;
	}

	public void setLote(final boolean lote) {
		this.lote = lote;
	}

	public BigDecimal getQuantityUser() {
		return quantityUser;
	}

	public void setQuantityUser(final BigDecimal quantityUser) {
		this.quantityUser = quantityUser;
	}

	public boolean isNew() {
		return newReg;
	}

	public void setNew(final boolean isNew) {
		this.newReg = isNew;
	}

	public String getUomSelName() throws Exception {
		if (getOp_Uom() > 0) {
			uomSelName = MUOM.nombreUDM(getCtx(), getUomSel().getC_UOM_ID());
		} else {
			uomSelName = MUOM.nombreUDM(getCtx(), getProduct().getUom()
					.getC_UOM_ID());
		}
		return uomSelName;
	}

	public void setUomSelName(final String uomSelName) {
		this.uomSelName = uomSelName;
	}

	public MUOM getUomSel() {
		if (uomSel == null) {
			uomSel = MUOM.get(getCtx(), getOp_Uom());
		}
		return uomSel;
	}

	public void setUomSel(final MUOM uomSel) {
		this.uomSel = uomSel;
	}

	/**
	 * Obtenemos el detalle de los movimientos a preparar
	 * 
	 * @param movementID
	 *            El id del movimiento a preparar
	 * @return Una lista con el detalle de los movimientos a preparar
	 * @deprecated
	 */
	public static List<org.compiere.model.MovementLine> getMovementLinePrep(final Properties ctx,
			final int movementID, final String trxName) throws Exception {
		final List<org.compiere.model.MovementLine> lista = new ArrayList<org.compiere.model.MovementLine>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			sql.append(
					"SELECT M_MovementLine.Line, M_MovementLine.OriginalQty, p.Value, p.Name, u.Name Udm FROM M_MovementLine ")
					.append("INNER JOIN M_Product p ON M_MovementLine.M_Product_ID = p.M_Product_ID ")
					.append("INNER JOIN C_Uom u ON p.C_Uom_ID = u.C_Uom_ID ")
					.append("WHERE M_MovementLine.M_Movement_ID = ? AND M_MovementLine.IsActive = 'Y' AND p.IsActive = 'Y' ")
					.append("ORDER BY M_MovementLine.Line");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, movementID);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				final org.compiere.model.MovementLine movementDet = new org.compiere.model.MovementLine();
				movementDet.setLine(rset.getLong("Line"));
				movementDet.setOriginalQty(rset.getLong("OriginalQty"));
				movementDet.setProdValue(rset.getString("Value"));
				movementDet.setProdName(rset.getString("Name"));
				movementDet.setUomName(rset.getString("Udm"));
				lista.add(movementDet);
			}

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "getMovementLinePrep (" + sql + ")", e);
			throw e;
		} finally {
			DB.close(rset, pstmt);
		}
		return lista;
	}

	/**
	 * MEXMEMovement
	 * 
	 * @return
	 */
	public MMovement getMovement() {
		if (mMovement == null) {
			mMovement = new MMovement(getCtx(), getM_Movement_ID(),
					get_TrxName());
		}
		return mMovement;
	}

	/**
	 * Obtenemos la cuenta paciente
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

	public void setMovement(final MMovement mexmeMovement) {
		if (mMovement == null) {
			mMovement = mexmeMovement;
		}
	}

	/**
	 * Cantidades
	 * 
	 * @param qty
	 * @param surtir
	 * @return
	 * @deprecated
	 */
	public String completeLine(final BigDecimal qty, final boolean surtir) {
		// Solicitud y surtido
		// con la misma unidad de medida
		setMovementQty(qty);
		setOriginalQty(qty);
		setTargetQty(surtir ? qty : Env.ZERO);

		setMovementQty_Vol(qty);
		setOriginalQty_Vol(qty);
		setTargetQty_Vol(surtir ? qty : Env.ZERO);

		// Convertir a minima
		if (getOp_Uom() == getProduct().getC_UOMVolume_ID()) {
			BigDecimal qtyEntered = MEXMEUOMConversion.convertProductFrom(
					getCtx(), getM_Product_ID(), getProduct().getC_UOM_ID(),
					qty, null);

			qtyEntered = qtyEntered.setScale(
					MUOM.getPrecision(getCtx(), getProduct().getC_UOM_ID()),
					BigDecimal.ROUND_HALF_UP);

			setMovementQty(qty);
			setOriginalQty(qty);
			setTargetQty(surtir ? qty : Env.ZERO);
		}

		// Convertir a volumen
		else if (getOp_Uom() == getProduct().getC_UOM_ID()) {
			BigDecimal qtyEntered = MEXMEUOMConversion.convertProductFrom(
					getCtx(), getM_Product_ID(), getProduct()
							.getC_UOMVolume_ID(), qty, null);

			qtyEntered = qtyEntered
					.setScale(MUOM.getPrecision(getCtx(), getProduct()
							.getC_UOMVolume_ID()), BigDecimal.ROUND_HALF_UP);

			setMovementQty_Vol(qty);
			setOriginalQty_Vol(qty);
			setTargetQty_Vol(surtir ? qty : Env.ZERO);
		}

		return null;
	}

	/**
	 * Campo vacio para reporte dinamico
	 * 
	 * @return
	 */
	public String getDummy() {
		return StringUtils.EMPTY;
	}

	/**
	 * Cantidad solicitada, revisa la unidad de medida de solicitud para hacer
	 * el calculo
	 * 
	 * @return
	 */
	public BigDecimal getRequestedQty() {
		MUOM uomSel = MUOM.get(getCtx(), getOp_Uom());
		if (getOp_Uom() == 0 || getProduct().getC_UOM_ID() == uomSel.getC_UOM_ID()) {
			return getOriginalQty();
		} else if (getProduct().getC_UOMVolume_ID() == uomSel.getC_UOM_ID()) {
			return getOriginalQty_Vol();
		}
		return BigDecimal.ZERO;
	}

	/**
	 * Unidad de medida del pedido
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getRequestedUom() throws Exception {
		if (getOp_Uom() == 0) {
			return MUOM.nombreUDM(getCtx(), getProduct().getUom().getC_UOM_ID());
		} else {
			MUOM uomSel = MUOM.get(getCtx(), getOp_Uom());
			return MUOM.nombreUDM(getCtx(), uomSel.getC_UOM_ID());
		}
	}

	/**@deprecated No es conveniente usar este objeto para procesos como Cargos, no cuenta con documentacion */
	public BeanView getBeanView() {
		return mBeanView;
	}
	/**@deprecated  No es conveniente usar este objeto para procesos como Cargos, no cuenta con documentacion*/
	public void setBeanView(final BigDecimal qty){ 
		mBeanView = new BeanView(qty, getOp_Uom(), null);
	}
	
//	/**
//	 * Recuperamos la linea de confirmacion relacionada a una
//	 * linea de movimiento.
//	 */
//	public MMovementLineConfirm getFromMovLine()  {
//		return MMovementLineConfirm.getFromMovLine(getCtx(), getM_MovementLine_ID(), get_TrxName());
//	}

	/** Cantidad previamente devuelta de la linea */
	public static BigDecimal getSumQtyDevol(final Properties ctx, final int movementLine) {
		BigDecimal qty = Env.ZERO;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT COALESCE(M_MovementLine.ConfirmedQty,0)        ")
		.append("      + COALESCE(M_MovementLine.ScrappedQty,0) AS conf ")
		.append("      , COALESCE(M_MovementLine.TargetQty,0)   AS surt ")
		.append(" FROM M_MovementLine ")
		.append(" INNER JOIN M_Movement m ON (m.M_Movement_ID = M_MovementLine.M_Movement_ID) ")
		.append("     AND m.IsActive  = 'Y'  ")
		.append("     AND m.IsDevol   = 'Y'  ")
		.append(" WHERE M_MovementLine.IsActive = 'Y' ")
		.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_MovementLine"))
		.append("     AND M_MovementLine.Ref_MovementLine_ID = ? ");
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, movementLine);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				qty = qty.add(rset.getBigDecimal("conf").compareTo(Env.ZERO)==0 
						? rset.getBigDecimal("surt")
						: rset.getBigDecimal("conf"));
				
			}

		} catch (SQLException e) {
			slog.log(Level.SEVERE, "getMovementLinePrep (" + sql + ")", e);
		} finally {
			DB.close(rset, pstmt);
		}
		return qty==null?Env.ZERO:qty;
	}

	// Devolución
	/**
	 * Si la unidad minima y de volumen son diferentes
	 * @return true: si la udm de la linea es la misma que la de volumen del producto
	 */
	public boolean isVolumeSelected() {
		boolean retValue = false;
		if (getProduct().getC_UOM_ID() != getProduct().getC_UOMVolume_ID() && getProduct().getC_UOMVolume_ID() == getOp_Uom()) {
			retValue = true;
		}
		return retValue;
	}
	
	/** Cantidad confirmada en minima */
	public BigDecimal getConfirmedQtyAddScrappedQty(){
		return this.getConfirmedQty().add(this.getScrappedQty());
	}

//	/** Valida el conteo de la cantidad que se ha devuelto de acuerdo a la unidad de medida */
//	public boolean validateQtyReturn(final BigDecimal qtyDevol){
//		final BigDecimal qty = qtyDevol.setScale(0,BigDecimal.ROUND_FLOOR);
//		BigDecimal qtyMin = qty;
//		
//		if(this.isVolumeSelected()){
//			// Convertir: convertProductFrom es empaque a minima 
//			qtyMin = MEXMEUOMConversion.convertProductFrom(getCtx(), //
//					this.getProduct().getM_Product_ID(), //
//					this.getProduct().getC_UOMVolume_ID(), //
//					qty, null, true);
//		}
//		
//		// Si la cantidad que se confirmada es menos a lo que se pretende devolver no permitir
//		return this.getConfirmedQtyAddScrappedQty().compareTo(qtyMin.add(getSumQtyDevol(getCtx(), getM_MovementLine_ID())))>=0;
//	}
	
	/** Posible cantidad a regresar, considerando que lo confirmado es lo maximo a devolver
	 * menos lo que se ha devuelto previamente, y que el almacen tenga existencia */
	public BigDecimal getAmountAvailableToReturn(){
		BigDecimal previus = MMovementLine.getSumQtyDevol(getCtx(), getM_MovementLine_ID());
		BigDecimal qtyDevol = Env.ZERO;
		BigDecimal qtyExist = Env.ZERO;
		
		// Existencias
		if(MEXMEMejoras.inventories(getCtx()) && MWarehouse.getFromLocator(getCtx(),this.getM_LocatorTo_ID(), null).isControlExistencias()){
			qtyExist =  MStorage.getQtyAvailable(0 
					, this.getM_LocatorTo_ID()
					, getM_Product_ID()
					, getM_AttributeSetInstance_ID()
					, null);
		} else {
			qtyExist = this.getConfirmedQtyAddScrappedQty().subtract(previus);
		}

		// Cant. a dev es la  Cantidad confirmada - Cantidad de devoluciones previas y que el almacen tenga existencia 
		if(qtyExist!=null && qtyExist.compareTo(Env.ZERO)>0){
			final BigDecimal qtySum = this.getConfirmedQtyAddScrappedQty().subtract(previus);
			if(qtyExist.compareTo(qtySum)<0){
				qtyDevol = qtyExist;
			} else {
				qtyDevol = qtySum;
			}
		}

		return qtyDevol==null?Env.ZERO:qtyDevol;
	}
		
	/** Actualizar la cantidad a devolver */
	public void updateQtyReturn(final BigDecimal qtyDevol){
		final BigDecimal qty = qtyDevol.setScale(0,BigDecimal.ROUND_FLOOR);

		if(this.isVolumeSelected()){
			// Convertir: convertProductFrom es empaque a minima 
			BigDecimal qtyMin = MEXMEUOMConversion.convertProductFrom(getCtx(), //
					this.getProduct().getM_Product_ID(), //
					this.getProduct().getC_UOMVolume_ID(), //
					qty, null, true);

			if(qtyMin==null)
				qtyMin = Env.ZERO;

			this.setReturnQty(qtyMin);
			this.setReturnQty_Vol(qty);

//			//TODO Quitar Antes de subir al SVN
//			final StringBuffer sql = new StringBuffer(" UPDATE M_MovementLine ")
//			.append(" SET ReturnQty = ? ")
//			.append("   , ReturnQty_Vol = ? ")
//			.append(" WHERE M_MovementLine_id = ? ");
//			int no = DB.executeUpdate(sql.toString(), new Object[]{qtyMin, qty, getM_MovementLine_ID()}, get_TrxName());
//			log.info("Update qty: " + no);

		} else {

			this.setReturnQty(qty);
			this.setReturnQty_Vol(Env.ZERO);

//			//TODO Quitar Antes de subir al SVN
//			final StringBuffer sql = new StringBuffer(" UPDATE M_MovementLine ")
//			.append(" SET ReturnQty = ? ")
//			.append("   , ReturnQty_Vol = ? ")
//			.append(" WHERE M_MovementLine_id = ? ");
//			int no = DB.executeUpdate(sql.toString(), new Object[]{qty, Env.ZERO, getM_MovementLine_ID()}, get_TrxName());
//			log.info("Update qty: " + no);
		}
	}

	/**
	 * Crea un objeto TransferBean a partir del actual (M_MovementLine)
	 * @return
	 */
	public TransferBean createTransferBean(final MMovementLineConfirm movLineConf ) {
		final TransferBean mTransferBean = new TransferBean();
		mTransferBean.setM_Product_ID(getM_Product_ID());
		mTransferBean.setM_AttributeSetInstance_ID(getM_AttributeSetInstance_ID());
		mTransferBean.setC_UOM_ID(getC_UOM_ID());
		mTransferBean.setC_UOMVolume_ID(isVolumeSelected()?getC_UOMVolume_ID():getC_UOM_ID());//Diseño original del OC
		mTransferBean.setQtyEntered(movLineConf.getTransferQty());
		mTransferBean.setQtyOrdered(movLineConf.getTransferQty());
		mTransferBean.setQtyEntered_Vol(movLineConf.getTransferQtyVol());
		mTransferBean.setQtyOrdered_Vol(movLineConf.getTransferQtyVol());
		mTransferBean.setDescription(getDescription());
		mTransferBean.setDatePromised(getMovement().getMovementDate());
		
		// Precio de los productos
		final MWarehouse warehouse = getMovement().getWarehouseCosigna();
		if (warehouse != null) {
			if (warehouse.getC_BPartner_ID() <= 0) {
				throw new MedsysException("No se tiene un socio de negocios relacionado para el almac\u00E9n en consigna " + warehouse.getName());
			}
			final MProductPrice mPrice = MProductPrice.getProductPrice(warehouse.getC_BPartner_ID(), 0, getM_Product_ID(), false);
			if (mPrice == null) {
				throw new MedsysException(Utilerias.getAppMsg(getCtx(), "error.factDirecta.findPrice", getProduct().getName()));
			} else if (isVolumeSelected() && BigDecimal.ZERO.compareTo(mPrice.getPriceList_Vol()) == 0) {
				throw new MedsysException(Utilerias.getAppMsg(getCtx(), "error.factDirecta.findPrice", getProduct().getName()) + " "
					+ Utilerias.getAppMsg(getCtx(), "msj.udm") + " " + MUOM.get(getCtx(), getProduct().getC_UOMVolume_ID()).getName());
			} else if (!isVolumeSelected() && BigDecimal.ZERO.compareTo(mPrice.getPriceList()) == 0) {
				throw new MedsysException(Utilerias.getAppMsg(getCtx(), "error.factDirecta.findPrice", getProduct().getName()) + " "
					+ Utilerias.getAppMsg(getCtx(), "msj.udm") + " " + getProduct().getUom().getName());
			}
			mTransferBean.setPriceActual_Vol(mPrice.getPriceList_Vol());
			mTransferBean.setPriceList_Vol(mPrice.getPriceList_Vol());
			mTransferBean.setPriceEntered_Vol(mPrice.getPriceList_Vol());
			mTransferBean.setPriceActual(mPrice.getPriceList());
			mTransferBean.setPriceList(mPrice.getPriceList());
			mTransferBean.setPriceEntered(mPrice.getPriceList());
		}
		return mTransferBean;
	}

	/** Cantidad a devolver deacuerdo a la udm del usuario */
	public BigDecimal getUserReturnQty() {
		if (isVolumeSelected()) {
			return getReturnQty_Vol();
		} else {
			return getReturnQty();
		}
	}
	
	/** Linea por productos */
	public static List<MovementLine> getLstProducts(final Properties ctx, final int M_Movement_ID, final int M_Product_ID) {
		List<MMovementLine> lst = new Query(ctx, MMovementLine.Table_Name, "M_Movement_ID=? AND p.M_Product_ID = ? ", null)//
		.setJoins(new StringBuilder(" INNER JOIN M_Product p ON p.M_Product_ID = M_MovementLine.M_Product_ID "))
		.setParameters(M_Movement_ID, M_Product_ID)//
		.list();
		
		List<MovementLine> lstTo = new ArrayList<MovementLine>();
		for (MMovementLine mLine: lst) {
			final MovementLine line = new MovementLine();
			line.setId(mLine.getM_MovementLine_ID());

			line.setConfirmedQty(mLine.getConfirmedQty());
			line.setConfirmedVolQty(mLine.getConfirmedQty_Vol());
			int ctaPacId = mLine.getEXME_CtaPac_ID();
			if(ctaPacId > 0){
				line.setCtaPac(new MEXMECtaPac(ctx, ctaPacId, null));
			}

			line.setLocatorFromId(mLine.getM_Locator_ID());
			line.setLocatorToId(mLine.getM_LocatorTo_ID());
			line.setLotId(mLine.getM_AttributeSetInstance_ID());
			line.setMovementId(M_Movement_ID);
			line.setOriginalQty(mLine.getOriginalQty());
			line.setOriginalVolQty(mLine.getOriginalQty_Vol());
			line.setProduct(new MProduct(ctx, mLine.getM_Product_ID(), null));
			line.setSelectedUomId(mLine.getOp_Uom());
			line.setTargetQty(mLine.getTargetQty());
			line.setTargetVolQty(mLine.getTargetQty_Vol());
			line.setUomId(mLine.getC_UOM_ID());
			line.setUomVolId(mLine.getC_UOMVolume_ID());
			line.setDescription(mLine.getDescription());
			line.setReturnQty(mLine.getReturnQty());
			line.setReturnVolQty(mLine.getReturnQty_Vol());
			line.setScrappedQty(mLine.getScrappedQty());
			line.setScrappedVolQty(mLine.getScrappedQty_Vol());
			lstTo.add(line);
		}
		return lstTo;
	}
	
//	/** Seleccionar la cantidad que sera transferida en la recepcion */
//	public BigDecimal getTransferQty(){
//		return getMovement().isDevol()
//				?(getConfirmedQtyAddScrappedQty()).negate()
//				:getConfirmedQtyAddScrappedQty();
//	}
//	
//	/** Seleccionar la cantidad que sera transferida en la recepcion */
//	public BigDecimal getTransferQtyVol(){
//		BigDecimal minQty =	Env.ZERO;
//		if(isVolumeSelected()){
//			minQty = getConfirmedQty_Vol().add(getScrappedQty_Vol());
//		} else {
//			minQty = getConfirmedQtyAddScrappedQty();
//		}
//		return getMovement().isDevol()?minQty.negate():minQty;
//	}
//	
	
	/**
	 * Guarda una línea en al base de datos, lógica utilizada en pedidos entre almacenes, solicitud de charola, pedido automático
	 * 
	 * @param movementId
	 *            Id del movimiento
	 * @param movementLine
	 *            Línea del movimiento
	 * @param prodId
	 *            Producto
	 * @param uomId
	 *            Unidad
	 * @param amount
	 *            Cantidad
	 * @param description
	 *            Descripción
	 * @param warehouseFrom
	 *            Almacen que surte
	 * @param locatorTo
	 *            Localizador destino
	 * @param trxName
	 *            Trx
	 * @return {@link ErrorList}
	 */
	public static ErrorList saveLine(int movementId, MovementLine movementLine, int prodId, int uomId, BigDecimal amount, String description, int warehouseFrom, int locatorTo, String trxName) {
		ErrorList errorList = new ErrorList();

		MMovement movement = new MMovement(Env.getCtx(), movementId, trxName);

		MMovementLine line = null;

		if (movementLine == null) {
			movementLine = new MovementLine();
		}
		
		line = new MMovementLine(Env.getCtx(), movementLine.getId(), null);

		movementLine.setMovementId(movementId);
		movementLine.setProduct(new MProduct(Env.getCtx(), prodId, null));
		movementLine.setSelectedUomId(uomId);
		movementLine.setOriginalQty(amount);
		movementLine.setDescription(description);

		if (movement.getEXME_CtaPac_ID() > 0) {
			movementLine.setCtaPac(new MEXMECtaPac(Env.getCtx(), movement.getEXME_CtaPac_ID(), null));
		}

		try {
			movementLine.setLocatorFromId(MLocator.getLocatorID(Env.getCtx(), warehouseFrom, null));
		} catch (final Exception e) {
			slog.log(Level.SEVERE, null, e);
			errorList.add(Error.CONFIGURATION_ERROR, Utilerias.getAppMsg(Env.getCtx(), "error.locatorID"));
		}
		
		if (errorList.isEmpty()) {

			movementLine.setLocatorToId(locatorTo);

			MovementLine.fillRequest(movementLine, line);

			errorList.getList().addAll(movementLine.validateRequest().getList());

			if (errorList.isEmpty()) {
				BigDecimal qtyAv = MStorage.getQtyAvailable(movement.getM_Warehouse_ID(), movementLine.getProduct().getM_Product_ID(), -1, null);

				boolean add = true;
				
				MEXMEProductoOrg prodOrg = movementLine.getProduct().getProdOrg();
				
				if (!(prodOrg != null && prodOrg.isConsigna())
						&& movementLine.getConfirmPanel() != null 
						&& movementLine.getOriginalQty().compareTo(qtyAv) >= 1) {
					StringBuilder str = new StringBuilder(Utilerias.getLabel("error.encCtaPac.noExistenProd", movementLine.getProduct().getName()));
					str.append("\n").append(Utilerias.getLabel("msj.continue"));

					add = movementLine.getConfirmPanel().confirm(str.toString());

					if (!add) {
						errorList.add(Error.VALIDATION_ERROR, Utilerias.getLabel("msj.seleccioneOtroProducto"));
					}
				}

				if (add) {
					if (line.save(trxName)) {
						line.set_TrxName(null);
					} else {
						String error = Utilerias.getLastErrorMessage();
						errorList.add(Error.EXCEPTION_ERROR, StringUtils.defaultIfEmpty(error, Utilerias.getAppMsg(Env.getCtx(), "error.guardar")));
					}
				}
			}
		}

		return errorList;
	}
	
	/**
	 * Obtener los registros hijos del movimiento entre consigna y propio 
	 * @param movementLineId Id de la linea del movimiento
	 * @return Puede devolver un null
	 */
	public static MovementLine getParentMovementConsigna(final Properties ctx, final int movementLineId, final int locator, final String trxName){
		MovementLine mMovLine = null;
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		
		.append(" SELECT sline.m_movement_id, ")
		.append("  sline.m_movementline_id, ")
		.append("  sline.confirmedqty, ")// Lo surtido despues de confirmar y se le resta scrappedQty para saber lo confirmado
		.append("  sline.confirmedqty_vol, ")
		.append("  sline.exme_ctapac_id, ")
		.append("  sline.m_locator_id, ")
		.append("  sline.m_locatorto_id, ")
		.append("  sline.m_attributesetinstance_id, ")
		.append("  sline.originalqty, ")// Lo solicitado
		.append("  sline.originalqty_vol, ")
		.append("  sline.m_product_id, ")
		.append("  sline.targetqty, ")// Lo surtido
		.append("  sline.targetqty_vol, ")	
		.append("  sline.c_uom_id, ")
		.append("  sline.c_uomvolume_id, ")
		.append("  sline.op_uom, ")
		.append("  sline.description, ")
		.append("  sline.returnQty, ")// La cantidad a devolver por transaccion (No es el acumulado de devolucion)
		.append("  sline.returnQty_Vol, ")
		.append("  sline.scrappedQty, ")// La diferencia entre lo surtido y confirmado por el usuario
		.append("  sline.scrappedQty_Vol ")
		
		.append(" FROM M_MovementLine line ") 
		.append(" INNER JOIN M_Movement      submov ON submov.Parent_Movement_ID = line.M_Movement_ID  ")
		.append("	                              AND  submov.IsActive = 'Y'                           ")
		.append(" INNER JOIN M_Warehouse       alm1 ON alm1.M_Warehouse_ID = submov.M_Warehouse_ID    AND alm1.Virtual = 'Y' ")
		.append(" INNER JOIN M_MovementLine   sline ON sline.M_Movement_ID = submov.M_Movement_ID    ")
		.append("	                              AND  sline.IsActive = 'Y' ")
		.append("	                              AND  sline.M_Product_ID              = line.M_Product_ID     ")
		.append(" 				                  AND  sline.M_AttributeSetInstance_ID = line.M_AttributeSetInstance_ID ")
		.append(" WHERE line.IsActive = 'Y'         ")
		.append(" AND   line.M_MovementLine_ID = ?      ");
		//.append(" AND  (line.ReturnQty > 0 OR line.ReturnQty_Vol > 0)     "); 

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final List<MovementLine> all = new ArrayList<MovementLine>();
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, movementLineId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
//				MovementLine mLine = new MovementLine();
				final MovementLine line = new MovementLine();
				line.setId(rs.getInt("m_movementline_id"));
				
				line.setConfirmedQty(rs.getBigDecimal("confirmedqty"));
				line.setConfirmedVolQty(rs.getBigDecimal("confirmedqty_vol"));
				int ctaPacId = rs.getInt("exme_ctapac_id");
				if(ctaPacId > 0){
					line.setCtaPac(new MEXMECtaPac(ctx, ctaPacId, null));
				}
				
				line.setLocatorFromId(rs.getInt("m_locator_id"));
				line.setLocatorToId(rs.getInt("m_locatorto_id"));
				line.setLotId(rs.getInt("m_attributesetinstance_id"));
				line.setMovementId(rs.getInt("m_movement_id"));
				line.setOriginalQty(rs.getBigDecimal("originalqty"));
				line.setOriginalVolQty(rs.getBigDecimal("originalqty_vol"));
				line.setProduct(new MProduct(ctx, rs.getInt("m_product_id"), null));
				line.setSelectedUomId(rs.getInt("op_uom"));
				line.setTargetQty(rs.getBigDecimal("targetqty"));
				line.setTargetVolQty(rs.getBigDecimal("targetqty_vol"));
				line.setUomId(rs.getInt("c_uom_id"));
				line.setUomVolId(rs.getInt("c_uomvolume_id"));
				line.setDescription(rs.getString("description"));
				line.setReturnQty(rs.getBigDecimal("returnQty"));
				line.setReturnVolQty(rs.getBigDecimal("returnQty_Vol"));
				line.setScrappedQty(rs.getBigDecimal("scrappedQty"));
				line.setScrappedVolQty(rs.getBigDecimal("scrappedQty_Vol"));
				all.add(line);
			}//FINWHILE
				
			if(all.size()==1){
				mMovLine = all.get(0);
				
			} else if(all.size()>1){
				// buscar por localizador
				for (final MovementLine bean: all) {
					if(bean.getLocatorFromId() == locator){//TODO Validar localizador
						mMovLine = bean;
						break;
					}
				}
				// si no se encuentra con el localizador del parametro toma el primero
				if(mMovLine == null){
					mMovLine = all.get(0);
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return mMovLine;
	}
	
	/** Hace una linea a partir de otra */
	public boolean copyReturnLines(final MovementLine bean, final MMovement newMovement){
		final MMovementLine newMovLine = new MMovementLine(getCtx(), 0, null);

		PO.copyValues(this, newMovLine, getAD_Client_ID(), getAD_Org_ID());
		newMovLine.set_ValueNoCheck(X_M_MovementLine.COLUMNNAME_M_MovementLine_ID, I_ZERO);
		
		newMovLine.setMovement(newMovement);

		newMovLine.setM_Movement_ID(newMovement.getM_Movement_ID());//Nuevo
		newMovLine.setAD_OrgTrx_ID(newMovement.getAD_OrgTrx_ID());
		newMovLine.setEXME_CtaPac_ID(newMovement.getEXME_CtaPac_ID());

		newMovLine.setMovementQty(bean.getReturnQty());
		newMovLine.setMovementQty_Vol(bean.getReturnVolQty());
		
		newMovLine.setTargetQty(bean.getReturnQty());
		newMovLine.setTargetQty_Vol(bean.getReturnVolQty());
		newMovLine.setOriginalQty(bean.getReturnQty());
		newMovLine.setOriginalQty_Vol(bean.getReturnVolQty());

		newMovLine.setConfirmedQty(BigDecimal.ZERO);
		newMovLine.setConfirmedQty_Vol(BigDecimal.ZERO);
		newMovLine.setReturnQty(BigDecimal.ZERO);
		newMovLine.setReturnQty_Vol(BigDecimal.ZERO);

		newMovLine.setProcessed(false);

		// Invertidos por que es una devolucion
		newMovLine.setM_LocatorTo_ID(bean.getLocatorFromId());//oldMov.getM_Locator_ID());
		newMovLine.setM_Locator_ID(bean.getLocatorToId());//oldMov.getM_LocatorTo_ID());

		// Referencias para posterior seguimiento en otros procesos
		newMovLine.setRef_MovementLine_ID(getM_MovementLine_ID());
		
		// Confirmar en automatico
		if (newMovement.isConsigVirtual()) {
			newMovLine.setConfirmedQty(bean.getReturnQty());
			newMovLine.setConfirmedQty_Vol(bean.getReturnVolQty());
		}
		return newMovLine.save(get_TrxName());
	}
	
	/**
	 * Campo usado en división de lotes para indicar si se actualizó o no un registro
	 */
	private boolean updated = false;

	/**
	 * Si fue o no actualizado, si no fue, se desactiva
	 * 
	 * @return
	 */
	public boolean isUpdated() {
		return updated;
	}

	/**
	 * Actualizado o no
	 * 
	 * @param updated
	 */
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	

	/**
	 * Obtener el costo que tenia al momento de completar la transacción
	 * para que al recontabilizar no tome el costo mas actual
	 * @param ctx: Contexto
	 * @param as: Esquema contable
	 * @param costo: Costo actual
	 * @param trxName: Nombre de transacción
	 * @return
	 */
	public BigDecimal getCostTransaction(final MAcctSchema as, final BigDecimal qty, final BigDecimal costs, final String trxName) {
		return MTransaction.getCostTransaction(as, qty, costs, getM_MovementLine_ID(), X_M_MovementLine.Table_Name, getProduct(), trxName);
	}
} // MMovementLine
