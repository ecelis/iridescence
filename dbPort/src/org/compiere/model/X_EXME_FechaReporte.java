/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_FechaReporte
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_FechaReporte extends PO implements I_EXME_FechaReporte, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_FechaReporte (Properties ctx, int EXME_FechaReporte_ID, String trxName)
    {
      super (ctx, EXME_FechaReporte_ID, trxName);
      /** if (EXME_FechaReporte_ID == 0)
        {
			setEXME_FechaReporte_ID (0);
			setEXME_Paciente_ID (0);
			setFechaReporte (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_EXME_FechaReporte (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_FechaReporte[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Report Dates for Transplant.
		@param EXME_FechaReporte_ID 
		Report Dates for Transplant
	  */
	public void setEXME_FechaReporte_ID (int EXME_FechaReporte_ID)
	{
		if (EXME_FechaReporte_ID < 1)
			 throw new IllegalArgumentException ("EXME_FechaReporte_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_FechaReporte_ID, Integer.valueOf(EXME_FechaReporte_ID));
	}

	/** Get Report Dates for Transplant.
		@return Report Dates for Transplant
	  */
	public int getEXME_FechaReporte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_FechaReporte_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Report Date.
		@param FechaReporte 
		Report Date
	  */
	public void setFechaReporte (Timestamp FechaReporte)
	{
		if (FechaReporte == null)
			throw new IllegalArgumentException ("FechaReporte is mandatory.");
		set_Value (COLUMNNAME_FechaReporte, FechaReporte);
	}

	/** Get Report Date.
		@return Report Date
	  */
	public Timestamp getFechaReporte () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaReporte);
	}
}