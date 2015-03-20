/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Scheduler_Para
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Scheduler_Para 
{

    /** TableName=AD_Scheduler_Para */
    public static final String Table_Name = "AD_Scheduler_Para";

    /** AD_Table_ID=698 */
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

    /** Column name AD_Process_Para_ID */
    public static final String COLUMNNAME_AD_Process_Para_ID = "AD_Process_Para_ID";

	/** Set Process Parameter.
	  * Process Parameter
	  */
	public void setAD_Process_Para_ID (int AD_Process_Para_ID);

	/** Get Process Parameter.
	  * Process Parameter
	  */
	public int getAD_Process_Para_ID();

	public I_AD_Process_Para getAD_Process_Para() throws RuntimeException;

    /** Column name AD_Scheduler_ID */
    public static final String COLUMNNAME_AD_Scheduler_ID = "AD_Scheduler_ID";

	/** Set Scheduler.
	  * Schedule Processes
	  */
	public void setAD_Scheduler_ID (int AD_Scheduler_ID);

	/** Get Scheduler.
	  * Schedule Processes
	  */
	public int getAD_Scheduler_ID();

	public I_AD_Scheduler getAD_Scheduler() throws RuntimeException;

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

    /** Column name ParameterDefault */
    public static final String COLUMNNAME_ParameterDefault = "ParameterDefault";

	/** Set Default Parameter.
	  * Default value of the parameter
	  */
	public void setParameterDefault (String ParameterDefault);

	/** Get Default Parameter.
	  * Default value of the parameter
	  */
	public String getParameterDefault();
}
