/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacExt
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_CtaPacExt 
{

    /** TableName=EXME_CtaPacExt */
    public static final String Table_Name = "EXME_CtaPacExt";

    /** AD_Table_ID=1000090 */
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

    /** Column name Anticipo */
    public static final String COLUMNNAME_Anticipo = "Anticipo";

	/** Set Advance payment	  */
	public void setAnticipo (BigDecimal Anticipo);

	/** Get Advance payment	  */
	public BigDecimal getAnticipo();

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

    /** Column name ChargeStatus */
    public static final String COLUMNNAME_ChargeStatus = "ChargeStatus";

	/** Set Charge Status	  */
	public void setChargeStatus (String ChargeStatus);

	/** Get Charge Status	  */
	public String getChargeStatus();

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

    /** Column name DesctoGlobal */
    public static final String COLUMNNAME_DesctoGlobal = "DesctoGlobal";

	/** Set Global Discount.
	  * Global Discount
	  */
	public void setDesctoGlobal (BigDecimal DesctoGlobal);

	/** Get Global Discount.
	  * Global Discount
	  */
	public BigDecimal getDesctoGlobal();

    /** Column name DesctoGlobalAmt */
    public static final String COLUMNNAME_DesctoGlobalAmt = "DesctoGlobalAmt";

	/** Set Global Disct $.
	  * Amount of Global Discount
	  */
	public void setDesctoGlobalAmt (BigDecimal DesctoGlobalAmt);

	/** Get Global Disct $.
	  * Amount of Global Discount
	  */
	public BigDecimal getDesctoGlobalAmt();

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

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

    /** Column name ExtensionNo */
    public static final String COLUMNNAME_ExtensionNo = "ExtensionNo";

	/** Set Extension Number.
	  * Extension Number
	  */
	public void setExtensionNo (int ExtensionNo);

	/** Get Extension Number.
	  * Extension Number
	  */
	public int getExtensionNo();

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

    /** Column name IsCoasegPercent */
    public static final String COLUMNNAME_IsCoasegPercent = "IsCoasegPercent";

	/** Set Percentage Coinsurance.
	  * Percentage Coinsurance, otherwise is an amount
	  */
	public void setIsCoasegPercent (boolean IsCoasegPercent);

	/** Get Percentage Coinsurance.
	  * Percentage Coinsurance, otherwise is an amount
	  */
	public boolean isCoasegPercent();

    /** Column name IsCoasMedPercent */
    public static final String COLUMNNAME_IsCoasMedPercent = "IsCoasMedPercent";

	/** Set Percentage	  */
	public void setIsCoasMedPercent (boolean IsCoasMedPercent);

	/** Get Percentage	  */
	public boolean isCoasMedPercent();

    /** Column name IsCopPercent */
    public static final String COLUMNNAME_IsCopPercent = "IsCopPercent";

	/** Set Percentage	  */
	public void setIsCopPercent (boolean IsCopPercent);

	/** Get Percentage	  */
	public boolean isCopPercent();

    /** Column name IsDeducInCoaseg */
    public static final String COLUMNNAME_IsDeducInCoaseg = "IsDeducInCoaseg";

	/** Set Including deductibles in the coinsurance.
	  * Including deductibles in the coinsurance
	  */
	public void setIsDeducInCoaseg (boolean IsDeducInCoaseg);

	/** Get Including deductibles in the coinsurance.
	  * Including deductibles in the coinsurance
	  */
	public boolean isDeducInCoaseg();

    /** Column name IsDeducPercent */
    public static final String COLUMNNAME_IsDeducPercent = "IsDeducPercent";

	/** Set Percent	  */
	public void setIsDeducPercent (boolean IsDeducPercent);

	/** Get Percent	  */
	public boolean isDeducPercent();

    /** Column name IsDiscPercent */
    public static final String COLUMNNAME_IsDiscPercent = "IsDiscPercent";

	/** Set Percent	  */
	public void setIsDiscPercent (boolean IsDiscPercent);

	/** Get Percent	  */
	public boolean isDiscPercent();

    /** Column name IsOrderFacLineCategory */
    public static final String COLUMNNAME_IsOrderFacLineCategory = "IsOrderFacLineCategory";

	/** Set Sort by Category invoice line	  */
	public void setIsOrderFacLineCategory (boolean IsOrderFacLineCategory);

	/** Get Sort by Category invoice line	  */
	public boolean isOrderFacLineCategory();

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

    /** Column name Observacion */
    public static final String COLUMNNAME_Observacion = "Observacion";

	/** Set Observation.
	  * Observation
	  */
	public void setObservacion (String Observacion);

	/** Get Observation.
	  * Observation
	  */
	public String getObservacion();

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

    /** Column name Ref_CtaPacExt_ID */
    public static final String COLUMNNAME_Ref_CtaPacExt_ID = "Ref_CtaPacExt_ID";

	/** Set Extension Encounter.
	  * Extension Encounter
	  */
	public void setRef_CtaPacExt_ID (int Ref_CtaPacExt_ID);

	/** Get Extension Encounter.
	  * Extension Encounter
	  */
	public int getRef_CtaPacExt_ID();

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
}
