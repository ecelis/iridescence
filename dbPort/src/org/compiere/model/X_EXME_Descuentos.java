/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Descuentos
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Descuentos extends PO implements I_EXME_Descuentos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Descuentos (Properties ctx, int EXME_Descuentos_ID, String trxName)
    {
      super (ctx, EXME_Descuentos_ID, trxName);
      /** if (EXME_Descuentos_ID == 0)
        {
			setEXME_Descuentos_ID (0);
			setMax_Desc (Env.ZERO);
			setMin_Desc (Env.ZERO);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Descuentos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Descuentos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Discount.
		@param EXME_Descuentos_ID Discount	  */
	public void setEXME_Descuentos_ID (int EXME_Descuentos_ID)
	{
		if (EXME_Descuentos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Descuentos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Descuentos_ID, Integer.valueOf(EXME_Descuentos_ID));
	}

	/** Get Discount.
		@return Discount	  */
	public int getEXME_Descuentos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Descuentos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximum Discount.
		@param Max_Desc Maximum Discount	  */
	public void setMax_Desc (BigDecimal Max_Desc)
	{
		if (Max_Desc == null)
			throw new IllegalArgumentException ("Max_Desc is mandatory.");
		set_Value (COLUMNNAME_Max_Desc, Max_Desc);
	}

	/** Get Maximum Discount.
		@return Maximum Discount	  */
	public BigDecimal getMax_Desc () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Max_Desc);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Discount.
		@param Min_Desc Minimum Discount	  */
	public void setMin_Desc (BigDecimal Min_Desc)
	{
		if (Min_Desc == null)
			throw new IllegalArgumentException ("Min_Desc is mandatory.");
		set_Value (COLUMNNAME_Min_Desc, Min_Desc);
	}

	/** Get Minimum Discount.
		@return Minimum Discount	  */
	public BigDecimal getMin_Desc () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Min_Desc);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}