/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EI_C_Cashline
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EI_C_Cashline extends PO implements I_EI_C_Cashline, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EI_C_Cashline (Properties ctx, int EI_C_Cashline_ID, String trxName)
    {
      super (ctx, EI_C_Cashline_ID, trxName);
      /** if (EI_C_Cashline_ID == 0)
        {
			setC_CashLine_ID (0);
			setEI_C_Cashline_ID (0);
			setErrorDescription (null);
			setErrorStatus (false);
        } */
    }

    /** Load Constructor */
    public X_EI_C_Cashline (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EI_C_Cashline[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_CashLine getC_CashLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_C_CashLine.Table_Name);
        I_C_CashLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_CashLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_CashLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Cash Journal Line.
		@param C_CashLine_ID 
		Cash Journal Line
	  */
	public void setC_CashLine_ID (int C_CashLine_ID)
	{
		if (C_CashLine_ID < 1)
			 throw new IllegalArgumentException ("C_CashLine_ID is mandatory.");
		set_Value (COLUMNNAME_C_CashLine_ID, Integer.valueOf(C_CashLine_ID));
	}

	/** Get Cash Journal Line.
		@return Cash Journal Line
	  */
	public int getC_CashLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CashLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Cashline Key.
		@param EI_C_Cashline_ID 
		Cashline Key
	  */
	public void setEI_C_Cashline_ID (int EI_C_Cashline_ID)
	{
		if (EI_C_Cashline_ID < 1)
			 throw new IllegalArgumentException ("EI_C_Cashline_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EI_C_Cashline_ID, Integer.valueOf(EI_C_Cashline_ID));
	}

	/** Get Cashline Key.
		@return Cashline Key
	  */
	public int getEI_C_Cashline_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EI_C_Cashline_ID);
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