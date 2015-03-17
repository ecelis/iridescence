/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Info_Fin
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_A_Asset_Info_Fin extends PO implements I_A_Asset_Info_Fin, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Asset_Info_Fin (Properties ctx, int A_Asset_Info_Fin_ID, String trxName)
    {
      super (ctx, A_Asset_Info_Fin_ID, trxName);
      /** if (A_Asset_Info_Fin_ID == 0)
        {
			setA_Asset_ID (0);
			setA_Asset_Info_Fin_ID (0);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Info_Fin (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Info_Fin[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_Info_Fin_ID.
		@param A_Asset_Info_Fin_ID A_Asset_Info_Fin_ID	  */
	public void setA_Asset_Info_Fin_ID (int A_Asset_Info_Fin_ID)
	{
		if (A_Asset_Info_Fin_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Info_Fin_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_Info_Fin_ID, Integer.valueOf(A_Asset_Info_Fin_ID));
	}

	/** Get A_Asset_Info_Fin_ID.
		@return A_Asset_Info_Fin_ID	  */
	public int getA_Asset_Info_Fin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Info_Fin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Info_Fin_ID()));
    }

	/** Set Contract Date.
		@param A_Contract_Date Contract Date	  */
	public void setA_Contract_Date (Timestamp A_Contract_Date)
	{
		set_Value (COLUMNNAME_A_Contract_Date, A_Contract_Date);
	}

	/** Get Contract Date.
		@return Contract Date	  */
	public Timestamp getA_Contract_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Contract_Date);
	}

	/** A_Due_On AD_Reference_ID=1200319 */
	public static final int A_DUE_ON_AD_Reference_ID=1200319;
	/** 15th of every month = 15T */
	public static final String A_DUE_ON_15thOfEveryMonth = "15T";
	/** 1st of every month = 1st */
	public static final String A_DUE_ON_1stOfEveryMonth = "1st";
	/** Beginning of every month = BEG */
	public static final String A_DUE_ON_BeginningOfEveryMonth = "BEG";
	/** Yearly on or before contract date = YER */
	public static final String A_DUE_ON_YearlyOnOrBeforeContractDate = "YER";
	/** Set Payment Due Date.
		@param A_Due_On Payment Due Date	  */
	public void setA_Due_On (String A_Due_On)
	{

		if (A_Due_On == null || A_Due_On.equals("15T") || A_Due_On.equals("1st") || A_Due_On.equals("BEG") || A_Due_On.equals("YER")); else throw new IllegalArgumentException ("A_Due_On Invalid value - " + A_Due_On + " - Reference_ID=1200319 - 15T - 1st - BEG - YER");		set_Value (COLUMNNAME_A_Due_On, A_Due_On);
	}

	/** Get Payment Due Date.
		@return Payment Due Date	  */
	public String getA_Due_On () 
	{
		return (String)get_Value(COLUMNNAME_A_Due_On);
	}

	/** Set Contract Expiration Date.
		@param A_Expired_Date Contract Expiration Date	  */
	public void setA_Expired_Date (Timestamp A_Expired_Date)
	{
		set_Value (COLUMNNAME_A_Expired_Date, A_Expired_Date);
	}

	/** Get Contract Expiration Date.
		@return Contract Expiration Date	  */
	public Timestamp getA_Expired_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Expired_Date);
	}

	/** A_Finance_Meth AD_Reference_ID=1200318 */
	public static final int A_FINANCE_METH_AD_Reference_ID=1200318;
	/** Capitalized Lease = CL */
	public static final String A_FINANCE_METH_CapitalizedLease = "CL";
	/** Non-Capitalized Lease = NL */
	public static final String A_FINANCE_METH_Non_CapitalizedLease = "NL";
	/** Owned = OW */
	public static final String A_FINANCE_METH_Owned = "OW";
	/** Rented = RE */
	public static final String A_FINANCE_METH_Rented = "RE";
	/** Set Finance Method.
		@param A_Finance_Meth Finance Method	  */
	public void setA_Finance_Meth (String A_Finance_Meth)
	{

		if (A_Finance_Meth == null || A_Finance_Meth.equals("CL") || A_Finance_Meth.equals("NL") || A_Finance_Meth.equals("OW") || A_Finance_Meth.equals("RE")); else throw new IllegalArgumentException ("A_Finance_Meth Invalid value - " + A_Finance_Meth + " - Reference_ID=1200318 - CL - NL - OW - RE");		set_Value (COLUMNNAME_A_Finance_Meth, A_Finance_Meth);
	}

	/** Get Finance Method.
		@return Finance Method	  */
	public String getA_Finance_Meth () 
	{
		return (String)get_Value(COLUMNNAME_A_Finance_Meth);
	}

	/** Set Monthly Payment.
		@param A_Monthly_Payment Monthly Payment	  */
	public void setA_Monthly_Payment (BigDecimal A_Monthly_Payment)
	{
		set_Value (COLUMNNAME_A_Monthly_Payment, A_Monthly_Payment);
	}

	/** Get Monthly Payment.
		@return Monthly Payment	  */
	public BigDecimal getA_Monthly_Payment () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Monthly_Payment);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Purchase Option.
		@param A_Purchase_Option Purchase Option	  */
	public void setA_Purchase_Option (boolean A_Purchase_Option)
	{
		set_Value (COLUMNNAME_A_Purchase_Option, Boolean.valueOf(A_Purchase_Option));
	}

	/** Get Purchase Option.
		@return Purchase Option	  */
	public boolean isA_Purchase_Option () 
	{
		Object oo = get_Value(COLUMNNAME_A_Purchase_Option);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Purchase Option Credit.
		@param A_Purchase_Option_Credit Purchase Option Credit	  */
	public void setA_Purchase_Option_Credit (int A_Purchase_Option_Credit)
	{
		set_Value (COLUMNNAME_A_Purchase_Option_Credit, Integer.valueOf(A_Purchase_Option_Credit));
	}

	/** Get Purchase Option Credit.
		@return Purchase Option Credit	  */
	public int getA_Purchase_Option_Credit () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Purchase_Option_Credit);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Purchase Option Credit %.
		@param A_Purchase_Option_Credit_Per Purchase Option Credit %	  */
	public void setA_Purchase_Option_Credit_Per (BigDecimal A_Purchase_Option_Credit_Per)
	{
		set_Value (COLUMNNAME_A_Purchase_Option_Credit_Per, A_Purchase_Option_Credit_Per);
	}

	/** Get Purchase Option Credit %.
		@return Purchase Option Credit %	  */
	public BigDecimal getA_Purchase_Option_Credit_Per () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Purchase_Option_Credit_Per);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Option Purchase Price.
		@param A_Purchase_Price Option Purchase Price	  */
	public void setA_Purchase_Price (BigDecimal A_Purchase_Price)
	{
		set_Value (COLUMNNAME_A_Purchase_Price, A_Purchase_Price);
	}

	/** Get Option Purchase Price.
		@return Option Purchase Price	  */
	public BigDecimal getA_Purchase_Price () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Purchase_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Business Partner.
		@param C_BPartner_ID 
		Identifier for a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifier for a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Text Message.
		@param TextMsg 
		Text Message
	  */
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	public String getTextMsg () 
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
	}
}