/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EI_M_Provision
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EI_M_Provision extends PO implements I_EI_M_Provision, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EI_M_Provision (Properties ctx, int EI_M_Provision_ID, String trxName)
    {
      super (ctx, EI_M_Provision_ID, trxName);
      /** if (EI_M_Provision_ID == 0)
        {
			setEI_M_Provision_ID (0);
			setErrorDescription (null);
			setErrorStatus (false);
			setM_Provision_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EI_M_Provision (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EI_M_Provision[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Provision.
		@param EI_M_Provision_ID 
		Provision Key
	  */
	public void setEI_M_Provision_ID (int EI_M_Provision_ID)
	{
		if (EI_M_Provision_ID < 1)
			 throw new IllegalArgumentException ("EI_M_Provision_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EI_M_Provision_ID, Integer.valueOf(EI_M_Provision_ID));
	}

	/** Get Provision.
		@return Provision Key
	  */
	public int getEI_M_Provision_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EI_M_Provision_ID);
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

	/** Set Provision.
		@param M_Provision_ID 
		Files names generated during the sales provision interface
	  */
	public void setM_Provision_ID (int M_Provision_ID)
	{
		if (M_Provision_ID < 1)
			 throw new IllegalArgumentException ("M_Provision_ID is mandatory.");
		set_Value (COLUMNNAME_M_Provision_ID, Integer.valueOf(M_Provision_ID));
	}

	/** Get Provision.
		@return Files names generated during the sales provision interface
	  */
	public int getM_Provision_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Provision_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}