/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DH_Voucher
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_DH_Voucher 
{

    /** TableName=DH_Voucher */
    public static final String Table_Name = "DH_Voucher";

    /** AD_Table_ID=1201566 */
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

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public I_C_BankAccount getC_BankAccount() throws RuntimeException;

    /** Column name DateTrx */
    public static final String COLUMNNAME_DateTrx = "DateTrx";

	/** Set Transaction Date.
	  * Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx);

	/** Get Transaction Date.
	  * Transaction Date
	  */
	public Timestamp getDateTrx();

    /** Column name DH_PrePayment_ID */
    public static final String COLUMNNAME_DH_PrePayment_ID = "DH_PrePayment_ID";

	/** Set Pre Payment	  */
	public void setDH_PrePayment_ID (int DH_PrePayment_ID);

	/** Get Pre Payment	  */
	public int getDH_PrePayment_ID();

	public I_DH_PrePayment getDH_PrePayment() throws RuntimeException;

    /** Column name DH_Voucher_ID */
    public static final String COLUMNNAME_DH_Voucher_ID = "DH_Voucher_ID";

	/** Set Voucher	  */
	public void setDH_Voucher_ID (int DH_Voucher_ID);

	/** Get Voucher	  */
	public int getDH_Voucher_ID();

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

	public I_EXME_FormaPago getEXME_FormaPago() throws RuntimeException;

    /** Column name GrandTotal */
    public static final String COLUMNNAME_GrandTotal = "GrandTotal";

	/** Set Grand Total.
	  * Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal);

	/** Get Grand Total.
	  * Total amount of document
	  */
	public BigDecimal getGrandTotal();

    /** Column name Membership */
    public static final String COLUMNNAME_Membership = "Membership";

	/** Set Membership	  */
	public void setMembership (String Membership);

	/** Get Membership	  */
	public String getMembership();

    /** Column name TaxAmt */
    public static final String COLUMNNAME_TaxAmt = "TaxAmt";

	/** Set Tax Amount.
	  * Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt);

	/** Get Tax Amount.
	  * Tax Amount for a document
	  */
	public BigDecimal getTaxAmt();

    /** Column name Voucher */
    public static final String COLUMNNAME_Voucher = "Voucher";

	/** Set Voucher	  */
	public void setVoucher (String Voucher);

	/** Get Voucher	  */
	public String getVoucher();
}
