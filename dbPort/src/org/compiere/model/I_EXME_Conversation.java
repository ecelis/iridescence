/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Conversation
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Conversation 
{

    /** TableName=EXME_Conversation */
    public static final String Table_Name = "EXME_Conversation";

    /** AD_Table_ID=1201360 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact .
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

    /** Column name EXME_Conversation_ID */
    public static final String COLUMNNAME_EXME_Conversation_ID = "EXME_Conversation_ID";

	/** Set Conversation	  */
	public void setEXME_Conversation_ID (int EXME_Conversation_ID);

	/** Get Conversation	  */
	public int getEXME_Conversation_ID();

    /** Column name Locked */
    public static final String COLUMNNAME_Locked = "Locked";

	/** Set Locked.
	  * Whether the terminal is locked
	  */
	public void setLocked (boolean Locked);

	/** Get Locked.
	  * Whether the terminal is locked
	  */
	public boolean isLocked();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

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
