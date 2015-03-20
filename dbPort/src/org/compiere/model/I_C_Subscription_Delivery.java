/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Subscription_Delivery
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_Subscription_Delivery 
{

    /** TableName=C_Subscription_Delivery */
    public static final String Table_Name = "C_Subscription_Delivery";

    /** AD_Table_ID=667 */
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

    /** Column name C_Subscription_Delivery_ID */
    public static final String COLUMNNAME_C_Subscription_Delivery_ID = "C_Subscription_Delivery_ID";

	/** Set Subscription Delivery.
	  * Optional Delivery Record for a Subscription
	  */
	public void setC_Subscription_Delivery_ID (int C_Subscription_Delivery_ID);

	/** Get Subscription Delivery.
	  * Optional Delivery Record for a Subscription
	  */
	public int getC_Subscription_Delivery_ID();

    /** Column name C_Subscription_ID */
    public static final String COLUMNNAME_C_Subscription_ID = "C_Subscription_ID";

	/** Set Subscription.
	  * Subscription of a Business Partner of a Product to renew
	  */
	public void setC_Subscription_ID (int C_Subscription_ID);

	/** Get Subscription.
	  * Subscription of a Business Partner of a Product to renew
	  */
	public int getC_Subscription_ID();

	public I_C_Subscription getC_Subscription() throws RuntimeException;
}
