/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EI_M_Movementline
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EI_M_Movementline extends PO implements I_EI_M_Movementline, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EI_M_Movementline (Properties ctx, int EI_M_Movementline_ID, String trxName)
    {
      super (ctx, EI_M_Movementline_ID, trxName);
      /** if (EI_M_Movementline_ID == 0)
        {
			setEI_M_Movementline_ID (0);
			setErrorDescription (null);
			setErrorStatus (false);
        } */
    }

    /** Load Constructor */
    public X_EI_M_Movementline (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EI_M_Movementline[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Movement Line.
		@param EI_M_Movementline_ID 
		Movement Line
	  */
	public void setEI_M_Movementline_ID (int EI_M_Movementline_ID)
	{
		if (EI_M_Movementline_ID < 1)
			 throw new IllegalArgumentException ("EI_M_Movementline_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EI_M_Movementline_ID, Integer.valueOf(EI_M_Movementline_ID));
	}

	/** Get Movement Line.
		@return Movement Line
	  */
	public int getEI_M_Movementline_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EI_M_Movementline_ID);
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