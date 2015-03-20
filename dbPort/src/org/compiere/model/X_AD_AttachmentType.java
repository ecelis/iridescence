/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_AttachmentType
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_AD_AttachmentType extends PO implements I_AD_AttachmentType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_AttachmentType (Properties ctx, int AD_AttachmentType_ID, String trxName)
    {
      super (ctx, AD_AttachmentType_ID, trxName);
      /** if (AD_AttachmentType_ID == 0)
        {
			setAD_AttachmentType_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_AttachmentType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_AttachmentType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Attachment type.
		@param AD_AttachmentType_ID Attachment type	  */
	public void setAD_AttachmentType_ID (int AD_AttachmentType_ID)
	{
		if (AD_AttachmentType_ID < 1)
			 throw new IllegalArgumentException ("AD_AttachmentType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_AttachmentType_ID, Integer.valueOf(AD_AttachmentType_ID));
	}

	/** Get Attachment type.
		@return Attachment type	  */
	public int getAD_AttachmentType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AttachmentType_ID);
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