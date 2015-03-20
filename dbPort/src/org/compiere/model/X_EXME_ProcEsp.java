/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ProcEsp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProcEsp extends PO implements I_EXME_ProcEsp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProcEsp (Properties ctx, int EXME_ProcEsp_ID, String trxName)
    {
      super (ctx, EXME_ProcEsp_ID, trxName);
      /** if (EXME_ProcEsp_ID == 0)
        {
			setEXME_ProcEsp_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProcEsp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProcEsp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Special Procedures.
		@param EXME_ProcEsp_ID Special Procedures	  */
	public void setEXME_ProcEsp_ID (int EXME_ProcEsp_ID)
	{
		if (EXME_ProcEsp_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProcEsp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProcEsp_ID, Integer.valueOf(EXME_ProcEsp_ID));
	}

	/** Get Special Procedures.
		@return Special Procedures	  */
	public int getEXME_ProcEsp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcEsp_ID);
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