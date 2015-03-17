/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for U_RoleMenu
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_U_RoleMenu 
{

    /** TableName=U_RoleMenu */
    public static final String Table_Name = "U_RoleMenu";

    /** AD_Table_ID=1200810 */
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

    /** Column name U_RoleMenu_ID */
    public static final String COLUMNNAME_U_RoleMenu_ID = "U_RoleMenu_ID";

	/** Set Role Menu	  */
	public void setU_RoleMenu_ID (int U_RoleMenu_ID);

	/** Get Role Menu	  */
	public int getU_RoleMenu_ID();

    /** Column name U_WebMenu_ID */
    public static final String COLUMNNAME_U_WebMenu_ID = "U_WebMenu_ID";

	/** Set Web Menu	  */
	public void setU_WebMenu_ID (int U_WebMenu_ID);

	/** Get Web Menu	  */
	public int getU_WebMenu_ID();

	public I_U_WebMenu getU_WebMenu() throws RuntimeException;
}
