/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MergeLexi
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_MergeLexi 
{

    /** TableName=EXME_MergeLexi */
    public static final String Table_Name = "EXME_MergeLexi";

    /** AD_Table_ID=1201378 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

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

    /** Column name EXME_MergeLexi_ID */
    public static final String COLUMNNAME_EXME_MergeLexi_ID = "EXME_MergeLexi_ID";

	/** Set EXME_MergeLexi_ID	  */
	public void setEXME_MergeLexi_ID (int EXME_MergeLexi_ID);

	/** Get EXME_MergeLexi_ID	  */
	public int getEXME_MergeLexi_ID();

    /** Column name Merged */
    public static final String COLUMNNAME_Merged = "Merged";

	/** Set Merged	  */
	public void setMerged (String Merged);

	/** Get Merged	  */
	public String getMerged();

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

    /** Column name M_Product_Lexi_ID */
    public static final String COLUMNNAME_M_Product_Lexi_ID = "M_Product_Lexi_ID";

	/** Set M_Product_Lexi_ID	  */
	public void setM_Product_Lexi_ID (int M_Product_Lexi_ID);

	/** Get M_Product_Lexi_ID	  */
	public int getM_Product_Lexi_ID();
}
