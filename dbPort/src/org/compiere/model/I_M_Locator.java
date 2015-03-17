/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Locator
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_Locator 
{

    /** TableName=M_Locator */
    public static final String Table_Name = "M_Locator";

    /** AD_Table_ID=207 */
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

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

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

    /** Column name PriorityNo */
    public static final String COLUMNNAME_PriorityNo = "PriorityNo";

	/** Set Relative Priority.
	  * Where inventory should be picked from first
	  */
	public void setPriorityNo (int PriorityNo);

	/** Get Relative Priority.
	  * Where inventory should be picked from first
	  */
	public int getPriorityNo();

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

    /** Column name X */
    public static final String COLUMNNAME_X = "X";

	/** Set Aisle (X).
	  * X dimension, e.g., Aisle
	  */
	public void setX (String X);

	/** Get Aisle (X).
	  * X dimension, e.g., Aisle
	  */
	public String getX();

    /** Column name Y */
    public static final String COLUMNNAME_Y = "Y";

	/** Set Bin (Y).
	  * Y dimension, e.g., Bin
	  */
	public void setY (String Y);

	/** Get Bin (Y).
	  * Y dimension, e.g., Bin
	  */
	public String getY();

    /** Column name Z */
    public static final String COLUMNNAME_Z = "Z";

	/** Set Level (Z).
	  * Z dimension, e.g., Level
	  */
	public void setZ (String Z);

	/** Get Level (Z).
	  * Z dimension, e.g., Level
	  */
	public String getZ();
}
