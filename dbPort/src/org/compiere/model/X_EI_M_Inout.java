/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EI_M_Inout
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EI_M_Inout extends PO implements I_EI_M_Inout, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EI_M_Inout (Properties ctx, int EI_M_Inout_ID, String trxName)
    {
      super (ctx, EI_M_Inout_ID, trxName);
      /** if (EI_M_Inout_ID == 0)
        {
			setC_Invoice_ID (0);
			setEI_M_Inout_ID (0);
			setErrorDescription (null);
			setErrorStatus (false);
        } */
    }

    /** Load Constructor */
    public X_EI_M_Inout (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EI_M_Inout[")
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

	/** Set Warehouse material Input/Output.
		@param EI_M_Inout_ID 
		Warehouse material Input/Output
	  */
	public void setEI_M_Inout_ID (int EI_M_Inout_ID)
	{
		if (EI_M_Inout_ID < 1)
			 throw new IllegalArgumentException ("EI_M_Inout_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EI_M_Inout_ID, Integer.valueOf(EI_M_Inout_ID));
	}

	/** Get Warehouse material Input/Output.
		@return Warehouse material Input/Output
	  */
	public int getEI_M_Inout_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EI_M_Inout_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ErrorDescription.
		@param ErrorDescription 
		ErrorDescription
	  */
	public void setErrorDescription (String ErrorDescription)
	{
		if (ErrorDescription == null)
			throw new IllegalArgumentException ("ErrorDescription is mandatory.");
		set_Value (COLUMNNAME_ErrorDescription, ErrorDescription);
	}

	/** Get ErrorDescription.
		@return ErrorDescription
	  */
	public String getErrorDescription () 
	{
		return (String)get_Value(COLUMNNAME_ErrorDescription);
	}

	/** Set ErrorStatus.
		@param ErrorStatus 
		ErrorStatus
	  */
	public void setErrorStatus (boolean ErrorStatus)
	{
		set_Value (COLUMNNAME_ErrorStatus, Boolean.valueOf(ErrorStatus));
	}

	/** Get ErrorStatus.
		@return ErrorStatus
	  */
	public boolean isErrorStatus () 
	{
		Object oo = get_Value(COLUMNNAME_ErrorStatus);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}