/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ActPacienteInd
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ActPacienteInd 
{

    /** TableName=EXME_ActPacienteInd */
    public static final String Table_Name = "EXME_ActPacienteInd";

    /** AD_Table_ID=1000079 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Org_Dest_ID */
    public static final String COLUMNNAME_AD_Org_Dest_ID = "AD_Org_Dest_ID";

	/** Set Target Organization.
	  * The organization to refer to
	  */
	public void setAD_Org_Dest_ID (int AD_Org_Dest_ID);

	/** Get Target Organization.
	  * The organization to refer to
	  */
	public int getAD_Org_Dest_ID();

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

    /** Column name Anotaciones */
    public static final String COLUMNNAME_Anotaciones = "Anotaciones";

	/** Set Notes.
	  * Notes related to the annexed image of the indication
	  */
	public void setAnotaciones (String Anotaciones);

	/** Get Notes.
	  * Notes related to the annexed image of the indication
	  */
	public String getAnotaciones();

    /** Column name Billable */
    public static final String COLUMNNAME_Billable = "Billable";

	/** Set Billable	  */
	public void setBillable (boolean Billable);

	/** Get Billable	  */
	public boolean isBillable();

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

    /** Column name CantidadToma */
    public static final String COLUMNNAME_CantidadToma = "CantidadToma";

	/** Set Amount to take	  */
	public void setCantidadToma (BigDecimal CantidadToma);

	/** Get Amount to take	  */
	public BigDecimal getCantidadToma();

    /** Column name CargosGenerados */
    public static final String COLUMNNAME_CargosGenerados = "CargosGenerados";

	/** Set CargosGenerados	  */
	public void setCargosGenerados (boolean CargosGenerados);

	/** Get CargosGenerados	  */
	public boolean isCargosGenerados();

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

    /** Column name CgoProcesado */
    public static final String COLUMNNAME_CgoProcesado = "CgoProcesado";

	/** Set Charge processed	  */
	public void setCgoProcesado (boolean CgoProcesado);

	/** Get Charge processed	  */
	public boolean isCgoProcesado();

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

    /** Column name ComponentType */
    public static final String COLUMNNAME_ComponentType = "ComponentType";

	/** Set Component Type.
	  * Component Type for a Bill of Material or Formula
	  */
	public void setComponentType (String ComponentType);

	/** Get Component Type.
	  * Component Type for a Bill of Material or Formula
	  */
	public String getComponentType();

    /** Column name ConsultingProvider */
    public static final String COLUMNNAME_ConsultingProvider = "ConsultingProvider";

	/** Set Consulting Provider.
	  * Consulting Provider / Facility
	  */
	public void setConsultingProvider (String ConsultingProvider);

	/** Get Consulting Provider.
	  * Consulting Provider / Facility
	  */
	public String getConsultingProvider();

    /** Column name Costo */
    public static final String COLUMNNAME_Costo = "Costo";

	/** Set Cost.
	  * Cost
	  */
	public void setCosto (BigDecimal Costo);

	/** Get Cost.
	  * Cost
	  */
	public BigDecimal getCosto();

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

    /** Column name C_UOMVolume_ID */
    public static final String COLUMNNAME_C_UOMVolume_ID = "C_UOMVolume_ID";

	/** Set Pack UOM.
	  * Unit of measure of volume or packing
	  */
	public void setC_UOMVolume_ID (int C_UOMVolume_ID);

	/** Get Pack UOM.
	  * Unit of measure of volume or packing
	  */
	public int getC_UOMVolume_ID();

    /** Column name DateCanceled */
    public static final String COLUMNNAME_DateCanceled = "DateCanceled";

	/** Set Date Canceled	  */
	public void setDateCanceled (Timestamp DateCanceled);

	/** Get Date Canceled	  */
	public Timestamp getDateCanceled();

    /** Column name DateCollected */
    public static final String COLUMNNAME_DateCollected = "DateCollected";

	/** Set Collection date.
	  * Specimen collection date
	  */
	public void setDateCollected (Timestamp DateCollected);

	/** Get Collection date.
	  * Specimen collection date
	  */
	public Timestamp getDateCollected();

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

    /** Column name DateReceived */
    public static final String COLUMNNAME_DateReceived = "DateReceived";

	/** Set Date received.
	  * Date a product was received
	  */
	public void setDateReceived (Timestamp DateReceived);

	/** Get Date received.
	  * Date a product was received
	  */
	public Timestamp getDateReceived();

    /** Column name DeliveredBy */
    public static final String COLUMNNAME_DeliveredBy = "DeliveredBy";

	/** Set Delivered By	  */
	public void setDeliveredBy (int DeliveredBy);

	/** Get Delivered By	  */
	public int getDeliveredBy();

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

    /** Column name Discount */
    public static final String COLUMNNAME_Discount = "Discount";

	/** Set Discount %.
	  * Discount in percent
	  */
	public void setDiscount (BigDecimal Discount);

	/** Get Discount %.
	  * Discount in percent
	  */
	public BigDecimal getDiscount();

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

    /** Column name Dose_txt */
    public static final String COLUMNNAME_Dose_txt = "Dose_txt";

	/** Set Dose	  */
	public void setDose_txt (String Dose_txt);

	/** Get Dose	  */
	public String getDose_txt();

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

    /** Column name EXME_ActPacienteInd_ID */
    public static final String COLUMNNAME_EXME_ActPacienteInd_ID = "EXME_ActPacienteInd_ID";

	/** Set Indication's detail.
	  * Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID);

	/** Get Indication's detail.
	  * Indication's detail
	  */
	public int getEXME_ActPacienteInd_ID();

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

    /** Column name EXME_Diagnostico2_ID */
    public static final String COLUMNNAME_EXME_Diagnostico2_ID = "EXME_Diagnostico2_ID";

	/** Set Second Diagnostic.
	  * Second Diagnostic
	  */
	public void setEXME_Diagnostico2_ID (int EXME_Diagnostico2_ID);

	/** Get Second Diagnostic.
	  * Second Diagnostic
	  */
	public int getEXME_Diagnostico2_ID();

    /** Column name EXME_Diagnostico3_ID */
    public static final String COLUMNNAME_EXME_Diagnostico3_ID = "EXME_Diagnostico3_ID";

	/** Set Third Diagnostic.
	  * Third Diagnostic
	  */
	public void setEXME_Diagnostico3_ID (int EXME_Diagnostico3_ID);

	/** Get Third Diagnostic.
	  * Third Diagnostic
	  */
	public int getEXME_Diagnostico3_ID();

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

    /** Column name EXME_Dosis_ID */
    public static final String COLUMNNAME_EXME_Dosis_ID = "EXME_Dosis_ID";

	/** Set Dose	  */
	public void setEXME_Dosis_ID (int EXME_Dosis_ID);

	/** Get Dose	  */
	public int getEXME_Dosis_ID();

	public I_EXME_Dosis getEXME_Dosis() throws RuntimeException;

    /** Column name EXME_Equipo_ID */
    public static final String COLUMNNAME_EXME_Equipo_ID = "EXME_Equipo_ID";

	/** Set Equipment.
	  * Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID);

	/** Get Equipment.
	  * Equipment
	  */
	public int getEXME_Equipo_ID();

	public I_EXME_Equipo getEXME_Equipo() throws RuntimeException;

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

    /** Column name EXME_EsqDesLine_ID */
    public static final String COLUMNNAME_EXME_EsqDesLine_ID = "EXME_EsqDesLine_ID";

	/** Set Price List Discount.
	  * Lines of discount schema
	  */
	public void setEXME_EsqDesLine_ID (int EXME_EsqDesLine_ID);

	/** Get Price List Discount.
	  * Lines of discount schema
	  */
	public int getEXME_EsqDesLine_ID();

	public I_EXME_EsqDesLine getEXME_EsqDesLine() throws RuntimeException;

    /** Column name EXME_Farmacia_ID */
    public static final String COLUMNNAME_EXME_Farmacia_ID = "EXME_Farmacia_ID";

	/** Set Pharmacy	  */
	public void setEXME_Farmacia_ID (int EXME_Farmacia_ID);

	/** Get Pharmacy	  */
	public int getEXME_Farmacia_ID();

	public I_EXME_Farmacia getEXME_Farmacia() throws RuntimeException;

    /** Column name EXME_GenProduct_ID */
    public static final String COLUMNNAME_EXME_GenProduct_ID = "EXME_GenProduct_ID";

	/** Set Generic Product	  */
	public void setEXME_GenProduct_ID (int EXME_GenProduct_ID);

	/** Get Generic Product	  */
	public int getEXME_GenProduct_ID();

	public I_EXME_GenProduct getEXME_GenProduct() throws RuntimeException;

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

    /** Column name EXME_Instructions_ID */
    public static final String COLUMNNAME_EXME_Instructions_ID = "EXME_Instructions_ID";

	/** Set Instructions.
	  * General instructions
	  */
	public void setEXME_Instructions_ID (int EXME_Instructions_ID);

	/** Get Instructions.
	  * General instructions
	  */
	public int getEXME_Instructions_ID();

	public I_EXME_Instructions getEXME_Instructions() throws RuntimeException;

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

    /** Column name EXME_MedicoPer_ID */
    public static final String COLUMNNAME_EXME_MedicoPer_ID = "EXME_MedicoPer_ID";

	/** Set Performing Physician	  */
	public void setEXME_MedicoPer_ID (int EXME_MedicoPer_ID);

	/** Get Performing Physician	  */
	public int getEXME_MedicoPer_ID();

    /** Column name EXME_Modifiers_ID */
    public static final String COLUMNNAME_EXME_Modifiers_ID = "EXME_Modifiers_ID";

	/** Set EXME_Modifiers_ID	  */
	public void setEXME_Modifiers_ID (int EXME_Modifiers_ID);

	/** Get EXME_Modifiers_ID	  */
	public int getEXME_Modifiers_ID();

	public I_EXME_Modifiers getEXME_Modifiers() throws RuntimeException;

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

    /** Column name EXME_OrderSet_ID */
    public static final String COLUMNNAME_EXME_OrderSet_ID = "EXME_OrderSet_ID";

	/** Set Order Set	  */
	public void setEXME_OrderSet_ID (int EXME_OrderSet_ID);

	/** Get Order Set	  */
	public int getEXME_OrderSet_ID();

	public I_EXME_OrderSet getEXME_OrderSet() throws RuntimeException;

    /** Column name EXME_PerformingLab_ID */
    public static final String COLUMNNAME_EXME_PerformingLab_ID = "EXME_PerformingLab_ID";

	/** Set Performing Lab	  */
	public void setEXME_PerformingLab_ID (int EXME_PerformingLab_ID);

	/** Get Performing Lab	  */
	public int getEXME_PerformingLab_ID();

	public I_EXME_PerformingLab getEXME_PerformingLab() throws RuntimeException;

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

    /** Column name EXME_ReferInpatient_ID */
    public static final String COLUMNNAME_EXME_ReferInpatient_ID = "EXME_ReferInpatient_ID";

	/** Set Inpatient Reference	  */
	public void setEXME_ReferInpatient_ID (int EXME_ReferInpatient_ID);

	/** Get Inpatient Reference	  */
	public int getEXME_ReferInpatient_ID();

	public I_EXME_ReferInpatient getEXME_ReferInpatient() throws RuntimeException;

    /** Column name EXME_RevenueCode_ID */
    public static final String COLUMNNAME_EXME_RevenueCode_ID = "EXME_RevenueCode_ID";

	/** Set Revenue Code	  */
	public void setEXME_RevenueCode_ID (int EXME_RevenueCode_ID);

	/** Get Revenue Code	  */
	public int getEXME_RevenueCode_ID();

	public I_EXME_RevenueCode getEXME_RevenueCode() throws RuntimeException;

    /** Column name EXME_ViasAdministracion_ID */
    public static final String COLUMNNAME_EXME_ViasAdministracion_ID = "EXME_ViasAdministracion_ID";

	/** Set Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID);

	/** Get Route of Administration	  */
	public int getEXME_ViasAdministracion_ID();

	public I_EXME_ViasAdministracion getEXME_ViasAdministracion() throws RuntimeException;

    /** Column name Folio */
    public static final String COLUMNNAME_Folio = "Folio";

	/** Set Folio	  */
	public void setFolio (int Folio);

	/** Get Folio	  */
	public int getFolio();

    /** Column name FreightAmt */
    public static final String COLUMNNAME_FreightAmt = "FreightAmt";

	/** Set Freight Amount.
	  * Freight Amount 
	  */
	public void setFreightAmt (BigDecimal FreightAmt);

	/** Get Freight Amount.
	  * Freight Amount 
	  */
	public BigDecimal getFreightAmt();

    /** Column name Imagen */
    public static final String COLUMNNAME_Imagen = "Imagen";

	/** Set Image.
	  * Name of stored image
	  */
	public void setImagen (String Imagen);

	/** Get Image.
	  * Name of stored image
	  */
	public String getImagen();

    /** Column name IsDAW */
    public static final String COLUMNNAME_IsDAW = "IsDAW";

	/** Set Dispense as Written	  */
	public void setIsDAW (boolean IsDAW);

	/** Get Dispense as Written	  */
	public boolean isDAW();

    /** Column name IsDEA */
    public static final String COLUMNNAME_IsDEA = "IsDEA";

	/** Set Print DEA Number	  */
	public void setIsDEA (boolean IsDEA);

	/** Get Print DEA Number	  */
	public boolean isDEA();

    /** Column name IsDescription */
    public static final String COLUMNNAME_IsDescription = "IsDescription";

	/** Set Description Only.
	  * if true, the line is just description and no transaction
	  */
	public void setIsDescription (boolean IsDescription);

	/** Get Description Only.
	  * if true, the line is just description and no transaction
	  */
	public boolean isDescription();

    /** Column name IsExternal */
    public static final String COLUMNNAME_IsExternal = "IsExternal";

	/** Set External.
	  * External
	  */
	public void setIsExternal (boolean IsExternal);

	/** Get External.
	  * External
	  */
	public boolean isExternal();

    /** Column name IsInstruction */
    public static final String COLUMNNAME_IsInstruction = "IsInstruction";

	/** Set Is Instruction.
	  * Indicates if is an instruction
	  */
	public void setIsInstruction (boolean IsInstruction);

	/** Get Is Instruction.
	  * Indicates if is an instruction
	  */
	public boolean isInstruction();

    /** Column name IsOBXReviewed */
    public static final String COLUMNNAME_IsOBXReviewed = "IsOBXReviewed";

	/** Set Is OBX Reviewed.
	  * Is OBX Reviewed
	  */
	public void setIsOBXReviewed (boolean IsOBXReviewed);

	/** Get Is OBX Reviewed.
	  * Is OBX Reviewed
	  */
	public boolean isOBXReviewed();

    /** Column name IsPRN */
    public static final String COLUMNNAME_IsPRN = "IsPRN";

	/** Set When necessary	  */
	public void setIsPRN (boolean IsPRN);

	/** Get When necessary	  */
	public boolean isPRN();

    /** Column name IsTodayService */
    public static final String COLUMNNAME_IsTodayService = "IsTodayService";

	/** Set Today Service.
	  * Today Service
	  */
	public void setIsTodayService (boolean IsTodayService);

	/** Get Today Service.
	  * Today Service
	  */
	public boolean isTodayService();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name LineNetAmt */
    public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";

	/** Set Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt);

	/** Get Line Amount.
	  * Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt();

    /** Column name Lote */
    public static final String COLUMNNAME_Lote = "Lote";

	/** Set Lote	  */
	public void setLote (String Lote);

	/** Get Lote	  */
	public String getLote();

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

    /** Column name M_InOutLine_ID */
    public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";

	/** Set Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID);

	/** Get Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID();

	public I_M_InOutLine getM_InOutLine() throws RuntimeException;

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

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

    /** Column name M_Shipper_ID */
    public static final String COLUMNNAME_M_Shipper_ID = "M_Shipper_ID";

	/** Set Shipper.
	  * Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID);

	/** Get Shipper.
	  * Method or manner of product delivery
	  */
	public int getM_Shipper_ID();

	public I_M_Shipper getM_Shipper() throws RuntimeException;

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

    /** Column name NumDias */
    public static final String COLUMNNAME_NumDias = "NumDias";

	/** Set Number of days	  */
	public void setNumDias (BigDecimal NumDias);

	/** Get Number of days	  */
	public BigDecimal getNumDias();

    /** Column name OtherInstructions */
    public static final String COLUMNNAME_OtherInstructions = "OtherInstructions";

	/** Set Other Instructions.
	  * Other Instructions
	  */
	public void setOtherInstructions (String OtherInstructions);

	/** Get Other Instructions.
	  * Other Instructions
	  */
	public String getOtherInstructions();

    /** Column name PriceActual */
    public static final String COLUMNNAME_PriceActual = "PriceActual";

	/** Set Unit Price.
	  * Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual);

	/** Get Unit Price.
	  * Actual Price 
	  */
	public BigDecimal getPriceActual();

    /** Column name PriceLimit */
    public static final String COLUMNNAME_PriceLimit = "PriceLimit";

	/** Set Limit Price.
	  * Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit);

	/** Get Limit Price.
	  * Lowest price for a product
	  */
	public BigDecimal getPriceLimit();

    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/** Set List Price.
	  * List Price
	  */
	public void setPriceList (BigDecimal PriceList);

	/** Get List Price.
	  * List Price
	  */
	public BigDecimal getPriceList();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Prod_Substitute_ID */
    public static final String COLUMNNAME_Prod_Substitute_ID = "Prod_Substitute_ID";

	/** Set Product Substitute.
	  * Product Substitute
	  */
	public void setProd_Substitute_ID (int Prod_Substitute_ID);

	/** Get Product Substitute.
	  * Product Substitute
	  */
	public int getProd_Substitute_ID();

    /** Column name Proveedor */
    public static final String COLUMNNAME_Proveedor = "Proveedor";

	/** Set Supplier	  */
	public void setProveedor (String Proveedor);

	/** Get Supplier	  */
	public String getProveedor();

    /** Column name QtyDelivered */
    public static final String COLUMNNAME_QtyDelivered = "QtyDelivered";

	/** Set Delivered Quantity.
	  * Delivered Quantity
	  */
	public void setQtyDelivered (BigDecimal QtyDelivered);

	/** Get Delivered Quantity.
	  * Delivered Quantity
	  */
	public BigDecimal getQtyDelivered();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name QtyInvoiced */
    public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";

	/** Set Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced);

	/** Get Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced();

    /** Column name QtyOrdered */
    public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";

	/** Set Ordered Quantity.
	  * Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered);

	/** Get Ordered Quantity.
	  * Ordered Quantity
	  */
	public BigDecimal getQtyOrdered();

    /** Column name QtyOrdered_Vol */
    public static final String COLUMNNAME_QtyOrdered_Vol = "QtyOrdered_Vol";

	/** Set Ordered Qty Pack.
	  * Ordered Quantity Packs ordered
	  */
	public void setQtyOrdered_Vol (BigDecimal QtyOrdered_Vol);

	/** Get Ordered Qty Pack.
	  * Ordered Quantity Packs ordered
	  */
	public BigDecimal getQtyOrdered_Vol();

    /** Column name QtyReserved */
    public static final String COLUMNNAME_QtyReserved = "QtyReserved";

	/** Set Reserved Quantity.
	  * Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved);

	/** Get Reserved Quantity.
	  * Reserved Quantity
	  */
	public BigDecimal getQtyReserved();

    /** Column name Quantity_txt */
    public static final String COLUMNNAME_Quantity_txt = "Quantity_txt";

	/** Set Quantity	  */
	public void setQuantity_txt (String Quantity_txt);

	/** Get Quantity	  */
	public String getQuantity_txt();

    /** Column name Ref_ActPacienteInd_ID */
    public static final String COLUMNNAME_Ref_ActPacienteInd_ID = "Ref_ActPacienteInd_ID";

	/** Set Reference to Patient Activity  Indications.
	  * Reference to Patient Activity Indications
	  */
	public void setRef_ActPacienteInd_ID (int Ref_ActPacienteInd_ID);

	/** Get Reference to Patient Activity  Indications.
	  * Reference to Patient Activity Indications
	  */
	public int getRef_ActPacienteInd_ID();

    /** Column name RequestType */
    public static final String COLUMNNAME_RequestType = "RequestType";

	/** Set Request Type	  */
	public void setRequestType (String RequestType);

	/** Get Request Type	  */
	public String getRequestType();

    /** Column name ResultStatus */
    public static final String COLUMNNAME_ResultStatus = "ResultStatus";

	/** Set Result Status	  */
	public void setResultStatus (String ResultStatus);

	/** Get Result Status	  */
	public String getResultStatus();

    /** Column name Resurtidos */
    public static final String COLUMNNAME_Resurtidos = "Resurtidos";

	/** Set Refills	  */
	public void setResurtidos (int Resurtidos);

	/** Get Refills	  */
	public int getResurtidos();

    /** Column name RisID */
    public static final String COLUMNNAME_RisID = "RisID";

	/** Set RIS Application Number	  */
	public void setRisID (int RisID);

	/** Get RIS Application Number	  */
	public int getRisID();

    /** Column name Sequence */
    public static final String COLUMNNAME_Sequence = "Sequence";

	/** Set Sequence	  */
	public void setSequence (int Sequence);

	/** Get Sequence	  */
	public int getSequence();

    /** Column name Surtir */
    public static final String COLUMNNAME_Surtir = "Surtir";

	/** Set Deliver.
	  * If the product or service will deliver internally
	  */
	public void setSurtir (boolean Surtir);

	/** Get Deliver.
	  * If the product or service will deliver internally
	  */
	public boolean isSurtir();

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

    /** Column name TipoSurtido */
    public static final String COLUMNNAME_TipoSurtido = "TipoSurtido";

	/** Set Dispense as written.
	  * Dispense as written  (DAW)
	  */
	public void setTipoSurtido (String TipoSurtido);

	/** Get Dispense as written.
	  * Dispense as written  (DAW)
	  */
	public String getTipoSurtido();

    /** Column name TomadoCasa */
    public static final String COLUMNNAME_TomadoCasa = "TomadoCasa";

	/** Set Taken at home	  */
	public void setTomadoCasa (boolean TomadoCasa);

	/** Get Taken at home	  */
	public boolean isTomadoCasa();

    /** Column name VecesDia */
    public static final String COLUMNNAME_VecesDia = "VecesDia";

	/** Set Day Timing	  */
	public void setVecesDia (BigDecimal VecesDia);

	/** Get Day Timing	  */
	public BigDecimal getVecesDia();

    /** Column name WarehouseLocation */
    public static final String COLUMNNAME_WarehouseLocation = "WarehouseLocation";

	/** Set Warehouse address.
	  * Warehouse address
	  */
	public void setWarehouseLocation (String WarehouseLocation);

	/** Get Warehouse address.
	  * Warehouse address
	  */
	public String getWarehouseLocation();

    /** Column name WarehouseName */
    public static final String COLUMNNAME_WarehouseName = "WarehouseName";

	/** Set Warehouse Name.
	  * Warehouse Name
	  */
	public void setWarehouseName (String WarehouseName);

	/** Get Warehouse Name.
	  * Warehouse Name
	  */
	public String getWarehouseName();
}
