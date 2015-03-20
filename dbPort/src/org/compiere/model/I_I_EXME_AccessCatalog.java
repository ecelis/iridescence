/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_AccessCatalog
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_AccessCatalog 
{

    /** TableName=I_EXME_AccessCatalog */
    public static final String Table_Name = "I_EXME_AccessCatalog";

    /** AD_Table_ID=1200305 */
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

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_AccessCatalog_ID */
    public static final String COLUMNNAME_I_EXME_AccessCatalog_ID = "I_EXME_AccessCatalog_ID";

	/** Set I_EXME_AccessCatalog_ID	  */
	public void setI_EXME_AccessCatalog_ID (int I_EXME_AccessCatalog_ID);

	/** Get I_EXME_AccessCatalog_ID	  */
	public int getI_EXME_AccessCatalog_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name MultiAccess */
    public static final String COLUMNNAME_MultiAccess = "MultiAccess";

	/** Set Multi Access.
	  * Multi Access
	  */
	public void setMultiAccess (BigDecimal MultiAccess);

	/** Get Multi Access.
	  * Multi Access
	  */
	public BigDecimal getMultiAccess();

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

    /** Column name OriginalAccess */
    public static final String COLUMNNAME_OriginalAccess = "OriginalAccess";

	/** Set OriginalAccess	  */
	public void setOriginalAccess (BigDecimal OriginalAccess);

	/** Get OriginalAccess	  */
	public BigDecimal getOriginalAccess();

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

    /** Column name UniqueAccess */
    public static final String COLUMNNAME_UniqueAccess = "UniqueAccess";

	/** Set UniqueAccess.
	  * UniqueAccess
	  */
	public void setUniqueAccess (BigDecimal UniqueAccess);

	/** Get UniqueAccess.
	  * UniqueAccess
	  */
	public BigDecimal getUniqueAccess();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
