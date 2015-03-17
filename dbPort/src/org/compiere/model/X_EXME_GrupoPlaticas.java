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

/** Generated Model for EXME_GrupoPlaticas
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_GrupoPlaticas extends PO implements I_EXME_GrupoPlaticas, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_GrupoPlaticas (Properties ctx, int EXME_GrupoPlaticas_ID, String trxName)
    {
      super (ctx, EXME_GrupoPlaticas_ID, trxName);
      /** if (EXME_GrupoPlaticas_ID == 0)
        {
			setConferencista (null);
			setEXME_GrupoPlaticas_ID (0);
			setEXME_MO_Platicas_ID (0);
			setFechaHr (new Timestamp( System.currentTimeMillis() ));
			setPlaticaImpartida (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_GrupoPlaticas (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_GrupoPlaticas[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Assistants.
		@param Asistentes Assistants	  */
	public void setAsistentes (int Asistentes)
	{
		set_Value (COLUMNNAME_Asistentes, Integer.valueOf(Asistentes));
	}

	/** Get Assistants.
		@return Assistants	  */
	public int getAsistentes () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Asistentes);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Lecturer.
		@param Conferencista 
		Name of lecturer who teaches the conversation 
	  */
	public void setConferencista (String Conferencista)
	{
		if (Conferencista == null)
			throw new IllegalArgumentException ("Conferencista is mandatory.");
		set_Value (COLUMNNAME_Conferencista, Conferencista);
	}

	/** Get Lecturer.
		@return Name of lecturer who teaches the conversation 
	  */
	public String getConferencista () 
	{
		return (String)get_Value(COLUMNNAME_Conferencista);
	}

	/** Set Chat Group.
		@param EXME_GrupoPlaticas_ID Chat Group	  */
	public void setEXME_GrupoPlaticas_ID (int EXME_GrupoPlaticas_ID)
	{
		if (EXME_GrupoPlaticas_ID < 1)
			 throw new IllegalArgumentException ("EXME_GrupoPlaticas_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_GrupoPlaticas_ID, Integer.valueOf(EXME_GrupoPlaticas_ID));
	}

	/** Get Chat Group.
		@return Chat Group	  */
	public int getEXME_GrupoPlaticas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GrupoPlaticas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_MO_Platicas getEXME_MO_Platicas() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_MO_Platicas.Table_Name);
        I_EXME_MO_Platicas result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_MO_Platicas)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_MO_Platicas_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Talk.
		@param EXME_MO_Platicas_ID 
		Talk  by Speciality
	  */
	public void setEXME_MO_Platicas_ID (int EXME_MO_Platicas_ID)
	{
		if (EXME_MO_Platicas_ID < 1)
			 throw new IllegalArgumentException ("EXME_MO_Platicas_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_MO_Platicas_ID, Integer.valueOf(EXME_MO_Platicas_ID));
	}

	/** Get Talk.
		@return Talk  by Speciality
	  */
	public int getEXME_MO_Platicas_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_MO_Platicas_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Hr and Date.
		@param FechaHr 
		Hr and Date
	  */
	public void setFechaHr (Timestamp FechaHr)
	{
		if (FechaHr == null)
			throw new IllegalArgumentException ("FechaHr is mandatory.");
		set_Value (COLUMNNAME_FechaHr, FechaHr);
	}

	/** Get Hr and Date.
		@return Hr and Date
	  */
	public Timestamp getFechaHr () 
	{
		return (Timestamp)get_Value(COLUMNNAME_FechaHr);
	}

	/** Set Chat Group Distributed.
		@param PlaticaImpartida Chat Group Distributed	  */
	public void setPlaticaImpartida (boolean PlaticaImpartida)
	{
		set_Value (COLUMNNAME_PlaticaImpartida, Boolean.valueOf(PlaticaImpartida));
	}

	/** Get Chat Group Distributed.
		@return Chat Group Distributed	  */
	public boolean isPlaticaImpartida () 
	{
		Object oo = get_Value(COLUMNNAME_PlaticaImpartida);
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