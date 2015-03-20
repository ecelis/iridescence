/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_SystemBody
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_SystemBody extends PO implements I_EXME_SystemBody, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_SystemBody (Properties ctx, int EXME_SystemBody_ID, String trxName)
    {
      super (ctx, EXME_SystemBody_ID, trxName);
      /** if (EXME_SystemBody_ID == 0)
        {
			setEXME_SystemBody_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_SystemBody (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_SystemBody[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Back Image.
		@param BackImage Back Image	  */
	public void setBackImage (String BackImage)
	{
		set_Value (COLUMNNAME_BackImage, BackImage);
	}

	/** Get Back Image.
		@return Back Image	  */
	public String getBackImage () 
	{
		return (String)get_Value(COLUMNNAME_BackImage);
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

	/** Set System Body.
		@param EXME_SystemBody_ID System Body	  */
	public void setEXME_SystemBody_ID (int EXME_SystemBody_ID)
	{
		if (EXME_SystemBody_ID < 1)
			 throw new IllegalArgumentException ("EXME_SystemBody_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SystemBody_ID, Integer.valueOf(EXME_SystemBody_ID));
	}

	/** Get System Body.
		@return System Body	  */
	public int getEXME_SystemBody_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SystemBody_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Front Image.
		@param FrontImage 
		Front Image
	  */
	public void setFrontImage (String FrontImage)
	{
		set_Value (COLUMNNAME_FrontImage, FrontImage);
	}

	/** Get Front Image.
		@return Front Image
	  */
	public String getFrontImage () 
	{
		return (String)get_Value(COLUMNNAME_FrontImage);
	}

	/** Set Left Image.
		@param LeftImage Left Image	  */
	public void setLeftImage (String LeftImage)
	{
		set_Value (COLUMNNAME_LeftImage, LeftImage);
	}

	/** Get Left Image.
		@return Left Image	  */
	public String getLeftImage () 
	{
		return (String)get_Value(COLUMNNAME_LeftImage);
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

	/** Set Right Image.
		@param RightImage Right Image	  */
	public void setRightImage (String RightImage)
	{
		set_Value (COLUMNNAME_RightImage, RightImage);
	}

	/** Get Right Image.
		@return Right Image	  */
	public String getRightImage () 
	{
		return (String)get_Value(COLUMNNAME_RightImage);
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