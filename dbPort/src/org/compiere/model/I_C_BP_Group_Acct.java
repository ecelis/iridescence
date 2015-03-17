/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_BP_Group_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_BP_Group_Acct 
{

    /** TableName=C_BP_Group_Acct */
    public static final String Table_Name = "C_BP_Group_Acct";

    /** AD_Table_ID=395 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Company Group.
	  * Company Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Company Group.
	  * Company Group
	  */
	public int getC_BP_Group_ID();

	public I_C_BP_Group getC_BP_Group() throws RuntimeException;

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

    /** Column name EXME_Cob_NF_Acct */
    public static final String COLUMNNAME_EXME_Cob_NF_Acct = "EXME_Cob_NF_Acct";

	/** Set Income noninvoiced	  */
	public void setEXME_Cob_NF_Acct (int EXME_Cob_NF_Acct);

	/** Get Income noninvoiced	  */
	public int getEXME_Cob_NF_Acct();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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
