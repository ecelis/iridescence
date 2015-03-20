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

/** Generated Model for I_EXME_CasosMedicos
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_EXME_CasosMedicos extends PO implements I_I_EXME_CasosMedicos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_EXME_CasosMedicos (Properties ctx, int I_EXME_CasosMedicos_ID, String trxName)
    {
      super (ctx, I_EXME_CasosMedicos_ID, trxName);
      /** if (I_EXME_CasosMedicos_ID == 0)
        {
			setI_EXME_CasosMedicos_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_EXME_CasosMedicos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_EXME_CasosMedicos[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getAD_User() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Name.
		@param AD_User_Name 
		User Name
	  */
	public void setAD_User_Name (String AD_User_Name)
	{
		set_Value (COLUMNNAME_AD_User_Name, AD_User_Name);
	}

	/** Get User Name.
		@return User Name
	  */
	public String getAD_User_Name () 
	{
		return (String)get_Value(COLUMNNAME_AD_User_Name);
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

	public I_EXME_Cama getEXME_Cama() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cama.Table_Name);
        I_EXME_Cama result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cama)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cama_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Bed Value.
		@param EXME_Cama_Value Bed Value	  */
	public void setEXME_Cama_Value (String EXME_Cama_Value)
	{
		set_Value (COLUMNNAME_EXME_Cama_Value, EXME_Cama_Value);
	}

	/** Get Bed Value.
		@return Bed Value	  */
	public String getEXME_Cama_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Cama_Value);
	}

	public I_EXME_CasosMedicos getEXME_CasosMedicos() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CasosMedicos.Table_Name);
        I_EXME_CasosMedicos result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CasosMedicos)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CasosMedicos_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical-Legal Cases.
		@param EXME_CasosMedicos_ID 
		Medical-Legal Cases
	  */
	public void setEXME_CasosMedicos_ID (int EXME_CasosMedicos_ID)
	{
		if (EXME_CasosMedicos_ID < 1) 
			set_Value (COLUMNNAME_EXME_CasosMedicos_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CasosMedicos_ID, Integer.valueOf(EXME_CasosMedicos_ID));
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

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Diagnostico.Table_Name);
        I_EXME_Diagnostico result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Diagnostico)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Diagnostico_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Diagnostic.
		@param EXME_Diagnostico_Value 
		Diagnostic
	  */
	public void setEXME_Diagnostico_Value (String EXME_Diagnostico_Value)
	{
		set_Value (COLUMNNAME_EXME_Diagnostico_Value, EXME_Diagnostico_Value);
	}

	/** Get Diagnostic.
		@return Diagnostic
	  */
	public String getEXME_Diagnostico_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Diagnostico_Value);
	}

	public I_EXME_Habitacion getEXME_Habitacion() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Habitacion.Table_Name);
        I_EXME_Habitacion result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Habitacion)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Habitacion_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Room.
		@param EXME_Habitacion_ID 
		Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID)
	{
		if (EXME_Habitacion_ID < 1) 
			set_Value (COLUMNNAME_EXME_Habitacion_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Habitacion_ID, Integer.valueOf(EXME_Habitacion_ID));
	}

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

	/** Set EXME_Habitacion_Value.
		@param EXME_Habitacion_Value 
		Room
	  */
	public void setEXME_Habitacion_Value (String EXME_Habitacion_Value)
	{
		set_Value (COLUMNNAME_EXME_Habitacion_Value, EXME_Habitacion_Value);
	}

	/** Get EXME_Habitacion_Value.
		@return Room
	  */
	public String getEXME_Habitacion_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Habitacion_Value);
	}

	public I_EXME_Hist_Exp getEXME_Hist_Exp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Hist_Exp.Table_Name);
        I_EXME_Hist_Exp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Hist_Exp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Hist_Exp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Patient History Number.
		@param EXME_Paciente_Value 
		Patient History Number
	  */
	public void setEXME_Paciente_Value (String EXME_Paciente_Value)
	{
		set_Value (COLUMNNAME_EXME_Paciente_Value, EXME_Paciente_Value);
	}

	/** Get Patient History Number.
		@return Patient History Number
	  */
	public String getEXME_Paciente_Value () 
	{
		return (String)get_Value(COLUMNNAME_EXME_Paciente_Value);
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

	/** Set I_EXME_CasosMedicos_ID.
		@param I_EXME_CasosMedicos_ID I_EXME_CasosMedicos_ID	  */
	public void setI_EXME_CasosMedicos_ID (int I_EXME_CasosMedicos_ID)
	{
		if (I_EXME_CasosMedicos_ID < 1)
			 throw new IllegalArgumentException ("I_EXME_CasosMedicos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_EXME_CasosMedicos_ID, Integer.valueOf(I_EXME_CasosMedicos_ID));
	}

	/** Get I_EXME_CasosMedicos_ID.
		@return I_EXME_CasosMedicos_ID	  */
	public int getI_EXME_CasosMedicos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_EXME_CasosMedicos_ID);
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

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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