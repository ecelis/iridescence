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

/** Generated Model for EXME_CitaMedica
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CitaMedica extends PO implements I_EXME_CitaMedica, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CitaMedica (Properties ctx, int EXME_CitaMedica_ID, String trxName)
    {
      super (ctx, EXME_CitaMedica_ID, trxName);
      /** if (EXME_CitaMedica_ID == 0)
        {
			setConfirmada (false);
			setEstatus (null);
			setEXME_CitaMedica_ID (0);
			setEXME_Medico_ID (0);
			setEXME_Paciente_ID (0);
			setFechaHrCita (new Timestamp( System.currentTimeMillis() ));
			setIsInfoSent (false);
			setName (null);
			setProcessing (false);
			setUtilidad (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_CitaMedica (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_CitaMedica[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Campaign getC_Campaign() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Campaign.Table_Name);
        I_C_Campaign result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Campaign)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Campaign_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Program.
		@param C_Campaign_ID 
		Program
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Program.
		@return Program
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Invoice getC_Invoice() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Invoice.Table_Name);
        I_C_Invoice result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Invoice)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Invoice_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** CitaDe AD_Reference_ID=1200056 */
	public static final int CITADE_AD_Reference_ID=1200056;
	/** First Time = P */
	public static final String CITADE_FirstTime = "P";
	/** Pre Appointment = R */
	public static final String CITADE_PreAppointment = "R";
	/** Subsequent = S */
	public static final String CITADE_Subsequent = "S";
	/** Inter Appoinment = I */
	public static final String CITADE_InterAppoinment = "I";
	/** New Pt. Established = PE */
	public static final String CITADE_NewPtEstablished = "PE";
	/** Pt. Follow Up = FU */
	public static final String CITADE_PtFollowUp = "FU";
	/** Set Appointment Of.
		@param CitaDe 
		Appointment Of
	  */
	public void setCitaDe (String CitaDe)
	{

		if (CitaDe == null || CitaDe.equals("P") || CitaDe.equals("R") || CitaDe.equals("S") || CitaDe.equals("I") || CitaDe.equals("PE") || CitaDe.equals("FU")); else throw new IllegalArgumentException ("CitaDe Invalid value - " + CitaDe + " - Reference_ID=1200056 - P - R - S - I - PE - FU");		set_Value (COLUMNNAME_CitaDe, CitaDe);
	}

	/** Get Appointment Of.
		@return Appointment Of
	  */
	public String getCitaDe () 
	{
		return (String)get_Value(COLUMNNAME_CitaDe);
	}

	/** Set Appointment Number.
		@param CitaNo 
		Appointment number
	  */
	public void setCitaNo (int CitaNo)
	{
		set_Value (COLUMNNAME_CitaNo, Integer.valueOf(CitaNo));
	}

	/** Get Appointment Number.
		@return Appointment number
	  */
	public int getCitaNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CitaNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
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

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		if (DocStatus == null || DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("IP") || DocStatus.equals("WP") || DocStatus.equals("WC")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Duration.
		@param Duracion 
		Duration
	  */
	public void setDuracion (int Duracion)
	{
		set_Value (COLUMNNAME_Duracion, Integer.valueOf(Duracion));
	}

	/** Get Duration.
		@return Duration
	  */
	public int getDuracion () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Duracion);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Age.
		@param Edad 
		Age
	  */
	public void setEdad (int Edad)
	{
		set_Value (COLUMNNAME_Edad, Integer.valueOf(Edad));
	}

	/** Get Age.
		@return Age
	  */
	public int getEdad () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Edad);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Estatus AD_Reference_ID=1000035 */
	public static final int ESTATUS_AD_Reference_ID=1000035;
	/** To Be Confirmed = 0 */
	public static final String ESTATUS_ToBeConfirmed = "0";
	/** Cancelled = 5 */
	public static final String ESTATUS_Cancelled = "5";
	/** Executed = 6 */
	public static final String ESTATUS_Executed = "6";
	/** Confirmed = 7 */
	public static final String ESTATUS_Confirmed = "7";
	/** Closed = 8 */
	public static final String ESTATUS_Closed = "8";
	/** In Process = 3 */
	public static final String ESTATUS_InProcess = "3";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		if (Estatus == null) throw new IllegalArgumentException ("Estatus is mandatory");
		if (Estatus.equals("0") || Estatus.equals("5") || Estatus.equals("6") || Estatus.equals("7") || Estatus.equals("8") || Estatus.equals("3")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1000035 - 0 - 5 - 6 - 7 - 8 - 3");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ActPacienteIndH.Table_Name);
        I_EXME_ActPacienteIndH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ActPacienteIndH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ActPacienteIndH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient's Indication.
		@param EXME_ActPacienteIndH_ID 
		Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID)
	{
		if (EXME_ActPacienteIndH_ID < 1) 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ActPacienteIndH_ID, Integer.valueOf(EXME_ActPacienteIndH_ID));
	}

	/** Get Patient's Indication.
		@return Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteIndH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
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

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Unit.
		@param EXME_EstServ_ID 
		Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Unit.
		@return Service Unit
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GrupoCuestionario getEXME_GrupoCuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GrupoCuestionario.Table_Name);
        I_EXME_GrupoCuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GrupoCuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GrupoCuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Form Group.
		@param EXME_GrupoCuestionario_ID 
		Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID)
	{
		if (EXME_GrupoCuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_GrupoCuestionario_ID, Integer.valueOf(EXME_GrupoCuestionario_ID));
	}

	/** Get Form Group.
		@return Form Group
	  */
	public int getEXME_GrupoCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GrupoCuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Intervencion.Table_Name);
        I_EXME_Intervencion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Intervencion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Intervencion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set CPT Code.
		@param EXME_Intervencion_ID 
		Current Procedural Terminology (CPT)
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get CPT Code.
		@return Current Procedural Terminology (CPT)
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Level_Of_Service getEXME_Level_Of_Service() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Level_Of_Service.Table_Name);
        I_EXME_Level_Of_Service result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Level_Of_Service)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Level_Of_Service_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Level of service.
		@param EXME_Level_Of_Service_ID 
		level of service
	  */
	public void setEXME_Level_Of_Service_ID (int EXME_Level_Of_Service_ID)
	{
		if (EXME_Level_Of_Service_ID < 1) 
			set_Value (COLUMNNAME_EXME_Level_Of_Service_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Level_Of_Service_ID, Integer.valueOf(EXME_Level_Of_Service_ID));
	}

	/** Get Level of service.
		@return level of service
	  */
	public int getEXME_Level_Of_Service_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Level_Of_Service_ID);
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

	/** Set EXME_MEDICO_ORIG.
		@param EXME_MEDICO_ORIG EXME_MEDICO_ORIG	  */
	public void setEXME_MEDICO_ORIG (int EXME_MEDICO_ORIG)
	{
		set_Value (COLUMNNAME_EXME_MEDICO_ORIG, Integer.valueOf(EXME_MEDICO_ORIG));
	}

	/** Get EXME_MEDICO_ORIG.
		@return EXME_MEDICO_ORIG	  */
	public int getEXME_MEDICO_ORIG () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MEDICO_ORIG);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MotivoCancel getEXME_MotivoCancel() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoCancel.Table_Name);
        I_EXME_MotivoCancel result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoCancel)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoCancel_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cancel Reason.
		@param EXME_MotivoCancel_ID 
		Cancel Reason
	  */
	public void setEXME_MotivoCancel_ID (int EXME_MotivoCancel_ID)
	{
		if (EXME_MotivoCancel_ID < 1) 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MotivoCancel_ID, Integer.valueOf(EXME_MotivoCancel_ID));
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public int getEXME_MotivoCancel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoCancel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_EXME_TipoPaciente getEXME_TipoPaciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoPaciente.Table_Name);
        I_EXME_TipoPaciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoPaciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoPaciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Type of Patient.
		@param EXME_TipoPaciente_ID 
		Type of Patient
	  */
	public void setEXME_TipoPaciente_ID (int EXME_TipoPaciente_ID)
	{
		if (EXME_TipoPaciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_TipoPaciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TipoPaciente_ID, Integer.valueOf(EXME_TipoPaciente_ID));
	}

	/** Get Type of Patient.
		@return Type of Patient
	  */
	public int getEXME_TipoPaciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPaciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Teatment.
		@param EXME_Tratamiento_ID 
		Teatment
	  */
	public void setEXME_Tratamiento_ID (int EXME_Tratamiento_ID)
	{
		if (EXME_Tratamiento_ID < 1) 
			set_Value (COLUMNNAME_EXME_Tratamiento_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Tratamiento_ID, Integer.valueOf(EXME_Tratamiento_ID));
	}

	/** Get Teatment.
		@return Teatment
	  */
	public int getEXME_Tratamiento_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamiento_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Tratamientos_Sesion.Table_Name);
        I_EXME_Tratamientos_Sesion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Tratamientos_Sesion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Tratamientos_Sesion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Treatment sessions.
		@param EXME_Tratamientos_Sesion_ID Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID)
	{
		if (EXME_Tratamientos_Sesion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Tratamientos_Sesion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Tratamientos_Sesion_ID, Integer.valueOf(EXME_Tratamientos_Sesion_ID));
	}

	/** Get Treatment sessions.
		@return Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Tratamientos_Sesion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Cancel date.
		@param FechaCancel 
		Cancel date
	  */
	public void setFechaCancel (Timestamp FechaCancel)
	{
		set_Value (COLUMNNAME_FechaCancel, FechaCancel);
	}

	/** Get Cancel date.
		@return Cancel date
	  */
	public Timestamp getFechaCancel () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCancel);
	}

	/** Set Confirmation Date.
		@param FechaConfirm Confirmation Date	  */
	public void setFechaConfirm (Timestamp FechaConfirm)
	{
		set_Value (COLUMNNAME_FechaConfirm, FechaConfirm);
	}

	/** Get Confirmation Date.
		@return Confirmation Date	  */
	public Timestamp getFechaConfirm () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaConfirm);
	}

	/** Set Date.
		@param FechaHrCita 
		Date
	  */
	public void setFechaHrCita (Timestamp FechaHrCita)
	{
		if (FechaHrCita == null)
			throw new IllegalArgumentException ("FechaHrCita is mandatory.");
		set_Value (COLUMNNAME_FechaHrCita, FechaHrCita);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFechaHrCita () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaHrCita);
	}

	/** Set Finish Hr and Date.
		@param FechaHrFin Finish Hr and Date	  */
	public void setFechaHrFin (Timestamp FechaHrFin)
	{
		set_Value (COLUMNNAME_FechaHrFin, FechaHrFin);
	}

	/** Get Finish Hr and Date.
		@return Finish Hr and Date	  */
	public Timestamp getFechaHrFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaHrFin);
	}

	/** Set Initial Hr and Date.
		@param FechaHrIni Initial Hr and Date	  */
	public void setFechaHrIni (Timestamp FechaHrIni)
	{
		set_Value (COLUMNNAME_FechaHrIni, FechaHrIni);
	}

	/** Get Initial Hr and Date.
		@return Initial Hr and Date	  */
	public Timestamp getFechaHrIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaHrIni);
	}

	/** Set Final counseling hour.
		@param FinCounseling 
		Final counseling hour
	  */
	public void setFinCounseling (Timestamp FinCounseling)
	{
		set_Value (COLUMNNAME_FinCounseling, FinCounseling);
	}

	/** Get Final counseling hour.
		@return Final counseling hour
	  */
	public Timestamp getFinCounseling () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FinCounseling);
	}

	/** Set Arrival Time.
		@param HoraLlegada 
		Arrival Time
	  */
	public void setHoraLlegada (Timestamp HoraLlegada)
	{
		set_Value (COLUMNNAME_HoraLlegada, HoraLlegada);
	}

	/** Get Arrival Time.
		@return Arrival Time
	  */
	public Timestamp getHoraLlegada () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HoraLlegada);
	}

	/** Set Initial counseling hour.
		@param IniCounseling 
		Initial counseling hour
	  */
	public void setIniCounseling (Timestamp IniCounseling)
	{
		set_Value (COLUMNNAME_IniCounseling, IniCounseling);
	}

	/** Get Initial counseling hour.
		@return Initial counseling hour
	  */
	public Timestamp getIniCounseling () 
	{
		return (Timestamp)get_Value(COLUMNNAME_IniCounseling);
	}

	/** Set Send Info.
		@param IsInfoSent 
		Send informational messages and copies
	  */
	public void setIsInfoSent (boolean IsInfoSent)
	{
		set_Value (COLUMNNAME_IsInfoSent, Boolean.valueOf(IsInfoSent));
	}

	/** Get Send Info.
		@return Send informational messages and copies
	  */
	public boolean isInfoSent () 
	{
		Object oo = get_Value(COLUMNNAME_IsInfoSent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Cancel Reason.
		@param MotivoCancel 
		Cancel Reason
	  */
	public void setMotivoCancel (String MotivoCancel)
	{
		set_Value (COLUMNNAME_MotivoCancel, MotivoCancel);
	}

	/** Get Cancel Reason.
		@return Cancel Reason
	  */
	public String getMotivoCancel () 
	{
		return (String)get_Value(COLUMNNAME_MotivoCancel);
	}

	/** Set S.W. Motive.
		@param MotivoTS 
		Social Work Motive
	  */
	public void setMotivoTS (String MotivoTS)
	{
		set_Value (COLUMNNAME_MotivoTS, MotivoTS);
	}

	/** Get S.W. Motive.
		@return Social Work Motive
	  */
	public String getMotivoTS () 
	{
		return (String)get_Value(COLUMNNAME_MotivoTS);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** NextVisit AD_Reference_ID=1200568 */
	public static final int NEXTVISIT_AD_Reference_ID=1200568;
	/** D = D */
	public static final String NEXTVISIT_D = "D";
	/** M = M */
	public static final String NEXTVISIT_M = "M";
	/** W = W */
	public static final String NEXTVISIT_W = "W";
	/** Set Next Visit.
		@param NextVisit Next Visit	  */
	public void setNextVisit (String NextVisit)
	{

		if (NextVisit == null || NextVisit.equals("D") || NextVisit.equals("M") || NextVisit.equals("W")); else throw new IllegalArgumentException ("NextVisit Invalid value - " + NextVisit + " - Reference_ID=1200568 - D - M - W");		set_Value (COLUMNNAME_NextVisit, NextVisit);
	}

	/** Get Next Visit.
		@return Next Visit	  */
	public String getNextVisit () 
	{
		return (String)get_Value(COLUMNNAME_NextVisit);
	}

	/** Set Notified.
		@param Notified Notified	  */
	public void setNotified (boolean Notified)
	{
		set_Value (COLUMNNAME_Notified, Boolean.valueOf(Notified));
	}

	/** Get Notified.
		@return Notified	  */
	public boolean isNotified () 
	{
		Object oo = get_Value(COLUMNNAME_Notified);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Promotion Number.
		@param N_Promo Promotion Number	  */
	public void setN_Promo (String N_Promo)
	{
		set_Value (COLUMNNAME_N_Promo, N_Promo);
	}

	/** Get Promotion Number.
		@return Promotion Number	  */
	public String getN_Promo () 
	{
		return (String)get_Value(COLUMNNAME_N_Promo);
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

	/** Patient_Type AD_Reference_ID=1200642 */
	public static final int PATIENT_TYPE_AD_Reference_ID=1200642;
	/** New = N */
	public static final String PATIENT_TYPE_New = "N";
	/** Established = E */
	public static final String PATIENT_TYPE_Established = "E";
	/** Set Patient Type.
		@param Patient_Type Patient Type	  */
	public void setPatient_Type (String Patient_Type)
	{

		if (Patient_Type == null || Patient_Type.equals("N") || Patient_Type.equals("E")); else throw new IllegalArgumentException ("Patient_Type Invalid value - " + Patient_Type + " - Reference_ID=1200642 - N - E");		set_Value (COLUMNNAME_Patient_Type, Patient_Type);
	}

	/** Get Patient Type.
		@return Patient Type	  */
	public String getPatient_Type () 
	{
		return (String)get_Value(COLUMNNAME_Patient_Type);
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

	/** Set Rank.
		@param Rank 
		Rank
	  */
	public void setRank (int Rank)
	{
		set_Value (COLUMNNAME_Rank, Integer.valueOf(Rank));
	}

	/** Get Rank.
		@return Rank
	  */
	public int getRank () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Rank);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reference of the First  Appointment.
		@param Ref_CitaMedica_ID 
		Reference of the First  Appointment
	  */
	public void setRef_CitaMedica_ID (int Ref_CitaMedica_ID)
	{
		if (Ref_CitaMedica_ID < 1) 
			set_Value (COLUMNNAME_Ref_CitaMedica_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_CitaMedica_ID, Integer.valueOf(Ref_CitaMedica_ID));
	}

	/** Get Reference of the First  Appointment.
		@return Reference of the First  Appointment
	  */
	public int getRef_CitaMedica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_CitaMedica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Substitute.
		@param Substitute_ID 
		Entity which can be used in place of this entity
	  */
	public void setSubstitute_ID (int Substitute_ID)
	{
		if (Substitute_ID < 1) 
			set_Value (COLUMNNAME_Substitute_ID, null);
		else 
			set_Value (COLUMNNAME_Substitute_ID, Integer.valueOf(Substitute_ID));
	}

	/** Get Substitute.
		@return Entity which can be used in place of this entity
	  */
	public int getSubstitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Substitute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Finished By.
		@param TerminadoPor Finished By	  */
	public void setTerminadoPor (int TerminadoPor)
	{
		set_Value (COLUMNNAME_TerminadoPor, Integer.valueOf(TerminadoPor));
	}

	/** Get Finished By.
		@return Finished By	  */
	public int getTerminadoPor () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TerminadoPor);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Utility.
		@param Utilidad 
		Utility
	  */
	public void setUtilidad (boolean Utilidad)
	{
		set_Value (COLUMNNAME_Utilidad, Boolean.valueOf(Utilidad));
	}

	/** Get Utility.
		@return Utility
	  */
	public boolean isUtilidad () 
	{
		Object oo = get_Value(COLUMNNAME_Utilidad);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}