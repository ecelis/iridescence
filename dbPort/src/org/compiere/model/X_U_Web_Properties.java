/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for U_Web_Properties
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_U_Web_Properties extends PO implements I_U_Web_Properties, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_U_Web_Properties (Properties ctx, int U_Web_Properties_ID, String trxName)
    {
      super (ctx, U_Web_Properties_ID, trxName);
      /** if (U_Web_Properties_ID == 0)
        {
			setU_Key (null);
			setU_Value (null);
			setU_Web_Properties_ID (0);
        } */
    }

    /** Load Constructor */
    public X_U_Web_Properties (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_U_Web_Properties[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Key.
		@param U_Key Key	  */
	public void setU_Key (String U_Key)
	{
		if (U_Key == null)
			throw new IllegalArgumentException ("U_Key is mandatory.");
		set_Value (COLUMNNAME_U_Key, U_Key);
	}

	/** Get Key.
		@return Key	  */
	public String getU_Key () 
	{
		return (String)get_Value(COLUMNNAME_U_Key);
	}

	/** Set Value.
		@param U_Value Value	  */
	public void setU_Value (String U_Value)
	{
		if (U_Value == null)
			throw new IllegalArgumentException ("U_Value is mandatory.");
		set_Value (COLUMNNAME_U_Value, U_Value);
	}

	/** Get Value.
		@return Value	  */
	public String getU_Value () 
	{
		return (String)get_Value(COLUMNNAME_U_Value);
	}

	/** Set Web Properties.
		@param U_Web_Properties_ID Web Properties	  */
	public void setU_Web_Properties_ID (int U_Web_Properties_ID)
	{
		if (U_Web_Properties_ID < 1)
			 throw new IllegalArgumentException ("U_Web_Properties_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_U_Web_Properties_ID, Integer.valueOf(U_Web_Properties_ID));
	}

	/** Get Web Properties.
		@return Web Properties	  */
	public int getU_Web_Properties_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_U_Web_Properties_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}