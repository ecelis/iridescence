/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for PHR_PacCuest
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_PacCuest extends PO implements I_PHR_PacCuest, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_PacCuest (Properties ctx, int PHR_PacCuest_ID, String trxName)
    {
      super (ctx, PHR_PacCuest_ID, trxName);
      /** if (PHR_PacCuest_ID == 0)
        {
			setName (null);
			setPHR_PacCuest_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_PHR_PacCuest (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_PacCuest[")
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

	/** Set Patient Questionaire.
		@param PHR_PacCuest_ID Patient Questionaire	  */
	public void setPHR_PacCuest_ID (int PHR_PacCuest_ID)
	{
		if (PHR_PacCuest_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacCuest_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_PacCuest_ID, Integer.valueOf(PHR_PacCuest_ID));
	}

	/** Get Patient Questionaire.
		@return Patient Questionaire	  */
	public int getPHR_PacCuest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacCuest_ID);
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