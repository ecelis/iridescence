/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RecursoEducativoDiag
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_RecursoEducativoDiag 
{

    /** TableName=EXME_RecursoEducativoDiag */
    public static final String Table_Name = "EXME_RecursoEducativoDiag";

    /** AD_Table_ID=1201028 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name EXME_RecursoEducativoDiag_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativoDiag_ID = "EXME_RecursoEducativoDiag_ID";

	/** Set Diagnostic Education Resource	  */
	public void setEXME_RecursoEducativoDiag_ID (int EXME_RecursoEducativoDiag_ID);

	/** Get Diagnostic Education Resource	  */
	public int getEXME_RecursoEducativoDiag_ID();

    /** Column name EXME_RecursoEducativo_ID */
    public static final String COLUMNNAME_EXME_RecursoEducativo_ID = "EXME_RecursoEducativo_ID";

	/** Set Education Resource	  */
	public void setEXME_RecursoEducativo_ID (int EXME_RecursoEducativo_ID);

	/** Get Education Resource	  */
	public int getEXME_RecursoEducativo_ID();

	public I_EXME_RecursoEducativo getEXME_RecursoEducativo() throws RuntimeException;
}
