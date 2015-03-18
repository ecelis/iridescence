/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_Withholding
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_C_Withholding extends PO implements I_C_Withholding, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_Withholding (Properties ctx, int C_Withholding_ID, String trxName)
    {
      super (ctx, C_Withholding_ID, trxName);
      /** if (C_Withholding_ID == 0)
        {
			setBeneficiary (0);
			setC_PaymentTerm_ID (0);
			setC_Withholding_ID (0);
			setIsPaidTo3Party (false);
			setIsPercentWithholding (false);
			setIsTaxProrated (false);
			setIsTaxWithholding (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_C_Withholding (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Withholding[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Beneficiary.
		@param Beneficiary 
		Business Partner to whom payment is made
	  */
	public void setBeneficiary (int Beneficiary)
	{
		set_Value (COLUMNNAME_Beneficiary, Integer.valueOf(Beneficiary));
	}

	/** Get Beneficiary.
		@return Business Partner to whom payment is made
	  */
	public int getBeneficiary () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Beneficiary);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Withholding.
		@param C_Withholding_ID 
		Withholding type defined
	  */
	public void setC_Withholding_ID (int C_Withholding_ID)
	{
		if (C_Withholding_ID < 1)
			 throw new IllegalArgumentException ("C_Withholding_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Withholding_ID, Integer.valueOf(C_Withholding_ID));
	}

	/** Get Withholding.
		@return Withholding type defined
	  */
	public int getC_Withholding_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Withholding_ID);
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

	/** Set Fix amount.
		@param FixAmt 
		Fix amounted amount to be levied or paid
	  */
	public void setFixAmt (BigDecimal FixAmt)
	{
		set_Value (COLUMNNAME_FixAmt, FixAmt);
	}

	/** Get Fix amount.
		@return Fix amounted amount to be levied or paid
	  */
	public BigDecimal getFixAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FixAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Paid to third party.
		@param IsPaidTo3Party 
		Amount paid to someone other than the Business Partner
	  */
	public void setIsPaidTo3Party (boolean IsPaidTo3Party)
	{
		set_Value (COLUMNNAME_IsPaidTo3Party, Boolean.valueOf(IsPaidTo3Party));
	}

	/** Get Paid to third party.
		@return Amount paid to someone other than the Business Partner
	  */
	public boolean isPaidTo3Party () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaidTo3Party);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percent withholding.
		@param IsPercentWithholding 
		Withholding amount is a percentage of the invoice amount
	  */
	public void setIsPercentWithholding (boolean IsPercentWithholding)
	{
		set_Value (COLUMNNAME_IsPercentWithholding, Boolean.valueOf(IsPercentWithholding));
	}

	/** Get Percent withholding.
		@return Withholding amount is a percentage of the invoice amount
	  */
	public boolean isPercentWithholding () 
	{
		Object oo = get_Value(COLUMNNAME_IsPercentWithholding);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Prorate tax.
		@param IsTaxProrated 
		Tax is Prorated
	  */
	public void setIsTaxProrated (boolean IsTaxProrated)
	{
		set_Value (COLUMNNAME_IsTaxProrated, Boolean.valueOf(IsTaxProrated));
	}

	/** Get Prorate tax.
		@return Tax is Prorated
	  */
	public boolean isTaxProrated () 
	{
		Object oo = get_Value(COLUMNNAME_IsTaxProrated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tax withholding.
		@param IsTaxWithholding 
		This is a tax related withholding
	  */
	public void setIsTaxWithholding (boolean IsTaxWithholding)
	{
		set_Value (COLUMNNAME_IsTaxWithholding, Boolean.valueOf(IsTaxWithholding));
	}

	/** Get Tax withholding.
		@return This is a tax related withholding
	  */
	public boolean isTaxWithholding () 
	{
		Object oo = get_Value(COLUMNNAME_IsTaxWithholding);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Max Amount.
		@param MaxAmt 
		Maximum Amount in invoice currency
	  */
	public void setMaxAmt (BigDecimal MaxAmt)
	{
		set_Value (COLUMNNAME_MaxAmt, MaxAmt);
	}

	/** Get Max Amount.
		@return Maximum Amount in invoice currency
	  */
	public BigDecimal getMaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min Amount.
		@param MinAmt 
		Minimum Amount in invoice currency
	  */
	public void setMinAmt (BigDecimal MinAmt)
	{
		set_Value (COLUMNNAME_MinAmt, MinAmt);
	}

	/** Get Min Amount.
		@return Minimum Amount in invoice currency
	  */
	public BigDecimal getMinAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
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

	/** Set Threshold max.
		@param ThresholdMax 
		Maximum gross amount for withholding calculation  (0=no limit)
	  */
	public void setThresholdMax (BigDecimal ThresholdMax)
	{
		set_Value (COLUMNNAME_ThresholdMax, ThresholdMax);
	}

	/** Get Threshold max.
		@return Maximum gross amount for withholding calculation  (0=no limit)
	  */
	public BigDecimal getThresholdMax () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ThresholdMax);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Threshold min.
		@param Thresholdmin 
		Minimum gross amount for withholding calculation
	  */
	public void setThresholdmin (BigDecimal Thresholdmin)
	{
		set_Value (COLUMNNAME_Thresholdmin, Thresholdmin);
	}

	/** Get Threshold min.
		@return Minimum gross amount for withholding calculation
	  */
	public BigDecimal getThresholdmin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Thresholdmin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}