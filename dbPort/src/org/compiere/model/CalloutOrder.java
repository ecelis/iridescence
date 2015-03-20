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
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Utilerias;

/**
 * Order Callouts.
 * 
 * @author Jorg Janke
 * @version $Id: CalloutOrder.java,v 1.5 2006/08/11 02:26:21 mrojas Exp $
 */
public class CalloutOrder extends CalloutEngine {
	/** Debug Steps */
	private boolean steps = false;

	/**
	 * Order Header Change - DocType. - InvoiceRuld/DeliveryRule/PaymentRule -
	 * temporary Document Context: - DocSubTypeSO - HasCharges - (re-sets
	 * Business Partner info of required)
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
	public String docType(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		mTab.setValue("C_BPartner_ID", 0);
		Integer C_DocType_ID = null;
		// #04664 — Documento INVALIDO en Recepción de Material debido a Lista
		// de Precios Ealvarez Se agrega la Lista de precios.
		mTab.setValue("M_PriceList_ID", 0);
		if (value instanceof BigDecimal) {
			C_DocType_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			C_DocType_ID = (Integer) value;// Actually C_DocTypeTarget_ID
		}
		if (C_DocType_ID == null || C_DocType_ID.intValue() == 0) {
			return "";
		}
		// Re-Create new DocNo, if there is a doc number already
		// and the existing source used a different Sequence number
		String oldDocNo = (String) mTab.getValue("DocumentNo");
		boolean newDocNo = (oldDocNo == null);
		if (!newDocNo && oldDocNo.startsWith("<") && oldDocNo.endsWith(">")) {
			newDocNo = true;
		}
		Integer oldC_DocType_ID = (Integer) mTab.getValue("C_DocType_ID");

		String sql = "SELECT d.DocSubTypeSO,d.HasCharges,'N'," // 1..3
				+ "d.IsDocNoControlled,s.CurrentNext,s.CurrentNextSys," // 4..6
				+ "s.AD_Sequence_ID,d.IsSOTrx " // 7..8
				+ "FROM C_DocType d, AD_Sequence s " + "WHERE C_DocType_ID=?" // #1
				+ " AND d.DocNoSequence_ID=s.AD_Sequence_ID(+)";
		try {
			int AD_Sequence_ID = 0;

			// Get old AD_SeqNo for comparison
			if (!newDocNo && oldC_DocType_ID.intValue() != 0) {
				PreparedStatement pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, oldC_DocType_ID.intValue());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					AD_Sequence_ID = rs.getInt(6);
				}
				rs.close();
				pstmt.close();
			}

			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_DocType_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			String DocSubTypeSO = "";
			boolean IsSOTrx = true;
			if (rs.next()) // we found document type
			{
				// Set Context: Document Sub Type for Sales Orders
				DocSubTypeSO = rs.getString(1);
				if (DocSubTypeSO == null) {
					DocSubTypeSO = "--";
				}
				Env.setContext(ctx, WindowNo, "OrderType", DocSubTypeSO);
				// No Drop Ship other than Standard
				if (!DocSubTypeSO.equals(MOrder.DocSubTypeSO_Standard)) {
					mTab.setValue("IsDropShip", "N");
				}
				// Delivery Rule
				if (DocSubTypeSO.equals(MOrder.DocSubTypeSO_POS)) {
					mTab.setValue("DeliveryRule", MOrder.DELIVERYRULE_Force);
				} else if (DocSubTypeSO.equals(MOrder.DocSubTypeSO_Prepay)) {
					mTab.setValue("DeliveryRule",
							MOrder.DELIVERYRULE_AfterReceipt);
				} else {
					mTab.setValue("DeliveryRule",
							MOrder.DELIVERYRULE_Availability);
				}
				// Invoice Rule
				if (DocSubTypeSO.equals(MOrder.DocSubTypeSO_POS)
						|| DocSubTypeSO.equals(MOrder.DocSubTypeSO_Prepay)
						|| DocSubTypeSO.equals(MOrder.DocSubTypeSO_OnCredit)) {
					mTab.setValue("InvoiceRule", MOrder.INVOICERULE_Immediate);
				} else {
					mTab.setValue("InvoiceRule",
							MOrder.INVOICERULE_AfterDelivery);
				}
				// Payment Rule - POS Order
				if (DocSubTypeSO.equals(MOrder.DocSubTypeSO_POS)) {
					mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_Cash);
				} else {
					mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_OnCredit);
				}
				// IsSOTrx
				if ("N".equals(rs.getString(8))) {
					IsSOTrx = false;
				}

				// Set Context:
				Env.setContext(ctx, WindowNo, "HasCharges", rs.getString(2));
				// DocumentNo
				if (rs.getString(4).equals("Y")) // IsDocNoControlled
				{
					if (!newDocNo && AD_Sequence_ID != rs.getInt(7)) {
						newDocNo = true;
					}
					if (newDocNo)
						if (Ini.isPropertyBool(Ini.P_COMPIERESYS)
								&& Env.getAD_Client_ID(Env.getCtx()) < 1000000) {
							mTab.setValue("DocumentNo", "<" + rs.getString(6)
									+ ">");
						} else {
							mTab.setValue("DocumentNo", "<" + rs.getString(5)
									+ ">");
						}
				}
			}
			rs.close();
			pstmt.close();
			// expert
			rs = null;
			pstmt = null;
			// expert
			// When BPartner is changed, the Rules are not set if
			// it is a POS or Credit Order (i.e. defaults from Standard
			// BPartner)
			// This re-reads the Rules and applies them.
			if (!(DocSubTypeSO.equals(MOrder.DocSubTypeSO_POS) || DocSubTypeSO
					.equals(MOrder.DocSubTypeSO_Prepay))) { // not for
															// POS/PrePay

				// }else
				// {
				sql = "SELECT NVL(pcte.PaymentRule, p.PaymentRule)      as PaymentRule,"
						+ "NVL(pcte.C_PaymentTerm_ID,   p.C_PaymentTerm_ID) as C_PaymentTerm_ID," // 1..2
						+ "NVL(pcte.InvoiceRule,        p.InvoiceRule)      as InvoiceRule,"
						+ "NVL(pcte.DeliveryRule,       p.DeliveryRule)     as DeliveryRule," // 3..4
						+ "p.FreightCostRule,"
						+ "NVL(pcte.DeliveryViaRule,    p.DeliveryViaRule)  as DeliveryViaRule, " // 5..6
						+ "p.PaymentRulePO,p.PO_PaymentTerm_ID "
						+ "FROM C_BPartner p"
						// Lama - Partner Cte
						+ " LEFT JOIN C_BPartner_Cte pcte ON (p.C_BPartner_ID=pcte.C_BPartner_ID AND pcte.AD_Client_ID = ? )" // #1
						+ "WHERE p.C_BPartner_ID=?"; // #2
				pstmt = DB.prepareStatement(sql, null);
				int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo,
						"C_BPartner_ID");
				pstmt.setInt(1, Env.getAD_Client_ID(ctx));
				pstmt.setInt(2, C_BPartner_ID);
				//
				rs = pstmt.executeQuery();
				if (rs.next()) {
					// PaymentRule
					String s = rs.getString(IsSOTrx ? "PaymentRule"
							: "PaymentRulePO");
					if (s != null && s.length() != 0) {
						if (IsSOTrx
								&& ("B".equals(s) || "S".equals(s) || "U"
										.equals(s))) { // No Cash/Check/Transfer
														// for SO_Trx
							s = "P"; // Payment Term
						}
						if (!IsSOTrx && ("B".equals(s))) { // No Cash for PO_Trx
							s = "P"; // Payment Term
						}
						mTab.setValue("PaymentRule", s);
					}
					// Payment Term
					Integer ii = Integer.valueOf(rs
							.getInt(IsSOTrx ? "C_PaymentTerm_ID"
									: "PO_PaymentTerm_ID"));
					if (!rs.wasNull()) {
						mTab.setValue("C_PaymentTerm_ID", ii);
					}
					// InvoiceRule
					s = rs.getString(3);
					if (s != null && s.length() != 0) {
						mTab.setValue("InvoiceRule", s);
					}
					// DeliveryRule
					s = rs.getString(4);
					if (s != null && s.length() != 0) {
						mTab.setValue("DeliveryRule", s);
					}
					// FreightCostRule
					s = rs.getString(5);
					if (s != null && s.length() != 0) {
						mTab.setValue("FreightCostRule", s);
					}
					// DeliveryViaRule
					s = rs.getString(6);
					if (s != null && s.length() != 0) {
						mTab.setValue("DeliveryViaRule", s);
					}
				}
				rs.close();
				pstmt.close();
				// expert
				rs = null;
				pstmt = null;
				// expert
			} // re-read customer rules
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
			return e.getLocalizedMessage();
		}

		return "";
	} // docType

	/**
	 * Order Header - BPartner. - M_PriceList_ID (+ Context) -
	 * C_BPartner_Location_ID - Bill_BPartner_ID/Bill_Location_ID - AD_User_ID -
	 * POReference - SO_Description - IsDiscountPrinted -
	 * InvoiceRule/DeliveryRule/PaymentRule/FreightCost/DeliveryViaRule -
	 * C_PaymentTerm_ID
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
	public String bPartner(Properties ctx, int WindowNo, GridTab mTab,
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
		setCalloutActive(true);

		String sql = "SELECT p.AD_Language,lship.C_BPartner_Location_ID,c.AD_User_ID,"
				+ "NVL(pcte.C_PaymentTerm_ID,     p.C_PaymentTerm_ID)   as C_PaymentTerm_ID, "
				+ " COALESCE("
				+ "NVL(pcte.M_PriceList_ID,       p.M_PriceList_ID),"
				+ "NVL(gcte.M_PriceList_ID,       g.M_PriceList_ID))    AS M_PriceList_ID, "
				+ "NVL(pcte.PaymentRule,          p.PaymentRule)        as PaymentRule,"
				+ "NVL(pcte.POReference,          p.POReference)        as POReference,"
				+ "NVL(pcte.SO_Description,       p.SO_Description)     as SO_Description,"
				+ "NVL(pcte.IsDiscountPrinted,    p.IsDiscountPrinted)  as IsDiscountPrinted,"
				+ "NVL(pcte.InvoiceRule,          p.InvoiceRule)        as InvoiceRule,"
				+ "NVL(pcte.DeliveryRule,         p.DeliveryRule)       as DeliveryRule,"
				+ "p.FreightCostRule,"
				+ "NVL(pcte.DeliveryViaRule,      p.DeliveryViaRule)    as DeliveryViaRule,"
				+ "NVL(pcte.SO_CreditLimit,       p.SO_CreditLimit)     as SO_CreditLimit,"
				+ "NVL(pcte.SO_CreditLimit,       p.SO_CreditLimit) -"
				+ "NVL(pcte.SO_CreditUsed,        p.SO_CreditUsed )     AS CreditAvailable,"
				+ " COALESCE(p.PO_PriceList_ID,"
				+ "NVL(gcte.PO_PriceList_ID,      g.PO_PriceList_ID))   AS PO_PriceList_ID,"
				+ " p.PaymentRulePO,p.PO_PaymentTerm_ID,"
				+ " lbill.C_BPartner_Location_ID AS Bill_Location_ID, "
				+ "NVL(pcte.SOCreditStatus,       p.SOCreditStatus)     as SOCreditStatus "
				+ "FROM C_BPartner p"
				// Lama - Partner Cte
				+ " LEFT JOIN C_BPartner_Cte pcte ON (p.C_BPartner_ID=pcte.C_BPartner_ID AND pcte.AD_Client_ID = ? )" // #1
				+ " INNER JOIN C_BP_Group g ON (p.C_BP_Group_ID=g.C_BP_Group_ID)"
				// Lama .- C_BP_Group_Cte
				+ " LEFT JOIN C_BP_Group_Cte gcte ON (g.C_BP_Group_ID=gcte.C_BP_Group_ID AND gcte.AD_Client_ID = ? )" // #2
				+ " LEFT OUTER JOIN C_BPartner_Location lbill ON (p.C_BPartner_ID=lbill.C_BPartner_ID AND lbill.IsBillTo='Y' AND lbill.IsActive='Y')"
				+ " LEFT OUTER JOIN C_BPartner_Location lship ON (p.C_BPartner_ID=lship.C_BPartner_ID AND lship.IsShipTo='Y' AND lship.IsActive='Y')"
				+ " LEFT OUTER JOIN AD_User c ON (p.C_BPartner_ID=c.C_BPartner_ID) "
				+ "WHERE p.C_BPartner_ID=? AND p.IsActive='Y'"; // #3

		boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));

		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, C_BPartner_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// PriceList (indirect: IsTaxIncluded & Currency)
				Integer ii = Integer
						.valueOf(rs.getInt(IsSOTrx ? "M_PriceList_ID"
								: "PO_PriceList_ID"));
				if (!rs.wasNull()) {
					mTab.setValue("M_PriceList_ID", ii);
				}
				// else#04664 — Documento INVALIDO en Recepción de Material
				// debido a Lista de Precios Ealvarez
				// { // get default PriceList
				// int i = Env.getContextAsInt(ctx, "#M_PriceList_ID");
				// if (i != 0){
				// mTab.setValue("M_PriceList_ID", Integer.valueOf(i));
				// }
				// }

				// Bill-To
				mTab.setValue("Bill_BPartner_ID", C_BPartner_ID);
				int bill_Location_ID = rs.getInt("Bill_Location_ID");
				if (bill_Location_ID == 0) {
					mTab.setValue("Bill_Location_ID", null);
				} else {
					mTab.setValue("Bill_Location_ID",
							Integer.valueOf(bill_Location_ID));
				}
				// Ship-To Location
				int shipTo_ID = rs.getInt("C_BPartner_Location_ID");
				// overwritten by InfoBP selection - works only if InfoWindow
				// was used otherwise creates error (uses last value, may belong
				// to differnt BP)
				if (C_BPartner_ID.toString().equals(
						Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
								"C_BPartner_ID"))) {
					String loc = Env.getContext(ctx, Env.WINDOW_INFO,
							Env.TAB_INFO, "C_BPartner_Location_ID");
					if (loc.length() > 0) {
						shipTo_ID = Integer.parseInt(loc);
					}
				}
				if (shipTo_ID == 0) {
					mTab.setValue("C_BPartner_Location_ID", null);
				} else {
					mTab.setValue("C_BPartner_Location_ID",
							Integer.valueOf(shipTo_ID));
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
					mTab.setValue("AD_User_ID", Integer.valueOf(contID));
					mTab.setValue("Bill_User_ID", Integer.valueOf(contID));
				}

				// CreditAvailable
				if (IsSOTrx) {
					double CreditLimit = rs.getDouble("SO_CreditLimit");
					// String SOCreditStatus = rs.getString("SOCreditStatus");
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
				String s = rs.getString("POReference");
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
				// Defaults, if not Walkin Receipt or Walkin Invoice
				String OrderType = Env.getContext(ctx, WindowNo, "OrderType");
				mTab.setValue("InvoiceRule", MOrder.INVOICERULE_AfterDelivery);
				mTab.setValue("DeliveryRule", MOrder.DELIVERYRULE_Availability);
				mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_OnCredit);
				if (OrderType.equals(MOrder.DocSubTypeSO_Prepay)) {
					mTab.setValue("InvoiceRule", MOrder.INVOICERULE_Immediate);
					mTab.setValue("DeliveryRule",
							MOrder.DELIVERYRULE_AfterReceipt);
				} else if (OrderType.equals(MOrder.DocSubTypeSO_POS)) { // for
																		// POS
					mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_Cash);
				} else {
					// PaymentRule
					s = rs.getString(IsSOTrx ? "PaymentRule" : "PaymentRulePO");
					if (s != null && s.length() != 0) {
						if ("B".equals(s)) { // No Cache in Non POS
							s = "P";
						}
						if (IsSOTrx && ("S".equals(s) || "U".equals(s))) { // No
																			// Check/Transfer
																			// for
																			// SO_Trx
							s = "P"; // Payment Term
						}
						mTab.setValue("PaymentRule", s);
					}
					// Payment Term
					ii = Integer.valueOf(rs.getInt(IsSOTrx ? "C_PaymentTerm_ID"
							: "PO_PaymentTerm_ID"));
					if (!rs.wasNull()) {
						mTab.setValue("C_PaymentTerm_ID", ii);
					}
					// InvoiceRule
					s = rs.getString("InvoiceRule");
					if (s != null && s.length() != 0) {
						mTab.setValue("InvoiceRule", s);
					}
					// DeliveryRule
					s = rs.getString("DeliveryRule");
					if (s != null && s.length() != 0) {
						mTab.setValue("DeliveryRule", s);
					}
					// FreightCostRule
					s = rs.getString("FreightCostRule");
					if (s != null && s.length() != 0) {
						mTab.setValue("FreightCostRule", s);
					}
					// DeliveryViaRule
					s = rs.getString("DeliveryViaRule");
					if (s != null && s.length() != 0) {
						mTab.setValue("DeliveryViaRule", s);
					}
				}
			}
			rs.close();
			pstmt.close();
			// expert
			rs = null;
			pstmt = null;
			// expert
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
			setCalloutActive(false);
			return e.getLocalizedMessage();
		}
		setCalloutActive(false);
		return "";
	} // bPartner

	/**
	 * Order Header - Invoice BPartner. - M_PriceList_ID (+ Context) -
	 * Bill_Location_ID - Bill_User_ID - POReference - SO_Description -
	 * IsDiscountPrinted - InvoiceRule/PaymentRule - C_PaymentTerm_ID
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
	public String bPartnerBill(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		if (isCalloutActive()) {
			return "";
		}
		Integer bill_BPartner_ID = null;
		if (value instanceof BigDecimal) {
			bill_BPartner_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			bill_BPartner_ID = (Integer) value;
		}
		if (bill_BPartner_ID == null || bill_BPartner_ID.intValue() == 0) {
			return "";
		}

		String sql = "SELECT p.AD_Language,p.C_PaymentTerm_ID,"
				+ "p.M_PriceList_ID,p.PaymentRule,p.POReference,"
				+ "p.SO_Description,p.IsDiscountPrinted,"
				+ "p.InvoiceRule,p.DeliveryRule,p.FreightCostRule,DeliveryViaRule,"
				+ "p.SO_CreditLimit, p.SO_CreditLimit-p.SO_CreditUsed AS CreditAvailable,"
				+ "c.AD_User_ID,"
				+ "p.PO_PriceList_ID, p.PaymentRulePO, p.PO_PaymentTerm_ID,"
				+ "lbill.C_BPartner_Location_ID AS Bill_Location_ID "
				+ "FROM C_BPartner p"
				+ " LEFT OUTER JOIN C_BPartner_Location lbill ON (p.C_BPartner_ID=lbill.C_BPartner_ID AND lbill.IsBillTo='Y' AND lbill.IsActive='Y')"
				+ " LEFT OUTER JOIN AD_User c ON (p.C_BPartner_ID=c.C_BPartner_ID) "
				+ "WHERE p.C_BPartner_ID=? AND p.IsActive='Y'"; // #1

		boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));

		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, bill_BPartner_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// PriceList (indirect: IsTaxIncluded & Currency)
				Integer ii = Integer
						.valueOf(rs.getInt(IsSOTrx ? "M_PriceList_ID"
								: "PO_PriceList_ID"));
				if (!rs.wasNull()) {
					mTab.setValue("M_PriceList_ID", ii);
				}
				// else#04664 — Documento INVALIDO en Recepción de Material
				// debido a Lista de Precios Ealvarez
				// { // get default PriceList
				// int i = Env.getContextAsInt(ctx, "#M_PriceList_ID");
				// if (i != 0){
				// mTab.setValue("M_PriceList_ID", Integer.valueOf(i));
				// }
				// }

				int bill_Location_ID = rs.getInt("Bill_Location_ID");
				// overwritten by InfoBP selection - works only if InfoWindow
				// was used otherwise creates error (uses last value, may belong
				// to differnt BP)
				if (bill_BPartner_ID.toString().equals(
						Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
								"C_BPartner_ID"))) {
					String loc = Env.getContext(ctx, Env.WINDOW_INFO,
							Env.TAB_INFO, "C_BPartner_Location_ID");
					if (loc.length() > 0) {
						bill_Location_ID = Integer.parseInt(loc);
					}
				}
				if (bill_Location_ID == 0) {
					mTab.setValue("Bill_Location_ID", null);
				} else {
					mTab.setValue("Bill_Location_ID",
							Integer.valueOf(bill_Location_ID));
				}
				// Contact - overwritten by InfoBP selection
				int contID = rs.getInt("AD_User_ID");
				if (bill_BPartner_ID.toString().equals(
						Env.getContext(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
								"C_BPartner_ID"))) {
					String cont = Env.getContext(ctx, Env.WINDOW_INFO,
							Env.TAB_INFO, "AD_User_ID");
					if (cont.length() > 0) {
						contID = Integer.parseInt(cont);
					}
				}
				if (contID == 0) {
					mTab.setValue("Bill_User_ID", null);
				} else {
					mTab.setValue("Bill_User_ID", Integer.valueOf(contID));
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
				String s = rs.getString("POReference");
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
				// Defaults, if not Walkin Receipt or Walkin Invoice
				String OrderType = Env.getContext(ctx, WindowNo, "OrderType");
				mTab.setValue("InvoiceRule", MOrder.INVOICERULE_AfterDelivery);
				mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_OnCredit);
				if (OrderType.equals(MOrder.DocSubTypeSO_Prepay)) {
					mTab.setValue("InvoiceRule", MOrder.INVOICERULE_Immediate);
				} else if (OrderType.equals(MOrder.DocSubTypeSO_POS)) { // for
																		// POS
					mTab.setValue("PaymentRule", MOrder.PAYMENTRULE_Cash);
				} else {
					// PaymentRule
					s = rs.getString(IsSOTrx ? "PaymentRule" : "PaymentRulePO");
					if (s != null && s.length() != 0) {
						if ("B".equals(s)) { // No Cache in Non POS
							s = "P";
						}
						if (IsSOTrx && ("S".equals(s) || "U".equals(s))) { // No
																			// Check/Transfer
																			// for
																			// SO_Trx
							s = "P"; // Payment Term
						}
						mTab.setValue("PaymentRule", s);
					}
					// Payment Term
					ii = Integer.valueOf(rs.getInt(IsSOTrx ? "C_PaymentTerm_ID"
							: "PO_PaymentTerm_ID"));
					if (!rs.wasNull()) {
						mTab.setValue("C_PaymentTerm_ID", ii);
					}
					// InvoiceRule
					s = rs.getString("InvoiceRule");
					if (s != null && s.length() != 0) {
						mTab.setValue("InvoiceRule", s);
					}
				}
			}
			rs.close();
			pstmt.close();
			// expert
			rs = null;
			pstmt = null;
			// expert
		} catch (SQLException e) {
			log.log(Level.SEVERE, "bPartnerBill", e);
			return e.getLocalizedMessage();
		}

		return "";
	} // bPartnerBill

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

		if (steps) {
			log.warning("init");
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
		if (steps) {
			log.warning("fini");
		}

		return "";
	} // priceList

	/*************************************************************************
	 * Order Line - Product. - reset C_Charge_ID / M_AttributeSetInstance_ID -
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
		if (value instanceof BigDecimal) {
			M_Product_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			M_Product_ID = (Integer) value;
		}
		if (M_Product_ID == null || M_Product_ID.intValue() == 0)
			return "";

//		// Validar que este dentro del charge master
//		final MProduct product = new MProduct(ctx, M_Product_ID, null);
//		if (M_Product_ID>0 && MEXMEMejoras.isControlaExistencias(
//				Env.getAD_Client_ID(Env.getCtx()),
//				Env.getAD_Org_ID(Env.getCtx()), null)
//				&& (product.getProdOrg() == null || (product.getProdOrg() != null && product
//						.getProdOrg().getAD_Org_ID() <= 0))) {
//			return Utilerias.getLabel("msj.ligarProducto");
//		}

		// Validar si el producto este a nivel de organización
		final MProduct product = new MProduct(ctx, M_Product_ID, null);
		final String error = MProduct.isValidProductOrg(ctx, M_Product_ID, false);
		if(error!=null){
			mTab.setValue("M_Product_ID", null);
			mTab.setValue("M_Locator_ID", null);
			mTab.setValue("Line", null);
			setCalloutActive(false);
			return error;
		}
				
		setCalloutActive(true);
		if (steps)
			log.warning("init");

		//
		mTab.setValue("C_Charge_ID", null);
		// Set Attribute
		if (Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
				"M_Product_ID") == M_Product_ID.intValue()
				&& Env.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
						"M_AttributeSetInstance_ID") != 0) {
			mTab.setValue("M_AttributeSetInstance_ID", Integer.valueOf(Env
					.getContextAsInt(ctx, Env.WINDOW_INFO, Env.TAB_INFO,
							"M_AttributeSetInstance_ID")));
		} else {
			mTab.setValue("M_AttributeSetInstance_ID", null);
		}
		/***** Price Calculation see also qty ****/
		int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
		BigDecimal Qty = (BigDecimal) mTab.getValue("QtyOrdered");
		boolean IsSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		MProductPricing pp = new MProductPricing(M_Product_ID.intValue(),
				C_BPartner_ID, Qty, IsSOTrx);
		//
		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_PriceList_ID");
		pp.setM_PriceList_ID(M_PriceList_ID);
		/** PLV is only accurate if PL selected in header */
		int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_PriceList_Version_ID");
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		Timestamp orderDate = (Timestamp) mTab.getValue("DateOrdered");
		pp.setPriceDate(orderDate);
		//
		mTab.setValue("PriceList", pp.getPriceList());
		mTab.setValue("PriceLimit", pp.getPriceLimit());
		mTab.setValue("PriceActual", pp.getPriceStd());
		mTab.setValue("PriceEntered", pp.getPriceStd());
		mTab.setValue("C_Currency_ID", Integer.valueOf(pp.getC_Currency_ID()));
		mTab.setValue("Discount", pp.getDiscount());
		mTab.setValue("C_UOM_ID", Integer.valueOf(pp.getC_UOM_ID()));

