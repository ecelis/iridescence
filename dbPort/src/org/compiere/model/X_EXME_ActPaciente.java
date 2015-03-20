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

/** Generated Model for EXME_ActPaciente
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ActPaciente extends PO implements I_EXME_ActPaciente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ActPaciente (Properties ctx, int EXME_ActPaciente_ID, String trxName)
    {
      super (ctx, EXME_ActPaciente_ID, trxName);
      /** if (EXME_ActPaciente_ID == 0)
        {
			setEXME_ActPaciente_ID (0);
			setEXME_Especialidad_ID (0);
			setEXME_Paciente_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setIsPregnant (false);
			setName (null);
			setProcessed (false);
			setTipoArea (null);
			setVacunaPendiente (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_ActPaciente (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ActPaciente[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_ValueNoCheck (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Patient Activity.
		@param EXME_ActPaciente_ID 
		Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID)
	{
		if (EXME_ActPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPaciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ActPaciente_ID, Integer.valueOf(EXME_ActPaciente_ID));
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
			set_ValueNoCheck (COLUMNNAME_EXME_CitaMedica_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_CitaMedica_ID, Integer.valueOf(EXME_CitaMedica_ID));
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Especialidad_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
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

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_Medico_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
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
			set_ValueNoCheck (COLUMNNAME_EXME_MotivoCita_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_MotivoCita_ID, Integer.valueOf(EXME_MotivoCita_ID));
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

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
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

	public I_EXME_ProgQuiro getEXME_ProgQuiro() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ProgQuiro.Table_Name);
        I_EXME_ProgQuiro result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ProgQuiro)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ProgQuiro_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Schedule of Surgery Room.
		@param EXME_ProgQuiro_ID 
		Schedule of Surgery Room
	  */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID)
	{
		if (EXME_ProgQuiro_ID < 1) 
			set_Value (COLUMNNAME_EXME_ProgQuiro_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ProgQuiro_ID, Integer.valueOf(EXME_ProgQuiro_ID));
	}

	/** Get Schedule of Surgery Room.
		@return Schedule of Surgery Room
	  */
	public int getEXME_ProgQuiro_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ProgQuiro_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** EstadoSalud AD_Reference_ID=1200194 */
	public static final int ESTADOSALUD_AD_Reference_ID=1200194;
	/** Stable = E */
	public static final String ESTADOSALUD_Stable = "E";
	/** Delicate = D */
	public static final String ESTADOSALUD_Delicate = "D";
	/** Serious = G */
	public static final String ESTADOSALUD_Serious = "G";
	/** Set Health .
		@param EstadoSalud Health 	  */
	public void setEstadoSalud (String EstadoSalud)
	{

		if (EstadoSalud == null || EstadoSalud.equals("E") || EstadoSalud.equals("D") || EstadoSalud.equals("G")); else throw new IllegalArgumentException ("EstadoSalud Invalid value - " + EstadoSalud + " - Reference_ID=1200194 - E - D - G");		set_Value (COLUMNNAME_EstadoSalud, EstadoSalud);
	}

	/** Get Health .
		@return Health 	  */
	public String getEstadoSalud () 
	{
		return (String)get_Value(COLUMNNAME_EstadoSalud);
	}

	/** Set Height.
		@param Estatura 
		Height
	  */
	public void setEstatura (BigDecimal Estatura)
	{
		set_Value (COLUMNNAME_Estatura, Estatura);
	}

	/** Get Height.
		@return Height
	  */
	public BigDecimal getEstatura () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Estatura);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Is Pregnant.
		@param IsPregnant 
		Indicates pregnancy
	  */
	public void setIsPregnant (boolean IsPregnant)
	{
		set_Value (COLUMNNAME_IsPregnant, Boolean.valueOf(IsPregnant));
	}

	/** Get Is Pregnant.
		@return Indicates pregnancy
	  */
	public boolean isPregnant () 
	{
		Object oo = get_Value(COLUMNNAME_IsPregnant);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Corporal Mass.
		@param MasaCorporal 
		Corporal Mass of the patient
	  */
	public void setMasaCorporal (BigDecimal MasaCorporal)
	{
		set_Value (COLUMNNAME_MasaCorporal, MasaCorporal);
	}

	/** Get Corporal Mass.
		@return Corporal Mass of the patient
	  */
	public BigDecimal getMasaCorporal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MasaCorporal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Weight.
		@param Peso 
		Weight
	  */
	public void setPeso (BigDecimal Peso)
	{
		set_Value (COLUMNNAME_Peso, Peso);
	}

	/** Get Weight.
		@return Weight
	  */
	public BigDecimal getPeso () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Peso);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Pregnancy Months.
		@param PregnancyMonths 
		Pregnancy Months
	  */
	public void setPregnancyMonths (BigDecimal PregnancyMonths)
	{
		set_Value (COLUMNNAME_PregnancyMonths, PregnancyMonths);
	}

	/** Get Pregnancy Months.
		@return Pregnancy Months
	  */
	public BigDecimal getPregnancyMonths () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PregnancyMonths);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_ValueNoCheck (COLUMNNAME_Processed, Boolean.valueOf(Processed));
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
		set_ValueNoCheck (COLUMNNAME_Processing, Boolean.valueOf(Processing));
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

	/** TipoArea AD_Reference_ID=1000039 */
	public static final int TIPOAREA_AD_Reference_ID=1000039;
	/** Hospitalization = H */
	public static final String TIPOAREA_Hospitalization = "H";
	/** Emergency = U */
	public static final String TIPOAREA_Emergency = "U";
	/** Ambulatory = A */
	public static final String TIPOAREA_Ambulatory = "A";
	/** Medical Consultation = C */
	public static final String TIPOAREA_MedicalConsultation = "C";
	/** Procedures = P */
	public static final String TIPOAREA_Procedures = "P";
	/** Medical Records = R */
	public static final String TIPOAREA_MedicalRecords = "R";
	/** Other = O */
	public static final String TIPOAREA_Other = "O";
	/** External = E */
	public static final String TIPOAREA_External = "E";
	/** Admission = D */
	public static final String TIPOAREA_Admission = "D";
	/** Social Work = T */
	public static final String TIPOAREA_SocialWork = "T";
	/** Social Comunication = S */
	public static final String TIPOAREA_SocialComunication = "S";
	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null) throw new IllegalArgumentException ("TipoArea is mandatory");
		if (TipoArea.equals("H") || TipoArea.equals("U") || TipoArea.equals("A") || TipoArea.equals("C") || TipoArea.equals("P") || TipoArea.equals("R") || TipoArea.equals("O") || TipoArea.equals("E") || TipoArea.equals("D") || TipoArea.equals("T") || TipoArea.equals("S")); else throw new IllegalArgumentException ("TipoArea Invalid value - " + TipoArea + " - Reference_ID=1000039 - H - U - A - C - P - R - O - E - D - T - S");		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}

	/** Set Vaccine Pending.
		@param VacunaPendiente Vaccine Pending	  */
	public void setVacunaPendiente (boolean VacunaPendiente)
	{
		set_Value (COLUMNNAME_VacunaPendiente, Boolean.valueOf(VacunaPendiente));
	}

	/** Get Vaccine Pending.
		@return Vaccine Pending	  */
	public boolean isVacunaPendiente () 
	{
		Object oo = get_Value(COLUMNNAME_VacunaPendiente);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Effective Clinical History.
		@param VigenciaHC Effective Clinical History	  */
	public void setVigenciaHC (Timestamp VigenciaHC)
	{
		set_Value (COLUMNNAME_VigenciaHC, VigenciaHC);
	}

	/** Get Effective Clinical History.
		@return Effective Clinical History	  */
	public Timestamp getVigenciaHC () 
	{
		return (Timestamp)get_Value(COLUMNNAME_VigenciaHC);
	}
}