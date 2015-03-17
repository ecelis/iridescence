/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for GL_Category
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_GL_Category 
{

    /** TableName=GL_Category */
    public static final String Table_Name = "GL_Category";

    /** AD_Table_ID=218 */
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

    /** Column name CategoryType */
    public static final String COLUMNNAME_CategoryType = "CategoryType";

	/** Set Category Type.
	  * Source of the Journal with this category
	  */
	public void setCategoryType (String CategoryType);

	/** Get Category Type.
	  * Source of the Journal with this category
	  */
	public String getCategoryType();

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

    /** Column name GL_Category_ID */
    public static final String COLUMNNAME_GL_Category_ID = "GL_Category_ID";

	/** Set GL Category.
	  * General Ledger Category
	  */
	public void setGL_Category_ID (int GL_Category_ID);

	/** Get GL Category.
	  * General Ledger Category
	  */
	public int getGL_Category_ID();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name ItemName */
    public static final String COLUMNNAME_ItemName = "ItemName";

	/** Set Print Item Name	  */
	public void setItemName (String ItemName);

	/** Get Print Item Name	  */
	public String getItemName();

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

    /** Column name PolicyType */
    public static final String COLUMNNAME_PolicyType = "PolicyType";

	/** Set Policy Type	  */
	public void setPolicyType (String PolicyType);

	/** Get Policy Type	  */
	public String getPolicyType();
}
