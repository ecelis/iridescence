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

/** Generated Model for C_BP_Group_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_BP_Group_Acct extends PO implements I_C_BP_Group_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_BP_Group_Acct (Properties ctx, int C_BP_Group_Acct_ID, String trxName)
    {
      super (ctx, C_BP_Group_Acct_ID, trxName);
      /** if (C_BP_Group_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setC_BP_Group_ID (0);
			setC_Prepayment_Acct (0);
			setC_Receivable_Acct (0);
			setC_Receivable_Services_Acct (0);
			setNotInvoicedReceipts_Acct (0);
			setNotInvoicedReceivables_Acct (0);
			setNotInvoicedRevenue_Acct (0);
			setPayDiscount_Exp_Acct (0);
			setPayDiscount_Rev_Acct (0);
			setUnEarnedRevenue_Acct (0);
			setV_Liability_Acct (0);
			setV_Liability_Services_Acct (0);
			setV_Prepayment_Acct (0);
			setWriteOff_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_C_BP_Group_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_BP_Group_Acct[")
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

	public I_C_BP_Group getC_BP_Group() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BP_Group.Table_Name);
        I_C_BP_Group result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BP_Group)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BP_Group_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company Group.
		@param C_BP_Group_ID 
		Company Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1)
			 throw new IllegalArgumentException ("C_BP_Group_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Company Group.
		@return Company Group
	  */
	public int getC_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_BP_Group_ID()));
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