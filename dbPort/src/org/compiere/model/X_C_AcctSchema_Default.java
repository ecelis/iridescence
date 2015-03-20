/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_AcctSchema_Default
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_AcctSchema_Default extends PO implements I_C_AcctSchema_Default, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_AcctSchema_Default (Properties ctx, int C_AcctSchema_Default_ID, String trxName)
    {
      super (ctx, C_AcctSchema_Default_ID, trxName);
      /** if (C_AcctSchema_Default_ID == 0)
        {
			setB_Asset_Acct (0);
			setB_Expense_Acct (0);
			setB_InterestExp_Acct (0);
			setB_InterestRev_Acct (0);
			setB_InTransit_Acct (0);
			setB_PaymentSelect_Acct (0);
			setB_RevaluationGain_Acct (0);
			setB_RevaluationLoss_Acct (0);
			setB_SettlementGain_Acct (0);
			setB_SettlementLoss_Acct (0);
			setB_UnallocatedCash_Acct (0);
			setB_Unidentified_Acct (0);
			setC_AcctSchema_ID (0);
			setCB_Asset_Acct (0);
			setCB_CashTransfer_Acct (0);
			setCB_Differences_Acct (0);
			setCB_Expense_Acct (0);
			setCB_Receipt_Acct (0);
			setCh_Expense_Acct (0);
			setCh_Revenue_Acct (0);
			setC_Prepayment_Acct (0);
			setC_Receivable_Acct (0);
			setC_Receivable_Services_Acct (0);
			setE_Expense_Acct (0);
			setE_Prepayment_Acct (0);
			setNotInvoicedReceipts_Acct (0);
			setNotInvoicedReceivables_Acct (0);
			setNotInvoicedRevenue_Acct (0);
			setP_Asset_Acct (0);
			setPayDiscount_Exp_Acct (0);
			setPayDiscount_Rev_Acct (0);
			setP_Burden_Acct (0);
			setP_COGS_Acct (0);
			setP_CostAdjustment_Acct (0);
			setP_CostOfProduction_Acct (0);
			setP_Expense_Acct (0);
			setP_FloorStock_Acct (0);
			setP_InventoryClearing_Acct (0);
			setP_InvoicePriceVariance_Acct (0);
			setPJ_Asset_Acct (0);
			setPJ_WIP_Acct (0);
			setP_Labor_Acct (0);
			setP_MethodChangeVariance_Acct (0);
			setP_MixVariance_Acct (0);
			setP_OutsideProcessing_Acct (0);
			setP_Overhead_Acct (0);
			setP_PurchasePriceVariance_Acct (0);
			setP_RateVariance_Acct (0);
			setP_Revenue_Acct (0);
			setP_Scrap_Acct (0);
			setP_TradeDiscountGrant_Acct (0);
			setP_TradeDiscountRec_Acct (0);
			setP_UsageVariance_Acct (0);
			setP_WIP_Acct (0);
			setRealizedGain_Acct (0);
			setRealizedLoss_Acct (0);
			setT_Credit_Acct (0);
			setT_Due_Acct (0);
			setT_Expense_Acct (0);
			setT_Liability_Acct (0);
			setT_Receivables_Acct (0);
			setUnEarnedRevenue_Acct (0);
			setUnrealizedGain_Acct (0);
			setUnrealizedLoss_Acct (0);
			setV_Liability_Acct (0);
			setV_Liability_Services_Acct (0);
			setV_Prepayment_Acct (0);
			setW_Differences_Acct (0);
			setW_InvActualAdjust_Acct (0);
			setW_Inventory_Acct (0);
			setWithholding_Acct (0);
			setW_Revaluation_Acct (0);
			setWriteOff_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_C_AcctSchema_Default (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_C_AcctSchema_Default[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set National Sundry Creditors.
		@param A_Creditors_Acct 
		National Sundry Creditors
	  */
	public void setA_Creditors_Acct (int A_Creditors_Acct)
	{
		set_Value (COLUMNNAME_A_Creditors_Acct, Integer.valueOf(A_Creditors_Acct));
	}

	/** Get National Sundry Creditors.
		@return National Sundry Creditors
	  */
	public int getA_Creditors_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Creditors_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sundry Creditors Foreigners.
		@param A_CreditorsFgn_Acct 
		Sundry Creditors Foreigners
	  */
	public void setA_CreditorsFgn_Acct (int A_CreditorsFgn_Acct)
	{
		set_Value (COLUMNNAME_A_CreditorsFgn_Acct, Integer.valueOf(A_CreditorsFgn_Acct));
	}

	/** Get Sundry Creditors Foreigners.
		@return Sundry Creditors Foreigners
	  */
	public int getA_CreditorsFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_CreditorsFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Asset.
		@param B_Asset_Acct 
		Bank Asset Account
	  */
	public void setB_Asset_Acct (int B_Asset_Acct)
	{
		set_Value (COLUMNNAME_B_Asset_Acct, Integer.valueOf(B_Asset_Acct));
	}

	/** Get Bank Asset.
		@return Bank Asset Account
	  */
	public int getB_Asset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_Asset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Foreign Banks.
		@param B_AssetFgn_Acct 
		Foreign Banks
	  */
	public void setB_AssetFgn_Acct (int B_AssetFgn_Acct)
	{
		set_Value (COLUMNNAME_B_AssetFgn_Acct, Integer.valueOf(B_AssetFgn_Acct));
	}

	/** Get Foreign Banks.
		@return Foreign Banks
	  */
	public int getB_AssetFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_AssetFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Expense.
		@param B_Expense_Acct 
		Bank Expense Account
	  */
	public void setB_Expense_Acct (int B_Expense_Acct)
	{
		set_Value (COLUMNNAME_B_Expense_Acct, Integer.valueOf(B_Expense_Acct));
	}

	/** Get Bank Expense.
		@return Bank Expense Account
	  */
	public int getB_Expense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_Expense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Interest Expense.
		@param B_InterestExp_Acct 
		Bank Interest Expense Account
	  */
	public void setB_InterestExp_Acct (int B_InterestExp_Acct)
	{
		set_Value (COLUMNNAME_B_InterestExp_Acct, Integer.valueOf(B_InterestExp_Acct));
	}

	/** Get Bank Interest Expense.
		@return Bank Interest Expense Account
	  */
	public int getB_InterestExp_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_InterestExp_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Interest Revenue.
		@param B_InterestRev_Acct 
		Bank Interest Revenue Account
	  */
	public void setB_InterestRev_Acct (int B_InterestRev_Acct)
	{
		set_Value (COLUMNNAME_B_InterestRev_Acct, Integer.valueOf(B_InterestRev_Acct));
	}

	/** Get Bank Interest Revenue.
		@return Bank Interest Revenue Account
	  */
	public int getB_InterestRev_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_InterestRev_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank In Transit.
		@param B_InTransit_Acct 
		Bank In Transit Account
	  */
	public void setB_InTransit_Acct (int B_InTransit_Acct)
	{
		set_Value (COLUMNNAME_B_InTransit_Acct, Integer.valueOf(B_InTransit_Acct));
	}

	/** Get Bank In Transit.
		@return Bank In Transit Account
	  */
	public int getB_InTransit_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_InTransit_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Foreign Banks In Transit.
		@param B_InTransitFgn_Acct 
		Foreign Banks In Transit
	  */
	public void setB_InTransitFgn_Acct (int B_InTransitFgn_Acct)
	{
		set_Value (COLUMNNAME_B_InTransitFgn_Acct, Integer.valueOf(B_InTransitFgn_Acct));
	}

	/** Get Foreign Banks In Transit.
		@return Foreign Banks In Transit
	  */
	public int getB_InTransitFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_InTransitFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payments.
		@param B_PaymentSelect_Acct 
		AP Payment Selection Clearing Account
	  */
	public void setB_PaymentSelect_Acct (int B_PaymentSelect_Acct)
	{
		set_Value (COLUMNNAME_B_PaymentSelect_Acct, Integer.valueOf(B_PaymentSelect_Acct));
	}

	/** Get Payments.
		@return AP Payment Selection Clearing Account
	  */
	public int getB_PaymentSelect_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_PaymentSelect_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Fluctuation Gain.
		@param B_RevaluationGain_Acct 
		Bank Fluctuation Gain
	  */
	public void setB_RevaluationGain_Acct (int B_RevaluationGain_Acct)
	{
		set_Value (COLUMNNAME_B_RevaluationGain_Acct, Integer.valueOf(B_RevaluationGain_Acct));
	}

	/** Get Bank Fluctuation Gain.
		@return Bank Fluctuation Gain
	  */
	public int getB_RevaluationGain_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_RevaluationGain_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Fluctuation Loss.
		@param B_RevaluationLoss_Acct 
		Bank Fluctuation Loss
	  */
	public void setB_RevaluationLoss_Acct (int B_RevaluationLoss_Acct)
	{
		set_Value (COLUMNNAME_B_RevaluationLoss_Acct, Integer.valueOf(B_RevaluationLoss_Acct));
	}

	/** Get Bank Fluctuation Loss.
		@return Bank Fluctuation Loss
	  */
	public int getB_RevaluationLoss_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_RevaluationLoss_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Settlement Gain.
		@param B_SettlementGain_Acct 
		Bank Settlement Gain Account
	  */
	public void setB_SettlementGain_Acct (int B_SettlementGain_Acct)
	{
		set_Value (COLUMNNAME_B_SettlementGain_Acct, Integer.valueOf(B_SettlementGain_Acct));
	}

	/** Get Bank Settlement Gain.
		@return Bank Settlement Gain Account
	  */
	public int getB_SettlementGain_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_SettlementGain_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Settlement Loss.
		@param B_SettlementLoss_Acct 
		Bank Settlement Loss Account
	  */
	public void setB_SettlementLoss_Acct (int B_SettlementLoss_Acct)
	{
		set_Value (COLUMNNAME_B_SettlementLoss_Acct, Integer.valueOf(B_SettlementLoss_Acct));
	}

	/** Get Bank Settlement Loss.
		@return Bank Settlement Loss Account
	  */
	public int getB_SettlementLoss_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_SettlementLoss_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unallocated Cash.
		@param B_UnallocatedCash_Acct 
		Unallocated Cash Clearing Account
	  */
	public void setB_UnallocatedCash_Acct (int B_UnallocatedCash_Acct)
	{
		set_Value (COLUMNNAME_B_UnallocatedCash_Acct, Integer.valueOf(B_UnallocatedCash_Acct));
	}

	/** Get Unallocated Cash.
		@return Unallocated Cash Clearing Account
	  */
	public int getB_UnallocatedCash_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_UnallocatedCash_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Bank Unidentified Receipts.
		@param B_Unidentified_Acct 
		Bank Unidentified Receipts Account
	  */
	public void setB_Unidentified_Acct (int B_Unidentified_Acct)
	{
		set_Value (COLUMNNAME_B_Unidentified_Acct, Integer.valueOf(B_Unidentified_Acct));
	}

	/** Get Bank Unidentified Receipts.
		@return Bank Unidentified Receipts Account
	  */
	public int getB_Unidentified_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_B_Unidentified_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_AcctSchema.Table_Name);
        I_C_AcctSchema result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_AcctSchema)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_AcctSchema_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1)
			 throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_AcctSchema_ID()));
    }

	/** Set Cash Book.
		@param CB_Asset_Acct 
		Cash Book
	  */
	public void setCB_Asset_Acct (int CB_Asset_Acct)
	{
		set_Value (COLUMNNAME_CB_Asset_Acct, Integer.valueOf(CB_Asset_Acct));
	}

	/** Get Cash Book.
		@return Cash Book
	  */
	public int getCB_Asset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CB_Asset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cash Transfer.
		@param CB_CashTransfer_Acct 
		Cash Transfer Clearing Account
	  */
	public void setCB_CashTransfer_Acct (int CB_CashTransfer_Acct)
	{
		set_Value (COLUMNNAME_CB_CashTransfer_Acct, Integer.valueOf(CB_CashTransfer_Acct));
	}

	/** Get Cash Transfer.
		@return Cash Transfer Clearing Account
	  */
	public int getCB_CashTransfer_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CB_CashTransfer_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cash Book Differences .
		@param CB_Differences_Acct 
		Cash Book Differences Account
	  */
	public void setCB_Differences_Acct (int CB_Differences_Acct)
	{
		set_Value (COLUMNNAME_CB_Differences_Acct, Integer.valueOf(CB_Differences_Acct));
	}

	/** Get Cash Book Differences .
		@return Cash Book Differences Account
	  */
	public int getCB_Differences_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CB_Differences_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cash Book Expense.
		@param CB_Expense_Acct 
		Cash Book Expense Account
	  */
	public void setCB_Expense_Acct (int CB_Expense_Acct)
	{
		set_Value (COLUMNNAME_CB_Expense_Acct, Integer.valueOf(CB_Expense_Acct));
	}

	/** Get Cash Book Expense.
		@return Cash Book Expense Account
	  */
	public int getCB_Expense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CB_Expense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cash Book Receipt.
		@param CB_Receipt_Acct 
		Cash Book Receipts Account
	  */
	public void setCB_Receipt_Acct (int CB_Receipt_Acct)
	{
		set_Value (COLUMNNAME_CB_Receipt_Acct, Integer.valueOf(CB_Receipt_Acct));
	}

	/** Get Cash Book Receipt.
		@return Cash Book Receipts Account
	  */
	public int getCB_Receipt_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CB_Receipt_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Charge Expense.
		@param Ch_Expense_Acct 
		Charge Expense Account
	  */
	public void setCh_Expense_Acct (int Ch_Expense_Acct)
	{
		set_Value (COLUMNNAME_Ch_Expense_Acct, Integer.valueOf(Ch_Expense_Acct));
	}

	/** Get Charge Expense.
		@return Charge Expense Account
	  */
	public int getCh_Expense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ch_Expense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Charge Revenue.
		@param Ch_Revenue_Acct 
		Charge Revenue Account
	  */
	public void setCh_Revenue_Acct (int Ch_Revenue_Acct)
	{
		set_Value (COLUMNNAME_Ch_Revenue_Acct, Integer.valueOf(Ch_Revenue_Acct));
	}

	/** Get Charge Revenue.
		@return Charge Revenue Account
	  */
	public int getCh_Revenue_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ch_Revenue_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Advances to National Customers.
		@param C_Prepayment_Acct 
		Advances to National Customers
	  */
	public void setC_Prepayment_Acct (int C_Prepayment_Acct)
	{
		set_Value (COLUMNNAME_C_Prepayment_Acct, Integer.valueOf(C_Prepayment_Acct));
	}

	/** Get Advances to National Customers.
		@return Advances to National Customers
	  */
	public int getC_Prepayment_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Prepayment_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Advances to Foreign Customers.
		@param C_PrepaymentFgn_Acct 
		Advances to Foreign Customers
	  */
	public void setC_PrepaymentFgn_Acct (int C_PrepaymentFgn_Acct)
	{
		set_Value (COLUMNNAME_C_PrepaymentFgn_Acct, Integer.valueOf(C_PrepaymentFgn_Acct));
	}

	/** Get Advances to Foreign Customers.
		@return Advances to Foreign Customers
	  */
	public int getC_PrepaymentFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PrepaymentFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set National customer.
		@param C_Receivable_Acct 
		National customer
	  */
	public void setC_Receivable_Acct (int C_Receivable_Acct)
	{
		set_Value (COLUMNNAME_C_Receivable_Acct, Integer.valueOf(C_Receivable_Acct));
	}

	/** Get National customer.
		@return National customer
	  */
	public int getC_Receivable_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Receivable_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Foreign Clients.
		@param C_ReceivableFgn_Acct 
		Foreign Clients
	  */
	public void setC_ReceivableFgn_Acct (int C_ReceivableFgn_Acct)
	{
		set_Value (COLUMNNAME_C_ReceivableFgn_Acct, Integer.valueOf(C_ReceivableFgn_Acct));
	}

	/** Get Foreign Clients.
		@return Foreign Clients
	  */
	public int getC_ReceivableFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ReceivableFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Receivable Services.
		@param C_Receivable_Services_Acct 
		Customer Accounts Receivables Services Account
	  */
	public void setC_Receivable_Services_Acct (int C_Receivable_Services_Acct)
	{
		set_Value (COLUMNNAME_C_Receivable_Services_Acct, Integer.valueOf(C_Receivable_Services_Acct));
	}

	/** Get Receivable Services.
		@return Customer Accounts Receivables Services Account
	  */
	public int getC_Receivable_Services_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Receivable_Services_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Employee Expense.
		@param E_Expense_Acct 
		Account for Employee Expenses
	  */
	public void setE_Expense_Acct (int E_Expense_Acct)
	{
		set_Value (COLUMNNAME_E_Expense_Acct, Integer.valueOf(E_Expense_Acct));
	}

	/** Get Employee Expense.
		@return Account for Employee Expenses
	  */
	public int getE_Expense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_E_Expense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Employee Prepayment.
		@param E_Prepayment_Acct 
		Account for Employee Expense Prepayments
	  */
	public void setE_Prepayment_Acct (int E_Prepayment_Acct)
	{
		set_Value (COLUMNNAME_E_Prepayment_Acct, Integer.valueOf(E_Prepayment_Acct));
	}

	/** Get Employee Prepayment.
		@return Account for Employee Expense Prepayments
	  */
	public int getE_Prepayment_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_E_Prepayment_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Income noninvoiced.
		@param EXME_Cob_NF_Acct Income noninvoiced	  */
	public void setEXME_Cob_NF_Acct (int EXME_Cob_NF_Acct)
	{
		set_Value (COLUMNNAME_EXME_Cob_NF_Acct, Integer.valueOf(EXME_Cob_NF_Acct));
	}

	/** Get Income noninvoiced.
		@return Income noninvoiced	  */
	public int getEXME_Cob_NF_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cob_NF_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Provision of Sale.
		@param EXME_Prov_Vta_Acct Provision of Sale	  */
	public void setEXME_Prov_Vta_Acct (int EXME_Prov_Vta_Acct)
	{
		set_Value (COLUMNNAME_EXME_Prov_Vta_Acct, Integer.valueOf(EXME_Prov_Vta_Acct));
	}

	/** Get Provision of Sale.
		@return Provision of Sale	  */
	public int getEXME_Prov_Vta_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Prov_Vta_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Receiving to Warehouse Pending to Invoice.
		@param NotInvoicedReceipts_Acct 
		Receiving to Warehouse Pending to Invoice
	  */
	public void setNotInvoicedReceipts_Acct (int NotInvoicedReceipts_Acct)
	{
		set_Value (COLUMNNAME_NotInvoicedReceipts_Acct, Integer.valueOf(NotInvoicedReceipts_Acct));
	}

	/** Get Receiving to Warehouse Pending to Invoice.
		@return Receiving to Warehouse Pending to Invoice
	  */
	public int getNotInvoicedReceipts_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NotInvoicedReceipts_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Not-invoiced Receivables.
		@param NotInvoicedReceivables_Acct 
		Account for not invoiced Receivables
	  */
	public void setNotInvoicedReceivables_Acct (int NotInvoicedReceivables_Acct)
	{
		set_Value (COLUMNNAME_NotInvoicedReceivables_Acct, Integer.valueOf(NotInvoicedReceivables_Acct));
	}

	/** Get Not-invoiced Receivables.
		@return Account for not invoiced Receivables
	  */
	public int getNotInvoicedReceivables_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NotInvoicedReceivables_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Not-invoiced Revenue.
		@param NotInvoicedRevenue_Acct 
		Account for not invoiced Revenue
	  */
	public void setNotInvoicedRevenue_Acct (int NotInvoicedRevenue_Acct)
	{
		set_Value (COLUMNNAME_NotInvoicedRevenue_Acct, Integer.valueOf(NotInvoicedRevenue_Acct));
	}

	/** Get Not-invoiced Revenue.
		@return Account for not invoiced Revenue
	  */
	public int getNotInvoicedRevenue_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NotInvoicedRevenue_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Asset.
		@param P_Asset_Acct 
		Account for Product Asset (Inventory)
	  */
	public void setP_Asset_Acct (int P_Asset_Acct)
	{
		set_Value (COLUMNNAME_P_Asset_Acct, Integer.valueOf(P_Asset_Acct));
	}

	/** Get Product Asset.
		@return Account for Product Asset (Inventory)
	  */
	public int getP_Asset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_Asset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment Discount Expense.
		@param PayDiscount_Exp_Acct 
		Payment Discount Expense Account
	  */
	public void setPayDiscount_Exp_Acct (int PayDiscount_Exp_Acct)
	{
		set_Value (COLUMNNAME_PayDiscount_Exp_Acct, Integer.valueOf(PayDiscount_Exp_Acct));
	}

	/** Get Payment Discount Expense.
		@return Payment Discount Expense Account
	  */
	public int getPayDiscount_Exp_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PayDiscount_Exp_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment Discount Revenue.
		@param PayDiscount_Rev_Acct 
		Payment Discount Revenue Account
	  */
	public void setPayDiscount_Rev_Acct (int PayDiscount_Rev_Acct)
	{
		set_Value (COLUMNNAME_PayDiscount_Rev_Acct, Integer.valueOf(PayDiscount_Rev_Acct));
	}

	/** Get Payment Discount Revenue.
		@return Payment Discount Revenue Account
	  */
	public int getPayDiscount_Rev_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PayDiscount_Rev_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Burden.
		@param P_Burden_Acct 
		The Burden account is the account used Manufacturing Order
	  */
	public void setP_Burden_Acct (int P_Burden_Acct)
	{
		set_Value (COLUMNNAME_P_Burden_Acct, Integer.valueOf(P_Burden_Acct));
	}

	/** Get Burden.
		@return The Burden account is the account used Manufacturing Order
	  */
	public int getP_Burden_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_Burden_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product COGS.
		@param P_COGS_Acct 
		Account for Cost of Goods Sold
	  */
	public void setP_COGS_Acct (int P_COGS_Acct)
	{
		set_Value (COLUMNNAME_P_COGS_Acct, Integer.valueOf(P_COGS_Acct));
	}

	/** Get Product COGS.
		@return Account for Cost of Goods Sold
	  */
	public int getP_COGS_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_COGS_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cost Adjustment.
		@param P_CostAdjustment_Acct 
		Product Cost Adjustment Account
	  */
	public void setP_CostAdjustment_Acct (int P_CostAdjustment_Acct)
	{
		set_Value (COLUMNNAME_P_CostAdjustment_Acct, Integer.valueOf(P_CostAdjustment_Acct));
	}

	/** Get Cost Adjustment.
		@return Product Cost Adjustment Account
	  */
	public int getP_CostAdjustment_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_CostAdjustment_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cost Of Production.
		@param P_CostOfProduction_Acct 
		The Cost Of Production account is the account used Manufacturing Order
	  */
	public void setP_CostOfProduction_Acct (int P_CostOfProduction_Acct)
	{
		set_Value (COLUMNNAME_P_CostOfProduction_Acct, Integer.valueOf(P_CostOfProduction_Acct));
	}

	/** Get Cost Of Production.
		@return The Cost Of Production account is the account used Manufacturing Order
	  */
	public int getP_CostOfProduction_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_CostOfProduction_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Expenses for National Services.
		@param P_Expense_Acct 
		Expenses for National Services
	  */
	public void setP_Expense_Acct (int P_Expense_Acct)
	{
		set_Value (COLUMNNAME_P_Expense_Acct, Integer.valueOf(P_Expense_Acct));
	}

	/** Get Expenses for National Services.
		@return Expenses for National Services
	  */
	public int getP_Expense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_Expense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Expenses for Foreign Services.
		@param P_ExpenseFgn_Acct 
		Expenses for Foreign Services
	  */
	public void setP_ExpenseFgn_Acct (int P_ExpenseFgn_Acct)
	{
		set_Value (COLUMNNAME_P_ExpenseFgn_Acct, Integer.valueOf(P_ExpenseFgn_Acct));
	}

	/** Get Expenses for Foreign Services.
		@return Expenses for Foreign Services
	  */
	public int getP_ExpenseFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_ExpenseFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Floor Stock.
		@param P_FloorStock_Acct 
		The Floor Stock account is the account used Manufacturing Order
	  */
	public void setP_FloorStock_Acct (int P_FloorStock_Acct)
	{
		set_Value (COLUMNNAME_P_FloorStock_Acct, Integer.valueOf(P_FloorStock_Acct));
	}

	/** Get Floor Stock.
		@return The Floor Stock account is the account used Manufacturing Order
	  */
	public int getP_FloorStock_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_FloorStock_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set National Shopping.
		@param P_InventoryClearing_Acct 
		National Shopping
	  */
	public void setP_InventoryClearing_Acct (int P_InventoryClearing_Acct)
	{
		set_Value (COLUMNNAME_P_InventoryClearing_Acct, Integer.valueOf(P_InventoryClearing_Acct));
	}

	/** Get National Shopping.
		@return National Shopping
	  */
	public int getP_InventoryClearing_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_InventoryClearing_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Foreign Shopping.
		@param P_InventoryClearingFgn_Acct 
		Foreign Shopping
	  */
	public void setP_InventoryClearingFgn_Acct (int P_InventoryClearingFgn_Acct)
	{
		set_Value (COLUMNNAME_P_InventoryClearingFgn_Acct, Integer.valueOf(P_InventoryClearingFgn_Acct));
	}

	/** Get Foreign Shopping.
		@return Foreign Shopping
	  */
	public int getP_InventoryClearingFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_InventoryClearingFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Invoice Price Variance.
		@param P_InvoicePriceVariance_Acct 
		Difference between Costs and Invoice Price (IPV)
	  */
	public void setP_InvoicePriceVariance_Acct (int P_InvoicePriceVariance_Acct)
	{
		set_Value (COLUMNNAME_P_InvoicePriceVariance_Acct, Integer.valueOf(P_InvoicePriceVariance_Acct));
	}

	/** Get Invoice Price Variance.
		@return Difference between Costs and Invoice Price (IPV)
	  */
	public int getP_InvoicePriceVariance_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_InvoicePriceVariance_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Project Asset.
		@param PJ_Asset_Acct 
		Project Asset Account
	  */
	public void setPJ_Asset_Acct (int PJ_Asset_Acct)
	{
		set_Value (COLUMNNAME_PJ_Asset_Acct, Integer.valueOf(PJ_Asset_Acct));
	}

	/** Get Project Asset.
		@return Project Asset Account
	  */
	public int getPJ_Asset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PJ_Asset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Work In Progress.
		@param PJ_WIP_Acct 
		Account for Work in Progress
	  */
	public void setPJ_WIP_Acct (int PJ_WIP_Acct)
	{
		set_Value (COLUMNNAME_PJ_WIP_Acct, Integer.valueOf(PJ_WIP_Acct));
	}

	/** Get Work In Progress.
		@return Account for Work in Progress
	  */
	public int getPJ_WIP_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PJ_WIP_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Labor.
		@param P_Labor_Acct 
		The Labor account is the account used Manufacturing Order
	  */
	public void setP_Labor_Acct (int P_Labor_Acct)
	{
		set_Value (COLUMNNAME_P_Labor_Acct, Integer.valueOf(P_Labor_Acct));
	}

	/** Get Labor.
		@return The Labor account is the account used Manufacturing Order
	  */
	public int getP_Labor_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_Labor_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Method Change Variance.
		@param P_MethodChangeVariance_Acct 
		The Method Change Variance account is the account used Manufacturing Order
	  */
	public void setP_MethodChangeVariance_Acct (int P_MethodChangeVariance_Acct)
	{
		set_Value (COLUMNNAME_P_MethodChangeVariance_Acct, Integer.valueOf(P_MethodChangeVariance_Acct));
	}

	/** Get Method Change Variance.
		@return The Method Change Variance account is the account used Manufacturing Order
	  */
	public int getP_MethodChangeVariance_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_MethodChangeVariance_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Mix Variance.
		@param P_MixVariance_Acct 
		The Mix Variance account is the account used Manufacturing Order
	  */
	public void setP_MixVariance_Acct (int P_MixVariance_Acct)
	{
		set_Value (COLUMNNAME_P_MixVariance_Acct, Integer.valueOf(P_MixVariance_Acct));
	}

	/** Get Mix Variance.
		@return The Mix Variance account is the account used Manufacturing Order
	  */
	public int getP_MixVariance_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_MixVariance_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Outside Processing.
		@param P_OutsideProcessing_Acct 
		The Outside Processing Account is the account used in Manufacturing Order
	  */
	public void setP_OutsideProcessing_Acct (int P_OutsideProcessing_Acct)
	{
		set_Value (COLUMNNAME_P_OutsideProcessing_Acct, Integer.valueOf(P_OutsideProcessing_Acct));
	}

	/** Get Outside Processing.
		@return The Outside Processing Account is the account used in Manufacturing Order
	  */
	public int getP_OutsideProcessing_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_OutsideProcessing_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Overhead.
		@param P_Overhead_Acct 
		The Overhead account is the account used  in Manufacturing Order 
	  */
	public void setP_Overhead_Acct (int P_Overhead_Acct)
	{
		set_Value (COLUMNNAME_P_Overhead_Acct, Integer.valueOf(P_Overhead_Acct));
	}

	/** Get Overhead.
		@return The Overhead account is the account used  in Manufacturing Order 
	  */
	public int getP_Overhead_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_Overhead_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Purchase Price Variance.
		@param P_PurchasePriceVariance_Acct 
		Difference between Standard Cost and Purchase Price (PPV)
	  */
	public void setP_PurchasePriceVariance_Acct (int P_PurchasePriceVariance_Acct)
	{
		set_Value (COLUMNNAME_P_PurchasePriceVariance_Acct, Integer.valueOf(P_PurchasePriceVariance_Acct));
	}

	/** Get Purchase Price Variance.
		@return Difference between Standard Cost and Purchase Price (PPV)
	  */
	public int getP_PurchasePriceVariance_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_PurchasePriceVariance_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Purchase Returns.
		@param P_PurchasesReturns_Acct 
		Purchase Returns
	  */
	public void setP_PurchasesReturns_Acct (int P_PurchasesReturns_Acct)
	{
		set_Value (COLUMNNAME_P_PurchasesReturns_Acct, Integer.valueOf(P_PurchasesReturns_Acct));
	}

	/** Get Purchase Returns.
		@return Purchase Returns
	  */
	public int getP_PurchasesReturns_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_PurchasesReturns_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rate Variance.
		@param P_RateVariance_Acct 
		The Rate Variance account is the account used Manufacturing Order
	  */
	public void setP_RateVariance_Acct (int P_RateVariance_Acct)
	{
		set_Value (COLUMNNAME_P_RateVariance_Acct, Integer.valueOf(P_RateVariance_Acct));
	}

	/** Get Rate Variance.
		@return The Rate Variance account is the account used Manufacturing Order
	  */
	public int getP_RateVariance_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_RateVariance_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Revenue.
		@param P_Revenue_Acct 
		Account for Product Revenue (Sales Account)
	  */
	public void setP_Revenue_Acct (int P_Revenue_Acct)
	{
		set_Value (COLUMNNAME_P_Revenue_Acct, Integer.valueOf(P_Revenue_Acct));
	}

	/** Get Product Revenue.
		@return Account for Product Revenue (Sales Account)
	  */
	public int getP_Revenue_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_Revenue_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Abroad.
		@param P_RevenueFgn_Acct 
		Sales Abroad
	  */
	public void setP_RevenueFgn_Acct (int P_RevenueFgn_Acct)
	{
		set_Value (COLUMNNAME_P_RevenueFgn_Acct, Integer.valueOf(P_RevenueFgn_Acct));
	}

	/** Get Sales Abroad.
		@return Sales Abroad
	  */
	public int getP_RevenueFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_RevenueFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exempt Sales counted.
		@param P_RevenueTECash_Acct 
		Exempt Sales counted
	  */
	public void setP_RevenueTECash_Acct (int P_RevenueTECash_Acct)
	{
		set_Value (COLUMNNAME_P_RevenueTECash_Acct, Integer.valueOf(P_RevenueTECash_Acct));
	}

	/** Get Exempt Sales counted.
		@return Exempt Sales counted
	  */
	public int getP_RevenueTECash_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_RevenueTECash_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exempt Sales Credit.
		@param P_RevenueTECredit_Acct 
		Exempt Sales Credit
	  */
	public void setP_RevenueTECredit_Acct (int P_RevenueTECredit_Acct)
	{
		set_Value (COLUMNNAME_P_RevenueTECredit_Acct, Integer.valueOf(P_RevenueTECredit_Acct));
	}

	/** Get Exempt Sales Credit.
		@return Exempt Sales Credit
	  */
	public int getP_RevenueTECredit_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_RevenueTECredit_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Taxable sales by counting in general rate.
		@param P_RevenueTGCash_Acct 
		Taxable sales by counting in general rate
	  */
	public void setP_RevenueTGCash_Acct (int P_RevenueTGCash_Acct)
	{
		set_Value (COLUMNNAME_P_RevenueTGCash_Acct, Integer.valueOf(P_RevenueTGCash_Acct));
	}

	/** Get Taxable sales by counting in general rate.
		@return Taxable sales by counting in general rate
	  */
	public int getP_RevenueTGCash_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_RevenueTGCash_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Taxable sales tax credit generally.
		@param P_RevenueTGCredit_Acct 
		Taxable sales tax credit generally
	  */
	public void setP_RevenueTGCredit_Acct (int P_RevenueTGCredit_Acct)
	{
		set_Value (COLUMNNAME_P_RevenueTGCredit_Acct, Integer.valueOf(P_RevenueTGCredit_Acct));
	}

	/** Get Taxable sales tax credit generally.
		@return Taxable sales tax credit generally
	  */
	public int getP_RevenueTGCredit_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_RevenueTGCredit_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Taxable Sales 0 % cash.
		@param P_RevenueTZCash_Acct 
		Taxable Sales 0 % cash
	  */
	public void setP_RevenueTZCash_Acct (int P_RevenueTZCash_Acct)
	{
		set_Value (COLUMNNAME_P_RevenueTZCash_Acct, Integer.valueOf(P_RevenueTZCash_Acct));
	}

	/** Get Taxable Sales 0 % cash.
		@return Taxable Sales 0 % cash
	  */
	public int getP_RevenueTZCash_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_RevenueTZCash_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Taxable Sales 0 % credit.
		@param P_RevenueTZCredit_Acct 
		Taxable Sales 0 % credit
	  */
	public void setP_RevenueTZCredit_Acct (int P_RevenueTZCredit_Acct)
	{
		set_Value (COLUMNNAME_P_RevenueTZCredit_Acct, Integer.valueOf(P_RevenueTZCredit_Acct));
	}

	/** Get Taxable Sales 0 % credit.
		@return Taxable Sales 0 % credit
	  */
	public int getP_RevenueTZCredit_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_RevenueTZCredit_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sales Returns.
		@param P_SalesReturns_Acct 
		Sales Returns
	  */
	public void setP_SalesReturns_Acct (int P_SalesReturns_Acct)
	{
		set_Value (COLUMNNAME_P_SalesReturns_Acct, Integer.valueOf(P_SalesReturns_Acct));
	}

	/** Get Sales Returns.
		@return Sales Returns
	  */
	public int getP_SalesReturns_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_SalesReturns_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Returns on exempt Sales.
		@param P_SalesReturnsE_Acct 
		Returns on exempt Sales
	  */
	public void setP_SalesReturnsE_Acct (int P_SalesReturnsE_Acct)
	{
		set_Value (COLUMNNAME_P_SalesReturnsE_Acct, Integer.valueOf(P_SalesReturnsE_Acct));
	}

	/** Get Returns on exempt Sales.
		@return Returns on exempt Sales
	  */
	public int getP_SalesReturnsE_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_SalesReturnsE_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Returns sales Overall Rate.
		@param P_SalesReturnsG_Acct 
		Returns sales Overall Rate
	  */
	public void setP_SalesReturnsG_Acct (int P_SalesReturnsG_Acct)
	{
		set_Value (COLUMNNAME_P_SalesReturnsG_Acct, Integer.valueOf(P_SalesReturnsG_Acct));
	}

	/** Get Returns sales Overall Rate.
		@return Returns sales Overall Rate
	  */
	public int getP_SalesReturnsG_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_SalesReturnsG_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Returns Sales 0%.
		@param P_SalesReturnsZ_Acct 
		Returns Sales 0%
	  */
	public void setP_SalesReturnsZ_Acct (int P_SalesReturnsZ_Acct)
	{
		set_Value (COLUMNNAME_P_SalesReturnsZ_Acct, Integer.valueOf(P_SalesReturnsZ_Acct));
	}

	/** Get Returns Sales 0%.
		@return Returns Sales 0%
	  */
	public int getP_SalesReturnsZ_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_SalesReturnsZ_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Scrap.
		@param P_Scrap_Acct 
		The Scrap account is the account used  in Manufacturing Order 
	  */
	public void setP_Scrap_Acct (int P_Scrap_Acct)
	{
		set_Value (COLUMNNAME_P_Scrap_Acct, Integer.valueOf(P_Scrap_Acct));
	}

	/** Get Scrap.
		@return The Scrap account is the account used  in Manufacturing Order 
	  */
	public int getP_Scrap_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_Scrap_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trade Discount Granted.
		@param P_TradeDiscountGrant_Acct 
		Trade Discount Granted Account
	  */
	public void setP_TradeDiscountGrant_Acct (int P_TradeDiscountGrant_Acct)
	{
		set_Value (COLUMNNAME_P_TradeDiscountGrant_Acct, Integer.valueOf(P_TradeDiscountGrant_Acct));
	}

	/** Get Trade Discount Granted.
		@return Trade Discount Granted Account
	  */
	public int getP_TradeDiscountGrant_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_TradeDiscountGrant_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Returns , Discounts and Rebates on exempt sales.
		@param P_TradeDiscountGrantE_Acct 
		Returns , Discounts and Rebates on exempt sales
	  */
	public void setP_TradeDiscountGrantE_Acct (int P_TradeDiscountGrantE_Acct)
	{
		set_Value (COLUMNNAME_P_TradeDiscountGrantE_Acct, Integer.valueOf(P_TradeDiscountGrantE_Acct));
	}

	/** Get Returns , Discounts and Rebates on exempt sales.
		@return Returns , Discounts and Rebates on exempt sales
	  */
	public int getP_TradeDiscountGrantE_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_TradeDiscountGrantE_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Returns , Discounts and Sales on Sales overall rate.
		@param P_TradeDiscountGrantG_Acct 
		Returns , Discounts and Sales on Sales overall rate
	  */
	public void setP_TradeDiscountGrantG_Acct (int P_TradeDiscountGrantG_Acct)
	{
		set_Value (COLUMNNAME_P_TradeDiscountGrantG_Acct, Integer.valueOf(P_TradeDiscountGrantG_Acct));
	}

	/** Get Returns , Discounts and Sales on Sales overall rate.
		@return Returns , Discounts and Sales on Sales overall rate
	  */
	public int getP_TradeDiscountGrantG_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_TradeDiscountGrantG_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Returns , Discounts and Sales on Sales 0 %.
		@param P_TradeDiscountGrantZ_Acct 
		Returns , Discounts and Sales on Sales 0 %
	  */
	public void setP_TradeDiscountGrantZ_Acct (int P_TradeDiscountGrantZ_Acct)
	{
		set_Value (COLUMNNAME_P_TradeDiscountGrantZ_Acct, Integer.valueOf(P_TradeDiscountGrantZ_Acct));
	}

	/** Get Returns , Discounts and Sales on Sales 0 %.
		@return Returns , Discounts and Sales on Sales 0 %
	  */
	public int getP_TradeDiscountGrantZ_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_TradeDiscountGrantZ_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trade Discount Received.
		@param P_TradeDiscountRec_Acct 
		Trade Discount Receivable Account
	  */
	public void setP_TradeDiscountRec_Acct (int P_TradeDiscountRec_Acct)
	{
		set_Value (COLUMNNAME_P_TradeDiscountRec_Acct, Integer.valueOf(P_TradeDiscountRec_Acct));
	}

	/** Get Trade Discount Received.
		@return Trade Discount Receivable Account
	  */
	public int getP_TradeDiscountRec_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_TradeDiscountRec_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Usage Variance.
		@param P_UsageVariance_Acct 
		The Usage Variance account is the account used Manufacturing Order
	  */
	public void setP_UsageVariance_Acct (int P_UsageVariance_Acct)
	{
		set_Value (COLUMNNAME_P_UsageVariance_Acct, Integer.valueOf(P_UsageVariance_Acct));
	}

	/** Get Usage Variance.
		@return The Usage Variance account is the account used Manufacturing Order
	  */
	public int getP_UsageVariance_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_UsageVariance_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Work In Process.
		@param P_WIP_Acct 
		The Work in Process account is the account used Manufacturing Order
	  */
	public void setP_WIP_Acct (int P_WIP_Acct)
	{
		set_Value (COLUMNNAME_P_WIP_Acct, Integer.valueOf(P_WIP_Acct));
	}

	/** Get Work In Process.
		@return The Work in Process account is the account used Manufacturing Order
	  */
	public int getP_WIP_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_WIP_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Realized Gain Acct.
		@param RealizedGain_Acct 
		Realized Gain Account
	  */
	public void setRealizedGain_Acct (int RealizedGain_Acct)
	{
		set_Value (COLUMNNAME_RealizedGain_Acct, Integer.valueOf(RealizedGain_Acct));
	}

	/** Get Realized Gain Acct.
		@return Realized Gain Account
	  */
	public int getRealizedGain_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RealizedGain_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Realized Loss Acct.
		@param RealizedLoss_Acct 
		Realized Loss Account
	  */
	public void setRealizedLoss_Acct (int RealizedLoss_Acct)
	{
		set_Value (COLUMNNAME_RealizedLoss_Acct, Integer.valueOf(RealizedLoss_Acct));
	}

	/** Get Realized Loss Acct.
		@return Realized Loss Account
	  */
	public int getRealizedLoss_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RealizedLoss_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Credit.
		@param T_Credit_Acct 
		Account for Tax you can reclaim
	  */
	public void setT_Credit_Acct (int T_Credit_Acct)
	{
		set_Value (COLUMNNAME_T_Credit_Acct, Integer.valueOf(T_Credit_Acct));
	}

	/** Get Tax Credit.
		@return Account for Tax you can reclaim
	  */
	public int getT_Credit_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_Credit_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Due.
		@param T_Due_Acct 
		Account for Tax you have to pay
	  */
	public void setT_Due_Acct (int T_Due_Acct)
	{
		set_Value (COLUMNNAME_T_Due_Acct, Integer.valueOf(T_Due_Acct));
	}

	/** Get Tax Due.
		@return Account for Tax you have to pay
	  */
	public int getT_Due_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_Due_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Expense.
		@param T_Expense_Acct 
		Account for paid tax you cannot reclaim
	  */
	public void setT_Expense_Acct (int T_Expense_Acct)
	{
		set_Value (COLUMNNAME_T_Expense_Acct, Integer.valueOf(T_Expense_Acct));
	}

	/** Get Tax Expense.
		@return Account for paid tax you cannot reclaim
	  */
	public int getT_Expense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_Expense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set withholding taxes payable.
		@param T_HoldLiab_Acct 
		withholding taxes payable
	  */
	public void setT_HoldLiab_Acct (int T_HoldLiab_Acct)
	{
		set_Value (COLUMNNAME_T_HoldLiab_Acct, Integer.valueOf(T_HoldLiab_Acct));
	}

	/** Get withholding taxes payable.
		@return withholding taxes payable
	  */
	public int getT_HoldLiab_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_HoldLiab_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Withholding a Favor.
		@param T_HoldRec_Acct 
		Withholding a Favor
	  */
	public void setT_HoldRec_Acct (int T_HoldRec_Acct)
	{
		set_Value (COLUMNNAME_T_HoldRec_Acct, Integer.valueOf(T_HoldRec_Acct));
	}

	/** Get Withholding a Favor.
		@return Withholding a Favor
	  */
	public int getT_HoldRec_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_HoldRec_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Liability.
		@param T_Liability_Acct 
		Account for Tax declaration liability
	  */
	public void setT_Liability_Acct (int T_Liability_Acct)
	{
		set_Value (COLUMNNAME_T_Liability_Acct, Integer.valueOf(T_Liability_Acct));
	}

	/** Get Tax Liability.
		@return Account for Tax declaration liability
	  */
	public int getT_Liability_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_Liability_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Receivables.
		@param T_Receivables_Acct 
		Account for Tax credit after tax declaration
	  */
	public void setT_Receivables_Acct (int T_Receivables_Acct)
	{
		set_Value (COLUMNNAME_T_Receivables_Acct, Integer.valueOf(T_Receivables_Acct));
	}

	/** Get Tax Receivables.
		@return Account for Tax credit after tax declaration
	  */
	public int getT_Receivables_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_T_Receivables_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unearned Revenue.
		@param UnEarnedRevenue_Acct 
		Account for unearned revenue
	  */
	public void setUnEarnedRevenue_Acct (int UnEarnedRevenue_Acct)
	{
		set_Value (COLUMNNAME_UnEarnedRevenue_Acct, Integer.valueOf(UnEarnedRevenue_Acct));
	}

	/** Get Unearned Revenue.
		@return Account for unearned revenue
	  */
	public int getUnEarnedRevenue_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UnEarnedRevenue_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unrealized Gain Acct.
		@param UnrealizedGain_Acct 
		Unrealized Gain Account for currency revaluation
	  */
	public void setUnrealizedGain_Acct (int UnrealizedGain_Acct)
	{
		set_Value (COLUMNNAME_UnrealizedGain_Acct, Integer.valueOf(UnrealizedGain_Acct));
	}

	/** Get Unrealized Gain Acct.
		@return Unrealized Gain Account for currency revaluation
	  */
	public int getUnrealizedGain_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UnrealizedGain_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unrealized Loss Acct.
		@param UnrealizedLoss_Acct 
		Unrealized Loss Account for currency revaluation
	  */
	public void setUnrealizedLoss_Acct (int UnrealizedLoss_Acct)
	{
		set_Value (COLUMNNAME_UnrealizedLoss_Acct, Integer.valueOf(UnrealizedLoss_Acct));
	}

	/** Get Unrealized Loss Acct.
		@return Unrealized Loss Account for currency revaluation
	  */
	public int getUnrealizedLoss_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UnrealizedLoss_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Passive National Suppliers.
		@param V_Liability_Acct 
		Passive National Suppliers
	  */
	public void setV_Liability_Acct (int V_Liability_Acct)
	{
		set_Value (COLUMNNAME_V_Liability_Acct, Integer.valueOf(V_Liability_Acct));
	}

	/** Get Passive National Suppliers.
		@return Passive National Suppliers
	  */
	public int getV_Liability_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_V_Liability_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Passive Foreign Suppliers.
		@param V_LiabilityFgn_Acct 
		Passive Foreign Suppliers
	  */
	public void setV_LiabilityFgn_Acct (int V_LiabilityFgn_Acct)
	{
		set_Value (COLUMNNAME_V_LiabilityFgn_Acct, Integer.valueOf(V_LiabilityFgn_Acct));
	}

	/** Get Passive Foreign Suppliers.
		@return Passive Foreign Suppliers
	  */
	public int getV_LiabilityFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_V_LiabilityFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vendor Service Liability.
		@param V_Liability_Services_Acct 
		Account for Vender Service Liability
	  */
	public void setV_Liability_Services_Acct (int V_Liability_Services_Acct)
	{
		set_Value (COLUMNNAME_V_Liability_Services_Acct, Integer.valueOf(V_Liability_Services_Acct));
	}

	/** Get Vendor Service Liability.
		@return Account for Vender Service Liability
	  */
	public int getV_Liability_Services_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_V_Liability_Services_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Advances to National Suppliers.
		@param V_Prepayment_Acct 
		Advances to National Suppliers
	  */
	public void setV_Prepayment_Acct (int V_Prepayment_Acct)
	{
		set_Value (COLUMNNAME_V_Prepayment_Acct, Integer.valueOf(V_Prepayment_Acct));
	}

	/** Get Advances to National Suppliers.
		@return Advances to National Suppliers
	  */
	public int getV_Prepayment_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_V_Prepayment_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Advances to Foreign Suppliers.
		@param V_PrepaymentFgn_Acct 
		Advances to Foreign Suppliers
	  */
	public void setV_PrepaymentFgn_Acct (int V_PrepaymentFgn_Acct)
	{
		set_Value (COLUMNNAME_V_PrepaymentFgn_Acct, Integer.valueOf(V_PrepaymentFgn_Acct));
	}

	/** Get Advances to Foreign Suppliers.
		@return Advances to Foreign Suppliers
	  */
	public int getV_PrepaymentFgn_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_V_PrepaymentFgn_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse Differences.
		@param W_Differences_Acct 
		Warehouse Differences Account
	  */
	public void setW_Differences_Acct (int W_Differences_Acct)
	{
		set_Value (COLUMNNAME_W_Differences_Acct, Integer.valueOf(W_Differences_Acct));
	}

	/** Get Warehouse Differences.
		@return Warehouse Differences Account
	  */
	public int getW_Differences_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_Differences_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inventory Adjustment.
		@param W_InvActualAdjust_Acct 
		Account for Inventory value adjustments for Actual Costing
	  */
	public void setW_InvActualAdjust_Acct (int W_InvActualAdjust_Acct)
	{
		set_Value (COLUMNNAME_W_InvActualAdjust_Acct, Integer.valueOf(W_InvActualAdjust_Acct));
	}

	/** Get Inventory Adjustment.
		@return Account for Inventory value adjustments for Actual Costing
	  */
	public int getW_InvActualAdjust_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_InvActualAdjust_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse Inventory.
		@param W_Inventory_Acct 
		Warehouse Inventory Asset Account - Currently not used
	  */
	public void setW_Inventory_Acct (int W_Inventory_Acct)
	{
		set_Value (COLUMNNAME_W_Inventory_Acct, Integer.valueOf(W_Inventory_Acct));
	}

	/** Get Warehouse Inventory.
		@return Warehouse Inventory Asset Account - Currently not used
	  */
	public int getW_Inventory_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_Inventory_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Withholding.
		@param Withholding_Acct 
		Account for Withholdings
	  */
	public void setWithholding_Acct (int Withholding_Acct)
	{
		set_Value (COLUMNNAME_Withholding_Acct, Integer.valueOf(Withholding_Acct));
	}

	/** Get Withholding.
		@return Account for Withholdings
	  */
	public int getWithholding_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Withholding_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inventory Revaluation.
		@param W_Revaluation_Acct 
		Account for Inventory Revaluation
	  */
	public void setW_Revaluation_Acct (int W_Revaluation_Acct)
	{
		set_Value (COLUMNNAME_W_Revaluation_Acct, Integer.valueOf(W_Revaluation_Acct));
	}

	/** Get Inventory Revaluation.
		@return Account for Inventory Revaluation
	  */
	public int getW_Revaluation_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_Revaluation_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Write-off.
		@param WriteOff_Acct 
		Account for Receivables write-off
	  */
	public void setWriteOff_Acct (int WriteOff_Acct)
	{
		set_Value (COLUMNNAME_WriteOff_Acct, Integer.valueOf(WriteOff_Acct));
	}

	/** Get Write-off.
		@return Account for Receivables write-off
	  */
	public int getWriteOff_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WriteOff_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}