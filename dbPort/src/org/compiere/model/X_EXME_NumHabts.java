/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_NumHabts
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_NumHabts extends PO implements I_EXME_NumHabts, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_NumHabts (Properties ctx, int EXME_NumHabts_ID, String trxName)
    {
      super (ctx, EXME_NumHabts_ID, trxName);
      /** if (EXME_NumHabts_ID == 0)
        {
			setDescription (null);
			setEXME_NumHabts_ID (0);
			setName (null);
			setPts (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_NumHabts (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_NumHabts[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Number of Rooms.
		@param EXME_NumHabts_ID 
		Number of Rooms
	  */
	public void setEXME_NumHabts_ID (int EXME_NumHabts_ID)
	{
		if (EXME_NumHabts_ID < 1)
			 throw new IllegalArgumentException ("EXME_NumHabts_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_NumHabts_ID, Integer.valueOf(EXME_NumHabts_ID));
	}

	/** Get Number of Rooms.
		@return Number of Rooms
	  */
	public int getEXME_NumHabts_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_NumHabts_ID);
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

	/** Set Points.
		@param Pts 
		Points
	  */
	public void setPts (int Pts)
	{
		set_Value (COLUMNNAME_Pts, Integer.valueOf(Pts));
	}

	/** Get Points.
		@return Points
	  */
	public int getPts () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Pts);
		if (ii == null)
			 return 0;
		return ii.intValue();
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