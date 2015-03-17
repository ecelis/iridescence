/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Ensenanza_8
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Ensenanza_8 extends PO implements I_EXME_Ensenanza_8, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Ensenanza_8 (Properties ctx, int EXME_Ensenanza_8_ID, String trxName)
    {
      super (ctx, EXME_Ensenanza_8_ID, trxName);
      /** if (EXME_Ensenanza_8_ID == 0)
        {
			setEXME_Ensenanza_8_ID (0);
			setTema (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Ensenanza_8 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Ensenanza_8[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Teaching Courses 8.
		@param EXME_Ensenanza_8_ID 
		Teaching Courses 8
	  */
	public void setEXME_Ensenanza_8_ID (int EXME_Ensenanza_8_ID)
	{
		if (EXME_Ensenanza_8_ID < 1)
			 throw new IllegalArgumentException ("EXME_Ensenanza_8_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Ensenanza_8_ID, Integer.valueOf(EXME_Ensenanza_8_ID));
	}

	/** Get Teaching Courses 8.
		@return Teaching Courses 8
	  */
	public int getEXME_Ensenanza_8_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ensenanza_8_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Dates Event.
		@param Fecha_Evento 
		Dates Event
	  */
	public void setFecha_Evento (Timestamp Fecha_Evento)
	{
		set_Value (COLUMNNAME_Fecha_Evento, Fecha_Evento);
	}

	/** Get Dates Event.
		@return Dates Event
	  */
	public Timestamp getFecha_Evento () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Evento);
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (String IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, IsPrinted);
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public String getIsPrinted () 
	{
		return (String)get_Value(COLUMNNAME_IsPrinted);
	}

	/** Set Number Attending.
		@param Num_Asistentes 
		Number Attending
	  */
	public void setNum_Asistentes (int Num_Asistentes)
	{
		set_Value (COLUMNNAME_Num_Asistentes, Integer.valueOf(Num_Asistentes));
	}

	/** Get Number Attending.
		@return Number Attending
	  */
	public int getNum_Asistentes () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Num_Asistentes);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Theme.
		@param Tema 
		Theme
	  */
	public void setTema (String Tema)
	{
		if (Tema == null)
			throw new IllegalArgumentException ("Tema is mandatory.");
		set_Value (COLUMNNAME_Tema, Tema);
	}

	/** Get Theme.
		@return Theme
	  */
	public String getTema () 
	{
		return (String)get_Value(COLUMNNAME_Tema);
	}
}