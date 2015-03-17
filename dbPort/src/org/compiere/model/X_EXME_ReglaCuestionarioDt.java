/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_ReglaCuestionarioDt
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ReglaCuestionarioDt extends PO implements I_EXME_ReglaCuestionarioDt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ReglaCuestionarioDt (Properties ctx, int EXME_ReglaCuestionarioDt_ID, String trxName)
    {
      super (ctx, EXME_ReglaCuestionarioDt_ID, trxName);
      /** if (EXME_ReglaCuestionarioDt_ID == 0)
        {
			setEXME_ReglaCuestionarioDt_ID (0);
			setEXME_ReglaCuestionario_ID (0);
			setOperator (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ReglaCuestionarioDt (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ReglaCuestionarioDt[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Quiz Rules Detail.
		@param EXME_ReglaCuestionarioDt_ID Quiz Rules Detail	  */
	public void setEXME_ReglaCuestionarioDt_ID (int EXME_ReglaCuestionarioDt_ID)
	{
		if (EXME_ReglaCuestionarioDt_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReglaCuestionarioDt_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ReglaCuestionarioDt_ID, Integer.valueOf(EXME_ReglaCuestionarioDt_ID));
	}

	/** Get Quiz Rules Detail.
		@return Quiz Rules Detail	  */
	public int getEXME_ReglaCuestionarioDt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReglaCuestionarioDt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ReglaCuestionario getEXME_ReglaCuestionario() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ReglaCuestionario.Table_Name);
        I_EXME_ReglaCuestionario result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ReglaCuestionario)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ReglaCuestionario_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Quiz Rules.
		@param EXME_ReglaCuestionario_ID Quiz Rules	  */
	public void setEXME_ReglaCuestionario_ID (int EXME_ReglaCuestionario_ID)
	{
		if (EXME_ReglaCuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReglaCuestionario_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ReglaCuestionario_ID, Integer.valueOf(EXME_ReglaCuestionario_ID));
	}

	/** Get Quiz Rules.
		@return Quiz Rules	  */
	public int getEXME_ReglaCuestionario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReglaCuestionario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Operator.
		@param Operator Operator	  */
	public void setOperator (String Operator)
	{
		if (Operator == null)
			throw new IllegalArgumentException ("Operator is mandatory.");
		set_Value (COLUMNNAME_Operator, Operator);
	}

	/** Get Operator.
		@return Operator	  */
	public String getOperator () 
	{
		return (String)get_Value(COLUMNNAME_Operator);
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

	/** Set Sequence Number.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence Number.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}