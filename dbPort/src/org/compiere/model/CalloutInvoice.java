/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
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
 * Invoice Callouts
 * 
 * @author Jorg Janke
 * @version $Id: CalloutInvoice.java,v 1.2 2006/08/11 19:52:02 mrojas Exp $
 */
public class CalloutInvoice extends CalloutEngine {

	/**
	 * Invoice Header - DocType. - PaymentRule - temporary Document Context: -
	 * DocSubTypeSO - HasCharges - (re-sets Business Partner info of required)
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

		final StringBuilder sql = new StringBuilder("SELECT ")
				.append(" d.HasCharges,'N'      ")
				.append(" , d.IsDocNoControlled ")
				.append(" , s.CurrentNext       ").append(" , d.DocBaseType ")
				.append(" FROM  C_DocType d, AD_Sequence s ")
				.append(" WHERE d.C_DocType_ID = ? ") // 1
				.append(" AND d.DocNoSequence_ID = s.AD_Sequence_ID(+) ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_DocType_ID.intValue());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// Charges - Set Context
				Env.setContext(ctx, WindowNo, "HasCharges", rs.getString(1));
				// DocumentNo
				if (rs.getString(3).equals("Y")) {
					mTab.setValue(X_C_Invoice.COLUMNNAME_DocumentNo,
							"<" + rs.getString(4) + ">");
				}
				// DocBaseType - Set Context
				final String sDocBaseType = rs.getString(5);
				Env.setContext(ctx, WindowNo, "DocBaseType", sDocBaseType);
				// AP Check & AR Credit Memo
				if (sDocBaseType.startsWith("AP")) {
					mTab.setValue(X_C_Invoice.COLUMNNAME_PaymentRule,
							X_C_Invoice.PAYMENTRULE_Check); // Check

				} else if (sDocBaseType.endsWith("C")) {
					mTab.setValue(X_C_Invoice.COLUMNNAME_PaymentRule,
							X_C_Invoice.PAYMENTRULE_OnCredit); // OnCredit
				}
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
			DB.close(rs, pstmt);
			return e.getLocalizedMessage();
		}
		return "";
	} // docType

	/**
	 * Invoice Header- BPartner. - M_PriceList_ID (+ Context) -
	 * C_BPartner_Location_ID - AD_User_ID - POReference - SO_Description -
	 * IsDiscountPrinted - PaymentRule - C_PaymentTerm_ID
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
	public String bPartner(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer C_BPartner_ID = null;
		if (value instanceof BigDecimal) {
			C_BPartner_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			C_BPartner_ID = (Integer) value;
		}
		if (C_BPartner_ID == null || C_BPartner_ID.intValue() == 0) {
			return "";
		}

		final StringBuilder sql = new StringBuilder("SELECT ")
				.append(" p.AD_Language, l.C_BPartner_Location_ID, c.AD_User_ID, ")
				.append(" NVL(pcte.C_PaymentTerm_ID,      p.C_PaymentTerm_ID) as C_PaymentTerm_ID,")
				.append(" COALESCE(NVL(p.M_PriceList_ID,  p.M_PriceList_ID),")
				.append(" NVL(gcte.M_PriceList_ID,        g.M_PriceList_ID))  as M_PriceList_ID, ")
				.append(" NVL(pcte.PaymentRule,           p.PaymentRule)      as PaymentRule,")
				.append(" NVL(pcte.POReference,           p.POReference)      as POReference,")
				.append(" NVL(pcte.SO_Description,        p.SO_Description)   as SO_Description,")
				.append(" NVL(pcte.IsDiscountPrinted,     p.IsDiscountPrinted) as IsDiscountPrinted,")
				.append(" NVL(pcte.SO_CreditLimit,        p.SO_CreditLimit)   as SO_CreditLimit, ")
				.append(" NVL(pcte.SO_CreditLimit,        p.SO_CreditLimit) - ")
				.append(" NVL(pcte.SO_CreditUsed,         p.SO_CreditUsed)    as CreditAvailable,")
				.append(" COALESCE(p.PO_PriceList_ID,")
				.append(" NVL(gcte.PO_PriceList_ID,       g.PO_PriceList_ID)) as PO_PriceList_ID, ")
				.append(" p.PaymentRulePO,")
				.append(" p.PO_PaymentTerm_ID ")
				.append(" FROM C_BPartner p")
				.append(" LEFT JOIN C_BPartner_Cte pcte ON (p.C_BPartner_ID=pcte.C_BPartner_ID AND pcte.AD_Client_ID = ? )")
				// #1
				.append(" INNER JOIN C_BP_Group g ON (p.C_BP_Group_ID=g.C_BP_Group_ID)")
				.append(" LEFT JOIN C_BP_Group_Cte gcte ON (g.C_BP_Group_ID=gcte.C_BP_Group_ID AND gcte.AD_Client_ID = ? )")
				// #2
				.append(" LEFT OUTER JOIN C_BPartner_Location l ON (p.C_BPartner_ID=l.C_BPartner_ID AND l.IsBillTo='Y' AND l.IsActive='Y')")
				.append(" LEFT OUTER JOIN AD_User c ON (p.C_BPartner_ID=c.C_BPartner_ID) ")
				.append(" WHERE p.C_BPartner_ID=? AND p.IsActive='Y'"); // #3

		final boolean IsSOTrx = Env.getContext(ctx, WindowNo,
				X_C_Invoice.COLUMNNAME_IsSOTrx).equals("Y");

		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
		ResultSet rs = null;

		try {

			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, C_BPartner_ID.intValue());
			rs = pstmt.executeQuery();
			//
			if (rs.next()) {

				// PaymentRule
				String s = rs.getString(IsSOTrx ? "PaymentRule"
						: "PaymentRulePO");
				if (s != null && s.length() != 0) {
					if (Env.getContext(ctx, WindowNo, "DocBaseType").endsWith(
							"C")) { // Credits are Payment Term
						s = "P";
					} else if (IsSOTrx && (s.equals("S") || s.equals("U"))) { // No
						// Check/Transfer
						// for
						// SO_Trx
						s = "P"; // Payment Term
					}
					mTab.setValue("PaymentRule", s);
				}

				// Payment Term
				Integer ii = new Integer(rs.getInt(IsSOTrx ? "C_PaymentTerm_ID"
						: "PO_PaymentTerm_ID"));
				if (!rs.wasNull()) {
					mTab.setValue("C_PaymentTerm_ID", ii);
				}

				// Location
				int locID = rs.getInt("C_BPartner_Location_ID");
				// overwritten by InfoBP selection - works only if InfoWindow
				// was used otherwise creates error (uses last value, may belong
				// to differnt BP)
				if (C_BPartner_ID.toString().equals(
						Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
								"C_BPartner_ID"))) {
					String loc = Env.getContext(ctx, Env.WINDOW_INFO,
							Env.TAB_INFO, "C_BPartner_Location_ID");
					if (loc.length() > 0) {
						locID = Integer.parseInt(loc);
					}
				}
				if (locID == 0) {
					mTab.setValue("C_BPartner_Location_ID", null);
				} else {
					mTab.setValue("C_BPartner_Location_ID", new Integer(locID));
				}

				// Contact - overwritten by InfoBP selection
				int contID = rs.getInt("AD_User_ID");
				if (C_BPartner_ID.toString().equals(
						Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
								"C_BPartner_ID"))) {
					String cont = Env.getContext(ctx, Env.WINDOW_INFO,
							Env.TAB_INFO, "AD_User_ID");
					if (cont.length() > 0) {
						contID = Integer.parseInt(cont);
					}
				}
				if (contID == 0) {
					mTab.setValue("AD_User_ID", null);
				} else {
					mTab.setValue("AD_User_ID", new Integer(contID));
				}

				// CreditAvailable
				if (IsSOTrx) {
					double CreditLimit = rs.getDouble("SO_CreditLimit");
					if (CreditLimit != 0) {
						double CreditAvailable = rs
								.getDouble("CreditAvailable");
						if (!rs.wasNull() && CreditAvailable < 0) {
							mTab.fireDataStatusEEvent(
									"CreditLimitOver",
									DisplayType.getNumberFormat(
											DisplayType.Amount).format(
											CreditAvailable), false);
						}
					}
				}

				// PO Reference
				s = rs.getString("POReference");
				if (s != null && s.length() != 0) {
					mTab.setValue("POReference", s);
				} else {
					mTab.setValue("POReference", null);
				}

				// SO Description
				s = rs.getString("SO_Description");
				if (s != null && s.trim().length() != 0) {
					mTab.setValue("Description", s);
				}
				// IsDiscountPrinted
				s = rs.getString("IsDiscountPrinted");
				if (s != null && s.length() != 0) {
					mTab.setValue("IsDiscountPrinted", s);
				} else {
					mTab.setValue("IsDiscountPrinted", "N");
				}
			}
			// expert
		} catch (SQLException e) {
			log.log(Level.SEVERE, "bPartner", e);
			return e.getLocalizedMessage();
		} finally {
			DB.close(rs, pstmt);
		}

		return "";
	} // bPartner

	/**
	 * Set Payment Term. Payment Term has changed
	 */
	public String paymentTerm(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer C_PaymentTerm_ID = null;
		if (value instanceof BigDecimal)
			C_PaymentTerm_ID = Integer.valueOf(value.toString());
		else if (value instanceof Integer)
			C_PaymentTerm_ID = (Integer) value;
		int C_Invoice_ID = Env.getContextAsInt(ctx, WindowNo, "C_Invoice_ID");
		if (C_PaymentTerm_ID == null || C_PaymentTerm_ID.intValue() == 0
				|| C_Invoice_ID == 0) // not saved yet
			return "";
		//
		MPaymentTerm pt = new MPaymentTerm(ctx, C_PaymentTerm_ID.intValue(),
				null);
		if (pt.get_ID() == 0)
			return "PaymentTerm not found";

		boolean valid = pt.apply(C_Invoice_ID);
		mTab.setValue("IsPayScheduleValid", valid ? "Y" : "N");

		return "";
	} // paymentTerm

