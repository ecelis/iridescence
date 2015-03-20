/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for AD_Scheduler_Para
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_Scheduler_Para extends PO implements I_AD_Scheduler_Para, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_Scheduler_Para (Properties ctx, int AD_Scheduler_Para_ID, String trxName)
    {
      super (ctx, AD_Scheduler_Para_ID, trxName);
      /** if (AD_Scheduler_Para_ID == 0)
        {
			setAD_Process_Para_ID (0);
			setAD_Scheduler_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_Scheduler_Para (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_Scheduler_Para[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Process_Para getAD_Process_Para() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Process_Para.Table_Name);
        I_AD_Process_Para result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Process_Para)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Process_Para_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Process Parameter.
		@param AD_Process_Para_ID 
		Process Parameter
	  */
	public void setAD_Process_Para_ID (int AD_Process_Para_ID)
	{
		if (AD_Process_Para_ID < 1)
			 throw new IllegalArgumentException ("AD_Process_Para_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Process_Para_ID, Integer.valueOf(AD_Process_Para_ID));
	}

	/** Get Process Parameter.
		@return Process Parameter
	  */
	public int getAD_Process_Para_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Process_Para_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Scheduler getAD_Scheduler() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Scheduler.Table_Name);
        I_AD_Scheduler result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Scheduler)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Scheduler_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Scheduler.
		@param AD_Scheduler_ID 
		Schedule Processes
	  */
	public void setAD_Scheduler_ID (int AD_Scheduler_ID)
	{
		if (AD_Scheduler_ID < 1)
			 throw new IllegalArgumentException ("AD_Scheduler_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Scheduler_ID, Integer.valueOf(AD_Scheduler_ID));
	}

	/** Get Scheduler.
		@return Schedule Processes
	  */
	public int getAD_Scheduler_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Scheduler_ID);
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

	/** Set Default Parameter.
		@param ParameterDefault 
		Default value of the parameter
	  */
	public void setParameterDefault (String ParameterDefault)
	{
		set_Value (COLUMNNAME_ParameterDefault, ParameterDefault);
	}

	/** Get Default Parameter.
		@return Default value of the parameter
	  */
	public String getParameterDefault () 
	{
		return (String)get_Value(COLUMNNAME_ParameterDefault);
	}
}