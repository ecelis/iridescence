/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for T_Replenish
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_T_Replenish 
{

    /** TableName=T_Replenish */
    public static final String Table_Name = "T_Replenish";

    /** AD_Table_ID=364 */
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

    /** Column name AD_PInstance_ID */
    public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";

	/** Set Process Instance.
	  * Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID);

	/** Get Process Instance.
	  * Instance of the process
	  */
	public int getAD_PInstance_ID();

	public I_AD_PInstance getAD_PInstance() throws RuntimeException;

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

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name Level_Max */
    public static final String COLUMNNAME_Level_Max = "Level_Max";

	/** Set Maximum Level.
	  * Maximum Inventory level for this product
	  */
	public void setLevel_Max (BigDecimal Level_Max);

	/** Get Maximum Level.
	  * Maximum Inventory level for this product
	  */
	public BigDecimal getLevel_Max();

    /** Column name Level_Max_Vol */
    public static final String COLUMNNAME_Level_Max_Vol = "Level_Max_Vol";

	/** Set Packing Maximum Level.
	  * Maximum Inventory level of packing for this product
	  */
	public void setLevel_Max_Vol (BigDecimal Level_Max_Vol);

	/** Get Packing Maximum Level.
	  * Maximum Inventory level of packing for this product
	  */
	public BigDecimal getLevel_Max_Vol();

    /** Column name Level_Min */
    public static final String COLUMNNAME_Level_Min = "Level_Min";

	/** Set Minimum Level.
	  * Minimum Inventory level for this product
	  */
	public void setLevel_Min (BigDecimal Level_Min);

	/** Get Minimum Level.
	  * Minimum Inventory level for this product
	  */
	public BigDecimal getLevel_Min();

    /** Column name Level_Min_Vol */
    public static final String COLUMNNAME_Level_Min_Vol = "Level_Min_Vol";

	/** Set Packing Minimum Level.
	  * Minimum Inventory level of packing for this product
	  */
	public void setLevel_Min_Vol (BigDecimal Level_Min_Vol);

	/** Get Packing Minimum Level.
	  * Minimum Inventory level of packing for this product
	  */
	public BigDecimal getLevel_Min_Vol();

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

    /** Column name M_WarehouseSource_ID */
    public static final String COLUMNNAME_M_WarehouseSource_ID = "M_WarehouseSource_ID";

	/** Set Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public void setM_WarehouseSource_ID (int M_WarehouseSource_ID);

	/** Get Source Warehouse.
	  * Optional Warehouse to replenish from
	  */
	public int getM_WarehouseSource_ID();

    /** Column name Order_Min */
    public static final String COLUMNNAME_Order_Min = "Order_Min";

	/** Set Minimum Order Qty.
	  * Minimum order quantity in UOM
	  */
	public void setOrder_Min (BigDecimal Order_Min);

	/** Get Minimum Order Qty.
	  * Minimum order quantity in UOM
	  */
	public BigDecimal getOrder_Min();

    /** Column name Order_Min_Vol */
    public static final String COLUMNNAME_Order_Min_Vol = "Order_Min_Vol";

	/** Set Minimum Order Qty Pack.
	  * Minimum Order Qty Pack
	  */
	public void setOrder_Min_Vol (BigDecimal Order_Min_Vol);

	/** Get Minimum Order Qty Pack.
	  * Minimum Order Qty Pack
	  */
	public BigDecimal getOrder_Min_Vol();

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

    /** Column name Order_Pack_Vol */
    public static final String COLUMNNAME_Order_Pack_Vol = "Order_Pack_Vol";

	/** Set Order Pack Qty.
	  * Package order size in UOM (e.g. order set of 5 units)
	  */
	public void setOrder_Pack_Vol (BigDecimal Order_Pack_Vol);

	/** Get Order Pack Qty.
	  * Package order size in UOM (e.g. order set of 5 units)
	  */
	public BigDecimal getOrder_Pack_Vol();

    /** Column name QtyOnHand */
    public static final String COLUMNNAME_QtyOnHand = "QtyOnHand";

	/** Set On Hand Quantity.
	  * On Hand Quantity
	  */
	public void setQtyOnHand (BigDecimal QtyOnHand);

	/** Get On Hand Quantity.
	  * On Hand Quantity
	  */
	public BigDecimal getQtyOnHand();

    /** Column name QtyOnHand_Vol */
    public static final String COLUMNNAME_QtyOnHand_Vol = "QtyOnHand_Vol";

	/** Set On Hand Quantity Pack.
	  * On Hand Quantity Pack
	  */
	public void setQtyOnHand_Vol (BigDecimal QtyOnHand_Vol);

	/** Get On Hand Quantity Pack.
	  * On Hand Quantity Pack
	  */
	public BigDecimal getQtyOnHand_Vol();

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

    /** Column name QtyOrdered_Vol */
    public static final String COLUMNNAME_QtyOrdered_Vol = "QtyOrdered_Vol";

	/** Set Ordered Qty Pack.
	  * Ordered Quantity Packs ordered
	  */
	public void setQtyOrdered_Vol (BigDecimal QtyOrdered_Vol);

	/** Get Ordered Qty Pack.
	  * Ordered Quantity Packs ordered
	  */
	public BigDecimal getQtyOrdered_Vol();

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

    /** Column name QtyReserved_Vol */
    public static final String COLUMNNAME_QtyReserved_Vol = "QtyReserved_Vol";

	/** Set QtyReserved_Vol.
	  * Reserved Quantity Package
	  */
	public void setQtyReserved_Vol (BigDecimal QtyReserved_Vol);

	/** Get QtyReserved_Vol.
	  * Reserved Quantity Package
	  */
	public BigDecimal getQtyReserved_Vol();

    /** Column name QtyToOrder */
    public static final String COLUMNNAME_QtyToOrder = "QtyToOrder";

	/** Set Quantity to Order	  */
	public void setQtyToOrder (BigDecimal QtyToOrder);

	/** Get Quantity to Order	  */
	public BigDecimal getQtyToOrder();

    /** Column name QtyToOrder_Vol */
    public static final String COLUMNNAME_QtyToOrder_Vol = "QtyToOrder_Vol";

	/** Set Quantity Pack to Order.
	  * Quantity Pack to Order
	  */
	public void setQtyToOrder_Vol (BigDecimal QtyToOrder_Vol);

	/** Get Quantity Pack to Order.
	  * Quantity Pack to Order
	  */
	public BigDecimal getQtyToOrder_Vol();

    /** Column name ReplenishmentCreate */
    public static final String COLUMNNAME_ReplenishmentCreate = "ReplenishmentCreate";

	/** Set Create.
	  * Create from Replenishment
	  */
	public void setReplenishmentCreate (String ReplenishmentCreate);

	/** Get Create.
	  * Create from Replenishment
	  */
	public String getReplenishmentCreate();

    /** Column name ReplenishType */
    public static final String COLUMNNAME_ReplenishType = "ReplenishType";

	/** Set Replenish Type.
	  * Method for re-ordering a product
	  */
	public void setReplenishType (String ReplenishType);

	/** Get Replenish Type.
	  * Method for re-ordering a product
	  */
	public String getReplenishType();

    /** Column name SeqNoInsert */
    public static final String COLUMNNAME_SeqNoInsert = "SeqNoInsert";

	/** Set SeqNoInsert.
	  * Number of the sequence to insert
	  */
	public void setSeqNoInsert (BigDecimal SeqNoInsert);

	/** Get SeqNoInsert.
	  * Number of the sequence to insert
	  */
	public BigDecimal getSeqNoInsert();
}
