/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Depreciation_Convention
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_A_Depreciation_Convention extends PO implements I_A_Depreciation_Convention, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Depreciation_Convention (Properties ctx, int A_Depreciation_Convention_ID, String trxName)
    {
      super (ctx, A_Depreciation_Convention_ID, trxName);
      /** if (A_Depreciation_Convention_ID == 0)
        {
			setA_Depreciation_Convention_ID (0);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_A_Depreciation_Convention (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_A_Depreciation_Convention[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set A_Depreciation_Convention_ID.
		@param A_Depreciation_Convention_ID A_Depreciation_Convention_ID	  */
	public void setA_Depreciation_Convention_ID (int A_Depreciation_Convention_ID)
	{
		if (A_Depreciation_Convention_ID < 1)
			 throw new IllegalArgumentException ("A_Depreciation_Convention_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Depreciation_Convention_ID, Integer.valueOf(A_Depreciation_Convention_ID));
	}

	/** Get A_Depreciation_Convention_ID.
		@return A_Depreciation_Convention_ID	  */
	public int getA_Depreciation_Convention_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Convention_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Depreciation_Convention_ID()));
    }

	/** Set ConventionType.
		@param ConventionType ConventionType	  */
	public void setConventionType (String ConventionType)
	{
		set_Value (COLUMNNAME_ConventionType, ConventionType);
	}

	/** Get ConventionType.
		@return ConventionType	  */
	public String getConventionType () 
	{
		return (String)get_Value(COLUMNNAME_ConventionType);
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
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Text Message.
		@param TextMsg 
		Text Message
	  */
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	public String getTextMsg () 
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
	}
}