/**
 * 
 */
package com.ecaresoft.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaDefault;
import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.model.MCharge;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MTax;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CompiereSystemError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 * Update or Create acct. schema default accounts, using System and specific
 * client data.
 * 
 * @author mrojas
 * 
 */
public class AcctSchemaDefaultCopy extends SvrProcess {

	/** Acct Schema */
	private int p_C_AcctSchema_ID = 0;
	/** Copy & Overwrite */
	private boolean p_CopyOverwriteAcct = false;
	/** Copy From */
	private boolean CopyFrom = false;
	/** Copy Of */
	private boolean CopyOf = false;
	/** Acct. Schema */
	private MAcctSchema as = null;
	/** Acct. Schema Default */
	private MAcctSchemaDefault acct = null;
	/** Properties */
	private Properties ctx;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if ("C_AcctSchema_ID".equals(name)) {
				p_C_AcctSchema_ID = para[i].getParameterAsInt();
			} else if ("CopyOverwriteAcct".equals(name)) {
				p_CopyOverwriteAcct = "Y".equals(para[i].getParameter());
			} else if ("CopyFrom".equals(name)) {
				CopyFrom = "Y".equals(para[i].getParameter());
			} else if ("CopyOf".equals(name)) {
				CopyOf = "Y".equals(para[i].getParameter());
			}
			else {
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
			}
		}
		ctx = getCtx();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {

		log.info("C_AcctSchema_ID=" + p_C_AcctSchema_ID
				+ ", CopyOverwriteAcct=" + p_CopyOverwriteAcct);

		if (p_C_AcctSchema_ID == 0) {
			throw new CompiereSystemError("C_AcctSchema_ID=0");
		}

		as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);
		if (as.get_ID() == 0) {
			throw new CompiereSystemError("Not Found - C_AcctSchema_ID="
					+ p_C_AcctSchema_ID);
		}

		acct = MAcctSchemaDefault.get(getCtx(), p_C_AcctSchema_ID);
		if (acct == null || acct.get_ID() == 0) {
			throw new CompiereSystemError(
					"Default Not Found - C_AcctSchema_ID=" + p_C_AcctSchema_ID);
		}

		// Update and Insert M_Product_Category_Acct
		doProductCat();
		// Insert M_Product_Acct
		doProduct();
		// Update and Insert C_BP_Group_Acct
		doBPGroup();
		// Update and Insert C_BP_Employee_Acct
		doBPartnerEmpl();
		// Insert C_BP_Customer_Acct
		doBPartnerCust();
		// Insert C_BP_Vendor_Acct
		doBPartnerVend();
		// Update and Insert M_Warehouse_Acct
		doWarehouse();
		// Update and Insert C_Project_Acct
		doProject();
		// Update and Insert C_Tax_Acct
		doTax();
		// Update and Insert C_BankAccount_Acct
		doBankAccount();
		// Update and Insert C_Withholding_Acct
		doWhithholding();
		// Update and Insert C_Charge_Acct
		doCharge();
		// Update and Insert C_Cashbook_Acct
		doCashbook();
		//Update and Insert C_BP_Creditor_Acct
		doBPCreditor();

		return null;
	}

	/**
	 * Generate data for product category based on default accounts. It should
	 * create account data for categories on System level but in the current
	 * client.
	 */
	private void doProductCat() {
		log.info("Generating for product categories ...");

		Trx trx = Trx.get(Trx.createTrxName("PRODCAT"), false);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		int updated = 0;
		int created = 0;

		try {
			// Update existing Product Category
			if (p_CopyOverwriteAcct) {
				sql.append("UPDATE M_Product_Category_Acct pa ")
						.append("SET P_Revenue_Acct=")
						.append(acct.getP_Revenue_Acct()<=0?null:acct.getP_Revenue_Acct())
						.append(", P_Expense_Acct=")
						.append(acct.getP_Expense_Acct()<=0?null:acct.getP_Expense_Acct())
						.append(", P_CostAdjustment_Acct=")
						.append(acct.getP_CostAdjustment_Acct()<=0?null:acct.getP_CostAdjustment_Acct())
						.append(", P_InventoryClearing_Acct=")
						.append(acct.getP_InventoryClearing_Acct()<=0?null:acct.getP_InventoryClearing_Acct())
						.append(", P_Asset_Acct=")
						.append(acct.getP_Asset_Acct()<=0?null:acct.getP_Asset_Acct())
						.append(", P_COGS_Acct=")
						.append(acct.getP_COGS_Acct()<=0?null:acct.getP_COGS_Acct())
						.append(", P_PurchasePriceVariance_Acct=")
						.append(acct.getP_PurchasePriceVariance_Acct()<=0?null:acct.getP_PurchasePriceVariance_Acct())
						.append(", P_InvoicePriceVariance_Acct=")
						.append(acct.getP_InvoicePriceVariance_Acct()<=0?null:acct.getP_InvoicePriceVariance_Acct())
						.append(", P_TradeDiscountRec_Acct=")
						.append(acct.getP_TradeDiscountRec_Acct()<=0?null:acct.getP_TradeDiscountRec_Acct())
						.append(", P_TradeDiscountGrant_Acct=")
						.append(acct.getP_TradeDiscountGrant_Acct()<=0?null:acct.getP_TradeDiscountGrant_Acct())
						.append(", P_ExpenseFgn_Acct=")
						.append(acct.getP_ExpenseFgn_Acct()<=0?null:acct.getP_ExpenseFgn_Acct())
						.append(", P_RevenueTGCash_Acct=")
						.append(acct.getP_RevenueTGCash_Acct()<=0?null:acct.getP_RevenueTGCash_Acct())
						.append(", P_RevenueTGCredit_Acct=")
						.append(acct.getP_RevenueTGCredit_Acct()<=0?null:acct.getP_RevenueTGCredit_Acct())
						.append(", P_RevenueTZCash_Acct=")
						.append(acct.getP_RevenueTZCash_Acct()<=0?null:acct.getP_RevenueTZCash_Acct())
						.append(", P_RevenueTZCredit_Acct=")
						.append(acct.getP_RevenueTZCredit_Acct()<=0?null:acct.getP_RevenueTZCredit_Acct())
						.append(", P_RevenueTECash_Acct=")
						.append(acct.getP_RevenueTECash_Acct()<=0?null:acct.getP_RevenueTECash_Acct())
						.append(", P_RevenueTECredit_Acct=")
						.append(acct.getP_RevenueTECredit_Acct()<=0?null:acct.getP_RevenueTECredit_Acct())
						.append(", P_TradeDiscountGrantG_Acct=")
						.append(acct.getP_TradeDiscountGrantG_Acct()<=0?null:acct.getP_TradeDiscountGrantG_Acct())
						.append(", P_TradeDiscountGrantZ_Acct=")
						.append(acct.getP_TradeDiscountGrantZ_Acct()<=0?null:acct.getP_TradeDiscountGrantZ_Acct())
						.append(", P_TradeDiscountGrantE_Acct=")
						.append(acct.getP_TradeDiscountGrantE_Acct()<=0?null:acct.getP_TradeDiscountGrantE_Acct())
						.append(", P_RevenueFgn_Acct=")
						.append(acct.getP_RevenueFgn_Acct()<=0?null:acct.getP_RevenueFgn_Acct())
						.append(", P_InventoryClearingFgn_Acct=")
						.append(acct.getP_InventoryClearingFgn_Acct()<=0?null:acct.getP_InventoryClearingFgn_Acct())
						.append(", P_PurchasesReturns_Acct=")
						.append(acct.getP_PurchasesReturns_Acct()<=0?null:acct.getP_PurchasesReturns_Acct())
						.append(", P_SalesReturns_Acct=")
						.append(acct.getP_SalesReturns_Acct()<=0?null:acct.getP_SalesReturns_Acct())	
						.append(", P_SalesReturnsG_Acct=")
						.append(acct.getP_SalesReturnsG_Acct()<=0?null:acct.getP_SalesReturnsG_Acct())
						.append(", P_SalesReturnsZ_Acct=")
						.append(acct.getP_SalesReturnsZ_Acct()<=0?null:acct.getP_SalesReturnsZ_Acct())
						.append(", P_SalesReturnsE_Acct=")
						.append(acct.getP_SalesReturnsE_Acct()<=0?null:acct.getP_SalesReturnsE_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE pa.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)// Para el esquema contable seleccionado como parametro
						.append(" AND EXISTS (SELECT * FROM M_Product_Category p ")
						.append("WHERE p.M_Product_Category_ID=pa.M_Product_Category_ID)");
				updated = DB.executeUpdate(sql.toString(), get_TrxName());

				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @M_Product_Category_ID@");
			}
			else{
				//Update existing Product Category of electronic accounting
				if(CopyFrom){
					
				sql.append("UPDATE M_Product_Category_Acct pa ")
						.append("SET P_RevenueTGCash_Acct=")
						.append(acct.getP_RevenueTGCash_Acct()<=0?null:acct.getP_RevenueTGCash_Acct())
						.append(", P_RevenueTGCredit_Acct=")
						.append(acct.getP_RevenueTGCredit_Acct()<=0?null:acct.getP_RevenueTGCredit_Acct())
						.append(", P_RevenueTZCash_Acct=")
						.append(acct.getP_RevenueTZCash_Acct()<=0?null:acct.getP_RevenueTZCash_Acct())
						.append(", P_RevenueTZCredit_Acct=")
						.append(acct.getP_RevenueTZCredit_Acct()<=0?null:acct.getP_RevenueTZCredit_Acct())
						.append(", P_RevenueTECash_Acct=")
						.append(acct.getP_RevenueTECash_Acct()<=0?null:acct.getP_RevenueTECash_Acct())
						.append(", P_RevenueTECredit_Acct=")
						.append(acct.getP_RevenueTECredit_Acct()<=0?null:acct.getP_RevenueTECredit_Acct())
						.append(", P_TradeDiscountGrantG_Acct=")
						.append(acct.getP_TradeDiscountGrantG_Acct()<=0?null:acct.getP_TradeDiscountGrantG_Acct())
						.append(", P_TradeDiscountGrantZ_Acct=")
						.append(acct.getP_TradeDiscountGrantZ_Acct()<=0?null:acct.getP_TradeDiscountGrantZ_Acct())
						.append(", P_TradeDiscountGrantE_Acct=")
						.append(acct.getP_TradeDiscountGrantE_Acct()<=0?null:acct.getP_TradeDiscountGrantE_Acct())
						.append(", P_SalesReturnsG_Acct=")
						.append(acct.getP_SalesReturnsG_Acct()<=0?null:acct.getP_SalesReturnsG_Acct())
						.append(", P_SalesReturnsZ_Acct=")
						.append(acct.getP_SalesReturnsZ_Acct()<=0?null:acct.getP_SalesReturnsZ_Acct())
						.append(", P_SalesReturnsE_Acct=")
						.append(acct.getP_SalesReturnsE_Acct()<=0?null:acct.getP_SalesReturnsE_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE pa.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)// Para el esquema contable seleccionado como parametro
						.append(" AND EXISTS (SELECT * FROM M_Product_Category p ")
						.append("WHERE p.M_Product_Category_ID=pa.M_Product_Category_ID)");
					
					
					updated = DB.executeUpdate(sql.toString(), get_TrxName());

				}
				
			    //Update existing Product Category of accounts foreign
				if(CopyOf){
					sql = new StringBuffer();
					sql.append("UPDATE M_Product_Category_Acct pa ")	
						.append("SET P_ExpenseFgn_Acct=")
						.append(acct.getP_ExpenseFgn_Acct()<=0?null:acct.getP_ExpenseFgn_Acct())
						.append(", P_RevenueFgn_Acct=")
						.append(acct.getP_RevenueFgn_Acct()<=0?null:acct.getP_RevenueFgn_Acct())
						.append(", P_InventoryClearingFgn_Acct=")
						.append(acct.getP_InventoryClearingFgn_Acct()<=0?null:acct.getP_InventoryClearingFgn_Acct())
						.append(", P_PurchasesReturns_Acct=")
						.append(acct.getP_PurchasesReturns_Acct()<=0?null:acct.getP_PurchasesReturns_Acct())
						.append(", P_SalesReturns_Acct=")
						.append(acct.getP_SalesReturns_Acct()<=0?null:acct.getP_SalesReturns_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE pa.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)// Para el esquema contable seleccionado como parametro
						.append(" AND EXISTS (SELECT * FROM M_Product_Category p ")
						.append("WHERE p.M_Product_Category_ID=pa.M_Product_Category_ID)");
					
					
					updated = DB.executeUpdate(sql.toString(), get_TrxName());

					
				}
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @M_Product_Category_ID@");
			}

			// insert new accounting data
			sql = new StringBuffer("SELECT * FROM M_Product_Category ")
					.append(" WHERE AD_Client_ID IN (0, ?) ") // Registros del cliente y de system
					.append(" AND IsActive = 'Y' ")
					.append(" AND M_Product_Category_ID NOT IN ")// Y que no existan el asiento contable para el cliente de contexto y esquema
					.append("     (SELECT DISTINCT M_Product_Category_ID ")
					.append("      FROM M_Product_Category_Acct ")
					.append("      WHERE AD_Client_ID = ? ")
					.append("      AND C_AcctSchema_ID = ? ")
					.append("      AND IsActive = 'Y')");
			pstmt = DB.prepareStatement(sql.toString(), trx.getTrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, as.getC_AcctSchema_ID());

			log.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MProductCategory pc = new MProductCategory(ctx, rs,
						trx.getTrxName());

				log.info("Processing product category: " + pc.getValue());

				if (pc.insertAccounting()) {
					created++;
				} else {
					log.severe("Can not insert accounting for product category "
							+ pc.getValue());

					addLog(0, null, null, "@Error@ @M_Product_Category_ID@ "
							+ pc.getValue());

				}
			}

			trx.commit();

			addLog(0, null, new BigDecimal(created),
					"@Created@ @M_Product_Category_ID@");

		} catch (SQLException e) {
			log.log(Level.SEVERE,
					"Inserting accounting data for product categories", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			DB.close(rs, pstmt);

			trx.close();
		}
	}

	/**
	 * Generate data for product based on default accounts. It should create
	 * account data for products on System level but in the current client.
	 */
	private void doProduct() {
		log.info("Generating for products ...");

		String sql = "SELECT * FROM M_Product "
				
				+ " WHERE AD_Client_ID = ?  "
				+ " AND IsActive = 'Y'      "
				+ " AND Value <> 'Standard' " 
				+ "	AND M_Product_ID NOT IN "
				+ "		(SELECT DISTINCT M_Product_ID "
				+ "		 FROM M_Product_Acct    "
				+ "		 WHERE  AD_Client_ID = ? "
				+ "      AND C_AcctSchema_ID = ? "
				+ "		 AND IsActive = 'Y')    ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Trx trx = Trx.get(Trx.createTrxName("PROD"), false);

		int inserted = 0;

		try {
			pstmt = DB.prepareStatement(sql, trx.getTrxName());
			pstmt.setInt(1, 0);
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, as.getC_AcctSchema_ID());

			log.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MProduct p = new MProduct(ctx, rs, trx.getTrxName());
				log.info("Processing product: " + p.getValue());

				if (p.insertAccounting()) {
					inserted++;
				} else {
					log.severe("Can not insert accounting for product "
							+ p.getValue());
					addLog(0, null, null,
							"@Error@ @M_Product_ID@ " + p.getValue());
				}
			}

			trx.commit();

			addLog(0, null, new BigDecimal(inserted),
					"@Created@ @M_Product_ID@");
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Inserting accounting data for products", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			DB.close(rs);
			trx.close();
		}
	}

	private void doBPGroup() {

		log.info("Generating for business partner groups ...");

		Trx trx = Trx.get(Trx.createTrxName("BPGRP"), false);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		int updated = 0;
		int created = 0;

		try {

			// Update existing BP Group
			if (p_CopyOverwriteAcct) {
				sql.append("UPDATE C_BP_Group_Acct a ")
						.append("SET C_Receivable_Acct=")
						.append(acct.getC_Receivable_Acct()<=0?null:acct.getC_Receivable_Acct())
						.append(", C_Receivable_Services_Acct=")
						.append(acct.getC_Receivable_Services_Acct()<=0?null:acct.getC_Receivable_Services_Acct())
						.append(", C_Prepayment_Acct=")
						.append(acct.getC_Prepayment_Acct()<=0?null:acct.getC_Prepayment_Acct())
						.append(", V_Liability_Acct=")
						.append(acct.getV_Liability_Acct()<=0?null:acct.getV_Liability_Acct())
						.append(", V_Liability_Services_Acct=")
						.append(acct.getV_Liability_Services_Acct()<=0?null:acct.getV_Liability_Services_Acct())
						.append(", V_Prepayment_Acct=")
						.append(acct.getV_Prepayment_Acct()<=0?null:acct.getV_Prepayment_Acct())
						.append(", PayDiscount_Exp_Acct=")
						.append(acct.getPayDiscount_Exp_Acct()<=0?null:acct.getPayDiscount_Exp_Acct())
						.append(", PayDiscount_Rev_Acct=")
						.append(acct.getPayDiscount_Rev_Acct()<=0?null:acct.getPayDiscount_Rev_Acct())
						.append(", WriteOff_Acct=")
						.append(acct.getWriteOff_Acct()<=0?null:acct.getWriteOff_Acct())
						.append(", NotInvoicedReceipts_Acct=")
						.append(acct.getNotInvoicedReceipts_Acct()<=0?null:acct.getNotInvoicedReceipts_Acct())
						.append(", UnEarnedRevenue_Acct=")
						.append(acct.getUnEarnedRevenue_Acct()<=0?null:acct.getUnEarnedRevenue_Acct())
						.append(", NotInvoicedRevenue_Acct=")
						.append(acct.getNotInvoicedRevenue_Acct()<=0?null:acct.getNotInvoicedRevenue_Acct())
						.append(", NotInvoicedReceivables_Acct=")
						.append(acct.getNotInvoicedReceivables_Acct()<=0?null:acct.getNotInvoicedReceivables_Acct())
						.append(", C_ReceivableFgn_Acct=")
						.append(acct.getC_ReceivableFgn_Acct()<=0?null:acct.getC_ReceivableFgn_Acct())
						.append(", V_LiabilityFgn_Acct=")
						.append(acct.getV_LiabilityFgn_Acct()<=0?null:acct.getV_LiabilityFgn_Acct())
						.append(", V_PrepaymentFgn_Acct=")
						.append(acct.getV_PrepaymentFgn_Acct()<=0?null:acct.getV_PrepaymentFgn_Acct())
						.append(", C_PrepaymentFgn_Acct=")
						.append(acct.getC_PrepaymentFgn_Acct()<=0?null:acct.getC_PrepaymentFgn_Acct())
						.append(", A_Creditors_Acct=")
						.append(acct.getA_Creditors_Acct()<=0?null:acct.getA_Creditors_Acct())
						.append(", A_CreditorsFgn_Acct=")
						.append(acct.getA_CreditorsFgn_Acct()<=0?null:acct.getA_CreditorsFgn_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=").append(p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_BP_Group_Acct x ")
						.append("WHERE x.C_BP_Group_ID=a.C_BP_Group_ID)");

				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_BP_Group_ID@");
			}
			
			//Update existing BP Group of accounts foreign
			if(CopyOf){
					sql.append("UPDATE C_BP_Group_Acct a ")
					.append("SET C_ReceivableFgn_Acct=")
					.append(acct.getC_ReceivableFgn_Acct()<=0?null:acct.getC_ReceivableFgn_Acct())
					.append(", V_LiabilityFgn_Acct=")
					.append(acct.getV_LiabilityFgn_Acct()<=0?null:acct.getV_LiabilityFgn_Acct())
					.append(", V_PrepaymentFgn_Acct=")
					.append(acct.getV_PrepaymentFgn_Acct()<=0?null:acct.getV_PrepaymentFgn_Acct())
					.append(", C_PrepaymentFgn_Acct=")
					.append(acct.getC_PrepaymentFgn_Acct()<=0?null:acct.getC_PrepaymentFgn_Acct())
					.append(", A_Creditors_Acct=")
					.append(acct.getA_Creditors_Acct()<=0?null:acct.getA_Creditors_Acct())
					.append(", A_CreditorsFgn_Acct=")
					.append(acct.getA_CreditorsFgn_Acct()<=0?null:acct.getA_CreditorsFgn_Acct())
					.append(", Updated= now(), UpdatedBy=0 ")
					.append("WHERE a.C_AcctSchema_ID=").append(p_C_AcctSchema_ID)
					.append(" AND EXISTS (SELECT * FROM C_BP_Group_Acct x ")
					.append("WHERE x.C_BP_Group_ID=a.C_BP_Group_ID)");

			updated = DB.executeUpdate(sql.toString(), get_TrxName());
			addLog(0, null, new BigDecimal(updated),
					"@Updated@ @C_BP_Group_ID@");
			}
			

			// insert new accounting data
			sql = new StringBuffer("SELECT * FROM C_BP_Group "
					+ " WHERE AD_Client_ID IN (0, ?) "
					+ " AND IsActive = 'Y'           "
					+ "	AND C_BP_Group_ID NOT IN     "
					+ "		(SELECT DISTINCT C_BP_Group_ID "
					+ "		 FROM C_BP_Group_Acct    "
					+ "      WHERE AD_Client_ID  = ? "
					+ "      AND C_AcctSchema_ID = ? "
					+ "		 AND IsActive = 'Y')     ");
			pstmt = DB.prepareStatement(sql.toString(), trx.getTrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, as.getC_AcctSchema_ID());

			log.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MBPGroup bpGroup = new MBPGroup(ctx, rs, trx.getTrxName());

				log.info("Processing bp Group : " + bpGroup.getValue());

				if (bpGroup.insertAccountingClient()) {
					created++;
				} else {
					log.severe("Can not insert accounting for BP Group "
							+ bpGroup.getValue());

					addLog(0, null, null,
							"@Error@ @C_BP_Group_ID@ " + bpGroup.getValue());
				}
			}

			trx.commit();

			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_BP_Group_ID@");

		} catch (SQLException e) {
			log.log(Level.SEVERE, "Inserting accounting data for BP Group", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			DB.close(rs, pstmt);

			trx.close();
		}
	}

	private void doBPartnerEmpl() {

		log.info("Generating for business partner (emmployee) ...");

		Trx trx = Trx.get(Trx.createTrxName("BPEMPL"), false);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		int updated = 0;
		int created = 0;

		try {

			// Update existing BP Employee data
			if (p_CopyOverwriteAcct) {
				sql.append("UPDATE C_BP_Employee_Acct a ")
						.append("SET E_Expense_Acct=")
						.append(acct.getE_Expense_Acct()<=0?null:acct.getE_Expense_Acct())
						.append(", E_Prepayment_Acct=")
						.append(acct.getE_Prepayment_Acct()<=0?null:acct.getE_Prepayment_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_BP_Employee_Acct x ")
						.append("WHERE x.C_BPartner_ID=a.C_BPartner_ID)");

				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_BPartner_ID@ @IsEmployee@");
			}

			// insert new accounting data
			sql = new StringBuffer("SELECT * FROM C_BPartner "
					+ " WHERE AD_Client_ID IN(0, ?) "
					+ " AND IsActive = 'Y'       "
					+ " AND IsEmployee = 'Y'     "
					+ "	AND C_BPartner_ID NOT IN "
					+ "		(SELECT DISTINCT C_BPartner_ID "
					+ "		 FROM C_BP_Employee_Acct "
					+ "      WHERE AD_Client_ID  = ? "
					+ "      AND C_AcctSchema_ID = ? "
					+ "		 AND IsActive = 'Y')     ");
			pstmt = DB.prepareStatement(sql.toString(), trx.getTrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, as.getC_AcctSchema_ID());

			log.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MBPartner bPartner = new MBPartner(ctx, rs, trx.getTrxName());

				log.info("Processing BPartner : " + bPartner.getValue());

				if (bPartner.insertAcctClientEmpl()) {
					created++;
				} else {
					log.severe("Can not insert accounting for BP (Employee) "
							+ bPartner.getValue());

					addLog(0,
							null,
							null,
							"@Error@ @C_BPartner_ID@ @IsEmployee@ "
									+ bPartner.getValue());
				}
			}

			trx.commit();

			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_BPartner_ID@ @IsEmployee@");

		} catch (SQLException e) {
			log.log(Level.SEVERE, "Inserting accounting data for BP Employee",
					e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			DB.close(rs, pstmt);

			trx.close();
		}
	}

	private void doBPartnerCust() {

		log.info("Generating for business partner (customer) ...");

		Trx trx = Trx.get(Trx.createTrxName("BPCUST"), false);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		int updated = 0;
		int created = 0;

		try {

			// Update existing BP Customer data
			if (p_CopyOverwriteAcct) {
				sql.append("UPDATE C_BP_Customer_Acct a ")
						.append("SET C_Receivable_Acct=")
						.append(acct.getC_Receivable_Acct()<=0?null:acct.getC_Receivable_Acct())
						.append(", C_Receivable_Services_Acct=")
						.append(acct.getC_Receivable_Services_Acct()<=0?null:acct.getC_Receivable_Services_Acct())
						.append(", C_PrePayment_Acct=")
						.append(acct.getC_Prepayment_Acct()<=0?null:acct.getC_Prepayment_Acct())
						.append(", C_ReceivableFgn_Acct=")
				        .append(acct.getC_ReceivableFgn_Acct()<=0?null:acct.getC_ReceivableFgn_Acct())
				        .append(", C_PrepaymentFgn_Acct=")
				        .append(acct.getC_PrepaymentFgn_Acct()<=0?null:acct.getC_PrepaymentFgn_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_BP_Customer_Acct x ")
						.append("WHERE x.C_BPartner_ID=a.C_BPartner_ID)");

				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_BPartner_ID@ @IsCustomer@");
			} 
			//Update existing BP Customer of accounts foreign
			    
			if(CopyOf){
					sql.append("UPDATE C_BP_Customer_Acct a ")
					.append("SET C_ReceivableFgn_Acct=")
					.append(acct.getC_ReceivableFgn_Acct()<=0?null:acct.getC_ReceivableFgn_Acct())
					.append(", C_PrepaymentFgn_Acct=")
					.append(acct.getC_PrepaymentFgn_Acct()<=0?null:acct.getC_PrepaymentFgn_Acct())
					.append(", Updated= now(), UpdatedBy=0 ")
					.append("WHERE a.C_AcctSchema_ID=")
					.append(p_C_AcctSchema_ID)
					.append(" AND EXISTS (SELECT * FROM C_BP_Customer_Acct x ")
					.append("WHERE x.C_BPartner_ID=a.C_BPartner_ID)");

					updated = DB.executeUpdate(sql.toString(), get_TrxName());
					addLog(0, null, new BigDecimal(updated), "@Updated@ @C_BPartner_ID@ @IsCustomer@");
			}
			

			// insert new accounting data
			sql = new StringBuffer("SELECT * FROM C_BPartner "
					+ " WHERE AD_Client_ID IN (0, ?) "
					+ "   AND IsActive = 'Y'         "
					+ "   AND IsCustomer = 'Y'       "
					+ "	  AND C_BPartner_ID NOT IN   "
					+ "		(SELECT DISTINCT C_BPartner_ID "
					+ "		 FROM C_BP_Customer_Acct "
					+ "      WHERE AD_Client_ID = ?  "
					+ "      AND C_AcctSchema_ID = ? "
					+ "		 AND IsActive = 'Y')     ");
			pstmt = DB.prepareStatement(sql.toString(), trx.getTrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, as.getC_AcctSchema_ID());

			log.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MBPartner bPartner = new MBPartner(ctx, rs, trx.getTrxName());

				log.info("Processing BPartner : " + bPartner.getValue());

				if (bPartner.insertAcctClientCust()) {
					created++;
				} else {
					log.severe("Can not insert accounting for BP (Customer) "
							+ bPartner.getValue());

					addLog(0,
							null,
							null,
							"@Error@ @C_BPartner_ID@ @IsCustomer@ "
									+ bPartner.getValue());
				}
			}

			trx.commit();

			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_BPartner_ID@ @IsCustomer@");

		} catch (SQLException e) {
			log.log(Level.SEVERE, "Inserting accounting data for BP Customer",
					e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			DB.close(rs, pstmt);

			trx.close();
		}
	}

	private void doBPartnerVend() {

		log.info("Generating for business partner (vendor) ...");

		Trx trx = Trx.get(Trx.createTrxName("BPVEND"), false);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		int updated = 0;
		int created = 0;

		try {

			// Update existing BP Vendor data
			if (p_CopyOverwriteAcct) {
				sql.append("UPDATE C_BP_Vendor_Acct a ")
						.append("SET V_Liability_Acct=")
						.append(acct.getV_Liability_Acct()<=0?null:acct.getV_Liability_Acct())
						.append(", V_Liability_Services_Acct=")
						.append(acct.getV_Liability_Services_Acct()<=0?null:acct.getV_Liability_Services_Acct())
						.append(", V_PrePayment_Acct=")
						.append(acct.getV_Prepayment_Acct()<=0?null:acct.getV_Prepayment_Acct())
						.append(", V_PrepaymentFgn_Acct=")
				        .append(acct.getV_PrepaymentFgn_Acct()<=0?null:acct.getV_PrepaymentFgn_Acct())
				        .append(", V_LiabilityFgn_Acct=")
				        .append(acct.getV_LiabilityFgn_Acct()<=0?null:acct.getV_LiabilityFgn_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_BP_Vendor_Acct x ")
						.append("WHERE x.C_BPartner_ID=a.C_BPartner_ID)");

				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_BPartner_ID@ @IsVendor@");
			}
			
			//Update existing BP Vendor of accounts foreign
			if(CopyOf){
					sql.append("UPDATE C_BP_Vendor_Acct a ")
					.append("SET V_PrepaymentFgn_Acct=")
					.append(acct.getV_PrepaymentFgn_Acct()<=0?null:acct.getV_PrepaymentFgn_Acct())
					.append(", V_LiabilityFgn_Acct=")
					.append(acct.getV_LiabilityFgn_Acct()<=0?null:acct.getV_LiabilityFgn_Acct())
					.append(", Updated= now(), UpdatedBy=0 ")
					.append("WHERE a.C_AcctSchema_ID=")
					.append(p_C_AcctSchema_ID)
					.append(" AND EXISTS (SELECT * FROM C_BP_Vendor_Acct x ")
					.append("WHERE x.C_BPartner_ID=a.C_BPartner_ID)");
				
					updated = DB.executeUpdate(sql.toString(), get_TrxName());
					addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_BPartner_ID@ @IsVendor@");
			}
			

			// insert new accounting data
			sql = new StringBuffer("SELECT * FROM C_BPartner "
					+ " WHERE AD_Client_ID IN (0, ?) "
					+ " AND IsActive = 'Y' "
					+ " AND IsVendor = 'Y' " 
					+ "	AND C_BPartner_ID NOT IN "
					+ "		(SELECT DISTINCT C_BPartner_ID "
					+ "		 FROM C_BP_Vendor_Acct   "
					+ "      WHERE AD_Client_ID = ?  "
					+ "      AND C_AcctSchema_ID = ? "
					+ "		 AND IsActive = 'Y')     ");
			pstmt = DB.prepareStatement(sql.toString(), trx.getTrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, as.getC_AcctSchema_ID());
// no envio datos
			log.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MBPartner bPartner = new MBPartner(ctx, rs, trx.getTrxName());

				log.info("Processing BPartner : " + bPartner.getValue());

				if (bPartner.insertAcctClientVndr()) {
					created++;
				} else {
					log.severe("Can not insert accounting for BP (Vendor) "
							+ bPartner.getValue());

					addLog(0, null, null, "@Error@ @C_BPartner_ID@ @IsVentor@ "
							+ bPartner.getValue());
				}
			}

			trx.commit();

			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_BPartner_ID@ @IsVendor@");

		} catch (SQLException e) {
			log.log(Level.SEVERE, "Inserting accounting data for BP Vendor", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			DB.close(rs, pstmt);

			trx.close();
		}
	}

	private void doWarehouse() {

		log.info("Generating for warehouse ...");

		Trx trx = Trx.get(Trx.createTrxName("WRH"), false);

		StringBuffer sql = new StringBuffer();

		int updated = 0;
		int created = 0;

		try {

			// Update Warehouse
			if (p_CopyOverwriteAcct) {
				sql.append("UPDATE M_Warehouse_Acct a ")
						.append("SET W_Inventory_Acct=")
						.append(acct.getW_Inventory_Acct()<=0?null:acct.getW_Inventory_Acct())
						.append(", W_Differences_Acct=")
						.append(acct.getW_Differences_Acct()<=0?null:acct.getW_Differences_Acct())
						.append(", W_Revaluation_Acct=")
						.append(acct.getW_Revaluation_Acct()<=0?null:acct.getW_Revaluation_Acct())
						.append(", W_InvActualAdjust_Acct=")
						.append(acct.getW_InvActualAdjust_Acct()<=0?null:acct.getW_InvActualAdjust_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM M_Warehouse_Acct x ")
						.append("WHERE x.M_Warehouse_ID=a.M_Warehouse_ID)");

				updated = DB.executeUpdate(sql.toString(), trx.getTrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @M_Warehouse_ID@");
			}

			// Insert new Warehouse
			sql = new StringBuffer(
					"INSERT INTO M_Warehouse_Acct "
							+ "(M_Warehouse_ID, C_AcctSchema_ID,"
							+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
							+ " W_Inventory_Acct, W_Differences_Acct, W_Revaluation_Acct, W_InvActualAdjust_Acct) "
							// Values
							+ " SELECT x.M_Warehouse_ID, acct.C_AcctSchema_ID,"
							+ " x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
							+ " acct.W_Inventory_Acct, acct.W_Differences_Acct, acct.W_Revaluation_Acct, acct.W_InvActualAdjust_Acct "
							+ " FROM M_Warehouse x"
							+ " INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) "
							+ " WHERE acct.C_AcctSchema_ID=").append(p_C_AcctSchema_ID).append(
					          " AND NOT EXISTS (SELECT * FROM M_Warehouse_Acct a "
							+ "                 WHERE a.M_Warehouse_ID=x.M_Warehouse_ID"
							+ "                 AND  a.C_AcctSchema_ID=acct.C_AcctSchema_ID)");
			created = DB.executeUpdate(sql.toString(), trx.getTrxName());
			addLog(0, null, new BigDecimal(created),
					"@Created@ @M_Warehouse_ID@");
			trx.commit();
// no envio datos
			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_BPartner_ID@ @IsVendor@");

		} catch (Exception e) {
			log.log(Level.SEVERE, "Inserting accounting data for Warehouse", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			trx.close();
		}
	}

	private void doProject() {

		log.info("Generating for project ...");

		Trx trx = Trx.get(Trx.createTrxName("PJT"), false);

		StringBuffer sql = null;

		int updated = 0;
		int created = 0;

		try {

			// Update Project
			if (p_CopyOverwriteAcct) {
				// AccessLevel 3 - Client - Org 
				sql = new StringBuffer();
				sql.append("UPDATE C_Project_Acct a SET PJ_Asset_Acct=")
						.append(acct.getPJ_Asset_Acct()<=0?null:acct.getPJ_Asset_Acct())
						.append(", PJ_WIP_Acct=")
						.append(acct.getPJ_Asset_Acct()<=0?null:acct.getPJ_Asset_Acct())//FIXME La cuenta configurada no es correcta (se encontro asi).
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_Project_Acct x ")
						.append("WHERE x.C_Project_ID=a.C_Project_ID)");
				updated = DB.executeUpdate(sql.toString(), trx.getTrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_Project_ID@");
			}

			// Insert new Projects
			sql = new StringBuffer();
			sql.append(
					"INSERT INTO C_Project_Acct "
							+ "(C_Project_ID, C_AcctSchema_ID,"
							+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
							+ " PJ_Asset_Acct, PJ_WIP_Acct) "
							// Values
							+ " SELECT x.C_Project_ID, acct.C_AcctSchema_ID,"
							+ " x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
							+ " acct.PJ_Asset_Acct, acct.PJ_WIP_Acct "
							+ " FROM C_Project x"
							+ " INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) "
							+ " WHERE acct.C_AcctSchema_ID=")
					.append(p_C_AcctSchema_ID)
					.append(  " AND NOT EXISTS (SELECT * FROM C_Project_Acct a "
							+ "                 WHERE a.C_Project_ID=x.C_Project_ID"
							+ "                 AND a.C_AcctSchema_ID=acct.C_AcctSchema_ID)");
			created = DB.executeUpdate(sql.toString(), trx.getTrxName());
			addLog(0, null, new BigDecimal(created), "@Created@ @C_Project_ID@");

			trx.commit();

		} catch (Exception e) {
			log.log(Level.SEVERE, "Inserting accounting data for Project", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			trx.close();
		}
	}

	
	// TWRY INICIO
	/**
	 * 
	 */
	private void doTax() {
		log.info("Generating for tax ...");

		Trx trx = Trx.get(Trx.createTrxName("TAX"), false);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = null;

		int updated = 0;
		int created = 0;

		try {

			// Update Tax
			if (p_CopyOverwriteAcct) {
				
				// AccessLevel 6 - System - Client 
				sql = new StringBuffer(" UPDATE C_Tax_Acct a ")
				.append("SET T_Due_Acct=")
				        .append(acct.getT_Due_Acct()<=0?null:acct.getT_Due_Acct())
						.append(", T_Liability_Acct=")
						.append(acct.getT_Liability_Acct()<=0?null:acct.getT_Liability_Acct())
						.append(", T_Credit_Acct=")
						.append(acct.getT_Credit_Acct()<=0?null:acct.getT_Credit_Acct())
						.append(", T_Receivables_Acct=")
						.append(acct.getT_Receivables_Acct()<=0?null:acct.getT_Receivables_Acct())
						.append(", T_Expense_Acct=")
						.append(acct.getT_Expense_Acct()<=0?null:acct.getT_Expense_Acct())				
						.append(", T_HoldRec_Acct=")
						.append(acct.getT_HoldRec_Acct()<=0?null:acct.getT_HoldRec_Acct())
						.append(", T_HoldLiab_Acct=")
						.append(acct.getT_HoldLiab_Acct()<=0?null:acct.getT_HoldLiab_Acct())			
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)
						.append("AND EXISTS (SELECT * FROM C_Tax_Acct x ")
						.append("             WHERE x.C_Tax_ID=a.C_Tax_ID)");
				
				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated), "@Updated@ @C_Tax_ID@");
			} 
				//Update existing Tax of accounts foreign
			if(CopyOf){
					sql = new StringBuffer(" UPDATE C_Tax_Acct a ")
					.append("SET T_HoldRec_Acct=")
				        	.append(acct.getT_HoldRec_Acct()<=0?null:acct.getT_HoldRec_Acct())
				        	.append(", T_HoldLiab_Acct=")
				        	.append(acct.getT_HoldLiab_Acct()<=0?null:acct.getT_HoldLiab_Acct())			
					        .append(", Updated= now(), UpdatedBy=0 ")
					        .append("WHERE a.C_AcctSchema_ID=")
					        .append(p_C_AcctSchema_ID)
					        .append("AND EXISTS (SELECT * FROM C_Tax_Acct x ")
					        .append("             WHERE x.C_Tax_ID=a.C_Tax_ID)");
			
					updated = DB.executeUpdate(sql.toString(), get_TrxName());
					addLog(0, null, new BigDecimal(updated), "@Updated@ @C_Tax_ID@");
			}
			
			
			// Insert new Tax
//			sql = new StringBuffer();
//			sql.append("INSERT INTO C_Tax_Acct ")
//					.append("(C_Tax_ID, C_AcctSchema_ID,")
//					.append(" AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,")
//					.append(" T_Due_Acct, T_Liability_Acct, T_Credit_Acct, T_Receivables_Acct, T_Expense_Acct) ")
//					// Values
//					.append(" SELECT x.C_Tax_ID, acct.C_AcctSchema_ID,")
//					.append(" x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,")
//					.append(" acct.T_Due_Acct, acct.T_Liability_Acct, acct.T_Credit_Acct, acct.T_Receivables_Acct, acct.T_Expense_Acct ")
//					.append(" FROM C_Tax x")
//					.append(" INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) ")
//					.append(" WHERE acct.C_AcctSchema_ID=").append(p_C_AcctSchema_ID)
//					.append(" AND NOT EXISTS (SELECT * FROM C_Tax_Acct a ")
//					.append(" WHERE a.C_Tax_ID=x.C_Tax_ID")
//					.append(" AND a.C_AcctSchema_ID=acct.C_AcctSchema_ID)");
//			created = DB.executeUpdate(sql.toString(), get_TrxName());
//			addLog(0, null, new BigDecimal(created), "@Created@ @C_Tax_ID@");
//			trx.commit();
			
			// Insert new Tax
			sql = new StringBuffer("SELECT * FROM C_Tax "
					+ " WHERE AD_Client_ID IN (0, ?) "
					+ " AND IsActive = 'Y' "
					+ "	AND C_Tax_ID NOT IN "
					+ "		(SELECT DISTINCT C_Tax_ID "
					+ "		 FROM   C_Tax_Acct   "
					+ "      WHERE  AD_Client_ID = ?  "
					+ "      AND    C_AcctSchema_ID = ? "
					+ "		 AND    IsActive = 'Y')     ");
			pstmt = DB.prepareStatement(sql.toString(), trx.getTrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, as.getC_AcctSchema_ID());

			log.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MTax tax = new MTax(ctx, rs, trx.getTrxName());

				log.info("Processing Tax : " + tax.getName());

				if (tax.insertAccountingClient()) {
					created++;
				} else {
					log.severe("Can not insert accounting for Tax "
							+ tax.getName());

					addLog(0, null, null, "@Error@ @C_Tax_ID@"
							+ tax.getName());
				}
			}

			trx.commit();

			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_Tax_ID@");

		} catch (Exception e) {
			log.log(Level.SEVERE, "Inserting accounting data for Tax ", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			trx.close();
		}
	}

	/**
	 * 
	 */
	private void doBankAccount() {
		log.info("Generating for Bank ...");

		Trx trx = Trx.get(Trx.createTrxName("BNK"), false);

		StringBuffer sql = null;

		int updated = 0;
		int created = 0;

		try {
			// Update BankAccount
			if (p_CopyOverwriteAcct) {

				// AccessLevel 7 - System - Client - Org 
				sql = new StringBuffer("UPDATE C_BankAccount_Acct a ")
				        .append("SET B_InTransit_Acct=")
					    .append(acct.getB_InTransit_Acct()<=0?null:acct.getB_InTransit_Acct())
						.append(", B_Asset_Acct=")
						.append(acct.getB_Asset_Acct()<=0?null:acct.getB_Asset_Acct())
						.append(", B_Expense_Acct=")
						.append(acct.getB_Expense_Acct()<=0?null:acct.getB_Expense_Acct())
						.append(", B_InterestRev_Acct=")
						.append(acct.getB_InterestRev_Acct()<=0?null:acct.getB_InterestRev_Acct())
						.append(", B_InterestExp_Acct=")
						.append(acct.getB_InterestExp_Acct()<=0?null:acct.getB_InterestExp_Acct())
					    .append(", B_Unidentified_Acct=")
						.append(acct.getB_Unidentified_Acct()<=0?null:acct.getB_Unidentified_Acct())
						.append(", B_UnallocatedCash_Acct=")
						.append(acct.getB_UnallocatedCash_Acct()<=0?null:acct.getB_UnallocatedCash_Acct())
						.append(", B_PaymentSelect_Acct=")
						.append(acct.getB_PaymentSelect_Acct()<=0?null:acct.getB_PaymentSelect_Acct())
						.append(", B_SettlementGain_Acct=")
						.append(acct.getB_SettlementGain_Acct()<=0?null:acct.getB_SettlementGain_Acct())
						.append(", B_SettlementLoss_Acct=")
						.append(acct.getB_SettlementLoss_Acct()<=0?null:acct.getB_SettlementLoss_Acct())
						.append(", B_RevaluationGain_Acct=")
						.append(acct.getB_RevaluationGain_Acct()<=0?null:acct.getB_RevaluationGain_Acct())
						.append(", B_RevaluationLoss_Acct=")
						.append(acct.getB_RevaluationLoss_Acct()<=0?null:acct.getB_RevaluationLoss_Acct())
						.append(", B_AssetFgn_Acct=")
						.append(acct.getB_AssetFgn_Acct()<=0?null:acct.getB_AssetFgn_Acct())
						.append(", B_InTransitFgn_Acct=")
						.append(acct.getB_InTransitFgn_Acct()<=0?null:acct.getB_InTransitFgn_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_BankAccount_Acct x ")
						.append("WHERE x.C_BankAccount_ID=a.C_BankAccount_ID)");
				
				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_BankAccount_ID@");

			}
			else{
				//Update existing Banks of accounts foreign
				if(CopyOf){
					sql = new StringBuffer("UPDATE C_BankAccount_Acct a ")
					        .append("SET B_AssetFgn_Acct=")
							.append(acct.getB_AssetFgn_Acct()<=0?null:acct.getB_AssetFgn_Acct())
							.append(", B_InTransitFgn_Acct=")
							.append(acct.getB_InTransitFgn_Acct()<=0?null:acct.getB_InTransitFgn_Acct())
							.append(", Updated= now(), UpdatedBy=0 ")
							.append("WHERE a.C_AcctSchema_ID=")
							.append(p_C_AcctSchema_ID)
							.append(" AND EXISTS (SELECT * FROM C_BankAccount_Acct x ")
							.append("WHERE x.C_BankAccount_ID=a.C_BankAccount_ID)");
					
					updated = DB.executeUpdate(sql.toString(), get_TrxName());
					addLog(0, null, new BigDecimal(updated),
							"@Updated@ @C_BankAccount_ID@");
				}
			}

			// Insert new BankAccount
			sql = new StringBuffer(
					"INSERT INTO C_BankAccount_Acct "
							+ "(C_BankAccount_ID, C_AcctSchema_ID,"
							+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
							+ " B_InTransit_Acct, B_Asset_Acct, B_Expense_Acct, B_InterestRev_Acct, B_InterestExp_Acct,"
							+ " B_Unidentified_Acct, B_UnallocatedCash_Acct, B_PaymentSelect_Acct,"
							+ " B_SettlementGain_Acct, B_SettlementLoss_Acct,"
							+ " B_RevaluationGain_Acct, B_RevaluationLoss_Acct,B_AssetFgn_Acct,B_InTransitFgn_Acct) "
							// Values
							+ "SELECT x.C_BankAccount_ID, acct.C_AcctSchema_ID,"
							+ " x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
							+ " acct.B_InTransit_Acct, acct.B_Asset_Acct, acct.B_Expense_Acct, acct.B_InterestRev_Acct, acct.B_InterestExp_Acct,"
							+ " acct.B_Unidentified_Acct, acct.B_UnallocatedCash_Acct, acct.B_PaymentSelect_Acct,"
							+ " acct.B_SettlementGain_Acct, acct.B_SettlementLoss_Acct,"
							+ " acct.B_RevaluationGain_Acct, acct.B_RevaluationLoss_Acct, acct.B_AssetFgn_Acct, acct.B_InTransitFgn_Acct "
							+ " FROM C_BankAccount x"
							+ " INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) "
							+ " WHERE acct.C_AcctSchema_ID=").append(p_C_AcctSchema_ID)
							.append(" AND NOT EXISTS (SELECT * FROM C_BankAccount_Acct a "
							+ " WHERE a.C_BankAccount_ID=x.C_BankAccount_ID"
							+ " AND a.C_AcctSchema_ID=acct.C_AcctSchema_ID)");
			created = DB.executeUpdate(sql.toString(), get_TrxName());
			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_BankAccount_ID@");
			trx.commit();

		} catch (Exception e) {
			log.log(Level.SEVERE, "Inserting accounting data for Bank ", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			trx.close();
		}
	}

	/**
	 * 
	 */
	private void doWhithholding() {

		log.info("Generating for Withholding ...");

		Trx trx = Trx.get(Trx.createTrxName("WHT"), false);

		StringBuffer sql = null;

		int updated = 0;
		int created = 0;

		try {

			// Update Withholding
			if (p_CopyOverwriteAcct) {
				// AccessLevel 7 - System - Client - Org 
				sql = new StringBuffer("UPDATE C_Withholding_Acct a ")
				        .append("SET Withholding_Acct=")
				        .append(acct.getWithholding_Acct()<=0?null:acct.getWithholding_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=" + p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_Withholding_Acct x ")
						.append("WHERE x.C_Withholding_ID=a.C_Withholding_ID)");
				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_Withholding_ID@");
			}
			
			// Insert new Withholding
			sql = new StringBuffer(
					"INSERT INTO C_Withholding_Acct "
							+ " (C_Withholding_ID, C_AcctSchema_ID,"
							+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
							+ "	Withholding_Acct) "
							+ " SELECT x.C_Withholding_ID, acct.C_AcctSchema_ID,"
							+ " x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
							+ " acct.Withholding_Acct "
							+ " FROM C_Withholding x"
							+ " INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) "
							+ " WHERE acct.C_AcctSchema_ID=").append(p_C_AcctSchema_ID).append(
							  " AND NOT EXISTS (SELECT * FROM C_Withholding_Acct a "
							+ " WHERE a.C_Withholding_ID=x.C_Withholding_ID"
							+ " AND a.C_AcctSchema_ID=acct.C_AcctSchema_ID)");
			created = DB.executeUpdate(sql.toString(), get_TrxName());
			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_Withholding_ID@");
			trx.commit();

		} catch (Exception e) {
			log.log(Level.SEVERE, "Inserting accounting data for Withholding", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			trx.close();
		}
	}

	/**
	 * 
	 */
	private void doCharge() {

		log.info("Generating for Charge ...");

		Trx trx = Trx.get(Trx.createTrxName("CHA"), false);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = null;

		int updated = 0;
		int created = 0;

		try {
			// Update Charge
			if (p_CopyOverwriteAcct) {
				
			    // AccessLevel 6 - System - Client 
				sql = new StringBuffer("UPDATE C_Charge_Acct a ")
						.append("SET Ch_Expense_Acct=") 
						.append(acct.getCh_Expense_Acct()<=0?null:acct.getCh_Expense_Acct())
						.append(", Ch_Revenue_Acct=") 
						.append(acct.getCh_Revenue_Acct()<=0?null:acct.getCh_Revenue_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=" + p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_Charge_Acct x ")
						.append("WHERE x.C_Charge_ID=a.C_Charge_ID)");
				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_Charge_ID@");
			}
			// Insert new Charge
//			sql = new StringBuffer(
//					"INSERT INTO C_Charge_Acct "
//							+ "(C_Charge_ID, C_AcctSchema_ID,"
//							+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
//							+ " Ch_Expense_Acct, Ch_Revenue_Acct) "
//							+ "SELECT x.C_Charge_ID, acct.C_AcctSchema_ID,"
//							+ " x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
//							+ " acct.Ch_Expense_Acct, acct.Ch_Revenue_Acct "
//							+ "FROM C_Charge x"
//							+ " INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) "
//							+ "WHERE acct.C_AcctSchema_ID=").append(p_C_AcctSchema_ID)
//							.append(" AND NOT EXISTS (SELECT * FROM C_Charge_Acct a "
//							+ "WHERE a.C_Charge_ID=x.C_Charge_ID"
//							+ " AND a.C_AcctSchema_ID=acct.C_AcctSchema_ID)");
//			created = DB.executeUpdate(sql.toString(), get_TrxName());
//			addLog(0, null, new BigDecimal(created), "@Created@ @C_Charge_ID@");
//			trx.commit();

			// Insert new Charge
			sql = new StringBuffer("SELECT * FROM C_Charge "
					+ " WHERE AD_Client_ID IN (0, ?) "
					+ " AND IsActive = 'Y' "
					+ "	AND C_Charge_ID NOT IN "
					+ "		(SELECT DISTINCT C_Charge_ID "
					+ "		 FROM   C_Charge_Acct   "
					+ "      WHERE  AD_Client_ID = ?  "
					+ "      AND    C_AcctSchema_ID = ? "
					+ "		 AND    IsActive = 'Y')     ");
			pstmt = DB.prepareStatement(sql.toString(), trx.getTrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, as.getC_AcctSchema_ID());

			log.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MCharge charge = new MCharge(ctx, rs, trx.getTrxName());

				log.info("Processing charge : " + charge.getValue());

				if (charge.insertAccountingClient()) {
					created++;
				} else {
					log.severe("Can not insert accounting for charge "
							+ charge.getValue());

					addLog(0, null, null, "@Error@ @C_Charge_ID@ "
							+ charge.getValue());
				}
			}

			trx.commit();

			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_Charge_ID@");

		} catch (Exception e) {
			log.log(Level.SEVERE, "Inserting accounting data for Charge ", e);

			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			trx.close();
		}
	}

	/**
	 * 
	 */
	private void doCashbook() {

		log.info("Generating for Cashbook ...");

		Trx trx = Trx.get(Trx.createTrxName("CBK"), false);

		StringBuffer sql = null;

		int updated = 0;
		int created = 0;

		try {
			if (p_CopyOverwriteAcct) {
				// AccessLevel 7 - System - Client - Org 
				sql = new StringBuffer("UPDATE C_Cashbook_Acct a ")
						.append("SET CB_Asset_Acct=") 
						.append(acct.getCB_Asset_Acct()<=0?null:acct.getCB_Asset_Acct())
						.append(", CB_Differences_Acct=")
						.append(acct.getCB_Differences_Acct()<=0?null:acct.getCB_Differences_Acct())
						.append(", CB_CashTransfer_Acct=")
						.append(acct.getCB_CashTransfer_Acct()<=0?null:acct.getCB_CashTransfer_Acct())  
						.append(", CB_Expense_Acct=")
						.append(acct.getCB_Expense_Acct()<=0?null:acct.getCB_Expense_Acct())
						.append(", CB_Receipt_Acct=")
						.append(acct.getCB_Receipt_Acct()<=0?null:acct.getCB_Receipt_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=" + p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_Cashbook_Acct x ")
						.append("WHERE x.C_Cashbook_ID=a.C_Cashbook_ID)");
				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_Cashbook_ID@");
			}
			//Update existing Banks of accounts foreign
			if(CopyOf){
					sql = new StringBuffer("UPDATE C_BankAccount_Acct a ")
							.append("SET B_AssetFgn_Acct=")
							.append(acct.getB_AssetFgn_Acct()<=0?null:acct.getB_AssetFgn_Acct())
							.append(", B_InTransitFgn_Acct=")
							.append(acct.getB_InTransitFgn_Acct()<=0?null:acct.getB_InTransitFgn_Acct())
							.append(", Updated= now(), UpdatedBy=0 ")
							.append("WHERE a.C_AcctSchema_ID=")
							.append(p_C_AcctSchema_ID)
							.append(" AND EXISTS (SELECT * FROM C_BankAccount_Acct x ")
							.append("WHERE x.C_BankAccount_ID=a.C_BankAccount_ID)");
					
					updated = DB.executeUpdate(sql.toString(), get_TrxName());
					addLog(0, null, new BigDecimal(updated),
							"@Updated@ @C_BankAccount_ID@");
			}
			
			// Insert new Cashbook
			sql = new StringBuffer(
					"INSERT INTO C_Cashbook_Acct "
							+ "(C_Cashbook_ID, C_AcctSchema_ID,"
							+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
							+ " CB_Asset_Acct, CB_Differences_Acct, CB_CashTransfer_Acct,"
							+ " CB_Expense_Acct, CB_Receipt_Acct) "
							+ " SELECT x.C_Cashbook_ID, acct.C_AcctSchema_ID,"
							+ " x.AD_Client_ID, x.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,"
							+ " acct.CB_Asset_Acct, acct.CB_Differences_Acct, acct.CB_CashTransfer_Acct,"
							+ " acct.CB_Expense_Acct, acct.CB_Receipt_Acct "
							+ " FROM C_Cashbook x"
							+ " INNER JOIN C_AcctSchema_Default acct ON (x.AD_Client_ID=acct.AD_Client_ID) "
							+ " WHERE acct.C_AcctSchema_ID=").append(p_C_AcctSchema_ID)
							.append(" AND NOT EXISTS (SELECT * FROM C_Cashbook_Acct a "
							+ "WHERE a.C_Cashbook_ID=x.C_Cashbook_ID"
							+ " AND a.C_AcctSchema_ID=acct.C_AcctSchema_ID)");
			created = DB.executeUpdate(sql.toString(), get_TrxName());
			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_Cashbook_ID@");
			trx.commit();

		} catch (Exception e) {
			log.log(Level.SEVERE, "Inserting accounting data for Cashbook ", e);
			addLog("@Error@ " + e.getMessage());

			trx.rollback();
		} finally {
			trx.close();
		}
	}
	
	/**
	 * Creditor
	 */ 
	private void doBPCreditor(){
		
		log.info("Generating for BPCreditor");
		
		Trx trx = Trx.get(Trx.createTrxName("BPCRED"), false);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		int updated = 0;
		int created = 0;
		
		try{
			
			// Update existing BP Creditor data
			if (p_CopyOverwriteAcct) {
				sql.append("UPDATE C_BP_Creditor_Acct a ")
						.append("SET A_Creditors_Acct=")
						.append(acct.getA_Creditors_Acct()<=0?null:acct.getA_Creditors_Acct())
						.append(", A_CreditorsFgn_Acct=")
						.append(acct.getA_CreditorsFgn_Acct()<=0?null:acct.getA_CreditorsFgn_Acct())
						.append(", Updated= now(), UpdatedBy=0 ")
						.append("WHERE a.C_AcctSchema_ID=")
						.append(p_C_AcctSchema_ID)
						.append(" AND EXISTS (SELECT * FROM C_BP_Creditor_Acct x ")
						.append("WHERE x.C_BPartner_ID=a.C_BPartner_ID)");

				updated = DB.executeUpdate(sql.toString(), get_TrxName());
				addLog(0, null, new BigDecimal(updated),
						"@Updated@ @C_BPartner_ID@ @IsCreditor@");
			} else{
				
				if(CopyOf){	
					sql.append("UPDATE C_BP_Creditor_Acct a ")
					.append("SET A_Creditors_Acct=")
					.append(acct.getA_Creditors_Acct()<=0?null:acct.getA_Creditors_Acct())
					.append(", A_CreditorsFgn_Acct=")
					.append(acct.getA_CreditorsFgn_Acct()<=0?null:acct.getA_CreditorsFgn_Acct())
					.append(", Updated= now(), UpdatedBy=0 ")
					.append("WHERE a.C_AcctSchema_ID=")
					.append(p_C_AcctSchema_ID)
					.append(" AND EXISTS (SELECT * FROM C_BP_Creditor_Acct x ")
					.append("WHERE x.C_BPartner_ID=a.C_BPartner_ID)");

			    updated = DB.executeUpdate(sql.toString(), get_TrxName());
		    	addLog(0, null, new BigDecimal(updated),
					"@Updated@ @C_BPartner_ID@ @IsCreditor@");
				}
			}
			
			// insert new accounting data
			sql = new StringBuffer("SELECT * FROM C_BPartner "
					+ " WHERE AD_Client_ID IN (0, ?) "
					+ "   AND IsActive = 'Y'         "
					+ "   AND IsCreditor = 'Y'       "
					+ "	  AND C_BPartner_ID NOT IN   "
					+ "		(SELECT DISTINCT C_BPartner_ID "
					+ "		 FROM C_BP_Customer_Acct "
					+ "      WHERE AD_Client_ID = ?  "
					+ "      AND C_AcctSchema_ID = ? "
					+ "		 AND IsActive = 'Y')     ");
			pstmt = DB.prepareStatement(sql.toString(), trx.getTrxName());
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			pstmt.setInt(2, Env.getAD_Client_ID(ctx));
			pstmt.setInt(3, as.getC_AcctSchema_ID());

			log.fine("SQL : " + sql + " - AD_Client_ID = "
					+ Env.getAD_Client_ID(ctx));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MBPartner bPartner = new MBPartner(ctx, rs, trx.getTrxName());

				log.info("Processing BPartner : " + bPartner.getValue());

				if (bPartner.insertAcctClientCust()) {
					created++;
				} else {
					log.severe("Can not insert accounting for BP (Customer) "
							+ bPartner.getValue());

					addLog(0,
							null,
							null,
							"@Error@ @C_BPartner_ID@ @IsCreditor@ "
									+ bPartner.getValue());
				}
			}

			trx.commit();

			addLog(0, null, new BigDecimal(created),
					"@Created@ @C_BPartner_ID@ @IsCreditor@");
					
			
		} catch (Exception e){
			log.log(Level.SEVERE, "Inserting accounting data for BPCreditor ", e);
			addLog("@Error@ " + e.getMessage());
			
			trx.rollback();
		} finally{
			trx.close();
		}
	}
}
