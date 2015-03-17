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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Physical Inventory Line Model
 *
 *  @author Jorg Janke
 *  @version $Id: MInventoryLine.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 */
public class MInventoryLine extends X_M_InventoryLine
{
	/** serialVersionUID */
	private static final long serialVersionUID = -7330773357992551370L;

	/**
	 * 	Get Inventory Line with parameters
	 *	@param inventory inventory
	 *	@param M_Locator_ID locator
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@return line or null
	 */
	public static MInventoryLine get (MInventory inventory,
		int M_Locator_ID, int M_Product_ID, int M_AttributeSetInstance_ID)
	{
		MInventoryLine retValue = null;
		final String sql = "SELECT * FROM M_InventoryLine "
			+ "WHERE M_Inventory_ID=? AND M_Locator_ID=?"
			+ " AND M_Product_ID=? AND M_AttributeSetInstance_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, inventory.get_TrxName());
			pstmt.setInt (1, inventory.getM_Inventory_ID());
			pstmt.setInt(2, M_Locator_ID);
			pstmt.setInt(3, M_Product_ID);
			pstmt.setInt(4, M_AttributeSetInstance_ID);
			rs = pstmt.executeQuery ();

			if (rs.next ()) {
				retValue = new MInventoryLine (inventory.getCtx(), rs, inventory.get_TrxName());
			}

		}
		catch (final Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;// Expert
		}

