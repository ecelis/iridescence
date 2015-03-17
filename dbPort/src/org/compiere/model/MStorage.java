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
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.compiere.model.bean.InventoryCost;
import org.compiere.model.bean.StorageInfo;
import org.compiere.model.bpm.BeanView;
import org.compiere.util.CLogger;
import org.compiere.util.Constantes;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

import com.ecaresoft.util.Error;
import com.ecaresoft.util.ErrorList;

/**
 * Inventory Storage Model
 * 
 * @author Jorg Janke
 * @version $Id: MStorage.java,v 1.1 2006/08/31 20:44:14 mrojas Exp $
 */
public class MStorage extends X_M_Storage {
	/** serialVersionUID */
	private static final long serialVersionUID = -3050951273798538249L;
	/** Log */
	private static CLogger slog = CLogger.getCLogger(MStorage.class);
	/** Warehouse */
	private int mMWarehouseID = 0;

	/**
	 * Get Storage Info
	 * 
	 * @param ctx
	 *            context
	 * @param mLocatorID
	 *            locator
	 * @param mProductID
	 *            product
	 * @param mAttSetInstID
	 *            instance
	 * @param trxName
	 *            transaction
	 * @return existing or null
	 */
	public static MStorage get(final Properties ctx, final int mLocatorID,
			final int mProductID, final int mAttSetInstID,
			final String trxName) {
		MStorage retValue = null;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT * FROM M_Storage ")
		.append(" WHERE M_Locator_ID=? AND M_Product_ID=? AND ");
		if (mAttSetInstID == 0) {
			sql.append("(M_AttributeSetInstance_ID=? OR M_AttributeSetInstance_ID IS NULL)");
		} else {
			sql.append("M_AttributeSetInstance_ID=?");
		}
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mLocatorID);
			pstmt.setInt(2, mProductID);
			pstmt.setInt(3, mAttSetInstID);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				retValue = new MStorage(ctx, rset, trxName);
			}

		} catch (SQLException ex) {
			slog.log(Level.SEVERE, sql.toString(), ex);
		} finally {
			DB.close(rset, pstmt);
		}

		if (retValue == null) {
			slog.fine("Not Found - M_Locator_ID=" + mLocatorID
					+ ", M_Product_ID=" + mProductID
					+ ", M_AttributeSetInstance_ID="
					+ mAttSetInstID);
		} else {
			slog.fine("M_Locator_ID=" + mLocatorID + ", M_Product_ID="
					+ mProductID + ", M_AttributeSetInstance_ID="
					+ mAttSetInstID);
		}
		return retValue;
	} // get

	/**
	 * Get all Storages for Product with ASI
	 * 
	 * @param ctx
	 *            context
	 * @param mProductID
	 *            product
	 * @param mLocatorID
	 *            locator
	 * @param FiFo
	 *            first in-first-out
	 * @param trxName
	 *            transaction
	 * @return existing or null
	 */
	public static MStorage[] getAllWithASI(final Properties ctx,
			final int mProductID, final int mLocatorID, final boolean FiFo,
			final String trxName) {
		final ArrayList<MStorage> list = new ArrayList<MStorage>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT * FROM M_Storage ")
		.append(" WHERE M_Product_ID=? AND M_Locator_ID=? ")
		.append(" AND M_AttributeSetInstance_ID > 0 ")
		.append(" AND QtyOnHand > 0 ")
		.append(" ORDER BY M_AttributeSetInstance_ID");
		if (!FiFo) {
			sql.append(" DESC");
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mProductID);
			pstmt.setInt(2, mLocatorID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MStorage(ctx, rs, trxName));
			}

		} catch (SQLException ex) {
			slog.log(Level.SEVERE, sql.toString(), ex);
		} finally {
			DB.close(rs, pstmt);
		}

		final MStorage[] retValue = new MStorage[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getAllWithASI

	/**
	 * Get all Storages for Product
	 * 
	 * @param ctx
	 *            context
	 * @param mProductID
	 *            product
	 * @param mLocatorID
	 *            locator
	 * @param trxName
	 *            transaction
	 * @return existing or null
	 */
	public static MStorage[] getAll(final Properties ctx,
			final int mProductID, final int mLocatorID, final String trxName) {
		final ArrayList<MStorage> list = new ArrayList<MStorage>();
		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT * FROM M_Storage ")
		.append(" WHERE M_Product_ID=? AND M_Locator_ID=?")
		.append(" AND QtyOnHand <> 0 " + "ORDER BY M_AttributeSetInstance_ID");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mProductID);
			pstmt.setInt(2, mLocatorID);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new MStorage(ctx, rset, trxName));
			}
		} catch (SQLException ex) {
			slog.log(Level.SEVERE, sql.toString(), ex);
		} finally {
			DB.close(rset, pstmt);
		}

		final MStorage[] retValue = new MStorage[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getAll

	/**
	 * Get Storage Info for Product across warehouses
	 * 
	 * @param ctx
	 *            context
	 * @param mProductID
	 *            product
	 * @param trxName
	 *            transaction
	 * @return existing or null
	 */
	public static MStorage[] getOfProduct(final Properties ctx,
			final int mProductID, final String trxName) {
		final ArrayList<MStorage> list = new ArrayList<MStorage>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM M_Storage WHERE M_Product_ID = ? ");
		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name));
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mProductID);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new MStorage(ctx, rset, trxName));
			}

		} catch (SQLException ex) {
			slog.log(Level.SEVERE, sql.toString(), ex);
		} finally {
			DB.close(rset, pstmt);
		}

		final MStorage[] retValue = new MStorage[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getOfProduct

	/**
	 * Get product's storage info for Warehouse
	 * 
	 * @param ctx
	 *            context
	 * @param mWarehouseID
	 * @param mProductID
	 *            product
	 * @param mAttSetInstID
	 *            instance
	 * @param mAttributeSetID
	 *            attribute set
	 * @param allAttributeInstances
	 *            if true, all attribute set instances
	 * @param minGuaranteeDate
	 *            optional minimum guarantee date if all attribute instances
	 * @param FiFo
	 *            first in-first-out
	 * @param trxName
	 *            transaction
	 * @return existing - ordered by location priority (desc) and/or guarantee
	 *         date
	 */
	public static MStorage[] getWarehouse(final Properties ctx,
			final int mWarehouseID, final int mProductID,
			final int mAttSetInstID, final int mAttributeSetID,
			boolean allAttributeInstances, final Timestamp minGuaranteeDate,
			final boolean FiFo, final String trxName) {
		if (mWarehouseID == 0 || mProductID == 0) {
			return new MStorage[0];
		}
		if (mAttributeSetID == 0) {
			allAttributeInstances = true;
		} else {
			final MAttributeSet mas = MAttributeSet.get(ctx, mAttributeSetID);
			if (!mas.isInstanceAttribute()) {
				allAttributeInstances = true;
			}
		}

		final ArrayList<MStorage> list = new ArrayList<MStorage>();
		final StringBuilder sql = new StringBuilder();

		if (allAttributeInstances) { // Specific Attribute Set Instance
			sql.append(
					" SELECT s.M_Product_ID,s.M_Locator_ID,s.M_AttributeSetInstance_ID, ")
					.append(" s.AD_Client_ID,s.AD_Org_ID,s.IsActive,s.Created,s.CreatedBy,s.Updated,s.UpdatedBy, ")
					.append(" s.QtyOnHand,s.QtyReserved,s.QtyOrdered,s.DateLastInventory ")
					.append(" FROM M_Storage s ")
					.append(" INNER JOIN M_Locator l ON (l.M_Locator_ID=s.M_Locator_ID) ")
					.append(" LEFT OUTER JOIN M_AttributeSetInstance asi ON (s.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID) ")
					.append(" WHERE l.M_Warehouse_ID=? ")
					.append(" AND s.M_Product_ID=? ");

			if (minGuaranteeDate != null) {
				sql.append(
						"AND (asi.GuaranteeDate IS NULL OR asi.GuaranteeDate>?) ")
						.append("ORDER BY asi.GuaranteeDate, M_AttributeSetInstance_ID");
				if (!FiFo) {
					sql.append(" DESC ");
				}
				sql.append(", l.PriorityNo DESC, s.QtyOnHand DESC ");
			} else {
				sql.append(" ORDER BY l.PriorityNo DESC, l.M_Locator_ID, s.M_AttributeSetInstance_ID ");
				if (!FiFo) {
					sql.append(" DESC ");
				}
				sql.append(", s.QtyOnHand DESC");
			}
			
		} else { // All Attribute Set Instances
			sql.append(
					" SELECT s.M_Product_ID,s.M_Locator_ID,s.M_AttributeSetInstance_ID, ")
					.append(" s.AD_Client_ID,s.AD_Org_ID,s.IsActive,s.Created,s.CreatedBy,s.Updated,s.UpdatedBy, ")
					.append(" s.QtyOnHand,s.QtyReserved,s.QtyOrdered,s.DateLastInventory ")
					.append(" FROM M_Storage s ")
					.append(" INNER JOIN M_Locator l ON (l.M_Locator_ID=s.M_Locator_ID) ")
					.append(" WHERE l.M_Warehouse_ID=? ")
					.append(" AND s.M_Product_ID=? ")
					.append(" AND COALESCE(s.M_AttributeSetInstance_ID,0)=? ")
					.append(" ORDER BY l.PriorityNo DESC, M_AttributeSetInstance_ID ");

			if (!FiFo) {
				sql.append(" DESC ");
			}
		}
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mWarehouseID);
			pstmt.setInt(2, mProductID);
			if (!allAttributeInstances) {
				pstmt.setInt(3, mAttSetInstID);
			} else if (minGuaranteeDate != null) {
				pstmt.setTimestamp(3, minGuaranteeDate);
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new MStorage(ctx, rset, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		final MStorage[] retValue = new MStorage[list.size()];
		list.toArray(retValue);
		return retValue;
	} // getWarehouse

	/**
	 * Create or Get Storage Info
	 * 
	 * @param ctx
	 *            context
	 * @param mLocatorID
	 *            locator
	 * @param mProductID
	 *            product
	 * @param mAttribSetInstID
	 *            instance
	 * @param trxName
	 *            transaction
	 * @return existing/new or null
	 */
	public static MStorage getCreate(final Properties ctx,
			final int mLocatorID, final int mProductID,
			final int mAttribSetInstID, final String trxName) {
		if (mLocatorID == 0) {
			throw new IllegalArgumentException("M_Locator_ID=0");
		}
		if (mProductID == 0) {
			throw new IllegalArgumentException("M_Product_ID=0");
		}

		MStorage retValue = get(ctx, mLocatorID, mProductID,
				mAttribSetInstID, trxName);
		if (retValue != null) {
			return retValue;
		}

		// Insert row based on locator
		final MLocator locator = new MLocator(ctx, mLocatorID, trxName);
		if (locator.get_ID() != mLocatorID) {
			throw new IllegalArgumentException("Not found M_Locator_ID="
					+ mLocatorID);
		}
		//
		retValue = new MStorage(locator, mProductID,
				mAttribSetInstID);
		retValue.save(trxName);
		slog.fine("New " + retValue);
		return retValue;
	} // getCreate

	/**
	 * Update Storage Info add. Called from MProjectIssue
	 * 
	 * @param ctx
	 *            context
	 * @param mWarehouseID
	 *            warehouse
	 * @param mLocatorID
	 *            locator
	 * @param mProductID
	 *            product
	 * @param mAttSetInstID
	 *            AS Instance
	 * @param reservationASIID
	 *            reservation AS Instance
	 * @param diffQtyOnHand
	 *            add on hand
	 * @param diffQtyReserved
	 *            add reserved
	 * @param diffQtyOrdered
	 *            add order
	 * @param trxName
	 *            transaction
	 * @return true if updated
	 */
	public static boolean add(final Properties ctx, final int mWarehouseID,
			final int mLocatorID, final int mProductID,
			final int mAttSetInstID,
			final int reservationASIID,
			final BigDecimal diffQtyOnHand, final BigDecimal diffQtyReserved,
			final BigDecimal diffQtyOrdered, final String trxName ) {

		MStorage storage = null;
		final StringBuffer diffText = new StringBuffer("(");

		// Get Storage
		if (storage == null) {
			storage = getCreate(ctx, mLocatorID, mProductID,
					mAttSetInstID, trxName);
		}// Verify
		if (storage.getM_Locator_ID() != mLocatorID
				&& storage.getM_Product_ID() != mProductID
				&& storage.getM_AttributeSetInstance_ID() != mAttSetInstID) {
			slog.severe("No Storage found - M_Locator_ID=" + mLocatorID
					+ ",M_Product_ID=" + mProductID + ",ASI="
					+ mAttSetInstID);
			return false;
		}
		MStorage storage0 = null;
		if (mAttSetInstID != reservationASIID) {
			storage0 = get(ctx, mLocatorID, mProductID,
					reservationASIID, trxName);
			if (storage0 == null) // create if not existing - should not happen
			{
				final MWarehouse mWarehouseh = MWarehouse.get(ctx, mWarehouseID);
				final int xM_Locator_ID = mWarehouseh.getDefaultLocator().getM_Locator_ID();
				storage0 = getCreate(ctx, xM_Locator_ID, mProductID,
						reservationASIID, trxName);
			}
		}
		boolean changed = false;
		if (diffQtyOnHand != null && diffQtyOnHand.signum() != 0) {
			storage.setQtyOnHand(storage.getQtyOnHand().add(diffQtyOnHand));
			diffText.append("OnHand=").append(diffQtyOnHand);
			changed = true;
		}
		if (diffQtyReserved != null && diffQtyReserved.signum() != 0) {// Cantidad
			// reservada
			if (storage0 == null) {
				storage.setQtyReserved(storage.getQtyReserved().add(
						diffQtyReserved));
			} else {
				storage0.setQtyReserved(storage0.getQtyReserved().add(
						diffQtyReserved));
			}
			diffText.append(" Reserved=").append(diffQtyReserved);
			changed = true;
		}
		if (diffQtyOrdered != null && diffQtyOrdered.signum() != 0) {
			if (storage0 == null) {
				storage.setQtyOrdered(storage.getQtyOrdered().add(
						diffQtyOrdered));//-6000
			} else {
				storage0.setQtyOrdered(storage0.getQtyOrdered().add(
						diffQtyOrdered));
			}
			diffText.append(" Ordered=").append(diffQtyOrdered);
			changed = true;
		}
		if (changed) {
			diffText.append(") -> ").append(storage.toString());
			slog.fine(diffText.toString());
			if (storage0 != null) {
				storage0.save(trxName);
			} // No AttributeSetInstance (reserved/ordered)
			return storage.save(trxName);
		}

		return true;
	} // add

	/**************************************************************************
	 * Get Location with highest Locator Priority and a sufficient OnHand Qty
	 * 
	 * @param mWarehouseID
	 *            warehouse
	 * @param mProductID
	 *            product
	 * @param mAttribSetInstID
	 *            asi
	 * @param Qty
	 *            qty
	 * @param trxName
	 *            transaction
	 * @return id
	 */
	public static int getMLocatorID(final int mWarehouseID, final int mProductID,
			final int mAttribSetInstID, final BigDecimal Qty, final String trxName) {
		return MStorage.getMLocatorID(null, mWarehouseID
				, mProductID
				, mAttribSetInstID
				, Qty
				, false
				, trxName);
	}
	
	/** Buscar el localizador del almacen dentro del storage 
	 * para el productos que debe estar en el maestro de productos
	 * puede tener un lote por orden de prioridad DESC y cantidad DESC */
	public static int getMLocatorID(final Properties ctx, final int mWarehouseID, final int mProductID,
			final int mAttribSetInstID, final BigDecimal Qty, final boolean force, final String trxName) {
		int M_Locator_ID = 0;
		int firstM_Locator_ID = 0;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT s.M_Locator_ID, s.QtyOnHand ")
		.append(" FROM M_Storage s")
		.append(" INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)")
		.append(" INNER JOIN M_Product p ON (s.M_Product_ID=p.M_Product_ID)")
		.append(" INNER JOIN EXME_ProductoOrg po ON (p.M_Product_ID=po.M_Product_ID AND po.IsActive='Y' AND po.AD_Org_ID = ")
		.append(Env.getAD_Org_ID(Env.getCtx()))
		.append(") ")
		.append(" LEFT OUTER JOIN M_AttributeSet mas ON (po.M_AttributeSet_ID=mas.M_AttributeSet_ID) ")
		.append(" WHERE l.M_Warehouse_ID=?")
		.append(" AND s.M_Product_ID=?")
		.append(" AND (mas.IsInstanceAttribute IS NULL OR mas.IsInstanceAttribute='N' OR s.M_AttributeSetInstance_ID=?)")
		.append(" AND l.IsActive='Y' ")
		.append(" ORDER BY l.PriorityNo DESC, s.QtyOnHand DESC");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mWarehouseID);
			pstmt.setInt(2, mProductID);
			pstmt.setInt(3, mAttribSetInstID);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				final BigDecimal QtyOnHand = rset.getBigDecimal(2);
				if (QtyOnHand != null && Qty.compareTo(QtyOnHand) <= 0) {
					M_Locator_ID = rset.getInt(1);
					break;
				}
				if (firstM_Locator_ID == 0) {
					firstM_Locator_ID = rset.getInt(1);
				}
			}
			if(force && ctx!=null){
				if (M_Locator_ID <= 0 && firstM_Locator_ID <= 0) {
					M_Locator_ID = MLocator.getLocatorID(ctx, mWarehouseID,
							trxName);
				}
			}

		} catch (SQLException ex) {
			slog.log(Level.SEVERE, sql.toString(), ex);
		} catch (final Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rset, pstmt);
		}

		if (M_Locator_ID != 0) {
			return M_Locator_ID;
		}
		return firstM_Locator_ID;
	} // getM_Locator_ID

	/**
	 * Get Available Qty. The call is accurate only if there is a storage record
	 * and assumes that the product is stocked
	 * 
	 * @param mWarehouseID
	 *            wh
	 * @param mProductID
	 *            product
	 * @param mAttSetInstID
	 *            masi
	 * @param trxName
	 *            transaction
	 * @return qty available (QtyOnHand-QtyReserved) or null
	 */
	public static BigDecimal getQtyAvailable(final int mWarehouseID, final int mProductID, final int mAttSetInstID, final String trxName) {
		return getQtyAvailable(mWarehouseID, 0, mProductID, mAttSetInstID, trxName);
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//
//		final StringBuilder sql = new StringBuilder(
//				" SELECT SUM(QtyOnHand-QtyReserved) ")
//				.append(" FROM M_Storage s ")
//				.append(" INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID) ")
//				.append("WHERE s.M_Product_ID = ? ") // #1
//				.append(" AND l.M_Warehouse_ID = ? ");
//
//		if (mAttSetInstID != 0) {
//			sql.append(" AND M_AttributeSetInstance_ID = ? ");
//		}
//
//		try {
//			pstmt = DB.prepareStatement(sql.toString(), trxName);
//			pstmt.setInt(1, mProductID);
//			pstmt.setInt(2, mWarehouseID);
//			if (mAttSetInstID != 0) {
//				pstmt.setInt(3, mAttSetInstID);
//			}
//			rset = pstmt.executeQuery();
//
//			if (rset.next()) {
//				retValue = rset.getBigDecimal(1);
//				if (rset.wasNull()) {
//					retValue = null;
//				}
//			}
//		} catch (Exception e) {
//			slog.log(Level.SEVERE, sql.toString(), e);
//		} finally {
//			DB.close(rset, pstmt);
//		}
//		slog.fine("M_Warehouse_ID=" + mWarehouseID + ",M_Product_ID="
//				+ mProductID + " = " + retValue);
//		return retValue;
	} // getQtyAvailable
	
	/**
	 * Get Available Qty. The call is accurate only if there is a storage record
	 * and assumes that the product is stocked
	 * @param warehouseId Almacen (requerido cuando @param locatorId < 0 )
	 * @param locatorId Localizador (requerido cuando @param warehouseId < 0 )
	 * @param productId Producto (obligatorio)
	 * @param attSetInstID Lote
	 * @param trxName
	 * @return qty available (QtyOnHand-QtyReserved)
	 */
	public static BigDecimal getQtyAvailable(final int warehouseId, 
		final int locatorId, final int productId, final int attSetInstID,
		final String trxName) {

		final List<Object> params = new ArrayList<Object>();
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SUM(QtyOnHand-QtyReserved) ");
		sql.append(" FROM M_Storage s ");
		if (locatorId > 0) {
			sql.append("WHERE s.M_Locator_ID=? ");
			params.add(locatorId);
		} else {
			sql.append(" INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID) ");
			sql.append("WHERE l.M_Warehouse_ID=? ");
			params.add(warehouseId);
		}
		sql.append(" AND s.M_Product_ID=? ");
		params.add(productId);

		if (attSetInstID >= 0) {
			sql.append(" AND s.M_AttributeSetInstance_ID=? ");
			params.add(attSetInstID);
		}

		BigDecimal retValue = DB.getSQLValueBD(trxName, sql.toString(), params);
		slog.fine(sql + "\nM_Warehouse_ID=" + warehouseId + ",M_Product_ID=" + productId + ",M_Locator_ID=" + locatorId+",M_AttributeSetInstance_ID="+attSetInstID);
		return retValue == null ? BigDecimal.ZERO : retValue.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : retValue ;
	} // getQtyAvailable

	/**************************************************************************
	 * Persistency Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param ignored
	 *            ignored
	 * @param trxName
	 *            transaction
	 */
	public MStorage(final Properties ctx, final int ignored, final String trxName) {
		super(ctx, 0, trxName);
		if (ignored != 0) {
			throw new IllegalArgumentException("Multi-Key");
		}
		//
		setQtyOnHand(Env.ZERO);
		setQtyOrdered(Env.ZERO);
		setQtyReserved(Env.ZERO);
	} // MStorage

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
	public MStorage(final Properties ctx, final ResultSet rset, final String trxName) {
		super(ctx, rset, trxName);
	} // MStorage

	/**
	 * Full NEW Constructor
	 * 
	 * @param locator
	 *            (parent) locator
	 * @param mProductID
	 *            product
	 * @param mAttribSetInstID
	 *            attribute		.append(" SELECT s.M_Locator_ID, s.QtyOnHand ")
		.append(" FROM M_Storage s")
		.append(" INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)")
		.append(" INNER JOIN M_Product p ON (s.M_Product_ID=p.M_Product_ID)")
		.append(" INNER JOIN EXME_ProductoOrg po ON (p.M_Product_ID=po.M_Product_ID AND po.IsActive='Y' AND po.AD_Org_ID = ")
		.append(Env.getAD_Org_ID(Env.getCtx()))
		.append(") ")
		.append(" LEFT OUTER JOIN M_AttributeSet mas ON (po.M_AttributeSet_ID=mas.M_AttributeSet_ID) ")
		.append(" WHERE l.M_Warehouse_ID=?")
		.append(" AND s.M_Product_ID=?")
		.append(" AND (mas.IsInstanceAttribute IS NULL OR mas.IsInstanceAttribute='N' OR s.M_AttributeSetInstance_ID=?)")
		.append(" AND l.IsActive='Y' ")
		.append(" ORDER BY l.PriorityNo DESC, s.QtyOnHand DESC");

	 */
	private MStorage(final MLocator locator, final int mProductID,
			final int mAttribSetInstID) {
		this(locator.getCtx(), 0, locator.get_TrxName());
		setClientOrg(locator);
		setM_Locator_ID(locator.getM_Locator_ID());
		setM_Product_ID(mProductID);
		setM_AttributeSetInstance_ID(mAttribSetInstID);
	} // MStorage

