/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Addition
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_A_Asset_Addition 
{

    /** TableName=A_Asset_Addition */
    public static final String Table_Name = "A_Asset_Addition";

    /** AD_Table_ID=1200794 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Asset_Addition_ID */
    public static final String COLUMNNAME_A_Asset_Addition_ID = "A_Asset_Addition_ID";

	/** Set A_Asset_Addition_ID	  */
	public void setA_Asset_Addition_ID (int A_Asset_Addition_ID);

	/** Get A_Asset_Addition_ID	  */
	public int getA_Asset_Addition_ID();

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

    /** Column name A_CapvsExp */
    public static final String COLUMNNAME_A_CapvsExp = "A_CapvsExp";

	/** Set Capital vs Expense	  */
	public void setA_CapvsExp (String A_CapvsExp);

	/** Get Capital vs Expense	  */
	public String getA_CapvsExp();

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

    /** Column name A_QTY_Current */
    public static final String COLUMNNAME_A_QTY_Current = "A_QTY_Current";

	/** Set Quantity	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current);

	/** Get Quantity	  */
	public BigDecimal getA_QTY_Current();

    /** Column name A_SourceType */
    public static final String COLUMNNAME_A_SourceType = "A_SourceType";

	/** Set Source of Entry	  */
	public void setA_SourceType (String A_SourceType);

	/** Get Source of Entry	  */
	public String getA_SourceType();

    /** Column name AssetValueAmt */
    public static final String COLUMNNAME_AssetValueAmt = "AssetValueAmt";

	/** Set Asset value.
	  * Book Value of the asset
	  */
	public void setAssetValueAmt (BigDecimal AssetValueAmt);

	/** Get Asset value.
	  * Book Value of the asset
	  */
	public BigDecimal getAssetValueAmt();

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

    /** Column name C_InvoiceLine_ID */
    public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";

	/** Set Invoice Line.
	  * Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID);

	/** Get Invoice Line.
	  * Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID();

	public I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException;

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

    /** Column name GL_JournalBatch_ID */
    public static final String COLUMNNAME_GL_JournalBatch_ID = "GL_JournalBatch_ID";

	/** Set Journal Batch.
	  * General Ledger Journal Batch
	  */
	public void setGL_JournalBatch_ID (int GL_JournalBatch_ID);

	/** Get Journal Batch.
	  * General Ledger Journal Batch
	  */
	public int getGL_JournalBatch_ID();

	public I_GL_JournalBatch getGL_JournalBatch() throws RuntimeException;

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name M_InOutLine_ID */
    public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";

	/** Set Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID);

	/** Get Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID();

    /** Column name PostingType */
    public static final String COLUMNNAME_PostingType = "PostingType";

	/** Set Posting Type.
	  * The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType);

	/** Get Posting Type.
	  * The type of posted amount for the transaction
	  */
	public String getPostingType();
}