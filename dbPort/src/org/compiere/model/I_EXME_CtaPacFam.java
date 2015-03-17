/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_CtaPacFam
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_CtaPacFam 
{

    /** TableName=EXME_CtaPacFam */
    public static final String Table_Name = "EXME_CtaPacFam";

    /** AD_Table_ID=1000092 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

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

    /** Column name BaseAmt */
    public static final String COLUMNNAME_BaseAmt = "BaseAmt";

	/** Set Base Amount.
	  * Base Amount
	  */
	public void setBaseAmt (BigDecimal BaseAmt);

	/** Get Base Amount.
	  * Base Amount
	  */
	public BigDecimal getBaseAmt();

    /** Column name DiscountAmt */
    public static final String COLUMNNAME_DiscountAmt = "DiscountAmt";

	/** Set Discount Amount.
	  * Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt);

	/** Get Discount Amount.
	  * Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt();

    /** Column name DiscountPorcent */
    public static final String COLUMNNAME_DiscountPorcent = "DiscountPorcent";

	/** Set DiscountPorcent.
	  * DiscountPorcent
	  */
	public void setDiscountPorcent (BigDecimal DiscountPorcent);

	/** Get DiscountPorcent.
	  * DiscountPorcent
	  */
	public BigDecimal getDiscountPorcent();

    /** Column name EXME_CtaPacExt_ID */
    public static final String COLUMNNAME_EXME_CtaPacExt_ID = "EXME_CtaPacExt_ID";

	/** Set Patient Account Extension.
	  * Patient Account Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID);

	/** Get Patient Account Extension.
	  * Patient Account Extension
	  */
	public int getEXME_CtaPacExt_ID();

    /** Column name EXME_CtaPacFam_ID */
    public static final String COLUMNNAME_EXME_CtaPacFam_ID = "EXME_CtaPacFam_ID";

	/** Set Discount per Product Family.
	  * Discount per Product Family
	  */
	public void setEXME_CtaPacFam_ID (int EXME_CtaPacFam_ID);

	/** Get Discount per Product Family.
	  * Discount per Product Family
	  */
	public int getEXME_CtaPacFam_ID();

    /** Column name EXME_ProductFam_ID */
    public static final String COLUMNNAME_EXME_ProductFam_ID = "EXME_ProductFam_ID";

	/** Set Family Products.
	  * Family Products
	  */
	public void setEXME_ProductFam_ID (int EXME_ProductFam_ID);

	/** Get Family Products.
	  * Family Products
	  */
	public int getEXME_ProductFam_ID();
}
