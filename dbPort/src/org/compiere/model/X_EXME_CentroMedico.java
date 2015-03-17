/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_CentroMedico
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CentroMedico extends PO implements I_EXME_CentroMedico, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CentroMedico (Properties ctx, int EXME_CentroMedico_ID, String trxName)
    {
      super (ctx, EXME_CentroMedico_ID, trxName);
      /** if (EXME_CentroMedico_ID == 0)
        {
			setC_Location_ID (0);
			setEXME_CentroMedico_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_CentroMedico (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CentroMedico[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1)
			 throw new IllegalArgumentException ("C_Location_ID is mandatory.");
		set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Medical Center.
		@param EXME_CentroMedico_ID 
		medical Center
	  */
	public void setEXME_CentroMedico_ID (int EXME_CentroMedico_ID)
	{
		if (EXME_CentroMedico_ID < 1)
			 throw new IllegalArgumentException ("EXME_CentroMedico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CentroMedico_ID, Integer.valueOf(EXME_CentroMedico_ID));
	}

	/** Get Medical Center.
		@return medical Center
	  */
	public int getEXME_CentroMedico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CentroMedico_ID);
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

	/** Set Medical Center Telephone.
		@param TelCentroMedico 
		Medical Center Telephone
	  */
	public void setTelCentroMedico (String TelCentroMedico)
	{
		set_Value (COLUMNNAME_TelCentroMedico, TelCentroMedico);
	}

	/** Get Medical Center Telephone.
		@return Medical Center Telephone
	  */
	public String getTelCentroMedico () 
	{
		return (String)get_Value(COLUMNNAME_TelCentroMedico);
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