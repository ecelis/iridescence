/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Quirofano
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Quirofano extends PO implements I_EXME_Quirofano, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Quirofano (Properties ctx, int EXME_Quirofano_ID, String trxName)
    {
      super (ctx, EXME_Quirofano_ID, trxName);
      /** if (EXME_Quirofano_ID == 0)
        {
			setDisponible (false);
			setDispQuirFin (new Timestamp( System.currentTimeMillis() ));
// 1970-01-01 00:00:00
			setDispQuirIni (new Timestamp( System.currentTimeMillis() ));
// 1970-01-01 00:00:00
			setEXME_EstServ_ID (0);
			setEXME_Quirofano_ID (0);
			setIntervalo (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Quirofano (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Quirofano[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Color getAD_Color() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Color.Table_Name);
        I_AD_Color result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Color)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Color_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set System Color.
		@param AD_Color_ID 
		Color for backgrounds or indicators
	  */
	public void setAD_Color_ID (int AD_Color_ID)
	{
		if (AD_Color_ID < 1) 
			set_Value (COLUMNNAME_AD_Color_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Color_ID, Integer.valueOf(AD_Color_ID));
	}

	/** Get System Color.
		@return Color for backgrounds or indicators
	  */
	public int getAD_Color_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Color_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Available.
		@param Disponible 
		Available
	  */
	public void setDisponible (boolean Disponible)
	{
		set_Value (COLUMNNAME_Disponible, Boolean.valueOf(Disponible));
	}

	/** Get Available.
		@return Available
	  */
	public boolean isDisponible () 
	{
		Object oo = get_Value(COLUMNNAME_Disponible);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Available To.
		@param DispQuirFin 
		Available To
	  */
	public void setDispQuirFin (Timestamp DispQuirFin)
	{
		if (DispQuirFin == null)
			throw new IllegalArgumentException ("DispQuirFin is mandatory.");
		set_Value (COLUMNNAME_DispQuirFin, DispQuirFin);
	}

	/** Get Available To.
		@return Available To
	  */
	public Timestamp getDispQuirFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DispQuirFin);
	}

	/** Set Available Since.
		@param DispQuirIni 
		Available Since
	  */
	public void setDispQuirIni (Timestamp DispQuirIni)
	{
		if (DispQuirIni == null)
			throw new IllegalArgumentException ("DispQuirIni is mandatory.");
		set_Value (COLUMNNAME_DispQuirIni, DispQuirIni);
	}

	/** Get Available Since.
		@return Available Since
	  */
	public Timestamp getDispQuirIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DispQuirIni);
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

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServ_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Surgery Room.
		@param EXME_Quirofano_ID 
		Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID)
	{
		if (EXME_Quirofano_ID < 1)
			 throw new IllegalArgumentException ("EXME_Quirofano_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Quirofano_ID, Integer.valueOf(EXME_Quirofano_ID));
	}

	/** Get Surgery Room.
		@return Surgey Room
	  */
	public int getEXME_Quirofano_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Quirofano_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interval.
		@param Intervalo 
		Interval
	  */
	public void setIntervalo (int Intervalo)
	{
		set_Value (COLUMNNAME_Intervalo, Integer.valueOf(Intervalo));
	}

	/** Get Interval.
		@return Interval
	  */
	public int getIntervalo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Intervalo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
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

	/** Set Responsible.
		@param RespQuirofano 
		Responsible of surgery room
	  */
	public void setRespQuirofano (int RespQuirofano)
	{
		set_Value (COLUMNNAME_RespQuirofano, Integer.valueOf(RespQuirofano));
	}

	/** Get Responsible.
		@return Responsible of surgery room
	  */
	public int getRespQuirofano () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RespQuirofano);
		if (ii == null)
			 return 0;
		return ii.intValue();
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