/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Funeraria
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Funeraria extends PO implements I_EXME_Funeraria, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Funeraria (Properties ctx, int EXME_Funeraria_ID, String trxName)
    {
      super (ctx, EXME_Funeraria_ID, trxName);
      /** if (EXME_Funeraria_ID == 0)
        {
			setEXME_Funeraria_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Funeraria (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Funeraria[")
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
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
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

	/** Set Funeral.
		@param EXME_Funeraria_ID 
		Funeral
	  */
	public void setEXME_Funeraria_ID (int EXME_Funeraria_ID)
	{
		if (EXME_Funeraria_ID < 1)
			 throw new IllegalArgumentException ("EXME_Funeraria_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Funeraria_ID, Integer.valueOf(EXME_Funeraria_ID));
	}

	/** Get Funeral.
		@return Funeral
	  */
	public int getEXME_Funeraria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Funeraria_ID);
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

	/** Set Responsible.
		@param Responsable 
		Responsible
	  */
	public void setResponsable (String Responsable)
	{
		set_Value (COLUMNNAME_Responsable, Responsable);
	}

	/** Get Responsible.
		@return Responsible
	  */
	public String getResponsable () 
	{
		return (String)get_Value(COLUMNNAME_Responsable);
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