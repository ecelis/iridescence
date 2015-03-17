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

/** Generated Model for EXME_CtaPacDatos
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_CtaPacDatos extends PO implements I_EXME_CtaPacDatos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CtaPacDatos (Properties ctx, int EXME_CtaPacDatos_ID, String trxName)
    {
      super (ctx, EXME_CtaPacDatos_ID, trxName);
      /** if (EXME_CtaPacDatos_ID == 0)
        {
			setEXME_CtaPacDatos_ID (0);
			setEXME_CtaPac_ID (0);
			setEXME_Paciente_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CtaPacDatos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CtaPacDatos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Admit Source.
		@param AdmitSource 
		Admit Source
	  */
	public void setAdmitSource (int AdmitSource)
	{
		set_Value (COLUMNNAME_AdmitSource, Integer.valueOf(AdmitSource));
	}

	/** Get Admit Source.
		@return Admit Source
	  */
	public int getAdmitSource () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AdmitSource);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Admit Type.
		@param AdmitType 
		Admit Type
	  */
	public void setAdmitType (int AdmitType)
	{
		set_Value (COLUMNNAME_AdmitType, Integer.valueOf(AdmitType));
	}

	/** Get Admit Type.
		@return Admit Type
	  */
	public int getAdmitType () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AdmitType);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Arrival Date.
		@param ArrivalDate 
		Arrival Date
	  */
	public void setArrivalDate (Timestamp ArrivalDate)
	{
		set_Value (COLUMNNAME_ArrivalDate, ArrivalDate);
	}

	/** Get Arrival Date.
		@return Arrival Date
	  */
	public Timestamp getArrivalDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ArrivalDate);
	}

	/** Set Arrival Mode.
		@param ArrivalMode 
		Arrival Mode
	  */
	public void setArrivalMode (int ArrivalMode)
	{
		set_Value (COLUMNNAME_ArrivalMode, Integer.valueOf(ArrivalMode));
	}

	/** Get Arrival Mode.
		@return Arrival Mode
	  */
	public int getArrivalMode () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ArrivalMode);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Authorization.
		@param Autorizacion 
		Authorization
	  */
	public void setAutorizacion (String Autorizacion)
	{
		set_Value (COLUMNNAME_Autorizacion, Autorizacion);
	}

	/** Get Authorization.
		@return Authorization
	  */
	public String getAutorizacion () 
	{
		return (String)get_Value(COLUMNNAME_Autorizacion);
	}

	/** Set Complementary Data.
		@param EXME_CtaPacDatos_ID 
		Complementary Data
	  */
	public void setEXME_CtaPacDatos_ID (int EXME_CtaPacDatos_ID)
	{
		if (EXME_CtaPacDatos_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacDatos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CtaPacDatos_ID, Integer.valueOf(EXME_CtaPacDatos_ID));
	}

	/** Get Complementary Data.
		@return Complementary Data
	  */
	public int getEXME_CtaPacDatos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacDatos_ID);
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
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
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

	/** Set Final Diagnostic.
		@param EXME_DiagnosticoFin_ID 
		Final Diagnostic
	  */
	public void setEXME_DiagnosticoFin_ID (int EXME_DiagnosticoFin_ID)
	{
		if (EXME_DiagnosticoFin_ID < 1) 
			set_Value (COLUMNNAME_EXME_DiagnosticoFin_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DiagnosticoFin_ID, Integer.valueOf(EXME_DiagnosticoFin_ID));
	}

	/** Get Final Diagnostic.
		@return Final Diagnostic
	  */
	public int getEXME_DiagnosticoFin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosticoFin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Initial Diagnostic.
		@param EXME_DiagnosticoIni_ID 
		Initial Diagnostic
	  */
	public void setEXME_DiagnosticoIni_ID (int EXME_DiagnosticoIni_ID)
	{
		if (EXME_DiagnosticoIni_ID < 1) 
			set_Value (COLUMNNAME_EXME_DiagnosticoIni_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_DiagnosticoIni_ID, Integer.valueOf(EXME_DiagnosticoIni_ID));
	}

	/** Get Initial Diagnostic.
		@return Initial Diagnostic
	  */
	public int getEXME_DiagnosticoIni_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DiagnosticoIni_ID);
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

	/** Set Intervention.
		@param EXME_Intervencion_ID 
		Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID)
	{
		if (EXME_Intervencion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Intervencion_ID, Integer.valueOf(EXME_Intervencion_ID));
	}

	/** Get Intervention.
		@return Intervention
	  */
	public int getEXME_Intervencion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Intervencion_ID);
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

	public I_EXME_TypeOfBill getEXME_TypeOfBill() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TypeOfBill.Table_Name);
        I_EXME_TypeOfBill result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TypeOfBill)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TypeOfBill_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set EXME_TypeOfBill_ID.
		@param EXME_TypeOfBill_ID 
		EXME_TypeOfBill_ID
	  */
	public void setEXME_TypeOfBill_ID (int EXME_TypeOfBill_ID)
	{
		if (EXME_TypeOfBill_ID < 1) 
			set_Value (COLUMNNAME_EXME_TypeOfBill_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_TypeOfBill_ID, Integer.valueOf(EXME_TypeOfBill_ID));
	}

	/** Get EXME_TypeOfBill_ID.
		@return EXME_TypeOfBill_ID
	  */
	public int getEXME_TypeOfBill_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TypeOfBill_ID);
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

	/** Set Date of Symptom Onset.
		@param FechaSintoma Date of Symptom Onset	  */
	public void setFechaSintoma (Timestamp FechaSintoma)
	{
		set_Value (COLUMNNAME_FechaSintoma, FechaSintoma);
	}

	/** Get Date of Symptom Onset.
		@return Date of Symptom Onset	  */
	public Timestamp getFechaSintoma () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaSintoma);
	}

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (String Folio)
	{
		set_Value (COLUMNNAME_Folio, Folio);
	}

	/** Get Folio.
		@return Folio	  */
	public String getFolio () 
	{
		return (String)get_Value(COLUMNNAME_Folio);
	}

	/** Set IsStudent.
		@param IsStudent IsStudent	  */
	public void setIsStudent (boolean IsStudent)
	{
		set_Value (COLUMNNAME_IsStudent, Boolean.valueOf(IsStudent));
	}

	/** Get IsStudent.
		@return IsStudent	  */
	public boolean isStudent () 
	{
		Object oo = get_Value(COLUMNNAME_IsStudent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Company Physician.
		@param MedicoCia 
		Company Physician
	  */
	public void setMedicoCia (String MedicoCia)
	{
		set_Value (COLUMNNAME_MedicoCia, MedicoCia);
	}

	/** Get Company Physician.
		@return Company Physician
	  */
	public String getMedicoCia () 
	{
		return (String)get_Value(COLUMNNAME_MedicoCia);
	}

	/** RelatedTo AD_Reference_ID=1200550 */
	public static final int RELATEDTO_AD_Reference_ID=1200550;
	/** Workplace Accident = AW */
	public static final String RELATEDTO_WorkplaceAccident = "AW";
	/** Accident school Center = AS */
	public static final String RELATEDTO_AccidentSchoolCenter = "AS";
	/** Auto Accident = AV */
	public static final String RELATEDTO_AutoAccident = "AV";
	/** Other = OT */
	public static final String RELATEDTO_Other = "OT";
	/** Set Related To.
		@param RelatedTo Related To	  */
	public void setRelatedTo (String RelatedTo)
	{

		if (RelatedTo == null || RelatedTo.equals("AW") || RelatedTo.equals("AS") || RelatedTo.equals("AV") || RelatedTo.equals("OT")); else throw new IllegalArgumentException ("RelatedTo Invalid value - " + RelatedTo + " - Reference_ID=1200550 - AW - AS - AV - OT");		set_Value (COLUMNNAME_RelatedTo, RelatedTo);
	}

	/** Get Related To.
		@return Related To	  */
	public String getRelatedTo () 
	{
		return (String)get_Value(COLUMNNAME_RelatedTo);
	}

	/** Set Service.
		@param Service 
		Service
	  */
	public void setService (int Service)
	{
		set_Value (COLUMNNAME_Service, Integer.valueOf(Service));
	}

	/** Get Service.
		@return Service
	  */
	public int getService () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Service);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set State.
		@param State State	  */
	public void setState (int State)
	{
		set_Value (COLUMNNAME_State, Integer.valueOf(State));
	}

	/** Get State.
		@return State	  */
	public int getState () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_State);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TypeStudent AD_Reference_ID=1200549 */
	public static final int TYPESTUDENT_AD_Reference_ID=1200549;
	/** Full Time = FT */
	public static final String TYPESTUDENT_FullTime = "FT";
	/** Part Time = PT */
	public static final String TYPESTUDENT_PartTime = "PT";
	/** Set Type Student.
		@param TypeStudent Type Student	  */
	public void setTypeStudent (String TypeStudent)
	{

		if (TypeStudent == null || TypeStudent.equals("FT") || TypeStudent.equals("PT")); else throw new IllegalArgumentException ("TypeStudent Invalid value - " + TypeStudent + " - Reference_ID=1200549 - FT - PT");		set_Value (COLUMNNAME_TypeStudent, TypeStudent);
	}

	/** Get Type Student.
		@return Type Student	  */
	public String getTypeStudent () 
	{
		return (String)get_Value(COLUMNNAME_TypeStudent);
	}
}