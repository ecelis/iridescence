/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ReferMot
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ReferMot extends PO implements I_EXME_ReferMot, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ReferMot (Properties ctx, int EXME_ReferMot_ID, String trxName)
    {
      super (ctx, EXME_ReferMot_ID, trxName);
      /** if (EXME_ReferMot_ID == 0)
        {
			setAprobacion (false);
			setEXME_ReferMot_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ReferMot (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ReferMot[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Approval.
		@param Aprobacion 
		Approval Required
	  */
	public void setAprobacion (boolean Aprobacion)
	{
		set_Value (COLUMNNAME_Aprobacion, Boolean.valueOf(Aprobacion));
	}

	/** Get Approval.
		@return Approval Required
	  */
	public boolean isAprobacion () 
	{
		Object oo = get_Value(COLUMNNAME_Aprobacion);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Reference Motive.
		@param EXME_ReferMot_ID 
		Reference Motive
	  */
	public void setEXME_ReferMot_ID (int EXME_ReferMot_ID)
	{
		if (EXME_ReferMot_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReferMot_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ReferMot_ID, Integer.valueOf(EXME_ReferMot_ID));
	}

	/** Get Reference Motive.
		@return Reference Motive
	  */
	public int getEXME_ReferMot_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReferMot_ID);
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