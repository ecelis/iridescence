/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_DischargeStatus
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_DischargeStatus extends PO implements I_EXME_DischargeStatus, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DischargeStatus (Properties ctx, int EXME_DischargeStatus_ID, String trxName)
    {
      super (ctx, EXME_DischargeStatus_ID, trxName);
      /** if (EXME_DischargeStatus_ID == 0)
        {
			setEXME_DischargeStatus_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_DischargeStatus (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_DischargeStatus[")
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

	/** Set Disposition.
		@param EXME_DischargeStatus_ID 
		Discharge Status
	  */
	public void setEXME_DischargeStatus_ID (int EXME_DischargeStatus_ID)
	{
		if (EXME_DischargeStatus_ID < 1)
			 throw new IllegalArgumentException ("EXME_DischargeStatus_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DischargeStatus_ID, Integer.valueOf(EXME_DischargeStatus_ID));
	}

	/** Get Disposition.
		@return Discharge Status
	  */
	public int getEXME_DischargeStatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DischargeStatus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exclude.
		@param IsExclude 
		Exclude access to the data - if not selected Include access to the data
	  */
	public void setIsExclude (boolean IsExclude)
	{
		set_Value (COLUMNNAME_IsExclude, Boolean.valueOf(IsExclude));
	}

	/** Get Exclude.
		@return Exclude access to the data - if not selected Include access to the data
	  */
	public boolean isExclude () 
	{
		Object oo = get_Value(COLUMNNAME_IsExclude);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Type AD_Reference_ID=1200633 */
	public static final int TYPE_AD_Reference_ID=1200633;
	/** Expired = E */
	public static final String TYPE_Expired = "E";
	/** Transfer = T */
	public static final String TYPE_Transfer = "T";
	/** Set Type.
		@param Type 
		Type
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("E") || Type.equals("T")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200633 - E - T");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type
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