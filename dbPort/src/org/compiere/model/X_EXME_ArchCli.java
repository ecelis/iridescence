/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/** Generated Model for EXME_ArchCli
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ArchCli extends PO implements I_EXME_ArchCli, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ArchCli (Properties ctx, int EXME_ArchCli_ID, String trxName)
    {
      super (ctx, EXME_ArchCli_ID, trxName);
      /** if (EXME_ArchCli_ID == 0)
        {
			setEXME_ArchCli_ID (0);
			setEXME_Paciente_ID (0);
			setEXME_TipoExped_ID (0);
			setM_Locator_ID (0);
			setM_Warehouse_ID (0);
// @M_Warehouse_ID@
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ArchCli (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ArchCli[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Code.
		@param Codigo Code	  */
	public void setCodigo (String Codigo)
	{
		set_Value (COLUMNNAME_Codigo, Codigo);
	}

	/** Get Code.
		@return Code	  */
	public String getCodigo () 
	{
		return (String)get_Value(COLUMNNAME_Codigo);
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

	/** Set Clinical Archive.
		@param EXME_ArchCli_ID 
		Clinical Archive
	  */
	public void setEXME_ArchCli_ID (int EXME_ArchCli_ID)
	{
		if (EXME_ArchCli_ID < 1)
			 throw new IllegalArgumentException ("EXME_ArchCli_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ArchCli_ID, Integer.valueOf(EXME_ArchCli_ID));
	}

	/** Get Clinical Archive.
		@return Clinical Archive
	  */
	public int getEXME_ArchCli_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ArchCli_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Patient.
		@param EXME_Paciente_ID 
		Patient
	  */
	public void setEXME_Paciente_ID (int EXME_Paciente_ID)
	{
		if (EXME_Paciente_ID < 1)
			 throw new IllegalArgumentException ("EXME_Paciente_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Paciente_ID, Integer.valueOf(EXME_Paciente_ID));
	}

	/** Get Patient.
		@return Patient
	  */
	public int getEXME_Paciente_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Paciente_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Type of Medical Record.
		@param EXME_TipoExped_ID 
		Type of Medical Record
	  */
	public void setEXME_TipoExped_ID (int EXME_TipoExped_ID)
	{
		if (EXME_TipoExped_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoExped_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_TipoExped_ID, Integer.valueOf(EXME_TipoExped_ID));
	}

	/** Get Type of Medical Record.
		@return Type of Medical Record
	  */
	public int getEXME_TipoExped_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoExped_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Creation Date.
		@param Fecha_Creacion 
		creation Date
	  */
	public void setFecha_Creacion (Timestamp Fecha_Creacion)
	{
		set_Value (COLUMNNAME_Fecha_Creacion, Fecha_Creacion);
	}

	/** Get Creation Date.
		@return creation Date
	  */
	public Timestamp getFecha_Creacion () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha_Creacion);
	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1)
			 throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
		set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 1)
			 throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
		set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
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

	/** Set Location of Element.
		@param Ubicacion 
		Location of Element
	  */
	public void setUbicacion (String Ubicacion)
	{
		set_Value (COLUMNNAME_Ubicacion, Ubicacion);
	}

	/** Get Location of Element.
		@return Location of Element
	  */
	public String getUbicacion () 
	{
		return (String)get_Value(COLUMNNAME_Ubicacion);
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