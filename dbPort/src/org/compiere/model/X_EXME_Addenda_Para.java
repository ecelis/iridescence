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

/** Generated Model for EXME_Addenda_Para
 *  @author Generated Class 
 *  @version Version @VERSION@ - $Id$ */
public class X_EXME_Addenda_Para extends PO implements I_EXME_Addenda_Para, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_Addenda_Para (Properties ctx, int EXME_Addenda_Para_ID, String trxName)
    {
      super (ctx, EXME_Addenda_Para_ID, trxName);
      /** if (EXME_Addenda_Para_ID == 0)
        {
			setEXME_Addenda_ID (0);
			setEXME_Addenda_Para_ID (0);
			setIsRequired (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_Addenda_Para (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_Addenda_Para[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** DataType AD_Reference_ID=1200668 */
	public static final int DATATYPE_AD_Reference_ID=1200668;
	/** T = T */
	public static final String DATATYPE_T = "T";
	/** C = C */
	public static final String DATATYPE_C = "C";
	/** Set Data Type.
		@param DataType 
		Type of data
	  */
	public void setDataType (String DataType)
	{

		if (DataType == null || DataType.equals("T") || DataType.equals("C")); else throw new IllegalArgumentException ("DataType Invalid value - " + DataType + " - Reference_ID=1200668 - T - C");		set_Value (COLUMNNAME_DataType, DataType);
	}

	/** Get Data Type.
		@return Type of data
	  */
	public String getDataType () 
	{
		return (String)get_Value(COLUMNNAME_DataType);
	}

	/** Set Default Logic.
		@param DefaultValue 
		Default value hierarchy, separated by ;
	  */
	public void setDefaultValue (String DefaultValue)
	{
		set_Value (COLUMNNAME_DefaultValue, DefaultValue);
	}

	/** Get Default Logic.
		@return Default value hierarchy, separated by ;
	  */
	public String getDefaultValue () 
	{
		return (String)get_Value(COLUMNNAME_DefaultValue);
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

	public I_EXME_Addenda getEXME_Addenda() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_Addenda.Table_Name);
        I_EXME_Addenda result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_Addenda)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_Addenda_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Addendum Settings for Clients.
		@param EXME_Addenda_ID Addendum Settings for Clients	  */
	public void setEXME_Addenda_ID (int EXME_Addenda_ID)
	{
		if (EXME_Addenda_ID < 1)
			 throw new IllegalArgumentException ("EXME_Addenda_ID is mandatory.");
		set_Value (COLUMNNAME_EXME_Addenda_ID, Integer.valueOf(EXME_Addenda_ID));
	}

	/** Get Addendum Settings for Clients.
		@return Addendum Settings for Clients	  */
	public int getEXME_Addenda_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Addenda_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Addendum Settings Parameters for Clients.
		@param EXME_Addenda_Para_ID Addendum Settings Parameters for Clients	  */
	public void setEXME_Addenda_Para_ID (int EXME_Addenda_Para_ID)
	{
		if (EXME_Addenda_Para_ID < 1)
			 throw new IllegalArgumentException ("EXME_Addenda_Para_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_Addenda_Para_ID, Integer.valueOf(EXME_Addenda_Para_ID));
	}

	/** Get Addendum Settings Parameters for Clients.
		@return Addendum Settings Parameters for Clients	  */
	public int getEXME_Addenda_Para_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_Addenda_Para_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Required.
		@param IsRequired 
		Required
	  */
	public void setIsRequired (boolean IsRequired)
	{
		set_Value (COLUMNNAME_IsRequired, Boolean.valueOf(IsRequired));
	}

	/** Get Required.
		@return Required
	  */
	public boolean isRequired () 
	{
		Object oo = get_Value(COLUMNNAME_IsRequired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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