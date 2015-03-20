/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_PagoGen
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PagoGen extends PO implements I_EXME_PagoGen, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PagoGen (Properties ctx, int EXME_PagoGen_ID, String trxName)
    {
      super (ctx, EXME_PagoGen_ID, trxName);
      /** if (EXME_PagoGen_ID == 0)
        {
			setActGeneral (null);
			setAnticipo (null);
			setDevolucion (null);
			setEfectivo (null);
			setEXME_PagoGen_ID (0);
			setFechaLimite (new Timestamp( System.currentTimeMillis() ));
			setPago (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PagoGen (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PagoGen[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set General Activity.
		@param ActGeneral General Activity	  */
	public void setActGeneral (String ActGeneral)
	{
		if (ActGeneral == null)
			throw new IllegalArgumentException ("ActGeneral is mandatory.");
		set_Value (COLUMNNAME_ActGeneral, ActGeneral);
	}

	/** Get General Activity.
		@return General Activity	  */
	public String getActGeneral () 
	{
		return (String)get_Value(COLUMNNAME_ActGeneral);
	}

	/** Set Advance payment.
		@param Anticipo Advance payment	  */
	public void setAnticipo (String Anticipo)
	{
		if (Anticipo == null)
			throw new IllegalArgumentException ("Anticipo is mandatory.");
		set_Value (COLUMNNAME_Anticipo, Anticipo);
	}

	/** Get Advance payment.
		@return Advance payment	  */
	public String getAnticipo () 
	{
		return (String)get_Value(COLUMNNAME_Anticipo);
	}

	/** Set Return.
		@param Devolucion Return	  */
	public void setDevolucion (String Devolucion)
	{
		if (Devolucion == null)
			throw new IllegalArgumentException ("Devolucion is mandatory.");
		set_Value (COLUMNNAME_Devolucion, Devolucion);
	}

	/** Get Return.
		@return Return	  */
	public String getDevolucion () 
	{
		return (String)get_Value(COLUMNNAME_Devolucion);
	}

	/** Set Cash.
		@param Efectivo Cash	  */
	public void setEfectivo (String Efectivo)
	{
		if (Efectivo == null)
			throw new IllegalArgumentException ("Efectivo is mandatory.");
		set_Value (COLUMNNAME_Efectivo, Efectivo);
	}

	/** Get Cash.
		@return Cash	  */
	public String getEfectivo () 
	{
		return (String)get_Value(COLUMNNAME_Efectivo);
	}

	/** Set Generic payments .
		@param EXME_PagoGen_ID Generic payments 	  */
	public void setEXME_PagoGen_ID (int EXME_PagoGen_ID)
	{
		if (EXME_PagoGen_ID < 1)
			 throw new IllegalArgumentException ("EXME_PagoGen_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PagoGen_ID, Integer.valueOf(EXME_PagoGen_ID));
	}

	/** Get Generic payments .
		@return Generic payments 	  */
	public int getEXME_PagoGen_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PagoGen_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date limits .
		@param FechaLimite Date limits 	  */
	public void setFechaLimite (Timestamp FechaLimite)
	{
		if (FechaLimite == null)
			throw new IllegalArgumentException ("FechaLimite is mandatory.");
		set_Value (COLUMNNAME_FechaLimite, FechaLimite);
	}

	/** Get Date limits .
		@return Date limits 	  */
	public Timestamp getFechaLimite () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaLimite);
	}

	/** Set Payment.
		@param Pago Payment	  */
	public void setPago (String Pago)
	{
		if (Pago == null)
			throw new IllegalArgumentException ("Pago is mandatory.");
		set_Value (COLUMNNAME_Pago, Pago);
	}

	/** Get Payment.
		@return Payment	  */
	public String getPago () 
	{
		return (String)get_Value(COLUMNNAME_Pago);
	}
}