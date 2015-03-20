/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_TratamientosSubesp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_TratamientosSubesp extends PO implements I_EXME_TratamientosSubesp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TratamientosSubesp (Properties ctx, int EXME_TratamientosSubesp_ID, String trxName)
    {
      super (ctx, EXME_TratamientosSubesp_ID, trxName);
      /** if (EXME_TratamientosSubesp_ID == 0)
        {
			setEXME_Especialidad_ID (0);
			setEXME_Tratamientos_ID (0);
			setEXME_TratamientosSubesp_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_TratamientosSubesp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TratamientosSubesp[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_EXME_Tratamientos getEXME_Tratamientos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos.Table_Name);
        I_EXME_Tratamientos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatments.
		@param EXME_Tratamientos_ID 
		Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID)
	{
		if (EXME_Tratamientos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Tratamientos_ID, Integer.valueOf(EXME_Tratamientos_ID));
	}

	/** Get Treatments.
		@return Treatments
	  */
	public int getEXME_Tratamientos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatment subspecialty.
		@param EXME_TratamientosSubesp_ID Treatment subspecialty	  */
	public void setEXME_TratamientosSubesp_ID (int EXME_TratamientosSubesp_ID)
	{
		if (EXME_TratamientosSubesp_ID < 1)
			 throw new IllegalArgumentException ("EXME_TratamientosSubesp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TratamientosSubesp_ID, Integer.valueOf(EXME_TratamientosSubesp_ID));
	}

	/** Get Treatment subspecialty.
		@return Treatment subspecialty	  */
	public int getEXME_TratamientosSubesp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TratamientosSubesp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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