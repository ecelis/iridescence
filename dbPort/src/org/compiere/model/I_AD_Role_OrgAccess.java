/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Role_OrgAccess
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_AD_Role_OrgAccess 
{

    /** TableName=AD_Role_OrgAccess */
    public static final String Table_Name = "AD_Role_OrgAccess";

    /** AD_Table_ID=422 */
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

    /** Column name IsReadOnly */
    public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";

	/** Set Read Only.
	  * Field is read only
	  */
	public void setIsReadOnly (boolean IsReadOnly);

	/** Get Read Only.
	  * Field is read only
	  */
	public boolean isReadOnly();
}
