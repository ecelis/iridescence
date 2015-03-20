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

/** Generated Model for C_Year
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_C_Year extends PO implements I_C_Year, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_Year (Properties ctx, int C_Year_ID, String trxName)
    {
      super (ctx, C_Year_ID, trxName);
      /** if (C_Year_ID == 0)
        {
			setC_Calendar_ID (0);
			setC_Year_ID (0);
			setFiscalYear (null);
			setYear (null);
        } */
    }

    /** Load Constructor */
    public X_C_Year (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Year[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Calendar getC_Calendar() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_Calendar.Table_Name);
        I_C_Calendar result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Calendar)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Calendar_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Calendar.
		@param C_Calendar_ID 
		Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID)
	{
		if (C_Calendar_ID < 1)
			 throw new IllegalArgumentException ("C_Calendar_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Calendar_ID, Integer.valueOf(C_Calendar_ID));
	}

	/** Get Calendar.
		@return Accounting Calendar Name
	  */
	public int getC_Calendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Calendar_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ¿Cerrado?.
		@param Cierre ¿Cerrado?	  */
	public void setCierre (boolean Cierre)
	{
		set_ValueNoCheck (COLUMNNAME_Cierre, Boolean.valueOf(Cierre));
	}

	/** Get ¿Cerrado?.
		@return ¿Cerrado?	  */
	public boolean isCierre () 
	{
		Object oo = get_Value(COLUMNNAME_Cierre);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Year.
		@param C_Year_ID 
		Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID)
	{
		if (C_Year_ID < 1)
			 throw new IllegalArgumentException ("C_Year_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Year_ID, Integer.valueOf(C_Year_ID));
	}

	/** Get Year.
		@return Calendar Year
	  */
	public int getC_Year_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Year_ID);
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

	/** Set Year.
		@param FiscalYear 
		The Fiscal Year
	  */
	public void setFiscalYear (String FiscalYear)
	{
		if (FiscalYear == null)
			throw new IllegalArgumentException ("FiscalYear is mandatory.");
		set_Value (COLUMNNAME_FiscalYear, FiscalYear);
	}

	/** Get Year.
		@return The Fiscal Year
	  */
	public String getFiscalYear () 
	{
		return (String)get_Value(COLUMNNAME_FiscalYear);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getFiscalYear());
    }

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Year.
		@param Year 
		Calendar Year
	  */
	public void setYear (String Year)
	{
		if (Year == null)
			throw new IllegalArgumentException ("Year is mandatory.");
		set_Value (COLUMNNAME_Year, Year);
	}

	/** Get Year.
		@return Calendar Year
	  */
	public String getYear () 
	{
		return (String)get_Value(COLUMNNAME_Year);
	}
}