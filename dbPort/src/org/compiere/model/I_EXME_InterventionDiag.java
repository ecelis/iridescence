/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_InterventionDiag
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_InterventionDiag 
{

    /** TableName=EXME_InterventionDiag */
    public static final String Table_Name = "EXME_InterventionDiag";

    /** AD_Table_ID=1201147 */
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

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException;

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set Intervention.
	  * Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get Intervention.
	  * Intervention
	  */
	public int getEXME_Intervencion_ID();

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException;

    /** Column name EXME_InterventionDiag_ID */
    public static final String COLUMNNAME_EXME_InterventionDiag_ID = "EXME_InterventionDiag_ID";

	/** Set Intervention-Diagnostic Identifier	  */
	public void setEXME_InterventionDiag_ID (int EXME_InterventionDiag_ID);

	/** Get Intervention-Diagnostic Identifier	  */
	public int getEXME_InterventionDiag_ID();
}
