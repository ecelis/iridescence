/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for IX_C_Invoice
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_IX_C_Invoice extends PO implements I_IX_C_Invoice, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_IX_C_Invoice (Properties ctx, int IX_C_Invoice_ID, String trxName)
    {
      super (ctx, IX_C_Invoice_ID, trxName);
      /** if (IX_C_Invoice_ID == 0)
        {
			setC_Invoice_ID (0);
			setIX_C_Invoice_ID (0);
			setNombreArchivoInterfase (null);
        } */
    }

    /** Load Constructor */
    public X_IX_C_Invoice (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_IX_C_Invoice[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Invoice getC_Invoice() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Invoice.Table_Name);
        I_C_Invoice result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Invoice)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Invoice_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1)
			 throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
		set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Invoice.
		@param IX_C_Invoice_ID Invoice	  */
	public void setIX_C_Invoice_ID (int IX_C_Invoice_ID)
	{
		if (IX_C_Invoice_ID < 1)
			 throw new IllegalArgumentException ("IX_C_Invoice_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_IX_C_Invoice_ID, Integer.valueOf(IX_C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice	  */
	public int getIX_C_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IX_C_Invoice_ID);
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