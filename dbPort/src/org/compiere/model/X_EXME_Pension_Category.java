/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Pension_Category
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Pension_Category extends PO implements I_EXME_Pension_Category, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Pension_Category (Properties ctx, int EXME_Pension_Category_ID, String trxName)
    {
      super (ctx, EXME_Pension_Category_ID, trxName);
      /** if (EXME_Pension_Category_ID == 0)
        {
			setEXME_Pension_Category_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Pension_Category (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Pension_Category[")
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

	/** Set Subcategory Description.
		@param Description_Subcategory Subcategory Description	  */
	public void setDescription_Subcategory (String Description_Subcategory)
	{
		set_Value (COLUMNNAME_Description_Subcategory, Description_Subcategory);
	}

	/** Get Subcategory Description.
		@return Subcategory Description	  */
	public String getDescription_Subcategory () 
	{
		return (String)get_Value(COLUMNNAME_Description_Subcategory);
	}

	/** Set Pension Category.
		@param EXME_Pension_Category_ID Pension Category	  */
	public void setEXME_Pension_Category_ID (int EXME_Pension_Category_ID)
	{
		if (EXME_Pension_Category_ID < 1)
			 throw new IllegalArgumentException ("EXME_Pension_Category_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Pension_Category_ID, Integer.valueOf(EXME_Pension_Category_ID));
	}

	/** Get Pension Category.
		@return Pension Category	  */
	public int getEXME_Pension_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pension_Category_ID);
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

	/** Set Subcategory Name.
		@param Name_Subcategory Subcategory Name	  */
	public void setName_Subcategory (String Name_Subcategory)
	{
		set_Value (COLUMNNAME_Name_Subcategory, Name_Subcategory);
	}

	/** Get Subcategory Name.
		@return Subcategory Name	  */
	public String getName_Subcategory () 
	{
		return (String)get_Value(COLUMNNAME_Name_Subcategory);
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