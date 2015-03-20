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

/** Generated Model for EXME_PacienteAler
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PacienteAler extends PO implements I_EXME_PacienteAler, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PacienteAler (Properties ctx, int EXME_PacienteAler_ID, String trxName)
    {
      super (ctx, EXME_PacienteAler_ID, trxName);
      /** if (EXME_PacienteAler_ID == 0)
        {
			setEXME_PacienteAler_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PacienteAler (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - Client to System
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
      StringBuffer sb = new StringBuffer ("X_EXME_PacienteAler[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Estatus AD_Reference_ID=1200351 */
	public static final int ESTATUS_AD_Reference_ID=1200351;
	/** Confirmed or verified = C */
	public static final String ESTATUS_ConfirmedOrVerified = "C";
	/** Doubt raised = D */
	public static final String ESTATUS_DoubtRaised = "D";
	/** Erroneous = E */
	public static final String ESTATUS_Erroneous = "E";
	/** Pending = P */
	public static final String ESTATUS_Pending = "P";
	/** Suspect = S */
	public static final String ESTATUS_Suspect = "S";
	/** Inactive = U */
	public static final String ESTATUS_Inactive = "U";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{

		if (Estatus == null || Estatus.equals("C") || Estatus.equals("D") || Estatus.equals("E") || Estatus.equals("P") || Estatus.equals("S") || Estatus.equals("U")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200351 - C - D - E - P - S - U");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	public I_EXME_ActPaciente getEXME_ActPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPaciente.Table_Name);
        I_EXME_ActPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Activity.
		@param EXME_ActPaciente_ID 
		Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID)
	{
		if (EXME_ActPaciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPaciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPaciente_ID, Integer.valueOf(EXME_ActPaciente_ID));
	}

	/** Get Patient Activity.
		@return Patient Activity
	  */
	public int getEXME_ActPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Alergia getEXME_Alergia() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Alergia.Table_Name);
        I_EXME_Alergia result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Alergia)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Alergia_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Alergy.
		@param EXME_Alergia_ID 
		Alergy
	  */
	public void setEXME_Alergia_ID (int EXME_Alergia_ID)
	{
		if (EXME_Alergia_ID < 1) 
			set_Value (COLUMNNAME_EXME_Alergia_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Alergia_ID, Integer.valueOf(EXME_Alergia_ID));
	}

	/** Get Alergy.
		@return Alergy
	  */
	public int getEXME_Alergia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Alergia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Allergies Patient.
		@param EXME_PacienteAler_ID 
		Allergies Patient
	  */
	public void setEXME_PacienteAler_ID (int EXME_PacienteAler_ID)
	{
		if (EXME_PacienteAler_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteAler_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PacienteAler_ID, Integer.valueOf(EXME_PacienteAler_ID));
	}

	/** Get Allergies Patient.
		@return Allergies Patient
	  */
	public int getEXME_PacienteAler_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteAler_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_SActiva getEXME_SActiva() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_SActiva.Table_Name);
        I_EXME_SActiva result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_SActiva)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_SActiva_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Active Substance.
		@param EXME_SActiva_ID 
		Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID)
	{
		if (EXME_SActiva_ID < 1) 
			set_Value (COLUMNNAME_EXME_SActiva_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_SActiva_ID, Integer.valueOf(EXME_SActiva_ID));
	}

	/** Get Active Substance.
		@return Active Substance
	  */
	public int getEXME_SActiva_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_SActiva_ID);
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

	/** Set Reaction.
		@param Reaccion Reaction	  */
	public void setReaccion (String Reaccion)
	{
		set_Value (COLUMNNAME_Reaccion, Reaccion);
	}

	/** Get Reaction.
		@return Reaction	  */
	public String getReaccion () 
	{
		return (String)get_Value(COLUMNNAME_Reaccion);
	}

	/** Severidad AD_Reference_ID=1200350 */
	public static final int SEVERIDAD_AD_Reference_ID=1200350;
	/** Mild = MI */
	public static final String SEVERIDAD_Mild = "MI";
	/** Moderate = MO */
	public static final String SEVERIDAD_Moderate = "MO";
	/** Severe = SV */
	public static final String SEVERIDAD_Severe = "SV";
	/** Unknow = U */
	public static final String SEVERIDAD_Unknow = "U";
	/** Set Severity.
		@param Severidad Severity	  */
	public void setSeveridad (String Severidad)
	{

		if (Severidad == null || Severidad.equals("MI") || Severidad.equals("MO") || Severidad.equals("SV") || Severidad.equals("U")); else throw new IllegalArgumentException ("Severidad Invalid value - " + Severidad + " - Reference_ID=1200350 - MI - MO - SV - U");		set_Value (COLUMNNAME_Severidad, Severidad);
	}

	/** Get Severity.
		@return Severity	  */
	public String getSeveridad () 
	{
		return (String)get_Value(COLUMNNAME_Severidad);
	}

	/** Set Alergy Type.
		@param TipoAlergia 
		Alergy Type
	  */
	public void setTipoAlergia (String TipoAlergia)
	{
		set_Value (COLUMNNAME_TipoAlergia, TipoAlergia);
	}

	/** Get Alergy Type.
		@return Alergy Type
	  */
	public String getTipoAlergia () 
	{
		return (String)get_Value(COLUMNNAME_TipoAlergia);
	}
}