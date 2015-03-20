/******************************************************************************
 * (C) eCareSoft, LLC.                                                        *
 ******************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for EXME_Nacionalidad
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_Nacionalidad extends PO implements I_EXME_Nacionalidad, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Nacionalidad (Properties ctx, int EXME_Nacionalidad_ID, String trxName)
    {
      super (ctx, EXME_Nacionalidad_ID, trxName);
      /** if (EXME_Nacionalidad_ID == 0)
        {
			setEXME_Nacionalidad_ID (0);
			setIsDefault (false);
// N
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Nacionalidad (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Nacionalidad[")
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

	/** Set Nationality.
		@param EXME_Nacionalidad_ID 
		Nationality
	  */
	public void setEXME_Nacionalidad_ID (int EXME_Nacionalidad_ID)
	{
		if (EXME_Nacionalidad_ID < 1)
			 throw new IllegalArgumentException ("EXME_Nacionalidad_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Nacionalidad_ID, Integer.valueOf(EXME_Nacionalidad_ID));
	}

	/** Get Nationality.
		@return Nationality
	  */
	public int getEXME_Nacionalidad_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Nacionalidad_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
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

	public I_C_Country getC_Country() throws RuntimeException {
		Class<?> clazz = MTable.getClass(I_C_Country.Table_Name);
		I_C_Country result = null;
		try {
			Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
			result = (I_C_Country) constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Country_ID()), get_TrxName()});
		} catch (Exception e) {
			final String errorStr = "Table=" + Table_Name + ",Class=" + clazz;
			log.log(Level.SEVERE, errorStr);
			log.saveError("Error", errorStr);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Set Country.
	 *	@param C_Country_ID
	 *		Country 
	*/
	public void setC_Country_ID(int C_Country_ID) {
		if (C_Country_ID < 1) {
			set_Value(COLUMNNAME_C_Country_ID, null);
		} else {
			set_Value(COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
		}
	}

	/** Get Country.
	 *	@return Country 	*/
	public int getC_Country_ID() { 
		Integer ii = (Integer) get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null) {
			return 0;
		}
		return ii.intValue();
	}
}