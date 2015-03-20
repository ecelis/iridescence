/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Message
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Message extends PO implements I_EXME_Message, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Message (Properties ctx, int EXME_Message_ID, String trxName)
    {
      super (ctx, EXME_Message_ID, trxName);
      /** if (EXME_Message_ID == 0)
        {
			setEXME_Conversation_ID (0);
			setEXME_Message_ID (0);
			setMessage (null);
			setValue (null);
			setViewed (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_Message (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Message[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Conversation.
		@param EXME_Conversation_ID Conversation	  */
	public void setEXME_Conversation_ID (int EXME_Conversation_ID)
	{
		if (EXME_Conversation_ID < 1)
			 throw new IllegalArgumentException ("EXME_Conversation_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Conversation_ID, Integer.valueOf(EXME_Conversation_ID));
	}

	/** Get Conversation.
		@return Conversation	  */
	public int getEXME_Conversation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Conversation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Message.
		@param EXME_Message_ID Message	  */
	public void setEXME_Message_ID (int EXME_Message_ID)
	{
		if (EXME_Message_ID < 1)
			 throw new IllegalArgumentException ("EXME_Message_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Message_ID, Integer.valueOf(EXME_Message_ID));
	}

	/** Get Message.
		@return Message	  */
	public int getEXME_Message_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Message_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Message.
		@param Message 
		EMail Message
	  */
	public void setMessage (String Message)
	{
		if (Message == null)
			throw new IllegalArgumentException ("Message is mandatory.");
		set_Value (COLUMNNAME_Message, Message);
	}

	/** Get Message.
		@return EMail Message
	  */
	public String getMessage () 
	{
		return (String)get_Value(COLUMNNAME_Message);
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

	/** Set Viewed.
		@param Viewed Viewed	  */
	public void setViewed (boolean Viewed)
	{
		set_Value (COLUMNNAME_Viewed, Boolean.valueOf(Viewed));
	}

	/** Get Viewed.
		@return Viewed	  */
	public boolean isViewed () 
	{
		Object oo = get_Value(COLUMNNAME_Viewed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}