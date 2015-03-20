/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ClaimForm
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ClaimForm 
{

    /** TableName=EXME_ClaimForm */
    public static final String Table_Name = "EXME_ClaimForm";

    /** AD_Table_ID=1201240 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public I_AD_Column getAD_Column() throws RuntimeException;

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

    /** Column name EXME_ClaimForm_ID */
    public static final String COLUMNNAME_EXME_ClaimForm_ID = "EXME_ClaimForm_ID";

	/** Set EXME_ClaimForm_ID	  */
	public void setEXME_ClaimForm_ID (int EXME_ClaimForm_ID);

	/** Get EXME_ClaimForm_ID	  */
	public int getEXME_ClaimForm_ID();

    /** Column name EXME_ClaimMessage_ID */
    public static final String COLUMNNAME_EXME_ClaimMessage_ID = "EXME_ClaimMessage_ID";

	/** Set EXME_ClaimMessage_ID	  */
	public void setEXME_ClaimMessage_ID (int EXME_ClaimMessage_ID);

	/** Get EXME_ClaimMessage_ID	  */
	public int getEXME_ClaimMessage_ID();

	public I_EXME_ClaimMessage getEXME_ClaimMessage() throws RuntimeException;

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (BigDecimal Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public BigDecimal getLine();

    /** Column name Position */
    public static final String COLUMNNAME_Position = "Position";

	/** Set Position	  */
	public void setPosition (BigDecimal Position);

	/** Get Position	  */
	public BigDecimal getPosition();

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

    /** Column name Sql */
    public static final String COLUMNNAME_Sql = "Sql";

	/** Set Sql	  */
	public void setSql (String Sql);

	/** Get Sql	  */
	public String getSql();

    /** Column name SubPosition */
    public static final String COLUMNNAME_SubPosition = "SubPosition";

	/** Set SubPosition	  */
	public void setSubPosition (String SubPosition);

	/** Get SubPosition	  */
	public String getSubPosition();
}
