/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_TipoMedico
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_TipoMedico extends PO implements I_EXME_TipoMedico, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TipoMedico (Properties ctx, int EXME_TipoMedico_ID, String trxName)
    {
      super (ctx, EXME_TipoMedico_ID, trxName);
      /** if (EXME_TipoMedico_ID == 0)
        {
			setEXME_TipoMedico_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_TipoMedico (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TipoMedico[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Withholding Tax Deductible by Receipt Payments.
		@param DedRetenImptoHon 
		Withholding Tax deductible by receipt payment
	  */
	public void setDedRetenImptoHon (BigDecimal DedRetenImptoHon)
	{
		set_Value (COLUMNNAME_DedRetenImptoHon, DedRetenImptoHon);
	}

	/** Get Withholding Tax Deductible by Receipt Payments.
		@return Withholding Tax deductible by receipt payment
	  */
	public BigDecimal getDedRetenImptoHon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DedRetenImptoHon);
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

	/** Set Type of Doctor.
		@param EXME_TipoMedico_ID 
		Type of Doctor
	  */
	public void setEXME_TipoMedico_ID (int EXME_TipoMedico_ID)
	{
		if (EXME_TipoMedico_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoMedico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TipoMedico_ID, Integer.valueOf(EXME_TipoMedico_ID));
	}

	/** Get Type of Doctor.
		@return Type of Doctor
	  */
	public int getEXME_TipoMedico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoMedico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Max withholded by Administration.
		@param MaxRetenAdmon 
		Maximum amount withholded by administration
	  */
	public void setMaxRetenAdmon (BigDecimal MaxRetenAdmon)
	{
		set_Value (COLUMNNAME_MaxRetenAdmon, MaxRetenAdmon);
	}

	/** Get Max withholded by Administration.
		@return Maximum amount withholded by administration
	  */
	public BigDecimal getMaxRetenAdmon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxRetenAdmon);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Max tax withholded by payment notes.
		@param MaxRetenImptoHon 
		Max. tax withholded by payment notes
	  */
	public void setMaxRetenImptoHon (BigDecimal MaxRetenImptoHon)
	{
		set_Value (COLUMNNAME_MaxRetenImptoHon, MaxRetenImptoHon);
	}

	/** Get Max tax withholded by payment notes.
		@return Max. tax withholded by payment notes
	  */
	public BigDecimal getMaxRetenImptoHon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxRetenImptoHon);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min withholded by administration.
		@param MinRetenAdmon 
		Min amount withholded by administration
	  */
	public void setMinRetenAdmon (BigDecimal MinRetenAdmon)
	{
		set_Value (COLUMNNAME_MinRetenAdmon, MinRetenAdmon);
	}

	/** Get Min withholded by administration.
		@return Min amount withholded by administration
	  */
	public BigDecimal getMinRetenAdmon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinRetenAdmon);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min tax withholded by notes payments.
		@param MinRetenImptoHon 
		Minimum tax withholded by notes payments
	  */
	public void setMinRetenImptoHon (BigDecimal MinRetenImptoHon)
	{
		set_Value (COLUMNNAME_MinRetenImptoHon, MinRetenImptoHon);
	}

	/** Get Min tax withholded by notes payments.
		@return Minimum tax withholded by notes payments
	  */
	public BigDecimal getMinRetenImptoHon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinRetenImptoHon);
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

	/** Set Withholded by Administration.
		@param RetenAdmon 
		Withholded by Administration
	  */
	public void setRetenAdmon (BigDecimal RetenAdmon)
	{
		set_Value (COLUMNNAME_RetenAdmon, RetenAdmon);
	}

	/** Get Withholded by Administration.
		@return Withholded by Administration
	  */
	public BigDecimal getRetenAdmon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RetenAdmon);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Wthholded Tax by Payment Note.
		@param RetenImptoHon 
		Withholded tax by Payment Note
	  */
	public void setRetenImptoHon (BigDecimal RetenImptoHon)
	{
		set_Value (COLUMNNAME_RetenImptoHon, RetenImptoHon);
	}

	/** Get Wthholded Tax by Payment Note.
		@return Withholded tax by Payment Note
	  */
	public BigDecimal getRetenImptoHon () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RetenImptoHon);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Other Withholding.
		@param RetenOtros 
		Other withholding
	  */
	public void setRetenOtros (BigDecimal RetenOtros)
	{
		set_Value (COLUMNNAME_RetenOtros, RetenOtros);
	}

	/** Get Other Withholding.
		@return Other withholding
	  */
	public BigDecimal getRetenOtros () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RetenOtros);
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