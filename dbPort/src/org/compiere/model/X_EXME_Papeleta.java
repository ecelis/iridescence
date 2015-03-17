/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Papeleta
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Papeleta extends PO implements I_EXME_Papeleta, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Papeleta (Properties ctx, int EXME_Papeleta_ID, String trxName)
    {
      super (ctx, EXME_Papeleta_ID, trxName);
      /** if (EXME_Papeleta_ID == 0)
        {
			setCantidad (0);
			setEXME_DestinoRH_ID (0);
			setEXME_Grado_ID (0);
			setEXME_Papeleta_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Papeleta (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Papeleta[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Quantity.
		@param Cantidad 
		Quantity
	  */
	public void setCantidad (int Cantidad)
	{
		set_Value (COLUMNNAME_Cantidad, Integer.valueOf(Cantidad));
	}

	/** Get Quantity.
		@return Quantity
	  */
	public int getCantidad () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Cantidad);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Destiny.
		@param EXME_DestinoRH_ID Destiny	  */
	public void setEXME_DestinoRH_ID (int EXME_DestinoRH_ID)
	{
		if (EXME_DestinoRH_ID < 1)
			 throw new IllegalArgumentException ("EXME_DestinoRH_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_DestinoRH_ID, Integer.valueOf(EXME_DestinoRH_ID));
	}

	/** Get Destiny.
		@return Destiny	  */
	public int getEXME_DestinoRH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_DestinoRH_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Grade.
		@param EXME_Grado_ID 
		Militar Grade
	  */
	public void setEXME_Grado_ID (int EXME_Grado_ID)
	{
		if (EXME_Grado_ID < 1)
			 throw new IllegalArgumentException ("EXME_Grado_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Grado_ID, Integer.valueOf(EXME_Grado_ID));
	}

	/** Get Grade.
		@return Militar Grade
	  */
	public int getEXME_Grado_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Grado_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ballot.
		@param EXME_Papeleta_ID Ballot	  */
	public void setEXME_Papeleta_ID (int EXME_Papeleta_ID)
	{
		if (EXME_Papeleta_ID < 1)
			 throw new IllegalArgumentException ("EXME_Papeleta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Papeleta_ID, Integer.valueOf(EXME_Papeleta_ID));
	}

	/** Get Ballot.
		@return Ballot	  */
	public int getEXME_Papeleta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Papeleta_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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