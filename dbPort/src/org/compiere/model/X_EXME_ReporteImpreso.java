/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for EXME_ReporteImpreso
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ReporteImpreso extends PO implements I_EXME_ReporteImpreso, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ReporteImpreso (Properties ctx, int EXME_ReporteImpreso_ID, String trxName)
    {
      super (ctx, EXME_ReporteImpreso_ID, trxName);
      /** if (EXME_ReporteImpreso_ID == 0)
        {
			setEXME_ReporteImpreso_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ReporteImpreso (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ReporteImpreso[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Printed Report.
		@param EXME_ReporteImpreso_ID Printed Report	  */
	public void setEXME_ReporteImpreso_ID (int EXME_ReporteImpreso_ID)
	{
		if (EXME_ReporteImpreso_ID < 1)
			 throw new IllegalArgumentException ("EXME_ReporteImpreso_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ReporteImpreso_ID, Integer.valueOf(EXME_ReporteImpreso_ID));
	}

	/** Get Printed Report.
		@return Printed Report	  */
	public int getEXME_ReporteImpreso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ReporteImpreso_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Parameters.
		@param Params Parameters	  */
	public void setParams (String Params)
	{
		set_Value (COLUMNNAME_Params, Params);
	}

	/** Get Parameters.
		@return Parameters	  */
	public String getParams () 
	{
		return (String)get_Value(COLUMNNAME_Params);
	}

	/** Set Sql.
		@param Sql Sql	  */
	public void setSql (String Sql)
	{
		set_Value (COLUMNNAME_Sql, Sql);
	}

	/** Get Sql.
		@return Sql	  */
	public String getSql () 
	{
		return (String)get_Value(COLUMNNAME_Sql);
	}
}