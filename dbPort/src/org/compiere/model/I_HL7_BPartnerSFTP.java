/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HL7_BPartnerSFTP
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_HL7_BPartnerSFTP 
{

    /** TableName=HL7_BPartnerSFTP */
    public static final String Table_Name = "HL7_BPartnerSFTP";

    /** AD_Table_ID=1200643 */
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

    /** Column name AppendToFile */
    public static final String COLUMNNAME_AppendToFile = "AppendToFile";

	/** Set Append To File	  */
	public void setAppendToFile (String AppendToFile);

	/** Get Append To File	  */
	public String getAppendToFile();

    /** Column name Directory */
    public static final String COLUMNNAME_Directory = "Directory";

	/** Set Directory	  */
	public void setDirectory (String Directory);

	/** Get Directory	  */
	public String getDirectory();

    /** Column name Encoding */
    public static final String COLUMNNAME_Encoding = "Encoding";

	/** Set Encoding.
	  * Messaage encoding
	  */
	public void setEncoding (String Encoding);

	/** Get Encoding.
	  * Messaage encoding
	  */
	public String getEncoding();

    /** Column name FileName */
    public static final String COLUMNNAME_FileName = "FileName";

	/** Set File Name.
	  * Name of the local file or URL
	  */
	public void setFileName (String FileName);

	/** Get File Name.
	  * Name of the local file or URL
	  */
	public String getFileName();

    /** Column name FileType */
    public static final String COLUMNNAME_FileType = "FileType";

	/** Set File Type	  */
	public void setFileType (String FileType);

	/** Get File Type	  */
	public String getFileType();

    /** Column name Filter */
    public static final String COLUMNNAME_Filter = "Filter";

	/** Set Filter Name Pattern.
	  * Filter Name Pattern
	  */
	public void setFilter (String Filter);

	/** Get Filter Name Pattern.
	  * Filter Name Pattern
	  */
	public String getFilter();

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

    /** Column name HL7_BPartnerSFTP_ID */
    public static final String COLUMNNAME_HL7_BPartnerSFTP_ID = "HL7_BPartnerSFTP_ID";

	/** Set HL7 Business Partner SFTP	  */
	public void setHL7_BPartnerSFTP_ID (int HL7_BPartnerSFTP_ID);

	/** Get HL7 Business Partner SFTP	  */
	public int getHL7_BPartnerSFTP_ID();

    /** Column name IsCheckAge */
    public static final String COLUMNNAME_IsCheckAge = "IsCheckAge";

	/** Set Check File Age	  */
	public void setIsCheckAge (String IsCheckAge);

	/** Get Check File Age	  */
	public String getIsCheckAge();

    /** Column name IsDeleteAfterRead */
    public static final String COLUMNNAME_IsDeleteAfterRead = "IsDeleteAfterRead";

	/** Set Delete File After Read	  */
	public void setIsDeleteAfterRead (String IsDeleteAfterRead);

	/** Get Delete File After Read	  */
	public String getIsDeleteAfterRead();

    /** Column name IsInbound */
    public static final String COLUMNNAME_IsInbound = "IsInbound";

	/** Set Is Inbound	  */
	public void setIsInbound (boolean IsInbound);

	/** Get Is Inbound	  */
	public boolean isInbound();

    /** Column name MoveErrorToFile */
    public static final String COLUMNNAME_MoveErrorToFile = "MoveErrorToFile";

	/** Set On Error Move to	  */
	public void setMoveErrorToFile (String MoveErrorToFile);

	/** Get On Error Move to	  */
	public String getMoveErrorToFile();

    /** Column name MoveToDir */
    public static final String COLUMNNAME_MoveToDir = "MoveToDir";

	/** Set Move to directory	  */
	public void setMoveToDir (String MoveToDir);

	/** Get Move to directory	  */
	public String getMoveToDir();

    /** Column name MoveToFile */
    public static final String COLUMNNAME_MoveToFile = "MoveToFile";

	/** Set Move File to	  */
	public void setMoveToFile (String MoveToFile);

	/** Get Move File to	  */
	public String getMoveToFile();

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

    /** Column name PollingFreq */
    public static final String COLUMNNAME_PollingFreq = "PollingFreq";

	/** Set Polling Frequency	  */
	public void setPollingFreq (int PollingFreq);

	/** Get Polling Frequency	  */
	public int getPollingFreq();

    /** Column name PollingType */
    public static final String COLUMNNAME_PollingType = "PollingType";

	/** Set Polling Type	  */
	public void setPollingType (String PollingType);

	/** Get Polling Type	  */
	public String getPollingType();

    /** Column name SFTPHost */
    public static final String COLUMNNAME_SFTPHost = "SFTPHost";

	/** Set SFTP Host	  */
	public void setSFTPHost (String SFTPHost);

	/** Get SFTP Host	  */
	public String getSFTPHost();

    /** Column name Template */
    public static final String COLUMNNAME_Template = "Template";

	/** Set Template.
	  * Message Template
	  */
	public void setTemplate (String Template);

	/** Get Template.
	  * Message Template
	  */
	public String getTemplate();

    /** Column name TestWrite */
    public static final String COLUMNNAME_TestWrite = "TestWrite";

	/** Set Test Write.
	  * Test the configuration
	  */
	public void setTestWrite (String TestWrite);

	/** Get Test Write.
	  * Test the configuration
	  */
	public String getTestWrite();

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
}
