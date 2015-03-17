/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for ASP_Workflow
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_ASP_Workflow 
{

    /** TableName=ASP_Workflow */
    public static final String Table_Name = "ASP_Workflow";

    /** AD_Table_ID=1200690 */
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

    /** Column name AD_Workflow_ID */
    public static final String COLUMNNAME_AD_Workflow_ID = "AD_Workflow_ID";

	/** Set Workflow.
	  * Workflow or combination of tasks
	  */
	public void setAD_Workflow_ID (int AD_Workflow_ID);

	/** Get Workflow.
	  * Workflow or combination of tasks
	  */
	public int getAD_Workflow_ID();

	public I_AD_Workflow getAD_Workflow() throws RuntimeException;

    /** Column name ASP_Level_ID */
    public static final String COLUMNNAME_ASP_Level_ID = "ASP_Level_ID";

	/** Set ASP Level	  */
	public void setASP_Level_ID (int ASP_Level_ID);

	/** Get ASP Level	  */
	public int getASP_Level_ID();

	public I_ASP_Level getASP_Level() throws RuntimeException;

    /** Column name ASP_Status */
    public static final String COLUMNNAME_ASP_Status = "ASP_Status";

	/** Set ASP Status	  */
	public void setASP_Status (String ASP_Status);

	/** Get ASP Status	  */
	public String getASP_Status();

    /** Column name ASP_Workflow_ID */
    public static final String COLUMNNAME_ASP_Workflow_ID = "ASP_Workflow_ID";

	/** Set ASP Workflow	  */
	public void setASP_Workflow_ID (int ASP_Workflow_ID);

	/** Get ASP Workflow	  */
	public int getASP_Workflow_ID();
}
