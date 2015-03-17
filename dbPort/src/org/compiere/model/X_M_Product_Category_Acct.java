/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for M_Product_Category_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_M_Product_Category_Acct extends PO implements I_M_Product_Category_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_Product_Category_Acct (Properties ctx, int M_Product_Category_Acct_ID, String trxName)
    {
      super (ctx, M_Product_Category_Acct_ID, trxName);
      /** if (M_Product_Category_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
// @$C_AcctSchema_ID@
			setM_Product_Category_ID (0);
			setP_Asset_Acct (0);
			setP_Burden_Acct (0);
			setP_COGS_Acct (0);
			setP_CostAdjustment_Acct (0);
			setP_CostOfProduction_Acct (0);
			setP_Expense_Acct (0);
			setP_FloorStock_Acct (0);
			setP_InventoryClearing_Acct (0);
			setP_InvoicePriceVariance_Acct (0);
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
        } */
    }

    /** Load Constructor */
    public X_M_Product_Category_Acct (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_M_Product_Category_Acct[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** CostingLevel AD_Reference_ID=355 */
	public static final int COSTINGLEVEL_AD_Reference_ID=355;
	/** Client = C */
	public static final String COSTINGLEVEL_Client = "C";
	/** Organization = O */
	public static final String COSTINGLEVEL_Organization = "O";
	/** Batch/Lot = B */
	public static final String COSTINGLEVEL_BatchLot = "B";
	/** Set Costing Level.
		@param CostingLevel 
		The lowest level to accumulate Costing Information
	  */
	public void setCostingLevel (String CostingLevel)
	{

		if (CostingLevel == null || CostingLevel.equals("C") || CostingLevel.equals("O") || CostingLevel.equals("B")); else throw new IllegalArgumentException ("CostingLevel Invalid value - " + CostingLevel + " - Reference_ID=355 - C - O - B");		set_Value (COLUMNNAME_CostingLevel, CostingLevel);
	}

	/** Get Costing Level.
		@return The lowest level to accumulate Costing Information
	  */
	public String getCostingLevel () 
	{
		return (String)get_Value(COLUMNNAME_CostingLevel);
	}

	/** CostingMethod AD_Reference_ID=122 */
	public static final int COSTINGMETHOD_AD_Reference_ID=122;
	/** Standard Costing = S */
	public static final String COSTINGMETHOD_StandardCosting = "S";
	/** Average PO = A */
	public static final String COSTINGMETHOD_AveragePO = "A";
	/** Lifo = L */
	public static final String COSTINGMETHOD_Lifo = "L";
	/** Fifo = F */
	public static final String COSTINGMETHOD_Fifo = "F";
	/** Last PO Price = p */
	public static final String COSTINGMETHOD_LastPOPrice = "p";
	/** Average Invoice = I */
	public static final String COSTINGMETHOD_AverageInvoice = "I";
	/** Last Invoice = i */
	public static final String COSTINGMETHOD_LastInvoice = "i";
	/** User Defined = U */
	public static final String COSTINGMETHOD_UserDefined = "U";
	/** _ = x */
	public static final String COSTINGMETHOD__ = "x";
	/** Set Costing Method.
		@param CostingMethod 
		Indicates how Costs will be calculated
	  */
	public void setCostingMethod (String CostingMethod)
	{

		if (CostingMethod == null || CostingMethod.equals("S") || CostingMethod.equals("A") || CostingMethod.equals("L") || CostingMethod.equals("F") || CostingMethod.equals("p") || CostingMethod.equals("I") || CostingMethod.equals("i") || CostingMethod.equals("U") || CostingMethod.equals("x")); else throw new IllegalArgumentException ("CostingMethod Invalid value - " + CostingMethod + " - Reference_ID=122 - S - A - L - F - p - I - i - U - x");		set_Value (COLUMNNAME_CostingMethod, CostingMethod);
	}

	/** Get Costing Method.
		@return Indicates how Costs will be calculated
	  */
	public String getCostingMethod () 
	{
		return (String)get_Value(COLUMNNAME_CostingMethod);
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

	public I_M_Product_Category getM_Product_Category() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product_Category.Table_Name);
        I_M_Product_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1)
			 throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
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
}