/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EI_M_Requisition
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EI_M_Requisition 
{

    /** TableName=EI_M_Requisition */
    public static final String Table_Name = "EI_M_Requisition";

    /** AD_Table_ID=1200243 */
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

    /** Column name EI_M_Requisition_ID */
    public static final String COLUMNNAME_EI_M_Requisition_ID = "EI_M_Requisition_ID";

	/** Set Requisition.
	  * Requisition Number
	  */
	public void setEI_M_Requisition_ID (int EI_M_Requisition_ID);

	/** Get Requisition.
	  * Requisition Number
	  */
	public int getEI_M_Requisition_ID();

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

    /** Column name M_Requisition_ID */
    public static final String COLUMNNAME_M_Requisition_ID = "M_Requisition_ID";

	/** Set Requisition.
	  * Material Requisition
	  */
	public void setM_Requisition_ID (int M_Requisition_ID);

	/** Get Requisition.
	  * Material Requisition
	  */
	public int getM_Requisition_ID();

	public I_M_Requisition getM_Requisition() throws RuntimeException;
}
