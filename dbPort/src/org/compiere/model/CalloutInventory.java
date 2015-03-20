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
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Physical Inventory Callouts
 *
 * @author Jorg Janke
 * @version $Id: CalloutInventory.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class CalloutInventory extends CalloutEngine {
	/**
	 * Product/Locator/ASI modified. Set Attribute Set Instance
	 *
	 * @param ctx
	 *            Context
	 * @param WindowNo
	 *            current Window No
	 * @param mTab
	 *            Model Tab
	 * @param mField
	 *            Model Field
	 * @param value
	 *            The new value
	 * @return Error message or ""
	 */


	public String product(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		if (isCalloutActive()) {
			return "";
		}
		final Integer InventoryLine = (Integer) mTab.getValue("M_InventoryLine_ID");
		if (InventoryLine != null && InventoryLine.intValue() != 0) {
			return "";
		}

		// New Line - Get Book Value
		int M_Product_ID = 0;
		final Integer Product = (Integer) mTab.getValue("M_Product_ID");

		if (Product != null) {
			M_Product_ID = Product.intValue();
		}

		final MProduct product = new MProduct(ctx, M_Product_ID, null);
		
		if (M_Product_ID > 0) {
			// Validar si el producto este a nivel de organización
			final String error = MProduct.isValidProductOrg(ctx, M_Product_ID, false);
			if(error!=null){
//				mTab.setValue("M_Product_ID", null);
				mTab.setValue("M_Locator_ID", null);
				mTab.setValue("Line", null);
				setCalloutActive(false);
				return error;
			}
		}
		
		if (M_Product_ID == 0) {

			mTab.setValue("C_UOM_ID", BigDecimal.ZERO);
			mTab.setValue("C_UOMVOLUME_ID", BigDecimal.ZERO);
			return "";
		}

////		 Validar que este dentro del charge master
//		final MProduct product = new MProduct(ctx, M_Product_ID, null);
//		if (M_Product_ID>0 && MEXMEMejoras.isControlaExistencias(
//				Env.getAD_Client_ID(Env.getCtx()),
//				Env.getAD_Org_ID(Env.getCtx()), null)
//				&& (product.getProdOrg() == null || (product.getProdOrg() != null && product
//						.getProdOrg().getAD_Org_ID() <= 0))) {
//			setCalloutActive(false);
//			return Utilerias.getLabel("msj.ligarProducto");
//		}
		
		int M_Locator_ID = 0;
		final Integer Locator = (Integer) mTab.getValue("M_Locator_ID");
		if (Locator != null) {
			M_Locator_ID = Locator.intValue();
		}
		if (M_Locator_ID == 0) {
			return "";
		}

		setCalloutActive(true);

		mTab.setValue("C_UOM_ID", Integer.valueOf(product.getC_UOM_ID()));
		mTab.setValue("C_UOMVOLUME_ID",
				Integer.valueOf(product.getC_UOMVolume_ID()));

		// Set Attribute
		int M_AttributeSetInstance_ID = 0;
		final Integer ASI = (Integer) mTab.getValue("M_AttributeSetInstance_ID");
		if (ASI != null) {
			M_AttributeSetInstance_ID = ASI.intValue();
		}
		// Product Selection
		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
				"M_Product_ID") == M_Product_ID) {
			if (M_AttributeSetInstance_ID == 0) {
				// toma del contexto.
				M_AttributeSetInstance_ID = Env.getContextAsInt(ctx,
						Env.WINDOW_INFO, Env.TAB_INFO,
						"M_AttributeSetInstance_ID");
			}
			if (M_AttributeSetInstance_ID == 0) {
				mTab.setValue("M_AttributeSetInstance_ID", null);
			} else {
				mTab.setValue("M_AttributeSetInstance_ID", Integer.valueOf(M_AttributeSetInstance_ID));
			}
		}

		// Set QtyBook from first storage location
		BigDecimal bd = null;
		/*
		 * String sql = "SELECT QtyOnHand FROM M_Storage " +
		 * "WHERE M_Product_ID=?" // 1 + " AND M_Locator_ID=?" // 2 +
		 * " AND M_AttributeSetInstance_ID=?"; if (M_AttributeSetInstance_ID ==
		 * 0) sql = "SELECT SUM(QtyOnHand) FROM M_Storage " +
		 * "WHERE M_Product_ID=?" // 1 + " AND M_Locator_ID=?"; // 2
		 */
		final String sql = qtySql(M_AttributeSetInstance_ID);
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {

			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_Product_ID);
			pstmt.setInt(2, M_Locator_ID);
			if (M_AttributeSetInstance_ID != 0) {
				pstmt.setInt(3, M_AttributeSetInstance_ID);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bd = rs.getBigDecimal(1);
				if (bd != null) {
					mTab.setValue("QtyBook", bd);
					setQtyBooks(product, bd, mTab);
				}
			}
		} catch (final SQLException e) {
			log.log(Level.SEVERE, sql, e);
			setCalloutActive(false);
			return e.getLocalizedMessage();
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
		//
		log.info("M_Product_ID=" + M_Product_ID + ", M_Locator_ID="
				+ M_Locator_ID + ", M_AttributeSetInstance_ID="
				+ M_AttributeSetInstance_ID + " - QtyBook=" + bd);
		setCalloutActive(false);
		return "";
	} // product

	/**
	 * Coloca las cantidades de empaque y volumen en los campos de registrados
	 * @param product
	 * @param value
	 * @param mTab
	 */
	public void setQtyBooks(MProduct product, BigDecimal value, GridTab mTab) {
		
		BigDecimal vol = MEXMEUOMConversion.convertProductTo(Env.getCtx(),
				product.getM_Product_ID(), product.getC_UOMVolume_ID(), value,
				null);
		
		//Validamos el Nulo por excepcion en log. 6 Dic 2013.
		if (vol == null) {
			vol = value; //Se le coloca la cantidad minimna en caso de que falle la Conversion.
		} else {
			vol = vol.setScale(0, RoundingMode.DOWN);
		}
		
		mTab.setValue("QtyBook_Vol", vol);

		final BigDecimal uom = MUOMConversion.getConversionDivisor(product, value);
		mTab.setValue("QtyBook_Uom", uom);
	}

	public static String qtySql(int M_AttributeSetInstance_ID) {
		// Set QtyBook from first storage location
//		String sql = "SELECT QtyOnHand FROM M_Storage "
		String sql = "SELECT QtyOnHand-QtyReserved FROM M_Storage "
				+ "WHERE M_Product_ID=?" // 1
				+ " AND M_Locator_ID=?" // 2
				+ " AND M_AttributeSetInstance_ID=?";
		if (M_AttributeSetInstance_ID == 0)
		 {
			sql = "SELECT SUM(QtyOnHand)-SUM(QtyReserved) FROM M_Storage "
//			sql = "SELECT SUM(QtyOnHand) FROM M_Storage "
					+ "WHERE M_Product_ID=?" // 1
					+ " AND M_Locator_ID=?"; // 2
		}

		return sql;
	}

	public String qtyConvert(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (isCalloutActive()) {
			return "";
		}
		final Integer InventoryLine = (Integer) mTab.getValue("M_InventoryLine_ID");
		if (InventoryLine != null && InventoryLine.intValue() != 0) {
			return "";
		}

		// Obtener el producto para realizar la conversion
		int mProductID = 0;
		final Integer Product = (Integer) mTab.getValue("M_Product_ID");
		if (Product != null) {
			mProductID = Product.intValue();
		}
		if (mProductID == 0) {
			return "";
		}
		final BigDecimal qty = (BigDecimal) mTab.getValue("QtyInternalUse");

		setCalloutActive(true);
		if (qty != null) {
			final int cUOMID = (Integer) mTab.getValue("C_UOM_ID");
			final MProduct mProduct = new MProduct(ctx, mProductID, null);
			// Si la nueva unidad de medida es la minima, no hace nada, pero si
			// es de paquete, convierte de minima a paquete.
			final BigDecimal convetedqty = MUOMConversion.convertUOMs(
					mProduct.getC_UOM_ID(), cUOMID, qty, true);
			mTab.setValue("QtyInternalUse", convetedqty);
		}
		setCalloutActive(false);

		return "";
	}

	/**
	 * Actualiza la cantidad contada cuando se modifican las cantidades de volumen o minima
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String setQtyCount(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		if (isCalloutActive()) {
			return "";
		}
		final BigDecimal qtyCountVol=(BigDecimal) mTab.getValue("QtyCount_Vol");
		final Integer prodId = (Integer) mTab.getValue("M_Product_ID");
		int productID=0;
		if (prodId != null) {
			productID = prodId.intValue();
		}
		final MProduct product = new MProduct(ctx, productID, null);
		final BigDecimal vol = MEXMEUOMConversion.convertProductFrom(Env.getCtx(),
				product.getM_Product_ID(), product.getC_UOMVolume_ID(), qtyCountVol,
				null, true);
		BigDecimal qtyCountUom=(BigDecimal) mTab.getValue("QtyCount_Uom");
		
		//Correccion de NullPointer detectado en log.
		//Jesus Cantu 6 Noviembre 2013.
		if (qtyCountUom == null) {
			qtyCountUom = Env.ZERO;
		}
		
		BigDecimal qtyCount=BigDecimal.ZERO;
		if (vol != null) {
			qtyCount = qtyCountUom.add(vol);
		}
		mTab.setValue("QtyCount", qtyCount);

		setCalloutActive(false);

		return "";
	}

} // CalloutInventory
