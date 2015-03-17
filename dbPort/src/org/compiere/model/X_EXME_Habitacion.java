/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Habitacion
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Habitacion extends PO implements I_EXME_Habitacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Habitacion (Properties ctx, int EXME_Habitacion_ID, String trxName)
    {
      super (ctx, EXME_Habitacion_ID, trxName);
      /** if (EXME_Habitacion_ID == 0)
        {
			setEstatus (false);
			setEXME_Habitacion_ID (0);
			setEXME_TipoHabitacion_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Habitacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Habitacion[")
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

	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (boolean Estatus)
	{
		set_Value (COLUMNNAME_Estatus, Boolean.valueOf(Estatus));
	}

	/** Get Status.
		@return Status
	  */
	public boolean isEstatus () 
	{
		Object oo = get_Value(COLUMNNAME_Estatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Daily Charges.
		@param EXME_CDiario_ID 
		Daily Charges
	  */
	public void setEXME_CDiario_ID (int EXME_CDiario_ID)
	{
		if (EXME_CDiario_ID < 1) 
			set_Value (COLUMNNAME_EXME_CDiario_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CDiario_ID, Integer.valueOf(EXME_CDiario_ID));
	}

	/** Get Daily Charges.
		@return Daily Charges
	  */
	public int getEXME_CDiario_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CDiario_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Specialty.
		@param EXME_Especialidad_ID 
		Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID)
	{
		if (EXME_Especialidad_ID < 1) 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Especialidad_ID, Integer.valueOf(EXME_Especialidad_ID));
	}

	/** Get Specialty.
		@return Specialty
	  */
	public int getEXME_Especialidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Especialidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EstServ.Table_Name);
        I_EXME_EstServ result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EstServ)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EstServ_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Service Unit.
		@param EXME_EstServ_ID 
		Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1) 
			set_Value (COLUMNNAME_EXME_EstServ_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Unit.
		@return Service Unit
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
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

	/** Set Type of Room.
		@param EXME_TipoHabitacion_ID 
		Type of Room
	  */
	public void setEXME_TipoHabitacion_ID (int EXME_TipoHabitacion_ID)
	{
		if (EXME_TipoHabitacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoHabitacion_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoHabitacion_ID, Integer.valueOf(EXME_TipoHabitacion_ID));
	}

	/** Get Type of Room.
		@return Type of Room
	  */
	public int getEXME_TipoHabitacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoHabitacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** TipoLimpieza AD_Reference_ID=1200067 */
	public static final int TIPOLIMPIEZA_AD_Reference_ID=1200067;
	/** Desinfect = D */
	public static final String TIPOLIMPIEZA_Desinfect = "D";
	/** Normal Cleaning = AN */
	public static final String TIPOLIMPIEZA_NormalCleaning = "AN";
	/** Set CleanningType.
		@param TipoLimpieza 
		Cleanning Typeto realize
	  */
	public void setTipoLimpieza (String TipoLimpieza)
	{

		if (TipoLimpieza == null || TipoLimpieza.equals("D") || TipoLimpieza.equals("AN")); else throw new IllegalArgumentException ("TipoLimpieza Invalid value - " + TipoLimpieza + " - Reference_ID=1200067 - D - AN");		set_Value (COLUMNNAME_TipoLimpieza, TipoLimpieza);
	}

	/** Get CleanningType.
		@return Cleanning Typeto realize
	  */
	public String getTipoLimpieza () 
	{
		return (String)get_Value(COLUMNNAME_TipoLimpieza);
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