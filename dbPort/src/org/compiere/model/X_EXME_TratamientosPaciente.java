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

/** Generated Model for EXME_TratamientosPaciente
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_TratamientosPaciente extends PO implements I_EXME_TratamientosPaciente, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TratamientosPaciente (Properties ctx, int EXME_TratamientosPaciente_ID, String trxName)
    {
      super (ctx, EXME_TratamientosPaciente_ID, trxName);
      /** if (EXME_TratamientosPaciente_ID == 0)
        {
			setEXME_CitaMedica_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_Tratamientos_ID (0);
			setEXME_TratamientosPaciente_ID (0);
			setFecha_Tratamiento (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_TratamientosPaciente (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TratamientosPaciente[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Appointment Number.
		@param CitaNo 
		Appointment number
	  */
	public void setCitaNo (BigDecimal CitaNo)
	{
		set_Value (COLUMNNAME_CitaNo, CitaNo);
	}

	/** Get Appointment Number.
		@return Appointment number
	  */
	public BigDecimal getCitaNo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CitaNo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Is Odontogram.
		@param EsOdontograma 
		Is Odontogram
	  */
	public void setEsOdontograma (boolean EsOdontograma)
	{
		set_Value (COLUMNNAME_EsOdontograma, Boolean.valueOf(EsOdontograma));
	}

	/** Get Is Odontogram.
		@return Is Odontogram
	  */
	public boolean isEsOdontograma () 
	{
		Object oo = get_Value(COLUMNNAME_EsOdontograma);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
			 throw new IllegalArgumentException ("EXME_CitaMedica_ID is mandatory.");
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

	public I_EXME_MO_PiezaDental getEXME_MO_PiezaDental() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MO_PiezaDental.Table_Name);
        I_EXME_MO_PiezaDental result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MO_PiezaDental)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MO_PiezaDental_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Dental Piece.
		@param EXME_MO_PiezaDental_ID Dental Piece	  */
	public void setEXME_MO_PiezaDental_ID (int EXME_MO_PiezaDental_ID)
	{
		if (EXME_MO_PiezaDental_ID < 1) 
			set_Value (COLUMNNAME_EXME_MO_PiezaDental_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MO_PiezaDental_ID, Integer.valueOf(EXME_MO_PiezaDental_ID));
	}

	/** Get Dental Piece.
		@return Dental Piece	  */
	public int getEXME_MO_PiezaDental_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_PiezaDental_ID);
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

	public I_EXME_Tratamientos getEXME_Tratamientos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos.Table_Name);
        I_EXME_Tratamientos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatments.
		@param EXME_Tratamientos_ID 
		Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID)
	{
		if (EXME_Tratamientos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Tratamientos_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Tratamientos_ID, Integer.valueOf(EXME_Tratamientos_ID));
	}

	/** Get Treatments.
		@return Treatments
	  */
	public int getEXME_Tratamientos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Treatments.
		@param EXME_TratamientosPaciente_ID Patient Treatments	  */
	public void setEXME_TratamientosPaciente_ID (int EXME_TratamientosPaciente_ID)
	{
		if (EXME_TratamientosPaciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_TratamientosPaciente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TratamientosPaciente_ID, Integer.valueOf(EXME_TratamientosPaciente_ID));
	}

	/** Get Patient Treatments.
		@return Patient Treatments	  */
	public int getEXME_TratamientosPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TratamientosPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Treatment Date.
		@param Fecha_Tratamiento Treatment Date	  */
	public void setFecha_Tratamiento (Timestamp Fecha_Tratamiento)
	{
		if (Fecha_Tratamiento == null)
			throw new IllegalArgumentException ("Fecha_Tratamiento is mandatory.");
		set_Value (COLUMNNAME_Fecha_Tratamiento, Fecha_Tratamiento);
	}

	/** Get Treatment Date.
		@return Treatment Date	  */
	public Timestamp getFecha_Tratamiento () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Tratamiento);
	}

	/** Set Schedule Appointments?.
		@param ProgramarCitas Schedule Appointments?	  */
	public void setProgramarCitas (boolean ProgramarCitas)
	{
		set_Value (COLUMNNAME_ProgramarCitas, Boolean.valueOf(ProgramarCitas));
	}

	/** Get Schedule Appointments?.
		@return Schedule Appointments?	  */
	public boolean isProgramarCitas () 
	{
		Object oo = get_Value(COLUMNNAME_ProgramarCitas);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Specialty Reference.
		@param Ref_EXME_Especialidad_ID Specialty Reference	  */
	public void setRef_EXME_Especialidad_ID (int Ref_EXME_Especialidad_ID)
	{
		if (Ref_EXME_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_Ref_EXME_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_EXME_Especialidad_ID, Integer.valueOf(Ref_EXME_Especialidad_ID));
	}

	/** Get Specialty Reference.
		@return Specialty Reference	  */
	public int getRef_EXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Status AD_Reference_ID=131 */
	public static final int STATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String STATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String STATUS_Completed = "CO";
	/** Approved = AP */
	public static final String STATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String STATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String STATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String STATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String STATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String STATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String STATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String STATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String STATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String STATUS_WaitingConfirmation = "WC";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		if (Status == null || Status.equals("DR") || Status.equals("CO") || Status.equals("AP") || Status.equals("NA") || Status.equals("VO") || Status.equals("IN") || Status.equals("RE") || Status.equals("CL") || Status.equals("??") || Status.equals("IP") || Status.equals("WP") || Status.equals("WC")); else throw new IllegalArgumentException ("Status Invalid value - " + Status + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}
}