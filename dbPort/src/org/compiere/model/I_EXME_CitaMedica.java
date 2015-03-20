/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CitaMedica
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CitaMedica 
{

    /** TableName=EXME_CitaMedica */
    public static final String Table_Name = "EXME_CitaMedica";

    /** AD_Table_ID=1000068 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Program.
	  * Program
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Program.
	  * Program
	  */
	public int getC_Campaign_ID();

	public I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name CitaDe */
    public static final String COLUMNNAME_CitaDe = "CitaDe";

	/** Set Appointment Of.
	  * Appointment Of
	  */
	public void setCitaDe (String CitaDe);

	/** Get Appointment Of.
	  * Appointment Of
	  */
	public String getCitaDe();

    /** Column name CitaNo */
    public static final String COLUMNNAME_CitaNo = "CitaNo";

	/** Set Appointment Number.
	  * Appointment number
	  */
	public void setCitaNo (int CitaNo);

	/** Get Appointment Number.
	  * Appointment number
	  */
	public int getCitaNo();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

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

    /** Column name Duracion */
    public static final String COLUMNNAME_Duracion = "Duracion";

	/** Set Duration.
	  * Duration
	  */
	public void setDuracion (int Duracion);

	/** Get Duration.
	  * Duration
	  */
	public int getDuracion();

    /** Column name Edad */
    public static final String COLUMNNAME_Edad = "Edad";

	/** Set Age.
	  * Age
	  */
	public void setEdad (int Edad);

	/** Get Age.
	  * Age
	  */
	public int getEdad();

    /** Column name Estatus */
    public static final String COLUMNNAME_Estatus = "Estatus";

	/** Set Status.
	  * Status
	  */
	public void setEstatus (String Estatus);

	/** Get Status.
	  * Status
	  */
	public String getEstatus();

    /** Column name EXME_ActPacienteIndH_ID */
    public static final String COLUMNNAME_EXME_ActPacienteIndH_ID = "EXME_ActPacienteIndH_ID";

	/** Set Patient's Indication.
	  * Patient's Indication
	  */
	public void setEXME_ActPacienteIndH_ID (int EXME_ActPacienteIndH_ID);

	/** Get Patient's Indication.
	  * Patient's Indication
	  */
	public int getEXME_ActPacienteIndH_ID();

	public I_EXME_ActPacienteIndH getEXME_ActPacienteIndH() throws RuntimeException;

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

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

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

    /** Column name EXME_GrupoCuestionario_ID */
    public static final String COLUMNNAME_EXME_GrupoCuestionario_ID = "EXME_GrupoCuestionario_ID";

	/** Set Form Group.
	  * Form Group
	  */
	public void setEXME_GrupoCuestionario_ID (int EXME_GrupoCuestionario_ID);

	/** Get Form Group.
	  * Form Group
	  */
	public int getEXME_GrupoCuestionario_ID();

	public I_EXME_GrupoCuestionario getEXME_GrupoCuestionario() throws RuntimeException;

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set CPT Code.
	  * Current Procedural Terminology (CPT)
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get CPT Code.
	  * Current Procedural Terminology (CPT)
	  */
	public int getEXME_Intervencion_ID();

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException;

    /** Column name EXME_Level_Of_Service_ID */
    public static final String COLUMNNAME_EXME_Level_Of_Service_ID = "EXME_Level_Of_Service_ID";

	/** Set Level of service.
	  * level of service
	  */
	public void setEXME_Level_Of_Service_ID (int EXME_Level_Of_Service_ID);

	/** Get Level of service.
	  * level of service
	  */
	public int getEXME_Level_Of_Service_ID();

	public I_EXME_Level_Of_Service getEXME_Level_Of_Service() throws RuntimeException;

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

    /** Column name EXME_MEDICO_ORIG */
    public static final String COLUMNNAME_EXME_MEDICO_ORIG = "EXME_MEDICO_ORIG";

	/** Set EXME_MEDICO_ORIG	  */
	public void setEXME_MEDICO_ORIG (int EXME_MEDICO_ORIG);

	/** Get EXME_MEDICO_ORIG	  */
	public int getEXME_MEDICO_ORIG();

    /** Column name EXME_MotivoCancel_ID */
    public static final String COLUMNNAME_EXME_MotivoCancel_ID = "EXME_MotivoCancel_ID";

	/** Set Cancel Reason.
	  * Cancel Reason
	  */
	public void setEXME_MotivoCancel_ID (int EXME_MotivoCancel_ID);

	/** Get Cancel Reason.
	  * Cancel Reason
	  */
	public int getEXME_MotivoCancel_ID();

	public I_EXME_MotivoCancel getEXME_MotivoCancel() throws RuntimeException;

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

    /** Column name EXME_TipoPaciente_ID */
    public static final String COLUMNNAME_EXME_TipoPaciente_ID = "EXME_TipoPaciente_ID";

	/** Set Type of Patient.
	  * Type of Patient
	  */
	public void setEXME_TipoPaciente_ID (int EXME_TipoPaciente_ID);

	/** Get Type of Patient.
	  * Type of Patient
	  */
	public int getEXME_TipoPaciente_ID();

	public I_EXME_TipoPaciente getEXME_TipoPaciente() throws RuntimeException;

    /** Column name EXME_Tratamiento_ID */
    public static final String COLUMNNAME_EXME_Tratamiento_ID = "EXME_Tratamiento_ID";

	/** Set Teatment.
	  * Teatment
	  */
	public void setEXME_Tratamiento_ID (int EXME_Tratamiento_ID);

	/** Get Teatment.
	  * Teatment
	  */
	public int getEXME_Tratamiento_ID();

    /** Column name EXME_Tratamientos_Sesion_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Sesion_ID = "EXME_Tratamientos_Sesion_ID";

	/** Set Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID);

	/** Get Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID();

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException;

    /** Column name FechaCancel */
    public static final String COLUMNNAME_FechaCancel = "FechaCancel";

	/** Set Cancel date.
	  * Cancel date
	  */
	public void setFechaCancel (Timestamp FechaCancel);

	/** Get Cancel date.
	  * Cancel date
	  */
	public Timestamp getFechaCancel();

    /** Column name FechaConfirm */
    public static final String COLUMNNAME_FechaConfirm = "FechaConfirm";

	/** Set Confirmation Date	  */
	public void setFechaConfirm (Timestamp FechaConfirm);

	/** Get Confirmation Date	  */
	public Timestamp getFechaConfirm();

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

    /** Column name FechaHrFin */
    public static final String COLUMNNAME_FechaHrFin = "FechaHrFin";

	/** Set Finish Hr and Date	  */
	public void setFechaHrFin (Timestamp FechaHrFin);

	/** Get Finish Hr and Date	  */
	public Timestamp getFechaHrFin();

    /** Column name FechaHrIni */
    public static final String COLUMNNAME_FechaHrIni = "FechaHrIni";

	/** Set Initial Hr and Date	  */
	public void setFechaHrIni (Timestamp FechaHrIni);

	/** Get Initial Hr and Date	  */
	public Timestamp getFechaHrIni();

    /** Column name FinCounseling */
    public static final String COLUMNNAME_FinCounseling = "FinCounseling";

	/** Set Final counseling hour.
	  * Final counseling hour
	  */
	public void setFinCounseling (Timestamp FinCounseling);

	/** Get Final counseling hour.
	  * Final counseling hour
	  */
	public Timestamp getFinCounseling();

    /** Column name HoraLlegada */
    public static final String COLUMNNAME_HoraLlegada = "HoraLlegada";

	/** Set Arrival Time.
	  * Arrival Time
	  */
	public void setHoraLlegada (Timestamp HoraLlegada);

	/** Get Arrival Time.
	  * Arrival Time
	  */
	public Timestamp getHoraLlegada();

    /** Column name IniCounseling */
    public static final String COLUMNNAME_IniCounseling = "IniCounseling";

	/** Set Initial counseling hour.
	  * Initial counseling hour
	  */
	public void setIniCounseling (Timestamp IniCounseling);

	/** Get Initial counseling hour.
	  * Initial counseling hour
	  */
	public Timestamp getIniCounseling();

    /** Column name IsInfoSent */
    public static final String COLUMNNAME_IsInfoSent = "IsInfoSent";

	/** Set Send Info.
	  * Send informational messages and copies
	  */
	public void setIsInfoSent (boolean IsInfoSent);

	/** Get Send Info.
	  * Send informational messages and copies
	  */
	public boolean isInfoSent();

    /** Column name MotivoCancel */
    public static final String COLUMNNAME_MotivoCancel = "MotivoCancel";

	/** Set Cancel Reason.
	  * Cancel Reason
	  */
	public void setMotivoCancel (String MotivoCancel);

	/** Get Cancel Reason.
	  * Cancel Reason
	  */
	public String getMotivoCancel();

    /** Column name MotivoTS */
    public static final String COLUMNNAME_MotivoTS = "MotivoTS";

	/** Set S.W. Motive.
	  * Social Work Motive
	  */
	public void setMotivoTS (String MotivoTS);

	/** Get S.W. Motive.
	  * Social Work Motive
	  */
	public String getMotivoTS();

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

    /** Column name NextVisit */
    public static final String COLUMNNAME_NextVisit = "NextVisit";

	/** Set Next Visit	  */
	public void setNextVisit (String NextVisit);

	/** Get Next Visit	  */
	public String getNextVisit();

    /** Column name Notified */
    public static final String COLUMNNAME_Notified = "Notified";

	/** Set Notified	  */
	public void setNotified (boolean Notified);

	/** Get Notified	  */
	public boolean isNotified();

    /** Column name N_Promo */
    public static final String COLUMNNAME_N_Promo = "N_Promo";

	/** Set Promotion Number	  */
	public void setN_Promo (String N_Promo);

	/** Get Promotion Number	  */
	public String getN_Promo();

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

    /** Column name Patient_Type */
    public static final String COLUMNNAME_Patient_Type = "Patient_Type";

	/** Set Patient Type	  */
	public void setPatient_Type (String Patient_Type);

	/** Get Patient Type	  */
	public String getPatient_Type();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Rank */
    public static final String COLUMNNAME_Rank = "Rank";

	/** Set Rank.
	  * Rank
	  */
	public void setRank (int Rank);

	/** Get Rank.
	  * Rank
	  */
	public int getRank();

    /** Column name Ref_CitaMedica_ID */
    public static final String COLUMNNAME_Ref_CitaMedica_ID = "Ref_CitaMedica_ID";

	/** Set Reference of the First  Appointment.
	  * Reference of the First  Appointment
	  */
	public void setRef_CitaMedica_ID (int Ref_CitaMedica_ID);

	/** Get Reference of the First  Appointment.
	  * Reference of the First  Appointment
	  */
	public int getRef_CitaMedica_ID();

    /** Column name Substitute_ID */
    public static final String COLUMNNAME_Substitute_ID = "Substitute_ID";

	/** Set Substitute.
	  * Entity which can be used in place of this entity
	  */
	public void setSubstitute_ID (int Substitute_ID);

	/** Get Substitute.
	  * Entity which can be used in place of this entity
	  */
	public int getSubstitute_ID();

    /** Column name TerminadoPor */
    public static final String COLUMNNAME_TerminadoPor = "TerminadoPor";

	/** Set Finished By	  */
	public void setTerminadoPor (int TerminadoPor);

	/** Get Finished By	  */
	public int getTerminadoPor();

    /** Column name Utilidad */
    public static final String COLUMNNAME_Utilidad = "Utilidad";

	/** Set Utility.
	  * Utility
	  */
	public void setUtilidad (boolean Utilidad);

	/** Get Utility.
	  * Utility
	  */
	public boolean isUtilidad();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
