/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ProductSActiva
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProductSActiva extends PO implements I_EXME_ProductSActiva, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductSActiva (Properties ctx, int EXME_ProductSActiva_ID, String trxName)
    {
      super (ctx, EXME_ProductSActiva_ID, trxName);
      /** if (EXME_ProductSActiva_ID == 0)
        {
			setEXME_ProductSActiva_ID (0);
			setEXME_SActiva_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductSActiva (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductSActiva[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Product - Active Substance.
		@param EXME_ProductSActiva_ID 
		Product - Active Substance
	  */
	public void setEXME_ProductSActiva_ID (int EXME_ProductSActiva_ID)
	{
		if (EXME_ProductSActiva_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductSActiva_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProductSActiva_ID, Integer.valueOf(EXME_ProductSActiva_ID));
	}

	/** Get Product - Active Substance.
		@return Product - Active Substance
	  */
	public int getEXME_ProductSActiva_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductSActiva_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Active Substance.
		@param EXME_SActiva_ID 
		Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID)
	{
		if (EXME_SActiva_ID < 1)
			 throw new IllegalArgumentException ("EXME_SActiva_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SActiva_ID, Integer.valueOf(EXME_SActiva_ID));
	}

	/** Get Active Substance.
		@return Active Substance
	  */
	public int getEXME_SActiva_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SActiva_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}