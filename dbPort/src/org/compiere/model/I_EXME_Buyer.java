/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_Buyer
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_Buyer 
{

    /** TableName=EXME_Buyer */
    public static final String Table_Name = "EXME_Buyer";

    /** AD_Table_ID=1201395 */
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

	public I_AD_User getAD_User() throws RuntimeException;

    /** Column name ad_usersuperior_id */
    public static final String COLUMNNAME_ad_usersuperior_id = "ad_usersuperior_id";

	/** Set ad_usersuperior_id	  */
	public void setad_usersuperior_id (int ad_usersuperior_id);

	/** Get ad_usersuperior_id	  */
	public int getad_usersuperior_id();

    /** Column name EXME_Buyer_ID */
    public static final String COLUMNNAME_EXME_Buyer_ID = "EXME_Buyer_ID";

	/** Set Buyer user	  */
	public void setEXME_Buyer_ID (int EXME_Buyer_ID);

	/** Get Buyer user	  */
	public int getEXME_Buyer_ID();

    /** Column name qtyauthorized */
    public static final String COLUMNNAME_qtyauthorized = "qtyauthorized";

	/** Set qtyauthorized	  */
	public void setqtyauthorized (BigDecimal qtyauthorized);

	/** Get qtyauthorized	  */
	public BigDecimal getqtyauthorized();
}
