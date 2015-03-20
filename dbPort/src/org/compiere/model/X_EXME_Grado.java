/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_Grado
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Grado extends PO implements I_EXME_Grado, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Grado (Properties ctx, int EXME_Grado_ID, String trxName)
    {
      super (ctx, EXME_Grado_ID, trxName);
      /** if (EXME_Grado_ID == 0)
        {
			setEXME_Grado_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Grado (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Grado[")
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

	/** Set Grade.
		@param EXME_Grado_ID 
		Militar Grade
	  */
	public void setEXME_Grado_ID (int EXME_Grado_ID)
	{
		if (EXME_Grado_ID < 1)
			 throw new IllegalArgumentException ("EXME_Grado_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Grado_ID, Integer.valueOf(EXME_Grado_ID));
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

	/** Grupo AD_Reference_ID=1200222 */
	public static final int GRUPO_AD_Reference_ID=1200222;
	/** Generals = GG */
	public static final String GRUPO_Generals = "GG";
	/** Chiefs = JJ */
	public static final String GRUPO_Chiefs = "JJ";
	/** Officials = OO */
	public static final String GRUPO_Officials = "OO";
	/** Troops = TT */
	public static final String GRUPO_Troops = "TT";
	/** Set Group.
		@param Grupo Group	  */
	public void setGrupo (String Grupo)
	{

		if (Grupo == null || Grupo.equals("GG") || Grupo.equals("JJ") || Grupo.equals("OO") || Grupo.equals("TT")); else throw new IllegalArgumentException ("Grupo Invalid value - " + Grupo + " - Reference_ID=1200222 - GG - JJ - OO - TT");		set_Value (COLUMNNAME_Grupo, Grupo);
	}

	/** Get Group.
		@return Group	  */
	public String getGrupo () 
	{
		return (String)get_Value(COLUMNNAME_Grupo);
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