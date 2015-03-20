/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_PreguntaDiag
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_PreguntaDiag extends PO implements I_EXME_PreguntaDiag, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PreguntaDiag (Properties ctx, int EXME_PreguntaDiag_ID, String trxName)
    {
      super (ctx, EXME_PreguntaDiag_ID, trxName);
      /** if (EXME_PreguntaDiag_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_PreguntaDiag_ID (0);
			setEXME_Pregunta_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PreguntaDiag (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PreguntaDiag[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Diagnosis.
		@param EXME_Diagnostico_ID 
		Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID)
	{
		if (EXME_Diagnostico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Diagnostico_ID is mandatory.");
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

	/** Set Diagnostic Question.
		@param EXME_PreguntaDiag_ID 
		Diagnostic Question
	  */
	public void setEXME_PreguntaDiag_ID (int EXME_PreguntaDiag_ID)
	{
		if (EXME_PreguntaDiag_ID < 1)
			 throw new IllegalArgumentException ("EXME_PreguntaDiag_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PreguntaDiag_ID, Integer.valueOf(EXME_PreguntaDiag_ID));
	}

	/** Get Diagnostic Question.
		@return Diagnostic Question
	  */
	public int getEXME_PreguntaDiag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreguntaDiag_ID);
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
}