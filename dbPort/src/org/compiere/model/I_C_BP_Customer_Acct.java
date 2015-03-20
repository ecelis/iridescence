/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_BP_Customer_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_BP_Customer_Acct 
{

    /** TableName=C_BP_Customer_Acct */
    public static final String Table_Name = "C_BP_Customer_Acct";

    /** AD_Table_ID=183 */
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

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Company.
	  * Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Company.
	  * Identifier for a Company
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Prepayment_Acct */
    public static final String COLUMNNAME_C_Prepayment_Acct = "C_Prepayment_Acct";

	/** Set Advances to National Customers.
	  * Advances to National Customers
	  */
	public void setC_Prepayment_Acct (int C_Prepayment_Acct);

	/** Get Advances to National Customers.
	  * Advances to National Customers
	  */
	public int getC_Prepayment_Acct();

    /** Column name C_PrepaymentFgn_Acct */
    public static final String COLUMNNAME_C_PrepaymentFgn_Acct = "C_PrepaymentFgn_Acct";

	/** Set Advances to Foreign Customers.
	  * Advances to Foreign Customers
	  */
	public void setC_PrepaymentFgn_Acct (int C_PrepaymentFgn_Acct);

	/** Get Advances to Foreign Customers.
	  * Advances to Foreign Customers
	  */
	public int getC_PrepaymentFgn_Acct();

    /** Column name C_Receivable_Acct */
    public static final String COLUMNNAME_C_Receivable_Acct = "C_Receivable_Acct";

	/** Set National customer.
	  * National customer
	  */
	public void setC_Receivable_Acct (int C_Receivable_Acct);

	/** Get National customer.
	  * National customer
	  */
	public int getC_Receivable_Acct();

    /** Column name C_ReceivableFgn_Acct */
    public static final String COLUMNNAME_C_ReceivableFgn_Acct = "C_ReceivableFgn_Acct";

	/** Set Foreign Clients.
	  * Foreign Clients
	  */
	public void setC_ReceivableFgn_Acct (int C_ReceivableFgn_Acct);

	/** Get Foreign Clients.
	  * Foreign Clients
	  */
	public int getC_ReceivableFgn_Acct();

    /** Column name C_Receivable_Services_Acct */
    public static final String COLUMNNAME_C_Receivable_Services_Acct = "C_Receivable_Services_Acct";

	/** Set Receivable Services.
	  * Customer Accounts Receivables Services Account
	  */
	public void setC_Receivable_Services_Acct (int C_Receivable_Services_Acct);

	/** Get Receivable Services.
	  * Customer Accounts Receivables Services Account
	  */
	public int getC_Receivable_Services_Acct();

    /** Column name EXME_Cob_NF_Acct */
    public static final String COLUMNNAME_EXME_Cob_NF_Acct = "EXME_Cob_NF_Acct";

	/** Set Income noninvoiced	  */
	public void setEXME_Cob_NF_Acct (int EXME_Cob_NF_Acct);

	/** Get Income noninvoiced	  */
	public int getEXME_Cob_NF_Acct();
}
