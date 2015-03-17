/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ConfigSeguridad
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ConfigSeguridad extends PO implements I_EXME_ConfigSeguridad, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigSeguridad (Properties ctx, int EXME_ConfigSeguridad_ID, String trxName)
    {
      super (ctx, EXME_ConfigSeguridad_ID, trxName);
      /** if (EXME_ConfigSeguridad_ID == 0)
        {
			setDias (0);
			setDiasCambiaPwd (0);
			setEXME_ConfigSeguridad_ID (0);
			setIntentos (0);
			setTimeOut (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigSeguridad (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigSeguridad[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Days.
		@param Dias Days	  */
	public void setDias (int Dias)
	{
		set_Value (COLUMNNAME_Dias, Integer.valueOf(Dias));
	}

	/** Get Days.
		@return Days	  */
	public int getDias () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Dias);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Days to request password change.
		@param DiasCambiaPwd 
		Days to request password change
	  */
	public void setDiasCambiaPwd (int DiasCambiaPwd)
	{
		set_Value (COLUMNNAME_DiasCambiaPwd, Integer.valueOf(DiasCambiaPwd));
	}

	/** Get Days to request password change.
		@return Days to request password change
	  */
	public int getDiasCambiaPwd () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DiasCambiaPwd);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Security Settings.
		@param EXME_ConfigSeguridad_ID Security Settings	  */
	public void setEXME_ConfigSeguridad_ID (int EXME_ConfigSeguridad_ID)
	{
		if (EXME_ConfigSeguridad_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigSeguridad_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigSeguridad_ID, Integer.valueOf(EXME_ConfigSeguridad_ID));
	}

	/** Get Security Settings.
		@return Security Settings	  */
	public int getEXME_ConfigSeguridad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigSeguridad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Attempts.
		@param Intentos Attempts	  */
	public void setIntentos (int Intentos)
	{
		set_Value (COLUMNNAME_Intentos, Integer.valueOf(Intentos));
	}

	/** Get Attempts.
		@return Attempts	  */
	public int getIntentos () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Intentos);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Time Out.
		@param TimeOut Time Out	  */
	public void setTimeOut (int TimeOut)
	{
		set_Value (COLUMNNAME_TimeOut, Integer.valueOf(TimeOut));
	}

	/** Get Time Out.
		@return Time Out	  */
	public int getTimeOut () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TimeOut);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}