/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_BatchClaimH
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_BatchClaimH 
{

    /** TableName=EXME_BatchClaimH */
    public static final String Table_Name = "EXME_BatchClaimH";

    /** AD_Table_ID=1201172 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AckMsg */
    public static final String COLUMNNAME_AckMsg = "AckMsg";

	/** Set AckMsg	  */
	public void setAckMsg (String AckMsg);

	/** Get AckMsg	  */
	public String getAckMsg();

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

    /** Column name ConfType */
    public static final String COLUMNNAME_ConfType = "ConfType";

	/** Set Configuration Type	  */
	public void setConfType (String ConfType);

	/** Get Configuration Type	  */
	public String getConfType();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * End Date of Claim Report
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * End Date of Claim Report
	  */
	public Timestamp getEndDate();

    /** Column name EXME_BatchClaimH_ID */
    public static final String COLUMNNAME_EXME_BatchClaimH_ID = "EXME_BatchClaimH_ID";

	/** Set EXME_BatchClaimH_ID	  */
	public void setEXME_BatchClaimH_ID (int EXME_BatchClaimH_ID);

	/** Get EXME_BatchClaimH_ID	  */
	public int getEXME_BatchClaimH_ID();

    /** Column name HL7_Dashboard_ID */
    public static final String COLUMNNAME_HL7_Dashboard_ID = "HL7_Dashboard_ID";

	/** Set HL7 Dashboard	  */
	public void setHL7_Dashboard_ID (int HL7_Dashboard_ID);

	/** Get HL7 Dashboard	  */
	public int getHL7_Dashboard_ID();

	public I_HL7_Dashboard getHL7_Dashboard() throws RuntimeException;

    /** Column name IsGenerated */
    public static final String COLUMNNAME_IsGenerated = "IsGenerated";

	/** Set Generated.
	  * This Line is generated
	  */
	public void setIsGenerated (boolean IsGenerated);

	/** Get Generated.
	  * This Line is generated
	  */
	public boolean isGenerated();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * Start Date of Claim Report
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * Start Date of Claim Report
	  */
	public Timestamp getStartDate();

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
