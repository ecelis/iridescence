/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PuestoOrg
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_PuestoOrg extends PO implements I_EXME_PuestoOrg, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PuestoOrg (Properties ctx, int EXME_PuestoOrg_ID, String trxName)
    {
      super (ctx, EXME_PuestoOrg_ID, trxName);
      /** if (EXME_PuestoOrg_ID == 0)
        {
			setEXME_PuestoOrg_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PuestoOrg (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PuestoOrg[")
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

	/** Set Administrativo Director.
		@param Director_Administrativo Administrativo Director	  */
	public void setDirector_Administrativo (String Director_Administrativo)
	{
		set_Value (COLUMNNAME_Director_Administrativo, Director_Administrativo);
	}

	/** Get Administrativo Director.
		@return Administrativo Director	  */
	public String getDirector_Administrativo () 
	{
		return (String)get_Value(COLUMNNAME_Director_Administrativo);
	}

	/** Set General manager.
		@param Director_General General manager	  */
	public void setDirector_General (String Director_General)
	{
		set_Value (COLUMNNAME_Director_General, Director_General);
	}

	/** Get General manager.
		@return General manager	  */
	public String getDirector_General () 
	{
		return (String)get_Value(COLUMNNAME_Director_General);
	}

	/** Set Job Position Org.
		@param EXME_PuestoOrg_ID 
		Job Position Org
	  */
	public void setEXME_PuestoOrg_ID (int EXME_PuestoOrg_ID)
	{
		if (EXME_PuestoOrg_ID < 1)
			 throw new IllegalArgumentException ("EXME_PuestoOrg_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PuestoOrg_ID, Integer.valueOf(EXME_PuestoOrg_ID));
	}

	/** Get Job Position Org.
		@return Job Position Org
	  */
	public int getEXME_PuestoOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PuestoOrg_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Purchase Manager.
		@param Jefe_Compras Purchase Manager	  */
	public void setJefe_Compras (String Jefe_Compras)
	{
		set_Value (COLUMNNAME_Jefe_Compras, Jefe_Compras);
	}

	/** Get Purchase Manager.
		@return Purchase Manager	  */
	public String getJefe_Compras () 
	{
		return (String)get_Value(COLUMNNAME_Jefe_Compras);
	}

	/** Set Accountant Manager.
		@param Jefe_Contabilidad Accountant Manager	  */
	public void setJefe_Contabilidad (String Jefe_Contabilidad)
	{
		set_Value (COLUMNNAME_Jefe_Contabilidad, Jefe_Contabilidad);
	}

	/** Get Accountant Manager.
		@return Accountant Manager	  */
	public String getJefe_Contabilidad () 
	{
		return (String)get_Value(COLUMNNAME_Jefe_Contabilidad);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
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