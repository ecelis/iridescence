/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Equipo_Falla
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Equipo_Falla extends PO implements I_EXME_Equipo_Falla, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Equipo_Falla (Properties ctx, int EXME_Equipo_Falla_ID, String trxName)
    {
      super (ctx, EXME_Equipo_Falla_ID, trxName);
      /** if (EXME_Equipo_Falla_ID == 0)
        {
			setEstatus (null);
			setEXME_Equipo_Falla_ID (0);
			setFechaReporte (new Timestamp( System.currentTimeMillis() ));
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Equipo_Falla (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Equipo_Falla[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Notes.
		@param Comentarios Notes	  */
	public void setComentarios (String Comentarios)
	{
		set_Value (COLUMNNAME_Comentarios, Comentarios);
	}

	/** Get Notes.
		@return Notes	  */
	public String getComentarios () 
	{
		return (String)get_Value(COLUMNNAME_Comentarios);
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

	/** Estatus AD_Reference_ID=1200201 */
	public static final int ESTATUS_AD_Reference_ID=1200201;
	/** Reported = R */
	public static final String ESTATUS_Reported = "R";
	/** Assigned = A */
	public static final String ESTATUS_Assigned = "A";
	/** In Process = P */
	public static final String ESTATUS_InProcess = "P";
	/** Completed = T */
	public static final String ESTATUS_Completed = "T";
	/** Set Status.
		@param Estatus 
		Status
	  */
	public void setEstatus (String Estatus)
	{
		if (Estatus == null) throw new IllegalArgumentException ("Estatus is mandatory");
		if (Estatus.equals("R") || Estatus.equals("A") || Estatus.equals("P") || Estatus.equals("T")); else throw new IllegalArgumentException ("Estatus Invalid value - " + Estatus + " - Reference_ID=1200201 - R - A - P - T");		set_Value (COLUMNNAME_Estatus, Estatus);
	}

	/** Get Status.
		@return Status
	  */
	public String getEstatus () 
	{
		return (String)get_Value(COLUMNNAME_Estatus);
	}

	/** Set Equipment Failure.
		@param EXME_Equipo_Falla_ID Equipment Failure	  */
	public void setEXME_Equipo_Falla_ID (int EXME_Equipo_Falla_ID)
	{
		if (EXME_Equipo_Falla_ID < 1)
			 throw new IllegalArgumentException ("EXME_Equipo_Falla_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Equipo_Falla_ID, Integer.valueOf(EXME_Equipo_Falla_ID));
	}

	/** Get Equipment Failure.
		@return Equipment Failure	  */
	public int getEXME_Equipo_Falla_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Equipo_Falla_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Equipment.
		@param EXME_Equipo_ID 
		Equipment
	  */
	public void setEXME_Equipo_ID (int EXME_Equipo_ID)
	{
		if (EXME_Equipo_ID < 1) 
			set_Value (COLUMNNAME_EXME_Equipo_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Equipo_ID, Integer.valueOf(EXME_Equipo_ID));
	}

	/** Get Equipment.
		@return Equipment
	  */
	public int getEXME_Equipo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Equipo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warning Date.
		@param FechaAtencion Warning Date	  */
	public void setFechaAtencion (Timestamp FechaAtencion)
	{
		set_Value (COLUMNNAME_FechaAtencion, FechaAtencion);
	}

	/** Get Warning Date.
		@return Warning Date	  */
	public Timestamp getFechaAtencion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAtencion);
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

	/** Set Settlement Date.
		@param FechaSolucion Settlement Date	  */
	public void setFechaSolucion (Timestamp FechaSolucion)
	{
		set_Value (COLUMNNAME_FechaSolucion, FechaSolucion);
	}

	/** Get Settlement Date.
		@return Settlement Date	  */
	public Timestamp getFechaSolucion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaSolucion);
	}

	/** Set Folio.
		@param Folio Folio	  */
	public void setFolio (int Folio)
	{
		set_Value (COLUMNNAME_Folio, Integer.valueOf(Folio));
	}

	/** Get Folio.
		@return Folio	  */
	public int getFolio () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Folio);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Supplier.
		@param Proveedor Supplier	  */
	public void setProveedor (String Proveedor)
	{
		set_Value (COLUMNNAME_Proveedor, Proveedor);
	}

	/** Get Supplier.
		@return Supplier	  */
	public String getProveedor () 
	{
		return (String)get_Value(COLUMNNAME_Proveedor);
	}

	/** Set Agent.
		@param Representante Agent	  */
	public void setRepresentante (String Representante)
	{
		set_Value (COLUMNNAME_Representante, Representante);
	}

	/** Get Agent.
		@return Agent	  */
	public String getRepresentante () 
	{
		return (String)get_Value(COLUMNNAME_Representante);
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