/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for K_Index
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_K_Index 
{

    /** TableName=K_Index */
    public static final String Table_Name = "K_Index";

    /** AD_Table_ID=900 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name CM_WebProject_ID */
    public static final String COLUMNNAME_CM_WebProject_ID = "CM_WebProject_ID";

	/** Set Web Project.
	  * A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public void setCM_WebProject_ID (int CM_WebProject_ID);

	/** Get Web Project.
	  * A web project is the main data container for Containers, URLs, Ads, Media etc.
	  */
	public int getCM_WebProject_ID();

	public I_CM_WebProject getCM_WebProject() throws RuntimeException;

    /** Column name Excerpt */
    public static final String COLUMNNAME_Excerpt = "Excerpt";

	/** Set Excerpt.
	  * Surrounding text of the keyword
	  */
	public void setExcerpt (String Excerpt);

	/** Get Excerpt.
	  * Surrounding text of the keyword
	  */
	public String getExcerpt();

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

    /** Column name K_INDEX_ID */
    public static final String COLUMNNAME_K_INDEX_ID = "K_INDEX_ID";

	/** Set Index.
	  * Text Search Index
	  */
	public void setK_INDEX_ID (int K_INDEX_ID);

	/** Get Index.
	  * Text Search Index
	  */
	public int getK_INDEX_ID();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

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

    /** Column name SourceUpdated */
    public static final String COLUMNNAME_SourceUpdated = "SourceUpdated";

	/** Set Source Updated.
	  * Date the source document was updated
	  */
	public void setSourceUpdated (Timestamp SourceUpdated);

	/** Get Source Updated.
	  * Date the source document was updated
	  */
	public Timestamp getSourceUpdated();
}
