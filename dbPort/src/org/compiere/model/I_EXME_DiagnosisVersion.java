/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DiagnosisVersion
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_DiagnosisVersion 
{

    /** TableName=EXME_DiagnosisVersion */
    public static final String Table_Name = "EXME_DiagnosisVersion";

    /** AD_Table_ID=1201380 */
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

    /** Column name EXME_DiagnosisType_ID */
    public static final String COLUMNNAME_EXME_DiagnosisType_ID = "EXME_DiagnosisType_ID";

	/** Set Diagnosis Type	  */
	public void setEXME_DiagnosisType_ID (int EXME_DiagnosisType_ID);

	/** Get Diagnosis Type	  */
	public int getEXME_DiagnosisType_ID();

	public I_EXME_DiagnosisType getEXME_DiagnosisType() throws RuntimeException;

    /** Column name EXME_DiagnosisVersion_ID */
    public static final String COLUMNNAME_EXME_DiagnosisVersion_ID = "EXME_DiagnosisVersion_ID";

	/** Set Diagnosis Version	  */
	public void setEXME_DiagnosisVersion_ID (int EXME_DiagnosisVersion_ID);

	/** Get Diagnosis Version	  */
	public int getEXME_DiagnosisVersion_ID();

    /** Column name Stage */
    public static final String COLUMNNAME_Stage = "Stage";

	/** Set Stage	  */
	public void setStage (String Stage);

	/** Get Stage	  */
	public String getStage();
}
