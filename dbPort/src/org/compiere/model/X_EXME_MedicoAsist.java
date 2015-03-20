/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_MedicoAsist
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MedicoAsist extends PO implements I_EXME_MedicoAsist, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MedicoAsist (Properties ctx, int EXME_MedicoAsist_ID, String trxName)
    {
      super (ctx, EXME_MedicoAsist_ID, trxName);
      /** if (EXME_MedicoAsist_ID == 0)
        {
			setEXME_Asistente_ID (0);
			setEXME_MedicoAsist_ID (0);
			setEXME_Medico_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_MedicoAsist (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MedicoAsist[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Assistant.
		@param EXME_Asistente_ID 
		Assistant
	  */
	public void setEXME_Asistente_ID (int EXME_Asistente_ID)
	{
		if (EXME_Asistente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Asistente_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Asistente_ID, Integer.valueOf(EXME_Asistente_ID));
	}

	/** Get Assistant.
		@return Assistant
	  */
	public int getEXME_Asistente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Asistente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Assistant Doctor.
		@param EXME_MedicoAsist_ID 
		Assistant Doctor
	  */
	public void setEXME_MedicoAsist_ID (int EXME_MedicoAsist_ID)
	{
		if (EXME_MedicoAsist_ID < 1)
			 throw new IllegalArgumentException ("EXME_MedicoAsist_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MedicoAsist_ID, Integer.valueOf(EXME_MedicoAsist_ID));
	}

	/** Get Assistant Doctor.
		@return Assistant Doctor
	  */
	public int getEXME_MedicoAsist_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoAsist_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}