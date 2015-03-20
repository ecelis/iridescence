/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for C_BP_Vendor_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_BP_Vendor_Acct extends PO implements I_C_BP_Vendor_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_BP_Vendor_Acct (Properties ctx, int C_BP_Vendor_Acct_ID, String trxName)
    {
      super (ctx, C_BP_Vendor_Acct_ID, trxName);
      /** if (C_BP_Vendor_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setC_BPartner_ID (0);
			setV_Liability_Acct (0);
			setV_Prepayment_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_C_BP_Vendor_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_BP_Vendor_Acct[")
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
}