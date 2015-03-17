/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for R_RequestProcessor
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_R_RequestProcessor 
{

    /** TableName=R_RequestProcessor */
    public static final String Table_Name = "R_RequestProcessor";

    /** AD_Table_ID=420 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

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

    /** Column name InactivityAlertDays */
    public static final String COLUMNNAME_InactivityAlertDays = "InactivityAlertDays";

	/** Set Inactivity Alert Days.
	  * Send Alert when there is no activity after days (0= no alert)
	  */
	public void setInactivityAlertDays (int InactivityAlertDays);

	/** Get Inactivity Alert Days.
	  * Send Alert when there is no activity after days (0= no alert)
	  */
	public int getInactivityAlertDays();

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

    /** Column name OverdueAlertDays */
    public static final String COLUMNNAME_OverdueAlertDays = "OverdueAlertDays";

	/** Set Alert after Days Due.
	  * Send email alert after number of days due (0=no alerts)
	  */
	public void setOverdueAlertDays (int OverdueAlertDays);

	/** Get Alert after Days Due.
	  * Send email alert after number of days due (0=no alerts)
	  */
	public int getOverdueAlertDays();

    /** Column name OverdueAssignDays */
    public static final String COLUMNNAME_OverdueAssignDays = "OverdueAssignDays";

	/** Set Escalate after Days Due.
	  * Escalation to superior after number of due days (0 = no)
	  */
	public void setOverdueAssignDays (int OverdueAssignDays);

	/** Get Escalate after Days Due.
	  * Escalation to superior after number of due days (0 = no)
	  */
	public int getOverdueAssignDays();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name RemindDays */
    public static final String COLUMNNAME_RemindDays = "RemindDays";

	/** Set Reminder Days.
	  * Days between sending Reminder Emails for a due or inactive Document
	  */
	public void setRemindDays (int RemindDays);

	/** Get Reminder Days.
	  * Days between sending Reminder Emails for a due or inactive Document
	  */
	public int getRemindDays();

    /** Column name R_RequestProcessor_ID */
    public static final String COLUMNNAME_R_RequestProcessor_ID = "R_RequestProcessor_ID";

	/** Set Request Processor.
	  * Processor for Requests
	  */
	public void setR_RequestProcessor_ID (int R_RequestProcessor_ID);

	/** Get Request Processor.
	  * Processor for Requests
	  */
	public int getR_RequestProcessor_ID();

    /** Column name R_RequestType_ID */
    public static final String COLUMNNAME_R_RequestType_ID = "R_RequestType_ID";

	/** Set Request Type.
	  * Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public void setR_RequestType_ID (int R_RequestType_ID);

	/** Get Request Type.
	  * Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public int getR_RequestType_ID();

	public I_R_RequestType getR_RequestType() throws RuntimeException;

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
}
