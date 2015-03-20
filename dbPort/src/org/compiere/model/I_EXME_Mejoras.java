/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Mejoras
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Mejoras 
{

    /** TableName=EXME_Mejoras */
    public static final String Table_Name = "EXME_Mejoras";

    /** AD_Table_ID=1200138 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Asset_Group_ID */
    public static final String COLUMNNAME_A_Asset_Group_ID = "A_Asset_Group_ID";

	/** Set Asset Group.
	  * Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID);

	/** Get Asset Group.
	  * Group of Assets
	  */
	public int getA_Asset_Group_ID();

	public I_A_Asset_Group getA_Asset_Group() throws RuntimeException;

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

    /** Column name AgregarPreguntas */
    public static final String COLUMNNAME_AgregarPreguntas = "AgregarPreguntas";

	/** Set Add Questions	  */
	public void setAgregarPreguntas (boolean AgregarPreguntas);

	/** Get Add Questions	  */
	public boolean isAgregarPreguntas();

    /** Column name CalcImpFact */
    public static final String COLUMNNAME_CalcImpFact = "CalcImpFact";

	/** Set Recalculate Taxes when Billing	  */
	public void setCalcImpFact (boolean CalcImpFact);

	/** Get Recalculate Taxes when Billing	  */
	public boolean isCalcImpFact();

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

	public I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name ChargeOnStock */
    public static final String COLUMNNAME_ChargeOnStock = "ChargeOnStock";

	/** Set Charge on Stock.
	  * Charge on Stock
	  */
	public void setChargeOnStock (boolean ChargeOnStock);

	/** Get Charge on Stock.
	  * Charge on Stock
	  */
	public boolean isChargeOnStock();

    /** Column name CleanBed */
    public static final String COLUMNNAME_CleanBed = "CleanBed";

	/** Set Clean bed after release.
	  * Clean bed after release
	  */
	public void setCleanBed (boolean CleanBed);

	/** Get Clean bed after release.
	  * Clean bed after release
	  */
	public boolean isCleanBed();

    /** Column name ControlExistencias */
    public static final String COLUMNNAME_ControlExistencias = "ControlExistencias";

	/** Set Inventory Management.
	  * The warehouse uses inventory management
	  */
	public void setControlExistencias (boolean ControlExistencias);

	/** Get Inventory Management.
	  * The warehouse uses inventory management
	  */
	public boolean isControlExistencias();

    /** Column name CurpHist */
    public static final String COLUMNNAME_CurpHist = "CurpHist";

	/** Set CurpHistory.
	  * CurpHistory
	  */
	public void setCurpHist (boolean CurpHist);

	/** Get CurpHistory.
	  * CurpHistory
	  */
	public boolean isCurpHist();

    /** Column name CURPMandatory */
    public static final String COLUMNNAME_CURPMandatory = "CURPMandatory";

	/** Set CURPMandatory	  */
	public void setCURPMandatory (boolean CURPMandatory);

	/** Get CURPMandatory	  */
	public boolean isCURPMandatory();

    /** Column name DateFrom */
    public static final String COLUMNNAME_DateFrom = "DateFrom";

	/** Set Date From.
	  * Starting date for a range
	  */
	public void setDateFrom (Timestamp DateFrom);

	/** Get Date From.
	  * Starting date for a range
	  */
	public Timestamp getDateFrom();

    /** Column name DateTo */
    public static final String COLUMNNAME_DateTo = "DateTo";

	/** Set Date To.
	  * End date of a date range
	  */
	public void setDateTo (Timestamp DateTo);

	/** Get Date To.
	  * End date of a date range
	  */
	public Timestamp getDateTo();

    /** Column name DigitosArea */
    public static final String COLUMNNAME_DigitosArea = "DigitosArea";

	/** Set Area digit number	  */
	public void setDigitosArea (int DigitosArea);

	/** Get Area digit number	  */
	public int getDigitosArea();

    /** Column name DigitosTel */
    public static final String COLUMNNAME_DigitosTel = "DigitosTel";

	/** Set Telephone digits number	  */
	public void setDigitosTel (int DigitosTel);

	/** Get Telephone digits number	  */
	public int getDigitosTel();

    /** Column name eClaim_Status_Doc */
    public static final String COLUMNNAME_eClaim_Status_Doc = "eClaim_Status_Doc";

	/** Set eClaim_Status_Doc	  */
	public void seteClaim_Status_Doc (String eClaim_Status_Doc);

	/** Get eClaim_Status_Doc	  */
	public String geteClaim_Status_Doc();

    /** Column name EditPastSchedules */
    public static final String COLUMNNAME_EditPastSchedules = "EditPastSchedules";

	/** Set Edit past operating room's schedules	  */
	public void setEditPastSchedules (boolean EditPastSchedules);

	/** Get Edit past operating room's schedules	  */
	public boolean isEditPastSchedules();

    /** Column name EditProgOperatingTime */
    public static final String COLUMNNAME_EditProgOperatingTime = "EditProgOperatingTime";

	/** Set Edit Operating room time Programming.
	  * Allows programming restore operating room time, when it was made from a request for surgery.
	  */
	public void setEditProgOperatingTime (boolean EditProgOperatingTime);

	/** Get Edit Operating room time Programming.
	  * Allows programming restore operating room time, when it was made from a request for surgery.
	  */
	public boolean isEditProgOperatingTime();

    /** Column name EncounterAge */
    public static final String COLUMNNAME_EncounterAge = "EncounterAge";

	/** Set Age of the encounter  for conciliation.
	  * Time to consider that the encounter should be integrated into a new encounter
	  */
	public void setEncounterAge (BigDecimal EncounterAge);

	/** Get Age of the encounter  for conciliation.
	  * Time to consider that the encounter should be integrated into a new encounter
	  */
	public BigDecimal getEncounterAge();

    /** Column name EScriptSendingEnabled */
    public static final String COLUMNNAME_EScriptSendingEnabled = "EScriptSendingEnabled";

	/** Set eScript Sending Enabled	  */
	public void setEScriptSendingEnabled (boolean EScriptSendingEnabled);

	/** Get eScript Sending Enabled	  */
	public boolean isEScriptSendingEnabled();

    /** Column name EsquemaInstalacion */
    public static final String COLUMNNAME_EsquemaInstalacion = "EsquemaInstalacion";

	/** Set Installation Schema.
	  * Installation Schema
	  */
	public void setEsquemaInstalacion (String EsquemaInstalacion);

	/** Get Installation Schema.
	  * Installation Schema
	  */
	public String getEsquemaInstalacion();

    /** Column name EXME_Area_Ref_ID */
    public static final String COLUMNNAME_EXME_Area_Ref_ID = "EXME_Area_Ref_ID";

	/** Set Area of Amphitheater.
	  * Area of Amphitheater
	  */
	public void setEXME_Area_Ref_ID (int EXME_Area_Ref_ID);

	/** Get Area of Amphitheater.
	  * Area of Amphitheater
	  */
	public int getEXME_Area_Ref_ID();

    /** Column name EXME_CIFHdr_ID */
    public static final String COLUMNNAME_EXME_CIFHdr_ID = "EXME_CIFHdr_ID";

	/** Set ICF Version	  */
	public void setEXME_CIFHdr_ID (int EXME_CIFHdr_ID);

	/** Get ICF Version	  */
	public int getEXME_CIFHdr_ID();

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

    /** Column name EXME_EspTrabajoSocial_ID */
    public static final String COLUMNNAME_EXME_EspTrabajoSocial_ID = "EXME_EspTrabajoSocial_ID";

	/** Set Social Work Specialty	  */
	public void setEXME_EspTrabajoSocial_ID (int EXME_EspTrabajoSocial_ID);

	/** Get Social Work Specialty	  */
	public int getEXME_EspTrabajoSocial_ID();

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

    /** Column name EXME_EstServRef_ID */
    public static final String COLUMNNAME_EXME_EstServRef_ID = "EXME_EstServRef_ID";

	/** Set Reference Service Station.
	  * It establishes a relationship to the service station.
	  */
	public void setEXME_EstServRef_ID (int EXME_EstServRef_ID);

	/** Get Reference Service Station.
	  * It establishes a relationship to the service station.
	  */
	public int getEXME_EstServRef_ID();

    /** Column name EXME_EstServ_Ref_Morgue_ID */
    public static final String COLUMNNAME_EXME_EstServ_Ref_Morgue_ID = "EXME_EstServ_Ref_Morgue_ID";

	/** Set Service Station of Morgue.
	  * Service Station of Morgue
	  */
	public void setEXME_EstServ_Ref_Morgue_ID (int EXME_EstServ_Ref_Morgue_ID);

	/** Get Service Station of Morgue.
	  * Service Station of Morgue
	  */
	public int getEXME_EstServ_Ref_Morgue_ID();

    /** Column name EXME_EstServTrx_ID */
    public static final String COLUMNNAME_EXME_EstServTrx_ID = "EXME_EstServTrx_ID";

	/** Set Station in charge of Acquisitions.
	  * It defines the station that this in chargue of the departament of Acquisitions
	  */
	public void setEXME_EstServTrx_ID (int EXME_EstServTrx_ID);

	/** Get Station in charge of Acquisitions.
	  * It defines the station that this in chargue of the departament of Acquisitions
	  */
	public int getEXME_EstServTrx_ID();

    /** Column name EXME_IntervencionHdr_ID */
    public static final String COLUMNNAME_EXME_IntervencionHdr_ID = "EXME_IntervencionHdr_ID";

	/** Set ICD 9 Vol III	  */
	public void setEXME_IntervencionHdr_ID (int EXME_IntervencionHdr_ID);

	/** Get ICD 9 Vol III	  */
	public int getEXME_IntervencionHdr_ID();

	public I_EXME_IntervencionHdr getEXME_IntervencionHdr() throws RuntimeException;

    /** Column name EXME_Mejoras_ID */
    public static final String COLUMNNAME_EXME_Mejoras_ID = "EXME_Mejoras_ID";

	/** Set Improvements	  */
	public void setEXME_Mejoras_ID (int EXME_Mejoras_ID);

	/** Get Improvements	  */
	public int getEXME_Mejoras_ID();

    /** Column name EXME_PromoEspec_ID */
    public static final String COLUMNNAME_EXME_PromoEspec_ID = "EXME_PromoEspec_ID";

	/** Set specialty military promotion 	  */
	public void setEXME_PromoEspec_ID (int EXME_PromoEspec_ID);

	/** Get specialty military promotion 	  */
	public int getEXME_PromoEspec_ID();

    /** Column name ExpAuto */
    public static final String COLUMNNAME_ExpAuto = "ExpAuto";

	/** Set Atomatic File Number.
	  * Automatically Generate File Number 
	  */
	public void setExpAuto (boolean ExpAuto);

	/** Get Atomatic File Number.
	  * Automatically Generate File Number 
	  */
	public boolean isExpAuto();

    /** Column name GeneraExp */
    public static final String COLUMNNAME_GeneraExp = "GeneraExp";

	/** Set GeneraExp	  */
	public void setGeneraExp (boolean GeneraExp);

	/** Get GeneraExp	  */
	public boolean isGeneraExp();

    /** Column name HostServManagOOff */
    public static final String COLUMNNAME_HostServManagOOff = "HostServManagOOff";

	/** Set Host Open Office Service Manager	  */
	public void setHostServManagOOff (String HostServManagOOff);

	/** Get Host Open Office Service Manager	  */
	public String getHostServManagOOff();

    /** Column name ImpBrazalete */
    public static final String COLUMNNAME_ImpBrazalete = "ImpBrazalete";

	/** Set Print Bracelet.
	  * Print Bracelet
	  */
	public void setImpBrazalete (boolean ImpBrazalete);

	/** Get Print Bracelet.
	  * Print Bracelet
	  */
	public boolean isImpBrazalete();

    /** Column name IsAgregaLote */
    public static final String COLUMNNAME_IsAgregaLote = "IsAgregaLote";

	/** Set Add to Lot	  */
	public void setIsAgregaLote (boolean IsAgregaLote);

	/** Get Add to Lot	  */
	public boolean isAgregaLote();

    /** Column name IsAutoReturnCharges */
    public static final String COLUMNNAME_IsAutoReturnCharges = "IsAutoReturnCharges";

	/** Set Automatically return charges	  */
	public void setIsAutoReturnCharges (boolean IsAutoReturnCharges);

	/** Get Automatically return charges	  */
	public boolean isAutoReturnCharges();

    /** Column name isDiagEnCuest */
    public static final String COLUMNNAME_isDiagEnCuest = "isDiagEnCuest";

	/** Set Diagnosis in Questionnaire	  */
	public void setisDiagEnCuest (boolean isDiagEnCuest);

	/** Get Diagnosis in Questionnaire	  */
	public boolean isDiagEnCuest();

    /** Column name IsEditarClienteFD */
    public static final String COLUMNNAME_IsEditarClienteFD = "IsEditarClienteFD";

	/** Set Edit Client Info In Direct Invoicing.
	  * Edit Client Info In Direct Invoicing
	  */
	public void setIsEditarClienteFD (boolean IsEditarClienteFD);

	/** Get Edit Client Info In Direct Invoicing.
	  * Edit Client Info In Direct Invoicing
	  */
	public boolean isEditarClienteFD();

    /** Column name IsEditarClienteFE */
    public static final String COLUMNNAME_IsEditarClienteFE = "IsEditarClienteFE";

	/** Set Edit Client Info in Extension Invoicing.
	  * Edit Client Info in Extension Invoicing
	  */
	public void setIsEditarClienteFE (boolean IsEditarClienteFE);

	/** Get Edit Client Info in Extension Invoicing.
	  * Edit Client Info in Extension Invoicing
	  */
	public boolean isEditarClienteFE();

    /** Column name IsMDS */
    public static final String COLUMNNAME_IsMDS = "IsMDS";

	/** Set Clinical Decision Support.
	  * Use the Medical Decision Support validation
	  */
	public void setIsMDS (boolean IsMDS);

	/** Get Clinical Decision Support.
	  * Use the Medical Decision Support validation
	  */
	public boolean isMDS();

    /** Column name IsModificaLotes */
    public static final String COLUMNNAME_IsModificaLotes = "IsModificaLotes";

	/** Set Modify Lot	  */
	public void setIsModificaLotes (boolean IsModificaLotes);

	/** Get Modify Lot	  */
	public boolean isModificaLotes();

    /** Column name IsMultiUOM */
    public static final String COLUMNNAME_IsMultiUOM = "IsMultiUOM";

	/** Set Is Multi UOM.
	  * Has multiple UOM for the product
	  */
	public void setIsMultiUOM (boolean IsMultiUOM);

	/** Get Is Multi UOM.
	  * Has multiple UOM for the product
	  */
	public boolean isMultiUOM();

    /** Column name IsNombrePorApellido */
    public static final String COLUMNNAME_IsNombrePorApellido = "IsNombrePorApellido";

	/** Set Is name for Last Name	  */
	public void setIsNombrePorApellido (boolean IsNombrePorApellido);

	/** Get Is name for Last Name	  */
	public boolean isNombrePorApellido();

    /** Column name isOcultarPass */
    public static final String COLUMNNAME_isOcultarPass = "isOcultarPass";

	/** Set Hide Discounts Application with password	  */
	public void setisOcultarPass (boolean isOcultarPass);

	/** Get Hide Discounts Application with password	  */
	public boolean isOcultarPass();

    /** Column name IsProductRequestByGrouping */
    public static final String COLUMNNAME_IsProductRequestByGrouping = "IsProductRequestByGrouping";

	/** Set Activate product request by grouping.
	  * used field to form doing multiple requests grouped by type product
	  */
	public void setIsProductRequestByGrouping (boolean IsProductRequestByGrouping);

	/** Get Activate product request by grouping.
	  * used field to form doing multiple requests grouped by type product
	  */
	public boolean isProductRequestByGrouping();

    /** Column name LotWarnings */
    public static final String COLUMNNAME_LotWarnings = "LotWarnings";

	/** Set Warning messages in lot	  */
	public void setLotWarnings (boolean LotWarnings);

	/** Get Warning messages in lot	  */
	public boolean isLotWarnings();

    /** Column name MaxClaimsBatch */
    public static final String COLUMNNAME_MaxClaimsBatch = "MaxClaimsBatch";

	/** Set MaxClaimsBatch	  */
	public void setMaxClaimsBatch (int MaxClaimsBatch);

	/** Get MaxClaimsBatch	  */
	public int getMaxClaimsBatch();

    /** Column name MaxQueryRecords */
    public static final String COLUMNNAME_MaxQueryRecords = "MaxQueryRecords";

	/** Set Max Query Records.
	  * If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records
	  */
	public void setMaxQueryRecords (int MaxQueryRecords);

	/** Get Max Query Records.
	  * If defined, you cannot query more records as defined - the query criteria needs to be changed to query less records
	  */
	public int getMaxQueryRecords();

    /** Column name MaxStmtBatch */
    public static final String COLUMNNAME_MaxStmtBatch = "MaxStmtBatch";

	/** Set MaxStmtBatch	  */
	public void setMaxStmtBatch (int MaxStmtBatch);

	/** Get MaxStmtBatch	  */
	public int getMaxStmtBatch();

    /** Column name MaxTryWS */
    public static final String COLUMNNAME_MaxTryWS = "MaxTryWS";

	/** Set Max Try WebService Communication	  */
	public void setMaxTryWS (int MaxTryWS);

	/** Get Max Try WebService Communication	  */
	public int getMaxTryWS();
	
    /** Column name MDSRepResp */
    public static final String COLUMNNAME_MDSRepResp = "MDSRepResp";

	/** Set Issued Only Once CDS Warning	  */
	public void setMDSRepResp (boolean MDSRepResp);

	/** Get Issued Only Once CDS Warning	  */
	public boolean isMDSRepResp();

    /** Column name Message_Due121_180 */
    public static final String COLUMNNAME_Message_Due121_180 = "Message_Due121_180";

	/** Set Message for Due Between 121 and 180	  */
	public void setMessage_Due121_180 (String Message_Due121_180);

	/** Get Message for Due Between 121 and 180	  */
	public String getMessage_Due121_180();

    /** Column name Message_Due1_30 */
    public static final String COLUMNNAME_Message_Due1_30 = "Message_Due1_30";

	/** Set Message for Due Between 1 and 30	  */
	public void setMessage_Due1_30 (String Message_Due1_30);

	/** Get Message for Due Between 1 and 30	  */
	public String getMessage_Due1_30();

    /** Column name Message_Due181_Plus */
    public static final String COLUMNNAME_Message_Due181_Plus = "Message_Due181_Plus";

	/** Set Message for Due Greater than 181	  */
	public void setMessage_Due181_Plus (String Message_Due181_Plus);

	/** Get Message for Due Greater than 181	  */
	public String getMessage_Due181_Plus();

    /** Column name Message_Due31_60 */
    public static final String COLUMNNAME_Message_Due31_60 = "Message_Due31_60";

	/** Set Message for Due Between 31 and 60	  */
	public void setMessage_Due31_60 (String Message_Due31_60);

	/** Get Message for Due Between 31 and 60	  */
	public String getMessage_Due31_60();

    /** Column name Message_Due61_90 */
    public static final String COLUMNNAME_Message_Due61_90 = "Message_Due61_90";

	/** Set Message for Due Between 61 and 90	  */
	public void setMessage_Due61_90 (String Message_Due61_90);

	/** Get Message for Due Between 61 and 90	  */
	public String getMessage_Due61_90();

    /** Column name Message_Due91_120 */
    public static final String COLUMNNAME_Message_Due91_120 = "Message_Due91_120";

	/** Set Message for Due Between 91 and 120	  */
	public void setMessage_Due91_120 (String Message_Due91_120);

	/** Get Message for Due Between 91 and 120	  */
	public String getMessage_Due91_120();

    /** Column name MustBeStocked */
    public static final String COLUMNNAME_MustBeStocked = "MustBeStocked";

	/** Set Product quantity must be in stock.
	  * If not sufficient in stock in the warehouse, the BOM is not produced
	  */
	public void setMustBeStocked (boolean MustBeStocked);

	/** Get Product quantity must be in stock.
	  * If not sufficient in stock in the warehouse, the BOM is not produced
	  */
	public boolean isMustBeStocked();

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

    /** Column name NotAvailableNDC */
    public static final String COLUMNNAME_NotAvailableNDC = "NotAvailableNDC";

	/** Set Not Available NDC	  */
	public void setNotAvailableNDC (int NotAvailableNDC);

	/** Get Not Available NDC	  */
	public int getNotAvailableNDC();

    /** Column name NursingCharges */
    public static final String COLUMNNAME_NursingCharges = "NursingCharges";

	/** Set Nursing Charges in eMAR.
	  * Makes the charge of the drug when is applied by the nurse
	  */
	public void setNursingCharges (boolean NursingCharges);

	/** Get Nursing Charges in eMAR.
	  * Makes the charge of the drug when is applied by the nurse
	  */
	public boolean isNursingCharges();

    /** Column name Pentaho_psw */
    public static final String COLUMNNAME_Pentaho_psw = "Pentaho_psw";

	/** Set Pass	  */
	public void setPentaho_psw (String Pentaho_psw);

	/** Get Pass	  */
	public String getPentaho_psw();

    /** Column name Pentaho_server */
    public static final String COLUMNNAME_Pentaho_server = "Pentaho_server";

	/** Set Pentaho Server	  */
	public void setPentaho_server (String Pentaho_server);

	/** Get Pentaho Server	  */
	public String getPentaho_server();

    /** Column name Pentaho_user */
    public static final String COLUMNNAME_Pentaho_user = "Pentaho_user";

	/** Set User	  */
	public void setPentaho_user (String Pentaho_user);

	/** Get User	  */
	public String getPentaho_user();

    /** Column name PortServManagOOff */
    public static final String COLUMNNAME_PortServManagOOff = "PortServManagOOff";

	/** Set Service Manager port of Open Office	  */
	public void setPortServManagOOff (int PortServManagOOff);

	/** Get Service Manager port of Open Office	  */
	public int getPortServManagOOff();

    /** Column name ProductValue */
    public static final String COLUMNNAME_ProductValue = "ProductValue";

	/** Set Product Key.
	  * Key of the Product
	  */
	public void setProductValue (String ProductValue);

	/** Get Product Key.
	  * Key of the Product
	  */
	public String getProductValue();

    /** Column name ProgramarCita */
    public static final String COLUMNNAME_ProgramarCita = "ProgramarCita";

	/** Set Schedule an appointment.
	  * Allows the doctor to schedule a new appointment from appointment's execution
	  */
	public void setProgramarCita (boolean ProgramarCita);

	/** Get Schedule an appointment.
	  * Allows the doctor to schedule a new appointment from appointment's execution
	  */
	public boolean isProgramarCita();

    /** Column name RegBloque */
    public static final String COLUMNNAME_RegBloque = "RegBloque";

	/** Set Records per Block.
	  * Number of records included in each searching blocks inside Opening File
	  */
	public void setRegBloque (int RegBloque);

	/** Get Records per Block.
	  * Number of records included in each searching blocks inside Opening File
	  */
	public int getRegBloque();

    /** Column name ReqMDSResp */
    public static final String COLUMNNAME_ReqMDSResp = "ReqMDSResp";

	/** Set Require MDS Response	  */
	public void setReqMDSResp (boolean ReqMDSResp);

	/** Get Require MDS Response	  */
	public boolean isReqMDSResp();

    /** Column name RequestEMail */
    public static final String COLUMNNAME_RequestEMail = "RequestEMail";

	/** Set Request EMail.
	  * EMail address to send automated mails from or receive mails for automated processing (fully qualified)
	  */
	public void setRequestEMail (String RequestEMail);

	/** Get Request EMail.
	  * EMail address to send automated mails from or receive mails for automated processing (fully qualified)
	  */
	public String getRequestEMail();

    /** Column name RequestID */
    public static final String COLUMNNAME_RequestID = "RequestID";

	/** Set RequestID	  */
	public void setRequestID (String RequestID);

	/** Get RequestID	  */
	public String getRequestID();

    /** Column name RequestUser */
    public static final String COLUMNNAME_RequestUser = "RequestUser";

	/** Set Request User.
	  * User Name (ID) of the email owner
	  */
	public void setRequestUser (String RequestUser);

	/** Get Request User.
	  * User Name (ID) of the email owner
	  */
	public String getRequestUser();

    /** Column name RequestUserPW */
    public static final String COLUMNNAME_RequestUserPW = "RequestUserPW";

	/** Set Request User Password.
	  * Password of the user name (ID) for mail processing
	  */
	public void setRequestUserPW (String RequestUserPW);

	/** Get Request User Password.
	  * Password of the user name (ID) for mail processing
	  */
	public String getRequestUserPW();

    /** Column name RoundQtyCharge */
    public static final String COLUMNNAME_RoundQtyCharge = "RoundQtyCharge";

	/** Set Round up the charge quantity	  */
	public void setRoundQtyCharge (boolean RoundQtyCharge);

	/** Get Round up the charge quantity	  */
	public boolean isRoundQtyCharge();

    /** Column name RutaOpenOffice */
    public static final String COLUMNNAME_RutaOpenOffice = "RutaOpenOffice";

	/** Set Open Office Path.
	  * Open Office Path
	  */
	public void setRutaOpenOffice (String RutaOpenOffice);

	/** Get Open Office Path.
	  * Open Office Path
	  */
	public String getRutaOpenOffice();

    /** Column name RxFaxSendingEnabled */
    public static final String COLUMNNAME_RxFaxSendingEnabled = "RxFaxSendingEnabled";

	/** Set Rx Fax Sending Enabled	  */
	public void setRxFaxSendingEnabled (boolean RxFaxSendingEnabled);

	/** Get Rx Fax Sending Enabled	  */
	public boolean isRxFaxSendingEnabled();

    /** Column name StatementAge */
    public static final String COLUMNNAME_StatementAge = "StatementAge";

	/** Set StatementAge	  */
	public void setStatementAge (BigDecimal StatementAge);

	/** Get StatementAge	  */
	public BigDecimal getStatementAge();

    /** Column name TemporaryNDC */
    public static final String COLUMNNAME_TemporaryNDC = "TemporaryNDC";

	/** Set Temporary NDC	  */
	public void setTemporaryNDC (int TemporaryNDC);

	/** Get Temporary NDC	  */
	public int getTemporaryNDC();

    /** Column name TimeOutWS */
    public static final String COLUMNNAME_TimeOutWS = "TimeOutWS";

	/** Set Time Out in Seconds Connecting to WebService	  */
	public void setTimeOutWS (int TimeOutWS);

	/** Get Time Out in Seconds Connecting to WebService	  */
	public int getTimeOutWS();
	
    /** Column name Tolerancia */
    public static final String COLUMNNAME_Tolerancia = "Tolerancia";

	/** Set Tolerance	  */
	public void setTolerancia (int Tolerancia);

	/** Get Tolerance	  */
	public int getTolerancia();

    /** Column name VistaCitas */
    public static final String COLUMNNAME_VistaCitas = "VistaCitas";

	/** Set Appointment View	  */
	public void setVistaCitas (boolean VistaCitas);

	/** Get Appointment View	  */
	public boolean isVistaCitas();

    /** Column name VoucherRequired */
    public static final String COLUMNNAME_VoucherRequired = "VoucherRequired";

	/** Set Requires payment voucher.
	  * Requires payment voucher
	  */
	public void setVoucherRequired (boolean VoucherRequired);

	/** Get Requires payment voucher.
	  * Requires payment voucher
	  */
	public boolean isVoucherRequired();
}
