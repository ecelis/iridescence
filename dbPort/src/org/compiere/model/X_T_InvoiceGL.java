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

/** Generated Model for T_InvoiceGL
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_T_InvoiceGL extends PO implements I_T_InvoiceGL, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_T_InvoiceGL (Properties ctx, int T_InvoiceGL_ID, String trxName)
    {
      super (ctx, T_InvoiceGL_ID, trxName);
      /** if (T_InvoiceGL_ID == 0)
        {
			setAD_PInstance_ID (0);
			setAmtAcctBalance (Env.ZERO);
			setAmtRevalCr (Env.ZERO);
			setAmtRevalCrDiff (Env.ZERO);
			setAmtRevalDr (Env.ZERO);
			setAmtRevalDrDiff (Env.ZERO);
			setAmtSourceBalance (Env.ZERO);
			setC_ConversionTypeReval_ID (0);
			setC_Invoice_ID (0);
			setDateReval (new Timestamp( System.currentTimeMillis() ));
			setFact_Acct_ID (0);
			setGrandTotal (Env.ZERO);
			setIsAllCurrencies (false);
			setOpenAmt (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_T_InvoiceGL (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_T_InvoiceGL[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_PInstance getAD_PInstance() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_PInstance.Table_Name);
        I_AD_PInstance result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PInstance)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PInstance_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Process Instance.
		@param AD_PInstance_ID 
		Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID)
	{
		if (AD_PInstance_ID < 1)
			 throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
		set_Value (COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
	}

	/** Get Process Instance.
		@return Instance of the process
	  */
	public int getAD_PInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Accounted Balance.
		@param AmtAcctBalance 
		Accounted Balance Amount
	  */
	public void setAmtAcctBalance (BigDecimal AmtAcctBalance)
	{
		if (AmtAcctBalance == null)
			throw new IllegalArgumentException ("AmtAcctBalance is mandatory.");
		set_Value (COLUMNNAME_AmtAcctBalance, AmtAcctBalance);
	}

	/** Get Accounted Balance.
		@return Accounted Balance Amount
	  */
	public BigDecimal getAmtAcctBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtAcctBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Revaluated Credit Amount.
		@param AmtRevalCr 
		Revaluated Credit Amount
	  */
	public void setAmtRevalCr (BigDecimal AmtRevalCr)
	{
		if (AmtRevalCr == null)
			throw new IllegalArgumentException ("AmtRevalCr is mandatory.");
		set_Value (COLUMNNAME_AmtRevalCr, AmtRevalCr);
	}

	/** Get Revaluated Credit Amount.
		@return Revaluated Credit Amount
	  */
	public BigDecimal getAmtRevalCr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtRevalCr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Revaluated Credit Amount Difference.
		@param AmtRevalCrDiff 
		Revaluated Credit Amount Difference
	  */
	public void setAmtRevalCrDiff (BigDecimal AmtRevalCrDiff)
	{
		if (AmtRevalCrDiff == null)
			throw new IllegalArgumentException ("AmtRevalCrDiff is mandatory.");
		set_Value (COLUMNNAME_AmtRevalCrDiff, AmtRevalCrDiff);
	}

	/** Get Revaluated Credit Amount Difference.
		@return Revaluated Credit Amount Difference
	  */
	public BigDecimal getAmtRevalCrDiff () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtRevalCrDiff);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Revaluated Debit Amount.
		@param AmtRevalDr 
		Revaluated Debit Amount
	  */
	public void setAmtRevalDr (BigDecimal AmtRevalDr)
	{
		if (AmtRevalDr == null)
			throw new IllegalArgumentException ("AmtRevalDr is mandatory.");
		set_Value (COLUMNNAME_AmtRevalDr, AmtRevalDr);
	}

	/** Get Revaluated Debit Amount.
		@return Revaluated Debit Amount
	  */
	public BigDecimal getAmtRevalDr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtRevalDr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Revaluated Debit Amount Difference.
		@param AmtRevalDrDiff 
		Revaluated Debit Amount Difference
	  */
	public void setAmtRevalDrDiff (BigDecimal AmtRevalDrDiff)
	{
		if (AmtRevalDrDiff == null)
			throw new IllegalArgumentException ("AmtRevalDrDiff is mandatory.");
		set_Value (COLUMNNAME_AmtRevalDrDiff, AmtRevalDrDiff);
	}

	/** Get Revaluated Debit Amount Difference.
		@return Revaluated Debit Amount Difference
	  */
	public BigDecimal getAmtRevalDrDiff () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtRevalDrDiff);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Source Balance.
		@param AmtSourceBalance 
		Source Balance Amount
	  */
	public void setAmtSourceBalance (BigDecimal AmtSourceBalance)
	{
		if (AmtSourceBalance == null)
			throw new IllegalArgumentException ("AmtSourceBalance is mandatory.");
		set_Value (COLUMNNAME_AmtSourceBalance, AmtSourceBalance);
	}

	/** Get Source Balance.
		@return Source Balance Amount
	  */
	public BigDecimal getAmtSourceBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtSourceBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** APAR AD_Reference_ID=332 */
	public static final int APAR_AD_Reference_ID=332;
	/** Receivables & Payables = A */
	public static final String APAR_ReceivablesPayables = "A";
	/** Receivables only = R */
	public static final String APAR_ReceivablesOnly = "R";
	/** Payables only = P */
	public static final String APAR_PayablesOnly = "P";
	/** Set AP - AR.
		@param APAR 
		Include Receivables and/or Payables transactions
	  */
	public void setAPAR (String APAR)
	{

		if (APAR == null || APAR.equals("A") || APAR.equals("R") || APAR.equals("P")); else throw new IllegalArgumentException ("APAR Invalid value - " + APAR + " - Reference_ID=332 - A - R - P");		set_Value (COLUMNNAME_APAR, APAR);
	}

	/** Get AP - AR.
		@return Include Receivables and/or Payables transactions
	  */
	public String getAPAR () 
	{
		return (String)get_Value(COLUMNNAME_APAR);
	}

	/** Set Revaluation Conversion Type.
		@param C_ConversionTypeReval_ID 
		Revaluation Currency Conversion Type
	  */
	public void setC_ConversionTypeReval_ID (int C_ConversionTypeReval_ID)
	{
		if (C_ConversionTypeReval_ID < 1)
			 throw new IllegalArgumentException ("C_ConversionTypeReval_ID is mandatory.");
		set_Value (COLUMNNAME_C_ConversionTypeReval_ID, Integer.valueOf(C_ConversionTypeReval_ID));
	}

	/** Get Revaluation Conversion Type.
		@return Revaluation Currency Conversion Type
	  */
	public int getC_ConversionTypeReval_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ConversionTypeReval_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Revaluation Document Type.
		@param C_DocTypeReval_ID 
		Document Type for Revaluation Journal
	  */
	public void setC_DocTypeReval_ID (int C_DocTypeReval_ID)
	{
		if (C_DocTypeReval_ID < 1) 
			set_Value (COLUMNNAME_C_DocTypeReval_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocTypeReval_ID, Integer.valueOf(C_DocTypeReval_ID));
	}

	/** Get Revaluation Document Type.
		@return Document Type for Revaluation Journal
	  */
	public int getC_DocTypeReval_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypeReval_ID);
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
			 throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
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

	/** Set Revaluation Date.
		@param DateReval 
		Date of Revaluation
	  */
	public void setDateReval (Timestamp DateReval)
	{
		if (DateReval == null)
			throw new IllegalArgumentException ("DateReval is mandatory.");
		set_Value (COLUMNNAME_DateReval, DateReval);
	}

	/** Get Revaluation Date.
		@return Date of Revaluation
	  */
	public Timestamp getDateReval () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReval);
	}

	/** Set Accounting Fact.
		@param Fact_Acct_ID Accounting Fact	  */
	public void setFact_Acct_ID (int Fact_Acct_ID)
	{
		if (Fact_Acct_ID < 1)
			 throw new IllegalArgumentException ("Fact_Acct_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_Fact_Acct_ID, Integer.valueOf(Fact_Acct_ID));
	}

	/** Get Accounting Fact.
		@return Accounting Fact	  */
	public int getFact_Acct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fact_Acct_ID);
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

	/** Set Include All Currencies.
		@param IsAllCurrencies 
		Report not just foreign currency Invoices
	  */
	public void setIsAllCurrencies (boolean IsAllCurrencies)
	{
		set_Value (COLUMNNAME_IsAllCurrencies, Boolean.valueOf(IsAllCurrencies));
	}

	/** Get Include All Currencies.
		@return Report not just foreign currency Invoices
	  */
	public boolean isAllCurrencies () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllCurrencies);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Open Amount.
		@param OpenAmt 
		Open item amount
	  */
	public void setOpenAmt (BigDecimal OpenAmt)
	{
		if (OpenAmt == null)
			throw new IllegalArgumentException ("OpenAmt is mandatory.");
		set_Value (COLUMNNAME_OpenAmt, OpenAmt);
	}

	/** Get Open Amount.
		@return Open item amount
	  */
	public BigDecimal getOpenAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OpenAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Percent.
		@param Percent 
		Percentage
	  */
	public void setPercent (BigDecimal Percent)
	{
		set_Value (COLUMNNAME_Percent, Percent);
	}

	/** Get Percent.
		@return Percentage
	  */
	public BigDecimal getPercent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Percent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}