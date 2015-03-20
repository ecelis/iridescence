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

/** Generated Model for EXME_ConfigDieta_Det
 *  @author Generated Class 
 *  @version Release 1.2 - $Id$ */
public class X_EXME_ConfigDieta_Det extends PO implements I_EXME_ConfigDieta_Det, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_EXME_ConfigDieta_Det (Properties ctx, int EXME_ConfigDieta_Det_ID, String trxName)
    {
      super (ctx, EXME_ConfigDieta_Det_ID, trxName);
      /** if (EXME_ConfigDieta_Det_ID == 0)
        {
			setDescription (null);
			setEXME_ConfigDieta_Det_ID (0);
			setEXME_ConfigDieta_ID (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_EXME_ConfigDieta_Det (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_EXME_ConfigDieta_Det[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Configuration Diet Detail.
		@param EXME_ConfigDieta_Det_ID 
		Configuration Diet Detail
	  */
	public void setEXME_ConfigDieta_Det_ID (int EXME_ConfigDieta_Det_ID)
	{
		if (EXME_ConfigDieta_Det_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigDieta_Det_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigDieta_Det_ID, Integer.valueOf(EXME_ConfigDieta_Det_ID));
	}

	/** Get Configuration Diet Detail.
		@return Configuration Diet Detail
	  */
	public int getEXME_ConfigDieta_Det_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigDieta_Det_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_EXME_ConfigDieta getEXME_ConfigDieta() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_EXME_ConfigDieta.Table_Name);
        I_EXME_ConfigDieta result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_EXME_ConfigDieta)constructor.newInstance(new Object[] {getCtx(), new Integer(getEXME_ConfigDieta_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Diet Configuration.
		@param EXME_ConfigDieta_ID Diet Configuration	  */
	public void setEXME_ConfigDieta_ID (int EXME_ConfigDieta_ID)
	{
		if (EXME_ConfigDieta_ID < 1)
			 throw new IllegalArgumentException ("EXME_ConfigDieta_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_EXME_ConfigDieta_ID, Integer.valueOf(EXME_ConfigDieta_ID));
	}

	/** Get Diet Configuration.
		@return Diet Configuration	  */
	public int getEXME_ConfigDieta_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXME_ConfigDieta_ID);
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