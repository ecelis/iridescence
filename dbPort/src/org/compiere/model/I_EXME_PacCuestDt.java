/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PacCuestDt
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_PacCuestDt 
{

    /** TableName=EXME_PacCuestDt */
    public static final String Table_Name = "EXME_PacCuestDt";

    /** AD_Table_ID=1200910 */
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

    /** Column name EXME_PacCuestDt_ID */
    public static final String COLUMNNAME_EXME_PacCuestDt_ID = "EXME_PacCuestDt_ID";

	/** Set Detail of Patient Questionnaire	  */
	public void setEXME_PacCuestDt_ID (int EXME_PacCuestDt_ID);

	/** Get Detail of Patient Questionnaire	  */
	public int getEXME_PacCuestDt_ID();

    /** Column name EXME_PacCuest_ID */
    public static final String COLUMNNAME_EXME_PacCuest_ID = "EXME_PacCuest_ID";

	/** Set Patient Questionnaire	  */
	public void setEXME_PacCuest_ID (int EXME_PacCuest_ID);

	/** Get Patient Questionnaire	  */
	public int getEXME_PacCuest_ID();

	public I_EXME_PacCuest getEXME_PacCuest() throws RuntimeException;

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

	public I_EXME_Pregunta getEXME_Pregunta() throws RuntimeException;

    /** Column name EXME_TipoPregunta_ID */
    public static final String COLUMNNAME_EXME_TipoPregunta_ID = "EXME_TipoPregunta_ID";

	/** Set Type of Question.
	  * Type of Question
	  */
	public void setEXME_TipoPregunta_ID (int EXME_TipoPregunta_ID);

	/** Get Type of Question.
	  * Type of Question
	  */
	public int getEXME_TipoPregunta_ID();

	public I_EXME_TipoPregunta getEXME_TipoPregunta() throws RuntimeException;

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (int Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public int getSecuencia();
}
