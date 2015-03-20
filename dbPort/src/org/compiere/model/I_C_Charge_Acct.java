/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Charge_Acct
 *  @author Generated Class 
 *  @version Release 1.2
 */
public interface I_C_Charge_Acct 
{

    /** TableName=C_Charge_Acct */
    public static final String Table_Name = "C_Charge_Acct";

    /** AD_Table_ID=396 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name Ch_Expense_Acct */
    public static final String COLUMNNAME_Ch_Expense_Acct = "Ch_Expense_Acct";

	/** Set Charge Expense.
	  * Charge Expense Account
	  */
	public void setCh_Expense_Acct (int Ch_Expense_Acct);

	/** Get Charge Expense.
	  * Charge Expense Account
	  */
	public int getCh_Expense_Acct();

    /** Column name Ch_Revenue_Acct */
    public static final String COLUMNNAME_Ch_Revenue_Acct = "Ch_Revenue_Acct";

	/** Set Charge Revenue.
	  * Charge Revenue Account
	  */
	public void setCh_Revenue_Acct (int Ch_Revenue_Acct);

	/** Get Charge Revenue.
	  * Charge Revenue Account
	  */
	public int getCh_Revenue_Acct();
}
