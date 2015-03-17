/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_BP_Creditor_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_BP_Creditor_Acct 
{

    /** TableName=C_BP_Creditor_Acct */
    public static final String Table_Name = "C_BP_Creditor_Acct";

    /** AD_Table_ID=1201568 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Creditors_Acct */
    public static final String COLUMNNAME_A_Creditors_Acct = "A_Creditors_Acct";

	/** Set National Sundry Creditors.
	  * National Sundry Creditors
	  */
	public void setA_Creditors_Acct (int A_Creditors_Acct);

	/** Get National Sundry Creditors.
	  * National Sundry Creditors
	  */
	public int getA_Creditors_Acct();

    /** Column name A_CreditorsFgn_Acct */
    public static final String COLUMNNAME_A_CreditorsFgn_Acct = "A_CreditorsFgn_Acct";

	/** Set Sundry Creditors Foreigners.
	  * Sundry Creditors Foreigners
	  */
	public void setA_CreditorsFgn_Acct (int A_CreditorsFgn_Acct);

	/** Get Sundry Creditors Foreigners.
	  * Sundry Creditors Foreigners
	  */
	public int getA_CreditorsFgn_Acct();

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
}
