/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_AcctSchema_GL
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_C_AcctSchema_GL extends PO implements I_C_AcctSchema_GL, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_AcctSchema_GL (Properties ctx, int C_AcctSchema_GL_ID, String trxName)
    {
      super (ctx, C_AcctSchema_GL_ID, trxName);
      /** if (C_AcctSchema_GL_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setCommitmentOffset_Acct (0);
			setCommitmentOffsetSales_Acct (0);
			setIncomeSummary_Acct (0);
			setIntercompanyDueFrom_Acct (0);
			setIntercompanyDueTo_Acct (0);
			setPPVOffset_Acct (0);
			setRetainedEarning_Acct (0);
			setUseCurrencyBalancing (false);
			setUseSuspenseBalancing (false);
			setUseSuspenseError (false);
        } */
    }

    /** Load Constructor */
    public X_C_AcctSchema_GL (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_AcctSchema_GL[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set ADEFAS.
		@param Adefas_Acct ADEFAS	  */
	public void setAdefas_Acct (int Adefas_Acct)
	{
		set_Value (COLUMNNAME_Adefas_Acct, Integer.valueOf(Adefas_Acct));
	}

	/** Get ADEFAS.
		@return ADEFAS	  */
	public int getAdefas_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Adefas_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_AcctSchema.Table_Name);
        I_C_AcctSchema result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_AcctSchema)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_AcctSchema_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1)
			 throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_AcctSchema_ID()));
    }

	/** Set Commitment Offset.
		@param CommitmentOffset_Acct 
		Budgetary Commitment Offset Account
	  */
	public void setCommitmentOffset_Acct (int CommitmentOffset_Acct)
	{
		set_Value (COLUMNNAME_CommitmentOffset_Acct, Integer.valueOf(CommitmentOffset_Acct));
	}

	/** Get Commitment Offset.
		@return Budgetary Commitment Offset Account
	  */
	public int getCommitmentOffset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CommitmentOffset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Commitment Offset Sales.
		@param CommitmentOffsetSales_Acct 
		Budgetary Commitment Offset Account for Sales
	  */
	public void setCommitmentOffsetSales_Acct (int CommitmentOffsetSales_Acct)
	{
		set_Value (COLUMNNAME_CommitmentOffsetSales_Acct, Integer.valueOf(CommitmentOffsetSales_Acct));
	}

	/** Get Commitment Offset Sales.
		@return Budgetary Commitment Offset Account for Sales
	  */
	public int getCommitmentOffsetSales_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CommitmentOffsetSales_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Currency Balancing Acct.
		@param CurrencyBalancing_Acct 
		Account used when a currency is out of balance
	  */
	public void setCurrencyBalancing_Acct (int CurrencyBalancing_Acct)
	{
		set_Value (COLUMNNAME_CurrencyBalancing_Acct, Integer.valueOf(CurrencyBalancing_Acct));
	}

	/** Get Currency Balancing Acct.
		@return Account used when a currency is out of balance
	  */
	public int getCurrencyBalancing_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CurrencyBalancing_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Income Summary Acct.
		@param IncomeSummary_Acct 
		Income Summary Account 
	  */
	public void setIncomeSummary_Acct (int IncomeSummary_Acct)
	{
		set_Value (COLUMNNAME_IncomeSummary_Acct, Integer.valueOf(IncomeSummary_Acct));
	}

	/** Get Income Summary Acct.
		@return Income Summary Account 
	  */
	public int getIncomeSummary_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IncomeSummary_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Intercompany Due From Acct.
		@param IntercompanyDueFrom_Acct 
		Intercompany Due From / Receivables Account
	  */
	public void setIntercompanyDueFrom_Acct (int IntercompanyDueFrom_Acct)
	{
		set_Value (COLUMNNAME_IntercompanyDueFrom_Acct, Integer.valueOf(IntercompanyDueFrom_Acct));
	}

	/** Get Intercompany Due From Acct.
		@return Intercompany Due From / Receivables Account
	  */
	public int getIntercompanyDueFrom_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IntercompanyDueFrom_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Intercompany Due To Acct.
		@param IntercompanyDueTo_Acct 
		Intercompany Due To / Payable Account
	  */
	public void setIntercompanyDueTo_Acct (int IntercompanyDueTo_Acct)
	{
		set_Value (COLUMNNAME_IntercompanyDueTo_Acct, Integer.valueOf(IntercompanyDueTo_Acct));
	}

	/** Get Intercompany Due To Acct.
		@return Intercompany Due To / Payable Account
	  */
	public int getIntercompanyDueTo_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IntercompanyDueTo_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Accrued Revenue Act.
		@param LeyIngDeve_Acct Accrued Revenue Act	  */
	public void setLeyIngDeve_Acct (int LeyIngDeve_Acct)
	{
		set_Value (COLUMNNAME_LeyIngDeve_Acct, Integer.valueOf(LeyIngDeve_Acct));
	}

	/** Get Accrued Revenue Act.
		@return Accrued Revenue Act	  */
	public int getLeyIngDeve_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeyIngDeve_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Estimated Revenue Act.
		@param LeyIngEsti_Acct Estimated Revenue Act	  */
	public void setLeyIngEsti_Acct (int LeyIngEsti_Acct)
	{
		set_Value (COLUMNNAME_LeyIngEsti_Acct, Integer.valueOf(LeyIngEsti_Acct));
	}

	/** Get Estimated Revenue Act.
		@return Estimated Revenue Act	  */
	public int getLeyIngEsti_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeyIngEsti_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Collected Revenue Act.
		@param LeyIngReca_Acct Collected Revenue Act	  */
	public void setLeyIngReca_Acct (int LeyIngReca_Acct)
	{
		set_Value (COLUMNNAME_LeyIngReca_Acct, Integer.valueOf(LeyIngReca_Acct));
	}

	/** Get Collected Revenue Act.
		@return Collected Revenue Act	  */
	public int getLeyIngReca_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeyIngReca_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Run Income Act.
		@param LeyIngxEjec_Acct Run Income Act	  */
	public void setLeyIngxEjec_Acct (int LeyIngxEjec_Acct)
	{
		set_Value (COLUMNNAME_LeyIngxEjec_Acct, Integer.valueOf(LeyIngxEjec_Acct));
	}

	/** Get Run Income Act.
		@return Run Income Act	  */
	public int getLeyIngxEjec_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LeyIngxEjec_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Modifications to Approved Expenditures Budget.
		@param ModPresEgrAprob_Acct Modifications to Approved Expenditures Budget	  */
	public void setModPresEgrAprob_Acct (int ModPresEgrAprob_Acct)
	{
		set_Value (COLUMNNAME_ModPresEgrAprob_Acct, Integer.valueOf(ModPresEgrAprob_Acct));
	}

	/** Get Modifications to Approved Expenditures Budget.
		@return Modifications to Approved Expenditures Budget	  */
	public int getModPresEgrAprob_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ModPresEgrAprob_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PPV Offset.
		@param PPVOffset_Acct 
		Purchase Price Variance Offset Account
	  */
	public void setPPVOffset_Acct (int PPVOffset_Acct)
	{
		set_Value (COLUMNNAME_PPVOffset_Acct, Integer.valueOf(PPVOffset_Acct));
	}

	/** Get PPV Offset.
		@return Purchase Price Variance Offset Account
	  */
	public int getPPVOffset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PPVOffset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approved Expenditure Budget.
		@param PresEgrAprob_Acct Approved Expenditure Budget	  */
	public void setPresEgrAprob_Acct (int PresEgrAprob_Acct)
	{
		set_Value (COLUMNNAME_PresEgrAprob_Acct, Integer.valueOf(PresEgrAprob_Acct));
	}

	/** Get Approved Expenditure Budget.
		@return Approved Expenditure Budget	  */
	public int getPresEgrAprob_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PresEgrAprob_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Committed Expenditure Budget.
		@param PresEgrComp_Acct Committed Expenditure Budget	  */
	public void setPresEgrComp_Acct (int PresEgrComp_Acct)
	{
		set_Value (COLUMNNAME_PresEgrComp_Acct, Integer.valueOf(PresEgrComp_Acct));
	}

	/** Get Committed Expenditure Budget.
		@return Committed Expenditure Budget	  */
	public int getPresEgrComp_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PresEgrComp_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Accrued Expenditure Budget.
		@param PresEgrDeve_Acct Accrued Expenditure Budget	  */
	public void setPresEgrDeve_Acct (int PresEgrDeve_Acct)
	{
		set_Value (COLUMNNAME_PresEgrDeve_Acct, Integer.valueOf(PresEgrDeve_Acct));
	}

	/** Get Accrued Expenditure Budget.
		@return Accrued Expenditure Budget	  */
	public int getPresEgrDeve_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PresEgrDeve_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exercised Expenditure Budget.
		@param PresEgrEjer_Acct Exercised Expenditure Budget	  */
	public void setPresEgrEjer_Acct (int PresEgrEjer_Acct)
	{
		set_Value (COLUMNNAME_PresEgrEjer_Acct, Integer.valueOf(PresEgrEjer_Acct));
	}

	/** Get Exercised Expenditure Budget.
		@return Exercised Expenditure Budget	  */
	public int getPresEgrEjer_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PresEgrEjer_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Paid Expenditure Budget.
		@param PresEgrPaga_Acct Paid Expenditure Budget	  */
	public void setPresEgrPaga_Acct (int PresEgrPaga_Acct)
	{
		set_Value (COLUMNNAME_PresEgrPaga_Acct, Integer.valueOf(PresEgrPaga_Acct));
	}

	/** Get Paid Expenditure Budget.
		@return Paid Expenditure Budget	  */
	public int getPresEgrPaga_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PresEgrPaga_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Expenditure Budget by Exercise.
		@param PresEgrxEjer_Acct Expenditure Budget by Exercise	  */
	public void setPresEgrxEjer_Acct (int PresEgrxEjer_Acct)
	{
		set_Value (COLUMNNAME_PresEgrxEjer_Acct, Integer.valueOf(PresEgrxEjer_Acct));
	}

	/** Get Expenditure Budget by Exercise.
		@return Expenditure Budget by Exercise	  */
	public int getPresEgrxEjer_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PresEgrxEjer_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Retained Earning Acct.
		@param RetainedEarning_Acct Retained Earning Acct	  */
	public void setRetainedEarning_Acct (int RetainedEarning_Acct)
	{
		set_Value (COLUMNNAME_RetainedEarning_Acct, Integer.valueOf(RetainedEarning_Acct));
	}

	/** Get Retained Earning Acct.
		@return Retained Earning Acct	  */
	public int getRetainedEarning_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RetainedEarning_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Financial Surplus.
		@param SuperFina_Acct Financial Surplus	  */
	public void setSuperFina_Acct (int SuperFina_Acct)
	{
		set_Value (COLUMNNAME_SuperFina_Acct, Integer.valueOf(SuperFina_Acct));
	}

	/** Get Financial Surplus.
		@return Financial Surplus	  */
	public int getSuperFina_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SuperFina_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Suspense Balancing Acct.
		@param SuspenseBalancing_Acct Suspense Balancing Acct	  */
	public void setSuspenseBalancing_Acct (int SuspenseBalancing_Acct)
	{
		set_Value (COLUMNNAME_SuspenseBalancing_Acct, Integer.valueOf(SuspenseBalancing_Acct));
	}

	/** Get Suspense Balancing Acct.
		@return Suspense Balancing Acct	  */
	public int getSuspenseBalancing_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SuspenseBalancing_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Suspense Error Acct.
		@param SuspenseError_Acct Suspense Error Acct	  */
	public void setSuspenseError_Acct (int SuspenseError_Acct)
	{
		set_Value (COLUMNNAME_SuspenseError_Acct, Integer.valueOf(SuspenseError_Acct));
	}

	/** Get Suspense Error Acct.
		@return Suspense Error Acct	  */
	public int getSuspenseError_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SuspenseError_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Use Currency Balancing.
		@param UseCurrencyBalancing Use Currency Balancing	  */
	public void setUseCurrencyBalancing (boolean UseCurrencyBalancing)
	{
		set_Value (COLUMNNAME_UseCurrencyBalancing, Boolean.valueOf(UseCurrencyBalancing));
	}

	/** Get Use Currency Balancing.
		@return Use Currency Balancing	  */
	public boolean isUseCurrencyBalancing () 
	{
		Object oo = get_Value(COLUMNNAME_UseCurrencyBalancing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Use Suspense Balancing.
		@param UseSuspenseBalancing Use Suspense Balancing	  */
	public void setUseSuspenseBalancing (boolean UseSuspenseBalancing)
	{
		set_Value (COLUMNNAME_UseSuspenseBalancing, Boolean.valueOf(UseSuspenseBalancing));
	}

	/** Get Use Suspense Balancing.
		@return Use Suspense Balancing	  */
	public boolean isUseSuspenseBalancing () 
	{
		Object oo = get_Value(COLUMNNAME_UseSuspenseBalancing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Use Suspense Error.
		@param UseSuspenseError Use Suspense Error	  */
	public void setUseSuspenseError (boolean UseSuspenseError)
	{
		set_Value (COLUMNNAME_UseSuspenseError, Boolean.valueOf(UseSuspenseError));
	}

	/** Get Use Suspense Error.
		@return Use Suspense Error	  */
	public boolean isUseSuspenseError () 
	{
		Object oo = get_Value(COLUMNNAME_UseSuspenseError);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}