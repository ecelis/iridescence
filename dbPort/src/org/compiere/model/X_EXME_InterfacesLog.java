/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_InterfacesLog
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_InterfacesLog extends PO implements I_EXME_InterfacesLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_InterfacesLog (Properties ctx, int EXME_InterfacesLog_ID, String trxName)
    {
      super (ctx, EXME_InterfacesLog_ID, trxName);
      /** if (EXME_InterfacesLog_ID == 0)
        {
			setEXME_INTERFACESLOG_ID (0);
			setFileName (null);
			setInterfase (null);
			setMessage (null);
			setOperation (null);
			setProcessingDate (new Timestamp( System.currentTimeMillis() ));
			setReferenceNo (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_InterfacesLog (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_InterfacesLog[")
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

	/** Set Interface Log.
		@param EXME_INTERFACESLOG_ID Interface Log	  */
	public void setEXME_INTERFACESLOG_ID (int EXME_INTERFACESLOG_ID)
	{
		if (EXME_INTERFACESLOG_ID < 1)
			 throw new IllegalArgumentException ("EXME_INTERFACESLOG_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_INTERFACESLOG_ID, Integer.valueOf(EXME_INTERFACESLOG_ID));
	}

	/** Get Interface Log.
		@return Interface Log	  */
	public int getEXME_INTERFACESLOG_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_INTERFACESLOG_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		if (FileName == null)
			throw new IllegalArgumentException ("FileName is mandatory.");
		set_Value (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}

	/** Set Interface.
		@param Interfase Interface	  */
	public void setInterfase (String Interfase)
	{
		if (Interfase == null)
			throw new IllegalArgumentException ("Interfase is mandatory.");
		set_Value (COLUMNNAME_Interfase, Interfase);
	}

	/** Get Interface.
		@return Interface	  */
	public String getInterfase () 
	{
		return (String)get_Value(COLUMNNAME_Interfase);
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

	/** Set Operation.
		@param Operation 
		Compare Operation
	  */
	public void setOperation (String Operation)
	{
		if (Operation == null)
			throw new IllegalArgumentException ("Operation is mandatory.");
		set_Value (COLUMNNAME_Operation, Operation);
	}

	/** Get Operation.
		@return Compare Operation
	  */
	public String getOperation () 
	{
		return (String)get_Value(COLUMNNAME_Operation);
	}

	/** Set Processing date.
		@param ProcessingDate Processing date	  */
	public void setProcessingDate (Timestamp ProcessingDate)
	{
		if (ProcessingDate == null)
			throw new IllegalArgumentException ("ProcessingDate is mandatory.");
		set_Value (COLUMNNAME_ProcessingDate, ProcessingDate);
	}

	/** Get Processing date.
		@return Processing date	  */
	public Timestamp getProcessingDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ProcessingDate);
	}

	/** Set Reference No.
		@param ReferenceNo 
		Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo)
	{
		if (ReferenceNo == null)
			throw new IllegalArgumentException ("ReferenceNo is mandatory.");
		set_Value (COLUMNNAME_ReferenceNo, ReferenceNo);
	}

	/** Get Reference No.
		@return Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo () 
	{
		return (String)get_Value(COLUMNNAME_ReferenceNo);
	}
}