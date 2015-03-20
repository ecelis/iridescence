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

/** Generated Model for EXME_RecursoEducativoPac
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_RecursoEducativoPac extends PO implements I_EXME_RecursoEducativoPac, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_RecursoEducativoPac (Properties ctx, int EXME_RecursoEducativoPac_ID, String trxName)
    {
      super (ctx, EXME_RecursoEducativoPac_ID, trxName);
      /** if (EXME_RecursoEducativoPac_ID == 0)
        {
			setEXME_Paciente_ID (0);
			setEXME_RecursoEducativoPac_ID (0);
			setURL (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_RecursoEducativoPac (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_RecursoEducativoPac[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_EncounterForms.Table_Name);
        I_EXME_EncounterForms result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_EncounterForms)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_EncounterForms_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter Forms.
		@param EXME_EncounterForms_ID 
		Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID)
	{
		if (EXME_EncounterForms_ID < 1) 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_EncounterForms_ID, Integer.valueOf(EXME_EncounterForms_ID));
	}

	/** Get Encounter Forms.
		@return Encounter Forms
	  */
	public int getEXME_EncounterForms_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EncounterForms_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient Education Resource.
		@param EXME_RecursoEducativoPac_ID 
		Patient Education Resource
	  */
	public void setEXME_RecursoEducativoPac_ID (int EXME_RecursoEducativoPac_ID)
	{
		if (EXME_RecursoEducativoPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_RecursoEducativoPac_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_RecursoEducativoPac_ID, Integer.valueOf(EXME_RecursoEducativoPac_ID));
	}

	/** Get Patient Education Resource.
		@return Patient Education Resource
	  */
	public int getEXME_RecursoEducativoPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativoPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_RecursoEducativo.Table_Name);
        I_EXME_RecursoEducativo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_RecursoEducativo)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_RecursoEducativo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Education Resource.
		@param EXME_RecursoEducativo_ID Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID)
	{
		if (EXME_RecursoEducativo_ID < 1) 
			set_Value (COLUMNNAME_EXME_RecursoEducativo_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_RecursoEducativo_ID, Integer.valueOf(EXME_RecursoEducativo_ID));
	}

	/** Get Education Resource.
		@return Education Resource	  */
	public int getEXME_RecursoEducativo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_RecursoEducativo_ID);
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

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL)
	{
		if (URL == null)
			throw new IllegalArgumentException ("URL is mandatory.");
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
	}
}