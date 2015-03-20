/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_EnfControlResp
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_EnfControlResp 
{

    /** TableName=EXME_EnfControlResp */
    public static final String Table_Name = "EXME_EnfControlResp";

    /** AD_Table_ID=1200513 */
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

    /** Column name Confidencial */
    public static final String COLUMNNAME_Confidencial = "Confidencial";

	/** Set Confidential.
	  * Indicates the level of confidenciality
	  */
	public void setConfidencial (String Confidencial);

	/** Get Confidential.
	  * Indicates the level of confidenciality
	  */
	public String getConfidencial();

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

    /** Column name EsNotaMedica */
    public static final String COLUMNNAME_EsNotaMedica = "EsNotaMedica";

	/** Set Is Medical Note	  */
	public void setEsNotaMedica (boolean EsNotaMedica);

	/** Get Is Medical Note	  */
	public boolean isEsNotaMedica();

    /** Column name EXME_CitaMedica_ID */
    public static final String COLUMNNAME_EXME_CitaMedica_ID = "EXME_CitaMedica_ID";

	/** Set Medical Appointment.
	  * Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID);

	/** Get Medical Appointment.
	  * Medical appointment
	  */
	public int getEXME_CitaMedica_ID();

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException;

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

    /** Column name EXME_EnfControlada_ID */
    public static final String COLUMNNAME_EXME_EnfControlada_ID = "EXME_EnfControlada_ID";

	/** Set Controlled Illness.
	  * Controlled Illness
	  */
	public void setEXME_EnfControlada_ID (int EXME_EnfControlada_ID);

	/** Get Controlled Illness.
	  * Controlled Illness
	  */
	public int getEXME_EnfControlada_ID();

	public I_EXME_EnfControlada getEXME_EnfControlada() throws RuntimeException;

    /** Column name EXME_EnfControlResp_ID */
    public static final String COLUMNNAME_EXME_EnfControlResp_ID = "EXME_EnfControlResp_ID";

	/** Set Answer of Controlled Disease	  */
	public void setEXME_EnfControlResp_ID (int EXME_EnfControlResp_ID);

	/** Get Answer of Controlled Disease	  */
	public int getEXME_EnfControlResp_ID();

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Station.
	  * Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Station.
	  * Service Station
	  */
	public int getEXME_EstServ_ID();

	public I_EXME_EstServ getEXME_EstServ() throws RuntimeException;

    /** Column name EXME_Medico_ID */
    public static final String COLUMNNAME_EXME_Medico_ID = "EXME_Medico_ID";

	/** Set Doctor.
	  * Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID);

	/** Get Doctor.
	  * Doctor
	  */
	public int getEXME_Medico_ID();

	public I_EXME_Medico getEXME_Medico() throws RuntimeException;

    /** Column name EXME_Paciente_ID */
    public static final String COLUMNNAME_EXME_Paciente_ID = "EXME_Paciente_ID";

	/** Set Patient.
	  * Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID);

	/** Get Patient.
	  * Patient
	  */
	public int getEXME_Paciente_ID();

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException;

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

    /** Column name ImagenBinary */
    public static final String COLUMNNAME_ImagenBinary = "ImagenBinary";

	/** Set Binary Image	  */
	public void setImagenBinary (byte[] ImagenBinary);

	/** Get Binary Image	  */
	public byte[] getImagenBinary();

    /** Column name Pregunta_Lista_Value */
    public static final String COLUMNNAME_Pregunta_Lista_Value = "Pregunta_Lista_Value";

	/** Set Question's Fixed Answer Value.
	  * Question's Fixed Answer Value
	  */
	public void setPregunta_Lista_Value (String Pregunta_Lista_Value);

	/** Get Question's Fixed Answer Value.
	  * Question's Fixed Answer Value
	  */
	public String getPregunta_Lista_Value();

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

    /** Column name RutaImagen */
    public static final String COLUMNNAME_RutaImagen = "RutaImagen";

	/** Set Image Route.
	  * Image Route
	  */
	public void setRutaImagen (String RutaImagen);

	/** Get Image Route.
	  * Image Route
	  */
	public String getRutaImagen();

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

    /** Column name TextBinary */
    public static final String COLUMNNAME_TextBinary = "TextBinary";

	/** Set Binary Text	  */
	public void setTextBinary (String TextBinary);

	/** Get Binary Text	  */
	public String getTextBinary();
}
