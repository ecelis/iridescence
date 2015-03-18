/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_RelatedProduct
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_M_RelatedProduct 
{

    /** TableName=M_RelatedProduct */
    public static final String Table_Name = "M_RelatedProduct";

    /** AD_Table_ID=662 */
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

    /** Column name RelatedProduct_ID */
    public static final String COLUMNNAME_RelatedProduct_ID = "RelatedProduct_ID";

	/** Set Related Product.
	  * Related Product
	  */
	public void setRelatedProduct_ID (int RelatedProduct_ID);

	/** Get Related Product.
	  * Related Product
	  */
	public int getRelatedProduct_ID();

    /** Column name RelatedProductType */
    public static final String COLUMNNAME_RelatedProductType = "RelatedProductType";

	/** Set Related Product Type	  */
	public void setRelatedProductType (String RelatedProductType);

	/** Get Related Product Type	  */
	public String getRelatedProductType();
}