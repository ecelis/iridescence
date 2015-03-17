/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Calendario
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Calendario extends PO implements I_EXME_Calendario, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Calendario (Properties ctx, int EXME_Calendario_ID, String trxName)
    {
      super (ctx, EXME_Calendario_ID, trxName);
      /** if (EXME_Calendario_ID == 0)
        {
			setDia_Mes (0);
			setDia_Semana (0);
			setEsFestivo (false);
// N
			setEXME_Calendario_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setMes (0);
			setYear (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Calendario (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Calendario[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Day of Month.
		@param Dia_Mes Day of Month	  */
	public void setDia_Mes (int Dia_Mes)
	{
		set_Value (COLUMNNAME_Dia_Mes, Integer.valueOf(Dia_Mes));
	}

	/** Get Day of Month.
		@return Day of Month	  */
	public int getDia_Mes () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Dia_Mes);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Day Of  Week.
		@param Dia_Semana Day Of  Week	  */
	public void setDia_Semana (int Dia_Semana)
	{
		set_Value (COLUMNNAME_Dia_Semana, Integer.valueOf(Dia_Semana));
	}

	/** Get Day Of  Week.
		@return Day Of  Week	  */
	public int getDia_Semana () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Dia_Semana);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Holiday.
		@param EsFestivo Holiday	  */
	public void setEsFestivo (boolean EsFestivo)
	{
		set_Value (COLUMNNAME_EsFestivo, Boolean.valueOf(EsFestivo));
	}

	/** Get Holiday.
		@return Holiday	  */
	public boolean isEsFestivo () 
	{
		Object oo = get_Value(COLUMNNAME_EsFestivo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Calendar.
		@param EXME_Calendario_ID Calendar	  */
	public void setEXME_Calendario_ID (int EXME_Calendario_ID)
	{
		if (EXME_Calendario_ID < 1)
			 throw new IllegalArgumentException ("EXME_Calendario_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Calendario_ID, Integer.valueOf(EXME_Calendario_ID));
	}

	/** Get Calendar.
		@return Calendar	  */
	public int getEXME_Calendario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Calendario_ID);
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

	/** Set Month.
		@param Mes Month	  */
	public void setMes (int Mes)
	{
		set_Value (COLUMNNAME_Mes, Integer.valueOf(Mes));
	}

	/** Get Month.
		@return Month	  */
	public int getMes () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Mes);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Year.
		@param Year 
		Calendar Year
	  */
	public void setYear (int Year)
	{
		set_Value (COLUMNNAME_Year, Integer.valueOf(Year));
	}

	/** Get Year.
		@return Calendar Year
	  */
	public int getYear () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Year);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}