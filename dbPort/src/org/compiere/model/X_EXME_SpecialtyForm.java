/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_SpecialtyForm
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_SpecialtyForm extends PO implements I_EXME_SpecialtyForm, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_SpecialtyForm (Properties ctx, int EXME_SpecialtyForm_ID, String trxName)
    {
      super (ctx, EXME_SpecialtyForm_ID, trxName);
      /** if (EXME_SpecialtyForm_ID == 0)
        {
			setEXME_Cuestionario_ID (0);
			setEXME_Especialidad_ID (0);
			setEXME_SpecialtyForm_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_SpecialtyForm (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_SpecialtyForm[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Cuestionario getEXME_Cuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cuestionario.Table_Name);
        I_EXME_Cuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad.Table_Name);
        I_EXME_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Specialty Form.
		@param EXME_SpecialtyForm_ID Specialty Form	  */
	public void setEXME_SpecialtyForm_ID (int EXME_SpecialtyForm_ID)
	{
		if (EXME_SpecialtyForm_ID < 1)
			 throw new IllegalArgumentException ("EXME_SpecialtyForm_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_SpecialtyForm_ID, Integer.valueOf(EXME_SpecialtyForm_ID));
	}

	/** Get Specialty Form.
		@return Specialty Form	  */
	public int getEXME_SpecialtyForm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SpecialtyForm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}