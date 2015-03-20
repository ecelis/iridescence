/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_VentaSuspendidaDet
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_VentaSuspendidaDet 
{

    /** TableName=EXME_VentaSuspendidaDet */
    public static final String Table_Name = "EXME_VentaSuspendidaDet";

    /** AD_Table_ID=1200201 */
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

    /** Column name EXME_VentaSuspendidaDet_ID */
    public static final String COLUMNNAME_EXME_VentaSuspendidaDet_ID = "EXME_VentaSuspendidaDet_ID";

	/** Set Suspended Sale Detail.
	  * Suspended Sale Detail
	  */
	public void setEXME_VentaSuspendidaDet_ID (int EXME_VentaSuspendidaDet_ID);

	/** Get Suspended Sale Detail.
	  * Suspended Sale Detail
	  */
	public int getEXME_VentaSuspendidaDet_ID();

    /** Column name EXME_VentaSuspendida_ID */
    public static final String COLUMNNAME_EXME_VentaSuspendida_ID = "EXME_VentaSuspendida_ID";

	/** Set Suspended Sale	  */
	public void setEXME_VentaSuspendida_ID (int EXME_VentaSuspendida_ID);

	/** Get Suspended Sale	  */
	public int getEXME_VentaSuspendida_ID();

	public I_EXME_VentaSuspendida getEXME_VentaSuspendida() throws RuntimeException;

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

    /** Column name M_WarehouseRel_ID */
    public static final String COLUMNNAME_M_WarehouseRel_ID = "M_WarehouseRel_ID";

	/** Set Warehouse Relation.
	  * Warehouse Relation
	  */
	public void setM_WarehouseRel_ID (int M_WarehouseRel_ID);

	/** Get Warehouse Relation.
	  * Warehouse Relation
	  */
	public int getM_WarehouseRel_ID();

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
}
