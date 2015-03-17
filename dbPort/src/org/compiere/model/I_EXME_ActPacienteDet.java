/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ActPacienteDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ActPacienteDet 
{

    /** TableName=EXME_ActPacienteDet */
    public static final String Table_Name = "EXME_ActPacienteDet";

    /** AD_Table_ID=1000086 */
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

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name EsNotaMedica */
    public static final String COLUMNNAME_EsNotaMedica = "EsNotaMedica";

	/** Set Is Medical Note	  */
	public void setEsNotaMedica (boolean EsNotaMedica);

	/** Get Is Medical Note	  */
	public boolean isEsNotaMedica();

    /** Column name EstadoSalud */
    public static final String COLUMNNAME_EstadoSalud = "EstadoSalud";

	/** Set Health 	  */
	public void setEstadoSalud (String EstadoSalud);

	/** Get Health 	  */
	public String getEstadoSalud();

    /** Column name EXME_ActPacienteDet_ID */
    public static final String COLUMNNAME_EXME_ActPacienteDet_ID = "EXME_ActPacienteDet_ID";

	/** Set Detail Patient Activity.
	  * Detail Patient Actvity
	  */
	public void setEXME_ActPacienteDet_ID (int EXME_ActPacienteDet_ID);

	/** Get Detail Patient Activity.
	  * Detail Patient Actvity
	  */
	public int getEXME_ActPacienteDet_ID();

    /** Column name EXME_ActPaciente_ID */
    public static final String COLUMNNAME_EXME_ActPaciente_ID = "EXME_ActPaciente_ID";

	/** Set Patient Activity.
	  * Patient Activity
	  */
	public void setEXME_ActPaciente_ID (int EXME_ActPaciente_ID);

	/** Get Patient Activity.
	  * Patient Activity
	  */
	public int getEXME_ActPaciente_ID();

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

    /** Column name EXME_EncounterForms_ID */
    public static final String COLUMNNAME_EXME_EncounterForms_ID = "EXME_EncounterForms_ID";

	/** Set Encounter Forms.
	  * Encounter Forms
	  */
	public void setEXME_EncounterForms_ID (int EXME_EncounterForms_ID);

	/** Get Encounter Forms.
	  * Encounter Forms
	  */
	public int getEXME_EncounterForms_ID();

	public I_EXME_EncounterForms getEXME_EncounterForms() throws RuntimeException;

    /** Column name EXME_Enfermeria_ID */
    public static final String COLUMNNAME_EXME_Enfermeria_ID = "EXME_Enfermeria_ID";

	/** Set Infirmary staff.
	  * Infirmary staff
	  */
	public void setEXME_Enfermeria_ID (int EXME_Enfermeria_ID);

	/** Get Infirmary staff.
	  * Infirmary staff
	  */
	public int getEXME_Enfermeria_ID();

	public I_EXME_Enfermeria getEXME_Enfermeria() throws RuntimeException;

    /** Column name EXME_Especialidad_ID */
    public static final String COLUMNNAME_EXME_Especialidad_ID = "EXME_Especialidad_ID";

	/** Set Specialty.
	  * Specialty
	  */
	public void setEXME_Especialidad_ID (int EXME_Especialidad_ID);

	/** Get Specialty.
	  * Specialty
	  */
	public int getEXME_Especialidad_ID();

	public I_EXME_Especialidad getEXME_Especialidad() throws RuntimeException;

    /** Column name EXME_EstServ_ID */
    public static final String COLUMNNAME_EXME_EstServ_ID = "EXME_EstServ_ID";

	/** Set Service Unit.
	  * Service Unit
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID);

	/** Get Service Unit.
	  * Service Unit
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

    /** Column name EXME_Tratamientos_Sesion_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Sesion_ID = "EXME_Tratamientos_Sesion_ID";

	/** Set Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID);

	/** Get Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID();

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException;

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

    /** Column name REF_EXME_PREGUNTA_ID */
    public static final String COLUMNNAME_REF_EXME_PREGUNTA_ID = "REF_EXME_PREGUNTA_ID";

	/** Set REF_EXME_PREGUNTA_ID	  */
	public void setREF_EXME_PREGUNTA_ID (int REF_EXME_PREGUNTA_ID);

	/** Get REF_EXME_PREGUNTA_ID	  */
	public int getREF_EXME_PREGUNTA_ID();

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
	public void setSecuencia (int Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public int getSecuencia();

    /** Column name TextBinary */
    public static final String COLUMNNAME_TextBinary = "TextBinary";

	/** Set Binary Text	  */
	public void setTextBinary (String TextBinary);

	/** Get Binary Text	  */
	public String getTextBinary();
}
