/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_Dashboard
 *  @author Generated Class 
 *  @version Release @VERSION@
 */
public interface I_HL7_Dashboard 
{

    /** TableName=HL7_Dashboard */
    public static final String Table_Name = "HL7_Dashboard";

    /** AD_Table_ID=1200645 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name GenerateMessage */
    public static final String COLUMNNAME_GenerateMessage = "GenerateMessage";

	/** Set Generate Message	  */
	public void setGenerateMessage (String GenerateMessage);

	/** Get Generate Message	  */
	public String getGenerateMessage();

    /** Column name HL7_Dashboard_ID */
    public static final String COLUMNNAME_HL7_Dashboard_ID = "HL7_Dashboard_ID";

	/** Set HL7 Dashboard	  */
	public void setHL7_Dashboard_ID (int HL7_Dashboard_ID);

	/** Get HL7 Dashboard	  */
	public int getHL7_Dashboard_ID();

    /** Column name HL7_MessageConf_ID */
    public static final String COLUMNNAME_HL7_MessageConf_ID = "HL7_MessageConf_ID";

	/** Set HL7 Message Configuration.
	  * Configuration of message and the process that generate them
	  */
	public void setHL7_MessageConf_ID (int HL7_MessageConf_ID);

	/** Get HL7 Message Configuration.
	  * Configuration of message and the process that generate them
	  */
	public int getHL7_MessageConf_ID();

	public I_HL7_MessageConf getHL7_MessageConf() throws RuntimeException;

    /** Column name Message */
    public static final String COLUMNNAME_Message = "Message";

	/** Set Message.
	  * EMail Message
	  */
	public void setMessage (String Message);

	/** Get Message.
	  * EMail Message
	  */
	public String getMessage();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name SendMessage */
    public static final String COLUMNNAME_SendMessage = "SendMessage";

	/** Set Send Message.
	  * Send Message
	  */
	public void setSendMessage (String SendMessage);

	/** Get Send Message.
	  * Send Message
	  */
	public String getSendMessage();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();
}
