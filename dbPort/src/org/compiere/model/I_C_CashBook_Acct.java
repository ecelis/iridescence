/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_CashBook_Acct
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_CashBook_Acct 
{

    /** TableName=C_CashBook_Acct */
    public static final String Table_Name = "C_CashBook_Acct";

    /** AD_Table_ID=409 */
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

    /** Column name CB_Asset_Acct */
    public static final String COLUMNNAME_CB_Asset_Acct = "CB_Asset_Acct";

	/** Set Cash Book Asset.
	  * Cash Book Asset Account
	  */
	public void setCB_Asset_Acct (int CB_Asset_Acct);

	/** Get Cash Book Asset.
	  * Cash Book Asset Account
	  */
	public int getCB_Asset_Acct();

    /** Column name CB_CashTransfer_Acct */
    public static final String COLUMNNAME_CB_CashTransfer_Acct = "CB_CashTransfer_Acct";

	/** Set Cash Transfer.
	  * Cash Transfer Clearing Account
	  */
	public void setCB_CashTransfer_Acct (int CB_CashTransfer_Acct);

	/** Get Cash Transfer.
	  * Cash Transfer Clearing Account
	  */
	public int getCB_CashTransfer_Acct();

    /** Column name CB_Differences_Acct */
    public static final String COLUMNNAME_CB_Differences_Acct = "CB_Differences_Acct";

	/** Set Cash Book Differences.
	  * Cash Book Differences Account
	  */
	public void setCB_Differences_Acct (int CB_Differences_Acct);

	/** Get Cash Book Differences.
	  * Cash Book Differences Account
	  */
	public int getCB_Differences_Acct();

    /** Column name CB_Expense_Acct */
    public static final String COLUMNNAME_CB_Expense_Acct = "CB_Expense_Acct";

	/** Set Cash Book Expense.
	  * Cash Book Expense Account
	  */
	public void setCB_Expense_Acct (int CB_Expense_Acct);

	/** Get Cash Book Expense.
	  * Cash Book Expense Account
	  */
	public int getCB_Expense_Acct();

    /** Column name CB_Receipt_Acct */
    public static final String COLUMNNAME_CB_Receipt_Acct = "CB_Receipt_Acct";

	/** Set Cash Book Receipt.
	  * Cash Book Receipts Account
	  */
	public void setCB_Receipt_Acct (int CB_Receipt_Acct);

	/** Get Cash Book Receipt.
	  * Cash Book Receipts Account
	  */
	public int getCB_Receipt_Acct();

    /** Column name C_CashBook_ID */
    public static final String COLUMNNAME_C_CashBook_ID = "C_CashBook_ID";

	/** Set Cash Book.
	  * Cash Book for recording petty cash transactions
	  */
	public void setC_CashBook_ID (int C_CashBook_ID);

	/** Get Cash Book.
	  * Cash Book for recording petty cash transactions
	  */
	public int getC_CashBook_ID();

	public I_C_CashBook getC_CashBook() throws RuntimeException;
}
