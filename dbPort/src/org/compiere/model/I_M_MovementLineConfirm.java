/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_MovementLineConfirm
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_M_MovementLineConfirm 
{

    /** TableName=M_MovementLineConfirm */
    public static final String Table_Name = "M_MovementLineConfirm";

    /** AD_Table_ID=737 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name ConfirmedQty */
    public static final String COLUMNNAME_ConfirmedQty = "ConfirmedQty";

	/** Set Confirmed Quantity.
	  * Confirmation of a received quantity
	  */
	public void setConfirmedQty (BigDecimal ConfirmedQty);

	/** Get Confirmed Quantity.
	  * Confirmation of a received quantity
	  */
	public BigDecimal getConfirmedQty();

    /** Column name ConfirmedQty_Vol */
    public static final String COLUMNNAME_ConfirmedQty_Vol = "ConfirmedQty_Vol";

	/** Set Confirmed Qty Pack.
	  * Confirmation of a received quantity in the Pack UOM
	  */
	public void setConfirmedQty_Vol (BigDecimal ConfirmedQty_Vol);

	/** Get Confirmed Qty Pack.
	  * Confirmation of a received quantity in the Pack UOM
	  */
	public BigDecimal getConfirmedQty_Vol();

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

    /** Column name DifferenceQty */
    public static final String COLUMNNAME_DifferenceQty = "DifferenceQty";

	/** Set Difference.
	  * Difference Quantity
	  */
	public void setDifferenceQty (BigDecimal DifferenceQty);

	/** Get Difference.
	  * Difference Quantity
	  */
	public BigDecimal getDifferenceQty();

    /** Column name DifferenceQty_Vol */
    public static final String COLUMNNAME_DifferenceQty_Vol = "DifferenceQty_Vol";

	/** Set Difference Qty Pack	  */
	public void setDifferenceQty_Vol (BigDecimal DifferenceQty_Vol);

	/** Get Difference Qty Pack	  */
	public BigDecimal getDifferenceQty_Vol();

    /** Column name M_InventoryLine_ID */
    public static final String COLUMNNAME_M_InventoryLine_ID = "M_InventoryLine_ID";

	/** Set Phys.Inventory Line.
	  * Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID);

	/** Get Phys.Inventory Line.
	  * Unique line in an Inventory document
	  */
	public int getM_InventoryLine_ID();

	public I_M_InventoryLine getM_InventoryLine() throws RuntimeException;

    /** Column name M_MovementConfirm_ID */
    public static final String COLUMNNAME_M_MovementConfirm_ID = "M_MovementConfirm_ID";

	/** Set Move Confirm.
	  * Inventory Move Confirmation
	  */
	public void setM_MovementConfirm_ID (int M_MovementConfirm_ID);

	/** Get Move Confirm.
	  * Inventory Move Confirmation
	  */
	public int getM_MovementConfirm_ID();

	public I_M_MovementConfirm getM_MovementConfirm() throws RuntimeException;

    /** Column name M_MovementLineConfirm_ID */
    public static final String COLUMNNAME_M_MovementLineConfirm_ID = "M_MovementLineConfirm_ID";

	/** Set Move Line Confirm.
	  * Inventory Move Line Confirmation
	  */
	public void setM_MovementLineConfirm_ID (int M_MovementLineConfirm_ID);

	/** Get Move Line Confirm.
	  * Inventory Move Line Confirmation
	  */
	public int getM_MovementLineConfirm_ID();

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

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name ScrappedQty */
    public static final String COLUMNNAME_ScrappedQty = "ScrappedQty";

	/** Set Scrapped Quantity.
	  * The Quantity scrapped due to QA issues
	  */
	public void setScrappedQty (BigDecimal ScrappedQty);

	/** Get Scrapped Quantity.
	  * The Quantity scrapped due to QA issues
	  */
	public BigDecimal getScrappedQty();

    /** Column name ScrappedQty_Vol */
    public static final String COLUMNNAME_ScrappedQty_Vol = "ScrappedQty_Vol";

	/** Set Scrapped Qty Pack.
	  * The Quantity scrapped due to QA issues in UOM Pack
	  */
	public void setScrappedQty_Vol (BigDecimal ScrappedQty_Vol);

	/** Get Scrapped Qty Pack.
	  * The Quantity scrapped due to QA issues in UOM Pack
	  */
	public BigDecimal getScrappedQty_Vol();

    /** Column name TargetQty */
    public static final String COLUMNNAME_TargetQty = "TargetQty";

	/** Set Target Quantity.
	  * Target Movement Quantity
	  */
	public void setTargetQty (BigDecimal TargetQty);

	/** Get Target Quantity.
	  * Target Movement Quantity
	  */
	public BigDecimal getTargetQty();

    /** Column name TargetQty_Vol */
    public static final String COLUMNNAME_TargetQty_Vol = "TargetQty_Vol";

	/** Set Target Qty Pack.
	  * Target Quantity Pack
	  */
	public void setTargetQty_Vol (BigDecimal TargetQty_Vol);

	/** Get Target Qty Pack.
	  * Target Quantity Pack
	  */
	public BigDecimal getTargetQty_Vol();
}
