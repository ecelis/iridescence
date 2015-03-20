/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Especimen
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Especimen extends PO implements I_EXME_Especimen, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Especimen (Properties ctx, int EXME_Especimen_ID, String trxName)
    {
      super (ctx, EXME_Especimen_ID, trxName);
      /** if (EXME_Especimen_ID == 0)
        {
			setEXME_Especimen_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Especimen (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Especimen[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Alternate Coding System.
		@param AlternateCodingSystem Alternate Coding System	  */
	public void setAlternateCodingSystem (String AlternateCodingSystem)
	{
		set_Value (COLUMNNAME_AlternateCodingSystem, AlternateCodingSystem);
	}

	/** Get Alternate Coding System.
		@return Alternate Coding System	  */
	public String getAlternateCodingSystem () 
	{
		return (String)get_Value(COLUMNNAME_AlternateCodingSystem);
	}

	/** Set Alternate Coding System Version.
		@param AlternateCodingSystemVersion Alternate Coding System Version	  */
	public void setAlternateCodingSystemVersion (String AlternateCodingSystemVersion)
	{
		set_Value (COLUMNNAME_AlternateCodingSystemVersion, AlternateCodingSystemVersion);
	}

	/** Get Alternate Coding System Version.
		@return Alternate Coding System Version	  */
	public String getAlternateCodingSystemVersion () 
	{
		return (String)get_Value(COLUMNNAME_AlternateCodingSystemVersion);
	}

	/** Set Alternate Identifier.
		@param AlternateIdentifier Alternate Identifier	  */
	public void setAlternateIdentifier (String AlternateIdentifier)
	{
		set_Value (COLUMNNAME_AlternateIdentifier, AlternateIdentifier);
	}

	/** Get Alternate Identifier.
		@return Alternate Identifier	  */
	public String getAlternateIdentifier () 
	{
		return (String)get_Value(COLUMNNAME_AlternateIdentifier);
	}

	/** Set Alternate Value.
		@param AlternateValue Alternate Value	  */
	public void setAlternateValue (String AlternateValue)
	{
		set_Value (COLUMNNAME_AlternateValue, AlternateValue);
	}

	/** Get Alternate Value.
		@return Alternate Value	  */
	public String getAlternateValue () 
	{
		return (String)get_Value(COLUMNNAME_AlternateValue);
	}

	/** Set Coding System Version.
		@param CodingSystemVersion Coding System Version	  */
	public void setCodingSystemVersion (String CodingSystemVersion)
	{
		set_Value (COLUMNNAME_CodingSystemVersion, CodingSystemVersion);
	}

	/** Get Coding System Version.
		@return Coding System Version	  */
	public String getCodingSystemVersion () 
	{
		return (String)get_Value(COLUMNNAME_CodingSystemVersion);
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

	/** Set Test Specimen.
		@param EXME_Especimen_ID 
		Test Specimen
	  */
	public void setEXME_Especimen_ID (int EXME_Especimen_ID)
	{
		if (EXME_Especimen_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especimen_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Especimen_ID, Integer.valueOf(EXME_Especimen_ID));
	}

	/** Get Test Specimen.
		@return Test Specimen
	  */
	public int getEXME_Especimen_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especimen_ID);
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