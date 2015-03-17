/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for HL7_Evaluation
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_Evaluation extends PO implements I_HL7_Evaluation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_Evaluation (Properties ctx, int HL7_Evaluation_ID, String trxName)
    {
      super (ctx, HL7_Evaluation_ID, trxName);
      /** if (HL7_Evaluation_ID == 0)
        {
			setHL7_Evaluation_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_HL7_Evaluation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_Evaluation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set HL7 Evaluation.
		@param HL7_Evaluation_ID HL7 Evaluation	  */
	public void setHL7_Evaluation_ID (int HL7_Evaluation_ID)
	{
		if (HL7_Evaluation_ID < 1)
			 throw new IllegalArgumentException ("HL7_Evaluation_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Evaluation_ID, Integer.valueOf(HL7_Evaluation_ID));
	}

	/** Get HL7 Evaluation.
		@return HL7 Evaluation	  */
	public int getHL7_Evaluation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Evaluation_ID);
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