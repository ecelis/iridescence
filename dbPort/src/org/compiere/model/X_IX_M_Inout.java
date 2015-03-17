/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for IX_M_Inout
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_IX_M_Inout extends PO implements I_IX_M_Inout, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_IX_M_Inout (Properties ctx, int IX_M_Inout_ID, String trxName)
    {
      super (ctx, IX_M_Inout_ID, trxName);
      /** if (IX_M_Inout_ID == 0)
        {
			setIX_M_Inout_ID (0);
			setNombreArchivoInterfase (null);
        } */
    }

    /** Load Constructor */
    public X_IX_M_Inout (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_IX_M_Inout[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set In/Out.
		@param IX_M_Inout_ID 
		In/Out
	  */
	public void setIX_M_Inout_ID (int IX_M_Inout_ID)
	{
		if (IX_M_Inout_ID < 1)
			 throw new IllegalArgumentException ("IX_M_Inout_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_IX_M_Inout_ID, Integer.valueOf(IX_M_Inout_ID));
	}

	/** Get In/Out.
		@return In/Out
	  */
	public int getIX_M_Inout_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IX_M_Inout_ID);
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