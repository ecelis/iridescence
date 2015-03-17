/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ViasAdministracion
 *  @author Generated Class 
 *  @version Release @VERSION@ - $Id$ */
public class X_EXME_ViasAdministracion extends PO implements I_EXME_ViasAdministracion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ViasAdministracion (Properties ctx, int EXME_ViasAdministracion_ID, String trxName)
    {
      super (ctx, EXME_ViasAdministracion_ID, trxName);
      /** if (EXME_ViasAdministracion_ID == 0)
        {
			setDescription (null);
			setEXME_ViasAdministracion_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ViasAdministracion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ViasAdministracion[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Route of Administration.
		@param EXME_ViasAdministracion_ID Route of Administration	  */
	public void setEXME_ViasAdministracion_ID (int EXME_ViasAdministracion_ID)
	{
		if (EXME_ViasAdministracion_ID < 1)
			 throw new IllegalArgumentException ("EXME_ViasAdministracion_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ViasAdministracion_ID, Integer.valueOf(EXME_ViasAdministracion_ID));
	}

	/** Get Route of Administration.
		@return Route of Administration	  */
	public int getEXME_ViasAdministracion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ViasAdministracion_ID);
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

	/** Tipo AD_Reference_ID=1200197 */
	public static final int TIPO_AD_Reference_ID=1200197;
	/** Diet = D */
	public static final String TIPO_Diet = "D";
	/** Medicine = M */
	public static final String TIPO_Medicine = "M";
	/** Set Type.
		@param Tipo Type	  */
	public void setTipo (String Tipo)
	{

		if (Tipo == null || Tipo.equals("D") || Tipo.equals("M")); else throw new IllegalArgumentException ("Tipo Invalid value - " + Tipo + " - Reference_ID=1200197 - D - M");		set_Value (COLUMNNAME_Tipo, Tipo);
	}

	/** Get Type.
		@return Type	  */
	public String getTipo () 
	{
		return (String)get_Value(COLUMNNAME_Tipo);
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

	/** Set Route of Administration.
		@param Via 
		Route of Administration
	  */
	public void setVia (String Via)
	{
		set_Value (COLUMNNAME_Via, Via);
	}

	/** Get Route of Administration.
		@return Route of Administration
	  */
	public String getVia () 
	{
		return (String)get_Value(COLUMNNAME_Via);
	}
}