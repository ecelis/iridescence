/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for SM_Devolucion
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_SM_Devolucion 
{

    /** TableName=SM_Devolucion */
    public static final String Table_Name = "SM_Devolucion";

    /** AD_Table_ID=1200404 */
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

    /** Column name Cantidad */
    public static final String COLUMNNAME_Cantidad = "Cantidad";

	/** Set Quantity.
	  * Quantity
	  */
	public void setCantidad (BigDecimal Cantidad);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getCantidad();

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

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name EXME_Error_ID */
    public static final String COLUMNNAME_EXME_Error_ID = "EXME_Error_ID";

	/** Set Error  Number	  */
	public void setEXME_Error_ID (int EXME_Error_ID);

	/** Get Error  Number	  */
	public int getEXME_Error_ID();

    /** Column name GuaranteeDate */
    public static final String COLUMNNAME_GuaranteeDate = "GuaranteeDate";

	/** Set Guarantee Date.
	  * Date when guarantee expires
	  */
	public void setGuaranteeDate (Timestamp GuaranteeDate);

	/** Get Guarantee Date.
	  * Date when guarantee expires
	  */
	public Timestamp getGuaranteeDate();

    /** Column name Lot */
    public static final String COLUMNNAME_Lot = "Lot";

	/** Set Lot No.
	  * Lot number (alphanumeric)
	  */
	public void setLot (String Lot);

	/** Get Lot No.
	  * Lot number (alphanumeric)
	  */
	public String getLot();

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

    /** Column name Mensajes_BD */
    public static final String COLUMNNAME_Mensajes_BD = "Mensajes_BD";

	/** Set Database Message	  */
	public void setMensajes_BD (String Mensajes_BD);

	/** Get Database Message	  */
	public String getMensajes_BD();

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

	public I_M_Locator getM_Locator() throws RuntimeException;

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

    /** Column name M_WarehouseTo_ID */
    public static final String COLUMNNAME_M_WarehouseTo_ID = "M_WarehouseTo_ID";

	/** Set Warehouse To.
	  * Warehouse
	  */
	public void setM_WarehouseTo_ID (int M_WarehouseTo_ID);

	/** Get Warehouse To.
	  * Warehouse
	  */
	public int getM_WarehouseTo_ID();

    /** Column name Producto_Value */
    public static final String COLUMNNAME_Producto_Value = "Producto_Value";

	/** Set Product Key	  */
	public void setProducto_Value (String Producto_Value);

	/** Get Product Key	  */
	public String getProducto_Value();

    /** Column name SM_Devolucion_ID */
    public static final String COLUMNNAME_SM_Devolucion_ID = "SM_Devolucion_ID";

	/** Set Virtual Returning	  */
	public void setSM_Devolucion_ID (int SM_Devolucion_ID);

	/** Get Virtual Returning	  */
	public int getSM_Devolucion_ID();

    /** Column name Transferido */
    public static final String COLUMNNAME_Transferido = "Transferido";

	/** Set Transferred	  */
	public void setTransferido (boolean Transferido);

	/** Get Transferred	  */
	public boolean isTransferido();

    /** Column name UOM_Value */
    public static final String COLUMNNAME_UOM_Value = "UOM_Value";

	/** Set Uom Key	  */
	public void setUOM_Value (String UOM_Value);

	/** Get Uom Key	  */
	public String getUOM_Value();

    /** Column name WHS_Dev_Value */
    public static final String COLUMNNAME_WHS_Dev_Value = "WHS_Dev_Value";

	/** Set Warehouse Returning	  */
	public void setWHS_Dev_Value (String WHS_Dev_Value);

	/** Get Warehouse Returning	  */
	public String getWHS_Dev_Value();

    /** Column name WHS_Rec_Value */
    public static final String COLUMNNAME_WHS_Rec_Value = "WHS_Rec_Value";

	/** Set Warehouse Receiver	  */
	public void setWHS_Rec_Value (String WHS_Rec_Value);

	/** Get Warehouse Receiver	  */
	public String getWHS_Rec_Value();
}
