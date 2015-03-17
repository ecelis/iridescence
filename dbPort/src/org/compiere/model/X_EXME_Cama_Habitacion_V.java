/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Cama_Habitacion_V
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Cama_Habitacion_V extends PO implements I_EXME_Cama_Habitacion_V, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Cama_Habitacion_V (Properties ctx, int EXME_Cama_Habitacion_V_ID, String trxName)
    {
      super (ctx, EXME_Cama_Habitacion_V_ID, trxName);
      /** if (EXME_Cama_Habitacion_V_ID == 0)
        {
			setCama (null);
			setHabitacion (null);
			setTipoArea (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Cama_Habitacion_V (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Cama_Habitacion_V[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Bed.
		@param Cama 
		Bed
	  */
	public void setCama (String Cama)
	{
		if (Cama == null)
			throw new IllegalArgumentException ("Cama is mandatory.");
		set_Value (COLUMNNAME_Cama, Cama);
	}

	/** Get Bed.
		@return Bed
	  */
	public String getCama () 
	{
		return (String)get_Value(COLUMNNAME_Cama);
	}

	/** Set EXME_Cama_Habitacion_ID_V.
		@param EXME_Cama_Habitacion_ID_V EXME_Cama_Habitacion_ID_V	  */
	public void setEXME_Cama_Habitacion_ID_V (int EXME_Cama_Habitacion_ID_V)
	{
		set_Value (COLUMNNAME_EXME_Cama_Habitacion_ID_V, Integer.valueOf(EXME_Cama_Habitacion_ID_V));
	}

	/** Get EXME_Cama_Habitacion_ID_V.
		@return EXME_Cama_Habitacion_ID_V	  */
	public int getEXME_Cama_Habitacion_ID_V () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_Habitacion_ID_V);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Room.
		@param Habitacion 
		Room
	  */
	public void setHabitacion (String Habitacion)
	{
		if (Habitacion == null)
			throw new IllegalArgumentException ("Habitacion is mandatory.");
		set_Value (COLUMNNAME_Habitacion, Habitacion);
	}

	/** Get Room.
		@return Room
	  */
	public String getHabitacion () 
	{
		return (String)get_Value(COLUMNNAME_Habitacion);
	}

	/** Set Area Type.
		@param TipoArea 
		Admission Area Type
	  */
	public void setTipoArea (String TipoArea)
	{
		if (TipoArea == null)
			throw new IllegalArgumentException ("TipoArea is mandatory.");
		set_Value (COLUMNNAME_TipoArea, TipoArea);
	}

	/** Get Area Type.
		@return Admission Area Type
	  */
	public String getTipoArea () 
	{
		return (String)get_Value(COLUMNNAME_TipoArea);
	}
}