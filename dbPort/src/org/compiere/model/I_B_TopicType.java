/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for B_TopicType
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_B_TopicType 
{

    /** TableName=B_TopicType */
    public static final String Table_Name = "B_TopicType";

    /** AD_Table_ID=690 */
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

    /** Column name AuctionType */
    public static final String COLUMNNAME_AuctionType = "AuctionType";

	/** Set Auction Type	  */
	public void setAuctionType (String AuctionType);

	/** Get Auction Type	  */
	public String getAuctionType();

    /** Column name B_TopicType_ID */
    public static final String COLUMNNAME_B_TopicType_ID = "B_TopicType_ID";

	/** Set Topic Type.
	  * Auction Topic Type
	  */
	public void setB_TopicType_ID (int B_TopicType_ID);

	/** Get Topic Type.
	  * Auction Topic Type
	  */
	public int getB_TopicType_ID();

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

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public I_M_PriceList getM_PriceList() throws RuntimeException;

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

    /** Column name M_ProductMember_ID */
    public static final String COLUMNNAME_M_ProductMember_ID = "M_ProductMember_ID";

	/** Set Membership.
	  * Product used to deternine the price of the membership for the topic type
	  */
	public void setM_ProductMember_ID (int M_ProductMember_ID);

	/** Get Membership.
	  * Product used to deternine the price of the membership for the topic type
	  */
	public int getM_ProductMember_ID();

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
}