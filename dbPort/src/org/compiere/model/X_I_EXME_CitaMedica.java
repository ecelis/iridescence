/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for I_EXME_CitaMedica
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_I_EXME_CitaMedica extends PO implements I_I_EXME_CitaMedica, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_CitaMedica (Properties ctx, int I_EXME_CitaMedica_ID, String trxName)
    {
      super (ctx, I_EXME_CitaMedica_ID, trxName);
      /** if (I_EXME_CitaMedica_ID == 0)
        {
			setI_EXME_CitaMedica_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_CitaMedica (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_CitaMedica[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Appointment Of.
		@param CitaDe 
		Appointment Of
	  */
	public void setCitaDe (boolean CitaDe)
	{
		set_Value (COLUMNNAME_CitaDe, Boolean.valueOf(CitaDe));
	}

	/** Get Appointment Of.
		@return Appointment Of
	  */
	public boolean isCitaDe () 
	{
		Object oo = get_Value(COLUMNNAME_CitaDe);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Confirmed.
		@param Confirmada 
		Confirmed
	  */
	public void setConfirmada (boolean Confirmada)
	{
		set_Value (COLUMNNAME_Confirmada, Boolean.valueOf(Confirmada));
	}

	/** Get Confirmed.
		@return Confirmed
	  */
	public boolean isConfirmada () 
	{
		Object oo = get_Value(COLUMNNAME_Confirmada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Duration.
		@param Duracion 
		Duration
	  */
	public void setDuracion (BigDecimal Duracion)
	{
		set_Value (COLUMNNAME_Duracion, Duracion);
	}

	/** Get Duration.
		@return Duration
	  */
	public BigDecimal getDuracion () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Duracion);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Age.
		@param Edad 
		Age
	  */
	public void setEdad (BigDecimal Edad)
	{
		set_Value (COLUMNNAME_Edad, Edad);
	}

	/** Get Age.
		@return Age
	  */
	public BigDecimal getEdad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Edad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (boolean Estatus)
	{
		set_Value (COLUMNNAME_Estatus, Boolean.valueOf(Estatus));
	}

	/** Get Status.
		@return Status
	  */
	public boolean isEstatus () 
	{
		Object oo = get_Value(COLUMNNAME_Estatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_EXME_Asistente getEXME_Asistente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Asistente.Table_Name);
        I_EXME_Asistente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Asistente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Asistente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Assistant.
		@param EXME_Asistente_ID 
		Assistant
	  */
	public void setEXME_Asistente_ID (int EXME_Asistente_ID)
	{
		if (EXME_Asistente_ID < 1) 
			set_Value (COLUMNNAME_EXME_Asistente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Asistente_ID, Integer.valueOf(EXME_Asistente_ID));
	}

	/** Get Assistant.
		@return Assistant
	  */
	public int getEXME_Asistente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Asistente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Assistant Value.
		@param EXME_Asistente_Value Assistant Value	  */
	public void setEXME_Asistente_Value (String EXME_Asistente_Value)
	{
		set_Value (COLUMNNAME_EXME_Asistente_Value, EXME_Asistente_Value);
	}

	/** Get Assistant Value.
		@return Assistant Value	  */
	public String getEXME_Asistente_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Asistente_Value);
	}

	/** Set Medical Center.
		@param EXME_CentroMedicoEnv_ID 
		Medical Center
	  */
	public void setEXME_CentroMedicoEnv_ID (int EXME_CentroMedicoEnv_ID)
	{
		if (EXME_CentroMedicoEnv_ID < 1) 
			set_Value (COLUMNNAME_EXME_CentroMedicoEnv_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CentroMedicoEnv_ID, Integer.valueOf(EXME_CentroMedicoEnv_ID));
	}

	/** Get Medical Center.
		@return Medical Center
	  */
	public int getEXME_CentroMedicoEnv_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CentroMedicoEnv_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value of Medical Center.
		@param EXME_CentroMedicoEnv_Value Value of Medical Center	  */
	public void setEXME_CentroMedicoEnv_Value (String EXME_CentroMedicoEnv_Value)
	{
		set_Value (COLUMNNAME_EXME_CentroMedicoEnv_Value, EXME_CentroMedicoEnv_Value);
	}

	/** Get Value of Medical Center.
		@return Value of Medical Center	  */
	public String getEXME_CentroMedicoEnv_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_CentroMedicoEnv_Value);
	}

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CitaMedica.Table_Name);
        I_EXME_CitaMedica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CitaMedica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CitaMedica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Appointment.
		@param EXME_CitaMedica_ID 
		Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID)
	{
		if (EXME_CitaMedica_ID < 1) 
			set_Value (COLUMNNAME_EXME_CitaMedica_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CitaMedica_ID, Integer.valueOf(EXME_CitaMedica_ID));
	}

	/** Get Medical Appointment.
		@return Medical appointment
	  */
	public int getEXME_CitaMedica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CitaMedica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Especialidad.Table_Name);
        I_EXME_Especialidad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Especialidad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Especialidad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Speciality Value.
		@param EXME_Especialidad_Value Speciality Value	  */
	public void setEXME_Especialidad_Value (String EXME_Especialidad_Value)
	{
		set_Value (COLUMNNAME_EXME_Especialidad_Value, EXME_Especialidad_Value);
	}

	/** Get Speciality Value.
		@return Speciality Value	  */
	public String getEXME_Especialidad_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Especialidad_Value);
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
			set_Value (COLUMNNAME_EXME_Medico_ID, null);
		else 
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

	/** Set Doctor Value.
		@param EXME_Medico_Value Doctor Value	  */
	public void setEXME_Medico_Value (String EXME_Medico_Value)
	{
		set_Value (COLUMNNAME_EXME_Medico_Value, EXME_Medico_Value);
	}

	/** Get Doctor Value.
		@return Doctor Value	  */
	public String getEXME_Medico_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Medico_Value);
	}

	public I_EXME_MotivoCita getEXME_MotivoCita() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoCita.Table_Name);
        I_EXME_MotivoCita result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoCita)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoCita_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Motive of appointment.
		@param EXME_MotivoCita_ID 
		Motive of appointment
	  */
	public void setEXME_MotivoCita_ID (int EXME_MotivoCita_ID)
	{
		if (EXME_MotivoCita_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoCita_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoCita_ID, Integer.valueOf(EXME_MotivoCita_ID));
	}

	/** Get Motive of appointment.
		@return Motive of appointment
	  */
	public int getEXME_MotivoCita_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCita_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reason Value.
		@param EXME_Motivo_Value Reason Value	  */
	public void setEXME_Motivo_Value (String EXME_Motivo_Value)
	{
		set_Value (COLUMNNAME_EXME_Motivo_Value, EXME_Motivo_Value);
	}

	/** Get Reason Value.
		@return Reason Value	  */
	public String getEXME_Motivo_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Motivo_Value);
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

	public I_EXME_TipoCita getEXME_TipoCita() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoCita.Table_Name);
        I_EXME_TipoCita result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoCita)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoCita_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Type of Appointment.
		@param EXME_TipoCita_ID 
		Type of Appointment
	  */
	public void setEXME_TipoCita_ID (int EXME_TipoCita_ID)
	{
		if (EXME_TipoCita_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoCita_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoCita_ID, Integer.valueOf(EXME_TipoCita_ID));
	}

	/** Get Type of Appointment.
		@return Type of Appointment
	  */
	public int getEXME_TipoCita_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoCita_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Appointment Type Value.
		@param EXME_TipoCita_Value Appointment Type Value	  */
	public void setEXME_TipoCita_Value (String EXME_TipoCita_Value)
	{
		set_Value (COLUMNNAME_EXME_TipoCita_Value, EXME_TipoCita_Value);
	}

	/** Get Appointment Type Value.
		@return Appointment Type Value	  */
	public String getEXME_TipoCita_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_TipoCita_Value);
	}

	/** Set Date.
		@param FechaHrCita 
		Date
	  */
	public void setFechaHrCita (Timestamp FechaHrCita)
	{
		set_Value (COLUMNNAME_FechaHrCita, FechaHrCita);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFechaHrCita () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaHrCita);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Medical Appointment.
		@param I_EXME_CitaMedica_ID Medical Appointment	  */
	public void setI_EXME_CitaMedica_ID (int I_EXME_CitaMedica_ID)
	{
		if (I_EXME_CitaMedica_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_CitaMedica_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_CitaMedica_ID, Integer.valueOf(I_EXME_CitaMedica_ID));
	}

	/** Get Medical Appointment.
		@return Medical Appointment	  */
	public int getI_EXME_CitaMedica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_CitaMedica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set History Number.
		@param NumHist History Number	  */
	public void setNumHist (String NumHist)
	{
		set_Value (COLUMNNAME_NumHist, NumHist);
	}

	/** Get History Number.
		@return History Number	  */
	public String getNumHist () 
	{
		return (String)get_Value(COLUMNNAME_NumHist);
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

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}