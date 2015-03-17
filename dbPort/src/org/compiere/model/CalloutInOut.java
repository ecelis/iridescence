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
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Utilerias;

/**
 * Shipment/Receipt Callouts
 * 
 * @author Jorg Janke
 * @version $Id: CalloutInOut.java,v 1.7 2006/07/30 00:51:05 jjanke Exp $
 */
public class CalloutInOut extends CalloutEngine {
	/** Debug Steps */
	private boolean steps = false;
	/**
	 * C_Order - Order Defaults.
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return error message or ""
	 */
	public String order(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		mTab.setValue("C_BPartner_ID", 0);
		Integer C_Order_ID = null;

		if (value instanceof BigDecimal) {
			C_Order_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			C_Order_ID = (Integer) value;
		}

		if (C_Order_ID == null || C_Order_ID.intValue() == 0) {
			return "";
		}

		// No Callout Active to fire dependent values
		// prevent recursive
		if (isCalloutActive()) {
			return "";
		}

		// Get Details
		final MOrder order = new MOrder(ctx, C_Order_ID.intValue(), null);

		if (order.get_ID() != 0) {

			mTab.setValue("DateOrdered", order.getDateOrdered());
			mTab.setValue("POReference", order.getPOReference());
			mTab.setValue("AD_Org_ID", Integer.valueOf(order.getAD_Org_ID()));
			mTab.setValue("AD_OrgTrx_ID",
					Integer.valueOf(order.getAD_OrgTrx_ID()));
			mTab.setValue("C_Activity_ID",
					Integer.valueOf(order.getC_Activity_ID()));
			mTab.setValue("C_Campaign_ID",
					Integer.valueOf(order.getC_Campaign_ID()));
			mTab.setValue("C_Project_ID",
					Integer.valueOf(order.getC_Project_ID()));
			mTab.setValue("User1_ID", Integer.valueOf(order.getUser1_ID()));
			mTab.setValue("User2_ID", Integer.valueOf(order.getUser2_ID()));
			mTab.setValue("M_Warehouse_ID",
					Integer.valueOf(order.getM_Warehouse_ID()));
			//
			mTab.setValue("DeliveryRule", order.getDeliveryRule());
			mTab.setValue("DeliveryViaRule", order.getDeliveryViaRule());
			mTab.setValue("M_Shipper_ID",
					Integer.valueOf(order.getM_Shipper_ID()));
			mTab.setValue("FreightCostRule", order.getFreightCostRule());
			mTab.setValue("FreightAmt", order.getFreightAmt());

			mTab.setValue("C_BPartner_ID",
					Integer.valueOf(order.getC_BPartner_ID()));
		}

		return "";
	} // order

