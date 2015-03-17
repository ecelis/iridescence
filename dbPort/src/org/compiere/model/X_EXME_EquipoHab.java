/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_EquipoHab
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EquipoHab extends PO implements I_EXME_EquipoHab, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EquipoHab (Properties ctx, int EXME_EquipoHab_ID, String trxName)
    {
      super (ctx, EXME_EquipoHab_ID, trxName);
      /** if (EXME_EquipoHab_ID == 0)
        {
			setEstatus_Equipo (null);
// S
			setEXME_EquipoHab_ID (0);
			setEXME_Equipo_ID (0);
			setEXME_Habitacion_ID (0);
			setFecha_Fin (new Timestamp( System.currentTimeMillis() ));
			setFecha_Ini (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_EquipoHab (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EquipoHab[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Estatus_Equipo AD_Reference_ID=1200113 */
	public static final int ESTATUS_EQUIPO_AD_Reference_ID=1200113;
	/** Sacheduled = P */
	public static final String ESTATUS_EQUIPO_Sacheduled = "P";
	/** Used = U */
	public static final String ESTATUS_EQUIPO_Used = "U";
	/** Maintenance = M */
	public static final String ESTATUS_EQUIPO_Maintenance = "M";
	/** Available = D */
	public static final String ESTATUS_EQUIPO_Available = "D";
	/** Ordered = S */
	public static final String ESTATUS_EQUIPO_Ordered = "S";
	/** Cancelled = C */
	public static final String ESTATUS_EQUIPO_Cancelled = "C";
	/** Set Equipment Status.
		@param Estatus_Equipo Equipment Status	  */
	public void setEstatus_Equipo (String Estatus_Equipo)
	{
		if (Estatus_Equipo == null) throw new IllegalArgumentException ("Estatus_Equipo is mandatory");
		if (Estatus_Equipo.equals("P") || Estatus_Equipo.equals("U") || Estatus_Equipo.equals("M") || Estatus_Equipo.equals("D") || Estatus_Equipo.equals("S") || Estatus_Equipo.equals("C")); else throw new IllegalArgumentException ("Estatus_Equipo Invalid value - " + Estatus_Equipo + " - Reference_ID=1200113 - P - U - M - D - S - C");		set_Value (COLUMNNAME_Estatus_Equipo, Estatus_Equipo);
	}

	/** Get Equipment Status.
		@return Equipment Status	  */
	public String getEstatus_Equipo () 
	{
		return (String)get_Value(COLUMNNAME_Estatus_Equipo);
	}

	/** Set Room Equipment.
		@param EXME_EquipoHab_ID Room Equipment	  */
	public void setEXME_EquipoHab_ID (int EXME_EquipoHab_ID)
	{
		if (EXME_EquipoHab_ID < 1)
			 throw new IllegalArgumentException ("EXME_EquipoHab_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EquipoHab_ID, Integer.valueOf(EXME_EquipoHab_ID));
	}

	/** Get Room Equipment.
		@return Room Equipment	  */
	public int getEXME_EquipoHab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EquipoHab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Equipment.
		@param EXME_Equipo_ID 
		Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID)
	{
		if (EXME_Equipo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Equipo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Equipo_ID, Integer.valueOf(EXME_Equipo_ID));
	}

	/** Get Equipment.
		@return Equipment
	  */
	public int getEXME_Equipo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Equipo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Room.
		@param EXME_Habitacion_ID 
		Room
	  */
	public void setEXME_Habitacion_ID (int EXME_Habitacion_ID)
	{
		if (EXME_Habitacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Habitacion_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Habitacion_ID, Integer.valueOf(EXME_Habitacion_ID));
	}

	/** Get Room.
		@return Room
	  */
	public int getEXME_Habitacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Habitacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ending Date.
		@param Fecha_Fin 
		Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin)
	{
		if (Fecha_Fin == null)
			throw new IllegalArgumentException ("Fecha_Fin is mandatory.");
		set_Value (COLUMNNAME_Fecha_Fin, Fecha_Fin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFecha_Fin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin);
	}

	/** Set Initial Date.
		@param Fecha_Ini 
		Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini)
	{
		if (Fecha_Ini == null)
			throw new IllegalArgumentException ("Fecha_Ini is mandatory.");
		set_Value (COLUMNNAME_Fecha_Ini, Fecha_Ini);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFecha_Ini () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini);
	}
}