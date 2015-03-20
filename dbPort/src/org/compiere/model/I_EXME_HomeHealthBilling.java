/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_HomeHealthBilling
 *  @author Generated Class 
 *  @version Release 1.2.0
 */
public interface I_EXME_HomeHealthBilling 
{

    /** TableName=EXME_HomeHealthBilling */
    public static final String Table_Name = "EXME_HomeHealthBilling";

    /** AD_Table_ID=1201213 */
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

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name EXME_HomeHealthBilling_ID */
    public static final String COLUMNNAME_EXME_HomeHealthBilling_ID = "EXME_HomeHealthBilling_ID";

	/** Set Home Health Billing	  */
	public void setEXME_HomeHealthBilling_ID (int EXME_HomeHealthBilling_ID);

	/** Get Home Health Billing	  */
	public int getEXME_HomeHealthBilling_ID();
}
