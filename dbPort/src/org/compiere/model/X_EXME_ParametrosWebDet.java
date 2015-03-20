/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for EXME_ParametrosWebDet
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ParametrosWebDet extends PO implements I_EXME_ParametrosWebDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ParametrosWebDet (Properties ctx, int EXME_ParametrosWebDet_ID, String trxName)
    {
      super (ctx, EXME_ParametrosWebDet_ID, trxName);
      /** if (EXME_ParametrosWebDet_ID == 0)
        {
			setEXME_ParametrosWebDet_ID (0);
			setEXME_ParametrosWebH_ID (0);
        } */
    }

    /** Load Constructor */
    public X_EXME_ParametrosWebDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ParametrosWebDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Parametros Header para ruta web externas.
		@param EXME_ParametrosWebDet_ID 
		Parametros Header para ruta web externas
	  */
	public void setEXME_ParametrosWebDet_ID (int EXME_ParametrosWebDet_ID)
	{
		if (EXME_ParametrosWebDet_ID < 1)
			 throw new IllegalArgumentException ("EXME_ParametrosWebDet_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ParametrosWebDet_ID, Integer.valueOf(EXME_ParametrosWebDet_ID));
	}

	/** Get Parametros Header para ruta web externas.
		@return Parametros Header para ruta web externas
	  */
	public int getEXME_ParametrosWebDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ParametrosWebDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ParametrosWebH getEXME_ParametrosWebH() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ParametrosWebH.Table_Name);
        I_EXME_ParametrosWebH result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ParametrosWebH)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ParametrosWebH_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Access Link Configuration Parameter.
		@param EXME_ParametrosWebH_ID 
		Access Link Configuration Parameter
	  */
	public void setEXME_ParametrosWebH_ID (int EXME_ParametrosWebH_ID)
	{
		if (EXME_ParametrosWebH_ID < 1)
			 throw new IllegalArgumentException ("EXME_ParametrosWebH_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_ParametrosWebH_ID, Integer.valueOf(EXME_ParametrosWebH_ID));
	}

	/** Get Access Link Configuration Parameter.
		@return Access Link Configuration Parameter
	  */
	public int getEXME_ParametrosWebH_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ParametrosWebH_ID);
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

	/** Set Notes.
		@param Observaciones 
		Notes
	  */
	public void setObservaciones (String Observaciones)
	{
		set_Value (COLUMNNAME_Observaciones, Observaciones);
	}

	/** Get Notes.
		@return Notes
	  */
	public String getObservaciones () 
	{
		return (String)get_Value(COLUMNNAME_Observaciones);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
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