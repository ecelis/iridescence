/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Invoice
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_Invoice 
{

    /** TableName=C_Invoice */
    public static final String Table_Name = "C_Invoice";

    /** AD_Table_ID=318 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name Addenda */
    public static final String COLUMNNAME_Addenda = "Addenda";

	/** Set Addenda	  */
	public void setAddenda (String Addenda);

	/** Get Addenda	  */
	public String getAddenda();

    /** Column name Address1 */
    public static final String COLUMNNAME_Address1 = "Address1";

	/** Set Address 1.
	  * Address line 1 for this location
	  */
	public void setAddress1 (String Address1);

	/** Get Address 1.
	  * Address line 1 for this location
	  */
	public String getAddress1();

    /** Column name Address2 */
    public static final String COLUMNNAME_Address2 = "Address2";

	/** Set Address 2.
	  * Address line 2 for this location
	  */
	public void setAddress2 (String Address2);

	/** Get Address 2.
	  * Address line 2 for this location
	  */
	public String getAddress2();

    /** Column name Address3 */
    public static final String COLUMNNAME_Address3 = "Address3";

	/** Set Address 3.
	  * Address Line 3 for the location
	  */
	public void setAddress3 (String Address3);

	/** Get Address 3.
	  * Address Line 3 for the location
	  */
	public String getAddress3();

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

    /** Column name Afecta_Caja */
    public static final String COLUMNNAME_Afecta_Caja = "Afecta_Caja";

	/** Set Affect Cashbook	  */
	public void setAfecta_Caja (boolean Afecta_Caja);

	/** Get Affect Cashbook	  */
	public boolean isAfecta_Caja();

    /** Column name Backoffice */
    public static final String COLUMNNAME_Backoffice = "Backoffice";

	/** Set Back office	  */
	public void setBackoffice (boolean Backoffice);

	/** Get Back office	  */
	public boolean isBackoffice();

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

	public I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name Cadena */
    public static final String COLUMNNAME_Cadena = "Cadena";

	/** Set Chain.
	  * Chain
	  */
	public void setCadena (String Cadena);

	/** Get Chain.
	  * Chain
	  */
	public String getCadena();

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

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Company Location.
	  * Identifies the (ship to) address for this Company
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Company Location.
	  * Identifies the (ship to) address for this Company
	  */
	public int getC_BPartner_Location_ID();

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

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

    /** Column name C_CashBook_ID */
    public static final String COLUMNNAME_C_CashBook_ID = "C_CashBook_ID";

	/** Set Cash Register.
	  * Cash Register for recording payments transactions
	  */
	public void setC_CashBook_ID (int C_CashBook_ID);

	/** Get Cash Register.
	  * Cash Register for recording payments transactions
	  */
	public int getC_CashBook_ID();

    /** Column name C_Cash_ID */
    public static final String COLUMNNAME_C_Cash_ID = "C_Cash_ID";

	/** Set Cash Journal.
	  * Cash Journal
	  */
	public void setC_Cash_ID (int C_Cash_ID);

	/** Get Cash Journal.
	  * Cash Journal
	  */
	public int getC_Cash_ID();

    /** Column name C_CashLine_ID */
    public static final String COLUMNNAME_C_CashLine_ID = "C_CashLine_ID";

	/** Set Cash Journal Line.
	  * Cash Journal Line
	  */
	public void setC_CashLine_ID (int C_CashLine_ID);

	/** Get Cash Journal Line.
	  * Cash Journal Line
	  */
	public int getC_CashLine_ID();

	public I_C_CashLine getC_CashLine() throws RuntimeException;

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

    /** Column name C_ConversionType_ID */
    public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";

	/** Set Currency Type.
	  * Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID);

	/** Get Currency Type.
	  * Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID();

	public I_C_ConversionType getC_ConversionType() throws RuntimeException;

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

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

	public I_C_Currency getC_Currency() throws RuntimeException;

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

	public I_C_DocType getC_DocType() throws RuntimeException;

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

    /** Column name C_DunningLevel_ID */
    public static final String COLUMNNAME_C_DunningLevel_ID = "C_DunningLevel_ID";

	/** Set Dunning Level	  */
	public void setC_DunningLevel_ID (int C_DunningLevel_ID);

	/** Get Dunning Level	  */
	public int getC_DunningLevel_ID();

	public I_C_DunningLevel getC_DunningLevel() throws RuntimeException;

    /** Column name Certificado */
    public static final String COLUMNNAME_Certificado = "Certificado";

	/** Set Client Certificate	  */
	public void setCertificado (String Certificado);

	/** Get Client Certificate	  */
	public String getCertificado();

    /** Column name CertificadoSAT */
    public static final String COLUMNNAME_CertificadoSAT = "CertificadoSAT";

	/** Set Certificate of Service Tax Administration	  */
	public void setCertificadoSAT (String CertificadoSAT);

	/** Get Certificate of Service Tax Administration	  */
	public String getCertificadoSAT();

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

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name Coaseguro */
    public static final String COLUMNNAME_Coaseguro = "Coaseguro";

	/** Set Coinsurance.
	  * Coinsurance
	  */
	public void setCoaseguro (BigDecimal Coaseguro);

	/** Get Coinsurance.
	  * Coinsurance
	  */
	public BigDecimal getCoaseguro();

    /** Column name CoaseguroAmt */
    public static final String COLUMNNAME_CoaseguroAmt = "CoaseguroAmt";

	/** Set Coinsurance	  */
	public void setCoaseguroAmt (BigDecimal CoaseguroAmt);

	/** Get Coinsurance	  */
	public BigDecimal getCoaseguroAmt();

    /** Column name CoaseguroMed */
    public static final String COLUMNNAME_CoaseguroMed = "CoaseguroMed";

	/** Set Coinsurance Professional.
	  * Coinsurance Professional
	  */
	public void setCoaseguroMed (BigDecimal CoaseguroMed);

	/** Get Coinsurance Professional.
	  * Coinsurance Professional
	  */
	public BigDecimal getCoaseguroMed();

    /** Column name CoaseguroMedAmt */
    public static final String COLUMNNAME_CoaseguroMedAmt = "CoaseguroMedAmt";

	/** Set Professional coinsurance	  */
	public void setCoaseguroMedAmt (BigDecimal CoaseguroMedAmt);

	/** Get Professional coinsurance	  */
	public BigDecimal getCoaseguroMedAmt();

    /** Column name ConfType */
    public static final String COLUMNNAME_ConfType = "ConfType";

	/** Set Configuration Type	  */
	public void setConfType (String ConfType);

	/** Get Configuration Type	  */
	public String getConfType();

    /** Column name Copago */
    public static final String COLUMNNAME_Copago = "Copago";

	/** Set CoPay	  */
	public void setCopago (BigDecimal Copago);

	/** Get CoPay	  */
	public BigDecimal getCopago();

    /** Column name CopagoAmt */
    public static final String COLUMNNAME_CopagoAmt = "CopagoAmt";

	/** Set Copayment	  */
	public void setCopagoAmt (BigDecimal CopagoAmt);

	/** Get Copayment	  */
	public BigDecimal getCopagoAmt();

    /** Column name CopyFrom */
    public static final String COLUMNNAME_CopyFrom = "CopyFrom";

	/** Set Copy From.
	  * Copy From Record
	  */
	public void setCopyFrom (String CopyFrom);

	/** Get Copy From.
	  * Copy From Record
	  */
	public String getCopyFrom();

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public I_C_Order getC_Order() throws RuntimeException;

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public I_C_Payment getC_Payment() throws RuntimeException;

    /** Column name C_PaymentTerm_ID */
    public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";

	/** Set Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID);

	/** Get Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID();

	public I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException;

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

	public I_C_Project getC_Project() throws RuntimeException;

    /** Column name CreateFrom */
    public static final String COLUMNNAME_CreateFrom = "CreateFrom";

	/** Set Create lines from.
	  * Process which will generate a new document lines based on an existing document
	  */
	public void setCreateFrom (String CreateFrom);

	/** Get Create lines from.
	  * Process which will generate a new document lines based on an existing document
	  */
	public String getCreateFrom();

    /** Column name C_Region_ID */
    public static final String COLUMNNAME_C_Region_ID = "C_Region_ID";

	/** Set Region.
	  * Identifies a geographical Region
	  */
	public void setC_Region_ID (int C_Region_ID);

	/** Get Region.
	  * Identifies a geographical Region
	  */
	public int getC_Region_ID();

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

    /** Column name Deducible */
    public static final String COLUMNNAME_Deducible = "Deducible";

	/** Set Deductible.
	  * Deductible
	  */
	public void setDeducible (BigDecimal Deducible);

	/** Get Deductible.
	  * Deductible
	  */
	public BigDecimal getDeducible();

    /** Column name DeducibleAmt */
    public static final String COLUMNNAME_DeducibleAmt = "DeducibleAmt";

	/** Set Deductible	  */
	public void setDeducibleAmt (BigDecimal DeducibleAmt);

	/** Get Deductible	  */
	public BigDecimal getDeducibleAmt();

    /** Column name DescFromAseg */
    public static final String COLUMNNAME_DescFromAseg = "DescFromAseg";

	/** Set Insurance Co Discount.
	  * Discount for Insurance Company
	  */
	public void setDescFromAseg (boolean DescFromAseg);

	/** Get Insurance Co Discount.
	  * Discount for Insurance Company
	  */
	public boolean isDescFromAseg();

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

    /** Column name DiscountAmt */
    public static final String COLUMNNAME_DiscountAmt = "DiscountAmt";

	/** Set Discount Amount.
	  * Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt);

	/** Get Discount Amount.
	  * Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt();

    /** Column name DiscountPorcent */
    public static final String COLUMNNAME_DiscountPorcent = "DiscountPorcent";

	/** Set DiscountPorcent.
	  * DiscountPorcent
	  */
	public void setDiscountPorcent (BigDecimal DiscountPorcent);

	/** Get DiscountPorcent.
	  * DiscountPorcent
	  */
	public BigDecimal getDiscountPorcent();

    /** Column name DiscountTaxAmt */
    public static final String COLUMNNAME_DiscountTaxAmt = "DiscountTaxAmt";

	/** Set Discount tax amout	  */
	public void setDiscountTaxAmt (BigDecimal DiscountTaxAmt);

	/** Get Discount tax amout	  */
	public BigDecimal getDiscountTaxAmt();

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

    /** Column name DocumentNoExt */
    public static final String COLUMNNAME_DocumentNoExt = "DocumentNoExt";

	/** Set Exterior Document No..
	  * Exterior Document No.
	  */
	public void setDocumentNoExt (String DocumentNoExt);

	/** Get Exterior Document No..
	  * Exterior Document No.
	  */
	public String getDocumentNoExt();

    /** Column name DunningGrace */
    public static final String COLUMNNAME_DunningGrace = "DunningGrace";

	/** Set Dunning Grace	  */
	public void setDunningGrace (Timestamp DunningGrace);

	/** Get Dunning Grace	  */
	public Timestamp getDunningGrace();

    /** Column name EstServ */
    public static final String COLUMNNAME_EstServ = "EstServ";

	/** Set Service station from CtaPac	  */
	public void setEstServ (String EstServ);

	/** Get Service station from CtaPac	  */
	public String getEstServ();

    /** Column name EXME_ClaimPayment_ID */
    public static final String COLUMNNAME_EXME_ClaimPayment_ID = "EXME_ClaimPayment_ID";

	/** Set Claim Transaction.
	  * Claim Transaction
	  */
	public void setEXME_ClaimPayment_ID (int EXME_ClaimPayment_ID);

	/** Get Claim Transaction.
	  * Claim Transaction
	  */
	public int getEXME_ClaimPayment_ID();

	public I_EXME_ClaimPayment getEXME_ClaimPayment() throws RuntimeException;

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

    /** Column name EXME_TownCouncil_ID */
    public static final String COLUMNNAME_EXME_TownCouncil_ID = "EXME_TownCouncil_ID";

	/** Set County.
	  * County
	  */
	public void setEXME_TownCouncil_ID (int EXME_TownCouncil_ID);

	/** Get County.
	  * County
	  */
	public int getEXME_TownCouncil_ID();

	public I_EXME_TownCouncil getEXME_TownCouncil() throws RuntimeException;

    /** Column name FechaNac */
    public static final String COLUMNNAME_FechaNac = "FechaNac";

	/** Set Birth Date.
	  * Birth Date
	  */
	public void setFechaNac (Timestamp FechaNac);

	/** Get Birth Date.
	  * Birth Date
	  */
	public Timestamp getFechaNac();

    /** Column name GenerateTo */
    public static final String COLUMNNAME_GenerateTo = "GenerateTo";

	/** Set Generate To.
	  * Generate To
	  */
	public void setGenerateTo (String GenerateTo);

	/** Get Generate To.
	  * Generate To
	  */
	public String getGenerateTo();

    /** Column name GlobalDiscount */
    public static final String COLUMNNAME_GlobalDiscount = "GlobalDiscount";

	/** Set GlobalDiscount	  */
	public void setGlobalDiscount (BigDecimal GlobalDiscount);

	/** Get GlobalDiscount	  */
	public BigDecimal getGlobalDiscount();

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

    /** Column name InvoiceCollectionType */
    public static final String COLUMNNAME_InvoiceCollectionType = "InvoiceCollectionType";

	/** Set Collection Status.
	  * Invoice Collection Status
	  */
	public void setInvoiceCollectionType (String InvoiceCollectionType);

	/** Get Collection Status.
	  * Invoice Collection Status
	  */
	public String getInvoiceCollectionType();

    /** Column name InvoicePhone */
    public static final String COLUMNNAME_InvoicePhone = "InvoicePhone";

	/** Set InvoicePhone	  */
	public void setInvoicePhone (String InvoicePhone);

	/** Get InvoicePhone	  */
	public String getInvoicePhone();

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

    /** Column name IsInDispute */
    public static final String COLUMNNAME_IsInDispute = "IsInDispute";

	/** Set In Dispute.
	  * Document is in dispute
	  */
	public void setIsInDispute (boolean IsInDispute);

	/** Get In Dispute.
	  * Document is in dispute
	  */
	public boolean isInDispute();

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

    /** Column name IsMultiple */
    public static final String COLUMNNAME_IsMultiple = "IsMultiple";

	/** Set Is a multiple bill.
	  * Is a multiple bill
	  */
	public void setIsMultiple (boolean IsMultiple);

	/** Get Is a multiple bill.
	  * Is a multiple bill
	  */
	public boolean isMultiple();

    /** Column name IsOrderFacLineCategory */
    public static final String COLUMNNAME_IsOrderFacLineCategory = "IsOrderFacLineCategory";

	/** Set Sort by Category invoice line	  */
	public void setIsOrderFacLineCategory (boolean IsOrderFacLineCategory);

	/** Get Sort by Category invoice line	  */
	public boolean isOrderFacLineCategory();

    /** Column name IsPaid */
    public static final String COLUMNNAME_IsPaid = "IsPaid";

	/** Set Paid.
	  * The document is paid
	  */
	public void setIsPaid (boolean IsPaid);

	/** Get Paid.
	  * The document is paid
	  */
	public boolean isPaid();

    /** Column name IsPayScheduleValid */
    public static final String COLUMNNAME_IsPayScheduleValid = "IsPayScheduleValid";

	/** Set Pay Schedule valid.
	  * Is the Payment Schedule is valid
	  */
	public void setIsPayScheduleValid (boolean IsPayScheduleValid);

	/** Get Pay Schedule valid.
	  * Is the Payment Schedule is valid
	  */
	public boolean isPayScheduleValid();

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

    /** Column name IsPrintedPre */
    public static final String COLUMNNAME_IsPrintedPre = "IsPrintedPre";

	/** Set IsPrintedPre	  */
	public void setIsPrintedPre (boolean IsPrintedPre);

	/** Get IsPrintedPre	  */
	public boolean isPrintedPre();

    /** Column name IsSelfService */
    public static final String COLUMNNAME_IsSelfService = "IsSelfService";

	/** Set Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService);

	/** Get Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService();

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

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

    /** Column name IsTransferred */
    public static final String COLUMNNAME_IsTransferred = "IsTransferred";

	/** Set Transferred.
	  * Transferred to General Ledger (i.e. accounted)
	  */
	public void setIsTransferred (String IsTransferred);

	/** Get Transferred.
	  * Transferred to General Ledger (i.e. accounted)
	  */
	public String getIsTransferred();

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

	public I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_RMA_ID */
    public static final String COLUMNNAME_M_RMA_ID = "M_RMA_ID";

	/** Set RMA.
	  * Return Material Authorization
	  */
	public void setM_RMA_ID (int M_RMA_ID);

	/** Get RMA.
	  * Return Material Authorization
	  */
	public int getM_RMA_ID();

	public I_M_RMA getM_RMA() throws RuntimeException;

    /** Column name Multiple_ID */
    public static final String COLUMNNAME_Multiple_ID = "Multiple_ID";

	/** Set Multiple invoice reference.
	  * Multiple invoice reference
	  */
	public void setMultiple_ID (int Multiple_ID);

	/** Get Multiple invoice reference.
	  * Multiple invoice reference
	  */
	public int getMultiple_ID();

    /** Column name NoCertificado */
    public static final String COLUMNNAME_NoCertificado = "NoCertificado";

	/** Set Certificate Number Client	  */
	public void setNoCertificado (String NoCertificado);

	/** Get Certificate Number Client	  */
	public String getNoCertificado();

    /** Column name Nombre_Medico */
    public static final String COLUMNNAME_Nombre_Medico = "Nombre_Medico";

	/** Set Doctor's Name	  */
	public void setNombre_Medico (String Nombre_Medico);

	/** Get Doctor's Name	  */
	public String getNombre_Medico();

    /** Column name Nombre_Paciente */
    public static final String COLUMNNAME_Nombre_Paciente = "Nombre_Paciente";

	/** Set Patient's Name.
	  * Patient's Name
	  */
	public void setNombre_Paciente (String Nombre_Paciente);

	/** Get Patient's Name.
	  * Patient's Name
	  */
	public String getNombre_Paciente();

    /** Column name NumExt */
    public static final String COLUMNNAME_NumExt = "NumExt";

	/** Set External Num.
	  * External Number
	  */
	public void setNumExt (String NumExt);

	/** Get External Num.
	  * External Number
	  */
	public String getNumExt();

    /** Column name NumIn */
    public static final String COLUMNNAME_NumIn = "NumIn";

	/** Set Internal Num.
	  * Internal Number
	  */
	public void setNumIn (String NumIn);

	/** Get Internal Num.
	  * Internal Number
	  */
	public String getNumIn();

    /** Column name Observation */
    public static final String COLUMNNAME_Observation = "Observation";

	/** Set Observation.
	  * Optional short description of the record
	  */
	public void setObservation (String Observation);

	/** Get Observation.
	  * Optional short description of the record
	  */
	public String getObservation();

    /** Column name Partner */
    public static final String COLUMNNAME_Partner = "Partner";

	/** Set Partner	  */
	public void setPartner (String Partner);

	/** Get Partner	  */
	public String getPartner();

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

    /** Column name POReference */
    public static final String COLUMNNAME_POReference = "POReference";

	/** Set Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public void setPOReference (String POReference);

	/** Get Order Reference.
	  * Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference();

    /** Column name Postal */
    public static final String COLUMNNAME_Postal = "Postal";

	/** Set ZIP.
	  * Postal code
	  */
	public void setPostal (String Postal);

	/** Get ZIP.
	  * Postal code
	  */
	public String getPostal();

    /** Column name Posted */
    public static final String COLUMNNAME_Posted = "Posted";

	/** Set Posted.
	  * Posting status
	  */
	public void setPosted (boolean Posted);

	/** Get Posted.
	  * Posting status
	  */
	public boolean isPosted();

    /** Column name PrecFromAseg */
    public static final String COLUMNNAME_PrecFromAseg = "PrecFromAseg";

	/** Set Insurance Price	  */
	public void setPrecFromAseg (boolean PrecFromAseg);

	/** Get Insurance Price	  */
	public boolean isPrecFromAseg();

    /** Column name PrePayment */
    public static final String COLUMNNAME_PrePayment = "PrePayment";

	/** Set Advance payment.
	  * Advance payment
	  */
	public void setPrePayment (BigDecimal PrePayment);

	/** Get Advance payment.
	  * Advance payment
	  */
	public BigDecimal getPrePayment();

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

    /** Column name Rate */
    public static final String COLUMNNAME_Rate = "Rate";

	/** Set Rate.
	  * Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate);

	/** Get Rate.
	  * Rate or Tax or Exchange
	  */
	public BigDecimal getRate();

    /** Column name Rebate */
    public static final String COLUMNNAME_Rebate = "Rebate";

	/** Set Rebate	  */
	public void setRebate (boolean Rebate);

	/** Get Rebate	  */
	public boolean isRebate();

    /** Column name Ref_Invoice_ID */
    public static final String COLUMNNAME_Ref_Invoice_ID = "Ref_Invoice_ID";

	/** Set Referenced Invoice	  */
	public void setRef_Invoice_ID (int Ref_Invoice_ID);

	/** Get Referenced Invoice	  */
	public int getRef_Invoice_ID();

    /** Column name Ref_Invoice_Sales_ID */
    public static final String COLUMNNAME_Ref_Invoice_Sales_ID = "Ref_Invoice_Sales_ID";

	/** Set Ref. sales receipt	  */
	public void setRef_Invoice_Sales_ID (int Ref_Invoice_Sales_ID);

	/** Get Ref. sales receipt	  */
	public int getRef_Invoice_Sales_ID();

    /** Column name Reversal_ID */
    public static final String COLUMNNAME_Reversal_ID = "Reversal_ID";

	/** Set Reversal ID.
	  * ID of document reversal
	  */
	public void setReversal_ID (int Reversal_ID);

	/** Get Reversal ID.
	  * ID of document reversal
	  */
	public int getReversal_ID();

    /** Column name SalesRep_ID */
    public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";

	/** Set Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID);

	/** Get Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public int getSalesRep_ID();

    /** Column name SELLO */
    public static final String COLUMNNAME_SELLO = "SELLO";

	/** Set SELLO	  */
	public void setSELLO (String SELLO);

	/** Get SELLO	  */
	public String getSELLO();

    /** Column name SelloSAT */
    public static final String COLUMNNAME_SelloSAT = "SelloSAT";

	/** Set Service stamp tax administration	  */
	public void setSelloSAT (String SelloSAT);

	/** Get Service stamp tax administration	  */
	public String getSelloSAT();

    /** Column name SendEMail */
    public static final String COLUMNNAME_SendEMail = "SendEMail";

	/** Set Send EMail.
	  * Enable sending Document EMail
	  */
	public void setSendEMail (boolean SendEMail);

	/** Get Send EMail.
	  * Enable sending Document EMail
	  */
	public boolean isSendEMail();

    /** Column name Sexo */
    public static final String COLUMNNAME_Sexo = "Sexo";

	/** Set Sex.
	  * Sex
	  */
	public void setSexo (String Sexo);

	/** Get Sex.
	  * Sex
	  */
	public String getSexo();

    /** Column name SortBy */
    public static final String COLUMNNAME_SortBy = "SortBy";

	/** Set SortBy	  */
	public void setSortBy (String SortBy);

	/** Get SortBy	  */
	public String getSortBy();

    /** Column name TaxAmt */
    public static final String COLUMNNAME_TaxAmt = "TaxAmt";

	/** Set Tax Amount.
	  * Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt);

	/** Get Tax Amount.
	  * Tax Amount for a document
	  */
	public BigDecimal getTaxAmt();

    /** Column name TipoOperacion */
    public static final String COLUMNNAME_TipoOperacion = "TipoOperacion";

	/** Set Operation Type	  */
	public void setTipoOperacion (String TipoOperacion);

	/** Get Operation Type	  */
	public String getTipoOperacion();

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

    /** Column name TrxType */
    public static final String COLUMNNAME_TrxType = "TrxType";

	/** Set Transaction Type.
	  * Type of credit card transaction
	  */
	public void setTrxType (String TrxType);

	/** Get Transaction Type.
	  * Type of credit card transaction
	  */
	public String getTrxType();

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

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Universally Unique Identifier	  */
	public void setUUID (String UUID);

	/** Get Universally Unique Identifier	  */
	public String getUUID();

    /** Column name VerPoliza */
    public static final String COLUMNNAME_VerPoliza = "VerPoliza";

	/** Set Policy	  */
	public void setVerPoliza (String VerPoliza);

	/** Get Policy	  */
	public String getVerPoliza();
}
