/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ReglaCuestionarioDt
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ReglaCuestionarioDt 
{

    /** TableName=EXME_ReglaCuestionarioDt */
    public static final String Table_Name = "EXME_ReglaCuestionarioDt";

    /** AD_Table_ID=1200414 */
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

    /** Column name EXME_Pregunta_Lista_ID */
    public static final String COLUMNNAME_EXME_Pregunta_Lista_ID = "EXME_Pregunta_Lista_ID";

	/** Set Fixed Answer.
	  * Fixed answer for the question in the clinic questionnaire
	  */
	public void setEXME_Pregunta_Lista_ID (int EXME_Pregunta_Lista_ID);

	/** Get Fixed Answer.
	  * Fixed answer for the question in the clinic questionnaire
	  */
	public int getEXME_Pregunta_Lista_ID();

	public I_EXME_Pregunta_Lista getEXME_Pregunta_Lista() throws RuntimeException;

    /** Column name EXME_ReglaCuestionarioDt_ID */
    public static final String COLUMNNAME_EXME_ReglaCuestionarioDt_ID = "EXME_ReglaCuestionarioDt_ID";

	/** Set Quiz Rules Detail	  */
	public void setEXME_ReglaCuestionarioDt_ID (int EXME_ReglaCuestionarioDt_ID);

	/** Get Quiz Rules Detail	  */
	public int getEXME_ReglaCuestionarioDt_ID();

    /** Column name EXME_ReglaCuestionario_ID */
    public static final String COLUMNNAME_EXME_ReglaCuestionario_ID = "EXME_ReglaCuestionario_ID";

	/** Set Quiz Rules	  */
	public void setEXME_ReglaCuestionario_ID (int EXME_ReglaCuestionario_ID);

	/** Get Quiz Rules	  */
	public int getEXME_ReglaCuestionario_ID();

	public I_EXME_ReglaCuestionario getEXME_ReglaCuestionario() throws RuntimeException;

    /** Column name Operator */
    public static final String COLUMNNAME_Operator = "Operator";

	/** Set Operator	  */
	public void setOperator (String Operator);

	/** Get Operator	  */
	public String getOperator();

    /** Column name Respuesta */
    public static final String COLUMNNAME_Respuesta = "Respuesta";

	/** Set Answer.
	  * Answer
	  */
	public void setRespuesta (String Respuesta);

	/** Get Answer.
	  * Answer
	  */
	public String getRespuesta();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
