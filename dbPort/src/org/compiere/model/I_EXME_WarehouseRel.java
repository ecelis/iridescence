/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_WarehouseRel
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_WarehouseRel 
{

    /** TableName=EXME_WarehouseRel */
    public static final String Table_Name = "EXME_WarehouseRel";

    /** AD_Table_ID=1000107 */
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
}
