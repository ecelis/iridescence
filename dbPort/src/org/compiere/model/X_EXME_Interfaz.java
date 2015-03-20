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

/** Generated Model for EXME_Interfaz
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Interfaz extends PO implements I_EXME_Interfaz, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Interfaz (Properties ctx, int EXME_Interfaz_ID, String trxName)
    {
      super (ctx, EXME_Interfaz_ID, trxName);
      /** if (EXME_Interfaz_ID == 0)
        {
			setAD_Process_ID (0);
			setEXME_Interfaz_ID (0);
			setEXME_InterfazProcessor_ID (0);
			setName (null);
			setTableName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Interfaz (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_EXME_Interfaz[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Process getAD_Process() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Process.Table_Name);
        I_AD_Process result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Process)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Process_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Process.
		@param AD_Process_ID 
		Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID)
	{
		if (AD_Process_ID < 1)
			 throw new IllegalArgumentException ("AD_Process_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Process_ID, Integer.valueOf(AD_Process_ID));
	}

	/** Get Process.
		@return Process or Report
	  */
	public int getAD_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Process_ID);
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

	/** Set Interface.
		@param EXME_Interfaz_ID Interface	  */
	public void setEXME_Interfaz_ID (int EXME_Interfaz_ID)
	{
		if (EXME_Interfaz_ID < 1)
			 throw new IllegalArgumentException ("EXME_Interfaz_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Interfaz_ID, Integer.valueOf(EXME_Interfaz_ID));
	}

	/** Get Interface.
		@return Interface	  */
	public int getEXME_Interfaz_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Interfaz_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_InterfazProcessor getEXME_InterfazProcessor() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_InterfazProcessor.Table_Name);
        I_EXME_InterfazProcessor result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_InterfazProcessor)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_InterfazProcessor_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Processor Interface.
		@param EXME_InterfazProcessor_ID Processor Interface	  */
	public void setEXME_InterfazProcessor_ID (int EXME_InterfazProcessor_ID)
	{
		if (EXME_InterfazProcessor_ID < 1)
			 throw new IllegalArgumentException ("EXME_InterfazProcessor_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_InterfazProcessor_ID, Integer.valueOf(EXME_InterfazProcessor_ID));
	}

	/** Get Processor Interface.
		@return Processor Interface	  */
	public int getEXME_InterfazProcessor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InterfazProcessor_ID);
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

	/** Set DB Table Name.
		@param TableName 
		Name of the table in the database
	  */
	public void setTableName (String TableName)
	{
		if (TableName == null)
			throw new IllegalArgumentException ("TableName is mandatory.");
		set_Value (COLUMNNAME_TableName, TableName);
	}

	/** Get DB Table Name.
		@return Name of the table in the database
	  */
	public String getTableName () 
	{
		return (String)get_Value(COLUMNNAME_TableName);
	}
}