/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ColumnasAut
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ColumnasAut 
{

    /** TableName=EXME_ColumnasAut */
    public static final String Table_Name = "EXME_ColumnasAut";

    /** AD_Table_ID=1200523 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public I_AD_Column getAD_Column() throws RuntimeException;

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

    /** Column name EXME_ColumnasAut_ID */
    public static final String COLUMNNAME_EXME_ColumnasAut_ID = "EXME_ColumnasAut_ID";

	/** Set Columns to Authorize.
	  * Columns that are going away to authorize
	  */
	public void setEXME_ColumnasAut_ID (int EXME_ColumnasAut_ID);

	/** Get Columns to Authorize.
	  * Columns that are going away to authorize
	  */
	public int getEXME_ColumnasAut_ID();

    /** Column name EXME_TablasAut_ID */
    public static final String COLUMNNAME_EXME_TablasAut_ID = "EXME_TablasAut_ID";

	/** Set Tables to Authorize.
	  * Tables that are going away to authorize
	  */
	public void setEXME_TablasAut_ID (int EXME_TablasAut_ID);

	/** Get Tables to Authorize.
	  * Tables that are going away to authorize
	  */
	public int getEXME_TablasAut_ID();

	public I_EXME_TablasAut getEXME_TablasAut() throws RuntimeException;

    /** Column name Show */
    public static final String COLUMNNAME_Show = "Show";

	/** Set Shown.
	  * Shown
	  */
	public void setShow (boolean Show);

	/** Get Shown.
	  * Shown
	  */
	public boolean isShow();
}
