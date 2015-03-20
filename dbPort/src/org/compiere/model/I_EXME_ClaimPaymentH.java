/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ClaimPaymentH
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ClaimPaymentH 
{

    /** TableName=EXME_ClaimPaymentH */
    public static final String Table_Name = "EXME_ClaimPaymentH";

    /** AD_Table_ID=1201221 */
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

    /** Column name BillingType */
    public static final String COLUMNNAME_BillingType = "BillingType";

	/** Set Billing type	  */
	public void setBillingType (String BillingType);

	/** Get Billing type	  */
	public String getBillingType();

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

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

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

    /** Column name EftCheckNo */
    public static final String COLUMNNAME_EftCheckNo = "EftCheckNo";

	/** Set EFT Check No.
	  * Electronic Funds Transfer Check No
	  */
	public void setEftCheckNo (String EftCheckNo);

	/** Get EFT Check No.
	  * Electronic Funds Transfer Check No
	  */
	public String getEftCheckNo();

    /** Column name EXME_ClaimPaymentH_ID */
    public static final String COLUMNNAME_EXME_ClaimPaymentH_ID = "EXME_ClaimPaymentH_ID";

	/** Set Payments Claim Header.
	  * Payments Claim Header
	  */
	public void setEXME_ClaimPaymentH_ID (int EXME_ClaimPaymentH_ID);

	/** Get Payments Claim Header.
	  * Payments Claim Header
	  */
	public int getEXME_ClaimPaymentH_ID();

    /** Column name EXME_ClaimPayment_S_ID */
    public static final String COLUMNNAME_EXME_ClaimPayment_S_ID = "EXME_ClaimPayment_S_ID";

	/** Set Claim Payment.
	  * ID to store Claim Payments from EXME_ClaimPaymentH
	  */
	public void setEXME_ClaimPayment_S_ID (int EXME_ClaimPayment_S_ID);

	/** Get Claim Payment.
	  * ID to store Claim Payments from EXME_ClaimPaymentH
	  */
	public int getEXME_ClaimPayment_S_ID();

	public I_EXME_ClaimPayment_S getEXME_ClaimPayment_S() throws RuntimeException;

    /** Column name isInterface */
    public static final String COLUMNNAME_isInterface = "isInterface";

	/** Set isInterface	  */
	public void setisInterface (boolean isInterface);

	/** Get isInterface	  */
	public boolean isInterface();

    /** Column name IsManual */
    public static final String COLUMNNAME_IsManual = "IsManual";

	/** Set Manual.
	  * This is a manual process
	  */
	public void setIsManual (boolean IsManual);

	/** Get Manual.
	  * This is a manual process
	  */
	public boolean isManual();

    /** Column name PaidAmt */
    public static final String COLUMNNAME_PaidAmt = "PaidAmt";

	/** Set Paid Amount	  */
	public void setPaidAmt (BigDecimal PaidAmt);

	/** Get Paid Amount	  */
	public BigDecimal getPaidAmt();

    /** Column name PaymentDate */
    public static final String COLUMNNAME_PaymentDate = "PaymentDate";

	/** Set Payment date	  */
	public void setPaymentDate (Timestamp PaymentDate);

	/** Get Payment date	  */
	public Timestamp getPaymentDate();

    /** Column name PaymentType */
    public static final String COLUMNNAME_PaymentType = "PaymentType";

	/** Set PaymentType.
	  * Billing Payment
	  */
	public void setPaymentType (String PaymentType);

	/** Get PaymentType.
	  * Billing Payment
	  */
	public String getPaymentType();

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

    /** Column name TotalAmt */
    public static final String COLUMNNAME_TotalAmt = "TotalAmt";

	/** Set Total Amount.
	  * Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt);

	/** Get Total Amount.
	  * Total Amount
	  */
	public BigDecimal getTotalAmt();
}
