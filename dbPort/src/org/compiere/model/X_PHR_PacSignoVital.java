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

/** Generated Model for PHR_PacSignoVital
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_PacSignoVital extends PO implements I_PHR_PacSignoVital, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_PacSignoVital (Properties ctx, int PHR_PacSignoVital_ID, String trxName)
    {
      super (ctx, PHR_PacSignoVital_ID, trxName);
      /** if (PHR_PacSignoVital_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setPHR_PacSignoVital_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_PacSignoVital (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PHR_PacSignoVital[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
	}

	public I_PHR_Evento getPHR_Evento() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_Evento.Table_Name);
        I_PHR_Evento result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_Evento)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_Evento_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Event.
		@param PHR_Evento_ID Patient Event	  */
	public void setPHR_Evento_ID (int PHR_Evento_ID)
	{
		if (PHR_Evento_ID < 1) 
			set_Value (COLUMNNAME_PHR_Evento_ID, null);
		else 
			set_Value (COLUMNNAME_PHR_Evento_ID, Integer.valueOf(PHR_Evento_ID));
	}

	/** Get Patient Event.
		@return Patient Event	  */
	public int getPHR_Evento_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_Evento_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient's Vital Signs.
		@param PHR_PacSignoVital_ID Patient's Vital Signs	  */
	public void setPHR_PacSignoVital_ID (int PHR_PacSignoVital_ID)
	{
		if (PHR_PacSignoVital_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacSignoVital_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_PacSignoVital_ID, Integer.valueOf(PHR_PacSignoVital_ID));
	}

	/** Get Patient's Vital Signs.
		@return Patient's Vital Signs	  */
	public int getPHR_PacSignoVital_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacSignoVital_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}