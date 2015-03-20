/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EI_C_Cashline
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EI_C_Cashline 
{

    /** TableName=EI_C_Cashline */
    public static final String Table_Name = "EI_C_Cashline";

    /** AD_Table_ID=1200237 */
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

    /** Column name C_CashLine_ID */
    public static final String COLUMNNAME_C_CashLine_ID = "C_CashLine_ID";

	/** Set Cash Journal Line.
	  * Cash Journal Line
	  */
	public void setC_CashLine_ID (int C_CashLine_ID);

	/** Get Cash Journal Line.
	  * Cash Journal Line
	  */
	public int getC_CashLine_ID();

	public I_C_CashLine getC_CashLine() throws RuntimeException;

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

    /** Column name EI_C_Cashline_ID */
    public static final String COLUMNNAME_EI_C_Cashline_ID = "EI_C_Cashline_ID";

	/** Set Cashline Key.
	  * Cashline Key
	  */
	public void setEI_C_Cashline_ID (int EI_C_Cashline_ID);

	/** Get Cashline Key.
	  * Cashline Key
	  */
	public int getEI_C_Cashline_ID();

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
}