	/**************************************************************************
	 * Invoice Line - Product. - reset C_Charge_ID / M_AttributeSetInstance_ID -
	 * PriceList, PriceStd, PriceLimit, C_Currency_ID, EnforcePriceLimit - UOM
	 * Calls Tax
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
	public String product(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer M_Product_ID = null;
		if (value instanceof BigDecimal){
			M_Product_ID = Integer.valueOf(value.toString());
		}
		else if (value instanceof Integer){
			M_Product_ID = (Integer) value;
		}
		if (M_Product_ID == null || M_Product_ID.intValue() == 0) {
			mTab.setValue("QtyEntered", BigDecimal.ZERO);
			mTab.setValue("QtyEntered_Vol", BigDecimal.ZERO);
			return "";
		}

		setCalloutActive(true);
		mTab.setValue("C_Charge_ID", null);

		// Validar si el producto este a nivel de organización
		final String error = MProduct.isValidProductOrg(ctx, M_Product_ID,
				false);
		if (error != null) {
			mTab.setValue("M_Product_ID", null);
			mTab.setValue("M_Locator_ID", null);
			mTab.setValue("Line", null);
			setCalloutActive(false);
			return error;
		}

		// Set Attribute
		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
				"M_Product_ID") == M_Product_ID.intValue()
				&& Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
						"M_AttributeSetInstance_ID") != 0){
			mTab.setValue(
					"M_AttributeSetInstance_ID",
					new Integer(Env.getContextAsInt(ctx, Env.WINDOW_INFO,
							Env.TAB_INFO, "M_AttributeSetInstance_ID")));
		} else {
			mTab.setValue("M_AttributeSetInstance_ID", null);
		}

		/***** Price Calculation see also qty ****/
		boolean IsSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, WindowNo,
				"C_BPartner_ID");
		BigDecimal Qty = (BigDecimal) mTab.getValue("QtyInvoiced");
		MProductPricing pp = new MProductPricing(M_Product_ID.intValue(),
				C_BPartner_ID, Qty, IsSOTrx);
		//
		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_PriceList_ID");
		pp.setM_PriceList_ID(M_PriceList_ID);
		int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_PriceList_Version_ID");
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		Timestamp date = Env.getContextAsDate(ctx, WindowNo, "DateInvoiced");
		pp.setPriceDate(date);
		//
		mTab.setValue("PriceList", pp.getPriceList());
		mTab.setValue("PriceLimit", pp.getPriceLimit());
		mTab.setValue("PriceActual", pp.getPriceStd());
		mTab.setValue("PriceEntered", pp.getPriceStd());
		mTab.setValue("C_Currency_ID", new Integer(pp.getC_Currency_ID()));
		// mTab.setValue("Discount", pp.getDiscount());
		mTab.setValue("C_UOM_ID", new Integer(pp.getC_UOM_ID()));
		Env.setContext(ctx, WindowNo, "EnforcePriceLimit",
				pp.isEnforcePriceLimit() ? "Y" : "N");
		Env.setContext(ctx, WindowNo, "DiscountSchema",
				pp.isDiscountSchema() ? "Y" : "N");
		//
		setCalloutActive(false);
		return tax(ctx, WindowNo, mTab, mField, value);
	} // product

	/**
	 * Invoice Line - Charge. - updates PriceActual from Charge - sets
	 * PriceLimit, PriceList to zero Calles tax
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
	public String charge(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer C_Charge_ID = null;
		if (value instanceof BigDecimal)
			C_Charge_ID = Integer.valueOf(value.toString());
		else if (value instanceof Integer)
			C_Charge_ID = (Integer) value;
		if (C_Charge_ID == null || C_Charge_ID.intValue() == 0)
			return "";

		// No Product defined
		if (mTab.getValue("M_Product_ID") != null) {
			mTab.setValue("C_Charge_ID", null);
			return "ChargeExclusively";
		}
		mTab.setValue("M_AttributeSetInstance_ID", null);
		mTab.setValue("S_ResourceAssignment_ID", null);
		mTab.setValue("C_UOM_ID", new Integer(100)); // EA

		Env.setContext(ctx, WindowNo, "DiscountSchema", "N");
		String sql = "SELECT ChargeAmt FROM C_Charge WHERE C_Charge_ID=?";
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_Charge_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				mTab.setValue("PriceEntered", rs.getBigDecimal(1));
				mTab.setValue("PriceActual", rs.getBigDecimal(1));
				mTab.setValue("PriceLimit", Env.ZERO);
				mTab.setValue("PriceList", Env.ZERO);
				mTab.setValue("Discount", Env.ZERO);
			}
			rs.close();
			pstmt.close();
			// expert
			rs = null;
			pstmt = null;
			// expert
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql + e);
			return e.getLocalizedMessage();
		}
		//
		return tax(ctx, WindowNo, mTab, mField, value);
	} // charge

	/**
	 * Invoice Line - Tax. - basis: Product, Charge, BPartner Location - sets
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

		// Check Product
		int M_Product_ID = 0;
		if (column.equals("M_Product_ID")) {
			if (value instanceof BigDecimal)
				M_Product_ID = Integer.valueOf(value.toString());
			else if (value instanceof Integer)
				M_Product_ID = ((Integer) value).intValue();
		} else {
			M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		}

		int C_Charge_ID = 0;
		if (column.equals("C_Charge_ID")) {
			if (value instanceof BigDecimal)
				C_Charge_ID = Integer.valueOf(value.toString());
			else if (value instanceof Integer)
				C_Charge_ID = ((Integer) value).intValue();
		} else {
			C_Charge_ID = Env.getContextAsInt(ctx, WindowNo, "C_Charge_ID");
		}
		log.fine("Product=" + M_Product_ID + ", C_Charge_ID=" + C_Charge_ID);
		if (M_Product_ID == 0 && C_Charge_ID == 0)
			return amt(ctx, WindowNo, mTab, mField, value); //

		// Check Partner Location
		int shipC_BPartner_Location_ID = Env.getContextAsInt(ctx, WindowNo,
				"C_BPartner_Location_ID");
		if (shipC_BPartner_Location_ID == 0)
			return amt(ctx, WindowNo, mTab, mField, value); //
		log.fine("Ship BP_Location=" + shipC_BPartner_Location_ID);
		int billC_BPartner_Location_ID = shipC_BPartner_Location_ID;
		log.fine("Bill BP_Location=" + billC_BPartner_Location_ID);

		// Dates
		Timestamp billDate = Env
				.getContextAsDate(ctx, WindowNo, "DateInvoiced");
		log.fine("Bill Date=" + billDate);
		Timestamp shipDate = billDate;
		log.fine("Ship Date=" + shipDate);

		int AD_Org_ID = Env.getContextAsInt(ctx, WindowNo, "AD_Org_ID");
		log.fine("Org=" + AD_Org_ID);

		int M_Warehouse_ID = Env.getContextAsInt(ctx, "#M_Warehouse_ID");
		log.fine("Warehouse=" + M_Warehouse_ID);

		// Tasa de impuesto EXCENTO
		int C_Tax_ID = MTax.getTaxProductVta(ctx, M_Product_ID);
		if (C_Tax_ID == 0) {
			C_Tax_ID = Tax.get(ctx, M_Product_ID, C_Charge_ID, billDate,
					shipDate, AD_Org_ID, M_Warehouse_ID,
					billC_BPartner_Location_ID, shipC_BPartner_Location_ID, Env
							.getContext(ctx, WindowNo, "IsSOTrx").equals("Y"));
		}
		log.info("Tax ID=" + C_Tax_ID);
		//
		if (C_Tax_ID == 0)
			mTab.fireDataStatusEEvent(CLogger.retrieveError());
		else
			mTab.setValue("C_Tax_ID", new Integer(C_Tax_ID));
		//
		return amt(ctx, WindowNo, mTab, mField, value);
	} // tax

	/**
	 * Invoice - Amount. - called from QtyInvoiced, PriceActual - calculates
	 * LineNetAmt
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
		if (isCalloutActive() || value == null){
			return "";
		}
		
		setCalloutActive(true);
		getImpuestos(ctx, WindowNo, mTab, mField, (BigDecimal)mTab.getValue("LineNetAmt"));
		setCalloutActive(false);
		return "";
	}
	
	/**
	 * Invoice - Amount. - called from QtyInvoiced, PriceActual - calculates
	 * LineNetAmt
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
	public String amtEnCasoDeUtilizarce(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		if (isCalloutActive() || value == null){
			return "";
		}
		setCalloutActive(true);
		final BigDecimal PriceLimit = (BigDecimal) mTab.getValue("PriceLimit");

		// Qty changed - recalc price
		if ((mField.getColumnName().equals("QtyInvoiced")
				|| mField.getColumnName().equals("QtyEntered") 
				|| mField.getColumnName().equals("M_Product_ID"))
				&& !"N".equals(Env.getContext(ctx, WindowNo, "DiscountSchema"))) {
			getUpdatePriceActual(ctx, WindowNo, mTab, mField);
			
		} else if (mField.getColumnName().equals("PriceActual")) {
			getUpdatePriceEntered(ctx, WindowNo, mTab, mField, (BigDecimal) value);
			
		} else if (mField.getColumnName().equals("PriceEntered")) {
			getUpdatePriceActual(ctx, WindowNo, mTab, value);
		}

		// Check PriceLimit
		String epl = Env.getContext(ctx, WindowNo, "EnforcePriceLimit");
		boolean enforce = Env.isSOTrx(ctx, WindowNo) && epl != null&& epl.equals("Y");
		
		if (enforce && MRole.getDefault().isOverwritePriceLimit()){
			enforce = false;
		}
		
		// Check Price Limit?
		if (enforce 
				&& PriceLimit.doubleValue() != 0.0
				&& ((BigDecimal) mTab.getValue("PriceActual")).compareTo(PriceLimit) < 0) {
			getUpdatePriceLimit(ctx, WindowNo, mTab, PriceLimit);
		}

		getImpuestos(ctx, WindowNo, mTab, mField, getLineNetAmt(ctx, WindowNo, mTab) );
		setCalloutActive(false);
		return "";
	} // amt
	
	
//	/**
//	 * Invoice - Amount. - called from QtyInvoiced, PriceActual - calculates
//	 * LineNetAmt
//	 * 
//	 * @param ctx
//	 *            Context
//	 * @param WindowNo
//	 *            current Window No
//	 * @param mTab
//	 *            Model Tab
//	 * @param mField
//	 *            Model Field
//	 * @param value
//	 *            The new value
//	 */
//	public String amt(Properties ctx, int WindowNo, GridTab mTab,
//			GridField mField, Object value) {
//		if (isCalloutActive() || value == null){
//			return "";
//		}
//		setCalloutActive(true);
//
//		// log.log(Level.WARNING,"amt - init");
////		int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
////		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
////		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_ID");
////		int StdPrecision = MPriceList.getStandardPrecision(ctx, Env.getContextAsInt(ctx, WindowNo, "M_PriceList_ID"));
//		BigDecimal /*QtyEntered, QtyInvoiced, PriceEntered, PriceActual,*/ PriceLimit/*, Discount, PriceList*/;
//		// get values
////		QtyEntered = mTab.getValue("QtyEntered") == null ? BigDecimal.ZERO
////				: (BigDecimal) mTab.getValue("QtyEntered");
////		QtyInvoiced = mTab.getValue("QtyInvoiced") == null ? BigDecimal.ZERO
////				: (BigDecimal) mTab.getValue("QtyInvoiced");
////		log.fine("QtyEntered=" + QtyEntered + ", Invoiced=" + QtyInvoiced
////				+ ", UOM=" + C_UOM_To_ID);
////		//
////		PriceEntered = (BigDecimal) mTab.getValue("PriceEntered");
////		PriceActual = (BigDecimal) mTab.getValue("PriceActual");
//		// Discount = (BigDecimal)mTab.getValue("Discount");
//		PriceLimit = (BigDecimal) mTab.getValue("PriceLimit");
////		PriceList = (BigDecimal) mTab.getValue("PriceList");
////		log.fine("PriceList=" + PriceList + ", Limit=" + PriceLimit
////				+ ", Precision=" + StdPrecision);
////		log.fine("PriceEntered=" + PriceEntered + ", Actual=" + PriceActual);// +
//		// ", Discount="
//		// +
//		// Discount);
//
//		// Qty changed - recalc price
//		if ((mField.getColumnName().equals("QtyInvoiced")
//				|| mField.getColumnName().equals("QtyEntered") 
//				|| mField.getColumnName().equals("M_Product_ID"))
//				&& !"N".equals(Env.getContext(ctx, WindowNo, "DiscountSchema"))) {
////			int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo,
////					"C_BPartner_ID");
////			if (mField.getColumnName().equals("QtyEntered")){
////				QtyInvoiced = MUOMConversion.convertProductTo(ctx,
////						M_Product_ID, C_UOM_To_ID, QtyEntered);
////			}
////			if (QtyInvoiced == null){
////				QtyInvoiced = QtyEntered;
////			}
////			boolean IsSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
////			final MProductPricing pp = new MProductPricing(M_Product_ID,
////					C_BPartner_ID, QtyInvoiced, IsSOTrx);
////			pp.setM_PriceList_ID(M_PriceList_ID);
////			final int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo,
////					"M_PriceList_Version_ID");
////			pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
////			final Timestamp date = (Timestamp) mTab.getValue("DateInvoiced");
////			pp.setPriceDate(date);
////			//
////			PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx,
////					M_Product_ID, C_UOM_To_ID, pp.getPriceStd(), null, false);
////			if (PriceEntered == null){
////				PriceEntered = pp.getPriceStd();
////			}
////			//
////			log.fine("amt - QtyChanged -> PriceActual=" + pp.getPriceStd()
////					+ ", PriceEntered=" + PriceEntered + ", Discount="
////					+ pp.getDiscount());
////			mTab.setValue("PriceActual", pp.getPriceStd());
////			// mTab.setValue("Discount", pp.getDiscount());
////			mTab.setValue("PriceEntered", PriceEntered);
////			Env.setContext(ctx, WindowNo, "DiscountSchema",
////					pp.isDiscountSchema() ? "Y" : "N");
//			getUpdatePriceActual(ctx, WindowNo, mTab, mField);
//			
//		} else if (mField.getColumnName().equals("PriceActual")) {
////			PriceActual = (BigDecimal) value;
////			PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx,
////					M_Product_ID, C_UOM_To_ID, PriceActual, null, false);
////			if (PriceEntered == null)
////				PriceEntered = PriceActual;
////			//
////			log.fine("amt - PriceActual=" + PriceActual + " -> PriceEntered="
////					+ PriceEntered);
////			mTab.setValue("PriceEntered", PriceEntered);
//			getUpdatePriceEntered(ctx, WindowNo, mTab, mField, (BigDecimal) value);
//			
//		} else if (mField.getColumnName().equals("PriceEntered")) {
////			PriceEntered = (BigDecimal) value;
////			//PriceList = (BigDecimal) value;
////			PriceActual = MEXMEUOMConversion.convertProductTo(ctx,M_Product_ID, C_UOM_To_ID, PriceEntered, null, false);
////			
////			if (PriceActual == null){
////				PriceActual = PriceEntered;
////			}
////
////			/*
////			 * Mloera: Ticket 932 Estatus no balanceado: se actualiza el campo
////			 * precio de lista con el mismo valor del campo precio. Esto con el
////			 * fin de que al postear no muestre el error de estatus no
////			 * balanceado.
////			 */
////			//PriceList = PriceEntered;
////			if(PriceList.compareTo(Env.ZERO)==0){
////				mTab.setValue("PriceList", PriceList);
////			}
////			//
////			log.fine("amt - PriceEntered=" + PriceEntered + " -> PriceActual="+ PriceActual);
////			mTab.setValue("PriceActual", PriceActual);
//			
//			getUpdatePriceActual(ctx, WindowNo, mTab, value);
//		}
//
//		/**
//		 * Discount entered - Calculate Actual/Entered if
//		 * (mField.getColumnName().equals("Discount")) { PriceActual = new
//		 * BigDecimal ((100.0 - Discount.doubleValue()) / 100.0 *
//		 * PriceList.doubleValue()); if (PriceActual.scale() > StdPrecision)
//		 * PriceActual = PriceActual.setScale(StdPrecision,
//		 * BigDecimal.ROUND_HALF_UP); PriceEntered =
//		 * MUOMConversion.convertProductFrom (ctx, M_Product_ID, C_UOM_To_ID,
//		 * PriceActual); if (PriceEntered == null) PriceEntered = PriceActual;
//		 * mTab.setValue("PriceActual", PriceActual);
//		 * mTab.setValue("PriceEntered", PriceEntered); } // calculate Discount
//		 * else { if (PriceList.intValue() == 0) Discount = Env.ZERO; else
//		 * Discount = new BigDecimal ((PriceList.doubleValue() -
//		 * PriceActual.doubleValue()) / PriceList.doubleValue() * 100.0); if
//		 * (Discount.scale() > 2) Discount = Discount.setScale(2,
//		 * BigDecimal.ROUND_HALF_UP); mTab.setValue("Discount", Discount); }
//		 * log.fine("amt = PriceEntered=" + PriceEntered + ", Actual" +
//		 * PriceActual + ", Discount=" + Discount); /*
//		 */
//
//		// Check PriceLimit
//		String epl = Env.getContext(ctx, WindowNo, "EnforcePriceLimit");
//		boolean enforce = Env.isSOTrx(ctx, WindowNo) && epl != null&& epl.equals("Y");
//		
//		if (enforce && MRole.getDefault().isOverwritePriceLimit()){
//			enforce = false;
//		}
//		
//		// Check Price Limit?
//		if (enforce 
//				&& PriceLimit.doubleValue() != 0.0
//				&& ((BigDecimal) mTab.getValue("PriceActual")).compareTo(PriceLimit) < 0) {
//			getUpdatePriceLimit(ctx, WindowNo, mTab, PriceLimit);
////			PriceActual = PriceLimit;
////			PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx,M_Product_ID, C_UOM_To_ID, PriceLimit, null, false);
////			
////			if (PriceEntered == null){
////				PriceEntered = PriceLimit;
////			}
////			log.fine("amt =(under) PriceEntered=" + PriceEntered + ", Actual"+ PriceLimit);
////			
////			mTab.setValue("PriceActual", PriceLimit);
////			mTab.setValue("PriceEntered", PriceEntered);
////			mTab.fireDataStatusEEvent("UnderLimitPrice", "", false);
////			
////			// Repeat Discount calc
////			if (PriceList.intValue() != 0) {
////				Discount = new BigDecimal(
////						(PriceList.doubleValue() - PriceActual.doubleValue())
////								/ PriceList.doubleValue() * 100.0);
////				if (Discount.scale() > 2)
////					Discount = Discount.setScale(2, BigDecimal.ROUND_HALF_UP);
////				// mTab.setValue ("Discount", Discount);
////			}
//		}
//
////		// Line Net Amt
////		BigDecimal qtyInvoicedVol = (BigDecimal) mTab
////				.getValue("QtyInvoiced_Vol");
////		if (qtyInvoicedVol == null) {// Null
////			qtyInvoicedVol = Env.ZERO;
////		}
////
////		// Correccion de Error en log 6 Nov 2013.
////		// Jesus Cantu
////		PriceActual = (BigDecimal) mTab.getValue("PriceActual");
////		if (PriceActual == null) {
////			PriceActual = Env.ZERO;
////		}
////
////		BigDecimal LineNetAmt = qtyInvoicedVol.multiply(PriceActual);
////		// BigDecimal LineNetAmt = QtyInvoiced.multiply(PriceActual);
////		if (LineNetAmt.scale() > StdPrecision){
////			LineNetAmt = LineNetAmt.setScale(StdPrecision,BigDecimal.ROUND_HALF_UP);
////		}
//		final BigDecimal LineNetAmt = getLineNetAmt(ctx, WindowNo, mTab);
//		
////		log.info("amt = LineNetAmt=" + LineNetAmt);
////		mTab.setValue("LineNetAmt", LineNetAmt);
//
////		// Calculate Tax Amount for PO
////		boolean IsSOTrx = "Y".equals(Env.getContext(Env.getCtx(), WindowNo,"IsSOTrx"));
////		if (!IsSOTrx) {
////			BigDecimal TaxAmt = null;
////			if (mField.getColumnName().equals("TaxAmt")) {
////				TaxAmt = (BigDecimal) mTab.getValue("TaxAmt");
////			
////			} else {
////				Integer taxID = (Integer) mTab.getValue("C_Tax_ID");
////				if (taxID != null) {
////					int C_Tax_ID = taxID.intValue();
////					MTax tax = new MTax(ctx, C_Tax_ID, null);
////					TaxAmt = tax.calculateTax(LineNetAmt,
////							isTaxIncluded(WindowNo), StdPrecision);
////					mTab.setValue("TaxAmt", TaxAmt);
////				}
////			}
////			// Add it up
////			mTab.setValue("LineTotalAmt", LineNetAmt.add(TaxAmt));
////		}
//		getImpuestos(ctx, WindowNo, mTab, mField, LineNetAmt );
//		setCalloutActive(false);
//		return "";
//	} // amt

	/**
	 * Is Tax Included
	 * 
	 * @param WindowNo
	 *            window no
	 * @return tax included (default: false)
	 */
	private boolean isTaxIncluded(final int WindowNo) {
		String ss = Env.getContext(Env.getCtx(), WindowNo, "IsTaxIncluded");
		// Not Set Yet
		if (ss.length() == 0) {
			final int M_PriceList_ID = Env.getContextAsInt(Env.getCtx(), WindowNo,"M_PriceList_ID");
			if (M_PriceList_ID == 0){
				return false;
			}
			
			ss = DB.getSQLValueString(
					null,
					"SELECT IsTaxIncluded FROM M_PriceList WHERE M_PriceList_ID=?",
					M_PriceList_ID);
			if (ss == null){
				ss = "N";
			}
			Env.setContext(Env.getCtx(), WindowNo, "IsTaxIncluded", ss);
		}
		return "Y".equals(ss);
	} // isTaxIncluded

	/**
	 * Invoice Line - Quantity. - called from C_UOM_ID, QtyEntered, QtyInvoiced
	 * - enforces qty UOM relationship
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
	public String qty(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		
		if (isCalloutActive() || value == null) {
			return "";
		}
		
		setCalloutActive(true);
		
		int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		boolean conversion = false;
		// log.log(Level.WARNING,"qty - init - M_Product_ID=" + M_Product_ID);
		BigDecimal QtyInvoiced = Env.ZERO; 
		BigDecimal QtyEntered = Env.ZERO;/*, PriceActual, PriceEntered*/;

		// No Product
		if (M_Product_ID == 0) {
			QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
			mTab.setValue("QtyInvoiced", QtyEntered);
			
		} else if (mField.getColumnName().equals("C_UOM_ID")) {// UOM Changed - convert from Entered -> Product
			C_UOM_To_ID = 0;
			if (value instanceof BigDecimal){
				C_UOM_To_ID = Integer.valueOf(value.toString());
			} else if (value instanceof Integer) {
				C_UOM_To_ID = ((Integer) value).intValue();
			}
			
			QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
			QtyInvoiced = MUOMConversion.convertProductFrom(ctx, M_Product_ID,C_UOM_To_ID, QtyEntered);
			
			if (QtyInvoiced == null){
				QtyInvoiced = QtyEntered;
			}
			
			conversion = QtyEntered.compareTo(QtyInvoiced) != 0;
//			PriceActual = (BigDecimal) mTab.getValue("PriceActual");
//			PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx,
//					M_Product_ID, C_UOM_To_ID, PriceActual, null, false);
//			if (PriceEntered == null)
//				PriceEntered = PriceActual;
//			log.fine("qty - UOM=" + C_UOM_To_ID + ", QtyEntered/PriceActual="
//					+ QtyEntered + "/" + PriceActual + " -> " + conversion
//					+ " QtyInvoiced/PriceEntered=" + QtyInvoiced + "/"
//					+ PriceEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y"
					: "N");
			mTab.setValue("QtyInvoiced", QtyInvoiced);
