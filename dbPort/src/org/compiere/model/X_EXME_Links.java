/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Links
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Links extends PO implements I_EXME_Links, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Links (Properties ctx, int EXME_Links_ID, String trxName)
    {
      super (ctx, EXME_Links_ID, trxName);
      /** if (EXME_Links_ID == 0)
        {
			setEXME_Links_ID (0);
			setName (null);
			setURL (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Links (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Links[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Links.
		@param EXME_Links_ID 
		Web Links
	  */
	public void setEXME_Links_ID (int EXME_Links_ID)
	{
		if (EXME_Links_ID < 1)
			 throw new IllegalArgumentException ("EXME_Links_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Links_ID, Integer.valueOf(EXME_Links_ID));
	}

	/** Get Links.
		@return Web Links
	  */
	public int getEXME_Links_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Links_ID);
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

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL)
	{
		if (URL == null)
			throw new IllegalArgumentException ("URL is mandatory.");
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
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