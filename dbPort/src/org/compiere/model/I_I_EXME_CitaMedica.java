/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_CitaMedica
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_I_EXME_CitaMedica 
{

    /** TableName=I_EXME_CitaMedica */
    public static final String Table_Name = "I_EXME_CitaMedica";

    /** AD_Table_ID=1200065 */
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

    /** Column name CitaDe */
    public static final String COLUMNNAME_CitaDe = "CitaDe";

	/** Set Appointment Of.
	  * Appointment Of
	  */
	public void setCitaDe (boolean CitaDe);

	/** Get Appointment Of.
	  * Appointment Of
	  */
	public boolean isCitaDe();

    /** Column name Confirmada */
    public static final String COLUMNNAME_Confirmada = "Confirmada";

	/** Set Confirmed.
	  * Confirmed
	  */
	public void setConfirmada (boolean Confirmada);

	/** Get Confirmed.
	  * Confirmed
	  */
	public boolean isConfirmada();

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

    /** Column name Duracion */
    public static final String COLUMNNAME_Duracion = "Duracion";

	/** Set Duration.
	  * Duration
	  */
	public void setDuracion (BigDecimal Duracion);

	/** Get Duration.
	  * Duration
	  */
	public BigDecimal getDuracion();

    /** Column name Edad */
    public static final String COLUMNNAME_Edad = "Edad";

	/** Set Age.
	  * Age
	  */
	public void setEdad (BigDecimal Edad);

	/** Get Age.
	  * Age
	  */
	public BigDecimal getEdad();

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (boolean Estatus);

	/** Get Status.
	  * Status
	  */
	public boolean isEstatus();

    /** Column name EXME_Asistente_ID */
    public static final String COLUMNNAME_EXME_Asistente_ID = "EXME_Asistente_ID";

	/** Set Assistant.
	  * Assistant
	  */
	public void setEXME_Asistente_ID (int EXME_Asistente_ID);

	/** Get Assistant.
	  * Assistant
	  */
	public int getEXME_Asistente_ID();

	public I_EXME_Asistente getEXME_Asistente() throws RuntimeException;

    /** Column name EXME_Asistente_Value */
    public static final String COLUMNNAME_EXME_Asistente_Value = "EXME_Asistente_Value";

	/** Set Assistant Value	  */
	public void setEXME_Asistente_Value (String EXME_Asistente_Value);

	/** Get Assistant Value	  */
	public String getEXME_Asistente_Value();

    /** Column name EXME_CentroMedicoEnv_ID */
    public static final String COLUMNNAME_EXME_CentroMedicoEnv_ID = "EXME_CentroMedicoEnv_ID";

	/** Set Medical Center.
	  * Medical Center
	  */
	public void setEXME_CentroMedicoEnv_ID (int EXME_CentroMedicoEnv_ID);

	/** Get Medical Center.
	  * Medical Center
	  */
	public int getEXME_CentroMedicoEnv_ID();

    /** Column name EXME_CentroMedicoEnv_Value */
    public static final String COLUMNNAME_EXME_CentroMedicoEnv_Value = "EXME_CentroMedicoEnv_Value";

	/** Set Value of Medical Center	  */
	public void setEXME_CentroMedicoEnv_Value (String EXME_CentroMedicoEnv_Value);

	/** Get Value of Medical Center	  */
	public String getEXME_CentroMedicoEnv_Value();

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

    /** Column name EXME_Especialidad_Value */
    public static final String COLUMNNAME_EXME_Especialidad_Value = "EXME_Especialidad_Value";

	/** Set Speciality Value	  */
	public void setEXME_Especialidad_Value (String EXME_Especialidad_Value);

	/** Get Speciality Value	  */
	public String getEXME_Especialidad_Value();

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

    /** Column name EXME_Medico_Value */
    public static final String COLUMNNAME_EXME_Medico_Value = "EXME_Medico_Value";

	/** Set Doctor Value	  */
	public void setEXME_Medico_Value (String EXME_Medico_Value);

	/** Get Doctor Value	  */
	public String getEXME_Medico_Value();

    /** Column name EXME_MotivoCita_ID */
    public static final String COLUMNNAME_EXME_MotivoCita_ID = "EXME_MotivoCita_ID";

	/** Set Motive of appointment.
	  * Motive of appointment
	  */
	public void setEXME_MotivoCita_ID (int EXME_MotivoCita_ID);

	/** Get Motive of appointment.
	  * Motive of appointment
	  */
	public int getEXME_MotivoCita_ID();

	public I_EXME_MotivoCita getEXME_MotivoCita() throws RuntimeException;

    /** Column name EXME_Motivo_Value */
    public static final String COLUMNNAME_EXME_Motivo_Value = "EXME_Motivo_Value";

	/** Set Reason Value	  */
	public void setEXME_Motivo_Value (String EXME_Motivo_Value);

	/** Get Reason Value	  */
	public String getEXME_Motivo_Value();

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

    /** Column name EXME_TipoCita_ID */
    public static final String COLUMNNAME_EXME_TipoCita_ID = "EXME_TipoCita_ID";

	/** Set Type of Appointment.
	  * Type of Appointment
	  */
	public void setEXME_TipoCita_ID (int EXME_TipoCita_ID);

	/** Get Type of Appointment.
	  * Type of Appointment
	  */
	public int getEXME_TipoCita_ID();

	public I_EXME_TipoCita getEXME_TipoCita() throws RuntimeException;

    /** Column name EXME_TipoCita_Value */
    public static final String COLUMNNAME_EXME_TipoCita_Value = "EXME_TipoCita_Value";

	/** Set Appointment Type Value	  */
	public void setEXME_TipoCita_Value (String EXME_TipoCita_Value);

	/** Get Appointment Type Value	  */
	public String getEXME_TipoCita_Value();

    /** Column name FechaHrCita */
    public static final String COLUMNNAME_FechaHrCita = "FechaHrCita";

	/** Set Date.
	  * Date
	  */
	public void setFechaHrCita (Timestamp FechaHrCita);

	/** Get Date.
	  * Date
	  */
	public Timestamp getFechaHrCita();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_EXME_CitaMedica_ID */
    public static final String COLUMNNAME_I_EXME_CitaMedica_ID = "I_EXME_CitaMedica_ID";

	/** Set Medical Appointment	  */
	public void setI_EXME_CitaMedica_ID (int I_EXME_CitaMedica_ID);

	/** Get Medical Appointment	  */
	public int getI_EXME_CitaMedica_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NumHist */
    public static final String COLUMNNAME_NumHist = "NumHist";

	/** Set History Number	  */
	public void setNumHist (String NumHist);

	/** Get History Number	  */
	public String getNumHist();

    /** Column name Observaciones */
    public static final String COLUMNNAME_Observaciones = "Observaciones";

	/** Set Notes.
	  * Notes
	  */
	public void setObservaciones (String Observaciones);

	/** Get Notes.
	  * Notes
	  */
	public String getObservaciones();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
