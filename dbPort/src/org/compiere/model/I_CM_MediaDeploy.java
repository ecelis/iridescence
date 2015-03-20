/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for CM_MediaDeploy
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_CM_MediaDeploy 
{

    /** TableName=CM_MediaDeploy */
    public static final String Table_Name = "CM_MediaDeploy";

    /** AD_Table_ID=892 */
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

    /** Column name CM_MediaDeploy_ID */
    public static final String COLUMNNAME_CM_MediaDeploy_ID = "CM_MediaDeploy_ID";

	/** Set Media Deploy.
	  * Media Deployment Log
	  */
	public void setCM_MediaDeploy_ID (int CM_MediaDeploy_ID);

	/** Get Media Deploy.
	  * Media Deployment Log
	  */
	public int getCM_MediaDeploy_ID();

    /** Column name CM_Media_ID */
    public static final String COLUMNNAME_CM_Media_ID = "CM_Media_ID";

	/** Set Media Item.
	  * Contains media content like images, flash movies etc.
	  */
	public void setCM_Media_ID (int CM_Media_ID);

	/** Get Media Item.
	  * Contains media content like images, flash movies etc.
	  */
	public int getCM_Media_ID();

	public I_CM_Media getCM_Media() throws RuntimeException;

    /** Column name CM_Media_Server_ID */
    public static final String COLUMNNAME_CM_Media_Server_ID = "CM_Media_Server_ID";

	/** Set Media Server.
	  * Media Server list to which content should get transfered
	  */
	public void setCM_Media_Server_ID (int CM_Media_Server_ID);

	/** Get Media Server.
	  * Media Server list to which content should get transfered
	  */
	public int getCM_Media_Server_ID();

	public I_CM_Media_Server getCM_Media_Server() throws RuntimeException;

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

    /** Column name IsDeployed */
    public static final String COLUMNNAME_IsDeployed = "IsDeployed";

	/** Set Deployed.
	  * Entity is deployed
	  */
	public void setIsDeployed (boolean IsDeployed);

	/** Get Deployed.
	  * Entity is deployed
	  */
	public boolean isDeployed();

    /** Column name LastSynchronized */
    public static final String COLUMNNAME_LastSynchronized = "LastSynchronized";

	/** Set Last Synchronized.
	  * Date when last synchronized
	  */
	public void setLastSynchronized (Timestamp LastSynchronized);

	/** Get Last Synchronized.
	  * Date when last synchronized
	  */
	public Timestamp getLastSynchronized();
}
