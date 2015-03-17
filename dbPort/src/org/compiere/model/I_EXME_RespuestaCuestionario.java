/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_RespuestaCuestionario
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_RespuestaCuestionario 
{

    /** TableName=EXME_RespuestaCuestionario */
    public static final String Table_Name = "EXME_RespuestaCuestionario";

    /** AD_Table_ID=1201267 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

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

    /** Column name EXME_Ejecucion_Cuest_ID */
    public static final String COLUMNNAME_EXME_Ejecucion_Cuest_ID = "EXME_Ejecucion_Cuest_ID";

	/** Set Execution of Questionnaire	  */
	public void setEXME_Ejecucion_Cuest_ID (int EXME_Ejecucion_Cuest_ID);

	/** Get Execution of Questionnaire	  */
	public int getEXME_Ejecucion_Cuest_ID();

	public I_EXME_Ejecucion_Cuest getEXME_Ejecucion_Cuest() throws RuntimeException;

    /** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID);

	/** Get Order Set	  */
	public int getEXME_OrderSet_ID();

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException;

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

    /** Column name EXME_RespuestaCuestionario_ID */
    public static final String COLUMNNAME_EXME_RespuestaCuestionario_ID = "EXME_RespuestaCuestionario_ID";

	/** Set Anwser	  */
	public void setEXME_RespuestaCuestionario_ID (int EXME_RespuestaCuestionario_ID);

	/** Get Anwser	  */
	public int getEXME_RespuestaCuestionario_ID();

    /** Column name FileContent */
    public static final String COLUMNNAME_FileContent = "FileContent";

	/** Set File Content	  */
	public void setFileContent (byte[] FileContent);

	/** Get File Content	  */
	public byte[] getFileContent();

    /** Column name IsPhysician */
    public static final String COLUMNNAME_IsPhysician = "IsPhysician";

	/** Set Physician.
	  * Indicates whether is a physician or not
	  */
	public void setIsPhysician (boolean IsPhysician);

	/** Get Physician.
	  * Indicates whether is a physician or not
	  */
	public boolean isPhysician();

    /** Column name Multiple */
    public static final String COLUMNNAME_Multiple = "Multiple";

	/** Set Multiple	  */
	public void setMultiple (boolean Multiple);

	/** Get Multiple	  */
	public boolean isMultiple();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

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

    /** Column name TextBinary */
    public static final String COLUMNNAME_TextBinary = "TextBinary";

	/** Set Binary Text	  */
	public void setTextBinary (String TextBinary);

	/** Get Binary Text	  */
	public String getTextBinary();
}