	/**
	 * InOut - DocType. - sets MovementType - gets DocNo
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return error message or ""
	 */
	public String docType(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		Integer C_DocType_ID = null;

		if (value instanceof BigDecimal) {
			C_DocType_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			C_DocType_ID = (Integer) value;
		}

		if (C_DocType_ID == null || C_DocType_ID.intValue() == 0) {
			return "";
		}

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT d.DocBaseType, d.IsDocNoControlled, s.CurrentNext ")
				.append("FROM C_DocType d, AD_Sequence s ")
				.append("WHERE C_DocType_ID=? ")
				.append("AND d.DocNoSequence_ID=s.AD_Sequence_ID(+) ");

		try {

			Env.setContext(ctx, WindowNo, "C_DocTypeTarget_ID",
					C_DocType_ID.intValue());
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_DocType_ID.intValue());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				// Set Movement Type
				final String DocBaseType = rs.getString("DocBaseType");
				// Material Shipments
				if ("MMS".equals(DocBaseType)) {
					mTab.setValue("MovementType", "C-"); // Customer Shipments
				} else if ("MMR".equals(DocBaseType)) { // Material Receipts
					mTab.setValue("MovementType", "V+"); // Vendor Receipts
				}
				// DocumentNo
				if (rs.getString("IsDocNoControlled").equals("Y")) {
					mTab.setValue("DocumentNo",
							"<" + rs.getString("CurrentNext") + ">");
				}

			}
			rs.close();
			pstmt.close();
			// expert
			rs = null;
			pstmt = null;
			// expert
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
			return e.getLocalizedMessage();
		}

		return "";
	} // docType

	/**
	 * M_InOut - Defaults for BPartner. - Location - Contact
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return error message or ""
	 */
	public String bpartner(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		Integer C_BPartner_ID = null;
		if (value instanceof BigDecimal) {
			C_BPartner_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			C_BPartner_ID = (Integer) value;
			final boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo,
					"IsSOTrx"));
			final MBPartner socio = new MBPartner(ctx, C_BPartner_ID, null);
			if((IsSOTrx && !socio.isVendor())){
				C_BPartner_ID = null;
			}
		}

		if (C_BPartner_ID == null || C_BPartner_ID.intValue() == 0) {
			return "";
		}

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.AD_Language,l.C_BPartner_Location_ID,c.AD_User_ID, "
				+ "NVL(pcte.C_PaymentTerm_ID, p.C_PaymentTerm_ID) as C_PaymentTerm_ID,"
				+ "NVL(pcte.M_PriceList_ID,   p.M_PriceList_ID)   as M_PriceList_ID, "
				+ "NVL(pcte.PaymentRule,      p.PaymentRule)      as PaymentRule,"
				+ "NVL(pcte.POReference,      p.POReference)      as POReference,"
				+ "NVL(pcte.SO_Description,   p.SO_Description)   as SO_Description, "
				+ "NVL(pcte.IsDiscountPrinted,p.IsDiscountPrinted) as IsDiscountPrinted,"
				+ "NVL(pcte.SO_CreditLimit,   p.SO_CreditLimit) - "
				+ "NVL(pcte.SO_CreditUsed,    p.SO_CreditUsed)    as CreditAvailable "

				+ "FROM C_BPartner p "
				+ "LEFT OUTER JOIN C_BPartner_Location l ON (p.C_BPartner_ID=l.C_BPartner_ID) "
				+ "LEFT OUTER JOIN AD_User c ON (p.C_BPartner_ID=c.C_BPartner_ID) "
				// Lama - Partner Cte
				+ "LEFT JOIN C_BPartner_Cte pcte ON (p.C_BPartner_ID=pcte.C_BPartner_ID "
				+ "AND pcte.AD_Client_ID = ? ) "// #1 - Partner Cte
				+ "WHERE  p.C_BPartner_ID=?" // #2
		);

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, C_BPartner_ID.intValue());

			rs = pstmt.executeQuery();
			// BigDecimal bd;
			if (rs.next()) {
				// Location
				Integer ii = Integer.valueOf(rs
						.getInt("C_BPartner_Location_ID"));
				if (rs.wasNull()) {
					mTab.setValue("C_BPartner_Location_ID", null);
				} else {
					mTab.setValue("C_BPartner_Location_ID", ii);
				}

				// Contact
				ii = Integer.valueOf(rs.getInt("AD_User_ID"));
				if (rs.wasNull()) {
					mTab.setValue("AD_User_ID", null);
				} else {
					mTab.setValue("AD_User_ID", ii);
				}

				// CreditAvailable
				final double CreditAvailable = rs.getDouble("CreditAvailable");

				if (!rs.wasNull() && CreditAvailable < 0) {
					mTab.fireDataStatusEEvent("CreditLimitOver",
							DisplayType.getNumberFormat(DisplayType.Amount)
									.format(CreditAvailable), false);
				}

			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
			return e.getLocalizedMessage();
		} finally {
			DB.close(rs, pstmt);
		}

		return "";
	} // bpartner

	/**
	 * M_Warehouse. Set Organization and Default Locator
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return error message or ""
	 */
	public String warehouse(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (isCalloutActive()) {
			return "";
		}

		Integer M_Warehouse_ID = null;

		if (value instanceof BigDecimal) {
			M_Warehouse_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			M_Warehouse_ID = (Integer) value;
		}

		if (M_Warehouse_ID == null || M_Warehouse_ID.intValue() == 0) {
			return "";
		}

		setCalloutActive(true);

		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT w.AD_Org_ID, l.M_Locator_ID ")
				.append("FROM M_Warehouse w ")
				.append("LEFT OUTER JOIN M_Locator l ON (l.M_Warehouse_ID=w.M_Warehouse_ID AND l.IsDefault='Y') ")
				.append("WHERE w.M_Warehouse_ID = ?");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, M_Warehouse_ID.intValue());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// Org
				Integer ii = Integer.valueOf(rs.getInt(1));
				int AD_Org_ID = Env.getContextAsInt(ctx, WindowNo, "AD_Org_ID");
				if (AD_Org_ID != ii.intValue()) {
					mTab.setValue("AD_Org_ID", ii);
				}
				// Locator
				ii = Integer.valueOf(rs.getInt(2));
				if (rs.wasNull()) {
					Env.setContext(ctx, WindowNo, 0, "M_Locator_ID", null);
				} else {
					log.config("M_Locator_ID=" + ii);
					Env.setContext(ctx, WindowNo, "M_Locator_ID", ii.intValue());
				}
			}
		} catch (SQLException e) {

			log.log(Level.SEVERE, sql.toString(), e);
			setCalloutActive(false);
			return e.getLocalizedMessage();

		} finally {
			DB.close(rs, pstmt);
		}

		setCalloutActive(false);
		return "";
	} // warehouse

	/**************************************************************************
	 * OrderLine Callout
	 * 
	 * @param ctx
	 *            context
	 * @param WindowNo
	 *            window no
	 * @param mTab
	 *            tab model
	 * @param mField
	 *            field model
	 * @param value
	 *            new value
	 * @return error message or ""
	 */
	public String orderLine(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		Integer C_OrderLine_ID = null;

		if (value instanceof BigDecimal) {
			C_OrderLine_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			C_OrderLine_ID = (Integer) value;
		}

		if (C_OrderLine_ID == null || C_OrderLine_ID.intValue() == 0) {
			return "";
		}

		setCalloutActive(true);

		// Get Details
		final MOrderLine ol = new MOrderLine(ctx, C_OrderLine_ID.intValue(),
				null);
		if (ol.get_ID() != 0) {

			mTab.setValue("M_Product_ID", new Integer(ol.getM_Product_ID()));
			mTab.setValue("M_AttributeSetInstance_ID",
					new Integer(ol.getM_AttributeSetInstance_ID()));
			//
			mTab.setValue("C_UOM_ID", new Integer(ol.getC_UOM_ID()));
			BigDecimal MovementQty = ol.getQtyOrdered().subtract(
					ol.getQtyDelivered());
			mTab.setValue("MovementQty", MovementQty);
			BigDecimal QtyEntered = MovementQty;

			if (ol.getQtyEntered().compareTo(ol.getQtyOrdered()) != 0) {
				QtyEntered = QtyEntered.multiply(ol.getQtyEntered()).divide(
						ol.getQtyOrdered(), 12, BigDecimal.ROUND_HALF_UP);
			}
			mTab.setValue("QtyEntered", QtyEntered);
			//
			mTab.setValue("C_Activity_ID", new Integer(ol.getC_Activity_ID()));
			mTab.setValue("C_Campaign_ID", new Integer(ol.getC_Campaign_ID()));
			mTab.setValue("C_Project_ID", new Integer(ol.getC_Project_ID()));
			mTab.setValue("C_ProjectPhase_ID",
					new Integer(ol.getC_ProjectPhase_ID()));
			mTab.setValue("C_ProjectTask_ID",
					new Integer(ol.getC_ProjectTask_ID()));
			mTab.setValue("AD_OrgTrx_ID", new Integer(ol.getAD_OrgTrx_ID()));
			mTab.setValue("User1_ID", new Integer(ol.getUser1_ID()));
			mTab.setValue("User2_ID", new Integer(ol.getUser2_ID()));
			mTab.setValue("PriceList", ol.getPriceActual());// Expert ..Twry
			mTab.setValue("PriceLimit", ol.getPriceLimit());// Expert ..Twry

		}

		setCalloutActive(false);
		return "";
	} // orderLine

	/**
	 * M_InOutLine - Default UOM/Locator for Product.
	 * 
	 * @param ctx
	 *            context
	 * @param WindowNo
	 *            window no
	 * @param mTab
	 *            tab model
	 * @param mField
	 *            field model
	 * @param value
	 *            new value
	 * @return error message or ""
	 */
	public String product(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (isCalloutActive()) {
			return "";
		}

		Integer M_Product_ID = null;

		if (value instanceof BigDecimal) {
			M_Product_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			M_Product_ID = (Integer) value;
		}

		if (M_Product_ID == null || M_Product_ID.intValue() == 0) {
			mTab.setValue("PriceActual", BigDecimal.ZERO);
			mTab.setValue("PriceActual_Vol", BigDecimal.ZERO);
			mTab.setValue("QtyEntered", BigDecimal.ZERO);
			mTab.setValue("QtyEntered_Vol", BigDecimal.ZERO);

			return "";
		}

		setCalloutActive(true);

		// se limpian las cantidades
		mTab.setValue("QtyEntered", BigDecimal.ZERO);
		mTab.setValue("QtyEntered_Vol", BigDecimal.ZERO);

//		// Validar que este dentro del charge master
		final MProduct product = new MProduct(ctx, M_Product_ID, null);
//		if (M_Product_ID>0 && MEXMEMejoras.isControlaExistencias(
//				Env.getAD_Client_ID(Env.getCtx()),
//				Env.getAD_Org_ID(Env.getCtx()), null)
//				&& (product.getProdOrg() == null || (product.getProdOrg() != null && product
//						.getProdOrg().getAD_Org_ID() <= 0))) {
//			setCalloutActive(false);
//			return Utilerias.getLabel("msj.ligarProducto");
//		}
//		
//		//Valida que el producto sea almacenable dependiendo de su configuración en Charge Master Card # 1060
//		if (!product.getProdOrg().getM_Product().isStocked()){
//			setCalloutActive(false);
//			return Utilerias.getLabel("El producto no es almacenable");
//		}
		
		// Validar si el producto este a nivel de organización y que sea almacenable
		final String error = MProduct.isValidProductOrg(ctx, M_Product_ID, true);
		if(error!=null){
			mTab.setValue("M_Product_ID", null);
			mTab.setValue("M_Locator_ID", null);
			mTab.setValue("Line", null);
			setCalloutActive(false);
			return error;
		}
		

		// Set Attribute & Locator
		int M_Locator_ID = 0;

		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
				"M_Product_ID") == M_Product_ID.intValue()
				&& Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
						"M_AttributeSetInstance_ID") != 0) {

			mTab.setValue(
					"M_AttributeSetInstance_ID",
					new Integer(Env.getContextAsInt(ctx, Env.WINDOW_INFO,
							Env.TAB_INFO, "M_AttributeSetInstance_ID")));
			M_Locator_ID = Env.getContextAsInt(ctx, Env.WINDOW_INFO,
					Env.TAB_INFO, "M_Locator_ID");

			if (M_Locator_ID != 0) {
				mTab.setValue("M_Locator_ID", Integer.valueOf(M_Locator_ID));
			}

		} else {
			mTab.setValue("M_AttributeSetInstance_ID", null);
		}

		//
		final int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_Warehouse_ID");
		final boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo,
				"IsSOTrx"));

		if (IsSOTrx) {

			setCalloutActive(false);
			return "";

		}

		// Set UOM/Locator/Qty
		mTab.setValue("C_UOM_ID", Integer.valueOf(product.getC_UOM_ID())); // Expert
																			// ..Twry
		mTab.setValue("C_UOMVOLUME_ID",
				Integer.valueOf(product.getC_UOMVolume_ID()));

		BigDecimal QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
		mTab.setValue("MovementQty", QtyEntered);

		if (M_Locator_ID == 0 && product.getM_Locator_ID() != 0) {

			MLocator loc = MLocator.get(ctx, product.getM_Locator_ID());
			if (M_Warehouse_ID == loc.getM_Warehouse_ID()) {
				mTab.setValue("M_Locator_ID",
						new Integer(product.getM_Locator_ID()));
			} else {
				log.fine("No Locator for M_Product_ID=" + M_Product_ID
						+ " and M_Warehouse_ID=" + M_Warehouse_ID);
			}
		} else {
			log.fine("No Locator for M_Product_ID=" + M_Product_ID);
		}

		/**
		 * Cambios por unidades de medida. rsolorzano
		 */
		int inOutID = Integer.valueOf(mTab.get_ValueAsString("M_InOut_ID"));
		MInOut mInOut = new MInOut(Env.getCtx(), inOutID, null);

		// se obtiene el precio
		MProductPrice productPrice = MProductPrice.getProductPrice(
				mInOut.getC_BPartner_ID(), 0, product.getM_Product_ID(),
				IsSOTrx);
		if (productPrice == null) {
			mTab.setValue("PriceActual", BigDecimal.ZERO);
			mTab.setValue("PriceActual_Vol", BigDecimal.ZERO);

		} else {
			mTab.setValue("PriceActual", productPrice.getPriceList());
			mTab.setValue("PriceActual_Vol", productPrice.getPriceList_Vol());

		}

		// Expert: Raul Montemayor --Control de Activos-- Inicio
		MConfigInt configuracion = MConfigInt.get(ctx, null); // Configuracion
																// de interfases
																// para la
																// organizacion
		if (configuracion != null && configuracion.isInterfase_Equipos()) { // Si
																			// tiene
																			// equipos
			String evaluar = "N"; // String para evaluar si hay que despleguar o
									// no
			StringBuilder sql = new StringBuilder()
					.append("SELECT ac.IsCreateAsActive  FROM M_Product ")
					.append("INNER JOIN M_Product_category pc ON (M_Product.M_Product_Category_ID = pc.M_Product_Category_ID) ")
					.append("LEFT JOIN A_Asset_Group ac ON (pc.A_Asset_Group_ID = ac.A_Asset_Group_ID) ")
					.append("WHERE M_Product.M_Product_ID = ?");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = DB.prepareStatement(sql.toString(), null);
				pstmt.setInt(1, product.getM_Product_ID());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					evaluar = rs.getString(1);
				}
				rs.close();
				pstmt.close();
				pstmt = null;
				rs = null;

				mTab.setValue("Desplegar", evaluar);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
					if (rs != null) {
						rs.close();
						rs = null;
					}
					sql = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
				pstmt = null;
			}

		}// Expert: Raul Montemayor Fin

		setCalloutActive(false);
		
		// Se agrea llamado a que calcule el tax según lo que tenga el producto
		// en maestro de productos, ETS 05479. Jesus Cantu 2 Sep 2013.
		// Solo si es una transacción de compra
		return tax(ctx, WindowNo, mTab, mField, value);
		//return "";
	} // product
	
	
	
	/**
	 * Receipt Line - Tax. - basis: Product, Charge, BPartner Location - sets
	 * C_Tax_ID Calles Amount
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
	 */
	public String tax(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		String column = mField.getColumnName();
		if (value == null)
			return "";
		if (steps)
			log.warning("init");

		// Check Product
		int M_Product_ID = 0;
		if ("M_Product_ID".equals(column)) {
			if (value instanceof BigDecimal) {
				M_Product_ID = Integer.valueOf(value.toString());
			} else if (value instanceof Integer) {
				M_Product_ID = ((Integer) value).intValue();
			}
		} else {
			M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		}
		int C_Charge_ID = 0;
		if ("C_Charge_ID".equals(column)) {
			if (value instanceof BigDecimal) {
				C_Charge_ID = Integer.valueOf(value.toString());
			} else if (value instanceof Integer) {
				C_Charge_ID = ((Integer) value).intValue();
			}
		} else {
			C_Charge_ID = Env.getContextAsInt(ctx, WindowNo, "C_Charge_ID");
		}
		log.fine("Product=" + M_Product_ID + ", C_Charge_ID=" + C_Charge_ID);
		if (M_Product_ID == 0 && C_Charge_ID == 0)
			return amt(ctx, WindowNo, mTab, mField, value); //

		// Check Partner Location
		int shipC_BPartner_Location_ID = 0;
		if ("C_BPartner_Location_ID".equals(column)) {
			if (value instanceof BigDecimal) {
				shipC_BPartner_Location_ID = Integer.valueOf(value.toString());
			} else if (value instanceof Integer) {
				shipC_BPartner_Location_ID = ((Integer) value).intValue();
			}
		} else {
			shipC_BPartner_Location_ID = Env.getContextAsInt(ctx, WindowNo,
					"C_BPartner_Location_ID");
		}
		if (shipC_BPartner_Location_ID == 0) {
			return amt(ctx, WindowNo, mTab, mField, value);
		}
		log.fine("Ship BP_Location=" + shipC_BPartner_Location_ID);

		//
		Timestamp billDate = Env.getContextAsDate(ctx, WindowNo, "DateOrdered");
		log.fine("Bill Date=" + billDate);

		Timestamp shipDate = Env
				.getContextAsDate(ctx, WindowNo, "DatePromised");
		log.fine("Ship Date=" + shipDate);

		int AD_Org_ID = Env.getContextAsInt(ctx, WindowNo, "AD_Org_ID");
		log.fine("Org=" + AD_Org_ID);

		int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_Warehouse_ID");
		log.fine("Warehouse=" + M_Warehouse_ID);

		int billC_BPartner_Location_ID = Env.getContextAsInt(ctx, WindowNo,
				"Bill_Location_ID");
		if (billC_BPartner_Location_ID == 0)
			billC_BPartner_Location_ID = shipC_BPartner_Location_ID;
		log.fine("Bill BP_Location=" + billC_BPartner_Location_ID);

		// Tasa de impuesto EXCENTO
		int C_Tax_ID = MTax.getTaxProductVta(ctx,M_Product_ID);
		if(C_Tax_ID==0){
			C_Tax_ID = Tax.get(ctx, M_Product_ID, C_Charge_ID, billDate,
					shipDate, AD_Org_ID, M_Warehouse_ID,
					billC_BPartner_Location_ID, shipC_BPartner_Location_ID,
					"Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx")));
		}
		log.info("Tax ID=" + C_Tax_ID);
		//
		if (C_Tax_ID == 0) {
			mTab.fireDataStatusEEvent(CLogger.retrieveError());
		} else {
			mTab.setValue("C_Tax_ID", Integer.valueOf(C_Tax_ID));
		}
		//
		if (steps)
			log.warning("fini");
		{
			return amt(ctx, WindowNo, mTab, mField, value);
		}
		
	} // tax
	
	/**
	 * Receipt Line - Amount. - called from QtyOrdered, Discount and PriceActual -
	 * calculates Discount or Actual Amount - calculates LineNetAmt - enforces
	 * PriceLimit
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
	 */
	public String amt(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		if (isCalloutActive() || value == null)
			return "";
		setCalloutActive(true);

		if (steps)
			log.warning("init");
		int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_PriceList_ID");
		int StdPrecision = MPriceList.getStandardPrecision(ctx, M_PriceList_ID);
		BigDecimal QtyEntered, QtyOrdered, PriceEntered, PriceActual, PriceLimit, Discount, PriceList;
		// get values
		QtyEntered = mTab.getValue("QtyEntered") == null ? BigDecimal.ZERO
				: (BigDecimal) mTab.getValue("QtyEntered");
		QtyOrdered = mTab.getValue("QtyOrdered") == null ? BigDecimal.ZERO
				: (BigDecimal) mTab.getValue("QtyOrdered");
		log.fine("QtyEntered=" + QtyEntered + ", Ordered=" + QtyOrdered
				+ ", UOM=" + C_UOM_To_ID);
		//
		PriceEntered = mTab.getValue("PriceEntered") == null ? BigDecimal.ZERO
				: (BigDecimal) mTab.getValue("PriceEntered");
		PriceActual = mTab.getValue("PriceActual") == null ? BigDecimal.ZERO
				: (BigDecimal) mTab.getValue("PriceActual");
		Discount = mTab.getValue("Discount") == null ? BigDecimal.ZERO
				: (BigDecimal) mTab.getValue("Discount");
		PriceLimit = mTab.getValue("PriceLimit") == null ? BigDecimal.ZERO
				: (BigDecimal) mTab.getValue("PriceLimit");
		PriceList = mTab.getValue("PriceList") == null ? BigDecimal.ZERO
				: (BigDecimal) mTab.getValue("PriceList");
		log.fine("PriceList=" + PriceList + ", Limit=" + PriceLimit
				+ ", Precision=" + StdPrecision);
		log.fine("PriceEntered=" + PriceEntered + ", Actual=" + PriceActual
				+ ", Discount=" + Discount);

		// Qty changed - recalc price
		if ((mField.getColumnName().equals("QtyOrdered")
				|| mField.getColumnName().equals("QtyEntered") || mField
				.getColumnName().equals("M_Product_ID"))
				&& !"N".equals(Env.getContext(ctx, WindowNo, "DiscountSchema"))) {
			int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo,
					"C_BPartner_ID");
			if (mField.getColumnName().equals("QtyEntered"))
				QtyOrdered = MEXMEUOMConversion.convertProductTo(ctx,
						M_Product_ID, C_UOM_To_ID, QtyEntered, null); // expert
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean IsSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals(
					"Y");
			MProductPricing pp = new MProductPricing(M_Product_ID,
					C_BPartner_ID, QtyOrdered, IsSOTrx);
			pp.setM_PriceList_ID(M_PriceList_ID);
			int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo,
					"M_PriceList_Version_ID");
			pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
			Timestamp date = (Timestamp) mTab.getValue("DateOrdered");
			pp.setPriceDate(date);
			//
			PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx,
					M_Product_ID, C_UOM_To_ID, pp.getPriceStd(), null, false);
			if (PriceEntered == null)
				PriceEntered = pp.getPriceStd();
			//
			log.fine("QtyChanged -> PriceActual=" + pp.getPriceStd()
					+ ", PriceEntered=" + PriceEntered + ", Discount="
					+ pp.getDiscount());
			mTab.setValue("PriceActual", pp.getPriceStd());
			mTab.setValue("Discount", pp.getDiscount());
			mTab.setValue("PriceEntered", PriceEntered);
			Env.setContext(ctx, WindowNo, "DiscountSchema",
					pp.isDiscountSchema() ? "Y" : "N");
		} else if (mField.getColumnName().equals("PriceActual")) {
			PriceActual = (BigDecimal) value;
			PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx,
					M_Product_ID, C_UOM_To_ID, PriceActual, null, false); // expert
			if (PriceEntered == null)
				PriceEntered = PriceActual;
			//
			log.fine("PriceActual=" + PriceActual + " -> PriceEntered="
					+ PriceEntered);
			mTab.setValue("PriceEntered", PriceEntered);
		} else if (mField.getColumnName().equals("PriceEntered")) {
			PriceEntered = (BigDecimal) value;
			PriceActual = MEXMEUOMConversion.convertProductTo(ctx,
					M_Product_ID, C_UOM_To_ID, PriceEntered, null, false); // expert
			if (PriceActual == null)
				PriceActual = PriceEntered;
			//
			log.fine("PriceEntered=" + PriceEntered + " -> PriceActual="
					+ PriceActual);
			mTab.setValue("PriceActual", PriceActual);
		}

		// Discount entered - Calculate Actual/Entered
		if (mField.getColumnName().equals("Discount")) {
			PriceActual = new BigDecimal((100.0 - Discount.doubleValue())
					/ 100.0 * PriceList.doubleValue());
			if (PriceActual.scale() > StdPrecision)
				PriceActual = PriceActual.setScale(StdPrecision,
						BigDecimal.ROUND_HALF_UP);
			PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx,
					M_Product_ID, C_UOM_To_ID, PriceActual, null, false); // expert
			if (PriceEntered == null)
				PriceEntered = PriceActual;
			mTab.setValue("PriceActual", PriceActual);
			mTab.setValue("PriceEntered", PriceEntered);
			if ("PO Line".equals(mTab.getName())) {
				if (PriceActual.compareTo(Env.ZERO) == 0
						&& PriceEntered.compareTo(Env.ZERO) == 0) {
					mTab.setValue("PriceActual_Vol", PriceActual);
					mTab.setValue("PriceEntered_Vol", PriceEntered);
				} else {
					int uomVolume = (Integer) mTab.getValue("C_UOMVolume_ID");
					mTab.setValue("PriceActual_Vol", MEXMEUOMConversion
							.convertProductFrom(Env.getCtx(), M_Product_ID,
									uomVolume, PriceActual, null, false));
					mTab.setValue("PriceEntered_Vol", MEXMEUOMConversion
							.convertProductFrom(Env.getCtx(), M_Product_ID,
									uomVolume, PriceEntered, null, false));
				}
			}
		}
		// calculate Discount
		else {
			if (PriceList == null || PriceList.intValue() == 0) {
				Discount = Env.ZERO;
			} else {
				Discount = new BigDecimal(
						(PriceList.doubleValue() - PriceActual.doubleValue())
								/ PriceList.doubleValue() * 100.0);
			}
			if (Discount.scale() > 2) {
				Discount = Discount.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			mTab.setValue("Discount", Discount);
		}
		log.fine("PriceEntered=" + PriceEntered + ", Actual=" + PriceActual
				+ ", Discount=" + Discount);

		// Check PriceLimit
		String epl = Env.getContext(ctx, WindowNo, "EnforcePriceLimit");
		boolean enforce = Env.isSOTrx(ctx, WindowNo) && epl != null
				&& epl.equals("Y");
		if (enforce && MRole.getDefault().isOverwritePriceLimit())
			enforce = false;
		// Check Price Limit?
		if (enforce && PriceLimit.doubleValue() != 0.0
				&& PriceActual.compareTo(PriceLimit) < 0) {
			PriceActual = PriceLimit;
			PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx,
					M_Product_ID, C_UOM_To_ID, PriceLimit, null, false);
			if (PriceEntered == null)
				PriceEntered = PriceLimit;
			log.fine("(under) PriceEntered=" + PriceEntered + ", Actual"
					+ PriceLimit);
			mTab.setValue("PriceActual", PriceLimit);
			mTab.setValue("PriceEntered", PriceEntered);
			mTab.fireDataStatusEEvent("UnderLimitPrice", "", false);
			// Repeat Discount calc
			if (PriceList.intValue() != 0) {
				Discount = new BigDecimal(
						(PriceList.doubleValue() - PriceActual.doubleValue())
								/ PriceList.doubleValue() * 100.0);
				if (Discount.scale() > 2)
					Discount = Discount.setScale(2, BigDecimal.ROUND_HALF_UP);
				mTab.setValue("Discount", Discount);
			}
		}

		// Line Net Amt
		BigDecimal LineNetAmt = QtyOrdered.multiply(PriceActual);
		if (LineNetAmt.scale() > StdPrecision)
			LineNetAmt = LineNetAmt.setScale(StdPrecision,
					BigDecimal.ROUND_HALF_UP);
		log.info("LineNetAmt=" + LineNetAmt);
		mTab.setValue("LineNetAmt", LineNetAmt);
		//
		setCalloutActive(false);
		return "";
	} // amt

	/**
	 * InOut Line - Quantity. - called from C_UOM_ID, QtyEntered, MovementQty -
	 * enforces qty UOM relationship
	 * 
	 * @param ctx
	 *            context
	 * @param WindowNo
	 *            window no
	 * @param mTab
	 *            tab model
	 * @param mField
	 *            field model
	 * @param value
	 *            new value
	 * @return error message or ""
	 */
	public String qty(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (isCalloutActive() || value == null) {
			return "";
		}

		setCalloutActive(true);

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		// log.log(Level.WARNING,"qty - init - M_Product_ID=" + M_Product_ID);
		BigDecimal MovementQty, QtyEntered;

		// No Product
		if (M_Product_ID == 0) {

			QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
			mTab.setValue("MovementQty", QtyEntered);

		} else if ("C_UOM_ID".equals(mField.getColumnName())) { // UOM Changed -
																// convert from
																// Entered ->
																// Product

			int C_UOM_To_ID = 0;
			if (value instanceof BigDecimal) {
				C_UOM_To_ID = Integer.valueOf(value.toString());
			} else if (value instanceof Integer) {
				C_UOM_To_ID = ((Integer) value).intValue();
			}

			QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
			BigDecimal QtyEntered1 = QtyEntered.setScale(
					MUOM.getPrecision(ctx, C_UOM_To_ID),
					BigDecimal.ROUND_HALF_UP);

			if (QtyEntered.compareTo(QtyEntered1) != 0) {
				log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID
						+ "; QtyEntered=" + QtyEntered + "->" + QtyEntered1);
				QtyEntered = QtyEntered1;
				mTab.setValue("QtyEntered", QtyEntered);
			}

			MovementQty = MEXMEUOMConversion.convertProductFrom(ctx,
					M_Product_ID, C_UOM_To_ID, QtyEntered, null);

			if (MovementQty == null) {
				MovementQty = QtyEntered;
			}

			boolean conversion = QtyEntered.compareTo(MovementQty) != 0;
			log.fine("UOM=" + C_UOM_To_ID + ", QtyEntered=" + QtyEntered
					+ " -> " + conversion + " MovementQty=" + MovementQty);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y"
					: "N");
			mTab.setValue("MovementQty", MovementQty);

		} else if (Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID") == 0) { // No
																			// UOM
																			// defined

			QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
			mTab.setValue("MovementQty", QtyEntered);

		} else if (mField.getColumnName().equals("QtyEntered")) { // QtyEntered
																	// changed -
																	// calculate
																	// MovementQty

			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyEntered = (BigDecimal) value;
			BigDecimal QtyEntered1 = QtyEntered.setScale(
					MUOM.getPrecision(ctx, C_UOM_To_ID),
					BigDecimal.ROUND_HALF_UP);

			if (QtyEntered.compareTo(QtyEntered1) != 0) {
				log.fine("Corrected QtyEntered Scale UOM=" + C_UOM_To_ID
						+ "; QtyEntered=" + QtyEntered + "->" + QtyEntered1);
				QtyEntered = QtyEntered1;
				mTab.setValue("QtyEntered", QtyEntered);
			}

			MovementQty = MEXMEUOMConversion.convertProductFrom(ctx,
					M_Product_ID, C_UOM_To_ID, QtyEntered, null);

			if (MovementQty == null) {
				MovementQty = QtyEntered;
			}

			boolean conversion = QtyEntered.compareTo(MovementQty) != 0;
			log.fine("UOM=" + C_UOM_To_ID + ", QtyEntered=" + QtyEntered
					+ " -> " + conversion + " MovementQty=" + MovementQty);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y"
					: "N");
			mTab.setValue("MovementQty", MovementQty);

		} else if (mField.getColumnName().equals("MovementQty")) { // MovementQty
																	// changed -
																	// calculate
																	// QtyEntered
																	// (should
																	// not
																	// happen)

			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			MovementQty = (BigDecimal) value;
			int precision = MProduct.get(ctx, M_Product_ID).getUOMPrecision();
			BigDecimal MovementQty1 = MovementQty.setScale(precision,
					BigDecimal.ROUND_HALF_UP);
			if (MovementQty.compareTo(MovementQty1) != 0) {
				log.fine("Corrected MovementQty " + MovementQty + "->"
						+ MovementQty1);
				MovementQty = MovementQty1;
				mTab.setValue("MovementQty", MovementQty);
			}

			QtyEntered = MEXMEUOMConversion.convertProductTo(ctx, M_Product_ID,
					C_UOM_To_ID, MovementQty, null);
			if (QtyEntered == null) {
				QtyEntered = MovementQty;
			}

			boolean conversion = MovementQty.compareTo(QtyEntered) != 0;
			log.fine("UOM=" + C_UOM_To_ID + ", MovementQty=" + MovementQty
					+ " -> " + conversion + " QtyEntered=" + QtyEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y"
					: "N");
			mTab.setValue("QtyEntered", QtyEntered);

		}
		//
		setCalloutActive(false);
		return "";

	} // qty

	/**
	 * M_InOutLine - ASI.
	 * 
	 * @param ctx
	 *            context
	 * @param WindowNo
	 *            window no
	 * @param mTab
	 *            tab model
	 * @param mField
	 *            field model
	 * @param value
	 *            new value
	 * @return error message or ""
	 */
	public String asi(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (isCalloutActive()) {
			return "";
		}

		Integer M_ASI_ID = null;
		if (value instanceof BigDecimal) {
			M_ASI_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			M_ASI_ID = (Integer) value;
		}

		if (M_ASI_ID == null || M_ASI_ID.intValue() == 0) {
			return "";
		}

		setCalloutActive(true);

		//
		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_Warehouse_ID");
		int M_Locator_ID = Env.getContextAsInt(ctx, WindowNo, "M_Locator_ID");

		log.fine("M_Product_ID=" + M_Product_ID + ", M_ASI_ID=" + M_ASI_ID
				+ " - M_Warehouse_ID=" + M_Warehouse_ID + ", M_Locator_ID="
				+ M_Locator_ID);

		// Check Selection
		int M_AttributeSetInstance_ID = Env.getContextAsInt(Env.getCtx(),
				Env.WINDOW_INFO, Env.TAB_INFO, "M_AttributeSetInstance_ID");

		if (M_ASI_ID.intValue() == M_AttributeSetInstance_ID) {

			int selectedM_Locator_ID = Env.getContextAsInt(Env.getCtx(),
					Env.WINDOW_INFO, Env.TAB_INFO, "M_Locator_ID");

			if (selectedM_Locator_ID != 0) {
				log.fine("Selected M_Locator_ID=" + selectedM_Locator_ID);
				mTab.setValue("M_Locator_ID", new Integer(selectedM_Locator_ID));
			}
		}

		setCalloutActive(false);
		return "";
	} // asi

	/**
	 * Calcula las conversiones de la cantidad capturada (volumen) a la cantidad
	 * minima
	 * 
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String qtyVol(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {

		if (isCalloutActive() || value == null) {
			mTab.setValue("QtyEntered", BigDecimal.ZERO);
			mTab.setValue("QtyEntered_Vol", BigDecimal.ZERO);
			mTab.setValue("MovementQty", BigDecimal.ZERO);
			mTab.setValue("MovementQty_Vol", BigDecimal.ZERO);
			return "";
		}

		setCalloutActive(true);

		BigDecimal qtyEnteredVol = null;
		BigDecimal qtyEntered = null;

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");

		MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);

		if (mField.getColumnName().equals("QtyEntered_Vol")) {
			if (value instanceof BigDecimal) {
				qtyEnteredVol = (BigDecimal) value;
			} else {
				qtyEnteredVol = BigDecimal.ZERO;
				mTab.setValue("QtyEntered", BigDecimal.ZERO);
				mTab.setValue("MovementQty", BigDecimal.ZERO);
			}

			BigDecimal qtyTmp = qtyEnteredVol.setScale(
					MUOM.getPrecision(ctx, product.getC_UOMVolume_ID()),
					BigDecimal.ROUND_HALF_UP);

			if (qtyEnteredVol.compareTo(qtyTmp) != 0) {
				log.fine("Corrected QtyEntered Scale UOM="
						+ product.getC_UOMVolume_ID() + "; QtyEntered="
						+ qtyEnteredVol + "->" + qtyTmp);
				qtyEnteredVol = qtyTmp;
				mTab.setValue("QtyEntered_Vol", qtyEnteredVol);
				mTab.setValue("MovementQty_Vol", qtyEnteredVol);
			}

			if (product.getC_UOM_ID() == product.getC_UOMVolume_ID()) {
				qtyEntered = qtyEnteredVol.setScale(
						MUOM.getPrecision(ctx, product.getC_UOM_ID()),
						BigDecimal.ROUND_HALF_UP);

				// se valida que solo cantidades positivas o negativas Ealvarez
				boolean negativo = false;
				String msg = null;
				if (qtyEntered.compareTo(Env.ZERO) < 0) {
					negativo = true;
				}

				final StringBuilder sql = new StringBuilder();
				sql.append("SELECT line.movementqty from M_InOutLine line ")
						.append("left join M_InOut On (M_InOut.M_InOut_id = line.M_InOut_id)")
						.append("where line.M_InOut_id = ? ");
				if (negativo) {
					sql.append(" and line.movementqty > 0 ");
				} else {
					sql.append(" and line.movementqty < 0 ");
				}

				try {

					int inOutID = Integer.valueOf(mTab
							.get_ValueAsString("M_InOut_ID"));
					PreparedStatement pstmt = DB.prepareStatement(
							sql.toString(), null);
					pstmt.setInt(1, inOutID);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {

						final int cantidad = rs.getInt("movementqty");
						if (cantidad < 0) {

							msg = Utilerias.getMsg(Env.getCtx(),
									"error.qtyPositive");
						} else {
							msg = Utilerias.getMsg(Env.getCtx(),
									"error.qtyNegative");
						}
						setCalloutActive(false);
						return msg;
					}
					rs.close();
					pstmt.close();
					// expert
					rs = null;
					pstmt = null;
					// expert
				} catch (SQLException e) {
					log.log(Level.SEVERE, sql.toString(), e);
					return e.getLocalizedMessage();
				} // end Ealvarez

				mTab.setValue("QtyEntered", qtyEntered);
				mTab.setValue("MovementQty", qtyEntered);
				mTab.setValue("MovementQty_Vol", qtyEntered);
			} else {
				MUOMConversion rates = MEXMEUOMConversion.validateConversions(
						Env.getCtx(), product.getM_Product_ID(),
						product.getC_UOMVolume_ID(), null);

				if (rates == null) {
					log.saveError(Utilerias.getMsg(Env.getCtx(),
							"error.udm.noExisteConversion"), product.getName());
					String msg = Utilerias.getMsg(Env.getCtx(),
							"error.udm.noExisteConversion");
					mField.setValue(mField.getOldValue(), true);
					mTab.setValue("QtyEntered", BigDecimal.ZERO);
					mTab.setValue("QtyEntered_Vol", BigDecimal.ZERO);
					mTab.setValue("MovementQty", BigDecimal.ZERO);
					mTab.setValue("MovementQty_Vol", BigDecimal.ZERO);

					setCalloutActive(false);
					return msg;
				}

				qtyEntered = MEXMEUOMConversion.convertProductFrom(ctx,
						product.getM_Product_ID(), product.getC_UOMVolume_ID(),
						qtyEnteredVol, null);

				qtyEntered = qtyEntered.setScale(
						MUOM.getPrecision(ctx, product.getC_UOM_ID()),
						BigDecimal.ROUND_HALF_UP);

				mTab.setValue("QtyEntered", qtyEntered);
				mTab.setValue("MovementQty_Vol", qtyEnteredVol);
				mTab.setValue("MovementQty", qtyEntered);

			}
		}
		BigDecimal priceEnteredVol, priceActualVol;
		int cUOMvOLID = product.getC_UOMVolume_ID();

		priceEnteredVol = (BigDecimal) mTab.getValue("PriceEntered_Vol");
		priceActualVol = (BigDecimal) mTab.getValue("PriceActual_Vol");
		if (mField.getColumnName().equals("PriceActual_Vol")) {

			log.fine("PriceActual=" + priceActualVol + " -> PriceEntered="
					+ priceEnteredVol);
			priceEnteredVol = priceActualVol;
			mTab.setValue("PriceEntered_Vol", priceActualVol);
			if (product.getC_UOM_ID() != product.getC_UOMVolume_ID()) {
				mTab.setValue("PriceEntered", MEXMEUOMConversion
						.convertProductTo(Env.getCtx(),
								product.getM_Product_ID(), cUOMvOLID,
								priceActualVol, null, false));
				mTab.setValue("PriceActual", MEXMEUOMConversion
						.convertProductTo(Env.getCtx(),
								product.getM_Product_ID(), cUOMvOLID,
								priceActualVol, null, false));
			} else {
				mTab.setValue("PriceEntered", priceActualVol);
				mTab.setValue("PriceActual", priceActualVol);
			}
		} else if (mField.getColumnName().equals("PriceEntered_Vol")) {
			log.fine("PriceEntered=" + priceEnteredVol + " -> PriceActual="
					+ priceActualVol);
			priceActualVol = priceEnteredVol;
			mTab.setValue("PriceActual_Vol", priceEnteredVol);
			mTab.setValue("PriceEntered", MEXMEUOMConversion.convertProductTo(
					Env.getCtx(), product.getM_Product_ID(), cUOMvOLID,
					priceEnteredVol, null, false));
			mTab.setValue("PriceActual", MEXMEUOMConversion.convertProductTo(
					Env.getCtx(), product.getM_Product_ID(), cUOMvOLID,
					priceEnteredVol, null, false));
		}

		setCalloutActive(false);
		return "";

	} // qtyVol

} // CalloutInOut
