/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_PacienteAntic
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_PacienteAntic extends PO implements I_EXME_PacienteAntic, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_PacienteAntic (Properties ctx, int EXME_PacienteAntic_ID, String trxName)
    {
      super (ctx, EXME_PacienteAntic_ID, trxName);
      /** if (EXME_PacienteAntic_ID == 0)
        {
			setEXME_Anticonceptivo_ID (0);
			setEXME_PacienteAntic_ID (0);
			setEXME_Paciente_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_PacienteAntic (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_PacienteAntic[")
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

	/** Set Contraceptive.
		@param EXME_Anticonceptivo_ID Contraceptive	  */
	public void setEXME_Anticonceptivo_ID (int EXME_Anticonceptivo_ID)
	{
		if (EXME_Anticonceptivo_ID < 1)
			 throw new IllegalArgumentException ("EXME_Anticonceptivo_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Anticonceptivo_ID, Integer.valueOf(EXME_Anticonceptivo_ID));
	}

	/** Get Contraceptive.
		@return Contraceptive	  */
	public int getEXME_Anticonceptivo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Anticonceptivo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Applied Contraceptives.
		@param EXME_PacienteAntic_ID Applied Contraceptives	  */
	public void setEXME_PacienteAntic_ID (int EXME_PacienteAntic_ID)
	{
		if (EXME_PacienteAntic_ID < 1)
			 throw new IllegalArgumentException ("EXME_PacienteAntic_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_PacienteAntic_ID, Integer.valueOf(EXME_PacienteAntic_ID));
	}

	/** Get Applied Contraceptives.
		@return Applied Contraceptives	  */
	public int getEXME_PacienteAntic_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_PacienteAntic_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Paciente getEXME_Paciente() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Paciente.Table_Name);
        I_EXME_Paciente result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Paciente)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Paciente_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
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

	/** Set Date of Application.
		@param FechaAplica 
		Date of Application
	  */
	public void setFechaAplica (Timestamp FechaAplica)
	{
		set_Value (COLUMNNAME_FechaAplica, FechaAplica);
	}

	/** Get Date of Application.
		@return Date of Application
	  */
	public Timestamp getFechaAplica () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaAplica);
	}

	/** Set Retirement Date.
		@param FechaRetiro Retirement Date	  */
	public void setFechaRetiro (Timestamp FechaRetiro)
	{
		set_Value (COLUMNNAME_FechaRetiro, FechaRetiro);
	}

	/** Get Retirement Date.
		@return Retirement Date	  */
	public Timestamp getFechaRetiro () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaRetiro);
	}

	/** Set Terminate Date.
		@param FechaVencimiento Terminate Date	  */
	public void setFechaVencimiento (Timestamp FechaVencimiento)
	{
		set_Value (COLUMNNAME_FechaVencimiento, FechaVencimiento);
	}

	/** Get Terminate Date.
		@return Terminate Date	  */
	public Timestamp getFechaVencimiento () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaVencimiento);
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