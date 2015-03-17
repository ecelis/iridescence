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

/** Generated Model for EXME_Frequency3
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Frequency3 extends PO implements I_EXME_Frequency3, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Frequency3 (Properties ctx, int EXME_Frequency3_ID, String trxName)
    {
      super (ctx, EXME_Frequency3_ID, trxName);
      /** if (EXME_Frequency3_ID == 0)
        {
			setEXME_Frequency1_ID (0);
			setEXME_Frequency2_ID (0);
			setEXME_Frequency3_ID (0);
			setHour (new Timestamp( System.currentTimeMillis() ));
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Frequency3 (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Frequency3[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_EXME_Frequency1 getEXME_Frequency1() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Frequency1.Table_Name);
        I_EXME_Frequency1 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Frequency1)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Frequency1_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Frequency 1.
		@param EXME_Frequency1_ID 
		Frequency Header ID
	  */
	public void setEXME_Frequency1_ID (int EXME_Frequency1_ID)
	{
		if (EXME_Frequency1_ID < 1)
			 throw new IllegalArgumentException ("EXME_Frequency1_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Frequency1_ID, Integer.valueOf(EXME_Frequency1_ID));
	}

	/** Get Frequency 1.
		@return Frequency Header ID
	  */
	public int getEXME_Frequency1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Frequency2 getEXME_Frequency2() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Frequency2.Table_Name);
        I_EXME_Frequency2 result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Frequency2)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Frequency2_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Frequency 2.
		@param EXME_Frequency2_ID 
		Frequency First Detail ID
	  */
	public void setEXME_Frequency2_ID (int EXME_Frequency2_ID)
	{
		if (EXME_Frequency2_ID < 1)
			 throw new IllegalArgumentException ("EXME_Frequency2_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Frequency2_ID, Integer.valueOf(EXME_Frequency2_ID));
	}

	/** Get Frequency 2.
		@return Frequency First Detail ID
	  */
	public int getEXME_Frequency2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Frequency 3.
		@param EXME_Frequency3_ID 
		Frequency Second Detail ID
	  */
	public void setEXME_Frequency3_ID (int EXME_Frequency3_ID)
	{
		if (EXME_Frequency3_ID < 1)
			 throw new IllegalArgumentException ("EXME_Frequency3_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Frequency3_ID, Integer.valueOf(EXME_Frequency3_ID));
	}

	/** Get Frequency 3.
		@return Frequency Second Detail ID
	  */
	public int getEXME_Frequency3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Frequency3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Hour.
		@param Hour Hour	  */
	public void setHour (Timestamp Hour)
	{
		if (Hour == null)
			throw new IllegalArgumentException ("Hour is mandatory.");
		set_Value (COLUMNNAME_Hour, Hour);
	}

	/** Get Hour.
		@return Hour	  */
	public Timestamp getHour () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Hour);
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