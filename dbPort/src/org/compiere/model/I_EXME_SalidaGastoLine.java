/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_SalidaGastoLine
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_SalidaGastoLine 
{

    /** TableName=EXME_SalidaGastoLine */
    public static final String Table_Name = "EXME_SalidaGastoLine";

    /** AD_Table_ID=1200205 */
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

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name CostAverage */
    public static final String COLUMNNAME_CostAverage = "CostAverage";

	/** Set Average Cost.
	  * Weighted average costs
	  */
	public void setCostAverage (BigDecimal CostAverage);

	/** Get Average Cost.
	  * Weighted average costs
	  */
	public BigDecimal getCostAverage();

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

    /** Column name EXME_SalidaGasto_ID */
    public static final String COLUMNNAME_EXME_SalidaGasto_ID = "EXME_SalidaGasto_ID";

	/** Set Internal Use Inventory	  */
	public void setEXME_SalidaGasto_ID (int EXME_SalidaGasto_ID);

	/** Get Internal Use Inventory	  */
	public int getEXME_SalidaGasto_ID();

	public I_EXME_SalidaGasto getEXME_SalidaGasto() throws RuntimeException;

    /** Column name EXME_SalidaGastoLine_ID */
    public static final String COLUMNNAME_EXME_SalidaGastoLine_ID = "EXME_SalidaGastoLine_ID";

	/** Set Internal Use Inventory Line	  */
	public void setEXME_SalidaGastoLine_ID (int EXME_SalidaGastoLine_ID);

	/** Get Internal Use Inventory Line	  */
	public int getEXME_SalidaGastoLine_ID();

    /** Column name InventoryType */
    public static final String COLUMNNAME_InventoryType = "InventoryType";

	/** Set Inventory Type.
	  * Type of inventory difference
	  */
	public void setInventoryType (String InventoryType);

	/** Get Inventory Type.
	  * Type of inventory difference
	  */
	public String getInventoryType();

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

    /** Column name OriginalQty */
    public static final String COLUMNNAME_OriginalQty = "OriginalQty";

	/** Set Original Quantity	  */
	public void setOriginalQty (BigDecimal OriginalQty);

	/** Get Original Quantity	  */
	public BigDecimal getOriginalQty();

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

    /** Column name QtyBook */
    public static final String COLUMNNAME_QtyBook = "QtyBook";

	/** Set Quantity book.
	  * Book Quantity
	  */
	public void setQtyBook (BigDecimal QtyBook);

	/** Get Quantity book.
	  * Book Quantity
	  */
	public BigDecimal getQtyBook();

    /** Column name QtyCount */
    public static final String COLUMNNAME_QtyCount = "QtyCount";

	/** Set Quantity count.
	  * Counted Quantity
	  */
	public void setQtyCount (BigDecimal QtyCount);

	/** Get Quantity count.
	  * Counted Quantity
	  */
	public BigDecimal getQtyCount();

    /** Column name QtyInternalUse */
    public static final String COLUMNNAME_QtyInternalUse = "QtyInternalUse";

	/** Set Internal Use Qty.
	  * Internal Use Quantity removed from Inventory
	  */
	public void setQtyInternalUse (BigDecimal QtyInternalUse);

	/** Get Internal Use Qty.
	  * Internal Use Quantity removed from Inventory
	  */
	public BigDecimal getQtyInternalUse();
}
