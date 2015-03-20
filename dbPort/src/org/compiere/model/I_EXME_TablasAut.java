/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TablasAut
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_TablasAut 
{

    /** TableName=EXME_TablasAut */
    public static final String Table_Name = "EXME_TablasAut";

    /** AD_Table_ID=1200524 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

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
}