//			mTab.setValue("PriceEntered", PriceEntered);
		
		} else if (mField.getColumnName().equals("QtyEntered")) {// QtyEntered changed - calculate QtyInvoiced
//			C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyEntered = (BigDecimal) value;
			QtyInvoiced = MUOMConversion.convertProductFrom(ctx, M_Product_ID,C_UOM_To_ID, QtyEntered);
			if (QtyInvoiced == null){
				QtyInvoiced = QtyEntered;
			}
			conversion = QtyEntered.compareTo(QtyInvoiced) != 0;
			
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y"
					: "N");
			mTab.setValue("QtyInvoiced", QtyInvoiced);
			
		} else if (mField.getColumnName().equals("QtyInvoiced")) {// QtyInvoiced changed - calculate QtyEntered
//			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyInvoiced = (BigDecimal) value;
			QtyEntered = MUOMConversion.convertProductTo(ctx, M_Product_ID,
					C_UOM_To_ID, QtyInvoiced);
			if (QtyEntered == null){
				QtyEntered = QtyInvoiced;
			}
			conversion = QtyInvoiced.compareTo(QtyEntered) != 0;
//			log.fine("qty - UOM=" + C_UOM_To_ID + ", QtyInvoiced="
//					+ QtyInvoiced + " -> " + conversion + " QtyEntered="
//					+ QtyEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y"
					: "N");
			mTab.setValue("QtyEntered", QtyEntered);
		}
		
		log.fine("qty - UOM=" + C_UOM_To_ID +
				", QtyInvoiced=" + QtyInvoiced + 
				", QtyEntered=" + QtyEntered +
				" -> " + conversion );
		//
		setCalloutActive(false);
		return "";
	} // qty

	/**
	 * Mloera: Invoice Line - Actualiza Precio con el valor de Precio de Lista.
	 * - called from PriceList
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
	public String actualizaPrecio(final Properties ctx, final int WindowNo, final GridTab mTab, final GridField mField, final Object value) {

//		if (isCalloutActive() || value == null){
//			return "";
//		}
//
//		setCalloutActive(true);
//
//		BigDecimal PriceList, PriceEntered = null;
//		GridField mFieldPriceEntered = null;
//		if (mField.getColumnName().equals("PriceList")) {
//			PriceEntered = (BigDecimal) mTab.getValue("PriceEntered");
//			PriceList = (BigDecimal) mTab.getValue("PriceList");
//
//			/*
//			 * Mloera: Ticket 932 Estatus no balanceado: se actualiza el campo
//			 * precio con el mismo valor del campo precio lista. Esto con el fin
//			 * de que al postear no muestre el error de estatus no balanceado.
//			 */
//			PriceEntered = PriceList;
//			mTab.setValue("PriceEntered", PriceEntered);
//
//			// Mloera: Buscamos el field PriceEntered
//			GridField[] fields = GridField.createFields(ctx, WindowNo,
//					mTab.getTabNo(), mTab.getAD_Tab_ID());
//
//			for (int i = 0; i < fields.length; i++) {
//				if (fields[i].getColumnName().equalsIgnoreCase("PriceEntered")) {
//					mFieldPriceEntered = fields[i];
//					break;
//				}
//			}
//			
//		} else if (mField.getColumnName().equals("PriceList_Vol")) {
//			PriceEntered = (BigDecimal) mTab.getValue("PriceEntered_Vol");
//			PriceList = (BigDecimal) mTab.getValue("PriceList_Vol");
//			/*
//			 * se actualiza el campo precio con el mismo valor del campo precio
//			 * lista. Esto con el fin de que al postear no muestre el error de
//			 * estatus no balanceado.
//			 */
//			PriceEntered = PriceList;
//			mTab.setValue("PriceEntered", PriceEntered);
//
//			// Mloera: Buscamos el field PriceEntered
//			GridField[] fields = GridField.createFields(ctx, WindowNo,
//					mTab.getTabNo(), mTab.getAD_Tab_ID());
//			for (int i = 0; i < fields.length; i++) {
//				if (fields[i].getColumnName().equalsIgnoreCase("PriceEntered")) {
//					mFieldPriceEntered = fields[i];
//					break;
//				}
//			}
//		}
//		
//		
//		
//		
//		
//		
//		// Desactivamos el callout para cuando se mande llamar la funci�n amt se
//		// realice.
//		setCalloutActive(false);
//
//		/*
//		 * Mandamos llamar el m�todo amt (Invoice - Amount) para que actualice
//		 * dado que el field PriceEntered fue modificado.
//		 */
//		amt(ctx, WindowNo, mTab, mFieldPriceEntered, PriceEntered);
		amtVol(ctx, WindowNo, mTab, mField, value);
		return "";
	}

	/**
	 * Invoice Line - Quantity. - called from C_UOM_ID, QtyEntered, QtyInvoiced
	 * - enforces qty UOM relationship
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
	public String qtyVol(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		
		if (isCalloutActive() || value == null){
			return "";
		}
		setCalloutActive(true);

		final StringBuilder msg = new StringBuilder("");
		final String errorconversion = "error.udm.noExisteConversion";
		// int mProductID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		final MProduct mProduct = new MProduct(ctx, Env.getContextAsInt(ctx,WindowNo, "M_Product_ID"), null);
		// log.log(Level.WARNING,"qty - init - M_Product_ID=" + M_Product_ID);
		BigDecimal qtyInvoicedVol = Env.ZERO;
		BigDecimal qtyEnteredVol = Env.ZERO;

		final MUOMConversion rates = MEXMEUOMConversion.validateConversions(
				Env.getCtx(), mProduct.getM_Product_ID(),
				mProduct.getC_UOMVolume_ID(), null);

		if (rates == null
				&& mProduct.getC_UOM_ID() != mProduct.getC_UOMVolume_ID()) {
			log.saveError(Utilerias.getMsg(Env.getCtx(), errorconversion),
					mProduct.getName());
			msg.append(Utilerias.getMsg(Env.getCtx(), errorconversion));
			mField.setValue(mField.getOldValue(), true);
		}
		
		// No Product
		if (mProduct != null && mProduct.getM_Product_ID() == 0) {
			qtyEnteredVol = (BigDecimal) mTab.getValue("QtyEntered_Vol");
			mTab.setValue("QtyInvoiced_Vol", qtyEnteredVol);
		}
		// UOM Changed - convert from Entered -> Product
		// else if (mField.getColumnName().equals("C_UOM_ID"))
		// {
		// int C_UOM_To_ID = 0;
		// if(value instanceof BigDecimal)
		// C_UOM_To_ID = Integer.valueOf(value.toString());
		// else if(value instanceof Integer)
		// C_UOM_To_ID = ((Integer)value).intValue();
		// qtyEnteredVol = (BigDecimal)mTab.getValue("QtyEntered");
		// qtyInvoicedVol = MUOMConversion.convertProductTo (ctx, mProductID,
		// C_UOM_To_ID, qtyEnteredVol);
		// if (qtyInvoicedVol == null)
		// qtyInvoicedVol = qtyEnteredVol;
		// boolean conversion = qtyEnteredVol.compareTo(qtyInvoicedVol) != 0;
		// priceActualVol = (BigDecimal)mTab.getValue("PriceActual");
		// priceEnteredVol = MUOMConversion.convertProductTo (ctx, mProductID,
		// C_UOM_To_ID, priceActualVol);
		// if (priceEnteredVol == null)
		// priceEnteredVol = priceActualVol;
		// log.fine("qty - UOM=" + C_UOM_To_ID
		// + ", QtyEntered/PriceActual=" + qtyEnteredVol + "/" + priceActualVol
		// + " -> " + conversion
		// + " QtyInvoiced/PriceEntered=" + qtyInvoicedVol + "/" +
		// priceEnteredVol);
		// Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" :
		// "N");
		// mTab.setValue("QtyInvoiced", qtyInvoicedVol);
		// mTab.setValue("PriceEntered", priceEnteredVol);
		// }
		// QtyEntered changed - calculate QtyInvoiced
		else if (mField.getColumnName().equals("QtyEntered_Vol")) {
			int cUOMToID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			qtyEnteredVol = (BigDecimal) value;
			qtyInvoicedVol = MUOMConversion.convertProductTo(ctx,mProduct.getM_Product_ID(), cUOMToID, qtyEnteredVol);
			if (qtyInvoicedVol == null){
				qtyInvoicedVol = qtyEnteredVol;
			}
			
			boolean conversion = qtyEnteredVol.compareTo(qtyInvoicedVol) != 0;
			log.fine("qty - UOM=" + cUOMToID + ", QtyEnteredVol="
					+ qtyEnteredVol + " -> " + conversion + " QtyInvoicedVol="
					+ qtyInvoicedVol);
			// Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" :
			// "N");
			mTab.setValue("QtyInvoiced_Vol", qtyInvoicedVol);
			mTab.setValue("QtyInvoiced", MEXMEUOMConversion.convertProductFrom(
					Env.getCtx(), mProduct.getM_Product_ID(),
					mProduct.getC_UOMVolume_ID(), qtyInvoicedVol, null));
		
		} else if (mField.getColumnName().equals("QtyInvoiced_Vol")) {// QtyInvoiced changed - calculate QtyEntered
			int cUOMToID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			qtyInvoicedVol = (BigDecimal) value;
			qtyEnteredVol = MUOMConversion.convertProductFrom(ctx,
					mProduct.getM_Product_ID(), cUOMToID, qtyInvoicedVol);
			if (qtyEnteredVol == null){
				qtyEnteredVol = qtyInvoicedVol;
			}
			boolean conversion = qtyInvoicedVol.compareTo(qtyEnteredVol) != 0;
			log.fine("qty - UOM=" + cUOMToID + ", QtyInvoicedVol="
					+ qtyInvoicedVol + " -> " + conversion + " QtyEnteredVol="
					+ qtyEnteredVol);
			// Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" :
			// "N");
			mTab.setValue("QtyEntered_Vol", qtyEnteredVol);
			mTab.setValue("QtyInvoiced", MEXMEUOMConversion.convertProductFrom(
					Env.getCtx(), mProduct.getM_Product_ID(),
					mProduct.getC_UOMVolume_ID(), qtyEnteredVol, null));
		}
		//
		setCalloutActive(false);
		return "";
	} // qty

	public String amtVol(final Properties ctx, final int WindowNo, final GridTab mTab,
			final GridField mField, final Object value) {
		final StringBuilder msg = new StringBuilder("");
		final String errorconversion = "error.udm.noExisteConversion";
		
		if (isCalloutActive() || value == null){
			return msg.toString();
		}
		
		setCalloutActive(true);
		int productId = mTab.getValue("M_Product_ID") == null ? 0 : Integer.valueOf(mTab.getValue("M_Product_ID").toString());
		final MProduct mProduct = new MProduct(ctx, productId, null);

		final MUOMConversion rates = MEXMEUOMConversion.validateConversions(
				Env.getCtx(), mProduct.getM_Product_ID(),
				mProduct.getC_UOMVolume_ID(), null);
		if (rates == null
				&& mProduct.getC_UOM_ID() != mProduct.getC_UOMVolume_ID()) {
			log.saveError(Utilerias.getMsg(Env.getCtx(), errorconversion),
					mProduct.getName());
			msg.append(Utilerias.getMsg(Env.getCtx(), errorconversion));
			mField.setValue(mField.getOldValue(), true);
		}
		
		int cUOMvOLID = mProduct.getC_UOMVolume_ID();

		BigDecimal qtyEnteredVol   = (BigDecimal) mTab.getValue("QtyEntered_Vol");
		BigDecimal priceActualVol  = (BigDecimal) mTab.getValue("PriceActual_Vol");

		// // Qty changed - recalc price
		if ((mField.getColumnName().equals("QtyOrdered_Vol")
				|| mField.getColumnName().equals("QtyEntered_Vol") || mField
				.getColumnName().equals("M_Product_ID"))
		/* && !"N".equals(Env.getContext(ctx, WindowNo, "DiscountSchema")) */) {

			if (mField.getColumnName().equals("QtyEntered_Vol")){
				mTab.setValue("QtyOrdered_Vol", qtyEnteredVol);
			}
			
			// Convertit las unidades de volumen a unidades minimas
			mTab.setValue("QtyEntered", MEXMEUOMConversion.convertProductFrom(
					Env.getCtx(), mProduct.getM_Product_ID(), cUOMvOLID,
					qtyEnteredVol, null));
			mTab.setValue("QtyOrdered", MEXMEUOMConversion.convertProductFrom(
					Env.getCtx(), mProduct.getM_Product_ID(), cUOMvOLID,
					qtyEnteredVol, null));
			// Env.setContext(ctx, WindowNo, "DiscountSchema",
			// pp.isDiscountSchema() ? "Y" : "N");
			
		} else if (mField.getColumnName().equals("PriceActual_Vol")) {
//			log.fine("PriceActual=" + priceActualVol + " -> PriceEntered="
//					+ priceEnteredVol);
//			priceEnteredVol = priceActualVol;
//			mTab.setValue("PriceEntered_Vol", priceActualVol);
//			mTab.setValue("PriceEntered", MEXMEUOMConversion.convertProductTo(
//					Env.getCtx(), mProduct.getM_Product_ID(), cUOMvOLID,
//					priceActualVol, null, false));
//			mTab.setValue("PriceActual", MEXMEUOMConversion.convertProductTo(
//					Env.getCtx(), mProduct.getM_Product_ID(), cUOMvOLID,
//					priceActualVol, null, false));
//			
//			mTab.setValue("Discount", getDiscount((BigDecimal) mTab.getValue("PriceList_Vol"), (BigDecimal) mTab.getValue("PriceActual_Vol")));
			getUpdatePriceEnteredVol(ctx, WindowNo, mTab, mField, priceActualVol);
			
		} else if (mField.getColumnName().equals("PriceEntered_Vol")) {
//			log.fine("PriceEntered=" + priceEnteredVol + " -> PriceActual="
//					+ priceActualVol);
//			priceActualVol = priceEnteredVol;
//			mTab.setValue("PriceActual_Vol", priceEnteredVol);
//			mTab.setValue("PriceEntered", MEXMEUOMConversion.convertProductTo(
//					Env.getCtx(), mProduct.getM_Product_ID(), cUOMvOLID,
//					priceEnteredVol, null, false));
//			mTab.setValue("PriceActual", MEXMEUOMConversion.convertProductTo(
//					Env.getCtx(), mProduct.getM_Product_ID(), cUOMvOLID,
//					priceEnteredVol, null, false));
//			
//			// Ticket 6162
//			if(priceListVol.compareTo(Env.ZERO)==0){
//				mTab.setValue("PriceList_Vol", priceEnteredVol);
//				mTab.setValue("PriceList", MEXMEUOMConversion.convertProductTo(Env.getCtx(), mProduct.getM_Product_ID(), cUOMvOLID,priceEnteredVol, null, false));
//			}
//			
//			mTab.setValue("Discount", getDiscount((BigDecimal) mTab.getValue("PriceList_Vol"), (BigDecimal) mTab.getValue("PriceActual_Vol")));
			getUpdatePriceActualVol(ctx, WindowNo, mTab, value);
			
		} else if (mField.getColumnName().equals("PriceList_Vol")) {
			getUpdatePriceList(ctx, WindowNo, mTab, value);
//			log.fine("PriceList_Vol=" + priceListVol);
//			mTab.setValue("PriceList", MEXMEUOMConversion.convertProductTo(
//					Env.getCtx(), mProduct.getM_Product_ID(), cUOMvOLID,
//					priceListVol, null, false));
//			
//			mTab.setValue("Discount", getDiscount((BigDecimal) mTab.getValue("PriceList_Vol"), (BigDecimal) mTab.getValue("PriceActual_Vol")));
//			
//			if (priceListVol.intValue() == 0) {
//				Discount = Env.ZERO;
//			} else {
//				Discount = new BigDecimal(
//						(priceListVol.doubleValue() - priceActualVol
//								.doubleValue())
//								/ priceListVol.doubleValue()
//								* 100.0);
//			}
//			if (Discount.scale() > 2) {
//				Discount = Discount.setScale(2, BigDecimal.ROUND_HALF_UP);
//			}
//			mTab.setValue("Discount", Discount);
		}

