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

/** Generated Model for A_Asset_Reval_Index
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_A_Asset_Reval_Index extends PO implements I_A_Asset_Reval_Index, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Asset_Reval_Index (Properties ctx, int A_Asset_Reval_Index_ID, String trxName)
    {
      super (ctx, A_Asset_Reval_Index_ID, trxName);
      /** if (A_Asset_Reval_Index_ID == 0)
        {
			setA_Asset_Reval_Index_ID (0);
			setA_Effective_Date (new Timestamp( System.currentTimeMillis() ));
			setA_Reval_Code (null);
			setA_Reval_Multiplier (null);
			setA_Reval_Rate (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Reval_Index (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Reval_Index[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Asset Reval Index ID.
		@param A_Asset_Reval_Index_ID Asset Reval Index ID	  */
	public void setA_Asset_Reval_Index_ID (int A_Asset_Reval_Index_ID)
	{
		if (A_Asset_Reval_Index_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Reval_Index_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_Reval_Index_ID, Integer.valueOf(A_Asset_Reval_Index_ID));
	}

	/** Get Asset Reval Index ID.
		@return Asset Reval Index ID	  */
	public int getA_Asset_Reval_Index_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Reval_Index_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Reval_Index_ID()));
    }

	/** Set Effective Date.
		@param A_Effective_Date Effective Date	  */
	public void setA_Effective_Date (Timestamp A_Effective_Date)
	{
		if (A_Effective_Date == null)
			throw new IllegalArgumentException ("A_Effective_Date is mandatory.");
		set_Value (COLUMNNAME_A_Effective_Date, A_Effective_Date);
	}

	/** Get Effective Date.
		@return Effective Date	  */
	public Timestamp getA_Effective_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Effective_Date);
	}

	/** A_Reval_Code AD_Reference_ID=1200309 */
	public static final int A_REVAL_CODE_AD_Reference_ID=1200309;
	/** Revaluation Code #1 = R01 */
	public static final String A_REVAL_CODE_RevaluationCode1 = "R01";
	/** Revaluation Code #2 = R02 */
	public static final String A_REVAL_CODE_RevaluationCode2 = "R02";
	/** Revaluation Code #3 = R03 */
	public static final String A_REVAL_CODE_RevaluationCode3 = "R03";
	/** Set Reval Code.
		@param A_Reval_Code Reval Code	  */
	public void setA_Reval_Code (String A_Reval_Code)
	{
		if (A_Reval_Code == null) throw new IllegalArgumentException ("A_Reval_Code is mandatory");
		if (A_Reval_Code.equals("R01") || A_Reval_Code.equals("R02") || A_Reval_Code.equals("R03")); else throw new IllegalArgumentException ("A_Reval_Code Invalid value - " + A_Reval_Code + " - Reference_ID=1200309 - R01 - R02 - R03");		set_Value (COLUMNNAME_A_Reval_Code, A_Reval_Code);
	}

	/** Get Reval Code.
		@return Reval Code	  */
	public String getA_Reval_Code () 
	{
		return (String)get_Value(COLUMNNAME_A_Reval_Code);
	}

	/** A_Reval_Multiplier AD_Reference_ID=1200307 */
	public static final int A_REVAL_MULTIPLIER_AD_Reference_ID=1200307;
	/** Factor = FAC */
	public static final String A_REVAL_MULTIPLIER_Factor = "FAC";
	/** Index = IND */
	public static final String A_REVAL_MULTIPLIER_Index = "IND";
	/** Set A Reval Multiplier.
		@param A_Reval_Multiplier A Reval Multiplier	  */
	public void setA_Reval_Multiplier (String A_Reval_Multiplier)
	{
		if (A_Reval_Multiplier == null) throw new IllegalArgumentException ("A_Reval_Multiplier is mandatory");
		if (A_Reval_Multiplier.equals("FAC") || A_Reval_Multiplier.equals("IND")); else throw new IllegalArgumentException ("A_Reval_Multiplier Invalid value - " + A_Reval_Multiplier + " - Reference_ID=1200307 - FAC - IND");		set_Value (COLUMNNAME_A_Reval_Multiplier, A_Reval_Multiplier);
	}

	/** Get A Reval Multiplier.
		@return A Reval Multiplier	  */
	public String getA_Reval_Multiplier () 
	{
		return (String)get_Value(COLUMNNAME_A_Reval_Multiplier);
	}

	/** Set Reval Rate.
		@param A_Reval_Rate Reval Rate	  */
	public void setA_Reval_Rate (BigDecimal A_Reval_Rate)
	{
		if (A_Reval_Rate == null)
			throw new IllegalArgumentException ("A_Reval_Rate is mandatory.");
		set_Value (COLUMNNAME_A_Reval_Rate, A_Reval_Rate);
	}

	/** Get Reval Rate.
		@return Reval Rate	  */
	public BigDecimal getA_Reval_Rate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Reval_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}