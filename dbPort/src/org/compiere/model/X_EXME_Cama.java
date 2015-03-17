/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Cama
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Cama extends PO implements I_EXME_Cama, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Cama (Properties ctx, int EXME_Cama_ID, String trxName)
    {
      super (ctx, EXME_Cama_ID, trxName);
      /** if (EXME_Cama_ID == 0)
        {
			setEXME_Cama_ID (0);
			setEXME_Habitacion_ID (0);
			setIsCensable (true);
// 'Y'
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Cama (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Cama[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Alternate Bed.
		@param Cama_Alterna 
		Alternate Bed
	  */
	public void setCama_Alterna (String Cama_Alterna)
	{
		set_Value (COLUMNNAME_Cama_Alterna, Cama_Alterna);
	}

	/** Get Alternate Bed.
		@return Alternate Bed
	  */
	public String getCama_Alterna () 
	{
		return (String)get_Value(COLUMNNAME_Cama_Alterna);
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

	/** Estatus AD_Reference_ID=1000047 */
	public static final int ESTATUS_AD_Reference_ID=1000047;
	/** Available Clean = DL */
	public static final String ESTATUS_AvailableClean = "DL";
	/** Available Dirty = DS */
	public static final String ESTATUS_AvailableDirty = "DS";
	/** Available on Maintenance = DM */
	public static final String ESTATUS_AvailableOnMaintenance = "DM";
	/** Occupied Clean = OL */
	public static final String ESTATUS_OccupiedClean = "OL";
	/** Occupied Dirty = OS */
	public static final String ESTATUS_OccupiedDirty = "OS";
	/** Occupied on Maintenance = OM */
	public static final String ESTATUS_OccupiedOnMaintenance = "OM";
	/** On Quarantine = EC */
	public static final String ESTATUS_OnQuarantine = "EC";
	/** Reserved = RE */
	public static final String ESTATUS_Reserved = "RE";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{

		if (Estatus == null || Estatus.equals("DL") || Estatus.equals("DS") || Estatus.equals("DM") || Estatus.equals("OL") || Estatus.equals("OS") || Estatus.equals("OM") || Estatus.equals("EC") || Estatus.equals("RE")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1000047 - DL - DS - DM - OL - OS - OM - EC - RE");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set Bed.
		@param EXME_Cama_ID 
		Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID)
	{
		if (EXME_Cama_ID < 1)
			 throw new IllegalArgumentException ("EXME_Cama_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Cama_ID, Integer.valueOf(EXME_Cama_ID));
	}

	/** Get Bed.
		@return Bed
	  */
	public int getEXME_Cama_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Cama_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
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
		set_ValueNoCheck (COLUMNNAME_EXME_Habitacion_ID, Integer.valueOf(EXME_Habitacion_ID));
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

	/** Set Is Censable.
		@param IsCensable 
		Is Censable
	  */
	public void setIsCensable (boolean IsCensable)
	{
		set_Value (COLUMNNAME_IsCensable, Boolean.valueOf(IsCensable));
	}

	/** Get Is Censable.
		@return Is Censable
	  */
	public boolean isCensable () 
	{
		Object oo = get_Value(COLUMNNAME_IsCensable);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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