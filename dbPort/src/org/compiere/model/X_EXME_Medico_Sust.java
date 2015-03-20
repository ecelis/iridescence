/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Medico_Sust
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Medico_Sust extends PO implements I_EXME_Medico_Sust, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Medico_Sust (Properties ctx, int EXME_Medico_Sust_ID, String trxName)
    {
      super (ctx, EXME_Medico_Sust_ID, trxName);
      /** if (EXME_Medico_Sust_ID == 0)
        {
			setEXME_Medico_ID (0);
			setEXME_Medico_Sust_ID (0);
			setSecuencia (0);
// 0
			setSubstitute_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Medico_Sust (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Medico_Sust[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
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

	/** Set Substitute Doctor.
		@param EXME_Medico_Sust_ID 
		Substitute doctor
	  */
	public void setEXME_Medico_Sust_ID (int EXME_Medico_Sust_ID)
	{
		if (EXME_Medico_Sust_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_Sust_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Medico_Sust_ID, Integer.valueOf(EXME_Medico_Sust_ID));
	}

	/** Get Substitute Doctor.
		@return Substitute doctor
	  */
	public int getEXME_Medico_Sust_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_Sust_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Substitute.
		@param Substitute_ID 
		Entity which can be used in place of this entity
	  */
	public void setSubstitute_ID (int Substitute_ID)
	{
		if (Substitute_ID < 1)
			 throw new IllegalArgumentException ("Substitute_ID is mandatory.");
		set_Value (COLUMNNAME_Substitute_ID, Integer.valueOf(Substitute_ID));
	}

	/** Get Substitute.
		@return Entity which can be used in place of this entity
	  */
	public int getSubstitute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Substitute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}