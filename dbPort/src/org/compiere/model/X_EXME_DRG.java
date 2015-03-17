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

/** Generated Model for EXME_DRG
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_DRG extends PO implements I_EXME_DRG, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DRG (Properties ctx, int EXME_DRG_ID, String trxName)
    {
      super (ctx, EXME_DRG_ID, trxName);
      /** if (EXME_DRG_ID == 0)
        {
			setEXME_DRG_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_DRG (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_DRG[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Arithmetic.
		@param Arithmetic Arithmetic	  */
	public void setArithmetic (BigDecimal Arithmetic)
	{
		set_Value (COLUMNNAME_Arithmetic, Arithmetic);
	}

	/** Get Arithmetic.
		@return Arithmetic	  */
	public BigDecimal getArithmetic () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Arithmetic);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set DRG Code ID.
		@param EXME_DRG_ID DRG Code ID	  */
	public void setEXME_DRG_ID (int EXME_DRG_ID)
	{
		if (EXME_DRG_ID < 1)
			 throw new IllegalArgumentException ("EXME_DRG_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DRG_ID, Integer.valueOf(EXME_DRG_ID));
	}

	/** Get DRG Code ID.
		@return DRG Code ID	  */
	public int getEXME_DRG_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DRG_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Geometric.
		@param Geometric Geometric	  */
	public void setGeometric (BigDecimal Geometric)
	{
		set_Value (COLUMNNAME_Geometric, Geometric);
	}

	/** Get Geometric.
		@return Geometric	  */
	public BigDecimal getGeometric () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Geometric);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Version.
		@param Version 
		Version of the table definition
	  */
	public void setVersion (int Version)
	{
		set_Value (COLUMNNAME_Version, Integer.valueOf(Version));
	}

	/** Get Version.
		@return Version of the table definition
	  */
	public int getVersion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Version);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Weight.
		@param Weight 
		Weight of a product
	  */
	public void setWeight (BigDecimal Weight)
	{
		set_Value (COLUMNNAME_Weight, Weight);
	}

	/** Get Weight.
		@return Weight of a product
	  */
	public BigDecimal getWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Weight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Year.
		@param Year 
		Calendar Year
	  */
	public void setYear (int Year)
	{
		set_Value (COLUMNNAME_Year, Integer.valueOf(Year));
	}

	/** Get Year.
		@return Calendar Year
	  */
	public int getYear () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Year);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}