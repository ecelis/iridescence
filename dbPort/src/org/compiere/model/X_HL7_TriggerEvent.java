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

/** Generated Model for HL7_TriggerEvent
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_HL7_TriggerEvent extends PO implements I_HL7_TriggerEvent, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_HL7_TriggerEvent (Properties ctx, int HL7_TriggerEvent_ID, String trxName)
    {
      super (ctx, HL7_TriggerEvent_ID, trxName);
      /** if (HL7_TriggerEvent_ID == 0)
        {
			setHL7_MessageType_ID (0);
			setHL7_TriggerEvent_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HL7_TriggerEvent (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HL7_TriggerEvent[")
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

	public I_HL7_MessageType getHL7_MessageType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_HL7_MessageType.Table_Name);
        I_HL7_MessageType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_HL7_MessageType)constructor.newInstance(new Object[] {getCtx(), new Integer(getHL7_MessageType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set HL7 Message Type.
		@param HL7_MessageType_ID HL7 Message Type	  */
	public void setHL7_MessageType_ID (int HL7_MessageType_ID)
	{
		if (HL7_MessageType_ID < 1)
			 throw new IllegalArgumentException ("HL7_MessageType_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_MessageType_ID, Integer.valueOf(HL7_MessageType_ID));
	}

	/** Get HL7 Message Type.
		@return HL7 Message Type	  */
	public int getHL7_MessageType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_MessageType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HL7 Trigger Event.
		@param HL7_TriggerEvent_ID HL7 Trigger Event	  */
	public void setHL7_TriggerEvent_ID (int HL7_TriggerEvent_ID)
	{
		if (HL7_TriggerEvent_ID < 1)
			 throw new IllegalArgumentException ("HL7_TriggerEvent_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_HL7_TriggerEvent_ID, Integer.valueOf(HL7_TriggerEvent_ID));
	}

	/** Get HL7 Trigger Event.
		@return HL7 Trigger Event	  */
	public int getHL7_TriggerEvent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HL7_TriggerEvent_ID);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
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