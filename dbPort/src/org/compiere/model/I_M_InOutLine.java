/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_InOutLine
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_M_InOutLine 
{

    /** TableName=M_InOutLine */
    public static final String Table_Name = "M_InOutLine";

    /** AD_Table_ID=320 */
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

    /** Column name ConfirmedQty */
    public static final String COLUMNNAME_ConfirmedQty = "ConfirmedQty";

	/** Set Confirmed Quantity.
	  * Confirmation of a received quantity
	  */
	public void setConfirmedQty (BigDecimal ConfirmedQty);

	/** Get Confirmed Quantity.
	  * Confirmation of a received quantity
	  */
	public BigDecimal getConfirmedQty();

    /** Column name ConfirmedQty_Vol */
    public static final String COLUMNNAME_ConfirmedQty_Vol = "ConfirmedQty_Vol";

	/** Set Confirmed Qty Pack.
	  * Confirmation of a received quantity in the Pack UOM
	  */
	public void setConfirmedQty_Vol (BigDecimal ConfirmedQty_Vol);

	/** Get Confirmed Qty Pack.
	  * Confirmation of a received quantity in the Pack UOM
	  */
	public BigDecimal getConfirmedQty_Vol();

    /** Column name C_OrderLine_ID */
    public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";

	/** Set Sales Order Line.
	  * Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID);

	/** Get Sales Order Line.
	  * Sales Order Line
	  */
	public int getC_OrderLine_ID();

	public I_C_OrderLine getC_OrderLine() throws RuntimeException;

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

    /** Column name C_ProjectPhase_ID */
    public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";

	/** Set Project Phase.
	  * Phase of a Project
	  */
	public void setC_ProjectPhase_ID (int C_ProjectPhase_ID);

	/** Get Project Phase.
	  * Phase of a Project
	  */
	public int getC_ProjectPhase_ID();

	public I_C_ProjectPhase getC_ProjectPhase() throws RuntimeException;

    /** Column name C_ProjectTask_ID */
    public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";

	/** Set Project Task.
	  * Actual Project Task in a Phase
	  */
	public void setC_ProjectTask_ID (int C_ProjectTask_ID);

	/** Get Project Task.
	  * Actual Project Task in a Phase
	  */
	public int getC_ProjectTask_ID();

	public I_C_ProjectTask getC_ProjectTask() throws RuntimeException;

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

    /** Column name Desplegar */
    public static final String COLUMNNAME_Desplegar = "Desplegar";

	/** Set Deploy	  */
	public void setDesplegar (boolean Desplegar);

	/** Get Deploy	  */
	public boolean isDesplegar();

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

    /** Column name EXME_ActPacienteInd_ID */
    public static final String COLUMNNAME_EXME_ActPacienteInd_ID = "EXME_ActPacienteInd_ID";

	/** Set Indication's detail.
	  * Indication's detail
	  */
	public void setEXME_ActPacienteInd_ID (int EXME_ActPacienteInd_ID);

	/** Get Indication's detail.
	  * Indication's detail
	  */
	public int getEXME_ActPacienteInd_ID();

	public I_EXME_ActPacienteInd getEXME_ActPacienteInd() throws RuntimeException;

    /** Column name EXME_CtaPacDet_ID */
    public static final String COLUMNNAME_EXME_CtaPacDet_ID = "EXME_CtaPacDet_ID";

	/** Set Encounter Detail.
	  * Encounter Detail
	  */
	public void setEXME_CtaPacDet_ID (int EXME_CtaPacDet_ID);

	/** Get Encounter Detail.
	  * Encounter Detail
	  */
	public int getEXME_CtaPacDet_ID();

	public I_EXME_CtaPacDet getEXME_CtaPacDet() throws RuntimeException;

    /** Column name EXME_PartidaPres_ID */
    public static final String COLUMNNAME_EXME_PartidaPres_ID = "EXME_PartidaPres_ID";

	/** Set Budget Item.
	  * Budget Item
	  */
	public void setEXME_PartidaPres_ID (int EXME_PartidaPres_ID);

	/** Get Budget Item.
	  * Budget Item
	  */
	public int getEXME_PartidaPres_ID();

	public I_EXME_PartidaPres getEXME_PartidaPres() throws RuntimeException;

    /** Column name EXME_ProductClassFact_ID */
    public static final String COLUMNNAME_EXME_ProductClassFact_ID = "EXME_ProductClassFact_ID";

	/** Set Product class.
	  * Product class
	  */
	public void setEXME_ProductClassFact_ID (int EXME_ProductClassFact_ID);

	/** Get Product class.
	  * Product class
	  */
	public int getEXME_ProductClassFact_ID();

    /** Column name Fijo */
    public static final String COLUMNNAME_Fijo = "Fijo";

	/** Set Fixed.
	  * Fixed
	  */
	public void setFijo (boolean Fijo);

	/** Get Fixed.
	  * Fixed
	  */
	public boolean isFijo();

    /** Column name isBeingUsedInvoice */
    public static final String COLUMNNAME_isBeingUsedInvoice = "isBeingUsedInvoice";

	/** Set isBeingUsedInvoice	  */
	public void setisBeingUsedInvoice (boolean isBeingUsedInvoice);

	/** Get isBeingUsedInvoice	  */
	public boolean isBeingUsedInvoice();

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

    /** Column name LotInfo */
    public static final String COLUMNNAME_LotInfo = "LotInfo";

	/** Set Lot's information	  */
	public void setLotInfo (String LotInfo);

	/** Get Lot's information	  */
	public String getLotInfo();

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

    /** Column name M_InOut_ID */
    public static final String COLUMNNAME_M_InOut_ID = "M_InOut_ID";

	/** Set Shipment/Receipt.
	  * Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID);

	/** Get Shipment/Receipt.
	  * Material Shipment Document
	  */
	public int getM_InOut_ID();

	public I_M_InOut getM_InOut() throws RuntimeException;

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

    /** Column name MovementQty */
    public static final String COLUMNNAME_MovementQty = "MovementQty";

	/** Set Movement Quantity.
	  * Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty);

	/** Get Movement Quantity.
	  * Quantity of a product moved.
	  */
	public BigDecimal getMovementQty();

    /** Column name MovementQty_Vol */
    public static final String COLUMNNAME_MovementQty_Vol = "MovementQty_Vol";

	/** Set Movement Qty Pack.
	  * Quantity of a product moved in UOM Pack
	  */
	public void setMovementQty_Vol (BigDecimal MovementQty_Vol);

	/** Get Movement Qty Pack.
	  * Quantity of a product moved in UOM Pack
	  */
	public BigDecimal getMovementQty_Vol();

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

    /** Column name M_RMALine_ID */
    public static final String COLUMNNAME_M_RMALine_ID = "M_RMALine_ID";

	/** Set RMA Line.
	  * Return Material Authorization Line
	  */
	public void setM_RMALine_ID (int M_RMALine_ID);

	/** Get RMA Line.
	  * Return Material Authorization Line
	  */
	public int getM_RMALine_ID();

	public I_M_RMALine getM_RMALine() throws RuntimeException;

    /** Column name PickedQty */
    public static final String COLUMNNAME_PickedQty = "PickedQty";

	/** Set Picked Quantity	  */
	public void setPickedQty (BigDecimal PickedQty);

	/** Get Picked Quantity	  */
	public BigDecimal getPickedQty();

    /** Column name PickedQty_Vol */
    public static final String COLUMNNAME_PickedQty_Vol = "PickedQty_Vol";

	/** Set Picked Qty Pack.
	  * Picked Quantity in UOM Pack
	  */
	public void setPickedQty_Vol (BigDecimal PickedQty_Vol);

	/** Get Picked Qty Pack.
	  * Picked Quantity in UOM Pack
	  */
	public BigDecimal getPickedQty_Vol();

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

    /** Column name PriceActual_Vol */
    public static final String COLUMNNAME_PriceActual_Vol = "PriceActual_Vol";

	/** Set Unit Price Pack.
	  * Actual Price Pack
	  */
	public void setPriceActual_Vol (BigDecimal PriceActual_Vol);

	/** Get Unit Price Pack.
	  * Actual Price Pack
	  */
	public BigDecimal getPriceActual_Vol();

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

    /** Column name PriceList_Vol */
    public static final String COLUMNNAME_PriceList_Vol = "PriceList_Vol";

	/** Set Pack Price List.
	  * Price List for the Pack UOM
	  */
	public void setPriceList_Vol (BigDecimal PriceList_Vol);

	/** Get Pack Price List.
	  * Price List for the Pack UOM
	  */
	public BigDecimal getPriceList_Vol();

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

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name QtyEntered_Vol */
    public static final String COLUMNNAME_QtyEntered_Vol = "QtyEntered_Vol";

	/** Set Qty Pack.
	  * The Quantity Entered is based on the UoM Pack
	  */
	public void setQtyEntered_Vol (BigDecimal QtyEntered_Vol);

	/** Get Qty Pack.
	  * The Quantity Entered is based on the UoM Pack
	  */
	public BigDecimal getQtyEntered_Vol();

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

    /** Column name Ref_InOutLine_ID */
    public static final String COLUMNNAME_Ref_InOutLine_ID = "Ref_InOutLine_ID";

	/** Set Referenced Shipment Line	  */
	public void setRef_InOutLine_ID (int Ref_InOutLine_ID);

	/** Get Referenced Shipment Line	  */
	public int getRef_InOutLine_ID();

    /** Column name ReversalLine_ID */
    public static final String COLUMNNAME_ReversalLine_ID = "ReversalLine_ID";

	/** Set Reversal Line.
	  * Use to keep the reversal line ID for reversing costing purpose
	  */
	public void setReversalLine_ID (int ReversalLine_ID);

	/** Get Reversal Line.
	  * Use to keep the reversal line ID for reversing costing purpose
	  */
	public int getReversalLine_ID();

    /** Column name ScrappedQty */
    public static final String COLUMNNAME_ScrappedQty = "ScrappedQty";

	/** Set Scrapped Quantity.
	  * The Quantity scrapped due to QA issues
	  */
	public void setScrappedQty (BigDecimal ScrappedQty);

	/** Get Scrapped Quantity.
	  * The Quantity scrapped due to QA issues
	  */
	public BigDecimal getScrappedQty();

    /** Column name ScrappedQty_Vol */
    public static final String COLUMNNAME_ScrappedQty_Vol = "ScrappedQty_Vol";

	/** Set Scrapped Qty Pack.
	  * The Quantity scrapped due to QA issues in UOM Pack
	  */
	public void setScrappedQty_Vol (BigDecimal ScrappedQty_Vol);

	/** Get Scrapped Qty Pack.
	  * The Quantity scrapped due to QA issues in UOM Pack
	  */
	public BigDecimal getScrappedQty_Vol();

    /** Column name TargetQty */
    public static final String COLUMNNAME_TargetQty = "TargetQty";

	/** Set Target Quantity.
	  * Target Movement Quantity
	  */
	public void setTargetQty (BigDecimal TargetQty);

	/** Get Target Quantity.
	  * Target Movement Quantity
	  */
	public BigDecimal getTargetQty();

    /** Column name TargetQty_Vol */
    public static final String COLUMNNAME_TargetQty_Vol = "TargetQty_Vol";

	/** Set Target Qty Pack.
	  * Target Quantity Pack
	  */
	public void setTargetQty_Vol (BigDecimal TargetQty_Vol);

	/** Get Target Qty Pack.
	  * Target Quantity Pack
	  */
	public BigDecimal getTargetQty_Vol();

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
}
