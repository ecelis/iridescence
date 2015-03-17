/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PatientRel_Diag
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PatientRel_Diag 
{

    /** TableName=EXME_PatientRel_Diag */
    public static final String Table_Name = "EXME_PatientRel_Diag";

    /** AD_Table_ID=1201370 */
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

    /** Column name EXME_PatientRel_Diag_ID */
    public static final String COLUMNNAME_EXME_PatientRel_Diag_ID = "EXME_PatientRel_Diag_ID";

	/** Set Diagnoses of the Patient's Relative	  */
	public void setEXME_PatientRel_Diag_ID (int EXME_PatientRel_Diag_ID);

	/** Get Diagnoses of the Patient's Relative	  */
	public int getEXME_PatientRel_Diag_ID();

    /** Column name EXME_PatientRel_ID */
    public static final String COLUMNNAME_EXME_PatientRel_ID = "EXME_PatientRel_ID";

	/** Set Patient's Relative	  */
	public void setEXME_PatientRel_ID (int EXME_PatientRel_ID);

	/** Get Patient's Relative	  */
	public int getEXME_PatientRel_ID();
}
