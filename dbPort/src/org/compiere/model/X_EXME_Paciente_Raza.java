/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Paciente_Raza
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Paciente_Raza extends PO implements I_EXME_Paciente_Raza, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Paciente_Raza (Properties ctx, int EXME_Paciente_Raza_ID, String trxName)
    {
      super (ctx, EXME_Paciente_Raza_ID, trxName);
      /** if (EXME_Paciente_Raza_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setEXME_Paciente_Raza_ID (0);
			setEXME_Razas_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Paciente_Raza (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 9 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Paciente_Raza[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_Paciente_Raza_ID.
		@param EXME_Paciente_Raza_ID EXME_Paciente_Raza_ID	  */
	public void setEXME_Paciente_Raza_ID (int EXME_Paciente_Raza_ID)
	{
		if (EXME_Paciente_Raza_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_Raza_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Paciente_Raza_ID, Integer.valueOf(EXME_Paciente_Raza_ID));
	}

	/** Get EXME_Paciente_Raza_ID.
		@return EXME_Paciente_Raza_ID	  */
	public int getEXME_Paciente_Raza_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_Raza_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Race.
		@param EXME_Razas_ID 
		Races
	  */
	public void setEXME_Razas_ID (int EXME_Razas_ID)
	{
		if (EXME_Razas_ID < 1)
			 throw new IllegalArgumentException ("EXME_Razas_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Razas_ID, Integer.valueOf(EXME_Razas_ID));
	}

	/** Get Race.
		@return Races
	  */
	public int getEXME_Razas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Razas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}