/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_EquipoServ
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EquipoServ extends PO implements I_EXME_EquipoServ, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EquipoServ (Properties ctx, int EXME_EquipoServ_ID, String trxName)
    {
      super (ctx, EXME_EquipoServ_ID, trxName);
      /** if (EXME_EquipoServ_ID == 0)
        {
			setEXME_Equipo_ID (0);
			setEXME_EquipoServ_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EquipoServ (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EquipoServ[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Equipment.
		@param EXME_Equipo_ID 
		Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID)
	{
		if (EXME_Equipo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Equipo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Equipo_ID, Integer.valueOf(EXME_Equipo_ID));
	}

	/** Get Equipment.
		@return Equipment
	  */
	public int getEXME_Equipo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Equipo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Service Equipment.
		@param EXME_EquipoServ_ID Service Equipment	  */
	public void setEXME_EquipoServ_ID (int EXME_EquipoServ_ID)
	{
		if (EXME_EquipoServ_ID < 1)
			 throw new IllegalArgumentException ("EXME_EquipoServ_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EquipoServ_ID, Integer.valueOf(EXME_EquipoServ_ID));
	}

	/** Get Service Equipment.
		@return Service Equipment	  */
	public int getEXME_EquipoServ_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EquipoServ_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}