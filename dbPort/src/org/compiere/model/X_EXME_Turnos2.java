/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_Turnos2
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Turnos2 extends PO implements I_EXME_Turnos2, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Turnos2 (Properties ctx, int EXME_Turnos2_ID, String trxName)
    {
      super (ctx, EXME_Turnos2_ID, trxName);
      /** if (EXME_Turnos2_ID == 0)
        {
			setDom_Entrada (new Timestamp( System.currentTimeMillis() ));
			setDom_Salida (new Timestamp( System.currentTimeMillis() ));
			setEXME_Turnos2_ID (0);
			setJue_Entrada (new Timestamp( System.currentTimeMillis() ));
			setJue_Salida (new Timestamp( System.currentTimeMillis() ));
			setLun_Entrada (new Timestamp( System.currentTimeMillis() ));
			setLun_Salida (new Timestamp( System.currentTimeMillis() ));
			setMar_Entrada (new Timestamp( System.currentTimeMillis() ));
			setMar_Salida (new Timestamp( System.currentTimeMillis() ));
			setMie_Entrada (new Timestamp( System.currentTimeMillis() ));
			setMie_Salida (new Timestamp( System.currentTimeMillis() ));
			setSab_Entrada (new Timestamp( System.currentTimeMillis() ));
			setSab_Salida (new Timestamp( System.currentTimeMillis() ));
			setValue (null);
			setVie_Entrada (new Timestamp( System.currentTimeMillis() ));
			setVie_Salida (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_Turnos2 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Turnos2[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Sunday`s Check-In.
		@param Dom_Entrada Sunday`s Check-In	  */
	public void setDom_Entrada (Timestamp Dom_Entrada)
	{
		if (Dom_Entrada == null)
			throw new IllegalArgumentException ("Dom_Entrada is mandatory.");
		set_Value (COLUMNNAME_Dom_Entrada, Dom_Entrada);
	}

	/** Get Sunday`s Check-In.
		@return Sunday`s Check-In	  */
	public Timestamp getDom_Entrada () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Dom_Entrada);
	}

	/** Set Sunday`s Check-Out.
		@param Dom_Salida Sunday`s Check-Out	  */
	public void setDom_Salida (Timestamp Dom_Salida)
	{
		if (Dom_Salida == null)
			throw new IllegalArgumentException ("Dom_Salida is mandatory.");
		set_Value (COLUMNNAME_Dom_Salida, Dom_Salida);
	}

	/** Get Sunday`s Check-Out.
		@return Sunday`s Check-Out	  */
	public Timestamp getDom_Salida () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Dom_Salida);
	}

	/** Set Warehouse Shifts 2.
		@param EXME_Turnos2_ID Warehouse Shifts 2	  */
	public void setEXME_Turnos2_ID (int EXME_Turnos2_ID)
	{
		if (EXME_Turnos2_ID < 1)
			 throw new IllegalArgumentException ("EXME_Turnos2_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Turnos2_ID, Integer.valueOf(EXME_Turnos2_ID));
	}

	/** Get Warehouse Shifts 2.
		@return Warehouse Shifts 2	  */
	public int getEXME_Turnos2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Turnos2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Thursday´s Check-In.
		@param Jue_Entrada Thursday´s Check-In	  */
	public void setJue_Entrada (Timestamp Jue_Entrada)
	{
		if (Jue_Entrada == null)
			throw new IllegalArgumentException ("Jue_Entrada is mandatory.");
		set_Value (COLUMNNAME_Jue_Entrada, Jue_Entrada);
	}

	/** Get Thursday´s Check-In.
		@return Thursday´s Check-In	  */
	public Timestamp getJue_Entrada () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Jue_Entrada);
	}

	/** Set Thursday´s Check-Out.
		@param Jue_Salida Thursday´s Check-Out	  */
	public void setJue_Salida (Timestamp Jue_Salida)
	{
		if (Jue_Salida == null)
			throw new IllegalArgumentException ("Jue_Salida is mandatory.");
		set_Value (COLUMNNAME_Jue_Salida, Jue_Salida);
	}

	/** Get Thursday´s Check-Out.
		@return Thursday´s Check-Out	  */
	public Timestamp getJue_Salida () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Jue_Salida);
	}

	/** Set Monday's Check-In.
		@param Lun_Entrada Monday's Check-In	  */
	public void setLun_Entrada (Timestamp Lun_Entrada)
	{
		if (Lun_Entrada == null)
			throw new IllegalArgumentException ("Lun_Entrada is mandatory.");
		set_Value (COLUMNNAME_Lun_Entrada, Lun_Entrada);
	}

	/** Get Monday's Check-In.
		@return Monday's Check-In	  */
	public Timestamp getLun_Entrada () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Lun_Entrada);
	}

	/** Set Monday´s Check-Out.
		@param Lun_Salida Monday´s Check-Out	  */
	public void setLun_Salida (Timestamp Lun_Salida)
	{
		if (Lun_Salida == null)
			throw new IllegalArgumentException ("Lun_Salida is mandatory.");
		set_Value (COLUMNNAME_Lun_Salida, Lun_Salida);
	}

	/** Get Monday´s Check-Out.
		@return Monday´s Check-Out	  */
	public Timestamp getLun_Salida () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Lun_Salida);
	}

	/** Set Tuesday's Ceck-In.
		@param Mar_Entrada Tuesday's Ceck-In	  */
	public void setMar_Entrada (Timestamp Mar_Entrada)
	{
		if (Mar_Entrada == null)
			throw new IllegalArgumentException ("Mar_Entrada is mandatory.");
		set_Value (COLUMNNAME_Mar_Entrada, Mar_Entrada);
	}

	/** Get Tuesday's Ceck-In.
		@return Tuesday's Ceck-In	  */
	public Timestamp getMar_Entrada () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Mar_Entrada);
	}

	/** Set Tuesday's Check-Out.
		@param Mar_Salida Tuesday's Check-Out	  */
	public void setMar_Salida (Timestamp Mar_Salida)
	{
		if (Mar_Salida == null)
			throw new IllegalArgumentException ("Mar_Salida is mandatory.");
		set_Value (COLUMNNAME_Mar_Salida, Mar_Salida);
	}

	/** Get Tuesday's Check-Out.
		@return Tuesday's Check-Out	  */
	public Timestamp getMar_Salida () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Mar_Salida);
	}

	/** Set Wednesday's Check-In.
		@param Mie_Entrada Wednesday's Check-In	  */
	public void setMie_Entrada (Timestamp Mie_Entrada)
	{
		if (Mie_Entrada == null)
			throw new IllegalArgumentException ("Mie_Entrada is mandatory.");
		set_Value (COLUMNNAME_Mie_Entrada, Mie_Entrada);
	}

	/** Get Wednesday's Check-In.
		@return Wednesday's Check-In	  */
	public Timestamp getMie_Entrada () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Mie_Entrada);
	}

	/** Set Wednesday's Check-Out.
		@param Mie_Salida Wednesday's Check-Out	  */
	public void setMie_Salida (Timestamp Mie_Salida)
	{
		if (Mie_Salida == null)
			throw new IllegalArgumentException ("Mie_Salida is mandatory.");
		set_Value (COLUMNNAME_Mie_Salida, Mie_Salida);
	}

	/** Get Wednesday's Check-Out.
		@return Wednesday's Check-Out	  */
	public Timestamp getMie_Salida () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Mie_Salida);
	}

	/** Set Saturday's Check-In.
		@param Sab_Entrada Saturday's Check-In	  */
	public void setSab_Entrada (Timestamp Sab_Entrada)
	{
		if (Sab_Entrada == null)
			throw new IllegalArgumentException ("Sab_Entrada is mandatory.");
		set_Value (COLUMNNAME_Sab_Entrada, Sab_Entrada);
	}

	/** Get Saturday's Check-In.
		@return Saturday's Check-In	  */
	public Timestamp getSab_Entrada () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Sab_Entrada);
	}

	/** Set Saturday's Check-Out.
		@param Sab_Salida Saturday's Check-Out	  */
	public void setSab_Salida (Timestamp Sab_Salida)
	{
		if (Sab_Salida == null)
			throw new IllegalArgumentException ("Sab_Salida is mandatory.");
		set_Value (COLUMNNAME_Sab_Salida, Sab_Salida);
	}

	/** Get Saturday's Check-Out.
		@return Saturday's Check-Out	  */
	public Timestamp getSab_Salida () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Sab_Salida);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Friday's Check-In.
		@param Vie_Entrada Friday's Check-In	  */
	public void setVie_Entrada (Timestamp Vie_Entrada)
	{
		if (Vie_Entrada == null)
			throw new IllegalArgumentException ("Vie_Entrada is mandatory.");
		set_Value (COLUMNNAME_Vie_Entrada, Vie_Entrada);
	}

	/** Get Friday's Check-In.
		@return Friday's Check-In	  */
	public Timestamp getVie_Entrada () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Vie_Entrada);
	}

	/** Set Friday's Check-Out.
		@param Vie_Salida Friday's Check-Out	  */
	public void setVie_Salida (Timestamp Vie_Salida)
	{
		if (Vie_Salida == null)
			throw new IllegalArgumentException ("Vie_Salida is mandatory.");
		set_Value (COLUMNNAME_Vie_Salida, Vie_Salida);
	}

	/** Get Friday's Check-Out.
		@return Friday's Check-Out	  */
	public Timestamp getVie_Salida () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Vie_Salida);
	}
}