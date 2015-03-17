/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_ProductoPrecio
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ProductoPrecio extends PO implements I_EXME_ProductoPrecio, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProductoPrecio (Properties ctx, int EXME_ProductoPrecio_ID, String trxName)
    {
      super (ctx, EXME_ProductoPrecio_ID, trxName);
      /** if (EXME_ProductoPrecio_ID == 0)
        {
			setEXME_ProductoPrecio_ID (0);
			setM_Product_ID (0);
// @M_Product_ID@
			setPriceLimit (Env.ZERO);
			setPriceList (Env.ZERO);
			setPriceStd (Env.ZERO);
			setValidFrom (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_ProductoPrecio (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProductoPrecio[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Product Price.
		@param EXME_ProductoPrecio_ID 
		Id relation uom, version of price list and product
	  */
	public void setEXME_ProductoPrecio_ID (int EXME_ProductoPrecio_ID)
	{
		if (EXME_ProductoPrecio_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProductoPrecio_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProductoPrecio_ID, Integer.valueOf(EXME_ProductoPrecio_ID));
	}

	/** Get Product Price.
		@return Id relation uom, version of price list and product
	  */
	public int getEXME_ProductoPrecio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProductoPrecio_ID);
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

	/** Set Limit Price.
		@param PriceLimit 
		Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit)
	{
		if (PriceLimit == null)
			throw new IllegalArgumentException ("PriceLimit is mandatory.");
		set_Value (COLUMNNAME_PriceLimit, PriceLimit);
	}

	/** Get Limit Price.
		@return Lowest price for a product
	  */
	public BigDecimal getPriceLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		if (PriceList == null)
			throw new IllegalArgumentException ("PriceList is mandatory.");
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Standard Price.
		@param PriceStd 
		Standard Price
	  */
	public void setPriceStd (BigDecimal PriceStd)
	{
		if (PriceStd == null)
			throw new IllegalArgumentException ("PriceStd is mandatory.");
		set_Value (COLUMNNAME_PriceStd, PriceStd);
	}

	/** Get Standard Price.
		@return Standard Price
	  */
	public BigDecimal getPriceStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceStd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		if (ValidFrom == null)
			throw new IllegalArgumentException ("ValidFrom is mandatory.");
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}
}