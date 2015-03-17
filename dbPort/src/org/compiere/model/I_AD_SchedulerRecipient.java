/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_SchedulerRecipient
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_SchedulerRecipient 
{

    /** TableName=AD_SchedulerRecipient */
    public static final String Table_Name = "AD_SchedulerRecipient";

    /** AD_Table_ID=704 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name AD_Role_ID */
    public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

	/** Set Role.
	  * Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID);

	/** Get Role.
	  * Responsibility Role
	  */
	public int getAD_Role_ID();

	public I_AD_Role getAD_Role() throws RuntimeException;

    /** Column name AD_Scheduler_ID */
    public static final String COLUMNNAME_AD_Scheduler_ID = "AD_Scheduler_ID";

	/** Set Scheduler.
	  * Schedule Processes
	  */
	public void setAD_Scheduler_ID (int AD_Scheduler_ID);

	/** Get Scheduler.
	  * Schedule Processes
	  */
	public int getAD_Scheduler_ID();

	public I_AD_Scheduler getAD_Scheduler() throws RuntimeException;

    /** Column name AD_SchedulerRecipient_ID */
    public static final String COLUMNNAME_AD_SchedulerRecipient_ID = "AD_SchedulerRecipient_ID";

	/** Set Scheduler Recipient.
	  * Recipient of the Scheduler Notification
	  */
	public void setAD_SchedulerRecipient_ID (int AD_SchedulerRecipient_ID);

	/** Get Scheduler Recipient.
	  * Recipient of the Scheduler Notification
	  */
	public int getAD_SchedulerRecipient_ID();

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;
}
