/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_EnfermeriaEsp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EnfermeriaEsp extends PO implements I_EXME_EnfermeriaEsp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EnfermeriaEsp (Properties ctx, int EXME_EnfermeriaEsp_ID, String trxName)
    {
      super (ctx, EXME_EnfermeriaEsp_ID, trxName);
      /** if (EXME_EnfermeriaEsp_ID == 0)
        {
			setEXME_EnfermeriaEsp_ID (0);
			setEXME_Enfermeria_ID (0);
			setEXME_Especialidad_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EnfermeriaEsp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EnfermeriaEsp[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Nursing specialty.
		@param EXME_EnfermeriaEsp_ID Nursing specialty	  */
	public void setEXME_EnfermeriaEsp_ID (int EXME_EnfermeriaEsp_ID)
	{
		if (EXME_EnfermeriaEsp_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfermeriaEsp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnfermeriaEsp_ID, Integer.valueOf(EXME_EnfermeriaEsp_ID));
	}

	/** Get Nursing specialty.
		@return Nursing specialty	  */
	public int getEXME_EnfermeriaEsp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfermeriaEsp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Enfermeria.Table_Name);
        I_EXME_Enfermeria result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Enfermeria)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Enfermeria_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary staff.
		@param EXME_Enfermeria_ID 
		Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID)
	{
		if (EXME_Enfermeria_ID < 1)
			 throw new IllegalArgumentException ("EXME_Enfermeria_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Enfermeria_ID, Integer.valueOf(EXME_Enfermeria_ID));
	}

	/** Get Infirmary staff.
		@return Infirmary staff
	  */
	public int getEXME_Enfermeria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}