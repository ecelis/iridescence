/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPac
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CtaPac 
{

    /** TableName=EXME_CtaPac */
    public static final String Table_Name = "EXME_CtaPac";

    /** AD_Table_ID=1000081 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name ActualizadoAlta */
    public static final String COLUMNNAME_ActualizadoAlta = "ActualizadoAlta";

	/** Set Updated by release.
	  * Who updated the status of patient account
	  */
	public void setActualizadoAlta (int ActualizadoAlta);

	/** Get Updated by release.
	  * Who updated the status of patient account
	  */
	public int getActualizadoAlta();

    /** Column name ActualizadoPrealta */
    public static final String COLUMNNAME_ActualizadoPrealta = "ActualizadoPrealta";

	/** Set Predischarge Update	  */
	public void setActualizadoPrealta (int ActualizadoPrealta);

	/** Get Predischarge Update	  */
	public int getActualizadoPrealta();

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

    /** Column name AD_PrintFormat_ID */
    public static final String COLUMNNAME_AD_PrintFormat_ID = "AD_PrintFormat_ID";

	/** Set Print Format.
	  * Data Print Format
	  */
	public void setAD_PrintFormat_ID (int AD_PrintFormat_ID);

	/** Get Print Format.
	  * Data Print Format
	  */
	public int getAD_PrintFormat_ID();

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

    /** Column name Archivo */
    public static final String COLUMNNAME_Archivo = "Archivo";

	/** Set File	  */
	public void setArchivo (byte[] Archivo);

	/** Get File	  */
	public byte[] getArchivo();

    /** Column name BillDate */
    public static final String COLUMNNAME_BillDate = "BillDate";

	/** Set Bill Date.
	  * Date when the encounter has been billed
	  */
	public void setBillDate (Timestamp BillDate);

	/** Get Bill Date.
	  * Date when the encounter has been billed
	  */
	public Timestamp getBillDate();

    /** Column name BillingStatus */
    public static final String COLUMNNAME_BillingStatus = "BillingStatus";

	/** Set BillingStatus	  */
	public void setBillingStatus (String BillingStatus);

	/** Get BillingStatus	  */
	public String getBillingStatus();

    /** Column name BillingType */
    public static final String COLUMNNAME_BillingType = "BillingType";

	/** Set Billing type	  */
	public void setBillingType (String BillingType);

	/** Get Billing type	  */
	public String getBillingType();

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Responsible Units.
	  * Responsible Units
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Responsible Units.
	  * Responsible Units
	  */
	public int getC_Activity_ID();

    /** Column name CasosMedicos */
    public static final String COLUMNNAME_CasosMedicos = "CasosMedicos";

	/** Set Medical Cases	  */
	public void setCasosMedicos (boolean CasosMedicos);

	/** Get Medical Cases	  */
	public boolean isCasosMedicos();

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

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

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

    /** Column name Coded */
    public static final String COLUMNNAME_Coded = "Coded";

	/** Set Coded	  */
	public void setCoded (boolean Coded);

	/** Get Coded	  */
	public boolean isCoded();

    /** Column name CodedProf */
    public static final String COLUMNNAME_CodedProf = "CodedProf";

	/** Set Professional Coded	  */
	public void setCodedProf (boolean CodedProf);

	/** Get Professional Coded	  */
	public boolean isCodedProf();

    /** Column name CodingDate */
    public static final String COLUMNNAME_CodingDate = "CodingDate";

	/** Set Coding Date	  */
	public void setCodingDate (Timestamp CodingDate);

	/** Get Coding Date	  */
	public Timestamp getCodingDate();

    /** Column name CodingDateProf */
    public static final String COLUMNNAME_CodingDateProf = "CodingDateProf";

	/** Set Professional Coding Date	  */
	public void setCodingDateProf (Timestamp CodingDateProf);

	/** Get Professional Coding Date	  */
	public Timestamp getCodingDateProf();

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

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

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

    /** Column name DatePrinted */
    public static final String COLUMNNAME_DatePrinted = "DatePrinted";

	/** Set Date printed.
	  * Date the document was printed.
	  */
	public void setDatePrinted (Timestamp DatePrinted);

	/** Get Date printed.
	  * Date the document was printed.
	  */
	public Timestamp getDatePrinted();

    /** Column name DepartureDate */
    public static final String COLUMNNAME_DepartureDate = "DepartureDate";

	/** Set DepartureDate	  */
	public void setDepartureDate (Timestamp DepartureDate);

	/** Get DepartureDate	  */
	public Timestamp getDepartureDate();

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

    /** Column name Diagnostico_Ingreso */
    public static final String COLUMNNAME_Diagnostico_Ingreso = "Diagnostico_Ingreso";

	/** Set Diagnostico_Ingreso	  */
	public void setDiagnostico_Ingreso (String Diagnostico_Ingreso);

	/** Get Diagnostico_Ingreso	  */
	public String getDiagnostico_Ingreso();

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

    /** Column name DRG */
    public static final String COLUMNNAME_DRG = "DRG";

	/** Set DRG	  */
	public void setDRG (BigDecimal DRG);

	/** Get DRG	  */
	public BigDecimal getDRG();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name EncounterStatus */
    public static final String COLUMNNAME_EncounterStatus = "EncounterStatus";

	/** Set Encounter Status	  */
	public void setEncounterStatus (String EncounterStatus);

	/** Get Encounter Status	  */
	public String getEncounterStatus();

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

    /** Column name EXME_AccompaniedBy_ID */
    public static final String COLUMNNAME_EXME_AccompaniedBy_ID = "EXME_AccompaniedBy_ID";

	/** Set Accompanied by	  */
	public void setEXME_AccompaniedBy_ID (int EXME_AccompaniedBy_ID);

	/** Get Accompanied by	  */
	public int getEXME_AccompaniedBy_ID();

	public I_EXME_AccompaniedBy getEXME_AccompaniedBy() throws RuntimeException;

    /** Column name EXME_AdmitSource_ID */
    public static final String COLUMNNAME_EXME_AdmitSource_ID = "EXME_AdmitSource_ID";

	/** Set Admit Source.
	  * Admit Source of the patient account
	  */
	public void setEXME_AdmitSource_ID (int EXME_AdmitSource_ID);

	/** Get Admit Source.
	  * Admit Source of the patient account
	  */
	public int getEXME_AdmitSource_ID();

	public I_EXME_AdmitSource getEXME_AdmitSource() throws RuntimeException;

    /** Column name EXME_AdmitType_ID */
    public static final String COLUMNNAME_EXME_AdmitType_ID = "EXME_AdmitType_ID";

	/** Set Admit Type.
	  * Admit Type of the Patient account
	  */
	public void setEXME_AdmitType_ID (int EXME_AdmitType_ID);

	/** Get Admit Type.
	  * Admit Type of the Patient account
	  */
	public int getEXME_AdmitType_ID();

	public I_EXME_AdmitType getEXME_AdmitType() throws RuntimeException;

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Service.
	  * Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Service.
	  * Service
	  */
	public int getEXME_Area_ID();

	public I_EXME_Area getEXME_Area() throws RuntimeException;

    /** Column name EXME_ArrivalMode_ID */
    public static final String COLUMNNAME_EXME_ArrivalMode_ID = "EXME_ArrivalMode_ID";

	/** Set Arrival Mode.
	  * Arrival Mode of the patient account
	  */
	public void setEXME_ArrivalMode_ID (int EXME_ArrivalMode_ID);

	/** Get Arrival Mode.
	  * Arrival Mode of the patient account
	  */
	public int getEXME_ArrivalMode_ID();

	public I_EXME_ArrivalMode getEXME_ArrivalMode() throws RuntimeException;

    /** Column name EXME_BeneficiosH_ID */
    public static final String COLUMNNAME_EXME_BeneficiosH_ID = "EXME_BeneficiosH_ID";

	/** Set EXME_BeneficiosH_ID	  */
	public void setEXME_BeneficiosH_ID (int EXME_BeneficiosH_ID);

	/** Get EXME_BeneficiosH_ID	  */
	public int getEXME_BeneficiosH_ID();

	public I_EXME_BeneficiosH getEXME_BeneficiosH() throws RuntimeException;

    /** Column name EXME_Cama_ID */
    public static final String COLUMNNAME_EXME_Cama_ID = "EXME_Cama_ID";

	/** Set Bed.
	  * Bed
	  */
	public void setEXME_Cama_ID (int EXME_Cama_ID);

	/** Get Bed.
	  * Bed
	  */
	public int getEXME_Cama_ID();

    /** Column name EXME_CamaIng_ID */
    public static final String COLUMNNAME_EXME_CamaIng_ID = "EXME_CamaIng_ID";

	/** Set Patient's Charge Bed	  */
	public void setEXME_CamaIng_ID (int EXME_CamaIng_ID);

	/** Get Patient's Charge Bed	  */
	public int getEXME_CamaIng_ID();

    /** Column name EXME_CtaPacExt_ID */
    public static final String COLUMNNAME_EXME_CtaPacExt_ID = "EXME_CtaPacExt_ID";

	/** Set Encounter Extension.
	  * Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID);

	/** Get Encounter Extension.
	  * Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID();

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException;

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

    /** Column name EXME_CtaPacRefer_ID */
    public static final String COLUMNNAME_EXME_CtaPacRefer_ID = "EXME_CtaPacRefer_ID";

	/** Set Referring Encounter	  */
	public void setEXME_CtaPacRefer_ID (int EXME_CtaPacRefer_ID);

	/** Get Referring Encounter	  */
	public int getEXME_CtaPacRefer_ID();

    /** Column name EXME_DescPrecioFijo_ID */
    public static final String COLUMNNAME_EXME_DescPrecioFijo_ID = "EXME_DescPrecioFijo_ID";

	/** Set Discount for a fixed billing Price.
	  * Discount for a fixed billing Price
	  */
	public void setEXME_DescPrecioFijo_ID (int EXME_DescPrecioFijo_ID);

	/** Get Discount for a fixed billing Price.
	  * Discount for a fixed billing Price
	  */
	public int getEXME_DescPrecioFijo_ID();

	public I_EXME_DescPrecioFijo getEXME_DescPrecioFijo() throws RuntimeException;

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

    /** Column name EXME_Discharged_Via_ID */
    public static final String COLUMNNAME_EXME_Discharged_Via_ID = "EXME_Discharged_Via_ID";

	/** Set Discharged Via	  */
	public void setEXME_Discharged_Via_ID (int EXME_Discharged_Via_ID);

	/** Get Discharged Via	  */
	public int getEXME_Discharged_Via_ID();

	public I_EXME_Discharged_Via getEXME_Discharged_Via() throws RuntimeException;

    /** Column name EXME_DischargeStatus_ID */
    public static final String COLUMNNAME_EXME_DischargeStatus_ID = "EXME_DischargeStatus_ID";

	/** Set Disposition.
	  * Discharge Status
	  */
	public void setEXME_DischargeStatus_ID (int EXME_DischargeStatus_ID);

	/** Get Disposition.
	  * Discharge Status
	  */
	public int getEXME_DischargeStatus_ID();

	public I_EXME_DischargeStatus getEXME_DischargeStatus() throws RuntimeException;

    /** Column name EXME_DRG_ID */
    public static final String COLUMNNAME_EXME_DRG_ID = "EXME_DRG_ID";

	/** Set DRG Code ID	  */
	public void setEXME_DRG_ID (int EXME_DRG_ID);

	/** Get DRG Code ID	  */
	public int getEXME_DRG_ID();

	public I_EXME_DRG getEXME_DRG() throws RuntimeException;

    /** Column name EXME_Especialidad2_ID */
    public static final String COLUMNNAME_EXME_Especialidad2_ID = "EXME_Especialidad2_ID";

	/** Set Specialty	  */
	public void setEXME_Especialidad2_ID (int EXME_Especialidad2_ID);

	/** Get Specialty	  */
	public int getEXME_Especialidad2_ID();

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

    /** Column name EXME_EspecialidadRefer_ID */
    public static final String COLUMNNAME_EXME_EspecialidadRefer_ID = "EXME_EspecialidadRefer_ID";

	/** Set Specialty Reference	  */
	public void setEXME_EspecialidadRefer_ID (int EXME_EspecialidadRefer_ID);

	/** Get Specialty Reference	  */
	public int getEXME_EspecialidadRefer_ID();

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

    /** Column name EXME_EstServIng_ID */
    public static final String COLUMNNAME_EXME_EstServIng_ID = "EXME_EstServIng_ID";

	/** Set Revenue Service Station 	  */
	public void setEXME_EstServIng_ID (int EXME_EstServIng_ID);

	/** Get Revenue Service Station 	  */
	public int getEXME_EstServIng_ID();

    /** Column name EXME_EstServProv_ID */
    public static final String COLUMNNAME_EXME_EstServProv_ID = "EXME_EstServProv_ID";

	/** Set Interim Service Station	  */
	public void setEXME_EstServProv_ID (int EXME_EstServProv_ID);

	/** Get Interim Service Station	  */
	public int getEXME_EstServProv_ID();

    /** Column name EXME_Institucion_ID */
    public static final String COLUMNNAME_EXME_Institucion_ID = "EXME_Institucion_ID";

	/** Set Service Facility.
	  * Service Facility
	  */
	public void setEXME_Institucion_ID (int EXME_Institucion_ID);

	/** Get Service Facility.
	  * Service Facility
	  */
	public int getEXME_Institucion_ID();

	public I_EXME_Institucion getEXME_Institucion() throws RuntimeException;

    /** Column name EXME_Medico2_ID */
    public static final String COLUMNNAME_EXME_Medico2_ID = "EXME_Medico2_ID";

	/** Set Doctor 2	  */
	public void setEXME_Medico2_ID (int EXME_Medico2_ID);

	/** Get Doctor 2	  */
	public int getEXME_Medico2_ID();

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

    /** Column name EXME_MedicoRefer_ID */
    public static final String COLUMNNAME_EXME_MedicoRefer_ID = "EXME_MedicoRefer_ID";

	/** Set EXME_MedicoRefer_ID	  */
	public void setEXME_MedicoRefer_ID (int EXME_MedicoRefer_ID);

	/** Get EXME_MedicoRefer_ID	  */
	public int getEXME_MedicoRefer_ID();

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

    /** Column name EXME_MotivoEgreso_ID */
    public static final String COLUMNNAME_EXME_MotivoEgreso_ID = "EXME_MotivoEgreso_ID";

	/** Set Discharge Reason	  */
	public void setEXME_MotivoEgreso_ID (int EXME_MotivoEgreso_ID);

	/** Get Discharge Reason	  */
	public int getEXME_MotivoEgreso_ID();

	public I_EXME_MotivoEgreso getEXME_MotivoEgreso() throws RuntimeException;

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

    /** Column name EXME_PaqBase_Version_ID */
    public static final String COLUMNNAME_EXME_PaqBase_Version_ID = "EXME_PaqBase_Version_ID";

	/** Set Package Version.
	  * Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID);

	/** Get Package Version.
	  * Package Version
	  */
	public int getEXME_PaqBase_Version_ID();

    /** Column name EXME_POS_ID */
    public static final String COLUMNNAME_EXME_POS_ID = "EXME_POS_ID";

	/** Set Place of Service.
	  * Place of Service
	  */
	public void setEXME_POS_ID (int EXME_POS_ID);

	/** Get Place of Service.
	  * Place of Service
	  */
	public int getEXME_POS_ID();

	public I_EXME_POS getEXME_POS() throws RuntimeException;

    /** Column name EXME_Procedencia_ID */
    public static final String COLUMNNAME_EXME_Procedencia_ID = "EXME_Procedencia_ID";

	/** Set Provenance.
	  * Provenance
	  */
	public void setEXME_Procedencia_ID (int EXME_Procedencia_ID);

	/** Get Provenance.
	  * Provenance
	  */
	public int getEXME_Procedencia_ID();

	public I_EXME_Procedencia getEXME_Procedencia() throws RuntimeException;

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

    /** Column name EXME_TipoProd_ID */
    public static final String COLUMNNAME_EXME_TipoProd_ID = "EXME_TipoProd_ID";

	/** Set Product Subtype.
	  * Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID);

	/** Get Product Subtype.
	  * Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID();

	public I_EXME_TipoProd getEXME_TipoProd() throws RuntimeException;

    /** Column name EXME_TipoTrasplante_ID */
    public static final String COLUMNNAME_EXME_TipoTrasplante_ID = "EXME_TipoTrasplante_ID";

	/** Set Transplant Type	  */
	public void setEXME_TipoTrasplante_ID (int EXME_TipoTrasplante_ID);

	/** Get Transplant Type	  */
	public int getEXME_TipoTrasplante_ID();

	public I_EXME_TipoTrasplante getEXME_TipoTrasplante() throws RuntimeException;

    /** Column name EXME_Transport_Mode_ID */
    public static final String COLUMNNAME_EXME_Transport_Mode_ID = "EXME_Transport_Mode_ID";

	/** Set EXME_Transport_Mode_ID	  */
	public void setEXME_Transport_Mode_ID (int EXME_Transport_Mode_ID);

	/** Get EXME_Transport_Mode_ID	  */
	public int getEXME_Transport_Mode_ID();

	public I_EXME_Transport_Mode getEXME_Transport_Mode() throws RuntimeException;

    /** Column name FechaAlta */
    public static final String COLUMNNAME_FechaAlta = "FechaAlta";

	/** Set Discharge Date.
	  * Discharge Date
	  */
	public void setFechaAlta (Timestamp FechaAlta);

	/** Get Discharge Date.
	  * Discharge Date
	  */
	public Timestamp getFechaAlta();

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

    /** Column name FechaCargoDiario */
    public static final String COLUMNNAME_FechaCargoDiario = "FechaCargoDiario";

	/** Set Date Daily Charge.
	  * Date when applied the charge
	  */
	public void setFechaCargoDiario (Timestamp FechaCargoDiario);

	/** Get Date Daily Charge.
	  * Date when applied the charge
	  */
	public Timestamp getFechaCargoDiario();

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

    /** Column name FechaEnv */
    public static final String COLUMNNAME_FechaEnv = "FechaEnv";

	/** Set Sending Date	  */
	public void setFechaEnv (Timestamp FechaEnv);

	/** Get Sending Date	  */
	public Timestamp getFechaEnv();

    /** Column name FechaPrealta */
    public static final String COLUMNNAME_FechaPrealta = "FechaPrealta";

	/** Set PreDischarge Date.
	  * PreDischarge Date
	  */
	public void setFechaPrealta (Timestamp FechaPrealta);

	/** Get PreDischarge Date.
	  * PreDischarge Date
	  */
	public Timestamp getFechaPrealta();

    /** Column name FechaTraslado */
    public static final String COLUMNNAME_FechaTraslado = "FechaTraslado";

	/** Set Transfer Date.
	  * Transfer Date
	  */
	public void setFechaTraslado (Timestamp FechaTraslado);

	/** Get Transfer Date.
	  * Transfer Date
	  */
	public Timestamp getFechaTraslado();

    /** Column name FormatoArchivo */
    public static final String COLUMNNAME_FormatoArchivo = "FormatoArchivo";

	/** Set File Format.
	  * File Format
	  */
	public void setFormatoArchivo (String FormatoArchivo);

	/** Get File Format.
	  * File Format
	  */
	public String getFormatoArchivo();

    /** Column name GrandTotal */
    public static final String COLUMNNAME_GrandTotal = "GrandTotal";

	/** Set Grand Total.
	  * Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal);

	/** Get Grand Total.
	  * Total amount of document
	  */
	public BigDecimal getGrandTotal();

    /** Column name ImpBrazalete */
    public static final String COLUMNNAME_ImpBrazalete = "ImpBrazalete";

	/** Set Print Bracelet.
	  * Print Bracelet
	  */
	public void setImpBrazalete (String ImpBrazalete);

	/** Get Print Bracelet.
	  * Print Bracelet
	  */
	public String getImpBrazalete();

    /** Column name ImpConsent */
    public static final String COLUMNNAME_ImpConsent = "ImpConsent";

	/** Set Print Consent.
	  * Print Consent
	  */
	public void setImpConsent (String ImpConsent);

	/** Get Print Consent.
	  * Print Consent
	  */
	public String getImpConsent();

    /** Column name ImpContrato */
    public static final String COLUMNNAME_ImpContrato = "ImpContrato";

	/** Set Print Contract.
	  * Print contract
	  */
	public void setImpContrato (String ImpContrato);

	/** Get Print Contract.
	  * Print contract
	  */
	public String getImpContrato();

    /** Column name ImpDatos */
    public static final String COLUMNNAME_ImpDatos = "ImpDatos";

	/** Set Print data.
	  * Print General Data
	  */
	public void setImpDatos (String ImpDatos);

	/** Get Print data.
	  * Print General Data
	  */
	public String getImpDatos();

    /** Column name InformeAlta */
    public static final String COLUMNNAME_InformeAlta = "InformeAlta";

	/** Set Discharge Report	  */
	public void setInformeAlta (String InformeAlta);

	/** Get Discharge Report	  */
	public String getInformeAlta();

    /** Column name InstitutionalStatus */
    public static final String COLUMNNAME_InstitutionalStatus = "InstitutionalStatus";

	/** Set Institutional Status.
	  * Institutional Status
	  */
	public void setInstitutionalStatus (String InstitutionalStatus);

	/** Get Institutional Status.
	  * Institutional Status
	  */
	public String getInstitutionalStatus();

    /** Column name InstitutionalStep */
    public static final String COLUMNNAME_InstitutionalStep = "InstitutionalStep";

	/** Set Institutional Step.
	  * Insurance that has the liability for institutional charges
	  */
	public void setInstitutionalStep (String InstitutionalStep);

	/** Get Institutional Step.
	  * Insurance that has the liability for institutional charges
	  */
	public String getInstitutionalStep();

    /** Column name InstruccionAlta */
    public static final String COLUMNNAME_InstruccionAlta = "InstruccionAlta";

	/** Set Release Instructions	  */
	public void setInstruccionAlta (String InstruccionAlta);

	/** Get Release Instructions	  */
	public String getInstruccionAlta();

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

    /** Column name IsAutorizada */
    public static final String COLUMNNAME_IsAutorizada = "IsAutorizada";

	/** Set Is Authorized	  */
	public void setIsAutorizada (boolean IsAutorizada);

	/** Get Is Authorized	  */
	public boolean isAutorizada();

    /** Column name IsBloqueada */
    public static final String COLUMNNAME_IsBloqueada = "IsBloqueada";

	/** Set Is Locked	  */
	public void setIsBloqueada (boolean IsBloqueada);

	/** Get Is Locked	  */
	public boolean isBloqueada();

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

    /** Column name IsFactEspec */
    public static final String COLUMNNAME_IsFactEspec = "IsFactEspec";

	/** Set Billing multiple	  */
	public void setIsFactEspec (boolean IsFactEspec);

	/** Get Billing multiple	  */
	public boolean isFactEspec();

    /** Column name IsGenerated */
    public static final String COLUMNNAME_IsGenerated = "IsGenerated";

	/** Set Generated.
	  * This Line is generated
	  */
	public void setIsGenerated (boolean IsGenerated);

	/** Get Generated.
	  * This Line is generated
	  */
	public boolean isGenerated();

    /** Column name IsIdentifier */
    public static final String COLUMNNAME_IsIdentifier = "IsIdentifier";

	/** Set Identifier.
	  * This column is part of the record identifier
	  */
	public void setIsIdentifier (boolean IsIdentifier);

	/** Get Identifier.
	  * This column is part of the record identifier
	  */
	public boolean isIdentifier();

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

    /** Column name IsNewBorn */
    public static final String COLUMNNAME_IsNewBorn = "IsNewBorn";

	/** Set Is New Born.
	  * Is New Born
	  */
	public void setIsNewBorn (boolean IsNewBorn);

	/** Get Is New Born.
	  * Is New Born
	  */
	public boolean isNewBorn();

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

    /** Column name IsUso */
    public static final String COLUMNNAME_IsUso = "IsUso";

	/** Set Is in Use	  */
	public void setIsUso (boolean IsUso);

	/** Get Is in Use	  */
	public boolean isUso();

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

    /** Column name NoInsuranceCoverage */
    public static final String COLUMNNAME_NoInsuranceCoverage = "NoInsuranceCoverage";

	/** Set NoInsuranceCoverage	  */
	public void setNoInsuranceCoverage (boolean NoInsuranceCoverage);

	/** Get NoInsuranceCoverage	  */
	public boolean isNoInsuranceCoverage();

    /** Column name NombreArchivo */
    public static final String COLUMNNAME_NombreArchivo = "NombreArchivo";

	/** Set File Name.
	  * File Name
	  */
	public void setNombreArchivo (String NombreArchivo);

	/** Get File Name.
	  * File Name
	  */
	public String getNombreArchivo();

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

    /** Column name NoStatementAge */
    public static final String COLUMNNAME_NoStatementAge = "NoStatementAge";

	/** Set NoStatementAge	  */
	public void setNoStatementAge (boolean NoStatementAge);

	/** Get NoStatementAge	  */
	public boolean isNoStatementAge();

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

    /** Column name ProfessionalStatus */
    public static final String COLUMNNAME_ProfessionalStatus = "ProfessionalStatus";

	/** Set Professional Status.
	  * Professional Status
	  */
	public void setProfessionalStatus (String ProfessionalStatus);

	/** Get Professional Status.
	  * Professional Status
	  */
	public String getProfessionalStatus();

    /** Column name ProfessionalStep */
    public static final String COLUMNNAME_ProfessionalStep = "ProfessionalStep";

	/** Set Professional Step.
	  * Insurance that has the liability for professional charges
	  */
	public void setProfessionalStep (String ProfessionalStep);

	/** Get Professional Step.
	  * Insurance that has the liability for professional charges
	  */
	public String getProfessionalStep();

    /** Column name Ref_CtaPacFam_ID */
    public static final String COLUMNNAME_Ref_CtaPacFam_ID = "Ref_CtaPacFam_ID";

	/** Set Reference Familiar Encounter	  */
	public void setRef_CtaPacFam_ID (int Ref_CtaPacFam_ID);

	/** Get Reference Familiar Encounter	  */
	public int getRef_CtaPacFam_ID();

    /** Column name Ref_CtaPac_ID */
    public static final String COLUMNNAME_Ref_CtaPac_ID = "Ref_CtaPac_ID";

	/** Set Reference Encounter.
	  * Reference Encounter
	  */
	public void setRef_CtaPac_ID (int Ref_CtaPac_ID);

	/** Get Reference Encounter.
	  * Reference Encounter
	  */
	public int getRef_CtaPac_ID();

    /** Column name Ref_Unit_ID */
    public static final String COLUMNNAME_Ref_Unit_ID = "Ref_Unit_ID";

	/** Set Ref. unit.
	  * Refers to the unit
	  */
	public void setRef_Unit_ID (int Ref_Unit_ID);

	/** Get Ref. unit.
	  * Refers to the unit
	  */
	public int getRef_Unit_ID();

    /** Column name Requester */
    public static final String COLUMNNAME_Requester = "Requester";

	/** Set Requester	  */
	public void setRequester (String Requester);

	/** Get Requester	  */
	public String getRequester();

    /** Column name ResStatus */
    public static final String COLUMNNAME_ResStatus = "ResStatus";

	/** Set Resuscitative Status	  */
	public void setResStatus (String ResStatus);

	/** Get Resuscitative Status	  */
	public String getResStatus();

    /** Column name StatusAlta */
    public static final String COLUMNNAME_StatusAlta = "StatusAlta";

	/** Set StatusAlta.
	  * Status for the patient account
	  */
	public void setStatusAlta (String StatusAlta);

	/** Get StatusAlta.
	  * Status for the patient account
	  */
	public String getStatusAlta();

    /** Column name StatusEleg */
    public static final String COLUMNNAME_StatusEleg = "StatusEleg";

	/** Set StatusEleg	  */
	public void setStatusEleg (boolean StatusEleg);

	/** Get StatusEleg	  */
	public boolean isStatusEleg();

    /** Column name TipoArea */
    public static final String COLUMNNAME_TipoArea = "TipoArea";

	/** Set Area Type.
	  * Admission Area Type
	  */
	public void setTipoArea (String TipoArea);

	/** Get Area Type.
	  * Admission Area Type
	  */
	public String getTipoArea();

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();

    /** Column name ValidOnQueue */
    public static final String COLUMNNAME_ValidOnQueue = "ValidOnQueue";

	/** Set Valid On Queue	  */
	public void setValidOnQueue (boolean ValidOnQueue);

	/** Get Valid On Queue	  */
	public boolean isValidOnQueue();
}
