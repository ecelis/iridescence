/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_TaskInstance
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_TaskInstance 
{

    /** TableName=AD_TaskInstance */
    public static final String Table_Name = "AD_TaskInstance";

    /** AD_Table_ID=125 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name AD_Task_ID */
    public static final String COLUMNNAME_AD_Task_ID = "AD_Task_ID";

	/** Set OS Task.
	  * Operation System Task
	  */
	public void setAD_Task_ID (int AD_Task_ID);

	/** Get OS Task.
	  * Operation System Task
	  */
	public int getAD_Task_ID();

    /** Column name AD_TaskInstance_ID */
    public static final String COLUMNNAME_AD_TaskInstance_ID = "AD_TaskInstance_ID";

	/** Set Task Instance	  */
	public void setAD_TaskInstance_ID (int AD_TaskInstance_ID);

	/** Get Task Instance	  */
	public int getAD_TaskInstance_ID();
}
