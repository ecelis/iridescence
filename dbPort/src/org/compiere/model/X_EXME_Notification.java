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

/** Generated Model for EXME_Notification
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Notification extends PO implements I_EXME_Notification, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Notification (Properties ctx, int EXME_Notification_ID, String trxName)
    {
      super (ctx, EXME_Notification_ID, trxName);
      /** if (EXME_Notification_ID == 0)
        {
			setComments (null);
			setEXME_Notification_ID (0);
			setFechaVencimiento (new Timestamp( System.currentTimeMillis() ));
			setTitle (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Notification (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Notification[")
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

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		if (Comments == null)
			throw new IllegalArgumentException ("Comments is mandatory.");
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Notification.
		@param EXME_Notification_ID 
		Notification
	  */
	public void setEXME_Notification_ID (int EXME_Notification_ID)
	{
		if (EXME_Notification_ID < 1)
			 throw new IllegalArgumentException ("EXME_Notification_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Notification_ID, Integer.valueOf(EXME_Notification_ID));
	}

	/** Get Notification.
		@return Notification
	  */
	public int getEXME_Notification_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Notification_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Termination Date.
		@param FechaVencimiento Termination Date	  */
	public void setFechaVencimiento (Timestamp FechaVencimiento)
	{
		if (FechaVencimiento == null)
			throw new IllegalArgumentException ("FechaVencimiento is mandatory.");
		set_Value (COLUMNNAME_FechaVencimiento, FechaVencimiento);
	}

	/** Get Termination Date.
		@return Termination Date	  */
	public Timestamp getFechaVencimiento () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaVencimiento);
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

	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{
		if (Title == null)
			throw new IllegalArgumentException ("Title is mandatory.");
		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
	}
}