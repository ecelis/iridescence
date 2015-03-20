/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_TTCtaPacDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_TTCtaPacDet 
{

    /** TableName=EXME_TTCtaPacDet */
    public static final String Table_Name = "EXME_TTCtaPacDet";

    /** AD_Table_ID=1200001 */
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

    /** Column name AD_Session_ID */
    public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";

	/** Set Session.
	  * User Session Online or Web
	  */
	public void setAD_Session_ID (int AD_Session_ID);

	/** Get Session.
	  * User Session Online or Web
	  */
	public int getAD_Session_ID();

	public I_AD_Session getAD_Session() throws RuntimeException;

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name BorrarConv */
    public static final String COLUMNNAME_BorrarConv = "BorrarConv";

	/** Set Erase agreement	  */
	public void setBorrarConv (boolean BorrarConv);

	/** Get Erase agreement	  */
	public boolean isBorrarConv();

    /** Column name CalcularPrecio */
    public static final String COLUMNNAME_CalcularPrecio = "CalcularPrecio";

	/** Set Calculate Price	  */
	public void setCalcularPrecio (boolean CalcularPrecio);

	/** Get Calculate Price	  */
	public boolean isCalcularPrecio();

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

    /** Column name Convenio_ID */
    public static final String COLUMNNAME_Convenio_ID = "Convenio_ID";

	/** Set Agreement	  */
	public void setConvenio_ID (int Convenio_ID);

	/** Get Agreement	  */
	public int getConvenio_ID();

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

	public I_C_Tax getC_Tax() throws RuntimeException;

    /** Column name C_TaxPublico_ID */
    public static final String COLUMNNAME_C_TaxPublico_ID = "C_TaxPublico_ID";

	/** Set Public Tax ID	  */
	public void setC_TaxPublico_ID (int C_TaxPublico_ID);

	/** Get Public Tax ID	  */
	public int getC_TaxPublico_ID();

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

    /** Column name DiscountFam */
    public static final String COLUMNNAME_DiscountFam = "DiscountFam";

	/** Set DiscountFam.
	  * DiscountFam
	  */
	public void setDiscountFam (BigDecimal DiscountFam);

	/** Get DiscountFam.
	  * DiscountFam
	  */
	public BigDecimal getDiscountFam();

    /** Column name EXME_Area_ID */
    public static final String COLUMNNAME_EXME_Area_ID = "EXME_Area_ID";

	/** Set Area.
	  * Area
	  */
	public void setEXME_Area_ID (int EXME_Area_ID);

	/** Get Area.
	  * Area
	  */
	public int getEXME_Area_ID();

	public I_EXME_Area getEXME_Area() throws RuntimeException;

    /** Column name EXME_CDiarioDet_ID */
    public static final String COLUMNNAME_EXME_CDiarioDet_ID = "EXME_CDiarioDet_ID";

	/** Set Detail Daily Charge.
	  * Detail Daily Charge
	  */
	public void setEXME_CDiarioDet_ID (int EXME_CDiarioDet_ID);

	/** Get Detail Daily Charge.
	  * Detail Daily Charge
	  */
	public int getEXME_CDiarioDet_ID();

	public I_EXME_CDiarioDet getEXME_CDiarioDet() throws RuntimeException;

    /** Column name EXME_CtaPacDet_ID */
    public static final String COLUMNNAME_EXME_CtaPacDet_ID = "EXME_CtaPacDet_ID";

	/** Set Patient Account Detail.
	  * Patient Account Detail
	  */
	public void setEXME_CtaPacDet_ID (int EXME_CtaPacDet_ID);

	/** Get Patient Account Detail.
	  * Patient Account Detail
	  */
	public int getEXME_CtaPacDet_ID();

	public I_EXME_CtaPacDet getEXME_CtaPacDet() throws RuntimeException;

    /** Column name EXME_CtaPacExt_ID */
    public static final String COLUMNNAME_EXME_CtaPacExt_ID = "EXME_CtaPacExt_ID";

	/** Set Patient Account Extension.
	  * Patient Account Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID);

	/** Get Patient Account Extension.
	  * Patient Account Extension
	  */
	public int getEXME_CtaPacExt_ID();

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException;

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Patient Account.
	  * Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Patient Account.
	  * Patient Account
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

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

    /** Column name EXME_PaqBase_Version_ID */
    public static final String COLUMNNAME_EXME_PaqBase_Version_ID = "EXME_PaqBase_Version_ID";

	/** Set Package Version.
	  * Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID);

	/** Get Package Version.
	  * Package Version
	  */
	public int getEXME_PaqBase_Version_ID();

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException;

    /** Column name EXME_PlanMedLine_ID */
    public static final String COLUMNNAME_EXME_PlanMedLine_ID = "EXME_PlanMedLine_ID";

	/** Set Scheduled Doctor.
	  * Scheduled Doctor
	  */
	public void setEXME_PlanMedLine_ID (int EXME_PlanMedLine_ID);

	/** Get Scheduled Doctor.
	  * Scheduled Doctor
	  */
	public int getEXME_PlanMedLine_ID();

	public I_EXME_PlanMedLine getEXME_PlanMedLine() throws RuntimeException;

    /** Column name EXME_TTCtaPacDet_ID */
    public static final String COLUMNNAME_EXME_TTCtaPacDet_ID = "EXME_TTCtaPacDet_ID";

	/** Set Patient Account Detail	  */
	public void setEXME_TTCtaPacDet_ID (int EXME_TTCtaPacDet_ID);

	/** Get Patient Account Detail	  */
	public int getEXME_TTCtaPacDet_ID();

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

    /** Column name Invoice_UOM_ID */
    public static final String COLUMNNAME_Invoice_UOM_ID = "Invoice_UOM_ID";

	/** Set Invoice Measuring Unit.
	  * Invoice Measuring Unit
	  */
	public void setInvoice_UOM_ID (int Invoice_UOM_ID);

	/** Get Invoice Measuring Unit.
	  * Invoice Measuring Unit
	  */
	public int getInvoice_UOM_ID();

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

    /** Column name IsFacturado */
    public static final String COLUMNNAME_IsFacturado = "IsFacturado";

	/** Set Invoiced.
	  * Invoiced
	  */
	public void setIsFacturado (boolean IsFacturado);

	/** Get Invoiced.
	  * Invoiced
	  */
	public boolean isFacturado();

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

    /** Column name M_MovementLine_ID */
    public static final String COLUMNNAME_M_MovementLine_ID = "M_MovementLine_ID";

	/** Set Move Line.
	  * Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID);

	/** Get Move Line.
	  * Inventory Move document Line
	  */
	public int getM_MovementLine_ID();

	public I_M_MovementLine getM_MovementLine() throws RuntimeException;

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

	public I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name NoLine */
    public static final String COLUMNNAME_NoLine = "NoLine";

	/** Set Line Number	  */
	public void setNoLine (int NoLine);

	/** Get Line Number	  */
	public int getNoLine();

    /** Column name PrecioPublico */
    public static final String COLUMNNAME_PrecioPublico = "PrecioPublico";

	/** Set Public Price	  */
	public void setPrecioPublico (BigDecimal PrecioPublico);

	/** Get Public Price	  */
	public BigDecimal getPrecioPublico();

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

    /** Column name PriceActualInv */
    public static final String COLUMNNAME_PriceActualInv = "PriceActualInv";

	/** Set Invoiced Actual Price	  */
	public void setPriceActualInv (BigDecimal PriceActualInv);

	/** Get Invoiced Actual Price	  */
	public BigDecimal getPriceActualInv();

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

    /** Column name PriceLimitInv */
    public static final String COLUMNNAME_PriceLimitInv = "PriceLimitInv";

	/** Set Invoiced Limit Price	  */
	public void setPriceLimitInv (BigDecimal PriceLimitInv);

	/** Get Invoiced Limit Price	  */
	public BigDecimal getPriceLimitInv();

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

    /** Column name PriceListInv */
    public static final String COLUMNNAME_PriceListInv = "PriceListInv";

	/** Set Invoiced List Price	  */
	public void setPriceListInv (BigDecimal PriceListInv);

	/** Get Invoiced List Price	  */
	public BigDecimal getPriceListInv();

    /** Column name ProductCategory */
    public static final String COLUMNNAME_ProductCategory = "ProductCategory";

	/** Set Product Category	  */
	public void setProductCategory (String ProductCategory);

	/** Get Product Category	  */
	public String getProductCategory();

    /** Column name ProductDescription */
    public static final String COLUMNNAME_ProductDescription = "ProductDescription";

	/** Set Product Description.
	  * Product Description
	  */
	public void setProductDescription (String ProductDescription);

	/** Get Product Description.
	  * Product Description
	  */
	public String getProductDescription();

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

    /** Column name QtyPaquete */
    public static final String COLUMNNAME_QtyPaquete = "QtyPaquete";

	/** Set Package Quantity.
	  * Package Quantity
	  */
	public void setQtyPaquete (BigDecimal QtyPaquete);

	/** Get Package Quantity.
	  * Package Quantity
	  */
	public BigDecimal getQtyPaquete();

    /** Column name QtyPendiente */
    public static final String COLUMNNAME_QtyPendiente = "QtyPendiente";

	/** Set Pending Quantity.
	  * Pending Quantity
	  */
	public void setQtyPendiente (BigDecimal QtyPendiente);

	/** Get Pending Quantity.
	  * Pending Quantity
	  */
	public BigDecimal getQtyPendiente();

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

    /** Column name Ref_CtaPacDet_ID */
    public static final String COLUMNNAME_Ref_CtaPacDet_ID = "Ref_CtaPacDet_ID";

	/** Set Reference to Patient Account detail.
	  * Reference to Patient Account Detail
	  */
	public void setRef_CtaPacDet_ID (int Ref_CtaPacDet_ID);

	/** Get Reference to Patient Account detail.
	  * Reference to Patient Account Detail
	  */
	public int getRef_CtaPacDet_ID();

    /** Column name Ref_TTCtaPacDet_ID */
    public static final String COLUMNNAME_Ref_TTCtaPacDet_ID = "Ref_TTCtaPacDet_ID";

	/** Set Patient Account Detail	  */
	public void setRef_TTCtaPacDet_ID (int Ref_TTCtaPacDet_ID);

	/** Get Patient Account Detail	  */
	public int getRef_TTCtaPacDet_ID();

    /** Column name Secuencia */
    public static final String COLUMNNAME_Secuencia = "Secuencia";

	/** Set Sequence.
	  * Sequence
	  */
	public void setSecuencia (int Secuencia);

	/** Get Sequence.
	  * Sequence
	  */
	public int getSecuencia();

    /** Column name Serie */
    public static final String COLUMNNAME_Serie = "Serie";

	/** Set Serie	  */
	public void setSerie (int Serie);

	/** Get Serie	  */
	public int getSerie();

    /** Column name S_ResourceAssignment_ID */
    public static final String COLUMNNAME_S_ResourceAssignment_ID = "S_ResourceAssignment_ID";

	/** Set Resource Assignment.
	  * Resource Assignment
	  */
	public void setS_ResourceAssignment_ID (int S_ResourceAssignment_ID);

	/** Get Resource Assignment.
	  * Resource Assignment
	  */
	public int getS_ResourceAssignment_ID();

	public I_S_ResourceAssignment getS_ResourceAssignment() throws RuntimeException;

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

    /** Column name TipoLinea */
    public static final String COLUMNNAME_TipoLinea = "TipoLinea";

	/** Set Line Type	  */
	public void setTipoLinea (String TipoLinea);

	/** Get Line Type	  */
	public String getTipoLinea();

    /** Column name UsarFactor */
    public static final String COLUMNNAME_UsarFactor = "UsarFactor";

	/** Set Use Factor.
	  * Use factor
	  */
	public void setUsarFactor (boolean UsarFactor);

	/** Get Use Factor.
	  * Use factor
	  */
	public boolean isUsarFactor();

    /** Column name Visible */
    public static final String COLUMNNAME_Visible = "Visible";

	/** Set Visible	  */
	public void setVisible (boolean Visible);

	/** Get Visible	  */
	public boolean isVisible();
}
