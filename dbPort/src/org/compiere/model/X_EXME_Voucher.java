/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Voucher
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Voucher extends PO implements I_EXME_Voucher, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Voucher (Properties ctx, int EXME_Voucher_ID, String trxName)
    {
      super (ctx, EXME_Voucher_ID, trxName);
      /** if (EXME_Voucher_ID == 0)
        {
			setEXME_Voucher_ID (0);
			setMetodoPago (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Voucher (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Voucher[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Voucher.
		@param EXME_Voucher_ID Voucher	  */
	public void setEXME_Voucher_ID (int EXME_Voucher_ID)
	{
		if (EXME_Voucher_ID < 1)
			 throw new IllegalArgumentException ("EXME_Voucher_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Voucher_ID, Integer.valueOf(EXME_Voucher_ID));
	}

	/** Get Voucher.
		@return Voucher	  */
	public int getEXME_Voucher_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Voucher_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment method.
		@param MetodoPago 
		Payment method
	  */
	public void setMetodoPago (String MetodoPago)
	{
		if (MetodoPago == null)
			throw new IllegalArgumentException ("MetodoPago is mandatory.");
		set_Value (COLUMNNAME_MetodoPago, MetodoPago);
	}

	/** Get Payment method.
		@return Payment method
	  */
	public String getMetodoPago () 
	{
		return (String)get_Value(COLUMNNAME_MetodoPago);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
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