/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_TransactionAllocation
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_TransactionAllocation 
{

    /** TableName=M_TransactionAllocation */
    public static final String Table_Name = "M_TransactionAllocation";

    /** AD_Table_ID=636 */
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

    /** Column name AllocationStrategyType */
    public static final String COLUMNNAME_AllocationStrategyType = "AllocationStrategyType";

	/** Set Allocation Strategy Type.
	  * Allocation Strategy Type
	  */
	public void setAllocationStrategyType (String AllocationStrategyType);

	/** Get Allocation Strategy Type.
	  * Allocation Strategy Type
	  */
	public String getAllocationStrategyType();

    /** Column name IsAllocated */
    public static final String COLUMNNAME_IsAllocated = "IsAllocated";

	/** Set Allocated.
	  * Indicates if the payment has been allocated
	  */
	public void setIsAllocated (boolean IsAllocated);

	/** Get Allocated.
	  * Indicates if the payment has been allocated
	  */
	public boolean isAllocated();

    /** Column name IsManual */
    public static final String COLUMNNAME_IsManual = "IsManual";

	/** Set Manual.
	  * This is a manual process
	  */
	public void setIsManual (boolean IsManual);

	/** Get Manual.
	  * This is a manual process
	  */
	public boolean isManual();

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

	public I_M_InventoryLine getM_InventoryLine() throws RuntimeException;

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

    /** Column name M_ProductionLine_ID */
    public static final String COLUMNNAME_M_ProductionLine_ID = "M_ProductionLine_ID";

	/** Set Production Line.
	  * Document Line representing a production
	  */
	public void setM_ProductionLine_ID (int M_ProductionLine_ID);

	/** Get Production Line.
	  * Document Line representing a production
	  */
	public int getM_ProductionLine_ID();

	public I_M_ProductionLine getM_ProductionLine() throws RuntimeException;

    /** Column name M_Transaction_ID */
    public static final String COLUMNNAME_M_Transaction_ID = "M_Transaction_ID";

	/** Set Inventory Transaction	  */
	public void setM_Transaction_ID (int M_Transaction_ID);

	/** Get Inventory Transaction	  */
	public int getM_Transaction_ID();

	public I_M_Transaction getM_Transaction() throws RuntimeException;

    /** Column name Out_M_InOutLine_ID */
    public static final String COLUMNNAME_Out_M_InOutLine_ID = "Out_M_InOutLine_ID";

	/** Set Out Shipment Line.
	  * Outgoing Shipment/Receipt
	  */
	public void setOut_M_InOutLine_ID (int Out_M_InOutLine_ID);

	/** Get Out Shipment Line.
	  * Outgoing Shipment/Receipt
	  */
	public int getOut_M_InOutLine_ID();

    /** Column name Out_M_InventoryLine_ID */
    public static final String COLUMNNAME_Out_M_InventoryLine_ID = "Out_M_InventoryLine_ID";

	/** Set Out Inventory Line.
	  * Outgoing Inventory Line
	  */
	public void setOut_M_InventoryLine_ID (int Out_M_InventoryLine_ID);

	/** Get Out Inventory Line.
	  * Outgoing Inventory Line
	  */
	public int getOut_M_InventoryLine_ID();

    /** Column name Out_M_ProductionLine_ID */
    public static final String COLUMNNAME_Out_M_ProductionLine_ID = "Out_M_ProductionLine_ID";

	/** Set Out Production Line.
	  * Outgoing Production Line
	  */
	public void setOut_M_ProductionLine_ID (int Out_M_ProductionLine_ID);

	/** Get Out Production Line.
	  * Outgoing Production Line
	  */
	public int getOut_M_ProductionLine_ID();

    /** Column name Out_M_Transaction_ID */
    public static final String COLUMNNAME_Out_M_Transaction_ID = "Out_M_Transaction_ID";

	/** Set Out Transaction.
	  * Outgoing Transaction
	  */
	public void setOut_M_Transaction_ID (int Out_M_Transaction_ID);

	/** Get Out Transaction.
	  * Outgoing Transaction
	  */
	public int getOut_M_Transaction_ID();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();
}