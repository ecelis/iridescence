/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_AttachmentFile
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_AD_AttachmentFile 
{

    /** TableName=AD_AttachmentFile */
    public static final String Table_Name = "AD_AttachmentFile";

    /** AD_Table_ID=1201331 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_AttachmentFile_ID */
    public static final String COLUMNNAME_AD_AttachmentFile_ID = "AD_AttachmentFile_ID";

	/** Set Attachment File	  */
	public void setAD_AttachmentFile_ID (int AD_AttachmentFile_ID);

	/** Get Attachment File	  */
	public int getAD_AttachmentFile_ID();

    /** Column name AD_AttachmentType_ID */
    public static final String COLUMNNAME_AD_AttachmentType_ID = "AD_AttachmentType_ID";

	/** Set Attachment type	  */
	public void setAD_AttachmentType_ID (int AD_AttachmentType_ID);

	/** Get Attachment type	  */
	public int getAD_AttachmentType_ID();

	public I_AD_AttachmentType getAD_AttachmentType() throws RuntimeException;

    /** Column name AD_Attachment_ID */
    public static final String COLUMNNAME_AD_Attachment_ID = "AD_Attachment_ID";

	/** Set Attachment.
	  * Attachment for the document
	  */
	public void setAD_Attachment_ID (int AD_Attachment_ID);

	/** Get Attachment.
	  * Attachment for the document
	  */
	public int getAD_Attachment_ID();

	public I_AD_Attachment getAD_Attachment() throws RuntimeException;

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

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

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

    /** Column name FileName */
    public static final String COLUMNNAME_FileName = "FileName";

	/** Set File Name.
	  * Name of the local file or URL
	  */
	public void setFileName (String FileName);

	/** Get File Name.
	  * Name of the local file or URL
	  */
	public String getFileName();
}
