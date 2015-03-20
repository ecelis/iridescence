/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_MovementLine
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_M_MovementLine 
{

    /** TableName=M_MovementLine */
    public static final String Table_Name = "M_MovementLine";

    /** AD_Table_ID=324 */
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

    /** Column name DD_OrderLine_ID */
    public static final String COLUMNNAME_DD_OrderLine_ID = "DD_OrderLine_ID";

	/** Set Distribution Order Line	  */
	public void setDD_OrderLine_ID (int DD_OrderLine_ID);

	/** Get Distribution Order Line	  */
	public int getDD_OrderLine_ID();

	public I_DD_OrderLine getDD_OrderLine() throws RuntimeException;

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

    /** Column name M_AttributeSetInstanceTo_ID */
    public static final String COLUMNNAME_M_AttributeSetInstanceTo_ID = "M_AttributeSetInstanceTo_ID";

	/** Set Attribute Set Instance To.
	  * Target Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstanceTo_ID (int M_AttributeSetInstanceTo_ID);

	/** Get Attribute Set Instance To.
	  * Target Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstanceTo_ID();

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

    /** Column name M_LocatorTo_ID */
    public static final String COLUMNNAME_M_LocatorTo_ID = "M_LocatorTo_ID";

	/** Set Locator To.
	  * Location inventory is moved to
	  */
	public void setM_LocatorTo_ID (int M_LocatorTo_ID);

	/** Get Locator To.
	  * Location inventory is moved to
	  */
	public int getM_LocatorTo_ID();

    /** Column name M_Movement_ID */
    public static final String COLUMNNAME_M_Movement_ID = "M_Movement_ID";

	/** Set Inventory Move.
	  * Movement of Inventory
	  */
	public void setM_Movement_ID (int M_Movement_ID);

	/** Get Inventory Move.
	  * Movement of Inventory
	  */
	public int getM_Movement_ID();

	public I_M_Movement getM_Movement() throws RuntimeException;

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

    /** Column name NumSerie */
    public static final String COLUMNNAME_NumSerie = "NumSerie";

	/** Set Serial number	  */
	public void setNumSerie (String NumSerie);

	/** Get Serial number	  */
	public String getNumSerie();

    /** Column name Op_Uom */
    public static final String COLUMNNAME_Op_Uom = "Op_Uom";

	/** Set UOM Selection.
	  * UOM Selection
	  */
	public void setOp_Uom (int Op_Uom);

	/** Get UOM Selection.
	  * UOM Selection
	  */
	public int getOp_Uom();

    /** Column name OriginalQty */
    public static final String COLUMNNAME_OriginalQty = "OriginalQty";

	/** Set Original Quantity	  */
	public void setOriginalQty (BigDecimal OriginalQty);

	/** Get Original Quantity	  */
	public BigDecimal getOriginalQty();

    /** Column name OriginalQty_Vol */
    public static final String COLUMNNAME_OriginalQty_Vol = "OriginalQty_Vol";

	/** Set Original Quantity Pack	  */
	public void setOriginalQty_Vol (BigDecimal OriginalQty_Vol);

	/** Get Original Quantity Pack	  */
	public BigDecimal getOriginalQty_Vol();

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

    /** Column name Ref_MovementLine_ID */
    public static final String COLUMNNAME_Ref_MovementLine_ID = "Ref_MovementLine_ID";

	/** Set Reference to the line of movement	  */
	public void setRef_MovementLine_ID (int Ref_MovementLine_ID);

	/** Get Reference to the line of movement	  */
	public int getRef_MovementLine_ID();

    /** Column name ReturnQty */
    public static final String COLUMNNAME_ReturnQty = "ReturnQty";

	/** Set Return quantity	  */
	public void setReturnQty (BigDecimal ReturnQty);

	/** Get Return quantity	  */
	public BigDecimal getReturnQty();

    /** Column name ReturnQty_Vol */
    public static final String COLUMNNAME_ReturnQty_Vol = "ReturnQty_Vol";

	/** Set Return quantity (Pack)	  */
	public void setReturnQty_Vol (BigDecimal ReturnQty_Vol);

	/** Get Return quantity (Pack)	  */
	public BigDecimal getReturnQty_Vol();

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
