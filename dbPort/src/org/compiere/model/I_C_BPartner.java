/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_BPartner
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_BPartner 
{

    /** TableName=C_BPartner */
    public static final String Table_Name = "C_BPartner";

    /** AD_Table_ID=291 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AcqusitionCost */
    public static final String COLUMNNAME_AcqusitionCost = "AcqusitionCost";

	/** Set Acquisition Cost.
	  * The cost of gaining the prospect as a customer
	  */
	public void setAcqusitionCost (BigDecimal AcqusitionCost);

	/** Get Acquisition Cost.
	  * The cost of gaining the prospect as a customer
	  */
	public BigDecimal getAcqusitionCost();

    /** Column name ActualLifeTimeValue */
    public static final String COLUMNNAME_ActualLifeTimeValue = "ActualLifeTimeValue";

	/** Set Actual Life Time Value.
	  * Actual Life Time Revenue
	  */
	public void setActualLifeTimeValue (BigDecimal ActualLifeTimeValue);

	/** Get Actual Life Time Value.
	  * Actual Life Time Revenue
	  */
	public BigDecimal getActualLifeTimeValue();

    /** Column name AD_Language */
    public static final String COLUMNNAME_AD_Language = "AD_Language";

	/** Set Language.
	  * Language for this entity
	  */
	public void setAD_Language (String AD_Language);

	/** Get Language.
	  * Language for this entity
	  */
	public String getAD_Language();

    /** Column name AD_OrgBP_ID */
    public static final String COLUMNNAME_AD_OrgBP_ID = "AD_OrgBP_ID";

	/** Set Linked Organization.
	  * The Business Partner is another Organization for explicit Inter-Org transactions
	  */
	public void setAD_OrgBP_ID (String AD_OrgBP_ID);

	/** Get Linked Organization.
	  * The Business Partner is another Organization for explicit Inter-Org transactions
	  */
	public String getAD_OrgBP_ID();

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

    /** Column name BPartner_Parent_ID */
    public static final String COLUMNNAME_BPartner_Parent_ID = "BPartner_Parent_ID";

	/** Set Company Parent.
	  * Company Parent
	  */
	public void setBPartner_Parent_ID (int BPartner_Parent_ID);

	/** Get Company Parent.
	  * Company Parent
	  */
	public int getBPartner_Parent_ID();

    /** Column name BP_Class */
    public static final String COLUMNNAME_BP_Class = "BP_Class";

	/** Set B. Partner Class	  */
	public void setBP_Class (String BP_Class);

	/** Get B. Partner Class	  */
	public String getBP_Class();

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

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Company Group.
	  * Company Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Company Group.
	  * Company Group
	  */
	public int getC_BP_Group_ID();

	public I_C_BP_Group getC_BP_Group() throws RuntimeException;

    /** Column name C_Dunning_ID */
    public static final String COLUMNNAME_C_Dunning_ID = "C_Dunning_ID";

	/** Set Dunning.
	  * Dunning Rules for overdue invoices
	  */
	public void setC_Dunning_ID (int C_Dunning_ID);

	/** Get Dunning.
	  * Dunning Rules for overdue invoices
	  */
	public int getC_Dunning_ID();

	public I_C_Dunning getC_Dunning() throws RuntimeException;

    /** Column name C_Greeting_ID */
    public static final String COLUMNNAME_C_Greeting_ID = "C_Greeting_ID";

	/** Set Greeting.
	  * Greeting to print on correspondence
	  */
	public void setC_Greeting_ID (int C_Greeting_ID);

	/** Get Greeting.
	  * Greeting to print on correspondence
	  */
	public int getC_Greeting_ID();

	public I_C_Greeting getC_Greeting() throws RuntimeException;

    /** Column name C_InvoiceSchedule_ID */
    public static final String COLUMNNAME_C_InvoiceSchedule_ID = "C_InvoiceSchedule_ID";

	/** Set Invoice Schedule.
	  * Schedule for generating Invoices
	  */
	public void setC_InvoiceSchedule_ID (int C_InvoiceSchedule_ID);

	/** Get Invoice Schedule.
	  * Schedule for generating Invoices
	  */
	public int getC_InvoiceSchedule_ID();

	public I_C_InvoiceSchedule getC_InvoiceSchedule() throws RuntimeException;

    /** Column name Claim_ID_Recp */
    public static final String COLUMNNAME_Claim_ID_Recp = "Claim_ID_Recp";

	/** Set Claim Reception.
	  * Claim Reception
	  */
	public void setClaim_ID_Recp (String Claim_ID_Recp);

	/** Get Claim Reception.
	  * Claim Reception
	  */
	public String getClaim_ID_Recp();

    /** Column name Claim_ID_Send */
    public static final String COLUMNNAME_Claim_ID_Send = "Claim_ID_Send";

	/** Set Claim Send	  */
	public void setClaim_ID_Send (String Claim_ID_Send);

	/** Get Claim Send	  */
	public String getClaim_ID_Send();

    /** Column name Copias */
    public static final String COLUMNNAME_Copias = "Copias";

	/** Set Copies	  */
	public void setCopias (BigDecimal Copias);

	/** Get Copies	  */
	public BigDecimal getCopias();

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

    /** Column name C_TaxGroup_ID */
    public static final String COLUMNNAME_C_TaxGroup_ID = "C_TaxGroup_ID";

	/** Set Tax Group	  */
	public void setC_TaxGroup_ID (int C_TaxGroup_ID);

	/** Get Tax Group	  */
	public int getC_TaxGroup_ID();

	public I_C_TaxGroup getC_TaxGroup() throws RuntimeException;

    /** Column name DeliveryRule */
    public static final String COLUMNNAME_DeliveryRule = "DeliveryRule";

	/** Set Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public void setDeliveryRule (String DeliveryRule);

	/** Get Delivery Rule.
	  * Defines the timing of Delivery
	  */
	public String getDeliveryRule();

    /** Column name DeliveryViaRule */
    public static final String COLUMNNAME_DeliveryViaRule = "DeliveryViaRule";

	/** Set Delivery Via.
	  * How the order will be delivered
	  */
	public void setDeliveryViaRule (String DeliveryViaRule);

	/** Get Delivery Via.
	  * How the order will be delivered
	  */
	public String getDeliveryViaRule();

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

    /** Column name DocumentCopies */
    public static final String COLUMNNAME_DocumentCopies = "DocumentCopies";

	/** Set Document Copies.
	  * Number of copies to be printed
	  */
	public void setDocumentCopies (int DocumentCopies);

	/** Get Document Copies.
	  * Number of copies to be printed
	  */
	public int getDocumentCopies();

    /** Column name DunningGrace */
    public static final String COLUMNNAME_DunningGrace = "DunningGrace";

	/** Set Dunning Grace	  */
	public void setDunningGrace (Timestamp DunningGrace);

	/** Get Dunning Grace	  */
	public Timestamp getDunningGrace();

    /** Column name DUNS */
    public static final String COLUMNNAME_DUNS = "DUNS";

	/** Set D-U-N-S.
	  * Dun & Bradstreet Number
	  */
	public void setDUNS (String DUNS);

	/** Get D-U-N-S.
	  * Dun & Bradstreet Number
	  */
	public String getDUNS();

    /** Column name Elig_ID_Recp */
    public static final String COLUMNNAME_Elig_ID_Recp = "Elig_ID_Recp";

	/** Set Eligibility Reception	  */
	public void setElig_ID_Recp (String Elig_ID_Recp);

	/** Get Eligibility Reception	  */
	public String getElig_ID_Recp();

    /** Column name Elig_ID_Send */
    public static final String COLUMNNAME_Elig_ID_Send = "Elig_ID_Send";

	/** Set Payer ID	  */
	public void setElig_ID_Send (String Elig_ID_Send);

	/** Get Payer ID	  */
	public String getElig_ID_Send();

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

    /** Column name EnviaEMail */
    public static final String COLUMNNAME_EnviaEMail = "EnviaEMail";

	/** Set Send Email.
	  * Send Email
	  */
	public void setEnviaEMail (String EnviaEMail);

	/** Get Send Email.
	  * Send Email
	  */
	public String getEnviaEMail();

    /** Column name EXME_FinancialClass_ID */
    public static final String COLUMNNAME_EXME_FinancialClass_ID = "EXME_FinancialClass_ID";

	/** Set Financial Class	  */
	public void setEXME_FinancialClass_ID (int EXME_FinancialClass_ID);

	/** Get Financial Class	  */
	public int getEXME_FinancialClass_ID();

	public I_EXME_FinancialClass getEXME_FinancialClass() throws RuntimeException;

    /** Column name EXME_PayerClass_ID */
    public static final String COLUMNNAME_EXME_PayerClass_ID = "EXME_PayerClass_ID";

	/** Set Payer Class	  */
	public void setEXME_PayerClass_ID (int EXME_PayerClass_ID);

	/** Get Payer Class	  */
	public int getEXME_PayerClass_ID();

	public I_EXME_PayerClass getEXME_PayerClass() throws RuntimeException;

    /** Column name FacturaEmail */
    public static final String COLUMNNAME_FacturaEmail = "FacturaEmail";

	/** Set Email Bill	  */
	public void setFacturaEmail (boolean FacturaEmail);

	/** Get Email Bill	  */
	public boolean isFacturaEmail();

    /** Column name FacturarAseg */
    public static final String COLUMNNAME_FacturarAseg = "FacturarAseg";

	/** Set Invoice to Insurance Company.
	  * Invoice to Insurance Company
	  */
	public void setFacturarAseg (boolean FacturarAseg);

	/** Get Invoice to Insurance Company.
	  * Invoice to Insurance Company
	  */
	public boolean isFacturarAseg();

    /** Column name FirstSale */
    public static final String COLUMNNAME_FirstSale = "FirstSale";

	/** Set First Sale.
	  * Date of First Sale
	  */
	public void setFirstSale (Timestamp FirstSale);

	/** Get First Sale.
	  * Date of First Sale
	  */
	public Timestamp getFirstSale();

    /** Column name FlatDiscount */
    public static final String COLUMNNAME_FlatDiscount = "FlatDiscount";

	/** Set Flat Discount %.
	  * Flat discount percentage 
	  */
	public void setFlatDiscount (BigDecimal FlatDiscount);

	/** Get Flat Discount %.
	  * Flat discount percentage 
	  */
	public BigDecimal getFlatDiscount();

    /** Column name FreightCostRule */
    public static final String COLUMNNAME_FreightCostRule = "FreightCostRule";

	/** Set Freight Cost Rule.
	  * Method for charging Freight
	  */
	public void setFreightCostRule (String FreightCostRule);

	/** Get Freight Cost Rule.
	  * Method for charging Freight
	  */
	public String getFreightCostRule();

    /** Column name Identificador */
    public static final String COLUMNNAME_Identificador = "Identificador";

	/** Set Identifier	  */
	public void setIdentificador (String Identificador);

	/** Get Identifier	  */
	public String getIdentificador();

    /** Column name ImpresionDe */
    public static final String COLUMNNAME_ImpresionDe = "ImpresionDe";

	/** Set ImpresionDe	  */
	public void setImpresionDe (String ImpresionDe);

	/** Get ImpresionDe	  */
	public String getImpresionDe();

    /** Column name Impresiones */
    public static final String COLUMNNAME_Impresiones = "Impresiones";

	/** Set Impressions	  */
	public void setImpresiones (String Impresiones);

	/** Get Impressions	  */
	public String getImpresiones();

    /** Column name ImprimeFactura */
    public static final String COLUMNNAME_ImprimeFactura = "ImprimeFactura";

	/** Set Print Invoice	  */
	public void setImprimeFactura (boolean ImprimeFactura);

	/** Get Print Invoice	  */
	public boolean isImprimeFactura();

    /** Column name Invoice_PrintFormat_ID */
    public static final String COLUMNNAME_Invoice_PrintFormat_ID = "Invoice_PrintFormat_ID";

	/** Set Invoice Print Format.
	  * Print Format for printing Invoices
	  */
	public void setInvoice_PrintFormat_ID (int Invoice_PrintFormat_ID);

	/** Get Invoice Print Format.
	  * Print Format for printing Invoices
	  */
	public int getInvoice_PrintFormat_ID();

    /** Column name InvoiceRule */
    public static final String COLUMNNAME_InvoiceRule = "InvoiceRule";

	/** Set Invoice Rule.
	  * Frequency and method of invoicing 
	  */
	public void setInvoiceRule (String InvoiceRule);

	/** Get Invoice Rule.
	  * Frequency and method of invoicing 
	  */
	public String getInvoiceRule();

    /** Column name IsBanorte */
    public static final String COLUMNNAME_IsBanorte = "IsBanorte";

	/** Set Main business partner	  */
	public void setIsBanorte (boolean IsBanorte);

	/** Get Main business partner	  */
	public boolean isBanorte();

    /** Column name IsCreditor */
    public static final String COLUMNNAME_IsCreditor = "IsCreditor";

	/** Set Diverse Creditor.
	  * Diverse Creditor
	  */
	public void setIsCreditor (boolean IsCreditor);

	/** Get Diverse Creditor.
	  * Diverse Creditor
	  */
	public boolean isCreditor();

    /** Column name IsCustomer */
    public static final String COLUMNNAME_IsCustomer = "IsCustomer";

	/** Set Customer.
	  * Indicates if this Business Partner is a Customer
	  */
	public void setIsCustomer (boolean IsCustomer);

	/** Get Customer.
	  * Indicates if this Business Partner is a Customer
	  */
	public boolean isCustomer();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

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

    /** Column name IsEmployee */
    public static final String COLUMNNAME_IsEmployee = "IsEmployee";

	/** Set Employee.
	  * Indicates if  this Business Partner is an employee
	  */
	public void setIsEmployee (boolean IsEmployee);

	/** Get Employee.
	  * Indicates if  this Business Partner is an employee
	  */
	public boolean isEmployee();

    /** Column name isExento */
    public static final String COLUMNNAME_isExento = "isExento";

	/** Set Billing after appointment	  */
	public void setisExento (boolean isExento);

	/** Get Billing after appointment	  */
	public boolean isExento();

    /** Column name IsFactEspec */
    public static final String COLUMNNAME_IsFactEspec = "IsFactEspec";

	/** Set Billing multiple	  */
	public void setIsFactEspec (boolean IsFactEspec);

	/** Get Billing multiple	  */
	public boolean isFactEspec();

    /** Column name IsGI */
    public static final String COLUMNNAME_IsGI = "IsGI";

	/** Set Preferably generic name	  */
	public void setIsGI (boolean IsGI);

	/** Get Preferably generic name	  */
	public boolean isGI();

    /** Column name IsMiniPack */
    public static final String COLUMNNAME_IsMiniPack = "IsMiniPack";

	/** Set Allows mini packages.
	  * Allows mini packages
	  */
	public void setIsMiniPack (boolean IsMiniPack);

	/** Get Allows mini packages.
	  * Allows mini packages
	  */
	public boolean isMiniPack();

    /** Column name IsNational */
    public static final String COLUMNNAME_IsNational = "IsNational";

	/** Set National.
	  * National
	  */
	public void setIsNational (boolean IsNational);

	/** Get National.
	  * National
	  */
	public boolean isNational();

    /** Column name IsNotaCargo */
    public static final String COLUMNNAME_IsNotaCargo = "IsNotaCargo";

	/** Set Allows debit memo.
	  * Allows debit memo
	  */
	public void setIsNotaCargo (boolean IsNotaCargo);

	/** Get Allows debit memo.
	  * Allows debit memo
	  */
	public boolean isNotaCargo();

    /** Column name IsOneTime */
    public static final String COLUMNNAME_IsOneTime = "IsOneTime";

	/** Set One time transaction	  */
	public void setIsOneTime (boolean IsOneTime);

	/** Get One time transaction	  */
	public boolean isOneTime();

    /** Column name IsOrderFacLineCategory */
    public static final String COLUMNNAME_IsOrderFacLineCategory = "IsOrderFacLineCategory";

	/** Set Sort by Category invoice line	  */
	public void setIsOrderFacLineCategory (boolean IsOrderFacLineCategory);

	/** Get Sort by Category invoice line	  */
	public boolean isOrderFacLineCategory();

    /** Column name IsPatient */
    public static final String COLUMNNAME_IsPatient = "IsPatient";

	/** Set Patient.
	  * Is patient
	  */
	public void setIsPatient (boolean IsPatient);

	/** Get Patient.
	  * Is patient
	  */
	public boolean isPatient();

    /** Column name IsPension */
    public static final String COLUMNNAME_IsPension = "IsPension";

	/** Set Pension	  */
	public void setIsPension (boolean IsPension);

	/** Get Pension	  */
	public boolean isPension();

    /** Column name IsPOTaxExempt */
    public static final String COLUMNNAME_IsPOTaxExempt = "IsPOTaxExempt";

	/** Set PO Tax exempt.
	  * Business partner is exempt from tax on purchases
	  */
	public void setIsPOTaxExempt (boolean IsPOTaxExempt);

	/** Get PO Tax exempt.
	  * Business partner is exempt from tax on purchases
	  */
	public boolean isPOTaxExempt();

    /** Column name IsProspect */
    public static final String COLUMNNAME_IsProspect = "IsProspect";

	/** Set Prospect.
	  * Indicates this is a Prospect
	  */
	public void setIsProspect (boolean IsProspect);

	/** Get Prospect.
	  * Indicates this is a Prospect
	  */
	public boolean isProspect();

    /** Column name IsSalesRep */
    public static final String COLUMNNAME_IsSalesRep = "IsSalesRep";

	/** Set Sales Representative.
	  * Indicates if  the business partner is a sales representative or company agent
	  */
	public void setIsSalesRep (boolean IsSalesRep);

	/** Get Sales Representative.
	  * Indicates if  the business partner is a sales representative or company agent
	  */
	public boolean isSalesRep();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

    /** Column name IsTaxExempt */
    public static final String COLUMNNAME_IsTaxExempt = "IsTaxExempt";

	/** Set Tax exempt.
	  * Business partner is exempt from tax
	  */
	public void setIsTaxExempt (boolean IsTaxExempt);

	/** Get Tax exempt.
	  * Business partner is exempt from tax
	  */
	public boolean isTaxExempt();

    /** Column name IsVendor */
    public static final String COLUMNNAME_IsVendor = "IsVendor";

	/** Set Vendor.
	  * Indicates if this Business Partner is a Vendor
	  */
	public void setIsVendor (boolean IsVendor);

	/** Get Vendor.
	  * Indicates if this Business Partner is a Vendor
	  */
	public boolean isVendor();

    /** Column name LlaveDeBusqueda */
    public static final String COLUMNNAME_LlaveDeBusqueda = "LlaveDeBusqueda";

	/** Set LlaveDeBusqueda	  */
	public void setLlaveDeBusqueda (String LlaveDeBusqueda);

	/** Get LlaveDeBusqueda	  */
	public String getLlaveDeBusqueda();

    /** Column name Logo_ID */
    public static final String COLUMNNAME_Logo_ID = "Logo_ID";

	/** Set Logo	  */
	public void setLogo_ID (int Logo_ID);

	/** Get Logo	  */
	public int getLogo_ID();

    /** Column name M_DiscountSchema_ID */
    public static final String COLUMNNAME_M_DiscountSchema_ID = "M_DiscountSchema_ID";

	/** Set Discount Schema.
	  * Schema to calculate the trade discount percentage
	  */
	public void setM_DiscountSchema_ID (int M_DiscountSchema_ID);

	/** Get Discount Schema.
	  * Schema to calculate the trade discount percentage
	  */
	public int getM_DiscountSchema_ID();

    /** Column name ModificaEnFactura */
    public static final String COLUMNNAME_ModificaEnFactura = "ModificaEnFactura";

	/** Set Modify In Invoice	  */
	public void setModificaEnFactura (boolean ModificaEnFactura);

	/** Get Modify In Invoice	  */
	public boolean isModificaEnFactura();

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

    /** Column name NAICS */
    public static final String COLUMNNAME_NAICS = "NAICS";

	/** Set NAICS/SIC.
	  * Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	  */
	public void setNAICS (String NAICS);

	/** Get NAICS/SIC.
	  * Standard Industry Code or its successor NAIC - http://www.osha.gov/oshstats/sicser.html
	  */
	public String getNAICS();

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

    /** Column name Name2 */
    public static final String COLUMNNAME_Name2 = "Name2";

	/** Set Name 2.
	  * Additional Name
	  */
	public void setName2 (String Name2);

	/** Get Name 2.
	  * Additional Name
	  */
	public String getName2();

    /** Column name NumberEmployees */
    public static final String COLUMNNAME_NumberEmployees = "NumberEmployees";

	/** Set Employees.
	  * Number of employees
	  */
	public void setNumberEmployees (int NumberEmployees);

	/** Get Employees.
	  * Number of employees
	  */
	public int getNumberEmployees();

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

    /** Column name PaymentRulePO */
    public static final String COLUMNNAME_PaymentRulePO = "PaymentRulePO";

	/** Set Payment Rule.
	  * Purchase payment option
	  */
	public void setPaymentRulePO (String PaymentRulePO);

	/** Get Payment Rule.
	  * Purchase payment option
	  */
	public String getPaymentRulePO();

    /** Column name PO_DiscountSchema_ID */
    public static final String COLUMNNAME_PO_DiscountSchema_ID = "PO_DiscountSchema_ID";

	/** Set PO Discount Schema.
	  * Schema to calculate the purchase trade discount percentage
	  */
	public void setPO_DiscountSchema_ID (int PO_DiscountSchema_ID);

	/** Get PO Discount Schema.
	  * Schema to calculate the purchase trade discount percentage
	  */
	public int getPO_DiscountSchema_ID();

    /** Column name PO_PaymentTerm_ID */
    public static final String COLUMNNAME_PO_PaymentTerm_ID = "PO_PaymentTerm_ID";

	/** Set PO Payment Term.
	  * Payment rules for a purchase order
	  */
	public void setPO_PaymentTerm_ID (int PO_PaymentTerm_ID);

	/** Get PO Payment Term.
	  * Payment rules for a purchase order
	  */
	public int getPO_PaymentTerm_ID();

    /** Column name PO_PriceList_ID */
    public static final String COLUMNNAME_PO_PriceList_ID = "PO_PriceList_ID";

	/** Set Purchase Pricelist.
	  * Price List used by this Business Partner
	  */
	public void setPO_PriceList_ID (int PO_PriceList_ID);

	/** Get Purchase Pricelist.
	  * Price List used by this Business Partner
	  */
	public int getPO_PriceList_ID();

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

    /** Column name PotentialLifeTimeValue */
    public static final String COLUMNNAME_PotentialLifeTimeValue = "PotentialLifeTimeValue";

	/** Set Potential Life Time Value.
	  * Total Revenue expected
	  */
	public void setPotentialLifeTimeValue (BigDecimal PotentialLifeTimeValue);

	/** Get Potential Life Time Value.
	  * Total Revenue expected
	  */
	public BigDecimal getPotentialLifeTimeValue();

    /** Column name QueuingTime */
    public static final String COLUMNNAME_QueuingTime = "QueuingTime";

	/** Set Queuing Time	  */
	public void setQueuingTime (int QueuingTime);

	/** Get Queuing Time	  */
	public int getQueuingTime();

    /** Column name Rating */
    public static final String COLUMNNAME_Rating = "Rating";

	/** Set Rating.
	  * Classification or Importance
	  */
	public void setRating (String Rating);

	/** Get Rating.
	  * Classification or Importance
	  */
	public String getRating();

    /** Column name ReferenceNo */
    public static final String COLUMNNAME_ReferenceNo = "ReferenceNo";

	/** Set Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo);

	/** Get Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo();

    /** Column name Referencia_Cliente */
    public static final String COLUMNNAME_Referencia_Cliente = "Referencia_Cliente";

	/** Set Customer Reference	  */
	public void setReferencia_Cliente (String Referencia_Cliente);

	/** Get Customer Reference	  */
	public String getReferencia_Cliente();

    /** Column name Referencia_Direccion */
    public static final String COLUMNNAME_Referencia_Direccion = "Referencia_Direccion";

	/** Set Reference Address	  */
	public void setReferencia_Direccion (String Referencia_Direccion);

	/** Get Reference Address	  */
	public String getReferencia_Direccion();

    /** Column name RequerirEleg */
    public static final String COLUMNNAME_RequerirEleg = "RequerirEleg";

	/** Set Eligibility require	  */
	public void setRequerirEleg (boolean RequerirEleg);

	/** Get Eligibility require	  */
	public boolean isRequerirEleg();

    /** Column name reward_amount */
    public static final String COLUMNNAME_reward_amount = "reward_amount";

	/** Set reward_amount	  */
	public void setreward_amount (BigDecimal reward_amount);

	/** Get reward_amount	  */
	public BigDecimal getreward_amount();

    /** Column name reward_card */
    public static final String COLUMNNAME_reward_card = "reward_card";

	/** Set reward_card	  */
	public void setreward_card (String reward_card);

	/** Get reward_card	  */
	public String getreward_card();

    /** Column name Rfc */
    public static final String COLUMNNAME_Rfc = "Rfc";

	/** Set Taxpayer Identification Number.
	  * Taxpayer Identification Number
	  */
	public void setRfc (String Rfc);

	/** Get Taxpayer Identification Number.
	  * Taxpayer Identification Number
	  */
	public String getRfc();

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

    /** Column name SalesVolume */
    public static final String COLUMNNAME_SalesVolume = "SalesVolume";

	/** Set Sales Volume in 1.000.
	  * Total Volume of Sales in Thousands of Currency
	  */
	public void setSalesVolume (int SalesVolume);

	/** Get Sales Volume in 1.000.
	  * Total Volume of Sales in Thousands of Currency
	  */
	public int getSalesVolume();

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

    /** Column name ShareOfCustomer */
    public static final String COLUMNNAME_ShareOfCustomer = "ShareOfCustomer";

	/** Set Share.
	  * Share of Customer's business as a percentage
	  */
	public void setShareOfCustomer (int ShareOfCustomer);

	/** Get Share.
	  * Share of Customer's business as a percentage
	  */
	public int getShareOfCustomer();

    /** Column name ShelfLifeMinPct */
    public static final String COLUMNNAME_ShelfLifeMinPct = "ShelfLifeMinPct";

	/** Set Min Shelf Life %.
	  * Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public void setShelfLifeMinPct (int ShelfLifeMinPct);

	/** Get Min Shelf Life %.
	  * Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public int getShelfLifeMinPct();

    /** Column name SO_CreditLimit */
    public static final String COLUMNNAME_SO_CreditLimit = "SO_CreditLimit";

	/** Set Credit Limit.
	  * Total outstanding invoice amounts allowed
	  */
	public void setSO_CreditLimit (BigDecimal SO_CreditLimit);

	/** Get Credit Limit.
	  * Total outstanding invoice amounts allowed
	  */
	public BigDecimal getSO_CreditLimit();

    /** Column name SOCreditStatus */
    public static final String COLUMNNAME_SOCreditStatus = "SOCreditStatus";

	/** Set Credit Status.
	  * Business Partner Credit Status
	  */
	public void setSOCreditStatus (String SOCreditStatus);

	/** Get Credit Status.
	  * Business Partner Credit Status
	  */
	public String getSOCreditStatus();

    /** Column name SO_CreditUsed */
    public static final String COLUMNNAME_SO_CreditUsed = "SO_CreditUsed";

	/** Set Credit Used.
	  * Current open balance
	  */
	public void setSO_CreditUsed (BigDecimal SO_CreditUsed);

	/** Get Credit Used.
	  * Current open balance
	  */
	public BigDecimal getSO_CreditUsed();

    /** Column name SO_Description */
    public static final String COLUMNNAME_SO_Description = "SO_Description";

	/** Set Order Description.
	  * Description to be used on orders
	  */
	public void setSO_Description (String SO_Description);

	/** Get Order Description.
	  * Description to be used on orders
	  */
	public String getSO_Description();

    /** Column name SupportBilling */
    public static final String COLUMNNAME_SupportBilling = "SupportBilling";

	/** Set Support Billing	  */
	public void setSupportBilling (String SupportBilling);

	/** Get Support Billing	  */
	public String getSupportBilling();

    /** Column name SupportEclaim */
    public static final String COLUMNNAME_SupportEclaim = "SupportEclaim";

	/** Set Support eClaim	  */
	public void setSupportEclaim (boolean SupportEclaim);

	/** Get Support eClaim	  */
	public boolean isSupportEclaim();

    /** Column name SupportElegibilityVerification */
    public static final String COLUMNNAME_SupportElegibilityVerification = "SupportElegibilityVerification";

	/** Set Support Elegibility Verification	  */
	public void setSupportElegibilityVerification (boolean SupportElegibilityVerification);

	/** Get Support Elegibility Verification	  */
	public boolean isSupportElegibilityVerification();

    /** Column name TaxID */
    public static final String COLUMNNAME_TaxID = "TaxID";

	/** Set Tax ID.
	  * Tax Identification
	  */
	public void setTaxID (String TaxID);

	/** Get Tax ID.
	  * Tax Identification
	  */
	public String getTaxID();

    /** Column name TipoProveedor */
    public static final String COLUMNNAME_TipoProveedor = "TipoProveedor";

	/** Set Provider Type	  */
	public void setTipoProveedor (String TipoProveedor);

	/** Get Provider Type	  */
	public String getTipoProveedor();

    /** Column name TotalOpenBalance */
    public static final String COLUMNNAME_TotalOpenBalance = "TotalOpenBalance";

	/** Set Open Balance.
	  * Total Open Balance Amount in primary Accounting Currency
	  */
	public void setTotalOpenBalance (BigDecimal TotalOpenBalance);

	/** Get Open Balance.
	  * Total Open Balance Amount in primary Accounting Currency
	  */
	public BigDecimal getTotalOpenBalance();

    /** Column name URL */
    public static final String COLUMNNAME_URL = "URL";

	/** Set URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public void setURL (String URL);

	/** Get URL.
	  * Full URL address - e.g. http://www.compiere.org
	  */
	public String getURL();

    /** Column name UseClearingHouse */
    public static final String COLUMNNAME_UseClearingHouse = "UseClearingHouse";

	/** Set Use Clearing House	  */
	public void setUseClearingHouse (boolean UseClearingHouse);

	/** Get Use Clearing House	  */
	public boolean isUseClearingHouse();

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
