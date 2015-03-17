/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PreOperatorio
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PreOperatorio 
{

    /** TableName=EXME_PreOperatorio */
    public static final String Table_Name = "EXME_PreOperatorio";

    /** AD_Table_ID=1200461 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

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

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

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
	public void setEdad (String Edad);

	/** Get Age.
	  * Age
	  */
	public String getEdad();

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

	public I_EXME_Diagnostico getEXME_Diagnostico() throws RuntimeException;

    /** Column name EXME_Diagnostico_Post_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_Post_ID = "EXME_Diagnostico_Post_ID";

	/** Set Diagnosis Post Surgery	  */
	public void setEXME_Diagnostico_Post_ID (int EXME_Diagnostico_Post_ID);

	/** Get Diagnosis Post Surgery	  */
	public int getEXME_Diagnostico_Post_ID();

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

    /** Column name EXME_Intervencion_ID */
    public static final String COLUMNNAME_EXME_Intervencion_ID = "EXME_Intervencion_ID";

	/** Set Intervention.
	  * Intervention
	  */
	public void setEXME_Intervencion_ID (int EXME_Intervencion_ID);

	/** Get Intervention.
	  * Intervention
	  */
	public int getEXME_Intervencion_ID();

	public I_EXME_Intervencion getEXME_Intervencion() throws RuntimeException;

    /** Column name EXME_Medico_Anest_ID */
    public static final String COLUMNNAME_EXME_Medico_Anest_ID = "EXME_Medico_Anest_ID";

	/** Set Anesthesiologists	  */
	public void setEXME_Medico_Anest_ID (int EXME_Medico_Anest_ID);

	/** Get Anesthesiologists	  */
	public int getEXME_Medico_Anest_ID();

    /** Column name EXME_Medico_Aprob_ID */
    public static final String COLUMNNAME_EXME_Medico_Aprob_ID = "EXME_Medico_Aprob_ID";

	/** Set M.D. Approbation	  */
	public void setEXME_Medico_Aprob_ID (int EXME_Medico_Aprob_ID);

	/** Get M.D. Approbation	  */
	public int getEXME_Medico_Aprob_ID();

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

    /** Column name EXME_MotivoReagenda_ID */
    public static final String COLUMNNAME_EXME_MotivoReagenda_ID = "EXME_MotivoReagenda_ID";

	/** Set Reason for Reschedule.
	  * Reason for Reschedule
	  */
	public void setEXME_MotivoReagenda_ID (int EXME_MotivoReagenda_ID);

	/** Get Reason for Reschedule.
	  * Reason for Reschedule
	  */
	public int getEXME_MotivoReagenda_ID();

	public I_EXME_MotivoReagenda getEXME_MotivoReagenda() throws RuntimeException;

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

    /** Column name EXME_PatientClass_ID */
    public static final String COLUMNNAME_EXME_PatientClass_ID = "EXME_PatientClass_ID";

	/** Set Patient Classification	  */
	public void setEXME_PatientClass_ID (int EXME_PatientClass_ID);

	/** Get Patient Classification	  */
	public int getEXME_PatientClass_ID();

	public I_EXME_PatientClass getEXME_PatientClass() throws RuntimeException;

    /** Column name EXME_PreOperatorio_ID */
    public static final String COLUMNNAME_EXME_PreOperatorio_ID = "EXME_PreOperatorio_ID";

	/** Set Pre Surgery.
	  * Pre Surgery
	  */
	public void setEXME_PreOperatorio_ID (int EXME_PreOperatorio_ID);

	/** Get Pre Surgery.
	  * Pre Surgery
	  */
	public int getEXME_PreOperatorio_ID();

    /** Column name EXME_PreOperatorioReagen_ID */
    public static final String COLUMNNAME_EXME_PreOperatorioReagen_ID = "EXME_PreOperatorioReagen_ID";

	/** Set Reference to Application of Surgery.
	  * Reference to Application of Surgery
	  */
	public void setEXME_PreOperatorioReagen_ID (int EXME_PreOperatorioReagen_ID);

	/** Get Reference to Application of Surgery.
	  * Reference to Application of Surgery
	  */
	public int getEXME_PreOperatorioReagen_ID();

    /** Column name EXME_ProcAnestesico_ID */
    public static final String COLUMNNAME_EXME_ProcAnestesico_ID = "EXME_ProcAnestesico_ID";

	/** Set Anesthetic Procedure.
	  * Anesthetic Procedure
	  */
	public void setEXME_ProcAnestesico_ID (int EXME_ProcAnestesico_ID);

	/** Get Anesthetic Procedure.
	  * Anesthetic Procedure
	  */
	public int getEXME_ProcAnestesico_ID();

	public I_EXME_ProcAnestesico getEXME_ProcAnestesico() throws RuntimeException;

    /** Column name EXME_ProgQuiro_ID */
    public static final String COLUMNNAME_EXME_ProgQuiro_ID = "EXME_ProgQuiro_ID";

	/** Set Schedule of Surgery Room.
	  * Schedule of Surgery Room
	  */
	public void setEXME_ProgQuiro_ID (int EXME_ProgQuiro_ID);

	/** Get Schedule of Surgery Room.
	  * Schedule of Surgery Room
	  */
	public int getEXME_ProgQuiro_ID();

	public I_EXME_ProgQuiro getEXME_ProgQuiro() throws RuntimeException;

    /** Column name EXME_Quirofano_ID */
    public static final String COLUMNNAME_EXME_Quirofano_ID = "EXME_Quirofano_ID";

	/** Set Surgery Room.
	  * Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID);

	/** Get Surgery Room.
	  * Surgey Room
	  */
	public int getEXME_Quirofano_ID();

	public I_EXME_Quirofano getEXME_Quirofano() throws RuntimeException;

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

    /** Column name FechaAnest */
    public static final String COLUMNNAME_FechaAnest = "FechaAnest";

	/** Set Date of approval of anesthesia	  */
	public void setFechaAnest (Timestamp FechaAnest);

	/** Get Date of approval of anesthesia	  */
	public Timestamp getFechaAnest();

    /** Column name FechaAprob */
    public static final String COLUMNNAME_FechaAprob = "FechaAprob";

	/** Set Date Approved.
	  * Date Approved
	  */
	public void setFechaAprob (Timestamp FechaAprob);

	/** Get Date Approved.
	  * Date Approved
	  */
	public Timestamp getFechaAprob();

    /** Column name FechaProg */
    public static final String COLUMNNAME_FechaProg = "FechaProg";

	/** Set Scheduled Date	  */
	public void setFechaProg (Timestamp FechaProg);

	/** Get Scheduled Date	  */
	public Timestamp getFechaProg();

    /** Column name Foraneo */
    public static final String COLUMNNAME_Foraneo = "Foraneo";

	/** Set Foreign	  */
	public void setForaneo (boolean Foraneo);

	/** Get Foreign	  */
	public boolean isForaneo();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsApprovedAnest */
    public static final String COLUMNNAME_IsApprovedAnest = "IsApprovedAnest";

	/** Set IsApprovedAnest	  */
	public void setIsApprovedAnest (boolean IsApprovedAnest);

	/** Get IsApprovedAnest	  */
	public boolean isApprovedAnest();

    /** Column name M_ProdAnestesico_ID */
    public static final String COLUMNNAME_M_ProdAnestesico_ID = "M_ProdAnestesico_ID";

	/** Set Anesthetic Products	  */
	public void setM_ProdAnestesico_ID (int M_ProdAnestesico_ID);

	/** Get Anesthetic Products	  */
	public int getM_ProdAnestesico_ID();

    /** Column name M_Warehouse_Sol_ID */
    public static final String COLUMNNAME_M_Warehouse_Sol_ID = "M_Warehouse_Sol_ID";

	/** Set Requesting Warehouse.
	  * Requesting Warehouse
	  */
	public void setM_Warehouse_Sol_ID (int M_Warehouse_Sol_ID);

	/** Get Requesting Warehouse.
	  * Requesting Warehouse
	  */
	public int getM_Warehouse_Sol_ID();

    /** Column name Nombre_Pac */
    public static final String COLUMNNAME_Nombre_Pac = "Nombre_Pac";

	/** Set Patient Name.
	  * Patient Name
	  */
	public void setNombre_Pac (String Nombre_Pac);

	/** Get Patient Name.
	  * Patient Name
	  */
	public String getNombre_Pac();

    /** Column name NoReagenda */
    public static final String COLUMNNAME_NoReagenda = "NoReagenda";

	/** Set Num. Reschedule	  */
	public void setNoReagenda (BigDecimal NoReagenda);

	/** Get Num. Reschedule	  */
	public BigDecimal getNoReagenda();

    /** Column name NotasReagenda */
    public static final String COLUMNNAME_NotasReagenda = "NotasReagenda";

	/** Set Notes Reschedule.
	  * Notes Reschedule
	  */
	public void setNotasReagenda (String NotasReagenda);

	/** Get Notes Reschedule.
	  * Notes Reschedule
	  */
	public String getNotasReagenda();

    /** Column name PriorityRule */
    public static final String COLUMNNAME_PriorityRule = "PriorityRule";

	/** Set Priority.
	  * Priority of a document
	  */
	public void setPriorityRule (String PriorityRule);

	/** Get Priority.
	  * Priority of a document
	  */
	public String getPriorityRule();

    /** Column name Programado */
    public static final String COLUMNNAME_Programado = "Programado";

	/** Set Scheduled	  */
	public void setProgramado (boolean Programado);

	/** Get Scheduled	  */
	public boolean isProgramado();

    /** Column name TipoAnestesia */
    public static final String COLUMNNAME_TipoAnestesia = "TipoAnestesia";

	/** Set Type of anesthesia	  */
	public void setTipoAnestesia (String TipoAnestesia);

	/** Get Type of anesthesia	  */
	public String getTipoAnestesia();

    /** Column name TipoCirugia */
    public static final String COLUMNNAME_TipoCirugia = "TipoCirugia";

	/** Set Surgery Type	  */
	public void setTipoCirugia (String TipoCirugia);

	/** Get Surgery Type	  */
	public String getTipoCirugia();

    /** Column name Trasplante */
    public static final String COLUMNNAME_Trasplante = "Trasplante";

	/** Set Transplant	  */
	public void setTrasplante (boolean Trasplante);

	/** Get Transplant	  */
	public boolean isTrasplante();

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
