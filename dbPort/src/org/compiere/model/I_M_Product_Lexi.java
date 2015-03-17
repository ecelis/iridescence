/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Product_Lexi
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_M_Product_Lexi 
{

    /** TableName=M_Product_Lexi */
    public static final String Table_Name = "M_Product_Lexi";

    /** AD_Table_ID=1201357 */
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

    /** Column name CambiaPrecio */
    public static final String COLUMNNAME_CambiaPrecio = "CambiaPrecio";

	/** Set Change price	  */
	public void setCambiaPrecio (boolean CambiaPrecio);

	/** Get Change price	  */
	public boolean isCambiaPrecio();

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

    /** Column name Classification */
    public static final String COLUMNNAME_Classification = "Classification";

	/** Set Classification.
	  * Classification for grouping
	  */
	public void setClassification (boolean Classification);

	/** Get Classification.
	  * Classification for grouping
	  */
	public boolean isClassification();

    /** Column name C_RevenueRecognition_ID */
    public static final String COLUMNNAME_C_RevenueRecognition_ID = "C_RevenueRecognition_ID";

	/** Set Revenue Recognition.
	  * Method for recording revenue
	  */
	public void setC_RevenueRecognition_ID (int C_RevenueRecognition_ID);

	/** Get Revenue Recognition.
	  * Method for recording revenue
	  */
	public int getC_RevenueRecognition_ID();

    /** Column name C_SubscriptionType_ID */
    public static final String COLUMNNAME_C_SubscriptionType_ID = "C_SubscriptionType_ID";

	/** Set Subscription Type.
	  * Type of subscription
	  */
	public void setC_SubscriptionType_ID (int C_SubscriptionType_ID);

	/** Get Subscription Type.
	  * Type of subscription
	  */
	public int getC_SubscriptionType_ID();

    /** Column name C_TaxCategory_ID */
    public static final String COLUMNNAME_C_TaxCategory_ID = "C_TaxCategory_ID";

	/** Set Tax Category.
	  * Tax Category
	  */
	public void setC_TaxCategory_ID (int C_TaxCategory_ID);

	/** Get Tax Category.
	  * Tax Category
	  */
	public int getC_TaxCategory_ID();

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

    /** Column name C_UOMWeight_ID */
    public static final String COLUMNNAME_C_UOMWeight_ID = "C_UOMWeight_ID";

	/** Set UOM of Weight.
	  * UOM of Weight
	  */
	public void setC_UOMWeight_ID (int C_UOMWeight_ID);

	/** Get UOM of Weight.
	  * UOM of Weight
	  */
	public int getC_UOMWeight_ID();

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

    /** Column name DescriptionURL */
    public static final String COLUMNNAME_DescriptionURL = "DescriptionURL";

	/** Set Description URL.
	  * URL for the description
	  */
	public void setDescriptionURL (String DescriptionURL);

	/** Get Description URL.
	  * URL for the description
	  */
	public String getDescriptionURL();

    /** Column name Discontinued */
    public static final String COLUMNNAME_Discontinued = "Discontinued";

	/** Set Discontinued.
	  * This product is no longer available
	  */
	public void setDiscontinued (boolean Discontinued);

	/** Get Discontinued.
	  * This product is no longer available
	  */
	public boolean isDiscontinued();

    /** Column name DiscontinuedBy */
    public static final String COLUMNNAME_DiscontinuedBy = "DiscontinuedBy";

	/** Set Discontinued by.
	  * Discontinued By
	  */
	public void setDiscontinuedBy (Timestamp DiscontinuedBy);

	/** Get Discontinued by.
	  * Discontinued By
	  */
	public Timestamp getDiscontinuedBy();

    /** Column name DocumentNote */
    public static final String COLUMNNAME_DocumentNote = "DocumentNote";

	/** Set Document Note.
	  * Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote);

	/** Get Document Note.
	  * Additional information for a Document
	  */
	public String getDocumentNote();

    /** Column name DownloadURL */
    public static final String COLUMNNAME_DownloadURL = "DownloadURL";

	/** Set Download URL.
	  * URL of the Download files
	  */
	public void setDownloadURL (String DownloadURL);

	/** Get Download URL.
	  * URL of the Download files
	  */
	public String getDownloadURL();

    /** Column name EsVacuna */
    public static final String COLUMNNAME_EsVacuna = "EsVacuna";

	/** Set Is Vaccine	  */
	public void setEsVacuna (boolean EsVacuna);

	/** Get Is Vaccine	  */
	public boolean isEsVacuna();

    /** Column name EXME_ConceptoFac_ID */
    public static final String COLUMNNAME_EXME_ConceptoFac_ID = "EXME_ConceptoFac_ID";

	/** Set Invoice Concept	  */
	public void setEXME_ConceptoFac_ID (int EXME_ConceptoFac_ID);

	/** Get Invoice Concept	  */
	public int getEXME_ConceptoFac_ID();

    /** Column name EXME_DoseForm_ID */
    public static final String COLUMNNAME_EXME_DoseForm_ID = "EXME_DoseForm_ID";

	/** Set DoseForm.
	  * DoseForm
	  */
	public void setEXME_DoseForm_ID (int EXME_DoseForm_ID);

	/** Get DoseForm.
	  * DoseForm
	  */
	public int getEXME_DoseForm_ID();

    /** Column name EXME_FactorPre_ID */
    public static final String COLUMNNAME_EXME_FactorPre_ID = "EXME_FactorPre_ID";

	/** Set Price Factor.
	  * Sales Price Factor
	  */
	public void setEXME_FactorPre_ID (int EXME_FactorPre_ID);

	/** Get Price Factor.
	  * Sales Price Factor
	  */
	public int getEXME_FactorPre_ID();

    /** Column name EXME_GenProduct_Lexi_ID */
    public static final String COLUMNNAME_EXME_GenProduct_Lexi_ID = "EXME_GenProduct_Lexi_ID";

	/** Set EXME_GenProduct_Lexi_ID	  */
	public void setEXME_GenProduct_Lexi_ID (int EXME_GenProduct_Lexi_ID);

	/** Get EXME_GenProduct_Lexi_ID	  */
	public int getEXME_GenProduct_Lexi_ID();

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

    /** Column name EXME_Labeler_ID */
    public static final String COLUMNNAME_EXME_Labeler_ID = "EXME_Labeler_ID";

	/** Set Labeler.
	  * Labeler
	  */
	public void setEXME_Labeler_ID (int EXME_Labeler_ID);

	/** Get Labeler.
	  * Labeler
	  */
	public int getEXME_Labeler_ID();

    /** Column name EXME_Loinc_ID */
    public static final String COLUMNNAME_EXME_Loinc_ID = "EXME_Loinc_ID";

	/** Set LOINC Code	  */
	public void setEXME_Loinc_ID (int EXME_Loinc_ID);

	/** Get LOINC Code	  */
	public int getEXME_Loinc_ID();

    /** Column name EXME_ProductFam_ID */
    public static final String COLUMNNAME_EXME_ProductFam_ID = "EXME_ProductFam_ID";

	/** Set Family Products.
	  * Family Products
	  */
	public void setEXME_ProductFam_ID (int EXME_ProductFam_ID);

	/** Get Family Products.
	  * Family Products
	  */
	public int getEXME_ProductFam_ID();

    /** Column name EXME_Route_ID */
    public static final String COLUMNNAME_EXME_Route_ID = "EXME_Route_ID";

	/** Set Route.
	  * Route
	  */
	public void setEXME_Route_ID (int EXME_Route_ID);

	/** Get Route.
	  * Route
	  */
	public int getEXME_Route_ID();

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

    /** Column name Generico */
    public static final String COLUMNNAME_Generico = "Generico";

	/** Set Generic.
	  * Generic
	  */
	public void setGenerico (boolean Generico);

	/** Get Generic.
	  * Generic
	  */
	public boolean isGenerico();

    /** Column name Group1 */
    public static final String COLUMNNAME_Group1 = "Group1";

	/** Set Group1	  */
	public void setGroup1 (String Group1);

	/** Get Group1	  */
	public String getGroup1();

    /** Column name Group2 */
    public static final String COLUMNNAME_Group2 = "Group2";

	/** Set Group2	  */
	public void setGroup2 (String Group2);

	/** Get Group2	  */
	public String getGroup2();

    /** Column name GuaranteeDays */
    public static final String COLUMNNAME_GuaranteeDays = "GuaranteeDays";

	/** Set Guarantee Days.
	  * Number of days the product is guaranteed or available
	  */
	public void setGuaranteeDays (int GuaranteeDays);

	/** Get Guarantee Days.
	  * Number of days the product is guaranteed or available
	  */
	public int getGuaranteeDays();

    /** Column name GuaranteeDaysMin */
    public static final String COLUMNNAME_GuaranteeDaysMin = "GuaranteeDaysMin";

	/** Set Min Guarantee Days.
	  * Minumum number of guarantee days
	  */
	public void setGuaranteeDaysMin (int GuaranteeDaysMin);

	/** Get Min Guarantee Days.
	  * Minumum number of guarantee days
	  */
	public int getGuaranteeDaysMin();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name ImageURL */
    public static final String COLUMNNAME_ImageURL = "ImageURL";

	/** Set Image URL.
	  * URL of  image
	  */
	public void setImageURL (String ImageURL);

	/** Get Image URL.
	  * URL of  image
	  */
	public String getImageURL();

    /** Column name IsBOM */
    public static final String COLUMNNAME_IsBOM = "IsBOM";

	/** Set Bill of Materials.
	  * Bill of Materials
	  */
	public void setIsBOM (boolean IsBOM);

	/** Get Bill of Materials.
	  * Bill of Materials
	  */
	public boolean isBOM();

    /** Column name IsCoveredByInsurance */
    public static final String COLUMNNAME_IsCoveredByInsurance = "IsCoveredByInsurance";

	/** Set Is Covered By Insurance	  */
	public void setIsCoveredByInsurance (boolean IsCoveredByInsurance);

	/** Get Is Covered By Insurance	  */
	public boolean isCoveredByInsurance();

    /** Column name IsDropShip */
    public static final String COLUMNNAME_IsDropShip = "IsDropShip";

	/** Set Drop Shipment.
	  * Drop Shipments are sent from the Vendor directly to the Customer
	  */
	public void setIsDropShip (boolean IsDropShip);

	/** Get Drop Shipment.
	  * Drop Shipments are sent from the Vendor directly to the Customer
	  */
	public boolean isDropShip();

    /** Column name IsExcludeAutoDelivery */
    public static final String COLUMNNAME_IsExcludeAutoDelivery = "IsExcludeAutoDelivery";

	/** Set Exclude Auto Delivery.
	  * Do you need medical equipment?
	  */
	public void setIsExcludeAutoDelivery (boolean IsExcludeAutoDelivery);

	/** Get Exclude Auto Delivery.
	  * Do you need medical equipment?
	  */
	public boolean isExcludeAutoDelivery();

    /** Column name IsGI */
    public static final String COLUMNNAME_IsGI = "IsGI";

	/** Set Preferably generic name	  */
	public void setIsGI (boolean IsGI);

	/** Get Preferably generic name	  */
	public boolean isGI();

    /** Column name IsInvoicePrintDetails */
    public static final String COLUMNNAME_IsInvoicePrintDetails = "IsInvoicePrintDetails";

	/** Set Print detail records on invoice.
	  * Print detail BOM elements on the invoice
	  */
	public void setIsInvoicePrintDetails (boolean IsInvoicePrintDetails);

	/** Get Print detail records on invoice.
	  * Print detail BOM elements on the invoice
	  */
	public boolean isInvoicePrintDetails();

    /** Column name IsLot */
    public static final String COLUMNNAME_IsLot = "IsLot";

	/** Set Lot.
	  * The product instances have a Lot Number
	  */
	public void setIsLot (boolean IsLot);

	/** Get Lot.
	  * The product instances have a Lot Number
	  */
	public boolean isLot();

    /** Column name IsNumSerie */
    public static final String COLUMNNAME_IsNumSerie = "IsNumSerie";

	/** Set Serial number	  */
	public void setIsNumSerie (boolean IsNumSerie);

	/** Get Serial number	  */
	public boolean isNumSerie();

    /** Column name IsPhysicianCharge */
    public static final String COLUMNNAME_IsPhysicianCharge = "IsPhysicianCharge";

	/** Set IsPhysicianCharge	  */
	public void setIsPhysicianCharge (boolean IsPhysicianCharge);

	/** Get IsPhysicianCharge	  */
	public boolean isPhysicianCharge();

    /** Column name IsPickListPrintDetails */
    public static final String COLUMNNAME_IsPickListPrintDetails = "IsPickListPrintDetails";

	/** Set Print detail records on pick list.
	  * Print detail BOM elements on the pick list
	  */
	public void setIsPickListPrintDetails (boolean IsPickListPrintDetails);

	/** Get Print detail records on pick list.
	  * Print detail BOM elements on the pick list
	  */
	public boolean isPickListPrintDetails();

    /** Column name IsPurchased */
    public static final String COLUMNNAME_IsPurchased = "IsPurchased";

	/** Set Purchased.
	  * Organization purchases this product
	  */
	public void setIsPurchased (boolean IsPurchased);

	/** Get Purchased.
	  * Organization purchases this product
	  */
	public boolean isPurchased();

    /** Column name IsSangre */
    public static final String COLUMNNAME_IsSangre = "IsSangre";

	/** Set Is Blood	  */
	public void setIsSangre (boolean IsSangre);

	/** Get Is Blood	  */
	public boolean isSangre();

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

    /** Column name IsSold */
    public static final String COLUMNNAME_IsSold = "IsSold";

	/** Set Sold.
	  * Organization sells this product
	  */
	public void setIsSold (boolean IsSold);

	/** Get Sold.
	  * Organization sells this product
	  */
	public boolean isSold();

    /** Column name IsStocked */
    public static final String COLUMNNAME_IsStocked = "IsStocked";

	/** Set Stocked.
	  * Organization stocks this product
	  */
	public void setIsStocked (boolean IsStocked);

	/** Get Stocked.
	  * Organization stocks this product
	  */
	public boolean isStocked();

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

    /** Column name IsVacuna */
    public static final String COLUMNNAME_IsVacuna = "IsVacuna";

	/** Set Is Vaccine	  */
	public void setIsVacuna (boolean IsVacuna);

	/** Get Is Vaccine	  */
	public boolean isVacuna();

    /** Column name IsVerified */
    public static final String COLUMNNAME_IsVerified = "IsVerified";

	/** Set Verified.
	  * The BOM configuration has been verified
	  */
	public void setIsVerified (boolean IsVerified);

	/** Get Verified.
	  * The BOM configuration has been verified
	  */
	public boolean isVerified();

    /** Column name IsWebStoreFeatured */
    public static final String COLUMNNAME_IsWebStoreFeatured = "IsWebStoreFeatured";

	/** Set Featured in Web Store.
	  * If selected, the product is displayed in the inital or any empy search
	  */
	public void setIsWebStoreFeatured (boolean IsWebStoreFeatured);

	/** Get Featured in Web Store.
	  * If selected, the product is displayed in the inital or any empy search
	  */
	public boolean isWebStoreFeatured();

    /** Column name ItemClass */
    public static final String COLUMNNAME_ItemClass = "ItemClass";

	/** Set Item Class.
	  * Item Class
	  */
	public void setItemClass (boolean ItemClass);

	/** Get Item Class.
	  * Item Class
	  */
	public boolean isItemClass();

    /** Column name LabelerID */
    public static final String COLUMNNAME_LabelerID = "LabelerID";

	/** Set Labeler	  */
	public void setLabelerID (int LabelerID);

	/** Get Labeler	  */
	public int getLabelerID();

    /** Column name LowLevel */
    public static final String COLUMNNAME_LowLevel = "LowLevel";

	/** Set Low Level	  */
	public void setLowLevel (int LowLevel);

	/** Get Low Level	  */
	public int getLowLevel();

    /** Column name M_AttributeSet_ID */
    public static final String COLUMNNAME_M_AttributeSet_ID = "M_AttributeSet_ID";

	/** Set Attribute Set.
	  * Product Attribute Set
	  */
	public void setM_AttributeSet_ID (int M_AttributeSet_ID);

	/** Get Attribute Set.
	  * Product Attribute Set
	  */
	public int getM_AttributeSet_ID();

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

    /** Column name M_FreightCategory_ID */
    public static final String COLUMNNAME_M_FreightCategory_ID = "M_FreightCategory_ID";

	/** Set Freight Category.
	  * Category of the Freight
	  */
	public void setM_FreightCategory_ID (int M_FreightCategory_ID);

	/** Get Freight Category.
	  * Category of the Freight
	  */
	public int getM_FreightCategory_ID();

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

    /** Column name m_product_lexi_id */
    public static final String COLUMNNAME_m_product_lexi_id = "m_product_lexi_id";

	/** Set m_product_lexi_id	  */
	public void setm_product_lexi_id (int m_product_lexi_id);

	/** Get m_product_lexi_id	  */
	public int getm_product_lexi_id();

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

    /** Column name NDC */
    public static final String COLUMNNAME_NDC = "NDC";

	/** Set NDC.
	  * NDC Code
	  */
	public void setNDC (String NDC);

	/** Get NDC.
	  * NDC Code
	  */
	public String getNDC();

    /** Column name PMID */
    public static final String COLUMNNAME_PMID = "PMID";

	/** Set PMID	  */
	public void setPMID (String PMID);

	/** Get PMID	  */
	public String getPMID();

    /** Column name ProcedureType */
    public static final String COLUMNNAME_ProcedureType = "ProcedureType";

	/** Set Procedure Type.
	  * Procedure Type
	  */
	public void setProcedureType (String ProcedureType);

	/** Get Procedure Type.
	  * Procedure Type
	  */
	public String getProcedureType();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ProductClass */
    public static final String COLUMNNAME_ProductClass = "ProductClass";

	/** Set Product Class	  */
	public void setProductClass (String ProductClass);

	/** Get Product Class	  */
	public String getProductClass();

    /** Column name ProductType */
    public static final String COLUMNNAME_ProductType = "ProductType";

	/** Set Product Type.
	  * Type of product
	  */
	public void setProductType (boolean ProductType);

	/** Get Product Type.
	  * Type of product
	  */
	public boolean isProductType();

    /** Column name RequiereContraste */
    public static final String COLUMNNAME_RequiereContraste = "RequiereContraste";

	/** Set Requires Contrast.
	  * Requires contrast media
	  */
	public void setRequiereContraste (boolean RequiereContraste);

	/** Get Requires Contrast.
	  * Requires contrast media
	  */
	public boolean isRequiereContraste();

    /** Column name R_MailText_ID */
    public static final String COLUMNNAME_R_MailText_ID = "R_MailText_ID";

	/** Set Mail Template.
	  * Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID);

	/** Get Mail Template.
	  * Text templates for mailings
	  */
	public int getR_MailText_ID();

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

    /** Column name S_ExpenseType_ID */
    public static final String COLUMNNAME_S_ExpenseType_ID = "S_ExpenseType_ID";

	/** Set Expense Type.
	  * Expense report type
	  */
	public void setS_ExpenseType_ID (int S_ExpenseType_ID);

	/** Get Expense Type.
	  * Expense report type
	  */
	public int getS_ExpenseType_ID();

    /** Column name ShelfDepth */
    public static final String COLUMNNAME_ShelfDepth = "ShelfDepth";

	/** Set Shelf Depth.
	  * Shelf depth required
	  */
	public void setShelfDepth (int ShelfDepth);

	/** Get Shelf Depth.
	  * Shelf depth required
	  */
	public int getShelfDepth();

    /** Column name ShelfHeight */
    public static final String COLUMNNAME_ShelfHeight = "ShelfHeight";

	/** Set Shelf Height.
	  * Shelf height required
	  */
	public void setShelfHeight (int ShelfHeight);

	/** Get Shelf Height.
	  * Shelf height required
	  */
	public int getShelfHeight();

    /** Column name ShelfWidth */
    public static final String COLUMNNAME_ShelfWidth = "ShelfWidth";

	/** Set Shelf Width.
	  * Shelf width required
	  */
	public void setShelfWidth (int ShelfWidth);

	/** Get Shelf Width.
	  * Shelf width required
	  */
	public int getShelfWidth();

    /** Column name SKU */
    public static final String COLUMNNAME_SKU = "SKU";

	/** Set SKU.
	  * Stock Keeping Unit
	  */
	public void setSKU (String SKU);

	/** Get SKU.
	  * Stock Keeping Unit
	  */
	public String getSKU();

    /** Column name S_Resource_ID */
    public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

	/** Set Resource.
	  * Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID);

	/** Get Resource.
	  * Resource
	  */
	public int getS_Resource_ID();

    /** Column name Strength */
    public static final String COLUMNNAME_Strength = "Strength";

	/** Set Strength.
	  * Strength
	  */
	public void setStrength (String Strength);

	/** Get Strength.
	  * Strength
	  */
	public String getStrength();

    /** Column name Strengthunits */
    public static final String COLUMNNAME_Strengthunits = "Strengthunits";

	/** Set Strengthunits.
	  * Strengthunits 
	  */
	public void setStrengthunits (String Strengthunits);

	/** Get Strengthunits.
	  * Strengthunits 
	  */
	public String getStrengthunits();

    /** Column name Trade_Name_ID */
    public static final String COLUMNNAME_Trade_Name_ID = "Trade_Name_ID";

	/** Set Trade name/key	  */
	public void setTrade_Name_ID (int Trade_Name_ID);

	/** Get Trade name/key	  */
	public int getTrade_Name_ID();

    /** Column name UnitsPerPack */
    public static final String COLUMNNAME_UnitsPerPack = "UnitsPerPack";

	/** Set Units Per Pack.
	  * The Units Per Pack indicates the no of units of a product packed together.
	  */
	public void setUnitsPerPack (int UnitsPerPack);

	/** Get Units Per Pack.
	  * The Units Per Pack indicates the no of units of a product packed together.
	  */
	public int getUnitsPerPack();

    /** Column name UnitsPerPallet */
    public static final String COLUMNNAME_UnitsPerPallet = "UnitsPerPallet";

	/** Set Units Per Pallet.
	  * Units Per Pallet
	  */
	public void setUnitsPerPallet (int UnitsPerPallet);

	/** Get Units Per Pallet.
	  * Units Per Pallet
	  */
	public int getUnitsPerPallet();

    /** Column name UPC */
    public static final String COLUMNNAME_UPC = "UPC";

	/** Set UPC/EAN.
	  * Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public void setUPC (String UPC);

	/** Get UPC/EAN.
	  * Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public String getUPC();

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

    /** Column name VersionNo */
    public static final String COLUMNNAME_VersionNo = "VersionNo";

	/** Set Version No.
	  * Version Number
	  */
	public void setVersionNo (String VersionNo);

	/** Get Version No.
	  * Version Number
	  */
	public String getVersionNo();

    /** Column name Volume */
    public static final String COLUMNNAME_Volume = "Volume";

	/** Set Volume.
	  * Volume of a product
	  */
	public void setVolume (BigDecimal Volume);

	/** Get Volume.
	  * Volume of a product
	  */
	public BigDecimal getVolume();

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (BigDecimal Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public BigDecimal getWeight();
}
