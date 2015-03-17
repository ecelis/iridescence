/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_PromotionReward
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_PromotionReward 
{

    /** TableName=M_PromotionReward */
    public static final String Table_Name = "M_PromotionReward";

    /** AD_Table_ID=1200678 */
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

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

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

    /** Column name DistributionSorting */
    public static final String COLUMNNAME_DistributionSorting = "DistributionSorting";

	/** Set Distribution Sorting.
	  * Quantity distribution sorting by unit price
	  */
	public void setDistributionSorting (String DistributionSorting);

	/** Get Distribution Sorting.
	  * Quantity distribution sorting by unit price
	  */
	public String getDistributionSorting();

    /** Column name IsForAllDistribution */
    public static final String COLUMNNAME_IsForAllDistribution = "IsForAllDistribution";

	/** Set For all distribution.
	  * This reward is for all distribution
	  */
	public void setIsForAllDistribution (boolean IsForAllDistribution);

	/** Get For all distribution.
	  * This reward is for all distribution
	  */
	public boolean isForAllDistribution();

    /** Column name IsSameDistribution */
    public static final String COLUMNNAME_IsSameDistribution = "IsSameDistribution";

	/** Set Same distribution for source and target.
	  * Use the same distribution for source and target
	  */
	public void setIsSameDistribution (boolean IsSameDistribution);

	/** Get Same distribution for source and target.
	  * Use the same distribution for source and target
	  */
	public boolean isSameDistribution();

    /** Column name M_PromotionDistribution_ID */
    public static final String COLUMNNAME_M_PromotionDistribution_ID = "M_PromotionDistribution_ID";

	/** Set Promotion Distribution	  */
	public void setM_PromotionDistribution_ID (int M_PromotionDistribution_ID);

	/** Get Promotion Distribution	  */
	public int getM_PromotionDistribution_ID();

	public I_M_PromotionDistribution getM_PromotionDistribution() throws RuntimeException;

    /** Column name M_Promotion_ID */
    public static final String COLUMNNAME_M_Promotion_ID = "M_Promotion_ID";

	/** Set Promotion	  */
	public void setM_Promotion_ID (int M_Promotion_ID);

	/** Get Promotion	  */
	public int getM_Promotion_ID();

	public I_M_Promotion getM_Promotion() throws RuntimeException;

    /** Column name M_PromotionReward_ID */
    public static final String COLUMNNAME_M_PromotionReward_ID = "M_PromotionReward_ID";

	/** Set Promotion Reward	  */
	public void setM_PromotionReward_ID (int M_PromotionReward_ID);

	/** Get Promotion Reward	  */
	public int getM_PromotionReward_ID();

    /** Column name M_TargetDistribution_ID */
    public static final String COLUMNNAME_M_TargetDistribution_ID = "M_TargetDistribution_ID";

	/** Set Target distribution.
	  * Get product from target distribution to apply the promotion reward
	  */
	public void setM_TargetDistribution_ID (int M_TargetDistribution_ID);

	/** Get Target distribution.
	  * Get product from target distribution to apply the promotion reward
	  */
	public int getM_TargetDistribution_ID();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name RewardType */
    public static final String COLUMNNAME_RewardType = "RewardType";

	/** Set Reward Type.
	  * Type of reward which consists of percentage discount, flat discount or absolute amount
	  */
	public void setRewardType (String RewardType);

	/** Get Reward Type.
	  * Type of reward which consists of percentage discount, flat discount or absolute amount
	  */
	public String getRewardType();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence Number.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();
}