		// Expert: twry..inicio
		// siempre y cuando sea una orden de compra se obtine la unidad de
		// medida del proveedor
		if (!IsSOTrx) {
			MProductPO poo = MProductPO.getOfProduct(ctx,
					M_Product_ID.intValue(), C_BPartner_ID);
			if (poo != null)
				mTab.setValue("C_UOM_ID", Integer.valueOf(poo.getC_UOM_ID()));
		}

		// cuando es una orden de compra y no se hayo la informacion en una
		// lista de precios
		// trae la informacion de M_Product_PO
		if (!IsSOTrx) {
			if (pp.getPriceList() == null || pp.getPriceLimit() == null
					|| pp.getPriceStd() == null
					|| pp.getPriceList().compareTo(Env.ZERO) <= 0
					|| pp.getPriceLimit().compareTo(Env.ZERO) <= 0
					|| pp.getPriceStd().compareTo(Env.ZERO) <= 0
					|| pp.getC_Currency_ID() <= 0 || pp.getC_UOM_ID() <= 0) {

				MProductPO po = MProductPO.getOfProduct(ctx,
						M_Product_ID.intValue(), C_BPartner_ID);
				if (!IsSOTrx && po == null) {
					setCalloutActive(false);
					return "No hay informaci\u00F3n de compra sobre este producto.";
				}

				mTab.setValue("PriceList", po.getPriceList());
				mTab.setValue("PriceLimit", BigDecimal.ZERO);
				mTab.setValue("PriceActual", po.getPricePO());
				mTab.setValue("PriceEntered", po.getPricePO());
				mTab.setValue("C_Currency_ID",
						Integer.valueOf(po.getC_Currency_ID()));
				mTab.setValue("Discount", BigDecimal.ZERO);
				mTab.setValue("C_UOM_ID", Integer.valueOf(po.getC_UOM_ID()));
			}
		}
		// Expert: twry..fin
		//
		mTab.setValue("QtyOrdered", mTab.getValue("QtyEntered"));
		Env.setContext(ctx, WindowNo, "EnforcePriceLimit",
				pp.isEnforcePriceLimit() ? "Y" : "N");
		Env.setContext(ctx, WindowNo, "DiscountSchema",
				pp.isDiscountSchema() ? "Y" : "N");

