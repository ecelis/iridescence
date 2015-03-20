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

/** Generated Model for EXME_ExternalVaccine
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ExternalVaccine extends PO implements I_EXME_ExternalVaccine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ExternalVaccine (Properties ctx, int EXME_ExternalVaccine_ID, String trxName)
    {
      super (ctx, EXME_ExternalVaccine_ID, trxName);
      /** if (EXME_ExternalVaccine_ID == 0)
        {
			setEXME_CitaMedica_ID (0);
			setEXME_ExternalVaccine_ID (0);
			setEXME_Vacuna_ID (0);
			setFechaAplica (new Timestamp( System.currentTimeMillis() ));
			setIsClosed (false);
			setLocation (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ExternalVaccine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ExternalVaccine[")
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

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CitaMedica.Table_Name);
        I_EXME_CitaMedica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CitaMedica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CitaMedica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Appointment.
		@param EXME_CitaMedica_ID 
		Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID)
	{
		if (EXME_CitaMedica_ID < 1)
			 throw new IllegalArgumentException ("EXME_CitaMedica_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CitaMedica_ID, Integer.valueOf(EXME_CitaMedica_ID));
	}

	/** Get Medical Appointment.
		@return Medical appointment
	  */
	public int getEXME_CitaMedica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CitaMedica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EXME_ExternalVaccine_ID.
		@param EXME_ExternalVaccine_ID EXME_ExternalVaccine_ID	  */
	public void setEXME_ExternalVaccine_ID (int EXME_ExternalVaccine_ID)
	{
		if (EXME_ExternalVaccine_ID < 1)
			 throw new IllegalArgumentException ("EXME_ExternalVaccine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ExternalVaccine_ID, Integer.valueOf(EXME_ExternalVaccine_ID));
	}

	/** Get EXME_ExternalVaccine_ID.
		@return EXME_ExternalVaccine_ID	  */
	public int getEXME_ExternalVaccine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ExternalVaccine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Vacuna getEXME_Vacuna() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Vacuna.Table_Name);
        I_EXME_Vacuna result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Vacuna)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Vacuna_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Vaccine.
		@param EXME_Vacuna_ID 
		Vaccine
	  */
	public void setEXME_Vacuna_ID (int EXME_Vacuna_ID)
	{
		if (EXME_Vacuna_ID < 1)
			 throw new IllegalArgumentException ("EXME_Vacuna_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Vacuna_ID, Integer.valueOf(EXME_Vacuna_ID));
	}

	/** Get Vaccine.
		@return Vaccine
	  */
	public int getEXME_Vacuna_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Vacuna_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date of Application.
		@param FechaAplica 
		Date of Application
	  */
	public void setFechaAplica (Timestamp FechaAplica)
	{
		if (FechaAplica == null)
			throw new IllegalArgumentException ("FechaAplica is mandatory.");
		set_Value (COLUMNNAME_FechaAplica, FechaAplica);
	}

	/** Get Date of Application.
		@return Date of Application
	  */
	public Timestamp getFechaAplica () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAplica);
	}

	/** Set Closed Status.
		@param IsClosed 
		The status is closed
	  */
	public void setIsClosed (boolean IsClosed)
	{
		set_Value (COLUMNNAME_IsClosed, Boolean.valueOf(IsClosed));
	}

	/** Get Closed Status.
		@return The status is closed
	  */
	public boolean isClosed () 
	{
		Object oo = get_Value(COLUMNNAME_IsClosed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Location.
		@param Location Location	  */
	public void setLocation (String Location)
	{
		if (Location == null)
			throw new IllegalArgumentException ("Location is mandatory.");
		set_Value (COLUMNNAME_Location, Location);
	}

	/** Get Location.
		@return Location	  */
	public String getLocation () 
	{
		return (String)get_Value(COLUMNNAME_Location);
	}
}