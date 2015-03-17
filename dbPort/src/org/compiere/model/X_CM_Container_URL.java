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

/** Generated Model for CM_Container_URL
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_CM_Container_URL extends PO implements I_CM_Container_URL, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_CM_Container_URL (Properties ctx, int CM_Container_URL_ID, String trxName)
    {
      super (ctx, CM_Container_URL_ID, trxName);
      /** if (CM_Container_URL_ID == 0)
        {
			setChecked (new Timestamp( System.currentTimeMillis() ));
			setCM_Container_ID (0);
			setCM_Container_URL_ID (0);
			setLast_Result (null);
			setStatus (null);
        } */
    }

    /** Load Constructor */
    public X_CM_Container_URL (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CM_Container_URL[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Last Checked.
		@param Checked 
		Info when we did the last check
	  */
	public void setChecked (Timestamp Checked)
	{
		if (Checked == null)
			throw new IllegalArgumentException ("Checked is mandatory.");
		set_Value (COLUMNNAME_Checked, Checked);
	}

	/** Get Last Checked.
		@return Info when we did the last check
	  */
	public Timestamp getChecked () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Checked);
	}

	public I_CM_Container getCM_Container() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_CM_Container.Table_Name);
        I_CM_Container result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_CM_Container)constructor.newInstance(new Object[] {getCtx(), new Integer(getCM_Container_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Web Container.
		@param CM_Container_ID 
		Web Container contains content like images, text etc.
	  */
	public void setCM_Container_ID (int CM_Container_ID)
	{
		if (CM_Container_ID < 1)
			 throw new IllegalArgumentException ("CM_Container_ID is mandatory.");
		set_Value (COLUMNNAME_CM_Container_ID, Integer.valueOf(CM_Container_ID));
	}

	/** Get Web Container.
		@return Web Container contains content like images, text etc.
	  */
	public int getCM_Container_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_Container_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Container URL.
		@param CM_Container_URL_ID 
		Contains info on used URLs
	  */
	public void setCM_Container_URL_ID (int CM_Container_URL_ID)
	{
		if (CM_Container_URL_ID < 1)
			 throw new IllegalArgumentException ("CM_Container_URL_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_Container_URL_ID, Integer.valueOf(CM_Container_URL_ID));
	}

	/** Get Container URL.
		@return Contains info on used URLs
	  */
	public int getCM_Container_URL_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_Container_URL_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Last Result.
		@param Last_Result 
		Contains data on the last check result
	  */
	public void setLast_Result (String Last_Result)
	{
		if (Last_Result == null)
			throw new IllegalArgumentException ("Last_Result is mandatory.");
		set_Value (COLUMNNAME_Last_Result, Last_Result);
	}

	/** Get Last Result.
		@return Contains data on the last check result
	  */
	public String getLast_Result () 
	{
		return (String)get_Value(COLUMNNAME_Last_Result);
	}

	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		if (Status == null)
			throw new IllegalArgumentException ("Status is mandatory.");
		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}
}