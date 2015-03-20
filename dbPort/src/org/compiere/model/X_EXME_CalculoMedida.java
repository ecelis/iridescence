/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_CalculoMedida
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CalculoMedida extends PO implements I_EXME_CalculoMedida, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CalculoMedida (Properties ctx, int EXME_CalculoMedida_ID, String trxName)
    {
      super (ctx, EXME_CalculoMedida_ID, trxName);
      /** if (EXME_CalculoMedida_ID == 0)
        {
			setEXME_CalculoMedida_ID (0);
			setIsInpatient (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_CalculoMedida (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CalculoMedida[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Automated Measure Calculation.
		@param EXME_CalculoMedida_ID Automated Measure Calculation	  */
	public void setEXME_CalculoMedida_ID (int EXME_CalculoMedida_ID)
	{
		if (EXME_CalculoMedida_ID < 1)
			 throw new IllegalArgumentException ("EXME_CalculoMedida_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CalculoMedida_ID, Integer.valueOf(EXME_CalculoMedida_ID));
	}

	/** Get Automated Measure Calculation.
		@return Automated Measure Calculation	  */
	public int getEXME_CalculoMedida_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CalculoMedida_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Inpatient.
		@param IsInpatient 
		Is Inpatient
	  */
	public void setIsInpatient (boolean IsInpatient)
	{
		set_Value (COLUMNNAME_IsInpatient, Boolean.valueOf(IsInpatient));
	}

	/** Get Is Inpatient.
		@return Is Inpatient
	  */
	public boolean isInpatient () 
	{
		Object oo = get_Value(COLUMNNAME_IsInpatient);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}