/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_FormaPago_Acct
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_FormaPago_Acct extends PO implements I_EXME_FormaPago_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FormaPago_Acct (Properties ctx, int EXME_FormaPago_Acct_ID, String trxName)
    {
      super (ctx, EXME_FormaPago_Acct_ID, trxName);
      /** if (EXME_FormaPago_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setEXME_FormaPago_ID (0);
			setFP_Asset_Acct (0);
			setFP_Revenue_Other_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_FormaPago_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FormaPago_Acct[")
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

	/** Set Payment Form.
		@param EXME_FormaPago_ID 
		Identifier of Payment Form
	  */
	public void setEXME_FormaPago_ID (int EXME_FormaPago_ID)
	{
		if (EXME_FormaPago_ID < 1)
			 throw new IllegalArgumentException ("EXME_FormaPago_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_FormaPago_ID, Integer.valueOf(EXME_FormaPago_ID));
	}

	/** Get Payment Form.
		@return Identifier of Payment Form
	  */
	public int getEXME_FormaPago_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormaPago_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cash Assets.
		@param FP_Asset_Acct Cash Assets	  */
	public void setFP_Asset_Acct (int FP_Asset_Acct)
	{
		set_Value (COLUMNNAME_FP_Asset_Acct, Integer.valueOf(FP_Asset_Acct));
	}

	/** Get Cash Assets.
		@return Cash Assets	  */
	public int getFP_Asset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FP_Asset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Other Cash income.
		@param FP_Revenue_Other_Acct 
		Other Cash income
	  */
	public void setFP_Revenue_Other_Acct (int FP_Revenue_Other_Acct)
	{
		set_Value (COLUMNNAME_FP_Revenue_Other_Acct, Integer.valueOf(FP_Revenue_Other_Acct));
	}

	/** Get Other Cash income.
		@return Other Cash income
	  */
	public int getFP_Revenue_Other_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FP_Revenue_Other_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}