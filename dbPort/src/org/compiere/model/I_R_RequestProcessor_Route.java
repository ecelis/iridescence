/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for R_RequestProcessor_Route
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_R_RequestProcessor_Route 
{

    /** TableName=R_RequestProcessor_Route */
    public static final String Table_Name = "R_RequestProcessor_Route";

    /** AD_Table_ID=474 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name Keyword */
    public static final String COLUMNNAME_Keyword = "Keyword";

	/** Set Keyword.
	  * Case insensitive keyword
	  */
	public void setKeyword (String Keyword);

	/** Get Keyword.
	  * Case insensitive keyword
	  */
	public String getKeyword();

    /** Column name R_RequestProcessor_ID */
    public static final String COLUMNNAME_R_RequestProcessor_ID = "R_RequestProcessor_ID";

	/** Set Request Processor.
	  * Processor for Requests
	  */
	public void setR_RequestProcessor_ID (int R_RequestProcessor_ID);

	/** Get Request Processor.
	  * Processor for Requests
	  */
	public int getR_RequestProcessor_ID();

	public I_R_RequestProcessor getR_RequestProcessor() throws RuntimeException;

    /** Column name R_RequestProcessor_Route_ID */
    public static final String COLUMNNAME_R_RequestProcessor_Route_ID = "R_RequestProcessor_Route_ID";

	/** Set Request Routing.
	  * Automatic routing of requests
	  */
	public void setR_RequestProcessor_Route_ID (int R_RequestProcessor_Route_ID);

	/** Get Request Routing.
	  * Automatic routing of requests
	  */
	public int getR_RequestProcessor_Route_ID();

    /** Column name R_RequestType_ID */
    public static final String COLUMNNAME_R_RequestType_ID = "R_RequestType_ID";

	/** Set Request Type.
	  * Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public void setR_RequestType_ID (int R_RequestType_ID);

	/** Get Request Type.
	  * Type of request (e.g. Inquiry, Complaint, ..)
	  */
	public int getR_RequestType_ID();

	public I_R_RequestType getR_RequestType() throws RuntimeException;

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
