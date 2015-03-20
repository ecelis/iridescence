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

/** Generated Model for EXME_ResidenciaRot
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ResidenciaRot extends PO implements I_EXME_ResidenciaRot, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ResidenciaRot (Properties ctx, int EXME_ResidenciaRot_ID, String trxName)
    {
      super (ctx, EXME_ResidenciaRot_ID, trxName);
      /** if (EXME_ResidenciaRot_ID == 0)
        {
			setEXME_Medico_ID (0);
			setEXME_ResidenciaRot_ID (0);
			setFechaFin (new Timestamp( System.currentTimeMillis() ));
			setFechaInicio (new Timestamp( System.currentTimeMillis() ));
			setHoraFin (null);
			setHoraInicio (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ResidenciaRot (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ResidenciaRot[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Medico getEXME_Medico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Medico.Table_Name);
        I_EXME_Medico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Medico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Medico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rotating Residence .
		@param EXME_ResidenciaRot_ID Rotating Residence 	  */
	public void setEXME_ResidenciaRot_ID (int EXME_ResidenciaRot_ID)
	{
		if (EXME_ResidenciaRot_ID < 1)
			 throw new IllegalArgumentException ("EXME_ResidenciaRot_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ResidenciaRot_ID, Integer.valueOf(EXME_ResidenciaRot_ID));
	}

	/** Get Rotating Residence .
		@return Rotating Residence 	  */
	public int getEXME_ResidenciaRot_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ResidenciaRot_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ending Date.
		@param FechaFin 
		Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin)
	{
		if (FechaFin == null)
			throw new IllegalArgumentException ("FechaFin is mandatory.");
		set_Value (COLUMNNAME_FechaFin, FechaFin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFechaFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaFin);
	}

	/** Set Beginning Date.
		@param FechaInicio Beginning Date	  */
	public void setFechaInicio (Timestamp FechaInicio)
	{
		if (FechaInicio == null)
			throw new IllegalArgumentException ("FechaInicio is mandatory.");
		set_Value (COLUMNNAME_FechaInicio, FechaInicio);
	}

	/** Get Beginning Date.
		@return Beginning Date	  */
	public Timestamp getFechaInicio () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaInicio);
	}

	/** Set End Hour.
		@param HoraFin End Hour	  */
	public void setHoraFin (String HoraFin)
	{
		if (HoraFin == null)
			throw new IllegalArgumentException ("HoraFin is mandatory.");
		set_Value (COLUMNNAME_HoraFin, HoraFin);
	}

	/** Get End Hour.
		@return End Hour	  */
	public String getHoraFin () 
	{
		return (String)get_Value(COLUMNNAME_HoraFin);
	}

	/** Set Start Hour.
		@param HoraInicio Start Hour	  */
	public void setHoraInicio (String HoraInicio)
	{
		if (HoraInicio == null)
			throw new IllegalArgumentException ("HoraInicio is mandatory.");
		set_Value (COLUMNNAME_HoraInicio, HoraInicio);
	}

	/** Get Start Hour.
		@return Start Hour	  */
	public String getHoraInicio () 
	{
		return (String)get_Value(COLUMNNAME_HoraInicio);
	}
}