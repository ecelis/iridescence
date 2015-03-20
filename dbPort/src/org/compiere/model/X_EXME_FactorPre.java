/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_FactorPre
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_FactorPre extends PO implements I_EXME_FactorPre, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FactorPre (Properties ctx, int EXME_FactorPre_ID, String trxName)
    {
      super (ctx, EXME_FactorPre_ID, trxName);
      /** if (EXME_FactorPre_ID == 0)
        {
			setEXME_FactorPre_ID (0);
			setIsPriceReduction (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_FactorPre (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_FactorPre[")
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

	/** Set Price Factor.
		@param EXME_FactorPre_ID 
		Sales Price Factor
	  */
	public void setEXME_FactorPre_ID (int EXME_FactorPre_ID)
	{
		if (EXME_FactorPre_ID < 1)
			 throw new IllegalArgumentException ("EXME_FactorPre_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_FactorPre_ID, Integer.valueOf(EXME_FactorPre_ID));
	}

	/** Get Price Factor.
		@return Sales Price Factor
	  */
	public int getEXME_FactorPre_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FactorPre_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Allows Price Reduction.
		@param IsPriceReduction Allows Price Reduction	  */
	public void setIsPriceReduction (boolean IsPriceReduction)
	{
		set_Value (COLUMNNAME_IsPriceReduction, Boolean.valueOf(IsPriceReduction));
	}

	/** Get Allows Price Reduction.
		@return Allows Price Reduction	  */
	public boolean isPriceReduction () 
	{
		Object oo = get_Value(COLUMNNAME_IsPriceReduction);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Percentage Allowed.
		@param PercentAllowed Percentage Allowed	  */
	public void setPercentAllowed (BigDecimal PercentAllowed)
	{
		set_Value (COLUMNNAME_PercentAllowed, PercentAllowed);
	}

	/** Get Percentage Allowed.
		@return Percentage Allowed	  */
	public BigDecimal getPercentAllowed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PercentAllowed);
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