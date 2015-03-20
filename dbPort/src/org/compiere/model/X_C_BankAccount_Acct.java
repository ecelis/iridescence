/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for C_BankAccount_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_BankAccount_Acct extends PO implements I_C_BankAccount_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_BankAccount_Acct (Properties ctx, int C_BankAccount_Acct_ID, String trxName)
    {
      super (ctx, C_BankAccount_Acct_ID, trxName);
      /** if (C_BankAccount_Acct_ID == 0)
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
			setC_BankAccount_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_BankAccount_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_BankAccount_Acct[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_C_BankAccount getC_BankAccount() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BankAccount.Table_Name);
        I_C_BankAccount result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BankAccount)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BankAccount_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1)
			 throw new IllegalArgumentException ("C_BankAccount_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}