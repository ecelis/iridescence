/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_FormulaSigVital
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_FormulaSigVital extends PO implements I_EXME_FormulaSigVital, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FormulaSigVital (Properties ctx, int EXME_FormulaSigVital_ID, String trxName)
    {
      super (ctx, EXME_FormulaSigVital_ID, trxName);
      /** if (EXME_FormulaSigVital_ID == 0)
        {
			setEXME_FormulaSigVital_ID (0);
			setFormula (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_FormulaSigVital (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FormulaSigVital[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Vital Signs Formula.
		@param EXME_FormulaSigVital_ID Vital Signs Formula	  */
	public void setEXME_FormulaSigVital_ID (int EXME_FormulaSigVital_ID)
	{
		if (EXME_FormulaSigVital_ID < 1)
			 throw new IllegalArgumentException ("EXME_FormulaSigVital_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FormulaSigVital_ID, Integer.valueOf(EXME_FormulaSigVital_ID));
	}

	/** Get Vital Signs Formula.
		@return Vital Signs Formula	  */
	public int getEXME_FormulaSigVital_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormulaSigVital_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Formule.
		@param Formula 
		Substance Formule
	  */
	public void setFormula (String Formula)
	{
		if (Formula == null)
			throw new IllegalArgumentException ("Formula is mandatory.");
		set_Value (COLUMNNAME_Formula, Formula);
	}

	/** Get Formule.
		@return Substance Formule
	  */
	public String getFormula () 
	{
		return (String)get_Value(COLUMNNAME_Formula);
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