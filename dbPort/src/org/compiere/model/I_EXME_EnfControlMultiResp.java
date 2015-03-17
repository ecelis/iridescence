/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EnfControlMultiResp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EnfControlMultiResp 
{

    /** TableName=EXME_EnfControlMultiResp */
    public static final String Table_Name = "EXME_EnfControlMultiResp";

    /** AD_Table_ID=1200515 */
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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

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

	public I_EXME_Cuestionario getEXME_Cuestionario() throws RuntimeException;

    /** Column name EXME_EnfControlMultiResp_ID */
    public static final String COLUMNNAME_EXME_EnfControlMultiResp_ID = "EXME_EnfControlMultiResp_ID";

	/** Set multi answers	  */
	public void setEXME_EnfControlMultiResp_ID (int EXME_EnfControlMultiResp_ID);

	/** Get multi answers	  */
	public int getEXME_EnfControlMultiResp_ID();

    /** Column name EXME_EnfControlResp_ID */
    public static final String COLUMNNAME_EXME_EnfControlResp_ID = "EXME_EnfControlResp_ID";

	/** Set Answer of Controlled Disease	  */
	public void setEXME_EnfControlResp_ID (int EXME_EnfControlResp_ID);

	/** Get Answer of Controlled Disease	  */
	public int getEXME_EnfControlResp_ID();

	public I_EXME_EnfControlResp getEXME_EnfControlResp() throws RuntimeException;

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

    /** Column name Fecha */
    public static final String COLUMNNAME_Fecha = "Fecha";

	/** Set Date.
	  * Date
	  */
	public void setFecha (Timestamp Fecha);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFecha();

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

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

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public BigDecimal getSecuencia();
}
