/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ActPacienteIndH
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ActPacienteIndH 
{

    /** TableName=EXME_ActPacienteIndH */
    public static final String Table_Name = "EXME_ActPacienteIndH";

    /** AD_Table_ID=1000084 */
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

    /** Column name ApprovalUser */
    public static final String COLUMNNAME_ApprovalUser = "ApprovalUser";

	/** Set ApprovalUser	  */
	public void setApprovalUser (int ApprovalUser);

	/** Get ApprovalUser	  */
	public int getApprovalUser();

    /** Column name AuthenticatedBy */
    public static final String COLUMNNAME_AuthenticatedBy = "AuthenticatedBy";

	/** Set Authenticated By	  */
	public void setAuthenticatedBy (int AuthenticatedBy);

	/** Get Authenticated By	  */
	public int getAuthenticatedBy();

    /** Column name CanceledBy */
    public static final String COLUMNNAME_CanceledBy = "CanceledBy";

	/** Set Canceled By.
	  * Canceled By
	  */
	public void setCanceledBy (int CanceledBy);

	/** Get Canceled By.
	  * Canceled By
	  */
	public int getCanceledBy();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

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

    /** Column name ChargeAmt */
    public static final String COLUMNNAME_ChargeAmt = "ChargeAmt";

	/** Set Charge amount.
	  * Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt);

	/** Get Charge amount.
	  * Charge Amount
	  */
	public BigDecimal getChargeAmt();

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

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address .
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address .
	  * Location or Address
	  */
	public int getC_Location_ID();

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

    /** Column name DateApproved */
    public static final String COLUMNNAME_DateApproved = "DateApproved";

	/** Set Date Approved	  */
	public void setDateApproved (Timestamp DateApproved);

	/** Get Date Approved	  */
	public Timestamp getDateApproved();

    /** Column name DateCanceled */
    public static final String COLUMNNAME_DateCanceled = "DateCanceled";

	/** Set Date Canceled	  */
	public void setDateCanceled (Timestamp DateCanceled);

	/** Get Date Canceled	  */
	public Timestamp getDateCanceled();

    /** Column name DateDelivered */
    public static final String COLUMNNAME_DateDelivered = "DateDelivered";

	/** Set Date Delivered.
	  * Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered);

	/** Get Date Delivered.
	  * Date when the product was delivered
	  */
	public Timestamp getDateDelivered();

    /** Column name DateInvoiced */
    public static final String COLUMNNAME_DateInvoiced = "DateInvoiced";

	/** Set Date Invoiced.
	  * Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced);

	/** Get Date Invoiced.
	  * Date printed on Invoice
	  */
	public Timestamp getDateInvoiced();

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

    /** Column name DatePromised */
    public static final String COLUMNNAME_DatePromised = "DatePromised";

	/** Set Date Promised.
	  * Date Order was promised
	  */
	public void setDatePromised (Timestamp DatePromised);

	/** Get Date Promised.
	  * Date Order was promised
	  */
	public Timestamp getDatePromised();

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

    /** Column name Diagnostico */
    public static final String COLUMNNAME_Diagnostico = "Diagnostico";

	/** Set Diagnostic.
	  * Diagnostic
	  */
	public void setDiagnostico (String Diagnostico);

	/** Get Diagnostic.
	  * Diagnostic
	  */
	public String getDiagnostico();

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

    /** Column name EXME_EspecialidadSol_ID */
    public static final String COLUMNNAME_EXME_EspecialidadSol_ID = "EXME_EspecialidadSol_ID";

	/** Set Specialty Applicant.
	  * Specialty requesting the service
	  */
	public void setEXME_EspecialidadSol_ID (int EXME_EspecialidadSol_ID);

	/** Get Specialty Applicant.
	  * Specialty requesting the service
	  */
	public int getEXME_EspecialidadSol_ID();

    /** Column name EXME_EspecimenCondicion_ID */
    public static final String COLUMNNAME_EXME_EspecimenCondicion_ID = "EXME_EspecimenCondicion_ID";

	/** Set Condition/Disposition of Specimen.
	  * Condition/Disposition of Specimen
	  */
	public void setEXME_EspecimenCondicion_ID (int EXME_EspecimenCondicion_ID);

	/** Get Condition/Disposition of Specimen.
	  * Condition/Disposition of Specimen
	  */
	public int getEXME_EspecimenCondicion_ID();

	public I_EXME_EspecimenCondicion getEXME_EspecimenCondicion() throws RuntimeException;

    /** Column name EXME_Especimen_ID */
    public static final String COLUMNNAME_EXME_Especimen_ID = "EXME_Especimen_ID";

	/** Set Test Specimen.
	  * Test Specimen
	  */
	public void setEXME_Especimen_ID (int EXME_Especimen_ID);

	/** Get Test Specimen.
	  * Test Specimen
	  */
	public int getEXME_Especimen_ID();

	public I_EXME_Especimen getEXME_Especimen() throws RuntimeException;

    /** Column name EXME_Farmacia_ID */
    public static final String COLUMNNAME_EXME_Farmacia_ID = "EXME_Farmacia_ID";

	/** Set Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID);

	/** Get Pharmacy	  */
	public int getEXME_Farmacia_ID();

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException;

    /** Column name EXME_Impresora_ID */
    public static final String COLUMNNAME_EXME_Impresora_ID = "EXME_Impresora_ID";

	/** Set printer.
	  * printer
	  */
	public void setEXME_Impresora_ID (int EXME_Impresora_ID);

	/** Get printer.
	  * printer
	  */
	public int getEXME_Impresora_ID();

	public I_EXME_Impresora getEXME_Impresora() throws RuntimeException;

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

    /** Column name EXME_MedicoSol_ID */
    public static final String COLUMNNAME_EXME_MedicoSol_ID = "EXME_MedicoSol_ID";

	/** Set Medical Applicants.
	  * Physician requesting the service
	  */
	public void setEXME_MedicoSol_ID (int EXME_MedicoSol_ID);

	/** Get Medical Applicants.
	  * Physician requesting the service
	  */
	public int getEXME_MedicoSol_ID();

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

    /** Column name EXME_Tratamientos_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_ID = "EXME_Tratamientos_ID";

	/** Set Treatments.
	  * Treatments
	  */
	public void setEXME_Tratamientos_ID (int EXME_Tratamientos_ID);

	/** Get Treatments.
	  * Treatments
	  */
	public int getEXME_Tratamientos_ID();

	public I_EXME_Tratamientos getEXME_Tratamientos() throws RuntimeException;

    /** Column name EXME_Tratamientos_Sesion_ID */
    public static final String COLUMNNAME_EXME_Tratamientos_Sesion_ID = "EXME_Tratamientos_Sesion_ID";

	/** Set Treatment sessions	  */
	public void setEXME_Tratamientos_Sesion_ID (int EXME_Tratamientos_Sesion_ID);

	/** Get Treatment sessions	  */
	public int getEXME_Tratamientos_Sesion_ID();

	public I_EXME_Tratamientos_Sesion getEXME_Tratamientos_Sesion() throws RuntimeException;

    /** Column name Fecha_Fin */
    public static final String COLUMNNAME_Fecha_Fin = "Fecha_Fin";

	/** Set Ending Date.
	  * Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin);

	/** Get Ending Date.
	  * Date of ending of intervention
	  */
	public Timestamp getFecha_Fin();

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

    /** Column name Intervalo */
    public static final String COLUMNNAME_Intervalo = "Intervalo";

	/** Set Interval.
	  * Interval
	  */
	public void setIntervalo (BigDecimal Intervalo);

	/** Get Interval.
	  * Interval
	  */
	public BigDecimal getIntervalo();

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

    /** Column name IsDelivered */
    public static final String COLUMNNAME_IsDelivered = "IsDelivered";

	/** Set Delivered	  */
	public void setIsDelivered (boolean IsDelivered);

	/** Get Delivered	  */
	public boolean isDelivered();

    /** Column name IsDiscountPrinted */
    public static final String COLUMNNAME_IsDiscountPrinted = "IsDiscountPrinted";

	/** Set Discount Printed.
	  * Print Discount on Invoice and Order
	  */
	public void setIsDiscountPrinted (boolean IsDiscountPrinted);

	/** Get Discount Printed.
	  * Print Discount on Invoice and Order
	  */
	public boolean isDiscountPrinted();

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

    /** Column name IsService */
    public static final String COLUMNNAME_IsService = "IsService";

	/** Set Is Service.
	  * Is Service
	  */
	public void setIsService (boolean IsService);

	/** Get Is Service.
	  * Is Service
	  */
	public boolean isService();

    /** Column name IsTaxIncluded */
    public static final String COLUMNNAME_IsTaxIncluded = "IsTaxIncluded";

	/** Set Price includes Tax.
	  * Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded);

	/** Get Price includes Tax.
	  * Tax is included in the price 
	  */
	public boolean isTaxIncluded();

    /** Column name MedicoNombre */
    public static final String COLUMNNAME_MedicoNombre = "MedicoNombre";

	/** Set M. D. Name.
	  * M. D. Name
	  */
	public void setMedicoNombre (String MedicoNombre);

	/** Get M. D. Name.
	  * M. D. Name
	  */
	public String getMedicoNombre();

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

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

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

    /** Column name NotedBy */
    public static final String COLUMNNAME_NotedBy = "NotedBy";

	/** Set Noted By	  */
	public void setNotedBy (int NotedBy);

	/** Get Noted By	  */
	public int getNotedBy();

    /** Column name NotedDate */
    public static final String COLUMNNAME_NotedDate = "NotedDate";

	/** Set Noted Date	  */
	public void setNotedDate (Timestamp NotedDate);

	/** Get Noted Date	  */
	public Timestamp getNotedDate();

    /** Column name OrderType */
    public static final String COLUMNNAME_OrderType = "OrderType";

	/** Set OrderType	  */
	public void setOrderType (String OrderType);

	/** Get OrderType	  */
	public String getOrderType();

    /** Column name Parent_ID */
    public static final String COLUMNNAME_Parent_ID = "Parent_ID";

	/** Set Parent.
	  * Parent of Entity
	  */
	public void setParent_ID (int Parent_ID);

	/** Get Parent.
	  * Parent of Entity
	  */
	public int getParent_ID();

    /** Column name PreAlta */
    public static final String COLUMNNAME_PreAlta = "PreAlta";

	/** Set Predischarge	  */
	public void setPreAlta (boolean PreAlta);

	/** Get Predischarge	  */
	public boolean isPreAlta();

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

    /** Column name Protocolo */
    public static final String COLUMNNAME_Protocolo = "Protocolo";

	/** Set Protocol.
	  * Protocol
	  */
	public void setProtocolo (String Protocolo);

	/** Get Protocol.
	  * Protocol
	  */
	public String getProtocolo();

    /** Column name ReadBack */
    public static final String COLUMNNAME_ReadBack = "ReadBack";

	/** Set Read Back.
	  * Read Back
	  */
	public void setReadBack (String ReadBack);

	/** Get Read Back.
	  * Read Back
	  */
	public String getReadBack();

    /** Column name Ref_ActPacienteIndH_ID */
    public static final String COLUMNNAME_Ref_ActPacienteIndH_ID = "Ref_ActPacienteIndH_ID";

	/** Set Indication Ref..
	  * Indication Reference
	  */
	public void setRef_ActPacienteIndH_ID (int Ref_ActPacienteIndH_ID);

	/** Get Indication Ref..
	  * Indication Reference
	  */
	public int getRef_ActPacienteIndH_ID();

    /** Column name Responsable */
    public static final String COLUMNNAME_Responsable = "Responsable";

	/** Set Responsible.
	  * Responsible
	  */
	public void setResponsable (String Responsable);

	/** Get Responsible.
	  * Responsible
	  */
	public String getResponsable();

    /** Column name SendOrder */
    public static final String COLUMNNAME_SendOrder = "SendOrder";

	/** Set Send Order	  */
	public void setSendOrder (int SendOrder);

	/** Get Send Order	  */
	public int getSendOrder();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

    /** Column name TotalLines */
    public static final String COLUMNNAME_TotalLines = "TotalLines";

	/** Set Total Lines.
	  * Total of all document lines
	  */
	public void setTotalLines (BigDecimal TotalLines);

	/** Get Total Lines.
	  * Total of all document lines
	  */
	public BigDecimal getTotalLines();

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();
}
