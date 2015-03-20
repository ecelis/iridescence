/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for B_TopicCategory
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_B_TopicCategory 
{

    /** TableName=B_TopicCategory */
    public static final String Table_Name = "B_TopicCategory";

    /** AD_Table_ID=691 */
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

    /** Column name B_TopicCategory_ID */
    public static final String COLUMNNAME_B_TopicCategory_ID = "B_TopicCategory_ID";

	/** Set Topic Category.
	  * Auction Topic Category
	  */
	public void setB_TopicCategory_ID (int B_TopicCategory_ID);

	/** Get Topic Category.
	  * Auction Topic Category
	  */
	public int getB_TopicCategory_ID();

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

	public I_B_TopicType getB_TopicType() throws RuntimeException;

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
