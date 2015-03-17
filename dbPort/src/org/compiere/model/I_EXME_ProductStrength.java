/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProductStrength
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ProductStrength 
{

    /** TableName=EXME_ProductStrength */
    public static final String Table_Name = "EXME_ProductStrength";

    /** AD_Table_ID=1201145 */
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

    /** Column name EXME_ProductStrength_ID */
    public static final String COLUMNNAME_EXME_ProductStrength_ID = "EXME_ProductStrength_ID";

	/** Set Product Strength ID	  */
	public void setEXME_ProductStrength_ID (int EXME_ProductStrength_ID);

	/** Get Product Strength ID	  */
	public int getEXME_ProductStrength_ID();

    /** Column name STRENGTH_DESCRIPTION */
    public static final String COLUMNNAME_STRENGTH_DESCRIPTION = "STRENGTH_DESCRIPTION";

	/** Set STRENGTH_DESCRIPTION	  */
	public void setSTRENGTH_DESCRIPTION (String STRENGTH_DESCRIPTION);

	/** Get STRENGTH_DESCRIPTION	  */
	public String getSTRENGTH_DESCRIPTION();

    /** Column name STRENGTH_ID */
    public static final String COLUMNNAME_STRENGTH_ID = "STRENGTH_ID";

	/** Set STRENGTH_ID	  */
	public void setSTRENGTH_ID (int STRENGTH_ID);

	/** Get STRENGTH_ID	  */
	public int getSTRENGTH_ID();
}
