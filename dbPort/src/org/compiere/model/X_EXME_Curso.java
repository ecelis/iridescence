/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Curso
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Curso extends PO implements I_EXME_Curso, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Curso (Properties ctx, int EXME_Curso_ID, String trxName)
    {
      super (ctx, EXME_Curso_ID, trxName);
      /** if (EXME_Curso_ID == 0)
        {
			setEXME_Curso_ID (0);
			setName (null);
			setTipo_Curso (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Curso (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Curso[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Course.
		@param EXME_Curso_ID 
		Course
	  */
	public void setEXME_Curso_ID (int EXME_Curso_ID)
	{
		if (EXME_Curso_ID < 1)
			 throw new IllegalArgumentException ("EXME_Curso_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Curso_ID, Integer.valueOf(EXME_Curso_ID));
	}

	/** Get Course.
		@return Course
	  */
	public int getEXME_Curso_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Curso_ID);
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

	/** Set Place.
		@param Lugar 
		Place
	  */
	public void setLugar (String Lugar)
	{
		set_Value (COLUMNNAME_Lugar, Lugar);
	}

	/** Get Place.
		@return Place
	  */
	public String getLugar () 
	{
		return (String)get_Value(COLUMNNAME_Lugar);
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

	/** Set Operations Centers.
		@param Plantel Operations Centers	  */
	public void setPlantel (String Plantel)
	{
		set_Value (COLUMNNAME_Plantel, Plantel);
	}

	/** Get Operations Centers.
		@return Operations Centers	  */
	public String getPlantel () 
	{
		return (String)get_Value(COLUMNNAME_Plantel);
	}

	/** Tipo_Curso AD_Reference_ID=1200143 */
	public static final int TIPO_CURSO_AD_Reference_ID=1200143;
	/** Military = M */
	public static final String TIPO_CURSO_Military = "M";
	/** Civil = C */
	public static final String TIPO_CURSO_Civil = "C";
	/** Set Course Type.
		@param Tipo_Curso 
		Course Type
	  */
	public void setTipo_Curso (String Tipo_Curso)
	{
		if (Tipo_Curso == null) throw new IllegalArgumentException ("Tipo_Curso is mandatory");
		if (Tipo_Curso.equals("M") || Tipo_Curso.equals("C")); else throw new IllegalArgumentException ("Tipo_Curso Invalid value - " + Tipo_Curso + " - Reference_ID=1200143 - M - C");		set_Value (COLUMNNAME_Tipo_Curso, Tipo_Curso);
	}

	/** Get Course Type.
		@return Course Type
	  */
	public String getTipo_Curso () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_Curso);
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