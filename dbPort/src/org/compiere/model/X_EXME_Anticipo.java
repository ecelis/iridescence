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

/** Generated Model for EXME_Anticipo
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Anticipo extends PO implements I_EXME_Anticipo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Anticipo (Properties ctx, int EXME_Anticipo_ID, String trxName)
    {
      super (ctx, EXME_Anticipo_ID, trxName);
      /** if (EXME_Anticipo_ID == 0)
        {
			setEXME_Anticipo_ID (0);
			setEXME_CtaPacExt_ID (0);
			setEXME_CtaPac_ID (0);
			setTotal (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_Anticipo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Anticipo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Applied Amount.
		@param Aplicado 
		Applied Amount
	  */
	public void setAplicado (BigDecimal Aplicado)
	{
		set_Value (COLUMNNAME_Aplicado, Aplicado);
	}

	/** Get Applied Amount.
		@return Applied Amount
	  */
	public BigDecimal getAplicado () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Aplicado);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Prepayment.
		@param EXME_Anticipo_ID Prepayment	  */
	public void setEXME_Anticipo_ID (int EXME_Anticipo_ID)
	{
		if (EXME_Anticipo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Anticipo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Anticipo_ID, Integer.valueOf(EXME_Anticipo_ID));
	}

	/** Get Prepayment.
		@return Prepayment	  */
	public int getEXME_Anticipo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Anticipo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPacExt getEXME_CtaPacExt() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPacExt.Table_Name);
        I_EXME_CtaPacExt result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPacExt)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPacExt_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account Extension.
		@param EXME_CtaPacExt_ID 
		Patient Account Extension
	  */
	public void setEXME_CtaPacExt_ID (int EXME_CtaPacExt_ID)
	{
		if (EXME_CtaPacExt_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPacExt_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPacExt_ID, Integer.valueOf(EXME_CtaPacExt_ID));
	}

	/** Get Patient Account Extension.
		@return Patient Account Extension
	  */
	public int getEXME_CtaPacExt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPacExt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_CtaPac getEXME_CtaPac() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_CtaPac.Table_Name);
        I_EXME_CtaPac result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_CtaPac)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_CtaPac_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Patient Account.
		@param EXME_CtaPac_ID 
		Patient Account
	  */
	public void setEXME_CtaPac_ID (int EXME_CtaPac_ID)
	{
		if (EXME_CtaPac_ID < 1)
			 throw new IllegalArgumentException ("EXME_CtaPac_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CtaPac_ID, Integer.valueOf(EXME_CtaPac_ID));
	}

	/** Get Patient Account.
		@return Patient Account
	  */
	public int getEXME_CtaPac_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CtaPac_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Balance.
		@param Saldo 
		Balance of Patient Account
	  */
	public void setSaldo (BigDecimal Saldo)
	{
		set_Value (COLUMNNAME_Saldo, Saldo);
	}

	/** Get Balance.
		@return Balance of Patient Account
	  */
	public BigDecimal getSaldo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Saldo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total.
		@param Total Total	  */
	public void setTotal (BigDecimal Total)
	{
		if (Total == null)
			throw new IllegalArgumentException ("Total is mandatory.");
		set_Value (COLUMNNAME_Total, Total);
	}

	/** Get Total.
		@return Total	  */
	public BigDecimal getTotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Total);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}