//		// Line Net Amt Calcular siempre.
//		BigDecimal qtyInvoicedVol = (BigDecimal) mTab.getValue("QtyInvoiced_Vol");
//		if (qtyInvoicedVol == null) {// Null
//			qtyInvoicedVol = Env.ZERO;
//		}
//		
//		priceActualVol = (BigDecimal) mTab.getValue("PriceActual");
//		if (priceActualVol == null) {
//			priceActualVol = Env.ZERO;
//		}
//		
		BigDecimal LineNetAmt = getLineNetAmt(ctx, WindowNo, mTab);
//				qtyInvoicedVol.multiply(priceActualVol);//qtyEnteredVol.multiply((BigDecimal) mTab.getValue("PriceList_Vol"));
//		
//		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo,"M_PriceList_ID");
//		int StdPrecision = MPriceList.getStandardPrecision(ctx, M_PriceList_ID);
//		if (LineNetAmt.scale() > StdPrecision){
//			LineNetAmt = LineNetAmt.setScale(StdPrecision,BigDecimal.ROUND_HALF_UP);
//		}
//		log.info("LineNetAmt=" + LineNetAmt);
//		mTab.setValue("LineNetAmt", LineNetAmt);

		//
		getImpuestos(ctx, WindowNo, mTab, mField, LineNetAmt );
