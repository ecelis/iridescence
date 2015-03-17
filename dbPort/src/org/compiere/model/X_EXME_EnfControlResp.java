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

/** Generated Model for EXME_EnfControlResp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EnfControlResp extends PO implements I_EXME_EnfControlResp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EnfControlResp (Properties ctx, int EXME_EnfControlResp_ID, String trxName)
    {
      super (ctx, EXME_EnfControlResp_ID, trxName);
      /** if (EXME_EnfControlResp_ID == 0)
        {
			setConfidencial (null);
			setEsNotaMedica (false);
			setEXME_EnfControlResp_ID (0);
			setEXME_Pregunta_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setSecuencia (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_EnfControlResp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EnfControlResp[")
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

	public I_EXME_Cuestionario getEXME_Cuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Cuestionario.Table_Name);
        I_EXME_Cuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Cuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Cuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	public I_EXME_EnfControlada getEXME_EnfControlada() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EnfControlada.Table_Name);
        I_EXME_EnfControlada result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EnfControlada)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EnfControlada_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Controlled Illness.
		@param EXME_EnfControlada_ID 
		Controlled Illness
	  */
	public void setEXME_EnfControlada_ID (int EXME_EnfControlada_ID)
	{
		if (EXME_EnfControlada_ID < 1) 
			set_Value (COLUMNNAME_EXME_EnfControlada_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EnfControlada_ID, Integer.valueOf(EXME_EnfControlada_ID));
	}

	/** Get Controlled Illness.
		@return Controlled Illness
	  */
	public int getEXME_EnfControlada_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfControlada_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Answer of Controlled Disease.
		@param EXME_EnfControlResp_ID Answer of Controlled Disease	  */
	public void setEXME_EnfControlResp_ID (int EXME_EnfControlResp_ID)
	{
		if (EXME_EnfControlResp_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfControlResp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnfControlResp_ID, Integer.valueOf(EXME_EnfControlResp_ID));
	}

	/** Get Answer of Controlled Disease.
		@return Answer of Controlled Disease	  */
	public int getEXME_EnfControlResp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfControlResp_ID);
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

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
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

	public I_EXME_Pregunta getEXME_Pregunta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Pregunta.Table_Name);
        I_EXME_Pregunta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Pregunta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Pregunta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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
	public void setSecuencia (BigDecimal Secuencia)
	{
		if (Secuencia == null)
			throw new IllegalArgumentException ("Secuencia is mandatory.");
		set_Value (COLUMNNAME_Secuencia, Secuencia);
	}

	/** Get Sequence.
		@return Sequence
	  */
	public BigDecimal getSecuencia () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Secuencia);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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