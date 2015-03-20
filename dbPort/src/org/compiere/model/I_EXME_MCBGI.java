/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_MCBGI
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_MCBGI 
{

    /** TableName=EXME_MCBGI */
    public static final String Table_Name = "EXME_MCBGI";

    /** AD_Table_ID=1200072 */
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

    /** Column name EXME_MCBGI_ID */
    public static final String COLUMNNAME_EXME_MCBGI_ID = "EXME_MCBGI_ID";

	/** Set EXME_MCBGI_ID.
	  * Generic Medicine Maintenance
	  */
	public void setEXME_MCBGI_ID (int EXME_MCBGI_ID);

	/** Get EXME_MCBGI_ID.
	  * Generic Medicine Maintenance
	  */
	public int getEXME_MCBGI_ID();

    /** Column name M_ProductGI_ID */
    public static final String COLUMNNAME_M_ProductGI_ID = "M_ProductGI_ID";

	/** Set Basic Medicine.
	  * Basic Medicine
	  */
	public void setM_ProductGI_ID (int M_ProductGI_ID);

	/** Get Basic Medicine.
	  * Basic Medicine
	  */
	public int getM_ProductGI_ID();

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
