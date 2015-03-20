/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_PreguntaListaDiag
 *  @author Generated Class 
 *  @version Release 1.1.2 - $Id$ */
public class X_EXME_PreguntaListaDiag extends PO implements I_EXME_PreguntaListaDiag, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PreguntaListaDiag (Properties ctx, int EXME_PreguntaListaDiag_ID, String trxName)
    {
      super (ctx, EXME_PreguntaListaDiag_ID, trxName);
      /** if (EXME_PreguntaListaDiag_ID == 0)
        {
			setEXME_Diagnostico_ID (0);
			setEXME_PreguntaListaDiag_ID (0);
			setEXME_Pregunta_Lista_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PreguntaListaDiag (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PreguntaListaDiag[")
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

	/** Set Diagnosis .
		@param EXME_PreguntaListaDiag_ID 
		Diagnosis for List of Values
	  */
	public void setEXME_PreguntaListaDiag_ID (int EXME_PreguntaListaDiag_ID)
	{
		if (EXME_PreguntaListaDiag_ID < 1)
			 throw new IllegalArgumentException ("EXME_PreguntaListaDiag_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PreguntaListaDiag_ID, Integer.valueOf(EXME_PreguntaListaDiag_ID));
	}

	/** Get Diagnosis .
		@return Diagnosis for List of Values
	  */
	public int getEXME_PreguntaListaDiag_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreguntaListaDiag_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fixed Answer.
		@param EXME_Pregunta_Lista_ID 
		Fixed answer for the question in the clinic questionnaire
	  */
	public void setEXME_Pregunta_Lista_ID (int EXME_Pregunta_Lista_ID)
	{
		if (EXME_Pregunta_Lista_ID < 1)
			 throw new IllegalArgumentException ("EXME_Pregunta_Lista_ID is mandatory.");
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
}