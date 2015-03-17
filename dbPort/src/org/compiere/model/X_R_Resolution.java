/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for R_Resolution
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_R_Resolution extends PO implements I_R_Resolution, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_R_Resolution (Properties ctx, int R_Resolution_ID, String trxName)
    {
      super (ctx, R_Resolution_ID, trxName);
      /** if (R_Resolution_ID == 0)
        {
			setName (null);
			setR_Resolution_ID (0);
        } */
    }

    /** Load Constructor */
    public X_R_Resolution (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_R_Resolution[")
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
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

	/** Set Resolution.
		@param R_Resolution_ID 
		Request Resolution
	  */
	public void setR_Resolution_ID (int R_Resolution_ID)
	{
		if (R_Resolution_ID < 1)
			 throw new IllegalArgumentException ("R_Resolution_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_Resolution_ID, Integer.valueOf(R_Resolution_ID));
	}

	/** Get Resolution.
		@return Request Resolution
	  */
	public int getR_Resolution_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Resolution_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}