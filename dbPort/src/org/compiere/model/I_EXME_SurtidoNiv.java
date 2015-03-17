/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SurtidoNiv
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_SurtidoNiv 
{

    /** TableName=EXME_SurtidoNiv */
    public static final String Table_Name = "EXME_SurtidoNiv";

    /** AD_Table_ID=1201257 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name DefaultProduct */
    public static final String COLUMNNAME_DefaultProduct = "DefaultProduct";

	/** Set Default Product	  */
	public void setDefaultProduct (boolean DefaultProduct);

	/** Get Default Product	  */
	public boolean isDefaultProduct();

    /** Column name EXME_RevenueCode_ID */
    public static final String COLUMNNAME_EXME_RevenueCode_ID = "EXME_RevenueCode_ID";

	/** Set Revenue Code ID	  */
	public void setEXME_RevenueCode_ID (int EXME_RevenueCode_ID);

	/** Get Revenue Code ID	  */
	public int getEXME_RevenueCode_ID();

	public I_EXME_RevenueCode getEXME_RevenueCode() throws RuntimeException;

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

    /** Column name Miscelaneos */
    public static final String COLUMNNAME_Miscelaneos = "Miscelaneos";

	/** Set Miscellaneous	  */
	public void setMiscelaneos (String Miscelaneos);

	/** Get Miscellaneous	  */
	public String getMiscelaneos();

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

    /** Column name ReplenishType */
    public static final String COLUMNNAME_ReplenishType = "ReplenishType";

	/** Set Replenish Type.
	  * Method for re-ordering a product
	  */
	public void setReplenishType (boolean ReplenishType);

	/** Get Replenish Type.
	  * Method for re-ordering a product
	  */
	public boolean isReplenishType();
}
