/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_FormaPago_Acct
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_EXME_FormaPago_Acct 
{

    /** TableName=EXME_FormaPago_Acct */
    public static final String Table_Name = "EXME_FormaPago_Acct";

    /** AD_Table_ID=1000182 */
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

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

    /** Column name EXME_FormaPago_ID */
    public static final String COLUMNNAME_EXME_FormaPago_ID = "EXME_FormaPago_ID";

	/** Set Payment Form.
	  * Identifier of Payment Form
	  */
	public void setEXME_FormaPago_ID (int EXME_FormaPago_ID);

	/** Get Payment Form.
	  * Identifier of Payment Form
	  */
	public int getEXME_FormaPago_ID();

    /** Column name FP_Asset_Acct */
    public static final String COLUMNNAME_FP_Asset_Acct = "FP_Asset_Acct";

	/** Set Cash Assets	  */
	public void setFP_Asset_Acct (int FP_Asset_Acct);

	/** Get Cash Assets	  */
	public int getFP_Asset_Acct();

    /** Column name FP_Revenue_Other_Acct */
    public static final String COLUMNNAME_FP_Revenue_Other_Acct = "FP_Revenue_Other_Acct";

	/** Set Other Cash income.
	  * Other Cash income
	  */
	public void setFP_Revenue_Other_Acct (int FP_Revenue_Other_Acct);

	/** Get Other Cash income.
	  * Other Cash income
	  */
	public int getFP_Revenue_Other_Acct();
}
