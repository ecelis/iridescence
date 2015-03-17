/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_InterfazProcessor
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_InterfazProcessor extends PO implements I_EXME_InterfazProcessor, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_InterfazProcessor (Properties ctx, int EXME_InterfazProcessor_ID, String trxName)
    {
      super (ctx, EXME_InterfazProcessor_ID, trxName);
      /** if (EXME_InterfazProcessor_ID == 0)
        {
			setAD_Client_Target_ID (0);
			setAD_Org_Target_ID (0);
			setEXME_InterfazProcessor_ID (0);
			setFrequency (0);
			setFrequencyType (null);
			setKeepLogDays (0);
			setName (null);
			setSupervisor_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_InterfazProcessor (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_InterfazProcessor[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Client Target.
		@param AD_Client_Target_ID Client Target	  */
	public void setAD_Client_Target_ID (int AD_Client_Target_ID)
	{
		if (AD_Client_Target_ID < 1)
			 throw new IllegalArgumentException ("AD_Client_Target_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Client_Target_ID, Integer.valueOf(AD_Client_Target_ID));
	}

	/** Get Client Target.
		@return Client Target	  */
	public int getAD_Client_Target_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Client_Target_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Organization Target.
		@param AD_Org_Target_ID 
		Organization Target
	  */
	public void setAD_Org_Target_ID (int AD_Org_Target_ID)
	{
		if (AD_Org_Target_ID < 1)
			 throw new IllegalArgumentException ("AD_Org_Target_ID is mandatory.");
		set_Value (COLUMNNAME_AD_Org_Target_ID, Integer.valueOf(AD_Org_Target_ID));
	}

	/** Get Organization Target.
		@return Organization Target
	  */
	public int getAD_Org_Target_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Org_Target_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date last run.
		@param DateLastRun 
		Date the process was last run.
	  */
	public void setDateLastRun (Timestamp DateLastRun)
	{
		set_ValueNoCheck (COLUMNNAME_DateLastRun, DateLastRun);
	}

	/** Get Date last run.
		@return Date the process was last run.
	  */
	public Timestamp getDateLastRun () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateLastRun);
	}

	/** Set Date next run.
		@param DateNextRun 
		Date the process will run next
	  */
	public void setDateNextRun (Timestamp DateNextRun)
	{
		set_ValueNoCheck (COLUMNNAME_DateNextRun, DateNextRun);
	}

	/** Get Date next run.
		@return Date the process will run next
	  */
	public Timestamp getDateNextRun () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateNextRun);
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

	/** Set Driver.
		@param Driver 
		Driver
	  */
	public void setDriver (String Driver)
	{
		set_Value (COLUMNNAME_Driver, Driver);
	}

	/** Get Driver.
		@return Driver
	  */
	public String getDriver () 
	{
		return (String)get_Value(COLUMNNAME_Driver);
	}

	/** Set Processor Interface.
		@param EXME_InterfazProcessor_ID Processor Interface	  */
	public void setEXME_InterfazProcessor_ID (int EXME_InterfazProcessor_ID)
	{
		if (EXME_InterfazProcessor_ID < 1)
			 throw new IllegalArgumentException ("EXME_InterfazProcessor_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_InterfazProcessor_ID, Integer.valueOf(EXME_InterfazProcessor_ID));
	}

	/** Get Processor Interface.
		@return Processor Interface	  */
	public int getEXME_InterfazProcessor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_InterfazProcessor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Frequency.
		@param Frequency 
		Frequency of events
	  */
	public void setFrequency (int Frequency)
	{
		set_Value (COLUMNNAME_Frequency, Integer.valueOf(Frequency));
	}

	/** Get Frequency.
		@return Frequency of events
	  */
	public int getFrequency () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Frequency);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** FrequencyType AD_Reference_ID=221 */
	public static final int FREQUENCYTYPE_AD_Reference_ID=221;
	/** Minute = M */
	public static final String FREQUENCYTYPE_Minute = "M";
	/** Hour = H */
	public static final String FREQUENCYTYPE_Hour = "H";
	/** Day = D */
	public static final String FREQUENCYTYPE_Day = "D";
	/** Set Frequency Type.
		@param FrequencyType 
		Frequency of event
	  */
	public void setFrequencyType (String FrequencyType)
	{
		if (FrequencyType == null) throw new IllegalArgumentException ("FrequencyType is mandatory");
		if (FrequencyType.equals("M") || FrequencyType.equals("H") || FrequencyType.equals("D")); else throw new IllegalArgumentException ("FrequencyType Invalid value - " + FrequencyType + " - Reference_ID=221 - M - H - D");		set_Value (COLUMNNAME_FrequencyType, FrequencyType);
	}

	/** Get Frequency Type.
		@return Frequency of event
	  */
	public String getFrequencyType () 
	{
		return (String)get_Value(COLUMNNAME_FrequencyType);
	}

	/** Set Days to keep Log.
		@param KeepLogDays 
		Number of days to keep the log entries
	  */
	public void setKeepLogDays (int KeepLogDays)
	{
		set_Value (COLUMNNAME_KeepLogDays, Integer.valueOf(KeepLogDays));
	}

	/** Get Days to keep Log.
		@return Number of days to keep the log entries
	  */
	public int getKeepLogDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_KeepLogDays);
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
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
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

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
	}

	/** Set Password.
		@param Password_I Password	  */
	public void setPassword_I (String Password_I)
	{
		set_Value (COLUMNNAME_Password_I, Password_I);
	}

	/** Get Password.
		@return Password	  */
	public String getPassword_I () 
	{
		return (String)get_Value(COLUMNNAME_Password_I);
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Supervisor.
		@param Supervisor_ID 
		Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID)
	{
		if (Supervisor_ID < 1)
			 throw new IllegalArgumentException ("Supervisor_ID is mandatory.");
		set_Value (COLUMNNAME_Supervisor_ID, Integer.valueOf(Supervisor_ID));
	}

	/** Get Supervisor.
		@return Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Supervisor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL)
	{
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
	}

	/** Set URL_I.
		@param URL_I URL_I	  */
	public void setURL_I (String URL_I)
	{
		set_Value (COLUMNNAME_URL_I, URL_I);
	}

	/** Get URL_I.
		@return URL_I	  */
	public String getURL_I () 
	{
		return (String)get_Value(COLUMNNAME_URL_I);
	}

	/** Set Registered EMail.
		@param UserName 
		Email of the responsible for the System
	  */
	public void setUserName (String UserName)
	{
		set_Value (COLUMNNAME_UserName, UserName);
	}

	/** Get Registered EMail.
		@return Email of the responsible for the System
	  */
	public String getUserName () 
	{
		return (String)get_Value(COLUMNNAME_UserName);
	}

	/** Set UserName_I.
		@param UserName_I UserName_I	  */
	public void setUserName_I (String UserName_I)
	{
		set_Value (COLUMNNAME_UserName_I, UserName_I);
	}

	/** Get UserName_I.
		@return UserName_I	  */
	public String getUserName_I () 
	{
		return (String)get_Value(COLUMNNAME_UserName_I);
	}
}