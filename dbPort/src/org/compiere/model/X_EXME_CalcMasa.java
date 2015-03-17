/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_CalcMasa
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CalcMasa extends PO implements I_EXME_CalcMasa, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CalcMasa (Properties ctx, int EXME_CalcMasa_ID, String trxName)
    {
      super (ctx, EXME_CalcMasa_ID, trxName);
      /** if (EXME_CalcMasa_ID == 0)
        {
			setActivityLevel (null);
			setEXME_CalcMasa_ID (0);
			setEXME_Paciente_ID (0);
			setFolio (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CalcMasa (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CalcMasa[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** ActivityLevel AD_Reference_ID=1200453 */
	public static final int ACTIVITYLEVEL_AD_Reference_ID=1200453;
	/** Sedentary = S */
	public static final String ACTIVITYLEVEL_Sedentary = "S";
	/** Moderate = M */
	public static final String ACTIVITYLEVEL_Moderate = "M";
	/** Active = A */
	public static final String ACTIVITYLEVEL_Active = "A";
	/** Set Level of Physic Activity.
		@param ActivityLevel Level of Physic Activity	  */
	public void setActivityLevel (String ActivityLevel)
	{
		if (ActivityLevel == null) throw new IllegalArgumentException ("ActivityLevel is mandatory");
		if (ActivityLevel.equals("S") || ActivityLevel.equals("M") || ActivityLevel.equals("A")); else throw new IllegalArgumentException ("ActivityLevel Invalid value - " + ActivityLevel + " - Reference_ID=1200453 - S - M - A");		set_Value (COLUMNNAME_ActivityLevel, ActivityLevel);
	}

	/** Get Level of Physic Activity.
		@return Level of Physic Activity	  */
	public String getActivityLevel () 
	{
		return (String)get_Value(COLUMNNAME_ActivityLevel);
	}

	/** Set Register Body Mass.
		@param EXME_CalcMasa_ID Register Body Mass	  */
	public void setEXME_CalcMasa_ID (int EXME_CalcMasa_ID)
	{
		if (EXME_CalcMasa_ID < 1)
			 throw new IllegalArgumentException ("EXME_CalcMasa_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CalcMasa_ID, Integer.valueOf(EXME_CalcMasa_ID));
	}

	/** Get Register Body Mass.
		@return Register Body Mass	  */
	public int getEXME_CalcMasa_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CalcMasa_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (int Folio)
	{
		set_Value (COLUMNNAME_Folio, Integer.valueOf(Folio));
	}

	/** Get Folio.
		@return Folio	  */
	public int getFolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Folio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}