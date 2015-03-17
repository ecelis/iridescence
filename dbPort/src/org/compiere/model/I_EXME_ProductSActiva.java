/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ProductSActiva
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_ProductSActiva 
{

    /** TableName=EXME_ProductSActiva */
    public static final String Table_Name = "EXME_ProductSActiva";

    /** AD_Table_ID=1000065 */
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

    /** Column name EXME_ProductSActiva_ID */
    public static final String COLUMNNAME_EXME_ProductSActiva_ID = "EXME_ProductSActiva_ID";

	/** Set Product - Active Substance.
	  * Product - Active Substance
	  */
	public void setEXME_ProductSActiva_ID (int EXME_ProductSActiva_ID);

	/** Get Product - Active Substance.
	  * Product - Active Substance
	  */
	public int getEXME_ProductSActiva_ID();

    /** Column name EXME_SActiva_ID */
    public static final String COLUMNNAME_EXME_SActiva_ID = "EXME_SActiva_ID";

	/** Set Active Substance.
	  * Active Substance
	  */
	public void setEXME_SActiva_ID (int EXME_SActiva_ID);

	/** Get Active Substance.
	  * Active Substance
	  */
	public int getEXME_SActiva_ID();

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
