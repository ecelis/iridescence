/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ClaimPayment_View
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ClaimPayment_View 
{

    /** TableName=EXME_ClaimPayment_View */
    public static final String Table_Name = "EXME_ClaimPayment_View";

    /** AD_Table_ID=1201269 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name Claim_Type */
    public static final String COLUMNNAME_Claim_Type = "Claim_Type";

	/** Set Claim_Type	  */
	public void setClaim_Type (String Claim_Type);

	/** Get Claim_Type	  */
	public String getClaim_Type();

    /** Column name Date_Created */
    public static final String COLUMNNAME_Date_Created = "Date_Created";

	/** Set Date_Created	  */
	public void setDate_Created (Timestamp Date_Created);

	/** Get Date_Created	  */
	public Timestamp getDate_Created();

    /** Column name Date_Received */
    public static final String COLUMNNAME_Date_Received = "Date_Received";

	/** Set Date_Received	  */
	public void setDate_Received (Timestamp Date_Received);

	/** Get Date_Received	  */
	public Timestamp getDate_Received();

    /** Column name DateWorkStart */
    public static final String COLUMNNAME_DateWorkStart = "DateWorkStart";

	/** Set Work Start.
	  * Date when work is (planned to be) started
	  */
	public void setDateWorkStart (Timestamp DateWorkStart);

	/** Get Work Start.
	  * Date when work is (planned to be) started
	  */
	public Timestamp getDateWorkStart();

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

    /** Column name EXME_ClaimPayment_View_ID */
    public static final String COLUMNNAME_EXME_ClaimPayment_View_ID = "EXME_ClaimPayment_View_ID";

	/** Set EXME_ClaimPayment_View_ID	  */
	public void setEXME_ClaimPayment_View_ID (int EXME_ClaimPayment_View_ID);

	/** Get EXME_ClaimPayment_View_ID	  */
	public int getEXME_ClaimPayment_View_ID();

    /** Column name Insurance */
    public static final String COLUMNNAME_Insurance = "Insurance";

	/** Set Insurance	  */
	public void setInsurance (String Insurance);

	/** Get Insurance	  */
	public String getInsurance();

    /** Column name PayAmt */
    public static final String COLUMNNAME_PayAmt = "PayAmt";

	/** Set Payment amount.
	  * Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt);

	/** Get Payment amount.
	  * Amount being paid
	  */
	public BigDecimal getPayAmt();

    /** Column name PostedDate */
    public static final String COLUMNNAME_PostedDate = "PostedDate";

	/** Set Post date.
	  * Post date
	  */
	public void setPostedDate (Timestamp PostedDate);

	/** Get Post date.
	  * Post date
	  */
	public Timestamp getPostedDate();

    /** Column name ReferenceNo */
    public static final String COLUMNNAME_ReferenceNo = "ReferenceNo";

	/** Set Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo);

	/** Get Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo();

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

    /** Column name Tot_Encounters */
    public static final String COLUMNNAME_Tot_Encounters = "Tot_Encounters";

	/** Set Tot_Encounters	  */
	public void setTot_Encounters (BigDecimal Tot_Encounters);

	/** Get Tot_Encounters	  */
	public BigDecimal getTot_Encounters();

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
