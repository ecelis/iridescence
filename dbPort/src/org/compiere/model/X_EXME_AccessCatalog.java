/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_AccessCatalog
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_AccessCatalog extends PO implements I_EXME_AccessCatalog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AccessCatalog (Properties ctx, int EXME_AccessCatalog_ID, String trxName)
    {
      super (ctx, EXME_AccessCatalog_ID, trxName);
      /** if (EXME_AccessCatalog_ID == 0)
        {
			setEXME_AccessCatalog_ID (0);
			setMultiAccess (0);
			setName (null);
			setOriginalAccess (0);
			setUniqueAccess (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_AccessCatalog (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AccessCatalog[")
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

	/** Set Access Catalog.
		@param EXME_AccessCatalog_ID 
		Access Catalog
	  */
	public void setEXME_AccessCatalog_ID (int EXME_AccessCatalog_ID)
	{
		if (EXME_AccessCatalog_ID < 1)
			 throw new IllegalArgumentException ("EXME_AccessCatalog_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AccessCatalog_ID, Integer.valueOf(EXME_AccessCatalog_ID));
	}

	/** Get Access Catalog.
		@return Access Catalog
	  */
	public int getEXME_AccessCatalog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AccessCatalog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Multi Access.
		@param MultiAccess 
		Multi Access
	  */
	public void setMultiAccess (int MultiAccess)
	{
		set_Value (COLUMNNAME_MultiAccess, Integer.valueOf(MultiAccess));
	}

	/** Get Multi Access.
		@return Multi Access
	  */
	public int getMultiAccess () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MultiAccess);
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

	/** Set OriginalAccess.
		@param OriginalAccess OriginalAccess	  */
	public void setOriginalAccess (int OriginalAccess)
	{
		set_Value (COLUMNNAME_OriginalAccess, Integer.valueOf(OriginalAccess));
	}

	/** Get OriginalAccess.
		@return OriginalAccess	  */
	public int getOriginalAccess () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_OriginalAccess);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UniqueAccess.
		@param UniqueAccess 
		UniqueAccess
	  */
	public void setUniqueAccess (int UniqueAccess)
	{
		set_Value (COLUMNNAME_UniqueAccess, Integer.valueOf(UniqueAccess));
	}

	/** Get UniqueAccess.
		@return UniqueAccess
	  */
	public int getUniqueAccess () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UniqueAccess);
		if (ii == null)
			 return 0;
		return ii.intValue();
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