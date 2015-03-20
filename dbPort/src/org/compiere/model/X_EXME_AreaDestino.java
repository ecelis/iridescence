/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_AreaDestino
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_AreaDestino extends PO implements I_EXME_AreaDestino, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AreaDestino (Properties ctx, int EXME_AreaDestino_ID, String trxName)
    {
      super (ctx, EXME_AreaDestino_ID, trxName);
      /** if (EXME_AreaDestino_ID == 0)
        {
			setEXME_AreaDestino_ID (0);
			setEXME_Area_ID (0);
			setEXME_AreaTo_ID (0);
			setRequireAuthentication (false);
        } */
    }

    /** Load Constructor */
    public X_EXME_AreaDestino (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_AreaDestino[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Alert Message.
		@param AlertMessage 
		Alert Message
	  */
	public void setAlertMessage (String AlertMessage)
	{
		set_Value (COLUMNNAME_AlertMessage, AlertMessage);
	}

	/** Get Alert Message.
		@return Alert Message
	  */
	public String getAlertMessage () 
	{
		return (String)get_Value(COLUMNNAME_AlertMessage);
	}

	/** Set To Service.
		@param EXME_AreaDestino_ID To Service	  */
	public void setEXME_AreaDestino_ID (int EXME_AreaDestino_ID)
	{
		if (EXME_AreaDestino_ID < 1)
			 throw new IllegalArgumentException ("EXME_AreaDestino_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AreaDestino_ID, Integer.valueOf(EXME_AreaDestino_ID));
	}

	/** Get To Service.
		@return To Service	  */
	public int getEXME_AreaDestino_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AreaDestino_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service.
		@param EXME_Area_ID 
		Service
	  */
	public void setEXME_Area_ID (int EXME_Area_ID)
	{
		if (EXME_Area_ID < 1)
			 throw new IllegalArgumentException ("EXME_Area_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Area_ID, Integer.valueOf(EXME_Area_ID));
	}

	/** Get Service.
		@return Service
	  */
	public int getEXME_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Area_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set To Service.
		@param EXME_AreaTo_ID To Service	  */
	public void setEXME_AreaTo_ID (int EXME_AreaTo_ID)
	{
		if (EXME_AreaTo_ID < 1)
			 throw new IllegalArgumentException ("EXME_AreaTo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_AreaTo_ID, Integer.valueOf(EXME_AreaTo_ID));
	}

	/** Get To Service.
		@return To Service	  */
	public int getEXME_AreaTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AreaTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Require Authentication.
		@param RequireAuthentication 
		Indicates that this patient type change will require authentication
	  */
	public void setRequireAuthentication (boolean RequireAuthentication)
	{
		set_Value (COLUMNNAME_RequireAuthentication, Boolean.valueOf(RequireAuthentication));
	}

	/** Get Require Authentication.
		@return Indicates that this patient type change will require authentication
	  */
	public boolean isRequireAuthentication () 
	{
		Object oo = get_Value(COLUMNNAME_RequireAuthentication);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}