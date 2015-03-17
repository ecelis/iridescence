/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_CostQueue
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_CostQueue 
{

    /** TableName=M_CostQueue */
    public static final String Table_Name = "M_CostQueue";

    /** AD_Table_ID=817 */
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

    /** Column name CurrentCostPrice */
    public static final String COLUMNNAME_CurrentCostPrice = "CurrentCostPrice";

	/** Set Current Cost Price.
	  * The currently used cost price
	  */
	public void setCurrentCostPrice (BigDecimal CurrentCostPrice);

	/** Get Current Cost Price.
	  * The currently used cost price
	  */
	public BigDecimal getCurrentCostPrice();

    /** Column name CurrentQty */
    public static final String COLUMNNAME_CurrentQty = "CurrentQty";

	/** Set Current Quantity.
	  * Current Quantity
	  */
	public void setCurrentQty (BigDecimal CurrentQty);

	/** Get Current Quantity.
	  * Current Quantity
	  */
	public BigDecimal getCurrentQty();

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

    /** Column name M_CostElement_ID */
    public static final String COLUMNNAME_M_CostElement_ID = "M_CostElement_ID";

	/** Set Cost Element.
	  * Product Cost Element
	  */
	public void setM_CostElement_ID (int M_CostElement_ID);

	/** Get Cost Element.
	  * Product Cost Element
	  */
	public int getM_CostElement_ID();

	public I_M_CostElement getM_CostElement() throws RuntimeException;

    /** Column name M_CostQueue_ID */
    public static final String COLUMNNAME_M_CostQueue_ID = "M_CostQueue_ID";

	/** Set Cost Queue.
	  * FiFo/LiFo Cost Queue
	  */
	public void setM_CostQueue_ID (int M_CostQueue_ID);

	/** Get Cost Queue.
	  * FiFo/LiFo Cost Queue
	  */
	public int getM_CostQueue_ID();

    /** Column name M_CostType_ID */
    public static final String COLUMNNAME_M_CostType_ID = "M_CostType_ID";

	/** Set Cost Type.
	  * Type of Cost (e.g. Current, Plan, Future)
	  */
	public void setM_CostType_ID (int M_CostType_ID);

	/** Get Cost Type.
	  * Type of Cost (e.g. Current, Plan, Future)
	  */
	public int getM_CostType_ID();

	public I_M_CostType getM_CostType() throws RuntimeException;

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
}
