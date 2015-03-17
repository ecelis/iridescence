/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ProcAnestesico
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ProcAnestesico extends PO implements I_EXME_ProcAnestesico, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ProcAnestesico (Properties ctx, int EXME_ProcAnestesico_ID, String trxName)
    {
      super (ctx, EXME_ProcAnestesico_ID, trxName);
      /** if (EXME_ProcAnestesico_ID == 0)
        {
			setEXME_ProcAnestesico_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ProcAnestesico (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ProcAnestesico[")
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

	/** Set Anesthetic Procedure.
		@param EXME_ProcAnestesico_ID 
		Anesthetic Procedure
	  */
	public void setEXME_ProcAnestesico_ID (int EXME_ProcAnestesico_ID)
	{
		if (EXME_ProcAnestesico_ID < 1)
			 throw new IllegalArgumentException ("EXME_ProcAnestesico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ProcAnestesico_ID, Integer.valueOf(EXME_ProcAnestesico_ID));
	}

	/** Get Anesthetic Procedure.
		@return Anesthetic Procedure
	  */
	public int getEXME_ProcAnestesico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProcAnestesico_ID);
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