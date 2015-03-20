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
package org.compiere.process;

import java.math.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.*;

import org.compiere.model.*;
import org.compiere.util.*;

/**
 * Copy Product Catergory Default Accounts
 *
 * @author Jorg Janke
 * @version $Id: ProductCategoryAcctCopy.java,v 1.2 2006/07/30 00:51:02 jjanke
 *          Exp $
 */
public class ProductCategoryAcctCopy extends SvrProcess {

	
	private int P_Revenue_Acct =0;
	private int P_Expense_Acct =0;
	private int P_CostAdjustment_Acct =0;
	private int P_InventoryClearing_Acct =0;
	private int P_Asset_Acct =0;
	private int P_COGS_Acct =0;

	private int P_PurchasePriceVariance_Acct =0;
	private int P_InvoicePriceVariance_Acct =0;

	private int P_TradeDiscountRec_Acct =0;
	private int P_TradeDiscountGrant_Acct =0;
	private int EXME_Prov_Vta_Acct =0;
	private int P_ExpenseFgn_Acct =0;

	private int P_RevenueTGCash_Acct =0;
	private int P_RevenueTGCredit_Acct =0;
	private int P_RevenueTZCash_Acct =0;
	private int P_RevenueTZCredit_Acct =0;

	private int P_RevenueTECash_Acct =0;
	private int P_RevenueTECredit_Acct =0;
	private int P_TradeDiscountGrantG_Acct =0;

	private int P_TradeDiscountGrantZ_Acct =0;
	private int P_TradeDiscountGrantE_Acct =0;
	
	private int P_RevenueFgn_Acct =0;
	private int P_InventoryClearingFgn_Acct =0;
	
	private int P_PurchasesReturns_Acct = 0;
	private int P_SalesReturns_Acct =0;
	
	private int P_SalesReturnsG_Acct =0;
	private int P_SalesReturnsZ_Acct =0;
	private int P_SalesReturnsE_Acct =0;

	private int updated = 0;
	/** Product Categpory */
	private int p_M_Product_Category_ID = 0;
	/** Acct Schema */
	private int p_C_AcctSchema_ID = 0;
	/** Context */
	private Properties ctx = null;
	/** Copy & Overwrite */
	private boolean p_CopyOverwriteAcct = false;
	/** Copy From */
	private boolean CopyFrom = false;
	/** Copy Of */
	private boolean CopyOf = false;

