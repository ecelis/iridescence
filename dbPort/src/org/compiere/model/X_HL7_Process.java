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

/** Generated Model for HL7_Process
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_Process extends PO implements I_HL7_Process, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_Process (Properties ctx, int HL7_Process_ID, String trxName)
    {
      super (ctx, HL7_Process_ID, trxName);
      /** if (HL7_Process_ID == 0)
        {
			setAD_Menu_ID (0);
			setAD_Table_ID (0);
			setHL7_Process_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_Process (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_HL7_Process[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Menu getAD_Menu() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Menu.Table_Name);
        I_AD_Menu result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Menu)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Menu_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Menu.
		@param AD_Menu_ID 
		Identifies a Menu
	  */
	public void setAD_Menu_ID (int AD_Menu_ID)
	{
		if (AD_Menu_ID < 1)
			 throw new IllegalArgumentException ("AD_Menu_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Menu_ID, Integer.valueOf(AD_Menu_ID));
	}

	/** Get Menu.
		@return Identifies a Menu
	  */
	public int getAD_Menu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Menu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_Menu_ID()));
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1)
			 throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HL7 Process.
		@param HL7_Process_ID 
		Process that generate HL7 Messages
	  */
	public void setHL7_Process_ID (int HL7_Process_ID)
	{
		if (HL7_Process_ID < 1)
			 throw new IllegalArgumentException ("HL7_Process_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_Process_ID, Integer.valueOf(HL7_Process_ID));
	}

	/** Get HL7 Process.
		@return Process that generate HL7 Messages
	  */
	public int getHL7_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_Process_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Route.
		@param Ruta 
		Route of screen or process
	  */
	public void setRuta (String Ruta)
	{
		set_Value (COLUMNNAME_Ruta, Ruta);
	}

	/** Get Route.
		@return Route of screen or process
	  */
	public String getRuta () 
	{
		return (String)get_Value(COLUMNNAME_Ruta);
	}
}