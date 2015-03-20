/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_MO_PiezaDental
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_MO_PiezaDental extends PO implements I_EXME_MO_PiezaDental, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_MO_PiezaDental (Properties ctx, int EXME_MO_PiezaDental_ID, String trxName)
    {
      super (ctx, EXME_MO_PiezaDental_ID, trxName);
      /** if (EXME_MO_PiezaDental_ID == 0)
        {
			setEXME_MO_PiezaDental_ID (0);
			setName (null);
			setTipo_PiezaDental (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_MO_PiezaDental (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_MO_PiezaDental[")
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

	/** Set Dental Piece.
		@param EXME_MO_PiezaDental_ID Dental Piece	  */
	public void setEXME_MO_PiezaDental_ID (int EXME_MO_PiezaDental_ID)
	{
		if (EXME_MO_PiezaDental_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_PiezaDental_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_MO_PiezaDental_ID, Integer.valueOf(EXME_MO_PiezaDental_ID));
	}

	/** Get Dental Piece.
		@return Dental Piece	  */
	public int getEXME_MO_PiezaDental_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_PiezaDental_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Adult.
		@param IsAdulto Is Adult	  */
	public void setIsAdulto (boolean IsAdulto)
	{
		set_Value (COLUMNNAME_IsAdulto, Boolean.valueOf(IsAdulto));
	}

	/** Get Is Adult.
		@return Is Adult	  */
	public boolean isAdulto () 
	{
		Object oo = get_Value(COLUMNNAME_IsAdulto);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsLingual.
		@param IsLingual IsLingual	  */
	public void setIsLingual (boolean IsLingual)
	{
		set_Value (COLUMNNAME_IsLingual, Boolean.valueOf(IsLingual));
	}

	/** Get IsLingual.
		@return IsLingual	  */
	public boolean isLingual () 
	{
		Object oo = get_Value(COLUMNNAME_IsLingual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Adult Map.
		@param MapaAdulto Adult Map	  */
	public void setMapaAdulto (String MapaAdulto)
	{
		set_Value (COLUMNNAME_MapaAdulto, MapaAdulto);
	}

	/** Get Adult Map.
		@return Adult Map	  */
	public String getMapaAdulto () 
	{
		return (String)get_Value(COLUMNNAME_MapaAdulto);
	}

	/** Set Dental Infant Map.
		@param MapaInfantil Dental Infant Map	  */
	public void setMapaInfantil (String MapaInfantil)
	{
		set_Value (COLUMNNAME_MapaInfantil, MapaInfantil);
	}

	/** Get Dental Infant Map.
		@return Dental Infant Map	  */
	public String getMapaInfantil () 
	{
		return (String)get_Value(COLUMNNAME_MapaInfantil);
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

	/** Set Route.
		@param Ruta 
		Route of screen or process
	  */
	public void setRuta (String Ruta)
	{
		set_ValueNoCheck (COLUMNNAME_Ruta, Ruta);
	}

	/** Get Route.
		@return Route of screen or process
	  */
	public String getRuta () 
	{
		return (String)get_Value(COLUMNNAME_Ruta);
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (BigDecimal Secuencia)
	{
		set_Value (COLUMNNAME_Secuencia, Secuencia);
	}

	/** Get Sequence.
		@return Sequence
	  */
	public BigDecimal getSecuencia () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Secuencia);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Tipo_PiezaDental AD_Reference_ID=1200133 */
	public static final int TIPO_PIEZADENTAL_AD_Reference_ID=1200133;
	/** Incisivo Medial = INM */
	public static final String TIPO_PIEZADENTAL_IncisivoMedial = "INM";
	/** Incisivo Lateral = INL */
	public static final String TIPO_PIEZADENTAL_IncisivoLateral = "INL";
	/** Canino = CAN */
	public static final String TIPO_PIEZADENTAL_Canino = "CAN";
	/** 1er. Premolar = 1PRE */
	public static final String TIPO_PIEZADENTAL_1erPremolar = "1PRE";
	/** 2do. Premolar = 2PRE */
	public static final String TIPO_PIEZADENTAL_2doPremolar = "2PRE";
	/** 1er. Molar = 1MOL */
	public static final String TIPO_PIEZADENTAL_1erMolar = "1MOL";
	/** 2do. Molar = 2MOL */
	public static final String TIPO_PIEZADENTAL_2doMolar = "2MOL";
	/** 3er. Molar = 3MOL */
	public static final String TIPO_PIEZADENTAL_3erMolar = "3MOL";
	/** Set Dental Piece Type.
		@param Tipo_PiezaDental Dental Piece Type	  */
	public void setTipo_PiezaDental (String Tipo_PiezaDental)
	{
		if (Tipo_PiezaDental == null) throw new IllegalArgumentException ("Tipo_PiezaDental is mandatory");
		if (Tipo_PiezaDental.equals("INM") || Tipo_PiezaDental.equals("INL") || Tipo_PiezaDental.equals("CAN") || Tipo_PiezaDental.equals("1PRE") || Tipo_PiezaDental.equals("2PRE") || Tipo_PiezaDental.equals("1MOL") || Tipo_PiezaDental.equals("2MOL") || Tipo_PiezaDental.equals("3MOL")); else throw new IllegalArgumentException ("Tipo_PiezaDental Invalid value - " + Tipo_PiezaDental + " - Reference_ID=1200133 - INM - INL - CAN - 1PRE - 2PRE - 1MOL - 2MOL - 3MOL");		set_Value (COLUMNNAME_Tipo_PiezaDental, Tipo_PiezaDental);
	}

	/** Get Dental Piece Type.
		@return Dental Piece Type	  */
	public String getTipo_PiezaDental () 
	{
		return (String)get_Value(COLUMNNAME_Tipo_PiezaDental);
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