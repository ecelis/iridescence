/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_BPartnerEmail
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_BPartnerEmail 
{

    /** TableName=HL7_BPartnerEmail */
    public static final String Table_Name = "HL7_BPartnerEmail";

    /** AD_Table_ID=1200641 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name ContentType */
    public static final String COLUMNNAME_ContentType = "ContentType";

	/** Set Content Type	  */
	public void setContentType (String ContentType);

	/** Get Content Type	  */
	public String getContentType();

    /** Column name HL7_BPartnerEmail_ID */
    public static final String COLUMNNAME_HL7_BPartnerEmail_ID = "HL7_BPartnerEmail_ID";

	/** Set HL7 Business Partner Email.
	  * HL7 Business Partner Email configuration
	  */
	public void setHL7_BPartnerEmail_ID (int HL7_BPartnerEmail_ID);

	/** Get HL7 Business Partner Email.
	  * HL7 Business Partner Email configuration
	  */
	public int getHL7_BPartnerEmail_ID();

    /** Column name HL7_BPartner_ID */
    public static final String COLUMNNAME_HL7_BPartner_ID = "HL7_BPartner_ID";

	/** Set HL7 Business Partner.
	  * Identifies a Business Partner
	  */
	public void setHL7_BPartner_ID (int HL7_BPartner_ID);

	/** Get HL7 Business Partner.
	  * Identifies a Business Partner
	  */
	public int getHL7_BPartner_ID();

	public I_HL7_BPartner getHL7_BPartner() throws RuntimeException;

    /** Column name IsInbound */
    public static final String COLUMNNAME_IsInbound = "IsInbound";

	/** Set Is Inbound	  */
	public void setIsInbound (boolean IsInbound);

	/** Get Is Inbound	  */
	public boolean isInbound();

    /** Column name MailBody */
    public static final String COLUMNNAME_MailBody = "MailBody";

	/** Set Mail Body.
	  * Mail Body
	  */
	public void setMailBody (String MailBody);

	/** Get Mail Body.
	  * Mail Body
	  */
	public String getMailBody();

    /** Column name MailFrom */
    public static final String COLUMNNAME_MailFrom = "MailFrom";

	/** Set Mail From	  */
	public void setMailFrom (String MailFrom);

	/** Get Mail From	  */
	public String getMailFrom();

    /** Column name MailTo */
    public static final String COLUMNNAME_MailTo = "MailTo";

	/** Set Mail To	  */
	public void setMailTo (String MailTo);

	/** Get Mail To	  */
	public String getMailTo();

    /** Column name SecureConnection */
    public static final String COLUMNNAME_SecureConnection = "SecureConnection";

	/** Set Secure Connection	  */
	public void setSecureConnection (String SecureConnection);

	/** Get Secure Connection	  */
	public String getSecureConnection();

    /** Column name SMTPHost */
    public static final String COLUMNNAME_SMTPHost = "SMTPHost";

	/** Set SMTP Server Host.
	  * Hostname of Mail Server for SMTP and IMAP
	  */
	public void setSMTPHost (String SMTPHost);

	/** Get SMTP Server Host.
	  * Hostname of Mail Server for SMTP and IMAP
	  */
	public String getSMTPHost();

    /** Column name SMTPPort */
    public static final String COLUMNNAME_SMTPPort = "SMTPPort";

	/** Set SMTP Server Port.
	  * Simple Mail Transfer Protocol Server Port
	  */
	public void setSMTPPort (int SMTPPort);

	/** Get SMTP Server Port.
	  * Simple Mail Transfer Protocol Server Port
	  */
	public int getSMTPPort();

    /** Column name Subject */
    public static final String COLUMNNAME_Subject = "Subject";

	/** Set Subject.
	  * Email Message Subject
	  */
	public void setSubject (String Subject);

	/** Get Subject.
	  * Email Message Subject
	  */
	public String getSubject();

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

    /** Column name UserPassword */
    public static final String COLUMNNAME_UserPassword = "UserPassword";

	/** Set User Password	  */
	public void setUserPassword (String UserPassword);

	/** Get User Password	  */
	public String getUserPassword();
}
