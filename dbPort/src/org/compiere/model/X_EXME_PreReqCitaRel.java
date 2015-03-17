/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PreReqCitaRel
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PreReqCitaRel extends PO implements I_EXME_PreReqCitaRel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PreReqCitaRel (Properties ctx, int EXME_PreReqCitaRel_ID, String trxName)
    {
      super (ctx, EXME_PreReqCitaRel_ID, trxName);
      /** if (EXME_PreReqCitaRel_ID == 0)
        {
			setEXME_CitaMedica_ID (0);
			setEXME_PreReqCita_ID (0);
			setEXME_PreReqCitaRel_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PreReqCitaRel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PreReqCitaRel[")
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

	public I_EXME_PreReqCita getEXME_PreReqCita() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PreReqCita.Table_Name);
        I_EXME_PreReqCita result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PreReqCita)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PreReqCita_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Consultation's Prerequisites Catalog.
		@param EXME_PreReqCita_ID Medical Consultation's Prerequisites Catalog	  */
	public void setEXME_PreReqCita_ID (int EXME_PreReqCita_ID)
	{
		if (EXME_PreReqCita_ID < 1)
			 throw new IllegalArgumentException ("EXME_PreReqCita_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_PreReqCita_ID, Integer.valueOf(EXME_PreReqCita_ID));
	}

	/** Get Medical Consultation's Prerequisites Catalog.
		@return Medical Consultation's Prerequisites Catalog	  */
	public int getEXME_PreReqCita_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreReqCita_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Medical Consultation's Prerequistes.
		@param EXME_PreReqCitaRel_ID Medical Consultation's Prerequistes	  */
	public void setEXME_PreReqCitaRel_ID (int EXME_PreReqCitaRel_ID)
	{
		if (EXME_PreReqCitaRel_ID < 1)
			 throw new IllegalArgumentException ("EXME_PreReqCitaRel_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PreReqCitaRel_ID, Integer.valueOf(EXME_PreReqCitaRel_ID));
	}

	/** Get Medical Consultation's Prerequistes.
		@return Medical Consultation's Prerequistes	  */
	public int getEXME_PreReqCitaRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PreReqCitaRel_ID);
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