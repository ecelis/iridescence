/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_CuestEsp
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_CuestEsp extends PO implements I_EXME_CuestEsp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CuestEsp (Properties ctx, int EXME_CuestEsp_ID, String trxName)
    {
      super (ctx, EXME_CuestEsp_ID, trxName);
      /** if (EXME_CuestEsp_ID == 0)
        {
			setEXME_CuestEsp_ID (0);
			setEXME_Cuestionario_ID (0);
			setEXME_Especialidad_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CuestEsp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CuestEsp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Questionnaire - Specialty.
		@param EXME_CuestEsp_ID 
		Questionnaire - Specialty
	  */
	public void setEXME_CuestEsp_ID (int EXME_CuestEsp_ID)
	{
		if (EXME_CuestEsp_ID < 1)
			 throw new IllegalArgumentException ("EXME_CuestEsp_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CuestEsp_ID, Integer.valueOf(EXME_CuestEsp_ID));
	}

	/** Get Questionnaire - Specialty.
		@return Questionnaire - Specialty
	  */
	public int getEXME_CuestEsp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CuestEsp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cuestionario_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public int getEXME_Cuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getEXME_Cuestionario_ID()));
    }

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Required.
		@param IsRequired 
		Required
	  */
	public void setIsRequired (boolean IsRequired)
	{
		set_Value (COLUMNNAME_IsRequired, Boolean.valueOf(IsRequired));
	}

	/** Get Required.
		@return Required
	  */
	public boolean isRequired () 
	{
		Object oo = get_Value(COLUMNNAME_IsRequired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sequence.
		@param Sequence Sequence	  */
	public void setSequence (int Sequence)
	{
		set_Value (COLUMNNAME_Sequence, Integer.valueOf(Sequence));
	}

	/** Get Sequence.
		@return Sequence	  */
	public int getSequence () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Sequence);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}