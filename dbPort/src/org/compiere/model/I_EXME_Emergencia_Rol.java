/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Emergencia_Rol
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Emergencia_Rol 
{

    /** TableName=EXME_Emergencia_Rol */
    public static final String Table_Name = "EXME_Emergencia_Rol";

    /** AD_Table_ID=1200850 */
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

    /** Column name EXME_Emergencia_Rol_ID */
    public static final String COLUMNNAME_EXME_Emergencia_Rol_ID = "EXME_Emergencia_Rol_ID";

	/** Set Emergency Role	  */
	public void setEXME_Emergencia_Rol_ID (int EXME_Emergencia_Rol_ID);

	/** Get Emergency Role	  */
	public int getEXME_Emergencia_Rol_ID();
}
