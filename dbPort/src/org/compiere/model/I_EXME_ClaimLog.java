/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ClaimLog
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ClaimLog 
{

    /** TableName=EXME_ClaimLog */
    public static final String Table_Name = "EXME_ClaimLog";

    /** AD_Table_ID=1201218 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Attachment_ID */
    public static final String COLUMNNAME_AD_Attachment_ID = "AD_Attachment_ID";

	/** Set Attachment.
	  * Attachment for the document
	  */
	public void setAD_Attachment_ID (int AD_Attachment_ID);

	/** Get Attachment.
	  * Attachment for the document
	  */
	public int getAD_Attachment_ID();

	public I_AD_Attachment getAD_Attachment() throws RuntimeException;

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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name ConfType */
    public static final String COLUMNNAME_ConfType = "ConfType";

	/** Set Configuration Type	  */
	public void setConfType (String ConfType);

	/** Get Configuration Type	  */
	public String getConfType();

    /** Column name Date_Submitted */
    public static final String COLUMNNAME_Date_Submitted = "Date_Submitted";

	/** Set Date Submitted.
	  * Date the claim message was submitted
	  */
	public void setDate_Submitted (Timestamp Date_Submitted);

	/** Get Date Submitted.
	  * Date the claim message was submitted
	  */
	public Timestamp getDate_Submitted();

    /** Column name EXME_ClaimLog_ID */
    public static final String COLUMNNAME_EXME_ClaimLog_ID = "EXME_ClaimLog_ID";

	/** Set EXME_CLAIMLOG_ID	  */
	public void setEXME_ClaimLog_ID (int EXME_ClaimLog_ID);

	/** Get EXME_CLAIMLOG_ID	  */
	public int getEXME_ClaimLog_ID();

    /** Column name EXME_CtaPacExt_ID */
    public static final String COLUMNNAME_EXME_CtaPacExt_ID = "EXME_CtaPacExt_ID";

	/** Set Encounter Extension.
	  * Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID);

	/** Get Encounter Extension.
	  * Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID();

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException;

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name HL7_Dashboard_ID */
    public static final String COLUMNNAME_HL7_Dashboard_ID = "HL7_Dashboard_ID";

	/** Set HL7 Dashboard	  */
	public void setHL7_Dashboard_ID (int HL7_Dashboard_ID);

	/** Get HL7 Dashboard	  */
	public int getHL7_Dashboard_ID();

	public I_HL7_Dashboard getHL7_Dashboard() throws RuntimeException;

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
