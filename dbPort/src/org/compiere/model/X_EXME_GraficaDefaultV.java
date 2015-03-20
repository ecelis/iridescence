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

/** Generated Model for EXME_GraficaDefaultV
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_GraficaDefaultV extends PO implements I_EXME_GraficaDefaultV, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_GraficaDefaultV (Properties ctx, int EXME_GraficaDefaultV_ID, String trxName)
    {
      super (ctx, EXME_GraficaDefaultV_ID, trxName);
      /** if (EXME_GraficaDefaultV_ID == 0)
        {
			setEXME_GraficaDefaultV_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_GraficaDefaultV (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_EXME_GraficaDefaultV[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Graphic Default Values.
		@param EXME_GraficaDefaultV_ID Graphic Default Values	  */
	public void setEXME_GraficaDefaultV_ID (int EXME_GraficaDefaultV_ID)
	{
		if (EXME_GraficaDefaultV_ID < 1)
			 throw new IllegalArgumentException ("EXME_GraficaDefaultV_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_GraficaDefaultV_ID, Integer.valueOf(EXME_GraficaDefaultV_ID));
	}

	/** Get Graphic Default Values.
		@return Graphic Default Values	  */
	public int getEXME_GraficaDefaultV_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_GraficaDefaultV_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_Grafica getEXME_Grafica() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Grafica.Table_Name);
        I_EXME_Grafica result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Grafica)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Grafica_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Graphic.
		@param EXME_Grafica_ID Graphic	  */
	public void setEXME_Grafica_ID (int EXME_Grafica_ID)
	{
		if (EXME_Grafica_ID < 1) 
			set_Value (COLUMNNAME_EXME_Grafica_ID, null);
		else 
			set_Value (COLUMNNAME_EXME_Grafica_ID, Integer.valueOf(EXME_Grafica_ID));
	}

	/** Get Graphic.
		@return Graphic	  */
	public int getEXME_Grafica_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Grafica_ID);
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