		// Check/Update Warehouse Setting
		// int M_Warehouse_ID = Env.getContextAsInt(ctx, Env.WINDOW_INFO,
		// "M_Warehouse_ID");
		// Integer wh = (Integer)mTab.getValue("M_Warehouse_ID");
		// if (wh.intValue() != M_Warehouse_ID)
		// {
		// mTab.setValue("M_Warehouse_ID", new Integer(M_Warehouse_ID));
		// ADialog.warn(,WindowNo, "WarehouseChanged");
		// }

		if (Env.isSOTrx(ctx, WindowNo)) {
			if (product.isStocked()) {
				BigDecimal QtyOrdered = (BigDecimal) mTab
						.getValue("QtyOrdered");
				int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo,
						"M_Warehouse_ID");
				int M_AttributeSetInstance_ID = Env.getContextAsInt(ctx,
						WindowNo, "M_AttributeSetInstance_ID");
				BigDecimal available = MStorage.getQtyAvailable(M_Warehouse_ID,
						M_Product_ID.intValue(), M_AttributeSetInstance_ID,
						null);
				if (available == null)
					available = Env.ZERO;
				if (available.signum() == 0) {
					mTab.fireDataStatusEEvent("NoQtyAvailable", "0", false);
				} else if (available.compareTo(QtyOrdered) < 0) {
					mTab.fireDataStatusEEvent("InsufficientQtyAvailable",
							available.toString(), false);
				} else {
					Integer C_OrderLine_ID = (Integer) mTab
							.getValue("C_OrderLine_ID");
					if (C_OrderLine_ID == null)
						C_OrderLine_ID = Integer.valueOf(0);
					BigDecimal notReserved = MOrderLine.getNotReserved(ctx,
							M_Warehouse_ID, M_Product_ID,
							M_AttributeSetInstance_ID,
							C_OrderLine_ID.intValue());
					if (notReserved == null)
						notReserved = Env.ZERO;
					BigDecimal total = available.subtract(notReserved);
					if (total.compareTo(QtyOrdered) < 0) {
						String info = Msg.parseTranslation(ctx,
								"@QtyAvailable@=" + available
										+ " - @QtyNotReserved@=" + notReserved
										+ " = " + total);
						mTab.fireDataStatusEEvent("InsufficientQtyAvailable",
								info, false);
					}
				}
			}
		}
		//
		setCalloutActive(false);
		if (steps)
			log.warning("fini");
		return tax(ctx, WindowNo, mTab, mField, value);
	} // product

	/**
	 * Order Line - Charge. - updates PriceActual from Charge - sets PriceLimit,
	 * PriceList to zero Calles tax
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
		if (value instanceof BigDecimal) {
			C_Charge_ID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			C_Charge_ID = (Integer) value;
		}
		if (C_Charge_ID == null || C_Charge_ID.intValue() == 0) {
			return "";
		}
		// No Product defined
		if (mTab.getValue("M_Product_ID") != null) {
			mTab.setValue("C_Charge_ID", null);
			return "ChargeExclusively";
		}
		mTab.setValue("M_AttributeSetInstance_ID", null);
		mTab.setValue("S_ResourceAssignment_ID", null);
		mTab.setValue("C_UOM_ID", Integer.valueOf(100)); // EA

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
			log.log(Level.SEVERE, sql, e);
			return e.getLocalizedMessage();
		}
		//
		return tax(ctx, WindowNo, mTab, mField, value);
	} // charge

	/**
	 * Order Line - Tax. - basis: Product, Charge, BPartner Location - sets
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
	 * Order Line - Amount. - called from QtyOrdered, Discount and PriceActual -
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
	 * Order Line - Quantity. - called from C_UOM_ID, QtyEntered, QtyOrdered -
	 * enforces qty UOM relationship
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
		if (isCalloutActive() || value == null)
			return "";
		setCalloutActive(true);

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		if (steps)
			log.warning("init - M_Product_ID=" + M_Product_ID + " - ");
		BigDecimal QtyOrdered = Env.ZERO;
		BigDecimal QtyEntered, PriceActual, PriceEntered;

		// No Product
		if (M_Product_ID == 0) {
			QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
			QtyOrdered = QtyEntered;
			mTab.setValue("QtyOrdered", QtyOrdered);
		}
		// UOM Changed - convert from Entered -> Product
		else if (mField.getColumnName().equals("C_UOM_ID")) {
			int C_UOM_To_ID = 0;
			if (value instanceof BigDecimal) {
				C_UOM_To_ID = Integer.valueOf(value.toString());
			} else if (value instanceof Integer) {
				C_UOM_To_ID = ((Integer) value).intValue();
			}
			QtyEntered = (BigDecimal) mTab.getValue("QtyEntered");
			QtyOrdered = MEXMEUOMConversion.convertProductFrom(ctx,
					M_Product_ID, C_UOM_To_ID, QtyEntered, null); // expert
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			PriceActual = (BigDecimal) mTab.getValue("PriceActual");
			PriceEntered = MEXMEUOMConversion.convertProductFrom(ctx,
					M_Product_ID, C_UOM_To_ID, PriceActual, null, false); // expert
			if (PriceEntered == null)
				PriceEntered = PriceActual;
			log.fine("UOM=" + C_UOM_To_ID + ", QtyEntered/PriceActual="
					+ QtyEntered + "/" + PriceActual + " -> " + conversion
					+ " QtyOrdered/PriceEntered=" + QtyOrdered + "/"
					+ PriceEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y"
					: "N");
			mTab.setValue("QtyOrdered", QtyOrdered);
			mTab.setValue("PriceEntered", PriceEntered);
		}
		// QtyEntered changed - calculate QtyOrdered
		else if (mField.getColumnName().equals("QtyEntered")) {
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyEntered = (BigDecimal) value;
			QtyOrdered = MEXMEUOMConversion.convertProductFrom(ctx,
					M_Product_ID, C_UOM_To_ID, QtyEntered, null); // Expert:Twry
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			log.fine("UOM=" + C_UOM_To_ID + ", QtyEntered=" + QtyEntered
					+ " -> " + conversion + " QtyOrdered=" + QtyOrdered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y"
					: "N");
			mTab.setValue("QtyOrdered", QtyOrdered);
		}
		// QtyOrdered changed - calculate QtyEntered
		else if (mField.getColumnName().equals("QtyOrdered")) {
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyOrdered = (BigDecimal) value;
			QtyEntered = MEXMEUOMConversion.convertProductTo(ctx, M_Product_ID,
					C_UOM_To_ID, QtyOrdered, null); // expert
			if (QtyEntered == null)
				QtyEntered = QtyOrdered;
			boolean conversion = QtyOrdered.compareTo(QtyEntered) != 0;
			log.fine("UOM=" + C_UOM_To_ID + ", QtyOrdered=" + QtyOrdered
					+ " -> " + conversion + " QtyEntered=" + QtyEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y"
					: "N");
			mTab.setValue("QtyEntered", QtyEntered);
		} else {
			// QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
			QtyOrdered = (BigDecimal) mTab.getValue("QtyOrdered");
		}

		// Storage
		if (M_Product_ID != 0 && Env.isSOTrx(ctx, WindowNo)
				&& QtyOrdered.signum() > 0) // no negative (returns)
		{
			MProduct product = MProduct.get(ctx, M_Product_ID);
			if (product.isStocked()) {
				int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo,
						"M_Warehouse_ID");
				int M_AttributeSetInstance_ID = Env.getContextAsInt(ctx,
						WindowNo, "M_AttributeSetInstance_ID");
				BigDecimal available = MStorage.getQtyAvailable(M_Warehouse_ID,
						M_Product_ID, M_AttributeSetInstance_ID, null);
				if (available == null) {
					available = Env.ZERO;
				} // correccion de error en corchetes (#30038 10/17/2012)
				if (available.signum() == 0) {
					mTab.fireDataStatusEEvent("NoQtyAvailable", "0", false);
				} else if (available.compareTo(QtyOrdered) < 0) {
					mTab.fireDataStatusEEvent("InsufficientQtyAvailable",
							available.toString(), false);
				} else {
					Integer C_OrderLine_ID = (Integer) mTab
							.getValue("C_OrderLine_ID");
					if (C_OrderLine_ID == null) {
						C_OrderLine_ID = Integer.valueOf(0);
					}
					BigDecimal notReserved = MOrderLine.getNotReserved(ctx,
							M_Warehouse_ID, M_Product_ID,
							M_AttributeSetInstance_ID,
							C_OrderLine_ID.intValue());
					if (notReserved == null) {	
						notReserved = Env.ZERO;
					}
					BigDecimal total = available.subtract(notReserved);
					if (total.compareTo(QtyOrdered) < 0) {
						String info = Msg.parseTranslation(ctx,
								"@QtyAvailable@=" + available
										+ "  -  @QtyNotReserved@="
										+ notReserved + "  =  " + total);
						mTab.fireDataStatusEEvent("InsufficientQtyAvailable",
								info, false);
					}
				}
			}
		}
		//
		setCalloutActive(false);
		return "";
	} // qty

	// expert : miguel rojas : para determinar la unidad de medida a mostrar
	public String getUOM(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		if (isCalloutActive() || value == null)
			return "";

		setCalloutActive(true);
		boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));
		// recuperamos la unidad de medida de compra de acuerdo al proveedor
		if (!IsSOTrx) {
			try {
				/*
				 * int M_Product_ID = Env.getContextAsInt(ctx, WindowNo,
				 * "M_Product_ID"); int C_BPartnet_ID = Env.getContextAsInt(ctx,
				 * WindowNo, "C_BPartner_ID");
				 * 
				 * MProductPO productPO = MProductPO.getOfProduct(ctx,
				 * M_Product_ID, C_BPartnet_ID);
				 * 
				 * if(productPO == null) return
				 * "No hay informaci\u00F3n de compra sobre este producto.";
				 * 
				 * mTab.setValue("C_UOM_ID", new
				 * Integer(productPO.getC_UOM_ID()));
				 */

			} catch (Exception e) {
				e.printStackTrace();
				setCalloutActive(false);
				return e.getMessage();
			}
		}

		setCalloutActive(false);
		return "";
	}

	// expert : miguel rojas : para determinar la unidad de medida a mostrar
	/**
	 * 
	 */
	public String amtVol(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		StringBuilder msg = new StringBuilder("");
		String errorconversion = "error.udm.noExisteConversion";

		if (isCalloutActive() || value == null)
			return msg.toString();
		setCalloutActive(true);
		int mProductID = mTab.getValue("M_Product_ID") == null ? 0 : Integer
				.valueOf(mTab.getValue("M_Product_ID").toString());
		MProduct mProduct = new MProduct(ctx, mProductID, null);
		MUOMConversion rates = MEXMEUOMConversion.validateConversions(
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

		BigDecimal qtyEnteredVol, priceEnteredVol, priceActualVol, Discount, priceListVol;
		// // get values
		qtyEnteredVol = (BigDecimal) mTab.getValue("QtyEntered_Vol");// Tambien
																		// es la
																		// cantidad
																		// ordenada
		log.fine("QtyEntered=" + qtyEnteredVol + ", UOM="
				+ mProduct.getC_UOM_ID());
		//
		priceEnteredVol = (BigDecimal) mTab.getValue("PriceEntered_Vol");
		priceActualVol = (BigDecimal) mTab.getValue("PriceActual_Vol");
		// Discount = (BigDecimal)mTab.getValue("Discount");
		// PriceLimit = (BigDecimal)mTab.getValue("PriceLimit");
		priceListVol = (BigDecimal) mTab.getValue("PriceList_Vol");

		// // Qty changed - recalc price
		if ((mField.getColumnName().equals("QtyOrdered_Vol")
				|| mField.getColumnName().equals("QtyEntered_Vol") || mField
				.getColumnName().equals("M_Product_ID"))
		/* && !"N".equals(Env.getContext(ctx, WindowNo, "DiscountSchema")) */) {

			if (mField.getColumnName().equals("QtyEntered_Vol"))
				mTab.setValue("QtyOrdered_Vol", qtyEnteredVol);
			// Convertit las unidades de volumen a unidades minimas
			if (mProduct.getC_UOM_ID() != mProduct.getC_UOMVolume_ID()) {
				mTab.setValue("QtyEntered", MEXMEUOMConversion
						.convertProductFrom(Env.getCtx(),
								mProduct.getM_Product_ID(), cUOMvOLID,
								qtyEnteredVol, null));
				mTab.setValue("QtyOrdered", MEXMEUOMConversion
						.convertProductFrom(Env.getCtx(),
								mProduct.getM_Product_ID(), cUOMvOLID,
								qtyEnteredVol, null));
				// Env.setContext(ctx, WindowNo, "DiscountSchema",
				// pp.isDiscountSchema() ? "Y" : "N");
			} else {
				mTab.setValue("QtyEntered", qtyEnteredVol);
				mTab.setValue("QtyOrdered", qtyEnteredVol);
			}
		} else if (mField.getColumnName().equals("PriceActual_Vol")) {

			log.fine("PriceActual=" + priceActualVol + " -> PriceEntered="
					+ priceEnteredVol);
			priceEnteredVol = priceActualVol;
			mTab.setValue("PriceEntered_Vol", priceActualVol);
			if (mProduct.getC_UOM_ID() != mProduct.getC_UOMVolume_ID()) {
				mTab.setValue(
						"PriceEntered",
						mProduct.getM_Product_ID() <= 0 ? priceActualVol
								: MEXMEUOMConversion.convertProductTo(
										Env.getCtx(),
										mProduct.getM_Product_ID(), cUOMvOLID,
										priceActualVol, null, false));
				mTab.setValue(
						"PriceActual",
						mProduct.getM_Product_ID() <= 0 ? priceActualVol
								: MEXMEUOMConversion.convertProductTo(
										Env.getCtx(),
										mProduct.getM_Product_ID(), cUOMvOLID,
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
			mTab.setValue(
					"PriceEntered",
					mProduct.getM_Product_ID() <= 0 ? priceEnteredVol
							: MEXMEUOMConversion.convertProductTo(Env.getCtx(),
									mProduct.getM_Product_ID(), cUOMvOLID,
									priceEnteredVol, null, false));
			mTab.setValue(
					"PriceActual",
					mProduct.getM_Product_ID() <= 0 ? priceEnteredVol
							: MEXMEUOMConversion.convertProductTo(Env.getCtx(),
									mProduct.getM_Product_ID(), cUOMvOLID,
									priceEnteredVol, null, false));

		} else if (mField.getColumnName().equals("PriceList_Vol")) {
			log.fine("PriceList_Vol=" + priceListVol);
			mTab.setValue(
					"PriceList",
					mProduct.getM_Product_ID() <= 0 ? priceListVol
							: MEXMEUOMConversion.convertProductTo(Env.getCtx(),
									mProduct.getM_Product_ID(), cUOMvOLID,
									priceListVol, null, false));
			if (priceListVol.intValue() == 0) {
				Discount = Env.ZERO;
			} else {
				Discount = new BigDecimal(
						(priceListVol.doubleValue() - priceActualVol
								.doubleValue())
								/ priceListVol.doubleValue()
								* 100.0);
			}
			if (Discount.scale() > 2) {
				Discount = Discount.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			mTab.setValue("Discount", Discount);
		}

		if (priceListVol.intValue() != 0) {
			Discount = new BigDecimal(
					(priceListVol.doubleValue() - priceActualVol.doubleValue())
							/ priceListVol.doubleValue() * 100.0);
			if (Discount.scale() > 2)
				Discount = Discount.setScale(2, BigDecimal.ROUND_HALF_UP);
			mTab.setValue("Discount", Discount);
		}

		// Line Net Amt Calcular siempre.
		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_PriceList_ID");
		BigDecimal LineNetAmt = qtyEnteredVol.multiply(priceActualVol);
		int StdPrecision = MPriceList.getStandardPrecision(ctx, M_PriceList_ID);
		if (LineNetAmt.scale() > StdPrecision)
			LineNetAmt = LineNetAmt.setScale(StdPrecision,
					BigDecimal.ROUND_HALF_UP);
		log.info("LineNetAmt=" + LineNetAmt);
		mTab.setValue("LineNetAmt", LineNetAmt);

		mTab.setValue("C_UOMVolume_ID", cUOMvOLID);
		setCalloutActive(false);
		return msg.toString();
	} // amt

	public String productVol(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer mProductID = null;
		if (value instanceof BigDecimal) {
			mProductID = Integer.valueOf(value.toString());
		} else if (value instanceof Integer) {
			mProductID = (Integer) value;
		}
		if (mProductID == null || mProductID.intValue() == 0)
			return "";
		setCalloutActive(true);

		// se limpian los datos de cantidad
		mTab.setValue("QtyOrdered_Vol", BigDecimal.ONE);
		mTab.setValue("QtyEntered_Vol", BigDecimal.ONE);
		mTab.setValue("QtyEntered", BigDecimal.ONE);
		mTab.setValue("QtyOrdered", BigDecimal.ONE);
//
//		// Validar que este dentro del charge master
//		final MProduct product = new MProduct(ctx, mProductID, null);
//		if (mProductID>0 && MEXMEMejoras.isControlaExistencias(
//				Env.getAD_Client_ID(Env.getCtx()),
//				Env.getAD_Org_ID(Env.getCtx()), null)
//				&& (product.getProdOrg() == null || (product.getProdOrg() != null && product
//						.getProdOrg().getAD_Org_ID() <= 0))) {
//			setCalloutActive(false);
//			return Utilerias.getLabel("msj.ligarProducto");
//		}

		// Validar si el producto este a nivel de organización
		final MProduct product = new MProduct(ctx, mProductID, null);
		final String error = MProduct.isValidProductOrg(ctx, mProductID, false);
		if(error!=null){
			setCalloutActive(false);
			return error;
		}
		
		/***** Price Calculation see also qty ****/
		int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
		BigDecimal Qty = (BigDecimal) mTab.getValue("QtyOrdered_Vol");
		boolean IsSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		MProductPricing pp = new MProductPricing(mProductID.intValue(),
				C_BPartner_ID, Qty, IsSOTrx);

		int M_PriceList_ID = Env.getContextAsInt(ctx, WindowNo,
				"M_PriceList_ID");

		MProductPrice mPrice = MProductPrice.getProductPrice(C_BPartner_ID,
				M_PriceList_ID, product.getM_Product_ID(), IsSOTrx);

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
			mTab.setValue("PriceEntered_Vol", Env.ZERO);
			mTab.setValue("PriceActual_Vol", Env.ZERO);
			if (product.getC_UOM_ID() == product.getC_UOMVolume_ID()) {
				mTab.setValue("PriceActual", Env.ZERO);
				mTab.setValue("PriceList", Env.ZERO);
				mTab.setValue("PriceEntered", Env.ZERO);
			} else {
				mTab.setValue("PriceActual", Env.ZERO);
				mTab.setValue("PriceList", Env.ZERO);
				mTab.setValue("PriceEntered", Env.ZERO);
				// mTab.setValue("QtyEntered",
				// MEXMEUOMConversion.convertProductFrom(Env.getCtx(),
				// product.getM_Product_ID(), product.getC_UOMVolume_ID(),
				// Env.ONE, null));
				// mTab.setValue("QtyOrdered",
				// MEXMEUOMConversion.convertProductFrom(Env.getCtx(),
				// product.getM_Product_ID(), product.getC_UOMVolume_ID(),
				// Env.ONE, null));
			}

		}

		// si las dos presentaciones son diferentes, verificamos si tiene
		// conversiones
		if (product.getC_UOM_ID() != product.getC_UOMVolume_ID()) {
			final MUOMConversion rates = MEXMEUOMConversion
					.validateConversions(Env.getCtx(),
							product.getM_Product_ID(),
							product.getC_UOMVolume_ID(), null);

			if (rates == null) {
				log.saveError(Utilerias.getMsg(Env.getCtx(),
						"error.udm.noExisteConversion"), product.getName());
				final String msg = Utilerias.getMsg(Env.getCtx(),
						"error.udm.noExisteConversion");
				// mField.setValue(null, true);
				mTab.setValue("Qty_Vol", BigDecimal.ONE);
				mTab.setValue("Qty", BigDecimal.ONE);
				setCalloutActive(false);
				return msg;
			}

			mTab.setValue(
					"QtyEntered",
					MEXMEUOMConversion.convertProductFrom(Env.getCtx(),
							product.getM_Product_ID(),
							product.getC_UOMVolume_ID(), Env.ONE, null));
			mTab.setValue(
					"QtyOrdered",
					MEXMEUOMConversion.convertProductFrom(Env.getCtx(),
							product.getM_Product_ID(),
							product.getC_UOMVolume_ID(), Env.ONE, null));

		}

		mTab.setValue("C_Currency_ID", Integer.valueOf(pp.getC_Currency_ID()));

		mTab.setValue("Discount", pp.getDiscount());
		mTab.setValue("C_UOM_ID", product.getC_UOM_ID());
		mTab.setValue("C_UOMVolume_ID", product.getC_UOMVolume_ID());

		setCalloutActive(false);
		return tax(ctx, WindowNo, mTab, mField, value);
	} // product

	/**
	 * Order Line - Quantity. - called from C_UOM_ID, QtyEntered, QtyOrdered -
	 * enforces qty UOM relationship
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
		if (isCalloutActive() || value == null)
			return "";
		setCalloutActive(true);

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		BigDecimal QtyOrdered = Env.ZERO;
		BigDecimal QtyEntered;

		// // No Product
		if (M_Product_ID == 0) {
			QtyEntered = (BigDecimal) mTab.getValue("QtyEntered_Vol");
			QtyOrdered = QtyEntered;
			mTab.setValue("QtyOrdered", QtyOrdered);
		}

		QtyEntered = (BigDecimal) mTab.getValue("QtyEntered_Vol");

		mTab.setValue("QtyOrdered", QtyOrdered);

		setCalloutActive(false);
		return "";
	} // qty

} // CalloutOrder

