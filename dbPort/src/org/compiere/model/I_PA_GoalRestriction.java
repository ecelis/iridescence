/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PA_GoalRestriction
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PA_GoalRestriction 
{

    /** TableName=PA_GoalRestriction */
    public static final String Table_Name = "PA_GoalRestriction";

    /** AD_Table_ID=832 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifier for a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public I_C_BP_Group getC_BP_Group() throws RuntimeException;

    /** Column name GoalRestrictionType */
    public static final String COLUMNNAME_GoalRestrictionType = "GoalRestrictionType";

	/** Set Restriction Type.
	  * Goal Restriction Type
	  */
	public void setGoalRestrictionType (String GoalRestrictionType);

	/** Get Restriction Type.
	  * Goal Restriction Type
	  */
	public String getGoalRestrictionType();

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

    /** Column name Org_ID */
    public static final String COLUMNNAME_Org_ID = "Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setOrg_ID (int Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getOrg_ID();

    /** Column name PA_Goal_ID */
    public static final String COLUMNNAME_PA_Goal_ID = "PA_Goal_ID";

	/** Set Goal.
	  * Performance Goal
	  */
	public void setPA_Goal_ID (int PA_Goal_ID);

	/** Get Goal.
	  * Performance Goal
	  */
	public int getPA_Goal_ID();

	public I_PA_Goal getPA_Goal() throws RuntimeException;

    /** Column name PA_GoalRestriction_ID */
    public static final String COLUMNNAME_PA_GoalRestriction_ID = "PA_GoalRestriction_ID";

	/** Set Goal Restriction.
	  * Performance Goal Restriction
	  */
	public void setPA_GoalRestriction_ID (int PA_GoalRestriction_ID);

	/** Get Goal Restriction.
	  * Performance Goal Restriction
	  */
	public int getPA_GoalRestriction_ID();
}
