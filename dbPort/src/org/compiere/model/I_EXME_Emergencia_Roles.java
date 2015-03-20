/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Emergencia_Roles
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Emergencia_Roles 
{

    /** TableName=EXME_Emergencia_Roles */
    public static final String Table_Name = "EXME_Emergencia_Roles";

    /** AD_Table_ID=1200851 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name AD_Role_ID */
    public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

	/** Set Role.
	  * Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID);

	/** Get Role.
	  * Responsibility Role
	  */
	public int getAD_Role_ID();

	public I_AD_Role getAD_Role() throws RuntimeException;

    /** Column name EXME_Emergencia_ID */
    public static final String COLUMNNAME_EXME_Emergencia_ID = "EXME_Emergencia_ID";

	/** Set Emergency	  */
	public void setEXME_Emergencia_ID (int EXME_Emergencia_ID);

	/** Get Emergency	  */
	public int getEXME_Emergencia_ID();

	public I_EXME_Emergencia getEXME_Emergencia() throws RuntimeException;

    /** Column name EXME_Emergencia_Roles_ID */
    public static final String COLUMNNAME_EXME_Emergencia_Roles_ID = "EXME_Emergencia_Roles_ID";

	/** Set Emergency Roles	  */
	public void setEXME_Emergencia_Roles_ID (int EXME_Emergencia_Roles_ID);

	/** Get Emergency Roles	  */
	public int getEXME_Emergencia_Roles_ID();
}
