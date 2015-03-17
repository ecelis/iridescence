/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_CuestionarioDt
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CuestionarioDt extends PO implements I_EXME_CuestionarioDt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CuestionarioDt (Properties ctx, int EXME_CuestionarioDt_ID, String trxName)
    {
      super (ctx, EXME_CuestionarioDt_ID, trxName);
      /** if (EXME_CuestionarioDt_ID == 0)
        {
			setEXME_CuestionarioDt_ID (0);
			setEXME_Cuestionario_ID (0);
			setEXME_Pregunta_ID (0);
			setEXME_TipoPregunta_ID (0);
			setObligatoria (false);
// N
			setSecuencia (0);
// @SQL=SELECT NVL(MAX(Secuencia),0)+10 AS DefaultValue FROM EXME_CuestionarioDt WHERE EXME_Cuestionario_ID=@EXME_Cuestionario_ID@
        } */
    }

    /** Load Constructor */
    public X_EXME_CuestionarioDt (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CuestionarioDt[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set DT Questinnaire.
		@param EXME_CuestionarioDt_ID 
		DT Questionnaire
	  */
	public void setEXME_CuestionarioDt_ID (int EXME_CuestionarioDt_ID)
	{
		if (EXME_CuestionarioDt_ID < 1)
			 throw new IllegalArgumentException ("EXME_CuestionarioDt_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_CuestionarioDt_ID, Integer.valueOf(EXME_CuestionarioDt_ID));
	}

	/** Get DT Questinnaire.
		@return DT Questionnaire
	  */
	public int getEXME_CuestionarioDt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CuestionarioDt_ID);
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
			 throw new IllegalArgumentException ("EXME_Cuestionario_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Cuestionario_ID, Integer.valueOf(EXME_Cuestionario_ID));
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

	/** Set Title.
		@param EXME_CuestTitulo_ID Title	  */
	public void setEXME_CuestTitulo_ID (int EXME_CuestTitulo_ID)
	{
		if (EXME_CuestTitulo_ID < 1) 
			set_Value (COLUMNNAME_EXME_CuestTitulo_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CuestTitulo_ID, Integer.valueOf(EXME_CuestTitulo_ID));
	}

	/** Get Title.
		@return Title	  */
	public int getEXME_CuestTitulo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CuestTitulo_ID);
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

	/** Set Type of Question.
		@param EXME_TipoPregunta_ID 
		Type of Question
	  */
	public void setEXME_TipoPregunta_ID (int EXME_TipoPregunta_ID)
	{
		if (EXME_TipoPregunta_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoPregunta_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoPregunta_ID, Integer.valueOf(EXME_TipoPregunta_ID));
	}

	/** Get Type of Question.
		@return Type of Question
	  */
	public int getEXME_TipoPregunta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoPregunta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Mandatory.
		@param Obligatoria 
		Mandatory
	  */
	public void setObligatoria (boolean Obligatoria)
	{
		set_Value (COLUMNNAME_Obligatoria, Boolean.valueOf(Obligatoria));
	}

	/** Get Mandatory.
		@return Mandatory
	  */
	public boolean isObligatoria () 
	{
		Object oo = get_Value(COLUMNNAME_Obligatoria);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
}