/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ConfigEC
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ConfigEC 
{

    /** TableName=EXME_ConfigEC */
    public static final String Table_Name = "EXME_ConfigEC";

    /** AD_Table_ID=1000076 */
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

    /** Column name AplicaPedCtaPac */
    public static final String COLUMNNAME_AplicaPedCtaPac = "AplicaPedCtaPac";

	/** Set Automatic Application of Orders to Patient acct.
	  * Automatic Application of Orders to Patient acct
	  */
	public void setAplicaPedCtaPac (boolean AplicaPedCtaPac);

	/** Get Automatic Application of Orders to Patient acct.
	  * Automatic Application of Orders to Patient acct
	  */
	public boolean isAplicaPedCtaPac();

    /** Column name BancoDeSangre */
    public static final String COLUMNNAME_BancoDeSangre = "BancoDeSangre";

	/** Set Blood bank 	  */
	public void setBancoDeSangre (boolean BancoDeSangre);

	/** Get Blood bank 	  */
	public boolean isBancoDeSangre();

    /** Column name CargaDiferAlm */
    public static final String COLUMNNAME_CargaDiferAlm = "CargaDiferAlm";

	/** Set Charges Differences to the Delivering Warehouse.
	  * Charges differences to the delivering warehouse
	  */
	public void setCargaDiferAlm (boolean CargaDiferAlm);

	/** Get Charges Differences to the Delivering Warehouse.
	  * Charges differences to the delivering warehouse
	  */
	public boolean isCargaDiferAlm();

    /** Column name CargDiarioAuto */
    public static final String COLUMNNAME_CargDiarioAuto = "CargDiarioAuto";

	/** Set Hour of Automatic Daily Charge.
	  * Hour to make the daily Automatic Charge
	  */
	public void setCargDiarioAuto (BigDecimal CargDiarioAuto);

	/** Get Hour of Automatic Daily Charge.
	  * Hour to make the daily Automatic Charge
	  */
	public BigDecimal getCargDiarioAuto();

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

    /** Column name CDA */
    public static final String COLUMNNAME_CDA = "CDA";

	/** Set Clinical Document Architecture	  */
	public void setCDA (boolean CDA);

	/** Get Clinical Document Architecture	  */
	public boolean isCDA();

    /** Column name ClasCliente */
    public static final String COLUMNNAME_ClasCliente = "ClasCliente";

	/** Set Customer classification.
	  * Customer classification
	  */
	public void setClasCliente (boolean ClasCliente);

	/** Get Customer classification.
	  * Customer classification
	  */
	public boolean isClasCliente();

    /** Column name CloseEncWD */
    public static final String COLUMNNAME_CloseEncWD = "CloseEncWD";

	/** Set Close encounter when discharge	  */
	public void setCloseEncWD (boolean CloseEncWD);

	/** Get Close encounter when discharge	  */
	public boolean isCloseEncWD();

    /** Column name CreateUserPatient */
    public static final String COLUMNNAME_CreateUserPatient = "CreateUserPatient";

	/** Set Create user for patient.
	  * Create user for patient
	  */
	public void setCreateUserPatient (boolean CreateUserPatient);

	/** Get Create user for patient.
	  * Create user for patient
	  */
	public boolean isCreateUserPatient();

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

    /** Column name DirectorioImag */
    public static final String COLUMNNAME_DirectorioImag = "DirectorioImag";

	/** Set Images Directory.
	  * Images Directory
	  */
	public void setDirectorioImag (String DirectorioImag);

	/** Get Images Directory.
	  * Images Directory
	  */
	public String getDirectorioImag();

    /** Column name DurationMin */
    public static final String COLUMNNAME_DurationMin = "DurationMin";

	/** Set Minimum duration	  */
	public void setDurationMin (int DurationMin);

	/** Get Minimum duration	  */
	public int getDurationMin();

    /** Column name EXME_ConfigEC_ID */
    public static final String COLUMNNAME_EXME_ConfigEC_ID = "EXME_ConfigEC_ID";

	/** Set EMR Configuration.
	  * EMR Configuration
	  */
	public void setEXME_ConfigEC_ID (int EXME_ConfigEC_ID);

	/** Get EMR Configuration.
	  * EMR Configuration
	  */
	public int getEXME_ConfigEC_ID();

    /** Column name EXME_Cuestionario2_ID */
    public static final String COLUMNNAME_EXME_Cuestionario2_ID = "EXME_Cuestionario2_ID";

	/** Set Clinic History Questionnaire	  */
	public void setEXME_Cuestionario2_ID (int EXME_Cuestionario2_ID);

	/** Get Clinic History Questionnaire	  */
	public int getEXME_Cuestionario2_ID();

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

    /** Column name GeneraBO */
    public static final String COLUMNNAME_GeneraBO = "GeneraBO";

	/** Set Genera Back Order.
	  * Genera Back Order
	  */
	public void setGeneraBO (boolean GeneraBO);

	/** Get Genera Back Order.
	  * Genera Back Order
	  */
	public boolean isGeneraBO();

    /** Column name HL7ADT */
    public static final String COLUMNNAME_HL7ADT = "HL7ADT";

	/** Set Requires HL7 ADT Message	  */
	public void setHL7ADT (boolean HL7ADT);

	/** Get Requires HL7 ADT Message	  */
	public boolean isHL7ADT();

    /** Column name Hours_Eleg */
    public static final String COLUMNNAME_Hours_Eleg = "Hours_Eleg";

	/** Set Eligibility Days	  */
	public void setHours_Eleg (int Hours_Eleg);

	/** Get Eligibility Days	  */
	public int getHours_Eleg();

    /** Column name IDPaziente */
    public static final String COLUMNNAME_IDPaziente = "IDPaziente";

	/** Set Patient WinLab Access Web 	  */
	public void setIDPaziente (String IDPaziente);

	/** Get Patient WinLab Access Web 	  */
	public String getIDPaziente();

    /** Column name ImpNotaEntMed */
    public static final String COLUMNNAME_ImpNotaEntMed = "ImpNotaEntMed";

	/** Set Print Delivery Note of Medications.
	  * Print delivery note of medications
	  */
	public void setImpNotaEntMed (boolean ImpNotaEntMed);

	/** Get Print Delivery Note of Medications.
	  * Print delivery note of medications
	  */
	public boolean isImpNotaEntMed();

    /** Column name ImpRecetaMed */
    public static final String COLUMNNAME_ImpRecetaMed = "ImpRecetaMed";

	/** Set Print Medications Prescription.
	  * Print Medication prescription
	  */
	public void setImpRecetaMed (boolean ImpRecetaMed);

	/** Get Print Medications Prescription.
	  * Print Medication prescription
	  */
	public boolean isImpRecetaMed();

    /** Column name ImpValeEntMed */
    public static final String COLUMNNAME_ImpValeEntMed = "ImpValeEntMed";

	/** Set Print Medication Coupon for Delivery.
	  * Print Medication Coupon for delivery
	  */
	public void setImpValeEntMed (boolean ImpValeEntMed);

	/** Get Print Medication Coupon for Delivery.
	  * Print Medication Coupon for delivery
	  */
	public boolean isImpValeEntMed();

    /** Column name ImpValeParcial */
    public static final String COLUMNNAME_ImpValeParcial = "ImpValeParcial";

	/** Set Print Coupon of Partial Medications.
	  * Print Coupon of Partial Medications
	  */
	public void setImpValeParcial (boolean ImpValeParcial);

	/** Get Print Coupon of Partial Medications.
	  * Print Coupon of Partial Medications
	  */
	public boolean isImpValeParcial();

    /** Column name IncluyeCartilla */
    public static final String COLUMNNAME_IncluyeCartilla = "IncluyeCartilla";

	/** Set Included in Immunization Cards	  */
	public void setIncluyeCartilla (boolean IncluyeCartilla);

	/** Get Included in Immunization Cards	  */
	public boolean isIncluyeCartilla();

    /** Column name InterfaseLaserFiche */
    public static final String COLUMNNAME_InterfaseLaserFiche = "InterfaseLaserFiche";

	/** Set InterfaseLaserFiche	  */
	public void setInterfaseLaserFiche (boolean InterfaseLaserFiche);

	/** Get InterfaseLaserFiche	  */
	public boolean isInterfaseLaserFiche();

    /** Column name Interfaz_HL7 */
    public static final String COLUMNNAME_Interfaz_HL7 = "Interfaz_HL7";

	/** Set HL 7 Interface.
	  * HL 7 Interface
	  */
	public void setInterfaz_HL7 (boolean Interfaz_HL7);

	/** Get HL 7 Interface.
	  * HL 7 Interface
	  */
	public boolean isInterfaz_HL7();

    /** Column name IsReqConfirm */
    public static final String COLUMNNAME_IsReqConfirm = "IsReqConfirm";

	/** Set Require Medical Appoinment Confirmation.
	  * Require Medical Appoinment Confirmation
	  */
	public void setIsReqConfirm (boolean IsReqConfirm);

	/** Get Require Medical Appoinment Confirmation.
	  * Require Medical Appoinment Confirmation
	  */
	public boolean isReqConfirm();

    /** Column name KODAK_R */
    public static final String COLUMNNAME_KODAK_R = "KODAK_R";

	/** Set Kodak	  */
	public void setKODAK_R (String KODAK_R);

	/** Get Kodak	  */
	public String getKODAK_R();

    /** Column name LIGAKODAK */
    public static final String COLUMNNAME_LIGAKODAK = "LIGAKODAK";

	/** Set Kodak Web Link.
	  * Kodak Web Link
	  */
	public void setLIGAKODAK (boolean LIGAKODAK);

	/** Get Kodak Web Link.
	  * Kodak Web Link
	  */
	public boolean isLIGAKODAK();

    /** Column name LigaWinlab */
    public static final String COLUMNNAME_LigaWinlab = "LigaWinlab";

	/** Set Web Link to Winlab.
	  * Web Link to Winlab
	  */
	public void setLigaWinlab (boolean LigaWinlab);

	/** Get Web Link to Winlab.
	  * Web Link to Winlab
	  */
	public boolean isLigaWinlab();

    /** Column name MostrarImpresora */
    public static final String COLUMNNAME_MostrarImpresora = "MostrarImpresora";

	/** Set Show printer	  */
	public void setMostrarImpresora (boolean MostrarImpresora);

	/** Get Show printer	  */
	public boolean isMostrarImpresora();

    /** Column name MostrarPacRefer */
    public static final String COLUMNNAME_MostrarPacRefer = "MostrarPacRefer";

	/** Set Show Referenced Patient.
	  * Show Referenced Patient in Medical Consultation
	  */
	public void setMostrarPacRefer (boolean MostrarPacRefer);

	/** Get Show Referenced Patient.
	  * Show Referenced Patient in Medical Consultation
	  */
	public boolean isMostrarPacRefer();

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

    /** Column name M_PriceList_Reg_ID */
    public static final String COLUMNNAME_M_PriceList_Reg_ID = "M_PriceList_Reg_ID";

	/** Set Regulated price	  */
	public void setM_PriceList_Reg_ID (int M_PriceList_Reg_ID);

	/** Get Regulated price	  */
	public int getM_PriceList_Reg_ID();

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

	public I_M_Product getM_Product() throws RuntimeException;

    /** Column name MultipleUDM */
    public static final String COLUMNNAME_MultipleUDM = "MultipleUDM";

	/** Set Multiple Units of Measure.
	  * Handles multiples unit of measures
	  */
	public void setMultipleUDM (boolean MultipleUDM);

	/** Get Multiple Units of Measure.
	  * Handles multiples unit of measures
	  */
	public boolean isMultipleUDM();

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

    /** Column name M_WarehouseSer_ID */
    public static final String COLUMNNAME_M_WarehouseSer_ID = "M_WarehouseSer_ID";

	/** Set Services Warehouse.
	  * Requesting warehouse of services for external consult
	  */
	public void setM_WarehouseSer_ID (int M_WarehouseSer_ID);

	/** Get Services Warehouse.
	  * Requesting warehouse of services for external consult
	  */
	public int getM_WarehouseSer_ID();

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

    /** Column name NotasMedicas */
    public static final String COLUMNNAME_NotasMedicas = "NotasMedicas";

	/** Set Medical Record	  */
	public void setNotasMedicas (boolean NotasMedicas);

	/** Get Medical Record	  */
	public boolean isNotasMedicas();

    /** Column name No_Usuarios_HL7 */
    public static final String COLUMNNAME_No_Usuarios_HL7 = "No_Usuarios_HL7";

	/** Set HL7 Allowed Users.
	  * HL7 Allowed Users
	  */
	public void setNo_Usuarios_HL7 (int No_Usuarios_HL7);

	/** Get HL7 Allowed Users.
	  * HL7 Allowed Users
	  */
	public int getNo_Usuarios_HL7();

    /** Column name PermCamAlm */
    public static final String COLUMNNAME_PermCamAlm = "PermCamAlm";

	/** Set Permit Change of Warehouse.
	  * Permit Change of Warehouse
	  */
	public void setPermCamAlm (boolean PermCamAlm);

	/** Get Permit Change of Warehouse.
	  * Permit Change of Warehouse
	  */
	public boolean isPermCamAlm();

    /** Column name PermCamEst */
    public static final String COLUMNNAME_PermCamEst = "PermCamEst";

	/** Set Permit Change Station Service.
	  * Permit change station service
	  */
	public void setPermCamEst (boolean PermCamEst);

	/** Get Permit Change Station Service.
	  * Permit change station service
	  */
	public boolean isPermCamEst();

    /** Column name PreReqCita */
    public static final String COLUMNNAME_PreReqCita = "PreReqCita";

	/** Set Medical Consultation's Prerequisites	  */
	public void setPreReqCita (boolean PreReqCita);

	/** Get Medical Consultation's Prerequisites	  */
	public boolean isPreReqCita();

    /** Column name Privado */
    public static final String COLUMNNAME_Privado = "Privado";

	/** Set Private	  */
	public void setPrivado (boolean Privado);

	/** Get Private	  */
	public boolean isPrivado();

    /** Column name ProductoXEsp */
    public static final String COLUMNNAME_ProductoXEsp = "ProductoXEsp";

	/** Set Products by Specialty.
	  * Filtrate Products by specialty
	  */
	public void setProductoXEsp (boolean ProductoXEsp);

	/** Get Products by Specialty.
	  * Filtrate Products by specialty
	  */
	public boolean isProductoXEsp();

    /** Column name Qty_Hr_Abierta */
    public static final String COLUMNNAME_Qty_Hr_Abierta = "Qty_Hr_Abierta";

	/** Set How many hours are the windows open?.
	  * How many hours at day are the windows open?
	  */
	public void setQty_Hr_Abierta (int Qty_Hr_Abierta);

	/** Get How many hours are the windows open?.
	  * How many hours at day are the windows open?
	  */
	public int getQty_Hr_Abierta();

    /** Column name Receiving_Application */
    public static final String COLUMNNAME_Receiving_Application = "Receiving_Application";

	/** Set Receiving Application	  */
	public void setReceiving_Application (String Receiving_Application);

	/** Get Receiving Application	  */
	public String getReceiving_Application();

    /** Column name Receiving_Facility */
    public static final String COLUMNNAME_Receiving_Facility = "Receiving_Facility";

	/** Set Receiving Facility	  */
	public void setReceiving_Facility (String Receiving_Facility);

	/** Get Receiving Facility	  */
	public String getReceiving_Facility();

    /** Column name ReqFactCE */
    public static final String COLUMNNAME_ReqFactCE = "ReqFactCE";

	/** Set Require Invoicing Before Executing Medical Appointment	  */
	public void setReqFactCE (boolean ReqFactCE);

	/** Get Require Invoicing Before Executing Medical Appointment	  */
	public boolean isReqFactCE();

    /** Column name ReqFactIndH */
    public static final String COLUMNNAME_ReqFactIndH = "ReqFactIndH";

	/** Set Requiring receipts before applying indicators.
	  * Indicates if necessary check requests for outpatient services, requests for service and execution of medications medical appointment before applying
	  */
	public void setReqFactIndH (boolean ReqFactIndH);

	/** Get Requiring receipts before applying indicators.
	  * Indicates if necessary check requests for outpatient services, requests for service and execution of medications medical appointment before applying
	  */
	public boolean isReqFactIndH();

    /** Column name ReqFactZero */
    public static final String COLUMNNAME_ReqFactZero = "ReqFactZero";

	/** Set Require Invoice in Zero for Bussines Partner Exempt	  */
	public void setReqFactZero (boolean ReqFactZero);

	/** Get Require Invoice in Zero for Bussines Partner Exempt	  */
	public boolean isReqFactZero();

    /** Column name RutaInterfaseLaserFiche */
    public static final String COLUMNNAME_RutaInterfaseLaserFiche = "RutaInterfaseLaserFiche";

	/** Set RutaInterfaseLaserFiche	  */
	public void setRutaInterfaseLaserFiche (String RutaInterfaseLaserFiche);

	/** Get RutaInterfaseLaserFiche	  */
	public String getRutaInterfaseLaserFiche();

    /** Column name ScaleMin */
    public static final String COLUMNNAME_ScaleMin = "ScaleMin";

	/** Set Pixels - Minute Scale.
	  * Pixels - Minute Scale for Daily Medical Agenda
	  */
	public void setScaleMin (int ScaleMin);

	/** Get Pixels - Minute Scale.
	  * Pixels - Minute Scale for Daily Medical Agenda
	  */
	public int getScaleMin();

    /** Column name seeCE */
    public static final String COLUMNNAME_seeCE = "seeCE";

	/** Set See closed encounters	  */
	public void setseeCE (boolean seeCE);

	/** Get See closed encounters	  */
	public boolean isseeCE();

    /** Column name Servidor_Reportes */
    public static final String COLUMNNAME_Servidor_Reportes = "Servidor_Reportes";

	/** Set Server for Report Download.
	  * Server from which reports would be downloaded (server:port)
	  */
	public void setServidor_Reportes (String Servidor_Reportes);

	/** Get Server for Report Download.
	  * Server from which reports would be downloaded (server:port)
	  */
	public String getServidor_Reportes();

    /** Column name SurteConfirma */
    public static final String COLUMNNAME_SurteConfirma = "SurteConfirma";

	/** Set Takes / Confirm.
	  * Takes / Confirm
	  */
	public void setSurteConfirma (boolean SurteConfirma);

	/** Get Takes / Confirm.
	  * Takes / Confirm
	  */
	public boolean isSurteConfirma();

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

    /** Column name TEST */
    public static final String COLUMNNAME_TEST = "TEST";

	/** Set TEST	  */
	public void setTEST (boolean TEST);

	/** Get TEST	  */
	public boolean isTEST();

    /** Column name Time_Eleg */
    public static final String COLUMNNAME_Time_Eleg = "Time_Eleg";

	/** Set Time_Eleg	  */
	public void setTime_Eleg (Timestamp Time_Eleg);

	/** Get Time_Eleg	  */
	public Timestamp getTime_Eleg();

    /** Column name Tipov_Eleg */
    public static final String COLUMNNAME_Tipov_Eleg = "Tipov_Eleg";

	/** Set Tipov_Eleg	  */
	public void setTipov_Eleg (String Tipov_Eleg);

	/** Get Tipov_Eleg	  */
	public String getTipov_Eleg();

    /** Column name Vademecum */
    public static final String COLUMNNAME_Vademecum = "Vademecum";

	/** Set Vademecum	  */
	public void setVademecum (String Vademecum);

	/** Get Vademecum	  */
	public String getVademecum();

    /** Column name ValidaCita */
    public static final String COLUMNNAME_ValidaCita = "ValidaCita";

	/** Set Validate Medical appointment	  */
	public void setValidaCita (boolean ValidaCita);

	/** Get Validate Medical appointment	  */
	public boolean isValidaCita();

    /** Column name validaDiagCita */
    public static final String COLUMNNAME_validaDiagCita = "validaDiagCita";

	/** Set Validate Medical appointment Diagnostic	  */
	public void setvalidaDiagCita (boolean validaDiagCita);

	/** Get Validate Medical appointment Diagnostic	  */
	public boolean isvalidaDiagCita();

    /** Column name Vigencia */
    public static final String COLUMNNAME_Vigencia = "Vigencia";

	/** Set Validity	  */
	public void setVigencia (String Vigencia);

	/** Get Validity	  */
	public String getVigencia();

    /** Column name WinLab_CodiceUtente */
    public static final String COLUMNNAME_WinLab_CodiceUtente = "WinLab_CodiceUtente";

	/** Set CodiceUtente for winlab	  */
	public void setWinLab_CodiceUtente (String WinLab_CodiceUtente);

	/** Get CodiceUtente for winlab	  */
	public String getWinLab_CodiceUtente();

    /** Column name WinlabMirthHttpRequest */
    public static final String COLUMNNAME_WinlabMirthHttpRequest = "WinlabMirthHttpRequest";

	/** Set Winlab Mirth Http Request.
	  * Winlab Mirth Http Request
	  */
	public void setWinlabMirthHttpRequest (String WinlabMirthHttpRequest);

	/** Get Winlab Mirth Http Request.
	  * Winlab Mirth Http Request
	  */
	public String getWinlabMirthHttpRequest();

    /** Column name WINLAB_R */
    public static final String COLUMNNAME_WINLAB_R = "WINLAB_R";

	/** Set Winlab Route	  */
	public void setWINLAB_R (String WINLAB_R);

	/** Get Winlab Route	  */
	public String getWINLAB_R();
}
