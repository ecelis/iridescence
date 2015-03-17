/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_AdmitRefList
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_AdmitRefList extends PO implements I_EXME_AdmitRefList, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AdmitRefList (Properties ctx, int EXME_AdmitRefList_ID, String trxName)
    {
      super (ctx, EXME_AdmitRefList_ID, trxName);
      /** if (EXME_AdmitRefList_ID == 0)
        {
			setEXME_AdmitRefList_ID (0);
			setName (null);
			setType (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_AdmitRefList (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_AdmitRefList[")
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

	/** Set Admit Reference List.
		@param EXME_AdmitRefList_ID 
		Admit Reference List
	  */
	public void setEXME_AdmitRefList_ID (int EXME_AdmitRefList_ID)
	{
		if (EXME_AdmitRefList_ID < 1)
			 throw new IllegalArgumentException ("EXME_AdmitRefList_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AdmitRefList_ID, Integer.valueOf(EXME_AdmitRefList_ID));
	}

	/** Get Admit Reference List.
		@return Admit Reference List
	  */
	public int getEXME_AdmitRefList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AdmitRefList_ID);
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

	/** Type AD_Reference_ID=1200479 */
	public static final int TYPE_AD_Reference_ID=1200479;
	/** Arrival Mode = AM */
	public static final String TYPE_ArrivalMode = "AM";
	/** Admit Type = AT */
	public static final String TYPE_AdmitType = "AT";
	/** Admit Source = AS */
	public static final String TYPE_AdmitSource = "AS";
	/** Service = SE */
	public static final String TYPE_Service = "SE";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{
		if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
		if (Type.equals("AM") || Type.equals("AT") || Type.equals("AS") || Type.equals("SE")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200479 - AM - AT - AS - SE");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
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