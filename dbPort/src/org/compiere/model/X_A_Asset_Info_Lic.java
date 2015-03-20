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

/** Generated Model for A_Asset_Info_Lic
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_A_Asset_Info_Lic extends PO implements I_A_Asset_Info_Lic, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Asset_Info_Lic (Properties ctx, int A_Asset_Info_Lic_ID, String trxName)
    {
      super (ctx, A_Asset_Info_Lic_ID, trxName);
      /** if (A_Asset_Info_Lic_ID == 0)
        {
			setA_Asset_ID (0);
			setA_Asset_Info_Lic_ID (0);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Info_Lic (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Info_Lic[")
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

	/** Set A Asset Info Lic ID.
		@param A_Asset_Info_Lic_ID A Asset Info Lic ID	  */
	public void setA_Asset_Info_Lic_ID (int A_Asset_Info_Lic_ID)
	{
		if (A_Asset_Info_Lic_ID < 1)
			 throw new IllegalArgumentException ("A_Asset_Info_Lic_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Asset_Info_Lic_ID, Integer.valueOf(A_Asset_Info_Lic_ID));
	}

	/** Get A Asset Info Lic ID.
		@return A Asset Info Lic ID	  */
	public int getA_Asset_Info_Lic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Info_Lic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Info_Lic_ID()));
    }

	/** Set Issuing Agency.
		@param A_Issuing_Agency Issuing Agency	  */
	public void setA_Issuing_Agency (String A_Issuing_Agency)
	{
		set_Value (COLUMNNAME_A_Issuing_Agency, A_Issuing_Agency);
	}

	/** Get Issuing Agency.
		@return Issuing Agency	  */
	public String getA_Issuing_Agency () 
	{
		return (String)get_Value(COLUMNNAME_A_Issuing_Agency);
	}

	/** Set License Fee.
		@param A_License_Fee License Fee	  */
	public void setA_License_Fee (BigDecimal A_License_Fee)
	{
		set_Value (COLUMNNAME_A_License_Fee, A_License_Fee);
	}

	/** Get License Fee.
		@return License Fee	  */
	public BigDecimal getA_License_Fee () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_License_Fee);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set License No.
		@param A_License_No License No	  */
	public void setA_License_No (String A_License_No)
	{
		set_Value (COLUMNNAME_A_License_No, A_License_No);
	}

	/** Get License No.
		@return License No	  */
	public String getA_License_No () 
	{
		return (String)get_Value(COLUMNNAME_A_License_No);
	}

	/** Set Policy Renewal Date.
		@param A_Renewal_Date Policy Renewal Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date)
	{
		set_Value (COLUMNNAME_A_Renewal_Date, A_Renewal_Date);
	}

	/** Get Policy Renewal Date.
		@return Policy Renewal Date	  */
	public Timestamp getA_Renewal_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Renewal_Date);
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

	/** Set Text.
		@param Text Text	  */
	public void setText (String Text)
	{
		set_Value (COLUMNNAME_Text, Text);
	}

	/** Get Text.
		@return Text	  */
	public String getText () 
	{
		return (String)get_Value(COLUMNNAME_Text);
	}
}