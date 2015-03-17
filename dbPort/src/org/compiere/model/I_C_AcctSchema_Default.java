/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_AcctSchema_Default
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_AcctSchema_Default 
{

    /** TableName=C_AcctSchema_Default */
    public static final String Table_Name = "C_AcctSchema_Default";

    /** AD_Table_ID=315 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name A_Creditors_Acct */
    public static final String COLUMNNAME_A_Creditors_Acct = "A_Creditors_Acct";

	/** Set National Sundry Creditors.
	  * National Sundry Creditors
	  */
	public void setA_Creditors_Acct (int A_Creditors_Acct);

	/** Get National Sundry Creditors.
	  * National Sundry Creditors
	  */
	public int getA_Creditors_Acct();

    /** Column name A_CreditorsFgn_Acct */
    public static final String COLUMNNAME_A_CreditorsFgn_Acct = "A_CreditorsFgn_Acct";

	/** Set Sundry Creditors Foreigners.
	  * Sundry Creditors Foreigners
	  */
	public void setA_CreditorsFgn_Acct (int A_CreditorsFgn_Acct);

	/** Get Sundry Creditors Foreigners.
	  * Sundry Creditors Foreigners
	  */
	public int getA_CreditorsFgn_Acct();

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

    /** Column name B_Asset_Acct */
    public static final String COLUMNNAME_B_Asset_Acct = "B_Asset_Acct";

	/** Set Bank Asset.
	  * Bank Asset Account
	  */
	public void setB_Asset_Acct (int B_Asset_Acct);

	/** Get Bank Asset.
	  * Bank Asset Account
	  */
	public int getB_Asset_Acct();

    /** Column name B_AssetFgn_Acct */
    public static final String COLUMNNAME_B_AssetFgn_Acct = "B_AssetFgn_Acct";

	/** Set Foreign Banks.
	  * Foreign Banks
	  */
	public void setB_AssetFgn_Acct (int B_AssetFgn_Acct);

	/** Get Foreign Banks.
	  * Foreign Banks
	  */
	public int getB_AssetFgn_Acct();

    /** Column name B_Expense_Acct */
    public static final String COLUMNNAME_B_Expense_Acct = "B_Expense_Acct";

	/** Set Bank Expense.
	  * Bank Expense Account
	  */
	public void setB_Expense_Acct (int B_Expense_Acct);

	/** Get Bank Expense.
	  * Bank Expense Account
	  */
	public int getB_Expense_Acct();

    /** Column name B_InterestExp_Acct */
    public static final String COLUMNNAME_B_InterestExp_Acct = "B_InterestExp_Acct";

	/** Set Bank Interest Expense.
	  * Bank Interest Expense Account
	  */
	public void setB_InterestExp_Acct (int B_InterestExp_Acct);

	/** Get Bank Interest Expense.
	  * Bank Interest Expense Account
	  */
	public int getB_InterestExp_Acct();

    /** Column name B_InterestRev_Acct */
    public static final String COLUMNNAME_B_InterestRev_Acct = "B_InterestRev_Acct";

	/** Set Bank Interest Revenue.
	  * Bank Interest Revenue Account
	  */
	public void setB_InterestRev_Acct (int B_InterestRev_Acct);

	/** Get Bank Interest Revenue.
	  * Bank Interest Revenue Account
	  */
	public int getB_InterestRev_Acct();

    /** Column name B_InTransit_Acct */
    public static final String COLUMNNAME_B_InTransit_Acct = "B_InTransit_Acct";

	/** Set Bank In Transit.
	  * Bank In Transit Account
	  */
	public void setB_InTransit_Acct (int B_InTransit_Acct);

	/** Get Bank In Transit.
	  * Bank In Transit Account
	  */
	public int getB_InTransit_Acct();

    /** Column name B_InTransitFgn_Acct */
    public static final String COLUMNNAME_B_InTransitFgn_Acct = "B_InTransitFgn_Acct";

	/** Set Foreign Banks In Transit.
	  * Foreign Banks In Transit
	  */
	public void setB_InTransitFgn_Acct (int B_InTransitFgn_Acct);

	/** Get Foreign Banks In Transit.
	  * Foreign Banks In Transit
	  */
	public int getB_InTransitFgn_Acct();

    /** Column name B_PaymentSelect_Acct */
    public static final String COLUMNNAME_B_PaymentSelect_Acct = "B_PaymentSelect_Acct";

	/** Set Payments.
	  * AP Payment Selection Clearing Account
	  */
	public void setB_PaymentSelect_Acct (int B_PaymentSelect_Acct);

	/** Get Payments.
	  * AP Payment Selection Clearing Account
	  */
	public int getB_PaymentSelect_Acct();

    /** Column name B_RevaluationGain_Acct */
    public static final String COLUMNNAME_B_RevaluationGain_Acct = "B_RevaluationGain_Acct";

	/** Set Bank Fluctuation Gain.
	  * Bank Fluctuation Gain
	  */
	public void setB_RevaluationGain_Acct (int B_RevaluationGain_Acct);

	/** Get Bank Fluctuation Gain.
	  * Bank Fluctuation Gain
	  */
	public int getB_RevaluationGain_Acct();

    /** Column name B_RevaluationLoss_Acct */
    public static final String COLUMNNAME_B_RevaluationLoss_Acct = "B_RevaluationLoss_Acct";

	/** Set Bank Fluctuation Loss.
	  * Bank Fluctuation Loss
	  */
	public void setB_RevaluationLoss_Acct (int B_RevaluationLoss_Acct);

	/** Get Bank Fluctuation Loss.
	  * Bank Fluctuation Loss
	  */
	public int getB_RevaluationLoss_Acct();

    /** Column name B_SettlementGain_Acct */
    public static final String COLUMNNAME_B_SettlementGain_Acct = "B_SettlementGain_Acct";

	/** Set Bank Settlement Gain.
	  * Bank Settlement Gain Account
	  */
	public void setB_SettlementGain_Acct (int B_SettlementGain_Acct);

	/** Get Bank Settlement Gain.
	  * Bank Settlement Gain Account
	  */
	public int getB_SettlementGain_Acct();

    /** Column name B_SettlementLoss_Acct */
    public static final String COLUMNNAME_B_SettlementLoss_Acct = "B_SettlementLoss_Acct";

	/** Set Bank Settlement Loss.
	  * Bank Settlement Loss Account
	  */
	public void setB_SettlementLoss_Acct (int B_SettlementLoss_Acct);

	/** Get Bank Settlement Loss.
	  * Bank Settlement Loss Account
	  */
	public int getB_SettlementLoss_Acct();

    /** Column name B_UnallocatedCash_Acct */
    public static final String COLUMNNAME_B_UnallocatedCash_Acct = "B_UnallocatedCash_Acct";

	/** Set Unallocated Cash.
	  * Unallocated Cash Clearing Account
	  */
	public void setB_UnallocatedCash_Acct (int B_UnallocatedCash_Acct);

	/** Get Unallocated Cash.
	  * Unallocated Cash Clearing Account
	  */
	public int getB_UnallocatedCash_Acct();

    /** Column name B_Unidentified_Acct */
    public static final String COLUMNNAME_B_Unidentified_Acct = "B_Unidentified_Acct";

	/** Set Bank Unidentified Receipts.
	  * Bank Unidentified Receipts Account
	  */
	public void setB_Unidentified_Acct (int B_Unidentified_Acct);

	/** Get Bank Unidentified Receipts.
	  * Bank Unidentified Receipts Account
	  */
	public int getB_Unidentified_Acct();

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

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

    /** Column name CB_Asset_Acct */
    public static final String COLUMNNAME_CB_Asset_Acct = "CB_Asset_Acct";

	/** Set Cash Book.
	  * Cash Book
	  */
	public void setCB_Asset_Acct (int CB_Asset_Acct);

	/** Get Cash Book.
	  * Cash Book
	  */
	public int getCB_Asset_Acct();

    /** Column name CB_CashTransfer_Acct */
    public static final String COLUMNNAME_CB_CashTransfer_Acct = "CB_CashTransfer_Acct";

	/** Set Cash Transfer.
	  * Cash Transfer Clearing Account
	  */
	public void setCB_CashTransfer_Acct (int CB_CashTransfer_Acct);

	/** Get Cash Transfer.
	  * Cash Transfer Clearing Account
	  */
	public int getCB_CashTransfer_Acct();

    /** Column name CB_Differences_Acct */
    public static final String COLUMNNAME_CB_Differences_Acct = "CB_Differences_Acct";

	/** Set Cash Book Differences .
	  * Cash Book Differences Account
	  */
	public void setCB_Differences_Acct (int CB_Differences_Acct);

	/** Get Cash Book Differences .
	  * Cash Book Differences Account
	  */
	public int getCB_Differences_Acct();

    /** Column name CB_Expense_Acct */
    public static final String COLUMNNAME_CB_Expense_Acct = "CB_Expense_Acct";

	/** Set Cash Book Expense.
	  * Cash Book Expense Account
	  */
	public void setCB_Expense_Acct (int CB_Expense_Acct);

	/** Get Cash Book Expense.
	  * Cash Book Expense Account
	  */
	public int getCB_Expense_Acct();

    /** Column name CB_Receipt_Acct */
    public static final String COLUMNNAME_CB_Receipt_Acct = "CB_Receipt_Acct";

	/** Set Cash Book Receipt.
	  * Cash Book Receipts Account
	  */
	public void setCB_Receipt_Acct (int CB_Receipt_Acct);

	/** Get Cash Book Receipt.
	  * Cash Book Receipts Account
	  */
	public int getCB_Receipt_Acct();

    /** Column name Ch_Expense_Acct */
    public static final String COLUMNNAME_Ch_Expense_Acct = "Ch_Expense_Acct";

	/** Set Charge Expense.
	  * Charge Expense Account
	  */
	public void setCh_Expense_Acct (int Ch_Expense_Acct);

	/** Get Charge Expense.
	  * Charge Expense Account
	  */
	public int getCh_Expense_Acct();

    /** Column name Ch_Revenue_Acct */
    public static final String COLUMNNAME_Ch_Revenue_Acct = "Ch_Revenue_Acct";

	/** Set Charge Revenue.
	  * Charge Revenue Account
	  */
	public void setCh_Revenue_Acct (int Ch_Revenue_Acct);

	/** Get Charge Revenue.
	  * Charge Revenue Account
	  */
	public int getCh_Revenue_Acct();

    /** Column name C_Prepayment_Acct */
    public static final String COLUMNNAME_C_Prepayment_Acct = "C_Prepayment_Acct";

	/** Set Advances to National Customers.
	  * Advances to National Customers
	  */
	public void setC_Prepayment_Acct (int C_Prepayment_Acct);

	/** Get Advances to National Customers.
	  * Advances to National Customers
	  */
	public int getC_Prepayment_Acct();

    /** Column name C_PrepaymentFgn_Acct */
    public static final String COLUMNNAME_C_PrepaymentFgn_Acct = "C_PrepaymentFgn_Acct";

	/** Set Advances to Foreign Customers.
	  * Advances to Foreign Customers
	  */
	public void setC_PrepaymentFgn_Acct (int C_PrepaymentFgn_Acct);

	/** Get Advances to Foreign Customers.
	  * Advances to Foreign Customers
	  */
	public int getC_PrepaymentFgn_Acct();

    /** Column name C_Receivable_Acct */
    public static final String COLUMNNAME_C_Receivable_Acct = "C_Receivable_Acct";

	/** Set National customer.
	  * National customer
	  */
	public void setC_Receivable_Acct (int C_Receivable_Acct);

	/** Get National customer.
	  * National customer
	  */
	public int getC_Receivable_Acct();

    /** Column name C_ReceivableFgn_Acct */
    public static final String COLUMNNAME_C_ReceivableFgn_Acct = "C_ReceivableFgn_Acct";

	/** Set Foreign Clients.
	  * Foreign Clients
	  */
	public void setC_ReceivableFgn_Acct (int C_ReceivableFgn_Acct);

	/** Get Foreign Clients.
	  * Foreign Clients
	  */
	public int getC_ReceivableFgn_Acct();

    /** Column name C_Receivable_Services_Acct */
    public static final String COLUMNNAME_C_Receivable_Services_Acct = "C_Receivable_Services_Acct";

	/** Set Receivable Services.
	  * Customer Accounts Receivables Services Account
	  */
	public void setC_Receivable_Services_Acct (int C_Receivable_Services_Acct);

	/** Get Receivable Services.
	  * Customer Accounts Receivables Services Account
	  */
	public int getC_Receivable_Services_Acct();

    /** Column name E_Expense_Acct */
    public static final String COLUMNNAME_E_Expense_Acct = "E_Expense_Acct";

	/** Set Employee Expense.
	  * Account for Employee Expenses
	  */
	public void setE_Expense_Acct (int E_Expense_Acct);

	/** Get Employee Expense.
	  * Account for Employee Expenses
	  */
	public int getE_Expense_Acct();

    /** Column name E_Prepayment_Acct */
    public static final String COLUMNNAME_E_Prepayment_Acct = "E_Prepayment_Acct";

	/** Set Employee Prepayment.
	  * Account for Employee Expense Prepayments
	  */
	public void setE_Prepayment_Acct (int E_Prepayment_Acct);

	/** Get Employee Prepayment.
	  * Account for Employee Expense Prepayments
	  */
	public int getE_Prepayment_Acct();

    /** Column name EXME_Cob_NF_Acct */
    public static final String COLUMNNAME_EXME_Cob_NF_Acct = "EXME_Cob_NF_Acct";

	/** Set Income noninvoiced	  */
	public void setEXME_Cob_NF_Acct (int EXME_Cob_NF_Acct);

	/** Get Income noninvoiced	  */
	public int getEXME_Cob_NF_Acct();

    /** Column name EXME_Prov_Vta_Acct */
    public static final String COLUMNNAME_EXME_Prov_Vta_Acct = "EXME_Prov_Vta_Acct";

	/** Set Provision of Sale	  */
	public void setEXME_Prov_Vta_Acct (int EXME_Prov_Vta_Acct);

	/** Get Provision of Sale	  */
	public int getEXME_Prov_Vta_Acct();

    /** Column name NotInvoicedReceipts_Acct */
    public static final String COLUMNNAME_NotInvoicedReceipts_Acct = "NotInvoicedReceipts_Acct";

	/** Set Receiving to Warehouse Pending to Invoice.
	  * Receiving to Warehouse Pending to Invoice
	  */
	public void setNotInvoicedReceipts_Acct (int NotInvoicedReceipts_Acct);

	/** Get Receiving to Warehouse Pending to Invoice.
	  * Receiving to Warehouse Pending to Invoice
	  */
	public int getNotInvoicedReceipts_Acct();

    /** Column name NotInvoicedReceivables_Acct */
    public static final String COLUMNNAME_NotInvoicedReceivables_Acct = "NotInvoicedReceivables_Acct";

	/** Set Not-invoiced Receivables.
	  * Account for not invoiced Receivables
	  */
	public void setNotInvoicedReceivables_Acct (int NotInvoicedReceivables_Acct);

	/** Get Not-invoiced Receivables.
	  * Account for not invoiced Receivables
	  */
	public int getNotInvoicedReceivables_Acct();

    /** Column name NotInvoicedRevenue_Acct */
    public static final String COLUMNNAME_NotInvoicedRevenue_Acct = "NotInvoicedRevenue_Acct";

	/** Set Not-invoiced Revenue.
	  * Account for not invoiced Revenue
	  */
	public void setNotInvoicedRevenue_Acct (int NotInvoicedRevenue_Acct);

	/** Get Not-invoiced Revenue.
	  * Account for not invoiced Revenue
	  */
	public int getNotInvoicedRevenue_Acct();

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

    /** Column name PayDiscount_Exp_Acct */
    public static final String COLUMNNAME_PayDiscount_Exp_Acct = "PayDiscount_Exp_Acct";

	/** Set Payment Discount Expense.
	  * Payment Discount Expense Account
	  */
	public void setPayDiscount_Exp_Acct (int PayDiscount_Exp_Acct);

	/** Get Payment Discount Expense.
	  * Payment Discount Expense Account
	  */
	public int getPayDiscount_Exp_Acct();

    /** Column name PayDiscount_Rev_Acct */
    public static final String COLUMNNAME_PayDiscount_Rev_Acct = "PayDiscount_Rev_Acct";

	/** Set Payment Discount Revenue.
	  * Payment Discount Revenue Account
	  */
	public void setPayDiscount_Rev_Acct (int PayDiscount_Rev_Acct);

	/** Get Payment Discount Revenue.
	  * Payment Discount Revenue Account
	  */
	public int getPayDiscount_Rev_Acct();

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

    /** Column name PJ_Asset_Acct */
    public static final String COLUMNNAME_PJ_Asset_Acct = "PJ_Asset_Acct";

	/** Set Project Asset.
	  * Project Asset Account
	  */
	public void setPJ_Asset_Acct (int PJ_Asset_Acct);

	/** Get Project Asset.
	  * Project Asset Account
	  */
	public int getPJ_Asset_Acct();

    /** Column name PJ_WIP_Acct */
    public static final String COLUMNNAME_PJ_WIP_Acct = "PJ_WIP_Acct";

	/** Set Work In Progress.
	  * Account for Work in Progress
	  */
	public void setPJ_WIP_Acct (int PJ_WIP_Acct);

	/** Get Work In Progress.
	  * Account for Work in Progress
	  */
	public int getPJ_WIP_Acct();

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

    /** Column name RealizedGain_Acct */
    public static final String COLUMNNAME_RealizedGain_Acct = "RealizedGain_Acct";

	/** Set Realized Gain Acct.
	  * Realized Gain Account
	  */
	public void setRealizedGain_Acct (int RealizedGain_Acct);

	/** Get Realized Gain Acct.
	  * Realized Gain Account
	  */
	public int getRealizedGain_Acct();

    /** Column name RealizedLoss_Acct */
    public static final String COLUMNNAME_RealizedLoss_Acct = "RealizedLoss_Acct";

	/** Set Realized Loss Acct.
	  * Realized Loss Account
	  */
	public void setRealizedLoss_Acct (int RealizedLoss_Acct);

	/** Get Realized Loss Acct.
	  * Realized Loss Account
	  */
	public int getRealizedLoss_Acct();

    /** Column name T_Credit_Acct */
    public static final String COLUMNNAME_T_Credit_Acct = "T_Credit_Acct";

	/** Set Tax Credit.
	  * Account for Tax you can reclaim
	  */
	public void setT_Credit_Acct (int T_Credit_Acct);

	/** Get Tax Credit.
	  * Account for Tax you can reclaim
	  */
	public int getT_Credit_Acct();

    /** Column name T_Due_Acct */
    public static final String COLUMNNAME_T_Due_Acct = "T_Due_Acct";

	/** Set Tax Due.
	  * Account for Tax you have to pay
	  */
	public void setT_Due_Acct (int T_Due_Acct);

	/** Get Tax Due.
	  * Account for Tax you have to pay
	  */
	public int getT_Due_Acct();

    /** Column name T_Expense_Acct */
    public static final String COLUMNNAME_T_Expense_Acct = "T_Expense_Acct";

	/** Set Tax Expense.
	  * Account for paid tax you cannot reclaim
	  */
	public void setT_Expense_Acct (int T_Expense_Acct);

	/** Get Tax Expense.
	  * Account for paid tax you cannot reclaim
	  */
	public int getT_Expense_Acct();

    /** Column name T_HoldLiab_Acct */
    public static final String COLUMNNAME_T_HoldLiab_Acct = "T_HoldLiab_Acct";

	/** Set withholding taxes payable.
	  * withholding taxes payable
	  */
	public void setT_HoldLiab_Acct (int T_HoldLiab_Acct);

	/** Get withholding taxes payable.
	  * withholding taxes payable
	  */
	public int getT_HoldLiab_Acct();

    /** Column name T_HoldRec_Acct */
    public static final String COLUMNNAME_T_HoldRec_Acct = "T_HoldRec_Acct";

	/** Set Withholding a Favor.
	  * Withholding a Favor
	  */
	public void setT_HoldRec_Acct (int T_HoldRec_Acct);

	/** Get Withholding a Favor.
	  * Withholding a Favor
	  */
	public int getT_HoldRec_Acct();

    /** Column name T_Liability_Acct */
    public static final String COLUMNNAME_T_Liability_Acct = "T_Liability_Acct";

	/** Set Tax Liability.
	  * Account for Tax declaration liability
	  */
	public void setT_Liability_Acct (int T_Liability_Acct);

	/** Get Tax Liability.
	  * Account for Tax declaration liability
	  */
	public int getT_Liability_Acct();

    /** Column name T_Receivables_Acct */
    public static final String COLUMNNAME_T_Receivables_Acct = "T_Receivables_Acct";

	/** Set Tax Receivables.
	  * Account for Tax credit after tax declaration
	  */
	public void setT_Receivables_Acct (int T_Receivables_Acct);

	/** Get Tax Receivables.
	  * Account for Tax credit after tax declaration
	  */
	public int getT_Receivables_Acct();

    /** Column name UnEarnedRevenue_Acct */
    public static final String COLUMNNAME_UnEarnedRevenue_Acct = "UnEarnedRevenue_Acct";

	/** Set Unearned Revenue.
	  * Account for unearned revenue
	  */
	public void setUnEarnedRevenue_Acct (int UnEarnedRevenue_Acct);

	/** Get Unearned Revenue.
	  * Account for unearned revenue
	  */
	public int getUnEarnedRevenue_Acct();

    /** Column name UnrealizedGain_Acct */
    public static final String COLUMNNAME_UnrealizedGain_Acct = "UnrealizedGain_Acct";

	/** Set Unrealized Gain Acct.
	  * Unrealized Gain Account for currency revaluation
	  */
	public void setUnrealizedGain_Acct (int UnrealizedGain_Acct);

	/** Get Unrealized Gain Acct.
	  * Unrealized Gain Account for currency revaluation
	  */
	public int getUnrealizedGain_Acct();

    /** Column name UnrealizedLoss_Acct */
    public static final String COLUMNNAME_UnrealizedLoss_Acct = "UnrealizedLoss_Acct";

	/** Set Unrealized Loss Acct.
	  * Unrealized Loss Account for currency revaluation
	  */
	public void setUnrealizedLoss_Acct (int UnrealizedLoss_Acct);

	/** Get Unrealized Loss Acct.
	  * Unrealized Loss Account for currency revaluation
	  */
	public int getUnrealizedLoss_Acct();

    /** Column name V_Liability_Acct */
    public static final String COLUMNNAME_V_Liability_Acct = "V_Liability_Acct";

	/** Set Passive National Suppliers.
	  * Passive National Suppliers
	  */
	public void setV_Liability_Acct (int V_Liability_Acct);

	/** Get Passive National Suppliers.
	  * Passive National Suppliers
	  */
	public int getV_Liability_Acct();

    /** Column name V_LiabilityFgn_Acct */
    public static final String COLUMNNAME_V_LiabilityFgn_Acct = "V_LiabilityFgn_Acct";

	/** Set Passive Foreign Suppliers.
	  * Passive Foreign Suppliers
	  */
	public void setV_LiabilityFgn_Acct (int V_LiabilityFgn_Acct);

	/** Get Passive Foreign Suppliers.
	  * Passive Foreign Suppliers
	  */
	public int getV_LiabilityFgn_Acct();

    /** Column name V_Liability_Services_Acct */
    public static final String COLUMNNAME_V_Liability_Services_Acct = "V_Liability_Services_Acct";

	/** Set Vendor Service Liability.
	  * Account for Vender Service Liability
	  */
	public void setV_Liability_Services_Acct (int V_Liability_Services_Acct);

	/** Get Vendor Service Liability.
	  * Account for Vender Service Liability
	  */
	public int getV_Liability_Services_Acct();

    /** Column name V_Prepayment_Acct */
    public static final String COLUMNNAME_V_Prepayment_Acct = "V_Prepayment_Acct";

	/** Set Advances to National Suppliers.
	  * Advances to National Suppliers
	  */
	public void setV_Prepayment_Acct (int V_Prepayment_Acct);

	/** Get Advances to National Suppliers.
	  * Advances to National Suppliers
	  */
	public int getV_Prepayment_Acct();

    /** Column name V_PrepaymentFgn_Acct */
    public static final String COLUMNNAME_V_PrepaymentFgn_Acct = "V_PrepaymentFgn_Acct";

	/** Set Advances to Foreign Suppliers.
	  * Advances to Foreign Suppliers
	  */
	public void setV_PrepaymentFgn_Acct (int V_PrepaymentFgn_Acct);

	/** Get Advances to Foreign Suppliers.
	  * Advances to Foreign Suppliers
	  */
	public int getV_PrepaymentFgn_Acct();

    /** Column name W_Differences_Acct */
    public static final String COLUMNNAME_W_Differences_Acct = "W_Differences_Acct";

	/** Set Warehouse Differences.
	  * Warehouse Differences Account
	  */
	public void setW_Differences_Acct (int W_Differences_Acct);

	/** Get Warehouse Differences.
	  * Warehouse Differences Account
	  */
	public int getW_Differences_Acct();

    /** Column name W_InvActualAdjust_Acct */
    public static final String COLUMNNAME_W_InvActualAdjust_Acct = "W_InvActualAdjust_Acct";

	/** Set Inventory Adjustment.
	  * Account for Inventory value adjustments for Actual Costing
	  */
	public void setW_InvActualAdjust_Acct (int W_InvActualAdjust_Acct);

	/** Get Inventory Adjustment.
	  * Account for Inventory value adjustments for Actual Costing
	  */
	public int getW_InvActualAdjust_Acct();

    /** Column name W_Inventory_Acct */
    public static final String COLUMNNAME_W_Inventory_Acct = "W_Inventory_Acct";

	/** Set Warehouse Inventory.
	  * Warehouse Inventory Asset Account - Currently not used
	  */
	public void setW_Inventory_Acct (int W_Inventory_Acct);

	/** Get Warehouse Inventory.
	  * Warehouse Inventory Asset Account - Currently not used
	  */
	public int getW_Inventory_Acct();

    /** Column name Withholding_Acct */
    public static final String COLUMNNAME_Withholding_Acct = "Withholding_Acct";

	/** Set Withholding.
	  * Account for Withholdings
	  */
	public void setWithholding_Acct (int Withholding_Acct);

	/** Get Withholding.
	  * Account for Withholdings
	  */
	public int getWithholding_Acct();

    /** Column name W_Revaluation_Acct */
    public static final String COLUMNNAME_W_Revaluation_Acct = "W_Revaluation_Acct";

	/** Set Inventory Revaluation.
	  * Account for Inventory Revaluation
	  */
	public void setW_Revaluation_Acct (int W_Revaluation_Acct);

	/** Get Inventory Revaluation.
	  * Account for Inventory Revaluation
	  */
	public int getW_Revaluation_Acct();

    /** Column name WriteOff_Acct */
    public static final String COLUMNNAME_WriteOff_Acct = "WriteOff_Acct";

	/** Set Write-off.
	  * Account for Receivables write-off
	  */
	public void setWriteOff_Acct (int WriteOff_Acct);

	/** Get Write-off.
	  * Account for Receivables write-off
	  */
	public int getWriteOff_Acct();
}
