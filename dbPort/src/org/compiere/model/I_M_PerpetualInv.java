/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_PerpetualInv
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_PerpetualInv 
{

    /** TableName=M_PerpetualInv */
    public static final String Table_Name = "M_PerpetualInv";

    /** AD_Table_ID=342 */
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

    /** Column name CountHighMovement */
    public static final String COLUMNNAME_CountHighMovement = "CountHighMovement";

	/** Set Count high turnover items.
	  * Count High Movement products
	  */
	public void setCountHighMovement (boolean CountHighMovement);

	/** Get Count high turnover items.
	  * Count High Movement products
	  */
	public boolean isCountHighMovement();

    /** Column name DateLastRun */
    public static final String COLUMNNAME_DateLastRun = "DateLastRun";

	/** Set Date last run.
	  * Date the process was last run.
	  */
	public void setDateLastRun (Timestamp DateLastRun);

	/** Get Date last run.
	  * Date the process was last run.
	  */
	public Timestamp getDateLastRun();

    /** Column name DateNextRun */
    public static final String COLUMNNAME_DateNextRun = "DateNextRun";

	/** Set Date next run.
	  * Date the process will run next
	  */
	public void setDateNextRun (Timestamp DateNextRun);

	/** Get Date next run.
	  * Date the process will run next
	  */
	public Timestamp getDateNextRun();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name M_PerpetualInv_ID */
    public static final String COLUMNNAME_M_PerpetualInv_ID = "M_PerpetualInv_ID";

	/** Set Perpetual Inventory.
	  * Rules for generating physical inventory
	  */
	public void setM_PerpetualInv_ID (int M_PerpetualInv_ID);

	/** Get Perpetual Inventory.
	  * Rules for generating physical inventory
	  */
	public int getM_PerpetualInv_ID();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public I_M_Product_Category getM_Product_Category() throws RuntimeException;

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NoInventoryCount */
    public static final String COLUMNNAME_NoInventoryCount = "NoInventoryCount";

	/** Set Number of Inventory counts.
	  * Frequency of inventory counts per year
	  */
	public void setNoInventoryCount (int NoInventoryCount);

	/** Get Number of Inventory counts.
	  * Frequency of inventory counts per year
	  */
	public int getNoInventoryCount();

    /** Column name NoProductCount */
    public static final String COLUMNNAME_NoProductCount = "NoProductCount";

	/** Set Number of Product counts.
	  * Frequency of product counts per year
	  */
	public void setNoProductCount (int NoProductCount);

	/** Get Number of Product counts.
	  * Frequency of product counts per year
	  */
	public int getNoProductCount();

    /** Column name NumberOfRuns */
    public static final String COLUMNNAME_NumberOfRuns = "NumberOfRuns";

	/** Set Number of runs.
	  * Frequency of processing Perpetual Inventory
	  */
	public void setNumberOfRuns (int NumberOfRuns);

	/** Get Number of runs.
	  * Frequency of processing Perpetual Inventory
	  */
	public int getNumberOfRuns();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
