/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Table
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Table 
{

    /** TableName=AD_Table */
    public static final String Table_Name = "AD_Table";

    /** AD_Table_ID=100 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Form_ID */
    public static final String COLUMNNAME_AD_Form_ID = "AD_Form_ID";

	/** Set Special Form.
	  * Special Form
	  */
	public void setAD_Form_ID (int AD_Form_ID);

	/** Get Special Form.
	  * Special Form
	  */
	public int getAD_Form_ID();

	public I_AD_Form getAD_Form() throws RuntimeException;

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

    /** Column name AD_Val_Rule_ID */
    public static final String COLUMNNAME_AD_Val_Rule_ID = "AD_Val_Rule_ID";

	/** Set Dynamic Validation.
	  * Dynamic Validation Rule
	  */
	public void setAD_Val_Rule_ID (int AD_Val_Rule_ID);

	/** Get Dynamic Validation.
	  * Dynamic Validation Rule
	  */
	public int getAD_Val_Rule_ID();

	public I_AD_Val_Rule getAD_Val_Rule() throws RuntimeException;

    /** Column name AD_Window_ID */
    public static final String COLUMNNAME_AD_Window_ID = "AD_Window_ID";

	/** Set Window.
	  * Data entry or display window
	  */
	public void setAD_Window_ID (int AD_Window_ID);

	/** Get Window.
	  * Data entry or display window
	  */
	public int getAD_Window_ID();

	public I_AD_Window getAD_Window() throws RuntimeException;

    /** Column name AccessLevel */
    public static final String COLUMNNAME_AccessLevel = "AccessLevel";

	/** Set Data Access Level.
	  * Access Level required
	  */
	public void setAccessLevel (String AccessLevel);

	/** Get Data Access Level.
	  * Access Level required
	  */
	public String getAccessLevel();

    /** Column name CopyColumnsFromTable */
    public static final String COLUMNNAME_CopyColumnsFromTable = "CopyColumnsFromTable";

	/** Set Copy Columns From Table	  */
	public void setCopyColumnsFromTable (String CopyColumnsFromTable);

	/** Get Copy Columns From Table	  */
	public String getCopyColumnsFromTable();

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

    /** Column name EntityType */
    public static final String COLUMNNAME_EntityType = "EntityType";

	/** Set Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType);

	/** Get Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public String getEntityType();

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

    /** Column name ImportTable */
    public static final String COLUMNNAME_ImportTable = "ImportTable";

	/** Set Import Table.
	  * Import Table Columns from Database
	  */
	public void setImportTable (String ImportTable);

	/** Get Import Table.
	  * Import Table Columns from Database
	  */
	public String getImportTable();

    /** Column name IsChangeLog */
    public static final String COLUMNNAME_IsChangeLog = "IsChangeLog";

	/** Set Maintain Change Log.
	  * Maintain a log of changes
	  */
	public void setIsChangeLog (boolean IsChangeLog);

	/** Get Maintain Change Log.
	  * Maintain a log of changes
	  */
	public boolean isChangeLog();

    /** Column name IsDeleteable */
    public static final String COLUMNNAME_IsDeleteable = "IsDeleteable";

	/** Set Records deleteable.
	  * Indicates if records can be deleted from the database
	  */
	public void setIsDeleteable (boolean IsDeleteable);

	/** Get Records deleteable.
	  * Indicates if records can be deleted from the database
	  */
	public boolean isDeleteable();

    /** Column name IsGenerateMessage */
    public static final String COLUMNNAME_IsGenerateMessage = "IsGenerateMessage";

	/** Set Generate HL7 Message	  */
	public void setIsGenerateMessage (boolean IsGenerateMessage);

	/** Get Generate HL7 Message	  */
	public boolean isGenerateMessage();

    /** Column name IsHighVolume */
    public static final String COLUMNNAME_IsHighVolume = "IsHighVolume";

	/** Set High Volume.
	  * Use Search instead of Pick list
	  */
	public void setIsHighVolume (boolean IsHighVolume);

	/** Get High Volume.
	  * Use Search instead of Pick list
	  */
	public boolean isHighVolume();

    /** Column name IsSecurityEnabled */
    public static final String COLUMNNAME_IsSecurityEnabled = "IsSecurityEnabled";

	/** Set Security enabled.
	  * If security is enabled, user access to data can be restricted via Roles
	  */
	public void setIsSecurityEnabled (boolean IsSecurityEnabled);

	/** Get Security enabled.
	  * If security is enabled, user access to data can be restricted via Roles
	  */
	public boolean isSecurityEnabled();

    /** Column name IsSystemCode */
    public static final String COLUMNNAME_IsSystemCode = "IsSystemCode";

	/** Set Coding System	  */
	public void setIsSystemCode (boolean IsSystemCode);

	/** Get Coding System	  */
	public boolean isSystemCode();

    /** Column name IsTrx */
    public static final String COLUMNNAME_IsTrx = "IsTrx";

	/** Set Is Transactional.
	  * It is a transactional table
	  */
	public void setIsTrx (boolean IsTrx);

	/** Get Is Transactional.
	  * It is a transactional table
	  */
	public boolean isTrx();

    /** Column name IsView */
    public static final String COLUMNNAME_IsView = "IsView";

	/** Set View.
	  * This is a view
	  */
	public void setIsView (boolean IsView);

	/** Get View.
	  * This is a view
	  */
	public boolean isView();

    /** Column name LoadSeq */
    public static final String COLUMNNAME_LoadSeq = "LoadSeq";

	/** Set Sequence	  */
	public void setLoadSeq (int LoadSeq);

	/** Get Sequence	  */
	public int getLoadSeq();

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

    /** Column name PO_Window_ID */
    public static final String COLUMNNAME_PO_Window_ID = "PO_Window_ID";

	/** Set PO Window.
	  * Purchase Order Window
	  */
	public void setPO_Window_ID (int PO_Window_ID);

	/** Get PO Window.
	  * Purchase Order Window
	  */
	public int getPO_Window_ID();

    /** Column name QuickSearch */
    public static final String COLUMNNAME_QuickSearch = "QuickSearch";

	/** Set Quick search.
	  * Quick search
	  */
	public void setQuickSearch (boolean QuickSearch);

	/** Get Quick search.
	  * Quick search
	  */
	public boolean isQuickSearch();

    /** Column name ReplicationType */
    public static final String COLUMNNAME_ReplicationType = "ReplicationType";

	/** Set Replication Type.
	  * Type of Data Replication
	  */
	public void setReplicationType (String ReplicationType);

	/** Get Replication Type.
	  * Type of Data Replication
	  */
	public String getReplicationType();

    /** Column name TableName */
    public static final String COLUMNNAME_TableName = "TableName";

	/** Set DB Table Name.
	  * Name of the table in the database
	  */
	public void setTableName (String TableName);

	/** Get DB Table Name.
	  * Name of the table in the database
	  */
	public String getTableName();

    /** Column name UseDB */
    public static final String COLUMNNAME_UseDB = "UseDB";

	/** Set Use Database.
	  * Use directly Database for quick search component
	  */
	public void setUseDB (boolean UseDB);

	/** Get Use Database.
	  * Use directly Database for quick search component
	  */
	public boolean isUseDB();
}
