/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for K_Type
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_K_Type 
{

    /** TableName=K_Type */
    public static final String Table_Name = "K_Type";

    /** AD_Table_ID=606 */
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

    /** Column name IsPublic */
    public static final String COLUMNNAME_IsPublic = "IsPublic";

	/** Set Public.
	  * Public can read entry
	  */
	public void setIsPublic (boolean IsPublic);

	/** Get Public.
	  * Public can read entry
	  */
	public boolean isPublic();

    /** Column name IsPublicWrite */
    public static final String COLUMNNAME_IsPublicWrite = "IsPublicWrite";

	/** Set Public Write.
	  * Public can write entries
	  */
	public void setIsPublicWrite (boolean IsPublicWrite);

	/** Get Public Write.
	  * Public can write entries
	  */
	public boolean isPublicWrite();

    /** Column name K_Type_ID */
    public static final String COLUMNNAME_K_Type_ID = "K_Type_ID";

	/** Set Knowldge Type.
	  * Knowledge Type
	  */
	public void setK_Type_ID (int K_Type_ID);

	/** Get Knowldge Type.
	  * Knowledge Type
	  */
	public int getK_Type_ID();

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
