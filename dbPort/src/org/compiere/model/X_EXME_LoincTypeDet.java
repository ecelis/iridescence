/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_LoincTypeDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_LoincTypeDet extends PO implements I_EXME_LoincTypeDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_LoincTypeDet (Properties ctx, int EXME_LoincTypeDet_ID, String trxName)
    {
      super (ctx, EXME_LoincTypeDet_ID, trxName);
      /** if (EXME_LoincTypeDet_ID == 0)
        {
			setEXME_Loinc_ID (0);
			setEXME_LoincTypeDet_ID (0);
			setEXME_LoincType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_LoincTypeDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_LoincTypeDet[")
        .append(get_ID()).append("]");
      return sb.toString();
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
		set_Value (COLUMNNAME_EXME_Loinc_ID, Integer.valueOf(EXME_Loinc_ID));
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

	/** Set Loinc Type Detail.
		@param EXME_LoincTypeDet_ID Loinc Type Detail	  */
	public void setEXME_LoincTypeDet_ID (int EXME_LoincTypeDet_ID)
	{
		if (EXME_LoincTypeDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_LoincTypeDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_LoincTypeDet_ID, Integer.valueOf(EXME_LoincTypeDet_ID));
	}

	/** Get Loinc Type Detail.
		@return Loinc Type Detail	  */
	public int getEXME_LoincTypeDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LoincTypeDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_LoincType getEXME_LoincType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_LoincType.Table_Name);
        I_EXME_LoincType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_LoincType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_LoincType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Loinc Type.
		@param EXME_LoincType_ID Loinc Type	  */
	public void setEXME_LoincType_ID (int EXME_LoincType_ID)
	{
		if (EXME_LoincType_ID < 1)
			 throw new IllegalArgumentException ("EXME_LoincType_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_LoincType_ID, Integer.valueOf(EXME_LoincType_ID));
	}

	/** Get Loinc Type.
		@return Loinc Type	  */
	public int getEXME_LoincType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LoincType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Loinc Version.
		@param EXME_LoincVersion_ID Loinc Version	  */
	public void setEXME_LoincVersion_ID (int EXME_LoincVersion_ID)
	{
		if (EXME_LoincVersion_ID < 1) 
			set_Value (COLUMNNAME_EXME_LoincVersion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_LoincVersion_ID, Integer.valueOf(EXME_LoincVersion_ID));
	}

	/** Get Loinc Version.
		@return Loinc Version	  */
	public int getEXME_LoincVersion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_LoincVersion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}