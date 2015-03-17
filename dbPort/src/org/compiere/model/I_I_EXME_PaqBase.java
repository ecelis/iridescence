/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_PaqBase
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_PaqBase 
{

    /** TableName=I_EXME_PaqBase */
    public static final String Table_Name = "I_EXME_PaqBase";

    /** AD_Table_ID=1200148 */
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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Value */
    public static final String COLUMNNAME_C_BPartner_Value = "C_BPartner_Value";

	/** Set Business Partner Value	  */
	public void setC_BPartner_Value (String C_BPartner_Value);

	/** Get Business Partner Value	  */
	public String getC_BPartner_Value();

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

    /** Column name EXME_PaqBase_ID */
    public static final String COLUMNNAME_EXME_PaqBase_ID = "EXME_PaqBase_ID";

	/** Set Base Package.
	  * Base Package
	  */
	public void setEXME_PaqBase_ID (int EXME_PaqBase_ID);

	/** Get Base Package.
	  * Base Package
	  */
	public int getEXME_PaqBase_ID();

	public I_EXME_PaqBase getEXME_PaqBase() throws RuntimeException;

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

    /** Column name I_EXME_PaqBase_ID */
    public static final String COLUMNNAME_I_EXME_PaqBase_ID = "I_EXME_PaqBase_ID";

	/** Set I_EXME_PaqBase_ID	  */
	public void setI_EXME_PaqBase_ID (int I_EXME_PaqBase_ID);

	/** Get I_EXME_PaqBase_ID	  */
	public int getI_EXME_PaqBase_ID();

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

    /** Column name IsMiniPack */
    public static final String COLUMNNAME_IsMiniPack = "IsMiniPack";

	/** Set Is Mini Pack.
	  * Is Mini Pack
	  */
	public void setIsMiniPack (boolean IsMiniPack);

	/** Get Is Mini Pack.
	  * Is Mini Pack
	  */
	public boolean isMiniPack();

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
