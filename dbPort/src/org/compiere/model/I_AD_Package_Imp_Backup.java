/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Package_Imp_Backup
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Package_Imp_Backup 
{

    /** TableName=AD_Package_Imp_Backup */
    public static final String Table_Name = "AD_Package_Imp_Backup";

    /** AD_Table_ID=1200799 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name AD_Package_Imp_Backup_ID */
    public static final String COLUMNNAME_AD_Package_Imp_Backup_ID = "AD_Package_Imp_Backup_ID";

	/** Set AD_Package_Imp_Backup_ID	  */
	public void setAD_Package_Imp_Backup_ID (int AD_Package_Imp_Backup_ID);

	/** Get AD_Package_Imp_Backup_ID	  */
	public int getAD_Package_Imp_Backup_ID();

    /** Column name AD_Package_Imp_Bck_Dir */
    public static final String COLUMNNAME_AD_Package_Imp_Bck_Dir = "AD_Package_Imp_Bck_Dir";

	/** Set AD_Package_Imp_Bck_Dir	  */
	public void setAD_Package_Imp_Bck_Dir (String AD_Package_Imp_Bck_Dir);

	/** Get AD_Package_Imp_Bck_Dir	  */
	public String getAD_Package_Imp_Bck_Dir();

    /** Column name AD_Package_Imp_Detail_ID */
    public static final String COLUMNNAME_AD_Package_Imp_Detail_ID = "AD_Package_Imp_Detail_ID";

	/** Set AD_Package_Imp_Detail_ID	  */
	public void setAD_Package_Imp_Detail_ID (int AD_Package_Imp_Detail_ID);

	/** Get AD_Package_Imp_Detail_ID	  */
	public int getAD_Package_Imp_Detail_ID();

    /** Column name AD_Package_Imp_ID */
    public static final String COLUMNNAME_AD_Package_Imp_ID = "AD_Package_Imp_ID";

	/** Set AD_Package_Imp_ID	  */
	public void setAD_Package_Imp_ID (int AD_Package_Imp_ID);

	/** Get AD_Package_Imp_ID	  */
	public int getAD_Package_Imp_ID();

    /** Column name AD_Package_Imp_Org_Dir */
    public static final String COLUMNNAME_AD_Package_Imp_Org_Dir = "AD_Package_Imp_Org_Dir";

	/** Set AD_Package_Imp_Org_Dir	  */
	public void setAD_Package_Imp_Org_Dir (String AD_Package_Imp_Org_Dir);

	/** Get AD_Package_Imp_Org_Dir	  */
	public String getAD_Package_Imp_Org_Dir();

    /** Column name AD_Reference_ID */
    public static final String COLUMNNAME_AD_Reference_ID = "AD_Reference_ID";

	/** Set Reference.
	  * System Reference and Validation
	  */
	public void setAD_Reference_ID (int AD_Reference_ID);

	/** Get Reference.
	  * System Reference and Validation
	  */
	public int getAD_Reference_ID();

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

    /** Column name ColValue */
    public static final String COLUMNNAME_ColValue = "ColValue";

	/** Set Col Value	  */
	public void setColValue (String ColValue);

	/** Get Col Value	  */
	public String getColValue();

    /** Column name Uninstall */
    public static final String COLUMNNAME_Uninstall = "Uninstall";

	/** Set Uninstall	  */
	public void setUninstall (boolean Uninstall);

	/** Get Uninstall	  */
	public boolean isUninstall();
}
