/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_Product
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_I_Product 
{

    /** TableName=I_Product */
    public static final String Table_Name = "I_Product";

    /** AD_Table_ID=532 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name BPartner_Value */
    public static final String COLUMNNAME_BPartner_Value = "BPartner_Value";

	/** Set Company Key.
	  * The Key of the Company
	  */
	public void setBPartner_Value (String BPartner_Value);

	/** Get Company Key.
	  * The Key of the Company
	  */
	public String getBPartner_Value();

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

    /** Column name Classification */
    public static final String COLUMNNAME_Classification = "Classification";

	/** Set Classification.
	  * Classification for grouping
	  */
	public void setClassification (String Classification);

	/** Get Classification.
	  * Classification for grouping
	  */
	public String getClassification();

    /** Column name CostPerOrder */
    public static final String COLUMNNAME_CostPerOrder = "CostPerOrder";

	/** Set Cost per Order.
	  * Fixed Cost Per Order
	  */
	public void setCostPerOrder (BigDecimal CostPerOrder);

	/** Get Cost per Order.
	  * Fixed Cost Per Order
	  */
	public BigDecimal getCostPerOrder();

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

	public I_C_TaxCategory getC_TaxCategory() throws RuntimeException;

    /** Column name C_TaxCategory_Name */
    public static final String COLUMNNAME_C_TaxCategory_Name = "C_TaxCategory_Name";

	/** Set Name of Tax Category.
	  * Name of Tax Category
	  */
	public void setC_TaxCategory_Name (String C_TaxCategory_Name);

	/** Get Name of Tax Category.
	  * Name of Tax Category
	  */
	public String getC_TaxCategory_Name();

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

	public I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name C_UOM_Vendor_ID */
    public static final String COLUMNNAME_C_UOM_Vendor_ID = "C_UOM_Vendor_ID";

	/** Set Vendor UOM.
	  * Vendor Unit of Measure
	  */
	public void setC_UOM_Vendor_ID (int C_UOM_Vendor_ID);

	/** Get Vendor UOM.
	  * Vendor Unit of Measure
	  */
	public int getC_UOM_Vendor_ID();

    /** Column name C_UOMVolume_ID */
    public static final String COLUMNNAME_C_UOMVolume_ID = "C_UOMVolume_ID";

	/** Set UOM of Volume.
	  * Unit of measure of volume
	  */
	public void setC_UOMVolume_ID (int C_UOMVolume_ID);

	/** Get UOM of Volume.
	  * Unit of measure of volume
	  */
	public int getC_UOMVolume_ID();

    /** Column name C_UOMVolume_Value */
    public static final String COLUMNNAME_C_UOMVolume_Value = "C_UOMVolume_Value";

	/** Set UOM of Volume Value.
	  * UOM of Volume Search Key
	  */
	public void setC_UOMVolume_Value (String C_UOMVolume_Value);

	/** Get UOM of Volume Value.
	  * UOM of Volume Search Key
	  */
	public String getC_UOMVolume_Value();

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

    /** Column name C_UOMWeight_Value */
    public static final String COLUMNNAME_C_UOMWeight_Value = "C_UOMWeight_Value";

	/** Set UOM of Weight Value.
	  * UOM of Weight Search Key
	  */
	public void setC_UOMWeight_Value (String C_UOMWeight_Value);

	/** Get UOM of Weight Value.
	  * UOM of Weight Search Key
	  */
	public String getC_UOMWeight_Value();

    /** Column name DeliveryTime_Promised */
    public static final String COLUMNNAME_DeliveryTime_Promised = "DeliveryTime_Promised";

	/** Set Promised Delivery Time.
	  * Promised days between order and delivery
	  */
	public void setDeliveryTime_Promised (int DeliveryTime_Promised);

	/** Get Promised Delivery Time.
	  * Promised days between order and delivery
	  */
	public int getDeliveryTime_Promised();

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

    /** Column name DfID */
    public static final String COLUMNNAME_DfID = "DfID";

	/** Set DfID	  */
	public void setDfID (int DfID);

	/** Get DfID	  */
	public int getDfID();

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

    /** Column name EXME_ConceptoFac_ID */
    public static final String COLUMNNAME_EXME_ConceptoFac_ID = "EXME_ConceptoFac_ID";

	/** Set Invoice Concept	  */
	public void setEXME_ConceptoFac_ID (int EXME_ConceptoFac_ID);

	/** Get Invoice Concept	  */
	public int getEXME_ConceptoFac_ID();

    /** Column name EXME_ConceptoFac_Value */
    public static final String COLUMNNAME_EXME_ConceptoFac_Value = "EXME_ConceptoFac_Value";

	/** Set Invoice Concept's Value.
	  * Invoice Concept's Value
	  */
	public void setEXME_ConceptoFac_Value (String EXME_ConceptoFac_Value);

	/** Get Invoice Concept's Value.
	  * Invoice Concept's Value
	  */
	public String getEXME_ConceptoFac_Value();

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

    /** Column name EXME_FactorPre_Value */
    public static final String COLUMNNAME_EXME_FactorPre_Value = "EXME_FactorPre_Value";

	/** Set Price Factor Key.
	  * Price Factor Key
	  */
	public void setEXME_FactorPre_Value (String EXME_FactorPre_Value);

	/** Get Price Factor Key.
	  * Price Factor Key
	  */
	public String getEXME_FactorPre_Value();

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

	public I_EXME_ProductFam getEXME_ProductFam() throws RuntimeException;

    /** Column name EXME_ProductFam_Value */
    public static final String COLUMNNAME_EXME_ProductFam_Value = "EXME_ProductFam_Value";

	/** Set Family Products Code.
	  * Family products Code
	  */
	public void setEXME_ProductFam_Value (String EXME_ProductFam_Value);

	/** Get Family Products Code.
	  * Family products Code
	  */
	public String getEXME_ProductFam_Value();

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

    /** Column name EXME_TipoProd_Value */
    public static final String COLUMNNAME_EXME_TipoProd_Value = "EXME_TipoProd_Value";

	/** Set Subtype Product Code.
	  * Subtypy Product Code
	  */
	public void setEXME_TipoProd_Value (String EXME_TipoProd_Value);

	/** Get Subtype Product Code.
	  * Subtypy Product Code
	  */
	public String getEXME_TipoProd_Value();

    /** Column name GCNSEQNO */
    public static final String COLUMNNAME_GCNSEQNO = "GCNSEQNO";

	/** Set GCNSEQNO	  */
	public void setGCNSEQNO (int GCNSEQNO);

	/** Get GCNSEQNO	  */
	public int getGCNSEQNO();

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

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

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

    /** Column name I_Product_ID */
    public static final String COLUMNNAME_I_Product_ID = "I_Product_ID";

	/** Set Import Product.
	  * Import Item or Service
	  */
	public void setI_Product_ID (int I_Product_ID);

	/** Get Import Product.
	  * Import Item or Service
	  */
	public int getI_Product_ID();

    /** Column name ISO_Code */
    public static final String COLUMNNAME_ISO_Code = "ISO_Code";

	/** Set ISO Currency Code.
	  * Three letter ISO 4217 Code of the Currency
	  */
	public void setISO_Code (String ISO_Code);

	/** Get ISO Currency Code.
	  * Three letter ISO 4217 Code of the Currency
	  */
	public String getISO_Code();

    /** Column name LabelerID */
    public static final String COLUMNNAME_LabelerID = "LabelerID";

	/** Set Labeler	  */
	public void setLabelerID (String LabelerID);

	/** Get Labeler	  */
	public String getLabelerID();

    /** Column name Manufacturer */
    public static final String COLUMNNAME_Manufacturer = "Manufacturer";

	/** Set Manufacturer.
	  * Manufacturer of the Product
	  */
	public void setManufacturer (String Manufacturer);

	/** Get Manufacturer.
	  * Manufacturer of the Product
	  */
	public String getManufacturer();

    /** Column name MEDID */
    public static final String COLUMNNAME_MEDID = "MEDID";

	/** Set MEDID	  */
	public void setMEDID (int MEDID);

	/** Get MEDID	  */
	public int getMEDID();

    /** Column name MNID */
    public static final String COLUMNNAME_MNID = "MNID";

	/** Set MNID	  */
	public void setMNID (int MNID);

	/** Get MNID	  */
	public int getMNID();

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

	public I_M_Product_Category getM_Product_Category() throws RuntimeException;

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

    /** Column name Order_Min */
    public static final String COLUMNNAME_Order_Min = "Order_Min";

	/** Set Minimum Order Qty.
	  * Minimum order quantity in UOM
	  */
	public void setOrder_Min (int Order_Min);

	/** Get Minimum Order Qty.
	  * Minimum order quantity in UOM
	  */
	public int getOrder_Min();

    /** Column name Order_Pack */
    public static final String COLUMNNAME_Order_Pack = "Order_Pack";

	/** Set Order Pack Qty.
	  * Package order size in UOM (e.g. order set of 5 units)
	  */
	public void setOrder_Pack (int Order_Pack);

	/** Get Order Pack Qty.
	  * Package order size in UOM (e.g. order set of 5 units)
	  */
	public int getOrder_Pack();

    /** Column name PMID */
    public static final String COLUMNNAME_PMID = "PMID";

	/** Set PMID	  */
	public void setPMID (String PMID);

	/** Get PMID	  */
	public String getPMID();

    /** Column name PrecioVenta */
    public static final String COLUMNNAME_PrecioVenta = "PrecioVenta";

	/** Set Sell Price.
	  * Product Sell Price
	  */
	public void setPrecioVenta (BigDecimal PrecioVenta);

	/** Get Sell Price.
	  * Product Sell Price
	  */
	public BigDecimal getPrecioVenta();

    /** Column name PriceEffective */
    public static final String COLUMNNAME_PriceEffective = "PriceEffective";

	/** Set Price effective.
	  * Effective Date of Price
	  */
	public void setPriceEffective (Timestamp PriceEffective);

	/** Get Price effective.
	  * Effective Date of Price
	  */
	public Timestamp getPriceEffective();

    /** Column name PriceLastPO */
    public static final String COLUMNNAME_PriceLastPO = "PriceLastPO";

	/** Set Last PO Price.
	  * Price of the last purchase order for the product
	  */
	public void setPriceLastPO (BigDecimal PriceLastPO);

	/** Get Last PO Price.
	  * Price of the last purchase order for the product
	  */
	public BigDecimal getPriceLastPO();

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

    /** Column name PricePO */
    public static final String COLUMNNAME_PricePO = "PricePO";

	/** Set PO Price.
	  * Price based on a purchase order
	  */
	public void setPricePO (BigDecimal PricePO);

	/** Get PO Price.
	  * Price based on a purchase order
	  */
	public BigDecimal getPricePO();

    /** Column name PriceStd */
    public static final String COLUMNNAME_PriceStd = "PriceStd";

	/** Set Standard Price.
	  * Standard Price
	  */
	public void setPriceStd (BigDecimal PriceStd);

	/** Get Standard Price.
	  * Standard Price
	  */
	public BigDecimal getPriceStd();

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

    /** Column name ProductCategory */
    public static final String COLUMNNAME_ProductCategory = "ProductCategory";

	/** Set Product Category	  */
	public void setProductCategory (String ProductCategory);

	/** Get Product Category	  */
	public String getProductCategory();

    /** Column name ProductCategory_Value */
    public static final String COLUMNNAME_ProductCategory_Value = "ProductCategory_Value";

	/** Set Product Category Key	  */
	public void setProductCategory_Value (String ProductCategory_Value);

	/** Get Product Category Key	  */
	public String getProductCategory_Value();

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
	public void setProductType (String ProductType);

	/** Get Product Type.
	  * Type of product
	  */
	public String getProductType();

    /** Column name RoyaltyAmt */
    public static final String COLUMNNAME_RoyaltyAmt = "RoyaltyAmt";

	/** Set Royalty Amount.
	  * (Included) Amount for copyright, etc.
	  */
	public void setRoyaltyAmt (BigDecimal RoyaltyAmt);

	/** Get Royalty Amount.
	  * (Included) Amount for copyright, etc.
	  */
	public BigDecimal getRoyaltyAmt();

    /** Column name RtID */
    public static final String COLUMNNAME_RtID = "RtID";

	/** Set RtID	  */
	public void setRtID (int RtID);

	/** Get RtID	  */
	public int getRtID();

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

    /** Column name VendorCategory */
    public static final String COLUMNNAME_VendorCategory = "VendorCategory";

	/** Set Partner Category.
	  * Product Category of the Business Partner
	  */
	public void setVendorCategory (String VendorCategory);

	/** Get Partner Category.
	  * Product Category of the Business Partner
	  */
	public String getVendorCategory();

    /** Column name VendorProductNo */
    public static final String COLUMNNAME_VendorProductNo = "VendorProductNo";

	/** Set Partner Product Key.
	  * Product Key of the Business Partner
	  */
	public void setVendorProductNo (String VendorProductNo);

	/** Get Partner Product Key.
	  * Product Key of the Business Partner
	  */
	public String getVendorProductNo();

    /** Column name Volume */
    public static final String COLUMNNAME_Volume = "Volume";

	/** Set Volume.
	  * Volume of a product
	  */
	public void setVolume (int Volume);

	/** Get Volume.
	  * Volume of a product
	  */
	public int getVolume();

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (int Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public int getWeight();

    /** Column name X12DE355 */
    public static final String COLUMNNAME_X12DE355 = "X12DE355";

	/** Set UOM Code.
	  * UOM EDI X12 Code
	  */
	public void setX12DE355 (String X12DE355);

	/** Get UOM Code.
	  * UOM EDI X12 Code
	  */
	public String getX12DE355();

    /** Column name X12DE355Vendor */
    public static final String COLUMNNAME_X12DE355Vendor = "X12DE355Vendor";

	/** Set Supplier EDI code.
	  * Supplier EDI of UdM code
	  */
	public void setX12DE355Vendor (String X12DE355Vendor);

	/** Get Supplier EDI code.
	  * Supplier EDI of UdM code
	  */
	public String getX12DE355Vendor();
}
