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

/** Generated Model for EXME_ActPacienteDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ActPacienteDet extends PO implements I_EXME_ActPacienteDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ActPacienteDet (Properties ctx, int EXME_ActPacienteDet_ID, String trxName)
    {
      super (ctx, EXME_ActPacienteDet_ID, trxName);
      /** if (EXME_ActPacienteDet_ID == 0)
        {
			setConfidencial (null);
			setEsNotaMedica (false);
			setEXME_ActPacienteDet_ID (0);
			setEXME_ActPaciente_ID (0);
			setEXME_Pregunta_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setSecuencia (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ActPacienteDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ActPacienteDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Confidential.
		@param Confidencial 
		Indicates the level of confidenciality
	  */
	public void setConfidencial (String Confidencial)
	{
		if (Confidencial == null)
			throw new IllegalArgumentException ("Confidencial is mandatory.");
		set_Value (COLUMNNAME_Confidencial, Confidencial);
	}

	/** Get Confidential.
		@return Indicates the level of confidenciality
	  */
	public String getConfidencial () 
	{
		return (String)get_Value(COLUMNNAME_Confidencial);
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

	/** DocStatus AD_Reference_ID=1200577 */
	public static final int DOCSTATUS_AD_Reference_ID=1200577;
	/** None = 0 */
	public static final String DOCSTATUS_None = "0";
	/** Pending = 1 */
	public static final String DOCSTATUS_Pending = "1";
	/** Active = 3 */
	public static final String DOCSTATUS_Active = "3";
	/** Closed = 4 */
	public static final String DOCSTATUS_Closed = "4";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		if (DocStatus == null || DocStatus.equals("0") || DocStatus.equals("1") || DocStatus.equals("3") || DocStatus.equals("4")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=1200577 - 0 - 1 - 3 - 4");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Is Medical Note.
		@param EsNotaMedica Is Medical Note	  */
	public void setEsNotaMedica (boolean EsNotaMedica)
	{
		set_Value (COLUMNNAME_EsNotaMedica, Boolean.valueOf(EsNotaMedica));
	}

	/** Get Is Medical Note.
		@return Is Medical Note	  */
	public boolean isEsNotaMedica () 
	{
		Object oo = get_Value(COLUMNNAME_EsNotaMedica);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Health .
		@param EstadoSalud Health 	  */
	public void setEstadoSalud (String EstadoSalud)
	{
		set_Value (COLUMNNAME_EstadoSalud, EstadoSalud);
	}

	/** Get Health .
		@return Health 	  */
	public String getEstadoSalud () 
	{
		return (String)get_Value(COLUMNNAME_EstadoSalud);
	}

	/** Set Detail Patient Activity.
		@param EXME_ActPacienteDet_ID 
		Detail Patient Actvity
	  */
	public void setEXME_ActPacienteDet_ID (int EXME_ActPacienteDet_ID)
	{
		if (EXME_ActPacienteDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_ActPacienteDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ActPacienteDet_ID, Integer.valueOf(EXME_ActPacienteDet_ID));
	}

	/** Get Detail Patient Activity.
		@return Detail Patient Actvity
	  */
	public int getEXME_ActPacienteDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ActPacienteDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1) 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
	}

	/** Get Questionnaire.
		@return Questionnaire
	  */
	public int getEXME_Cuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EncounterForms.Table_Name);
        I_EXME_EncounterForms result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EncounterForms)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EncounterForms_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter Forms.
		@param EXME_EncounterForms_ID 
		Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID)
	{
		if (EXME_EncounterForms_ID < 1) 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, Integer.valueOf(EXME_EncounterForms_ID));
	}

	/** Get Encounter Forms.
		@return Encounter Forms
	  */
	public int getEXME_EncounterForms_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EncounterForms_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Enfermeria.Table_Name);
        I_EXME_Enfermeria result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Enfermeria)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Enfermeria_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Infirmary staff.
		@param EXME_Enfermeria_ID 
		Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID)
	{
		if (EXME_Enfermeria_ID < 1) 
			set_Value (COLUMNNAME_EXME_Enfermeria_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Enfermeria_ID, Integer.valueOf(EXME_Enfermeria_ID));
	}

	/** Get Infirmary staff.
		@return Infirmary staff
	  */
	public int getEXME_Enfermeria_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Enfermeria_ID);
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

	/** Set Question.
		@param EXME_Pregunta_ID 
		Question
	  */
	public void setEXME_Pregunta_ID (int EXME_Pregunta_ID)
	{
		if (EXME_Pregunta_ID < 1)
			 throw new IllegalArgumentException ("EXME_Pregunta_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Pregunta_ID, Integer.valueOf(EXME_Pregunta_ID));
	}

	/** Get Question.
		@return Question
	  */
	public int getEXME_Pregunta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pregunta_ID);
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

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (int Folio)
	{
		set_Value (COLUMNNAME_Folio, Integer.valueOf(Folio));
	}

	/** Get Folio.
		@return Folio	  */
	public int getFolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Folio);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Binary Image.
		@param ImagenBinary Binary Image	  */
	public void setImagenBinary (byte[] ImagenBinary)
	{
		set_Value (COLUMNNAME_ImagenBinary, ImagenBinary);
	}

	/** Get Binary Image.
		@return Binary Image	  */
	public byte[] getImagenBinary () 
	{
		return (byte[])get_Value(COLUMNNAME_ImagenBinary);
	}

	/** Set Question's Fixed Answer Value.
		@param Pregunta_Lista_Value 
		Question's Fixed Answer Value
	  */
	public void setPregunta_Lista_Value (String Pregunta_Lista_Value)
	{
		set_Value (COLUMNNAME_Pregunta_Lista_Value, Pregunta_Lista_Value);
	}

	/** Get Question's Fixed Answer Value.
		@return Question's Fixed Answer Value
	  */
	public String getPregunta_Lista_Value () 
	{
		return (String)get_Value(COLUMNNAME_Pregunta_Lista_Value);
	}

	/** Set REF_EXME_PREGUNTA_ID.
		@param REF_EXME_PREGUNTA_ID REF_EXME_PREGUNTA_ID	  */
	public void setREF_EXME_PREGUNTA_ID (int REF_EXME_PREGUNTA_ID)
	{
		if (REF_EXME_PREGUNTA_ID < 1) 
			set_Value (COLUMNNAME_REF_EXME_PREGUNTA_ID, null);
		else 
			set_Value (COLUMNNAME_REF_EXME_PREGUNTA_ID, Integer.valueOf(REF_EXME_PREGUNTA_ID));
	}

	/** Get REF_EXME_PREGUNTA_ID.
		@return REF_EXME_PREGUNTA_ID	  */
	public int getREF_EXME_PREGUNTA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_REF_EXME_PREGUNTA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Answer.
		@param Respuesta 
		Answer
	  */
	public void setRespuesta (String Respuesta)
	{
		set_Value (COLUMNNAME_Respuesta, Respuesta);
	}

	/** Get Answer.
		@return Answer
	  */
	public String getRespuesta () 
	{
		return (String)get_Value(COLUMNNAME_Respuesta);
	}

	/** Set Image Route.
		@param RutaImagen 
		Image Route
	  */
	public void setRutaImagen (String RutaImagen)
	{
		set_Value (COLUMNNAME_RutaImagen, RutaImagen);
	}

	/** Get Image Route.
		@return Image Route
	  */
	public String getRutaImagen () 
	{
		return (String)get_Value(COLUMNNAME_RutaImagen);
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (int Secuencia)
	{
		set_Value (COLUMNNAME_Secuencia, Integer.valueOf(Secuencia));
	}

	/** Get Sequence.
		@return Sequence
	  */
	public int getSecuencia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Secuencia);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Binary Text.
		@param TextBinary Binary Text	  */
	public void setTextBinary (String TextBinary)
	{
		set_Value (COLUMNNAME_TextBinary, TextBinary);
	}

	/** Get Binary Text.
		@return Binary Text	  */
	public String getTextBinary () 
	{
		return (String)get_Value(COLUMNNAME_TextBinary);
	}
}