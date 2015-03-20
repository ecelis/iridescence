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
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Colacion
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Colacion extends PO implements I_EXME_Colacion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Colacion (Properties ctx, int EXME_Colacion_ID, String trxName)
    {
      super (ctx, EXME_Colacion_ID, trxName);
      /** if (EXME_Colacion_ID == 0)
        {
			setCalorias (Env.ZERO);
			setDescription (null);
			setEXME_Colacion_ID (0);
			setEXME_TipoColacion (null);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Colacion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Colacion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Calories.
		@param Calorias Calories	  */
	public void setCalorias (BigDecimal Calorias)
	{
		if (Calorias == null)
			throw new IllegalArgumentException ("Calorias is mandatory.");
		set_Value (COLUMNNAME_Calorias, Calorias);
	}

	/** Get Calories.
		@return Calories	  */
	public BigDecimal getCalorias () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Calorias);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Snack.
		@param EXME_Colacion_ID Snack	  */
	public void setEXME_Colacion_ID (int EXME_Colacion_ID)
	{
		if (EXME_Colacion_ID < 1)
			 throw new IllegalArgumentException ("EXME_Colacion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Colacion_ID, Integer.valueOf(EXME_Colacion_ID));
	}

	/** Get Snack.
		@return Snack	  */
	public int getEXME_Colacion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Colacion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Firts Diet.
		@param EXME_Dieta1_ID Firts Diet	  */
	public void setEXME_Dieta1_ID (int EXME_Dieta1_ID)
	{
		if (EXME_Dieta1_ID < 1) 
			set_Value (COLUMNNAME_EXME_Dieta1_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Dieta1_ID, Integer.valueOf(EXME_Dieta1_ID));
	}

	/** Get Firts Diet.
		@return Firts Diet	  */
	public int getEXME_Dieta1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dieta1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Second Diet.
		@param EXME_Dieta2_ID Second Diet	  */
	public void setEXME_Dieta2_ID (int EXME_Dieta2_ID)
	{
		if (EXME_Dieta2_ID < 1) 
			set_Value (COLUMNNAME_EXME_Dieta2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Dieta2_ID, Integer.valueOf(EXME_Dieta2_ID));
	}

	/** Get Second Diet.
		@return Second Diet	  */
	public int getEXME_Dieta2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dieta2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Third Diet.
		@param EXME_Dieta3_ID Third Diet	  */
	public void setEXME_Dieta3_ID (int EXME_Dieta3_ID)
	{
		if (EXME_Dieta3_ID < 1) 
			set_Value (COLUMNNAME_EXME_Dieta3_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Dieta3_ID, Integer.valueOf(EXME_Dieta3_ID));
	}

	/** Get Third Diet.
		@return Third Diet	  */
	public int getEXME_Dieta3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dieta3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fourth Diet.
		@param EXME_Dieta4_ID Fourth Diet	  */
	public void setEXME_Dieta4_ID (int EXME_Dieta4_ID)
	{
		if (EXME_Dieta4_ID < 1) 
			set_Value (COLUMNNAME_EXME_Dieta4_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Dieta4_ID, Integer.valueOf(EXME_Dieta4_ID));
	}

	/** Get Fourth Diet.
		@return Fourth Diet	  */
	public int getEXME_Dieta4_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Dieta4_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** EXME_TipoColacion AD_Reference_ID=1200149 */
	public static final int EXME_TIPOCOLACION_AD_Reference_ID=1200149;
	/** Morning shift = M */
	public static final String EXME_TIPOCOLACION_MorningShift = "M";
	/** Swing Shift = V */
	public static final String EXME_TIPOCOLACION_SwingShift = "V";
	/** Ninght Shift = N */
	public static final String EXME_TIPOCOLACION_NinghtShift = "N";
	/** Set Snack Type.
		@param EXME_TipoColacion Snack Type	  */
	public void setEXME_TipoColacion (String EXME_TipoColacion)
	{
		if (EXME_TipoColacion == null) throw new IllegalArgumentException ("EXME_TipoColacion is mandatory");
		if (EXME_TipoColacion.equals("M") || EXME_TipoColacion.equals("V") || EXME_TipoColacion.equals("N")); else throw new IllegalArgumentException ("EXME_TipoColacion Invalid value - " + EXME_TipoColacion + " - Reference_ID=1200149 - M - V - N");		set_Value (COLUMNNAME_EXME_TipoColacion, EXME_TipoColacion);
	}

	/** Get Snack Type.
		@return Snack Type	  */
	public String getEXME_TipoColacion () 
	{
		return (String)get_Value(COLUMNNAME_EXME_TipoColacion);
	}

	/** Set Date.
		@param Fecha 
		Date
	  */
	public void setFecha (Timestamp Fecha)
	{
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
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