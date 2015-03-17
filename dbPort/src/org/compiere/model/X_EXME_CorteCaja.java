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

/** Generated Model for EXME_CorteCaja
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_CorteCaja extends PO implements I_EXME_CorteCaja, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CorteCaja (Properties ctx, int EXME_CorteCaja_ID, String trxName)
    {
      super (ctx, EXME_CorteCaja_ID, trxName);
      /** if (EXME_CorteCaja_ID == 0)
        {
			setCashierAmount (Env.ZERO);
			setC_Cash_ID (0);
			setEXME_CorteCaja_ID (0);
			setName (null);
			setStatementDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_CorteCaja (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CorteCaja[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Cashier amount.
		@param CashierAmount 
		Cashier amount
	  */
	public void setCashierAmount (BigDecimal CashierAmount)
	{
		if (CashierAmount == null)
			throw new IllegalArgumentException ("CashierAmount is mandatory.");
		set_Value (COLUMNNAME_CashierAmount, CashierAmount);
	}

	/** Get Cashier amount.
		@return Cashier amount
	  */
	public BigDecimal getCashierAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CashierAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_Cash getC_Cash() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Cash.Table_Name);
        I_C_Cash result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Cash)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Cash_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cash Journal.
		@param C_Cash_ID 
		Cash Journal
	  */
	public void setC_Cash_ID (int C_Cash_ID)
	{
		if (C_Cash_ID < 1)
			 throw new IllegalArgumentException ("C_Cash_ID is mandatory.");
		set_Value (COLUMNNAME_C_Cash_ID, Integer.valueOf(C_Cash_ID));
	}

	/** Get Cash Journal.
		@return Cash Journal
	  */
	public int getC_Cash_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Cash_ID);
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

	/** Set Cash Balance.
		@param EXME_CorteCaja_ID 
		Identifier of Cash Balance
	  */
	public void setEXME_CorteCaja_ID (int EXME_CorteCaja_ID)
	{
		if (EXME_CorteCaja_ID < 1)
			 throw new IllegalArgumentException ("EXME_CorteCaja_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CorteCaja_ID, Integer.valueOf(EXME_CorteCaja_ID));
	}

	/** Get Cash Balance.
		@return Identifier of Cash Balance
	  */
	public int getEXME_CorteCaja_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CorteCaja_ID);
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

	/** Set Statement date.
		@param StatementDate 
		Date of the statement
	  */
	public void setStatementDate (Timestamp StatementDate)
	{
		if (StatementDate == null)
			throw new IllegalArgumentException ("StatementDate is mandatory.");
		set_Value (COLUMNNAME_StatementDate, StatementDate);
	}

	/** Get Statement date.
		@return Date of the statement
	  */
	public Timestamp getStatementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StatementDate);
	}
}