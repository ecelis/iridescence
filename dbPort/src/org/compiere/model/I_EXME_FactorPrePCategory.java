/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FactorPrePCategory
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_FactorPrePCategory 
{

    /** TableName=EXME_FactorPrePCategory */
    public static final String Table_Name = "EXME_FactorPrePCategory";

    /** AD_Table_ID=1201228 */
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

    /** Column name EXME_FactorPre_ID */
    public static final String COLUMNNAME_EXME_FactorPre_ID = "EXME_FactorPre_ID";

	/** Set Price Factor.
	  * Sales Price Factor
	  */
	public void setEXME_FactorPre_ID (int EXME_FactorPre_ID);

	/** Get Price Factor.
	  * Sales Price Factor
	  */
	public int getEXME_FactorPre_ID();

	public I_EXME_FactorPre getEXME_FactorPre() throws RuntimeException;

    /** Column name EXME_FactorPrePCategory_ID */
    public static final String COLUMNNAME_EXME_FactorPrePCategory_ID = "EXME_FactorPrePCategory_ID";

	/** Set Price increase rules – category.
	  * Price increase rules – category
	  */
	public void setEXME_FactorPrePCategory_ID (int EXME_FactorPrePCategory_ID);

	/** Get Price increase rules – category.
	  * Price increase rules – category
	  */
	public int getEXME_FactorPrePCategory_ID();

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
}
