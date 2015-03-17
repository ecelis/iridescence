/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_InterfazProcessor
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_InterfazProcessor 
{

    /** TableName=EXME_InterfazProcessor */
    public static final String Table_Name = "EXME_InterfazProcessor";

    /** AD_Table_ID=1000186 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Client_Target_ID */
    public static final String COLUMNNAME_AD_Client_Target_ID = "AD_Client_Target_ID";

	/** Set Client Target	  */
	public void setAD_Client_Target_ID (int AD_Client_Target_ID);

	/** Get Client Target	  */
	public int getAD_Client_Target_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_Org_Target_ID */
    public static final String COLUMNNAME_AD_Org_Target_ID = "AD_Org_Target_ID";

	/** Set Organization Target.
	  * Organization Target
	  */
	public void setAD_Org_Target_ID (int AD_Org_Target_ID);

	/** Get Organization Target.
	  * Organization Target
	  */
	public int getAD_Org_Target_ID();

    /** Column name DateLastRun */
    public static final String COLUMNNAME_DateLastRun = "DateLastRun";

	/** Set Date last run.
	  * Date the process was last run.
	  */
	public void setDateLastRun (Timestamp DateLastRun);

	/** Get Date last run.
	  * Date the process was last run.
	  */
	public Timestamp getDateLastRun();

    /** Column name DateNextRun */
    public static final String COLUMNNAME_DateNextRun = "DateNextRun";

	/** Set Date next run.
	  * Date the process will run next
	  */
	public void setDateNextRun (Timestamp DateNextRun);

	/** Get Date next run.
	  * Date the process will run next
	  */
	public Timestamp getDateNextRun();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Driver */
    public static final String COLUMNNAME_Driver = "Driver";

	/** Set Driver.
	  * Driver
	  */
	public void setDriver (String Driver);

	/** Get Driver.
	  * Driver
	  */
	public String getDriver();

    /** Column name EXME_InterfazProcessor_ID */
    public static final String COLUMNNAME_EXME_InterfazProcessor_ID = "EXME_InterfazProcessor_ID";

	/** Set Processor Interface	  */
	public void setEXME_InterfazProcessor_ID (int EXME_InterfazProcessor_ID);

	/** Get Processor Interface	  */
	public int getEXME_InterfazProcessor_ID();

    /** Column name Frequency */
    public static final String COLUMNNAME_Frequency = "Frequency";

	/** Set Frequency.
	  * Frequency of events
	  */
	public void setFrequency (int Frequency);

	/** Get Frequency.
	  * Frequency of events
	  */
	public int getFrequency();

    /** Column name FrequencyType */
    public static final String COLUMNNAME_FrequencyType = "FrequencyType";

	/** Set Frequency Type.
	  * Frequency of event
	  */
	public void setFrequencyType (String FrequencyType);

	/** Get Frequency Type.
	  * Frequency of event
	  */
	public String getFrequencyType();

    /** Column name KeepLogDays */
    public static final String COLUMNNAME_KeepLogDays = "KeepLogDays";

	/** Set Days to keep Log.
	  * Number of days to keep the log entries
	  */
	public void setKeepLogDays (int KeepLogDays);

	/** Get Days to keep Log.
	  * Number of days to keep the log entries
	  */
	public int getKeepLogDays();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

    /** Column name Password_I */
    public static final String COLUMNNAME_Password_I = "Password_I";

	/** Set Password	  */
	public void setPassword_I (String Password_I);

	/** Get Password	  */
	public String getPassword_I();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Supervisor_ID */
    public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";

	/** Set Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID);

	/** Get Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID();

    /** Column name URL */
    public static final String COLUMNNAME_URL = "URL";

	/** Set URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL);

	/** Get URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL();

    /** Column name URL_I */
    public static final String COLUMNNAME_URL_I = "URL_I";

	/** Set URL_I	  */
	public void setURL_I (String URL_I);

	/** Get URL_I	  */
	public String getURL_I();

    /** Column name UserName */
    public static final String COLUMNNAME_UserName = "UserName";

	/** Set Registered EMail.
	  * Email of the responsible for the System
	  */
	public void setUserName (String UserName);

	/** Get Registered EMail.
	  * Email of the responsible for the System
	  */
	public String getUserName();

    /** Column name UserName_I */
    public static final String COLUMNNAME_UserName_I = "UserName_I";

	/** Set UserName_I	  */
	public void setUserName_I (String UserName_I);

	/** Get UserName_I	  */
	public String getUserName_I();
}
