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

/** Generated Model for EXME_EnfControlMultiResp
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EnfControlMultiResp extends PO implements I_EXME_EnfControlMultiResp, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EnfControlMultiResp (Properties ctx, int EXME_EnfControlMultiResp_ID, String trxName)
    {
      super (ctx, EXME_EnfControlMultiResp_ID, trxName);
      /** if (EXME_EnfControlMultiResp_ID == 0)
        {
			setEXME_EnfControlMultiResp_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EnfControlMultiResp (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EnfControlMultiResp[")
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

	/** Set multi answers.
		@param EXME_EnfControlMultiResp_ID multi answers	  */
	public void setEXME_EnfControlMultiResp_ID (int EXME_EnfControlMultiResp_ID)
	{
		if (EXME_EnfControlMultiResp_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfControlMultiResp_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnfControlMultiResp_ID, Integer.valueOf(EXME_EnfControlMultiResp_ID));
	}

	/** Get multi answers.
		@return multi answers	  */
	public int getEXME_EnfControlMultiResp_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfControlMultiResp_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EnfControlResp getEXME_EnfControlResp() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EnfControlResp.Table_Name);
        I_EXME_EnfControlResp result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EnfControlResp)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EnfControlResp_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Answer of Controlled Disease.
		@param EXME_EnfControlResp_ID Answer of Controlled Disease	  */
	public void setEXME_EnfControlResp_ID (int EXME_EnfControlResp_ID)
	{
		if (EXME_EnfControlResp_ID < 1) 
			set_Value (COLUMNNAME_EXME_EnfControlResp_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EnfControlResp_ID, Integer.valueOf(EXME_EnfControlResp_ID));
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
			set_Value (COLUMNNAME_EXME_Pregunta_ID, null);
		else 
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

	public I_EXME_Pregunta_Lista getEXME_Pregunta_Lista() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Pregunta_Lista.Table_Name);
        I_EXME_Pregunta_Lista result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Pregunta_Lista)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Pregunta_Lista_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Fixed Answer.
		@param EXME_Pregunta_Lista_ID 
		Fixed answer for the question in the clinic questionnaire
	  */
	public void setEXME_Pregunta_Lista_ID (int EXME_Pregunta_Lista_ID)
	{
		if (EXME_Pregunta_Lista_ID < 1) 
			set_Value (COLUMNNAME_EXME_Pregunta_Lista_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Pregunta_Lista_ID, Integer.valueOf(EXME_Pregunta_Lista_ID));
	}

	/** Get Fixed Answer.
		@return Fixed answer for the question in the clinic questionnaire
	  */
	public int getEXME_Pregunta_Lista_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Pregunta_Lista_ID);
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

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia)
	{
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
}