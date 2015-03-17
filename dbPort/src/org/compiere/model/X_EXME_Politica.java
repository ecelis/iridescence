/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_Politica
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Politica extends PO implements I_EXME_Politica, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Politica (Properties ctx, int EXME_Politica_ID, String trxName)
    {
      super (ctx, EXME_Politica_ID, trxName);
      /** if (EXME_Politica_ID == 0)
        {
			setEXME_Politica_ID (0);
			setWhsSolicita (0);
			setWhsSurte (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Politica (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Politica[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Policy.
		@param EXME_Politica_ID 
		Policy
	  */
	public void setEXME_Politica_ID (int EXME_Politica_ID)
	{
		if (EXME_Politica_ID < 1)
			 throw new IllegalArgumentException ("EXME_Politica_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Politica_ID, Integer.valueOf(EXME_Politica_ID));
	}

	/** Get Policy.
		@return Policy
	  */
	public int getEXME_Politica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Politica_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Percentage.
		@param Porcentaje 
		percentage
	  */
	public void setPorcentaje (BigDecimal Porcentaje)
	{
		set_Value (COLUMNNAME_Porcentaje, Porcentaje);
	}

	/** Get Percentage.
		@return percentage
	  */
	public BigDecimal getPorcentaje () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Porcentaje);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Requesting warehouse.
		@param WhsSolicita 
		Requesting warehouse
	  */
	public void setWhsSolicita (int WhsSolicita)
	{
		set_Value (COLUMNNAME_WhsSolicita, Integer.valueOf(WhsSolicita));
	}

	/** Get Requesting warehouse.
		@return Requesting warehouse
	  */
	public int getWhsSolicita () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WhsSolicita);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Deliver Warehouse.
		@param WhsSurte 
		Deliver warehouse
	  */
	public void setWhsSurte (int WhsSurte)
	{
		set_Value (COLUMNNAME_WhsSurte, Integer.valueOf(WhsSurte));
	}

	/** Get Deliver Warehouse.
		@return Deliver warehouse
	  */
	public int getWhsSurte () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WhsSurte);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}