/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ClaimPayment_S
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ClaimPayment_S 
{

    /** TableName=EXME_ClaimPayment_S */
    public static final String Table_Name = "EXME_ClaimPayment_S";

    /** AD_Table_ID=1201320 */
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

    /** Column name CantTotal */
    public static final String COLUMNNAME_CantTotal = "CantTotal";

	/** Set Total Quantity.
	  * Total Quantity
	  */
	public void setCantTotal (BigDecimal CantTotal);

	/** Get Total Quantity.
	  * Total Quantity
	  */
	public BigDecimal getCantTotal();

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

    /** Column name PaymentDate */
    public static final String COLUMNNAME_PaymentDate = "PaymentDate";

	/** Set Payment date	  */
	public void setPaymentDate (Timestamp PaymentDate);

	/** Get Payment date	  */
	public Timestamp getPaymentDate();

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
