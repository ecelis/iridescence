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

/** Generated Model for EXME_Prof_Ens
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Prof_Ens extends PO implements I_EXME_Prof_Ens, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Prof_Ens (Properties ctx, int EXME_Prof_Ens_ID, String trxName)
    {
      super (ctx, EXME_Prof_Ens_ID, trxName);
      /** if (EXME_Prof_Ens_ID == 0)
        {
			setEXME_Ensenanza_5_ID (0);
			setEXME_Prof_Ens_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Prof_Ens (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Prof_Ens[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set SW Specialty.
		@param Especialidad_TS 
		Social Work Specialty
	  */
	public void setEspecialidad_TS (String Especialidad_TS)
	{
		set_Value (COLUMNNAME_Especialidad_TS, Especialidad_TS);
	}

	/** Get SW Specialty.
		@return Social Work Specialty
	  */
	public String getEspecialidad_TS () 
	{
		return (String)get_Value(COLUMNNAME_Especialidad_TS);
	}

	public I_EXME_Ensenanza_5 getEXME_Ensenanza_5() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Ensenanza_5.Table_Name);
        I_EXME_Ensenanza_5 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Ensenanza_5)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Ensenanza_5_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Teaching Courses 5.
		@param EXME_Ensenanza_5_ID 
		Teaching Courses 5
	  */
	public void setEXME_Ensenanza_5_ID (int EXME_Ensenanza_5_ID)
	{
		if (EXME_Ensenanza_5_ID < 1)
			 throw new IllegalArgumentException ("EXME_Ensenanza_5_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Ensenanza_5_ID, Integer.valueOf(EXME_Ensenanza_5_ID));
	}

	/** Get Teaching Courses 5.
		@return Teaching Courses 5
	  */
	public int getEXME_Ensenanza_5_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ensenanza_5_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Teaching Professor.
		@param EXME_Prof_Ens_ID Teaching Professor	  */
	public void setEXME_Prof_Ens_ID (int EXME_Prof_Ens_ID)
	{
		if (EXME_Prof_Ens_ID < 1)
			 throw new IllegalArgumentException ("EXME_Prof_Ens_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Prof_Ens_ID, Integer.valueOf(EXME_Prof_Ens_ID));
	}

	/** Get Teaching Professor.
		@return Teaching Professor	  */
	public int getEXME_Prof_Ens_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Prof_Ens_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Profesor Number.
		@param Num_Profesor Profesor Number	  */
	public void setNum_Profesor (BigDecimal Num_Profesor)
	{
		set_Value (COLUMNNAME_Num_Profesor, Num_Profesor);
	}

	/** Get Profesor Number.
		@return Profesor Number	  */
	public BigDecimal getNum_Profesor () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Num_Profesor);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}