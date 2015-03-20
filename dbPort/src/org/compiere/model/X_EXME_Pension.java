/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Pension
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Pension extends PO implements I_EXME_Pension, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Pension (Properties ctx, int EXME_Pension_ID, String trxName)
    {
      super (ctx, EXME_Pension_ID, trxName);
      /** if (EXME_Pension_ID == 0)
        {
			setAfiliacionNo (0);
			setEXME_Pension_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Pension (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Pension[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Affiliation Number.
		@param AfiliacionNo Affiliation Number	  */
	public void setAfiliacionNo (int AfiliacionNo)
	{
		set_Value (COLUMNNAME_AfiliacionNo, Integer.valueOf(AfiliacionNo));
	}

	/** Get Affiliation Number.
		@return Affiliation Number	  */
	public int getAfiliacionNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AfiliacionNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
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
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
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

	/** Set Pension.
		@param EXME_Pension_ID 
		Pension
	  */
	public void setEXME_Pension_ID (int EXME_Pension_ID)
	{
		if (EXME_Pension_ID < 1)
			 throw new IllegalArgumentException ("EXME_Pension_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Pension_ID, Integer.valueOf(EXME_Pension_ID));
	}

	/** Get Pension.
		@return Pension
	  */
	public int getEXME_Pension_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pension_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discharge Date.
		@param Fecha_Egreso Discharge Date	  */
	public void setFecha_Egreso (Timestamp Fecha_Egreso)
	{
		set_Value (COLUMNNAME_Fecha_Egreso, Fecha_Egreso);
	}

	/** Get Discharge Date.
		@return Discharge Date	  */
	public Timestamp getFecha_Egreso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Egreso);
	}

	/** Set Admission Date.
		@param FechaIngreso 
		Admission Date
	  */
	public void setFechaIngreso (Timestamp FechaIngreso)
	{
		set_Value (COLUMNNAME_FechaIngreso, FechaIngreso);
	}

	/** Get Admission Date.
		@return Admission Date
	  */
	public Timestamp getFechaIngreso () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaIngreso);
	}
}