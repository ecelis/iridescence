/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_Rango_Porc_Alim
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Rango_Porc_Alim extends PO implements I_EXME_Rango_Porc_Alim, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Rango_Porc_Alim (Properties ctx, int EXME_Rango_Porc_Alim_ID, String trxName)
    {
      super (ctx, EXME_Rango_Porc_Alim_ID, trxName);
      /** if (EXME_Rango_Porc_Alim_ID == 0)
        {
			setEXME_Rango_Porc_Alim_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Rango_Porc_Alim (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Rango_Porc_Alim[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Feeding and score percentage rank.
		@param EXME_Rango_Porc_Alim_ID 
		Feeding and score percentage rank
	  */
	public void setEXME_Rango_Porc_Alim_ID (int EXME_Rango_Porc_Alim_ID)
	{
		if (EXME_Rango_Porc_Alim_ID < 1)
			 throw new IllegalArgumentException ("EXME_Rango_Porc_Alim_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Rango_Porc_Alim_ID, Integer.valueOf(EXME_Rango_Porc_Alim_ID));
	}

	/** Get Feeding and score percentage rank.
		@return Feeding and score percentage rank
	  */
	public int getEXME_Rango_Porc_Alim_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Rango_Porc_Alim_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Percentage.
		@param Porcentaje_Final 
		Final Percentage
	  */
	public void setPorcentaje_Final (BigDecimal Porcentaje_Final)
	{
		set_Value (COLUMNNAME_Porcentaje_Final, Porcentaje_Final);
	}

	/** Get Final Percentage.
		@return Final Percentage
	  */
	public BigDecimal getPorcentaje_Final () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Porcentaje_Final);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Percentage Initial.
		@param Porcentaje_Inicial 
		Percentage Initial
	  */
	public void setPorcentaje_Inicial (BigDecimal Porcentaje_Inicial)
	{
		set_Value (COLUMNNAME_Porcentaje_Inicial, Porcentaje_Inicial);
	}

	/** Get Percentage Initial.
		@return Percentage Initial
	  */
	public BigDecimal getPorcentaje_Inicial () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Porcentaje_Inicial);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Score.
		@param Puntaje 
		Score
	  */
	public void setPuntaje (BigDecimal Puntaje)
	{
		set_Value (COLUMNNAME_Puntaje, Puntaje);
	}

	/** Get Score.
		@return Score
	  */
	public BigDecimal getPuntaje () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Puntaje);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}