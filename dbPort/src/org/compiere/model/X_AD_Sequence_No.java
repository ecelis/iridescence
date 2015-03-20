/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for AD_Sequence_No
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_AD_Sequence_No extends PO implements I_AD_Sequence_No, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_AD_Sequence_No (Properties ctx, int AD_Sequence_No_ID, String trxName)
    {
      super (ctx, AD_Sequence_No_ID, trxName);
      /** if (AD_Sequence_No_ID == 0)
        {
			setAD_Sequence_ID (0);
			setCalendarYear (null);
			setCurrentNext (0);
			setYear (null);
        } */
    }

    /** Load Constructor */
    public X_AD_Sequence_No (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_Sequence_No[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Sequence getAD_Sequence() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Sequence.Table_Name);
        I_AD_Sequence result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Sequence)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Sequence_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Sequence.
		@param AD_Sequence_ID 
		Document Sequence
	  */
	public void setAD_Sequence_ID (int AD_Sequence_ID)
	{
		if (AD_Sequence_ID < 1)
			 throw new IllegalArgumentException ("AD_Sequence_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_Sequence_ID, Integer.valueOf(AD_Sequence_ID));
	}

	/** Get Sequence.
		@return Document Sequence
	  */
	public int getAD_Sequence_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Sequence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Year.
		@param CalendarYear 
		Calendar Year
	  */
	public void setCalendarYear (String CalendarYear)
	{
		if (CalendarYear == null)
			throw new IllegalArgumentException ("CalendarYear is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CalendarYear, CalendarYear);
	}

	/** Get Year.
		@return Calendar Year
	  */
	public String getCalendarYear () 
	{
		return (String)get_Value(COLUMNNAME_CalendarYear);
	}

	/** Set Current Next.
		@param CurrentNext 
		The next number to be used
	  */
	public void setCurrentNext (int CurrentNext)
	{
		set_Value (COLUMNNAME_CurrentNext, Integer.valueOf(CurrentNext));
	}

	/** Get Current Next.
		@return The next number to be used
	  */
	public int getCurrentNext () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CurrentNext);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Year.
		@param Year 
		Calendar Year
	  */
	public void setYear (String Year)
	{
		if (Year == null)
			throw new IllegalArgumentException ("Year is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Year, Year);
	}

	/** Get Year.
		@return Calendar Year
	  */
	public String getYear () 
	{
		return (String)get_Value(COLUMNNAME_Year);
	}
}