/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_AreaDestino
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_AreaDestino 
{

    /** TableName=EXME_AreaDestino */
    public static final String Table_Name = "EXME_AreaDestino";

    /** AD_Table_ID=1201374 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name AlertMessage */
    public static final String COLUMNNAME_AlertMessage = "AlertMessage";

	/** Set Alert Message.
	  * Alert Message
	  */
	public void setAlertMessage (String AlertMessage);

	/** Get Alert Message.
	  * Alert Message
	  */
	public String getAlertMessage();

    /** Column name EXME_AreaDestino_ID */
    public static final String COLUMNNAME_EXME_AreaDestino_ID = "EXME_AreaDestino_ID";

	/** Set To Service	  */
	public void setEXME_AreaDestino_ID (int EXME_AreaDestino_ID);

	/** Get To Service	  */
	public int getEXME_AreaDestino_ID();

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Service.
	  * Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Service.
	  * Service
	  */
	public int getEXME_Area_ID();

    /** Column name EXME_AreaTo_ID */
    public static final String COLUMNNAME_EXME_AreaTo_ID = "EXME_AreaTo_ID";

	/** Set To Service	  */
	public void setEXME_AreaTo_ID (int EXME_AreaTo_ID);

	/** Get To Service	  */
	public int getEXME_AreaTo_ID();

    /** Column name RequireAuthentication */
    public static final String COLUMNNAME_RequireAuthentication = "RequireAuthentication";

	/** Set Require Authentication.
	  * Indicates that this patient type change will require authentication
	  */
	public void setRequireAuthentication (boolean RequireAuthentication);

	/** Get Require Authentication.
	  * Indicates that this patient type change will require authentication
	  */
	public boolean isRequireAuthentication();
}
