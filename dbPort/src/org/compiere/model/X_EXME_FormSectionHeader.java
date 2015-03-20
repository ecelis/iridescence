/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_FormSectionHeader
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_FormSectionHeader extends PO implements I_EXME_FormSectionHeader, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FormSectionHeader (Properties ctx, int EXME_FormSectionHeader_ID, String trxName)
    {
      super (ctx, EXME_FormSectionHeader_ID, trxName);
      /** if (EXME_FormSectionHeader_ID == 0)
        {
			setEXME_FormSectionHeader_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_FormSectionHeader (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FormSectionHeader[")
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

	/** Set Form Section's Header.
		@param EXME_FormSectionHeader_ID Form Section's Header	  */
	public void setEXME_FormSectionHeader_ID (int EXME_FormSectionHeader_ID)
	{
		if (EXME_FormSectionHeader_ID < 1)
			 throw new IllegalArgumentException ("EXME_FormSectionHeader_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FormSectionHeader_ID, Integer.valueOf(EXME_FormSectionHeader_ID));
	}

	/** Get Form Section's Header.
		@return Form Section's Header	  */
	public int getEXME_FormSectionHeader_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormSectionHeader_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Jasper Report.
		@param JasperReport Jasper Report	  */
	public void setJasperReport (String JasperReport)
	{
		set_Value (COLUMNNAME_JasperReport, JasperReport);
	}

	/** Get Jasper Report.
		@return Jasper Report	  */
	public String getJasperReport () 
	{
		return (String)get_Value(COLUMNNAME_JasperReport);
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

	/** Type AD_Reference_ID=1200632 */
	public static final int TYPE_AD_Reference_ID=1200632;
	/** Discharge = D */
	public static final String TYPE_Discharge = "D";
	/** Emergency = E */
	public static final String TYPE_Emergency = "E";
	/** Format = F */
	public static final String TYPE_Format = "F";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("D") || Type.equals("E") || Type.equals("F")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200632 - D - E - F");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
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