//	/**
//	 * Change Qty OnHand
//	 * 
//	 * @param qty
//	 *            quantity
//	 * @param add
//	 *            add if true
//	 */
//	public void changeQtyOnHand(BigDecimal qty, boolean add) {
//		if (qty == null || qty.signum() == 0) {
//			return;
//		}
//		if (add) {
//			setQtyOnHand(getQtyOnHand().add(qty));
//		} else {
//			setQtyOnHand(getQtyOnHand().subtract(qty));
//		}
//	} // changeQtyOnHand

	/**
	 * Get M_Warehouse_ID of Locator
	 * 
	 * @return warehouse
	 */
	public int getMWarehouseID() {
		if (mMWarehouseID == 0) {
			final MLocator loc = MLocator.get(getCtx(), getM_Locator_ID());
			mMWarehouseID = loc.getM_Warehouse_ID();
		}
		return mMWarehouseID;
	} // getM_Warehouse_ID

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	public String toString() {
		final StringBuffer sb = new StringBuffer("MStorage[").append("M_Locator_ID=")
				.append(getM_Locator_ID()).append(",M_Product_ID=")
				.append(getM_Product_ID())
				.append(",M_AttributeSetInstance_ID=")
				.append(getM_AttributeSetInstance_ID()).append(": OnHand=")
				.append(getQtyOnHand()).append(",Reserved=")
				.append(getQtyReserved()).append(",Ordered=")
				.append(getQtyOrdered()).append("]");
		return sb.toString();
	} // toString
	
	/**
	 * Revisa si puede o no surtir un producto
	 * 
	 * @param ctx
	 *            Contexto
	 * @param productId
	 *            Producto
	 * @param locatorId
	 *            Localizador del producto
	 * @param lotId
	 *            Lote del producto, puede ser 0
	 * @param qty
	 *            Cantidad a solicitar
	 * @param trxName
	 * @return Si/No puede surtir
	 */
	public static boolean canSupply(Properties ctx, int productId, int locatorId, int lotId, BigDecimal qty, String trxName) {
		boolean retValue = false;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  COALESCE(s.QtyOnHand, 0) - COALESCE(s.QtyReserved, 0) ");
		sql.append("FROM ");
		sql.append("  m_storage s ");
		sql.append("WHERE ");
		sql.append("  s.m_product_id = ? AND ");
		sql.append("  s.m_locator_id = ? AND ");
		sql.append("  m_attributesetinstance_id = ? ");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, locatorId);
			pstmt.setInt(3, lotId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				BigDecimal dbQty = rs.getBigDecimal(1);

				if (qty.compareTo(dbQty) <= 0) {
					retValue = true;
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return retValue;
	}

	/**
	 * Verificamos si el producto seleccionado cuenta con existencias
	 * suficientes en el almacen indicado para surtir el pedido.
	 * 
	 * @param producto
	 *            El producto a verificar
	 * @param almacen
	 *            El almacen a consultar
	 * @param cantidad
	 *            La cantidad solicitada (Se truncan las decimales (25.34 --> 25
	 *            � 56.5 --> 56))
	 * @return La diferencia entre la existencia y la cantidad que se pretende
	 *         dar salida (existencia - cantidad).
	 * @throws Exception
	 *             en caso de ocurrir un error al consultar la existencia
	 */
	public static BigDecimal puedeSurtir(final int mProductID, final int mWarehouseID,
			BigDecimal cantidad) {

		final StringBuilder sql = new StringBuilder();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (cantidad == null) {
			cantidad = Env.ZERO;
		}
		cantidad = cantidad.setScale(0, RoundingMode.HALF_UP);

		try {
			sql.append(
					" SELECT NVL(SUM(s.QtyOnHand - s.QtyReserved),0) AS existencia ")
					.append(" FROM M_Storage s, M_Locator l ")
					.append(" WHERE s.M_Product_ID = ? ")
					.append(" AND s.M_Locator_ID = l.M_Locator_ID ")
					.append(" AND l.M_Warehouse_ID = ? ");

			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setLong(1, mProductID);
			pstmt.setLong(2, mWarehouseID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				BigDecimal existencia = rs.getBigDecimal("existencia");
				if (existencia == null) {
					existencia = Env.ZERO;
				}
				return existencia.subtract(cantidad);
			}
		} catch (SQLException e) {// es
			slog.log(Level.SEVERE, " -- puedeSurtir : ", e);
//			e.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
		}

		return Env.ZERO.negate();
	}

	// expert : gisela lee : get qty on hand
	/**
	 * Get Qty On Hand. The call is accurate only if there is a storage record
	 * and assumes that the product is stocked
	 * 
	 * @param mLocatorID
	 *            wh
	 * @param mProductID
	 *            product
	 * @param mAttSetInstID
	 *            masi
	 * @param trxName
	 *            transaction
	 * @return qty available (QtyOnHand-QtyReserved) or null
	 */
	public static BigDecimal getQtyOnHand(final int mLocatorID, final int mProductID,
			final int mAttSetInstID, final String trxName) {
		BigDecimal retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY)
		.append(" SELECT NVL(SUM(QtyOnHand), 0) ")
		.append(" FROM M_Storage ")
		.append(" WHERE M_Product_ID=? ") // #1
		.append(" AND M_Locator_ID=? ");
		if (mAttSetInstID >= 0) {
			sql.append(" AND M_AttributeSetInstance_ID=?");
		}
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, mProductID);
			pstmt.setInt(2, mLocatorID);
			if (mAttSetInstID >= 0) {// Se iguala la condicion 
				pstmt.setInt(3, mAttSetInstID);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				retValue = rs.getBigDecimal(1);
				if (rs.wasNull()) {
					retValue = null;
				}
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}
		slog.fine("M_Locator_ID=" + mLocatorID + ",M_Product_ID="
				+ mProductID + " = " + retValue);
		return retValue;
	} // getQtyOnHand

	// expert : gisela lee : get qty on hand

	/**
	 * addRegistriesOfNewProducts Obtiene los productos que no tengan un
	 * registro en M_Storage y los agrega en dicha tabla con cantidades en cero.
	 * 
	 * @param ctx
	 *            Contexto de la sesi?n
	 * @param m_Locator_ID
	 *            ID del Localizador de los productos a buscar y agregar en
	 *            M_Storage
	 * @param commit
	 *            Hacer commit a los nuevos registros
	 * @param trxName
	 *            Nombre de la transacci?n
	 * 
	 *            public static void addRegistriesOfNewProducts (Properties ctx,
	 *            int m_Locator_ID, boolean commit, String trxName) {
	 * 
	 *            if (m_Locator_ID != 0){ StringBuffer sql = new StringBuffer();
	 * 
	 *            sql.append(" SELECT p.M_Product_ID ")
	 *            .append(" FROM M_Product p ")
	 *            .append(" WHERE p.M_Locator_Id = ").append(m_Locator_ID)
	 *            .append(" AND p.isActive = 'Y'")
	 *            .append(" AND p.M_Product_ID ")
	 *            .append(" NOT IN (SELECT DISTINCT s.M_Product_ID ")
	 *            .append(" FROM M_Storage s ")
	 *            .append(" INNER JOIN m_product p ON (p.m_locator_id = "
	 *            ).append(m_Locator_ID) .append(" AND p.isActive='Y' )) ");
	 * 
	 *            PreparedStatement pstmt = null;
	 * 
	 *            try { pstmt = DB.prepareStatement (sql.toString(), trxName);
	 *            ResultSet rs = pstmt.executeQuery (); while (rs.next ()) {
	 *            MStorage newRegistry = new MStorage(ctx, 0, trxName);
	 *            newRegistry.setM_Product_ID(rs.getInt("M_Product_ID"));
	 *            newRegistry.setM_Locator_ID(m_Locator_ID);
	 *            newRegistry.setIsActive(true);
	 *            newRegistry.setQtyOnHand(Env.ZERO);
	 *            newRegistry.setQtyReserved(Env.ZERO);
	 *            newRegistry.setQtyOrdered(Env.ZERO);
	 *            newRegistry.setM_AttributeSetInstance_ID(0);
	 *            newRegistry.save(); }
	 * 
	 *            rs.close (); pstmt.close (); pstmt = null; rs=null; } catch
	 *            (Exception e) { s_log.log(Level.SEVERE, sql.toString(), e); }
	 *            try { if (pstmt != null) pstmt.close (); pstmt = null; } catch
	 *            (Exception e) { pstmt = null; } } }
	 */
	public static boolean validarRelLocatorAlm(final Properties ctx, final int locator,
			final int producto, final int mAttSetInstId) throws Exception {

		boolean encontro = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final StringBuilder sql = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		sql.append("	SELECT *").append("	FROM M_Storage")
				.append("	WHERE M_PRODUCT_ID = ? ")
				.append("	AND M_LOCATOR_ID = ? ")
				.append("	AND M_ATTRIBUTESETINSTANCE_ID = ? ");

		if (ctx != null) {
			sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ", "M_Storage"));
		}
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, producto);
			pstmt.setInt(2, locator);
			pstmt.setInt(3, mAttSetInstId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				encontro = true;
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e.getMessage());
		} finally {
			DB.close(rs, pstmt);
		}

		return encontro;
	}

	/**
	 * 
	 * @param ctx
	 * @param warehouseId
	 * @return
	 */
	public static List<MProduct> getDetail(final Properties ctx,
			final int mAttSetInstID) {
		final List<MProduct> list = new ArrayList<MProduct>();

		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append("select m_product_id from m_storage where m_attributesetinstance_id = ?");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, mAttSetInstID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				final MProduct prod = new MProduct(ctx, rs.getInt("m_product_id"),
						null);
				list.add(prod);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}

