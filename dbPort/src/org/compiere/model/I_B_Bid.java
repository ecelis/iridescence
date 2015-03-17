/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for B_Bid
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_B_Bid 
{

    /** TableName=B_Bid */
    public static final String Table_Name = "B_Bid";

    /** AD_Table_ID=686 */
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

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name B_Bid_ID */
    public static final String COLUMNNAME_B_Bid_ID = "B_Bid_ID";

	/** Set Bid.
	  * Bid for a Topic
	  */
	public void setB_Bid_ID (int B_Bid_ID);

	/** Get Bid.
	  * Bid for a Topic
	  */
	public int getB_Bid_ID();

    /** Column name B_BuyerFunds_ID */
    public static final String COLUMNNAME_B_BuyerFunds_ID = "B_BuyerFunds_ID";

	/** Set Buyer Funds.
	  * Buyer Funds for Bids on Topics
	  */
	public void setB_BuyerFunds_ID (int B_BuyerFunds_ID);

	/** Get Buyer Funds.
	  * Buyer Funds for Bids on Topics
	  */
	public int getB_BuyerFunds_ID();

	public I_B_BuyerFunds getB_BuyerFunds() throws RuntimeException;

    /** Column name B_Topic_ID */
    public static final String COLUMNNAME_B_Topic_ID = "B_Topic_ID";

	/** Set Topic.
	  * Auction Topic
	  */
	public void setB_Topic_ID (int B_Topic_ID);

	/** Get Topic.
	  * Auction Topic
	  */
	public int getB_Topic_ID();

	public I_B_Topic getB_Topic() throws RuntimeException;

    /** Column name IsWillingToCommit */
    public static final String COLUMNNAME_IsWillingToCommit = "IsWillingToCommit";

	/** Set Willing to commit	  */
	public void setIsWillingToCommit (boolean IsWillingToCommit);

	/** Get Willing to commit	  */
	public boolean isWillingToCommit();

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

    /** Column name PrivateNote */
    public static final String COLUMNNAME_PrivateNote = "PrivateNote";

	/** Set Private Note.
	  * Private Note - not visible to the other parties
	  */
	public void setPrivateNote (String PrivateNote);

	/** Get Private Note.
	  * Private Note - not visible to the other parties
	  */
	public String getPrivateNote();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();
}
