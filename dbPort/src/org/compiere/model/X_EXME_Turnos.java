/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Turnos
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Turnos extends PO implements I_EXME_Turnos, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Turnos (Properties ctx, int EXME_Turnos_ID, String trxName)
    {
      super (ctx, EXME_Turnos_ID, trxName);
      /** if (EXME_Turnos_ID == 0)
        {
			setEXME_Turnos_ID (0);
			setHoraEnt1Es (null);
			setHoraSal1Es (null);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Turnos (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Turnos[")
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

	/** Set Shift.
		@param EXME_Turnos_ID 
		Shift
	  */
	public void setEXME_Turnos_ID (int EXME_Turnos_ID)
	{
		if (EXME_Turnos_ID < 1)
			 throw new IllegalArgumentException ("EXME_Turnos_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Turnos_ID, Integer.valueOf(EXME_Turnos_ID));
	}

	/** Get Shift.
		@return Shift
	  */
	public int getEXME_Turnos_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Turnos_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Check in Hour 1.
		@param HoraEnt1Es 
		Check in Hour 1
	  */
	public void setHoraEnt1Es (String HoraEnt1Es)
	{
		if (HoraEnt1Es == null)
			throw new IllegalArgumentException ("HoraEnt1Es is mandatory.");
		set_Value (COLUMNNAME_HoraEnt1Es, HoraEnt1Es);
	}

	/** Get Check in Hour 1.
		@return Check in Hour 1
	  */
	public String getHoraEnt1Es () 
	{
		return (String)get_Value(COLUMNNAME_HoraEnt1Es);
	}

	/** Set Week End Check in Hour.
		@param HoraEnt1Fs 
		Week End Check in Hour
	  */
	public void setHoraEnt1Fs (String HoraEnt1Fs)
	{
		set_Value (COLUMNNAME_HoraEnt1Fs, HoraEnt1Fs);
	}

	/** Get Week End Check in Hour.
		@return Week End Check in Hour
	  */
	public String getHoraEnt1Fs () 
	{
		return (String)get_Value(COLUMNNAME_HoraEnt1Fs);
	}

	/** Set Check in Hour 2.
		@param HoraEnt2Es 
		Check in Hour 2
	  */
	public void setHoraEnt2Es (String HoraEnt2Es)
	{
		set_Value (COLUMNNAME_HoraEnt2Es, HoraEnt2Es);
	}

	/** Get Check in Hour 2.
		@return Check in Hour 2
	  */
	public String getHoraEnt2Es () 
	{
		return (String)get_Value(COLUMNNAME_HoraEnt2Es);
	}

	/** Set Check in Hour 3.
		@param HoraEnt3Es 
		Check in Hour 3
	  */
	public void setHoraEnt3Es (String HoraEnt3Es)
	{
		set_Value (COLUMNNAME_HoraEnt3Es, HoraEnt3Es);
	}

	/** Get Check in Hour 3.
		@return Check in Hour 3
	  */
	public String getHoraEnt3Es () 
	{
		return (String)get_Value(COLUMNNAME_HoraEnt3Es);
	}

	/** Set Check out hour 1.
		@param HoraSal1Es 
		Check out 1
	  */
	public void setHoraSal1Es (String HoraSal1Es)
	{
		if (HoraSal1Es == null)
			throw new IllegalArgumentException ("HoraSal1Es is mandatory.");
		set_Value (COLUMNNAME_HoraSal1Es, HoraSal1Es);
	}

	/** Get Check out hour 1.
		@return Check out 1
	  */
	public String getHoraSal1Es () 
	{
		return (String)get_Value(COLUMNNAME_HoraSal1Es);
	}

	/** Set Week End Check Out Hour.
		@param HoraSal1Fs 
		Week End Check Out Hour
	  */
	public void setHoraSal1Fs (String HoraSal1Fs)
	{
		set_Value (COLUMNNAME_HoraSal1Fs, HoraSal1Fs);
	}

	/** Get Week End Check Out Hour.
		@return Week End Check Out Hour
	  */
	public String getHoraSal1Fs () 
	{
		return (String)get_Value(COLUMNNAME_HoraSal1Fs);
	}

	/** Set Check Our Hour 2.
		@param HoraSal2Es 
		Check out hour 2
	  */
	public void setHoraSal2Es (String HoraSal2Es)
	{
		set_Value (COLUMNNAME_HoraSal2Es, HoraSal2Es);
	}

	/** Get Check Our Hour 2.
		@return Check out hour 2
	  */
	public String getHoraSal2Es () 
	{
		return (String)get_Value(COLUMNNAME_HoraSal2Es);
	}

	/** Set Check Out Hour 3.
		@param HoraSal3Es 
		Check out hour 3
	  */
	public void setHoraSal3Es (String HoraSal3Es)
	{
		set_Value (COLUMNNAME_HoraSal3Es, HoraSal3Es);
	}

	/** Get Check Out Hour 3.
		@return Check out hour 3
	  */
	public String getHoraSal3Es () 
	{
		return (String)get_Value(COLUMNNAME_HoraSal3Es);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
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
}