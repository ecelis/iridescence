/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_RecursoEducativoRes
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_RecursoEducativoRes extends PO implements I_EXME_RecursoEducativoRes, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RecursoEducativoRes (Properties ctx, int EXME_RecursoEducativoRes_ID, String trxName)
    {
      super (ctx, EXME_RecursoEducativoRes_ID, trxName);
      /** if (EXME_RecursoEducativoRes_ID == 0)
        {
			setEXME_RecursoEducativo_ID (0);
			setEXME_RecursoEducativoRes_ID (0);
			setLevel_Max (Env.ZERO);
			setLevel_Min (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_RecursoEducativoRes (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RecursoEducativoRes[")
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
			set_Value (COLUMNNAME_EXME_Loinc_ID, null);
		else 
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

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RecursoEducativo.Table_Name);
        I_EXME_RecursoEducativo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RecursoEducativo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RecursoEducativo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Education Resource.
		@param EXME_RecursoEducativo_ID Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID)
	{
		if (EXME_RecursoEducativo_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_RecursoEducativo_ID, Integer.valueOf(EXME_RecursoEducativo_ID));
	}

	/** Get Education Resource.
		@return Education Resource	  */
	public int getEXME_RecursoEducativo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Results Education Resources.
		@param EXME_RecursoEducativoRes_ID Results Education Resources	  */
	public void setEXME_RecursoEducativoRes_ID (int EXME_RecursoEducativoRes_ID)
	{
		if (EXME_RecursoEducativoRes_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativoRes_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RecursoEducativoRes_ID, Integer.valueOf(EXME_RecursoEducativoRes_ID));
	}

	/** Get Results Education Resources.
		@return Results Education Resources	  */
	public int getEXME_RecursoEducativoRes_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativoRes_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximum Level.
		@param Level_Max 
		Maximum Inventory level for this product
	  */
	public void setLevel_Max (BigDecimal Level_Max)
	{
		if (Level_Max == null)
			throw new IllegalArgumentException ("Level_Max is mandatory.");
		set_Value (COLUMNNAME_Level_Max, Level_Max);
	}

	/** Get Maximum Level.
		@return Maximum Inventory level for this product
	  */
	public BigDecimal getLevel_Max () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Level_Max);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Level.
		@param Level_Min 
		Minimum Inventory level for this product
	  */
	public void setLevel_Min (BigDecimal Level_Min)
	{
		if (Level_Min == null)
			throw new IllegalArgumentException ("Level_Min is mandatory.");
		set_Value (COLUMNNAME_Level_Min, Level_Min);
	}

	/** Get Minimum Level.
		@return Minimum Inventory level for this product
	  */
	public BigDecimal getLevel_Min () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Level_Min);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}