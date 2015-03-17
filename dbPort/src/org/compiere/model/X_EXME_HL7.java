/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_HL7
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_HL7 extends PO implements I_EXME_HL7, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_HL7 (Properties ctx, int EXME_HL7_ID, String trxName)
    {
      super (ctx, EXME_HL7_ID, trxName);
      /** if (EXME_HL7_ID == 0)
        {
			setEXME_HL7_ID (0);
			setHostHL7 (null);
			setName (null);
			setPortHL7 (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_HL7 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_HL7[")
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

	/** Set HL7 Identifier.
		@param EXME_HL7_ID 
		HL7 Identifier
	  */
	public void setEXME_HL7_ID (int EXME_HL7_ID)
	{
		if (EXME_HL7_ID < 1)
			 throw new IllegalArgumentException ("EXME_HL7_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_HL7_ID, Integer.valueOf(EXME_HL7_ID));
	}

	/** Get HL7 Identifier.
		@return HL7 Identifier
	  */
	public int getEXME_HL7_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_HL7_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HL7 Server.
		@param HostHL7 
		HL7 Server
	  */
	public void setHostHL7 (String HostHL7)
	{
		if (HostHL7 == null)
			throw new IllegalArgumentException ("HostHL7 is mandatory.");
		set_Value (COLUMNNAME_HostHL7, HostHL7);
	}

	/** Get HL7 Server.
		@return HL7 Server
	  */
	public String getHostHL7 () 
	{
		return (String)get_Value(COLUMNNAME_HostHL7);
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

	/** Set Port For HL7.
		@param PortHL7 
		Host's Port For HL7 Message Reception
	  */
	public void setPortHL7 (String PortHL7)
	{
		if (PortHL7 == null)
			throw new IllegalArgumentException ("PortHL7 is mandatory.");
		set_Value (COLUMNNAME_PortHL7, PortHL7);
	}

	/** Get Port For HL7.
		@return Host's Port For HL7 Message Reception
	  */
	public String getPortHL7 () 
	{
		return (String)get_Value(COLUMNNAME_PortHL7);
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