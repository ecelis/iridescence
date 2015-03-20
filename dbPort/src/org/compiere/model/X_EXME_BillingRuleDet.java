/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_BillingRuleDet
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_BillingRuleDet extends PO implements I_EXME_BillingRuleDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_BillingRuleDet (Properties ctx, int EXME_BillingRuleDet_ID, String trxName)
    {
      super (ctx, EXME_BillingRuleDet_ID, trxName);
      /** if (EXME_BillingRuleDet_ID == 0)
        {
			setComparisonValue (null);
			setEXME_BillingFilter_ID (0);
			setEXME_BillingRuleDet_ID (0);
			setEXME_BillingRule_ID (0);
			setOperation (null);
			setValorIni (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_BillingRuleDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_BillingRuleDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** ComparisonValue AD_Reference_ID=1200628 */
	public static final int COMPARISONVALUE_AD_Reference_ID=1200628;
	/** EQ = EQ */
	public static final String COMPARISONVALUE_EQ = "EQ";
	/** GT = GT */
	public static final String COMPARISONVALUE_GT = "GT";
	/** LT = LT */
	public static final String COMPARISONVALUE_LT = "LT";
	/** Set Comparison Value.
		@param ComparisonValue Comparison Value	  */
	public void setComparisonValue (String ComparisonValue)
	{
		if (ComparisonValue == null) throw new IllegalArgumentException ("ComparisonValue is mandatory");
		if (ComparisonValue.equals("EQ") || ComparisonValue.equals("GT") || ComparisonValue.equals("LT")); else throw new IllegalArgumentException ("ComparisonValue Invalid value - " + ComparisonValue + " - Reference_ID=1200628 - EQ - GT - LT");		set_Value (COLUMNNAME_ComparisonValue, ComparisonValue);
	}

	/** Get Comparison Value.
		@return Comparison Value	  */
	public String getComparisonValue () 
	{
		return (String)get_Value(COLUMNNAME_ComparisonValue);
	}

	public I_EXME_BillingFilter getEXME_BillingFilter() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_BillingFilter.Table_Name);
        I_EXME_BillingFilter result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_BillingFilter)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_BillingFilter_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Billing Rule Filter .
		@param EXME_BillingFilter_ID Billing Rule Filter 	  */
	public void setEXME_BillingFilter_ID (int EXME_BillingFilter_ID)
	{
		if (EXME_BillingFilter_ID < 1)
			 throw new IllegalArgumentException ("EXME_BillingFilter_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_BillingFilter_ID, Integer.valueOf(EXME_BillingFilter_ID));
	}

	/** Get Billing Rule Filter .
		@return Billing Rule Filter 	  */
	public int getEXME_BillingFilter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BillingFilter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Billing Rule Detail.
		@param EXME_BillingRuleDet_ID Billing Rule Detail	  */
	public void setEXME_BillingRuleDet_ID (int EXME_BillingRuleDet_ID)
	{
		if (EXME_BillingRuleDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_BillingRuleDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_BillingRuleDet_ID, Integer.valueOf(EXME_BillingRuleDet_ID));
	}

	/** Get Billing Rule Detail.
		@return Billing Rule Detail	  */
	public int getEXME_BillingRuleDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BillingRuleDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_BillingRule getEXME_BillingRule() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_BillingRule.Table_Name);
        I_EXME_BillingRule result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_BillingRule)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_BillingRule_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Billing Rule.
		@param EXME_BillingRule_ID 
		Billing Rule
	  */
	public void setEXME_BillingRule_ID (int EXME_BillingRule_ID)
	{
		if (EXME_BillingRule_ID < 1)
			 throw new IllegalArgumentException ("EXME_BillingRule_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_BillingRule_ID, Integer.valueOf(EXME_BillingRule_ID));
	}

	/** Get Billing Rule.
		@return Billing Rule
	  */
	public int getEXME_BillingRule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_BillingRule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** IEOperator AD_Reference_ID=1200631 */
	public static final int IEOPERATOR_AD_Reference_ID=1200631;
	/** IN = IN */
	public static final String IEOPERATOR_IN = "IN";
	/** EX = EX */
	public static final String IEOPERATOR_EX = "EX";
	/** Set I/E Operator.
		@param IEOperator 
		Include / Exclude Operator
	  */
	public void setIEOperator (String IEOperator)
	{

		if (IEOperator == null || IEOperator.equals("IN") || IEOperator.equals("EX")); else throw new IllegalArgumentException ("IEOperator Invalid value - " + IEOperator + " - Reference_ID=1200631 - IN - EX");		set_Value (COLUMNNAME_IEOperator, IEOperator);
	}

	/** Get I/E Operator.
		@return Include / Exclude Operator
	  */
	public String getIEOperator () 
	{
		return (String)get_Value(COLUMNNAME_IEOperator);
	}

	/** Set I/E Value.
		@param IEValue 
		Include / Exclude Value
	  */
	public void setIEValue (String IEValue)
	{
		set_Value (COLUMNNAME_IEValue, IEValue);
	}

	/** Get I/E Value.
		@return Include / Exclude Value
	  */
	public String getIEValue () 
	{
		return (String)get_Value(COLUMNNAME_IEValue);
	}

	/** Operation AD_Reference_ID=1200629 */
	public static final int OPERATION_AD_Reference_ID=1200629;
	/** SM = SM */
	public static final String OPERATION_SM = "SM";
	/** CT = CT */
	public static final String OPERATION_CT = "CT";
	/** NA = NA */
	public static final String OPERATION_NA = "NA";
	/** Set Operation.
		@param Operation 
		Compare Operation
	  */
	public void setOperation (String Operation)
	{
		if (Operation == null) throw new IllegalArgumentException ("Operation is mandatory");
		if (Operation.equals("SM") || Operation.equals("CT") || Operation.equals("NA")); else throw new IllegalArgumentException ("Operation Invalid value - " + Operation + " - Reference_ID=1200629 - SM - CT - NA");		set_Value (COLUMNNAME_Operation, Operation);
	}

	/** Get Operation.
		@return Compare Operation
	  */
	public String getOperation () 
	{
		return (String)get_Value(COLUMNNAME_Operation);
	}

	/** ParameterValue AD_Reference_ID=1200630 */
	public static final int PARAMETERVALUE_AD_Reference_ID=1200630;
	/** VA = VA */
	public static final String PARAMETERVALUE_VA = "VA";
	/** LS = LS */
	public static final String PARAMETERVALUE_LS = "LS";
	/** Set Parameter Value.
		@param ParameterValue Parameter Value	  */
	public void setParameterValue (String ParameterValue)
	{

		if (ParameterValue == null || ParameterValue.equals("VA") || ParameterValue.equals("LS")); else throw new IllegalArgumentException ("ParameterValue Invalid value - " + ParameterValue + " - Reference_ID=1200630 - VA - LS");		set_Value (COLUMNNAME_ParameterValue, ParameterValue);
	}

	/** Get Parameter Value.
		@return Parameter Value	  */
	public String getParameterValue () 
	{
		return (String)get_Value(COLUMNNAME_ParameterValue);
	}

	/** Set Final Value.
		@param ValorFin Final Value	  */
	public void setValorFin (String ValorFin)
	{
		set_Value (COLUMNNAME_ValorFin, ValorFin);
	}

	/** Get Final Value.
		@return Final Value	  */
	public String getValorFin () 
	{
		return (String)get_Value(COLUMNNAME_ValorFin);
	}

	/** Set Initial Value.
		@param ValorIni Initial Value	  */
	public void setValorIni (String ValorIni)
	{
		if (ValorIni == null)
			throw new IllegalArgumentException ("ValorIni is mandatory.");
		set_Value (COLUMNNAME_ValorIni, ValorIni);
	}

	/** Get Initial Value.
		@return Initial Value	  */
	public String getValorIni () 
	{
		return (String)get_Value(COLUMNNAME_ValorIni);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
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