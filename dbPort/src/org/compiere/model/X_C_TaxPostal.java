/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_TaxPostal
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_C_TaxPostal extends PO implements I_C_TaxPostal, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_TaxPostal (Properties ctx, int C_TaxPostal_ID, String trxName)
    {
      super (ctx, C_TaxPostal_ID, trxName);
      /** if (C_TaxPostal_ID == 0)
        {
			setC_Tax_ID (0);
			setC_TaxPostal_ID (0);
			setPostal (null);
        } */
    }

    /** Load Constructor */
    public X_C_TaxPostal (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_C_TaxPostal[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Tax getC_Tax() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Tax.Table_Name);
        I_C_Tax result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Tax)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Tax_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1)
			 throw new IllegalArgumentException ("C_Tax_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tax ZIP.
		@param C_TaxPostal_ID 
		Tax Postal/ZIP
	  */
	public void setC_TaxPostal_ID (int C_TaxPostal_ID)
	{
		if (C_TaxPostal_ID < 1)
			 throw new IllegalArgumentException ("C_TaxPostal_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_TaxPostal_ID, Integer.valueOf(C_TaxPostal_ID));
	}

	/** Get Tax ZIP.
		@return Tax Postal/ZIP
	  */
	public int getC_TaxPostal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_TaxPostal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ZIP.
		@param Postal 
		Postal code
	  */
	public void setPostal (String Postal)
	{
		if (Postal == null)
			throw new IllegalArgumentException ("Postal is mandatory.");
		set_Value (COLUMNNAME_Postal, Postal);
	}

	/** Get ZIP.
		@return Postal code
	  */
	public String getPostal () 
	{
		return (String)get_Value(COLUMNNAME_Postal);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getPostal());
    }

	/** Set ZIP To.
		@param Postal_To 
		Postal code to
	  */
	public void setPostal_To (String Postal_To)
	{
		set_Value (COLUMNNAME_Postal_To, Postal_To);
	}

	/** Get ZIP To.
		@return Postal code to
	  */
	public String getPostal_To () 
	{
		return (String)get_Value(COLUMNNAME_Postal_To);
	}
}