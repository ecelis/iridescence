/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_MotivoReagenda
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MotivoReagenda extends PO implements I_EXME_MotivoReagenda, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MotivoReagenda (Properties ctx, int EXME_MotivoReagenda_ID, String trxName)
    {
      super (ctx, EXME_MotivoReagenda_ID, trxName);
      /** if (EXME_MotivoReagenda_ID == 0)
        {
			setEXME_MotivoReagenda_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MotivoReagenda (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MotivoReagenda[")
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

	/** Set Reason for Reschedule.
		@param EXME_MotivoReagenda_ID 
		Reason for Reschedule
	  */
	public void setEXME_MotivoReagenda_ID (int EXME_MotivoReagenda_ID)
	{
		if (EXME_MotivoReagenda_ID < 1)
			 throw new IllegalArgumentException ("EXME_MotivoReagenda_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MotivoReagenda_ID, Integer.valueOf(EXME_MotivoReagenda_ID));
	}

	/** Get Reason for Reschedule.
		@return Reason for Reschedule
	  */
	public int getEXME_MotivoReagenda_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoReagenda_ID);
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