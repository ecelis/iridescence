/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Delegacion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Delegacion extends PO implements I_EXME_Delegacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Delegacion (Properties ctx, int EXME_Delegacion_ID, String trxName)
    {
      super (ctx, EXME_Delegacion_ID, trxName);
      /** if (EXME_Delegacion_ID == 0)
        {
			setEXME_Delegacion_ID (0);
			setIsDefault (false);
// N
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Delegacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Delegacion[")
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

	/** Set Delegacion.
		@param EXME_Delegacion_ID 
		Delegacion
	  */
	public void setEXME_Delegacion_ID (int EXME_Delegacion_ID)
	{
		if (EXME_Delegacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Delegacion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Delegacion_ID, Integer.valueOf(EXME_Delegacion_ID));
	}

	/** Get Delegacion.
		@return Delegacion
	  */
	public int getEXME_Delegacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Delegacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
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