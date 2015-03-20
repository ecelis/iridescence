/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_HouseKeeping
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_HouseKeeping 
{

    /** TableName=AD_HouseKeeping */
    public static final String Table_Name = "AD_HouseKeeping";

    /** AD_Table_ID=1200748 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_HouseKeeping_ID */
    public static final String COLUMNNAME_AD_HouseKeeping_ID = "AD_HouseKeeping_ID";

	/** Set House Keeping Configuration	  */
	public void setAD_HouseKeeping_ID (int AD_HouseKeeping_ID);

	/** Get House Keeping Configuration	  */
	public int getAD_HouseKeeping_ID();

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

    /** Column name BackupFolder */
    public static final String COLUMNNAME_BackupFolder = "BackupFolder";

	/** Set Backup Folder.
	  * Backup Folder
	  */
	public void setBackupFolder (String BackupFolder);

	/** Get Backup Folder.
	  * Backup Folder
	  */
	public String getBackupFolder();

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

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsExportXMLBackup */
    public static final String COLUMNNAME_IsExportXMLBackup = "IsExportXMLBackup";

	/** Set Export XML Backup	  */
	public void setIsExportXMLBackup (boolean IsExportXMLBackup);

	/** Get Export XML Backup	  */
	public boolean isExportXMLBackup();

    /** Column name IsSaveInHistoric */
    public static final String COLUMNNAME_IsSaveInHistoric = "IsSaveInHistoric";

	/** Set Save In Historic	  */
	public void setIsSaveInHistoric (boolean IsSaveInHistoric);

	/** Get Save In Historic	  */
	public boolean isSaveInHistoric();

    /** Column name LastDeleted */
    public static final String COLUMNNAME_LastDeleted = "LastDeleted";

	/** Set Last Deleted	  */
	public void setLastDeleted (int LastDeleted);

	/** Get Last Deleted	  */
	public int getLastDeleted();

    /** Column name LastRun */
    public static final String COLUMNNAME_LastRun = "LastRun";

	/** Set Last Run	  */
	public void setLastRun (Timestamp LastRun);

	/** Get Last Run	  */
	public Timestamp getLastRun();

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

    /** Column name WhereClause */
    public static final String COLUMNNAME_WhereClause = "WhereClause";

	/** Set Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause);

	/** Get Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public String getWhereClause();
}
