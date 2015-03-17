/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Replication_Log
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Replication_Log 
{

    /** TableName=AD_Replication_Log */
    public static final String Table_Name = "AD_Replication_Log";

    /** AD_Table_ID=604 */
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

    /** Column name AD_Replication_Log_ID */
    public static final String COLUMNNAME_AD_Replication_Log_ID = "AD_Replication_Log_ID";

	/** Set Replication Log.
	  * Data Replication Log Details
	  */
	public void setAD_Replication_Log_ID (int AD_Replication_Log_ID);

	/** Get Replication Log.
	  * Data Replication Log Details
	  */
	public int getAD_Replication_Log_ID();

    /** Column name AD_Replication_Run_ID */
    public static final String COLUMNNAME_AD_Replication_Run_ID = "AD_Replication_Run_ID";

	/** Set Replication Run.
	  * Data Replication Run
	  */
	public void setAD_Replication_Run_ID (int AD_Replication_Run_ID);

	/** Get Replication Run.
	  * Data Replication Run
	  */
	public int getAD_Replication_Run_ID();

	public I_AD_Replication_Run getAD_Replication_Run() throws RuntimeException;

    /** Column name AD_ReplicationTable_ID */
    public static final String COLUMNNAME_AD_ReplicationTable_ID = "AD_ReplicationTable_ID";

	/** Set Replication Table.
	  * Data Replication Strategy Table Info
	  */
	public void setAD_ReplicationTable_ID (int AD_ReplicationTable_ID);

	/** Get Replication Table.
	  * Data Replication Strategy Table Info
	  */
	public int getAD_ReplicationTable_ID();

	public I_AD_ReplicationTable getAD_ReplicationTable() throws RuntimeException;

    /** Column name IsReplicated */
    public static final String COLUMNNAME_IsReplicated = "IsReplicated";

	/** Set Replicated.
	  * The data is successfully replicated
	  */
	public void setIsReplicated (boolean IsReplicated);

	/** Get Replicated.
	  * The data is successfully replicated
	  */
	public boolean isReplicated();

    /** Column name P_Msg */
    public static final String COLUMNNAME_P_Msg = "P_Msg";

	/** Set Process Message	  */
	public void setP_Msg (String P_Msg);

	/** Get Process Message	  */
	public String getP_Msg();
}
