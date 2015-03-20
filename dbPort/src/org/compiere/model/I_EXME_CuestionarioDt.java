/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CuestionarioDt
 *  @author Generated Class 
 *  @version Release 1.1.2
 */
public interface I_EXME_CuestionarioDt 
{

    /** TableName=EXME_CuestionarioDt */
    public static final String Table_Name = "EXME_CuestionarioDt";

    /** AD_Table_ID=1000021 */
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

    /** Column name EXME_CuestionarioDt_ID */
    public static final String COLUMNNAME_EXME_CuestionarioDt_ID = "EXME_CuestionarioDt_ID";

	/** Set DT Questinnaire.
	  * DT Questionnaire
	  */
	public void setEXME_CuestionarioDt_ID (int EXME_CuestionarioDt_ID);

	/** Get DT Questinnaire.
	  * DT Questionnaire
	  */
	public int getEXME_CuestionarioDt_ID();

    /** Column name EXME_Cuestionario_ID */
    public static final String COLUMNNAME_EXME_Cuestionario_ID = "EXME_Cuestionario_ID";

	/** Set Questionnaire.
	  * Questionnaire
	  */
	public void setEXME_Cuestionario_ID (int EXME_Cuestionario_ID);

	/** Get Questionnaire.
	  * Questionnaire
	  */
	public int getEXME_Cuestionario_ID();

    /** Column name EXME_CuestTitulo_ID */
    public static final String COLUMNNAME_EXME_CuestTitulo_ID = "EXME_CuestTitulo_ID";

	/** Set Title	  */
	public void setEXME_CuestTitulo_ID (int EXME_CuestTitulo_ID);

	/** Get Title	  */
	public int getEXME_CuestTitulo_ID();

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

    /** Column name Obligatoria */
    public static final String COLUMNNAME_Obligatoria = "Obligatoria";

	/** Set Mandatory.
	  * Mandatory
	  */
	public void setObligatoria (boolean Obligatoria);

	/** Get Mandatory.
	  * Mandatory
	  */
	public boolean isObligatoria();

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
