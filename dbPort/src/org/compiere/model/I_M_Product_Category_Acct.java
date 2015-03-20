/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Product_Category_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_M_Product_Category_Acct 
{

    /** TableName=M_Product_Category_Acct */
    public static final String Table_Name = "M_Product_Category_Acct";

    /** AD_Table_ID=401 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

    /** Column name CostingLevel */
    public static final String COLUMNNAME_CostingLevel = "CostingLevel";

	/** Set Costing Level.
	  * The lowest level to accumulate Costing Information
	  */
	public void setCostingLevel (String CostingLevel);

	/** Get Costing Level.
	  * The lowest level to accumulate Costing Information
	  */
	public String getCostingLevel();

    /** Column name CostingMethod */
    public static final String COLUMNNAME_CostingMethod = "CostingMethod";

	/** Set Costing Method.
	  * Indicates how Costs will be calculated
	  */
	public void setCostingMethod (String CostingMethod);

	/** Get Costing Method.
	  * Indicates how Costs will be calculated
	  */
	public String getCostingMethod();

    /** Column name EXME_Prov_Vta_Acct */
    public static final String COLUMNNAME_EXME_Prov_Vta_Acct = "EXME_Prov_Vta_Acct";

	/** Set Provision of Sale	  */
	public void setEXME_Prov_Vta_Acct (int EXME_Prov_Vta_Acct);

	/** Get Provision of Sale	  */
	public int getEXME_Prov_Vta_Acct();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public I_M_Product_Category getM_Product_Category() throws RuntimeException;

    /** Column name P_Asset_Acct */
    public static final String COLUMNNAME_P_Asset_Acct = "P_Asset_Acct";

	/** Set Product Asset.
	  * Account for Product Asset (Inventory)
	  */
	public void setP_Asset_Acct (int P_Asset_Acct);

	/** Get Product Asset.
	  * Account for Product Asset (Inventory)
	  */
	public int getP_Asset_Acct();

    /** Column name P_Burden_Acct */
    public static final String COLUMNNAME_P_Burden_Acct = "P_Burden_Acct";

	/** Set Burden.
	  * The Burden account is the account used Manufacturing Order
	  */
	public void setP_Burden_Acct (int P_Burden_Acct);

	/** Get Burden.
	  * The Burden account is the account used Manufacturing Order
	  */
	public int getP_Burden_Acct();

    /** Column name P_COGS_Acct */
    public static final String COLUMNNAME_P_COGS_Acct = "P_COGS_Acct";

	/** Set Product COGS.
	  * Account for Cost of Goods Sold
	  */
	public void setP_COGS_Acct (int P_COGS_Acct);

	/** Get Product COGS.
	  * Account for Cost of Goods Sold
	  */
	public int getP_COGS_Acct();

    /** Column name P_CostAdjustment_Acct */
    public static final String COLUMNNAME_P_CostAdjustment_Acct = "P_CostAdjustment_Acct";

	/** Set Cost Adjustment.
	  * Product Cost Adjustment Account
	  */
	public void setP_CostAdjustment_Acct (int P_CostAdjustment_Acct);

	/** Get Cost Adjustment.
	  * Product Cost Adjustment Account
	  */
	public int getP_CostAdjustment_Acct();

    /** Column name P_CostOfProduction_Acct */
    public static final String COLUMNNAME_P_CostOfProduction_Acct = "P_CostOfProduction_Acct";

	/** Set Cost Of Production.
	  * The Cost Of Production account is the account used Manufacturing Order
	  */
	public void setP_CostOfProduction_Acct (int P_CostOfProduction_Acct);

	/** Get Cost Of Production.
	  * The Cost Of Production account is the account used Manufacturing Order
	  */
	public int getP_CostOfProduction_Acct();

    /** Column name P_Expense_Acct */
    public static final String COLUMNNAME_P_Expense_Acct = "P_Expense_Acct";

	/** Set Expenses for National Services.
	  * Expenses for National Services
	  */
	public void setP_Expense_Acct (int P_Expense_Acct);

	/** Get Expenses for National Services.
	  * Expenses for National Services
	  */
	public int getP_Expense_Acct();

    /** Column name P_ExpenseFgn_Acct */
    public static final String COLUMNNAME_P_ExpenseFgn_Acct = "P_ExpenseFgn_Acct";

	/** Set Expenses for Foreign Services.
	  * Expenses for Foreign Services
	  */
	public void setP_ExpenseFgn_Acct (int P_ExpenseFgn_Acct);

	/** Get Expenses for Foreign Services.
	  * Expenses for Foreign Services
	  */
	public int getP_ExpenseFgn_Acct();

    /** Column name P_FloorStock_Acct */
    public static final String COLUMNNAME_P_FloorStock_Acct = "P_FloorStock_Acct";

	/** Set Floor Stock.
	  * The Floor Stock account is the account used Manufacturing Order
	  */
	public void setP_FloorStock_Acct (int P_FloorStock_Acct);

	/** Get Floor Stock.
	  * The Floor Stock account is the account used Manufacturing Order
	  */
	public int getP_FloorStock_Acct();

    /** Column name P_InventoryClearing_Acct */
    public static final String COLUMNNAME_P_InventoryClearing_Acct = "P_InventoryClearing_Acct";

	/** Set National Shopping.
	  * National Shopping
	  */
	public void setP_InventoryClearing_Acct (int P_InventoryClearing_Acct);

	/** Get National Shopping.
	  * National Shopping
	  */
	public int getP_InventoryClearing_Acct();

    /** Column name P_InventoryClearingFgn_Acct */
    public static final String COLUMNNAME_P_InventoryClearingFgn_Acct = "P_InventoryClearingFgn_Acct";

	/** Set Foreign Shopping.
	  * Foreign Shopping
	  */
	public void setP_InventoryClearingFgn_Acct (int P_InventoryClearingFgn_Acct);

	/** Get Foreign Shopping.
	  * Foreign Shopping
	  */
	public int getP_InventoryClearingFgn_Acct();

    /** Column name P_InvoicePriceVariance_Acct */
    public static final String COLUMNNAME_P_InvoicePriceVariance_Acct = "P_InvoicePriceVariance_Acct";

	/** Set Invoice Price Variance.
	  * Difference between Costs and Invoice Price (IPV)
	  */
	public void setP_InvoicePriceVariance_Acct (int P_InvoicePriceVariance_Acct);

	/** Get Invoice Price Variance.
	  * Difference between Costs and Invoice Price (IPV)
	  */
	public int getP_InvoicePriceVariance_Acct();

    /** Column name P_Labor_Acct */
    public static final String COLUMNNAME_P_Labor_Acct = "P_Labor_Acct";

	/** Set Labor.
	  * The Labor account is the account used Manufacturing Order
	  */
	public void setP_Labor_Acct (int P_Labor_Acct);

	/** Get Labor.
	  * The Labor account is the account used Manufacturing Order
	  */
	public int getP_Labor_Acct();

    /** Column name P_MethodChangeVariance_Acct */
    public static final String COLUMNNAME_P_MethodChangeVariance_Acct = "P_MethodChangeVariance_Acct";

	/** Set Method Change Variance.
	  * The Method Change Variance account is the account used Manufacturing Order
	  */
	public void setP_MethodChangeVariance_Acct (int P_MethodChangeVariance_Acct);

	/** Get Method Change Variance.
	  * The Method Change Variance account is the account used Manufacturing Order
	  */
	public int getP_MethodChangeVariance_Acct();

    /** Column name P_MixVariance_Acct */
    public static final String COLUMNNAME_P_MixVariance_Acct = "P_MixVariance_Acct";

	/** Set Mix Variance.
	  * The Mix Variance account is the account used Manufacturing Order
	  */
	public void setP_MixVariance_Acct (int P_MixVariance_Acct);

	/** Get Mix Variance.
	  * The Mix Variance account is the account used Manufacturing Order
	  */
	public int getP_MixVariance_Acct();

    /** Column name P_OutsideProcessing_Acct */
    public static final String COLUMNNAME_P_OutsideProcessing_Acct = "P_OutsideProcessing_Acct";

	/** Set Outside Processing.
	  * The Outside Processing Account is the account used in Manufacturing Order
	  */
	public void setP_OutsideProcessing_Acct (int P_OutsideProcessing_Acct);

	/** Get Outside Processing.
	  * The Outside Processing Account is the account used in Manufacturing Order
	  */
	public int getP_OutsideProcessing_Acct();

    /** Column name P_Overhead_Acct */
    public static final String COLUMNNAME_P_Overhead_Acct = "P_Overhead_Acct";

	/** Set Overhead.
	  * The Overhead account is the account used  in Manufacturing Order 
	  */
	public void setP_Overhead_Acct (int P_Overhead_Acct);

	/** Get Overhead.
	  * The Overhead account is the account used  in Manufacturing Order 
	  */
	public int getP_Overhead_Acct();

    /** Column name P_PurchasePriceVariance_Acct */
    public static final String COLUMNNAME_P_PurchasePriceVariance_Acct = "P_PurchasePriceVariance_Acct";

	/** Set Purchase Price Variance.
	  * Difference between Standard Cost and Purchase Price (PPV)
	  */
	public void setP_PurchasePriceVariance_Acct (int P_PurchasePriceVariance_Acct);

	/** Get Purchase Price Variance.
	  * Difference between Standard Cost and Purchase Price (PPV)
	  */
	public int getP_PurchasePriceVariance_Acct();

    /** Column name P_PurchasesReturns_Acct */
    public static final String COLUMNNAME_P_PurchasesReturns_Acct = "P_PurchasesReturns_Acct";

	/** Set Purchase Returns.
	  * Purchase Returns
	  */
	public void setP_PurchasesReturns_Acct (int P_PurchasesReturns_Acct);

	/** Get Purchase Returns.
	  * Purchase Returns
	  */
	public int getP_PurchasesReturns_Acct();

    /** Column name P_RateVariance_Acct */
    public static final String COLUMNNAME_P_RateVariance_Acct = "P_RateVariance_Acct";

	/** Set Rate Variance.
	  * The Rate Variance account is the account used Manufacturing Order
	  */
	public void setP_RateVariance_Acct (int P_RateVariance_Acct);

	/** Get Rate Variance.
	  * The Rate Variance account is the account used Manufacturing Order
	  */
	public int getP_RateVariance_Acct();

    /** Column name P_Revenue_Acct */
    public static final String COLUMNNAME_P_Revenue_Acct = "P_Revenue_Acct";

	/** Set Product Revenue.
	  * Account for Product Revenue (Sales Account)
	  */
	public void setP_Revenue_Acct (int P_Revenue_Acct);

	/** Get Product Revenue.
	  * Account for Product Revenue (Sales Account)
	  */
	public int getP_Revenue_Acct();

    /** Column name P_RevenueFgn_Acct */
    public static final String COLUMNNAME_P_RevenueFgn_Acct = "P_RevenueFgn_Acct";

	/** Set Sales Abroad.
	  * Sales Abroad
	  */
	public void setP_RevenueFgn_Acct (int P_RevenueFgn_Acct);

	/** Get Sales Abroad.
	  * Sales Abroad
	  */
	public int getP_RevenueFgn_Acct();

    /** Column name P_RevenueTECash_Acct */
    public static final String COLUMNNAME_P_RevenueTECash_Acct = "P_RevenueTECash_Acct";

	/** Set Exempt Sales counted.
	  * Exempt Sales counted
	  */
	public void setP_RevenueTECash_Acct (int P_RevenueTECash_Acct);

	/** Get Exempt Sales counted.
	  * Exempt Sales counted
	  */
	public int getP_RevenueTECash_Acct();

    /** Column name P_RevenueTECredit_Acct */
    public static final String COLUMNNAME_P_RevenueTECredit_Acct = "P_RevenueTECredit_Acct";

	/** Set Exempt Sales Credit.
	  * Exempt Sales Credit
	  */
	public void setP_RevenueTECredit_Acct (int P_RevenueTECredit_Acct);

	/** Get Exempt Sales Credit.
	  * Exempt Sales Credit
	  */
	public int getP_RevenueTECredit_Acct();

    /** Column name P_RevenueTGCash_Acct */
    public static final String COLUMNNAME_P_RevenueTGCash_Acct = "P_RevenueTGCash_Acct";

	/** Set Taxable sales by counting in general rate.
	  * Taxable sales by counting in general rate
	  */
	public void setP_RevenueTGCash_Acct (int P_RevenueTGCash_Acct);

	/** Get Taxable sales by counting in general rate.
	  * Taxable sales by counting in general rate
	  */
	public int getP_RevenueTGCash_Acct();

    /** Column name P_RevenueTGCredit_Acct */
    public static final String COLUMNNAME_P_RevenueTGCredit_Acct = "P_RevenueTGCredit_Acct";

	/** Set Taxable sales tax credit generally.
	  * Taxable sales tax credit generally
	  */
	public void setP_RevenueTGCredit_Acct (int P_RevenueTGCredit_Acct);

	/** Get Taxable sales tax credit generally.
	  * Taxable sales tax credit generally
	  */
	public int getP_RevenueTGCredit_Acct();

    /** Column name P_RevenueTZCash_Acct */
    public static final String COLUMNNAME_P_RevenueTZCash_Acct = "P_RevenueTZCash_Acct";

	/** Set Taxable Sales 0 % cash.
	  * Taxable Sales 0 % cash
	  */
	public void setP_RevenueTZCash_Acct (int P_RevenueTZCash_Acct);

	/** Get Taxable Sales 0 % cash.
	  * Taxable Sales 0 % cash
	  */
	public int getP_RevenueTZCash_Acct();

    /** Column name P_RevenueTZCredit_Acct */
    public static final String COLUMNNAME_P_RevenueTZCredit_Acct = "P_RevenueTZCredit_Acct";

	/** Set Taxable Sales 0 % credit.
	  * Taxable Sales 0 % credit
	  */
	public void setP_RevenueTZCredit_Acct (int P_RevenueTZCredit_Acct);

	/** Get Taxable Sales 0 % credit.
	  * Taxable Sales 0 % credit
	  */
	public int getP_RevenueTZCredit_Acct();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name P_SalesReturns_Acct */
    public static final String COLUMNNAME_P_SalesReturns_Acct = "P_SalesReturns_Acct";

	/** Set Sales Returns.
	  * Sales Returns
	  */
	public void setP_SalesReturns_Acct (int P_SalesReturns_Acct);

	/** Get Sales Returns.
	  * Sales Returns
	  */
	public int getP_SalesReturns_Acct();

    /** Column name P_SalesReturnsE_Acct */
    public static final String COLUMNNAME_P_SalesReturnsE_Acct = "P_SalesReturnsE_Acct";

	/** Set Returns on exempt Sales.
	  * Returns on exempt Sales
	  */
	public void setP_SalesReturnsE_Acct (int P_SalesReturnsE_Acct);

	/** Get Returns on exempt Sales.
	  * Returns on exempt Sales
	  */
	public int getP_SalesReturnsE_Acct();

    /** Column name P_SalesReturnsG_Acct */
    public static final String COLUMNNAME_P_SalesReturnsG_Acct = "P_SalesReturnsG_Acct";

	/** Set Returns sales Overall Rate.
	  * Returns sales Overall Rate
	  */
	public void setP_SalesReturnsG_Acct (int P_SalesReturnsG_Acct);

	/** Get Returns sales Overall Rate.
	  * Returns sales Overall Rate
	  */
	public int getP_SalesReturnsG_Acct();

    /** Column name P_SalesReturnsZ_Acct */
    public static final String COLUMNNAME_P_SalesReturnsZ_Acct = "P_SalesReturnsZ_Acct";

	/** Set Returns Sales 0%.
	  * Returns Sales 0%
	  */
	public void setP_SalesReturnsZ_Acct (int P_SalesReturnsZ_Acct);

	/** Get Returns Sales 0%.
	  * Returns Sales 0%
	  */
	public int getP_SalesReturnsZ_Acct();

    /** Column name P_Scrap_Acct */
    public static final String COLUMNNAME_P_Scrap_Acct = "P_Scrap_Acct";

	/** Set Scrap.
	  * The Scrap account is the account used  in Manufacturing Order 
	  */
	public void setP_Scrap_Acct (int P_Scrap_Acct);

	/** Get Scrap.
	  * The Scrap account is the account used  in Manufacturing Order 
	  */
	public int getP_Scrap_Acct();

    /** Column name P_TradeDiscountGrant_Acct */
    public static final String COLUMNNAME_P_TradeDiscountGrant_Acct = "P_TradeDiscountGrant_Acct";

	/** Set Trade Discount Granted.
	  * Trade Discount Granted Account
	  */
	public void setP_TradeDiscountGrant_Acct (int P_TradeDiscountGrant_Acct);

	/** Get Trade Discount Granted.
	  * Trade Discount Granted Account
	  */
	public int getP_TradeDiscountGrant_Acct();

    /** Column name P_TradeDiscountGrantE_Acct */
    public static final String COLUMNNAME_P_TradeDiscountGrantE_Acct = "P_TradeDiscountGrantE_Acct";

	/** Set Returns , Discounts and Rebates on exempt sales.
	  * Returns , Discounts and Rebates on exempt sales
	  */
	public void setP_TradeDiscountGrantE_Acct (int P_TradeDiscountGrantE_Acct);

	/** Get Returns , Discounts and Rebates on exempt sales.
	  * Returns , Discounts and Rebates on exempt sales
	  */
	public int getP_TradeDiscountGrantE_Acct();

    /** Column name P_TradeDiscountGrantG_Acct */
    public static final String COLUMNNAME_P_TradeDiscountGrantG_Acct = "P_TradeDiscountGrantG_Acct";

	/** Set Returns , Discounts and Sales on Sales overall rate.
	  * Returns , Discounts and Sales on Sales overall rate
	  */
	public void setP_TradeDiscountGrantG_Acct (int P_TradeDiscountGrantG_Acct);

	/** Get Returns , Discounts and Sales on Sales overall rate.
	  * Returns , Discounts and Sales on Sales overall rate
	  */
	public int getP_TradeDiscountGrantG_Acct();

    /** Column name P_TradeDiscountGrantZ_Acct */
    public static final String COLUMNNAME_P_TradeDiscountGrantZ_Acct = "P_TradeDiscountGrantZ_Acct";

	/** Set Returns , Discounts and Sales on Sales 0 %.
	  * Returns , Discounts and Sales on Sales 0 %
	  */
	public void setP_TradeDiscountGrantZ_Acct (int P_TradeDiscountGrantZ_Acct);

	/** Get Returns , Discounts and Sales on Sales 0 %.
	  * Returns , Discounts and Sales on Sales 0 %
	  */
	public int getP_TradeDiscountGrantZ_Acct();

    /** Column name P_TradeDiscountRec_Acct */
    public static final String COLUMNNAME_P_TradeDiscountRec_Acct = "P_TradeDiscountRec_Acct";

	/** Set Trade Discount Received.
	  * Trade Discount Receivable Account
	  */
	public void setP_TradeDiscountRec_Acct (int P_TradeDiscountRec_Acct);

	/** Get Trade Discount Received.
	  * Trade Discount Receivable Account
	  */
	public int getP_TradeDiscountRec_Acct();

    /** Column name P_UsageVariance_Acct */
    public static final String COLUMNNAME_P_UsageVariance_Acct = "P_UsageVariance_Acct";

	/** Set Usage Variance.
	  * The Usage Variance account is the account used Manufacturing Order
	  */
	public void setP_UsageVariance_Acct (int P_UsageVariance_Acct);

	/** Get Usage Variance.
	  * The Usage Variance account is the account used Manufacturing Order
	  */
	public int getP_UsageVariance_Acct();

    /** Column name P_WIP_Acct */
    public static final String COLUMNNAME_P_WIP_Acct = "P_WIP_Acct";

	/** Set Work In Process.
	  * The Work in Process account is the account used Manufacturing Order
	  */
	public void setP_WIP_Acct (int P_WIP_Acct);

	/** Get Work In Process.
	  * The Work in Process account is the account used Manufacturing Order
	  */
	public int getP_WIP_Acct();
}
