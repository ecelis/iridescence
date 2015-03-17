/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Periodo
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Periodo extends PO implements I_EXME_Periodo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Periodo (Properties ctx, int EXME_Periodo_ID, String trxName)
    {
      super (ctx, EXME_Periodo_ID, trxName);
      /** if (EXME_Periodo_ID == 0)
        {
			setEXME_Periodo_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Periodo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Periodo[")
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

	/** Set IsActual.
		@param EsActual IsActual	  */
	public void setEsActual (boolean EsActual)
	{
		set_Value (COLUMNNAME_EsActual, Boolean.valueOf(EsActual));
	}

	/** Get IsActual.
		@return IsActual	  */
	public boolean isEsActual () 
	{
		Object oo = get_Value(COLUMNNAME_EsActual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsClosed.
		@param EsCerrada IsClosed	  */
	public void setEsCerrada (boolean EsCerrada)
	{
		set_Value (COLUMNNAME_EsCerrada, Boolean.valueOf(EsCerrada));
	}

	/** Get IsClosed.
		@return IsClosed	  */
	public boolean isEsCerrada () 
	{
		Object oo = get_Value(COLUMNNAME_EsCerrada);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsOrdinary.
		@param EsOrdinaria IsOrdinary	  */
	public void setEsOrdinaria (boolean EsOrdinaria)
	{
		set_Value (COLUMNNAME_EsOrdinaria, Boolean.valueOf(EsOrdinaria));
	}

	/** Get IsOrdinary.
		@return IsOrdinary	  */
	public boolean isEsOrdinaria () 
	{
		Object oo = get_Value(COLUMNNAME_EsOrdinaria);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Period.
		@param EXME_Periodo_ID Period	  */
	public void setEXME_Periodo_ID (int EXME_Periodo_ID)
	{
		if (EXME_Periodo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Periodo_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Periodo_ID, Integer.valueOf(EXME_Periodo_ID));
	}

	/** Get Period.
		@return Period	  */
	public int getEXME_Periodo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Periodo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Closing Date.
		@param FechaCierre 
		Date of Intervention Closing
	  */
	public void setFechaCierre (Timestamp FechaCierre)
	{
		set_Value (COLUMNNAME_FechaCierre, FechaCierre);
	}

	/** Get Closing Date.
		@return Date of Intervention Closing
	  */
	public Timestamp getFechaCierre () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaCierre);
	}

	/** Set Ending Date.
		@param Fecha_Fin 
		Date of ending of intervention
	  */
	public void setFecha_Fin (Timestamp Fecha_Fin)
	{
		set_Value (COLUMNNAME_Fecha_Fin, Fecha_Fin);
	}

	/** Get Ending Date.
		@return Date of ending of intervention
	  */
	public Timestamp getFecha_Fin () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Fin);
	}

	/** Set Initial Date.
		@param Fecha_Ini 
		Initial Date
	  */
	public void setFecha_Ini (Timestamp Fecha_Ini)
	{
		set_Value (COLUMNNAME_Fecha_Ini, Fecha_Ini);
	}

	/** Get Initial Date.
		@return Initial Date
	  */
	public Timestamp getFecha_Ini () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Ini);
	}

	/** Set Paiment Date.
		@param Fecha_Pago Paiment Date	  */
	public void setFecha_Pago (Timestamp Fecha_Pago)
	{
		set_Value (COLUMNNAME_Fecha_Pago, Fecha_Pago);
	}

	/** Get Paiment Date.
		@return Paiment Date	  */
	public Timestamp getFecha_Pago () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Pago);
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

	/** Set Month Number.
		@param No_Mes Month Number	  */
	public void setNo_Mes (int No_Mes)
	{
		set_Value (COLUMNNAME_No_Mes, Integer.valueOf(No_Mes));
	}

	/** Get Month Number.
		@return Month Number	  */
	public int getNo_Mes () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_No_Mes);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Period Number.
		@param No_Periodo Period Number	  */
	public void setNo_Periodo (int No_Periodo)
	{
		set_Value (COLUMNNAME_No_Periodo, Integer.valueOf(No_Periodo));
	}

	/** Get Period Number.
		@return Period Number	  */
	public int getNo_Periodo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_No_Periodo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fortnight Number.
		@param No_Quincena Fortnight Number	  */
	public void setNo_Quincena (int No_Quincena)
	{
		set_Value (COLUMNNAME_No_Quincena, Integer.valueOf(No_Quincena));
	}

	/** Get Fortnight Number.
		@return Fortnight Number	  */
	public int getNo_Quincena () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_No_Quincena);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Week Number.
		@param No_Semana Week Number	  */
	public void setNo_Semana (int No_Semana)
	{
		set_Value (COLUMNNAME_No_Semana, Integer.valueOf(No_Semana));
	}

	/** Get Week Number.
		@return Week Number	  */
	public int getNo_Semana () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_No_Semana);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type Paiment.
		@param TipoNomina Type Paiment	  */
	public void setTipoNomina (boolean TipoNomina)
	{
		set_Value (COLUMNNAME_TipoNomina, Boolean.valueOf(TipoNomina));
	}

	/** Get Type Paiment.
		@return Type Paiment	  */
	public boolean isTipoNomina () 
	{
		Object oo = get_Value(COLUMNNAME_TipoNomina);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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