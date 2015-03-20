/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProductClassWhs
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ProductClassWhs 
{

    /** TableName=EXME_ProductClassWhs */
    public static final String Table_Name = "EXME_ProductClassWhs";

    /** AD_Table_ID=1201226 */
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

    /** Column name EXME_ProductClassWhs_ID */
    public static final String COLUMNNAME_EXME_ProductClassWhs_ID = "EXME_ProductClassWhs_ID";

	/** Set Related product class - service unit.
	  * Related product class - service unit
	  */
	public void setEXME_ProductClassWhs_ID (int EXME_ProductClassWhs_ID);

	/** Get Related product class - service unit.
	  * Related product class - service unit
	  */
	public int getEXME_ProductClassWhs_ID();

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

    /** Column name ProductClass */
    public static final String COLUMNNAME_ProductClass = "ProductClass";

	/** Set Product Class	  */
	public void setProductClass (String ProductClass);

	/** Get Product Class	  */
	public String getProductClass();
}
