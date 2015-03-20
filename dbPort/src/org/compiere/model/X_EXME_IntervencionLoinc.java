/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_IntervencionLoinc
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_IntervencionLoinc extends PO implements I_EXME_IntervencionLoinc, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_IntervencionLoinc (Properties ctx, int EXME_IntervencionLoinc_ID, String trxName)
    {
      super (ctx, EXME_IntervencionLoinc_ID, trxName);
      /** if (EXME_IntervencionLoinc_ID == 0)
        {
			setEXME_IntervencionLoinc_ID (0);
			setEXME_Intervencion_ID (0);
			setEXME_Loinc_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_IntervencionLoinc (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_IntervencionLoinc[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set CPT - Loinc.
		@param EXME_IntervencionLoinc_ID CPT - Loinc	  */
	public void setEXME_IntervencionLoinc_ID (int EXME_IntervencionLoinc_ID)
	{
		if (EXME_IntervencionLoinc_ID < 1)
			 throw new IllegalArgumentException ("EXME_IntervencionLoinc_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_IntervencionLoinc_ID, Integer.valueOf(EXME_IntervencionLoinc_ID));
	}

	/** Get CPT - Loinc.
		@return CPT - Loinc	  */
	public int getEXME_IntervencionLoinc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_IntervencionLoinc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Intervencion.Table_Name);
        I_EXME_Intervencion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Intervencion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Intervencion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Intervention.
		@param EXME_Intervencion_ID 
		Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Intervencion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get Intervention.
		@return Intervention
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Loinc getEXME_Loinc() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Loinc.Table_Name);
        I_EXME_Loinc result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Loinc)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Loinc_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set LOINC Code.
		@param EXME_Loinc_ID LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID)
	{
		if (EXME_Loinc_ID < 1)
			 throw new IllegalArgumentException ("EXME_Loinc_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Loinc_ID, Integer.valueOf(EXME_Loinc_ID));
	}

	/** Get LOINC Code.
		@return LOINC Code	  */
	public int getEXME_Loinc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Loinc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}