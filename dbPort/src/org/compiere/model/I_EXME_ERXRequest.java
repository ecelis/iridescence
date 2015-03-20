/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ERXRequest
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ERXRequest 
{

    /** TableName=EXME_ERXRequest */
    public static final String Table_Name = "EXME_ERXRequest";

    /** AD_Table_ID=1201259 */
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

    /** Column name EXME_ERXRequest_ID */
    public static final String COLUMNNAME_EXME_ERXRequest_ID = "EXME_ERXRequest_ID";

	/** Set ePrescribing Request Message	  */
	public void setEXME_ERXRequest_ID (int EXME_ERXRequest_ID);

	/** Get ePrescribing Request Message	  */
	public int getEXME_ERXRequest_ID();

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_PrescRXDet_ID */
    public static final String COLUMNNAME_EXME_PrescRXDet_ID = "EXME_PrescRXDet_ID";

	/** Set RXNorm Prescription Detail.
	  * RXNorm Prescription Detail
	  */
	public void setEXME_PrescRXDet_ID (int EXME_PrescRXDet_ID);

	/** Get RXNorm Prescription Detail.
	  * RXNorm Prescription Detail
	  */
	public int getEXME_PrescRXDet_ID();

	public I_EXME_PrescRXDet getEXME_PrescRXDet() throws RuntimeException;

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

    /** Column name Notes */
    public static final String COLUMNNAME_Notes = "Notes";

	/** Set Notes	  */
	public void setNotes (String Notes);

	/** Get Notes	  */
	public String getNotes();

    /** Column name ReasonCode */
    public static final String COLUMNNAME_ReasonCode = "ReasonCode";

	/** Set Reason Code	  */
	public void setReasonCode (String ReasonCode);

	/** Get Reason Code	  */
	public String getReasonCode();

    /** Column name RefillDisp */
    public static final String COLUMNNAME_RefillDisp = "RefillDisp";

	/** Set RefillDisp	  */
	public void setRefillDisp (int RefillDisp);

	/** Get RefillDisp	  */
	public int getRefillDisp();

    /** Column name RefillPresc */
    public static final String COLUMNNAME_RefillPresc = "RefillPresc";

	/** Set RefillPresc	  */
	public void setRefillPresc (int RefillPresc);

	/** Get RefillPresc	  */
	public int getRefillPresc();

    /** Column name RequestType */
    public static final String COLUMNNAME_RequestType = "RequestType";

	/** Set Request Type	  */
	public void setRequestType (String RequestType);

	/** Get Request Type	  */
	public String getRequestType();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
