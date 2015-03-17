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

/** Generated Model for PHR_PacRespDt
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_PHR_PacRespDt extends PO implements I_PHR_PacRespDt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PHR_PacRespDt (Properties ctx, int PHR_PacRespDt_ID, String trxName)
    {
      super (ctx, PHR_PacRespDt_ID, trxName);
      /** if (PHR_PacRespDt_ID == 0)
        {
			setEXME_Pregunta_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setPHR_PacCuest_ID (0);
			setPHR_PacRespDt_ID (0);
			setSecuencia (0);
        } */
    }

    /** Load Constructor */
    public X_PHR_PacRespDt (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_PHR_PacRespDt[")
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

	public I_PHR_PacCuest getPHR_PacCuest() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_PHR_PacCuest.Table_Name);
        I_PHR_PacCuest result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_PHR_PacCuest)constructor.newInstance(new Object[] {getCtx(), new Integer(getPHR_PacCuest_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Questionaire.
		@param PHR_PacCuest_ID Patient Questionaire	  */
	public void setPHR_PacCuest_ID (int PHR_PacCuest_ID)
	{
		if (PHR_PacCuest_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacCuest_ID is mandatory.");
		set_Value (COLUMNNAME_PHR_PacCuest_ID, Integer.valueOf(PHR_PacCuest_ID));
	}

	/** Get Patient Questionaire.
		@return Patient Questionaire	  */
	public int getPHR_PacCuest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacCuest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Detail of Response Patient.
		@param PHR_PacRespDt_ID Detail of Response Patient	  */
	public void setPHR_PacRespDt_ID (int PHR_PacRespDt_ID)
	{
		if (PHR_PacRespDt_ID < 1)
			 throw new IllegalArgumentException ("PHR_PacRespDt_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PHR_PacRespDt_ID, Integer.valueOf(PHR_PacRespDt_ID));
	}

	/** Get Detail of Response Patient.
		@return Detail of Response Patient	  */
	public int getPHR_PacRespDt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PHR_PacRespDt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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