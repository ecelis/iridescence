/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for SM_Solicitud
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_SM_Solicitud 
{

    /** TableName=SM_Solicitud */
    public static final String Table_Name = "SM_Solicitud";

    /** AD_Table_ID=1200401 */
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
	public void setEXME_Error_ID (String EXME_Error_ID);

	/** Get Error  Number	  */
	public String getEXME_Error_ID();

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

    /** Column name MovementDate */
    public static final String COLUMNNAME_MovementDate = "MovementDate";

	/** Set Movement Date.
	  * Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate);

	/** Get Movement Date.
	  * Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate();

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

    /** Column name SM_Solicitud_ID */
    public static final String COLUMNNAME_SM_Solicitud_ID = "SM_Solicitud_ID";

	/** Set Virtual Request	  */
	public void setSM_Solicitud_ID (int SM_Solicitud_ID);

	/** Get Virtual Request	  */
	public int getSM_Solicitud_ID();

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

    /** Column name WHS_Sol_Value */
    public static final String COLUMNNAME_WHS_Sol_Value = "WHS_Sol_Value";

	/** Set Warehouse Requesting	  */
	public void setWHS_Sol_Value (String WHS_Sol_Value);

	/** Get Warehouse Requesting	  */
	public String getWHS_Sol_Value();

    /** Column name WHS_Sur_Value */
    public static final String COLUMNNAME_WHS_Sur_Value = "WHS_Sur_Value";

	/** Set Warehouse Supplier	  */
	public void setWHS_Sur_Value (String WHS_Sur_Value);

	/** Get Warehouse Supplier	  */
	public String getWHS_Sur_Value();
}
