/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_MotivoCita
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_MotivoCita extends PO implements I_EXME_MotivoCita, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MotivoCita (Properties ctx, int EXME_MotivoCita_ID, String trxName)
    {
      super (ctx, EXME_MotivoCita_ID, trxName);
      /** if (EXME_MotivoCita_ID == 0)
        {
			setEXME_MotivoCita_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MotivoCita (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MotivoCita[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Body.
		@param Body Body	  */
	public void setBody (String Body)
	{
		set_Value (COLUMNNAME_Body, Body);
	}

	/** Get Body.
		@return Body	  */
	public String getBody () 
	{
		return (String)get_Value(COLUMNNAME_Body);
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

	/** Set Motive of appointment.
		@param EXME_MotivoCita_ID 
		Motive of appointment
	  */
	public void setEXME_MotivoCita_ID (int EXME_MotivoCita_ID)
	{
		if (EXME_MotivoCita_ID < 1)
			 throw new IllegalArgumentException ("EXME_MotivoCita_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MotivoCita_ID, Integer.valueOf(EXME_MotivoCita_ID));
	}

	/** Get Motive of appointment.
		@return Motive of appointment
	  */
	public int getEXME_MotivoCita_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCita_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Header.
		@param Header Header	  */
	public void setHeader (String Header)
	{
		set_Value (COLUMNNAME_Header, Header);
	}

	/** Get Header.
		@return Header	  */
	public String getHeader () 
	{
		return (String)get_Value(COLUMNNAME_Header);
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

	/** Set Social Work Apptmt..
		@param TrabSoc 
		Social Work Appointment
	  */
	public void setTrabSoc (boolean TrabSoc)
	{
		set_Value (COLUMNNAME_TrabSoc, Boolean.valueOf(TrabSoc));
	}

	/** Get Social Work Apptmt..
		@return Social Work Appointment
	  */
	public boolean isTrabSoc () 
	{
		Object oo = get_Value(COLUMNNAME_TrabSoc);
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
}