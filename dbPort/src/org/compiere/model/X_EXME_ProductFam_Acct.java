/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ProductFam_Acct
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProductFam_Acct extends PO implements I_EXME_ProductFam_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductFam_Acct (Properties ctx, int EXME_ProductFam_Acct_ID, String trxName)
    {
      super (ctx, EXME_ProductFam_Acct_ID, trxName);
      /** if (EXME_ProductFam_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setEXME_ProductFam_ID (0);
			setP_DesctoFam_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductFam_Acct (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductFam_Acct[")
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
		set_Value (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
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

	/** Set Family Products.
		@param EXME_ProductFam_ID 
		Family Products
	  */
	public void setEXME_ProductFam_ID (int EXME_ProductFam_ID)
	{
		if (EXME_ProductFam_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductFam_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ProductFam_ID, Integer.valueOf(EXME_ProductFam_ID));
	}

	/** Get Family Products.
		@return Family Products
	  */
	public int getEXME_ProductFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductFam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Family Discount Account.
		@param P_DesctoFam_Acct 
		Family Discount Account
	  */
	public void setP_DesctoFam_Acct (int P_DesctoFam_Acct)
	{
		set_Value (COLUMNNAME_P_DesctoFam_Acct, Integer.valueOf(P_DesctoFam_Acct));
	}

	/** Get Family Discount Account.
		@return Family Discount Account
	  */
	public int getP_DesctoFam_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_P_DesctoFam_Acct);
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
}