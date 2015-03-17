/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_InventoryLine
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_M_InventoryLine 
{

    /** TableName=M_InventoryLine */
    public static final String Table_Name = "M_InventoryLine";

    /** AD_Table_ID=322 */
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

    /** Column name EXME_ProductClassFact_ID */
    public static final String COLUMNNAME_EXME_ProductClassFact_ID = "EXME_ProductClassFact_ID";

	/** Set Product class and Fact. acct..
	  * Product class and Fact. acct.
	  */
	public void setEXME_ProductClassFact_ID (int EXME_ProductClassFact_ID);

	/** Get Product class and Fact. acct..
	  * Product class and Fact. acct.
	  */
	public int getEXME_ProductClassFact_ID();

    /** Column name InventoryType */
    public static final String COLUMNNAME_InventoryType = "InventoryType";

	/** Set Inventory Type.
	  * Type of inventory difference
	  */
	public void setInventoryType (String InventoryType);

	/** Get Inventory Type.
	  * Type of inventory difference
	  */
	public String getInventoryType();

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

    /** Column name M_Inventory_ID */
    public static final String COLUMNNAME_M_Inventory_ID = "M_Inventory_ID";

	/** Set Phys.Inventory.
	  * Parameters for a Physical Inventory
	  */
	public void setM_Inventory_ID (int M_Inventory_ID);

	/** Get Phys.Inventory.
	  * Parameters for a Physical Inventory
	  */
	public int getM_Inventory_ID();

	public I_M_Inventory getM_Inventory() throws RuntimeException;

    /** Column name M_InventoryLine_ID */
    public static final String COLUMNNAME_M_InventoryLine_ID = "M_InventoryLine_ID";

	/** Set Phys.Inventory Line.
	  * Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID);

	/** Get Phys.Inventory Line.
	  * Unique line in an Inventory document
	  */
	public int getM_InventoryLine_ID();

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

    /** Column name QtyBook */
    public static final String COLUMNNAME_QtyBook = "QtyBook";

	/** Set Quantity book.
	  * Book Quantity
	  */
	public void setQtyBook (BigDecimal QtyBook);

	/** Get Quantity book.
	  * Book Quantity
	  */
	public BigDecimal getQtyBook();

    /** Column name QtyBook_Uom */
    public static final String COLUMNNAME_QtyBook_Uom = "QtyBook_Uom";

	/** Set Quantity book.
	  * Book Quantity
	  */
	public void setQtyBook_Uom (BigDecimal QtyBook_Uom);

	/** Get Quantity book.
	  * Book Quantity
	  */
	public BigDecimal getQtyBook_Uom();

    /** Column name QtyBook_Vol */
    public static final String COLUMNNAME_QtyBook_Vol = "QtyBook_Vol";

	/** Set Quantity book Pack.
	  * Book Quantity
	  */
	public void setQtyBook_Vol (BigDecimal QtyBook_Vol);

	/** Get Quantity book Pack.
	  * Book Quantity
	  */
	public BigDecimal getQtyBook_Vol();

    /** Column name QtyCount */
    public static final String COLUMNNAME_QtyCount = "QtyCount";

	/** Set Quantity count.
	  * Counted Quantity
	  */
	public void setQtyCount (BigDecimal QtyCount);

	/** Get Quantity count.
	  * Counted Quantity
	  */
	public BigDecimal getQtyCount();

    /** Column name QtyCount_Uom */
    public static final String COLUMNNAME_QtyCount_Uom = "QtyCount_Uom";

	/** Set Quantity count.
	  * Counted Quantity
	  */
	public void setQtyCount_Uom (BigDecimal QtyCount_Uom);

	/** Get Quantity count.
	  * Counted Quantity
	  */
	public BigDecimal getQtyCount_Uom();

    /** Column name QtyCount_Vol */
    public static final String COLUMNNAME_QtyCount_Vol = "QtyCount_Vol";

	/** Set Quantity count Pack.
	  * Counted Quantity Pack
	  */
	public void setQtyCount_Vol (BigDecimal QtyCount_Vol);

	/** Get Quantity count Pack.
	  * Counted Quantity Pack
	  */
	public BigDecimal getQtyCount_Vol();

    /** Column name QtyCsv */
    public static final String COLUMNNAME_QtyCsv = "QtyCsv";

	/** Set Qty Csv	  */
	public void setQtyCsv (BigDecimal QtyCsv);

	/** Get Qty Csv	  */
	public BigDecimal getQtyCsv();

    /** Column name QtyInternalUse */
    public static final String COLUMNNAME_QtyInternalUse = "QtyInternalUse";

	/** Set Internal Use Qty.
	  * Internal Use Quantity removed from Inventory
	  */
	public void setQtyInternalUse (BigDecimal QtyInternalUse);

	/** Get Internal Use Qty.
	  * Internal Use Quantity removed from Inventory
	  */
	public BigDecimal getQtyInternalUse();

    /** Column name QtyInternalUse_Uom */
    public static final String COLUMNNAME_QtyInternalUse_Uom = "QtyInternalUse_Uom";

	/** Set Internal Use(UOM).
	  * Internal Use Quantity removed from Inventory(UOM)
	  */
	public void setQtyInternalUse_Uom (BigDecimal QtyInternalUse_Uom);

	/** Get Internal Use(UOM).
	  * Internal Use Quantity removed from Inventory(UOM)
	  */
	public BigDecimal getQtyInternalUse_Uom();

    /** Column name QtyInternalUse_Vol */
    public static final String COLUMNNAME_QtyInternalUse_Vol = "QtyInternalUse_Vol";

	/** Set QtyInternalUse_Vol.
	  * Internal Use Quantity removed from Inventory(UOM)
	  */
	public void setQtyInternalUse_Vol (BigDecimal QtyInternalUse_Vol);

	/** Get QtyInternalUse_Vol.
	  * Internal Use Quantity removed from Inventory(UOM)
	  */
	public BigDecimal getQtyInternalUse_Vol();

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
}
