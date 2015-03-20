/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProgQuiro
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ProgQuiro 
{

    /** TableName=EXME_ProgQuiro */
    public static final String Table_Name = "EXME_ProgQuiro";

    /** AD_Table_ID=1000113 */
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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name AD_User_Enfermera_ID */
    public static final String COLUMNNAME_AD_User_Enfermera_ID = "AD_User_Enfermera_ID";

	/** Set Nurse	  */
	public void setAD_User_Enfermera_ID (int AD_User_Enfermera_ID);

	/** Get Nurse	  */
	public int getAD_User_Enfermera_ID();

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

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

    /** Column name C_DocTypeTarget_ID */
    public static final String COLUMNNAME_C_DocTypeTarget_ID = "C_DocTypeTarget_ID";

	/** Set Target Document Type.
	  * Target document type for conversing documents
	  */
	public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID);

	/** Get Target Document Type.
	  * Target document type for conversing documents
	  */
	public int getC_DocTypeTarget_ID();

    /** Column name Comentario */
    public static final String COLUMNNAME_Comentario = "Comentario";

	/** Set Comment	  */
	public void setComentario (String Comentario);

	/** Get Comment	  */
	public String getComentario();

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

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

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

    /** Column name EXME_Diagnostico_Post_ID */
    public static final String COLUMNNAME_EXME_Diagnostico_Post_ID = "EXME_Diagnostico_Post_ID";

	/** Set Diagnosis Post Surgery	  */
	public void setEXME_Diagnostico_Post_ID (int EXME_Diagnostico_Post_ID);

	/** Get Diagnosis Post Surgery	  */
	public int getEXME_Diagnostico_Post_ID();

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

    /** Column name EXME_EnfermeriaInst_ID */
    public static final String COLUMNNAME_EXME_EnfermeriaInst_ID = "EXME_EnfermeriaInst_ID";

	/** Set Scrub Nurse	  */
	public void setEXME_EnfermeriaInst_ID (int EXME_EnfermeriaInst_ID);

	/** Get Scrub Nurse	  */
	public int getEXME_EnfermeriaInst_ID();

    /** Column name EXME_IntervencionReal_ID */
    public static final String COLUMNNAME_EXME_IntervencionReal_ID = "EXME_IntervencionReal_ID";

	/** Set Surgery Performed	  */
	public void setEXME_IntervencionReal_ID (int EXME_IntervencionReal_ID);

	/** Get Surgery Performed	  */
	public int getEXME_IntervencionReal_ID();

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

    /** Column name EXME_Medico2_ID */
    public static final String COLUMNNAME_EXME_Medico2_ID = "EXME_Medico2_ID";

	/** Set Doctor 2	  */
	public void setEXME_Medico2_ID (int EXME_Medico2_ID);

	/** Get Doctor 2	  */
	public int getEXME_Medico2_ID();

    /** Column name EXME_Medico3_ID */
    public static final String COLUMNNAME_EXME_Medico3_ID = "EXME_Medico3_ID";

	/** Set Doctor 3	  */
	public void setEXME_Medico3_ID (int EXME_Medico3_ID);

	/** Get Doctor 3	  */
	public int getEXME_Medico3_ID();

    /** Column name EXME_Medico4_ID */
    public static final String COLUMNNAME_EXME_Medico4_ID = "EXME_Medico4_ID";

	/** Set Doctor 4	  */
	public void setEXME_Medico4_ID (int EXME_Medico4_ID);

	/** Get Doctor 4	  */
	public int getEXME_Medico4_ID();

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

    /** Column name EXME_PreferenceCard_ID */
    public static final String COLUMNNAME_EXME_PreferenceCard_ID = "EXME_PreferenceCard_ID";

	/** Set Preference Card	  */
	public void setEXME_PreferenceCard_ID (int EXME_PreferenceCard_ID);

	/** Get Preference Card	  */
	public int getEXME_PreferenceCard_ID();

	public I_EXME_PreferenceCard getEXME_PreferenceCard() throws RuntimeException;

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

    /** Column name EXME_Warehouse_ID */
    public static final String COLUMNNAME_EXME_Warehouse_ID = "EXME_Warehouse_ID";

	/** Set Warehouse	  */
	public void setEXME_Warehouse_ID (int EXME_Warehouse_ID);

	/** Get Warehouse	  */
	public int getEXME_Warehouse_ID();

    /** Column name FechaAnestesiaFin */
    public static final String COLUMNNAME_FechaAnestesiaFin = "FechaAnestesiaFin";

	/** Set Anesthesia End Time.
	  * Anesthesia End Time
	  */
	public void setFechaAnestesiaFin (Timestamp FechaAnestesiaFin);

	/** Get Anesthesia End Time.
	  * Anesthesia End Time
	  */
	public Timestamp getFechaAnestesiaFin();

    /** Column name FechaAnestesiaInicio */
    public static final String COLUMNNAME_FechaAnestesiaInicio = "FechaAnestesiaInicio";

	/** Set Anesthesia Start Time.
	  * Anesthesia Start Time
	  */
	public void setFechaAnestesiaInicio (Timestamp FechaAnestesiaInicio);

	/** Get Anesthesia Start Time.
	  * Anesthesia Start Time
	  */
	public Timestamp getFechaAnestesiaInicio();

    /** Column name FechaCierre */
    public static final String COLUMNNAME_FechaCierre = "FechaCierre";

	/** Set Closing Date.
	  * Date of Intervention Closing
	  */
	public void setFechaCierre (Timestamp FechaCierre);

	/** Get Closing Date.
	  * Date of Intervention Closing
	  */
	public Timestamp getFechaCierre();

    /** Column name FechaFin */
    public static final String COLUMNNAME_FechaFin = "FechaFin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFechaFin (Timestamp FechaFin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFechaFin();

    /** Column name FechaInicio */
    public static final String COLUMNNAME_FechaInicio = "FechaInicio";

	/** Set Beginning Date	  */
	public void setFechaInicio (Timestamp FechaInicio);

	/** Get Beginning Date	  */
	public Timestamp getFechaInicio();

    /** Column name FechaProg */
    public static final String COLUMNNAME_FechaProg = "FechaProg";

	/** Set Scheduled Date	  */
	public void setFechaProg (Timestamp FechaProg);

	/** Get Scheduled Date	  */
	public Timestamp getFechaProg();

    /** Column name HoraCierre */
    public static final String COLUMNNAME_HoraCierre = "HoraCierre";

	/** Set Closing Hour	  */
	public void setHoraCierre (BigDecimal HoraCierre);

	/** Get Closing Hour	  */
	public BigDecimal getHoraCierre();

    /** Column name HoraFin */
    public static final String COLUMNNAME_HoraFin = "HoraFin";

	/** Set End Hour	  */
	public void setHoraFin (BigDecimal HoraFin);

	/** Get End Hour	  */
	public BigDecimal getHoraFin();

    /** Column name HoraInicio */
    public static final String COLUMNNAME_HoraInicio = "HoraInicio";

	/** Set Start Hour	  */
	public void setHoraInicio (BigDecimal HoraInicio);

	/** Get Start Hour	  */
	public BigDecimal getHoraInicio();

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

    /** Column name IsCreditApproved */
    public static final String COLUMNNAME_IsCreditApproved = "IsCreditApproved";

	/** Set Credit Approved.
	  * Credit  has been approved
	  */
	public void setIsCreditApproved (boolean IsCreditApproved);

	/** Get Credit Approved.
	  * Credit  has been approved
	  */
	public boolean isCreditApproved();

    /** Column name IsInvoiced */
    public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";

	/** Set Invoiced.
	  * Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced);

	/** Get Invoiced.
	  * Is this invoiced?
	  */
	public boolean isInvoiced();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name MotivoCancelacion */
    public static final String COLUMNNAME_MotivoCancelacion = "MotivoCancelacion";

	/** Set Comment of the reason for suspension.
	  * Comment of the reason for suspension
	  */
	public void setMotivoCancelacion (String MotivoCancelacion);

	/** Get Comment of the reason for suspension.
	  * Comment of the reason for suspension
	  */
	public String getMotivoCancelacion();

    /** Column name M_ProdAnestesico_ID */
    public static final String COLUMNNAME_M_ProdAnestesico_ID = "M_ProdAnestesico_ID";

	/** Set Anesthetic Products	  */
	public void setM_ProdAnestesico_ID (int M_ProdAnestesico_ID);

	/** Get Anesthetic Products	  */
	public int getM_ProdAnestesico_ID();

    /** Column name NombrePac */
    public static final String COLUMNNAME_NombrePac = "NombrePac";

	/** Set Patient Name	  */
	public void setNombrePac (String NombrePac);

	/** Get Patient Name	  */
	public String getNombrePac();

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

    /** Column name UserCierre */
    public static final String COLUMNNAME_UserCierre = "UserCierre";

	/** Set User Close	  */
	public void setUserCierre (int UserCierre);

	/** Get User Close	  */
	public int getUserCierre();
}
