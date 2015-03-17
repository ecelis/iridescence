/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_PedidoBaseDet
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_PedidoBaseDet 
{

    /** TableName=EXME_PedidoBaseDet */
    public static final String Table_Name = "EXME_PedidoBaseDet";

    /** AD_Table_ID=1000109 */
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

    /** Column name ConfirmedQty */
    public static final String COLUMNNAME_ConfirmedQty = "ConfirmedQty";

	/** Set Confirmed Quantity.
	  * Confirmation of a received quantity
	  */
	public void setConfirmedQty (int ConfirmedQty);

	/** Get Confirmed Quantity.
	  * Confirmation of a received quantity
	  */
	public int getConfirmedQty();

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

    /** Column name EXME_PedidoBaseDet_ID */
    public static final String COLUMNNAME_EXME_PedidoBaseDet_ID = "EXME_PedidoBaseDet_ID";

	/** Set Detail Base Order.
	  * Detail Base Order
	  */
	public void setEXME_PedidoBaseDet_ID (int EXME_PedidoBaseDet_ID);

	/** Get Detail Base Order.
	  * Detail Base Order
	  */
	public int getEXME_PedidoBaseDet_ID();

    /** Column name EXME_PedidoBase_ID */
    public static final String COLUMNNAME_EXME_PedidoBase_ID = "EXME_PedidoBase_ID";

	/** Set Base Order.
	  * Base Order
	  */
	public void setEXME_PedidoBase_ID (int EXME_PedidoBase_ID);

	/** Get Base Order.
	  * Base Order
	  */
	public int getEXME_PedidoBase_ID();

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

    /** Column name ScrappedQty */
    public static final String COLUMNNAME_ScrappedQty = "ScrappedQty";

	/** Set Scrapped Quantity.
	  * The Quantity scrapped due to QA issues
	  */
	public void setScrappedQty (int ScrappedQty);

	/** Get Scrapped Quantity.
	  * The Quantity scrapped due to QA issues
	  */
	public int getScrappedQty();

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
	public void setTargetQty (int TargetQty);

	/** Get Target Quantity.
	  * Target Movement Quantity
	  */
	public int getTargetQty();

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
}
