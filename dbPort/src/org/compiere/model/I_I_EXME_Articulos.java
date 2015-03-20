/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_EXME_Articulos
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_I_EXME_Articulos 
{

    /** TableName=I_EXME_Articulos */
    public static final String Table_Name = "I_EXME_Articulos";

    /** AD_Table_ID=1200231 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name Activo */
    public static final String COLUMNNAME_Activo = "Activo";

	/** Set Active	  */
	public void setActivo (boolean Activo);

	/** Get Active	  */
	public boolean isActivo();

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

    /** Column name Arancel */
    public static final String COLUMNNAME_Arancel = "Arancel";

	/** Set Duty.
	  * Define the tax on imports or exports of goods
	  */
	public void setArancel (String Arancel);

	/** Get Duty.
	  * Define the tax on imports or exports of goods
	  */
	public String getArancel();

    /** Column name AwaitedLife */
    public static final String COLUMNNAME_AwaitedLife = "AwaitedLife";

	/** Set AwaitedLife	  */
	public void setAwaitedLife (String AwaitedLife);

	/** Get AwaitedLife	  */
	public String getAwaitedLife();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Value */
    public static final String COLUMNNAME_C_BPartner_Value = "C_BPartner_Value";

	/** Set Business Partner Value	  */
	public void setC_BPartner_Value (String C_BPartner_Value);

	/** Get Business Partner Value	  */
	public String getC_BPartner_Value();

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

    /** Column name C_Currency_Value */
    public static final String COLUMNNAME_C_Currency_Value = "C_Currency_Value";

	/** Set C_Currency_Value	  */
	public void setC_Currency_Value (String C_Currency_Value);

	/** Get C_Currency_Value	  */
	public String getC_Currency_Value();

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

    /** Column name Code_Bol */
    public static final String COLUMNNAME_Code_Bol = "Code_Bol";

	/** Set Code_Bol	  */
	public void setCode_Bol (String Code_Bol);

	/** Get Code_Bol	  */
	public String getCode_Bol();

    /** Column name Comprador */
    public static final String COLUMNNAME_Comprador = "Comprador";

	/** Set Buyer	  */
	public void setComprador (String Comprador);

	/** Get Buyer	  */
	public String getComprador();

    /** Column name CostingMethod */
    public static final String COLUMNNAME_CostingMethod = "CostingMethod";

	/** Set Costing Method.
	  * Indicates how Costs will be calculated
	  */
	public void setCostingMethod (boolean CostingMethod);

	/** Get Costing Method.
	  * Indicates how Costs will be calculated
	  */
	public boolean isCostingMethod();

    /** Column name CountryCode */
    public static final String COLUMNNAME_CountryCode = "CountryCode";

	/** Set ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public void setCountryCode (String CountryCode);

	/** Get ISO Country Code.
	  * Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html
	  */
	public String getCountryCode();

    /** Column name CreateDate */
    public static final String COLUMNNAME_CreateDate = "CreateDate";

	/** Set CreateDate	  */
	public void setCreateDate (Timestamp CreateDate);

	/** Get CreateDate	  */
	public Timestamp getCreateDate();

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

    /** Column name C_TaxCategory_Value */
    public static final String COLUMNNAME_C_TaxCategory_Value = "C_TaxCategory_Value";

	/** Set C_TaxCategory_Value	  */
	public void setC_TaxCategory_Value (String C_TaxCategory_Value);

	/** Get C_TaxCategory_Value	  */
	public String getC_TaxCategory_Value();

    /** Column name Cuestionario */
    public static final String COLUMNNAME_Cuestionario = "Cuestionario";

	/** Set Questionnaire.
	  * Questionnaire
	  */
	public void setCuestionario (String Cuestionario);

	/** Get Questionnaire.
	  * Questionnaire
	  */
	public String getCuestionario();

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

    /** Column name C_UOM_Value */
    public static final String COLUMNNAME_C_UOM_Value = "C_UOM_Value";

	/** Set C_UOM_Value	  */
	public void setC_UOM_Value (String C_UOM_Value);

	/** Get C_UOM_Value	  */
	public String getC_UOM_Value();

    /** Column name Desc_Pago */
    public static final String COLUMNNAME_Desc_Pago = "Desc_Pago";

	/** Set Payment Description	  */
	public void setDesc_Pago (String Desc_Pago);

	/** Get Payment Description	  */
	public String getDesc_Pago();

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

    /** Column name EXME_ConceptoFac_ID */
    public static final String COLUMNNAME_EXME_ConceptoFac_ID = "EXME_ConceptoFac_ID";

	/** Set Invoice Concept	  */
	public void setEXME_ConceptoFac_ID (int EXME_ConceptoFac_ID);

	/** Get Invoice Concept	  */
	public int getEXME_ConceptoFac_ID();

	public I_EXME_ConceptoFac getEXME_ConceptoFac() throws RuntimeException;

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

	public I_EXME_FactorPre getEXME_FactorPre() throws RuntimeException;

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

	public I_EXME_TipoProd getEXME_TipoProd() throws RuntimeException;

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

    /** Column name GeneraExpClinico */
    public static final String COLUMNNAME_GeneraExpClinico = "GeneraExpClinico";

	/** Set GeneraExpClinico	  */
	public void setGeneraExpClinico (String GeneraExpClinico);

	/** Get GeneraExpClinico	  */
	public String getGeneraExpClinico();

    /** Column name Generico */
    public static final String COLUMNNAME_Generico = "Generico";

	/** Set Generico	  */
	public void setGenerico (String Generico);

	/** Get Generico	  */
	public String getGenerico();

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

    /** Column name I_EXME_Articulos_ID */
    public static final String COLUMNNAME_I_EXME_Articulos_ID = "I_EXME_Articulos_ID";

	/** Set I_EXME_Articulos_ID	  */
	public void setI_EXME_Articulos_ID (int I_EXME_Articulos_ID);

	/** Get I_EXME_Articulos_ID	  */
	public int getI_EXME_Articulos_ID();

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

    /** Column name Inspeccion */
    public static final String COLUMNNAME_Inspeccion = "Inspeccion";

	/** Set Inspeccion	  */
	public void setInspeccion (String Inspeccion);

	/** Get Inspeccion	  */
	public String getInspeccion();

    /** Column name MedidaVenta */
    public static final String COLUMNNAME_MedidaVenta = "MedidaVenta";

	/** Set MedidaVenta	  */
	public void setMedidaVenta (String MedidaVenta);

	/** Get MedidaVenta	  */
	public String getMedidaVenta();

    /** Column name MLI */
    public static final String COLUMNNAME_MLI = "MLI";

	/** Set MLI	  */
	public void setMLI (String MLI);

	/** Get MLI	  */
	public String getMLI();

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

    /** Column name M_Product_Value */
    public static final String COLUMNNAME_M_Product_Value = "M_Product_Value";

	/** Set Product Code.
	  * product search Code
	  */
	public void setM_Product_Value (String M_Product_Value);

	/** Get Product Code.
	  * product search Code
	  */
	public String getM_Product_Value();

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

    /** Column name NoCargosVentas */
    public static final String COLUMNNAME_NoCargosVentas = "NoCargosVentas";

	/** Set Number of Sales Charges	  */
	public void setNoCargosVentas (String NoCargosVentas);

	/** Get Number of Sales Charges	  */
	public String getNoCargosVentas();

    /** Column name Order_Pack */
    public static final String COLUMNNAME_Order_Pack = "Order_Pack";

	/** Set Order Pack Qty.
	  * Package order size in UOM (e.g. order set of 5 units)
	  */
	public void setOrder_Pack (BigDecimal Order_Pack);

	/** Get Order Pack Qty.
	  * Package order size in UOM (e.g. order set of 5 units)
	  */
	public BigDecimal getOrder_Pack();

    /** Column name OrderPolicy */
    public static final String COLUMNNAME_OrderPolicy = "OrderPolicy";

	/** Set Order Policy	  */
	public void setOrderPolicy (String OrderPolicy);

	/** Get Order Policy	  */
	public String getOrderPolicy();

    /** Column name PermitBackOrder */
    public static final String COLUMNNAME_PermitBackOrder = "PermitBackOrder";

	/** Set Permit Back Order	  */
	public void setPermitBackOrder (String PermitBackOrder);

	/** Get Permit Back Order	  */
	public String getPermitBackOrder();

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

    /** Column name Unidad_Operativa */
    public static final String COLUMNNAME_Unidad_Operativa = "Unidad_Operativa";

	/** Set Operational Unit	  */
	public void setUnidad_Operativa (String Unidad_Operativa);

	/** Get Operational Unit	  */
	public String getUnidad_Operativa();

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

    /** Column name UpdateDart */
    public static final String COLUMNNAME_UpdateDart = "UpdateDart";

	/** Set UpdateDart	  */
	public void setUpdateDart (Timestamp UpdateDart);

	/** Get UpdateDart	  */
	public Timestamp getUpdateDart();

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

    /** Column name Value_B */
    public static final String COLUMNNAME_Value_B = "Value_B";

	/** Set Value B	  */
	public void setValue_B (String Value_B);

	/** Get Value B	  */
	public String getValue_B();

    /** Column name Value_C */
    public static final String COLUMNNAME_Value_C = "Value_C";

	/** Set Value C	  */
	public void setValue_C (String Value_C);

	/** Get Value C	  */
	public String getValue_C();

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

    /** Column name X12DE355B */
    public static final String COLUMNNAME_X12DE355B = "X12DE355B";

	/** Set X12DE355B	  */
	public void setX12DE355B (String X12DE355B);

	/** Get X12DE355B	  */
	public String getX12DE355B();
}
