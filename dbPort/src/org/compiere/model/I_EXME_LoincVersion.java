/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_LoincVersion
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_LoincVersion 
{

    /** TableName=EXME_LoincVersion */
    public static final String Table_Name = "EXME_LoincVersion";

    /** AD_Table_ID=1201383 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name EXME_LoincType_ID */
    public static final String COLUMNNAME_EXME_LoincType_ID = "EXME_LoincType_ID";

	/** Set Loinc Type	  */
	public void setEXME_LoincType_ID (int EXME_LoincType_ID);

	/** Get Loinc Type	  */
	public int getEXME_LoincType_ID();

	public I_EXME_LoincType getEXME_LoincType() throws RuntimeException;

    /** Column name EXME_LoincVersion_ID */
    public static final String COLUMNNAME_EXME_LoincVersion_ID = "EXME_LoincVersion_ID";

	/** Set Loinc Version	  */
	public void setEXME_LoincVersion_ID (int EXME_LoincVersion_ID);

	/** Get Loinc Version	  */
	public int getEXME_LoincVersion_ID();

    /** Column name Stage */
    public static final String COLUMNNAME_Stage = "Stage";

	/** Set Stage	  */
	public void setStage (String Stage);

	/** Get Stage	  */
	public String getStage();
}
