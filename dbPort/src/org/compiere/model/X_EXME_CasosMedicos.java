/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_CasosMedicos
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CasosMedicos extends PO implements I_EXME_CasosMedicos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CasosMedicos (Properties ctx, int EXME_CasosMedicos_ID, String trxName)
    {
      super (ctx, EXME_CasosMedicos_ID, trxName);
      /** if (EXME_CasosMedicos_ID == 0)
        {
			setAverPrev (false);
			setDateOrdered (new Timestamp( System.currentTimeMillis() ));
			setEXME_CasosMedicos_ID (0);
			setEXME_Paciente_ID (0);
			setJuridico (false);
			setMinStPub (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_CasosMedicos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CasosMedicos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Preliminary Investigation..
		@param AverPrev 
		Preliminary Investigation.
	  */
	public void setAverPrev (boolean AverPrev)
	{
		set_Value (COLUMNNAME_AverPrev, Boolean.valueOf(AverPrev));
	}

	/** Get Preliminary Investigation..
		@return Preliminary Investigation.
	  */
	public boolean isAverPrev () 
	{
		Object oo = get_Value(COLUMNNAME_AverPrev);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		if (DateOrdered == null)
			throw new IllegalArgumentException ("DateOrdered is mandatory.");
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** Set Diagnostic.
		@param Diagnostico 
		Diagnostic
	  */
	public void setDiagnostico (String Diagnostico)
	{
		set_Value (COLUMNNAME_Diagnostico, Diagnostico);
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public String getDiagnostico () 
	{
		return (String)get_Value(COLUMNNAME_Diagnostico);
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

	/** Set Medical-Legal Cases.
		@param EXME_CasosMedicos_ID 
		Medical-Legal Cases
	  */
	public void setEXME_CasosMedicos_ID (int EXME_CasosMedicos_ID)
	{
		if (EXME_CasosMedicos_ID < 1)
			 throw new IllegalArgumentException ("EXME_CasosMedicos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CasosMedicos_ID, Integer.valueOf(EXME_CasosMedicos_ID));
	}

	/** Get Medical-Legal Cases.
		@return Medical-Legal Cases
	  */
	public int getEXME_CasosMedicos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CasosMedicos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1) 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Diagnostico_ID, Integer.valueOf(EXME_Diagnostico_ID));
	}

	/** Get Diagnosis.
		@return Diagnosis
	  */
	public int getEXME_Diagnostico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Diagnostico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical File.
		@param EXME_Hist_Exp_ID 
		Medical File
	  */
	public void setEXME_Hist_Exp_ID (int EXME_Hist_Exp_ID)
	{
		if (EXME_Hist_Exp_ID < 1) 
			set_Value (COLUMNNAME_EXME_Hist_Exp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Hist_Exp_ID, Integer.valueOf(EXME_Hist_Exp_ID));
	}

	/** Get Medical File.
		@return Medical File
	  */
	public int getEXME_Hist_Exp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Hist_Exp_ID);
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

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, IsPrinted);
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public String getIsPrinted () 
	{
		return (String)get_Value(COLUMNNAME_IsPrinted);
	}

	/** Set Legal.
		@param Juridico 
		Legal
	  */
	public void setJuridico (boolean Juridico)
	{
		set_Value (COLUMNNAME_Juridico, Boolean.valueOf(Juridico));
	}

	/** Get Legal.
		@return Legal
	  */
	public boolean isJuridico () 
	{
		Object oo = get_Value(COLUMNNAME_Juridico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Physician.
		@param Medico Physician	  */
	public void setMedico (String Medico)
	{
		set_Value (COLUMNNAME_Medico, Medico);
	}

	/** Get Physician.
		@return Physician	  */
	public String getMedico () 
	{
		return (String)get_Value(COLUMNNAME_Medico);
	}

	/** Set Public  Prosecutor.
		@param MinStPub 
		Public  Prosecutor
	  */
	public void setMinStPub (boolean MinStPub)
	{
		set_Value (COLUMNNAME_MinStPub, Boolean.valueOf(MinStPub));
	}

	/** Get Public  Prosecutor.
		@return Public  Prosecutor
	  */
	public boolean isMinStPub () 
	{
		Object oo = get_Value(COLUMNNAME_MinStPub);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Discharge Reason.
		@param MotivoAlta 
		Discharge Reason
	  */
	public void setMotivoAlta (String MotivoAlta)
	{
		set_Value (COLUMNNAME_MotivoAlta, MotivoAlta);
	}

	/** Get Discharge Reason.
		@return Discharge Reason
	  */
	public String getMotivoAlta () 
	{
		return (String)get_Value(COLUMNNAME_MotivoAlta);
	}

	/** Set Preliminary investigation Number.
		@param NoAverPrev 
		Preliminary investigation Number
	  */
	public void setNoAverPrev (String NoAverPrev)
	{
		set_Value (COLUMNNAME_NoAverPrev, NoAverPrev);
	}

	/** Get Preliminary investigation Number.
		@return Preliminary investigation Number
	  */
	public String getNoAverPrev () 
	{
		return (String)get_Value(COLUMNNAME_NoAverPrev);
	}

	/** Set Patient Name.
		@param Nombre_Pac 
		Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac)
	{
		set_Value (COLUMNNAME_Nombre_Pac, Nombre_Pac);
	}

	/** Get Patient Name.
		@return Patient Name
	  */
	public String getNombre_Pac () 
	{
		return (String)get_Value(COLUMNNAME_Nombre_Pac);
	}

	/** Set Observation.
		@param Observacion 
		Observation
	  */
	public void setObservacion (String Observacion)
	{
		set_Value (COLUMNNAME_Observacion, Observacion);
	}

	/** Get Observation.
		@return Observation
	  */
	public String getObservacion () 
	{
		return (String)get_Value(COLUMNNAME_Observacion);
	}

	/** Set Social Work Bed.
		@param TSCama Social Work Bed	  */
	public void setTSCama (String TSCama)
	{
		set_Value (COLUMNNAME_TSCama, TSCama);
	}

	/** Get Social Work Bed.
		@return Social Work Bed	  */
	public String getTSCama () 
	{
		return (String)get_Value(COLUMNNAME_TSCama);
	}

	/** Set Social Work Clasification.
		@param TSClasificacion Social Work Clasification	  */
	public void setTSClasificacion (String TSClasificacion)
	{
		set_Value (COLUMNNAME_TSClasificacion, TSClasificacion);
	}

	/** Get Social Work Clasification.
		@return Social Work Clasification	  */
	public String getTSClasificacion () 
	{
		return (String)get_Value(COLUMNNAME_TSClasificacion);
	}

	/** Set Social Work Clinical.
		@param TSClinico 
		Social Work Clinical
	  */
	public void setTSClinico (String TSClinico)
	{
		set_Value (COLUMNNAME_TSClinico, TSClinico);
	}

	/** Get Social Work Clinical.
		@return Social Work Clinical
	  */
	public String getTSClinico () 
	{
		return (String)get_Value(COLUMNNAME_TSClinico);
	}

	/** Set Sex.
		@param TSSexo Sex	  */
	public void setTSSexo (String TSSexo)
	{
		set_Value (COLUMNNAME_TSSexo, TSSexo);
	}

	/** Get Sex.
		@return Sex	  */
	public String getTSSexo () 
	{
		return (String)get_Value(COLUMNNAME_TSSexo);
	}
}