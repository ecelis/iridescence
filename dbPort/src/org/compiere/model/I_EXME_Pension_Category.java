/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Pension_Category
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_Pension_Category 
{

    /** TableName=EXME_Pension_Category */
    public static final String Table_Name = "EXME_Pension_Category";

    /** AD_Table_ID=1200216 */
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

    /** Column name Description_Subcategory */
    public static final String COLUMNNAME_Description_Subcategory = "Description_Subcategory";

	/** Set Subcategory Description	  */
	public void setDescription_Subcategory (String Description_Subcategory);

	/** Get Subcategory Description	  */
	public String getDescription_Subcategory();

    /** Column name EXME_Pension_Category_ID */
    public static final String COLUMNNAME_EXME_Pension_Category_ID = "EXME_Pension_Category_ID";

	/** Set Pension Category	  */
	public void setEXME_Pension_Category_ID (int EXME_Pension_Category_ID);

	/** Get Pension Category	  */
	public int getEXME_Pension_Category_ID();

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

    /** Column name Name_Subcategory */
    public static final String COLUMNNAME_Name_Subcategory = "Name_Subcategory";

	/** Set Subcategory Name	  */
	public void setName_Subcategory (String Name_Subcategory);

	/** Get Subcategory Name	  */
	public String getName_Subcategory();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