		return retValue;
	}	//	get
	
	/**
	 * Obtiene la(s) línea(s) del inventario relacionado al producto enviado
	 * 
	 * @param ctx
	 *            Contexto
	 * @param inventoryId
	 *            Inventario de parámetro
	 * @param productId
	 *            Producto a buscar
	 * @param trxName
	 *            Trx Name
	 * @return Listado de los resultados
	 */
	public static List<MInventoryLine> get(Properties ctx, int inventoryId, int productId, String trxName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  * ");
		sql.append("FROM ");
		sql.append("  m_inventoryline line ");
		sql.append("WHERE ");
		sql.append("  line.m_product_id = ? AND ");
		sql.append("  line.m_inventory_id = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", Table_Name, "line"));

		List<MInventoryLine> list = new ArrayList<MInventoryLine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, inventoryId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new MInventoryLine(ctx, rs, trxName));
			}
		} catch (Exception e) {
			s_log.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}


	/**	Logger				*/
	private static CLogger	s_log	= CLogger.getCLogger (MInventoryLine.class);


	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param M_InventoryLine_ID line
	 *	@param trxName transaction
	 */
	public MInventoryLine (Properties ctx, int M_InventoryLine_ID, String trxName)
	{
		super (ctx, M_InventoryLine_ID, trxName);
		if (M_InventoryLine_ID == 0)
		{
		//	setM_Inventory_ID (0);			//	Parent
		//	setM_InventoryLine_ID (0);		//	PK
		//	setM_Locator_ID (0);			//	FK
			setLine(0);
		//	setM_Product_ID (0);			//	FK
			setM_AttributeSetInstance_ID(0);	//	FK
			setInventoryType (INVENTORYTYPE_InventoryDifference);
			setQtyBook (Env.ZERO);
			setQtyCount (Env.ZERO);
			setProcessed(false);
		}
	}	//	MInventoryLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MInventoryLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MInventoryLine

	/**
	 * 	Detail Constructor.
	 * 	Locator/Product/AttributeSetInstance must be unique
	 *	@param inventory parent
	 *	@param M_Locator_ID locator
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID instance
	 *	@param QtyBook book value
	 *	@param QtyCount count value
	 */
	public MInventoryLine (MInventory inventory,
		int M_Locator_ID, int M_Product_ID, int M_AttributeSetInstance_ID,
		BigDecimal QtyBook, BigDecimal QtyCount)
	{
		this (inventory.getCtx(), 0, inventory.get_TrxName());
		if (inventory.get_ID() == 0) {
			throw new IllegalArgumentException("Header not saved");
		}
		m_parent = inventory;
		setM_Inventory_ID (inventory.getM_Inventory_ID());		//	Parent
		setClientOrg (inventory.getAD_Client_ID(), inventory.getAD_Org_ID());
		setM_Locator_ID (M_Locator_ID);		//	FK
		setM_Product_ID (M_Product_ID);		//	FK
		setM_AttributeSetInstance_ID (M_AttributeSetInstance_ID);

		final MProduct product = new MProduct(Env.getCtx(), M_Product_ID, null);
		if(product!= null){
			setC_UOM_ID(product.getC_UOM_ID());
			setC_UOMVolume_ID(product.getC_UOMVolume_ID());
		}
		//
		if (QtyBook != null) {
			setQtyBook (QtyBook);
		}
		if (QtyCount != null && QtyCount.signum() != 0) {
			setQtyCount (QtyCount);
		}
		m_isManualEntry = false;
	}	//	MInventoryLine

	/**
	 * 	Detail Constructor.
	 * 	Locator/Product/AttributeSetInstance must be unique
	 *	@param inventory parent
	 *	@param M_Locator_ID locator
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID instance
	 *	@param QtyBook book value (QtyOnHand)
	 *	@param QtyCount count value (QtyOnHand)
	 * 	@param QtyOnHandVol count value in pack (QtyOnHand)
	 * 	@param QtyOnHandUom count value in minium(QtyOnHand_uom)
	 */
	public MInventoryLine (MInventory inventory,
		int M_Locator_ID, int M_Product_ID, int M_AttributeSetInstance_ID,
		BigDecimal QtyBook, BigDecimal QtyCount, BigDecimal QtyOnHandVol, BigDecimal QtyOnHandUom)
	{
		this (inventory.getCtx(), 0, inventory.get_TrxName());
		if (inventory.get_ID() == 0) {
			throw new IllegalArgumentException("Header not saved");
		}
		m_parent = inventory;
		setM_Inventory_ID (inventory.getM_Inventory_ID());		//	Parent
		setClientOrg (inventory.getAD_Client_ID(), inventory.getAD_Org_ID());
		setM_Locator_ID (M_Locator_ID);		//	FK
		setM_Product_ID (M_Product_ID);		//	FK
		setM_AttributeSetInstance_ID (M_AttributeSetInstance_ID);
		final MProduct product = new MProduct(getCtx(), M_Product_ID, null);
		setC_UOM_ID(product.getC_UOM_ID());
		setC_UOMVolume_ID(product.getC_UOMVolume_ID());
		//
		if (QtyBook != null){
			setQtyBook (QtyBook);
			setQtyBook_Vol(QtyOnHandVol);
			setQtyBook_Uom(QtyOnHandUom);
		}
		if (QtyCount != null && QtyCount.signum() != 0){
			setQtyCount (QtyCount);
			setQtyCount_Vol(QtyOnHandVol);
			setQtyCount_Uom(QtyOnHandUom);
		}
		m_isManualEntry = false;
	}	//	MInventoryLine

	/** Manually created				*/
	private boolean 	m_isManualEntry = true;
	/** Parent							*/
	private MInventory 	m_parent = null;
	/** Product							*/
	private MProduct 	m_product = null;

	/**
	 * 	Get Qty Book
	 *	@return Qty Book
	 */
	@Override
	public BigDecimal getQtyBook ()
	{
		BigDecimal bd = super.getQtyBook ();
		if (bd == null) {
			bd = Env.ZERO;
		}
		return bd;
	}	//	getQtyBook

	/**
	 * 	Get Qty Count
	 *	@return Qty Count
	 */
	@Override
	public BigDecimal getQtyCount ()
	{
		BigDecimal bd = super.getQtyCount();
		if (bd == null) {
			bd = Env.ZERO;
		}
		return bd;
	}	//	getQtyBook

	/**
	 * 	Get Product
	 *	@return product or null if not defined
	 */
	public MProduct getProduct()
	{
		final int M_Product_ID = getM_Product_ID();
		if (M_Product_ID == 0) {
			return null;
		}
		if (m_product != null && m_product.getM_Product_ID() != M_Product_ID)
		 {
			m_product = null;	//	reset
		}
		if (m_product == null) {
			m_product = MProduct.get(getCtx(), M_Product_ID);
		}
		return m_product;
	}	//	getProduct

	/**
	 * 	Set Count Qty - enforce UOM
	 *	@param QtyCount qty
	 */
	@Override
	public void setQtyCount (BigDecimal QtyCount)
	{
		if (QtyCount != null)
		{
			final MProduct product = getProduct();
			if (product != null)
			{
				final int precision = product.getUOMPrecision();
				QtyCount = QtyCount.setScale(precision, BigDecimal.ROUND_HALF_UP);
			}
		}
		super.setQtyCount(QtyCount);
	}	//	setQtyCount

	/**
	 * 	Set Internal Use Qty - enforce UOM
	 *	@param QtyInternalUse qty
	 */
	@Override
	public void setQtyInternalUse (BigDecimal QtyInternalUse)
	{
		if (QtyInternalUse != null)
		{
			final MProduct product = getProduct();
			if (product != null)
			{
				final int precision = product.getUOMPrecision();
				QtyInternalUse = QtyInternalUse.setScale(precision, BigDecimal.ROUND_HALF_UP);
			}
		}
		super.setQtyInternalUse(QtyInternalUse);
	}	//	setQtyInternalUse


	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		final String desc = getDescription();
		if (desc == null) {
			setDescription(description);
		} else {
			setDescription(desc + " | " + description);
		}
	}	//	addDescription

	/**
	 * 	Get Parent
	 *	@param parent parent
	 */
	protected void setParent(MInventory parent)
	{
		m_parent = parent;
	}	//	setParent

	/**
	 * 	Get Parent
	 *	@return parent
	 */
	private MInventory getParent()
	{
		if (m_parent == null) {
			m_parent = new MInventory (getCtx(), getM_Inventory_ID(), get_TrxName());
		}
		return m_parent;
	}	//	getParent

	/**
	 * 	String Representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		final StringBuffer sb = new StringBuffer ("MInventoryLine[")
			.append (get_ID())
			.append("-M_Product_ID=").append (getM_Product_ID())
			.append(",QtyCount=").append(getQtyCount())
			.append(",QtyInternalUse=").append(getQtyInternalUse())
			.append(",QtyBook=").append(getQtyBook())
			.append(",M_AttributeSetInstance_ID=").append(getM_AttributeSetInstance_ID())
			.append("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if can be saved
	 */
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		final MProduct product = MProduct.get(getCtx(), getM_Product_ID());
		if (newRecord && m_isManualEntry)
		{
			//	Product requires ASI
			if (getM_AttributeSetInstance_ID() == 0)
			{

				if (product.getM_AttributeSet_ID() != 0)
				{
					final MAttributeSet mas = MAttributeSet.get(getCtx(), product.getM_AttributeSet_ID());
					if (mas.isInstanceAttribute()
						&& (mas.isMandatory() || mas.isMandatoryAlways()))
					{
						log.saveError("FillMandatory", Msg.getElement(getCtx(), "M_AttributeSetInstance_ID"));
						return false;
					}
				}
			}	//	No ASI
		}	//	new or manual

		//	Set Line No
		if (getLine() == 0)
		{
			final String sql = "SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM M_InventoryLine WHERE M_Inventory_ID=?";
			final int ii = DB.getSQLValue (get_TrxName(), sql, getM_Inventory_ID());
			setLine (ii);
		}

		// Enforce Qty UOM
		//if(getQtyCount().compareTo(Env.ZERO)==0){// Se agrego por que cuando se pasa la info no respeta el valor que se le esta enviando
			if ((newRecord || is_ValueChanged(I_M_InventoryLine.COLUMNNAME_QtyCount_Vol))
//					|| (!newRecord && is_ValueChanged("QtyCount_UOM"))){
					|| (!newRecord && is_ValueChanged(I_M_InventoryLine.COLUMNNAME_QtyCount_Uom))){

				// Sumar ambas cantidades para colocarlas en el campo de cantidad contada.
				// Convertir la cantidad de vol, capturada a la minima
				final BigDecimal countVol =
						MEXMEUOMConversion.convertProductFrom(
								Env.getCtx(),
								product.getM_Product_ID(),
								product.getC_UOMVolume_ID(),
								getQtyCount_Vol(), //getQtyCount_Vol() se captura en la opcion inventario fisico
								null
								);
				// Si no hay conversion
				if(countVol == null){
					//Si no hay cant de empaque colocar la minima (getQtyCount() ya no se captura en la opc. inventario fisico)
					setQtyCount(getQtyCount_Uom());//getQtyCount_Uom() se captura en la opcion inventario fisico
				} else {// getQtyInternalUse_Uom() no se captura en inv. fisico por lo que es cero pero Si se utiliza internaluse esta bien que mas adelante se cambie la cantidad ?
					final BigDecimal total = getQtyInternalUse_Uom().add(countVol);// Internal use mas la cantidad de vol convertida a minima
					setQtyCount(getQtyCount_Uom().add(total));// Se suma cant. minima y de cant. de volumen para que sea lo contado que se tomara
				}
			}
		//}

		//
