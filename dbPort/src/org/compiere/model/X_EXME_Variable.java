/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Variable
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Variable extends PO implements I_EXME_Variable, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Variable (Properties ctx, int EXME_Variable_ID, String trxName)
    {
      super (ctx, EXME_Variable_ID, trxName);
      /** if (EXME_Variable_ID == 0)
        {
			setEXME_Variable_ID (0);
			setName (null);
			setSecuencia (Env.ZERO);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Variable (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Variable[")
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

	/** Set Variable.
		@param EXME_Variable_ID Variable	  */
	public void setEXME_Variable_ID (int EXME_Variable_ID)
	{
		if (EXME_Variable_ID < 1)
			 throw new IllegalArgumentException ("EXME_Variable_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Variable_ID, Integer.valueOf(EXME_Variable_ID));
	}

	/** Get Variable.
		@return Variable	  */
	public int getEXME_Variable_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Variable_ID);
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

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia)
	{
		if (Secuencia == null)
			throw new IllegalArgumentException ("Secuencia is mandatory.");
		set_Value (COLUMNNAME_Secuencia, Secuencia);
	}

	/** Get Sequence.
		@return Sequence
	  */
	public BigDecimal getSecuencia () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Secuencia);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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