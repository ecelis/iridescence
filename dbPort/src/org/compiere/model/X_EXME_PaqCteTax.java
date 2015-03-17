/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_PaqCteTax
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PaqCteTax extends PO implements I_EXME_PaqCteTax, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PaqCteTax (Properties ctx, int EXME_PaqCteTax_ID, String trxName)
    {
      super (ctx, EXME_PaqCteTax_ID, trxName);
      /** if (EXME_PaqCteTax_ID == 0)
        {
			setC_Tax_ID (0);
			setEXME_PaqCte_ID (0);
			setTaxAmt (Env.ZERO);
			setTaxBaseAmt (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_PaqCteTax (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PaqCteTax[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1)
			 throw new IllegalArgumentException ("C_Tax_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Package - Account.
		@param EXME_PaqCte_ID 
		Package - Account
	  */
	public void setEXME_PaqCte_ID (int EXME_PaqCte_ID)
	{
		if (EXME_PaqCte_ID < 1)
			 throw new IllegalArgumentException ("EXME_PaqCte_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PaqCte_ID, Integer.valueOf(EXME_PaqCte_ID));
	}

	/** Get Package - Account.
		@return Package - Account
	  */
	public int getEXME_PaqCte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PaqCte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		if (TaxAmt == null)
			throw new IllegalArgumentException ("TaxAmt is mandatory.");
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax base Amount.
		@param TaxBaseAmt 
		Base for calculating the tax amount
	  */
	public void setTaxBaseAmt (BigDecimal TaxBaseAmt)
	{
		if (TaxBaseAmt == null)
			throw new IllegalArgumentException ("TaxBaseAmt is mandatory.");
		set_Value (COLUMNNAME_TaxBaseAmt, TaxBaseAmt);
	}

	/** Get Tax base Amount.
		@return Base for calculating the tax amount
	  */
	public BigDecimal getTaxBaseAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxBaseAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}