/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_WarehouseRel
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_WarehouseRel extends PO implements I_EXME_WarehouseRel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_WarehouseRel (Properties ctx, int EXME_WarehouseRel_ID, String trxName)
    {
      super (ctx, EXME_WarehouseRel_ID, trxName);
      /** if (EXME_WarehouseRel_ID == 0)
        {
			setM_Warehouse_ID (0);
			setM_WarehouseRel_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_WarehouseRel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_WarehouseRel[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Warehouse Relation.
		@param M_WarehouseRel_ID 
		Warehouse Relation
	  */
	public void setM_WarehouseRel_ID (int M_WarehouseRel_ID)
	{
		if (M_WarehouseRel_ID < 1)
			 throw new IllegalArgumentException ("M_WarehouseRel_ID is mandatory.");
		set_Value (COLUMNNAME_M_WarehouseRel_ID, Integer.valueOf(M_WarehouseRel_ID));
	}

	/** Get Warehouse Relation.
		@return Warehouse Relation
	  */
	public int getM_WarehouseRel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_WarehouseRel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}