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

/** Generated Model for I_Asset
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_I_Asset extends PO implements I_I_Asset, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_I_Asset (Properties ctx, int I_Asset_ID, String trxName)
    {
      super (ctx, I_Asset_ID, trxName);
      /** if (I_Asset_ID == 0)
        {
			setI_Asset_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_Asset (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_Asset[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Accumulated Depreciation.
		@param A_Accumdepreciation_Acct Accumulated Depreciation	  */
	public void setA_Accumdepreciation_Acct (int A_Accumdepreciation_Acct)
	{
		set_Value (COLUMNNAME_A_Accumdepreciation_Acct, Integer.valueOf(A_Accumdepreciation_Acct));
	}

	/** Get Accumulated Depreciation.
		@return Accumulated Depreciation	  */
	public int getA_Accumdepreciation_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Accumdepreciation_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Accumulated_Depr.
		@param A_Accumulated_Depr A_Accumulated_Depr	  */
	public void setA_Accumulated_Depr (BigDecimal A_Accumulated_Depr)
	{
		set_Value (COLUMNNAME_A_Accumulated_Depr, A_Accumulated_Depr);
	}

	/** Get A_Accumulated_Depr.
		@return A_Accumulated_Depr	  */
	public BigDecimal getA_Accumulated_Depr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Accumulated_Depr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asset Cost Account.
		@param A_Asset_Acct Asset Cost Account	  */
	public void setA_Asset_Acct (int A_Asset_Acct)
	{
		set_Value (COLUMNNAME_A_Asset_Acct, Integer.valueOf(A_Asset_Acct));
	}

	/** Get Asset Cost Account.
		@return Asset Cost Account	  */
	public int getA_Asset_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asset Cost.
		@param A_Asset_Cost Asset Cost	  */
	public void setA_Asset_Cost (BigDecimal A_Asset_Cost)
	{
		set_Value (COLUMNNAME_A_Asset_Cost, A_Asset_Cost);
	}

	/** Get Asset Cost.
		@return Asset Cost	  */
	public BigDecimal getA_Asset_Cost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Asset_Cost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_A_Asset_Group getA_Asset_Group() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_A_Asset_Group.Table_Name);
        I_A_Asset_Group result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_A_Asset_Group)constructor.newInstance(new Object[] {getCtx(), new Integer(getA_Asset_Group_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Asset Group.
		@param A_Asset_Group_ID 
		Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID)
	{
		if (A_Asset_Group_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_Group_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_Group_ID, Integer.valueOf(A_Asset_Group_ID));
	}

	/** Get Asset Group.
		@return Group of Assets
	  */
	public int getA_Asset_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asset.
		@param A_Asset_ID 
		Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_ID, null);
		else 
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

	/** Set Asset Life Current Year.
		@param A_Asset_Life_Current_Year Asset Life Current Year	  */
	public void setA_Asset_Life_Current_Year (int A_Asset_Life_Current_Year)
	{
		set_Value (COLUMNNAME_A_Asset_Life_Current_Year, Integer.valueOf(A_Asset_Life_Current_Year));
	}

	/** Get Asset Life Current Year.
		@return Asset Life Current Year	  */
	public int getA_Asset_Life_Current_Year () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Life_Current_Year);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asset life Years.
		@param A_Asset_Life_Years Asset life Years	  */
	public void setA_Asset_Life_Years (int A_Asset_Life_Years)
	{
		set_Value (COLUMNNAME_A_Asset_Life_Years, Integer.valueOf(A_Asset_Life_Years));
	}

	/** Get Asset life Years.
		@return Asset life Years	  */
	public int getA_Asset_Life_Years () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Life_Years);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set A_Asset_Spread_Type.
		@param A_Asset_Spread_Type A_Asset_Spread_Type	  */
	public void setA_Asset_Spread_Type (int A_Asset_Spread_Type)
	{
		set_Value (COLUMNNAME_A_Asset_Spread_Type, Integer.valueOf(A_Asset_Spread_Type));
	}

	/** Get A_Asset_Spread_Type.
		@return A_Asset_Spread_Type	  */
	public int getA_Asset_Spread_Type () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Spread_Type);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Base Amount.
		@param A_Base_Amount Base Amount	  */
	public void setA_Base_Amount (BigDecimal A_Base_Amount)
	{
		set_Value (COLUMNNAME_A_Base_Amount, A_Base_Amount);
	}

	/** Get Base Amount.
		@return Base Amount	  */
	public BigDecimal getA_Base_Amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Base_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Calculate Accumulated Depreciation.
		@param A_Calc_Accumulated_Depr Calculate Accumulated Depreciation	  */
	public void setA_Calc_Accumulated_Depr (BigDecimal A_Calc_Accumulated_Depr)
	{
		set_Value (COLUMNNAME_A_Calc_Accumulated_Depr, A_Calc_Accumulated_Depr);
	}

	/** Get Calculate Accumulated Depreciation.
		@return Calculate Accumulated Depreciation	  */
	public BigDecimal getA_Calc_Accumulated_Depr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Calc_Accumulated_Depr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Curr Dep Exp.
		@param A_Curr_Dep_Exp Curr Dep Exp	  */
	public void setA_Curr_Dep_Exp (BigDecimal A_Curr_Dep_Exp)
	{
		set_Value (COLUMNNAME_A_Curr_Dep_Exp, A_Curr_Dep_Exp);
	}

	/** Get Curr Dep Exp.
		@return Curr Dep Exp	  */
	public BigDecimal getA_Curr_Dep_Exp () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Curr_Dep_Exp);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current Period.
		@param A_Current_Period Current Period	  */
	public void setA_Current_Period (int A_Current_Period)
	{
		set_Value (COLUMNNAME_A_Current_Period, Integer.valueOf(A_Current_Period));
	}

	/** Get Current Period.
		@return Current Period	  */
	public int getA_Current_Period () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Current_Period);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Depreciation Expense Account.
		@param A_Depreciation_Acct Depreciation Expense Account	  */
	public void setA_Depreciation_Acct (int A_Depreciation_Acct)
	{
		set_Value (COLUMNNAME_A_Depreciation_Acct, Integer.valueOf(A_Depreciation_Acct));
	}

	/** Get Depreciation Expense Account.
		@return Depreciation Expense Account	  */
	public int getA_Depreciation_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Depreciation Calculation Type.
		@param A_Depreciation_Calc_Type Depreciation Calculation Type	  */
	public void setA_Depreciation_Calc_Type (int A_Depreciation_Calc_Type)
	{
		set_Value (COLUMNNAME_A_Depreciation_Calc_Type, Integer.valueOf(A_Depreciation_Calc_Type));
	}

	/** Get Depreciation Calculation Type.
		@return Depreciation Calculation Type	  */
	public int getA_Depreciation_Calc_Type () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Calc_Type);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Depreciation Manual Amount.
		@param A_Depreciation_Manual_Amount Depreciation Manual Amount	  */
	public void setA_Depreciation_Manual_Amount (BigDecimal A_Depreciation_Manual_Amount)
	{
		set_Value (COLUMNNAME_A_Depreciation_Manual_Amount, A_Depreciation_Manual_Amount);
	}

	/** Get Depreciation Manual Amount.
		@return Depreciation Manual Amount	  */
	public BigDecimal getA_Depreciation_Manual_Amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Depreciation_Manual_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** A_Depreciation_Manual_Period AD_Reference_ID=1200303 */
	public static final int A_DEPRECIATION_MANUAL_PERIOD_AD_Reference_ID=1200303;
	/** Period = PR */
	public static final String A_DEPRECIATION_MANUAL_PERIOD_Period = "PR";
	/** Yearly = YR */
	public static final String A_DEPRECIATION_MANUAL_PERIOD_Yearly = "YR";
	/** Set Depreciation Manual Period.
		@param A_Depreciation_Manual_Period Depreciation Manual Period	  */
	public void setA_Depreciation_Manual_Period (String A_Depreciation_Manual_Period)
	{

		if (A_Depreciation_Manual_Period == null || A_Depreciation_Manual_Period.equals("PR") || A_Depreciation_Manual_Period.equals("YR")); else throw new IllegalArgumentException ("A_Depreciation_Manual_Period Invalid value - " + A_Depreciation_Manual_Period + " - Reference_ID=1200303 - PR - YR");		set_Value (COLUMNNAME_A_Depreciation_Manual_Period, A_Depreciation_Manual_Period);
	}

	/** Get Depreciation Manual Period.
		@return Depreciation Manual Period	  */
	public String getA_Depreciation_Manual_Period () 
	{
		return (String)get_Value(COLUMNNAME_A_Depreciation_Manual_Period);
	}

	/** Set A_Depreciation_Table_Header_ID.
		@param A_Depreciation_Table_Header_ID A_Depreciation_Table_Header_ID	  */
	public void setA_Depreciation_Table_Header_ID (int A_Depreciation_Table_Header_ID)
	{
		if (A_Depreciation_Table_Header_ID < 1) 
			set_Value (COLUMNNAME_A_Depreciation_Table_Header_ID, null);
		else 
			set_Value (COLUMNNAME_A_Depreciation_Table_Header_ID, Integer.valueOf(A_Depreciation_Table_Header_ID));
	}

	/** Get A_Depreciation_Table_Header_ID.
		@return A_Depreciation_Table_Header_ID	  */
	public int getA_Depreciation_Table_Header_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Table_Header_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Depreciation Variable Perc.
		@param A_Depreciation_Variable_Perc Depreciation Variable Perc	  */
	public void setA_Depreciation_Variable_Perc (BigDecimal A_Depreciation_Variable_Perc)
	{
		set_Value (COLUMNNAME_A_Depreciation_Variable_Perc, A_Depreciation_Variable_Perc);
	}

	/** Get Depreciation Variable Perc.
		@return Depreciation Variable Perc	  */
	public BigDecimal getA_Depreciation_Variable_Perc () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Depreciation_Variable_Perc);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Loss on Disposal.
		@param A_Disposal_Loss Loss on Disposal	  */
	public void setA_Disposal_Loss (int A_Disposal_Loss)
	{
		set_Value (COLUMNNAME_A_Disposal_Loss, Integer.valueOf(A_Disposal_Loss));
	}

	/** Get Loss on Disposal.
		@return Loss on Disposal	  */
	public int getA_Disposal_Loss () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Loss);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Disposal Revenue.
		@param A_Disposal_Revenue Disposal Revenue	  */
	public void setA_Disposal_Revenue (int A_Disposal_Revenue)
	{
		set_Value (COLUMNNAME_A_Disposal_Revenue, Integer.valueOf(A_Disposal_Revenue));
	}

	/** Get Disposal Revenue.
		@return Disposal Revenue	  */
	public int getA_Disposal_Revenue () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Disposal_Revenue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life Period.
		@param A_Life_Period Life Period	  */
	public void setA_Life_Period (int A_Life_Period)
	{
		set_Value (COLUMNNAME_A_Life_Period, Integer.valueOf(A_Life_Period));
	}

	/** Get Life Period.
		@return Life Period	  */
	public int getA_Life_Period () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Life_Period);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asset ID.
		@param A_Parent_Asset_ID Asset ID	  */
	public void setA_Parent_Asset_ID (int A_Parent_Asset_ID)
	{
		if (A_Parent_Asset_ID < 1) 
			set_Value (COLUMNNAME_A_Parent_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Parent_Asset_ID, Integer.valueOf(A_Parent_Asset_ID));
	}

	/** Get Asset ID.
		@return Asset ID	  */
	public int getA_Parent_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Parent_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Period End.
		@param A_Period_End Period End	  */
	public void setA_Period_End (int A_Period_End)
	{
		set_Value (COLUMNNAME_A_Period_End, Integer.valueOf(A_Period_End));
	}

	/** Get Period End.
		@return Period End	  */
	public int getA_Period_End () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Period_End);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Period Posted.
		@param A_Period_Posted Period Posted	  */
	public void setA_Period_Posted (int A_Period_Posted)
	{
		set_Value (COLUMNNAME_A_Period_Posted, Integer.valueOf(A_Period_Posted));
	}

	/** Get Period Posted.
		@return Period Posted	  */
	public int getA_Period_Posted () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Period_Posted);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Period Start.
		@param A_Period_Start Period Start	  */
	public void setA_Period_Start (int A_Period_Start)
	{
		set_Value (COLUMNNAME_A_Period_Start, Integer.valueOf(A_Period_Start));
	}

	/** Get Period Start.
		@return Period Start	  */
	public int getA_Period_Start () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Period_Start);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Prior Year Accumulated Depr.
		@param A_Prior_Year_Accumulated_Depr Prior Year Accumulated Depr	  */
	public void setA_Prior_Year_Accumulated_Depr (BigDecimal A_Prior_Year_Accumulated_Depr)
	{
		set_Value (COLUMNNAME_A_Prior_Year_Accumulated_Depr, A_Prior_Year_Accumulated_Depr);
	}

	/** Get Prior Year Accumulated Depr.
		@return Prior Year Accumulated Depr	  */
	public BigDecimal getA_Prior_Year_Accumulated_Depr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Prior_Year_Accumulated_Depr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity.
		@param A_QTY_Current Quantity	  */
	public void setA_QTY_Current (int A_QTY_Current)
	{
		set_Value (COLUMNNAME_A_QTY_Current, Integer.valueOf(A_QTY_Current));
	}

	/** Get Quantity.
		@return Quantity	  */
	public int getA_QTY_Current () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_QTY_Current);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Original Quantity.
		@param A_QTY_Original Original Quantity	  */
	public void setA_QTY_Original (int A_QTY_Original)
	{
		set_Value (COLUMNNAME_A_QTY_Original, Integer.valueOf(A_QTY_Original));
	}

	/** Get Original Quantity.
		@return Original Quantity	  */
	public int getA_QTY_Original () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_QTY_Original);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Revaluation Accumulated Depreciation Offset for Current Year.
		@param A_Reval_Accumdep_Offset_Cur Revaluation Accumulated Depreciation Offset for Current Year	  */
	public void setA_Reval_Accumdep_Offset_Cur (int A_Reval_Accumdep_Offset_Cur)
	{
		set_Value (COLUMNNAME_A_Reval_Accumdep_Offset_Cur, Integer.valueOf(A_Reval_Accumdep_Offset_Cur));
	}

	/** Get Revaluation Accumulated Depreciation Offset for Current Year.
		@return Revaluation Accumulated Depreciation Offset for Current Year	  */
	public int getA_Reval_Accumdep_Offset_Cur () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Accumdep_Offset_Cur);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Revaluation Accumulated Depreciation Offset for Prior Year.
		@param A_Reval_Accumdep_Offset_Prior Revaluation Accumulated Depreciation Offset for Prior Year	  */
	public void setA_Reval_Accumdep_Offset_Prior (int A_Reval_Accumdep_Offset_Prior)
	{
		set_Value (COLUMNNAME_A_Reval_Accumdep_Offset_Prior, Integer.valueOf(A_Reval_Accumdep_Offset_Prior));
	}

	/** Get Revaluation Accumulated Depreciation Offset for Prior Year.
		@return Revaluation Accumulated Depreciation Offset for Prior Year	  */
	public int getA_Reval_Accumdep_Offset_Prior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Accumdep_Offset_Prior);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** A_Reval_Cal_Method AD_Reference_ID=1200306 */
	public static final int A_REVAL_CAL_METHOD_AD_Reference_ID=1200306;
	/** Default = DFT */
	public static final String A_REVAL_CAL_METHOD_Default = "DFT";
	/** Inception to date = IDF */
	public static final String A_REVAL_CAL_METHOD_InceptionToDate = "IDF";
	/** Year Balances = YBF */
	public static final String A_REVAL_CAL_METHOD_YearBalances = "YBF";
	/** Set Revaluation Calculation Method.
		@param A_Reval_Cal_Method Revaluation Calculation Method	  */
	public void setA_Reval_Cal_Method (String A_Reval_Cal_Method)
	{

		if (A_Reval_Cal_Method == null || A_Reval_Cal_Method.equals("DFT") || A_Reval_Cal_Method.equals("IDF") || A_Reval_Cal_Method.equals("YBF")); else throw new IllegalArgumentException ("A_Reval_Cal_Method Invalid value - " + A_Reval_Cal_Method + " - Reference_ID=1200306 - DFT - IDF - YBF");		set_Value (COLUMNNAME_A_Reval_Cal_Method, A_Reval_Cal_Method);
	}

	/** Get Revaluation Calculation Method.
		@return Revaluation Calculation Method	  */
	public String getA_Reval_Cal_Method () 
	{
		return (String)get_Value(COLUMNNAME_A_Reval_Cal_Method);
	}

	/** Set Revaluation Cost Offset for Current Year.
		@param A_Reval_Cost_Offset Revaluation Cost Offset for Current Year	  */
	public void setA_Reval_Cost_Offset (int A_Reval_Cost_Offset)
	{
		set_Value (COLUMNNAME_A_Reval_Cost_Offset, Integer.valueOf(A_Reval_Cost_Offset));
	}

	/** Get Revaluation Cost Offset for Current Year.
		@return Revaluation Cost Offset for Current Year	  */
	public int getA_Reval_Cost_Offset () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Cost_Offset);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Revaluation Cost Offset for Prior Year.
		@param A_Reval_Cost_Offset_Prior Revaluation Cost Offset for Prior Year	  */
	public void setA_Reval_Cost_Offset_Prior (int A_Reval_Cost_Offset_Prior)
	{
		set_Value (COLUMNNAME_A_Reval_Cost_Offset_Prior, Integer.valueOf(A_Reval_Cost_Offset_Prior));
	}

	/** Get Revaluation Cost Offset for Prior Year.
		@return Revaluation Cost Offset for Prior Year	  */
	public int getA_Reval_Cost_Offset_Prior () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Cost_Offset_Prior);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Revaluation Expense Offs.
		@param A_Reval_Depexp_Offset Revaluation Expense Offs	  */
	public void setA_Reval_Depexp_Offset (int A_Reval_Depexp_Offset)
	{
		set_Value (COLUMNNAME_A_Reval_Depexp_Offset, Integer.valueOf(A_Reval_Depexp_Offset));
	}

	/** Get Revaluation Expense Offs.
		@return Revaluation Expense Offs	  */
	public int getA_Reval_Depexp_Offset () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Reval_Depexp_Offset);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Salvage Value.
		@param A_Salvage_Value Salvage Value	  */
	public void setA_Salvage_Value (BigDecimal A_Salvage_Value)
	{
		set_Value (COLUMNNAME_A_Salvage_Value, A_Salvage_Value);
	}

	/** Get Salvage Value.
		@return Salvage Value	  */
	public BigDecimal getA_Salvage_Value () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Salvage_Value);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Split Percentage.
		@param A_Split_Percent Split Percentage	  */
	public void setA_Split_Percent (BigDecimal A_Split_Percent)
	{
		set_Value (COLUMNNAME_A_Split_Percent, A_Split_Percent);
	}

	/** Get Split Percentage.
		@return Split Percentage	  */
	public BigDecimal getA_Split_Percent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Split_Percent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asset Depreciation Date.
		@param AssetDepreciationDate 
		Date of last depreciation
	  */
	public void setAssetDepreciationDate (Timestamp AssetDepreciationDate)
	{
		set_Value (COLUMNNAME_AssetDepreciationDate, AssetDepreciationDate);
	}

	/** Get Asset Depreciation Date.
		@return Date of last depreciation
	  */
	public Timestamp getAssetDepreciationDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssetDepreciationDate);
	}

	/** Set Asset Disposal Date.
		@param AssetDisposalDate 
		Date when the asset is/was disposed
	  */
	public void setAssetDisposalDate (Timestamp AssetDisposalDate)
	{
		set_Value (COLUMNNAME_AssetDisposalDate, AssetDisposalDate);
	}

	/** Get Asset Disposal Date.
		@return Date when the asset is/was disposed
	  */
	public Timestamp getAssetDisposalDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssetDisposalDate);
	}

	/** Set Market value Amount.
		@param AssetMarketValueAmt 
		Market value of the asset
	  */
	public void setAssetMarketValueAmt (BigDecimal AssetMarketValueAmt)
	{
		set_Value (COLUMNNAME_AssetMarketValueAmt, AssetMarketValueAmt);
	}

	/** Get Market value Amount.
		@return Market value of the asset
	  */
	public BigDecimal getAssetMarketValueAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AssetMarketValueAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set In Service Date.
		@param AssetServiceDate 
		Date when Asset was put into service
	  */
	public void setAssetServiceDate (Timestamp AssetServiceDate)
	{
		set_Value (COLUMNNAME_AssetServiceDate, AssetServiceDate);
	}

	/** Get In Service Date.
		@return Date when Asset was put into service
	  */
	public Timestamp getAssetServiceDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssetServiceDate);
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
			set_Value (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_Value (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
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

	public I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner_Location.Table_Name);
        I_C_BPartner_Location result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner_Location)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_Location_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Business Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Business Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Location getC_Location() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Location.Table_Name);
        I_C_Location result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Location)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Location_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ConventionType.
		@param ConventionType ConventionType	  */
	public void setConventionType (int ConventionType)
	{
		set_Value (COLUMNNAME_ConventionType, Integer.valueOf(ConventionType));
	}

	/** Get ConventionType.
		@return ConventionType	  */
	public int getConventionType () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ConventionType);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Depreciation Type.
		@param DepreciationType Depreciation Type	  */
	public void setDepreciationType (int DepreciationType)
	{
		set_Value (COLUMNNAME_DepreciationType, Integer.valueOf(DepreciationType));
	}

	/** Get Depreciation Type.
		@return Depreciation Type	  */
	public int getDepreciationType () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DepreciationType);
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

	/** Set Guarantee Date.
		@param GuaranteeDate 
		Date when guarantee expires
	  */
	public void setGuaranteeDate (Timestamp GuaranteeDate)
	{
		set_Value (COLUMNNAME_GuaranteeDate, GuaranteeDate);
	}

	/** Get Guarantee Date.
		@return Date when guarantee expires
	  */
	public Timestamp getGuaranteeDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GuaranteeDate);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set I_Asset_ID.
		@param I_Asset_ID I_Asset_ID	  */
	public void setI_Asset_ID (int I_Asset_ID)
	{
		if (I_Asset_ID < 1)
			 throw new IllegalArgumentException ("I_Asset_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_I_Asset_ID, Integer.valueOf(I_Asset_ID));
	}

	/** Get I_Asset_ID.
		@return I_Asset_ID	  */
	public int getI_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getI_Asset_ID()));
    }

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Depreciate.
		@param IsDepreciated 
		The asset will be depreciated
	  */
	public void setIsDepreciated (boolean IsDepreciated)
	{
		set_Value (COLUMNNAME_IsDepreciated, Boolean.valueOf(IsDepreciated));
	}

	/** Get Depreciate.
		@return The asset will be depreciated
	  */
	public boolean isDepreciated () 
	{
		Object oo = get_Value(COLUMNNAME_IsDepreciated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Disposed.
		@param IsDisposed 
		The asset is disposed
	  */
	public void setIsDisposed (boolean IsDisposed)
	{
		set_Value (COLUMNNAME_IsDisposed, Boolean.valueOf(IsDisposed));
	}

	/** Get Disposed.
		@return The asset is disposed
	  */
	public boolean isDisposed () 
	{
		Object oo = get_Value(COLUMNNAME_IsDisposed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Fully depreciated.
		@param IsFullyDepreciated 
		The asset is fully depreciated
	  */
	public void setIsFullyDepreciated (boolean IsFullyDepreciated)
	{
		set_Value (COLUMNNAME_IsFullyDepreciated, Boolean.valueOf(IsFullyDepreciated));
	}

	/** Get Fully depreciated.
		@return The asset is fully depreciated
	  */
	public boolean isFullyDepreciated () 
	{
		Object oo = get_Value(COLUMNNAME_IsFullyDepreciated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set In Possession.
		@param IsInPosession 
		The asset is in the possession of the organization
	  */
	public void setIsInPosession (boolean IsInPosession)
	{
		set_Value (COLUMNNAME_IsInPosession, Boolean.valueOf(IsInPosession));
	}

	/** Get In Possession.
		@return The asset is in the possession of the organization
	  */
	public boolean isInPosession () 
	{
		Object oo = get_Value(COLUMNNAME_IsInPosession);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Owned.
		@param IsOwned 
		The asset is owned by the organization
	  */
	public void setIsOwned (boolean IsOwned)
	{
		set_Value (COLUMNNAME_IsOwned, Boolean.valueOf(IsOwned));
	}

	/** Get Owned.
		@return The asset is owned by the organization
	  */
	public boolean isOwned () 
	{
		Object oo = get_Value(COLUMNNAME_IsOwned);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Life use.
		@param LifeUseUnits 
		Units of use until the asset is not usable anymore
	  */
	public void setLifeUseUnits (int LifeUseUnits)
	{
		set_Value (COLUMNNAME_LifeUseUnits, Integer.valueOf(LifeUseUnits));
	}

	/** Get Life use.
		@return Units of use until the asset is not usable anymore
	  */
	public int getLifeUseUnits () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LifeUseUnits);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Location comment.
		@param LocationComment 
		Additional comments or remarks concerning the location
	  */
	public void setLocationComment (String LocationComment)
	{
		set_Value (COLUMNNAME_LocationComment, LocationComment);
	}

	/** Get Location comment.
		@return Additional comments or remarks concerning the location
	  */
	public String getLocationComment () 
	{
		return (String)get_Value(COLUMNNAME_LocationComment);
	}

	/** Set Lot No.
		@param Lot 
		Lot number (alphanumeric)
	  */
	public void setLot (String Lot)
	{
		set_Value (COLUMNNAME_Lot, Lot);
	}

	/** Get Lot No.
		@return Lot number (alphanumeric)
	  */
	public String getLot () 
	{
		return (String)get_Value(COLUMNNAME_Lot);
	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 1) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_Locator() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_Locator.Table_Name);
        I_M_Locator result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Locator)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Locator_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** PostingType AD_Reference_ID=125 */
	public static final int POSTINGTYPE_AD_Reference_ID=125;
	/** Actual = A */
	public static final String POSTINGTYPE_Actual = "A";
	/** Budget = B */
	public static final String POSTINGTYPE_Budget = "B";
	/** Commitment = E */
	public static final String POSTINGTYPE_Commitment = "E";
	/** Statistical = S */
	public static final String POSTINGTYPE_Statistical = "S";
	/** Reservation = R */
	public static final String POSTINGTYPE_Reservation = "R";
	/** Set Posting Type.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{

		if (PostingType == null || PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("S") || PostingType.equals("R")); else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - S - R");		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get Posting Type.
		@return The type of posted amount for the transaction
	  */
	public String getPostingType () 
	{
		return (String)get_Value(COLUMNNAME_PostingType);
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

	/** Set Serial No.
		@param SerNo 
		Product Serial Number 
	  */
	public void setSerNo (String SerNo)
	{
		set_Value (COLUMNNAME_SerNo, SerNo);
	}

	/** Get Serial No.
		@return Product Serial Number 
	  */
	public String getSerNo () 
	{
		return (String)get_Value(COLUMNNAME_SerNo);
	}

	/** Set Usable Life - Months.
		@param UseLifeMonths 
		Months of the usable life of the asset
	  */
	public void setUseLifeMonths (int UseLifeMonths)
	{
		set_Value (COLUMNNAME_UseLifeMonths, Integer.valueOf(UseLifeMonths));
	}

	/** Get Usable Life - Months.
		@return Months of the usable life of the asset
	  */
	public int getUseLifeMonths () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseLifeMonths);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Usable Life - Years.
		@param UseLifeYears 
		Years of the usable life of the asset
	  */
	public void setUseLifeYears (int UseLifeYears)
	{
		set_Value (COLUMNNAME_UseLifeYears, Integer.valueOf(UseLifeYears));
	}

	/** Get Usable Life - Years.
		@return Years of the usable life of the asset
	  */
	public int getUseLifeYears () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseLifeYears);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Use units.
		@param UseUnits 
		Currently used units of the assets
	  */
	public void setUseUnits (int UseUnits)
	{
		set_Value (COLUMNNAME_UseUnits, Integer.valueOf(UseUnits));
	}

	/** Get Use units.
		@return Currently used units of the assets
	  */
	public int getUseUnits () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseUnits);
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
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set Version No.
		@param VersionNo 
		Version Number
	  */
	public void setVersionNo (String VersionNo)
	{
		set_Value (COLUMNNAME_VersionNo, VersionNo);
	}

	/** Get Version No.
		@return Version Number
	  */
	public String getVersionNo () 
	{
		return (String)get_Value(COLUMNNAME_VersionNo);
	}
}