/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Ind_Diag
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_Ind_Diag 
{

    /** TableName=EXME_Ind_Diag */
    public static final String Table_Name = "EXME_Ind_Diag";

    /** AD_Table_ID=1201200 */
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

    /** Column name EXME_ActPacienteDiag_ID */
    public static final String COLUMNNAME_EXME_ActPacienteDiag_ID = "EXME_ActPacienteDiag_ID";

	/** Set Diagnostic.
	  * Diagnostic
	  */
	public void setEXME_ActPacienteDiag_ID (int EXME_ActPacienteDiag_ID);

	/** Get Diagnostic.
	  * Diagnostic
	  */
	public int getEXME_ActPacienteDiag_ID();

	public I_EXME_ActPacienteDiag getEXME_ActPacienteDiag() throws RuntimeException;

    /** Column name EXME_ActPacienteInd_ID */
    public static final String COLUMNNAME_EXME_ActPacienteInd_ID = "EXME_ActPacienteInd_ID";

	/** Set Indications Patient Activity.
	  * Indications Patient Activity
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID);

	/** Get Indications Patient Activity.
	  * Indications Patient Activity
	  */
	public int getEXME_ActPacienteInd_ID();

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException;

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (BigDecimal Sequence);

	/** Get Sequence	  */
	public BigDecimal getSequence();
}
