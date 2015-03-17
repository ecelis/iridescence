/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_UserAut
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_UserAut 
{

    /** TableName=EXME_UserAut */
    public static final String Table_Name = "EXME_UserAut";

    /** AD_Table_ID=1200525 */
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

    /** Column name AD_UserAut_ID */
    public static final String COLUMNNAME_AD_UserAut_ID = "AD_UserAut_ID";

	/** Set User Authorizes.
	  * User that Authorizes
	  */
	public void setAD_UserAut_ID (int AD_UserAut_ID);

	/** Get User Authorizes.
	  * User that Authorizes
	  */
	public int getAD_UserAut_ID();

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

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name EXME_UserAut_ID */
    public static final String COLUMNNAME_EXME_UserAut_ID = "EXME_UserAut_ID";

	/** Set User to Authorize.
	  * User that are going away to authorize
	  */
	public void setEXME_UserAut_ID (int EXME_UserAut_ID);

	/** Get User to Authorize.
	  * User that are going away to authorize
	  */
	public int getEXME_UserAut_ID();

    /** Column name ImporteMax */
    public static final String COLUMNNAME_ImporteMax = "ImporteMax";

	/** Set Max Amount	  */
	public void setImporteMax (int ImporteMax);

	/** Get Max Amount	  */
	public int getImporteMax();

    /** Column name ImporteMin */
    public static final String COLUMNNAME_ImporteMin = "ImporteMin";

	/** Set Min Amount	  */
	public void setImporteMin (int ImporteMin);

	/** Get Min Amount	  */
	public int getImporteMin();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();
}
