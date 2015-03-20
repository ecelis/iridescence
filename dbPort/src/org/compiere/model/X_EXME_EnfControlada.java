/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_EnfControlada
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_EnfControlada extends PO implements I_EXME_EnfControlada, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_EnfControlada (Properties ctx, int EXME_EnfControlada_ID, String trxName)
    {
      super (ctx, EXME_EnfControlada_ID, trxName);
      /** if (EXME_EnfControlada_ID == 0)
        {
			setEXME_EnfControlada_ID (0);
			setEXME_GrupoEnf_ID (0);
			setName (null);
			setNotificarCaso (false);
			setReqEstEpidemiologico (false);
			setSecuencia (0);
// @SQL=SELECT COALESCE(MAX(secuencia),0)+10 AS DefaultValue FROM EXME_EnfControlada
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_EnfControlada (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_EnfControlada[")
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

	/** Set EPI Key.
		@param EPI_Clave 
		EPI Key
	  */
	public void setEPI_Clave (int EPI_Clave)
	{
		set_Value (COLUMNNAME_EPI_Clave, Integer.valueOf(EPI_Clave));
	}

	/** Get EPI Key.
		@return EPI Key
	  */
	public int getEPI_Clave () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EPI_Clave);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Controlled Illness.
		@param EXME_EnfControlada_ID 
		Controlled Illness
	  */
	public void setEXME_EnfControlada_ID (int EXME_EnfControlada_ID)
	{
		if (EXME_EnfControlada_ID < 1)
			 throw new IllegalArgumentException ("EXME_EnfControlada_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_EnfControlada_ID, Integer.valueOf(EXME_EnfControlada_ID));
	}

	/** Get Controlled Illness.
		@return Controlled Illness
	  */
	public int getEXME_EnfControlada_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_EnfControlada_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_GrupoEnf getEXME_GrupoEnf() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_GrupoEnf.Table_Name);
        I_EXME_GrupoEnf result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_GrupoEnf)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_GrupoEnf_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Illness Group.
		@param EXME_GrupoEnf_ID 
		Controlled illness group
	  */
	public void setEXME_GrupoEnf_ID (int EXME_GrupoEnf_ID)
	{
		if (EXME_GrupoEnf_ID < 1)
			 throw new IllegalArgumentException ("EXME_GrupoEnf_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_GrupoEnf_ID, Integer.valueOf(EXME_GrupoEnf_ID));
	}

	/** Get Illness Group.
		@return Controlled illness group
	  */
	public int getEXME_GrupoEnf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GrupoEnf_ID);
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

	/** Set Notify Case.
		@param NotificarCaso 
		Notify case of a controlled illness
	  */
	public void setNotificarCaso (boolean NotificarCaso)
	{
		set_Value (COLUMNNAME_NotificarCaso, Boolean.valueOf(NotificarCaso));
	}

	/** Get Notify Case.
		@return Notify case of a controlled illness
	  */
	public boolean isNotificarCaso () 
	{
		Object oo = get_Value(COLUMNNAME_NotificarCaso);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Requires Epidemiological Study.
		@param ReqEstEpidemiologico 
		The controlled illness requires epidemiological study
	  */
	public void setReqEstEpidemiologico (boolean ReqEstEpidemiologico)
	{
		set_Value (COLUMNNAME_ReqEstEpidemiologico, Boolean.valueOf(ReqEstEpidemiologico));
	}

	/** Get Requires Epidemiological Study.
		@return The controlled illness requires epidemiological study
	  */
	public boolean isReqEstEpidemiologico () 
	{
		Object oo = get_Value(COLUMNNAME_ReqEstEpidemiologico);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sequence.
		@param Secuencia 
		Sequence
	  */
	public void setSecuencia (int Secuencia)
	{
		set_Value (COLUMNNAME_Secuencia, Integer.valueOf(Secuencia));
	}

	/** Get Sequence.
		@return Sequence
	  */
	public int getSecuencia () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Secuencia);
		if (ii == null)
			 return 0;
		return ii.intValue();
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