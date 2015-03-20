/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for C_BP_Customer_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_BP_Customer_Acct extends PO implements I_C_BP_Customer_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_BP_Customer_Acct (Properties ctx, int C_BP_Customer_Acct_ID, String trxName)
    {
      super (ctx, C_BP_Customer_Acct_ID, trxName);
      /** if (C_BP_Customer_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setC_BPartner_ID (0);
			setC_Prepayment_Acct (0);
			setC_Receivable_Acct (0);
			setC_Receivable_Services_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_C_BP_Customer_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_BP_Customer_Acct[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
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
}