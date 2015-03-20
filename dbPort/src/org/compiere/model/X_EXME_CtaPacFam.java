/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_CtaPacFam
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CtaPacFam extends PO implements I_EXME_CtaPacFam, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacFam (Properties ctx, int EXME_CtaPacFam_ID, String trxName)
    {
      super (ctx, EXME_CtaPacFam_ID, trxName);
      /** if (EXME_CtaPacFam_ID == 0)
        {
			setBaseAmt (Env.ZERO);
			setDiscountAmt (Env.ZERO);
			setDiscountPorcent (Env.ZERO);
			setEXME_CtaPacExt_ID (0);
			setEXME_CtaPacFam_ID (0);
			setEXME_ProductFam_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacFam (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacFam[")
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

	/** Set Patient Account Extension.
		@param EXME_CtaPacExt_ID 
		Patient Account Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID)
	{
		if (EXME_CtaPacExt_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacExt_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPacExt_ID, Integer.valueOf(EXME_CtaPacExt_ID));
	}

	/** Get Patient Account Extension.
		@return Patient Account Extension
	  */
	public int getEXME_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacExt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discount per Product Family.
		@param EXME_CtaPacFam_ID 
		Discount per Product Family
	  */
	public void setEXME_CtaPacFam_ID (int EXME_CtaPacFam_ID)
	{
		if (EXME_CtaPacFam_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacFam_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPacFam_ID, Integer.valueOf(EXME_CtaPacFam_ID));
	}

	/** Get Discount per Product Family.
		@return Discount per Product Family
	  */
	public int getEXME_CtaPacFam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacFam_ID);
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