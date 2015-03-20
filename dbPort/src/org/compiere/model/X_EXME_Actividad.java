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
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Actividad
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Actividad extends PO implements I_EXME_Actividad, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Actividad (Properties ctx, int EXME_Actividad_ID, String trxName)
    {
      super (ctx, EXME_Actividad_ID, trxName);
      /** if (EXME_Actividad_ID == 0)
        {
			setEndDate (new Timestamp( System.currentTimeMillis() ));
			setEXME_Actividad_ID (0);
			setEXME_Medico_ID (0);
			setEXME_TipoActividad_ID (0);
			setName (null);
			setProcessing (false);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Actividad (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Actividad[")
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		if (EndDate == null)
			throw new IllegalArgumentException ("EndDate is mandatory.");
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set Activity.
		@param EXME_Actividad_ID Activity	  */
	public void setEXME_Actividad_ID (int EXME_Actividad_ID)
	{
		if (EXME_Actividad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Actividad_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Actividad_ID, Integer.valueOf(EXME_Actividad_ID));
	}

	/** Get Activity.
		@return Activity	  */
	public int getEXME_Actividad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Actividad_ID);
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

	public I_EXME_TipoActividad getEXME_TipoActividad() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_TipoActividad.Table_Name);
        I_EXME_TipoActividad result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_TipoActividad)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_TipoActividad_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Activity Type.
		@param EXME_TipoActividad_ID Activity Type	  */
	public void setEXME_TipoActividad_ID (int EXME_TipoActividad_ID)
	{
		if (EXME_TipoActividad_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoActividad_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoActividad_ID, Integer.valueOf(EXME_TipoActividad_ID));
	}

	/** Get Activity Type.
		@return Activity Type	  */
	public int getEXME_TipoActividad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoActividad_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		if (StartDate == null)
			throw new IllegalArgumentException ("StartDate is mandatory.");
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Use Substitute.
		@param UsaSustituto 
		Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public void setUsaSustituto (boolean UsaSustituto)
	{
		throw new IllegalArgumentException ("UsaSustituto is virtual column");	}

	/** Get Use Substitute.
		@return Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public boolean isUsaSustituto () 
	{
		Object oo = get_Value(COLUMNNAME_UsaSustituto);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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