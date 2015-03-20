/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_PromotionLine
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_PromotionLine 
{

    /** TableName=M_PromotionLine */
    public static final String Table_Name = "M_PromotionLine";

    /** AD_Table_ID=1200675 */
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

    /** Column name IsMandatoryPL */
    public static final String COLUMNNAME_IsMandatoryPL = "IsMandatoryPL";

	/** Set Mandatory Promotion Line.
	  * Order must have this promotion line
	  */
	public void setIsMandatoryPL (boolean IsMandatoryPL);

	/** Get Mandatory Promotion Line.
	  * Order must have this promotion line
	  */
	public boolean isMandatoryPL();

    /** Column name MinimumAmt */
    public static final String COLUMNNAME_MinimumAmt = "MinimumAmt";

	/** Set Minimum Amt.
	  * Minumum Amout in Document Currency
	  */
	public void setMinimumAmt (BigDecimal MinimumAmt);

	/** Get Minimum Amt.
	  * Minumum Amout in Document Currency
	  */
	public BigDecimal getMinimumAmt();

    /** Column name M_PromotionGroup_ID */
    public static final String COLUMNNAME_M_PromotionGroup_ID = "M_PromotionGroup_ID";

	/** Set Promotion Group	  */
	public void setM_PromotionGroup_ID (int M_PromotionGroup_ID);

	/** Get Promotion Group	  */
	public int getM_PromotionGroup_ID();

	public I_M_PromotionGroup getM_PromotionGroup() throws RuntimeException;

    /** Column name M_Promotion_ID */
    public static final String COLUMNNAME_M_Promotion_ID = "M_Promotion_ID";

	/** Set Promotion	  */
	public void setM_Promotion_ID (int M_Promotion_ID);

	/** Get Promotion	  */
	public int getM_Promotion_ID();

	public I_M_Promotion getM_Promotion() throws RuntimeException;

    /** Column name M_PromotionLine_ID */
    public static final String COLUMNNAME_M_PromotionLine_ID = "M_PromotionLine_ID";

	/** Set Promotion Line	  */
	public void setM_PromotionLine_ID (int M_PromotionLine_ID);

	/** Get Promotion Line	  */
	public int getM_PromotionLine_ID();
}
