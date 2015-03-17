/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Escolaridad
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Escolaridad extends PO implements I_EXME_Escolaridad, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Escolaridad (Properties ctx, int EXME_Escolaridad_ID, String trxName)
    {
      super (ctx, EXME_Escolaridad_ID, trxName);
      /** if (EXME_Escolaridad_ID == 0)
        {
			setEXME_Escolaridad_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Escolaridad (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Escolaridad[")
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

	/** Set Schooling.
		@param EXME_Escolaridad_ID 
		Schooling
	  */
	public void setEXME_Escolaridad_ID (int EXME_Escolaridad_ID)
	{
		if (EXME_Escolaridad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Escolaridad_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Escolaridad_ID, Integer.valueOf(EXME_Escolaridad_ID));
	}

	/** Get Schooling.
		@return Schooling
	  */
	public int getEXME_Escolaridad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Escolaridad_ID);
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