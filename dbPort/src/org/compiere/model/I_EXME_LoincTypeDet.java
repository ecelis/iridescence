/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_LoincTypeDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_LoincTypeDet 
{

    /** TableName=EXME_LoincTypeDet */
    public static final String Table_Name = "EXME_LoincTypeDet";

    /** AD_Table_ID=1201114 */
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

    /** Column name EXME_Loinc_ID */
    public static final String COLUMNNAME_EXME_Loinc_ID = "EXME_Loinc_ID";

	/** Set LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID);

	/** Get LOINC Code	  */
	public int getEXME_Loinc_ID();

	public I_EXME_Loinc getEXME_Loinc() throws RuntimeException;

    /** Column name EXME_LoincTypeDet_ID */
    public static final String COLUMNNAME_EXME_LoincTypeDet_ID = "EXME_LoincTypeDet_ID";

	/** Set Loinc Type Detail	  */
	public void setEXME_LoincTypeDet_ID (int EXME_LoincTypeDet_ID);

	/** Get Loinc Type Detail	  */
	public int getEXME_LoincTypeDet_ID();

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
}
