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

/** Generated Model for EXME_BlockTime
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_BlockTime extends PO implements I_EXME_BlockTime, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_BlockTime (Properties ctx, int EXME_BlockTime_ID, String trxName)
    {
      super (ctx, EXME_BlockTime_ID, trxName);
      /** if (EXME_BlockTime_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_EXME_BlockTime (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_BlockTime[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
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

	/** Set BlockTime.
		@param EXME_BlockTime_ID BlockTime	  */
	public void setEXME_BlockTime_ID (int EXME_BlockTime_ID)
	{
		if (EXME_BlockTime_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_EXME_BlockTime_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_EXME_BlockTime_ID, Integer.valueOf(EXME_BlockTime_ID));
	}

	/** Get BlockTime.
		@return BlockTime	  */
	public int getEXME_BlockTime_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BlockTime_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Finish Hr and Date.
		@param FechaHrFin Finish Hr and Date	  */
	public void setFechaHrFin (Timestamp FechaHrFin)
	{
		set_Value (COLUMNNAME_FechaHrFin, FechaHrFin);
	}

	/** Get Finish Hr and Date.
		@return Finish Hr and Date	  */
	public Timestamp getFechaHrFin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaHrFin);
	}

	/** Set Initial Hr and Date.
		@param FechaHrIni Initial Hr and Date	  */
	public void setFechaHrIni (Timestamp FechaHrIni)
	{
		set_Value (COLUMNNAME_FechaHrIni, FechaHrIni);
	}

	/** Get Initial Hr and Date.
		@return Initial Hr and Date	  */
	public Timestamp getFechaHrIni () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaHrIni);
	}

	/** Set Daily.
		@param IsDaily Daily	  */
	public void setIsDaily (boolean IsDaily)
	{
		set_Value (COLUMNNAME_IsDaily, Boolean.valueOf(IsDaily));
	}

	/** Get Daily.
		@return Daily	  */
	public boolean isDaily () 
	{
		Object oo = get_Value(COLUMNNAME_IsDaily);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Repeat.
		@param IsRepeat 
		Repeat
	  */
	public void setIsRepeat (boolean IsRepeat)
	{
		set_Value (COLUMNNAME_IsRepeat, Boolean.valueOf(IsRepeat));
	}

	/** Get Repeat.
		@return Repeat
	  */
	public boolean isRepeat () 
	{
		Object oo = get_Value(COLUMNNAME_IsRepeat);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Weekly.
		@param IsWeekly Weekly	  */
	public void setIsWeekly (boolean IsWeekly)
	{
		set_Value (COLUMNNAME_IsWeekly, Boolean.valueOf(IsWeekly));
	}

	/** Get Weekly.
		@return Weekly	  */
	public boolean isWeekly () 
	{
		Object oo = get_Value(COLUMNNAME_IsWeekly);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 1) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
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

	/** Set Universally Unique Identifier.
		@param UUID Universally Unique Identifier	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Universally Unique Identifier.
		@return Universally Unique Identifier	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}