//		boolean IsSOTrx = "Y".equals(Env.getContext(Env.getCtx(), WindowNo,
//				"IsSOTrx"));
//		if (!IsSOTrx) {
//			BigDecimal TaxAmt = null;
//			if (mField.getColumnName().equals("TaxAmt")) {
//				TaxAmt = (BigDecimal) mTab.getValue("TaxAmt");
//			} else {
//				Integer taxID = (Integer) mTab.getValue("C_Tax_ID");//
//				if (taxID != null) {
//					int C_Tax_ID = taxID.intValue();
//					MTax tax = new MTax(ctx, C_Tax_ID, null);
//					TaxAmt = tax.calculateTax(LineNetAmt,
//							isTaxIncluded(WindowNo), StdPrecision);
//					mTab.setValue("TaxAmt", TaxAmt);
//				}
//			}
//			// Add it up
//			mTab.setValue("LineTotalAmt", LineNetAmt.add(TaxAmt));
//		}

		mTab.setValue("C_UOM_Volume_ID", cUOMvOLID);
		setCalloutActive(false);
		return msg.toString();
	} // amt
	
	private BigDecimal getLineNetAmt(final Properties ctx, final int WindowNo, final GridTab mTab){
		// Line Net Amt Calcular siempre.
		BigDecimal qtyInvoicedVol = (BigDecimal) mTab.getValue("QtyInvoiced_Vol");
		if (qtyInvoicedVol == null) {// Null
			qtyInvoicedVol = Env.ZERO;
		}

		BigDecimal priceActualVol = (BigDecimal) mTab.getValue("PriceActual_Vol");
		if (priceActualVol == null) {
			priceActualVol = Env.ZERO;
		}

		BigDecimal LineNetAmt = qtyInvoicedVol.multiply(priceActualVol);//qtyEnteredVol.multiply((BigDecimal) mTab.getValue("PriceList_Vol"));

		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo,"M_PriceList_ID");
		int StdPrecision = MPriceList.getStandardPrecision(ctx, M_PriceList_ID);
		if (LineNetAmt.scale() > StdPrecision){
			LineNetAmt = LineNetAmt.setScale(StdPrecision,BigDecimal.ROUND_HALF_UP);
		}
		log.info("LineNetAmt=" + LineNetAmt);
		mTab.setValue("LineNetAmt", LineNetAmt);
		return LineNetAmt;
	}

	/** Calcula el impuesto de la linea de la factura */
	private void getImpuestos(final Properties ctx, final int WindowNo, final GridTab mTab,
			final GridField mField, final BigDecimal LineNetAmt ){
		
		int StdPrecision = MPriceList.getStandardPrecision(ctx, Env.getContextAsInt(ctx, WindowNo,"M_PriceList_ID"));
		boolean IsSOTrx = "Y".equals(Env.getContext(Env.getCtx(), WindowNo,
				"IsSOTrx"));
		if (!IsSOTrx) {
			BigDecimal TaxAmt = null;
			if (mField.getColumnName().equals("TaxAmt")) {
				TaxAmt = (BigDecimal) mTab.getValue("TaxAmt");
				
			} else {
				Integer taxID = (Integer) mTab.getValue("C_Tax_ID");//
				if (taxID != null) {
					final int C_Tax_ID = taxID.intValue();
					final MTax tax = new MTax(ctx, C_Tax_ID, null);
					TaxAmt = tax.calculateTax(LineNetAmt,
							isTaxIncluded(WindowNo), StdPrecision);
					mTab.setValue("TaxAmt", TaxAmt);
				}
			}
			// Add it up
			mTab.setValue("LineTotalAmt", LineNetAmt.add(TaxAmt));
		}
	}
	
	/** Calculo de descuento */
	private BigDecimal getDiscount(final BigDecimal priceListVol, final BigDecimal priceActualVol){
		BigDecimal discount = Env.ZERO;
		if (priceListVol.intValue() == 0) {
			discount = Env.ZERO;
		} else {
			discount = new BigDecimal(
					(priceListVol.doubleValue() - priceActualVol
							.doubleValue())
							/ priceListVol.doubleValue()
							* 100.0);
		}
		if (discount.scale() > 2) {
			discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return discount;
	}
	
	
	
	/**************************************************************************
	 * Invoice Line - Product. - reset C_Charge_ID / M_AttributeSetInstance_ID -
	 * PriceList, PriceStd, PriceLimit, C_Currency_ID, EnforcePriceLimit - UOM
	 * Calls Tax
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
	public String productVol(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer mProductID = null;

		if (value instanceof BigDecimal)
			mProductID = Integer.valueOf(value.toString());
		else if (value instanceof Integer)
			mProductID = (Integer) value;

		setCalloutActive(true);

		if (mProductID == null || mProductID.intValue() == 0) {

			// si el producto es cero se limpian los valores
			mTab.setValue("PriceList_Vol", Env.ZERO);
			mTab.setValue("PriceList", Env.ZERO);
			mTab.setValue("PriceEntered_Vol", Env.ZERO);
			mTab.setValue("PriceEntered", Env.ZERO);
			mTab.setValue("PriceActual_Vol", Env.ZERO);
			mTab.setValue("PriceActual", Env.ZERO);
			mTab.setValue("QtyEntered", Env.ZERO);
			mTab.setValue("QtyInvoiced", Env.ZERO);
			mTab.setValue("QtyEntered_Vol", Env.ZERO);
			mTab.setValue("QtyInvoiced_Vol", Env.ZERO);

			setCalloutActive(false);

			return "";
		} else {
			// se limpian las cantidades
			mTab.setValue("QtyEntered", Env.ZERO);
			mTab.setValue("QtyInvoiced", Env.ZERO);
			mTab.setValue("QtyEntered_Vol", Env.ZERO);
			mTab.setValue("QtyInvoiced_Vol", Env.ZERO);
		}

		setCalloutActive(true);
		mTab.setValue("C_Charge_ID", null);

		// Validar que este dentro del charge master
		// final MProduct product = new MProduct(ctx, mProductID, null);
		// if (mProductID>0 && MEXMEMejoras.isControlaExistencias(
		// Env.getAD_Client_ID(Env.getCtx()),
		// Env.getAD_Org_ID(Env.getCtx()), null)
		// && (product.getProdOrg() == null || (product.getProdOrg() != null &&
		// product
		// .getProdOrg().getAD_Org_ID() <= 0))) {
		// setCalloutActive(false);
		// return Utilerias.getLabel("msj.ligarProducto");
		// }
		// Validar si el producto este a nivel de organización
		final MProduct product = new MProduct(ctx, mProductID, null);
		final String error = MProduct.isValidProductOrg(ctx, mProductID, false);
		if (error != null) {
			setCalloutActive(false);
			return error;
		}

		// Set Attribute
		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
				"M_Product_ID") == mProductID.intValue()
				&& Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
						"M_AttributeSetInstance_ID") != 0)
			mTab.setValue(
					"M_AttributeSetInstance_ID",
					new Integer(Env.getContextAsInt(ctx, Env.WINDOW_INFO,
							Env.TAB_INFO, "M_AttributeSetInstance_ID")));
		else
			mTab.setValue("M_AttributeSetInstance_ID", null);

		// /***** Price Calculation see also qty ****/
		// boolean IsSOTrx = Env.getContext(ctx, WindowNo,
		// "IsSOTrx").equals("Y");
		// int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, WindowNo,
		// "C_BPartner_ID");
		// BigDecimal Qty = (BigDecimal)mTab.getValue("QtyInvoiced");
		// MProductPricing pp = new MProductPricing (M_Product_ID.intValue(),
		// C_BPartner_ID, Qty, IsSOTrx);
		// //
		// int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo,
		// "M_PriceList_ID");
		// pp.setM_PriceList_ID(M_PriceList_ID);
		// int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo,
		// "M_PriceList_Version_ID");
		// pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		// Timestamp date = Env.getContextAsDate(ctx, WindowNo, "DateInvoiced");
		// pp.setPriceDate(date);
		// //
		// mTab.setValue("PriceList", pp.getPriceList());
		// mTab.setValue("PriceLimit", pp.getPriceLimit());
		// mTab.setValue("PriceActual", pp.getPriceStd());
		// mTab.setValue("PriceEntered", pp.getPriceStd());
		// mTab.setValue("C_Currency_ID", new Integer(pp.getC_Currency_ID()));
		// mTab.setValue("C_UOM_ID", new Integer(pp.getC_UOM_ID()));
		// Env.setContext(ctx, WindowNo, "EnforcePriceLimit",
		// pp.isEnforcePriceLimit() ? "Y" : "N");
		// Env.setContext(ctx, WindowNo, "DiscountSchema", pp.isDiscountSchema()
		// ? "Y" : "N");

		/***** Price Calculation see also qty ****/
		boolean IsSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, WindowNo,
				"C_BPartner_ID");
		BigDecimal Qty = (BigDecimal) mTab.getValue("QtyInvoiced");
		MProductPricing pp = new MProductPricing(mProductID.intValue(),
				C_BPartner_ID, Qty, IsSOTrx);
		//
		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_PriceList_ID");
		// pp.setM_PriceList_ID(M_PriceList_ID);
		int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_PriceList_Version_ID");
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		// Timestamp date = Env.getContextAsDate(ctx, WindowNo, "DateInvoiced");
		// pp.setPriceDate(date);

		MProductPrice mPrice = MProductPrice.getProductPrice(C_BPartner_ID,
				M_PriceList_ID, product.getM_Product_ID(), IsSOTrx);
		//
		if (mPrice != null) {
			// Si el producto existe en una lista de precios
			mTab.setValue("PriceList_Vol", mPrice.getPriceList_Vol());
			mTab.setValue("PriceEntered_Vol", mPrice.getPriceList_Vol());
			mTab.setValue("PriceActual_Vol", mPrice.getPriceList_Vol());
			mTab.setValue("PriceList", mPrice.getPriceList());
			mTab.setValue("PriceEntered", mPrice.getPriceList());
			mTab.setValue("PriceActual", mPrice.getPriceList());

		} else {
			mTab.setValue("PriceList_Vol", Env.ZERO);
			mTab.setValue("PriceList", Env.ZERO);
			mTab.setValue("PriceEntered_Vol", Env.ZERO);
			mTab.setValue("PriceEntered", Env.ZERO);
			mTab.setValue("PriceActual_Vol", Env.ZERO);
			mTab.setValue("PriceActual", Env.ZERO);

		}
		mTab.setValue("C_Currency_ID", Integer.valueOf(pp.getC_Currency_ID()));

		mTab.setValue("Discount", Env.ZERO);
		mTab.setValue("C_UOM_ID", product.getC_UOM_ID());
		mTab.setValue("C_UOMVolume_ID", product.getC_UOMVolume_ID());

		// Env.setContext(ctx, WindowNo, "EnforcePriceLimit",
		// pp.isEnforcePriceLimit() ? "Y" : "N");
		// Env.setContext(ctx, WindowNo, "DiscountSchema", pp.isDiscountSchema()
		// ? "Y" : "N");
		//
		setCalloutActive(false);
		return tax(ctx, WindowNo, mTab, mField, value);
	} // product

	/**
	 * Order Header - PriceList. (used also in Invoice) - C_Currency_ID -
	 * IsTaxIncluded Window Context: - EnforcePriceLimit - StdPrecision -
	 * M_PriceList_Version_ID
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
	public String priceList(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer M_PriceList_ID = null;
		if (value instanceof BigDecimal) {
			M_PriceList_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			M_PriceList_ID = (Integer) value;
		}
		if (M_PriceList_ID == null || M_PriceList_ID.intValue() == 0) {
			setCalloutActive(true);
			mTab.setValue("M_PriceList_ID", 0);
			setCalloutActive(false);
			return "";
		}

		String sql = "SELECT pl.IsTaxIncluded,pl.EnforcePriceLimit,pl.C_Currency_ID,c.StdPrecision,"
				+ "plv.M_PriceList_Version_ID,plv.ValidFrom "
				+ "FROM M_PriceList pl,C_Currency c,M_PriceList_Version plv "
				+ "WHERE pl.C_Currency_ID=c.C_Currency_ID"
				+ " AND pl.M_PriceList_ID=plv.M_PriceList_ID"
				+ " AND pl.M_PriceList_ID=? " // 1
				+ "ORDER BY plv.ValidFrom DESC";
		// Use newest price list - may not be future
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_PriceList_ID.intValue());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// Tax Included
				mTab.setValue("IsTaxIncluded",
						Boolean.valueOf("Y".equals(rs.getString(1))));
				// Price Limit Enforce
				Env.setContext(ctx, WindowNo, "EnforcePriceLimit",
						rs.getString(2));
				// Currency
				Integer ii = Integer.valueOf(rs.getInt(3));
				mTab.setValue("C_Currency_ID", ii);
				// PriceList Version
				Env.setContext(ctx, WindowNo, "M_PriceList_Version_ID",
						rs.getInt(5));
			}

		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
			return e.getLocalizedMessage();
		} finally {
			DB.close(rs, pstmt);
		}
		return "";
	} // priceList
	
	
	
	
	
	
	
	
	
	
	/** Actualiza el precio actual por medio de lista de precios*/
	private void getUpdatePriceActual(final Properties ctx, final int WindowNo, final GridTab mTab, final GridField mField){

		final int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");

		// get values
		final BigDecimal QtyEntered = mTab.getValue("QtyEntered") == null ? BigDecimal.ZERO
				: (BigDecimal) mTab.getValue("QtyEntered");
		BigDecimal QtyInvoiced = mTab.getValue("QtyInvoiced") == null ? BigDecimal.ZERO
				: (BigDecimal) mTab.getValue("QtyInvoiced");

		if (mField.getColumnName().equals("QtyEntered")){
			QtyInvoiced = MUOMConversion.convertProductTo(ctx
					, M_Product_ID
					, Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID")
					, QtyEntered);
		}

		final MProductPricing pp = new MProductPricing(M_Product_ID
				, Env.getContextAsInt(ctx, WindowNo,"C_BPartner_ID")
				, QtyInvoiced == null?QtyEntered:QtyInvoiced
				, Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y"));
		pp.setM_PriceList_ID(Env.getContextAsInt(ctx, WindowNo, "M_PriceList_ID"));
		pp.setM_PriceList_Version_ID(Env.getContextAsInt(ctx, WindowNo,"M_PriceList_Version_ID"));
		pp.setPriceDate((Timestamp) mTab.getValue("DateInvoiced"));

		mTab.setValue("PriceActual", pp.getPriceStd());
		getUpdatePriceEntered(ctx, WindowNo, mTab, mField, pp.getPriceStd());// mTab.setValue("PriceEntered", PriceEntered);
		
		Env.setContext(ctx, WindowNo, "DiscountSchema",pp.isDiscountSchema() ? "Y" : "N");
	}
	
	/** Actualiza el precio capturado a partir del precio actual en udm minima */
	private void getUpdatePriceEntered(final Properties ctx, final int WindowNo, final GridTab mTab, final  GridField mField, final BigDecimal PriceActual){
		final BigDecimal PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx
				, Env.getContextAsInt(ctx, WindowNo, "M_Product_ID")
				, Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID"), PriceActual, null, false);
		
		mTab.setValue("PriceEntered", PriceEntered == null?PriceActual:PriceEntered);
		log.fine("amt - PriceActual=" + PriceActual + " -> PriceEntered="+ PriceEntered);
	}
	
	/** Actualiza el precio capturado a partir del precio actual en udm de volumen */
	private void getUpdatePriceEnteredVol(final Properties ctx, final int WindowNo, final GridTab mTab, final  GridField mField, final BigDecimal PriceActualVol){
		int productId = mTab.getValue("M_Product_ID") == null ? 0 : Integer.valueOf(mTab.getValue("M_Product_ID").toString());
		if(productId>0){
			final MProduct mProduct = new MProduct(ctx, productId, null);
			final int cUOMvOLID     = mProduct.getC_UOMVolume_ID();

			mTab.setValue("PriceEntered_Vol", PriceActualVol);
			mTab.setValue("PriceEntered", MEXMEUOMConversion.convertProductTo(ctx
					, mProduct.getM_Product_ID()
					, cUOMvOLID
					, PriceActualVol, null, false));
			mTab.setValue("PriceActual", MEXMEUOMConversion.convertProductTo(ctx
					, mProduct.getM_Product_ID()
					, cUOMvOLID
					, PriceActualVol, null, false));

			mTab.setValue("Discount", getDiscount((BigDecimal) mTab.getValue("PriceList_Vol"), (BigDecimal) mTab.getValue("PriceActual_Vol")));
		}
	}
	
	
	/** Al cambiar el precio capturado actualiza el precio actual */
	private void getUpdatePriceActual(final Properties ctx, final int WindowNo, final GridTab mTab, Object value) {
		final BigDecimal PriceEntered = (BigDecimal) value;
		BigDecimal PriceActual  = MEXMEUOMConversion.convertProductTo(ctx
				, Env.getContextAsInt(ctx, WindowNo, "M_Product_ID")
				, Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID")
				, PriceEntered, null, false);
		
		if (PriceActual == null){
			PriceActual = PriceEntered;
		}

		if(((BigDecimal) mTab.getValue("PriceList")).compareTo(Env.ZERO)==0){
			mTab.setValue("PriceList", PriceEntered);
		}
		
		mTab.setValue("PriceActual", PriceActual == null?PriceEntered:PriceActual);
		log.fine("amt - PriceEntered=" + PriceEntered + " -> PriceActual="+ PriceActual);
	}
	/** Al cambiar el precio capturado actualiza el precio actual en volumen */
	private void getUpdatePriceActualVol(final Properties ctx, final int WindowNo, final GridTab mTab, Object value) {
		final BigDecimal priceEnteredVol = (BigDecimal) value;
		int productId = mTab.getValue("M_Product_ID") == null ? 0 : Integer.valueOf(mTab.getValue("M_Product_ID").toString());
		if(productId>0){
			final MProduct mProduct = new MProduct(ctx, productId, null);
			final int cUOMvOLID     = mProduct.getC_UOMVolume_ID();

			BigDecimal PriceActual  = MEXMEUOMConversion.convertProductTo(ctx
					, Env.getContextAsInt(ctx, WindowNo, "M_Product_ID")
					, cUOMvOLID
					, priceEnteredVol, null, false);

			if (PriceActual == null){
				PriceActual = priceEnteredVol;
			}

			if(((BigDecimal) mTab.getValue("PriceList_Vol")).compareTo(Env.ZERO)==0){
				mTab.setValue("PriceList_Vol", priceEnteredVol);
				mTab.setValue("PriceList",     PriceActual);
			}

			mTab.setValue("PriceEntered", PriceActual);
			mTab.setValue("PriceActual",  PriceActual);
			mTab.setValue("PriceActual_Vol", priceEnteredVol);
		} else {
			int cChargeID = mTab.getValue("C_Charge_ID") == null ? 0 : Integer.valueOf(mTab.getValue("C_Charge_ID").toString());
			if(cChargeID>0){
				mTab.setValue("PriceList_Vol", priceEnteredVol);
				mTab.setValue("PriceList",    priceEnteredVol);
				mTab.setValue("PriceEntered", priceEnteredVol);
				mTab.setValue("PriceActual",  priceEnteredVol);
				mTab.setValue("PriceActual_Vol", priceEnteredVol);
			}
		}
	}
	
	
	/** Actualiza el precio actual y el de captura deacuerdo al precio limite */
	private void getUpdatePriceLimit(final Properties ctx, final int WindowNo, final GridTab mTab, BigDecimal PriceLimit){
		BigDecimal PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx
				, Env.getContextAsInt(ctx, WindowNo, "M_Product_ID")
				, Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID")
				, PriceLimit, null, false);
		
		if (PriceEntered == null){
			PriceEntered = PriceLimit;
		}
		log.fine("amt =(under) PriceEntered=" + PriceEntered + ", Actual"+ PriceLimit);
		
		mTab.setValue("PriceActual", PriceLimit);
		mTab.setValue("PriceEntered", PriceEntered);
		mTab.fireDataStatusEEvent("UnderLimitPrice", "", false);
	}
	
	
	
	/** Al cambiar el precio capturado actualiza el precio actual en volumen */
	private void getUpdatePriceList(final Properties ctx, final int WindowNo, final GridTab mTab, Object value) {
		final BigDecimal priceListVol = (BigDecimal) value;
		int productId = mTab.getValue("M_Product_ID") == null ? 0 : Integer.valueOf(mTab.getValue("M_Product_ID").toString());
		if(productId>0){
			final MProduct mProduct = new MProduct(ctx, productId, null);
			mTab.setValue("PriceList", MEXMEUOMConversion.convertProductTo(ctx
					, mProduct.getM_Product_ID()
					, mProduct.getC_UOMVolume_ID(),
					priceListVol, null, false));

			mTab.setValue("Discount", getDiscount(priceListVol, (BigDecimal) mTab.getValue("PriceActual_Vol")));
		} else {
			mTab.setValue("PriceList", priceListVol);
		}
	}
} // CalloutInvoice
