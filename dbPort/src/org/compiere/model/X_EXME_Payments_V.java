/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_Payments_V
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Payments_V extends PO implements I_EXME_Payments_V, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Payments_V (Properties ctx, int EXME_Payments_V_ID, String trxName)
    {
      super (ctx, EXME_Payments_V_ID, trxName);
      /** if (EXME_Payments_V_ID == 0)
        {
			setEXME_Payments_V_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_Payments_V (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Payments_V[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BatchNo.
		@param BatchNo BatchNo	  */
	public void setBatchNo (String BatchNo)
	{
		set_Value (COLUMNNAME_BatchNo, BatchNo);
	}

	/** Get BatchNo.
		@return BatchNo	  */
	public String getBatchNo () 
	{
		return (String)get_Value(COLUMNNAME_BatchNo);
	}

	/** Set Payments View Id.
		@param EXME_Payments_V_ID Payments View Id	  */
	public void setEXME_Payments_V_ID (int EXME_Payments_V_ID)
	{
		if (EXME_Payments_V_ID < 1)
			 throw new IllegalArgumentException ("EXME_Payments_V_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Payments_V_ID, Integer.valueOf(EXME_Payments_V_ID));
	}

	/** Get Payments View Id.
		@return Payments View Id	  */
	public int getEXME_Payments_V_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Payments_V_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
	}

	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Tot_Encounters.
		@param Tot_Encounters Tot_Encounters	  */
	public void setTot_Encounters (BigDecimal Tot_Encounters)
	{
		set_Value (COLUMNNAME_Tot_Encounters, Tot_Encounters);
	}

	/** Get Tot_Encounters.
		@return Tot_Encounters	  */
	public BigDecimal getTot_Encounters () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Tot_Encounters);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set TOT_Payment.
		@param TOT_Payment TOT_Payment	  */
	public void setTOT_Payment (BigDecimal TOT_Payment)
	{
		set_Value (COLUMNNAME_TOT_Payment, TOT_Payment);
	}

	/** Get TOT_Payment.
		@return TOT_Payment	  */
	public BigDecimal getTOT_Payment () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TOT_Payment);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}