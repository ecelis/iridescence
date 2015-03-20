/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for HL7_Ref_List
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_Ref_List extends PO implements I_HL7_Ref_List, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_Ref_List (Properties ctx, int HL7_Ref_List_ID, String trxName)
    {
      super (ctx, HL7_Ref_List_ID, trxName);
      /** if (HL7_Ref_List_ID == 0)
        {
			setHL7_Ref_List_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_Ref_List (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_Ref_List[")
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

	/** Set HL7 Reference List.
		@param HL7_Ref_List_ID HL7 Reference List	  */
	public void setHL7_Ref_List_ID (int HL7_Ref_List_ID)
	{
		if (HL7_Ref_List_ID < 1)
			 throw new IllegalArgumentException ("HL7_Ref_List_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Ref_List_ID, Integer.valueOf(HL7_Ref_List_ID));
	}

	/** Get HL7 Reference List.
		@return HL7 Reference List	  */
	public int getHL7_Ref_List_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Ref_List_ID);
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