/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Cripto
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Cripto extends PO implements I_EXME_Cripto, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Cripto (Properties ctx, int EXME_Cripto_ID, String trxName)
    {
      super (ctx, EXME_Cripto_ID, trxName);
      /** if (EXME_Cripto_ID == 0)
        {
			setEXME_Cripto_ID (0);
			setName (null);
			setStringIVBRaw (null);
			setStringIVB64 (null);
			setStringKeyB64 (null);
			setStringKeyRaw (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Cripto (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Cripto[")
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

	/** Set EXME_Cripto_ID.
		@param EXME_Cripto_ID 
		EXME_Cripto_ID
	  */
	public void setEXME_Cripto_ID (int EXME_Cripto_ID)
	{
		if (EXME_Cripto_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cripto_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Cripto_ID, Integer.valueOf(EXME_Cripto_ID));
	}

	/** Get EXME_Cripto_ID.
		@return EXME_Cripto_ID
	  */
	public int getEXME_Cripto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cripto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set StringIVBRaw.
		@param StringIVBRaw 
		StringIVBRaw
	  */
	public void setStringIVBRaw (String StringIVBRaw)
	{
		if (StringIVBRaw == null)
			throw new IllegalArgumentException ("StringIVBRaw is mandatory.");
		set_Value (COLUMNNAME_StringIVBRaw, StringIVBRaw);
	}

	/** Get StringIVBRaw.
		@return StringIVBRaw
	  */
	public String getStringIVBRaw () 
	{
		return (String)get_Value(COLUMNNAME_StringIVBRaw);
	}

	/** Set StringIVB64.
		@param StringIVB64 
		StringIVB64
	  */
	public void setStringIVB64 (String StringIVB64)
	{
		if (StringIVB64 == null)
			throw new IllegalArgumentException ("StringIVB64 is mandatory.");
		set_Value (COLUMNNAME_StringIVB64, StringIVB64);
	}

	/** Get StringIVB64.
		@return StringIVB64
	  */
	public String getStringIVB64 () 
	{
		return (String)get_Value(COLUMNNAME_StringIVB64);
	}

	/** Set StringKeyB64.
		@param StringKeyB64 
		StringKeyB64
	  */
	public void setStringKeyB64 (String StringKeyB64)
	{
		if (StringKeyB64 == null)
			throw new IllegalArgumentException ("StringKeyB64 is mandatory.");
		set_Value (COLUMNNAME_StringKeyB64, StringKeyB64);
	}

	/** Get StringKeyB64.
		@return StringKeyB64
	  */
	public String getStringKeyB64 () 
	{
		return (String)get_Value(COLUMNNAME_StringKeyB64);
	}

	/** Set StringKeyRaw.
		@param StringKeyRaw 
		StringKeyRaw
	  */
	public void setStringKeyRaw (String StringKeyRaw)
	{
		if (StringKeyRaw == null)
			throw new IllegalArgumentException ("StringKeyRaw is mandatory.");
		set_Value (COLUMNNAME_StringKeyRaw, StringKeyRaw);
	}

	/** Get StringKeyRaw.
		@return StringKeyRaw
	  */
	public String getStringKeyRaw () 
	{
		return (String)get_Value(COLUMNNAME_StringKeyRaw);
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