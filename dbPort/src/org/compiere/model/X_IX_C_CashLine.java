/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for IX_C_CashLine
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_IX_C_CashLine extends PO implements I_IX_C_CashLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_IX_C_CashLine (Properties ctx, int IX_C_CashLine_ID, String trxName)
    {
      super (ctx, IX_C_CashLine_ID, trxName);
      /** if (IX_C_CashLine_ID == 0)
        {
			setIX_C_CashLine_ID (0);
			setNombreArchivoInterfase (null);
        } */
    }

    /** Load Constructor */
    public X_IX_C_CashLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_IX_C_CashLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Cash Line.
		@param IX_C_CashLine_ID Cash Line	  */
	public void setIX_C_CashLine_ID (int IX_C_CashLine_ID)
	{
		if (IX_C_CashLine_ID < 1)
			 throw new IllegalArgumentException ("IX_C_CashLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_IX_C_CashLine_ID, Integer.valueOf(IX_C_CashLine_ID));
	}

	/** Get Cash Line.
		@return Cash Line	  */
	public int getIX_C_CashLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IX_C_CashLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interface File Name.
		@param NombreArchivoInterfase Interface File Name	  */
	public void setNombreArchivoInterfase (String NombreArchivoInterfase)
	{
		if (NombreArchivoInterfase == null)
			throw new IllegalArgumentException ("NombreArchivoInterfase is mandatory.");
		set_Value (COLUMNNAME_NombreArchivoInterfase, NombreArchivoInterfase);
	}

	/** Get Interface File Name.
		@return Interface File Name	  */
	public String getNombreArchivoInterfase () 
	{
		return (String)get_Value(COLUMNNAME_NombreArchivoInterfase);
	}
}