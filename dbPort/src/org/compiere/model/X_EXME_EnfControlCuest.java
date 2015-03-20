/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_EnfControlCuest
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EnfControlCuest extends PO implements I_EXME_EnfControlCuest, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EnfControlCuest (Properties ctx, int EXME_EnfControlCuest_ID, String trxName)
    {
      super (ctx, EXME_EnfControlCuest_ID, trxName);
      /** if (EXME_EnfControlCuest_ID == 0)
        {
			setEXME_Cuestionario_ID (0);
			setEXME_EnfControlada_ID (0);
			setEXME_EnfControlCuest_ID (0);
			setEXME_Formato_ID (0);
			setTipoCuestionario (null);
// N
        } */
    }

    /** Load Constructor */
    public X_EXME_EnfControlCuest (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EnfControlCuest[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Questionnaire.
		@param EXME_Cuestionario_ID 
		Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID)
	{
		if (EXME_Cuestionario_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cuestionario_ID is mandatory.");
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

	/** Set Controlled Illness.
		@param EXME_EnfControlada_ID 
		Controlled Illness
	  */
	public void setEXME_EnfControlada_ID (int EXME_EnfControlada_ID)
	{
		if (EXME_EnfControlada_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfControlada_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnfControlada_ID, Integer.valueOf(EXME_EnfControlada_ID));
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

	/** Set Questionary of Controlled Disease.
		@param EXME_EnfControlCuest_ID 
		Questionary of Controlled Disease
	  */
	public void setEXME_EnfControlCuest_ID (int EXME_EnfControlCuest_ID)
	{
		if (EXME_EnfControlCuest_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfControlCuest_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnfControlCuest_ID, Integer.valueOf(EXME_EnfControlCuest_ID));
	}

	/** Get Questionary of Controlled Disease.
		@return Questionary of Controlled Disease
	  */
	public int getEXME_EnfControlCuest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfControlCuest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Format.
		@param EXME_Formato_ID Format	  */
	public void setEXME_Formato_ID (int EXME_Formato_ID)
	{
		if (EXME_Formato_ID < 1)
			 throw new IllegalArgumentException ("EXME_Formato_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Formato_ID, Integer.valueOf(EXME_Formato_ID));
	}

	/** Get Format.
		@return Format	  */
	public int getEXME_Formato_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Formato_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TipoCuestionario AD_Reference_ID=1200191 */
	public static final int TIPOCUESTIONARIO_AD_Reference_ID=1200191;
	/** Notification = N */
	public static final String TIPOCUESTIONARIO_Notification = "N";
	/** Monitoring = S */
	public static final String TIPOCUESTIONARIO_Monitoring = "S";
	/** Concentrated = C */
	public static final String TIPOCUESTIONARIO_Concentrated = "C";
	/** Set Questionary Type.
		@param TipoCuestionario Questionary Type	  */
	public void setTipoCuestionario (String TipoCuestionario)
	{
		if (TipoCuestionario == null) throw new IllegalArgumentException ("TipoCuestionario is mandatory");
		if (TipoCuestionario.equals("N") || TipoCuestionario.equals("S") || TipoCuestionario.equals("C")); else throw new IllegalArgumentException ("TipoCuestionario Invalid value - " + TipoCuestionario + " - Reference_ID=1200191 - N - S - C");		set_Value (COLUMNNAME_TipoCuestionario, TipoCuestionario);
	}

	/** Get Questionary Type.
		@return Questionary Type	  */
	public String getTipoCuestionario () 
	{
		return (String)get_Value(COLUMNNAME_TipoCuestionario);
	}
}