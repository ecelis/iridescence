/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_ProductoOrg
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_I_EXME_ProductoOrg 
{

    /** TableName=I_EXME_ProductoOrg */
    public static final String Table_Name = "I_EXME_ProductoOrg";

    /** AD_Table_ID=1201286 */
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

    /** Column name Charge */
    public static final String COLUMNNAME_Charge = "Charge";

	/** Set Charge	  */
	public void setCharge (String Charge);

	/** Get Charge	  */
	public String getCharge();

    /** Column name Cost */
    public static final String COLUMNNAME_Cost = "Cost";

	/** Set Cost.
	  * Cost information
	  */
	public void setCost (BigDecimal Cost);

	/** Get Cost.
	  * Cost information
	  */
	public BigDecimal getCost();

    /** Column name CPT */
    public static final String COLUMNNAME_CPT = "CPT";

	/** Set CPT.
	  * CPT Code
	  */
	public void setCPT (String CPT);

	/** Get CPT.
	  * CPT Code
	  */
	public String getCPT();

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

    /** Column name Effective_Date */
    public static final String COLUMNNAME_Effective_Date = "Effective_Date";

	/** Set Effective Date	  */
	public void setEffective_Date (Timestamp Effective_Date);

	/** Get Effective Date	  */
	public Timestamp getEffective_Date();

    /** Column name EXME_Modifier1_ID */
    public static final String COLUMNNAME_EXME_Modifier1_ID = "EXME_Modifier1_ID";

	/** Set Modifier 1	  */
	public void setEXME_Modifier1_ID (int EXME_Modifier1_ID);

	/** Get Modifier 1	  */
	public int getEXME_Modifier1_ID();

    /** Column name EXME_Modifier2_ID */
    public static final String COLUMNNAME_EXME_Modifier2_ID = "EXME_Modifier2_ID";

	/** Set Modifier 2	  */
	public void setEXME_Modifier2_ID (int EXME_Modifier2_ID);

	/** Get Modifier 2	  */
	public int getEXME_Modifier2_ID();

    /** Column name EXME_Modifier3_ID */
    public static final String COLUMNNAME_EXME_Modifier3_ID = "EXME_Modifier3_ID";

	/** Set Modifier 3	  */
	public void setEXME_Modifier3_ID (int EXME_Modifier3_ID);

	/** Get Modifier 3	  */
	public int getEXME_Modifier3_ID();

    /** Column name EXME_Modifier4_ID */
    public static final String COLUMNNAME_EXME_Modifier4_ID = "EXME_Modifier4_ID";

	/** Set Modifier 4	  */
	public void setEXME_Modifier4_ID (int EXME_Modifier4_ID);

	/** Get Modifier 4	  */
	public int getEXME_Modifier4_ID();

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

    /** Column name EXME_ProductoOrg_ID */
    public static final String COLUMNNAME_EXME_ProductoOrg_ID = "EXME_ProductoOrg_ID";

	/** Set Producto Org	  */
	public void setEXME_ProductoOrg_ID (int EXME_ProductoOrg_ID);

	/** Get Producto Org	  */
	public int getEXME_ProductoOrg_ID();

	public I_EXME_ProductoOrg getEXME_ProductoOrg() throws RuntimeException;

    /** Column name EXME_RevenueCode_ID */
    public static final String COLUMNNAME_EXME_RevenueCode_ID = "EXME_RevenueCode_ID";

	/** Set Revenue Code	  */
	public void setEXME_RevenueCode_ID (int EXME_RevenueCode_ID);

	/** Get Revenue Code	  */
	public int getEXME_RevenueCode_ID();

	public I_EXME_RevenueCode getEXME_RevenueCode() throws RuntimeException;

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

    /** Column name HCPCS */
    public static final String COLUMNNAME_HCPCS = "HCPCS";

	/** Set HCPCS.
	  * HCPCS Code
	  */
	public void setHCPCS (String HCPCS);

	/** Get HCPCS.
	  * HCPCS Code
	  */
	public String getHCPCS();

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

    /** Column name I_EXME_ProductoOrg_ID */
    public static final String COLUMNNAME_I_EXME_ProductoOrg_ID = "I_EXME_ProductoOrg_ID";

	/** Set Charge Master Import Interface	  */
	public void setI_EXME_ProductoOrg_ID (int I_EXME_ProductoOrg_ID);

	/** Get Charge Master Import Interface	  */
	public int getI_EXME_ProductoOrg_ID();

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

    /** Column name IsCoveredByInsurance */
    public static final String COLUMNNAME_IsCoveredByInsurance = "IsCoveredByInsurance";

	/** Set Is Covered By Insurance	  */
	public void setIsCoveredByInsurance (boolean IsCoveredByInsurance);

	/** Get Is Covered By Insurance	  */
	public boolean isCoveredByInsurance();

    /** Column name IsProfessional */
    public static final String COLUMNNAME_IsProfessional = "IsProfessional";

	/** Set Is Professional?	  */
	public void setIsProfessional (boolean IsProfessional);

	/** Get Is Professional?	  */
	public boolean isProfessional();

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

    /** Column name Modifier_1 */
    public static final String COLUMNNAME_Modifier_1 = "Modifier_1";

	/** Set Modifier 1	  */
	public void setModifier_1 (String Modifier_1);

	/** Get Modifier 1	  */
	public String getModifier_1();

    /** Column name Modifier_2 */
    public static final String COLUMNNAME_Modifier_2 = "Modifier_2";

	/** Set Modifier 2	  */
	public void setModifier_2 (String Modifier_2);

	/** Get Modifier 2	  */
	public String getModifier_2();

    /** Column name Modifier_3 */
    public static final String COLUMNNAME_Modifier_3 = "Modifier_3";

	/** Set Modifier 3	  */
	public void setModifier_3 (String Modifier_3);

	/** Get Modifier 3	  */
	public String getModifier_3();

    /** Column name Modifier_4 */
    public static final String COLUMNNAME_Modifier_4 = "Modifier_4";

	/** Set Modifier 4	  */
	public void setModifier_4 (String Modifier_4);

	/** Get Modifier 4	  */
	public String getModifier_4();

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

    /** Column name M_PriceList_Name */
    public static final String COLUMNNAME_M_PriceList_Name = "M_PriceList_Name";

	/** Set Name Price List.
	  * Name Price List
	  */
	public void setM_PriceList_Name (String M_PriceList_Name);

	/** Get Name Price List.
	  * Name Price List
	  */
	public String getM_PriceList_Name();

    /** Column name M_PriceList_Version_ID */
    public static final String COLUMNNAME_M_PriceList_Version_ID = "M_PriceList_Version_ID";

	/** Set Price List Version.
	  * Identifies a unique instance of a Price List
	  */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID);

	/** Get Price List Version.
	  * Identifies a unique instance of a Price List
	  */
	public int getM_PriceList_Version_ID();

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

    /** Column name M_Product_Category_Value */
    public static final String COLUMNNAME_M_Product_Category_Value = "M_Product_Category_Value";

	/** Set Product Category	  */
	public void setM_Product_Category_Value (String M_Product_Category_Value);

	/** Get Product Category	  */
	public String getM_Product_Category_Value();

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

    /** Column name Other */
    public static final String COLUMNNAME_Other = "Other";

	/** Set Other Code	  */
	public void setOther (String Other);

	/** Get Other Code	  */
	public String getOther();

    /** Column name Price */
    public static final String COLUMNNAME_Price = "Price";

	/** Set Price.
	  * Price
	  */
	public void setPrice (BigDecimal Price);

	/** Get Price.
	  * Price
	  */
	public BigDecimal getPrice();

    /** Column name PriceList_Vol */
    public static final String COLUMNNAME_PriceList_Vol = "PriceList_Vol";

	/** Set Pack Price List.
	  * Price List for the Pack UOM
	  */
	public void setPriceList_Vol (int PriceList_Vol);

	/** Get Pack Price List.
	  * Price List for the Pack UOM
	  */
	public int getPriceList_Vol();

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

    /** Column name Rev_Code */
    public static final String COLUMNNAME_Rev_Code = "Rev_Code";

	/** Set Revenue Code	  */
	public void setRev_Code (String Rev_Code);

	/** Get Revenue Code	  */
	public String getRev_Code();

    /** Column name UOM_Value */
    public static final String COLUMNNAME_UOM_Value = "UOM_Value";

	/** Set Uom Key	  */
	public void setUOM_Value (String UOM_Value);

	/** Get Uom Key	  */
	public String getUOM_Value();

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
}
