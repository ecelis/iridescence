/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_I_Tlm
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_I_Tlm extends PO implements I_EXME_I_Tlm, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_I_Tlm (Properties ctx, int EXME_I_Tlm_ID, String trxName)
    {
      super (ctx, EXME_I_Tlm_ID, trxName);
      /** if (EXME_I_Tlm_ID == 0)
        {
			setEXME_I_TLM_ID (0);
			setFileName (null);
			setInterfase (null);
			setMessage (null);
			setProcessed (false);
			setProcessingDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_I_Tlm (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_I_Tlm[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set EXME_I_TLM_ID.
		@param EXME_I_TLM_ID 
		eCareSoft Interface Output Table
	  */
	public void setEXME_I_TLM_ID (int EXME_I_TLM_ID)
	{
		if (EXME_I_TLM_ID < 1)
			 throw new IllegalArgumentException ("EXME_I_TLM_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_I_TLM_ID, Integer.valueOf(EXME_I_TLM_ID));
	}

	/** Get EXME_I_TLM_ID.
		@return eCareSoft Interface Output Table
	  */
	public int getEXME_I_TLM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_I_TLM_ID);
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

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
}