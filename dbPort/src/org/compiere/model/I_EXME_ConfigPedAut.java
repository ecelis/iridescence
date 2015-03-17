/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigPedAut
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfigPedAut 
{

    /** TableName=EXME_ConfigPedAut */
    public static final String Table_Name = "EXME_ConfigPedAut";

    /** AD_Table_ID=1200265 */
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

    /** Column name EXME_ConfigPedAut_ID */
    public static final String COLUMNNAME_EXME_ConfigPedAut_ID = "EXME_ConfigPedAut_ID";

	/** Set Automatic Product Request Configuration.
	  * Automatic Product Request Configuration
	  */
	public void setEXME_ConfigPedAut_ID (int EXME_ConfigPedAut_ID);

	/** Get Automatic Product Request Configuration.
	  * Automatic Product Request Configuration
	  */
	public int getEXME_ConfigPedAut_ID();

    /** Column name EXME_TipoProd_ID */
    public static final String COLUMNNAME_EXME_TipoProd_ID = "EXME_TipoProd_ID";

	/** Set Product Subtype.
	  * Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID);

	/** Get Product Subtype.
	  * Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID();
}
