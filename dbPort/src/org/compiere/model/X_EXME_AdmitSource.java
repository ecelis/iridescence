/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_AdmitSource
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_AdmitSource extends PO implements I_EXME_AdmitSource, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AdmitSource (Properties ctx, int EXME_AdmitSource_ID, String trxName)
    {
      super (ctx, EXME_AdmitSource_ID, trxName);
      /** if (EXME_AdmitSource_ID == 0)
        {
			setEXME_AdmitSource_ID (0);
			setIsDefault (false);
			setIsNewBorn (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_AdmitSource (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AdmitSource[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Claim Code.
		@param ClaimCode Claim Code	  */
	public void setClaimCode (String ClaimCode)
	{
		set_Value (COLUMNNAME_ClaimCode, ClaimCode);
	}

	/** Get Claim Code.
		@return Claim Code	  */
	public String getClaimCode () 
	{
		return (String)get_Value(COLUMNNAME_ClaimCode);
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

	/** Set Admit Source.
		@param EXME_AdmitSource_ID 
		Admit Source of the patient account
	  */
	public void setEXME_AdmitSource_ID (int EXME_AdmitSource_ID)
	{
		if (EXME_AdmitSource_ID < 1)
			 throw new IllegalArgumentException ("EXME_AdmitSource_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AdmitSource_ID, Integer.valueOf(EXME_AdmitSource_ID));
	}

	/** Get Admit Source.
		@return Admit Source of the patient account
	  */
	public int getEXME_AdmitSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AdmitSource_ID);
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

	/** Set Is New Born.
		@param IsNewBorn 
		Is New Born
	  */
	public void setIsNewBorn (boolean IsNewBorn)
	{
		set_Value (COLUMNNAME_IsNewBorn, Boolean.valueOf(IsNewBorn));
	}

	/** Get Is New Born.
		@return Is New Born
	  */
	public boolean isNewBorn () 
	{
		Object oo = get_Value(COLUMNNAME_IsNewBorn);
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