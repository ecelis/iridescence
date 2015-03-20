/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DH_PrePaymentLine
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_DH_PrePaymentLine 
{

    /** TableName=DH_PrePaymentLine */
    public static final String Table_Name = "DH_PrePaymentLine";

    /** AD_Table_ID=1201563 */
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

    /** Column name DH_PrePayment_ID */
    public static final String COLUMNNAME_DH_PrePayment_ID = "DH_PrePayment_ID";

	/** Set Pre Payment	  */
	public void setDH_PrePayment_ID (int DH_PrePayment_ID);

	/** Get Pre Payment	  */
	public int getDH_PrePayment_ID();

	public I_DH_PrePayment getDH_PrePayment() throws RuntimeException;

    /** Column name DH_PrePaymentLine_ID */
    public static final String COLUMNNAME_DH_PrePaymentLine_ID = "DH_PrePaymentLine_ID";

	/** Set Pre Payment Line	  */
	public void setDH_PrePaymentLine_ID (int DH_PrePaymentLine_ID);

	/** Get Pre Payment Line	  */
	public int getDH_PrePaymentLine_ID();

    /** Column name DH_Voucher_ID */
    public static final String COLUMNNAME_DH_Voucher_ID = "DH_Voucher_ID";

	/** Set Voucher	  */
	public void setDH_Voucher_ID (int DH_Voucher_ID);

	/** Get Voucher	  */
	public int getDH_Voucher_ID();

	public I_DH_Voucher getDH_Voucher() throws RuntimeException;

    /** Column name DocumentNoExt */
    public static final String COLUMNNAME_DocumentNoExt = "DocumentNoExt";

	/** Set Exterior Document No..
	  * Exterior Document No.
	  */
	public void setDocumentNoExt (String DocumentNoExt);

	/** Get Exterior Document No..
	  * Exterior Document No.
	  */
	public String getDocumentNoExt();

    /** Column name EXME_PaqBase_Version_ID */
    public static final String COLUMNNAME_EXME_PaqBase_Version_ID = "EXME_PaqBase_Version_ID";

	/** Set Package Version.
	  * Package Version
	  */
	public void setEXME_PaqBase_Version_ID (int EXME_PaqBase_Version_ID);

	/** Get Package Version.
	  * Package Version
	  */
	public int getEXME_PaqBase_Version_ID();

	public I_EXME_PaqBase_Version getEXME_PaqBase_Version() throws RuntimeException;

    /** Column name IsPaid */
    public static final String COLUMNNAME_IsPaid = "IsPaid";

	/** Set Paid.
	  * The document is paid
	  */
	public void setIsPaid (boolean IsPaid);

	/** Get Paid.
	  * The document is paid
	  */
	public boolean isPaid();

    /** Column name LineTotalAmt */
    public static final String COLUMNNAME_LineTotalAmt = "LineTotalAmt";

	/** Set Line Total.
	  * Total line amount incl. Tax
	  */
	public void setLineTotalAmt (BigDecimal LineTotalAmt);

	/** Get Line Total.
	  * Total line amount incl. Tax
	  */
	public BigDecimal getLineTotalAmt();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

    /** Column name PayAmt */
    public static final String COLUMNNAME_PayAmt = "PayAmt";

	/** Set Payment amount.
	  * Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt);

	/** Get Payment amount.
	  * Amount being paid
	  */
	public BigDecimal getPayAmt();

    /** Column name PaymentDate */
    public static final String COLUMNNAME_PaymentDate = "PaymentDate";

	/** Set Payment date	  */
	public void setPaymentDate (Timestamp PaymentDate);

	/** Get Payment date	  */
	public Timestamp getPaymentDate();

    /** Column name PaymentType */
    public static final String COLUMNNAME_PaymentType = "PaymentType";

	/** Set PaymentType.
	  * Billing Payment
	  */
	public void setPaymentType (String PaymentType);

	/** Get PaymentType.
	  * Billing Payment
	  */
	public String getPaymentType();

    /** Column name PriceList */
    public static final String COLUMNNAME_PriceList = "PriceList";

	/** Set List Price.
	  * List Price
	  */
	public void setPriceList (BigDecimal PriceList);

	/** Get List Price.
	  * List Price
	  */
	public BigDecimal getPriceList();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (int QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public int getQtyEntered();

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

    /** Column name TransactionCode */
    public static final String COLUMNNAME_TransactionCode = "TransactionCode";

	/** Set Transaction Code.
	  * The transaction code represents the search definition
	  */
	public void setTransactionCode (String TransactionCode);

	/** Get Transaction Code.
	  * The transaction code represents the search definition
	  */
	public String getTransactionCode();
}
