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

/** Generated Model for EXME_Metricas_IMC
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Metricas_IMC extends PO implements I_EXME_Metricas_IMC, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Metricas_IMC (Properties ctx, int EXME_Metricas_IMC_ID, String trxName)
    {
      super (ctx, EXME_Metricas_IMC_ID, trxName);
      /** if (EXME_Metricas_IMC_ID == 0)
        {
			setEXME_Metricas_IMC_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_RangoSV_ID (0);
			setEXME_SignoVitalDet_ID (0);
			setFecha_IMC (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_Metricas_IMC (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Metricas_IMC[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BMI metrics.
		@param EXME_Metricas_IMC_ID 
		Body Mass Index metrics
	  */
	public void setEXME_Metricas_IMC_ID (int EXME_Metricas_IMC_ID)
	{
		if (EXME_Metricas_IMC_ID < 1)
			 throw new IllegalArgumentException ("EXME_Metricas_IMC_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Metricas_IMC_ID, Integer.valueOf(EXME_Metricas_IMC_ID));
	}

	/** Get BMI metrics.
		@return Body Mass Index metrics
	  */
	public int getEXME_Metricas_IMC_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Metricas_IMC_ID);
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

	public I_EXME_RangoSV getEXME_RangoSV() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RangoSV.Table_Name);
        I_EXME_RangoSV result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RangoSV)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RangoSV_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vital Sign's Rank.
		@param EXME_RangoSV_ID Vital Sign's Rank	  */
	public void setEXME_RangoSV_ID (int EXME_RangoSV_ID)
	{
		if (EXME_RangoSV_ID < 1)
			 throw new IllegalArgumentException ("EXME_RangoSV_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_RangoSV_ID, Integer.valueOf(EXME_RangoSV_ID));
	}

	/** Get Vital Sign's Rank.
		@return Vital Sign's Rank	  */
	public int getEXME_RangoSV_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RangoSV_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_SignoVitalDet getEXME_SignoVitalDet() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SignoVitalDet.Table_Name);
        I_EXME_SignoVitalDet result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SignoVitalDet)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SignoVitalDet_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vital Signs Details.
		@param EXME_SignoVitalDet_ID 
		Vital Signs Details
	  */
	public void setEXME_SignoVitalDet_ID (int EXME_SignoVitalDet_ID)
	{
		if (EXME_SignoVitalDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_SignoVitalDet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_SignoVitalDet_ID, Integer.valueOf(EXME_SignoVitalDet_ID));
	}

	/** Get Vital Signs Details.
		@return Vital Signs Details
	  */
	public int getEXME_SignoVitalDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SignoVitalDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IMC Date.
		@param Fecha_IMC IMC Date	  */
	public void setFecha_IMC (Timestamp Fecha_IMC)
	{
		if (Fecha_IMC == null)
			throw new IllegalArgumentException ("Fecha_IMC is mandatory.");
		set_Value (COLUMNNAME_Fecha_IMC, Fecha_IMC);
	}

	/** Get IMC Date.
		@return IMC Date	  */
	public Timestamp getFecha_IMC () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_IMC);
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
}