/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_ChangeLog
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_ChangeLog 
{

    /** TableName=AD_ChangeLog */
    public static final String Table_Name = "AD_ChangeLog";

    /** AD_Table_ID=580 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_ChangeLog_ID */
    public static final String COLUMNNAME_AD_ChangeLog_ID = "AD_ChangeLog_ID";

	/** Set Change Log.
	  * Log of data changes
	  */
	public void setAD_ChangeLog_ID (int AD_ChangeLog_ID);

	/** Get Change Log.
	  * Log of data changes
	  */
	public int getAD_ChangeLog_ID();

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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

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

    /** Column name EventChangeLog */
    public static final String COLUMNNAME_EventChangeLog = "EventChangeLog";

	/** Set Event Change Log.
	  * Type of Event in Change Log
	  */
	public void setEventChangeLog (String EventChangeLog);

	/** Get Event Change Log.
	  * Type of Event in Change Log
	  */
	public String getEventChangeLog();

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

    /** Column name IsCustomization */
    public static final String COLUMNNAME_IsCustomization = "IsCustomization";

	/** Set Customization.
	  * The change is a customization of the data dictionary and can be applied after Migration
	  */
	public void setIsCustomization (boolean IsCustomization);

	/** Get Customization.
	  * The change is a customization of the data dictionary and can be applied after Migration
	  */
	public boolean isCustomization();

    /** Column name NewValue */
    public static final String COLUMNNAME_NewValue = "NewValue";

	/** Set New Value.
	  * New field value
	  */
	public void setNewValue (String NewValue);

	/** Get New Value.
	  * New field value
	  */
	public String getNewValue();

    /** Column name OldValue */
    public static final String COLUMNNAME_OldValue = "OldValue";

	/** Set Old Value.
	  * The old file data
	  */
	public void setOldValue (String OldValue);

	/** Get Old Value.
	  * The old file data
	  */
	public String getOldValue();

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

    /** Column name Redo */
    public static final String COLUMNNAME_Redo = "Redo";

	/** Set Redo	  */
	public void setRedo (String Redo);

	/** Get Redo	  */
	public String getRedo();

    /** Column name TrxName */
    public static final String COLUMNNAME_TrxName = "TrxName";

	/** Set Transaction.
	  * Name of the transaction
	  */
	public void setTrxName (String TrxName);

	/** Get Transaction.
	  * Name of the transaction
	  */
	public String getTrxName();

    /** Column name Undo */
    public static final String COLUMNNAME_Undo = "Undo";

	/** Set Undo	  */
	public void setUndo (String Undo);

	/** Get Undo	  */
	public String getUndo();
}
