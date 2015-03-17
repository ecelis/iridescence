/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_DiagnosisTypeDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_DiagnosisTypeDet 
{

    /** TableName=EXME_DiagnosisTypeDet */
    public static final String Table_Name = "EXME_DiagnosisTypeDet";

    /** AD_Table_ID=1201102 */
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

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (String Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public String getCode();

    /** Column name EXME_DiagnosisTypeDet_ID */
    public static final String COLUMNNAME_EXME_DiagnosisTypeDet_ID = "EXME_DiagnosisTypeDet_ID";

	/** Set Diagnosis Type Detail	  */
	public void setEXME_DiagnosisTypeDet_ID (int EXME_DiagnosisTypeDet_ID);

	/** Get Diagnosis Type Detail	  */
	public int getEXME_DiagnosisTypeDet_ID();

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

    /** Column name EXME_Diagnostico_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_ID = "EXME_Diagnostico_ID";

	/** Set Diagnosis.
	  * Diagnosis
	  */
	public void setEXME_Diagnostico_ID (int EXME_Diagnostico_ID);

	/** Get Diagnosis.
	  * Diagnosis
	  */
	public int getEXME_Diagnostico_ID();
}
