/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_DiagnosisTypeDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_DiagnosisTypeDet extends PO implements I_EXME_DiagnosisTypeDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DiagnosisTypeDet (Properties ctx, int EXME_DiagnosisTypeDet_ID, String trxName)
    {
      super (ctx, EXME_DiagnosisTypeDet_ID, trxName);
      /** if (EXME_DiagnosisTypeDet_ID == 0)
        {
			setEXME_DiagnosisTypeDet_ID (0);
			setEXME_DiagnosisType_ID (0);
			setEXME_Diagnostico_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_DiagnosisTypeDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_DiagnosisTypeDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (String Code)
	{
		set_Value (COLUMNNAME_Code, Code);
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public String getCode () 
	{
		return (String)get_Value(COLUMNNAME_Code);
	}

	/** Set Diagnosis Type Detail.
		@param EXME_DiagnosisTypeDet_ID Diagnosis Type Detail	  */
	public void setEXME_DiagnosisTypeDet_ID (int EXME_DiagnosisTypeDet_ID)
	{
		if (EXME_DiagnosisTypeDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_DiagnosisTypeDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DiagnosisTypeDet_ID, Integer.valueOf(EXME_DiagnosisTypeDet_ID));
	}

	/** Get Diagnosis Type Detail.
		@return Diagnosis Type Detail	  */
	public int getEXME_DiagnosisTypeDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosisTypeDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_DiagnosisType getEXME_DiagnosisType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_DiagnosisType.Table_Name);
        I_EXME_DiagnosisType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_DiagnosisType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_DiagnosisType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diagnosis Type.
		@param EXME_DiagnosisType_ID Diagnosis Type	  */
	public void setEXME_DiagnosisType_ID (int EXME_DiagnosisType_ID)
	{
		if (EXME_DiagnosisType_ID < 1)
			 throw new IllegalArgumentException ("EXME_DiagnosisType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DiagnosisType_ID, Integer.valueOf(EXME_DiagnosisType_ID));
	}

	/** Get Diagnosis Type.
		@return Diagnosis Type	  */
	public int getEXME_DiagnosisType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosisType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnosis Version.
		@param EXME_DiagnosisVersion_ID Diagnosis Version	  */
	public void setEXME_DiagnosisVersion_ID (int EXME_DiagnosisVersion_ID)
	{
		if (EXME_DiagnosisVersion_ID < 1) 
			set_Value (COLUMNNAME_EXME_DiagnosisVersion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DiagnosisVersion_ID, Integer.valueOf(EXME_DiagnosisVersion_ID));
	}

	/** Get Diagnosis Version.
		@return Diagnosis Version	  */
	public int getEXME_DiagnosisVersion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosisVersion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}