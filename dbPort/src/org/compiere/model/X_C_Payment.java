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
import org.compiere.util.KeyNamePair;

/** Generated Model for C_Payment
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_Payment extends PO implements I_C_Payment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_Payment (Properties ctx, int C_Payment_ID, String trxName)
    {
      super (ctx, C_Payment_ID, trxName);
      /** if (C_Payment_ID == 0)
        {
			setAmtSubtract (Env.ZERO);
			setC_BankAccount_ID (0);
			setC_BPartner_ID (0);
			setC_Currency_ID (0);
			setC_DocType_ID (0);
			setC_Payment_ID (0);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDateTrx (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setIsAllocated (false);
			setIsApproved (false);
// N
			setIsDelayedCapture (false);
			setIsOnline (false);
			setIsOverUnderPayment (false);
// N
			setIsPrepayment (false);
			setIsReceipt (false);
			setIsReconciled (false);
			setIsSelfService (false);
			setPayAmt (Env.ZERO);
// 0
			setPosted (false);
// N
			setProcessed (false);
			setReciboNo (null);
			setTenderType (null);
// K
			setTrxType (null);
// S
        } */
    }

    /** Load Constructor */
    public X_C_Payment (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_C_Payment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account No.
		@param AccountNo 
		Account Number
	  */
	public void setAccountNo (String AccountNo)
	{
		set_Value (COLUMNNAME_AccountNo, AccountNo);
	}

	/** Get Account No.
		@return Account Number
	  */
	public String getAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_AccountNo);
	}

	/** Set Account City.
		@param A_City 
		City or the Credit Card or Account Holder
	  */
	public void setA_City (String A_City)
	{
		set_Value (COLUMNNAME_A_City, A_City);
	}

	/** Get Account City.
		@return City or the Credit Card or Account Holder
	  */
	public String getA_City () 
	{
		return (String)get_Value(COLUMNNAME_A_City);
	}

	/** Set Account Country.
		@param A_Country 
		Country
	  */
	public void setA_Country (String A_Country)
	{
		set_Value (COLUMNNAME_A_Country, A_Country);
	}

	/** Get Account Country.
		@return Country
	  */
	public String getA_Country () 
	{
		return (String)get_Value(COLUMNNAME_A_Country);
	}

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account EMail.
		@param A_EMail 
		Email Address
	  */
	public void setA_EMail (String A_EMail)
	{
		set_Value (COLUMNNAME_A_EMail, A_EMail);
	}

	/** Get Account EMail.
		@return Email Address
	  */
	public String getA_EMail () 
	{
		return (String)get_Value(COLUMNNAME_A_EMail);
	}

	/** Set Driver License.
		@param A_Ident_DL 
		Payment Identification - Driver License
	  */
	public void setA_Ident_DL (String A_Ident_DL)
	{
		set_Value (COLUMNNAME_A_Ident_DL, A_Ident_DL);
	}

	/** Get Driver License.
		@return Payment Identification - Driver License
	  */
	public String getA_Ident_DL () 
	{
		return (String)get_Value(COLUMNNAME_A_Ident_DL);
	}

	/** Set Social Security No.
		@param A_Ident_SSN 
		Payment Identification - Social Security No
	  */
	public void setA_Ident_SSN (String A_Ident_SSN)
	{
		set_Value (COLUMNNAME_A_Ident_SSN, A_Ident_SSN);
	}

	/** Get Social Security No.
		@return Payment Identification - Social Security No
	  */
	public String getA_Ident_SSN () 
	{
		return (String)get_Value(COLUMNNAME_A_Ident_SSN);
	}

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Subtract Amount.
		@param AmtSubtract 
		Subtract Amount for generating commissions
	  */
	public void setAmtSubtract (BigDecimal AmtSubtract)
	{
		if (AmtSubtract == null)
			throw new IllegalArgumentException ("AmtSubtract is mandatory.");
		set_Value (COLUMNNAME_AmtSubtract, AmtSubtract);
	}

	/** Get Subtract Amount.
		@return Subtract Amount for generating commissions
	  */
	public BigDecimal getAmtSubtract () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtSubtract);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Account Name.
		@param A_Name 
		Name on Credit Card or Account holder
	  */
	public void setA_Name (String A_Name)
	{
		set_Value (COLUMNNAME_A_Name, A_Name);
	}

	/** Get Account Name.
		@return Name on Credit Card or Account holder
	  */
	public String getA_Name () 
	{
		return (String)get_Value(COLUMNNAME_A_Name);
	}

	/** Set Account State.
		@param A_State 
		State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State)
	{
		set_Value (COLUMNNAME_A_State, A_State);
	}

	/** Get Account State.
		@return State of the Credit Card or Account holder
	  */
	public String getA_State () 
	{
		return (String)get_Value(COLUMNNAME_A_State);
	}

	/** Set Account Street.
		@param A_Street 
		Street address of the Credit Card or Account holder
	  */
	public void setA_Street (String A_Street)
	{
		set_Value (COLUMNNAME_A_Street, A_Street);
	}

	/** Get Account Street.
		@return Street address of the Credit Card or Account holder
	  */
	public String getA_Street () 
	{
		return (String)get_Value(COLUMNNAME_A_Street);
	}

	/** Set Account Zip/Postal.
		@param A_Zip 
		Zip Code of the Credit Card or Account Holder
	  */
	public void setA_Zip (String A_Zip)
	{
		set_Value (COLUMNNAME_A_Zip, A_Zip);
	}

	/** Get Account Zip/Postal.
		@return Zip Code of the Credit Card or Account Holder
	  */
	public String getA_Zip () 
	{
		return (String)get_Value(COLUMNNAME_A_Zip);
	}

	/** Set Back office.
		@param Backoffice Back office	  */
	public void setBackoffice (String Backoffice)
	{
		set_Value (COLUMNNAME_Backoffice, Backoffice);
	}

	/** Get Back office.
		@return Back office	  */
	public String getBackoffice () 
	{
		return (String)get_Value(COLUMNNAME_Backoffice);
	}

	/** Set Initial Amount.
		@param BeginningBalance 
		Balance prior to any transactions
	  */
	public void setBeginningBalance (BigDecimal BeginningBalance)
	{
		set_Value (COLUMNNAME_BeginningBalance, BeginningBalance);
	}

	/** Get Initial Amount.
		@return Balance prior to any transactions
	  */
	public BigDecimal getBeginningBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BeginningBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_Activity getC_Activity() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Activity.Table_Name);
        I_C_Activity result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Activity)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Activity_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Responsible Units.
		@param C_Activity_ID 
		Responsible Units
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Responsible Units.
		@return Responsible Units
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BankAccount getC_BankAccount() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BankAccount.Table_Name);
        I_C_BankAccount result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BankAccount)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BankAccount_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1)
			 throw new IllegalArgumentException ("C_BankAccount_ID is mandatory.");
		set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			 throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
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

	public I_C_BP_BankAccount getC_BP_BankAccount() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BP_BankAccount.Table_Name);
        I_C_BP_BankAccount result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BP_BankAccount)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BP_BankAccount_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Partner Bank Account.
		@param C_BP_BankAccount_ID 
		Bank Account of the Business Partner
	  */
	public void setC_BP_BankAccount_ID (int C_BP_BankAccount_ID)
	{
		if (C_BP_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BP_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_BankAccount_ID, Integer.valueOf(C_BP_BankAccount_ID));
	}

	/** Get Partner Bank Account.
		@return Bank Account of the Business Partner
	  */
	public int getC_BP_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Campaign getC_Campaign() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Campaign.Table_Name);
        I_C_Campaign result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Campaign)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Campaign_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Program.
		@param C_Campaign_ID 
		Program
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Program.
		@return Program
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_CashBook getC_CashBook() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_CashBook.Table_Name);
        I_C_CashBook result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_CashBook)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_CashBook_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cash Register.
		@param C_CashBook_ID 
		Cash Register for recording payments transactions
	  */
	public void setC_CashBook_ID (int C_CashBook_ID)
	{
		if (C_CashBook_ID < 1) 
			set_Value (COLUMNNAME_C_CashBook_ID, null);
		else 
			set_Value (COLUMNNAME_C_CashBook_ID, Integer.valueOf(C_CashBook_ID));
	}

	/** Get Cash Register.
		@return Cash Register for recording payments transactions
	  */
	public int getC_CashBook_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CashBook_ID);
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

	public I_C_ConversionType getC_ConversionType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_ConversionType.Table_Name);
        I_C_ConversionType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_ConversionType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_ConversionType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Currency Type.
		@param C_ConversionType_ID 
		Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID)
	{
		if (C_ConversionType_ID < 1) 
			set_Value (COLUMNNAME_C_ConversionType_ID, null);
		else 
			set_Value (COLUMNNAME_C_ConversionType_ID, Integer.valueOf(C_ConversionType_ID));
	}

	/** Get Currency Type.
		@return Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ConversionType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Currency getC_Currency() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Currency.Table_Name);
        I_C_Currency result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Currency)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Currency_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1)
			 throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
		set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0)
			 throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
		set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Charge amount.
		@param ChargeAmt 
		Charge Amount
	  */
	public void setChargeAmt (BigDecimal ChargeAmt)
	{
		set_Value (COLUMNNAME_ChargeAmt, ChargeAmt);
	}

	/** Get Charge amount.
		@return Charge Amount
	  */
	public BigDecimal getChargeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ChargeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Check No.
		@param CheckNo 
		Check Number
	  */
	public void setCheckNo (String CheckNo)
	{
		set_Value (COLUMNNAME_CheckNo, CheckNo);
	}

	/** Get Check No.
		@return Check Number
	  */
	public String getCheckNo () 
	{
		return (String)get_Value(COLUMNNAME_CheckNo);
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

	/** ConfType AD_Reference_ID=1200579 */
	public static final int CONFTYPE_AD_Reference_ID=1200579;
	/** Technical = T */
	public static final String CONFTYPE_Technical = "T";
	/** Professional = P */
	public static final String CONFTYPE_Professional = "P";
	/** Set Configuration Type.
		@param ConfType Configuration Type	  */
	public void setConfType (String ConfType)
	{

		if (ConfType == null || ConfType.equals("T") || ConfType.equals("P")); else throw new IllegalArgumentException ("ConfType Invalid value - " + ConfType + " - Reference_ID=1200579 - T - P");		set_Value (COLUMNNAME_ConfType, ConfType);
	}

	/** Get Configuration Type.
		@return Configuration Type	  */
	public String getConfType () 
	{
		return (String)get_Value(COLUMNNAME_ConfType);
	}

	public I_C_Order getC_Order() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Order.Table_Name);
        I_C_Order result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Order)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Order_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_Value (COLUMNNAME_C_Order_ID, null);
		else 
			set_Value (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_PaymentBatch getC_PaymentBatch() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_PaymentBatch.Table_Name);
        I_C_PaymentBatch result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_PaymentBatch)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_PaymentBatch_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Payment Batch.
		@param C_PaymentBatch_ID 
		Payment batch for EFT
	  */
	public void setC_PaymentBatch_ID (int C_PaymentBatch_ID)
	{
		if (C_PaymentBatch_ID < 1) 
			set_Value (COLUMNNAME_C_PaymentBatch_ID, null);
		else 
			set_Value (COLUMNNAME_C_PaymentBatch_ID, Integer.valueOf(C_PaymentBatch_ID));
	}

	/** Get Payment Batch.
		@return Payment batch for EFT
	  */
	public int getC_PaymentBatch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentBatch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1)
			 throw new IllegalArgumentException ("C_Payment_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Project getC_Project() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Project.Table_Name);
        I_C_Project result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Project)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Project_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exp. Month.
		@param CreditCardExpMM 
		Expiry Month
	  */
	public void setCreditCardExpMM (int CreditCardExpMM)
	{
		set_Value (COLUMNNAME_CreditCardExpMM, Integer.valueOf(CreditCardExpMM));
	}

	/** Get Exp. Month.
		@return Expiry Month
	  */
	public int getCreditCardExpMM () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CreditCardExpMM);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exp. Year.
		@param CreditCardExpYY 
		Expiry Year
	  */
	public void setCreditCardExpYY (int CreditCardExpYY)
	{
		set_Value (COLUMNNAME_CreditCardExpYY, Integer.valueOf(CreditCardExpYY));
	}

	/** Get Exp. Year.
		@return Expiry Year
	  */
	public int getCreditCardExpYY () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CreditCardExpYY);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number.
		@param CreditCardNumber 
		Credit Card Number 
	  */
	public void setCreditCardNumber (String CreditCardNumber)
	{
		set_Value (COLUMNNAME_CreditCardNumber, CreditCardNumber);
	}

	/** Get Number.
		@return Credit Card Number 
	  */
	public String getCreditCardNumber () 
	{
		return (String)get_Value(COLUMNNAME_CreditCardNumber);
	}

	/** CreditCardType AD_Reference_ID=149 */
	public static final int CREDITCARDTYPE_AD_Reference_ID=149;
	/** Amex = A */
	public static final String CREDITCARDTYPE_Amex = "A";
	/** MasterCard = M */
	public static final String CREDITCARDTYPE_MasterCard = "M";
	/** Visa = V */
	public static final String CREDITCARDTYPE_Visa = "V";
	/** ATM = C */
	public static final String CREDITCARDTYPE_ATM = "C";
	/** Diners = D */
	public static final String CREDITCARDTYPE_Diners = "D";
	/** Discover = N */
	public static final String CREDITCARDTYPE_Discover = "N";
	/** Purchase Card = P */
	public static final String CREDITCARDTYPE_PurchaseCard = "P";
	/** Set Credit Card.
		@param CreditCardType 
		Credit Card (Visa, MC, AmEx)
	  */
	public void setCreditCardType (String CreditCardType)
	{

		if (CreditCardType == null || CreditCardType.equals("A") || CreditCardType.equals("M") || CreditCardType.equals("V") || CreditCardType.equals("C") || CreditCardType.equals("D") || CreditCardType.equals("N") || CreditCardType.equals("P")); else throw new IllegalArgumentException ("CreditCardType Invalid value - " + CreditCardType + " - Reference_ID=149 - A - M - V - C - D - N - P");		set_Value (COLUMNNAME_CreditCardType, CreditCardType);
	}

	/** Get Credit Card.
		@return Credit Card (Visa, MC, AmEx)
	  */
	public String getCreditCardType () 
	{
		return (String)get_Value(COLUMNNAME_CreditCardType);
	}

	/** Set Verification Code.
		@param CreditCardVV 
		Credit Card Verification code on credit card
	  */
	public void setCreditCardVV (String CreditCardVV)
	{
		set_Value (COLUMNNAME_CreditCardVV, CreditCardVV);
	}

	/** Get Verification Code.
		@return Credit Card Verification code on credit card
	  */
	public String getCreditCardVV () 
	{
		return (String)get_Value(COLUMNNAME_CreditCardVV);
	}

	/** Set Amount of the credit note.
		@param CreditMemo Amount of the credit note	  */
	public void setCreditMemo (BigDecimal CreditMemo)
	{
		set_Value (COLUMNNAME_CreditMemo, CreditMemo);
	}

	/** Get Amount of the credit note.
		@return Amount of the credit note	  */
	public BigDecimal getCreditMemo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CreditMemo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		if (DateAcct == null)
			throw new IllegalArgumentException ("DateAcct is mandatory.");
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		if (DateTrx == null)
			throw new IllegalArgumentException ("DateTrx is mandatory.");
		set_Value (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Discount Amount.
		@param DiscountAmt 
		Calculated amount of discount
	  */
	public void setDiscountAmt (BigDecimal DiscountAmt)
	{
		set_Value (COLUMNNAME_DiscountAmt, DiscountAmt);
	}

	/** Get Discount Amount.
		@return Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
		if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");
		if (DocAction.equals("CO") || DocAction.equals("AP") || DocAction.equals("RJ") || DocAction.equals("PO") || DocAction.equals("VO") || DocAction.equals("CL") || DocAction.equals("RC") || DocAction.equals("RA") || DocAction.equals("IN") || DocAction.equals("RE") || DocAction.equals("--") || DocAction.equals("PR") || DocAction.equals("XL") || DocAction.equals("WC")); else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - CO - AP - RJ - PO - VO - CL - RC - RA - IN - RE - -- - PR - XL - WC");		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
		if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
		if (DocStatus.equals("DR") || DocStatus.equals("CO") || DocStatus.equals("AP") || DocStatus.equals("NA") || DocStatus.equals("VO") || DocStatus.equals("IN") || DocStatus.equals("RE") || DocStatus.equals("CL") || DocStatus.equals("??") || DocStatus.equals("IP") || DocStatus.equals("WP") || DocStatus.equals("WC")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - DR - CO - AP - NA - VO - IN - RE - CL - ?? - IP - WP - WC");		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getDocumentNo());
    }

	/** Set System Count.
		@param EndingBalance 
		Ending  or closing balance
	  */
	public void setEndingBalance (BigDecimal EndingBalance)
	{
		set_Value (COLUMNNAME_EndingBalance, EndingBalance);
	}

	/** Get System Count.
		@return Ending  or closing balance
	  */
	public BigDecimal getEndingBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_EndingBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_EXME_AdjustmentType getEXME_AdjustmentType() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_AdjustmentType.Table_Name);
        I_EXME_AdjustmentType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_AdjustmentType)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_AdjustmentType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Adjustment Type.
		@param EXME_AdjustmentType_ID Adjustment Type	  */
	public void setEXME_AdjustmentType_ID (int EXME_AdjustmentType_ID)
	{
		if (EXME_AdjustmentType_ID < 1) 
			set_Value (COLUMNNAME_EXME_AdjustmentType_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_AdjustmentType_ID, Integer.valueOf(EXME_AdjustmentType_ID));
	}

	/** Get Adjustment Type.
		@return Adjustment Type	  */
	public int getEXME_AdjustmentType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AdjustmentType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CitaMedica getEXME_CitaMedica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CitaMedica.Table_Name);
        I_EXME_CitaMedica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CitaMedica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CitaMedica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Medical Appointment.
		@param EXME_CitaMedica_ID 
		Medical appointment
	  */
	public void setEXME_CitaMedica_ID (int EXME_CitaMedica_ID)
	{
		if (EXME_CitaMedica_ID < 1) 
			set_Value (COLUMNNAME_EXME_CitaMedica_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_CitaMedica_ID, Integer.valueOf(EXME_CitaMedica_ID));
	}

	/** Get Medical Appointment.
		@return Medical appointment
	  */
	public int getEXME_CitaMedica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CitaMedica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ClaimPayment getEXME_ClaimPayment() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ClaimPayment.Table_Name);
        I_EXME_ClaimPayment result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ClaimPayment)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ClaimPayment_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Claim Transaction.
		@param EXME_ClaimPayment_ID 
		Claim Transaction
	  */
	public void setEXME_ClaimPayment_ID (int EXME_ClaimPayment_ID)
	{
		if (EXME_ClaimPayment_ID < 1) 
			set_Value (COLUMNNAME_EXME_ClaimPayment_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_ClaimPayment_ID, Integer.valueOf(EXME_ClaimPayment_ID));
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

	/** Set Encounter.
		@param EXME_CtaPac_ID 
		Encounter
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1) 
			set_Value (COLUMNNAME_EXME_CtaPac_ID, null);
		else 
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

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1) 
			set_Value (COLUMNNAME_EXME_Paciente_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_PartidaPres getEXME_PartidaPres() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_PartidaPres.Table_Name);
        I_EXME_PartidaPres result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_PartidaPres)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_PartidaPres_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Budget Item.
		@param EXME_PartidaPres_ID 
		Budget Item
	  */
	public void setEXME_PartidaPres_ID (int EXME_PartidaPres_ID)
	{
		if (EXME_PartidaPres_ID < 1) 
			set_Value (COLUMNNAME_EXME_PartidaPres_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_PartidaPres_ID, Integer.valueOf(EXME_PartidaPres_ID));
	}

	/** Get Budget Item.
		@return Budget Item
	  */
	public int getEXME_PartidaPres_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PartidaPres_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Allocated.
		@param IsAllocated 
		Indicates if the payment has been allocated
	  */
	public void setIsAllocated (boolean IsAllocated)
	{
		set_Value (COLUMNNAME_IsAllocated, Boolean.valueOf(IsAllocated));
	}

	/** Get Allocated.
		@return Indicates if the payment has been allocated
	  */
	public boolean isAllocated () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllocated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_ValueNoCheck (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Delayed Capture.
		@param IsDelayedCapture 
		Charge after Shipment
	  */
	public void setIsDelayedCapture (boolean IsDelayedCapture)
	{
		set_Value (COLUMNNAME_IsDelayedCapture, Boolean.valueOf(IsDelayedCapture));
	}

	/** Get Delayed Capture.
		@return Charge after Shipment
	  */
	public boolean isDelayedCapture () 
	{
		Object oo = get_Value(COLUMNNAME_IsDelayedCapture);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Online Access.
		@param IsOnline 
		Can be accessed online 
	  */
	public void setIsOnline (boolean IsOnline)
	{
		set_Value (COLUMNNAME_IsOnline, Boolean.valueOf(IsOnline));
	}

	/** Get Online Access.
		@return Can be accessed online 
	  */
	public boolean isOnline () 
	{
		Object oo = get_Value(COLUMNNAME_IsOnline);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Over/Under Payment.
		@param IsOverUnderPayment 
		Over-Payment (unallocated) or Under-Payment (partial payment)
	  */
	public void setIsOverUnderPayment (boolean IsOverUnderPayment)
	{
		set_Value (COLUMNNAME_IsOverUnderPayment, Boolean.valueOf(IsOverUnderPayment));
	}

	/** Get Over/Under Payment.
		@return Over-Payment (unallocated) or Under-Payment (partial payment)
	  */
	public boolean isOverUnderPayment () 
	{
		Object oo = get_Value(COLUMNNAME_IsOverUnderPayment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Prepayment.
		@param IsPrepayment 
		The Payment/Receipt is a Prepayment
	  */
	public void setIsPrepayment (boolean IsPrepayment)
	{
		set_Value (COLUMNNAME_IsPrepayment, Boolean.valueOf(IsPrepayment));
	}

	/** Get Prepayment.
		@return The Payment/Receipt is a Prepayment
	  */
	public boolean isPrepayment () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrepayment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Receipt.
		@param IsReceipt 
		This is a sales transaction (receipt)
	  */
	public void setIsReceipt (boolean IsReceipt)
	{
		set_Value (COLUMNNAME_IsReceipt, Boolean.valueOf(IsReceipt));
	}

	/** Get Receipt.
		@return This is a sales transaction (receipt)
	  */
	public boolean isReceipt () 
	{
		Object oo = get_Value(COLUMNNAME_IsReceipt);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Reconciled.
		@param IsReconciled 
		Payment is reconciled with bank statement
	  */
	public void setIsReconciled (boolean IsReconciled)
	{
		set_Value (COLUMNNAME_IsReconciled, Boolean.valueOf(IsReconciled));
	}

	/** Get Reconciled.
		@return Payment is reconciled with bank statement
	  */
	public boolean isReconciled () 
	{
		Object oo = get_Value(COLUMNNAME_IsReconciled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Self-Service.
		@param IsSelfService 
		This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService)
	{
		set_Value (COLUMNNAME_IsSelfService, Boolean.valueOf(IsSelfService));
	}

	/** Get Self-Service.
		@return This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService () 
	{
		Object oo = get_Value(COLUMNNAME_IsSelfService);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Micr.
		@param Micr 
		Combination of routing no, account and check no
	  */
	public void setMicr (String Micr)
	{
		set_Value (COLUMNNAME_Micr, Micr);
	}

	/** Get Micr.
		@return Combination of routing no, account and check no
	  */
	public String getMicr () 
	{
		return (String)get_Value(COLUMNNAME_Micr);
	}

	/** Set Online Processing.
		@param OProcessing 
		This payment can be processed online
	  */
	public void setOProcessing (String OProcessing)
	{
		set_Value (COLUMNNAME_OProcessing, OProcessing);
	}

	/** Get Online Processing.
		@return This payment can be processed online
	  */
	public String getOProcessing () 
	{
		return (String)get_Value(COLUMNNAME_OProcessing);
	}

	/** Set Original Transaction.
		@param Orig_TrxID 
		Original Transaction
	  */
	public void setOrig_TrxID (String Orig_TrxID)
	{
		set_Value (COLUMNNAME_Orig_TrxID, Orig_TrxID);
	}

	/** Get Original Transaction.
		@return Original Transaction
	  */
	public String getOrig_TrxID () 
	{
		return (String)get_Value(COLUMNNAME_Orig_TrxID);
	}

	/** Set Over/Under Payment.
		@param OverUnderAmt 
		Over-Payment (unallocated) or Under-Payment (partial payment) Amount
	  */
	public void setOverUnderAmt (BigDecimal OverUnderAmt)
	{
		set_Value (COLUMNNAME_OverUnderAmt, OverUnderAmt);
	}

	/** Get Over/Under Payment.
		@return Over-Payment (unallocated) or Under-Payment (partial payment) Amount
	  */
	public BigDecimal getOverUnderAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OverUnderAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Payment amount.
		@param PayAmt 
		Amount being paid
	  */
	public void setPayAmt (BigDecimal PayAmt)
	{
		if (PayAmt == null)
			throw new IllegalArgumentException ("PayAmt is mandatory.");
		set_Value (COLUMNNAME_PayAmt, PayAmt);
	}

	/** Get Payment amount.
		@return Amount being paid
	  */
	public BigDecimal getPayAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PayAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** PayConcept AD_Reference_ID=1200525 */
	public static final int PAYCONCEPT_AD_Reference_ID=1200525;
	/** CoPay = C */
	public static final String PAYCONCEPT_CoPay = "C";
	/** Deductible = D */
	public static final String PAYCONCEPT_Deductible = "D";
	/** Others = O */
	public static final String PAYCONCEPT_Others = "O";
	/** Coinsurance = I */
	public static final String PAYCONCEPT_Coinsurance = "I";
	/** Payment = P */
	public static final String PAYCONCEPT_Payment = "P";
	/** Adjustment = A */
	public static final String PAYCONCEPT_Adjustment = "A";
	/** CopayPayment = Y */
	public static final String PAYCONCEPT_CopayPayment = "Y";
	/** DeductiblePayment = E */
	public static final String PAYCONCEPT_DeductiblePayment = "E";
	/** CoinsurancePayment = N */
	public static final String PAYCONCEPT_CoinsurancePayment = "N";
	/** OthersPayment = T */
	public static final String PAYCONCEPT_OthersPayment = "T";
	/** Bad Debt Adjustment = B */
	public static final String PAYCONCEPT_BadDebtAdjustment = "B";
	/** Patient Adjustments = G */
	public static final String PAYCONCEPT_PatientAdjustments = "G";
	/** Insurance Payments = M */
	public static final String PAYCONCEPT_InsurancePayments = "M";
	/** Global Invoice = F */
	public static final String PAYCONCEPT_GlobalInvoice = "F";
	/** Credit Note Prompt Payment = H */
	public static final String PAYCONCEPT_CreditNotePromptPayment = "H";
	/** Credit Note Pacial Payment = J */
	public static final String PAYCONCEPT_CreditNotePacialPayment = "J";
	/** Internal Use Inventory = S */
	public static final String PAYCONCEPT_InternalUseInventory = "S";
	/** Set PayConcept.
		@param PayConcept PayConcept	  */
	public void setPayConcept (String PayConcept)
	{

		if (PayConcept == null || PayConcept.equals("C") || PayConcept.equals("D") || PayConcept.equals("O") || PayConcept.equals("I") || PayConcept.equals("P") || PayConcept.equals("A") || PayConcept.equals("Y") || PayConcept.equals("E") || PayConcept.equals("N") || PayConcept.equals("T") || PayConcept.equals("B") || PayConcept.equals("G") || PayConcept.equals("M") || PayConcept.equals("F") || PayConcept.equals("H") || PayConcept.equals("J") || PayConcept.equals("S")); else throw new IllegalArgumentException ("PayConcept Invalid value - " + PayConcept + " - Reference_ID=1200525 - C - D - O - I - P - A - Y - E - N - T - B - G - M - F - H - J - S");		set_Value (COLUMNNAME_PayConcept, PayConcept);
	}

	/** Get PayConcept.
		@return PayConcept	  */
	public String getPayConcept () 
	{
		return (String)get_Value(COLUMNNAME_PayConcept);
	}

	/** PaymentType AD_Reference_ID=1200566 */
	public static final int PAYMENTTYPE_AD_Reference_ID=1200566;
	/** ACH = ACH */
	public static final String PAYMENTTYPE_ACH = "ACH";
	/** CreditCard = CC */
	public static final String PAYMENTTYPE_CreditCard = "CC";
	/** ERA = ERA */
	public static final String PAYMENTTYPE_ERA = "ERA";
	/** Mail = M */
	public static final String PAYMENTTYPE_Mail = "M";
	/** Cash = CA */
	public static final String PAYMENTTYPE_Cash = "CA";
	/** Check = CH */
	public static final String PAYMENTTYPE_Check = "CH";
	/** Set PaymentType.
		@param PaymentType 
		Billing Payment
	  */
	public void setPaymentType (String PaymentType)
	{

		if (PaymentType == null || PaymentType.equals("ACH") || PaymentType.equals("CC") || PaymentType.equals("ERA") || PaymentType.equals("M") || PaymentType.equals("CA") || PaymentType.equals("CH")); else throw new IllegalArgumentException ("PaymentType Invalid value - " + PaymentType + " - Reference_ID=1200566 - ACH - CC - ERA - M - CA - CH");		set_Value (COLUMNNAME_PaymentType, PaymentType);
	}

	/** Get PaymentType.
		@return Billing Payment
	  */
	public String getPaymentType () 
	{
		return (String)get_Value(COLUMNNAME_PaymentType);
	}

	/** Set PO Number.
		@param PONum 
		Purchase Order Number
	  */
	public void setPONum (String PONum)
	{
		set_Value (COLUMNNAME_PONum, PONum);
	}

	/** Get PO Number.
		@return Purchase Order Number
	  */
	public String getPONum () 
	{
		return (String)get_Value(COLUMNNAME_PONum);
	}

	/** Set Posted.
		@param Posted 
		Posting status
	  */
	public void setPosted (boolean Posted)
	{
		set_Value (COLUMNNAME_Posted, Boolean.valueOf(Posted));
	}

	/** Get Posted.
		@return Posting status
	  */
	public boolean isPosted () 
	{
		Object oo = get_Value(COLUMNNAME_Posted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Rate.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_Value (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Authorization Code.
		@param R_AuthCode 
		Authorization Code returned
	  */
	public void setR_AuthCode (String R_AuthCode)
	{
		set_ValueNoCheck (COLUMNNAME_R_AuthCode, R_AuthCode);
	}

	/** Get Authorization Code.
		@return Authorization Code returned
	  */
	public String getR_AuthCode () 
	{
		return (String)get_Value(COLUMNNAME_R_AuthCode);
	}

	/** Set Authorization Code (DC).
		@param R_AuthCode_DC 
		Authorization Code Delayed Capture returned
	  */
	public void setR_AuthCode_DC (String R_AuthCode_DC)
	{
		set_ValueNoCheck (COLUMNNAME_R_AuthCode_DC, R_AuthCode_DC);
	}

	/** Get Authorization Code (DC).
		@return Authorization Code Delayed Capture returned
	  */
	public String getR_AuthCode_DC () 
	{
		return (String)get_Value(COLUMNNAME_R_AuthCode_DC);
	}

	/** R_AvsAddr AD_Reference_ID=213 */
	public static final int R_AVSADDR_AD_Reference_ID=213;
	/** Match = Y */
	public static final String R_AVSADDR_Match = "Y";
	/** No Match = N */
	public static final String R_AVSADDR_NoMatch = "N";
	/** Unavailable = X */
	public static final String R_AVSADDR_Unavailable = "X";
	/** Set Address verified.
		@param R_AvsAddr 
		This address has been verified
	  */
	public void setR_AvsAddr (String R_AvsAddr)
	{

		if (R_AvsAddr == null || R_AvsAddr.equals("Y") || R_AvsAddr.equals("N") || R_AvsAddr.equals("X")); else throw new IllegalArgumentException ("R_AvsAddr Invalid value - " + R_AvsAddr + " - Reference_ID=213 - Y - N - X");		set_ValueNoCheck (COLUMNNAME_R_AvsAddr, R_AvsAddr);
	}

	/** Get Address verified.
		@return This address has been verified
	  */
	public String getR_AvsAddr () 
	{
		return (String)get_Value(COLUMNNAME_R_AvsAddr);
	}

	/** R_AvsZip AD_Reference_ID=213 */
	public static final int R_AVSZIP_AD_Reference_ID=213;
	/** Match = Y */
	public static final String R_AVSZIP_Match = "Y";
	/** No Match = N */
	public static final String R_AVSZIP_NoMatch = "N";
	/** Unavailable = X */
	public static final String R_AVSZIP_Unavailable = "X";
	/** Set Zip verified.
		@param R_AvsZip 
		The Zip Code has been verified
	  */
	public void setR_AvsZip (String R_AvsZip)
	{

		if (R_AvsZip == null || R_AvsZip.equals("Y") || R_AvsZip.equals("N") || R_AvsZip.equals("X")); else throw new IllegalArgumentException ("R_AvsZip Invalid value - " + R_AvsZip + " - Reference_ID=213 - Y - N - X");		set_ValueNoCheck (COLUMNNAME_R_AvsZip, R_AvsZip);
	}

	/** Get Zip verified.
		@return The Zip Code has been verified
	  */
	public String getR_AvsZip () 
	{
		return (String)get_Value(COLUMNNAME_R_AvsZip);
	}

	/** Set CVV Match.
		@param R_CVV2Match 
		Credit Card Verification Code Match
	  */
	public void setR_CVV2Match (boolean R_CVV2Match)
	{
		set_ValueNoCheck (COLUMNNAME_R_CVV2Match, Boolean.valueOf(R_CVV2Match));
	}

	/** Get CVV Match.
		@return Credit Card Verification Code Match
	  */
	public boolean isR_CVV2Match () 
	{
		Object oo = get_Value(COLUMNNAME_R_CVV2Match);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Payment Receipt No..
		@param ReciboNo 
		Payment receipt No in cash book
	  */
	public void setReciboNo (String ReciboNo)
	{
		if (ReciboNo == null)
			throw new IllegalArgumentException ("ReciboNo is mandatory.");
		set_Value (COLUMNNAME_ReciboNo, ReciboNo);
	}

	/** Get Payment Receipt No..
		@return Payment receipt No in cash book
	  */
	public String getReciboNo () 
	{
		return (String)get_Value(COLUMNNAME_ReciboNo);
	}

	/** Set Referenced Invoice.
		@param Ref_Invoice_ID Referenced Invoice	  */
	public void setRef_Invoice_ID (int Ref_Invoice_ID)
	{
		if (Ref_Invoice_ID < 1) 
			set_Value (COLUMNNAME_Ref_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_Invoice_ID, Integer.valueOf(Ref_Invoice_ID));
	}

	/** Get Referenced Invoice.
		@return Referenced Invoice	  */
	public int getRef_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Referenced Payment.
		@param Ref_Payment_ID Referenced Payment	  */
	public void setRef_Payment_ID (int Ref_Payment_ID)
	{
		if (Ref_Payment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Ref_Payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Ref_Payment_ID, Integer.valueOf(Ref_Payment_ID));
	}

	/** Get Referenced Payment.
		@return Referenced Payment	  */
	public int getRef_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reversal ID.
		@param Reversal_ID 
		ID of document reversal
	  */
	public void setReversal_ID (int Reversal_ID)
	{
		if (Reversal_ID < 1) 
			set_Value (COLUMNNAME_Reversal_ID, null);
		else 
			set_Value (COLUMNNAME_Reversal_ID, Integer.valueOf(Reversal_ID));
	}

	/** Get Reversal ID.
		@return ID of document reversal
	  */
	public int getReversal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Reversal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Info.
		@param R_Info 
		Response info
	  */
	public void setR_Info (String R_Info)
	{
		set_ValueNoCheck (COLUMNNAME_R_Info, R_Info);
	}

	/** Get Info.
		@return Response info
	  */
	public String getR_Info () 
	{
		return (String)get_Value(COLUMNNAME_R_Info);
	}

	/** Set Routing No.
		@param RoutingNo 
		Bank Routing Number
	  */
	public void setRoutingNo (String RoutingNo)
	{
		set_Value (COLUMNNAME_RoutingNo, RoutingNo);
	}

	/** Get Routing No.
		@return Bank Routing Number
	  */
	public String getRoutingNo () 
	{
		return (String)get_Value(COLUMNNAME_RoutingNo);
	}

	/** Set Reference.
		@param R_PnRef 
		Payment reference
	  */
	public void setR_PnRef (String R_PnRef)
	{
		set_ValueNoCheck (COLUMNNAME_R_PnRef, R_PnRef);
	}

	/** Get Reference.
		@return Payment reference
	  */
	public String getR_PnRef () 
	{
		return (String)get_Value(COLUMNNAME_R_PnRef);
	}

	/** Set Reference (DC).
		@param R_PnRef_DC 
		Payment Reference Delayed Capture
	  */
	public void setR_PnRef_DC (String R_PnRef_DC)
	{
		set_ValueNoCheck (COLUMNNAME_R_PnRef_DC, R_PnRef_DC);
	}

	/** Get Reference (DC).
		@return Payment Reference Delayed Capture
	  */
	public String getR_PnRef_DC () 
	{
		return (String)get_Value(COLUMNNAME_R_PnRef_DC);
	}

	/** Set Response Message.
		@param R_RespMsg 
		Response message
	  */
	public void setR_RespMsg (String R_RespMsg)
	{
		set_ValueNoCheck (COLUMNNAME_R_RespMsg, R_RespMsg);
	}

	/** Get Response Message.
		@return Response message
	  */
	public String getR_RespMsg () 
	{
		return (String)get_Value(COLUMNNAME_R_RespMsg);
	}

	/** Set Result.
		@param R_Result 
		Result of transmission
	  */
	public void setR_Result (String R_Result)
	{
		set_ValueNoCheck (COLUMNNAME_R_Result, R_Result);
	}

	/** Get Result.
		@return Result of transmission
	  */
	public String getR_Result () 
	{
		return (String)get_Value(COLUMNNAME_R_Result);
	}

	/** Set Swipe.
		@param Swipe 
		Track 1 and 2 of the Credit Card
	  */
	public void setSwipe (String Swipe)
	{
		set_ValueNoCheck (COLUMNNAME_Swipe, Swipe);
	}

	/** Get Swipe.
		@return Track 1 and 2 of the Credit Card
	  */
	public String getSwipe () 
	{
		return (String)get_Value(COLUMNNAME_Swipe);
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** TenderType AD_Reference_ID=214 */
	public static final int TENDERTYPE_AD_Reference_ID=214;
	/** Credit Card = C */
	public static final String TENDERTYPE_CreditCard = "C";
	/** Check = K */
	public static final String TENDERTYPE_Check = "K";
	/** Direct Deposit = A */
	public static final String TENDERTYPE_DirectDeposit = "A";
	/** Direct Debit = D */
	public static final String TENDERTYPE_DirectDebit = "D";
	/** Other = O */
	public static final String TENDERTYPE_Other = "O";
	/** Account = T */
	public static final String TENDERTYPE_Account = "T";
	/** Cash = X */
	public static final String TENDERTYPE_Cash = "X";
	/** Set Tender type.
		@param TenderType 
		Method of Payment
	  */
	public void setTenderType (String TenderType)
	{
		if (TenderType == null) throw new IllegalArgumentException ("TenderType is mandatory");
		if (TenderType.equals("C") || TenderType.equals("K") || TenderType.equals("A") || TenderType.equals("D") || TenderType.equals("O") || TenderType.equals("T") || TenderType.equals("X")); else throw new IllegalArgumentException ("TenderType Invalid value - " + TenderType + " - Reference_ID=214 - C - K - A - D - O - T - X");		set_Value (COLUMNNAME_TenderType, TenderType);
	}

	/** Get Tender type.
		@return Method of Payment
	  */
	public String getTenderType () 
	{
		return (String)get_Value(COLUMNNAME_TenderType);
	}

	/** TrxType AD_Reference_ID=215 */
	public static final int TRXTYPE_AD_Reference_ID=215;
	/** Sales = S */
	public static final String TRXTYPE_Sales = "S";
	/** Delayed Capture = D */
	public static final String TRXTYPE_DelayedCapture = "D";
	/** Credit (Payment) = C */
	public static final String TRXTYPE_CreditPayment = "C";
	/** Voice Authorization = F */
	public static final String TRXTYPE_VoiceAuthorization = "F";
	/** Authorization = A */
	public static final String TRXTYPE_Authorization = "A";
	/** Void = V */
	public static final String TRXTYPE_Void = "V";
	/** Set Transaction Type.
		@param TrxType 
		Type of credit card transaction
	  */
	public void setTrxType (String TrxType)
	{
		if (TrxType == null) throw new IllegalArgumentException ("TrxType is mandatory");
		if (TrxType.equals("S") || TrxType.equals("D") || TrxType.equals("C") || TrxType.equals("F") || TrxType.equals("A") || TrxType.equals("V")); else throw new IllegalArgumentException ("TrxType Invalid value - " + TrxType + " - Reference_ID=215 - S - D - C - F - A - V");		set_Value (COLUMNNAME_TrxType, TrxType);
	}

	/** Get Transaction Type.
		@return Type of credit card transaction
	  */
	public String getTrxType () 
	{
		return (String)get_Value(COLUMNNAME_TrxType);
	}

	/** Type AD_Reference_ID=1200664 */
	public static final int TYPE_AD_Reference_ID=1200664;
	/** AdvancePayment = A */
	public static final String TYPE_AdvancePayment = "A";
	/** InvoicePayment = C */
	public static final String TYPE_InvoicePayment = "C";
	/** Payment = P */
	public static final String TYPE_Payment = "P";
	/** PaymentRefund = R */
	public static final String TYPE_PaymentRefund = "R";
	/** Set Type.
		@param Type 
		Type of Validation
	  */
	public void setType (String Type)
	{

		if (Type == null || Type.equals("A") || Type.equals("C") || Type.equals("P") || Type.equals("R")); else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=1200664 - A - C - P - R");		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}

	/** Set User List 1.
		@param User1_ID 
		User defined list element #1
	  */
	public void setUser1_ID (int User1_ID)
	{
		if (User1_ID < 1) 
			set_Value (COLUMNNAME_User1_ID, null);
		else 
			set_Value (COLUMNNAME_User1_ID, Integer.valueOf(User1_ID));
	}

	/** Get User List 1.
		@return User defined list element #1
	  */
	public int getUser1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User List 2.
		@param User2_ID 
		User defined list element #2
	  */
	public void setUser2_ID (int User2_ID)
	{
		if (User2_ID < 1) 
			set_Value (COLUMNNAME_User2_ID, null);
		else 
			set_Value (COLUMNNAME_User2_ID, Integer.valueOf(User2_ID));
	}

	/** Get User List 2.
		@return User defined list element #2
	  */
	public int getUser2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Policy.
		@param VerPoliza Policy	  */
	public void setVerPoliza (String VerPoliza)
	{
		set_Value (COLUMNNAME_VerPoliza, VerPoliza);
	}

	/** Get Policy.
		@return Policy	  */
	public String getVerPoliza () 
	{
		return (String)get_Value(COLUMNNAME_VerPoliza);
	}

	/** Set Voice authorization code.
		@param VoiceAuthCode 
		Voice Authorization Code from credit card company
	  */
	public void setVoiceAuthCode (String VoiceAuthCode)
	{
		set_Value (COLUMNNAME_VoiceAuthCode, VoiceAuthCode);
	}

	/** Get Voice authorization code.
		@return Voice Authorization Code from credit card company
	  */
	public String getVoiceAuthCode () 
	{
		return (String)get_Value(COLUMNNAME_VoiceAuthCode);
	}

	/** Set Write-off Amount.
		@param WriteOffAmt 
		Amount to write-off
	  */
	public void setWriteOffAmt (BigDecimal WriteOffAmt)
	{
		set_Value (COLUMNNAME_WriteOffAmt, WriteOffAmt);
	}

	/** Get Write-off Amount.
		@return Amount to write-off
	  */
	public BigDecimal getWriteOffAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_WriteOffAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}