/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_AgrupacionSol
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_AgrupacionSol extends PO implements I_EXME_AgrupacionSol, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_AgrupacionSol (Properties ctx, int EXME_AgrupacionSol_ID, String trxName)
    {
      super (ctx, EXME_AgrupacionSol_ID, trxName);
      /** if (EXME_AgrupacionSol_ID == 0)
        {
			setEXME_AgrupacionSol_ID (0);
			setEXME_TipoProd_ID (0);
			setM_Warehouse_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_AgrupacionSol (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_AgrupacionSol[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Grouping of Product Requests.
		@param EXME_AgrupacionSol_ID 
		Grouping of Product Requests
	  */
	public void setEXME_AgrupacionSol_ID (int EXME_AgrupacionSol_ID)
	{
		if (EXME_AgrupacionSol_ID < 1)
			 throw new IllegalArgumentException ("EXME_AgrupacionSol_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_AgrupacionSol_ID, Integer.valueOf(EXME_AgrupacionSol_ID));
	}

	/** Get Grouping of Product Requests.
		@return Grouping of Product Requests
	  */
	public int getEXME_AgrupacionSol_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_AgrupacionSol_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product Subtype.
		@param EXME_TipoProd_ID 
		Product Subtype for hospitals
	  */
	public void setEXME_TipoProd_ID (int EXME_TipoProd_ID)
	{
		if (EXME_TipoProd_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoProd_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoProd_ID, Integer.valueOf(EXME_TipoProd_ID));
	}

	/** Get Product Subtype.
		@return Product Subtype for hospitals
	  */
	public int getEXME_TipoProd_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoProd_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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