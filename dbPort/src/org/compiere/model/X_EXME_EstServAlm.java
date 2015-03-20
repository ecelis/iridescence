/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_EstServAlm
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EstServAlm extends PO implements I_EXME_EstServAlm, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EstServAlm (Properties ctx, int EXME_EstServAlm_ID, String trxName)
    {
      super (ctx, EXME_EstServAlm_ID, trxName);
      /** if (EXME_EstServAlm_ID == 0)
        {
			setEXME_EstServAlm_ID (0);
			setEXME_EstServ_ID (0);
			setIsDefault (false);
// N
			setM_Warehouse_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EstServAlm (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_EXME_EstServAlm[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Service Station - Warehouse.
		@param EXME_EstServAlm_ID 
		Links Service Station -Warehouse
	  */
	public void setEXME_EstServAlm_ID (int EXME_EstServAlm_ID)
	{
		if (EXME_EstServAlm_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServAlm_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EstServAlm_ID, Integer.valueOf(EXME_EstServAlm_ID));
	}

	/** Get Service Station - Warehouse.
		@return Links Service Station -Warehouse
	  */
	public int getEXME_EstServAlm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServAlm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service Station.
		@param EXME_EstServ_ID 
		Service Station
	  */
	public void setEXME_EstServ_ID (int EXME_EstServ_ID)
	{
		if (EXME_EstServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_EstServ_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EstServ_ID, Integer.valueOf(EXME_EstServ_ID));
	}

	/** Get Service Station.
		@return Service Station
	  */
	public int getEXME_EstServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EstServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
		set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}