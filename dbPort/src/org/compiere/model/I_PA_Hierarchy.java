/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for PA_Hierarchy
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_PA_Hierarchy 
{

    /** TableName=PA_Hierarchy */
    public static final String Table_Name = "PA_Hierarchy";

    /** AD_Table_ID=821 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name AD_Tree_Account_ID */
    public static final String COLUMNNAME_AD_Tree_Account_ID = "AD_Tree_Account_ID";

	/** Set Account Tree.
	  * Tree for Natural Account Tree
	  */
	public void setAD_Tree_Account_ID (int AD_Tree_Account_ID);

	/** Get Account Tree.
	  * Tree for Natural Account Tree
	  */
	public int getAD_Tree_Account_ID();

    /** Column name AD_Tree_Activity_ID */
    public static final String COLUMNNAME_AD_Tree_Activity_ID = "AD_Tree_Activity_ID";

	/** Set Activity Tree.
	  * Tree to determine activity hierarchy
	  */
	public void setAD_Tree_Activity_ID (int AD_Tree_Activity_ID);

	/** Get Activity Tree.
	  * Tree to determine activity hierarchy
	  */
	public int getAD_Tree_Activity_ID();

    /** Column name AD_Tree_BPartner_ID */
    public static final String COLUMNNAME_AD_Tree_BPartner_ID = "AD_Tree_BPartner_ID";

	/** Set BPartner Tree.
	  * Tree to determine business partner hierarchy
	  */
	public void setAD_Tree_BPartner_ID (int AD_Tree_BPartner_ID);

	/** Get BPartner Tree.
	  * Tree to determine business partner hierarchy
	  */
	public int getAD_Tree_BPartner_ID();

    /** Column name AD_Tree_Campaign_ID */
    public static final String COLUMNNAME_AD_Tree_Campaign_ID = "AD_Tree_Campaign_ID";

	/** Set Campaign Tree.
	  * Tree to determine marketing campaign hierarchy
	  */
	public void setAD_Tree_Campaign_ID (int AD_Tree_Campaign_ID);

	/** Get Campaign Tree.
	  * Tree to determine marketing campaign hierarchy
	  */
	public int getAD_Tree_Campaign_ID();

    /** Column name AD_Tree_Org_ID */
    public static final String COLUMNNAME_AD_Tree_Org_ID = "AD_Tree_Org_ID";

	/** Set Organization Tree.
	  * Tree to determine organizational hierarchy
	  */
	public void setAD_Tree_Org_ID (int AD_Tree_Org_ID);

	/** Get Organization Tree.
	  * Tree to determine organizational hierarchy
	  */
	public int getAD_Tree_Org_ID();

    /** Column name AD_Tree_OrgTrx_ID */
    public static final String COLUMNNAME_AD_Tree_OrgTrx_ID = "AD_Tree_OrgTrx_ID";

	/** Set Transactional Organization Tree	  */
	public void setAD_Tree_OrgTrx_ID (int AD_Tree_OrgTrx_ID);

	/** Get Transactional Organization Tree	  */
	public int getAD_Tree_OrgTrx_ID();

    /** Column name AD_Tree_Product_ID */
    public static final String COLUMNNAME_AD_Tree_Product_ID = "AD_Tree_Product_ID";

	/** Set Product Tree.
	  * Tree to determine product hierarchy
	  */
	public void setAD_Tree_Product_ID (int AD_Tree_Product_ID);

	/** Get Product Tree.
	  * Tree to determine product hierarchy
	  */
	public int getAD_Tree_Product_ID();

    /** Column name AD_Tree_Project_ID */
    public static final String COLUMNNAME_AD_Tree_Project_ID = "AD_Tree_Project_ID";

	/** Set Project Tree.
	  * Tree to determine project hierarchy
	  */
	public void setAD_Tree_Project_ID (int AD_Tree_Project_ID);

	/** Get Project Tree.
	  * Tree to determine project hierarchy
	  */
	public int getAD_Tree_Project_ID();

    /** Column name AD_Tree_SalesRegion_ID */
    public static final String COLUMNNAME_AD_Tree_SalesRegion_ID = "AD_Tree_SalesRegion_ID";

	/** Set Sales Region Tree.
	  * Tree to determine sales regional hierarchy
	  */
	public void setAD_Tree_SalesRegion_ID (int AD_Tree_SalesRegion_ID);

	/** Get Sales Region Tree.
	  * Tree to determine sales regional hierarchy
	  */
	public int getAD_Tree_SalesRegion_ID();

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

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

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

    /** Column name PA_Hierarchy_ID */
    public static final String COLUMNNAME_PA_Hierarchy_ID = "PA_Hierarchy_ID";

	/** Set Reporting Hierarchy.
	  * Optional Reporting Hierarchy - If not selected the default hierarchy trees are used.
	  */
	public void setPA_Hierarchy_ID (int PA_Hierarchy_ID);

	/** Get Reporting Hierarchy.
	  * Optional Reporting Hierarchy - If not selected the default hierarchy trees are used.
	  */
	public int getPA_Hierarchy_ID();
}
