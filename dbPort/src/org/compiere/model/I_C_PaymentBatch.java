/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_PaymentBatch
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_PaymentBatch 
{

    /** TableName=C_PaymentBatch */
    public static final String Table_Name = "C_PaymentBatch";

    /** AD_Table_ID=411 */
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

    /** Column name C_PaymentBatch_ID */
    public static final String COLUMNNAME_C_PaymentBatch_ID = "C_PaymentBatch_ID";

	/** Set Payment Batch.
	  * Payment batch for EFT
	  */
	public void setC_PaymentBatch_ID (int C_PaymentBatch_ID);

	/** Get Payment Batch.
	  * Payment batch for EFT
	  */
	public int getC_PaymentBatch_ID();

    /** Column name C_PaymentProcessor_ID */
    public static final String COLUMNNAME_C_PaymentProcessor_ID = "C_PaymentProcessor_ID";

	/** Set Payment Processor.
	  * Payment processor for electronic payments
	  */
	public void setC_PaymentProcessor_ID (int C_PaymentProcessor_ID);

	/** Get Payment Processor.
	  * Payment processor for electronic payments
	  */
	public int getC_PaymentProcessor_ID();

	public I_C_PaymentProcessor getC_PaymentProcessor() throws RuntimeException;

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

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

    /** Column name ProcessingDate */
    public static final String COLUMNNAME_ProcessingDate = "ProcessingDate";

	/** Set Processing date	  */
	public void setProcessingDate (Timestamp ProcessingDate);

	/** Get Processing date	  */
	public Timestamp getProcessingDate();
}
