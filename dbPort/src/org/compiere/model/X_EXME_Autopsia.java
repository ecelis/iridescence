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

/** Generated Model for EXME_Autopsia
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Autopsia extends PO implements I_EXME_Autopsia, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Autopsia (Properties ctx, int EXME_Autopsia_ID, String trxName)
    {
      super (ctx, EXME_Autopsia_ID, trxName);
      /** if (EXME_Autopsia_ID == 0)
        {
			setEXME_Autopsia_ID (0);
			setEXME_Medico_ID (0);
			setEXME_Morgue_ID (0);
			setEXME_MotivoMuerte_ID (0);
			setFecha (new Timestamp( System.currentTimeMillis() ));
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Autopsia (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Autopsia[")
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

	/** Set Autopsy.
		@param EXME_Autopsia_ID 
		Autopsy
	  */
	public void setEXME_Autopsia_ID (int EXME_Autopsia_ID)
	{
		if (EXME_Autopsia_ID < 1)
			 throw new IllegalArgumentException ("EXME_Autopsia_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Autopsia_ID, Integer.valueOf(EXME_Autopsia_ID));
	}

	/** Get Autopsy.
		@return Autopsy
	  */
	public int getEXME_Autopsia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Autopsia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Auxiliar Doctor 1.
		@param EXME_MedicoAux1_ID 
		Auxiliar Doctor 1
	  */
	public void setEXME_MedicoAux1_ID (int EXME_MedicoAux1_ID)
	{
		if (EXME_MedicoAux1_ID < 1) 
			set_Value (COLUMNNAME_EXME_MedicoAux1_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MedicoAux1_ID, Integer.valueOf(EXME_MedicoAux1_ID));
	}

	/** Get Auxiliar Doctor 1.
		@return Auxiliar Doctor 1
	  */
	public int getEXME_MedicoAux1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoAux1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Auxiliar Doctor 2.
		@param EXME_MedicoAux2_ID 
		Auxiliar Doctor 2
	  */
	public void setEXME_MedicoAux2_ID (int EXME_MedicoAux2_ID)
	{
		if (EXME_MedicoAux2_ID < 1) 
			set_Value (COLUMNNAME_EXME_MedicoAux2_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_MedicoAux2_ID, Integer.valueOf(EXME_MedicoAux2_ID));
	}

	/** Get Auxiliar Doctor 2.
		@return Auxiliar Doctor 2
	  */
	public int getEXME_MedicoAux2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MedicoAux2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Doctor.
		@param EXME_Medico_ID 
		Doctor
	  */
	public void setEXME_Medico_ID (int EXME_Medico_ID)
	{
		if (EXME_Medico_ID < 1)
			 throw new IllegalArgumentException ("EXME_Medico_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Medico_ID, Integer.valueOf(EXME_Medico_ID));
	}

	/** Get Doctor.
		@return Doctor
	  */
	public int getEXME_Medico_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Medico_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Morgue getEXME_Morgue() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Morgue.Table_Name);
        I_EXME_Morgue result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Morgue)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Morgue_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Morgue.
		@param EXME_Morgue_ID 
		Morgue
	  */
	public void setEXME_Morgue_ID (int EXME_Morgue_ID)
	{
		if (EXME_Morgue_ID < 1)
			 throw new IllegalArgumentException ("EXME_Morgue_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Morgue_ID, Integer.valueOf(EXME_Morgue_ID));
	}

	/** Get Morgue.
		@return Morgue
	  */
	public int getEXME_Morgue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Morgue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MotivoMuerte getEXME_MotivoMuerte() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MotivoMuerte.Table_Name);
        I_EXME_MotivoMuerte result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MotivoMuerte)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MotivoMuerte_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Death Cause.
		@param EXME_MotivoMuerte_ID 
		Death Cause
	  */
	public void setEXME_MotivoMuerte_ID (int EXME_MotivoMuerte_ID)
	{
		if (EXME_MotivoMuerte_ID < 1)
			 throw new IllegalArgumentException ("EXME_MotivoMuerte_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_MotivoMuerte_ID, Integer.valueOf(EXME_MotivoMuerte_ID));
	}

	/** Get Death Cause.
		@return Death Cause
	  */
	public int getEXME_MotivoMuerte_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MotivoMuerte_ID);
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
		if (Fecha == null)
			throw new IllegalArgumentException ("Fecha is mandatory.");
		set_Value (COLUMNNAME_Fecha, Fecha);
	}

	/** Get Date.
		@return Date
	  */
	public Timestamp getFecha () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Fecha);
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