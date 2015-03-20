/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Conversation
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Conversation extends PO implements I_EXME_Conversation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Conversation (Properties ctx, int EXME_Conversation_ID, String trxName)
    {
      super (ctx, EXME_Conversation_ID, trxName);
      /** if (EXME_Conversation_ID == 0)
        {
			setAD_User_ID (0);
			setEXME_Conversation_ID (0);
			setLocked (false);
			setTitle (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Conversation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Conversation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User/Contact .
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact .
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Conversation.
		@param EXME_Conversation_ID Conversation	  */
	public void setEXME_Conversation_ID (int EXME_Conversation_ID)
	{
		if (EXME_Conversation_ID < 1)
			 throw new IllegalArgumentException ("EXME_Conversation_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Conversation_ID, Integer.valueOf(EXME_Conversation_ID));
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

	/** Set Locked.
		@param Locked 
		Whether the terminal is locked
	  */
	public void setLocked (boolean Locked)
	{
		set_Value (COLUMNNAME_Locked, Boolean.valueOf(Locked));
	}

	/** Get Locked.
		@return Whether the terminal is locked
	  */
	public boolean isLocked () 
	{
		Object oo = get_Value(COLUMNNAME_Locked);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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