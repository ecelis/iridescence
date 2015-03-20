/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_GenDrug
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_GenDrug extends PO implements I_EXME_GenDrug, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_GenDrug (Properties ctx, int EXME_GenDrug_ID, String trxName)
    {
      super (ctx, EXME_GenDrug_ID, trxName);
      /** if (EXME_GenDrug_ID == 0)
        {
			setDrug_ID (0);
			setEXME_GenDrug_ID (0);
			setGeneric_Name (null);
			setHALF_LIFE_HOURS (0);
			setHALF_LIFE_IS_EMPIRICAL (Env.ZERO);
			setIS_INGREDIENT (false);
			setIS_PRODUCT (false);
			setPREGNANCY_RISK_FACTOR (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_GenDrug (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_GenDrug[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Drug.
		@param Drug_ID Drug	  */
	public void setDrug_ID (int Drug_ID)
	{
		if (Drug_ID < 1)
			 throw new IllegalArgumentException ("Drug_ID is mandatory.");
		set_Value (COLUMNNAME_Drug_ID, Integer.valueOf(Drug_ID));
	}

	/** Get Drug.
		@return Drug	  */
	public int getDrug_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Drug_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generic Drug.
		@param EXME_GenDrug_ID Generic Drug	  */
	public void setEXME_GenDrug_ID (int EXME_GenDrug_ID)
	{
		if (EXME_GenDrug_ID < 1)
			 throw new IllegalArgumentException ("EXME_GenDrug_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_GenDrug_ID, Integer.valueOf(EXME_GenDrug_ID));
	}

	/** Get Generic Drug.
		@return Generic Drug	  */
	public int getEXME_GenDrug_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenDrug_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generic Name.
		@param Generic_Name Generic Name	  */
	public void setGeneric_Name (String Generic_Name)
	{
		if (Generic_Name == null)
			throw new IllegalArgumentException ("Generic_Name is mandatory.");
		set_Value (COLUMNNAME_Generic_Name, Generic_Name);
	}

	/** Get Generic Name.
		@return Generic Name	  */
	public String getGeneric_Name () 
	{
		return (String)get_Value(COLUMNNAME_Generic_Name);
	}

	/** Set HALF_LIFE_HOURS.
		@param HALF_LIFE_HOURS HALF_LIFE_HOURS	  */
	public void setHALF_LIFE_HOURS (int HALF_LIFE_HOURS)
	{
		set_Value (COLUMNNAME_HALF_LIFE_HOURS, Integer.valueOf(HALF_LIFE_HOURS));
	}

	/** Get HALF_LIFE_HOURS.
		@return HALF_LIFE_HOURS	  */
	public int getHALF_LIFE_HOURS () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HALF_LIFE_HOURS);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HALF_LIFE_IS_EMPIRICAL.
		@param HALF_LIFE_IS_EMPIRICAL HALF_LIFE_IS_EMPIRICAL	  */
	public void setHALF_LIFE_IS_EMPIRICAL (BigDecimal HALF_LIFE_IS_EMPIRICAL)
	{
		if (HALF_LIFE_IS_EMPIRICAL == null)
			throw new IllegalArgumentException ("HALF_LIFE_IS_EMPIRICAL is mandatory.");
		set_Value (COLUMNNAME_HALF_LIFE_IS_EMPIRICAL, HALF_LIFE_IS_EMPIRICAL);
	}

	/** Get HALF_LIFE_IS_EMPIRICAL.
		@return HALF_LIFE_IS_EMPIRICAL	  */
	public BigDecimal getHALF_LIFE_IS_EMPIRICAL () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_HALF_LIFE_IS_EMPIRICAL);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set IS_INGREDIENT.
		@param IS_INGREDIENT IS_INGREDIENT	  */
	public void setIS_INGREDIENT (boolean IS_INGREDIENT)
	{
		set_Value (COLUMNNAME_IS_INGREDIENT, Boolean.valueOf(IS_INGREDIENT));
	}

	/** Get IS_INGREDIENT.
		@return IS_INGREDIENT	  */
	public boolean is_INGREDIENT () 
	{
		Object oo = get_Value(COLUMNNAME_IS_INGREDIENT);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IS_PRODUCT.
		@param IS_PRODUCT IS_PRODUCT	  */
	public void setIS_PRODUCT (boolean IS_PRODUCT)
	{
		set_Value (COLUMNNAME_IS_PRODUCT, Boolean.valueOf(IS_PRODUCT));
	}

	/** Get IS_PRODUCT.
		@return IS_PRODUCT	  */
	public boolean is_PRODUCT () 
	{
		Object oo = get_Value(COLUMNNAME_IS_PRODUCT);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Manufacturer Generic Name.
		@param MANUFACTURER_GENERIC_NAME Manufacturer Generic Name	  */
	public void setMANUFACTURER_GENERIC_NAME (String MANUFACTURER_GENERIC_NAME)
	{
		set_Value (COLUMNNAME_MANUFACTURER_GENERIC_NAME, MANUFACTURER_GENERIC_NAME);
	}

	/** Get Manufacturer Generic Name.
		@return Manufacturer Generic Name	  */
	public String getMANUFACTURER_GENERIC_NAME () 
	{
		return (String)get_Value(COLUMNNAME_MANUFACTURER_GENERIC_NAME);
	}

	/** Set Obsolete Date.
		@param Obsolete_Date Obsolete Date	  */
	public void setObsolete_Date (Timestamp Obsolete_Date)
	{
		set_Value (COLUMNNAME_Obsolete_Date, Obsolete_Date);
	}

	/** Get Obsolete Date.
		@return Obsolete Date	  */
	public Timestamp getObsolete_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Obsolete_Date);
	}

	/** Set PREGNANCY_RISK_FACTOR.
		@param PREGNANCY_RISK_FACTOR PREGNANCY_RISK_FACTOR	  */
	public void setPREGNANCY_RISK_FACTOR (String PREGNANCY_RISK_FACTOR)
	{
		if (PREGNANCY_RISK_FACTOR == null)
			throw new IllegalArgumentException ("PREGNANCY_RISK_FACTOR is mandatory.");
		set_Value (COLUMNNAME_PREGNANCY_RISK_FACTOR, PREGNANCY_RISK_FACTOR);
	}

	/** Get PREGNANCY_RISK_FACTOR.
		@return PREGNANCY_RISK_FACTOR	  */
	public String getPREGNANCY_RISK_FACTOR () 
	{
		return (String)get_Value(COLUMNNAME_PREGNANCY_RISK_FACTOR);
	}

	/** Set RXCUI.
		@param RXCUI RXCUI	  */
	public void setRXCUI (String RXCUI)
	{
		set_Value (COLUMNNAME_RXCUI, RXCUI);
	}

	/** Get RXCUI.
		@return RXCUI	  */
	public String getRXCUI () 
	{
		return (String)get_Value(COLUMNNAME_RXCUI);
	}

	/** Set RX_OTC_STATUS_CODE.
		@param RX_OTC_STATUS_CODE RX_OTC_STATUS_CODE	  */
	public void setRX_OTC_STATUS_CODE (String RX_OTC_STATUS_CODE)
	{
		set_Value (COLUMNNAME_RX_OTC_STATUS_CODE, RX_OTC_STATUS_CODE);
	}

	/** Get RX_OTC_STATUS_CODE.
		@return RX_OTC_STATUS_CODE	  */
	public String getRX_OTC_STATUS_CODE () 
	{
		return (String)get_Value(COLUMNNAME_RX_OTC_STATUS_CODE);
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