//	/**
//	 * Cantidad en libro de un producto
//	 * 
//	 * @param ctx
//	 * @param M_Product_ID
//	 * @param M_Locator_ID
//	 * @param trxName
//	 * @return
//	 */
//	public static BigDecimal getQtyBook(final Properties ctx,
//			final int M_Product_ID, final int M_Locator_ID, final String trxName) {
//		BigDecimal onHand = Env.ZERO;
//		final MStorage[] storages = MStorage.getAll(ctx, M_Product_ID,
//				M_Locator_ID, trxName);
//		if (storages == null) {
//			return onHand;
//		}
//
//		for (int i = 0; i < storages.length; i++) {
//			final MStorage storage = storages[i];
//			if (storage.getQtyOnHand().signum() == 0) {
//				continue;
//			}
//			onHand = onHand.add(storage.getQtyOnHand());
//		}
//		return onHand;
//	}

//	/**
//	 * Validar existencias
//	 * 
//	 * @param ctx
//	 *            : Contexto
//	 * @param mProduct
//	 *            : Información del Producto
//	 * @param uoMSale
//	 *            : Unidad de medida de la transacción (normalmente de venta)
//	 * @param txtQty
//	 *            : Cantidad de la transacción (normalmente de venta)
//	 * @param mWarehouse
//	 *            : Almacen donde se validarán las existencias
//	 * @return ModelError : Con el mensaje de error o null si no hubo errores
//	 */
//	public static ModelError controlaExistencias(final Properties ctx,
//			final MProduct mProduct, final int uoMSale,
//			final BigDecimal txtQty, final MWarehouse mWarehouse) {
//
//		// Si se controlan existencias y el producto es almacenable
//		if (MEXMEMejoras.get(ctx).isControlExistencias()
//				&& mProduct.isStocked()) {
//			final MEXMEConfigPre config = MEXMEConfigPre.get(ctx, null);
//			if (config != null && config.isCreateInventory()) {
//
//				// Cantidad requerida
//				BigDecimal targetQty = Env.ZERO;
//
//				// validacion de conversiones de UDM. Si las udm son diferentes
//				if (mProduct.getC_UOM_ID() == uoMSale) {
//					targetQty = txtQty;
//				} else {
//					// conversion entre unidades
//					final MUOMConversion rates = MEXMEUOMConversion
//							.validateConversions(Env.getCtx(),
//									mProduct.getM_Product_ID(), uoMSale, null);
//					if (rates == null) {
//						return new ModelError(1, "error.udm.noExisteConversion");
//					} else {
//						// cantidad en udm de minima
//						BigDecimal[] qtys = mProduct.qtyConversion(txtQty,
//								uoMSale);
//						// minima
//						targetQty = qtys[0];// Min
//					}
//				}
//
//				// Cantidad disponible en el almacen
//				BigDecimal QtyAvailable = MStorage.getQtyAvailable(
//						mWarehouse.getM_Warehouse_ID(),
//						mProduct.getM_Product_ID(), 0, null);
//
//				if (QtyAvailable == null) {
//					QtyAvailable = Env.ZERO;
//				}
//
//				// Comparacion final
//				if (targetQty.compareTo(QtyAvailable) > 0) {
//					return new ModelError(ModelError.TIPOERROR_Error,
//							"error.encCtaPac.noExistenProd", mProduct.getName());
//				}
//			}// Si no existen continua con el proceso
//		}// Si no existen continua con el proceso
//		return null;
//	}

	/**
	 * Se espera que siempre controle existencias
	 * 
	 * @param ctx
	 * @param mGenProduct
	 * @param trxName
	 * @return
	 */
	public static List<BeanView> getExistenciasGenProduct(
			final Properties ctx, final MEXMEGenProduct mGenProduct,
			final int almacenId, final boolean agrupar, final String trxName) {

		final List<BeanView> list = new ArrayList<BeanView>();
		final StringBuilder sql = new StringBuilder(
				Constantes.INIT_CAPACITY_ARRAY);
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		sql.append(
				" SELECT s.M_AttributeSetInstance_ID, SUM(s.qtyonhand-s.qtyreserved) as disponible, asi.lot ")
				.append(" , p.Value, p.Name, p.M_Product_ID , gp.Generic_Product_Name , po.IsFormulary, asi.GuaranteeDate ")
				.append(" FROM M_Storage s                ")
				.append("    INNER JOIN M_Product         p ON p.M_Product_ID  = s.M_Product_ID AND p.IsActive = 'Y' ")
				.append("    INNER JOIN EXME_ProductoOrg po ON po.M_Product_ID = p.M_Product_ID AND p.IsActive = 'Y' ")
				.append("    INNER JOIN EXME_GenProduct  gp ON gp.EXME_GenProduct_ID = p.EXME_GenProduct_ID ")
				.append("    LEFT  JOIN M_AttributeSetInstance asi ON asi.M_AttributeSetInstance_ID = s.M_AttributeSetInstance_ID   ")
				.append(" WHERE s.IsActive = 'Y'          ")
				.append(MEXMELookupInfo.addAccessLevelSQL(ctx, " ",
						"M_Storage", "s"))
				.append("    AND   po.AD_Org_ID = ?          ")
				.append("    AND   gp.EXME_GenProduct_ID = ? ")
				.append("    AND   s.QtyOnHand > 0           ")
				.append("    AND   s.M_Locator_ID IN (       ")
				.append("            SELECT M_Locator_ID FROM M_Locator WHERE IsActive = 'Y' AND M_Warehouse_ID  = ? ")
				.append("    )                               ")
				.append(" GROUP BY gp.Generic_Product_Name, p.Value, p.Name, p.M_Product_ID, s.M_AttributeSetInstance_ID, asi.lot, po.IsFormulary, asi.GuaranteeDate ")
				.append(" ORDER BY asi.GuaranteeDate ASC, s.M_AttributeSetInstance_ID DESC, po.IsFormulary DESC ");

		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Org_ID(ctx));
			pstmt.setInt(2, mGenProduct.getEXME_GenProduct_ID());
			pstmt.setInt(3, almacenId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getBigDecimal("disponible").compareTo(Env.ZERO) > 0) {
					final BeanView bean = new BeanView();
					bean.setCadena1(rs.getString("lot"));
					bean.setCadena2(rs.getString("Value"));
					bean.setCadena3(rs.getString("Name"));
					bean.setCadena4(rs.getString("IsFormulary"));

					String fecha = "";
					if (rs.getTimestamp("GuaranteeDate") != null) {
						fecha = Constantes.getShortDate(ctx).format(
								rs.getTimestamp("GuaranteeDate"));
					}
					//
					// if(rs.getTimestamp("DateTo")!=null){
					// fecha = fecha +
					// " "+Constantes.getShortDate(ctx).format(rs.getTimestamp("DateTo"));
					// }

					bean.setCadena5(fecha);
					bean.setInteger1(rs.getInt("M_Product_ID"));
					bean.setInteger2(rs.getInt("M_AttributeSetInstance_ID"));
					bean.setColor(rs.getString("Generic_Product_Name"));
					bean.setDcimal(rs.getBigDecimal("disponible"));
					list.add(bean);
				}
			}
			if (list.isEmpty()) {
				final BeanView bean = new BeanView();
				bean.setCadena1("");
				bean.setCadena2(mGenProduct.getGeneric_Product_Name());
				bean.setCadena3("");
				bean.setCadena4("N");
				bean.setCadena5("");
				bean.setInteger1(0);
				bean.setInteger2(0);
				bean.setColor(mGenProduct.getGeneric_Product_Name());
				bean.setDcimal(Env.ZERO);
				list.add(bean);
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
	
	/**
	 * Productos con los que cuenta un almacen en particular
	 * 
	 * @param ctx
	 *            Contexto de la App
	 * @param warehouseId
	 *            Almacen
	 * @param trxName
	 *            Trx
	 * @return Listado de Productos
	 */
	public static List<Integer> getProductsByWarehouse(Properties ctx, int warehouseId, String trxName){
		List<Integer> products = new ArrayList<Integer>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ");
		sql.append("  (s.M_Product_ID) ");
		sql.append("FROM ");
		sql.append("  M_Storage s ");
		sql.append("  INNER JOIN M_locator l ");
		sql.append("  ON s.M_Locator_ID = l.M_Locator_ID ");
		sql.append("  INNER JOIN M_Product p ");
		sql.append("  ON s.M_Product_ID = p.M_Product_ID ");
		sql.append("WHERE ");
		sql.append("  l.M_Warehouse_ID = ? AND ");
		sql.append("  p.isActive = 'Y' AND ");
		sql.append("  l.isActive = 'Y' AND ");
		sql.append("  s.isActive = 'Y' ");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, warehouseId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				products.add(rs.getInt(1));
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		
		return products;
	}
	
	/**
	 * Obtiene las líneas del almacén basandose en los parámetros
	 * 
	 * @param ctx
	 *            Contexto
	 * @param inventoryId
	 *            Inventario, se necesita si hay que excluir datos
	 * @param locatorId
	 *            Localizador id
	 * @param locatorValue
	 *            Valor del localizador
	 * @param productId
	 *            Producto Id
	 * @param productValue
	 *            Valor del Producto
	 * @param prodCategoryId
	 *            Categoría del Producto
	 * @param operator
	 *            Operador Operador, se usa para validar exitencias (< 0, > 0, =
	 *            0, null)
	 * @param exclude
	 *            Excluir valores previos
	 * @param trxName
	 *            Trx Name
	 * @return Detalles del almacén
	 */
	public static List<MStorage> get(Properties ctx, int inventoryId, int locatorId, String locatorValue, int productId, String productValue, int prodCategoryId, String operator, boolean exclude, String trxName) {
		List<MStorage> list = new ArrayList<MStorage>();
		
		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  s.* ");
		sql.append("FROM ");
		sql.append("  m_storage s ");
		sql.append("  INNER JOIN m_locator l ");
		sql.append("  ON s.m_locator_id = l.m_locator_id ");
		sql.append("  INNER JOIN m_product p ");
		sql.append("  ON s.m_product_id = p.m_product_id ");
		sql.append("WHERE s.isActive = 'Y' ");

		if (locatorId > 0) {
			sql.append("  AND s.m_locator_id = ? ");
			params.add(locatorId);
		}

		if (StringUtils.isNotBlank(locatorValue)) {
			sql.append("  AND l.value LIKE ? ");
			params.add(locatorValue);
		}

		if (productId > 0) {
			sql.append("  AND p.m_product_id = ? ");
			params.add(productId);
		}

		if (StringUtils.isNotBlank(productValue)) {
			sql.append("  AND p.value LIKE ? ");
			params.add(productValue);
		}

		if (prodCategoryId > 0) {
			sql.append("  AND p.m_product_category_id = ? ");
			params.add(prodCategoryId);
		}
		
		if (">".equals(operator)) {
			sql.append("  AND (s.QtyOnHand - s.qtyReserved) > 0 ");
		} else if ("<".equals(operator)) {
			sql.append("  AND (s.QtyOnHand - s.qtyReserved) < 0 ");
		} else if ("=".equals(operator)) {
			sql.append("  AND (s.QtyOnHand - s.qtyReserved) = 0 ");
		}
		
		if (exclude && inventoryId > 0) {
			sql.append("  AND NOT EXISTS (");
			sql.append("SELECT ");
			sql.append("  * ");
			sql.append("FROM ");
			sql.append("  M_InventoryLine il ");
			sql.append("WHERE ");
			sql.append("  il.M_Inventory_ID=? AND ");
			sql.append("  il.M_Product_ID=s.M_Product_ID AND ");
			sql.append("  il.M_Locator_ID=s.M_Locator_ID AND ");
			sql.append("  COALESCE(il.M_AttributeSetInstance_ID, ");
			sql.append("  0) =COALESCE(s.M_AttributeSetInstance_ID, ");
			sql.append("  0)) ");

			params.add(inventoryId);
		}

		sql.append(MEXMELookupInfo.addAccessLevelSQL(ctx, Constantes.SPACE, Table_Name, "s"));

		sql.append(" ORDER BY l.Value, p.Value, s.QtyOnHand DESC");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				list.add(new MStorage(ctx, rs, trxName));
			}
		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return list;
	}

	
	/**
	 * Buscar si existen movimientos de productos pendientes de ser confirmados
	 * @param ctx: Contexto
	 * @param warehouseId: Almacen
	 * @return Mensaje vacio si no hay pendientes 
	 */
	public static String getMovementInProgress(final Properties ctx, final int warehouseId) {
		final StringBuilder where = new StringBuilder()
		.append(" COALESCE(QtyReserved,0) <> 0 ")
		.append(" AND M_Locator_ID IN ( ")
		.append("     SELECT M_Locator_ID FROM M_Locator WHERE M_Warehouse_ID = ? ) ");

		final List<MStorage> lista = new Query(ctx
				, X_M_Storage.Table_Name
				, where.toString()
				, null)
		.setOnlyActiveRecords(true)
		.addAccessLevelSQL(true)
		.setParameters(warehouseId)
		.list();

		final StringBuilder msg = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		if(!lista.isEmpty()){
			msg.append(Utilerias.getLabel("listaAltasMedicas.tags.pendingMovements"))
			.append(" ")
			.append(Utilerias.getLabel("msg.citas.estatus.porConfirmar"));
		}
		return msg.toString();
	}
	
	
	public static List<MStorage> getStorage(final Properties ctx, int productId, int locatorId, int lotId, String trxName) {
		return new Query(ctx, Table_Name, " m_product_id = ? AND m_locator_id = ? AND m_attributesetinstance_id = ? ", trxName)//
		.setParameters(productId, locatorId, lotId)//
		.list();
	}
	
	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean isValidateStockConfig(Properties ctx) {
		return MEXMEMejoras.get(ctx).isControlExistencias();
			// Si es que no se permiten existencias en negativo
		// se comenta pq es para estados unidos, validar si aun aplicara
//			&& MEXMEConfigPre.get(ctx, null).isCreateInventory();
	}
	
	/**
	 * 
	 * @param prod
	 * @param uomSale
	 * @param qtyDelivered
	 * @param warehouseId
	 * @param attributeSetInstanceId
	 * @param warehouseConsign
	 * @return
	 */
	public static String validateStock(final MProduct prod, 
		final int uomSale, 
		final BigDecimal qtyDelivered, 
		final int warehouseId,
		final int locatorId,
		final int attributeSetInstanceId) {

		final ErrorList errores = new ErrorList();

		if (prod.isStocked()) {
			
			final MWarehouse ware = MWarehouse.get(prod.getCtx(), warehouseId);
			
			if(ware.isControlExistencias()) {
				
				Properties ctx = prod.getCtx();
				BigDecimal targetQty  = BigDecimal.ZERO;
				// validacion de conversiones de UDM. Si las udm son diferentes
				if (prod.getC_UOM_ID() == uomSale) {
					targetQty = qtyDelivered;
				} else {
					// conversion entre unidades
					final MUOMConversion rates = MEXMEUOMConversion.validateConversions(ctx, prod.getM_Product_ID(), uomSale, null);
					if (rates == null) {
						errores.add(Error.VALIDATION_ERROR, Utilerias.getMsg(ctx, "error.udm.noExisteConversion"));
						slog.severe(Utilerias.getMsg(ctx, "error.udm.noExisteConversion") + prod.getName());
					} else {
						// cantidad en udm de minima
						final BigDecimal[] qtys = prod.qtyConversion(qtyDelivered, uomSale);
						targetQty = qtys[0];// Min
					}
				}
				
				// cantidad disponible
				BigDecimal qtyAvailable =// BigDecimal.ZERO;
					MStorage.getQtyAvailable(warehouseId, locatorId, prod.getM_Product_ID(), attributeSetInstanceId, null);
				
				boolean inStock = true;
				// si la cantidad solicitada es myor que la disponible
				if (targetQty.compareTo(qtyAvailable) > 0) {
					inStock = false;
					// MStorage.add(ctx, warehouseId, locatorId, prod.getM_Product_ID(), attributeSetInstanceId, 0, new BigDecimal(7),
					// BigDecimal.ZERO, BigDecimal.ZERO, null);
					// si el producto es consigna se hacen sus respectivas validaciones
					if (MCountry.isMexico(ctx) && prod.getProdOrg().isConsigna()) {
						if(ware.isConsigna()) {// si no hay en almacen de consigna
							if (ware.isVirtual()) {
								errores.add(-1, Utilerias.getAppMsg(ctx, "error.stock.consign.virtual", ware.getName()));
							} else { // pedir a fisico
								errores.add(-1, Utilerias.getAppMsg(ctx, "error.stock.consign.warehouse", ware.getName()));
							}
						} else if (locatorId > 0) { // es propio
							errores.add(-1, Utilerias.getAppMsg(ctx, "error.stock.consign.product"));
						} else {
							int wareConsignId = MWarehouse.getWarehouseRel(ctx, warehouseId, prod.getM_Product_ID(), null)[0];
							if(wareConsignId > 0) {
								String msg = validateStock(prod, uomSale, qtyDelivered, wareConsignId, -1, attributeSetInstanceId);
								if(msg.length() > 0) {
									errores.add(-1, msg.toString());
								} else { // hay existencias
									MWarehouse ware2 = MWarehouse.get(ctx, wareConsignId);
									if (ware2.isVirtual()) {
										inStock = true;
									} else {// pedir a fisico
										errores.add(-1, Utilerias.getAppMsg(ctx, "error.stock.consign.warehouse", ware2.getName()));
									}
								}								
							} else {// no existencias locales
								errores.add(-1, Utilerias.getAppMsg(ctx, "error.stock.consign.product"));
							}
						}
					} else {
						errores.add(-1, Utilerias.getAppMsg(ctx, "error.encCtaPac.noExistenProd", prod.getName()));
					}
				}
				if (!inStock) {
					slog.severe("EnterCharge#validateStock: No hay cantidad suficiente para el cargo: " + qtyAvailable);
					StringBuilder msg = new StringBuilder();
					msg.append(Utilerias.getMsg(ctx, "msj.cantidadDisponible"));
					if(locatorId > 0) {
						msg.append(" ").append(MLocator.get(ctx, locatorId).getName());
					} else {
						msg.append(" ").append(ware.getName());
					}
					msg.append("<br> (").append(MUOM.get(ctx, uomSale).getName()).append(") ");
					if (prod.getC_UOM_ID() == uomSale) {
						msg.append(": ").append(qtyAvailable);
					} else {
						BigDecimal qtyVol = MEXMEUOMConversion.convertProductTo(ctx, prod.getM_Product_ID(), uomSale, qtyAvailable, null, true);
						msg.append(": ").append(qtyVol == null ? BigDecimal.ZERO : qtyVol.setScale(0, RoundingMode.FLOOR));
					}
					errores.add(-1, msg.toString());
				}
			}
		}//
		return errores.toString();
	}
	
	
	/**
	 * Obtiene la lista de registros de existencias.
	 * ordenados por Fecha de Vencimiento de lote (para el caso de un producto con lote),
	 * y por cantidad disponible (a la mano menos reservada)
	 * Filtra los registros con lotes vencidos
	 * 
	 * @param ctx Contexto
	 * @param warehouseId almacen (obligatorio)
	 * @param productId Producto (obligatorio)
	 * @param attributeSetInstanceId Lote
	 * @param trxName
	 * @return
	 */
	public static List<MStorage> getList(Properties ctx, 
		int warehouseId, 
		int productId, 
		int attributeSetInstanceId,
		String trxName) {

		final StringBuilder where = new StringBuilder(Constantes.INIT_CAPACITY_ARRAY);
		final List<Object> params = new ArrayList<Object>();
		final StringBuilder joins = new StringBuilder();
		final StringBuilder orderby = new StringBuilder();
		final StringBuilder select = new StringBuilder();
		
		joins.append("INNER JOIN M_Locator l ON (l.M_Locator_ID=M_Storage.M_Locator_ID) ");
		
		where.append(" M_Storage.M_product_id=? ");
		params.add(productId);
		where.append(" AND (M_Storage.QtyOnHand-M_Storage.QtyReserved)>0 ");
		where.append(" AND l.M_Warehouse_ID=? ");
		params.add(warehouseId);
		
		//LOTE
		if (attributeSetInstanceId >= 0) {
			joins.append("INNER JOIN M_AttributeSetInstance lot ");
			joins.append(" ON (M_Storage.M_AttributeSetInstance_ID=lot.M_AttributeSetInstance_ID) ");
			if(attributeSetInstanceId > 0) {
				where.append(" AND M_Storage.M_AttributeSetInstance_id IN (0,?) ");
				params.add(attributeSetInstanceId);
			} else {
				where.append(" AND M_Storage.M_AttributeSetInstance_id > 0");
			}
			// no vencidos
			where.append(" AND (lot.GuaranteeDate IS NULL OR lot.GuaranteeDate >= DATE_TRUNC('DAY',NOW())) ");
			orderby.append(" lot.GuaranteeDate, ");
			select.append(" lot.GuaranteeDate, ");
		}
		select.append(" (M_Storage.QtyOnHand-M_Storage.QtyReserved) as available ");
		orderby.append(" available  DESC ");
			
		return new Query(ctx, MStorage.Table_Name, where.toString(), trxName)//
			.setParameters(params)//
			.setOnlyActiveRecords(true)//
			.setSelectColumns(select.toString())//
			.setJoins(joins)//
			.setOrderBy(orderby.toString())//
			.addAccessLevelSQL(true)//
			.list();
	}
	
	public BigDecimal getQtyAvailable() {
		final BigDecimal qtyOnHand = super.getQtyOnHand() == null ? BigDecimal.ZERO : super.getQtyOnHand();
		final BigDecimal qtyReserved = super.getQtyReserved() == null ? BigDecimal.ZERO : super.getQtyReserved();
		final BigDecimal qtyAvailable = qtyOnHand.subtract(qtyReserved);
		return qtyAvailable.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : qtyAvailable;
	}
	
	private BigDecimal qtyHandUom;
	private BigDecimal qtyHandVol;
	
	public BigDecimal getQtyHandVol() {
		if (qtyHandVol == null) {
			final StringBuilder sql = new StringBuilder("SELECT (case  when (p.c_uom_id = p.c_uomvolume_id )then 0  when(sum(s.QtyOnHand))/COALESCE(con.MultiplyRate,con2.MultiplyRate) is null then null ")
					.append("WHEN (COALESCE(con.c_uom_to_id,con2.c_uom_to_id) = p.c_uomvolume_id ) THEN (COALESCE(floor((SUM(s.QtyOnHand))/COALESCE(con.dividerate,con2.dividerate)),(SUM(s.QtyOnHand)))) ")
					.append("ELSE (COALESCE(floor((sum(s.QtyOnHand))/COALESCE(con.MultiplyRate,con2.MultiplyRate)),(sum(s.QtyOnHand)))) END ) as VolumeH FROM m_storage s ")
					.append("INNER JOIN m_product p ON s.m_product_id = p.m_product_id LEFT JOIN c_uom_conversion con ON (con.C_Uom_ID=p.C_Uom_ID and con.C_Uom_To_ID =p.C_UomVolume_ID) ")
					.append("LEFT JOIN c_uom_conversion con2 ON (con2.C_Uom_ID=p.C_UomVolume_ID and con2.C_Uom_To_ID =p.C_Uom_ID) WHERE s.m_product_id = ? and s.m_locator_id = ? ")
					.append("GROUP BY s.m_product_id, con.MultiplyRate, con2.MultiplyRate, con.dividerate, con2.dividerate, con.c_uom_to_id, con.c_uom_to_id, con2.c_uom_to_id, p.c_uom_id, ")
					.append("p.c_uomvolume_id, s.m_locator_id ");

			qtyHandVol = new BigDecimal(DB.getSQLValue(get_TrxName(), sql.toString(), getM_Product_ID(), getM_Locator_ID()));
			if (qtyHandVol == null) {
				qtyHandVol = BigDecimal.ZERO;
			}
		}
		return qtyHandVol;
	}
	
	public BigDecimal getqtyHandUom() {
		if (qtyHandUom == null) {
			final StringBuilder sql = new StringBuilder(
					"SELECT (CASE WHEN (p.c_uom_id = p.c_uomvolume_id ) THEN (SUM(s.QtyOnHand)) WHEN MOD((sum(s.QtyOnHand)),COALESCE(con.MultiplyRate,con2.MultiplyRate)) IS NULL THEN 0")
					.append("WHEN (COALESCE(con.c_uom_to_id,con2.c_uom_to_id) = p.c_uomvolume_id ) THEN (floor(MOD((SUM(s.QtyOnHand)),COALESCE(con.dividerate,con2.dividerate)))) ")
					.append("ELSE (floor(mod((sum(s.QtyOnHand)),COALESCE(con.MultiplyRate,con2.MultiplyRate)))) END ) AS UnitH FROM m_storage s ")
					.append("INNER JOIN m_product p ON s.m_product_id = p.m_product_id LEFT JOIN c_uom_conversion con ON (con.c_uom_id=p.c_uom_id and con.c_uom_to_id =p.c_uomvolume_id) ")
					.append("LEFT JOIN c_uom_conversion con2 ON (con2.C_Uom_ID=p.C_UomVolume_ID and con2.c_uom_to_id = p.c_uom_id) WHERE s.m_product_id = ? and s.m_locator_id = ? ")
					.append("GROUP BY s.m_product_id, con.MultiplyRate, con2.MultiplyRate, con.dividerate, con2.dividerate, con.c_uom_to_id, con.c_uom_to_id, con2.c_uom_to_id, p.c_uom_id, ")
					.append("p.c_uomvolume_id, s.m_locator_id ");

			qtyHandUom = new BigDecimal(DB.getSQLValue(get_TrxName(), sql.toString(), getM_Product_ID(), getM_Locator_ID()));
			if (qtyHandUom == null) {
				qtyHandUom = BigDecimal.ZERO;
			}
		}
		return qtyHandUom;
	}

	public static List<InventoryCost> getInventoryCost(Properties ctx, int categoryId, int warehouseId,String orderBy, String trxName) {

		List<InventoryCost> list = new ArrayList<InventoryCost>();

		List<Object> params = new ArrayList<Object>();
		
		params.add(Env.getAD_Client_ID(ctx));
		params.add(Env.getAD_Org_ID(ctx));
		params.add(Env.getAD_Org_ID(ctx));
		params.add(Env.getAD_Org_ID(ctx));
		params.add(Env.getAD_Client_ID(ctx));

		StringBuilder sql = new StringBuilder();
		sql.append("with todo as ( ");
		sql.append("SELECT distinct p.M_Product_ID ");
		sql.append("  , p.Value ");
		sql.append("  , p.Name ");
		sql.append("  , pc.Name AS Category ");
		sql.append("  , w.name as wname ");
		sql.append("  , (get_cost(org.AD_Client_ID, org.M_Product_ID)) as costos ");
		sql.append("  , get_costo_kardex(org.AD_Client_ID, org.M_Product_ID) as costo ");
		sql.append("  ,(SUM(s.QtyOnHand)) AS QTY ");
		sql.append("FROM  M_Product p ");
		sql.append("inner join EXME_ProductoOrg org on (org.M_Product_ID = p.M_Product_ID ");
		sql.append("            AND org.ad_client_id = ? and org.isactive='Y' ) ");
		sql.append("INNER  JOIN M_Product_Category pc ON pc.M_Product_Category_ID = p.M_Product_Category_ID ");
		sql.append("INNER  JOIN M_Storage  s ON (org.M_Product_ID = s.M_Product_ID ");
		sql.append("			AND ( s.QtyOnHand  >0 OR s.QtyReserved   >0 OR s.QtyOrdered    >0 ) ");
		sql.append("			AND s.AD_Org_ID = ? ) ");
		sql.append("LEFT JOIN M_Locator   l ON (s.M_Locator_ID = l.M_Locator_ID ");
		sql.append("			AND l.AD_Org_ID = ? ) ");
		sql.append("LEFT JOIN M_Warehouse w ON (l.M_Warehouse_ID = w.M_Warehouse_ID ");
		sql.append("			AND w.AD_Org_ID = ? ) ");
		sql.append("WHERE p.IsActive    ='Y' ");
		sql.append("AND p.AD_Client_ID IN (0,?) ");

		if (categoryId > 0) {
			sql.append(" AND p.M_Product_Category_ID = ?");
			params.add(categoryId);
		}

		if (warehouseId > 0) {
			sql.append(" AND s.m_locator_id IN (SELECT M_Locator_ID FROM M_locator Where M_WAREHOUSE_ID = ? ) ");
			params.add(warehouseId);
		}

		sql.append("AND p.IsSummary     ='N' ");
		sql.append("GROUP BY org.AD_Client_ID, org.M_Product_ID, pc.Name, p.M_Product_ID, p.Value, P.NAME, ");
		sql.append("  w.name  ");
		
		if (StringUtils.isNotBlank(orderBy)) {
			sql.append(orderBy);
		}
		
		sql.append(") ");
		
		sql.append("select distinct M_Product_ID,Value,Name,Category,wname,QTY, ");
		sql.append("   (costos).costo_promedio as CostoPromedio, (costos).costo_estandar as CostoEstandar,  (costos).price_lastPO as PrecioUltimaCompra, costo * QTY as ValorExistencia ");
		sql.append("  from todo where qty > 0 ");

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, params);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				InventoryCost inventorycost = new InventoryCost();
				inventorycost.setValue(rs.getString("value"));
				inventorycost.setName(rs.getString("name"));
				inventorycost.setCategory(rs.getString("category"));
				inventorycost.setWname(rs.getString("wname"));
				inventorycost.setQty(rs.getBigDecimal("qty"));
				inventorycost.setAverageCost(rs.getBigDecimal("CostoPromedio"));
				inventorycost.setStandardCost(rs.getBigDecimal("CostoEstandar"));
				inventorycost.setLastPoCost(rs.getBigDecimal("PrecioUltimaCompra"));
				inventorycost.setStockValue(rs.getBigDecimal("ValorExistencia"));
				
				list.add(inventorycost);
			}

		} catch (Exception e) {
			slog.log(Level.SEVERE, null, e);
		} finally {
			DB.close(rs, pstmt);
		}

		return list;
	}
}// MStorage
