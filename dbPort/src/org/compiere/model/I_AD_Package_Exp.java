/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Package_Exp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Package_Exp 
{

    /** TableName=AD_Package_Exp */
    public static final String Table_Name = "AD_Package_Exp";

    /** AD_Table_ID=1200802 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name AD_Package_Exp_ID */
    public static final String COLUMNNAME_AD_Package_Exp_ID = "AD_Package_Exp_ID";

	/** Set AD_Package_Exp_ID	  */
	public void setAD_Package_Exp_ID (int AD_Package_Exp_ID);

	/** Get AD_Package_Exp_ID	  */
	public int getAD_Package_Exp_ID();

    /** Column name AD_Package_Type */
    public static final String COLUMNNAME_AD_Package_Type = "AD_Package_Type";

	/** Set AD_Package_Type	  */
	public void setAD_Package_Type (String AD_Package_Type);

	/** Get AD_Package_Type	  */
	public String getAD_Package_Type();

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

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name File_Directory */
    public static final String COLUMNNAME_File_Directory = "File_Directory";

	/** Set File_Directory	  */
	public void setFile_Directory (String File_Directory);

	/** Get File_Directory	  */
	public String getFile_Directory();

    /** Column name Instructions */
    public static final String COLUMNNAME_Instructions = "Instructions";

	/** Set Instructions	  */
	public void setInstructions (String Instructions);

	/** Get Instructions	  */
	public String getInstructions();

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

    /** Column name PK_Version */
    public static final String COLUMNNAME_PK_Version = "PK_Version";

	/** Set Package Version	  */
	public void setPK_Version (String PK_Version);

	/** Get Package Version	  */
	public String getPK_Version();

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

    /** Column name ReleaseNo */
    public static final String COLUMNNAME_ReleaseNo = "ReleaseNo";

	/** Set Release No.
	  * Internal Release Number
	  */
	public void setReleaseNo (String ReleaseNo);

	/** Get Release No.
	  * Internal Release Number
	  */
	public String getReleaseNo();

    /** Column name UserName */
    public static final String COLUMNNAME_UserName = "UserName";

	/** Set Registered EMail.
	  * Email of the responsible for the System
	  */
	public void setUserName (String UserName);

	/** Get Registered EMail.
	  * Email of the responsible for the System
	  */
	public String getUserName();

    /** Column name Version */
    public static final String COLUMNNAME_Version = "Version";

	/** Set Version.
	  * Version of the table definition
	  */
	public void setVersion (String Version);

	/** Get Version.
	  * Version of the table definition
	  */
	public String getVersion();
}