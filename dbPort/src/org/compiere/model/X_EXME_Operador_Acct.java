/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_Operador_Acct
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Operador_Acct extends PO implements I_EXME_Operador_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Operador_Acct (Properties ctx, int EXME_Operador_Acct_ID, String trxName)
    {
      super (ctx, EXME_Operador_Acct_ID, trxName);
      /** if (EXME_Operador_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setEXME_Operador_ID (0);
			setO_DeudDiv_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Operador_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Operador_Acct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1)
			 throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
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

	/** Set Operator.
		@param EXME_Operador_ID 
		Operator
	  */
	public void setEXME_Operador_ID (int EXME_Operador_ID)
	{
		if (EXME_Operador_ID < 1)
			 throw new IllegalArgumentException ("EXME_Operador_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Operador_ID, Integer.valueOf(EXME_Operador_ID));
	}

	/** Get Operator.
		@return Operator
	  */
	public int getEXME_Operador_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Operador_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sundry debtors.
		@param O_DeudDiv_Acct 
		Sundry debtors account for the Operator
	  */
	public void setO_DeudDiv_Acct (int O_DeudDiv_Acct)
	{
		set_Value (COLUMNNAME_O_DeudDiv_Acct, Integer.valueOf(O_DeudDiv_Acct));
	}

	/** Get Sundry debtors.
		@return Sundry debtors account for the Operator
	  */
	public int getO_DeudDiv_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_O_DeudDiv_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}