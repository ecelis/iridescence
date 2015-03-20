/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_GroupAcct
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_GroupAcct extends PO implements I_EXME_GroupAcct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_GroupAcct (Properties ctx, int EXME_GroupAcct_ID, String trxName)
    {
      super (ctx, EXME_GroupAcct_ID, trxName);
      /** if (EXME_GroupAcct_ID == 0)
        {
			setAcct_Level (0);
			setEXME_GroupAcct_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_GroupAcct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_GroupAcct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account Level.
		@param Acct_Level 
		The account level in the account schema
	  */
	public void setAcct_Level (int Acct_Level)
	{
		set_Value (COLUMNNAME_Acct_Level, Integer.valueOf(Acct_Level));
	}

	/** Get Account Level.
		@return The account level in the account schema
	  */
	public int getAcct_Level () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Acct_Level);
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

	/** Set Group Acct.
		@param EXME_GroupAcct_ID Group Acct	  */
	public void setEXME_GroupAcct_ID (int EXME_GroupAcct_ID)
	{
		if (EXME_GroupAcct_ID < 1)
			 throw new IllegalArgumentException ("EXME_GroupAcct_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_GroupAcct_ID, Integer.valueOf(EXME_GroupAcct_ID));
	}

	/** Get Group Acct.
		@return Group Acct	  */
	public int getEXME_GroupAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GroupAcct_ID);
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

	/** Set Reference to the parent group.
		@param Ref_GroupAcct_ID Reference to the parent group	  */
	public void setRef_GroupAcct_ID (int Ref_GroupAcct_ID)
	{
		if (Ref_GroupAcct_ID < 1) 
			set_Value (COLUMNNAME_Ref_GroupAcct_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_GroupAcct_ID, Integer.valueOf(Ref_GroupAcct_ID));
	}

	/** Get Reference to the parent group.
		@return Reference to the parent group	  */
	public int getRef_GroupAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_GroupAcct_ID);
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