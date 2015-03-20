/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigDocType
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ConfigDocType 
{

    /** TableName=EXME_ConfigDocType */
    public static final String Table_Name = "EXME_ConfigDocType";

    /** AD_Table_ID=1200863 */
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

    /** Column name C_DocTypeVacuna_ID */
    public static final String COLUMNNAME_C_DocTypeVacuna_ID = "C_DocTypeVacuna_ID";

	/** Set Document Type for Vaccine Application	  */
	public void setC_DocTypeVacuna_ID (int C_DocTypeVacuna_ID);

	/** Get Document Type for Vaccine Application	  */
	public int getC_DocTypeVacuna_ID();

    /** Column name EXME_ConfigDocType_ID */
    public static final String COLUMNNAME_EXME_ConfigDocType_ID = "EXME_ConfigDocType_ID";

	/** Set Document Type Configuration	  */
	public void setEXME_ConfigDocType_ID (int EXME_ConfigDocType_ID);

	/** Get Document Type Configuration	  */
	public int getEXME_ConfigDocType_ID();
}
