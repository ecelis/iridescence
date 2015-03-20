/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Vacacion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Vacacion extends PO implements I_EXME_Vacacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Vacacion (Properties ctx, int EXME_Vacacion_ID, String trxName)
    {
      super (ctx, EXME_Vacacion_ID, trxName);
      /** if (EXME_Vacacion_ID == 0)
        {
			setC_Region_ID (0);
			setEXME_Emp_ID (0);
			setEXME_PerVacD_ID (0);
			setEXME_PerVacH_ID (0);
			setEXME_TownCouncil_ID (0);
			setEXME_Vacacion_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Vacacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Vacacion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set City.
		@param Ciudad 
		description of a city
	  */
	public void setCiudad (String Ciudad)
	{
		set_Value (COLUMNNAME_Ciudad, Ciudad);
	}

	/** Get City.
		@return description of a city
	  */
	public String getCiudad () 
	{
		return (String)get_Value(COLUMNNAME_Ciudad);
	}

	public I_C_Region getC_Region() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Region.Table_Name);
        I_C_Region result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Region)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Region_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Region.
		@param C_Region_ID 
		Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID)
	{
		if (C_Region_ID < 1)
			 throw new IllegalArgumentException ("C_Region_ID is mandatory.");
		set_Value (COLUMNNAME_C_Region_ID, Integer.valueOf(C_Region_ID));
	}

	/** Get Region.
		@return Identifies a geographical Region
	  */
	public int getC_Region_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Region_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Employee.
		@param EXME_Emp_ID 
		Employee
	  */
	public void setEXME_Emp_ID (int EXME_Emp_ID)
	{
		if (EXME_Emp_ID < 1)
			 throw new IllegalArgumentException ("EXME_Emp_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Emp_ID, Integer.valueOf(EXME_Emp_ID));
	}

	/** Get Employee.
		@return Employee
	  */
	public int getEXME_Emp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Emp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vacational Period Details.
		@param EXME_PerVacD_ID 
		Vacational Period Details
	  */
	public void setEXME_PerVacD_ID (int EXME_PerVacD_ID)
	{
		if (EXME_PerVacD_ID < 1)
			 throw new IllegalArgumentException ("EXME_PerVacD_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PerVacD_ID, Integer.valueOf(EXME_PerVacD_ID));
	}

	/** Get Vacational Period Details.
		@return Vacational Period Details
	  */
	public int getEXME_PerVacD_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PerVacD_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vacational Period.
		@param EXME_PerVacH_ID 
		Vacational Period
	  */
	public void setEXME_PerVacH_ID (int EXME_PerVacH_ID)
	{
		if (EXME_PerVacH_ID < 1)
			 throw new IllegalArgumentException ("EXME_PerVacH_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PerVacH_ID, Integer.valueOf(EXME_PerVacH_ID));
	}

	/** Get Vacational Period.
		@return Vacational Period
	  */
	public int getEXME_PerVacH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PerVacH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set County.
		@param EXME_TownCouncil_ID 
		County
	  */
	public void setEXME_TownCouncil_ID (int EXME_TownCouncil_ID)
	{
		if (EXME_TownCouncil_ID < 1)
			 throw new IllegalArgumentException ("EXME_TownCouncil_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TownCouncil_ID, Integer.valueOf(EXME_TownCouncil_ID));
	}

	/** Get County.
		@return County
	  */
	public int getEXME_TownCouncil_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TownCouncil_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vacation.
		@param EXME_Vacacion_ID Vacation	  */
	public void setEXME_Vacacion_ID (int EXME_Vacacion_ID)
	{
		if (EXME_Vacacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Vacacion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Vacacion_ID, Integer.valueOf(EXME_Vacacion_ID));
	}

	/** Get Vacation.
		@return Vacation	  */
	public int getEXME_Vacacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Vacacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}