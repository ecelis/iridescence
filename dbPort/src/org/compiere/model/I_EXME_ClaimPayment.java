/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for EXME_ClaimPayment
 *  @author Generated Class 
 *  @version Version @VERSION@
 */
public interface I_EXME_ClaimPayment 
{

    /** TableName=EXME_ClaimPayment */
    public static final String Table_Name = "EXME_ClaimPayment";

    /** AD_Table_ID=1201219 */
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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name Amt_Billed */
    public static final String COLUMNNAME_Amt_Billed = "Amt_Billed";

	/** Set Amount Billed.
	  * Amount Billed
	  */
	public void setAmt_Billed (BigDecimal Amt_Billed);

	/** Get Amount Billed.
	  * Amount Billed
	  */
	public BigDecimal getAmt_Billed();

    /** Column name Amt_Paid */
    public static final String COLUMNNAME_Amt_Paid = "Amt_Paid";

	/** Set Amount Paid.
	  * Amount Paid
	  */
	public void setAmt_Paid (BigDecimal Amt_Paid);

	/** Get Amount Paid.
	  * Amount Paid
	  */
	public BigDecimal getAmt_Paid();

    /** Column name Balance */
    public static final String COLUMNNAME_Balance = "Balance";

	/** Set Balance	  */
	public void setBalance (BigDecimal Balance);

	/** Get Balance	  */
	public BigDecimal getBalance();

    /** Column name BillingType */
    public static final String COLUMNNAME_BillingType = "BillingType";

	/** Set Billing type	  */
	public void setBillingType (String BillingType);

	/** Get Billing type	  */
	public String getBillingType();

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

	public I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name Claim_TransID */
    public static final String COLUMNNAME_Claim_TransID = "Claim_TransID";

	/** Set Claim Transaction Identifier.
	  * Claim Transaction Identifier
	  */
	public void setClaim_TransID (String Claim_TransID);

	/** Get Claim Transaction Identifier.
	  * Claim Transaction Identifier
	  */
	public String getClaim_TransID();

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (String Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public String getCode();

    /** Column name DateOrdered */
    public static final String COLUMNNAME_DateOrdered = "DateOrdered";

	/** Set Date Ordered.
	  * Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered);

	/** Get Date Ordered.
	  * Date of Order
	  */
	public Timestamp getDateOrdered();

    /** Column name DetailType */
    public static final String COLUMNNAME_DetailType = "DetailType";

	/** Set Detail type	  */
	public void setDetailType (String DetailType);

	/** Get Detail type	  */
	public String getDetailType();

    /** Column name EXME_ClaimPaymentH_ID */
    public static final String COLUMNNAME_EXME_ClaimPaymentH_ID = "EXME_ClaimPaymentH_ID";

	/** Set Payments Claim Header.
	  * Payments Claim Header
	  */
	public void setEXME_ClaimPaymentH_ID (int EXME_ClaimPaymentH_ID);

	/** Get Payments Claim Header.
	  * Payments Claim Header
	  */
	public int getEXME_ClaimPaymentH_ID();

	public I_EXME_ClaimPaymentH getEXME_ClaimPaymentH() throws RuntimeException;

    /** Column name EXME_ClaimPayment_ID */
    public static final String COLUMNNAME_EXME_ClaimPayment_ID = "EXME_ClaimPayment_ID";

	/** Set Claim Transaction.
	  * Claim Transaction
	  */
	public void setEXME_ClaimPayment_ID (int EXME_ClaimPayment_ID);

	/** Get Claim Transaction.
	  * Claim Transaction
	  */
	public int getEXME_ClaimPayment_ID();

    /** Column name EXME_CtaPacExt_ID */
    public static final String COLUMNNAME_EXME_CtaPacExt_ID = "EXME_CtaPacExt_ID";

	/** Set Encounter Extension.
	  * Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID);

	/** Get Encounter Extension.
	  * Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID();

    /** Column name EXME_CtaPac_ID */
    public static final String COLUMNNAME_EXME_CtaPac_ID = "EXME_CtaPac_ID";

	/** Set Encounter.
	  * Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID);

	/** Get Encounter.
	  * Encounter
	  */
	public int getEXME_CtaPac_ID();

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException;

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

    /** Column name NextAction */
    public static final String COLUMNNAME_NextAction = "NextAction";

	/** Set Next action.
	  * Next Action to be taken
	  */
	public void setNextAction (String NextAction);

	/** Get Next action.
	  * Next Action to be taken
	  */
	public String getNextAction();

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

    /** Column name QtyInvoiced */
    public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";

	/** Set Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced);

	/** Get Quantity Invoiced.
	  * Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced();

    /** Column name ReceiverAccountNo */
    public static final String COLUMNNAME_ReceiverAccountNo = "ReceiverAccountNo";

	/** Set Receiver or Provider Bank ID Number.
	  * Depository Financial Institution (DFI) identification number
	  */
	public void setReceiverAccountNo (String ReceiverAccountNo);

	/** Get Receiver or Provider Bank ID Number.
	  * Depository Financial Institution (DFI) identification number
	  */
	public String getReceiverAccountNo();

    /** Column name ReceiverRoutingNo */
    public static final String COLUMNNAME_ReceiverRoutingNo = "ReceiverRoutingNo";

	/** Set Receiver or Provider Account Number.
	  * Account number assigned
	  */
	public void setReceiverRoutingNo (String ReceiverRoutingNo);

	/** Get Receiver or Provider Account Number.
	  * Account number assigned
	  */
	public String getReceiverRoutingNo();

    /** Column name SenderAccountNo */
    public static final String COLUMNNAME_SenderAccountNo = "SenderAccountNo";

	/** Set Sender DFI Identifier.
	  * Depository Financial Institution (DFI) identification number
	  */
	public void setSenderAccountNo (String SenderAccountNo);

	/** Get Sender DFI Identifier.
	  * Depository Financial Institution (DFI) identification number
	  */
	public String getSenderAccountNo();

    /** Column name SenderRoutingNo */
    public static final String COLUMNNAME_SenderRoutingNo = "SenderRoutingNo";

	/** Set Sender Bank Account Number.
	  * Account number assigned
	  */
	public void setSenderRoutingNo (String SenderRoutingNo);

	/** Get Sender Bank Account Number.
	  * Account number assigned
	  */
	public String getSenderRoutingNo();

    /** Column name TransactionType */
    public static final String COLUMNNAME_TransactionType = "TransactionType";

	/** Set Transaction type	  */
	public void setTransactionType (String TransactionType);

	/** Get Transaction type	  */
	public String getTransactionType();
}
