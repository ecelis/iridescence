/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EncounterForms
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_EncounterForms 
{

    /** TableName=EXME_EncounterForms */
    public static final String Table_Name = "EXME_EncounterForms";

    /** AD_Table_ID=1201196 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name Authenticated */
    public static final String COLUMNNAME_Authenticated = "Authenticated";

	/** Set Authenticated	  */
	public void setAuthenticated (boolean Authenticated);

	/** Get Authenticated	  */
	public boolean isAuthenticated();

    /** Column name Authenticated_Date */
    public static final String COLUMNNAME_Authenticated_Date = "Authenticated_Date";

	/** Set Authentication Date	  */
	public void setAuthenticated_Date (Timestamp Authenticated_Date);

	/** Get Authentication Date	  */
	public Timestamp getAuthenticated_Date();

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

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

    /** Column name EXME_EncounterForms_ID */
    public static final String COLUMNNAME_EXME_EncounterForms_ID = "EXME_EncounterForms_ID";

	/** Set Encounter Forms.
	  * Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID);

	/** Get Encounter Forms.
	  * Encounter Forms
	  */
	public int getEXME_EncounterForms_ID();

    /** Column name EXME_FormSectionHeader_ID */
    public static final String COLUMNNAME_EXME_FormSectionHeader_ID = "EXME_FormSectionHeader_ID";

	/** Set Form Section's Header	  */
	public void setEXME_FormSectionHeader_ID (int EXME_FormSectionHeader_ID);

	/** Get Form Section's Header	  */
	public int getEXME_FormSectionHeader_ID();

	public I_EXME_FormSectionHeader getEXME_FormSectionHeader() throws RuntimeException;

    /** Column name IsComplete */
    public static final String COLUMNNAME_IsComplete = "IsComplete";

	/** Set Complete.
	  * It is complete
	  */
	public void setIsComplete (boolean IsComplete);

	/** Get Complete.
	  * It is complete
	  */
	public boolean isComplete();

    /** Column name NotedBy */
    public static final String COLUMNNAME_NotedBy = "NotedBy";

	/** Set Noted By	  */
	public void setNotedBy (int NotedBy);

	/** Get Noted By	  */
	public int getNotedBy();

    /** Column name NotedDate */
    public static final String COLUMNNAME_NotedDate = "NotedDate";

	/** Set Noted Date	  */
	public void setNotedDate (Timestamp NotedDate);

	/** Get Noted Date	  */
	public Timestamp getNotedDate();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name respAD_User_id */
    public static final String COLUMNNAME_respAD_User_id = "respAD_User_id";

	/** Set Responsible User	  */
	public void setrespAD_User_id (int respAD_User_id);

	/** Get Responsible User	  */
	public int getrespAD_User_id();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation
	  */
	public String getType();
}
