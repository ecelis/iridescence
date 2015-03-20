/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RD_Role_Access
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_RD_Role_Access 
{

    /** TableName=EXME_RD_Role_Access */
    public static final String Table_Name = "EXME_RD_Role_Access";

    /** AD_Table_ID=1201274 */
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

    /** Column name EXME_ReportDesigner_ID */
    public static final String COLUMNNAME_EXME_ReportDesigner_ID = "EXME_ReportDesigner_ID";

	/** Set Report Designer ID.
	  * Report Designer ID
	  */
	public void setEXME_ReportDesigner_ID (int EXME_ReportDesigner_ID);

	/** Get Report Designer ID.
	  * Report Designer ID
	  */
	public int getEXME_ReportDesigner_ID();

	public I_EXME_ReportDesigner getEXME_ReportDesigner() throws RuntimeException;
}
