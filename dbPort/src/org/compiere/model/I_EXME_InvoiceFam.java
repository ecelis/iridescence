/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_InvoiceFam
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_InvoiceFam 
{

    /** TableName=EXME_InvoiceFam */
    public static final String Table_Name = "EXME_InvoiceFam";

    /** AD_Table_ID=1000162 */
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

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

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
