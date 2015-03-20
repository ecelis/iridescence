/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for U_BlackListCheque
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_U_BlackListCheque extends PO implements I_U_BlackListCheque, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_U_BlackListCheque (Properties ctx, int U_BlackListCheque_ID, String trxName)
    {
      super (ctx, U_BlackListCheque_ID, trxName);
      /** if (U_BlackListCheque_ID == 0)
        {
			setBankName (null);
			setChequeNo (null);
			setU_BlackListCheque_ID (0);
        } */
    }

    /** Load Constructor */
    public X_U_BlackListCheque (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_U_BlackListCheque[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Bank Name.
		@param BankName Bank Name	  */
	public void setBankName (String BankName)
	{
		if (BankName == null)
			throw new IllegalArgumentException ("BankName is mandatory.");
		set_Value (COLUMNNAME_BankName, BankName);
	}

	/** Get Bank Name.
		@return Bank Name	  */
	public String getBankName () 
	{
		return (String)get_Value(COLUMNNAME_BankName);
	}

	/** Set Cheque No.
		@param ChequeNo Cheque No	  */
	public void setChequeNo (String ChequeNo)
	{
		if (ChequeNo == null)
			throw new IllegalArgumentException ("ChequeNo is mandatory.");
		set_Value (COLUMNNAME_ChequeNo, ChequeNo);
	}

	/** Get Cheque No.
		@return Cheque No	  */
	public String getChequeNo () 
	{
		return (String)get_Value(COLUMNNAME_ChequeNo);
	}

	/** Set Black List Cheque.
		@param U_BlackListCheque_ID Black List Cheque	  */
	public void setU_BlackListCheque_ID (int U_BlackListCheque_ID)
	{
		if (U_BlackListCheque_ID < 1)
			 throw new IllegalArgumentException ("U_BlackListCheque_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_U_BlackListCheque_ID, Integer.valueOf(U_BlackListCheque_ID));
	}

	/** Get Black List Cheque.
		@return Black List Cheque	  */
	public int getU_BlackListCheque_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_U_BlackListCheque_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}