/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_Salarios_Min_Mes
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Salarios_Min_Mes extends PO implements I_EXME_Salarios_Min_Mes, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Salarios_Min_Mes (Properties ctx, int EXME_Salarios_Min_Mes_ID, String trxName)
    {
      super (ctx, EXME_Salarios_Min_Mes_ID, trxName);
      /** if (EXME_Salarios_Min_Mes_ID == 0)
        {
			setEXME_Salarios_Min_Mes_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Salarios_Min_Mes (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Salarios_Min_Mes[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Minimum Monthly Salaries.
		@param EXME_Salarios_Min_Mes_ID 
		Minimum Monthly Salaries
	  */
	public void setEXME_Salarios_Min_Mes_ID (int EXME_Salarios_Min_Mes_ID)
	{
		if (EXME_Salarios_Min_Mes_ID < 1)
			 throw new IllegalArgumentException ("EXME_Salarios_Min_Mes_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Salarios_Min_Mes_ID, Integer.valueOf(EXME_Salarios_Min_Mes_ID));
	}

	/** Get Minimum Monthly Salaries.
		@return Minimum Monthly Salaries
	  */
	public int getEXME_Salarios_Min_Mes_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Salarios_Min_Mes_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set No. Miembro Final.
		@param Num_Miem_Final 
		No. Miembro Final
	  */
	public void setNum_Miem_Final (int Num_Miem_Final)
	{
		set_Value (COLUMNNAME_Num_Miem_Final, Integer.valueOf(Num_Miem_Final));
	}

	/** Get No. Miembro Final.
		@return No. Miembro Final
	  */
	public int getNum_Miem_Final () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Num_Miem_Final);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set No. Miembro Inicial.
		@param Num_Miem_Inicial 
		No. Miembro Inicial
	  */
	public void setNum_Miem_Inicial (int Num_Miem_Inicial)
	{
		set_Value (COLUMNNAME_Num_Miem_Inicial, Integer.valueOf(Num_Miem_Inicial));
	}

	/** Get No. Miembro Inicial.
		@return No. Miembro Inicial
	  */
	public int getNum_Miem_Inicial () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Num_Miem_Inicial);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Score.
		@param Puntaje 
		Score
	  */
	public void setPuntaje (int Puntaje)
	{
		set_Value (COLUMNNAME_Puntaje, Integer.valueOf(Puntaje));
	}

	/** Get Score.
		@return Score
	  */
	public int getPuntaje () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Puntaje);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Final Minimum Wage.
		@param Sal_Min_Final 
		Final Minimum Wage
	  */
	public void setSal_Min_Final (BigDecimal Sal_Min_Final)
	{
		set_Value (COLUMNNAME_Sal_Min_Final, Sal_Min_Final);
	}

	/** Get Final Minimum Wage.
		@return Final Minimum Wage
	  */
	public BigDecimal getSal_Min_Final () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sal_Min_Final);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Initial Minimum Wage.
		@param Sal_Min_Inicial 
		Initial Minimum Wage
	  */
	public void setSal_Min_Inicial (BigDecimal Sal_Min_Inicial)
	{
		set_Value (COLUMNNAME_Sal_Min_Inicial, Sal_Min_Inicial);
	}

	/** Get Initial Minimum Wage.
		@return Initial Minimum Wage
	  */
	public BigDecimal getSal_Min_Inicial () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Sal_Min_Inicial);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}