/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_FactorPreDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_FactorPreDet extends PO implements I_EXME_FactorPreDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FactorPreDet (Properties ctx, int EXME_FactorPreDet_ID, String trxName)
    {
      super (ctx, EXME_FactorPreDet_ID, trxName);
      /** if (EXME_FactorPreDet_ID == 0)
        {
			setEXME_FactorPreDet_ID (0);
			setEXME_FactorPre_ID (0);
			setLinea (0);
			setNivelSuperior (Env.ZERO);
			setPorcentaje (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_EXME_FactorPreDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FactorPreDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Sales Price Factor ( Detail ).
		@param EXME_FactorPreDet_ID 
		Sales price factor  ( Detail ) 
	  */
	public void setEXME_FactorPreDet_ID (int EXME_FactorPreDet_ID)
	{
		if (EXME_FactorPreDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_FactorPreDet_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_FactorPreDet_ID, Integer.valueOf(EXME_FactorPreDet_ID));
	}

	/** Get Sales Price Factor ( Detail ).
		@return Sales price factor  ( Detail ) 
	  */
	public int getEXME_FactorPreDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FactorPreDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price Factor.
		@param EXME_FactorPre_ID 
		Sales Price Factor
	  */
	public void setEXME_FactorPre_ID (int EXME_FactorPre_ID)
	{
		if (EXME_FactorPre_ID < 1)
			 throw new IllegalArgumentException ("EXME_FactorPre_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FactorPre_ID, Integer.valueOf(EXME_FactorPre_ID));
	}

	/** Get Price Factor.
		@return Sales Price Factor
	  */
	public int getEXME_FactorPre_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FactorPre_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line.
		@param Linea 
		Line
	  */
	public void setLinea (int Linea)
	{
		set_Value (COLUMNNAME_Linea, Integer.valueOf(Linea));
	}

	/** Get Line.
		@return Line
	  */
	public int getLinea () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Linea);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Superior Level.
		@param NivelSuperior 
		Superior Level
	  */
	public void setNivelSuperior (BigDecimal NivelSuperior)
	{
		if (NivelSuperior == null)
			throw new IllegalArgumentException ("NivelSuperior is mandatory.");
		set_Value (COLUMNNAME_NivelSuperior, NivelSuperior);
	}

	/** Get Superior Level.
		@return Superior Level
	  */
	public BigDecimal getNivelSuperior () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NivelSuperior);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Percentage.
		@param Porcentaje 
		percentage
	  */
	public void setPorcentaje (BigDecimal Porcentaje)
	{
		if (Porcentaje == null)
			throw new IllegalArgumentException ("Porcentaje is mandatory.");
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
}