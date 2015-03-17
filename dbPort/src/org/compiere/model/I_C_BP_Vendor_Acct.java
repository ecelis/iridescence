/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_BP_Vendor_Acct
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_C_BP_Vendor_Acct 
{

    /** TableName=C_BP_Vendor_Acct */
    public static final String Table_Name = "C_BP_Vendor_Acct";

    /** AD_Table_ID=185 */
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

    /** Column name V_Liability_Acct */
    public static final String COLUMNNAME_V_Liability_Acct = "V_Liability_Acct";

	/** Set Passive National Suppliers.
	  * Passive National Suppliers
	  */
	public void setV_Liability_Acct (int V_Liability_Acct);

	/** Get Passive National Suppliers.
	  * Passive National Suppliers
	  */
	public int getV_Liability_Acct();

    /** Column name V_LiabilityFgn_Acct */
    public static final String COLUMNNAME_V_LiabilityFgn_Acct = "V_LiabilityFgn_Acct";

	/** Set Passive Foreign Suppliers.
	  * Passive Foreign Suppliers
	  */
	public void setV_LiabilityFgn_Acct (int V_LiabilityFgn_Acct);

	/** Get Passive Foreign Suppliers.
	  * Passive Foreign Suppliers
	  */
	public int getV_LiabilityFgn_Acct();

    /** Column name V_Liability_Services_Acct */
    public static final String COLUMNNAME_V_Liability_Services_Acct = "V_Liability_Services_Acct";

	/** Set Vendor Service Liability.
	  * Account for Vender Service Liability
	  */
	public void setV_Liability_Services_Acct (int V_Liability_Services_Acct);

	/** Get Vendor Service Liability.
	  * Account for Vender Service Liability
	  */
	public int getV_Liability_Services_Acct();

    /** Column name V_Prepayment_Acct */
    public static final String COLUMNNAME_V_Prepayment_Acct = "V_Prepayment_Acct";

	/** Set Advances to National Suppliers.
	  * Advances to National Suppliers
	  */
	public void setV_Prepayment_Acct (int V_Prepayment_Acct);

	/** Get Advances to National Suppliers.
	  * Advances to National Suppliers
	  */
	public int getV_Prepayment_Acct();

    /** Column name V_PrepaymentFgn_Acct */
    public static final String COLUMNNAME_V_PrepaymentFgn_Acct = "V_PrepaymentFgn_Acct";

	/** Set Advances to Foreign Suppliers.
	  * Advances to Foreign Suppliers
	  */
	public void setV_PrepaymentFgn_Acct (int V_PrepaymentFgn_Acct);

	/** Get Advances to Foreign Suppliers.
	  * Advances to Foreign Suppliers
	  */
	public int getV_PrepaymentFgn_Acct();
}