//		if(getQtyInternalUse().compareTo(Env.ZERO)==0){// Se agrego por que cuando se pasa la info no respeta el valor que se le esta enviando
			if (newRecord 
					|| is_ValueChanged(I_M_InventoryLine.COLUMNNAME_QtyInternalUse_Uom) 
					|| is_ValueChanged(I_M_InventoryLine.COLUMNNAME_QtyInternalUse_Vol)){
				final BigDecimal internalVol = MEXMEUOMConversion.convertProductFrom(Env.getCtx(), product.getM_Product_ID(), product.getC_UOMVolume_ID(),
						getQtyInternalUse_Vol(), null);
				if(internalVol == null){
					setQtyInternalUse(getQtyInternalUse_Uom());
				}else{
					//Sumar ambas cantidades para colocarlas en el campo de uso interno.
					final BigDecimal total = getQtyInternalUse_Uom().add(internalVol);
					setQtyInternalUse(total);
				}
			}
//		}

		//	InternalUse Inventory
		if (getQtyInternalUse().signum() != 0)
		{
			if (!INVENTORYTYPE_ChargeAccount.equals(getInventoryType())) {
				setInventoryType(INVENTORYTYPE_ChargeAccount);
			}

			//Obtenemos el cargo default
			if(getC_Charge_ID() == 0 && MEXMEMejoras.get(getCtx()) != null){
				setC_Charge_ID(MEXMEMejoras.get(getCtx()).getC_Charge_ID());
			}
			
			if (getC_Charge_ID() == 0){
				log.saveError("EXME_SalidaGasto_ID", "InternalUseNeedsCharge");
				return false;
			}

			/** Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
			if (processWF) { //Valida solo la cantidad cuando proviene de confirmacion
				if (!validateQtyInternalUse(0, null)) {
					return false;
				}
			} else if (!validarExistencias(true)) {
				return false;
			}

//			//Alejandro.- Se agrego validacion para que tome en cuenta la cantidad disponible y no la canitdad a la mano
//			MLocator loc = MLocator.get(getCtx(), getM_Locator_ID());
//			int M_Warehouse_ID = loc.getM_Warehouse_ID();
//
//			BigDecimal QtyAvailable = MStorage.getQtyAvailable(M_Warehouse_ID, getM_Product_ID(), getM_AttributeSetInstance_ID(), get_TrxName());
//			if(QtyAvailable==null){
//				QtyAvailable = Env.ZERO;
//			}
//			if(getQtyInternalUse().compareTo(QtyAvailable)>0){
//				log.saveError("SaveError", "La cantidad de uso interno es mayor que " +
//						"la cantidad disponible para el almacen (" + QtyAvailable + ")");
//				return false;
//			}
//			if(getQtyInternalUse().compareTo(Env.ZERO)<0) {
//				log.saveError("SaveError", "La cantidad de uso interno es menor que " +
//						"la cantidad disponible para el almacen (" + QtyAvailable + ")");
//				return false;
//			}
//			//expert : gisela lee : validar contra el inventario
		}
		else if (INVENTORYTYPE_ChargeAccount.equals(getInventoryType()))
		{
			if (getC_Charge_ID() == 0)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "C_Charge_ID"));
				return false;
			}
		}
		else if (getC_Charge_ID() != 0){
			setC_Charge_ID(0);//FIXME: VALIDAR O CAMBIAR TIPO ???
		}
		//	Set AD_Org to parent if not charge
		if (getC_Charge_ID() == 0){
			setAD_Org_ID(getParent().getAD_Org_ID());
		}

		//lhernandez, validar que si el producto tiene lotes se seleccionen.
//		final MEXMEMejoras m =  MEXMEMejoras.get(getCtx(), null);
//		if(product.isLot() && (m.isModificaLotes() || m.isAgregaLote()) && getM_AttributeSetInstance_ID() == 0 && getLotInfo() == null){
//			log.saveError("Error", Msg.parseTranslation(getCtx(), "@MsjSelectLot@"));
//			return false;
//		}

		// Clase de producto para el post
		if (getEXME_ProductClassFact_ID()<=0 && getM_Product_ID()>0){
			setEXME_ProductClassFact_ID(MEXMEProductClassFact.get(getCtx(), getM_Product_ID(), get_TrxName()));
		}

		return true;
	}	//	beforeSave

	/**
	 * Valida las existencias
	 * @return true: si existen cantidad suficiente
	 */
	public boolean validarExistencias(boolean isReplenishR){
		/**Cambios generados por confirmacion de productos con diferencia que genera una salida al gasto */
		//Alejandro.- Se agrego validacion para que tome en cuenta la cantidad disponible y no la canitdad a la mano
		final MLocator loc = MLocator.get(getCtx(), getM_Locator_ID());
		final int M_Warehouse_ID = loc.getM_Warehouse_ID();

		BigDecimal QtyAvailable = MStorage.getQtyAvailable(M_Warehouse_ID, getM_Product_ID(), getM_AttributeSetInstance_ID(), get_TrxName());
		if(QtyAvailable==null){
			QtyAvailable = Env.ZERO;
		}
		//Si se carga al almacen que confirma, brincar la validacion de comparar uso interno y
		if(getQtyInternalUse().compareTo(QtyAvailable)>0){
			final MConfigEC configEC = MConfigEC.get(Env.getCtx(), null);
			if(configEC.isCargaDiferAlm() || isReplenishR){
				log.saveError("SaveError", "La cantidad de uso interno es mayor que " +
						"la cantidad disponible para el almacen (" + QtyAvailable + ")");
				return false;
			}
		}
		return validateQtyInternalUse(M_Warehouse_ID, QtyAvailable);
//		if(getQtyInternalUse().compareTo(Env.ZERO)<0) {
//			log.saveError("SaveError", "La cantidad de uso interno es menor que " +
//					"la cantidad disponible para el almacen (" + QtyAvailable + ")");
//			return false;
//		}
		//expert : gisela lee : validar contra el inventario
//		return true;
	}
	
	/**
	 * valida que la Cantidad de uso Interno NO sea negativa
	 * @param mWarehouse_ID almacen para calcular la catidad disponible
	 * @param qtyAvailable (usada para el mensaje
	 * @return true si es valida
	 */
	public boolean validateQtyInternalUse(int mWarehouse_ID, BigDecimal qtyAvailable) {
		if (getQtyInternalUse().compareTo(Env.ZERO) < 0) {
			if (qtyAvailable == null) {
				if (mWarehouse_ID <= 0) {
					final MLocator loc = MLocator.get(getCtx(), getM_Locator_ID());
					mWarehouse_ID = loc.getM_Warehouse_ID();
				}
				qtyAvailable = MStorage.getQtyAvailable(mWarehouse_ID, getM_Product_ID(), getM_AttributeSetInstance_ID(), get_TrxName());
			}
			log.saveError("SaveError", "La cantidad de uso interno es menor que la cantidad disponible para el almacen (" + qtyAvailable + ")");
			return false;
		}
		return true;
	}

	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return true
	 */
	@Override
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//	Create MA
		if (newRecord && success
			&& m_isManualEntry && getM_AttributeSetInstance_ID() == 0) {
			createMA();
		}
		return true;
	}	//	afterSave

	/**
	 * 	Create Material Allocations for new Instances
	 */
	private void createMA()
	{
		final MStorage[] storages = MStorage.getAll(getCtx(), getM_Product_ID(),
			getM_Locator_ID(), get_TrxName());
		boolean allZeroASI = true;
		for (final MStorage storage : storages) {
			if (storage.getM_AttributeSetInstance_ID() != 0)
			{
				allZeroASI = false;
				break;
			}
		}
		if (allZeroASI) {
			return;
		}

		MInventoryLineMA ma = null;
		BigDecimal sum = Env.ZERO;
		for (final MStorage storage : storages) {
			if (storage.getQtyOnHand().signum() == 0) {
				continue;
			}
			if (ma != null
				&& ma.getM_AttributeSetInstance_ID() == storage.getM_AttributeSetInstance_ID()) {
				ma.setMovementQty(ma.getMovementQty().add(storage.getQtyOnHand()));
			} else {
				ma = new MInventoryLineMA (this,
					storage.getM_AttributeSetInstance_ID(), storage.getQtyOnHand());
			}
			if (!ma.save()) {
				;
			}
			sum = sum.add(storage.getQtyOnHand());
		}
		if (sum.compareTo(getQtyBook()) != 0)
		{
			log.warning("QtyBook=" + getQtyBook() + " corrected to Sum of MA=" + sum);
			setQtyBook(sum);
		}
	}	//	createMA

	/**
	 * Coloca las cant(contada y libro) cuando no hay cant disponible suficiente al realizar fact directa
	 * @param qtyUser cantidad colocada por el usuario
	 * @param uomUser unidad de medida seleccionada
	 * @param qtyAvalible cant disponible
	 */
	public void setQtysBookAndCount(final BigDecimal qtyUser, final int uomUser, final BigDecimal qtyAvalible) {
		setC_UOM_ID(getProduct().getC_UOM_ID());
		setC_UOMVolume_ID(getProduct().getC_UOMVolume_ID());
		BigDecimal calculateQtyUser;

		if (uomUser == getProduct().getC_UOMVolume_ID() && getProduct().getC_UOMVolume_ID() != getProduct().getC_UOM_ID()) {
			BigDecimal qtyAvalibleVol = BigDecimal.ZERO;
			
			if (qtyAvalible.compareTo(BigDecimal.ZERO) > 0) {
				qtyAvalibleVol= MEXMEUOMConversion.convertProductTo(Env.getCtx(),
					getProduct().getM_Product_ID(), getProduct().getC_UOMVolume_ID(), qtyAvalible,
					null, true);
			}
			
			BigDecimal qtyBookvol = BigDecimal.ZERO;
			
			if (qtyAvalible.compareTo(BigDecimal.ZERO) > 0) {
				qtyBookvol = MEXMEUOMConversion.convertProductTo(Env.getCtx(),
					getProduct().getM_Product_ID(), getProduct().getC_UOMVolume_ID(), getQtyBook(),
					null, true);
			}
			
			//Se resta la cantidad disponible a la que se ingreso para dar entrada al inventario
			calculateQtyUser = qtyUser.subtract(qtyAvalibleVol);
			setQtyBook_Vol(qtyBookvol);
			setQtyCount_Vol(getQtyBook_Vol().add(calculateQtyUser));

		} else {
			setQtyBook_Uom(getQtyBook());
			//Se resta la cantidad disponible a la que se ingreso para dar entrada al inventario
			calculateQtyUser = qtyUser.subtract(qtyAvalible);
			setQtyCount_Uom(getQtyBook_Uom().add(calculateQtyUser));

		}
	}

	private boolean processWF = false;
	public void setProcessWF(boolean process) {
		processWF = process;
	}
	
	/** Campos para mantenimiento de inventario **/
	private MLocator locator;

	public MLocator getLocator() {
		if (locator == null) {
			locator = new MLocator(getCtx(), getM_Locator_ID(), null);
		}
		return locator;
	}
	
	private MAttributeSetInstance attributeSetInstance;

	public MAttributeSetInstance getAttributeSetInstance() {
		if (attributeSetInstance == null) {
			attributeSetInstance = new MAttributeSetInstance(getCtx(), getM_AttributeSetInstance_ID(), null);
		}
		return attributeSetInstance;
	}
	
	private MUOM uom;

	public MUOM getUom() {
		if (uom == null) {
			uom = new MUOM(getCtx(), getC_UOM_ID(), null);
		}
		return uom;
	}
	
	private MUOM uomVol;

	public MUOM getUomVol() {
		if (uomVol == null) {
			uomVol = new MUOM(getCtx(), getC_UOMVolume_ID(), null);
		}
		return uomVol;
	}
	
	private MWarehouse warehouse;
	
	public MWarehouse getWarehouse() {
		if (warehouse == null) {
			MLocator locator = getLocator();
			if (locator != null) {
				warehouse = new MWarehouse(getCtx(), locator.getM_Warehouse_ID(), null);
			}
		}
		return warehouse;
	}
	
	/**
	 * Regresa los cantidades por minima o por volumen.
	 */
	public BigDecimal getQtyInternal() {
		if (getQtyInternalUse_Vol().compareTo(BigDecimal.ZERO) == 0) {
			return getQtyInternalUse();
		} else {
			return getQtyInternalUse_Vol();
		}
	}
	
	private MStorage storage;
	
	public MStorage getStorage() {
		if (storage == null) {
			storage = MStorage.get(getCtx(), getM_Locator_ID(), getM_Product_ID(), getM_AttributeSetInstance_ID(), null);
		}
		return storage;
	}
	
	/** FIN Campos para mantenimiento de inventario **/

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
		return MTransaction.getCostTransaction(as, qty, costs, getM_InventoryLine_ID(), X_M_InventoryLine.Table_Name, getProduct(), trxName);
	}
}	//	MInventoryLine
