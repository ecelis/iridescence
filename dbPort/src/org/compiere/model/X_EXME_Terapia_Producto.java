/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Terapia_Producto
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Terapia_Producto extends PO implements I_EXME_Terapia_Producto, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Terapia_Producto (Properties ctx, int EXME_Terapia_Producto_ID, String trxName)
    {
      super (ctx, EXME_Terapia_Producto_ID, trxName);
      /** if (EXME_Terapia_Producto_ID == 0)
        {
			setEXME_Terapia_ID (0);
// @EXME_Terapia_ID@
			setEXME_Terapia_Producto_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Terapia_Producto (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Terapia_Producto[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Generic Product.
		@param EXME_GenProduct_ID Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID)
	{
		if (EXME_GenProduct_ID < 1) 
			set_Value (COLUMNNAME_EXME_GenProduct_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GenProduct_ID, Integer.valueOf(EXME_GenProduct_ID));
	}

	/** Get Generic Product.
		@return Generic Product	  */
	public int getEXME_GenProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GenProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Terapia getEXME_Terapia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Terapia.Table_Name);
        I_EXME_Terapia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Terapia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Terapia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Therapy.
		@param EXME_Terapia_ID Therapy	  */
	public void setEXME_Terapia_ID (int EXME_Terapia_ID)
	{
		if (EXME_Terapia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Terapia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Terapia_ID, Integer.valueOf(EXME_Terapia_ID));
	}

	/** Get Therapy.
		@return Therapy	  */
	public int getEXME_Terapia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Terapia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Therapy - Product.
		@param EXME_Terapia_Producto_ID Therapy - Product	  */
	public void setEXME_Terapia_Producto_ID (int EXME_Terapia_Producto_ID)
	{
		if (EXME_Terapia_Producto_ID < 1)
			 throw new IllegalArgumentException ("EXME_Terapia_Producto_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Terapia_Producto_ID, Integer.valueOf(EXME_Terapia_Producto_ID));
	}

	/** Get Therapy - Product.
		@return Therapy - Product	  */
	public int getEXME_Terapia_Producto_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Terapia_Producto_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Therapies Version.
		@param EXME_TherapiesVersion_ID Therapies Version	  */
	public void setEXME_TherapiesVersion_ID (int EXME_TherapiesVersion_ID)
	{
		if (EXME_TherapiesVersion_ID < 1) 
			set_Value (COLUMNNAME_EXME_TherapiesVersion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TherapiesVersion_ID, Integer.valueOf(EXME_TherapiesVersion_ID));
	}

	/** Get Therapies Version.
		@return Therapies Version	  */
	public int getEXME_TherapiesVersion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TherapiesVersion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medication.
		@param Medication 
		Medication
	  */
	public void setMedication (String Medication)
	{
		set_Value (COLUMNNAME_Medication, Medication);
	}

	/** Get Medication.
		@return Medication
	  */
	public String getMedication () 
	{
		return (String)get_Value(COLUMNNAME_Medication);
	}
}