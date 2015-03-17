/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_FormaPago
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_FormaPago extends PO implements I_EXME_FormaPago, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FormaPago (Properties ctx, int EXME_FormaPago_ID, String trxName)
    {
      super (ctx, EXME_FormaPago_ID, trxName);
      /** if (EXME_FormaPago_ID == 0)
        {
			setC_PaymentTerm_ID (0);
			setEsDevol (false);
// N
			setEXME_FormaPago_ID (0);
			setName (null);
			setPaymentRule (null);
			setPosteaCaja (true);
// Y
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_FormaPago (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_FormaPago[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_PaymentTerm.Table_Name);
        I_C_PaymentTerm result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_PaymentTerm)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_PaymentTerm_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Payment Term.
		@param C_PaymentTerm_ID 
		The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
	{
		if (C_PaymentTerm_ID < 1)
			 throw new IllegalArgumentException ("C_PaymentTerm_ID is mandatory.");
		set_Value (COLUMNNAME_C_PaymentTerm_ID, Integer.valueOf(C_PaymentTerm_ID));
	}

	/** Get Payment Term.
		@return The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentTerm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set IsReturn.
		@param EsDevol 
		Indicates if the form of payment is (isn`t) return
	  */
	public void setEsDevol (boolean EsDevol)
	{
		set_Value (COLUMNNAME_EsDevol, Boolean.valueOf(EsDevol));
	}

	/** Get IsReturn.
		@return Indicates if the form of payment is (isn`t) return
	  */
	public boolean isEsDevol () 
	{
		Object oo = get_Value(COLUMNNAME_EsDevol);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Payment Form.
		@param EXME_FormaPago_ID 
		Identifier of Payment Form
	  */
	public void setEXME_FormaPago_ID (int EXME_FormaPago_ID)
	{
		if (EXME_FormaPago_ID < 1)
			 throw new IllegalArgumentException ("EXME_FormaPago_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FormaPago_ID, Integer.valueOf(EXME_FormaPago_ID));
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULE_Cash = "B";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Other = O */
	public static final String PAYMENTRULE_Other = "O";
//	/** Prepayment = A */
//	public static final String PAYMENTRULE_Prepayment = "A";
	/** Mixed = M */
	public static final String PAYMENTRULE_Mixed = "M";
	/** Set Payment Rule.
		@param PaymentRule 
		How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule)
	{
		if (PaymentRule == null) throw new IllegalArgumentException ("PaymentRule is mandatory");
		if (PaymentRule.equals("B") || PaymentRule.equals("K") || PaymentRule.equals("T") || PaymentRule.equals("S") || PaymentRule.equals("P") || PaymentRule.equals("D") || PaymentRule.equals("O") || PaymentRule.equals("A") || PaymentRule.equals("M")); else throw new IllegalArgumentException ("PaymentRule Invalid value - " + PaymentRule + " - Reference_ID=195 - B - K - T - S - P - D - O - A - M");		set_Value (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
	}

	/** Set Post in Cash book.
		@param PosteaCaja 
		Indicates if post in cash book
	  */
	public void setPosteaCaja (boolean PosteaCaja)
	{
		set_Value (COLUMNNAME_PosteaCaja, Boolean.valueOf(PosteaCaja));
	}

	/** Get Post in Cash book.
		@return Indicates if post in cash book
	  */
	public boolean isPosteaCaja () 
	{
		Object oo = get_Value(COLUMNNAME_PosteaCaja);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Ref. Payment Form.
		@param Ref_FormaPago_ID 
		if payment form is a return, this field says to which payment form is associated this return.
	  */
	public void setRef_FormaPago_ID (int Ref_FormaPago_ID)
	{
		if (Ref_FormaPago_ID < 1) 
			set_Value (COLUMNNAME_Ref_FormaPago_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_FormaPago_ID, Integer.valueOf(Ref_FormaPago_ID));
	}

	/** Get Ref. Payment Form.
		@return if payment form is a return, this field says to which payment form is associated this return.
	  */
	public int getRef_FormaPago_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_FormaPago_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}