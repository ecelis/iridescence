/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_PromotionGroupLine
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_PromotionGroupLine 
{

    /** TableName=M_PromotionGroupLine */
    public static final String Table_Name = "M_PromotionGroupLine";

    /** AD_Table_ID=1200673 */
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

    /** Column name M_PromotionGroup_ID */
    public static final String COLUMNNAME_M_PromotionGroup_ID = "M_PromotionGroup_ID";

	/** Set Promotion Group	  */
	public void setM_PromotionGroup_ID (int M_PromotionGroup_ID);

	/** Get Promotion Group	  */
	public int getM_PromotionGroup_ID();

	public I_M_PromotionGroup getM_PromotionGroup() throws RuntimeException;

    /** Column name M_PromotionGroupLine_ID */
    public static final String COLUMNNAME_M_PromotionGroupLine_ID = "M_PromotionGroupLine_ID";

	/** Set Promotion Group Line	  */
	public void setM_PromotionGroupLine_ID (int M_PromotionGroupLine_ID);

	/** Get Promotion Group Line	  */
	public int getM_PromotionGroupLine_ID();
}
