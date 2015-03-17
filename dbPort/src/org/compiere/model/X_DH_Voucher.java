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

/** Generated Model for DH_Voucher
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_DH_Voucher extends PO implements I_DH_Voucher, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_DH_Voucher (Properties ctx, int DH_Voucher_ID, String trxName)
    {
      super (ctx, DH_Voucher_ID, trxName);
      /** if (DH_Voucher_ID == 0)
        {
			setDateTrx (new Timestamp( System.currentTimeMillis() ));
			setDH_PrePayment_ID (0);
			setDH_Voucher_ID (0);
			setGrandTotal (Env.ZERO);
			setTaxAmt (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_DH_Voucher (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_DH_Voucher[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
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

	public I_DH_PrePayment getDH_PrePayment() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_DH_PrePayment.Table_Name);
        I_DH_PrePayment result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_DH_PrePayment)constructor.newInstance(new Object[] {getCtx(), new Integer(getDH_PrePayment_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Pre Payment.
		@param DH_PrePayment_ID Pre Payment	  */
	public void setDH_PrePayment_ID (int DH_PrePayment_ID)
	{
		if (DH_PrePayment_ID < 1)
			 throw new IllegalArgumentException ("DH_PrePayment_ID is mandatory.");
		set_Value (COLUMNNAME_DH_PrePayment_ID, Integer.valueOf(DH_PrePayment_ID));
	}

	/** Get Pre Payment.
		@return Pre Payment	  */
	public int getDH_PrePayment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DH_PrePayment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Voucher.
		@param DH_Voucher_ID Voucher	  */
	public void setDH_Voucher_ID (int DH_Voucher_ID)
	{
		if (DH_Voucher_ID < 1)
			 throw new IllegalArgumentException ("DH_Voucher_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_DH_Voucher_ID, Integer.valueOf(DH_Voucher_ID));
	}

	/** Get Voucher.
		@return Voucher	  */
	public int getDH_Voucher_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DH_Voucher_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_FormaPago getEXME_FormaPago() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_FormaPago.Table_Name);
        I_EXME_FormaPago result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_FormaPago)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_FormaPago_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Payment Form.
		@param EXME_FormaPago_ID 
		Identifier of Payment Form
	  */
	public void setEXME_FormaPago_ID (int EXME_FormaPago_ID)
	{
		if (EXME_FormaPago_ID < 1) 
			set_Value (COLUMNNAME_EXME_FormaPago_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_FormaPago_ID, Integer.valueOf(EXME_FormaPago_ID));
	}

	/** Get Payment Form.
		@return Identifier of Payment Form
	  */
	public int getEXME_FormaPago_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormaPago_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Grand Total.
		@param GrandTotal 
		Total amount of document
	  */
	public void setGrandTotal (BigDecimal GrandTotal)
	{
		if (GrandTotal == null)
			throw new IllegalArgumentException ("GrandTotal is mandatory.");
		set_Value (COLUMNNAME_GrandTotal, GrandTotal);
	}

	/** Get Grand Total.
		@return Total amount of document
	  */
	public BigDecimal getGrandTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GrandTotal);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Membership AD_Reference_ID=1200676 */
	public static final int MEMBERSHIP_AD_Reference_ID=1200676;
	/** WithOut = SM */
	public static final String MEMBERSHIP_WithOut = "SM";
	/** With = CM */
	public static final String MEMBERSHIP_With = "CM";
	/** Set Membership.
		@param Membership Membership	  */
	public void setMembership (String Membership)
	{

		if (Membership == null || Membership.equals("SM") || Membership.equals("CM")); else throw new IllegalArgumentException ("Membership Invalid value - " + Membership + " - Reference_ID=1200676 - SM - CM");		set_Value (COLUMNNAME_Membership, Membership);
	}

	/** Get Membership.
		@return Membership	  */
	public String getMembership () 
	{
		return (String)get_Value(COLUMNNAME_Membership);
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		if (TaxAmt == null)
			throw new IllegalArgumentException ("TaxAmt is mandatory.");
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

	/** Set Voucher.
		@param Voucher Voucher	  */
	public void setVoucher (String Voucher)
	{
		set_Value (COLUMNNAME_Voucher, Voucher);
	}

	/** Get Voucher.
		@return Voucher	  */
	public String getVoucher () 
	{
		return (String)get_Value(COLUMNNAME_Voucher);
	}
}