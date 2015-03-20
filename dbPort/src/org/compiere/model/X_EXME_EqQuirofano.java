/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_EqQuirofano
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EqQuirofano extends PO implements I_EXME_EqQuirofano, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EqQuirofano (Properties ctx, int EXME_EqQuirofano_ID, String trxName)
    {
      super (ctx, EXME_EqQuirofano_ID, trxName);
      /** if (EXME_EqQuirofano_ID == 0)
        {
			setCantidad (Env.ZERO);
// 1
			setEXME_EqQuirofano_ID (0);
			setEXME_Equipo_ID (0);
			setEXME_Quirofano_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_EqQuirofano (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EqQuirofano[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (BigDecimal Cantidad)
	{
		if (Cantidad == null)
			throw new IllegalArgumentException ("Cantidad is mandatory.");
		set_Value (COLUMNNAME_Cantidad, Cantidad);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getCantidad () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Cantidad);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Surgical Room- Equipment.
		@param EXME_EqQuirofano_ID 
		Surgical room - Equipment
	  */
	public void setEXME_EqQuirofano_ID (int EXME_EqQuirofano_ID)
	{
		if (EXME_EqQuirofano_ID < 1)
			 throw new IllegalArgumentException ("EXME_EqQuirofano_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EqQuirofano_ID, Integer.valueOf(EXME_EqQuirofano_ID));
	}

	/** Get Surgical Room- Equipment.
		@return Surgical room - Equipment
	  */
	public int getEXME_EqQuirofano_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EqQuirofano_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Surgery Room.
		@param EXME_Quirofano_ID 
		Surgey Room
	  */
	public void setEXME_Quirofano_ID (int EXME_Quirofano_ID)
	{
		if (EXME_Quirofano_ID < 1)
			 throw new IllegalArgumentException ("EXME_Quirofano_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Quirofano_ID, Integer.valueOf(EXME_Quirofano_ID));
	}

	/** Get Surgery Room.
		@return Surgey Room
	  */
	public int getEXME_Quirofano_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Quirofano_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}