	private static CLogger slog = CLogger.getCLogger(MCtaPacDet.class);

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_Category_ID")) {
				p_M_Product_Category_ID = para[i].getParameterAsInt();
			} else if ("CopyOverwriteAcct".equals(name)) {
				p_CopyOverwriteAcct = "Y".equals(para[i].getParameter());
			} else if (name.equals("C_AcctSchema_ID")) {
				p_C_AcctSchema_ID = para[i].getParameterAsInt();
			} else if ("CopyFrom".equals(name)) {
				CopyFrom = "Y".equals(para[i].getParameter());
			} else if ("CopyOf".equals(name)) {
				CopyOf = "Y".equals(para[i].getParameter());
			}
			log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	} // prepare

	public String doIt(Properties pctx, int pMProductCategoryID, int pCAcctSchemaID) throws Exception {
		p_M_Product_Category_ID = pMProductCategoryID;
		p_C_AcctSchema_ID = pCAcctSchemaID;
		ctx = pctx;
		return doIt();
	}

	/**
	 * Process
	 *
	 * @return message
	 * @throws Exception
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	protected String doIt() throws Exception {
		if (ctx == null)
			ctx = getCtx();
		log.info("C_AcctSchema_ID=" + p_C_AcctSchema_ID);
		if (p_C_AcctSchema_ID == 0)
			throw new CompiereSystemError("C_AcctSchema_ID=0");
		MAcctSchema as = MAcctSchema.get(ctx, p_C_AcctSchema_ID);
		if (as.get_ID() == 0)
			throw new CompiereSystemError("Not Found - C_AcctSchema_ID=" + p_C_AcctSchema_ID);

		// Select
		String sql = "SELECT P_Revenue_Acct,P_Expense_Acct,P_CostAdjustment_Acct,P_InventoryClearing_Acct,P_Asset_Acct,P_COGS_Acct,"
				+ " P_PurchasePriceVariance_Acct,P_InvoicePriceVariance_Acct,"
				+ " P_TradeDiscountRec_Acct,P_TradeDiscountGrant_Acct, EXME_Prov_Vta_Acct,P_ExpenseFgn_Acct, "
				+ "P_RevenueTGCash_Acct, P_RevenueTGCredit_Acct, P_RevenueTZCash_Acct, P_RevenueTZCredit_Acct, "
				+ "P_RevenueTECash_Acct, P_RevenueTECredit_Acct, P_TradeDiscountGrantG_Acct, "
				+ "P_TradeDiscountGrantZ_Acct, P_TradeDiscountGrantE_Acct, P_RevenueFgn_Acct, P_InventoryClearingFgn_Acct, "
				+ "P_PurchasesReturns_Acct, P_SalesReturns_Acct, P_SalesReturnsG_Acct, P_SalesReturnsZ_Acct, P_SalesReturnsE_Acct"
				+ " FROM M_Product_Category_Acct pca"
				+ " WHERE pca.M_Product_Category_ID="
				+ p_M_Product_Category_ID
				+ " AND pca.C_AcctSchema_ID="
				+ p_C_AcctSchema_ID;

		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();

			while (rs.next()) {

				// Save variables
				P_Revenue_Acct = rs.getInt("P_Revenue_Acct");
				P_Expense_Acct = rs.getInt("P_Expense_Acct");
				P_CostAdjustment_Acct = rs.getInt("P_CostAdjustment_Acct");
				P_InventoryClearing_Acct = rs.getInt("P_InventoryClearing_Acct");
				P_Asset_Acct = rs.getInt("P_Asset_Acct");
				P_COGS_Acct = rs.getInt("P_COGS_Acct");

				P_PurchasePriceVariance_Acct = rs.getInt("P_PurchasePriceVariance_Acct");
				P_InvoicePriceVariance_Acct = rs.getInt("P_InvoicePriceVariance_Acct");

				P_TradeDiscountRec_Acct = rs.getInt("P_TradeDiscountRec_Acct");
				P_TradeDiscountGrant_Acct = rs.getInt("P_TradeDiscountGrant_Acct");
				EXME_Prov_Vta_Acct = rs.getInt("EXME_Prov_Vta_Acct");
				P_ExpenseFgn_Acct = rs.getInt("P_ExpenseFgn_Acct");

				P_RevenueTGCash_Acct = rs.getInt("P_RevenueTGCash_Acct");
				P_RevenueTGCredit_Acct = rs.getInt("P_RevenueTGCredit_Acct");
				P_RevenueTZCash_Acct = rs.getInt("P_RevenueTZCash_Acct");
				P_RevenueTZCredit_Acct = rs.getInt("P_RevenueTZCredit_Acct");

				P_RevenueTECash_Acct = rs.getInt("P_RevenueTECash_Acct");
				P_RevenueTECredit_Acct = rs.getInt("P_RevenueTECredit_Acct");
				P_TradeDiscountGrantG_Acct = rs.getInt("P_TradeDiscountGrantG_Acct");

				P_TradeDiscountGrantZ_Acct = rs.getInt("P_TradeDiscountGrantZ_Acct");
				P_TradeDiscountGrantE_Acct = rs.getInt("P_TradeDiscountGrantE_Acct");
				
				P_RevenueFgn_Acct = rs.getInt("P_RevenueFgn_Acct");
				P_InventoryClearingFgn_Acct = rs.getInt("P_InventoryClearingFgn_Acct");
				
				P_PurchasesReturns_Acct = rs.getInt("P_PurchasesReturns_Acct");
				P_SalesReturns_Acct = rs.getInt("P_SalesReturns_Acct");
				
				P_SalesReturnsG_Acct = rs.getInt("P_SalesReturnsG_Acct");
				P_SalesReturnsZ_Acct = rs.getInt("P_SalesReturnsZ_Acct");
				P_SalesReturnsE_Acct = rs.getInt("P_SalesReturnsE_Acct");

			}

		} catch (Exception e) {

			slog.log(Level.SEVERE, null, e);
		}
		// Update Complete
		if (p_CopyOverwriteAcct) {

			StringBuilder sql2 = new StringBuilder();

			sql2.append("UPDATE M_Product_Acct pa ");
			sql2.append("SET P_Revenue_Acct = ?,P_Expense_Acct = ?,P_CostAdjustment_Acct = ?,P_InventoryClearing_Acct = ?,P_Asset_Acct = ?,P_COGS_Acct = ?, ");
			sql2.append("P_PurchasePriceVariance_Acct = ?,P_InvoicePriceVariance_Acct = ?, ");
			sql2.append("P_TradeDiscountRec_Acct = ?,P_TradeDiscountGrant_Acct = ?, EXME_Prov_Vta_Acct = ?,P_ExpenseFgn_Acct = ?, ");
			sql2.append("P_RevenueTGCash_Acct = ?, P_RevenueTGCredit_Acct = ?, P_RevenueTZCash_Acct = ?, P_RevenueTZCredit_Acct = ?, ");
			sql2.append("P_RevenueTECash_Acct = ?, P_RevenueTECredit_Acct = ?, P_TradeDiscountGrantG_Acct = ?, ");
			sql2.append("P_TradeDiscountGrantZ_Acct = ?, P_TradeDiscountGrantE_Acct = ?, P_RevenueFgn_Acct = ?, P_InventoryClearingFgn_Acct = ?, ");
			sql2.append("P_PurchasesReturns_Acct = ?, P_SalesReturns_Acct = ?, ");
			sql2.append("P_SalesReturnsG_Acct = ?, P_SalesReturnsZ_Acct = ?, P_SalesReturnsE_Acct = ?, ");
			sql2.append("Updated= now(), UpdatedBy=0 ");
			sql2.append("WHERE pa.C_AcctSchema_ID=" + p_C_AcctSchema_ID);
			sql2.append("AND EXISTS (SELECT * FROM M_Product p ");
			sql2.append("WHERE p.M_Product_ID=pa.M_Product_ID ");
			sql2.append("AND p.M_Product_Category_ID=" + p_M_Product_Category_ID + ") ");

			try {

				List<Object> params = new ArrayList<Object>();

				// Params
				params.add(P_Revenue_Acct<=0?null:P_Revenue_Acct);
				params.add(P_Expense_Acct<=0?null:P_Expense_Acct);
				params.add(P_CostAdjustment_Acct<=0?null:P_CostAdjustment_Acct);
				params.add(P_InventoryClearing_Acct<=0?null:P_InventoryClearing_Acct);
				params.add(P_Asset_Acct<=0?null:P_Asset_Acct);
				params.add(P_COGS_Acct<=0?null:P_COGS_Acct);

				params.add(P_PurchasePriceVariance_Acct<=0?null:P_PurchasePriceVariance_Acct);
				params.add(P_InvoicePriceVariance_Acct<=0?null:P_InvoicePriceVariance_Acct);

				params.add(P_TradeDiscountRec_Acct<=0?null:P_TradeDiscountRec_Acct);
				params.add(P_TradeDiscountGrant_Acct<=0?null:P_TradeDiscountGrant_Acct);
				params.add(EXME_Prov_Vta_Acct<=0?null:EXME_Prov_Vta_Acct);
				params.add(P_ExpenseFgn_Acct<=0?null:P_ExpenseFgn_Acct);

				params.add(P_RevenueTGCash_Acct<=0?null:P_RevenueTGCash_Acct);
				params.add(P_RevenueTGCredit_Acct<=0?null:P_RevenueTGCredit_Acct);
				params.add(P_RevenueTZCash_Acct<=0?null:P_RevenueTZCash_Acct);
				params.add(P_RevenueTZCredit_Acct<=0?null:P_RevenueTZCredit_Acct);

				params.add(P_RevenueTECash_Acct<=0?null:P_RevenueTECash_Acct);
				params.add(P_RevenueTECredit_Acct<=0?null:P_RevenueTECredit_Acct);
				params.add(P_TradeDiscountGrantG_Acct<=0?null:P_TradeDiscountGrantG_Acct);

				params.add(P_TradeDiscountGrantZ_Acct<=0?null:P_TradeDiscountGrantZ_Acct);
				params.add(P_TradeDiscountGrantE_Acct<=0?null:P_TradeDiscountGrantE_Acct);
			
				params.add(P_RevenueFgn_Acct<=0?null:P_RevenueFgn_Acct);
				params.add(P_InventoryClearingFgn_Acct<=0?null:P_InventoryClearingFgn_Acct);
				
				
				params.add(P_PurchasesReturns_Acct<=0?null:P_PurchasesReturns_Acct);
				params.add(P_SalesReturns_Acct<=0?null:P_SalesReturns_Acct);
				params.add(P_SalesReturnsG_Acct<=0?null:P_SalesReturnsG_Acct);
				params.add(P_SalesReturnsZ_Acct<=0?null:P_SalesReturnsZ_Acct);
				params.add(P_SalesReturnsE_Acct<=0?null:P_SalesReturnsE_Acct);
				DB.executeUpdate(sql2.toString(), params.toArray(new Object[] {}), get_TrxName());
				updated = DB.executeUpdate(sql2.toString(), params.toArray(new Object[] {}), get_TrxName());

			} catch (Exception e) {

				slog.log(Level.SEVERE, null, e);
			}

			addLog(0, null, new BigDecimal(updated), "@Updated@");
		} else {
			// Update existing Product Category of electronic accounting
			if (CopyFrom) {
				StringBuilder sql2 = new StringBuilder();

				sql2.append("UPDATE M_Product_Acct pa ");
				sql2.append("SET P_RevenueTGCash_Acct = ?, P_RevenueTGCredit_Acct = ?, P_RevenueTZCash_Acct = ?, P_RevenueTZCredit_Acct = ?, ");
				sql2.append("P_RevenueTECash_Acct = ?, P_RevenueTECredit_Acct = ?, P_TradeDiscountGrantG_Acct = ?, ");
				sql2.append("P_TradeDiscountGrantZ_Acct = ?, P_TradeDiscountGrantE_Acct = ?, ");
				sql2.append("P_SalesReturnsG_Acct = ?, P_SalesReturnsZ_Acct = ?, P_SalesReturnsE_Acct = ?, ");
				sql2.append("Updated= now(), UpdatedBy=0 ");
				sql2.append("WHERE pa.C_AcctSchema_ID=" + p_C_AcctSchema_ID);
				sql2.append("AND EXISTS (SELECT * FROM M_Product p ");
				sql2.append("WHERE p.M_Product_ID=pa.M_Product_ID ");
				sql2.append("AND p.M_Product_Category_ID=" + p_M_Product_Category_ID + ") ");

				try {

					List<Object> params = new ArrayList<Object>();

					// Params
					params.add(P_RevenueTGCash_Acct<=0?null:P_RevenueTGCash_Acct);
					params.add(P_RevenueTGCredit_Acct<=0?null:P_RevenueTGCredit_Acct);
					params.add(P_RevenueTZCash_Acct<=0?null:P_RevenueTZCash_Acct);
					params.add(P_RevenueTZCredit_Acct<=0?null:P_RevenueTZCredit_Acct);

					params.add(P_RevenueTECash_Acct<=0?null:P_RevenueTECash_Acct);
					params.add(P_RevenueTECredit_Acct<=0?null:P_RevenueTECredit_Acct);
					params.add(P_TradeDiscountGrantG_Acct<=0?null:P_TradeDiscountGrantG_Acct);

					params.add(P_TradeDiscountGrantZ_Acct<=0?null:P_TradeDiscountGrantZ_Acct);
					params.add(P_TradeDiscountGrantE_Acct<=0?null:P_TradeDiscountGrantE_Acct);
					
					params.add(P_SalesReturnsG_Acct<=0?null:P_SalesReturnsG_Acct);
					params.add(P_SalesReturnsZ_Acct<=0?null:P_SalesReturnsZ_Acct);
					params.add(P_SalesReturnsE_Acct<=0?null:P_SalesReturnsE_Acct);
					

					DB.executeUpdate(sql2.toString(), params.toArray(new Object[] {}), get_TrxName());
					updated = DB.executeUpdate(sql2.toString(), params.toArray(new Object[] {}), get_TrxName());

				} catch (Exception e) {

					slog.log(Level.SEVERE, null, e);
				}

			}
			// Update existing Product Category of accounts foreign
			if (CopyOf) {
				StringBuilder sql2 = new StringBuilder();

				sql2.append("UPDATE M_Product_Acct pa ");
				sql2.append("SET P_ExpenseFgn_Acct = ?, P_RevenueFgn_Acct = ?, P_InventoryClearingFgn_Acct = ?, ");
				sql2.append("P_PurchasesReturns_Acct = ?, P_SalesReturns_Acct = ?, ");
				sql2.append("Updated= now(), UpdatedBy=0 ");
				sql2.append("WHERE pa.C_AcctSchema_ID=" + p_C_AcctSchema_ID);
				sql2.append("AND EXISTS (SELECT * FROM M_Product p ");
				sql2.append("WHERE p.M_Product_ID=pa.M_Product_ID ");
				sql2.append("AND p.M_Product_Category_ID=" + p_M_Product_Category_ID + ") ");

				try {

					List<Object> params = new ArrayList<Object>();

					// Param
					params.add(P_ExpenseFgn_Acct<=0?null:P_ExpenseFgn_Acct);
					params.add(P_RevenueFgn_Acct<=0?null:P_RevenueFgn_Acct);
					params.add(P_InventoryClearingFgn_Acct<=0?null:P_InventoryClearingFgn_Acct);
					params.add(P_PurchasesReturns_Acct<=0?null:P_PurchasesReturns_Acct);
					params.add(P_SalesReturns_Acct<=0?null:P_SalesReturns_Acct);
					DB.executeUpdate(sql2.toString(), params.toArray(new Object[] {}), get_TrxName());
					updated = DB.executeUpdate(sql2.toString(), params.toArray(new Object[] {}), get_TrxName());

				} catch (Exception e) {

					slog.log(Level.SEVERE, null, e);
				}

			}
			addLog(0, null, new BigDecimal(updated), "@Updated@");
		}

		// // Update
		// String sql = "UPDATE M_Product_Acct pa "
		// +
		// "SET (P_Revenue_Acct,P_Expense_Acct,P_CostAdjustment_Acct,P_InventoryClearing_Acct,P_Asset_Acct,P_COGS_Acct,"
		// + " P_PurchasePriceVariance_Acct,P_InvoicePriceVariance_Acct,"
		// +
		// " P_TradeDiscountRec_Acct,P_TradeDiscountGrant_Acct, EXME_Prov_Vta_Acct,P_ExpenseFgn_Acct, P_RevenueTGCash_Acct, P_RevenueTGCredit_Acct, P_RevenueTZCash_Acct, P_RevenueTZCredit_Acct, P_RevenueTECash_Acct, P_RevenueTECash_Acct, P_RevenueTECredit_Acct, P_TradeDiscountGrantG_Acct, P_TradeDiscountGrantZ_Acct, P_TradeDiscountGrantE_Acct)="
		// +
		// " (SELECT P_Revenue_Acct,P_Expense_Acct,P_CostAdjustment_Acct,P_InventoryClearing_Acct,P_Asset_Acct,P_COGS_Acct,"
		// + " P_PurchasePriceVariance_Acct,P_InvoicePriceVariance_Acct,"
		// +
		// " P_TradeDiscountRec_Acct,P_TradeDiscountGrant_Acct, EXME_Prov_Vta_Acct,P_ExpenseFgn_Acct, P_RevenueTGCash_Acct, P_RevenueTGCredit_Acct, P_RevenueTZCash_Acct, P_RevenueTZCredit_Acct, P_RevenueTECash_Acct, P_RevenueTECash_Acct, P_RevenueTECredit_Acct, P_TradeDiscountGrantG_Acct, P_TradeDiscountGrantZ_Acct, P_TradeDiscountGrantE_Acct "
		// + " FROM M_Product_Category_Acct pca"
		// + " WHERE pca.M_Product_Category_ID=" + p_M_Product_Category_ID
		// + " AND pca.C_AcctSchema_ID=" + p_C_AcctSchema_ID
		// + "), Updated=SysDate, UpdatedBy=0 "
		// + "WHERE pa.C_AcctSchema_ID=" + p_C_AcctSchema_ID
		// + " AND EXISTS (SELECT * FROM M_Product p "
		// + "WHERE p.M_Product_ID=pa.M_Product_ID"
		// + " AND p.M_Product_Category_ID=" + p_M_Product_Category_ID + ")";
		// // int updated = DB.executeUpdate(sql, get_TrxName());
		// addLog(0, null, new BigDecimal(updated), "@Updated@");

		// Insert new Products
		sql = "INSERT INTO M_Product_Acct "
				+ "(M_Product_ID, C_AcctSchema_ID,"
				+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
				+ " P_Revenue_Acct, P_Expense_Acct, P_CostAdjustment_Acct, P_InventoryClearing_Acct, P_Asset_Acct, P_CoGs_Acct,"
				+ " P_PurchasePriceVariance_Acct, P_InvoicePriceVariance_Acct,"
				+ " P_TradeDiscountRec_Acct, P_TradeDiscountGrant_Acct, EXME_Prov_Vta_Acct,"
				+ " P_ExpenseFgn_Acct, P_RevenueTGCash_Acct, P_RevenueTGCredit_Acct,"
				+ " P_RevenueTZCash_Acct, P_RevenueTZCredit_Acct,"
				+ " P_RevenueTECash_Acct, P_RevenueTECredit_Acct, P_TradeDiscountGrantG_Acct,"
				+ " P_TradeDiscountGrantZ_Acct, P_TradeDiscountGrantE_Acct, P_RevenueFgn_Acct, P_InventoryClearingFgn_Acct,"
				+ " P_PurchasesReturns_Acct, P_SalesReturns_Acct, P_SalesReturnsG_Acct, P_SalesReturnsZ_Acct, P_SalesReturnsE_Acct ) "
				+ "SELECT p.M_Product_ID, acct.C_AcctSchema_ID,"
				// +
				// " p.AD_Client_ID, p.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
				+ " acct.AD_Client_ID, acct.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
				+ " acct.P_Revenue_Acct, acct.P_Expense_Acct, acct.P_CostAdjustment_Acct, acct.P_InventoryClearing_Acct, acct.P_Asset_Acct, acct.P_CoGs_Acct,"
				+ " acct.P_PurchasePriceVariance_Acct, acct.P_InvoicePriceVariance_Acct,"
				+ " acct.P_TradeDiscountRec_Acct, acct.P_TradeDiscountGrant_Acct, acct.EXME_Prov_Vta_Acct,"
				+ " acct.P_ExpenseFgn_Acct, acct.P_RevenueTGCash_Acct, acct.P_RevenueTGCredit_Acct,"
				+ " acct.P_RevenueTZCash_Acct, acct.P_RevenueTZCredit_Acct, acct.P_RevenueTECash_Acct,"
				+ " acct.P_RevenueTECash_Acct, acct.P_RevenueTECredit_Acct,"
				+ " acct.P_TradeDiscountGrantZ_Acct, acct.P_TradeDiscountGrantE_Acct, acct.P_RevenueFgn_Acct, acct.P_InventoryClearingFgn_Acct,"
				+ " acct.P_PurchasesReturns_Acct, acct.P_SalesReturns_Acct, acct.P_SalesReturnsG_Acct, acct.P_SalesReturnsZ_Acct, acct.P_SalesReturnsE_Acct "
				+ "FROM M_Product p"
				+ " INNER JOIN M_Product_Category_Acct acct ON (acct.M_Product_Category_ID=p.M_Product_Category_ID)"
				+ "WHERE acct.C_AcctSchema_ID="
				+ p_C_AcctSchema_ID // #
				+ " AND p.M_Product_Category_ID="
				+ p_M_Product_Category_ID // #
				+ " AND NOT EXISTS (SELECT * FROM M_Product_Acct pa " + "WHERE pa.M_Product_ID=p.M_Product_ID"
				+ " AND pa.C_AcctSchema_ID=acct.C_AcctSchema_ID)";

		int created = DB.executeUpdate(sql, get_TrxName());
		addLog(0, null, new BigDecimal(created), "@Created@");

		return "@Created@=" + created + ", @Updated@=" + updated;
	} // doIt

} // ProductCategoryAcctCopy
