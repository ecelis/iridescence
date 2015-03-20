/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for WF_DemoLine
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_WF_DemoLine extends PO implements I_WF_DemoLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_WF_DemoLine (Properties ctx, int WF_DemoLine_ID, String trxName)
    {
      super (ctx, WF_DemoLine_ID, trxName);
      /** if (WF_DemoLine_ID == 0)
        {
			setRecord_ID (0);
			setWF_DemoLine_ID (0);
			setWF_Demo_ID (0);
        } */
    }

    /** Load Constructor */
    public X_WF_DemoLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_WF_DemoLine[")
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

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 1)
			set_Value(COLUMNNAME_Record_ID, null);
		else
			set_Value(COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set WF_DemoLine_ID.
		@param WF_DemoLine_ID WF_DemoLine_ID	  */
	public void setWF_DemoLine_ID (int WF_DemoLine_ID)
	{
		if (WF_DemoLine_ID < 1)
			 throw new IllegalArgumentException ("WF_DemoLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_WF_DemoLine_ID, Integer.valueOf(WF_DemoLine_ID));
	}

	/** Get WF_DemoLine_ID.
		@return WF_DemoLine_ID	  */
	public int getWF_DemoLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WF_DemoLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_WF_Demo getWF_Demo() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_WF_Demo.Table_Name);
        I_WF_Demo result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_WF_Demo)constructor.newInstance(new Object[] {getCtx(), new Integer(getWF_Demo_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set WF_Demo_ID.
		@param WF_Demo_ID WF_Demo_ID	  */
	public void setWF_Demo_ID (int WF_Demo_ID)
	{
		if (WF_Demo_ID < 1)
			 throw new IllegalArgumentException ("WF_Demo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_WF_Demo_ID, Integer.valueOf(WF_Demo_ID));
	}

	/** Get WF_Demo_ID.
		@return WF_Demo_ID	  */
	public int getWF_Demo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WF_Demo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}