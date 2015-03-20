/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Reporte_Access
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Reporte_Access 
{

    /** TableName=EXME_Reporte_Access */
    public static final String Table_Name = "EXME_Reporte_Access";

    /** AD_Table_ID=1200422 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name EXME_Reporte_Access_ID */
    public static final String COLUMNNAME_EXME_Reporte_Access_ID = "EXME_Reporte_Access_ID";

	/** Set Report Access	  */
	public void setEXME_Reporte_Access_ID (int EXME_Reporte_Access_ID);

	/** Get Report Access	  */
	public int getEXME_Reporte_Access_ID();

    /** Column name EXME_Reporte_ID */
    public static final String COLUMNNAME_EXME_Reporte_ID = "EXME_Reporte_ID";

	/** Set Report	  */
	public void setEXME_Reporte_ID (int EXME_Reporte_ID);

	/** Get Report	  */
	public int getEXME_Reporte_ID();

	public I_EXME_Reporte getEXME_Reporte() throws RuntimeException;
}
