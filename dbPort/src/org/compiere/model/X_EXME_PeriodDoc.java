/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_PeriodDoc
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PeriodDoc extends PO implements I_EXME_PeriodDoc, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PeriodDoc (Properties ctx, int EXME_PeriodDoc_ID, String trxName)
    {
      super (ctx, EXME_PeriodDoc_ID, trxName);
      /** if (EXME_PeriodDoc_ID == 0)
        {
			setC_Period_ID (0);
			setCurrentNext (0);
			setCurrentNextBudget (0);
			setEXME_PeriodDoc_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_PeriodDoc (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PeriodDoc[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Period getC_Period() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Period.Table_Name);
        I_C_Period result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Period)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Period_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1)
			 throw new IllegalArgumentException ("C_Period_ID is mandatory.");
		set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Current Next.
		@param CurrentNext 
		The next number to be used
	  */
	public void setCurrentNext (int CurrentNext)
	{
		set_Value (COLUMNNAME_CurrentNext, Integer.valueOf(CurrentNext));
	}

	/** Get Current Next.
		@return The next number to be used
	  */
	public int getCurrentNext () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CurrentNext);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Current Next (Budget).
		@param CurrentNextBudget Current Next (Budget)	  */
	public void setCurrentNextBudget (int CurrentNextBudget)
	{
		set_Value (COLUMNNAME_CurrentNextBudget, Integer.valueOf(CurrentNextBudget));
	}

	/** Get Current Next (Budget).
		@return Current Next (Budget)	  */
	public int getCurrentNextBudget () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CurrentNextBudget);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Period Documents.
		@param EXME_PeriodDoc_ID Period Documents	  */
	public void setEXME_PeriodDoc_ID (int EXME_PeriodDoc_ID)
	{
		if (EXME_PeriodDoc_ID < 1)
			 throw new IllegalArgumentException ("EXME_PeriodDoc_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PeriodDoc_ID, Integer.valueOf(EXME_PeriodDoc_ID));
	}

	/** Get Period Documents.
		@return Period Documents	  */
	public int getEXME_PeriodDoc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PeriodDoc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PolicyType AD_Reference_ID=1200673 */
	public static final int POLICYTYPE_AD_Reference_ID=1200673;
	/** None = N */
	public static final String POLICYTYPE_None = "N";
	/** Journal = J */
	public static final String POLICYTYPE_Journal = "J";
	/** Income = I */
	public static final String POLICYTYPE_Income = "I";
	/** Expense = E */
	public static final String POLICYTYPE_Expense = "E";
	/** Set Policy Type.
		@param PolicyType Policy Type	  */
	public void setPolicyType (String PolicyType)
	{

		if (PolicyType == null || PolicyType.equals("N") || PolicyType.equals("J") || PolicyType.equals("I") || PolicyType.equals("E")); else throw new IllegalArgumentException ("PolicyType Invalid value - " + PolicyType + " - Reference_ID=1200673 - N - J - I - E");		set_Value (COLUMNNAME_PolicyType, PolicyType);
	}

	/** Get Policy Type.
		@return Policy Type	  */
	public String getPolicyType () 
	{
		return (String)get_Value(COLUMNNAME_PolicyType);
	}
}