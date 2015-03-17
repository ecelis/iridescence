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

/** Generated Model for EXME_TipoActividad
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_TipoActividad extends PO implements I_EXME_TipoActividad, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_TipoActividad (Properties ctx, int EXME_TipoActividad_ID, String trxName)
    {
      super (ctx, EXME_TipoActividad_ID, trxName);
      /** if (EXME_TipoActividad_ID == 0)
        {
			setEXME_TipoActividad_ID (0);
			setName (null);
			setUsaSustituto (false);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_TipoActividad (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_TipoActividad[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Color getAD_Color() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Color.Table_Name);
        I_AD_Color result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Color)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Color_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set System Color.
		@param AD_Color_ID 
		Color for backgrounds or indicators
	  */
	public void setAD_Color_ID (int AD_Color_ID)
	{
		if (AD_Color_ID < 1) 
			set_Value (COLUMNNAME_AD_Color_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Color_ID, Integer.valueOf(AD_Color_ID));
	}

	/** Get System Color.
		@return Color for backgrounds or indicators
	  */
	public int getAD_Color_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Color_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Activity Type.
		@param EXME_TipoActividad_ID Activity Type	  */
	public void setEXME_TipoActividad_ID (int EXME_TipoActividad_ID)
	{
		if (EXME_TipoActividad_ID < 1)
			 throw new IllegalArgumentException ("EXME_TipoActividad_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_TipoActividad_ID, Integer.valueOf(EXME_TipoActividad_ID));
	}

	/** Get Activity Type.
		@return Activity Type	  */
	public int getEXME_TipoActividad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_TipoActividad_ID);
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

	/** Set Use Substitute.
		@param UsaSustituto 
		Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public void setUsaSustituto (boolean UsaSustituto)
	{
		set_Value (COLUMNNAME_UsaSustituto, Boolean.valueOf(UsaSustituto));
	}

	/** Get Use Substitute.
		@return Indicates if a new version of packages  has been discontinued, ask for the substitutes
	  */
	public boolean isUsaSustituto () 
	{
		Object oo = get_Value(COLUMNNAME_UsaSustituto);
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