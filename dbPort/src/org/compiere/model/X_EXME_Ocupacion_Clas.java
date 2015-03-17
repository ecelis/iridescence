/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for EXME_Ocupacion_Clas
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Ocupacion_Clas extends PO implements I_EXME_Ocupacion_Clas, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Ocupacion_Clas (Properties ctx, int EXME_Ocupacion_Clas_ID, String trxName)
    {
      super (ctx, EXME_Ocupacion_Clas_ID, trxName);
      /** if (EXME_Ocupacion_Clas_ID == 0)
        {
			setEXME_Ocupacion_Clas_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Ocupacion_Clas (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Ocupacion_Clas[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Classified Ocupation.
		@param EXME_Ocupacion_Clas_ID 
		Classified Ocupation
	  */
	public void setEXME_Ocupacion_Clas_ID (int EXME_Ocupacion_Clas_ID)
	{
		if (EXME_Ocupacion_Clas_ID < 1)
			 throw new IllegalArgumentException ("EXME_Ocupacion_Clas_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Ocupacion_Clas_ID, Integer.valueOf(EXME_Ocupacion_Clas_ID));
	}

	/** Get Classified Ocupation.
		@return Classified Ocupation
	  */
	public int getEXME_Ocupacion_Clas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Ocupacion_Clas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		if (Value == null)
			throw new IllegalArgumentException ("Value is mandatory.");
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}