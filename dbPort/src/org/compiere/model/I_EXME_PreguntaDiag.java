/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PreguntaDiag
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_PreguntaDiag 
{

    /** TableName=EXME_PreguntaDiag */
    public static final String Table_Name = "EXME_PreguntaDiag";

    /** AD_Table_ID=1201182 */
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

    /** Column name EXME_PreguntaDiag_ID */
    public static final String COLUMNNAME_EXME_PreguntaDiag_ID = "EXME_PreguntaDiag_ID";

	/** Set Diagnostic Question.
	  * Diagnostic Question
	  */
	public void setEXME_PreguntaDiag_ID (int EXME_PreguntaDiag_ID);

	/** Get Diagnostic Question.
	  * Diagnostic Question
	  */
	public int getEXME_PreguntaDiag_ID();

    /** Column name EXME_Pregunta_ID */
    public static final String COLUMNNAME_EXME_Pregunta_ID = "EXME_Pregunta_ID";

	/** Set Question.
	  * Question
	  */
	public void setEXME_Pregunta_ID (int EXME_Pregunta_ID);

	/** Get Question.
	  * Question
	  */
	public int getEXME_Pregunta_ID();
}
