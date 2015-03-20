/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for B_SellerFunds
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_B_SellerFunds 
{

    /** TableName=B_SellerFunds */
    public static final String Table_Name = "B_SellerFunds";

    /** AD_Table_ID=680 */
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

    /** Column name B_SellerFunds_ID */
    public static final String COLUMNNAME_B_SellerFunds_ID = "B_SellerFunds_ID";

	/** Set Seller Funds.
	  * Seller Funds from Offers on Topics
	  */
	public void setB_SellerFunds_ID (int B_SellerFunds_ID);

	/** Get Seller Funds.
	  * Seller Funds from Offers on Topics
	  */
	public int getB_SellerFunds_ID();

    /** Column name CommittedAmt */
    public static final String COLUMNNAME_CommittedAmt = "CommittedAmt";

	/** Set Committed Amount.
	  * The (legal) commitment amount
	  */
	public void setCommittedAmt (BigDecimal CommittedAmt);

	/** Get Committed Amount.
	  * The (legal) commitment amount
	  */
	public BigDecimal getCommittedAmt();

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public I_C_Order getC_Order() throws RuntimeException;

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public I_C_Payment getC_Payment() throws RuntimeException;

    /** Column name NonCommittedAmt */
    public static final String COLUMNNAME_NonCommittedAmt = "NonCommittedAmt";

	/** Set Not Committed Aount.
	  * Amount not committed yet
	  */
	public void setNonCommittedAmt (BigDecimal NonCommittedAmt);

	/** Get Not Committed Aount.
	  * Amount not committed yet
	  */
	public BigDecimal getNonCommittedAmt();
}
