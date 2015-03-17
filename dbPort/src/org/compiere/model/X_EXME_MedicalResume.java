/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_MedicalResume
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MedicalResume extends PO implements I_EXME_MedicalResume, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MedicalResume (Properties ctx, int EXME_MedicalResume_ID, String trxName)
    {
      super (ctx, EXME_MedicalResume_ID, trxName);
      /** if (EXME_MedicalResume_ID == 0)
        {
			setEXME_ActPaciente_ID (0);
			setEXME_MedicalResume_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MedicalResume (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MedicalResume[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Clinic Resume Out.
		@param ClinicResumeOut Clinic Resume Out	  */
	public void setClinicResumeOut (String ClinicResumeOut)
	{
		set_Value (COLUMNNAME_ClinicResumeOut, ClinicResumeOut);
	}

	/** Get Clinic Resume Out.
		@return Clinic Resume Out	  */
	public String getClinicResumeOut () 
	{
		return (String)get_Value(COLUMNNAME_ClinicResumeOut);
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
			 throw new IllegalArgumentException ("EXME_ActPaciente_ID is mandatory.");
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

	/** Set Medical Resume.
		@param EXME_MedicalResume_ID Medical Resume	  */
	public void setEXME_MedicalResume_ID (int EXME_MedicalResume_ID)
	{
		if (EXME_MedicalResume_ID < 1)
			 throw new IllegalArgumentException ("EXME_MedicalResume_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MedicalResume_ID, Integer.valueOf(EXME_MedicalResume_ID));
	}

	/** Get Medical Resume.
		@return Medical Resume	  */
	public int getEXME_MedicalResume_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicalResume_ID);
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

	/** Set Forecast.
		@param Forecast Forecast	  */
	public void setForecast (String Forecast)
	{
		set_Value (COLUMNNAME_Forecast, Forecast);
	}

	/** Get Forecast.
		@return Forecast	  */
	public String getForecast () 
	{
		return (String)get_Value(COLUMNNAME_Forecast);
	}

	/** Set Main Treatment.
		@param MainTreatment Main Treatment	  */
	public void setMainTreatment (String MainTreatment)
	{
		set_Value (COLUMNNAME_MainTreatment, MainTreatment);
	}

	/** Get Main Treatment.
		@return Main Treatment	  */
	public String getMainTreatment () 
	{
		return (String)get_Value(COLUMNNAME_MainTreatment);
	}

	/** Set Medicine Prescription.
		@param MedicinePrescription Medicine Prescription	  */
	public void setMedicinePrescription (String MedicinePrescription)
	{
		set_Value (COLUMNNAME_MedicinePrescription, MedicinePrescription);
	}

	/** Get Medicine Prescription.
		@return Medicine Prescription	  */
	public String getMedicinePrescription () 
	{
		return (String)get_Value(COLUMNNAME_MedicinePrescription);
	}

	/** Set Other Treatment.
		@param OtherTreatment Other Treatment	  */
	public void setOtherTreatment (String OtherTreatment)
	{
		set_Value (COLUMNNAME_OtherTreatment, OtherTreatment);
	}

	/** Get Other Treatment.
		@return Other Treatment	  */
	public String getOtherTreatment () 
	{
		return (String)get_Value(COLUMNNAME_OtherTreatment);
	}

	/** Set Rehab.
		@param Rehab Rehab	  */
	public void setRehab (String Rehab)
	{
		set_Value (COLUMNNAME_Rehab, Rehab);
	}

	/** Get Rehab.
		@return Rehab	  */
	public String getRehab () 
	{
		return (String)get_Value(COLUMNNAME_Rehab);
	}
}