/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;

/** Generated Model for EXME_ClaimPayment
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_ClaimPayment extends PO implements I_EXME_ClaimPayment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ClaimPayment (Properties ctx, int EXME_ClaimPayment_ID, String trxName)
    {
      super (ctx, EXME_ClaimPayment_ID, trxName);
      /** if (EXME_ClaimPayment_ID == 0)
        {
			setClaim_TransID (null);
			setEXME_ClaimPayment_ID (0);
			setEXME_CtaPac_ID (0);
			setQtyInvoiced (Env.ZERO);
// 0
        } */
    }

    /** Load Constructor */
    public X_EXME_ClaimPayment (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_EXME_ClaimPayment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Amount Billed.
		@param Amt_Billed 
		Amount Billed
	  */
	public void setAmt_Billed (BigDecimal Amt_Billed)
	{
		set_Value (COLUMNNAME_Amt_Billed, Amt_Billed);
	}

	/** Get Amount Billed.
		@return Amount Billed
	  */
	public BigDecimal getAmt_Billed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amt_Billed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount Paid.
		@param Amt_Paid 
		Amount Paid
	  */
	public void setAmt_Paid (BigDecimal Amt_Paid)
	{
		set_Value (COLUMNNAME_Amt_Paid, Amt_Paid);
	}

	/** Get Amount Paid.
		@return Amount Paid
	  */
	public BigDecimal getAmt_Paid () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amt_Paid);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Balance.
		@param Balance Balance	  */
	public void setBalance (BigDecimal Balance)
	{
		set_Value (COLUMNNAME_Balance, Balance);
	}

	/** Get Balance.
		@return Balance	  */
	public BigDecimal getBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** BillingType AD_Reference_ID=1200579 */
	public static final int BILLINGTYPE_AD_Reference_ID=1200579;
	/** Technical = T */
	public static final String BILLINGTYPE_Technical = "T";
	/** Professional = P */
	public static final String BILLINGTYPE_Professional = "P";
	/** Set Billing type.
		@param BillingType Billing type	  */
	public void setBillingType (String BillingType)
	{

		if (BillingType == null || BillingType.equals("T") || BillingType.equals("P")); else throw new IllegalArgumentException ("BillingType Invalid value - " + BillingType + " - Reference_ID=1200579 - T - P");		set_Value (COLUMNNAME_BillingType, BillingType);
	}

	/** Get Billing type.
		@return Billing type	  */
	public String getBillingType () 
	{
		return (String)get_Value(COLUMNNAME_BillingType);
	}

	public I_C_BPartner getC_BPartner() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Company.
		@param C_BPartner_ID 
		Identifier for a Company
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Company.
		@return Identifier for a Company
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Charge getC_Charge() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Charge.Table_Name);
        I_C_Charge result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Charge)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Charge_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Invoice getC_Invoice() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Invoice.Table_Name);
        I_C_Invoice result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Invoice)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Invoice_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Claim Transaction Identifier.
		@param Claim_TransID 
		Claim Transaction Identifier
	  */
	public void setClaim_TransID (String Claim_TransID)
	{
		if (Claim_TransID == null)
			throw new IllegalArgumentException ("Claim_TransID is mandatory.");
		set_Value (COLUMNNAME_Claim_TransID, Claim_TransID);
	}

	/** Get Claim Transaction Identifier.
		@return Claim Transaction Identifier
	  */
	public String getClaim_TransID () 
	{
		return (String)get_Value(COLUMNNAME_Claim_TransID);
	}

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (String Code)
	{
		set_Value (COLUMNNAME_Code, Code);
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public String getCode () 
	{
		return (String)get_Value(COLUMNNAME_Code);
	}

	/** Set Date Ordered.
		@param DateOrdered 
		Date of Order
	  */
	public void setDateOrdered (Timestamp DateOrdered)
	{
		set_Value (COLUMNNAME_DateOrdered, DateOrdered);
	}

	/** Get Date Ordered.
		@return Date of Order
	  */
	public Timestamp getDateOrdered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateOrdered);
	}

	/** DetailType AD_Reference_ID=1200582 */
	public static final int DETAILTYPE_AD_Reference_ID=1200582;
	/** Itemized = I */
	public static final String DETAILTYPE_Itemized = "I";
	/** Summary = S */
	public static final String DETAILTYPE_Summary = "S";
	/** Set Detail type.
		@param DetailType Detail type	  */
	public void setDetailType (String DetailType)
	{

		if (DetailType == null || DetailType.equals("I") || DetailType.equals("S")); else throw new IllegalArgumentException ("DetailType Invalid value - " + DetailType + " - Reference_ID=1200582 - I - S");		set_Value (COLUMNNAME_DetailType, DetailType);
	}

	/** Get Detail type.
		@return Detail type	  */
	public String getDetailType () 
	{
		return (String)get_Value(COLUMNNAME_DetailType);
	}

	public I_EXME_ClaimPaymentH getEXME_ClaimPaymentH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClaimPaymentH.Table_Name);
        I_EXME_ClaimPaymentH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClaimPaymentH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClaimPaymentH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Payments Claim Header.
		@param EXME_ClaimPaymentH_ID 
		Payments Claim Header
	  */
	public void setEXME_ClaimPaymentH_ID (int EXME_ClaimPaymentH_ID)
	{
		if (EXME_ClaimPaymentH_ID < 1) 
			set_Value (COLUMNNAME_EXME_ClaimPaymentH_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ClaimPaymentH_ID, Integer.valueOf(EXME_ClaimPaymentH_ID));
	}

	/** Get Payments Claim Header.
		@return Payments Claim Header
	  */
	public int getEXME_ClaimPaymentH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimPaymentH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Claim Transaction.
		@param EXME_ClaimPayment_ID 
		Claim Transaction
	  */
	public void setEXME_ClaimPayment_ID (int EXME_ClaimPayment_ID)
	{
		if (EXME_ClaimPayment_ID < 1)
			 throw new IllegalArgumentException ("EXME_ClaimPayment_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ClaimPayment_ID, Integer.valueOf(EXME_ClaimPayment_ID));
	}

	/** Get Claim Transaction.
		@return Claim Transaction
	  */
	public int getEXME_ClaimPayment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ClaimPayment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Encounter Extension.
		@param EXME_CtaPacExt_ID 
		Encounter Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID)
	{
		if (EXME_CtaPacExt_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPacExt_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CtaPacExt_ID, Integer.valueOf(EXME_CtaPacExt_ID));
	}

	/** Get Encounter Extension.
		@return Encounter Extension
	  */
	public int getEXME_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacExt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Encounter.
		@return Encounter
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** NextAction AD_Reference_ID=1200583 */
	public static final int NEXTACTION_AD_Reference_ID=1200583;
	/** Primary Payer = 1P */
	public static final String NEXTACTION_PrimaryPayer = "1P";
	/** Secondary Payer = 2P */
	public static final String NEXTACTION_SecondaryPayer = "2P";
	/** 3P = 3P */
	public static final String NEXTACTION_3P = "3P";
	/** Set Next action.
		@param NextAction 
		Next Action to be taken
	  */
	public void setNextAction (String NextAction)
	{

		if (NextAction == null || NextAction.equals("1P") || NextAction.equals("2P") || NextAction.equals("3P")); else throw new IllegalArgumentException ("NextAction Invalid value - " + NextAction + " - Reference_ID=1200583 - 1P - 2P - 3P");		set_Value (COLUMNNAME_NextAction, NextAction);
	}

	/** Get Next action.
		@return Next Action to be taken
	  */
	public String getNextAction () 
	{
		return (String)get_Value(COLUMNNAME_NextAction);
	}

	/** Set Payment date.
		@param PaymentDate Payment date	  */
	public void setPaymentDate (Timestamp PaymentDate)
	{
		set_Value (COLUMNNAME_PaymentDate, PaymentDate);
	}

	/** Get Payment date.
		@return Payment date	  */
	public Timestamp getPaymentDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PaymentDate);
	}

	/** PaymentType AD_Reference_ID=1200586 */
	public static final int PAYMENTTYPE_AD_Reference_ID=1200586;
	/** Automated Clearing House = ACH */
	public static final String PAYMENTTYPE_AutomatedClearingHouse = "ACH";
	/** Financial Institution Option = BOP */
	public static final String PAYMENTTYPE_FinancialInstitutionOption = "BOP";
	/** Check = CHK */
	public static final String PAYMENTTYPE_Check = "CHK";
	/** Federal Wire Transfer = FWT */
	public static final String PAYMENTTYPE_FederalWireTransfer = "FWT";
	/** Non Payment Data = NON */
	public static final String PAYMENTTYPE_NonPaymentData = "NON";
	/** Set PaymentType.
		@param PaymentType 
		Billing Payment
	  */
	public void setPaymentType (String PaymentType)
	{

		if (PaymentType == null || PaymentType.equals("ACH") || PaymentType.equals("BOP") || PaymentType.equals("CHK") || PaymentType.equals("FWT") || PaymentType.equals("NON")); else throw new IllegalArgumentException ("PaymentType Invalid value - " + PaymentType + " - Reference_ID=1200586 - ACH - BOP - CHK - FWT - NON");		set_Value (COLUMNNAME_PaymentType, PaymentType);
	}

	/** Get PaymentType.
		@return Billing Payment
	  */
	public String getPaymentType () 
	{
		return (String)get_Value(COLUMNNAME_PaymentType);
	}

	/** Set Quantity Invoiced.
		@param QtyInvoiced 
		Invoiced Quantity
	  */
	public void setQtyInvoiced (BigDecimal QtyInvoiced)
	{
		if (QtyInvoiced == null)
			throw new IllegalArgumentException ("QtyInvoiced is mandatory.");
		set_ValueNoCheck (COLUMNNAME_QtyInvoiced, QtyInvoiced);
	}

	/** Get Quantity Invoiced.
		@return Invoiced Quantity
	  */
	public BigDecimal getQtyInvoiced () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInvoiced);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Receiver or Provider Bank ID Number.
		@param ReceiverAccountNo 
		Depository Financial Institution (DFI) identification number
	  */
	public void setReceiverAccountNo (String ReceiverAccountNo)
	{
		set_Value (COLUMNNAME_ReceiverAccountNo, ReceiverAccountNo);
	}

	/** Get Receiver or Provider Bank ID Number.
		@return Depository Financial Institution (DFI) identification number
	  */
	public String getReceiverAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_ReceiverAccountNo);
	}

	/** Set Receiver or Provider Account Number.
		@param ReceiverRoutingNo 
		Account number assigned
	  */
	public void setReceiverRoutingNo (String ReceiverRoutingNo)
	{
		set_Value (COLUMNNAME_ReceiverRoutingNo, ReceiverRoutingNo);
	}

	/** Get Receiver or Provider Account Number.
		@return Account number assigned
	  */
	public String getReceiverRoutingNo () 
	{
		return (String)get_Value(COLUMNNAME_ReceiverRoutingNo);
	}

	/** Set Sender DFI Identifier.
		@param SenderAccountNo 
		Depository Financial Institution (DFI) identification number
	  */
	public void setSenderAccountNo (String SenderAccountNo)
	{
		set_Value (COLUMNNAME_SenderAccountNo, SenderAccountNo);
	}

	/** Get Sender DFI Identifier.
		@return Depository Financial Institution (DFI) identification number
	  */
	public String getSenderAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_SenderAccountNo);
	}

	/** Set Sender Bank Account Number.
		@param SenderRoutingNo 
		Account number assigned
	  */
	public void setSenderRoutingNo (String SenderRoutingNo)
	{
		set_Value (COLUMNNAME_SenderRoutingNo, SenderRoutingNo);
	}

	/** Get Sender Bank Account Number.
		@return Account number assigned
	  */
	public String getSenderRoutingNo () 
	{
		return (String)get_Value(COLUMNNAME_SenderRoutingNo);
	}

	/** TransactionType AD_Reference_ID=1200584 */
	public static final int TRANSACTIONTYPE_AD_Reference_ID=1200584;
	/** Professional Payment = PP */
	public static final String TRANSACTIONTYPE_ProfessionalPayment = "PP";
	/** Profession Adjustment = PA */
	public static final String TRANSACTIONTYPE_ProfessionAdjustment = "PA";
	/** Institutional Adjustment = PI */
	public static final String TRANSACTIONTYPE_InstitutionalAdjustment = "PI";
	/** Set Transaction type.
		@param TransactionType Transaction type	  */
	public void setTransactionType (String TransactionType)
	{

		if (TransactionType == null || TransactionType.equals("PP") || TransactionType.equals("PA") || TransactionType.equals("PI")); else throw new IllegalArgumentException ("TransactionType Invalid value - " + TransactionType + " - Reference_ID=1200584 - PP - PA - PI");		set_Value (COLUMNNAME_TransactionType, TransactionType);
	}

	/** Get Transaction type.
		@return Transaction type	  */
	public String getTransactionType () 
	{
		return (String)get_Value(COLUMNNAME_TransactionType);
	}
}