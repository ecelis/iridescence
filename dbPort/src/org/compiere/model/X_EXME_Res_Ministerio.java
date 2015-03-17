/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Res_Ministerio
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Res_Ministerio extends PO implements I_EXME_Res_Ministerio, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Res_Ministerio (Properties ctx, int EXME_Res_Ministerio_ID, String trxName)
    {
      super (ctx, EXME_Res_Ministerio_ID, trxName);
      /** if (EXME_Res_Ministerio_ID == 0)
        {
			setEXME_Ministerio_ID (0);
			setEXME_Res_Ministerio_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Res_Ministerio (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Res_Ministerio[")
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

	/** Set Ministry.
		@param EXME_Ministerio_ID 
		Ministry
	  */
	public void setEXME_Ministerio_ID (int EXME_Ministerio_ID)
	{
		if (EXME_Ministerio_ID < 1)
			 throw new IllegalArgumentException ("EXME_Ministerio_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Ministerio_ID, Integer.valueOf(EXME_Ministerio_ID));
	}

	/** Get Ministry.
		@return Ministry
	  */
	public int getEXME_Ministerio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ministerio_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Responsible Ministry.
		@param EXME_Res_Ministerio_ID 
		Responsables de Ministerios
	  */
	public void setEXME_Res_Ministerio_ID (int EXME_Res_Ministerio_ID)
	{
		if (EXME_Res_Ministerio_ID < 1)
			 throw new IllegalArgumentException ("EXME_Res_Ministerio_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Res_Ministerio_ID, Integer.valueOf(EXME_Res_Ministerio_ID));
	}

	/** Get Responsible Ministry.
		@return Responsables de Ministerios
	  */
	public int getEXME_Res_Ministerio_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Res_Ministerio_ID);
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