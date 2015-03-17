/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_PatientRel_Diag
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PatientRel_Diag extends PO implements I_EXME_PatientRel_Diag, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PatientRel_Diag (Properties ctx, int EXME_PatientRel_Diag_ID, String trxName)
    {
      super (ctx, EXME_PatientRel_Diag_ID, trxName);
      /** if (EXME_PatientRel_Diag_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_PatientRel_Diag_ID (0);
			setEXME_PatientRel_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PatientRel_Diag (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PatientRel_Diag[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnoses of the Patient's Relative.
		@param EXME_PatientRel_Diag_ID Diagnoses of the Patient's Relative	  */
	public void setEXME_PatientRel_Diag_ID (int EXME_PatientRel_Diag_ID)
	{
		if (EXME_PatientRel_Diag_ID < 1)
			 throw new IllegalArgumentException ("EXME_PatientRel_Diag_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PatientRel_Diag_ID, Integer.valueOf(EXME_PatientRel_Diag_ID));
	}

	/** Get Diagnoses of the Patient's Relative.
		@return Diagnoses of the Patient's Relative	  */
	public int getEXME_PatientRel_Diag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PatientRel_Diag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient's Relative.
		@param EXME_PatientRel_ID Patient's Relative	  */
	public void setEXME_PatientRel_ID (int EXME_PatientRel_ID)
	{
		if (EXME_PatientRel_ID < 1)
			 throw new IllegalArgumentException ("EXME_PatientRel_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PatientRel_ID, Integer.valueOf(EXME_PatientRel_ID));
	}

	/** Get Patient's Relative.
		@return Patient's Relative	  */
	public int getEXME_PatientRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PatientRel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}