/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Parentesco
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Parentesco extends PO implements I_EXME_Parentesco, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Parentesco (Properties ctx, int EXME_Parentesco_ID, String trxName)
    {
      super (ctx, EXME_Parentesco_ID, trxName);
      /** if (EXME_Parentesco_ID == 0)
        {
			setEXME_Parentesco_ID (0);
			setIsCloseRelative (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Parentesco (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Parentesco[")
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

	/** Set Kinship.
		@param EXME_Parentesco_ID 
		Kinship
	  */
	public void setEXME_Parentesco_ID (int EXME_Parentesco_ID)
	{
		if (EXME_Parentesco_ID < 1)
			 throw new IllegalArgumentException ("EXME_Parentesco_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Parentesco_ID, Integer.valueOf(EXME_Parentesco_ID));
	}

	/** Get Kinship.
		@return Kinship
	  */
	public int getEXME_Parentesco_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Parentesco_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is close relative.
		@param IsCloseRelative Is close relative	  */
	public void setIsCloseRelative (boolean IsCloseRelative)
	{
		set_Value (COLUMNNAME_IsCloseRelative, Boolean.valueOf(IsCloseRelative));
	}

	/** Get Is close relative.
		@return Is close relative	  */
	public boolean isCloseRelative () 
	{
		Object oo = get_Value(COLUMNNAME_IsCloseRelative);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Type.
		@param Type2 
		Type
	  */
	public void setType2 (boolean Type2)
	{
		set_Value (COLUMNNAME_Type2, Boolean.valueOf(Type2));
	}

	/** Get Type.
		@return Type
	  */
	public boolean isType2 () 
	{
		Object oo = get_Value(COLUMNNAME_Type2);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Value 270.
		@param Value_270 Value 270	  */
	public void setValue_270 (String Value_270)
	{
		set_Value (COLUMNNAME_Value_270, Value_270);
	}

	/** Get Value 270.
		@return Value 270	  */
	public String getValue_270 () 
	{
		return (String)get_Value(COLUMNNAME_Value_270);
	}

	/** Set Value 837.
		@param Value_837 Value 837	  */
	public void setValue_837 (String Value_837)
	{
		set_Value (COLUMNNAME_Value_837, Value_837);
	}

	/** Get Value 837.
		@return Value 837	  */
	public String getValue_837 () 
	{
		return (String)get_Value(COLUMNNAME_Value_837);
	}
}