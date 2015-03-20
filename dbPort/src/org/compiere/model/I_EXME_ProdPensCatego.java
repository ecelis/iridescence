/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProdPensCatego
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ProdPensCatego 
{

    /** TableName=EXME_ProdPensCatego */
    public static final String Table_Name = "EXME_ProdPensCatego";

    /** AD_Table_ID=1200246 */
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

    /** Column name EXME_Pension_Category_ID */
    public static final String COLUMNNAME_EXME_Pension_Category_ID = "EXME_Pension_Category_ID";

	/** Set Pension Category	  */
	public void setEXME_Pension_Category_ID (int EXME_Pension_Category_ID);

	/** Get Pension Category	  */
	public int getEXME_Pension_Category_ID();

	public I_EXME_Pension_Category getEXME_Pension_Category() throws RuntimeException;

    /** Column name EXME_ProdPensCatego_ID */
    public static final String COLUMNNAME_EXME_ProdPensCatego_ID = "EXME_ProdPensCatego_ID";

	/** Set Product Pension Category	  */
	public void setEXME_ProdPensCatego_ID (int EXME_ProdPensCatego_ID);

	/** Get Product Pension Category	  */
	public int getEXME_ProdPensCatego_ID();

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
