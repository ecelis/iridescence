/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_InvoiceFam
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_InvoiceFam extends PO implements I_EXME_InvoiceFam, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_InvoiceFam (Properties ctx, int EXME_InvoiceFam_ID, String trxName)
    {
      super (ctx, EXME_InvoiceFam_ID, trxName);
      /** if (EXME_InvoiceFam_ID == 0)
        {
			setBaseAmt (Env.ZERO);
			setC_Invoice_ID (0);
			setDiscountAmt (Env.ZERO);
			setDiscountPorcent (Env.ZERO);
			setEXME_ProductFam_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_InvoiceFam (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_InvoiceFam[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Base Amount.
		@param BaseAmt 
		Base Amount
	  */
	public void setBaseAmt (BigDecimal BaseAmt)
	{
		if (BaseAmt == null)
			throw new IllegalArgumentException ("BaseAmt is mandatory.");
		set_Value (COLUMNNAME_BaseAmt, BaseAmt);
	}

	/** Get Base Amount.
		@return Base Amount
	  */
	public BigDecimal getBaseAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BaseAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1)
			 throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discount Amount.
		@param DiscountAmt 
		Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt)
	{
		if (DiscountAmt == null)
			throw new IllegalArgumentException ("DiscountAmt is mandatory.");
		set_Value (COLUMNNAME_DiscountAmt, DiscountAmt);
	}

	/** Get Discount Amount.
		@return Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set DiscountPorcent.
		@param DiscountPorcent 
		DiscountPorcent
	  */
	public void setDiscountPorcent (BigDecimal DiscountPorcent)
	{
		if (DiscountPorcent == null)
			throw new IllegalArgumentException ("DiscountPorcent is mandatory.");
		set_Value (COLUMNNAME_DiscountPorcent, DiscountPorcent);
	}

	/** Get DiscountPorcent.
		@return DiscountPorcent
	  */
	public BigDecimal getDiscountPorcent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountPorcent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Family Products.
		@param EXME_ProductFam_ID 
		Family Products
	  */
	public void setEXME_ProductFam_ID (int EXME_ProductFam_ID)
	{
		if (EXME_ProductFam_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductFam_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProductFam_ID, Integer.valueOf(EXME_ProductFam_ID));
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
}