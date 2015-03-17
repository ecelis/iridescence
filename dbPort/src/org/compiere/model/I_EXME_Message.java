/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Message
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Message 
{

    /** TableName=EXME_Message */
    public static final String Table_Name = "EXME_Message";

    /** AD_Table_ID=1201361 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name EXME_Conversation_ID */
    public static final String COLUMNNAME_EXME_Conversation_ID = "EXME_Conversation_ID";

	/** Set Conversation	  */
	public void setEXME_Conversation_ID (int EXME_Conversation_ID);

	/** Get Conversation	  */
	public int getEXME_Conversation_ID();

    /** Column name EXME_Message_ID */
    public static final String COLUMNNAME_EXME_Message_ID = "EXME_Message_ID";

	/** Set Message	  */
	public void setEXME_Message_ID (int EXME_Message_ID);

	/** Get Message	  */
	public int getEXME_Message_ID();

    /** Column name Message */
    public static final String COLUMNNAME_Message = "Message";

	/** Set Message.
	  * EMail Message
	  */
	public void setMessage (String Message);

	/** Get Message.
	  * EMail Message
	  */
	public String getMessage();

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

    /** Column name Viewed */
    public static final String COLUMNNAME_Viewed = "Viewed";

	/** Set Viewed	  */
	public void setViewed (boolean Viewed);

	/** Get Viewed	  */
	public boolean isViewed();
}
