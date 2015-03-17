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

/** Generated Model for EXME_Egreso_Hora
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Egreso_Hora extends PO implements I_EXME_Egreso_Hora, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Egreso_Hora (Properties ctx, int EXME_Egreso_Hora_ID, String trxName)
    {
      super (ctx, EXME_Egreso_Hora_ID, trxName);
      /** if (EXME_Egreso_Hora_ID == 0)
        {
			setConsecutivo (0);
			setEXME_Egreso_Hora_ID (0);
			setEXME_Egresos_ID (0);
			setEXME_Paciente_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setHora (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Egreso_Hora (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Egreso_Hora[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Consecutive.
		@param Consecutivo 
		Consecutive
	  */
	public void setConsecutivo (int Consecutivo)
	{
		set_Value (COLUMNNAME_Consecutivo, Integer.valueOf(Consecutivo));
	}

	/** Get Consecutive.
		@return Consecutive
	  */
	public int getConsecutivo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Consecutivo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ctas_C.
		@param Ctas_C Ctas_C	  */
	public void setCtas_C (String Ctas_C)
	{
		set_Value (COLUMNNAME_Ctas_C, Ctas_C);
	}

	/** Get Ctas_C.
		@return Ctas_C	  */
	public String getCtas_C () 
	{
		return (String)get_Value(COLUMNNAME_Ctas_C);
	}

	/** Set Bed.
		@param EXME_Cama_ID 
		Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID)
	{
		if (EXME_Cama_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cama_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cama_ID, Integer.valueOf(EXME_Cama_ID));
	}

	/** Get Bed.
		@return Bed
	  */
	public int getEXME_Cama_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Time of Discharge.
		@param EXME_Egreso_Hora_ID Time of Discharge	  */
	public void setEXME_Egreso_Hora_ID (int EXME_Egreso_Hora_ID)
	{
		if (EXME_Egreso_Hora_ID < 1)
			 throw new IllegalArgumentException ("EXME_Egreso_Hora_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Egreso_Hora_ID, Integer.valueOf(EXME_Egreso_Hora_ID));
	}

	/** Get Time of Discharge.
		@return Time of Discharge	  */
	public int getEXME_Egreso_Hora_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Egreso_Hora_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Egresos getEXME_Egresos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Egresos.Table_Name);
        I_EXME_Egresos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Egresos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Egresos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Discharges.
		@param EXME_Egresos_ID 
		Discharges
	  */
	public void setEXME_Egresos_ID (int EXME_Egresos_ID)
	{
		if (EXME_Egresos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Egresos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Egresos_ID, Integer.valueOf(EXME_Egresos_ID));
	}

	/** Get Discharges.
		@return Discharges
	  */
	public int getEXME_Egresos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Egresos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Room.
		@param EXME_Habitacion_ID 
		Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID)
	{
		throw new IllegalArgumentException ("EXME_Habitacion_ID is virtual column");	}

	/** Get Room.
		@return Room
	  */
	public int getEXME_Habitacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Habitacion_ID);
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

	/** Set Medical Record.
		@param Expediente 
		Medical Record
	  */
	public void setExpediente (String Expediente)
	{
		set_Value (COLUMNNAME_Expediente, Expediente);
	}

	/** Get Medical Record.
		@return Medical Record
	  */
	public String getExpediente () 
	{
		return (String)get_Value(COLUMNNAME_Expediente);
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Hour.
		@param Hora 
		Hour
	  */
	public void setHora (String Hora)
	{
		if (Hora == null)
			throw new IllegalArgumentException ("Hora is mandatory.");
		set_Value (COLUMNNAME_Hora, Hora);
	}

	/** Get Hour.
		@return Hour
	  */
	public String getHora () 
	{
		return (String)get_Value(COLUMNNAME_Hora);
	}

	/** Motivo AD_Reference_ID=1200007 */
	public static final int MOTIVO_AD_Reference_ID=1200007;
	/** Set Motive.
		@param Motivo 
		Motive / Reason
	  */
	public void setMotivo (String Motivo)
	{
		set_Value (COLUMNNAME_Motivo, Motivo);
	}

	/** Get Motive.
		@return Motive / Reason
	  */
	public String getMotivo () 
	{
		return (String)get_Value(COLUMNNAME_Motivo);
	}

	/** Set Social Work.
		@param Trab_Soc Social Work	  */
	public void setTrab_Soc (String Trab_Soc)
	{
		set_Value (COLUMNNAME_Trab_Soc, Trab_Soc);
	}

	/** Get Social Work.
		@return Social Work	  */
	public String getTrab_Soc () 
	{
		return (String)get_Value(COLUMNNAME_Trab_Soc);
	}

	/** Set Ult_Act.
		@param Ult_Act Ult_Act	  */
	public void setUlt_Act (Timestamp Ult_Act)
	{
		set_Value (COLUMNNAME_Ult_Act, Ult_Act);
	}

	/** Get Ult_Act.
		@return Ult_Act	  */
	public Timestamp getUlt_Act () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Ult_Act);
	}

	/** Set OK.
		@param VoBo 
		OK
	  */
	public void setVoBo (String VoBo)
	{
		set_Value (COLUMNNAME_VoBo, VoBo);
	}

	/** Get OK.
		@return OK
	  */
	public String getVoBo () 
	{
		return (String)get_Value(COLUMNNAME_VoBo);
	}
}