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

/** Generated Model for EXME_DescPrecioFijo
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_DescPrecioFijo extends PO implements I_EXME_DescPrecioFijo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_DescPrecioFijo (Properties ctx, int EXME_DescPrecioFijo_ID, String trxName)
    {
      super (ctx, EXME_DescPrecioFijo_ID, trxName);
      /** if (EXME_DescPrecioFijo_ID == 0)
        {
			setAplicaCoaseDeduc (false);
			setAplicaConvenio (false);
			setC_Charge_ID (0);
			setDescFijo (Env.ZERO);
			setEXME_DescPrecioFijo_ID (0);
			setExtensionNo (0);
			setFactValorReal (false);
			setPrecioFijo (Env.ZERO);
			setTopeMaxCta (Env.ZERO);
			setTopeMaxDesc (Env.ZERO);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_DescPrecioFijo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_DescPrecioFijo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Discount on Deductible and Coinsurance.
		@param AplicaCoaseDeduc 
		Apply discount on deductible and coinsurance
	  */
	public void setAplicaCoaseDeduc (boolean AplicaCoaseDeduc)
	{
		set_Value (COLUMNNAME_AplicaCoaseDeduc, Boolean.valueOf(AplicaCoaseDeduc));
	}

	/** Get Discount on Deductible and Coinsurance.
		@return Apply discount on deductible and coinsurance
	  */
	public boolean isAplicaCoaseDeduc () 
	{
		Object oo = get_Value(COLUMNNAME_AplicaCoaseDeduc);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Applies Agreement.
		@param AplicaConvenio 
		Apply discount for Agreement
	  */
	public void setAplicaConvenio (boolean AplicaConvenio)
	{
		set_Value (COLUMNNAME_AplicaConvenio, Boolean.valueOf(AplicaConvenio));
	}

	/** Get Applies Agreement.
		@return Apply discount for Agreement
	  */
	public boolean isAplicaConvenio () 
	{
		Object oo = get_Value(COLUMNNAME_AplicaConvenio);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_C_Charge getC_Charge() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Charge.Table_Name);
        I_C_Charge result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Charge)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Charge_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1)
			 throw new IllegalArgumentException ("C_Charge_ID is mandatory.");
		set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fixed Discount.
		@param DescFijo 
		Fixed Discount
	  */
	public void setDescFijo (BigDecimal DescFijo)
	{
		if (DescFijo == null)
			throw new IllegalArgumentException ("DescFijo is mandatory.");
		set_Value (COLUMNNAME_DescFijo, DescFijo);
	}

	/** Get Fixed Discount.
		@return Fixed Discount
	  */
	public BigDecimal getDescFijo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DescFijo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Discount for a fixed billing Price.
		@param EXME_DescPrecioFijo_ID 
		Discount for a fixed billing Price
	  */
	public void setEXME_DescPrecioFijo_ID (int EXME_DescPrecioFijo_ID)
	{
		if (EXME_DescPrecioFijo_ID < 1)
			 throw new IllegalArgumentException ("EXME_DescPrecioFijo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_DescPrecioFijo_ID, Integer.valueOf(EXME_DescPrecioFijo_ID));
	}

	/** Get Discount for a fixed billing Price.
		@return Discount for a fixed billing Price
	  */
	public int getEXME_DescPrecioFijo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DescPrecioFijo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Extension Number.
		@param ExtensionNo 
		Extension Number
	  */
	public void setExtensionNo (int ExtensionNo)
	{
		set_Value (COLUMNNAME_ExtensionNo, Integer.valueOf(ExtensionNo));
	}

	/** Get Extension Number.
		@return Extension Number
	  */
	public int getExtensionNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ExtensionNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set FactValorReal.
		@param FactValorReal 
		Invoice value of the account
	  */
	public void setFactValorReal (boolean FactValorReal)
	{
		set_Value (COLUMNNAME_FactValorReal, Boolean.valueOf(FactValorReal));
	}

	/** Get FactValorReal.
		@return Invoice value of the account
	  */
	public boolean isFactValorReal () 
	{
		Object oo = get_Value(COLUMNNAME_FactValorReal);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Fixed Price.
		@param PrecioFijo 
		Fixed Price
	  */
	public void setPrecioFijo (BigDecimal PrecioFijo)
	{
		if (PrecioFijo == null)
			throw new IllegalArgumentException ("PrecioFijo is mandatory.");
		set_Value (COLUMNNAME_PrecioFijo, PrecioFijo);
	}

	/** Get Fixed Price.
		@return Fixed Price
	  */
	public BigDecimal getPrecioFijo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PrecioFijo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Maximum total of the Account.
		@param TopeMaxCta 
		Maximum total value of the account
	  */
	public void setTopeMaxCta (BigDecimal TopeMaxCta)
	{
		if (TopeMaxCta == null)
			throw new IllegalArgumentException ("TopeMaxCta is mandatory.");
		set_Value (COLUMNNAME_TopeMaxCta, TopeMaxCta);
	}

	/** Get Maximum total of the Account.
		@return Maximum total value of the account
	  */
	public BigDecimal getTopeMaxCta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TopeMaxCta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Upper limit for application of discount.
		@param TopeMaxDesc 
		Upper limit for application of discount
	  */
	public void setTopeMaxDesc (BigDecimal TopeMaxDesc)
	{
		if (TopeMaxDesc == null)
			throw new IllegalArgumentException ("TopeMaxDesc is mandatory.");
		set_Value (COLUMNNAME_TopeMaxDesc, TopeMaxDesc);
	}

	/** Get Upper limit for application of discount.
		@return Upper limit for application of discount
	  */
	public BigDecimal getTopeMaxDesc () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TopeMaxDesc);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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