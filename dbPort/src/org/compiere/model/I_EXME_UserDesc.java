/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_UserDesc
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_UserDesc 
{

    /** TableName=EXME_UserDesc */
    public static final String Table_Name = "EXME_UserDesc";

    /** AD_Table_ID=1200297 */
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

    /** Column name EXME_Descuentos_ID */
    public static final String COLUMNNAME_EXME_Descuentos_ID = "EXME_Descuentos_ID";

	/** Set Discount	  */
	public void setEXME_Descuentos_ID (int EXME_Descuentos_ID);

	/** Get Discount	  */
	public int getEXME_Descuentos_ID();

	public I_EXME_Descuentos getEXME_Descuentos() throws RuntimeException;

    /** Column name EXME_UserDesc_ID */
    public static final String COLUMNNAME_EXME_UserDesc_ID = "EXME_UserDesc_ID";

	/** Set User Discount 	  */
	public void setEXME_UserDesc_ID (int EXME_UserDesc_ID);

	/** Get User Discount 	  */
	public int getEXME_UserDesc_ID();

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
