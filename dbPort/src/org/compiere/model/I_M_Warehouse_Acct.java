/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Warehouse_Acct
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_Warehouse_Acct 
{

    /** TableName=M_Warehouse_Acct */
    public static final String Table_Name = "M_Warehouse_Acct";

    /** AD_Table_ID=191 */
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

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

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

    /** Column name W_Differences_Acct */
    public static final String COLUMNNAME_W_Differences_Acct = "W_Differences_Acct";

	/** Set Warehouse Differences.
	  * Warehouse Differences Account
	  */
	public void setW_Differences_Acct (int W_Differences_Acct);

	/** Get Warehouse Differences.
	  * Warehouse Differences Account
	  */
	public int getW_Differences_Acct();

    /** Column name W_InvActualAdjust_Acct */
    public static final String COLUMNNAME_W_InvActualAdjust_Acct = "W_InvActualAdjust_Acct";

	/** Set Inventory Adjustment.
	  * Account for Inventory value adjustments for Actual Costing
	  */
	public void setW_InvActualAdjust_Acct (int W_InvActualAdjust_Acct);

	/** Get Inventory Adjustment.
	  * Account for Inventory value adjustments for Actual Costing
	  */
	public int getW_InvActualAdjust_Acct();

    /** Column name W_Inventory_Acct */
    public static final String COLUMNNAME_W_Inventory_Acct = "W_Inventory_Acct";

	/** Set Warehouse Inventory.
	  * Warehouse Inventory Asset Account - Currently not used
	  */
	public void setW_Inventory_Acct (int W_Inventory_Acct);

	/** Get Warehouse Inventory.
	  * Warehouse Inventory Asset Account - Currently not used
	  */
	public int getW_Inventory_Acct();

    /** Column name W_Revaluation_Acct */
    public static final String COLUMNNAME_W_Revaluation_Acct = "W_Revaluation_Acct";

	/** Set Inventory Revaluation.
	  * Account for Inventory Revaluation
	  */
	public void setW_Revaluation_Acct (int W_Revaluation_Acct);

	/** Get Inventory Revaluation.
	  * Account for Inventory Revaluation
	  */
	public int getW_Revaluation_Acct();
}
