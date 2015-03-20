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

/** Generated Model for EXME_CorteCajaDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_CorteCajaDet extends PO implements I_EXME_CorteCajaDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_CorteCajaDet (Properties ctx, int EXME_CorteCajaDet_ID, String trxName)
    {
      super (ctx, EXME_CorteCajaDet_ID, trxName);
      /** if (EXME_CorteCajaDet_ID == 0)
        {
			setAmount (Env.ZERO);
			setC_Currency_ID (0);
			setEXME_CorteCajaDet_ID (0);
			setEXME_CorteCaja_ID (0);
			setEXME_FormaPago_ID (0);
			setLine (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_CorteCajaDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_CorteCajaDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		if (Amount == null)
			throw new IllegalArgumentException ("Amount is mandatory.");
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_C_Currency getC_Currency() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Currency.Table_Name);
        I_C_Currency result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Currency)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Currency_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1)
			 throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
		set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
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

	/** Set Detail Cash Balance.
		@param EXME_CorteCajaDet_ID 
		Identifier of cash balance detail
	  */
	public void setEXME_CorteCajaDet_ID (int EXME_CorteCajaDet_ID)
	{
		if (EXME_CorteCajaDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_CorteCajaDet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_CorteCajaDet_ID, Integer.valueOf(EXME_CorteCajaDet_ID));
	}

	/** Get Detail Cash Balance.
		@return Identifier of cash balance detail
	  */
	public int getEXME_CorteCajaDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_CorteCajaDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Payment Form.
		@param EXME_FormaPago_ID 
		Identifier of Payment Form
	  */
	public void setEXME_FormaPago_ID (int EXME_FormaPago_ID)
	{
		if (EXME_FormaPago_ID < 1)
			 throw new IllegalArgumentException ("EXME_FormaPago_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_FormaPago_ID, Integer.valueOf(EXME_FormaPago_ID));
	}

	/** Get Payment Form.
		@return Identifier of Payment Form
	  */
	public int getEXME_FormaPago_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FormaPago_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}