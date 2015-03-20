/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_InvoiceInfo
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_InvoiceInfo 
{

    /** TableName=C_InvoiceInfo */
    public static final String Table_Name = "C_InvoiceInfo";

    /** AD_Table_ID=1201389 */
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

    /** Column name C_InvoiceInfo_ID */
    public static final String COLUMNNAME_C_InvoiceInfo_ID = "C_InvoiceInfo_ID";

	/** Set Additional information bill	  */
	public void setC_InvoiceInfo_ID (int C_InvoiceInfo_ID);

	/** Get Additional information bill	  */
	public int getC_InvoiceInfo_ID();

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

    /** Column name ErrorMsg */
    public static final String COLUMNNAME_ErrorMsg = "ErrorMsg";

	/** Set Error Msg	  */
	public void setErrorMsg (String ErrorMsg);

	/** Get Error Msg	  */
	public String getErrorMsg();

    /** Column name Ref_Invoice_Sales_ID */
    public static final String COLUMNNAME_Ref_Invoice_Sales_ID = "Ref_Invoice_Sales_ID";

	/** Set Ref. sales receipt 	  */
	public void setRef_Invoice_Sales_ID (int Ref_Invoice_Sales_ID);

	/** Get Ref. sales receipt 	  */
	public int getRef_Invoice_Sales_ID();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Universally Unique Identifier	  */
	public void setUUID (String UUID);

	/** Get Universally Unique Identifier	  */
	public String getUUID();
}
