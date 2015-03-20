/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EI_C_Invoice
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EI_C_Invoice 
{

    /** TableName=EI_C_Invoice */
    public static final String Table_Name = "EI_C_Invoice";

    /** AD_Table_ID=1200238 */
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

    /** Column name EI_C_Invoice_ID */
    public static final String COLUMNNAME_EI_C_Invoice_ID = "EI_C_Invoice_ID";

	/** Set Invoice Key.
	  * Invoice Key
	  */
	public void setEI_C_Invoice_ID (int EI_C_Invoice_ID);

	/** Get Invoice Key.
	  * Invoice Key
	  */
	public int getEI_C_Invoice_ID();

    /** Column name ErrorDescription */
    public static final String COLUMNNAME_ErrorDescription = "ErrorDescription";

	/** Set ErrorDescription.
	  * ErrorDescription
	  */
	public void setErrorDescription (String ErrorDescription);

	/** Get ErrorDescription.
	  * ErrorDescription
	  */
	public String getErrorDescription();

    /** Column name ErrorStatus */
    public static final String COLUMNNAME_ErrorStatus = "ErrorStatus";

	/** Set ErrorStatus.
	  * ErrorStatus
	  */
	public void setErrorStatus (boolean ErrorStatus);

	/** Get ErrorStatus.
	  * ErrorStatus
	  */
	public boolean isErrorStatus();

    /** Column name PrintName */
    public static final String COLUMNNAME_PrintName = "PrintName";

	/** Set Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public void setPrintName (String PrintName);

	/** Get Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public String getPrintName();
}
